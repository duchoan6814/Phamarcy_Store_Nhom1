package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.LoThuoc;
import entity.PhieuHuyHang;

public class DAOPhieuHuy {
	
	private DAOLoThuoc daoLoThuoc = new DAOLoThuoc();
	private Connection conn;
	
	public DAOPhieuHuy() {
		// TODO Auto-generated constructor stub
		conn = DAO.getInstance().getConn();
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
