package com.hm.dao;

import hm.akr.dto.ArticleDTO;
import hm.akr.dto.CashDTO;
import hm.akr.dto.CashRegisterDTO;
import hm.akr.dto.LRNumberRangeDTO;
import hm.akr.dto.SMSDTO;
import hm.akr.exceptions.BusinessException;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author HM
 * @version 1.0 Class for CashReceipt
 */
public class CashDAO implements ICashDAO, DAOConstants {

	/**
	 * Constructor Method
	 */
	public CashDAO() {
		super();
	}

	/**
	 * 
	 * @param stationCode
	 * @return
	 * @throws Exception
	 */
	public CashRegisterDTO[] getLRNosForTopay(String stationCode)
			throws Exception {

		Connection con = null;
		PreparedStatement pst = null;
		CashRegisterDTO book = null;
		CashRegisterDTO[] dto = null;
		ResultSet rs = null;
		ArrayList<CashRegisterDTO> cashlist = new ArrayList<CashRegisterDTO>();

		try {

			DBHelper db = new DBHelper();
			con = db.getConnection();

			if (null != con) {
				pst = con.prepareStatement(TOPAY_LR_NOS1);

				if (pst != null) {

					pst.setString(1, stationCode);

					rs = pst.executeQuery();
					DateFormat format = new SimpleDateFormat(DATE_FORMAT);
					if (null != rs) {
						while (rs.next()) {
							book = new CashRegisterDTO();
							book.setLrno(rs.getString(LR_NO)
									+ " | "
									+ format.format(rs
											.getDate(LAST_INWARD_DATE)));
							book.setDate(rs.getDate(LR_DATE));
							String fromStation = rs.getString(FROM_STATION);
							book.setFrom(fromStation);
							String toStation = rs.getString(TO_STATION);
							book.setTo(toStation);
							book.setConsignorName(rs.getString(CONSIGNOR_NAME));
							book.setConsigneeName(rs.getString(CONSIGNEE_NAME));
							book.setNo_of_articles(rs.getInt(NO_OF_ARTICLES));
							book.setCharged_weight(rs.getFloat(CHARGED_WEIGHT));
							book.setDdc(rs.getFloat(DOOR_DELIVERY_CHARGE));
							book.setTotal(rs.getFloat(TOTAL));
							book.setFromMobile(rs.getString(CNOR_PHONE));
							book.setToMobile(rs.getString(CNEE_PHONE));
							book.setRate_type(rs.getInt(RATE_TYPE));
							book.setSms(rs.getInt(SMS_ALERT));
							book.setCreatedby(rs.getString(CR_NO));
							
							book.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
							
							cashlist.add(book);
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
		dto = cashlist.toArray(new CashRegisterDTO[cashlist.size()]);

		return dto;

	}

	/**
	 * 
	 * @param stationCode
	 * @return
	 * @throws Exception
	 */
	public CashRegisterDTO[] getLRNosForBilling(String stationCode)
			throws Exception {
		System.out.println("Inside the method getLRNosForBilling");
		Connection con = null;
		PreparedStatement pst = null;
		CashRegisterDTO book = null;
		CashRegisterDTO[] dto = null;
		ResultSet rs = null;
		ArrayList<CashRegisterDTO> cashlist = new ArrayList<CashRegisterDTO>();
		try {

			DBHelper db = new DBHelper();
			con = db.getConnection();

			if (null != con) {
				pst = con.prepareStatement(BILLING_LR_NOS1);

				if (pst != null) {

					pst.setString(1, stationCode);

					rs = pst.executeQuery();
					DateFormat format = new SimpleDateFormat(DATE_FORMAT);
					if (null != rs) {
						while (rs.next()) {
							book = new CashRegisterDTO();
							book.setLrno(rs.getString(LR_NO)
									+ " | "
									+ format.format(rs
											.getDate(LAST_INWARD_DATE)));
							book.setDate(rs.getDate(LR_DATE));
							String fromStation = rs.getString(FROM_STATION);
							book.setFrom(fromStation);
							String toStation = rs.getString(TO_STATION);
							book.setTo(toStation);
							book.setConsignorName(rs.getString(CONSIGNOR_NAME));
							book.setConsigneeName(rs.getString(CONSIGNEE_NAME));
							book.setNo_of_articles(rs.getInt(NO_OF_ARTICLES));
							book.setCharged_weight(rs.getFloat(CHARGED_WEIGHT));
							book.setDdc(rs.getFloat(DOOR_DELIVERY_CHARGE));
							book.setTotal(rs.getFloat(TOTAL));
							book.setFromMobile(rs.getString(CNOR_PHONE));
							book.setToMobile(rs.getString(CNEE_PHONE));
							book.setRate_type(rs.getInt(RATE_TYPE));
							book.setSms(rs.getInt(SMS_ALERT));
							book.setCreatedby(rs.getString(CR_NO));
							
							book.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));

							cashlist.add(book);
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
		dto = cashlist.toArray(new CashRegisterDTO[cashlist.size()]);
		System.out.println("OUT the method getLRNosForBilling");
		return dto;

		/*
		 * ArrayList<String> lrno = new ArrayList<String>(); DBHelper dbhelper =
		 * new DBHelper(); Connection con = null; PreparedStatement pst = null;
		 * ResultSet rs = null; String lr = null; Date inwardTime = null; try {
		 * con = dbhelper.getConnection(); if (null != con) { pst =
		 * con.prepareStatement(BILLING_LR_NOS); if (null != pst) {
		 * pst.setString(1, stationCode);
		 * 
		 * rs = pst.executeQuery(); if (rs != null) { while (rs.next()) { lr =
		 * rs.getString(LR_NO); inwardTime = rs.getDate(LAST_INWARD_DATE);
		 * DateFormat format = new SimpleDateFormat( DATE_FORMAT); lr = lr + " | " +
		 * format.format(inwardTime); lrno.add(lr); } } } } } catch
		 * (SQLException sqlexception) { throw sqlexception; } catch (Exception
		 * exception) { throw exception; } finally { try { if (null != rs)
		 * rs.close(); if (null != pst) pst.close(); if (null != con)
		 * con.close(); } catch (Exception exception) { if (null != pst) {
		 * pst.close(); } if (null != con) { con.close(); } } }
		 * 
		 * int size = lrno.size(); return (String[]) lrno.toArray(new
		 * String[size]);
		 */}

	/**
	 * 
	 * @param stationCode
	 * @return
	 * @throws Exception
	 */
	public CashRegisterDTO[] getLRNosForPaid(String stationCode)
			throws Exception {

		Connection con = null;
		PreparedStatement pst = null;
		CashRegisterDTO book = null;
		CashRegisterDTO[] dto = null;
		ResultSet rs = null;
		ArrayList<CashRegisterDTO> cashlist = new ArrayList<CashRegisterDTO>();

		try {

			DBHelper db = new DBHelper();
			con = db.getConnection();

			if (null != con) {
				pst = con.prepareStatement(PAID_LR_NOS1);

				if (pst != null) {

					pst.setString(1, stationCode);

					rs = pst.executeQuery();
					DateFormat format = new SimpleDateFormat(DATE_FORMAT);
					if (null != rs) {
						while (rs.next()) {
							book = new CashRegisterDTO();
							book.setLrno(rs.getString(LR_NO)
									+ " | "
									+ format.format(rs
											.getDate(LAST_INWARD_DATE)));
							book.setDate(rs.getDate(LR_DATE));
							String fromStation = rs.getString(FROM_STATION);
							book.setFrom(fromStation);
							String toStation = rs.getString(TO_STATION);
							book.setTo(toStation);
							book.setConsignorName(rs.getString(CONSIGNOR_NAME));
							book.setConsigneeName(rs.getString(CONSIGNEE_NAME));
							book.setNo_of_articles(rs.getInt(NO_OF_ARTICLES));
							book.setCharged_weight(rs.getFloat(CHARGED_WEIGHT));
							book.setDdc(rs.getFloat(DOOR_DELIVERY_CHARGE));
							book.setTotal(rs.getFloat(TOTAL));
							book.setFromMobile(rs.getString(CNOR_PHONE));
							book.setToMobile(rs.getString(CNEE_PHONE));
							book.setRate_type(rs.getInt(RATE_TYPE));
							book.setSms(rs.getInt(SMS_ALERT));
							book.setCreatedby(rs.getString(CR_NO));
							
							book.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));

							cashlist.add(book);
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
		dto = cashlist.toArray(new CashRegisterDTO[cashlist.size()]);

		return dto;

	}

	/**
	 * 
	 */
	private boolean generateCR(CashDTO dto, boolean lastrecord)
			throws SQLException, BusinessException, Exception {
		DBHelper db = new DBHelper();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Timestamp dbSqlTimestamp = null;

		boolean flag = false;
		int result = 0;

		try {
			con = db.getConnection();
			
			if (con != null) {
				ps = con.prepareStatement(getDeliveryDate);
				ps.setString(1, dto.getLr_no());

				rs = ps.executeQuery();
				if (rs.next()) {
					dbSqlTimestamp = rs.getTimestamp("delivered_date");
					flag = true;
				}

			}
			if (flag) {
				ps = null;
				if (con != null) {
					if (lastrecord)
						ps = con.prepareStatement(generateCR1);
					else
						ps = con.prepareStatement(generateCR);

					if (ps != null) {

						ps.setString(1, dto.getCr_no());
						ps.setString(2, dto.getStation_code());
						ps.setString(3, dto.getLr_no());
						ps.setFloat(4, dto.getDemurrage());
						ps.setFloat(5, dto.getUnder_charge());
						ps.setFloat(6, dto.getPostage());
						ps.setFloat(7, dto.getDd_extra());
						ps.setFloat(8, dto.getOther());
						ps.setFloat(9, dto.getTotal_amount());
						ps.setTimestamp(10, dbSqlTimestamp);
						ps.setString(11, dto.getConsigneeName());

						result = ps.executeUpdate();

						if (result > 0) {
							flag = true;							
						}
					}

				}
			}
		} catch (SQLException sqlexception) {
			String message = sqlexception.getMessage();
			if (message.startsWith(DUPLICATE_ENTRY)) {				
				ResultSet rs1 = null;
				String newLastUsed = "";
				ps = con.prepareStatement(GET_LASTUSED_CR_AGAIN);
				if(ps != null){
					ps.setString(1, dto.getStation_code());
					ps.setString(2, dto.getCr_no().substring(0, 1));
					rs1 = ps.executeQuery();
					if(rs1 != null){
						if(rs1.next()){
							newLastUsed = rs1.getString(1);
						}
					}
				}
				
				BusinessException business = new BusinessException();
				business.setResponseMessage(newLastUsed);
				
				throw business;
			} else {
				throw sqlexception;
			}
		} catch (Exception exception) {
			con.rollback();
			throw exception;
		} finally {
			try {
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			}
		}

		return flag;
	}

	/**
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public String[] generateCRList(CashDTO[] dto) throws Exception {

		/*boolean flag = false;		
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < length; i++) {
			if (i == length - 1)
				flag = generateCR(dto[i], true);
			else
				flag = generateCR(dto[i], false);
			if (!flag) {
				list.add(dto[i].getCr_no());
			}
		}*/

		if (null != dto) {
			int length = 0;
			length = dto.length;
			if (length > 0) {
				SMSDTO[] smsdto = dto[0].getSms();
				if (smsdto != null) {
					storeSMSToFuture(smsdto);
				}
			}
		}

		//String[] crno = (String[]) list.toArray(new String[list.size()]);

		return null;
	}

	/**
	 * 
	 * @param smsdto
	 */
	public void storeSMSToFuture(SMSDTO[] smsdto) throws Exception {
		int len = smsdto.length;
		if (len > 0) {

			DBHelper helper = new DBHelper();
			Connection con = null;
			PreparedStatement ps = null;

			try {
				con = helper.getConnection();
				if (null != con) {
					for (int i = 0; i < len; i++) {
						ps = con.prepareStatement(STOR_SMS);
						ps.setString(1, smsdto[i].getCnor_phone());
						ps.setString(2, smsdto[i].getCnor_message());
						ps.addBatch();
						ps.setString(1, smsdto[i].getCnee_phone());
						ps.setString(2, smsdto[i].getCnee_message());
						ps.addBatch();
					}
					ps.executeBatch();

				}

			} catch (Exception exception) {
				throw exception;
			} finally {
				try {
					if (null != ps)
						ps.close();
					if (null != con)
						con.close();
				} catch (Exception exception) {
					if (ps != null)
						ps.close();
					if (con != null)
						con.close();
				}
			}
		}
	}
	
	// Update CR
	public void updateCRList(CashDTO[] dto) throws Exception {
		int length = 0;
		if (null != dto) {
			length = dto.length;
			if (length > 0) {
				for (int i = 0; i < length; i++) {
					updateCR(dto[i]);
				}

				SMSDTO[] smsdto = dto[0].getSms();
				if (smsdto != null) {
					storeSMSToFuture(smsdto);
				}
			}
		}

	}
	
	private boolean updateCR(CashDTO dto) throws Exception {
		DBHelper db = new DBHelper();
		Connection con = null;
		PreparedStatement ps = null;
		boolean flag = false;	

		try {
			con = db.getConnection();			
			con.setAutoCommit(false);
			if (con != null) {
				ps = con.prepareStatement(updateCR);
				if (ps != null) {										
					ps.setFloat(1, dto.getDemurrage());
					ps.setFloat(2, dto.getUnder_charge());
					ps.setFloat(3, dto.getPostage());
					ps.setFloat(4, dto.getDd_extra());
					ps.setFloat(5, dto.getOther());
					ps.setFloat(6, dto.getTotal_amount());		
					ps.setString(7, dto.getConsigneeName());
					ps.setString(8, dto.getCr_no());
					
					ps.executeUpdate();					
				}
				
				updateDeliveryCommission(con, dto.getStation_code(), dto.getLr_no(), dto.getDelivery_ommission());
				con.commit();
			}
			
		} catch (Exception exception) {		
			con.rollback();
			throw exception;
		} finally {
			try {
				if (null != ps)
					ps.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			}
		}

		return flag;
	}

	private void updateDeliveryCommission(Connection con, String station_code, String lr_no, float deliv_commn) throws Exception {
		PreparedStatement pst = null;
		try {
			if (null != con) {			
				
				pst = con.prepareStatement(UPDATE_DELIVERY_COMMISSION);
				
				if (null != pst) {
					pst.setFloat(1, deliv_commn);
					pst.setString(2, lr_no);					
					pst.setString(3, lr_no);
					
					pst.executeUpdate();					
				}
			}
		} catch (SQLException sqlexception) {
			throw sqlexception;
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != pst)
					pst.close();
			} catch (Exception exception) {
				if (pst != null)
					pst.close();
			}
		}
		
	}

	/**
	 * 
	 * @param cash
	 * @return
	 * @throws Exception
	 */
	public boolean cancelCR(CashDTO dto) throws Exception {
		DBHelper helper = new DBHelper();
		boolean flag = false;
		Connection con = null;

		try {
			con = helper.getConnection();
			if (null != con) {

				flag = changeCashRegisterStatus(con, dto.getCr_no(), dto
						.getStation_code(), CANCELLED_STATUS);
			}

		} catch (Exception exception) {
			flag = false;
			if (null != con)
				con.close();

			throw exception;
		} finally {
			try {
				if (null != con) {
					con.close();
				}
				con = null;
			} catch (Exception excep) {
				throw excep;
			}
		}
		return flag;
	}

	/**
	 * 
	 * @param con
	 * @param crNumber
	 * @param stationCode
	 * @param status
	 * @return
	 * @throws Exception
	 */
	private boolean changeCashRegisterStatus(Connection con, String crNumber,
			String stationCode, int status) throws Exception {
		boolean flag = false;
		PreparedStatement pst = null;

		try {
			if (null != con) {
				pst = con.prepareStatement(UPDATE_CR_STATUS);
				if (null != pst) {
					pst.setInt(1, CANCELLED_STATUS);
					pst.setString(2, crNumber);
					pst.setString(3, stationCode);
					if (pst.executeUpdate() > 0) {
						flag = true;
					} else {
						BusinessException business = new BusinessException();
						business.setResponseMessage(STATUS_CHANGE_ERROR);
						throw business;
					}
				}
			}
		} catch (SQLException sqlexception) {
			throw sqlexception;
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != pst)
					pst.close();
			} catch (Exception exception) {
				if (pst != null)
					pst.close();
			}
		}

		return flag;
	}

	/**
	 * 
	 */
	public CashDTO getCRInformation(String crno, Date date) throws Exception {
		DBHelper helper = new DBHelper();
		CashDTO dto = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			con = helper.getConnection();
			if (null != con) {
				pst = con.prepareStatement(GET_CR_INFO);
				if (null != pst) {
					pst.setString(1, crno);
					rs = pst.executeQuery();

					if (null != rs && rs.next()) {
						dto = new CashDTO();
						dto.setCr_no(rs.getString(CR_NO));
						dto.setDate(rs.getDate(DATE));
						dto.setStation_code(rs.getString(STATION_CODE));
						dto.setLr_no(rs.getString(LR_NO));
						dto.setDemurrage(rs.getFloat(DEMURAGE));
						dto.setUnder_charge(rs.getFloat(UNDER_CHARGE));
						dto.setPostage(rs.getFloat(POSTAGE));
						dto.setDd_extra(rs.getFloat(DD_EXTRA));
						dto.setTotal_amount(rs.getFloat(TOTAL_AMOUNT));
						dto.setLr_date(rs.getDate(LR_DATE));
						dto.setFrom_station(rs.getString(FROM_STATION));
						dto.setTo_station(rs.getString(TO_STATION));
						dto.setNo_of_articles(rs.getFloat(NO_OF_ARTICLES));
						dto.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
						dto.setBft(rs.getFloat(BASIC_FRIEGHT));
						dto.setDdc(rs.getFloat(DOOR_DELIVERY_CHARGE));
						dto.setConsignorName(rs.getString(CONSIGNOR_NAME));
						dto.setConsigneeName(rs.getString(CONSIGNEE_NAME));
						dto.setOther(rs.getFloat(OTHER));
						dto.setCnor_phone(rs.getString(CNOR_PHONE));
						dto.setCnee_phone(rs.getString(CNEE_PHONE));
						int status = rs.getInt(STATUS);
						if (status == 1)
							dto.setStatus(true);
					}else {
						pst = con.prepareStatement(GET_CR_INFO_HISTORY);
						if (null != pst) {
							pst.setString(1, crno);
							rs = pst.executeQuery();
							if (null != rs && rs.next()) {
								dto = new CashDTO();
								dto.setCr_no(rs.getString(CR_NO));
								dto.setDate(rs.getDate(DATE));
								dto.setStation_code(rs.getString(STATION_CODE));
								dto.setLr_no(rs.getString(LR_NO));
								dto.setDemurrage(rs.getFloat(DEMURAGE));
								dto.setUnder_charge(rs.getFloat(UNDER_CHARGE));
								dto.setPostage(rs.getFloat(POSTAGE));
								dto.setDd_extra(rs.getFloat(DD_EXTRA));
								dto.setTotal_amount(rs.getFloat(TOTAL_AMOUNT));
								dto.setLr_date(rs.getDate(LR_DATE));
								dto.setFrom_station(rs.getString(FROM_STATION));
								dto.setTo_station(rs.getString(TO_STATION));
								dto.setNo_of_articles(rs.getFloat(NO_OF_ARTICLES));
								dto.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
								dto.setBft(rs.getFloat(BASIC_FRIEGHT));
								dto.setDdc(rs.getFloat(DOOR_DELIVERY_CHARGE));
								dto.setConsignorName(rs.getString(CONSIGNOR_NAME));
								dto.setConsigneeName(rs.getString(CONSIGNEE_NAME));
								dto.setOther(rs.getFloat(OTHER));
								dto.setCnor_phone(rs.getString(CNOR_PHONE));
								dto.setCnee_phone(rs.getString(CNEE_PHONE));
								int status = rs.getInt(STATUS);
								if (status == 1)
									dto.setStatus(true);
							}
						}
					}
					
					if(dto != null){
					
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					String formattedDate = format.format(dto.getDate());
					
					int isDRSConfirmed = isDRSConfirmed(con, dto.getStation_code(), formattedDate);
				//	System.out.println("isdrs->"+isDRSConfirmed+"stn->"+dto.getStation_code()+"dt->"+formattedDate);
					
					if(dto != null){
						dto.setIsDRSConfirmed(isDRSConfirmed);
					}
					}
					
				} else {
					BusinessException business = new BusinessException();
					business.setResponseMessage(GET_CR_ERROR);
					throw business;
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
				if (null != con)
					con.close();
			}
		}

		return dto;

	}
	
	private int isDRSConfirmed(Connection con, String stationCode, String curdate) throws Exception{	
		PreparedStatement ps = null;
		ResultSet rs = null;		
		try {
			String IS_DRS_CONFIRMED = "SELECT status FROM Daily_remittance WHERE station_code = '"+stationCode+"' " +
			" and date(date_sub(dr_date, interval 1 day)) = '"+curdate+"' ";

			ps = con.prepareStatement(IS_DRS_CONFIRMED);
			if (ps != null) {
				//ps.setString(1, stationCode);
				//ps.setString(2, curdate);
				rs = ps.executeQuery();
				//System.out.println("sss--->"+ IS_DRS_CONFIRMED);
				if(rs != null){
				if (rs.next()) {
				//	System.out.println("sss--->"+ rs.getInt(STATUS));
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
	 * 
	 */
	public CashDTO[] getCRInformation(String stationcode, Date fromdate,
			Date todate) throws Exception {

		ArrayList<CashDTO> list = new ArrayList<CashDTO>();
		DBHelper helper = new DBHelper();
		CashDTO dto = null;
		CashRegisterDTO lrdto = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			SimpleDateFormat format = new SimpleDateFormat(SQLDATE_FORMAT);
			String date1 = format.format(fromdate);

			con = helper.getConnection();
			if (null != con) {
				con.setAutoCommit(false);
				pst = con.prepareStatement(GET_CR_PRINT_INFO);
				if (null != pst) {
					pst.setString(1, stationcode);
					pst.setString(2, date1);
					rs = pst.executeQuery();
					String receiv = null;
					while (rs.next()) {
						dto = new CashDTO();
						dto.setCr_no(rs.getString(CR_NO));
						dto.setDate(rs.getDate(DATE));
						dto.setStation_code(rs.getString(STATION_CODE));
						dto.setLr_no(rs.getString(LR_NO));
						dto.setDemurrage(rs.getFloat(DEMURAGE));
						dto.setUnder_charge(rs.getFloat(UNDER_CHARGE));
						dto.setPostage(rs.getFloat(POSTAGE));
						dto.setDd_extra(rs.getFloat(DD_EXTRA));
						dto.setTotal_amount(rs.getFloat(TOTAL_AMOUNT));
						dto.setLr_date(rs.getDate(LR_DATE));
						dto.setFrom_station(rs.getString(FROM_STATION));
						dto.setTo_station(rs.getString(TO_STATION));
						dto.setNo_of_articles(rs.getFloat(NO_OF_ARTICLES));
						dto.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
						dto.setBft(rs.getFloat(BASIC_FRIEGHT));
						dto.setDdc(rs.getFloat(DOOR_DELIVERY_CHARGE));
						dto.setCrprint(rs.getBoolean(CR_PRINT));
						receiv = rs.getString(RECIV_FROM);
						

						if (receiv != null) {
							if (receiv.equalsIgnoreCase("NA")) {
								dto.setConsigneeName(rs
										.getString(CONSIGNEE_NAME));
							} else {
								dto.setConsigneeName(receiv);
							}
						}

						dto.setOther(rs.getFloat(OTHER));
						dto.setLr_total(rs.getFloat(TOTAL));
						int status = rs.getInt(STATUS);
						if (status == 1)
							dto.setStatus(true);

						lrdto = new CashRegisterDTO();
						lrdto.setConsignor_address(rs
								.getString(CONSIGNOR_ADDRESS));
						lrdto.setConsignee_address(rs
								.getString(CONSIGNEE_ADDRESS));
						lrdto.setType(rs.getString(LR_TYPE));
						lrdto.setConsignorName(rs.getString(CONSIGNOR_NAME));
						lrdto.setConsignor_CST(rs.getString(CONSIGNOR_CST));
						lrdto.setConsignee_GST(rs.getString(CONSIGNEE_GST));
						lrdto.setArticle_id(rs.getInt(ARTICLE_ID));
						lrdto.setArticle_value(rs.getFloat(ARTICLE_VALUE));
						lrdto
								.setArticle_description(rs
										.getString(ARTICLE_DESC));
						lrdto.setCharged_weight(rs.getFloat(CHARGED_WEIGHT));
						lrdto.setBft(rs.getFloat(BASIC_FRIEGHT));
						lrdto.setLrc(rs.getFloat(LRCHARGES));
						lrdto.setDhc(rs.getFloat(DHC));
						lrdto.setCcc(rs.getFloat(CCC));
						lrdto.setDcc(rs.getFloat(DCC));
						lrdto.setIec(rs.getFloat(IEC));
						lrdto.setLc(rs.getFloat(LC));
						lrdto.setGsc(rs.getFloat(GSC));
						lrdto.setStax(rs.getFloat(SALES_TAX));
						lrdto.setOther_charges(rs.getFloat(OTHER_CHARGES));
						lrdto.setTotal(rs.getFloat(TOTAL));
						
						int stat = rs.getInt(LR_STATUS);
						if (stat > 1)
							lrdto.setStatus(true);

						ArticleDTO[] artdto = getArticleDetails(con, dto
								.getLr_no().trim());
						lrdto.setArticledto(artdto);
						dto.setCashdto(lrdto);
						list.add(dto);
						con.commit();
					}
				} else {
					BusinessException business = new BusinessException();
					business.setResponseMessage(GET_CR_ERROR);
					throw business;
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
				if (null != con)
					con.close();
			}
		}
		int size = list.size();

		if (size > 0) {
			return (CashDTO[]) list.toArray(new CashDTO[size]);
		}
		return null;

	}
	
	public CashDTO[] getCRInformationHistory(String stationcode, Date fromdate,
			Date todate, String dbHis) throws Exception {

		ArrayList<CashDTO> list = new ArrayList<CashDTO>();
		CashDTO dto = null;
		CashRegisterDTO lrdto = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			SimpleDateFormat format = new SimpleDateFormat(SQLDATE_FORMAT);
			String date1 = format.format(fromdate);


			if(dbHis == null || dbHis.equals("")){
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			}else{
				DBHelperHis helperHis = new DBHelperHis();
				con = helperHis.getConnectionHistory(dbHis);
			}
			
			if (null != con) {
				con.setAutoCommit(false);
				pst = con.prepareStatement(GET_CR_PRINT_INFO_HISTORY);
				if (null != pst) {
					pst.setString(1, stationcode);
					pst.setString(2, date1);
					rs = pst.executeQuery();
					String receiv = null;
					if(rs != null){
					while (rs.next()) {
						dto = new CashDTO();
						dto.setCr_no(rs.getString(CR_NO));
						dto.setDate(rs.getDate(DATE));
						dto.setStation_code(rs.getString(STATION_CODE));
						dto.setLr_no(rs.getString(LR_NO));
						dto.setDemurrage(rs.getFloat(DEMURAGE));
						dto.setUnder_charge(rs.getFloat(UNDER_CHARGE));
						dto.setPostage(rs.getFloat(POSTAGE));
						dto.setDd_extra(rs.getFloat(DD_EXTRA));
						dto.setTotal_amount(rs.getFloat(TOTAL_AMOUNT));
						dto.setLr_date(rs.getDate(LR_DATE));
						dto.setFrom_station(rs.getString(FROM_STATION));
						dto.setTo_station(rs.getString(TO_STATION));
						dto.setNo_of_articles(rs.getFloat(NO_OF_ARTICLES));
						dto.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
						dto.setBft(rs.getFloat(BASIC_FRIEGHT));
						dto.setDdc(rs.getFloat(DOOR_DELIVERY_CHARGE));
						dto.setCrprint(rs.getBoolean(CR_PRINT));
						receiv = rs.getString(RECIV_FROM);

						if (receiv != null) {
							if (receiv.equalsIgnoreCase("NA")) {
								dto.setConsigneeName(rs
										.getString(CONSIGNEE_NAME));
							} else {
								dto.setConsigneeName(receiv);
							}
						}

						dto.setOther(rs.getFloat(OTHER));
						dto.setLr_total(rs.getFloat(TOTAL));
						int status = rs.getInt(STATUS);
						if (status == 1)
							dto.setStatus(true);

						lrdto = new CashRegisterDTO();
						lrdto.setConsignor_address(rs
								.getString(CONSIGNOR_ADDRESS));
						lrdto.setConsignee_address(rs
								.getString(CONSIGNEE_ADDRESS));
						lrdto.setType(rs.getString(LR_TYPE));
						lrdto.setConsignorName(rs.getString(CONSIGNOR_NAME));
						lrdto.setConsignor_CST(rs.getString(CONSIGNOR_CST));
						lrdto.setConsignee_GST(rs.getString(CONSIGNEE_GST));
						lrdto.setArticle_id(rs.getInt(ARTICLE_ID));
						lrdto.setArticle_value(rs.getFloat(ARTICLE_VALUE));
						lrdto
								.setArticle_description(rs
										.getString(ARTICLE_DESC));
						lrdto.setCharged_weight(rs.getFloat(CHARGED_WEIGHT));
						lrdto.setBft(rs.getFloat(BASIC_FRIEGHT));
						lrdto.setLrc(rs.getFloat(LRCHARGES));
						lrdto.setCcc(rs.getFloat(CCC));
						lrdto.setDcc(rs.getFloat(DCC));
						lrdto.setIec(rs.getFloat(IEC));
						lrdto.setLc(rs.getFloat(LC));
						lrdto.setGsc(rs.getFloat(GSC));
						lrdto.setStax(rs.getFloat(SALES_TAX));
						lrdto.setOther_charges(rs.getFloat(OTHER_CHARGES));
						lrdto.setTotal(rs.getFloat(TOTAL));

						int stat = rs.getInt(LR_STATUS);
						if (stat > 1)
							lrdto.setStatus(true);

						ArticleDTO[] artdto = getArticleDetailsHistory(con, dto .getLr_no().trim());						
						lrdto.setArticledto(artdto);
						dto.setCashdto(lrdto);
						list.add(dto);
						con.commit();
					}				
						
					}
					
				} else {
					BusinessException business = new BusinessException();
					business.setResponseMessage(GET_CR_ERROR);
					throw business;
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
				if (null != con)
					con.close();
			}
		}
		int size = list.size();

		if (size > 0) {
			return (CashDTO[]) list.toArray(new CashDTO[size]);
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
	private ArticleDTO[] getArticleDetails(Connection con, String lrno)
			throws Exception {

		ArticleDTO dto = null;
		ArrayList<ArticleDTO> list = new ArrayList<ArticleDTO>();
		PreparedStatement ps = null;

		ResultSet rs = null;

		try {
			ps = con.prepareStatement(GET_ARTICLEDETAIL);

			if (ps != null) {
				ps.setString(1, lrno);

				rs = ps.executeQuery();
				while (rs.next()) {
					dto = new ArticleDTO();
					dto.setArticleName(rs.getString(ART_NAME));
					dto.setNoOfArticles(rs.getInt(ART_NOA));
					dto.setArticleValue(rs.getString(ART_VALUE));
					dto.setActualWeight(rs.getFloat(ART_ACTUALWT));
					dto.setVolumetricWeight(rs.getFloat(ART_VOLUMEWT));
					dto.setChargedWeight(rs.getFloat(ART_CHGDWT));
					dto.setArticleDesc(rs.getString(ART_DESC));
					dto.setArt_length(rs.getFloat(ART_LEN));
					dto.setArt_breath(rs.getFloat(ART_BRTH));
					dto.setArt_height(rs.getFloat(ART_HT));
					list.add(dto);
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

			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();

			}
		}
		int size = list.size();

		if (size > 0) {

			return (ArticleDTO[]) list.toArray(new ArticleDTO[size]);

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
	private ArticleDTO[] getArticleDetailsHistory(Connection con, String lrno)
			throws Exception {

		ArticleDTO dto = null;
		ArrayList<ArticleDTO> list = new ArrayList<ArticleDTO>();
		PreparedStatement ps = null;

		ResultSet rs = null;

		try {
			ps = con.prepareStatement(GET_ARTICLEDETAIL_HISTORY);

			if (ps != null) {
				ps.setString(1, lrno);
				rs = ps.executeQuery();
				
				while (rs.next()) {
					dto = new ArticleDTO();
					dto.setArticleName(rs.getString(ART_NAME));
					dto.setNoOfArticles(rs.getInt(ART_NOA));
					dto.setArticleValue(rs.getString(ART_VALUE));
					dto.setActualWeight(rs.getFloat(ART_ACTUALWT));
					dto.setVolumetricWeight(rs.getFloat(ART_VOLUMEWT));
					dto.setChargedWeight(rs.getFloat(ART_CHGDWT));
					dto.setArticleDesc(rs.getString(ART_DESC));
					dto.setArt_length(rs.getFloat(ART_LEN));
					dto.setArt_breath(rs.getFloat(ART_BRTH));
					dto.setArt_height(rs.getFloat(ART_HT));
					list.add(dto);
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

			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (null != ps)
					ps.close();

			}
		}
		int size = list.size();

		if (size > 0) {

			return (ArticleDTO[]) list.toArray(new ArticleDTO[size]);

		}
		return null;
	}


	/**
	 * Method to change status for CR Print
	 * 
	 * @param crno
	 * @throws SQLException
	 * @throws Exception
	 */
	public void updatePrintStatus(String[] crno) throws SQLException, Exception {
		Connection con = null;
		PreparedStatement pst = null;
		DBHelper helper = new DBHelper();
		try {
			con = helper.getConnection();
			if (null != con) {

				pst = con.prepareStatement(UPDATE_CR_PRINT_STATUS);
				if (null != pst) {
					int batch_count = crno.length;
					for (int i = 0; i < batch_count; i++) {
						pst.setString(1, crno[i]);
						pst.addBatch();
					}

					pst.executeBatch();
				}

			}
		} catch (SQLException sqlexception) {
			throw sqlexception;
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {

				if (null != pst)
					pst.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {

				if (pst != null)
					pst.close();
				if (null != con)
					con.close();
			}
		}

	}

	@Override
	public String getLastUsedCR(String station_code) throws RemoteException,
			Exception {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		DBHelper helper = new DBHelper();
		try {
			con = helper.getConnection();
			if (null != con) {

				pst = con.prepareStatement(GET_LAST_CREATED_CR);
				if (null != pst) {
					pst.setString(1, station_code);					
					rs = pst.executeQuery();
					while (rs.next()) {
						return rs.getString("LAST_CR");
					}

				}

			}
		} catch (SQLException sqlexception) {
			throw sqlexception;
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {

				if (null != pst)
					pst.close();
				if (null != con)
					con.close();
			} catch (Exception exception) {

				if (pst != null)
					pst.close();
				if (null != con)
					con.close();
			}
		}
		return null;
	}

	@Override
	public LRNumberRangeDTO[] getLRRange(String stationcode)
			throws RemoteException, Exception {

		Connection con = null;
		PreparedStatement pst = null;
		ArrayList<LRNumberRangeDTO> list = new ArrayList<LRNumberRangeDTO>();
		LRNumberRangeDTO lrFormat = null;

		try {

			DBHelper db = new DBHelper();
			con = db.getConnection();

			if (null != con) {
				pst = con.prepareStatement(GET_LR_RANGE);

				if (null != pst) {
					pst.setString(1, stationcode);
					ResultSet rs = pst.executeQuery();

					if (null != rs) {

						while (rs.next()) {
							lrFormat = new LRNumberRangeDTO();

							lrFormat.setStationCode(rs.getString(STATION_CODE));
							lrFormat.setType(rs.getString("type"));
							lrFormat.setRangeFrom(rs.getString("start_number"));
							lrFormat.setRangeTo(rs.getString("end_number"));

							list.add(lrFormat);
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
				pst.close();
				pst = null;
				con.close();
				con = null;

			} catch (Exception exception) {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			}
		}

		int size = list.size();

		if (size > 0) {
			return (LRNumberRangeDTO[]) list
					.toArray(new LRNumberRangeDTO[size]);
		}
		return null;

	}

	@Override
	public boolean generateCR(CashDTO dto) throws BusinessException,
			SQLException, Exception {

		return false;
	}

}
