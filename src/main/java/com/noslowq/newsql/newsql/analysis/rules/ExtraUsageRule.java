package com.noslowq.newsql.newsql.analysis.rules;


import com.noslowq.newsql.constants.ResultLevelsEnum;
import com.noslowq.newsql.dto.ExplainResult;
import com.noslowq.newsql.newsql.analysis.IResultRule;
import jodd.util.StringUtil;

/**
 * @author: gannicus at 2018/7/31
 */
public class ExtraUsageRule implements IResultRule {

    private static final ResultLevelsEnum USING_FILESORT_OR_TEMPORARY = ResultLevelsEnum.EXTRA_IS_BAD;

    @Override
    public ResultLevelsEnum match(ExplainResult explainResult) {
        if (StringUtil.isEmpty(explainResult.getExtra())) {
            return null;
        }
        if (explainResult.getExtra().contains("filesort") || explainResult.getExtra().contains("temporary")) {
            return USING_FILESORT_OR_TEMPORARY;
        }
        return null;
    }
}
