/**
 * 
 */
package com.hm.dao;

/**
 * @author HM
 * @version 1.0
 */
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * 
 * @author HM
 */
public class DBHelper {
	Connection con = null;

	public DBHelper() {
		super();

	}

	public Connection getConnection() throws Exception {
		try {
			InitialContext ic = new InitialContext();
			
			DataSource ds = (DataSource) ic.lookup("java:/MySqlDS");
			
			 con = ds.getConnection();			
			
		} catch (SQLException exception) {
			throw exception;
		} catch (Exception exception) {
			throw exception;

		}
		
		return con;
	}
}
