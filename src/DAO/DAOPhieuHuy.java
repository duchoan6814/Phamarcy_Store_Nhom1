package DAO;

import java.sql.Connection;

public class DAOPhieuHuy {
	private Connection conn;
	
	public DAOPhieuHuy() {
		// TODO Auto-generated constructor stub
		conn = DAO.getInstance().getConn();
	}
	
}
