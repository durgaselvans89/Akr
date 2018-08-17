package com.hm.bean.cash.ejb;

import java.rmi.RemoteException;
import java.util.Date;

import hm.akr.dto.CashDTO;
import hm.akr.dto.CashRegisterDTO;
import hm.akr.dto.LRNumberRangeDTO;
import hm.akr.dto.SMSDTO;
import hm.akr.exceptions.BusinessException;
import hm.akr.interfaces.CashRegister;

import com.hm.dao.CashDAO;
import com.hm.dao.ICashDAO;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

@Stateless
@Remote(CashRegister.class)
@TransactionManagement(TransactionManagementType.BEAN)
public class CashRegisterBean implements CashRegister {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CashRegisterBean() {
		super();
	}

	/**
	 * Business Method
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public boolean generateCR(CashDTO dto) throws BusinessException, Exception {

		ICashDAO cash = new CashDAO();
		boolean result = true;

		try {
			result = cash.generateCR(dto);
		} catch (BusinessException business) {
			throw business;
		} catch (Exception exception) {
			throw exception;
		}
		return result;
	}

	/**
	 * 
	 * @param dto
	 * @return
	 * @throws BusinessException
	 * @throws Exception
	 */
	public boolean cancelCR(CashDTO dto) throws BusinessException, Exception {

		ICashDAO cash = new CashDAO();
		boolean result = true;

		try {
			result = cash.cancelCR(dto);
		} catch (BusinessException business) {

			throw business;
		} catch (Exception exception) {
			throw exception;
		}
		return result;
	}

	/**
	 * 
	 * @param crno
	 * @return
	 * @throws BusinessException
	 * @throws Exception
	 */
	public CashDTO getCRInformation(String crno, Date date) throws BusinessException,
			Exception {

		ICashDAO cash = new CashDAO();
		CashDTO dto = null;

		try {
			dto = cash.getCRInformation(crno, date);
		} catch (BusinessException business) {
			throw business;
		} catch (Exception exception) {
			throw exception;
		}
		return dto;
	}

	/**
	 * 
	 * @param crno
	 * @return
	 * @throws BusinessException
	 * @throws Exception
	 */
	public CashDTO[] getCRInformation(String stationcode, Date fromdate,
			Date todate) throws BusinessException, Exception {

		ICashDAO cash = new CashDAO();
		CashDTO[] dto = null;

		try {
			dto = cash.getCRInformation(stationcode, fromdate, todate);
		} catch (BusinessException business) {
			throw business;
		} catch (Exception exception) {
			throw exception;
		}
		return dto;
	}

	/**
	 * 
	 * @param stationCode
	 * @return
	 * @throws BusinessException
	 * @throws Exception
	 */
	public CashRegisterDTO[] getLRNosForTopay(String stationCode)
			throws BusinessException, Exception {
		CashRegisterDTO[] lrnumber = null;

		try {
			CashDAO allocation = new CashDAO();
			lrnumber = allocation.getLRNosForTopay(stationCode);

		} catch (BusinessException business) {
			throw business;
		} catch (Exception exception) {
			throw exception;
		}

		return lrnumber;
	}

	/**
	 * 
	 * @param stationCode
	 * @return
	 * @throws BusinessException
	 * @throws Exception
	 */
	public CashRegisterDTO[] getLRNosForPaid(String stationCode)
			throws BusinessException, Exception {
		CashRegisterDTO[] lrnumber = null;

		try {
			ICashDAO allocation = new CashDAO();
			lrnumber = allocation.getLRNosForPaid(stationCode);

		} catch (BusinessException business) {

			throw business;
		} catch (Exception exception) {
			throw exception;
		}

		return lrnumber;
	}

	/**
	 * Business Method
	 * 
	 * @param stationCode
	 * @return
	 * @throws Exception
	 */
	public CashRegisterDTO[] getLRNosForBilling(String stationCode)
			throws BusinessException, Exception {
		CashRegisterDTO[] lrnumber = null;

		try {
			ICashDAO allocation = new CashDAO();
			lrnumber = allocation.getLRNosForBilling(stationCode);

		} catch (BusinessException business) {
			throw business;
		} catch (Exception exception) {
			throw exception;
		}

		return lrnumber;
	}

	public String[] generateCRList(CashDTO[] dto) throws BusinessException,
			Exception {
		ICashDAO dao = new CashDAO();
		String[] failed = null;

		try {
			failed = dao.generateCRList(dto);
		} catch (Exception exception) {
			throw exception;
		}
		return failed;
	}
	
	public void updateCRList(CashDTO[] dto) throws Exception {
		ICashDAO dao = new CashDAO();

		try {
			dao.updateCRList(dto);
		} catch (Exception exception) {
			throw exception;
		}
	}

	@Override
	public void updatePrintStatus(String[] crno) throws RemoteException,
			Exception {

		ICashDAO dao = new CashDAO();

		try {
			dao.updatePrintStatus(crno);
		} catch (Exception exception) {
			throw exception;
		}

	}

	@Override
	public String getLastUsedCR(String station_code) throws RemoteException,
			Exception {
		ICashDAO cash = new CashDAO();
		String crno = null;

		try {
			crno = cash.getLastUsedCR(station_code);
		} catch (Exception exception) {
			throw exception;
		}
		return crno;
	}

	/**
	 * 
	 */
	public LRNumberRangeDTO[] getLRRange(String stationCode) throws Exception {
		ICashDAO station = new CashDAO();
		LRNumberRangeDTO[] lrFormat = null;

		try {
			lrFormat = station.getLRRange(stationCode);

		} catch (Exception exception) {
			throw exception;
		}

		return lrFormat;
	}

	/**
	 * 
	 * @param smsdto
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void storeSMSToFuture(SMSDTO[] smsdto) throws RemoteException,
			Exception {

		ICashDAO dao = new CashDAO();
		try {
			dao.storeSMSToFuture(smsdto);
		} catch (Exception exception) {
			throw exception;
		}
	}

	public CashDTO[] getCRInformationHistory(String stationcode, Date fromdate,
			Date todate, String dbHistory) throws Exception{

		ICashDAO cash = new CashDAO();
		CashDTO[] dto = null;

		try {
			dto = cash.getCRInformationHistory(stationcode, fromdate, todate, dbHistory);
		} catch (BusinessException business) {
			throw business;
		} catch (Exception exception) {
			throw exception;
		}
		return dto;
	}
}
