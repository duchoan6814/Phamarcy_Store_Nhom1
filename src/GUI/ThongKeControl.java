package GUI;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;


import DAO.DAONhanVien;
import common.Common;
import entity.NhanVienBanThuoc;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.text.Text;

public class ThongKeControl implements Initializable {
	
	Common common = new Common();
	
	NhanVienBanThuoc nhanVienBanThuoc;
	DAONhanVien daoNhanVien = new DAONhanVien();
	
	public LineChart<?, ?> chartThongKe;
	public Text lblSoHoaDonNgay;
	public Text lblDoanhThuNgay;
	public Text lblSoHoaDonTong;
	public Text lblDoanhThuTong;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		initTopThongKe();
		
		chartThongKe.getData().clear();
		
		XYChart.Series series = new XYChart.Series();
		LocalDate toDay = LocalDate.now();
		LocalDate date = LocalDate.of(toDay.getYear(), toDay.getMonthValue(), 1);

		while(date.getMonthValue() == toDay.getMonthValue()) {
			double doanhSo = daoNhanVien.getDoanhSoByNgay(nhanVienBanThuoc.getId(), date.toString());
			series.getData().add(new XYChart.Data<>(date.toString(), doanhSo));
			date = date.plusDays(1);
		}
		
		chartThongKe.getData().add(series);
		
	}

	private void initTopThongKe() {
		// TODO Auto-generated method stub
		lblSoHoaDonNgay.setText(Integer.toString(daoNhanVien.getSoHoaDonTrongNgay(nhanVienBanThuoc.getId())));
		lblSoHoaDonTong.setText(Integer.toString(daoNhanVien.getTotalHoaDonByNhanVien(nhanVienBanThuoc.getId())));
		lblDoanhThuNgay.setText(common.formatMoney(daoNhanVien.getDoanhSoTrongNgay(nhanVienBanThuoc.getId())));
		lblDoanhThuTong.setText(common.formatMoney(daoNhanVien.getDoanhSoTong(nhanVienBanThuoc.getId())));
	}

	public NhanVienBanThuoc getNhanVienBanThuoc() {
		return nhanVienBanThuoc;
	}

	public void setNhanVienBanThuoc(NhanVienBanThuoc nhanVienBanThuoc) {
		this.nhanVienBanThuoc = nhanVienBanThuoc;
	}

	
	
}
