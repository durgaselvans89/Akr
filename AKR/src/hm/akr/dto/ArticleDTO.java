/**
 * @author Hakuna Matata
 * @version 1.0
 * @copyright (c) AKR
 */
package hm.akr.dto;

import java.io.Serializable;

/**
 * DTO class for ARTICLE 
 */
public class ArticleDTO implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    private int articleId = 0;
	
	private String type = null;
	
	private String articleName = null;
	
	private float chargedWeight = 0;
	
	private int noOfArticles = 0;
	
	private String articleValue = null;
	
	private float actualWeight = 0;
	
	private float volumetricWeight = 0;
	
	private String articleDesc = null;
	
	private float  value_per_km =0;
	
	private float  art_length =0;
	
	private float  art_breath =0;
	
	private float  art_height =0;
	

	
	
	
	
	
	
	/**
	 * Constructor method
	 */
	public ArticleDTO() {
		super();
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
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
	 * @return the noOfArticles
	 */
	public int getNoOfArticles() {
		return noOfArticles;
	}

	/**
	 * @param noOfArticles the noOfArticles to set
	 */
	public void setNoOfArticles(int noOfArticles) {
		this.noOfArticles = noOfArticles;
	}

	/**
	 * @return the articleValue
	 */
	public String getArticleValue() {
		return articleValue;
	}

	/**
	 * @param articleValue the articleValue to set
	 */
	public void setArticleValue(String articleValue) {
		this.articleValue = articleValue;
	}

	/**
	 * @return the actualWeight
	 */
	public float getActualWeight() {
		return actualWeight;
	}

	/**
	 * @param actualWeight the actualWeight to set
	 */
	public void setActualWeight(float actualWeight) {
		this.actualWeight = actualWeight;
	}

	/**
	 * @return the volumetricWeight
	 */
	public float getVolumetricWeight() {
		return volumetricWeight;
	}

	/**
	 * @param volumetricWeight the volumetricWeight to set
	 */
	public void setVolumetricWeight(float volumetricWeight) {
		this.volumetricWeight = volumetricWeight;
	}

	/**
	 * @return the articleDesc
	 */
	public String getArticleDesc() {
		return articleDesc;
	}

	/**
	 * @param articleDesc the articleDesc to set
	 */
	public void setArticleDesc(String articleDesc) {
		this.articleDesc = articleDesc;
	}

	/**
	 * @return the value_per_km
	 */
	public float getValue_per_km() {
		return value_per_km;
	}

	/**
	 * @param value_per_km the value_per_km to set
	 */
	public void setValue_per_km(float value_per_km) {
		this.value_per_km = value_per_km;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	/**
	 * @return the art_length
	 */
	public float getArt_length() {
		return art_length;
	}

	/**
	 * @param art_length the art_length to set
	 */
	public void setArt_length(float art_length) {
		this.art_length = art_length;
	}

	/**
	 * @return the art_breath
	 */
	public float getArt_breath() {
		return art_breath;
	}

	/**
	 * @param art_breath the art_breath to set
	 */
	public void setArt_breath(float art_breath) {
		this.art_breath = art_breath;
	}

	/**
	 * @return the art_height
	 */
	public float getArt_height() {
		return art_height;
	}

	/**
	 * @param art_height the art_height to set
	 */
	public void setArt_height(float art_height) {
		this.art_height = art_height;
	}

}
