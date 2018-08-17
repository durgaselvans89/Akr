package hm.akr.dto;
import java.io.Serializable;
import java.util.ArrayList;

public class CancelledLRSummaryDTODecorator implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String slNo = null;

	private String branchCode = null;

	private String stationCode = null;
	
	private String noC = null;
	
	private String totalFt = null;

	private String avgFt = null;

	

	/**
	 * 
	 * @return
	 */
	public static ArrayList<CancelledLRSummaryDTODecorator> Main() {

		return new ArrayList<CancelledLRSummaryDTODecorator>();
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
	 * @return the noC
	 */
	public String getNoC() {
		return noC;
	}



	/**
	 * @param noC the noC to set
	 */
	public void setNoC(String noC) {
		this.noC = noC;
	}



	/**
	 * @return the totalFt
	 */
	public String getTotalFt() {
		return totalFt;
	}



	/**
	 * @param totalFt the totalFt to set
	 */
	public void setTotalFt(String totalFt) {
		this.totalFt = totalFt;
	}



	/**
	 * @return the avgFt
	 */
	public String getAvgFt() {
		return avgFt;
	}



	/**
	 * @param avgFt the avgFt to set
	 */
	public void setAvgFt(String avgFt) {
		this.avgFt = avgFt;
	}

	
}
