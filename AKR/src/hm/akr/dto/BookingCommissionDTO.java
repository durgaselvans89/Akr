package hm.akr.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO class for delivery commission details.
 * 
 * @author Hakuna Matata
 * @version 1.0
 */
public class BookingCommissionDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//LR Number
	private String lrno;
	
	//LR Type
	private String lrType;
	
	//From Station
	private String fromStation;
	
	//To Station
	private String toStation;
	
	//Charged Weight
	private float chargedWeight;
	
	//Basic Freight
	private float bft;
	
	//Actual Bft
	private float actualBft;
	
	//Profile Name
	private String profile_name = null;
	
	//Rate Type
	private String rateType;
	
	//Booking Date
	private Date bookingDate;
	
	
	//Total LR Amount
	private float totalAmount;
	
	//Booking commission
	private float bookingCommission;
	
	//CC Commission
	private float ccCommission;
	
	//%
	private float bookingCommissionPercent;
	

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
	 * @return the fromStation
	 */
	public String getFromStation() {
		return fromStation;
	}

	/**
	 * @param fromStation the fromStation to set
	 */
	public void setFromStation(String fromStation) {
		this.fromStation = fromStation;
	}

	/**
	 * @return the toStation
	 */
	public String getToStation() {
		return toStation;
	}

	/**
	 * @param toStation the toStation to set
	 */
	public void setToStation(String toStation) {
		this.toStation = toStation;
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
	 * @return the totalAmount
	 */
	public float getTotalAmount() {
		return totalAmount;
	}

	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

	/**
	 * @return the commissionAmount
	 */
	public float getBookingCommission() {
		return bookingCommission;
	}

	/**
	 * @param commissionAmount the commissionAmount to set
	 */
	public void setBookingCommission(float commissionAmount) {
		this.bookingCommission = commissionAmount;
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
	 * @return the chargedWeight
	 */
	public float getChargedWeight() {
		return chargedWeight;
	}

	/**
	 * @param chargedWeight the chargedWeight to set
	 */
	public void setChargedWeight(float chargedWeight) {
		this.chargedWeight = chargedWeight;
	}

	
	/**
	 * @return the actualBft
	 */
	public float getActualBft() {
		return actualBft;
	}

	/**
	 * @param actualBft the actualBft to set
	 */
	public void setActualBft(float actualBft) {
		this.actualBft = actualBft;
	}


	/**
	 * @return the profile_name
	 */
	public String getProfile_name() {
		return profile_name;
	}

	/**
	 * @param profile_name the profile_name to set
	 */
	public void setProfile_name(String profile_name) {
		this.profile_name = profile_name;
	}

	/**
	 * @return the rateType
	 */
	public String getRateType() {
		return rateType;
	}

	/**
	 * @param rateType the rateType to set
	 */
	public void setRateType(String rateType) {
		this.rateType = rateType;
	}

	/**
	 * @return the ccCommission
	 */
	public float getCcCommission() {
		return ccCommission;
	}

	/**
	 * @param ccCommission the ccCommission to set
	 */
	public void setCcCommission(float ccCommission) {
		this.ccCommission = ccCommission;
	}

	/**
	 * @return the bookingCommissionPercent
	 */
	public float getBookingCommissionPercent() {
		return bookingCommissionPercent;
	}

	/**
	 * @param bookingCommissionPercent the bookingCommissionPercent to set
	 */
	public void setBookingCommissionPercent(float bookingCommissionPercent) {
		this.bookingCommissionPercent = bookingCommissionPercent;
	}
	
}
