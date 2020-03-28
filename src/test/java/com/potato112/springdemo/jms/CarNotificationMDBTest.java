package com.potato112.springdemo.jms;

import com.potato112.springdemo.conf.AppConfig;
import com.potato112.springdemo.jms.bulkaction.BulkActionExecutor;
import com.potato112.springdemo.crud.jdbc.services.RentalCarDAO;
import com.potato112.springdemo.crud.jdbc.datasource.DataSourceBuilder;
import com.potato112.springdemo.crud.jdbc.model.RentalCarVO;
import com.potato112.springdemo.utils.DBQueryMapUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.jms.ObjectMessage;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@EnableTransactionManagement
@Rollback
@SpringBootTest
public class CarNotificationMDBTest {


    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private RentalCarDAO rentalCarDAO;

    @Test
    public void shouldReceiveMessage() throws SQLException {

        DBQueryMapUtil.readQueries();
        DataSource dataSource = DataSourceBuilder.buildDataSource();
        Connection connection = dataSource.getConnection();

        List<String> carIds = Arrays.asList("Ford", "Tesla");
        List<RentalCarVO> cars = rentalCarDAO.getRentalCarList(connection, carIds);

        RentalCarVO rentalCarVO = cars.get(0);

        // Send a message with a POJO - the template reuse the message converter
        System.out.println("Sending an RentalCarVO to CarNotificationMDB  message.");

        //carNotificationProcessorQueue
        jmsTemplate.send("carNotificationProcessorQueue", session -> {
            ObjectMessage objectMessage = session.createObjectMessage();
            objectMessage.setObject(rentalCarVO);
            objectMessage.setStringProperty("userName", "TestUser1");
            return objectMessage;
        });

        // Dedicated Receiver will print message with content
    }

    @Autowired
    private BulkActionExecutor bulkActionExecutor;

    @Test
    public void shouldRunInvestmentChangeStatusBulkAction() {

        bulkActionExecutor.executeBulkAction();
    }

}