package GUI.control;

import java.net.URL;
import java.util.ResourceBundle;

import DAO.DAOHoaDon;
import common.Common;
import common.NhanVienTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class ThongKeDoanhThuControl implements Initializable {
	private DAOHoaDon daoHoaDon = new DAOHoaDon();
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
	private ObservableList<NhanVienTable> dataTable;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		initCmbThongKeTheo();
		intSomeField();
		initable();
		initDataForTable();
	}
	private void initDataForTable() {
		// TODO Auto-generated method stub
		
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
