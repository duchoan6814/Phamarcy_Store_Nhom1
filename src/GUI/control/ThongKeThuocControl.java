package GUI.control;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import DAO.DAOLoaiThuoc;
import DAO.DAOThuoc;
import common.Common;
import common.ThongKeThuocTable;
import entity.Thuoc;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class ThongKeThuocControl implements Initializable {

	public BarChart<String, Number> barChart;
	public CategoryAxis xAsix;
	public NumberAxis yAsix;
	public DatePicker dateFrom;
	public DatePicker dateTo;
	public Button btnThongKe;
	public RadioButton rbtTatCa;
	public PieChart pieChart;
	public TableView<ThongKeThuocTable> tblThuoc;
	public TableColumn<ThongKeThuocTable, Integer> colTop;
	public TableColumn<ThongKeThuocTable, String> colMaThuoc;
	public TableColumn<ThongKeThuocTable, String> colTenThuoc;
	public TableColumn<ThongKeThuocTable, String> colLoaiThuoc;
	public TableColumn<ThongKeThuocTable, String> colDonViTinh;
	public TableColumn<ThongKeThuocTable, String> colGia;
	public TableColumn<ThongKeThuocTable, String> colDoanhThu;

	private DAOLoaiThuoc daoLoaiThuoc = new DAOLoaiThuoc();
	private DAOThuoc daoThuoc = new DAOThuoc();
	private ObservableList<ThongKeThuocTable> dataTable;
	private Common common = new Common();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		initSomeField();
		initBarChart();
		initPieChart();
		initTable();
		initDataTable();
		initButtonThongKe();
	}

	private void initButtonThongKe() {
		// TODO Auto-generated method stub
		btnThongKe.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				actionButtonThongKe();
			}
		});
	}

	protected void actionButtonThongKe() {
		// TODO Auto-generated method stub
		String temp = checkNgayThongKe();
		if (temp == "pass") {
			dataTable.clear();
			daoThuoc.thongKeThuocDoanhThuThuocTheoThoiGian(rbtTatCa.isSelected(), dateFrom.getValue(), dateTo.getValue()).forEach((thuoc, doanhThu) -> {
				dataTable.add(new ThongKeThuocTable(dataTable.size()+1, thuoc.getId(), thuoc.getTenThuoc(), thuoc.getLoaiThuoc().getTenLoai(), thuoc.getDonViTinh(), common.formatMoney(thuoc.getGia()), common.formatMoney(doanhThu)));
			});
			
			barChart.getData().clear();
			pieChart.getData().clear();
			xAsix.getCategories().clear();
			
			daoLoaiThuoc.thongKeDoanhThuLoaiThuoc(rbtTatCa.isSelected(), dateFrom.getValue(), dateTo.getValue()).forEach((tenLoai, doanhThu) -> {
				pieChart.getData().add(new Data(tenLoai, doanhThu));
			});
			
			ObservableList<String> listXLabel = FXCollections.observableArrayList();
			Series series = new Series();
			daoLoaiThuoc.thongKeDoanhThuLoaiThuoc(rbtTatCa.isSelected(), dateFrom.getValue(), dateTo.getValue()).forEach((tenLoai, doanhThu) -> {
				series.getData().add(new XYChart.Data(tenLoai, doanhThu));
				listXLabel.add(tenLoai);
			});
			xAsix.setCategories(listXLabel);
			barChart.getData().add(series);
		}else {
			common.showNotification(AlertType.ERROR, "ERROR", temp);
		}
		
		
	}

	private void initDataTable() {
		// TODO Auto-generated method stub
		daoThuoc.thongKeThuocDoanhThuThuocTheoThoiGian(rbtTatCa.isSelected(), dateFrom.getValue(), dateTo.getValue()).forEach((thuoc, doanhThu) -> {
			dataTable.add(new ThongKeThuocTable(dataTable.size()+1, thuoc.getId(), thuoc.getTenThuoc(), thuoc.getLoaiThuoc().getTenLoai(), thuoc.getDonViTinh(), common.formatMoney(thuoc.getGia()), common.formatMoney(doanhThu)));
		});
	}

	private void initTable() {
		// TODO Auto-generated method stub
		dataTable = FXCollections.observableArrayList();

		colTop.setCellValueFactory(new PropertyValueFactory<>("top"));
		colMaThuoc.setCellValueFactory(new PropertyValueFactory<>("maThuoc"));
		colTenThuoc.setCellValueFactory(new PropertyValueFactory<>("tenThuoc"));
		colLoaiThuoc.setCellValueFactory(new PropertyValueFactory<>("loaiThuoc"));
		colDonViTinh.setCellValueFactory(new PropertyValueFactory<>("donViTinh"));
		colGia.setCellValueFactory(new PropertyValueFactory<>("gia"));
		colDoanhThu.setCellValueFactory(new PropertyValueFactory<>("doanhThu"));

		tblThuoc.setItems(dataTable);
	}

	private void initPieChart() {
		// TODO Auto-generated method stub
		daoLoaiThuoc.thongKeDoanhThuLoaiThuoc(rbtTatCa.isSelected(), dateFrom.getValue(), dateTo.getValue()).forEach((tenLoai, doanhThu) -> {
			pieChart.getData().add(new Data(tenLoai, doanhThu));
		});
		pieChart.setLabelsVisible(false);
	}

	private void initSomeField() {
		// TODO Auto-generated method stub
		rbtTatCa.setSelected(true);
		dateFrom.setDisable(true);
		dateTo.setDisable(true);
		rbtTatCa.selectedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				// TODO Auto-generated method stub
				if (rbtTatCa.isSelected()) {
					dateFrom.setDisable(true);
					dateTo.setDisable(true);
				}else {
					dateFrom.setDisable(false);
					dateTo.setDisable(false);
				}
			}
		});

	}
	
	private String checkNgayThongKe() {
		if (dateFrom.getValue() == null || dateTo.getValue() == null) {
			return "Không được để trống ngày";
		}
		
		if (dateFrom.getValue().compareTo(dateTo.getValue()) > 0) {
			return "Ngày bắt đầu phải nhỏ hơn hoặc bằng ngày kết thúc";
		}
		
		return "pass";
	}

	private void initBarChart() {
		// TODO Auto-generated method stub
		xAsix.setLabel("Loại Thuốc");
		yAsix.setLabel("Doanh Thu");
		xAsix.setTickLabelRotation(90);
		Series series = new Series();
		daoLoaiThuoc.thongKeDoanhThuLoaiThuoc(rbtTatCa.isSelected(), dateFrom.getValue(), dateTo.getValue()).forEach((tenLoai, doanhThu) -> {
			series.getData().add(new XYChart.Data(tenLoai, doanhThu));
		});

		barChart.getData().add(series);
	}

}
