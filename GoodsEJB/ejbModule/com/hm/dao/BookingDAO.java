package com.hm.dao;

import hm.akr.dto.ActivityLog;
import hm.akr.dto.ArticleDTO;
import hm.akr.dto.BookingDTO;
import hm.akr.dto.GMRDTO;
import hm.akr.dto.GMRInTimeDTO;
import hm.akr.dto.GMROutTimeDTO;
import hm.akr.dto.LRTrackDTO;
import hm.akr.dto.SMSDTO;
import hm.akr.dto.VehicleDTO;
import hm.akr.exceptions.BusinessException;
import hm.akr.interfaces.Goods;

import java.rmi.RemoteException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.ejb.CreateException;
import javax.naming.NamingException;

/**
 * 
 * @author HM Class for BookingDAO
 */
public class BookingDAO implements IBookingDAO, DAOConstants {

	public static String status = null;

	/**
	 * Constructor method
	 */
	public BookingDAO() {
		super();

	}

	/**
	 * 
	 */
	public boolean bookParcel(BookingDTO dto) throws BusinessException,
			Exception {
		boolean flag = false;
		Connection con = null;

		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {

				con.setAutoCommit(false);

				flag = insertRecordToLR(con, dto);

				flag = insertArticleDetails(con, dto);

				if (flag) {					
					GMRDTO[] gmr = new GMRDTO[1];
					gmr[0] = new GMRDTO();
					gmr[0].setLrNumber(dto.getLrno());
					gmr[0].setStationCode(dto.getFrom());
					insertLRTrackForCurrStation(con, gmr);
					int limit = 0;
					int avail = 0;
					limit = getStationaryLimit(con,dto.getFrom(),dto.getType());
					
					avail = getAvailStationaryCount(con,dto.getFrom(),dto.getType());
					
					if(limit > avail){
						assignStationary(con, dto.getFrom(),dto.getType());
					}
					
					con.commit();
				}
			}
		} catch (Exception exception) {
			flag = false;
			con.rollback();
			throw exception;
		} finally {
			try {
				if (null != con) {
					con.close();
				}
				con = null;
			} catch (Exception excep) {
				excep = null;
			}
		}

