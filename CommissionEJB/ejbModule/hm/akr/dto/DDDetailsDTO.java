package hm.akr.dto;

import java.io.Serializable;
import java.util.Date;

public class DDDetailsDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String lrNo = null;
	private Date lrDate = null;
	private String mode = null;
	private float ddc = 0;
	private float ddcFree = 0;
	private float ddExtra = 0;
	private float others = 0;
	private float demurrage = 0;
	private float underCharge = 0;
	private float postageCharge = 0;
	private float total = 0;

	/**
	 * @return the lrNo
	 */
	public String getLrNo() {
		return lrNo;
	}

	/**
	 * @param lrNo
	 *            the lrNo to set
	 */
	public void setLrNo(String lrNo) {
		this.lrNo = lrNo;
	}

	/**
	 * @return the lrDate
	 */
	public Date getLrDate() {
		return lrDate;
	}

	/**
	 * @param lrDate
	 *            the lrDate to set
	 */
	public void setLrDate(Date lrDate) {
		this.lrDate = lrDate;
	}

	/**
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * @param mode
	 *            the mode to set
	 */
	public void setMode(String mode) {
		this.mode = mode;
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
	 * @return the ddExtra
	 */
	public float getDdExtra() {
		return ddExtra;
	}

	/**
	 * @param ddExtra
	 *            the ddExtra to set
	 */
	public void setDdExtra(float ddExtra) {
		this.ddExtra = ddExtra;
	}

	/**
	 * @return the others
	 */
	public float getOthers() {
		return others;
	}

	/**
	 * @param others
	 *            the others to set
	 */
	public void setOthers(float others) {
		this.others = others;
	}

	/**
	 * @return the demurrage
	 */
	public float getDemurrage() {
		return demurrage;
	}

	/**
	 * @param demurrage
	 *            the demurrage to set
	 */
	public void setDemurrage(float demurrage) {
		this.demurrage = demurrage;
	}

	/**
	 * @return the underCharge
	 */
	public float getUnderCharge() {
		return underCharge;
	}

	/**
	 * @param underCharge
	 *            the underCharge to set
	 */
	public void setUnderCharge(float underCharge) {
		this.underCharge = underCharge;
	}

	/**
	 * @return the postageCharge
	 */
	public float getPostageCharge() {
		return postageCharge;
	}

	/**
	 * @param postageCharge
	 *            the postageCharge to set
	 */
	public void setPostageCharge(float postageCharge) {
		this.postageCharge = postageCharge;
	}

	/**
	 * @return the total
	 */
	public float getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(float total) {
		this.total = total;
	}

	public float getDdcFree() {
		return ddcFree;
	}

	public void setDdcFree(float ddcFree) {
		this.ddcFree = ddcFree;
	}

}
