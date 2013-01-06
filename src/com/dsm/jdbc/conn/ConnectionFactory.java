package com.dsm.jdbc.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactory {

	// 创建一个自身的实例
	private static ConnectionFactory connectionFactory = new ConnectionFactory();

	// 注册驱动程序
	private ConnectionFactory()// 使用private而不是public
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// 返回一个Connection
	public static Connection getConnection() {
		Connection con = null;
		String url = "jdbc:mysql://127.0.0.1:3306/hrm_db";// 如果是其他的数据库则做相应的更改
		try {
			con = DriverManager.getConnection(url, "root", "wangyue");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	// 关闭Connection
	public static void close(Connection connection) {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 关闭Statement
	public static void close(Statement statement) throws SQLException {
		if (statement != null && !statement.isClosed()) {
			statement.close();
		}
	}

	// 关闭ResultSet
	public static void close(ResultSet resultSet) throws SQLException {
		if (resultSet != null && !resultSet.isClosed()) {
			resultSet.close();
		}
	}

}
