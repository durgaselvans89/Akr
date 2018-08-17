package hm.akr.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Statement Of Door Delivery ReImbursement DTO
 * @version 1.0
 **/
public class SODDRDTO implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String lrNo;

	private Date lrDate;
	
	private String fromStation;
	
	private int noOfArticles;
	
	private float actual_weight;
	
	private float totalFrieght;
	
	private float doorDelivery;
	
	private float ddExtra;
	
	private float others;
	
	private String crno;
	
	private Date crDate;
	
	private String lrType;

	/**
	 * @return Returns the actual_weight.
	 */
	public float getActual_weight() {
		return actual_weight;
	}

	/**
	 * @param actual_weight The actual_weight to set.
	 */
	public void setActual_weight(float actual_weight) {
		this.actual_weight = actual_weight;
	}

	/**
	 * @return Returns the crDate.
	 */
	public Date getCrDate() {
		return crDate;
	}

	/**
	 * @param crDate The crDate to set.
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
	 * @param crno The crno to set.
	 */
	public void setCrno(String crno) {
		this.crno = crno;
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
	 * @return Returns the doorDelivery.
	 */
	public float getDoorDelivery() {
		return doorDelivery;
	}

	/**
	 * @param doorDelivery The doorDelivery to set.
	 */
	public void setDoorDelivery(float doorDelivery) {
		this.doorDelivery = doorDelivery;
	}

	/**
	 * @return Returns the fromStation.
	 */
	public String getFromStation() {
		return fromStation;
	}

	/**
	 * @param fromStation The fromStation to set.
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
	 * @param lrDate The lrDate to set.
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
	 * @param lrNo The lrNo to set.
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
	 * @param lrType The lrType to set.
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
	 * @param noOfArticles The noOfArticles to set.
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
	 * @param others The others to set.
	 */
	public void setOthers(float others) {
		this.others = others;
	}

	/**
	 * @return Returns the totalFrieght.
	 */
	public float getTotalFrieght() {
		return totalFrieght;
	}

	/**
	 * @param totalFrieght The totalFrieght to set.
	 */
	public void setTotalFrieght(float totalFrieght) {
		this.totalFrieght = totalFrieght;
	}
	

}
