package hm.akr.dto;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * 
 * @version 1.0
 */
public class GMROutTimeDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String lr_no = null;

	private Date lrdate = null;

	private String lr_type = null;

	private String article_type = null;

	private String from = null;

	private String to = null;

	private int no_of_articles = 0;

	private float actual_weight = 0;

	private String consignorName = null;

	private String consigneeName = null;

	private Date outTimeDate = null;

	private Timestamp last_inwarded_date = null;

	private float ddc = 0;

	private float total = 0;

	private float article_value = 0;

	/**
	 * Constructor Method
	 */
	public GMROutTimeDTO() {
		super();
	}

	/**
	 * @return Returns the actual_weight.
	 */
	public float getActual_weight() {
		return actual_weight;
	}

	/**
	 * @param actual_weight
	 *            The actual_weight to set.
	 */
	public void setActual_weight(float actual_weight) {
		this.actual_weight = actual_weight;
	}

	/**
	 * @return Returns the consignee_address.
	 */
	public String getConsigneeName() {
		return consigneeName;
	}

	/**
	 * @param consignee_address
	 *            The consignee_address to set.
	 */
	public void setConsigneeName(String consignee_address) {
		this.consigneeName = consignee_address;
	}

	/**
	 * @return Returns the consignor_address.
	 */
	public String getConsignorName() {
		return consignorName;
	}

	/**
	 * @param consignor_address
	 *            The consignor_address to set.
	 */
	public void setConsignorName(String consignor_address) {
		this.consignorName = consignor_address;
	}

	/**
	 * @return Returns the date.
	 */
	public Date getLrDate() {
		return lrdate;
	}

	/**
	 * @param date
	 *            The date to set.
	 */
	public void setLrDate(Date date) {
		this.lrdate = date;
	}

	/**
	 * @return Returns the from.
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from
	 *            The from to set.
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
	 * @param lr_no
	 *            The lr_no to set.
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
	 * @param no_of_articles
	 *            The no_of_articles to set.
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
	 * @param to
	 *            The to to set.
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * @return Returns the outTimeDate.
	 */
	public Date getOutTimeDate() {
		return outTimeDate;
	}

	/**
	 * @param outTimeDate
	 *            The outTimeDate to set.
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
	 * @param ddc
	 *            the ddc to set
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
	 * @param total
	 *            the total to set
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
	 * @param article_value
	 *            the article_value to set
	 */
	public void setArticle_value(float article_value) {
		this.article_value = article_value;
	}

	/**
	 * @return the lr_type
	 */
	public String getLr_type() {
		return lr_type;
	}

	/**
	 * @param lr_type
	 *            the lr_type to set
	 */
	public void setLr_type(String lr_type) {
		this.lr_type = lr_type;
	}

	/**
	 * @return the article_type
	 */
	public String getArticle_type() {
		return article_type;
	}

	/**
	 * @param article_type
	 *            the article_type to set
	 */
	public void setArticle_type(String article_type) {
		this.article_type = article_type;
	}

	public Timestamp getLast_inwarded_date() {
		return last_inwarded_date;
	}

	public void setLast_inwarded_date(Timestamp last_inwarded_date) {
		this.last_inwarded_date = last_inwarded_date;
	}

}
