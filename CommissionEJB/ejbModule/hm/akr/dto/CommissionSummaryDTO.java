package hm.akr.dto;

import java.io.Serializable;

/**
 * DTO class for Commission Summary
 * 
 * @author Hakuna Matata
 * @version 1.0
 */
public class CommissionSummaryDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String station_code = null;
	
	private String station_name = null;
	
	//Booking Commission
	private float bcTotal;
	
	//Delivery Commission
	private float dcTotal;
	
	//Cartage Collection Commission
	private float cccTotal;
	
	//Refund amount
	private float refundAmount;
	
	//Recovery amount
	private float recoveryAmount;
	
	//dd charges
	private float ddc = 0;
	
	//Net Amount
	private float netAmount;

	/**
	 * @return the bcTotal
	 */
	public float getBcTotal() {
		return bcTotal;
	}

	/**
	 * @param bcTotal the bcTotal to set
	 */
	public void setBcTotal(float bcTotal) {
		this.bcTotal = bcTotal;
	}

	/**
	 * @return the dcTotal
	 */
	public float getDcTotal() {
		return dcTotal;
	}

	/**
	 * @param dcTotal the dcTotal to set
	 */
	public void setDcTotal(float dcTotal) {
		this.dcTotal = dcTotal;
	}

	/**
	 * @return the cccTotal
	 */
	public float getCccTotal() {
		return cccTotal;
	}

	/**
	 * @param cccTotal the cccTotal to set
	 */
	public void setCccTotal(float cccTotal) {
		this.cccTotal = cccTotal;
	}

	/**
	 * @return the refundAmount
	 */
	public float getRefundAmount() {
		return refundAmount;
	}

	/**
	 * @param refundAmount the refundAmount to set
	 */
	public void setRefundAmount(float refundAmount) {
		this.refundAmount = refundAmount;
	}

	/**
	 * @return the recoveryAmount
	 */
	public float getRecoveryAmount() {
		return recoveryAmount;
	}

	/**
	 * @param recoveryAmount the recoveryAmount to set
	 */
	public void setRecoveryAmount(float recoveryAmount) {
		this.recoveryAmount = recoveryAmount;
	}

	/**
	 * @return the netAmount
	 */
	public float getNetAmount() {
		return netAmount;
	}

	/**
	 * @param netAmount the netAmount to set
	 */
	public void setNetAmount(float netAmount) {
		this.netAmount = netAmount;
	}

	/**
	 * @return the station_code
	 */
	public String getStation_code() {
		return station_code;
	}

	/**
	 * @param station_code the station_code to set
	 */
	public void setStation_code(String station_code) {
		this.station_code = station_code;
	}

	/**
	 * @return the station_name
	 */
	public String getStation_name() {
		return station_name;
	}

	/**
	 * @param station_name the station_name to set
	 */
	public void setStation_name(String station_name) {
		this.station_name = station_name;
	}

	/**
	 * @return the ddc
	 */
	public float getDdc() {
		return ddc;
	}

	/**
	 * @param ddc the ddc to set
	 */
	public void setDdc(float ddc) {
		this.ddc = ddc;
	}
	
	
	
}
