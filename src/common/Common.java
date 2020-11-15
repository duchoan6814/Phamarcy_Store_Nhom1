package common;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Common {
	public String formatMoney(double money) {
		Locale locale = new Locale("vi", "VI");
		String pattern = "###,###đ";
		DecimalFormat dcf = (DecimalFormat) NumberFormat.getNumberInstance(locale);
	    dcf.applyPattern(pattern);
	    return dcf.format(money);
	}
	
	public static void main(String[] args) {
		Common cm = new Common();
		
		System.out.println(cm.formatMoney(15000000));
	}
}
