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
public class StationsDTO implements Serializable {

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

	private int drs_duration = 0;

	private int drs_fine = 0;

	private float min_freight_value = 0;

	private float min_weight_value = 0;

	private int cc_limit = 0;

	private int cc_special = 0;

	private int cc_commodity = 0;

	private int cc_consider = 0;

	private int cc_refund = 0;

	private float cc_hlc = 0;

	private float refundperarticle = 0;
	
	private float dhc = 0;
	
	
	
	private String stationname = null;
	
	private String lrno = null;

	private String lrdate = null;

	private String from = null;

	private String to = null;
	
	private String cnor = null;
	
	private String cnee = null;

	private float totalfreight = 0;
	
	private float totalfreightin25 = 0;
	
	private float servicetax = 0;
	
	private float educess = 0;

	private float hreducess = 0;
	
	private float totalst = 0;
	
	


	/**
	 * @return the refundperarticle
	 */
	public float getRefundperarticle() {
		return refundperarticle;
	}

	/**
	 * @param refundperarticle
	 *            the refundperarticle to set
	 */
	public void setRefundperarticle(float refundperarticle) {
		this.refundperarticle = refundperarticle;
	}

	public float getCc_hlc() {
		return cc_hlc;
	}

	public void setCc_hlc(float cc_hlc) {
		this.cc_hlc = cc_hlc;
	}

	/**
	 * @return the min_freight_value
	 */
	public float getMin_freight_value() {
		return min_freight_value;
	}

	/**
	 * @param min_freight_value
	 *            the min_freight_value to set
	 */
	public void setMin_freight_value(float min_freight_value) {
		this.min_freight_value = min_freight_value;
	}

	/**
	 * @return the min_weight_value
	 */
	public float getMin_weight_value() {
		return min_weight_value;
	}

	/**
	 * @param min_weight_value
	 *            the min_weight_value to set
	 */
	public void setMin_weight_value(float min_weight_value) {
		this.min_weight_value = min_weight_value;
	}

	/**
	 * @return the drs_duration
	 */
	public int getDrs_duration() {
		return drs_duration;
	}

	/**
	 * @param drs_duration
	 *            the drs_duration to set
	 */
	public void setDrs_duration(int drs_duration) {
		this.drs_duration = drs_duration;
	}

	/**
	 * @return the drs_fine
	 */
	public int getDrs_fine() {
		return drs_fine;
	}

	/**
	 * @param drs_fine
	 *            the drs_fine to set
	 */
	public void setDrs_fine(int drs_fine) {
		this.drs_fine = drs_fine;
	}

	/**
	 * Constructor Method
	 */
	public StationsDTO() {
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

	public int getCc_limit() {
		return cc_limit;
	}

	public void setCc_limit(int cc_limit) {
		this.cc_limit = cc_limit;
	}

	/**
	 * @return the cc_special
	 */
	public int getCc_special() {
		return cc_special;
	}

	/**
	 * @param cc_special
	 *            the cc_special to set
	 */
	public void setCc_special(int cc_special) {
		this.cc_special = cc_special;
	}

	/**
	 * @return the cc_commodity
	 */
	public int getCc_commodity() {
		return cc_commodity;
	}

	/**
	 * @param cc_commodity
	 *            the cc_commodity to set
	 */
	public void setCc_commodity(int cc_commodity) {
		this.cc_commodity = cc_commodity;
	}

	/**
	 * @return the cc_consider
	 */
	public int getCc_consider() {
		return cc_consider;
	}

	/**
	 * @param cc_consider
	 *            the cc_consider to set
	 */
	public void setCc_consider(int cc_consider) {
		this.cc_consider = cc_consider;
	}

	/**
	 * @return the cc_refund
	 */
	public int getCc_refund() {
		return cc_refund;
	}

	/**
	 * @param cc_refund
	 *            the cc_refund to set
	 */
	public void setCc_refund(int cc_refund) {
		this.cc_refund = cc_refund;
	}


	public float getDhc() {
		return dhc;
	}

	
	public void setDhc(float dhc) {
		this.dhc = dhc;
	}

	
	
	
	
	
	public void setStationname(String stationname) {
		this.stationname = stationname;
	}
	
	
	public String getStationname() {
		return stationname;
	}

	
	public void setLrno(String lrno) {
		this.lrno = lrno;
	}

	
	public String getLrno() {
		return lrno;
	}

	
	public void setLrdate(String lrdate) {
		this.lrdate = lrdate;
	}
	
	
	public String getLrdate() {
		return lrdate;
	}
	
	public String getFrom() {
		return from;
	}

	
	public void setFrom(String from) {
		this.from = from;
	}

	
	public String getTo() {
		return to;
	}


	public void setTo(String to) {
		this.to = to;
	}

	
	public String getCnor() {
		return cnor;
	}

	
	public void setCnor(String cnor) {	
		this.cnor = cnor;
	}


	public String getCnee() {
		return cnee;
	}


	public void setCnee(String cnee) {
		this.cnee = cnee;
	}

	public Float getTotalfreight() {
		return totalfreight;
	}

	
	public void setTotalfreight(Float totalfreight) {
		this.totalfreight = totalfreight;
	}

	public Float getTotalfreightin25() {
		return totalfreightin25;
	}

	
	public void setTotalfreightin25(Float totalfreightin25) {
		this.totalfreightin25 = totalfreightin25;
	}
	
	public Float getServicetax() {
		return servicetax;
	}

	
	public void setServicetax(Float servicetax) {
		this.servicetax = servicetax;
	}
	
	public Float getEducess() {
		return educess;
	}

	
	public void setEducess(Float educess) {
		this.educess = educess;
	}
	
	public Float getHreducess() {
		
		return hreducess;
	}

	
	public void setHreducess(Float hreducess) {
		this.hreducess = hreducess;
	}

	public Float getTotalst() {
		
		return totalst;
	}

	
	public void setTotalst(Float totalst) {
		this.totalst = totalst;
	}

	

	
	
	
	
}
