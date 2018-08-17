package hm.akr.admin.commission.handler;

import hm.akr.common.BeanUtil;
import hm.akr.dto.CommissionSummaryDTO;
import hm.akr.dto.CustomerDTO;
import hm.akr.dto.ProfileDTO;
import hm.akr.dto.StationsDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;

public class AgencyCommisionHandler {

	BeanUtil beanutil = null;

	/**
	 * 
	 * @throws Exception
	 */
	public AgencyCommisionHandler() throws Exception {
		beanutil = new BeanUtil();

	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String[] getProfileNames() throws Exception {
		String[] names = null;
		if (null != beanutil) {
			names = beanutil.getProfileNames();
		}
		return names;
	}
	
	

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public ProfileDTO[] getProfileDetails() throws Exception {
		ProfileDTO[] dto = null;
		if (null != beanutil) {
			dto = beanutil.getProfileDetails();
		}
		return dto;
	}

	/**
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public String setProfileDetails(ProfileDTO dto) throws Exception {
		String id = null;
		if (null != beanutil) {
			id = beanutil.createProfile(dto);
		}
		return id;
	}
	
	public String setCCProfileDetails(ProfileDTO dto) throws Exception {
		String id = null;
		if (null != beanutil) {
			id = beanutil.createCCProfile(dto);
		}
		return id;
	}
	
	public String setDCProfileDetails(ProfileDTO dto) throws Exception {
		String id = null;
		if (null != beanutil) {
			id = beanutil.createDCProfile(dto);
		}
		return id;
	}

	public boolean isCCProfileExist(String profileName) throws Exception {
		boolean result = false;
		if (null != beanutil) {
			result = beanutil.isCCProfileExist(profileName);
		}

		return result;
	}
	
	public boolean isDCProfileExist(String profileName) throws Exception {
		boolean result = false;
		if (null != beanutil) {
			result = beanutil.isDCProfileExist(profileName);
		}

		return result;
	}
	
	public void deleteCCProfile(String profileName) throws Exception {

		if (null != beanutil) {
			beanutil.deleteCCProfile(profileName);
		}

	}
	
	public void deleteDCProfile(String profileName) throws Exception {

		if (null != beanutil) {
			beanutil.deleteDCProfile(profileName);
		}

	}
	
	public void setCCProfileCommision(String[] stations, String profilename)
	throws Exception {

		if (null != beanutil) {
			beanutil.setCCProfileCommision(stations, profilename);
		}

	}
	
	public void setDCProfileCommision(String[] stations, String profilename)
	throws Exception {

		if (null != beanutil) {
			beanutil.setDCProfileCommision(stations, profilename);
		}

	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String[] getCCProfileNames() throws Exception {
		String[] names = null;
		if (null != beanutil) {
			names = beanutil.getCCProfileNames();
		}
		return names;
	}
	
	
	public String[] getDCProfileNames() throws Exception {
		String[] names = null;
		if (null != beanutil) {
			names = beanutil.getDCProfileNames();
		}
		return names;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public ProfileDTO[] getCCProfileDetails() throws Exception {
		ProfileDTO[] dto = null;
		if (null != beanutil) {
			dto = beanutil.getCCProfileDetails();
		}
		return dto;
	}

	public ProfileDTO[] getDCProfileDetails() throws Exception {
		ProfileDTO[] dto = null;
		if (null != beanutil) {
			dto = beanutil.getDCProfileDetails();
		}
		return dto;
	}
	
	/**
	 * 
	 * @param profileName
	 * @return
	 * @throws Exception
	 */
	public boolean isProfileExist(String profileName) throws Exception {
		boolean result = false;
		if (null != beanutil) {
			result = beanutil.isProfileExist(profileName);
		}

		return result;
	}

	/**
	 * 
	 * @param profileId
	 * @throws Exception
	 */
	public void deleteProfile(String profileId) throws Exception {

		if (null != beanutil) {
			beanutil.deleteProfile(profileId);
		}

	}

	/**
	 * 
	 * @param stations
	 * @param profilename
	 * @throws Exception
	 */
	public void setBookingCommision(String[] stations, String profilename)
			throws Exception {

		if (null != beanutil) {
			beanutil.setBookingCommision(stations, profilename);
		}

	}

	public void setBookingCommisionEffDate(String[] stations,
			String profilename, Date date) throws Exception {

		if (null != beanutil) {
			beanutil.setBookingCommisionEffDate(stations, profilename, date);
		}

	}

	/**
	 * 
	 * @param stations
	 * @param delivery
	 * @throws Exception
	 */
	public void setDeliveryCommision(String[] stations, float delivery)
			throws Exception {

		if (null != beanutil) {
			beanutil.setDeliveryCommision(stations, delivery);
		}

	}

	/**
	 * 
	 * @param stationCode
	 * @param description
	 * @param installamount
	 * @throws Exception
	 */
	public void createRefundRegister(String[] stationCode, String contact,
			String description, HashMap<Integer, Float> installamount)
			throws Exception {
		if (null != beanutil) {
			beanutil.createRefundRegister(stationCode, contact, description,
					installamount);
		}
	}

	/**
	 * 
	 * @param stationCode
	 * @param description
	 * @param installamount
	 * @throws Exception
	 */
	public void createRecoveryRegister(String[] stationCode, String contact,
			String description, HashMap<Integer, Float> installamount)
			throws Exception {
		if (null != beanutil) {
			beanutil.createRecoveryRegister(stationCode, contact, description,
					installamount);
		}
	}

	public void setCCCommissionList(String[] stations, float parseFloat)
			throws Exception {

		if (null != beanutil) {
			beanutil.setCCCommissionList(stations, parseFloat);
		}
	}

	public CommissionSummaryDTO[] getAdminCommission(String branch_code,
			int day, int month) throws Exception {
		CommissionSummaryDTO[] dto = null;
		if (null != beanutil) {
			dto = beanutil.getAdminCommission(branch_code, day, month);
		}
		return dto;
	}

	/**
	 * 
	 * @param stations
	 * @param parseFloat
	 * @throws Exception
	 */
	public void setCCRefunder(String[] stations, int consider, int refund,
			int ccLimit) throws Exception {

		if (null != beanutil) {
			beanutil.setCCRefunder(stations, consider, refund, ccLimit);
		}
	}

	public String getServerDate() throws Exception {
		String date = null;
		if (null != beanutil) {
			date = BeanUtil.getServerDate();
		}
		return date;
	}

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

	public StationsDTO[] getAllBranches() throws Exception {

		StationsDTO[] stations = beanutil.getAvailableStations();
		StationsDTO[] branches = null;

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
	 * @return
	 * @throws Exception
	 */
	public StationsDTO[] getManageCommission() throws Exception {
		StationsDTO[] dto = null;
		if (null != beanutil) {
			dto = beanutil.getManageCommission();
		}
		return dto;
	}

	public void updateManageCommission(StationsDTO[] dto) throws Exception {
		if (null != beanutil) {
			beanutil.updateManageCommission(dto);
		}
	}

	public void setCCForSpecialSundry(String[] stationcodes, int cc)
			throws Exception {
		if (null != beanutil) {
			beanutil.setCCForSpecialSundry(stationcodes, cc);
		}
	}

	/**
	 * Method to get Customers List
	 * @return
	 * @throws Exception
	 */
	public CustomerDTO[] getCustomers() throws Exception {
		CustomerDTO[] customers = null;
		if (beanutil != null) {
			customers = beanutil.getCustomers();
		}
		return customers;
	}

	public void setCCForCommodity(String[] stationcodes, int cc)
			throws Exception {
		if (null != beanutil) {
			beanutil.setCCForCommodity(stationcodes, cc);
		}
	}

	public void setCCForHLC(String[] stationcodes, String customer,
			float ccArticle, float refundArticle) throws Exception {
		if (null != beanutil) {
			beanutil.setCCForHLC(stationcodes, customer, ccArticle,
					refundArticle);
		}
	}

	public void preparePrint(ArrayList<CommissionSummaryDTO> rowDTO,
			String viewCommissionJrxml, String[] value) {
		// TODO Auto-generated method stub
		
		if (null != beanutil) {
			try {

				HashMap<String, String> hashMap = new HashMap<String, String>();
				//HashMap<String, String> hashMap = null;
				for(int i = 0; i < value.length; i++){
					hashMap.put("val"+i, value[i]); 
					
				}
				
				beanutil.printData(rowDTO, viewCommissionJrxml, hashMap);
				
			} catch (JRException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	public void printReportExcel(ArrayList list, String fileName,
			String jrxmlFile, String[] value) {
		// TODO Auto-generated method stub
		if (null != beanutil) {
			try {
				HashMap<String, String> hashMap = new HashMap<String, String>();
				
				for(int i = 0; i < value.length; i++){
					hashMap.put("val"+i, value[i]);
					
					
				}
				beanutil.exportToReportExcel(list, jrxmlFile, hashMap, "ViewCommission");
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
