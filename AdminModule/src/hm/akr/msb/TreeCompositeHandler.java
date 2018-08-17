package hm.akr.msb;

import hm.akr.common.BeanUtil;
import hm.akr.dto.CustomerDTO;
import hm.akr.dto.StationsDTO;
import hm.akr.interfaces.Station;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Properties;

import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.NamingException;

/**
 * 
 */
public class TreeCompositeHandler {

	BeanUtil beanutil = null;

	Properties properties = null;

	Context context = null;

	/**
	 * Constructor method
	 * @throws Exception
	 */
	public TreeCompositeHandler() throws Exception {

		try {
			beanutil = BeanUtil.getInstance();

		} catch (Exception exception) {
			throw exception;
		}
	}

	/**
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
	 * 
	 */
	public StationsDTO[] getAllBranches() throws Exception {

		StationsDTO[] stations = beanutil.getAvailableStations();
		StationsDTO[] branches = null;

		if (null != stations) {
			ArrayList<String> branchNameList = new ArrayList<String>();

			//Get the list of branch codes
			int len = stations.length;
			for (int i = 0; i < len; i++) {
				if (!branchNameList.contains(stations[i].getBranch_code())) {
					branchNameList.add(stations[i].getBranch_code());
				}
			}

			java.util.Collections.sort(branchNameList);
			int size = branchNameList.size();
			branches = new StationsDTO[size];

			//Get the associate name of the branch code
			for (int j = 0; j < size; j++) {
				String branchcode = branchNameList.get(j);
				for (int i = 0; i < len; i++) {
					if (branchcode.equals(stations[i].getId())) {
						
						branches[j] = stations[i];
						break;
						
					}
				}
			}
			

		}

		return branches;
	}

	/**
	 * 
	 */
	public StationsDTO[] getAllStations() throws Exception {
		return beanutil.getAvailableStations();
	}

	
	
	/**
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
	 * 
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
	 * 
	 * @param stationcode
	 * @return
	 * @throws Exception 
	 */
	public StationsDTO[] getAllAssociatedStations(String stationcode) throws Exception {
		
		//String stationCode = beanutil.getActingStationCode();
		String branchCode = getAssociatedBranch(stationcode);
		StationsDTO[] stations = getStations(branchCode);
		return stations;
	}

	public CustomerDTO[] getCustomerDetails() throws Exception {

		return beanutil.getAdminCustomerDetails();
	}

}
