package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import entity.NhanVienBanThuoc;
import entity.QuanLy;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class KhoHangControl implements Initializable {
	private NhanVienBanThuoc nhanVienBanThuoc;
	
	public TabPane tabKhoHang;
	public Tab tabNhapThuoc;
	public Tab tabThuoc;
	public Tab tabPhieuNhap;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		setContentNhapThuoc();
		setContentThuoc();
		setContenPhieuNhapThuoc();
	}

	private void setContenPhieuNhapThuoc() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Quanlyphieunhap.fxml"));
		NhapThuocControl nhapThuocControl = new NhapThuocControl();
		QuanlyphieunhapControl control = new QuanlyphieunhapControl();
		loader.setController(control);
		try {
			tabPhieuNhap.setContent(loader.load());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	
	private void setContentThuoc() {
		tabThuoc.setOnSelectionChanged(e -> {
			if (tabThuoc.isSelected()) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("PnlThuoc2.fxml"));
				Thuoc2Control control = new Thuoc2Control();
				loader.setController(control);
				
				try {
					tabThuoc.setContent(loader.load());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
	}

	public NhanVienBanThuoc getNhanVienBanThuoc() {
		return nhanVienBanThuoc;
	}

	public void setNhanVienBanThuoc(NhanVienBanThuoc nhanVienBanThuoc) {
		this.nhanVienBanThuoc = nhanVienBanThuoc;
	}



}
