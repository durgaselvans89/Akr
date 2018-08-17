/**
 * @author Hakuna Matata
 * @version 1.0
 * @copyright (c) AKR
 */
package hm.akr.dto;

import java.io.Serializable;

/**
 * DTO class for Common Measuring Information.  
 */

public class SpecialSundryDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int minDistance = 0;
	
	private int maxDistance = 0;
	
	private float minWeight = 0;
	
	private float maxWeight = 0;
	
	private float bft = 0;
	
	private float lrc = 0;
	
	public SpecialSundryDTO(){
		super();
		// TODO Auto-generated constructor stub
	}
	

	public float getMinWeight() {
		return minWeight;
	}

	public void setMinWeight(float minWeight) {
		this.minWeight = minWeight;
	}

	public float getMaxWeight() {
		return maxWeight;
	}

	public void setMaxWeight(float maxWeight) {
		this.maxWeight = maxWeight;
	}

	public float getBft() {
		return bft;
	}

	public void setBft(float bft) {
		this.bft = bft;
	}

	public float getLrc() {
		return lrc;
	}

	public void setLrc(float lrc) {
		this.lrc = lrc;
	}


	/**
	 * @return the minDistance
	 */
	public int getMinDistance() {
		return minDistance;
	}


	/**
	 * @param minDistance the minDistance to set
	 */
	public void setMinDistance(int minDistance) {
		this.minDistance = minDistance;
	}


	/**
	 * @return the maxDistance
	 */
	public int getMaxDistance() {
		return maxDistance;
	}


	/**
	 * @param maxDistance the maxDistance to set
	 */
	public void setMaxDistance(int maxDistance) {
		this.maxDistance = maxDistance;
	}
	
	
	

}
