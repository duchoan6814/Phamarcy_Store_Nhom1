package DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;


import entity.LoThuoc;
import entity.PhieuNhapHang;
import entity.QuanLy;
import entity.Thuoc;

public class DAOPhieuNhap extends DAO {
	DAOLoThuoc daoLoThuoc = new DAOLoThuoc();
	DAONhanVien daoNhanVien = new DAONhanVien();

//	public void autoNhap() {
//		Set<String> getListMaThuoc = new DAOThuoc().getAllMaThuoc();
//		PhieuNhapHang phieuNhapHang = new PhieuNhapHang();
//		java.util.Date date = new java.util.Date();
//		phieuNhapHang.setId("NH20201114000002");
//		QuanLy quanLy = new QuanLy();
//		quanLy.setId("NV001");
//		phieuNhapHang.setQuanLy(quanLy);
//		//		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//		phieuNhapHang.setThoiGianLap(new Timestamp(date.getTime()));
//		getListMaThuoc.forEach(i -> {
//			phieuNhapHang.getDsLoThuoc().add(new LoThuoc(new Thuoc(i), Date.valueOf(LocalDate.now()), 50, 0));
//		});
//
//		String sql = "insert into PhieuNhapHang (PhieuNhapHangId, QuanLyId, ThoiGianLap) VALUES (?, ?, DEFAULT)";
//		String sql2 = "INSERT into LoThuoc(PhieuNhapHangId, ThuocId, NgaySanXuat, SoLuong) VALUES (?, ?, ?, ?)";
//		try {
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ps.setString(1, phieuNhapHang.getId());
//			//			ps.setTimestamp(3, phieuNhapHang.getThoiGianLap());
//			ps.setString(2, phieuNhapHang.getQuanLy().getId());
//			ps.executeUpdate();
//			phieuNhapHang.getDsLoThuoc().forEach(i -> {
//				try {
//					PreparedStatement ps2 = conn.prepareStatement(sql2);
//					ps2.setString(1, phieuNhapHang.getId());
//					ps2.setString(2, i.getThuoc().getId());
//					ps2.setDate(3, i.getNgaySanXuat());
//					ps2.setInt(4, i.getSoLuong());
//					ps2.executeUpdate();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			});
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("done");
//	}

	public String generateIDPhieuNhap() {
		String sql = "SELECT top 1 PhieuNhapHangId from PhieuNhapHang ORDER BY PhieuNhapHangId DESC";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String id = rs.getString("PhieuNhapHangId");
				int doDaiText = id.length();
				String txt = id.substring(0, 2);
				String date = id.substring(2, 10);
				String sqID = id.substring(10, doDaiText);

				String year = date.substring(0, 4);
				String month = date.substring(4, 6);
				String day = date.substring(6, 8);

				LocalDate datenow = LocalDate.now();
				if (datenow.compareTo(LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day))) != 0) {
					return "NH"+datenow.toString().replace("-", "")+"00001";
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
				return "NH"+date.toString().replace("-", "")+"00001";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public PhieuNhapHang getPhieuNhapHangById(String id) {
		PhieuNhapHang hang = new PhieuNhapHang();
		String sql = "Select * from PhieuNhapHang where PhieuNhapHangId = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				hang.setId(rs.getString("PhieuNhapHangId"));
				hang.setThoiGianLap(rs.getTimestamp("ThoiGianLap"));
				hang.setNhanVienBanThuoc(daoNhanVien.getNhanVienById(rs.getString("QuanLyId")));
				hang.setDsLoThuoc((ArrayList<LoThuoc>) daoLoThuoc.getListLoThuocByIdPhieuNhap(id));
			}

			return hang;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean themPhieuNhapHang(PhieuNhapHang phieuNhapHang) {
		String sql = "INSERT into PhieuNhapHang(PhieuNhapHangId, QuanLyId, ThoiGianLap) VALUES (?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, phieuNhapHang.getId());
			ps.setString(2, phieuNhapHang.getNhanVienBanThuoc().getId());
			ps.setTimestamp(3, phieuNhapHang.getThoiGianLap());
			
			boolean rs = ps.executeUpdate() > 0;
			
			if (rs) {
				phieuNhapHang.getDsLoThuoc().forEach(i -> {
					daoLoThuoc.themLoThuoc(phieuNhapHang.getId(), i);
				});
			}
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public static void main(String[] args) {
		DAOPhieuNhap nhap = new DAOPhieuNhap();

		//		nhap.autoNhap();

		nhap.getPhieuNhapHangById("NH20201114000002").getDsLoThuoc().forEach(i -> System.out.println(i));
	}
}
