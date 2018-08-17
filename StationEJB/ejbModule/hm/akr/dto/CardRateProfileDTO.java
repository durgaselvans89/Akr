package hm.akr.dto;

import java.io.Serializable;

/**
 * DTO class for Distance List
 */
public class CardRateProfileDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private float equals_card_rate = 0;

	private float above_card_rate = 0;

	private float upto_20_card_rate = 0;

	private float above_20_card_rate = 0;

	private String station_code = null;

	private String lr_type = null;

	/**
	 * @return the equals_card_rate
	 */
	public float getEquals_card_rate() {
		return equals_card_rate;
	}

	/**
	 * @param equals_card_rate
	 *            the equals_card_rate to set
	 */
	public void setEquals_card_rate(float equals_card_rate) {
		this.equals_card_rate = equals_card_rate;
	}

	/**
	 * @return the above_card_rate
	 */
	public float getAbove_card_rate() {
		return above_card_rate;
	}

	/**
	 * @param above_card_rate
	 *            the above_card_rate to set
	 */
	public void setAbove_card_rate(float above_card_rate) {
		this.above_card_rate = above_card_rate;
	}

	/**
	 * @return the upto_20_card_rate
	 */
	public float getUpto_20_card_rate() {
		return upto_20_card_rate;
	}

	/**
	 * @param upto_20_card_rate
	 *            the upto_20_card_rate to set
	 */
	public void setUpto_20_card_rate(float upto_20_card_rate) {
		this.upto_20_card_rate = upto_20_card_rate;
	}

	/**
	 * @return the above_20_card_rate
	 */
	public float getAbove_20_card_rate() {
		return above_20_card_rate;
	}

	/**
	 * @param above_20_card_rate
	 *            the above_20_card_rate to set
	 */
	public void setAbove_20_card_rate(float above_20_card_rate) {
		this.above_20_card_rate = above_20_card_rate;
	}

	/**
	 * @return the station_code
	 */
	public String getStation_code() {
		return station_code;
	}

	/**
	 * @param station_code
	 *            the station_code to set
	 */
	public void setStation_code(String station_code) {
		this.station_code = station_code;
	}

	/**
	 * @return the lr_type
	 */
	public String getLr_type() {
		return lr_type;
	}

	/**
	 * @param lr_type
	 *            the lr_type to set
	 */
	public void setLr_type(String lr_type) {
		this.lr_type = lr_type;
	}

}
