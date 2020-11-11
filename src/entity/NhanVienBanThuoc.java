package entity;

import java.sql.Date;
import java.util.Arrays;

public class NhanVienBanThuoc {
	private String id, hoTenDem, ten, soDienThoai, soCMND;
	private Date ngaySinh;
	private boolean gioiTinh;
	private String diaChi;
	private byte[] avatar;
	private TaiKhoan taiKhoan;
	
	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}
	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}
	public byte[] getAvatar() {
		return avatar;
	}
	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
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
	public String getSoCMND() {
		return soCMND;
	}
	public void setSoCMND(String soCMND) {
		this.soCMND = soCMND;
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
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public NhanVienBanThuoc(String id, String hoTenDem, String ten, String soDienThoai, String soCMND, Date ngaySinh,
			boolean gioiTinh, String diaChi, byte[] avatar, TaiKhoan taiKhoan) {
		super();
		this.id = id;
		this.hoTenDem = hoTenDem;
		this.ten = ten;
		this.soDienThoai = soDienThoai;
		this.soCMND = soCMND;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
		this.avatar = avatar;
		this.taiKhoan = taiKhoan;
	}
	public NhanVienBanThuoc() {
		super();
	}
	public NhanVienBanThuoc(String id) {
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
		NhanVienBanThuoc other = (NhanVienBanThuoc) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "NhanVienBanThuoc [id=" + id + ", hoTenDem=" + hoTenDem + ", ten=" + ten + ", soDienThoai=" + soDienThoai
				+ ", soCMND=" + soCMND + ", ngaySinh=" + ngaySinh + ", gioiTinh=" + gioiTinh + ", diaChi=" + diaChi
				+ ", avatar=" + Arrays.toString(avatar) + ", taiKhoan=" + taiKhoan + "]";
	}

	

}
