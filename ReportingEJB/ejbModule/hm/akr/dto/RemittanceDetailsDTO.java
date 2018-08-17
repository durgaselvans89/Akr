package hm.akr.dto;

import java.io.Serializable;

public class RemittanceDetailsDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String lrno = null;

	private float total = 0;
	
	private boolean status = false;

	/**
	 * @return the lrno
	 */
	public String getLrno() {
		return lrno;
	}

	/**
	 * @param lrno the lrno to set
	 */
	public void setLrno(String lrno) {
		this.lrno = lrno;
	}

	/**
	 * @return the total
	 */
	public float getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(float total) {
		this.total = total;
	}

	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
	

}
