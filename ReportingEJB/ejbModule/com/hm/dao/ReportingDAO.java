/**
 * 
 * @version 1.0
 */
package com.hm.dao;

import hm.akr.dto.BookedLRDTO;
import hm.akr.dto.BookingDTO;
import hm.akr.dto.CurrentStatusDTO;
import hm.akr.dto.DDRDTO;
import hm.akr.dto.DRSDTO;
import hm.akr.dto.DailyDeliveryStatusDTO;
import hm.akr.dto.DailyStatusDTO;
import hm.akr.dto.DeliveryVerificationDTO;
import hm.akr.dto.GMRReportDTO;
import hm.akr.dto.InwardAnalysisDTO;
import hm.akr.dto.SODDTO;
//import hm.akr.dto.ServiceTaxDTO;
import hm.akr.dto.ServiceTaxDTO;
import hm.akr.dto.TranshipmentDTO;
import hm.akr.dto.VersionDTO;
import hm.akr.dto.RemittanceDetailsDTO;
import hm.akr.dto.DRSAttendanceDTO;
import hm.akr.dto.MarketVehicleDTO;
import hm.akr.dto.MissingCustomersDTO;
import hm.akr.dto.GeneralSummaryDTO;
import hm.akr.dto.StationsDTO;
import hm.akr.dto.RemittanceShortageDTO;
import hm.akr.dto.CustomerBusinessAnalysisDTO;
import hm.akr.dto.GMROutTimeDTO;
import hm.akr.dto.BusinessPerformanceDTO;

import java.rmi.RemoteException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import hm.akr.exceptions.BusinessException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Basic;

import com.sun.org.apache.xml.internal.security.utils.HelperNodeList;

/**
 * @author HM
 * @version 1.0 Class for ReportingDAO
 */
public class ReportingDAO implements IReportingDAO, DAOConstants {

	

	

	

	

	/**
	 * Constructor method
	 */
	public ReportingDAO() {

	}

	/**
	 * DAO method to get the Statement Of Booking Records for the given station
	 * code and date
	 */
	
	
	
	
	
	public ServiceTaxDTO[] getHolidays(String year) throws Exception {
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			ServiceTaxDTO holiday = null;
			ArrayList<ServiceTaxDTO> service = new ArrayList<ServiceTaxDTO>();
			try{
				DBHelper helper =  new DBHelper();
				con = helper.getConnection();
				if(null != con){
					ps = con.prepareStatement(GET_HOLIDAY_VIEW);
					if(ps != null){
						ps.setString(1, year);
						
						rs = ps.executeQuery();
						
						if(rs != null){
							while(rs.next()){
								holiday = new ServiceTaxDTO();
								holiday.setLrno(rs.getString(SNO));
								holiday.setFrom(rs.getString(HOLIDAY_DESC));
								holiday.setLrdate(rs.getString(HOLIDAY_DATE));
								
								service.add(holiday);
								
							}
						}
					}
				}
			}catch (SQLException sqlexception) {
				sqlexception.printStackTrace();
				throw sqlexception;
			} catch (Exception exception) {
				exception.printStackTrace();
				throw exception;
			} finally {
				try {
					if (null != rs)
						rs.close();
					if (null != ps)
						ps.close();
					if (null != con)
						con.close();
				} catch (Exception exception) {
					if (null != rs)
						rs.close();
					if (null != ps)
						ps.close();
					if (null != con)
						con.close();
				}
			}
			int size = service.size();
			if(size > 0){
				return (ServiceTaxDTO[]) service.toArray(new ServiceTaxDTO[size]);
			}
			
			return null;
	}

	
	
	public Float getOldLrTotal(String lrno)throws Exception{
		Connection con= null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		Float oldlrtotal= 0.0f;
		try{
			DBHelper helper=new DBHelper();
			con  = helper.getConnection();
			if(null != con){
				ps = con.prepareStatement(CHECK_OLDLR_TOTAL);
				
				if(ps != null){
					ps.setString(1, lrno);
					
					rs = ps.executeQuery();
					
					if(null != rs){
						while (rs.next()) {
							if(rs.getString(LR_TYPE).equals("TOPAY")){
								oldlrtotal = rs.getFloat(TOTAL);
							}
								
							
						}
					}
				}
				
			}
		}catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}
		
		return oldlrtotal;
		
	}

	
	
	public BookingDTO[] getStatementOfBooking(String stationCode,
			java.util.Date date, String dbHis) throws Exception {

		Connection con = null;
		PreparedStatement ps = null;

		ResultSet rs = null;
		ArrayList<BookingDTO> sodList = new ArrayList<BookingDTO>();

		BookingDTO stbook = null;

		try {

			if (dbHis == null || dbHis.equals("")) {
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			} else {
				DBHelperHist helperHis = new DBHelperHist();
				con = helperHis.getConnectionHistory(dbHis);
			}

			if (null != con) {
				ps = con.prepareStatement(ST_BOOKING);

				if (ps != null) {
					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(date);

					ps.setString(1, stationCode);
					ps.setString(2, formattedDate);

					rs = ps.executeQuery();
					if (null != rs) {
						int status = 0;
						while (rs.next()) {

							stbook = new BookingDTO();
							stbook.setLrno(rs.getString(LR_NO));
							stbook.setDate(rs.getDate(LR_DATE));
							stbook.setType(rs.getString(LR_TYPE));
							stbook.setTo(rs.getString(TO_STATION));
							stbook.setNo_of_articles(rs.getInt(NO_OF_ARTICLES));
							stbook.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
							stbook.setBft(rs.getFloat(BASIC_FRIEGHT));
							stbook.setCcc(rs.getFloat(CCC));
							stbook.setOther_charges(rs.getFloat(OTHER_CHARGES));
							stbook.setDdc(rs.getFloat(DOOR_DELIVERY_CHARGE));
							stbook.setLrc(rs.getFloat(LRC));
							stbook.setDcc(rs.getFloat(DCC));
							stbook.setIec(rs.getFloat(IEC));
							stbook.setLc(rs.getFloat(LC));
							stbook.setGsc(rs.getFloat(GSC));
							stbook.setStax(rs.getFloat(STAX));
							stbook.setTotal(rs.getFloat(TOTAL));
							//stbook.setOldLrno(rs.getString(OLDLRNO));
							//stbook.setIsUpd(rs.getString(ISUPD));
							status = rs.getInt(LR_STATUS);
							if (status == 1)
								stbook.setStatus(true);
							else
								stbook.setStatus(false);

							sodList.add(stbook);
						}

						int isDRSConfirmed = isDRSConfirmed(con, stationCode,
								formattedDate);
						if (sodList != null && sodList.size() > 0) {
							sodList.get(0).setRate_type(isDRSConfirmed);
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = sodList.size();

		if (size > 0) {
			return (BookingDTO[]) sodList.toArray(new BookingDTO[size]);
		}
		return null;
	}

	public BookingDTO[] getStatementOfBookingUnion(String stationCode,
			java.util.Date date, String dbHis) throws Exception {

		Connection con = null;
		PreparedStatement ps = null;

		ResultSet rs = null;
		ArrayList<BookingDTO> sodList = new ArrayList<BookingDTO>();

		BookingDTO stbook = null;

		try {

			if (dbHis == null || dbHis.equals("")) {
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			} else {
				DBHelperHist helperHis = new DBHelperHist();
				con = helperHis.getConnectionHistory(dbHis);
			}

			if (null != con) {
				ps = con.prepareStatement(ST_BOOKING_UNION);

				if (ps != null) {
					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(date);

					ps.setString(1, stationCode);
					ps.setString(2, formattedDate);
					ps.setString(3, stationCode);
					ps.setString(4, formattedDate);

					rs = ps.executeQuery();
					if (null != rs) {
						int status = 0;
						while (rs.next()) {

							stbook = new BookingDTO();
							stbook.setLrno(rs.getString(LR_NO));
							stbook.setDate(rs.getDate(LR_DATE));
							stbook.setType(rs.getString(LR_TYPE));
							stbook.setTo(rs.getString(TO_STATION));
							stbook.setNo_of_articles(rs.getInt(NO_OF_ARTICLES));
							stbook.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
							stbook.setBft(rs.getFloat(BASIC_FRIEGHT));
							stbook.setCcc(rs.getFloat(CCC));
							stbook.setOther_charges(rs.getFloat(OTHER_CHARGES));
							stbook.setDdc(rs.getFloat(DOOR_DELIVERY_CHARGE));
							stbook.setLrc(rs.getFloat(LRC));
							stbook.setDcc(rs.getFloat(DCC));
							stbook.setIec(rs.getFloat(IEC));
							stbook.setLc(rs.getFloat(LC));
							stbook.setGsc(rs.getFloat(GSC));
							stbook.setStax(rs.getFloat(STAX));
							stbook.setTotal(rs.getFloat(TOTAL));
							status = rs.getInt(LR_STATUS);
							if (status == 1)
								stbook.setStatus(true);
							else
								stbook.setStatus(false);

							sodList.add(stbook);
						}

						int isDRSConfirmed = isDRSConfirmed(con, stationCode,
								formattedDate);
						if (sodList != null && sodList.size() > 0) {
							sodList.get(0).setRate_type(isDRSConfirmed);
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = sodList.size();

		if (size > 0) {
			return (BookingDTO[]) sodList.toArray(new BookingDTO[size]);
		}
		return null;
	}

	private int isDRSConfirmed(Connection con, String stationCode,
			String curdate) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(IS_DRS_CONFIRMED);
			if (ps != null) {
				ps.setString(1, stationCode);
				ps.setString(2, curdate);
				rs = ps.executeQuery();
				if (rs != null) {
					if (rs.next()) {
						return rs.getInt(STATUS);
					}
				}
			}
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();

			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
			}
		}

		return 0;
	}

	/**
	 * DAO method to get the Statement Of Booking Records for the given station
	 * code and date
	 */
	public BookingDTO[] getStatementOfBookingHistory(String stationCode,
			java.util.Date date, String dbHis) throws Exception {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<BookingDTO> sodList = new ArrayList<BookingDTO>();

		BookingDTO stbook = null;

		try {
			if (dbHis == null || dbHis.equals("")) {
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			} else {
				DBHelperHist helperHis = new DBHelperHist();
				con = helperHis.getConnectionHistory(dbHis);
			}

			if (null != con) {
				ps = con.prepareStatement(ST_BOOKING_HISTORY);

				if (ps != null) {
					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(date);

					ps.setString(1, stationCode);
					ps.setString(2, formattedDate);

					rs = ps.executeQuery();
					if (null != rs) {
						int status = 0;
						while (rs.next()) {

							stbook = new BookingDTO();
							stbook.setLrno(rs.getString(LR_NO));
							stbook.setDate(rs.getDate(LR_DATE));
							stbook.setType(rs.getString(LR_TYPE));
							stbook.setTo(rs.getString(TO_STATION));
							stbook.setNo_of_articles(rs.getInt(NO_OF_ARTICLES));
							stbook.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
							stbook.setBft(rs.getFloat(BASIC_FRIEGHT));
							stbook.setCcc(rs.getFloat(CCC));
							stbook.setOther_charges(rs.getFloat(OTHER_CHARGES));
							stbook.setDdc(rs.getFloat(DOOR_DELIVERY_CHARGE));
							stbook.setLrc(rs.getFloat(LRC));
							stbook.setDcc(rs.getFloat(DCC));
							stbook.setIec(rs.getFloat(IEC));
							stbook.setLc(rs.getFloat(LC));
							stbook.setGsc(rs.getFloat(GSC));
							stbook.setStax(rs.getFloat(STAX));
							stbook.setTotal(rs.getFloat(TOTAL));
							status = rs.getInt(LR_STATUS);
							if (status == 1)
								stbook.setStatus(true);
							else
								stbook.setStatus(false);

							sodList.add(stbook);
						}
						int isDRSConfirmed = isDRSConfirmed(con, stationCode,
								formattedDate);
						if (sodList != null && sodList.size() > 0) {
							sodList.get(0).setRate_type(isDRSConfirmed);
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = sodList.size();

		if (size > 0) {
			return (BookingDTO[]) sodList.toArray(new BookingDTO[size]);
		}
		return null;
	}

	/**
	 * 
	 */
	public DRSDTO getDailyRemittance(String drNo, Date date)
			throws SQLException, Exception {

		PreparedStatement ps = null;
		DBHelper dbhelper = new DBHelper();
		Connection con = null;
		ResultSet rs = null;
		DRSDTO drs = null;

		try {
			con = dbhelper.getConnection();

			if (null != con) {
				ps = con.prepareStatement(GET_DRS);

				SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
				String formattedDate = format.format(date);

				if (ps != null) {
					ps.setString(1, drNo);
					ps.setString(2, formattedDate);

					rs = ps.executeQuery();
					if (null != rs) {

						if (rs.next()) {
							drs = new DRSDTO();

							drs.setNo(drNo);
							drs.setAdd_remittance(rs.getFloat(ADD_REMITTANCE));
							drs.setDate(rs.getDate(DR_DATE));
							drs.setDd_reimbursement(rs
									.getFloat(DD_REIMBURSEMENT));
							drs.setGrand_total(rs.getFloat(GRAND_TOTAL));
							drs
									.setLess_remittance(rs
											.getFloat(LESS_REMITTANCE));
							drs.setPaid_collection_amount(rs
									.getFloat(PAID_COLLECTION));
							drs.setStation_code(rs.getString(STATION_CODE));
							drs.setStatus(rs.getBoolean(STATUS));

						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		return drs;
	}

	/**
	 * 
	 */
	private boolean insertDailyRemittanceStatement(DRSDTO dto)
			throws SQLException, Exception {

		DBHelper db = new DBHelper();
		Connection con = null;
		PreparedStatement ps = null;
		boolean flag = false;

		try {
			con = db.getConnection();

			if (con != null) {
				ps = con.prepareStatement(INSERT_DRS);
				if (ps != null) {

					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(dto.getDate());

					ps.setString(1, dto.getNo());
					ps.setString(2, dto.getStation_code());
					ps.setFloat(3, dto.getPaid_collection_amount());
					ps.setFloat(4, dto.getTopay_collection_amount());
					ps.setFloat(5, dto.getAdd_remittance());
					ps.setFloat(6, dto.getLess_remittance());
					ps.setFloat(7, dto.getDd_reimbursement());
					ps.setFloat(8, dto.getGrand_total());
					ps.setBoolean(9, dto.isStatus());

					ps.setString(10, formattedDate);

					int result = ps.executeUpdate();
					if (result > 0) {
						flag = true;
					}
				}
			}
		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		return flag;

	}

	/**
	 * 
	 */
	private boolean updateDailyRemittanceStatement(DRSDTO dto)
			throws SQLException, Exception {

		DBHelper db = new DBHelper();
		Connection con = null;
		PreparedStatement ps = null;
		boolean flag = false;

		try {
			con = db.getConnection();

			if (con != null) {
				ps = con.prepareStatement(UPDATE_DRS);
				if (ps != null) {
					ps.setFloat(1, dto.getPaid_collection_amount());
					ps.setFloat(2, dto.getTopay_collection_amount());
					ps.setFloat(3, dto.getAdd_remittance());
					ps.setFloat(4, dto.getLess_remittance());
					ps.setFloat(5, dto.getDd_reimbursement());
					ps.setFloat(6, dto.getGrand_total());
					ps.setBoolean(7, dto.isStatus());
					ps.setString(8, dto.getNo());

					int result = ps.executeUpdate();
					if (result > 0) {
						flag = true;
					}
				}
			}
		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		return flag;

	}

	/**
	 * DAO method to submit a Daily Remittance statement. This method check if
	 * there is any record available for the given dr no, if present it updates
	 * the record else it calls a method to insert the record.
	 * 
	 * @param dto
	 *            An instance of DRSDTO
	 */
	public boolean submitDRS(DRSDTO dto) throws SQLException, Exception {
		boolean flag = false;

		// Check if the record is already there
		if (null != getDailyRemittance(dto.getNo(), dto.getDate())) {
			// update the record if present
			flag = updateDailyRemittanceStatement(dto);
		} else {
			// insert the record if not present
			flag = insertDailyRemittanceStatement(dto);
		}

		return flag;
	}

	/**
	 * DAO method to get the statement of Delivery Records for the given station
	 * code and given date
	 */
	public SODDTO[] getStatementOfDelivery(String stationCode,
			java.util.Date date, String dbHis) throws Exception {

		Connection con = null;
		PreparedStatement ps = null;
		SODDTO stdeli = null;
		ResultSet rs = null;
		ArrayList<SODDTO> sodList = new ArrayList<SODDTO>();

		try {
			if (dbHis == null || dbHis.equals("")) {
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			} else {
				DBHelperHist helperHis = new DBHelperHist();
				con = helperHis.getConnectionHistory(dbHis);
			}

			if (null != con) {
				ps = con.prepareStatement(ST_DELIVERY);

				if (ps != null) {
					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(date);

					ps.setString(1, stationCode);
					ps.setString(2, formattedDate + "%");
					ps.setString(3, stationCode);
					ps.setString(4, formattedDate + "%");

					rs = ps.executeQuery();

					if (null != rs) {
						while (rs.next()) {

							// The cancelled CR should be removed as it is not
							// delivered
							stdeli = new SODDTO();

							stdeli.setLrno(rs.getString(LR_NO));
							stdeli.setLrDate(rs.getDate(LR_DATE));
							stdeli.setFrom(rs.getString(FROM_STATION));
							stdeli.setNoofarticles(rs.getInt(NO_OF_ARTICLES));
							stdeli.setWeight(rs.getFloat(ACTUAL_WEIGHT));
							stdeli.setLrtype(rs.getString(LR_TYPE));
							stdeli.setTotalAmount(rs.getFloat(TOTAL));
							stdeli.setOthers(rs.getFloat("other"));
							stdeli.setDd(rs.getFloat(DOOR_DELIVERY_CHARGE));
							stdeli.setDemurrage(rs.getFloat(DEMURRAGE));
							stdeli.setUnderCharge(rs.getFloat(UNDER_CHARGE));
							stdeli.setPostage(rs.getFloat(POSTAGE));
							stdeli.setDdExtra(rs.getFloat(DD_EXTRA));
							stdeli.setCrno(rs.getString(CR_NO));
							stdeli.setCrAmt(rs.getFloat(TOTAL_AMOUNT));
							stdeli
									.setDelivered_date(rs
											.getDate(DELIVERED_DATE));
							stdeli.setInward_date(rs.getDate(LAST_INWARD_DATE));
							if (rs.getInt(CR_STATUS) == 1) {
								stdeli.setStatus(true);
							}

							stdeli.setCr_status(rs.getInt(CR_STATUS));
							stdeli.setDdcFree(rs.getFloat(DDC_FREE));
							stdeli.setIsUpd(rs.getString(ISUPD));
							stdeli.setOldLrno(rs.getString(OLDLRNO));
							sodList.add(stdeli);
						}

						int isDRSConfirmed = isDRSConfirmed(con, stationCode,
								formattedDate);

						if (sodList != null && sodList.size() > 0) {
							sodList.get(0).setIsDRSConfirmed(isDRSConfirmed);
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = sodList.size();

		if (size > 0) {
			return (SODDTO[]) sodList.toArray(new SODDTO[size]);
		}
		return null;

	}

	
	public SODDTO[] getStatementOfDeliveryUnion(String stationCode,
			java.util.Date date, String dbHis) throws Exception {

		Connection con = null;
		PreparedStatement ps = null;
		SODDTO stdeli = null;
		ResultSet rs = null;
		ArrayList<SODDTO> sodList = new ArrayList<SODDTO>();

		try {
			if (dbHis == null || dbHis.equals("")) {
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			} else {
				DBHelperHist helperHis = new DBHelperHist();
				con = helperHis.getConnectionHistory(dbHis);
			}

			if (null != con) {
				ps = con.prepareStatement(ST_DELIVERY_UNION);

				if (ps != null) {
					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(date);

					ps.setString(1, stationCode);
					ps.setString(2, formattedDate + "%");
					ps.setString(3, stationCode);
					ps.setString(4, formattedDate + "%");
					ps.setString(5, stationCode);
					ps.setString(6, formattedDate + "%");
					ps.setString(7, stationCode);
					ps.setString(8, formattedDate + "%");
					rs = ps.executeQuery();

					if (null != rs) {
						while (rs.next()) {

							// The cancelled CR should be removed as it is not
							// delivered
							stdeli = new SODDTO();

							stdeli.setLrno(rs.getString(LR_NO));
							stdeli.setLrDate(rs.getDate(LR_DATE));
							stdeli.setFrom(rs.getString(FROM_STATION));
							stdeli.setNoofarticles(rs.getInt(NO_OF_ARTICLES));
							stdeli.setWeight(rs.getFloat(ACTUAL_WEIGHT));
							stdeli.setLrtype(rs.getString(LR_TYPE));
							stdeli.setTotalAmount(rs.getFloat(TOTAL));
							stdeli.setOthers(rs.getFloat("other"));
							stdeli.setDd(rs.getFloat(DOOR_DELIVERY_CHARGE));
							stdeli.setDemurrage(rs.getFloat(DEMURRAGE));
							stdeli.setUnderCharge(rs.getFloat(UNDER_CHARGE));
							stdeli.setPostage(rs.getFloat(POSTAGE));
							stdeli.setDdExtra(rs.getFloat(DD_EXTRA));
							stdeli.setCrno(rs.getString(CR_NO));
							stdeli.setCrAmt(rs.getFloat(TOTAL_AMOUNT));
							stdeli
									.setDelivered_date(rs
											.getDate(DELIVERED_DATE));
							stdeli.setInward_date(rs.getDate(LAST_INWARD_DATE));
							if (rs.getInt(CR_STATUS) == 1) {
								stdeli.setStatus(true);
							}

							stdeli.setCr_status(rs.getInt(CR_STATUS));
							stdeli.setDdcFree(rs.getFloat(DDC_FREE));
							stdeli.setIsUpd(rs.getString(ISUPD));
							stdeli.setOldLrno(rs.getString(OLDLRNO));
							sodList.add(stdeli);
						}

						int isDRSConfirmed = isDRSConfirmed(con, stationCode,
								formattedDate);

						if (sodList != null && sodList.size() > 0) {
							sodList.get(0).setIsDRSConfirmed(isDRSConfirmed);
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = sodList.size();

		if (size > 0) {
			return (SODDTO[]) sodList.toArray(new SODDTO[size]);
		}
		return null;

	}

	
	/**
	 * DAO method to get the statement of Delivery Records for the given station
	 * code and given date
	 */
	public SODDTO[] getStatementOfDeliveryHistory(String stationCode,
			java.util.Date date, String dbHis) throws Exception {

		Connection con = null;
		PreparedStatement ps = null;

		SODDTO stdeli = null;
		ResultSet rs = null;

		ArrayList<SODDTO> sodList = new ArrayList<SODDTO>();

		if (dbHis == null || dbHis.equals("")) {
			DBHelper helper = new DBHelper();
			con = helper.getConnection();
		} else {
			DBHelperHist helperHis = new DBHelperHist();
			con = helperHis.getConnectionHistory(dbHis);
		}

		try {
			if (null != con) {
				ps = con.prepareStatement(ST_DELIVERY_HISTORY);

				if (ps != null) {
					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(date);

					ps.setString(1, stationCode);
					ps.setString(2, formattedDate + "%");
					ps.setString(3, stationCode);
					ps.setString(4, formattedDate + "%");

					rs = ps.executeQuery();

					if (null != rs) {
						while (rs.next()) {

							// The cancelled CR should be removed as it is not
							// delivered
							stdeli = new SODDTO();

							stdeli.setLrno(rs.getString(LR_NO));
							stdeli.setLrDate(rs.getDate(LR_DATE));
							stdeli.setFrom(rs.getString(FROM_STATION));
							stdeli.setNoofarticles(rs.getInt(NO_OF_ARTICLES));
							stdeli.setWeight(rs.getFloat(ACTUAL_WEIGHT));
							stdeli.setLrtype(rs.getString(LR_TYPE));
							stdeli.setTotalAmount(rs.getFloat(TOTAL));
							stdeli.setOthers(rs.getFloat("other"));
							stdeli.setDd(rs.getFloat(DOOR_DELIVERY_CHARGE));
							stdeli.setDemurrage(rs.getFloat(DEMURRAGE));
							stdeli.setUnderCharge(rs.getFloat(UNDER_CHARGE));
							stdeli.setPostage(rs.getFloat(POSTAGE));
							stdeli.setDdExtra(rs.getFloat(DD_EXTRA));
							stdeli.setCrno(rs.getString(CR_NO));
							stdeli.setCrAmt(rs.getFloat(TOTAL_AMOUNT));
							stdeli
									.setDelivered_date(rs
											.getDate(DELIVERED_DATE));
							stdeli.setInward_date(rs.getDate(LAST_INWARD_DATE));
							if (rs.getInt(CR_STATUS) == 1) {
								stdeli.setStatus(true);
							}
							stdeli.setCr_status(rs.getInt(CR_STATUS));
							stdeli.setDdcFree(rs.getFloat(DDC_FREE));
							stdeli.setIsUpd(rs.getString(ISUPD));
							stdeli.setOldLrno(rs.getString(OLDLRNO));
							sodList.add(stdeli);
						}
						int isDRSConfirmed = isDRSConfirmed(con, stationCode,
								formattedDate);
						if (sodList != null && sodList.size() > 0) {
							sodList.get(0).setIsDRSConfirmed(isDRSConfirmed);
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = sodList.size();

		if (size > 0) {
			return (SODDTO[]) sodList.toArray(new SODDTO[size]);
		}
		return null;

	}

	/**
	 * 
	 * @param date
	 *            This parameter should not be null. This method will throw
	 *            exception if it is null.
	 * 
	 * @throws Exception
	 */
	public DRSDTO manipulateDRSDetails(String stationCode, java.util.Date date) throws Exception {

		Connection con = null;
		CallableStatement cs = null;
		DBHelper helper = new DBHelper();

		DRSDTO dto = null;
		ResultSet rs = null;

		try {
			con = helper.getConnection();

			if (null != con) {
				cs = con.prepareCall("{call calc_drs(?,?)}");
				if (cs != null) {
					SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = sdf.format(date);
					cs.setString(1, stationCode);
					cs.setString(2, formattedDate);
					cs.executeUpdate();
					rs = cs.getResultSet();
					if (null != rs) {
						if (rs.next()) {
							dto = new DRSDTO();

							dto.setFrom_LR_NO(rs.getString("paid_from"));
							dto.setTo_LR_NO(rs.getString("paid_to"));
							dto.setPaid_collection_amount(rs
									.getFloat("paid_total"));

							dto.setFrom_CR_NO(rs.getString("cr_from"));
							dto.setTo_CR_NO(rs.getString("cr_to"));
							dto.setTopay_collection_amount(rs
									.getFloat("cr_total"));
							dto.setDd_reimbursement(rs.getFloat("dd_reimp"));
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != cs)
					cs.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != cs)
					cs.close();
				if (null != con)
					con.close();

			}
		}

		return dto;
	}

	public DRSDTO manipulateDRSDetailsHistory(String stationCode,
			java.util.Date date, String dbHis) throws Exception {

		Connection con = null;
		CallableStatement cs = null;
		DRSDTO dto = null;
		ResultSet rs = null;

		try {

			if (dbHis == null || dbHis.equals("")) {
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			} else {
				DBHelperHist helperHis = new DBHelperHist();
				con = helperHis.getConnectionHistory(dbHis);
			}

			if (null != con) {
				cs = con.prepareCall("{call calc_drs_history(?,?)}");
				if (cs != null) {
					SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = sdf.format(date);
					cs.setString(1, stationCode);
					cs.setString(2, formattedDate);
					cs.executeUpdate();
					rs = cs.getResultSet();
					if (null != rs) {
						if (rs.next()) {
							dto = new DRSDTO();

							dto.setFrom_LR_NO(rs.getString("paid_from"));
							dto.setTo_LR_NO(rs.getString("paid_to"));
							dto.setPaid_collection_amount(rs
									.getFloat("paid_total"));

							dto.setFrom_CR_NO(rs.getString("cr_from"));
							dto.setTo_CR_NO(rs.getString("cr_to"));
							dto.setTopay_collection_amount(rs
									.getFloat("cr_total"));
							dto.setDd_reimbursement(rs.getFloat("dd_reimp"));
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != cs)
					cs.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != cs)
					cs.close();
				if (null != con)
					con.close();

			}
		}

		return dto;
	}

	/**
	 * 
	 */
	public DDRDTO[] getDoorDeliverReImbursementList(String stationCode,
			java.util.Date date, String dbHis) throws Exception {

		Connection con = null;
		PreparedStatement ps = null;
		DDRDTO ddr = null;
		ResultSet rs = null;

		ArrayList<DDRDTO> ddrList = new ArrayList<DDRDTO>();

		try {

			if (dbHis == null || dbHis.equals("")) {
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			} else {
				DBHelperHist helperHis = new DBHelperHist();
				con = helperHis.getConnectionHistory(dbHis);
			}

			if (null != con) {

				ps = con.prepareStatement(GET_DDREIMBURSEMENT);

				if (ps != null) {
					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(date);

					ps.setString(1, stationCode);
					ps.setString(2, formattedDate);
					/*
					 * ps.setString(3, stationCode); ps.setString(4,
					 * formattedDate);
					 */

					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {
							ddr = new DDRDTO();

							ddr.setLrNo(rs.getString(LR_NO));
							ddr.setLrDate(rs.getDate(LR_DATE));
							ddr.setLrType(rs.getString(LR_TYPE));
							ddr.setNoOfArticles(rs.getInt(NO_OF_ARTICLES));
							ddr.setActualWeight(rs.getFloat(ACTUAL_WEIGHT));
							ddr.setBasicFreight(rs.getFloat(BASIC_FRIEGHT));
							ddr.setCrDate(rs.getDate(CR_DATE));
							ddr.setCrno(rs.getString(CR_NO));
							ddr.setDdc(rs.getFloat(DOOR_DELIVERY_CHARGE));
							ddr.setDdExtra(rs.getFloat(DD_EXTRA));
							ddr.setOthers(rs.getFloat(OTHER_CHARGES));
							ddr.setFromStation(rs.getString(FROM_STATION));
							ddr.setTotal(rs.getFloat(TOTAL));
							ddr.setDdcFree(rs.getFloat(DDC_FREE));

							ddr.setCr_status(rs.getInt(CR_STATUS));

							ddrList.add(ddr);
						}
						int isDRSConfirmed = isDRSConfirmed(con, stationCode,
								formattedDate);
						if (ddrList != null && ddrList.size() > 0) {
							ddrList.get(0).setIsDRSConfirmed(isDRSConfirmed);
						}
					}
				}
			}
		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = ddrList.size();
		if (size > 0) {
			return (DDRDTO[]) ddrList.toArray(new DDRDTO[size]);
		}
		return null;
	}
	
	public DDRDTO[] getDoorDeliverReImbursementListUnion(String stationCode,
			java.util.Date date, String dbHis) throws Exception {

		Connection con = null;
		PreparedStatement ps = null;
		DDRDTO ddr = null;
		ResultSet rs = null;

		ArrayList<DDRDTO> ddrList = new ArrayList<DDRDTO>();

		try {

			if (dbHis == null || dbHis.equals("")) {
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			} else {
				DBHelperHist helperHis = new DBHelperHist();
				con = helperHis.getConnectionHistory(dbHis);
			}

			if (null != con) {

				ps = con.prepareStatement(GET_DDREIMBURSEMENT_UNION);

				if (ps != null) {
					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(date);

					ps.setString(1, stationCode);
					ps.setString(2, formattedDate);
					ps.setString(3, stationCode);
					ps.setString(4, formattedDate);
					/*
					 * ps.setString(3, stationCode); ps.setString(4,
					 * formattedDate);
					 */

					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {
							ddr = new DDRDTO();

							ddr.setLrNo(rs.getString(LR_NO));
							ddr.setLrDate(rs.getDate(LR_DATE));
							ddr.setLrType(rs.getString(LR_TYPE));
							ddr.setNoOfArticles(rs.getInt(NO_OF_ARTICLES));
							ddr.setActualWeight(rs.getFloat(ACTUAL_WEIGHT));
							ddr.setBasicFreight(rs.getFloat(BASIC_FRIEGHT));
							ddr.setCrDate(rs.getDate(CR_DATE));
							ddr.setCrno(rs.getString(CR_NO));
							ddr.setDdc(rs.getFloat(DOOR_DELIVERY_CHARGE));
							ddr.setDdExtra(rs.getFloat(DD_EXTRA));
							ddr.setOthers(rs.getFloat(OTHER_CHARGES));
							ddr.setFromStation(rs.getString(FROM_STATION));
							ddr.setTotal(rs.getFloat(TOTAL));
							ddr.setDdcFree(rs.getFloat(DDC_FREE));

							ddr.setCr_status(rs.getInt(CR_STATUS));

							ddrList.add(ddr);
						}
						int isDRSConfirmed = isDRSConfirmed(con, stationCode,
								formattedDate);
						if (ddrList != null && ddrList.size() > 0) {
							ddrList.get(0).setIsDRSConfirmed(isDRSConfirmed);
						}
					}
				}
			}
		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = ddrList.size();
		if (size > 0) {
			return (DDRDTO[]) ddrList.toArray(new DDRDTO[size]);
		}
		return null;
	}

	

	/**
	 * 
	 */
	public DDRDTO[] getDoorDeliverReImbursementListHistory(String stationCode,
			java.util.Date date, String dbHis) throws Exception {

		Connection con = null;
		PreparedStatement ps = null;
		DDRDTO ddr = null;
		ResultSet rs = null;

		ArrayList<DDRDTO> ddrList = new ArrayList<DDRDTO>();

		try {
			if (dbHis == null || dbHis.equals("")) {
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			} else {
				DBHelperHist helperHis = new DBHelperHist();
				con = helperHis.getConnectionHistory(dbHis);
			}

			if (null != con) {

				ps = con.prepareStatement(GET_DDREIMBURSEMENT_HISTORY);

				if (ps != null) {
					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(date);

					ps.setString(1, stationCode);
					ps.setString(2, formattedDate);
					/*
					 * ps.setString(3, stationCode); ps.setString(4,
					 * formattedDate);
					 */

					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {
							ddr = new DDRDTO();

							ddr.setLrNo(rs.getString(LR_NO));
							ddr.setLrDate(rs.getDate(LR_DATE));
							ddr.setLrType(rs.getString(LR_TYPE));
							ddr.setNoOfArticles(rs.getInt(NO_OF_ARTICLES));
							ddr.setActualWeight(rs.getFloat(ACTUAL_WEIGHT));
							ddr.setBasicFreight(rs.getFloat(BASIC_FRIEGHT));
							ddr.setCrDate(rs.getDate(CR_DATE));
							ddr.setCrno(rs.getString(CR_NO));
							ddr.setDdc(rs.getFloat(DOOR_DELIVERY_CHARGE));
							ddr.setDdExtra(rs.getFloat(DD_EXTRA));
							ddr.setOthers(rs.getFloat(OTHER_CHARGES));
							ddr.setFromStation(rs.getString(FROM_STATION));
							ddr.setTotal(rs.getFloat(TOTAL));
							ddr.setDdcFree(rs.getFloat(DDC_FREE));

							ddrList.add(ddr);
						}
					}
				}
			}
		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = ddrList.size();
		if (size > 0) {
			return (DDRDTO[]) ddrList.toArray(new DDRDTO[size]);
		}
		return null;
	}

	/**
	 * 
	 * @param stationCode
	 * @param date
	 * @return
	 */
	public float calculateDoorDeliveryReImbursement(String stationCode,
			java.util.Date date) throws Exception {

		Connection con = null;
		PreparedStatement ps = null;
		DBHelper helper = new DBHelper();

		ResultSet rs = null;
		float ddReImbursement = 0;

		try {
			con = helper.getConnection();

			if (null != con) {
				ps = con.prepareStatement(CALCULATE_DOOR_DELIVERY);

				if (ps != null) {
					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(date);

					ps.setString(1, stationCode);
					ps.setString(2, formattedDate);
					ps.setString(3, stationCode);
					ps.setString(4, formattedDate);

					rs = ps.executeQuery();
					if (null != rs) {

						while (rs.next()) {
							ddReImbursement += rs.getFloat(DDCHARGES);
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		return ddReImbursement;
	}

	/**
	 * 
	 */
	public GMRReportDTO[] getGoodsMovementList(String stationCode, Date date,
			String dbHistory) throws Exception {
		Connection con = null;
		PreparedStatement pst = null;
		ArrayList<GMRReportDTO> list = new ArrayList<GMRReportDTO>();
		GMRReportDTO goods = null;
		ResultSet rs = null;
		java.sql.Timestamp dbSqlTimestamp = null;
		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();

			if (null != con) {

				pst = con.prepareStatement(GET_GOODS_MOVEMENT);

				if (pst != null) {

					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(date);

					pst.setString(1, stationCode);
					pst.setString(2, formattedDate);
					pst.setString(3, stationCode);
					pst.setString(4, formattedDate);

					rs = pst.executeQuery();

					if (null != rs) {

						while (rs.next()) {
							goods = new GMRReportDTO();

							goods.setLrNo(rs.getString(LR_NO));
							goods.setBookingDate(rs.getDate(LR_DATE));
							dbSqlTimestamp = rs.getTimestamp(OUTWARD_TIME);
							goods.setVehicleDate(dbSqlTimestamp);
							goods.setFrom_station(rs.getString(FROM_STATION));
							goods.setTo_station(rs.getString(TO_STATION));
							goods.setConsignor_name(rs
									.getString(CONSIGNOR_NAME));
							goods.setConsignee_name(rs
									.getString(CONSIGNEE_NAME));
							goods.setWeight(rs.getFloat(ACTUAL_WEIGHT));
							goods.setNo_of_articles(rs.getInt(NO_OF_ARTICLES));
							goods.setVehicleNo(rs.getString(VEHICLE_NO));
							goods.setMoved_to(rs.getString(MOVED_TO));
							goods.setDriverName(rs.getString(DRIVER_NAME));
							goods.setArticle_id(rs.getInt(ARTICLE_ID));
							goods.setBft(rs.getFloat(TOTAL));
							list.add(goods);
						}
					}
				}

			}
		} catch (SQLException sqlexception) {
			throw sqlexception;
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != pst)
					pst.close();
				if (null != con)
					con.close();

			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != pst)
					pst.close();
				if (null != con)
					con.close();
			}
		}

		int size = list.size();

		if (size > 0) {
			return (GMRReportDTO[]) list.toArray(new GMRReportDTO[size]);
		}

		return null;

	}

	public GMRReportDTO[] getGoodsMovementListHistory(String stationCode,
			Date date, String dbHistory) throws Exception {
		Connection con = null;
		PreparedStatement pst = null;
		ArrayList<GMRReportDTO> list = new ArrayList<GMRReportDTO>();
		GMRReportDTO goods = null;
		ResultSet rs = null;
		java.sql.Timestamp dbSqlTimestamp = null;
		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();

			if (null != con) {

				pst = con.prepareStatement(GET_GOODS_MOVEMENT_HISTORY);

				if (pst != null) {

					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(date);

					pst.setString(1, stationCode);
					pst.setString(2, formattedDate);
					pst.setString(3, stationCode);
					pst.setString(4, formattedDate);

					rs = pst.executeQuery();

					if (null != rs) {

						while (rs.next()) {
							goods = new GMRReportDTO();

							goods.setLrNo(rs.getString(LR_NO));
							goods.setBookingDate(rs.getDate(LR_DATE));
							dbSqlTimestamp = rs.getTimestamp(OUTWARD_TIME);
							goods.setVehicleDate(dbSqlTimestamp);
							goods.setFrom_station(rs.getString(FROM_STATION));
							goods.setTo_station(rs.getString(TO_STATION));
							goods.setConsignor_name(rs
									.getString(CONSIGNOR_NAME));
							goods.setConsignee_name(rs
									.getString(CONSIGNEE_NAME));
							goods.setWeight(rs.getFloat(ACTUAL_WEIGHT));
							goods.setNo_of_articles(rs.getInt(NO_OF_ARTICLES));
							goods.setVehicleNo(rs.getString(VEHICLE_NO));
							goods.setMoved_to(rs.getString(MOVED_TO));
							goods.setDriverName(rs.getString(DRIVER_NAME));
							goods.setArticle_id(rs.getInt(ARTICLE_ID));
							goods.setBft(rs.getFloat(TOTAL));
							list.add(goods);
						}
					}
				}

			}
		} catch (SQLException sqlexception) {
			throw sqlexception;
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != pst)
					pst.close();
				if (null != con)
					con.close();

			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != pst)
					pst.close();
				if (null != con)
					con.close();
			}
		}

		int size = list.size();

		if (size > 0) {
			return (GMRReportDTO[]) list.toArray(new GMRReportDTO[size]);
		}

		return null;

	}

	/**
	 * 
	 * @param stationCode
	 * @param date
	 * @param vehicle
	 * @return
	 * @throws Exception
	 */
	public GMRReportDTO[] getGoodsMovementForVehicle(String stationCode,
			Date date, String vehicle, String dbHistory) throws Exception {

		Connection con = null;
		PreparedStatement pst = null;
		ArrayList<GMRReportDTO> list = new ArrayList<GMRReportDTO>();
		GMRReportDTO goods = null;
		ResultSet rs = null;
		java.sql.Timestamp dbSqlTimestamp = null;

		try {

			DBHelper db = new DBHelper();
			con = db.getConnection();

			if (null != con) {

				pst = con.prepareStatement(GET_GOODS_MOVEMENT_VEHICLE);

				if (pst != null) {

					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(date);

					pst.setString(1, stationCode);
					pst.setString(2, formattedDate);
					pst.setString(3, vehicle);
					pst.setString(4, stationCode);
					pst.setString(5, formattedDate);
					pst.setString(6, vehicle);

					rs = pst.executeQuery();

					if (null != rs) {

						while (rs.next()) {
							goods = new GMRReportDTO();

							goods.setLrNo(rs.getString(LR_NO));
							goods.setBookingDate(rs.getDate(OUTWARD_TIME));
							dbSqlTimestamp = rs.getTimestamp(OUTWARD_TIME);
							goods.setVehicleDate(dbSqlTimestamp);
							goods.setFrom_station(rs.getString(FROM_STATION));
							goods.setTo_station(rs.getString(TO_STATION));
							goods.setConsignor_name(rs
									.getString(CONSIGNOR_NAME));
							goods.setConsignee_name(rs
									.getString(CONSIGNEE_NAME));
							goods.setWeight(rs.getFloat(ACTUAL_WEIGHT));
							goods.setNo_of_articles(rs.getInt(NO_OF_ARTICLES));
							goods.setVehicleNo(rs.getString(VEHICLE_NO));
							goods.setMoved_to(rs.getString(MOVED_TO));

							list.add(goods);
						}
					}
				}

			}
		} catch (SQLException sqlexception) {
			throw sqlexception;
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != pst)
					pst.close();
				if (null != con)
					con.close();

			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != pst)
					pst.close();
				if (null != con)
					con.close();
			}
		}

		int size = list.size();

		if (size > 0) {
			return (GMRReportDTO[]) list.toArray(new GMRReportDTO[size]);
		}

		return null;

	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public DailyStatusDTO[] getDailyStatus(Date date) throws SQLException,
			Exception {

		Connection con = null;
		CallableStatement cs = null;
		DBHelper helper = new DBHelper();
		ArrayList<DailyStatusDTO> list = new ArrayList<DailyStatusDTO>();

		try {
			con = helper.getConnection();

			if (null != con) {
				con.setAutoCommit(false);
				cs = con.prepareCall("{call da(?)}");
				SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
				String formattedDate = format.format(date);

				cs.setString(1, formattedDate);

				if (cs != null) {

					int check = cs.executeUpdate();
					list = retriveStatus(con);

					if (list != null) {
						con.commit();
					}
				}
			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != con)
					con.close();
			}
		}

		int size = list.size();

		if (size > 0) {
			return (DailyStatusDTO[]) list.toArray(new DailyStatusDTO[size]);
		}
		return null;
	}

	/**
	 * 
	 * @param con
	 * @return
	 * @throws Exception
	 */
	private ArrayList<DailyStatusDTO> retriveStatus(Connection con)
			throws Exception {
		DailyStatusDTO dto = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<DailyStatusDTO> list = new ArrayList<DailyStatusDTO>();
		try {

			ps = con.prepareStatement(GET_DAILY_STATUS);
			if (ps != null) {
				rs = ps.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						dto = new DailyStatusDTO();
						dto.setBranch_code(rs.getString(BRANCH_CODE));
						dto.setDs_no(rs.getInt("ds_no"));
						dto.setStation_code(rs.getString(STATION_CODE));
						dto.setName(rs.getString(STATION_NAME));
						dto.setTopay_count(rs.getInt(TOPAY_TYPE));
						dto.setPaid_count(rs.getInt(PAID_TYPE));
						dto.setBilling_count(rs.getInt(BILLING_TYPE));
						dto.setGMRintime_count(rs.getInt(GMR_INTIME));
						dto.setGMRouttime_count(rs.getInt(GMR_OUTTIME));
						dto.setCr_count(rs.getInt(CR_COUNT));

						list.add(dto);
					}
				}
			}
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();

			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
			}
		}

		return list;
	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public DailyStatusDTO[] getReport(Date fromDate, Date toDate, String dbHis)
			throws SQLException, Exception {

		Connection con = null;
		CallableStatement cs = null;
		ArrayList<DailyStatusDTO> list = new ArrayList<DailyStatusDTO>();

		try {
			if (dbHis == null || dbHis.equals("")) {
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			} else {
				DBHelperHist helperHis = new DBHelperHist();
				con = helperHis.getConnectionHistory(dbHis);
			}

			if (null != con) {
				con.setAutoCommit(false);
				cs = con.prepareCall("{call report(?, ?)}");
				SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
				String formattedDate = format.format(fromDate);
				String formattedToDate = format.format(toDate);

				cs.setString(1, formattedDate);
				cs.setString(2, formattedToDate);

				if (cs != null) {

					int check = cs.executeUpdate();
					list = retriveReport(con);

					if (list != null) {
						con.commit();
					}
				}
			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != con)
					con.close();
			}
		}

		int size = list.size();

		if (size > 0) {
			return (DailyStatusDTO[]) list.toArray(new DailyStatusDTO[size]);
		}
		return null;
	}

	/**
	 * 
	 * @param con
	 * @return
	 * @throws Exception
	 */
	private ArrayList<DailyStatusDTO> retriveReport(Connection con)
			throws Exception {
		DailyStatusDTO dto = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<DailyStatusDTO> list = new ArrayList<DailyStatusDTO>();
		try {

			ps = con.prepareStatement(GET_DAILY_STATUS);
			if (ps != null) {
				rs = ps.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						dto = new DailyStatusDTO();
						dto.setBranch_code(rs.getString(BRANCH_CODE));
						dto.setDs_no(rs.getInt("ds_no"));
						dto.setStation_code(rs.getString(STATION_CODE));
						dto.setName(rs.getString(STATION_NAME));
						dto.setTopay_count(rs.getInt(TOPAY_TYPE));
						dto.setPaid_count(rs.getInt(PAID_TYPE));
						dto.setBilling_count(rs.getInt(BILLING_TYPE));

						list.add(dto);
					}
				}
			}
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();

			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
			}
		}

		return list;
	}

	/**
	 * Method to get Outstanding details
	 * 
	 */
	@Override
	public BookingDTO[] getOutstandingDetails(String stationCode, Date date)
			throws Exception {

		Connection con = null;
		PreparedStatement ps = null;
		DBHelper helper = new DBHelper();
		ResultSet rs = null;
		ArrayList<BookingDTO> outstandinglist = new ArrayList<BookingDTO>();

		BookingDTO outstanding = null;

		try {
			con = helper.getConnection();
			if (null != con) {
				ps = con.prepareStatement(GET_OUTSTANDING);
				if (ps != null) {
					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(date);

					/*
					 * ps.setString(1, formattedDate); ps.setString(2,
					 * formattedDate);
					 */
					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {
							outstanding = new BookingDTO();
							outstanding.setFrom(rs.getString(CURRENT_STATION));
							outstanding.setType(rs.getString(LR_TYPE));
							outstanding.setDdc(rs.getFloat(DDC));
							outstanding.setTotal(rs.getFloat(TOTAL));

							outstandinglist.add(outstanding);

						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = outstandinglist.size();

		if (size > 0) {
			return (BookingDTO[]) outstandinglist.toArray(new BookingDTO[size]);
		}
		return null;
	}

	/**
	 * 
	 */
	@Override
	public VersionDTO[] getVersionReports() throws RemoteException, Exception {

		Connection con = null;
		PreparedStatement ps = null;
		DBHelper helper = new DBHelper();
		ResultSet rs = null;
		ArrayList<VersionDTO> versionlist = new ArrayList<VersionDTO>();
		VersionDTO versions = null;
		try {
			con = helper.getConnection();
			if (null != con) {
				ps = con.prepareStatement(GET_VERSION);

				if (ps != null) {
					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {

							versions = new VersionDTO();
							versions.setBranch_code(rs.getString(BRANCH_CODE));
							versions
									.setStation_code(rs.getString(STATION_CODE));
							versions.setVersion_id(rs.getString(VERSION_ID));
							versionlist.add(versions);

						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = versionlist.size();

		if (size > 0) {
			return (VersionDTO[]) versionlist.toArray(new VersionDTO[size]);
		}
		return null;
	}

	@Override
	public BookedLRDTO[] getBookedLRs(Date fromDate, Date toDate,
			String branchCode, String dbHis) throws RemoteException, Exception {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<BookedLRDTO> totalLRlist = new ArrayList<BookedLRDTO>();
		BookedLRDTO bookingLRDto = null;
		try {
			if (dbHis == null || dbHis.equals("")) {
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			} else {
				DBHelperHist helperHis = new DBHelperHist();
				con = helperHis.getConnectionHistory(dbHis);
			}

			if (null != con) {
				SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
				ps = con.prepareStatement(GET_BOOKEDLR_COUNT);
				ps.setString(1, format.format(fromDate));
				ps.setString(2, format.format(toDate));
				ps.setString(3, branchCode);

				ps.setString(4, format.format(fromDate));
				ps.setString(5, format.format(toDate));
				ps.setString(6, branchCode);

				if (ps != null) {
					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {
							bookingLRDto = new BookedLRDTO();
							bookingLRDto.setStationCode(rs
									.getString(STATION_CODE));
							bookingLRDto.setLrType(rs.getString(LR_TYPE));
							bookingLRDto.setLrCount(rs.getInt(LR_COUNT));
							totalLRlist.add(bookingLRDto);
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = totalLRlist.size();

		if (size > 0) {
			return (BookedLRDTO[]) totalLRlist.toArray(new BookedLRDTO[size]);
		}

		return null;
	}

	
	
	public BookedLRDTO[] getBookedLRsUnion(Date fromDate, Date toDate,
			String branchCode, String dbHis) throws RemoteException, Exception {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<BookedLRDTO> totalLRlist = new ArrayList<BookedLRDTO>();
		BookedLRDTO bookingLRDto = null;
		try {
			if (dbHis == null || dbHis.equals("")) {
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			} else {
				DBHelperHist helperHis = new DBHelperHist();
				con = helperHis.getConnectionHistory(dbHis);
			}

			if (null != con) {
				SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
				ps = con.prepareStatement(GET_BOOKEDLR_COUNT_UNION);
				ps.setString(1, format.format(fromDate));
				ps.setString(2, format.format(toDate));
				ps.setString(3, branchCode);

				ps.setString(4, format.format(fromDate));
				ps.setString(5, format.format(toDate));
				ps.setString(6, branchCode);
				

				if (ps != null) {
					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {
							bookingLRDto = new BookedLRDTO();
							bookingLRDto.setStationCode(rs
									.getString(STATION_CODE));
							bookingLRDto.setLrType(rs.getString(LR_TYPE));
							bookingLRDto.setLrCount(rs.getInt(LR_COUNT));
							totalLRlist.add(bookingLRDto);
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = totalLRlist.size();

		if (size > 0) {
			return (BookedLRDTO[]) totalLRlist.toArray(new BookedLRDTO[size]);
		}

		return null;
	}

	
	@Override
	public TranshipmentDTO[] getTranshipment(int month, int year,
			String branch, String dbHis) throws RemoteException, Exception {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<TranshipmentDTO> list = new ArrayList<TranshipmentDTO>();
		TranshipmentDTO dto = null;
		try {

			if (dbHis == null || dbHis.equals("")) {
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			} else {
				DBHelperHist helperHis = new DBHelperHist();
				con = helperHis.getConnectionHistory(dbHis);
			}

			if (null != con) {
				ps = con.prepareStatement(GET_TRANSHIPMENT);
				ps.setString(1, branch);
				ps.setInt(2, month);
				ps.setInt(3, year);

				if (ps != null) {
					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {
							dto = new TranshipmentDTO();
							dto.setStation_code(rs.getString(STATION_CODE));
							dto.setDelivery1Day(rs.getInt(DELIVERY_1DAY));
							dto.setDelivery2Day(rs.getInt(DELIVERY_2DAY));
							dto.setDelivery3Day(rs.getInt(DELIVERY_3DAY));
							dto.setDeliveryM3(rs.getInt(DELIVERY_M3));
							dto.setInwardLr(rs.getInt(INWARD_LRS));
							dto.setIntime1Day(rs.getInt(INTIME_1DAY));
							dto.setStock(rs.getInt(STOCK));

							list.add(dto);
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = list.size();

		if (size > 0) {
			return (TranshipmentDTO[]) list.toArray(new TranshipmentDTO[size]);
		}

		return null;

	}

	/*
	 * 
	 */
	public InwardAnalysisDTO[] getInwardAnalysis(Date fromDate, Date toDate,
			String fromStn, String inwardStn, String dbHis)
			throws RemoteException, Exception {

		Connection con = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		ArrayList<InwardAnalysisDTO> list = new ArrayList<InwardAnalysisDTO>();
		InwardAnalysisDTO dto = null;
		try {
			if (dbHis == null || dbHis.equals("")) {
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			} else {
				DBHelperHist helperHis = new DBHelperHist();
				con = helperHis.getConnectionHistory(dbHis);
			}

			if (null != con) {
				SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);

				cs = con.prepareCall("{call inward_analysis(?,?,?,?)}");
				if (cs != null) {
					cs.setString(1, fromStn);
					cs.setString(2, inwardStn);
					cs.setString(3, format.format(fromDate));
					cs.setString(4, format.format(toDate));
					cs.executeUpdate();
					rs = cs.getResultSet();
					if (null != rs) {

						if (fromStn.equalsIgnoreCase("All")
								&& inwardStn.equalsIgnoreCase("All")) {
							while (rs.next()) {
								dto = new InwardAnalysisDTO();
								dto.setFromStation(rs.getString("fromBranch"));
								dto.setInwardStation(rs.getString("toBranch"));
								dto.setCount(rs.getInt("branchCount"));
								dto.setBasic_freight(rs.getFloat("basFrt"));
								dto.setTotal_freight(rs.getFloat("totalFrt"));
								dto.setActual_weight(rs.getFloat("actWt"));
								dto.setCharged_weight(rs.getFloat("chrgWt"));
								dto.setNoa(rs.getInt("noArt"));
								list.add(dto);
							}

						} else if (!fromStn.equalsIgnoreCase("All")
								&& inwardStn.equalsIgnoreCase("All")) {
							while (rs.next()) {
								dto = new InwardAnalysisDTO();
								dto
										.setFromStation(rs
												.getString("from_station"));
								dto.setInwardStation(rs.getString("toBranch"));
								dto.setCount(rs.getInt("branchCount"));
								dto.setBasic_freight(rs.getFloat("basFrt"));
								dto.setTotal_freight(rs.getFloat("totalFrt"));
								dto.setActual_weight(rs.getFloat("actWt"));
								dto.setCharged_weight(rs.getFloat("chrgWt"));
								dto.setNoa(rs.getInt("noArt"));
								list.add(dto);
							}

						} else if (fromStn.equalsIgnoreCase("All")
								&& !inwardStn.equalsIgnoreCase("All")) {
							while (rs.next()) {
								dto = new InwardAnalysisDTO();
								dto.setFromStation(rs.getString("fromBranch"));
								dto
										.setInwardStation(rs
												.getString("to_station"));
								dto.setCount(rs.getInt("branchCount"));
								dto.setBasic_freight(rs.getFloat("basFrt"));
								dto.setTotal_freight(rs.getFloat("totalFrt"));
								dto.setActual_weight(rs.getFloat("actWt"));
								dto.setCharged_weight(rs.getFloat("chrgWt"));
								dto.setNoa(rs.getInt("noArt"));
								list.add(dto);
							}

						} else {
							while (rs.next()) {
								dto = new InwardAnalysisDTO();
								dto
										.setFromStation(rs
												.getString("from_station"));
								dto
										.setInwardStation(rs
												.getString("to_station"));
								dto.setCount(rs.getInt("count"));
								dto.setBasic_freight(rs
										.getFloat("basic_freight"));
								dto.setTotal_freight(rs
										.getFloat("total_freight"));
								dto.setActual_weight(rs
										.getFloat("actual_weight"));
								dto.setCharged_weight(rs
										.getFloat("charged_weight"));
								dto.setNoa(rs.getInt("noa"));
								list.add(dto);
							}
						}

					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != cs)
					cs.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != cs)
					cs.close();
				if (null != con)
					con.close();
			}
		}

		int size = list.size();
		System.out.println("OUT ReportingDAO getinwardanalysis-->"+size);
		if (size > 0) {
			return (InwardAnalysisDTO[]) list
					.toArray(new InwardAnalysisDTO[size]);
		}

		return null;

	}
	
	/*public InwardAnalysisDTO[] getInwardAnalysis(Date fromDate,
			String fromStn, String inwardStn, String dbHis)
			throws RemoteException, Exception {

		Connection con = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		ArrayList<InwardAnalysisDTO> list = new ArrayList<InwardAnalysisDTO>();
		InwardAnalysisDTO dto = null;
		try {
			if (dbHis == null || dbHis.equals("")) {
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			} else {
				DBHelperHist helperHis = new DBHelperHist();
				con = helperHis.getConnectionHistory(dbHis);
			}

			if (null != con) {
				SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);

				cs = con.prepareCall("{call inward_analysis(?,?,?,?)}");
				if (cs != null) {
					cs.setString(1, fromStn);
					cs.setString(2, inwardStn);
					cs.setInt(3, month);
					cs.setInt(4, year);
					cs.executeUpdate();
					rs = cs.getResultSet();
					if (null != rs) {

						if (fromStn.equalsIgnoreCase("All")
								&& inwardStn.equalsIgnoreCase("All")) {
							while (rs.next()) {
								dto = new InwardAnalysisDTO();
								dto.setFromStation(rs.getString("fromBranch"));
								dto.setInwardStation(rs.getString("toBranch"));
								dto.setCount(rs.getInt("branchCount"));
								dto.setBasic_freight(rs.getFloat("basFrt"));
								dto.setTotal_freight(rs.getFloat("totalFrt"));
								dto.setActual_weight(rs.getFloat("actWt"));
								dto.setCharged_weight(rs.getFloat("chrgWt"));
								dto.setNoa(rs.getInt("noArt"));
								list.add(dto);
							}

						} else if (!fromStn.equalsIgnoreCase("All")
								&& inwardStn.equalsIgnoreCase("All")) {
							while (rs.next()) {
								dto = new InwardAnalysisDTO();
								dto
										.setFromStation(rs
												.getString("from_station"));
								dto.setInwardStation(rs.getString("toBranch"));
								dto.setCount(rs.getInt("branchCount"));
								dto.setBasic_freight(rs.getFloat("basFrt"));
								dto.setTotal_freight(rs.getFloat("totalFrt"));
								dto.setActual_weight(rs.getFloat("actWt"));
								dto.setCharged_weight(rs.getFloat("chrgWt"));
								dto.setNoa(rs.getInt("noArt"));
								list.add(dto);
							}

						} else if (fromStn.equalsIgnoreCase("All")
								&& !inwardStn.equalsIgnoreCase("All")) {
							while (rs.next()) {
								dto = new InwardAnalysisDTO();
								dto.setFromStation(rs.getString("fromBranch"));
								dto
										.setInwardStation(rs
												.getString("to_station"));
								dto.setCount(rs.getInt("branchCount"));
								dto.setBasic_freight(rs.getFloat("basFrt"));
								dto.setTotal_freight(rs.getFloat("totalFrt"));
								dto.setActual_weight(rs.getFloat("actWt"));
								dto.setCharged_weight(rs.getFloat("chrgWt"));
								dto.setNoa(rs.getInt("noArt"));
								list.add(dto);
							}

						} else {
							while (rs.next()) {
								dto = new InwardAnalysisDTO();
								dto
										.setFromStation(rs
												.getString("from_station"));
								dto
										.setInwardStation(rs
												.getString("to_station"));
								dto.setCount(rs.getInt("count"));
								dto.setBasic_freight(rs
										.getFloat("basic_freight"));
								dto.setTotal_freight(rs
										.getFloat("total_freight"));
								dto.setActual_weight(rs
										.getFloat("actual_weight"));
								dto.setCharged_weight(rs
										.getFloat("charged_weight"));
								dto.setNoa(rs.getInt("noa"));
								list.add(dto);
							}
						}

					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != cs)
					cs.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != cs)
					cs.close();
				if (null != con)
					con.close();
			}
		}

		int size = list.size();

		if (size > 0) {
			return (InwardAnalysisDTO[]) list
					.toArray(new InwardAnalysisDTO[size]);
		}

		return null;

	}
	

	/**
	 * 
	 * @param code
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	@Override
	public CurrentStatusDTO[] getCurrentStatus(String code)
			throws RemoteException, Exception {
		Connection con = null;
		PreparedStatement pst = null;
		ArrayList<CurrentStatusDTO> outList = new ArrayList<CurrentStatusDTO>();
		ResultSet rs = null;

		try {

			DBHelper db = new DBHelper();
			con = db.getConnection();

			if (null != con) {
				pst = con.prepareStatement(GET_CURRENT_STATUS);
				if (pst != null) {
					pst.setString(1, code);

					rs = pst.executeQuery();
					if (null != rs) {
						while (rs.next()) {
							CurrentStatusDTO book = new CurrentStatusDTO();
							book.setLr_no(rs.getString(LR_NO));
							book.setLrdate(rs.getDate(LR_DATE));
							book.setFrom(rs.getString(FROM_STATION));
							book.setTo(rs.getString(TO_STATION));
							book.setNo_of_articles(rs.getInt(NO_OF_ARTICLES));
							book.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
							book.setConsignorName(rs.getString(CONSIGNOR_NAME));
							book.setConsigneeName(rs.getString(CONSIGNEE_NAME));
							book.setOutTimeDate(rs.getTimestamp(INWARD_TIME));
							book.setDdc(rs.getFloat(DOOR_DELIVERY_CHARGE));
							book.setTotal(rs.getFloat(TOTAL));
							book.setStation_code(rs.getString(STATION_CODE));
							book.setLr_status(rs.getString(LR_STATUS));//

							outList.add(book);
						}
					}
				}
			}
		} catch (SQLException sqlexception) {
			throw sqlexception;
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != pst)
					pst.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			}
		}

		int size = outList.size();
		if (size > 0) {
			return outList.toArray(new CurrentStatusDTO[size]);
		}
		return null;
	}

	/**
	 * 
	 */
	public void setUnitVlaues(double inch, double feet, double cm)
			throws RemoteException, Exception {

		DBHelper db = new DBHelper();
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = db.getConnection();

			if (con != null) {
				ps = con.prepareStatement(UPDATE_MEASUREMENTS);
				if (ps != null) {

					ps.setDouble(1, inch);
					ps.setDouble(2, feet);
					ps.setDouble(3, cm);

					ps.executeUpdate();

				}
			}
		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

	}

	/**
	 * 
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public double[] getMeasurements() throws RemoteException, Exception {

		Connection con = null;
		PreparedStatement ps = null;
		DBHelper helper = new DBHelper();
		ResultSet rs = null;
		double[] units = new double[3];

		try {
			con = helper.getConnection();
			if (null != con) {
				ps = con.prepareStatement(GET_MEASUREMENTS);
				if (ps != null) {
					rs = ps.executeQuery();
					if (null != rs) {

						while (rs.next()) {
							units[0] = Double.parseDouble(rs.getString(FEET));
							units[1] = Double.parseDouble(rs.getString(CM));
							units[2] = Double.parseDouble(rs.getString(INCH));
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		return units;

	}

	/**
	 * 
	 * @param date
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public int getLRTrackCount(Date date, Date to_date, String dbHis)
			throws RemoteException, Exception {
		
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;

		try {
			if (dbHis == null || dbHis.equals("")) {
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			} else {
				DBHelperHist helperHis = new DBHelperHist();
				con = helperHis.getConnectionHistory(dbHis);
			}

			if (null != con) {
				ps = con.prepareStatement(GET_LR_TRACK_COUNT);
				SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
				if (ps != null) {
					ps.setString(1, format.format(date));
					ps.setString(2, format.format(to_date));
					rs = ps.executeQuery();
					if (null != rs) {

						while (rs.next()) {
							count = rs.getInt(COUNT);
							
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		return count;

	}

	/**
	 * 
	 */
	public DeliveryVerificationDTO[] getVerificationReport(String[] details,
			String dbHis) throws RemoteException, SQLException, Exception {

		String query = formConditionalQuery(details);

		Connection con = null;
		PreparedStatement pst = null;
		ArrayList<DeliveryVerificationDTO> outList = new ArrayList<DeliveryVerificationDTO>();
		ResultSet rs = null;

		try {

			if (dbHis == null || dbHis.equals("")) {
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			} else {
				DBHelperHist helperHis = new DBHelperHist();
				con = helperHis.getConnectionHistory(dbHis);
			}

			String GET_VERFICATION_CR = "select a.lr_no,a.lr_date,a.consignor_name,a.consignee_name,"
					+ "a.consignee_address,a.no_of_articles,a.actual_weight,a.article_id,a.total,a.ddc,"
					+ "a.lr_type,a.from_station,a.to_station,a.delivered_date inward_time from lr a,lr_tracking_archive b,"
					+ "station c where a.lr_no=b.lr_no and b.lr_status='Delivered' and "
					+ "date(a.delivered_date) > '2010-02-28'"
					+ "and c.station_code=b.station_code and a.lr_status!=3 and a.lr_status!=1 ";

			if (query != null) {
				GET_VERFICATION_CR = GET_VERFICATION_CR + query;

			}
			if (!details[4].equalsIgnoreCase("")) {
				GET_VERFICATION_CR = GET_VERFICATION_CR
						+ " and datediff(curdate(),a.delivered_date)>"
						+ details[4];
			}

			String GET_VERFICATION_INTIME = "select a.lr_no,a.lr_date,a.consignor_name,a.consignee_name,"
					+ "a.consignee_address,a.no_of_articles,a.actual_weight,a.article_id,a.total,a.ddc,"
					+ "a.lr_type,a.from_station,a.to_station,b.inward_time from lr a,lr_tracking b,"
					+ "station c where a.lr_no=b.lr_no and b.lr_status='ToArrive' "
					+ "and c.station_code=b.station_code and a.lr_status!=3 and a.lr_status!=1 ";

			if (query != null) {
				GET_VERFICATION_INTIME = GET_VERFICATION_INTIME + query;

			}
			if (!details[4].equalsIgnoreCase("")) {
				GET_VERFICATION_INTIME = GET_VERFICATION_INTIME
						+ " and datediff(curdate(),b.inward_time)>"
						+ details[4];
			}

			String GET_VERFICATION_OUTTIME = "select a.lr_no,a.lr_date,a.consignor_name,a.consignee_name,"
					+ "a.consignee_address,a.no_of_articles,a.actual_weight,a.article_id,a.total,a.ddc,"
					+ "a.lr_type,a.from_station,a.to_station,b.inward_time from lr a,lr_tracking b,"
					+ "station c where a.lr_no=b.lr_no and b.lr_status='Arrived' "
					+ "and c.station_code=b.station_code and a.lr_status!=3 and a.lr_status!=1 ";

			if (query != null) {
				GET_VERFICATION_OUTTIME = GET_VERFICATION_OUTTIME + query;

			}
			if (!details[4].equalsIgnoreCase("")) {
				GET_VERFICATION_OUTTIME = GET_VERFICATION_OUTTIME
						+ " and datediff(curdate(),b.inward_time)>"
						+ details[4];
			}

			String GET_VERFICATION_REPORT = GET_VERFICATION_CR + " union "
					+ GET_VERFICATION_INTIME + " union "
					+ GET_VERFICATION_OUTTIME;

			//System.out.println(GET_VERFICATION_REPORT);

			if (null != con) {
				pst = con.prepareStatement(GET_VERFICATION_REPORT);
				if (pst != null) {
					// pst.setString(1, code);

					rs = pst.executeQuery();
					if (null != rs) {
						while (rs.next()) {

							DeliveryVerificationDTO book = new DeliveryVerificationDTO();

							book.setLr_no(rs.getString(LR_NO));
							book.setLr_date(rs.getDate(LR_DATE));
							book.setCnor_name(rs.getString(CONSIGNOR_NAME));
							book.setCnee_name(rs.getString(CONSIGNEE_NAME));
							book.setCnee_address(rs
									.getString(CONSIGNEE_ADDRESS));
							book.setNoa(rs.getInt(NO_OF_ARTICLES));
							book.setActual_wt(rs.getFloat(ACTUAL_WEIGHT));
							book.setArt_id(rs.getString(ARTICLE_ID));
							book.setLr_total(rs.getFloat(TOTAL));
							book.setDdc(rs.getFloat(DDC));
							book.setLr_type(rs.getString(LR_TYPE));
							book.setFrom(rs.getString(FROM_STATION));
							book.setTo(rs.getString(TO_STATION));
							book.setOutward_date(rs.getDate(INWARD_TIME));
							outList.add(book);
						}
					}
				}
			}
		} catch (SQLException sqlexception) {
			throw sqlexception;
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != pst)
					pst.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			}
		}

		int size = outList.size();
		if (size > 0) {
			return outList.toArray(new DeliveryVerificationDTO[size]);
		}
		return null;
	}

	/**
	 * 
	 * @param date
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public DailyDeliveryStatusDTO[] getDailyDeliveryStatus(Date date,String branch,
			String dbHis) throws SQLException, Exception {

		Connection con = null;
		PreparedStatement pst = null;
		ArrayList<DailyDeliveryStatusDTO> outList = new ArrayList<DailyDeliveryStatusDTO>();
		ResultSet rs = null;

		try {
			if (dbHis == null || dbHis.equals("")) {
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			} else {
				DBHelperHist helperHis = new DBHelperHist();
				con = helperHis.getConnectionHistory(dbHis);
			}

			if (null != con) {
				pst = con.prepareStatement(DAILY_DELIVERY_STATUS);
				if (pst != null) {
					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(date);
					pst.setString(1, formattedDate);
					pst.setString(2,branch);

					rs = pst.executeQuery();
					if (null != rs) {
						while (rs.next()) {

							DailyDeliveryStatusDTO status = new DailyDeliveryStatusDTO();

							status.setStationCode(rs.getString("station_code"));
							status.setInward_od(rs.getInt("inward_od"));
							status.setInward_dd(rs.getInt("inward_dd"));
							status.setDelv_before830_od(rs
									.getInt("delv_before830_od"));
							status.setDelv_before830_dd(rs
									.getInt("delv_before830_dd"));
							status.setDelv_830_od(rs.getInt("delv_830_od"));
							status.setDelv_830_dd(rs.getInt("delv_830_dd"));
							status.setDelv_930_od(rs.getInt("delv_930_od"));
							status.setDelv_930_dd(rs.getInt("delv_930_dd"));
							status.setDelv_1030_od(rs.getInt("delv_1030_od"));
							status.setDelv_1030_dd(rs.getInt("delv_1030_dd"));
							status.setDelv_1130_od(rs.getInt("delv_1130_od"));
							status.setDelv_1130_dd(rs.getInt("delv_1130_dd"));
							status.setDelv_1230_od(rs.getInt("delv_1230_od"));
							status.setDelv_1230_dd(rs.getInt("delv_1230_dd"));
							status.setDelv_1330_od(rs.getInt("delv_1330_od"));
							status.setDelv_1330_dd(rs.getInt("delv_1330_dd"));
							status.setDelv_1430_od(rs.getInt("delv_1430_od"));
							status.setDelv_1430_dd(rs.getInt("delv_1430_dd"));
							status.setDelv_after1530_od(rs
									.getInt("delv_after1530_od"));
							status.setDelv_after1530_dd(rs
									.getInt("delv_after1530_dd"));
							status.setPending_od(rs.getInt("pending_od"));
							status.setPending_dd(rs.getInt("pending_dd"));
							status.setStock_od(rs.getInt("stock_od"));
							status.setStock_dd(rs.getInt("stock_dd"));

							outList.add(status);
						}
					}
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != pst)
					pst.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			}
		}

		int size = outList.size();
		//System.out.println("size-->"+size);
		if (size > 0) {
			return outList.toArray(new DailyDeliveryStatusDTO[size]);
		}
		return null;
	}

	private String formConditionalQuery(String[] details) {
		StringBuffer query = new StringBuffer();
		boolean isAvail = false;
		query.append(" and ");
		if (!details[0].equalsIgnoreCase("All")
				&& !details[0].equalsIgnoreCase("")) {
			query.append("c.branch_code='" + details[0] + "' and ");
			isAvail = true;
		}
		if (!details[1].equalsIgnoreCase("All")
				&& !details[1].equalsIgnoreCase("")) {
			query.append("b.station_code='" + details[1] + "' and ");
			isAvail = true;
		}
		if (!details[2].equalsIgnoreCase("All")
				&& !details[2].equalsIgnoreCase("")) {
			query.append("a.lr_type='" + details[2] + "' and ");
			isAvail = true;
		}
		if (!details[3].equalsIgnoreCase("All")
				&& !details[3].equalsIgnoreCase("")) {
			if (details[3].equalsIgnoreCase("DOOR"))
				query.append("a.ddc!=0 and ");
			else
				query.append("a.ddc=0 and ");
			isAvail = true;
		}

		if (!details[5].equalsIgnoreCase("All")
				&& !details[5].equalsIgnoreCase("")) {
			query.append("a.total>" + details[5] + " and ");
			isAvail = true;
		}
		if (isAvail == false)
			return null;
		else {
			String temp = query.toString();
			int index = temp.length();
			temp = temp.substring(0, index - 4);
			return temp.trim();
		}

	}

	public VersionDTO[] getHistoryYears() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		DBHelper helper = new DBHelper();
		ResultSet rs = null;
		ArrayList<VersionDTO> versionlist = new ArrayList<VersionDTO>();
		VersionDTO versions = null;
		try {
			con = helper.getConnection();
			if (null != con) {
				ps = con.prepareStatement(GET_HISTORY_YEARS);

				if (ps != null) {
					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {
							versions = new VersionDTO();
							versions.setBranch_code(rs.getString(YEAR));
							versions.setStation_code(rs.getString(DB_NAME));
							versions.setUpdated_date(rs.getDate("three_month"));
							versionlist.add(versions);

						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = versionlist.size();
		System.out.println("Out ReportingDAO getHistoryyears===>"
				+ size);
		if (size > 0) {
			return (VersionDTO[]) versionlist.toArray(new VersionDTO[size]);
		}
		return null;

	}

	private boolean insertDailyLessRemittance(Connection con, DRSDTO dto)
			throws SQLException, Exception {
		PreparedStatement ps = null;
		boolean flag = false;
		RemittanceDetailsDTO[] detailsdto = dto.getRemittanceDetails();
		String sno = dto.getNo();
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
		String formattedDate = format.format(dto.getDate());
		String station_code = dto.getStation_code();
		try {

			ps = con.prepareStatement(INSERT_LESS_REMITTANCE);
			for (int i = 0; i < detailsdto.length; i++) {
				if (ps != null) {
					if (!detailsdto[i].isStatus()) {
						ps.setString(1, sno);
						ps.setString(2, formattedDate);
						ps.setString(3, station_code);
						ps.setString(4, detailsdto[i].getLrno());
						ps.setFloat(5, detailsdto[i].getTotal());
						ps.addBatch();
					}
				}
			}
			ps.executeBatch();
		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != ps)
					ps.close();
			} catch (Exception exception) {
				if (null != ps)
					ps.close();
			}
		}

		return flag;
	}

	private boolean updateDailyAddRemittance(Connection con, DRSDTO dto)
			throws SQLException, Exception {
		PreparedStatement ps = null;
		boolean flag = false;
		RemittanceDetailsDTO[] detailsdto = dto.getRemittanceDetails();
		String paidstation = dto.getStation_code();
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
		String paid_date = format.format(dto.getDate());

		try {
			ps = con.prepareStatement(UPDATE_ADD_REMITTANCE);
			for (int i = 0; i < detailsdto.length; i++) {
				if (ps != null) {
					if (detailsdto[i].isStatus()) {
						ps.setString(1, paidstation);
						ps.setString(2, paid_date);
						ps.setString(3, detailsdto[i].getLrno());
						ps.addBatch();
					}
				}
			}
			ps.executeBatch();
		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != ps)
					ps.close();
			} catch (Exception exception) {
				if (null != ps)
					ps.close();
			}
		}

		return flag;
	}

	private RemittanceDetailsDTO[] getValidRemittanceLR(Connection con,
			String station_id, String DBHistory) throws SQLException, Exception {

		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<RemittanceDetailsDTO> remittance_details = new ArrayList<RemittanceDetailsDTO>();
		RemittanceDetailsDTO remittance = null;
		try {
			pst = con.prepareStatement(GET_VALID_REMITTANCE_LR);

			if (pst != null) {
				pst.setString(1, station_id);
				pst.setString(2, station_id);
				rs = pst.executeQuery();
				while (rs.next()) {
					remittance = new RemittanceDetailsDTO();
					remittance.setLrno(rs.getString(LR_NO));
					remittance.setTotal(rs.getFloat("lrAmount"));
					remittance.setStatus(rs.getBoolean("status"));
					remittance_details.add(remittance);
				}
			}
		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != pst)
					pst.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != pst)
					pst.close();
			}
		}

		int size = remittance_details.size();

		if (size > 0) {
			return (RemittanceDetailsDTO[]) remittance_details
					.toArray(new RemittanceDetailsDTO[size]);
		}

		return null;
	}

	private RemittanceDetailsDTO[] getValidRemittanceLRHistory(Connection con,
			String station_id, String DBHistory) throws SQLException, Exception {

		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<RemittanceDetailsDTO> remittance_details = new ArrayList<RemittanceDetailsDTO>();
		RemittanceDetailsDTO remittance = null;
		try {
			pst = con.prepareStatement(GET_VALID_REMITTANCE_LR_HISTORY);

			if (pst != null) {
				pst.setString(1, station_id);
				pst.setString(2, station_id);
				rs = pst.executeQuery();
				while (rs.next()) {
					remittance = new RemittanceDetailsDTO();
					remittance.setLrno(rs.getString(LR_NO));
					remittance.setTotal(rs.getFloat("lrAmount"));
					remittance.setStatus(rs.getBoolean("status"));
					remittance_details.add(remittance);
				}
			}
		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != pst)
					pst.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != pst)
					pst.close();
			}
		}

		int size = remittance_details.size();

		if (size > 0) {
			return (RemittanceDetailsDTO[]) remittance_details
					.toArray(new RemittanceDetailsDTO[size]);
		}

		return null;
	}

	public float calculateDoorDeliveryReImbursementHistory(String stationCode,
			java.util.Date date, String DBHistory) throws Exception {

		Connection con = null;
		PreparedStatement ps = null;
		DBHelper helper = new DBHelper();

		ResultSet rs = null;
		float ddReImbursement = 0;

		try {
			con = helper.getConnection();

			if (null != con) {
				ps = con.prepareStatement(CALCULATE_DOOR_DELIVERY_HISTORY);

				if (ps != null) {
					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(date);

					ps.setString(1, stationCode);
					ps.setString(2, formattedDate);
					ps.setString(3, stationCode);
					ps.setString(4, formattedDate);

					rs = ps.executeQuery();
					if (null != rs) {

						while (rs.next()) {
							ddReImbursement += rs.getFloat(DDCHARGES);
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		return ddReImbursement;
	}

	public GMRReportDTO[] getGoodsMovementForVehicleHistory(String stationCode,
			Date date, String vehicle, String DBHistory) throws Exception {

		Connection con = null;
		PreparedStatement pst = null;
		ArrayList<GMRReportDTO> list = new ArrayList<GMRReportDTO>();
		GMRReportDTO goods = null;
		ResultSet rs = null;
		java.sql.Timestamp dbSqlTimestamp = null;

		try {

			DBHelper db = new DBHelper();
			con = db.getConnection();

			if (null != con) {

				pst = con.prepareStatement(GET_GOODS_MOVEMENT_VEHICLE_HISTORY);

				if (pst != null) {

					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(date);

					pst.setString(1, stationCode);
					pst.setString(2, formattedDate);
					pst.setString(3, vehicle);
					pst.setString(4, stationCode);
					pst.setString(5, formattedDate);
					pst.setString(6, vehicle);

					rs = pst.executeQuery();

					if (null != rs) {

						while (rs.next()) {
							goods = new GMRReportDTO();

							goods.setLrNo(rs.getString(LR_NO));
							goods.setBookingDate(rs.getDate(OUTWARD_TIME));
							dbSqlTimestamp = rs.getTimestamp(OUTWARD_TIME);
							goods.setVehicleDate(dbSqlTimestamp);
							goods.setFrom_station(rs.getString(FROM_STATION));
							goods.setTo_station(rs.getString(TO_STATION));
							goods.setConsignor_name(rs
									.getString(CONSIGNOR_NAME));
							goods.setConsignee_name(rs
									.getString(CONSIGNEE_NAME));
							goods.setWeight(rs.getFloat(ACTUAL_WEIGHT));
							goods.setNo_of_articles(rs.getInt(NO_OF_ARTICLES));
							goods.setVehicleNo(rs.getString(VEHICLE_NO));
							goods.setMoved_to(rs.getString(MOVED_TO));

							list.add(goods);
						}
					}
				}

			}
		} catch (SQLException sqlexception) {
			throw sqlexception;
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != pst)
					pst.close();
				if (null != con)
					con.close();

			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != pst)
					pst.close();
				if (null != con)
					con.close();
			}
		}

		int size = list.size();

		if (size > 0) {
			return (GMRReportDTO[]) list.toArray(new GMRReportDTO[size]);
		}

		return null;

	}

	public BookedLRDTO[] getBookedLRsHistory(Date fromDate, Date toDate,
			String branchCode, String DBHistory) throws RemoteException,
			Exception {

		Connection con = null;
		PreparedStatement ps = null;
		DBHelper helper = new DBHelper();
		ResultSet rs = null;
		ArrayList<BookedLRDTO> totalLRlist = new ArrayList<BookedLRDTO>();
		BookedLRDTO bookingLRDto = null;
		try {
			con = helper.getConnection();
			if (null != con) {
				SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
				ps = con.prepareStatement(GET_BOOKEDLR_COUNT_HISTORY);
				ps.setString(1, format.format(fromDate));
				ps.setString(2, format.format(toDate));
				ps.setString(3, branchCode);

				if (ps != null) {
					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {
							bookingLRDto = new BookedLRDTO();
							bookingLRDto.setStationCode(rs
									.getString(STATION_CODE));
							bookingLRDto.setLrType(rs.getString(LR_TYPE));
							bookingLRDto.setLrCount(rs.getInt(LR_COUNT));
							totalLRlist.add(bookingLRDto);
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = totalLRlist.size();

		if (size > 0) {
			return (BookedLRDTO[]) totalLRlist.toArray(new BookedLRDTO[size]);
		}

		return null;
	}

	/*public CurrentStatusDTO[] getCurrentStatusHistory(String code,String DBHistory)
	throws RemoteException, Exception {
		Connection con = null;
		PreparedStatement pst = null;
		ArrayList<CurrentStatusDTO> outList = new ArrayList<CurrentStatusDTO>();
		ResultSet rs = null;
		
		try {
		
			DBHelper db = new DBHelper();
			con = db.getConnection();
		
			if (null != con) {
				pst = con.prepareStatement(GET_CURRENT_STATUS_HISTORY);
				if (pst != null) {
					pst.setString(1, code);
		
					rs = pst.executeQuery();
					if (null != rs) {
						while (rs.next()) {
							CurrentStatusDTO book = new CurrentStatusDTO();
							book.setLr_no(rs.getString(LR_NO));
							book.setLrdate(rs.getDate(LR_DATE));
							book.setFrom(rs.getString(FROM_STATION));
							book.setTo(rs.getString(TO_STATION));
							book.setNo_of_articles(rs.getInt(NO_OF_ARTICLES));
							book.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
							book.setConsignorName(rs.getString(CONSIGNOR_NAME));
							book.setConsigneeName(rs.getString(CONSIGNEE_NAME));
							book.setOutTimeDate(rs.getTimestamp(INWARD_TIME));
							book.setDdc(rs.getFloat(DOOR_DELIVERY_CHARGE));
							book.setTotal(rs.getFloat(TOTAL));
							book.setStation_code(rs.getString(STATION_CODE));
							book.setLr_status(rs.getString(LR_STATUS));//
		
							outList.add(book);
						}
					}
				}
			}
		} catch (SQLException sqlexception) {
			throw sqlexception;
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != pst)
					pst.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			}
		}
		
		int size = outList.size();
		if (size > 0) {
			return outList.toArray(new CurrentStatusDTO[size]);
		}
		return null;
		}*/

	public DeliveryVerificationDTO[] getVerificationReportHistory(
			String[] details, String DBHistory) throws RemoteException,
			SQLException, Exception {

		String query = formConditionalQuery(details);

		Connection con = null;
		PreparedStatement pst = null;
		ArrayList<DeliveryVerificationDTO> outList = new ArrayList<DeliveryVerificationDTO>();
		ResultSet rs = null;

		try {

			DBHelper db = new DBHelper();
			con = db.getConnection();

			String GET_VERFICATION_CR = "select a.lr_no,a.lr_date,a.consignor_name,a.consignee_name,"
					+ "a.consignee_address,a.no_of_articles,a.actual_weight,a.article_id,a.total,a.ddc,"
					+ "a.lr_type,a.from_station,a.to_station,a.delivered_date inward_time from lr_history a,lr_tracking_archive_history b,"
					+ "station c where a.lr_no=b.lr_no and b.lr_status='Delivered' and "
					+ "date(a.delivered_date) > '2010-02-28'"
					+ "and c.station_code=b.station_code and a.lr_status!=3 and a.lr_status!=1 ";

			if (query != null) {
				GET_VERFICATION_CR = GET_VERFICATION_CR + query;

			}
			if (!details[4].equalsIgnoreCase("")) {
				GET_VERFICATION_CR = GET_VERFICATION_CR
						+ " and datediff(curdate(),a.delivered_date)>"
						+ details[4];
			}

			String GET_VERFICATION_INTIME = "select a.lr_no,a.lr_date,a.consignor_name,a.consignee_name,"
					+ "a.consignee_address,a.no_of_articles,a.actual_weight,a.article_id,a.total,a.ddc,"
					+ "a.lr_type,a.from_station,a.to_station,b.inward_time from lr_history a,lr_tracking b,"
					+ "station c where a.lr_no=b.lr_no and b.lr_status='ToArrive' "
					+ "and c.station_code=b.station_code and a.lr_status!=3 and a.lr_status!=1 ";

			if (query != null) {
				GET_VERFICATION_INTIME = GET_VERFICATION_INTIME + query;

			}
			if (!details[4].equalsIgnoreCase("")) {
				GET_VERFICATION_INTIME = GET_VERFICATION_INTIME
						+ " and datediff(curdate(),b.inward_time)>"
						+ details[4];
			}

			String GET_VERFICATION_OUTTIME = "select a.lr_no,a.lr_date,a.consignor_name,a.consignee_name,"
					+ "a.consignee_address,a.no_of_articles,a.actual_weight,a.article_id,a.total,a.ddc,"
					+ "a.lr_type,a.from_station,a.to_station,b.inward_time from lr_history a,lr_tracking b,"
					+ "station c where a.lr_no=b.lr_no and b.lr_status='Arrived' "
					+ "and c.station_code=b.station_code and a.lr_status!=3 and a.lr_status!=1 ";

			if (query != null) {
				GET_VERFICATION_OUTTIME = GET_VERFICATION_OUTTIME + query;

			}
			if (!details[4].equalsIgnoreCase("")) {
				GET_VERFICATION_OUTTIME = GET_VERFICATION_OUTTIME
						+ " and datediff(curdate(),b.inward_time)>"
						+ details[4];
			}

			String GET_VERFICATION_REPORT = GET_VERFICATION_CR + " union "
					+ GET_VERFICATION_INTIME + " union "
					+ GET_VERFICATION_OUTTIME;

			System.out.println(GET_VERFICATION_REPORT);

			if (null != con) {
				pst = con.prepareStatement(GET_VERFICATION_REPORT);
				if (pst != null) {
					// pst.setString(1, code);

					rs = pst.executeQuery();
					if (null != rs) {
						while (rs.next()) {

							DeliveryVerificationDTO book = new DeliveryVerificationDTO();

							book.setLr_no(rs.getString(LR_NO));
							book.setLr_date(rs.getDate(LR_DATE));
							book.setCnor_name(rs.getString(CONSIGNOR_NAME));
							book.setCnee_name(rs.getString(CONSIGNEE_NAME));
							book.setCnee_address(rs
									.getString(CONSIGNEE_ADDRESS));
							book.setNoa(rs.getInt(NO_OF_ARTICLES));
							book.setActual_wt(rs.getFloat(ACTUAL_WEIGHT));
							book.setArt_id(rs.getString(ARTICLE_ID));
							book.setLr_total(rs.getFloat(TOTAL));
							book.setDdc(rs.getFloat(DDC));
							book.setLr_type(rs.getString(LR_TYPE));
							book.setFrom(rs.getString(FROM_STATION));
							book.setTo(rs.getString(TO_STATION));
							book.setOutward_date(rs.getDate(INWARD_TIME));
							outList.add(book);
						}
					}
				}
			}
		} catch (SQLException sqlexception) {
			throw sqlexception;
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != pst)
					pst.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			}
		}

		int size = outList.size();
		if (size > 0) {
			return outList.toArray(new DeliveryVerificationDTO[size]);
		}
		return null;
	}

	public DDRDTO[] getDDReport(Date from, Date to, String station,
			String DBHistory) throws Exception {
		System.out.println("In ReportingDAO GetDDEReport");
		Connection con = null;
		PreparedStatement pst = null;
		ArrayList<DDRDTO> outList = new ArrayList<DDRDTO>();
		ResultSet rs = null;
		String fromDate = "";
		String toDate = "";
		String branch = "";
		String GET_DD_REPORT = "";

		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
		fromDate = format.format(from);
		toDate = format.format(to);

		if(station.equalsIgnoreCase("%")){
			GET_DD_REPORT = "select a.lr_no, a.lr_type, a.ddc, a.bft, a.total, "
				+ "ifnull(b.ddextra,0) as ddextra, ifnull(b.other,0) as others,b.station_code  from lr a left join cash_receipt b on a.lr_no = b.lr_no "
				+ "where a.lr_status != 1 and b.status != 1 "
				+ "and date(a.delivered_date) between '"
				+ fromDate + "' and '" + toDate + "' ";
		}
		else if (station.startsWith("All")) {
			branch = station.substring(4);
			GET_DD_REPORT = "select a.lr_no, a.lr_type, a.ddc, a.bft, a.total, "
					+ "ifnull(b.ddextra,0) as ddextra, ifnull(b.other,0) as others,b.station_code  from lr a left join cash_receipt b on a.lr_no = b.lr_no "
					+ "where a.lr_status != 1  and b.status != 1 and a.delivered_station in (select station_code from station where branch_code = '"
					+ branch
					+ "') "
					+ "and date(a.delivered_date) between '"
					+ fromDate + "' and '" + toDate + "' ";
		} else {
			GET_DD_REPORT = "select a.lr_no, a.lr_type, a.ddc, a.bft, a.total, "
					+ "ifnull(b.ddextra,0) as ddextra, ifnull(b.other,0) as others,b.station_code  from lr a left join cash_receipt b on "
					+ "a.lr_no = b.lr_no where a.lr_status != 1  and b.status != 1 and  a.delivered_station = '"
					+ station
					+ "' and date(a.delivered_date) "
					+ "between '"
					+ fromDate + "' and '" + toDate + "' ";
		}

		try {

			DBHelper db = new DBHelper();
			con = db.getConnection();

			if (null != con) {
				pst = con.prepareStatement(GET_DD_REPORT);
				if (pst != null) {
					rs = pst.executeQuery();
					if (null != rs) {
						while (rs.next()) {
							DDRDTO book = new DDRDTO();
							book.setLrNo(rs.getString(LR_NO));
							book.setLrType(rs.getString(LR_TYPE));
							book.setDdc(rs.getFloat(DDC));
							book.setBasicFreight(rs.getFloat(BASIC_FRIEGHT));
							book.setTotal(rs.getFloat(TOTAL));
							book.setDdExtra(rs.getFloat(DD_EXTRA));
							book.setOthers(rs.getFloat(OTHERS));
							book.setFromStation(rs.getString(STATION_CODE));

							outList.add(book);
						}
					}
				}
			}
		} catch (SQLException sqlexception) {
			throw sqlexception;
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != pst)
					pst.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			}
		}

		int size = outList.size();
		System.out.println("Out ReportingDAO GetDDEReport"+size);
		if (size > 0) {
			return outList.toArray(new DDRDTO[size]);
		}
		return null;
	}
	
	
	public DDRDTO[] getDDReportUnion(Date from, Date to, String station,
			String DBHistory) throws Exception {
		System.out.println("In ReportingDAO GetDDEReport");
		Connection con = null;
		PreparedStatement pst = null;
		ArrayList<DDRDTO> outList = new ArrayList<DDRDTO>();
		ResultSet rs = null;
		String fromDate = "";
		String toDate = "";
		String branch = "";
		String GET_DD_REPORT = "";

		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
		fromDate = format.format(from);
		toDate = format.format(to);

		if(station.equalsIgnoreCase("%")){
			GET_DD_REPORT = "select a.lr_no, a.lr_type, a.ddc, a.bft, a.total, "
				+ "ifnull(b.ddextra,0) as ddextra, ifnull(b.other,0) as others,b.station_code  from lr a left join cash_receipt b on a.lr_no = b.lr_no "
				+ "where a.lr_status != 1 and b.status != 1 "
				+ "and date(a.delivered_date) between '"
				+ fromDate + "' and '" + toDate + "' " + 
				" union " +
				"select a.lr_no, a.lr_type, a.ddc, a.bft, a.total, "
				+ "ifnull(b.ddextra,0) as ddextra, ifnull(b.other,0) as others,b.station_code  from lr_history a left join cash_receipt_history b on a.lr_no = b.lr_no "
				+ "where a.lr_status != 1  and b.status != 1 "
				+ "and date(a.delivered_date) between '"
				+ fromDate + "' and '" + toDate + "' ";
		}
		else if (station.startsWith("All")) {
			branch = station.substring(4);
			GET_DD_REPORT = "select a.lr_no, a.lr_type, a.ddc, a.bft, a.total, "
					+ "ifnull(b.ddextra,0) as ddextra, ifnull(b.other,0) as others,b.station_code  from lr a left join cash_receipt b on a.lr_no = b.lr_no "
					+ "where a.lr_status != 1  and b.status != 1 and a.delivered_station in (select station_code from station where branch_code = '"
					+ branch
					+ "') "
					+ "and date(a.delivered_date) between '"
					+ fromDate + "' and '" + toDate + "' " +
					" union " +
					"select a.lr_no, a.lr_type, a.ddc, a.bft, a.total, "
					+ "ifnull(b.ddextra,0) as ddextra, ifnull(b.other,0) as others,b.station_code  from lr_history a left join cash_receipt_history b on a.lr_no = b.lr_no "
					+ "where a.lr_status != 1  and b.status != 1 and a.delivered_station in (select station_code from station where branch_code = '"
					+ branch
					+ "') "
					+ "and date(a.delivered_date) between '"
					+ fromDate + "' and '" + toDate + "' ";
		} else {
			GET_DD_REPORT = "select a.lr_no, a.lr_type, a.ddc, a.bft, a.total, "
					+ "ifnull(b.ddextra,0) as ddextra, ifnull(b.other,0) as others,b.station_code  from lr a left join cash_receipt b on "
					+ "a.lr_no = b.lr_no where a.lr_status != 1  and b.status != 1 and  a.delivered_station = '"
					+ station
					+ "' and date(a.delivered_date) "
					+ "between '"
					+ fromDate + "' and '" + toDate + "' " +
					" union " +
					"select a.lr_no, a.lr_type, a.ddc, a.bft, a.total, "
					+ "ifnull(b.ddextra,0) as ddextra, ifnull(b.other,0) as others,b.station_code  from lr_history a left join cash_receipt_history b on "
					+ "a.lr_no = b.lr_no where a.lr_status != 1 and b.status != 1 and  a.delivered_station = '"
					+ station
					+ "' and date(a.delivered_date) "
					+ "between '"
					+ fromDate + "' and '" + toDate + "' ";
		}

		try {

			DBHelper db = new DBHelper();
			con = db.getConnection();

			if (null != con) {
				pst = con.prepareStatement(GET_DD_REPORT);
				if (pst != null) {
					rs = pst.executeQuery();
					if (null != rs) {
						while (rs.next()) {
							DDRDTO book = new DDRDTO();
							book.setLrNo(rs.getString(LR_NO));
							book.setLrType(rs.getString(LR_TYPE));
							book.setDdc(rs.getFloat(DDC));
							book.setBasicFreight(rs.getFloat(BASIC_FRIEGHT));
							book.setTotal(rs.getFloat(TOTAL));
							book.setDdExtra(rs.getFloat(DD_EXTRA));
							book.setOthers(rs.getFloat(OTHERS));
							book.setFromStation(rs.getString(STATION_CODE));

							outList.add(book);
						}
					}
				}
			}
		} catch (SQLException sqlexception) {
			throw sqlexception;
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != pst)
					pst.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			}
		}

		int size = outList.size();
		System.out.println("Out ReportingDAO GetDDEReport"+size);
		if (size > 0) {
			return outList.toArray(new DDRDTO[size]);
		}
		return null;
	}


	public DDRDTO[] getDDReportHistory(Date from, Date to, String station,
			String dbHis) throws Exception {
		Connection con = null;
		PreparedStatement pst = null;
		ArrayList<DDRDTO> outList = new ArrayList<DDRDTO>();
		ResultSet rs = null;
		String fromDate = "";
		String toDate = "";
		String branch = "";
		String GET_DD_REPORT = "";

		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
		fromDate = format.format(from);
		toDate = format.format(to);
	
		if(station.equalsIgnoreCase("%")){
			GET_DD_REPORT = "select a.lr_no, a.lr_type, a.ddc, a.bft, a.total, "
				+ "ifnull(b.ddextra,0) as ddextra, ifnull(b.other,0) as others,b.station_code  from lr_history a left join cash_receipt_history b on a.lr_no = b.lr_no "
				+ "where a.lr_status != 1  and b.status != 1 "
				+ "and date(a.delivered_date) between '"
				+ fromDate + "' and '" + toDate + "' ";
		}
		else if (station.startsWith("All")) {
			branch = station.substring(4);
			GET_DD_REPORT = "select a.lr_no, a.lr_type, a.ddc, a.bft, a.total, "
					+ "ifnull(b.ddextra,0) as ddextra, ifnull(b.other,0) as others,b.station_code  from lr_history a left join cash_receipt_history b on a.lr_no = b.lr_no "
					+ "where a.lr_status != 1  and b.status != 1 and a.delivered_station in (select station_code from station where branch_code = '"
					+ branch
					+ "') "
					+ "and date(a.delivered_date) between '"
					+ fromDate + "' and '" + toDate + "' ";
		} else {
			GET_DD_REPORT = "select a.lr_no, a.lr_type, a.ddc, a.bft, a.total, "
					+ "ifnull(b.ddextra,0) as ddextra, ifnull(b.other,0) as others,b.station_code  from lr_history a left join cash_receipt_history b on "
					+ "a.lr_no = b.lr_no where a.lr_status != 1 and b.status != 1 and  a.delivered_station = '"
					+ station
					+ "' and date(a.delivered_date) "
					+ "between '"
					+ fromDate + "' and '" + toDate + "' ";
		}

		try {

			if (dbHis == null || dbHis.equals("")) {
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			} else {
				DBHelperHist helperHis = new DBHelperHist();
				con = helperHis.getConnectionHistory(dbHis);
			}

			if (null != con) {
				pst = con.prepareStatement(GET_DD_REPORT);
				if (pst != null) {
					rs = pst.executeQuery();
					if (null != rs) {
						while (rs.next()) {
							DDRDTO book = new DDRDTO();
							book.setLrNo(rs.getString(LR_NO));
							book.setLrType(rs.getString(LR_TYPE));
							book.setDdc(rs.getFloat(DDC));
							book.setBasicFreight(rs.getFloat(BASIC_FRIEGHT));
							book.setTotal(rs.getFloat(TOTAL));
							book.setDdExtra(rs.getFloat(DD_EXTRA));
							book.setOthers(rs.getFloat(OTHERS));
							book.setFromStation(rs.getString(STATION_CODE));

							

							outList.add(book);
						}
					}
				}
			}
		} catch (SQLException sqlexception) {
			throw sqlexception;
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != pst)
					pst.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			}
		}

		int size = outList.size();
		if (size > 0) {
			return outList.toArray(new DDRDTO[size]);
		}
		return null;
	}

	public RemittanceShortageDTO[] getRSReport(String branch, int month,
			int year, String DBHistory) throws Exception {

		Connection con = null;
		PreparedStatement ps = null;
		DBHelper helper = new DBHelper();
		ResultSet rs = null;
		ArrayList<RemittanceShortageDTO> list = new ArrayList<RemittanceShortageDTO>();
		RemittanceShortageDTO dto = null;
		try {
			con = helper.getConnection();
			if (null != con) {
				ps = con.prepareStatement(GET_RS_REPORT);
				ps.setInt(1, month);
				ps.setInt(2, year);
				ps.setString(3, branch);
				
				if (ps != null) {
					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {
							dto = new RemittanceShortageDTO();

							dto.setFromStation(rs.getString(FROM_STATION));
							dto.setToStation(rs.getString(TO_STATION));
							dto.setLrNo(rs.getString(LR_NO));
							dto.setLrDate(rs.getDate(LR_DATE));
							dto.setLrType(rs.getString(LR_TYPE));
							dto.setDrsNo(rs.getString(DR_NO));
							dto.setDrsDate(rs.getDate("date"));
							dto.setCrNo(rs.getString(CR_NO));
							dto.setCnor(rs.getString(CONSIGNOR_NAME));
							dto.setCnee(rs.getString(CONSIGNEE_NAME));
							dto.setAmount(rs.getFloat("lr_amount"));
							dto.setRecoveryDate(rs.getDate("paid_date"));

							list.add(dto);
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = list.size();

		if (size > 0) {
			return (RemittanceShortageDTO[]) list
					.toArray(new RemittanceShortageDTO[size]);
		}

		return null;

	}
	
	
	public RemittanceShortageDTO[] getRSReportUnion(String branch, int month,
			int year, String DBHistory) throws Exception {

		Connection con = null;
		PreparedStatement ps = null;
		DBHelper helper = new DBHelper();
		ResultSet rs = null;
		ArrayList<RemittanceShortageDTO> list = new ArrayList<RemittanceShortageDTO>();
		RemittanceShortageDTO dto = null;
		try {
			con = helper.getConnection();
			if (null != con) {
				ps = con.prepareStatement(GET_RS_REPORT_UNION);
				ps.setInt(1, month);
				ps.setInt(2, year);
				ps.setString(3, branch);
				ps.setInt(4, month);
				ps.setInt(5, year);
				ps.setString(6, branch);
				if (ps != null) {
					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {
							dto = new RemittanceShortageDTO();

							dto.setFromStation(rs.getString(FROM_STATION));
							dto.setToStation(rs.getString(TO_STATION));
							dto.setLrNo(rs.getString(LR_NO));
							dto.setLrDate(rs.getDate(LR_DATE));
							dto.setLrType(rs.getString(LR_TYPE));
							dto.setDrsNo(rs.getString(DR_NO));
							dto.setDrsDate(rs.getDate("date"));
							dto.setCrNo(rs.getString(CR_NO));
							dto.setCnor(rs.getString(CONSIGNOR_NAME));
							dto.setCnee(rs.getString(CONSIGNEE_NAME));
							dto.setAmount(rs.getFloat("lr_amount"));
							dto.setRecoveryDate(rs.getDate("paid_date"));

							list.add(dto);
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = list.size();

		if (size > 0) {
			return (RemittanceShortageDTO[]) list
					.toArray(new RemittanceShortageDTO[size]);
		}

		return null;

	}

	
	public RemittanceShortageDTO[] getRSReportHistory(String branch, int month,
			int year, String dbHis) throws Exception {

		Connection con = null;
		PreparedStatement ps = null;		
		ResultSet rs = null;
		ArrayList<RemittanceShortageDTO> list = new ArrayList<RemittanceShortageDTO>();
		RemittanceShortageDTO dto = null;
		try {
			if (dbHis == null || dbHis.equals("")) {
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			} else {
				DBHelperHist helperHis = new DBHelperHist();
				con = helperHis.getConnectionHistory(dbHis);
			}
			
			if (null != con) {
				ps = con.prepareStatement(GET_RS_REPORT_HISTORY);
				ps.setInt(1, month);
				ps.setInt(2, year);
				ps.setString(3, branch);

				if (ps != null) {
					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {
							dto = new RemittanceShortageDTO();

							dto.setFromStation(rs.getString(FROM_STATION));
							dto.setToStation(rs.getString(TO_STATION));
							dto.setLrNo(rs.getString(LR_NO));
							dto.setLrDate(rs.getDate(LR_DATE));
							dto.setLrType(rs.getString(LR_TYPE));
							dto.setDrsNo(rs.getString(DR_NO));
							dto.setDrsDate(rs.getDate("date"));
							dto.setCrNo(rs.getString(CR_NO));
							dto.setCnor(rs.getString(CONSIGNOR_NAME));
							dto.setCnee(rs.getString(CONSIGNEE_NAME));
							dto.setAmount(rs.getFloat("lr_amount"));
							dto.setRecoveryDate(rs.getDate("paid_date"));

							list.add(dto);
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = list.size();

		if (size > 0) {
			return (RemittanceShortageDTO[]) list
					.toArray(new RemittanceShortageDTO[size]);
		}

		return null;

	}

	/**
	 * 
	 */
	public BookingDTO[] getCancelledLR(Date fromDate, Date toDate, String type,String branch_code,String station_code,
			String DBHistory) throws RemoteException, BusinessException,
			Exception {
		System.out.println("in ReportingDAO getCancelledLR");
		Connection con = null;
		PreparedStatement ps = null;
		DBHelper helper = new DBHelper();
		ResultSet rs = null;
		ArrayList<BookingDTO> cancelledLRList = new ArrayList<BookingDTO>();

		BookingDTO dto = null;
		if(branch_code.equalsIgnoreCase("All")){
			branch_code = "%";
		}
		if(station_code.equalsIgnoreCase("All")){
			station_code = "%";
		}

		try {
			con = helper.getConnection();

			if (null != con) {
				if (type.equalsIgnoreCase("Detailed")) {
					ps = con.prepareStatement(GET_CANCELLED_LR);
				} else if (type.equalsIgnoreCase("Summary")) {
					ps = con.prepareStatement(GET_CANCELLED_LR1);
				}

				if (ps != null) {

					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(fromDate);
					String toformattedDate = format.format(toDate);
					
					ps.setString(1, formattedDate);
					ps.setString(2, toformattedDate);
					ps.setString(3, branch_code);
					if (type.equalsIgnoreCase("Detailed")) {
						ps.setString(4, station_code);
					}

					rs = ps.executeQuery();
					if (null != rs) {
						if (type.equalsIgnoreCase("Detailed")) {
							while (rs.next()) {

								dto = new BookingDTO();

								dto.setFrom(rs.getString(FROM_STATION));
								dto.setLrno(rs.getString(LR_NO));
								dto.setDate(rs.getDate(LR_DATE));
								dto.setType(rs.getString(LR_TYPE));
								dto.setTo(rs.getString(TO_STATION));
								dto
										.setNo_of_articles(rs
												.getInt(NO_OF_ARTICLES));
								dto
										.setActual_weight(rs
												.getFloat(ACTUAL_WEIGHT));
								dto.setBft(rs.getFloat(BASIC_FRIEGHT));
								dto.setCcc(rs.getFloat(CCC));
								dto.setIec(rs.getFloat(IEC));
								dto
										.setOther_charges(rs
												.getFloat(OTHER_CHARGES));
								dto.setDdc(rs.getFloat(DDC));
								dto.setTotal(rs.getFloat(TOTAL));

								cancelledLRList.add(dto);
							}
						} else if (type.equalsIgnoreCase("Summary")) {
							while (rs.next()) {

								dto = new BookingDTO();

								dto.setFrom(rs.getString(FROM_STATION));
								dto.setNo_of_articles(rs.getInt(NO_OF_LRS));
								dto.setBft(rs.getFloat(TOTAL));
								

								cancelledLRList.add(dto);
							}
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = cancelledLRList.size();
		System.out.println("in ReportingDAO getCancelledLR--->"+size);
		if (size > 0) {
			return (BookingDTO[]) cancelledLRList.toArray(new BookingDTO[size]);
		}
		return null;

	}

	
	public BookingDTO[] getCancelledLRUnion(Date fromDate, Date toDate, String type,String branch_code,String station_code,
			String DBHistory) throws RemoteException, BusinessException,
			Exception {
		System.out.println("in ReportingDAO getCancelledLR");
		Connection con = null;
		PreparedStatement ps = null;
		DBHelper helper = new DBHelper();
		ResultSet rs = null;
		ArrayList<BookingDTO> cancelledLRList = new ArrayList<BookingDTO>();

		BookingDTO dto = null;
		if(branch_code.equalsIgnoreCase("All")){
			branch_code = "%";
		}
		if(station_code.equalsIgnoreCase("All")){
			station_code = "%";
		}

		try {
			con = helper.getConnection();

			if (null != con) {
				if (type.equalsIgnoreCase("Detailed")) {
					ps = con.prepareStatement(GET_CANCELLED_LR_UNION);
				} else if (type.equalsIgnoreCase("Summary")) {
					ps = con.prepareStatement(GET_CANCELLED_LR1_UNION);
				}

				if (ps != null) {

					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(fromDate);
					String toformattedDate = format.format(toDate);
					
					ps.setString(1, formattedDate);
					ps.setString(2, toformattedDate);
					ps.setString(3, branch_code);
					if (type.equalsIgnoreCase("Detailed")) {
						ps.setString(4, station_code);
						ps.setString(5, formattedDate);
						ps.setString(6, toformattedDate);
						ps.setString(7, branch_code);
						ps.setString(8, station_code);
						
					}
					else
					{
						ps.setString(4, formattedDate);
						ps.setString(5, toformattedDate);
						ps.setString(6, branch_code);
												

					}
					
					rs = ps.executeQuery();
					if (null != rs) {
						if (type.equalsIgnoreCase("Detailed")) {
							while (rs.next()) {

								dto = new BookingDTO();

								dto.setFrom(rs.getString(FROM_STATION));
								dto.setLrno(rs.getString(LR_NO));
								dto.setDate(rs.getDate(LR_DATE));
								dto.setType(rs.getString(LR_TYPE));
								dto.setTo(rs.getString(TO_STATION));
								dto
										.setNo_of_articles(rs
												.getInt(NO_OF_ARTICLES));
								dto
										.setActual_weight(rs
												.getFloat(ACTUAL_WEIGHT));
								dto.setBft(rs.getFloat(BASIC_FRIEGHT));
								dto.setCcc(rs.getFloat(CCC));
								dto.setIec(rs.getFloat(IEC));
								dto
										.setOther_charges(rs
												.getFloat(OTHER_CHARGES));
								dto.setDdc(rs.getFloat(DDC));
								dto.setTotal(rs.getFloat(TOTAL));

								cancelledLRList.add(dto);
							}
						} else if (type.equalsIgnoreCase("Summary")) {
							while (rs.next()) {

								dto = new BookingDTO();

								dto.setFrom(rs.getString(FROM_STATION));
								dto.setNo_of_articles(rs.getInt(NO_OF_LRS));
								dto.setBft(rs.getFloat(TOTAL));
								

								cancelledLRList.add(dto);
							}
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = cancelledLRList.size();
		System.out.println("in ReportingDAO getCancelledLR--->"+size);
		if (size > 0) {
			return (BookingDTO[]) cancelledLRList.toArray(new BookingDTO[size]);
		}
		return null;

	}

	
	public BookingDTO[] getCancelledLRHistory(Date fromDate, Date toDate,
			String type,String branch_code,String station_code, String dbHis) throws RemoteException,
			BusinessException, Exception {

		Connection con = null;
		PreparedStatement ps = null;	
		ResultSet rs = null;
		ArrayList<BookingDTO> cancelledLRList = new ArrayList<BookingDTO>();
		if(branch_code.equalsIgnoreCase("All")){
			branch_code = "%";
		}
		if(station_code.equalsIgnoreCase("All")){
			station_code = "%";
		}
		BookingDTO dto = null;

		try {
			if (dbHis == null || dbHis.equals("")) {
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			} else {
				DBHelperHist helperHis = new DBHelperHist();
				con = helperHis.getConnectionHistory(dbHis);
			}

			if (null != con) {
				if (type.equalsIgnoreCase("Detailed")) {
					ps = con.prepareStatement(GET_CANCELLED_LR_HISTORY);
				} else if (type.equalsIgnoreCase("Summary")) {
					ps = con.prepareStatement(GET_CANCELLED_LR1_HISTORY);
				}

				if (ps != null) {

					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(fromDate);
					String toformattedDate = format.format(toDate);

					ps.setString(1, formattedDate);
					ps.setString(2, toformattedDate);
					ps.setString(3, branch_code);
					if (type.equalsIgnoreCase("Detailed")) {
						ps.setString(4, station_code);
					}
					rs = ps.executeQuery();
					if (null != rs) {
						if (type.equalsIgnoreCase("Detailed")) {
							while (rs.next()) {

								dto = new BookingDTO();

								dto.setFrom(rs.getString(FROM_STATION));
								dto.setLrno(rs.getString(LR_NO));
								dto.setDate(rs.getDate(LR_DATE));
								dto.setType(rs.getString(LR_TYPE));
								dto.setTo(rs.getString(TO_STATION));
								dto
										.setNo_of_articles(rs
												.getInt(NO_OF_ARTICLES));
								dto
										.setActual_weight(rs
												.getFloat(ACTUAL_WEIGHT));
								dto.setBft(rs.getFloat(BASIC_FRIEGHT));
								dto.setCcc(rs.getFloat(CCC));
								dto.setIec(rs.getFloat(IEC));
								dto
										.setOther_charges(rs
												.getFloat(OTHER_CHARGES));
								dto.setDdc(rs.getFloat(DDC));
								dto.setTotal(rs.getFloat(TOTAL));

								cancelledLRList.add(dto);
							}
						} else if (type.equalsIgnoreCase("Summary")) {
							while (rs.next()) {

								dto = new BookingDTO();

								dto.setFrom(rs.getString(FROM_STATION));
								dto.setNo_of_articles(rs.getInt(NO_OF_LRS));
								dto.setBft(rs.getFloat(TOTAL));
								

								cancelledLRList.add(dto);
							}
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = cancelledLRList.size();

		if (size > 0) {
			return (BookingDTO[]) cancelledLRList.toArray(new BookingDTO[size]);
		}
		return null;

	}

	/**
	 * 
	 */
	public CustomerBusinessAnalysisDTO[] getCustomerBusinessAnalysisReport(
			boolean isConsignor, String branch, String from_date,
			String to_date, String dbHis) throws RemoteException,
			BusinessException, Exception {

		Connection con = null;
		PreparedStatement ps = null;
		DBHelper helper = new DBHelper();
		ResultSet rs = null;
		ArrayList<CustomerBusinessAnalysisDTO> list = new ArrayList<CustomerBusinessAnalysisDTO>();
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
		String DATE_FORMAT1 = "MMM yyyy";
		java.text.SimpleDateFormat sdf1 = new java.text.SimpleDateFormat(DATE_FORMAT1);

		CustomerBusinessAnalysisDTO dto = null;
		System.out.println("in ReportingDAO getCustomerBookingAnalysis--->");
		//System.out.println("from--->"+from_date);
		//System.out.println("to--->"+to_date);
		try {
			/*if (dbHis == null || dbHis.equals("")) {
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			} else {
				DBHelperHist helperHis = new DBHelperHist();
				con = helperHis.getConnectionHistory(dbHis);
			}*/
			con = helper.getConnection();
			if (null != con) {
				if (isConsignor)
					ps = con.prepareStatement(GET_CNOR_BUSINESS_ANAYSIS);
				else
					ps = con.prepareStatement(GET_CNEE_BUSINESS_ANAYSIS);
				ps.setString(1, branch);
				ps.setString(2, from_date);
				ps.setString(3, to_date);
				ps.setString(4, branch);
				ps.setString(5, from_date);
				ps.setString(6, to_date);
				
				
				String stnCode = "";
				int rateType = -1;
				boolean isAvail = false;
				String month = "";
				String customername = "";
				String customertype = "";
				
				if (ps != null) {
					rs = ps.executeQuery();
					//System.out.println("rs.size-->"+String.valueOf(rs.getRow()));
					if (null != rs) {
						while (rs.next()) {
							
								stnCode = rs.getString(STATION_CODE);
								rateType = rs.getInt(RATE_TYPE);
								month = sdf1.format(format.parse(rs.getString(LR_DATE)));
								customername = rs.getString(CUSTOMER_NAME);
								customertype = rs.getString(CONSIGNEE_ADDRESS);
								//System.out.println("stncode-->"+rs.getString(STATION_CODE));
								//System.out.println("rate_type-->"+rateType);
								if(list != null && list.size() > 0){
									for(int k = 0; k < list.size(); k++){
										CustomerBusinessAnalysisDTO tempDTO = list.get(k);
										isAvail = false;									
										if(stnCode.equalsIgnoreCase(tempDTO.getFromStation())&&(month.equalsIgnoreCase(sdf1.format(tempDTO.getLr_date())))){
											
																							
											if((isConsignor)&&(customertype.equalsIgnoreCase("Consignee"))){
												tempDTO.setCustomerName("Consignee Quotation");
												tempDTO.setBasic_freight(rs.getFloat(BASIC_FRIEGHT) + tempDTO.getBasic_freight());
												tempDTO.setTotal_freight(rs.getFloat(TOTAL) + tempDTO.getTotal_freight());
												tempDTO.setActual_weight(rs.getFloat(ACTUAL_WEIGHT) + tempDTO.getActual_weight());
												tempDTO.setCharged_weight(rs.getFloat(CHARGED_WEIGHT) + tempDTO.getCharged_weight());
												tempDTO.setNoa(rs.getInt(NO_OF_ARTICLES) + tempDTO.getNoa());
												tempDTO.setLr_date(format.parse(rs.getString(LR_DATE)));
												tempDTO.setCount(rs.getInt(COUNT) + tempDTO.getCount() );		
												
												list.set(k,tempDTO);
												isAvail = true;
												break;
											}
											if((! isConsignor)&&(customertype.equalsIgnoreCase("Consignor"))){
												tempDTO.setCustomerName("Consignor Quotation");
												tempDTO.setBasic_freight(rs.getFloat(BASIC_FRIEGHT) + tempDTO.getBasic_freight());
												tempDTO.setTotal_freight(rs.getFloat(TOTAL) + tempDTO.getTotal_freight());
												tempDTO.setActual_weight(rs.getFloat(ACTUAL_WEIGHT) + tempDTO.getActual_weight());
												tempDTO.setCharged_weight(rs.getFloat(CHARGED_WEIGHT) + tempDTO.getCharged_weight());
												tempDTO.setNoa(rs.getInt(NO_OF_ARTICLES) + tempDTO.getNoa());
												tempDTO.setLr_date(format.parse(rs.getString(LR_DATE)));
												tempDTO.setCount(rs.getInt(COUNT) + tempDTO.getCount() );		
												
												list.set(k,tempDTO);
												isAvail = true;
												break;
											}
											
											if((((tempDTO.getRate_type() <= 3) || ((tempDTO.getRate_type() >= 7 ) && (tempDTO.getRate_type() <= 9)))&&(((rateType <= 3) || ((rateType >= 7 ) && (rateType <= 9)))))){												// Avail										
												
												tempDTO.setCustomerName("Sundry");
												tempDTO.setBasic_freight(rs.getFloat(BASIC_FRIEGHT) + tempDTO.getBasic_freight());
												tempDTO.setTotal_freight(rs.getFloat(TOTAL) + tempDTO.getTotal_freight());
												tempDTO.setActual_weight(rs.getFloat(ACTUAL_WEIGHT) + tempDTO.getActual_weight());
												tempDTO.setCharged_weight(rs.getFloat(CHARGED_WEIGHT) + tempDTO.getCharged_weight());
												tempDTO.setNoa(rs.getInt(NO_OF_ARTICLES) + tempDTO.getNoa());
												tempDTO.setLr_date(format.parse(rs.getString(LR_DATE)));
												tempDTO.setCount(rs.getInt(COUNT) + tempDTO.getCount() );		
												
												list.set(k,tempDTO);
												isAvail = true;
												break;											
											}
											
											if(customername.equalsIgnoreCase(tempDTO.getCustomerName())){
													tempDTO.setCustomerName(rs.getString(CUSTOMER_NAME));
													tempDTO.setBasic_freight(rs.getFloat(BASIC_FRIEGHT) + tempDTO.getBasic_freight());
													tempDTO.setTotal_freight(rs.getFloat(TOTAL) + tempDTO.getTotal_freight());
													tempDTO.setActual_weight(rs.getFloat(ACTUAL_WEIGHT) + tempDTO.getActual_weight());
													tempDTO.setCharged_weight(rs.getFloat(CHARGED_WEIGHT) + tempDTO.getCharged_weight());
													tempDTO.setNoa(rs.getInt(NO_OF_ARTICLES) + tempDTO.getNoa());
													tempDTO.setLr_date(format.parse(rs.getString(LR_DATE)));
													tempDTO.setCount(rs.getInt(COUNT) + tempDTO.getCount() );		
													
													list.set(k,tempDTO);
													isAvail = true;
													break;									
											}
												
													
										}
										
										
									}
									 if(! isAvail){
										 
										dto = new CustomerBusinessAnalysisDTO();
										//dto = tempDTO;
										if((isConsignor)&&(customertype.equalsIgnoreCase("Consignee"))){
											dto.setCustomerName("Consignee Quotation");
											dto.setFromStation(stnCode);
											dto.setBasic_freight(rs.getFloat(BASIC_FRIEGHT));
											dto.setTotal_freight(rs.getFloat(TOTAL));
											dto.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
											dto.setCharged_weight(rs.getFloat(CHARGED_WEIGHT));
											dto.setNoa(rs.getInt(NO_OF_ARTICLES));
											dto.setLr_date(format.parse(rs.getString(LR_DATE)));
											dto.setCount(rs.getInt(COUNT));
											
											dto.setRate_type(rateType);
											list.add(dto);
										}
										if((! isConsignor)&&(customertype.equalsIgnoreCase("Consignor"))){
											dto.setCustomerName("Consignor Quotation");
											dto.setFromStation(stnCode);
											dto.setBasic_freight(rs.getFloat(BASIC_FRIEGHT));
											dto.setTotal_freight(rs.getFloat(TOTAL));
											dto.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
											dto.setCharged_weight(rs.getFloat(CHARGED_WEIGHT));
											dto.setNoa(rs.getInt(NO_OF_ARTICLES));
											dto.setLr_date(format.parse(rs.getString(LR_DATE)));
											dto.setCount(rs.getInt(COUNT));
											
											dto.setRate_type(rateType);
											list.add(dto);
										}
										if(((rateType <= 3) || ((rateType >= 7 ) && (rateType <= 9)))){
											dto.setCustomerName("Sundry");
											dto.setFromStation(stnCode);
											dto.setBasic_freight(rs.getFloat(BASIC_FRIEGHT));
											dto.setTotal_freight(rs.getFloat(TOTAL));
											dto.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
											dto.setCharged_weight(rs.getFloat(CHARGED_WEIGHT));
											dto.setNoa(rs.getInt(NO_OF_ARTICLES));
											dto.setLr_date(format.parse(rs.getString(LR_DATE)));
											dto.setCount(rs.getInt(COUNT));
											
											dto.setRate_type(rateType);
											list.add(dto);
										}
										else{
											dto.setCustomerName(rs.getString(CUSTOMER_NAME));
											dto.setFromStation(stnCode);
											dto.setBasic_freight(rs.getFloat(BASIC_FRIEGHT));
											dto.setTotal_freight(rs.getFloat(TOTAL));
											dto.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
											dto.setCharged_weight(rs.getFloat(CHARGED_WEIGHT));
											dto.setNoa(rs.getInt(NO_OF_ARTICLES));
											dto.setLr_date(format.parse(rs.getString(LR_DATE)));
											dto.setCount(rs.getInt(COUNT));
											
											dto.setRate_type(rateType);
											list.add(dto);
										}
										//System.out.println("customername-->"+String.valueOf(dto.getCustomerName()));
										
									}
									
								}else{
									// First Time	
									
									
									dto = new CustomerBusinessAnalysisDTO();								
									if((isConsignor)&&(customertype.equalsIgnoreCase("Consignee"))){
										dto.setCustomerName("Consignee Quotation");
										dto.setFromStation(stnCode);
										dto.setBasic_freight(rs.getFloat(BASIC_FRIEGHT));
										dto.setTotal_freight(rs.getFloat(TOTAL));
										dto.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
										dto.setCharged_weight(rs.getFloat(CHARGED_WEIGHT));
										dto.setNoa(rs.getInt(NO_OF_ARTICLES));
										dto.setLr_date(format.parse(rs.getString(LR_DATE)));
										dto.setCount(rs.getInt(COUNT));
										
										dto.setRate_type(rateType);
										list.add(dto);
									}
									if((! isConsignor)&&(customertype.equalsIgnoreCase("Consignor"))){
										dto.setCustomerName("Consignor Quotation");
										dto.setFromStation(stnCode);
										dto.setBasic_freight(rs.getFloat(BASIC_FRIEGHT));
										dto.setTotal_freight(rs.getFloat(TOTAL));
										dto.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
										dto.setCharged_weight(rs.getFloat(CHARGED_WEIGHT));
										dto.setNoa(rs.getInt(NO_OF_ARTICLES));
										dto.setLr_date(format.parse(rs.getString(LR_DATE)));
										dto.setCount(rs.getInt(COUNT));
										
										dto.setRate_type(rateType);
										list.add(dto);
									}
									
									if(((rateType <= 3) || ((rateType >= 7 ) && (rateType <= 9)))){
										dto.setCustomerName("Sundry");
										dto.setFromStation(stnCode);
										//dto.setCustomerName(rs.getString(CUSTOMER_NAME));
										dto.setBasic_freight(rs.getFloat(BASIC_FRIEGHT));
										dto.setTotal_freight(rs.getFloat(TOTAL));
										dto.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
										dto.setCharged_weight(rs.getFloat(CHARGED_WEIGHT));
										dto.setNoa(rs.getInt(NO_OF_ARTICLES));
										dto.setLr_date(format.parse(rs.getString(LR_DATE)));
										dto.setCount(rs.getInt(COUNT));
										dto.setRate_type(rateType);
										list.add(dto);
									}
									else{
										dto.setCustomerName(rs.getString(CUSTOMER_NAME));
										dto.setFromStation(stnCode);
										//dto.setCustomerName(rs.getString(CUSTOMER_NAME));
										dto.setBasic_freight(rs.getFloat(BASIC_FRIEGHT));
										dto.setTotal_freight(rs.getFloat(TOTAL));
										dto.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
										dto.setCharged_weight(rs.getFloat(CHARGED_WEIGHT));
										dto.setNoa(rs.getInt(NO_OF_ARTICLES));
										dto.setLr_date(format.parse(rs.getString(LR_DATE)));
										dto.setCount(rs.getInt(COUNT));
										dto.setRate_type(rateType);
										list.add(dto);
									}
									
								}	
								
								
							
							

							
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}
		
		
		int size = list.size();
		//ArrayList<CustomerBusinessAnalysisDTO> gplist = groupsundry((CustomerBusinessAnalysisDTO[]) list.toArray(new CustomerBusinessAnalysisDTO[size]));
		
		System.out.println("in ReportingDAO getCustomerBookingAnalysis--->"+size);
		//int gpsize = gplist.size();
		//System.out.println("in ReportingDAO getCustomerBookingAnalysis  gpsize--->"+gpsize);
		if (size > 0) {
			return (CustomerBusinessAnalysisDTO[]) list
					.toArray(new CustomerBusinessAnalysisDTO[size]);
		}
		return null;

	}

	
	public CustomerBusinessAnalysisDTO[] getCnorBusinessAnalysisReport(
			boolean isConsignor, String branch, String from_date,
			String to_date, String dbHis) throws RemoteException,
			BusinessException, Exception {

		Connection con = null;
		PreparedStatement ps = null;
		DBHelper helper = new DBHelper();
		ResultSet rs = null;
		ArrayList<CustomerBusinessAnalysisDTO> list = new ArrayList<CustomerBusinessAnalysisDTO>();
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
		String DATE_FORMAT1 = "MMM yyyy";
		java.text.SimpleDateFormat sdf1 = new java.text.SimpleDateFormat(DATE_FORMAT1);

		CustomerBusinessAnalysisDTO dto = null;
		System.out.println("in ReportingDAO getCustomerBookingAnalysis--->");
		//System.out.println("from--->"+from_date);
		//System.out.println("to--->"+to_date);
		try {
			/*if (dbHis == null || dbHis.equals("")) {
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			} else {
				DBHelperHist helperHis = new DBHelperHist();
				con = helperHis.getConnectionHistory(dbHis);
			}*/
			con = helper.getConnection();
			if (null != con) {
				
				ps = con.prepareStatement(GET_CNOR_BUSINESS_ANAYSIS);
				
				ps.setString(1, branch);
				ps.setString(2, from_date);
				ps.setString(3, to_date);
				ps.setString(4, branch);
				ps.setString(5, from_date);
				ps.setString(6, to_date);
				
				
				String stnCode = "";
				int rateType = -1;
				boolean isAvail = false;
				String month = "";
				String customername = "";
				String customertype = "";
				
				if (ps != null) {
					rs = ps.executeQuery();
					//System.out.println("rs.size-->"+String.valueOf(rs.getRow()));
					if (null != rs) {
						while (rs.next()) {
							
								stnCode = rs.getString(STATION_CODE);
								rateType = rs.getInt(RATE_TYPE);
								month = sdf1.format(format.parse(rs.getString(LR_DATE)));
								customername = rs.getString(CUSTOMER_NAME);
								customertype = rs.getString(TYPE_IN);
								//System.out.println("stncode-->"+rs.getString(STATION_CODE));
								//System.out.println("rate_type-->"+rateType);
								if(list != null && list.size() > 0){
									for(int k = 0; k < list.size(); k++){
										CustomerBusinessAnalysisDTO tempDTO = list.get(k);
										isAvail = false;									
									
										// If Same repeats
										if(stnCode.equalsIgnoreCase(tempDTO.getFromStation())&&(month.equalsIgnoreCase(sdf1.format(tempDTO.getLr_date())))){
																																		
											if(tempDTO.getCustomerName().equalsIgnoreCase("Consignee Quotation") && customertype.equalsIgnoreCase("Consignee")){
												tempDTO.setCustomerName("Consignee Quotation");
												tempDTO.setBasic_freight(rs.getFloat(BASIC_FRIEGHT) + tempDTO.getBasic_freight());
												tempDTO.setTotal_freight(rs.getFloat(TOTAL) + tempDTO.getTotal_freight());
												tempDTO.setActual_weight(rs.getFloat(ACTUAL_WEIGHT) + tempDTO.getActual_weight());
												tempDTO.setCharged_weight(rs.getFloat(CHARGED_WEIGHT) + tempDTO.getCharged_weight());
												tempDTO.setNoa(rs.getInt(NO_OF_ARTICLES) + tempDTO.getNoa());
												tempDTO.setLr_date(format.parse(rs.getString(LR_DATE)));
												tempDTO.setCount(rs.getInt(COUNT) + tempDTO.getCount() );		
												
												list.set(k,tempDTO);
												isAvail = true;
												break;
											}else if(tempDTO.getCustomerName().equalsIgnoreCase("Sundry") && customertype.equalsIgnoreCase("Sundry")){
												// Sundry										
												
												tempDTO.setCustomerName("Sundry");
												tempDTO.setBasic_freight(rs.getFloat(BASIC_FRIEGHT) + tempDTO.getBasic_freight());
												tempDTO.setTotal_freight(rs.getFloat(TOTAL) + tempDTO.getTotal_freight());
												tempDTO.setActual_weight(rs.getFloat(ACTUAL_WEIGHT) + tempDTO.getActual_weight());
												tempDTO.setCharged_weight(rs.getFloat(CHARGED_WEIGHT) + tempDTO.getCharged_weight());
												tempDTO.setNoa(rs.getInt(NO_OF_ARTICLES) + tempDTO.getNoa());
												tempDTO.setLr_date(format.parse(rs.getString(LR_DATE)));
												tempDTO.setCount(rs.getInt(COUNT) + tempDTO.getCount() );		
												
												list.set(k,tempDTO);
												isAvail = true;
												break;											
											}										
																								
										}
										
										
									}
									 if(! isAvail){
										 
										dto = new CustomerBusinessAnalysisDTO();
									
										if(customertype.equalsIgnoreCase("Consignor")){
											dto.setCustomerName(rs.getString(CUSTOMER_NAME));
											dto.setFromStation(stnCode);
											dto.setBasic_freight(rs.getFloat(BASIC_FRIEGHT));
											dto.setTotal_freight(rs.getFloat(TOTAL));
											dto.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
											dto.setCharged_weight(rs.getFloat(CHARGED_WEIGHT));
											dto.setNoa(rs.getInt(NO_OF_ARTICLES));
											dto.setLr_date(format.parse(rs.getString(LR_DATE)));
											dto.setCount(rs.getInt(COUNT));
											
											dto.setRate_type(rateType);
											list.add(dto);
										}else if(customertype.equalsIgnoreCase("Consignee")){
											dto.setCustomerName("Consignee Quotation");
											dto.setFromStation(stnCode);
											dto.setBasic_freight(rs.getFloat(BASIC_FRIEGHT));
											dto.setTotal_freight(rs.getFloat(TOTAL));
											dto.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
											dto.setCharged_weight(rs.getFloat(CHARGED_WEIGHT));
											dto.setNoa(rs.getInt(NO_OF_ARTICLES));
											dto.setLr_date(format.parse(rs.getString(LR_DATE)));
											dto.setCount(rs.getInt(COUNT));
											
											dto.setRate_type(rateType);
											list.add(dto);
										}else if(customertype.equalsIgnoreCase("Sundry")){
											dto.setCustomerName("Sundry");
											dto.setFromStation(stnCode);
											dto.setBasic_freight(rs.getFloat(BASIC_FRIEGHT));
											dto.setTotal_freight(rs.getFloat(TOTAL));
											dto.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
											dto.setCharged_weight(rs.getFloat(CHARGED_WEIGHT));
											dto.setNoa(rs.getInt(NO_OF_ARTICLES));
											dto.setLr_date(format.parse(rs.getString(LR_DATE)));
											dto.setCount(rs.getInt(COUNT));
											
											dto.setRate_type(rateType);
											list.add(dto);
										}
									 }
										
									
								}else{
									// First Time	
									
									
									dto = new CustomerBusinessAnalysisDTO();								
									
									if(customertype.equalsIgnoreCase("Consignor")){
										dto.setCustomerName(rs.getString(CUSTOMER_NAME));
										dto.setFromStation(stnCode);
										dto.setBasic_freight(rs.getFloat(BASIC_FRIEGHT));
										dto.setTotal_freight(rs.getFloat(TOTAL));
										dto.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
										dto.setCharged_weight(rs.getFloat(CHARGED_WEIGHT));
										dto.setNoa(rs.getInt(NO_OF_ARTICLES));
										dto.setLr_date(format.parse(rs.getString(LR_DATE)));
										dto.setCount(rs.getInt(COUNT));
										
										dto.setRate_type(rateType);
										list.add(dto);
									}else if(customertype.equalsIgnoreCase("Consignee")){
										dto.setCustomerName("Consignee Quotation");
										dto.setFromStation(stnCode);
										dto.setBasic_freight(rs.getFloat(BASIC_FRIEGHT));
										dto.setTotal_freight(rs.getFloat(TOTAL));
										dto.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
										dto.setCharged_weight(rs.getFloat(CHARGED_WEIGHT));
										dto.setNoa(rs.getInt(NO_OF_ARTICLES));
										dto.setLr_date(format.parse(rs.getString(LR_DATE)));
										dto.setCount(rs.getInt(COUNT));
										
										dto.setRate_type(rateType);
										list.add(dto);
									 }else if(customertype.equalsIgnoreCase("Sundry")){
										dto.setCustomerName("Sundry");
										dto.setFromStation(stnCode);									
										dto.setBasic_freight(rs.getFloat(BASIC_FRIEGHT));
										dto.setTotal_freight(rs.getFloat(TOTAL));
										dto.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
										dto.setCharged_weight(rs.getFloat(CHARGED_WEIGHT));
										dto.setNoa(rs.getInt(NO_OF_ARTICLES));
										dto.setLr_date(format.parse(rs.getString(LR_DATE)));
										dto.setCount(rs.getInt(COUNT));
										dto.setRate_type(rateType);
										list.add(dto);
									}									
								}	
							
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}
		
		
		int size = list.size();
		//ArrayList<CustomerBusinessAnalysisDTO> gplist = groupsundry((CustomerBusinessAnalysisDTO[]) list.toArray(new CustomerBusinessAnalysisDTO[size]));
		
		System.out.println("in ReportingDAO getCustomerBookingAnalysis--->"+size);
		//int gpsize = gplist.size();
		//System.out.println("in ReportingDAO getCustomerBookingAnalysis  gpsize--->"+gpsize);
		if (size > 0) {
			return (CustomerBusinessAnalysisDTO[]) list
					.toArray(new CustomerBusinessAnalysisDTO[size]);
		}
		return null;

	}
	
	public CustomerBusinessAnalysisDTO[] getCneeBusinessAnalysisReport(
			boolean isConsignor, String branch, String from_date,
			String to_date, String dbHis) throws RemoteException,
			BusinessException, Exception {

		Connection con = null;
		PreparedStatement ps = null;
		DBHelper helper = new DBHelper();
		ResultSet rs = null;
		ArrayList<CustomerBusinessAnalysisDTO> list = new ArrayList<CustomerBusinessAnalysisDTO>();
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
		String DATE_FORMAT1 = "MMM yyyy";
		java.text.SimpleDateFormat sdf1 = new java.text.SimpleDateFormat(DATE_FORMAT1);

		CustomerBusinessAnalysisDTO dto = null;
		System.out.println("in ReportingDAO getCustomerBookingAnalysis--->");
		//System.out.println("from--->"+from_date);
		//System.out.println("to--->"+to_date);
		try {
			/*if (dbHis == null || dbHis.equals("")) {
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			} else {
				DBHelperHist helperHis = new DBHelperHist();
				con = helperHis.getConnectionHistory(dbHis);
			}*/
			con = helper.getConnection();
			if (null != con) {
			
				ps = con.prepareStatement(GET_CNEE_BUSINESS_ANAYSIS);
				ps.setString(1, branch);
				ps.setString(2, from_date);
				ps.setString(3, to_date);
				ps.setString(4, branch);
				ps.setString(5, from_date);
				ps.setString(6, to_date);
				
				
				String stnCode = "";
				int rateType = -1;
				boolean isAvail = false;
				String month = "";
				String customername = "";
				String customertype = "";
				
				if (ps != null) {
					rs = ps.executeQuery();
					//System.out.println("rs.size-->"+String.valueOf(rs.getRow()));
					if (null != rs) {
						while (rs.next()) {
							
								stnCode = rs.getString(STATION_CODE);
								rateType = rs.getInt(RATE_TYPE);
								month = sdf1.format(format.parse(rs.getString(LR_DATE)));
								customername = rs.getString(CUSTOMER_NAME);
								customertype = rs.getString(TYPE_IN);
								//System.out.println("stncode-->"+rs.getString(STATION_CODE));
								//System.out.println("rate_type-->"+rateType);
								if(list != null && list.size() > 0){
									for(int k = 0; k < list.size(); k++){
										CustomerBusinessAnalysisDTO tempDTO = list.get(k);
										isAvail = false;									
									
										// If Same repeats
										if(stnCode.equalsIgnoreCase(tempDTO.getFromStation())&&(month.equalsIgnoreCase(sdf1.format(tempDTO.getLr_date())))){
																																		
											/*if(tempDTO.getCustomerName().equalsIgnoreCase("Consignor Quotation") && customertype.equalsIgnoreCase("Consignor")){
												tempDTO.setCustomerName("Consignor Quotation");
												tempDTO.setBasic_freight(rs.getFloat(BASIC_FRIEGHT) + tempDTO.getBasic_freight());
												tempDTO.setTotal_freight(rs.getFloat(TOTAL) + tempDTO.getTotal_freight());
												tempDTO.setActual_weight(rs.getFloat(ACTUAL_WEIGHT) + tempDTO.getActual_weight());
												tempDTO.setCharged_weight(rs.getFloat(CHARGED_WEIGHT) + tempDTO.getCharged_weight());
												tempDTO.setNoa(rs.getInt(NO_OF_ARTICLES) + tempDTO.getNoa());
												tempDTO.setLr_date(format.parse(rs.getString(LR_DATE)));
												tempDTO.setCount(rs.getInt(COUNT) + tempDTO.getCount() );		
												
												list.set(k,tempDTO);
												isAvail = true;
												break;
											}else*/ if(tempDTO.getCustomerName().equalsIgnoreCase("Sundry") && ((customertype.equalsIgnoreCase("Sundry"))||(customertype.equalsIgnoreCase("Consignor")))){
												// Sundry										
												
												tempDTO.setCustomerName("Sundry");
												tempDTO.setBasic_freight(rs.getFloat(BASIC_FRIEGHT) + tempDTO.getBasic_freight());
												tempDTO.setTotal_freight(rs.getFloat(TOTAL) + tempDTO.getTotal_freight());
												tempDTO.setActual_weight(rs.getFloat(ACTUAL_WEIGHT) + tempDTO.getActual_weight());
												tempDTO.setCharged_weight(rs.getFloat(CHARGED_WEIGHT) + tempDTO.getCharged_weight());
												tempDTO.setNoa(rs.getInt(NO_OF_ARTICLES) + tempDTO.getNoa());
												tempDTO.setLr_date(format.parse(rs.getString(LR_DATE)));
												tempDTO.setCount(rs.getInt(COUNT) + tempDTO.getCount() );		
												
												list.set(k,tempDTO);
												isAvail = true;
												break;											
											}										
																								
										}
										
										
									}
									 if(! isAvail){
										 
										dto = new CustomerBusinessAnalysisDTO();
									
										/*if(customertype.equalsIgnoreCase("Consignor")){
											dto.setCustomerName("Consignor Quotation");											
											dto.setFromStation(stnCode);
											dto.setBasic_freight(rs.getFloat(BASIC_FRIEGHT));
											dto.setTotal_freight(rs.getFloat(TOTAL));
											dto.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
											dto.setCharged_weight(rs.getFloat(CHARGED_WEIGHT));
											dto.setNoa(rs.getInt(NO_OF_ARTICLES));
											dto.setLr_date(format.parse(rs.getString(LR_DATE)));
											dto.setCount(rs.getInt(COUNT));
											
											dto.setRate_type(rateType);
											list.add(dto);
										}else*/ if(customertype.equalsIgnoreCase("Consignee")){
											dto.setCustomerName(rs.getString(CUSTOMER_NAME));
											dto.setFromStation(stnCode);
											dto.setBasic_freight(rs.getFloat(BASIC_FRIEGHT));
											dto.setTotal_freight(rs.getFloat(TOTAL));
											dto.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
											dto.setCharged_weight(rs.getFloat(CHARGED_WEIGHT));
											dto.setNoa(rs.getInt(NO_OF_ARTICLES));
											dto.setLr_date(format.parse(rs.getString(LR_DATE)));
											dto.setCount(rs.getInt(COUNT));
											
											dto.setRate_type(rateType);
											list.add(dto);
										}else {
											dto.setCustomerName("Sundry");
											dto.setFromStation(stnCode);
											dto.setBasic_freight(rs.getFloat(BASIC_FRIEGHT));
											dto.setTotal_freight(rs.getFloat(TOTAL));
											dto.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
											dto.setCharged_weight(rs.getFloat(CHARGED_WEIGHT));
											dto.setNoa(rs.getInt(NO_OF_ARTICLES));
											dto.setLr_date(format.parse(rs.getString(LR_DATE)));
											dto.setCount(rs.getInt(COUNT));
											
											dto.setRate_type(rateType);
											list.add(dto);
										}
									 }
										
									
								}else{
									// First Time	
									
									
									dto = new CustomerBusinessAnalysisDTO();								
									
									/*if(customertype.equalsIgnoreCase("Consignor")){
										dto.setCustomerName("Consignor Quotation");
										dto.setFromStation(stnCode);
										dto.setBasic_freight(rs.getFloat(BASIC_FRIEGHT));
										dto.setTotal_freight(rs.getFloat(TOTAL));
										dto.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
										dto.setCharged_weight(rs.getFloat(CHARGED_WEIGHT));
										dto.setNoa(rs.getInt(NO_OF_ARTICLES));
										dto.setLr_date(format.parse(rs.getString(LR_DATE)));
										dto.setCount(rs.getInt(COUNT));
										
										dto.setRate_type(rateType);
										list.add(dto);
									}else*/ if(customertype.equalsIgnoreCase("Consignee")){										
										dto.setCustomerName(rs.getString(CUSTOMER_NAME));
										dto.setFromStation(stnCode);
										dto.setBasic_freight(rs.getFloat(BASIC_FRIEGHT));
										dto.setTotal_freight(rs.getFloat(TOTAL));
										dto.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
										dto.setCharged_weight(rs.getFloat(CHARGED_WEIGHT));
										dto.setNoa(rs.getInt(NO_OF_ARTICLES));
										dto.setLr_date(format.parse(rs.getString(LR_DATE)));
										dto.setCount(rs.getInt(COUNT));
										
										dto.setRate_type(rateType);
										list.add(dto);
									 }else{
										dto.setCustomerName("Sundry");
										dto.setFromStation(stnCode);									
										dto.setBasic_freight(rs.getFloat(BASIC_FRIEGHT));
										dto.setTotal_freight(rs.getFloat(TOTAL));
										dto.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
										dto.setCharged_weight(rs.getFloat(CHARGED_WEIGHT));
										dto.setNoa(rs.getInt(NO_OF_ARTICLES));
										dto.setLr_date(format.parse(rs.getString(LR_DATE)));
										dto.setCount(rs.getInt(COUNT));
										dto.setRate_type(rateType);
										list.add(dto);
									}									
								}	
							
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}
		
		
		int size = list.size();
		//ArrayList<CustomerBusinessAnalysisDTO> gplist = groupsundry((CustomerBusinessAnalysisDTO[]) list.toArray(new CustomerBusinessAnalysisDTO[size]));
		
		System.out.println("in ReportingDAO getCustomerBookingAnalysis--->"+size);
		//int gpsize = gplist.size();
		//System.out.println("in ReportingDAO getCustomerBookingAnalysis  gpsize--->"+gpsize);
		if (size > 0) {
			return (CustomerBusinessAnalysisDTO[]) list
					.toArray(new CustomerBusinessAnalysisDTO[size]);
		}
		return null;

	}
	

	public CustomerBusinessAnalysisDTO[] getCustomerBusinessAnalysisReportHistory(
			boolean isConsignor, String branch, String from_date,
			String to_date, String dbHis) throws RemoteException,
			BusinessException, Exception {

		Connection con = null;
		PreparedStatement ps = null;		
		ResultSet rs = null;
		ArrayList<CustomerBusinessAnalysisDTO> list = new ArrayList<CustomerBusinessAnalysisDTO>();
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);

		CustomerBusinessAnalysisDTO dto = null;

		try {
			if (dbHis == null || dbHis.equals("")) {
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			} else {
				DBHelperHist helperHis = new DBHelperHist();
				con = helperHis.getConnectionHistory(dbHis);
			}

			if (null != con) {
				if (isConsignor)
					ps = con
							.prepareStatement(GET_CNOR_BUSINESS_ANAYSIS_HISTORY);
				else
					ps = con
							.prepareStatement(GET_CNEE_BUSINESS_ANAYSIS_HISTORY);
				ps.setString(1, branch);
				ps.setString(2, from_date);
				ps.setString(3, to_date);

				if (ps != null) {
					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {
							
							dto = new CustomerBusinessAnalysisDTO();

							dto.setFromStation(rs.getString(STATION_CODE));
							dto.setCustomerName(rs.getString(CUSTOMER_NAME));
							dto.setBasic_freight(rs.getFloat(BASIC_FRIEGHT));
							dto.setTotal_freight(rs.getFloat(TOTAL));
							dto.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
							dto.setCharged_weight(rs.getFloat(CHARGED_WEIGHT));
							dto.setNoa(rs.getInt(NO_OF_ARTICLES));
							dto.setLr_date(format.parse(rs.getString(LR_DATE)));
							dto.setCount(rs.getInt(COUNT));
							dto.setRate_type(rs.getInt(RATE_TYPE));
							
							list.add(dto);
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = list.size();
		if (size > 0) {
			return (CustomerBusinessAnalysisDTO[]) list
					.toArray(new CustomerBusinessAnalysisDTO[size]);
		}
		return null;

	}
	
	
	public BookingDTO[] getBPAReport(Date from, Date to,
			String branch,String DBHistory) throws RemoteException, BusinessException,
			Exception {
		System.out.println("in ReportingDAO getBpa");
		Connection con = null;
		PreparedStatement ps = null;
		DBHelper helper = new DBHelper();
		ResultSet rs = null;
		ArrayList<BookingDTO> outList = new ArrayList<BookingDTO>();

		BookingDTO dto = null;

		try {
			con = helper.getConnection();

			if (null != con) {
				if(branch.equalsIgnoreCase("All")){
					ps = con.prepareStatement(GET_BPA);
				}
				else
				{
					ps = con.prepareStatement(GET_BPA_BRANCH);
				}
				if (ps != null) {

					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(from);
					String toformattedDate = format.format(to);
					if(branch.equalsIgnoreCase("All")){
						ps.setString(1, formattedDate);
						ps.setString(2, toformattedDate);
					}
					else{
						ps.setString(1, branch);
						ps.setString(2, formattedDate);
						ps.setString(3, toformattedDate);
						
					}
						

					rs = ps.executeQuery();
					if (null != rs) {
							while (rs.next()) {

								dto = new BookingDTO();

								dto.setFrom(rs.getString(FROM_STATION));
								dto.setRate_type(rs.getInt(LR_NO));
								dto.setNo_of_articles(rs.getInt(NO_OF_ARTICLES));
								dto.setActual_weight(rs
												.getFloat(ACTUAL_WEIGHT));
								dto.setCharged_weight(rs
										.getFloat(CHARGED_WEIGHT));
								dto.setBft(rs.getFloat(BASIC_FRIEGHT));
								dto.setLrc(rs.getFloat(LRC));
								dto.setDhc(rs.getFloat(DHC));
								dto.setCcc(rs.getFloat(CCC));
								dto.setDcc(rs.getFloat(DCC));
								dto.setDdc(rs.getFloat(DDC));
								dto.setIec(rs.getFloat(IEC));
								dto.setLc(rs.getFloat(LC));
								dto.setStax(rs.getFloat(STAX));
								dto.setTotal(rs.getFloat(TOTAL));

								outList.add(dto);
							}
						} 
					}
				}

			

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = outList.size();
		System.out.println("in ReportingDAO getBpa--->"+size);
		if (size > 0) {
			return (BookingDTO[]) outList.toArray(new BookingDTO[size]);
		}
		return null;

	}

	
	public BookingDTO[] getBPAReportUnion(Date from, Date to,
			String branch,String DBHistory) throws RemoteException, BusinessException,
			Exception {
		System.out.println("in ReportingDAO getBpa");
		Connection con = null;
		PreparedStatement ps = null;
		DBHelper helper = new DBHelper();
		ResultSet rs = null;
		ArrayList<BookingDTO> outList = new ArrayList<BookingDTO>();

		BookingDTO dto = null;

		try {
			con = helper.getConnection();

			if (null != con) {
				if(branch.equalsIgnoreCase("All")){
					ps = con.prepareStatement(GET_BPA_UNION);
				}
				else
				{
					ps = con.prepareStatement(GET_BPA_BRANCH_UNION);
				}
				if (ps != null) {

					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(from);
					String toformattedDate = format.format(to);
					if(branch.equalsIgnoreCase("All")){
						ps.setString(1, formattedDate);
						ps.setString(2, toformattedDate);
						ps.setString(3, formattedDate);
						ps.setString(4, toformattedDate);
					}
					else{
						ps.setString(1, branch);
						ps.setString(2, formattedDate);
						ps.setString(3, toformattedDate);
						ps.setString(4, branch);
						ps.setString(5, formattedDate);
						ps.setString(6, toformattedDate);
						
					}
						

					rs = ps.executeQuery();
					if (null != rs) {
							while (rs.next()) {

								dto = new BookingDTO();

								dto.setFrom(rs.getString(FROM_STATION));
								dto.setRate_type(rs.getInt(LR_NO));
								dto.setNo_of_articles(rs.getInt(NO_OF_ARTICLES));
								dto.setActual_weight(rs
												.getFloat(ACTUAL_WEIGHT));
								dto.setCharged_weight(rs
										.getFloat(CHARGED_WEIGHT));
								dto.setBft(rs.getFloat(BASIC_FRIEGHT));
								dto.setLrc(rs.getFloat(LRC));
								dto.setDhc(rs.getFloat(DHC));
								dto.setCcc(rs.getFloat(CCC));
								dto.setDcc(rs.getFloat(DCC));
								dto.setDdc(rs.getFloat(DDC));
								dto.setIec(rs.getFloat(IEC));
								dto.setLc(rs.getFloat(LC));
								dto.setStax(rs.getFloat(STAX));
								dto.setTotal(rs.getFloat(TOTAL));

								outList.add(dto);
							}
						} 
					}
				}

			

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = outList.size();
		System.out.println("in ReportingDAO getBpa--->"+size);
		if (size > 0) {
			return (BookingDTO[]) outList.toArray(new BookingDTO[size]);
		}
		return null;

	}

	
	public BookingDTO[] getBPAReportHistory(Date from, Date to,
			String branch,String DBHistory) throws RemoteException, BusinessException,
			Exception {
		System.out.println("in ReportingDAO getBpa");
		Connection con = null;
		PreparedStatement ps = null;
		DBHelper helper = new DBHelper();
		ResultSet rs = null;
		ArrayList<BookingDTO> outList = new ArrayList<BookingDTO>();

		BookingDTO dto = null;

		try {
			con = helper.getConnection();

			if (null != con) {
				if(branch.equalsIgnoreCase("All")){
					ps = con.prepareStatement(GET_BPA_HISTORY);
				}
				else
				{
					ps = con.prepareStatement(GET_BPA_BRANCH_HISTORY);
				}
				if (ps != null) {

					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(from);
					String toformattedDate = format.format(to);
					if(branch.equalsIgnoreCase("All")){
						ps.setString(1, formattedDate);
						ps.setString(2, toformattedDate);
					}
					else{
						ps.setString(1, branch);
						ps.setString(2, formattedDate);
						ps.setString(3, toformattedDate);
						
					}
						

					rs = ps.executeQuery();
					if (null != rs) {
							while (rs.next()) {

								dto = new BookingDTO();

								dto.setFrom(rs.getString(FROM_STATION));
								dto.setRate_type(rs.getInt(LR_NO));
								dto.setNo_of_articles(rs.getInt(NO_OF_ARTICLES));
								dto.setActual_weight(rs
												.getFloat(ACTUAL_WEIGHT));
								dto.setCharged_weight(rs
										.getFloat(CHARGED_WEIGHT));
								dto.setBft(rs.getFloat(BASIC_FRIEGHT));
								dto.setLrc(rs.getFloat(LRC));
								dto.setDhc(rs.getFloat(DHC));
								dto.setCcc(rs.getFloat(CCC));
								dto.setDcc(rs.getFloat(DCC));
								dto.setDdc(rs.getFloat(DDC));
								dto.setIec(rs.getFloat(IEC));
								dto.setLc(rs.getFloat(LC));
								dto.setStax(rs.getFloat(STAX));
								dto.setTotal(rs.getFloat(TOTAL));

								outList.add(dto);
							}
						} 
					}
				}

			

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = outList.size();
		System.out.println("in ReportingDAO getBpa--->"+size);
		if (size > 0) {
			return (BookingDTO[]) outList.toArray(new BookingDTO[size]);
		}
		return null;

	}

	
	/*public BusinessPerformanceDTO[] getBPAReport(Date from, Date to,
			String branch,String DBHistory) throws Exception {
		Connection con = null;
		PreparedStatement pst = null;
		ArrayList<BusinessPerformanceDTO> outList = new ArrayList<BusinessPerformanceDTO>();
		ResultSet rs = null;
		String fromDate = "";
		String toDate = "";

		String GET_BPA_REPORT = "";
		BusinessPerformanceDTO bpa = null;

		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
		fromDate = format.format(from);
		toDate = format.format(to);

		int index = 0;

		index = fromDate.indexOf("-");
		int year = Integer.parseInt(fromDate.substring(0, index));
		int dtInd = fromDate.indexOf("-", index + 1);
		int month = Integer.parseInt(fromDate.substring(index + 1, dtInd));
		int sameDt = Integer.parseInt(fromDate.substring(dtInd + 1));
		String prevFromMonth = (year) + "-" + (month - 1) + "-" + sameDt;

		String prevFromYear = (year - 1) + "-" + (month) + "-" + sameDt;

		//System.out.println("lm===>" + prevFromMonth);
		//System.out.println("ly===>" + prevFromYear);

		index = toDate.indexOf("-");
		year = Integer.parseInt(toDate.substring(0, index));
		dtInd = toDate.indexOf("-", index + 1);
		month = Integer.parseInt(toDate.substring(index + 1, dtInd));
		sameDt = Integer.parseInt(toDate.substring(dtInd + 1));
		String prevToMonth = (year) + "-" + (month - 1) + "-" + sameDt;

		String prevToYear = (year - 1) + "-" + (month) + "-" + sameDt;

		//System.out.println("lm to===>" + prevToMonth);
		//System.out.println("ly to===>" + prevToYear);

		boolean isBranch = false;
		
		if (branch.startsWith("All")) {
			// ALL BRANCH
			if (branch.charAt(3) == 'C') {
				isBranch = true;
				GET_BPA_REPORT = "select 2 as lr_date, s.branch_code as station, ifnull(sum(a.bft),0) as bft, "
						+ "ifnull(sum(a.total),0) as total, count(a.lr_no) as count, ifnull(sum(a.actual_weight),0) as actual_weight,"
						+ "ifnull(sum(a.charged_weight),0) as charged_weight, ifnull(sum(a.no_of_articles),0) as no_of_articles "
						+ "from lr a, station s "
						+ "where a.lr_status != 1 and date(a.lr_date) between '"
						+ fromDate
						+ "' and '"
						+ toDate
						+ "' and "
						+ "a.from_station = s.station_code and s.branch_code in "
						+ "(select s.station_code from station s where s.station_code=s.branch_code) "
						+ "group by s.branch_code "
						+ "union "
						+ "select 4 as lr_date, s.branch_code as station, ifnull(sum(a.bft),0) as lm_bft, "
						+ "ifnull(sum(a.total),0) as lm_total, count(a.lr_no) as lm_count, ifnull(sum(a.actual_weight),0) as lm_actual_weight,"
						+ "ifnull(sum(a.charged_weight),0) as lm_charged_weight, ifnull(sum(a.no_of_articles),0) as lm_no_of_articles "
						+ "from lr a, station s "
						+ "where a.lr_status != 1 and date(a.lr_date) between '"
						+ prevFromMonth
						+ "' and '"
						+ prevToMonth
						+ "' and "
						+ "a.from_station = s.station_code and s.branch_code in "
						+ "(select s.station_code from station s where s.station_code=s.branch_code) "
						+ "group by s.branch_code "
						+ "union "
						+ "select 6 as lr_date, s.branch_code as station, ifnull(sum(a.bft),0) as ly_bft, "
						+ "ifnull(sum(a.total),0) as ly_total,count(a.lr_no) as ly_count, ifnull(sum(a.actual_weight),0) as ly_actual_weight,"
						+ "ifnull(sum(a.charged_weight),0) as ly_charged_weight, ifnull(sum(a.no_of_articles),0) as ly_no_of_articles "
						+ "from lr a, station s "
						+ "where a.lr_status != 1 and date(a.lr_date) between '"
						+ prevFromYear
						+ "' and '"
						+ prevToYear
						+ "' and "
						+ "a.from_station = s.station_code and s.branch_code in "
						+ "(select s.station_code from station s where s.station_code=s.branch_code) "
						+ "group by s.branch_code";
			} else {
				// ALL Stations
				GET_BPA_REPORT = "select 2 as lr_date, a.from_station as station, ifnull(sum(a.bft),0) as bft, "
						+ "ifnull(sum(a.total),0) as total, count(a.lr_no) as count, ifnull(sum(a.actual_weight),0) as actual_weight,"
						+ "ifnull(sum(a.charged_weight),0) as charged_weight, ifnull(sum(a.no_of_articles),0) as no_of_articles "
						+ "from lr a, station s where a.lr_status != 1 and date(a.lr_date) between '"
						+ fromDate
						+ "' and '"
						+ toDate
						+ "' "
						+ "and a.from_station = s.station_code and s.branch_code in "
						+ "(select s.station_code from station s where s.station_code=s.branch_code) "
						+ "group by s.station_code "
						+ "union "
						+ "select 4 as lr_date, a.from_station as station, ifnull(sum(a.bft),0) as lm_bft, "
						+ "ifnull(sum(a.total),0) as lm_total, count(a.lr_no) as lm_count, ifnull(sum(a.actual_weight),0) as lm_actual_weight,"
						+ "ifnull(sum(a.charged_weight),0) as lm_charged_weight, ifnull(sum(a.no_of_articles),0) as lm_no_of_articles "
						+ "from lr a, station s where a.lr_status != 1 and date(a.lr_date) between '"
						+ prevFromMonth
						+ "' and '"
						+ prevToMonth
						+ "' "
						+ "and a.from_station = s.station_code and s.branch_code in "
						+ "(select s.station_code from station s where s.station_code=s.branch_code) "
						+ "group by s.station_code "
						+ "union "
						+ "select 6 as lr_date, a.from_station as station, ifnull(sum(a.bft),0) as ly_bft, "
						+ "ifnull(sum(a.total),0) as ly_total, count(a.lr_no) as ly_count, ifnull(sum(a.actual_weight),0) as ly_actual_weight,"
						+ "ifnull(sum(a.charged_weight),0) as ly_charged_weight, ifnull(sum(a.no_of_articles),0) as ly_no_of_articles "
						+ "from lr a, station s where a.lr_status != 1 and date(a.lr_date) between '"
						+ prevFromYear
						+ "' and '"
						+ prevToYear
						+ "' "
						+ "and a.from_station = s.station_code and s.branch_code in "
						+ "(select s.station_code from station s where s.station_code=s.branch_code) "
						+ "group by s.station_code";
			}

		} else {
			// selected branch stations
			/*
			 * GET_BPA_REPORT = "select a.from_station as station,
			 * ifnull(sum(a.bft),0) as bft, ifnull(sum(a.total),0) as total " +
			 * "from lr a, station s where date(a.lr_date) between
			 * '"+fromDate+"' and '"+toDate+"' " + "and a.from_station =
			 * s.station_code and s.branch_code = '"+branch+"' group by
			 * s.station_code";
			 *\/
			
			GET_BPA_REPORT = "select 2 as lr_date, a.from_station as station, ifnull(sum(a.bft),0) as bft,"
					+ "ifnull(sum(a.total),0) as total, count(a.lr_no) as count, ifnull(sum(a.actual_weight),0) as actual_weight,"
					+ "ifnull(sum(a.charged_weight),0) as charged_weight, ifnull(sum(a.no_of_articles),0) as no_of_articles "
					+ "from lr a, station s where a.lr_status != 1 and date(a.lr_date) between '"
					+ fromDate
					+ "' and '"
					+ toDate
					+ "' "
					+ "and a.from_station = s.station_code and s.branch_code = '"
					+ branch
					+ "' group by a.from_station "
					+ "union "
					+ "select 4 as lr_date, a.from_station as station, ifnull(sum(a.bft),0) as lm_bft,"
					+ "ifnull(sum(a.total),0) as lm_total, count(a.lr_no) as lm_count, ifnull(sum(a.actual_weight),0) as lm_actual_weight,"
					+ "ifnull(sum(a.charged_weight),0) as lm_charged_weight, ifnull(sum(a.no_of_articles),0) as lm_no_of_articles "
					+ "from lr a, station s where a.lr_status != 1 and date(a.lr_date) between '"
					+ prevFromMonth
					+ "' and '"
					+ prevToMonth
					+ "' and "
					+ "a.from_station = s.station_code and s.branch_code = '"
					+ branch
					+ "' group by a.from_station "
					+ "union "
					+ "select 6 as lr_date, a.from_station as station, ifnull(sum(a.bft),0) as ly_bft,"
					+ "ifnull(sum(a.total),0) as ly_total, count(a.lr_no) as ly_count, ifnull(sum(a.actual_weight),0) as ly_actual_weight,"
					+ "ifnull(sum(a.charged_weight),0) as ly_charged_weight, ifnull(sum(a.no_of_articles),0) as ly_no_of_articles "
					+ "from lr a, station s where a.lr_status != 1 and date(a.lr_date) between '"
					+ prevFromYear
					+ "' and '"
					+ prevToYear
					+ "' and "
					+ "a.from_station = s.station_code and s.branch_code = '"
					+ branch + "' group by a.from_station";

		}

		try {

			DBHelper db = new DBHelper();
			con = db.getConnection();
			int days = 0;
			if (null != con) {
				pst = con.prepareStatement(GET_BPA_REPORT);
				
				if (pst != null) {
					rs = pst.executeQuery();
					if (null != rs) {
						while (rs.next()) {
							bpa = new BusinessPerformanceDTO();
							bpa.setStation(rs.getString(STATION));
							bpa.setTbl_index(rs.getInt("lr_date"));
							bpa.setCount(rs.getInt(COUNT));
							bpa.setBasic_freight(rs.getFloat(BASIC_FRIEGHT));
							bpa.setTotal_freight(rs.getFloat(TOTAL));
							bpa.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
							bpa.setCharged_weight(rs.getFloat(CHARGED_WEIGHT));
							bpa.setNoa(rs.getInt(NO_OF_ARTICLES));
							
							bpa = getHighestBPA(con, bpa, isBranch);
							//System.out.println("bpa-->"+bpa);
							days = getRestOfHolidays(con, fromDate, toDate);
							//System.out.println("days-->"+days);
							bpa.setWorking_days(days);
							
							outList.add(bpa);
						}
					}
				}	

			}
		} catch (SQLException sqlexception) {
			throw sqlexception;
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != pst)
					pst.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			}
		}

		int size = outList.size();
		//System.out.println("getBPAReport size--->"+size);
		if (size > 0) {
			return outList.toArray(new BusinessPerformanceDTO[size]);
		}
		return null;
	}

	
	

	
	public BusinessPerformanceDTO[] getBPAReportHistory(Date from, Date to,
			String branch, String dbHis) throws Exception {
		Connection con = null;
		PreparedStatement pst = null;
		ArrayList<BusinessPerformanceDTO> outList = new ArrayList<BusinessPerformanceDTO>();
		ResultSet rs = null;
		String fromDate = "";
		String toDate = "";

		String GET_BPA_REPORT = "";
		BusinessPerformanceDTO bpa = null;

		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
		fromDate = format.format(from);
		toDate = format.format(to);

		int index = 0;

		index = fromDate.indexOf("-");
		int year = Integer.parseInt(fromDate.substring(0, index));
		int dtInd = fromDate.indexOf("-", index + 1);
		int month = Integer.parseInt(fromDate.substring(index + 1, dtInd));
		int sameDt = Integer.parseInt(fromDate.substring(dtInd + 1));
		String prevFromMonth = (year) + "-" + (month - 1) + "-" + sameDt;

		String prevFromYear = (year - 1) + "-" + (month) + "-" + sameDt;

		//System.out.println("lm===>" + prevFromMonth);
		//System.out.println("ly===>" + prevFromYear);

		index = toDate.indexOf("-");
		year = Integer.parseInt(toDate.substring(0, index));
		dtInd = toDate.indexOf("-", index + 1);
		month = Integer.parseInt(toDate.substring(index + 1, dtInd));
		sameDt = Integer.parseInt(toDate.substring(dtInd + 1));
		String prevToMonth = (year) + "-" + (month - 1) + "-" + sameDt;

		String prevToYear = (year - 1) + "-" + (month) + "-" + sameDt;
		boolean isBranch = false;

		if (branch.startsWith("All")) {
			// ALL BRANCH
			if (branch.charAt(3) == 'C') {
				isBranch = true;
				GET_BPA_REPORT = "select 2 as lr_date, s.branch_code as station, ifnull(sum(a.bft),0) as bft, "
						+ "ifnull(sum(a.total),0) as total, count(a.lr_no) as count, ifnull(sum(a.actual_weight),0) as actual_weight, "
						+ "ifnull(sum(a.charged_weight),0) as charged_weight, ifnull(sum(a.no_of_articles),0) as no_of_articles "
						+ "from lr_history a, station s "
						+ "where a.lr_status != 1 and date(a.lr_date) between '"
						+ fromDate
						+ "' and '"
						+ toDate
						+ "' and "
						+ "a.from_station = s.station_code and s.branch_code in "
						+ "(select s.station_code from station s where s.station_code=s.branch_code) "
						+ "group by s.branch_code "
						+ "union "
						+ "select 4 as lr_date, s.branch_code as station, ifnull(sum(a.bft),0) as lm_bft, "
						+ "ifnull(sum(a.total),0) as lm_total, count(a.lr_no) as lm_count, ifnull(sum(a.actual_weight),0) as lm_actual_weight, "
						+ "ifnull(sum(a.charged_weight),0) as lm_charged_weight, ifnull(sum(a.no_of_articles),0) as lm_no_of_articles "
						+ "from lr_history a, station s "
						+ "where a.lr_status != 1 and date(a.lr_date) between '"
						+ prevFromMonth
						+ "' and '"
						+ prevToMonth
						+ "' and "
						+ "a.from_station = s.station_code and s.branch_code in "
						+ "(select s.station_code from station s where s.station_code=s.branch_code) "
						+ "group by s.branch_code "
						+ "union "
						+ "select 6 as lr_date, s.branch_code as station, ifnull(sum(a.bft),0) as ly_bft, "
						+ "ifnull(sum(a.total),0) as ly_total,count(a.lr_no) as ly_count, ifnull(sum(a.actual_weight),0) as ly_actual_weight, "
						+ "ifnull(sum(a.charged_weight),0) as ly_charged_weight, ifnull(sum(a.no_of_articles),0) as ly_no_of_articles "
						+ "from lr_history a, station s "
						+ "where a.lr_status != 1 and date(a.lr_date) between '"
						+ prevFromYear
						+ "' and '"
						+ prevToYear
						+ "' and "
						+ "a.from_station = s.station_code and s.branch_code in "
						+ "(select s.station_code from station s where s.station_code=s.branch_code) "
						+ "group by s.branch_code";
			} else {
				// ALL Stations
				GET_BPA_REPORT = "select 2 as lr_date, a.from_station as station, ifnull(sum(a.bft),0) as bft, "
						+ "ifnull(sum(a.total),0) as total, count(a.lr_no) as count, ifnull(sum(a.actual_weight),0) as actual_weight, "
						+ "ifnull(sum(a.charged_weight),0) as charged_weight, ifnull(sum(a.no_of_articles),0) as no_of_articles "
						+ "from lr_history a, station s where a.lr_status != 1 and date(a.lr_date) between '"
						+ fromDate
						+ "' and '"
						+ toDate
						+ "' "
						+ "and a.from_station = s.station_code and s.branch_code in "
						+ "(select s.station_code from station s where s.station_code=s.branch_code) "
						+ "group by s.station_code "
						+ "union "
						+ "select 4 as lr_date, a.from_station as station, ifnull(sum(a.bft),0) as lm_bft, "
						+ "ifnull(sum(a.total),0) as lm_total, count(a.lr_no) as lm_count, ifnull(sum(a.actual_weight),0) as lm_actual_weight, "
						+ "ifnull(sum(a.charged_weight),0) as lm_charged_weight, ifnull(sum(a.no_of_articles),0) as lm_no_of_articles "
						+ "from lr_history a, station s where a.lr_status != 1 and date(a.lr_date) between '"
						+ prevFromMonth
						+ "' and '"
						+ prevToMonth
						+ "' "
						+ "and a.from_station = s.station_code and s.branch_code in "
						+ "(select s.station_code from station s where s.station_code=s.branch_code) "
						+ "group by s.station_code "
						+ "union "
						+ "select 6 as lr_date, a.from_station as station, ifnull(sum(a.bft),0) as ly_bft, "
						+ "ifnull(sum(a.total),0) as ly_total, count(a.lr_no) as ly_count, ifnull(sum(a.actual_weight),0) as ly_actual_weight, " 
						+ "ifnull(sum(a.charged_weight),0) as ly_charged_weight, ifnull(sum(a.no_of_articles),0) as ly_no_of_articles "
						+ "from lr_history a, station s where a.lr_status != 1 and date(a.lr_date) between '"
						+ prevFromYear
						+ "' and '"
						+ prevToYear
						+ "' "
						+ "and a.from_station = s.station_code and s.branch_code in "
						+ "(select s.station_code from station s where s.station_code=s.branch_code) "
						+ "group by s.station_code";
			}

		} else {
			// selected branch stations
			/*
			 * GET_BPA_REPORT = "select a.from_station as station,
			 * ifnull(sum(a.bft),0) as bft, ifnull(sum(a.total),0) as total " +
			 * "from lr a, station s where date(a.lr_date) between
			 * '"+fromDate+"' and '"+toDate+"' " + "and a.from_station =
			 * s.station_code and s.branch_code = '"+branch+"' group by
			 * s.station_code";
			 *\/

			GET_BPA_REPORT = "select 2 as lr_date, a.from_station as station, ifnull(sum(a.bft),0) as bft, "
					+ "ifnull(sum(a.total),0) as total, count(a.lr_no) as count, ifnull(sum(a.actual_weight),0) as actual_weight, "
					+ "ifnull(sum(a.charged_weight),0) as charged_weight, ifnull(sum(a.no_of_articles),0) as no_of_articles "
					+ "from lr_history a, station s where a.lr_status != 1 and date(a.lr_date) between '"
					+ fromDate
					+ "' and '"
					+ toDate
					+ "' "
					+ "and a.from_station = s.station_code and s.branch_code = '"
					+ branch
					+ "' group by a.from_station "
					+ "union "
					+ "select 4 as lr_date, a.from_station as station, ifnull(sum(a.bft),0) as lm_bft, "
					+ "ifnull(sum(a.total),0) as lm_total, count(a.lr_no) as lm_count, ifnull(sum(a.actual_weight),0) as lm_actual_weight, "
					+ "ifnull(sum(a.charged_weight),0) as lm_charged_weight, ifnull(sum(a.no_of_articles),0) as lm_no_of_articles "
					+ "from lr_history a, station s where a.lr_status != 1 and date(a.lr_date) between '"
					+ prevFromMonth
					+ "' and '"
					+ prevToMonth
					+ "' and "
					+ "a.from_station = s.station_code and s.branch_code = '"
					+ branch
					+ "' group by a.from_station "
					+ "union "
					+ "select 6 as lr_date, a.from_station as station, ifnull(sum(a.bft),0) as ly_bft, "
					+ "ifnull(sum(a.total),0) as ly_total, count(a.lr_no) as ly_count, ifnull(sum(a.actual_weight),0) as ly_actual_weight, "
					+ "ifnull(sum(a.charged_weight),0) as ly_charged_weight, ifnull(sum(a.no_of_articles),0) as ly_no_of_articles "
					+ "from lr_history a, station s where a.lr_status != 1 and date(a.lr_date) between '"
					+ prevFromYear
					+ "' and '"
					+ prevToYear
					+ "' and "
					+ "a.from_station = s.station_code and s.branch_code = '"
					+ branch + "' group by a.from_station ";

		}

		try {

			if (dbHis == null || dbHis.equals("")) {
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			} else {
				DBHelperHist helperHis = new DBHelperHist();
				con = helperHis.getConnectionHistory(dbHis);
			}
			
			int days = 0;
			if (null != con) {
				pst = con.prepareStatement(GET_BPA_REPORT);

				if (pst != null) {
					rs = pst.executeQuery();
					if (null != rs) {
						while (rs.next()) {
							bpa = new BusinessPerformanceDTO();
							bpa.setStation(rs.getString(STATION));
							bpa.setTbl_index(rs.getInt("lr_date"));
							bpa.setCount(rs.getInt(COUNT));
							bpa.setBasic_freight(rs.getFloat(BASIC_FRIEGHT));
							bpa.setTotal_freight(rs.getFloat(TOTAL));
							bpa.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
							bpa.setCharged_weight(rs.getFloat(CHARGED_WEIGHT));
							bpa.setNoa(rs.getInt(NO_OF_ARTICLES));

							bpa = getHighestBPA(con, bpa, isBranch);
							
							days = getRestOfHolidays(con, fromDate, toDate);
							
							bpa.setWorking_days(days);

							outList.add(bpa);
						}
					}
				}

			}
		} catch (SQLException sqlexception) {
			throw sqlexception;
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != pst)
					pst.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			}
		}

		int size = outList.size();
		System.out.println("size-->"+size);
		if (size > 0) {
			return outList.toArray(new BusinessPerformanceDTO[size]);
		}
		return null;
	}

	private int getRestOfHolidays(Connection con, String from, String to)
			throws Exception {

		PreparedStatement ps = null;
		ResultSet rs = null;
		CallableStatement cs = null;

		try {
			if (con != null) {
				cs = con.prepareCall("{call BPA_holiday(?,?)}");
				if (cs != null) {
					cs.setString(1, from);
					cs.setString(2, to);

					cs.executeate();
					rs = cs.getResultSet();

					if (null != rs) {
						if (rs.next()) {
							return rs.getInt("days");
						}
					}
				}
			}
		} catch (SQLException sqlexception) {
			throw sqlexception;
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();

			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (ps != null)
					ps.close();
			}
		}
		
		return 0;
	}

	private BusinessPerformanceDTO getHighestBPA(Connection con,
			BusinessPerformanceDTO bpa, boolean isBranch) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			if (con != null) {
				if (isBranch) {
					ps = con.prepareStatement(GET_HIGH_BPA_BRANCH);
				} else {
					ps = con.prepareStatement(GET_HIGH_BPA);
				}

				ps.setString(1, bpa.getStation());

				if (ps != null) {
					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {
							bpa.setHigh_basic_freight(rs
									.getFloat(HIGH_BASIC_FRIEGHT));
							bpa.setHigh_total_freight(rs.getFloat(HIGH_TOTAL));
							bpa.setHigh_actual_weight(rs
									.getFloat(HIGH_ACTUAL_WEIGHT));
							bpa.setHigh_charged_weight(rs
									.getFloat(HIGH_CHARGED_WEIGHT));
							bpa.setHigh_noa(rs.getInt(HIGH_NO_OF_ARTICLES));
						}
					}
				}
			}
		} catch (SQLException sqlexception) {
			throw sqlexception;
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();

			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (ps != null)
					ps.close();
			}
		}
		

		return bpa;

	}*/

	public GeneralSummaryDTO[] getCCChargeSummary(Date fromDate, Date toDate,
			String branch, String DBHistory) throws RemoteException,
			BusinessException, Exception {

		Connection con = null;
		PreparedStatement ps = null;
		DBHelper helper = new DBHelper();
		ResultSet rs = null;
		ArrayList<GeneralSummaryDTO> cccSummary = new ArrayList<GeneralSummaryDTO>();
		GeneralSummaryDTO dto = null;

		try {
			con = helper.getConnection();

			if (null != con) {
				ps = con.prepareStatement(GET_CCCHARGE_SUMMARY_BFT);

				if (ps != null) {

					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(fromDate);
					String tofomattedDate = format.format(toDate);

					ps.setString(1, formattedDate);
					ps.setString(2, tofomattedDate);
					ps.setString(3, branch);

					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {
							dto = new GeneralSummaryDTO();

							dto.setStation_code(rs.getString(FROM_STATION));
							dto.setBranch_code(rs.getString(BRANCH_CODE));
							dto.setBft(rs.getFloat(BASIC_FRIEGHT));
							dto.setCcc(rs.getFloat(CCC));
							dto.setLr_no(rs.getInt("CC_LRs"));

							// dto = getCCCSummaryPDTPCount(con,fromDate,
							// toDate, branch, dto);

							cccSummary.add(dto);
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = cccSummary.size();

		if (size > 0) {
			return (GeneralSummaryDTO[]) cccSummary
					.toArray(new GeneralSummaryDTO[size]);
		}
		return null;

	}

	
	public GeneralSummaryDTO[] getCCChargeSummaryUnion(Date fromDate, Date toDate,
			String branch, String DBHistory) throws RemoteException,
			BusinessException, Exception {

		Connection con = null;
		PreparedStatement ps = null;
		DBHelper helper = new DBHelper();
		ResultSet rs = null;
		ArrayList<GeneralSummaryDTO> cccSummary = new ArrayList<GeneralSummaryDTO>();
		GeneralSummaryDTO dto = null;

		try {
			con = helper.getConnection();

			if (null != con) {
				ps = con.prepareStatement(GET_CCCHARGE_SUMMARY_BFT_UNION);

				if (ps != null) {

					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(fromDate);
					String tofomattedDate = format.format(toDate);

					ps.setString(1, formattedDate);
					ps.setString(2, tofomattedDate);
					ps.setString(3, branch);
					ps.setString(4, formattedDate);
					ps.setString(5, tofomattedDate);
					ps.setString(6, branch);

					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {
							dto = new GeneralSummaryDTO();

							dto.setStation_code(rs.getString(FROM_STATION));
							dto.setBranch_code(rs.getString(BRANCH_CODE));
							dto.setBft(rs.getFloat(BASIC_FRIEGHT));
							dto.setCcc(rs.getFloat(CCC));
							dto.setLr_no(rs.getInt("CC_LRs"));

							// dto = getCCCSummaryPDTPCount(con,fromDate,
							// toDate, branch, dto);

							cccSummary.add(dto);
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = cccSummary.size();

		if (size > 0) {
			return (GeneralSummaryDTO[]) cccSummary
					.toArray(new GeneralSummaryDTO[size]);
		}
		return null;

	}

	
	public GeneralSummaryDTO[] getCCChargeSummaryHistory(Date fromDate,
			Date toDate, String branch, String dbHis)
			throws RemoteException, BusinessException, Exception {

		Connection con = null;
		PreparedStatement ps = null;		
		ResultSet rs = null;
		ArrayList<GeneralSummaryDTO> cccSummary = new ArrayList<GeneralSummaryDTO>();
		GeneralSummaryDTO dto = null;

		try {
			
			if (dbHis == null || dbHis.equals("")) {
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			} else {
				DBHelperHist helperHis = new DBHelperHist();
				con = helperHis.getConnectionHistory(dbHis);
			}

			if (null != con) {
				ps = con.prepareStatement(GET_CCCHARGE_SUMMARY_BFT_HISTORY);

				if (ps != null) {

					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(fromDate);
					String tofomattedDate = format.format(toDate);

					ps.setString(1, formattedDate);
					ps.setString(2, tofomattedDate);
					ps.setString(3, branch);

					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {
							dto = new GeneralSummaryDTO();

							dto.setStation_code(rs.getString(FROM_STATION));
							dto.setBranch_code(rs.getString(BRANCH_CODE));
							dto.setBft(rs.getFloat(BASIC_FRIEGHT));
							dto.setCcc(rs.getFloat(CCC));
							dto.setLr_no(rs.getInt("CC_LRs"));

							// dto = getCCCSummaryPDTPCount(con,fromDate,
							// toDate, branch, dto);

							cccSummary.add(dto);
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = cccSummary.size();

		if (size > 0) {
			return (GeneralSummaryDTO[]) cccSummary
					.toArray(new GeneralSummaryDTO[size]);
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
	public GeneralSummaryDTO[] getCCCSummaryPDTPCount(Date fromDate,
			Date toDate, String branch, String DBHistory) throws Exception {

		Connection con = null;
		PreparedStatement ps = null;
		DBHelper helper = new DBHelper();
		ResultSet rs = null;
		GeneralSummaryDTO dto = null;
		ArrayList<GeneralSummaryDTO> cccSummary = new ArrayList<GeneralSummaryDTO>();

		try {
			con = helper.getConnection();
			if (null != con) {
				ps = con.prepareStatement(GET_CCCHARGE_SUMMARY_PDTP);

				if (ps != null) {

					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(fromDate);
					String tofomattedDate = format.format(toDate);

					ps.setString(1, formattedDate);
					ps.setString(2, tofomattedDate);
					ps.setString(3, branch);

					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {
							dto = new GeneralSummaryDTO();
							dto.setStation_code(rs.getString(FROM_STATION));
							dto.setBranch_code(rs.getString(BRANCH_CODE));
							dto.setNo_of_articles(rs.getInt("PD_TP_LRs"));
							dto.setBft(rs.getFloat(BASIC_FRIEGHT));
							dto.setCcc(rs.getFloat(CCC));
							cccSummary.add(dto);
						}
					}

				}
			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();

			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}
		int size = cccSummary.size();
		System.out.println("Out ReportingDAO getCCChargeSummary PD/TP===>"
				+ size);
		if (size > 0) {
			return (GeneralSummaryDTO[]) cccSummary
					.toArray(new GeneralSummaryDTO[size]);
		}
		return null;

	}
	
	
	public GeneralSummaryDTO[] getCCCSummaryPDTPCountUnion(Date fromDate,
			Date toDate, String branch, String DBHistory) throws Exception {

		Connection con = null;
		PreparedStatement ps = null;
		DBHelper helper = new DBHelper();
		ResultSet rs = null;
		GeneralSummaryDTO dto = null;
		ArrayList<GeneralSummaryDTO> cccSummary = new ArrayList<GeneralSummaryDTO>();

		try {
			con = helper.getConnection();
			if (null != con) {
				ps = con.prepareStatement(GET_CCCHARGE_SUMMARY_PDTP_UNION);

				if (ps != null) {

					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(fromDate);
					String tofomattedDate = format.format(toDate);

					ps.setString(1, formattedDate);
					ps.setString(2, tofomattedDate);
					ps.setString(3, branch);
					ps.setString(4, formattedDate);
					ps.setString(5, tofomattedDate);
					ps.setString(6, branch);

					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {
							dto = new GeneralSummaryDTO();
							dto.setStation_code(rs.getString(FROM_STATION));
							dto.setBranch_code(rs.getString(BRANCH_CODE));
							dto.setNo_of_articles(rs.getInt("PD_TP_LRs"));
							dto.setBft(rs.getFloat(BASIC_FRIEGHT));
							dto.setCcc(rs.getFloat(CCC));
							cccSummary.add(dto);
						}
					}

				}
			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();

			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}
		int size = cccSummary.size();
		System.out.println("Out ReportingDAO getCCChargeSummary PD/TP===>"
				+ size);
		if (size > 0) {
			return (GeneralSummaryDTO[]) cccSummary
					.toArray(new GeneralSummaryDTO[size]);
		}
		return null;

	}

	public GeneralSummaryDTO[] getCCCSummaryPDTPCountHistory(Date fromDate,
			Date toDate, String branch, String dbHis) throws Exception {

		Connection con = null;
		PreparedStatement ps = null;		
		ResultSet rs = null;
		GeneralSummaryDTO dto = null;
		ArrayList<GeneralSummaryDTO> cccSummary = new ArrayList<GeneralSummaryDTO>();

		try {
			if (dbHis == null || dbHis.equals("")) {
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			} else {
				DBHelperHist helperHis = new DBHelperHist();
				con = helperHis.getConnectionHistory(dbHis);
			}
			
			if (null != con) {
				ps = con.prepareStatement(GET_CCCHARGE_SUMMARY_PDTP_HISTORY);

				if (ps != null) {

					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(fromDate);
					String tofomattedDate = format.format(toDate);

					ps.setString(1, formattedDate);
					ps.setString(2, tofomattedDate);
					ps.setString(3, branch);

					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {
							dto = new GeneralSummaryDTO();
							dto.setStation_code(rs.getString(FROM_STATION));
							dto.setBranch_code(rs.getString(BRANCH_CODE));
							dto.setNo_of_articles(rs.getInt("PD_TP_LRs"));
							dto.setBft(rs.getFloat(BASIC_FRIEGHT));
							dto.setCcc(rs.getFloat(CCC));
							cccSummary.add(dto);
						}
					}

				}
			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();

			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}
		int size = cccSummary.size();
		System.out.println("Out ReportingDAO getCCChargeSummary PD/TP===>"
				+ size);
		if (size > 0) {
			return (GeneralSummaryDTO[]) cccSummary
					.toArray(new GeneralSummaryDTO[size]);
		}
		return null;

	}

	public GeneralSummaryDTO[] getTotalUnDeliveredTopay(Date fromDate, Date toDate,
			String branch, String DBHistory) throws RemoteException,
			BusinessException, Exception {
		System.out.println("In ReportingDAO getTotalUnDeliveredTopay===>");
		Connection con = null;
		PreparedStatement ps = null;
		DBHelper helper = new DBHelper();
		ResultSet rs = null;
		ArrayList<GeneralSummaryDTO> undeliveredTopay = new ArrayList<GeneralSummaryDTO>();

		GeneralSummaryDTO dto = null;

		try {
			con = helper.getConnection();

			if (null != con) {
				if (branch.equalsIgnoreCase("All")) {
					ps = con.prepareStatement(GET_UNDELIVERED_TOPAY);
				} else {
					ps = con.prepareStatement(GET_UNDELIVERED_TOPAY_BRANCH);
				}

				if (ps != null) {
					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(fromDate);
					String tofomattedDate = format.format(toDate);

					ps.setString(1, formattedDate);
					ps.setString(2, tofomattedDate);

					if (branch.equalsIgnoreCase("All")) {
						ps.setString(1, formattedDate);
						ps.setString(2, tofomattedDate);
						ps.setString(3, formattedDate);
						ps.setString(4, tofomattedDate);
						
					} else {
						ps.setString(1, formattedDate);
						ps.setString(2, tofomattedDate);
						ps.setString(3, branch);
						ps.setString(4, branch);
						ps.setString(5, formattedDate);
						ps.setString(6, tofomattedDate);
						
						
					}
					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {

							dto = new GeneralSummaryDTO();

							dto.setStation_code(rs.getString(STATION_CODE));
							dto.setLr_no(rs.getInt(LR_NO));
							dto.setTotal(rs.getInt(TOTAL));
							dto.setSundry_type(rs.getString(SUNDRY_TYPE));

							undeliveredTopay.add(dto);
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = undeliveredTopay.size();
		System.out.println("Out ReportingDAO getTotalUnDeliveredTopay===>"
				+ size);
		if (size > 0) {
			return (GeneralSummaryDTO[]) undeliveredTopay
					.toArray(new GeneralSummaryDTO[size]);
		}
		return null;

	}

	
	public GeneralSummaryDTO[] getTotalUnDeliveredTopayUnion(Date fromDate, Date toDate,
			String branch, String DBHistory) throws RemoteException,
			BusinessException, Exception {
		System.out.println("In ReportingDAO getTotalUnDeliveredTopay===>");
		Connection con = null;
		PreparedStatement ps = null;
		DBHelper helper = new DBHelper();
		ResultSet rs = null;
		ArrayList<GeneralSummaryDTO> undeliveredTopay = new ArrayList<GeneralSummaryDTO>();

		GeneralSummaryDTO dto = null;

		try {
			con = helper.getConnection();

			if (null != con) {
				if (branch.equalsIgnoreCase("All")) {
					ps = con.prepareStatement(GET_UNDELIVERED_TOPAY_UNION);
				} else {
					ps = con.prepareStatement(GET_UNDELIVERED_TOPAY_BRANCH_UNION);
				}

				if (ps != null) {
					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(fromDate);
					String tofomattedDate = format.format(toDate);

					ps.setString(1, formattedDate);
					ps.setString(2, tofomattedDate);

					if (branch.equalsIgnoreCase("All")) {
						ps.setString(1, formattedDate);
						ps.setString(2, tofomattedDate);
						ps.setString(3, formattedDate);
						ps.setString(4, tofomattedDate);
						ps.setString(5, formattedDate);
						ps.setString(6, tofomattedDate);
						
					} else {
						ps.setString(1, formattedDate);
						ps.setString(2, tofomattedDate);
						ps.setString(3, branch);
						ps.setString(4, branch);
						ps.setString(5, formattedDate);
						ps.setString(6, tofomattedDate);
						ps.setString(7, branch);
						ps.setString(8, formattedDate);
						ps.setString(9, tofomattedDate);
						
						
					}
					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {

							dto = new GeneralSummaryDTO();

							dto.setStation_code(rs.getString(STATION_CODE));
							dto.setLr_no(rs.getInt(LR_NO));
							dto.setTotal(rs.getInt(TOTAL));
							dto.setSundry_type(rs.getString(SUNDRY_TYPE));

							undeliveredTopay.add(dto);
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = undeliveredTopay.size();
		System.out.println("Out ReportingDAO getTotalUnDeliveredTopay===>"
				+ size);
		if (size > 0) {
			return (GeneralSummaryDTO[]) undeliveredTopay
					.toArray(new GeneralSummaryDTO[size]);
		}
		return null;

	}

	
	public GeneralSummaryDTO[] getTotalUnDeliveredTopayHistory(Date fromDate,
			Date toDate, String branch, String dbHis) throws RemoteException,
			BusinessException, Exception {
		System.out.println("In ReportingDAO getTotalUnDeliveredTopay===>");
		Connection con = null;
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		ArrayList<GeneralSummaryDTO> undeliveredTopay = new ArrayList<GeneralSummaryDTO>();

		GeneralSummaryDTO dto = null;

		try {
			if (dbHis == null || dbHis.equals("")) {
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			} else {
				DBHelperHist helperHis = new DBHelperHist();
				con = helperHis.getConnectionHistory(dbHis);
			}

			if (null != con) {
				if (branch.equalsIgnoreCase("All")) {
					ps = con.prepareStatement(GET_UNDELIVERED_TOPAY_HISTORY);
				} else {
					ps = con
							.prepareStatement(GET_UNDELIVERED_TOPAY_BRANCH_HISTORY);
				}

				if (ps != null) {
					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(fromDate);
					String tofomattedDate = format.format(toDate);

					

					if (branch.equalsIgnoreCase("All")) {
						ps.setString(1, formattedDate);
						ps.setString(2, tofomattedDate);
						/*ps.setString(3, formattedDate);
						ps.setString(4, tofomattedDate);*/
						
					} else {
						/*ps.setString(1, formattedDate);
						ps.setString(2, tofomattedDate);
						ps.setString(3, branch);*/
						ps.setString(1, branch);
						ps.setString(2, formattedDate);
						ps.setString(3, tofomattedDate);
						
						
					}
					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {

							dto = new GeneralSummaryDTO();

							dto.setStation_code(rs.getString(STATION_CODE));
							dto.setLr_no(rs.getInt(LR_NO));
							dto.setTotal(rs.getInt(TOTAL));
							dto.setSundry_type(rs.getString(SUNDRY_TYPE));

							undeliveredTopay.add(dto);
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = undeliveredTopay.size();
		System.out.println("Out ReportingDAO getTotalUnDeliveredTopay===>"
				+ size);
		if (size > 0) {
			return (GeneralSummaryDTO[]) undeliveredTopay
					.toArray(new GeneralSummaryDTO[size]);
		}
		return null;

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
			Date fromDate, Date toDate, String DBHistory) throws RemoteException,
			BusinessException, Exception {
		System.out
				.println("In ReportingDAO getTotalUnDeliveredTopayDetailed===>");
		Connection con = null;
		PreparedStatement ps = null;
		DBHelper helper = new DBHelper();
		ResultSet rs = null;
		ArrayList<BookingDTO> undeliveredTopayDetailedList = new ArrayList<BookingDTO>();

		BookingDTO dto = null;

		try {
			con = helper.getConnection();

			if (null != con) {
				ps = con.prepareStatement(GET_UNDELIVERED_TOPAY_DETAILED);

				if (ps != null) {
					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(fromDate);
					String tofomattedDate = format.format(toDate);

					ps.setString(1, branch_code);
					ps.setString(2, formattedDate);
					ps.setString(3, tofomattedDate);
					

					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {

							dto = new BookingDTO();
							dto.setLrno(rs.getString(LR_NO));
							// Status
							dto.setType(getLrStatus(con, dto.getLrno()));
							dto.setDate(rs.getDate(LR_DATE));
							dto.setFrom(rs.getString(FROM_STATION));
							dto.setTo(rs.getString(TO_STATION));
							dto.setBft(rs.getFloat(BASIC_FRIEGHT));
							dto.setNo_of_articles(rs.getInt(NO_OF_ARTICLES));
							dto.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
							dto.setTotal(rs.getFloat(TOTAL));
							dto.setArticle_description(rs.getString(STATION_CODE));
							undeliveredTopayDetailedList.add(dto);
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = undeliveredTopayDetailedList.size();
		System.out
				.println("Out ReportingDAO getTotalUnDeliveredTopayDetailed===>"
						+ size);
		if (size > 0) {
			return (BookingDTO[]) undeliveredTopayDetailedList
					.toArray(new BookingDTO[size]);
		}
		return null;

	}

	
	public BookingDTO[] getTotalUnDeliveredTopayDetailedUnion(String branch_code,
			Date fromDate, Date toDate, String DBHistory) throws RemoteException,
			BusinessException, Exception {
		System.out
				.println("In ReportingDAO getTotalUnDeliveredTopayDetailed===>");
		Connection con = null;
		PreparedStatement ps = null;
		DBHelper helper = new DBHelper();
		ResultSet rs = null;
		ArrayList<BookingDTO> undeliveredTopayDetailedList = new ArrayList<BookingDTO>();

		BookingDTO dto = null;

		try {
			con = helper.getConnection();

			if (null != con) {
				ps = con.prepareStatement(GET_UNDELIVERED_TOPAY_DETAILED_UNION);

				if (ps != null) {
					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(fromDate);
					String tofomattedDate = format.format(toDate);

					ps.setString(1, branch_code);
					ps.setString(2, formattedDate);
					ps.setString(3, tofomattedDate);
					ps.setString(4, branch_code);
					ps.setString(5, formattedDate);
					ps.setString(6, tofomattedDate);
					
					

					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {

							dto = new BookingDTO();
							dto.setLrno(rs.getString(LR_NO));
							// Status
							dto.setType(getLrStatus(con, dto.getLrno()));
							dto.setDate(rs.getDate(LR_DATE));
							dto.setFrom(rs.getString(FROM_STATION));
							dto.setTo(rs.getString(TO_STATION));
							dto.setBft(rs.getFloat(BASIC_FRIEGHT));
							dto.setNo_of_articles(rs.getInt(NO_OF_ARTICLES));
							dto.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
							dto.setTotal(rs.getFloat(TOTAL));
							dto.setArticle_description(rs.getString(STATION_CODE));
							undeliveredTopayDetailedList.add(dto);
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = undeliveredTopayDetailedList.size();
		System.out
				.println("Out ReportingDAO getTotalUnDeliveredTopayDetailed===>"
						+ size);
		if (size > 0) {
			return (BookingDTO[]) undeliveredTopayDetailedList
					.toArray(new BookingDTO[size]);
		}
		return null;

	}

	
	public BookingDTO[] getTotalUnDeliveredTopayDetailedHistory(
			String branch_code, Date fromDate, Date toDate, String dbHis)
			throws RemoteException, BusinessException, Exception {
		System.out
				.println("In ReportingDAO getTotalUnDeliveredTopayDetailed===>");
		Connection con = null;
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		ArrayList<BookingDTO> undeliveredTopayDetailedList = new ArrayList<BookingDTO>();

		BookingDTO dto = null;

		try {
			if (dbHis == null || dbHis.equals("")) {
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			} else {
				DBHelperHist helperHis = new DBHelperHist();
				con = helperHis.getConnectionHistory(dbHis);
			}

			if (null != con) {
				ps = con
						.prepareStatement(GET_UNDELIVERED_TOPAY_DETAILED_HISTORY);

				if (ps != null) {
					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(fromDate);
					String tofomattedDate = format.format(toDate);

					ps.setString(1, branch_code);
					ps.setString(2, formattedDate);
					ps.setString(3, tofomattedDate);
					

					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {

							dto = new BookingDTO();
							dto.setLrno(rs.getString(LR_NO));
							// Status
							dto.setType(getLrStatus(con, dto.getLrno()));

							dto.setDate(rs.getDate(LR_DATE));
							dto.setFrom(rs.getString(FROM_STATION));
							dto.setTo(rs.getString(TO_STATION));
							dto.setBft(rs.getFloat(BASIC_FRIEGHT));
							dto.setNo_of_articles(rs.getInt(NO_OF_ARTICLES));
							dto.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
							dto.setTotal(rs.getFloat(TOTAL));
							dto.setArticle_description(rs.getString(STATION_CODE));
							undeliveredTopayDetailedList.add(dto);
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = undeliveredTopayDetailedList.size();
		System.out
				.println("Out ReportingDAO getTotalUnDeliveredTopayDetailed===>"
						+ size);
		if (size > 0) {
			return (BookingDTO[]) undeliveredTopayDetailedList
					.toArray(new BookingDTO[size]);
		}
		return null;

	}

	/**
	 * 
	 * @param con
	 * @param lrno
	 * @return
	 * @throws Exception
	 */
	private String getLrStatus(Connection con, String lrno) throws Exception {
		String status = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(GET_LR_STATUS);
			if (ps != null) {
				ps.setString(1, lrno);

				rs = ps.executeQuery();

				if (rs != null) {
					if (rs.next()) {
						if (rs.getString(LR_STATUS).equalsIgnoreCase("Arrived")) {
							status = "Received at "
									+ rs.getString(STATION_CODE);
						} else {
							status = "Moved to " + rs.getString(STATION_CODE);
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != ps)
					ps.close();
				if (null != ps)
					ps.close();
			} catch (Exception exception) {
				if (null != ps)
					ps.close();
				if (null != rs)
					rs.close();
			}
		}

		return status;
	}

	public GeneralSummaryDTO[] getDailyBookingAnalysis(Date fromDate,
			Date toDate, String DBHistory) throws Exception {
		System.out.println("In ReportingDAO getDailyBookingAnalysis===>");
		Connection con = null;
		PreparedStatement ps = null;
		DBHelper helper = new DBHelper();
		ResultSet rs = null;

		GeneralSummaryDTO bookedLr = null;
		ArrayList<GeneralSummaryDTO> list = new ArrayList<GeneralSummaryDTO>();

		try {
			con = helper.getConnection();
			if (null != con) {
				ps = con.prepareStatement(GET_DAILY_BOOKING_ANALYSIS);

				if (ps != null) {

					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(fromDate);
					String tofomattedDate = format.format(toDate);

					ps.setString(1, formattedDate);
					ps.setString(2, tofomattedDate);

					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {
							bookedLr = new GeneralSummaryDTO();

							bookedLr
									.setStation_code(rs.getString(FROM_STATION));
							bookedLr.setLr_no(rs.getInt(COUNT));
							bookedLr.setLrDate(rs.getDate(LR_DATE));
							bookedLr.setTotal(rs.getFloat(TOTAL));
							bookedLr.setBft(rs.getFloat(BASIC_FRIEGHT));
							bookedLr.setNo_of_articles(rs
									.getInt(NO_OF_ARTICLES));
							bookedLr.setActual_weight(rs
									.getFloat(ACTUAL_WEIGHT));
							bookedLr.setCharged_weight(rs
									.getFloat(CHARGED_WEIGHT));

							list.add(bookedLr);
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = list.size();
		System.out.println("Out ReportingDAO getDailyBookingAnalysis===>"
				+ size);
		if (size > 0) {
			return (GeneralSummaryDTO[]) list
					.toArray(new GeneralSummaryDTO[size]);
		}

		return null;
	}

	
	public GeneralSummaryDTO[] getDailyBookingAnalysisUnion(Date fromDate,
			Date toDate, String DBHistory) throws Exception {
		System.out.println("In ReportingDAO getDailyBookingAnalysis===>");
		Connection con = null;
		PreparedStatement ps = null;
		DBHelper helper = new DBHelper();
		ResultSet rs = null;

		GeneralSummaryDTO bookedLr = null;
		ArrayList<GeneralSummaryDTO> list = new ArrayList<GeneralSummaryDTO>();

		try {
			con = helper.getConnection();
			if (null != con) {
				ps = con.prepareStatement(GET_DAILY_BOOKING_ANALYSIS_UNION);

				if (ps != null) {

					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(fromDate);
					String tofomattedDate = format.format(toDate);

					ps.setString(1, formattedDate);
					ps.setString(2, tofomattedDate);
					ps.setString(3, formattedDate);
					ps.setString(4, tofomattedDate);

					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {
							bookedLr = new GeneralSummaryDTO();

							bookedLr
									.setStation_code(rs.getString(FROM_STATION));
							bookedLr.setLr_no(rs.getInt(COUNT));
							bookedLr.setLrDate(rs.getDate(LR_DATE));
							bookedLr.setTotal(rs.getFloat(TOTAL));
							bookedLr.setBft(rs.getFloat(BASIC_FRIEGHT));
							bookedLr.setNo_of_articles(rs
									.getInt(NO_OF_ARTICLES));
							bookedLr.setActual_weight(rs
									.getFloat(ACTUAL_WEIGHT));
							bookedLr.setCharged_weight(rs
									.getFloat(CHARGED_WEIGHT));

							list.add(bookedLr);
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = list.size();
		System.out.println("Out ReportingDAO getDailyBookingAnalysis===>"
				+ size);
		if (size > 0) {
			return (GeneralSummaryDTO[]) list
					.toArray(new GeneralSummaryDTO[size]);
		}

		return null;
	}

	
	public GeneralSummaryDTO[] getDailyBookingAnalysisHistory(Date fromDate,
			Date toDate, String dbHis) throws Exception {
		System.out.println("In ReportingDAO getDailyBookingAnalysis===>");
		Connection con = null;
		PreparedStatement ps = null;
		
		ResultSet rs = null;

		GeneralSummaryDTO bookedLr = null;
		ArrayList<GeneralSummaryDTO> list = new ArrayList<GeneralSummaryDTO>();

		try {
			if (dbHis == null || dbHis.equals("")) {
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			} else {
				DBHelperHist helperHis = new DBHelperHist();
				con = helperHis.getConnectionHistory(dbHis);
			}
			
			if (null != con) {
				ps = con.prepareStatement(GET_DAILY_BOOKING_ANALYSIS_HISTORY);

				if (ps != null) {

					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(fromDate);
					String tofomattedDate = format.format(toDate);

					ps.setString(1, formattedDate);
					ps.setString(2, tofomattedDate);

					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {
							bookedLr = new GeneralSummaryDTO();

							bookedLr
									.setStation_code(rs.getString(FROM_STATION));
							bookedLr.setLr_no(rs.getInt(COUNT));
							bookedLr.setLrDate(rs.getDate(LR_DATE));
							bookedLr.setTotal(rs.getFloat(TOTAL));
							bookedLr.setBft(rs.getFloat(BASIC_FRIEGHT));
							bookedLr.setNo_of_articles(rs
									.getInt(NO_OF_ARTICLES));
							bookedLr.setActual_weight(rs
									.getFloat(ACTUAL_WEIGHT));
							bookedLr.setCharged_weight(rs
									.getFloat(CHARGED_WEIGHT));

							list.add(bookedLr);
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = list.size();
		System.out.println("Out ReportingDAO getDailyBookingAnalysis===>"
				+ size);
		if (size > 0) {
			return (GeneralSummaryDTO[]) list
					.toArray(new GeneralSummaryDTO[size]);
		}

		return null;
	}

	/**
	 * 
	 */
	public StationsDTO[] getCounterConsolidated(Date from, Date to,
			String branch, StationsDTO[] stations, String DBHistory)
			throws Exception {
		System.out.println("In ReportingDAO getCounterConsolidated===>");
		Connection con = null;
		PreparedStatement ps = null;
		DBHelper helper = new DBHelper();
		ResultSet rs = null;

		String GET_COUNTER_REPORT = "";

		String GET_COUNTER_SUNDRY = "";

		String GET_COUNTER_CANCEL = "";

		StationsDTO dto = null;
		String fromDate = "";
		String toDate = "";
		boolean isC = false;
		int stationsLen = stations.length;
		StationsDTO[] stationDto = null;
		try {
			con = helper.getConnection();

			if (null != con) {
				SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
				fromDate = format.format(from);
				toDate = format.format(to);

				if (branch.startsWith("All")) {
					// ALL BRANCH
					if (branch.charAt(3) == 'C') {
						isC = true;
						GET_COUNTER_REPORT = "Select count(*) as count,b.branch_code as from_station, lr_type "
								+ " from lr a, station b where a.from_station = "
								+ "b.station_code and b.branch_code in (select s.station_code from station s "
								+ "where s.station_code=s.branch_code) and date(lr_date) between '"
								+ fromDate
								+ "' "
								+ "and '"
								+ toDate
								+ "' group by lr_type, b.branch_code";

						GET_COUNTER_SUNDRY = "Select count(*) as count,b.branch_code as from_station, rate_type "
								+ "from lr a, station b where a.from_station = "
								+ "b.station_code and b.branch_code in (select s.station_code from station s "
								+ "where s.station_code=s.branch_code) and date(lr_date) between '"
								+ fromDate
								+ "' "
								+ "and '"
								+ toDate
								+ "' group by rate_type,b.branch_code";

						GET_COUNTER_CANCEL = "Select count(*) as count,b.branch_code as from_station from lr a, station b "
								+ "where a.lr_status = 1 and a.from_station = b.station_code and b.branch_code in "
								+ "(select s.station_code from station s where s.station_code=s.branch_code) and "
								+ "date(lr_date) between '"
								+ fromDate
								+ "' and '"
								+ toDate
								+ "' group by b.branch_code";
					} else {
						// ALL Stations
						GET_COUNTER_REPORT = "Select count(*) as count,from_station, lr_type "
								+ "from lr a, station b where from_station = b.station_code and "
								+ "b.branch_code in (select s.station_code from station s where "
								+ "s.station_code=s.branch_code) and date(lr_date) between '"
								+ fromDate
								+ "' "
								+ "and '"
								+ toDate
								+ "' group by lr_type,from_station;";

						GET_COUNTER_SUNDRY = "Select count(*) as count, from_station, rate_type "
								+ "from lr a, station b where  from_station = b.station_code and "
								+ "b.branch_code in (select s.station_code from station s where "
								+ "s.station_code=s.branch_code) and date(lr_date) between '"
								+ fromDate
								+ "' and '"
								+ toDate
								+ "' "
								+ " group by rate_type, from_station";

						GET_COUNTER_CANCEL = "Select count(*) as count,from_station from lr a, station b "
								+ "where a.lr_status = 1 and from_station = b.station_code and b.branch_code in "
								+ "(select s.station_code from station s where s.station_code=s.branch_code) and "
								+ "date(lr_date) between '"
								+ fromDate
								+ "' and '"
								+ toDate
								+ "' group by from_station";
					}

				} else {
					// selected branch stations

					GET_COUNTER_REPORT = "Select count(*) as count,from_station, lr_type "
							+ "from lr a, station b where b.branch_code = '"
							+ branch
							+ "' and "
							+ "a.from_station = b.station_code and date(lr_date) between '"
							+ fromDate
							+ "' and "
							+ "'"
							+ toDate
							+ "' group by lr_type,from_station ";

					GET_COUNTER_SUNDRY = "Select count(*) as count, from_station, rate_type "
							+ "from lr a, station b where b.branch_code = '"
							+ branch
							+ "' and a.from_station = "
							+ "b.station_code and date(lr_date) between '"
							+ fromDate
							+ "' and '"
							+ toDate
							+ "' "
							+ " group by rate_type, from_station";

					GET_COUNTER_CANCEL = "Select count(*) as count,from_station from lr a, station b "
							+ "where a.lr_status = 1 and b.branch_code = '"
							+ branch
							+ "' and a.from_station = "
							+ "b.station_code and date(lr_date) between '"
							+ fromDate
							+ "' and '"
							+ toDate
							+ "' "
							+ "group by from_station";

				}

				ps = con.prepareStatement(GET_COUNTER_REPORT);

				if (ps != null) {
					rs = ps.executeQuery();
					String stnCode = "";
					if (null != rs) {
						while (rs.next()) {

							for (int i = 0; i < stationsLen; i++) {
								dto = stations[i];

								if (!isC) {
									stnCode = stations[i].getId();
								} else {
									stnCode = stations[i].getBranch_code();
								}
								if (rs.getString(FROM_STATION)
										.equalsIgnoreCase(stnCode)) {

									if (rs.getString(LR_TYPE).equalsIgnoreCase(
											"Topay")) {
										dto.setCc_limit(rs.getInt(COUNT));
									} else if (rs.getString(LR_TYPE)
											.equalsIgnoreCase("Paid")) {
										dto.setCc_consider(rs.getInt(COUNT));
									} else if (rs.getString(LR_TYPE)
											.equalsIgnoreCase("Billing")) {
										dto.setCc_refund(rs.getInt(COUNT));
									}

								}
							}

						}
					}
				}

				StationsDTO[] stn = getCounterSundryLRS(con, stations,
						GET_COUNTER_SUNDRY, isC);
				stationDto = getCounterCancelLRS(con, stn, GET_COUNTER_CANCEL,
						isC);

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		return stationDto;

	}
	
	
	public StationsDTO[] getCounterConsolidatedUnion(Date from, Date to,
			String branch, StationsDTO[] stations, String DBHistory)
			throws Exception {
		System.out.println("In ReportingDAO getCounterConsolidated===>");
		Connection con = null;
		PreparedStatement ps = null;
		DBHelper helper = new DBHelper();
		ResultSet rs = null;

		String GET_COUNTER_REPORT = "";

		String GET_COUNTER_SUNDRY = "";

		String GET_COUNTER_CANCEL = "";

		StationsDTO dto = null;
		String fromDate = "";
		String toDate = "";
		boolean isC = false;
		int stationsLen = stations.length;
		StationsDTO[] stationDto = null;
		try {
			con = helper.getConnection();

			if (null != con) {
				SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
				fromDate = format.format(from);
				toDate = format.format(to);

				if (branch.startsWith("All")) {
					// ALL BRANCH
					if (branch.charAt(3) == 'C') {
						isC = true;
						GET_COUNTER_REPORT = "Select count(*) as count,b.branch_code as from_station, lr_type "
								+ " from lr a, station b where a.from_station = "
								+ "b.station_code and b.branch_code in (select s.station_code from station s "
								+ "where s.station_code=s.branch_code) and date(lr_date) between '"
								+ fromDate
								+ "' "
								+ "and '"
								+ toDate
								+ "' group by lr_type, b.branch_code" +
								" union "+
								" Select count(*) as count,b.branch_code as from_station, lr_type "
								+ " from lr_history a, station b where a.from_station = "
								+ "b.station_code and b.branch_code in (select s.station_code from station s "
								+ "where s.station_code=s.branch_code) and date(lr_date) between '"
								+ fromDate
								+ "' "
								+ "and '"
								+ toDate
								+ "' group by lr_type, b.branch_code";

						GET_COUNTER_SUNDRY = "Select count(*) as count,b.branch_code as from_station, rate_type "
								+ "from lr a, station b where a.from_station = "
								+ "b.station_code and b.branch_code in (select s.station_code from station s "
								+ "where s.station_code=s.branch_code) and date(lr_date) between '"
								+ fromDate
								+ "' "
								+ "and '"
								+ toDate
								+ "' group by rate_type,b.branch_code "
								+" union " +
								"Select count(*) as count,b.branch_code as from_station, rate_type "
								+ "from lr_history a, station b where a.from_station = "
								+ "b.station_code and b.branch_code in (select s.station_code from station s "
								+ "where s.station_code=s.branch_code) and date(lr_date) between '"
								+ fromDate
								+ "' "
								+ "and '"
								+ toDate
								+ "' group by rate_type,b.branch_code";

								

						GET_COUNTER_CANCEL = "Select count(*) as count,b.branch_code as from_station from lr a, station b "
								+ "where a.lr_status = 1 and a.from_station = b.station_code and b.branch_code in "
								+ "(select s.station_code from station s where s.station_code=s.branch_code) and "
								+ "date(lr_date) between '"
								+ fromDate
								+ "' and '"
								+ toDate
								+ "' group by b.branch_code" +
								" union " +
								"Select count(*) as count,b.branch_code as from_station from lr_history a, station b "
								+ "where a.lr_status = 1 and a.from_station = b.station_code and b.branch_code in "
								+ "(select s.station_code from station s where s.station_code=s.branch_code) and "
								+ "date(lr_date) between '"
								+ fromDate
								+ "' and '"
								+ toDate
								+ "' group by b.branch_code";
								
					} else {
						// ALL Stations
						GET_COUNTER_REPORT = "Select count(*) as count,from_station, lr_type "
								+ "from lr a, station b where from_station = b.station_code and "
								+ "b.branch_code in (select s.station_code from station s where "
								+ "s.station_code=s.branch_code) and date(lr_date) between '"
								+ fromDate
								+ "' "
								+ "and '"
								+ toDate
								+ "' group by lr_type,from_station"+
								" union " +
								"Select count(*) as count,from_station, lr_type "
								+ "from lr_history a, station b where from_station = b.station_code and "
								+ "b.branch_code in (select s.station_code from station s where "
								+ "s.station_code=s.branch_code) and date(lr_date) between '"
								+ fromDate
								+ "' "
								+ "and '"
								+ toDate
								+ "' group by lr_type,from_station";

						GET_COUNTER_SUNDRY = "Select count(*) as count, from_station, rate_type "
								+ "from lr a, station b where  from_station = b.station_code and "
								+ "b.branch_code in (select s.station_code from station s where "
								+ "s.station_code=s.branch_code) and date(lr_date) between '"
								+ fromDate
								+ "' and '"
								+ toDate
								+ "' "
								+ " group by rate_type, from_station"+
								" union " +
								"Select count(*) as count, from_station, rate_type "
								+ "from lr_history a, station b where  from_station = b.station_code and "
								+ "b.branch_code in (select s.station_code from station s where "
								+ "s.station_code=s.branch_code) and date(lr_date) between '"
								+ fromDate
								+ "' and '"
								+ toDate
								+ "' "
								+ " group by rate_type, from_station";

						GET_COUNTER_CANCEL = "Select count(*) as count,from_station from lr a, station b "
								+ "where a.lr_status = 1 and from_station = b.station_code and b.branch_code in "
								+ "(select s.station_code from station s where s.station_code=s.branch_code) and "
								+ "date(lr_date) between '"
								+ fromDate
								+ "' and '"
								+ toDate
								+ "' group by from_station "+
								" union " +
								"Select count(*) as count,from_station from lr_history a, station b "
								+ "where a.lr_status = 1 and from_station = b.station_code and b.branch_code in "
								+ "(select s.station_code from station s where s.station_code=s.branch_code) and "
								+ "date(lr_date) between '"
								+ fromDate
								+ "' and '"
								+ toDate
								+ "' group by from_station";
					}

				} else {
					// selected branch stations

					GET_COUNTER_REPORT = "Select count(*) as count,from_station, lr_type "
							+ "from lr a, station b where b.branch_code = '"
							+ branch
							+ "' and "
							+ "a.from_station = b.station_code and date(lr_date) between '"
							+ fromDate
							+ "' and "
							+ "'"
							+ toDate
							+ "' group by lr_type,from_station "+
							" union " +
							"Select count(*) as count,from_station, lr_type "
							+ "from lr_history a, station b where b.branch_code = '"
							+ branch
							+ "' and "
							+ "a.from_station = b.station_code and date(lr_date) between '"
							+ fromDate
							+ "' and "
							+ "'"
							+ toDate
							+ "' group by lr_type,from_station ";


					GET_COUNTER_SUNDRY = "Select count(*) as count, from_station, rate_type "
							+ "from lr a, station b where b.branch_code = '"
							+ branch
							+ "' and a.from_station = "
							+ "b.station_code and date(lr_date) between '"
							+ fromDate
							+ "' and '"
							+ toDate
							+ "' "
							+ " group by rate_type, from_station"+
							" union " +
							"Select count(*) as count, from_station, rate_type "
							+ "from lr_history a, station b where b.branch_code = '"
							+ branch
							+ "' and a.from_station = "
							+ "b.station_code and date(lr_date) between '"
							+ fromDate
							+ "' and '"
							+ toDate
							+ "' "
							+ " group by rate_type, from_station";


					GET_COUNTER_CANCEL = "Select count(*) as count,from_station from lr a, station b "
							+ "where a.lr_status = 1 and b.branch_code = '"
							+ branch
							+ "' and a.from_station = "
							+ "b.station_code and date(lr_date) between '"
							+ fromDate
							+ "' and '"
							+ toDate
							+ "' "
							+ "group by from_station"+
							" union " +
							"Select count(*) as count,from_station from lr_history a, station b "
							+ "where a.lr_status = 1 and b.branch_code = '"
							+ branch
							+ "' and a.from_station = "
							+ "b.station_code and date(lr_date) between '"
							+ fromDate
							+ "' and '"
							+ toDate
							+ "' "
							+ "group by from_station";


				}

				ps = con.prepareStatement(GET_COUNTER_REPORT);

				if (ps != null) {
					rs = ps.executeQuery();
					String stnCode = "";
					if (null != rs) {
						while (rs.next()) {

							for (int i = 0; i < stationsLen; i++) {
								dto = stations[i];

								if (!isC) {
									stnCode = stations[i].getId();
								} else {
									stnCode = stations[i].getBranch_code();
								}
								if (rs.getString(FROM_STATION)
										.equalsIgnoreCase(stnCode)) {

									if (rs.getString(LR_TYPE).equalsIgnoreCase(
											"Topay")) {
										dto.setCc_limit(rs.getInt(COUNT));
									} else if (rs.getString(LR_TYPE)
											.equalsIgnoreCase("Paid")) {
										dto.setCc_consider(rs.getInt(COUNT));
									} else if (rs.getString(LR_TYPE)
											.equalsIgnoreCase("Billing")) {
										dto.setCc_refund(rs.getInt(COUNT));
									}

								}
							}

						}
					}
				}

				StationsDTO[] stn = getCounterSundryLRS(con, stations,
						GET_COUNTER_SUNDRY, isC);
				stationDto = getCounterCancelLRS(con, stn, GET_COUNTER_CANCEL,
						isC);

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		return stationDto;

	}


	public StationsDTO[] getCounterConsolidatedHistory(Date from, Date to,
			String branch, StationsDTO[] stations, String dbHis)
			throws Exception {
		System.out.println("In ReportingDAO getCounterConsolidated===>");
		Connection con = null;
		PreparedStatement ps = null;
		
		ResultSet rs = null;

		String GET_COUNTER_REPORT = "";

		String GET_COUNTER_SUNDRY = "";

		String GET_COUNTER_CANCEL = "";

		StationsDTO dto = null;
		String fromDate = "";
		String toDate = "";
		boolean isC = false;
		int stationsLen = stations.length;
		StationsDTO[] stationDto = null;
		try {
			if (dbHis == null || dbHis.equals("")) {
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			} else {
				DBHelperHist helperHis = new DBHelperHist();
				con = helperHis.getConnectionHistory(dbHis);
			}

			if (null != con) {
				SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
				fromDate = format.format(from);
				toDate = format.format(to);

				if (branch.startsWith("All")) {
					// ALL BRANCH
					if (branch.charAt(3) == 'C') {
						isC = true;
						GET_COUNTER_REPORT = "Select count(*) as count,b.branch_code as from_station, lr_type "
								+ " from lr_history a, station b where a.from_station = "
								+ "b.station_code and b.branch_code in (select s.station_code from station s "
								+ "where s.station_code=s.branch_code) and date(lr_date) between '"
								+ fromDate
								+ "' "
								+ "and '"
								+ toDate
								+ "' group by lr_type, b.branch_code";

						GET_COUNTER_SUNDRY = "Select count(*) as count,b.branch_code as from_station, rate_type "
								+ "from lr_history a, station b where a.from_station = "
								+ "b.station_code and b.branch_code in (select s.station_code from station s "
								+ "where s.station_code=s.branch_code) and date(lr_date) between '"
								+ fromDate
								+ "' "
								+ "and '"
								+ toDate
								+ "' group by rate_type,b.branch_code";

						GET_COUNTER_CANCEL = "Select count(*) as count,b.branch_code as from_station from lr_history a, station b "
								+ "where a.lr_status = 1 and a.from_station = b.station_code and b.branch_code in "
								+ "(select s.station_code from station s where s.station_code=s.branch_code) and "
								+ "date(lr_date) between '"
								+ fromDate
								+ "' and '"
								+ toDate
								+ "' group by b.branch_code";
					} else {
						// ALL Stations
						GET_COUNTER_REPORT = "Select count(*) as count,from_station, lr_type "
								+ "from lr_history a, station b where from_station = b.station_code and "
								+ "b.branch_code in (select s.station_code from station s where "
								+ "s.station_code=s.branch_code) and date(lr_date) between '"
								+ fromDate
								+ "' "
								+ "and '"
								+ toDate
								+ "' group by lr_type,from_station;";

						GET_COUNTER_SUNDRY = "Select count(*) as count, from_station, rate_type "
								+ "from lr_history a, station b where  from_station = b.station_code and "
								+ "b.branch_code in (select s.station_code from station s where "
								+ "s.station_code=s.branch_code) and date(lr_date) between '"
								+ fromDate
								+ "' and '"
								+ toDate
								+ "' "
								+ " group by rate_type, from_station";

						GET_COUNTER_CANCEL = "Select count(*) as count,from_station from lr_history a, station b "
								+ "where a.lr_status = 1 and from_station = b.station_code and b.branch_code in "
								+ "(select s.station_code from station s where s.station_code=s.branch_code) and "
								+ "date(lr_date) between '"
								+ fromDate
								+ "' and '"
								+ toDate
								+ "' group by from_station";
					}

				} else {
					// selected branch stations

					GET_COUNTER_REPORT = "Select count(*) as count,from_station, lr_type "
							+ "from lr_history a, station b where b.branch_code = '"
							+ branch
							+ "' and "
							+ "a.from_station = b.station_code and date(lr_date) between '"
							+ fromDate
							+ "' and "
							+ "'"
							+ toDate
							+ "' group by lr_type,from_station ";

					GET_COUNTER_SUNDRY = "Select count(*) as count, from_station, rate_type "
							+ "from lr_history a, station b where b.branch_code = '"
							+ branch
							+ "' and a.from_station = "
							+ "b.station_code and date(lr_date) between '"
							+ fromDate
							+ "' and '"
							+ toDate
							+ "' "
							+ " group by rate_type, from_station";

					GET_COUNTER_CANCEL = "Select count(*) as count,from_station from lr_history a, station b "
							+ "where a.lr_status = 1 and b.branch_code = '"
							+ branch
							+ "' and a.from_station = "
							+ "b.station_code and date(lr_date) between '"
							+ fromDate
							+ "' and '"
							+ toDate
							+ "' "
							+ "group by from_station";

				}

				ps = con.prepareStatement(GET_COUNTER_REPORT);

				if (ps != null) {
					rs = ps.executeQuery();
					String stnCode = "";
					if (null != rs) {
						while (rs.next()) {

							for (int i = 0; i < stationsLen; i++) {
								dto = stations[i];

								if (!isC) {
									stnCode = stations[i].getId();
								} else {
									stnCode = stations[i].getBranch_code();
								}
								if (rs.getString(FROM_STATION)
										.equalsIgnoreCase(stnCode)) {

									if (rs.getString(LR_TYPE).equalsIgnoreCase(
											"Topay")) {
										dto.setCc_limit(rs.getInt(COUNT));
									} else if (rs.getString(LR_TYPE)
											.equalsIgnoreCase("Paid")) {
										dto.setCc_consider(rs.getInt(COUNT));
									} else if (rs.getString(LR_TYPE)
											.equalsIgnoreCase("Billing")) {
										dto.setCc_refund(rs.getInt(COUNT));
									}

								}
							}

						}
					}
				}

				StationsDTO[] stn = getCounterSundryLRS(con, stations,
						GET_COUNTER_SUNDRY, isC);
				stationDto = getCounterCancelLRS(con, stn, GET_COUNTER_CANCEL,
						isC);

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		return stationDto;

	}

	private StationsDTO[] getCounterCancelLRS(Connection con,
			StationsDTO[] stations, String get_counter_cancel, boolean isC)
			throws SQLException {

		PreparedStatement ps = null;
		ResultSet rs = null;
		StationsDTO dto = null;
		if (con != null) {
			ps = con.prepareStatement(get_counter_cancel);

			if (ps != null) {
				rs = ps.executeQuery();
				String stnCode = "";
				if (null != rs) {
					while (rs.next()) {

						for (int i = 0; i < stations.length; i++) {

							if (!isC) {
								stnCode = stations[i].getId();
							} else {
								stnCode = stations[i].getBranch_code();
							}
							if (rs.getString(FROM_STATION).equalsIgnoreCase(
									stnCode)) {
								dto = stations[i];

								dto.setDiscount(rs.getInt(COUNT));

							}
						}

					}
				}
			}
		}

		return stations;

	}

	private StationsDTO[] getCounterSundryLRS(Connection con,
			StationsDTO[] stations, String get_counter_sundry, boolean isC)
			throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		StationsDTO dto = null;
		if (con != null) {
			ps = con.prepareStatement(get_counter_sundry);

			if (ps != null) {
				rs = ps.executeQuery();
				String stnCode = "";
				int type = -1;
				if (null != rs) {
					while (rs.next()) {

						for (int i = 0; i < stations.length; i++) {
							dto = stations[i];
							if (!isC) {
								stnCode = stations[i].getId();
							} else {
								stnCode = stations[i].getBranch_code();
							}
							if (rs.getString(FROM_STATION).equalsIgnoreCase(
									stnCode)) {
								type = rs.getInt(RATE_TYPE);
								if (type == 4) {
									dto.setCc_commodity(dto.getCc_commodity()
											+ rs.getInt(COUNT));
								} else if (type == 6) {
									dto.setBf_increment(dto.getBf_increment()
											+ rs.getInt(COUNT));
								} else if(((type >= 7) &&( type <= 9)) || (type <= 3)) {
									dto.setCc_special(dto.getCc_special()
											+ rs.getInt(COUNT));
								}

							}
						}

					}
				}
			}
		}

		return stations;
	}
	
	
	
	
	
	public StationsDTO[] getCounterConsolidatedMisc(Date from, Date to,
			String branch, StationsDTO[] stations, String DBHistory)
			throws Exception {
		System.out.println("In ReportingDAO getCounterConsolidated===>");
		Connection con = null;
		PreparedStatement ps = null;
		DBHelper helper = new DBHelper();
		ResultSet rs = null;

		String GET_COUNTER_REPORT_MISC = "";

		String GET_COUNTER_INWARDLR = "";

		String GET_COUNTER_TOTALCR = "";

		StationsDTO dto = null;
		String fromDate = "";
		String toDate = "";
		boolean isC = false;
		int stationsLen = stations.length;
		StationsDTO[] stationDto = null;
		try {
			con = helper.getConnection();

			if (null != con) {
				SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
				fromDate = format.format(from);
				toDate = format.format(to);

				if (branch.startsWith("All")) {
					// ALL BRANCH
					if (branch.charAt(3) == 'C') {
						isC = true;
						GET_COUNTER_REPORT_MISC = " select b.branch_code as from_station,sum(a.no_of_articles) as no_of_articles,sum(a.actual_weight) as actual_weight, " +
								" sum(a.charged_weight) as charged_weight,count(a.ddc) as ddextra,(a.ddc) as ddc from lr a,station b  where a.from_station = b.station_code " +
								" and  b.branch_code like '%' and b.station_code like '%' " +
								" and lr_status != 1 and date(lr_date) between '" +
								fromDate +
								"' and '" +
								toDate 
								+"'  group by ddc = 0, b.branch_code ";


						GET_COUNTER_INWARDLR =  " select count(b.lr_no) as count,c.branch_code as from_station from lr_tracking a,lr b,station c where  b.lr_status != 1  and a.lr_status != 'ToArrive'  and  " +
								" a.station_code = c.station_code and c.branch_code like '%'" +
								" and  c.station_code like '%' and a.station_code != b.from_station " +
								" and a.lr_no = b.lr_no and date(a.inward_time) between '" +
								fromDate
								+ " ' and '" +
								toDate
								+ "' group by c.branch_code " +
								" union " +
								" select count(b.lr_no) as count,c.branch_code as from_station from lr_tracking_archive a,lr b,station c where b.lr_status != 1 and " +
								" a.station_code = c.station_code and c.branch_code like '%' and  c.station_code like '%' and a.station_code != b.from_station " +
								" and a.lr_status = 'Delivered' and a.lr_no = b.lr_no and date(a.inward_time) between '"+
								fromDate
								+"' and '"+
								toDate 
								+"' group by c.branch_code ";

						GET_COUNTER_TOTALCR = " select count(a.cr_no) as cr_no,(b.branch_code) as from_station  from cash_receipt a,station b where  a.status != 1 and " +
								" a.station_code = b.station_code and b.branch_code like '%' and  b.station_code like '%' " +
								" and date(a.date) between '"+ 
								fromDate +
								"' and '" +
								toDate + "' group by b.branch_code ";
					} else {
						// ALL Stations
						GET_COUNTER_REPORT_MISC = " select a.from_station as from_station,sum(a.no_of_articles) as no_of_articles,sum(a.actual_weight) as actual_weight, " +
								" sum(a.charged_weight) as charged_weight,count(a.ddc) as ddextra,(a.ddc) as ddc from lr a,station b  where a.from_station = b.station_code " +
								" and  b.branch_code like '%' and b.station_code like '%' " +
								" and lr_status != 1 and date(lr_date) between '" +
								fromDate +
								"' and '" +
								toDate +"' group by ddc = 0, a.from_station ";

						GET_COUNTER_INWARDLR =  " select count(b.lr_no) as count,(a.station_code) as from_station from lr_tracking a,lr b,station c where  b.lr_status != 1  and a.lr_status != 'ToArrive'  and  " +
								" a.station_code = c.station_code and c.branch_code like '%'" +
								" and  c.station_code like '%' and a.station_code != b.from_station " +
								" and a.lr_no = b.lr_no and date(a.inward_time) between '" +
								fromDate
								+ " ' and '" +
								toDate
								+ "' group by a.station_code " +
								" union " +
								" select count(b.lr_no) as count,(a.station_code) as from_station from lr_tracking_archive a,lr b,station c where b.lr_status != 1 and " +
								" a.station_code = c.station_code and c.branch_code like '%' and  c.station_code like '%' and a.station_code != b.from_station " +
								" and a.lr_status = 'Delivered' and a.lr_no = b.lr_no and date(a.inward_time) between '"+
								fromDate
								+"' and '"+
								toDate 
								+"' group by a.station_code ";


						GET_COUNTER_TOTALCR =  " select count(a.cr_no) as cr_no,(a.station_code) as from_station  from cash_receipt a,station b where  a.status != 1 and " +
								" a.station_code = b.station_code and b.branch_code like '%' and  b.station_code like '%' " +
								" and date(a.date) between '"+ 
								fromDate +
								"' and '" +
								toDate + "' group by a.station_code ";
					}

				} else {
					// selected branch stations

					GET_COUNTER_REPORT_MISC =  " select a.from_station as from_station,sum(a.no_of_articles) as no_of_articles,sum(a.actual_weight) as actual_weight, " +
								" sum(a.charged_weight) as charged_weight,count(a.ddc) as ddextra,(a.ddc) as ddc from lr a,station b  where a.from_station = b.station_code " +
								" and  b.branch_code like '" +
								branch 
								+ "' and b.station_code like '%' " +
								" and lr_status != 1 and date(lr_date) between '" +
								fromDate +
								"' and '" +
								toDate +"' group by ddc = 0, a.from_station ";
						
					GET_COUNTER_INWARDLR = " select count(b.lr_no) as count,(a.station_code) as from_station from lr_tracking a,lr b,station c where  b.lr_status != 1 and a.lr_status != 'ToArrive' and  " +
								" a.station_code = c.station_code and c.branch_code like '"+
								branch 
								+"' and  c.station_code like '%' and a.station_code != b.from_station " +
								" and a.lr_no = b.lr_no and date(a.inward_time) between '" +
								fromDate
								+ " ' and '" +
								toDate
								+ "' group by a.station_code " +
								" union  " +
								" select count(b.lr_no) as count,(a.station_code) as from_station from lr_tracking_archive a,lr b,station c where b.lr_status != 1 and " +
								" a.station_code = c.station_code and c.branch_code like '"+
								branch
								+"' and  c.station_code like '%' and a.station_code != b.from_station " +
								" and a.lr_status = 'Delivered' and a.lr_no = b.lr_no and date(a.inward_time) between '"+
								fromDate
								+"' and '"+
								toDate 
								+"' group by a.station_code ";


					GET_COUNTER_TOTALCR = " select count(a.cr_no) as cr_no,(a.station_code) as from_station  from cash_receipt a,station b where  a.status != 1 and " +
								" a.station_code = b.station_code and b.branch_code like '"+ 
								branch +"' and  b.station_code like '%' " +
								" and date(a.date) between '"+ 
								fromDate +
								"' and '" +
								toDate + "' group by a.station_code ";

				}

				ps = con.prepareStatement(GET_COUNTER_REPORT_MISC);

				if (ps != null) {
					rs = ps.executeQuery();
					String stnCode = "";
					if (null != rs) {
						while (rs.next()) {

							for (int i = 0; i < stationsLen; i++) {
								dto = stations[i];

								if (!isC) {
									stnCode = stations[i].getId();
								} else {
									stnCode = stations[i].getBranch_code();
								}
								if (rs.getString(FROM_STATION)
										.equalsIgnoreCase(stnCode)) {

									dto.setBf_increment(dto.getBf_increment() + rs.getInt(NO_OF_ARTICLES) );
									dto.setBpi(dto.getBpi() + rs.getFloat(ACTUAL_WEIGHT));
									dto.setCc_hlc(dto.getCc_hlc() + rs.getFloat(CHARGED_WEIGHT));
														
									if ((rs.getInt(DDC) > 0)) {
										 dto.setCc_limit(rs.getInt(DD_EXTRA));
									
									}
									else{
										dto.setCc_refund(rs.getInt(DD_EXTRA));
									}
										

								}
							}
						}
					}
				}

				StationsDTO[] stn = getCounterInwardLRS(con, stations,
						GET_COUNTER_INWARDLR, isC);
				
				stationDto = getTotalCRs(con, stn, GET_COUNTER_TOTALCR,isC);

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}
		System.out.println("Out ReportingDAO getCounterConsolidated===>"+stationDto.length);
		return stationDto;
		

	}


	
	
	
	
	
		
	private StationsDTO[] getTotalCRs(Connection con, StationsDTO[] stn,
			String get_counter_totalcr, boolean isC) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		StationsDTO dto = null;
		if (con != null) {
			ps = con.prepareStatement(get_counter_totalcr);

			if (ps != null) {
				rs = ps.executeQuery();
				String stnCode = "";
				int type = -1;
				if (null != rs) {
					while (rs.next()) {

						for (int i = 0; i < stn.length; i++) {
							dto = stn[i];
							if (!isC) {
								stnCode = stn[i].getId();
							} else {
								stnCode = stn[i].getBranch_code();
							}
							if (rs.getString(FROM_STATION).equalsIgnoreCase(
									stnCode)) {
								
								dto.setDiscount(dto.getDiscount() + rs.getInt(CR_NO));
								
								

							}
						}

					}
				}
			}
		}

		return stn;
	}


	private StationsDTO[] getCounterInwardLRS(Connection con,
			StationsDTO[] stations, String get_counter_inwardlr, boolean isC) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		StationsDTO dto = null;
		if (con != null) {
			ps = con.prepareStatement(get_counter_inwardlr);

			if (ps != null) {
				rs = ps.executeQuery();
				String stnCode = "";
				int type = -1;
				if (null != rs) {
					while (rs.next()) {

						for (int i = 0; i < stations.length; i++) {
							dto = stations[i];
							if (!isC) {
								stnCode = stations[i].getId();
							} else {
								stnCode = stations[i].getBranch_code();
							}
							if (rs.getString(FROM_STATION).equalsIgnoreCase(
									stnCode)) {
								
								dto.setCc_special(dto.getCc_special() + rs.getInt(COUNT));
								
							
							}
						}

					}
				}
			}
		}

		return stations;
	}

	
	
	public StationsDTO[] getCounterConsolidatedMiscUnion(Date from, Date to,
			String branch, StationsDTO[] stations, String DBHistory)
			throws Exception {
		System.out.println("In ReportingDAO getCounterConsolidated===>");
		Connection con = null;
		PreparedStatement ps = null;
		DBHelper helper = new DBHelper();
		ResultSet rs = null;

		String GET_COUNTER_REPORT_MISC = "";

		String GET_COUNTER_INWARDLR = "";

		String GET_COUNTER_TOTALCR = "";

		StationsDTO dto = null;
		String fromDate = "";
		String toDate = "";
		boolean isC = false;
		int stationsLen = stations.length;
		StationsDTO[] stationDto = null;
		try {
			con = helper.getConnection();

			if (null != con) {
				SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
				fromDate = format.format(from);
				toDate = format.format(to);

				if (branch.startsWith("All")) {
					// ALL BRANCH
					if (branch.charAt(3) == 'C') {
						isC = true;
						GET_COUNTER_REPORT_MISC = " select b.branch_code as from_station,sum(a.no_of_articles) as no_of_articles,sum(a.actual_weight) as actual_weight, " +
								" sum(a.charged_weight) as charged_weight,count(a.ddc) as ddextra,(a.ddc) as ddc from lr a,station b  where a.from_station = b.station_code " +
								" and  b.branch_code like '%' and b.station_code like '%' " +
								" and lr_status != 1 and date(lr_date) between '" +
								fromDate +
								"' and '" +
								toDate 
								+"'  group by ddc = 0, b.branch_code "+
								" union "+
								" select b.branch_code as from_station,sum(a.no_of_articles) as no_of_articles,sum(a.actual_weight) as actual_weight, " +
								" sum(a.charged_weight) as charged_weight,count(a.ddc) as ddextra,(a.ddc) as ddc from lr_history a,station b  where a.from_station = b.station_code " +
								" and  b.branch_code like '%' and b.station_code like '%' " +
								" and lr_status != 1 and date(lr_date) between '" +
								fromDate +
								"' and '" +
								toDate 
								+"'  group by ddc = 0, b.branch_code ";


						GET_COUNTER_INWARDLR =  " select count(b.lr_no) as count,c.branch_code as from_station from lr_tracking a,lr b,station c where  b.lr_status != 1  and a.lr_status != 'ToArrive'  and  " +
								" a.station_code = c.station_code and c.branch_code like '%'" +
								" and  c.station_code like '%' and a.station_code != b.from_station " +
								" and a.lr_no = b.lr_no and date(a.inward_time) between '" +
								fromDate
								+ " ' and '" +
								toDate
								+ "' group by c.branch_code " +
								" union " +
								" select count(b.lr_no) as count,c.branch_code as from_station from lr_tracking_archive a,lr b,station c where b.lr_status != 1 and " +
								" a.station_code = c.station_code and c.branch_code like '%' and  c.station_code like '%' and a.station_code != b.from_station " +
								" and a.lr_status = 'Delivered' and a.lr_no = b.lr_no and date(a.inward_time) between '"+
								fromDate
								+"' and '"+
								toDate 
								+"' group by c.branch_code "+
								" union "+
								" select count(b.lr_no) as count,c.branch_code as from_station from lr_tracking_archive_history a,lr_history b,station c where b.lr_status != 1 and " +
								" a.station_code = c.station_code and c.branch_code like '%' and  c.station_code like '%' and a.station_code != b.from_station " +
								" and a.lr_status = 'Delivered' and a.lr_no = b.lr_no and date(a.inward_time) between '"+
								fromDate
								+"' and '"+
								toDate 
								+"' group by c.branch_code ";


						GET_COUNTER_TOTALCR = " select count(a.cr_no) as cr_no,(b.branch_code) as from_station  from cash_receipt a,station b where  a.status != 1 and " +
								" a.station_code = b.station_code and b.branch_code like '%' and  b.station_code like '%' " +
								" and date(a.date) between '"+ 
								fromDate +
								"' and '" +
								toDate + "' group by b.branch_code "+
								" union "+
								" select count(a.cr_no) as cr_no,(b.branch_code) as from_station  from cash_receipt a,station b where  a.status != 1 and " +
								" a.station_code = b.station_code and b.branch_code like '%' and  b.station_code like '%' " +
								" and date(a.date) between '"+ 
								fromDate +
								"' and '" +
								toDate + "' group by b.branch_code ";
					} else {
						// ALL Stations
						GET_COUNTER_REPORT_MISC = " select a.from_station as from_station,sum(a.no_of_articles) as no_of_articles,sum(a.actual_weight) as actual_weight, " +
								" sum(a.charged_weight) as charged_weight,count(a.ddc) as ddextra,(a.ddc) as ddc from lr a,station b  where a.from_station = b.station_code " +
								" and  b.branch_code like '%' and b.station_code like '%' " +
								" and lr_status != 1 and date(lr_date) between '" +
								fromDate +
								"' and '" +
								toDate +"' group by ddc = 0, a.from_station "+
								" union "+
								" select a.from_station as from_station,sum(a.no_of_articles) as no_of_articles,sum(a.actual_weight) as actual_weight, " +
								" sum(a.charged_weight) as charged_weight,count(a.ddc) as ddextra,(a.ddc) as ddc from lr_history a,station b  where a.from_station = b.station_code " +
								" and  b.branch_code like '%' and b.station_code like '%' " +
								" and lr_status != 1 and date(lr_date) between '" +
								fromDate +
								"' and '" +
								toDate +"' group by ddc = 0, a.from_station ";

						GET_COUNTER_INWARDLR =  " select count(b.lr_no) as count,(a.station_code) as from_station from lr_tracking a,lr b,station c where  b.lr_status != 1  and a.lr_status != 'ToArrive'  and  " +
								" a.station_code = c.station_code and c.branch_code like '%'" +
								" and  c.station_code like '%' and a.station_code != b.from_station " +
								" and a.lr_no = b.lr_no and date(a.inward_time) between '" +
								fromDate
								+ " ' and '" +
								toDate
								+ "' group by a.station_code " +
								" union " +
								" select count(b.lr_no) as count,(a.station_code) as from_station from lr_tracking_archive a,lr b,station c where b.lr_status != 1 and " +
								" a.station_code = c.station_code and c.branch_code like '%' and  c.station_code like '%' and a.station_code != b.from_station " +
								" and a.lr_status = 'Delivered' and a.lr_no = b.lr_no and date(a.inward_time) between '"+
								fromDate
								+"' and '"+
								toDate 
								+"' group by a.station_code "+
								" union "+
								" select count(b.lr_no) as count,(a.station_code) as from_station from lr_tracking_archive_history a,lr_history b,station c where b.lr_status != 1 and " +
								" a.station_code = c.station_code and c.branch_code like '%' and  c.station_code like '%' and a.station_code != b.from_station " +
								" and a.lr_status = 'Delivered' and a.lr_no = b.lr_no and date(a.inward_time) between '"+
								fromDate
								+"' and '"+
								toDate 
								+"' group by a.station_code ";


						GET_COUNTER_TOTALCR =  " select count(a.cr_no) as cr_no,(a.station_code) as from_station  from cash_receipt a,station b where  a.status != 1 and " +
								" a.station_code = b.station_code and b.branch_code like '%' and  b.station_code like '%' " +
								" and date(a.date) between '"+ 
								fromDate +
								"' and '" +
								toDate + "' group by a.station_code "+
								" union "+
								" select count(a.cr_no) as cr_no,(a.station_code) as from_station  from cash_receipt a,station b where  a.status != 1 and " +
								" a.station_code = b.station_code and b.branch_code like '%' and  b.station_code like '%' " +
								" and date(a.date) between '"+ 
								fromDate +
								"' and '" +
								toDate + "' group by a.station_code ";
					}

				} else {
					// selected branch stations

					GET_COUNTER_REPORT_MISC =  " select a.from_station as from_station,sum(a.no_of_articles) as no_of_articles,sum(a.actual_weight) as actual_weight, " +
								" sum(a.charged_weight) as charged_weight,count(a.ddc) as ddextra,(a.ddc) as ddc from lr a,station b  where a.from_station = b.station_code " +
								" and  b.branch_code like '" +
								branch 
								+ "' and b.station_code like '%' " +
								" and lr_status != 1 and date(lr_date) between '" +
								fromDate +
								"' and '" +
								toDate +"' group by ddc = 0, a.from_station "+
								" union "+
								" select a.from_station as from_station,sum(a.no_of_articles) as no_of_articles,sum(a.actual_weight) as actual_weight, " +
								" sum(a.charged_weight) as charged_weight,count(a.ddc) as ddextra,(a.ddc) as ddc from lr_history a,station b  where a.from_station = b.station_code " +
								" and  b.branch_code like '%' and b.station_code like '%' " +
								" and lr_status != 1 and date(lr_date) between '" +
								fromDate +
								"' and '" +
								toDate +"' group by ddc = 0, a.from_station ";
						
					GET_COUNTER_INWARDLR = " select count(b.lr_no) as count,(a.station_code) as from_station from lr_tracking a,lr b,station c where  b.lr_status != 1 and a.lr_status != 'ToArrive' and  " +
								" a.station_code = c.station_code and c.branch_code like '"+
								branch 
								+"' and  c.station_code like '%' and a.station_code != b.from_station " +
								" and a.lr_no = b.lr_no and date(a.inward_time) between '" +
								fromDate
								+ " ' and '" +
								toDate
								+ "' group by a.station_code " +
								" union  " +
								" select count(b.lr_no) as count,(a.station_code) as from_station from lr_tracking_archive a,lr b,station c where b.lr_status != 1 and " +
								" a.station_code = c.station_code and c.branch_code like '"+
								branch
								+"' and  c.station_code like '%' and a.station_code != b.from_station " +
								" and a.lr_status = 'Delivered' and a.lr_no = b.lr_no and date(a.inward_time) between '"+
								fromDate
								+"' and '"+
								toDate 
								+"' group by a.station_code "+
								" union "+
								" select count(b.lr_no) as count,(a.station_code) as from_station from lr_tracking_archive_history a,lr_history b,station c where b.lr_status != 1 and " +
								" a.station_code = c.station_code and c.branch_code like '"+
								branch
								+"' and  c.station_code like '%' and a.station_code != b.from_station " +
								" and a.lr_status = 'Delivered' and a.lr_no = b.lr_no and date(a.inward_time) between '"+
								fromDate
								+"' and '"+
								toDate 
								+"' group by a.station_code ";

					



					GET_COUNTER_TOTALCR = " select count(a.cr_no) as cr_no,(a.station_code) as from_station  from cash_receipt a,station b where  a.status != 1 and " +
								" a.station_code = b.station_code and b.branch_code like '"+ 
								branch +"' and  b.station_code like '%' " +
								" and date(a.date) between '"+ 
								fromDate +
								"' and '" +
								toDate + "' group by a.station_code "+
								" union "+
								" select count(a.cr_no) as cr_no,(a.station_code) as from_station  from cash_receipt a,station b where  a.status != 1 and " +
								" a.station_code = b.station_code and b.branch_code like '"+ 
								branch +"' and  b.station_code like '%' " +
								" and date(a.date) between '"+ 
								fromDate +
								"' and '" +
								toDate + "' group by a.station_code ";

				}

				ps = con.prepareStatement(GET_COUNTER_REPORT_MISC);

				if (ps != null) {
					rs = ps.executeQuery();
					String stnCode = "";
					if (null != rs) {
						while (rs.next()) {

							for (int i = 0; i < stationsLen; i++) {
								dto = stations[i];

								if (!isC) {
									stnCode = stations[i].getId();
								} else {
									stnCode = stations[i].getBranch_code();
								}
								if (rs.getString(FROM_STATION)
										.equalsIgnoreCase(stnCode)) {

									dto.setBf_increment(dto.getBf_increment() + rs.getInt(NO_OF_ARTICLES) );
									dto.setBpi(dto.getBpi() + rs.getFloat(ACTUAL_WEIGHT));
									dto.setCc_hlc(dto.getCc_hlc() + rs.getFloat(CHARGED_WEIGHT));
														
									if ((rs.getInt(DDC) > 0)) {
										 dto.setCc_limit(rs.getInt(DD_EXTRA));
									
									}
									else{
										dto.setCc_refund(rs.getInt(DD_EXTRA));
									}
										

								}
							}
						}
					}
				}

				StationsDTO[] stn = getCounterInwardLRS(con, stations,
						GET_COUNTER_INWARDLR, isC);
				
				stationDto = getTotalCRs(con, stn, GET_COUNTER_TOTALCR,isC);

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}
		System.out.println("Out ReportingDAO getCounterConsolidated===>"+stationDto.length);
		return stationDto;
		

	}

	
	
	public StationsDTO[] getCounterConsolidatedMiscHistory(Date from, Date to,
			String branch, StationsDTO[] stations, String dbHis)
			throws Exception {
		System.out.println("In ReportingDAO getCounterConsolidatedHistory===>");
		Connection con = null;
		PreparedStatement ps = null;
		//DBHelper helper = new DBHelper();
		ResultSet rs = null;

		String GET_COUNTER_REPORT_MISC = "";

		String GET_COUNTER_INWARDLR = "";

		String GET_COUNTER_TOTALCR = "";

		StationsDTO dto = null;
		String fromDate = "";
		String toDate = "";
		boolean isC = false;
		int stationsLen = stations.length;
		StationsDTO[] stationDto = null;
		try {
			//con = helper.getConnection();
			if (dbHis == null || dbHis.equals("")) {
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			} else {
				DBHelperHist helperHis = new DBHelperHist();
				con = helperHis.getConnectionHistory(dbHis);
			}

			if (null != con) {
				SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
				fromDate = format.format(from);
				toDate = format.format(to);

				if (branch.startsWith("All")) {
					// ALL BRANCH
					if (branch.charAt(3) == 'C') {
						isC = true;
						GET_COUNTER_REPORT_MISC = " select b.branch_code as from_station,sum(a.no_of_articles) as no_of_articles,sum(a.actual_weight) as actual_weight, " +
								" sum(a.charged_weight) as charged_weight,count(a.ddc) as ddextra,(a.ddc) as ddc from lr_history a,station b  where a.from_station = b.station_code " +
								" and  b.branch_code like '%' and b.station_code like '%' " +
								" and lr_status != 1 and date(lr_date) between '" +
								fromDate +
								"' and '" +
								toDate 
								+"'  group by ddc = 0, b.branch_code ";


						GET_COUNTER_INWARDLR =  " select count(b.lr_no) as count,c.branch_code as from_station from lr_tracking_archive_history a,lr_history b,station c where b.lr_status != 1 and " +
								" a.station_code = c.station_code and c.branch_code like '%' and  c.station_code like '%' and a.station_code != b.from_station " +
								" and a.lr_status = 'Delivered' and a.lr_no = b.lr_no and date(a.inward_time) between '"+
								fromDate
								+"' and '"+
								toDate 
								+"' group by c.branch_code ";

						GET_COUNTER_TOTALCR = " select count(a.cr_no) as cr_no,(b.branch_code) as from_station  from cash_receipt a,station b where  a.status != 1 and " +
								" a.station_code = b.station_code and b.branch_code like '%' and  b.station_code like '%' " +
								" and date(a.date) between '"+ 
								fromDate +
								"' and '" +
								toDate + "' group by b.branch_code ";
					} else {
						// ALL Stations
						GET_COUNTER_REPORT_MISC = " select a.from_station as from_station,sum(a.no_of_articles) as no_of_articles,sum(a.actual_weight) as actual_weight, " +
								" sum(a.charged_weight) as charged_weight,count(a.ddc) as ddextra,(a.ddc) as ddc from lr_history a,station b  where a.from_station = b.station_code " +
								" and  b.branch_code like '%' and b.station_code like '%' " +
								" and lr_status != 1 and date(lr_date) between '" +
								fromDate +
								"' and '" +
								toDate +"' group by ddc = 0, a.from_station ";

						GET_COUNTER_INWARDLR = 	" select count(b.lr_no) as count,(a.station_code) as from_station from lr_tracking_archive_history a,lr_history b,station c where b.lr_status != 1 and " +
								" a.station_code = c.station_code and c.branch_code like '%' and  c.station_code like '%' and a.station_code != b.from_station " +
								" and a.lr_status = 'Delivered' and a.lr_no = b.lr_no and date(a.inward_time) between '"+
								fromDate
								+"' and '"+
								toDate 
								+"' group by a.station_code ";


						GET_COUNTER_TOTALCR =  " select count(a.cr_no) as cr_no,(a.station_code) as from_station  from cash_receipt a,station b where  a.status != 1 and " +
								" a.station_code = b.station_code and b.branch_code like '%' and  b.station_code like '%' " +
								" and date(a.date) between '"+ 
								fromDate +
								"' and '" +
								toDate + "' group by a.station_code ";
					}

				} else {
					// selected branch stations

					GET_COUNTER_REPORT_MISC =  " select a.from_station as from_station,sum(a.no_of_articles) as no_of_articles,sum(a.actual_weight) as actual_weight, " +
								" sum(a.charged_weight) as charged_weight,count(a.ddc) as ddextra,(a.ddc) as ddc from lr_history a,station b  where a.from_station = b.station_code " +
								" and  b.branch_code like '" +
								branch 
								+ "' and b.station_code like '%' " +
								" and lr_status != 1 and date(lr_date) between '" +
								fromDate +
								"' and '" +
								toDate +"' group by ddc = 0, a.from_station ";
						
					GET_COUNTER_INWARDLR = " select count(b.lr_no) as count,(a.station_code) as from_station from lr_tracking_archive_history a,lr_history b,station c where b.lr_status != 1 and " +
								" a.station_code = c.station_code and c.branch_code like '"+
								branch
								+"' and  c.station_code like '%' and a.station_code != b.from_station " +
								" and a.lr_status = 'Delivered' and a.lr_no = b.lr_no and date(a.inward_time) between '"+
								fromDate
								+"' and '"+
								toDate 
								+"' group by a.station_code ";


					GET_COUNTER_TOTALCR = " select count(a.cr_no) as cr_no,(a.station_code) as from_station  from cash_receipt a,station b where  a.status != 1 and " +
							" a.station_code = b.station_code and b.branch_code like '"+ 
							branch +"' and  b.station_code like '%' " +
							" and date(a.date) between '"+ 
							fromDate +
							"' and '" +
							toDate + "' group by a.station_code ";
				}

				ps = con.prepareStatement(GET_COUNTER_REPORT_MISC);

				if (ps != null) {
					rs = ps.executeQuery();
					String stnCode = "";
					if (null != rs) {
						while (rs.next()) {

							for (int i = 0; i < stationsLen; i++) {
								dto = stations[i];

								if (!isC) {
									stnCode = stations[i].getId();
								} else {
									stnCode = stations[i].getBranch_code();
								}
								if (rs.getString(FROM_STATION)
										.equalsIgnoreCase(stnCode)) {

									dto.setBf_increment(dto.getBf_increment() + rs.getInt(NO_OF_ARTICLES) );
									dto.setBpi(dto.getBpi() + rs.getFloat(ACTUAL_WEIGHT));
									dto.setCc_hlc(dto.getCc_hlc() + rs.getFloat(CHARGED_WEIGHT));
														
									if ((rs.getInt(DDC) > 0)) {
										 dto.setCc_limit(rs.getInt(DD_EXTRA));
									
									}
									else{
										dto.setCc_refund(rs.getInt(DD_EXTRA));
									}
										

								}
							}
						}
					}
				}

				StationsDTO[] stn = getCounterInwardLRS(con, stations,
						GET_COUNTER_INWARDLR, isC);
				
				stationDto = getTotalCRs(con, stn, GET_COUNTER_TOTALCR,isC);

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}
		System.out.println("Out ReportingDAO getCounterConsolidatedHistory===>"+stationDto.length);
		return stationDto;
		

	}

	
	
	
	public BookingDTO[] getCounterDetailed(Date fromDate, Date toDate,
			String station_code, String branch_code, String DBHistory)
			throws RemoteException, BusinessException, Exception {
		System.out.println("In ReportingDAO getCounterDetailed===>");
		Connection con = null;
		PreparedStatement ps = null;
		DBHelper helper = new DBHelper();
		ResultSet rs = null;
		ArrayList<BookingDTO> counterDetailed = new ArrayList<BookingDTO>();

		BookingDTO dto = null;

		try {
			con = helper.getConnection();

			if (null != con) {
				ps = con.prepareStatement(GET_COUNTER_CONSOLIDATED);

				if (ps != null) {

					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(fromDate);
					String tofomattedDate = format.format(toDate);
					//System.out.println("Palani Added>" + formattedDate + " "
					//	+ tofomattedDate + " " + branch_code);
					ps.setString(1, branch_code);
					ps.setString(2, formattedDate);
					ps.setString(3, tofomattedDate);

					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {

							dto = new BookingDTO();

							dto.setFrom(rs.getString(FROM_STATION));
							dto.setType(rs.getString(LR_TYPE));
							dto.setRate_type(rs.getInt(RATE_TYPE));
							dto.setStatus(rs.getBoolean(LR_STATUS));

							counterDetailed.add(dto);
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = counterDetailed.size();
		System.out.println("Out ReportingDAO getCounterDetailed===>" + size);
		if (size > 0) {
			return (BookingDTO[]) counterDetailed.toArray(new BookingDTO[size]);
		}
		return null;

	}

	public BookingDTO[] getCounterDetailedHistory(Date fromDate, Date toDate,
			String station_code, String branch_code, String dbHis)	throws RemoteException, BusinessException, Exception {
		System.out.println("In ReportingDAO getCounterDetailed===>");
		Connection con = null;
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		ArrayList<BookingDTO> counterDetailed = new ArrayList<BookingDTO>();

		BookingDTO dto = null;

		try {
			if (dbHis == null || dbHis.equals("")) {
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			} else {
				DBHelperHist helperHis = new DBHelperHist();
				con = helperHis.getConnectionHistory(dbHis);
			}

			if (null != con) {
				ps = con.prepareStatement(GET_COUNTER_CONSOLIDATED_HISTORY);

				if (ps != null) {

					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(fromDate);
					String tofomattedDate = format.format(toDate);
					//System.out.println("Palani Added>" + formattedDate + " "
					//	+ tofomattedDate + " " + branch_code);
					ps.setString(1, branch_code);
					ps.setString(2, formattedDate);
					ps.setString(3, tofomattedDate);

					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {

							dto = new BookingDTO();

							dto.setFrom(rs.getString(FROM_STATION));
							dto.setType(rs.getString(LR_TYPE));
							dto.setRate_type(rs.getInt(RATE_TYPE));
							dto.setStatus(rs.getBoolean(LR_STATUS));

							counterDetailed.add(dto);
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = counterDetailed.size();
		System.out.println("Out ReportingDAO getCounterDetailed===>" + size);
		if (size > 0) {
			return (BookingDTO[]) counterDetailed.toArray(new BookingDTO[size]);
		}
		return null;

	}

	public GeneralSummaryDTO[] getSundryBookingAnalysis(Date fromDate,
			Date toDate,String branch, String station, String DBHistory) throws RemoteException,
			BusinessException, Exception {

		System.out.println("In ReportingDAO getSundryBookingAnalysis===>"+branch+"|"+station);
		Connection con = null;
		PreparedStatement ps = null;
		DBHelper helper = new DBHelper();
		ResultSet rs = null;
		ArrayList<GeneralSummaryDTO> sundryBookingAnalysis = new ArrayList<GeneralSummaryDTO>();

		GeneralSummaryDTO dto = null;

		try {
			con = helper.getConnection();

			if (null != con) {
				if(station.equalsIgnoreCase("Group")){
					ps = con.prepareStatement(GET_SUNDRY_BOOKING_ANALYSIS_GROUP);
				}else{
					ps = con.prepareStatement(GET_SUNDRY_BOOKING_ANALYSIS);
				}
				

				if (ps != null) {

					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(fromDate);
					String tofomattedDate = format.format(toDate);
					
					if(station.equalsIgnoreCase("Group")){
						ps.setString(1, branch);						
						ps.setString(2, formattedDate);
						ps.setString(3, tofomattedDate);
						
						ps.setString(4, branch);						
						ps.setString(5, formattedDate);
						ps.setString(6, tofomattedDate);

					}else{
						ps.setString(1, branch);
						ps.setString(2, station);
						ps.setString(3, formattedDate);
						ps.setString(4, tofomattedDate);
						
						ps.setString(5, branch);
						ps.setString(6, station);
						ps.setString(7, formattedDate);
						ps.setString(8, tofomattedDate);
					}

					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {

							dto = new GeneralSummaryDTO();

							dto.setStation_code(rs.getString(FROM_STATION));
							dto.setLr_no(rs.getInt(LR_NO));
							dto.setTotal(rs.getFloat(TOTAL));
							dto.setBft(rs.getFloat(BASIC_FRIEGHT));
							dto.setNo_of_articles(rs.getInt(NO_OF_ARTICLES));
							dto.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
							dto.setCharged_weight(rs.getFloat(CHARGED_WEIGHT));
							dto.setSundry_type(rs.getString(SUNDRY_TYPE));

							sundryBookingAnalysis.add(dto);
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = sundryBookingAnalysis.size();
		System.out.println("Out ReportingDAO getSundryBookingAnalysis===>"
				+ size);
		if (size > 0) {
			return (GeneralSummaryDTO[]) sundryBookingAnalysis.toArray(new GeneralSummaryDTO[size]);
		}
		return null;

	}

	
	public GeneralSummaryDTO[] getSundryBookingAnalysisUnion(Date fromDate,
			Date toDate,String branch, String station, String DBHistory) throws RemoteException,
			BusinessException, Exception {

		System.out.println("In ReportingDAO getSundryBookingAnalysis===>"+branch+"|"+station);
		Connection con = null;
		PreparedStatement ps = null;
		DBHelper helper = new DBHelper();
		ResultSet rs = null;
		ArrayList<GeneralSummaryDTO> sundryBookingAnalysis = new ArrayList<GeneralSummaryDTO>();

		GeneralSummaryDTO dto = null;

		try {
			con = helper.getConnection();

			if (null != con) {
				if(station.equalsIgnoreCase("Group")){
					ps = con.prepareStatement(GET_SUNDRY_BOOKING_ANALYSIS_GROUP_UNION);
				}else{
					ps = con.prepareStatement(GET_SUNDRY_BOOKING_ANALYSIS_UNION);
				}
				

				if (ps != null) {

					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(fromDate);
					String tofomattedDate = format.format(toDate);
					
					if(station.equalsIgnoreCase("Group")){
						ps.setString(1, branch);						
						ps.setString(2, formattedDate);
						ps.setString(3, tofomattedDate);
						
						ps.setString(4, branch);						
						ps.setString(5, formattedDate);
						ps.setString(6, tofomattedDate);
						
						ps.setString(7, branch);
						ps.setString(8, formattedDate);
						ps.setString(9, tofomattedDate);
						
						ps.setString(10, branch);
						ps.setString(11, formattedDate);
						ps.setString(12, tofomattedDate);

					}else{
						ps.setString(1, branch);
						ps.setString(2, station);
						ps.setString(3, formattedDate);
						ps.setString(4, tofomattedDate);
						
						ps.setString(5, branch);
						ps.setString(6, station);
						ps.setString(7, formattedDate);
						ps.setString(8, tofomattedDate);
						
						ps.setString(9, branch);
						ps.setString(10, station);
						ps.setString(11, formattedDate);
						ps.setString(12, tofomattedDate);
						
						ps.setString(13, branch);
						ps.setString(14, station);
						ps.setString(15, formattedDate);
						ps.setString(16, tofomattedDate);
					}

					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {

							dto = new GeneralSummaryDTO();

							dto.setStation_code(rs.getString(FROM_STATION));
							dto.setLr_no(rs.getInt(LR_NO));
							dto.setTotal(rs.getFloat(TOTAL));
							dto.setBft(rs.getFloat(BASIC_FRIEGHT));
							dto.setNo_of_articles(rs.getInt(NO_OF_ARTICLES));
							dto.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
							dto.setCharged_weight(rs.getFloat(CHARGED_WEIGHT));
							dto.setSundry_type(rs.getString(SUNDRY_TYPE));

							sundryBookingAnalysis.add(dto);
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = sundryBookingAnalysis.size();
		System.out.println("Out ReportingDAO getSundryBookingAnalysis===>"
				+ size);
		if (size > 0) {
			return (GeneralSummaryDTO[]) sundryBookingAnalysis.toArray(new GeneralSummaryDTO[size]);
		}
		return null;

	}

	
	public GeneralSummaryDTO[] getSundryBookingAnalysisHistory(Date fromDate,
			Date toDate,String branch, String station, String dbHis) throws RemoteException,
			BusinessException, Exception {

		System.out.println("In ReportingDAO getSundryBookingAnalysis===>");
		Connection con = null;
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		ArrayList<GeneralSummaryDTO> sundryBookingAnalysis = new ArrayList<GeneralSummaryDTO>();

		GeneralSummaryDTO dto = null;

		try {
			if (dbHis == null || dbHis.equals("")) {
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			} else {
				DBHelperHist helperHis = new DBHelperHist();
				con = helperHis.getConnectionHistory(dbHis);
			}

			if (null != con) {
				if(station.equalsIgnoreCase("Group")){
					ps = con.prepareStatement(GET_SUNDRY_BOOKING_ANALYSIS_GROUP_HISTORY);
				}else{
					ps = con.prepareStatement(GET_SUNDRY_BOOKING_ANALYSIS_HISTORY);
				}
				

				if (ps != null) {

					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(fromDate);
					String tofomattedDate = format.format(toDate);
					
					if(station.equalsIgnoreCase("Group")){
						ps.setString(1, branch);						
						ps.setString(2, formattedDate);
						ps.setString(3, tofomattedDate);
						
						ps.setString(4, branch);						
						ps.setString(5, formattedDate);
						ps.setString(6, tofomattedDate);

					}else{
						ps.setString(1, branch);
						ps.setString(2, station);
						ps.setString(3, formattedDate);
						ps.setString(4, tofomattedDate);
						
						ps.setString(5, branch);
						ps.setString(6, station);
						ps.setString(7, formattedDate);
						ps.setString(8, tofomattedDate);
					}

					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {

							dto = new GeneralSummaryDTO();

							dto.setStation_code(rs.getString(FROM_STATION));
							dto.setLr_no(rs.getInt(LR_NO));
							dto.setTotal(rs.getFloat(TOTAL));
							dto.setBft(rs.getFloat(BASIC_FRIEGHT));
							dto.setNo_of_articles(rs.getInt(NO_OF_ARTICLES));
							dto.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
							dto.setCharged_weight(rs.getFloat(CHARGED_WEIGHT));
							dto.setSundry_type(rs.getString(SUNDRY_TYPE));

							sundryBookingAnalysis.add(dto);
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = sundryBookingAnalysis.size();
		System.out.println("Out ReportingDAO getSundryBookingAnalysis===>"
				+ size);
		if (size > 0) {
			return (GeneralSummaryDTO[]) sundryBookingAnalysis
					.toArray(new GeneralSummaryDTO[size]);
		}
		return null;

	}

	
	
	
	public StationsDTO[] getServicetax(Date fromDate,
			Date toDate, String DBHistory) throws RemoteException,
			BusinessException, Exception {

		System.out.println("In ReportingDAO getServiceTax===>");
		Connection con = null;
		DBHelper helper = new DBHelper();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<StationsDTO> ServiceTax = new ArrayList<StationsDTO>();

		StationsDTO dto = null;

		try {
			con = helper.getConnection();
			
			if (null != con) {
				ps = con.prepareStatement(GET_SERVICETAX);

				if (ps != null) {

					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(fromDate);
					String tofomattedDate = format.format(toDate);

					ps.setString(1, formattedDate);
					ps.setString(2, tofomattedDate);
					

					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {

							dto = new StationsDTO();

							dto.setStationname(rs.getString(FROM_STATION));
							dto.setTotalfreight(rs.getFloat(TOTAL));
							dto.setServicetax(rs.getFloat(STAX));
							
							

							ServiceTax.add(dto);
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = ServiceTax.size();
		System.out.println("Out ReportingDAO getServiceTax===>"
				+ size);
		if (size > 0) {
			return (StationsDTO[]) ServiceTax
					.toArray(new StationsDTO[size]);
		}
		return null;

	}
	
	public StationsDTO[] getServicetaxUnion(Date fromDate,
			Date toDate, String DBHistory) throws RemoteException,
			BusinessException, Exception {

		System.out.println("In ReportingDAO getServiceTax===>");
		Connection con = null;
		DBHelper helper = new DBHelper();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<StationsDTO> ServiceTax = new ArrayList<StationsDTO>();

		StationsDTO dto = null;

		try {
			con = helper.getConnection();
			
			if (null != con) {
				ps = con.prepareStatement(GET_SERVICETAX_UNION);

				if (ps != null) {

					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(fromDate);
					String tofomattedDate = format.format(toDate);

					ps.setString(1, formattedDate);
					ps.setString(2, tofomattedDate);
					ps.setString(3, formattedDate);
					ps.setString(4, tofomattedDate);
					

					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {

							dto = new StationsDTO();

							dto.setStationname(rs.getString(FROM_STATION));
							dto.setTotalfreight(rs.getFloat(TOTAL));
							dto.setServicetax(rs.getFloat(STAX));
							
							

							ServiceTax.add(dto);
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = ServiceTax.size();
		System.out.println("Out ReportingDAO getServiceTax===>"
				+ size);
		if (size > 0) {
			return (StationsDTO[]) ServiceTax
					.toArray(new StationsDTO[size]);
		}
		return null;

	}


	public StationsDTO[] getServicetaxHistory(Date fromDate,
			Date toDate, String DBHistory) throws RemoteException,
			BusinessException, Exception {

		System.out.println("In ReportingDAO getServiceTaxHistory===>");
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<StationsDTO> ServiceTax = new ArrayList<StationsDTO>();

		StationsDTO dto = null;

		try {
			
			if (DBHistory == null || DBHistory.equals("")) {
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			} else {
				DBHelperHist helperHis = new DBHelperHist();
				con = helperHis.getConnectionHistory(DBHistory);
			}

			if (null != con) {
				ps = con.prepareStatement(GET_SERVICETAX_HISTORY);

				if (ps != null) {

					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(fromDate);
					String tofomattedDate = format.format(toDate);

					ps.setString(1, formattedDate);
					ps.setString(2, tofomattedDate);
					

					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {

							dto = new StationsDTO();

							dto.setStationname(rs.getString(FROM_STATION));
							dto.setTotalfreight(rs.getFloat(TOTAL));
							dto.setServicetax(rs.getFloat(STAX));
							
							

							ServiceTax.add(dto);
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = ServiceTax.size();
		System.out.println("Out ReportingDAO getServiceTaxHistory===>"
				+ size);
		if (size > 0) {
			return (StationsDTO[]) ServiceTax
					.toArray(new StationsDTO[size]);
		}
		return null;

	}

	
	public StationsDTO[] getServicetaxLR(Date fromDate,
			Date toDate, String DBHistory) throws RemoteException,
			BusinessException, Exception {

		System.out.println("In ReportingDAO getServiceTax_LR===>");
		Connection con = null;
		DBHelper helper = new DBHelper();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<StationsDTO> ServiceTax = new ArrayList<StationsDTO>();

		StationsDTO dto = null;
		try {
			con = helper.getConnection();
			
			if (null != con) {
				ps = con.prepareStatement(GET_SERVICETAX_LR);
				
				if (ps != null) {

					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(fromDate);
					String tofomattedDate = format.format(toDate);
					ps.setString(1, formattedDate);
					ps.setString(2, tofomattedDate);
					

					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {
							dto = new StationsDTO();

							dto.setLrno(rs.getString(LR_NO));
							
							dto.setLrdate(rs.getString(LR_DATE));
							dto.setFrom(rs.getString(FROM_STATION));
							dto.setTo(rs.getString(TO_STATION));
							dto.setCnor(rs.getString(CONSIGNOR_NAME));
							dto.setCnee(rs.getString(CONSIGNEE_NAME));
							dto.setTotalfreight(rs.getFloat(TOTAL));
							dto.setServicetax(rs.getFloat(STAX));
							
							ServiceTax.add(dto);
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = ServiceTax.size();
		System.out.println("Out ReportingDAO getServiceTax_LR===>"
				+ size);
		if (size > 0) {
			return (StationsDTO[]) ServiceTax
					.toArray(new StationsDTO[size]);
		}
		return null;

	}
	
	public StationsDTO[] getServicetaxLRUnion(Date fromDate,
			Date toDate, String DBHistory) throws RemoteException,
			BusinessException, Exception {

		System.out.println("In ReportingDAO getServiceTax_LR===>");
		Connection con = null;
		DBHelper helper = new DBHelper();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<StationsDTO> ServiceTax = new ArrayList<StationsDTO>();

		StationsDTO dto = null;
		try {
			con = helper.getConnection();
			
			if (null != con) {
				ps = con.prepareStatement(GET_SERVICETAX_LR_UNION);
				
				if (ps != null) {

					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(fromDate);
					String tofomattedDate = format.format(toDate);
					ps.setString(1, formattedDate);
					ps.setString(2, tofomattedDate);
					ps.setString(3, formattedDate);
					ps.setString(4, tofomattedDate);

					

					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {
							dto = new StationsDTO();

							dto.setLrno(rs.getString(LR_NO));
							
							dto.setLrdate(rs.getString(LR_DATE));
							dto.setFrom(rs.getString(FROM_STATION));
							dto.setTo(rs.getString(TO_STATION));
							dto.setCnor(rs.getString(CONSIGNOR_NAME));
							dto.setCnee(rs.getString(CONSIGNEE_NAME));
							dto.setTotalfreight(rs.getFloat(TOTAL));
							dto.setServicetax(rs.getFloat(STAX));
							
							ServiceTax.add(dto);
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = ServiceTax.size();
		System.out.println("Out ReportingDAO getServiceTax_LR===>"
				+ size);
		if (size > 0) {
			return (StationsDTO[]) ServiceTax
					.toArray(new StationsDTO[size]);
		}
		return null;

	}


	public StationsDTO[] getServicetaxLRHistory(Date fromDate,
			Date toDate, String DBHistory) throws RemoteException,
			BusinessException, Exception {

		System.out.println("In ReportingDAO getServiceTax_LR_History===>");
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<StationsDTO> ServiceTax = new ArrayList<StationsDTO>();

		StationsDTO dto = null;

		try {
			if (DBHistory == null || DBHistory.equals("")) {
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			} else {
				DBHelperHist helperHis = new DBHelperHist();
				con = helperHis.getConnectionHistory(DBHistory);
			}

			if (null != con) {
				ps = con.prepareStatement(GET_SERVICETAX_LR_HISTORY);

				if (ps != null) {

					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(fromDate);
					String tofomattedDate = format.format(toDate);

					ps.setString(1, formattedDate);
					ps.setString(2, tofomattedDate);
					

					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {

							dto = new StationsDTO();

							dto.setLrno(rs.getString(LR_NO));
							dto.setLrdate(rs.getString(LR_DATE));
							dto.setFrom(rs.getString(FROM_STATION));
							dto.setTo(rs.getString(TO_STATION));
							dto.setCnor(rs.getString(CONSIGNOR_NAME));
							dto.setCnee(rs.getString(CONSIGNEE_NAME));
							dto.setTotalfreight(rs.getFloat(TOTAL));
							dto.setServicetax(rs.getFloat(STAX));
							
							

							ServiceTax.add(dto);
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = ServiceTax.size();
		System.out.println("Out ReportingDAO getServiceTax_LR_History===>"
				+ size);
		if (size > 0) {
			return (StationsDTO[]) ServiceTax
					.toArray(new StationsDTO[size]);
		}
		return null;

	}
	
	
	
	
	
	
	
	
	
	public BookingDTO[] getUPDReady(String station_code, String branch_code,
			int inwardDays, boolean isMoreThan, String DBHistory)
			throws RemoteException, BusinessException, Exception {
		System.out.println("In ReportingDAO getUPDReady===>");
		Connection con = null;
		PreparedStatement ps = null;
		DBHelper helper = new DBHelper();
		ResultSet rs = null;
		ArrayList<BookingDTO> updReady = new ArrayList<BookingDTO>();

		BookingDTO dto = null;

		try {
			con = helper.getConnection();

			if (null != con) {
				if (isMoreThan) {
					ps = con.prepareStatement(GET_UPD_READY);
				} else {
					ps = con.prepareStatement(GET_UPD_READY_EQUAL);
				}

				if (ps != null) {

					ps.setInt(1, inwardDays);
					ps.setString(2, branch_code);
					ps.setString(3, station_code);

					rs = ps.executeQuery();

					if (null != rs) {
						while (rs.next()) {

							dto = new BookingDTO();

							dto.setFrom(rs.getString(FROM_STATION));
							dto.setTo(rs.getString(TO_STATION));
							dto.setLrno(rs.getString(LR_NO));
							dto.setDate(rs.getDate(LR_DATE));
							dto.setType(rs.getString(LR_TYPE));
							dto.setConsignorName(rs.getString(CONSIGNOR_NAME));
							dto.setConsigneeName(rs.getString(CONSIGNEE_NAME));
							// Inward Time
							dto.setDeliveredDate(rs.getDate(INWARD_TIME));
							dto.setTotal(rs.getFloat(TOTAL));
							dto.setNo_of_articles(rs.getInt("inwardDays"));

							//String cnorPhone = getUPDPhone(con, dto.getConsignorName());

							//String cneePhone = getUPDPhone(con, dto.getConsigneeName());

							dto.setFromMobile(rs.getString(CNORPHONE));
							dto.setToMobile(rs.getString(CNEEPHONE));

							updReady.add(dto);
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = updReady.size();
		System.out.println("Out ReportingDAO getUPDReady===>" + size);
		if (size > 0) {
			return (BookingDTO[]) updReady.toArray(new BookingDTO[size]);
		}
		return null;

	}

	
	public BookingDTO[] getUPDReadyUnion(String station_code, String branch_code,
			int inwardDays, boolean isMoreThan, String DBHistory)
			throws RemoteException, BusinessException, Exception {
		System.out.println("In ReportingDAO getUPDReady===>");
		Connection con = null;
		PreparedStatement ps = null;
		DBHelper helper = new DBHelper();
		ResultSet rs = null;
		ArrayList<BookingDTO> updReady = new ArrayList<BookingDTO>();

		BookingDTO dto = null;

		try {
			con = helper.getConnection();

			if (null != con) {
				if (isMoreThan) {
					ps = con.prepareStatement(GET_UPD_READY_UNION);
				} else {
					ps = con.prepareStatement(GET_UPD_READY_EQUAL_UNION);
				}

				if (ps != null) {

					ps.setInt(1, inwardDays);
					ps.setString(2, branch_code);
					ps.setString(3, station_code);
					ps.setInt(4, inwardDays);
					ps.setString(5, branch_code);
					ps.setString(6, station_code);


					rs = ps.executeQuery();

					if (null != rs) {
						while (rs.next()) {

							dto = new BookingDTO();

							dto.setFrom(rs.getString(FROM_STATION));
							dto.setTo(rs.getString(TO_STATION));
							dto.setLrno(rs.getString(LR_NO));
							dto.setDate(rs.getDate(LR_DATE));
							dto.setType(rs.getString(LR_TYPE));
							dto.setConsignorName(rs.getString(CONSIGNOR_NAME));
							dto.setConsigneeName(rs.getString(CONSIGNEE_NAME));
							// Inward Time
							dto.setDeliveredDate(rs.getDate(INWARD_TIME));
							dto.setTotal(rs.getFloat(TOTAL));
							dto.setNo_of_articles(rs.getInt("inwardDays"));

							//String cnorPhone = getUPDPhone(con, dto.getConsignorName());

							//String cneePhone = getUPDPhone(con, dto.getConsigneeName());

							dto.setFromMobile(rs.getString(CNORPHONE));
							dto.setToMobile(rs.getString(CNEEPHONE));

							updReady.add(dto);
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = updReady.size();
		System.out.println("Out ReportingDAO getUPDReady===>" + size);
		if (size > 0) {
			return (BookingDTO[]) updReady.toArray(new BookingDTO[size]);
		}
		return null;

	}

	
	public BookingDTO[] getUPDReadyHistory(String station_code,
			String branch_code, int inwardDays, boolean isMoreThan,
			String dbHis) throws RemoteException, BusinessException,
			Exception {
		System.out.println("In ReportingDAO getUPDReady===>");
		Connection con = null;
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		ArrayList<BookingDTO> updReady = new ArrayList<BookingDTO>();

		BookingDTO dto = null;

		try {
			if (dbHis == null || dbHis.equals("")) {
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			} else {
				DBHelperHist helperHis = new DBHelperHist();
				con = helperHis.getConnectionHistory(dbHis);
			}

			if (null != con) {
				if (isMoreThan) {
					ps = con.prepareStatement(GET_UPD_READY_HISTORY);
				} else {
					ps = con.prepareStatement(GET_UPD_READY_EQUAL_HISTORY);
				}

				if (ps != null) {

					ps.setInt(1, inwardDays);
					ps.setString(2, branch_code);
					ps.setString(3, station_code);

					rs = ps.executeQuery();

					if (null != rs) {
						while (rs.next()) {

							dto = new BookingDTO();

							dto.setFrom(rs.getString(FROM_STATION));
							dto.setTo(rs.getString(TO_STATION));
							dto.setLrno(rs.getString(LR_NO));
							dto.setDate(rs.getDate(LR_DATE));
							dto.setType(rs.getString(LR_TYPE));
							dto.setConsignorName(rs.getString(CONSIGNOR_NAME));
							dto.setConsigneeName(rs.getString(CONSIGNEE_NAME));
							// Inward Time
							dto.setDeliveredDate(rs.getDate(INWARD_TIME));
							dto.setTotal(rs.getFloat(TOTAL));
							dto.setNo_of_articles(rs.getInt("inwardDays"));

							//String cnorPhone = getUPDPhone(con, dto.getConsignorName());

							//String cneePhone = getUPDPhone(con, dto.getConsigneeName());

							dto.setFromMobile(rs.getString(CNORPHONE));
							dto.setToMobile(rs.getString(CNEEPHONE));

							updReady.add(dto);
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = updReady.size();
		System.out.println("Out ReportingDAO getUPDReady===>" + size);
		if (size > 0) {
			return (BookingDTO[]) updReady.toArray(new BookingDTO[size]);
		}
		return null;

	}

	private String getUPDPhone(Connection con, String name) throws Exception {

		PreparedStatement ps = null;
		ResultSet rs = null;
		String phone = null;

		try {
			if (con != null) {

				ps = con.prepareStatement(GET_UPD_READY_PHONE);

				if (ps != null) {

					ps.setString(1, name);
					rs = ps.executeQuery();

					if (null != rs) {
						if (rs.next()) {
							phone = rs.getString("phone");
							if (phone != null) {
								return phone;
							} else {
								return rs.getString("landline");
							}
						}
					}
				}
			}
		} catch (SQLException sqlexception) {
			throw sqlexception;
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();

			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (ps != null)
					ps.close();
			}
		}

		return null;

	}

	/*public GMROutTimeDTO[] getInwardRegister(String stationCode, Date from,
			Date to, String DBHistory) throws RemoteException,
			BusinessException, Exception {

		System.out.println("In ReportingDAO getInwardRegister===>");
		Connection con = null;
		PreparedStatement ps = null;
		DBHelper helper = new DBHelper();
		ResultSet rs = null;
		ArrayList<GMROutTimeDTO> inwardRegister = new ArrayList<GMROutTimeDTO>();

		GMROutTimeDTO dto = null;

		try {
			con = helper.getConnection();

			if (null != con) {
				ps = con.prepareStatement(GET_INWARD_REGISTER);

				if (ps != null) {

					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedFromDate = format.format(from);
					String formattedToDate = format.format(to);

					ps.setString(1, stationCode);
					ps.setString(2, formattedFromDate);
					ps.setString(3, formattedToDate);

					ps.setString(4, stationCode);
					ps.setString(5, formattedFromDate);
					ps.setString(6, formattedToDate);

					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {

							if (!rs.getString(LR_STATUS).equalsIgnoreCase(
									"ToArrive")) {
								dto = new GMROutTimeDTO();

								dto.setFrom(rs.getString(FROM_STATION));
								dto.setLr_no(rs.getString(LR_NO));
								dto.setLrDate(rs.getDate(LR_DATE));
								dto.setLr_type(rs.getString(LR_TYPE));
								// dto.setTo(rs.getString(STATION_CODE));
								dto.setOutTimeDate(rs.getDate(INWARD_TIME));
								dto.setActual_weight(rs
										.getFloat(CHARGED_WEIGHT));
								dto.setConsignorName(rs
										.getString(CONSIGNOR_NAME));
								dto.setConsigneeName(rs
										.getString(CONSIGNEE_NAME));
								dto.setArticle_type(rs.getString(VEHICLE_NO));

								dto
										.setNo_of_articles(rs
												.getInt(NO_OF_ARTICLES));
								dto.setTotal(rs.getFloat(TOTAL));

								inwardRegister.add(dto);
							}
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = inwardRegister.size();
		System.out.println("Out ReportingDAO getInwardRegister===>" + size);
		if (size > 0) {
			return (GMROutTimeDTO[]) inwardRegister
					.toArray(new GMROutTimeDTO[size]);
		}
		return null;

	}*/
	
	
	
	public GMROutTimeDTO[] getInwardRegister(Date from,
			   Date to, String branchCode, String stnCode, String DBHistory) throws RemoteException,
			   BusinessException, Exception {

			  System.out.println("In ReportingDAO getInwardRegister===>");
			  Connection con = null;
			  PreparedStatement ps = null;
			  DBHelper helper = new DBHelper();
			  ResultSet rs = null;
			  ArrayList<GMROutTimeDTO> inwardRegister = new ArrayList<GMROutTimeDTO>();

			  GMROutTimeDTO dto = null;

			  try {
			   con = helper.getConnection();

			   if (null != con) {
			    ps = con.prepareStatement(GET_INWARD_REGISTER);

			    if (ps != null) {

			     SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
			     String formattedFromDate = format.format(from);
			     String formattedToDate = format.format(to);

			     ps.setString(1,branchCode);
			     ps.setString(2,stnCode);
			     ps.setString(3, formattedFromDate);
			     ps.setString(4, formattedToDate);
			     ps.setString(5, branchCode);
			    // ps.setString(5, stationCode);
			     ps.setString(6,stnCode );
			     ps.setString(7, formattedFromDate);
			     ps.setString(8, formattedToDate);

			     rs = ps.executeQuery();
			     if (null != rs) {
			      while (rs.next()) {
			       String status = rs.getString(LR_STATUS);
			       int count = rs.getInt(COUNT);
			     
			        dto = new GMROutTimeDTO();

			        dto.setFrom(rs.getString(FROM_STATION));
			        dto.setLr_no(rs.getString(LR_NO));
			        dto.setLrDate(rs.getDate(LR_DATE));
			        dto.setLr_type(rs.getString(LR_TYPE));
			        
			        dto.setOutTimeDate(rs.getDate(INWARD_TIME));
			        dto.setActual_weight(rs
			          .getFloat(CHARGED_WEIGHT));
			        dto.setConsignorName(rs
			          .getString(CONSIGNOR_NAME));
			        dto.setConsigneeName(rs
			          .getString(CONSIGNEE_NAME));
			       

			        dto
			          .setNo_of_articles(rs
			            .getInt(NO_OF_ARTICLES));
			        dto.setTotal(rs.getFloat(TOTAL));
			        dto.setArticle_type(rs.getString(VEHICLE_NO));
			       /* if(count == 3){
			        	String VehicleNo = getPreviousVehicleNo(con, dto.getLr_no(), stationCode,count);
			        	dto.setArticle_type(VehicleNo);
			        	 System.out.println("vvv--->"+VehicleNo);
			        }
			        else{
			        	dto.setArticle_type(rs.getString(VEHICLE_NO));
			        }*/
			       
			        
			       
			        
			      
			        
			        inwardRegister.add(dto);
			     
			      }
			     }
			    }

			   }

			  } catch (SQLException sqlexception) {
			   sqlexception.printStackTrace();
			   throw sqlexception;
			  } catch (Exception exception) {
			   exception.printStackTrace();
			   throw exception;
			  } finally {
			   try {
			    if (null != rs)
			     rs.close();
			    if (null != ps)
			     ps.close();
			    if (null != con)
			     con.close();
			   } catch (Exception exception) {
			    if (null != rs)
			     rs.close();
			    if (null != ps)
			     ps.close();
			    if (null != con)
			     con.close();
			   }
			  }

			  int size = inwardRegister.size();
			  System.out.println("Out ReportingDAO getInwardRegister===>" + size);
			  if (size > 0) {
			   return (GMROutTimeDTO[]) inwardRegister
			     .toArray(new GMROutTimeDTO[size]);
			  }
			  return null;

			 }
	
	
	public GMROutTimeDTO[] getInwardRegisterUnion(Date from,
			   Date to, String branchCode, String stnCode, String DBHistory) throws RemoteException,
			   BusinessException, Exception {

			  System.out.println("In ReportingDAO getInwardRegister===>");
			  Connection con = null;
			  PreparedStatement ps = null;
			  DBHelper helper = new DBHelper();
			  ResultSet rs = null;
			  ArrayList<GMROutTimeDTO> inwardRegister = new ArrayList<GMROutTimeDTO>();

			  GMROutTimeDTO dto = null;

			  try {
			   con = helper.getConnection();

			   if (null != con) {
			    ps = con.prepareStatement(GET_INWARD_REGISTER_UNION);

			    if (ps != null) {

			     SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
			     String formattedFromDate = format.format(from);
			     String formattedToDate = format.format(to);

			     ps.setString(1,branchCode);
			     ps.setString(2,stnCode);
			     ps.setString(3, formattedFromDate);
			     ps.setString(4, formattedToDate);
			     ps.setString(5, branchCode);
			    // ps.setString(5, stationCode);
			     ps.setString(6,stnCode );
			     ps.setString(7, formattedFromDate);
			     ps.setString(8, formattedToDate);
			     ps.setString(9,branchCode );
				ps.setString(10, stnCode);
				//ps.setString(2, stationCode);
				ps.setString(11, formattedFromDate);
				ps.setString(12, formattedToDate);	

			     rs = ps.executeQuery();
			     if (null != rs) {
			      while (rs.next()) {
			       String status = rs.getString(LR_STATUS);
			       int count = rs.getInt(COUNT);
			     
			        dto = new GMROutTimeDTO();

			        dto.setFrom(rs.getString(FROM_STATION));
			        dto.setLr_no(rs.getString(LR_NO));
			        dto.setLrDate(rs.getDate(LR_DATE));
			        dto.setLr_type(rs.getString(LR_TYPE));
			        
			        dto.setOutTimeDate(rs.getDate(INWARD_TIME));
			        dto.setActual_weight(rs
			          .getFloat(CHARGED_WEIGHT));
			        dto.setConsignorName(rs
			          .getString(CONSIGNOR_NAME));
			        dto.setConsigneeName(rs
			          .getString(CONSIGNEE_NAME));
			       

			        dto
			          .setNo_of_articles(rs
			            .getInt(NO_OF_ARTICLES));
			        dto.setTotal(rs.getFloat(TOTAL));
			        dto.setArticle_type(rs.getString(VEHICLE_NO));
			       /* if(count == 3){
			        	String VehicleNo = getPreviousVehicleNo(con, dto.getLr_no(), stationCode,count);
			        	dto.setArticle_type(VehicleNo);
			        	 System.out.println("vvv--->"+VehicleNo);
			        }
			        else{
			        	dto.setArticle_type(rs.getString(VEHICLE_NO));
			        }*/
			       
			        
			       
			        
			      
			        
			        inwardRegister.add(dto);
			     
			      }
			     }
			    }

			   }

			  } catch (SQLException sqlexception) {
			   sqlexception.printStackTrace();
			   throw sqlexception;
			  } catch (Exception exception) {
			   exception.printStackTrace();
			   throw exception;
			  } finally {
			   try {
			    if (null != rs)
			     rs.close();
			    if (null != ps)
			     ps.close();
			    if (null != con)
			     con.close();
			   } catch (Exception exception) {
			    if (null != rs)
			     rs.close();
			    if (null != ps)
			     ps.close();
			    if (null != con)
			     con.close();
			   }
			  }

			  int size = inwardRegister.size();
			  System.out.println("Out ReportingDAO getInwardRegister===>" + size);
			  if (size > 0) {
			   return (GMROutTimeDTO[]) inwardRegister
			     .toArray(new GMROutTimeDTO[size]);
			  }
			  return null;

			 }
	

	
	private String getPreviousVehicleNo(Connection con, String lr_no,
			  String stationCode, int count) throws Exception {

			  PreparedStatement ps = null;
			  ResultSet rs = null;
			  String vehicleNo = null;

			  try {
			   if (con != null) {

				 if(count == 3){
			     ps = con.prepareStatement(GET_VEHICLE_NO_DELIVERED);
			    }else{
			     ps = con.prepareStatement(GET_VEHICLE_NO_ARRIVED);
			    }

			    if (ps != null) {

			     ps.setString(1, lr_no);
			     ps.setString(2, stationCode);     
			     
			     rs = ps.executeQuery();

			     if (null != rs) {
			      if (rs.next()) {
			       vehicleNo = rs.getString(VEHICLE_NO);
			       if (vehicleNo != null) {
			        return vehicleNo;
			       }
			      }
			     }
			    }
			   }
			  } catch (SQLException sqlexception) {
			   throw sqlexception;
			  } catch (Exception exception) {
			   throw exception;
			  } finally {
			   try {
			    if (null != rs)
			     rs.close();
			    if (null != ps)
			     ps.close();

			   } catch (Exception exception) {
			    if (null != rs)
			     rs.close();
			    if (ps != null)
			     ps.close();
			   }
			  }

			  return null;
			 }

	public GMROutTimeDTO[] getInwardRegisterHistory(
			Date from, Date to, String branchCode, String stnCode, String dbHis) throws RemoteException,
			BusinessException, Exception {

		System.out.println("In ReportingDAO getInwardRegisterHistory===>");
		Connection con = null;
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		ArrayList<GMROutTimeDTO> inwardRegister = new ArrayList<GMROutTimeDTO>();

		GMROutTimeDTO dto = null;

		try {
			if (dbHis == null || dbHis.equals("")) {
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			} else {
				DBHelperHist helperHis = new DBHelperHist();
				con = helperHis.getConnectionHistory(dbHis);
			}

			if (null != con) {
				ps = con.prepareStatement(GET_INWARD_REGISTER_HISTORY);

				if (ps != null) {

					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedFromDate = format.format(from);
					String formattedToDate = format.format(to);
					
					ps.setString(1,branchCode );
					ps.setString(2, stnCode);
					//ps.setString(2, stationCode);
					ps.setString(3, formattedFromDate);
					ps.setString(4, formattedToDate);				

					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {
							
							 int count = rs.getInt(COUNT);
							   
							//if (!rs.getString(LR_STATUS).equalsIgnoreCase("ToArrive")) {
								dto = new GMROutTimeDTO();

								dto.setFrom(rs.getString(FROM_STATION));
								dto.setLr_no(rs.getString(LR_NO));
								dto.setLrDate(rs.getDate(LR_DATE));
								dto.setLr_type(rs.getString(LR_TYPE));
								// dto.setTo(rs.getString(STATION_CODE));
								dto.setOutTimeDate(rs.getDate(INWARD_TIME));
								dto.setActual_weight(rs
										.getFloat(CHARGED_WEIGHT));
								dto.setConsignorName(rs
										.getString(CONSIGNOR_NAME));
								dto.setConsigneeName(rs
										.getString(CONSIGNEE_NAME));
								
								/*if(count == 3){
							        	String VehicleNo = getPreviousVehicleNo(con, dto.getLr_no(), stationCode,count);
							        	dto.setArticle_type(VehicleNo);
							      /*  	 System.out.println("vvv--->"+VehicleNo);
							        }
								 else{
									 dto.setArticle_type(rs.getString(VEHICLE_NO));
								 }*/
								dto.setArticle_type(rs.getString(VEHICLE_NO));
								dto
										.setNo_of_articles(rs
												.getInt(NO_OF_ARTICLES));
								dto.setTotal(rs.getFloat(TOTAL));

								inwardRegister.add(dto);
							//}
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = inwardRegister.size();
		System.out.println("Out ReportingDAO getInwardRegister===>" + size);
		if (size > 0) {
			return (GMROutTimeDTO[]) inwardRegister
					.toArray(new GMROutTimeDTO[size]);
		}
		return null;

	}

	public CustomerBusinessAnalysisDTO[] getInterIntraState(Date fromDate,
			Date toDate, String DBHistory) throws Exception {

		System.out.println("In ReportingDAO getInterIntraState===>");
		Connection con = null;
		PreparedStatement ps = null;
		DBHelper helper = new DBHelper();
		ResultSet rs = null;
		ArrayList<CustomerBusinessAnalysisDTO> list = new ArrayList<CustomerBusinessAnalysisDTO>();

		CustomerBusinessAnalysisDTO dto = null;

		try {
			con = helper.getConnection();

			if (null != con) {

				SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
				String formattedDate = format.format(fromDate);
				String tofomattedDate = format.format(toDate);

				ps = con.prepareStatement(GET_INTER_INTRA);
				ps.setString(1, formattedDate);
				ps.setString(2, tofomattedDate);

				if (ps != null) {
					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {
							dto = new CustomerBusinessAnalysisDTO();

							dto.setFromStation(rs.getString("fromState"));
							dto.setCustomerName(rs.getString("toState"));
							dto.setBasic_freight(rs.getFloat(BASIC_FRIEGHT));
							dto.setTotal_freight(rs.getFloat(TOTAL));
							dto.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
							dto.setCharged_weight(rs.getFloat(CHARGED_WEIGHT));
							dto.setNoa(rs.getInt(NO_OF_ARTICLES));
							dto.setCount(rs.getInt(COUNT));

							list.add(dto);
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = list.size();
		System.out.println("Out ReportingDAO getInterIntraState===>" + size);
		if (size > 0) {
			return (CustomerBusinessAnalysisDTO[]) list
					.toArray(new CustomerBusinessAnalysisDTO[size]);
		}
		return null;

	}

	
	public CustomerBusinessAnalysisDTO[] getInterIntraStateUnion(Date fromDate,
			Date toDate, String DBHistory) throws Exception {

		System.out.println("In ReportingDAO getInterIntraState===>");
		Connection con = null;
		PreparedStatement ps = null;
		DBHelper helper = new DBHelper();
		ResultSet rs = null;
		ArrayList<CustomerBusinessAnalysisDTO> list = new ArrayList<CustomerBusinessAnalysisDTO>();

		CustomerBusinessAnalysisDTO dto = null;

		try {
			con = helper.getConnection();

			if (null != con) {

				SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
				String formattedDate = format.format(fromDate);
				String tofomattedDate = format.format(toDate);

				ps = con.prepareStatement(GET_INTER_INTRA_UNION);
				ps.setString(1, formattedDate);
				ps.setString(2, tofomattedDate);
				ps.setString(3, formattedDate);
				ps.setString(4, tofomattedDate);


				if (ps != null) {
					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {
							dto = new CustomerBusinessAnalysisDTO();

							dto.setFromStation(rs.getString("fromState"));
							dto.setCustomerName(rs.getString("toState"));
							dto.setBasic_freight(rs.getFloat(BASIC_FRIEGHT));
							dto.setTotal_freight(rs.getFloat(TOTAL));
							dto.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
							dto.setCharged_weight(rs.getFloat(CHARGED_WEIGHT));
							dto.setNoa(rs.getInt(NO_OF_ARTICLES));
							dto.setCount(rs.getInt(COUNT));

							list.add(dto);
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = list.size();
		System.out.println("Out ReportingDAO getInterIntraState===>" + size);
		if (size > 0) {
			return (CustomerBusinessAnalysisDTO[]) list
					.toArray(new CustomerBusinessAnalysisDTO[size]);
		}
		return null;

	}

	
	public CustomerBusinessAnalysisDTO[] getInterIntraStateHistory(
			Date fromDate, Date toDate, String dbHis) throws Exception {

		System.out.println("In ReportingDAO getInterIntraState===>");
		Connection con = null;
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		ArrayList<CustomerBusinessAnalysisDTO> list = new ArrayList<CustomerBusinessAnalysisDTO>();

		CustomerBusinessAnalysisDTO dto = null;

		try {
			if (dbHis == null || dbHis.equals("")) {
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			} else {
				DBHelperHist helperHis = new DBHelperHist();
				con = helperHis.getConnectionHistory(dbHis);
			}

			if (null != con) {

				SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
				String formattedDate = format.format(fromDate);
				String tofomattedDate = format.format(toDate);

				ps = con.prepareStatement(GET_INTER_INTRA_HISTORY);
				ps.setString(1, formattedDate);
				ps.setString(2, tofomattedDate);

				if (ps != null) {
					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {
							dto = new CustomerBusinessAnalysisDTO();

							dto.setFromStation(rs.getString("fromState"));
							dto.setCustomerName(rs.getString("toState"));
							dto.setBasic_freight(rs.getFloat(BASIC_FRIEGHT));
							dto.setTotal_freight(rs.getFloat(TOTAL));
							dto.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
							dto.setCharged_weight(rs.getFloat(CHARGED_WEIGHT));
							dto.setNoa(rs.getInt(NO_OF_ARTICLES));
							dto.setCount(rs.getInt(COUNT));

							list.add(dto);
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = list.size();
		System.out.println("Out ReportingDAO getInterIntraState===>" + size);
		if (size > 0) {
			return (CustomerBusinessAnalysisDTO[]) list
					.toArray(new CustomerBusinessAnalysisDTO[size]);
		}
		return null;

	}

	public MissingCustomersDTO[] getMissingCustomers() throws Exception {

		System.out.println("In ReportingDAO getMissingCustomers===>");
		Connection con = null;
		PreparedStatement ps = null;
		DBHelper helper = new DBHelper();
		ResultSet rs = null;
		ArrayList<MissingCustomersDTO> list = new ArrayList<MissingCustomersDTO>();

		MissingCustomersDTO dto = null;

		try {
			con = helper.getConnection();

			if (null != con) {

				ps = con.prepareStatement(GET_MISSING_CUSTOMER_LAST_MONTH);

				if (ps != null) {
					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {
							dto = new MissingCustomersDTO();
							dto.setCustomer(rs.getString(NAME));
							dto.setLastMonthBft(rs.getFloat(BASIC_FRIEGHT));
							dto.setLastMonthCW(rs.getFloat(CHARGED_WEIGHT));

							list.add(dto);
						}
					}
				}

				ps = con.prepareStatement(GET_MISSING_CUSTOMER_CUR_MONTH);

				if (ps != null) {
					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {
							dto = new MissingCustomersDTO();
							dto.setCustomer(rs.getString(NAME));
							dto.setCurrMonthBft(rs.getFloat(BASIC_FRIEGHT));
							dto.setCurrMonthCW(rs.getFloat(CHARGED_WEIGHT));

							list.add(dto);
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = list.size();
		System.out.println("Out ReportingDAO getMissingCustomers===>" + size);
		if (size > 0) {
			return (MissingCustomersDTO[]) list
					.toArray(new MissingCustomersDTO[size]);
		}
		return null;

	}

	public MissingCustomersDTO[] getMissingCustomersLastYear() throws Exception {

		System.out.println("In ReportingDAO getMissingCustomersLstYr===>");
		Connection con = null;
		PreparedStatement ps = null;
		DBHelper helper = new DBHelper();
		ResultSet rs = null;
		ArrayList<MissingCustomersDTO> list = new ArrayList<MissingCustomersDTO>();

		MissingCustomersDTO dto = null;

		try {
			con = helper.getConnection();

			if (null != con) {

				ps = con.prepareStatement(GET_MISSING_CUSTOMER_YEAR);

				if (ps != null) {
					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {
							dto = new MissingCustomersDTO();

							dto.setCustomer(rs.getString(NAME));
							dto.setMaxBft(rs.getFloat(BASIC_FRIEGHT));
							dto.setMaxCW(rs.getFloat(CHARGED_WEIGHT));

							// date
							dto.setMaxBftDate(rs.getDate(LR_DATE));

							list.add(dto);
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = list.size();
		System.out.println("Out ReportingDAO getMissingCustomersLstYr===>"
				+ size);
		if (size > 0) {
			return (MissingCustomersDTO[]) list
					.toArray(new MissingCustomersDTO[size]);
		}
		return null;

	}

	private ArrayList<MissingCustomersDTO> getMissingCustomerYear(
			Connection con, ArrayList<MissingCustomersDTO> list)
			throws Exception {

		PreparedStatement ps = null;
		ResultSet rs = null;

		String customer = "";

		ps = con.prepareStatement(GET_MISSING_CUSTOMER_YEAR);
		//MissingCustomersDTO dto = null;

		if (ps != null) {
			rs = ps.executeQuery();
			if (null != rs) {
				while (rs.next()) {
					customer = rs.getString("name");
					for (int i = 0; i < list.size(); i++) {
						if (list.get(i).getLastMonthBft() > 0
								&& customer.equalsIgnoreCase(list.get(i)
										.getCustomer())) {
							list.get(i).setMaxBft(rs.getFloat("maxBft"));
							list.get(i).setMinBft(rs.getFloat("minBft"));
							list.get(i).setMaxCW(rs.getFloat("maxCW"));
							list.get(i).setMinCW(rs.getFloat("minCW"));
							// date list.get(i).setMaxBftDate(rs.getDate(LR_DATE));
						}
					}
				}
			}
		}

		return list;
	}

	public DRSAttendanceDTO[] getDRSAttendance(int type, String branch_code,
			int month, int year,String dbHis) throws RemoteException, BusinessException,
			Exception {
		Connection con = null;
		
		
		
		PreparedStatement ps = null;
		//DBHelper helper = new DBHelper();
		ResultSet rs = null;
		ArrayList<DRSAttendanceDTO> drsAttendanceList = new ArrayList<DRSAttendanceDTO>();

		DRSAttendanceDTO dto = null;
		String from_date = year + "-" + month + "-" + "01";
		String to_date = year + "-" + month + "-" + "31";

		int[][] dates = new int[3][2];

		dates[0][0] = month;
		dates[0][1] = year;

		if (month != 1) {
			month = month - 1;

		} else {
			month = 12;
			year = year - 1;
		}

		dates[1][0] = month;
		dates[1][1] = year;

		if (month != 1) {
			month = month - 1;

		} else {
			month = 12;
			year = year - 1;
		}

		dates[2][0] = month;
		dates[2][1] = year;

		try {
			//con = helper.getConnection();
			if (dbHis == null || dbHis.equals("")) {
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			} else {
				DBHelperHist helperHis = new DBHelperHist();
				con = helperHis.getConnectionHistory(dbHis);
			}

			if (null != con) {
				if (type == 1)
					ps = con.prepareStatement(GET_DRS_ATTENDANCE);
				else
					ps = con.prepareStatement(GET_DRS_ATTENDANCE_SUMMARY);

				if (ps != null) {

					ps.setString(1, branch_code);
					if (type == 1) {
						ps.setInt(2, dates[0][0]);
						ps.setInt(3, dates[0][1]);
						ps.setInt(4, dates[1][0]);
						ps.setInt(5, dates[1][1]);
						ps.setInt(6, dates[2][0]);
						ps.setInt(7, dates[2][1]);
					} else {
						ps.setString(2, from_date);
						ps.setString(3, to_date);
					}

					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {

							dto = new DRSAttendanceDTO();
							dto.setCount(rs.getInt(COUNT));
							dto.setStation_code(rs.getString(STATION_CODE));
							dto.setDrs_date(rs.getDate(DRS_DATE));
							dto.setStatus(rs.getString(STATUS));

							drsAttendanceList.add(dto);
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = drsAttendanceList.size();

		if (size > 0) {
			return (DRSAttendanceDTO[]) drsAttendanceList
					.toArray(new DRSAttendanceDTO[size]);
		}
		return null;

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
	public BookingDTO[] getOpenLRS(Date fromDate, Date toDate,
			String branchCode, String DBHistory) throws RemoteException,
			BusinessException, Exception {

		Connection con = null;
		PreparedStatement ps = null;
		DBHelper helper = new DBHelper();
		ResultSet rs = null;
		ArrayList<BookingDTO> openLRList = new ArrayList<BookingDTO>();

		BookingDTO dto = null;

		try {
			con = helper.getConnection();

			if (null != con) {

				ps = con.prepareStatement(GET_OPEN_LR);

				if (ps != null) {

					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(fromDate);
					String toformattedDate = format.format(toDate);

					ps.setString(1, branchCode);
					ps.setString(2, formattedDate);
					ps.setString(3, toformattedDate);

					rs = ps.executeQuery();
					if (null != rs) {

						while (rs.next()) {

							dto = new BookingDTO();

							dto.setFrom(rs.getString(FROM_STATION));
							dto.setTo(rs.getString(TO_STATION));
							dto.setLrno(rs.getString(LR_NO));
							dto.setDate(rs.getDate(LR_DATE));
							dto.setType(rs.getString(LR_TYPE));
							dto.setConsignorName(rs.getString(CONSIGNOR_NAME));
							dto.setConsigneeName(rs.getString(CONSIGNEE_NAME));
							dto.setCreatedby(rs.getString(BRANCH_CODE));
							dto.setNo_of_articles(rs.getInt(DISCOUNT));
							dto.setActual_bft(rs.getFloat(ACTUAL_BFT));
							// dto.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
							dto.setBft(rs.getFloat(BASIC_FRIEGHT));
							dto.setCcc(rs.getFloat(CCC));
							dto.setOther_charges(rs.getFloat(OTHER_CHARGES));
							dto.setDdc(rs.getFloat(DDC));
							dto.setTotal(rs.getFloat(TOTAL));
							dto.setCharged_weight(rs.getFloat(CHARGED_WEIGHT));

							openLRList.add(dto);
						}

					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = openLRList.size();

		if (size > 0) {
			return (BookingDTO[]) openLRList.toArray(new BookingDTO[size]);
		}
		return null;

	}

	
	public BookingDTO[] getOpenLRSUnion(Date fromDate, Date toDate,
			String branchCode, String DBHistory) throws RemoteException,
			BusinessException, Exception {

		Connection con = null;
		PreparedStatement ps = null;
		DBHelper helper = new DBHelper();
		ResultSet rs = null;
		ArrayList<BookingDTO> openLRList = new ArrayList<BookingDTO>();

		BookingDTO dto = null;

		try {
			con = helper.getConnection();

			if (null != con) {

				ps = con.prepareStatement(GET_OPEN_LR_UNION);

				if (ps != null) {

					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(fromDate);
					String toformattedDate = format.format(toDate);

					ps.setString(1, branchCode);
					ps.setString(2, formattedDate);
					ps.setString(3, toformattedDate);
					ps.setString(4, branchCode);
					ps.setString(5, formattedDate);
					ps.setString(6, toformattedDate);

					rs = ps.executeQuery();
					if (null != rs) {

						while (rs.next()) {

							dto = new BookingDTO();

							dto.setFrom(rs.getString(FROM_STATION));
							dto.setTo(rs.getString(TO_STATION));
							dto.setLrno(rs.getString(LR_NO));
							dto.setDate(rs.getDate(LR_DATE));
							dto.setType(rs.getString(LR_TYPE));
							dto.setConsignorName(rs.getString(CONSIGNOR_NAME));
							dto.setConsigneeName(rs.getString(CONSIGNEE_NAME));
							dto.setCreatedby(rs.getString(BRANCH_CODE));
							dto.setNo_of_articles(rs.getInt(DISCOUNT));
							dto.setActual_bft(rs.getFloat(ACTUAL_BFT));
							// dto.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
							dto.setBft(rs.getFloat(BASIC_FRIEGHT));
							dto.setCcc(rs.getFloat(CCC));
							dto.setOther_charges(rs.getFloat(OTHER_CHARGES));
							dto.setDdc(rs.getFloat(DDC));
							dto.setTotal(rs.getFloat(TOTAL));
							dto.setCharged_weight(rs.getFloat(CHARGED_WEIGHT));

							openLRList.add(dto);
						}

					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = openLRList.size();

		if (size > 0) {
			return (BookingDTO[]) openLRList.toArray(new BookingDTO[size]);
		}
		return null;

	}

	
	public BookingDTO[] getOpenLRSHistory(Date fromDate, Date toDate,
			String branchCode, String dbHis) throws RemoteException,
			BusinessException, Exception {

		Connection con = null;
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		ArrayList<BookingDTO> openLRList = new ArrayList<BookingDTO>();

		BookingDTO dto = null;

		try {
			if (dbHis == null || dbHis.equals("")) {
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			} else {
				DBHelperHist helperHis = new DBHelperHist();
				con = helperHis.getConnectionHistory(dbHis);
			}

			if (null != con) {

				ps = con.prepareStatement(GET_OPEN_LR_HISTORY);

				if (ps != null) {

					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(fromDate);
					String toformattedDate = format.format(toDate);

					ps.setString(1, branchCode);
					ps.setString(2, formattedDate);
					ps.setString(3, toformattedDate);

					rs = ps.executeQuery();
					if (null != rs) {

						while (rs.next()) {

							dto = new BookingDTO();

							dto.setFrom(rs.getString(FROM_STATION));
							dto.setTo(rs.getString(TO_STATION));
							dto.setLrno(rs.getString(LR_NO));
							dto.setDate(rs.getDate(LR_DATE));
							dto.setType(rs.getString(LR_TYPE));
							dto.setConsignorName(rs.getString(CONSIGNOR_NAME));
							dto.setConsigneeName(rs.getString(CONSIGNEE_NAME));
							dto.setCreatedby(rs.getString(BRANCH_CODE));
							dto.setNo_of_articles(rs.getInt(DISCOUNT));
							dto.setActual_bft(rs.getFloat(ACTUAL_BFT));
							// dto.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
							dto.setBft(rs.getFloat(BASIC_FRIEGHT));
							dto.setCcc(rs.getFloat(CCC));
							dto.setOther_charges(rs.getFloat(OTHER_CHARGES));
							dto.setDdc(rs.getFloat(DDC));
							dto.setTotal(rs.getFloat(TOTAL));
							dto.setCharged_weight(rs.getFloat(CHARGED_WEIGHT));

							openLRList.add(dto);
						}

					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = openLRList.size();

		if (size > 0) {
			return (BookingDTO[]) openLRList.toArray(new BookingDTO[size]);
		}
		return null;

	}

	/**
	 * 
	 */
	public BookingDTO[] getFOCLRS(Date from_date, Date to_date,
			String branch_code, int type, String DBHistory)
			throws RemoteException, BusinessException, Exception {

		Connection con = null;
		PreparedStatement ps = null;
		DBHelper helper = new DBHelper();
		ResultSet rs = null;
		ArrayList<BookingDTO> focLRList = new ArrayList<BookingDTO>();

		BookingDTO dto = null;

		try {
			con = helper.getConnection();

			if (null != con) {
				if (type == 1)
					ps = con.prepareStatement(GET_FOC_LR_SUMMARY);
				else
					ps = con.prepareStatement(GET_FOC_LR_DETAIL);

				if (ps != null) {

					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(from_date);
					String toformattedDate = format.format(to_date);

					ps.setString(1, branch_code);
					ps.setString(2, formattedDate);
					ps.setString(3, toformattedDate);

					rs = ps.executeQuery();
					if (null != rs) {

						if (type == 0)
							while (rs.next()) {

								dto = new BookingDTO();

								dto.setLrno(rs.getString(LR_NO));
								dto.setDate(rs.getDate(LR_DATE));
								dto.setFrom(rs.getString(FROM_STATION));
								dto.setTo(rs.getString(TO_STATION));
								dto
										.setNo_of_articles(rs
												.getInt(NO_OF_ARTICLES));
								dto.setCharged_weight(rs
										.getFloat(CHARGED_WEIGHT));
								dto.setArticle_id(rs.getInt(ARTICLE_ID));
								dto
										.setArticle_value(rs
												.getFloat(ARTICLE_VALUE));

								focLRList.add(dto);
							}
						else

							while (rs.next()) {

								dto = new BookingDTO();

								dto.setFrom(rs.getString(FROM_STATION));
								dto.setTo(rs.getString(BRANCH_CODE));
								dto.setNo_of_articles(rs.getInt(COUNT));
								dto
										.setActual_weight(rs
												.getFloat(CHARGED_WEIGHT));
								dto.setRate_type(rs.getInt(RATE_TYPE));

								focLRList.add(dto);
							}

					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = focLRList.size();

		if (size > 0) {
			return (BookingDTO[]) focLRList.toArray(new BookingDTO[size]);
		}
		return null;

	}
	
	
	public BookingDTO[] getFOCLRSUnion(Date from_date, Date to_date,
			String branch_code, int type, String DBHistory)
			throws RemoteException, BusinessException, Exception {

		Connection con = null;
		PreparedStatement ps = null;
		DBHelper helper = new DBHelper();
		ResultSet rs = null;
		ArrayList<BookingDTO> focLRList = new ArrayList<BookingDTO>();

		BookingDTO dto = null;

		try {
			con = helper.getConnection();

			if (null != con) {
				if (type == 1)
					ps = con.prepareStatement(GET_FOC_LR_SUMMARY_UNION);
				else
					ps = con.prepareStatement(GET_FOC_LR_DETAIL_UNION);

				if (ps != null) {

					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(from_date);
					String toformattedDate = format.format(to_date);

					ps.setString(1, branch_code);
					ps.setString(2, formattedDate);
					ps.setString(3, toformattedDate);
					ps.setString(4, branch_code);
					ps.setString(5, formattedDate);
					ps.setString(6, toformattedDate);

					rs = ps.executeQuery();
					if (null != rs) {

						if (type == 0)
							while (rs.next()) {

								dto = new BookingDTO();

								dto.setLrno(rs.getString(LR_NO));
								dto.setDate(rs.getDate(LR_DATE));
								dto.setFrom(rs.getString(FROM_STATION));
								dto.setTo(rs.getString(TO_STATION));
								dto
										.setNo_of_articles(rs
												.getInt(NO_OF_ARTICLES));
								dto.setCharged_weight(rs
										.getFloat(CHARGED_WEIGHT));
								dto.setArticle_id(rs.getInt(ARTICLE_ID));
								dto
										.setArticle_value(rs
												.getFloat(ARTICLE_VALUE));

								focLRList.add(dto);
							}
						else

							while (rs.next()) {

								dto = new BookingDTO();

								dto.setFrom(rs.getString(FROM_STATION));
								dto.setTo(rs.getString(BRANCH_CODE));
								dto.setNo_of_articles(rs.getInt(COUNT));
								dto
										.setActual_weight(rs
												.getFloat(CHARGED_WEIGHT));
								dto.setRate_type(rs.getInt(RATE_TYPE));

								focLRList.add(dto);
							}

					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = focLRList.size();

		if (size > 0) {
			return (BookingDTO[]) focLRList.toArray(new BookingDTO[size]);
		}
		return null;

	}


	public BookingDTO[] getFOCLRSHistory(Date from_date, Date to_date,
			String branch_code, int type, String dbHis)
			throws RemoteException, BusinessException, Exception {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<BookingDTO> focLRList = new ArrayList<BookingDTO>();

		BookingDTO dto = null;

		try {
			if (dbHis == null || dbHis.equals("")) {
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			} else {
				DBHelperHist helperHis = new DBHelperHist();
				con = helperHis.getConnectionHistory(dbHis);
			}

			if (null != con) {
				if (type == 1)
					ps = con.prepareStatement(GET_FOC_LR_SUMMARY_HISTORY);
				else
					ps = con.prepareStatement(GET_FOC_LR_DETAIL_HISTORY);

				if (ps != null) {

					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedDate = format.format(from_date);
					String toformattedDate = format.format(to_date);

					ps.setString(1, branch_code);
					ps.setString(2, formattedDate);
					ps.setString(3, toformattedDate);

					rs = ps.executeQuery();
					if (null != rs) {

						if (type == 0)
							while (rs.next()) {

								dto = new BookingDTO();

								dto.setLrno(rs.getString(LR_NO));
								dto.setDate(rs.getDate(LR_DATE));
								dto.setFrom(rs.getString(FROM_STATION));
								dto.setTo(rs.getString(TO_STATION));
								dto
										.setNo_of_articles(rs
												.getInt(NO_OF_ARTICLES));
								dto.setCharged_weight(rs
										.getFloat(CHARGED_WEIGHT));
								dto.setArticle_id(rs.getInt(ARTICLE_ID));
								dto
										.setArticle_value(rs
												.getFloat(ARTICLE_VALUE));

								focLRList.add(dto);
							}
						else

							while (rs.next()) {

								dto = new BookingDTO();

								dto.setFrom(rs.getString(FROM_STATION));
								dto.setTo(rs.getString(BRANCH_CODE));
								dto.setNo_of_articles(rs.getInt(COUNT));
								dto
										.setActual_weight(rs
												.getFloat(CHARGED_WEIGHT));
								dto.setRate_type(rs.getInt(RATE_TYPE));

								focLRList.add(dto);
							}

					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = focLRList.size();

		if (size > 0) {
			return (BookingDTO[]) focLRList.toArray(new BookingDTO[size]);
		}
		return null;

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

		Connection con = null;
		PreparedStatement ps = null;
		//DBHelper helper = new DBHelper();
		ResultSet rs = null;
		ArrayList<MarketVehicleDTO> marketVehicleList = new ArrayList<MarketVehicleDTO>();

		MarketVehicleDTO dto = null;

		try {
			//con = helper.getConnection();
			if (dbHis == null || dbHis.equals("")) {
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			} else {
				DBHelperHist helperHis = new DBHelperHist();
				con = helperHis.getConnectionHistory(dbHis);
			}


			if (null != con) {
				if(branch_code.equalsIgnoreCase("%")) {
					ps = con.prepareStatement(GET_MARKET_VEHICLE);
				}
				else{
					ps = con.prepareStatement(GET_MARKET_VEHICLE_BRANCH);
				}

				if (ps != null) {

					ps.setString(1, branch_code);
					ps.setInt(2, month);
					ps.setInt(3, year);

					rs = ps.executeQuery();
					if (null != rs) {

						while (rs.next()) {

							dto = new MarketVehicleDTO();

							dto.setTo_station(rs.getString(TO_STATION));
							dto.setNo_vehicle(rs.getInt(COUNT));
							dto.setVehicle_name(rs.getString(MODEL_NO));
							dto.setAmount(rs.getFloat(RATE));

							marketVehicleList.add(dto);
						}

					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = marketVehicleList.size();
		System.out.println("Out MarketvehicleDAO getMarketvehiclesLst===>"
				+ size);
		if (size > 0) {
			return (MarketVehicleDTO[]) marketVehicleList
					.toArray(new MarketVehicleDTO[size]);
		}
		return null;

	}

	/*
	 * 
	 */
	/*public InwardAnalysisDTO[] getLOG(Date from_date, Date to_date,
			String fromStn, String inwardStn,String dbHis) throws RemoteException, Exception {
		System.out.println("In ReportingDAO getLog--->");
		Connection con = null;
		PreparedStatement ps = null;
		//DBHelper helper = new DBHelper();
		ResultSet rs = null;
		ArrayList<InwardAnalysisDTO> list = new ArrayList<InwardAnalysisDTO>();
		InwardAnalysisDTO dto = null;
		try {
			//con = helper.getConnection();
			if (dbHis == null || dbHis.equals("")) {
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			} else {
				DBHelperHist helperHis = new DBHelperHist();
				con = helperHis.getConnectionHistory(dbHis);
			}
			if (null != con) {

				SimpleDateFormat format = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");

				long diffInMilleseconds = to_date.getTime()
						- from_date.getTime();
				long diffInSeconds = diffInMilleseconds / 1000;
				long diffInMinutes = diffInSeconds / 60;
				long diffInHours = diffInMinutes / 60;
				long diffInDays = diffInHours / 24;

				Calendar c1 = Calendar.getInstance();
				c1.setTime(from_date);
				String join = null;
				for (int nod = 0; nod <= diffInDays; nod++) {
					String from_in = format.format(c1.getTime());
					c1.add(Calendar.DATE, 1);
					
					if (null != join)
						join = join + " OR (a.out_time>= '" + from_in
								+ "'  AND a.in_time<='" + from_in + "') ";
					else
						join = " OR (a.out_time>='" + from_in
								+ "' AND a.in_time<='" + from_in + "') ";
				
				
				
				}

				if (fromStn.equalsIgnoreCase("All")
						&& inwardStn.equalsIgnoreCase("All")) {
					String query = "select sum(a.count) as branchCount,sum(a.basic_freight) as basFrt, "
							+ "sum(a.total_freight) as totalFrt, sum(a.actual_weight) as actWt,sum(a.charged_weight) as "
							+ "chrgWt, sum(a.noa) as noArt, b.branch_code as fromBranch,c.branch_code as toBranch "
							+ "from left_over_goods a,station b, station c where b.station_code=a.from_station and "
							+ "c.station_code=a.to_station and (a.out_time='0000-00-00 00:00:00' ";
					if (join != null)
						query = query + join;
					query = query + ") group by fromBranch,toBranch";

					ps = con.prepareStatement(query);
					rs = ps.executeQuery();

					while (rs.next()) {
						dto = new InwardAnalysisDTO();
						dto.setFromStation(rs.getString("fromBranch"));
						dto.setInwardStation(rs.getString("toBranch"));
						dto.setCount(rs.getInt("branchCount"));
						dto.setBasic_freight(rs.getFloat("basFrt"));
						dto.setTotal_freight(rs.getFloat("totalFrt"));
						dto.setActual_weight(rs.getFloat("actWt"));
						dto.setCharged_weight(rs.getFloat("chrgWt"));
						dto.setNoa(rs.getInt("noArt"));
						list.add(dto);
					}

				} else if (!fromStn.equalsIgnoreCase("All")
						&& inwardStn.equalsIgnoreCase("All")) {

					String query = "select sum(a.count) as branchCount, sum(a.basic_freight) as basFrt, "
							+ "sum(a.total_freight) as totalFrt, sum(a.actual_weight) as actWt, "
							+ "sum(a.charged_weight) as chrgWt, sum(a.noa) as noArt, a.from_station, "
							+ "c.branch_code as toBranch from left_over_goods a,station b, station c "
							+ "where b.station_code=a.from_station and c.station_code=a.to_station and "
							+ "(a.out_time='0000-00-00 00:00:00'";
					if (join != null)
						query = query + join;
					query = query + ") group by from_station,toBranch";

					ps = con.prepareStatement(query);
					rs = ps.executeQuery();

					while (rs.next()) {
						dto = new InwardAnalysisDTO();
						dto.setFromStation(rs.getString("from_station"));
						dto.setInwardStation(rs.getString("toBranch"));
						dto.setCount(rs.getInt("branchCount"));
						dto.setBasic_freight(rs.getFloat("basFrt"));
						dto.setTotal_freight(rs.getFloat("totalFrt"));
						dto.setActual_weight(rs.getFloat("actWt"));
						dto.setCharged_weight(rs.getFloat("chrgWt"));
						dto.setNoa(rs.getInt("noArt"));
						list.add(dto);
					}

				} else if (fromStn.equalsIgnoreCase("All")
						&& !inwardStn.equalsIgnoreCase("All")) {

					String query = "select sum(a.count) as branchCount, sum(a.basic_freight) as basFrt, "
							+ "sum(a.total_freight) as totalFrt, sum(a.actual_weight) as actWt, "
							+ "sum(a.charged_weight) as chrgWt, sum(a.noa) as noArt, b.branch_code as "
							+ "fromBranch, a.to_station from left_over_goods a,station b, station c where "
							+ "b.station_code=a.from_station and c.station_code=a.to_station and "
							+ "(a.out_time='0000-00-00 00:00:00' ";
					if (join != null)
						query = query + join;
					query = query + ") group by fromBranch,to_station";

					ps = con.prepareStatement(query);
					rs = ps.executeQuery();

					while (rs.next()) {
						dto = new InwardAnalysisDTO();
						dto.setFromStation(rs.getString("fromBranch"));
						dto.setInwardStation(rs.getString("to_station"));
						dto.setCount(rs.getInt("branchCount"));
						dto.setBasic_freight(rs.getFloat("basFrt"));
						dto.setTotal_freight(rs.getFloat("totalFrt"));
						dto.setActual_weight(rs.getFloat("actWt"));
						dto.setCharged_weight(rs.getFloat("chrgWt"));
						dto.setNoa(rs.getInt("noArt"));
						list.add(dto);
					}

				} else {

					String query = "select sum(count) as count, sum(basic_freight) as basic_freight , sum(total_freight) as total_freight , " +
							"sum(actual_weight) as actual_weight ,sum(charged_weight) as charged_weight, " +
							" sum(noa) as noa,from_station,to_station FROM left_over_goods a where "
							+ "(a.out_time='0000-00-00 00:00:00' ";
					if (join != null)
						query = query + join;
					query = query + ") group by from_station,to_station";

					ps = con.prepareStatement(query);
					rs = ps.executeQuery();

					while (rs.next()) {
						dto = new InwardAnalysisDTO();
						dto.setFromStation(rs.getString("from_station"));
						dto.setInwardStation(rs.getString("to_station"));
						dto.setCount(rs.getInt("count"));
						dto.setBasic_freight(rs.getFloat("basic_freight"));
						dto.setTotal_freight(rs.getFloat("total_freight"));
						dto.setActual_weight(rs.getFloat("actual_weight"));
						dto.setCharged_weight(rs.getFloat("charged_weight"));
						dto.setNoa(rs.getInt("noa"));
						list.add(dto);
					}
				}

			}
			
			
			
						
			

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = list.size();
		System.out.println("Out ReportingDAO getLog--->"+size);
		if (size > 0) {
			return (InwardAnalysisDTO[]) list
					.toArray(new InwardAnalysisDTO[size]);
		}

		return null;

	}*/
	
	public InwardAnalysisDTO[] getLOG(Date from_date, Date to_date,
			String fromStn, String inwardStn,String dbHis) throws RemoteException, Exception {
		System.out.println("In ReportingDAO getLog--->");
		Connection con = null;
		PreparedStatement ps = null;
		//DBHelper helper = new DBHelper();
		ResultSet rs = null;
		ArrayList<InwardAnalysisDTO> list = new ArrayList<InwardAnalysisDTO>();
		InwardAnalysisDTO dto = null;
		try {
			//con = helper.getConnection();
			if (dbHis == null || dbHis.equals("")) {
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			} else {
				DBHelperHist helperHis = new DBHelperHist();
				con = helperHis.getConnectionHistory(dbHis);
			}
			if (null != con) {

				SimpleDateFormat format = new SimpleDateFormat(
						"yyyy-MM-dd");

				String FromDate = format.format(from_date);
				String ToDate = format.format(to_date);

				if (fromStn.equalsIgnoreCase("All")
						&& inwardStn.equalsIgnoreCase("All")) {
					String query = " select sum(a.count) as branchCount,sum(a.basic_freight) as basFrt, " +
							" sum(a.total_freight) as totalFrt, sum(a.actual_weight) as actWt,sum(a.charged_weight) as " +
							" chrgWt, sum(a.noa) as noArt, b.branch_code as fromBranch,c.branch_code as toBranch " +
							" from insert_left_over_goods a,station b,station c where b.station_code=a.Station_code and " +
							" c.station_code=a.to_station and date(a.lr_date) between '" +
							FromDate +
							"' and '" +
							ToDate +
							"' group by fromBranch,toBranch";

					ps = con.prepareStatement(query);
					rs = ps.executeQuery();

					while (rs.next()) {
						dto = new InwardAnalysisDTO();
						dto.setFromStation(rs.getString("fromBranch"));
						dto.setInwardStation(rs.getString("toBranch"));
						dto.setCount(rs.getInt("branchCount"));
						dto.setBasic_freight(rs.getFloat("basFrt"));
						dto.setTotal_freight(rs.getFloat("totalFrt"));
						dto.setActual_weight(rs.getFloat("actWt"));
						dto.setCharged_weight(rs.getFloat("chrgWt"));
						dto.setNoa(rs.getInt("noArt"));
						list.add(dto);
					}

				} else if (!fromStn.equalsIgnoreCase("All")
						&& inwardStn.equalsIgnoreCase("All")) {

					String query =  " select sum(a.count) as branchCount,sum(a.basic_freight) as basFrt, " +
								" sum(a.total_freight) as totalFrt, sum(a.actual_weight) as actWt,sum(a.charged_weight) as " +
								" chrgWt, sum(a.noa) as noArt, b.station_code as from_station,c.branch_code as toBranch " +
								" from insert_left_over_goods a,station b,station c where b.station_code=a.Station_code and " +
								" c.station_code=a.to_station and date(a.lr_date) between '" +
								FromDate +
								"' and '" +
								ToDate +
								"' group by from_station,toBranch";
					ps = con.prepareStatement(query);
					rs = ps.executeQuery();

					while (rs.next()) {
						dto = new InwardAnalysisDTO();
						dto.setFromStation(rs.getString("from_station"));
						dto.setInwardStation(rs.getString("toBranch"));
						dto.setCount(rs.getInt("branchCount"));
						dto.setBasic_freight(rs.getFloat("basFrt"));
						dto.setTotal_freight(rs.getFloat("totalFrt"));
						dto.setActual_weight(rs.getFloat("actWt"));
						dto.setCharged_weight(rs.getFloat("chrgWt"));
						dto.setNoa(rs.getInt("noArt"));
						list.add(dto);
					}

				} else if (fromStn.equalsIgnoreCase("All")
						&& !inwardStn.equalsIgnoreCase("All")) {

					String query = " select sum(a.count) as branchCount,sum(a.basic_freight) as basFrt, " +
					" sum(a.total_freight) as totalFrt, sum(a.actual_weight) as actWt,sum(a.charged_weight) as " +
					" chrgWt, sum(a.noa) as noArt, b.branch_code as fromBranch,c.station_code as to_station " +
					" from insert_left_over_goods a,station b,station c where b.station_code=a.Station_code and " +
					" c.station_code=a.to_station and date(a.lr_date) between '" +
					FromDate +
					"' and '" +
					ToDate +
					"' group by fromBranch,to_station";
				

					ps = con.prepareStatement(query);
					rs = ps.executeQuery();

					while (rs.next()) {
						dto = new InwardAnalysisDTO();
						dto.setFromStation(rs.getString("fromBranch"));
						dto.setInwardStation(rs.getString("to_station"));
						dto.setCount(rs.getInt("branchCount"));
						dto.setBasic_freight(rs.getFloat("basFrt"));
						dto.setTotal_freight(rs.getFloat("totalFrt"));
						dto.setActual_weight(rs.getFloat("actWt"));
						dto.setCharged_weight(rs.getFloat("chrgWt"));
						dto.setNoa(rs.getInt("noArt"));
						list.add(dto);
					}

				} else {

					String query = " select sum(a.count) as count,sum(a.basic_freight) as basic_freight, " +
						" sum(a.total_freight) as total_freight, sum(a.actual_weight) as actual_weight,sum(a.charged_weight) as " +
						" charged_weight, sum(a.noa) as noa, b.station_code as from_station,c.station_code as to_station  " +
						" from insert_left_over_goods a,station b,station c where b.station_code=a.Station_code and " +
						" c.station_code=a.to_station and date(a.lr_date) between '" +
						FromDate +
						"' and '" +
						ToDate +
						"' group by from_station,to_station";
				
					ps = con.prepareStatement(query);
					rs = ps.executeQuery();

					while (rs.next()) {
						dto = new InwardAnalysisDTO();
						dto.setFromStation(rs.getString("from_station"));
						dto.setInwardStation(rs.getString("to_station"));
						dto.setCount(rs.getInt("count"));
						dto.setBasic_freight(rs.getFloat("basic_freight"));
						dto.setTotal_freight(rs.getFloat("total_freight"));
						dto.setActual_weight(rs.getFloat("actual_weight"));
						dto.setCharged_weight(rs.getFloat("charged_weight"));
						dto.setNoa(rs.getInt("noa"));
						list.add(dto);
					}
				}

			}
			
			
			
						
			

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = list.size();
		System.out.println("Out ReportingDAO getLog--->"+size);
		if (size > 0) {
			return (InwardAnalysisDTO[]) list.toArray(new InwardAnalysisDTO[size]);
		}

		return null;

	}

	


/*public GMROutTimeDTO[] getInwardRegister(String stationCode, Date from, Date to)
	throws RemoteException, BusinessException, Exception {

		System.out.println("In ReportingDAO getInwardRegister===>");
		Connection con = null;
		PreparedStatement ps = null;
		DBHelper helper = new DBHelper();
		ResultSet rs = null;
		ArrayList<GMROutTimeDTO> inwardRegister = new ArrayList<GMROutTimeDTO>();

		GMROutTimeDTO dto = null;

		try {
			con = helper.getConnection();

			if (null != con) {
				ps = con.prepareStatement(GET_INWARD_REGISTER);

				if (ps != null) {

					SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
					String formattedFromDate = format.format(from);
					String formattedToDate = format.format(to);

					ps.setString(1, stationCode);
					ps.setString(2, formattedFromDate);
					ps.setString(3, formattedToDate);

					ps.setString(4, stationCode);
					ps.setString(5, formattedFromDate);
					ps.setString(6, formattedToDate);

					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {
							
							String status = rs.getString(LR_STATUS);
							int count = rs.getInt(COUNT);
							if (!status.equalsIgnoreCase("ToArrive")) {
								dto = new GMROutTimeDTO();

								dto.setFrom(rs.getString(FROM_STATION));
								dto.setLr_no(rs.getString(LR_NO));
								dto.setLrDate(rs.getDate(LR_DATE));
								dto.setLr_type(rs.getString(LR_TYPE));
								// dto.setTo(rs.getString(STATION_CODE));
								dto.setOutTimeDate(rs.getDate(INWARD_TIME));
								dto.setActual_weight(rs
										.getFloat(CHARGED_WEIGHT));
								dto.setConsignorName(rs
										.getString(CONSIGNOR_NAME));
								dto.setConsigneeName(rs
										.getString(CONSIGNEE_NAME));
								dto.setArticle_type(rs.getString(VEHICLE_NO));
								String VehicleNo = getPreviousVehicleNo(con, dto.getLr_no(), stationCode);
								dto
										.setNo_of_articles(rs
												.getInt(NO_OF_ARTICLES));
								dto.setTotal(rs.getFloat(TOTAL));

								inwardRegister.add(dto);
							}
						}
					}
				}

			}

		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			try {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			}
		}

		int size = inwardRegister.size();
		System.out.println("Out ReportingDAO getInwardRegister===>" + size);
		if (size > 0) {
			return (GMROutTimeDTO[]) inwardRegister
					.toArray(new GMROutTimeDTO[size]);
		}
		
		return null;

	}*/
	


	
	
}