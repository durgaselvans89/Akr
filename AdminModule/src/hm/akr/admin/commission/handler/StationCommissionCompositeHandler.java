/**
 * 
 */
package hm.akr.admin.commission.handler;

import hm.akr.common.BeanUtil;
import hm.akr.dto.BookingCommissionDTO;
import hm.akr.dto.CartageCommissionDTO;
import hm.akr.dto.CommissionSummaryDTO;
import hm.akr.dto.DDDetailsDTO;
import hm.akr.dto.DeliveryCommissionDTO;
import hm.akr.dto.RefundRecoveryDTO;
import hm.akr.dto.StationsDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import net.sf.jasperreports.engine.JRException;

/**
 * @author user
 *
 */
public class StationCommissionCompositeHandler {

	BeanUtil beanutil = null;
	
	/**
	 * 
	 */
	public StationCommissionCompositeHandler() throws Exception {		
			beanutil = BeanUtil.getInstance();
	}
	
	/**
	 * 
	 * @param Date
	 * @return
	 * @throws Exception
	 */
	public BookingCommissionDTO[] getBookingCommissionList(Date date, String station) throws Exception {
		BookingCommissionDTO[] bookingCommDTO = null;
		if (null != beanutil) {
			bookingCommDTO = beanutil.getBookingCommissionList(date, station);
		}
		return bookingCommDTO;
	}
	
	public BookingCommissionDTO[] getBookingCommissionListHistory(Date date, String station) throws Exception {
		BookingCommissionDTO[] bookingCommDTO = null;
		if (null != beanutil) {
			bookingCommDTO = beanutil.getBookingCommissionListHistory(date, station);
		}
		return bookingCommDTO;
	}
	
	/**
	 * 
	 * @param Date
	 * @return
	 * @throws Exception
	 */
	
	
	public DeliveryCommissionDTO[] getDeliveryCommissionListHistory(Date date, String station) throws Exception {
		DeliveryCommissionDTO[] deliveryCommDTO = null;
		if (null != beanutil) {
			deliveryCommDTO = beanutil.getDeliveryCommissionListHistory(date, station);
		}
		return deliveryCommDTO;
	}
	
	/**
	 * 
	 * @param Date
	 * @return
	 * @throws Exception
	 */
	public CartageCommissionDTO[] getCartageCommissionList(Date date, String station) throws Exception {
		CartageCommissionDTO[] cartageCommDTO = null;
		if (null != beanutil) {
			cartageCommDTO = beanutil.getCartageCommissionList(date, station);
		}
		return cartageCommDTO;
	}
	
	public CartageCommissionDTO[] getCartageCommissionListHistory(Date date, String station) throws Exception {
		CartageCommissionDTO[] cartageCommDTO = null;
		if (null != beanutil) {
			cartageCommDTO = beanutil.getCartageCommissionListHistory(date, station);
		}
		return cartageCommDTO;
	}
	/**
	 * 
	 * @param Date
	 * @return
	 * @throws Exception
	 */
	public RefundRecoveryDTO[] getRecoveryList(Date date,String station ) throws Exception {
		RefundRecoveryDTO[] recoveryDTO = null;
		if (null != beanutil) {
			recoveryDTO = beanutil.getRecoveryList(date, station);
		}
		return recoveryDTO;
	}
	
	/**
	 * 
	 * @param Date
	 * @return
	 * @throws Exception
	 */
	public RefundRecoveryDTO[] getRefundList(Date date, String station) throws Exception {
		RefundRecoveryDTO[] refundDTO = null;
		if (null != beanutil) {
			refundDTO = beanutil.getRefundList(date, station);
		}
		return refundDTO;
	}
	
	/**
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public DDDetailsDTO[] getDDDetails(Date date, String station) throws Exception {
		DDDetailsDTO[] ddDetailsDTO = null;
		if (null != beanutil) {
			ddDetailsDTO = beanutil.getDDDetails(date, station);
		}
		return ddDetailsDTO;
	}
	
	public DDDetailsDTO[] getDDDetailsHistory(Date date, String station) throws Exception {
		DDDetailsDTO[] ddDetailsDTO = null;
		if (null != beanutil) {
			ddDetailsDTO = beanutil.getDDDetailsHistory(date, station);
		}
		return ddDetailsDTO;
	}
	/**
	 * 
	 * @param date
	 * @param refresh
	 * @return
	 * @throws Exception
	 */
	public CommissionSummaryDTO getCommisionSummary(Date date, String station) throws Exception {
		CommissionSummaryDTO summaryDTO = null;
		if (null != beanutil) {
			summaryDTO = beanutil.getCommisionSummary(date, station);
		}
		return summaryDTO;
	}
	
	public CommissionSummaryDTO getCommisionSummaryHistory(Date date, String station) throws Exception {
		CommissionSummaryDTO summaryDTO = null;
		if (null != beanutil) {
			summaryDTO = beanutil.getCommisionSummaryHistory(date, station);
		}
		return summaryDTO;
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

	public void printReportExcel(ArrayList list,
			String fileName, String jrxmlFile, String[] value) {
		// TODO Auto-generated method stub
		if (null != beanutil) {
			try {
				
				//HashMap<String, String> hashMap = null;
				HashMap hashMap = new HashMap();
				hashMap.put("selectBranch", value[0]);
				hashMap.put("selectStation", value[1]);
				hashMap.put("selectDate", value[2]);
				
				
				beanutil
						.exportToReportExcel(list, jrxmlFile, hashMap, fileName);
			
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
		}
			
	}

	public void printReportPDF(ArrayList list,
			String fileName, String jrxmlFile, String[] value) {
		// TODO Auto-generated method stub
		if (null != beanutil) {
			try {
				
				//HashMap<String, String> hashMap = null;
				HashMap hashMap = new HashMap();
				hashMap.put("selectBranch", value[0]);
				hashMap.put("selectStation", value[1]);
				hashMap.put("selectDate", value[2]);
				
				beanutil.exportToReportPDF(list, jrxmlFile, hashMap, fileName);
			} catch (JRException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void preparePrint(ArrayList list,
			String fileName, String jrxmlFile, String[] value) {
		// TODO Auto-generated method stub
		if (null != beanutil) {
			try {

				//HashMap<String, String> hashMap = new HashMap<String, String>();
				//HashMap<String, String> hashMap = null;
				HashMap hashMap = new HashMap();
				hashMap.put("selectBranch", value[0]);
				hashMap.put("selectStation", value[1]);
				hashMap.put("selectDate", value[2]);
				
				beanutil.printData(list,jrxmlFile, hashMap);
				
			} catch (JRException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public DeliveryCommissionDTO[] getDeliveryCommissionList(Date date,
			String station) throws Exception {
		// TODO Auto-generated method stub
		DeliveryCommissionDTO[] deliveryCommDTO = null;
		if (null != beanutil) {
			deliveryCommDTO = beanutil.getDeliveryCommissionList(date, station);
		}
		return deliveryCommDTO;
	}

	
}
