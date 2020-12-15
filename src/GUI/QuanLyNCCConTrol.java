package GUI;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import DAO.DAONhaCungCap;
import common.Common;
import common.PhieuNhapTable;
import common.QLNhaCungCapTable;
import entity.NhaCungCap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class QuanLyNCCConTrol implements Initializable {
	ObservableList<QLNhaCungCapTable> data;
	private DAONhaCungCap daoNhaCungCap = new DAONhaCungCap();
	Common common = new Common();
	
	
	public TextField txtMa;
	public TextField txtTen;
	public TextField txtSDT;
	public TextField txtFax;
	public TextField txtTrangChu;
	public TextField txtDiaChi;
	public Button btnTim;
	public Button btnThem;
	
	public TableView<QLNhaCungCapTable> tabNCC;
	public TableColumn<QLNhaCungCapTable, Integer> colSTT;
	public TableColumn<QLNhaCungCapTable, String> colMa;
	public TableColumn<QLNhaCungCapTable, String> colTen;
	public TableColumn<QLNhaCungCapTable, String> colSDT;
	public TableColumn<QLNhaCungCapTable, String> colFax;
	public TableColumn<QLNhaCungCapTable, String> colTrangChu;
	public TableColumn<QLNhaCungCapTable, String> colDiaChi;
	public TableColumn colSua;
	public TableColumn colXoa;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initbtnTim();
		initTable();
	}

	private void initTable() {
		data = FXCollections.observableArrayList();
		colSTT.setCellValueFactory(new PropertyValueFactory<>("STT"));
		colTen.setCellValueFactory(new PropertyValueFactory<>("tenNCC"));
		colMa.setCellValueFactory(new PropertyValueFactory<>("maNCC"));
		colSDT.setCellValueFactory(new PropertyValueFactory<>("sdt"));
		colFax.setCellValueFactory(new PropertyValueFactory<>("fax"));
		colTrangChu.setCellValueFactory(new PropertyValueFactory<>("trangChu"));
		colDiaChi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
		tabNCC.setItems(data);
	}

	private void initbtnTim() {
		
		btnTim.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				data.clear();
				
				List<NhaCungCap> list = daoNhaCungCap.filterPhieuNhapNCC(txtMa.getText(), txtTen.getText(), txtSDT.getText(), txtFax.getText(), txtTrangChu.getText(), txtDiaChi.getText());
				if(list.size() <= 0) {
					common.showNotification(AlertType.INFORMATION, "No Data", "Không tìm thấy kết quả phù hợp!");
				}else {
					list.forEach(i -> {
						QLNhaCungCapTable capTable = new QLNhaCungCapTable(data.size(), i.getId(), i.getTenNhaCungCap(), i.getSoDienThoai(), i.getEmail(), i.getTrangChu(), i.getDiaChi());
						data.add(capTable);
					});
				}
			}
		});
	}
	
}
