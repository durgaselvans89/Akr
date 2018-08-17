/**
 * 
 */
package hm.akr.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author kibaitachi
 * 
 */
public class GeneralSummaryDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String station_code = null;

	private int lr_no = 0;

	private float total = 0;

	private float bft = 0;

	private int no_of_articles = 0;

	private float actual_weight = 0;

	private float charged_weight = 0;

	private String sundry_type = null;
	
	private float ccc = 0;
	
	private String branch_code = null;
	
	private Date lrDate = null;

	/**
	 * @return the station_code
	 */
	public String getStation_code() {
		return station_code;
	}

	/**
	 * @param station_code
	 *            the station_code to set
	 */
	public void setStation_code(String station_code) {
		this.station_code = station_code;
	}

	/**
	 * @return the lr_no
	 */
	public int getLr_no() {
		return lr_no;
	}

	/**
	 * @param lr_no
	 *            the lr_no to set
	 */
	public void setLr_no(int lr_no) {
		this.lr_no = lr_no;
	}

	

	/**
	 * @return the no_of_articles
	 */
	public int getNo_of_articles() {
		return no_of_articles;
	}

	/**
	 * @param no_of_articles
	 *            the no_of_articles to set
	 */
	public void setNo_of_articles(int no_of_articles) {
		this.no_of_articles = no_of_articles;
	}
	

	/**
	 * @return the sundry_type
	 */
	public String getSundry_type() {
		return sundry_type;
	}

	/**
	 * @param sundry_type
	 *            the sundry_type to set
	 */
	public void setSundry_type(String sundry_type) {
		this.sundry_type = sundry_type;
	}

	/**
	 * @return the total
	 */
	public float getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(float total) {
		this.total = total;
	}

	/**
	 * @return the bft
	 */
	public float getBft() {
		return bft;
	}

	/**
	 * @param bft the bft to set
	 */
	public void setBft(float bft) {
		this.bft = bft;
	}

	/**
	 * @return the actual_weight
	 */
	public float getActual_weight() {
		return actual_weight;
	}

	/**
	 * @param actual_weight the actual_weight to set
	 */
	public void setActual_weight(float actual_weight) {
		this.actual_weight = actual_weight;
	}

	/**
	 * @return the charged_weight
	 */
	public float getCharged_weight() {
		return charged_weight;
	}

	/**
	 * @param charged_weight the charged_weight to set
	 */
	public void setCharged_weight(float charged_weight) {
		this.charged_weight = charged_weight;
	}

	/**
	 * @return the ccc
	 */
	public float getCcc() {
		return ccc;
	}

	/**
	 * @param ccc the ccc to set
	 */
	public void setCcc(float ccc) {
		this.ccc = ccc;
	}

	/**
	 * @return the branch_code
	 */
	public String getBranch_code() {
		return branch_code;
	}

	/**
	 * @param branch_code the branch_code to set
	 */
	public void setBranch_code(String branch_code) {
		this.branch_code = branch_code;
	}

	/**
	 * @return the lrDate
	 */
	public Date getLrDate() {
		return lrDate;
	}

	/**
	 * @param lrDate the lrDate to set
	 */
	public void setLrDate(Date lrDate) {
		this.lrDate = lrDate;
	}

}
