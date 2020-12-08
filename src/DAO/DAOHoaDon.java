package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import common.Common;
import common.HoaDonTable;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;

public class DAOHoaDon {
	private Connection conn;
	
	DAOChiTietHoaDon daoChiTietHoaDon = new DAOChiTietHoaDon();
	DAOKhachHang daoKhachHang = new DAOKhachHang();
	DAOLoThuoc daoLoThuoc = new DAOLoThuoc();
	DAONhanVien daoNhanVien = new DAONhanVien();
	
	public DAOHoaDon() {
		// TODO Auto-generated constructor stub
		conn = DAO.getInstance().getConn();
	}
	
	public double getDoanhThuTheoNam(String nam) {
		String sql = "SELECT SUM(TienPhaiTra) as TongDoanhThu from HoaDon where YEAR(ThoiGianLap) in (YEAR(?))";
		SimpleDateFormat dt1 = new SimpleDateFormat("yyyyy-MM-dd");
		Date date;
		try {
			date = dt1.parse(nam);
			LocalDate dateLocal = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			java.sql.Date dateGet = java.sql.Date.valueOf(dateLocal);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDate(1, dateGet);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getDouble("TongDoanhThu");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public double getTongDoanhThuNamHienTai() {
		String sql = "SELECT SUM(TienPhaiTra) as TongDoanhThu from HoaDon where YEAR(ThoiGianLap) in (YEAR(CURRENT_TIMESTAMP))";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
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
	
	public int getTongHoaDonNamHienTai() {
		String sql = "SELECT COUNT(*) as SoHoaDon from HoaDon where YEAR(ThoiGianLap) in (YEAR(CURRENT_TIMESTAMP))";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
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
	
	public double getTongDoanhThuByThang(String thang) {
		String sql = "SELECT sum(TienPhaiTra) as TongDoanhThu from HoaDon where MONTH(ThoiGianLap) in (MONTH(?)) and Year(ThoiGianLap) in (Year(?))";
		SimpleDateFormat dt1 = new SimpleDateFormat("yyyyy-MM-dd");
		Date date;
		try {
			date = dt1.parse(thang);
			LocalDate dateLocal = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			java.sql.Date dateGet = java.sql.Date.valueOf(dateLocal);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDate(1, dateGet);
			ps.setDate(2, dateGet);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getDouble("TongDoanhThu");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public int getSoHoaDonTheoThangHienTai() {
		String sql = "SELECT COUNT(*) as SoHoaDon FROM HoaDon WHERE MONTH(ThoiGianLap) in (MONTH(CURRENT_TIMESTAMP))";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
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
	
	public double getTongDoanhThuTheoThangHienTai() {
		String sql = "SELECT SUM(TienPhaiTra) as TongDoanhThu FROM HoaDon WHERE MONTH(ThoiGianLap) in (MONTH(CURRENT_TIMESTAMP))";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
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
	
	public double getTongDoanhThuTheoNgay(String ngayCanGet) {
		String sql = "SELECT sum(TienPhaiTra) as TongDoanhThu from HoaDon where ThoiGianLap BETWEEN ? and ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			SimpleDateFormat dt1 = new SimpleDateFormat("yyyyy-MM-dd");
			Date date = dt1.parse(ngayCanGet);
			LocalDate dateLocal = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			
			java.sql.Date dateGet = java.sql.Date.valueOf(dateLocal);
			java.sql.Date dateAfter = java.sql.Date.valueOf(dateLocal.plusDays(1));
			
			ps.setDate(1, dateGet);
			ps.setDate(2, dateAfter);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getDouble("TongDoanhThu");
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
	
	public double getTongDoanhThuTrongNgay() {
		String sql = "SELECT SUM(TienPhaiTra) as TongDoanhThu from HoaDon where ThoiGianLap BETWEEN ? and ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			java.sql.Date date = java.sql.Date.valueOf(LocalDate.now());
			java.sql.Date afterDate = java.sql.Date.valueOf(LocalDate.now().plusDays(1));
			ps.setDate(1, date);
			ps.setDate(2, afterDate);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt("TongDoanhThu");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public int getSoHoaDonTrongNgay() {
		String sql = "SELECT COUNT(*) as SoHoaDon from HoaDon where ThoiGianLap BETWEEN ? and ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			java.sql.Date date = java.sql.Date.valueOf(LocalDate.now());
			java.sql.Date afterDate = java.sql.Date.valueOf(LocalDate.now().plusDays(1));
			ps.setDate(1, date);
			ps.setDate(2, afterDate);
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
	
	public HoaDon getHoaDonById(String id) {
		String sql = "select * from HoaDon where HoaDonId = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				HoaDon hoaDon = new HoaDon();
				hoaDon.setId(rs.getString("HoaDonId"));
				hoaDon.setDiemSuDung(rs.getDouble("DiemSuDung"));
				hoaDon.setThoiGianLap(rs.getTimestamp("ThoiGianLap"));
				hoaDon.setDsChiTietHoaDon((ArrayList<ChiTietHoaDon>) daoChiTietHoaDon.getListChiTietHoaDonById(hoaDon.getId()));
				hoaDon.setNhanVienBanThuoc(daoNhanVien.getNhanVienById(rs.getString("NhanVienBanThuocId")));
				hoaDon.setKhachHang(daoKhachHang.getKhachHangById(rs.getString("KhachHangId")));
				return hoaDon;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<HoaDon> filterHoaDon(LocalDate dateFrom, LocalDate dateTo, String maHoaDon, String tenNhanVien, String tenKhachHang){

		List<HoaDon> list = new ArrayList<>();
		String _ngayLap = "";
		if (dateFrom.compareTo(dateTo) == 0) {
			java.sql.Date _dateFrom = java.sql.Date.valueOf(dateFrom.toString());
			_ngayLap = "hd.ThoiGianLap like '"+_dateFrom.toString()+"'";
		}else {
			java.sql.Date _dateFrom = java.sql.Date.valueOf(dateFrom.toString());
			java.sql.Date _dateTo = java.sql.Date.valueOf(dateTo.toString());
			_ngayLap = "hd.ThoiGianLap BETWEEN '"+_dateFrom.toString()+"' and '"+_dateTo.toString()+"'";
		}
		String sql = "SELECT hd.*, nv.HoTenDem as HoTenDemNV, nv.Ten as TenNV, kh.HoTenDem as HoTenDemKH, kh.Ten as TenKH from HoaDon as hd LEFT JOIN NhaVienBanThuoc as nv on hd.NhanVienBanThuocId = nv.NhanVienBanThuocId LEFT JOIN KhachHang as kh on hd.KhachHangId = kh.KhachHangId WHERE CONCAT_WS(' ', kh.HoTenDem, kh.Ten) like N'%"+tenKhachHang+"%' and CONCAT_WS(' ', nv.HoTenDem, nv.Ten) like N'%"+tenNhanVien+"%' and hd.HoaDonId like '%"+maHoaDon+"%' and "+_ngayLap+"";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);		
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				HoaDon hoaDon = new HoaDon();
				hoaDon.setId(rs.getString("HoaDonId"));
				hoaDon.setDiemSuDung(rs.getDouble("DiemSuDung"));
				hoaDon.setThoiGianLap(rs.getTimestamp("ThoiGianLap"));
				hoaDon.setDsChiTietHoaDon((ArrayList<ChiTietHoaDon>) daoChiTietHoaDon.getListChiTietHoaDonById(hoaDon.getId()));
				hoaDon.setNhanVienBanThuoc(daoNhanVien.getNhanVienById(rs.getString("NhanVienBanThuocId")));
				hoaDon.setKhachHang(daoKhachHang.getKhachHangById(rs.getString("KhachHangId")));
				list.add(hoaDon);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return list;
		}

	}

	public String generateId() {
		String sql = "SELECT top 1 HoaDonId from HoaDon ORDER BY HoaDonId DESC";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				String id = rs.getString("HoaDonId");
				int doDaiText = id.length();
				String txt = id.substring(0, 2);
				String date = id.substring(2, 10);
				String sqID = id.substring(10, doDaiText);

				String year = date.substring(0, 4);
				String month = date.substring(4, 6);
				String day = date.substring(6, 8);

				LocalDate datenow = LocalDate.now();
				if (datenow.compareTo(LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day))) != 0) {
					return "HD"+datenow.toString().replace("-", "")+"00001";
				}else {
					int n = Integer.parseInt(sqID);
					n++;
					String ns = null;
					if (Integer.toString(n).length() < 5 && Integer.toString(n).length() >= 4) {
						ns = "0"+Integer.toString(n);
					}else if (Integer.toString(n).length() < 4 && Integer.toString(n).length() >= 3) {
						ns = "00"+Integer.toString(n);
					}else if (Integer.toString(n).length() < 3 && Integer.toString(n).length() >= 2) {
						ns = "000"+Integer.toString(n);
					}else if (Integer.toString(n).length() < 2 && Integer.toString(n).length() >= 1) {
						ns = "0000"+Integer.toString(n);
					}
					return txt+date+ns;
				}

			}else {
				LocalDate date = LocalDate.now();
				return "HD"+date.toString().replace("-", "")+"00001";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean TaoHoaDon(HoaDon hoaDon) {
		String sql = "INSERT INTO HoaDon (HoaDonId, KhachHangId, NhanVienBanThuocId, ThoiGianLap, DiemSuDung, TienPhaiTra) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, hoaDon.getId());
			try {
				ps.setString(2, hoaDon.getKhachHang().getId());
			} catch (Exception e) {
				// TODO: handle exception
				ps.setString(2, null);
			}

			ps.setString(3, hoaDon.getNhanVienBanThuoc().getId());
			ps.setTimestamp(4, hoaDon.getThoiGianLap());
			ps.setDouble(5, hoaDon.getDiemSuDung());
			ps.setDouble(6, hoaDon.getTienPhaiTra());

			int rs = ps.executeUpdate();

			hoaDon.getDsChiTietHoaDon().forEach(i -> {
				ChiTietHoaDon chiTietHoaDon = i;
				if (!daoChiTietHoaDon.themChiTietHoaDon(hoaDon.getId(), chiTietHoaDon)) {
					System.out.println("them chi tiet hoa don khong thanh cong");
				}

				daoLoThuoc.giamSoLuongConLai(chiTietHoaDon.getSoLuong(), chiTietHoaDon.getThuoc().getId());

			});

			if (hoaDon.getKhachHang() != null) {
				if (!daoKhachHang.congDiemTichLuy(hoaDon)) {
					System.out.println("cong Diem that bai");
				}
				if (!daoKhachHang.truDiemTichLuy(hoaDon)) {
					System.out.println("tru diem that bai");
				}

			}

			return rs > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}
