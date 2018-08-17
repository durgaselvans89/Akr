package com.hm.bean.commission.ejb;

import hm.akr.dto.BookingCommissionDTO;
import hm.akr.dto.CartageCommissionDTO;
import hm.akr.dto.CommissionSummaryDTO;
import hm.akr.dto.DDDetailsDTO;
import hm.akr.dto.DeliveryCommissionDTO;
import hm.akr.dto.ProfileDTO;
import hm.akr.dto.RefundRecoveryDTO;
import hm.akr.dto.StationDTO;
import hm.akr.exceptions.BusinessException;
import hm.akr.interfaces.Commission;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.hm.dao.CommissionDAO;
import com.hm.dao.ICommissionDAO;

@Stateless
@Remote(Commission.class)
@TransactionManagement(TransactionManagementType.BEAN)
/**
 * @author 
 * @version 1.0
 * @copyright (c) AKR
 */
public class CommissionBean implements Commission {

	// @EJB Station station;
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor method
	 */
	public CommissionBean() {
		super();
	}

	/** ************Client Side************** */

	/**
	 * Method to get the booking commission details for the given station code
	 * and the date.
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
	 * @throws RemoteException
	 */
	public BookingCommissionDTO[] getLRWithCommission(String stationCode,
			Date date,String dbHistory) throws RemoteException, Exception {
		ICommissionDAO commission = new CommissionDAO();
		BookingCommissionDTO[] bookingCommission = null;

		try {
			String month = getMonth(date);
			String year = getYear(date);

			bookingCommission = commission.getLRWithCommission(stationCode,
					month, year,dbHistory);

		} catch (Exception exception) {			
			throw exception;
		}
		return bookingCommission;
	}
	
	public BookingCommissionDTO[] getLRWithCommissionHistory(String stationCode,
			Date date, String dbHistory) throws Exception {
		ICommissionDAO commission = new CommissionDAO();
		BookingCommissionDTO[] bookingCommission = null;

		try {
			String month = getMonth(date);
			String year = getYear(date);

			bookingCommission = commission.getLRWithCommissionHistory(stationCode,
					month, year, dbHistory);

		} catch (Exception exception) {			
			throw exception;
		}
		return bookingCommission;
	}

	/**
	 * Method to get the booking commission details of the current month for the
	 * given station code.
	 * 
	 * @param stationCode
	 *            Station code. This parameter should not be null.
	 * 
	 * @return BookingCommissionDTO[] Array instance of Booking commission dto.
	 * 
	 * @throws RemoteException
	 */
	public BookingCommissionDTO[] getLRWithCommission(String stationCode,String dbHistory)
			throws RemoteException, Exception {

		ICommissionDAO commission = new CommissionDAO();
		BookingCommissionDTO[] bookingCommission = null;

		try {
			Date currDate = new Date();
			String month = getMonth(currDate);
			String year = getYear(currDate);

			bookingCommission = commission.getLRWithCommission(stationCode,
					month, year,dbHistory);

		} catch (Exception exception) {			
			throw exception;
		}
		return bookingCommission;
	}

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
	 * @throws RemoteException
	 */
	public DeliveryCommissionDTO[] getDeliveryCommissionList(
			String stationCode, Date date,String dbHistory) throws RemoteException, Exception {
		ICommissionDAO commission = new CommissionDAO();
		DeliveryCommissionDTO[] deliveryCommission = null;

		try {
			String month = getMonth(date);
			String year = getYear(date);
			deliveryCommission = commission.getDeliveryCommissionList(
					stationCode, month, year,dbHistory);

		} catch (Exception exception) {			
			throw exception;
		}
		return deliveryCommission;
	}
	
	public DeliveryCommissionDTO[] getDeliveryCommissionListHistory(
			String stationCode, Date date, String dbHistory) throws Exception {
		ICommissionDAO commission = new CommissionDAO();
		DeliveryCommissionDTO[] deliveryCommission = null;

		try {
			String month = getMonth(date);
			String year = getYear(date);
			deliveryCommission = commission.getDeliveryCommissionListHistory(
					stationCode, month, year, dbHistory);

		} catch (Exception exception) {			
			throw exception;
		}
		return deliveryCommission;
	}

