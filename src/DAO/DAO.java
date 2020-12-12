package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
	protected Connection conn;
//	private String url = "jdbc:sqlserver://192.168.1.97:1433;databasename=PhamarcyHMTV";
//	private String username = "sa";
//	private String password = "Tokelovip123";
	private static DAO instance = null;
	private static String ip;
	private static String userName;
	private static String password;
	private boolean resultConnect = false;
	
	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public DAO(String ip2, String userName2, String password2) {
		// TODO Auto-generated constructor stub
		
		this.ip = ip2;
		this.userName = userName2;
		this.password = password2;
		String _url = "jdbc:sqlserver://"+this.ip+":1433;databasename=PhamarcyHMTV";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			try {
				conn = (Connection) DriverManager.getConnection(_url, this.userName, this.password);
				this.resultConnect = true;
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
	
	public static DAO getInstance() {
		if (instance == null) {
			instance = new DAO(ip, userName, password);
		}
		return instance;
	}

	public boolean getResult() {
		// TODO Auto-generated method stub
		 return this.resultConnect;
	}

}
