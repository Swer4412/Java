package it.jac.corsojava.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDao {

	public Connection openConnection() throws SQLException {

		String url = "jdbc:mysql://localhost:3306/istat";
		return DriverManager.getConnection(url, "root", "mysql");
	}

}
