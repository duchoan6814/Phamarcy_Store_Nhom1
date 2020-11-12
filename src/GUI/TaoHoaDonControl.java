package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;


import DAO.DAOKhachHang;
import entity.NhanVienBanThuoc;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TaoHoaDonControl implements Initializable {
	
	private NhanVienBanThuoc nhanVienBanThuoc;
	private DAOKhachHang kh_dao = new DAOKhachHang();
	
	public Button btnThanhToan;
	public TextField txtMaNhanVien;
	public TextField txtTenNhanVien;
//	public TextField txtMaKhachHang;
	public TextField txtMaKhachHang;
	
	
	
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
		
//		TextFields.bindAutoCompletion(txtMaKhachHang, kh_dao.getAllMaKH());
		TextFields.bindAutoCompletion(txtMaKhachHang, kh_dao.getAllMaKH());
	}

}
