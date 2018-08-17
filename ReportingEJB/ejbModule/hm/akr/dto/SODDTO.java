package hm.akr.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @version 1.0
 */
public class SODDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String sno = null;
	
	private String lrno = null;
	
	private Date lrDate = null;
	
	private String from = null;
	
	private int noofarticles = 0;
	
	private float weight = 0;
	
	private String lrtype = null;
	
	private float totalAmount = 0;
	
	private float dd = 0;
	
	private float ddcFree = 0;
	
	private float demurrage = 0;
	
	private float underCharge = 0;
	
	private float postage = 0;
	
	private float ddExtra = 0;
	
	private float others = 0;
	
	private String crno = null;
	
	private float crAmt = 0;
	
	private Date delivered_date = null;
	
	private Date inward_date = null;
	
	private boolean status = false;
	
	private int isDRSConfirmed = 0;
	
	private int cr_status = 0;
	
	private String oldLrno = null;
	
	private String isUpd = null;
	

	/**
	 * @return the booking_percent
	 * 
	 * 
	 */
	
	public String getIsUPd()	{
		return isUpd;
	}
	
	public void setIsUpd(String isUpd){
		this.isUpd = isUpd;
	}
	
	public String getOldLrno() {
		return oldLrno;
	}
	
	public void setOldLrno(String oldLrno){
		this.oldLrno = oldLrno;
	}
	
	/**
	 * @return Returns the delivered_date.
	 */
	public Date getDelivered_date() {
		return delivered_date;
	}
	/**
	 * @param delivered_date The delivered_date to set.
	 */
	public void setDelivered_date(Date delivered_date) {
		this.delivered_date = delivered_date;
	}
	/**
	 * @return Returns the crAmt.
	 */
	public float getCrAmt() {
		return crAmt;
	}
	/**
	 * @param crAmt The crAmt to set.
	 */
	public void setCrAmt(float crAmt) {
		this.crAmt = crAmt;
	}
	/**
	 * @return Returns the crno.
	 */
	public String getCrno() {
		return crno;
	}
	/**
	 * @param crno The crno to set.
	 */
	public void setCrno(String crno) {
		this.crno = crno;
	}
	/**
	 * @return Returns the dd.
	 */
	public float getDd() {
		return dd;
	}
	/**
	 * @param dd The dd to set.
	 */
	public void setDd(float dd) {
		this.dd = dd;
	}
	/**
	 * @return Returns the ddExtra.
	 */
	public float getDdExtra() {
		return ddExtra;
	}
	/**
	 * @param ddExtra The ddExtra to set.
	 */
	public void setDdExtra(float ddExtra) {
		this.ddExtra = ddExtra;
	}
	/**
	 * @return Returns the demurrage.
	 */
	public float getDemurrage() {
		return demurrage;
	}
	/**
	 * @param demurrage The demurrage to set.
	 */
	public void setDemurrage(float demurrage) {
		this.demurrage = demurrage;
	}
	
	/**
	 * @return Returns the status.
	 */
	public boolean getStatus() {
		return status;
	}

	/**
	 * @param status The status to set.
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	
	/**
	 * @return Returns the from.
	 */
	public String getFrom() {
		return from;
	}
	/**
	 * @param from The from to set.
	 */
	public void setFrom(String from) {
		this.from = from;
	}
	/**
	 * @return Returns the lrDate.
	 */
	public Date getLrDate() {
		return lrDate;
	}
	/**
	 * @param lrDate The lrDate to set.
	 */
	public void setLrDate(Date lrDate) {
		this.lrDate = lrDate;
	}
	/**
	 * @return Returns the lrno.
	 */
	public String getLrno() {
		return lrno;
	}
	/**
	 * @param lrno The lrno to set.
	 */
	public void setLrno(String lrno) {
		this.lrno = lrno;
	}
	/**
	 * @return Returns the lrtype.
	 */
	public String getLrtype() {
		return lrtype;
	}
	/**
	 * @param lrtype The lrtype to set.
	 */
	public void setLrtype(String lrtype) {
		this.lrtype = lrtype;
	}
	/**
	 * @return Returns the noofarticles.
	 */
	public int getNoofarticles() {
		return noofarticles;
	}
	/**
	 * @param noofarticles The noofarticles to set.
	 */
	public void setNoofarticles(int noofarticles) {
		this.noofarticles = noofarticles;
	}
	/**
	 * @return Returns the others.
	 */
	public float getOthers() {
		return others;
	}
	/**
	 * @param others The others to set.
	 */
	public void setOthers(float others) {
		this.others = others;
	}
	/**
	 * @return Returns the postage.
	 */
	public float getPostage() {
		return postage;
	}
	/**
	 * @param postage The postage to set.
	 */
	public void setPostage(float postage) {
		this.postage = postage;
	}
	/**
	 * @return Returns the sno.
	 */
	public String getSno() {
		return sno;
	}
	/**
	 * @param sno The sno to set.
	 */
	public void setSno(String sno) {
		this.sno = sno;
	}
	/**
	 * @return Returns the totalAmount.
	 */
	public float getTotalAmount() {
		return totalAmount;
	}
	/**
	 * @param totalAmount The totalAmount to set.
	 */
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}
	/**
	 * @return Returns the underCharge.
	 */
	public float getUnderCharge() {
		return underCharge;
	}
	/**
	 * @param underCharge The underCharge to set.
	 */
	public void setUnderCharge(float underCharge) {
		this.underCharge = underCharge;
	}
	/**
	 * @return Returns the weight.
	 */
	public float getWeight() {
		return weight;
	}
	/**
	 * @param weight The weight to set.
	 */
	public void setWeight(float weight) {
		this.weight = weight;
	}
	/**
	 * @return Returns the inward_date.
	 */
	public Date getInward_date() {
		return inward_date;
	}
	/**
	 * @param inward_date The inward_date to set.
	 */
	public void setInward_date(Date inward_date) {
		this.inward_date = inward_date;
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
