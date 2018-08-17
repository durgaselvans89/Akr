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

	private Date date = null;
	
	private String activity = null;
	
	private String location = null;
	
	private int no_of_articles  = 0;
	
	private float actual_weight = 0;
	
	private String from_location = null;
	
	private String to_location = null;
	
	private String cancel_crno = null;
	
	private String phone_no = null;
	
	/**
	 * Constructor Method
	 */
	public LRTrackDTO(){
		super();
	}


	/**
	 * @return the activity
	 */
	public String getActivity() {
		return activity;
	}


	/**
	 * @param activity the activity to set
	 */
	public void setActivity(String activity) {
		this.activity = activity;
	}


	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}


	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
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
	 * @return the from_location
	 */
	public String getFrom_location() {
		return from_location;
	}




	/**
	 * @param from_location the from_location to set
	 */
	public void setFrom_location(String from_location) {
		this.from_location = from_location;
	}




	/**
	 * @return the to_location
	 */
	public String getTo_location() {
		return to_location;
	}




	/**
	 * @param to_location the to_location to set
	 */
	public void setTo_location(String to_location) {
		this.to_location = to_location;
	}


	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}


	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}


	/**
	 * @return the cancel_crno
	 */
	public String getCancel_crno() {
		return cancel_crno;
	}


	/**
	 * @param cancel_crno the cancel_crno to set
	 */
	public void setCancel_crno(String cancel_crno) {
		this.cancel_crno = cancel_crno;
	}


	/**
	 * @return the phone_no
	 */
	public String getPhone_no() {
		return phone_no;
	}


	/**
	 * @param phone_no the phone_no to set
	 */
	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}
	
	
	
}
