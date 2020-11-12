package GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;

import com.gluonhq.charm.glisten.control.Alert;

import DAO.DAOKhachHang;
import common.Toast;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVienBanThuoc;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TaoHoaDonControl implements Initializable {

	private NhanVienBanThuoc nhanVienBanThuoc;
	private DAOKhachHang kh_dao = new DAOKhachHang();
	private KhachHang khachHang;
	private HoaDon hoaDon;

	public Button btnThanhToan;
	public TextField txtMaNhanVien;
	public TextField txtTenNhanVien;
	//	public TextField txtMaKhachHang;
	public TextField txtSoDienThoai;



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
	}

	@FXML
	public void setKhachHangForHoaDonWhenAddKhachHang() {
		System.out.println("...");
		if (hoaDon == null) {
			khachHang = kh_dao.getKhachHangBySoDienThoai(txtSoDienThoai.getText());
			if (khachHang == null) {
				
			}
			hoaDon = new HoaDon("HD121120001");

		}
	}

}
