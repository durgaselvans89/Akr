package hm.akr.dto;

import java.io.Serializable;

public class OtherChargesDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String stationCode = null;
	private float lrCharge = 0;
	private float gsc = 0;
	
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
	 * @return the lrCharge
	 */
	public float getLrCharge() {
		return lrCharge;
	}
	/**
	 * @param lrCharge the lrCharge to set
	 */
	public void setLrCharge(float lrCharge) {
		this.lrCharge = lrCharge;
	}
	/**
	 * @return the gsc
	 */
	public float getGsc() {
		return gsc;
	}
	
	/**
	 * @param gsc the gsc to set
	 */
	public void setGsc(float gsc) {
		this.gsc = gsc;
	}
}
