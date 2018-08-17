package com.hm.dao;

import hm.akr.dto.ArticleDTO;
import hm.akr.dto.BookingDTO;
import hm.akr.dto.GMRDTO;
import hm.akr.dto.GMRInTimeDTO;
import hm.akr.dto.GMROutTimeDTO;
import hm.akr.dto.LRTrackDTO;
import hm.akr.dto.VehicleDTO;
import hm.akr.exceptions.BusinessException;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.ejb.CreateException;
import javax.naming.NamingException;

/**
 * @
 * @version 1.0
 * 
 * DAO class for Booking Parcel
 */
public interface IBookingDAO {

	/**
	 * 
	 * @param dto
	 * @throws Exception
	 */
	public boolean bookParcel(BookingDTO dto) throws BusinessException,
			Exception;

	/**
	 * 
	 * @param lrno
	 * @return
	 * @throws Exception
	 */
	public boolean cancelBooking(String lrno, String stationCode,
			String cancelOption) throws BusinessException, Exception;

	/**
	 * 
	 * @param goods
	 * @return
	 * @throws Exception
	 */
	public String[] assignGoods(GMRDTO[] goods) throws Exception;

	/**
	 * 
	 * @param goods
	 * @return
	 * @throws Exception
	 */
	public String[] deliverGoods(GMRDTO[] goods) throws Exception;

	/**
	 * 
	 * @param goods
	 * @return
	 * @throws Exception
	 */
	public String[] dispatchGoods(GMRDTO[] goods) throws Exception;

	/**
	 * 
	 * @param stationCode
	 * @return
	 * @throws Exception
	 */
	public ArrayList<GMROutTimeDTO> getOutTimeGoods(String stationCode)
			throws Exception;

	/**
	 * 
	 * @param stationCode
	 * @return
	 * @throws Exception
	 */
	public ArrayList<GMRInTimeDTO> getInTimeGoods(String stationCode)
			throws Exception;

	/**
	 * 
	 * @param stationCode
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public ArrayList getStockDetails(String stationCode) throws SQLException,
			Exception;

	/**
	 * 
	 * @param lrno
	 * @return
	 * @throws Exception
	 */
	public BookingDTO getBookingDetail(String lrno, String stationCode, String dbHistory)
			throws BusinessException, Exception;

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArticleDTO[] getArticleTypes() throws Exception;

	/**
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public String[] bookParcels(BookingDTO[] dto) throws Exception;

	/**
	 * 
	 * @param lrno
	 * @return
	 * @throws BusinessException
	 * @throws Exception
	 */
	public LRTrackDTO[] trackLRDetails(String lrno, String dbHistory) throws BusinessException,
			Exception;

	/**
	 * 
	 * @param station_code
	 * @return
	 * @throws BusinessException
	 * @throws Exception
	 */
	public BookingDTO[] getUsedLRNumbers(String station_code,Date allocationDate, String type) throws BusinessException, Exception;

	public BookingDTO[] getViewBill(String customerName, String stationCode,
			String month, String year, boolean bookingType)
			throws SQLException, Exception;

	/**
	 * Method to set the commodities This method is called for insert of
	 * commodity data.
	 * 
	 * @param commodity
	 *            ArticleDTO
	 * 
	 * @throws SQLException
	 * @throws Exception
	 */
	public void insertCommodities(ArticleDTO[] commodity) throws SQLException,
			Exception;

	/**
	 * Method to set the commodities This method is called for update of
	 * commodity data.
	 * 
	 * @param commodity
	 *            ArticleDTO
	 * 
	 * @throws SQLException
	 * @throws Exception
	 */
	public void updateCommodities(ArticleDTO[] commodity) throws SQLException,
			Exception;

	/**
	 * 
	 * @param articleId
	 * @throws SQLException
	 * @throws Exception
	 */
	public void deleteCommodity(String articleName) throws SQLException, Exception;

	/**
	 * 
	 * @param articleType
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void insertSundryArticle(String articleType) throws RemoteException,
			Exception;

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArticleDTO[] getSundryArticles() throws RemoteException, Exception;

	/**
	 * 
	 * @param articleType
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void deleteSundryArticle(String articleType) throws RemoteException,
			Exception;
	
	/**
	 * 
	 * @param code
	 * @param toStation
	 * @return
	 * @throws RemoteException
	 * @throws NamingException
	 * @throws CreateException
	 */
	public int getSpecialDiscounter(String code, String toStation)
			throws RemoteException, NamingException, CreateException,Exception;

public VehicleDTO[] getVehicles() throws Exception;
	/**
	 * 
	 * @param stationCode
	 * @return
	 * @throws Exception
	 */
	public BookingDTO[] getUnUsedLRList(String stationCode) throws Exception;
	
	public GMRDTO getoldLrGMROdetails(String lrno,String stationCode) throws Exception;
	
	public BookingDTO[] getOldlrstatus(String lrno, String stationcode)throws Exception;
	
	public String  deliver_oldlr_goods(GMRDTO goods) throws Exception;
}