		return flag;
	}

	private boolean assignStationary(Connection con, String station, String type) throws Exception {
		System.out.println("Assign Stationary after booking");
		
		PreparedStatement pst = null;
		CallableStatement cs = null;
		boolean result = false;

		try {		

			if (null != con) {
				con.setAutoCommit(false);
				int in = 0;				

					cs = con.prepareCall("{call assignstationary(?,?,?,?,?,?)}");
					cs.setString(1, station);
					if(type.equalsIgnoreCase("topay")){
						cs.setInt(2, 1);
					}else{
						cs.setInt(2, 0);
					}
					
					if(type.equalsIgnoreCase("paid")){
						cs.setInt(3, 1);
					}else{
						cs.setInt(3, 0);
					}
				
					if(type.equalsIgnoreCase("billing")){
						cs.setInt(4, 1);
					}else{
						cs.setInt(4, 0);
					}					
				
					cs.setInt(5, 0);
					cs.setInt(6, in);
					cs.registerOutParameter(6, java.sql.Types.INTEGER);

					cs.executeUpdate();

					int status = cs.getInt(6);

					if (status == 1) {
						System.out
								.println("Out GoodsDAO Stationary Settings Not Assigned");
						con.rollback();
						return false;
					}
				}
				System.out
						.println("Out GoodsDAO Stationary Settings  Assigned");
				result = true;
				
				if (result) {
					con.commit();
				}
			
		} catch (SQLException sqlexception) {
			con.rollback();
			result = false;
			throw sqlexception;
		} catch (Exception exception) {
			con.rollback();
			result = false;
			throw exception;
		} finally {
			try {
				pst.close();
				pst = null;
			} catch (Exception exception) {
				if (pst != null)
					pst.close();				
			}
		}

		return result;
	}
	
	
	private int getAvailStationaryCount(Connection con, String from, String type) throws Exception {

		PreparedStatement pst = null;
		ResultSet rs = null;
	
		try {
			if (null != con) {				

				pst = con.prepareStatement(GET_AVAIL_STATIONARY_COUNT);
				if (null != pst) {
					pst.setString(1, from);
					pst.setString(2, type);
					
					rs = pst.executeQuery();
					if(rs != null){
						if(rs.next()){
							return rs.getInt(1);
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
				if (null != pst)
					pst.close();
			} catch (Exception exception) {
				if (pst != null)
					pst.close();
			}
		}

		return 0;	
	
	}

	private int getStationaryLimit(Connection con, String from, String type) throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
	
		try {
			if (null != con) {				

				pst = con.prepareStatement(GET_STATIONARY_LIMIT);
				if (null != pst) {
					pst.setString(1, from);
					pst.setString(2, type);
					
					rs = pst.executeQuery();
					if(rs != null){
						if(rs.next()){
							return rs.getInt(1);
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
				if (null != pst)
					pst.close();
			} catch (Exception exception) {
				if (pst != null)
					pst.close();
			}
		}

		return 0;
	
	}

	/**
	 * 
	 * @param con
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	private boolean insertArticleDetails(Connection con, BookingDTO dto)
			throws Exception {
		PreparedStatement pst = null;

		boolean flag = false;
		try {
			if (null != con) {
				ArticleDTO[] artdto = dto.getArticledto();

				pst = con.prepareStatement(INSERT_ARTICLES);

				if (null != pst) {
					int batch_count = artdto.length;

					for (int i = 0; i < batch_count; i++) {
						pst.setString(1, dto.getLrno());
						pst.setString(2, artdto[i].getArticleName());
						pst.setInt(3, artdto[i].getNoOfArticles());
						pst.setString(4, artdto[i].getArticleValue());
						pst.setFloat(5, artdto[i].getActualWeight());
						pst.setFloat(6, artdto[i].getArt_length());
						pst.setFloat(7, artdto[i].getArt_breath());
						pst.setFloat(8, artdto[i].getArt_height());
						pst.setFloat(9, artdto[i].getVolumetricWeight());
						pst.setFloat(10, artdto[i].getChargedWeight());
						pst.setString(11, artdto[i].getArticleDesc());

						pst.addBatch();
					}

					pst.executeBatch();
					flag = true;
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
	public String[] bookParcels(BookingDTO[] dto) throws Exception {
		boolean flag = false;
		int length = 0;
		length = dto.length;
		ArrayList<String> list = new ArrayList<String>();

		for (int i = 0; i < length; i++) {
			flag = bookParcel(dto[i]);
			if (!flag) {
				list.add(dto[i].getLrno());
			}
		}

		String[] lrno = (String[]) list.toArray(new String[list.size()]);
		return lrno;
	}

	/**
	 * 
	 * @param con
	 * @param dto
	 * @return
	 * @throws BusinessException
	 * @throws Exception
	 * 
	 */
	
	public BookingDTO[] getOldlrstatus(String lrno,String stationcode)throws Exception{
		System.out.println("BookingDAO getoldlrstaus in------->");
		Connection con= null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		String status = null;
		BookingDTO book = null;
		ArrayList<BookingDTO> booking = new ArrayList<BookingDTO>(); 
		try{
			DBHelper helper=new DBHelper();
			con  = helper.getConnection();
			if(null != con){
				ps = con.prepareStatement(CHECK_OLDLR_STATUS);
				
				if(ps != null){
					ps.setString(1,stationcode);
					ps.setString(2, lrno);
					
					rs = ps.executeQuery();
					
					if(null != rs){
						while(rs.next()){
							 book = new BookingDTO();
							 book.setLrno(rs.getString(LR_STATUS));
							 book.setFrom(rs.getString(FROM_STATION));
							 book.setDate(rs.getDate(INWARD_TIME));
							 book.setConsignorName(rs.getString(CONSIGNOR_NAME));
							 book.setConsigneeName(rs.getString(CONSIGNEE_NAME));
							 book.setConsignor_address(rs.getString(CONSIGNOR_ADDRESS));
							 book.setConsignee_address(rs.getString(CONSIGNEE_ADDRESS));
							 book.setConsignor_CST(rs.getString(CONSIGNOR_CST));
							 book.setConsignee_GST(rs.getString(CONSIGNEE_GST));
							 book.setCneePhone(rs.getString(CNEE_PHONE));
							 book.setCnorPhone(rs.getString(CNOR_PHONE));
							 book.setCnorLandLine(rs.getString(CNOR_LANDLINE));
							 book.setCneeLandLine(rs.getString(CNEE_LANDLINE));
							 booking.add(book);
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
		
		int size = booking.size();
		System.out.println("BookingDAO getoldlrstaus in------->"+size);
		if(size > 0){
			return (BookingDTO[])booking.toArray(new BookingDTO[size]) ;
		}
		return null;
		
		
		
	}
	
	
	
	 public String deliver_oldlr_goods(GMRDTO goods) throws Exception {
		// boolean result = false;
		Connection con = null;
		CallableStatement cs = null;
		PreparedStatement pst = null;
		String lrnos = null;
		ResultSet rs = null;

		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			String formateddate = dateFormat.format(date);
			PreparedStatement ps =null;
			if (null != con) {
					
							
							con.setAutoCommit(false);
							cs = con.prepareCall("{call oldlr_goods_delivery(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
							//cs = con.prepareCall("{call oldlr_goods_delivery_new(?,?,?,?,?,?,?,?,?)}");
							System.out.println("********call oldlr_goods_delivery*******");
							if (null != cs) {
								//int batch_count = goods.length;

								//for (int i = 0; i < batch_count; i++) {
									date.setSeconds(date.getSeconds()+1);
									String crdateSLoop = dateFormat.format(date);
									cs.setString(1, goods.getLrNumber());
									cs.setString(2, goods.getStationCode());
									cs.setString(3, goods.getVehicleNumber());
									cs.setString(4, goods.getDriverName());
									cs.setString(5, formateddate);
									cs.setString(6, dateFormat.format(goods.getLrDate()));
									// From station
									cs.setString(7, goods.getDestinationStn());
									cs.setString(8, goods.getCrNo());
									cs.setFloat(9, goods.getCrTotal());
									cs.setString(10, goods.getCneeName());
									cs.setString(11, crdateSLoop);
									cs.setString(12, goods.getLrType());
									cs.setString(13, goods.getDispatchTo());
													
															
									cs.execute();
									ResultSet rst = cs.getResultSet();
									if (null != rst) {
										int isout = 1;
										while (rst.next()) {

											isout = rst.getInt("globalstatus");

										}
										if (isout == 1) {
											con.rollback();
											BusinessException business = new BusinessException();
											business
													.setResponseMessage("Delivery failed,Kindly try after 10 seconds");
											throw business;

										} else
											con.commit();
									}

								

							}

							
						}
					
				
			
		
		} catch (Exception exception) {			
			throw exception;
		} finally {
			try {
				if (null != cs) {
					cs.close();
				}
				if (null != con) {
					con.close();
				}
				con = null;
				cs = null;
			} catch (Exception excep) {
				con = null;
				cs = null;
			}
		}

		return lrnos;
	}
	
	
	
	private boolean insertRecordToLR(Connection con, BookingDTO dto)
			throws BusinessException, Exception {
		PreparedStatement pstmt = null;

		boolean bookingFlag = false;

		int result = 0;

		boolean lrFlag = false;

		try {
			if (null != con) {

				lrFlag = checkDuplicateLr(dto.getLrno(), con);

				if (lrFlag) {
					con.commit();
					BusinessException business = new BusinessException();
					business.setResponseMessage(DUPLICATE_ERROR);
					throw business;
				} else {
					if(dto.getIsUPd().equals("Rebook") || dto.getIsUPd().equals("ToUPD")){
						pstmt = con.prepareStatement(INSERT_QUERY_REBOOK);
						//deliver_oldlr_goods(dto);
					}
					else{
						pstmt = con.prepareStatement(INSERT_QUERY);
					}
					pstmt.setString(1, dto.getLrno());// lr_no
					pstmt.setString(2, dto.getType());// type
					pstmt.setString(3, dto.getFrom());// from
					pstmt.setString(4, dto.getTo());// to
					pstmt.setString(5, dto.getConsignorName());
					pstmt.setString(6, dto.getConsignor_address()); // consignor_address
					pstmt.setString(7, dto.getConsigneeName());
					pstmt.setString(8, dto.getConsignee_address()); // consignee_address
					pstmt.setString(9, dto.getConsignor_CST());// consignor_CST
					pstmt.setString(10, dto.getConsignee_GST()); // consignee_GST
					pstmt.setInt(11, dto.getArticle_id());
					pstmt.setFloat(12, dto.getArticle_value()); // article_value
					pstmt.setInt(13, dto.getNo_of_articles());// no_of_articles
					pstmt.setString(14, dto.getArticle_description()); // article_description
					pstmt.setFloat(15, dto.getActual_weight()); // actual_weight
					pstmt.setFloat(16, dto.getCharged_weight()); // charged_weight
					pstmt.setFloat(17, dto.getBft());
					pstmt.setFloat(18, dto.getLrc());
					pstmt.setFloat(19, dto.getCcc()); // ccc
					pstmt.setFloat(20, dto.getDdc());
					pstmt.setFloat(21, dto.getDcc());// dcc
					pstmt.setFloat(22, dto.getIec());
					pstmt.setFloat(23, dto.getLc());
					pstmt.setFloat(24, dto.getGsc());
					pstmt.setFloat(25, dto.getStax());
					pstmt.setFloat(26, dto.getOther_charges()); // other_charges
					pstmt.setFloat(27, dto.getTotal());// total
					pstmt.setInt(28, BOOKEDSTATUS); // Status
					pstmt.setString(29, dto.getCreatedby()); // Created by
					pstmt.setInt(30, dto.getArt_unit());
					pstmt.setInt(31, dto.getRate_type());
					pstmt.setFloat(32, dto.getActual_bft());
					pstmt.setFloat(33, dto.getDdcFree());
					pstmt.setString(34, dto.getCnorPhone());
					pstmt.setString(35, dto.getCneePhone());
					pstmt.setFloat(36, dto.getTotal_charged_wt());

					pstmt.setInt(37, dto.getSmsNotify());
					pstmt.setString(38, dto.getCnorLandLine());
					pstmt.setString(39, dto.getCneeLandLine());
					pstmt.setFloat(40, dto.getBooking_commission());
					pstmt.setFloat(41, dto.getCc_commission());
					pstmt.setBoolean(42, dto.isStaxNo());
					pstmt.setFloat(43, dto.getBooking_percent());
					pstmt.setFloat(44, dto.getBooking_percent());
					pstmt.setFloat(44, dto.getDhc());
					//System.out.println("customertype-->"+String.valueOf(dto.getInvoiceNo()));
					pstmt.setString(45, dto.getInvoiceNo());
					if(dto.getIsUPd() != null &&(dto.getIsUPd().equals("Rebook")  || dto.getIsUPd().equals("ToUPD"))){
						pstmt.setString(46, dto.getIsUPd());
						pstmt.setString(47, dto.getOldLrno());
						pstmt.setFloat(48, dto.getOldLrTotal());
						pstmt.setFloat(49, dto.getPost());
						pstmt.setFloat(50,dto.getDemu());
					}
					

					result = pstmt.executeUpdate();

					if (result > 0) {
						bookingFlag = true;
					}
				}
			}
		} catch (SQLException sqlexception) {
			String message = sqlexception.getMessage();
			if (message.startsWith(DUPLICATE_ENTRY)) {
				BusinessException business = new BusinessException();
				business.setResponseMessage(DUPLICATE_ERROR);
				throw business;
			} else {
				throw sqlexception;
			}
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != pstmt)
					pstmt.close();
			} catch (Exception exception) {
				if (null != pstmt) {
					pstmt.close();
				}
			}

		}
		return bookingFlag;
	}

	
		// TODO Auto-generated method stub
		
	

	/**
	 * 
	 * @param lrNo
	 * @param con
	 * @return
	 * @throws Exception
	 */
	private boolean checkDuplicateLr(String lrNo, Connection con)
			throws Exception {
		PreparedStatement pstmt = null;
		boolean flag = false;
		ResultSet rs = null;

		try {
			if (con != null) {
				pstmt = con.prepareStatement(CHECK_DUPLICATE);
				pstmt.setString(1, lrNo);
				rs = pstmt.executeQuery();
				if (rs != null) {
					rs.next();
					if (rs.getInt(1) > 0) {
						flag = true;
					}
				}
			}
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != pstmt) {
					pstmt.close();
				}
			} catch (Exception exception) {
				if (null != pstmt) {
					pstmt.close();
				}
			}
		}

		return flag;
	}

	/**
	 * Method to cancel the booking
	 * 
	 */
	public boolean cancelBooking(String lrno, String stationCode,
			String cancelOption) throws SQLException, Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean cancelFlag = false;
		DBHelper helper = new DBHelper();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String isupd = null;
		String oldLrno = null;
		String delivered_station = null; 
		System.out.println("GoodsBean Cancelbookig in--->");
		try {
			con = helper.getConnection();

			if (null != con) {
				ps=con.prepareStatement(CHECK_ISUPD);
					ps.setString(1, lrno);
					rs = ps.executeQuery();
					if(null != rs){
						while(rs.next()){
							isupd = rs.getString(ISUPD);
							oldLrno = rs.getString(OLDLRNO);
							delivered_station = rs.getString(FROM_STATION);
						}
					}
				System.out.println("from_station-->"+delivered_station);
				System.out.println("isupd-->"+isupd);
				System.out.println("oldlrno-->"+oldLrno);
				if(isupd != null && oldLrno != null && (isupd.equalsIgnoreCase("Rebook") || isupd.equalsIgnoreCase("ToUPD"))){
					
					rollbackOldLR(con,lrno,isupd,oldLrno,delivered_station);
				}
					
					con.setAutoCommit(false);
					System.out.println("cancelloption"+cancelOption);
					pstmt = con.prepareStatement(CANCEL_LR_QUERY);
					pstmt.setInt(1, CANCELLEDSTATUS); // status cancelled
					pstmt.setString(2, cancelOption);
					pstmt.setString(3, stationCode);
					pstmt.setString(4, lrno);
					pstmt.setString(5, lrno);
					if (pstmt.executeUpdate() > 0) {
						cancelFlag = true;
						deleteRecordFromLRTracking(con, lrno);
						con.commit();
					} else {
						BusinessException business = new BusinessException();
						business.setResponseMessage(CANCELLATION_ERROR);
						throw business;
					}
				
			}

		} catch (SQLException sqlexception) {
			if (null != con)
				con.rollback();
			throw sqlexception;
		} catch (Exception exception) {
			if (null != con)
				con.rollback();
			throw exception;
		} finally {
			try {
				if(null != rs){
					rs.close();
				}
				if(null != ps){
					ps.close();
				}
				if (null != pstmt) {
					pstmt.close();
				}
				if (null != con) {
					con.close();
				}
			} catch (Exception exception) {
				
				if(null != rs){
					rs.close();
				}
				if(null != ps){
					ps.close();
				}
				if (null != pstmt) {
					pstmt.close();
				}
				if (null != con) {
					con.close();
				}
			}
		}
		System.out.println("GoodsBean Cancelbookig out--->"+cancelFlag);
		return cancelFlag;
	}

	private void rollbackOldLR(Connection con, String lrno, String isupd,
			String oldLrno, String delivered_station) throws SQLException,Exception {
		// TODO Auto-generated method stub
		System.out.println("rollbackOldLr in ---->");
		CallableStatement cs = null;

		try {
			if (null != con) {
				con.setAutoCommit(false);
				cs = con.prepareCall("{call oldlr_booking_cancel(?,?,?,?)}");

				if (null != cs) {
					cs.setString(1, lrno);
					cs.setString(2, isupd);
					cs.setString(3, oldLrno);
					cs.setString(4, delivered_station);
					cs.executeUpdate();

					cs.execute();
					ResultSet rst = cs.getResultSet();
					if (null != rst) {
						int isout = 1;
						while (rst.next()) {

							isout = rst.getInt("globalstatus");

						}
						System.out.println("rollbackOldLr out ---->"+isout);
						if (isout == 1) {
							con.rollback();
							BusinessException business = new BusinessException();
							business
									.setResponseMessage("LR Cancel failed,Kindly try after 10 seconds");
							throw business;

						} else
							con.commit();
					}
				}
			}

		} catch (SQLException sqlexception) {
			throw sqlexception;
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != cs)
					cs.close();
			} catch (Exception exception) {
				if (null != cs)
					cs.close();
			}
		}
		System.out.println("rollbackOldLr out ---->");
	}

	/**
	 * 
	 * @param goods
	 * @throws SQLException
	 * @throws Exception
	 */
	public String[] assignGoods(GMRDTO[] goods) throws SQLException, Exception {
		Connection con = null;
		PreparedStatement pst = null;

		String[] lrnos = null;

		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				pst = con.prepareStatement(ASSIGN_GOODS);

				if (null != pst) {
					int batch_count = goods.length;

					for (int i = 0; i < batch_count; i++) {
						pst.setString(1, goods[i].getStationCode());
						pst.setString(2, goods[i].getLrNumber());
						pst.addBatch();
					}

					pst.executeBatch();
				}

				storePendingSMS(con, goods);

			}
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != pst) {
					pst.close();
				}
				if (null != con) {
					con.close();
				}
				con = null;
				pst = null;
			} catch (Exception excep) {
				con = null;
				pst = null;
			}
		}

		return lrnos;

	}

	/**
	 * 
	 * @param con
	 * @param goods
	 * @throws Exception
	 */
	private void storePendingSMS(Connection con, GMRDTO[] goods)
			throws Exception {
		PreparedStatement pst = null;

		try {
			if (null != con) {
				pst = con.prepareStatement(INSERT_SMS);
				if (null != pst) {
					int batch_count = goods.length;

					for (int i = 0; i < batch_count; i++) {
						SMSDTO sms = goods[i].getSmsDto();
						if (sms != null) {
							pst.setString(1, sms.getCnor_phone());
							pst.setString(2, sms.getCnor_message());
							pst.addBatch();

							pst.setString(1, sms.getCnee_phone());
							pst.setString(2, sms.getCnee_message());
							pst.addBatch();
						}
					}

					pst.executeBatch();
				}

			}

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
	 */
	public String[] deliverGoods(GMRDTO[] goods) throws SQLException, Exception {
		// boolean result = false;
		Connection con = null;
		CallableStatement cs = null;
		String[] lrnos = null;

		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			String formateddate = dateFormat.format(date);
			PreparedStatement ps =null;
			if (null != con) {
				con.setAutoCommit(false);
				cs = con.prepareCall("{call goods_delivery(?,?,?,?,?,?,?,?,?,?,?)}");
				System.out.println("********call goods_delivery*******");
				if (null != cs) {
					int batch_count = goods.length;

					for (int i = 0; i < batch_count; i++) {
						date.setSeconds(date.getSeconds()+1);
						String crdateSLoop = dateFormat.format(date);

						cs.setString(1, goods[i].getLrNumber());
						cs.setString(2, goods[i].getStationCode());
						cs.setString(3, goods[i].getVehicleNumber());
						cs.setString(4, goods[i].getDriverName());
						cs.setString(5, formateddate);
						cs.setString(6, goods[i].getLastInwardTime());
						// From station
						cs.setString(7, goods[i].getDestinationStn());
						cs.setString(8, goods[i].getCrNo());
						cs.setFloat(9, goods[i].getCrTotal());
						cs.setString(10, goods[i].getCneeName());
						cs.setString(11, crdateSLoop);
						
						//System.out.println("Fromstn-->"+goods[i].getDestinationStn());
						cs.execute();
						ResultSet rs = cs.getResultSet();
						if (null != rs) {
							int isout = 1;
							while (rs.next()) {

								isout = rs.getInt("globalstatus");

							}
							if (isout == 1) {
								con.rollback();
								BusinessException business = new BusinessException();
								business
										.setResponseMessage("Delivery failed,Kindly try after 10 seconds");
								throw business;

							} else
								con.commit();
						}

					}

				}
			}

		} catch (Exception exception) {			
			throw exception;
		} finally {
			try {
				if (null != cs) {
					cs.close();
				}
				if (null != con) {
					con.close();
				}
				con = null;
				cs = null;
			} catch (Exception excep) {
				con = null;
				cs = null;
			}
		}

		return lrnos;
	}

	/**
	 * 
	 * @param goods
	 * @throws Exception
	 */
	public String[] dispatchGoods(GMRDTO[] goods) throws Exception {
		// Insert records to gmr intime table and update the tracking table
		// and delete the records from gmr outtime

		Connection con = null;
		String[] lrnos = null;

		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			con.setAutoCommit(false);

			updateLRTrackForDispatchedGoods(con, goods);
			insertLRTrackForDispatchedStation(con, goods);

			if (goods[0].isMarketVehile()) {
				insertMarketVahile(con, goods[0]);
			}

			con.commit();
		} catch (Exception exception) {
			if (null != con)
				con.rollback();
			throw exception;
		} finally {
			try {
				if (null != con) {
					con.close();
				}
				con = null;
			} catch (Exception excep) {
				con = null;
			}
		}
		return lrnos;
	}

	/**
	 * 
	 * @param con
	 * @param dto
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	private boolean insertMarketVahile(Connection con, GMRDTO dto)
			throws SQLException, Exception {

		PreparedStatement pst = null;

		boolean flag = false;
		try {
			if (null != con) {
				DateFormat dateFormat = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				Date date = new Date();
				String formateddate = dateFormat.format(date);
				pst = con.prepareStatement(INSERT_MARKET_VEHICLE);

				if (null != pst) {

					pst.setString(1, dto.getVehicleName());
					pst.setString(2, dto.getVehiclePhone());
					pst.setString(3, dto.getModelNo());
					pst.setFloat(4, dto.getVehicleRate());
					pst.setString(5, formateddate);
					pst.setString(6, dto.getStationCode());
					pst.setString(7, dto.getDispatchTo());
					pst.executeUpdate();
				}

				flag = true;
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
	 * @param con
	 * @param dto
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	private boolean updateLRTrackForDispatchedGoods(Connection con,
			GMRDTO[] goods) throws SQLException, Exception {
		PreparedStatement pst = null;

		boolean flag = false;
		try {
			if (null != con) {

				pst = con.prepareStatement(UPDATE_LRTRACK);
				DateFormat dateFormat = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				Date date = new Date();
				String formateddate = dateFormat.format(date);

				if (null != pst) {
					int batch_count = goods.length;

					for (int i = 0; i < batch_count; i++) {
						pst.setString(1, DISPATCH_STATUS);
						pst.setString(2, goods[i].getVehicleNumber());
						pst.setString(3, goods[i].getDriverName());
						pst.setString(4, goods[i].getDispatchTo());
						pst.setString(5, formateddate);
						pst.setString(6, goods[i].getStationCode());
						pst.setString(7, goods[i].getLrNumber());

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
			} catch (Exception exception) {
				if (pst != null)
					pst.close();
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
	private boolean insertLRTrackForCurrStation(Connection con, GMRDTO[] dto)
			throws SQLException, Exception {
		PreparedStatement pst = null;

		boolean flag = false;
		try {
			if (null != con) {
				DateFormat dateFormat = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				Date date = new Date();
				String formateddate = dateFormat.format(date);

				pst = con.prepareStatement(INSERT_LRTRACK);

				if (null != pst) {
					int batch_count = dto.length;

					for (int i = 0; i < batch_count; i++) {
						pst.setString(1, dto[i].getLrNumber());
						pst.setString(2, dto[i].getStationCode());
						pst.setString(3, ARRIVED_STATUS);
						pst.setString(4, EMPTY_STRING);
						pst.setString(5, formateddate);
						pst.addBatch();
					}

					pst.executeBatch();
					flag = true;
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
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	private boolean insertLRTrackForDispatchedStation(Connection con,
			GMRDTO[] dto) throws SQLException, Exception {
		PreparedStatement pst = null;

		boolean flag = false;
		try {
			if (null != con) {
				DateFormat dateFormat = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				Date date = new Date();
				String formateddate = dateFormat.format(date);

				pst = con.prepareStatement(INSERT_LRTRACK);

				if (null != pst) {
					int batch_count = dto.length;

					for (int i = 0; i < batch_count; i++) {
						pst.setString(1, dto[i].getLrNumber());
						pst.setString(2, dto[i].getDispatchTo());
						pst.setString(3, TOARRIVE_STATUS);
						pst.setString(4, dto[i].getVehicleNumber());
						pst.setString(5, formateddate);
						pst.addBatch();
					}

					pst.executeBatch();
					flag = true;
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
	 * @param con
	 * @param lrNumber
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	private boolean deleteRecordFromLRTracking(Connection con, String lrNumber)
			throws SQLException, Exception {
		boolean flag = false;
		PreparedStatement pst = null;

		try {
			if (null != con) {
				pst = con.prepareStatement(DELETE_LRTRACKING);

				if (null != pst) {
					pst.setString(1, lrNumber);
					pst.executeUpdate();

					flag = true;
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
	 * @param stationCode
	 * @return outList
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ArrayList getOutTimeGoods(String stationCode) throws SQLException,
			Exception {
		Connection con = null;
		PreparedStatement pst = null;
		ArrayList<GMROutTimeDTO> outList = new ArrayList<GMROutTimeDTO>();
		ResultSet rs = null;

		try {

			DBHelper db = new DBHelper();
			con = db.getConnection();
			java.sql.Timestamp dbSqlTimestamp = null;

			if (null != con) {

				pst = con.prepareStatement(JOIN_OUTTIMEWITHLR);
				if (pst != null) {
					pst.setString(1, stationCode);

					rs = pst.executeQuery();
					if (null != rs) {
						while (rs.next()) {
							GMROutTimeDTO book = new GMROutTimeDTO();
							book.setLr_no(rs.getString(LR_NO));
							book.setLrDate(rs.getDate(LR_DATE));
							book.setFrom(rs.getString(FROM_STATION));
							book.setTo(rs.getString(TO_STATION));
							book.setNo_of_articles(rs.getInt(NO_OF_ARTICLES));
							book.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
							book.setConsignorName(rs.getString(CONSIGNOR_NAME));
							book.setConsigneeName(rs.getString(CONSIGNEE_NAME));
							dbSqlTimestamp = rs.getTimestamp(INWARD_TIME);
							if (dbSqlTimestamp != null)
								book.setLast_inwarded_date(dbSqlTimestamp);
							book.setOutTimeDate(rs.getDate(INWARD_TIME));
							book.setDdc(rs.getFloat(DOOR_DELIVERY_CHARGE));
							book.setArticle_value(rs.getFloat(ARTICLE_VALUE));
							book.setTotal(rs.getFloat(TOTAL));
							book.setLr_type(rs.getString(LR_TYPE));
							book.setArticle_type(String.valueOf(rs
									.getInt(ARTICLE_ID)));
							dbSqlTimestamp = null;

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
		return outList;
	}

	/**
	 * 
	 * @param stationCode
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public ArrayList getDeliveredGoods(String stationCode) throws SQLException,
			Exception {
		Connection con = null;
		PreparedStatement pst = null;
		ArrayList<GMROutTimeDTO> deliverlist = new ArrayList<GMROutTimeDTO>();
		ResultSet rs = null;

		try {

			DBHelper db = new DBHelper();
			con = db.getConnection();

			if (null != con) {

				pst = con.prepareStatement(JOIN_CRWITHLR);
				if (pst != null) {
					pst.setString(1, stationCode);
					pst.setString(2, stationCode);

					rs = pst.executeQuery();
					if (null != rs) {
						while (rs.next()) {
							GMROutTimeDTO book = new GMROutTimeDTO();
							book.setLr_no(rs.getString(LR_NO));
							book.setLrDate(rs.getDate(LR_DATE));
							book.setFrom(rs.getString(FROM_STATION));
							book.setTo(rs.getString(TO_STATION));
							book.setNo_of_articles(rs.getInt(NO_OF_ARTICLES));
							book.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
							book.setConsignorName(rs.getString(CONSIGNOR_NAME));
							book.setConsigneeName(rs.getString(CONSIGNEE_NAME));
							book.setOutTimeDate(rs.getDate(LAST_INWARD_DATE));
							book.setDdc(rs.getFloat(DOOR_DELIVERY_CHARGE));
							book.setArticle_value(rs.getFloat(ARTICLE_VALUE));
							book.setTotal(rs.getFloat(TOTAL));
							book.setLr_type(rs.getString(LR_TYPE));
							book.setArticle_type(String.valueOf(rs
									.getInt(ARTICLE_ID)));

							deliverlist.add(book);
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
		return deliverlist;
	}

	/**
	 * @param stationCode
	 * @return inList
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ArrayList getInTimeGoods(String stationCode) throws SQLException,
			Exception {

		Connection con = null;
		PreparedStatement pst = null;
		ArrayList<GMRInTimeDTO> inList = new ArrayList<GMRInTimeDTO>();
		ResultSet rs = null;

		try {

			DBHelper db = new DBHelper();
			con = db.getConnection();

			if (null != con) {

				pst = con.prepareStatement(JOIN_INTIMEWITHLR);
				if (pst != null) {

					pst.setString(1, stationCode);

					rs = pst.executeQuery();

					if (null != rs) {
						while (rs.next()) {
							GMRInTimeDTO book = new GMRInTimeDTO();
							book.setLr_no(rs.getString(LR_NO));
							book.setDate(rs.getDate(LR_DATE));
							book.setSent_date(rs.getDate(INWARD_TIME));
							book.setToday_date(rs.getDate(TODAY));
							book.setTotal(rs.getFloat(TOTAL));
							book.setLr_type(rs.getString(LR_TYPE));
							book.setDdc(rs.getFloat(DDC));
							book.setFrom(rs.getString(FROM_STATION));
							book.setTo(rs.getString(TO_STATION));
							book.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
							book.setVehicle_no(rs.getString(VEHICLE_NO));
							book.setConsignorName(rs.getString(CONSIGNOR_NAME));
							book.setConsigneeName(rs.getString(CONSIGNEE_NAME));
							book.setNo_of_articles(rs.getInt(NO_OF_ARTICLES));
							book.setArticle_value(String.valueOf(rs
									.getFloat(ARTICLE_VALUE)));
							book.setArticle_type(String.valueOf(rs
									.getInt(ARTICLE_ID)));
							book.setCnorPhone(rs.getString(CNOR_PHONE));
							book.setCneePhone(rs.getString(CNEE_PHONE));

							book.setRate_type(rs.getInt(RATE_TYPE));
							book.setSmsNotify(rs.getInt(SMS_NOTIFY));
							inList.add(book);

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
		return inList;
	}

	@SuppressWarnings("unchecked")
	public ArrayList getStockDetails(String stationCode) throws SQLException,
			Exception {

		ArrayList inList = new ArrayList();

		inList.add(getOutTimeGoods(stationCode));
		inList.add(getInTimeGoods(stationCode));
		inList.add(getDeliveredGoods(stationCode));

		return inList;
	}

	/**
	 * 
	 */
	public BookingDTO getBookingDetail(String lrno, String stationCode, String dbHis)
			throws BusinessException, SQLException, Exception {

		Connection con = null;
		PreparedStatement pst = null;
		BookingDTO book = null;
		ResultSet rs = null;

		try {

			if(dbHis == null || dbHis.equals("")){
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			}else{
				DBHelperHis helperHis = new DBHelperHis();
				con = helperHis.getConnectionHistory(dbHis);
			}
			if (null != con) {
				
				pst = con.prepareStatement(GET_BOOKINGDETAIL);

				if (pst != null) {

					pst.setString(1, lrno);

					rs = pst.executeQuery();				
					
						if (rs.next()) {
							//System.out.println("in current record- lr");
							book = new BookingDTO();

							book.setLrno(rs.getString(LR_NO));
							book.setDate(rs.getDate(LR_DATE));
							book.setType(rs.getString(LR_TYPE));
							String fromStation = rs.getString(FROM_STATION);
							book.setFrom(fromStation);
							String toStation = rs.getString(TO_STATION);
							book.setTo(toStation);
							book.setConsignorName(rs.getString(CONSIGNOR_NAME));
							book.setConsignor_address(rs
									.getString(CONSIGNOR_ADDRESS));
							book.setConsigneeName(rs.getString(CONSIGNEE_NAME));
							book.setConsignee_address(rs
									.getString(CONSIGNEE_ADDRESS));
							book.setConsignor_CST(rs.getString(CONSIGNOR_CST));
							book.setConsignee_GST(rs.getString(CONSIGNEE_GST));
							book.setArticle_id(rs.getInt(ARTICLE_ID));
							book.setArticle_value(rs.getFloat(ARTICLE_VALUE));
							book.setNo_of_articles(rs.getInt(NO_OF_ARTICLES));
							book.setArticle_description(rs
									.getString(ARTICLE_DESC));
							book.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
							book.setCharged_weight(rs.getFloat(CHARGED_WEIGHT));
							book.setBft(rs.getFloat(BASIC_FRIEGHT));
							book.setLrc(rs.getFloat(LRCHARGES));
							book.setCcc(rs.getFloat(CCC));
							book.setDdc(rs.getFloat(DOOR_DELIVERY_CHARGE));
							book.setDcc(rs.getFloat(DCC));
							book.setIec(rs.getFloat(IEC));
							book.setLc(rs.getFloat(LC));
							book.setGsc(rs.getFloat(GSC));
							book.setStax(rs.getFloat(SALES_TAX));
							book.setOther_charges(rs.getFloat(OTHER_CHARGES));
							book.setTotal(rs.getFloat(TOTAL));
							book.setArt_unit(rs.getInt(ART_UNIT));
							book.setRate_type(rs.getInt(RATE_TYPE));
							book.setCnorPhone(rs.getString(CNOR_PHONE));
							book.setCneePhone(rs.getString(CNEE_PHONE));
							book.setTotal_charged_wt(rs
									.getFloat(TOTAL_CHARGED_WT));
							
							book.setDhc(rs
									.getFloat(DHC));

							int status = rs.getInt(LR_STATUS);
							if (status == 1)
								book.setStatus(true);

							book.setCnorLandLine(rs.getString(CNOR_LANDLINE));
							book.setCneeLandLine(rs.getString(CNEE_LANDLINE));
							book.setStaxNo(rs.getBoolean(SSTAX));
							book.setIsUpd(rs.getString(ISUPD));
							book.setOldLrno(rs.getString(OLDLRNO));
							book.setPost(rs.getFloat(POST));
							book.setDemu(rs.getFloat(DEMU));
							book.setOldLrTotal(rs.getFloat(OLDLRTOTAL));

							ArticleDTO[] dto = getArticleDetails(con, lrno);

							book.setArticledto(dto);
							
						} else {

						//System.out.println("in old record- lr_history");
						pst = null;
						rs = null;

						pst = con.prepareStatement(GET_BOOKINGDETAIL_HISTORY);

						if (pst != null) {

							pst.setString(1, lrno);

							rs = pst.executeQuery();

							if (rs.next()) {
								book = new BookingDTO();

								book.setLrno(rs.getString(LR_NO));
								book.setDate(rs.getDate(LR_DATE));
								book.setType(rs.getString(LR_TYPE));
								String fromStation = rs.getString(FROM_STATION);
								book.setFrom(fromStation);
								String toStation = rs.getString(TO_STATION);
								book.setTo(toStation);
								book.setConsignorName(rs
										.getString(CONSIGNOR_NAME));
								book.setConsignor_address(rs
										.getString(CONSIGNOR_ADDRESS));
								book.setConsigneeName(rs
										.getString(CONSIGNEE_NAME));
								book.setConsignee_address(rs
										.getString(CONSIGNEE_ADDRESS));
								book.setConsignor_CST(rs
										.getString(CONSIGNOR_CST));
								book.setConsignee_GST(rs
										.getString(CONSIGNEE_GST));
								book.setArticle_id(rs.getInt(ARTICLE_ID));
								book.setArticle_value(rs
										.getFloat(ARTICLE_VALUE));
								book.setNo_of_articles(rs
										.getInt(NO_OF_ARTICLES));
								book.setArticle_description(rs
										.getString(ARTICLE_DESC));
								book.setActual_weight(rs
										.getFloat(ACTUAL_WEIGHT));
								book.setCharged_weight(rs
										.getFloat(CHARGED_WEIGHT));
								book.setBft(rs.getFloat(BASIC_FRIEGHT));
								book.setLrc(rs.getFloat(LRCHARGES));
								book.setCcc(rs.getFloat(CCC));
								book.setDdc(rs.getFloat(DOOR_DELIVERY_CHARGE));
								book.setDcc(rs.getFloat(DCC));
								book.setIec(rs.getFloat(IEC));
								book.setLc(rs.getFloat(LC));
								book.setGsc(rs.getFloat(GSC));
								book.setStax(rs.getFloat(SALES_TAX));
								book.setOther_charges(rs
										.getFloat(OTHER_CHARGES));
								book.setTotal(rs.getFloat(TOTAL));
								book.setArt_unit(rs.getInt(ART_UNIT));
								book.setRate_type(rs.getInt(RATE_TYPE));
								book.setCnorPhone(rs.getString(CNOR_PHONE));
								book.setCneePhone(rs.getString(CNEE_PHONE));
								book.setTotal_charged_wt(rs
										.getFloat(TOTAL_CHARGED_WT));

								book.setDhc(rs.getFloat(DHC));

								int status = rs.getInt(LR_STATUS);
								if (status == 1)
									book.setStatus(true);

								book.setCnorLandLine(rs
										.getString(CNOR_LANDLINE));
								book.setCneeLandLine(rs
										.getString(CNEE_LANDLINE));
								book.setStaxNo(rs.getBoolean(SSTAX));
								book.setIsUpd(rs.getString(ISUPD));
								book.setOldLrno(rs.getString(OLDLRNO));
								book.setPost(rs.getFloat(POST));
								book.setDemu(rs.getFloat(DEMU));
								book.setOldLrTotal(rs.getFloat(OLDLRTOTAL));


								ArticleDTO[] dto = getArticleDetailsHistory(
										con, lrno);

								book.setArticledto(dto);

							} else {
								BusinessException business = new BusinessException();
								business
										.setResponseMessage(RECORD_NOT_AVAILABLE);
								throw business;
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
		return book;

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
	 */
	private BookingDTO getBookingDetail(Connection con, String lrno) throws BusinessException,
			SQLException, Exception {		
		PreparedStatement pst = null;
		BookingDTO book = null;
		ResultSet rs = null;

		try {			

			if (null != con) {
				pst = con.prepareStatement(GET_BOOKINGDETAIL);

				if (pst != null) {

					pst.setString(1, lrno);

					rs = pst.executeQuery();

					if (null != rs) {
						if (rs.next()) {
							book = new BookingDTO();

							book.setLrno(rs.getString(LR_NO));
							Timestamp inward_time = rs.getTimestamp(LR_DATE);
							book.setDate(inward_time);
							Timestamp cancel_date = rs.getTimestamp(CREATEDON);
							book.setCancel_Date(cancel_date);
							book.setType(rs.getString(LR_TYPE));
							String fromStation = rs.getString(FROM_STATION);
							book.setFrom(fromStation);
							String toStation = rs.getString(TO_STATION);
							book.setTo(toStation);
							book.setConsignorName(rs.getString(CONSIGNOR_NAME));
							book.setConsignor_address(rs
									.getString(CONSIGNOR_ADDRESS));
							book.setConsigneeName(rs.getString(CONSIGNEE_NAME));
							book.setConsignee_address(rs
									.getString(CONSIGNEE_ADDRESS));
							book.setConsignor_CST(rs.getString(CONSIGNOR_CST));
							book.setConsignee_GST(rs.getString(CONSIGNEE_GST));
							book.setArticle_id(rs.getInt(ARTICLE_ID));
							book.setArticle_value(rs.getFloat(ARTICLE_VALUE));
							book.setNo_of_articles(rs.getInt(NO_OF_ARTICLES));
							book.setArticle_description(rs
									.getString(ARTICLE_DESC));
							book.setActual_weight(rs.getFloat(ACTUAL_WEIGHT));
							book.setCharged_weight(rs.getFloat(CHARGED_WEIGHT));
							book.setBft(rs.getFloat(BASIC_FRIEGHT));
							book.setLrc(rs.getFloat(LRCHARGES));
							book.setCcc(rs.getFloat(CCC));
							book.setDdc(rs.getFloat(DOOR_DELIVERY_CHARGE));
							book.setDcc(rs.getFloat(DCC));
							book.setIec(rs.getFloat(IEC));
							book.setLc(rs.getFloat(LC));
							book.setGsc(rs.getFloat(GSC));
							book.setStax(rs.getFloat(SALES_TAX));
							book.setOther_charges(rs.getFloat(OTHER_CHARGES));
							book.setTotal(rs.getFloat(TOTAL));
							book.setIsUpd(rs.getString(ISUPD));
							book.setOldLrno(rs.getString(OLDLRNO));
							book.setPost(rs.getFloat(POST));
							book.setDemu(rs.getFloat(DEMU));
							book.setOldLrTotal(rs.getFloat(OLDLRTOTAL));

							int status = rs.getInt(LR_STATUS);
							if (status == 1) {
								book.setCancelledOption(rs.getString(DESC));
							} else {
								book.setCancelledOption(null);
							}
							if (status > 1)
								book.setStatus(true);
							book.setRate_type(0);
						} else {
							pst = null;
							rs = null;
							pst = con
									.prepareStatement(GET_BOOKINGDETAIL_HISTORY);

							if (pst != null) {

								pst.setString(1, lrno);

								rs = pst.executeQuery();

								if (null != rs) {
									if (rs.next()) {

										book = new BookingDTO();

										book.setLrno(rs.getString(LR_NO));
										Timestamp inward_time = rs
												.getTimestamp(LR_DATE);
										book.setDate(inward_time);
										Timestamp cancel_date = rs
												.getTimestamp(CREATEDON);
										book.setCancel_Date(cancel_date);
										book.setType(rs.getString(LR_TYPE));
										String fromStation = rs
												.getString(FROM_STATION);
										book.setFrom(fromStation);
										String toStation = rs
												.getString(TO_STATION);
										book.setTo(toStation);
										book.setConsignorName(rs
												.getString(CONSIGNOR_NAME));
										book.setConsignor_address(rs
												.getString(CONSIGNOR_ADDRESS));
										book.setConsigneeName(rs
												.getString(CONSIGNEE_NAME));
										book.setConsignee_address(rs
												.getString(CONSIGNEE_ADDRESS));
										book.setConsignor_CST(rs
												.getString(CONSIGNOR_CST));
										book.setConsignee_GST(rs
												.getString(CONSIGNEE_GST));
										book.setArticle_id(rs
												.getInt(ARTICLE_ID));
										book.setArticle_value(rs
												.getFloat(ARTICLE_VALUE));
										book.setNo_of_articles(rs
												.getInt(NO_OF_ARTICLES));
										book.setArticle_description(rs
												.getString(ARTICLE_DESC));
										book.setActual_weight(rs
												.getFloat(ACTUAL_WEIGHT));
										book.setCharged_weight(rs
												.getFloat(CHARGED_WEIGHT));
										book.setBft(rs.getFloat(BASIC_FRIEGHT));
										book.setLrc(rs.getFloat(LRCHARGES));
										book.setCcc(rs.getFloat(CCC));
										book
												.setDdc(rs
														.getFloat(DOOR_DELIVERY_CHARGE));
										book.setDcc(rs.getFloat(DCC));
										book.setIec(rs.getFloat(IEC));
										book.setLc(rs.getFloat(LC));
										book.setGsc(rs.getFloat(GSC));
										book.setStax(rs.getFloat(SALES_TAX));
										book.setOther_charges(rs
												.getFloat(OTHER_CHARGES));
										book.setTotal(rs.getFloat(TOTAL));
										book.setIsUpd(rs.getString(ISUPD));
										book.setOldLrno(rs.getString(OLDLRNO));
										book.setPost(rs.getFloat(POST));
										book.setDemu(rs.getFloat(DEMU));
										book.setOldLrTotal(rs.getFloat(OLDLRTOTAL));

										int status = rs.getInt(LR_STATUS);
										if (status == 1) {
											book.setCancelledOption(rs
													.getString(DESC));
										} else {
											book.setCancelledOption(null);
										}
										if (status > 1)
											book.setStatus(true);
										book.setRate_type(1);

									} else {
										BusinessException business = new BusinessException();
										business
												.setResponseMessage(RECORD_NOT_AVAILABLE);
										throw business;
									}
								}
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
				if (null != pst)
					pst.close();
				
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (pst != null)
					pst.close();
				
			}
		}
		return book;

	}

	/**
	 * 
	 */
	public ArticleDTO[] getArticleTypes() throws SQLException, Exception {

		Connection con = null;
		PreparedStatement pst = null;
		ArrayList<ArticleDTO> list = new ArrayList<ArticleDTO>();
		ArticleDTO articleType = null;
		ResultSet rs = null;

		try {

			DBHelper db = new DBHelper();
			con = db.getConnection();

			if (null != con) {

				pst = con.prepareStatement(GET_ARTICLE_TYPES);

				if (pst != null) {

					rs = pst.executeQuery();

					if (null != rs) {

						while (rs.next()) {
							articleType = new ArticleDTO();
							articleType.setArticleId(rs.getInt(ARTICLE_ID));
							articleType.setType(rs.getString(ARTICLE_TYPE));
							articleType.setValue_per_km(rs
									.getFloat(VALUE_PER_KM));
							list.add(articleType);
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
			return (ArticleDTO[]) list.toArray(new ArticleDTO[size]);
		}

		return null;
	}

	/**
	 * Method for LR Tracking
	 */
	public LRTrackDTO[] trackLRDetails(String lrno, String dbHis) throws BusinessException,
			SQLException, Exception {

		ArrayList<LRTrackDTO> list = new ArrayList<LRTrackDTO>();
		LRTrackDTO track = new LRTrackDTO();
		BookingDTO booking = null;
		Connection con = null;
		PreparedStatement pst= null;
		ResultSet rs = null;
		
		try {

			if(dbHis == null || dbHis.equals("")){
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			}else{
				DBHelperHis helperHis = new DBHelperHis();
				con = helperHis.getConnectionHistory(dbHis);
			}
			con.setAutoCommit(false);
			// Get the lr Details
			booking = getBookingDetail(con, lrno);

			track.setLrNo(booking.getLrno());
			track.setLrDate(booking.getDate());
			track.setLrType(booking.getType());
			track.setFromLocation(booking.getFrom());
			track.setWeight(booking.getActual_weight());
			track.setToLocation(booking.getTo());
			track.setNo_of_articles(booking.getNo_of_articles());
			track.setCancelledOption(booking.getCancelledOption());
			track.setCancelDate(booking.getCancel_Date());
			// Fields Added
			track.setBft(booking.getTotal());
			track.setConsignorName(booking.getConsignorName());
			track.setConsigneeName(booking.getConsigneeName());
			track.setDdc(booking.getDdc());
			track.setIsUpd(booking.getIsUPd());
			track.setOldLrno(booking.getOldLrno());
			
			ActivityLog[] activities = null;
			
			if (null == track.getCancelledOption()){
				boolean isHistory = false;
				
				if(booking.getRate_type() == 1){
					System.out.println("Lr Tracking History - Inside History");
					isHistory = true;
					activities = getLRTrackingInfo(con, TRACKING_TABLE_ARCHIVE_HISTORY, lrno,isHistory);
				}else {
				if (!booking.getStatus()) { // Is Delivered
					System.out.println("Lr Tracking - Inside Not delivered");
					activities = getLRTrackingInfo(con, TRACKING_TABLE, lrno,isHistory);
				} else {
					System.out.println("Lr Tracking - Inside delivered");
					activities = getLRTrackingInfo(con, TRACKING_TABLE_ARCHIVE, lrno,isHistory);
				}
				}
				
				
			}else {
				activities = new ActivityLog[1];
				activities[0] = new ActivityLog();
				activities[0].setActivityTime(track.getLrDate());
				activities[0].setStationCode(track.getFromLocation());
				activities[0].setActivity(ARRIVED_STATUS);

			}
			
			 if(track.getIsUPd()!= null && track.getIsUPd().length() >= 10){
				 ActivityLog[] oldlractivity = null;
				 	
				 	int lr_status=0;
				 	int rate_type = 0;
				 	String desc = null;
					String newLr_no = track.getIsUPd().substring(0,track.getIsUPd().indexOf(" - ")).trim();
					System.out.println("newlr_no"+newLr_no);
					//boolean isHistory = false;
					String GET_OLDLR_STATUS = "select lr_status,rate_type,`desc`,to_station,createdon,lr_date from lr where lr_no = ?";
					if (null != con) {
						pst = con.prepareStatement(GET_OLDLR_STATUS);
						if (null != pst) {
							pst.setString(1, newLr_no);
							rs = pst.executeQuery();
							if (null != rs) {
								while (rs.next()) {
									rate_type = rs.getInt(RATE_TYPE);
									track.setLr_status(rs.getInt(LR_STATUS));
									track.setDesc(rs.getString(DESC));
									track.setTo_station(rs.getString(TO_STATION));
									track.setCreatedon(rs.getString(CREATEDON));
									track.setLr_date(rs.getString(LR_DATE));
									
									
									
								}
							}
						}
					}
					System.out.println("length--->"+activities.length);
					track.setRate_type(activities.length);
					if (track.getLr_status() != 1){
						boolean isHistory = false;
						if(rate_type == 1){
							System.out.println("Lr Tracking History - Inside History");
							isHistory = true;
							oldlractivity = getLRTrackingInfo(con, TRACKING_TABLE_ARCHIVE_HISTORY, newLr_no,isHistory,activities);
						}else {
						if (track.getLr_status() < 1) { // Is Delivered
							System.out.println("Lr Tracking - Inside Not delivered");
							oldlractivity = getLRTrackingInfo(con, TRACKING_TABLE, newLr_no,isHistory,activities);
						} else {
							System.out.println("Lr Tracking - Inside delivered");
							oldlractivity = getLRTrackingInfo(con, TRACKING_TABLE_ARCHIVE, newLr_no,isHistory,activities);
						}
						}
					}else{
						oldlractivity = new ActivityLog[activities.length+1];
						int activitylen = activities.length;
						if (activitylen > 0) {

							for (int k = 0; k < activitylen; k++) {
								oldlractivity[k] = activities[k];
							}
						}
						System.out.println("lr_date"+track.getLrDate());
						//oldlractivity = new ActivityLog[1];
						oldlractivity[activitylen] = new ActivityLog();
						oldlractivity[activitylen].setActivityTime(track.getLrDate());
						oldlractivity[activitylen].setStationCode(track.getFromLocation());
						oldlractivity[activitylen].setActivity(ARRIVED_STATUS);
					}
					track.setActivities(oldlractivity);

					list.add(track);
				}else{
			
			
					track.setActivities(activities);

					list.add(track);
				}
			 

			int size = list.size();

			if (size > 0) {
				return list.toArray(new LRTrackDTO[size]);
			}

			con.commit();
			
		} catch (Exception exception) {
			con.rollback();
			throw exception;
		}finally {
			try {				
				if (null != con)
					con.close();
			} catch (Exception exception) {
				if (con != null)
					con.close();
			}
		}

		return null;
	}

	private ActivityLog[] getLRTrackingInfo(Connection con,
			String trackingTable, String newLr_no,
			boolean isHistory, ActivityLog[] activities) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<ActivityLog> list = new ArrayList<ActivityLog>();
		ActivityLog dto = null;		
		PreparedStatement pst = null;
		ResultSet rs = null;
		int cractlength = 0;
		int activitylen = 0;

		try {		
			activitylen = activities.length;
			System.out.println("activitylen--->"+activitylen);
			if (activitylen > 0) {

				for (int k = 0; k < activitylen; k++) {
					//if (!CR_BOOKED_ACT.equals(activities[k].getActivity())) {
						list.add(activities[k]);
					//}
				}
			}
			ActivityLog[] crActivities = getCRActivity(newLr_no, con,isHistory);
			if (null != crActivities)
				cractlength = crActivities.length;
			System.out.println("cractlen--->"+cractlength);

			String GET_LRTRACK = "SELECT "
					+ "a.lr_no,a.station_code,a.lr_status,a.dispatch_to,"
					+ "a.inward_time,a.outward_time, a.vehicle_no " + "FROM "
					+ trackingTable + " a " + "WHERE " + "a.lr_no = ? "
					+ "order by a.inward_time ";

			if (null != con) {
				pst = con.prepareStatement(GET_LRTRACK);
				if (null != pst) {
					pst.setString(1, newLr_no);
					rs = pst.executeQuery();
					if (null != rs) {
						while (rs.next()) {
							String status = rs.getString(LR_STATUS);
							if (!status.equals(TOARRIVE_STATUS)) {
								Timestamp outward_time = rs
										.getTimestamp(OUTWARD_TIME);
								Timestamp inward_time = rs
										.getTimestamp(INWARD_TIME);
								String stationcode = rs.getString(STATION_CODE);

								String tostationcode = rs
										.getString(DISPATCH_TO);
								String vehicleno = rs.getString(VEHICLE_NO);

								if (status.equals(ARRIVED_STATUS)) {
									dto = getArrivedActivity(stationcode,
											inward_time);
									list.add(dto);

								} else if (status.equals(DISPATCH_STATUS)) {

									dto = getArrivedActivity(stationcode,inward_time);
									list.add(dto);

									dto = getDispatchedActivity(stationcode,tostationcode, outward_time,vehicleno);
									list.add(dto);

								} else if (status.equals(DELIVERED_STATUS)) {
									dto = getArrivedActivity(stationcode,inward_time);
									list.add(dto);

									dto = getDeliveredActivity(stationcode,tostationcode, outward_time,vehicleno);
									if (cractlength > 0)
										dto	.setCrno(crActivities[cractlength - 1].getCrno());
									list.add(dto);
								}
							}
						}
					}
				}
			}
			if (cractlength > 0) {
				
				for (int k = 0; k < cractlength; k++) {
					if (!CR_BOOKED_ACT.equals(crActivities[k].getActivity())) {
						list.add(crActivities[k]);
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
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (pst != null)
					pst.close();
				
			}
		}
		
		int size = list.size();System.out.println("size--->"+size);
		if (size > 0) {
			return (ActivityLog[]) list.toArray(new ActivityLog[size]);
		}

		return null;
		
	}

	/**
	 * 
	 * @param con 
	 * @param trackingTable
	 * @param lrno
	 * @param isHistory 
	 * @return
	 * @throws Exception
	 */
	private ActivityLog[] getLRTrackingInfo(Connection con, String trackingTable, String lrno, boolean isHistory)
			throws Exception {
		ArrayList<ActivityLog> list = new ArrayList<ActivityLog>();
		ActivityLog dto = null;		
		PreparedStatement pst = null;
		ResultSet rs = null;
		int cractlength = 0;

		try {			

			ActivityLog[] crActivities = getCRActivity(lrno, con,isHistory);
			if (null != crActivities)
				cractlength = crActivities.length;

			String GET_LRTRACK = "SELECT "
					+ "a.lr_no,a.station_code,a.lr_status,a.dispatch_to,"
					+ "a.inward_time,a.outward_time, a.vehicle_no " + "FROM "
					+ trackingTable + " a " + "WHERE " + "a.lr_no = ? "
					+ "order by a.inward_time ";

			if (null != con) {
				pst = con.prepareStatement(GET_LRTRACK);
				if (null != pst) {
					pst.setString(1, lrno);
					rs = pst.executeQuery();
					if (null != rs) {
						while (rs.next()) {
							String status = rs.getString(LR_STATUS);
							if (!status.equals(TOARRIVE_STATUS)) {
								Timestamp outward_time = rs
										.getTimestamp(OUTWARD_TIME);
								Timestamp inward_time = rs
										.getTimestamp(INWARD_TIME);
								String stationcode = rs.getString(STATION_CODE);

								String tostationcode = rs
										.getString(DISPATCH_TO);
								String vehicleno = rs.getString(VEHICLE_NO);

								if (status.equals(ARRIVED_STATUS)) {
									dto = getArrivedActivity(stationcode,
											inward_time);
									list.add(dto);

								} else if (status.equals(DISPATCH_STATUS)) {

									dto = getArrivedActivity(stationcode,
											inward_time);
									list.add(dto);

									dto = getDispatchedActivity(stationcode,
											tostationcode, outward_time,
											vehicleno);
									list.add(dto);

								} else if (status.equals(DELIVERED_STATUS)) {
									dto = getArrivedActivity(stationcode,
											inward_time);
									list.add(dto);

									dto = getDeliveredActivity(stationcode,
											tostationcode, outward_time,
											vehicleno);
									if (cractlength > 0)
										dto
												.setCrno(crActivities[cractlength - 1]
														.getCrno());
									list.add(dto);
								}
							}
						}
					}
				}
			}
			if (cractlength > 0) {

				for (int k = 0; k < cractlength; k++) {
					if (!CR_BOOKED_ACT.equals(crActivities[k].getActivity())) {
						list.add(crActivities[k]);
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
			} catch (Exception exception) {
				if (null != rs)
					rs.close();
				if (pst != null)
					pst.close();
				
			}
		}

		int size = list.size();
		if (size > 0) {
			return (ActivityLog[]) list.toArray(new ActivityLog[size]);
		}

		return null;
	}

	/**
	 * Method to build the Arrived Activity dto for the given station code and
	 * inward time
	 * 
	 * @return
	 */
	private ActivityLog getArrivedActivity(String stationcode, Date inward_time) {
		ActivityLog dto = new ActivityLog();

		dto.setActivityTime(inward_time);
		dto.setStationCode(stationcode);
		dto.setActivity(ARRIVED_STATUS);

		return dto;
	}

	private ActivityLog getDeliveredActivity(String stationcode,
			String tostationcode, Date outward_time, String vehicleno) {

		ActivityLog dto = new ActivityLog();

		dto.setActivity(DELIVERED_STATUS);
		dto.setActivityTime(outward_time);
		dto.setStationCode(stationcode);
		dto.setToStationCode(tostationcode);
		dto.setVehicleNo(vehicleno);

		return dto;
	}

	/**
	 * Method to build the dispatched Activity dto with the given information
	 * 
	 * @param stationcode
	 *            Station Code
	 * @param tostationcode
	 *            Destination Station code
	 * @param outward_time
	 *            Outward Time
	 * @param vehicleno
	 *            Vehicle Number
	 * @param contact
	 *            Contact details
	 * 
	 * @return
	 */
	private ActivityLog getDispatchedActivity(String stationcode,
			String tostationcode, Date outward_time, String vehicleno) {

		ActivityLog dto = new ActivityLog();

		dto.setActivityTime(outward_time);
		dto.setStationCode(stationcode);
		dto.setToStationCode(tostationcode);
		dto.setVehicleNo(vehicleno);
		dto.setActivity(DISPATCH_STATUS);

		return dto;
	}

	/**
	 * 
	 * @param con
	 * @param lrNo
	 * @param isHistory 
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	private ActivityLog[] getCRActivity(String lrNo, Connection con, boolean isHistory)
	throws SQLException, Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		ActivityLog[] activities = null;

		try {
			
			if(isHistory == true){
				ps = con.prepareStatement(CHECK_CR_HISTORY);
			}else{
				ps = con.prepareStatement(CHECK_CR);
			}
			

			if (ps != null) {
				ps.setString(1, lrNo);
				rs = ps.executeQuery();
				if (null != rs) {
					rs.last();
					int count = rs.getRow();
					activities = new ActivityLog[count];
					rs.beforeFirst();
					int i = 0;
					int status = 0;
					while (rs.next()) {

						status = rs.getInt(CR_STATUS);
						activities[i] = new ActivityLog();
						activities[i].setStationCode(rs.getString(STATION_CODE));

						if (status == 0 || status == 2) {
							activities[i].setActivity(CR_BOOKED_ACT);
						} else if (status == 1) {
							activities[i].setActivity(CR_CANCELLED_ACT);
							Date canceledDeliverDate = rs
									.getTimestamp(LR_DELIVERY_DATE);
							activities[i]
									.setCancelledDeliverTime(canceledDeliverDate);
						}

						Date crCancelDate = rs.getTimestamp(CR_CANCEL_DATE);
						activities[i].setActivityTime(crCancelDate);
						activities[i++].setCrno(rs.getString(CR_NO));
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
				if (null != ps)
					ps.close();

			}
		}

		return activities;
	}
	

	/**
	 * 
	 * @param con
	 * @param code
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	/*
	 * private String getStationName(Connection con, String code) throws
	 * SQLException, Exception { PreparedStatement ps = null;
	 * 
	 * String name = null;
	 * 
	 * ResultSet rs = null;
	 * 
	 * try { ps = con.prepareStatement(GET_STATION_NAME);
	 * 
	 * if (ps != null) { ps.setString(1, code);
	 * 
	 * rs = ps.executeQuery(); if (null != rs) {
	 * 
	 * if (rs.next()) { name = rs.getString("name"); } } } } catch (SQLException
	 * sqlexception) { sqlexception.printStackTrace(); throw sqlexception; }
	 * catch (Exception exception) { exception.printStackTrace(); throw
	 * exception; } finally { try { if (null != rs) rs.close(); if (null != ps)
	 * ps.close(); } catch (Exception exception) { if (null != rs) rs.close();
	 * if (null != ps) ps.close(); } }
	 * 
	 * return name; }
	 */

	@Override
	/**
	 * 
	 */
	public BookingDTO[] getUsedLRNumbers(String station_code,
			Date allocationDate, String type) throws BusinessException, Exception {

		Connection con = null;
		PreparedStatement pst = null;
		ArrayList<BookingDTO> inList = new ArrayList<BookingDTO>();
		ResultSet rs = null;

		try {

			DBHelper db = new DBHelper();
			con = db.getConnection();

			if (null != con) {
				pst = con.prepareStatement(GET_USED_LR_NO);
				if (pst != null) {
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					pst.setString(1, station_code);
					pst.setString(2, type);
					pst.setString(3, dateFormat.format(allocationDate));
					
					pst.setString(4, station_code);
					pst.setString(5, type);
					pst.setString(6, dateFormat.format(allocationDate));
					rs = pst.executeQuery();

					if (null != rs) {
						while (rs.next()) {
							BookingDTO book = new BookingDTO();
							book.setLrno(rs.getString(LR_NO));
							book.setType(rs.getString(LR_TYPE));
							inList.add(book);
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

		int size = inList.size();
		if (size > 0) {
			return (BookingDTO[]) inList.toArray(new BookingDTO[size]);
		}else{
			BookingDTO[] book = new BookingDTO[1];
			book[0] = new BookingDTO();
			book[0].setType(type);
			book[0].setLrno("None");
			return book;
		}
		
	}
	

	public BookingDTO[] getUnUsedLRList(String stationCode) throws Exception {

		Connection con = null;
		PreparedStatement pst = null;
		ArrayList<BookingDTO> inList = new ArrayList<BookingDTO>();
		ResultSet rs = null;
		System.out.println("in un used lr-->");
		try {

			DBHelper db = new DBHelper();
			con = db.getConnection();

			if (null != con) {
				pst = con.prepareStatement(GET_UN_USED_LR_LIST);
				if (pst != null) {					
					pst.setString(1, stationCode);
					
					rs = pst.executeQuery();

					if (null != rs) {
						while (rs.next()) {
							BookingDTO book = new BookingDTO();
							book.setLrno(rs.getString(LR_NO));
							book.setType(rs.getString(TYPE));
							inList.add(book);
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

		int size = inList.size();
		
		if (size > 0) {
			System.out.println("un used size-->"+size);
			return (BookingDTO[]) inList.toArray(new BookingDTO[size]);
		}
		
		return null;
	}
	

	/**
	 * 
	 * @param con
	 * @param station_code
	 * @param inList
	 * @throws Exception
	 */
	private void insertUsedCRNumber(Connection con, String station_code,
			ArrayList<BookingDTO> inList) throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			if (null != con) {

				pst = con.prepareStatement(GET_USED_CR_NO);

				if (pst != null) {

					pst.setString(1, station_code);
					rs = pst.executeQuery();

					if (null != rs) {
						while (rs.next()) {
							BookingDTO book = new BookingDTO();
							book.setLrno(rs.getString(CR_NO));
							book.setType("CR");
							inList.add(book);
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
				if (null != pst)
					pst.close();
			} catch (Exception exception) {
				if (pst != null)
					pst.close();
			}
		}

	}

	@Override
	public void insertCommodities(ArticleDTO[] commodity) throws SQLException,
			Exception {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				pst = con.prepareStatement(INSERT_COMMODITIES);
				if (null != pst) {
					int batch_count = commodity.length;
					for (int i = 0; i < batch_count; i++) {
						pst.setString(1, commodity[i].getType());
						pst.setFloat(2, commodity[i].getValue_per_km());
						pst.addBatch();
					}
					pst.executeBatch();
				}
			}
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != pst) {
					pst.close();
					pst = null;
				}
				if (null != con) {
					con.close();
					con = null;
				}
			} catch (Exception excep) {
				pst = null;
				con = null;
			}
		}

	}

	/**
	 * Method to set the commodities This method is called for update of
	 * commodity data.
	 * 
	 * @param commodity
	 *            ArticleDTO
	 * 
	 * @throws SQLException
	 * @throws Exception
	 */
	public void updateCommodities(ArticleDTO[] commodity) throws SQLException,
			Exception {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				pst = con.prepareStatement(UPDATE_COMMODITIES);
				if (null != pst) {
					int batch_count = commodity.length;
					for (int i = 0; i < batch_count; i++) {
						pst.setFloat(1, commodity[i].getValue_per_km());
						pst.setString(2, commodity[i].getType());
						pst.addBatch();
					}
					pst.executeBatch();
				}
			}
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != pst) {
					pst.close();
					pst = null;
				}
				if (null != con) {
					con.close();
					con = null;
				}
			} catch (Exception excep) {
				pst = null;
				con = null;
			}
		}
	}

	/**
	 * 
	 */
	public void deleteCommodity(String articleName) throws SQLException,
			Exception {
		Connection con = null;
		PreparedStatement pst = null;

		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				pst = con.prepareStatement(COMMODITIES_REINITIAL);
				if (null != pst) {
					pst.setString(1, articleName);
					pst.executeUpdate();
				}
			}
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != pst) {
					pst.close();
					pst = null;
				}
				if (null != con) {
					con.close();
					con = null;
				}
			} catch (Exception excep) {
				pst = null;
				con = null;
			}
		}
	}

	@Override
	public BookingDTO[] getViewBill(String customerName, String stationCode,
			String month, String year, boolean bookingType)
			throws SQLException, Exception {
		Connection con = null;
		PreparedStatement pst = null;
		BookingDTO bill = null;
		ArrayList<BookingDTO> viewBillList = new ArrayList<BookingDTO>();
		ResultSet rs = null;

		try {

			DBHelper db = new DBHelper();
			con = db.getConnection();

			if (null != con) {

				if (bookingType) {
					pst = con.prepareStatement(VIEW_BILL_INWARD);
				} else {
					pst = con.prepareStatement(VIEW_BILL_OUTWARD);
				}

				if (pst != null) {
					pst.setString(1, month);
					pst.setString(2, year);
					pst.setString(3, customerName);
					pst.setString(4, stationCode);

					rs = pst.executeQuery();

					if (null != rs) {
						while (rs.next()) {

							bill = new BookingDTO();

							bill.setLrno(rs.getString(LR_NO));
							bill.setDate(rs.getDate(LR_DATE));
							bill.setTo(rs.getString(TO_STATION));
							bill.setFrom(rs.getString(FROM_STATION));
							bill.setNo_of_articles(rs.getInt(NO_OF_ARTICLES));
							bill.setCharged_weight(rs.getFloat(CHARGED_WEIGHT));
							bill.setBft(rs.getFloat(BASIC_FRIEGHT));
							bill.setLrc(rs.getFloat(LRCHARGES));
							bill.setCcc(rs.getFloat(CCC));
							bill.setDdc(rs.getFloat(DOOR_DELIVERY_CHARGE));
							bill.setDcc(rs.getFloat(DCC));
							bill.setIec(rs.getFloat(IEC));
							bill.setLc(rs.getFloat(LC));
							bill.setGsc(rs.getFloat(GSC));
							bill.setStax(rs.getFloat(SALES_TAX));
							bill.setOther_charges(rs.getFloat(OTHER_CHARGES));
							bill.setTotal(rs.getFloat(TOTAL));
							bill.setDeliveredDate(rs.getDate(DELIVERED_DATE));
							bill.setInvoiceNo(rs.getString(INVOICE_NO));
							viewBillList.add(bill);

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

		int size = viewBillList.size();
		if (size > 0) {
			return (BookingDTO[]) viewBillList.toArray(new BookingDTO[size]);
		}
		return null;
	}

	/**
	 * 
	 */
	public void insertSundryArticle(String articleType) throws Exception {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				pst = con.prepareStatement(INSERT_SUNDRY_ARTICLE);
				if (null != pst) {

					pst.setString(1, articleType);

					pst.executeUpdate();
				}
			}
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != pst) {
					pst.close();
					pst = null;
				}
				if (null != con) {
					con.close();
					con = null;
				}
			} catch (Exception excep) {
				pst = null;
				con = null;
			}
		}
	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public ArticleDTO[] getSundryArticles() throws SQLException, Exception {
		Connection con = null;
		PreparedStatement pst = null;
		ArrayList<ArticleDTO> list = new ArrayList<ArticleDTO>();
		ArticleDTO articleType = null;
		ResultSet rs = null;

		try {

			DBHelper db = new DBHelper();
			con = db.getConnection();

			if (null != con) {

				pst = con.prepareStatement(GET_SUNDRY_ARTICLES);

				if (pst != null) {

					rs = pst.executeQuery();

					if (null != rs) {
						while (rs.next()) {
							articleType = new ArticleDTO();
							articleType.setArticleId(rs.getInt(ARTICLE_ID));
							articleType.setType(rs.getString(ARTICLE_TYPE));
							list.add(articleType);
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
			return (ArticleDTO[]) list.toArray(new ArticleDTO[size]);
		}

		return null;
	}

	/**
	 * 
	 * @param articleType
	 * @throws SQLException
	 * @throws Exception
	 */
	public void deleteSundryArticle(String articleType) throws SQLException,
			Exception {
		Connection con = null;
		PreparedStatement pst = null;

		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				pst = con.prepareStatement(DELETE_SUNDRY_ARTICLE);
				if (null != pst) {
					pst.setString(1, articleType);
					pst.executeUpdate();
				}
			}
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != pst) {
					pst.close();
					pst = null;
				}
				if (null != con) {
					con.close();
					con = null;
				}
			} catch (Exception excep) {
				pst = null;
				con = null;
			}
		}
	}

	@Override
	public int getSpecialDiscounter(String code, String toStation)
			throws RemoteException, NamingException, CreateException, Exception {
		Connection con = null;
		PreparedStatement pst = null;
		int special = 0;
		ResultSet rs = null;

		try {

			DBHelper db = new DBHelper();
			con = db.getConnection();

			if (null != con) {

				pst = con.prepareStatement(GET_SPECIAL_DISCOUNTER);

				if (pst != null) {

					pst.setString(1, code);
					pst.setString(2, toStation);
					rs = pst.executeQuery();

					if (null != rs) {
						while (rs.next()) {
							special = rs.getInt(DISCOUNT);
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

		return special;
	}

	public VehicleDTO[] getVehicles() throws Exception{
		Connection con = null;
		PreparedStatement pst = null;
		ArrayList<VehicleDTO> list = new ArrayList<VehicleDTO>();
		VehicleDTO vehicle = null;
		ResultSet rs = null;
		System.out.println("In GoodsBean Getvehicles--->");
		try {

			DBHelper db = new DBHelper();
			con = db.getConnection();

			if (null != con) {

				pst = con.prepareStatement(GET_VEHICLES);

				if (pst != null) {

					rs = pst.executeQuery();

					if (null != rs) {
						while (rs.next()) {
							vehicle = new VehicleDTO();
							vehicle.setVehicle_model(rs.getString(VEHICLE_MODEL));							
							list.add(vehicle);
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
		System.out.println("Out GoodsBean Getvehicles--->"+size);
		if (size > 0) {
			return (VehicleDTO[]) list.toArray(new VehicleDTO[size]);
		}

		return null;
	}
	

	
	public GMRDTO getoldLrGMROdetails(String lrno,String stationcode ) throws Exception{
		Connection con = null;
		PreparedStatement pst = null;
		//ArrayList<VehicleDTO> list = new ArrayList<VehicleDTO>();
		GMRDTO gmr = null;
		ResultSet rs = null;
		System.out.println("In GoodsBean getoldlrGMROdetails--->");
		try {

			DBHelper db = new DBHelper();
			con = db.getConnection();

			if (null != con) {
				
				pst = con.prepareStatement(GET_OLDLR_GMRO_DETAILS);

				if (pst != null) {
					pst.setString(1, lrno);
					pst.setString(2, stationcode);

					rs = pst.executeQuery();

					if (null != rs) {
						while (rs.next()) {
							gmr = new GMRDTO();
							gmr.setDestinationStn(rs.getString(FROM_STATION));
							gmr.setLrDate(rs.getDate(INWARD_TIME));
							//System.out.println("inwardtime---"+rs.getString(INWARD_TIME));
							gmr.setCneeName(rs.getString(CONSIGNEE_NAME));
							//System.out.println("cneename-->"+rs.getString(CONSIGNEE_NAME));
							
							
							
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

		/*int size = list.size();
		System.out.println("Out GoodsBean Getvehicles--->"+size);
		if (size > 0) {
			return (VehicleDTO[]) list.toArray(new VehicleDTO[size]);
		}*/
		System.out.println("gmrr-->");
		return gmr;
	}

	

	
	
	
}
