package hm.akr.container.reports;

import hm.akr.common.BeanUtil;
import hm.akr.dto.ArticleDTO;
import hm.akr.dto.BookingDTO;
import hm.akr.dto.CurrentStatusDTO;
import hm.akr.dto.DDRDTO;
import hm.akr.dto.DistanceListDTO;
import hm.akr.dto.GMRInTimeDTO;
import hm.akr.dto.GMROutTimeDTO;
import hm.akr.dto.GMRReportDTO;
import hm.akr.dto.OutstandingDTO;
import hm.akr.dto.RefundRecoveryDTO;
import hm.akr.dto.SODDTO;
import hm.akr.dto.StationsDTO;
import hm.akr.dto.printer.CurrentStatusDTODecorator;
import hm.akr.dto.printer.GMRDTODecorator;
import hm.akr.dto.printer.OutstandingDTODecorator;
import hm.akr.dto.printer.SOBDTODecorator;
import hm.akr.dto.printer.SODDRDecorator;
import hm.akr.dto.printer.SODDTODecorator;
import hm.akr.dto.printer.STOCKDTODecoretor;
import hm.akr.interfaces.Reporting;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import net.sf.jasperreports.engine.JRException;

/**
 * LRComposite class
 * 
 * @version 1.0
 */
public class ReportsCompositeHandler {

	BeanUtil beanutil;

	Reporting remote;

	private static final String SOB_JRXML = "hm/akr/resources/printer/SOB.jrxml";

	private static final String STOCK_JRXML = "hm/akr/resources/printer/Stock.jrxml";

	static final String SOD_JRXML = "hm/akr/resources/printer/SOD.jrxml";

	private static String SODDR_JRXML = "hm/akr/resources/printer/SODDR.jrxml";

	private static String GMR_JRXML = "hm/akr/resources/printer/GMR.jrxml";
	
	private static String OUTSTANDING_JRXML = "hm/akr/resources/printer/Outstanding.jrxml";
	
	private static String CURRENTSTATUS_JRXML = "hm/akr/resources/printer/Currentstatus.jrxml";
	
	private static final String RECOVERY_JRXML = "hm/akr/resources/printer/Recovery.jrxml";
	
	private static final String REFUND_JRXML = "hm/akr/resources/printer/Refund.jrxml";
	
	private static final String INWARD_JRXML = "hm/akr/resources/printer/InwardRegister.jrxml";

	String stationCode = null;
	String stationName = null;

	/**
	 * Constructor For Report Composite Handler
	 * 
	 * @throws Exception
	 */
	public ReportsCompositeHandler() throws Exception {

		try {
			beanutil = BeanUtil.getInstance();
			stationCode = beanutil.getActingStationCode();
			stationName = beanutil.getActingStationName();
		} catch (Exception exception) {
			throw exception;
		}
	}

	/**
	 * Method to get statement of booking
	 * 
	 * @param stationCode
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public BookingDTO[] getStatementOfBooking(String stationCode, Date date)
			throws Exception {
		BookingDTO[] bookingList = null;

		try {
			if (null != beanutil) {
				Reporting cr = beanutil.getReportingBean();
				bookingList = cr.getStatementOfBooking(stationCode, date, BeanUtil.getDbName());
			}
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
		return bookingList;
	}
	
	/**
	 * Method to get statement of booking
	 * 
	 * @param stationCode
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public BookingDTO[] getStatementOfBookingHistory(String stationCode, Date date)
			throws Exception {
		BookingDTO[] bookingList = null;

		try {
			if (null != beanutil) {
				Reporting cr = beanutil.getReportingBean();
				bookingList = cr.getStatementOfBookingHistory(stationCode, date, BeanUtil.getDbName());
			}
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
		return bookingList;
	}

	/**
	 * Method to get Date from Remote Server
	 */
	public String getServerDate() {
		return beanutil.getServerDate();
	}

