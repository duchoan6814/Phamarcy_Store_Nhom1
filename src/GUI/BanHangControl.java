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
	public Tab tabThuoc;
	public Tab tabHoaDon;
	public Tab tabThongKe;

	
	
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
	private ThuocControl thuocControl;
	private ThongKeControl thongKeControl;
	
	

	public ThongKeControl getThongKeControl() {
		return thongKeControl;
	}

	public void setThongKeControl(ThongKeControl thongKeControl) {
		this.thongKeControl = thongKeControl;
	}

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
		setContentTabThuoc();
		setContentTabHoaDon();
		setContentThongKe();
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
		taoHoaDonControl.setBanHangControl(this);
		loader.setController(taoHoaDonControl);
		try {
			tabTaoHoaDon.setContent(loader.load());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void setContentTabThuoc() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("PnlThuoc.fxml"));
		thuocControl = new ThuocControl();
		thuocControl.setBanHangControl(this);
		loader.setController(thuocControl);
		try {
			tabThuoc.setContent(loader.load());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void setContentTabHoaDon() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("PnlHoaDon.fxml"));
		HoaDonControl hoaDonControl = new HoaDonControl();
		loader.setController(hoaDonControl);
		try {
			tabHoaDon.setContent(loader.load());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void setContentThongKe() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("PnlThongKe.fxml"));
		thongKeControl = new ThongKeControl();
		thongKeControl.setNhanVienBanThuoc(nhanVienBanThuoc);
		loader.setController(thongKeControl);
		try {
			tabThongKe.setContent(loader.load());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
