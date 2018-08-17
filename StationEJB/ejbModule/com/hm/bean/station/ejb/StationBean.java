package com.hm.bean.station.ejb;

import hm.akr.dto.CCProfileDTO;
import hm.akr.dto.CardRateProfileDTO;
import hm.akr.dto.DCProfileDTO;
import hm.akr.dto.DistanceListDTO;
import hm.akr.dto.LRNumberRangeDTO;
import hm.akr.dto.StationsDTO;
import hm.akr.interfaces.Station;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Date;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.hm.dao.IStationDAO;
import com.hm.dao.StationDAO;

@Stateless
@Remote(Station.class)
@TransactionManagement(TransactionManagementType.BEAN)
/**
 * 
 */
public class StationBean implements Station {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StationBean() {
		super();
	}

	/**
	 * 
	 * @param branch
	 * @param dto
	 * @throws Exception
	 */
	public boolean addStation(String branch, StationsDTO dto) throws Exception {

		boolean result = false;

		IStationDAO stn = new StationDAO();

		try {

			result = stn.addStation(branch, dto);

		} catch (Exception exception) {

			throw exception;
		}
		return result;

	}

	/**
	 * 
	 * @param id
	 * @throws Exception
	 */
	public boolean deleteStation(int id) throws Exception {

		boolean result = false;

		IStationDAO stn = new StationDAO();

		try {

			result = stn.deleteStation(id);

		} catch (Exception exception) {

			throw exception;
		}
		return result;
	}

	/**
	 * 
	 * @param region
	 * @param dto
	 * @throws Exception
	 */
	public boolean addBranch(String region, StationsDTO dto) throws Exception {

		boolean result = false;

		IStationDAO stn = new StationDAO();

		try {

			result = stn.addBranch(region, dto);

		} catch (Exception exception) {

			throw exception;
		}
		return result;

	}

	/**
	 * 
	 * @param id
	 * @throws Exception
	 */
	public boolean deleteBranch(int id) throws Exception {

		boolean result = false;

		IStationDAO stn = new StationDAO();

		try {

			result = stn.deleteBranch(id);

		} catch (Exception exception) {

			throw exception;
		}
		return result;
	}

	/**
	 * 
	 * @param dto
	 * @throws Exception
	 */
	public boolean addRegion(StationsDTO dto) throws Exception {

		boolean result = false;

		IStationDAO stn = new StationDAO();

		try {

			result = stn.addRegion(dto);

		} catch (Exception exception) {

			throw exception;
		}

		return result;

	}

	/**
	 * 
	 * @param id
	 */
	public boolean deleteRegion(int id) throws Exception {

		boolean result = false;

		IStationDAO stn = new StationDAO();

		try {

			result = stn.deleteRegion(id);

		} catch (Exception exception) {

			throw exception;
		}

		return result;
	}

	/**
	 * 
	 * @return
	 */
	public StationsDTO[] getAllStations() throws Exception {

		IStationDAO station = new StationDAO();

		StationsDTO[] list = null;

		try {

			list = station.getAllStations();

		} catch (Exception exception) {

			throw exception;
		}

		return list;

	}

	/**
	 * 
	 */
	public StationsDTO[] getSelectedStations(String stationCode)
			throws Exception {
		IStationDAO station = new StationDAO();

		StationsDTO[] list = null;

		try {

			list = station.getSelectedStations(stationCode);

		} catch (Exception exception) {

			throw exception;
		}

		return list;
	}

	@Override
	/**
	 * 
	 */
	public StationsDTO[] getTRs() throws Exception {

		IStationDAO station = new StationDAO();

		StationsDTO[] list = null;

		try {

			list = station.getTRs();

		} catch (Exception exception) {

			throw exception;
		}

		return list;
	}

	/**
	 * 
	 * @param lrFormat
	 * @return
	 * @throws Exception
	 */
	public boolean setStationaryValues(LRNumberRangeDTO[] lrFormat)
			throws Exception {

		IStationDAO station = new StationDAO();

		boolean result = false;

		try {

			result = station.setStationaryValues(lrFormat);

		} catch (Exception exception) {

			throw exception;
		}

		return result;
	}

