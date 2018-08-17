package hm.akr.interfaces;

import hm.akr.dto.BookedLRDTO;
import hm.akr.dto.BookingDTO;
import hm.akr.dto.BusinessPerformanceDTO;
import hm.akr.dto.CurrentStatusDTO;
import hm.akr.dto.CustomerBusinessAnalysisDTO;
import hm.akr.dto.DDRDTO;
import hm.akr.dto.DRSAttendanceDTO;
import hm.akr.dto.DRSDTO;
import hm.akr.dto.DailyDeliveryStatusDTO;
import hm.akr.dto.DailyStatusDTO;
import hm.akr.dto.DeliveryVerificationDTO;
import hm.akr.dto.GMROutTimeDTO;
import hm.akr.dto.GMRReportDTO;
import hm.akr.dto.GeneralSummaryDTO;
import hm.akr.dto.InwardAnalysisDTO;
import hm.akr.dto.MarketVehicleDTO;
import hm.akr.dto.MissingCustomersDTO;
import hm.akr.dto.RemittanceShortageDTO;
import hm.akr.dto.SODDTO;
import hm.akr.dto.TranshipmentDTO;
import hm.akr.dto.VersionDTO;
import hm.akr.dto.StationsDTO;
//import hm.akr.dto.ServiceTaxDTO;
import hm.akr.exceptions.BusinessException;
import hm.akr.dto.ServiceTaxDTO;

import java.rmi.RemoteException;
import java.sql.SQLException;

import java.util.Date;

/**
 * Remote interface for Reporting.
 * 
 * @author Hakuna Matata
 */
public interface Reporting {
	/**
	 * 
	 * @param dto
	 * @throws RemoteException
	 */
	public boolean submitDRS(DRSDTO dto) throws BusinessException,
			RemoteException, Exception;

	/**
	 * 
	 * @param stationCode
	 * @param date
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public BookingDTO[] getStatementOfBooking(String stationCode, Date date,
			String dbHistory) throws RemoteException, Exception;

	/**
	 * 
	 * @param stationCode
	 * @param date
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public BookingDTO[] getOutstandingDetails(String stationCode, Date date)
			throws RemoteException, Exception;

	/**
	 * 
	 * @param stationCode
	 * @param date
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public SODDTO[] getStatementOfDelivery(String stationCode, Date date,
			String dbHistory) throws RemoteException, Exception;
	

	/**
	 * 
	 * @param drNo
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public DRSDTO getDailyRemittance(String drNo) throws RemoteException,
			Exception;

	/**
	 * 
	 * @param stationCode
	 * @param date
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	/*
	 * public DRSDTO getDRSDetails(String stationCode, Date date) throws
	 * RemoteException, Exception;
	 * 
	 * /**
	 * 
	 * @param stationCode @param date @return @throws RemoteException @throws
	 * Exception
	 */
	public DDRDTO[] getStatementOfDoorDeliveryReImbursement(String stationCode,
			java.util.Date date, String dbHistory) throws RemoteException,
			Exception;
	
	public DRSDTO getDRSDetails(String stationCode, Date date)
	throws RemoteException, Exception;
	
	public DRSDTO getDRSDetailsHistory(String stationCode, Date date, String dbHistory)
	throws RemoteException, Exception;


	

	/**
	 * 
	 * @param stationCode
	 * @param date
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */

	/**
	 * 
	 * 
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public DailyStatusDTO[] getDailyStatus(Date date) throws RemoteException,
			Exception;

	/**
	 * 
	 * @param fromDate
	 * @param toDate
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public DailyStatusDTO[] getReport(Date fromDate, Date toDate,
			String dbHistory) throws RemoteException, Exception;

	/**
	 * 
	 * @param stationCode
	 * @param date
	 * @param vehicle
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */

	/**
	 * 
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public VersionDTO[] getVersionReports() throws RemoteException, Exception;

	/**
	 * 
	 * @param fromDate
	 * @param toDate
	 * @param branchCode
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public BookedLRDTO[] getBookedLRs(Date fromDate, Date toDate,
			String branchCode, String dbHistory) throws RemoteException,
			Exception;

	/**
	 * 
	 * @param fromdate
	 * @param todate
	 * @param branch
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public TranshipmentDTO[] getTranshipment(int month, int year,
			String branch, String dbHistory) throws RemoteException, Exception;

	/**
	 * 
	 * @param month
	 * @param year
	 * @param fromStn
	 * @param inwardStn
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	/*public InwardAnalysisDTO[] getInwardAnalysis(int month, int year,
			String fromStn, String inwardStn, String dbHistory)
			throws RemoteException, Exception;*/
	public InwardAnalysisDTO[] getInwardAnalysis(Date fromDate, Date toDate,
			String fromStn, String inwardStn, String dbHistory)
			throws RemoteException, Exception;

