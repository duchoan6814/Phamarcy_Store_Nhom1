package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.gluonhq.impl.charm.a.b.b.a.c;

import DAO.DAOLoThuoc;
import DAO.DAOLoaiThuoc;
import DAO.DAOThuoc;
import common.Common;
import common.ThuocTable;
import entity.Thuoc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Thuoc2Control implements Initializable {

private BanHangControl banHangControl;
	
	
	public BanHangControl getBanHangControl() {
		return banHangControl;
	}

	public void setBanHangControl(BanHangControl banHangControl) {
		this.banHangControl = banHangControl;
	}

	Common common = new Common();

	DAOThuoc daoThuoc = new DAOThuoc();
	DAOLoaiThuoc daoLoaiThuoc = new DAOLoaiThuoc();
	DAOLoThuoc daoLoThuoc = new DAOLoThuoc();
	private ObservableList<ThuocTable> dataListThuoc;

	public ComboBox<String> cmbDonViTinh;
	public ComboBox<String> cmbLoaiThuoc;
	public ComboBox<String> cmbNuocSanXuat;
	public TableView<ThuocTable> tblThuoc;
	public TableColumn<ThuocTable, Integer> colSTT;
	public TableColumn<ThuocTable, String> colMaThuoc;
	public TableColumn<ThuocTable, String> colTenThuoc;
	public TableColumn<ThuocTable, String> colDVT;
	public TableColumn<ThuocTable, String> colGia;
	public TableColumn<ThuocTable, Integer> colTonKho;
	public TableColumn<ThuocTable, String> colHSD;
	public TableColumn<ThuocTable, String> colLoai;
	public TableColumn<ThuocTable, String> colNuoSX;
	public TableColumn<ThuocTable, String> colCongTy;
	public TableColumn colDelete;
	public TableColumn colEdit;
	public TextField txtMaThuoc;
	public TextField txtTenThuoc;
	public TextField txtNhaCungCap;
	public Button btnThem;
	public RadioButton rbtSoLuong;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		initNuocSanXuat();
		initDonViTinh();
		initLoaiThuoc();
		initTableView();
		createButtonChon();
		createButtonDelete();
		initButtonThem();
	}

	private void initButtonThem() {
		// TODO Auto-generated method stub
		btnThem.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				FXMLLoader loader = new FXMLLoader(getClass().getResource("DialogThemThuoc.fxml"));
				ThemThuocControl control = new ThemThuocControl();
				loader.setController(control);
				Stage themThuoc;
				try {
					themThuoc = loader.load();
					themThuoc.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
	}

	private void createButtonChon() {
		// TODO Auto-generated method stub
		Callback<TableColumn<common.ChiTietHoaDon, String>, TableCell<common.ChiTietHoaDon, String>> cellFactory
		= //
		new Callback<TableColumn<common.ChiTietHoaDon, String>, TableCell<common.ChiTietHoaDon, String>>()
		{
			@Override
			public TableCell call(final TableColumn<common.ChiTietHoaDon, String> param)
			{
				final TableCell<common.ChiTietHoaDon, String> cell = new TableCell<common.ChiTietHoaDon, String>()
				{

					final Button btn = new Button("edit");

					{
						btn.setStyle("-fx-background-color: orange");
						btn.setOnAction(event -> {
							TableColumn col = tblThuoc.getColumns().get(1);
							String data = (String) col.getCellObservableValue(tblThuoc.getItems().get(getIndex())).getValue();
							
							Thuoc thuoc = daoThuoc.getThuocById(data);
							if (thuoc == null) {
								common.showNotification(AlertType.ERROR, "ERROR", "Lỗi get dữ liệu!");
							}else {
								showDialogSua(data);
							}
						});
					}

					@Override
					public void updateItem(String item, boolean empty)
					{
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
							setText(null);
						}
						else {
							setGraphic(btn);
							setText(null);
						}
					}
				};
				return cell;
			}
		};

		colEdit.setCellFactory(cellFactory);
	}
	
	private void showDialogSua(String data) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("DialogThemThuoc.fxml"));
		SuaThuocControl control = new SuaThuocControl();
		control.setThuoc(data);
		control.setThuoc2Control(this);
		loader.setController(control);
		try {
			Stage stage = loader.load();
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void createButtonDelete() {
		// TODO Auto-generated method stub
		Callback<TableColumn<common.ChiTietHoaDon, String>, TableCell<common.ChiTietHoaDon, String>> cellFactory
		= //
		new Callback<TableColumn<common.ChiTietHoaDon, String>, TableCell<common.ChiTietHoaDon, String>>()
		{
			@Override
			public TableCell call(final TableColumn<common.ChiTietHoaDon, String> param)
			{
				final TableCell<common.ChiTietHoaDon, String> cell = new TableCell<common.ChiTietHoaDon, String>()
				{
					
					final Button btn = new Button("del");
					
					{
						btn.setStyle("-fx-background-color: red");
						btn.setOnAction(event -> {
							tblThuoc.getItems().remove(getIndex());
						});
					}
					
					@Override
					public void updateItem(String item, boolean empty)
					{
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
							setText(null);
						}
						else {
							setGraphic(btn);
							setText(null);
						}
					}
				};
				return cell;
			}
		};
		
		colDelete.setCellFactory(cellFactory);
	}

	private void initTableView() {
		// TODO Auto-generated method stub
		dataListThuoc = FXCollections.observableArrayList();
		colSTT.setCellValueFactory(new PropertyValueFactory<>("stt"));
		colMaThuoc.setCellValueFactory(new PropertyValueFactory<>("maThuoc"));
		colTenThuoc.setCellValueFactory(new PropertyValueFactory<>("tenThuoc"));
		colDVT.setCellValueFactory(new PropertyValueFactory<>("donViTinh"));
		colGia.setCellValueFactory(new PropertyValueFactory<>("gia"));
		colTonKho.setCellValueFactory(new PropertyValueFactory<>("tonKho"));
		colHSD.setCellValueFactory(new PropertyValueFactory<>("hanSuDung"));
		colLoai.setCellValueFactory(new PropertyValueFactory<>("loai"));
		colNuoSX.setCellValueFactory(new PropertyValueFactory<>("nuocSX"));
		colCongTy.setCellValueFactory(new PropertyValueFactory<>("congTy"));
		tblThuoc.setItems(dataListThuoc);
	}

	private void initLoaiThuoc() {
		// TODO Auto-generated method stub
		ObservableList<String> loaiThuocList = FXCollections.observableArrayList(daoLoaiThuoc.getListLoaiThuoc());
		loaiThuocList.add("Tất cả");
		cmbLoaiThuoc.setItems(loaiThuocList);
		cmbLoaiThuoc.setValue("Tất cả");
	}

	private void initDonViTinh() {
		// TODO Auto-generated method stub
		ObservableList<String> donViTinhList = FXCollections.observableArrayList(daoThuoc.getListDonViTinh());
		donViTinhList.add("Tất cả");
		cmbDonViTinh.setItems(donViTinhList);
		cmbDonViTinh.setValue("Tất cả");
	}

	private void initNuocSanXuat() {
		// TODO Auto-generated method stub
		ObservableList<String> nuocSanXuatList = FXCollections.observableArrayList(daoThuoc.getListNuocSanXuat());
		nuocSanXuatList.add("Tất cả");
		cmbNuocSanXuat.setItems(nuocSanXuatList);
		cmbNuocSanXuat.setValue("Tất cả");
	}

	@FXML
	public void actionButtonTim() {
		dataListThuoc.clear();
		List<Thuoc> listThuoc = daoThuoc.filterThuoc(txtMaThuoc.getText(), txtTenThuoc.getText(),
				cmbNuocSanXuat.getValue(), txtNhaCungCap.getText(), cmbLoaiThuoc.getValue(), cmbDonViTinh.getValue(), rbtSoLuong.isSelected());
		if (listThuoc.size() <= 0) {
			common.showNotification(AlertType.INFORMATION, "Không tìm thấy!", "Không tìm thấy thuốc phù hợp!");
		}else {
			listThuoc.forEach(i -> {
				dataListThuoc.add(new ThuocTable(dataListThuoc.size(), i.getId(), i.getTenThuoc(), i.getDonViTinh(), common.formatMoney(i.getGia()),
						daoThuoc.getSoLuongTon(i.getId()), i.getHanSuDung()+" Tháng", i.getLoaiThuoc().getTenLoai(), i.getNuocSanXuat(), i.getNhaCungCap().getTenNhaCungCap()));
			});
		}
	}
}
