package com.potato112.springdemo.utils;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class DBQueryMapUtilTest {

    void init(){}

    @Test
    void getSqlQueryVo() throws SQLException {

        DBQueryMapUtil.readQueries();
        String query = DBQueryMapUtil.getSqlQueryVo("select.rentalCars");
        System.out.println(query);
    }
}