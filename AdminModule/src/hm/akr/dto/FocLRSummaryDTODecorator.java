package hm.akr.dto;
import java.io.Serializable;
import java.util.ArrayList;

public class FocLRSummaryDTODecorator implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String slNo = null;

	private String branchCode = null;

	private String stationCode = null;

	private String totalLr = null;

	private String focLr = null;

	private String lrPercent = null;

	private String totalWt = null;

	private String focWt = null;

	private String wtPercent = null;

	/**
	 * 
	 * @return
	 */
	public static ArrayList<FocLRSummaryDTODecorator> Main() {

		return new ArrayList<FocLRSummaryDTODecorator>();
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
	 * @return the branchCode
	 */
	public String getBranchCode() {
		return branchCode;
	}

	/**
	 * @param branchCode the branchCode to set
	 */
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	/**
	 * @return the stationCode
	 */
	public String getStationCode() {
		return stationCode;
	}

	/**
	 * @param stationCode the stationCode to set
	 */
	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	/**
	 * @return the totalLr
	 */
	public String getTotalLr() {
		return totalLr;
	}

	/**
	 * @param totalLr the totalLr to set
	 */
	public void setTotalLr(String totalLr) {
		this.totalLr = totalLr;
	}

	/**
	 * @return the focLr
	 */
	public String getFocLr() {
		return focLr;
	}

	/**
	 * @param focLr the focLr to set
	 */
	public void setFocLr(String focLr) {
		this.focLr = focLr;
	}

	/**
	 * @return the lrPercent
	 */
	public String getLrPercent() {
		return lrPercent;
	}

	/**
	 * @param lrPercent the lrPercent to set
	 */
	public void setLrPercent(String lrPercent) {
		this.lrPercent = lrPercent;
	}

	/**
	 * @return the totalWt
	 */
	public String getTotalWt() {
		return totalWt;
	}

	/**
	 * @param totalWt the totalWt to set
	 */
	public void setTotalWt(String totalWt) {
		this.totalWt = totalWt;
	}

	/**
	 * @return the focWt
	 */
	public String getFocWt() {
		return focWt;
	}

	/**
	 * @param focWt the focWt to set
	 */
	public void setFocWt(String focWt) {
		this.focWt = focWt;
	}

	/**
	 * @return the wtPercent
	 */
	public String getWtPercent() {
		return wtPercent;
	}

	/**
	 * @param wtPercent the wtPercent to set
	 */
	public void setWtPercent(String wtPercent) {
		this.wtPercent = wtPercent;
	}

}
