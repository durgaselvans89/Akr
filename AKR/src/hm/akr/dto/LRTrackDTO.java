package hm.akr.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @version 1.0
 */

public class LRTrackDTO implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private String lrNo = null;
	
	private String lrType = null;
	
	private float bft = 0;
	
	private String fromLocation = null;
	
	private String toLocation = null;
	
	private int no_of_articles  = 0;
	
	private Date lrDate = null;
	
	private Date cancelDate = null;
	
	private float weight = 0;
	
	private String cancelledOption = null;
	
	private ActivityLog[] activities = null;
	
	private String consignorName = null;

	private String consigneeName = null;
	
	private float ddc = 0;
	
	
	private float oldLrTotal = 0;
	
	private float post = 0;
	
	private float demu = 0;
	
	private String oldLrno = null;
	
	private String isUpd = null;
	
	private int lr_status = 0;
	
	private String desc = null;
	
	private String to_station = null;
	
	private String createdon = null;
	
	private int rate_type =0;
	
	private String lr_date = null;

	public String getLr_date() {
		return lr_date;
	}

	public void setLr_date(String lr_date) {
		this.lr_date = lr_date;
	}

	

	public int getRate_type() {
		return rate_type;
	}

	public void setRate_type(int rate_type) {
		this.rate_type = rate_type;
	}

	public int getLr_status() {
		return lr_status;
	}

	public void setLr_status(int lr_status) {
		this.lr_status = lr_status;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getTo_station() {
		
		return to_station;
	}

	public void setTo_station(String to_station) {
		this.to_station = to_station;
	}

	public String getCreatedon() {
		return createdon;
	}

	public void setCreatedon(String createdon) {
		this.createdon = createdon;
	}

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

	/**
	 * @return the rate_type
	 */
	
	
	public float getOldLrTotal() {
		return oldLrTotal;
	}
	
	public void setOldLrTotal(Float oldLrTotal){
		this.oldLrTotal = oldLrTotal;
	}
	
	
	public float getPost() {
		return post;
	}
	
	public void setPost(Float post){
		this.post = post;
	}
	
	public float getDemu() {
		return demu;
	}
	
	public void setDemu(Float demu){
		this.demu = demu;
	}
	
	public String getOldLrno() {
		return oldLrno;
	}
	
	public void setOldLrno(String oldLrno){
		this.oldLrno = oldLrno;
	}

	
	
	/**
	 * Constructor Method
	 */
	public LRTrackDTO(){
		super();
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
	 * @return the fromLocation
	 */
	public String getFromLocation() {
		return fromLocation;
	}





	/**
	 * @param fromLocation the fromLocation to set
	 */
	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}





	/**
	 * @return the toLocation
	 */
	public String getToLocation() {
		return toLocation;
	}





	/**
	 * @param toLocation the toLocation to set
	 */
	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}





	/**
	 * @return the no_of_articles
	 */
	public int getNo_of_articles() {
		return no_of_articles;
	}





	/**
	 * @param no_of_articles the no_of_articles to set
	 */
	public void setNo_of_articles(int no_of_articles) {
		this.no_of_articles = no_of_articles;
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
	 * @return the activities
	 */
	public ActivityLog[] getActivities() {
		return activities;
	}


	/**
	 * @param activities the activities to set
	 */
	public void setActivities(ActivityLog[] activities) {
		this.activities = activities;
	}





	/**
	 * @return the weight
	 */
	public float getWeight() {
		return weight;
	}





	/**
	 * @param weight the weight to set
	 */
	public void setWeight(float weight) {
		this.weight = weight;
	}





	public String getCancelledOption() {
		return cancelledOption;
	}





	public void setCancelledOption(String cancelledOption) {
		this.cancelledOption = cancelledOption;
	}





	public Date getCancelDate() {
		return cancelDate;
	}





	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}





	/**
	 * @return the consignorName
	 */
	public String getConsignorName() {
		return consignorName;
	}





	/**
	 * @param consignorName the consignorName to set
	 */
	public void setConsignorName(String consignorName) {
		this.consignorName = consignorName;
	}





	/**
	 * @return the consigneeName
	 */
	public String getConsigneeName() {
		return consigneeName;
	}





	/**
	 * @param consigneeName the consigneeName to set
	 */
	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}





	/**
	 * @return the ddc
	 */
	public float getDdc() {
		return ddc;
	}





	/**
	 * @param ddc the ddc to set
	 */
	public void setDdc(float ddc) {
		this.ddc = ddc;
	}
	
}
