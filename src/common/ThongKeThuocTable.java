package common;

public class ThongKeThuocTable {
	private int top;
	private String maThuoc, tenThuoc, loaiThuoc, donViTinh, gia, doanhThu;
	public int getTop() {
		return top;
	}
	public void setTop(int top) {
		this.top = top;
	}
	public String getMaThuoc() {
		return maThuoc;
	}
	public void setMaThuoc(String maThuoc) {
		this.maThuoc = maThuoc;
	}
	public String getTenThuoc() {
		return tenThuoc;
	}
	public void setTenThuoc(String tenThuoc) {
		this.tenThuoc = tenThuoc;
	}
	public String getLoaiThuoc() {
		return loaiThuoc;
	}
	public void setLoaiThuoc(String loaiThuoc) {
		this.loaiThuoc = loaiThuoc;
	}
	public String getDonViTinh() {
		return donViTinh;
	}
	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}
	public String getGia() {
		return gia;
	}
	public void setGia(String gia) {
		this.gia = gia;
	}
	public String getDoanhThu() {
		return doanhThu;
	}
	public void setDoanhThu(String doanhThu) {
		this.doanhThu = doanhThu;
	}
	public ThongKeThuocTable(int top, String maThuoc, String tenThuoc, String loaiThuoc, String donViTinh, String gia,
			String doanhThu) {
		super();
		this.top = top;
		this.maThuoc = maThuoc;
		this.tenThuoc = tenThuoc;
		this.loaiThuoc = loaiThuoc;
		this.donViTinh = donViTinh;
		this.gia = gia;
		this.doanhThu = doanhThu;
	}
	public ThongKeThuocTable() {
		super();
	}
	@Override
	public String toString() {
		return "ThongKeThuocTable [top=" + top + ", maThuoc=" + maThuoc + ", tenThuoc=" + tenThuoc + ", loaiThuoc="
				+ loaiThuoc + ", donViTinh=" + donViTinh + ", gia=" + gia + ", doanhThu=" + doanhThu + "]";
	}
	
	
}	
