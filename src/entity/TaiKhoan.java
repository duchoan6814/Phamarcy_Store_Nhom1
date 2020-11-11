package entity;

public class TaiKhoan {
	private String tenDangNhap;
	private String matKhau;
	private PhanQuyen phanQuyen;
	public String getTenDangNhap() {
		return tenDangNhap;
	}
	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	public PhanQuyen getPhanQuyen() {
		return phanQuyen;
	}
	public void setPhanQuyen(PhanQuyen phanQuyen) {
		this.phanQuyen = phanQuyen;
	}
	public TaiKhoan(String tenDangNhap, String matKhau, PhanQuyen phanQuyen) {
		super();
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
		this.phanQuyen = phanQuyen;
	}
	public TaiKhoan() {
		super();
	}
	@Override
	public String toString() {
		return "TaiKhoan [tenDangNhap=" + tenDangNhap + ", matKhau=" + matKhau + ", phanQuyen=" + phanQuyen + "]";
	}
	
}
