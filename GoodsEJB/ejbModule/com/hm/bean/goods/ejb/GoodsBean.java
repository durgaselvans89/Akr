package com.hm.bean.goods.ejb;

import hm.akr.dto.GMRDTO;
import hm.akr.exceptions.BusinessException;
import hm.akr.interfaces.Goods;
import hm.akr.dto.ArticleDTO;
import hm.akr.dto.BookingDTO;
import hm.akr.dto.GMRInTimeDTO;
import hm.akr.dto.GMROutTimeDTO;
import hm.akr.dto.LRTrackDTO;
import hm.akr.dto.VehicleDTO;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.hm.dao.BookingDAO;
import com.hm.dao.IBookingDAO;




import javax.ejb.CreateException;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.naming.NamingException;

@Stateless
@Remote(Goods.class)
@TransactionManagement(TransactionManagementType.BEAN)
/**
 * 
 */
public class GoodsBean implements Goods {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor method
	 */
	public GoodsBean() {
		super();
	}

	/**
	 * Business method
	 * 
	 * @throws Exception
	 * @ejb.interface-method view-type = "remote"
	 */
	public void deliverGoods(GMRDTO[] goodsList) throws Exception {
		IBookingDAO ib = new BookingDAO();

		try {
			ib.deliverGoods(goodsList);

		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
		// ut.commit();
	}

	/**
	 * Business method
	 * 
	 * @throws Exception
	 * @ejb.interface-method view-type = "remote"
	 */
	public void assignGoods(GMRDTO[] goodsList) throws Exception {
		IBookingDAO ib = new BookingDAO();
		try {
			ib.assignGoods(goodsList);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
	}

	/**
	 * Business method
	 * 
	 * @throws Exception
	 * @ejb.interface-method view-type = "remote"
	 */
	public void dispatchGoods(GMRDTO[] goodsList) throws Exception {
		IBookingDAO ib = new BookingDAO();
		try {

			ib.dispatchGoods(goodsList);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
	}

	/**
	 * 
	 * @param dto
	 * @throws Exception
	 */
	public void bookParcel(BookingDTO dto) throws BusinessException, Exception {
		IBookingDAO booking = new BookingDAO();
		try {

			booking.bookParcel(dto);
		} catch (BusinessException business) {

			throw business;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
	}

	/**
	 * 
	 * @param lrno
	 * @throws Exception
	 */
	public void cancelBooking(String lrno, String stationCode,
			String cancelOption) throws BusinessException, Exception {
		try {

			IBookingDAO booking = new BookingDAO();
			booking.cancelBooking(lrno, stationCode, cancelOption);

		} catch (BusinessException business) {

			throw business;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
	}

	/**
	 * 
	 * @param stationCode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<GMROutTimeDTO> getOutTimeGoods(String stationCode)
			throws Exception {
		IBookingDAO booking = new BookingDAO();
		ArrayList<GMROutTimeDTO> outTime = new ArrayList<GMROutTimeDTO>();

		try {
			outTime = booking.getOutTimeGoods(stationCode);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}

		return outTime;
	}

	/**
	 * 
	 * @param stationCode
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ArrayList getInTimeGoods(String stationCode) throws Exception {
		IBookingDAO booking = new BookingDAO();
		ArrayList<GMRInTimeDTO> inTime = new ArrayList<GMRInTimeDTO>();

		try {

			inTime = booking.getInTimeGoods(stationCode);

		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}

		return inTime;
	}

	/**
	 * 
	 */
	public ArrayList getStockDetails(String stationCode) throws SQLException,
			Exception {
		IBookingDAO booking = new BookingDAO();
		ArrayList stock = new ArrayList();

		try {

			stock = booking.getStockDetails(stationCode);

		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}

		return stock;
	}

	/**
	 * 
	 * @param lrno
	 * @return
	 * @throws Exception
	 */
	public BookingDTO getBookingDetail(String lrno, String stationCode, String dbHistory)
			throws BusinessException, Exception {
		IBookingDAO booking = new BookingDAO();

		BookingDTO dto = null;

		try {
			dto = booking.getBookingDetail(lrno, stationCode, dbHistory);

		} catch (BusinessException business) {

			throw business;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}

		return dto;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArticleDTO[] getArticleTypes() throws Exception {
		IBookingDAO booking = new BookingDAO();

		ArticleDTO[] articles = null;

		try {
			articles = booking.getArticleTypes();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
		return articles;
	}

	public String[] bookParcels(BookingDTO[] dto) throws Exception {
		IBookingDAO booking = new BookingDAO();
		String[] failed = null;

		try {
			failed = booking.bookParcels(dto);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}

		return failed;
	}

	@Override
	/**
	 * @return
	 * @throws Exception
	 */
	public LRTrackDTO[] trackLRDetails(String lrno, String dbHistory) throws Exception {
		IBookingDAO booking = new BookingDAO();
		LRTrackDTO[] track = null;

		try {
			track = booking.trackLRDetails(lrno, dbHistory);

		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}

		return track;
	}

	@Override
	/**
	 * 
	 */
	public BookingDTO[] getUsedLRNumbers(String station_code,
			Date allocationDate, String type) throws BusinessException, RemoteException,
			Exception {
		IBookingDAO booking = new BookingDAO();
		BookingDTO[] lrnumber = null;

		try {
			lrnumber = booking.getUsedLRNumbers(station_code, allocationDate, type);

		} catch (Exception exception) {
			throw exception;
		}

		return lrnumber;
	}

	/**
	 * Method to set the commodities This method is called for insert of
	 * commodity data.
	 * 
	 * @param commodity
	 *            ArticleDTO
	 * 
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void insertCommodities(ArticleDTO[] commodity)
			throws RemoteException, Exception {
		IBookingDAO commodities = new BookingDAO();
		try {
			commodities.insertCommodities(commodity);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
	}

	/**
	 * Method to set the commodities This method is called for insert of
	 * commodity data.
	 * 
	 * @param commodity
	 *            ArticleDTO
	 * 
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void updateCommodities(ArticleDTO[] commodity)
			throws RemoteException, Exception {
		IBookingDAO commodities = new BookingDAO();
		try {
			commodities.updateCommodities(commodity);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
	}

	public void deleteCommodity(String articleName) throws RemoteException,
			Exception {
		IBookingDAO commodity = new BookingDAO();
		try {
			commodity.deleteCommodity(articleName);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
	}

	@Override
	public BookingDTO[] getViewBill(String customerName, String stationCode,
			String month, String year, boolean bookingType)
			throws RemoteException, Exception {
		IBookingDAO booking = new BookingDAO();
		BookingDTO[] viewBill = null;

		try {
			viewBill = booking.getViewBill(customerName, stationCode, month,
					year, bookingType);

		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}

		return viewBill;
	}

	/**
	 * 
	 */
	public void insertSundryArticle(String articleType) throws RemoteException,
			Exception {
		IBookingDAO commodities = new BookingDAO();
		try {
			commodities.insertSundryArticle(articleType);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
	}

	/**
	 * 
	 */
	public ArticleDTO[] getSundryArticles() throws Exception {
		IBookingDAO dao = new BookingDAO();
		ArticleDTO[] articleDto = null;
		try {
			articleDto = dao.getSundryArticles();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}

		return articleDto;
	}

	/**
	 * 
	 */
	public void deleteSundryArticle(String articleType) throws RemoteException,
			Exception {
		IBookingDAO dao = new BookingDAO();

		try {
			dao.deleteSundryArticle(articleType);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
	}

	@Override
	public int getSpecialDiscounter(String code, String toStation)
			throws RemoteException, NamingException, CreateException, Exception {
		IBookingDAO dao = new BookingDAO();
		int special = 0;
		try {
			special = dao.getSpecialDiscounter(code, toStation);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
		return special;
	}
	
		public VehicleDTO[] getVehicles() throws Exception{
		IBookingDAO booking = new BookingDAO();
		VehicleDTO[] dto = null;

		try {
			dto = booking.getVehicles();

		} catch (BusinessException business) {
			throw business;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}

		return dto;
	}

	public BookingDTO[] getUnUsedLRList(String station_code) throws Exception {
		IBookingDAO booking = new BookingDAO();
		BookingDTO[] lrnumber = null;
	
		try {
			lrnumber = booking.getUnUsedLRList(station_code);

		} catch (Exception exception) {
			throw exception;
		}

		return lrnumber;
	}
	
	public GMRDTO getoldLrGMROdetails(String lrno,String stationcode)
	throws Exception {
	IBookingDAO sod = new BookingDAO();
	GMRDTO list = null;
	
	try {
		list = sod.getoldLrGMROdetails(lrno,stationcode);
	
	} catch (Exception exception) {
		throw exception;
	}
	
	return list;
	
	}
	
	
	public BookingDTO[] getOldlrstatus(String lrno,String stationcode)
	throws Exception {
		IBookingDAO sod = new BookingDAO();
	BookingDTO[] list = null;
	
	try {
		list = sod.getOldlrstatus(lrno,stationcode);
	
	} catch (Exception exception) {
		throw exception;
	}
	
	return list;
	
	}
	
	public void deliver_oldlr_goods(GMRDTO goods)
	throws Exception {
	IBookingDAO sod = new BookingDAO();
	//GMRDTO list = null;
	
	try {
		 sod.deliver_oldlr_goods(goods);
	
	} catch (Exception exception) {
		throw exception;
	}
	
	//return list;
	
	}

}
