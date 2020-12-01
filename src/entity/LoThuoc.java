package entity;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class LoThuoc {
	private Thuoc thuoc;
	private Date ngaySanXuat;
	private int soLuong;
	private int soLuongConLai;
	

	public int getSoLuongConLai() {
		return soLuongConLai;
	}
	public void setSoLuongConLai(int soLuongConLai) {
		this.soLuongConLai = soLuongConLai;
	}
	public Thuoc getThuoc() {
		return thuoc;
	}
	public void setThuoc(Thuoc thuoc) {
		this.thuoc = thuoc;
	}
	public Date getNgaySanXuat() {
		return ngaySanXuat;
	}
	public void setNgaySanXuat(Date ngaySanXuat) {
		this.ngaySanXuat = ngaySanXuat;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	
	public LoThuoc(Thuoc thuoc, Date ngaySanXuat, int soLuong, int soLuongConLai) {
		super();
		this.thuoc = thuoc;
		this.ngaySanXuat = ngaySanXuat;
		this.soLuong = soLuong;
		this.soLuongConLai = soLuongConLai;
	}
	public LoThuoc() {
		super();
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
		LoThuoc other = (LoThuoc) obj;
		if (thuoc == null) {
			if (other.thuoc != null)
				return false;
		} else if (!thuoc.equals(other.thuoc))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "LoThuoc [thuoc=" + thuoc + ", ngaySanXuat=" + ngaySanXuat + ", soLuong=" + soLuong + ", soLuongConLai="
				+ soLuongConLai + "]";
	}
	public java.util.Date addDays(java.util.Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return (java.util.Date) cal.getTime();
	}

	public Date tinhNgayHetHan() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		int hanSuDung = thuoc.getHanSuDung() * 30;
		try {
			java.util.Date date = sdf.parse(this.getNgaySanXuat().toString());
			java.util.Date newDate = addDays(date, hanSuDung);
			Date ngayHetHan = new Date(newDate.getTime());
			return ngayHetHan;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public double tinhTongTienLoThuoc() {
		return this.getThuoc().getGia() * this.getSoLuong();
	}

}
