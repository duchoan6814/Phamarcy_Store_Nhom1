package GUI.control;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import DAO.DAOHoaDon;
import DAO.DAONhanVien;
import common.Common;
import common.NhanVienTable;
import entity.NhanVienBanThuoc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class ThongKeDoanhThuControl implements Initializable {
	private DAOHoaDon daoHoaDon = new DAOHoaDon();
	private DAONhanVien daoNhanVien = new DAONhanVien();
	private Common common = new Common();
	
	public ComboBox<String> cmbThongKeTheo;
	public Text lblSoHoaDon;
	public Text lblTongDoanhThu; 
	public TableView<NhanVienTable> tblNhanVien;
	public TableColumn<NhanVienTable, Integer> colSTT;
	public TableColumn<NhanVienTable, Integer> colSoHoaDon;
	public TableColumn<NhanVienTable, String> colHoTen;
	public TableColumn<NhanVienTable, String> colMaNhanVien;
	public TableColumn<NhanVienTable, String> colTongDoanhThu;
	public TableColumn colXem;
	public LineChart<?, ?> lineThongKe;
	
	private ObservableList<NhanVienTable> dataTable;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		initCmbThongKeTheo();
		intSomeField();
		initable();
		initDataForTable();
		initChart();
	}
	private void initChart() {
		// TODO Auto-generated method stub
		lineThongKe.getData().clear();
		XYChart.Series series = new XYChart.Series();
		LocalDate toDay = LocalDate.now();
		LocalDate date = LocalDate.of(toDay.getYear(), toDay.getMonthValue(), 1);
		
		while(date.getMonthValue() == toDay.getMonthValue()) {
			double doanhSo = daoHoaDon.getTongDoanhThuTheoNgay(date.toString());
			series.getData().add(new XYChart.Data<>(date.getDayOfMonth()+"-"+date.getMonthValue(), doanhSo));
			date = date.plusDays(1);
		}
		
		lineThongKe.getData().add(series);
	}
	private void initDataForTable() {
		// TODO Auto-generated method stub
		List<NhanVienBanThuoc> banThuocs = daoNhanVien.getlistNhanVien();
		banThuocs.forEach(i -> {
			dataTable.add(new NhanVienTable(dataTable.size(), daoNhanVien.getSoHoaDonTrongNgay(i.getId()), i.getHoTenDem()+" "+i.getTen(),
					common.formatMoney(daoNhanVien.getDoanhSoTrongNgay(i.getId())), i.getId()));
		});
	}
	private void initable() {
		// TODO Auto-generated method stub
		dataTable = FXCollections.observableArrayList();
		colHoTen.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
		colMaNhanVien.setCellValueFactory(new PropertyValueFactory<>("maNhanVien"));
		colSoHoaDon.setCellValueFactory(new PropertyValueFactory<>("soHoaDon"));
		colSTT.setCellValueFactory(new PropertyValueFactory<>("STT"));
		colTongDoanhThu.setCellValueFactory(new PropertyValueFactory<>("doanhThu"));
		
		tblNhanVien.setItems(dataTable);
	}
	private void intSomeField() {
		// TODO Auto-generated method stub
		lblSoHoaDon.setText(Integer.toString(daoHoaDon.getSoHoaDonTrongNgay()));
		lblTongDoanhThu.setText(common.formatMoney(daoHoaDon.getTongDoanhThuTrongNgay()));
	}
	private void initCmbThongKeTheo() {
		// TODO Auto-generated method stub
		ObservableList<String> listThongKeTheo = FXCollections.observableArrayList();
		listThongKeTheo.add("Ngày");
		listThongKeTheo.add("Tháng");
		listThongKeTheo.add("Năm");
		cmbThongKeTheo.setItems(listThongKeTheo);
		cmbThongKeTheo.setValue(listThongKeTheo.get(0));
	}

}
