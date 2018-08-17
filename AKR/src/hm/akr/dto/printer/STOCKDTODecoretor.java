package hm.akr.dto.printer;

import hm.akr.dto.GMRInTimeDTO;

import java.util.Date;



public class STOCKDTODecoretor {

	private GMRInTimeDTO dto = null;
	int sno =0;
	
	
	public STOCKDTODecoretor(GMRInTimeDTO intimedto,int sno) {
		this.dto = intimedto;
		this.sno=sno;
	}

	/**
	 * @return the lr_no
	 */
	public String getLr_no() {
		return dto.getLr_no();
	}

	public int getSno() {
		return this.sno;
	}
	/**
	 * @return the vehicle_no
	 */
	public String getVehicle_no() {
		return dto.getVehicle_no();
	}

	/**
	 * @return the from
	 */
	public String getFrom() {
		return dto.getFrom();
	}

	/**
	 * @return the to
	 */
	public String getTo() {
		return dto.getTo();
	}

	/**
	 * @return the consignor_name
	 */
	public String getConsignor_name() {
		return dto.getConsignor_name();
	}

	/**
	 * @return the consignee_name
	 */
	public String getConsignee_name() {
		return dto.getConsignee_name();
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return dto.getDate();
	}

	/**
	 * @return the sent_date
	 */
	public Date getSent_date() {
		return dto.getSent_date();
	}

	/**
	 * @return the today_date
	 */
	public Date getToday_date() {
		return dto.getToday_date();
	}

	/**
	 * @return the total
	 */
	public float getTotal() {
		return dto.getTotal();
	}

	/**
	 * @return the lr_type
	 */
	public String getLr_type() {
		return dto.getLr_type();
	}

	/**
	 * 
	 * @return the ddc
	 */
	public float getDdc() {
		return dto.getDdc();
	}

	/**
	 * @return the no_of_articles
	 */
	public int getNo_of_articles() {
		return dto.getNo_of_articles();
	}

	/**
	 * @return the actual_weight
	 */
	public float getActual_weight() {
		return dto.getActual_weight();
	}

	/**
	 * @return the dto
	 */
	public GMRInTimeDTO getDto() {
		return dto;
	}

	/**
	 * @return the article_value
	 */
	public String getArticle_value() {
		return dto.getArticle_value();
	}

	/**
	 * @return the article_type
	 */
	public String getArticle_type() {
		return dto.getArticle_type();
	}

	/**
	 * @return the inward_days
	 */
	public String getInward_days() {
		return dto.getInward_days();
	}

	/**
	 * @return the sent_days
	 */
	public String getSent_days() {
		return dto.getSent_days();
	}

}
