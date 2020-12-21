package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import GUI.control.PhieuHuyControl;
import GUI.control.QuanLyLoaiThuocControl;
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
	public Tab tabNCC;
	public Tab tabLoaiThuoc;
	public Tab tabHuyThuoc;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		setContentNhapThuoc();
		setContentThuoc();
		setContenPhieuNhapThuoc();
		setcontentQLNhaCC();
		setContentQLLoaiThuoc();
		setContentHuyThuoc();
	}

	private void setContentHuyThuoc() {
		// TODO Auto-generated method stub
		tabHuyThuoc.setOnSelectionChanged(e -> {
			if (tabHuyThuoc.isSelected()) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("HuyThuoc.fxml"));
				PhieuHuyControl control = new PhieuHuyControl(nhanVienBanThuoc);
				loader.setController(control);
				try {
					tabHuyThuoc.setContent(loader.load());
				} catch (IOException q) {
					// TODO Auto-generated catch block
					q.printStackTrace();
				}
			}
		});
	}

	private void setContentQLLoaiThuoc() {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader(getClass().getResource("QuanLyLoaiThuoc.fxml"));
		QuanLyLoaiThuocControl control = new QuanLyLoaiThuocControl();
		loader.setController(control);
		try {
			tabLoaiThuoc.setContent(loader.load());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setcontentQLNhaCC() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("QuanLyNCC.fxml"));
		QuanLyNCCConTrol control = new QuanLyNCCConTrol();
		loader.setController(control);
		try {
			tabNCC.setContent(loader.load());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
