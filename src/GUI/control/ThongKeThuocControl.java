package GUI.control;

import java.net.URL;
import java.util.ResourceBundle;

import DAO.DAOLoaiThuoc;
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
import javafx.scene.control.Tooltip;
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

	private DAOLoaiThuoc daoLoaiThuoc = new DAOLoaiThuoc();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		initSomeField();
		initBarChart();
		initPieChart();
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
	
	}

	private void initBarChart() {
		// TODO Auto-generated method stub
		xAsix.setLabel("Loại Thuốc");
		yAsix.setLabel("Doanh Thu");
		xAsix.setTickLabelRotation(35);
		Series series = new Series();

		daoLoaiThuoc.thongKeDoanhThuLoaiThuoc(rbtTatCa.isSelected(), dateFrom.getValue(), dateTo.getValue()).forEach((tenLoai, doanhThu) -> {
			series.getData().add(new XYChart.Data(tenLoai, doanhThu));
		});

		barChart.getData().add(series);
	}

}
