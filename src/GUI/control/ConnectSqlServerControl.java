package GUI.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

import DAO.DAO;
import common.Common;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConnectSqlServerControl implements Initializable {
	private Common common = new Common();
	
	public TextField txtIp;
	public TextField txtUserName;
	public TextField txtPassword;
	public Button btnDangNhap;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		txtIp.setText("localhost");
		txtUserName.setText("sa");
		txtPassword.setText("Tokelovip123");

		initButtonConnect();
	}

	private void initButtonConnect() {
		// TODO Auto-generated method stub
		btnDangNhap.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				DAO dao = new DAO(txtIp.getText(), txtUserName.getText(), txtPassword.getText());
				if (dao.getResult()) {
					try {
						FileInputStream fileInputStream = new FileInputStream(new File("src/GUI/Login.fxml"));
						FXMLLoader loader = new FXMLLoader();
						Parent root = loader.load(fileInputStream);
						Stage stage = new Stage();
						Scene scene = new Scene(root);
						stage.setScene(scene);
						stage.show();
						Stage stageCurrent = (Stage) ((Node)arg0.getSource()).getScene().getWindow();
						stageCurrent.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						common.showNotification(AlertType.ERROR, "ERROR", "Lỗi kết nối, vui lòng kiểm tra lại!");
					}
				}else {
					common.showNotification(AlertType.ERROR, "ERROR", "Lỗi kết nối, vui lòng kiểm tra lại!");
				}
			}
		});
	}




}
