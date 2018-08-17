package hm.akr.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO class to hold the Door Delivery Reimbursement data
 * 
 * @version 1.0
 */
public class DDRDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String lrNo = null;
	private Date lrDate = null;
	private String fromStation = null;
	private int noOfArticles = 0;
	private float actualWeight = 0;
	private float basicFreight = 0;
	private float ddc = 0;

	private float ddcFree = 0;
	private float ddExtra = 0;
	private float others = 0;
	private String crno = null;
	private Date crDate = null;
	private String lrType = null;
	private float total = 0;
	private int isDRSConfirmed = 0;

	private int cr_status = 0;
	
	/**
	 * @return Returns the actualWeight.
	 */
	public float getActualWeight() {
		return actualWeight;
	}

	/**
	 * @param actualWeight
	 *            The actualWeight to set.
	 */
	public void setActualWeight(float actualWeight) {
		this.actualWeight = actualWeight;
	}

	/**
	 * @return Returns the basicFreight.
	 */
	public float getBasicFreight() {
		return basicFreight;
	}

	/**
	 * @param basicFreight
	 *            The basicFreight to set.
	 */
	public void setBasicFreight(float basicFreight) {
		this.basicFreight = basicFreight;
	}

	/**
	 * @return Returns the crDate.
	 */
	public Date getCrDate() {
		return crDate;
	}

	/**
	 * @param crDate
	 *            The crDate to set.
	 */
	public void setCrDate(Date crDate) {
		this.crDate = crDate;
	}

	/**
	 * @return Returns the crno.
	 */
	public String getCrno() {
		return crno;
	}

	/**
	 * @param crno
	 *            The crno to set.
	 */
	public void setCrno(String crno) {
		this.crno = crno;
	}

	/**
	 * @return Returns the ddc.
	 */
	public float getDdc() {
		return ddc;
	}

	/**
	 * @param ddc
	 *            The ddc to set.
	 */
	public void setDdc(float ddc) {
		this.ddc = ddc;
	}

	/**
	 * @return Returns the ddExtra.
	 */
	public float getDdExtra() {
		return ddExtra;
	}

	/**
	 * @param ddExtra
	 *            The ddExtra to set.
	 */
	public void setDdExtra(float ddExtra) {
		this.ddExtra = ddExtra;
	}

	/**
	 * @return Returns the fromStation.
	 */
	public String getFromStation() {
		return fromStation;
	}

	/**
	 * @param fromStation
	 *            The fromStation to set.
	 */
	public void setFromStation(String fromStation) {
		this.fromStation = fromStation;
	}

	/**
	 * @return Returns the lrDate.
	 */
	public Date getLrDate() {
		return lrDate;
	}

	/**
	 * @param lrDate
	 *            The lrDate to set.
	 */
	public void setLrDate(Date lrDate) {
		this.lrDate = lrDate;
	}

	/**
	 * @return Returns the lrNo.
	 */
	public String getLrNo() {
		return lrNo;
	}

	/**
	 * @param lrNo
	 *            The lrNo to set.
	 */
	public void setLrNo(String lrNo) {
		this.lrNo = lrNo;
	}

	/**
	 * @return Returns the lrType.
	 */
	public String getLrType() {
		return lrType;
	}

	/**
	 * @param lrType
	 *            The lrType to set.
	 */
	public void setLrType(String lrType) {
		this.lrType = lrType;
	}

	/**
	 * @return Returns the noOfArticles.
	 */
	public int getNoOfArticles() {
		return noOfArticles;
	}

	/**
	 * @param noOfArticles
	 *            The noOfArticles to set.
	 */
	public void setNoOfArticles(int noOfArticles) {
		this.noOfArticles = noOfArticles;
	}

	/**
	 * @return Returns the others.
	 */
	public float getOthers() {
		return others;
	}

	/**
	 * @param others
	 *            The others to set.
	 */
	public void setOthers(float others) {
		this.others = others;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public float getDdcFree() {
		return ddcFree;
	}

	public void setDdcFree(float ddcFree) {
		this.ddcFree = ddcFree;
	}

	/**
	 * @return the isDRSConfirmed
	 */
	public int getIsDRSConfirmed() {
		return isDRSConfirmed;
	}

	/**
	 * @param isDRSConfirmed the isDRSConfirmed to set
	 */
	public void setIsDRSConfirmed(int isDRSConfirmed) {
		this.isDRSConfirmed = isDRSConfirmed;
	}

	/**
	 * @return the cr_status
	 */
	public int getCr_status() {
		return cr_status;
	}

	/**
	 * @param cr_status the cr_status to set
	 */
	public void setCr_status(int cr_status) {
		this.cr_status = cr_status;
	}

}
