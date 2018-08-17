package hm.akr.admin.salesquotation.handler;

import hm.akr.common.BeanUtil;
import hm.akr.dto.BftDTO;
import hm.akr.dto.CustomerDTO;
import hm.akr.dto.ExcelDTO;
import hm.akr.dto.QuotationDTO;
import hm.akr.dto.QuotationDetailsDTO;
import hm.akr.dto.QuotationExcelDecorator;
import hm.akr.dto.StationsDTO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import net.sf.jasperreports.engine.JRException;

/**
 * 
 */
public class SalesQuotationHandler {

	BeanUtil beanutil = null;
	
	QuotationDTO[] quotList = null;
	
	private static final String EXCEL_JRXML = "hm/akr/resources/printer/QExcel.jrxml";
	
	public SalesQuotationHandler() throws Exception {
		super();
		
		beanutil = new BeanUtil();
	}

	/**
	 * Method to create quotation
	 * 
	 * @param quotation
	 */
	public void createQuotation(QuotationDTO quotation) throws Exception
	{		
		if(beanutil != null){
			beanutil.createQuotation(quotation);
		}
		
	}
	
	/**
	 * Method to update quotation
	 * 
	 * @param quotation
	 */
	public void updateQuotation(QuotationDTO quotation) throws Exception
	{		
		if(beanutil != null){
			beanutil.updateQuotation(quotation);
		}
		
	}
	
	/**
	 * Method to getQuotationList
	 * @return
	 */
	public QuotationDTO[] getQuotationList() throws Exception{		
		if(beanutil != null){			
			quotList = beanutil.getQuotationList();
			ArrayList<QuotationDTO> list = new ArrayList<QuotationDTO>();
			if(quotList != null){
			for(int i = 0; i < quotList.length; i++){
				if(quotList[i].getIs_Approved() == 1){
					list.add(quotList[i]);
				}
			}
			if(list != null){
				int len = list.size();
				if(len > 0){
					return list.toArray(new QuotationDTO[len]);
				}
			}
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public StationsDTO[] getAllStations() throws Exception {
		StationsDTO[] stnDTO = null;
		if(beanutil != null){
			 stnDTO = beanutil.getAvailableStations();
		}
		return stnDTO;
	}
	
	/**
	 * Method to get Customers List
	 * @return
	 * @throws Exception
	 */
	public CustomerDTO[] getCustomers() throws Exception {
		CustomerDTO[] customers = null;
		if(beanutil != null){
			customers = beanutil.getCustomers();
		}
		return customers;
	}

	/**
	 * Method to Update Price Index
	 * @param percent 
	 * @param increment 
	 * @param map
	 * @throws Exception 
	 */
	public void updatePriceIndex(HashMap<String, Float> hMap, byte increment, float percent) throws Exception {
		if(beanutil != null){
			beanutil.updatePriceIndex(hMap, increment, percent);
		}		
	}	
	
	/**
	 * 
	 * @param quotationId
	 * @param isInward 
	 * @throws Exception
	 */
	public void deleteQuotation(String quotationId, boolean isInward) throws Exception
	{		
		if(beanutil != null){
			beanutil.deleteQuotation(quotationId, isInward);
		}
		
	}
	
	public void approveQuotation(String quotationId) throws Exception
	{		
		if(beanutil != null){
			beanutil.approveQuotation(quotationId);
		}
		
	}
	
	/*public void printExcel(String customer, BftDTO[] dto) throws Exception {		
		ArrayList<QuotationExcelDecorator> list = new ArrayList<QuotationExcelDecorator>();
		HashMap<String, String> hashMap = new HashMap<String, String>();
		if (null != dto) {
			int size = dto.length;
			hashMap.put("CustomerName", customer);
			for (int i = 0; i < size; i++) {
				list.add(new QuotationExcelDecorator(dto[i]));
			}
		}
		if (null != beanutil) {			
			beanutil.exportToExcel(list, EXCEL_JRXML, hashMap);		
		}
	}*/

	public void printExcel(String station,	String customer, QuotationDetailsDTO[] quotationDetails) {
		ArrayList<QuotationExcelDecorator> list = new ArrayList<QuotationExcelDecorator>();
		HashMap<String, String> hashMap = new HashMap<String, String>();
		if (null != quotationDetails) {
			int size = quotationDetails.length;
			int xlLen = 0;
			ExcelDTO[] xlDTO = null;
			Date date = new Date();
			String currDate = "Date: ";
						
			SimpleDateFormat formt = new SimpleDateFormat("dd.MM.yyyy");
			currDate = currDate + formt.format(date);
					
			
			hashMap.put("CustomerName", customer);
			hashMap.put("CurrDate", currDate);
			hashMap.put("FromStation", getStationName(station));
			
			if(quotationDetails[0].getBft() != null){
				xlLen = quotationDetails[0].getBft().length;
				xlDTO = new ExcelDTO[xlLen];
			
			for (int i = 0; i < size; i++) {
				BftDTO[] bft = quotationDetails[i].getBft();
				if(bft != null){
										
				//System.out.println("len--->"+bft.length);	
				for(int j = 0; j < bft.length; j++){
					//list.add(new QuotationExcelDecorator(bft[j],j+1));
					if(i == 0){
						xlDTO[j] = new ExcelDTO();
						xlDTO[j].setBpi(bft[j].getBpi());
						xlDTO[j].setStationCode(bft[j].getStationCode());
						xlDTO[j].setArtName(quotationDetails[i].getArticleName());						
					}else if(i == 1){						
						xlDTO[j].setArtName2(quotationDetails[i].getArticleName());
						xlDTO[j].setBpi2(bft[j].getBpi());
						xlDTO[j].setStationCode(bft[j].getStationCode());
					}else if(i == 2){						
						xlDTO[j].setArtName3(quotationDetails[i].getArticleName());
						xlDTO[j].setBpi3(bft[j].getBpi());
						xlDTO[j].setStationCode(bft[j].getStationCode());
					}else if(i == 3){						
						xlDTO[j].setArtName4(quotationDetails[i].getArticleName());
						xlDTO[j].setBpi4(bft[j].getBpi());
						xlDTO[j].setStationCode(bft[j].getStationCode());
					}else if(i == 4){						
						xlDTO[j].setArtName5(quotationDetails[i].getArticleName());
						xlDTO[j].setBpi5(bft[j].getBpi());
						xlDTO[j].setStationCode(bft[j].getStationCode());
					}
					
				}				
				}
			}
		}
			
			if(xlDTO != null){
			for(int j = 0; j < xlLen; j++){
				list.add(new QuotationExcelDecorator(xlDTO[j],j+1));
			}
			}
			
			if (null != beanutil) {			
				try {					
					beanutil.exportToExcel(list, EXCEL_JRXML, hashMap);
				} catch (JRException e) {				
					e.printStackTrace();
				} catch (Exception e) {				
					e.printStackTrace();
				}		
			}
		}
		
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
	
}
