package common;

public class ChiTietPhieuNhapTable {
	private String maThuoc, tenThuoc, ngaySanXuat, hanSuDung, tongTien;
	private int soLuong;
	public String getMaThuoc() {
		return maThuoc;
	}
	public void setMaThuoc(String maThuoc) {
		this.maThuoc = maThuoc;
	}
	public String getTenThuoc() {
		return tenThuoc;
	}
	public void setTenThuoc(String tenThuoc) {
		this.tenThuoc = tenThuoc;
	}
	public String getNgaySanXuat() {
		return ngaySanXuat;
	}
	public void setNgaySanXuat(String ngaySanXuat) {
		this.ngaySanXuat = ngaySanXuat;
	}
	public String getHanSuDung() {
		return hanSuDung;
	}
	public void setHanSuDung(String hanSuDung) {
		this.hanSuDung = hanSuDung;
	}
	public String getTongTien() {
		return tongTien;
	}
	public void setTongTien(String tongTien) {
		this.tongTien = tongTien;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public ChiTietPhieuNhapTable(String maThuoc, String tenThuoc, String ngaySanXuat, String hanSuDung, String tongTien,
			int soLuong) {
		super();
		this.maThuoc = maThuoc;
		this.tenThuoc = tenThuoc;
		this.ngaySanXuat = ngaySanXuat;
		this.hanSuDung = hanSuDung;
		this.tongTien = tongTien;
		this.soLuong = soLuong;
	}
	public ChiTietPhieuNhapTable() {
		super();
	}
	@Override
	public String toString() {
		return "ChiTietPhieuNhapTable [maThuoc=" + maThuoc + ", tenThuoc=" + tenThuoc + ", ngaySanXuat=" + ngaySanXuat
				+ ", hanSuDung=" + hanSuDung + ", tongTien=" + tongTien + ", soLuong=" + soLuong + "]";
	}
	
	
}
