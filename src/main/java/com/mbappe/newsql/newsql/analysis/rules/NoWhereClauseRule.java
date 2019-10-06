package com.mbappe.newsql.newsql.analysis.rules;

import com.alibaba.fastsql.DbType;
import com.alibaba.fastsql.sql.SQLUtils;
import com.alibaba.fastsql.sql.ast.SQLExpr;
import com.alibaba.fastsql.sql.ast.statement.SQLSelectStatement;
import com.alibaba.fastsql.sql.dialect.mysql.ast.statement.MySqlSelectQueryBlock;
import com.mbappe.newsql.constants.ResultLevelsEnum;
import com.mbappe.newsql.dto.ExplainResult;
import com.mbappe.newsql.newsql.analysis.IResultRule;

/**
 * @author: gannicus at 2018/7/31
 */
public class NoWhereClauseRule implements IResultRule {

    private static final ResultLevelsEnum NO_WHERE_CLAUSE = ResultLevelsEnum.NO_WHERE_CLAUSE;

    @Override
    public ResultLevelsEnum match(ExplainResult explainResult) {
        try {
            SQLSelectStatement stmt = (SQLSelectStatement)
                    SQLUtils.parseStatements(explainResult.getNew_sql(), DbType.mysql)
                            .get(0);
            MySqlSelectQueryBlock queryBlock = (MySqlSelectQueryBlock) stmt.getSelect().getQuery();
            SQLExpr group = queryBlock.getWhere();
            if (null == group) {
                return NO_WHERE_CLAUSE;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
