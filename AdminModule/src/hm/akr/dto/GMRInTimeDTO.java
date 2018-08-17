/**
 * 
 * @version 1.0
 */
package hm.akr.dto;

import java.io.Serializable;
import java.sql.Date;


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

	private int no_of_articles = 0;

	private float actual_weight;
	
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


	
	
}


