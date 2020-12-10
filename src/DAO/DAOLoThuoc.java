package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import entity.LoThuoc;

public class DAOLoThuoc{
	private Connection conn;
	
	DAOThuoc daoThuoc = new DAOThuoc();
	
	public DAOLoThuoc() {
		// TODO Auto-generated constructor stub
		conn = DAO.getInstance().getConn();
	}
	
	public boolean themLoThuoc(String phieuNhapHangId, LoThuoc loThuoc) {
		String sql = "INSERT into LoThuoc(PhieuNhapHangId, SoLuong, SoLuongConLai, ThuocId, NgaySanXuat) VALUES (?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, phieuNhapHangId);
			ps.setInt(2, loThuoc.getSoLuong());
			ps.setInt(3, loThuoc.getSoLuong());
			ps.setString(4, loThuoc.getThuoc().getId());
			ps.setDate(5, loThuoc.getNgaySanXuat());
			
			return ps.executeUpdate() > 0;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public void giamSoLuongConLai(int soLuong, String thuocId) {
		AtomicInteger _soLuong = new AtomicInteger(soLuong);
		Map<String, LoThuoc> list = getListLoThuocSoLuongTonKhachKhongById(thuocId);
		list.forEach((phieuNhapId, loThuoc) -> {	
			if (_soLuong.get() > loThuoc.getSoLuongConLai()) {
				_soLuong.set(_soLuong.get() - loThuoc.getSoLuongConLai());
				if (giamSoLuongConLaiTheoMaPhieuNhapVaMaThuoc(phieuNhapId, loThuoc.getThuoc().getId(), loThuoc.getSoLuongConLai())) {
					System.out.println("Giam so luong khong thanh cong");
				}
			}else {
				if (giamSoLuongConLaiTheoMaPhieuNhapVaMaThuoc(phieuNhapId, loThuoc.getThuoc().getId(), _soLuong.get())) {
					System.out.println("Giam so luong khong thanh cong");
				}
				_soLuong.set(0);
			}
			
		});
	}
	
	public boolean giamSoLuongConLaiTheoMaPhieuNhapVaMaThuoc(String maPhieuNhap, String maThuoc, int soLuongBiTru) {
		String sql = "update LoThuoc set SoLuongConLai = SoLuongConLai - ? where PhieuNhapHangId = ? and ThuocId = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, soLuongBiTru);
			ps.setString(2, maPhieuNhap);
			ps.setString(3, maThuoc);
			
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public Map<String ,LoThuoc> getListLoThuocSoLuongTonKhachKhongById(String Id){
		Map<String, LoThuoc> list = new HashMap<>();
		String sql = "Select * from LoThuoc where SoLuongConLai != 0 and ThuocId = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				LoThuoc loThuoc = new LoThuoc();
				loThuoc.setThuoc(daoThuoc.getThuocById(rs.getString("ThuocId")));
				loThuoc.setNgaySanXuat(rs.getDate("NgaySanXuat"));
				loThuoc.setSoLuong(rs.getInt("SoLuong"));
				loThuoc.setSoLuongConLai(rs.getInt("SoLuongConLai"));
				list.put(rs.getString("PhieuNhapHangId"), loThuoc);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return list;
		}
	}
	
	public List<LoThuoc> getListLoThuocByIdPhieuNhap(String id) {
		List<LoThuoc> list = new ArrayList<LoThuoc>();
		String sql = "select * from LoThuoc where PhieuNhapHangId = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				LoThuoc loThuoc = new LoThuoc();
				loThuoc.setNgaySanXuat(rs.getDate("NgaySanXuat"));
				loThuoc.setSoLuong(rs.getInt("SoLuong"));
				loThuoc.setThuoc(daoThuoc.getThuocById(rs.getString("ThuocId")));
				loThuoc.setSoLuongConLai(rs.getInt("SoLuongConLai"));
				list.add(loThuoc);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return list;
		}
	}
	
	public static void main(String[] args) {
		DAOLoThuoc daoLoThuoc = new DAOLoThuoc();
		daoLoThuoc.giamSoLuongConLai(160, "GC-247-16");
	}
}
