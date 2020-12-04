package GUI;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import DAO.DAOLoaiThuoc;
import DAO.DAONhaCungCap;
import DAO.DAOThuoc;
import common.Common;
import entity.Thuoc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SuaThuocControl implements Initializable {
	private DAOThuoc daoThuoc = new DAOThuoc();
	private Common common = new Common();
	private DAONhaCungCap daoNhaCungCap = new DAONhaCungCap();
	private DAOLoaiThuoc daoLoaiThuoc = new DAOLoaiThuoc();
	
	private Thuoc thuoc;
	private Thuoc2Control thuoc2Control;
	
	
	public Thuoc2Control getThuoc2Control() {
		return thuoc2Control;
	}

	public void setThuoc2Control(Thuoc2Control thuoc2Control) {
		this.thuoc2Control = thuoc2Control;
	}

	public void setThuoc(String data) {
		// TODO Auto-generated method stub
		this.thuoc = daoThuoc.getThuocById(data);
	}
	
	public TextField txtMaThuoc;
	public TextField txtTenThuoc;
	public TextField txtNuocSanXuat;
	public TextField txtHanSuDung;
	public TextField txtDangBaoChe;
	public TextField txtQuyCachDongGoi;
	public TextField txtGiaThuoc;
	public Text lblMaThuoc;
	public Text lblTenThuoc;
	public Text lblNuocSanXuat;
	public Text lblHanSuDung;
	public Text lblGiaThuoc;
	public Text lblTitle;
	public ComboBox<String> cmbLoaiThuoc;
	public ComboBox<String> cmbNhaCungCap;
	public ComboBox<String> cmbDonViTinh;
	public TextArea txaMoTa;
	public Button btnThem;
	public Button btnHuy;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		initSomeField();
		initDataLoaiThuoc();
		initDataNhaCungCap();
		initDataDonViTinh();
		initDataNuocSanXuat();
		initMaThuoc();
		initTenThuoc();
		initNuocSanXuat();
		initHanSuDung();
		initGiaThuoc();
		initButtonThem();
		initButtonHuy();
		
		checkGiaThuoc();
		checkHanSuDung();
		checkMaThuoc();
		checkNuocSanXuat();
		checkTenThuoc();
	}
	
	private void initSomeField() {
		// TODO Auto-generated method stub
		lblTitle.setText("Sửa Thuốc");
		txtDangBaoChe.setText(thuoc.getDangBaoChe());
		txtQuyCachDongGoi.setText(thuoc.getQuyCachDongGoi());
		txaMoTa.setText(thuoc.getMoTa());
	}

	private void initButtonHuy() {
		// TODO Auto-generated method stub
		btnHuy.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				closeStage(event);
			}

		});
	}
	
	private void closeStage(ActionEvent event) {
		// TODO Auto-generated method stub
		Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		currentStage.close();
	}

	private void initGiaThuoc() {
		// TODO Auto-generated method stub
		txtGiaThuoc.setText(Double.toString(thuoc.getGia()));
		txtGiaThuoc.textProperty().addListener((o, oldv, newv) -> {
			if (!newv.matches("^[0-9]*")) {
				txtGiaThuoc.setText(oldv);
			}
			checkGiaThuoc();
		});
	}

	private boolean checkGiaThuoc() {
		// TODO Auto-generated method stub
		if (txtGiaThuoc.getText().isEmpty()) {
			lblGiaThuoc.setText("Giá Thuốc không được để trống.");
			return false;
		}else if(Double.parseDouble(txtGiaThuoc.getText()) <= 0) {
			lblGiaThuoc.setText("Giá thuốc phải lớn hơn 0");
			return false;
		}
		lblGiaThuoc.setText("");
		return true;
	}

	private void initButtonThem() {
		// TODO Auto-generated method stub
		btnThem.setText("Sửa");
		btnThem.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if (!checkGiaThuoc() || !checkHanSuDung() || !checkMaThuoc() || !checkNuocSanXuat() || !checkTenThuoc()) {
					common.showNotification(AlertType.ERROR, "ERROR", "Lỗi sửa, vui lòng kiểm tra lại các kiều kiện!");
				}else {
					Thuoc thuoc = new Thuoc(txtMaThuoc.getText(), txtTenThuoc.getText(),
							txaMoTa.getText(), cmbDonViTinh.getValue(), txtDangBaoChe.getText(),
							txtQuyCachDongGoi.getText(), Integer.parseInt(txtHanSuDung.getText()),
							0, Double.parseDouble(txtGiaThuoc.getText()), 0.05,
							daoNhaCungCap.getNhaCungCapByName(cmbNhaCungCap.getValue()),
							daoLoaiThuoc.getLoaiThuocByTen(cmbLoaiThuoc.getValue()), txtNuocSanXuat.getText());
					if (daoThuoc.updateThuoc(thuoc)) {
						common.showNotification(AlertType.INFORMATION, "SUCCESS", "Sửa thuốc thành công.");
						closeStage(event);
						thuoc2Control.actionButtonTim();
					}else {
						common.showNotification(AlertType.ERROR, "ERRORERROR", "Sửa không thành công vui lòng kiểm tra lại!");
					}
				}
			}
		});
	}

	private void initHanSuDung() {
		// TODO Auto-generated method stub
		txtHanSuDung.setText(Integer.toString(thuoc.getHanSuDung()));
		txtHanSuDung.textProperty().addListener((o, old, newv) -> {
			if (!newv.matches("^[0-9]*")) {
				txtHanSuDung.setText(old);
			}
			
			checkHanSuDung();
		});
	}

	private boolean checkHanSuDung() {
		// TODO Auto-generated method stub
		if (txtHanSuDung.getText().isEmpty()) {
			lblHanSuDung.setText("Hạn sử dụng không được để trống.");
			return false;
		}else if(Integer.parseInt(txtHanSuDung.getText()) <= 0) {
			lblHanSuDung.setText("Hạn sử dụng phải lớn hơn 0 tháng.");
			return false;
		}
		lblHanSuDung.setText("");
		return true;
	}

	private void initNuocSanXuat() {
		// TODO Auto-generated method stub
		if (thuoc.getNuocSanXuat() != null) {
			txtNuocSanXuat.setText(thuoc.getNuocSanXuat());
		}
		
		txtNuocSanXuat.textProperty().addListener((o, old, newv) -> {
			checkNuocSanXuat();
		});
	}

	private boolean checkNuocSanXuat() {
		// TODO Auto-generated method stub
		if (txtNuocSanXuat.getText().isEmpty()) {
			lblNuocSanXuat.setText("Nước sản xuất không được để trống.");
			return false;
		}
		lblNuocSanXuat.setText("");
		return true;
	}

	private void initTenThuoc() {
		// TODO Auto-generated method stub
		txtTenThuoc.setText(thuoc.getTenThuoc());
		txtTenThuoc.textProperty().addListener((o, old, newv) -> {
			checkTenThuoc();
		});
	}

	private boolean checkTenThuoc() {
		// TODO Auto-generated method stub
		
		if (txtTenThuoc.getText().isEmpty()) {
			lblTenThuoc.setText("Tên thuốc không được để trống.");
			return false;
		}
		lblTenThuoc.setText("");
		return true;
	}

	private void initMaThuoc() {
		// TODO Auto-generated method stub
		txtMaThuoc.setEditable(false);
		txtMaThuoc.setText(thuoc.getId());
		txtMaThuoc.textProperty().addListener((o, old, newv) -> {
			checkMaThuoc();
		});
	}

	private boolean checkMaThuoc() {
		// TODO Auto-generated method stub
		if (txtMaThuoc.getText().isEmpty()) {
			lblMaThuoc.setText("Mã thuốc không được để chống.");
			return false;
		}
		lblMaThuoc.setText("");
		return true;
	}

	private void initDataNuocSanXuat() {
		// TODO Auto-generated method stub
		TextFields.bindAutoCompletion(txtNuocSanXuat, daoThuoc.getListNuocSanXuat());
	}

	private void initDataDonViTinh() {
		// TODO Auto-generated method stub
		ObservableList<String> donViTinhList = FXCollections.observableArrayList(daoThuoc.getListDonViTinh());
		cmbDonViTinh.setItems(donViTinhList);
		cmbDonViTinh.setValue(thuoc.getDonViTinh());
	}

	private void initDataNhaCungCap() {
		// TODO Auto-generated method stub
		ObservableList<String> nhaCungCapList = FXCollections.observableArrayList(daoNhaCungCap.getListTenNhaCungCap());
		cmbNhaCungCap.setItems(nhaCungCapList);
		cmbNhaCungCap.setValue(thuoc.getNhaCungCap().getTenNhaCungCap());
	}

	private void initDataLoaiThuoc() {
		// TODO Auto-generated method stub
		ObservableList<String> loaiThuocList = FXCollections.observableArrayList(daoLoaiThuoc.getListLoaiThuoc());
		cmbLoaiThuoc.setItems(loaiThuocList);
		cmbLoaiThuoc.setValue(thuoc.getLoaiThuoc().getTenLoai());
	}

}
