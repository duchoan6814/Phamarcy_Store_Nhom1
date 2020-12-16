package GUI.control;

import java.net.URL;
import java.util.ResourceBundle;

import DAO.DAOLocation;
import DAO.DAONhaCungCap;
import GUI.QuanLyNCCConTrol;
import common.Common;
import entity.NhaCungCap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ThemNhaCungCapControl implements Initializable {
	public TextField txtMaNhaCungCap;
	public TextField txtTenNhaCungCap;
	public TextField txtSoDienThoai;
	public TextField txtFAX;
	public TextField txtTrangChu;
	public TextField txtSoNha;
	public Button btnThem;
	public Button btnHuy;
	public ComboBox<String> cmbTinh;
	public ComboBox<String> cmbQuan;
	public ComboBox<String> cmbPhuong;
	public Text lblTenNhaCungCap;
	public Text lblSoDienThoai;
	public Text lblFAX;
	public Text lblTrangChu;
	public Text lblDiaChi;


	private Common common = new Common();
	private DAONhaCungCap daoNhaCungCap = new DAONhaCungCap();
	private DAOLocation daoLocation = new DAOLocation();
	private ThemThuocControl themThuocControl;
	private QuanLyNCCConTrol quanLyNCCConTrol;

	private ObservableList<String> listTinh;
	private ObservableList<String> listQuan;
	private ObservableList<String> listXa;

	public ThemNhaCungCapControl(ThemThuocControl control) {
		// TODO Auto-generated constructor stub
		this.themThuocControl = control;
	}

	public ThemNhaCungCapControl(QuanLyNCCConTrol conTrol) {
		// TODO Auto-generated constructor stub
		this.quanLyNCCConTrol = conTrol;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		initField();
		initButtonThem();
		initButtonHuy();
	}

	private void initButtonHuy() {
		// TODO Auto-generated method stub
		btnHuy.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Stage thanhToanStage = (Stage) ((Node)arg0.getSource()).getScene().getWindow();
				thanhToanStage.close();
			}
		});
	}

	private void initButtonThem() {
		// TODO Auto-generated method stub
		btnThem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				actionButtonThem(arg0);
			}
		});
	}

	protected void actionButtonThem(ActionEvent event) {
		// TODO Auto-generated method stub

		if (checkDiaChi() && checkEmail() && checkoutTrangChu() && checkSoDienThoai() && checkTenNhaCungCap()) {
			NhaCungCap nhaCungCap = new NhaCungCap();
			nhaCungCap.setId(txtMaNhaCungCap.getText());
			nhaCungCap.setDiaChi(txtSoNha.getText()+" - "+cmbPhuong.getValue()+" - "+cmbQuan.getValue()+" - "+cmbTinh.getValue());
			nhaCungCap.setEmail(txtFAX.getText());
			nhaCungCap.setSoDienThoai(txtSoDienThoai.getText());
			nhaCungCap.setTenNhaCungCap(txtTenNhaCungCap.getText());
			nhaCungCap.setTrangChu(txtTrangChu.getText());
			if (daoNhaCungCap.themNhaCungCap(nhaCungCap)) {
				common.showNotification(AlertType.INFORMATION, "INFORMATION", "Thêm nhà cung cấp thành công!");
				if (this.themThuocControl != null) {
					this.themThuocControl.listNhaCungCap.add(txtTenNhaCungCap.getText());
					this.themThuocControl.cmbNhaCungCap.setValue(txtTenNhaCungCap.getText());
				}else {
					this.quanLyNCCConTrol.actionButtonTim();
				}
				Stage thanhToanStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
				thanhToanStage.close();
			}else {
				common.showNotification(AlertType.ERROR, "ERROR", "Có lỗi sảy ra khi thêm, vui lòng kiểm tra kết nối!");
			}
		}else {
			common.showNotification(AlertType.ERROR, "ERROR", "Thêm không thành công vui lòng kiểm tra lại điều kiện!");
		}


	}

	private void initField() {
		// TODO Auto-generated method stub
		initMaNhaCungCap();
		initTenNhaCungCap();
		initSoDienThoai();
		initGmail();
		initTrangChu();
		initDiaChi();
	}

	private void initMaNhaCungCap() {
		// TODO Auto-generated method stub
		txtMaNhaCungCap.setText(daoNhaCungCap.generateID());
		txtMaNhaCungCap.setEditable(false);
	}

	private void initDiaChi() {
		// TODO Auto-generated method stub
		initTinhThanhPho();
		initQuanHuyen();
		initSoNha();
		initXa();
	}

	private void initXa() {
		// TODO Auto-generated method stub
		cmbPhuong.setValue("");
		cmbPhuong.getSelectionModel().selectedItemProperty().addListener((option, old, newv) -> {
			checkDiaChi();
		});
	}

	private void initSoNha() {
		// TODO Auto-generated method stub
		txtSoNha.textProperty().addListener((options, old, newv) -> {
			checkDiaChi();
		});
	}

	private void initQuanHuyen() {
		// TODO Auto-generated method stub
		cmbQuan.getSelectionModel().selectedItemProperty().addListener((ops, old, newv) -> {
			try {
				if (newv.equals("")) {
					cmbPhuong.setDisable(true);
					cmbPhuong.setValue("");
				}else {
					cmbPhuong.setDisable(false);
					setValueForXa();
				}
			}catch (Exception e) {
				// TODO: handle exception
			}

			checkDiaChi();

		});
	}

	private boolean checkDiaChi() {
		if ((cmbTinh.getValue() == null || cmbTinh.getValue().equals("")) || (cmbQuan.getValue() == null || cmbQuan.getValue().equals("")) || (cmbPhuong.getValue() == null || cmbPhuong.getValue().equals(""))) {
			lblDiaChi.setText("* Vui lòng chọn đầy đủ thông tin địa chỉ!");
			return false;
		}

		if (txtSoNha.getText().isEmpty()) {
			lblDiaChi.setText("* Vui lòng nhập số nhà!");
			return false;
		}

		lblDiaChi.setText("*");
		return true;
	}

	private void setValueForXa() {
		// TODO Auto-generated method stub
		listXa = FXCollections.observableArrayList(daoLocation.getListPhuongXaByHuyenVaTinh(cmbTinh.getValue(), cmbQuan.getValue()));
		cmbPhuong.setItems(listXa);
	}

	private void initTinhThanhPho() {
		// TODO Auto-generated method stub
		listTinh = FXCollections.observableArrayList(daoLocation.getListThanhPho());
		cmbTinh.setValue("");
		cmbTinh.setItems(listTinh);
		//		cmbTinh.setValue(listTinh.get(0));

		cmbTinh.getSelectionModel().selectedItemProperty().addListener((op, old, newv) -> {
			checkDiaChi();
			if (newv.equals("")) {
				cmbQuan.setDisable(true);
				cmbPhuong.setDisable(true);
			}else {
				cmbQuan.setDisable(false);
				setValueForQuanHuyen();
			}
		});
	}
	private void setValueForQuanHuyen() {
		// TODO Auto-generated method stub
		listQuan = FXCollections.observableArrayList(daoLocation.getListQuanHuyenByTinh(cmbTinh.getValue()));
		cmbQuan.setItems(listQuan);
	}

	//============================================================
	private void initTrangChu() {
		// TODO Auto-generated method stub
		txtTrangChu.textProperty().addListener((o, old, newv) -> {
			checkoutTrangChu();
		});
	}
	private boolean checkoutTrangChu() {
		// TODO Auto-generated method stub
		if (txtTrangChu.getText().isEmpty()) {
			return true;
		}

		if (!txtTrangChu.getText().matches("^[a-zA-Z0-9][a-zA-Z0-9-]{1,61}[a-zA-Z0-9]\\.[a-zA-Z]{2,}$")) {
			lblTrangChu.setText("Vui lòng nhập đúng domain của doanh nghiệp!");
			return false;
		}
		lblTrangChu.setText("");
		return true;
	}

	//=====================================================================
	private void initGmail() {
		// TODO Auto-generated method stub
		txtFAX.textProperty().addListener((o, old, newv) -> {
			checkEmail();
		});
	}
	private boolean checkEmail() {
		// TODO Auto-generated method stub
		if (txtFAX.getText().isEmpty()) {
			return true;
		}

		if (!txtFAX.getText().matches("(?:[a-z0-9!#$%&'*+/=?^_`\\{|\\}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`\\{|\\}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
			lblFAX.setText("Nhập đúng email của doanh nghiệp!");
			return false;
		}
		lblFAX.setText("");
		return true;
	}

	//=============================================================
	private void initSoDienThoai() {
		// TODO Auto-generated method stub
		txtSoDienThoai.textProperty().addListener((o, old, newv) -> {
			checkSoDienThoai();
		});
	}
	private boolean checkSoDienThoai() {
		// TODO Auto-generated method stub
		if (txtSoDienThoai.getText().isEmpty()) {
			lblSoDienThoai.setText("* Số điện thoại không được để trống!");
			return false;
		}

		if (!txtSoDienThoai.getText().matches("(84|0[3|5|7|8|9])+([0-9]{8})\\b")) {
			lblSoDienThoai.setText("* Số điện thoại có 10 chữ số bắt đầu bằng 84, 03, 05, 07, 08, 09!");
			return false;
		}

		lblSoDienThoai.setText("*");
		return true;
	}

	//================================================
	private void initTenNhaCungCap() {
		// TODO Auto-generated method stub
		txtTenNhaCungCap.textProperty().addListener((o, old, newv) -> {
			checkTenNhaCungCap();
		});

	}

	private boolean checkTenNhaCungCap() {
		// TODO Auto-generated method stub
		if (txtTenNhaCungCap.getText().isEmpty()) {
			lblTenNhaCungCap.setText("* Tên nhà cung cấp không được bỏ trống!");
			return false;
		}

		lblTenNhaCungCap.setText("*");
		return true;
	}
}
