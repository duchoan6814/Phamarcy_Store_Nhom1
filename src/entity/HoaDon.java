package entity;

import java.sql.Timestamp;
import java.util.ArrayList;

public class HoaDon {
	private String id;
	private Timestamp thoiGianLap;
	private ArrayList<ChiTietHoaDon> dsChiTietHoaDon;
	private NhanVienBanThuoc nhanVienBanThuoc;
	private KhachHang khachHang;
	private double diemSuDung;
	private double tienPhaiTra;
	
	
	
	public double getDiemSuDung() {
		return diemSuDung;
	}
	public void setDiemSuDung(double diemSuDung) {
		this.diemSuDung = diemSuDung;
	}
	public double getTienPhaiTra() {
		return this.tinhTienPhaiTra();
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
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
			NhanVienBanThuoc nhanVienBanThuoc, KhachHang khachHang, double diemSuDung) {
		super();
		this.id = id;
		this.thoiGianLap = thoiGianLap;
		this.dsChiTietHoaDon = dsChiTietHoaDon;
		this.nhanVienBanThuoc = nhanVienBanThuoc;
		this.khachHang = khachHang;
		this.diemSuDung = diemSuDung;
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
				+ ", nhanVienBanThuoc=" + nhanVienBanThuoc + ", khachHang=" + khachHang + ", diemSuDung=" + diemSuDung
				+ ", tienPhaiTra=" + getTienPhaiTra() + "]";
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
	
	public boolean checkChiTietHoaDonTonTai(String ID) {
		for (ChiTietHoaDon chiTietHoaDon : dsChiTietHoaDon) {
			if (chiTietHoaDon.getThuoc().getId() == ID) {
				return true;
			}
		}
		return false;
	}
	
	public double tinhDiemTichLuy() {
		return 0.02 * this.tinhTienHoaDonBaoGomThue();
	}

	public double tinhTienPhaiTra() {
		return this.tinhTienHoaDonBaoGomThue() - this.getDiemSuDung();
	}

}
