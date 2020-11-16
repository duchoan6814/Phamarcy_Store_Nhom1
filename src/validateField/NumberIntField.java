package validateField;

import javafx.scene.control.IndexRange;
import javafx.scene.control.TextField;

public class NumberIntField extends TextField {
	@Override
	public void replaceText(IndexRange arg0, String arg1) {
		// TODO Auto-generated method stub
		if (arg1.matches("[0-9]")) {
			System.out.println("hello");
			super.replaceText(arg0, arg1);
		}
	}
	
	@Override
	public void replaceSelection(String arg0) {
		// TODO Auto-generated method stub
		super.replaceSelection(arg0);
	}
}
