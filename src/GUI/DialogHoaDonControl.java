package GUI;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import DAO.DAOHoaDon;
import common.ChiTietHoaDonTable;
import common.Common;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class DialogHoaDonControl implements Initializable {
	Common common = new Common();
	DAOHoaDon daoHoaDon = new DAOHoaDon();
	
	private HoaDon hoaDon;
	private String maHoaDon;
	
	public Text lblMaHoaDon;
	public Text lblNhanVien;
	public Text lblKhachHang;
	public Text lblNgayLap;
	public Text lblGioLap;
	public Text lblTongTien;
	public Text lblThue;
	public Text lblThanhTien;
	public TableView<ChiTietHoaDonTable> tblChiTietHoaDon;
	public TableColumn<ChiTietHoaDonTable, Integer> colSoLuong;
	public TableColumn<ChiTietHoaDonTable, String> colTenThuoc;
	public TableColumn<ChiTietHoaDonTable, String> colDonViTinh;
	public TableColumn<ChiTietHoaDonTable, String> colDonGia;
	public TableColumn<ChiTietHoaDonTable, String> colTongTien;
	private ObservableList<ChiTietHoaDonTable> dataChiTietHoaDon;
	

	public HoaDon getHoaDon() {
		return hoaDon;
	}



	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	public void setMaHoaDon(String maHoadon) {
		this.maHoaDon = maHoadon;
	}

	@Override
	public synchronized void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		fetchDataHoaDon(this.maHoaDon);
		initTable();
		initInfomationField(hoaDon);
		
	}



	private void initTable() {
		// TODO Auto-generated method stub
		dataChiTietHoaDon = FXCollections.observableArrayList();
		colSoLuong.setCellValueFactory(new PropertyValueFactory<>("soLuong"));
		colTenThuoc.setCellValueFactory(new PropertyValueFactory<>("tenThuoc"));
		colDonViTinh.setCellValueFactory(new PropertyValueFactory<>("donViTinh"));
		colDonGia.setCellValueFactory(new PropertyValueFactory<>("donGia"));
		colTongTien.setCellValueFactory(new PropertyValueFactory<>("tongTien"));
		tblChiTietHoaDon.setItems(dataChiTietHoaDon);
	}



	private void fetchDataHoaDon(String maHoaDon2) {
		// TODO Auto-generated method stub
		hoaDon = daoHoaDon.getHoaDonById(maHoaDon2);
	}



	private void initInfomationField(HoaDon hoaDon) {
		// TODO Auto-generated method stub
		lblMaHoaDon.setText("Mã HD: "+hoaDon.getId());
		lblNhanVien.setText("NV: "+hoaDon.getNhanVienBanThuoc().getHoTenDem()+" "+hoaDon.getNhanVienBanThuoc().getTen());
		if (hoaDon.getKhachHang() == null) {
			lblKhachHang.setText("KH: NAN");
		}else {
			lblKhachHang.setText("KH: "+hoaDon.getKhachHang().getHoTenDem()+" "+hoaDon.getKhachHang().getTen());
		}
		SimpleDateFormat dateOnly = new SimpleDateFormat("MM/dd/yyyy");
		lblNgayLap.setText("Ngày lập: "+dateOnly.format(hoaDon.getThoiGianLap()));
		SimpleDateFormat timeOnly = new SimpleDateFormat("HH:mm:ss");
		lblGioLap.setText("Giờ lập: "+timeOnly.format(hoaDon.getThoiGianLap()));
		lblTongTien.setText(common.formatMoney(hoaDon.tinhTienHoaDonChuaThue()));
		lblThue.setText(common.formatMoney(hoaDon.tinhThueHoaDon()));
		lblThanhTien.setText(common.formatMoney(hoaDon.tinhTienHoaDonBaoGomThue()));
		
		for (ChiTietHoaDon i : hoaDon.getDsChiTietHoaDon()) {
			dataChiTietHoaDon.add(new ChiTietHoaDonTable(i.getSoLuong(), i.getThuoc().getTenThuoc(), i.getThuoc().getDonViTinh(),
					common.formatMoney(i.getGiaBan()),
					common.formatMoney(i.tinhTongTienChuaThue())));
		}
		
//		hoaDon.getDsChiTietHoaDon().forEach(i -> {
//			dataChiTietHoaDon.add(new ChiTietHoaDonTable(i.getSoLuong(), i.getThuoc().getTenThuoc(), i.getThuoc().getDonViTinh(),
//					common.formatMoney(i.getGiaBan()),
//					common.formatMoney(i.tinhTongTienChuaThue())));
//		});
	}

}
