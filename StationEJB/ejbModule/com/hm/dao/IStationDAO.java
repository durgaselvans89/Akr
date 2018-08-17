/**
 * 
 * @version 1.0
 */
package com.hm.dao;

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
 * 
 * DAO class for Station
 */

public interface IStationDAO {

	/**
	 * @param dto
	 * @throws Exception
	 * @throws SQLException
	 * 
	 */
	public boolean addStation(String branch, StationsDTO dto)
			throws SQLException, Exception;

	/**
	 * 
	 * @param id
	 */
	public boolean deleteStation(int id) throws SQLException, Exception;

	/**
	 * @param dto
	 * 
	 */
	public boolean addBranch(String region, StationsDTO dto)
			throws SQLException, Exception;

	/**
	 * 
	 * @param id
	 */
	public boolean deleteBranch(int id) throws SQLException, Exception;

	/**
	 * @param dto
	 * 
	 */
	public boolean addRegion(StationsDTO dto) throws SQLException, Exception;

	/**
	 * 
	 * @param id
	 */
	public boolean deleteRegion(int id) throws SQLException, Exception;

	/**
	 * 
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public StationsDTO[] getAllStations() throws SQLException, Exception;

	/**
	 * 
	 * @param stationCode
	 * @return
	 * @throws Exception
	 */
	public StationsDTO[] getSelectedStations(String stationCode)
			throws Exception;

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public StationsDTO[] getTRs() throws Exception;

	/**
	 * 
	 * @param lrFormat
	 * @return
	 * @throws Exception
	 */
	public boolean setStationaryValues(LRNumberRangeDTO[] lrFormat)
			throws Exception;

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public LRNumberRangeDTO[] getStationaryValues() throws Exception;

	/**
	 * 
	 * @param stations
	 * @param types
	 * @return
	 * @throws Exception
	 */
	public boolean assignStationary(String[] stations, int[] types)
			throws Exception;

	/**
	 * 
	 * @param stations
	 * @param types
	 * @return
	 * @throws Exception
	 */
	public boolean assignStationary(LRNumberRangeDTO[] stationary)
			throws Exception;

	/**
	 * 
	 * @param lrFormat
	 * @return
	 * @throws Exception
	 */
	public boolean updateLRFormat(LRNumberRangeDTO[] lrFormat) throws Exception;

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
	 * @throws Exception
	 */
	public boolean setAlarmSettings(String[] stations, int topay, int paid,
			int billing, int cr) throws Exception;

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public LRNumberRangeDTO[] getStationaryReport() throws Exception;

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
	 * @throws SQLException
	 * @throws Exception
	 */
	public void setBFIncrement(String[] station_code, int bf_increment)
			throws SQLException, Exception;

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
	 * @throws SQLException
	 * @throws Exception
	 */
	public void setBFDecrement(String[] station_code, int discount)
			throws SQLException, Exception;

	/**
	 * Method to calculate the distance from the given station code to the rest
	 * of the stations. The method calls the stored procedures which returns
	 * back the record set containing the data.
	 * 
	 * @param stationCode
	 *            Station Code
	 * 
	 * @return DistanceListDTO An instance of DistanceListDTO
	 * 
	 * @throws SQLException
	 * @throws Exception
	 */
	public DistanceListDTO[] calculateDistance(String stationCode)
			throws SQLException, Exception;

	/**
	 * Method to set the distance between the given stationcode and the rest all
	 * stations which is available in the hashmap. This method is called for
	 * insertion of distance data. This method first inserts the data in a
	 * temporary table using batch statement and then calls the stored procedure
	 * which takes the input from the temporary table and updates the necessary
	 * data. The logic of handling/storing the distance is taken care by the
	 * stored procedures.
	 * 
	 * @param stationCode
	 *            Station Code
	 * 
	 * @param distanceList
	 *            Hashmap contains the destination station as the key and
	 *            distance as the value
	 * 
	 * @throws SQLException
	 * @throws Exception
	 */
	public void setDistanceList(String stationCode,
			DistanceListDTO[] distanceList) throws SQLException, Exception;

	/**
	 * 
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public StationsDTO[] getAllBFIncrementAndDiscount() throws SQLException,
			Exception;

	/**
	 * Method to set the list of manage sundry using the RegularSundry dto This
	 * method is called for insertion/updation of regular sundry data.
	 * 
	 * @param dto
	 *            RegularSundryDTO list
	 * 
	 * @throws SQLException
	 * @throws Exception
	 */
	public void setManageSundrySetting(StationsDTO[] dto) throws SQLException,
			Exception;

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
	public void setCCSundryLimt(String[] station_code, int discount)
			throws SQLException, Exception;

	/**
	 * 
	 * @param station_code
	 * @param minFT
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void setMinimumFreight(String[] station_code, int minFT)
			throws SQLException, Exception;

	/**
	 * 
	 * @param station_code
	 * @param minWT
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void setMinimumWeight(String[] station_code, int minWT)
			throws SQLException, Exception;

	/**
	 * 
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public StationsDTO[] getManageCommission() throws SQLException, Exception;

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
			throws SQLException, Exception;

	/**
	 * 
	 * @param station_code
	 * @param cc
	 * @throws SQLException
	 * @throws Exception
	 */
	public void setCCForCommodity(String[] station_code, int cc)
			throws SQLException, Exception;

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
	 * @throws SQLException
	 * @throws Exception
	 */
	public void setStationLanguage(String[] station_code, byte tamil)
			throws SQLException, Exception;

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
	 * @param station_code
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public float getRefundPerArticleForHLC(String customer,String station_code) throws SQLException, Exception;
	
	public void setHolidayEntry(Date holidayDate,String holidayDesc)throws SQLException,Exception;
}
