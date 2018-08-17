package com.hm.dao;

import hm.akr.dto.BookingCommissionDTO;
import hm.akr.dto.CardDTO;
import hm.akr.dto.CartageCommissionDTO;
import hm.akr.dto.CommissionSummaryDTO;
import hm.akr.dto.DDDetailsDTO;
import hm.akr.dto.DeliveryCommissionDTO;
import hm.akr.dto.ProfileDTO;
import hm.akr.dto.RefundRecoveryDTO;
import hm.akr.dto.StationDTO;
import hm.akr.exceptions.BusinessException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CommissionDAO implements ICommissionDAO, DAOConstants {

	/**
	 * Constructor method
	 */
	public CommissionDAO() {
		super();

	}

	private void insertProfile(Connection con, ProfileDTO profile,
			String profile_id) throws SQLException, Exception {
		PreparedStatement pst = null;

		try {
			if (null != con) {
				pst = con.prepareStatement(CREATE_PROFILE);
				if (null != pst) {
					int batch_count = profile.getCard().length;
					for (int i = 0; i < batch_count; i++) {
						pst.setString(1, profile_id);
						pst.setString(2, profile.getProfileName());
						pst.setString(3, profile.getCard()[i].getType());
						pst
								.setFloat(4, profile.getCard()[i]
										.getAboveCardrate());
						pst
								.setFloat(5, profile.getCard()[i]
										.getEqualCardrate());
						pst.setFloat(6, profile.getCard()[i]
								.getUpto20Cardrate());
						pst.setFloat(7, profile.getCard()[i]
								.getAbove20Cardrate());
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
			} catch (Exception excep) {
				pst = null;
			}
		}
	}
	
	private void insertCCProfile(Connection con, ProfileDTO profile) throws SQLException, Exception {
		PreparedStatement pst = null;

		try {
			if (null != con) {
				pst = con.prepareStatement(CREATE_CC_PROFILE);
				if (null != pst) {

					pst.setString(1, profile.getProfileName());
					pst.setInt(2, profile.getCc_limit());
					pst.setInt(3, profile.getCc_consider());
					pst.setInt(4, profile.getCc_refund());
					pst.setInt(5, profile.getCcforspecial());
					pst.setInt(6, profile.getCcforcommodity());

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
			} catch (Exception excep) {
				pst = null;
			}
		}
	}

	private void updateProfile(Connection con, ProfileDTO profile)
			throws SQLException, Exception {
		PreparedStatement pst = null;

		try {
			pst = con.prepareStatement(UPDATE_PROFILE);
			if (null != pst) {
				int batch_count = profile.getCard().length;

				for (int i = 0; i < batch_count; i++) {

					pst.setFloat(1, profile.getCard()[i].getAboveCardrate());
					pst.setFloat(2, profile.getCard()[i].getEqualCardrate());
					pst.setFloat(3, profile.getCard()[i].getUpto20Cardrate());
					pst.setFloat(4, profile.getCard()[i].getAbove20Cardrate());
					pst.setString(5, profile.getProfileName());
					pst.setString(6, profile.getCard()[i].getType());
					pst.addBatch();
				}
				pst.executeBatch();
			}
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != pst) {
					pst.close();
					pst = null;
				}
			} catch (Exception excep) {
				pst = null;
			}
		}
	}
	
	private void updateCCProfile(Connection con, ProfileDTO profile)
	throws SQLException, Exception {
		PreparedStatement pst = null;

		try {
			pst = con.prepareStatement(UPDATE_CC_PROFILE);
			if (null != pst) {

				pst.setInt(1, profile.getCc_limit());
				pst.setInt(2, profile.getCc_consider());
				pst.setInt(3, profile.getCc_refund());
				pst.setInt(4, profile.getCcforspecial());
				pst.setInt(5, profile.getCcforcommodity());
				pst.setString(6, profile.getProfileName());
				pst.executeUpdate();
			}
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != pst) {
					pst.close();
					pst = null;
				}
			} catch (Exception excep) {
				pst = null;
			}
		}
	}

	private String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	private ProfileDTO[] getProfileList(Connection con) throws SQLException,
			Exception {

		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ProfileDTO> profileList = new ArrayList<ProfileDTO>();
		ProfileDTO profile = null;
		CardDTO[] card = null;

		try {
			ps = con.prepareStatement(GET_PROFILE_LIST);
			if (ps != null) {
				rs = ps.executeQuery();
				while (rs.next()) {
					profile = new ProfileDTO();
					profile.setProfileId(rs.getString(PROFILE_ID));
					profile.setProfileName(rs.getString(PROFILE_NAME));
					card = getCards(con, profile.getProfileName());
					profile.setCard(card);
					profileList.add(profile);
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
		int size = profileList.size();
		if (size > 0) {
			return (ProfileDTO[]) profileList.toArray(new ProfileDTO[size]);
		}

		return null;
	}
	
	private ProfileDTO[] getCCProfileList(Connection con) throws SQLException,
	Exception {

		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ProfileDTO> profileList = new ArrayList<ProfileDTO>();
		ProfileDTO profile = null;
		
		try {
			ps = con.prepareStatement(GET_CC_PROFILE_LIST);
			if (ps != null) {
				rs = ps.executeQuery();
				while (rs.next()) {
					profile = new ProfileDTO();
					profile.setProfileName(rs.getString(PROFILE_NAME));
					profile.setCc_consider(rs.getInt("cc_consider"));
					profile.setCc_limit(rs.getInt("cc_limit"));
					profile.setCc_refund(rs.getInt("cc_refund"));
					profile.setCcforcommodity(rs.getInt("ccforcommodity"));
					profile.setCcforspecial(rs.getInt("ccforspecial"));
					profileList.add(profile);
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
		int size = profileList.size();
		if (size > 0) {
			return (ProfileDTO[]) profileList.toArray(new ProfileDTO[size]);
		}

		return null;
	}

	

	private CardDTO[] getCardList(Connection con) throws SQLException,
			Exception {

		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<CardDTO> cardList = new ArrayList<CardDTO>();
		CardDTO card = null;
		try {
			ps = con.prepareStatement(GET_CARD_PROFILE);
			if (ps != null) {
				rs = ps.executeQuery();
				while (rs.next()) {
					card = new CardDTO();
					card.setType(rs.getString(LR_TYPE));
					card.setAboveCardrate(rs.getFloat(ABOVE_CARD_RATE));
					card.setEqualCardrate(rs.getFloat(EQUALS_CARD_RATE));
					card.setUpto20Cardrate(rs.getFloat(UPTO_20));
					card.setAbove20Cardrate(rs.getFloat(ABOVE_20));
					cardList.add(card);
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

		int size = cardList.size();

		if (size > 0) {
			return (CardDTO[]) cardList.toArray(new CardDTO[size]);
		}

		return null;
	}

	private ProfileDTO[] getProfile(Connection con, String profileName)
			throws SQLException, Exception {

		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ProfileDTO> profileList = new ArrayList<ProfileDTO>();
		ProfileDTO profile = null;
		CardDTO[] card = null;

		try {
			ps = con.prepareStatement(GET_PROFILE);
			if (ps != null) {
				ps.setString(1, profileName);
				rs = ps.executeQuery();
				while (rs.next()) {
					profile = new ProfileDTO();
					profile.setProfileId(rs.getString(PROFILE_ID));
					profile.setProfileName(rs.getString(PROFILE_NAME));
					card = getCards(con, profileName);
					profile.setCard(card);
					profileList.add(profile);
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
		int size = profileList.size();
		if (size > 0) {
			return (ProfileDTO[]) profileList.toArray(new ProfileDTO[size]);
		}

		return null;
	}
	
	private ProfileDTO[] getCCProfile(Connection con, String profileName)
	throws SQLException, Exception {

		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ProfileDTO> profileList = new ArrayList<ProfileDTO>();
		ProfileDTO profile = null;

		try {
			ps = con.prepareStatement(GET_CC_PROFILE);
			if (ps != null) {
				ps.setString(1, profileName);
				rs = ps.executeQuery();
				while (rs.next()) {
					profile = new ProfileDTO();

					profile.setProfileName(rs.getString(PROFILE_NAME));
					profile.setCc_consider(rs.getInt("cc_consider"));
					profile.setCc_limit(rs.getInt("cc_limit"));
					profile.setCc_refund(rs.getInt("cc_refund"));
					profile.setCcforcommodity(rs.getInt("ccforcommodity"));
					profile.setCcforspecial(rs.getInt("ccforspecial"));
					profileList.add(profile);
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
		int size = profileList.size();
		if (size > 0) {
			return (ProfileDTO[]) profileList.toArray(new ProfileDTO[size]);
		}

		return null;
	}

	private CardDTO[] getCards(Connection con, String profileName)
			throws SQLException, Exception {

		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<CardDTO> cardList = new ArrayList<CardDTO>();
		CardDTO card = null;
		try {
			ps = con.prepareStatement(GET_CARD);
			if (ps != null) {
				ps.setString(1, profileName);
				rs = ps.executeQuery();
				while (rs.next()) {
					card = new CardDTO();
					card.setType(rs.getString(LR_TYPE));
					card.setAboveCardrate(rs.getFloat(ABOVE_CARD_RATE));
					card.setEqualCardrate(rs.getFloat(EQUALS_CARD_RATE));
					card.setUpto20Cardrate(rs.getFloat(UPTO_20));
					card.setAbove20Cardrate(rs.getFloat(ABOVE_20));
					cardList.add(card);
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

		int size = cardList.size();

		if (size > 0) {
			return (CardDTO[]) cardList.toArray(new CardDTO[size]);
		}

		return null;
	}

	/** ************Client Side************** */

	/**
	 * Method to get the booking commission details for the given month and
	 * year.
	 * 
	 * @param stationCode
	 *            Station Code
	 * 
	 * @param date
	 *            Month and year for which the booking commission details needs
	 *            to be fetched.
	 * 
	 * @return BookingCommissionDTO[] Array instance of BookingCommission DTO.
	 * 
	 * @throws SQLException
	 */
	public BookingCommissionDTO[] getLRWithCommission(String stationCode,
			String month, String year,String dbHistory) throws SQLException, Exception {
		Connection con = null;
		CallableStatement cs = null;
		DBHelper helper = new DBHelper();

		ArrayList<BookingCommissionDTO> bookingCommission = new ArrayList<BookingCommissionDTO>();

		try {
			con = helper.getConnection();

			if (null != con) {
				// con.setAutoCommit(false);
				// cs = con.prepareCall("{call calc_commission(?)}");
				// cs.setString(1, stationCode);
				/*
				 * if (cs != null) { cs.executeUpdate();
				 */
				bookingCommission = retrieveBookingCommission(con, stationCode,
						month, year);
				/*
				 * if (bookingCommission != null) { con.commit(); }
				 */
				// }
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

		int size = bookingCommission.size();
		if (size > 0) {
			return (BookingCommissionDTO[]) bookingCommission
					.toArray(new BookingCommissionDTO[size]);
		}
		return null;
	}

	public BookingCommissionDTO[] getLRWithCommissionHistory(String stationCode,
			String month, String year, String dbHis) throws Exception {
		Connection con = null;
		CallableStatement cs = null;		

		ArrayList<BookingCommissionDTO> bookingCommission = new ArrayList<BookingCommissionDTO>();

		try {
			if(dbHis == null || dbHis.equals("")){
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			}else{
				DBHelperHis helperHis = new DBHelperHis();
				con = helperHis.getConnectionHistory(dbHis);
			}

			if (null != con) {				
				bookingCommission = retrieveBookingCommissionHistory(con, stationCode,
						month, year);				
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

		int size = bookingCommission.size();
		if (size > 0) {
			return (BookingCommissionDTO[]) bookingCommission
					.toArray(new BookingCommissionDTO[size]);
		}
		return null;
	}
	
	/**
	 * 
	 * @param con
	 * @param stationCode
	 * @param month
	 * @param year
	 * @return
	 * @throws Exception
	 */
	private ArrayList<BookingCommissionDTO> retrieveBookingCommission(
			Connection con, String stationCode, String month, String year)
			throws Exception {
		BookingCommissionDTO dto = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<BookingCommissionDTO> list = new ArrayList<BookingCommissionDTO>();
		try {

			ps = con.prepareStatement(GET_BOOKING_COMMISSIONS_LIST);
			if (ps != null) {
				ps.setString(1, stationCode);
				ps.setString(2, month);
				ps.setString(3, year);
				rs = ps.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						dto = new BookingCommissionDTO();
						dto.setLrno(rs.getString(LR_NO));
						dto.setBookingDate(rs.getDate(LR_DATE));
						dto.setLrType(rs.getString(LR_TYPE));
						dto.setBft(rs.getFloat(BFT));
						dto.setBookingCommission(rs
								.getFloat(BOOKING_COMMISSIONS));
						dto.setBookingCommissionPercent(rs
								.getFloat(BOOKING_COMM_PERCENT));

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

	private ArrayList<BookingCommissionDTO> retrieveBookingCommissionHistory(
			Connection con, String stationCode, String month, String year)
			throws Exception {
		BookingCommissionDTO dto = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<BookingCommissionDTO> list = new ArrayList<BookingCommissionDTO>();
		try {

			ps = con.prepareStatement(GET_BOOKING_COMMISSIONS_LIST_HISTORY);
			if (ps != null) {
				ps.setString(1, stationCode);
				ps.setString(2, month);
				ps.setString(3, year);
				rs = ps.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						dto = new BookingCommissionDTO();
						dto.setLrno(rs.getString(LR_NO));
						dto.setBookingDate(rs.getDate(LR_DATE));
						dto.setLrType(rs.getString(LR_TYPE));
						dto.setBft(rs.getFloat(BFT));
						dto.setBookingCommission(rs
								.getFloat(BOOKING_COMMISSIONS));
						dto.setBookingCommissionPercent(rs
								.getFloat(BOOKING_COMM_PERCENT));

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
	 * @param con
	 * @param cardRateType
	 * @return
	 * @throws Exception
	 */
	private float getBookingPercent(Connection con, String cardRateType,
			String stationCode, String lrType) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		float percent = 0;
		try {
			String GET_PERCENTAGE = "Select a."
					+ cardRateType
					+ " as percent from booking_commission a, station b "
					+ " where a.profile_name = b.profile_name and b.station_code = '"
					+ stationCode + "' and a.lr_type = '" + lrType + "' ";

			ps = con.prepareStatement(GET_PERCENTAGE);
			if (ps != null) {
				rs = ps.executeQuery();
				if (rs != null) {
					if (rs.next()) {
						percent = rs.getFloat("percent");
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

		return percent;
	}

	private ArrayList<CartageCommissionDTO> retrieveCartageCommission(
			Connection con, String stationCode, String month, String year)
			throws Exception {
		CartageCommissionDTO dto = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<CartageCommissionDTO> list = new ArrayList<CartageCommissionDTO>();
		try {

			ps = con.prepareStatement(GET_CARTAGE_COMMISSION_LIST);
			if (ps != null) {
				ps.setString(1, stationCode);
				ps.setString(2, month);
				ps.setString(3, year);
				rs = ps.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						dto = new CartageCommissionDTO();
						dto.setLrno(rs.getString(LR_NO));
						dto.setBft(rs.getFloat(BFT));
						dto.setBookingDate(rs.getDate(LR_DATE));
						dto.setCcCharges(rs.getFloat(CCCHARGES));
						dto.setCommissionAmount(rs.getFloat(CCCOMMISSIONS));
						dto.setLrType(rs.getString(LR_TYPE));
						dto.setCcPercentage(rs.getFloat("ccPercent"));
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

	private ArrayList<CartageCommissionDTO> retrieveCartageCommissionHistory(
			Connection con, String stationCode, String month, String year)
			throws Exception {
		CartageCommissionDTO dto = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<CartageCommissionDTO> list = new ArrayList<CartageCommissionDTO>();
		try {

			ps = con.prepareStatement(GET_CARTAGE_COMMISSION_LIST_HISTORY);
			if (ps != null) {
				ps.setString(1, stationCode);
				ps.setString(2, month);
				ps.setString(3, year);
				rs = ps.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						dto = new CartageCommissionDTO();
						dto.setLrno(rs.getString(LR_NO));
						dto.setBft(rs.getFloat(BFT));
						dto.setBookingDate(rs.getDate(LR_DATE));
						dto.setCcCharges(rs.getFloat(CCCHARGES));
						dto.setCommissionAmount(rs.getFloat(CCCOMMISSIONS));
						dto.setLrType(rs.getString(LR_TYPE));
						dto.setCcPercentage(rs.getFloat("ccPercent"));
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
	 * Method to get the Cartage Collection commission details for the given
	 * station code and date code
	 * 
	 * @param stationCode
	 *            Station Code
	 * 
	 * @param date
	 *            Month and year for which the cc commission details needs to be
	 *            retrieved.
	 * 
	 * @return CartageCommissionDTO[] Array instance of Cartage Collection
	 *         Commission DTO
	 * 
	 * @throws SQLException
	 */
	public CartageCommissionDTO[] getCCCommissionList(String stationCode,
			String month, String year,String dbHistory) throws SQLException, Exception {
		Connection con = null;
		CallableStatement cs = null;
		DBHelper helper = new DBHelper();

		ArrayList<CartageCommissionDTO> cartageCommission = new ArrayList<CartageCommissionDTO>();

		try {
			con = helper.getConnection();

			if (null != con) {
				// con.setAutoCommit(false);
				// cs = con.prepareCall("{call calc_commission(?)}");
				// cs.setString(1, stationCode);
				/*
				 * if (cs != null) { cs.executeUpdate();
				 */
				cartageCommission = retrieveCartageCommission(con, stationCode,
						month, year);
				/*
				 * if (cartageCommission != null) { con.commit(); }
				 */
				// }
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

		int size = cartageCommission.size();
		if (size > 0) {
			return (CartageCommissionDTO[]) cartageCommission
					.toArray(new CartageCommissionDTO[size]);
		}
		return null;
	}
	
	public CartageCommissionDTO[] getCCCommissionListHistory(String stationCode,
			String month, String year, String dbHis) throws SQLException, Exception {
		Connection con = null;
		CallableStatement cs = null;		

		ArrayList<CartageCommissionDTO> cartageCommission = new ArrayList<CartageCommissionDTO>();

		try {
			if(dbHis == null || dbHis.equals("")){
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			}else{
				DBHelperHis helperHis = new DBHelperHis();
				con = helperHis.getConnectionHistory(dbHis);
			}

			if (null != con) {
				
				cartageCommission = retrieveCartageCommissionHistory(con, stationCode,
						month, year);				
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

		int size = cartageCommission.size();
		if (size > 0) {
			return (CartageCommissionDTO[]) cartageCommission
					.toArray(new CartageCommissionDTO[size]);
		}
		return null;
	}

	/**
	 * Method to call the procedure which calculates the booking commission and
	 * delivery commission for the given station code. The procedure gets all
	 * the lr's for which the commission is not calculated and calculates the
	 * commission based on the commission setting for the given station code
	 * 
	 * @param con
	 *            An instance of Connection
	 * @param stationCode
	 *            Station Code
	 * 
	 */
	/*private void calculateBcAndCcCommission(Connection con, String stationCode)
			throws SQLException, Exception {

		CallableStatement cs = null;

		try {
			con.setAutoCommit(false);
			cs = con.prepareCall("{call calc_commission(?)}");
			cs.setString(1, stationCode);
			if (cs != null) {
				cs.executeUpdate();
				con.commit();
			}
		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
			throw sqlexception;
		} catch (Exception exception) {
			exception.printStackTrace();
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

	}*/

	/**
	 * Method to get the delivery commission details for the given station code
	 * and the date
	 * 
	 * @param stationCode
	 *            Station Code
	 * 
	 * @param date
	 *            Month and year for which the booking commission details needs
	 *            to be fetched.
	 * 
	 * @return DeliveryCommissionDTO[] Array instance of Delivery Commission
	 *         DTO.
	 * 
	 * @throws SQLException
	 */
	public DeliveryCommissionDTO[] getDeliveryCommissionList(
			String stationCode, String month, String year,String dbHistory) throws SQLException,
			Exception {
		Connection con = null;
		PreparedStatement pst = null;
		DBHelper helper = new DBHelper();
		ResultSet rs = null;
		ArrayList<DeliveryCommissionDTO> dcCommissionList = new ArrayList<DeliveryCommissionDTO>();
		DeliveryCommissionDTO dcCommission = null;

		try {

			con = helper.getConnection();
			if (null != con) {
				pst = con.prepareStatement(GET_DELIVERY_COMMISSION_LIST);
				if (pst != null) {
					pst.setString(1, stationCode);
					pst.setString(2, month);
					pst.setString(3, year);
					rs = pst.executeQuery();
					while (rs.next()) {
						dcCommission = new DeliveryCommissionDTO();
						dcCommission.setLrno(rs.getString(LR_NO));
						dcCommission.setDeliveredDate(rs.getDate(DELV_DATE));
						dcCommission.setLrDate(rs.getDate(LR_DATE));
						dcCommission.setLrType(rs.getString(LR_TYPE));
						dcCommission
								.setActualWeight(rs.getFloat(ACTUAL_WEIGHT));
						dcCommission.setDc(rs.getFloat(DELIVERY_COMMISSION));
						dcCommission.setRate_type(rs.getInt(RATE_TYPE));
						dcCommission.setDdcFree(rs.getFloat(DDCFREE));
						dcCommissionList.add(dcCommission);
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
		int size = dcCommissionList.size();
		if (size > 0) {
			return (DeliveryCommissionDTO[]) dcCommissionList
					.toArray(new DeliveryCommissionDTO[size]);
		}
		return null;
	}
	
	public DeliveryCommissionDTO[] getDeliveryCommissionListHistory(
			String stationCode, String month, String year, String dbHis) throws SQLException,
			Exception {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<DeliveryCommissionDTO> dcCommissionList = new ArrayList<DeliveryCommissionDTO>();
		DeliveryCommissionDTO dcCommission = null;

		try {

			if(dbHis == null || dbHis.equals("")){
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			}else{
				DBHelperHis helperHis = new DBHelperHis();
				con = helperHis.getConnectionHistory(dbHis);
			}
			
			if (null != con) {
				pst = con.prepareStatement(GET_DELIVERY_COMMISSION_LIST_HISTORY);
				if (pst != null) {
					pst.setString(1, stationCode);
					pst.setString(2, month);
					pst.setString(3, year);
					rs = pst.executeQuery();
					while (rs.next()) {
						dcCommission = new DeliveryCommissionDTO();
						dcCommission.setLrno(rs.getString(LR_NO));
						dcCommission.setDeliveredDate(rs.getDate(DELV_DATE));
						dcCommission.setLrDate(rs.getDate(LR_DATE));
						dcCommission.setLrType(rs.getString(LR_TYPE));
						dcCommission
								.setActualWeight(rs.getFloat(ACTUAL_WEIGHT));
						dcCommission.setDc(rs.getFloat(DELIVERY_COMMISSION));
						dcCommission.setRate_type(rs.getInt(RATE_TYPE));
						dcCommission.setDdcFree(rs.getFloat(DDCFREE));
						dcCommissionList.add(dcCommission);
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
		int size = dcCommissionList.size();
		if (size > 0) {
			return (DeliveryCommissionDTO[]) dcCommissionList
					.toArray(new DeliveryCommissionDTO[size]);
		}
		return null;
	}
	

	/**
	 * Method to get the refund details for the given station code and date.
	 * 
	 * @param stationCode
	 *            Station Code. This parameter should not be null.
	 * 
	 * @param date
	 *            Month and year for which the cc commission details needs to be
	 *            retrieved.
	 * 
	 * @return RefundRecoveryDTO[] Array Instance of RefundRecoveryDTO
	 * 
	 * @throws SQLException
	 */
	public RefundRecoveryDTO[] getRefundDetailList(String stationCode,
			String month, String year, String dbHis) throws SQLException, Exception {

		//System.out.println("in CommissionDAO getRefundDetailList===>");
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<RefundRecoveryDTO> recoveryList = new ArrayList<RefundRecoveryDTO>();
		RefundRecoveryDTO recovery = null;
		int mon = 0;
		int yr = 0;
		String fromDate = "";
		String toDate = "";
		try {
			if(dbHis == null || dbHis.equals("")){
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			}else{
				DBHelperHis helperHis = new DBHelperHis();
				con = helperHis.getConnectionHistory(dbHis);
			}
			
			if (null != con) {
				pst = con.prepareStatement(GET_REFUND_COLLECTION_LIST);
				if (pst != null) {
					pst.setString(1, stationCode);
					fromDate = year + '-' + month + '-' + 9;
					pst.setString(2, fromDate);
					mon = Integer.parseInt(month);
					if (mon == 12) {
						yr = Integer.parseInt(year);
						year = String.valueOf(yr + 1);
						mon = 0;
					}
					toDate = year + '-' + String.valueOf(mon + 1) + '-' + 8;
					pst.setString(3, toDate);

					//System.out.println("from-->"+fromDate+"to-->"+toDate);
					
					rs = pst.executeQuery();
					while (rs.next()) {
						recovery = new RefundRecoveryDTO();
						recovery.setRr_id(rs.getInt(RR_ID));
						//System.out.println("RRID-->"+rs.getInt(RR_ID));
						recovery.setStationCode(rs.getString(STATION_CODE));
						recovery.setDescription(rs.getString(DESCRIPTION));
						recovery.setAmount(rs.getFloat(AMOUNT));
						recovery.setContacts(rs.getString(CONTACTS));
						recovery.setRecovery_flag(rs.getInt(RECOVERY_FLAG));
						recovery.setRr_date(rs.getDate(RR_CREATED_DATE));
						recovery.setInstallment(rs.getString(INSTALLMENT));
						recoveryList.add(recovery);
					}
				}
			}
		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
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
		int size = recoveryList.size();
		System.out.println("Out CommissionDAO getRefundDetailList===>" + size);
		if (size > 0) {
			return (RefundRecoveryDTO[]) recoveryList
					.toArray(new RefundRecoveryDTO[size]);
		}
		return null;
	}
	
	
	
	

	/**
	 * Method to get the recovery details for the given station code and date.
	 * 
	 * @param stationCode
	 *            Station Code. This parameter should not be null.
	 * 
	 * @param date
	 *            Month and year for which the cc commission details needs to be
	 *            retrieved.
	 * 
	 * @return RefundRecoveryDTO[] Array Instance of RefundRecoveryDTO
	 * 
	 * @throws SQLException
	 */
	public RefundRecoveryDTO[] getRecoveryDetailList(String stationCode,
			String month, String year, String dbHis) throws SQLException, Exception {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		System.out.println("Out CommissionDAO getRecoveryDetailList===>" );
		ArrayList<RefundRecoveryDTO> recoveryList = new ArrayList<RefundRecoveryDTO>();
		RefundRecoveryDTO recovery = null;
		int mon = 0;
		int yr = 0;
		String fromDate = "";
		String toDate = "";

		try {
			if(dbHis == null || dbHis.equals("")){
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			}else{
				DBHelperHis helperHis = new DBHelperHis();
				con = helperHis.getConnectionHistory(dbHis);
			}
			
			if (null != con) {
				pst = con.prepareStatement(GET_RECOVERY_COLLECTION_LIST);
				if (pst != null) {
					pst.setString(1, stationCode);
					fromDate = year + '-' + month + '-' + 9;
					pst.setString(2, fromDate);
					mon = Integer.parseInt(month);
					if (mon == 12) {
						yr = Integer.parseInt(year);
						year = String.valueOf(yr + 1);
						mon = 0;
					}
					toDate = year + '-' + String.valueOf(mon + 1) + '-' + 8;

					//System.out.println("from-->"+fromDate+"to-->"+toDate);
					
					pst.setString(3, toDate);

					rs = pst.executeQuery();
					while (rs.next()) {
						recovery = new RefundRecoveryDTO();
						recovery.setRr_id(rs.getInt(RR_ID));
						recovery.setStationCode(rs.getString(STATION_CODE));
						recovery.setDescription(rs.getString(DESCRIPTION));
						recovery.setAmount(rs.getFloat(AMOUNT));
						recovery.setContacts(rs.getString(CONTACTS));
						recovery.setRecovery_flag(rs.getInt(RECOVERY_FLAG));
						recovery.setRr_date(rs.getDate(RR_CREATED_DATE));
						recovery.setInstallment(rs.getString(INSTALLMENT));
						recoveryList.add(recovery);
					}
				}
			}
		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
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
		int size = recoveryList.size();
		System.out.println("Out CommissionDAO getRecoveryDetailList===>" + size);
		if (size > 0) {
			return (RefundRecoveryDTO[]) recoveryList
					.toArray(new RefundRecoveryDTO[size]);
		}
		return null;
	}

	/**
	 * Method to get the overall commission summary for the given station code
	 * and for the given month.
	 * 
	 * @param stationCode
	 *            Station Code. This parameter should not be null.
	 * 
	 * @param month
	 *            Month for which commission summary details needs to be
	 *            retrieved.
	 * @param year
	 *            Year for which the commission summary details needs to be
	 *            retrieved.
	 * @return CommissionSummaryDTO
	 * 
	 * @throws SQLException
	 */
	public CommissionSummaryDTO getCommissionSummary(String stationCode,
			String month, String year,String dbHistory) throws SQLException, Exception {

		Connection con = null;
		CallableStatement cs = null;
		DBHelper helper = new DBHelper();
		ResultSet rs = null;

		CommissionSummaryDTO summary = null;

		try {
			con = helper.getConnection();
			if (null != con) {
				cs = con.prepareCall("{call station_comm_summary(?,?,?)}");
				if (cs != null) {
					cs.setString(1, stationCode);
					cs.setString(2, month);
					cs.setString(3, year);
					cs.executeUpdate();
					rs = cs.getResultSet();
					if (null != rs) {
						if (rs.next()) {
							summary = new CommissionSummaryDTO();
							summary.setBcTotal(rs.getFloat(BKGCOMMISSION));
							summary.setCccTotal(rs.getFloat(CCCOMMISSION));
							summary.setDcTotal(rs.getFloat(DLYCOMMISSION));
							summary.setRecoveryAmount(rs.getFloat(RECOVERY));
							summary.setRefundAmount(rs.getFloat(REFUND));
							summary.setDdc(rs.getFloat(DDC));
							summary.setNetAmount(rs.getFloat(TOTAL));
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

		return summary;
	}
	
	public CommissionSummaryDTO getCommissionSummaryHistory(String stationCode,
			String month, String year, String dbHis) throws SQLException, Exception {

		Connection con = null;
		CallableStatement cs = null;
		
		ResultSet rs = null;

		CommissionSummaryDTO summary = null;

		try {
			if(dbHis == null || dbHis.equals("")){
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			}else{
				DBHelperHis helperHis = new DBHelperHis();
				con = helperHis.getConnectionHistory(dbHis);
			}
			
			if (null != con) {
				cs = con.prepareCall("{call station_comm_summary_history(?,?,?)}");
				if (cs != null) {
					cs.setString(1, stationCode);
					cs.setString(2, month);
					cs.setString(3, year);
					cs.executeUpdate();
					rs = cs.getResultSet();
					if (null != rs) {
						if (rs.next()) {
							summary = new CommissionSummaryDTO();
							summary.setBcTotal(rs.getFloat(BKGCOMMISSION));
							summary.setCccTotal(rs.getFloat(CCCOMMISSION));
							summary.setDcTotal(rs.getFloat(DLYCOMMISSION));
							summary.setRecoveryAmount(rs.getFloat(RECOVERY));
							summary.setRefundAmount(rs.getFloat(REFUND));
							summary.setDdc(rs.getFloat(DDC));
							summary.setNetAmount(rs.getFloat(TOTAL));
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

		return summary;
	}
	

	/** *************** Admin Side ************* */

	/**
	 * Method is to create the profile.This method will first delete the records
	 * for the given profile_id and then it will insert the profile information
	 * in the booking_commission table
	 * 
	 * @param profile
	 *            ProfileDTO
	 * @return profileName String
	 * 
	 * @throws SQLException
	 */
	public String createProfile(ProfileDTO profile) throws SQLException,
			Exception {

		Connection con = null;
		String profile_id = null;

		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != getProfile(con, profile.getProfileName())) {
				// update the record if present
				updateProfile(con, profile);
			} else {
				// insert the record if not present
				profile_id = "P" + getDateTime();
				insertProfile(con, profile, profile_id);
			}
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != con) {
					con.close();
					con = null;
				}
			} catch (Exception excep) {
				con = null;
			}
		}
		return profile_id;
	}
	
	public String createCCProfile(ProfileDTO profile) throws SQLException,
	Exception {

		Connection con = null;
		String profile_id = null;

		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != getCCProfile(con, profile.getProfileName())) {
				// update the record if present
				updateCCProfile(con, profile);
			} else {
				// insert the record if not present
				profile_id = "P" + getDateTime();
				insertCCProfile(con, profile);
			}
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != con) {
					con.close();
					con = null;
				}
			} catch (Exception excep) {
				con = null;
			}
		}
		return profile_id;
	}

	/**
	 * Method is to get the profile list
	 * 
	 * @return ProfileDTO[] profileDTO array
	 * @throws SQLException
	 * @throws Exception
	 */
	public ProfileDTO[] getProfileList() throws SQLException, Exception {
		Connection con = null;
		ProfileDTO[] profile = null;
		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				profile = getProfileList(con);
			}
		} catch (SQLException sqlexception) {
			throw sqlexception;
		} catch (Exception exception) {
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
		return profile;
	}
	
	public ProfileDTO[] getCCProfileList() throws SQLException, Exception {
		Connection con = null;
		ProfileDTO[] profile = null;
		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				profile = getCCProfileList(con);
			}
		} catch (SQLException sqlexception) {
			throw sqlexception;
		} catch (Exception exception) {
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
		return profile;
	}

	/**
	 * Method to get distinct Profiles
	 * 
	 * @return Float[] Distinct Profiles
	 * 
	 * @throws SQLException
	 * @throws Exception
	 */
	public String[] getDistinctProfile() throws SQLException, Exception {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<String> distinctProfile = new ArrayList<String>();
		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				ps = con.prepareStatement(GET_DISTINCT_PROFILE);
				if (ps != null) {
					rs = ps.executeQuery();
					while (rs.next()) {
						distinctProfile.add(rs.getString(PROFILE_NAME));
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
		int size = distinctProfile.size();
		if (size > 0) {
			return (String[]) distinctProfile.toArray(new String[size]);
		}
		return null;
	}

	public String[] getDistinctCCProfile() throws SQLException, Exception {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<String> distinctProfile = new ArrayList<String>();
		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				ps = con.prepareStatement(GET_DISTINCT_CC_PROFILE);
				if (ps != null) {
					rs = ps.executeQuery();
					while (rs.next()) {
						distinctProfile.add(rs.getString(PROFILE_NAME));
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
		int size = distinctProfile.size();
		if (size > 0) {
			return (String[]) distinctProfile.toArray(new String[size]);
		}
		return null;
	}

	
	/**
	 * Method to set the Booking Commission for the given list of stations. This
	 * method updates the booking commission in station table
	 * 
	 * @param stationCode
	 *            StationCode List
	 * 
	 * @param profilename
	 *            Profile Name
	 * 
	 * @throws SQLException
	 * @throws Exception
	 */
	public void setBookingCommission(String[] stationCode, String profilename)
			throws SQLException, Exception {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				pst = con.prepareStatement(UPDATE_BOOKING_COMMISSION);
				if (null != pst) {
					int batch_count = stationCode.length;
					for (int i = 0; i < batch_count; i++) {
						pst.setString(1, profilename);
						pst.setString(2, stationCode[i]);
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
	
	public void setCCProfileCommission(String[] stationCode, String profilename)
	throws SQLException, Exception {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				pst = con.prepareStatement(UPDATE_CC_PROFILE_COMMISSION);
				if (null != pst) {
					int batch_count = stationCode.length;
					for (int i = 0; i < batch_count; i++) {
						pst.setString(1, profilename);
						pst.setString(2, stationCode[i]);
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
	 * This method returns the stationCode,StationName and the
	 * deliveryCommission value list.
	 * 
	 * @return StationDTO[] Array of StationDTO instance
	 * 
	 * @throws SQLException
	 * @throws Exception
	 */
	public StationDTO[] getBCList() throws SQLException, Exception {

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<StationDTO> bookingCommissionList = new ArrayList<StationDTO>();
		StationDTO bookingCommission = null;

		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				pst = con.prepareStatement(GET_BOOKING_COMMISSION_LIST);
				if (null != pst) {
					rs = pst.executeQuery();
					while (rs.next()) {
						bookingCommission = new StationDTO();
						bookingCommission.setId(rs.getString(STATION_CODE));
						bookingCommission.setName(rs.getString(STATION_NAME));
						bookingCommission.setProfileName(rs
								.getString(PROFILE_NAME));
						bookingCommissionList.add(bookingCommission);
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
		int size = bookingCommissionList.size();

		if (size > 0) {
			return (StationDTO[]) bookingCommissionList
					.toArray(new StationDTO[size]);
		}
		return null;
	}

	/**
	 * Method is to get the distinct BookingCommission
	 * 
	 * @return String[] BookingCommission
	 * @throws SQLException
	 * @throws Exception
	 */
	public String[] getDistinctBookingCommission() throws SQLException,
			Exception {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<String> bookingCommission = new ArrayList<String>();

		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				pst = con.prepareStatement(GET_DISTINCT_BOOKING_COMMISSION);
				if (pst != null) {
					rs = pst.executeQuery();
					while (rs.next()) {
						bookingCommission.add(rs.getString(PROFILE_NAME));
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
		int size = bookingCommission.size();
		if (size > 0) {
			return (String[]) bookingCommission.toArray(new String[size]);
		}

		return null;
	}

	/***************************************************************************
	 * Method to get the booking Commission value for the given stationCode.
	 * 
	 * @param stationCode
	 *            StationCode
	 * 
	 * @return String BookingCommission
	 * 
	 * @throws SQLException
	 * @throws Exception
	 */
	public String getBookingCommission(String stationCode) throws SQLException,
			Exception {

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String booking_commission = null;

		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				pst = con.prepareStatement(GET_BOOKING_COMMISSION);

				if (pst != null) {
					pst.setString(1, stationCode);
					rs = pst.executeQuery();
					if (rs.next()) {
						booking_commission = rs.getString(PROFILE_NAME);
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
		return booking_commission;
	}

	/**
	 * Method is to insert the recovery details in to the rr_register table
	 * 
	 * @param recovery
	 *            array RefundRecoveryDTO Array
	 * @throws SQLException
	 * @throws Exception
	 */
	public void createRecoveryRegister(String[] stationCode,
			String description, String contacts,
			HashMap<Integer, Float> installamount) throws SQLException,
			Exception {
		Connection con = null;
		PreparedStatement pst = null;

		int installment = 0;
		float amount = 0;

		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				pst = con.prepareStatement(CREATE_RECOVERY);
				if (null != pst) {
					int batch_count = stationCode.length;
					for (int i = 0; i < batch_count; i++) {

						Set<Map.Entry<Integer, Float>> set = installamount
								.entrySet();
						for (Map.Entry<Integer, Float> me : set) {
							pst.setString(1, stationCode[i]);
							pst.setString(2, description);
							pst.setString(3, contacts);
							installment = me.getKey();
							amount = me.getValue();
							pst.setInt(4, installment - 1);
							pst.setFloat(5, amount);
							pst.setString(6, String.valueOf(installment) + "-"
									+ String.valueOf(set.size()));
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
				if (null != pst) {
					pst.close();
					pst = null;
				}
				if (null != con) {
					con.close();
					con = null;
				}
			} catch (Exception excep) {
				con = null;
				pst = null;
			}
		}
	}

	/**
	 * Method is to delete the records from rr_register for the given
	 * recoveryId.
	 * 
	 * @param recoveryId
	 *            Recovery ID
	 * @throws SQLException
	 * @throws Exception
	 */
	public void deleteRecovery(int recoveryId) throws SQLException, Exception {
		Connection con = null;
		PreparedStatement pst = null;

		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				pst = con.prepareStatement(DELETE_RECOVERY);
				if (null != pst) {
					pst.setInt(1, recoveryId);
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
	 * Method is to insert the refund details in to the rr_register table
	 * 
	 * @param recovery
	 *            array RefundRecoveryDTO Array
	 * @throws SQLException
	 * @throws Exception
	 */
	public void createRefundRegister(String[] stationCode, String description,
			String contacts, HashMap<Integer, Float> installamount)
			throws SQLException, Exception {
		Connection con = null;
		PreparedStatement pst = null;

		int installment = 0;
		float amount = 0;

		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				pst = con.prepareStatement(CREATE_REFUND);
				if (null != pst) {
					int batch_count = stationCode.length;
					for (int i = 0; i < batch_count; i++) {

						Set<Map.Entry<Integer, Float>> set = installamount
								.entrySet();
						for (Map.Entry<Integer, Float> me : set) {
							pst.setString(1, stationCode[i]);
							pst.setString(2, description);
							pst.setString(3, contacts);
							installment = me.getKey();
							amount = me.getValue();
							pst.setInt(4, installment - 1);
							pst.setFloat(5, amount);
							pst.setString(6, String.valueOf(installment) + "-"
									+ String.valueOf(set.size()));
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
				if (null != pst) {
					pst.close();
					pst = null;
				}
				if (null != con) {
					con.close();
					con = null;
				}
			} catch (Exception excep) {
				con = null;
				pst = null;
			}
		}
	}

	/**
	 * Method is to delete the records from rr_register for the given refundId.
	 * 
	 * @param recoveryId
	 *            Recovery ID
	 * @throws SQLException
	 * @throws Exception
	 */
	public void deleteRefund(int refundId) throws SQLException, Exception {
		Connection con = null;
		PreparedStatement pst = null;

		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				pst = con.prepareStatement(DELETE_REFUND);
				if (null != pst) {
					pst.setInt(1, refundId);
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

	// public void updateRecovery(int recoveryId)throws SQLException,Exception{
	// Connection con = null;
	// PreparedStatement pst = null;
	//	
	// try {
	// DBHelper db = new DBHelper();
	// con = db.getConnection();
	// if (null != con) {
	// pst = con.prepareStatement(UPDATE_RECOVERY);
	// if (null != pst ) {
	// pst.setInt(1,recoveryId);
	// pst.executeUpdate();
	// }
	// }
	// } catch (Exception exception) {
	// throw exception;
	// } finally {
	// try {
	// if (null != pst) {
	// pst.close();
	// pst = null;
	// }
	// if (null != con) {
	// con.close();
	// con = null;
	// }
	// } catch (Exception excep) {
	// pst = null;
	// con = null;
	// }
	// }
	//	
	// }
	//	
	// public void updateRefund(int refundId)throws SQLException,Exception{
	// Connection con = null;
	// PreparedStatement pst = null;
	//	
	// try {
	// DBHelper db = new DBHelper();
	// con = db.getConnection();
	// if (null != con) {
	// pst = con.prepareStatement(UPDATE_REFUND);
	// if (null != pst ) {
	// pst.setInt(1,refundId);
	// pst.executeUpdate();
	// }
	// }
	// } catch (Exception exception) {
	// throw exception;
	// } finally {
	// try {
	// if (null != pst) {
	// pst.close();
	// pst = null;
	// }
	// if (null != con) {
	// con.close();
	// pst = null;
	// }
	// } catch (Exception excep) {
	// con = null;
	// pst = null;
	// }
	// }
	// }
	//	

	/** **************Delivery Commission*********** */

	/**
	 * This method returns the stationCode,StationName and the
	 * deliveryCommission value list.
	 * 
	 * @return StationDTO[] Array of StationDTO instance
	 * 
	 * @throws SQLException
	 * @throws Exception
	 */
	public StationDTO[] getDCList() throws SQLException, Exception {

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<StationDTO> dcCommissionList = new ArrayList<StationDTO>();
		StationDTO dcCommission = null;

		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				pst = con.prepareStatement(GET_DC_COMMISSION_LIST);
				if (null != pst) {
					rs = pst.executeQuery();
					while (rs.next()) {
						dcCommission = new StationDTO();
						dcCommission.setId(rs.getString(STATION_CODE));
						dcCommission.setName(rs.getString(STATION_NAME));
						dcCommission.setDeliveryCommission(rs
								.getFloat(DC_COMMISSION));
						dcCommissionList.add(dcCommission);
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
		int size = dcCommissionList.size();

		if (size > 0) {
			return (StationDTO[]) dcCommissionList
					.toArray(new StationDTO[size]);
		}
		return null;
	}

	/**
	 * Method to set the Delivery Commission for the given list of stations.
	 * This method updates the deliveryCommission in station table
	 * 
	 * @param stationCode
	 *            StationCode List
	 * 
	 * @param deliveryCommission
	 *            DeliveryCommission
	 * 
	 * @throws SQLException
	 * @throws Exception
	 */
	public void setDCCommissionList(String[] stationCode,
			float deliveryCommission) throws SQLException, Exception {
		Connection con = null;
		PreparedStatement pst = null;

		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				pst = con.prepareStatement(UPDATE_DC_COMMISSION);
				if (null != pst) {
					int batch_count = stationCode.length;
					for (int i = 0; i < batch_count; i++) {
						pst.setFloat(1, deliveryCommission);
						pst.setString(2, stationCode[i]);
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

	/***************************************************************************
	 * Method to get the deliveryCommission value for the given stationCode.
	 * 
	 * @param stationCode
	 *            StationCode
	 * 
	 * @return float DeliveryCommission
	 * 
	 * @throws SQLException
	 * @throws Exception
	 */
	public float getDCCommission(String stationCode) throws SQLException,
			Exception {

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		float dc_commission = 0;

		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				pst = con.prepareStatement(GET_DC_COMMISSION);

				if (pst != null) {
					pst.setString(1, stationCode);
					rs = pst.executeQuery();
					if (rs.next()) {
						dc_commission = rs.getFloat(DC_COMMISSION);
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
		return dc_commission;
	}

	/**
	 * Method is to get the distinct DeliveryCommission
	 * 
	 * @return Float[] deliveryCommission
	 * @throws SQLException
	 * @throws Exception
	 */
	public Float[] getDistinctDCCommission() throws SQLException, Exception {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<Float> dcCommission = new ArrayList<Float>();

		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				pst = con.prepareStatement(GET_DISTINCT_DC_COMMISSION);
				if (pst != null) {
					rs = pst.executeQuery();
					while (rs.next()) {
						dcCommission.add(rs.getFloat(DC_COMMISSION));
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
		int size = dcCommission.size();
		if (size > 0) {
			return (Float[]) dcCommission.toArray(new Float[size]);
		}

		return null;
	}

	/** ************CCCharge Commission*************** */
	/**
	 * This method returns the stationCode,StationName and the CartageCommission
	 * value list.
	 * 
	 * @return StationDTO[] Array of StationDTO instance
	 * 
	 * @throws SQLException
	 * @throws Exception
	 */
	public StationDTO[] getCCCList() throws SQLException, Exception {

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<StationDTO> ccCommissionList = new ArrayList<StationDTO>();
		StationDTO ccCommission = null;

		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				pst = con.prepareStatement(GET_CC_COMMISSION_LIST);
				if (null != pst) {
					rs = pst.executeQuery();
					while (rs.next()) {
						ccCommission = new StationDTO();
						ccCommission.setId(rs.getString(STATION_CODE));
						ccCommission.setName(rs.getString(STATION_NAME));
						ccCommission
								.setCcCommission(rs.getFloat(CC_COMMISSION));
						ccCommissionList.add(ccCommission);
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
		int size = ccCommissionList.size();
		if (size > 0) {
			return (StationDTO[]) ccCommissionList
					.toArray(new StationDTO[size]);
		}
		return null;
	}

	/**
	 * Method to set the Cartage Commission for the given list of stations. This
	 * method updates the cartageCommission in station table
	 * 
	 * @param stationCode
	 *            StationCode List
	 * 
	 * @param cartageCommission
	 *            CartageCommission
	 * 
	 * @throws SQLException
	 * @throws Exception
	 */
	public void setCCCommissionList(String[] station_code, float ccCommission)
			throws SQLException, Exception {
		Connection con = null;
		PreparedStatement pst = null;

		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				pst = con.prepareStatement(UPDATE_CC_COMMISSION);
				if (null != pst) {
					int batch_count = station_code.length;

					for (int i = 0; i < batch_count; i++) {
						pst.setFloat(1, ccCommission);
						pst.setString(2, station_code[i]);
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

	/***************************************************************************
	 * Method to get the CartageCommission value for the given stationCode.
	 * 
	 * @param stationCode
	 *            StationCode
	 * 
	 * @return float CartageCommission
	 * 
	 * @throws SQLException
	 * @throws Exception
	 */
	public float getCCCommission(String stationCode) throws SQLException,
			Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		float cc_commission = 0;

		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				ps = con.prepareStatement(GET_CC_COMMISSION);
				if (ps != null) {
					ps.setString(1, stationCode);

					rs = ps.executeQuery();
					if (rs.next()) {
						cc_commission = rs.getFloat(CC_COMMISSION);
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
		return cc_commission;
	}

	/**
	 * Method is to get the distinct CartageCommission
	 * 
	 * @return Float[] cartageCommission
	 * @throws SQLException
	 * @throws Exception
	 */
	public Float[] getDistinctCCCommission() throws SQLException, Exception {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Float> ccCommission = new ArrayList<Float>();

		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				ps = con.prepareStatement(GET_DISTINCT_CC_COMMISSION);

				if (ps != null) {
					rs = ps.executeQuery();
					while (rs.next()) {
						ccCommission.add(rs.getFloat(CC_COMMISSION));
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
		int size = ccCommission.size();
		if (size > 0) {
			return (Float[]) ccCommission.toArray(new Float[size]);
		}
		return null;

	}

	/**
	 * 
	 */
	@Override
	public CommissionSummaryDTO[] getAdminCommission(String branch_code,
			int day, int month) throws BusinessException, Exception {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<CommissionSummaryDTO> ccCommissionList = new ArrayList<CommissionSummaryDTO>();
		CommissionSummaryDTO dto = null;

		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				pst = con.prepareStatement(GET_ADMIN_VIEW);
				pst.setString(1, branch_code);
				pst.setInt(2, day);
				pst.setInt(3, month);
				rs = pst.executeQuery();
				while (rs.next()) {
					dto = new CommissionSummaryDTO();
					dto.setStation_code(rs.getString(STATION_CODE));
					dto.setStation_name(rs.getString(STATION_NAME));
					dto.setBcTotal(rs.getFloat(BOOKING));
					dto.setDcTotal(rs.getFloat(DELIVERY));
					dto.setRefundAmount(rs.getFloat(REFUND));
					dto.setRecoveryAmount(rs.getFloat(RECOVERY));
					dto.setCccTotal(rs.getFloat(CC_COMMISSION));
					ccCommissionList.add(dto);
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
		int size = ccCommissionList.size();
		if (size > 0) {
			return (CommissionSummaryDTO[]) ccCommissionList
					.toArray(new CommissionSummaryDTO[size]);
		}
		return null;
	}

	public String getPassword(String stnCode) throws SQLException, Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String password = null;

		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				ps = con.prepareStatement(GET_PASSWORD);
				if (ps != null) {
					ps.setString(1, stnCode);

					rs = ps.executeQuery();
					if (rs.next()) {
						password = rs.getString(PASSWORD);
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
		return password;
	}

	public void setPassword(String stnCode, String password)
			throws SQLException, Exception {
		Connection con = null;
		PreparedStatement pst = null;

		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				pst = con.prepareStatement(UPDATE_PASSWORD);
				if (null != pst) {
					pst.setString(1, password);
					pst.setString(2, stnCode);
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

	public DDDetailsDTO[] getDDDetailsList(String stationCode, String month,
			String year,String dbHistory) throws SQLException, Exception {
		Connection con = null;
		PreparedStatement ps = null;
		DBHelper helper = new DBHelper();

		DDDetailsDTO ddr = null;
		ResultSet rs = null;

		ArrayList<DDDetailsDTO> ddList = new ArrayList<DDDetailsDTO>();

		try {
			
			con = helper.getConnection();

			if (null != con) {

				ps = con.prepareStatement(GET_DDDETAILS);

				if (ps != null) {

					ps.setString(1, stationCode);
					ps.setString(2, month);
					ps.setString(3, year);
					ps.setString(4, stationCode);
					ps.setString(5, month);
					ps.setString(6, year);
					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {
							ddr = new DDDetailsDTO();
							ddr.setLrNo(rs.getString(LR_NO));
							ddr.setLrDate(rs.getDate(LR_DATE));
							ddr.setMode(rs.getString(MODE));
							ddr.setDdc(rs.getFloat(DDC));
							ddr.setDdcFree(rs.getFloat(DDCFREE));
							ddr.setTotal(rs.getFloat(TOTAL));
							ddr.setDdExtra(rs.getFloat(DDEXTRA));
							ddr.setOthers(rs.getFloat(OTHER));
							ddr.setDemurrage(rs.getFloat(DEMURRAGE));
							ddr.setUnderCharge(rs.getFloat(UNDERCHARGE));
							ddr.setPostageCharge(rs.getFloat(POSTAGE));
							ddList.add(ddr);
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

		int size = ddList.size();

		if (size > 0) {
			return (DDDetailsDTO[]) ddList.toArray(new DDDetailsDTO[size]);
		}
		return null;
	}
	
	public DDDetailsDTO[] getDDDetailsListHistory(String stationCode, String month,
			String year,String dbHis) throws SQLException, Exception {
		Connection con = null;
		PreparedStatement ps = null;
		//DBHelper helper = new DBHelper();

		DDDetailsDTO ddr = null;
		ResultSet rs = null;

		ArrayList<DDDetailsDTO> ddList = new ArrayList<DDDetailsDTO>();

		try {
			if(dbHis == null || dbHis.equals("")){
				DBHelper helper = new DBHelper();
				con = helper.getConnection();
			}else{
				DBHelperHis helperHis = new DBHelperHis();
				con = helperHis.getConnectionHistory(dbHis);
			}

			if (null != con) {

				ps = con.prepareStatement(GET_DDDETAILS_HISTORY);

				if (ps != null) {

					ps.setString(1, stationCode);
					ps.setString(2, month);
					ps.setString(3, year);
					ps.setString(4, stationCode);
					ps.setString(5, month);
					ps.setString(6, year);
					rs = ps.executeQuery();
					if (null != rs) {
						while (rs.next()) {
							ddr = new DDDetailsDTO();
							ddr.setLrNo(rs.getString(LR_NO));
							ddr.setLrDate(rs.getDate(LR_DATE));
							ddr.setMode(rs.getString(MODE));
							ddr.setDdc(rs.getFloat(DDC));
							ddr.setDdcFree(rs.getFloat(DDCFREE));
							ddr.setTotal(rs.getFloat(TOTAL));
							ddr.setDdExtra(rs.getFloat(DDEXTRA));
							ddr.setOthers(rs.getFloat(OTHER));
							ddr.setDemurrage(rs.getFloat(DEMURRAGE));
							ddr.setUnderCharge(rs.getFloat(UNDERCHARGE));
							ddr.setPostageCharge(rs.getFloat(POSTAGE));
							ddList.add(ddr);
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

		int size = ddList.size();

		if (size > 0) {
			return (DDDetailsDTO[]) ddList.toArray(new DDDetailsDTO[size]);
		}
		return null;
	}

	/**
	 * 
	 * @param profileId
	 * @throws SQLException
	 * @throws Exception
	 */
	public boolean isProfileExist(String profileName) throws SQLException,
			Exception {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		boolean isExist = false;
		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				pst = con.prepareStatement(IS_PROFILE_EXIST);
				if (null != pst) {
					pst.setString(1, profileName);
					rs = pst.executeQuery();
				}
				if (rs != null) {
					if (rs.next()) {
						isExist = true;
					}
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

		return isExist;
	}
	
	public boolean isCCProfileExist(String profileName) throws SQLException,
	Exception {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		boolean isExist = false;
		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				pst = con.prepareStatement(IS_CC_PROFILE_EXIST);
				if (null != pst) {
					pst.setString(1, profileName);
					rs = pst.executeQuery();
				}
				if (rs != null) {
					if (rs.next()) {
						isExist = true;
					}
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

		return isExist;
	}

	/**
	 * 
	 * @param profileId
	 * @throws SQLException
	 * @throws Exception
	 */
	public void deleteProfile(String profileId) throws SQLException, Exception {
		Connection con = null;
		PreparedStatement pst = null;

		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				pst = con.prepareStatement(DELETE_PROFILE);
				if (null != pst) {
					pst.setString(1, profileId);
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
	
	public void deleteCCProfile(String profileName) throws SQLException, Exception {
		Connection con = null;
		PreparedStatement pst = null;

		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				pst = con.prepareStatement(DELETE_CC_PROFILE);
				if (null != pst) {
					pst.setString(1, profileName);
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


	public void setBookingCommissionEffDate(String[] stationCode,
			String profilename, Date date) throws SQLException, Exception {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				pst = con.prepareStatement(BOOKING_COMMISSION_EFFECTIVE_DATE);
				if (null != pst) {
					int batch_count = stationCode.length;
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					for (int i = 0; i < batch_count; i++) {
						pst.setString(1, stationCode[i]);
						pst.setString(2, profilename);
						pst.setString(3, dateFormat.format(date));

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
	public void setCCConsiderRefund(String[] station, int consider, int refund,
			int ccLimit) throws SQLException, Exception {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				pst = con.prepareStatement(UPDATE_CC_CONSIDER_REFUND);
				if (null != pst) {
					int batch_count = station.length;

					for (int i = 0; i < batch_count; i++) {
						pst.setInt(1, consider);
						pst.setInt(2, refund);
						pst.setInt(3, ccLimit);
						pst.setString(4, station[i]);

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
	
	// DC profile
	
	public String createDCProfile(ProfileDTO profile) throws SQLException,
	Exception {

		Connection con = null;
		String profile_id = null;

		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != getDCProfile(con, profile.getProfileName())) {
				// update the record if present
				updateDCProfile(con, profile);
			} else {
				// insert the record if not present
				profile_id = "P" + getDateTime();
				insertDCProfile(con, profile);
			}
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != con) {
					con.close();
					con = null;
				}
			} catch (Exception excep) {
				con = null;
			}
		}
		return profile_id;
	}
	
	public ProfileDTO[] getDCProfileList() throws SQLException, Exception {
		Connection con = null;
		ProfileDTO[] profile = null;
		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				profile = getDCProfileList(con);
			}
		} catch (SQLException sqlexception) {
			throw sqlexception;
		} catch (Exception exception) {
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
		return profile;
	}
		
	
	private void insertDCProfile(Connection con, ProfileDTO profile) throws SQLException, Exception {
		PreparedStatement pst = null;

		try {
			if (null != con) {
				pst = con.prepareStatement(CREATE_DC_PROFILE);
				if (null != pst) {
					pst.setString(1, profile.getProfileName());
					pst.setInt(2, profile.getDelivery_commission());				
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
			} catch (Exception excep) {
				pst = null;
			}
		}
	}
	
	private void updateDCProfile(Connection con, ProfileDTO profile)
	throws SQLException, Exception {
		PreparedStatement pst = null;

		try {
			pst = con.prepareStatement(UPDATE_DC_PROFILE);
			if (null != pst) {

				pst.setInt(1, profile.getDelivery_commission());				
				pst.setString(2, profile.getProfileName());
				pst.executeUpdate();
			}
		} catch (Exception exception) {
			throw exception;
		} finally {
			try {
				if (null != pst) {
					pst.close();
					pst = null;
				}
			} catch (Exception excep) {
				pst = null;
			}
		}
	}
	
	private ProfileDTO[] getDCProfileList(Connection con) throws SQLException,
	Exception {

		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ProfileDTO> profileList = new ArrayList<ProfileDTO>();
		ProfileDTO profile = null;
		
		try {
			ps = con.prepareStatement(GET_DC_PROFILE_LIST);
			if (ps != null) {
				rs = ps.executeQuery();
				while (rs.next()) {
					profile = new ProfileDTO();
					profile.setProfileName(rs.getString(PROFILE_NAME));
					profile.setDelivery_commission(rs.getInt(DC));					
					profileList.add(profile);
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
		int size = profileList.size();
		if (size > 0) {
			return (ProfileDTO[]) profileList.toArray(new ProfileDTO[size]);
		}

		return null;
	}

	private ProfileDTO[] getDCProfile(Connection con, String profileName)
	throws SQLException, Exception {

		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ProfileDTO> profileList = new ArrayList<ProfileDTO>();
		ProfileDTO profile = null;

		try {
			ps = con.prepareStatement(GET_DC_PROFILE);
			if (ps != null) {
				ps.setString(1, profileName);
				rs = ps.executeQuery();
				while (rs.next()) {
					profile = new ProfileDTO();

					profile.setProfileName(rs.getString(PROFILE_NAME));
					profile.setDelivery_commission(rs.getInt(DC));
					profileList.add(profile);
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
		int size = profileList.size();
		if (size > 0) {
			return (ProfileDTO[]) profileList.toArray(new ProfileDTO[size]);
		}

		return null;
	}

	public String[] getDistinctDCProfile() throws SQLException, Exception {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<String> distinctProfile = new ArrayList<String>();
		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				ps = con.prepareStatement(GET_DISTINCT_DC_PROFILE);
				if (ps != null) {
					rs = ps.executeQuery();
					while (rs.next()) {
						distinctProfile.add(rs.getString(PROFILE_NAME));
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
		int size = distinctProfile.size();
		if (size > 0) {
			return (String[]) distinctProfile.toArray(new String[size]);
		}
		return null;
	}
	
	public void setDCProfileCommission(String[] stationCode, String profilename)
	throws SQLException, Exception {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				pst = con.prepareStatement(UPDATE_DC_PROFILE_COMMISSION);
				if (null != pst) {
					int batch_count = stationCode.length;
					for (int i = 0; i < batch_count; i++) {
						pst.setString(1, profilename);
						pst.setString(2, stationCode[i]);
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
	
	public void deleteDCProfile(String profileName) throws SQLException, Exception {
		Connection con = null;
		PreparedStatement pst = null;

		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				pst = con.prepareStatement(DELETE_DC_PROFILE);
				if (null != pst) {
					pst.setString(1, profileName);
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
	
	public boolean isDCProfileExist(String profileName) throws SQLException,
	Exception {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		boolean isExist = false;
		try {
			DBHelper db = new DBHelper();
			con = db.getConnection();
			if (null != con) {
				pst = con.prepareStatement(IS_DC_PROFILE_EXIST);
				if (null != pst) {
					pst.setString(1, profileName);
					rs = pst.executeQuery();
				}
				if (rs != null) {
					if (rs.next()) {
						isExist = true;
					}
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

		return isExist;
	}


}
