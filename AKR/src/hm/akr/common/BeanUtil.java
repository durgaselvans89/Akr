package hm.akr.common;

import hm.akr.dto.ArticleDTO;
import hm.akr.dto.BookedLRDTO;
import hm.akr.dto.BookingCommissionDTO;
import hm.akr.dto.BookingDTO;
import hm.akr.dto.CCProfileDTO;
import hm.akr.dto.CardRateProfileDTO;
import hm.akr.dto.CartageCommissionDTO;
import hm.akr.dto.ClientQuotationDTO;
import hm.akr.dto.CommissionSummaryDTO;
import hm.akr.dto.CurrentStatusDTO;
import hm.akr.dto.CustomerBusinessAnalysisDTO;
import hm.akr.dto.CustomerDTO;
import hm.akr.dto.DCProfileDTO;
import hm.akr.dto.DDDetailsDTO;
import hm.akr.dto.DDRDTO;
import hm.akr.dto.DailyStatusDTO;
import hm.akr.dto.DeliveryCommissionDTO;
import hm.akr.dto.DistanceListDTO;
import hm.akr.dto.GMROutTimeDTO;
import hm.akr.dto.GeneralSummaryDTO;
import hm.akr.dto.InwardAnalysisDTO;
import hm.akr.dto.LRNumberRangeDTO;
import hm.akr.dto.MissingCustomersDTO;
import hm.akr.dto.MsgDTO;
import hm.akr.dto.RefundRecoveryDTO;
import hm.akr.dto.RegularSundryDTO;
import hm.akr.dto.RemittanceShortageDTO;
import hm.akr.dto.SpecialSundryDTO;
import hm.akr.dto.StationsDTO;
import hm.akr.dto.TranshipmentDTO;
import hm.akr.dto.ValidationDTO;
import hm.akr.dto.VehicleDTO;
import hm.akr.dto.VersionDTO;
import hm.akr.interfaces.CashRegister;
import hm.akr.interfaces.Commission;
import hm.akr.interfaces.Customer;
import hm.akr.interfaces.Goods;
import hm.akr.interfaces.Quotation;
import hm.akr.interfaces.Reporting;
import hm.akr.interfaces.Security;
import hm.akr.interfaces.Station;
import hm.akr.interfaces.Sundry;
import hm.akr.interfaces.Vehicle;
import hm.akr.workspace.AkrEmail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Properties;

import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.view.JasperViewer;

/**
 * Bean Util class to get the context and the remote interface of the bean
 * 
 * @version 1.0
 */
public class BeanUtil {

	private static BeanUtil util = null;

	static Properties properties = null;

	static Context context = null;

	private static String PROPERTY_FILE_NAME = "lib/akrsystem.properties";

	private static final String INITIAL_CONTEXT_FACTORY = "InitialContextFactory";

	private static final String URL_PKG_PREFIXES = "UrlPrefix";

	private static final String STATION_CODE = "StationCode";

	private static final String STATION_NAME = "StationName";

	VehicleDTO[] vehicles = null;

	StationsDTO[] stations = null;

	ValidationDTO validation = null;

	ArticleDTO[] articles = null;

	DailyStatusDTO[] status = null;

	BookingCommissionDTO[] currentBCommDTO = null;

	DeliveryCommissionDTO[] currentDCommDTO = null;

	CartageCommissionDTO[] currentCCommDTO = null;

	RefundRecoveryDTO[] currentRecoveryDTO = null;

	RefundRecoveryDTO[] currentRefundDTO = null;

	CommissionSummaryDTO currentSummaryDTO = null;

	CardRateProfileDTO[] cardRateProfileDTO = null;

	CCProfileDTO[] ccProfileDTO = null;

	DCProfileDTO[] dcProfileDTO = null;

	FileOutputStream fo = null;

	private static LRNumberRangeDTO[] lrFormat = null;

	private static String stationCode = null;

	private static String stationName = null;

	private static RegularSundryDTO sundrydto = null;

	private static DistanceListDTO[] distancedto = null;

	CustomerDTO[] customerdto = null;

	private static String serverDate = null;

	private static String serverDateTime = null;

	private static boolean isValid = false;

	private static boolean isAdmin = false;

	private static boolean isAdminHead = false;

	private static boolean isAdminHeadStationary = false;

	private static boolean isSecondThread = false;

	private static boolean isDiffStation = false;

	public static int isSecondServer = 0;

	boolean isQuotationTaken = false;

	CustomerDTO[] contactsDTO = null;

	double[] units = null;

	SpecialSundryDTO[] specialdto = null;

	CustomerDTO[] initialContactsDTO = null;
	
	private static String dbName = "";
	
	private static String dbYear = ""; 
	
	private static float clientRights = -1;

private static Date three_month_updated = null;
	/**
	 * 
	 * @return
	 */
	public static BeanUtil getInstance() throws Exception {
		if (null == util) {
			util = new BeanUtil();

		}
		return util;
	}

	/**
	 * Constructor method
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public BeanUtil() throws NamingException, Exception {
		try {
			if (isSecondServer == 0 || isSecondServer == 2) {
				//getContext("122.165.219.48:1099");
				//getContext("59.99.224.159:1099");
				//getContext("115.115.65.122:1099");
				//getContext("182.72.199.30:1099");
				//getContext("192.168.0.21:1099");
				getContext("localhost:1099");
				//getContext("192.168.1.222:1099");
			} else if (isSecondServer == 1 || isSecondServer == 3) {
				isSecondServer = 4;				
				//getContext("122.165.57.163:1099");
				//getContext("192.168.1.222:1099");
				getContext("122.165.219.48:1099");
			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Method to EJB Reference Using Initial Context
	 * 
	 * @param serverIp
	 * @throws Exception
	 */
	private void getContext(String serverIp) throws Exception {
		FileInputStream stream = null;
		stream = new FileInputStream(PROPERTY_FILE_NAME);
		try {

			if (null != stream) {
				properties = new Properties();
				properties.load(stream);

				// Set the context
				Hashtable ht = new Hashtable();
				ht.put(InitialContext.INITIAL_CONTEXT_FACTORY, properties
						.getProperty(INITIAL_CONTEXT_FACTORY));
				ht.put(InitialContext.PROVIDER_URL, serverIp);

				ht.put(InitialContext.URL_PKG_PREFIXES, properties
						.getProperty(URL_PKG_PREFIXES));
				// Find and create a reference to the bean using JNDI

				context = new InitialContext(ht);

			} else {

			}
		} catch (Exception exception) {
			throw exception;
		}
	}

	/**
	 * Method to get Cash Bean Reference
	 * 
	 * @return
	 * @throws CreateException
	 */
	public CashRegister getCashRegisterBean() throws NamingException,
			RemoteException, CreateException {
		CashRegister register = null;

		try {
			register = (CashRegister) context.lookup("CashRegisterBean/remote");
		} catch (Exception exception) {

		}
		return register;
	}

	/**
	 * Method to get Goods Bean Reference
	 * 
	 * @return
	 * @throws NamingException
	 * @throws RemoteException
	 * @throws CreateException
	 */
	public Goods getGoodsBean() throws NamingException, RemoteException,
			CreateException {

		Goods goods = null;

		try {
			goods = (Goods) context.lookup("GoodsBean/remote");
		} catch (Exception exception) {

		}
		return goods;
	}

	/**
	 * Method to get Reporting Bean Reference
	 * 
	 * @return
	 * @throws NamingException
	 * @throws RemoteException
	 * @throws CreateException
	 */
	public Reporting getReportingBean() throws NamingException,
			RemoteException, CreateException {
		Reporting reporting = null;
		try {
			reporting = (Reporting) context.lookup("ReportingBean/remote");
		} catch (Exception exception) {

		}
		return reporting;
	}

	/**
	 * Method to get Vehicle Bean Reference
	 * 
	 * @return
	 * @throws NamingException
	 * @throws RemoteException
	 * @throws CreateException
	 */
	public Vehicle getVehicleBean() throws NamingException, RemoteException,
			CreateException {
		Vehicle vehicle = null;

		try {
			vehicle = (Vehicle) context.lookup("VehicleBean/remote");
		} catch (Exception exception) {

		}
		return vehicle;
	}

