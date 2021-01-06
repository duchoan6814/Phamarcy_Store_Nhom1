package GUI.control;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import DAO.DAOThuoc;
import common.Common;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ThemNuocSanXuatControl implements Initializable {
	public Text lblTenNuocSanXuat;
	public Button btnThem;
	public Button btnHuy;
	public TextField txtTenNuoc;
	
	private DAOThuoc daoThuoc = new DAOThuoc();
	private ThemThuocControl themThuocControl;
	private SuaThuocNewControl suaThuocNewControl;
	private Common common = new Common();

	public ThemNuocSanXuatControl(ThemThuocControl themThuocControl) {
		// TODO Auto-generated constructor stub
		this.themThuocControl = themThuocControl;
	}

	public ThemNuocSanXuatControl(SuaThuocNewControl suaThuocNewControl) {
		// TODO Auto-generated constructor stub
		this.suaThuocNewControl = suaThuocNewControl;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		TextFields.bindAutoCompletion(txtTenNuoc, daoThuoc.getAllCountry());
		initButtonThem();
		initButtonHuy();
	}
	
	private void initButtonHuy() {
		// TODO Auto-generated method stub
		btnHuy.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				actionButtonHuy(arg0);
			}
		});
	}

	private void actionButtonHuy(ActionEvent arg0) {
		Stage thanhToanStage = (Stage) ((Node)arg0.getSource()).getScene().getWindow();
		thanhToanStage.close();
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

	protected void actionButtonThem(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (!daoThuoc.checkCountry(txtTenNuoc.getText())) {
			lblTenNuocSanXuat.setText("* Quốc gia không tồn tại!");
		}else {
			common.showNotification(AlertType.INFORMATION, "INFORMATION", "Thêm thành công!");
			this.themThuocControl.listNuocSanXuat.add(txtTenNuoc.getText());
			this.themThuocControl.cmbNuocSanXuat.setValue(txtTenNuoc.getText());
			actionButtonHuy(arg0);
		}
	}

}
