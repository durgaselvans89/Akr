package hm.akr.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @version 1.0
 * DTO class for DRS
 */

public class DRSDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String no = null;

	private Date date = null;
	
	private String station_code = null;

	private float paid_collection_amount = 0;

	private float topay_collection_amount = 0;

	private float add_remittance = 0;

	private float less_remittance = 0;

	private float grand_total = 0;
	
	private float dd_reimbursement = 0;
		
	private String from_LR_NO = null;
	
	private String to_LR_NO = null;
	
	private String from_CR_NO = null;
	
	private String to_CR_NO = null;
	
	private boolean status = false;
	
	private RemittanceDetailsDTO[] remittanceDetails = null;
	/**
	 * Constructor method
	 */	
	public DRSDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * @return Returns the add_remittance.
	 */
	public float getAdd_remittance() {
		return add_remittance;
	}


	/**
	 * @param add_remittance The add_remittance to set.
	 */
	public void setAdd_remittance(float add_remittance) {
		this.add_remittance = add_remittance;
	}
	
	public RemittanceDetailsDTO[] getRemittanceDetails() {
		return remittanceDetails;
	}

	/**
	 * @return Returns the date.
	 */
	public Date getDate() {
		return date;
	}


	/**
	 * @param date The date to set.
	 */
	public void setDate(Date date) {
		this.date = date;
	}


	/**
	 * @return Returns the grand_total.
	 */
	public float getGrand_total() {
		return grand_total;
	}


	/**
	 * @param grand_total The grand_total to set.
	 */
	public void setGrand_total(float grand_total) {
		this.grand_total = grand_total;
	}


	/**
	 * @return Returns the less_remittance.
	 */
	public float getLess_remittance() {
		return less_remittance;
	}


	/**
	 * @param less_remittance The less_remittance to set.
	 */
	public void setLess_remittance(float less_remittance) {
		this.less_remittance = less_remittance;
	}


	/**
	 * @return Returns the no.
	 */
	public String getNo() {
		return no;
	}


	/**
	 * @param no The no to set.
	 */
	public void setNo(String no) {
		this.no = no;
	}


	/**
	 * @return Returns the paid_collection_amount.
	 */
	public float getPaid_collection_amount() {
		return paid_collection_amount;
	}


	/**
	 * @param paid_collection_amount The paid_collection_amount to set.
	 */
	public void setPaid_collection_amount(float paid_collection_amount) {
		this.paid_collection_amount = paid_collection_amount;
	}


	/**
	 * @return Returns the station_code.
	 */
	public String getStation_code() {
		return station_code;
	}


	/**
	 * @param station_code The station_code to set.
	 */
	public void setStation_code(String station_code) {
		this.station_code = station_code;
	}


	/**
	 * @return Returns the topay_collection_amount.
	 */
	public float getTopay_collection_amount() {
		return topay_collection_amount;
	}


	/**
	 * @param topay_collection_amount The topay_collection_amount to set.
	 */
	public void setTopay_collection_amount(float topay_collection_amount) {
		this.topay_collection_amount = topay_collection_amount;
	}


	/**
	 * @return Returns the from_CR_NO.
	 */
	public String getFrom_CR_NO() {
		return from_CR_NO;
	}


	/**
	 * @param from_CR_NO The from_CR_NO to set.
	 */
	public void setFrom_CR_NO(String from_CR_NO) {
		this.from_CR_NO = from_CR_NO;
	}


	/**
	 * @return Returns the from_LR_NO.
	 */
	public String getFrom_LR_NO() {
		return from_LR_NO;
	}


	/**
	 * @param from_LR_NO The from_LR_NO to set.
	 */
	public void setFrom_LR_NO(String from_LR_NO) {
		this.from_LR_NO = from_LR_NO;
	}


	/**
	 * @return Returns the to_CR_NO.
	 */
	public String getTo_CR_NO() {
		return to_CR_NO;
	}


	/**
	 * @param to_CR_NO The to_CR_NO to set.
	 */
	public void setTo_CR_NO(String to_CR_NO) {
		this.to_CR_NO = to_CR_NO;
	}


	/**
	 * @return Returns the to_LR_NO.
	 */
	public String getTo_LR_NO() {
		return to_LR_NO;
	}


	/**
	 * @param to_LR_NO The to_LR_NO to set.
	 */
	public void setTo_LR_NO(String to_LR_NO) {
		this.to_LR_NO = to_LR_NO;
	}


	/**
	 * @return Returns the dd_reimbursement.
	 */
	public float getDd_reimbursement() {
		return dd_reimbursement;
	}


	/**
	 * @param dd_reimbursement The dd_reimbursement to set.
	 */
	public void setDd_reimbursement(float dd_reimbursement) {
		this.dd_reimbursement = dd_reimbursement;
	}


	
	/**
	 * @param status The status to set.
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}


	public boolean isStatus() {
		// TODO Auto-generated method stub
		return status;
	}
}
