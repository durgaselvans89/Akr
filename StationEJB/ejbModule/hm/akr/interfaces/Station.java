/**
 * 
 * @version 1.0
 */
package hm.akr.interfaces;

import hm.akr.dto.CCProfileDTO;
import hm.akr.dto.CardRateProfileDTO;
import hm.akr.dto.DCProfileDTO;
import hm.akr.dto.DistanceListDTO;
import hm.akr.dto.LRNumberRangeDTO;
import hm.akr.dto.StationsDTO;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Date;

/**
 * Station Interface
 * 
 * @version 1.0
 */
public interface Station {

	/**
	 * 
	 * @param branch
	 * @param dto
	 * @return
	 * @throws RemoteException
	 */
	public boolean addStation(String branch, StationsDTO dto)
			throws RemoteException, Exception;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws RemoteException
	 */
	public boolean deleteStation(int id) throws RemoteException, Exception;

	/**
	 * 
	 * @param region
	 * @param dto
	 * @return
	 * @throws RemoteException
	 */
	public boolean addBranch(String region, StationsDTO dto)
			throws RemoteException, Exception;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws RemoteException
	 */
	public boolean deleteBranch(int id) throws RemoteException, Exception;

	/**
	 * 
	 * @param dto
	 * @return
	 * @throws RemoteException
	 */
	public boolean addRegion(StationsDTO dto) throws RemoteException, Exception;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws RemoteException
	 */
	public boolean deleteRegion(int id) throws RemoteException, Exception;

	/**
	 * 
	 * @return
	 * @throws RemoteException
	 */
	public StationsDTO[] getAllStations() throws RemoteException, Exception;

	/**
	 * 
	 * @param stationCode
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public StationsDTO[] getSelectedStations(String stationCode)
			throws RemoteException, Exception;

	/**
	 * 
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public StationsDTO[] getTRs() throws RemoteException, Exception;

	/**
	 * 
	 * @param lrFormat
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public boolean setStationaryValues(LRNumberRangeDTO[] lrFormat)
			throws RemoteException, Exception;

	/**
	 * 
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public LRNumberRangeDTO[] getStationaryValues() throws RemoteException,
			Exception;

	/**
	 * 
	 * @param stations
	 * @param types
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public boolean assignStationary(String[] stations, int[] types)
			throws RemoteException, Exception;

	/**
	 * 
	 * @param stationary
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public boolean assignStationary(LRNumberRangeDTO[] stationary)
			throws RemoteException, Exception;

	/**
	 * 
	 * @param lrFormat
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public boolean updateLRFormat(LRNumberRangeDTO[] lrFormat)
			throws RemoteException, Exception;

	/**
	 * 
	 * @param stationName
	 * @param type
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public LRNumberRangeDTO[] getLRRange(String stationCode)
			throws RemoteException, Exception;

	/**
	 * 
	 * @param stations
	 * @param topay
	 * @param paid
	 * @param billing
	 * @param cr
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public boolean setAlarmSettings(String[] stations, int topay, int paid,
			int billing, int cr) throws RemoteException, Exception;

	/**
	 * 
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public LRNumberRangeDTO[] getStationaryReport() throws RemoteException,
			Exception;

	/**
	 * Method to set the BF_Increment for list of stations. This method is
	 * called for updation of bf_increment data.
	 * 
	 * @param stationCode
	 *            StationCode List
	 * 
	 * @param bf_increment
	 *            BFIncrement
	 * 
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void setBFIncrement(String[] station_code, int bf_increment)
			throws RemoteException, Exception;

	/**
	 * Method to set the discount for list of stations. This method is called
	 * for updation of discount data.
	 * 
	 * @param stationCode
	 *            StationCode List
	 * 
	 * @param discount
	 *            Discount
	 * 
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void setBFDecrement(String[] station_code, int discount)
			throws RemoteException, Exception;

	/**
	 * Method to get the distance list for the given station code. The method
	 * returns the list of distance from the given station code to all the
	 * available stations.
	 * 
	 * @param stationCode
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public DistanceListDTO[] getDistanceList(String stationCode)
			throws RemoteException, Exception;

	/**
	 * Method to set the distance list.
	 * 
	 * @param stationCode
	 * @param destDistance
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void setDistanceList(String stationCode,
			DistanceListDTO[] distanceList) throws RemoteException, Exception;

	/**
	 * 
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public StationsDTO[] getBFIncrementAndDiscount() throws RemoteException,
			Exception;

	/**
	 * Method to set the list of manage sundry using the RegularSundryDTO This
	 * method is called for insertion/updation of manage sundry data.
	 * 
	 * @param dto
	 *            StationDTO list
	 * 
	 * @throws SQLException
	 * @throws Exception
	 */
	public void setManageSundrySetting(StationsDTO[] dto)
			throws RemoteException, Exception;

	/**
	 * 
	 * @param dto
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void setDRSFine(StationsDTO[] dto) throws RemoteException, Exception;

	/**
	 * 
	 * @param station_code
	 * @param discount
	 * @throws SQLException
	 * @throws Exception
	 */
	public void setCCSundryLimit(String[] station_code, int discount)
			throws RemoteException, Exception;

	/**
	 * 
	 * @param station_code
	 * @param minFT
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void setMinimumFreight(String[] station_code, int minFT)
			throws RemoteException, Exception;

	/**
	 * 
	 * @param station_code
	 * @param minWT
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void setMinimumWeight(String[] station_code, int minWT)
			throws RemoteException, Exception;

	/**
	 * 
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public StationsDTO[] getManageCommission() throws RemoteException,
			Exception;

	/**
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public void updateManageCommisssion(StationsDTO[] dto) throws Exception;

	/**
	 * 
	 * @param station_code
	 * @param cc
	 * @throws SQLException
	 * @throws Exception
	 */
	public void setCCForSpecialSundry(String[] station_code, int cc)
			throws RemoteException, Exception;

	/**
	 * 
	 * @param station_code
	 * @param cc
	 * @throws SQLException
	 * @throws Exception
	 */
	public void setCCForCommodity(String[] station_code, int cc)
			throws RemoteException, Exception;

	/**
	 * 
	 * @param station_code
	 * @param customer
	 * @param ccArticle
	 * @param refundArticle
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void setCCForHLC(String[] station_code, String customer,
			float ccArticle, float refundArticle) throws RemoteException,
			Exception;

	/**
	 * 
	 * @param station_code
	 * @param tamil
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void setStationLanguage(String[] station_code, byte tamil)
			throws RemoteException, Exception;

	/**
	 * 
	 * @param station_code
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public CardRateProfileDTO[] getCardRateProfile(String station_code)
			throws RemoteException, Exception;
	/**
	 * 
	 * @param station_code
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public CCProfileDTO[] getCCDetails(String station_code)
	throws SQLException, Exception;
	
	/**
	 * 
	 * @param station_code
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public DCProfileDTO[] getDCDetails(String station_code)
	throws SQLException, Exception;
	
	/**
	 * 
	 * @param customer
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public float getRefundPerArticleForHLC(String customer,String station_code) throws SQLException, Exception;
	
	public void setHolidayEntry(Date holidayDate,String holidayDesc)throws SQLException,Exception;
} 
