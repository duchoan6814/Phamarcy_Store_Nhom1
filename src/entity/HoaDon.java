package entity;

import java.sql.Timestamp;
import java.util.ArrayList;

public class HoaDon {
	private String id;
	private Timestamp thoiGianLap;
	private ArrayList<ChiTietHoaDon> dsChiTietHoaDon;
	private NhanVienBanThuoc nhanVienBanThuoc;

	public NhanVienBanThuoc getNhanVienBanThuoc() {
		return nhanVienBanThuoc;
	}
	public void setNhanVienBanThuoc(NhanVienBanThuoc nhanVienBanThuoc) {
		this.nhanVienBanThuoc = nhanVienBanThuoc;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Timestamp getThoiGianLap() {
		return thoiGianLap;
	}
	public void setThoiGianLap(Timestamp thoiGianLap) {
		this.thoiGianLap = thoiGianLap;
	}
	public ArrayList<ChiTietHoaDon> getDsChiTietHoaDon() {
		return dsChiTietHoaDon;
	}
	public void setDsChiTietHoaDon(ArrayList<ChiTietHoaDon> dsChiTietHoaDon) {
		this.dsChiTietHoaDon = dsChiTietHoaDon;
	}

	public HoaDon(String id, Timestamp thoiGianLap, ArrayList<ChiTietHoaDon> dsChiTietHoaDon,
			NhanVienBanThuoc nhanVienBanThuoc) {
		super();
		this.id = id;
		this.thoiGianLap = thoiGianLap;
		this.dsChiTietHoaDon = dsChiTietHoaDon;
		this.nhanVienBanThuoc = nhanVienBanThuoc;
	}
	public HoaDon() {
		super();
		this.dsChiTietHoaDon = new ArrayList<ChiTietHoaDon>();
	}
	public HoaDon(String id) {
		super();
		this.id = id;
		this.dsChiTietHoaDon = new ArrayList<ChiTietHoaDon>();
	}

	@Override
	public String toString() {
		return "HoaDon [id=" + id + ", thoiGianLap=" + thoiGianLap + ", dsChiTietHoaDon=" + dsChiTietHoaDon
				+ ", nhanVienBanThuoc=" + nhanVienBanThuoc + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		HoaDon other = (HoaDon) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public double tinhTienHoaDonChuaThue() {
		double tongTien = 0;
		for (ChiTietHoaDon chiTietHoaDon : dsChiTietHoaDon) {
			tongTien += chiTietHoaDon.tinhTongTienChuaThue();
		}
		return tongTien;
	}

	public double tinhThueHoaDon() {
		double tongThue = 0;
		for (ChiTietHoaDon chiTietHoaDon : dsChiTietHoaDon) {
			tongThue += chiTietHoaDon.tinhThueChiTietHoaDon();
		}
		return tongThue;
	}

	public double tinhTienHoaDonBaoGomThue() {
		return this.tinhThueHoaDon() + this.tinhTienHoaDonChuaThue();
	}

	public boolean themChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
		if (this.getDsChiTietHoaDon().contains(chiTietHoaDon)) {
			return false;
		}
		this.getDsChiTietHoaDon().add(chiTietHoaDon);
		return true;
	}

	public boolean xoaChiTietHoaDon(String id) {
		for (ChiTietHoaDon chiTietHoaDon : dsChiTietHoaDon) {
			if (chiTietHoaDon.getThuoc().getId().equals(id)) {
				if (this.getDsChiTietHoaDon().remove(chiTietHoaDon)) {
					return true;
				}
				return false;
			}
		}
		return false;
	}

	public boolean suaSoLuongThuoc(String id, int soLuongThuoc) {
		for (ChiTietHoaDon chiTietHoaDon : dsChiTietHoaDon) {
			if (chiTietHoaDon.getThuoc().getId().equals(id)) {
				chiTietHoaDon.setSoLuong(soLuongThuoc);
				return true;
			}
		}
		return false;
	}

	public ChiTietHoaDon timChiTietHoaDon(String id) {
		for (ChiTietHoaDon chiTietHoaDon : dsChiTietHoaDon) {
			if (chiTietHoaDon.getThuoc().getId().equals(id)) {
				return chiTietHoaDon;
			}
		}
		return null;
	}


}
