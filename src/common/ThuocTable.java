package common;

public class ThuocTable {
	private int stt, tonKho;
	private String maThuoc, tenThuoc, donViTinh, gia, hanSuDung, loai, nuocSX, congTy;
	public int getStt() {
		return stt;
	}
	public void setStt(int stt) {
		this.stt = stt;
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
	public int getTonKho() {
		return tonKho;
	}
	public void setTonKho(int tonKho) {
		this.tonKho = tonKho;
	}
	public String getHanSuDung() {
		return hanSuDung;
	}
	public void setHanSuDung(String hanSuDung) {
		this.hanSuDung = hanSuDung;
	}
	public String getLoai() {
		return loai;
	}
	public void setLoai(String loai) {
		this.loai = loai;
	}
	public String getNuocSX() {
		return nuocSX;
	}
	public void setNuocSX(String nuocSX) {
		this.nuocSX = nuocSX;
	}
	public String getCongTy() {
		return congTy;
	}
	public void setCongTy(String congTy) {
		this.congTy = congTy;
	}
	public ThuocTable(int stt, String maThuoc, String tenThuoc, String donViTinh, String gia, int tonKho,
			String hanSuDung, String loai, String nuocSX, String congTy) {
		super();
		this.stt = stt;
		this.maThuoc = maThuoc;
		this.tenThuoc = tenThuoc;
		this.donViTinh = donViTinh;
		this.gia = gia;
		this.tonKho = tonKho;
		this.hanSuDung = hanSuDung;
		this.loai = loai;
		this.nuocSX = nuocSX;
		this.congTy = congTy;
	}
	public ThuocTable() {
		super();
	}
	
	
}
