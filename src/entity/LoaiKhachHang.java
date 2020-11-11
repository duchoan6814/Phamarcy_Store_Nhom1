package entity;

import java.util.HashMap;
import java.util.Map;

public enum LoaiKhachHang {
	MEMBER("MEMBER"),
	VIP("VIP"),
	SUPERVIP("SUPERVIP");
	
	private String loaiKhachHang;

	LoaiKhachHang(String string) {
		// TODO Auto-generated constructor stub
		this.loaiKhachHang = string;
	}
	
	public String getLoaiKhachHang() {
		return this.loaiKhachHang;
	}
	
	//****** Reverse Lookup Implementation************//
	 
    //Lookup table
    private static final Map<String, LoaiKhachHang> lookup = new HashMap<>();
  
    //Populate the lookup table on loading time
    static
    {
        for(LoaiKhachHang env : LoaiKhachHang.values())
        {
            lookup.put(env.getLoaiKhachHang(), env);
        }
    }
  
    //This method can be used for reverse lookup purpose
    public static LoaiKhachHang get(String url) 
    {
        return lookup.get(url);
    }
	
	
}
