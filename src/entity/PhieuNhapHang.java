package entity;

import java.sql.Timestamp;
import java.util.ArrayList;

public class PhieuNhapHang {
	private String id;
	private Timestamp thoiGianLap;
	private QuanLy quanLy;
	private ArrayList<LoThuoc> dsLoThuoc;
	
	public QuanLy getQuanLy() {
		return quanLy;
	}
	public void setQuanLy(QuanLy quanLy) {
		this.quanLy = quanLy;
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
	public ArrayList<LoThuoc> getDsLoThuoc() {
		return dsLoThuoc;
	}
	public void setDsLoThuoc(ArrayList<LoThuoc> dsLoThuoc) {
		this.dsLoThuoc = dsLoThuoc;
	}
	
	public PhieuNhapHang(String id, Timestamp thoiGianLap, QuanLy quanLy, ArrayList<LoThuoc> dsLoThuoc) {
		super();
		this.id = id;
		this.thoiGianLap = thoiGianLap;
		this.quanLy = quanLy;
		this.dsLoThuoc = dsLoThuoc;
	}
	
	public PhieuNhapHang(String id) {
		super();
		this.id = id;
		this.dsLoThuoc = new ArrayList<LoThuoc>();
	}
	public PhieuNhapHang() {
		super();
		this.dsLoThuoc = new ArrayList<LoThuoc>();
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
		PhieuNhapHang other = (PhieuNhapHang) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PhieuNhapHang [id=" + id + ", thoiGianLap=" + thoiGianLap + ", quanLy=" + quanLy + ", dsLoThuoc="
				+ dsLoThuoc + "]";
	}
	public boolean themLoThuoc(LoThuoc loThuoc) {
		if(this.dsLoThuoc.contains(loThuoc))		
			return false;
		this.dsLoThuoc.add(loThuoc);
		return true;
	}
	
	public boolean xoaLoThuoc(String id) {
		for (LoThuoc loThuoc : dsLoThuoc) {
			if (loThuoc.getThuoc().getId().equals(id)) {
				dsLoThuoc.remove(loThuoc);
				return true;
			}
		}
		return false;
	}
	
	public LoThuoc timKiemLoThuoc(String id) {
		for (LoThuoc loThuoc : dsLoThuoc) {
			if (loThuoc.getThuoc().getId().equals(id)) {
				return loThuoc;
			}
		}
		return null;
	}
	
	public double tinhTongTienNhapHang() {
		double tongTien = 0;
		for (LoThuoc loThuoc : dsLoThuoc) {
			tongTien += loThuoc.tinhTongTienLoThuoc();
		}
		return tongTien;
	}

}