	/**
	 * Method to get Station Bean Reference
	 * 
	 * @return
	 * @throws NamingException
	 * @throws RemoteException
	 * @throws CreateException
	 */
	public Station getStationBean() throws NamingException, RemoteException,
			CreateException {
		Station station = null;

		try {
			station = (Station) context.lookup("StationBean/remote");
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return station;
	}

	/**
	 * Method to get Security Bean Reference
	 * 
	 * @return
	 * @throws NamingException
	 * @throws RemoteException
	 * @throws CreateException
	 */
	public Security getSecurityBean() throws NamingException, RemoteException,
			CreateException {
		Security security = null;
		try {
			security = (Security) context.lookup("SecurityBean/remote");
		} catch (Exception exception) {
			 exception.printStackTrace();
			util = null;
			return null;

		}
		return security;
	}

	/**
	 * Method to get reference for Customer Bean
	 * 
	 * @return
	 * @throws NamingException
	 * @throws RemoteException
	 * @throws CreateException
	 */
	public Customer getCustomerBean() throws NamingException, RemoteException,
			CreateException {
		Customer customer = null;

		try {
			customer = (Customer) context.lookup("CustomerBean/remote");
		} catch (Exception exception) {

		}
		return customer;
	}

	/**
	 * 
	 * @return
	 * @throws NamingException
	 * @throws RemoteException
	 * @throws CreateException
	 */
	public Quotation getQuotationBean() throws NamingException,
			RemoteException, CreateException {
		Quotation quotation = null;

		try {
			quotation = (Quotation) context.lookup("QuotationBean/remote");
		} catch (Exception exception) {

		}
		return quotation;
	}

	/**
	 * 
	 * @return
	 * @throws NamingException
	 * @throws RemoteException
	 * @throws CreateException
	 */
	public Sundry getSundryBean() throws NamingException, RemoteException,
			CreateException {
		Sundry sundry = null;

		try {
			sundry = (Sundry) context.lookup("SundryBean/remote");
		} catch (Exception exception) {

		}
		return sundry;
	}

	/**
	 * 
	 * @return
	 * @throws NamingException
	 * @throws RemoteException
	 * @throws CreateException
	 */
	public Commission getCommisionBean() throws NamingException,
			RemoteException, CreateException {
		Commission commision = null;

		try {
			commision = (Commission) context.lookup("CommissionBean/remote");

		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return commision;
	}

	/**
	 * 
	 * @return
	 */
	public String getActingStationCode() {
		if (null == stationCode) {
			stationCode = properties.getProperty(STATION_CODE);
		}

		return stationCode;
	}

	/**
	 * Method to get Actual station Code
	 * 
	 * @return
	 */
	public String getActualStationCode() {
		return properties.getProperty(STATION_CODE);
	}

	/**
	 * Method to get LR created Station
	 * 
	 * @return
	 */
	public String getCreatedBy() {
		String createdby = properties.getProperty(STATION_CODE);
		return createdby;
	}

	/**
	 * Method to get ActingStation Name
	 */
	public String getActingStationName() {
		if (null == stationName) {
			stationName = properties.getProperty(STATION_NAME);
		}
		return stationName;
	}

	/**
	 * Method to get Actual Station Code
	 * 
	 * @return
	 */
	public String getActualStationName() {
		return properties.getProperty(STATION_NAME);
	}

	/**
	 * Method to Set Station Name
	 * 
	 * @param StationNameValue
	 * @throws IOException
	 */
	public void setStationName(String StationNameValue) throws IOException {
		stationName = StationNameValue;
	}

	/**
	 * Method to set Station_Code
	 * 
	 * @param StationCodeValue
	 * @throws IOException
	 */
	public void setStationCode(String StationCodeValue) throws IOException {
		stationCode = StationCodeValue;
	}

	public void setServerDate(String serverDate) {
		BeanUtil.serverDate = serverDate;
	}

	/**
	 * @return the serverDateTime
	 */
	public String getServerDateTime() {
		String date = null;
		String SERVER_DATE_TIME = null;
		try {
			Security sr = getSecurityBean();
			if (sr != null)
				date = sr.getServerDate();
			DateFormat timeFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			java.util.Date dateTime = timeFormat.parse(date);
			SERVER_DATE_TIME = timeFormat.format(dateTime);
		} catch (Exception e) {

		}

		return SERVER_DATE_TIME;

	}

	/**
	 * @param serverDateTime
	 *            the serverDateTime to set
	 */
	/*
	 * public void setServerDateTime(String serverDateTime) {
	 * BeanUtil.serverDateTime = serverDateTime; }
	 */

	/**
	 * Method to Print Details
	 * 
	 * @param data
	 * @param file_location
	 * @param hashMap
	 * @throws JRException
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void printData(ArrayList data, String file_location, HashMap hashMap)
			throws JRException, Exception {
		try {
			JasperReport jasperReport;
			JasperPrint jasperPrint;

			InputStream stream = BeanUtil.class.getClassLoader()
					.getResourceAsStream(file_location);
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(data);
			jasperReport = JasperCompileManager.compileReport(stream);
			jasperPrint = JasperFillManager.fillReport(jasperReport, hashMap,
					ds);
			/*JasperViewer viewer = new JasperViewer(jasperPrint, false);
			viewer.setTitle("AKR Express");
			viewer.show();*/
			JasperPrintManager.printReport(jasperPrint, false);

		} catch (JRException exception) {
			exception.printStackTrace();
			throw exception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
	}

	public void printData(ArrayList data, String file_location, HashMap hashMap,boolean isreport)
	throws JRException, Exception {
try {
	JasperReport jasperReport;
	JasperPrint jasperPrint;

	InputStream stream = BeanUtil.class.getClassLoader()
			.getResourceAsStream(file_location);
	JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(data);
	jasperReport = JasperCompileManager.compileReport(stream);
	jasperPrint = JasperFillManager.fillReport(jasperReport, hashMap,
			ds);
	JasperViewer viewer = new JasperViewer(jasperPrint, false);
	viewer.setTitle("AKR Express");
	viewer.show();
	//JasperPrintManager.printReport(jasperPrint, false);

} catch (JRException exception) {
	exception.printStackTrace();
	throw exception;
} catch (Exception exception) {
	exception.printStackTrace();
	throw exception;
}
}
	
	
	public void exportToReportExcel(ArrayList data, String jrxmlFile, HashMap<String, String> hashMap,  String fileName)
	throws JRException, Exception {
		try {
			JasperReport jasperReport;
			JasperPrint jasperPrint;
			//	System.out.println("list-->"+data);

			InputStream stream = BeanUtil.class.getClassLoader()
				.getResourceAsStream(jrxmlFile);
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(
					data, false);
			jasperReport = JasperCompileManager.compileReport(stream);
			jasperPrint = JasperFillManager.fillReport(jasperReport, hashMap,
					ds);
	
			//JasperPrintManager.printReport(jasperPrint, false);

			//new JRBeanCollectionDataSource(data, false);

			File output = new File("lib/" + fileName + ".xls");
			if (output.exists()) {
				output.createNewFile();
			}
			JRXlsExporter exporterXLS = new JRXlsExporter();
			exporterXLS.setParameter(JRExporterParameter.JASPER_PRINT,
					jasperPrint);
			exporterXLS.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
					output.toString());
			exporterXLS
				.setParameter(JRXlsAbstractExporterParameter.IS_ONE_PAGE_PER_SHEET,
					Boolean.FALSE);
			exporterXLS.setParameter(
					JRXlsAbstractExporterParameter.IS_AUTO_DETECT_CELL_TYPE,
						Boolean.TRUE);
			exporterXLS.setParameter(
					JRXlsAbstractExporterParameter.IS_WHITE_PAGE_BACKGROUND,
					Boolean.FALSE);
			exporterXLS.setParameter(
					JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
						Boolean.TRUE);
			exporterXLS.exportReport();

		} catch (JRException exception) {
			exception.printStackTrace();
			throw exception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
	}

	public void printPDF(ArrayList data, String jrxmlFile, HashMap<String, String> hashMap,  String fileName)
	throws JRException, Exception {
		try {
			JasperReport jasperReport;
			JasperPrint jasperPrint;
		
			//	System.out.println("list-->"+data);

			InputStream stream = BeanUtil.class.getClassLoader()
				.getResourceAsStream(jrxmlFile);
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(
					data, false);
			jasperReport = JasperCompileManager.compileReport(stream);
			jasperPrint = JasperFillManager.fillReport(jasperReport, hashMap,
					ds);
			
			//	JasperPrintManager.printReport(jasperPrint, false);
			
			//new JRBeanCollectionDataSource(data, false);

			File output = new File("lib/" + fileName + ".pdf");
			JasperPrintManager.printReportToPdfFile(jasperPrint, output.toString());	
			
		} catch (JRException exception) {
			exception.printStackTrace();
			throw exception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
		

	}

	
	
	/**
	 * Method to get All Vehicles
	 * 
	 * @param stationCode
	 * @return
	 * @throws Exception
	 */
	public VehicleDTO[] getVehicles(String stationCode) throws Exception {

		if (null == vehicles) {
			try {
				Vehicle vehicleRemote = getVehicleBean();
				vehicles = vehicleRemote.getVehicles(stationCode);
			} catch (RemoteException remoteexception) {
				throw remoteexception;
			} catch (Exception exception) {
				throw exception;
			}
		}
		return vehicles;
	}

	/**
	 * Method to get All Available Stations
	 * 
	 * @return
	 * @throws Exception
	 */
	public StationsDTO[] getAvailableStations() throws Exception {

		if (null == stations) {
			try {
				Station stationRemote = getStationBean();
				stations = stationRemote.getAllStations();

			} catch (RemoteException remoteException) {
				remoteException.printStackTrace();
				throw remoteException;
			} catch (Exception exception) {
				exception.printStackTrace();
				throw exception;
			}
		}

		return stations;
	}

	/**
	 * 
	 * @param fromDt
	 * @param toDate
	 * @param fromStn
	 * @param inwardStn
	 * @return
	 * @throws Exception
	 */
	public InwardAnalysisDTO[] getInwardAnalysis(Date fromDt, Date toDate,
			String fromStn, String inwardStn) throws Exception {
		Reporting reportingRemote = getReportingBean();
		InwardAnalysisDTO[] dto = null;
		try {
			dto = reportingRemote.getInwardAnalysis(fromDt,toDate, fromStn,
					inwardStn, BeanUtil.getDbName());
		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}

	/**
	 * Method to set Stationary Settings
	 * 
	 * @param lrFormat
	 * @return
	 * @throws Exception
	 */
	public boolean setStationaryValues(LRNumberRangeDTO[] lrFormat)
			throws Exception {
		boolean result = false;
		try {
			Station stationRemote = getStationBean();
			result = stationRemote.setStationaryValues(lrFormat);
		} catch (RemoteException remoteException) {
			remoteException.printStackTrace();
			throw remoteException;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}

		return result;
	}

	/**
	 * Method to get Stationary
	 * 
	 * @return
	 * @throws Exception
	 */
	public LRNumberRangeDTO[] getStationaryValues() throws Exception {
		LRNumberRangeDTO[] lrFormat = null;
		try {
			Station stationRemote = getStationBean();
			lrFormat = stationRemote.getStationaryValues();

		} catch (RemoteException remoteException) {
			remoteException.printStackTrace();
			throw remoteException;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}

		return lrFormat;
	}

	/**
	 * Method to Assign Stationary
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean assignStationary(String[] stations, int[] types)
			throws Exception {
		boolean result = false;
		try {
			Station stationRemote = getStationBean();
			result = stationRemote.assignStationary(stations, types);

		} catch (RemoteException remoteException) {
			remoteException.printStackTrace();
			throw remoteException;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}

		return result;
	}

	/**
	 * Method to Assign Stationary
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public boolean assignStationary(LRNumberRangeDTO[] dto) throws Exception {
		boolean result = false;
		try {
			Station stationRemote = getStationBean();
			result = stationRemote.assignStationary(dto);

		} catch (RemoteException remoteException) {
			remoteException.printStackTrace();
			throw remoteException;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}

		return result;

	}

	/**
	 * Method to set Alarms
	 * 
	 * @param stations
	 * @param topay
	 * @param paid
	 * @param billing
	 * @param cr
	 * @return
	 * @throws Exception
	 */
	public boolean setAlarmSettings(String[] stations, int topay, int paid,
			int billing, int cr) throws Exception {
		boolean result = false;
		try {
			Station stationRemote = getStationBean();
			result = stationRemote.setAlarmSettings(stations, topay, paid,
					billing, cr);

		} catch (RemoteException remoteException) {
			remoteException.printStackTrace();
			throw remoteException;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}

		return result;
	}

	/**
	 * Method to get stationary Report
	 * 
	 * @return
	 * @throws Exception
	 */
	public LRNumberRangeDTO[] getStationaryReport() throws Exception {
		LRNumberRangeDTO[] lrFormat = null;
		try {
			Station stationRemote = getStationBean();
			lrFormat = stationRemote.getStationaryReport();

		} catch (RemoteException remoteException) {
			remoteException.printStackTrace();
			throw remoteException;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}

		return lrFormat;
	}

	/**
	 * Method to update LR Format
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean updateLRFormat(LRNumberRangeDTO[] lrFormat) throws Exception {
		boolean result = false;
		try {
			Station stationRemote = getStationBean();
			result = stationRemote.updateLRFormat(lrFormat);

		} catch (RemoteException remoteException) {
			remoteException.printStackTrace();
			throw remoteException;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}

		return result;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public LRNumberRangeDTO[] getLRRange(boolean refresh) throws Exception {

		if (null == lrFormat || refresh) {
			try {
				Station stationRemote = getStationBean();
				lrFormat = stationRemote
						.getLRRange(util.getActingStationCode());

			} catch (RemoteException remoteException) {
				remoteException.printStackTrace();
				throw remoteException;
			} catch (Exception exception) {
				exception.printStackTrace();
				throw exception;
			}
		}
		return lrFormat;
	}

	/**
	 * Method to get ArticleTypes
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArticleDTO[] getArticleTypes() throws Exception {
		if (null == articles) {
			try {
				Goods goodsRemote = getGoodsBean();
				articles = goodsRemote.getArticleTypes();

			} catch (RemoteException remoteException) {
				throw remoteException;
			} catch (Exception exception) {
				throw exception;
			}
		}
		return articles;
	}

	/**
	 * Method to get Daily status
	 * 
	 * @return
	 * @throws Exception
	 */
	public DailyStatusDTO[] getDailyStatus(Date date) throws Exception {
		try {
			Reporting reportingRemote = getReportingBean();
			status = reportingRemote.getDailyStatus(date);

		} catch (RemoteException remoteException) {
			remoteException.printStackTrace();
			throw remoteException;
		} catch (Exception exception) {
			throw exception;
		}

		return status;
	}

	/**
	 * Method to get daily status report
	 * 
	 * @param fromdate
	 * @param todate
	 * @return
	 * @throws Exception
	 */
	public DailyStatusDTO[] getReport(Date fromdate, Date todate)
			throws Exception {
		try {
			Reporting reportingRemote = getReportingBean();
			status = reportingRemote.getReport(fromdate, todate, BeanUtil.getDbName());

		} catch (RemoteException remoteException) {
			remoteException.printStackTrace();
			throw remoteException;
		} catch (Exception exception) {
			throw exception;
		}

		return status;
	}

	/**
	 * Method to send a mail
	 * 
	 * @param data
	 * @param file_location
	 * @param hashMap
	 * @throws JRException
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public boolean sendMessage(ArrayList data, String file_location,
			HashMap hashMap) throws JRException, Exception {
		boolean sent = false;
		try {
			JasperReport jasperReport;
			JasperPrint jasperPrint;

			InputStream url = BeanUtil.class.getClassLoader()
					.getResourceAsStream(file_location);
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(data);
			jasperReport = JasperCompileManager.compileReport(url);
			jasperPrint = JasperFillManager.fillReport(jasperReport, hashMap,
					ds);

			JasperExportManager.exportReportToPdfFile(jasperPrint,
					"lib/DailyStatus.pdf");

			String curDir = System.getProperty("user.dir");
			String dirLoc = curDir + "/lib/DailyStatus.pdf";
			File f = new File(dirLoc);
			if (f.exists()) {
				AkrEmail akremail = new AkrEmail();
				sent = akremail.sendMail("akr.arun@gmail.com",
						"DailyStatus.pdf", dirLoc);
			} else {
				System.out.println("File not Exists");
			}

		} catch (JRException exception) {
			throw exception;
		} catch (Exception exception) {
			throw exception;
		}
		
		return sent;
	}

	/**
	 * Method to get Messages
	 * 
	 * @param station
	 * @return
	 * @throws Exception
	 */
	public MsgDTO[] getMessage(String station) throws Exception {
		MsgDTO[] msg = null;
		try {		
			Security secRemote = getSecurityBean();
			msg = secRemote.getMessage(station);
		} catch (Exception exception) {
			throw exception;
		}
		return msg;
	}

	/**
	 * Method to get Sundry Details
	 * 
	 * @param stationcode
	 * @param isActingStation
	 * @return
	 * @throws Exception
	 */
	public RegularSundryDTO getSundryDetails(String stationcode,
			boolean isActingStation) throws Exception {

		if ((null == sundrydto && isActingStation == false)
				|| (null != sundrydto && isActingStation == true)) {
			System.out.println("Getting Sundry details in Bean Util");
			Sundry sundryRemote = getSundryBean();
			sundrydto = sundryRemote.getRegularSundrySettings(stationcode);

		}
		return sundrydto;
	}

	/**
	 * Method to get Distance List
	 * 
	 * @param stationcode
	 * @param isActingStation
	 * @return
	 * @throws RemoteException
	 * @throws NamingException
	 * @throws CreateException
	 * @throws Exception
	 */
	public DistanceListDTO[] getDistanceList(String stationcode,
			boolean isActingStation) throws RemoteException, NamingException,
			CreateException, Exception {

		if ((null == distancedto && isActingStation == false)
				|| (null != distancedto && isActingStation == true)) {
			Station stationRemote = getStationBean();
			distancedto = stationRemote.getDistanceList(stationcode);
		}

		return distancedto;
	}

	/**
	 * Method to All Customers
	 * 
	 * @param fromstation
	 * @param tostation
	 * @return
	 * @throws Exception
	 */
	public CustomerDTO[] getCustomers(String fromstation,
			boolean isActingStation) throws Exception {

		if ((null == customerdto && isQuotationTaken == false)
				|| isActingStation == true) {
			// System.out.println("Getting Customer Quotation");
			Customer customerRemote = getCustomerBean();
			customerdto = customerRemote.getCustomers(fromstation);

			isQuotationTaken = true;
		}

		return customerdto;
	}

	/*
	 * public CustomerDTO[] getCustomerDetails() { // if(initialContactsDTO ==
	 * null){
	 * 
	 * try { if (null == contactsDTO) { System.out.println("Getting Customer
	 * details"); Customer customerRemote = getCustomerBean(); contactsDTO =
	 * customerRemote.getCustomerDetails(); } } catch (Exception exception) {
	 * exception.printStackTrace(); } // }
	 * 
	 * return contactsDTO; }
	 */

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public double[] getMeasurements() throws Exception {

		Reporting reportingRemote = getReportingBean();
		if (null != reportingRemote) {
			if (null == units)
				units = reportingRemote.getMeasurements();
		}
		return units;
	}

	/**
	 * Method to get Special Sundry details
	 * 
	 * @param stationcode
	 * @return
	 * @throws Exception
	 */
	public SpecialSundryDTO[] getSpecialSundryDetails() throws Exception {
		if (null == specialdto) {
			Sundry sundryRemote = getSundryBean();
			specialdto = sundryRemote.getSpecialSundrySettings();
		}
		return specialdto;
	}

	/**
	 * Method to get quotation details
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public ClientQuotationDTO getQuotationDetails(String quotationID,
			String tostation) throws Exception {
		ClientQuotationDTO dto = null;
		Quotation quotationRemote = getQuotationBean();
		dto = quotationRemote.getQuotationDetails(quotationID, tostation);
		return dto;
	}

	/**
	 * Method to get Booked LR
	 * 
	 * @param fromDate
	 * @param toDate
	 * @param branchCode
	 * @throws Exception
	 */
	public BookedLRDTO[] getBookedLRs(Date fromDate, Date toDate,
			String branchCode) throws Exception {
		BookedLRDTO[] bookedLRDto = null;
		try {
			Reporting reportingRemote = getReportingBean();
			bookedLRDto = reportingRemote.getBookedLRs(fromDate, toDate,
					branchCode, BeanUtil.getDbName());
		} catch (RemoteException remoteException) {
			remoteException.printStackTrace();
			throw remoteException;
		} catch (Exception exception) {
			throw exception;
		}

		return bookedLRDto;
	}

	/**
	 * Method to get Transhipment details
	 * 
	 * @param fromDate
	 * @param toDate
	 * @param branchCode
	 * @throws Exception
	 */
	public TranshipmentDTO[] getTranshipment(int month, int year,
			String branchCode) throws Exception {
		TranshipmentDTO[] dto = null;
		try {
			Reporting reportingRemote = getReportingBean();
			dto = reportingRemote.getTranshipment(month, year, branchCode, BeanUtil.getDbName());
		} catch (RemoteException remoteException) {
			remoteException.printStackTrace();
			throw remoteException;
		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}

	/**
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public BookingCommissionDTO[] getBookingCommissionList(Date date,
			boolean refresh) throws Exception {
		if ((null == currentBCommDTO) || (null != currentBCommDTO && refresh)) {
			try {
				Commission commRemote = getCommisionBean();
				String stationCode = getActingStationCode();
				if (null != date)
					currentBCommDTO = commRemote.getLRWithCommission(
							stationCode, date,BeanUtil.getDbName());
				else
					currentBCommDTO = commRemote
							.getLRWithCommission(stationCode,BeanUtil.getDbName());

			} catch (Exception exception) {
				throw exception;
			}

		}

		return currentBCommDTO;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		BeanUtil.isValid = isValid;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		BeanUtil.isAdmin = isAdmin;
	}

	public boolean isAdminHead() {
		return isAdminHead;
	}

	public void setAdminHead(boolean isAdminHead) {
		BeanUtil.isAdminHead = isAdminHead;
	}

	public float getClientRights() {
		return clientRights;
	}

	public void setClientRights(float clientRights) {
		BeanUtil.clientRights = clientRights;
	}
	
	public static String getDbName() {
		return dbName;
	}

	public static void setDbName(String dbName) {
		BeanUtil.dbName = dbName;
	}

	public static String getDbYear() {
		return dbYear;
	}

	public static void setDbYear(String dbYear) {
		BeanUtil.dbYear = dbYear;
	}

	
	public static Date getThree_month_updated() {
		return three_month_updated;
	}

	/**
	 * @param three_month_updated the three_month_updated to set
	 */
	public static void setThree_month_updated(Date three_month_updated) {
		BeanUtil.three_month_updated = three_month_updated;
	}
    /**
	 * 
	 * @param macId
	 * @param stationCode
	 * @param version
	 * @return
	 * @throws Exception
	 */
	public boolean isValidMachine(String macId, String stationCode,
			String version) throws Exception {
		if (null == validation) {
			try {
				Security securityRemote = getSecurityBean();
				// isValid = securityRemote.isValidMachine(macId, stationCode,
				// version);
				validation = new ValidationDTO();
				this.setValid(isValid);

			} catch (RemoteException remoteException) {
				throw remoteException;
			} catch (Exception exception) {
				throw exception;
			}
		}
		return isValid;
	}

	/**
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public DeliveryCommissionDTO[] getDeliveryCommissionList(Date date,
			boolean refresh) throws Exception {

		if ((null == currentDCommDTO) || (null != currentDCommDTO && refresh)) {
			try {
				Commission commRemote = getCommisionBean();
				String stationCode = getActingStationCode();
				if (null != date)
					currentDCommDTO = commRemote.getDeliveryCommissionList(
							stationCode, date,BeanUtil.getDbName());
				else
					currentDCommDTO = commRemote
							.getDeliveryCommissionList(stationCode,BeanUtil.getDbName());

			} catch (Exception exception) {
				throw exception;
			}

		}
		return currentDCommDTO;
	}

	/**
	 * 
	 * @param date
	 * @return recoveryDTO
	 * @throws Exception
	 */
	public RefundRecoveryDTO[] getRecoveryList(Date date, boolean refresh)
			throws Exception {

		if ((null == currentRecoveryDTO)
				|| (null != currentRecoveryDTO && refresh)) {
			try {
				Commission commRemote = getCommisionBean();
				String stationCode = getActingStationCode();
				if (null != date)
					currentRecoveryDTO = commRemote.getRecoveryDetailList(
							stationCode, date, BeanUtil.getDbName());
				else
					currentRecoveryDTO = commRemote
							.getRecoveryDetailList(stationCode,BeanUtil.getDbName());

			} catch (Exception exception) {
				throw exception;
			}

		}

		return currentRecoveryDTO;
	}

	/**
	 * Method to get Refund details
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public RefundRecoveryDTO[] getRefundList(Date date, boolean refresh)
			throws Exception {

		if ((null == currentRefundDTO) || (null != currentRefundDTO && refresh)) {
			try {
				Commission commRemote = getCommisionBean();
				String stationCode = getActingStationCode();
				if (null != date)
					currentRefundDTO = commRemote.getRefundDetailList(
							stationCode, date, BeanUtil.getDbName());
				else
					currentRefundDTO = commRemote
							.getRefundDetailList(stationCode,BeanUtil.getDbName());

			} catch (Exception exception) {
				throw exception;
			}

		}

		return currentRefundDTO;
	}

	/**
	 * Method to Outstanding Report
	 * 
	 * @param stationcode
	 * @param date
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public BookingDTO[] getOutstandingReport(String stationcode, Date date)
			throws RemoteException, Exception {
		BookingDTO[] dto = null;
		try {
			Reporting reportingRemote = getReportingBean();
			dto = reportingRemote.getOutstandingDetails(stationcode, date);

		} catch (RemoteException remoteException) {
			remoteException.printStackTrace();
			throw remoteException;
		} catch (Exception exception) {
			throw exception;
		}

		return dto;

	}

	/**
	 * Method to get Stock Information
	 * 
	 * @param stationcode
	 * @return
	 * @throws Exception
	 */
	public ArrayList getStockDetails(String stationcode) throws Exception {

		ArrayList list = null;
		try {
			Goods goodsRemote = getGoodsBean();
			list = goodsRemote.getStockDetails(stationcode);

		} catch (RemoteException remoteException) {
			remoteException.printStackTrace();
			throw remoteException;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}

		return list;
	}

	/**
	 * @return the isAdminHeadStationary
	 */
	public boolean isAdminHeadStationary() {
		return isAdminHeadStationary;
	}

	/**
	 * @param isAdminHeadStationary
	 *            the isAdminHeadStationary to set
	 */
	public void setAdminHeadStationary(boolean isAdminHeadStationary) {
		BeanUtil.isAdminHeadStationary = isAdminHeadStationary;
	}

	/**
	 * @return the isAdminHeadStationary
	 */
	public boolean isSecondThread() {
		return isSecondThread;
	}

	/**
	 * @param isAdminHeadStationary
	 *            the isAdminHeadStationary to set
	 */
	public void setSecondThread(boolean isSecondThread) {
		BeanUtil.isSecondThread = isSecondThread;
	}

	/**
	 * @return the isAdminHeadStationary
	 */
	public boolean isDiffStation() {
		return isDiffStation;
	}

	/**
	 * @param isAdminHeadStationary
	 *            the isAdminHeadStationary to set
	 */
	public void setDiffStation(boolean isDiffStation) {
		BeanUtil.isDiffStation = isDiffStation;
	}

	/**
	 * Method to get all version details
	 * 
	 * @return
	 * @throws Exception
	 */
	public VersionDTO[] getVersionDetails() throws Exception {
		VersionDTO[] dto = null;
		try {

			Reporting reportingRemote = getReportingBean();
			dto = reportingRemote.getVersionReports();

		} catch (RemoteException remoteException) {
			remoteException.printStackTrace();
			throw remoteException;
		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}

	/**
	 * Method to get CartageCommissionList
	 * 
	 * @param fromDate
	 * @param toDate
	 * @param branchCode
	 * @throws Exception
	 */
	public CartageCommissionDTO[] getCartageCommissionList(Date date,
			boolean refresh) throws Exception {

		if ((null == currentCCommDTO) || (null != currentCCommDTO && refresh)) {
			try {
				Commission commRemote = getCommisionBean();
				String stationCode = getActingStationCode();
				if (null != date)
					currentCCommDTO = commRemote.getCCCommissionList(
							stationCode, date,BeanUtil.getDbName());
				else
					currentCCommDTO = commRemote
							.getCCCommissionList(stationCode,BeanUtil.getDbName());

			} catch (Exception exception) {
				throw exception;
			}

		}
		return currentCCommDTO;
	}

	/**
	 * 
	 * @param fromDate
	 * @param toDate
	 * @param branchCode
	 * @throws Exception
	 */
	public CommissionSummaryDTO getCommisionSummary(Date date, boolean refresh)
			throws Exception {

		if ((null == currentSummaryDTO)
				|| (null != currentSummaryDTO && refresh)) {
			try {
				Commission commRemote = getCommisionBean();
				String stationCode = getActingStationCode();
				if (null != date) {
					currentSummaryDTO = commRemote.getCommissionSummary(
							stationCode, date,BeanUtil.getDbName());

				}

				else {
					currentSummaryDTO = commRemote
							.getCommissionSummary_month(stationCode,BeanUtil.getDbName());

				}

			} catch (Exception exception) {
				throw exception;
			}

		}
		return currentSummaryDTO;
	}

	/**
	 * 
	 * @param code
	 * @return
	 */
	public CurrentStatusDTO[] getCurrentStatusReport(String code)
			throws RemoteException, Exception {
		CurrentStatusDTO[] dto = null;
		try {
			Reporting reportingRemote = getReportingBean();
			dto = reportingRemote.getCurrentStatus(code);
		} catch (RemoteException remoteException) {
			remoteException.printStackTrace();
			throw remoteException;
		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}

	/**
	 * 
	 * @return
	 */
	
	


	
	
	public String getServerDate() {
		try {
			DateFormat timeFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date local = new Date();
			Date remote = timeFormat.parse(serverDate);
			if (!local.equals(remote)) {
				remote = timeFormat.parse(getServerDateTime());
				setServerDate(timeFormat.format(remote));
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return serverDate;
	}

	public CustomerDTO[] getBillingCustomers() throws Exception {

		CustomerDTO[] billingCustomers = null;
		Customer customerRemote = getCustomerBean();
		billingCustomers = customerRemote.getCustomersByPrivillage(1);

		return billingCustomers;
	}

	/**
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public BookingCommissionDTO[] getBookingCommissionList(Date date)
			throws Exception {
		try {
			Commission commRemote = getCommisionBean();
			String stationCode = getActingStationCode();
			if (null != date)
				currentBCommDTO = commRemote.getLRWithCommission(stationCode,
						date,BeanUtil.getDbName());
			else
				currentBCommDTO = commRemote.getLRWithCommission(stationCode,BeanUtil.getDbName());

		} catch (Exception exception) {
			throw exception;
		}

		return currentBCommDTO;
	}


	public BookingCommissionDTO[] getBookingCommissionListHistory(Date date)
	throws Exception {
		try {
			Commission commRemote = getCommisionBean();
			String stationCode = getActingStationCode();

			currentBCommDTO = commRemote.getLRWithCommissionHistory(stationCode,date, BeanUtil.getDbName());

		} catch (Exception exception) {
			throw exception;
		}

		return currentBCommDTO;
	}

	
	/**
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public DeliveryCommissionDTO[] getDeliveryCommissionList(Date date)
			throws Exception {

		try {
			Commission commRemote = getCommisionBean();
			String stationCode = getActingStationCode();
			if (null != date)
				currentDCommDTO = commRemote.getDeliveryCommissionList(
						stationCode, date,BeanUtil.getDbName());
			else
				currentDCommDTO = commRemote
						.getDeliveryCommissionList(stationCode,BeanUtil.getDbName());

		} catch (Exception exception) {
			throw exception;
		}

		return currentDCommDTO;
	}
	
	public DeliveryCommissionDTO[] getDeliveryCommissionListHistory(Date date)
	throws Exception {

		try {
			Commission commRemote = getCommisionBean();
			String stationCode = getActingStationCode();

			currentDCommDTO = commRemote.getDeliveryCommissionListHistory(
					stationCode, date, BeanUtil.getDbName());

		} catch (Exception exception) {
			throw exception;
		}

		return currentDCommDTO;
	}

	/**
	 * 
	 * @param date
	 * @return recoveryDTO
	 * @throws Exception
	 */
	public RefundRecoveryDTO[] getRecoveryList(Date date) throws Exception {
		try {
			Commission commRemote = getCommisionBean();
			String stationCode = getActingStationCode();
			if (null != date)
				currentRecoveryDTO = commRemote.getRecoveryDetailList(
						stationCode, date,BeanUtil.getDbName());
			else
				currentRecoveryDTO = commRemote
						.getRecoveryDetailList(stationCode,BeanUtil.getDbName());

		} catch (Exception exception) {
			throw exception;
		}

		return currentRecoveryDTO;
	}

	
	/**
	 * Method to get Refund details
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public RefundRecoveryDTO[] getRefundList(Date date) throws Exception {

		try {
			Commission commRemote = getCommisionBean();
			String stationCode = getActingStationCode();
			if (null != date)
				currentRefundDTO = commRemote.getRefundDetailList(stationCode,
						date, BeanUtil.getDbName());
			else
				currentRefundDTO = commRemote.getRefundDetailList(stationCode,BeanUtil.getDbName());

		} catch (Exception exception) {
			throw exception;
		}

		return currentRefundDTO;
	}

	/**
	 * Method to get CartageCommissionList
	 * 
	 * @param fromDate
	 * @param toDate
	 * @param branchCode
	 * @throws Exception
	 */
	public CartageCommissionDTO[] getCartageCommissionList(Date date)
			throws Exception {

		try {
			Commission commRemote = getCommisionBean();
			String stationCode = getActingStationCode();
			if (null != date)
				currentCCommDTO = commRemote.getCCCommissionList(stationCode,
						date,BeanUtil.getDbName());
			else
				currentCCommDTO = commRemote.getCCCommissionList(stationCode,BeanUtil.getDbName());

		} catch (Exception exception) {
			throw exception;
		}

		return currentCCommDTO;
	}
	
	public CartageCommissionDTO[] getCartageCommissionListHistory(Date date)
	throws Exception {

		try {
			Commission commRemote = getCommisionBean();
			String stationCode = getActingStationCode();

			currentCCommDTO = commRemote.getCCCommissionListHistory(stationCode, date, BeanUtil.getDbName());

		} catch (Exception exception) {
			throw exception;
		}

		return currentCCommDTO;
	}

	/**
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public DDDetailsDTO[] getDDDetails(Date date) throws Exception {
		DDDetailsDTO[] ddDetails = null;
		try {
			Commission commRemote = getCommisionBean();
			String stationCode = getActingStationCode();
			ddDetails = commRemote.getDDDetailsList(stationCode, date,BeanUtil.getDbName());
		} catch (Exception exception) {
			throw exception;
		}

		return ddDetails;
	}
	
	public DDDetailsDTO[] getDDDetailsHistory(Date date) throws Exception {
		DDDetailsDTO[] ddDetails = null;
		try {
			Commission commRemote = getCommisionBean();
			String stationCode = getActingStationCode();
			ddDetails = commRemote.getDDDetailsListHistory(stationCode, date,BeanUtil.getDbName());
		} catch (Exception exception) {
			throw exception;
		}

		return ddDetails;
	}

	/**
	 * 
	 * @param fromDate
	 * @param toDate
	 * @param branchCode
	 * @throws Exception
	 */
	public CommissionSummaryDTO getCommisionSummary(Date date) throws Exception {

		try {
			Commission commRemote = getCommisionBean();
			String stationCode = getActingStationCode();
			if (null != date) {
				currentSummaryDTO = commRemote.getCommissionSummary(
						stationCode, date,BeanUtil.getDbName());

			}

			else {
				currentSummaryDTO = commRemote
						.getCommissionSummary_month(stationCode,BeanUtil.getDbName());

			}

		} catch (Exception exception) {
			throw exception;
		}

		return currentSummaryDTO;
	}
	
	public CommissionSummaryDTO getCommisionSummaryHistory(Date date) throws Exception {
		try {
			Commission commRemote = getCommisionBean();
			String stationCode = getActingStationCode();

			System.out.println("-->"+BeanUtil.getDbName());
			currentSummaryDTO = commRemote.getCommissionSummaryHistory(
						stationCode, date, BeanUtil.getDbName());

		} catch (Exception exception) {
			throw exception;
		}
		
		return currentSummaryDTO;
	}

	/**
	 * 
	 * @param stnCode
	 * @return
	 * @throws Exception
	 */
	public String getPassword(String stnCode) throws Exception {
		Commission commRemote = getCommisionBean();
		String pwd = null;
		try {
			pwd = commRemote.getPassword(stnCode);
		} catch (Exception exception) {
			throw exception;
		}
		return pwd;
	}

	/**
	 * 
	 * @param stnCode
	 * @param pwd
	 * @throws Exception
	 */
	public void setPassword(String stnCode, String pwd) throws Exception {
		Commission commRemote = getCommisionBean();
		try {
			commRemote.setPassword(stnCode, pwd);
		} catch (Exception exception) {
			throw exception;
		}
	}

	public CustomerDTO[] populateCustomersFromRemote(boolean isActingStation) {
		if (initialContactsDTO == null || isActingStation) {
			try {
				Customer customerRemote = getCustomerBean();
				initialContactsDTO = customerRemote
						.getAllSundryCustomers(stationCode);

			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}

		return initialContactsDTO;
	}

	/**
	 * 
	 * @param customer
	 */
	/*
	 * public void addCustomerInDTO(CustomerDTO customer) {
	 * 
	 * initialContactsDTO = populateCustomersFromRemote();
	 * 
	 * if (null != initialContactsDTO) { int len = initialContactsDTO.length; if
	 * (len > 0) { for(int j = 0; j < len; j++){ }
	 * 
	 * CustomerDTO[] temp = new CustomerDTO[len + 1]; for (int i = 0; i <
	 * temp.length - 1; i++) { temp[i] = initialContactsDTO[i]; }
	 * 
	 * 
	 * temp[temp.length - 1] = customer; initialContactsDTO = temp; } } }
	 */
	/**
	 * 
	 * @param customer
	 */
	public void addCustomerInDTO(CustomerDTO customer) {

		initialContactsDTO = populateCustomersFromRemote(false);

		if (null != initialContactsDTO) {
			int len = initialContactsDTO.length;
			if (len > 0) {
				if (!isUpdateCustomer(customer)) {
					CustomerDTO[] temp = new CustomerDTO[len + 1];
					for (int i = 0; i < temp.length - 1; i++) {
						temp[i] = initialContactsDTO[i];
					}
					temp[temp.length - 1] = customer;
					initialContactsDTO = temp;
				}
			}

		}
	}

	/**
	 * 
	 * @param customer
	 * @return
	 */
	private boolean isUpdateCustomer(CustomerDTO customer) {
		for (int i = 0; i < initialContactsDTO.length; i++) {
			if (initialContactsDTO[i].getName().equalsIgnoreCase(
					customer.getName())) {
				initialContactsDTO[i] = customer;
				return true;
			}
		}
		return false;

	}

	/**
	 * 
	 * @param name
	 */
	public void deleteCustomerInDTO(String name) {

		initialContactsDTO = populateCustomersFromRemote(false);

		if (null != initialContactsDTO) {
			int len = initialContactsDTO.length;
			if (len > 0) {
				CustomerDTO[] temp = new CustomerDTO[len - 1];
				int index = 0;
				for (int i = 0; i < len; i++) {
					if (!initialContactsDTO[i].getName().equalsIgnoreCase(name)) {
						temp[index] = initialContactsDTO[i];
						index++;
					}
				}
				initialContactsDTO = temp;
			}

		}
	}

	/**
	 * 
	 * @param station_code
	 * @return
	 */
	public boolean isBranch() {
		try {
			StationsDTO[] dto = getAvailableStations();
			if (null != dto) {
				int len = dto.length;
				for (int i = 0; i < len; i++) {
					if (getActingStationCode().equalsIgnoreCase(dto[i].getId())
							&& getActingStationCode().equalsIgnoreCase(
									dto[i].getBranch_code()))
						return true;
				}

			}
		} catch (Exception exception) {

		}

		return false;
	}

	/**
	 * 
	 * @param station_code
	 * @return
	 * @throws Exception
	 */
	public CardRateProfileDTO[] getCardRateProfile() throws Exception {

		if (null == cardRateProfileDTO) {
			try {
				Station station = getStationBean();
				String station_code = getActingStationCode();
				cardRateProfileDTO = station.getCardRateProfile(station_code);

			} catch (Exception exception) {
				exception.printStackTrace();
				throw exception;
			}
		}

		return cardRateProfileDTO;
	}

	/**
	 * 
	 * @return
	 */
	public CCProfileDTO[] getCCDetails() throws Exception {

		if (null == ccProfileDTO) {
			try {
				Station station = getStationBean();
				String station_code = getActingStationCode();
				ccProfileDTO = station.getCCDetails(station_code);

			} catch (Exception exception) {
				exception.printStackTrace();
				throw exception;
			}
		}

		return ccProfileDTO;
	}

	public void updateMsgDownload(int msgId) {
		try {
			Security securityRemote = getSecurityBean();
			securityRemote.updateDownloaded(msgId);
		} catch (Exception e) {

		}

	}

	/**
	 * 
	 * @return
	 */
	public DCProfileDTO[] getDCDetails() throws Exception {

		if (null == dcProfileDTO) {
			try {
				Station station = getStationBean();
				String station_code = getActingStationCode();
				dcProfileDTO = station.getDCDetails(station_code);

			} catch (Exception exception) {
				exception.printStackTrace();
				throw exception;
			}
		}

		return dcProfileDTO;
	}

	/**
	 * 
	 * @param customer
	 * @return
	 * @throws Exception
	 */
	public float getRefundPerArticleForHLC(String customer) throws Exception {
		try {
			Station station = getStationBean();
			if (null != station) {
				String station_code = getActingStationCode();
				return station
						.getRefundPerArticleForHLC(customer, station_code);
			}

		} catch (Exception exception) {
			throw exception;
		}
		return 0;
	}
	
	
	public GMROutTimeDTO[] getInwardRegister(Date fromDate, Date toDate, String branchCode, String stnCode) throws Exception {
		GMROutTimeDTO[] inward = null;
		Reporting reportingRemote = getReportingBean();
		inward = reportingRemote.getInwardRegister(fromDate, toDate,branchCode,stnCode,BeanUtil.getDbName());
		
		return inward;
	}

public GMROutTimeDTO[] getInwardRegisterHistory(Date fromDate, Date toDate, String branchCode, String stnCode) throws Exception {
		GMROutTimeDTO[] inward = null;
		Reporting reportingRemote = getReportingBean();
		inward = reportingRemote.getInwardRegisterHistory(fromDate, toDate,branchCode,stnCode,BeanUtil.getDbName());
		
		return inward;
	}

		//Reports
	public GeneralSummaryDTO[] getSundryBooking(Date fromDate, Date toDate,String branch, String station)
	throws Exception {
		GeneralSummaryDTO[] sundryBooking = null;
		try {
		Reporting reporting = getReportingBean();
		sundryBooking = reporting
				.getSundryBookingAnalysis(fromDate, toDate,branch, station, BeanUtil.getDbName());
		} catch (RemoteException remoteexception) {
		throw remoteexception;
		} catch (Exception exception) {
		throw exception;
		}
		
		return sundryBooking;
	}
	
	public GeneralSummaryDTO[] getSundryBookingHistory(Date fromDate, Date toDate,String branch, String station)
	throws Exception {
		GeneralSummaryDTO[] sundryBooking = null;
		try {
		Reporting reporting = getReportingBean();
		sundryBooking = reporting
				.getSundryBookingAnalysisHistory(fromDate, toDate,branch, station, BeanUtil.getDbName());
		} catch (RemoteException remoteexception) {
		throw remoteexception;
		} catch (Exception exception) {
		throw exception;
		}
		
		return sundryBooking;
	}
	
	public MissingCustomersDTO[] getMissingCustomers() throws Exception {
		MissingCustomersDTO[] mc = null;
		Reporting reportingRemote = getReportingBean();
		mc = reportingRemote.getMissingCustomers();

		return mc;
	}

	public MissingCustomersDTO[] getMissingCustomersLastYear() throws Exception {
		MissingCustomersDTO[] mcLastYear = null;
		Reporting reportingRemote = getReportingBean();
		mcLastYear = reportingRemote.getMissingCustomersLastYear();

		return mcLastYear;
	}
	
	public CustomerBusinessAnalysisDTO[] getIIStates(Date fromDate, Date toDate)
	throws Exception {
		CustomerBusinessAnalysisDTO[] iis = null;
		Reporting reportingRemote = getReportingBean();
		iis = reportingRemote.getInterIntraState(fromDate, toDate, BeanUtil.getDbName());
		
		return iis;
	}

	public CustomerBusinessAnalysisDTO[] getIIStatesHistory(Date fromDate, Date toDate)
	throws Exception {
		CustomerBusinessAnalysisDTO[] iis = null;
		Reporting reportingRemote = getReportingBean();
		iis = reportingRemote.getInterIntraStateHistory(fromDate, toDate, BeanUtil.getDbName());
		
		return iis;
	}
	
	public GeneralSummaryDTO[] getDailyBooking(Date fromDate, Date toDate)
	throws Exception {
		GeneralSummaryDTO[] bookedLr = null;
		try {
			Reporting reporting = getReportingBean();
			bookedLr = reporting.getDailyBookingAnalysis(fromDate, toDate, BeanUtil.getDbName());
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
		
		return bookedLr;
	}

	public GeneralSummaryDTO[] getDailyBookingHistory(Date fromDate, Date toDate)
	throws Exception {
		GeneralSummaryDTO[] bookedLr = null;
		try {
		Reporting reporting = getReportingBean();
		bookedLr = reporting.getDailyBookingAnalysisHistory(fromDate, toDate, BeanUtil.getDbName());
		} catch (RemoteException remoteexception) {
		throw remoteexception;
		} catch (Exception exception) {
		throw exception;
		}
		
		return bookedLr;
	}
	
	public BookingDTO[] getBPAReport(Date from, Date to,
			String branch) {
		BookingDTO[] bpaReport = null;
		try {
			Reporting reportingRemote = getReportingBean();
			bpaReport = reportingRemote.getBPAReport(from, to, branch, BeanUtil.getDbName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bpaReport;
	}
	
	public BookingDTO[] getBPAReportHistory(Date from, Date to,
			String branch) {
		BookingDTO[] bpaReport = null;
		try {
			Reporting reportingRemote = getReportingBean();
			bpaReport = reportingRemote.getBPAReportHistory(from, to, branch, BeanUtil.getDbName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bpaReport;
	}
	
	public StationsDTO[] getServicetaxHistory(Date fromDt, Date toDate)throws Exception {
		// TODO Auto-generated method stub
		StationsDTO[] servicetax = null;
		try
		{
			Reporting reporting =getReportingBean();
			servicetax = reporting.getServicetaxHistory(fromDt,toDate,BeanUtil.getDbName());
		}catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
		return servicetax;
	}

	public StationsDTO[] getServicetax(Date fromDt, Date toDate)throws Exception  {
		// TODO Auto-generated method stub
		StationsDTO[] servicetax = null;
		try
		{
			Reporting reporting =getReportingBean();
			servicetax = reporting.getServicetax(fromDt, toDate, BeanUtil.getDbName());
		}catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
		return servicetax;
	}

	public StationsDTO[] getServicetaxLRHistory(Date fromDt, Date toDate)throws Exception  {
		// TODO Auto-generated method stub
		StationsDTO[] servicetaxLR = null;
		try
		{
			Reporting reporting =getReportingBean();
			servicetaxLR = reporting.getServicetaxLRHistory(fromDt,toDate,BeanUtil.getDbName());
		}catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
		return servicetaxLR;
	}

	public StationsDTO[] getServicetaxLR(Date fromDt, Date toDate)throws Exception  {
		// TODO Auto-generated method stub
		StationsDTO[] servicetaxLR = null;
		try
		{
			Reporting reporting = getReportingBean();
			servicetaxLR = reporting.getServicetaxLR(fromDt,toDate,BeanUtil.getDbName());
		}catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
		return servicetaxLR;
		
	}
	
	public DDRDTO[] getDDReport(Date from, Date to, String station) {
		DDRDTO[] ddReport = null;
		try {
			Reporting reportingRemote = getReportingBean();
			ddReport = reportingRemote.getDDReport(from, to, station, BeanUtil.getDbName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ddReport;
	}
	
	public DDRDTO[] getDDReportHistory(Date from, Date to, String station) {
		DDRDTO[] ddReport = null;
		try {
			Reporting reportingRemote = getReportingBean();
			ddReport = reportingRemote.getDDReportHistory(from, to, station, BeanUtil.getDbName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ddReport;
	}

	public RemittanceShortageDTO[] getRSReport(String branch, int month,
			int year) {
		RemittanceShortageDTO[] rsReport = null;
		try {
			Reporting reportingRemote = getReportingBean();
			rsReport = reportingRemote.getRSReport(branch, month, year, BeanUtil.getDbName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rsReport;
	}
	
	public RemittanceShortageDTO[] getRSReportHistory(String branch, int month,
			int year) {
		RemittanceShortageDTO[] rsReport = null;
		try {
			Reporting reportingRemote = getReportingBean();
			rsReport = reportingRemote.getRSReportHistory(branch, month, year, BeanUtil.getDbName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rsReport;
	}
	
	public BookingDTO[] getUPDReady(String station, String branch,
			int inwardDays, boolean isMoreThan) throws Exception {
		BookingDTO[] updReady = null;
		try {
			Reporting reporting = getReportingBean();
			updReady = reporting.getUPDReady(station, branch, inwardDays,
					isMoreThan, BeanUtil.getDbName());
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}

		return updReady;
	}
	
	public BookingDTO[] getUPDReadyHistory(String station, String branch,
			int inwardDays, boolean isMoreThan) throws Exception {
		BookingDTO[] updReady = null;
		try {
			Reporting reporting = getReportingBean();
			updReady = reporting.getUPDReadyHistory(station, branch, inwardDays,
					isMoreThan, BeanUtil.getDbName());
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}

		return updReady;
	}

	
	public BookingDTO[] getUnDeliveredTopayDetailed(String branch, Date fromDate,
			Date toDate) throws Exception {
		BookingDTO[] utDto = null;
		try {
			Reporting reporting = getReportingBean();
			utDto = reporting.getTotalUnDeliveredTopayDetailed(branch, fromDate,
					toDate, BeanUtil.getDbName());
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}

		return utDto;
	}
	
	
	public BookingDTO[] getUnDeliveredTopayDetailedUnion(String branch, Date fromDate,
			Date toDate) throws Exception {
		BookingDTO[] utDto = null;
		try {
			Reporting reporting = getReportingBean();
			utDto = reporting.getTotalUnDeliveredTopayDetailedUnion(branch, fromDate,
					toDate, BeanUtil.getDbName());
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}

		return utDto;
	}

	
	public BookingDTO[] getUnDeliveredTopayDetailedHistory(String branch, Date fromDate,
			Date toDate) throws Exception {
		BookingDTO[] utDto = null;
		try {
			Reporting reporting = getReportingBean();
			utDto = reporting.getTotalUnDeliveredTopayDetailedHistory(branch, fromDate,
					toDate, BeanUtil.getDbName());
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}

		return utDto;
	}
	
	public GeneralSummaryDTO[] getUnDeliveredTopay(Date fromDate, Date toDate,
			String branch) throws Exception {
		GeneralSummaryDTO[] utDto = null;
		try {
			Reporting reporting = getReportingBean();
			utDto = reporting.getTotalUnDeliveredTopay(fromDate, toDate, branch, BeanUtil.getDbName());
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}

		return utDto;
	}
	
	public GeneralSummaryDTO[] getUnDeliveredTopayHistory(Date fromDate, Date toDate,
			String branch) throws Exception {
		GeneralSummaryDTO[] utDto = null;
		try {
			Reporting reporting = getReportingBean();
			utDto = reporting.getTotalUnDeliveredTopayHistory(fromDate, toDate, branch, BeanUtil.getDbName());
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}

		return utDto;
	}
	
	public GeneralSummaryDTO[] getCCChargeSummary(Date fromDate, Date toDate,
			String branch) throws Exception {
		GeneralSummaryDTO[] cccSummary = null;
		try {
			Reporting reporting = getReportingBean();
			cccSummary = reporting.getCCChargeSummary(fromDate, toDate, branch, BeanUtil.getDbName());
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}

		return cccSummary;
	}
	
	public GeneralSummaryDTO[] getCCChargeSummaryHistory(Date fromDate, Date toDate,
			String branch) throws Exception {
		GeneralSummaryDTO[] cccSummary = null;
		try {
			Reporting reporting = getReportingBean();
			cccSummary = reporting.getCCChargeSummaryHistory(fromDate, toDate, branch, BeanUtil.getDbName());
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}

		return cccSummary;
	}

	
	/**
	 * 
	 * @param fromDate
	 * @param toDate
	 * @param branch
	 * @return
	 * @throws Exception
	 */
	public GeneralSummaryDTO[] getCCCSummaryPDTP(Date fromDate, Date toDate,
			String branch) throws Exception {
		GeneralSummaryDTO[] cccSummary = null;
		try {
			Reporting reporting = getReportingBean();
			cccSummary = reporting.getCCCSummaryPDTPCount(fromDate, toDate,
					branch, BeanUtil.getDbName());
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}

		return cccSummary;
	}
	
	public GeneralSummaryDTO[] getCCCSummaryPDTPHistory(Date fromDate, Date toDate,
			String branch) throws Exception {
		GeneralSummaryDTO[] cccSummary = null;
		try {
			Reporting reporting = getReportingBean();
			cccSummary = reporting.getCCCSummaryPDTPCountHistory(fromDate, toDate,
					branch, BeanUtil.getDbName());
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}

		return cccSummary;
	}

	public DDRDTO[] getDDReportUnion(Date from, Date to, String station) {
		// TODO Auto-generated method stub
		DDRDTO[] ddReport = null;
		try {
			Reporting reportingRemote = getReportingBean();
			ddReport = reportingRemote.getDDReportUnion(from, to, station, BeanUtil.getDbName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ddReport;
	}

	public BookedLRDTO[] getBookedLRsHistory(Date fromDate, Date toDate,
			String branchCode) throws Exception		{
		// TODO Auto-generated method stub
		BookedLRDTO[] bookedLRDto = null;
		try {
			Reporting reportingRemote = getReportingBean();
			bookedLRDto = reportingRemote.getBookedLRsHistory(fromDate, toDate,
					branchCode, BeanUtil.getDbName());
		} catch (RemoteException remoteException) {
			remoteException.printStackTrace();
			throw remoteException;
		} catch (Exception exception) {
			throw exception;
		}

		return bookedLRDto;
	}

	public BookedLRDTO[] getBookedLRsUnion(Date fromDate, Date toDate,
			String branchCode) throws Exception{
		// TODO Auto-generated method stub
		BookedLRDTO[] bookedLRDto = null;
		try {
			Reporting reportingRemote = getReportingBean();
			bookedLRDto = reportingRemote.getBookedLRsUnion(fromDate, toDate,
					branchCode, BeanUtil.getDbName());
		} catch (RemoteException remoteException) {
			remoteException.printStackTrace();
			throw remoteException;
		} catch (Exception exception) {
			throw exception;
		}

		return bookedLRDto;
	}

	



}
