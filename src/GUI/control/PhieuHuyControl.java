package GUI.control;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import DAO.DAOLoThuoc;
import DAO.DAOLoaiThuoc;
import DAO.DAOThuoc;
import common.ChiTietPhieuHuyTable;
import common.Common;
import common.LoThuocTableInPhieuHuy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class PhieuHuyControl implements Initializable {

	public Text lblThanhTie;
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
	private Common common = new Common();
	private ObservableList<LoThuocTableInPhieuHuy> dataLoThuoc;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		initTimThuocField();
		initLoThuocTable();
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
		
		actionButtonTim();
	}

	private void initTimThuocField() {
		// TODO Auto-generated method stub
		initMaThuoc();
		initTenThuoc();
		initLoaiThuoc();
		initThuocHetHan();
		initButtonTim();
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
		daoLoThuoc.filterLoThuoc(txtMaThuoc.getText(), txtTenThuoc.getText(), cmbLoaiThuoc.getValue(), rbtThuocHetHan.isSelected()).forEach(i -> {
			dataLoThuoc.add(new LoThuocTableInPhieuHuy(dataLoThuoc.size(), i.getSoLuongConLai(), i.getThuoc().getId(), i.getThuoc().getTenThuoc(),
					i.getThuoc().getDonViTinh(), common.formatMoney(i.getThuoc().getGia()), i.getThuoc().getLoaiThuoc().getTenLoai(), i.getThuoc().getNuocSanXuat(),
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
