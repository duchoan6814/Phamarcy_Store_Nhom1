package common;

public class LoThuocTable {
	private int stt, soLuong;
	private String maThuoc, tenThuoc, ngaySanXuat, hanSuDung, donGia, tongTien;
	public int getStt() {
		return stt;
	}
	public void setStt(int stt) {
		this.stt = stt;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
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
	public LoThuocTable(int stt, int soLuong, String maThuoc, String tenThuoc, String ngaySanXuat, String hanSuDung,
			String donGia, String tongTien) {
		super();
		this.stt = stt;
		this.soLuong = soLuong;
		this.maThuoc = maThuoc;
		this.tenThuoc = tenThuoc;
		this.ngaySanXuat = ngaySanXuat;
		this.hanSuDung = hanSuDung;
		this.donGia = donGia;
		this.tongTien = tongTien;
	}
	public LoThuocTable() {
		super();
	}
	@Override
	public String toString() {
		return "LoThuocTable [stt=" + stt + ", soLuong=" + soLuong + ", maThuoc=" + maThuoc + ", tenThuoc=" + tenThuoc
				+ ", ngaySanXuat=" + ngaySanXuat + ", hanSuDung=" + hanSuDung + ", donGia=" + donGia + ", tongTien="
				+ tongTien + "]";
	}
	
	
}
