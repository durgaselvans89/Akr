package hm.akr.admin.holidays.handler;

import java.rmi.RemoteException;
import java.util.Date;

import hm.akr.common.BeanUtil;
import hm.akr.dto.ServiceTaxDTO;

public class HolidaysSettingsCompositeHandler {

	private BeanUtil beanUtil;

	/**
	 * 
	 */
	public HolidaysSettingsCompositeHandler() {
		try {
			beanUtil = BeanUtil.getInstance();
		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}

	/**
	 * 
	 * @param date
	 * @param desc
	 * @return
	 */
	public void setHolidays(Date date, String desc)
			throws RemoteException, Exception {
		if (null != beanUtil) {
		 beanUtil.setHolidaySettings(date, desc);
		} else
			throw new Exception();

	}

	public ServiceTaxDTO[] getHolidays(String year) {
		// TODO Auto-generated method stub
		ServiceTaxDTO[] holiday = null;
		try {
			if (null != beanUtil) {
				  holiday = beanUtil.getHolidays(year);
				} 
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return holiday;
		
	}

}
