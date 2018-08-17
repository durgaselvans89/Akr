package hm.akr.dto;


import java.io.Serializable;

/**
 * @author
 * @version 1.0
 * @copyright (c) AKR Parcel Service
 */

/**
 * DTO class for Distance List 
 */
public class DiscounterDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;	
	
	private String fromStation = "";
	
	private String toStation = "";
	
	private int discount = 0;
	
	private String description = "";
	
	private int increment = 0;

	/**
	 * @return the fromStation
	 */
	public String getFromStation() {
		return fromStation;
	}

	/**
	 * @param fromStation the fromStation to set
	 */
	public void setFromStation(String fromStation) {
		this.fromStation = fromStation;
	}

	/**
	 * @return the toStation
	 */
	public String getToStation() {
		return toStation;
	}

	/**
	 * @param toStation the toStation to set
	 */
	public void setToStation(String toStation) {
		this.toStation = toStation;
	}

	/**
	 * @return the discount
	 */
	public int getDiscount() {
		return discount;
	}

	/**
	 * @param discount the discount to set
	 */
	public void setDiscount(int discount) {
		this.discount = discount;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the increment
	 */
	public int getIncrement() {
		return increment;
	}

	/**
	 * @param increment the increment to set
	 */
	public void setIncrement(int increment) {
		this.increment = increment;
	}

	
}
