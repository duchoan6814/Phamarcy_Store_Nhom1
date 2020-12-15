package entity;

public class NhaCungCap {
	private String id, tenNhaCungCap, soDienThoai, email, trangChu;
	private String diaChi;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTenNhaCungCap() {
		return tenNhaCungCap;
	}
	public void setTenNhaCungCap(String tenNhaCungCap) {
		this.tenNhaCungCap = tenNhaCungCap;
	}
	public String getSoDienThoai() {
		return soDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTrangChu() {
		return trangChu;
	}
	public void setTrangChu(String trangChu) {
		this.trangChu = trangChu;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public NhaCungCap(String id, String tenNhaCungCap, String soDienThoai, String email, String trangChu, String diaChi) {
		super();
		this.id = id;
		this.tenNhaCungCap = tenNhaCungCap;
		this.soDienThoai = soDienThoai;
		this.email = email;
		this.trangChu = trangChu;
		this.diaChi = diaChi;
	}
	public NhaCungCap() {
		super();
	}
	public NhaCungCap(String id) {
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
		NhaCungCap other = (NhaCungCap) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "NhaCungCap [id=" + id + ", tenNhaCungCap=" + tenNhaCungCap + ", soDienThoai=" + soDienThoai + ", fax="
				+ email + ", trangChu=" + trangChu + ", diaChi=" + diaChi + "]";
	}


}
