package hm.akr.admin.drs.handler;

import java.rmi.RemoteException;

import hm.akr.common.BeanUtil;
import hm.akr.dto.StationsDTO;

public class DRSFineCompositeHandler {

	private BeanUtil beanutil = null;

	/**
	 * 
	 * @throws Exception
	 */
	public DRSFineCompositeHandler() throws Exception {
		try {
			beanutil = BeanUtil.getInstance();

		} catch (Exception exception) {
			throw exception;

		}
	}

	/**
	 * 
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public StationsDTO[] getStations() throws RemoteException, Exception {
		if (null != beanutil) {		
		//System.out.println("B" + beanutil);
			return beanutil.getAvailableStations();
		}
		return null;
	}

	/**
	 * 
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void setDRSFineSettings(StationsDTO[] dto) throws RemoteException,
			Exception {
		if(null != beanutil){
			beanutil.setDRSFineSettings(dto);
		}
	}
}
