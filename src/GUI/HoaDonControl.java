package GUI;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ListResourceBundle;
import java.util.ResourceBundle;

import DAO.DAOHoaDon;
import common.Common;
import common.HoaDonTable;
import entity.HoaDon;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

public class HoaDonControl implements Initializable {
	DAOHoaDon daoHoaDon = new DAOHoaDon();
	Common common = new Common();
	
	private int soHoaDon;
	private double tongDoanhThu;
	
	public TableView<HoaDonTable> tbsHoaDon;
	public DatePicker dateFrom;
	public DatePicker dateTo;
	public Button btnTim;
	public TextField txtMaHoaDon;
	public TextField txtNhanVien;
	public TextField txtKhachHang;
	public RadioButton rbtTatCaThoiGian;
	public Text lblTongSoHoaDon;
	public Text lblTongDoanhThu;
	public TableColumn<HoaDonTable, Integer> colSTT;
	public TableColumn<HoaDonTable, String> colMaHoaDon;
	public TableColumn<HoaDonTable, String> colNgayLap;
	public TableColumn<HoaDonTable, String> colNhanVien;
	public TableColumn<HoaDonTable, String> colKhachHang;
	public TableColumn<HoaDonTable, String> colThanhTien;
	public TableColumn<HoaDonTable, String> colDiemSuDung;
	public TableColumn<HoaDonTable, String> colPhaiTra;
	public TableColumn<HoaDonTable, String> colTichLuy;
	public TableColumn colXem;
	
	private ObservableList<HoaDonTable> dataTable;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		initNgayLap();
		initTable();
		initSomeField();
	}

	private void initSomeField() {
		// TODO Auto-generated method stub
		rbtTatCaThoiGian.setSelected(true);
		dateFrom.setDisable(true);
		dateTo.setDisable(true);
		rbtTatCaThoiGian.selectedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				// TODO Auto-generated method stub
				if (!rbtTatCaThoiGian.isSelected()) {
					dateFrom.setDisable(false);
					dateTo.setDisable(false);
				}else {
					dateFrom.setDisable(true);
					dateTo.setDisable(true);
				}
			}
		});
	}

	private void initTable() {
		// TODO Auto-generated method stub
		dataTable = FXCollections.observableArrayList();
		colSTT.setCellValueFactory(new PropertyValueFactory<>("STT"));
		colMaHoaDon.setCellValueFactory(new PropertyValueFactory<>("maHoaDon"));
		colNgayLap.setCellValueFactory(new PropertyValueFactory<>("ngayLap"));
		colNhanVien.setCellValueFactory(new PropertyValueFactory<>("tenNhanVien"));
		colKhachHang.setCellValueFactory(new PropertyValueFactory<>("tenKhachHang"));
		colThanhTien.setCellValueFactory(new PropertyValueFactory<>("thanhTien"));
		colDiemSuDung.setCellValueFactory(new PropertyValueFactory<>("diemSuDung"));
		colPhaiTra.setCellValueFactory(new PropertyValueFactory<>("phaiTra"));
		colTichLuy.setCellValueFactory(new PropertyValueFactory<>("tichLuy"));
		createButtonXem();
		
		tbsHoaDon.setItems(dataTable);
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
						btn.setStyle("-fx-background-color: orange");
						btn.setOnAction(event -> {
							TableColumn col = tbsHoaDon.getColumns().get(1);
							String data = (String) col.getCellObservableValue(tbsHoaDon.getItems().get(getIndex())).getValue();
							System.out.println(data);
							showDialogHoaDon(data);
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

	private void initNgayLap() {
		// TODO Auto-generated method stub
		dateFrom.setValue(LocalDate.now());
		dateTo.setValue(LocalDate.now());
	}

	@FXML
	public void actionButtonTim() {
		if (dateFrom.getValue().compareTo(dateTo.getValue()) <= 0) {
			dataTable.clear();
			this.soHoaDon = 0;
			this.tongDoanhThu = 0;
			List<HoaDon> list = daoHoaDon.filterHoaDon(rbtTatCaThoiGian.isSelected(),dateFrom.getValue(), dateTo.getValue(),
					txtMaHoaDon.getText(), txtNhanVien.getText(), txtKhachHang.getText());
			if (list.size() <= 0) {
				common.showNotification(AlertType.INFORMATION, "Không tìm thấy!", "Không tìm thấy thuốc hóa đơn tương thích!");
				lblTongSoHoaDon.setText("Tổng Số Hóa Đơn: 0");
				lblTongDoanhThu.setText("Tổng Doanh Thu: ođ");
			}else {
				list.forEach(i -> {
					String _tenKhachHang = "";
					try {
						_tenKhachHang = i.getKhachHang().getHoTenDem()+" "+i.getKhachHang().getTen();
					}catch (Exception e) {
						// TODO: handle exception
						;
					}

					dataTable.add(new HoaDonTable(dataTable.size(), i.getId(),
							i.getThoiGianLap().toString(), i.getNhanVienBanThuoc().getHoTenDem()+" "+i.getNhanVienBanThuoc().getTen(),
							_tenKhachHang,
							common.formatMoney(i.tinhTienHoaDonBaoGomThue()),
							common.formatMoney(i.getDiemSuDung()),
							common.formatMoney(i.getTienPhaiTra()),
							common.formatMoney(i.tinhDiemTichLuy())));
					
					soHoaDon += 1;
					tongDoanhThu += i.tinhTienPhaiTra();
				});
				lblTongSoHoaDon.setText("Tổng Số Hóa Đơn: "+soHoaDon);
				lblTongDoanhThu.setText("Tổng Doanh Thu: "+common.formatMoney(tongDoanhThu));
			}
		}else {
			common.showNotification(AlertType.INFORMATION, "INFORMATION", "Ngày bắt đầu Chỉ được phép bé hơn hoặc bằng ngày kết thúc!");
		}
	}
	
	private void showDialogHoaDon(String hoaDonID) {
		// TODO Auto-generated method stub'
		FXMLLoader loader = new FXMLLoader(getClass().getResource("DialogHoaDon.fxml"));
		DialogHoaDonControl dialogHoaDonControl = new DialogHoaDonControl();
		dialogHoaDonControl.setMaHoaDon(hoaDonID);
		loader.setController(dialogHoaDonControl);
		Stage hoaDonStage;
		try {
			hoaDonStage = loader.load();
			hoaDonStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
