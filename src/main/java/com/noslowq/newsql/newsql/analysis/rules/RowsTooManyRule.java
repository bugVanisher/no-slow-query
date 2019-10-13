package com.noslowq.newsql.newsql.analysis.rules;

import com.noslowq.newsql.constants.ResultLevelsEnum;
import com.noslowq.newsql.dto.ExplainResult;
import com.noslowq.newsql.newsql.analysis.IResultRule;
import jodd.util.StringUtil;

/**
 * @author: gannicus at 2018/7/31
 */
public class RowsTooManyRule implements IResultRule {
    private static final ResultLevelsEnum ROWS_TOO_MANY = ResultLevelsEnum.ROWS_IS_TOO_MANY;
    private static final Integer MAX_NUM = 100;

    @Override
    public ResultLevelsEnum match(ExplainResult explainResult) {
        if (StringUtil.isEmpty(explainResult.getRows())) {
            return null;
        }
        Integer num = Integer.valueOf(explainResult.getRows());
        if (MAX_NUM <= num) {
            return ROWS_TOO_MANY;
        }
        return null;
    }
}
