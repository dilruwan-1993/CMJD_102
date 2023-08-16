package com.cruds.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManager {
	
	static
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static Connection getConnection() {
		
		Connection conn = null;
		try {
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookdb", "root", "root");
                        conn = DriverManager.getConnection("jdbc:mysql://ls-615677c9f7d3d152c2fe8eadd712967406362234.ct2wseu444kk.ap-south-1.rds.amazonaws.com:3306/dilruwan_pos", "dbmasteruser", "l7j~<6hYudO,fhb1v&;f.VbR0Axq;NSU");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
		
	}

}
