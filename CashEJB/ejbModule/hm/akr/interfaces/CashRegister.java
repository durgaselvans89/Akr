package hm.akr.interfaces;

import hm.akr.dto.CashDTO;
import hm.akr.dto.CashRegisterDTO;
import hm.akr.dto.LRNumberRangeDTO;
import hm.akr.dto.SMSDTO;
import hm.akr.exceptions.BusinessException;

import java.rmi.RemoteException;
import java.util.Date;

/**
 * Remote interface for CR.
 */
public interface CashRegister {
	/**
	 * Business Method
	 * 
	 * @param dto
	 * @throws RemoteException
	 */
	public boolean generateCR(CashDTO dto) throws RemoteException,
			BusinessException, Exception;

	/**
	 * 
	 * @param stationCode
	 * @return
	 * @throws RemoteException
	 * @throws BusinessException
	 * @throws Exception
	 */
	public CashRegisterDTO[] getLRNosForBilling(String stationCode)
			throws RemoteException, BusinessException, Exception;

	/**
	 * 
	 * @param stationCode
	 * @return
	 * @throws RemoteException
	 * @throws BusinessException
	 * @throws Exception
	 */
	public CashRegisterDTO[] getLRNosForPaid(String stationCode)
			throws RemoteException, BusinessException, Exception;

	/**
	 * 
	 * @param stationCode
	 * @return
	 * @throws RemoteException
	 * @throws BusinessException
	 * @throws Exception
	 */
	public CashRegisterDTO[] getLRNosForTopay(String stationCode)
			throws RemoteException, BusinessException, Exception;

	/**
	 * 
	 * @param dto
	 * @return
	 * @throws RemoteException
	 * @throws BusinessException
	 * @throws Exception
	 */
	public boolean cancelCR(CashDTO dto) throws RemoteException,
			BusinessException, Exception;

	/**
	 * 
	 * @param crno
	 * @return
	 * @throws RemoteException
	 * @throws BusinessException
	 * @throws Exception
	 */
	public CashDTO getCRInformation(String crno, Date date) throws RemoteException,
			BusinessException, Exception;

	/**
	 * 
	 * @param crno
	 * @return
	 * @throws RemoteException
	 * @throws BusinessException
	 * @throws Exception
	 */
	public CashDTO[] getCRInformation(String stationcode, Date fromdate,
			Date todate) throws RemoteException, BusinessException, Exception;

	/**
	 * 
	 * @param dto
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public String[] generateCRList(CashDTO[] dto) throws RemoteException,
			Exception;
	
	public void updateCRList(CashDTO[] dto) throws Exception;

	/**
	 * 
	 * @param crno
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void updatePrintStatus(String[] crno) throws RemoteException,
			Exception;

	/**
	 * 
	 * @param station_code
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public String getLastUsedCR(String station_code) throws RemoteException,
			Exception;

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
	 * @param smsdto
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void storeSMSToFuture(SMSDTO[] smsdto) throws RemoteException,
			Exception;

	public CashDTO[] getCRInformationHistory(String stationcode, Date fromdate,
			Date todate, String dbHistory) throws Exception;
}
