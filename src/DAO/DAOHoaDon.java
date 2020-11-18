package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;

public class DAOHoaDon extends DAO {
	DAOChiTietHoaDon daoChiTietHoaDon = new DAOChiTietHoaDon();
	DAOKhachHang daoKhachHang = new DAOKhachHang();
	DAOLoThuoc daoLoThuoc = new DAOLoThuoc();
	
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
