package GUI;

import java.io.File;
import java.io.FileInputStream;

import DAO.DAO;
import GUI.control.ConnectSqlServerControl;
import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.*;

public class MainTest extends Application {

	public static void main(String passes[]) {
		// TODO Auto-generated method stub
		DAO dao = new DAO("localhost", "sa", "Tokelovip123");
		launch(passes);
	}

	@Override
	public void start(Stage arg0) throws Exception {		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ConnectSqlServer.fxml"));
		loader.setController(new ConnectSqlServerControl());
		Stage stage = loader.load();
		stage.show();
	}

}
