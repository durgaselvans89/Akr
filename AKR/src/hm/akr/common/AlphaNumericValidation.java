package hm.akr.common;

import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;

/**
 * 
 */
public class AlphaNumericValidation implements VerifyListener {

	/**
	 * 
	 */
	public void verifyText(VerifyEvent event) {
		String temp = event.text;
		int len = temp.length();
		char myChar;

		for (int i = 0; i < len; i++) {
			myChar = temp.charAt(i);

			if (!Character.isLetterOrDigit(myChar) && myChar != '\b') {
				event.doit = false;
				return;
			}
		}
		event.doit = true;
	}

}
