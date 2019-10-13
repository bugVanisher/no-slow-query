package com.noslowq.newsql.newsql.analysis;

import com.noslowq.newsql.constants.ResultLevelsEnum;
import com.noslowq.newsql.dto.ExplainResult;

public interface IResultRule {

    ResultLevelsEnum match(ExplainResult explainResult);
}
