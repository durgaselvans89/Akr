package hm.akr.dto;

import java.io.Serializable;

public class ClientQuotationDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 0 - Weight Based Quotation 1 - Article Based Quotation 2 - Mixed Article
	 * Quotation
	 */
	private byte type = 0;

	/**
	 * true - Inward Booking Type false - Outward Booking Type
	 */
	private boolean isInward = false;

	private float minFreightValue = 0;

	private float minWeightValue = 0;

	/**
	 * 0 - Same As Sundry 1 - Manual
	 */
	private byte lrchargeType = 0;

	private float lrCharge = 0;

	/**
	 * 0 - Same as Sundry 1 - Manual
	 */
	private byte gscType = 0;

	private float gsc = 0;

	private boolean isServiceTax = false;

	/**
	 * 0 - Same as Sundry 1 - Manual 2 - Don't charge
	 */
	private byte insuranceChargeType = 0;

	private InsuranceDTO[] insuranceCharges = null;

	private ClientQuotationDetailsDTO[] quotationDetails = null;

	private byte dhcType = 0;

	private float dhc = 0;

	/**
	 * Constructor method
	 */
	public ClientQuotationDTO() {
		super();
	}

	/**
	 * @return the type
	 */
	public byte getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(byte type) {
		this.type = type;
	}

	/**
	 * @return the isInward
	 */
	public boolean isInward() {
		return isInward;
	}

	/**
	 * @param isInward
	 *            the isInward to set
	 */
	public void setInward(boolean isInward) {
		this.isInward = isInward;
	}

	/**
	 * @return the minFreightValue
	 */
	public float getMinFreightValue() {
		return minFreightValue;
	}

	/**
	 * @param minFreightValue
	 *            the minFreightValue to set
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
	 * @param minWeightValue
	 *            the minWeightValue to set
	 */
	public void setMinWeightValue(float minWeightValue) {
		this.minWeightValue = minWeightValue;
	}

	/**
	 * @return the lrchargeType
	 */
	public byte getLrchargeType() {
		return lrchargeType;
	}

	/**
	 * @param lrchargeType
	 *            the lrchargeType to set
	 */
	public void setLrchargeType(byte lrchargeType) {
		this.lrchargeType = lrchargeType;
	}

	/**
	 * @return the lrCharge
	 */
	public float getLrCharge() {
		return lrCharge;
	}

	/**
	 * @param lrCharge
	 *            the lrCharge to set
	 */
	public void setLrCharge(float lrCharge) {
		this.lrCharge = lrCharge;
	}

	/**
	 * @return the gscType
	 */
	public byte getGscType() {
		return gscType;
	}

	/**
	 * @param gscType
	 *            the gscType to set
	 */
	public void setGscType(byte gscType) {
		this.gscType = gscType;
	}

	/**
	 * @return the gsc
	 */
	public float getGsc() {
		return gsc;
	}

	/**
	 * @param gsc
	 *            the gsc to set
	 */
	public void setGsc(float gsc) {
		this.gsc = gsc;
	}

	/**
	 * @return the isServiceTax
	 */
	public boolean isServiceTax() {
		return isServiceTax;
	}

	/**
	 * @param isServiceTax
	 *            the isServiceTax to set
	 */
	public void setServiceTax(boolean isServiceTax) {
		this.isServiceTax = isServiceTax;
	}

	/**
	 * @return the insuranceCharges
	 */
	public InsuranceDTO[] getInsuranceCharges() {
		return insuranceCharges;
	}

	/**
	 * @param insuranceCharges
	 *            the insuranceCharges to set
	 */
	public void setInsuranceCharges(InsuranceDTO[] insuranceCharges) {
		this.insuranceCharges = insuranceCharges;
	}

	/**
	 * @return the quotationDetails
	 */
	public ClientQuotationDetailsDTO[] getQuotationDetails() {
		return quotationDetails;
	}

	/**
	 * @param quotationDetails
	 *            the quotationDetails to set
	 */
	public void setQuotationDetails(ClientQuotationDetailsDTO[] quotationDetails) {
		this.quotationDetails = quotationDetails;
	}

	/**
	 * @return the insuranceChargeType
	 */
	public byte getInsuranceChargeType() {
		return insuranceChargeType;
	}

	/**
	 * @param insuranceChargeType
	 *            the insuranceChargeType to set
	 */
	public void setInsuranceChargeType(byte insuranceChargeType) {
		this.insuranceChargeType = insuranceChargeType;
	}

	/**
	 * @return the dhcType
	 */
	public byte getDhcType() {
		return dhcType;
	}

	/**
	 * @param dhcType the dhcType to set
	 */
	public void setDhcType(byte dhcType) {
		this.dhcType = dhcType;
	}

	/**
	 * @return the dhc
	 */
	public float getDhc() {
		return dhc;
	}

	/**
	 * @param dhc the dhc to set
	 */
	public void setDhc(float dhc) {
		this.dhc = dhc;
	}

}
