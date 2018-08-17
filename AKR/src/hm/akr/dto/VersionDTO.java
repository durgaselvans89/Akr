package hm.akr.dto;

import java.io.Serializable;
import java.util.Date;

public class VersionDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String branch_code = null;

	private String station_code = null;

	private String version_id = null;
	
	private Date updated_date = null;

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
	 * @return the version_id
	 */
	public String getVersion_id() {
		return version_id;
	}

	/**
	 * @param version_id
	 *            the version_id to set
	 */
	public void setVersion_id(String version_id) {
		this.version_id = version_id;
	}

	public String getBranch_code() {
		return branch_code;
	}

	public void setBranch_code(String branch_code) {
		this.branch_code = branch_code;
	}

	/**
	 * @return the updated_date
	 */
	public Date getUpdated_date() {
		return updated_date;
	}

	/**
	 * @param updated_date the updated_date to set
	 */
	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}

}
