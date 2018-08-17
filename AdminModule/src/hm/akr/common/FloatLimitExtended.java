package hm.akr.common;

import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Text;

public class FloatLimitExtended implements VerifyListener {
	
	
	int decimalLimit = 0;
	
	public FloatLimitExtended(int decimalLimit) {
		super();
		this.decimalLimit = decimalLimit;
	}

	public FloatLimitExtended() {
		super();
		decimalLimit = 2;
	}
	
	/**
	 * 
	 */
	
	
	public void verifyText(VerifyEvent event) {
		String temp = event.text;
		int dotindex = 0;		
		String decimals = null;
		int decimalLen = 0;	
		String txtValue = null;
		int cursorPosition = 0;
		boolean isDot = false;
		
		txtValue = ((Text) event.getSource()).getText();
		if(txtValue != null){
			cursorPosition = ((Text) event.getSource()).getCaretPosition();
		}				
		
		if(temp.length() > 1){		
			event.doit = true;
		}else{
			if (txtValue.contains(".")) {
				dotindex = txtValue.indexOf(".");				
				decimals = txtValue.substring(dotindex);
				decimalLen = decimals.length();
			}

			int len = temp.length();
			char myChar;		

			for (int i = 0; i < len; i++) {
				myChar = temp.charAt(i);
				
				if(dotindex > 0){
					if(!Character.isDigit(myChar) && myChar != '\b' && myChar != '.' || 
							myChar != '\b' && ((decimalLen > decimalLimit) && cursorPosition > dotindex)){
						event.doit = false;
						return;
					}
				}else if (!Character.isDigit(myChar) && myChar != '\b' && myChar != '.'  && myChar != '>' ) {
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

}
