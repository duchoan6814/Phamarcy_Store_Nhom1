package entity;

import java.sql.Date;

public class KhachHang {
	private String id, hoTenDem, ten, soDienThoai;
	private Date ngaySinh;
	private boolean gioiTinh;
	private String diaChi;
	private double dienTichLuy;
	private LoaiKhachHang loaiKhachHang;
	
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	
	
	public String getDiaChi() {
		return diaChi;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHoTenDem() {
		return hoTenDem;
	}
	public void setHoTenDem(String hoTenDem) {
		this.hoTenDem = hoTenDem;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public String getSoDienThoai() {
		return soDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
	public Date getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public boolean isGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public double getDienTichLuy() {
		return dienTichLuy;
	}
	public void setDienTichLuy(double dienTichLuy) {
		this.dienTichLuy = dienTichLuy;
	}
	public LoaiKhachHang getLoaiKhachHang() {
		return loaiKhachHang;
	}
	public void setLoaiKhachHang(LoaiKhachHang loaiKhachHang) {
		this.loaiKhachHang = loaiKhachHang;
	}
	public KhachHang(String id, String hoTenDem, String ten, String soDienThoai, Date ngaySinh, boolean gioiTinh,
			String diaChi, double dienTichLuy, LoaiKhachHang loaiKhachHang) {
		super();
		this.id = id;
		this.hoTenDem = hoTenDem;
		this.ten = ten;
		this.soDienThoai = soDienThoai;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
		this.dienTichLuy = dienTichLuy;
		this.loaiKhachHang = loaiKhachHang;
	}
	public KhachHang() {
		super();
	}
	public KhachHang(String id) {
		super();
		this.id = id;
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
		KhachHang other = (KhachHang) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "KhachHang [id=" + id + ", hoTenDem=" + hoTenDem + ", ten=" + ten + ", soDienThoai=" + soDienThoai
				+ ", ngaySinh=" + ngaySinh + ", gioiTinh=" + gioiTinh + ", diaChi=" + diaChi + ", dienTichLuy="
				+ dienTichLuy + ", loaiKhachHang=" + loaiKhachHang + "]";
	}



}
