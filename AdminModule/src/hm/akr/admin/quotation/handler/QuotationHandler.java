package hm.akr.admin.quotation.handler;

import java.util.ArrayList;
import java.util.HashMap;

import hm.akr.common.BeanUtil;
import hm.akr.dto.CustomerDTO;
import hm.akr.dto.QuotationDTO;
import hm.akr.dto.StationsDTO;

/**
 * 
 */
public class QuotationHandler {

	BeanUtil beanutil = null;
	
	QuotationDTO[] quotList = null;
	
	public QuotationHandler() throws Exception {
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
				if(quotList[i].getIs_Approved() == 0){
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
	
	public void setCustomerLogin(String quotationId, String user, String pwd) throws Exception
	{		
		if(beanutil != null){
			beanutil.setCustomerLogin(quotationId, user, pwd);
		}
		
	}
	
}
