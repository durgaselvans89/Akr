package hm.akr.dto;

import java.io.Serializable;

/**
 * @version 1.0 DTO class for Vehicle
 */
public class VehicleDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String vehicle_number = null;

	private String vehicle_model = null;

	private String vehicle_type = null;

	private String branch_associated = null;

	private String owner = null;

	private int odometer_starting_reading = 0;

	/**
	 * Constructor method
	 */
	public VehicleDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return Returns the branch_associated.
	 */
	public String getBranch_associated() {
		return branch_associated;
	}

	/**
	 * @param branch_associated
	 *            The branch_associated to set.
	 */
	public void setBranch_associated(String branch_associated) {
		this.branch_associated = branch_associated;
	}

	/**
	 * @return Returns the odometer_starting_reading.
	 */
	public int getOdometer_starting_reading() {
		return odometer_starting_reading;
	}

	/**
	 * @param odometer_starting_reading
	 *            The odometer_starting_reading to set.
	 */
	public void setOdometer_starting_reading(int odometer_starting_reading) {
		this.odometer_starting_reading = odometer_starting_reading;
	}

	/**
	 * @return Returns the owner.
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * @param owner
	 *            The owner to set.
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * @return Returns the vehicle_model.
	 */
	public String getVehicle_model() {
		return vehicle_model;
	}

	/**
	 * @param vehicle_model
	 *            The vehicle_model to set.
	 */
	public void setVehicle_model(String vehicle_model) {
		this.vehicle_model = vehicle_model;
	}

	/**
	 * @return Returns the vehicle_number.
	 */
	public String getVehicle_number() {
		return vehicle_number;
	}

	/**
	 * @param vehicle_number
	 *            The vehicle_number to set.
	 */
	public void setVehicle_number(String vehicle_number) {
		this.vehicle_number = vehicle_number;
	}

	/**
	 * @return Returns the vehicle_type.
	 */
	public String getVehicle_type() {
		return vehicle_type;
	}

	/**
	 * @param vehicle_type
	 *            The vehicle_type to set.
	 */
	public void setVehicle_type(String vehicle_type) {
		this.vehicle_type = vehicle_type;
	}

}
