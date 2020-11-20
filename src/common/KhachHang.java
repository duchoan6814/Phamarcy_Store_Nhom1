package common;

public class KhachHang {
	private String maKhachHang, hoKhachHang, tenKhachHang, ngaySinh, gioiTinh, dienThoai, loaiKhachHang, diaChi, diem;
	private int stt;
	public String getMaKhachHang() {
		return maKhachHang;
	}
	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}
	public String getHoKhachHang() {
		return hoKhachHang;
	}
	public void setHoKhachHang(String hoKhachHang) {
		this.hoKhachHang = hoKhachHang;
	}
	public String getTenKhachHang() {
		return tenKhachHang;
	}
	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}
	public String getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getDienThoai() {
		return dienThoai;
	}
	public void setDienThoai(String dienThoai) {
		this.dienThoai = dienThoai;
	}
	public String getLoaiKhachHang() {
		return loaiKhachHang;
	}
	public void setLoaiKhachHang(String loaiKhachHang) {
		this.loaiKhachHang = loaiKhachHang;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getDiem() {
		return diem;
	}
	public void setDiem(String diem) {
		this.diem = diem;
	}
	public int getStt() {
		return stt;
	}
	public void setStt(int stt) {
		this.stt = stt;
	}
	public KhachHang(String maKhachHang, String hoKhachHang, String tenKhachHang, String ngaySinh, String gioiTinh,
			String dienThoai, String loaiKhachHang, String diaChi, String diem, int stt) {
		super();
		this.maKhachHang = maKhachHang;
		this.hoKhachHang = hoKhachHang;
		this.tenKhachHang = tenKhachHang;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.dienThoai = dienThoai;
		this.loaiKhachHang = loaiKhachHang;
		this.diaChi = diaChi;
		this.diem = diem;
		this.stt = stt;
	}
	
	
	
}
