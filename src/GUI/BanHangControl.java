package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import entity.NhanVienBanThuoc;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.layout.StackPane;

public class BanHangControl implements Initializable {

	public Tab tabTaoHoaDon;
	public Tab tabKhachHang;

	public NhanVienBanThuoc nhanVienBanThuoc;

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
		KhachHangControl khachHangControl = new KhachHangControl();
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
		TaoHoaDonControl taoHoaDonControl = new TaoHoaDonControl(nhanVienBanThuoc);
		loader.setController(taoHoaDonControl);
		try {
			tabTaoHoaDon.setContent(loader.load());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
