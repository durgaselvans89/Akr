package hm.akr.admin.customer.handler;

import hm.akr.common.BeanUtil;
import hm.akr.dto.BookingDTO;
import hm.akr.dto.CustomerDTO;
import hm.akr.dto.StationsDTO;
import hm.akr.dto.printer.ViewBillDecorator;
import hm.akr.interfaces.Customer;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomerCompositeHandler {

	BeanUtil beanutil = null;
	
	private static final String VIEW_BILL_BF1_JRXML = "hm/akr/resources/printer/BillingBF1.jrxml";
	
	private static final String VIEW_BILL_BF0_JRXML = "hm/akr/resources/printer/BillingBF0.jrxml";
	

	public CustomerCompositeHandler() throws Exception {
		beanutil = new BeanUtil();
	}

	public String[] getCustomerNames() throws Exception {
		String[] names = null;
		if (null != beanutil) {
			names = beanutil.getCustomerNames();
		}
		return names;
	}

	public CustomerDTO[] getAdminCustomerDetails() throws Exception {
		CustomerDTO[] customer = null;
		if (null != beanutil) {
			customer = beanutil.getAdminCustomerDetails();
		}
		return customer;
	}

	public CustomerDTO getAdminCustomer(String customerName) throws Exception {
		CustomerDTO customer = null;
		if (null != beanutil) {
			customer = beanutil.getAdminCustomer(customerName);
		}
		return customer;
	}

	public void createAdminCustomer(CustomerDTO dto) throws Exception {
		if (null != beanutil) {
			beanutil.createAdminCustomer(dto);
		}
	}

	public StationsDTO[] getAvailableStations() throws Exception {
		StationsDTO[] station = null;
		if (null != beanutil) {
			station = beanutil.getAvailableStations();
		}
		return station;
	}

	public CustomerDTO[] getCustomerBookingType() throws Exception {
		CustomerDTO[] dto = null;
		if (null != beanutil) {
			dto = beanutil.getCustomerBookingType();
		}
		return dto;
	}

	/**
	 * 
	 * @param stationcode
	 * @param customername
	 * @param customertype
	 * @param billdate
	 * @return
	 * @throws Exception
	 */
	public BookingDTO[] getBillingDetails(String customername,String stationcode,String month,String year, boolean customertype) throws Exception {
		BookingDTO[] dto = null;
		if(null != beanutil)
		{
			dto = beanutil.getBillingDetails(customername,stationcode,month,year,customertype);
		}
		return dto;
	}
	
	/**
	 * 
	 * @param dto
	 * @throws Exception
	 */
	public void printViewBill(BookingDTO[] dto, byte basicFormat, byte addOn1, byte addOn2) throws Exception {
		HashMap hashMap = new HashMap();
		
		if(addOn1 == 1){
			hashMap.put("AddOn1", "1");
			if(addOn2 == 1){
				hashMap.put("AddOn2", "11");
			}		
		}else{			
			if(addOn2 == 1){
				hashMap.put("AddOn2", "01");
			}
		}
		
		ArrayList list = new ArrayList();

		if (null != dto) {
			int size = dto.length;
			for (int i = 0; i < size; i++) {
				list.add(new ViewBillDecorator(dto[i]));
			}
		}
		if (null != beanutil) {
			if(basicFormat == 1){
				beanutil.printData(list, VIEW_BILL_BF1_JRXML, hashMap);
			}else{
				beanutil.printData(list, VIEW_BILL_BF0_JRXML, hashMap);
			}
			
		}
	}
	
	/**
	 * 
	 * @param name
	 * @throws Exception
	 */
	public void deleteAdminCustomer(String name, String QuotationId) throws Exception {
		if (null != beanutil) {
			beanutil.deleteAdminCustomer(name, QuotationId);
		}
	}

	public StationsDTO[] getAllBranches() throws Exception {

		StationsDTO[] stations = beanutil.getAvailableStations();
		StationsDTO[] branches = null;

		if (null != stations) {
			ArrayList<String> branchNameList = new ArrayList<String>();

			// Get the list of branch codes
			int len = stations.length;
			for (int i = 0; i < len; i++) {
				if (!branchNameList.contains(stations[i].getBranch_code())) {
					branchNameList.add(stations[i].getBranch_code());
				}
			}

			java.util.Collections.sort(branchNameList);
			int size = branchNameList.size();
			branches = new StationsDTO[size];

			// Get the associate name of the branch code
			for (int j = 0; j < size; j++) {
				String branchcode = branchNameList.get(j);
				for (int i = 0; i < len; i++) {
					if (branchcode.equals(stations[i].getId())) {

						branches[j] = stations[i];
						break;

					}
				}
			}

		}

		return branches;

	}

	public void changeRemittanceShortagePrivilage(String[] customers,
			boolean remitance, int days) throws Exception {
		Customer customer = null;
		if (null != beanutil) {
			customer = beanutil.getCustomerBean();
			customer.changeRemittanceShortagePrivilage(customers, remitance,days);
		}
	}


}
