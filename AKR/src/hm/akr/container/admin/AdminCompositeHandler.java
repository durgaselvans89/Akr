package hm.akr.container.admin;

import hm.akr.common.BeanUtil;
import hm.akr.dto.BookedLRDTO;
import hm.akr.dto.DailyDeliveryStatusDTO;
import hm.akr.dto.DeliveryVerificationDTO;
import hm.akr.dto.LRNumberRangeDTO;
import hm.akr.dto.StationsDTO;
import hm.akr.dto.TranshipmentDTO;
import hm.akr.interfaces.Reporting;
import hm.akr.interfaces.Station;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.NamingException;

/**
 * 
 */
public class AdminCompositeHandler {

	BeanUtil beanutil = null;

	Properties properties = null;

	Context context = null;

	/**
	 * Constructor method
	 * 
	 * @throws Exception
	 */
	public AdminCompositeHandler() throws Exception {

		try {
			beanutil = BeanUtil.getInstance();

		} catch (Exception exception) {
			throw exception;
		}
	}

	/**
	 * Method to get Station Bean Reference
	 * 
	 * @return
	 * @throws NamingException
	 * @throws RemoteException
	 * @throws CreateException
	 */
	public Station getStationBean() throws NamingException, RemoteException,
			CreateException {
		Station station = null;

		try {
			station = (Station) context.lookup("StationBean/remote");
		} catch (Exception exception) {

		}
		return station;
	}

	/**
	 * 
	 * @return
	 */
	public String getStationCode() {
		return beanutil.getActingStationCode();
	}

	/**
	 * Method to get All Branch Station
	 * 
	 * @return
	 * @throws Exception
	 */
	public StationsDTO[] getAllBranches() throws Exception {

		StationsDTO[] stations = beanutil.getAvailableStations();
		StationsDTO[] branches = null;
		ArrayList<StationsDTO> list = new ArrayList<StationsDTO>();
		if (null != stations) {
			ArrayList<String> branchNameList = new ArrayList<String>();

			// Get the list of branch codes
			int len = stations.length;
			for (int i = 0; i < len; i++) {
				if (!branchNameList.contains(stations[i].getBranch_code())) {
					branchNameList.add(stations[i].getBranch_code());
				}
			}
			java.util.Collections.sort(branchNameList);
			int size = branchNameList.size();
			branches = new StationsDTO[size];

			// Get the associate name of the branch code
			for (int j = 0; j < size; j++) {
				String branchcode = branchNameList.get(j);

				for (int i = 0; i < len; i++) {
					if (branchcode.equals(stations[i].getId())) {
						// branches[j] = stations[i];
						list.add(stations[i]);
						break;
					}
				}
			}

		}
		int size = list.size();
		branches = list.toArray(new StationsDTO[size]);

		return branches;
	}

	/**
	 * Method to get All Available Stations
	 * 
	 * @return
	 * @throws Exception
	 */
	public StationsDTO[] getAllStations() throws Exception {
		return beanutil.getAvailableStations();
	}

	/**
	 * This method returns all the associated stations for this station.
	 * 
	 * @return StationDTO[] An array instance of StationDTO
	 */
	public StationsDTO[] getAllAssociatedStations() throws Exception {

		String stationCode = beanutil.getActingStationCode();
		String branchCode = getAssociatedBranch(stationCode);
		StationsDTO[] stations = getStations(branchCode);
		return stations;
	}

	/**
	 * Method to get Branch for Given Station
	 * 
	 * @param stationCode
	 * @return
	 */
	private String getAssociatedBranch(String stationCode) throws Exception {
		String branchcode = null;

		StationsDTO[] stations = beanutil.getAvailableStations();
		if (null != stations) {
			int size = stations.length;
			for (int i = 0; i < size; i++) {
				if (stationCode.equals(stations[i].getId())) {
					branchcode = stations[i].getBranch_code();
				}
			}
		}

		return branchcode;
	}

	/**
	 * Method to get All Stations
	 * 
	 * @param branchcode
	 * @return
	 * @throws Exception
	 */
	public StationsDTO[] getStations(String branchcode) throws Exception {
		ArrayList<StationsDTO> stationList = new ArrayList<StationsDTO>();

		StationsDTO[] stations = beanutil.getAvailableStations();

		int size = 0;
		if (null != stations) {
			size = stations.length;
			for (int i = 0; i < size; i++) {
				if (branchcode.equals(stations[i].getBranch_code())) {
					stationList.add(stations[i]);
				}
			}
		}

		size = stationList.size();
		if (size > 0) {
			return stationList.toArray(new StationsDTO[size]);
		}
		return null;
	}

	/**
	 * Method to set Stationary Values
	 * 
	 * @param lrFormat
	 * @return
	 * @throws Exception
	 */
	public boolean setStationaryValues(LRNumberRangeDTO[] lrFormat)
			throws Exception {
		boolean result = false;
		try {

			result = beanutil.setStationaryValues(lrFormat);

		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}

		return result;
	}

