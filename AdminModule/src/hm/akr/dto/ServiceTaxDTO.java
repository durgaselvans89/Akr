package hm.akr.dto;

import java.io.Serializable;

/**
 * 
 * @version 1.0 DTO class for Customer
 */

public class ServiceTaxDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	

	private String stationname = null;
	
	private String lrno = null;

	private String lrdate = null;

	private String from = null;

	private String to = null;
	
	private String cnor = null;
	
	private String cnee = null;

	private float totalfreight = 0;
	
	private float totalfreightin25 = 0;
	
	private float servicetax = 0;
	
	private float educess = 0;

	private float hreducess = 0;
	
	//private float totalservicetax = 0;
	
	

	/**
	 * Constructor method
	 */
	public ServiceTaxDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void setStationname(String stationname) {
		this.stationname = stationname;
	}
	
	/**
	 * @return Returns the address.
	 */
	public String getStationname() {
		return stationname;
	}

	/**
	 * @param address
	 *            The address to set.
	 */
	public void setLrno(String lrno) {
		this.lrno = lrno;
	}

	/**
	 * @return Returns the city.
	 */
	public String getLrno() {
		return lrno;
	}

	/**
	 * @param city
	 *            The city to set.
	 */
	public void setLrdate(String lrdate) {
		this.lrdate = lrdate;
	}
	
	
	public String getLrdate() {
		return lrdate;
	}
	/**
	 * @return Returns the mobile.
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param mobile
	 *            The mobile to set.
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * @return Returns the name.
	 */
	public String getTo() {
		return to;
	}

	/**
	 * @param name
	 *            The name to set.
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * @return Returns the phone.
	 */
	public String getCnor() {
		return cnor;
	}

	/**
	 * @param phone
	 *            The phone to set.
	 */
	public void setCnor(String cnor) {	
		this.cnor = cnor;
	}

	/**
	 * @return Returns the pincode.
	 */
	public String getCnee() {
		return cnee;
	}

	/**
	 * @param pincode
	 *            The pincode to set.
	 */
	public void setCnee(String cnee) {
		this.cnee = cnee;
	}

	public Float getTotalfreight() {
		return totalfreight;
	}

	
	public void setTotalfreight(Float totalfreight) {
		this.totalfreight = totalfreight;
	}

	public Float getTotalfreightin25() {
		return totalfreightin25;
	}

	
	public void setTotalfreightin25(Float totalfreightin25) {
		this.totalfreightin25 = totalfreightin25;
	}
	
	public Float getServicetax() {
		return servicetax;
	}

	
	public void setServicetax(Float servicetax) {
		this.servicetax = servicetax;
	}
	
	public Float getEducess() {
		return educess;
	}

	
	public void setEducess(Float educess) {
		this.educess = educess;
	}
	
	public Float getHreducess() {
		return hreducess;
	}

	
	public void setHreducess(Float hreducess) {
		this.hreducess = hreducess;
	}

	/*public Float getTotalervicetax() {
		return totalservicetax;
	}

	
	public void setTotalServicetax(Float totalservicetax) {
		this.totalservicetax = totalservicetax;
	}*/

	
}
