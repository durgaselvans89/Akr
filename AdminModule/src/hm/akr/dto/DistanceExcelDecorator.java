package hm.akr.dto;

import java.io.Serializable;

public class DistanceExcelDecorator implements Serializable{

	private static final long serialVersionUID = 1L;
		
	private String stationName;
	
	private int distance = 0;
	
	private float cardRate = 0;
	
	private int serial = 1;
	
	private DistanceListDTO dto;
	
	public DistanceExcelDecorator(DistanceListDTO dto, int serial) {		
		super();
		this.dto = dto;
		this.serial = serial;
	}

	/**
	 * @return the stationName
	 */
	public String getStationName() {
		return dto.getDestStation();
	}

	
	/**
	 * @return the distance
	 */
	public int getDistance() {
		return dto.getDistance();
	}

	
	/**
	 * @return the cardRate
	 */
	public float getCardRate() {
		return dto.getCardRate();
	}

	
	/**
	 * @return the serial
	 */
	public int getSerial() {
		return serial;
	}

	
	/**
	 * @return the dto
	 */
	public DistanceListDTO getDto() {
		return dto;
	}

	
}
