package entity;

public class ChiTietHoaDon {
	private int soLuong;
	private Thuoc thuoc;
	private double giaBan;




	@Override
	public String toString() {
		return "ChiTietHoaDon [soLuong=" + soLuong + ", thuoc=" + thuoc + ", giaBan=" + giaBan + "]";
	}




	public ChiTietHoaDon() {
		super();
	}




	public ChiTietHoaDon(int soLuong, Thuoc thuoc, double giaBan) {
		super();
		this.soLuong = soLuong;
		this.thuoc = thuoc;
		this.giaBan = giaBan;
	}




	public int getSoLuong() {
		return soLuong;
	}




	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}




	public Thuoc getThuoc() {
		return thuoc;
	}




	public void setThuoc(Thuoc thuoc) {
		this.thuoc = thuoc;
	}




	public double getGiaBan() {
		return giaBan;
	}




	public void setGiaBan(double giaBan) {
		this.giaBan = giaBan;
	}




	public double tinhTongTienChuaThue() {
		return this.getSoLuong()*this.getGiaBan();
	}
	
	public double tinhThueChiTietHoaDon() {
		return tinhThueChiTietHoaDon()*this.getThuoc().getThue();
	}
	
	public double tinhTongTienBaoGomThue() {
		return this.tinhTongTienChuaThue() +  this.tinhThueChiTietHoaDon();
	}

}
