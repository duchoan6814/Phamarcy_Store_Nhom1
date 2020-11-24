package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import entity.LoaiThuoc;
import entity.NhaCungCap;
import entity.Thuoc;

public class DAOThuoc extends DAO {

	DAOLoaiThuoc daoLoaiThuoc = new DAOLoaiThuoc();
	DAONhaCungCap daoNhaCungCap = new DAONhaCungCap();

	public List<Thuoc> filterThuoc(String maThuoc, String tenThuoc, String nuocSanXuat, String tenNhaCungCap, String loaiThuoc, String donViTinh){
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

		System.out.println(_tenNuocSanXuat);
		System.out.println(_donViTinh);
		System.out.println(_loaiThuoc);
		String sql = "SELECT t.* FROM Thuoc as t INNER JOIN NhaCungCap ncc on t.NhaCungCapId = ncc.NhaCungCapId INNER JOIN "
				+"LoaiThuoc as lt on lt.LoaiThuocId = t.LoaiThuocId where t.ThuocId like ? and t.TenThuoc like ? "
				+"and "+_tenNuocSanXuat +" and ncc.TenNhaCungCap like ? and "
				+"lt.TenLoaiThuoc like "+_loaiThuoc+" and t.DonViTinh like "+_donViTinh+"";
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
		String sql = "SELECT NuocSanXuat FROM Thuoc group BY NuocSanXuat";
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
