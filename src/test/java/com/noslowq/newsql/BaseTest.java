package com.noslowq.newsql;

import com.noslowq.newsql.config.DBConfig;
import com.noslowq.newsql.config.MyBatisConfig;
import com.noslowq.newsql.config.MyBatisScannerConfig;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BaseTest.ComponentScanConfig.class, DBConfig.class, MyBatisScannerConfig.class, MyBatisConfig.class})
@TestPropertySource("/application.properties")
public abstract class BaseTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Configuration
    @ComponentScan(basePackages = {"com.noslowq.newsql"}, excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class))
    static class ComponentScanConfig {

    }
}
