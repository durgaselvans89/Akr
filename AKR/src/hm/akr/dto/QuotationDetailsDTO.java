package hm.akr.dto;

import java.io.Serializable;

/**
 * @author Hakuna Matata
 * @version 1.0
 */
public class QuotationDetailsDTO implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String articleName = null;
	
	private float  chargedWeight = 0;
	
	/**
	 * 0 - Percentage of basic Freight
	 * 1 - Per article
	 * 2 - Open
	 */
	private byte ccchargeType = 0;
	
	private float ccchargeValue = 0;
	
	/**
	 * 0 - Percentage of basic Freight
	 * 1 - Per article
	 * 2 - Open
	 */
	private byte dcchargeType = 0;
	
	private float dcchargeValue = 0;
	
	/**
	 * 0 - Free
	 * 1 - Charged
	 * 2 - Extra/Open
	 */
	private byte ddchargeType = 0;
	
	private float minDdchargeValue = 0;
	
	private float ddchargeArticle = 0;
	
	/**
	 * 0 - Percentage of Basic Freight
	 * 1 - Per Article
	 */
	private byte iechargeType = 0;
	
	private float iechargeValue = 0;
	
	/**
	 * 0 - Percentage of Basic Freight
	 * 1 - Per Article
	 */
	private byte lcchargeType = 0;
	
	private float lcchargeValue = 0;
	
	private BftDTO[] bft = null;

	/**
	 * Constructor method
	 */
	public QuotationDetailsDTO(){
		super();
	}
	
	/**
	 * @return the chargedWeight
	 */
	public float getChargedWeight() {
		return chargedWeight;
	}


	/**
	 * @param chargedWeight the chargedWeight to set
	 */
	public void setChargedWeight(float chargedWeight) {
		this.chargedWeight = chargedWeight;
	}

	
	/**
	 * @return the ccchargeValue
	 */
	public float getCcchargeValue() {
		return ccchargeValue;
	}

	/**
	 * @param ccchargeValue the ccchargeValue to set
	 */
	public void setCcchargeValue(float ccchargeValue) {
		this.ccchargeValue = ccchargeValue;
	}

	/**
	 * @return the iechargeType
	 */
	public byte getIechargeType() {
		return iechargeType;
	}

	/**
	 * @param iechargeType the iechargeType to set
	 */
	public void setIechargeType(byte iechargeType) {
		this.iechargeType = iechargeType;
	}

	/**
	 * @return the iechargeValue
	 */
	public float getIechargeValue() {
		return iechargeValue;
	}

	/**
	 * @param iechargeValue the iechargeValue to set
	 */
	public void setIechargeValue(float iechargeValue) {
		this.iechargeValue = iechargeValue;
	}

	/**
	 * @return the lcchargeType
	 */
	public byte getLcchargeType() {
		return lcchargeType;
	}

	/**
	 * @param lcchargeType the lcchargeType to set
	 */
	public void setLcchargeType(byte lcchargeType) {
		this.lcchargeType = lcchargeType;
	}

	/**
	 * @return the lcchargeValue
	 */
	public float getLcchargeValue() {
		return lcchargeValue;
	}

	/**
	 * @param lcchargeValue the lcchargeValue to set
	 */
	public void setLcchargeValue(float lcchargeValue) {
		this.lcchargeValue = lcchargeValue;
	}

	/**
	 * @return the bft
	 */
	public BftDTO[] getBft() {
		return bft;
	}


	/**
	 * @param bft the bft to set
	 */
	public void setBft(BftDTO[] bft) {
		this.bft = bft;
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
	 * @return the ccchargeType
	 */
	public byte getCcchargeType() {
		return ccchargeType;
	}

	/**
	 * @param ccchargeType the ccchargeType to set
	 */
	public void setCcchargeType(byte ccchargeType) {
		this.ccchargeType = ccchargeType;
	}

	/**
	 * @return the dcchargeType
	 */
	public byte getDcchargeType() {
		return dcchargeType;
	}

	/**
	 * @param dcchargeType the dcchargeType to set
	 */
	public void setDcchargeType(byte dcchargeType) {
		this.dcchargeType = dcchargeType;
	}

	/**
	 * @return the dcchargeValue
	 */
	public float getDcchargeValue() {
		return dcchargeValue;
	}

	/**
	 * @param dcchargeValue the dcchargeValue to set
	 */
	public void setDcchargeValue(float dcchargeValue) {
		this.dcchargeValue = dcchargeValue;
	}

	/**
	 * @return the ddchargeType
	 */
	public byte getDdchargeType() {
		return ddchargeType;
	}

	/**
	 * @param ddchargeType the ddchargeType to set
	 */
	public void setDdchargeType(byte ddchargeType) {
		this.ddchargeType = ddchargeType;
	}

	
	/**
	 * @return the minDdchargeValue
	 */
	public float getMinDdchargeValue() {
		return minDdchargeValue;
	}

	/**
	 * @param minDdchargeValue the minDdchargeValue to set
	 */
	public void setMinDdchargeValue(float minDdchargeValue) {
		this.minDdchargeValue = minDdchargeValue;
	}

	/**
	 * @return the ddchargeArticle
	 */
	public float getDdchargeArticle() {
		return ddchargeArticle;
	}

	/**
	 * @param ddchargeArticle the ddchargeArticle to set
	 */
	public void setDdchargeArticle(float ddchargeArticle) {
		this.ddchargeArticle = ddchargeArticle;
	}
}
