/**
 * 
 */
package hm.akr.dto;

import java.io.Serializable;

/**
 * @author Anand
 * 
 */
public class MarketVehicleDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String from_station = null;

	private String to_station = null;

	private String vehicle_name = null;

	private int no_vehicle = 0;

	private float amount = 0;

	public String getFrom_station() {
		return from_station;
	}

	public void setFrom_station(String from_station) {
		this.from_station = from_station;
	}

	public String getTo_station() {
		return to_station;
	}

	public void setTo_station(String to_station) {
		this.to_station = to_station;
	}

	public String getVehicle_name() {
		return vehicle_name;
	}

	public void setVehicle_name(String vehicle_name) {
		this.vehicle_name = vehicle_name;
	}

	public int getNo_vehicle() {
		return no_vehicle;
	}

	public void setNo_vehicle(int no_vehicle) {
		this.no_vehicle = no_vehicle;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

}
