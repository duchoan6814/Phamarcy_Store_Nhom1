package GUI;

import java.net.URL;
import java.util.ResourceBundle;

import entity.QuanLy;
import javafx.fxml.Initializable;

public class KhoHangControl implements Initializable {
	private QuanLy quanLy;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}


	public QuanLy getQuanLy() {
		return quanLy;
	}
	public void setQuanLy(QuanLy quanLy) {
		this.quanLy = quanLy;
	}
}
