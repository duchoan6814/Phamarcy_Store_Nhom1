package GUI.control;

import java.net.URL;
import java.util.List;
import java.util.ListResourceBundle;
import java.util.ResourceBundle;

import DAO.DAONhanVien;
import GUI.MainSenceControl;
import common.Common;
import common.QuanLyNhanVienTable;
import entity.NhanVienBanThuoc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class NhanVienControl implements Initializable {
	public TextField txtMaNhanVien;
	public TextField txtHoTenNhanVien;
	public TextField txtSoDienThoai;
	public TextField txtSoCMND;
	public ComboBox<String> cmbGioiTinh;
	public ComboBox<String> cmbChucVu;
	public Button btnTim;
	public Button btnThem;
	public TableView<QuanLyNhanVienTable> tblNhanVien;
	public TableColumn<QuanLyNhanVienTable, Integer> colSTT;
	public TableColumn<QuanLyNhanVienTable, String> colMaNhanVien;
	public TableColumn<QuanLyNhanVienTable, String> colTenNhanVien;
	public TableColumn<QuanLyNhanVienTable, String> colNgaySinh;
	public TableColumn<QuanLyNhanVienTable, String> colSoDienThoai;
	public TableColumn<QuanLyNhanVienTable, String> colSoCMND;
	public TableColumn<QuanLyNhanVienTable, String> colGioiTinh;
	public TableColumn<QuanLyNhanVienTable, String> colPhanQuyen;
	public TableColumn colSua;
	public TableColumn colXoa;
	
	private ObservableList<QuanLyNhanVienTable> dataTable;
	private Common common = new Common();
	private DAONhanVien daoNhanVien = new DAONhanVien();
	private MainSenceControl mainSenceControl;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		initTable();
		initButtonTim();
		initGioiTinhField();
		initPhanQuyenField();
		initButtonThem();
	}


	private void initButtonThem() {
		// TODO Auto-generated method stub
		btnThem.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				mainSenceControl.showThemNhanVien();
			}
		});
		
	}


	private void initPhanQuyenField() {
		// TODO Auto-generated method stub
		ObservableList<String> listPhanQuyen = FXCollections.observableArrayList();
		listPhanQuyen.add("Tất Cả");
		listPhanQuyen.add("NHANVIEN");
		listPhanQuyen.add("QUANLY");
		cmbChucVu.setItems(listPhanQuyen);
		cmbChucVu.setValue(listPhanQuyen.get(0));
	}


	private void initGioiTinhField() {
		// TODO Auto-generated method stub
		ObservableList<String> listGioiTinh = FXCollections.observableArrayList();
		listGioiTinh.add("Cả Hai");
		listGioiTinh.add("Nam");
		listGioiTinh.add("Nữ");
		cmbGioiTinh.setItems(listGioiTinh);
		cmbGioiTinh.setValue(listGioiTinh.get(0));
	}


	private void initButtonTim() {
		// TODO Auto-generated method stub
		btnTim.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dataTable.clear();
				List<NhanVienBanThuoc> list = daoNhanVien.filterNhanVienBanThuoc(txtMaNhanVien.getText(),
						txtHoTenNhanVien.getText(), txtSoDienThoai.getText(), cmbGioiTinh.getValue(),
						cmbChucVu.getValue(), txtSoCMND.getText());
				if (list.size() <= 0) {
					common.showNotification(AlertType.INFORMATION, "INFORMATION", "Không tìm thấy kết quả phù hợp!");
				}else {
					for (NhanVienBanThuoc nhanVienBanThuoc : list) {
						String _ngaySinh = null;
						try {
							_ngaySinh = nhanVienBanThuoc.getNgaySinh().toString();
						} catch (Exception e) {
							// TODO: handle exception
						}
						dataTable.add(new QuanLyNhanVienTable(dataTable.size(), nhanVienBanThuoc.getId(),
								nhanVienBanThuoc.getHoTenDem()+" "+nhanVienBanThuoc.getTen(),
								_ngaySinh, nhanVienBanThuoc.getSoDienThoai(),
								nhanVienBanThuoc.getSoCMND(), nhanVienBanThuoc.isGioiTinh() ? "Nam" : "Nữ", nhanVienBanThuoc.getTaiKhoan().getPhanQuyen().getPhanQuyen()));
					}
				}
			}
		});
	}


	private void initTable() {
		// TODO Auto-generated method stub
		dataTable = FXCollections.observableArrayList();
		
		colSTT.setCellValueFactory(new PropertyValueFactory<>("stt"));
		colMaNhanVien.setCellValueFactory(new PropertyValueFactory<>("maNhanVien"));
		colTenNhanVien.setCellValueFactory(new PropertyValueFactory<>("tenNhanVien"));
		colNgaySinh.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
		colSoDienThoai.setCellValueFactory(new PropertyValueFactory<>("soDienThoai"));
		colSoCMND.setCellValueFactory(new PropertyValueFactory<>("soCMND"));
		colGioiTinh.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
		colPhanQuyen.setCellValueFactory(new PropertyValueFactory<>("phanQuyen"));
		
		createButtonSua();
		createButtonXoa();
		
		tblNhanVien.setItems(dataTable);
		
		
	}


	private void createButtonXoa() {
		// TODO Auto-generated method stub
		
	}


	private void createButtonSua() {
		// TODO Auto-generated method stub
		
	}


	public void setMainSenceControl(MainSenceControl mainSenceControl) {
		// TODO Auto-generated method stub
		this.mainSenceControl = mainSenceControl;
	}

}
