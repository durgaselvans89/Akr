package hm.akr.common;

import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Text;

public class FloatValidation implements VerifyListener {
	/**
	 * 
	 */
	public void verifyText(VerifyEvent event) {
		String temp = event.text;
		int len = temp.length();
		char myChar;
		String txtValue = null;
		boolean isDot = false;
		
		txtValue = ((Text) event.getSource()).getText();
		
		for (int i = 0; i < len; i++) {
			myChar = temp.charAt(i);
			if (!Character.isDigit(myChar) && myChar != '\b' && myChar != '.') {
				event.doit = false;
				return;
			}						
		}		
				
		for(int j = 0; j < txtValue.length(); j++){
			char dot = txtValue.charAt(j);			
			if(dot == '.'){
				isDot = true;
			}
		}
		
		if(isDot && temp.equals(".")){
			event.doit = false;
			return;
		}
		
		event.doit = true;
	}

}
