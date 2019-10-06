package com.mbappe.newsql.utils;

/*  Created by heyu on 2019-10-04. 
    usage:     
*/

import org.springframework.stereotype.Component;

@Component
public class SimpleIdGenerator implements IdGenerator {

    @Override
    public Long genId() {
        return System.currentTimeMillis();
    }

}
