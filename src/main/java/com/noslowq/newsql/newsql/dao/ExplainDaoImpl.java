package com.noslowq.newsql.newsql.dao;

/*  Created by heyu on 2019-10-06. 
    usage:     
*/

import com.noslowq.newsql.newsql.persistence.ddl.ExplainDO;
import com.noslowq.newsql.newsql.persistence.ddl.ExplainDOExample;
import com.noslowq.newsql.newsql.persistence.mapper.ExplainDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExplainDaoImpl {

    @Autowired
    private ExplainDOMapper explainDOMapper;

    public boolean insert(ExplainDO explainDO) {
        return explainDOMapper.insert(explainDO) > 0;
    }

    public List<ExplainDO> getExplainsByIds(List<Long> ids) {
        ExplainDOExample explainResultEntityExample = new ExplainDOExample();
        explainResultEntityExample.createCriteria().andIdIn(ids);
        explainResultEntityExample.setOrderByClause("ctime asc");
        return explainDOMapper.selectByExample(explainResultEntityExample);
    }

    public List<ExplainDO> getExplainResultById(Long id) {
        ExplainDOExample explainDOExample = new ExplainDOExample();
        explainDOExample.createCriteria().andIdEqualTo(id);
        return explainDOMapper.selectByExample(explainDOExample);
    }
}
