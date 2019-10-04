package com.mbappe.newsql.newsql.dao;

import com.mbappe.newsql.newsql.persistence.mapper.DbInfoDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author gannicus at 2019/2/20
 */
@Component
public class DbInfoDAOImpl {

    @Autowired
    private DbInfoDOMapper dbInfoMapper;



}
