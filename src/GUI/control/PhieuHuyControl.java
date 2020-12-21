package GUI.control;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import DAO.DAOLoThuoc;
import DAO.DAOLoaiThuoc;
import DAO.DAOPhieuHuy;
import DAO.DAOThuoc;
import common.ChiTietPhieuHuyTable;
import common.Common;
import common.LoThuocTableInPhieuHuy;
import entity.LoThuoc;
import entity.NhanVienBanThuoc;
import entity.PhieuHuyHang;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class PhieuHuyControl implements Initializable {

	public Text lblThanhTien;
	public TextField txtMaThuoc;
	public TextField txtTenThuoc;
	public TextField txtMaPhieuHuy;
	public ComboBox<String> cmbLoaiThuoc;
	public RadioButton rbtThuocHetHan;
	public DatePicker dateNgayLap;
	public Button btnTim;
	public Button btnLuu;
	public Button btnHuy;
	public TableView<LoThuocTableInPhieuHuy> tblLoThuoc;
	public TableColumn<LoThuocTableInPhieuHuy, Integer> colSTTLoThuoc;
	public TableColumn<LoThuocTableInPhieuHuy, Integer> colSoLuongTonLoThuoc;
	public TableColumn<LoThuocTableInPhieuHuy, String> colMaThuocLoThuoc;
	public TableColumn<LoThuocTableInPhieuHuy, String> colTenThuocLoThuoc;
	public TableColumn<LoThuocTableInPhieuHuy, String> colDonViTinhLoThuoc;
	public TableColumn<LoThuocTableInPhieuHuy, String> colDonGiaLoThuoc;
	public TableColumn<LoThuocTableInPhieuHuy, String> colLoaiThuocLoThuoc;
	public TableColumn<LoThuocTableInPhieuHuy, String> colNuocSanXuatLoThuoc;
	public TableColumn<LoThuocTableInPhieuHuy, String> colNgaySanXuatLoThuoc;
	public TableColumn<LoThuocTableInPhieuHuy, String> colHanSuDungLoThuoc;
	public TableColumn colChonLoThuoc;
	public TableView<ChiTietPhieuHuyTable> tblChiTietPhieuHuy;
	public TableColumn<ChiTietPhieuHuyTable, Integer> colSTTChiTiet;
	public TableColumn<ChiTietPhieuHuyTable, Integer> colSoLuongHuyChiTiet;
	public TableColumn<ChiTietPhieuHuyTable, String> colMaThuocChiTiet;
	public TableColumn<ChiTietPhieuHuyTable, String> colTenThuocChiTiet;
	public TableColumn<ChiTietPhieuHuyTable, String> colDonViTinhChiTiet;
	public TableColumn<ChiTietPhieuHuyTable, String> colDonGiaChiTiet;
	public TableColumn<ChiTietPhieuHuyTable, String> colLoaiThuocChiTiet;
	public TableColumn<ChiTietPhieuHuyTable, String> colNgaySanXuatChiTiet;
	public TableColumn<ChiTietPhieuHuyTable, String> colHanSuDungChiTiet;
	public TableColumn<ChiTietPhieuHuyTable, String> colTongTien;
	public TableColumn colXoaChiTiet;
	
	private DAOThuoc daoThuoc = new DAOThuoc();
	private DAOLoaiThuoc daoLoaiThuoc = new DAOLoaiThuoc();
	private DAOLoThuoc daoLoThuoc = new DAOLoThuoc();
	private DAOPhieuHuy daoPhieuHuy = new DAOPhieuHuy();
	private Common common = new Common();
	private ObservableList<LoThuocTableInPhieuHuy> dataLoThuoc;
	private LoThuoc loThuoc;
	private PhieuHuyHang phieuHuyHang;
	private NhanVienBanThuoc nhanVienBanThuoc;
	private ObservableList<ChiTietPhieuHuyTable> dataChiTiet;
	
	public PhieuHuyControl(NhanVienBanThuoc nhanVienBanThuoc) {
		// TODO Auto-generated constructor stub
		this.nhanVienBanThuoc = nhanVienBanThuoc;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		initTimThuocField();
		initLoThuocTable();
		initChiTietPhieuHuy();
	}

	private void initChiTietPhieuHuy() {
		// TODO Auto-generated method stub
		dataChiTiet = FXCollections.observableArrayList();
		tblChiTietPhieuHuy.setItems(dataChiTiet);
		
		colSTTChiTiet.setCellValueFactory(new PropertyValueFactory<>("STT"));
		colSoLuongHuyChiTiet.setCellValueFactory(new PropertyValueFactory<>("soLuongHuy"));
		colMaThuocChiTiet.setCellValueFactory(new PropertyValueFactory<>("maThuoc"));
		colTenThuocChiTiet.setCellValueFactory(new PropertyValueFactory<>("tenThuoc"));
		colDonViTinhChiTiet.setCellValueFactory(new PropertyValueFactory<>("donViTinh"));
		colDonGiaChiTiet.setCellValueFactory(new PropertyValueFactory<>("donGia"));
		colLoaiThuocChiTiet.setCellValueFactory(new PropertyValueFactory<>("loaiThuoc"));
		colNgaySanXuatChiTiet.setCellValueFactory(new PropertyValueFactory<>("ngaySanXuat"));
		colHanSuDungChiTiet.setCellValueFactory(new PropertyValueFactory<>("hanSuDung"));
		colTongTien.setCellValueFactory(new PropertyValueFactory<>("tongTien"));
		
		createButtonXoa();
		
		dataChiTiet.addListener(new ListChangeListener<ChiTietPhieuHuyTable>() {

			@Override
			public void onChanged(Change<? extends ChiTietPhieuHuyTable> arg0) {
				// TODO Auto-generated method stub
				lblThanhTien.setText("Thành Tiền: "+ common.formatMoney(phieuHuyHang.tinhTongTienHuyHang()));
			}
			
		});
		
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
						btn.setStyle("-fx-background-color: #F5508B");
						btn.setOnAction(event -> {
							int index = getIndex();
							phieuHuyHang.xoaLoThuoc(index);
							dataChiTiet.remove(index);
							
							if (phieuHuyHang.getDsLoThuoc().size() <= 0) {
								phieuHuyHang = null;
								
								txtMaPhieuHuy.setText("");
								dateNgayLap.setValue(null);
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

		colXoaChiTiet.setCellFactory(cellFactory);
	}

	private void initLoThuocTable() {
		// TODO Auto-generated method stub
		dataLoThuoc = FXCollections.observableArrayList();
		tblLoThuoc.setItems(dataLoThuoc);
		
		colSTTLoThuoc.setCellValueFactory(new PropertyValueFactory<>("STT"));
		colSoLuongTonLoThuoc.setCellValueFactory(new PropertyValueFactory<>("soLuongTon"));
		colMaThuocLoThuoc.setCellValueFactory(new PropertyValueFactory<>("maThuoc"));
		colTenThuocLoThuoc.setCellValueFactory(new PropertyValueFactory<>("tenThuoc"));
		colDonViTinhLoThuoc.setCellValueFactory(new PropertyValueFactory<>("donViTinh"));
		colDonGiaLoThuoc.setCellValueFactory(new PropertyValueFactory<>("donGia"));
		colLoaiThuocLoThuoc.setCellValueFactory(new PropertyValueFactory<>("loaiThuoc"));
		colLoaiThuocLoThuoc.setCellValueFactory(new PropertyValueFactory<>("loaiThuoc"));
		colNuocSanXuatLoThuoc.setCellValueFactory(new PropertyValueFactory<>("nuocSanXuat"));
		colNgaySanXuatLoThuoc.setCellValueFactory(new PropertyValueFactory<>("ngaySanXuat"));
		colHanSuDungLoThuoc.setCellValueFactory(new PropertyValueFactory<>("hanSuDung"));
		
		createButtonChon();
		
		actionButtonTim();
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

					final Button btn = new Button("Chọn");

					{
						btn.setStyle("-fx-background-color: #64AD4A");
						btn.setOnAction(event -> {
							TableColumn colMaPhieuNhap = tblLoThuoc.getColumns().get(1);
							String maPhieuNhap = (String) colMaPhieuNhap.getCellObservableValue(tblLoThuoc.getItems().get(getIndex())).getValue();
							TableColumn colMaThuoc = tblLoThuoc.getColumns().get(2);
							String maThuoc = (String) colMaThuoc.getCellObservableValue(tblLoThuoc.getItems().get(getIndex())).getValue();
							TableColumn colNgaySanXuat = tblLoThuoc.getColumns().get(7);
							String ngaySanXuat = (String) colNgaySanXuat.getCellObservableValue(tblLoThuoc.getItems().get(getIndex())).getValue();
							actionButtonChon(maThuoc, ngaySanXuat, maPhieuNhap);
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

		colChonLoThuoc.setCellFactory(cellFactory);
	}
	
	private void actionButtonChon(String id, String date, String maPhieuNhap) {
		// TODO Auto-generated method stub
		loThuoc = daoLoThuoc.getLoThuocByID(id, date, maPhieuNhap);
		if (phieuHuyHang == null) {
			phieuHuyHang = new PhieuHuyHang();
			phieuHuyHang.setId(daoPhieuHuy.genenateID());
			phieuHuyHang.setQuanLy(nhanVienBanThuoc);
			phieuHuyHang.setThoiGianLap(Timestamp.valueOf(LocalDateTime.now()));
			
			txtMaPhieuHuy.setText(daoPhieuHuy.genenateID());
			dateNgayLap.setValue(LocalDate.now());
		}
		
		phieuHuyHang.getDsLoThuoc().add(loThuoc);
		dataChiTiet.add(new ChiTietPhieuHuyTable(dataChiTiet.size(), loThuoc.getSoLuongConLai(), loThuoc.getThuoc().getId(),
				loThuoc.getThuoc().getTenThuoc(), loThuoc.getThuoc().getDonViTinh(), common.formatMoney(loThuoc.getThuoc().getGia()),
				loThuoc.getThuoc().getLoaiThuoc().getTenLoai(), loThuoc.getNgaySanXuat().toString(), loThuoc.tinhNgayHetHan().toString(), common.formatMoney(loThuoc.tinhGiaTriLoThuocConLai())));
		loThuoc = null;
	}

	private void initTimThuocField() {
		// TODO Auto-generated method stub
		initMaThuoc();
		initTenThuoc();
		initLoaiThuoc();
		initThuocHetHan();
		initButtonTim();
		initThanhTien();
	}

	private void initThanhTien() {
		// TODO Auto-generated method stub
		lblThanhTien.setText("Thành Tiền: 0đ");
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
		dataLoThuoc.clear();
		
		daoLoThuoc.filterLoThuoc(txtMaThuoc.getText(), txtTenThuoc.getText(), cmbLoaiThuoc.getValue(), rbtThuocHetHan.isSelected()).forEach((maPhieuNhap, i) -> {
			dataLoThuoc.add(new LoThuocTableInPhieuHuy(dataLoThuoc.size(), i.getSoLuongConLai(), i.getThuoc().getId(), i.getThuoc().getTenThuoc(),
					i.getThuoc().getDonViTinh(), common.formatMoney(i.getThuoc().getGia()), i.getThuoc().getLoaiThuoc().getTenLoai(), maPhieuNhap,
					i.getNgaySanXuat().toString(), i.tinhNgayHetHan().toString()));
		});
	}

	private void initThuocHetHan() {
		// TODO Auto-generated method stub
		rbtThuocHetHan.setSelected(true);
	}

	private void initLoaiThuoc() {
		// TODO Auto-generated method stub
		ObservableList<String> listLoaiThuoc = FXCollections.observableArrayList(daoLoaiThuoc.getListLoaiThuoc());
		listLoaiThuoc.add("Tất Cả");
		cmbLoaiThuoc.setItems(listLoaiThuoc);
		cmbLoaiThuoc.setValue("Tất Cả");
	}

	private void initTenThuoc() {
		// TODO Auto-generated method stub
		TextFields.bindAutoCompletion(txtTenThuoc, daoThuoc.getAllTenThuoc());
	}

	private void initMaThuoc() {
		// TODO Auto-generated method stub
		TextFields.bindAutoCompletion(txtMaThuoc, daoThuoc.getAllMaThuoc());
	}

}