	/**
	 * Method to get statement of delivery
	 * 
	 * @param stationCode
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public SODDTO[] getStatementOfDelivery(String stationCode, Date date)
			throws Exception {
		SODDTO[] deliveryList = null;

		try {
			if (null != beanutil) {

				Reporting cr = beanutil.getReportingBean();
				
				deliveryList = cr.getStatementOfDelivery(stationCode, date, BeanUtil.getDbName());
			}
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
		return deliveryList;
	}
	
	/**
	 * Method to get statement of delivery
	 * 
	 * @param stationCode
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public SODDTO[] getStatementOfDeliveryHistory(String stationCode, Date date)
			throws Exception {
		SODDTO[] deliveryList = null;

		try {
			if (null != beanutil) {

				Reporting cr = beanutil.getReportingBean();
				System.out.println("calling DB---->"+BeanUtil.getDbName());
				deliveryList = cr.getStatementOfDeliveryHistory(stationCode, date, BeanUtil.getDbName());
			}
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
		return deliveryList;
	}

	/**
	 * Method to get goods movement
	 * 
	 * @param stationCode
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public GMRReportDTO[] getGoodsMovement(String stationCode, Date date)
			throws Exception {
		GMRReportDTO[] goods = null;

		try {
			if (null != beanutil) {
				Reporting cr = beanutil.getReportingBean();
				goods = cr.getGoodsMovement(stationCode, date, BeanUtil.getDbName());
			}
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
		return goods;
	}

	/**
	 * Method to get goods movement for vehicle
	 * 
	 * @param stationCode
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public GMRReportDTO[] getGoodsMovementForVehicle(String stationCode,
			Date date, String vehicle) throws Exception {
		GMRReportDTO[] goods = null;

		try {
			if (null != beanutil) {
				Reporting cr = beanutil.getReportingBean();
				goods = cr.getGoodsMovementForVehicle(stationCode, date,
						vehicle, BeanUtil.getDbName());
			}
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
		return goods;
	}

	/**
	 * Method to get statement of door delivery reimbursement
	 * 
	 * @param stationCode
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public DDRDTO[] getStatementOfDoorDeliveryReImbursement(String stationCode,
			Date date) throws Exception {
		DDRDTO[] delivery = null;

		try {
			if (null != beanutil) {
				Reporting cr = beanutil.getReportingBean();
				delivery = cr.getStatementOfDoorDeliveryReImbursement(
						stationCode, date, BeanUtil.getDbName());
			}
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
		return delivery;
	}
	
	/**
	 * Method to get statement of door delivery reimbursement
	 * 
	 * @param stationCode
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public DDRDTO[] getStatementOfDoorDeliveryReImbursementHistory(String stationCode,
			Date date) throws Exception {
		DDRDTO[] delivery = null;

		try {
			if (null != beanutil) {
				Reporting cr = beanutil.getReportingBean();
				System.out.println("calling DBddr---->"+BeanUtil.getDbName());
				delivery = cr.getDoorDeliverReImbursementListHistory(stationCode, date, BeanUtil.getDbName());
			}
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
		return delivery;
	}

	
	
	public GMROutTimeDTO[] getInwardRegister(Date date, Date toDate) throws Exception {
		if(beanutil != null){
			//return beanutil.getInwardRegister(date, toDate);
		}
		return null;
	}

	public GMROutTimeDTO[] getInwardRegisterHistory(Date date, Date toDate) throws Exception {
		if(beanutil != null){
			//return beanutil.getInwardRegisterHistory(date, toDate);
	
	
		}
		return null;
	}
	
	
	/**
	 * Method to print Statement of booking
	 * 
	 * @param dto
	 * @param date
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void printStatementOfBooking(BookingDTO[] dto, String date,
			String stno, float totaltopay, float totalpaid, float totalbilling)
			throws Exception {
		HashMap hashMap = new HashMap();

		hashMap.put("StationCode", stationCode);
		hashMap.put("StationName", stationName);
		hashMap.put("SelectedDate", date);
		hashMap.put("STNO", stno);
		hashMap.put("tottopay", String.valueOf(totaltopay));
		hashMap.put("totpaid", String.valueOf(totalpaid));
		hashMap.put("totbilling", String.valueOf(totalbilling));

		ArrayList list = new ArrayList();

		if (null != dto) {
			int size = dto.length;
			for (int i = 0; i < size; i++) {
				list.add(new SOBDTODecorator(dto[i]));
			}
		}
		if (null != beanutil) {
			beanutil.printData(list, SOB_JRXML, hashMap);
		}
	}

	/**
	 * Method Print Stock Details
	 * 
	 * @param dto
	 * @throws JRException
	 * @throws Exception
	 */
	public void printStockDetails(GMRInTimeDTO[] dto) throws JRException,
			Exception {
		ArrayList list = new ArrayList();

		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("Title", "DAILYSTATUS");

		if (null != dto) {
			int size = dto.length;
			for (int i = 0; i < size; i++) {
				list.add(new STOCKDTODecoretor(dto[i], i + 1));
			}
		}
		if (null != beanutil) {
			beanutil.printData(list, STOCK_JRXML, hashMap);
		}
	}

