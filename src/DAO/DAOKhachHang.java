package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import entity.HoaDon;
import entity.KhachHang;
import entity.LoaiKhachHang;

public class DAOKhachHang extends DAO {
	
	public boolean congDiemTichLuy(HoaDon hoaDon) {
		String sql = "UPDATE KhachHang SET DiemTichLuy = DiemTichLuy + ? WHERE KhachHangId = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, hoaDon.tinhDiemTichLuy());
			ps.setString(2, hoaDon.getKhachHang().getId());
			
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean truDiemTichLuy(HoaDon hoaDon) {
		String sql = "UPDATE KhachHang SET DiemTichLuy = DiemTichLuy - ? WHERE KhachHangId = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, hoaDon.getDiemSuDung());
			ps.setString(2, hoaDon.getKhachHang().getId());
			
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public KhachHang getKhachHangBySoDienThoai(String sdt) {
		KhachHang khachHang = new KhachHang();
		String sql = "SELECT * from KhachHang where SoDienThoai = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, sdt);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				khachHang.setId(rs.getString("KhachHangId"));
				khachHang.setHoTenDem(rs.getString("HoTenDem"));
				khachHang.setTen(rs.getString("Ten"));
				khachHang.setGioiTinh(rs.getBoolean("GioiTinh"));
				khachHang.setNgaySinh(rs.getDate("NgaySinh"));
				khachHang.setSoDienThoai(rs.getString("SoDienThoai"));
				khachHang.setDiaChi(rs.getString("DiaChi"));
				khachHang.setDienTichLuy(rs.getDouble("DiemTichLuy"));
				khachHang.setLoaiKhachHang(LoaiKhachHang.get(rs.getString("LoaiKhachHang")));
				return khachHang;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public Set<String> getAllSoDienThoai() throws SQLException{
		String sql = "SELECT SoDienThoai from KhachHang";
		Set<String> list = new HashSet<>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				list.add(rs.getString("SoDienThoai"));
			}
			
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	
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
