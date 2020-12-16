package GUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import DAO.DAONhaCungCap;
import GUI.control.SuaNhaCungCapControl;
import GUI.control.ThemNhaCungCapControl;
import common.Common;
import common.PhieuNhapTable;
import common.QLNhaCungCapTable;
import entity.NhaCungCap;
import entity.Thuoc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class QuanLyNCCConTrol implements Initializable {
	ObservableList<QLNhaCungCapTable> data;
	private DAONhaCungCap daoNhaCungCap = new DAONhaCungCap();
	Common common = new Common();
	
	
	public TextField txtMa;
	public TextField txtTen;
	public TextField txtSDT;
	public TextField txtFax;
	public TextField txtTrangChu;
	public TextField txtDiaChi;
	public Button btnTim;
	public Button btnThem;
	
	public TableView<QLNhaCungCapTable> tabNCC;
	public TableColumn<QLNhaCungCapTable, Integer> colSTT;
	public TableColumn<QLNhaCungCapTable, String> colMa;
	public TableColumn<QLNhaCungCapTable, String> colTen;
	public TableColumn<QLNhaCungCapTable, String> colSDT;
	public TableColumn<QLNhaCungCapTable, String> colFax;
	public TableColumn<QLNhaCungCapTable, String> colTrangChu;
	public TableColumn<QLNhaCungCapTable, String> colDiaChi;
	public TableColumn colSua;
	public TableColumn colXoa;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initbtnTim();
		initTable();
		initButtonThem();
	}

	private void initButtonThem() {
		// TODO Auto-generated method stub
		btnThem.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				actionButtonThem();
			}
		});
	}

	protected void actionButtonThem() {
		// TODO Auto-generated method stub
		try {
			FileInputStream stream = new FileInputStream(new File("src/GUI/ThemNhaCungCap.fxml"));
			FXMLLoader loader = new FXMLLoader();
			ThemNhaCungCapControl control = new ThemNhaCungCapControl(this);
			loader.setController(control);
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

	private void initTable() {
		data = FXCollections.observableArrayList();
		colSTT.setCellValueFactory(new PropertyValueFactory<>("STT"));
		colTen.setCellValueFactory(new PropertyValueFactory<>("tenNCC"));
		colMa.setCellValueFactory(new PropertyValueFactory<>("maNCC"));
		colSDT.setCellValueFactory(new PropertyValueFactory<>("sdt"));
		colFax.setCellValueFactory(new PropertyValueFactory<>("fax"));
		colTrangChu.setCellValueFactory(new PropertyValueFactory<>("trangChu"));
		colDiaChi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
		
		createButtonSua();
		createButtonXoa();
		
		tabNCC.setItems(data);
	}

	private void createButtonXoa() {
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

					final Button btn = new Button("Xóa");

					{
						btn.setStyle("-fx-background-color: red");
						btn.setOnAction(event -> {
							TableColumn col = tabNCC.getColumns().get(1);
							String data = (String) col.getCellObservableValue(tabNCC.getItems().get(getIndex())).getValue();
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

		colXoa.setCellFactory(cellFactory);
	}

	private void createButtonSua() {
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

					final Button btn = new Button("Sửa");

					{
						btn.setStyle("-fx-background-color: orange");
						btn.setOnAction(event -> {
							TableColumn col = tabNCC.getColumns().get(1);
							String data = (String) col.getCellObservableValue(tabNCC.getItems().get(getIndex())).getValue();
							showStageSuaNhaCungCap(data);
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

		colSua.setCellFactory(cellFactory);
	}
	
	private void showStageSuaNhaCungCap(String id) {
		// TODO Auto-generated method stub
		NhaCungCap cap = daoNhaCungCap.getNhaCungCapByID(id);
		try {
			FileInputStream fileInputStream = new FileInputStream(new File("src/GUI/ThemNhaCungCap.fxml"));
			FXMLLoader loader = new FXMLLoader();
			SuaNhaCungCapControl capControl = new SuaNhaCungCapControl(this, cap);
			loader.setController(capControl);
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

	private void initbtnTim() {
		
		btnTim.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				actionButtonTim();
			}
		});
	}

	public void actionButtonTim() {
		// TODO Auto-generated method stub
		data.clear();
		
		List<NhaCungCap> list = daoNhaCungCap.filterPhieuNhapNCC(txtMa.getText(), txtTen.getText(), txtSDT.getText(), txtFax.getText(), txtTrangChu.getText(), txtDiaChi.getText());
		if(list.size() <= 0) {
			common.showNotification(AlertType.INFORMATION, "No Data", "Không tìm thấy kết quả phù hợp!");
		}else {
			list.forEach(i -> {
				QLNhaCungCapTable capTable = new QLNhaCungCapTable(data.size(), i.getId(), i.getTenNhaCungCap(), i.getSoDienThoai(), i.getEmail(), i.getTrangChu(), i.getDiaChi());
				data.add(capTable);
			});
		}
	}
	
}
