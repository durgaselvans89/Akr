package hm.akr.container.admin;

import hm.akr.common.BeanUtil;
import hm.akr.dto.ArticleDTO;
import hm.akr.dto.BookingDTO;
import hm.akr.dto.CustomerBusinessAnalysisDTO;
import hm.akr.dto.DDRDTO;
import hm.akr.dto.DRSAttendanceDTO;
import hm.akr.dto.GMRInTimeDTO;
import hm.akr.dto.GMROutTimeDTO;
import hm.akr.dto.GeneralSummaryDTO;
import hm.akr.dto.InwardAnalysisDTO;
import hm.akr.dto.MarketVehicleDTO;
import hm.akr.dto.MissingCustomersDTO;
import hm.akr.dto.OutstandingDTO;
import hm.akr.dto.RemittanceShortageDTO;
import hm.akr.dto.StationsDTO;
import hm.akr.dto.VehicleDTO;
import hm.akr.dto.printer.OutstandingDTODecorator;
import hm.akr.dto.printer.STOCKDTODecoretor;
import hm.akr.exceptions.BusinessException;
import hm.akr.interfaces.Goods;
import hm.akr.interfaces.Reporting;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import net.sf.jasperreports.engine.JRException;

public class ReportsMenuHandler {

	BeanUtil beanutil = null; 
	String stationCode = null;
	String stationName = null;
	private static final String STOCK_JRXML = "hm/akr/resources/printer/Stock.jrxml";
	
	private static String OUTSTANDING_JRXML = "hm/akr/resources/printer/Outstanding.jrxml";
	
