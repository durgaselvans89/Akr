package hm.akr.dto;

import java.io.Serializable;

/**
 * @author Hakuna Matata
 * @version 1.0
 * @copyright (c) AKR Parcel Service
 */

/**
 * DTO class for Distance List 
 */
public class DistanceListDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;	
	
	//station code
	private String destStation = null;
	
	//Net Charge
	private float cardRate = 0;
	
	
	//Distance
	private int distance = 0;
	
	
	//incrementer
	private int incrementer = 0;
	
	
	/**
	 * @return the cardRate
	 */
	public float getCardRate() {
		return cardRate;
	}

	/**
	 * @param cardRate the cardRate to set
	 */
	public void setCardRate(float cardRate) {
		this.cardRate = cardRate;
	}

	/**
	 * @return the destStation
	 */
	public String getDestStation() {
		return destStation;
	}

	/**
	 * @param destStation the destStation to set
	 */
	public void setDestStation(String destStation) {
		this.destStation = destStation;
	}

	/**
	 * @return the distance
	 */
	public int getDistance() {
		return distance;
	}

	/**
	 * @param distance the distance to set
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}

	/**
	 * @return the incrementer
	 */
	public int getIncrementer() {
		return incrementer;
	}

	/**
	 * @param incrementer the incrementer to set
	 */
	public void setIncrementer(int incrementer) {
		this.incrementer = incrementer;
	}

	
	
}




