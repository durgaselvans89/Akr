/**
 * 
 */
package com.hm.dao;

/**
 * @author
 * @version 1.0
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * @author
 */
public class DBHelperHis {	
	Connection con1 = null;
	
	public Connection getConnectionHistory(String dbName) throws Exception {
		try {
			
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch (InstantiationException e1) {			
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {				
				e1.printStackTrace();
			}
			try {				
				//con1 = DriverManager.getConnection("jdbc:mysql://192.168.1.222:3306/"+dbName, "root", "superakr257");
				//con1 = DriverManager.getConnection("jdbc:mysql://192.168.1.222:3306/"+dbName, "root", "superakr257");
				con1 = DriverManager.getConnection("jdbc:mysql://182.72.199.30:3306/"+dbName, "root", "DzhUG1500geTusLvaX");
				System.out.println("con from history-->"+dbName);
			} catch (SQLException e) {			
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return con1;
	}
	
}
