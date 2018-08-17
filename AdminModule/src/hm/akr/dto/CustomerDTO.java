package hm.akr.dto;

import java.io.Serializable;

/**
 * 
 * @version 1.0 DTO class for Customer
 */

public class CustomerDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int customerId = 0;

	private String name = null;

	private String station = null;

	private String quotationId = null;

	private String typeOfCustomer = null;

	private String taxNo = null;

	private String contactPerson = null;

	private String address = null;

	private String address2 = null;

	private String city = null;

	private String state = null;

	private String mobile = null;

	private String phone = null;

	private String emailId = null;

	private boolean bookingType = false;

	private int pincode = 0;

	private float ccperarticle = 0;

	/** Is Consignee or Consignor * */
	private boolean isConsignee = false;

	/** Tin Number * */
	private String tinNo = null;

	private int id = 0;

	/**
	 * 1 - basic format 1 2 - basic format 2
	 */
	private byte basicFormat = 0;

	/**
	 * 0 - add-on-format1 not selected 1 - add-on-format1 selected
	 */
	private byte addOnFormat1 = 0;

	/**
	 * 0 - add-on-format2 not selected 1 - add-on-format2 selected
	 */
	private byte addOnFormat2 = 0;

	/**
	 * 0 - add-on-format3 not selected 1 - add-on-format3 selected
	 */
	private byte addOnFormat3 = 0;

	private int quotation_type = -1;
	
	private byte cnorSMS = 0;
	
	private byte cneeSMS = 0;
	
	private String landLine = null;
	
	private int service_stax = 0;
	
	
	

	/**
	 * @return the service_stax
	 */
	public int getService_stax() {
		return service_stax;
	}

	/**
	 * @param service_stax the service_stax to set
	 */
	public void setService_stax(int service_stax) {
		this.service_stax = service_stax;
	}

	/**
	 * @return the landLine
	 */
	public String getLandLine() {
		return landLine;
	}

	/**
	 * @param landLine the landLine to set
	 */
	public void setLandLine(String landLine) {
		this.landLine = landLine;
	}

	/**
	 * Constructor method
	 */
	public CustomerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return Returns the address.
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            The address to set.
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return Returns the city.
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            The city to set.
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return Returns the mobile.
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            The mobile to set.
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Returns the phone.
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            The phone to set.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return Returns the pincode.
	 */
	public int getPincode() {
		return pincode;
	}

	/**
	 * @param pincode
	 *            The pincode to set.
	 */
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	/**
	 * @return Returns the state.
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            The state to set.
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return Returns the station.
	 */
	public String getStation() {
		return station;
	}

	/**
	 * @param station
	 *            The station to set.
	 */
	public void setStation(String station) {
		this.station = station;
	}

	/**
	 * @return the customerId
	 */
	public int getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId
	 *            the customerId to set
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the typeOfCustomer
	 */
	public String getTypeOfCustomer() {
		return typeOfCustomer;
	}

	/**
	 * @param typeOfCustomer
	 *            the typeOfCustomer to set
	 */
	public void setTypeOfCustomer(String typeOfCustomer) {
		this.typeOfCustomer = typeOfCustomer;
	}

	/**
	 * @return the taxNo
	 */
	public String getTaxNo() {
		return taxNo;
	}

	/**
	 * @param taxNo
	 *            the taxNo to set
	 */
	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}

	/**
	 * @return the contactPerson
	 */
	public String getContactPerson() {
		return contactPerson;
	}

	/**
	 * @param contactPerson
	 *            the contactPerson to set
	 */
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId
	 *            the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the bookingType
	 */
	public boolean isBookingType() {
		return bookingType;
	}

	/**
	 * @param bookingType
	 *            the bookingType to set
	 */
	public void setBookingType(boolean bookingType) {
		this.bookingType = bookingType;
	}

	/**
	 * @return the quotationId
	 */
	public String getQuotationId() {
		return quotationId;
	}

	/**
	 * @param quotationId
	 *            the quotationId to set
	 */
	public void setQuotationId(String quotationId) {
		this.quotationId = quotationId;
	}

	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * @param address2
	 *            the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * @return the basicFormat
	 */
	public byte getBasicFormat() {
		return basicFormat;
	}

	/**
	 * @param basicFormat
	 *            the basicFormat to set
	 */
	public void setBasicFormat(byte basicFormat) {
		this.basicFormat = basicFormat;
	}

	/**
	 * @return the addOnFormat1
	 */
	public byte getAddOnFormat1() {
		return addOnFormat1;
	}

	/**
	 * @param addOnFormat1
	 *            the addOnFormat1 to set
	 */
	public void setAddOnFormat1(byte addOnFormat1) {
		this.addOnFormat1 = addOnFormat1;
	}

	/**
	 * @return the addOnFormat2
	 */
	public byte getAddOnFormat2() {
		return addOnFormat2;
	}

	/**
	 * @param addOnFormat2
	 *            the addOnFormat2 to set
	 */
	public void setAddOnFormat2(byte addOnFormat2) {
		this.addOnFormat2 = addOnFormat2;
	}

	/**
	 * @return the addOnFormat3
	 */
	public byte getAddOnFormat3() {
		return addOnFormat3;
	}

	/**
	 * @param addOnFormat3
	 *            the addOnFormat3 to set
	 */
	public void setAddOnFormat3(byte addOnFormat3) {
		this.addOnFormat3 = addOnFormat3;
	}

	/**
	 * @return the tinNo
	 */
	public String getTinNo() {
		return tinNo;
	}

	/**
	 * @param tinNo
	 *            the tinNo to set
	 */
	public void setTinNo(String tinNo) {
		this.tinNo = tinNo;
	}

	/**
	 * @return the isConsignee
	 */
	public boolean isConsignee() {
		return isConsignee;
	}

	/**
	 * @param isConsignee
	 *            the isConsignee to set
	 */
	public void setConsignee(boolean isConsignee) {
		this.isConsignee = isConsignee;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the quotation_type
	 */
	public int getQuotation_type() {
		return quotation_type;
	}

	/**
	 * @param quotation_type
	 *            the quotation_type to set
	 */
	public void setQuotation_type(int quotation_type) {
		this.quotation_type = quotation_type;
	}

	public float getCcperarticle() {
		return ccperarticle;
	}

	public void setCcperarticle(float ccperarticle) {
		this.ccperarticle = ccperarticle;
	}

	/**
	 * @return the cnorSMS
	 */
	public byte getCnorSMS() {
		return cnorSMS;
	}

	/**
	 * @param cnorSMS the cnorSMS to set
	 */
	public void setCnorSMS(byte cnorSMS) {
		this.cnorSMS = cnorSMS;
	}

	/**
	 * @return the cneeSMS
	 */
	public byte getCneeSMS() {
		return cneeSMS;
	}

	/**
	 * @param cneeSMS the cneeSMS to set
	 */
	public void setCneeSMS(byte cneeSMS) {
		this.cneeSMS = cneeSMS;
	}

}
