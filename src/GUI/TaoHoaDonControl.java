package GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.text.TabableView;

import org.controlsfx.control.textfield.TextFields;


import DAO.DAOHoaDon;
import DAO.DAOKhachHang;
import DAO.DAOThuoc;
import common.Common;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVienBanThuoc;
import entity.Thuoc;
import javafx.beans.binding.IntegerExpression;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.converter.NumberStringConverter;

public class TaoHoaDonControl implements Initializable {

	private DAOKhachHang kh_dao = new DAOKhachHang();
	private DAOHoaDon hd_dao = new DAOHoaDon();
	private DAOThuoc thuoc_dao = new DAOThuoc();

	private KhachHang khachHang;
	private HoaDon hoaDon;
	private NhanVienBanThuoc nhanVienBanThuoc;
	private ChiTietHoaDon chiTietHoaDon;

	private ObservableList<common.ChiTietHoaDon> data;



	public TextField txtMaNhanVien;
	public TextField txtTenNhanVien;

	public TextField txtSoDienThoai;
	public TextField txtTenKhachHang;
	public Text lblDiemTichLuy;
	public Text lblXoaKH;
	public Pane btnXoaKH;

	public TextField txtMaHoaDon;
	public DatePicker dateNgayLap;

	public TextField txtMaThuoc;
	public Button btnThanhToan;
	public TextField txtTenThuoc;
	public TextField txtDonViTinh;
	public TextField txtSoLuong;
	public TextField txtDonGia;
	public TextField txtTongTien;

	public TableView<common.ChiTietHoaDon> tblChiTietHoaDon;
	public TableColumn<common.ChiTietHoaDon, Integer> colSTT;
	public TableColumn<common.ChiTietHoaDon, String> colMaThuoc;
	public TableColumn<common.ChiTietHoaDon, String> colTenThuoc;
	public TableColumn<common.ChiTietHoaDon, String> colDonViTinh;
	public TableColumn<common.ChiTietHoaDon, Integer> colSoLuong;
	public TableColumn<common.ChiTietHoaDon, String> colNuocSanXuat;
	public TableColumn<common.ChiTietHoaDon, String> colLoaiThuoc;
	public TableColumn<common.ChiTietHoaDon, String> colDonGia;
	public TableColumn<common.ChiTietHoaDon, String> colTongTien;
	public TableColumn colDelete;

	public TextField txtTongTienAll;
	public TextField txtThue;
	public TextField txtThanhTien;


	public TaoHoaDonControl(NhanVienBanThuoc nhanVienBanThuoc2) {
		// TODO Auto-generated constructor stub
		this.nhanVienBanThuoc = nhanVienBanThuoc2;
	}

	@FXML
	public void showDialogThanhToan() {
		if (hoaDon == null) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Hoá đơn chưa được tạo, vui lòng tạo hóa đơn trước khi thanh toán!");

			alert.showAndWait();
		}else {
			if (hoaDon.getDsChiTietHoaDon().size() <= 0) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Hóa đơn chưa có sản phẩm, vui lòng thêm sản phẩm vào hóa đơn!");

				alert.showAndWait();
			}else {
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("DialogThanhToan.fxml"));
					ThanhToanControl thanhToanControl = new ThanhToanControl();
					thanhToanControl.setHoaDon(hoaDon);
					loader.setController(thanhToanControl);

					Stage thanhToanStage = loader.load();
					thanhToanStage.setTitle("Thanh Toan");
					thanhToanStage.initModality(Modality.APPLICATION_MODAL);
					thanhToanStage.initStyle(StageStyle.UNDECORATED);
					thanhToanStage.show();

