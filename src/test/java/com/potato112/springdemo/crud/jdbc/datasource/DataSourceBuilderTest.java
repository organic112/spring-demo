package com.potato112.springdemo.crud.jdbc.datasource;

import org.junit.Test;

import javax.sql.DataSource;


class DataSourceBuilderTest {


    @Test
    public void shouldBuildDataSource() {

        DataSource basicDataSource = DataSourceBuilder.buildDataSource();


    }

}