package com.mbappe.newsql.utils;

import com.mbappe.newsql.metaq.DbInfo;
import com.mbappe.newsql.utils.Logger;
import jodd.util.StringUtil;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: gannicus at 2018/7/25
 */
public class DBUtil {

    private static Logger logger = Logger.getLogger(DBUtil.class);
    private static final String DIRVERCLASSNAME = "com.mysql.jdbc.Driver";
    private static String urltmp = "jdbc:mysql://%s/%s?zeroDateTimeBehavior=round&useUnicode=true&characterEncoding=UTF-8&useSSL=false";
    private static Map<String, Connection> connectionHashMap = new ConcurrentHashMap<>();
    private static Map<String, ConnectionWrapper> hashMap = new ConcurrentHashMap<>();
    // 重连时间间隔,毫秒
    private static final Long RECONNECT_IDLE_TIME = 3600 * 1000L;
    // 超时,秒
    private static final int TIMEOUT = 1;

    /**
     * 每次都判断是否有效连接
     *
     * @param db 数据库信息
     * @return 连接
     */
    private static Connection getConnection(DbInfo db) {
        String dbInfoHash = String.valueOf(db.hashCode());
        if (connectionHashMap.containsKey(dbInfoHash)) {
            Connection connection = connectionHashMap.get(dbInfoHash);
            try {
                if (connection.isValid(TIMEOUT)) {
                    return connection;
                }
            } catch (SQLException e) {
                logger.error(e, "");
            }
        }
        Connection conn = null;
        try {
            Class.forName(DIRVERCLASSNAME);
        } catch (ClassNotFoundException e) {
            logger.error(e, "getConnection error: {}", db.toString());
        }
        String addr = db.getmHost() + ":" + db.getmPort();
        String url = String.format(urltmp, addr, db.getDb());
        conn = makeConnection(url, db);
        if (null == conn) {
            return null;
        }
        connectionHashMap.put(dbInfoHash, conn);
        return conn;
    }

    /**
     * 定时重新连接
     *
     * @param db DbInfo
     * @return Connection
     */
    private static Connection getConnectionV2(DbInfo db) {
        String dbInfoHash = String.valueOf(db.hashCode());
        if (hashMap.containsKey(dbInfoHash)) {
            Connection connection = hashMap.get(dbInfoHash).getConnection();
            if (System.currentTimeMillis() - hashMap.get(dbInfoHash).getConnectedTime() < RECONNECT_IDLE_TIME) {
                logger.info("Thread: {}, get conn {}", Thread.currentThread().getName(), db.getmHost());
                return connection;
            } else {
                try {
                    logger.info("Thread: {}, close conn {}", Thread.currentThread().getName(), db.getmHost());
                    connection.close();
                } catch (SQLException e) {
                    logger.error(e, "close connection error.{}", db.getmHost());
                }
            }
        }
        Connection conn = null;
        try {
            Class.forName(DIRVERCLASSNAME);
        } catch (Exception e) {
            logger.error(e, "getConnection error: {}", db.toString());
        }
        String addr = db.getmHost() + ":" + db.getmPort();
        String url = String.format(urltmp, addr, db.getDb());
        conn = makeConnection(url, db);
        if (null == conn) {
            return null;
        }
        ConnectionWrapper connectionWrapper = new ConnectionWrapper();
        connectionWrapper.setConnectedTime(System.currentTimeMillis());
        connectionWrapper.setConnection(conn);
        logger.info("connect {}, at {}", db.getmHost(), System.currentTimeMillis());
        hashMap.put(dbInfoHash, connectionWrapper);
        return conn;
    }

    private static Connection makeConnection(String url, DbInfo dbInfo) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, dbInfo.getmUserName(), dbInfo.getmPwd());
        } catch (SQLException e) {
            logger.error(e, "getConnection error: {}，{}", dbInfo.getmHost(), dbInfo.getmPort());
        }
        return conn;
    }

    public static Object getList(DbInfo db, Class cl, String sql) {
        QueryRunner qRunner = new QueryRunner();
        Object ret = null;
        try {
            Connection conn = getConnectionV2(db);
            if (null == conn) {
                return null;
            }
            ret = qRunner.query(conn, sql, new BeanListHandler<Object>(cl));
        } catch (Exception e) {
            logger.error(e, "sql getList error:{}, {}", db.toString(), sql);
        }
        return ret;
    }

    public static Map<String, Object> get(DbInfo db, String sql) {
        QueryRunner qRunner = new QueryRunner();
        Map<String, Object> ret = null;
        try {
            Connection conn = getConnectionV2(db);
            if (null == conn) {
                return null;
            }
            ret = qRunner.query(conn, sql, new MapHandler(new BasicRowProcessor()));
        } catch (Exception e) {
            logger.error(e, "sql query error: {}, {}", db.toString(), sql);
        }
        return ret;
    }

    static class ConnectionWrapper {
        Connection connection;
        Long connectedTime;

        Connection getConnection() {
            return connection;
        }

        void setConnection(Connection connection) {
            this.connection = connection;
        }

        Long getConnectedTime() {
            return connectedTime;
        }

        void setConnectedTime(Long connectedTime) {
            this.connectedTime = connectedTime;
        }
    }

}
