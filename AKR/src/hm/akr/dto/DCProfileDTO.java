package hm.akr.dto;

import java.io.Serializable;

public class DCProfileDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String station_code = null;

	private float dc_per_ton = 0;

	public String getStation_code() {
		return station_code;
	}

	public void setStation_code(String station_code) {
		this.station_code = station_code;
	}

	public float getDc_per_ton() {
		return dc_per_ton;
	}

	public void setDc_per_ton(float dc_per_ton) {
		this.dc_per_ton = dc_per_ton;
	}

}
