package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOLocation {
	private Connection conn;
	public DAOLocation() {
		// TODO Auto-generated constructor stub
		conn = DAO.getInstance().getConn();
	}
	
	public List<String> getListThanhPho() {
		List<String> list = new ArrayList<>();
		list.add("");
		String sql = "SELECT * from [dbo].[province]";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				list.add(rs.getNString("_name"));
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return list;
		}
	}
	
	public List<String> getListQuanHuyenByTinh(String tinh) {
		List<String> list = new ArrayList<>();
		list.add("");
		String sql = "select d._name from district d join province p on d._province_id = p.id where p._name = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setNString(1, tinh);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				list.add(rs.getNString("_name"));
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return list;
		}
	}
	
	public List<String> getListPhuongXaByHuyenVaTinh(String tinh, String huyen) {
		List<String> list = new ArrayList<>();
		list.add("");
		String sql = "SELECT w._name from ward w join district d on w._district_id = d.id join province p on w._province_id = p.id where d._name = ? and p._name = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setNString(1, huyen);
			ps.setNString(2, tinh);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				list.add(rs.getNString("_name"));
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return list;
		}
	}
	
	
	
}
