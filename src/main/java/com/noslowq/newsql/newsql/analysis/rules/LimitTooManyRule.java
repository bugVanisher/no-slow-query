package com.noslowq.newsql.newsql.analysis.rules;

import com.noslowq.newsql.constants.ResultLevelsEnum;
import com.noslowq.newsql.dto.ExplainResult;
import com.noslowq.newsql.newsql.analysis.IResultRule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: gannicus at 2018/7/31
 */
public class LimitTooManyRule implements IResultRule {

    private static final ResultLevelsEnum LIMIT_TOO_MANY = ResultLevelsEnum.LARGE_SPAN_LIMIT;
    private final static Pattern LIMIT_PATTERN = Pattern.compile("limit(.*)$");
    private final static int MAX_NUM = 1000;

    @Override
    public ResultLevelsEnum match(ExplainResult explainResult) {
        String str = explainResult.getNew_sql();
        Matcher m = LIMIT_PATTERN.matcher(str.toLowerCase());
        if (m.find()) {
            String s = m.group(1);
            String[] nums = s.split("[,|offset]");
            Integer size;
            try {
                if (nums.length == 1) {
                    if (Integer.valueOf(nums[0].trim()) > MAX_NUM) {
                        return LIMIT_TOO_MANY;
                    }
                    return null;
                }
                size = Integer.valueOf(nums[1].trim());
                if (MAX_NUM < size) {
                    return LIMIT_TOO_MANY;
                }
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
}
