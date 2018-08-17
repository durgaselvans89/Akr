package com.hm.dao;

import hm.akr.dto.BookingCommissionDTO;
import hm.akr.dto.CartageCommissionDTO;
import hm.akr.dto.CommissionSummaryDTO;
import hm.akr.dto.DDDetailsDTO;
import hm.akr.dto.DeliveryCommissionDTO;
import hm.akr.dto.ProfileDTO;
import hm.akr.dto.RefundRecoveryDTO;
import hm.akr.dto.StationDTO;
import hm.akr.exceptions.BusinessException;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;


public interface ICommissionDAO {
	
	
	public void setPassword(String stnCode,String password)throws SQLException,Exception;
	
	
	public String getPassword(String stnCode)throws SQLException,Exception;
	


			/**************Client Side***************/
	
	/**
	 * Method to get the booking commission details for the given station code for the
	 * given month and year.
	 * 
	 * @param stationCode  Station Code
	 * @param month  Month of the format 'MM'
	 * @param year   Year of the format 'yyyy'
	 * 
	 * @return BookingCommissionDTO[] Array instance of BookingCommission DTO.
	 * 
	 * @throws SQLException
	 */
	public BookingCommissionDTO[] getLRWithCommission(String stationCode,String month, String year,String dbHistory)
				throws SQLException, Exception;
	
	
	/**
	 * Method to get the delivery commission details for the given station code for the
	 * given month and year.
	 * 
	 * @param stationCode  Station Code
	 * @param month  Month of the format 'MM'
	 * @param year   Year of the format 'yyyy'
	 * 
	 * @return DeliveryCommissionDTO[] Array instance of DeliveryCommission DTO.
	 * 
	 * @throws SQLException
	 */
	public DeliveryCommissionDTO[] getDeliveryCommissionList(String stationCode,String month,String year,String dbHistory)
						throws SQLException, Exception;
	
	
	/**
	 * Method to get the cartage commission details for the given station code for the
	 * given month and year.
	 * 
	 * @param stationCode  Station Code
	 * @param month  Month of the format 'MM'
	 * @param year   Year of the format 'yyyy'
	 * 
	 * @return CartageCommissionDTO[] Array instance of CartageCommission DTO.
	 * 
	 * @throws SQLException
	 */
	public CartageCommissionDTO[] getCCCommissionList(String stationCode,String month,String year,String dbHistory)
					throws SQLException, Exception;
	
	
	
	/**
	 * Method to get the DD Details of the current month for the given station
	 * code.
	 * 
	 * @param stationCode   Station code. This parameter should not be null.
	 * 
	 * @return DDDetails[]  Array instance of DDDetails DTO
	 * 
	 * @throws SQLException
	 */
	public DDDetailsDTO[] getDDDetailsList(String stationCode,String month,String year,String dbHistory) throws SQLException,Exception;
	
	
	/**
	 * Method to get the refund details for the given station code and date.
	 * 
	 * @param stationCode  Station Code. This parameter should not be null.
	 * 
	 * @param date   Month and year for which the cc commission details needs to be retrieved.
	 * 
	 * @return RefundRecoveryDTO[] Array Instance of RefundRecoveryDTO
	 * 
	 * @throws SQLException
	 */
	public RefundRecoveryDTO[] getRefundDetailList(String stationCode,String month,String year,String dbHistory)throws SQLException, Exception;
	
	
	/**
	 * Method to get the recovery details for the given station code and date.
	 * 
	 * @param stationCode  Station Code. This parameter should not be null.
	 * 
	 * @param date   Month and year for which the cc commission details needs to be retrieved.
	 * 
	 * @return RefundRecoveryDTO[] Array Instance of RefundRecoveryDTO
	 * 
	 * @throws SQLException
	 */
	public RefundRecoveryDTO[] getRecoveryDetailList(String stationCode,String month,String year, String dbHistory)throws SQLException, Exception;
	

	/**
	 * Method to get the overall commission summary for the given station code and for the given month.
	 * 
	 * @param stationCode  Station Code. This parameter should not be null.
	 * 
	 * @param date   Month and year for which the cc commission details needs to be retrieved.
	 * 
	 * @return CommissionSummaryDTO
	 * 
	 * @throws SQLException
	 */
	public CommissionSummaryDTO getCommissionSummary(String stationCode,String month,String year,String dbHistory)throws SQLException, Exception;
	
	
	/***************** Admin Side **************/
	
