package common;

public class PhieuNhapTable {
	private int STT;
	private String maPhieu;
	private String ngayNhap;
	private String tongTien;
	private String quanLy;
	public int getSTT() {
		return STT;
	}
	public void setSTT(int sTT) {
		STT = sTT;
	}
	public String getMaPhieu() {
		return maPhieu;
	}
	public void setMaPhieu(String maPhieu) {
		this.maPhieu = maPhieu;
	}
	public String getNgayNhap() {
		return ngayNhap;
	}
	public void setNgayNhap(String ngayNhap) {
		this.ngayNhap = ngayNhap;
	}
	public String getTongTien() {
		return tongTien;
	}
	public void setTongTien(String tongTien) {
		this.tongTien = tongTien;
	}
	public String getQuanLy() {
		return quanLy;
	}
	public void setQuanLy(String quanLy) {
		this.quanLy = quanLy;
	}
	public PhieuNhapTable(int sTT, String maPhieu, String ngayNhap, String tongTien, String quanLy) {
		super();
		STT = sTT;
		this.maPhieu = maPhieu;
		this.ngayNhap = ngayNhap;
		this.tongTien = tongTien;
		this.quanLy = quanLy;
	}
	@Override
	public String toString() {
		return "PhieuNhapTable [STT=" + STT + ", maPhieu=" + maPhieu + ", ngayNhap=" + ngayNhap + ", tongTien="
				+ tongTien + ", quanLy=" + quanLy + "]";
	}

	
	
}
