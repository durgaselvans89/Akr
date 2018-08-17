package hm.akr.dto;

import hm.akr.common.BeanUtil;
import hm.akr.dto.ExcelDTO;
import hm.akr.dto.StationsDTO;

import java.io.Serializable;

import javax.naming.NamingException;

public class QuotationExcelDecorator implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String stationCode;
	
	private String artName;
	
	private String artName2 = null;
	
	private String artName3 = null;
	
	private String artName4 = null;
	
	private String artName5 = null;
	
	private float bpi;
	
	private float bpi2;
	
	private float bpi3;
	
	private float bpi4;
	
	private float bpi5;
	
	private ExcelDTO dto;
	
	BeanUtil beanutil = null;
	
	private int serial = 1;

	

	
	public QuotationExcelDecorator(ExcelDTO excelDTO, int serial) {
		this.dto = excelDTO;
		this.serial = serial;
		try {
			beanutil = new BeanUtil();
		} catch (NamingException e) {			
			e.printStackTrace();
		} catch (Exception e) {		
			e.printStackTrace();
		}
	}

	/**
	 * @return the stationCode
	 */
	public String getStationCode() {
		return getStationName(dto.getStationCode());
	}	

	/**
	 * @return the bpi
	 */
	public float getBpi() {
		return dto.getBpi();
	}
	
	/**
	 * @return the dto
	 */
	public ExcelDTO getDto() {
		return dto;
	}
	
	/**
	 * @return the serial
	 */
	public int getSerial() {
		return serial;
	}
	
	private String getStationName(String stnCode) {
		int size = 0;			
		try {
			StationsDTO[] stations = beanutil.getAvailableStations();
			size = stations.length;
			if (size > 0) {
				for (int i = 0; i < size; i++) {
					if (stations[i].getId().equals(stnCode)) {
						return stations[i].getName();
					}
				}
			}
		} catch (Exception e) {				
			e.printStackTrace();
		}
	
	return null;
	}

	/**
	 * @return the bpi2
	 */
	public float getBpi2() {
		return dto.getBpi2();
	}	

	/**
	 * @return the bpi3
	 */
	public float getBpi3() {
		return dto.getBpi3();
	}	

	/**
	 * @return the bpi4
	 */
	public float getBpi4() {
		return dto.getBpi4();
	}	

	/**
	 * @return the bpi5
	 */
	public float getBpi5() {
		return dto.getBpi5();
	}

	/**
	 * @return the artName
	 */
	public String getArtName() {
		return dto.getArtName();
	}

	/**
	 * @return the artName2
	 */
	public String getArtName2() {
		return dto.getArtName2();
	}

	/**
	 * @return the artName3
	 */
	public String getArtName3() {
		return dto.getArtName3();
	}

	/**
	 * @return the artName4
	 */
	public String getArtName4() {
		return dto.getArtName4();
	}

	/**
	 * @return the artName5
	 */
	public String getArtName5() {
		return dto.getArtName5();
	}
		
}
