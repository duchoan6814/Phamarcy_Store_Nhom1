package entity;

public class Thuoc {
	private String id, tenThuoc, moTa, donViTinh, dangBaoChe, quyCachDongGoi;
	private int hanSuDung, tonKho;
	private double gia, thue;
	private NhaCungCap nhaCungCap;
	private LoaiThuoc loaiThuoc;
	private String nuocSanXuat;

	public String getNuocSanXuat() {
		return nuocSanXuat;
	}
	public void setNuocSanXuat(String nuocSanXuat) {
		this.nuocSanXuat = nuocSanXuat;
	}
	public double getThue() {
		return thue;
	}
	public void setThue(double thue) {
		this.thue = thue;
	}
	public int getTonKho() {
		return tonKho;
	}
	public void setTonKho(int tonKho) {
		this.tonKho = tonKho;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTenThuoc() {
		return tenThuoc;
	}
	public void setTenThuoc(String tenThuoc) {
		this.tenThuoc = tenThuoc;
	}
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	public String getDonViTinh() {
		return donViTinh;
	}
	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}
	public String getDangBaoChe() {
		return dangBaoChe;
	}
	public void setDangBaoChe(String dangBaoChe) {
		this.dangBaoChe = dangBaoChe;
	}
	public String getQuyCachDongGoi() {
		return quyCachDongGoi;
	}
	public void setQuyCachDongGoi(String quyCachDongGoi) {
		this.quyCachDongGoi = quyCachDongGoi;
	}
	public int getHanSuDung() {
		return hanSuDung;
	}
	public void setHanSuDung(int hanSuDung) {
		this.hanSuDung = hanSuDung;
	}
	public double getGia() {
		return gia;
	}
	public void setGia(double gia) {
		this.gia = gia;
	}
	public NhaCungCap getNhaCungCap() {
		return nhaCungCap;
	}
	public void setNhaCungCap(NhaCungCap nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}
	public LoaiThuoc getLoaiThuoc() {
		return loaiThuoc;
	}
	public void setLoaiThuoc(LoaiThuoc loaiThuoc) {
		this.loaiThuoc = loaiThuoc;
	}
	
	public Thuoc(String id, String tenThuoc, String moTa, String donViTinh, String dangBaoChe, String quyCachDongGoi,
			int hanSuDung, int tonKho, double gia, double thue, NhaCungCap nhaCungCap, LoaiThuoc loaiThuoc,
			String nuocSanXuat) {
		super();
		this.id = id;
		this.tenThuoc = tenThuoc;
		this.moTa = moTa;
		this.donViTinh = donViTinh;
		this.dangBaoChe = dangBaoChe;
		this.quyCachDongGoi = quyCachDongGoi;
		this.hanSuDung = hanSuDung;
		this.tonKho = tonKho;
		this.gia = gia;
		this.thue = thue;
		this.nhaCungCap = nhaCungCap;
		this.loaiThuoc = loaiThuoc;
		this.nuocSanXuat = nuocSanXuat;
	}
	public Thuoc() {
		super();
	}
	public Thuoc(String id) {
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
		Thuoc other = (Thuoc) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Thuoc [id=" + id + ", tenThuoc=" + tenThuoc + ", moTa=" + moTa + ", donViTinh=" + donViTinh
				+ ", dangBaoChe=" + dangBaoChe + ", quyCachDongGoi=" + quyCachDongGoi + ", hanSuDung=" + hanSuDung
				+ ", tonKho=" + tonKho + ", gia=" + gia + ", thue=" + thue + ", nhaCungCap=" + nhaCungCap
				+ ", loaiThuoc=" + loaiThuoc + ", nuocSanXuat=" + nuocSanXuat + "]";
	}
	
	

}
