package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.LoThuoc;

public class DAOLoThuoc extends DAO {
	DAOThuoc daoThuoc = new DAOThuoc();
	
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
				list.add(loThuoc);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return list;
		}
	}
}
