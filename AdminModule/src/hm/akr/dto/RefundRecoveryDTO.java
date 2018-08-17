package hm.akr.dto;

import java.io.Serializable;
import java.util.Date;

public class RefundRecoveryDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int rr_id;
	
	private String stationCode = null;
	
	private String description = null;
	
	private String contacts = null;
	
	private float amount = 0;
	
	private Date rr_date;
	
	private int recovery_flag;
	
	private String installment = null;
	
	private boolean isRecovery = true;


	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	

	/**
	 * @return the rr_date
	 */
	public Date getRr_date() {
		return rr_date;
	}

	/**
	 * @param rr_date the rr_date to set
	 */
	public void setRr_date(Date rr_date) {
		this.rr_date = rr_date;
	}

	/**
	 * @return the recovery_flag
	 */
	public int getRecovery_flag() {
		return recovery_flag;
	}

	/**
	 * @param recovery_flag the recovery_flag to set
	 */
	public void setRecovery_flag(int recovery_flag) {
		this.recovery_flag = recovery_flag;
	}

	
	/**
	 * @return the isRecovery
	 */
	public boolean isRecovery() {
		return isRecovery;
	}

	/**
	 * @param isRecovery the isRecovery to set
	 */
	public void setRecovery(boolean isRecovery) {
		this.isRecovery = isRecovery;
	}

	
	/**
	 * @return the rr_id
	 */
	public int getRr_id() {
		return rr_id;
	}

	/**
	 * @param rr_id the rr_id to set
	 */
	public void setRr_id(int rr_id) {
		this.rr_id = rr_id;
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
	 * @return the amount
	 */
	public float getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(float amount) {
		this.amount = amount;
	}

	/**
	 * @return the contacts
	 */
	public String getContacts() {
		return contacts;
	}

	/**
	 * @param contacts the contacts to set
	 */
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getInstallment() {
		return installment;
	}

	public void setInstallment(String installment) {
		this.installment = installment;
	}

	
	
}
