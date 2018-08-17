package hm.akr.dto;

import java.io.Serializable;

public class CardRateDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String station_name = null;

	private String station_code = null;

	private int distance = 0;

	private float card_rate = 0;

	public String getStation_name() {
		return station_name;
	}

	public void setStation_name(String station_name) {
		this.station_name = station_name;
	}

	public String getStation_code() {
		return station_code;
	}

	public void setStation_code(String station_code) {
		this.station_code = station_code;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public float getCard_rate() {
		return card_rate;
	}

	public void setCard_rate(float card_rate) {
		this.card_rate = card_rate;
	}

}
