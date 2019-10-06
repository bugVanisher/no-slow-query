package com.mbappe.newsql.newsql.dao;

import com.mbappe.newsql.newsql.persistence.ddl.TemplateSqlDO;
import com.mbappe.newsql.newsql.persistence.ddl.TemplateSqlDOExample;
import com.mbappe.newsql.newsql.persistence.mapper.TemplateSqlDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/*  Created by heyu on 2019-10-04.
    usage:
*/

@Component
public class TemplateSqlDaoImpl {

    @Autowired
    private TemplateSqlDOMapper templateSqlMapper;

    public TemplateSqlDO getTemplateSqlByAppName(String appName, String sqlMd5) {
        TemplateSqlDOExample example = new TemplateSqlDOExample();
        example.createCriteria().andAppNameEqualTo(appName).andTemplateSqlMd5EqualTo(sqlMd5);
        List<TemplateSqlDO> templateSqlDOList = templateSqlMapper.selectByExample(example);
        if (null != templateSqlDOList && templateSqlDOList.size() > 0) {
            return templateSqlDOList.get(0);
        }
        return null;
    }

    public boolean insert(TemplateSqlDO templateSqlDO) {
        return templateSqlMapper.insert(templateSqlDO) > 0;
    }

    public boolean updateById(Long id, TemplateSqlDO templateSqlDO) {
        TemplateSqlDOExample templateSqlDOExample = new TemplateSqlDOExample();
        templateSqlDOExample.createCriteria().andIdEqualTo(id);
        return templateSqlMapper.updateByExampleSelective(templateSqlDO, templateSqlDOExample) > 0;
    }

    public boolean updateStatus(Long id, Short toStatus, Short fromStatus) {
        TemplateSqlDOExample templateSqlDOExample = new TemplateSqlDOExample();
        templateSqlDOExample.createCriteria().andIdEqualTo(id).andHandleStatusEqualTo(fromStatus);
        TemplateSqlDO templateSqlDO = new TemplateSqlDO();
        templateSqlDO.setHandleStatus(toStatus);
        return templateSqlMapper.updateByExampleSelective(templateSqlDO, templateSqlDOExample) > 0;
    }
}