	private static final String INWARD_JRXML = "hm/akr/resources/printer/InwardRegister.jrxml";
	public ReportsMenuHandler(){
		try {
			beanutil = BeanUtil.getInstance();
			stationCode = beanutil.getActingStationCode();
			stationName = beanutil.getActingStationName();

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
	
	public String getStationCode() {
		return stationCode;
	}
	
	public ArrayList getStockDetails(String stationcode) throws Exception {
		ArrayList list = beanutil.getStockDetails(stationcode);
		return list;
	}
	
	public ArticleDTO[] getArticleType(int parseInt) throws Exception {
		if (null != beanutil) {
			return beanutil.getArticleTypes();
		}
		return null;
	}
	
	
	public String getServerDate() {
		return beanutil.getServerDate();
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
	
	public StationsDTO[] getAllStations() throws Exception {
		return beanutil.getAvailableStations();
	}
	
	
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
	
	public void printReportPDF(ArrayList list, String fileName,
			String jrxmlFile, String[] moc,String[] value) {
		if (null != beanutil) {
			try {
				
				HashMap<String, String> hashMap = null;
				hashMap = IncludeHeader( list,  fileName,
						jrxmlFile,  moc, value);
				
				beanutil
						.printPDF(list, jrxmlFile, hashMap, fileName);
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
				beanutil.printData(list, STOCK_JRXML, hashMap,true);
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
					
					beanutil.printData(dto,INWARD_JRXML, hashMap,true);
					
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
	
	public CustomerBusinessAnalysisDTO[] getCneeBusinessAnalysisReport(
			boolean isConsignor, String branch, String from_date, String to_date)
			throws BusinessException, Exception {
		Reporting reportingRemote = beanutil.getReportingBean();
		return reportingRemote.getCneeBusinessAnalysisReport(isConsignor,
				branch, from_date, to_date, BeanUtil.getDbName());
	}
	
	public CustomerBusinessAnalysisDTO[] getCnorBusinessAnalysisReport(
			boolean isConsignor, String branch, String from_date, String to_date)
			throws BusinessException, Exception {
		Reporting reportingRemote = beanutil.getReportingBean();
		return reportingRemote.getCnorBusinessAnalysisReport(isConsignor,
				branch, from_date, to_date, BeanUtil.getDbName());
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
	
	public VehicleDTO[] getVehicles() throws Exception {
		Goods goodsRemote = beanutil.getGoodsBean();
		
		return goodsRemote.getVehicles();
	
	}
	
	public MarketVehicleDTO[] getMarketVehicles(String selectedBranch,
			int month, int year) throws BusinessException, Exception {
		Reporting reportingRemote = beanutil.getReportingBean();
		return reportingRemote.getMarketVehicles(selectedBranch, month, year,BeanUtil.getDbName());

	}
	
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
	
	public DRSAttendanceDTO[] getDRSAttendance(int type, String station_code,
			int month, int year) throws Exception {
		Reporting reportingRemote = beanutil.getReportingBean();
		return reportingRemote
				.getDRSAttendance(type, station_code, month, year,BeanUtil.getDbName());

	}
	
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
	
	public InwardAnalysisDTO[] getinwardAnalysis(Date fromDt, Date toDate, String fromStn, String inwardStn) throws Exception {
		if(beanutil != null){
			return beanutil.getInwardAnalysis(fromDt,toDate, fromStn, inwardStn);
		}
		
		return null;
	}

	
	public GMROutTimeDTO[] getInwardRegister(Date date, Date toDate, String branchCode, String stnCode) throws Exception {
		if(beanutil != null){
			return beanutil.getInwardRegister(date, toDate,branchCode,stnCode);
		}
		return null;
	}

	public GMROutTimeDTO[] getInwardRegisterHistory(Date date, Date toDate, String branchCode, String stnCode) throws Exception {
		if(beanutil != null){
			return beanutil.getInwardRegisterHistory(date, toDate,branchCode,stnCode);
	
	
		}
		return null;
	}


	public BookingDTO[] getBPAReportUnion(Date from, Date to,
			String branch)throws Exception {
		if (beanutil != null) {
			Reporting rep = beanutil.getReportingBean();
			return rep.getBPAReportUnion(from, to, branch,BeanUtil.getDbName());
		}
		return null;
	}
	
	public BookingDTO[] getCancelledLRUnion(Date from_date, Date to_date, String type, String branch_code, String station_code)
	throws Exception {
		Reporting reportingRemote = beanutil.getReportingBean();
		return reportingRemote.getCancelledLRUnion(from_date, to_date, type,
				branch_code, station_code, BeanUtil.getDbName());

	}
	
	public GeneralSummaryDTO[] getCCCSummaryPDTPUnion(Date fromDate, Date toDate,
			String branch) throws Exception {
		if (beanutil != null) {
			Reporting reportingRemote = beanutil.getReportingBean();
			return reportingRemote.getCCCSummaryPDTPCountUnion(fromDate, toDate, branch,BeanUtil.getDbName());
		}
		return null;
	}
	
	public GeneralSummaryDTO[] getCCChargeSummaryUnion(Date fromDate, Date toDate,
			String branch) throws Exception {
		if (beanutil != null) {
			Reporting reportingRemote = beanutil.getReportingBean();
			return reportingRemote.getCCChargeSummaryUnion(fromDate, toDate, branch, BeanUtil.getDbName());
		}
		return null;
	}

	public StationsDTO[] getCounterReportMiscUnion(Date from_date, Date to_date,
			String branch_code, StationsDTO[] station) throws Exception {
		Reporting reportingRemote = beanutil.getReportingBean();

		return reportingRemote.getCounterConsolidatedMiscUnion(from_date, to_date,
				branch_code, station, BeanUtil.getDbName());
	}
	
	public StationsDTO[] getCounterReportUnion(Date from_date, Date to_date,
			String branch_code, StationsDTO[] station) throws Exception {
		Reporting reportingRemote = beanutil.getReportingBean();

		return reportingRemote.getCounterConsolidatedUnion(from_date, to_date,
				branch_code, station, BeanUtil.getDbName());
	}
	
	public GeneralSummaryDTO[] getDailyBookingUnion(Date fromDate, Date toDate)
	throws Exception {
		if (beanutil != null) {
			Reporting reportingRemote = beanutil.getReportingBean();
			return reportingRemote.getDailyBookingAnalysisUnion(fromDate, toDate, BeanUtil.getDbName());
		}
		return null;
		}
	
	public DDRDTO[] getDDReportUnion(Date from, Date to, String station) {
		if (beanutil != null) {
			return beanutil.getDDReportUnion(from, to, station);
		}

		return null;
	}
	
	public BookingDTO[] getFOCLRSUnion(Date from_date, Date to_date,
			String branch_code, int type) throws BusinessException, Exception {
		Reporting reportingRemote = beanutil.getReportingBean();
		return reportingRemote.getFOCLRSUnion(from_date, to_date, branch_code, type, BeanUtil.getDbName());

	}
	
	public CustomerBusinessAnalysisDTO[] getIIStatesUnion(Date fromDate, Date toDate) throws Exception {
		if (beanutil != null) {
			Reporting reportingRemote = beanutil.getReportingBean();
			return reportingRemote.getInterIntraStateUnion(fromDate, toDate, BeanUtil.getDbName());
		}
		return null;
	}
	
	public GMROutTimeDTO[] getInwardRegisterUnion(Date date, Date toDate, String branchCode, String stnCode) throws Exception {
		if(beanutil != null){
			Reporting reportingRemote = beanutil.getReportingBean();
			return reportingRemote.getInwardRegisterUnion(date, toDate,branchCode,stnCode, BeanUtil.getDbName());
		}
		return null;
	}
	
	public BookingDTO[] getOpenLRSUnion(Date from_date, Date to_date, String branch)
	throws Exception {
		Reporting reportingRemote = beanutil.getReportingBean();
		return reportingRemote.getOpenLRSUnion(from_date, to_date, branch, BeanUtil.getDbName());

	}
	
	public StationsDTO[] getServicetaxUnion(Date fromDt, Date toDate)throws Exception {	
		if(beanutil != null){
			Reporting reportingRemote = beanutil.getReportingBean();
			return reportingRemote.getServicetaxUnion(fromDt,toDate, BeanUtil.getDbName());
		}
			
		return null;
	}
	
	public StationsDTO[] getServicetaxLRUnion(Date fromDt, Date toDate)throws Exception {		
		if(beanutil != null){
			Reporting reportingRemote = beanutil.getReportingBean();
			return reportingRemote.getServicetaxLRUnion(fromDt,toDate, BeanUtil.getDbName());
		}
			
		return null;
	}
	
	public GeneralSummaryDTO[] getSundryBookingUnion(Date fromDate, Date toDate,String branch, String station)
	throws Exception {
		if (beanutil != null) {
			Reporting reportingRemote = beanutil.getReportingBean();
			return reportingRemote.getSundryBookingAnalysisUnion(fromDate, toDate, branch, station, BeanUtil.getDbName());
		}
		return null;
	}
	
	public GeneralSummaryDTO[] getUnDeliveredTopayUnion(Date fromDate, Date toDate,
			String branch) throws Exception {
		if (beanutil != null) {
			Reporting reportingRemote = beanutil.getReportingBean();
			return reportingRemote.getTotalUnDeliveredTopayUnion(fromDate, toDate, branch, BeanUtil.getDbName());
		}
		return null;
	}


	public BookingDTO[] getUnDeliveredTopayDetailedUnion(String branch,
			Date fromDate, Date toDate)throws Exception {
		// TODO Auto-generated method stub
		if (beanutil != null) {
			return beanutil.getUnDeliveredTopayDetailed(branch, fromDate, toDate);
		}
		return null;
	}
	
}
