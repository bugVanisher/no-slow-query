package com.mbappe.newsql.utils;

import jodd.util.StringUtil;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author gannicus at 2019/2/20
 */
public class Logger {
    private static final Map<String, Logger> LoggerInstances = new ConcurrentHashMap<String, Logger>();

    private static final String host = getHost();

    private static final String appName = getAppName();

    private org.slf4j.Logger logger;

    public static final String SYSTEM_PROPERTY_NAME_APP_NAME = "project.name";

    private static String getHost() {
        try {
            InetAddress e = InetAddress.getLocalHost();
            return e.getHostName();
        } catch (UnknownHostException arg3) {
            return "-";
        }
    }

    private static String getAppName() {
        String name = System.getProperties().getProperty(SYSTEM_PROPERTY_NAME_APP_NAME);
        if (StringUtil.isEmpty(name)) {
            return "-";
        }
        return name;
    }

    private Logger(org.slf4j.Logger logger) {
        this.logger = logger;
    }

    public static Logger getLogger(Class<?> clazz) {
        return getLogger(clazz.getName());
    }

    public static Logger getLogger(String loggerName) {
        synchronized (Logger.class) {
            Logger logger = LoggerInstances.get(loggerName);
            if (logger == null) {
                logger = new Logger(org.slf4j.LoggerFactory.getLogger(loggerName));
                LoggerInstances.put(loggerName, logger);
            }
            return logger;
        }
    }

    /**
     * trace级别 log接口
     */
    public void trace(String msg) {
        if (!logger.isTraceEnabled()) {
            return;
        }
        logger.trace(msg);
    }

    public void trace(String format, Object arg) {
        if (!logger.isTraceEnabled()) {
            return;
        }
        logger.trace(format, arg);
    }

    public void trace(String format, Object arg1, Object arg2) {
        if (!logger.isTraceEnabled()) {
            return;
        }
        logger.trace(format, arg1, arg2);
    }

    public void trace(String format, Object... arguments) {
        if (!logger.isTraceEnabled()) {
            return;
        }
        logger.trace(format, arguments);
    }

    public void trace(Throwable t, String msg) {
        if (!logger.isTraceEnabled()) {
            return;
        }

        logger.trace(msg);
    }

    public void trace(Throwable t, String msg, Object... arguments) {
        if (!logger.isTraceEnabled()) {
            return;
        }

        logger.trace(msg, arguments);
    }

    /**
     * debug级别 log接口
     */
    public void debug(String msg) {
        if (!logger.isDebugEnabled()) {
            return;
        }
        logger.debug(msg);
    }

    public void debug(String format, Object arg) {
        if (!logger.isDebugEnabled()) {
            return;
        }
        logger.debug(format, arg);
    }

    public void debug(String format, Object arg1, Object arg2) {
        if (!logger.isDebugEnabled()) {
            return;
        }
        logger.debug(format, arg1, arg2);
    }

    public void debug(String format, Object... arguments) {
        if (!logger.isDebugEnabled()) {
            return;
        }
        logger.debug(format, arguments);
    }

    public void debug(Throwable t, String msg) {
        if (!logger.isDebugEnabled()) {
            return;
        }

        logger.debug(msg);
    }

    public void debug(Throwable t, String msg, Object... arguments) {
        if (!logger.isDebugEnabled()) {
            return;
        }

        logger.debug(msg, arguments);
    }


    /**
     * info级别 log接口
     */
    public void info(String msg) {
        if (!logger.isInfoEnabled()) {
            return;
        }
        logger.info(msg);
    }

    public void info(String format, Object arg) {
        if (!logger.isInfoEnabled()) {
            return;
        }
        logger.info(format, arg);
    }

    public void info(String format, Object arg1, Object arg2) {
        if (!logger.isInfoEnabled()) {
            return;
        }
        logger.info(format, arg1, arg2);
    }

    public void info(String format, Object... arguments) {
        if (!logger.isInfoEnabled()) {
            return;
        }
        logger.info(format, arguments);
    }

    public void info(Throwable t, String msg) {
        if (!logger.isInfoEnabled()) {
            return;
        }

        logger.info(msg);
    }

    public void info(Throwable t, String msg, Object... arguments) {
        if (!logger.isInfoEnabled()) {
            return;
        }

        logger.info(msg, arguments);
    }

    /**
     * warn级别 log接口
     */
    public void warn(String msg) {
        if (!logger.isWarnEnabled()) {
            return;
        }
        logger.warn(msg);
    }

    public void warn(String format, Object arg) {
        if (!logger.isWarnEnabled()) {
            return;
        }
        logger.warn(format, arg);
    }


    public void warn(String format, Object arg1, Object arg2) {
        if (!logger.isWarnEnabled()) {
            return;
        }
        logger.warn(format, arg1, arg2);
    }

    public void warn(String format, Object... arguments) {
        if (!logger.isWarnEnabled()) {
            return;
        }
        logger.warn(format, arguments);
    }

    public void warn(Throwable t, String msg) {
        if (!logger.isWarnEnabled()) {
            return;
        }

        logger.warn(msg);
    }

    public void warn(Throwable t, String msg, Object... arguments) {
        if (!logger.isWarnEnabled()) {
            return;
        }

        logger.warn(msg, arguments);
    }

    /**
     * error级别 log接口
     */
    public void error(String msg) {
        if (!logger.isErrorEnabled()) {
            return;
        }
        logger.error(msg);
    }

    public void error(String format, Object arg) {
        if (!logger.isErrorEnabled()) {
            return;
        }

        logger.error(format, arg);
    }

    public void error(String format, Object arg1, Object arg2) {
        if (!logger.isErrorEnabled()) {
            return;
        }

        logger.error(format, arg1, arg2);
    }

    public void error(String format, Object... arguments) {
        if (!logger.isErrorEnabled()) {
            return;
        }

        logger.error(format, arguments);
    }

    public void error(Throwable t, String msg) {
        if (!logger.isErrorEnabled()) {
            return;
        }

        logger.error(msg);
    }

    public void error(Throwable t, String msg, Object... arguments) {
        if (!logger.isErrorEnabled()) {
            return;
        }

        logger.error(msg, arguments);
    }

}

