package hm.akr.dto;

import java.io.Serializable;

public class ProfileDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String profileId = null;
	
	private String profileName = null;
	
	private CardDTO[] card = null;
	
	private int cc_limit = 0;

	private int cc_consider = 0;
	
	private int cc_refund = 0;
	
	private int ccforspecial = 0;
	
	private int ccforcommodity = 0;
	
	private int delivery_commission = 0;
	
	
	public String getProfileId() {
		return profileId;
	}

	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public CardDTO[] getCard() {
		return card;
	}

	public void setCard(CardDTO[] card) {
		this.card = card;
	}

	/**
	 * @return the cc_limit
	 */
	public int getCc_limit() {
		return cc_limit;
	}

	/**
	 * @param cc_limit the cc_limit to set
	 */
	public void setCc_limit(int cc_limit) {
		this.cc_limit = cc_limit;
	}

	/**
	 * @return the cc_consider
	 */
	public int getCc_consider() {
		return cc_consider;
	}

	/**
	 * @param cc_consider the cc_consider to set
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
	 * @param cc_refund the cc_refund to set
	 */
	public void setCc_refund(int cc_refund) {
		this.cc_refund = cc_refund;
	}

	/**
	 * @return the ccforspecial
	 */
	public int getCcforspecial() {
		return ccforspecial;
	}

	/**
	 * @param ccforspecial the ccforspecial to set
	 */
	public void setCcforspecial(int ccforspecial) {
		this.ccforspecial = ccforspecial;
	}

	/**
	 * @return the ccforcommodity
	 */
	public int getCcforcommodity() {
		return ccforcommodity;
	}

	/**
	 * @param ccforcommodity the ccforcommodity to set
	 */
	public void setCcforcommodity(int ccforcommodity) {
		this.ccforcommodity = ccforcommodity;
	}

	/**
	 * @return the delivery_commission
	 */
	public int getDelivery_commission() {
		return delivery_commission;
	}

	/**
	 * @param delivery_commission the delivery_commission to set
	 */
	public void setDelivery_commission(int delivery_commission) {
		this.delivery_commission = delivery_commission;
	}
	

}
