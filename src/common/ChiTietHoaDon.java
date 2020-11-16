package common;

public class ChiTietHoaDon {
	private String maThuoc, tenThuoc, donViTinh, nuocSanXuat, loaiThuoc, donGia, tongTien;
	private int stt, soLuong;
	
	
	
	public void setMaThuoc(String maThuoc) {
		this.maThuoc = maThuoc;
	}



	public void setTenThuoc(String tenThuoc) {
		this.tenThuoc = tenThuoc;
	}



	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}



	public void setNuocSanXuat(String nuocSanXuat) {
		this.nuocSanXuat = nuocSanXuat;
	}



	public void setLoaiThuoc(String loaiThuoc) {
		this.loaiThuoc = loaiThuoc;
	}



	public void setDonGia(String donGia) {
		this.donGia = donGia;
	}



	public void setTongTien(String tongTien) {
		this.tongTien = tongTien;
	}



	public void setStt(int stt) {
		this.stt = stt;
	}



	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}



	public String getMaThuoc() {
		return maThuoc;
	}



	public String getTenThuoc() {
		return tenThuoc;
	}



	public String getDonViTinh() {
		return donViTinh;
	}



	public String getNuocSanXuat() {
		return nuocSanXuat;
	}



	public String getLoaiThuoc() {
		return loaiThuoc;
	}



	public String getDonGia() {
		return donGia;
	}



	public String getTongTien() {
		return tongTien;
	}



	public int getStt() {
		return stt;
	}



	public int getSoLuong() {
		return soLuong;
	}



	public ChiTietHoaDon(String string, String string2, String string3,
			String string4, String string5, String string6,
			String string7, int i, int j) {
		super();
		this.maThuoc = string;
		this.tenThuoc = string2;
		this.donViTinh = string3;
		this.nuocSanXuat = string4;
		this.loaiThuoc = string5;
		this.donGia = string6;
		this.tongTien = string7;
		this.stt = i;
		this.soLuong = j;
	}
	
	
	
}
