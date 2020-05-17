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
        assertEquals("LhSxuBZxuu2oeai6TQvlWQ==", encyData);
    }

    @Test
    public void AESdecrypt() throws Exception {

        assertEquals("admin", algorithmUtil.AESdecrypt("LhSxuBZxuu2oeai6TQvlWQ=="));
    }
}