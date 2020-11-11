package entity;

public class LoaiThuoc {
	private String id, tenLoai, moTa;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTenLoai() {
		return tenLoai;
	}

	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public LoaiThuoc(String id, String tenLoai, String moTa) {
		super();
		this.id = id;
		this.tenLoai = tenLoai;
		this.moTa = moTa;
	}

	public LoaiThuoc() {
		super();
	}

	public LoaiThuoc(String id) {
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
		LoaiThuoc other = (LoaiThuoc) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LoaiThuoc [id=" + id + ", tenLoai=" + tenLoai + ", moTa=" + moTa + "]";
	}
	
	
}
