package com.huynhthanhnha.restExample.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.common.LogHelper;
import com.huynhthanhnha.restExample.helper.ResourcesHelper;


public class JDBCPostgreSQLConnection {
	
	public static Connection connection() {
		
		ResourcesHelper rh = new ResourcesHelper();
		Properties properties = rh.getPropertiesFileInResources("config.properties");
		
		Connection conn = null;
		
		try {
			
			String url = properties.getProperty("url");
			String username = properties.getProperty("username");
			String password = properties.getProperty("password");
			String sqlDriver = properties.getProperty("sqlDriver");
			
			Class.forName(sqlDriver);
			
			conn = DriverManager.getConnection(url, username, password);
			LogHelper.log(new JDBCPostgreSQLConnection()).info("Connected database successfully");
		} catch (ClassNotFoundException | SQLException e) {
			LogHelper.log(new JDBCPostgreSQLConnection()).severe("Connected database failed");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
}
