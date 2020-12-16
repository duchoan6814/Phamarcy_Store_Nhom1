package GUI.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import DAO.DAOLoaiThuoc;
import common.Common;
import entity.LoaiThuoc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

public class QuanLyLoaiThuocControl implements Initializable {
	public TextField txtMaLoai;
	public TextField txtTenLoai;
	public TextField txtMoTa;
	public Button btnTim;
	public Button btnThem;
	public TableView<LoaiThuoc> tblLoaiThuoc;
	public TableColumn<LoaiThuoc, String> colMaLoai;
	public TableColumn<LoaiThuoc, String> colTenLoai;
	public TableColumn<LoaiThuoc, String> colMoTa;
	public TableColumn colSua;
	public TableColumn colXoa;
	
	private ObservableList<LoaiThuoc> dataTable;
	private DAOLoaiThuoc daoLoaiThuoc = new DAOLoaiThuoc();
	private Common common = new Common();
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		initTable();
		initButtonTim();
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
			FileInputStream stream = new FileInputStream(new File("src/GUI/ThemLoaiThuoc.fxml"));
			FXMLLoader loader = new FXMLLoader();
			ThemLoaiThuocControl control = new ThemLoaiThuocControl(this);
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


	private void initButtonTim() {
		// TODO Auto-generated method stub
		btnTim.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				actionButtonTim();
			}
		});
	}


	protected void actionButtonTim() {
		// TODO Auto-generated method stub
		dataTable.clear();
		List<LoaiThuoc> list = daoLoaiThuoc.filterLoaiThuoc(txtMaLoai.getText(), txtTenLoai.getText(), txtMoTa.getText());
		for (LoaiThuoc loaiThuoc : list) {
			dataTable.add(loaiThuoc);
		}
	}


	private void initTable() {
		// TODO Auto-generated method stub
		dataTable = FXCollections.observableArrayList();
		
		colMaLoai.setCellValueFactory(new PropertyValueFactory<>("id"));
		colMoTa.setCellValueFactory(new PropertyValueFactory<>("moTa"));
		colTenLoai.setCellValueFactory(new PropertyValueFactory<>("tenLoai"));
		
		createButtonSua();
		createButtonXoa();
		
		tblLoaiThuoc.setItems(dataTable);
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
							TableColumn col = tblLoaiThuoc.getColumns().get(0);
							String data = (String) col.getCellObservableValue(tblLoaiThuoc.getItems().get(getIndex())).getValue();
							Alert alert = new Alert(AlertType.CONFIRMATION);
							alert.setTitle("Xóa");
							alert.setHeaderText(null);
							alert.setContentText("Bạn có muốn xóa loại thuốc?");

							Optional<ButtonType> result = alert.showAndWait();
							if (result.get() == ButtonType.OK){
								// ... user chose OK'
								if (!daoLoaiThuoc.xoaLoaiThuocByID(data)) {
									common.showNotification(AlertType.ERROR, "ERROR", "Bạn không thể xóa loại thuốc này!");
								}else {
									common.showNotification(AlertType.INFORMATION, "INFORMATION", "Xóa thành công!");
									actionButtonTim();
								}
							} else {
								// ... user chose CANCEL or closed the dialog
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
							TableColumn col = tblLoaiThuoc.getColumns().get(1);
							String data = (String) col.getCellObservableValue(tblLoaiThuoc.getItems().get(getIndex())).getValue();
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

}
