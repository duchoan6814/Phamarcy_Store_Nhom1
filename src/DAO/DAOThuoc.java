package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import entity.LoaiThuoc;
import entity.NhaCungCap;
import entity.Thuoc;

public class DAOThuoc extends DAO {
	DAOLoaiThuoc daoLoaiThuoc = new DAOLoaiThuoc();
	DAONhaCungCap daoNhaCungCap = new DAONhaCungCap();
	
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
