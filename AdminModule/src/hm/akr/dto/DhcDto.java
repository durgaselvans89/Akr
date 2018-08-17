package hm.akr.dto;

import java.io.Serializable;

public class DhcDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String stationCode = null;
	private float dhc = 0;
	
	
	public String getStationCode() {
		return stationCode;
	}
	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}
	public float getDhc() {
		return dhc;
	}
	public void setDhc(float dhc) {
		this.dhc = dhc;
	}
	
}
