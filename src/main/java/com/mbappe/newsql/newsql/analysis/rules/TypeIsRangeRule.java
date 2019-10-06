package com.mbappe.newsql.newsql.analysis.rules;

import com.mbappe.newsql.constants.ResultLevelsEnum;
import com.mbappe.newsql.dto.ExplainResult;
import com.mbappe.newsql.newsql.analysis.IResultRule;

/**
 * @author: gannicus at 2018/7/31
 */
public class TypeIsRangeRule implements IResultRule {

    private static final ResultLevelsEnum TYPE_IS_RANGE = ResultLevelsEnum.TYPE_IS_RANGE;
    private static final String RANGE = "range";

    @Override
    public ResultLevelsEnum match(ExplainResult explainResult) {
        if (RANGE.equals(explainResult.getType())) {
            return TYPE_IS_RANGE;
        }
        return null;
    }
}
