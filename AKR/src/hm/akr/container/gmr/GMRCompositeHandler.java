package hm.akr.container.gmr;

import hm.akr.common.BeanUtil;
import hm.akr.dto.ArticleDTO;
import hm.akr.dto.CashDTO;
import hm.akr.dto.DCProfileDTO;
import hm.akr.dto.GMRDTO;
import hm.akr.dto.GMRReportDTO;
import hm.akr.dto.LRNumberRangeDTO;
import hm.akr.dto.StationsDTO;
import hm.akr.dto.VehicleDTO;
import hm.akr.dto.printer.GMRDetailDTODecorator;
import hm.akr.interfaces.CashRegister;
import hm.akr.interfaces.Goods;
import hm.akr.interfaces.Reporting;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Class to handle the bean communication and client business logic for Goods
 * Movement Register
 * 
 * 
 */
public class GMRCompositeHandler {

	BeanUtil beanutil;

	ArrayList list;

	private static String GMR_DETAILS_JRXML = "hm/akr/resources/printer/GMRDetail.jrxml";
	
	private static String GMR_DETAILS_JRXML1 = "hm/akr/resources/printer/GMRDetail1.jrxml";

	/**
	 * Constructor Method
	 * 
	 * @throws Exception
	 */
	public GMRCompositeHandler() throws Exception {
		try {
			beanutil = BeanUtil.getInstance();
		} catch (Exception exception) {
			throw exception;
		}
	}

