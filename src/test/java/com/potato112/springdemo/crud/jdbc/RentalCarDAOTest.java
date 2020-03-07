package com.potato112.springdemo.crud.jdbc;

import com.potato112.springdemo.conf.AppConfig;
import com.potato112.springdemo.crud.jdbc.datasource.DataSourceBuilder;
import com.potato112.springdemo.crud.jdbc.model.RentalCarVO;
import com.potato112.springdemo.utils.DBQueryMapUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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
public class RentalCarDAOTest {

    @Autowired
    private RentalCarDAO rentalCarDAO;

    @Before
    public void init() throws SQLException{
        DBQueryMapUtil.readQueries();
    }


    @Test
    public void shouldReturnResult() throws SQLException {

        DBQueryMapUtil.readQueries();
        DataSource dataSource = DataSourceBuilder.buildDataSource();
        Connection connection = dataSource.getConnection();

        List<String> carIds = Arrays.asList("Ford", "Tesla");
        List<RentalCarVO> cars = rentalCarDAO.getRentalCarList(connection, carIds);

        System.out.println("cars size:" + cars.size());

    }


}