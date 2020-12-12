package common;

public class NhanVienTable {
	private int STT, soHoaDon;
	private String hoTen, doanhThu, maNhanVien;
	public int getSTT() {
		return STT;
	}
	public void setSTT(int sTT) {
		STT = sTT;
	}
	public int getSoHoaDon() {
		return soHoaDon;
	}
	public void setSoHoaDon(int soHoaDon) {
		this.soHoaDon = soHoaDon;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public String getDoanhThu() {
		return doanhThu;
	}
	public void setDoanhThu(String doanhThu) {
		this.doanhThu = doanhThu;
	}
	public String getMaNhanVien() {
		return maNhanVien;
	}
	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}
	public NhanVienTable(int sTT, int soHoaDon, String hoTen, String doanhThu, String maNhanVien) {
		super();
		STT = sTT;
		this.soHoaDon = soHoaDon;
		this.hoTen = hoTen;
		this.doanhThu = doanhThu;
		this.maNhanVien = maNhanVien;
	}
	public NhanVienTable() {
		super();
	}
	@Override
	public String toString() {
		return "NhanVienTable [STT=" + STT + ", soHoaDon=" + soHoaDon + ", hoTen=" + hoTen + ", doanhThu=" + doanhThu
				+ ", maNhanVien=" + maNhanVien + "]";
	}
	
	
	
}	
