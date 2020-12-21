package entity;

import java.sql.Timestamp;
import java.util.ArrayList;

public class PhieuHuyHang {
	private String id;
	private Timestamp thoiGianLap;
	private NhanVienBanThuoc quanLy;
	private ArrayList<LoThuoc> dsLoThuoc;
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
	public NhanVienBanThuoc getQuanLy() {
		return quanLy;
	}
	public void setQuanLy(NhanVienBanThuoc nhanVienBanThuoc) {
		this.quanLy = quanLy;
	}
	public ArrayList<LoThuoc> getDsLoThuoc() {
		return dsLoThuoc;
	}
	public void setDsLoThuoc(ArrayList<LoThuoc> dsLoThuoc) {
		this.dsLoThuoc = dsLoThuoc;
	}
	public PhieuHuyHang(String id, Timestamp thoiGianLap, QuanLy quanLy, ArrayList<LoThuoc> dsLoThuoc) {
		super();
		this.id = id;
		this.thoiGianLap = thoiGianLap;
		this.quanLy = quanLy;
		this.dsLoThuoc = dsLoThuoc;
	}
	public PhieuHuyHang() {
		super();
		this.dsLoThuoc = new ArrayList<>();
	}
	public PhieuHuyHang(String id) {
		super();
		this.id = id;
		this.dsLoThuoc = new ArrayList<>();
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
		PhieuHuyHang other = (PhieuHuyHang) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PhieuHuyHang [id=" + id + ", thoiGianLap=" + thoiGianLap + ", quanLy=" + quanLy + ", dsLoThuoc="
				+ dsLoThuoc + "]";
	}

	public boolean themLoThuoc(LoThuoc loThuoc) {
		if (this.getDsLoThuoc().contains(loThuoc)) {
			return false;
		}
		this.getDsLoThuoc().add(loThuoc);
		return true;
	}

	public boolean xoaLoThuoc(String id) {
		for (LoThuoc loThuoc : dsLoThuoc) {
			if (loThuoc.getThuoc().getId().equals(id)) {
				if (dsLoThuoc.remove(loThuoc)) {
					return true;
				}
				return false;
			}
		}
		return false;
	}
	
	public void xoaLoThuoc(int index) {
		this.dsLoThuoc.remove(index);
	}
	
	public LoThuoc timKiemLoThuoc(String id) {
		for (LoThuoc loThuoc : dsLoThuoc) {
			if (loThuoc.getThuoc().getId().equals(id)) {
				return loThuoc;
			}
		}
		return null;
	}
	
	public double tinhTongTienHuyHang() {
		double tongTien = 0;
		for (LoThuoc loThuoc : dsLoThuoc) {
			tongTien += loThuoc.tinhGiaTriLoThuocConLai();
		}
		return tongTien;
	}
}
