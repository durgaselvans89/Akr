package hm.akr.common;

public class RoundOff {

	/**
	 * 
	 * @param value
	 * @return
	 */
	public static float getRounded2Decimal(float value) {

		if (value == 0) {
			return 0;
		} else {
			value = value * 100;
			value = (float) Math.ceil(value);
			value = value / 100;
		}
		return value;
	}
}
