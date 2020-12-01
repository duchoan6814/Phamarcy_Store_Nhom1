package GUI;

import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import DAO.DAOPhieuNhap;
import DAO.DAOThuoc;
import common.ChiTietHoaDon;
import common.Common;
import common.LoThuocTable;
import entity.LoThuoc;
import entity.NhanVienBanThuoc;
import entity.PhieuNhapHang;
import entity.QuanLy;
import entity.Thuoc;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener.Change;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class NhapThuocControl implements Initializable {

	private DAOThuoc daoThuoc = new DAOThuoc();
	private DAOPhieuNhap daoPhieuNhap = new DAOPhieuNhap();
	private Common common = new Common();

	private NhanVienBanThuoc nhanVienBanThuoc;
	private LoThuoc loThuoc;
	private PhieuNhapHang phieuNhapHang;

	public TextField txtMaPhieuNhap;
	public DatePicker dateNgayLap;
	public Button btnThem;
	public TextField txtMaThuoc;
	public TextField txtTenThuoc;
	public TextField txtSoLuong;
	public DatePicker dateNgaySanXuat;
	public TextField txtDonGia;
	public TextField txtTongTien;
	public Text lblThanhTien;
	public Button btnLuu;
	public Button btnHuy;
	public TableView<LoThuocTable> tblLoThuoc;
	public TableColumn<LoThuocTable, Integer> colSTT;
	public TableColumn<LoThuocTable, String> colMaThuoc;
	public TableColumn<LoThuocTable, String> colTenThuoc;
	public TableColumn<LoThuocTable, Integer> colSoLuong;
	public TableColumn<LoThuocTable, String> colNSX;
	public TableColumn<LoThuocTable, String> colHSD;
	public TableColumn<LoThuocTable, String> colDonGia;
	public TableColumn<LoThuocTable, String> colTongTien;
	public TableColumn colSua;
	public TableColumn colXoa;

	private ObservableList<LoThuocTable> dataTable;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		initTable();
		initTxtMaThuoc();
		initActionInTxtMaThuocField();
		initTxtSoLuong();
		initDateNgaySanXuat();
		initActionButtonThem();
	}

	//================================================================================

	private void initActionButtonThem() {
		// TODO Auto-generated method stub
		btnThem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				handleActionButtonThem();
			}
		});
	}

	private void handleActionButtonThem() {
		// TODO Auto-generated method stub
		if (loThuoc != null) {
			if (loThuoc.getNgaySanXuat() == null) {
				common.showNotification(AlertType.INFORMATION, "Lổi thêm", "Lô thuốc chưa có ngày sản xuất!");
			}else {
				if (phieuNhapHang == null) {
					phieuNhapHang = new PhieuNhapHang();
					phieuNhapHang.setId(daoPhieuNhap.generateIDPhieuNhap());
					phieuNhapHang.setNhanVienBanThuoc(nhanVienBanThuoc);
					phieuNhapHang.setThoiGianLap(Timestamp.valueOf(LocalDateTime.now()));

					dateNgayLap.setValue(LocalDate.now());
					txtMaPhieuNhap.setText(phieuNhapHang.getId());

					addLoThuocIntoTable();
				}else {
					handleWhenThuocTrungKhoa();
				}
			}
		}else {
			common.showNotification(AlertType.INFORMATION, "Lỗi Thêm", "Vui lòng nhập đủ các field!");
		}
	}

	private void handleWhenThuocTrungKhoa() {
		LoThuoc loThuocInDS = null;
		// TODO Auto-generated method stub
		if (kiemTraTrungKhoa(loThuoc) && kiemTraTrungNgaySanXuat(loThuoc)) {
			for (int i = 0; i < phieuNhapHang.getDsLoThuoc().size(); i++) {
				if (loThuoc.getThuoc().getId().equals(phieuNhapHang.getDsLoThuoc().get(i).getThuoc().getId())) {
					loThuocInDS = phieuNhapHang.getDsLoThuoc().get(i); 
					loThuocInDS.setSoLuong(loThuocInDS.getSoLuong()+loThuoc.getSoLuong());
					phieuNhapHang.getDsLoThuoc().set(i, loThuocInDS);
					break;
				}
			}

			for (int i = 0; i < dataTable.size(); i++) {
				if (loThuoc.getThuoc().getId().equals(dataTable.get(i).getMaThuoc())) {

					LoThuocTable loThuocTable = dataTable.get(i);
					loThuocTable.setSoLuong(loThuocTable.getSoLuong()+loThuoc.getSoLuong());
					loThuocTable.setTongTien(common.formatMoney(loThuocInDS.tinhTongTienLoThuoc()));
					dataTable.set(i, loThuocTable);
					break;
				}
			}

			txtTongTien.setText("Thành Tiền: "+ common.formatMoney(phieuNhapHang.tinhTongTienNhapHang()));
			removeLoThuocAndClearTxtFied();
			txtMaThuoc.setText("");
			dateNgaySanXuat.setValue(null);
			loThuoc = null;
		}else {
			addLoThuocIntoTable();
		}
	}

	private boolean kiemTraTrungNgaySanXuat(LoThuoc loThuoc2) {
		// TODO Auto-generated method stub
		for (LoThuoc i : phieuNhapHang.getDsLoThuoc()) {
			if (i.getNgaySanXuat().equals(loThuoc.getNgaySanXuat())) {
				return true;
			}
		}
		return false;
	}

	private boolean kiemTraTrungKhoa(LoThuoc loThuoc) {
		// TODO Auto-generated method stub
		for (LoThuoc i : phieuNhapHang.getDsLoThuoc()) {
			if (i.getThuoc().getId().equals(loThuoc.getThuoc().getId())) {
				return true;
			}
		}
		return false;
	}

	private void addLoThuocIntoTable() {
		// TODO Auto-generated method stub

		phieuNhapHang.getDsLoThuoc().add(loThuoc);
		dataTable.add(new LoThuocTable(dataTable.size(), loThuoc.getSoLuong(), loThuoc.getThuoc().getId(),
				loThuoc.getThuoc().getTenThuoc(),
				loThuoc.getNgaySanXuat().toString(),
				loThuoc.tinhNgayHetHan().toString(),
				common.formatMoney(loThuoc.getThuoc().getGia()),
				common.formatMoney(loThuoc.tinhTongTienLoThuoc())));
		removeLoThuocAndClearTxtFied();
		txtMaThuoc.setText("");
		dateNgaySanXuat.setValue(null);
		loThuoc = null;
	}
	//========================================================================================
	private void initDateNgaySanXuat() {
		// TODO Auto-generated method stub
		dateNgaySanXuat.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (loThuoc != null) {
				if (LocalDate.now().compareTo(newValue) < 0) {
					common.showNotification(AlertType.INFORMATION, "lỗi ngày", "Ngày sản xuất không được lớn hơn ngày hiện tại!");
					dateNgaySanXuat.setValue(oldValue);
				}else {
					loThuoc.setNgaySanXuat(Date.valueOf(newValue));
				}
			}
		});
	}

	private void initTxtSoLuong() {
		// TODO Auto-generated method stub
		txtSoLuong.setText("1");
		txtSoLuong.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				if (!newValue.matches("^[0-9]*")) {
					txtSoLuong.setText(oldValue);
				}
				if (loThuoc != null) {
					try {
						int _soLuong = Integer.parseInt(txtSoLuong.getText());
						if (_soLuong <= 0) {
							common.showNotification(AlertType.ERROR, "Số lượng", "Số lượng không được bé hơn hoặc bằng 0!");
						}else {
							loThuoc.setSoLuong(_soLuong);
							txtTongTien.setText(common.formatMoney(loThuoc.tinhTongTienLoThuoc()));
						}
					}catch (Exception e) {
						// TODO: handle exception
						loThuoc.setSoLuong(0);
						txtTongTien.setText(common.formatMoney(loThuoc.tinhTongTienLoThuoc()));
					}
				}
			}
		});
	}

	private void initActionInTxtMaThuocField() {
		// TODO Auto-generated method stub
		txtMaThuoc.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				txtMaThuocHandleOnAction();
			}
		});
	}

	private void initTxtMaThuoc() {
		// TODO Auto-generated method stub
		TextFields.bindAutoCompletion(txtMaThuoc, daoThuoc.getAllMaThuoc());
		txtMaThuocHandleOnchange();
	}

	private void txtMaThuocHandleOnAction() {
		// TODO Auto-generated method stub
		Thuoc thuoc = daoThuoc.getThuocById(txtMaThuoc.getText());
		if (thuoc == null) {
			common.showNotification(AlertType.INFORMATION, "Không tìm thấy", "Không tìm thấy thuốc có mã "+txtMaThuoc.getText());
			removeLoThuocAndClearTxtFied();
		}else {
			taoLoThuocVaInRaCacField(thuoc);
		}
	}

	private void removeLoThuocAndClearTxtFied() {
		// TODO Auto-generated method stub
		loThuoc = null;
		txtTenThuoc.setText("");
		txtDonGia.setText("");
		txtTongTien.setText("");
		txtSoLuong.setText("");
	}

	private void txtMaThuocHandleOnchange() {
		// TODO Auto-generated method stub
		txtMaThuoc.textProperty().addListener((observable, oldValue, newValue) -> {
			Thuoc thuoc = daoThuoc.getThuocById(txtMaThuoc.getText());
			if (thuoc != null) {
				taoLoThuocVaInRaCacField(thuoc);
			}else {
				loThuoc = null;
				removeLoThuocAndClearTxtFied();
			}

		});
	}

	private void taoLoThuocVaInRaCacField(Thuoc thuoc) {
		// TODO Auto-generated method stub
		loThuoc = new LoThuoc();
		loThuoc.setThuoc(thuoc);
		loThuoc.setSoLuong(1);
		try {
			loThuoc.setNgaySanXuat(Date.valueOf(dateNgaySanXuat.getValue()));
		} catch (Exception e) {
			// TODO: handle exception
		}
		txtDonGia.setText(common.formatMoney(loThuoc.getThuoc().getGia()));
		txtTenThuoc.setText(loThuoc.getThuoc().getTenThuoc());
		txtSoLuong.setText("1");
		txtTongTien.setText(common.formatMoney(loThuoc.tinhTongTienLoThuoc()));
	}

	private void initTable() {
		// TODO Auto-generated method stub
		dataTable = FXCollections.observableArrayList();
		colDonGia.setCellValueFactory(new PropertyValueFactory<>("donGia"));
		colHSD.setCellValueFactory(new PropertyValueFactory<>("hanSuDung"));
		colMaThuoc.setCellValueFactory(new PropertyValueFactory<>("maThuoc"));
		colNSX.setCellValueFactory(new PropertyValueFactory<>("ngaySanXuat"));
		colSoLuong.setCellValueFactory(new PropertyValueFactory<>("soLuong"));
		colSTT.setCellValueFactory(new PropertyValueFactory<>("stt"));
		colTenThuoc.setCellValueFactory(new PropertyValueFactory<>("tenThuoc"));
		colTongTien.setCellValueFactory(new PropertyValueFactory<>("tongTien"));
		initButtonSua();
		initButtonXoa();

		tblLoThuoc.setItems(dataTable);

		handleTableOnChange();
	}


	private void handleTableOnChange() {
		// TODO Auto-generated method stub
		dataTable.addListener(new ListChangeListener<LoThuocTable>() {

			@Override
			public void onChanged(Change<? extends LoThuocTable> c) {
				// TODO Auto-generated method stub
				lblThanhTien.setText("Thành Tiền: "+common.formatMoney(phieuNhapHang.tinhTongTienNhapHang()));
			}

		});
	}

	private void initButtonXoa() {
		// TODO Auto-generated method stub

	}

	private void initButtonSua() {
		// TODO Auto-generated method stub

	}

	public NhanVienBanThuoc getNhanVienBanThuoc() {
		return nhanVienBanThuoc;
	}


	public void setNhanVienBanThuoc(NhanVienBanThuoc nhanVienBanThuoc) {
		this.nhanVienBanThuoc = nhanVienBanThuoc;
	}


}