	/**
	 * Method to print door delivery reimbursement
	 * 
	 * @param dto
	 * @param date
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void printDoorDeliveryReimbursement(DDRDTO[] dto, String date,
			String stno) throws Exception {
		HashMap hashMap = new HashMap();

		hashMap.put("StationCode", stationCode);
		hashMap.put("StationName", stationName);
		hashMap.put("SelectedDate", date);
		hashMap.put("STNO", stno);

		ArrayList list = new ArrayList();

		if (null != dto) {
			int size = dto.length;
			for (int i = 0; i < size; i++) {
				list.add(new SODDRDecorator(dto[i], i + 1));
			}
		}

		if (null != beanutil) {
			beanutil.printData(list, SODDR_JRXML, hashMap);
		}
	}

	/**
	 * Method to print GMR details
	 * 
	 * @param dto
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void printGMRDetails(GMRReportDTO[] dto) throws Exception {
		HashMap hashMap = new HashMap();
		hashMap.put("Title", "GMR");
		ArrayList list = new ArrayList();

		if (null != dto) {
			int size = dto.length;
			for (int i = 0; i < size; i++) {
				list.add(new GMRDTODecorator(dto[i]));
			}
		}

		if (null != beanutil) {
			beanutil.printData(list, GMR_JRXML, hashMap);
		}
	}

	/**
	 * Method to populate statement of delivery
	 * 
	 * @param dto
	 * @param date
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void printStatementOfDelivery(SODDTO[] dto, String date, String stno)
			throws Exception {
		HashMap hashMap = new HashMap();
		hashMap.put("StationCode", stationCode);
		hashMap.put("StationName", stationName);
		hashMap.put("SelectedDate", date);
		hashMap.put("STNO", stno);
		ArrayList list = new ArrayList();

		if (null != dto) {
			int size = dto.length;
			for (int i = 0; i < size; i++) {
				list.add(new SODDTODecorator(dto[i]));
			}
		}

		if (null != beanutil) {
			beanutil.printData(list, SOD_JRXML, hashMap);
		}
	}
	
	public void printSOBReportExcel(BookingDTO[] dto, String date,
			String stno, float totaltopay, float totalpaid, float totalbilling, String fileName)throws Exception {
		if (null != beanutil) {
			try {
				HashMap<String, String> hashMap = new HashMap();
				/*hashMap = IncludeHeader( list,  fileName,
						 jrxmlFile,  moc, value);*/
				
					hashMap.put("StationCode", stationCode);
					hashMap.put("StationName", stationName);
					hashMap.put("SelectedDate", date);
					hashMap.put("STNO", stno);
					hashMap.put("tottopay", String.valueOf(totaltopay));
					hashMap.put("totpaid", String.valueOf(totalpaid));
					hashMap.put("totbilling", String.valueOf(totalbilling));

					ArrayList list = new ArrayList();

					if (null != dto) {
						int size = dto.length;
						for (int i = 0; i < size; i++) {
							list.add(new SOBDTODecorator(dto[i]));
						}
					}
					if (null != beanutil) {
						beanutil.exportToReportExcel(list, SOB_JRXML, hashMap, fileName);
					}

					
					
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Method Print Stock Details
	 * 
	 * @param dto
	 * @throws JRException
	 * @throws Exception
	 */
	public void printStockDetails(GMRInTimeDTO[] dto,String fileName) throws JRException,
			Exception {
		ArrayList list = new ArrayList();

		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("Title", "DAILYSTATUS");

		if (null != dto) {
			int size = dto.length;
			for (int i = 0; i < size; i++) {
				list.add(new STOCKDTODecoretor(dto[i], i + 1));
			}
		}
		if (null != beanutil) {
			beanutil.printData(list, STOCK_JRXML, hashMap);
		}
	}
	
	
	public void printOutstandingDetails(OutstandingDTO[] dto, String selectednames,String fileName) throws JRException,
			Exception {
		ArrayList list = new ArrayList();

		HashMap<String, String> hashMap = new HashMap<String, String>();
		//hashMap.put("Title", "DAILYSTATUS");
		hashMap.put("station", stationCode);

		if (null != dto) {
			int size = dto.length;
			for (int i = 0; i < size; i++) {
				list.add(new OutstandingDTODecorator(dto[i], i + 1));
			}
		}
		if (null != beanutil) {
			beanutil.printPDF(list, OUTSTANDING_JRXML, hashMap,fileName);
		}
	}
	
