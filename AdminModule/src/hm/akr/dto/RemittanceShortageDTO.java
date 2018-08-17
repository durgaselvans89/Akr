package hm.akr.dto;

import java.io.Serializable;
import java.util.Date;

public class RemittanceShortageDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fromStation = null;
	private String toStation = null;
	private String lrNo = null;
	private Date lrDate = null;
	private String lrType = null;
	private String drsNo = null;
	private Date drsDate = null;
	private String crNo = null;
	private String cnor = null;
	private String cnee = null;
	private float amount = 0;
	private Date recoveryDate = null;
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
	 * @return the lrNo
	 */
	public String getLrNo() {
		return lrNo;
	}
	/**
	 * @param lrNo the lrNo to set
	 */
	public void setLrNo(String lrNo) {
		this.lrNo = lrNo;
	}
	/**
	 * @return the lrDate
	 */
	public Date getLrDate() {
		return lrDate;
	}
	/**
	 * @param lrDate the lrDate to set
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
	 * @param lrType the lrType to set
	 */
	public void setLrType(String lrType) {
		this.lrType = lrType;
	}
	/**
	 * @return the drsNo
	 */
	public String getDrsNo() {
		return drsNo;
	}
	/**
	 * @param drsNo the drsNo to set
	 */
	public void setDrsNo(String drsNo) {
		this.drsNo = drsNo;
	}
	/**
	 * @return the drsDate
	 */
	public Date getDrsDate() {
		return drsDate;
	}
	/**
	 * @param drsDate the drsDate to set
	 */
	public void setDrsDate(Date drsDate) {
		this.drsDate = drsDate;
	}
	
	/**
	 * @return the crNo
	 */
	public String getCrNo() {
		return crNo;
	}
	/**
	 * @param crNo the crNo to set
	 */
	public void setCrNo(String crNo) {
		this.crNo = crNo;
	}
	/**
	 * @return the cnor
	 */
	public String getCnor() {
		return cnor;
	}
	/**
	 * @param cnor the cnor to set
	 */
	public void setCnor(String cnor) {
		this.cnor = cnor;
	}
	/**
	 * @return the cnee
	 */
	public String getCnee() {
		return cnee;
	}
	/**
	 * @param cnee the cnee to set
	 */
	public void setCnee(String cnee) {
		this.cnee = cnee;
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
	 * @return the recoveryDate
	 */
	public Date getRecoveryDate() {
		return recoveryDate;
	}
	/**
	 * @param recoveryDate the recoveryDate to set
	 */
	public void setRecoveryDate(Date recoveryDate) {
		this.recoveryDate = recoveryDate;
	}
	
	
}
