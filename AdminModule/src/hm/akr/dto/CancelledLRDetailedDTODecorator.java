package hm.akr.dto;
import java.io.Serializable;
import java.util.ArrayList;

public class CancelledLRDetailedDTODecorator implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String slNo = null;

	private String lrNo = null;

	private String lrDate = null;
	
	private String lrType = null;
	
	private String from = null;

	private String to = null;

	private String noa = null;

	private String artValue = null;
	
	private String bft = null;
	
	private String cc = null;
	
	private String iec = null;
	
	private String others = null;
	
	private String dd = null;
	
	private String total = null;

	/**
	 * 
	 * @return
	 */
	public static ArrayList<CancelledLRDetailedDTODecorator> Main() {

		return new ArrayList<CancelledLRDetailedDTODecorator>();
	}

	/**
	 * @return the slNo
	 */
	public String getSlNo() {
		return slNo;
	}

	/**
	 * @param slNo the slNo to set
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
	 * @param lrNo the lrNo to set
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
	 * @param lrDate the lrDate to set
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
	 * @param lrType the lrType to set
	 */
	public void setLrType(String lrType) {
		this.lrType = lrType;
	}

	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from the from to set
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
	 * @param to the to to set
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
	 * @param noa the noa to set
	 */
	public void setNoa(String noa) {
		this.noa = noa;
	}

	/**
	 * @return the artValue
	 */
	public String getArtValue() {
		return artValue;
	}

	/**
	 * @param artValue the artValue to set
	 */
	public void setArtValue(String artValue) {
		this.artValue = artValue;
	}

	/**
	 * @return the bft
	 */
	public String getBft() {
		return bft;
	}

	/**
	 * @param bft the bft to set
	 */
	public void setBft(String bft) {
		this.bft = bft;
	}

	/**
	 * @return the cc
	 */
	public String getCc() {
		return cc;
	}

	/**
	 * @param cc the cc to set
	 */
	public void setCc(String cc) {
		this.cc = cc;
	}

	/**
	 * @return the iec
	 */
	public String getIec() {
		return iec;
	}

	/**
	 * @param iec the iec to set
	 */
	public void setIec(String iec) {
		this.iec = iec;
	}

	/**
	 * @return the others
	 */
	public String getOthers() {
		return others;
	}

	/**
	 * @param others the others to set
	 */
	public void setOthers(String others) {
		this.others = others;
	}

	/**
	 * @return the dd
	 */
	public String getDd() {
		return dd;
	}

	/**
	 * @param dd the dd to set
	 */
	public void setDd(String dd) {
		this.dd = dd;
	}

	/**
	 * @return the total
	 */
	public String getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(String total) {
		this.total = total;
	}

}
