package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.NhaCungCap;
import entity.NhanVienBanThuoc;

public class DAONhaCungCap{
	private Connection conn;
	
	public DAONhaCungCap() {
		// TODO Auto-generated constructor stub
		conn = DAO.getInstance().getConn();
	}
//	
//	public String generateID() {
//		String sql = "";
//	}
	
	public List<String> getListTenNhaCungCap(){
		String sql = "SELECT TenNhaCungCap FROM NhaCungCap";
		List<String> list = new ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				list.add(rs.getString("TenNhaCungCap"));
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return list;
		}
	}
	
	public NhaCungCap getNhaCungCapByName(String name) {
		String sql = "SELECT * from NhaCungCap where TenNhaCungCap like ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setNString(1, name);
			
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
	public List<NhaCungCap> filterPhieuNhapNCC(String ma,String ten,String sdt,String fax,String trangChu,String diaChi ){
		List<NhaCungCap> list = new ArrayList<NhaCungCap>();
		String sql = "select *from [dbo].[NhaCungCap] where [NhaCungCapId] like ? and [TenNhaCungCap] like ? and [SoDienThoai] like ? and [FAX] like ? and [TrangChu] like ? and ([DiaChi] like ? or  [DiaChi] is null)";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setNString(1,"%"+ma+"%" );
			ps.setNString(2,"%"+ten+"%" );
			ps.setNString(3,"%"+sdt+"%" );
			ps.setNString(4,"%"+fax+"%" );
			ps.setNString(5,"%"+trangChu+"%" );
			ps.setNString(6,"%"+diaChi+"%" );
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				NhaCungCap cap = new NhaCungCap();
				cap.setDiaChi(rs.getString("DiaChi"));
				cap.setFax(rs.getString("Fax"));
				cap.setId(rs.getString("NhaCungCapId"));
				cap.setSoDienThoai(rs.getString("SoDienThoai"));
				cap.setTenNhaCungCap(rs.getString("TenNhaCungCap"));
				cap.setTrangChu(rs.getString("TrangChu"));
				list.add(cap);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return list;
		}
	}
	public static void main(String[] args) {
		DAONhaCungCap cap = new DAONhaCungCap();
		System.out.println(cap.getNhaCungCapByID("NCC302"));
	}
}
