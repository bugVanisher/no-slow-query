package com.noslowq.newsql.utils;

/*  Created by heyu on 2019-10-04. 
    usage:     
*/

import org.springframework.stereotype.Component;

@Component
public class SimpleIdGenerator implements IdGenerator {

    @Override
    public Long genId() {
        // todo 实现分布式genID
        return System.currentTimeMillis();
    }

}
