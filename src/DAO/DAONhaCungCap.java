package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.NhaCungCap;

public class DAONhaCungCap extends DAO {
	public NhaCungCap getNhaCungCapByID(String id) {
		String sql = "select * from NhaCungCap where NhaCungCapId = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				NhaCungCap cap = new NhaCungCap();
				cap.setDiaChi(rs.getString("DiaChi"));
				cap.setFax(rs.getString("Fax"));
				cap.setId(rs.getString("NhaCungCapId"));
				cap.setSoDienThoai(rs.getString("SoDienThoai"));
				cap.setTenNhaCungCap(rs.getString("TenNhaCungCap"));
				cap.setTrangChu(rs.getString("TrangChu"));
				return cap;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {
		DAONhaCungCap cap = new DAONhaCungCap();
		System.out.println(cap.getNhaCungCapByID("NCC302"));
	}
}
