package hm.akr.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CellValidation {

	public static boolean checkCellNumber(String phone) {

		Pattern paten = Pattern.compile("^[8-9]\\d{9}$");
		Matcher fit = paten.matcher(phone);
		if (fit.matches()) {
			return true;
		} else {
			return false;
		}

	}

}
