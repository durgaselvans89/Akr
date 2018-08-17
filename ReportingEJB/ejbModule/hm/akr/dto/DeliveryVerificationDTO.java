/**
 * 
 */
package hm.akr.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Naruto1
 * 
 */
public class DeliveryVerificationDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String lr_no = null;

	private Date lr_date = null;

	private String cnor_name = null;

	private String cnee_name = null;

	private String cnee_address = null;

	private int noa = 0;

	private float actual_wt = 0;

	private String art_id = null;

	private float lr_total = 0;

	private float ddc = 0;

	private String lr_type = null;

	private int inward_days = 0;

	private String from = null;

	private String to = null;

	private Date outward_date = null;

	/**
	 * @return the outward_date
	 */
	public Date getOutward_date() {
		return outward_date;
	}

	/**
	 * @param outward_date
	 *            the outward_date to set
	 */
	public void setOutward_date(Date outward_date) {
		this.outward_date = outward_date;
	}

	/**
	 * @return the lr_no
	 */
	public String getLr_no() {
		return lr_no;
	}

	/**
	 * @param lr_no
	 *            the lr_no to set
	 */
	public void setLr_no(String lr_no) {
		this.lr_no = lr_no;
	}

	/**
	 * @return the lr_date
	 */
	public Date getLr_date() {
		return lr_date;
	}

	/**
	 * @param lr_date
	 *            the lr_date to set
	 */
	public void setLr_date(Date lr_date) {
		this.lr_date = lr_date;
	}

	/**
	 * @return the cnor_name
	 */
	public String getCnor_name() {
		return cnor_name;
	}

	/**
	 * @param cnor_name
	 *            the cnor_name to set
	 */
	public void setCnor_name(String cnor_name) {
		this.cnor_name = cnor_name;
	}

	/**
	 * @return the cnee_name
	 */
	public String getCnee_name() {
		return cnee_name;
	}

	/**
	 * @param cnee_name
	 *            the cnee_name to set
	 */
	public void setCnee_name(String cnee_name) {
		this.cnee_name = cnee_name;
	}

	/**
	 * @return the cnee_address
	 */
	public String getCnee_address() {
		return cnee_address;
	}

	/**
	 * @param cnee_address
	 *            the cnee_address to set
	 */
	public void setCnee_address(String cnee_address) {
		this.cnee_address = cnee_address;
	}

	/**
	 * @return the noa
	 */
	public int getNoa() {
		return noa;
	}

	/**
	 * @param noa
	 *            the noa to set
	 */
	public void setNoa(int noa) {
		this.noa = noa;
	}

	/**
	 * @return the actual_wt
	 */
	public float getActual_wt() {
		return actual_wt;
	}

	/**
	 * @param actual_wt
	 *            the actual_wt to set
	 */
	public void setActual_wt(float actual_wt) {
		this.actual_wt = actual_wt;
	}

	/**
	 * @return the art_id
	 */
	public String getArt_id() {
		return art_id;
	}

	/**
	 * @param art_id
	 *            the art_id to set
	 */
	public void setArt_id(String art_id) {
		this.art_id = art_id;
	}

	/**
	 * @return the lr_total
	 */
	public float getLr_total() {
		return lr_total;
	}

	/**
	 * @param lr_total
	 *            the lr_total to set
	 */
	public void setLr_total(float lr_total) {
		this.lr_total = lr_total;
	}

	/**
	 * @return the ddc
	 */
	public float getDdc() {
		return ddc;
	}

	/**
	 * @param ddc
	 *            the ddc to set
	 */
	public void setDdc(float ddc) {
		this.ddc = ddc;
	}

	/**
	 * @return the lr_type
	 */
	public String getLr_type() {
		return lr_type;
	}

	/**
	 * @param lr_type
	 *            the lr_type to set
	 */
	public void setLr_type(String lr_type) {
		this.lr_type = lr_type;
	}

	/**
	 * @return the inward_days
	 */
	public int getInward_days() {
		return inward_days;
	}

	/**
	 * @param inward_days
	 *            the inward_days to set
	 */
	public void setInward_days(int inward_days) {
		this.inward_days = inward_days;
	}

	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from
	 *            the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}

	/**
	 * @param to
	 *            the to to set
	 */
	public void setTo(String to) {
		this.to = to;
	}

}
