package com.potato112.springdemo.jms;

import com.potato112.springdemo.conf.AppConfig;
import com.potato112.springdemo.crud.jdbc.BulkActionExecutor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@EnableTransactionManagement
@Rollback
@SpringBootTest
public class BulkActionExecutorTest {

    @Autowired
    private BulkActionExecutor bulkActionExecutor;

    @Test
    public void shouldRunInvestmentChangeStatusBulkAction() {

        bulkActionExecutor.executeBulkAction();
    }

}