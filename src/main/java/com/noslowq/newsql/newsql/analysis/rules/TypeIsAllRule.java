package com.noslowq.newsql.newsql.analysis.rules;


import com.noslowq.newsql.constants.ResultLevelsEnum;
import com.noslowq.newsql.dto.ExplainResult;
import com.noslowq.newsql.newsql.analysis.IResultRule;

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
