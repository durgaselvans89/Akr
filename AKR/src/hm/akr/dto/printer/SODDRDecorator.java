package hm.akr.dto.printer;

import java.util.Date;

import hm.akr.dto.DDRDTO;

/**
 * @version 1.0
 */
public class SODDRDecorator {

	DDRDTO dto = null;
	
	private int sno;
	
	/**
	 * Constructor method
	 * @param dto
	 */
	public SODDRDecorator(DDRDTO dto,int no)
	{
		this.dto = dto;
		this.sno=no;
	}
	

	public String getSno() {
		return String.valueOf(sno);
	}	
	
	
	/**
	 * @return Returns the actualWeight.
	 */
	public Float getActualWeight() 
	{
		return dto.getActualWeight();
	}

	/**
	 * @return Returns the basicFreight.
	 */
	public Float getBasicFreight() {
		return dto.getBasicFreight();
	}

	/**
	 * @return Returns the crDate.
	 */
	public Date getCrDate() {
		return dto.getCrDate();
	}

	/**
	 * @return Returns the crno.
	 */
	public String getCrno() {
		return dto.getCrno();
	}
	
	/**
	 * @return Returns the ddc.
	 */
	public Float getDdc() {
		return dto.getDdc();
	}

	/**
	 * @return Returns the ddExtra.
	 */
	public Float getDdExtra() {
		return dto.getDdExtra();
	}
	
	/**
	 * @return Returns the fromStation.
	 */
	public String getFromStation() {
		return dto.getFromStation();
	}
	
	/**
	 * @return Returns the lrDate.
	 */
	public Date getLrDate() {
		return dto.getLrDate();
	}
	
	/**
	 * @return Returns the lrNo.
	 */
	public String getLrNo() {
		return dto.getLrNo();
	}
	
	/**
	 * @return Returns the lrType.
	 */
	public String getLrType() {
		return dto.getLrType();
	}
	
	/**
	 * @return Returns the noOfArticles.
	 */
	public int getNoOfArticles() {
		return dto.getNoOfArticles();
	}
	
	/**
	 * @return Returns the others.
	 */
	public Float getOthers() {
		return dto.getOthers();
	}
	
	public Float getTotal() {
		return dto.getTotal();
	}
}
