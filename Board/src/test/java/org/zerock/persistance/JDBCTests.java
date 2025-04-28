package org.zerock.persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class JDBCTests {
	
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String uid = "hr";
	private String pass = "hr";
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testConnection() {
		
		try {
			Connection conn = DriverManager.getConnection(url, uid, pass);
			log.info("conn : " + conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
