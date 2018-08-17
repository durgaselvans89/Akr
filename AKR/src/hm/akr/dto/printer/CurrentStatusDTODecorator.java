package hm.akr.dto.printer;
import java.util.Date;

import hm.akr.dto.CurrentStatusDTO;

public class CurrentStatusDTODecorator {
	CurrentStatusDTO status = null;
	int sno = 0;
	
	/**
	 * Constructor Method
	 * @param statusDTO
	 */
	public CurrentStatusDTODecorator(CurrentStatusDTO statusDTO,int sno) 
	{
		this.status = statusDTO;
		this.sno=sno;
	}

	/**
	 * @return
	 * @see hm.akr.dto.DailyStatusDTO#getBilling_count()
	 */

	public String getLr_no() {
	
		return status.getLr_no();
	}
	
	public Date getLrdate() {
		
		return status.getLrdate();
	}
	
	public String getLr_status() {
	
		return status.getLr_status();
	}
	
	public String getStation_code() {
	
		return status.getStation_code();
	}
	
	public String getFrom() {
	
		return status.getFrom();
	}
	
	public String getTo() {
		
		return status.getTo();
	}
	
	public int getNo_of_articles() {
	
		return status.getNo_of_articles();
	}
	
	public float getActual_weight() {
		
		return status.getActual_weight();
	}
	
	public String getConsignorName() {
		
		return status.getConsignorName();
	}
	
	public String getConsigneeName() {
		
		return status.getConsigneeName();
	}
	
	public Date getOutTimeDate() {
	
		return status.getOutTimeDate();
	}
	
	public float getDdc() {
		
		return status.getDdc();
	}
	
	public float getTotal() {
		
		return status.getTotal();
		
	}
	
	public float getArticle_value() {
		
		return status.getArticle_value();
	}
}
