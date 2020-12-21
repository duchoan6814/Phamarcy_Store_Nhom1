package common;

public class LoThuocTableInPhieuHuy {
	private int STT, soLuongTon;
	private String maThuoc, tenThuoc, donViTinh, donGia, loaiThuoc, nuocSanXuat, ngaySanXuat, hanSuDung;
	public int getSTT() {
		return STT;
	}
	public void setSTT(int sTT) {
		STT = sTT;
	}
	public int getSoLuongTon() {
		return soLuongTon;
	}
	public void setSoLuongTon(int soLuongTon) {
		this.soLuongTon = soLuongTon;
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
	public String getNuocSanXuat() {
		return nuocSanXuat;
	}
	public void setNuocSanXuat(String nuocSanXuat) {
		this.nuocSanXuat = nuocSanXuat;
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
	public LoThuocTableInPhieuHuy(int sTT, int soLuongTon, String maThuoc, String tenThuoc, String donViTinh,
			String donGia, String loaiThuoc, String nuocSanXuat, String ngaySanXuat, String hanSuDung) {
		super();
		STT = sTT;
		this.soLuongTon = soLuongTon;
		this.maThuoc = maThuoc;
		this.tenThuoc = tenThuoc;
		this.donViTinh = donViTinh;
		this.donGia = donGia;
		this.loaiThuoc = loaiThuoc;
		this.nuocSanXuat = nuocSanXuat;
		this.ngaySanXuat = ngaySanXuat;
		this.hanSuDung = hanSuDung;
	}
	public LoThuocTableInPhieuHuy() {
		super();
	}
	@Override
	public String toString() {
		return "LoThuocTableInPhieuHuy [STT=" + STT + ", soLuongTon=" + soLuongTon + ", maThuoc=" + maThuoc
				+ ", tenThuoc=" + tenThuoc + ", donViTinh=" + donViTinh + ", donGia=" + donGia + ", loaiThuoc="
				+ loaiThuoc + ", nuocSanXuat=" + nuocSanXuat + ", ngaySanXuat=" + ngaySanXuat + ", hanSuDung="
				+ hanSuDung + "]";
	}
	
	
}
