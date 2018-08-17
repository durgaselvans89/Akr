package hm.akr.dto;

import java.io.Serializable;

public class InsuranceChargeDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private float fromRange = 0;
	
	private float toRange = 0;
	
	private float insuranceChargeValue = 0;	
	
	public InsuranceChargeDTO(){
		super();
	}

	/**
	 * @return the fromRange
	 */
	public float getFromRange() {
		return fromRange;
	}

	/**
	 * @param fromRange the fromRange to set
	 */
	public void setFromRange(float fromRange) {
		this.fromRange = fromRange;
	}

	/**
	 * @return the toRange
	 */
	public float getToRange() {
		return toRange;
	}

	/**
	 * @param toRange the toRange to set
	 */
	public void setToRange(float toRange) {
		this.toRange = toRange;
	}

	/**
	 * @return the insuranceChargeValue
	 */
	public float getInsuranceChargeValue() {
		return insuranceChargeValue;
	}

	/**
	 * @param insuranceChargeValue the insuranceChargeValue to set
	 */
	public void setInsuranceChargeValue(float insuranceChargeValue) {
		this.insuranceChargeValue = insuranceChargeValue;
	}
	
}
