package GUI.control;

import java.net.URL;
import java.util.ResourceBundle;

import common.ChiTietPhieuNhapTable;
import common.Common;
import entity.PhieuHuyHang;
import entity.PhieuNhapHang;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class ChiTietPhieuHuyControl implements Initializable {
	public Text lblMaPhieuNhap;
	public Text lblTenNguoiNhap;
	public Text lblNgayLap;
	public Text lblGioLap;
	public Text lblTongTien;
	public TableView<ChiTietPhieuNhapTable> tblLoThuoc;
	public TableColumn<ChiTietPhieuNhapTable, String> colMaThuoc;
	public TableColumn<ChiTietPhieuNhapTable, String> colTenThuoc;
	public TableColumn<ChiTietPhieuNhapTable, String> colNgaySanXuat;
	public TableColumn<ChiTietPhieuNhapTable, String> colHanSuDung;
	public TableColumn<ChiTietPhieuNhapTable, Integer> colSoLuong;
	public TableColumn<ChiTietPhieuNhapTable, String> colTongTien;
	
	private PhieuHuyHang phieuHuyHang;
	private Common common = new Common();
	private ObservableList<ChiTietPhieuNhapTable> dataTable;
	
	public ChiTietPhieuHuyControl(PhieuHuyHang phieuHuyHang) {
		// TODO Auto-generated constructor stub
		this.phieuHuyHang = phieuHuyHang;
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		initField();
		initTable();
	}


	private void initTable() {
		// TODO Auto-generated method stub
		dataTable = FXCollections.observableArrayList();
		colHanSuDung.setCellValueFactory(new PropertyValueFactory<>("hanSuDung"));
		colMaThuoc.setCellValueFactory(new PropertyValueFactory<>("maThuoc"));
		colNgaySanXuat.setCellValueFactory(new PropertyValueFactory<>("ngaySanXuat"));
		colSoLuong.setCellValueFactory(new PropertyValueFactory<>("soLuong"));
		colTenThuoc.setCellValueFactory(new PropertyValueFactory<>("tenThuoc"));
		colTongTien.setCellValueFactory(new PropertyValueFactory<>("tongTien"));
		
		tblLoThuoc.setItems(dataTable);
		
		insertData();
	}


	private void insertData() {
		// TODO Auto-generated method stub
		phieuHuyHang.getDsLoThuoc().forEach(i -> {
			dataTable.add(new ChiTietPhieuNhapTable(i.getThuoc().getId(), i.getThuoc().getTenThuoc(), i.getNgaySanXuat().toString(), i.tinhNgayHetHan().toString(), common.formatMoney(i.tinhTongTienLoThuoc()), i.getSoLuong()));
		});
	}


	private void initField() {
		// TODO Auto-generated method stub
		lblMaPhieuNhap.setText("Mã Phiếu Hủy: "+phieuHuyHang.getId());
		lblTenNguoiNhap.setText("Người Lập: "+phieuHuyHang.getQuanLy().getHoTenDem()+" "+phieuHuyHang.getQuanLy().getTen());
		lblNgayLap.setText("Ngày lập: "+phieuHuyHang.getThoiGianLap().getDate()+"/"+phieuHuyHang.getThoiGianLap().getMonth()+"/"+Integer.toString(1900+phieuHuyHang.getThoiGianLap().getYear()));
		lblGioLap.setText("Giờ lập: "+phieuHuyHang.getThoiGianLap().getHours()+":"+phieuHuyHang.getThoiGianLap().getMinutes());
		lblTongTien.setText(common.formatMoney(this.phieuHuyHang.tinhTongTienHuyHang()));
	}

}
