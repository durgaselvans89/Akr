package hm.akr.dto;

import java.io.Serializable;

public class TranshipmentDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	

	private String station_code = null;	
	
	private int inwardLr = 0;
	
	private int delivery1Day = 0;
	
	private int delivery2Day = 0;
	
	private int delivery3Day = 0;
	
	private int deliveryM3 = 0;
	
	private int stock = 0;
	
	private int intime1Day = 0;

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
	 * @return the inwardLr
	 */
	public int getInwardLr() {
		return inwardLr;
	}

	/**
	 * @param inwardLr the inwardLr to set
	 */
	public void setInwardLr(int inwardLr) {
		this.inwardLr = inwardLr;
	}

	/**
	 * @return the delivery1Day
	 */
	public int getDelivery1Day() {
		return delivery1Day;
	}

	/**
	 * @param delivery1Day the delivery1Day to set
	 */
	public void setDelivery1Day(int delivery1Day) {
		this.delivery1Day = delivery1Day;
	}

	/**
	 * @return the delivery2Day
	 */
	public int getDelivery2Day() {
		return delivery2Day;
	}

	/**
	 * @param delivery2Day the delivery2Day to set
	 */
	public void setDelivery2Day(int delivery2Day) {
		this.delivery2Day = delivery2Day;
	}

	/**
	 * @return the delivery3Day
	 */
	public int getDelivery3Day() {
		return delivery3Day;
	}

	/**
	 * @param delivery3Day the delivery3Day to set
	 */
	public void setDelivery3Day(int delivery3Day) {
		this.delivery3Day = delivery3Day;
	}

	/**
	 * @return the deliveryM3
	 */
	public int getDeliveryM3() {
		return deliveryM3;
	}

	/**
	 * @param deliveryM3 the deliveryM3 to set
	 */
	public void setDeliveryM3(int deliveryM3) {
		this.deliveryM3 = deliveryM3;
	}

	/**
	 * @return the stock
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * @param stock the stock to set
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}

	/**
	 * @return the intime1Day
	 */
	public int getIntime1Day() {
		return intime1Day;
	}

	/**
	 * @param intime1Day the intime1Day to set
	 */
	public void setIntime1Day(int intime1Day) {
		this.intime1Day = intime1Day;
	}

	

}
