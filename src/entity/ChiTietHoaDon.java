package entity;

public class ChiTietHoaDon {
	private int soLuong;
	private Thuoc thuoc;
	private double giaBan;




	@Override
	public String toString() {
		return "ChiTietHoaDon [soLuong=" + soLuong + ", thuoc=" + thuoc + ", giaBan=" + this.getGiaBan() + "]";
	}




	public ChiTietHoaDon() {
		super();
	}




	public ChiTietHoaDon(int soLuong, Thuoc thuoc) {
		super();
		this.soLuong = soLuong;
		this.thuoc = thuoc;
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
		return tinhGiaBan();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((thuoc == null) ? 0 : thuoc.hashCode());
		return result;
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChiTietHoaDon other = (ChiTietHoaDon) obj;
		if (thuoc == null) {
			if (other.thuoc != null)
				return false;
		} else if (!thuoc.equals(other.thuoc))
			return false;
		return true;
	}




	public double tinhTongTienChuaThue() {
		return this.getSoLuong()*this.getGiaBan();
	}

	public double tinhThueChiTietHoaDon() {
		return this.soLuong*this.getThuoc().getThue()*this.getGiaBan();
	}

	public double tinhTongTienBaoGomThue() {
		return this.tinhTongTienChuaThue() +  this.tinhThueChiTietHoaDon();
	}

	public double tinhGiaBan() {
		return this.getThuoc().getGia()*1.2;
	}

}
