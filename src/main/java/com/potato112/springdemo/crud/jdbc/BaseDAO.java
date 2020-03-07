package com.potato112.springdemo.crud.jdbc;

import com.potato112.springdemo.crud.jdbc.model.BaseVO;
import com.potato112.springdemo.utils.DBQueryMapUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDAO<T extends BaseVO> {


    public abstract T resultSetToVO(ResultSet resultSet) throws SQLException;


    public List<T> selectList(Connection connection, String query) throws SQLException {

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                return convertResultSetVoToVoList(resultSet);
            }
        }
    }

    private List<T> convertResultSetVoToVoList(ResultSet resultSet) throws SQLException {

        List<T> voList = new ArrayList<>();

        while (resultSet.next()) {
            T vo = resultSetToVO(resultSet);
            voList.add(vo);
        }
        return voList;
    }

    protected String getSQLQuery(String queryId) throws SQLException {

        String sql = DBQueryMapUtil.getSqlQueryVo(queryId);

        if (null == sql) {
            throw new SQLException("Resource not found. Missing query in xml file.");
        }
        return sql;
    }

    public String replaceInParameter(String sql, String paramName, List<String> paramList) {

        String inParams = String.join("', '", paramList);
        StringBuilder sb = new StringBuilder();
        inParams = sb.append("'").append(inParams).append("'").toString();
        return sql.replace(paramName, inParams);
    }


}
