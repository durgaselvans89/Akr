package hm.akr.interfaces;

import hm.akr.dto.BookingCommissionDTO;
import hm.akr.dto.CartageCommissionDTO;
import hm.akr.dto.CommissionSummaryDTO;
import hm.akr.dto.DDDetailsDTO;
import hm.akr.dto.DeliveryCommissionDTO;
import hm.akr.dto.ProfileDTO;
import hm.akr.dto.RefundRecoveryDTO;
import hm.akr.exceptions.BusinessException;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

/**
 * Interface class for the commission bean
 * 
 * @author Hakuna Matata
 * @version 1.0
 * @copyright (c) AKR Parcel Service
 */
public interface Commission {
	
	public void setPassword(String stnCode,String password)throws RemoteException,Exception;
	
	
	public String getPassword(String stnCode)throws RemoteException,Exception;
	
	/**
	 * Method to get the booking/cc commission details for the given station code and the date.
	 * 
	 * @param stationCode  Station Code
	 * 
	 * @param date  Month and year for which the booking commission details needs to be fetched.
	 * 
	 * @return BookingCommissionDTO[] Array instance of BookingCommission DTO.
	 * 
	 * @throws RemoteException
	 */
	public BookingCommissionDTO[] getLRWithCommission(String stationCode,Date date,String dbHistory)
				throws RemoteException, Exception;
	
	/**
	 * Method to get the booking commission details of the current month for the given station 
	 * code.
	 * 
	 * @param stationCode  Station code. This parameter should not be null.
	 * 
	 * @return BookingCommissionDTO[] Array instance of Booking commission dto.
	 * 
	 * @throws RemoteException 
	 */
	public BookingCommissionDTO[] getLRWithCommission(String stationCode,String dbHistory)throws RemoteException,Exception;
	
	/**
	 * Method to get the delivery commission details for the given station code and the date
	 *  
	 * @param stationCode  Station Code 
	 * 
	 * @param date  Month and year for which the booking commission details needs to be fetched.
	 * 
	 * @return DeliveryCommissionDTO[] Array instance of Delivery Commission DTO. 
	 * 
	 * @throws RemoteException
	 */
	public DeliveryCommissionDTO[] getDeliveryCommissionList(String stationCode,Date date,String dbHistory)
						throws RemoteException, Exception;
	
	/**
	 * Method to get the delivery commission details of the current month for the given station code
	 * 
	 * @param stationCode  Station code. This parameter should not be null.
	 * 
	 * @return DeliveryCommission[] Array instance of the Delivery Commission DTO
	 * 
	 * @throws RemoteException
	 */
	public DeliveryCommissionDTO[] getDeliveryCommissionList(String stationCode,String dbHistory)throws RemoteException,Exception;

	/**
	 * Method to get the Cartage Collection commission details for the given station code and date
	 * code
	 * 
	 * @param stationCode Station Code
	 *  
	 * @param date  Month and year for which the cc commission details needs to be retrieved.
	 * 
	 * @return CartageCommissionDTO[] Array instance of Cartage Collection Commission DTO
	 * 
	 * @throws RemoteException
	 */
	public CartageCommissionDTO[] getCCCommissionList(String stationCode,Date date,String dbHistory)
					throws RemoteException, Exception;
	
	/**
	 * Method to get the cartage collection commission details of the current month for the given station
	 * code.
	 * 
	 * @param stationCode   Station code. This parameter should not be null.
	 * 
	 * @return CartageCommissionDTO[]  Array instance of CartageCommissionDTO
	 * 
	 * @throws RemoteException
	 */
	public CartageCommissionDTO[] getCCCommissionList(String stationCode,String dbHistory) throws RemoteException,Exception;
	
	
	/**
	 * Method to get the DDDetails for the given station code and date
	 * code
	 * 
	 * @param stationCode Station Code
	 *  
	 * @param date  Month and year for which the  DDDetails needs to be retrieved.
	 * 
	 * @return DDDetails[] Array instance of DDDetails DTO
	 * 
	 * @throws RemoteException
	 */
	public DDDetailsDTO[] getDDDetailsList(String stationCode,Date date,String dbHistory)throws RemoteException, Exception;
	
	/**
	 * Method to get the DD Details of the current month for the given station
	 * code.
	 * 
	 * @param stationCode   Station code. This parameter should not be null.
	 * 
	 * @return DDDetails[]  Array instance of DDDetails DTO
	 * 
	 * @throws RemoteException
	 */
	public DDDetailsDTO[] getDDDetailsList(String stationCode,String dbHistory) throws RemoteException,Exception;
	
	/**
	 * Method to get the refund details for the given station code and date.
	 * 
	 * @param stationCode  Station Code. This parameter should not be null.
	 * 
	 * @param date   Month and year for which the cc commission details needs to be retrieved.
	 * 
	 * @return RefundRecoveryDTO[] Array Instance of RefundRecoveryDTO
	 * 
	 * @throws RemoteException
	 */
	public RefundRecoveryDTO[] getRefundDetailList(String stationCode,Date date, String dbHistory)throws RemoteException, Exception;
	
	/**
	 * Method to get the refund details of the current month for the given station code.
	 * 
	 * @param stationCode  Station Code. This parameter should not be null.
	 * 
	 * @return RefundRecoveryDTO[] Array Instance of RefundRecoveryDTO
	 * 
	 * @throws RemoteException
	 */
	public RefundRecoveryDTO[] getRefundDetailList(String stationCode,String dbHistory)throws RemoteException,Exception;
	
