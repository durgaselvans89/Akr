/**
 * 
 */
package hm.akr.dto;

import java.io.Serializable;

/**
 * @author Naruto1
 * 
 */
public class CCProfileDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String station_code = null;

	private int cc_limit = 0;

	private int cc_consider = 0;
	private int cc_refund = 0;
	private int cc_special = 0;
	private int cc_commodity = 0;

	/**
	 * @return the cc_limit
	 */
	public int getCc_limit() {
		return cc_limit;
	}

	/**
	 * @param cc_limit
	 *            the cc_limit to set
	 */
	public void setCc_limit(int cc_limit) {
		this.cc_limit = cc_limit;
	}

	/**
	 * @return the cc_consider
	 */
	public int getCc_consider() {
		return cc_consider;
	}

	/**
	 * @param cc_consider
	 *            the cc_consider to set
	 */
	public void setCc_consider(int cc_consider) {
		this.cc_consider = cc_consider;
	}

	/**
	 * @return the cc_refund
	 */
	public int getCc_refund() {
		return cc_refund;
	}

	/**
	 * @param cc_refund
	 *            the cc_refund to set
	 */
	public void setCc_refund(int cc_refund) {
		this.cc_refund = cc_refund;
	}

	/**
	 * @return the cc_special
	 */
	public int getCc_special() {
		return cc_special;
	}

	/**
	 * @param cc_special
	 *            the cc_special to set
	 */
	public void setCc_special(int cc_special) {
		this.cc_special = cc_special;
	}

	/**
	 * @return the cc_commodity
	 */
	public int getCc_commodity() {
		return cc_commodity;
	}

	/**
	 * @param cc_commodity
	 *            the cc_commodity to set
	 */
	public void setCc_commodity(int cc_commodity) {
		this.cc_commodity = cc_commodity;
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

}
