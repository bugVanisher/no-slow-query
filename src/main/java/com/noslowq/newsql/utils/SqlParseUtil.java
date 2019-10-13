package com.noslowq.newsql.utils;

import com.alibaba.fastsql.DbType;
import com.alibaba.fastsql.sql.SQLUtils;
import com.alibaba.fastsql.sql.ast.SQLObject;
import com.alibaba.fastsql.sql.ast.SQLStatement;
import com.alibaba.fastsql.sql.parser.SQLParserUtils;
import com.alibaba.fastsql.sql.parser.SQLType;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.util.TablesNamesFinder;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.alibaba.fastsql.sql.parser.SQLParserFeature.SkipComments;
import static com.alibaba.fastsql.sql.visitor.VisitorFeature.OutputParameterized;
import static com.alibaba.fastsql.sql.visitor.VisitorFeature.OutputUCase;

/**
 * @author : gannicus at 2018/8/24
 */
public class SqlParseUtil {

    private static final Logger logger = Logger.getLogger(SqlParseUtil.class);

    private final static Pattern TABLE_NAME_PATTERN = Pattern.compile("select.*?from[\\s`]+(\\w+)[`\\s]?");


    public static List<String> getTableNames(String sql) {
        Statement stmt = null;
        try {
            stmt = CCJSqlParserUtil.parse(sql);
        } catch (JSQLParserException e) {
            logger.error(e, "parse error: {}");
        }
        Select selectStatement = (Select) stmt;
        if (null == selectStatement) {
            return null;
        }
        TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
        return tablesNamesFinder.getTableList(selectStatement);
    }

    public static String getTableName(String sql) {
        Matcher m = TABLE_NAME_PATTERN.matcher(sql.trim().toLowerCase());
        String s = null;
        if (m.find()) {
            s = m.group(1);
        }
        if (null == s) {
            return null;
        }
        return s.trim();

    }

    /**
     * 获取格式化后的sql
     *
     * @param sql
     * @return
     */
    @Deprecated
    public static String getFormattedSQL(String sql) {
        Statement stmt = null;
        try {
            stmt = CCJSqlParserUtil.parse(sql);
        } catch (JSQLParserException e) {
            logger.error(e, "sql parse error: {}", sql);
        }
        if (null == stmt) {
            return sql;
        }
        Select selectStatement = null;
        try {
            selectStatement = (Select) stmt;
        } catch (ClassCastException e) {
            logger.error("not a select statement:{}", sql);
            return sql;
        }
        return selectStatement.toString();
    }

    /**
     * 关键字大写并参数化语句
     *
     * @param sql
     * @return
     */
    public static String getUpCaseParameterizedSQL(String sql) {
        SQLStatement statement;
        try {
            statement = SQLUtils.parseSingleStatement(sql, DbType.mysql, SkipComments);
            SQLObject sqlObject = statement.getChildren().get(0);
            return SQLUtils.formatMySql(sqlObject.toString(), new SQLUtils.FormatOption(OutputParameterized, OutputUCase));
        } catch (Exception e) {
            logger.error("getUpCaseKeywordsSQL error: {}", sql);
        }
        return sql;
    }

    /**
     * 关键字大写
     *
     * @param sql
     * @return
     */
    public static String getUpCaseKeywordsSQL(String sql) {
        SQLStatement statement;
        try {
            statement = SQLUtils.parseSingleStatement(sql, DbType.mysql, SkipComments);
            SQLObject sqlObject = statement.getChildren().get(0);
            return SQLUtils.formatMySql(sqlObject.toString(), new SQLUtils.FormatOption(OutputUCase));
        } catch (Exception e) {
            logger.error("getUpCaseKeywordsSQL error: {}", sql);
        }
        return sql;
    }

    public static boolean isSelectQuery(String sql) {
        return SQLType.SELECT == SQLParserUtils.getSQLType(sql, DbType.mysql);
    }
}
