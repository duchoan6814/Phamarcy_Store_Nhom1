package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.NhanVienBanThuoc;
import entity.PhanQuyen;
import entity.TaiKhoan;

public class DAONhanVien extends DAO {
	
	public NhanVienBanThuoc getNhanVienBanThuocByUserName(String userName) {
		String sql = "SELECT tk.PhanQuyen, nv.* FROM TaiKhoan tk join NhaVienBanThuoc nv on tk.TenDangNhap = nv.TenDangNhap WHERE nv.TenDangNhap = ?";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ResultSet result = ps.executeQuery();
			if (result.next()) {
				NhanVienBanThuoc nhanVienBanThuoc = new NhanVienBanThuoc();
				nhanVienBanThuoc.setId(result.getString("NhanVienBanThuocId"));
				nhanVienBanThuoc.setHoTenDem(result.getString("HoTenDem"));
				nhanVienBanThuoc.setTen(result.getString("Ten"));
				nhanVienBanThuoc.setNgaySinh(result.getDate("NgaySinh"));
				nhanVienBanThuoc.setSoDienThoai(result.getString("SoDienThoai"));
				nhanVienBanThuoc.setSoCMND(result.getString("SoCMND"));
				nhanVienBanThuoc.setGioiTinh(result.getBoolean("GioiTinh"));
				nhanVienBanThuoc.setDiaChi(result.getString("DiaChi"));
				nhanVienBanThuoc.setAvatar(result.getBytes("Avatar"));
				TaiKhoan taiKhoan = new TaiKhoan();
				taiKhoan.setTenDangNhap(result.getString("TenDangNhap"));
				taiKhoan.setPhanQuyen(PhanQuyen.get(result.getString("PhanQuyen").trim()));
				nhanVienBanThuoc.setTaiKhoan(taiKhoan);
				return nhanVienBanThuoc;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public boolean kiemTraNhanVien(String maNhanVien) {
		String sql = "SELECT * from NhaVienBanThuoc WHERE NhanVienBanThuocId = ?";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, maNhanVien);
			ResultSet result = ps.executeQuery();
			if (result.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return false;
	}


	public int getTotalHoaDonByNhanVien(String maNhanVien) {
		String sql = "SELECT count(*) as TongSoHoaDon from HoaDon where NhanVienBanThuocId = ?";

		if (!kiemTraNhanVien(maNhanVien)) {
			return -1;
		}else {
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, maNhanVien);
				ResultSet result = ps.executeQuery();
				if (result.next()) {
					return result.getInt("TongSoHoaDon");
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -1;
			}
		}
		return -1;
	}

	public NhanVienBanThuoc getNhanVienById(String nhanVienID) {
		// TODO Auto-generated method stub
		String sql = "select * from NhaVienBanThuoc as nv join TaiKhoan as tk on nv.TenDangNhap = tk.TenDangNhap where NhanVienBanThuocId = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, nhanVienID);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				NhanVienBanThuoc nhanVienBanThuoc = new NhanVienBanThuoc();
				nhanVienBanThuoc.setId(rs.getString("NhanVienBanThuocId"));
				nhanVienBanThuoc.setHoTenDem(rs.getString("HoTenDem"));
				nhanVienBanThuoc.setTen(rs.getString("Ten"));
				nhanVienBanThuoc.setNgaySinh(rs.getDate("NgaySinh"));
				nhanVienBanThuoc.setSoDienThoai(rs.getString("SoDienThoai"));
				nhanVienBanThuoc.setSoCMND(rs.getString("SoCMND"));
				nhanVienBanThuoc.setGioiTinh(rs.getBoolean("GioiTinh"));
				nhanVienBanThuoc.setDiaChi(rs.getString("DiaChi"));
				nhanVienBanThuoc.setAvatar(rs.getBytes("Avatar"));
				TaiKhoan taiKhoan = new TaiKhoan();
				taiKhoan.setTenDangNhap(rs.getString("TenDangNhap"));
				taiKhoan.setPhanQuyen(PhanQuyen.get(rs.getString("PhanQuyen").trim()));
				nhanVienBanThuoc.setTaiKhoan(taiKhoan);
				return nhanVienBanThuoc;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
