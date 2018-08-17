package com.hm.bean.report.ejb;

import hm.akr.dto.BookedLRDTO;
import hm.akr.dto.BookingDTO;
import hm.akr.dto.DDRDTO;
import hm.akr.dto.DRSDTO;
import hm.akr.dto.BusinessPerformanceDTO;
import hm.akr.dto.CurrentStatusDTO;
import hm.akr.dto.CustomerBusinessAnalysisDTO;
import hm.akr.dto.DRSAttendanceDTO;
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
import hm.akr.dto.ServiceTaxDTO;
//import hm.akr.dto.ServiceTaxDTO;
import hm.akr.dto.StationsDTO;

import hm.akr.dto.SODDTO;
import hm.akr.dto.TranshipmentDTO;
import hm.akr.dto.VersionDTO;
import hm.akr.exceptions.BusinessException;
import hm.akr.interfaces.Reporting;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.hm.dao.IReportingDAO;
import com.hm.dao.ReportingDAO;

@Stateless
@Remote(Reporting.class)
@TransactionManagement(TransactionManagementType.BEAN)
/**
 * 
 */
public class ReportingBean implements Reporting {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ReportingBean() {
		super();
	}

	/**
	 * Business Method
	 * 
	 * @param dto
	 * @throws Exception
	 */
	public boolean submitDRS(DRSDTO dto) throws BusinessException,
			RemoteException, Exception {
		boolean flag = false;

		String FUTURE_DATE_ERROR = "The Date should not exceed the current Date";
		String SUBMIT_DATE_ERROR = "DRS can't be submitted for the given Date. It should be submitted within a day.";
		String CONFIRMATION_ERROR = "DRS can't be confirmed within the same day.";

		try {

			Date drsDate = dto.getDate();
			Date currentDate = new Date();

			if (currentDate.before(drsDate)) {
				BusinessException business = new BusinessException();
				business.setResponseMessage(FUTURE_DATE_ERROR);
				throw business;
			} else {

				long diff = currentDate.getTime() - drsDate.getTime();
				diff = diff / (24 * 60 * 60 * 1000);
				if (!dto.isStatus() && diff >= 2) { // Report can't be submitted
					// after two days
					// raise business exception;
					BusinessException business = new BusinessException();
					business.setResponseMessage(SUBMIT_DATE_ERROR);
					throw business;

				} else if (dto.isStatus() && diff < 1) {
					BusinessException business = new BusinessException();
					business.setResponseMessage(CONFIRMATION_ERROR);
					throw business;
				} else {
					IReportingDAO report = new ReportingDAO();
					report.submitDRS(dto);
					flag = true;
				}
			}

		} catch (Exception exception) {
			throw exception;
		}
		return flag;
	}

	/**
	 * 
	 * @param stationCode
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public BookingDTO[] getStatementOfBooking(String stationCode, Date date, String dbHistory)
			throws Exception {
		IReportingDAO sob = new ReportingDAO();
		BookingDTO[] list = null;

		try {

			list = sob.getStatementOfBooking(stationCode, date, dbHistory);

		} catch (Exception exception) {
			throw exception;
		}

		return list;
	}
	
	public BookingDTO[] getStatementOfBookingUnion(String stationCode, Date date, String dbHistory)
	throws Exception {
	IReportingDAO sob = new ReportingDAO();
	BookingDTO[] list = null;
	
	try {
	
		list = sob.getStatementOfBookingUnion(stationCode, date, dbHistory);
	
	} catch (Exception exception) {
		throw exception;
	}
	
	return list;
	}

	/**
	 * 
	 * @param stationCode
	 * @param date
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public DDRDTO[] getStatementOfDoorDeliveryReImbursement(String stationCode,
			Date date, String dbHistory) throws RemoteException, Exception {
		DDRDTO[] ddReimbursement = null;

		IReportingDAO dao = new ReportingDAO();

		try {
			ddReimbursement = dao.getDoorDeliverReImbursementList(stationCode,
					date, dbHistory);
		} catch (Exception exception) {
			throw exception;
		}
		return ddReimbursement;
	}
	
	public DDRDTO[] getStatementOfDoorDeliveryReImbursementUnion(String stationCode,
			Date date, String dbHistory) throws RemoteException, Exception {
		DDRDTO[] ddReimbursement = null;

		IReportingDAO dao = new ReportingDAO();

		try {
			ddReimbursement = dao.getDoorDeliverReImbursementListUnion(stationCode,
					date, dbHistory);
		} catch (Exception exception) {
			throw exception;
		}
		return ddReimbursement;
	}
	
	

	/**
	 * 
	 * @param stationCode
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public SODDTO[] getStatementOfDelivery(String stationCode, Date date, String dbHistory)
			throws Exception {
		IReportingDAO sod = new ReportingDAO();
		SODDTO[] list = null;

		try {
			list = sod.getStatementOfDelivery(stationCode, date, dbHistory);

		} catch (Exception exception) {
			throw exception;
		}

		return list;

	}
	
	public SODDTO[] getStatementOfDeliveryUnion(String stationCode, Date date, String dbHistory)
	throws Exception {
	IReportingDAO sod = new ReportingDAO();
	SODDTO[] list = null;
	
	try {
		list = sod.getStatementOfDeliveryUnion(stationCode, date, dbHistory);
	
	} catch (Exception exception) {
		throw exception;
	}
	
	return list;
	
	}
	
	
	
	public Float getOldLrTotal(String lrno)
	throws Exception {
	IReportingDAO sod = new ReportingDAO();
	Float list = 0.0f;
	
	try {
		list = sod.getOldLrTotal(lrno);
	
	} catch (Exception exception) {
		throw exception;
	}
	
	return list;
	
	}
	
	

	/**
	 * 
	 * @param stationCode
	 * @return
	 * @throws Exception
	 *//*
	public DRSDTO getDRSDetails(String stationCode, Date date,String dbHistory) throws Exception {
		IReportingDAO drs = new ReportingDAO();
		DRSDTO dto = null;

		try {
			String drsNo = stationCode + "DRS" + calculateStatementNumber(date);

			dto = drs.manipulateDRSDetails(stationCode, date,dbHistory);

			if (null != dto) {
				float grandTotal = dto.getGrand_total();
				dto.setNo(drsNo);
				dto.setDate(date);
				

				DRSDTO existingRecord = drs.getDailyRemittance(drsNo, date);
				if (null != existingRecord) {
					dto.setAdd_remittance(existingRecord.getAdd_remittance());
					dto.setLess_remittance(existingRecord.getLess_remittance());
					dto.setStatus(existingRecord.isStatus());
				}

				grandTotal = grandTotal + dto.getAdd_remittance()
						- dto.getLess_remittance() - dto.getDd_reimbursement();
				dto.setGrand_total(grandTotal);
			} else {
				dto = new DRSDTO();
				dto.setNo(drsNo);
				dto.setDate(date);
			}

		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}

	*//**
	 * 
	 * @param stationCode
	 * @return
	 * @throws Exception
	 *//*
	public DRSDTO getDRSDetailsHistory(String stationCode, Date date, String dbHistory) throws Exception {
		IReportingDAO drs = new ReportingDAO();
		DRSDTO dto = null;

		try {
			String drsNo = stationCode + "DRS" + calculateStatementNumber(date);

			dto = drs.manipulateDRSDetailsHistory(stationCode, date, dbHistory);

			if (null != dto) {
				float grandTotal = dto.getGrand_total();
				dto.setNo(drsNo);
				dto.setDate(date);
				dto.setDd_reimbursement(drs.calculateDoorDeliveryReImbursement(
						stationCode, date));

				DRSDTO existingRecord = drs.getDailyRemittance(drsNo, date);
				if (null != existingRecord) {
					dto.setAdd_remittance(existingRecord.getAdd_remittance());
					dto.setLess_remittance(existingRecord.getLess_remittance());
					dto.setStatus(existingRecord.isStatus());
				}

				grandTotal = grandTotal + dto.getAdd_remittance()
						- dto.getLess_remittance() - dto.getDd_reimbursement();
				dto.setGrand_total(grandTotal);
			} else {
				dto = new DRSDTO();
				dto.setNo(drsNo);
				dto.setDate(date);
			}

		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}*/
	
