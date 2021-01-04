package GUI.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class ThongKeGlobal implements Initializable {
	
	public TabPane tabsThongKe;
	public Tab tabDoanhThu;
	public Tab tabThongKeThuoc;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		initLayoutTongKe();
	}

	private void initLayoutTongKe() {
		// TODO Auto-generated method stub
		initContentThongKeDoanhThu();
		initContentThongKeThuoc();
	}

	private void initContentThongKeThuoc() {
		// TODO Auto-generated method stub
		tabThongKeThuoc.setOnSelectionChanged(new EventHandler<Event>() {
			
			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				if (tabThongKeThuoc.isSelected()) {
					getgetFieldPnlThongKeThuoc();
				}
			}
		});
	}

	protected void getgetFieldPnlThongKeThuoc() {
		// TODO Auto-generated method stub
		try {
			FileInputStream fileInputStream = new FileInputStream(new File("src/GUI/ThongKeThuoc.fxml"));
			FXMLLoader loader = new FXMLLoader();
			ThongKeThuocControl control = new ThongKeThuocControl();
			loader.setController(control);
			
			tabThongKeThuoc.setContent(loader.load(fileInputStream));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void initContentThongKeDoanhThu() {
		// TODO Auto-generated method stub
		getFilePnlThongKeDoanhThu();
		
		tabDoanhThu.setOnSelectionChanged(new EventHandler<Event>() {
			
			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				if (tabDoanhThu.isSelected()) {
					getFilePnlThongKeDoanhThu();
				}
			}
		});
	}

	private void getFilePnlThongKeDoanhThu() {
		// TODO Auto-generated method stub
		try {
			FileInputStream inputStream = new FileInputStream(new File("src/GUI/ThongKeDoanhThu.fxml"));
			FXMLLoader fxmlLoader = new FXMLLoader();
			ThongKeDoanhThuControl control = new ThongKeDoanhThuControl();
			fxmlLoader.setController(control);
			try {
				tabDoanhThu.setContent(fxmlLoader.load(inputStream));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