	/**
	 * 
	 * @param inch
	 * @param feet
	 * @param cm
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void setUnitVlaues(double inch, double feet, double cm)
			throws RemoteException, Exception;

	/**
	 * 
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public double[] getMeasurements() throws RemoteException, Exception;

	/**
	 * 
	 * @param date
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public int getLRTrackCount(Date date, Date to_date, String dbHistory)
			throws RemoteException, Exception;

	public CurrentStatusDTO[] getCurrentStatus(String code)
			throws RemoteException, Exception;

	/**
	 * 
	 * @return
	 * @throws RemoteException
	 * @throws SQLException
	 * @throws Exception
	 */
	public DeliveryVerificationDTO[] getVerificationReport(String[] details,
			String dbHistory) throws RemoteException, SQLException, Exception;

	/**
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public DailyDeliveryStatusDTO[] getDailyDeliveryStatus(Date date,String branch,
			String dbHistory) throws Exception;

	public BookingDTO[] getStatementOfBookingHistory(String stationCode,
			java.util.Date date, String dbHistory) throws Exception;

	public DDRDTO[] getDoorDeliverReImbursementListHistory(String stationCode,
			java.util.Date date, String dbHistory) throws Exception;

	public GMRReportDTO[] getGoodsMovementListHistory(String stationCode,
			Date date, String dbHistory) throws Exception;

	public VersionDTO[] getHistoryYears() throws Exception;

	public SODDTO[] getStatementOfDeliveryHistory(String stationCode,
			Date date, String dbHistory) throws Exception;

	/**
	 * 
	 * @param stationCode
	 * @param date
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public GMRReportDTO[] getGoodsMovement(String stationCode, Date date,
			String DBHistory) throws RemoteException, Exception;

	/**
	 * 
	 * 
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */

	/**
	 * 
	 * @param stationCode
	 * @param date
	 * @param vehicle
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public GMRReportDTO[] getGoodsMovementForVehicle(String stationCode,
			Date date, String vehicle, String DBHistory)
			throws RemoteException, Exception;

	/**
	 * 
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */

	/**
	 * 
	 * @param month
	 * @param year
	 * @param fromStn
	 * @param inwardStn
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */

	/**
	 * 
	 * @param inch
	 * @param feet
	 * @param cm
	 * @throws RemoteException
	 * @throws Exception
	 */

	/**
	 * 
	 * @return
	 * @throws RemoteException
	 * @throws SQLException
	 * @throws Exception
	 */

	public DDRDTO[] getDDReport(Date from, Date to, String station,
			String DBHistory) throws Exception;

	public DDRDTO[] getDDReportHistory(Date from, Date to, String station,
			String DBHistory) throws Exception;

	public RemittanceShortageDTO[] getRSReport(String branch, int month,
			int year, String DBHistory) throws Exception;

	/**
	 * 
	 * @param fromDate
	 * @param toDate
	 * @return
	 * @throws RemoteException
	 * @throws BusinessException
	 * @throws Exception
	 */
	public BookingDTO[] getCancelledLR(Date fromDate, Date toDate, String type,String branch_code,String station_code,
			String DBHistory) throws RemoteException, BusinessException,
			Exception;

	public BookingDTO[] getBPAReport(Date from, Date to,
			String branch, String DBHistory) throws Exception;

	
	/**
	 * 
	 * @param fromDate
	 * @param toDate
	 * @param branch
	 * @return
	 * @throws Exception
	 */
	public GeneralSummaryDTO[] getCCChargeSummary(Date fromDate, Date toDate,
			String branch, String DBHistory) throws Exception;

	/**
	 * 
	 * @param fromDate
	 * @param toDate
	 * @param branch
	 * @return
	 * @throws Exception
	 */
	public GeneralSummaryDTO[] getCCCSummaryPDTPCount(Date fromDate,
			Date toDate, String branch, String DBHistory) throws Exception;

