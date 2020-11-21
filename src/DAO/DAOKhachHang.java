package DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ListResourceBundle;
import java.util.Set;

import entity.HoaDon;
import entity.KhachHang;
import entity.LoaiKhachHang;

public class DAOKhachHang extends DAO {
	
	public boolean xoaKhachHangById(String id) {
		String sql = "Delete From KhachHang where KhachHangId = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean updateHoaDonWhenXoaKhachHang(String khachHangId) {
		String sql = "update HoaDon set KhachHangId = NULL where KhachHangId = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, khachHangId);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public List<KhachHang> filterKhachhang(String khachHangId, String ho, String ten, String ngaySinh, String gioiTinh, String soDienThoai, String loaiKhachHang, String diaChi ){
		String _gioiTinh;
		if (gioiTinh == "Nam") {
			_gioiTinh = "1";
		}else if(gioiTinh == "Nữ") {
			_gioiTinh = "0";
		}else {
			_gioiTinh = "0,1";
		}
		
		String _loaiKhachHang = "";
		if (!(loaiKhachHang == "Tất cả")) {
			_loaiKhachHang = loaiKhachHang;
		}
		String sql = "SELECT * from KhachHang where KhachHangId like ? and HoTenDem like ? and Ten like ? and NgaySinh like ? and GioiTinh in ("+_gioiTinh+") and SoDienThoai like ? and LoaiKhachHang like ? and DiaChi like ?";
		List<KhachHang> list = new ArrayList<>();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+khachHangId+"%");
			ps.setNString(2, "%"+ho+"%");
			ps.setNString(3, "%"+ten+"%");
			ps.setString(4, "%"+ngaySinh+"%");
			ps.setString(5, "%"+soDienThoai+"%");
			ps.setNString(7, "%"+diaChi+"%");
			ps.setString(6, "%"+_loaiKhachHang+"%");
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				KhachHang khachHang = new KhachHang();
				khachHang.setId(rs.getString("KhachHangId"));
				khachHang.setHoTenDem(rs.getString("HoTenDem"));
				khachHang.setTen(rs.getString("Ten"));
				khachHang.setGioiTinh(rs.getBoolean("GioiTinh"));
				khachHang.setNgaySinh(rs.getDate("NgaySinh"));
				khachHang.setSoDienThoai(rs.getString("SoDienThoai"));
				khachHang.setDiaChi(rs.getString("DiaChi"));
				khachHang.setDienTichLuy(rs.getDouble("DiemTichLuy"));
				khachHang.setLoaiKhachHang(LoaiKhachHang.get(rs.getString("LoaiKhachHang")));
				list.add(khachHang);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return list;
		}
	}
	
	public List<String> getListLoaiKhachHang(){
		String sql = "select LoaiKhachHang from KhachHang";
		List<String> list = new ArrayList<>();
		list.add("Tất cả");
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				list.add(rs.getString("LoaiKhachHang"));
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return list;
		}
	}
	
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
