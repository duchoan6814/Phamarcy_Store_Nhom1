package GUI;


import java.io.IOException;
import java.util.ResourceBundle;

import DAO.DAONhanVien;
import DAO.DAOTaiKhoan;
import entity.NhanVienBanThuoc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class LoginControl{

	DAOTaiKhoan tk = new DAOTaiKhoan();
	DAONhanVien nv_dao = new DAONhanVien();

	public TextField userName;
	public PasswordField password;
	public Button buttonLogin;

	@FXML
	public void login(ActionEvent event) {
		boolean isLoginSuccess = tk.login(userName.getText(), password.getText());
		if (isLoginSuccess) {
			showSenceMain();
			closeStageWhenLoginSuccess(event);
		}

	}

	public void showSenceMain() {
//		Parent root;
		try {
//			root = FXMLLoader.load(getClass().getResource("MainSence.fxml"));
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MainSence.fxml"));
			NhanVienBanThuoc nhanVienBanThuoc = nv_dao.getNhanVienBanThuocByUserName(userName.getText());
			MainSenceControl controller = new MainSenceControl();
			controller.setNhanVienBanThuoc(nhanVienBanThuoc);
			loader.setController(controller);
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			//			arg0.initStyle(StageStyle.TRANSPARENT);
			stage.setTitle("TestAPP");
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void closeStageWhenLoginSuccess(ActionEvent event) {
		Stage stage = (Stage) buttonLogin.getScene().getWindow();
		stage.close();
	}

}
