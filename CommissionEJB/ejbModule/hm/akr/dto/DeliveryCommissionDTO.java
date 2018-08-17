package hm.akr.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO Class for Delivery Commission
 * 
 * @author Hakuna Matata
 * @version 1.0
 */
public class DeliveryCommissionDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	// LR Number
	private String lrno;

	// Delivered Date
	private Date deliveredDate;

	// Lr Date
	private Date lrDate;

	// Lr Type
	private String lrType;

	// Actual Weight
	private float actualWeight;

	// Delivery commission
	private float dc;

	private int rate_type = 0;
	
	private float ddcFree;

	public int getRate_type() {
		return rate_type;
	}

	public void setRate_type(int rate_type) {
		this.rate_type = rate_type;
	}

	/**
	 * @return the lrno
	 */
	public String getLrno() {
		return lrno;
	}

	/**
	 * @param lrno
	 *            the lrno to set
	 */
	public void setLrno(String lrno) {
		this.lrno = lrno;
	}

	/**
	 * @return the deliveredDate
	 */
	public Date getDeliveredDate() {
		return deliveredDate;
	}

	/**
	 * @param deliveredDate
	 *            the deliveredDate to set
	 */
	public void setDeliveredDate(Date deliveredDate) {
		this.deliveredDate = deliveredDate;
	}

	/**
	 * @return the actualWeight
	 */
	public float getActualWeight() {
		return actualWeight;
	}

	/**
	 * @param actualWeight
	 *            the actualWeight to set
	 */
	public void setActualWeight(float actualWeight) {
		this.actualWeight = actualWeight;
	}

	/**
	 * @return the dc
	 */
	public float getDc() {
		return dc;
	}

	/**
	 * @param dc
	 *            the dc to set
	 */
	public void setDc(float dc) {
		this.dc = dc;
	}

	/**
	 * @return the lrDate
	 */
	public Date getLrDate() {
		return lrDate;
	}

	/**
	 * @param lrDate
	 *            the lrDate to set
	 */
	public void setLrDate(Date lrDate) {
		this.lrDate = lrDate;
	}

	/**
	 * @return the lrType
	 */
	public String getLrType() {
		return lrType;
	}

	/**
	 * @param lrType
	 *            the lrType to set
	 */
	public void setLrType(String lrType) {
		this.lrType = lrType;
	}

	public float getDdcFree() {
		return ddcFree;
	}

	public void setDdcFree(float ddcFree) {
		this.ddcFree = ddcFree;
	}

}
