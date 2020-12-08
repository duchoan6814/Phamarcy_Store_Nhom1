package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.ChiTietHoaDon;
import entity.NhanVienBanThuoc;

public class DAOChiTietHoaDon{
	private Connection conn;
	
	public DAOChiTietHoaDon() {
		// TODO Auto-generated constructor stub
		conn = DAO.getInstance().getConn();
	}
	
	DAOThuoc daoThuoc = new DAOThuoc();
	
	public List<ChiTietHoaDon> getListChiTietHoaDonById(String id) {
		List<ChiTietHoaDon> list = new ArrayList<>();
		String sql = "SELECT * from ChiTietHoaDon where HoaDonId = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
				chiTietHoaDon.setSoLuong(rs.getInt("SoLuong"));
				chiTietHoaDon.setThuoc(daoThuoc.getThuocById(rs.getString("ThuocId")));
				list.add(chiTietHoaDon);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return list;
		}
	}
	
	public boolean themChiTietHoaDon(String hoaDonID, ChiTietHoaDon chiTietHoaDon) {
		String sql = "INSERT INTO ChiTietHoaDon (HoaDonId, SoLuong, ThuocId, GiaBan) VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, hoaDonID);
			ps.setInt(2, chiTietHoaDon.getSoLuong());
			ps.setString(3, chiTietHoaDon.getThuoc().getId());
			ps.setDouble(4, chiTietHoaDon.getGiaBan());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
