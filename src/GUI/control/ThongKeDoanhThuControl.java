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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.Callback;

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
	public CategoryAxis xLabel;

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
		xLabel.getCategories().clear();
		LocalDate toDay = LocalDate.now();
		LocalDate date = toDay.minusMonths(1);
		Series series = new XYChart.Series();
		ObservableList<String> listXLabel = FXCollections.observableArrayList();
		while(!date.toString().equals(toDay.plusDays(1).toString())) {
			double doanhSo = daoHoaDon.getTongDoanhThuTheoNgay(date.toString());
			series.getData().add(new XYChart.Data<>(date.getDayOfMonth()+"-"+date.getMonthValue(), doanhSo));
			listXLabel.add(date.getDayOfMonth()+"-"+date.getMonthValue());
			date = date.plusDays(1);
		}
		xLabel.setCategories(listXLabel);
		xLabel.setTickLabelRotation(50);
		lineThongKe.getData().add(series);
	}

	private void initDataForTable() {
		// TODO Auto-generated method stub
		dataTable.clear();
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
		createButtonXem();
		tblNhanVien.setItems(dataTable);
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
							TableColumn col = tblNhanVien.getColumns().get(1);
							String data = (String) col.getCellObservableValue(tblNhanVien.getItems().get(getIndex())).getValue();
							handleChartWhenXemNhanVien(data);
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
	
	private void handleChartWhenXemNhanVien(String data) {
		// TODO Auto-generated method stub
		if (cmbThongKeTheo.getValue().equals("Ngày")) {
			lineThongKe.getData().clear();
			xLabel.getCategories().clear();
			LocalDate toDay = LocalDate.now();
			LocalDate date = toDay.minusMonths(1);
			Series series = new XYChart.Series();
			ObservableList<String> listXLabel = FXCollections.observableArrayList();
			while(!date.toString().equals(toDay.plusDays(1).toString())) {
				double doanhSo = daoNhanVien.getDoanhSoByNgay(data, date.toString());
				series.getData().add(new XYChart.Data<>(date.getDayOfMonth()+"-"+date.getMonthValue(), doanhSo));
				listXLabel.add(date.getDayOfMonth()+"-"+date.getMonthValue());
				date = date.plusDays(1);
			}
			xLabel.setCategories(listXLabel);
			xLabel.setTickLabelRotation(50);
			lineThongKe.getData().add(series);
		}else if (cmbThongKeTheo.getValue().equals("Tháng")) {
			lineThongKe.getData().clear();
			xLabel.getCategories().clear();
			LocalDate toDay = LocalDate.now();
			LocalDate date = LocalDate.of(toDay.getYear(), 1, 1);
			Series series = new XYChart.Series();
			ObservableList<String> listXLabel = FXCollections.observableArrayList();
			while(date.getYear() == toDay.getYear()) {
				double doanhSo = daoNhanVien.getDoanhSoByThang(data, date.toString());
				series.getData().add(new XYChart.Data<>(date.getMonthValue()+"-"+date.getYear(), doanhSo));
				listXLabel.add(date.getMonthValue()+"-"+date.getYear());
				date = date.plusMonths(1);
			}
			xLabel.setCategories(listXLabel);
			lineThongKe.getData().add(series);
		}else {
			lineThongKe.getData().clear();
			xLabel.getCategories().clear();
			LocalDate toDay = LocalDate.now();
			LocalDate date = toDay.minusYears(5);
			Series series = new XYChart.Series();
			ObservableList<String> listXLabel = FXCollections.observableArrayList();
			while(date.getYear() != toDay.plusYears(1).getYear()) {
				double doanhSo = daoNhanVien.getDoanhSoByNam(data, date.toString());
				series.getData().add(new XYChart.Data<>(date.getYear()+"", doanhSo));
				listXLabel.add(date.getYear()+"");
				date = date.plusYears(1);
			}
			xLabel.setCategories(listXLabel);
			lineThongKe.getData().add(series);
		}
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

		cmbThongKeTheo.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				whenComboxChange();
			}
		});
	}

	private void whenComboxChange() {
		// TODO Auto-generated method stub
		if (cmbThongKeTheo.getValue().equals("Ngày")) {
			intSomeField();
			initDataForTable();
			initChart();
		}else if (cmbThongKeTheo.getValue().equals("Tháng")) {
			initDataThang();
		}else if (cmbThongKeTheo.getValue().equals("Năm")) {
			initDataNam();
		} 
	}
	private void initDataNam() {
		// TODO Auto-generated method stub
		initDataForSomeFileByNam();
		initDataForTableByNam();
		initDataForCharByNam();
	}

	private void initDataForCharByNam() {
		// TODO Auto-generated method stub
		lineThongKe.getData().clear();
		xLabel.getCategories().clear();
		LocalDate toDay = LocalDate.now();
		LocalDate date = toDay.minusYears(5);
		Series series = new XYChart.Series();
		ObservableList<String> listXLabel = FXCollections.observableArrayList();
		while(date.getYear() != toDay.plusYears(1).getYear()) {
			double doanhSo = daoHoaDon.getDoanhThuTheoNam(date.toString());
			series.getData().add(new XYChart.Data<>(date.getYear()+"", doanhSo));
			listXLabel.add(date.getYear()+"");
			date = date.plusYears(1);
		}
		xLabel.setCategories(listXLabel);
		lineThongKe.getData().add(series);
	}
	private void initDataForTableByNam() {
		// TODO Auto-generated method stub
		dataTable.clear();
		List<NhanVienBanThuoc> banThuocs = daoNhanVien.getlistNhanVien();
		banThuocs.forEach(i -> {
			dataTable.add(new NhanVienTable(dataTable.size(), daoNhanVien.getTongHoaDonNamHienTai(i.getId()), i.getHoTenDem()+" "+i.getTen(),
					common.formatMoney(daoNhanVien.getTongDoanhThuNamHienTai(i.getId())), i.getId()));
		});
	}
	private void initDataForSomeFileByNam() {
		// TODO Auto-generated method stub
		lblSoHoaDon.setText(daoHoaDon.getTongHoaDonNamHienTai()+"");
		lblTongDoanhThu.setText(common.formatMoney(daoHoaDon.getTongDoanhThuNamHienTai()));
	}
	//	===============================
	private void initDataThang() {
		// TODO Auto-generated method stub
		initDataForSomeFieldByThang();
		initDataTableTheoThang();
		initDataChartTheoThang();
	}
	private void initDataChartTheoThang() {
		// TODO Auto-generated method stub
		lineThongKe.getData().clear();
		xLabel.getCategories().clear();
		LocalDate toDay = LocalDate.now();
		LocalDate date = LocalDate.of(toDay.getYear(), 1, 1);
		Series series = new XYChart.Series();
		ObservableList<String> listXLabel = FXCollections.observableArrayList();
		while(date.getYear() == toDay.getYear()) {
			double doanhSo = daoHoaDon.getTongDoanhThuByThang(date.toString());
			series.getData().add(new XYChart.Data<>(date.getMonthValue()+"-"+date.getYear(), doanhSo));
			listXLabel.add(date.getMonthValue()+"-"+date.getYear());
			date = date.plusMonths(1);
		}
		xLabel.setCategories(listXLabel);
		lineThongKe.getData().add(series);
	}
	private void initDataTableTheoThang() {
		// TODO Auto-generated method stub
		dataTable.clear();
		List<NhanVienBanThuoc> banThuocs = daoNhanVien.getlistNhanVien();
		banThuocs.forEach(i -> {
			dataTable.add(new NhanVienTable(dataTable.size(), daoNhanVien.getSoHoaDonTrongThangHienTai(i.getId()), i.getHoTenDem()+" "+i.getTen(),
					common.formatMoney(daoNhanVien.getTongDoanhThuTheoThangHienTai(i.getId())), i.getId()));
		});
	}
	private void initDataForSomeFieldByThang() {
		// TODO Auto-generated method stub
		lblSoHoaDon.setText(daoHoaDon.getSoHoaDonTheoThangHienTai()+"");
		lblTongDoanhThu.setText(common.formatMoney(daoHoaDon.getTongDoanhThuTheoThangHienTai()));
	}

}
