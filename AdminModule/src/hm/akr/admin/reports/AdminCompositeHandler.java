package hm.akr.admin.reports;

import hm.akr.common.BeanUtil;
import hm.akr.dto.BookingDTO;
import hm.akr.dto.BusinessPerformanceDTO;
import hm.akr.dto.CancelledLRDetailedDTODecorator;
import hm.akr.dto.CancelledLRSummaryDTODecorator;
import hm.akr.dto.CustomerBusinessAnalysisDTO;
import hm.akr.dto.DDRDTO;
import hm.akr.dto.DRSAttendanceDTO;
import hm.akr.dto.FocLRDetailedDTODecorator;
import hm.akr.dto.FocLRSummaryDTODecorator;
import hm.akr.dto.GeneralSummaryDTO;
import hm.akr.dto.InwardAnalysisDTO;
import hm.akr.dto.MarketVehicleDTO;
import hm.akr.dto.MissingCustomersDTO;
import hm.akr.dto.OpenLrDTODecorator;
import hm.akr.dto.RemittanceShortageDTO;
//import hm.akr.dto.ServiceTaxDTO;
import hm.akr.dto.StationsDTO;
import hm.akr.dto.VehicleDTO;
import hm.akr.exceptions.BusinessException;
import hm.akr.interfaces.Goods;
import hm.akr.interfaces.Reporting;
import hm.akr.interfaces.Station;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.NamingException;

import com.lowagie.tools.plugins.Txt2Pdf;

import net.sf.jasperreports.engine.JRException;

/**
 * 
 * @author Class for AdminHandler
 * 
 */
public class AdminCompositeHandler {

	BeanUtil beanutil = null;

	Properties properties = null;

	Context context = null;

	private String CANCELLEDLR_DETAILED__JRXML = "hm/akr/resources/printer/CancelledLrDetailed.jrxml";

	private String CANCELLEDLR_SUMMARY__JRXML = "hm/akr/resources/printer/CancelledLrSummary.jrxml";

	private static final String OPENLR_JRXML = "hm/akr/resources/printer/OpenLR.jrxml";

	private static final String FOCLR_DETAILED__JRXML = "hm/akr/resources/printer/FocLrDetailed.jrxml";

	private static final String FOCLR_SUMMARY__JRXML = "hm/akr/resources/printer/FocLrSummary.jrxml";
	
	

	/**
	 * Constructor method
	 * 
	 * @throws Exception
	 */
	public AdminCompositeHandler() throws Exception {

		try {
			beanutil = BeanUtil.getInstance();

		} catch (Exception exception) {
			throw exception;
		}
	}

	/**
	 * 
	 * @return
	 * @throws NamingException
	 * @throws RemoteException
	 * @throws CreateException
	 */
	public Station getStationBean() throws NamingException, RemoteException,
			CreateException {
		Station station = null;

		try {
			station = (Station) context.lookup("StationBean/remote");
		} catch (Exception exception) {

		}
		return station;
	}

	/**
	 * 
	 * @return
	 */
	public String getStationCode() {
		return beanutil.getActingStationCode();
	}

	/**
	 * 
	 */
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

	/**
	 * 
	 */
	public StationsDTO[] getAllStations() throws Exception {
		return beanutil.getAvailableStations();
	}

	/**
	 * This method returns all the associated stations for this station.
	 * 
	 * @return StationDTO[] An array instance of StationDTO
	 */
	public StationsDTO[] getAllAssociatedStations() throws Exception {

		String stationCode = beanutil.getActingStationCode();
		String branchCode = getAssociatedBranch(stationCode);
		StationsDTO[] stations = getStations(branchCode);
		return stations;
	}

