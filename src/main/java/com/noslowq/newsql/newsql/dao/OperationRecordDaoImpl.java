package com.noslowq.newsql.newsql.dao;

/*  Created by gannicus on 2019/11/9. 
    usage:     
*/

import com.noslowq.newsql.newsql.persistence.ddl.OperationRecord;
import com.noslowq.newsql.newsql.persistence.ddl.OperationRecordExample;
import com.noslowq.newsql.newsql.persistence.mapper.OperationRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OperationRecordDaoImpl {

    @Autowired
    private OperationRecordMapper operationRecordMapper;

    public boolean insert(OperationRecord operationRecord) {
        return operationRecordMapper.insertSelective(operationRecord) > 0;
    }

    public List<OperationRecord> get(Long uid) {
        OperationRecordExample operationRecordExample = new OperationRecordExample();
        operationRecordExample.createCriteria().andUniqIdEqualTo(uid);
        return operationRecordMapper.selectByExample(operationRecordExample);
    }
}
