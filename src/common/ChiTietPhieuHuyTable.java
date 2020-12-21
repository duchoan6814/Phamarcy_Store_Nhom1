package common;

public class ChiTietPhieuHuyTable {
	private int STT, soLuongHuy;
	private String maThuoc, tenThuoc, donViTinh, donGia, loaiThuoc, ngaySanXuat, hanSuDung, tongTien;
	public int getSTT() {
		return STT;
	}
	public void setSTT(int sTT) {
		STT = sTT;
	}
	public int getSoLuongHuy() {
		return soLuongHuy;
	}
	public void setSoLuongHuy(int soLuongHuy) {
		this.soLuongHuy = soLuongHuy;
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
	public String getLoaiThuoc() {
		return loaiThuoc;
	}
	public void setLoaiThuoc(String loaiThuoc) {
		this.loaiThuoc = loaiThuoc;
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
	public ChiTietPhieuHuyTable(int sTT, int soLuongHuy, String maThuoc, String tenThuoc, String donViTinh,
			String donGia, String loaiThuoc, String ngaySanXuat, String hanSuDung, String tongTien) {
		super();
		STT = sTT;
		this.soLuongHuy = soLuongHuy;
		this.maThuoc = maThuoc;
		this.tenThuoc = tenThuoc;
		this.donViTinh = donViTinh;
		this.donGia = donGia;
		this.loaiThuoc = loaiThuoc;
		this.ngaySanXuat = ngaySanXuat;
		this.hanSuDung = hanSuDung;
		this.tongTien = tongTien;
	}
	public ChiTietPhieuHuyTable() {
		super();
	}
	@Override
	public String toString() {
		return "ChiTietPhieuHuyTable [STT=" + STT + ", soLuongHuy=" + soLuongHuy + ", maThuoc=" + maThuoc
				+ ", tenThuoc=" + tenThuoc + ", donViTinh=" + donViTinh + ", donGia=" + donGia + ", loaiThuoc="
				+ loaiThuoc + ", ngaySanXuat=" + ngaySanXuat + ", hanSuDung=" + hanSuDung + ", tongTien=" + tongTien
				+ "]";
	}
	
	
}
