package hm.akr.dto;

import java.io.Serializable;

public class BftDTO implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	private String stationCode = null;
	
	private float bpi = 0;
	
	/**
	 * Constructor method
	 */
	public BftDTO() {
		super();
	}

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
	 * @return the bpi
	 */
	public float getBpi() {
		return bpi;
	}

	/**
	 * @param bpi the bpi to set
	 */
	public void setBpi(float bpi) {
		this.bpi = bpi;
	}
}
