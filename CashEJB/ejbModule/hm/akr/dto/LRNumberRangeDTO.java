/**
 * 
 */
package hm.akr.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Administrator
 * 
 */
public class LRNumberRangeDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String branchCode = null;

	private String stationCode = null;

	private String type = null;

	private String rangeFrom = null;

	private String rangeTo = null;

	private int topay = 0;

	private int paid = 0;

	private int billing = 0;

	private int cr = 0;

	private Date allocationDate = null;

	private String lrNumber = null;

	public String getLrNumber() {
		return lrNumber;
	}

	public void setLrNumber(String lrNumber) {
		this.lrNumber = lrNumber;
	}

	/**
	 * 
	 */
	public LRNumberRangeDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the branchCode
	 */
	public String getBranchCode() {
		return branchCode;
	}

	/**
	 * @param branchCode
	 *            the branchCode to set
	 */
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	/**
	 * @return the stationCode
	 */
	public String getStationCode() {
		return stationCode;
	}

	/**
	 * @param stationCode
	 *            the stationCode to set
	 */
	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the rangeFrom
	 */
	public String getRangeFrom() {
		return rangeFrom;
	}

	/**
	 * @param rangeFrom
	 *            the rangeFrom to set
	 */
	public void setRangeFrom(String rangeFrom) {
		this.rangeFrom = rangeFrom;
	}

	/**
	 * @return the rangeTo
	 */
	public String getRangeTo() {
		return rangeTo;
	}

	/**
	 * @param rangeTo
	 *            the rangeTo to set
	 */
	public void setRangeTo(String rangeTo) {
		this.rangeTo = rangeTo;
	}

	/**
	 * @return the topay
	 */
	public int getTopay() {
		return topay;
	}

	/**
	 * @param topay
	 *            the topay to set
	 */
	public void setTopay(int topay) {
		this.topay = topay;
	}

	/**
	 * @return the paid
	 */
	public int getPaid() {
		return paid;
	}

	/**
	 * @param paid
	 *            the paid to set
	 */
	public void setPaid(int paid) {
		this.paid = paid;
	}

	/**
	 * @return the billing
	 */
	public int getBilling() {
		return billing;
	}

	/**
	 * @param billing
	 *            the billing to set
	 */
	public void setBilling(int billing) {
		this.billing = billing;
	}

	/**
	 * @return the cr
	 */
	public int getCr() {
		return cr;
	}

	/**
	 * @param cr
	 *            the cr to set
	 */
	public void setCr(int cr) {
		this.cr = cr;
	}

	public Date getAllocationDate() {
		return allocationDate;
	}

	public void setAllocationDate(Date allocationDate) {
		this.allocationDate = allocationDate;
	}

}
