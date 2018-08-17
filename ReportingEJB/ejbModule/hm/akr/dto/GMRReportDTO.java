package hm.akr.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;


public class GMRReportDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	
	private String lrNo = null;
	
	private Date bookingDate = null;
	
	private String from_station = null;
	
	private String to_station = null;
	
	private String consignor_name = null;
	
	private String consignee_name = null;
	
	private float weight  = 0;
	
	private int no_of_articles  = 0;
	
	private String vehicleNo = null;
	
	private String moved_to = null;
	
	private Timestamp vehicleDate = null;
	
	private String driverName = null;
	
	private float bft  = 0;
	
	private int article_id  = 0;
	
	
	/**
	 * 
	 * @return
	 */
	public String getLrNo() {
		return lrNo;
	}
	/**
	 * 
	 * @param lrNo
	 */
	public void setLrNo(String lrNo) {
		this.lrNo = lrNo;
	}
	/**
	 * @return Returns the bookingDate.
	 */
	public Date getBookingDate() {
		return bookingDate;
	}
	/**
	 * @param bookingDate The bookingDate to set.
	 */
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	/**
	 * @return Returns the consignee_name.
	 */
	public String getConsignee_name() {
		return consignee_name;
	}
	/**
	 * @param consignee_name The consignee_name to set.
	 */
	public void setConsignee_name(String consignee_name) {
		this.consignee_name = consignee_name;
	}
	/**
	 * @return Returns the consignor_name.
	 */
	public String getConsignor_name() {
		return consignor_name;
	}
	/**
	 * @param consignor_name The consignor_name to set.
	 */
	public void setConsignor_name(String consignor_name) {
		this.consignor_name = consignor_name;
	}
	/**
	 * @return Returns the from_station.
	 */
	public String getFrom_station() {
		return from_station;
	}
	/**
	 * @param from_station The from_station to set.
	 */
	public void setFrom_station(String from_station) {
		this.from_station = from_station;
	}
	
	/**
	 * @return Returns the to_station.
	 */
	public String getTo_station() {
		return to_station;
	}
	/**
	 * @param to_station The to_station to set.
	 */
	public void setTo_station(String to_station) {
		this.to_station = to_station;
	}
	/**
	 * @return Returns the vehicleNo.
	 */
	public String getVehicleNo() {
		return vehicleNo;
	}
	/**
	 * @param vehicleNo The vehicleNo to set.
	 */
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	
	/**
	 * @return Returns the moved_to.
	 */
	public String getMoved_to() {
		return moved_to;
	}
	/**
	 * @param moved_to The moved_to to set.
	 */
	public void setMoved_to(String moved_to) {
		this.moved_to = moved_to;
	}
	/**
	 * @return Returns the no_of_articles.
	 */
	public int getNo_of_articles() {
		return no_of_articles;
	}
	/**
	 * @param no_of_articles The no_of_articles to set.
	 */
	public void setNo_of_articles(int no_of_articles) {
		this.no_of_articles = no_of_articles;
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
	 * @return the vehicleDate
	 */
	public Timestamp getVehicleDate() {
		return vehicleDate;
	}
	/**
	 * @param vehicleDate the vehicleDate to set
	 */
	public void setVehicleDate(Timestamp vehicleDate) {
		this.vehicleDate = vehicleDate;
	}
	/**
	 * @return the driverName
	 */
	public String getDriverName() {
		return driverName;
	}
	/**
	 * @param driverName the driverName to set
	 */
	public void setDriverName(String driverName) {
		this.driverName = driverName;
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
	 * @return the article_id
	 */
	public int getArticle_id() {
		return article_id;
	}
	/**
	 * @param article_id the article_id to set
	 */
	public void setArticle_id(int article_id) {
		this.article_id = article_id;
	}
	
	
	
	
}
