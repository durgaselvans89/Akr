/**
 * 
 */
package hm.akr.dto;

import java.io.Serializable;

/**
 * @author user
 *
 */
public class BusinessPerformanceDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String station = null;
	
	private int tbl_index = 0;
	
	private int count = 0;
	
	private float basic_freight = 0;
	
	private float total_freight = 0;
	
	private float actual_weight = 0;
	
	private float charged_weight = 0;
	
	private int noa = 0;
	
	private int high_count = 0;
	
	private float high_basic_freight = 0;
	
	private float high_total_freight = 0;
	
	private float high_actual_weight = 0;
	
	private float high_charged_weight = 0;
	
	private int high_noa = 0;
	
	private int working_days = 0;
		
	
	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return the basic_freight
	 */
	public float getBasic_freight() {
		return basic_freight;
	}

	/**
	 * @param basic_freight the basic_freight to set
	 */
	public void setBasic_freight(float basic_freight) {
		this.basic_freight = basic_freight;
	}

	/**
	 * @return the total_freight
	 */
	public float getTotal_freight() {
		return total_freight;
	}

	/**
	 * @param total_freight the total_freight to set
	 */
	public void setTotal_freight(float total_freight) {
		this.total_freight = total_freight;
	}

	/**
	 * @return the actual_weight
	 */
	public float getActual_weight() {
		return actual_weight;
	}

	/**
	 * @param actual_weight the actual_weight to set
	 */
	public void setActual_weight(float actual_weight) {
		this.actual_weight = actual_weight;
	}

	/**
	 * @return the charged_weight
	 */
	public float getCharged_weight() {
		return charged_weight;
	}

	/**
	 * @param charged_weight the charged_weight to set
	 */
	public void setCharged_weight(float charged_weight) {
		this.charged_weight = charged_weight;
	}

	/**
	 * @return the noa
	 */
	public int getNoa() {
		return noa;
	}

	/**
	 * @param noa the noa to set
	 */
	public void setNoa(int noa) {
		this.noa = noa;
	}

	/**
	 * @return the station
	 */
	public String getStation() {
		return station;
	}

	/**
	 * @param station the station to set
	 */
	public void setStation(String station) {
		this.station = station;
	}

	/**
	 * @return the tbl_index
	 */
	public int getTbl_index() {
		return tbl_index;
	}

	/**
	 * @param tbl_index the tbl_index to set
	 */
	public void setTbl_index(int tbl_index) {
		this.tbl_index = tbl_index;
	}

	/**
	 * @return the high_count
	 */
	public int getHigh_count() {
		return high_count;
	}

	/**
	 * @param high_count the high_count to set
	 */
	public void setHigh_count(int high_count) {
		this.high_count = high_count;
	}

	/**
	 * @return the high_basic_freight
	 */
	public float getHigh_basic_freight() {
		return high_basic_freight;
	}

	/**
	 * @param high_basic_freight the high_basic_freight to set
	 */
	public void setHigh_basic_freight(float high_basic_freight) {
		this.high_basic_freight = high_basic_freight;
	}

	/**
	 * @return the high_total_freight
	 */
	public float getHigh_total_freight() {
		return high_total_freight;
	}

	/**
	 * @param high_total_freight the high_total_freight to set
	 */
	public void setHigh_total_freight(float high_total_freight) {
		this.high_total_freight = high_total_freight;
	}

	/**
	 * @return the high_actual_weight
	 */
	public float getHigh_actual_weight() {
		return high_actual_weight;
	}

	/**
	 * @param high_actual_weight the high_actual_weight to set
	 */
	public void setHigh_actual_weight(float high_actual_weight) {
		this.high_actual_weight = high_actual_weight;
	}

	/**
	 * @return the high_charged_weight
	 */
	public float getHigh_charged_weight() {
		return high_charged_weight;
	}

	/**
	 * @param high_charged_weight the high_charged_weight to set
	 */
	public void setHigh_charged_weight(float high_charged_weight) {
		this.high_charged_weight = high_charged_weight;
	}

	/**
	 * @return the high_noa
	 */
	public int getHigh_noa() {
		return high_noa;
	}

	/**
	 * @param high_noa the high_noa to set
	 */
	public void setHigh_noa(int high_noa) {
		this.high_noa = high_noa;
	}

	/**
	 * @return the working_days
	 */
	public int getWorking_days() {
		return working_days;
	}

	/**
	 * @param working_days the working_days to set
	 */
	public void setWorking_days(int working_days) {
		this.working_days = working_days;
	}
			

}