	/**
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
				if (stationCode.equals(stations[i].getId())) {
					branchcode = stations[i].getBranch_code();
				}
			}
		}

		return branchcode;
	}

	/**
	 * 
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

	public DDRDTO[] getDDReport(Date from, Date to, String station) {
		if (beanutil != null) {
			return beanutil.getDDReport(from, to, station);
		}

		return null;
	}
	
	public DDRDTO[] getDDReportHistory(Date from, Date to, String station) {
		if (beanutil != null) {
			return beanutil.getDDReportHistory(from, to, station);
		}

		return null;
	}

	public RemittanceShortageDTO[] getRSReport(String branch, int month,
			int year) {
		if (beanutil != null) {
			return beanutil.getRSReport(branch, month, year);
		}

		return null;
	}

	public RemittanceShortageDTO[] getRSReportHistory(String branch, int month,
			int year) {
		if (beanutil != null) {
			return beanutil.getRSReportHistory(branch, month, year);
		}

		return null;
	}

	
	public BookingDTO[] getBPAReport(Date from, Date to,
			String branch) {
		if (beanutil != null) {
			return beanutil.getBPAReport(from, to, branch);
		}

		return null;

	}
	
	public BookingDTO[] getBPAReportHistory(Date from, Date to,
			String branch) {
		if (beanutil != null) {
			return beanutil.getBPAReportHistory(from, to, branch);
		}

		return null;

	}

	/**
	 * 
	 * @param fromDate
	 * @param toDate
	 * @param branch
	 * @return
	 * @throws Exception
	 */
	public GeneralSummaryDTO[] getCCChargeSummary(Date fromDate, Date toDate,
			String branch) throws Exception {
		if (beanutil != null) {
			return beanutil.getCCChargeSummary(fromDate, toDate, branch);
		}
		return null;
	}
	
	
	public GeneralSummaryDTO[] getCCChargeSummaryHistory(Date fromDate, Date toDate,
			String branch) throws Exception {
		if (beanutil != null) {
			return beanutil.getCCChargeSummaryHistory(fromDate, toDate, branch);
		}
		return null;
	}

	/**
	 * 
	 * @param fromDate
	 * @param toDate
	 * @param branch
	 * @return
	 * @throws Exception
	 */
	public GeneralSummaryDTO[] getCCCSummaryPDTP(Date fromDate, Date toDate,
			String branch) throws Exception {
		if (beanutil != null) {
			return beanutil.getCCCSummaryPDTP(fromDate, toDate, branch);
		}
		return null;
	}
	
	
	public GeneralSummaryDTO[] getCCCSummaryPDTPHistory(Date fromDate, Date toDate,
			String branch) throws Exception {
		if (beanutil != null) {
			return beanutil.getCCCSummaryPDTPHistory(fromDate, toDate, branch);
		}
		return null;
	}

	/**
	 * 
	 * @param branch
	 * @param month
	 * @param year
	 * @return
	 * @throws Exception
	 */
	
	
	

	/**
	 * 
	 * @param month
	 * @param year
	 * @return
	 * @throws Exception
	 */
	public GeneralSummaryDTO[] getUnDeliveredTopay(Date fromDate, Date toDate,
			String branch) throws Exception {
		if (beanutil != null) {
			return beanutil.getUnDeliveredTopay(fromDate, toDate, branch);
		}
		return null;
	}
	
	public GeneralSummaryDTO[] getUnDeliveredTopayHistory(Date fromDate, Date toDate,
			String branch) throws Exception {
		if (beanutil != null) {
			return beanutil.getUnDeliveredTopayHistory(fromDate, toDate, branch);
		}
		return null;
	}

	/**
	 * 
	 * @param fromDate
	 * @param toDate
	 * @return
	 * @throws Exception
	 */
	public GeneralSummaryDTO[] getDailyBooking(Date fromDate, Date toDate)
			throws Exception {
		if (beanutil != null) {
			return beanutil.getDailyBooking(fromDate, toDate);
		}
		return null;
	}

	public GeneralSummaryDTO[] getDailyBookingHistory(Date fromDate, Date toDate)
	throws Exception {
		if (beanutil != null) {
			return beanutil.getDailyBooking(fromDate, toDate);
		}
		return null;
		}

	
	/**
	 * 
	 * @param from_date
	 * @param to_date
	 * @param station
	 * @param branchcode
	 */
	public StationsDTO[] getCounterReport(Date from_date, Date to_date,
			String branch_code, StationsDTO[] station) throws Exception {
		Reporting reportingRemote = beanutil.getReportingBean();

		return reportingRemote.getCounterConsolidated(from_date, to_date,
				branch_code, station, BeanUtil.getDbName());

	}

