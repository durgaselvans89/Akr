package hm.akr.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO class for Cartage Collection commission
 *  
 * @author Hakuna Matata
 * @version 1.0
 */
public class CartageCommissionDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//LR Number
	private String lrno;	
	
	//Booking Date
	private Date bookingDate;
	
	//Basic Freight
	private float bft;	
	
	//CC Charges
	private float ccCharges;
	
	// CC Commission	
	private float commissionAmount;
	
	//Lr Type
	private String lrType;
	
	// CC % 
	private float ccPercentage;

	/**
	 * @return the lrno
	 */
	public String getLrno() {
		return lrno;
	}

	/**
	 * @param lrno the lrno to set
	 */
	public void setLrno(String lrno) {
		this.lrno = lrno;
	}

	

	/**
	 * @return the bookingDate
	 */
	public Date getBookingDate() {
		return bookingDate;
	}

	/**
	 * @param bookingDate the bookingDate to set
	 */
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	/**
	 * @return the bft
	 */
	public float getBft() {
		return bft;
	}

	/**
	 * @param bft the bft to set
	 */
	public void setBft(float bft) {
		this.bft = bft;
	}

	/**
	 * @return the ccCharges
	 */
	public float getCcCharges() {
		return ccCharges;
	}

	/**
	 * @param ccCharges the ccCharges to set
	 */
	public void setCcCharges(float ccCharges) {
		this.ccCharges = ccCharges;
	}
	

	/**
	 * @return the commissionAmount
	 */
	public float getCommissionAmount() {
		return commissionAmount;
	}

	/**
	 * @param commissionAmount the commissionAmount to set
	 */
	public void setCommissionAmount(float commissionAmount) {
		this.commissionAmount = commissionAmount;
	}
	
	/**
	 * @return the lrType
	 */
	public String getLrType() {
		return lrType;
	}

	/**
	 * @param lrType the lrType to set
	 */
	public void setLrType(String lrType) {
		this.lrType = lrType;
	}

	/**
	 * @return the ccPercentage
	 */
	public float getCcPercentage() {
		return ccPercentage;
	}

	/**
	 * @param ccPercentage the ccPercentage to set
	 */
	public void setCcPercentage(float ccPercentage) {
		this.ccPercentage = ccPercentage;
	}
	
}
