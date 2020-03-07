package com.potato112.springdemo.crud.jdbc;

import com.potato112.springdemo.crud.jdbc.model.RentalCarVO;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class RentalCarDAO extends BaseDAO<RentalCarVO> {

    @Override
    public RentalCarVO resultSetToVO(ResultSet resultSet) throws SQLException {

        RentalCarVO vo = new RentalCarVO();
        vo.setId(resultSet.getString("car_id"));
        vo.setBrand(resultSet.getString("brand"));
        vo.setColor(resultSet.getString("color"));
        vo.setPayloadKg(resultSet.getString("payloadkg"));
        vo.setPricePerHour(resultSet.getString("price_per_hour"));

        return vo;
    }

    public List<RentalCarVO> getRentalCarList(Connection connection, List<String> brandNames) throws SQLException {

        String sql = getSQLQuery("select.rentalCars");
        sql = replaceInParameter(sql, ":brandNames", brandNames);
        return selectList(connection, sql);
    }
}
