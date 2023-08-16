package com.cruds.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestRavinath {
    private static Connection con;

    private static void setUpConnection()throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://ls-615677c9f7d3d152c2fe8eadd712967406362234.ct2wseu444kk.ap-south-1.rds.amazonaws.com:3306/dilruwan_pos", "dbmasteruser", "l7j~<6hYudO,fhb1v&;f.VbR0Axq;NSU");
    }

    public static synchronized Connection getConnection()throws Exception {
        if (con == null) {
            setUpConnection();
        }
        return con;
    }

    public static void main(String[] args) {
        try{
            getConnection();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
