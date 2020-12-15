package GUI.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import DAO.DAOLoaiThuoc;
import DAO.DAONhaCungCap;
import DAO.DAOThuoc;
import GUI.Thuoc2Control;
import common.Common;
import entity.Thuoc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ThemThuocControl implements Initializable {
	public TextField txtMaThuoc;
	public TextField txtTenThuoc;
	public TextField txtHanSuDung;
	public TextField txtGiaThuoc;
	public ComboBox<String> cmbLoaiThuoc;
	public ComboBox<String> cmbNhaCungCap;
	public ComboBox<String> cmbNuocSanXuat;
	public ComboBox<String> cmbDonViTinh;
	public ComboBox<String> cmbDangBaoChe;
	public ComboBox<String> cmbQuyCachDongGoi;
	public Button btnLoaiThuoc;
	public Button btnNhaCungCap;
	public Button btnNuocSanXuat;
	public Button btnDonViTinh;
	public Button btnDangBaoChe;
	public Button btnQuyCachDongGoi;
	public Button btnThem;
	public Button btnHuy;
	public TextArea txaMoTa;
	public Text lblTenThuoc;
	public Text lblGiaThuoc;
	public Text lblHanSuDung;

	private Thuoc2Control thuoc2Control;
	private DAOLoaiThuoc daoLoaiThuoc = new DAOLoaiThuoc();
	private DAONhaCungCap daoNhaCungCap = new DAONhaCungCap();
	private DAOThuoc daoThuoc = new DAOThuoc();
	private Common common = new Common();
	private ObservableList<String> listLoaiThuoc;
	public ObservableList<String> listNhaCungCap;
	public ObservableList<String> listNuocSanXuat;
	public ObservableList<String> listDonViTinh;



	public ObservableList<String> getListLoaiThuoc() {
		return listLoaiThuoc;
	}

	public void setListLoaiThuoc(ObservableList<String> listLoaiThuoc) {
		this.listLoaiThuoc = listLoaiThuoc;
	}

	public ThemThuocControl(Thuoc2Control thuoc2Control) {
		// TODO Auto-generated constructor stub
		this.thuoc2Control = thuoc2Control;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		initButtonHuy();
		initField();
		initButtonThem();
	}

	//=============================================
	private void initField() {
		// TODO Auto-generated method stub
		initMaThuoc();
		initTenThuoc();
		initGiaThuoc();
		initHSD();
		initLoaiThuoc();
		initNhaCungCap();
		initNuocSanXuat();
		initDonViTinh();
	}

	private void initDonViTinh() {
		// TODO Auto-generated method stub
		listDonViTinh = FXCollections.observableArrayList(daoThuoc.getListDonViTinh());
		cmbDonViTinh.setItems(listDonViTinh);
		cmbDonViTinh.setValue(listDonViTinh.get(0));
	}
	//=========================================================
	private void initNuocSanXuat() {
		// TODO Auto-generated method stub
		listNuocSanXuat = FXCollections.observableArrayList(daoThuoc.getListNuocSanXuat());
		cmbNuocSanXuat.setItems(listNuocSanXuat);
		cmbNuocSanXuat.setValue(listNuocSanXuat.get(0));
		btnNuocSanXuat.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				actionButtonNuocSanXuat();
			}
		});
	}
	protected void actionButtonNuocSanXuat() {
		// TODO Auto-generated method stub
		FileInputStream fileInputStream;
		try {
			fileInputStream = new FileInputStream(new File("src/GUI/ThemCountry.fxml"));
			FXMLLoader loader = new FXMLLoader();
			ThemNuocSanXuatControl themNuocSanXuatControl = new ThemNuocSanXuatControl(this);
			loader.setController(themNuocSanXuatControl);
			
			Stage stage = loader.load(fileInputStream);
			stage.show();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//========================================
	private void initNhaCungCap() {
		// TODO Auto-generated method stub
		listNhaCungCap = FXCollections.observableArrayList(daoNhaCungCap.getListTenNhaCungCap());
		cmbNhaCungCap.setItems(listNhaCungCap);
		cmbNhaCungCap.setValue(listNhaCungCap.get(0));
		btnNhaCungCap.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				actionButtonNhaCungCap();
			}
		});
	}
	
	private void actionButtonNhaCungCap() {
		// TODO Auto-generated method stub
		FileInputStream stream;
		try {
			stream = new FileInputStream(new File("src/GUI/ThemNhaCungCap.fxml"));
			FXMLLoader loader = new FXMLLoader();
			ThemNhaCungCapControl capControl = new ThemNhaCungCapControl(this);
			loader.setController(capControl);
			
			Stage stage = loader.load(stream);
			stage.show();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//=======================================================
	private void initLoaiThuoc() {
		// TODO Auto-generated method stub
		listLoaiThuoc = FXCollections.observableArrayList(daoLoaiThuoc.getListLoaiThuoc());
		cmbLoaiThuoc.setItems(listLoaiThuoc);
		cmbLoaiThuoc.setValue(listLoaiThuoc.get(0));

		btnLoaiThuoc.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				actionButtonThemLoaiThuoc(arg0);
			}
		});
	}

	private void actionButtonThemLoaiThuoc(ActionEvent arg0) {
		// TODO Auto-generated method stub
		try {
			FileInputStream fileInputStream = new FileInputStream(new File("src/GUI/ThemLoaiThuoc.fxml"));
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setController(new ThemLoaiThuocControl(this));
			Stage themLoaiThuocStage = fxmlLoader.load(fileInputStream);
			themLoaiThuocStage.show();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//============================================
	private void initHSD() {
		// TODO Auto-generated method stub
		txtHanSuDung.textProperty().addListener((ob, old, newv) -> {
			if (!newv.matches("^[0-9]*$")) {
				txtHanSuDung.setText(old);
			}
			checkHanSuDung();
		});
	}

	private boolean checkHanSuDung() {
		// TODO Auto-generated method stub

		if (txtHanSuDung.getText().isEmpty()) {
			lblHanSuDung.setText("* Hạn sử dụng không được để trống!");
			return false;
		}
		
		if (!isStringInt(txtHanSuDung.getText())) {
			lblHanSuDung.setText("* Tháng quá lớn!");
			return false;
		}

		if (Integer.parseInt(txtHanSuDung.getText()) <= 0) {
			lblHanSuDung.setText("* Hạn sử dụng phải lớn hơn 0");
			return false;
		}

		lblHanSuDung.setText("*");
		return true;
	}

	public boolean isStringInt(String s){
		try  {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}
	
	public boolean isStringDouble(String s){
		try  {
			Double.parseDouble(s);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}

	//	=====================================================
	private void initGiaThuoc() {
		// TODO Auto-generated method stub
		txtGiaThuoc.textProperty().addListener((ob, old, newv) -> {
			if (!newv.matches("^[0-9]*$")) {
				txtGiaThuoc.setText(old);
			}
			checkGiaThuoc();
		});
	}
	private boolean checkGiaThuoc() {
		// TODO Auto-generated method stu		
		if (txtGiaThuoc.getText().isEmpty()) {
			lblGiaThuoc.setText("* Giá thuốc không được để trống!");
			return false;
		}
		
		if (!isStringDouble(txtGiaThuoc.getText())) {
			lblGiaThuoc.setText("* Giá quá lớn vượt ra khỏi giới hạn!");
			return false;
		}
		
		lblGiaThuoc.setText("*");
		return true;
	}

	//==============================================
	private void initTenThuoc() {
		// TODO Auto-generated method stub
		txtTenThuoc.textProperty().addListener((ob, old, newv) -> {
			checkTenThuoc();
		});
	}

	private boolean checkTenThuoc() {
		// TODO Auto-generated method stub
		if (txtTenThuoc.getText().isEmpty()) {
			lblTenThuoc.setText("* Tên Thuốc không được bỏ trống!");
			return false;
		}
		lblTenThuoc.setText("*");
		return true;
	}

	//=======================================================
	private void initMaThuoc() {
		// TODO Auto-generated method stub
		txtMaThuoc.setEditable(false);
		txtMaThuoc.setText(daoThuoc.generateID());
	}

	private void initButtonHuy() {
		// TODO Auto-generated method stub
		btnHuy.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				thuoc2Control.showquanLyThuoc();
			}
		});
	}

	private void initButtonThem() {
		// TODO Auto-generated method stub
		btnThem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				boolean dk1 = checkTenThuoc();
				boolean dk2 = checkGiaThuoc();
				boolean dk3 = checkHanSuDung();
				if (!dk1 || !dk2 || !dk3) {
					common.showNotification(AlertType.ERROR, "ERROR", "Không thể thêm, vui lòng kiểm tra lại các điều kiện!");
				}else {
					Thuoc thuoc = new Thuoc(txtMaThuoc.getText(),
							txtTenThuoc.getText(), txaMoTa.getText(), cmbDonViTinh.getValue(),
							null, null,
							Integer.parseInt(txtHanSuDung.getText()), 0, Double.parseDouble(txtGiaThuoc.getText()),
							0.05, daoNhaCungCap.getNhaCungCapByName(cmbNhaCungCap.getValue()),
							daoLoaiThuoc.getLoaiThuocByTen(cmbLoaiThuoc.getValue()), cmbNuocSanXuat.getValue());

					if (!daoThuoc.themThuocMoi(thuoc)) {
						common.showNotification(AlertType.ERROR, "ERROR", "Lỗi thêm thuốc không thành công, vui lòng kiểm tra lại!");
					}else {
						common.showNotification(AlertType.INFORMATION, "Information", "Thêm thuốc thành công.");
						thuoc2Control.showquanLyThuoc();
						thuoc2Control.actionButtonTim();
					}
				}
			}
		});
	}

}
