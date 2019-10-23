package com.noslowq.newsql.utils;

import com.noslowq.newsql.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;


/*  Created by heyu on 2019/10/20. 
    usage:     
*/

public class AlgorithmUtilTest extends BaseTest {

    @Autowired
    private AlgorithmUtil algorithmUtil;

    @Test
    public void AESencrypt() throws Exception {

        String encyData = algorithmUtil.AESencrypt("admin");
        System.out.println(encyData);
        System.out.println("hello world.");

    }

    @Test
    public void AESdecrypt() throws Exception {

        System.out.println(algorithmUtil.AESdecrypt("LhSxuBZxuu2oeai6TQvlWQ=="));
    }
}