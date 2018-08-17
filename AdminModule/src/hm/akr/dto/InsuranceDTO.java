package hm.akr.dto;

import java.io.Serializable;

public class InsuranceDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String quotationId = null;
	
	private String articleName = null;
	
	private float fromValue = 0;
	
	private float toValue = 0;
	
	private float insuranceChargeValue = 0;

	/**
	 * @return the quotationId
	 */
	public String getQuotationId() {
		return quotationId;
	}

	/**
	 * @param quotationId the quotationId to set
	 */
	public void setQuotationId(String quotationId) {
		this.quotationId = quotationId;
	}

	/**
	 * @return the articleName
	 */
	public String getArticleName() {
		return articleName;
	}

	/**
	 * @param articleName the articleName to set
	 */
	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}

	/**
	 * @return the fromValue
	 */
	public float getFromValue() {
		return fromValue;
	}

	/**
	 * @param fromValue the fromValue to set
	 */
	public void setFromValue(float fromValue) {
		this.fromValue = fromValue;
	}

	/**
	 * @return the toValue
	 */
	public float getToValue() {
		return toValue;
	}

	/**
	 * @param toValue the toValue to set
	 */
	public void setToValue(float toValue) {
		this.toValue = toValue;
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
