/**
 * 
 */
package hm.akr.dto;

import java.io.Serializable;

/**
 * Class for DailyStatusDTO
 */
public class DailyStatusDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String branch_code = null;
	
	private String station_code = null;

	private String name = null;

	private int topay_count = 0;

	private int paid_count = 0;

	private int billing_count = 0;
	
	private int GMRintime_count = 0;
	
	private int GMRouttime_count = 0;

	private int cr_count = 0;

	private int ds_no = 0;
	
	boolean mail_sent = false;
	
	/**
	 * @return the mail_sent
	 */
	public boolean isMail_sent() {
		return mail_sent;
	}


	/**
	 * @param mail_sent the mail_sent to set
	 */
	public void setMail_sent(boolean mail_sent) {
		this.mail_sent = mail_sent;
	}


	/**
	 * Constructor Method
	 */
	public DailyStatusDTO() {
		
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the topay_count
	 */
	public int getTopay_count() {
		return topay_count;
	}


	/**
	 * @param topay_count the topay_count to set
	 */
	public void setTopay_count(int topay_count) {
		this.topay_count = topay_count;
	}


	/**
	 * @return the paid_count
	 */
	public int getPaid_count() {
		return paid_count;
	}


	/**
	 * @param paid_count the paid_count to set
	 */
	public void setPaid_count(int paid_count) {
		this.paid_count = paid_count;
	}


	/**
	 * @return the billing_count
	 */
	public int getBilling_count() {
		return billing_count;
	}


	/**
	 * @param billing_count the billing_count to set
	 */
	public void setBilling_count(int billing_count) {
		this.billing_count = billing_count;
	}


	/**
	 * @return the gMRintime_count
	 */
	public int getGMRintime_count() {
		return GMRintime_count;
	}


	/**
	 * @param rintime_count the gMRintime_count to set
	 */
	public void setGMRintime_count(int rintime_count) {
		GMRintime_count = rintime_count;
	}


	/**
	 * @return the gMRouttime_count
	 */
	public int getGMRouttime_count() {
		return GMRouttime_count;
	}


	/**
	 * @param routtime_count the gMRouttime_count to set
	 */
	public void setGMRouttime_count(int routtime_count) {
		GMRouttime_count = routtime_count;
	}


	/**
	 * @return the cr_count
	 */
	public int getCr_count() {
		return cr_count;
	}


	/**
	 * @param cr_count the cr_count to set
	 */
	public void setCr_count(int cr_count) {
		this.cr_count = cr_count;
	}


	/**
	 * @return the ds_no
	 */
	public int getDs_no() {
		return ds_no;
	}


	/**
	 * @param ds_no the ds_no to set
	 */
	public void setDs_no(int ds_no) {
		this.ds_no = ds_no;
	}


	public String getBranch_code() {
		return branch_code;
	}


	public void setBranch_code(String branch_code) {
		this.branch_code = branch_code;
	}


	

}
