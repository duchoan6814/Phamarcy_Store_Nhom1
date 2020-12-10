package GUI.control;

import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import DAO.DAONhanVien;
import GUI.MainSenceControl;
import common.Common;
import entity.NhanVienBanThuoc;
import entity.PhanQuyen;
import entity.TaiKhoan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ThemNhanVienControl implements Initializable {

	public Button btnHuy;
	public Button btnThem;
	public Button btnAvatar;
	public Text lblTenDangNhap;
	public Text lblMatKhau;
	public Text lblNhapLaiMatKhau;
	public Text lblHoVaTenDem;
	public Text lblTen;
	public Text lblSoDienThoai;
	public Text lblSoCMND;
	public Text lblAvatar;
	public TextField txtTenDangNhap;
	public TextField txtMaNhanVien;
	public TextField txtHoVaTenDem;
	public TextField txtTen;
	public TextField txtSoDienThoai;
	public TextField txtSoCMND;
	public TextField txtAvatar;
	public TextField txtDiaChi;
	public PasswordField txtMatKhau;
	public PasswordField txtNhapLaiMatKhau;
	public DatePicker dateNgaySinh;
	public ComboBox<String> cmbGioiTinh;
	public ComboBox<String> cmbPhanQuyen;


	private FileChooser fileChooser = new FileChooser();
	private MainSenceControl mainSenceControl;
	private DAONhanVien daoNhanVien = new DAONhanVien();
	private Common common = new Common();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		initButtonHuy();
		initSomeField();
		initButtonFile();
		initButtonThem();
	}

	private void initButtonThem() {
		// TODO Auto-generated method stub
		btnThem.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (checkHoVaTenDem(txtHoVaTenDem.getText()) && checkMatKhau(txtMatKhau.getText()) && checkNhapLaiMatKhau(txtNhapLaiMatKhau.getText())
						&& checkSoCMND(txtSoCMND.getText()) && checkSoDienThoai(txtSoDienThoai.getText())
						&& checkTen(txtTen.getText()) && checkTenDangNhap(txtTenDangNhap.getText()) && checkAvatar()) {
					NhanVienBanThuoc nhanVienBanThuoc = new NhanVienBanThuoc();
					nhanVienBanThuoc.setDiaChi(txtDiaChi.getText());
					nhanVienBanThuoc.setGioiTinh(cmbGioiTinh.getValue().equals("Nam") ? true : false);
					nhanVienBanThuoc.setHoTenDem(txtHoVaTenDem.getText());
					nhanVienBanThuoc.setId(txtMaNhanVien.getText());
					LocalDate ngaySinh = dateNgaySinh.getValue();
					if (ngaySinh != null) {
						nhanVienBanThuoc.setNgaySinh(Date.valueOf(dateNgaySinh.getValue()));
					}
					
					nhanVienBanThuoc.setSoCMND(txtSoCMND.getText());
					nhanVienBanThuoc.setSoDienThoai(txtSoDienThoai.getText());
					nhanVienBanThuoc.setTen(txtTen.getText());
					TaiKhoan taiKhoan = new TaiKhoan();
					taiKhoan.setTenDangNhap(txtTenDangNhap.getText());
					taiKhoan.setMatKhau(txtMatKhau.getText());
					taiKhoan.setPhanQuyen(PhanQuyen.valueOf(cmbPhanQuyen.getValue()));
					nhanVienBanThuoc.setTaiKhoan(taiKhoan);
					
					if (!daoNhanVien.themNhanVien(nhanVienBanThuoc, txtAvatar.getText())) {
						common.showNotification(AlertType.ERROR, "ERROR", "Lỗi kết nối vui lòng kiểm tra lại!");
					}else {
						common.showNotification(AlertType.INFORMATION, "INFORMATION", "Thêm thành công!");
						themThanhcong();
					}
				}else {
					common.showNotification(AlertType.ERROR, "ERROR", "Thêm không thành công vui lòng kiểm tra lại cái điều kiện!");
				}
			}
		});
	}
	
	private void themThanhcong() {
		// TODO Auto-generated method stub
		txtTenDangNhap.setText("");
		txtMatKhau.setText("");
		txtNhapLaiMatKhau.setText("");
		txtMaNhanVien.setText("");
		txtHoVaTenDem.setText("");
		txtTen.setText("");
		txtSoDienThoai.setText("");
		txtSoCMND.setText("");
		txtAvatar.setText("");
		txtDiaChi.setText("");
		dateNgaySinh.setValue(null);
		this.mainSenceControl.showQuanLyNhanVien();
	}

	private void initButtonFile() {
		// TODO Auto-generated method stub
		btnAvatar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Stage stage = (Stage) ((Node)arg0.getSource()).getScene().getWindow();
				File file = fileChooser.showOpenDialog(stage);
				txtAvatar.setText(file.getPath());
			}
		});
	}

	private void initSomeField() {
		// TODO Auto-generated method stub
		initGioiTinh();
		initPhanQuyen();
		initTenDangNhap();
		initMatKhau();
		initNhapLaiMatKhau();
		initHoVaTenDem();
		initTen();
		initSoDienThoai();
		initSoCMND();
		initFilePicker();
	}
	private void initFilePicker() {
		// TODO Auto-generated method stub
		txtAvatar.setDisable(true);
	}
	
	private boolean checkAvatar() {
		if (txtAvatar.getText().isEmpty()) {
			lblAvatar.setText("Avatar không được để trống!");
			return false;
		}
		lblAvatar.setText("");
		return true;
	}

	public void initMaNhanVien() {
		// TODO Auto-generated method stub
		txtMaNhanVien.setText(daoNhanVien.generateMaNhanVien());
	}
	//=======================================================
	private void initSoDienThoai() {
		// TODO Auto-generated method stub
		txtSoDienThoai.textProperty().addListener((p, oldv, newv) -> {
			if (!newv.matches("^[0-9]*$")) {
				txtSoDienThoai.setText(oldv);
			}
			checkSoDienThoai(newv);
		});
	}
	private boolean checkSoDienThoai(String newv) {
		// TODO Auto-generated method stub
		if (newv.isEmpty()) {
			lblSoDienThoai.setText("Số điện thoại không được để trống!");
			return false;
		}
		lblSoDienThoai.setText("");
		return true;
	}

	//	=============================================
	private void initSoCMND() {
		// TODO Auto-generated method stub
		txtSoCMND.textProperty().addListener((p, oldv, newv) -> {
			if (!newv.matches("^[0-9]*$")) {
				txtSoCMND.setText(oldv);
			}
			checkSoCMND(newv);
		});
	}
	private boolean checkSoCMND(String newv) {
		// TODO Auto-generated method stub
		if (newv.isEmpty()) {
			lblSoCMND.setText("Số CMND không được để trống!");
			return false;
		}
		lblSoCMND.setText("");
		return true;
	}

	//======================================================================
	private void initTen() {
		// TODO Auto-generated method stub
		txtTen.textProperty().addListener((p, oldv, newv) -> {
			checkTen(newv);
		});
	}
	private boolean checkTen(String newv) {
		// TODO Auto-generated method stub
		if (newv.isEmpty()) {
			lblTen.setText("Tên không được bỏ trống!");
			return false;
		}

		lblTen.setText("");
		return true;
	}

	//=============================================
	private void initHoVaTenDem() {
		// TODO Auto-generated method stub
		txtHoVaTenDem.textProperty().addListener((p, oldv, newv) -> {
			checkHoVaTenDem(newv);
		});
	}

	private boolean checkHoVaTenDem(String newv) {
		// TODO Auto-generated method stub
		if (newv.isEmpty()) {
			lblHoVaTenDem.setText("Họ và tên đệm không được bỏ trống!");
			return false;
		}

		lblHoVaTenDem.setText("");
		return true;
	}

	//	==============================================

	private void initNhapLaiMatKhau() {
		// TODO Auto-generated method stub
		txtNhapLaiMatKhau.textProperty().addListener((p, oldv, newv) -> {
			checkNhapLaiMatKhau(newv);
		});
	}
	private boolean checkNhapLaiMatKhau(String newv) {
		// TODO Auto-generated method stub
		if (newv.isEmpty()) {
			lblNhapLaiMatKhau.setText("Vui lòng xác nhận lại mật khẩu!");
			return false;
		}

		if (!newv.equals(txtMatKhau.getText())) {
			lblNhapLaiMatKhau.setText("Mật khẩu chưa khớp!");
			return false;
		}

		lblNhapLaiMatKhau.setText("");
		return true;
	}

	//============================================================
	private void initMatKhau() {
		// TODO Auto-generated method stub
		txtMatKhau.textProperty().addListener((p, oldv, newv) -> {
			checkMatKhau(newv);
		});
	}
	private boolean checkMatKhau(String newv) {
		// TODO Auto-generated method stub
		if (newv.isEmpty()) {
			lblMatKhau.setText("Mật khẩu không được bỏ trống!");
			return false;
		}
		lblMatKhau.setText("");
		return true;
	}

	//=============================================================================
	private void initTenDangNhap() {
		// TODO Auto-generated method stub
		txtTenDangNhap.textProperty().addListener((p, oldv, newv) -> {
			checkTenDangNhap(newv);
		});
	}

	private boolean checkTenDangNhap(String newv) {
		// TODO Auto-generated method stub
		if (newv.isEmpty()) {
			lblTenDangNhap.setText("Tên Đăng nhập không được bỏ trống!");
			return false;
		}
		lblTenDangNhap.setText("");
		return true;
	}
	//==========================================================================
	private void initPhanQuyen() {
		// TODO Auto-generated method stub
		ObservableList<String> listPhanQuyen = FXCollections.observableArrayList();
		listPhanQuyen.add("NHANVIEN");
		listPhanQuyen.add("QUANLY");
		cmbPhanQuyen.setItems(listPhanQuyen);
		cmbPhanQuyen.setValue(listPhanQuyen.get(0));
	}

	private void initGioiTinh() {
		// TODO Auto-generated method stub
		ObservableList<String> listGioTinh = FXCollections.observableArrayList();
		listGioTinh.add("Nam");
		listGioTinh.add("Nữ");
		cmbGioiTinh.setItems(listGioTinh);
		cmbGioiTinh.setValue(listGioTinh.get(0));
	}

	private void initButtonHuy() {
		// TODO Auto-generated method stub
		btnHuy.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirmation Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Bạn có chắc muốn hủy?");

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK){
					// ... user chose OK
					mainSenceControl.showQuanLyNhanVien();
				} else {
					// ... user chose CANCEL or closed the dialog
				}
			}
		});
	}

	public void setMainSenceControl(MainSenceControl mainSenceControl2) {
		// TODO Auto-generated method stub
		this.mainSenceControl = mainSenceControl2;
	}
}
