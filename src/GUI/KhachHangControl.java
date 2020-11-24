package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import DAO.DAOKhachHang;
import common.Common;
import entity.KhachHang;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class KhachHangControl implements Initializable {
	private Common common = new Common();

	private BanHangControl banHangControl;
	private DAOKhachHang daoKhachHang = new DAOKhachHang();
	private ObservableList<common.KhachHang> dataKhachHang;

	public ComboBox<String> cmbGioiTinh;
	public ComboBox<String> cmbLoaiKhachHang;
	public TableView<common.KhachHang> tblKhachHang;
	public TableColumn<common.KhachHang, Integer> colSTT;
	public TableColumn<common.KhachHang, String> colMaKhachHang;
	public TableColumn<common.KhachHang, String> colHoKhachHang;
	public TableColumn<common.KhachHang, String> colTenKhachHang;
	public TableColumn<common.KhachHang, String> colNgaySinh;
	public TableColumn<common.KhachHang, String> colGioiTinh;
	public TableColumn<common.KhachHang, String> colDienThoai;
	public TableColumn<common.KhachHang, String> colDiem;
	public TableColumn<common.KhachHang, String> colLoai;
	public TableColumn<common.KhachHang, String> colDiaChi;
	public TableColumn colEdit;
	public TableColumn colDelete;
	public TextField txtMaKhachHang;
	public TextField txtSoDienThoai;
	public TextField txtHoKhachHang;
	public TextField txtTenKhachHang;
	public TextField txtDiaChi;
	public DatePicker dateNgaySinh;


	public BanHangControl getBanHangControl() {
		return banHangControl;
	}

	public void setBanHangControl(BanHangControl banHangControl) {
		this.banHangControl = banHangControl;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		initValueGioiTinhCombobox();
		initValueLoaiKhachHangCombobox();

		initTableView();

		createButtonDeleteColumn();
		createButtonEditColumn();

	}

	@FXML void actionButtonThemKhachHang() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("DialogThemAndSua.fxml"));
		ThemKhachHangControl themKhachHangControl = new ThemKhachHangControl();
		loader.setController(themKhachHangControl);
		try {
			Stage themKhachHangStage = loader.load();
			themKhachHangStage.initModality(Modality.APPLICATION_MODAL);
			themKhachHangStage.initStyle(StageStyle.UNDECORATED);
			themKhachHangStage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void actionButtonFilter() {
		dataKhachHang.clear();
		String _ngaySinh;
		if (dateNgaySinh.getValue() == null) {
			_ngaySinh = "";
		}else {
			_ngaySinh = dateNgaySinh.getValue().toString();
		}
		dateNgaySinh.setValue(null);
		List<KhachHang> listKhachHang = daoKhachHang.filterKhachhang(txtMaKhachHang.getText(), txtHoKhachHang.getText(),
				txtTenKhachHang.getText(), _ngaySinh, cmbGioiTinh.getValue(),
				txtSoDienThoai.getText(), cmbLoaiKhachHang.getValue(), txtDiaChi.getText());
		if (listKhachHang.size() <= 0) {
			common.showNotification(AlertType.INFORMATION, "Không kết quảquả", "Không tìm thấy kết quả phù hợp!");
		}else {
			listKhachHang.forEach(i -> {
				String ngaySinh;
				try {
					ngaySinh = i.getNgaySinh().toString();
				} catch (Exception e) {
					// TODO: handle exception
					ngaySinh = "";
				}
				dataKhachHang.add(new common.KhachHang(i.getId(), i.getHoTenDem(), i.getTen(),
						ngaySinh, i.isGioiTinh() ? "Nam" : "Nữ", i.getSoDienThoai(),
								i.getLoaiKhachHang().getLoaiKhachHang(), i.getDiaChi(), common.formatMoney(i.getDienTichLuy()), dataKhachHang.size()));
			});
		}
	}

	@FXML
	public void actionButtonChonKhachHang() {
		if (tblKhachHang.getSelectionModel().getSelectedItem() == null) {
			common.showNotification(AlertType.ERROR, "Lỗi chưa chọn khách hàng!", "Bạn phải chọn 1 khách hàng để thêm vào hóa đơn!");
		}else {
			banHangControl.getTbsBanHang().getSelectionModel().select(banHangControl.getTabTaoHoaDon());
			common.KhachHang khachHangTable = tblKhachHang.getSelectionModel().getSelectedItem();
			banHangControl.getTaoHoaDonControl().addKhachHangFromTabKhachHang(khachHangTable.getDienThoai());
			tblKhachHang.getSelectionModel().clearSelection();
		}

	}

	private void createButtonEditColumn() {
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
							TableColumn col = tblKhachHang.getColumns().get(1);
							String data = (String) col.getCellObservableValue(tblKhachHang.getItems().get(getIndex())).getValue();

							FXMLLoader loader = new FXMLLoader(getClass().getResource("DialogThemAndSua.fxml"));
							SuaKhachHangControl suaKhachHangControl = new SuaKhachHangControl(data);
							loader.setController(suaKhachHangControl);

							try {
								Stage editStage = loader.load();
								editStage.show();

								editStage.setOnHiding( events -> {
									if (suaKhachHangControl.isSuaKhachHangThanhCong()) {
										actionButtonFilter();
									}
								});
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

		colEdit.setCellFactory(cellFactory);
	}

	private void createButtonDeleteColumn() {
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
							TableColumn col = tblKhachHang.getColumns().get(1);
							String data = (String) col.getCellObservableValue(tblKhachHang.getItems().get(getIndex())).getValue();

							Alert alert = new Alert(AlertType.CONFIRMATION);
							alert.setTitle("Confirmation Dialog");
							alert.setHeaderText(null);
							alert.setContentText("Bạn có chắc chắn muốn xóa khách hàng?");

							Optional<ButtonType> result = alert.showAndWait();
							if (result.get() == ButtonType.OK){
								// ... user chose OK
								if (!daoKhachHang.xoaKhachHangById(data)) {
									Alert alert1 = new Alert(AlertType.CONFIRMATION);
									alert1.setTitle("Confirmation Dialog");
									alert1.setHeaderText(null);
									alert1.setContentText("Có một số hóa đơn chứa thông tin của khách hàng này, bạn có chắc muốn xóa?");

									Optional<ButtonType> result1 = alert1.showAndWait();
									if (result1.get() == ButtonType.OK){
										// ... user chose OK
										if (!daoKhachHang.updateHoaDonWhenXoaKhachHang(data)) {
											common.showNotification(AlertType.ERROR, "Lỗi Xóa", "Xóa không thành công, vui lòng kiểm tra lại");
										}else {
											if (daoKhachHang.xoaKhachHangById(data)) {
												common.showNotification(AlertType.INFORMATION, "Thành công", "Xóa khách hàng thành công");
												tblKhachHang.getItems().remove(getIndex());
											}else {
												common.showNotification(AlertType.ERROR, "Lỗi Xóa", "Xóa không thành công, vui lòng kiểm tra lại");
											}
										}

									} else {
										// ... user chose CANCEL or closed the dialog
									}
								}else {
									common.showNotification(AlertType.INFORMATION, "Đã xóa", "Xóa khách hàng thành công.");

									tblKhachHang.getItems().remove(getIndex());
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

		colDelete.setCellFactory(cellFactory);
	}

	private void initTableView() {
		// TODO Auto-generated method stub
		dataKhachHang = FXCollections.observableArrayList();
		colSTT.setCellValueFactory(new PropertyValueFactory<>("stt"));
		colMaKhachHang.setCellValueFactory(new PropertyValueFactory<>("maKhachHang"));
		colHoKhachHang.setCellValueFactory(new PropertyValueFactory<>("hoKhachHang"));
		colTenKhachHang.setCellValueFactory(new PropertyValueFactory<>("tenKhachHang"));
		colNgaySinh.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
		colGioiTinh.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
		colDienThoai.setCellValueFactory(new PropertyValueFactory<>("dienThoai"));
		colLoai.setCellValueFactory(new PropertyValueFactory<>("loaiKhachHang"));
		colDiaChi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
		colDiem.setCellValueFactory(new PropertyValueFactory<>("diem"));
		tblKhachHang.setItems(dataKhachHang);
	}

	private void initValueLoaiKhachHangCombobox() {
		// TODO Auto-generated method stub
		ObservableList<String> listLoaiKhachHang = FXCollections.observableArrayList(daoKhachHang.getListLoaiKhachHang());
		cmbLoaiKhachHang.setValue(listLoaiKhachHang.get(0));
		cmbLoaiKhachHang.setItems(listLoaiKhachHang);
	}

	private void initValueGioiTinhCombobox() {
		// TODO Auto-generated method stub
		ObservableList<String> listGioiTinh = FXCollections.observableArrayList();
		listGioiTinh.add("Nam");
		listGioiTinh.add("Nữ");
		listGioiTinh.add("Cả hai");

		cmbGioiTinh.setItems(listGioiTinh);
		cmbGioiTinh.setValue(listGioiTinh.get(2));
	}

}
