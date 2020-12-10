package GUI;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import DAO.DAONhanVien;
import GUI.control.NhanVienControl;
import GUI.control.ThemNhanVienControl;
import GUI.control.ThongKeGlobal;
import entity.HoaDon;
import entity.NhanVienBanThuoc;
import entity.PhanQuyen;
import entity.QuanLy;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class MainSenceControl implements Initializable {
	private NhanVienBanThuoc nhanVienBanThuoc;
	private QuanLy quanLy;
	
	private DAONhanVien daoNhanVien = new DAONhanVien();

	public Text lblTenNhanVien1;
	public Text lblTenNhanVien2;
	public Text lblId;
	public Text lblSoHoaDon;
	public StackPane stkOptions;
	public Circle cirAvatar;
	public HBox btnBanHang;
	public HBox btnKhoHang;
	public HBox btnThongKe;
	public HBox btnNhanVien;
	private ThemNhanVienControl themNhanVienControl;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		showPaneBanHang();
		initOptionPanel();
		setSomeFieldNhanVien();
		//set active button
		btnBanHang.getStyleClass().add("activeButton");
		phanQuyen();

	}

	private void phanQuyen() {
		// TODO Auto-generated method stub
		if (nhanVienBanThuoc.getTaiKhoan().getPhanQuyen() == PhanQuyen.NHANVIEN) {
			btnKhoHang.setVisible(false);
			btnThongKe.setVisible(false);
			btnNhanVien.setVisible(false);
		}
	}


	private void setSomeFieldNhanVien() {
		// TODO Auto-generated method stub
		lblTenNhanVien1.setText(nhanVienBanThuoc.getHoTenDem()+" "+nhanVienBanThuoc.getTen());
		lblTenNhanVien2.setText(nhanVienBanThuoc.getHoTenDem()+" "+nhanVienBanThuoc.getTen());
		lblId.setText("ID: "+nhanVienBanThuoc.getId());
		lblSoHoaDon.setText("Số hóa đơn: "+daoNhanVien.getTotalHoaDonByNhanVien(nhanVienBanThuoc.getId()));

		try {
			setAvatar();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}


	private void showPaneBanHang() {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader(getClass().getResource("pnlBanHang.fxml"));
		BanHangControl banHangControl = new BanHangControl(nhanVienBanThuoc);
		loader.setController(banHangControl);
		try {
			stkOptions.getChildren().add(0, loader.load());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void initOptionPanel() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("PanelKhoHang.fxml"));
		KhoHangControl khoHangControl = new KhoHangControl();
		khoHangControl.setNhanVienBanThuoc(nhanVienBanThuoc);
		loader.setController(khoHangControl);
		try {
			stkOptions.getChildren().add(1, loader.load());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FXMLLoader loaderThongKe = new FXMLLoader(getClass().getResource("pnlThongKeGlobal.fxml"));
		ThongKeGlobal thongKeGlobal = new ThongKeGlobal();
		loaderThongKe.setController(thongKeGlobal);
		try {
			stkOptions.getChildren().add(2, loaderThongKe.load());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FXMLLoader loaderNhanVien = new FXMLLoader(getClass().getResource("QuanLyNV.fxml"));
		NhanVienControl control = new NhanVienControl();
		control.setMainSenceControl(this);
		loaderNhanVien.setController(control);
		try {
			stkOptions.getChildren().add(3, loaderNhanVien.load());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FXMLLoader loaderThemNhanVien = new FXMLLoader(getClass().getResource("ThemNhanVien.fxml"));
		themNhanVienControl = new ThemNhanVienControl();
		themNhanVienControl.setMainSenceControl(this);
		loaderThemNhanVien.setController(themNhanVienControl);
		try {
			stkOptions.getChildren().add(4, loaderThemNhanVien.load());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Node thongKe = stkOptions.getChildren().get(2);
		Node khoHang = stkOptions.getChildren().get(1);
		Node banHang = stkOptions.getChildren().get(0);
		Node nhanVien = stkOptions.getChildren().get(3);
		Node themNhanVien = stkOptions.getChildren().get(4);
		
		khoHang.setVisible(false);
		banHang.setVisible(true);
		thongKe.setVisible(false);
		nhanVien.setVisible(false);
		themNhanVien.setVisible(false);
		
	}
	
	public void showThemNhanVien() {
		Node thongKe = stkOptions.getChildren().get(2);
		Node khoHang = stkOptions.getChildren().get(1);
		Node banHang = stkOptions.getChildren().get(0);
		Node nhanVien = stkOptions.getChildren().get(3);
		Node themNhanVien = stkOptions.getChildren().get(4);
		khoHang.setVisible(false);
		banHang.setVisible(false);
		thongKe.setVisible(false);
		nhanVien.setVisible(false);
		themNhanVien.setVisible(true);
		themNhanVienControl.initMaNhanVien();
	}
	
	@FXML
	public void actionButtonKhoHang() {
		Node khoHang = stkOptions.getChildren().get(1);
		Node banHang = stkOptions.getChildren().get(0);
		Node thongKe = stkOptions.getChildren().get(2);
		Node nhanVien = stkOptions.getChildren().get(3);
		Node themNhanVien = stkOptions.getChildren().get(4);
		themNhanVien.setVisible(false);
		khoHang.setVisible(true);
		banHang.setVisible(false);
		thongKe.setVisible(false);
		nhanVien.setVisible(false);
		
		btnKhoHang.getStyleClass().add("activeButton");
		btnBanHang.getStyleClass().clear();
		btnBanHang.getStyleClass().addAll("button", "buttonSelectMain");
		btnThongKe.getStyleClass().clear();
		btnThongKe.getStyleClass().addAll("button", "buttonSelectMain");
		btnNhanVien.getStyleClass().clear();
		btnNhanVien.getStyleClass().addAll("button", "buttonSelectMain");
	}
	
	@FXML
	public void actionButtonBanHang() {
		Node banHang = stkOptions.getChildren().get(0);
		Node khoHang = stkOptions.getChildren().get(1);
		Node thongKe = stkOptions.getChildren().get(2);
		Node nhanVien = stkOptions.getChildren().get(3);
		Node themNhanVien = stkOptions.getChildren().get(4);
		themNhanVien.setVisible(false);
		khoHang.setVisible(false);
		banHang.setVisible(true);
		thongKe.setVisible(false);
		nhanVien.setVisible(false);
		btnBanHang.getStyleClass().add("activeButton");
		btnKhoHang.getStyleClass().clear();
		btnKhoHang.getStyleClass().addAll("button", "buttonSelectMain");
		btnThongKe.getStyleClass().clear();
		btnThongKe.getStyleClass().addAll("button", "buttonSelectMain");
		btnNhanVien.getStyleClass().clear();
		btnNhanVien.getStyleClass().addAll("button", "buttonSelectMain");
	}
	
	@FXML
	public void actionButtonThongKe() {
		Node banHang = stkOptions.getChildren().get(0);
		Node khoHang = stkOptions.getChildren().get(1);
		Node thongKe = stkOptions.getChildren().get(2);
		Node nhanVien = stkOptions.getChildren().get(3);
		Node themNhanVien = stkOptions.getChildren().get(4);
		themNhanVien.setVisible(false);
		khoHang.setVisible(false);
		banHang.setVisible(false);
		nhanVien.setVisible(false);
		thongKe.setVisible(true);
		btnThongKe.getStyleClass().add("activeButton");
		btnKhoHang.getStyleClass().clear();
		btnKhoHang.getStyleClass().addAll("button", "buttonSelectMain");
		btnBanHang.getStyleClass().clear();
		btnBanHang.getStyleClass().addAll("button", "buttonSelectMain");
		btnNhanVien.getStyleClass().clear();
		btnNhanVien.getStyleClass().addAll("button", "buttonSelectMain");
	}
	
	@FXML
	public void actionButtonNhanVien() {
		Node banHang = stkOptions.getChildren().get(0);
		Node khoHang = stkOptions.getChildren().get(1);
		Node thongKe = stkOptions.getChildren().get(2);
		Node nhanVien = stkOptions.getChildren().get(3);
		Node themNhanVien = stkOptions.getChildren().get(4);
		themNhanVien.setVisible(false);
		khoHang.setVisible(false);
		banHang.setVisible(false);
		nhanVien.setVisible(true);
		thongKe.setVisible(false);
		btnNhanVien.getStyleClass().add("activeButton");
		btnKhoHang.getStyleClass().clear();
		btnKhoHang.getStyleClass().addAll("button", "buttonSelectMain");
		btnBanHang.getStyleClass().clear();
		btnBanHang.getStyleClass().addAll("button", "buttonSelectMain");
		btnThongKe.getStyleClass().clear();
		btnThongKe.getStyleClass().addAll("button", "buttonSelectMain");
	}


	private void setAvatar() {
		// TODO Auto-generated method stub
		//		set avatar
		InputStream in = new ByteArrayInputStream(nhanVienBanThuoc.getAvatar());
		Image avatar = new Image(in);
		cirAvatar.setFill(new ImagePattern(avatar));
	}


	public void setNhanVienBanThuoc(NhanVienBanThuoc nhanVienBanThuoc) {
		// TODO Auto-generated method stub
		this.nhanVienBanThuoc = nhanVienBanThuoc;

	}

	public void showQuanLyNhanVien() {
		// TODO Auto-generated method stub
		Node thongKe = stkOptions.getChildren().get(2);
		Node khoHang = stkOptions.getChildren().get(1);
		Node banHang = stkOptions.getChildren().get(0);
		Node nhanVien = stkOptions.getChildren().get(3);
		Node themNhanVien = stkOptions.getChildren().get(4);
		khoHang.setVisible(false);
		banHang.setVisible(false);
		thongKe.setVisible(false);
		nhanVien.setVisible(true);
		themNhanVien.setVisible(false);
	}



}