	/**
	 * 
	 * @param gLIST
	 * @return
	 * @throws Exception
	 */
	public boolean deliverGoods(GMRDTO[] gLIST) throws Exception {
		boolean status = true;
		try {
			if (null != beanutil) {
				Goods remote = beanutil.getGoodsBean();
				remote.deliverGoods(gLIST);
			}
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
		return status;
	}

	/**
	 * 
	 * @param gLIST
	 * @return
	 * @throws Exception
	 */
	public boolean assignGoods(GMRDTO[] gLIST) throws Exception {
		boolean status = true;
		try {
			if (null != beanutil) {
				Goods remote = beanutil.getGoodsBean();
				//System.out.println("sms--->"+gLIST[0].getSmsDto());
				remote.assignGoods(gLIST);
			}
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
		return status;
	}

	/**
	 * 
	 * @param gLIST
	 * @return
	 * @throws Exception
	 */
	public boolean dispatchGoods(GMRDTO[] gLIST) throws Exception {
		boolean status = true;
		try {
			if (null != beanutil) {
				Goods remote = beanutil.getGoodsBean();
				remote.dispatchGoods(gLIST);
			}
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
		return status;
	}

	/**
	 * 
	 * @param lRNO
	 * @return
	 * @throws Exception
	 */
	

	/**
	 * 
	 * @param sCODE
	 * @return
	 * @throws Exception
	 */
	public ArrayList getOutTimeGoods(String sCODE) throws Exception {
		list = new ArrayList();
		try {
			if (null != beanutil) {
				Goods remote = beanutil.getGoodsBean();
				list = remote.getOutTimeGoods(sCODE);
			}
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
		return list;
	}

	/**
	 * 
	 * @param sCODE
	 * @return
	 * @throws Exception
	 */
	public ArrayList getInTimeGoods(String sCODE) throws Exception {
		list = new ArrayList();
		try {
			if (null != beanutil) {
				Goods remote = beanutil.getGoodsBean();
				list = remote.getInTimeGoods(sCODE);
			}
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
		return list;
	}

	public VehicleDTO[] getVehicles() throws Exception {
		Goods goodsRemote = beanutil.getGoodsBean();
		return goodsRemote.getVehicles();
	}
	
	/**
	 * 
	 * @param stationCode
	 * @return
	 * @throws Exception
	 */
	public VehicleDTO[] getVehicles(String stationCode) throws Exception {

		try {
			if (null != beanutil) {
				return beanutil.getVehicles(stationCode);
			}
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}

		return null;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public StationsDTO[] getAvailableStations() throws Exception {

		try {
			if (null != beanutil) {
				return beanutil.getAvailableStations();
			}
		} catch (RemoteException remoteException) {
			throw remoteException;
		} catch (Exception exception) {
			throw exception;
		}
		return null;
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
	public boolean isAdminHead() {
		return beanutil.isAdminHead();
	}

	/**
	 * 
	 */
	public boolean isAdmin() {
		return beanutil.isAdmin();
	}

		
	/**
	 * 
	 * @param stationCode
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public GMRReportDTO[] getGoodsMovement(String stationCode, Date date)
			throws Exception {
		GMRReportDTO[] goods = null;

		try {
			if (null != beanutil) {
				Reporting cr = beanutil.getReportingBean();
				goods = cr.getGoodsMovement(stationCode, date, BeanUtil.getDbName());
			}
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
		return goods;
	}

	/**
	 * 
	 * @param stationCode
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public GMRReportDTO[] getGoodsMovementHistory(String stationCode, Date date)
			throws Exception {
		GMRReportDTO[] goods = null;

		try {
			if (null != beanutil) {
				Reporting cr = beanutil.getReportingBean();
				goods = cr.getGoodsMovementListHistory(stationCode, date, BeanUtil.getDbName());
			}
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
		return goods;
	}

	/**
	 * 
	 * @param dto
	 * @param date
	 * @param stNo
	 * @param dName
	 * @param vNo
	 * @throws Exception
	 */
	public void printGMRDetails(GMRReportDTO[] dto, String date, String stNo,
			String dName, String vNo, String fromstation, String tostation,boolean isconsignor)
			throws Exception {
		HashMap<String, String> hashMap = new HashMap<String, String>();

		hashMap.put("Date", date);
		hashMap.put("StatementNo", stNo);
		hashMap.put("DriverName", dName);
		hashMap.put("VehicleNo", vNo);
		hashMap.put("fromstation", fromstation);
		hashMap.put("tostation", tostation);

		ArrayList<GMRDetailDTODecorator> list = new ArrayList<GMRDetailDTODecorator>();

		if (null != dto) {
			int size = dto.length;
			for (int i = 0; i < size; i++) {
				list.add(new GMRDetailDTODecorator(dto[i], i + 1,
						getArticleDesc(dto[i].getArticle_id())));
			}
		}

		if (null != beanutil) {
			if(isconsignor)
			beanutil.printData(list, GMR_DETAILS_JRXML, hashMap);
			else
				beanutil.printData(list, GMR_DETAILS_JRXML1, hashMap);
		}
	}

	public String getServerDate() {
		return beanutil.getServerDate();
	}

	public String getServerDateTime() {
		return beanutil.getServerDateTime();
	}

	
	/**
	 * Method to get Article Types
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArticleDTO[] getArticleTypes() throws Exception {
		ArticleDTO[] articles = null;
		if (beanutil != null) {
			articles = beanutil.getArticleTypes();
		}

		return articles;
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	private String getArticleDesc(int id) throws Exception {
		ArticleDTO[] articles = null;

		articles = getArticleTypes();
		for (int i = 0; i < articles.length; i++) {
			if (id == articles[i].getArticleId()) {
				return articles[i].getType();
			}
		}

		return null;
	}
	
	/**
	 * 
	 * @param stationCode
	 * @return
	 *//*
	public String getBranchCode(String stationCode) {
		String station = null;
		try {
			StationsDTO[] stationsDTO = beanutil.getAvailableStations();
			
			for(int i = 0; i < stationsDTO.length; i++){
				if(stationsDTO[i].getId().equalsIgnoreCase(stationCode)){
					station = stationsDTO[i].getBranch_code();					
				}
			}
			
		} catch (Exception e) {			
			
		}
		return station;
	}*/

	/**
	 * 
	 * @param stationCode
	 * @return
	 * @throws Exception
	 */
	public String getLastUsedCR(String stationCode) throws Exception {
		String crno = null;

		try {
			if (null != beanutil) {
				CashRegister cr = beanutil.getCashRegisterBean();
				crno = cr.getLastUsedCR(stationCode);
			}
		} catch (Exception exception) {
			throw exception;
		}

		return crno;
	}

	/**
	 * 
	 * @param stationCode
	 * @return
	 * @throws Exception
	 */
	public LRNumberRangeDTO[] getCRRange(String stationCode) throws Exception {
		LRNumberRangeDTO[] crno = null;
		try {
			if (null != beanutil) {				
				CashRegister cr = beanutil.getCashRegisterBean();
				crno = cr.getLRRange(stationCode);
			}
		} catch (Exception exception) {
			throw exception;
		}

		return crno;
	}
	
	public DCProfileDTO[] getDCDetails() throws Exception {
		DCProfileDTO[] dto = null;
		if (null != beanutil) {
			try {
				dto = beanutil.getDCDetails();

			} catch (Exception exception) {
				throw exception;
			}
		}
		return dto;

	}
	
	public String[] submitCashReceipt(CashDTO[] dto) throws Exception {
		String[] status = null;
		try {
			if (null != beanutil) {

				CashRegister cr = beanutil.getCashRegisterBean();
				status = cr.generateCRList(dto);
			}
		} catch (Exception exception) {
			throw exception;
		}

		return status;
	}

	
}