	/**
	 * Method to get All stationary Values
	 * 
	 * @return
	 * @throws Exception
	 */
	public LRNumberRangeDTO[] getStationaryValues() throws Exception {
		LRNumberRangeDTO[] lrFormat = null;
		try {

			lrFormat = beanutil.getStationaryValues();

		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}

		return lrFormat;
	}

	/**
	 * Method to Assign Stationary Values for Selected Stations
	 * 
	 * @param stations
	 * @param types
	 * @return
	 * @throws Exception
	 */
	public boolean assignStationary(String[] stations, int[] types)
			throws Exception {
		boolean result = false;
		try {

			result = beanutil.assignStationary(stations, types);

		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}

		return result;
	}

	/**
	 * Method to set Stationary Values for All Station (10% of Assigned Values)
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public boolean assignStationary(LRNumberRangeDTO[] dto) throws Exception {
		if (null != beanutil)
			return beanutil.assignStationary(dto);
		return false;
	}

	/**
	 * Method to Update LRFormat
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean updateLRFormat(LRNumberRangeDTO[] lrFormat) throws Exception {
		boolean result = false;
		try {

			result = beanutil.updateLRFormat(lrFormat);

		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}

		return result;
	}

	/**
	 * Method to LR Range
	 * 
	 * @return
	 * @throws Exception
	 */
	public LRNumberRangeDTO[] getLRRange() throws Exception {

		LRNumberRangeDTO[] lrFormat = null;
		try {

			lrFormat = beanutil.getLRRange(false);

		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}

		return lrFormat;
	}

	/**
	 * Method to set Alarm Values
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
			int billing, int cr) throws Exception {
		boolean result = false;
		try {
			result = beanutil.setAlarmSettings(stations, topay, paid, billing,
					cr);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}

		return result;
	}

	/**
	 * Method to get Stationary Report
	 * 
	 * @return
	 * @throws Exception
	 */
	public LRNumberRangeDTO[] getStationaryReport() throws Exception {
		LRNumberRangeDTO[] lrFormat = null;
		try {

			lrFormat = beanutil.getStationaryReport();

		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}

		return lrFormat;
	}

	/**
	 * 
	 * @param fromDate
	 * @param toDate
	 * @param branchCode
	 * @throws Exception
	 */
	public BookedLRDTO[] getBookedLRs(Date fromDate, Date toDate,
			String branchCode) throws Exception {
		BookedLRDTO[] bookedLRDto = null;
		try {
			bookedLRDto = beanutil.getBookedLRs(fromDate, toDate, branchCode);

		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}

		return bookedLRDto;
	}

	/**
	 * 
	 * @param fromDate
	 * @param toDate
	 * @param branchCode
	 * @throws Exception
	 */
	public TranshipmentDTO[] getTranshipment(int month, int year,
			String branchCode) throws Exception {
		TranshipmentDTO[] dto = null;
		try {
			dto = beanutil.getTranshipment(month, year, branchCode);

		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}

		return dto;
	}

	/**
	 * 
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void setUnitVlaues(double inch, double feet, double cm)
			throws RemoteException, Exception {
		Reporting report = beanutil.getReportingBean();
		if (null != report) {

			report.setUnitVlaues(inch, feet, cm);
		}

	}

	/**
	 * 
	 * @throws RemoteException
	 * @throws Exception
	 */
	public int getLRTrackCount(Date date, Date to_date) throws RemoteException,
			Exception {
		Reporting report = beanutil.getReportingBean();
		int count = 0;
		if (null != report) {
			count = report.getLRTrackCount(date, to_date, BeanUtil.getDbName());
		}
		return count;
	}

	public DeliveryVerificationDTO[] getVerificationReport(String[] details)
			throws RemoteException, Exception {
		Reporting report = beanutil.getReportingBean();
		if (null != report) {
			return report.getVerificationReport(details, BeanUtil.getDbName());
		}
		return null;
	}

	public DailyDeliveryStatusDTO[] getDailyDeliveryStatus(Date date, String branch)
			throws Exception {
		Reporting report = beanutil.getReportingBean();
		if (null != report) {
			return report.getDailyDeliveryStatus(date, branch,BeanUtil.getDbName());
		}
		return null;
	}

	public BookedLRDTO[] getBookedLRsHistory(Date fromDate, Date toDate,
			String branchCode) throws Exception{
		// TODO Auto-generated method stub
		BookedLRDTO[] bookedLRDto = null;
		try {
			bookedLRDto = beanutil.getBookedLRsHistory(fromDate, toDate, branchCode);

		} catch (Exception exception) {
			exception.printStackTrace();
			
		}

		return bookedLRDto;
	}

	public BookedLRDTO[] getBookedLRsUnion(Date fromDate, Date toDate,
			String branchCode) throws Exception{
		// TODO Auto-generated method stub
		BookedLRDTO[] bookedLRDto = null;
		try {
			bookedLRDto = beanutil.getBookedLRsUnion(fromDate, toDate, branchCode);

		} catch (Exception exception) {
			exception.printStackTrace();
			
		}

		return bookedLRDto;
	}
}
