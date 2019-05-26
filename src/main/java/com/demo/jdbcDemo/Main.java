/**
 * Copyright (C), 2015-2019
 * FileName: Main
 * Author:   huhu
 * Date:     2019/5/26 21:23
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.demo.jdbcDemo;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 〈jdbcDemo〉<br>
 * @author huhu
 * @create 2019/5/26
 * @since 1.0.0
 */
public class Main {

    private final String url="jdbc:mysql://127.0.0.1:3306/%*%#";
    private final String user="root";
    private final String pwd="123456";
    public static void main(String[] args) {
        try {
            System.out.println(new Main().getData());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection connection() throws SQLException {
        Connection con = null;
        con = (Connection) DriverManager.getConnection(url, user, pwd);
        return con;
    }

    private List<Map<String,Object>> getData() throws SQLException {
        Connection con=connection();
        PreparedStatement preparedStatement=(PreparedStatement) con.prepareStatement("SELECT id,role_id,dept_id FROM sys_role");
        ResultSet set= preparedStatement.executeQuery();
        int columns= set.getMetaData().getColumnCount();
        List<Map<String,Object>> list=new LinkedList<Map<String, Object>>();
        ResultSetMetaData resultSetMetaData= set.getMetaData();
        while (set.next()){
            Map<String,Object> map=new HashMap<String, Object>(columns);
            for (int i = 1; i <= columns; i++) {
                map.put(resultSetMetaData.getColumnName(i),set.getInt(i));
            }
            list.add(map);
        }
        return list;
    }

}