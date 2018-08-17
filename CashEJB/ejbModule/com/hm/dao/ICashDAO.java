/**
 * @author hm
 * @version 1.0
 */
package com.hm.dao;

import hm.akr.dto.CashDTO;
import hm.akr.dto.CashRegisterDTO;
import hm.akr.dto.LRNumberRangeDTO;
import hm.akr.dto.SMSDTO;
import hm.akr.exceptions.BusinessException;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Date;

/**
 * 
 *
 */
public interface ICashDAO {

	/**
	 * 
	 * @param dto
	 */
	public boolean generateCR(CashDTO dto) throws BusinessException, SQLException, Exception;

	/**
	 * 
	 * @param stationCode
	 * @return
	 * @throws Exception
	 */
	public CashRegisterDTO[] getLRNosForBilling(String stationCode) throws Exception;

	/**
	 * 
	 * @param stationCode
	 * @return
	 * @throws Exception
	 */
	public CashRegisterDTO[] getLRNosForPaid(String stationCode) throws Exception;

	/**
	 * 
	 * @param stationCode
	 * @return
	 * @throws Exception
	 */
	public CashRegisterDTO[] getLRNosForTopay(String stationCode) throws Exception;

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean cancelCR(CashDTO dto) throws Exception;

	/**
	 * 
	 * @param crno
	 * @return
	 * @throws Exception
	 */
	public CashDTO getCRInformation(String crno, Date date) throws Exception;
	
	/**
	 * 
	 * @param crno
	 * @return
	 * @throws Exception
	 */
	public CashDTO[] getCRInformation(String stationcode,Date fromdate,Date todate) throws Exception;

	public void updateCRList(CashDTO[] dto) throws Exception;
		
	
	/**
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public String[] generateCRList(CashDTO[] dto) throws Exception;
	/**
	 * 
	 * @param crno
	 * @throws SQLException
	 * @throws Exception
	 */
	public void updatePrintStatus(String[] crno) throws SQLException, Exception;
	
	/**
	 * 
	 * @param station_code
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public String getLastUsedCR(String station_code) throws RemoteException,Exception;
	
	/**
	 * 
	 * @param stationName
	 * @param type
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public LRNumberRangeDTO[] getLRRange(String stationCode) throws RemoteException, Exception;
	
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
