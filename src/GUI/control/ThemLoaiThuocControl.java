package GUI.control;

import java.net.URL;
import java.util.ResourceBundle;

import DAO.DAOLoaiThuoc;
import common.Common;
import entity.LoaiThuoc;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ThemLoaiThuocControl implements Initializable {
	public TextField txtMaLoai;
	public TextField txtTenLoai;
	public TextArea txaMoTa;
	public Button btnThem;
	public Button btnHuy;
	public Text lblTenLoai;
	
	
	private Common common = new Common();
	private DAOLoaiThuoc daoLoaiThuoc = new DAOLoaiThuoc();
	private ThemThuocControl themThuocControl;
	
	public ThemLoaiThuocControl(ThemThuocControl themThuocControl) {
		// TODO Auto-generated constructor stub
		this.themThuocControl = themThuocControl;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		initTenLoai();
		initButtonHuy();
		initButtonThem();
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
	
	private void actionButtonThem(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (!checkTenLoaiThuoc()) {
			common.showNotification(AlertType.ERROR, "ERROR", "Lỗi thêm loại thuốc vui lòng kiểm tra lại điều kiện!");
		}else {
			LoaiThuoc loaiThuoc = new LoaiThuoc(txtMaLoai.getText(), txtTenLoai.getText(), txaMoTa.getText());
			if (!daoLoaiThuoc.themLoaiThuoc(loaiThuoc)) {
				common.showNotification(AlertType.ERROR, "ERROR", "Lỗi thêm vui lòng kiểm tra lại!");
			}else {
				common.showNotification(AlertType.INFORMATION, "INFORMATION", "Thêm thành công.");
				this.themThuocControl.getListLoaiThuoc().add(txtTenLoai.getText());
				Stage thanhToanStage = (Stage) ((Node)arg0.getSource()).getScene().getWindow();
				thanhToanStage.close();
			}
		}
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


	private void initTenLoai() {
		// TODO Auto-generated method stub
		txtTenLoai.textProperty().addListener((ob, old, newv) -> {
			checkTenLoaiThuoc();
		});
	}


	private boolean checkTenLoaiThuoc() {
		// TODO Auto-generated method stub
		if (txtTenLoai.getText().isEmpty()) {
			lblTenLoai.setText("* Tên loại thuốc không được bỏ trống!");
			return false;
		}
		lblTenLoai.setText("*");
		return true;
	}

}
