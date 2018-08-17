/**
 * 
 * @version 1.0
 */
package hm.akr.dto;

import java.io.Serializable;

/**
 * 
 * DTO class for Station
 * 
 */
public class StationDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id = null;

	private String name = null;

	private String profileName = null;

	private float ccCommission = 0;

	private float deliveryCommission = 0;

	private String type = null;

	private String address = null;

	private String city = null;

	private String state = null;

	private int pin = 0;

	private String mobile = null;

	private String phone1 = null;

	private String phone2 = null;

	private String owner = null;

	private int discount = 0;

	private String branch_code = null;

	private String region_code = null;

	private int bf_increment = 0;

	private float bpi = 0;

	private float lrcharge = 0;

	private float gsc = 0;

	/**
	 * Constructor Method
	 */
	public StationDTO() {
		super();

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
	 * @return Returns the discount.
	 */
	public int getDiscount() {
		return discount;
	}

	/**
	 * @param discount
	 *            The discount to set.
	 */
	public void setDiscount(int discount) {
		this.discount = discount;
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
	 * @return Returns the owner.
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * @param owner
	 *            The owner to set.
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * @return Returns the phone1.
	 */
	public String getPhone1() {
		return phone1;
	}

	/**
	 * @param phone1
	 *            The phone1 to set.
	 */
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	/**
	 * @return Returns the phone2.
	 */
	public String getPhone2() {
		return phone2;
	}

	/**
	 * @param phone2
	 *            The phone2 to set.
	 */
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	/**
	 * @return Returns the pin.
	 */
	public int getPin() {
		return pin;
	}

	/**
	 * @param pin
	 *            The pin to set.
	 */
	public void setPin(int pin) {
		this.pin = pin;
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
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return Returns the region_code.
	 */
	public String getRegion_code() {
		return region_code;
	}

	/**
	 * @param region_code
	 *            The region_code to set.
	 */
	public void setRegion_code(String region_code) {
		this.region_code = region_code;
	}

	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the branch_code
	 */
	public String getBranch_code() {
		return branch_code;
	}

	/**
	 * @param branch_code
	 *            the branch_code to set
	 */
	public void setBranch_code(String branch_code) {
		this.branch_code = branch_code;
	}

	/**
	 * @return the bf_increment
	 */
	public int getBf_increment() {
		return bf_increment;
	}

	/**
	 * @param bf_increment
	 *            the bf_increment to set
	 */
	public void setBf_increment(int bf_increment) {
		this.bf_increment = bf_increment;
	}

	public float getBpi() {
		return bpi;
	}

	public void setBpi(float bpi) {
		this.bpi = bpi;
	}

	public float getLrcharge() {
		return lrcharge;
	}

	public void setLrcharge(float lrcharge) {
		this.lrcharge = lrcharge;
	}

	public float getGsc() {
		return gsc;
	}

	public void setGsc(float gsc) {
		this.gsc = gsc;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public float getCcCommission() {
		return ccCommission;
	}

	public void setCcCommission(float ccCommission) {
		this.ccCommission = ccCommission;
	}

	public float getDeliveryCommission() {
		return deliveryCommission;
	}

	public void setDeliveryCommission(float deliveryCommission) {
		this.deliveryCommission = deliveryCommission;
	}

}
