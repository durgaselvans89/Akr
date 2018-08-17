/**
 * 
 * @version 1.0
 */
package hm.akr.dto;

import java.io.Serializable;
import java.util.Date;



public class GMRInTimeDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String lr_no = null;

	private String vehicle_no = null;
	
	private String from = null;

	private String to = null;

	private String consignor_name = null;

	private String consignee_name = null;

	private Date date = null;
	
	private Date sent_date = null;
	
	private Date today_date = null;
	
	private float total = 0;
	
	private String lr_type = null;
	
	private float ddc = 0;

	private int no_of_articles = 0;

	private float actual_weight;
	
	private String article_value = null;
	
	private String article_type = null;
	
	private String inward_days = null;
	
	private String sent_days= null;	
	
	private String cnorPhone = "";
	
	private String cneePhone = "";
	
	private int rate_type = 0;
	
	private int smsNotify = 0;
	
	/**
	 * @return the cnorPhone
	 */
	public String getCnorPhone() {
		return cnorPhone;
	}


	/**
	 * @param cnorPhone the cnorPhone to set
	 */
	public void setCnorPhone(String cnorPhone) {
		this.cnorPhone = cnorPhone;
	}


	/**
	 * @return the cneePhone
	 */
	public String getCneePhone() {
		return cneePhone;
	}


	/**
	 * @param cneePhone the cneePhone to set
	 */
	public void setCneePhone(String cneePhone) {
		this.cneePhone = cneePhone;
	}


	/**
	 * Constructor Method
	 */
	public GMRInTimeDTO() {
		super();
		
	}


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
	 * @return Returns the consignee_address.
	 */
	public String getConsigneeName() {
		return consignee_name;
	}


	/**
	 * @param consignee_address The consignee_address to set.
	 */
	public void setConsigneeName(String consignee_address) {
		this.consignee_name = consignee_address;
	}

	/**
	 * @return Returns the consignor_address.
	 */
	public String getConsignorName() {
		return consignor_name;
	}


	/**
	 * @param consignor_address The consignor_address to set.
	 */
	public void setConsignorName(String consignor_address) {
		this.consignor_name = consignor_address;
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
	 * @return Returns the lr_no.
	 */
	public String getLr_no() {
		return lr_no;
	}


	/**
	 * @param lr_no The lr_no to set.
	 */
	public void setLr_no(String lr_no) {
		this.lr_no = lr_no;
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
	 * @return Returns the to.
	 */
	public String getTo() {
		return to;
	}


	/**
	 * @param to The to to set.
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * @return Returns the vehicle_no.
	 */
	public String getVehicle_no() {
		return vehicle_no;
	}


	/**
	 * @param vehicle_no The vehicle_no to set.
	 */
	public void setVehicle_no(String vehicle_no) {
		this.vehicle_no = vehicle_no;
	}


	/**
	 * @return the consignor_name
	 */
	public String getConsignor_name() {
		return consignor_name;
	}


	/**
	 * @param consignor_name the consignor_name to set
	 */
	public void setConsignor_name(String consignor_name) {
		this.consignor_name = consignor_name;
	}


	/**
	 * @return the consignee_name
	 */
	public String getConsignee_name() {
		return consignee_name;
	}


	/**
	 * @param consignee_name the consignee_name to set
	 */
	public void setConsignee_name(String consignee_name) {
		this.consignee_name = consignee_name;
	}


	/**
	 * @return the sent_date
	 */
	public Date getSent_date() {
		return sent_date;
	}


	/**
	 * @param sent_date the sent_date to set
	 */
	public void setSent_date(Date sent_date) {
		this.sent_date = sent_date;
	}


	/**
	 * @return the today_date
	 */
	public Date getToday_date() {
		return today_date;
	}


	/**
	 * @param today_date the today_date to set
	 */
	public void setToday_date(Date today_date) {
		this.today_date = today_date;
	}


	/**
	 * @return the total
	 */
	public float getTotal() {
		return total;
	}


	/**
	 * @param total the total to set
	 */
	public void setTotal(float total) {
		this.total = total;
	}


	/**
	 * @return the lr_type
	 */
	public String getLr_type() {
		return lr_type;
	}


	/**
	 * @param lr_type the lr_type to set
	 */
	public void setLr_type(String lr_type) {
		this.lr_type = lr_type;
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


	/**
	 * @return the article_value
	 */
	public String getArticle_value() {
		return article_value;
	}


	/**
	 * @param article_value the article_value to set
	 */
	public void setArticle_value(String article_value) {
		this.article_value = article_value;
	}


	/**
	 * @return the article_type
	 */
	public String getArticle_type() {
		return article_type;
	}


	/**
	 * @param article_type the article_type to set
	 */
	public void setArticle_type(String article_type) {
		this.article_type = article_type;
	}


	/**
	 * @return the inward_days
	 */
	public String getInward_days() {
		return inward_days;
	}


	/**
	 * @param inward_days the inward_days to set
	 */
	public void setInward_days(String inward_days) {
		this.inward_days = inward_days;
	}


	/**
	 * @return the sent_days
	 */
	public String getSent_days() {
		return sent_days;
	}


	/**
	 * @param sent_days the sent_days to set
	 */
	public void setSent_days(String sent_days) {
		this.sent_days = sent_days;
	}


	/**
	 * @return the rate_type
	 */
	public int getRate_type() {
		return rate_type;
	}


	/**
	 * @param rate_type the rate_type to set
	 */
	public void setRate_type(int rate_type) {
		this.rate_type = rate_type;
	}


	/**
	 * @return the smsNotify
	 */
	public int getSmsNotify() {
		return smsNotify;
	}


	/**
	 * @param smsNotify the smsNotify to set
	 */
	public void setSmsNotify(int smsNotify) {
		this.smsNotify = smsNotify;
	}


	
	
}


