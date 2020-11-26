package common;

public class HoaDonTable {
	private int STT;
	private String maHoaDon, ngayLap, tenNhanVien, tenKhachHang, thanhTien, diemSuDung, phaiTra, tichLuy;
	public int getSTT() {
		return STT;
	}
	public void setSTT(int sTT) {
		STT = sTT;
	}
	public String getMaHoaDon() {
		return maHoaDon;
	}
	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}
	public String getNgayLap() {
		return ngayLap;
	}
	public void setNgayLap(String ngayLap) {
		this.ngayLap = ngayLap;
	}
	public String getTenNhanVien() {
		return tenNhanVien;
	}
	public void setTenNhanVien(String tenNhanVien) {
		this.tenNhanVien = tenNhanVien;
	}
	public String getTenKhachHang() {
		return tenKhachHang;
	}
	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}
	public String getThanhTien() {
		return thanhTien;
	}
	public void setThanhTien(String thanhTien) {
		this.thanhTien = thanhTien;
	}
	public String getDiemSuDung() {
		return diemSuDung;
	}
	public void setDiemSuDung(String diemSuDung) {
		this.diemSuDung = diemSuDung;
	}
	public String getPhaiTra() {
		return phaiTra;
	}
	public void setPhaiTra(String phaiTra) {
		this.phaiTra = phaiTra;
	}
	public String getTichLuy() {
		return tichLuy;
	}
	public void setTichLuy(String tichLuy) {
		this.tichLuy = tichLuy;
	}
	public HoaDonTable(int sTT, String maHoaDon, String ngayLap, String tenNhanVien, String tenKhachHang,
			String thanhTien, String diemSuDung, String phaiTra, String tichLuy) {
		super();
		STT = sTT;
		this.maHoaDon = maHoaDon;
		this.ngayLap = ngayLap;
		this.tenNhanVien = tenNhanVien;
		this.tenKhachHang = tenKhachHang;
		this.thanhTien = thanhTien;
		this.diemSuDung = diemSuDung;
		this.phaiTra = phaiTra;
		this.tichLuy = tichLuy;
	}
	public HoaDonTable() {
		super();
	}
	@Override
	public String toString() {
		return "HoaDonTable [STT=" + STT + ", maHoaDon=" + maHoaDon + ", ngayLap=" + ngayLap + ", tenNhanVien="
				+ tenNhanVien + ", tenKhachHang=" + tenKhachHang + ", thanhTien=" + thanhTien + ", diemSuDung="
				+ diemSuDung + ", phaiTra=" + phaiTra + ", tichLuy=" + tichLuy + "]";
	}
	
	
}
