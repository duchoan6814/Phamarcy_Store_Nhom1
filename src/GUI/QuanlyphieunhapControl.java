package GUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import DAO.DAOPhieuNhap;
import GUI.control.ChiTietPhieuNhapControl;
import common.Common;
import common.PhieuNhapTable;
import entity.PhieuNhapHang;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

public class QuanlyphieunhapControl implements Initializable {
	ObservableList<PhieuNhapTable> data;
	DAOPhieuNhap daoPhieuNhap = new DAOPhieuNhap();
	Common common = new Common();
	
	public DatePicker dateNgayLapFrom;
	public DatePicker dateNgayLapTo;
	public TextField txtNhanVien;
	public TextField txtMaPhieu;
	public Button btnTim;
	public RadioButton rbtTatCaThoiGian;
	
	public TableView<PhieuNhapTable> tblPhieuNhap;
	public TableColumn<PhieuNhapTable, Integer> colSTT;
	public TableColumn<PhieuNhapTable, String> colMa;
	public TableColumn<PhieuNhapTable, String> colNgayNhap;
	public TableColumn<PhieuNhapTable, String> colTongTien;
	public TableColumn<PhieuNhapTable, String> colQuanLy;
	public TableColumn colXem;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initbtnTim();
		initTable();
		initButtonRadio();
		initSomeField();
		
	}
	
	private void initSomeField() {
		// TODO Auto-generated method stub
		dateNgayLapFrom.setDisable(true);
		dateNgayLapTo.setDisable(true);
		dateNgayLapFrom.setValue(LocalDate.now());
		dateNgayLapTo.setValue(LocalDate.now());
	}
	private void initButtonRadio() {
		// TODO Auto-generated method stub
		rbtTatCaThoiGian.setSelected(true);
		rbtTatCaThoiGian.selectedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				// TODO Auto-generated method stub
				if (rbtTatCaThoiGian.isSelected()) {
					dateNgayLapFrom.setDisable(true);
					dateNgayLapTo.setDisable(true);
				}else {
					dateNgayLapFrom.setDisable(false);
					dateNgayLapTo.setDisable(false);
				}
			}
		});
	}
	private void initTable() {
		data = FXCollections.observableArrayList();
		
		colSTT.setCellValueFactory(new PropertyValueFactory<>("STT"));
		colMa.setCellValueFactory(new PropertyValueFactory<>("maPhieu"));
		colNgayNhap.setCellValueFactory(new PropertyValueFactory<>("ngayNhap"));
		colTongTien.setCellValueFactory(new PropertyValueFactory<>("tongTien"));
		colQuanLy.setCellValueFactory(new PropertyValueFactory<>("quanLy"));
		
		createButtonXem();
		
		tblPhieuNhap.setItems(data);
	}
	private void createButtonXem() {
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

					final Button btn = new Button("xem");

					{
						btn.setStyle("-fx-background-color: green");
						btn.setOnAction(event -> {
							TableColumn col = tblPhieuNhap.getColumns().get(1);
							String data = (String) col.getCellObservableValue(tblPhieuNhap.getItems().get(getIndex())).getValue();
							PhieuNhapHang hang = daoPhieuNhap.getPhieuNhapHangById(data);
							try {
								FileInputStream stream = new FileInputStream(new File("src/GUI/ChiTietPhieuNhap.fxml"));
								FXMLLoader loader = new FXMLLoader();
								ChiTietPhieuNhapControl control = new ChiTietPhieuNhapControl(hang);
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

		colXem.setCellFactory(cellFactory);
	}

	private void initbtnTim() {
		btnTim.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				data.clear();
				List<PhieuNhapHang> list = daoPhieuNhap.filterPhieuNhap(rbtTatCaThoiGian.isSelected() ,dateNgayLapFrom.getValue(), dateNgayLapTo.getValue(), txtNhanVien.getText(), txtMaPhieu.getText());
				if (list.size() <= 0) {
					common.showNotification(AlertType.INFORMATION, "No Data", "Không tìm thấy kết quả phù hợp!");
				}else {
					list.forEach(i -> {
						data.add(new PhieuNhapTable(data.size(), i.getId(), i.getThoiGianLap().toString(),
								common.formatMoney(i.tinhTongTienNhapHang()), i.getNhanVienBanThuoc().getHoTenDem()+" "+i.getNhanVienBanThuoc().getTen()));
					});
				}
				
			}
		});
		
	}

}
