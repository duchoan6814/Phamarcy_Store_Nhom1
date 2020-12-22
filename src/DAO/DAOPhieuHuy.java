package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entity.LoThuoc;
import entity.PhieuHuyHang;

public class DAOPhieuHuy {

	private DAOLoThuoc daoLoThuoc = new DAOLoThuoc();
	private DAONhanVien daoNhanVien = new DAONhanVien();
	private Connection conn;

	public DAOPhieuHuy() {
		// TODO Auto-generated constructor stub
		conn = DAO.getInstance().getConn();
	}
	
	public PhieuHuyHang getPhieuHuyHangByID(String maPhieuHuy) {
		String sql = "select * from PhieuHuyHang where PhieuHuyId = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, maPhieuHuy);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				PhieuHuyHang phieuHuyHang = new PhieuHuyHang();
				phieuHuyHang.setId(rs.getString("PhieuHuyId"));
				phieuHuyHang.setQuanLy(daoNhanVien.getNhanVienById(rs.getString("QuanLyId")));
				phieuHuyHang.setThoiGianLap(rs.getTimestamp("ThoiGianLap"));
				phieuHuyHang.setDsLoThuoc((ArrayList<LoThuoc>) daoLoThuoc.getLoThuocByPhieuHuy(rs.getString("PhieuHuyID")));
				return phieuHuyHang;
			}
			
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<PhieuHuyHang> filterPhieuHuy(LocalDate dateFrom, LocalDate dateTo, String phieuHuyID, String nhanVien, boolean tatCaThoiGian){

		String _ngayLap = "";

		if (!tatCaThoiGian) {
			java.sql.Date _dateFrom = java.sql.Date.valueOf(dateFrom.toString());
			java.sql.Date _dateTo = java.sql.Date.valueOf(dateTo.plusDays(1).toString());
			_ngayLap = " and ThoiGianLap between '"+_dateFrom+"' and '"+_dateTo+"'";
		}

		String sql = "SELECT phh.* FROM PhieuHuyHang phh join NhaVienBanThuoc nvbt on phh.QuanLyId = nvbt.NhanVienBanThuocId WHERE PhieuHuyId like ? and CONCAT_WS(' ', nvbt.HoTenDem, nvbt.Ten) like ?"+_ngayLap;

		List<PhieuHuyHang> list = new ArrayList<>();

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+phieuHuyID+"%");
			ps.setNString(2, "%"+nhanVien+"%");

			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				PhieuHuyHang phieuHuyHang = new PhieuHuyHang();
				phieuHuyHang.setId(rs.getString("PhieuHuyId"));
				phieuHuyHang.setQuanLy(daoNhanVien.getNhanVienById(rs.getString("QuanLyId")));
				phieuHuyHang.setThoiGianLap(rs.getTimestamp("ThoiGianLap"));
				phieuHuyHang.setDsLoThuoc((ArrayList<LoThuoc>) daoLoThuoc.getLoThuocByPhieuHuy(rs.getString("PhieuHuyID")));
				list.add(phieuHuyHang);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return list;
		}

	}

	public boolean themPhieuHuy(PhieuHuyHang phieuHuyHang) {
		String sql = "insert into PhieuHuyHang (PhieuHuyId, QuanLyId, ThoiGianLap) VALUES (?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, phieuHuyHang.getId());
			ps.setString(2, phieuHuyHang.getQuanLy().getId());
			ps.setTimestamp(3, phieuHuyHang.getThoiGianLap());

			boolean rs = (int) ps.executeUpdate() > 0;

			if (rs) {

				for (LoThuoc i : phieuHuyHang.getDsLoThuoc()) {
					daoLoThuoc.addPhieuHuyVaoLoThuoc(i, i.getMaPhieuNhap(), phieuHuyHang.getId());
				}
			}
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	public String genenateID() {
		String sql = "SELECT PhieuHuyId from PhieuHuyHang ORDER BY PhieuHuyId DESC";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String currentID = rs.getString("PhieuHuyId");
				int n = currentID.length();
				String text = currentID.substring(0, 2);
				String nextNumber = Integer.toString(Integer.parseInt(currentID.substring(2, n)) + 1);
				String newId = "";
				if (nextNumber.length() >= 1 && nextNumber.length() < 2) {
					newId = text+"000000"+nextNumber;
				}else if (nextNumber.length() >= 2 && nextNumber.length() < 3) {
					newId = text+"00000"+nextNumber;
				}else if (nextNumber.length() >= 3 && nextNumber.length() < 4) {
					newId = text+"0000"+nextNumber;
				}else if (nextNumber.length() >= 4 && nextNumber.length() < 5) {
					newId = text+"000"+nextNumber;
				}else if (nextNumber.length() >= 5 && nextNumber.length() < 6) {
					newId = text+"00"+nextNumber;
				}else if (nextNumber.length() >= 6 && nextNumber.length() < 7) {
					newId = text+"0"+nextNumber;
				}else {
					newId = text+nextNumber;
				}
				return newId;
			}else {
				return "HH0000001";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Loi roi";
		}
	}

}
