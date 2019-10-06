package com.mbappe.newsql.newsql.analysis.rules;


import com.mbappe.newsql.constants.ResultLevelsEnum;
import com.mbappe.newsql.dto.ExplainResult;
import com.mbappe.newsql.newsql.analysis.IResultRule;

/**
 * @author: gannicus at 2018/7/31
 */
public class TypeIsAllRule implements IResultRule {

    private static final ResultLevelsEnum TYPE_IS_ALL = ResultLevelsEnum.TYPE_IS_ALL;
    private static final String ALL = "ALL";

    @Override
    public ResultLevelsEnum match(ExplainResult explainResult) {
        if (ALL.equals(explainResult.getType())) {
            return TYPE_IS_ALL;
        }
        return null;
    }
}
