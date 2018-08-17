package hm.akr.container.cr;

import hm.akr.common.BeanUtil;
import hm.akr.dto.BookingDTO;
import hm.akr.dto.CCProfileDTO;
import hm.akr.dto.CashDTO;
import hm.akr.dto.CashRegisterDTO;
import hm.akr.dto.DCProfileDTO;
import hm.akr.dto.LRNumberRangeDTO;
import hm.akr.dto.SMSDTO;
import hm.akr.dto.StationsDTO;
import hm.akr.dto.printer.CashPrinterDTO;
import hm.akr.interfaces.CashRegister;
import hm.akr.interfaces.Goods;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Class to handle the bean communication and client business logic for Cash
 * Register
 * 
 * 
 */
public class CashRegisterContainerHandler {

	/**
	 * Constructor method
	 */
	BeanUtil beanutil;

	private static final String JRXML_FILE = "hm/akr/resources/printer/Cash.jrxml";

	BookingDTO bookingdto;

	private static final String TOPAY_TYPE = "TOPAY";

	private static final String PAID_TYPE = "PAID";

	private static final String BILLING_TYPE = "BILLING";

	private CashRegisterDTO[] toPayLRList = null;

	private CashRegisterDTO[] paidLRList = null;

	private CashRegisterDTO[] billingLRList = null;

	public CashRegisterContainerHandler() throws Exception {
		try {
			beanutil = BeanUtil.getInstance();
		} catch (Exception exception) {
			throw exception;
		}
	}

	/**
	 * Method to get Current Station Code
	 */
	public String getStationCode() {
		return beanutil.getActingStationCode();
	}

	/**
	 * Method to get Station Name
	 */
	public String getStationName() {
		return beanutil.getActingStationName();
	}

	/**
	 * Method to get Server Date
	 */
	public String getServerDate() {
		return beanutil.getServerDate();
	}

	public String getServerDateTime() {
		return beanutil.getServerDateTime();
	}

	/**
	 * Method to Print Cash receipt
	 * 
	 * @param dTo
	 * @throws Exception
	 */
	public void printCashReceipt(CashPrinterDTO dTo) throws Exception {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("Title", "CASH RECEIPT");

		ArrayList<CashPrinterDTO> list = new ArrayList<CashPrinterDTO>();
		list.add(dTo);

		if (null != beanutil) {
			beanutil.printData(list, JRXML_FILE, hashMap);
		}

	}

	/**
	 * 
	 * @param dTo
	 * @param dto
	 * @throws Exception
	 */
	public void printCashReceipt(ArrayList list) throws Exception {

		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("Title", "CASH RECEIPT");

		beanutil.printData(list, JRXML_FILE, hashMap);

	}

