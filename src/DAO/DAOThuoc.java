package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import entity.LoaiThuoc;
import entity.NhaCungCap;
import entity.Thuoc;

public class DAOThuoc {
	private Connection conn;

	DAOLoaiThuoc daoLoaiThuoc = new DAOLoaiThuoc();
	DAONhaCungCap daoNhaCungCap = new DAONhaCungCap();

	public DAOThuoc() {
		// TODO Auto-generated constructor stub
		conn = DAO.getInstance().getConn();
	}
	
	public Map<Thuoc, Double> thongKeThuocDoanhThuThuocTheoThoiGian(boolean checkAllTime, LocalDate dateFrom, LocalDate dateTo){
		String _date = checkAllTime ? "" : " and hd.ThoiGianLap BETWEEN '"+Date.valueOf(dateFrom).toString()+"' and '"+Date.valueOf(dateTo.plusDays(1)).toString()+"'";
		String sql = "SELECT top 10 ct.ThuocId, SUM(GiaBan*SoLuong) as DoanhThu FROM ChiTietHoaDon ct join HoaDon hd on ct.HoaDonId=hd.HoaDonId"+_date+" GROUP BY ct.ThuocId ORDER BY DoanhThu DESC";
		Map<Thuoc, Double> map = new LinkedHashMap<>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				map.put(getThuocById(rs.getString("ThuocId")), rs.getDouble("DoanhThu"));
			}
			return map;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return map;
		}
	}
	
	public List<String> getListDangBaoChe() {
		String sql = "SELECT DangBaoChe from thuoc GROUP BY DangBaoChe";
		List<String> listr = new ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				if (rs.getString("DangBaoChe") != null) {		
					listr.add(rs.getString("DangBaoChe"));
				}
				
			}
			return listr;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return listr;
		}
	}
	
	public List<String> getListQuyCachDongGoi() {
		String sql = "SELECT QuyCachDongGoi from Thuoc GROUP BY QuyCachDongGoi";
		List<String> listr = new ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				if (rs.getString("QuyCachDongGoi") != null) {
					listr.add(rs.getString("QuyCachDongGoi"));
				}
				
			}
			return listr;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return listr;
		}
	}
	
	
	
	public boolean checkCountry(String countryName) {
		String sql = "SELECT name from country where name = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, countryName);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public List<String> getAllCountry() {
		String sql = "SELECT name from country";
		List<String> list = new ArrayList<String>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				list.add(rs.getString("name"));
			}
			
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return list;
		}
	}

	public String generateID() {
		String sql = "select ThuocId from Thuoc WHERE ThuocId like 'TH%' order by ThuocId desc";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String id = rs.getString("ThuocId");
				int lengthId = id.length();
				String text = id.substring(0, 2);
				int number = Integer.parseInt(id.substring(2, lengthId));
				String newNumber = Integer.toString(number+1);
				
				String newID;
				
				if (newNumber.length() >= 1 && newNumber.length() < 2) {
					newID = text+"000000"+newNumber;
				}else if (newNumber.length() >= 2 && newNumber.length() < 3) {
					newID = text+"00000"+newNumber;
				}else if (newNumber.length() >= 3 && newNumber.length() < 4) {
					newID = text+"0000"+newNumber;
				}else if (newNumber.length() >= 4 && newNumber.length() < 5) {
					newID = text+"000"+newNumber;
				}else if (newNumber.length() >= 5 && newNumber.length() < 6) {
					newID = text+"00"+newNumber;
				}else if (newNumber.length() >= 6 && newNumber.length() < 7) {
					newID = text+"0"+newNumber;
				}
				else {
					newID = text+newNumber;
				}

				return newID;
			}else {
				return "TH0000001";
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "TH0000001";
	}

	public boolean updateThuoc(Thuoc thuoc) {
		String sql = "UPDATE Thuoc SET NhaCungCapId = ?, LoaiThuocId = ?, TenThuoc = ?,"
				+ " MoTa = ?, HanSuDung = ?, DonViTinh = ?, DangBaoChe = ?, Gia = ?, QuyCachDongGoi = ?, NuocSanXuat = ?"
				+ " Where ThuocId = ?";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, thuoc.getNhaCungCap().getId());
			ps.setString(2, thuoc.getLoaiThuoc().getId());
			ps.setNString(3, thuoc.getTenThuoc());
			ps.setNString(4, thuoc.getMoTa());
			ps.setInt(5, thuoc.getHanSuDung());
			ps.setNString(6, thuoc.getDonViTinh());
			ps.setNString(7, thuoc.getDangBaoChe());
			ps.setDouble(8, thuoc.getGia());
			ps.setNString(9, thuoc.getQuyCachDongGoi());
			ps.setNString(10, thuoc.getNuocSanXuat());
			ps.setString(11, thuoc.getId());

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean themThuocMoi(Thuoc thuoc) {
		String sql = "insert into Thuoc(DangBaoChe, DonViTinh, Gia, HanSuDung, LoaiThuocId, MoTa, NhaCungCapId, NuocSanXuat, QuyCachDongGoi, TenThuoc, Thue, ThuocId, TonKho)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setNString(1, thuoc.getDangBaoChe());
			ps.setNString(2, thuoc.getDonViTinh());
			ps.setDouble(3, thuoc.getGia());
			ps.setInt(4, thuoc.getHanSuDung());
			ps.setString(5, thuoc.getLoaiThuoc().getId());
			ps.setNString(6, thuoc.getMoTa());
			ps.setString(7, thuoc.getNhaCungCap().getId());
			ps.setNString(8, thuoc.getNuocSanXuat());
			ps.setNString(9, thuoc.getQuyCachDongGoi());
			ps.setNString(10, thuoc.getTenThuoc());
			ps.setDouble(11, 0.05);
			ps.setString(12, thuoc.getId());
			ps.setInt(13, 0);

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public List<Thuoc> filterThuoc(String maThuoc, String tenThuoc, String nuocSanXuat, String tenNhaCungCap, String loaiThuoc, String donViTinh, boolean soLuong){
		System.out.println(nuocSanXuat);
		String _tenNuocSanXuat = "(t.NuocSanXuat like N'%%' or t.NuocSanXuat is NULL)";
		if (!"Tất cả".equals(nuocSanXuat)) {
			_tenNuocSanXuat = "t.NuocSanXuat like N'%"+nuocSanXuat+"%'";
		}
		String _donViTinh = "N'%%'";
		if (!"Tất cả".equals(donViTinh)) {
			_donViTinh = "N'%"+donViTinh+"%'";
		}

		String _loaiThuoc = "N'%%'";
		if (!"Tất cả".equals(loaiThuoc)) {
			_loaiThuoc = "N'%"+loaiThuoc+"%'";
		}

		String _querySoLuong = "";
		if (soLuong) {
			_querySoLuong = "HAVING SUM(SoLuongConLai) < 10";
		}

		System.out.println(_tenNuocSanXuat);
		System.out.println(_donViTinh);
		System.out.println(_loaiThuoc);

		String sql = "SELECT t.ThuocId, t.DangBaoChe, t.DonViTinh, t.DonViTinh, t.Gia, t.HanSuDung, t.LoaiThuocId, CAST(t.MoTa AS NVARCHAR(255)) as MoTa, t.NhaCungCapId, t.NuocSanXuat, t.QuyCachDongGoi, t.TenThuoc, t.Thue, t.TonKho " + 
				"FROM Thuoc as t INNER JOIN NhaCungCap ncc on t.NhaCungCapId = ncc.NhaCungCapId " + 
				"INNER JOIN LoaiThuoc as lt on lt.LoaiThuocId = t.LoaiThuocId " + 
				"LEFT JOIN LoThuoc as lpt on lpt.ThuocId = t.ThuocId " + 
				"where t.ThuocId like ? and t.TenThuoc like ? " + 
				"and "+_tenNuocSanXuat+" "+ 
				"and ncc.TenNhaCungCap like ? and lt.TenLoaiThuoc like "+_loaiThuoc+" " + 
				"and t.DonViTinh like "+_donViTinh +" "+ 
				"GROUP BY t.ThuocId, t.DangBaoChe, t.DonViTinh, t.DonViTinh, t.Gia, t.HanSuDung, t.LoaiThuocId, CAST(t.MoTa AS NVARCHAR(255)), t.NhaCungCapId, t.NuocSanXuat, t.QuyCachDongGoi, t.TenThuoc, t.Thue, t.TonKho " + 
				_querySoLuong;

		List<Thuoc> list = new ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+maThuoc+"%");
			ps.setNString(2, "%"+tenThuoc+"%");
			ps.setNString(3, "%"+tenNhaCungCap+"%");

			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Thuoc thuoc = new Thuoc();
				thuoc.setDangBaoChe(rs.getString("DangBaoChe"));
				thuoc.setDonViTinh(rs.getString("DonViTinh"));
				thuoc.setGia(rs.getDouble("Gia"));
				thuoc.setHanSuDung(rs.getInt("HanSuDung"));
				thuoc.setId(rs.getString("ThuocId"));
				thuoc.setLoaiThuoc(daoLoaiThuoc.getLoaiThuocById(rs.getString("LoaiThuocId")));
				thuoc.setMoTa(rs.getString("MoTa"));
				thuoc.setNhaCungCap(daoNhaCungCap.getNhaCungCapByID(rs.getString("NhaCungCapId")));
				thuoc.setNuocSanXuat(rs.getString("NuocSanXuat"));
				thuoc.setQuyCachDongGoi(rs.getString("QuyCachDongGoi"));
				thuoc.setTenThuoc(rs.getString("TenThuoc"));
				thuoc.setThue(rs.getDouble("Thue"));
				thuoc.setTonKho(rs.getInt("TonKho"));

				list.add(thuoc);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return list;
		}
	}

	public List<String> getListNuocSanXuat(){
		String sql = "SELECT NuocSanXuat FROM Thuoc where NuocSanXuat is not null and NuocSanXuat not like '' group BY NuocSanXuat";
		List<String> list = new ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				list.add(rs.getString("NuocSanXuat"));
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return list;
		}
	}

	public List<String> getListDonViTinh(){
		String sql = "SELECT DonViTinh FROM thuoc group by DonViTinh";
		List<String> list = new ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				list.add(rs.getString("DonViTinh"));
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return list;
		}
	}

	public int getSoLuongTon(String id) {
		String sql = "select sum(SoLuongConLai) as SoLuongConLai from LoThuoc where ThuocId = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt("SoLuongConLai");
			}
			return -1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}

	public Set<String> getAllMaThuoc(){
		Set<String> list = new HashSet<>();
		String sql = "select ThuocId from Thuoc";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				list.add(rs.getString("ThuocId"));
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return list;
		}
	}

	public List<String> getAllTenThuoc() {
		List<String> list = new ArrayList<>();
		String sql = "select TenThuoc from Thuoc";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				list.add(rs.getString("TenThuoc"));
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<Thuoc> getAllThuoc(){
		List<Thuoc> list = new ArrayList<>();
		String sql = "select * from Thuoc";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				Thuoc thuoc = new Thuoc();
				thuoc.setDangBaoChe(rs.getString("DangBaoChe"));
				thuoc.setDonViTinh(rs.getString("DonViTinh"));
				thuoc.setGia(rs.getDouble("Gia"));
				thuoc.setHanSuDung(rs.getInt("HanSuDung"));
				thuoc.setId(rs.getString("ThuocId"));
				thuoc.setLoaiThuoc(daoLoaiThuoc.getLoaiThuocById(rs.getString("LoaiThuocId")));
				thuoc.setMoTa(rs.getString("MoTa"));
				thuoc.setNhaCungCap(daoNhaCungCap.getNhaCungCapByID(rs.getString("NhaCungCapId")));
				thuoc.setNuocSanXuat(rs.getString("NuocSanXuat"));
				thuoc.setQuyCachDongGoi(rs.getString("QuyCachDongGoi"));
				thuoc.setTenThuoc(rs.getString("TenThuoc"));
				thuoc.setThue(rs.getDouble("Thue"));
				thuoc.setTonKho(rs.getInt("TonKho"));
				list.add(thuoc);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}



	public Thuoc getThuocByName(String name) {

		String sql = "select * from Thuoc where TenThuoc like ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setNString(1, name);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Thuoc thuoc = new Thuoc();
				thuoc.setDangBaoChe(rs.getString("DangBaoChe"));
				thuoc.setDonViTinh(rs.getString("DonViTinh"));
				thuoc.setGia(rs.getDouble("Gia"));
				thuoc.setHanSuDung(rs.getInt("HanSuDung"));
				thuoc.setId(rs.getString("ThuocId"));
				thuoc.setLoaiThuoc(daoLoaiThuoc.getLoaiThuocById(rs.getString("LoaiThuocId")));
				thuoc.setMoTa(rs.getString("MoTa"));
				thuoc.setNhaCungCap(daoNhaCungCap.getNhaCungCapByID(rs.getString("NhaCungCapId")));
				thuoc.setNuocSanXuat(rs.getString("NuocSanXuat"));
				thuoc.setQuyCachDongGoi(rs.getString("QuyCachDongGoi"));
				thuoc.setTenThuoc(rs.getString("TenThuoc"));
				thuoc.setThue(rs.getDouble("Thue"));
				thuoc.setTonKho(rs.getInt("TonKho"));
				return thuoc;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public Thuoc getThuocById(String id) {

		String sql = "select * from Thuoc where ThuocId = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Thuoc thuoc = new Thuoc();
				thuoc.setDangBaoChe(rs.getString("DangBaoChe"));
				thuoc.setDonViTinh(rs.getString("DonViTinh"));
				thuoc.setGia(rs.getDouble("Gia"));
				thuoc.setHanSuDung(rs.getInt("HanSuDung"));
				thuoc.setId(rs.getString("ThuocId"));
				thuoc.setLoaiThuoc(daoLoaiThuoc.getLoaiThuocById(rs.getString("LoaiThuocId")));
				thuoc.setMoTa(rs.getString("MoTa"));
				thuoc.setNhaCungCap(daoNhaCungCap.getNhaCungCapByID(rs.getString("NhaCungCapId")));
				thuoc.setNuocSanXuat(rs.getString("NuocSanXuat"));
				thuoc.setQuyCachDongGoi(rs.getString("QuyCachDongGoi"));
				thuoc.setTenThuoc(rs.getString("TenThuoc"));
				thuoc.setThue(rs.getDouble("Thue"));
				thuoc.setTonKho(rs.getInt("TonKho"));
				return thuoc;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		DAOThuoc daoThuoc = new DAOThuoc();

		System.out.println(daoThuoc.getThuocById("VN-20017-16"));
	}
}
