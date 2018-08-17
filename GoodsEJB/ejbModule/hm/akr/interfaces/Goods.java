package hm.akr.interfaces;

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
 * Remote interface for GMR.
 * 
 */
public interface Goods {

	/**
	 * Business method
	 */
	public void deliverGoods(GMRDTO[] goodsList) throws RemoteException,
			Exception;

	/**
	 * Business method
	 */
	public void assignGoods(GMRDTO[] goodsList) throws RemoteException,
			Exception;

	/**
	 * Business method
	 */
	public void dispatchGoods(GMRDTO[] goodsList) throws RemoteException,
			Exception;

	/**
	 * 
	 * @throws RemoteException
	 */
	public void bookParcel(BookingDTO bookDTO) throws RemoteException,
			BusinessException, Exception;

	/**
	 * 
	 * @param lrno
	 * @throws RemoteException
	 */
	public void cancelBooking(String lrno, String stationCode,
			String cancelOption) throws RemoteException, BusinessException,
			Exception;

	/**
	 * 
	 * @param stationCode
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<GMROutTimeDTO> getOutTimeGoods(String stationCode)
			throws RemoteException, Exception;

	/**
	 * 
	 * @param stationCode
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<GMRInTimeDTO> getInTimeGoods(String stationCode)
			throws RemoteException, Exception;

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
	 * @throws RemoteException
	 */
	public BookingDTO getBookingDetail(String lrno, String station_code, String dbHistory)
			throws BusinessException, RemoteException, Exception;

	/**
	 * 
	 */
	public ArticleDTO[] getArticleTypes() throws RemoteException, Exception;

	/**
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public String[] bookParcels(BookingDTO[] dto) throws RemoteException,
			Exception;

	/**
	 * 
	 * @param lrno
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public LRTrackDTO[] trackLRDetails(String lrno, String dbHistory) throws RemoteException,
			Exception;

	/**
	 * 
	 * @param station_code
	 * @return
	 * @throws BusinessException
	 * @throws RemoteException
	 * @throws Exception
	 */
	public BookingDTO[] getUsedLRNumbers(String station_code,
			Date allocationDate, String type) throws BusinessException, RemoteException,
			Exception;

	public BookingDTO[] getViewBill(String customerName, String stationCode,
			String month, String year, boolean bookingType)
			throws RemoteException, Exception;

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
	public void insertCommodities(ArticleDTO[] commodity)
			throws RemoteException, Exception;

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
	public void updateCommodities(ArticleDTO[] commodity)
			throws RemoteException, Exception;

	/**
	 * 
	 * @param articleId
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void deleteCommodity(String articleName) throws RemoteException,
			Exception;

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
	 * @throws Exception 
	 */
	public int getSpecialDiscounter(String code, String toStation)
			throws RemoteException, NamingException, CreateException, Exception;
	
	public VehicleDTO[] getVehicles() throws Exception;
	/**
	 * 
	 * @param stationCode
	 * @return
	 * @throws Exception
	 */
	public BookingDTO[] getUnUsedLRList(String stationCode) throws Exception;
	
	public GMRDTO getoldLrGMROdetails(String lrno,String stationCode) throws Exception;
	
	public BookingDTO[] getOldlrstatus(String lrno, String stationcode)throws RemoteException,Exception;
	
	public void  deliver_oldlr_goods(GMRDTO goods) throws Exception;
	
}