	/**
	 * Method to get the recovery details for the given station code and date.
	 * 
	 * @param stationCode  Station Code. This parameter should not be null.
	 * 
	 * @param date   Month and year for which the cc commission details needs to be retrieved.
	 * 
	 * @return RefundRecoveryDTO[] Array Instance of RefundRecoveryDTO
	 * 
	 * @throws RemoteException
	 */
	public RefundRecoveryDTO[] getRecoveryDetailList(String stationCode,Date date, String dbHistory)throws RemoteException, Exception;
	
	/**
	 * Method to get the recovery details of the current month for the given station code.
	 * 
	 * @param stationCode  Station Code. This parameter should not be null.
	 * 
	 * @return RefundRecoveryDTO[] Array Instance of RefundRecoveryDTO
	 * 
	 * @throws RemoteException
	 */
	public RefundRecoveryDTO[] getRecoveryDetailList(String stationCode,String dbHistory)throws RemoteException,Exception;
	
	/**
	 * Method to get the overall commission summary for the given station code and for the given month.
	 * 
	 * @param stationCode  Station Code. This parameter should not be null.
	 * 
	 * @param date   Month and year for which the commission summary details needs to be retrieved.
	 * 
	 * @return CommissionSummaryDTO
	 * 
	 * @throws RemoteException
	 */
	public CommissionSummaryDTO getCommissionSummary(String stationCode,Date date,String dbHistory)throws RemoteException, Exception;
	
	/**
	 * Method to get the overall commission summary of the current month for the given station code.
	 * 
	 * @param stationCode  Station Code. This parameter should not be null.
	 * 
	 * @return CommissionSummaryDTO
	 * 
	 * @throws RemoteException
	 */
	public CommissionSummaryDTO getCommissionSummary_month(String stationCode,String dbHistory)throws RemoteException,Exception;

	/***************** Admin Side **************/
	
	/*********Booking Commission**********/
	
	/**
	 *  
	 * @param profile ProfileDTO
	 * 
	 * @return profileName	String
	 * 
	 * @throws RemoteException
	 */
	public String createProfile(ProfileDTO profile)throws RemoteException,Exception;
	
	public ProfileDTO[] getProfileList() throws RemoteException,Exception;
	
	public String[] getDistinctProfile()throws RemoteException, Exception;
	
	public void setBookingCommission(String[] stationCode,String profilename)throws RemoteException,Exception;
	
	public void createRecoveryRegister(String[] stationCode,String description,String contacts,HashMap<Integer, Float> installamount)throws RemoteException,Exception;
	
	public void createRefundRegister(String[] stationCode,String description,String contacts,HashMap<Integer, Float> installamount)throws RemoteException,Exception;
	
	public void deleteRecovery(int recoveryId)throws RemoteException,Exception;
	
	public void deleteRefund(int refundId)throws RemoteException,Exception;
	
	
	/****************Delivery Commission************/
	//public StationDTO[] getDCList()throws RemoteException,Exception;
	
	public void setDCCommissionList(String[] stationCode,float deliveryCommission)throws RemoteException,Exception;
	
	public float getDCCommission(String stationCode)throws RemoteException,Exception;
	
	public Float[] getDistinctDCCommission()throws RemoteException,Exception;	
			
			
	/**************CCCharge Commission****************/
	//public StationDTO[] getCCCList()throws RemoteException,Exception;
	
	
	public void setCCCommissionList(String[] station_code,float ccCommission)throws BusinessException,Exception;
	
	
	public float getCCCommission(String stationCode)throws BusinessException,Exception;
	
	
	public Float[] getDistinctCCCommission()throws RemoteException,Exception;

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

	
	public boolean isProfileExist(String profileName) throws RemoteException, Exception;
	
	public void deleteProfile(String profileId) throws RemoteException, Exception;
	
	public void setBookingCommissionEffDate(String[] stationCode,String profilename, Date date)throws RemoteException,Exception;
	
	public void setCCConsiderRefund(String[] station, int consider, int refund, int ccLimit) throws Exception;
	
	// for CC
	public String createCCProfile(ProfileDTO profile)throws Exception;
	
	public ProfileDTO[] getCCProfileList()throws Exception;
	
	public String[] getDistinctCCProfile()throws Exception;	
	
	public boolean isCCProfileExist(String profileName) throws Exception;
	
	public void deleteCCProfile(String profileId) throws Exception;
	
	public void setCCProfileCommission(String[] stationCode,String profilename)throws Exception;
	
	// for DC
	public String createDCProfile(ProfileDTO profile)throws Exception;
	
	public ProfileDTO[] getDCProfileList()throws Exception;
	
	public String[] getDistinctDCProfile()throws Exception;	
	
	public boolean isDCProfileExist(String profileName) throws Exception;
	
	public void deleteDCProfile(String profileId) throws Exception;
	
	public void setDCProfileCommission(String[] stationCode,String profilename)throws Exception;
	
	public BookingCommissionDTO[] getLRWithCommissionHistory(String stationCode,
			Date date, String dbHistory) throws Exception;
	
	public DeliveryCommissionDTO[] getDeliveryCommissionListHistory(
			String stationCode, Date date, String dbHistory) throws Exception;
	
	public CartageCommissionDTO[] getCCCommissionListHistory(String stationCode,
			Date date, String dbHistory) throws Exception;
	
	public DDDetailsDTO[] getDDDetailsListHistory(String stationCode, Date date,String dbHistory)
	throws Exception;
	
	public CommissionSummaryDTO getCommissionSummaryHistory(String stationCode,
			Date date, String dbHistory) throws Exception;
	
}

