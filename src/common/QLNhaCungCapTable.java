package common;

public class QLNhaCungCapTable  {
	private int STT;
	private String maNCC;
	private String tenNCC;
	private String sdt;
	private String fax;
	private String trangChu;
	private String diaChi;
	public int getSTT() {
		return STT;
	}
	public void setSTT(int sTT) {
		STT = sTT;
	}
	public String getMaNCC() {
		return maNCC;
	}
	public void setMaNCC(String maNCC) {
		this.maNCC = maNCC;
	}
	public String getTenNCC() {
		return tenNCC;
	}
	public void setTenNCC(String tenNCC) {
		this.tenNCC = tenNCC;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
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
	public QLNhaCungCapTable(int sTT, String maNCC, String tenNCC, String sdt, String fax, String trangChu,
			String diaChi) {
		super();
		STT = sTT;
		this.maNCC = maNCC;
		this.tenNCC = tenNCC;
		this.sdt = sdt;
		this.fax = fax;
		this.trangChu = trangChu;
		this.diaChi = diaChi;
	}
	@Override
	public String toString() {
		return "QLNhaCungCapTable [STT=" + STT + ", maNCC=" + maNCC + ", tenNCC=" + tenNCC + ", sdt=" + sdt + ", fax="
				+ fax + ", trangChu=" + trangChu + ", diaChi=" + diaChi + "]";
	}
	
}
