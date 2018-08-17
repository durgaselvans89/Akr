package hm.akr.dto.printer;

import java.util.Date;

import hm.akr.dto.GMRReportDTO;

/**
 * @version 1.0
 */
public class GMRDTODecorator {
	
	GMRReportDTO dto = null;
	
	public GMRDTODecorator(GMRReportDTO dto)
	{
		this.dto = dto;
	}
	
	
	public String getLrNo() 
	{
		return dto.getLrNo();
	}

	
	/**
	 * @return Returns the BookingDate.
	 */
	public Date getBookingDate() {
		return dto.getBookingDate();
	}

	/**
	 * @return Returns the From_station.
	 */
	public String getFrom_station() {
		return dto.getFrom_station();
	}
	
	/**
	 * @return Returns the To_station.
	 */
	public String getTo_station() {
		return dto.getTo_station();
	}
	
	/**
	 * @return Returns the Consignee_name.
	 */
	public String getConsignee_name() {
		return dto.getConsignee_name();
	}
	
	/**
	 * @return Returns the Consignor_name.
	 */
	public String getConsignor_name() {
		return dto.getConsignor_name();
	}
	
	
	/**
	 * @return Returns the Weight.
	 */
	public Float getWeight() {
		return dto.getWeight();
	}
	
		
	/**
	 * @return Returns the No_of_articles.
	 */
	public int getNo_of_articles() {
		return dto.getNo_of_articles();
	}
	
	/**
	 * @return Returns the VehicleNo.
	 */
	public String getVehicleNo() {
		return dto.getVehicleNo();
	}

	/**
	 * @return Returns the Moved_to.
	 */
	public String getMoved_to() {
		return dto.getMoved_to();
	}
	
}
