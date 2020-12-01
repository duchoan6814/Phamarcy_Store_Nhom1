package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import entity.NhanVienBanThuoc;
import entity.QuanLy;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class KhoHangControl implements Initializable {
	private NhanVienBanThuoc nhanVienBanThuoc;
	
	public TabPane tabKhoHang;
	public Tab tabNhapThuoc;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		setContentNhapThuoc();
	}

	private void setContentNhapThuoc() {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ThemThuoc.fxml"));
		NhapThuocControl nhapThuocControl = new NhapThuocControl();
		nhapThuocControl.setNhanVienBanThuoc(nhanVienBanThuoc);
		loader.setController(nhapThuocControl);
		try {
			tabNhapThuoc.setContent(loader.load());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public NhanVienBanThuoc getNhanVienBanThuoc() {
		return nhanVienBanThuoc;
	}

	public void setNhanVienBanThuoc(NhanVienBanThuoc nhanVienBanThuoc) {
		this.nhanVienBanThuoc = nhanVienBanThuoc;
	}



}