	/**
	 * 
	 */
	public LRNumberRangeDTO[] getStationaryValues() throws Exception {
		IStationDAO station = new StationDAO();
		LRNumberRangeDTO[] lrFormat = null;

		try {
			lrFormat = station.getStationaryValues();

		} catch (Exception exception) {
			throw exception;
		}

		return lrFormat;
	}

	/**
	 * 
	 * @param stations
	 * @param types
	 * @return
	 * @throws Exception
	 */
	public boolean assignStationary(String[] stations, int[] types)
			throws Exception {
		IStationDAO station = new StationDAO();
		boolean result = false;

		try {

			result = station.assignStationary(stations, types);

		} catch (Exception exception) {

			throw exception;
		}

		return result;
	}

	/**
	 * 
	 */
	public boolean assignStationary(LRNumberRangeDTO[] stationary)
			throws Exception {
		IStationDAO station = new StationDAO();
		boolean result = false;

		try {

			result = station.assignStationary(stationary);

		} catch (Exception exception) {

			throw exception;
		}

		return result;
	}

	/**
	 * 
	 * @param lrFormat
	 * @return
	 * @throws Exception
	 */
	public boolean updateLRFormat(LRNumberRangeDTO[] lrFormat) throws Exception {

		IStationDAO station = new StationDAO();

		boolean result = false;

		try {

			result = station.updateLRFormat(lrFormat);

		} catch (Exception exception) {

			throw exception;
		}

		return result;
	}

	/**
	 * 
	 */
	public LRNumberRangeDTO[] getLRRange(String stationCode) throws Exception {
		IStationDAO station = new StationDAO();
		LRNumberRangeDTO[] lrFormat = null;

		try {
			lrFormat = station.getLRRange(stationCode);

		} catch (Exception exception) {
			throw exception;
		}

		return lrFormat;
	}

	/**
	 * Method to Set Alarm Settings
	 */
	public boolean setAlarmSettings(String[] stations, int topay, int paid,
			int billing, int cr) throws Exception {
		boolean result = false;
		IStationDAO station = new StationDAO();

		try {

			result = station.setAlarmSettings(stations, topay, paid, billing,
					cr);

		} catch (Exception exception) {

			throw exception;
		}

		return result;
	}

