package common;

public class ChiTietHoaDon {
	private final String maThuoc, tenThuoc, donViTinh, nuocSanXuat, loaiThuoc, donGia, tongTien;
	private final int stt, soLuong;
	
	
	
	public String getMaThuoc() {
		return maThuoc;
	}



	public String getTenThuoc() {
		return tenThuoc;
	}



	public String getDonViTinh() {
		return donViTinh;
	}



	public String getNuocSanXuat() {
		return nuocSanXuat;
	}



	public String getLoaiThuoc() {
		return loaiThuoc;
	}



	public String getDonGia() {
		return donGia;
	}



	public String getTongTien() {
		return tongTien;
	}



	public int getStt() {
		return stt;
	}



	public int getSoLuong() {
		return soLuong;
	}



	public ChiTietHoaDon(String string, String string2, String string3,
			String string4, String string5, String string6,
			String string7, int i, int j) {
		super();
		this.maThuoc = string;
		this.tenThuoc = string2;
		this.donViTinh = string3;
		this.nuocSanXuat = string4;
		this.loaiThuoc = string5;
		this.donGia = string6;
		this.tongTien = string7;
		this.stt = i;
		this.soLuong = j;
	}
	
	
	
}
