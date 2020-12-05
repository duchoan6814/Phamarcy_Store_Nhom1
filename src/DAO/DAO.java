package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
	protected Connection conn;
	private String url = "jdbc:sqlserver://192.168.1.79:1433;databasename=PhamarcyHMTV";
	private String username = "sa";
	private String password = "Tokelovip123";
	public DAO() {
		// TODO Auto-generated constructor stub
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			try {
				conn = (Connection) DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("ket noi that bai 2");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ket noi that bai!");
		}
	}
}
