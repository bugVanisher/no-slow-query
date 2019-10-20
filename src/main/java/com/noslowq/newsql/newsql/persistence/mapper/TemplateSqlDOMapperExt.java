package com.noslowq.newsql.newsql.persistence.mapper;

import java.util.List;

public interface TemplateSqlDOMapperExt extends TemplateSqlDOMapper {

    List<String> selectDistinctTableNameByAppName(String appName);

    List<String> selectDistinctLabelByAppName(String appName);

}
