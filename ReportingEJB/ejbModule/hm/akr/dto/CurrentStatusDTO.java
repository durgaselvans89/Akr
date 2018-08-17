package hm.akr.dto;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * @version 1.0
 */
public class CurrentStatusDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String lr_no = null;

	private Date lrdate = null;
	
	private String lr_status = null;
	
	private String station_code = null;

	private String from = null;

	private String to = null;

	private int no_of_articles = 0;

	private float actual_weight = 0;

	private String consignorName = null;

	private String consigneeName = null;
	
	private Date outTimeDate = null;
	
	private float ddc = 0;
	
	private float total = 0;
	
	private float article_value = 0;

	/**
	 * Constructor Method
	 */
	public CurrentStatusDTO() {
		super();
	}

	/**
	 * @return the lr_no
	 */
	public String getLr_no() {
		return lr_no;
	}

	/**
	 * @param lr_no the lr_no to set
	 */
	public void setLr_no(String lr_no) {
		this.lr_no = lr_no;
	}

	/**
	 * @return the lrdate
	 */
	public Date getLrdate() {
		return lrdate;
	}

	/**
	 * @param lrdate the lrdate to set
	 */
	public void setLrdate(Date lrdate) {
		this.lrdate = lrdate;
	}

	/**
	 * @return the lr_status
	 */
	public String getLr_status() {
		return lr_status;
	}

	/**
	 * @param lr_status the lr_status to set
	 */
	public void setLr_status(String lr_status) {
		this.lr_status = lr_status;
	}

	/**
	 * @return the station_code
	 */
	public String getStation_code() {
		return station_code;
	}

	/**
	 * @param station_code the station_code to set
	 */
	public void setStation_code(String station_code) {
		this.station_code = station_code;
	}

	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}

	/**
	 * @param to the to to set
	 */
	public void setTo(String to) {
		this.to = to;
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
	 * @return the actual_weight
	 */
	public float getActual_weight() {
		return actual_weight;
	}

	/**
	 * @param actual_weight the actual_weight to set
	 */
	public void setActual_weight(float actual_weight) {
		this.actual_weight = actual_weight;
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
	 * @return the outTimeDate
	 */
	public Date getOutTimeDate() {
		return outTimeDate;
	}

	/**
	 * @param outTimeDate the outTimeDate to set
	 */
	public void setOutTimeDate(Date outTimeDate) {
		this.outTimeDate = outTimeDate;
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
	 * @return the article_value
	 */
	public float getArticle_value() {
		return article_value;
	}

	/**
	 * @param article_value the article_value to set
	 */
	public void setArticle_value(float article_value) {
		this.article_value = article_value;
	}

	
}
