package hm.akr.dto;

import java.io.Serializable;

public class OutstandingDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String sno = null;
	
	private String branchcode =null;
	
	private String stationname = null;
	
	private String stationcode = null;
	
	private Float tpdd = null;
	
	private Float tpod = null;
	
	private Float pdbooking = null;
	
	private Float total = null;

	public String getBranchcode() {
		return branchcode;
	}
	
	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	public String getStationname() {
		return stationname;
	}

	public void setStationname(String stationname) {
		this.stationname = stationname;
	}

	public String getStationcode() {
		return stationcode;
	}
	
	public void setBranchcode(String branchcode) {
		this.branchcode = branchcode;
	}
	
	public void setStationcode(String stationcode) {
		this.stationcode = stationcode;
	}

	public Float getTpdd() {
		return tpdd;
	}
	
	public void setTpdd(float tpdd)
	{		// TODO Auto-generated method stub
		this.tpdd = tpdd;
	}

	

	public Float getTpod() {
		return tpod;
	}

	public void setTpod(Float tpod) {
		this.tpod = tpod;
	}

	public Float getPdbooking() {
		return pdbooking;
	}

	public void setPdbooking(Float pdbooking) {
		this.pdbooking = pdbooking;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	

	


}