	public StationsDTO[] getCounterReportHistory(Date from_date, Date to_date,
			String branch_code, StationsDTO[] station) throws Exception {
		Reporting reportingRemote = beanutil.getReportingBean();

		return reportingRemote.getCounterConsolidatedHistory(from_date, to_date,
				branch_code, station, BeanUtil.getDbName());

	}
	
	
	public StationsDTO[] getCounterReportMisc(Date from_date, Date to_date,
			String branch_code, StationsDTO[] station) throws Exception {
		Reporting reportingRemote = beanutil.getReportingBean();

		return reportingRemote.getCounterConsolidatedMisc(from_date, to_date,
				branch_code, station, BeanUtil.getDbName());
		

	}

	public StationsDTO[] getCounterReportMiscHistory(Date from_date, Date to_date,
			String branch_code, StationsDTO[] station) throws Exception {
		Reporting reportingRemote = beanutil.getReportingBean();

		return reportingRemote.getCounterConsolidatedMiscHistory(from_date, to_date,
				branch_code, station, BeanUtil.getDbName());
		

	}

	
	public GeneralSummaryDTO[] getSundryBooking(Date fromDate, Date toDate,String branch, String station)
			throws Exception {
		if (beanutil != null) {
			return beanutil.getSundryBooking(fromDate, toDate, branch, station);
		}
		return null;
	}
	
	public GeneralSummaryDTO[] getSundryBookingHistory(Date fromDate, Date toDate,String branch, String station)
	throws Exception {
		if (beanutil != null) {
			return beanutil.getSundryBookingHistory(fromDate, toDate,branch, station);
		}
		return null;
	}

	public BookingDTO[] getUPDReady(String station, String branch,
			int inwardDays, boolean isMoreThan) throws Exception {
		if (beanutil != null) {
			return beanutil
					.getUPDReady(station, branch, inwardDays, isMoreThan);
		}
		return null;
	}
	
	public BookingDTO[] getUPDReadyHistory(String station, String branch,
			int inwardDays, boolean isMoreThan) throws Exception {
		if (beanutil != null) {
			return beanutil
					.getUPDReadyHistory(station, branch, inwardDays, isMoreThan);
		}
		return null;
	}

	public CustomerBusinessAnalysisDTO[] getIIStates(Date fromDate, Date toDate)
			throws Exception {
		if (beanutil != null) {
			return beanutil.getIIStates(fromDate, toDate);
		}
		return null;
	}
	
	public CustomerBusinessAnalysisDTO[] getIIStatesHistory(Date fromDate, Date toDate)
	throws Exception {
		if (beanutil != null) {
			return beanutil.getIIStatesHistory(fromDate, toDate);
		}
		return null;
	}

	public MissingCustomersDTO[] getMissingCustomers() throws Exception {
		if (beanutil != null) {
			return beanutil.getMissingCustomers();
		}
		return null;
	}

	public MissingCustomersDTO[] getMissingCustomersLastYear() throws Exception {
		if (beanutil != null) {
			return beanutil.getMissingCustomersLastYear();
		}
		return null;
	}

	//
	/**
	 * 
	 * @param from_date
	 * @param to_date
	 * @param station_code 
	 * @param branch_code 
	 * @param branchcode
	 * @throws Exception
	 */
	public BookingDTO[] getCancelledLR(Date from_date, Date to_date, String type, String branch_code, String station_code)
			throws Exception {
		Reporting reportingRemote = beanutil.getReportingBean();
		return reportingRemote.getCancelledLR(from_date, to_date, type, branch_code,station_code,BeanUtil.getDbName());

	}

	public BookingDTO[] getCancelledLRHistory(Date from_date, Date to_date, String type, String branch_code, String station_code)
	throws Exception {
		Reporting reportingRemote = beanutil.getReportingBean();
		return reportingRemote.getCancelledLR(from_date, to_date, type,branch_code,station_code, BeanUtil.getDbName());
		
		}

	
	/**
	 * 
	 * @param from_date
	 * @param to_date
	 * @return
	 * @throws Exception
	 * @throws BusinessException
	 */
	public CustomerBusinessAnalysisDTO[] getCnorBusinessAnalysisReport(
			boolean isConsignor, String branch, String from_date, String to_date)
			throws BusinessException, Exception {
		Reporting reportingRemote = beanutil.getReportingBean();
		return reportingRemote.getCnorBusinessAnalysisReport(isConsignor,
				branch, from_date, to_date, BeanUtil.getDbName());
	}
	
