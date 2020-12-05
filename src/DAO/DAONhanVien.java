package DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import entity.NhanVienBanThuoc;
import entity.PhanQuyen;
import entity.TaiKhoan;

public class DAONhanVien extends DAO {
	
	public double getTongDoanhThuTheoThangHienTai(String maNhanVien) {
		String sql = "SELECT SUM(TienPhaiTra) as TongDoanhThu FROM HoaDon WHERE MONTH(ThoiGianLap) in (MONTH(CURRENT_TIMESTAMP)) AND NhanVienBanThuocId = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, maNhanVien);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getDouble("TongDoanhThu");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public int getSoHoaDonTrongThangHienTai(String maNhanVien) {
		String sql = "SELECT COUNT(*) as SoHoaDon FROM HoaDon WHERE MONTH(ThoiGianLap) in (MONTH(CURRENT_TIMESTAMP)) AND NhanVienBanThuocId = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, maNhanVien);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt("SoHoaDon");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public double getDoanhSoTrongNgay(String maNhanVien) {
		String sql = "select sum(TienPhaiTra) as TongDoanhSo from HoaDon WHERE NhanVienBanThuocId = ? and ThoiGianLap BETWEEN ? and ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			Date date = Date.valueOf(LocalDate.now());
			Date date1 = Date.valueOf(LocalDate.now().plusDays(1));
			ps.setString(1, maNhanVien);
			ps.setDate(2, date);
			ps.setDate(3, date1);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				return rs.getDouble("TongDoanhSo");
			}
			return -1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
	
	
	public double getDoanhSoByNgay(String maNhanVien, String ngayCanGet) {
		String sql = "select sum(TienPhaiTra) as TongDoanhSo from HoaDon WHERE NhanVienBanThuocId = ? and ThoiGianLap BETWEEN ? and ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			SimpleDateFormat dt1 = new SimpleDateFormat("yyyyy-MM-dd");
			
			java.util.Date date = dt1.parse(ngayCanGet);
			
			LocalDate dateLocal = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			
			Date datesql1 = Date.valueOf(dateLocal);
			Date datesql2 = Date.valueOf(dateLocal.plusDays(1));
			
			ps.setString(1, maNhanVien);
			ps.setDate(2, datesql1);
			ps.setDate(3, datesql2);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				return rs.getDouble("TongDoanhSo");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public double getDoanhSoTong(String maNhanVien) {
		String sql = "select sum(TienPhaiTra) as TongDoanhSo from HoaDon WHERE NhanVienBanThuocId = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, maNhanVien);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt("TongDoanhSo");
			}
			return -1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
	
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
	
	public int getSoHoaDonTrongNgay(String maNhanVien) {
		String sql = "select count(*) as SoHoaDon from HoaDon where NhanVienBanThuocId = ? and ThoiGianLap BETWEEN ? and ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, maNhanVien);
			Date date = Date.valueOf(LocalDate.now());
			Date date1 = Date.valueOf(LocalDate.now().plusDays(1));
			ps.setDate(2, date);
			ps.setDate(3, date1);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				return rs.getInt("SoHoaDon");
			}
			return -1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
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


	public List<NhanVienBanThuoc> getlistNhanVien() {
		// TODO Auto-generated method stub
		List<NhanVienBanThuoc> list = new ArrayList<NhanVienBanThuoc>();
		String sql = "select * from NhaVienBanThuoc as nv join TaiKhoan as tk on nv.TenDangNhap = tk.TenDangNhap";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
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
				list.add(nhanVienBanThuoc);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return list;
		}
	}
}
