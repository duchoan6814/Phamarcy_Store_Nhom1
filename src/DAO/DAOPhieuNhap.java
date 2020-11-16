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
	
	public void autoNhap() {
		Set<String> getListMaThuoc = new DAOThuoc().getAllMaThuoc();
		PhieuNhapHang phieuNhapHang = new PhieuNhapHang();
		java.util.Date date = new java.util.Date();
		phieuNhapHang.setId("NH20201114000002");
		QuanLy quanLy = new QuanLy();
		quanLy.setId("NV001");
		phieuNhapHang.setQuanLy(quanLy);
//		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		phieuNhapHang.setThoiGianLap(new Timestamp(date.getTime()));
		getListMaThuoc.forEach(i -> {
			phieuNhapHang.getDsLoThuoc().add(new LoThuoc(new Thuoc(i), Date.valueOf(LocalDate.now()), 50));
		});
		
		String sql = "insert into PhieuNhapHang (PhieuNhapHangId, QuanLyId, ThoiGianLap) VALUES (?, ?, DEFAULT)";
		String sql2 = "INSERT into LoThuoc(PhieuNhapHangId, ThuocId, NgaySanXuat, SoLuong) VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, phieuNhapHang.getId());
//			ps.setTimestamp(3, phieuNhapHang.getThoiGianLap());
			ps.setString(2, phieuNhapHang.getQuanLy().getId());
			ps.executeUpdate();
			phieuNhapHang.getDsLoThuoc().forEach(i -> {
				try {
					PreparedStatement ps2 = conn.prepareStatement(sql2);
					ps2.setString(1, phieuNhapHang.getId());
					ps2.setString(2, i.getThuoc().getId());
					ps2.setDate(3, i.getNgaySanXuat());
					ps2.setInt(4, i.getSoLuong());
					ps2.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("done");
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
				hang.setQuanLy(new QuanLy(rs.getString("QuanLyId")));
				hang.setDsLoThuoc((ArrayList<LoThuoc>) daoLoThuoc.getListLoThuocByIdPhieuNhap(id));
			}
			
			return hang;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {
		DAOPhieuNhap nhap = new DAOPhieuNhap();
		
//		nhap.autoNhap();
		
		nhap.getPhieuNhapHangById("NH20201114000002").getDsLoThuoc().forEach(i -> System.out.println(i));
	}
}
