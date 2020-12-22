package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.LoaiKhachHang;
import entity.NhanVienBanThuoc;
import entity.PhanQuyen;
import entity.TaiKhoan;

public class DAOTaiKhoan {
	private Connection conn;
	public DAOTaiKhoan() {
		// TODO Auto-generated constructor stub
		conn = DAO.getInstance().getConn();
	}
	
	public boolean suaTaiKhoan(TaiKhoan taiKhoan) {
		String sql = "update TaiKhoan set PhanQuyen = ?, MatKhau = ? WHERE TenDangNhap = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, taiKhoan.getPhanQuyen().getPhanQuyen());
			ps.setString(2, taiKhoan.getMatKhau());
			ps.setString(3, taiKhoan.getTenDangNhap());
			
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean themTaiKhoan(Connection connTemp, NhanVienBanThuoc nhanVienBanThuoc) {
		String sql = "INSERT into TaiKhoan(TenDangNhap, MatKhau, PhanQuyen) VALUES (?, ?, ?)";
		try {
			connTemp.setAutoCommit(false);
			PreparedStatement ps = connTemp.prepareStatement(sql);
			ps.setString(1, nhanVienBanThuoc.getTaiKhoan().getTenDangNhap());
			ps.setString(2, nhanVienBanThuoc.getTaiKhoan().getMatKhau());
			ps.setString(3, nhanVienBanThuoc.getTaiKhoan().getPhanQuyen().getPhanQuyen());
			
			boolean rs = ps.executeUpdate() > 0;
			connTemp.commit();
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				connTemp.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean checkTaiKhoan(String userName) {
		String sql = "select * from TaiKhoan WHERE TenDangNhap = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public boolean checkMatKhau(String userName, String password) {
		String sql = "select * from TaiKhoan WHERE TenDangNhap = ? and MatKhau = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
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

//	public static void main(String[] args) {
//		DAOTaiKhoan dtk = new DAOTaiKhoan();
//		System.out.println(dtk.login("duchoan6814", "sapassword"));
//	}
}
