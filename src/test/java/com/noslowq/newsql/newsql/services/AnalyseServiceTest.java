package com.noslowq.newsql.newsql.services;

import com.noslowq.newsql.constants.NoticeLevelEnum;
import com.noslowq.newsql.dto.ExplainResult;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;


/*  Created by gannicus on 2020/4/16. 
    usage:     
*/

public class AnalyseServiceTest {

    private AnalyseService analyseService = new AnalyseService();

    @Test
    public void analyseNotGood() {
        ExplainResult explainResult = new ExplainResult();

        explainResult.setId(1L);
        explainResult.setSelect_type("simple");
        explainResult.setTable("table");
        explainResult.setPartitions("");
        explainResult.setType("all");
        explainResult.setPossible_keys("idx_slevel_ctime");
        explainResult.setKey("");
        explainResult.setKey_len("");
        explainResult.setRows("100000");
        explainResult.setFiltered("");
        explainResult.setExtra("Using where");
        explainResult.setNew_sql("SELECT * FROM new_sql_explain where eid = 1001;");

        AnalyseService.AnalysisResult analysisResult = analyseService.analyse(explainResult);
        assertEquals("6", analysisResult.slevels);
        assertEquals(NoticeLevelEnum.WARNING, analysisResult.finalEnum);


    }

    @Test
    public void analyseResGood() {
        ExplainResult explainResult = new ExplainResult();

        explainResult.setId(1L);
        explainResult.setSelect_type("simple");
        explainResult.setTable("table");
        explainResult.setPartitions("");
        explainResult.setType("const");
        explainResult.setKey("idx_slevel_ctime");
        explainResult.setPossible_keys("idx_slevel_ctime");
        explainResult.setKey_len("20");
        explainResult.setRows("10");
        explainResult.setFiltered("");
        explainResult.setExtra("Using where");
        explainResult.setNew_sql("SELECT * FROM new_sql_explain where eid = 1001;");

        AnalyseService.AnalysisResult analysisResult = analyseService.analyse(explainResult);
        assertEquals("", analysisResult.slevels);
        assertEquals(NoticeLevelEnum.GOOD, analysisResult.finalEnum);


    }
}