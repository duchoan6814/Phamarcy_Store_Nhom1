package GUI;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import DAO.DAOKhachHang;
import common.Common;
import entity.KhachHang;
import entity.LoaiKhachHang;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SuaKhachHangControl implements Initializable {
	Common common = new Common();
	DAOKhachHang daoKhachHang = new DAOKhachHang();

	private KhachHang khachHang;
	private String maKhachHang;
	private boolean suaKhachHangThanhCong = false;

	public TextField txtMaKhachHang;
	public TextField txtHoTenDem;
	public TextField txtTen;
	public DatePicker dateNgaySinh;
	public ComboBox<String> cmbGioiTinh;
	public ComboBox<String> cmbLoaiKhachHang;
	public TextField txtSoDienThoai;
	public TextField txtDiaChi;
	public VBox vbxThongBao;
	public Button btnThemOrSua;

	public SuaKhachHangControl(String maKhachHang) {
		super();
		this.maKhachHang = maKhachHang;
	}
	
	


	public boolean isSuaKhachHangThanhCong() {
		return suaKhachHangThanhCong;
	}




	public void setSuaKhachHangThanhCong(boolean suaKhachHangThanhCong) {
		this.suaKhachHangThanhCong = suaKhachHangThanhCong;
	}




	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		initSuaKhachHangForm();
		checkAndChangeNotification();
		handleWhenTextFieldChange();
		
		btnThemOrSua.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				handleButtonSua(event);
			}
		});

	}
	
	private void handleWhenTextFieldChange() {
		// TODO Auto-generated method stub
		txtHoTenDem.textProperty().addListener((observable, oldValue, newValue) -> {
			vbxThongBao.getChildren().clear();
			checkAndChangeNotification();
		});
		txtTen.textProperty().addListener((observable, oldValue, newValue) -> {
			vbxThongBao.getChildren().clear();
			checkAndChangeNotification();
		});
		txtSoDienThoai.textProperty().addListener((observable, oldValue, newValue) -> {
			vbxThongBao.getChildren().clear();
			checkAndChangeNotification();
		});
	}
	
	public void checkAndChangeNotification() {
		List<String> listNotification = new ArrayList<>();
		if (!checkHo(txtHoTenDem.getText())) {
			listNotification.add(" - Họ phải được viết hoa chữ cái đầu!");
			listNotification.add(" - họ không chứa ký tự đặc biệt và số!");
		}
		if (!checkTen(txtTen.getText())) {
			listNotification.add(" - Tên không được để trống!");
			listNotification.add(" - Tên Phải được viết hoa chữ cái đầu!");
		}
		if (!checkSoDienThoai(txtSoDienThoai.getText())) {
			listNotification.add(" - Số điện thoại không được để trống!");
			listNotification.add(" - Số điện thoại không được chứa ký tự đặc biệt và chữchữ!");
			listNotification.add(" - Số điện thoại có chiều dài từ 10-13 chữ số!");
			
		}
		
		for (String string : listNotification) {
			Text text = new Text(string);
			text.setStyle("-fx-fill: red; -fx-font-style: italic");
			vbxThongBao.getChildren().add(text);
		}
	}
	
	public boolean checkHo(String ho) {
		if (!ho.trim().matches("(^[A-Z]{1}[a-z]*)?(\\s[A-Z]{1}[a-z]*)*"))
			return false;
		return true;
	}
	
	public boolean checkTen(String ten) {
		if (!ten.trim().matches("(^[A-Z]{1}[a-z]*)?(\\s?[A-Z]{1}[a-z]*)+")) {
			return false;
		}
		return true;
	}
	
	public boolean checkSoDienThoai(String sdt) {
		if (!sdt.trim().matches("[0-9]{10,13}")) {
			return false;
		}
		return true;
	}
	
	private void handleButtonSua(ActionEvent event) {
		if (!checkHo(txtHoTenDem.getText()) || !checkTen(txtTen.getText()) || !checkSoDienThoai(txtSoDienThoai.getText())) {
			common.showNotification(AlertType.ERROR, "Lỗi thêm", "Sửa khách hàng không thành công, vui lòng kiểm tra lại hết các điều kiện!");
		}else {
			KhachHang khachHang = new KhachHang();
			khachHang.setId(txtMaKhachHang.getText());
			khachHang.setHoTenDem(txtHoTenDem.getText());
			khachHang.setTen(txtTen.getText());
			khachHang.setGioiTinh(cmbGioiTinh.getValue() == "Nam" ? true : false);
			try {
				Date date = Date.from(dateNgaySinh.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
				java.sql.Date sqlDate = new java.sql.Date(date.getTime());
				khachHang.setNgaySinh(sqlDate);
			}catch (Exception e) {
				// TODO: handle exception
			}
			khachHang.setLoaiKhachHang(LoaiKhachHang.get(cmbLoaiKhachHang.getValue()));
			khachHang.setSoDienThoai(txtSoDienThoai.getText());
			khachHang.setDiaChi(txtDiaChi.getText());
			if (daoKhachHang.suaKhachHang(khachHang)) {
				common.showNotification(AlertType.INFORMATION, "Thành công", "Sửa khách hàng thành công.");
				this.suaKhachHangThanhCong = true;
				actionButonHuy(event);
			}else {
				common.showNotification(AlertType.ERROR, "Thất bại", "Sửa khách hàng không thành công vui lòng kiểm tra lại!");
			}
		}
	}

	private void initSuaKhachHangForm() {
		// TODO Auto-generated method stub
		khachHang = daoKhachHang.getKhachHangById(this.maKhachHang);
		if (khachHang == null) {
			common.showNotification(AlertType.ERROR, "Lỗi kết nối", "Xảy ra lỗi trong quá trình get dữ liệu!");;
		}else {
			txtMaKhachHang.setText(khachHang.getId());
			txtHoTenDem.setText(khachHang.getHoTenDem());
			txtTen.setText(khachHang.getTen());
			try {
				dateNgaySinh.setValue(khachHang.getNgaySinh().toLocalDate());
			} catch (Exception e) {
				// TODO: handle exception
				dateNgaySinh.setValue(null);
			}
			txtSoDienThoai.setText(khachHang.getSoDienThoai());
			txtDiaChi.setText(khachHang.getDiaChi());

			ObservableList<String> listGioiTinh = FXCollections.observableArrayList();
			listGioiTinh.add("Nam");
			listGioiTinh.add("Nữ");
			cmbGioiTinh.setItems(listGioiTinh);
			cmbGioiTinh.setValue(khachHang.isGioiTinh() ? listGioiTinh.get(0) : listGioiTinh.get(1));

			ObservableList<String> listLoaiKhacHang = FXCollections.observableArrayList();
			listLoaiKhacHang.add("MEMBER");
			listLoaiKhacHang.add("VIP");
			listLoaiKhacHang.add("SUPERVIP");
			cmbLoaiKhachHang.setItems(listLoaiKhacHang);
			for (int i = 0; i < listLoaiKhacHang.size(); i++) {
				if (listLoaiKhacHang.get(i) == khachHang.getLoaiKhachHang().getLoaiKhachHang()) {
					cmbLoaiKhachHang.setValue(listLoaiKhacHang.get(i));
					break;
				}
			}
		}
	}


	@FXML
	public void actionButonHuy(ActionEvent event) {
		Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		currentStage.close();
	}

}