	/**
	 * Method to get the delivery commission details of the current month for
	 * the given station code
	 * 
	 * @param stationCode
	 *            Station code. This parameter should not be null.
	 * 
	 * @return DeliveryCommission[] Array instance of the Delivery Commission
	 *         DTO
	 * 
	 * @throws RemoteException
	 */
	public DeliveryCommissionDTO[] getDeliveryCommissionList(String stationCode,String dbHistory)
			throws RemoteException, Exception {
		ICommissionDAO commission = new CommissionDAO();
		DeliveryCommissionDTO[] deliveryCommission = null;

		try {
			Date currDate = new Date();
			String month = getMonth(currDate);
			String year = getYear(currDate);

			deliveryCommission = commission.getDeliveryCommissionList(
					stationCode, month, year,dbHistory);

		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
		return deliveryCommission;
	}

	/**
	 * Method to get the current month.
	 * 
	 * @return
	 */
	private String getMonth(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("MM");
		return dateFormat.format(date);
	}

	/**
	 * 
	 * @return
	 */
	private String getYear(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy");
		return dateFormat.format(date);
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
	 * @throws RemoteException
	 */
	public CartageCommissionDTO[] getCCCommissionList(String stationCode,
			Date date,String dbHistory) throws RemoteException, Exception {
		ICommissionDAO commission = new CommissionDAO();
		CartageCommissionDTO[] cartageCommission = null;

		try {
			String month = getMonth(date);
			String year = getYear(date);
			cartageCommission = commission.getCCCommissionList(stationCode,
					month, year,dbHistory);

		} catch (Exception exception) {			
			throw exception;
		}
		return cartageCommission;
	}
	
	public CartageCommissionDTO[] getCCCommissionListHistory(String stationCode,
			Date date, String dbHistory) throws Exception {
		ICommissionDAO commission = new CommissionDAO();
		CartageCommissionDTO[] cartageCommission = null;

		try {
			String month = getMonth(date);
			String year = getYear(date);
			cartageCommission = commission.getCCCommissionListHistory(stationCode,
					month, year, dbHistory);

		} catch (Exception exception) {			
			throw exception;
		}
		return cartageCommission;
	}

	/**
	 * Method to get the cartage collection commission details of the current
	 * month for the given station code.
	 * 
	 * @param stationCode
	 *            Station code. This parameter should not be null.
	 * 
	 * @return CartageCommissionDTO[] Array instance of CartageCommissionDTO
	 * 
	 * @throws RemoteException
	 */
	public CartageCommissionDTO[] getCCCommissionList(String stationCode,String dbHistory)
			throws RemoteException, Exception {
		ICommissionDAO commission = new CommissionDAO();
		CartageCommissionDTO[] cartageCommission = null;

		try {
			Date currDate = new Date();
			String month = getMonth(currDate);
			String year = getYear(currDate);

			cartageCommission = commission.getCCCommissionList(stationCode,
					month, year,dbHistory);

		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
		return cartageCommission;
	}

	/**
	 * Method to get the refund details for the given station code and date.
	 * 
	 * @param stationCode
	 *            Station Code. This parameter should not be null.
	 * 
	 * @param date
	 *            Month and year for which the refund commission details needs
	 *            to be retrieved.
	 * 
	 * @return RefundRecoveryDTO[] Array Instance of RefundRecoveryDTO
	 * 
	 * @throws RemoteException
	 */
	public RefundRecoveryDTO[] getRefundDetailList(String stationCode, Date date, String dbHistory)
			throws RemoteException, Exception {
		ICommissionDAO commission = new CommissionDAO();
		RefundRecoveryDTO[] refund = null;

		try {
			String month = getMonth(date);
			String year = getYear(date);
			refund = commission.getRefundDetailList(stationCode, month, year, dbHistory);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
		return refund;
	}

	/**
	 * Method to get the refund details of the current month for the given
	 * station code.
	 * 
	 * @param stationCode
	 *            Station Code. This parameter should not be null.
	 * 
	 * @return RefundRecoveryDTO[] Array Instance of RefundRecoveryDTO
	 * 
	 * @throws RemoteException
	 */
	public RefundRecoveryDTO[] getRefundDetailList(String stationCode,String dbHistory)
			throws RemoteException, Exception {
		ICommissionDAO commission = new CommissionDAO();
		RefundRecoveryDTO[] refund = null;
		try {
			Date currDate = new Date();
			String month = getMonth(currDate);
			String year = getYear(currDate);

			refund = commission.getRefundDetailList(stationCode, month, year, dbHistory);

		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
		return refund;
	}

	/**
	 * Method to get the recovery details for the given station code and date.
	 * 
	 * @param stationCode
	 *            Station Code. This parameter should not be null.
	 * 
	 * @param date
	 *            Month and year for which the recovery commission details needs
	 *            to be retrieved.
	 * 
	 * @return RefundRecoveryDTO[] Array Instance of RefundRecoveryDTO
	 * 
	 * @throws RemoteException
	 */
	public RefundRecoveryDTO[] getRecoveryDetailList(String stationCode,
			Date date, String dbHistory) throws RemoteException, Exception {
		ICommissionDAO commission = new CommissionDAO();
		RefundRecoveryDTO[] recovery = null;
		try {
			String month = getMonth(date);
			String year = getYear(date);
			recovery = commission.getRecoveryDetailList(stationCode, month,
					year, dbHistory);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
		return recovery;
	}

	/**
	 * Method to get the recovery details of the current month for the given
	 * station code.
	 * 
	 * @param stationCode
	 *            Station Code. This parameter should not be null.
	 * 
	 * @return RefundRecoveryDTO[] Array Instance of RefundRecoveryDTO
	 * 
	 * @throws RemoteException
	 */
	public RefundRecoveryDTO[] getRecoveryDetailList(String stationCode,String dbHistory)
			throws RemoteException, Exception {
		ICommissionDAO commission = new CommissionDAO();
		RefundRecoveryDTO[] recovery = null;
		try {
			Date currDate = new Date();
			String month = getMonth(currDate);
			String year = getYear(currDate);

			recovery = commission.getRecoveryDetailList(stationCode, month,
					year, dbHistory);

		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
		return recovery;
	}

	/**
	 * Method to get the overall commission summary for the given station code
	 * and for the given month.
	 * 
	 * @param stationCode
	 *            Station Code. This parameter should not be null.
	 * 
	 * @param date
	 *            Month and year for which the cc commission details needs to be
	 *            retrieved.
	 * 
	 * @return CommissionSummaryDTO
	 * 
	 * @throws RemoteException
	 */
	public CommissionSummaryDTO getCommissionSummary(String stationCode,
			Date date,String dbHistory) throws RemoteException, Exception {
		ICommissionDAO commission = new CommissionDAO();
		CommissionSummaryDTO summary = null;
		try {
			String month = getMonth(date);
			String year = getYear(date);
			summary = commission.getCommissionSummary(stationCode, month, year,dbHistory);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
		return summary;
	}
	
	public CommissionSummaryDTO getCommissionSummaryHistory(String stationCode,
			Date date, String dbHistory) throws RemoteException, Exception {
		ICommissionDAO commission = new CommissionDAO();
		CommissionSummaryDTO summary = null;
		try {
			String month = getMonth(date);
			String year = getYear(date);
			summary = commission.getCommissionSummaryHistory(stationCode, month, year, dbHistory);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
		return summary;
	}

	/**
	 * Method to get the overall commission summary of the current month for the
	 * given station code.
	 * 
	 * @param stationCode
	 *            Station Code. This parameter should not be null.
	 * 
	 * @return CommissionSummaryDTO
	 * 
	 * @throws RemoteException
	 */
	public CommissionSummaryDTO getCommissionSummary_month(String stationCode,String dbHistory)
			throws RemoteException, Exception {
		ICommissionDAO commission = new CommissionDAO();
		CommissionSummaryDTO summary = null;
		try {
			Date currDate = new Date();
			String month = getMonth(currDate);
			String year = getYear(currDate);
			summary = commission.getCommissionSummary(stationCode, month, year,dbHistory);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
		return summary;
	}

	/** *************** Admin Side ************* */

	/** *******Booking Commission********* */

	/**
	 * 
	 * @param profile
	 *            ProfileDTO
	 * 
	 * @return profileName String
	 * 
	 * @throws RemoteException
	 */
	public String createProfile(ProfileDTO profile) throws RemoteException,
			Exception {
		ICommissionDAO commission = new CommissionDAO();
		String profile_id = null;
		try {
			if (null != profile) {
				profile_id = commission.createProfile(profile);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
		return profile_id;
	}

	public ProfileDTO[] getProfileList() throws RemoteException, Exception {
		ICommissionDAO commission = new CommissionDAO();
		ProfileDTO[] profile = null;
		try {

			profile = commission.getProfileList();

		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
		return profile;
	}

	public String[] getDistinctProfile() throws RemoteException, Exception {
		ICommissionDAO commission = new CommissionDAO();
		String[] profile = null;
		try {

			profile = commission.getDistinctProfile();

		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
		return profile;
	}

	public void setBookingCommission(String[] stationCode, String profilename)
			throws RemoteException, Exception {
		ICommissionDAO commission = new CommissionDAO();
		try {
			commission.setBookingCommission(stationCode, profilename);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
	}

	public void createRecoveryRegister(String[] stationCode,
			String description, String contacts,
			HashMap<Integer, Float> installamount) throws RemoteException,
			Exception {
		ICommissionDAO commission = new CommissionDAO();
		try {

			if (null != stationCode) {
				commission.createRecoveryRegister(stationCode, description,
						contacts, installamount);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
	}

	public void createRefundRegister(String[] stationCode, String description,
			String contacts, HashMap<Integer, Float> installamount)
			throws RemoteException, Exception {
		ICommissionDAO commission = new CommissionDAO();
		try {

			if (null != stationCode) {
				commission.createRefundRegister(stationCode, description,
						contacts, installamount);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
	}

	public void deleteRecovery(int recoveryId) throws RemoteException,
			Exception {
		ICommissionDAO commission = new CommissionDAO();
		try {
			commission.deleteRecovery(recoveryId);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
	}

	public void deleteRefund(int refundId) throws RemoteException, Exception {
		ICommissionDAO commission = new CommissionDAO();
		try {
			commission.deleteRefund(refundId);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
	}

	/** **************Delivery Commission*********** */
	public StationDTO[] getDCList() throws RemoteException, Exception {
		ICommissionDAO commission = new CommissionDAO();
		StationDTO[] station = null;

		try {

			station = commission.getDCList();

		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
		return station;
	}

	public void setDCCommissionList(String[] stationCode,
			float deliveryCommission) throws RemoteException, Exception {
		ICommissionDAO commission = new CommissionDAO();
		try {
			commission.setDCCommissionList(stationCode, deliveryCommission);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
	}

	public float getDCCommission(String stationCode) throws RemoteException,
			Exception {

		ICommissionDAO commission = new CommissionDAO();
		float deliveryCommission = 0;

		try {
			deliveryCommission = commission.getDCCommission(stationCode);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
		return deliveryCommission;
	}

	public Float[] getDistinctDCCommission() throws RemoteException, Exception {
		ICommissionDAO commission = new CommissionDAO();
		Float[] dcCommission = null;
		try {

			dcCommission = commission.getDistinctDCCommission();

		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
		return dcCommission;
	}

	/** ************CCCharge Commission*************** */
	public StationDTO[] getCCCList() throws RemoteException, Exception {
		ICommissionDAO commission = new CommissionDAO();
		StationDTO[] station = null;

		try {

			station = commission.getCCCList();

		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
		return station;
	}

	public void setCCCommissionList(String[] station_code, float ccCommission)
			throws BusinessException, Exception {
		ICommissionDAO commission = new CommissionDAO();
		try {
			commission.setCCCommissionList(station_code, ccCommission);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
	}

	public float getCCCommission(String stationCode) throws BusinessException,
			Exception {

		ICommissionDAO commission = new CommissionDAO();
		float cartageCommission = 0;

		try {
			cartageCommission = commission.getCCCommission(stationCode);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
		return cartageCommission;
	}

	public Float[] getDistinctCCCommission() throws RemoteException, Exception {
		ICommissionDAO commission = new CommissionDAO();
		Float[] ccCommission = null;
		try {

			ccCommission = commission.getDistinctCCCommission();

		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
		return ccCommission;
	}

	public CommissionSummaryDTO[] getAdminCommission(String branch_code,
			int day, int month) throws BusinessException, Exception {
		ICommissionDAO commission = new CommissionDAO();
		CommissionSummaryDTO[] commissionDTO = null;
		try {

			commissionDTO = commission.getAdminCommission(branch_code, day,
					month);

		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
		return commissionDTO;
	}

	public String getPassword(String stnCode) throws RemoteException, Exception {
		ICommissionDAO commission = new CommissionDAO();
		String password = null;

		try {
			password = commission.getPassword(stnCode);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
		return password;
	}

	public void setPassword(String stnCode, String password)
			throws RemoteException, Exception {
		ICommissionDAO commission = new CommissionDAO();
		try {
			commission.setPassword(stnCode, password);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
	}

	public DDDetailsDTO[] getDDDetailsList(String stationCode, Date date,String dbHistory)
			throws RemoteException, Exception {
		ICommissionDAO commission = new CommissionDAO();
		DDDetailsDTO[] ddDetails = null;

		try {
			String month = getMonth(date);
			String year = getYear(date);
			ddDetails = commission.getDDDetailsList(stationCode, month, year,dbHistory);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
		return ddDetails;
	}
	
	public DDDetailsDTO[] getDDDetailsListHistory(String stationCode, Date date,String dbHistory)
	throws RemoteException, Exception {
		ICommissionDAO commission = new CommissionDAO();
		DDDetailsDTO[] ddDetails = null;

		try {
			String month = getMonth(date);
			String year = getYear(date);
			ddDetails = commission.getDDDetailsListHistory(stationCode, month,
					year,dbHistory);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
		return ddDetails;
	}

	public DDDetailsDTO[] getDDDetailsList(String stationCode,String dbHistory )
			throws RemoteException, Exception {
		ICommissionDAO commission = new CommissionDAO();
		DDDetailsDTO[] ddDetails = null;

		try {
			Date currDate = new Date();
			String month = getMonth(currDate);
			String year = getYear(currDate);

			ddDetails = commission.getDDDetailsList(stationCode, month, year,dbHistory);

		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
		return ddDetails;
	}

	public boolean isProfileExist(String profileName) throws RemoteException,
			Exception {
		ICommissionDAO commission = new CommissionDAO();
		boolean isExist = false;
		try {
			isExist = commission.isProfileExist(profileName);

		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}

		return isExist;
	}

	public void deleteProfile(String profileId) throws RemoteException,
			Exception {
		ICommissionDAO commission = new CommissionDAO();
		try {
			commission.deleteProfile(profileId);

		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
	}

	public void setBookingCommissionEffDate(String[] stationCode,
			String profilename, Date date) throws RemoteException, Exception {
		ICommissionDAO commission = new CommissionDAO();
		try {
			commission.setBookingCommissionEffDate(stationCode, profilename,
					date);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
	}

	public void setCCConsiderRefund(String[] station, int consider, int refund,
			int ccLimit) throws Exception {
		ICommissionDAO commission = new CommissionDAO();
		try {
			commission.setCCConsiderRefund(station, consider, refund, ccLimit);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
	}
	
	
	
	@Override
	public String createCCProfile(ProfileDTO profile) throws Exception {
		ICommissionDAO commission = new CommissionDAO();
		String profile_id = null;
		try {
			if (null != profile) {
				profile_id = commission.createCCProfile(profile);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
		return profile_id;
	}

	@Override
	public void deleteCCProfile(String profileId) throws Exception {
		ICommissionDAO commission = new CommissionDAO();	
		try {
			if (null != profileId) {
				commission.deleteCCProfile(profileId);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
	}

	@Override
	public ProfileDTO[] getCCProfileList() throws Exception {
		ICommissionDAO commission = new CommissionDAO();
		ProfileDTO[] profile = null;
		try {
			
			profile = commission.getCCProfileList();
			
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
		return profile;
	}

	@Override
	public String[] getDistinctCCProfile() throws Exception {
		ICommissionDAO commission = new CommissionDAO();
		String[] profile = null;
		try {			
			profile = commission.getDistinctCCProfile();
			
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
		return profile;
	}

	@Override
	public boolean isCCProfileExist(String profileName) throws Exception {
		ICommissionDAO commission = new CommissionDAO();
		boolean profile = false;
		try {
			
			profile = commission.isCCProfileExist(profileName);
			
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
		return profile;
	}

	@Override
	public void setCCProfileCommission(String[] stationCode, String profilename)
			throws Exception {
		ICommissionDAO commission = new CommissionDAO();	
		try {			
			commission.setCCProfileCommission(stationCode, profilename);			
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}		
		
	}
	
	//DC
	
	@Override
	public String createDCProfile(ProfileDTO profile) throws Exception {
		ICommissionDAO commission = new CommissionDAO();
		String profile_id = null;
		try {
			if (null != profile) {
				profile_id = commission.createDCProfile(profile);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
		return profile_id;
	}

	@Override
	public void deleteDCProfile(String profileId) throws Exception {
		ICommissionDAO commission = new CommissionDAO();	
		try {
			if (null != profileId) {
				commission.deleteDCProfile(profileId);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
	}

	@Override
	public ProfileDTO[] getDCProfileList() throws Exception {
		ICommissionDAO commission = new CommissionDAO();
		ProfileDTO[] profile = null;
		try {
			
			profile = commission.getDCProfileList();
			
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
		return profile;
	}

	@Override
	public String[] getDistinctDCProfile() throws Exception {
		ICommissionDAO commission = new CommissionDAO();
		String[] profile = null;
		try {			
			profile = commission.getDistinctDCProfile();
			
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
		return profile;
	}

	@Override
	public boolean isDCProfileExist(String profileName) throws Exception {
		ICommissionDAO commission = new CommissionDAO();
		boolean profile = false;
		try {
			
			profile = commission.isDCProfileExist(profileName);
			
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
		return profile;
	}

	@Override
	public void setDCProfileCommission(String[] stationCode, String profilename)
			throws Exception {
		ICommissionDAO commission = new CommissionDAO();	
		try {			
			commission.setDCProfileCommission(stationCode, profilename);			
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}		
		
	}

}
