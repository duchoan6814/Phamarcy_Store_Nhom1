package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import entity.NhanVienBanThuoc;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;

public class BanHangControl implements Initializable {

	public TabPane tbsBanHang;
	public Tab tabTaoHoaDon;
	public Tab tabKhachHang;

	
	
	public Tab getTabTaoHoaDon() {
		return tabTaoHoaDon;
	}

	public void setTabTaoHoaDon(Tab tabTaoHoaDon) {
		this.tabTaoHoaDon = tabTaoHoaDon;
	}

	public Tab getTabKhachHang() {
		return tabKhachHang;
	}

	public void setTabKhachHang(Tab tabKhachHang) {
		this.tabKhachHang = tabKhachHang;
	}

	public TabPane getTbsBanHang() {
		return tbsBanHang;
	}

	public void setTbsBanHang(TabPane tbsBanHang) {
		this.tbsBanHang = tbsBanHang;
	}

	public NhanVienBanThuoc nhanVienBanThuoc;
	private KhachHangControl khachHangControl;
	private TaoHoaDonControl taoHoaDonControl;
	
	

	public KhachHangControl getKhachHangControl() {
		return khachHangControl;
	}

	public void setKhachHangControl(KhachHangControl khachHangControl) {
		this.khachHangControl = khachHangControl;
	}

	public TaoHoaDonControl getTaoHoaDonControl() {
		return taoHoaDonControl;
	}

	public void setTaoHoaDonControl(TaoHoaDonControl taoHoaDonControl) {
		this.taoHoaDonControl = taoHoaDonControl;
	}

	public BanHangControl(NhanVienBanThuoc nhanVienBanThuoc2) {
		// TODO Auto-generated constructor stub
		this.nhanVienBanThuoc = nhanVienBanThuoc2;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		setContentTabTaoHaoDon();
		setContentTabKhachHang();
	}

	private void setContentTabKhachHang() {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader(getClass().getResource("pnlKhachHang.fxml"));
		khachHangControl = new KhachHangControl();
		khachHangControl.setBanHangControl(this);
		loader.setController(khachHangControl);
		try {
			tabKhachHang.setContent(loader.load());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setContentTabTaoHaoDon() {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader(getClass().getResource("pnlTaoHoaDon.fxml"));
		taoHoaDonControl = new TaoHoaDonControl(nhanVienBanThuoc);
		loader.setController(taoHoaDonControl);
		try {
			tabTaoHoaDon.setContent(loader.load());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
