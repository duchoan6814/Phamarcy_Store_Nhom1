package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.LoaiKhachHang;
import entity.NhanVienBanThuoc;
import entity.PhanQuyen;
import entity.TaiKhoan;

public class DAOTaiKhoan extends DAO {
	public boolean login(String userName, String password) {
		String sql = "select * from TaiKhoan where TenDangNhap = ? and MatKhau = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, password);
			ResultSet result = ps.executeQuery();
			return result.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public static void main(String[] args) {
		DAOTaiKhoan dtk = new DAOTaiKhoan();
		System.out.println(dtk.login("duchoan6814", "sapassword"));
	}
}
