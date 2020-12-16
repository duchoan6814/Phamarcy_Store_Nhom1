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
	
	public String generateID() {
		String sql = "select top 1 * from NhaCungCap ORDER BY NhaCungCapId DESC";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String ID = rs.getString("NhaCungCapId");
				int n = ID.length();
				String text = ID.substring(0, 3);
				int number = Integer.parseInt(ID.substring(3, n));
				String newNumber = Integer.toString(number + 1);
				String newID;
				if (newNumber.length() >= 1 && newNumber.length() < 2) {
					newID = text+"00000"+newNumber;
				}else if (newNumber.length() >= 2 && newNumber.length() < 3) {
					newID = text+"0000"+newNumber;
				}else if (newNumber.length() >= 3 && newNumber.length() < 4) {
					newID = text+"000"+newNumber;
				}else if (newNumber.length() >= 4 && newNumber.length() < 5) {
					newID = text+"00"+newNumber;
				}else if (newNumber.length() >= 5 && newNumber.length() < 5) {
					newID = text+"0"+newNumber;
				}else {
					newID = text+newNumber;
				}
				return newID;
			}else {
				return "NCC000001";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "NCC000001";
		}
	}
	
	public boolean themNhaCungCap(NhaCungCap nhaCungCap) {
		String sql = "insert into NhaCungCap (DiaChi, Email, NhaCungCapId, SoDienThoai, TenNhaCungCap, TrangChu) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql );
			ps.setNString(1, nhaCungCap.getDiaChi());
			ps.setString(2, nhaCungCap.getEmail());
			ps.setString(3, nhaCungCap.getId());
			ps.setString(4, nhaCungCap.getSoDienThoai());
			ps.setNString(5, nhaCungCap.getTenNhaCungCap());
			ps.setString(6, nhaCungCap.getTrangChu());
			
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
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
				cap.setEmail(rs.getString("Fax"));
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
				cap.setEmail(rs.getString("Email"));
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
	public List<NhaCungCap> filterPhieuNhapNCC(String ma,String ten,String sdt,String email,String trangChu,String diaChi ){
		List<NhaCungCap> list = new ArrayList<NhaCungCap>();
		String sql = "select *from [dbo].[NhaCungCap] where [NhaCungCapId] like ? and [TenNhaCungCap] like ? and [SoDienThoai] like ? and [Email] like ? and [TrangChu] like ? and ([DiaChi] like ? or  [DiaChi] is null)";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setNString(1,"%"+ma+"%" );
			ps.setNString(2,"%"+ten+"%" );
			ps.setNString(3,"%"+sdt+"%" );
			ps.setNString(4,"%"+email+"%" );
			ps.setNString(5,"%"+trangChu+"%" );
			ps.setNString(6,"%"+diaChi+"%" );
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				NhaCungCap cap = new NhaCungCap();
				cap.setDiaChi(rs.getString("DiaChi"));
				cap.setEmail(rs.getString("Email"));
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

	public boolean suaNhaCungCap(NhaCungCap nhaCungCap) {
		// TODO Auto-generated method stub
		String sql = "update NhaCungCap SET DiaChi = ?, Email = ?, SoDienThoai = ?, TenNhaCungCap = ?, TrangChu = ? WHERE NhaCungCapId = ?";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.setNString(1, nhaCungCap.getDiaChi());
			ps.setString(2, nhaCungCap.getEmail());
			ps.setString(3, nhaCungCap.getSoDienThoai());
			ps.setNString(4, nhaCungCap.getTenNhaCungCap());
			ps.setString(5, nhaCungCap.getTrangChu());
			ps.setString(6, nhaCungCap.getId());
			
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return false;
	}

	public boolean xoaNhaCungCapByID(String data) {
		// TODO Auto-generated method stub
		String sql = "delete NhaCungCap WHERE NhaCungCapId = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, data);
			
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
