/**
 * 
 */
package hm.akr.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author user
 *
 */
public class MissingCustomersDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String customer = "";
	
	private float maxBft = 0;
	
	private float minBft = 0;
	
	private Date maxBftDate = null;
	
	private Date minBftDate = null;
	
	private float maxCW = 0;
	
	private float minCW = 0;
	
	private Date maxCWDate = null;
	
	private Date minCWDate = null;
	
	private float lastMonthBft = 0;
	
	private float currMonthBft = 0;
	
	private float lastMonthCW = 0;
	
	private float currMonthCW = 0;

	/**
	 * @return the customer
	 */
	public String getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(String customer) {
		this.customer = customer;
	}

	/**
	 * @return the maxBft
	 */
	public float getMaxBft() {
		return maxBft;
	}

	/**
	 * @param maxBft the maxBft to set
	 */
	public void setMaxBft(float maxBft) {
		this.maxBft = maxBft;
	}

	/**
	 * @return the minBft
	 */
	public float getMinBft() {
		return minBft;
	}

	/**
	 * @param minBft the minBft to set
	 */
	public void setMinBft(float minBft) {
		this.minBft = minBft;
	}

	/**
	 * @return the maxBftDate
	 */
	public Date getMaxBftDate() {
		return maxBftDate;
	}

	/**
	 * @param maxBftDate the maxBftDate to set
	 */
	public void setMaxBftDate(Date maxBftDate) {
		this.maxBftDate = maxBftDate;
	}

	/**
	 * @return the minBftDate
	 */
	public Date getMinBftDate() {
		return minBftDate;
	}

	/**
	 * @param minBftDate the minBftDate to set
	 */
	public void setMinBftDate(Date minBftDate) {
		this.minBftDate = minBftDate;
	}

	/**
	 * @return the maxCW
	 */
	public float getMaxCW() {
		return maxCW;
	}

	/**
	 * @param maxCW the maxCW to set
	 */
	public void setMaxCW(float maxCW) {
		this.maxCW = maxCW;
	}

	/**
	 * @return the minCW
	 */
	public float getMinCW() {
		return minCW;
	}

	/**
	 * @param minCW the minCW to set
	 */
	public void setMinCW(float minCW) {
		this.minCW = minCW;
	}

	/**
	 * @return the maxCWDate
	 */
	public Date getMaxCWDate() {
		return maxCWDate;
	}

	/**
	 * @param maxCWDate the maxCWDate to set
	 */
	public void setMaxCWDate(Date maxCWDate) {
		this.maxCWDate = maxCWDate;
	}

	/**
	 * @return the minCWDate
	 */
	public Date getMinCWDate() {
		return minCWDate;
	}

	/**
	 * @param minCWDate the minCWDate to set
	 */
	public void setMinCWDate(Date minCWDate) {
		this.minCWDate = minCWDate;
	}

	/**
	 * @return the lastMonthBft
	 */
	public float getLastMonthBft() {
		return lastMonthBft;
	}

	/**
	 * @param lastMonthBft the lastMonthBft to set
	 */
	public void setLastMonthBft(float lastMonthBft) {
		this.lastMonthBft = lastMonthBft;
	}

	/**
	 * @return the currMonthBft
	 */
	public float getCurrMonthBft() {
		return currMonthBft;
	}

	/**
	 * @param currMonthBft the currMonthBft to set
	 */
	public void setCurrMonthBft(float currMonthBft) {
		this.currMonthBft = currMonthBft;
	}

	/**
	 * @return the lastMonthCW
	 */
	public float getLastMonthCW() {
		return lastMonthCW;
	}

	/**
	 * @param lastMonthCW the lastMonthCW to set
	 */
	public void setLastMonthCW(float lastMonthCW) {
		this.lastMonthCW = lastMonthCW;
	}

	/**
	 * @return the currMonthCW
	 */
	public float getCurrMonthCW() {
		return currMonthCW;
	}

	/**
	 * @param currMonthCW the currMonthCW to set
	 */
	public void setCurrMonthCW(float currMonthCW) {
		this.currMonthCW = currMonthCW;
	}
	
	
	
	
}
