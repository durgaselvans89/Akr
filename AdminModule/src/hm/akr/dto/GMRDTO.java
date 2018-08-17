package hm.akr.dto;

import java.io.Serializable;

/**
 * 
 * @version 1.0
 * DTO class for GMR
 */
public class GMRDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String lrNumber = null;

	private String stationCode = null;
	
	private String vehicleNumber = null;
	
	private String driverName = null;
	
	private String dispatchTo = null;
	
	
	/**
	 * Constructor method
	 */
	public GMRDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * 
	 * @return
	 */
	public String getDriverName() {
		return driverName;
	}


	/**
	 * 
	 * @param driverName
	 */
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}


	/**
	 * 
	 * @return
	 */
	public String getLrNumber() {
		return lrNumber;
	}


	/**
	 * 
	 * @param lrNumber
	 */
	public void setLrNumber(String lrNumber) {
		this.lrNumber = lrNumber;
	}


	public String getStationCode() {
		return stationCode;
	}


	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}


	public String getVehicleNumber() {
		return vehicleNumber;
	}


	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}


	/**
	 * @return Returns the dispatchTo.
	 */
	public String getDispatchTo() {
		return dispatchTo;
	}


	/**
	 * @param dispatchTo The dispatchTo to set.
	 */
	public void setDispatchTo(String dispatchTo) {
		this.dispatchTo = dispatchTo;
	}

}