	public DRSDTO getDRSDetails(String stationCode, Date date) throws Exception {
		IReportingDAO drs = new ReportingDAO();
		DRSDTO dto = null;

		try {
			String drsNo = stationCode + "DRS" + calculateStatementNumber(date);

			dto = drs.manipulateDRSDetails(stationCode, date);

			if (null != dto) {
				float grandTotal = dto.getGrand_total();
				dto.setNo(drsNo);
				dto.setDate(date);
				/*dto.setDd_reimbursement(drs.calculateDoorDeliveryReImbursement(
						stationCode, date));*/

				DRSDTO existingRecord = drs.getDailyRemittance(drsNo, date);
				if (null != existingRecord) {
					dto.setAdd_remittance(existingRecord.getAdd_remittance());
					dto.setLess_remittance(existingRecord.getLess_remittance());
					dto.setStatus(existingRecord.isStatus());
				}

				grandTotal = grandTotal + dto.getAdd_remittance()
						- dto.getLess_remittance() - dto.getDd_reimbursement();
				dto.setGrand_total(grandTotal);
			} else {
				dto = new DRSDTO();
				dto.setNo(drsNo);
				dto.setDate(date);
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
	public DRSDTO getDRSDetailsHistory(String stationCode, Date date, String dbHistory) throws Exception {
		IReportingDAO drs = new ReportingDAO();
		DRSDTO dto = null;

		try {
			String drsNo = stationCode + "DRS" + calculateStatementNumber(date);

			dto = drs.manipulateDRSDetailsHistory(stationCode, date, dbHistory);

			if (null != dto) {
				float grandTotal = dto.getGrand_total();
				dto.setNo(drsNo);
				dto.setDate(date);
				/*dto.setDd_reimbursement(drs.calculateDoorDeliveryReImbursement(
						stationCode, date));*/

				DRSDTO existingRecord = drs.getDailyRemittance(drsNo, date);
				if (null != existingRecord) {
					dto.setAdd_remittance(existingRecord.getAdd_remittance());
					dto.setLess_remittance(existingRecord.getLess_remittance());
					dto.setStatus(existingRecord.isStatus());
				}

				grandTotal = grandTotal + dto.getAdd_remittance()
						- dto.getLess_remittance() - dto.getDd_reimbursement();
				dto.setGrand_total(grandTotal);
			} else {
				dto = new DRSDTO();
				dto.setNo(drsNo);
				dto.setDate(date);
			}

		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}
	
	/**
	 * 
	 * @param date
	 * @return
	 */
	private int calculateStatementNumber(Date date) {
		int temp = 0;

		if (null != date) {

			try {

				GregorianCalendar givenDate = new GregorianCalendar();
				givenDate.setTime(date);

				int month = givenDate.get(Calendar.MONTH);
				int year = givenDate.get(Calendar.YEAR);
				int day = givenDate.get(Calendar.DATE);
				int mo = month + 1;

				givenDate.set(year, mo, day);

				if (month < 4)
					year--;
				GregorianCalendar cal = new GregorianCalendar();
				cal.set(year, 3, 31);
				long mill1 = givenDate.getTimeInMillis();
				long mill2 = cal.getTimeInMillis();
				long diff = mill1 - mill2;

				// long diff = date.getTime() - cal.getTime().getTime();

				temp = (int) (diff / (24 * 60 * 60 * 1000)); // Number Of
				// days since
				// financial year
			} catch (Exception exception) {
				return 0;
			}
		}
		return temp;
	}

	/**
	 * 
	 * @param drNo
	 * @return
	 * @throws Exception
	 */
	public DRSDTO getDailyRemittance(String drNo) throws Exception {

		IReportingDAO dao = new ReportingDAO();
		DRSDTO drs = null;
		Date date = null;
		try {
			drs = dao.getDailyRemittance(drNo, date);

		} catch (Exception exception) {
			throw exception;
		}

		return drs;
	}

	/**
	 * 
	 * @param stationCode
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public GMRReportDTO[] getGoodsMovement(String stationCode, Date date,String dbHistory)
			throws Exception {
		IReportingDAO dao = new ReportingDAO();
		GMRReportDTO[] gmr = null;

		try {

			gmr = dao.getGoodsMovementList(stationCode, date,dbHistory);

		} catch (Exception exception) {
			throw exception;
		}

		return gmr;
	}

	@Override
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public DailyStatusDTO[] getDailyStatus(Date date) throws Exception {
		IReportingDAO dao = new ReportingDAO();
		DailyStatusDTO[] dto = null;

		try {

			dto = dao.getDailyStatus(date);

		} catch (Exception exception) {
			throw exception;
		}
		return dto;
	}

	public DailyStatusDTO[] getReport(Date fromDate, Date toDate, String dbHistory)
			throws Exception {
		IReportingDAO dao = new ReportingDAO();
		DailyStatusDTO[] dto = null;

		try {

			dto = dao.getReport(fromDate, toDate, dbHistory);

		} catch (Exception exception) {
			throw exception;
		}
		return dto;
	}

	@Override
	public GMRReportDTO[] getGoodsMovementForVehicle(String stationCode,
			Date date, String vehicle, String dbHistory) throws Exception {

		IReportingDAO dao = new ReportingDAO();
		GMRReportDTO[] gmr = null;

		try {
			gmr = dao.getGoodsMovementForVehicle(stationCode, date, vehicle,dbHistory);

		} catch (Exception exception) {
			throw exception;
		}

		return gmr;
	}

	@Override
	public BookingDTO[] getOutstandingDetails(String stationCode, Date date)
			throws RemoteException, Exception {

		IReportingDAO sob = new ReportingDAO();
		BookingDTO[] outstanding = null;

		try {
			outstanding = sob.getOutstandingDetails(stationCode, date);
		} catch (Exception exception) {
			throw exception;
		}

		return outstanding;

	}

	@Override
	public VersionDTO[] getVersionReports() throws RemoteException, Exception {

		IReportingDAO sob = new ReportingDAO();
		VersionDTO[] versiondto = null;

		try {
			versiondto = sob.getVersionReports();
		} catch (Exception exception) {
			throw exception;
		}

		return versiondto;

	}

	/**
	 * 
	 */
	public BookedLRDTO[] getBookedLRs(Date fromDate, Date toDate,
			String branchCode, String dbHistory) throws RemoteException, Exception {

		IReportingDAO dao = new ReportingDAO();
		BookedLRDTO[] bookedLRdto = null;

		try {
			bookedLRdto = dao.getBookedLRs(fromDate, toDate, branchCode, dbHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return bookedLRdto;

	}
	
	public BookedLRDTO[] getBookedLRsHistory(Date fromDate, Date toDate,
			String branchCode, String dbHistory) throws RemoteException, Exception {

		IReportingDAO dao = new ReportingDAO();
		BookedLRDTO[] bookedLRdto = null;

		try {
			bookedLRdto = dao.getBookedLRsHistory(fromDate, toDate, branchCode, dbHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return bookedLRdto;

	}

	public BookedLRDTO[] getBookedLRsUnion(Date fromDate, Date toDate,
			String branchCode, String dbHistory) throws RemoteException, Exception {

		IReportingDAO dao = new ReportingDAO();
		BookedLRDTO[] bookedLRdto = null;

		try {
			bookedLRdto = dao.getBookedLRsUnion(fromDate, toDate, branchCode, dbHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return bookedLRdto;

	}
	
	@Override
	public TranshipmentDTO[] getTranshipment(int month, int year, String branch, String dbHistory)
			throws RemoteException, Exception {
		IReportingDAO dao = new ReportingDAO();
		TranshipmentDTO[] dto = null;

		try {
			dto = dao.getTranshipment(month, year, branch, dbHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}

	/**
	 * 
	 */
	/*public InwardAnalysisDTO[] getInwardAnalysis(int month, int year,
			String fromStn, String inwardStn, String dbHistory) throws Exception {

		IReportingDAO dao = new ReportingDAO();
		InwardAnalysisDTO[] dto = null;

		try {
			dto = dao.getInwardAnalysis(month, year, fromStn, inwardStn, dbHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}*/

	public InwardAnalysisDTO[] getInwardAnalysis(Date fromDate, Date toDate,
			String fromStn, String inwardStn, String dbHistory) throws Exception {

		IReportingDAO dao = new ReportingDAO();
		InwardAnalysisDTO[] dto = null;

		try {
			dto = dao.getInwardAnalysis(fromDate, toDate, fromStn, inwardStn, dbHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}
	
	public CurrentStatusDTO[] getCurrentStatus(String code)
			throws RemoteException, Exception {
		IReportingDAO dao = new ReportingDAO();
		CurrentStatusDTO[] dto = null;
		dto = dao.getCurrentStatus(code);
		return dto;

	}

	/**
	 * 
	 * @param inch
	 * @param feet
	 * @param cm
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void setUnitVlaues(double inch, double feet, double cm)
			throws RemoteException, Exception {
		IReportingDAO dao = new ReportingDAO();

		dao.setUnitVlaues(inch, feet, cm);

	}

	/**
	 * 
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public double[] getMeasurements() throws RemoteException, Exception {
		double[] units = null;
		IReportingDAO dao = new ReportingDAO();
		units = dao.getMeasurements();
		return units;

	}

	/**
	 * 
	 * @param date
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public int getLRTrackCount(Date date, Date to_date, String dbHistory) throws RemoteException,
			Exception {
		IReportingDAO dao = new ReportingDAO();

		return dao.getLRTrackCount(date, to_date, dbHistory);
	}
	
	/**
	 * 
	 */
	public DeliveryVerificationDTO[] getVerificationReport(String[] details, String dbHistory)
			throws RemoteException, SQLException, Exception {
		try {
			IReportingDAO dao = new ReportingDAO();
			return dao.getVerificationReport(details, dbHistory);
		} catch (Exception exception) {
			throw exception;
		}

	}
	
	/**
	 * 
	 */
	public DailyDeliveryStatusDTO[] getDailyDeliveryStatus(Date date,String branch, String dbHistory)
	throws Exception{
		try {
			IReportingDAO dao = new ReportingDAO();
			return dao.getDailyDeliveryStatus(date,branch, dbHistory);
		} catch (Exception exception) {
			throw exception;
		}
	}

	@Override
	public DDRDTO[] getDoorDeliverReImbursementListHistory(String stationCode,
			Date date, String dbHistory) throws Exception {
		DDRDTO[] ddReimbursement = null;
		IReportingDAO dao = new ReportingDAO();

		try {
			ddReimbursement = dao.getDoorDeliverReImbursementListHistory(stationCode,
					date, dbHistory);
		} catch (Exception exception) {
			throw exception;
		}
		return ddReimbursement;
	}
	@Override
	public GMRReportDTO[] getGoodsMovementListHistory(String stationCode,
			Date date,String dbHistory) throws Exception {
		IReportingDAO dao = new ReportingDAO();
		GMRReportDTO[] gmr = null;

		try {
			gmr = dao.getGoodsMovementListHistory(stationCode, date,dbHistory);

		} catch (Exception exception) {
			throw exception;
		}

		return gmr;
	}

	@Override
	public BookingDTO[] getStatementOfBookingHistory(String stationCode,
			Date date, String dbHistory) throws Exception {
		IReportingDAO sob = new ReportingDAO();
		BookingDTO[] list = null;
		try {
			list = sob.getStatementOfBookingHistory(stationCode, date, dbHistory);

		} catch (Exception exception) {
			throw exception;
		}

		return list;
	}
	@Override
	public SODDTO[] getStatementOfDeliveryHistory(String stationCode, Date date, String dbHistory)
			throws Exception {
		IReportingDAO sod = new ReportingDAO();
		SODDTO[] list = null;

		try {
			list = sod.getStatementOfDeliveryHistory(stationCode, date, dbHistory);

		} catch (Exception exception) {
			throw exception;
		}

		return list;
	}

	public VersionDTO[] getHistoryYears() throws Exception {
		IReportingDAO sod = new ReportingDAO();
		VersionDTO[] list = null;

		try {
			list = sod.getHistoryYears();

		} catch (Exception exception) {
			throw exception;
		}

		return list;
	}
	
	
	
	@Override
	public DDRDTO[] getDDReport(Date from, Date to, String station,String DBHistory)
			throws Exception {

		IReportingDAO dao = new ReportingDAO();
		DDRDTO[] dto = null;

		try {
			dto = dao.getDDReport(from, to, station,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}
	
	
	public DDRDTO[] getDDReportUnion(Date from, Date to, String station,String DBHistory)
	throws Exception {
	
	IReportingDAO dao = new ReportingDAO();
	DDRDTO[] dto = null;
	
	try {
		dto = dao.getDDReportUnion(from, to, station,DBHistory);
	} catch (Exception exception) {
		throw exception;
	}
	
	return dto;
	}


	@Override
	public RemittanceShortageDTO[] getRSReport(String branch, int month,
			int year,String DBHistory) throws Exception {
		IReportingDAO dao = new ReportingDAO();
		RemittanceShortageDTO[] dto = null;

		try {
			dto = dao.getRSReport(branch, month, year,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}
	
	public RemittanceShortageDTO[] getRSReportUnion(String branch, int month,
			int year,String DBHistory) throws Exception {
		IReportingDAO dao = new ReportingDAO();
		RemittanceShortageDTO[] dto = null;

		try {
			dto = dao.getRSReportUnion(branch, month, year,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}

	/**
	 * 
	 */
	public BookingDTO[] getCancelledLR(Date fromDate, Date toDate, String type,String branch_code,String station_code,String DBHistory)
			throws RemoteException, BusinessException, Exception {

		IReportingDAO dao = new ReportingDAO();
		BookingDTO[] dto = null;

		try {
			dto = dao.getCancelledLR(fromDate, toDate, type,branch_code,station_code,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}
	
	public BookingDTO[] getCancelledLRUnion(Date fromDate, Date toDate, String type,String branch_code,String station_code,String DBHistory)
	throws RemoteException, BusinessException, Exception {
	
	IReportingDAO dao = new ReportingDAO();
	BookingDTO[] dto = null;
	
	try {
		dto = dao.getCancelledLRUnion(fromDate, toDate, type,branch_code,station_code,DBHistory);
	} catch (Exception exception) {
		throw exception;
	}
	
	return dto;
	}

	@Override
	public BookingDTO[] getBPAReport(Date from, Date to,
			String branch,String DBHistory) throws Exception {

		IReportingDAO dao = new ReportingDAO();
		BookingDTO[] dto = null;

		try {
			dto = dao.getBPAReport(from, to, branch,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}

	
	public BookingDTO[] getBPAReportUnion(Date from, Date to,
			String branch,String DBHistory) throws Exception {

		IReportingDAO dao = new ReportingDAO();
		BookingDTO[] dto = null;

		try {
			dto = dao.getBPAReportUnion(from, to, branch,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}

	/**
	 * 
	 */
	public GeneralSummaryDTO[] getCCChargeSummary(Date fromDate, Date toDate,
			String branch,String DBHistory) throws RemoteException, BusinessException, Exception {

		IReportingDAO dao = new ReportingDAO();
		GeneralSummaryDTO[] dto = null;
		try {
			dto = dao.getCCChargeSummary(fromDate, toDate, branch,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}

	@Override
	public GeneralSummaryDTO[] getCCCSummaryPDTPCount(Date fromDate,
			Date toDate, String branch,String DBHistory) throws Exception {

		IReportingDAO dao = new ReportingDAO();
		GeneralSummaryDTO[] dto = null;
		try {
			dto = dao.getCCCSummaryPDTPCount(fromDate, toDate, branch,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}
	
	public GeneralSummaryDTO[] getCCChargeSummaryUnion(Date fromDate, Date toDate,
			String branch,String DBHistory) throws RemoteException, BusinessException, Exception {

		IReportingDAO dao = new ReportingDAO();
		GeneralSummaryDTO[] dto = null;
		try {
			dto = dao.getCCChargeSummaryUnion(fromDate, toDate, branch,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}

	
	public GeneralSummaryDTO[] getCCCSummaryPDTPCountUnion(Date fromDate,
			Date toDate, String branch,String DBHistory) throws Exception {

		IReportingDAO dao = new ReportingDAO();
		GeneralSummaryDTO[] dto = null;
		try {
			dto = dao.getCCCSummaryPDTPCountUnion(fromDate, toDate, branch,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}

	/**
	 * 
	 */
	public GeneralSummaryDTO[] getTotalUnDeliveredTopay(Date fromDate, Date toDate,
			String branch,String DBHistory) throws RemoteException, BusinessException, Exception {
		IReportingDAO dao = new ReportingDAO();
		GeneralSummaryDTO[] dto = null;
		try {
			dto = dao.getTotalUnDeliveredTopay(fromDate, toDate, branch,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return dto;

	}
	
	public GeneralSummaryDTO[] getTotalUnDeliveredTopayUnion(Date fromDate, Date toDate,
			String branch,String DBHistory) throws RemoteException, BusinessException, Exception {
		IReportingDAO dao = new ReportingDAO();
		GeneralSummaryDTO[] dto = null;
		try {
			dto = dao.getTotalUnDeliveredTopayUnion(fromDate, toDate, branch,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return dto;

	}

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
			Date fromDate, Date toDate,String DBHistory) throws RemoteException, BusinessException,
			Exception {

		IReportingDAO dao = new ReportingDAO();
		BookingDTO[] dto = null;
		try {
			dto = dao.getTotalUnDeliveredTopayDetailed(branch_code, fromDate, toDate,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return dto;

	}

	public BookingDTO[] getTotalUnDeliveredTopayDetailedUnion(String branch_code,
			Date fromDate, Date toDate,String DBHistory) throws RemoteException, BusinessException,
			Exception {

		IReportingDAO dao = new ReportingDAO();
		BookingDTO[] dto = null;
		try {
			dto = dao.getTotalUnDeliveredTopayDetailedUnion(branch_code, fromDate, toDate,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return dto;

	}
	
	/**
	 * 
	 */
	public CustomerBusinessAnalysisDTO[] getCustomerBusinessAnalysisReport(
			boolean isConsignor, String branch, String from_date, String to_date,String DBHistory)
			throws RemoteException, BusinessException, Exception {

		IReportingDAO dao = new ReportingDAO();
		CustomerBusinessAnalysisDTO[] dto = null;

		try {
			dto = dao.getCustomerBusinessAnalysisReport(isConsignor, branch,
					from_date, to_date,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}

	public CustomerBusinessAnalysisDTO[] getCnorBusinessAnalysisReport(
			boolean isConsignor, String branch, String from_date, String to_date,String DBHistory)
			throws RemoteException, BusinessException, Exception {

		IReportingDAO dao = new ReportingDAO();
		CustomerBusinessAnalysisDTO[] dto = null;

		try {
			dto = dao.getCnorBusinessAnalysisReport(isConsignor, branch,
					from_date, to_date,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}

	public CustomerBusinessAnalysisDTO[] getCneeBusinessAnalysisReport(
			boolean isConsignor, String branch, String from_date, String to_date,String DBHistory)
			throws RemoteException, BusinessException, Exception {

		IReportingDAO dao = new ReportingDAO();
		CustomerBusinessAnalysisDTO[] dto = null;

		try {
			dto = dao.getCneeBusinessAnalysisReport(isConsignor, branch,
					from_date, to_date,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}

	/**
	 * 
	 * @param fromDate
	 * @param toDate
	 * @return
	 * @throws RemoteException
	 * @throws BusinessException
	 * @throws Exception
	 */
	public GeneralSummaryDTO[] getDailyBookingAnalysis(Date fromDate, Date toDate,String DBHistory)
			throws RemoteException, BusinessException, Exception {

		IReportingDAO dao = new ReportingDAO();
		GeneralSummaryDTO[] bookedLr = null;

		try {
			bookedLr = dao.getDailyBookingAnalysis(fromDate, toDate,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return bookedLr;

	}

	
	public GeneralSummaryDTO[] getDailyBookingAnalysisUnion(Date fromDate, Date toDate,String DBHistory)
	throws RemoteException, BusinessException, Exception {
		
		IReportingDAO dao = new ReportingDAO();
		GeneralSummaryDTO[] bookedLr = null;
		
		try {
			bookedLr = dao.getDailyBookingAnalysisUnion(fromDate, toDate,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}
		
		return bookedLr;
		
		}

	/**
	 * 
	 * @param fromDate
	 * @param toDate
	 * @param station_code
	 * @return
	 * @throws RemoteException
	 * @throws BusinessException
	 * @throws Exception
	 */
	public StationsDTO[] getCounterConsolidated(Date fromDate, Date toDate,
			String station_code, StationsDTO[] stations,String DBHistory)
			throws RemoteException, BusinessException, Exception {

		IReportingDAO dao = new ReportingDAO();
		StationsDTO[] dto = null;
		try {
			dto = dao.getCounterConsolidated(fromDate, toDate, station_code,
					stations,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return dto;

	}
	
	public StationsDTO[] getCounterConsolidatedMisc(Date fromDate, Date toDate,
			String station_code, StationsDTO[] stations,String DBHistory)
			throws RemoteException, BusinessException, Exception {

		IReportingDAO dao = new ReportingDAO();
		StationsDTO[] dto = null;
		try {
			dto = dao.getCounterConsolidatedMisc(fromDate, toDate, station_code,
					stations,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return dto;

	}
	
	public StationsDTO[] getCounterConsolidatedUnion(Date fromDate, Date toDate,
			String station_code, StationsDTO[] stations,String DBHistory)
			throws RemoteException, BusinessException, Exception {

		IReportingDAO dao = new ReportingDAO();
		StationsDTO[] dto = null;
		try {
			dto = dao.getCounterConsolidatedUnion(fromDate, toDate, station_code,
					stations,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return dto;

	}
	
	public StationsDTO[] getCounterConsolidatedMiscUnion(Date fromDate, Date toDate,
			String station_code, StationsDTO[] stations,String DBHistory)
			throws RemoteException, BusinessException, Exception {

		IReportingDAO dao = new ReportingDAO();
		StationsDTO[] dto = null;
		try {
			dto = dao.getCounterConsolidatedMiscUnion(fromDate, toDate, station_code,
					stations,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return dto;

	}

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
			String station_code, String branch_code,String DBHistory) throws RemoteException,
			BusinessException, Exception {

		IReportingDAO dao = new ReportingDAO();
		BookingDTO[] dto = null;
		try {
			dto = dao.getCounterDetailed(fromDate, toDate, station_code,
					branch_code,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}

	public GeneralSummaryDTO[] getSundryBookingAnalysis(Date fromDate,
			Date toDate,String branch, String station, String DBHistory) throws RemoteException, BusinessException, Exception {
		IReportingDAO dao = new ReportingDAO();
		GeneralSummaryDTO[] dto = null;
		try {
			dto = dao.getSundryBookingAnalysis(fromDate, toDate,branch, station, DBHistory);
		} catch (Exception exception) {
			throw exception;
		}//System.out.println("dto--"+dto);
		return dto;

	}
	
	public GeneralSummaryDTO[] getSundryBookingAnalysisUnion(Date fromDate,
			Date toDate,String branch, String station, String DBHistory) throws RemoteException, BusinessException, Exception {
		IReportingDAO dao = new ReportingDAO();
		GeneralSummaryDTO[] dto = null;
		try {
			dto = dao.getSundryBookingAnalysisUnion(fromDate, toDate,branch, station, DBHistory);
		} catch (Exception exception) {
			throw exception;
		}//System.out.println("dto--"+dto);
		return dto;

	}

	

	
	public BookingDTO[] getUPDReady(String station_code, String branch_code,
			int inwardDays, boolean isMoreThan,String DBHistory) throws RemoteException, BusinessException,
			Exception {
		IReportingDAO dao = new ReportingDAO();
		BookingDTO[] dto = null;

		try {
			dto = dao.getUPDReady(station_code, branch_code, inwardDays, isMoreThan,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return dto;

	}

	public BookingDTO[] getUPDReadyHistory(String station_code, String branch_code,
			int inwardDays, boolean isMoreThan,String DBHistory) throws RemoteException, BusinessException,
			Exception {
		IReportingDAO dao = new ReportingDAO();
		BookingDTO[] dto = null;

		try {
			dto = dao.getUPDReadyHistory(station_code, branch_code, inwardDays, isMoreThan,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return dto;

	}

	
	public GMROutTimeDTO[] getInwardRegister(Date from, Date to, String branchCode, String stnCode,String DBHistory)
			throws RemoteException, BusinessException, Exception {

		IReportingDAO dao = new ReportingDAO();
		GMROutTimeDTO[] dto = null;

		try {
			dto = dao.getInwardRegister(from, to,branchCode,stnCode,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return dto;

	}
	
	
	public GMROutTimeDTO[] getInwardRegisterUnion(Date from, Date to, String branchCode, String stnCode,String DBHistory)
	throws RemoteException, BusinessException, Exception {
		
		IReportingDAO dao = new ReportingDAO();
		GMROutTimeDTO[] dto = null;
		
		try {
			dto = dao.getInwardRegisterUnion(from, to,branchCode,stnCode,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}
		
		return dto;
		
		}

	public GMROutTimeDTO[] getInwardRegisterHistory(Date from, Date to, String branchCode, String stnCode,String DBHistory)
	throws RemoteException, BusinessException, Exception {

		IReportingDAO dao = new ReportingDAO();
		GMROutTimeDTO[] dto = null;
		
		try {
			dto = dao.getInwardRegisterHistory(from, to,branchCode,stnCode,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}
		
		return dto;
		
		}

	
	@Override
	public CustomerBusinessAnalysisDTO[] getInterIntraState(Date fromDate, Date toDate,String DBHistory)
			throws Exception {
		IReportingDAO dao = new ReportingDAO();
		CustomerBusinessAnalysisDTO[] dto = null;
		try {
			dto = dao.getInterIntraState(fromDate, toDate,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}
	
	public CustomerBusinessAnalysisDTO[] getInterIntraStateUnion(Date fromDate, Date toDate,String DBHistory)
			throws Exception {
		IReportingDAO dao = new ReportingDAO();
		CustomerBusinessAnalysisDTO[] dto = null;
		try {
			dto = dao.getInterIntraStateUnion(fromDate, toDate,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}
		
		return dto;
		}

	public CustomerBusinessAnalysisDTO[] getInterIntraStateHistory(Date fromDate, Date toDate,String DBHistory)
	throws Exception {
		IReportingDAO dao = new ReportingDAO();
		CustomerBusinessAnalysisDTO[] dto = null;
		try {
			dto = dao.getInterIntraStateHistory(fromDate, toDate,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}
		
		return dto;
		}

	
	@Override
	public MissingCustomersDTO[] getMissingCustomers() throws Exception {
		IReportingDAO dao = new ReportingDAO();
		MissingCustomersDTO[] dto = null;
		try {
			dto = dao.getMissingCustomers();
		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}

	@Override
	public MissingCustomersDTO[] getMissingCustomersLastYear() throws Exception {
		IReportingDAO dao = new ReportingDAO();
		MissingCustomersDTO[] dto = null;
		try {
			dto = dao.getMissingCustomersLastYear();
		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}

	public DRSAttendanceDTO[] getDRSAttendance(int type, String station_code,
			int month, int year,String dbHis) throws RemoteException, BusinessException,
			Exception {

		IReportingDAO dao = new ReportingDAO();
		DRSAttendanceDTO[] dto = null;

		try {
			dto = dao.getDRSAttendance(type, station_code, month, year,dbHis);
		} catch (Exception exception) {
			throw exception;
		}

		return dto;

	}

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
	public BookingDTO[] getOpenLRS(Date fromDate, Date toDate, String branchCode,String DBHistory)
			throws RemoteException, BusinessException, Exception {

		IReportingDAO dao = new ReportingDAO();
		BookingDTO[] dto = null;

		try {
			dto = dao.getOpenLRS(fromDate, toDate, branchCode,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}
	
	public BookingDTO[] getOpenLRSUnion(Date fromDate, Date toDate, String branchCode,String DBHistory)
	throws RemoteException, BusinessException, Exception {
		
		IReportingDAO dao = new ReportingDAO();
		BookingDTO[] dto = null;
		
		try {
			dto = dao.getOpenLRSUnion(fromDate, toDate, branchCode,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}
		
		return dto;
		}

	/**
	 * 
	 */
	public BookingDTO[] getFOCLRS(Date from_date, Date to_date,
			String branch_code, int type,String DBHistory) throws RemoteException,
			BusinessException, Exception {

		IReportingDAO dao = new ReportingDAO();
		BookingDTO[] dto = null;

		try {
			dto = dao.getFOCLRS(from_date, to_date, branch_code, type,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}
	
	public BookingDTO[] getFOCLRSUnion(Date from_date, Date to_date,
			String branch_code, int type,String DBHistory) throws RemoteException,
			BusinessException, Exception {

		IReportingDAO dao = new ReportingDAO();
		BookingDTO[] dto = null;

		try {
			dto = dao.getFOCLRSUnion(from_date, to_date, branch_code, type,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}
	

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
			int year,String dbHis) throws RemoteException, BusinessException, Exception {

		IReportingDAO dao = new ReportingDAO();
		MarketVehicleDTO[] dto = null;

		try {
			dto = dao.getMarketVehicles(branch_code, month, year,dbHis);
		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}
	
	
	/**
	 * 
	 */
	public InwardAnalysisDTO[] getLOG(Date from_date, Date to_date,
			String fromStn, String inwardStn,String dbHis) throws Exception {

		IReportingDAO dao = new ReportingDAO();
		InwardAnalysisDTO[] dto = null;

		try {
			dto = dao.getLOG(from_date, to_date, fromStn, inwardStn,dbHis);
		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}

	
	public StationsDTO[] getServicetax(Date fromDt,
			Date toDate, String DBHistory) throws RemoteException,
			BusinessException, Exception {
		 
				IReportingDAO dao = new ReportingDAO();
				StationsDTO[] dto = null;
				try {
					dto = dao.getServicetax(fromDt, toDate, DBHistory);
				} catch (Exception exception) {
					throw exception;
				}
				return dto;

		
	}

	public StationsDTO[] getServicetaxLR(Date fromDt,
			Date toDate, String DBHistory) throws RemoteException,
			BusinessException, Exception {
		 
				IReportingDAO dao = new ReportingDAO();
				StationsDTO[] dto = null;
				try {
					dto = dao.getServicetaxLR(fromDt, toDate, DBHistory);
				} catch (Exception exception) {
					throw exception;
				}
				return dto;

		
	}
	
	public StationsDTO[] getServicetaxUnion(Date fromDt,
			Date toDate, String DBHistory) throws RemoteException,
			BusinessException, Exception {
		 
				IReportingDAO dao = new ReportingDAO();
				StationsDTO[] dto = null;
				try {
					dto = dao.getServicetaxUnion(fromDt, toDate, DBHistory);
				} catch (Exception exception) {
					throw exception;
				}
				return dto;

		
	}

	public StationsDTO[] getServicetaxLRUnion(Date fromDt,
			Date toDate, String DBHistory) throws RemoteException,
			BusinessException, Exception {
		 
				IReportingDAO dao = new ReportingDAO();
				StationsDTO[] dto = null;
				try {
					dto = dao.getServicetaxLRUnion(fromDt, toDate, DBHistory);
				} catch (Exception exception) {
					throw exception;
				}
				return dto;

		
	}
	


	/*History Methods*/
	@Override
	public DDRDTO[] getDDReportHistory(Date from, Date to, String station,
			String DBHistory) throws Exception {

		IReportingDAO dao = new ReportingDAO();
		DDRDTO[] dto = null;

		try {
			dto = dao.getDDReportHistory(from, to, station,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}

	@Override
	public BookingDTO[] getBPAReportHistory(Date from, Date to,
			String branch,String DBHistory) throws Exception {
		IReportingDAO dao = new ReportingDAO();
		BookingDTO[] dto = null;

		try {
			dto = dao.getBPAReportHistory(from, to, branch,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}

	@Override
	public GeneralSummaryDTO[] getCCCSummaryPDTPCountHistory(Date fromDate,
			Date toDate, String branch, String DBHistory) throws Exception {
		IReportingDAO dao = new ReportingDAO();
		GeneralSummaryDTO[] dto = null;
		try {
			dto = dao.getCCCSummaryPDTPCountHistory(fromDate, toDate, branch,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}

	@Override
	public GeneralSummaryDTO[] getCCChargeSummaryHistory(Date fromDate,
			Date toDate, String branch, String DBHistory) throws Exception {
		IReportingDAO dao = new ReportingDAO();
		GeneralSummaryDTO[] dto = null;
		try {
			dto = dao.getCCChargeSummaryHistory(fromDate, toDate, branch,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}

	@Override
	public BookingDTO[] getCancelledLRHistory(Date fromDate, Date toDate,
			String type,String branch_code,String station_code, String DBHistory) throws RemoteException,
			BusinessException, Exception {
		IReportingDAO dao = new ReportingDAO();
		BookingDTO[] dto = null;

		try {
			dto = dao.getCancelledLRHistory(fromDate, toDate, type,branch_code,station_code,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}

	@Override
	public StationsDTO[] getCounterConsolidatedHistory(Date fromDate,
			Date toDate, String station_code, StationsDTO[] stations,
			String DBHistory) throws RemoteException, BusinessException,
			Exception {

		IReportingDAO dao = new ReportingDAO();
		StationsDTO[] dto = null;
		try {
			dto = dao.getCounterConsolidatedHistory(fromDate, toDate, station_code,
					stations,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}
	
	
	@Override
	public StationsDTO[] getCounterConsolidatedMiscHistory(Date fromDate,
			Date toDate, String station_code, StationsDTO[] stations,
			String DBHistory) throws RemoteException, BusinessException,
			Exception {

		IReportingDAO dao = new ReportingDAO();
		StationsDTO[] dto = null;
		try {
			dto = dao.getCounterConsolidatedMiscHistory(fromDate, toDate, station_code,
					stations,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}

	@Override
	public BookingDTO[] getCounterDetailedHistory(Date fromDate, Date toDate,
			String station_code, String branch_code, String DBHistory)
			throws RemoteException, BusinessException, Exception {
		IReportingDAO dao = new ReportingDAO();
		BookingDTO[] dto = null;
		try {
			dto = dao.getCounterDetailedHistory(fromDate, toDate, station_code,
					branch_code,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}

	@Override
	public CustomerBusinessAnalysisDTO[] getCustomerBusinessAnalysisReportHistory(
			boolean isConsignor, String branch, String from_date,
			String to_date, String DBHistory) throws RemoteException,
			BusinessException, Exception {
		IReportingDAO dao = new ReportingDAO();
		CustomerBusinessAnalysisDTO[] dto = null;

		try {
			dto = dao.getCustomerBusinessAnalysisReportHistory(isConsignor, branch,
					from_date, to_date,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}
	
	@Override
	public GeneralSummaryDTO[] getDailyBookingAnalysisHistory(Date fromDate,
			Date toDate, String DBHistory) throws RemoteException,
			BusinessException, Exception {
		IReportingDAO dao = new ReportingDAO();
		GeneralSummaryDTO[] bookedLr = null;

		try {
			bookedLr = dao.getDailyBookingAnalysisHistory(fromDate, toDate,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return bookedLr;
	}

	@Override
	public RemittanceShortageDTO[] getRSReportHistory(String branch, int month,
			int year, String DBHistory) throws Exception {
		IReportingDAO dao = new ReportingDAO();
		RemittanceShortageDTO[] dto = null;

		try {
			dto = dao.getRSReportHistory(branch, month, year,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}

	@Override
	public GeneralSummaryDTO[] getTotalUnDeliveredTopayHistory(Date fromDate, Date toDate, String branch, String DBHistory) throws RemoteException,
			BusinessException, Exception {
		IReportingDAO dao = new ReportingDAO();
		GeneralSummaryDTO[] dto = null;
		try {
			dto = dao.getTotalUnDeliveredTopay(fromDate, toDate, branch,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}	
	
	public BookingDTO[] getTotalUnDeliveredTopayDetailedHistory(String branch_code,
			Date fromDate, Date toDate,String DBHistory) throws RemoteException, BusinessException,
			Exception {

		IReportingDAO dao = new ReportingDAO();
		BookingDTO[] dto = null;
		try {
			dto = dao
					.getTotalUnDeliveredTopayDetailedHistory(branch_code, fromDate, toDate,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return dto;

	}
	
	
	public BookingDTO[] getFOCLRSHistory(Date from_date, Date to_date,
			String branch_code, int type,String DBHistory) throws RemoteException,
			BusinessException, Exception {

		IReportingDAO dao = new ReportingDAO();
		BookingDTO[] dto = null;

		try {
			dto = dao.getFOCLRSHistory(from_date, to_date, branch_code, type,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}
	
	public BookingDTO[] getOpenLRSHistory(Date fromDate, Date toDate, String branchCode,String DBHistory)
	throws RemoteException, BusinessException, Exception {

		IReportingDAO dao = new ReportingDAO();
		BookingDTO[] dto = null;
		
		try {
			dto = dao.getOpenLRSHistory(fromDate, toDate, branchCode,DBHistory);
		} catch (Exception exception) {
			throw exception;
		}
		
		return dto;
		}

	@Override
	public GeneralSummaryDTO[] getSundryBookingAnalysisHistory(Date fromDate,
			Date toDate,String branch,String station, String DBHistory) throws RemoteException,
			BusinessException, Exception {
		 
				IReportingDAO dao = new ReportingDAO();
				GeneralSummaryDTO[] dto = null;
				try {
					dto = dao.getSundryBookingAnalysisHistory(fromDate, toDate,branch, station, DBHistory);
				} catch (Exception exception) {
					throw exception;
				}
				return dto;

		
	}
	
	public StationsDTO[] getServicetaxHistory(Date fromDt,
			Date toDate, String DBHistory) throws RemoteException,
			BusinessException, Exception {
		 
				IReportingDAO dao = new ReportingDAO();
				StationsDTO[] dto = null;
				try {
					dto = dao.getServicetaxHistory(fromDt, toDate, DBHistory);
				} catch (Exception exception) {
					throw exception;
				}
				return dto;

		
	}
/*public GMROutTimeDTO[] getInwardRegister(String stationCode, Date from, Date to)
	throws RemoteException, BusinessException, Exception {

		IReportingDAO dao = new ReportingDAO();
		GMROutTimeDTO[] dto = null;
			try {
			dto = dao.getInwardRegister(stationCode, from, to);
		} catch (Exception exception) {
			throw exception;
		}

		return dto;

	}*/
	public StationsDTO[] getServicetaxLRHistory(Date fromDt,
			Date toDate, String DBHistory) throws RemoteException,
			BusinessException, Exception {
		 
				IReportingDAO dao = new ReportingDAO();
				StationsDTO[] dto = null;
				try {
					dto = dao.getServicetaxLRHistory(fromDt, toDate, DBHistory);
				} catch (Exception exception) {
					throw exception;
				}
				return dto;

		
	}

	
	public ServiceTaxDTO[] getHolidays(String year) throws RemoteException,
			BusinessException, Exception {
		 
				IReportingDAO dao = new ReportingDAO();
				ServiceTaxDTO[] dto = null;
				try {
					dto = dao.getHolidays(year);
				} catch (Exception exception) {
					throw exception;
				}
				return dto;

		
	}

	

	

	

	

	
		
	
}
