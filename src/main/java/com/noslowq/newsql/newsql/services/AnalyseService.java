package com.noslowq.newsql.newsql.services;

import com.noslowq.newsql.constants.NoticeLevelEnum;
import com.noslowq.newsql.constants.ResultLevelsEnum;
import com.noslowq.newsql.dto.ExplainResult;
import com.noslowq.newsql.newsql.analysis.IResultRule;
import com.noslowq.newsql.newsql.analysis.rules.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static io.netty.util.internal.StringUtil.EMPTY_STRING;

/*  Created by heyu on 2019-10-04.
    usage:
*/
@Service
public class AnalyseService {

    private static final List<IResultRule> I_RESULT_RULES = new ArrayList<>();

    static {
        I_RESULT_RULES.add(new NoWhereClauseRule());
        I_RESULT_RULES.add(new ExtraUsageRule());
        I_RESULT_RULES.add(new LimitTooManyRule());
        I_RESULT_RULES.add(new RowsTooManyRule());
        I_RESULT_RULES.add(new TypeIsAllRule());
        I_RESULT_RULES.add(new TypeIsRangeRule());
    }

    /**
     *  分析统一入口
     * @param explainResult explain结果
     * @return  分析结果
     */
    public AnalysisResult analyse(ExplainResult explainResult) {
        List<ResultLevelsEnum> resultLevelsEnums = new ArrayList<>();
        for (IResultRule resultRule : I_RESULT_RULES) {
            ResultLevelsEnum resultLevelsEnum;
            resultLevelsEnum = resultRule.match(explainResult);
            if (null != resultLevelsEnum) {
                resultLevelsEnums.add(resultLevelsEnum);
            }
        }
        AnalysisResult analysisResult = new AnalysisResult();
        analysisResult.slevels = resultLevelsEnums2Str(resultLevelsEnums);
        analysisResult.finalEnum = getFinalLevel(resultLevelsEnums);
        return analysisResult;
    }

    private String resultLevelsEnums2Str(List<ResultLevelsEnum> resultLevelsEnums) {
        List<String> slevels = new ArrayList<>();
        resultLevelsEnums.forEach(resultLevelsEnum -> {
            if (null != resultLevelsEnum) {
                slevels.add(resultLevelsEnum.getCode().toString());
            }

        });
        if (resultLevelsEnums.size() == 0 ) {
            return EMPTY_STRING;
        }
        return String.join(",", slevels);
    }


    private NoticeLevelEnum getFinalLevel(List<ResultLevelsEnum> resultLevelsEnums) {
        NoticeLevelEnum noticeLevelEnum = NoticeLevelEnum.GOOD;
        for (ResultLevelsEnum resultLevelsEnum : resultLevelsEnums) {
            if (resultLevelsEnum.getNoticeLevelEnum().getCode() > noticeLevelEnum.getCode()) {
                noticeLevelEnum = resultLevelsEnum.getNoticeLevelEnum();
            }
        }
        return noticeLevelEnum;
    }

    class AnalysisResult {
        String slevels;
        NoticeLevelEnum finalEnum;
    }
}
