package entity;

import java.sql.Date;

public class TestApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HoaDon hd = new HoaDon();
		QuanLy ql = new QuanLy();
		ql.setHoTenDem("Truong Duc");
		ql.setTen("Hoan");
		hd.setNhanVienBanThuoc(ql);
		System.out.println(hd);

		PhieuNhapHang pn = new PhieuNhapHang();
		NhanVienBanThuoc nv = new NhanVienBanThuoc();
		nv.setHoTenDem("Truong Duc");
		nv.setTen("Hoan");
//		pn.setQuanLy(ql);

		System.out.println(pn);
	}

}