	public void printCurrentstatusDetails(CurrentStatusDTO[] dto,  String fileName,String Currentstation) throws JRException,
				Exception {
		ArrayList list = new ArrayList();

		HashMap<String, String> hashMap = new HashMap<String, String>();
		//	hashMap.put("Title", "DAILYSTATUS");
		hashMap.put("Station", Currentstation);
		if (null != dto) {
			int size = dto.length;
		for (int i = 0; i < size; i++) {
			list.add(new CurrentStatusDTODecorator(dto[i], i + 1));
		}
		}
		if (null != beanutil) {
			beanutil.printData(list, CURRENTSTATUS_JRXML, hashMap);
		}
	}


	
	/**
	 * Method to get Station Code
	 * 
	 * @return
	 */
	public String getStationCode() {
		return stationCode;
	}

	/**
	 * Method to get Stock Details
	 * 
	 * @param stationcode
	 * @return
	 * @throws Exception
	 */
	public ArrayList getStockDetails(String stationcode) throws Exception {
		ArrayList list = beanutil.getStockDetails(stationcode);
		return list;
	}

	/**
	 * Method to get article Type for Given article ID
	 * 
	 * @param parseInt
	 * @return
	 * @throws Exception
	 */
	public ArticleDTO[] getArticleType(int parseInt) throws Exception {
		if (null != beanutil) {
			return beanutil.getArticleTypes();
		}
		return null;
	}

	/**
	 * 
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public CurrentStatusDTO[] getCurrentStatusReport(String code)
			throws Exception {
		if (null != beanutil) {
			return beanutil.getCurrentStatusReport(code);
		}
		return null;
	}

	/**
	 * 
	 */
	public StationsDTO[] getStations() throws Exception {
		if (null != beanutil) {
			try {
				return beanutil.getAvailableStations();
			} catch (Exception exception) {
				throw exception;
			}
		}
		return null;
	}

	/**
	 * This method returns all the associated stations for this station.
	 * 
	 * @return StationDTO[] An array instance of StationDTO
	 */
	public StationsDTO[] getAllAssociatedStations() throws Exception {
		StationsDTO[] stations = null;
		String stationCode = beanutil.getActingStationCode();

		String branchCode = getAssociatedBranch(stationCode);
		if (null != branchCode)
			stations = getStations(branchCode);
		return stations;
	}

	/**
	 * Method to get All Stations
	 * 
	 * @param branchcode
	 * @return
	 * @throws Exception
	 */
	public StationsDTO[] getStations(String branchcode) throws Exception {
		ArrayList<StationsDTO> stationList = new ArrayList<StationsDTO>();

		StationsDTO[] stations = beanutil.getAvailableStations();

		int size = 0;
		if (null != stations) {
			size = stations.length;
			for (int i = 0; i < size; i++) {
				if (branchcode.equals(stations[i].getBranch_code())) {
					stationList.add(stations[i]);
				}
			}
		}

		size = stationList.size();
		if (size > 0) {
			return stationList.toArray(new StationsDTO[size]);
		}
		return null;
	}

