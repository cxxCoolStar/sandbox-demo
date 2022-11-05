package com.taobao.demo.util;

import com.taobao.demo.model.HttpDO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtils {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://192.168.1.129:3306/sandbox?useUnicode=true&characterEncoding=UTF-8&useSSL=false&autoReconnect=true&failOverReadOnly=false&maxReconnects=15000&allowMultiQueries=true";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "shulie@2020";


    public static boolean insertRecord(HttpDO httpDO) {
        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Inserting records into the table...");
            stmt = conn.createStatement();
            String sql = "INSERT INTO t_http " +
                    "(`from`,`status`,method,cost,uri,parameters,user_agent,cause)" +
                    "VALUES ('"
                    +    httpDO.getFrom() + "',  " +
                    ""+ httpDO.getStatus() + ", " +
                    "'"+ httpDO.getMethod() + "', " +
                    ""+ httpDO.getCost() +", " +
                    "'"+ httpDO.getUri() +"', " +
                    "'"+ httpDO.getParameters() +"', " +
                    "'"+ httpDO.getUserAgent() +"', " +
                    "'"+ httpDO.getCause() + "' " +
                    ")";

            stmt.executeUpdate(sql);
            System.out.println("Inserted records into the table...");

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return true;
    }//end main


    public static void main(String[] args) {


        HttpDO httpDO = new HttpDO();
        httpDO.setCause("cause.getMessage()");
        httpDO.setFrom("ha.from");
        httpDO.setCost(100l);
        httpDO.setStatus(101);
        httpDO.setUserAgent("ha.userAgent");
        httpDO.setParameters("formatParameterMap(ha.parameterMap)");
        httpDO.setMethod("ha.method");
        httpDO.setUri("ha.uri");

        insertRecord(httpDO);
    }
}
