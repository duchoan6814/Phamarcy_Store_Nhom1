package common;

public class QuanLyNhanVienTable {
	private int stt;
	private String maNhanVien, tenNhanVien, ngaySinh, soDienThoai, soCMND, gioiTinh, phanQuyen;
	public int getStt() {
		return stt;
	}
	public void setStt(int stt) {
		this.stt = stt;
	}
	public String getMaNhanVien() {
		return maNhanVien;
	}
	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}
	public String getTenNhanVien() {
		return tenNhanVien;
	}
	public void setTenNhanVien(String tenNhanVien) {
		this.tenNhanVien = tenNhanVien;
	}
	public String getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getSoDienThoai() {
		return soDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
	public String getSoCMND() {
		return soCMND;
	}
	public void setSoCMND(String soCMND) {
		this.soCMND = soCMND;
	}
	public String getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getPhanQuyen() {
		return phanQuyen;
	}
	public void setPhanQuyen(String phanQuyen) {
		this.phanQuyen = phanQuyen;
	}
	public QuanLyNhanVienTable(int stt, String maNhanVien, String tenNhanVien, String ngaySinh, String soDienThoai,
			String soCMND, String gioiTinh, String phanQuyen) {
		super();
		this.stt = stt;
		this.maNhanVien = maNhanVien;
		this.tenNhanVien = tenNhanVien;
		this.ngaySinh = ngaySinh;
		this.soDienThoai = soDienThoai;
		this.soCMND = soCMND;
		this.gioiTinh = gioiTinh;
		this.phanQuyen = phanQuyen;
	}
	public QuanLyNhanVienTable() {
		super();
	}
	@Override
	public String toString() {
		return "QuanLyNhanVienTable [stt=" + stt + ", maNhanVien=" + maNhanVien + ", tenNhanVien=" + tenNhanVien
				+ ", ngaySinh=" + ngaySinh + ", soDienThoai=" + soDienThoai + ", soCMND=" + soCMND + ", gioiTinh="
				+ gioiTinh + ", phanQuyen=" + phanQuyen + "]";
	}
	
	
}
