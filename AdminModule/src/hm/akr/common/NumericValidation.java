package hm.akr.common;

import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;

/**
 * @version 1.0
 **/
public class NumericValidation implements VerifyListener {

	/**
	 * 
	 */
	public void verifyText(VerifyEvent event) {
		String temp = event.text;
		int len = temp.length();
		char myChar;

		for (int i = 0; i < len; i++) {
			myChar = temp.charAt(i);

			if (!Character.isDigit(myChar) && myChar != '\b') {
				event.doit = false;
				return;
			}
		}

		event.doit = true;
	}

}
