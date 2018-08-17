package hm.akr.container.customer;

import hm.akr.common.BeanUtil;
import hm.akr.container.lr.LRCompositeHandler;
import hm.akr.dto.CustomerDTO;
import hm.akr.interfaces.Customer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

import javax.naming.Context;

/**
 * 
 * 
 */
public class CustomerCompositeHandler {

	BeanUtil beanutil = null;

	Customer remote = null;

	CustomerDTO dto = null;

	Properties properties = null;

	Context context = null;

	String[] allNames = null;

	String[] allConsignee = null;

	String stationCode = null;

	CustomerDTO[] contactsDTO = null;

	/**
	 * Constructor method
	 * 
	 * @throws Exception
	 */
	public CustomerCompositeHandler() throws Exception {

		try {
			beanutil = BeanUtil.getInstance();
			remote = beanutil.getCustomerBean();
			stationCode = beanutil.getActingStationCode();
		} catch (Exception exception) {
			throw exception;
		}
	}

	/**
	 * 
	 * @return
	 */
	public String[] getSundryCustomers(boolean isConsignee) {
		int len = 0;
		ArrayList<String> listCustomer = new ArrayList<String>();
		CustomerDTO[] customers = null;
		// initialContactsDTO = null;
		customers = populateCustomersFromRemote();
		if (customers != null) {
			len = customers.length;
			for (int i = 0; i < len; i++) {
				if (customers[i].isConsignee() == isConsignee) {
					listCustomer.add(customers[i].getName());
				}
			}

			int size = listCustomer.size();
			if (size > 0) {
				return (String[]) listCustomer.toArray(new String[size]);
			}
		}

		return null;
	}

	/**
	 * 
	 * @return
	 */
	public CustomerDTO[] populateCustomersFromRemote() {

		return beanutil.populateCustomersFromRemote(false);

	}

	private CustomerDTO[] getCustomerDetails() {
		// if(initialContactsDTO == null){

		try {

			contactsDTO = beanutil.populateCustomersFromRemote(false);

		} catch (Exception exception) {
			exception.printStackTrace();
		}
		// }

		return contactsDTO;
	}

	/**
	 * 
	 * @param name
	 * @param address
	 * @param tinNo
	 * @param cneePhone
	 * @throws Exception
	 */
	public void addConsignee(String name, String address, String tinNo,
			String cneePhone, String cneeLandline, boolean isStax)
			throws Exception {
		CustomerDTO consignee = new CustomerDTO();
		consignee.setName(name);
		consignee.setAddress(address);
		consignee.setTinNo(tinNo);
		consignee.setConsignee(true);
		consignee.setStation(stationCode);
		consignee.setPhone(cneePhone);
		consignee.setLandLine(cneeLandline);
		if (isStax)
			consignee.setService_stax(1);
		else
			consignee.setService_stax(0);

		remote.addCustomer(consignee);
		beanutil.addCustomerInDTO(consignee);
	}

	/**
	 * 
	 * @param name
	 * @param address
	 * @param tinNo
	 * @param cnorPhone
	 * @throws Exception
	 */
	public void addConsignor(String name, String address, String tinNo,
			String cnorPhone, String cnorLandline, boolean isStax)
			throws Exception {
		CustomerDTO consignor = new CustomerDTO();
		consignor.setName(name);
		consignor.setAddress(address);
		consignor.setTinNo(tinNo);
		consignor.setConsignee(false);
		consignor.setStation(stationCode);
		consignor.setPhone(cnorPhone);
		consignor.setLandLine(cnorLandline);
		if (isStax)
			consignor.setService_stax(1);
		else
			consignor.setService_stax(0);
		remote.addCustomer(consignor);
		beanutil.addCustomerInDTO(consignor);
	}

	/**
	 * 
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public boolean deleteCustomer(String name) throws Exception {
		boolean status = false;
		if (null != name) {
			status = remote.deleteCustomer(name);
			beanutil.deleteCustomerInDTO(name);
		}

		return status;
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	/*
	 * public String[] getAddressByName(String name) { String[] details = new
	 * String[2]; if (null != name) { CustomerDTO[] customer = null; customer =
	 * populateCustomersFromRemote(); if(customer != null){ for(int i = 0; i <
	 * customer.length; i++){ if(customer[i].getName().equalsIgnoreCase(name)){
	 * details[0] = customer[i].getAddress(); details[1] =
	 * customer[i].getTinNo(); } } } }
	 * 
	 * return details; }
	 */

	public CustomerDTO getAddressByName(String name, boolean isConsignee) {
		int len = 0;

		CustomerDTO[] customers = null;
		CustomerDTO result = null;
		customers = getCustomerDetails();
		if (customers != null) {
			len = customers.length;
			for (int i = 0; i < len; i++) {
				if (customers[i].getName().equalsIgnoreCase(name)) {
					result = customers[i];

					return result;
				}
			}

		}

		if (result == null) {
			try {
				LRCompositeHandler handler = new LRCompositeHandler();
				customers = handler.getQuotationCustomers(handler
						.getStationCode(), true);
				if (customers != null) {
					len = customers.length;
					for (int i = 0; i < len; i++) {
						if (customers[i].getName().equalsIgnoreCase(name)) {

							result = customers[i];
							return result;
						}
					}

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

}
