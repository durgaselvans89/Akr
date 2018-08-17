/**
 * 
 */
package hm.akr.container.commission;

import hm.akr.common.BeanUtil;
import hm.akr.dto.BookingCommissionDTO;
import hm.akr.dto.CartageCommissionDTO;
import hm.akr.dto.CommissionSummaryDTO;
import hm.akr.dto.DDDetailsDTO;
import hm.akr.dto.DeliveryCommissionDTO;
import hm.akr.dto.RefundRecoveryDTO;

import java.util.Date;
import java.util.HashMap;
import java.util.ArrayList;

import net.sf.jasperreports.engine.JRException;


/**
 * @author user
 *
 */
public class CommissionCompositeHandler {

	BeanUtil beanutil = null;
	
	/**
	 * 
	 */
	public CommissionCompositeHandler() throws Exception {		
			beanutil = BeanUtil.getInstance();
	}
	
	/**
	 * 
	 * @param Date
	 * @return
	 * @throws Exception
	 */
	public BookingCommissionDTO[] getBookingCommissionList(Date date) throws Exception {
		BookingCommissionDTO[] bookingCommDTO = null;
		if (null != beanutil) {
			bookingCommDTO = beanutil.getBookingCommissionList(date);
		}
		return bookingCommDTO;
	}
	
	public BookingCommissionDTO[] getBookingCommissionListHistory(Date date) throws Exception {
		BookingCommissionDTO[] bookingCommDTO = null;
		if (null != beanutil) {
			bookingCommDTO = beanutil.getBookingCommissionListHistory(date);
		}
		return bookingCommDTO;
	}
	
	/**
	 * 
	 * @param Date
	 * @return
	 * @throws Exception
	 */
	public DeliveryCommissionDTO[] getDeliveryCommissionList(Date date) throws Exception {
		DeliveryCommissionDTO[] deliveryCommDTO = null;
		if (null != beanutil) {
			deliveryCommDTO = beanutil.getDeliveryCommissionList(date);
		}
		return deliveryCommDTO;
	}
	
	public DeliveryCommissionDTO[] getDeliveryCommissionListHistory(Date date) throws Exception {
		DeliveryCommissionDTO[] deliveryCommDTO = null;
		if (null != beanutil) {
			deliveryCommDTO = beanutil.getDeliveryCommissionListHistory(date);
		}
		return deliveryCommDTO;
	}
	
	/**
	 * 
	 * @param Date
	 * @return
	 * @throws Exception
	 */
	public CartageCommissionDTO[] getCartageCommissionList(Date date) throws Exception {
		CartageCommissionDTO[] cartageCommDTO = null;
		if (null != beanutil) {
			cartageCommDTO = beanutil.getCartageCommissionList(date);
		}
		return cartageCommDTO;
	}
	
	public CartageCommissionDTO[] getCartageCommissionListHistory(Date date) throws Exception {
		CartageCommissionDTO[] cartageCommDTO = null;
		if (null != beanutil) {
			cartageCommDTO = beanutil.getCartageCommissionListHistory(date);
		}
		return cartageCommDTO;
	}
	
	/**
	 * 
	 * @param Date
	 * @return
	 * @throws Exception
	 */
	public RefundRecoveryDTO[] getRecoveryList(Date date ) throws Exception {
		RefundRecoveryDTO[] recoveryDTO = null;
		if (null != beanutil) {
			recoveryDTO = beanutil.getRecoveryList(date);
		}
		return recoveryDTO;
	}
	
	
	/**
	 * 
	 * @param Date
	 * @return
	 * @throws Exception
	 */
	public RefundRecoveryDTO[] getRefundList(Date date) throws Exception {
		RefundRecoveryDTO[] refundDTO = null;
		if (null != beanutil) {
			refundDTO = beanutil.getRefundList(date);
		}
		return refundDTO;
	}
	
	/**
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public DDDetailsDTO[] getDDDetails(Date date) throws Exception {
		DDDetailsDTO[] ddDetailsDTO = null;
		if (null != beanutil) {
			ddDetailsDTO = beanutil.getDDDetails(date);
		}
		return ddDetailsDTO;
	}
	
	public DDDetailsDTO[] getDDDetailsHistory(Date date) throws Exception {
		DDDetailsDTO[] ddDetailsDTO = null;
		if (null != beanutil) {
			ddDetailsDTO = beanutil.getDDDetailsHistory(date);
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
	public CommissionSummaryDTO getCommisionSummary(Date date) throws Exception {
		CommissionSummaryDTO summaryDTO = null;
		if (null != beanutil) {
			summaryDTO = beanutil.getCommisionSummary(date);
		}
		return summaryDTO;
	}
	
	public CommissionSummaryDTO getCommisionSummaryHistory(Date date) throws Exception {
		CommissionSummaryDTO summaryDTO = null;
		if (null != beanutil) {
			summaryDTO = beanutil.getCommisionSummaryHistory(date);
		}
		return summaryDTO;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getPassword() throws Exception {		
		if (null != beanutil) {
			return beanutil.getPassword(beanutil.getActingStationCode());
		}
		return null;		
	}
	
	/**
	 * 
	 * @param pwd
	 * @throws Exception
	 */
	public void setPassword(String pwd) throws Exception {		
		if (null != beanutil) {
			beanutil.setPassword(beanutil.getActingStationCode(), pwd);
		}				
	}
	
	public void printReportExcel(ArrayList list,
			String fileName, String jrxmlFile, String value) {
		// TODO Auto-generated method stub
		if (null != beanutil) {
			try {
				
				//HashMap<String, String> hashMap = null;
				HashMap hashMap = new HashMap();
				hashMap.put("selectDate", value);
				
				
				beanutil
						.exportToReportExcel(list, jrxmlFile, hashMap, fileName);
			
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
		}
			
	}

	public void printReportPDF(ArrayList list,
			String fileName, String jrxmlFile, String value) {
		// TODO Auto-generated method stub
		if (null != beanutil) {
			try {
				
				//HashMap<String, String> hashMap = null;
				HashMap hashMap = new HashMap();
				hashMap.put("selectDate", value);
				
				beanutil
						.printPDF(list, jrxmlFile, hashMap, fileName);
			} catch (JRException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void preparePrint(ArrayList list,
			String fileName, String jrxmlFile, String value) {
		// TODO Auto-generated method stub
		if (null != beanutil) {
			try {

				//HashMap<String, String> hashMap = new HashMap<String, String>();
				//HashMap<String, String> hashMap = null;
				HashMap hashMap = new HashMap();
				hashMap.put("selectDate", value);
				
				beanutil.printData(list,jrxmlFile, hashMap);
				
			} catch (JRException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


}
