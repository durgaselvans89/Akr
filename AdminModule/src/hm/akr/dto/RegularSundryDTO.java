package hm.akr.dto;

import java.io.Serializable;


/**
 * DTO class for Regular Sundry
 * 
 * @author Hakuna Matata
 * @version 1.0
 * @copyright (c) AKR Parcel Service
 */
public class RegularSundryDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private float bpi = 0;
	
	private float serviceTax = 0;
	
	private float lrCharge = 0;
	
	private float minWeight = 0;
	
	private float minFreight = 0;
	
	private float gsc = 0;
	
	private InsuranceDTO[] insuranceDTO = null;
	
	//Service Tax Limit Value
	private int staxLimit = 0;
	
	private int weightRoundOff = 0;
	
	private String stationCode = null;
	
	private int distance = 0;

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	/**
	 * @return the bpi
	 */
	public float getBpi() {
		return bpi;
	}

	/**
	 * @param bpi the bpi to set
	 */
	public void setBpi(float bpi) {
		this.bpi = bpi;
	}

	/**
	 * @return the serviceTax
	 */
	public float getServiceTax() {
		return serviceTax;
	}

	/**
	 * @param serviceTax the serviceTax to set
	 */
	public void setServiceTax(float serviceTax) {
		this.serviceTax = serviceTax;
	}

	/**
	 * @return the lrCharge
	 */
	public float getLrCharge() {
		return lrCharge;
	}

	/**
	 * @param lrCharge the lrCharge to set
	 */
	public void setLrCharge(float lrCharge) {
		this.lrCharge = lrCharge;
	}

	/**
	 * @return the minWeight
	 */
	public float getMinWeight() {
		return minWeight;
	}

	/**
	 * @param minWeight the minWeight to set
	 */
	public void setMinWeight(float minWeight) {
		this.minWeight = minWeight;
	}

	/**
	 * @return the minFreight
	 */
	public float getMinFreight() {
		return minFreight;
	}

	/**
	 * @param minFreight the minFreight to set
	 */
	public void setMinFreight(float minFreight) {
		this.minFreight = minFreight;
	}

	/**
	 * @return the gsc
	 */
	public float getGsc() {
		return gsc;
	}

	/**
	 * @param gsc the gsc to set
	 */
	public void setGsc(float gsc) {
		this.gsc = gsc;
	}

	/**
	 * @return the insuranceDTO
	 */
	public InsuranceDTO[] getInsuranceDTO() {
		return insuranceDTO;
	}

	/**
	 * @param insuranceDTO the insuranceDTO to set
	 */
	public void setInsuranceDTO(InsuranceDTO[] insuranceDTO) {
		this.insuranceDTO = insuranceDTO;
	}

	

	public int getStaxLimit() {
		return staxLimit;
	}

	public void setStaxLimit(int staxLimit) {
		this.staxLimit = staxLimit;
	}

	/**
	 * @return the weightRoundOff
	 */
	public int getWeightRoundOff() {
		return weightRoundOff;
	}

	/**
	 * @param weightRoundOff the weightRoundOff to set
	 */
	public void setWeightRoundOff(int weightRoundOff) {
		this.weightRoundOff = weightRoundOff;
	}

	/**
	 * @return the stationCode
	 */
	public String getStationCode() {
		return stationCode;
	}

	/**
	 * @param stationCode the stationCode to set
	 */
	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}
	

}
