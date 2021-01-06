package GUI;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import DAO.DAONhanVien;
import GUI.control.NhanVienControl;
import GUI.control.SuaNhanVienControl;
import GUI.control.ThemNhanVienControl;
import GUI.control.ThongKeGlobal;
import entity.HoaDon;
import entity.NhanVienBanThuoc;
import entity.PhanQuyen;
import entity.QuanLy;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainSenceControl implements Initializable {
	private NhanVienBanThuoc nhanVienBanThuoc;
	private QuanLy quanLy;

	private DAONhanVien daoNhanVien = new DAONhanVien();

	public Text lblTenNhanVien1;
	public Text lblTenNhanVien2;
	public Text lblId;
	public Text lblSoHoaDon;
	public Text txtTime;
	public StackPane stkOptions;
	public Circle cirAvatar;
	public HBox btnBanHang;
	public HBox btnKhoHang;
	public HBox btnThongKe;
	public HBox btnNhanVien;
	public HBox btnDangXuat;
	public AnchorPane main;
	public ImageView imgBanHang;
	public ImageView imgKhoHang;
	public ImageView imgThongKe;
	public ImageView imgNhanVien;
	public ImageView imgDangXuat;
	private ThemNhanVienControl themNhanVienControl;
	private NhanVienControl control;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		initStyleSheetAndImage();
		showPaneBanHang();
		initOptionPanel();
		setSomeFieldNhanVien();
		//set active button
		btnBanHang.getStyleClass().add("activeButton");
		phanQuyen();

		Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {        
			LocalDateTime currentTime = LocalDateTime.now();
			txtTime.setText(currentTime.getHour() + ":" + currentTime.getMinute() + " - " + currentTime.getDayOfMonth() + "/" + currentTime.getMonthValue() + "/" + currentTime.getYear());
		}),
				new KeyFrame(Duration.seconds(60))
				);
		clock.setCycleCount(Animation.INDEFINITE);
		clock.play();

		btnDangXuat.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Đăng Xuất");
				alert.setHeaderText(null);
				alert.setContentText("Bạn có muốn đăng xuất?");

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK){
					// ... user chose OK
					Stage stage = (Stage) ((Node)arg0.getSource()).getScene().getWindow();
					stage.close();

					FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
					Parent root;
					try {
						root = loader.load();
						Stage loginStage = new Stage();
						Scene scene = new Scene(root);
						loginStage.setScene(scene);
						loginStage.show();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					// ... user chose CANCEL or closed the dialog
				}
			}
		});

	}

	private void initStyleSheetAndImage() {
		// TODO Auto-generated method stub
		String css = this.getClass().getResource("MainSence.css").toExternalForm(); 
		main.getStylesheets().add(css);
		
		File cast = new File("icon/shopping-cart (1).png");
		Image castImage = new Image(cast.toURI().toString());
		imgBanHang.setImage(castImage);
		
		imgKhoHang.setImage(new Image(new File("icon/box.png").toURI().toString()));
		imgThongKe.setImage(new Image(new File("icon/statistics.png").toURI().toString()));
		imgNhanVien.setImage(new Image(new File("icon/employee.png").toURI().toString()));
		imgDangXuat.setImage(new Image(new File("icon/logout.png").toURI().toString()));
		
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
		control = new NhanVienControl();
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
		
		try {
			Node suaNhanVien = stkOptions.getChildren().get(5);
			suaNhanVien.setVisible(false);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void showSuaNhanVien(NhanVienBanThuoc nhanVienById) {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ThemNhanVien.fxml"));
		SuaNhanVienControl control = new SuaNhanVienControl(nhanVienById);
		control.setMainSenceControl(this);
		loader.setController(control);
		try {
			stkOptions.getChildren().add(5, loader.load());
			
			Node thongKe = stkOptions.getChildren().get(2);
			Node khoHang = stkOptions.getChildren().get(1);
			Node banHang = stkOptions.getChildren().get(0);
			Node nhanVien = stkOptions.getChildren().get(3);
			Node themNhanVien = stkOptions.getChildren().get(4);
			Node suaNhanVien = stkOptions.getChildren().get(5);
			suaNhanVien.setVisible(true);
			khoHang.setVisible(false);
			banHang.setVisible(false);
			thongKe.setVisible(false);
			nhanVien.setVisible(false);
			themNhanVien.setVisible(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void refestPageQuanLyNhanVien() {
		// TODO Auto-generated method stub
		control.actionButtonTim();
	}

}
