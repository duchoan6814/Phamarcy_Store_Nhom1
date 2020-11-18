package DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import entity.ChiTietHoaDon;

public class DAOChiTietHoaDon extends DAO {
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
