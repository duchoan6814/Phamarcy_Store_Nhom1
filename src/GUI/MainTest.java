package GUI;

import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.*;

public class MainTest extends Application {

	public static void main(String passes[]) {
		// TODO Auto-generated method stub
		launch(passes);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		Scene scene = new Scene(root);
//		arg0.initStyle(StageStyle.TRANSPARENT);
		arg0.setTitle("TestAPP");
		arg0.setScene(scene);
		arg0.show();
	}

}