	/**
	 * 
	 * @param dTo
	 * @throws Exception
	 */
	public void updatePrintStatus(String[] crno) throws Exception {
		try {
			if (null != beanutil) {

				CashRegister cr = beanutil.getCashRegisterBean();
				cr.updatePrintStatus(crno);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}

	/**
	 * Method to Submit Cash register
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public boolean submitForm(CashDTO dto) throws Exception {
		boolean status = false;
		try {
			if (null != beanutil) {

				CashRegister cr = beanutil.getCashRegisterBean();
				status = cr.generateCR(dto);
			}
		} catch (Exception exception) {
			throw exception;
		}

		return status;
	}

	public String[] submitCashReceipt(CashDTO[] dto) throws Exception {
		String[] status = null;
		try {
			if (null != beanutil) {

				CashRegister cr = beanutil.getCashRegisterBean();
				status = cr.generateCRList(dto);
			}
		} catch (Exception exception) {
			throw exception;
		}

		return status;
	}
	
	public void updateCashReceipt(CashDTO[] dto) throws Exception {
		
		try {
			if (null != beanutil) {

				CashRegister cr = beanutil.getCashRegisterBean();
				cr.updateCRList(dto);
			}
		} catch (Exception exception) {
			throw exception;
		}
	}

	/**
	 * 
	 * @param lrno
	 * @return
	 * @throws Exception
	 */
	public BookingDTO getDetails(String lrno, String stationCode)
			throws Exception {
		BookingDTO bookingdto = new BookingDTO();
		try {
			if (null != beanutil) {
				Goods cr = beanutil.getGoodsBean();

				bookingdto = cr.getBookingDetail(lrno, stationCode, BeanUtil.getDbName());
			}
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
		return bookingdto;
	}

	/**
	 * Method to get LR List
	 * 
	 * @param type
	 * @return
	 * @throws Exception
	 */
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

	/**
	 * Method to Cancel particular CR Number
	 * 
	 * @return
	 */
	public boolean cancelCashRegister(CashDTO dto) throws Exception {
		boolean status = false;
		try {
			if (null != beanutil) {

				CashRegister cr = beanutil.getCashRegisterBean();
				status = cr.cancelCR(dto);
			}
		} catch (Exception exception) {

			throw exception;
		}

		return status;
	}

	/**
	 * Method to get CR Details
	 * 
	 * @param crno
	 * @return
	 * @throws Exception
	 */
	public CashDTO getCRDetails(String crno, Date date) throws Exception {
		CashDTO dto = null;

		try {
			if (null != beanutil) {

				CashRegister cr = beanutil.getCashRegisterBean();
				dto = cr.getCRInformation(crno, date);
			}
		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}

	/**
	 * Method to get CR Details
	 * 
	 * @param crno
	 * @return
	 * @throws Exception
	 */
	public CashDTO[] getCRDetails(String station, Date fromdate, Date todate)
			throws Exception {
		CashDTO[] dto = null;

		try {
			if (null != beanutil) {

				CashRegister cr = beanutil.getCashRegisterBean();
				dto = cr.getCRInformation(station, fromdate, todate);
			}
		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}
	
	/**
	 * Method to get CR Details
	 * 
	 * @param crno
	 * @return
	 * @throws Exception
	 */
	public CashDTO[] getCRDetailsHistory(String station, Date fromdate, Date todate)
			throws Exception {
		CashDTO[] dto = null;

		try {
			if (null != beanutil) {

				CashRegister cr = beanutil.getCashRegisterBean();
				dto = cr.getCRInformationHistory(station, fromdate, todate, BeanUtil.getDbName());			
			}
		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}

	/**
	 * 
	 * @param stationCode
	 * @return
	 * @throws Exception
	 */
	public String getLastUsedCR(String stationCode) throws Exception {
		String crno = null;

		try {
			if (null != beanutil) {
				CashRegister cr = beanutil.getCashRegisterBean();
				crno = cr.getLastUsedCR(stationCode);
			}
		} catch (Exception exception) {
			throw exception;
		}

		return crno;
	}

	/**
	 * 
	 * @param stationCode
	 * @return
	 * @throws Exception
	 */
	public LRNumberRangeDTO[] getCRRange(String stationCode) throws Exception {
		LRNumberRangeDTO[] crno = null;
		try {
			if (null != beanutil) {
				CashRegister cr = beanutil.getCashRegisterBean();
				crno = cr.getLRRange(stationCode);
			}
		} catch (Exception exception) {
			throw exception;
		}

		return crno;
	}

	/**
	 * 
	 * @param sms
	 * @throws Exception
	 */
	public void storeSMS(SMSDTO[] sms) throws Exception {
		try {
			if (null != beanutil) {
				CashRegister cr = beanutil.getCashRegisterBean();
				cr.storeSMSToFuture(sms);
			}
		} catch (Exception exception) {
			throw exception;
		}

	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public StationsDTO[] getAvailableStations() throws Exception {

		try {
			if (null != beanutil) {
				return beanutil.getAvailableStations();
			}
		} catch (RemoteException remoteException) {
			throw remoteException;
		} catch (Exception exception) {
			throw exception;
		}
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public DCProfileDTO[] getDCDetails() throws Exception {
		DCProfileDTO[] dto = null;
		if (null != beanutil) {
			try {
				dto = beanutil.getDCDetails();

			} catch (Exception exception) {
				throw exception;
			}
		}
		return dto;

	}
}