	/*********Booking Commission**********/
	
	/**
	 *  
	 * @param profile ProfileDTO
	 * 
	 * @return profileName	String
	 * 
	 * @throws SQLException
	 */
	public String createProfile(ProfileDTO profile)throws SQLException,Exception;
	
	public ProfileDTO[] getProfileList()throws SQLException,Exception;
	
	public String[] getDistinctProfile()throws SQLException, Exception;	
	
	public boolean isProfileExist(String profileName) throws SQLException, Exception;
	
	public void deleteProfile(String profileId) throws SQLException, Exception;
	
	// for CC
	public String createCCProfile(ProfileDTO profile)throws SQLException,Exception;
	
	public ProfileDTO[] getCCProfileList()throws SQLException,Exception;
	
	public String[] getDistinctCCProfile()throws SQLException, Exception;	
	
	public boolean isCCProfileExist(String profileName) throws SQLException, Exception;
	
	public void deleteCCProfile(String profileId) throws SQLException, Exception;
	
	public void setCCProfileCommission(String[] stationCode,String profilename)throws SQLException,Exception;
	
	
	
	public void setBookingCommission(String[] stationCode,String profilename)throws SQLException,Exception;
	
	public void createRecoveryRegister(String[] stationCode,String description,String contacts,HashMap<Integer, Float> installamount)throws SQLException,Exception;
	
	public void createRefundRegister(String[] stationCode,String description,String contacts,HashMap<Integer, Float> installamount)throws SQLException,Exception;
	
	public void deleteRecovery(int recoveryId)throws SQLException,Exception;
	
	public void deleteRefund(int refundId)throws SQLException,Exception;
	
	
	/****************Delivery Commission************/
	public StationDTO[] getDCList()throws SQLException,Exception;
	
	
	public void setDCCommissionList(String[] stationCode,float deliveryCommission)throws SQLException,Exception;
	
	
	public float getDCCommission(String stationCode)throws SQLException,Exception;
	
	
	public Float[] getDistinctDCCommission()throws SQLException,Exception;	
			
			
	/**************CCCharge Commission****************/
	public StationDTO[] getCCCList()throws SQLException,Exception;
	
	
	public void setCCCommissionList(String[] station_code,float ccCommission)throws SQLException,Exception;
	
	
	public float getCCCommission(String stationCode)throws SQLException,Exception;
	
	
	public Float[] getDistinctCCCommission()throws SQLException,Exception;
	
	/**
	 * 
	 * @param branch_code
	 * @param day
	 * @param month
	 * @return
	 * @throws BusinessException
	 * @throws Exception
	 */
	public CommissionSummaryDTO[] getAdminCommission(String branch_code,int day,int month) throws BusinessException,Exception;

	
	public void setBookingCommissionEffDate(String[] stationCode,String profilename, Date date)throws SQLException,Exception;

	public void setCCConsiderRefund(String[] station, int consider, int refund, int ccLimit) throws Exception;
	
	// for DC
	public String createDCProfile(ProfileDTO profile)throws Exception;
	
	public ProfileDTO[] getDCProfileList()throws Exception;
	
	public String[] getDistinctDCProfile()throws Exception;	
	
	public boolean isDCProfileExist(String profileName) throws Exception;
	
	public void deleteDCProfile(String profileId) throws Exception;
	
	public void setDCProfileCommission(String[] stationCode,String profilename)throws Exception;

	public BookingCommissionDTO[] getLRWithCommissionHistory(String stationCode,String month, String year, String dbHistory)
	throws Exception;


	public DeliveryCommissionDTO[] getDeliveryCommissionListHistory(String stationCode, String month, String year, String dbHistory)
	 throws Exception;
	
	public CartageCommissionDTO[] getCCCommissionListHistory(String stationCode,
			String month, String year, String dbHistory) throws Exception;
	
	public DDDetailsDTO[] getDDDetailsListHistory(String stationCode, String month,
			String year,String dbHistory) throws SQLException, Exception;
	
	public CommissionSummaryDTO getCommissionSummaryHistory(String stationCode,
			String month, String year, String dbHistory) throws SQLException, Exception;
			
}
