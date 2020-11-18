package GUI;

import java.net.URL;
import java.util.ResourceBundle;

import common.Common;
import entity.HoaDon;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ThanhToanControl implements Initializable {
	private boolean trangThaiThanhToan = false;
	
	public boolean isTrangThaiThanhToan() {
		return trangThaiThanhToan;
	}

	public void setTrangThaiThanhToan(boolean trangThaiThanhToan) {
		this.trangThaiThanhToan = trangThaiThanhToan;
	}

	private Common common = new Common();

	private HoaDon hoaDon;

	public Text lblKhachHang;
	public Text lblTenKhachHang;
	public Text lblDiemTichLuy;
	public Text lblDiemTichLuyKhachHang;
	public Text lblSuDungDiemTichLuy;
	public TextField txtSuDungDiemTichLuy;
	public Text lblDiemTichLuyDuoc;
	public Text lblSoDiemTichLuyDuoc;

	public Text lblTongTien;
	public Text lblThue;
	public Text lblThanhTien;
	public Text lblTienThua;
	public TextField txtTienKhachDua;
	public Text lblTienPhaiTra;

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		khoiTaoThanhToan();
		handleThanhTienWhenSuDungDiemTichLuy();
		handleTienKhachDuaField();
	}

	private void handleTienKhachDuaField() {
		// TODO Auto-generated method stub
		txtTienKhachDua.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				if (!newValue.matches("^[0-9]*")) {
					txtTienKhachDua.setText(oldValue);
				}

				handleTienPhaiTra();
			}
		});
	}

	private void handleThanhTienWhenSuDungDiemTichLuy() {
		// TODO Auto-generated method stub
		txtSuDungDiemTichLuy.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				if (!newValue.matches("^[0-9]*")) {
					txtSuDungDiemTichLuy.setText(oldValue);
				}

				if (txtSuDungDiemTichLuy.getText().isEmpty()) {
					hoaDon.setDiemSuDung(0);
					lblTienPhaiTra.setText(common.formatMoney(hoaDon.getTienPhaiTra()));
					handleTienPhaiTra();
				}else {
					if (Double.parseDouble(txtSuDungDiemTichLuy.getText()) > hoaDon.getKhachHang().getDienTichLuy()) {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Information Dialog");
						alert.setHeaderText(null);
						alert.setContentText("Diểm tích lũy không đủ!");

						alert.showAndWait();

						txtSuDungDiemTichLuy.setText(oldValue);
						hoaDon.setDiemSuDung(Double.parseDouble(txtSuDungDiemTichLuy.getText()));
					}else {
						hoaDon.setDiemSuDung(Double.parseDouble(txtSuDungDiemTichLuy.getText()));

						if (hoaDon.getTienPhaiTra() < 0) {
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("Information Dialog");
							alert.setHeaderText(null);
							alert.setContentText("Tiền phải trả phải lớn hơn hoặc bằng 0!");

							alert.showAndWait();
							txtSuDungDiemTichLuy.setText(oldValue);
							hoaDon.setDiemSuDung(Double.parseDouble(txtSuDungDiemTichLuy.getText()));
						}else {
							lblTienPhaiTra.setText(common.formatMoney(hoaDon.getTienPhaiTra()));
							handleTienPhaiTra();
						}
					}
				}
			}
		});
	}

	private void handleTienPhaiTra() {
		// TODO Auto-generated method stub
		if (txtTienKhachDua.getText().isEmpty()) {
			lblTienThua.setText(common.formatMoney(0 - hoaDon.getTienPhaiTra()));
		}else {
			lblTienThua.setText(common.formatMoney(Double.parseDouble(txtTienKhachDua.getText())
					- hoaDon.getTienPhaiTra() 
					));
		}
	}

	private void khoiTaoThanhToan() {
		// TODO Auto-generated method stub
		if (this.hoaDon.getKhachHang() == null) {
			lblKhachHang.setVisible(false);
			lblTenKhachHang.setVisible(false);
			lblDiemTichLuy.setVisible(false);
			lblDiemTichLuyKhachHang.setVisible(false);
			lblSuDungDiemTichLuy.setVisible(false);
			txtSuDungDiemTichLuy.setVisible(false);
			lblDiemTichLuyDuoc.setVisible(false);
			lblSoDiemTichLuyDuoc.setVisible(false);

			lblTongTien.setText(common.formatMoney(hoaDon.tinhTienHoaDonChuaThue()));
			lblThue.setText(common.formatMoney(hoaDon.tinhThueHoaDon()));
			lblThanhTien.setText(common.formatMoney(hoaDon.tinhTienHoaDonBaoGomThue()));
			lblTienPhaiTra.setText(common.formatMoney(hoaDon.getTienPhaiTra()));
			lblTienThua.setText(common.formatMoney(0 - hoaDon.getTienPhaiTra()));
		}else {
			lblTongTien.setText(common.formatMoney(hoaDon.tinhTienHoaDonChuaThue()));
			lblThue.setText(common.formatMoney(hoaDon.tinhThueHoaDon()));
			lblThanhTien.setText(common.formatMoney(hoaDon.tinhTienHoaDonBaoGomThue()));
			lblTenKhachHang.setText(hoaDon.getKhachHang().getHoTenDem()+" "+hoaDon.getKhachHang().getTen());
			lblDiemTichLuyKhachHang.setText(common.formatMoney(hoaDon.getKhachHang().getDienTichLuy()));
			lblSoDiemTichLuyDuoc.setText(common.formatMoney(hoaDon.tinhDiemTichLuy()));
			lblTienPhaiTra.setText(common.formatMoney(hoaDon.getTienPhaiTra()));
			lblTienThua.setText(common.formatMoney(0 - hoaDon.getTienPhaiTra()));
		}
	}

	@FXML
	public void btnThoat(ActionEvent event) {
		Stage thanhToanStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		thanhToanStage.close();
	}
	
	@FXML
	public void btnThanhToan(ActionEvent event) {
		double tienKhachDua;
		if (txtTienKhachDua.getText().isEmpty()) {
			tienKhachDua = 0;
		}else {
			tienKhachDua = Double.parseDouble(txtTienKhachDua.getText());
		}
		
		if (tienKhachDua < hoaDon.getTienPhaiTra()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Khách chưa đưa đủ tiền!");

			alert.showAndWait();
		}else {
			System.out.println(hoaDon);
			this.trangThaiThanhToan = true;
		}
	}




}
