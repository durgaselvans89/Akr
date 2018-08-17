package hm.akr.dto;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * @version 1.0 DTO class for Booking
 */
public class OpenLrDTODecorator implements Serializable {

	private static final long serialVersionUID = 1L;

	private String slNo = null;

	private String branchCode = null;

	private String from = null;

	private String to = null;

	private String lrNo = null;

	private String lrDate = null;

	private String lrType = null;

	private String cardRate = null;

	private String bft = null;

	private String ccc = null;

	private String ddc = null;

	private String others = null;

	private String total = null;

	private String discount = null;

	private String crgWt = null;

	private String consignorName = null;

	private String consigneeName = null;

	
	/**
	 * 
	 * @return
	 */
	public static ArrayList<OpenLrDTODecorator> Main() {

		return new ArrayList<OpenLrDTODecorator>();
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
	 * @return the branchCode
	 */
	public String getBranchCode() {
		return branchCode;
	}

	/**
	 * @param branchCode
	 *            the branchCode to set
	 */
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
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
	 * @return the lrType
	 */
	public String getLrType() {
		return lrType;
	}

	/**
	 * @param lrType
	 *            the lrType to set
	 */
	public void setLrType(String lrType) {
		this.lrType = lrType;
	}

	/**
	 * @return the cardRate
	 */
	public String getCardRate() {
		return cardRate;
	}

	/**
	 * @param cardRate
	 *            the cardRate to set
	 */
	public void setCardRate(String cardRate) {
		this.cardRate = cardRate;
	}

	/**
	 * @return the bft
	 */
	public String getBft() {
		return bft;
	}

	/**
	 * @param bft
	 *            the bft to set
	 */
	public void setBft(String bft) {
		this.bft = bft;
	}

	/**
	 * @return the ccc
	 */
	public String getCcc() {
		return ccc;
	}

	/**
	 * @param ccc
	 *            the ccc to set
	 */
	public void setCcc(String ccc) {
		this.ccc = ccc;
	}

	/**
	 * @return the ddc
	 */
	public String getDdc() {
		return ddc;
	}

	/**
	 * @param ddc
	 *            the ddc to set
	 */
	public void setDdc(String ddc) {
		this.ddc = ddc;
	}

	/**
	 * @return the others
	 */
	public String getOthers() {
		return others;
	}

	/**
	 * @param others
	 *            the others to set
	 */
	public void setOthers(String others) {
		this.others = others;
	}

	/**
	 * @return the total
	 */
	public String getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(String total) {
		this.total = total;
	}

	/**
	 * @return the discount
	 */
	public String getDiscount() {
		return discount;
	}

	/**
	 * @param discount
	 *            the discount to set
	 */
	public void setDiscount(String discount) {
		this.discount = discount;
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
	 * @return the consignorName
	 */
	public String getConsignorName() {
		return consignorName;
	}

	/**
	 * @param consignorName
	 *            the consignorName to set
	 */
	public void setConsignorName(String consignorName) {
		this.consignorName = consignorName;
	}

	/**
	 * @return the consigneeName
	 */
	public String getConsigneeName() {
		return consigneeName;
	}

	/**
	 * @param consigneeName
	 *            the consigneeName to set
	 */
	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}

}
