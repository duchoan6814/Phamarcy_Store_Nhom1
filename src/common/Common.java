package common;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Common {
	public String formatMoney(double money) {
		Locale locale = new Locale("vi", "VI");
		String pattern = "###,###đ";
		DecimalFormat dcf = (DecimalFormat) NumberFormat.getNumberInstance(locale);
	    dcf.applyPattern(pattern);
	    return dcf.format(money);
	}
	
	public void showNotification(AlertType alertType, String titleDialog, String content ) {
		Alert alert = new Alert(alertType);
		alert.setTitle(titleDialog);
		alert.setHeaderText(null);
		alert.setContentText(content);

		alert.showAndWait();
	}
	
	public static void main(String[] args) {
		Common cm = new Common();
		
		System.out.println(cm.formatMoney(15000000));
	}
	
}
