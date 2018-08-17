package hm.akr.dto;

import java.io.Serializable;
import java.util.Date;

public class DRSAttendanceDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date drs_date = null;

	private String station_code = null;

	private int count = 0;

	private String status = null;

	/**
	 * @return the drs_date
	 */
	public Date getDrs_date() {
		return drs_date;
	}

	/**
	 * @param drs_date
	 *            the drs_date to set
	 */
	public void setDrs_date(Date drs_date) {
		this.drs_date = drs_date;
	}

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
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
