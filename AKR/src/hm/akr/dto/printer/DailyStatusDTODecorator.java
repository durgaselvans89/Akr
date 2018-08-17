/**
 * 
 */
package hm.akr.dto.printer;

import hm.akr.dto.DailyStatusDTO;

/**
 * 
 *
 */
public class DailyStatusDTODecorator {

	DailyStatusDTO status = null;
	int sno = 0;
	
	/**
	 * Constructor Method
	 * @param statusDTO
	 */
	public DailyStatusDTODecorator(DailyStatusDTO statusDTO,int sno) 
	{
		status = statusDTO;
		this.sno=sno;
	}

	/**
	 * @return
	 * @see hm.akr.dto.DailyStatusDTO#getBilling_count()
	 */
	public Long getBilling_count() {
		return new Long(status.getBilling_count());
	}

	/**
	 * @return
	 * @see hm.akr.dto.DailyStatusDTO#getCr_count()
	 */
	public Long getCr_count() {
		return new Long(status.getCr_count());
	}

	/**
	 * @return
	 * @see hm.akr.dto.DailyStatusDTO#getGMRintime_count()
	 */
	public Long getGMRintime_count() {
		return new Long(status.getGMRintime_count());
	}

	/**
	 * @return
	 * @see hm.akr.dto.DailyStatusDTO#getGMRouttime_count()
	 */
	public Long getGMRouttime_count() {
		return new Long(status.getGMRouttime_count());
	}

	/**
	 * @return
	 * @see hm.akr.dto.DailyStatusDTO#getName()
	 */
	public String getName() {
		return status.getName();
	}

	/**
	 * @return
	 * @see hm.akr.dto.DailyStatusDTO#getPaid_count()
	 */
	public Long getPaid_count() {
		return new Long(status.getPaid_count());
	}

	/**
	 * @return
	 * @see hm.akr.dto.DailyStatusDTO#getStation_code()
	 */
	public String getStation_code() {
		return status.getStation_code();
	}
	
	public String getBranch_code()
	{
		return status.getBranch_code();
	}

	/**
	 * @return
	 * @see hm.akr.dto.DailyStatusDTO#getTopay_count()
	 */
	public Long getTopay_count() {
		return new Long(status.getTopay_count());
	}

	/**
	 * @return
	 * @see hm.akr.dto.DailyStatusDTO#getDs_no()
	 */
	public int  getDs_no() {
		return sno;
	}
	
	
	
}