					//					Stage thanhToanStage = FXMLLoader.load(getClass().getResource("DialogThanhToan.fxml"));
					//					thanhToanStage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	public void handleWhenTableChange() {
		txtTongTienAll.setText(new Common().formatMoney(hoaDon.tinhTienHoaDonChuaThue()));
		txtThue.setText(new Common().formatMoney(hoaDon.tinhThueHoaDon()));
		txtThanhTien.setText(new Common().formatMoney(hoaDon.tinhTienHoaDonBaoGomThue()));
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		txtMaNhanVien.setText(nhanVienBanThuoc.getId());
		txtTenNhanVien.setText(nhanVienBanThuoc.getHoTenDem()+" "+nhanVienBanThuoc.getTen());
		try {
			TextFields.bindAutoCompletion(txtSoDienThoai, kh_dao.getAllSoDienThoai());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		TextFields.bindAutoCompletion(txtMaThuoc, thuoc_dao.getAllMaThuoc());

		data = FXCollections.observableArrayList();
		//		maThuoc, tenThuoc, donViTinh, nuocSanXuat, loaiThuoc, donGia, tongTien
		colMaThuoc.setCellValueFactory(new PropertyValueFactory<>("maThuoc"));
		colTenThuoc.setCellValueFactory(new PropertyValueFactory<>("tenThuoc"));
		colDonViTinh.setCellValueFactory(new PropertyValueFactory<>("donViTinh"));
		colNuocSanXuat.setCellValueFactory(new PropertyValueFactory<>("nuocSanXuat"));
		colLoaiThuoc.setCellValueFactory(new PropertyValueFactory<>("loaiThuoc"));
		colDonGia.setCellValueFactory(new PropertyValueFactory<>("donGia"));
		colSoLuong.setCellValueFactory(new PropertyValueFactory<>("soLuong"));
		colSTT.setCellValueFactory(new PropertyValueFactory<>("stt"));
		colDonGia.setCellValueFactory(new PropertyValueFactory<>("donGia"));
		colTongTien.setCellValueFactory(new PropertyValueFactory<>("tongTien"));

		tblChiTietHoaDon.setItems(data);

		txtMaThuoc.textProperty().addListener((observable, oldValue, newValue) -> {
			Thuoc thuoc = thuoc_dao.getThuocById(newValue);
			if (thuoc != null) {
				chiTietHoaDon = new ChiTietHoaDon();
				chiTietHoaDon.setThuoc(thuoc);
				chiTietHoaDon.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
				txtTenThuoc.setText(chiTietHoaDon.getThuoc().getTenThuoc());
				txtDonViTinh.setText(chiTietHoaDon.getThuoc().getDonViTinh());
				txtDonGia.setText(new Common().formatMoney(chiTietHoaDon.getGiaBan()));
				txtTongTien.setText(new Common().formatMoney(chiTietHoaDon.tinhTongTienChuaThue()));
			}
		});

		txtSoLuong.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				if (!newValue.matches("^[0-9]*")) {
					txtSoLuong.setText(oldValue);
				}

				if (chiTietHoaDon != null && !newValue.isEmpty()) {
					chiTietHoaDon.setSoLuong(Integer.parseInt(newValue));
					txtTongTien.setText(new Common().formatMoney(chiTietHoaDon.tinhTongTienChuaThue()));
				}
			}

		});

		Callback<TableColumn<common.ChiTietHoaDon, String>, TableCell<common.ChiTietHoaDon, String>> cellFactory
		= //
		new Callback<TableColumn<common.ChiTietHoaDon, String>, TableCell<common.ChiTietHoaDon, String>>()
		{
			@Override
			public TableCell call(final TableColumn<common.ChiTietHoaDon, String> param)
			{
				final TableCell<common.ChiTietHoaDon, String> cell = new TableCell<common.ChiTietHoaDon, String>()
				{

					final Button btn = new Button("delete");

					{
						btn.setOnAction(event -> {
							TableColumn col = tblChiTietHoaDon.getColumns().get(1);
							String data = (String) col.getCellObservableValue(tblChiTietHoaDon.getItems().get(getIndex())).getValue();
							if (hoaDon.xoaChiTietHoaDon(data)) {
								tblChiTietHoaDon.getItems().remove(getIndex());
								if (hoaDon.getDsChiTietHoaDon().size() <= 0) {
									hoaDon = null;
									dateNgayLap.setValue(null);
									txtMaHoaDon.setText("");
								}
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

		data.addListener(new ListChangeListener<common.ChiTietHoaDon>() {

			@Override
			public void onChanged(Change<? extends common.ChiTietHoaDon> c) {
				// TODO Auto-generated method stub
				if (!(data.size() <= 0)) {
					handleWhenTableChange();
				}
			}

		});
	}

	public void setKhachHangAndFieldKhachHang() {
		hoaDon.setKhachHang(khachHang);
		Date date= new Date();
		long time = date.getTime();
		hoaDon.setThoiGianLap(new Timestamp(time));

		txtMaHoaDon.setText(hoaDon.getId());
		txtTenKhachHang.setText(hoaDon.getKhachHang().getHoTenDem()+" "+hoaDon.getKhachHang().getTen());
		lblDiemTichLuy.setText("Điểm tích lũy: "+new Common().formatMoney(hoaDon.getKhachHang().getDienTichLuy()));
		dateNgayLap.setValue(LocalDate.now());
		txtSoDienThoai.setEditable(false);
		txtTenKhachHang.setEditable(false);
		btnXoaKH.setDisable(false);
		btnXoaKH.setStyle("-fx-background-color:  #F5508B; -fx-background-radius: 10px");
		lblXoaKH.setStyle("-fx-fill: #FFFFFF;");
	}

	@FXML
	public void setKhachHangForHoaDonWhenAddKhachHang() {
		if (hoaDon == null) {
			khachHang = kh_dao.getKhachHangBySoDienThoai(txtSoDienThoai.getText());
			if (khachHang == null) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Số điện thoại bạn nhập không tồn tại, vui lòng nhập lại!");

				alert.showAndWait();
			}else {
				hoaDon = new HoaDon();
				hoaDon.setId(hd_dao.generateId());
				hoaDon.setNhanVienBanThuoc(nhanVienBanThuoc);

				setKhachHangAndFieldKhachHang();
			}

		}else {
			khachHang = kh_dao.getKhachHangBySoDienThoai(txtSoDienThoai.getText());
			if (khachHang == null) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Số điện thoại bạn nhập không tồn tại, vui lòng nhập lại!");

				alert.showAndWait();
			}else {
				setKhachHangAndFieldKhachHang();
			}
		}
	}

	@FXML
	public void xoaKhachHang() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText(null);
		alert.setContentText("Bạn có chắc chắn muốn xóa khách hànghàng?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			// ... user chose OK
			if (hoaDon.getDsChiTietHoaDon().size() <= 0) {
				hoaDon = null;

				txtMaHoaDon.setText("");
				txtTenKhachHang.setText("");
				txtSoDienThoai.setText("");
				lblDiemTichLuy.setText("Điểm tích lũy: 0đ");
				txtSoDienThoai.setEditable(true);
				txtTenKhachHang.setEditable(true);
				btnXoaKH.setDisable(true);
				dateNgayLap.setValue(null);
				btnXoaKH.setStyle("-fx-background-color: #DFDFDF; -fx-background-radius: 10px");
				lblXoaKH.setStyle("-fx-fill: #B1B1B1;");
			}else {
				hoaDon.setKhachHang(null);
				txtSoDienThoai.setText("");
				txtTenKhachHang.setText("");
			}
		} else {
			// ... user chose CANCEL or closed the dialog
		}
	}

	@FXML
	public void actionTextFieldMaThuoc() {
		Thuoc thuoc;
		thuoc = thuoc_dao.getThuocById(txtMaThuoc.getText());
		if (thuoc == null) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Mã số thuốc không tồn tại, vui lòng nhập lại!");

			alert.showAndWait();
		}else {
			chiTietHoaDon = new ChiTietHoaDon();
			chiTietHoaDon.setThuoc(thuoc);
			chiTietHoaDon.setSoLuong(Integer.parseInt(txtSoLuong.getText()));

			txtTenThuoc.setText(chiTietHoaDon.getThuoc().getTenThuoc());
			txtDonViTinh.setText(chiTietHoaDon.getThuoc().getDonViTinh());
			txtDonGia.setText(new Common().formatMoney(chiTietHoaDon.getGiaBan()));
			txtTongTien.setText(new Common().formatMoney(chiTietHoaDon.tinhTongTienChuaThue()));
		}

	}

	public boolean kiemtraTonTai(String id) {
		for (common.ChiTietHoaDon chiTietHoaDon : data) {
			if (chiTietHoaDon.getMaThuoc().equals(id)) {
				return true;
			}
		}
		return false;
	}

	public boolean kiemTraSoLuongKhiTrungKhoa(ChiTietHoaDon chiTietHoaDon) {
		ChiTietHoaDon timDuoc = hoaDon.timChiTietHoaDon(chiTietHoaDon.getThuoc().getId());
		int soLuong = chiTietHoaDon.getSoLuong() + timDuoc.getSoLuong();
		if (soLuong <= thuoc_dao.getSoLuongTon(chiTietHoaDon.getThuoc().getId())) {
			return true;
		}
		return false;
	}

	public void suaSoLuongKhiAddChiTietHoaDonTrung(ChiTietHoaDon chiTietHoaDon) {
		for (int i = 0; i < hoaDon.getDsChiTietHoaDon().size(); i++) {
			if (hoaDon.getDsChiTietHoaDon().get(i).getThuoc().getId().equals(chiTietHoaDon.getThuoc().getId())) {
				ChiTietHoaDon _chiChiTietHoaDon = chiTietHoaDon;
				_chiChiTietHoaDon.setSoLuong(chiTietHoaDon.getSoLuong()+hoaDon.getDsChiTietHoaDon().get(i).getSoLuong());
				hoaDon.getDsChiTietHoaDon().set(i, _chiChiTietHoaDon);
				break;
			}
		}

	}

	public void suaSoLuongTrenTableViewKhiKhoaTrung(ChiTietHoaDon chiTietHoaDon) {
		for (int i = 0; i < data.size(); i++) {
			if (tblChiTietHoaDon.getItems().get(i).getMaThuoc().equals(chiTietHoaDon.getThuoc().getId())) {
				common.ChiTietHoaDon cTietHoaDon = data.get(i);
				cTietHoaDon.setSoLuong(chiTietHoaDon.getSoLuong());
				cTietHoaDon.setTongTien(new Common().formatMoney(chiTietHoaDon.tinhTongTienChuaThue()));
				data.set(i, cTietHoaDon);
				break;
			}
		}
	}

	public void clearNhapThuocField() {
		chiTietHoaDon = null;
		txtTenThuoc.setText("");
		txtDonViTinh.setText("");
		txtDonGia.setText("");
		txtTongTien.setText("");
		txtMaThuoc.setText("");
		txtSoLuong.setText("1");
	}


	@FXML
	public void actionWhenAddChiTietHoaDon() {
		if (thuoc_dao.getThuocById(txtMaThuoc.getText()) == null) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Thuốc hoặc mã thuốc bạn nhập không tồn tại, vui lòng kiểm tra lại!");

			alert.showAndWait();
		}else {
			if (!txtSoLuong.getText().isEmpty()) {
				if (thuoc_dao.getSoLuongTon(chiTietHoaDon.getThuoc().getId()) < chiTietHoaDon.getSoLuong()) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText("Không đủ số lượng tồn. Trong kho còn "+ thuoc_dao.getSoLuongTon(chiTietHoaDon.getThuoc().getId())
					+" "+ chiTietHoaDon.getThuoc().getDonViTinh());

					alert.showAndWait();
				}else {
					if (hoaDon == null) {
						if (chiTietHoaDon == null) {
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("Information Dialog");
							alert.setHeaderText(null);
							alert.setContentText("Vui lòng nhập thông tin thuốc!");

							alert.showAndWait();
						}else {
							chiTietHoaDon.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
							hoaDon = new HoaDon();
							hoaDon.setId(hd_dao.generateId());
							hoaDon.setNhanVienBanThuoc(nhanVienBanThuoc);
							hoaDon.getDsChiTietHoaDon().add(chiTietHoaDon);
							data.add(new common.ChiTietHoaDon(chiTietHoaDon.getThuoc().getId(), 
									chiTietHoaDon.getThuoc().getTenThuoc(),
									chiTietHoaDon.getThuoc().getDonViTinh(),
									chiTietHoaDon.getThuoc().getNuocSanXuat(),
									chiTietHoaDon.getThuoc().getLoaiThuoc().getTenLoai(),
									new Common().formatMoney(chiTietHoaDon.getGiaBan()),
									new Common().formatMoney(chiTietHoaDon.tinhTongTienChuaThue()),
									data.size(),
									chiTietHoaDon.getSoLuong()));
							clearNhapThuocField();
							txtMaHoaDon.setText(hoaDon.getId());
							dateNgayLap.setValue(LocalDate.now());
						}
					}else {
						if (chiTietHoaDon == null) {
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("Information Dialog");
							alert.setHeaderText(null);
							alert.setContentText("Vui lòng nhập thông tin thuốc!");

							alert.showAndWait();
						}else {
							if (kiemtraTonTai(chiTietHoaDon.getThuoc().getId())) {
								if (!kiemTraSoLuongKhiTrungKhoa(chiTietHoaDon)) {
									Alert alert = new Alert(AlertType.INFORMATION);
									alert.setTitle("Information Dialog");
									alert.setHeaderText(null);
									alert.setContentText("Không đủ số lượng, trong kho còn "
											+thuoc_dao.getSoLuongTon(chiTietHoaDon.getThuoc().getId())
											+" "
											+chiTietHoaDon.getThuoc().getDonViTinh());

									alert.showAndWait();
								}else {
									suaSoLuongKhiAddChiTietHoaDonTrung(chiTietHoaDon);
									suaSoLuongTrenTableViewKhiKhoaTrung(chiTietHoaDon);
									clearNhapThuocField();
								}
							} else {
								chiTietHoaDon.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
								hoaDon.setNhanVienBanThuoc(nhanVienBanThuoc);
								hoaDon.getDsChiTietHoaDon().add(chiTietHoaDon);
								data.add(new common.ChiTietHoaDon(chiTietHoaDon.getThuoc().getId(), 
										chiTietHoaDon.getThuoc().getTenThuoc(),
										chiTietHoaDon.getThuoc().getDonViTinh(),
										chiTietHoaDon.getThuoc().getNuocSanXuat(),
										chiTietHoaDon.getThuoc().getLoaiThuoc().getTenLoai(),
										new Common().formatMoney(chiTietHoaDon.getGiaBan()),
										new Common().formatMoney(chiTietHoaDon.tinhTongTienChuaThue()),
										data.size(),
										chiTietHoaDon.getSoLuong()));
								clearNhapThuocField();
								txtMaHoaDon.setText(hoaDon.getId());
								dateNgayLap.setValue(LocalDate.now());
							}
						}
					}
				}
			}else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Số lượng không được để trống!");

				alert.showAndWait();
			}
		}

	}

	public void clearAllField() {
		clearNhapThuocField();
		txtTenKhachHang.setText("");
		dateNgayLap.setValue(null);
		txtMaHoaDon.setText("");
		txtSoDienThoai.setText("");
		txtTongTienAll.setText("");
		txtThanhTien.setText("");
		txtThue.setText("");
	}

	public void btnHuy() {
		if (hoaDon != null) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Bạn có chắc muốn hủy hóa đơn này?");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				// ... user chose OK
				hoaDon = null;
				clearAllField();
				data.clear();
			} else {
				// ... user chose CANCEL or closed the dialog
			}
		}else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Bạn chưa có hóa đơn để hủy!");
			
			alert.show();
		}
	}
}
