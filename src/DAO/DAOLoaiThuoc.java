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
	
	public boolean suaLoaiThuoc(LoaiThuoc loaiThuoc) {
		String sql = "update LoaiThuoc set TenLoaiThuoc = ?, MoTa = ? where LoaiThuocId = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setNString(1, loaiThuoc.getTenLoai());
			ps.setNString(2, loaiThuoc.getMoTa());
			ps.setString(3, loaiThuoc.getId());
			
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public Boolean xoaLoaiThuocByID(String id) {
		String sql = "delete from LoaiThuoc WHERE LoaiThuocId = ?";
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
	
	public List<LoaiThuoc> filterLoaiThuoc(String maLoai, String tenLoai, String moTa) {
		String sql = "SELECT * FROM LoaiThuoc WHERE LoaiThuocId LIKE ? and TenLoaiThuoc like ? AND MoTa like ?";
		List<LoaiThuoc> list = new ArrayList<LoaiThuoc>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+maLoai+"%");
			ps.setNString(2, "%"+tenLoai+"%");
			ps.setNString(3, "%"+moTa+"%");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				LoaiThuoc loaiThuoc = new LoaiThuoc();
				loaiThuoc.setId(rs.getString("LoaiThuocId"));
				loaiThuoc.setTenLoai(rs.getString("TenLoaiThuoc"));
				loaiThuoc.setMoTa(rs.getString("MoTa"));
				
				list.add(loaiThuoc);
			}
			
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return list;
		}
	}

	public List<LoaiThuoc> getDSLoaiThuoc() {
		List<LoaiThuoc> list = new ArrayList<>();
		String sql = "select * from LoaiThuoc";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				LoaiThuoc loaiThuoc = new LoaiThuoc();
				loaiThuoc.setId(rs.getString("LoaiThuocId"));
				loaiThuoc.setTenLoai(rs.getNString("TenLoaiThuoc"));
				loaiThuoc.setMoTa(rs.getNString("MoTa"));
				
				list.add(loaiThuoc);
			}
			
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return list;
		}
	}


	public String generateID() {
		String sql = "select top 1 LoaiThuocId from LoaiThuoc order by LoaiThuocId desc";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String currentID = rs.getString("LoaiThuocId");
				int lengthID = currentID.length();
				try {
					String text = currentID.substring(0, 2);
					int number = Integer.parseInt(currentID.substring(2, lengthID));
					String nextID = Integer.toString(number+1);

					String newID;

					if (nextID.length() >= 1 && nextID.length() <  2) {
						newID = text+"000"+nextID;
					}else if (nextID.length() >= 2 && nextID.length() <  3) {
						newID = text+"00"+nextID;
					}else if (nextID.length() >= 3 && nextID.length() <  4) {
						newID = text+"0"+nextID;
					}else {
						newID = text+nextID;
					}
					return newID;
				} catch (Exception e) {
					// TODO: handle exception
					return "LT0001";
				}
				
			}else {
				return "LT0001";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "LT0001";
		}
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