	/**
	 * Method to Get Stationary Report
	 */
	public LRNumberRangeDTO[] getStationaryReport() throws Exception {
		IStationDAO station = new StationDAO();
		LRNumberRangeDTO[] lrFormat = null;

		try {
			lrFormat = station.getStationaryReport();

		} catch (Exception exception) {
			throw exception;
		}

		return lrFormat;
	}

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
			throws RemoteException, Exception {
		IStationDAO station = new StationDAO();
		try {
			station.setBFIncrement(station_code, bf_increment);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
	}

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
			throws RemoteException, Exception {
		IStationDAO station = new StationDAO();
		try {
			station.setBFDecrement(station_code, discount);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
	}

	@Override
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
			throws RemoteException, Exception {
		DistanceListDTO[] distanceList = null;

		try {
			// Call the dao to get the distance list for the given station code
			IStationDAO station = new StationDAO();
			distanceList = station.calculateDistance(stationCode);
		} catch (Exception exception) {
			throw exception;
		}

		return distanceList;
	}

	@Override
	/**
	 * Method to set the distance list.
	 * 
	 * @param stationCode
	 * @param destDistance
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void setDistanceList(String stationCode, DistanceListDTO[] distance)
			throws RemoteException, Exception {
		try {
			// Call the dao to set the distance list for the given station code
			IStationDAO station = new StationDAO();
			station.setDistanceList(stationCode, distance);
		} catch (Exception exception) {
			throw exception;
		}
	}

	/**
	 * 
	 */
	public StationsDTO[] getBFIncrementAndDiscount() throws RemoteException,
			Exception {
		IStationDAO station = new StationDAO();
		StationsDTO[] list = null;

		try {
			list = station.getAllBFIncrementAndDiscount();
		} catch (Exception exception) {
			throw exception;
		}

		return list;
	}

	/**
	 * Method to set the list of manage sundry using the RegularSundryDTO This
	 * method is called for insertion/updation of manage sundry data.
	 * 
	 * @param dto
	 *            RegularSundryDTO list
	 * 
	 * @throws SQLException
	 * @throws Exception
	 */
	public void setManageSundrySetting(StationsDTO[] dto)
			throws RemoteException, Exception {

		try {
			IStationDAO manage = new StationDAO();
			manage.setManageSundrySetting(dto);

		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
	}

	/**
	 * 
	 */
	public void setDRSFine(StationsDTO[] dto) throws RemoteException, Exception {

		try {
			IStationDAO manage = new StationDAO();
			manage.setDRSFine(dto);

		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}

	}

	/**
	 * 
	 * @param station_code
	 * @param ccvalue
	 * @throws SQLException
	 * @throws Exception
	 */
	public void setCCSundryLimit(String[] station_code, int ccvalue)
			throws SQLException, Exception {
		IStationDAO station = new StationDAO();
		try {
			station.setCCSundryLimt(station_code, ccvalue);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}

	}

	/**
	 * 
	 * @param station_code
	 * @param minFT
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void setMinimumFreight(String[] station_code, int minFT)
			throws RemoteException, Exception {
		IStationDAO station = new StationDAO();
		try {
			station.setMinimumFreight(station_code, minFT);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
	}

	/**
	 * 
	 */
	public void setMinimumWeight(String[] station_code, int minWT)
			throws RemoteException, Exception {
		IStationDAO station = new StationDAO();
		try {
			station.setMinimumWeight(station_code, minWT);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
	}

	/**
	 * 
	 */
	public StationsDTO[] getManageCommission() throws RemoteException,
			Exception {
		IStationDAO station = new StationDAO();
		StationsDTO[] dto = null;
		try {
			dto = station.getManageCommission();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}

		return dto;
	}

	/**
	 * 
	 */
	public void updateManageCommisssion(StationsDTO[] dto)
			throws RemoteException, Exception {
		IStationDAO station = new StationDAO();
		try {
			station.updateManageCommisssion(dto);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
	}

	public void setCCForSpecialSundry(String[] station_code, int cc)
			throws RemoteException, Exception {
		IStationDAO station = new StationDAO();
		try {
			station.setCCForSpecialSundry(station_code, cc);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
	}

	public void setCCForCommodity(String[] station_code, int cc)
			throws RemoteException, Exception {
		IStationDAO station = new StationDAO();
		try {
			station.setCCForCommodity(station_code, cc);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
	}

	/**
	 * 
	 */
	public void setCCForHLC(String[] station_code, String customer,
			float ccArticle, float refundArticle) throws RemoteException,
			Exception {
		IStationDAO station = new StationDAO();
		try {
			station.setCCForHLC(station_code, customer, ccArticle,
					refundArticle);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
	}

	@Override
	public void setStationLanguage(String[] station_code, byte tamil)
			throws RemoteException, Exception {
		IStationDAO station = new StationDAO();
		try {
			station.setStationLanguage(station_code, tamil);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
	}

	/**
	 * 
	 * @param station_code
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public CardRateProfileDTO[] getCardRateProfile(String station_code)
			throws RemoteException, Exception {

		IStationDAO station = new StationDAO();
		CardRateProfileDTO[] dto = null;
		try {
			dto = station.getCardRateProfile(station_code);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
		return dto;

	}

	/**
	 * 
	 */
	public CCProfileDTO[] getCCDetails(String station_code)
			throws SQLException, Exception {

		IStationDAO station = new StationDAO();
		CCProfileDTO[] dto = null;
		try {
			dto = station.getCCDetails(station_code);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
		return dto;

	}

	/**
	 * 
	 */
	public DCProfileDTO[] getDCDetails(String station_code)
			throws SQLException, Exception {

		IStationDAO station = new StationDAO();
		DCProfileDTO[] dto = null;
		try {
			dto = station.getDCDetails(station_code);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
		return dto;

	}

	@Override
	public float getRefundPerArticleForHLC(String customer,String station_code)
			throws SQLException, Exception {

		IStationDAO station = new StationDAO();
		float refund = 0;
		try {
			refund = station.getRefundPerArticleForHLC(customer,station_code);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
		return refund;

	}

	public void setHolidayEntry(Date holidayDate, String holidayDesc)
	throws RemoteException, Exception {
		try {
			IStationDAO manage = new StationDAO();
			manage.setHolidayEntry(holidayDate, holidayDesc);

		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
	}
	
}
