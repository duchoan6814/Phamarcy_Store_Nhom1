package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import entity.LoThuoc;
import entity.Thuoc;

public class DAOLoThuoc{
	private Connection conn;
	
	DAOThuoc daoThuoc = new DAOThuoc();
	
	public DAOLoThuoc() {
		// TODO Auto-generated constructor stub
		conn = DAO.getInstance().getConn();
	}
	
	public List<LoThuoc> getLoThuocByPhieuHuy(String maPhieuHuy) {
		String sql = "SELECT * from LoThuoc where PhieuHuyHangId = ?";
		List<LoThuoc> list = new ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, maPhieuHuy);
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
	
	public void addPhieuHuyVaoLoThuoc(LoThuoc loThuoc, String maPhieuNhap, String maPhieuHuy) {
		String sql = "UPDATE LoThuoc set PhieuHuyHangId = ? WHERE PhieuNhapHangId = ? AND ThuocId = ? and NgaySanXuat = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, maPhieuHuy);
			ps.setString(2, maPhieuNhap);
			ps.setString(3, loThuoc.getThuoc().getId());
			ps.setDate(4, loThuoc.getNgaySanXuat());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public LoThuoc getLoThuocByID(String id, String nsx, String maPhieuNhap) {
		
		Date _nsx = Date.valueOf(nsx);
		
		String sql = "select * from LoThuoc where ThuocId = ? and NgaySanXuat = ? and PhieuNhapHangId = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setDate(2, _nsx);
			ps.setString(3, maPhieuNhap);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				LoThuoc loThuoc = new LoThuoc();
				loThuoc.setNgaySanXuat(rs.getDate("NgaySanXuat"));
				loThuoc.setSoLuong(rs.getInt("SoLuong"));
				loThuoc.setThuoc(daoThuoc.getThuocById(rs.getString("ThuocId")));
				loThuoc.setSoLuongConLai(rs.getInt("SoLuongConLai"));
				return loThuoc;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public Map<String, LoThuoc> filterLoThuoc(String maThuoc, String tenThuoc, String loaiThuoc, boolean hetHan) {
		Map<String, LoThuoc> list = new HashMap<String, LoThuoc>();
		String _loaiThuoc;
		if (loaiThuoc.equals("Tất Cả")) {
			_loaiThuoc = "";
		}else {
			_loaiThuoc = loaiThuoc;
		}
		
		String _hetHan = "";
		if (hetHan) {
			_hetHan = " and DATEDIFF(DAY, CURRENT_TIMESTAMP, DATEADD(MONTH, t.HanSuDung, lt.NgaySanXuat)) < 15";
		}
		
		String sql = "SELECT lt.* from LoThuoc as lt join Thuoc as t on lt.ThuocId = t.ThuocId join LoaiThuoc lth on t.LoaiThuocId = lth.LoaiThuocId WHERE lt.ThuocId like ? and t.TenThuoc like ? and lth.TenLoaiThuoc like ? and PhieuHuyHangId is null"+_hetHan;
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+maThuoc+"%");
			ps.setNString(2, "%"+tenThuoc+"%");
			ps.setNString(3, "%"+_loaiThuoc+"%");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				LoThuoc loThuoc = new LoThuoc();
				loThuoc.setNgaySanXuat(rs.getDate("NgaySanXuat"));
				loThuoc.setSoLuong(rs.getInt("SoLuong"));
				loThuoc.setThuoc(daoThuoc.getThuocById(rs.getString("ThuocId")));
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
