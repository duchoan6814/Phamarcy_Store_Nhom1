package common;

public class ChiTietHoaDonTable {
	private int soLuong;
	private String tenThuoc, donViTinh, donGia, tongTien;
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public String getTenThuoc() {
		return tenThuoc;
	}
	public void setTenThuoc(String tenThuoc) {
		this.tenThuoc = tenThuoc;
	}
	public String getDonViTinh() {
		return donViTinh;
	}
	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}
	public String getDonGia() {
		return donGia;
	}
	public void setDonGia(String donGia) {
		this.donGia = donGia;
	}
	public String getTongTien() {
		return tongTien;
	}
	public void setTongTien(String tongTien) {
		this.tongTien = tongTien;
	}
	public ChiTietHoaDonTable(int soLuong, String tenThuoc, String donViTinh, String donGia, String tongTien) {
		super();
		this.soLuong = soLuong;
		this.tenThuoc = tenThuoc;
		this.donViTinh = donViTinh;
		this.donGia = donGia;
		this.tongTien = tongTien;
	}
	public ChiTietHoaDonTable() {
		super();
	}
	@Override
	public String toString() {
		return "ChiTietHoaDonTable [soLuong=" + soLuong + ", tenThuoc=" + tenThuoc + ", donViTinh=" + donViTinh
				+ ", donGia=" + donGia + ", tongTien=" + tongTien + "]";
	}
	
	
}
