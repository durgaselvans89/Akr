package hm.akr.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @version 1.0 DTO class for GMR
 */
public class GMRDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String lrNumber = null;
	
	private String lastInwardTime = null;

	private String stationCode = null;

	private String vehicleNumber = null;

	private String driverName = null;

	private String dispatchTo = null;

	private float vehicleRate = 0;

	private String modelNo = null;

	private String vehicleName = null;
	
	private String vehiclePhone = null;
	
	private boolean isMarketVehile = false;
	
	// For SMS
	
	private String cnorPhone = "";
	
	private String cneePhone = "";
	
	private String cnorName = "";
	
	private String cneeName = "";
	
	private Date lrDate = null;
	
	private String destinationStn = "";
	
	private String destinationPhone = "";
	
	private String lrType = "";
	
	private String lrTotal = "";
	
	private SMSDTO smsDto = null;

	private int rate_type = 0;
	
	private int smsNotify = 0;
	
	private String crNo = "";
	
	private float crTotal = 0;
	
	public boolean isMarketVehile() {
		return isMarketVehile;
	}

	public void setMarketVehile(boolean isMarketVehile) {
		this.isMarketVehile = isMarketVehile;
	}

	/**
	 * Constructor method
	 */
	public GMRDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @return
	 */
	public String getDriverName() {
		return driverName;
	}

	/**
	 * 
	 * @param driverName
	 */
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	/**
	 * 
	 * @return
	 */
	public String getLrNumber() {
		return lrNumber;
	}

	/**
	 * 
	 * @param lrNumber
	 */
	public void setLrNumber(String lrNumber) {
		this.lrNumber = lrNumber;
	}

	public String getStationCode() {
		return stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public String getDispatchTo() {
		return dispatchTo;
	}

	public void setDispatchTo(String dispatchTo) {
		this.dispatchTo = dispatchTo;
	}

	public float getVehicleRate() {
		return vehicleRate;
	}

	public void setVehicleRate(float vehicleRate) {
		this.vehicleRate = vehicleRate;
	}

	public String getModelNo() {
		return modelNo;
	}

	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public String getLastInwardTime() {
		return lastInwardTime;
	}

	public void setLastInwardTime(String lastInwardTime) {
		this.lastInwardTime = lastInwardTime;
	}

	public String getVehiclePhone() {
		return vehiclePhone;
	}

	public void setVehiclePhone(String vehiclePhone) {
		this.vehiclePhone = vehiclePhone;
	}

	/**
	 * @return the cnorPhone
	 */
	public String getCnorPhone() {
		return cnorPhone;
	}

	/**
	 * @param cnorPhone the cnorPhone to set
	 */
	public void setCnorPhone(String cnorPhone) {
		this.cnorPhone = cnorPhone;
	}

	/**
	 * @return the cneePhone
	 */
	public String getCneePhone() {
		return cneePhone;
	}

	/**
	 * @param cneePhone the cneePhone to set
	 */
	public void setCneePhone(String cneePhone) {
		this.cneePhone = cneePhone;
	}

	/**
	 * @return the cnorName
	 */
	public String getCnorName() {
		return cnorName;
	}

	/**
	 * @param cnorName the cnorName to set
	 */
	public void setCnorName(String cnorName) {
		this.cnorName = cnorName;
	}

	/**
	 * @return the cneeName
	 */
	public String getCneeName() {
		return cneeName;
	}

	/**
	 * @param cneeName the cneeName to set
	 */
	public void setCneeName(String cneeName) {
		this.cneeName = cneeName;
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

	/**
	 * @return the destinationStn
	 */
	public String getDestinationStn() {
		return destinationStn;
	}

	/**
	 * @param destinationStn the destinationStn to set
	 */
	public void setDestinationStn(String destinationStn) {
		this.destinationStn = destinationStn;
	}

	/**
	 * @return the destinationPhone
	 */
	public String getDestinationPhone() {
		return destinationPhone;
	}

	/**
	 * @param destinationPhone the destinationPhone to set
	 */
	public void setDestinationPhone(String destinationPhone) {
		this.destinationPhone = destinationPhone;
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
	 * @return the lrTotal
	 */
	public String getLrTotal() {
		return lrTotal;
	}

	/**
	 * @param lrTotal the lrTotal to set
	 */
	public void setLrTotal(String lrTotal) {
		this.lrTotal = lrTotal;
	}

	/**
	 * @return the smsDto
	 */
	public SMSDTO getSmsDto() {
		return smsDto;
	}

	/**
	 * @param smsDto the smsDto to set
	 */
	public void setSmsDto(SMSDTO smsDto) {
		this.smsDto = smsDto;
	}

	/**
	 * @return the rate_type
	 */
	public int getRate_type() {
		return rate_type;
	}

	/**
	 * @param rate_type the rate_type to set
	 */
	public void setRate_type(int rate_type) {
		this.rate_type = rate_type;
	}

	/**
	 * @return the smsNotify
	 */
	public int getSmsNotify() {
		return smsNotify;
	}

	/**
	 * @param smsNotify the smsNotify to set
	 */
	public void setSmsNotify(int smsNotify) {
		this.smsNotify = smsNotify;
	}

	/**
	 * @return the crNo
	 */
	public String getCrNo() {
		return crNo;
	}

	/**
	 * @param crNo the crNo to set
	 */
	public void setCrNo(String crNo) {
		this.crNo = crNo;
	}

	/**
	 * @return the crTotal
	 */
	public float getCrTotal() {
		return crTotal;
	}

	/**
	 * @param crTotal the crTotal to set
	 */
	public void setCrTotal(float crTotal) {
		this.crTotal = crTotal;
	}
	

}
