package GUI;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import DAO.DAONhanVien;
import entity.HoaDon;
import entity.NhanVienBanThuoc;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class MainSenceControl implements Initializable {
	private NhanVienBanThuoc nhanVienBanThuoc;
	private DAONhanVien daoNhanVien = new DAONhanVien();

	public Text lblTenNhanVien1;
	public Text lblTenNhanVien2;
	public Text lblId;
	public Text lblSoHoaDon;
	public StackPane stkOptions;
	public Circle cirAvatar;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader(getClass().getResource("pnlBanHang.fxml"));
		BanHangControl banHangControl = new BanHangControl(nhanVienBanThuoc);
		loader.setController(banHangControl);
		try {
			stkOptions.getChildren().add(loader.load());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lblTenNhanVien1.setText(nhanVienBanThuoc.getHoTenDem()+" "+nhanVienBanThuoc.getTen());
		lblTenNhanVien2.setText(nhanVienBanThuoc.getHoTenDem()+" "+nhanVienBanThuoc.getTen());
		lblId.setText("ID: "+nhanVienBanThuoc.getId());
		lblSoHoaDon.setText("Số hóa đơn: "+daoNhanVien.getTotalHoaDonByNhanVien(nhanVienBanThuoc.getId()));

		try {
			setAvatar();
		}catch (Exception e) {
			// TODO: handle exception
		}


	}


	private void setAvatar() {
		// TODO Auto-generated method stub
		//		set avatar
		InputStream in = new ByteArrayInputStream(nhanVienBanThuoc.getAvatar());
		Image avatar = new Image(in);
		cirAvatar.setFill(new ImagePattern(avatar));
	}


	public void setNhanVienBanThuoc(NhanVienBanThuoc nhanVienBanThuoc) {
		// TODO Auto-generated method stub
		this.nhanVienBanThuoc = nhanVienBanThuoc;

	}



}
