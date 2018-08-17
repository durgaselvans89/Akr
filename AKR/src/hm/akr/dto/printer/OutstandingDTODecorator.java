package hm.akr.dto.printer;


import hm.akr.dto.OutstandingDTO;

public class OutstandingDTODecorator {
	OutstandingDTO dto = null;
	int sno = 0;
	public OutstandingDTODecorator(OutstandingDTO dto,int sno)
	{
		this.sno=sno;
		this.dto = dto;
	}
	
	
	
	
	public String getBranchcode() {
		return dto.getBranchcode();
	}

	


	public String getStationname() {
		return dto.getStationname();
	}

	

	public String getStationcode() {
		return dto.getStationcode();
	}

	

	public Float getTpdd() {
		return dto.getTpdd();
	}


	public Float getTpod() {
		return dto.getTpod();
	}

	

	public Float getPdbooking() {
		return dto.getPdbooking();
	}


	public Float getTotal() {
		return dto.getTotal();
	}

	
	
	

}