	/**
	 * 
	 * @param month
	 * @param year
	 * @return
	 * @throws RemoteException
	 * @throws BusinessException
	 * @throws Exception
	 */
	public GeneralSummaryDTO[] getTotalUnDeliveredTopay(Date fromDate, Date toDate,
			String branch, String DBHistory) throws RemoteException,
			BusinessException, Exception;

	/**
	 * 
	 * @param branch_code
	 * @param month
	 * @param year
	 * @return
	 * @throws RemoteException
	 * @throws BusinessException
	 * @throws Exception
	 */
	public BookingDTO[] getTotalUnDeliveredTopayDetailed(String branch_code,
			Date fromDate, Date toDate, String DBHistory) throws RemoteException,
			BusinessException, Exception;

	public CustomerBusinessAnalysisDTO[] getCustomerBusinessAnalysisReport(
			boolean isConsignor, String branch, String from_date,
			String to_date, String DBHistory) throws RemoteException,
			BusinessException, Exception;

	/**
	 * 
	 * @param fromDate
	 * @param toDate
	 * @return
	 * @throws RemoteException
	 * @throws BusinessException
	 * @throws Exception
	 */
	public GeneralSummaryDTO[] getDailyBookingAnalysis(Date fromDate,
			Date toDate, String DBHistory) throws RemoteException,
			BusinessException, Exception;

	public StationsDTO[] getCounterConsolidated(Date fromDate, Date toDate,
			String station_code, StationsDTO[] stations, String DBHistory)
			throws RemoteException, BusinessException, Exception;
	
	public StationsDTO[] getCounterConsolidatedMisc(Date fromDate, Date toDate,
			String station_code, StationsDTO[] stations, String DBHistory)
			throws RemoteException, BusinessException, Exception;

	/**
	 * 
	 * @param fromDate
	 * @param toDate
	 * @param station_code
	 * @param branch_code
	 * @return
	 * @throws RemoteException
	 * @throws BusinessException
	 * @throws Exception
	 */
	public BookingDTO[] getCounterDetailed(Date fromDate, Date toDate,
			String station_code, String branch_code, String DBHistory)
			throws RemoteException, BusinessException, Exception;

	public GeneralSummaryDTO[] getSundryBookingAnalysis(Date fromDate,
			Date toDate,String branch, String station, String DBHistory) throws RemoteException,
			BusinessException, Exception;
	
	public GeneralSummaryDTO[] getSundryBookingAnalysisHistory(Date fromDate,
			Date toDate,String branch, String station, String DBHistory) throws RemoteException,
			BusinessException, Exception;

	public BookingDTO[] getUPDReady(String station_code, String branch_code,
			int inwardDays, boolean isMoreThan, String DBHistory)
			throws RemoteException, Exception;
	
	public BookingDTO[] getUPDReadyHistory(String station_code, String branch_code,
			int inwardDays, boolean isMoreThan, String DBHistory)
			throws RemoteException, Exception;

	public GMROutTimeDTO[] getInwardRegister( Date from,
			Date to, String branchCode, String stnCode, String DBHistory) throws RemoteException, Exception;
	
	public GMROutTimeDTO[] getInwardRegisterHistory(Date from,
			Date to, String branchCode, String stnCode, String DBHistory) throws RemoteException, Exception;

	public CustomerBusinessAnalysisDTO[] getInterIntraState(Date fromDate,
			Date toDate, String DBHistory) throws Exception;

	public CustomerBusinessAnalysisDTO[] getInterIntraStateHistory(Date fromDate,
			Date toDate, String DBHistory) throws Exception;

	
	public MissingCustomersDTO[] getMissingCustomers() throws Exception;

	public MissingCustomersDTO[] getMissingCustomersLastYear() throws Exception;

	/**
	 * 
	 * @param station_code
	 * @param month
	 * @param year
	 * @return
	 * @throws RemoteException
	 * @throws BusinessException
	 * @throws Exception
	 */
	public DRSAttendanceDTO[] getDRSAttendance(int type, String station_code,
			int month, int year,String dbHis) throws RemoteException, BusinessException,
			Exception;

	/**
	 * 
	 * @param fromDate
	 * @param toDate
	 * @param branchCode
	 * @return
	 * @throws RemoteException
	 * @throws BusinessException
	 * @throws Exception
	 */
	public BookingDTO[] getOpenLRS(Date fromDate, Date toDate,
			String branchCode, String DBHistory) throws RemoteException,
			BusinessException, Exception;

	
	public BookingDTO[] getOpenLRSHistory(Date fromDate, Date toDate,
			String branchCode, String DBHistory) throws RemoteException,
			BusinessException, Exception;

