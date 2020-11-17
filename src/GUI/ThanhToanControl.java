package GUI;

import java.net.URL;
import java.util.ResourceBundle;

import common.Common;
import entity.HoaDon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ThanhToanControl implements Initializable {
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
	}
	
	@FXML
	public void btnThoat(ActionEvent event) {
		Stage thanhToanStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		thanhToanStage.close();
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
		}else {
			lblTongTien.setText(common.formatMoney(hoaDon.tinhTienHoaDonChuaThue()));
			lblThue.setText(common.formatMoney(hoaDon.tinhThueHoaDon()));
			lblThanhTien.setText(common.formatMoney(hoaDon.getTienPhaiTra()));
			lblTenKhachHang.setText(hoaDon.getKhachHang().getHoTenDem()+" "+hoaDon.getKhachHang().getTen());
			lblDiemTichLuyKhachHang.setText(common.formatMoney(hoaDon.getKhachHang().getDienTichLuy()));
			lblSoDiemTichLuyDuoc.setText(common.formatMoney(hoaDon.tinhDiemTichLuy()));
		}
	}


}
