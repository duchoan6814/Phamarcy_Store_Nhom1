package entity;

import java.sql.Date;

public class QuanLy extends NhanVienBanThuoc {
	public QuanLy() {
		// TODO Auto-generated constructor stub
		super();
	}

	public QuanLy(String id, String hoTenDem, String ten, String soDienThoai, String soCMND, Date ngaySinh,
			boolean gioiTinh, String diaChi, byte[] avatar, TaiKhoan taiKhoan) {
		super(id, hoTenDem, ten, soDienThoai, soCMND, ngaySinh, gioiTinh, diaChi, avatar, taiKhoan);
	}

	public QuanLy(String id) {
		// TODO Auto-generated constructor stub
		super(id);
	}
}