	public CustomerBusinessAnalysisDTO[] getCneeBusinessAnalysisReport(
			boolean isConsignor, String branch, String from_date, String to_date)
			throws BusinessException, Exception {
		Reporting reportingRemote = beanutil.getReportingBean();
		return reportingRemote.getCneeBusinessAnalysisReport(isConsignor,
				branch, from_date, to_date, BeanUtil.getDbName());
	}

	public CustomerBusinessAnalysisDTO[] getCustomerBusinessAnalysisReportHistory(
			boolean isConsignor, String branch, String from_date, String to_date)
			throws BusinessException, Exception {
		Reporting reportingRemote = beanutil.getReportingBean();
		return reportingRemote.getCustomerBusinessAnalysisReportHistory(isConsignor,
				branch, from_date, to_date, BeanUtil.getDbName());
	}

	/**
	 * 
	 * @param station_code
	 * @param month
	 * @param year
	 * @return
	 * @throws Exception
	 */
	public DRSAttendanceDTO[] getDRSAttendance(int type, String station_code,
			int month, int year) throws Exception {
		Reporting reportingRemote = beanutil.getReportingBean();
		return reportingRemote
				.getDRSAttendance(type, station_code, month, year,BeanUtil.getDbName());

	}

	
	/**
	 * 
	 * @param from_date
	 * @param to_date
	 * @param branchcode
	 * @throws Exception
	 */
	public BookingDTO[] getOpenLRS(Date from_date, Date to_date, String branch)
			throws Exception {
		Reporting reportingRemote = beanutil.getReportingBean();
		return reportingRemote.getOpenLRS(from_date, to_date, branch, BeanUtil.getDbName());

	}
	
	public BookingDTO[] getOpenLRSHistory(Date from_date, Date to_date, String branch)
	throws Exception {
		Reporting reportingRemote = beanutil.getReportingBean();
		return reportingRemote.getOpenLRSHistory(from_date, to_date, branch, BeanUtil.getDbName());
		
		}

	/**
	 * 
	 * @param from_date
	 * @param to_date
	 * @param branch_code
	 * @param type
	 * @return
	 * @throws BusinessException
	 * @throws Exception
	 */
	public BookingDTO[] getFOCLRS(Date from_date, Date to_date,
			String branch_code, int type) throws BusinessException, Exception {
		Reporting reportingRemote = beanutil.getReportingBean();
		return reportingRemote.getFOCLRS(from_date, to_date, branch_code, type, BeanUtil.getDbName());

	}
	public BookingDTO[] getFOCLRSHistory(Date from_date, Date to_date,
			String branch_code, int type) throws BusinessException, Exception {
		Reporting reportingRemote = beanutil.getReportingBean();
		return reportingRemote.getFOCLRSHistory(from_date, to_date, branch_code, type, BeanUtil.getDbName());

	}
	
	
	/**
	 * 
	 * @param selectedBranch
	 * @param month
	 * @param year
	 * @return
	 * @throws BusinessException
	 * @throws Exception
	 */
	public MarketVehicleDTO[] getMarketVehicles(String selectedBranch,
			int month, int year) throws BusinessException, Exception {
		Reporting reportingRemote = beanutil.getReportingBean();
		return reportingRemote.getMarketVehicles(selectedBranch, month, year,BeanUtil.getDbName());

	}

