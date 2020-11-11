package entity;

import java.util.HashMap;
import java.util.Map;

public enum PhanQuyen {
	QUANLY("QUANLY"),
	NHANVIEN("NHANVIEN");
	
	private String phanQuyen;

	PhanQuyen(String string) {
		// TODO Auto-generated constructor stub
		this.phanQuyen = string;
	}
	
	public String getPhanQuyen() {
		return this.phanQuyen;
	}
	
	//****** Reverse Lookup Implementation************//
	 
    //Lookup table
    private static final Map<String, PhanQuyen> lookup = new HashMap<>();
  
    //Populate the lookup table on loading time
    static
    {
        for(PhanQuyen env : PhanQuyen.values())
        {
            lookup.put(env.getPhanQuyen(), env);
        }
    }
  
    //This method can be used for reverse lookup purpose
    public static PhanQuyen get(String url) 
    {
        return lookup.get(url);
    }
}
