/**
 * 
 */
package hm.akr.dto;

import java.io.Serializable;

/**
 * @author user
 *
 */
public class BookedLRDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String stationCode = null;
	
	private String lrType = null;
	
	private int lrCount = 0;
	

	/**
	 * @return the stationCode
	 */
	public String getStationCode() {
		return stationCode;
	}

	/**
	 * @param stationCode the stationCode to set
	 */
	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	/**
	 * @return the lrType
	 */
	public String getLrType() {
		return lrType;
	}

	/**
	 * @param lrType the lrType to set
	 */
	public void setLrType(String lrType) {
		this.lrType = lrType;
	}

	/**
	 * @return the lrCount
	 */
	public int getLrCount() {
		return lrCount;
	}

	/**
	 * @param lrCount the lrCount to set
	 */
	public void setLrCount(int lrCount) {
		this.lrCount = lrCount;
	}

}
