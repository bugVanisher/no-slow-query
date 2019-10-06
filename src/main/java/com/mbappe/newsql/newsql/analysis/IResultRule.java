package com.mbappe.newsql.newsql.analysis;

import com.mbappe.newsql.constants.ResultLevelsEnum;
import com.mbappe.newsql.dto.ExplainResult;

public interface IResultRule {

    ResultLevelsEnum match(ExplainResult explainResult);
}
