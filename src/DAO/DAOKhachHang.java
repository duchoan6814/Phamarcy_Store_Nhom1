package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.KhachHang;
import entity.LoaiKhachHang;

public class DAOKhachHang extends DAO {
	
	public KhachHang getAllKhachHang() {
		List<KhachHang> list = new ArrayList<KhachHang>();
		String sql = "SELECT * from KhachHang";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				KhachHang khachHang = new KhachHang();
				khachHang.setId(rs.getString("KhachHangId"));
				khachHang.setHoTenDem(rs.getString("HoTenDem"));
				khachHang.setTen(rs.getString("Ten"));
				khachHang.setNgaySinh(rs.getDate("NgaySinh"));
				khachHang.setDiaChi(rs.getString("DiaChi"));
				khachHang.setDienTichLuy(rs.getDouble("DiemTichLuy"));
				khachHang.setGioiTinh(rs.getBoolean("GioiTinh"));
				khachHang.setLoaiKhachHang(LoaiKhachHang.get(rs.getString("LoaiKhachHang")));
				khachHang.setSoDienThoai(rs.getString("SoDienThoai"));
				list.add(khachHang);
			}
			return (KhachHang) list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public List<String> getAllMaKH(){
		List<String> list = new ArrayList<String>();
		String sql = "SELECT KhachHangId from KhachHang";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				list.add(rs.getString("KhachHangId"));
				
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
