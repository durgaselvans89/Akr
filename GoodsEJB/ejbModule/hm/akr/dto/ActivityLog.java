package hm.akr.dto;

import java.io.Serializable;
import java.util.Date;

public class ActivityLog implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String activity = null;
	
	private Date activityTime = null;
	
	private String stationCode = null;
	
	private String toStationCode = null;
	
	private String crno = null;
	
	private String vehicleNo = null;
	
	private String driverName = null;
	
	private String[] phone_no = null;
	
	private Date cancelledDeliverTime = null;

	/**
	 * @return the activity
	 */
	public String getActivity() {
		return activity;
	}

	/**
	 * @param activity the activity to set
	 */
	public void setActivity(String activity) {
		this.activity = activity;
	}

	/**
	 * @return the activityTime
	 */
	public Date getActivityTime() {
		return activityTime;
	}

	/**
	 * @param activityTime the activityTime to set
	 */
	public void setActivityTime(Date activityTime) {
		this.activityTime = activityTime;
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
	 * @return the toStationCode
	 */
	public String getToStationCode() {
		return toStationCode;
	}

	/**
	 * @param toStationCode the toStationCode to set
	 */
	public void setToStationCode(String toStationCode) {
		this.toStationCode = toStationCode;
	}

	/**
	 * @return the crno
	 */
	public String getCrno() {
		return crno;
	}

	/**
	 * @param crno the crno to set
	 */
	public void setCrno(String crno) {
		this.crno = crno;
	}

	/**
	 * @return the vehicleNo
	 */
	public String getVehicleNo() {
		return vehicleNo;
	}

	/**
	 * @param vehicleNo the vehicleNo to set
	 */
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	/**
	 * @return the driverName
	 */
	public String getDriverName() {
		return driverName;
	}

	/**
	 * @param driverName the driverName to set
	 */
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	/**
	 * @return the phone_no
	 */
	public String[] getPhone_no() {
		return phone_no;
	}

	/**
	 * @param phone_no the phone_no to set
	 */
	public void setPhone_no(String[] phone_no) {
		this.phone_no = phone_no;
	}

	/**
	 * @return the cancelledDeliverTime
	 */
	public Date getCancelledDeliverTime() {
		return cancelledDeliverTime;
	}

	/**
	 * @param cancelledDeliverTime the cancelledDeliverTime to set
	 */
	public void setCancelledDeliverTime(Date cancelledDeliverTime) {
		this.cancelledDeliverTime = cancelledDeliverTime;
	}
	
	
	
	
}