	/**
	 * 
	 * @param from_date
	 * @param to_date
	 * @param branch_code
	 * @param type
	 * @return
	 * @throws RemoteException
	 * @throws BusinessException
	 * @throws Exception
	 */
	public BookingDTO[] getFOCLRS(Date from_date, Date to_date,
			String branch_code, int type, String DBHistory)
			throws RemoteException, BusinessException, Exception;
	
	public BookingDTO[] getFOCLRSHistory(Date from_date, Date to_date,
			String branch_code, int type, String DBHistory)
			throws RemoteException, BusinessException, Exception;
	
	
	
	/**
	 * 
	 * @param branch_code
	 * @param month
	 * @param year
	 * @return
	 * @throws RemoteException
	 * @throws BusinessException
	 * @throws Exception
	 */
	public MarketVehicleDTO[] getMarketVehicles(String branch_code, int month,
			int year,String dbHis) throws RemoteException, BusinessException, Exception;
	
	
	public StationsDTO[] getServicetax(Date fromDt, Date toDate,String DBHistory)throws  RemoteException, BusinessException,Exception;
	
	public StationsDTO[] getServicetaxLR(Date fromDt, Date toDate,String DBHistory)throws  RemoteException, BusinessException,Exception;

	/**
	 * 
	 * @param from_date
	 * @param to_date
	 * @param fromStn
	 * @param inwardStn
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public InwardAnalysisDTO[] getLOG(Date from_date, Date to_date,
			String fromStn, String inwardStn,String dbHis) throws RemoteException, Exception;

	public RemittanceShortageDTO[] getRSReportHistory(String branch, int month,
			int year, String DBHistory) throws Exception;

	public BookingDTO[] getCancelledLRHistory(Date fromDate, Date toDate,
			String type,String branch_code,String station_code, String DBHistory) throws RemoteException,
			BusinessException, Exception;

	

	public GeneralSummaryDTO[] getCCChargeSummaryHistory(Date fromDate,
			Date toDate, String branch, String DBHistory) throws Exception;

	public GeneralSummaryDTO[] getCCCSummaryPDTPCountHistory(Date fromDate,
			Date toDate, String branch, String DBHistory) throws Exception;

	public GeneralSummaryDTO[] getTotalUnDeliveredTopayHistory(Date fromDate,
			Date toDate, String branch, String DBHistory) throws RemoteException,
			BusinessException, Exception;

	public BookingDTO[] getTotalUnDeliveredTopayDetailedHistory(String branch_code,
			Date fromDate, Date toDate,String DBHistory) throws RemoteException, BusinessException,
			Exception;
	
	public CustomerBusinessAnalysisDTO[] getCustomerBusinessAnalysisReportHistory(
			boolean isConsignor, String branch, String from_date,
			String to_date, String DBHistory) throws RemoteException,
			BusinessException, Exception;

	public CustomerBusinessAnalysisDTO[] getCnorBusinessAnalysisReport(
			boolean isConsignor, String branch, String from_date, String to_date,String DBHistory)
			throws Exception;
	
	public CustomerBusinessAnalysisDTO[] getCneeBusinessAnalysisReport(
			boolean isConsignor, String branch, String from_date, String to_date,String DBHistory)
			throws Exception;
	public GeneralSummaryDTO[] getDailyBookingAnalysisHistory(Date fromDate,
			Date toDate, String DBHistory) throws RemoteException,
			BusinessException, Exception;

	public StationsDTO[] getCounterConsolidatedHistory(Date fromDate,
			Date toDate, String station_code, StationsDTO[] stations,
			String DBHistory) throws RemoteException, BusinessException,
			Exception;
	
	public StationsDTO[] getCounterConsolidatedMiscHistory(Date fromDate,
			Date toDate, String station_code, StationsDTO[] stations,
			String DBHistory) throws RemoteException, BusinessException,
			Exception;

	public BookingDTO[] getCounterDetailedHistory(Date fromDate, Date toDate,
			String station_code, String branch_code, String DBHistory)
			throws RemoteException, BusinessException, Exception;
	
	public StationsDTO[] getServicetaxHistory(Date fromDt, Date toDate,String DBHistory)throws  RemoteException, BusinessException,Exception;
	
	public StationsDTO[] getServicetaxLRHistory(Date fromDt, Date toDate,String DBHistory)throws  RemoteException, BusinessException,Exception;

	public BookingDTO[] getBPAReportHistory(Date from, Date to, String branch,
			String DBHistory) throws Exception;

	public StationsDTO[] getServicetaxLRUnion(Date fromDt, Date toDate,
			String history)throws  RemoteException, BusinessException,Exception;

	public StationsDTO[] getServicetaxUnion(Date fromDt, Date toDate,
			String history)throws  RemoteException, BusinessException,Exception;

	public BookingDTO[] getFOCLRSUnion(Date from_date, Date to_date,
			String branch_code, int type, String history)throws  RemoteException, BusinessException,Exception;

	public BookingDTO[] getOpenLRSUnion(Date fromDate, Date toDate,
			String branchCode, String history)throws  RemoteException, BusinessException,Exception;

	public CustomerBusinessAnalysisDTO[] getInterIntraStateUnion(Date fromDate,
			Date toDate, String history)throws  RemoteException, BusinessException,Exception;

	public GMROutTimeDTO[] getInwardRegisterUnion(Date from, Date to,
			String branchCode, String stnCode, String history)throws  RemoteException, BusinessException,Exception;

	public GeneralSummaryDTO[] getSundryBookingAnalysisUnion(Date fromDate,
			Date toDate, String branch, String station, String history)throws  RemoteException, BusinessException,Exception;

	public StationsDTO[] getCounterConsolidatedMiscUnion(Date fromDate,
			Date toDate, String station_code, StationsDTO[] stations,
			String history)throws  RemoteException, BusinessException,Exception;

	public StationsDTO[] getCounterConsolidatedUnion(Date fromDate,
			Date toDate, String station_code, StationsDTO[] stations,
			String history)throws  RemoteException, BusinessException,Exception;

	public GeneralSummaryDTO[] getDailyBookingAnalysisUnion(Date fromDate,
			Date toDate, String history)throws  RemoteException, BusinessException,Exception;

	public BookingDTO[] getTotalUnDeliveredTopayDetailedUnion(
			String branch_code, Date fromDate, Date toDate, String history)throws  RemoteException, BusinessException,Exception;

	public GeneralSummaryDTO[] getTotalUnDeliveredTopayUnion(Date fromDate,
			Date toDate, String branch, String history)throws  RemoteException, BusinessException,Exception;

	public GeneralSummaryDTO[] getCCCSummaryPDTPCountUnion(Date fromDate,
			Date toDate, String branch, String history)throws  RemoteException, BusinessException,Exception;

	public GeneralSummaryDTO[] getCCChargeSummaryUnion(Date fromDate,
			Date toDate, String branch, String history)throws  RemoteException, BusinessException,Exception;

	public BookingDTO[] getBPAReportUnion(Date from, Date to, String branch,
			String history)throws  RemoteException, BusinessException,Exception;

	public BookingDTO[] getCancelledLRUnion(Date fromDate, Date toDate,
			String type, String branch_code, String station_code, String history)throws  RemoteException, BusinessException,Exception;

	public RemittanceShortageDTO[] getRSReportUnion(String branch, int month,
			int year, String history)throws  RemoteException, BusinessException,Exception;

	public DDRDTO[] getDDReportUnion(Date from, Date to, String station,
			String history)throws  RemoteException, BusinessException,Exception;

	public BookedLRDTO[] getBookedLRsUnion(Date fromDate, Date toDate,
			String branchCode, String dbHistory)throws  RemoteException, BusinessException,Exception;

	public SODDTO[] getStatementOfDeliveryUnion(String stationCode, Date date,
			String dbHistory)throws  RemoteException, BusinessException,Exception;

	/*public DDRDTO[] getDoorDeliverReImbursementList(String stationCode,
			Date date, String dbHistory)throws  RemoteException, BusinessException,Exception;*/

	public BookingDTO[] getStatementOfBookingUnion(String stationCode,
			Date date, String dbHistory)throws  RemoteException, BusinessException,Exception;
	
	public BookedLRDTO[] getBookedLRsHistory(Date fromDate, Date toDate,
			String branchCode, String dbHistory) throws RemoteException, Exception ;
	
	
	
	public Float getOldLrTotal(String lrno)throws RemoteException,Exception;
	
	public ServiceTaxDTO[] getHolidays(String year)throws RemoteException,Exception;
	

	
}
