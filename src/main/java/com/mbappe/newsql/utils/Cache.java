package com.mbappe.newsql.utils;

/*  Created by heyu on 2019-10-04. 
    usage:     
*/

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Cache {

    // todo 实现缓存

    public <T> List<T> getList(String key, Class<T> tClass, String prefix) {
        return null;
    }

    public <T> boolean set(String key, List<T> list, String prefix) {
        return true;
    }

    public <T> boolean delete(String key, String prefix) {
        return true;
    }





}
