package hm.akr.container.drs;

import hm.akr.common.BeanUtil;
import hm.akr.dto.CashRegisterDTO;
import hm.akr.dto.DRSDTO;
import hm.akr.dto.printer.DRSPrinterDTO;
import hm.akr.interfaces.CashRegister;
import hm.akr.interfaces.Reporting;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Class to handle the bean communication and client business logic for Daily
 * Remittance Statement
 * 
 * 
 */
public class DailyRemittanceCompositeHandler {

	BeanUtil beanutil;

	private static final String JRXML_FILE = "hm/akr/resources/printer/DRS.jrxml";

	Reporting remote;

	private static final String TOPAY_TYPE = "TOPAY";

	private static final String PAID_TYPE = "PAID";

	private static final String BILLING_TYPE = "BILLING";

	private CashRegisterDTO[] toPayLRList = null;

	private CashRegisterDTO[] paidLRList = null;

	private CashRegisterDTO[] billingLRList = null;
	
	/**
	 * Constructor method
	 * 
	 * @throws Exception
	 */
	public DailyRemittanceCompositeHandler() throws Exception {
		try {
			beanutil = BeanUtil.getInstance();
		} catch (Exception exception) {
			throw exception;
		}
	}

	/**
	 * Method to get Server date
	 */
	public String getServerDate() {
		return beanutil.getServerDate();
	}

	/**
	 * Method to print DRS details
	 * 
	 * @param dTo
	 * @throws Exception
	 */
	public void printDRS(DRSPrinterDTO dTo) throws Exception {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("Title", "DRS");

		ArrayList<DRSPrinterDTO> list = new ArrayList<DRSPrinterDTO>();
		list.add(dTo);

		if (null != beanutil) {
			beanutil.printData(list, JRXML_FILE, hashMap);
		}

	}

	/**
	 * Method to register the DRS Details
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public boolean submitDRSForm(DRSDTO dto) throws Exception {
		boolean status = false;
		try {
			if (null != beanutil) {
				remote = beanutil.getReportingBean();
				remote.submitDRS(dto);
			}
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
		return status;
	}

	/**
	 * Method to get DRS Statement
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public DRSDTO getDailyRemittanceStatement(Date date) throws Exception {
		DRSDTO dto = null;
		try {
			if (null != beanutil) {
				String stationCode = getStationCode();
				remote = beanutil.getReportingBean();
				dto = remote.getDRSDetails(stationCode, date);
			}
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
		return dto;
	}
	
	/**
	 * Method to get DRS Statement
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public DRSDTO getDailyRemittanceStatementHistory(Date date) throws Exception {
		DRSDTO dto = null;
		try {
			if (null != beanutil) {
				String stationCode = getStationCode();
				remote = beanutil.getReportingBean();
				dto = remote.getDRSDetailsHistory(stationCode, date, BeanUtil.getDbName());
			}
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
		return dto;
	}

	/**
	 * Method to get Station Name
	 * 
	 * @return
	 */
	public String getStationName() {
		return beanutil.getActingStationName();
	}

	/**
	 * Method to get station Code
	 * 
	 * @return
	 */
	public String getStationCode() {
		return beanutil.getActingStationCode();
	}
	
	public CashRegisterDTO[] getLRList(String type) throws Exception {
		CashRegisterDTO[] lrList = null;

		try {
			if (null != beanutil) {
				String stationCode = getStationCode();

				CashRegister remote = beanutil.getCashRegisterBean();

				if (PAID_TYPE.equals(type)) {
					// if (null == paidLRList) {
					paidLRList = remote.getLRNosForPaid(stationCode);
					// }
					return paidLRList;
				} else if (TOPAY_TYPE.equals(type)) {
					// if (null == toPayLRList) {
					toPayLRList = remote.getLRNosForTopay(stationCode);
					// }
					return toPayLRList;

				} else if (BILLING_TYPE.equals(type)) {
					// if (null == billingLRList) {
					billingLRList = remote.getLRNosForBilling(stationCode);
					// }
					return billingLRList;
				}

			}
		} catch (RemoteException remoteException) {
			throw remoteException;
		} catch (Exception exception) {
			throw exception;
		}

		return lrList;
	}

}
