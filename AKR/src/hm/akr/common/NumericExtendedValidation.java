package hm.akr.common;

import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;

/**
 * @version 1.0
 **/
public class NumericExtendedValidation implements VerifyListener {

	/**
	 * 
	 */
	public void verifyText(VerifyEvent event) {
		String temp = event.text;
		//System.out.println(temp);
		int len = temp.length();
		char myChar;

		for (int i = 0; i < len; i++) {
			myChar = temp.charAt(i);

			if (!Character.isDigit(myChar) && myChar != '\b' && myChar != 'n' && myChar != 'N' && myChar != 'I' && myChar != 'L') {
				event.doit = false;
				return;
			}
		}

		event.doit = true;
	}

}
