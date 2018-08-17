package hm.akr.dto;

import java.io.Serializable;

public class QuotationDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String id = null;
	
	/**
	 * 0 - Weight Based Quotation
	 * 1 - Article Based Quotation
	 * 2 - Mixed Article Quotation
	 */
	private byte type = 0;
	
	/**
	 * true - Inward Booking Type
	 * false - Outward Booking Type 
	 */
	private boolean isInward = false;
	
	private String stationCode = null;
	
	private String customerName = null;
	
	private float priceIndex = 0;
	
	private float minFreightValue = 0;
	
	private float minWeightValue = 0;
	
	/**
	 * 0 - Same As Sundry
	 * 1 - Manual
	 */
	private byte lrchargeType = 0;
	
	/**
	 * 0 - Same as Sundry
	 * 1 - Manual
	 */
	private byte gscType = 0;
	
	private OtherChargesDTO[] otherCharges = null;
	
	private boolean isServiceTax = false;
	
	/**
	 * 0 - Same as Sundry
	 * 1 - Manual
	 * 2 - Don't charge
	 */
	private byte insuranceChargeType = 0;
	
	private InsuranceDTO[] insuranceCharges = null;
	
	private QuotationDetailsDTO[] quotationDetails = null;
	
	/**
	 * Constructor method
	 */
	public QuotationDTO(){
		super();
	}

	/**
	 * @return the otherCharges
	 */
	public OtherChargesDTO[] getOtherCharges() {
		return otherCharges;
	}

	/**
	 * @param otherCharges the otherCharges to set
	 */
	public void setOtherCharges(OtherChargesDTO[] otherCharges) {
		this.otherCharges = otherCharges;
	}

	

	/**
	 * @return the insuranceCharges
	 */
	public InsuranceDTO[] getInsuranceCharges() {
		return insuranceCharges;
	}

	/**
	 * @param insuranceCharges the insuranceCharges to set
	 */
	public void setInsuranceCharges(InsuranceDTO[] insuranceCharges) {
		this.insuranceCharges = insuranceCharges;
	}

	/**
	 * @return the quotationDetails
	 */
	public QuotationDetailsDTO[] getQuotationDetails() {
		return quotationDetails;
	}

	/**
	 * @param quotationDetails the quotationDetails to set
	 */
	public void setQuotationDetails(QuotationDetailsDTO[] quotationDetails) {
		this.quotationDetails = quotationDetails;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the type
	 */
	public byte getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(byte type) {
		this.type = type;
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

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the priceIndex
	 */
	public float getPriceIndex() {
		return priceIndex;
	}

	/**
	 * @param priceIndex the priceIndex to set
	 */
	public void setPriceIndex(float priceIndex) {
		this.priceIndex = priceIndex;
	}

	/**
	 * @return the minFreightValue
	 */
	public float getMinFreightValue() {
		return minFreightValue;
	}

	/**
	 * @param minFreightValue the minFreightValue to set
	 */
	public void setMinFreightValue(float minFreightValue) {
		this.minFreightValue = minFreightValue;
	}

	/**
	 * @return the minWeightValue
	 */
	public float getMinWeightValue() {
		return minWeightValue;
	}

	/**
	 * @param minWeightValue the minWeightValue to set
	 */
	public void setMinWeightValue(float minWeightValue) {
		this.minWeightValue = minWeightValue;
	}

	/**
	 * @return the isInward
	 */
	public boolean isInward() {
		return isInward;
	}

	/**
	 * @param isInward the isInward to set
	 */
	public void setInward(boolean isInward) {
		this.isInward = isInward;
	}

	/**
	 * @return the isServiceTax
	 */
	public boolean isServiceTax() {
		return isServiceTax;
	}
	/**
	 * @param isServiceTax the isServiceTax to set
	 */
	public void setServiceTax(boolean isServiceTax) {
		this.isServiceTax = isServiceTax;
	}

	/**
	 * @return the lrchargeType
	 */
	public byte getLrchargeType() {
		return lrchargeType;
	}

	/**
	 * @param lrchargeType the lrchargeType to set
	 */
	public void setLrchargeType(byte lrchargeType) {
		this.lrchargeType = lrchargeType;
	}

	/**
	 * @return the gscType
	 */
	public byte getGscType() {
		return gscType;
	}

	/**
	 * @param gscType the gscType to set
	 */
	public void setGscType(byte gscType) {
		this.gscType = gscType;
	}

	/**
	 * @return the insuranceChargeType
	 */
	public byte getInsuranceChargeType() {
		return insuranceChargeType;
	}

	/**
	 * @param insuranceChargeType the insuranceChargeType to set
	 */
	public void setInsuranceChargeType(byte insuranceChargeType) {
		this.insuranceChargeType = insuranceChargeType;
	}

}
