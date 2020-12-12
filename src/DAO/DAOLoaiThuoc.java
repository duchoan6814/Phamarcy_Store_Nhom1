package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.LoaiThuoc;

public class DAOLoaiThuoc {
	private Connection conn;
	
	public DAOLoaiThuoc() {
		// TODO Auto-generated constructor stub
		conn = DAO.getInstance().getConn();
	}
	
	public LoaiThuoc getLoaiThuocByTen(String name) {
		String sql = "SELECT * from LoaiThuoc WHERE TenLoaiThuoc like ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setNString(1, name);
			
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
	
	public List<String> getListLoaiThuoc() {
		String sql = "SELECT TenLoaiThuoc FROM LoaiThuoc";
		List<String> list = new ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				list.add(rs.getString("TenLoaiThuoc"));
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return list;
		}
		
	}
	
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
	
	public boolean themLoaiThuoc(LoaiThuoc loaiThuoc) {
		String sql = "insert into LoaiThuoc (LoaiThuocId, TenLoaiThuoc, MoTa) VALUES (?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, loaiThuoc.getId());
			ps.setNString(2, loaiThuoc.getTenLoai());
			ps.setString(3, loaiThuoc.getMoTa());
			
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[] args) {
		DAOLoaiThuoc daoLoaiThuoc = new DAOLoaiThuoc();
		System.out.println(daoLoaiThuoc.getLoaiThuocById("1"));
	}
}
