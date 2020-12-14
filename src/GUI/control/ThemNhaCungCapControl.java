package GUI.control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ThemNhaCungCapControl implements Initializable {
	public TextField txtMaNhaCungCap;
	public TextField txtTenNhaCungCap;
	public TextField txtSoDienThoai;
	public TextField txtFAX;
	public TextField txtTrangChu;
	public TextField txtSoNha;
	public Button btnThem;
	public Button btnHuy;
	public ComboBox<String> cmbTinh;
	public ComboBox<String> cmbQuan;
	public ComboBox<String> cmbPhuong;
	public Text lblTenNhaCungCap;
	public Text lblSoDienThoai;
	public Text lblFAX;
	public Text lblTrangChu;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		initField();
	}

	private void initField() {
		// TODO Auto-generated method stub
		initTenNhaCungCap();
		initSoDienThoai();
		initGmail();
		initTrangChu();
	}

	private void initTrangChu() {
		// TODO Auto-generated method stub
		txtTrangChu.textProperty().addListener((o, old, newv) -> {
			checkoutTrangChu();
		});
	}
	private boolean checkoutTrangChu() {
		// TODO Auto-generated method stub
		if (txtTrangChu.getText().isEmpty()) {
			return true;
		}
		
		if (!txtTrangChu.getText().matches("^[a-zA-Z0-9][a-zA-Z0-9-]{1,61}[a-zA-Z0-9]\\.[a-zA-Z]{2,}$")) {
			lblTrangChu.setText("Vui lòng nhập đúng domain của doanh nghiệp!");
			return false;
		}
		lblTrangChu.setText("");
		return true;
	}

	//=====================================================================
	private void initGmail() {
		// TODO Auto-generated method stub
		txtFAX.textProperty().addListener((o, old, newv) -> {
			checkEmail();
		});
	}
	private boolean checkEmail() {
		// TODO Auto-generated method stub
		if (txtFAX.getText().isEmpty()) {
			return true;
		}
		
		if (!txtFAX.getText().matches("(?:[a-z0-9!#$%&'*+/=?^_`\\{|\\}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`\\{|\\}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
			lblFAX.setText("Nhập đúng email của doanh nghiệp!");
			return false;
		}
		lblFAX.setText("");
		return true;
	}

	//=============================================================
	private void initSoDienThoai() {
		// TODO Auto-generated method stub
		txtSoDienThoai.textProperty().addListener((o, old, newv) -> {
			checkSoDienThoai();
		});
	}
	private boolean checkSoDienThoai() {
		// TODO Auto-generated method stub
		if (txtSoDienThoai.getText().isEmpty()) {
			lblSoDienThoai.setText("* Số điện thoại không được để trống!");
			return false;
		}

		if (!txtSoDienThoai.getText().matches("(84|0[3|5|7|8|9])+([0-9]{8})\\b")) {
			lblSoDienThoai.setText("* Số điện thoại có 10 chữ số bắt đầu bằng 84, 03, 05, 07, 08, 09!");
			return false;
		}

		lblSoDienThoai.setText("*");
		return true;
	}

	//================================================
	private void initTenNhaCungCap() {
		// TODO Auto-generated method stub
		txtTenNhaCungCap.textProperty().addListener((o, old, newv) -> {
			checkTenNhaCungCap();
		});

	}

	private boolean checkTenNhaCungCap() {
		// TODO Auto-generated method stub
		if (txtTenNhaCungCap.getText().isEmpty()) {
			lblTenNhaCungCap.setText("* Tên nhà cung cấp không được bỏ trống!");
			return false;
		}

		lblTenNhaCungCap.setText("*");
		return true;
	}
}
