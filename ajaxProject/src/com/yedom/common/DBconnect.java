package com.yedom.common;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnect {
	static Connection conn;
	public static Connection getCon() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String password = "hr";
		

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("DB���Ἲ��");
		} catch (Exception e) {
			System.out.println("DB�������");
		}
		return conn;
	}
}
