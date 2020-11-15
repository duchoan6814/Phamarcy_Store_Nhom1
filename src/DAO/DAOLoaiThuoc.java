package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.LoaiThuoc;

public class DAOLoaiThuoc extends DAO {
	public LoaiThuoc getLoaiThuocById(String id) {
		String sql = "Select * from LoaiThuoc where LoaiThuocId = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				LoaiThuoc loaiThuoc = new LoaiThuoc();
				loaiThuoc.setId(rs.getString("LoaiThuocId"));
				loaiThuoc.setMoTa(rs.getString("MoTa"));
				loaiThuoc.setTenLoai(rs.getString("TenLoaiThuoc"));
				return loaiThuoc;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {
		DAOLoaiThuoc daoLoaiThuoc = new DAOLoaiThuoc();
		System.out.println(daoLoaiThuoc.getLoaiThuocById("1"));
	}
}