	/**
	 * 
	 * @param stationcode
	 * @param isActingStation
	 * @return
	 * @throws RemoteException
	 * @throws NamingException
	 * @throws CreateException
	 * @throws Exception
	 */
	public DistanceListDTO[] getDistanceList(String stationcode,
			boolean isActingStation) throws RemoteException, Exception {
		try {
			return beanutil.getDistanceList(stationcode,false);
		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * Method to get Branch for Given Station
	 * 
	 * @param stationCode
	 * @return
	 */
	private String getAssociatedBranch(String stationCode) throws Exception {
		String branchcode = null;

		StationsDTO[] stations = beanutil.getAvailableStations();
		if (null != stations) {
			int size = stations.length;
			for (int i = 0; i < size; i++) {
				if (stationCode.equals(stations[i].getId())
						&& stationCode.equals(stations[i].getBranch_code())) {
					branchcode = stations[i].getBranch_code();
				}
			}
		}

		return branchcode;
	}

	
	public void printSODReportExcel(SODDTO[] dto, String date, String stno,
			String fileName)throws Exception {
		// TODO Auto-generated method stub
		HashMap hashMap = new HashMap();
		hashMap.put("StationCode", stationCode);
		hashMap.put("StationName", stationName);
		hashMap.put("SelectedDate", date);
		hashMap.put("STNO", stno);
		ArrayList list = new ArrayList();

		if (null != dto) {
			int size = dto.length;
			for (int i = 0; i < size; i++) {
				list.add(new SODDTODecorator(dto[i]));
			}
		}

		if (null != beanutil) {
			beanutil.exportToReportExcel(list, SOD_JRXML, hashMap,fileName);
		}

		
	}

	public void printDDRReportExcel(DDRDTO[] dto, String date,
			String stno,String fileName) throws Exception {
		HashMap hashMap = new HashMap();

		hashMap.put("StationCode", stationCode);
		hashMap.put("StationName", stationName);
		hashMap.put("SelectedDate", date);
		hashMap.put("STNO", stno);

		ArrayList list = new ArrayList();

		if (null != dto) {
			int size = dto.length;
			for (int i = 0; i < size; i++) {
				list.add(new SODDRDecorator(dto[i], i + 1));
			}
		}

		if (null != beanutil) {
			beanutil.exportToReportExcel(list, SODDR_JRXML, hashMap,fileName);
		}
	}
	
	public void printSTOCKReportExcel(GMRInTimeDTO[] dto,String fileName) throws JRException,
	Exception {
			ArrayList list = new ArrayList();

			HashMap<String, String> hashMap = new HashMap<String, String>();
			hashMap.put("Title", "DAILYSTATUS");

			if (null != dto) {
				int size = dto.length;
				for (int i = 0; i < size; i++) {
					list.add(new STOCKDTODecoretor(dto[i], i + 1));
				}
			}
			if (null != beanutil) {
				beanutil.exportToReportExcel(list, STOCK_JRXML, hashMap,fileName);
			}
	}

	public void printOutstandingReportExcel(OutstandingDTO[] dto,
			String fileName, String selectednames) throws JRException, Exception {
		// TODO Auto-generated method stub
		HashMap hashMap = new HashMap();

		hashMap.put("station", selectednames);
		
		

		ArrayList list = new ArrayList();

		if (null != dto) {
			int size = dto.length;
			for (int i = 0; i < size; i++) {
				list.add(new OutstandingDTODecorator(dto[i], i + 1));
			}
		}

		if (null != beanutil) {
			beanutil.exportToReportExcel(list, OUTSTANDING_JRXML, hashMap,fileName);
		}

		
	}
	
	public void printCurrentstatusReportExcel(CurrentStatusDTO[] dto,
			String fileName,String station) throws JRException, Exception {
		// TODO Auto-generated method stub
		HashMap hashMap = new HashMap();
		hashMap.put("Station", station);
			

		ArrayList list = new ArrayList();

		if (null != dto) {
			int size = dto.length;
			for (int i = 0; i < size; i++) {
				list.add(new CurrentStatusDTODecorator(dto[i], i + 1));
			}
		}

		if (null != beanutil) {
			beanutil.exportToReportExcel(list, CURRENTSTATUS_JRXML, hashMap,fileName);
		}
	}

	
	
	public void printSODReportPDF(SODDTO[] sto, String text, String sodST,
			String fileName) throws Exception {
		// TODO Auto-generated method stub
		HashMap hashMap = new HashMap();
		hashMap.put("StationCode", stationCode);
		hashMap.put("StationName", stationName);
		hashMap.put("SelectedDate", text);
		hashMap.put("STNO", sodST);
		ArrayList list = new ArrayList();

		if (null != sto) {
			int size = sto.length;
			for (int i = 0; i < size; i++) {
				list.add(new SODDTODecorator(sto[i]));
			}
		}

		if (null != beanutil) {
			beanutil.printPDF(list, SOD_JRXML, hashMap,fileName);
		}
	}

	public void printSOBReportPDF(BookingDTO[] sto, String text, String sobST,
			float totaltopay, float totalpaid, float totalbillng, String fileName)throws Exception {
		// TODO Auto-generated method stub
		if (null != beanutil) {
			try {
				HashMap<String, String> hashMap = new HashMap();
				/*hashMap = IncludeHeader( list,  fileName,
						 jrxmlFile,  moc, value);*/
				
					hashMap.put("StationCode", stationCode);
					hashMap.put("StationName", stationName);
					hashMap.put("SelectedDate", text);
					hashMap.put("STNO", sobST);
					hashMap.put("tottopay", String.valueOf(totaltopay));
					hashMap.put("totpaid", String.valueOf(totalpaid));
					hashMap.put("totbilling", String.valueOf(totalbillng));

					ArrayList list = new ArrayList();

					if (null != sto) {
						int size = sto.length;
						for (int i = 0; i < size; i++) {
							list.add(new SOBDTODecorator(sto[i]));
						}
					}
					if (null != beanutil) {
						beanutil.printPDF(list, SOB_JRXML, hashMap, fileName);
					}

					
					
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

			}

	public void printStackReportPDF(GMRInTimeDTO[] dto, String fileName)throws Exception {
		// TODO Auto-generated method stub
		ArrayList list = new ArrayList();

		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("Title","DAILYSTATUS");

		if (null != dto) {
			int size = dto.length;
			for (int i = 0; i < size; i++) {
				list.add(new STOCKDTODecoretor(dto[i], i + 1));
			}
		}
		if (null != beanutil) {
			beanutil.printPDF(list, STOCK_JRXML, hashMap,fileName);
		}

		
	}

	public void printDDRReportPDF(DDRDTO[] door, String text, String soddrST,
			String fileName)throws Exception {
		// TODO Auto-generated method stub
		HashMap hashMap = new HashMap();

		hashMap.put("StationCode", stationCode);
		hashMap.put("StationName", stationName);
		hashMap.put("SelectedDate", text);
		hashMap.put("STNO", soddrST);

		ArrayList list = new ArrayList();

		if (null != door) {
			int size = door.length;
			for (int i = 0; i < size; i++) {
				list.add(new SODDRDecorator(door[i], i + 1));
			}
		}

		if (null != beanutil) {
			beanutil.printPDF(list, SODDR_JRXML, hashMap,fileName);
		}

		
	}

	public void printCurrentStatusReportPDF(CurrentStatusDTO[] dto,
			String fileName,String station) throws JRException, Exception {
		// TODO Auto-generated method stub
		HashMap hashMap = new HashMap();
		hashMap.put("Station", station);
		ArrayList list = new ArrayList();

		if (null != dto) {
			int size = dto.length;
			for (int i = 0; i < size; i++) {
				list.add(new CurrentStatusDTODecorator(dto[i], i + 1));
			}
		}

		if (null != beanutil) {
			beanutil.printPDF(list, CURRENTSTATUS_JRXML, hashMap,fileName);
		}


	}

	public void printOutStandingReportPDF(OutstandingDTO[] dto, String fileName,String stationName) throws JRException, Exception {
		// TODO Auto-generated method stub
		HashMap hashMap = new HashMap();

		
		hashMap.put("station", stationName);
	

		ArrayList list = new ArrayList();

		if (null != dto) {
			int size = dto.length;
			for (int i = 0; i < size; i++) {
				list.add(new OutstandingDTODecorator(dto[i], i + 1));
			}
		}

		if (null != beanutil) {
			beanutil.printPDF(list, OUTSTANDING_JRXML, hashMap,fileName);
		}

	}

	public void printRecoveryReportExcel(ArrayList dto, String month,
			String fileName) throws JRException,
			Exception {
		if (null != beanutil) {
			try {

				//HashMap<String, String> hashMap = new HashMap<String, String>();
				//HashMap<String, String> hashMap = null;
				HashMap hashMap = new HashMap();
				hashMap.put("selectDate", month);
			
				
				beanutil.exportToReportExcel(dto,RECOVERY_JRXML, hashMap,fileName);
				
			} catch (JRException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// TODO Auto-generated method stub
		
	}

	public void printRefundReportExcel(ArrayList dto, String month,String fileName) throws JRException,
	Exception {
		// TODO Auto-generated method stub
		if (null != beanutil) {
			try {

				//HashMap<String, String> hashMap = new HashMap<String, String>();
				//HashMap<String, String> hashMap = null;
				HashMap hashMap = new HashMap();
				hashMap.put("selectDate", month);
			
				
				beanutil.exportToReportExcel(dto,RECOVERY_JRXML, hashMap,fileName);
				
			} catch (JRException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	
	public void printInwardReportExcel(ArrayList dto, String irSt,
			String fromdate, String todate, String fileName) {
		// TODO Auto-generated method stub
		if (null != beanutil) {
			try {

				//HashMap<String, String> hashMap = new HashMap<String, String>();
				//HashMap<String, String> hashMap = null;
				HashMap hashMap = new HashMap();
				hashMap.put("stationname", irSt);
				hashMap.put("fromdate", fromdate);
				hashMap.put("todate", todate);
			
				
				beanutil.exportToReportExcel(dto,INWARD_JRXML, hashMap,fileName);
				
			} catch (JRException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		
	}

	public void printInwardRegister(ArrayList<GMROutTimeDTO> dto, String irSt,
			String fromdate, String todate, String string) {
		// TODO Auto-generated method stub
		if (null != beanutil) {
			try {

				//HashMap<String, String> hashMap = new HashMap<String, String>();
				//HashMap<String, String> hashMap = null;
				HashMap hashMap = new HashMap();
				hashMap.put("stationname", irSt);
				hashMap.put("fromdate", fromdate);
				hashMap.put("todate", todate);
				
				beanutil.printData(dto,INWARD_JRXML, hashMap);
				
			} catch (JRException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	public void printRefund(ArrayList<RefundRecoveryDTO> list, String month,
			String string) {
		// TODO Auto-generated method stub
		if (null != beanutil) {
			try {

				//HashMap<String, String> hashMap = new HashMap<String, String>();
				//HashMap<String, String> hashMap = null;
				HashMap hashMap = new HashMap();
				hashMap.put("selectDate", month);
				
				
				beanutil.printData(list,REFUND_JRXML, hashMap);
				
			} catch (JRException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	public void printRecovery(ArrayList<RefundRecoveryDTO> list, String month,
			String string) {
		// TODO Auto-generated method stub
		if (null != beanutil) {
			try {

				//HashMap<String, String> hashMap = new HashMap<String, String>();
				//HashMap<String, String> hashMap = null;
				HashMap hashMap = new HashMap();
				hashMap.put("selectDate", month);
							
				beanutil.printData(list,RECOVERY_JRXML, hashMap);
				
			} catch (JRException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	public void printInwardPDF(ArrayList<GMROutTimeDTO> list, String irSt,
			String fromdate, String todate, String fileName) {
		// TODO Auto-generated method stub
		if (null != beanutil) {
			try {
				
				//HashMap<String, String> hashMap = null;
				HashMap hashMap = new HashMap();
				hashMap.put("stationname", irSt);
				hashMap.put("fromdate", fromdate);
				hashMap.put("todate", todate);
				
				beanutil.printPDF(list,INWARD_JRXML , hashMap, fileName);
			} catch (JRException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	public void printRefundPDF(ArrayList<RefundRecoveryDTO> list, String month,
			String fileName) {
		// TODO Auto-generated method stub
		if (null != beanutil) {
			try {
				
				//HashMap<String, String> hashMap = null;
				HashMap hashMap = new HashMap();
				hashMap.put("selectDate", month);
				
				beanutil.printPDF(list, REFUND_JRXML, hashMap, fileName);
			} catch (JRException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	public void printRecoveryPDF(ArrayList<RefundRecoveryDTO> list,
			String month, String fileName) {
		// TODO Auto-generated method stub
		if (null != beanutil) {
			try {
				
				//HashMap<String, String> hashMap = null;
				HashMap hashMap = new HashMap();
				hashMap.put("selectDate", month);
				
				beanutil.printPDF(list, RECOVERY_JRXML, hashMap, fileName);
			} catch (JRException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	
	
}
