package hm.akr.dto;
import java.io.Serializable;
import java.util.ArrayList;

public class FocLRDetailedDTODecorator implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String slNo = null;

	private String lrNo = null;

	private String lrDate = null;

	private String from = null;

	private String to = null;

	private String noa = null;

	private String crgWt = null;

	private String artType = null;

	private String artValue = null;

	/**
	 * 
	 * @return
	 */
	public static ArrayList<FocLRDetailedDTODecorator> Main() {

		return new ArrayList<FocLRDetailedDTODecorator>();
	}

	/**
	 * @return the slNo
	 */
	public String getSlNo() {
		return slNo;
	}

	/**
	 * @param slNo
	 *            the slNo to set
	 */
	public void setSlNo(String slNo) {
		this.slNo = slNo;
	}

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
	public String getLrDate() {
		return lrDate;
	}

	/**
	 * @param lrDate
	 *            the lrDate to set
	 */
	public void setLrDate(String lrDate) {
		this.lrDate = lrDate;
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

	/**
	 * @return the noa
	 */
	public String getNoa() {
		return noa;
	}

	/**
	 * @param noa
	 *            the noa to set
	 */
	public void setNoa(String noa) {
		this.noa = noa;
	}

	/**
	 * @return the crgWt
	 */
	public String getCrgWt() {
		return crgWt;
	}

	/**
	 * @param crgWt
	 *            the crgWt to set
	 */
	public void setCrgWt(String crgWt) {
		this.crgWt = crgWt;
	}

	/**
	 * @return the artType
	 */
	public String getArtType() {
		return artType;
	}

	/**
	 * @param artType
	 *            the artType to set
	 */
	public void setArtType(String artType) {
		this.artType = artType;
	}

	/**
	 * @return the artValue
	 */
	public String getArtValue() {
		return artValue;
	}

	/**
	 * @param artValue
	 *            the artValue to set
	 */
	public void setArtValue(String artValue) {
		this.artValue = artValue;
	}

}
