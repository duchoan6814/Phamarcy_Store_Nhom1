package GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;


import DAO.DAOHoaDon;
import DAO.DAOKhachHang;
import DAO.DAOThuoc;
import common.Common;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVienBanThuoc;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TaoHoaDonControl implements Initializable {

	private DAOKhachHang kh_dao = new DAOKhachHang();
	private DAOHoaDon hd_dao = new DAOHoaDon();
	private DAOThuoc thuoc_dao = new DAOThuoc();
	
	private KhachHang khachHang;
	private HoaDon hoaDon;
	private NhanVienBanThuoc nhanVienBanThuoc;

	public Button btnThanhToan;
	public TextField txtMaNhanVien;
	public TextField txtTenNhanVien;
	public TextField txtSoDienThoai;
	public TextField txtMaHoaDon;
	public TextField txtTenKhachHang;
	public Text lblDiemTichLuy;
	public DatePicker dateNgayLap;
	public Text lblXoaKH;
	public Pane btnXoaKH;
	public TextField txtMaThuoc;



	public TaoHoaDonControl(NhanVienBanThuoc nhanVienBanThuoc2) {
		// TODO Auto-generated constructor stub
		this.nhanVienBanThuoc = nhanVienBanThuoc2;
	}

	@FXML
	public void showDialogThanhToan() {
		try {
			Stage thanhToanStage = FXMLLoader.load(getClass().getResource("DialogThanhToan.fxml"));
			thanhToanStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		txtMaNhanVien.setText(nhanVienBanThuoc.getId());
		txtTenNhanVien.setText(nhanVienBanThuoc.getHoTenDem()+" "+nhanVienBanThuoc.getTen());
		try {
			TextFields.bindAutoCompletion(txtSoDienThoai, kh_dao.getAllSoDienThoai());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		TextFields.bindAutoCompletion(txtMaThuoc, thuoc_dao.getAllMaThuoc());
	}

	@FXML
	public void setKhachHangForHoaDonWhenAddKhachHang() {
		if (hoaDon == null) {
			khachHang = kh_dao.getKhachHangBySoDienThoai(txtSoDienThoai.getText());
			if (khachHang == null) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Số điện thoại bạn nhập không tồn tại, vui lòng nhập lại!");

				alert.showAndWait();
			}else {
				hoaDon = new HoaDon();
				hoaDon.setId(hd_dao.generateId());
				hoaDon.setKhachHang(khachHang);
				
				Date date= new Date();
				long time = date.getTime();
				hoaDon.setThoiGianLap(new Timestamp(time));
				
				txtMaHoaDon.setText(hoaDon.getId());
				txtTenKhachHang.setText(hoaDon.getKhachHang().getHoTenDem()+" "+hoaDon.getKhachHang().getTen());
				lblDiemTichLuy.setText("Điểm tích lũy: "+new Common().formatMoney(hoaDon.getKhachHang().getDienTichLuy()));
				dateNgayLap.setValue(LocalDate.now());
				txtSoDienThoai.setEditable(false);
				txtTenKhachHang.setEditable(false);
				btnXoaKH.setDisable(false);
				btnXoaKH.setStyle("-fx-background-color:  #F5508B; -fx-background-radius: 10px");
				lblXoaKH.setStyle("-fx-fill: #FFFFFF;");
			}

		}
	}
	
	@FXML
	public void xoaKhachHang() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText(null);
		alert.setContentText("Bạn có chắc chắn muốn xóa khách hànghàng?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
		    // ... user chose OK
			if (hoaDon.getDsChiTietHoaDon().size() <= 0) {
				hoaDon = null;
				
				txtMaHoaDon.setText("");
				txtTenKhachHang.setText("");
				txtSoDienThoai.setText("");
				lblDiemTichLuy.setText("Điểm tích lũy: 0đ");
				txtSoDienThoai.setEditable(true);
				txtTenKhachHang.setEditable(true);
				btnXoaKH.setDisable(true);
				dateNgayLap.setValue(null);
				btnXoaKH.setStyle("-fx-background-color: #DFDFDF; -fx-background-radius: 10px");
				lblXoaKH.setStyle("-fx-fill: #B1B1B1;");
			}else {
				hoaDon.setKhachHang(null);
				txtSoDienThoai.setText("");
				txtTenKhachHang.setText("");
			}
		} else {
		    // ... user chose CANCEL or closed the dialog
		}
		
	}

}