	/**
	 * 
	 * @param month
	 * @param year
	 * @param fromStn
	 * @param inwardStn
	 * @return
	 * @throws Exception
	 */
	public InwardAnalysisDTO[] getinwardAnalysis(int month, int year,
			String fromStn, String inwardStn) throws Exception {
		Reporting reportingRemote = beanutil.getReportingBean();
		InwardAnalysisDTO[] dto = null;
		try {
			//dto = reportingRemote.getInwardAnalysis(month, year, fromStn,inwardStn, BeanUtil.getDbName());
		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}

	
	/**
	 * 
	 * @param month
	 * @param year
	 * @param fromStn
	 * @param inwardStn
	 * @return
	 * @throws Exception
	 */
	public InwardAnalysisDTO[] getLOG(Date from_Date, Date to_date,
			String fromStn, String inwardStn) throws Exception {
		Reporting reportingRemote = beanutil.getReportingBean();
		InwardAnalysisDTO[] dto = null;
		try {
			dto = reportingRemote
					.getLOG(from_Date, to_date, fromStn, inwardStn,BeanUtil.getDbName());
		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}
	
	public VehicleDTO[] getVehicles() throws Exception {
		Goods goodsRemote = beanutil.getGoodsBean();
		return goodsRemote.getVehicles();
			
		 

	}
	
	
	public void printOpenLR(OpenLrDTODecorator[] dto) throws Exception {
		HashMap hashMap = new HashMap();
		hashMap.put("AddOn2", "01");										

		ArrayList list = new ArrayList();

		if (null != dto) {
			int size = dto.length;
			for (int i = 0; i < size; i++) {
				list.add(dto[i]);
			}
		}

		beanutil.exportToReportExcel(list, OPENLR_JRXML, hashMap, "Open");

	}

	public void printLR(FocLRDetailedDTODecorator[] dto) throws Exception {
		HashMap hashMap = new HashMap();
		hashMap.put("FOCDetailed", "01");

		ArrayList list = new ArrayList();

		if (null != dto) {
			int size = dto.length;
			for (int i = 0; i < size; i++) {
				list.add(dto[i]);
			}
		}

		beanutil.exportToReportExcel(list, FOCLR_DETAILED__JRXML, hashMap,
				"FocDetailed");

	}

	public void printLR(FocLRSummaryDTODecorator[] dto) throws Exception {
		HashMap hashMap = new HashMap();
		hashMap.put("FOCSummary", "01");

		ArrayList list = new ArrayList();

		if (null != dto) {
			int size = dto.length;
			for (int i = 0; i < size; i++) {
				list.add(dto[i]);
			}
		}

		beanutil.exportToReportExcel(list, FOCLR_SUMMARY__JRXML, hashMap,
				"FocSummary");
		
		
	}
	
	
	public void printLR(CancelledLRDetailedDTODecorator[] dto) throws Exception {
		HashMap hashMap = new HashMap();
		hashMap.put("FOCDetailed", "01");

		ArrayList list = new ArrayList();

		if (null != dto) {
			int size = dto.length;
			for (int i = 0; i < size; i++) {
				list.add(dto[i]);
			}
		}

		beanutil.exportToReportExcel(list, CANCELLEDLR_DETAILED__JRXML,
				hashMap, "CancelLRDetailed");

	}

	public void printLR(CancelledLRSummaryDTODecorator[] dto) throws Exception {
		HashMap hashMap = new HashMap();
		hashMap.put("FOCDetailed", "01");

		ArrayList list = new ArrayList();

		if (null != dto) {
			int size = dto.length;
			for (int i = 0; i < size; i++) {
				list.add(dto[i]);
			}
		}

		beanutil.exportToReportExcel(list, CANCELLEDLR_SUMMARY__JRXML, hashMap,
				"CancelLRSummary");

	}

	@SuppressWarnings("unchecked")
	public void printReportExcel(ArrayList list, String fileName,
			String jrxmlFile, String[] moc,String[] value) {
		if (null != beanutil) {
			try {
				HashMap<String, String> hashMap = null;
				hashMap = IncludeHeader( list,  fileName,
						 jrxmlFile,  moc, value);
				
				beanutil
						.exportToReportExcel(list, jrxmlFile, hashMap, fileName);
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	public void printReportTXT(ArrayList list, String fileName,
			String jrxmlFile, String[] moc,String[] value) {
		if (null != beanutil) {
			try {
				HashMap<String, String> hashMap = null;
				hashMap = IncludeHeader( list,  fileName,
						 jrxmlFile,  moc, value);
				
				beanutil
						.exportToReportTXT(list, jrxmlFile, hashMap, fileName);
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	
	public void printReportPDF(ArrayList list, String fileName,
			String jrxmlFile, String[] moc,String[] value) {
		if (null != beanutil) {
			try {
				
				HashMap<String, String> hashMap = null;
				hashMap = IncludeHeader( list,  fileName,
						jrxmlFile,  moc, value);
				
				beanutil
						.exportToReportPDF(list, jrxmlFile, hashMap, fileName);
			} catch (JRException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	@SuppressWarnings("unchecked")
	/*public void printIISReportExcel(ArrayList list, String fileName,
			String jrxmlFile, String[] value) {
		if (null != beanutil) {
			try {

				HashMap<String, String> hashMap = new HashMap<String, String>();
				if(value != null)
				{
					if(fileName.equals("Inter_Intra")){
						for(int i = 0; i < value.length; i++){
							hashMap.put("val"+i, value[i]); 
							
						}
						
					}
				}
				beanutil
						.exportToReportExcel(list, jrxmlFile, hashMap, fileName);
			} catch (JRException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}*/
	
	public void preparePrint(ArrayList list,String fileName, String ddeExcelJrxml,String[] moc,String[] value) {
		if (null != beanutil) {
			try {

				//HashMap<String, String> hashMap = new HashMap<String, String>();
				HashMap<String, String> hashMap = null;
				hashMap = IncludeHeader( list,  fileName,
						ddeExcelJrxml,  moc, value);
				beanutil.printData(list, ddeExcelJrxml, hashMap);
				
			} catch (JRException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public HashMap<String,String> IncludeHeader(ArrayList list, String fileName,
			String jrxmlFile, String[] moc,String[] value)
	{
	HashMap<String, String> hashMap = new HashMap<String, String>();
	if(value != null)
	{
		
		
		
		if(fileName.equalsIgnoreCase("LeftOverGoods")){
			for(int i = 0; i < value.length; i++){
				hashMap.put("val"+i, value[i]); 
				
			}
			
		}
		
		else if(fileName.equalsIgnoreCase("DDExtra")){
			for(int i = 0; i < value.length; i++){
				hashMap.put("val"+i, value[i]); 
				
			}
			
		}
		
		else if(fileName.equalsIgnoreCase("RemittanceShortage")){
			for(int i = 0; i < value.length; i++){
				hashMap.put("val"+i, value[i]); 
				
			}
			
		}
		
		else if(fileName.equalsIgnoreCase("BPA")){
			for(int i = 0; i < value.length; i++){
				hashMap.put("val"+i, value[i]); 
				
			}
			
		}
		
		else if(fileName.equalsIgnoreCase("CCSummary")){
			for(int i = 0; i < value.length; i++){
				hashMap.put("val"+i, value[i]); 
				
			}
			
		}
		
		else if(fileName.equalsIgnoreCase("Undelivered_Topay")){
			for(int i = 0; i < value.length; i++){
				hashMap.put("val"+i, value[i]); 
				
			}
			
		}
		
		else if(fileName.equalsIgnoreCase("Undelivered_Topay_Detailed")){
			for(int i = 0; i < value.length; i++){
				hashMap.put("val"+i, value[i]); 
				
			}
			
		}
		
		else if(fileName.equalsIgnoreCase("Daily_Booking")){
			for(int i = 0; i < value.length; i++){
				hashMap.put("val"+i, value[i]); 
				
			}
			
			for(int i = 0; i < moc.length; i++){
				hashMap.put("moc"+i, moc[i]); 
				
			}
			
		}
		
		else if(fileName.equalsIgnoreCase("CounterReport")){
			for(int i = 0; i < value.length; i++){
				hashMap.put("val"+i, value[i]); 
				
			}
			
		}
		
		else if(fileName.equalsIgnoreCase("Sundry_Booking")){
			for(int i = 0; i < value.length; i++){
				hashMap.put("val"+i, value[i]); 
				
			}
			
		}
		
		else if(fileName.equalsIgnoreCase("UPD_Ready")){
			for(int i = 0; i < value.length; i++){
				hashMap.put("val"+i, value[i]); 
				
			}
			
		}
		
		else if(fileName.equalsIgnoreCase("Inter_Intra")){
			for(int i = 0; i < value.length; i++){
				hashMap.put("val"+i, value[i]); 
				
			}
			
		}
		
		else if(fileName.equalsIgnoreCase("Missing_Customer")){
			for(int i = 0; i < value.length; i++){
				hashMap.put("val"+i, value[i]); 
				
			}
			
		}
		
		else if(fileName.equalsIgnoreCase("Cancelled LRs")){
			for(int i = 0; i < value.length; i++){
				hashMap.put("val"+i, value[i]); 
				
			}
			
		}
		
		else if(fileName.equalsIgnoreCase("FOC LRs")){
			for(int i = 0; i < value.length; i++){
				hashMap.put("val"+i, value[i]); 
				
			}
			
		}
		
		else if(fileName.equalsIgnoreCase("OpenLr")){
			for(int i = 0; i < value.length; i++){
				hashMap.put("val"+i, value[i]); 
				
			}
			
		}
		
		else if(fileName.equalsIgnoreCase("CnorBookingAnalysis")){
			for(int i = 0; i < value.length; i++){
				hashMap.put("val"+i, value[i]); 
				
			}
			
		}
		
		else if(fileName.equalsIgnoreCase("CneeBookingAnalysis")){
			for(int i = 0; i < value.length; i++){
				hashMap.put("val"+i, value[i]); 
				
			}
			
		}
		
		else if(fileName.equalsIgnoreCase("Market Vehicle")){
			for(int i = 0; i < value.length; i++){
				hashMap.put("val"+i, value[i]); 
				
			}
			
		}
		
		else if(fileName.equalsIgnoreCase("DRS Attendance")){
			for(int i = 0; i < value.length; i++){
				hashMap.put("val"+i, value[i]); 
				
			}
			
		}
		
		else if(fileName.equalsIgnoreCase("Service Tax Annexure")){
			for(int i = 0; i < value.length; i++){
				hashMap.put("val"+i, value[i]); 
				
			}
			
		}
		
		else if(fileName.equalsIgnoreCase("Service Tax Annexure - LR Wise")){
			for(int i = 0; i < value.length; i++){
				hashMap.put("val"+i, value[i]); 
				
			}
			
		}
		
	}
	if (moc != null)
	{
		 /*if(fileName.equalsIgnoreCase("BPA")){
			hashMap.put("MOC", moc[0]);
		}
		else*/ if((fileName.equalsIgnoreCase("CnorBookingAnalysis"))||(fileName.equalsIgnoreCase("CneeBookingAnalysis")))
		{
			
			for(int i = 0; i < moc.length; i++){
				hashMap.put("MO"+i, moc[i]);
				
			}
		}
		else {	
			String head = "";
			for(int i = 0; i < moc.length; i++){
				head = "head"+i;	
				
				//System.out.println("he"+moc[i]);
				
				hashMap.put(head, moc[i]);
			}						
		}
	}
	
	return hashMap;
	}

	

	public BookingDTO[] getUnDeliveredTopayDetailedHistory(String branch,
			Date fromDate, Date toDate)throws Exception{
		// TODO Auto-generated method stub
		if (beanutil != null) {
			return beanutil.getUnDeliveredTopayDetailedHistory(branch, fromDate, toDate);
		}
	
		return null;
	}

	public BookingDTO[] getUnDeliveredTopayDetailed(String branch,
			Date fromDate, Date toDate)throws Exception {
		// TODO Auto-generated method stub
		if (beanutil != null) {
			return beanutil.getUnDeliveredTopayDetailed(branch, fromDate, toDate);
		}
	
		return null;
	}

	public StationsDTO[] getServicetaxHistory(Date fromDt, Date toDate)throws Exception {
		// TODO Auto-generated method stub
		if(beanutil != null){
			return beanutil.getServicetaxHistory(fromDt,toDate);
			
		}
		return null;
	}

	public StationsDTO[] getServicetax(Date fromDt, Date toDate)throws Exception {
		// TODO Auto-generated method stub
		if(beanutil != null){
			return beanutil.getServicetax(fromDt,toDate);
		}
			
		return null;
	}
	
	public StationsDTO[] getServicetaxLRHistory(Date fromDt, Date toDate)throws Exception {
		// TODO Auto-generated method stub
		if(beanutil != null){
			return beanutil.getServicetaxLRHistory(fromDt,toDate);
			
		}
		return null;
	}

	public StationsDTO[] getServicetaxLR(Date fromDt, Date toDate)throws Exception {
		// TODO Auto-generated method stub
		if(beanutil != null){
			return beanutil.getServicetaxLR(fromDt,toDate);
		}
			
		return null;
	}
	
	
}
