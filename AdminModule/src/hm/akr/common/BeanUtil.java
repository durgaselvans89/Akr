package hm.akr.common;

import hm.akr.dto.ArticleDTO;
import hm.akr.dto.BookingCommissionDTO;
import hm.akr.dto.BookingDTO;
import hm.akr.dto.BusinessPerformanceDTO;
import hm.akr.dto.CartageCommissionDTO;
import hm.akr.dto.CommissionSummaryDTO;
import hm.akr.dto.CustomerBusinessAnalysisDTO;
import hm.akr.dto.CustomerDTO;
import hm.akr.dto.DDDetailsDTO;
import hm.akr.dto.ServiceTaxDTO;
import hm.akr.dto.DDRDTO;
import hm.akr.dto.DailyStatusDTO;
import hm.akr.dto.DeliveryCommissionDTO;
import hm.akr.dto.DiscounterDTO;
import hm.akr.dto.DistanceExcelDecorator;
import hm.akr.dto.DistanceListDTO;
import hm.akr.dto.GeneralSummaryDTO;
import hm.akr.dto.InsuranceDTO;
import hm.akr.dto.MissingCustomersDTO;
import hm.akr.dto.MsgDTO;
import hm.akr.dto.ProfileDTO;
import hm.akr.dto.QuotationDTO;
import hm.akr.dto.RefundRecoveryDTO;
import hm.akr.dto.RegularSundryDTO;
import hm.akr.dto.RemittanceShortageDTO;
//import hm.akr.dto.ServiceTaxDTO;
import hm.akr.dto.SpecialSundryDTO;
import hm.akr.dto.StationsDTO;
import hm.akr.dto.VehicleDTO;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
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
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.view.JasperViewer;

/**
 * Bean Util class to get the context and the remote interface of the bean
 * 
 * @version 1.0
 */
public class BeanUtil {

	private static BeanUtil util = null;

	Properties properties = null;

	Context context = null;

	private static final String PROPERTY_FILE_NAME = "lib/akrAdmin.properties";

	private static final String INITIAL_CONTEXT_FACTORY = "InitialContextFactory";

	// private static final String PROVIDER_URL = "ProviderUrl";

	private static final String URL_PKG_PREFIXES = "UrlPrefix";

	private static final String STATION_CODE = "StationCode";

	private static final String STATION_NAME = "StationName";

	private static final String ADMIN_HEAD = "AdminHead";

	// private static final String SECURITY_BEAN_JNDI_NAME = "SecurityBeanJNDI";

	VehicleDTO[] vehicles = null;

	StationsDTO[] stations = null;

	ArticleDTO[] articles = null;

	CustomerDTO[] customerDetails = null;

	CustomerDTO customer = null;
	
	DailyStatusDTO[] status = null;

	FileOutputStream fo = null;

	private static String stationCode = null;

	private static String stationName = null;

	private static String serverDate = null;

	public static boolean isBPI_Changed = false;

	private static int adminRights = -1;

	public static int isSecondServer = 0;

	BookingCommissionDTO[] currentBCommDTO = null;

	DeliveryCommissionDTO[] currentDCommDTO = null;

	CartageCommissionDTO[] currentCCommDTO = null;

	RefundRecoveryDTO[] currentRecoveryDTO = null;

	RefundRecoveryDTO[] currentRefundDTO = null;

	CommissionSummaryDTO currentSummaryDTO = null;
	
	private static String dbName = "";
	
	private static String dbYear = "";
	
		private static Date three_month_updated = null;
	
	/**
	 * 
	 * @return
	 */
	public static BeanUtil getInstance() throws Exception {
		if (null == util || isSecondServer == 1) {
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
			if (isSecondServer == 0) {
				//getContext("122.165.219.48:1099");
				//getContext("122.165.57.163:1099");
				//getContext("192.168.0.22:1099");
				//getContext("115.115.65.122:1099");
				//getContext("192.168.1.222:1099");
				//getContext("115.115.65.122:1099");
				//getContext("182.72.199.30:1099");
				getContext("localhost:1099");
				//isSecondServer=1;
			} else if (isSecondServer == 1) {
				//isSecondServer = 2;		
				//getContext("122.165.219.48:1099");
				//getContext("192.168.0.104:1099");	
				//getContext("192.168.1.222:1099");
				//getContext("122.165.57.163:1099");
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
				ht.put(Context.INITIAL_CONTEXT_FACTORY, properties
						.getProperty(INITIAL_CONTEXT_FACTORY));
				ht.put(Context.PROVIDER_URL, serverIp);

				ht.put(Context.URL_PKG_PREFIXES, properties
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
	 * 
	 */
	public Goods getGoodsBean() throws NamingException, RemoteException,
			CreateException {
		/*
		 * Object ref =
		 * context.lookup(properties.getProperty(GMR_BEAN_JNDI_NAME)); GoodsHome
		 * home = (GoodsHome) PortableRemoteObject.narrow(ref, GoodsHome.class);
		 * return home.create();
		 */

		Goods goods = null;
		
		try {
			goods = (Goods) context.lookup("GoodsBean/remote");
		} catch (Exception exception) {

		}
		return goods;
	}

	/**
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

	public Vehicle getVehicleBean() throws NamingException, RemoteException,
			CreateException {
		Vehicle vehicle = null;

		try {
			vehicle = (Vehicle) context.lookup("VehicleBean/remote");
		} catch (Exception exception) {

		}
		return vehicle;
	}

	public Station getStationBean() throws NamingException, RemoteException,
			CreateException {
		Station station = null;

		try {
			station = (Station) context.lookup("StationBean/remote");
		} catch (Exception exception) {

		}
		return station;
	}

	/**
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

		}
		return security;
	}

	/**
	 * 
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
			exception.printStackTrace();
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
	 * 
	 * @return
	 */
	public String getActualStationCode() {
		return properties.getProperty(STATION_CODE);
	}

	/**
	 * 
	 * @return
	 */
	public String getCreatedBy() {
		String createdby = properties.getProperty(STATION_CODE);

		return createdby;
	}

	/**
	 * 
	 */
	public String getActingStationName() {
		if (null == stationName) {
			stationName = properties.getProperty(STATION_NAME);
		}
		return stationName;
	}

	/**
	 * 
	 * @return
	 */
	public String getActualStationName() {
		return properties.getProperty(STATION_NAME);
	}

	/**
	 * 
	 * @return
	 */
	public boolean isAdminHead() {
		boolean isAdminHead = false;
		String flag = properties.getProperty(ADMIN_HEAD);

		if (null != flag) {
			try {
				isAdminHead = Boolean.valueOf(flag);
			} catch (Exception exception) {

			}
		}
		return isAdminHead;
	}

	/**
	 * @throws IOException
	 * 
	 */

	public void setStationName(String StationNameValue) throws IOException {
		stationName = StationNameValue;
	}

	public void setStationCode(String StationCodeValue) throws IOException {
		stationCode = StationCodeValue;
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
	 * 
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

	public void exportToExcel(ArrayList data, String file_location,
			HashMap<String, String> hashMap) throws JRException, Exception {
		try {
			JasperReport jasperReport;
			JasperPrint jasperPrint;

			InputStream stream = BeanUtil.class.getClassLoader()
					.getResourceAsStream(file_location);
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(
					data, false);
			jasperReport = JasperCompileManager.compileReport(stream);
			jasperPrint = JasperFillManager.fillReport(jasperReport, hashMap,
					ds);

			//JasperPrintManager.printReport(jasperPrint, false);

			//new JRBeanCollectionDataSource(data, false);

			File output = new File("lib/QuotationRate.xls");
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

	public void exportToDistanceExcel(ArrayList<DistanceExcelDecorator> data,
			String file_location, HashMap<String, String> hashMap)
			throws JRException, Exception {
		try {
			JasperReport jasperReport;
			JasperPrint jasperPrint;

			InputStream stream = BeanUtil.class.getClassLoader()
					.getResourceAsStream(file_location);
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(
					data, false);
			jasperReport = JasperCompileManager.compileReport(stream);
			jasperPrint = JasperFillManager.fillReport(jasperReport, hashMap,
					ds);

			//JasperPrintManager.printReport(jasperPrint, false);

			//new JRBeanCollectionDataSource(data, false);

			File output = new File("lib/Distance.xls");
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

	/**
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
	 * To Refresh All stations
	 * 
	 * @throws Exception
	 */
	public void refreshAvailableStations() throws Exception {
		// System.out.println("refresh");
		stations = null;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public StationsDTO[] getAvailableStations() throws Exception {

		if (null == stations) {
			try {
				Station stationRemote = getStationBean();
				stations = stationRemote.getAllStations();
				// System.out.println("remote");
			} catch (RemoteException remoteException) {
				remoteException.printStackTrace();
				throw remoteException;
			} catch (Exception exception) {
				throw exception;
			}
		}

		return stations;
	}

	/**
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
				// AkrEmail akremail = new AkrEmail();
				// sent = akremail.sendMail("akr.arun@gmail.com",
				// "DailyStatus.pdf",dirLoc, "DailyStatus");
			} else {
				//System.out.println("File not Exists");
			}

		} catch (JRException exception) {
			throw exception;
		} catch (Exception exception) {
			throw exception;
		}

		return sent;
	}

	/**
	 * 
	 * @param station
	 * @return
	 * @throws Exception
	 */
	public MsgDTO[] getMessage(String station) throws Exception {
		MsgDTO[] msg = null;
		try {
			Security securityRemote = getSecurityBean();
			msg = securityRemote.getMessage(station);
		} catch (Exception exception) {
			throw exception;
		}

		return msg;
	}

	/* __________________________________________________________________________________________________ */

	/**
	 * 
	 * @param stationcodes
	 * @param bpi
	 * @throws Exception
	 */
	public void setBPI(String[] stationcodes, float bpi) throws Exception {

		try {
			Sundry sundryRemote = getSundryBean();
			sundryRemote.setBasePriceIndex(stationcodes, bpi);
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}

	}

	/**
	 * 
	 * @param stationcodes
	 * @param lrcharge
	 * @throws Exception
	 */
	public void setLRCharges(String[] stationcodes, float lrcharge)
			throws Exception {

		try {
			Sundry sundryRemote = getSundryBean();
			sundryRemote.setLRCharge(stationcodes, lrcharge);
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}

	}

	/**
	 * 
	 * @param stationcodes
	 * @param gsc
	 * @throws Exception
	 */
	public void setGSC(String[] stationcodes, float gsc) throws Exception {

		try {
			Sundry sundryRemote = getSundryBean();
			sundryRemote.setGSCCharge(stationcodes, gsc);
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}

	}

	/**
	 * 
	 * @param stationcodes
	 * @param bfincrement
	 * @throws Exception
	 */
	public void setBFIncrement(String[] stationcodes, int bfincrement)
			throws Exception {

		try {
			Station stationRemote = getStationBean();
			stationRemote.setBFIncrement(stationcodes, bfincrement);
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}

	}

	/**
	 * 
	 * @param stationcodes
	 * @param bfdecrement
	 * @throws Exception
	 */
	public void setBFDecrement(String[] stationcodes, int bfdecrement)
			throws Exception {

		try {
			Station stationRemote = getStationBean();
			stationRemote.setBFDecrement(stationcodes, bfdecrement);
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}

	}

	/**
	 * Method to get BPI values
	 * 
	 * @return
	 * @throws Exception
	 */
	public Float[] getBPI() throws Exception {
		Float[] bpi = null;
		try {
			Sundry sundryRemote = getSundryBean();
			bpi = sundryRemote.getDistinctBPI();
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
		return bpi;

	}

	/**
	 * Method to get pending BPI values
	 * 
	 * @return
	 * @throws Exception
	 */
	public Float[] getPendingBPI() throws Exception {
		Float[] bpi = null;
		try {
			Sundry sundryRemote = getSundryBean();
			bpi = sundryRemote.getPendingBPI();
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
		return bpi;

	}

	/**
	 * Method to get LR Charges
	 * 
	 * @return
	 * @throws Exception
	 */
	public Float[] getLRCharges() throws Exception {
		Float[] lrc = null;
		try {
			Sundry sundryRemote = getSundryBean();
			lrc = sundryRemote.getDistinctLRC();
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
		return lrc;

	}

	/**
	 * Method to get Pending LR Charges
	 * 
	 * @return
	 * @throws Exception
	 */
	public Float[] getPendingLRC() throws Exception {
		Float[] lrc = null;
		try {
			Sundry sundryRemote = getSundryBean();
			lrc = sundryRemote.getPendingLRC();
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
		return lrc;

	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public Float[] getGSC() throws Exception {
		Float[] gsc = null;
		try {
			Sundry sundryRemote = getSundryBean();
			gsc = sundryRemote.getDistinctGSC();
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
		return gsc;

	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public RegularSundryDTO[] getRegularSundryDetails() throws Exception {
		RegularSundryDTO[] sundry = null;
		try {
			Sundry sundryRemote = getSundryBean();
			sundry = sundryRemote.getRegularSundry_AllStation();
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
		return sundry;

	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public StationsDTO[] getBFIAndBFDDetails() throws Exception {
		StationsDTO[] dto = null;
		try {
			Station stationRemote = getStationBean();

			dto = stationRemote.getBFIncrementAndDiscount();
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
		return dto;

	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String[] getProfileNames() throws Exception {
		String[] names = null;
		try {
			Commission commisionRemote = getCommisionBean();

			names = commisionRemote.getDistinctProfile();
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
		return names;

	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public ProfileDTO[] getProfileDetails() throws Exception {
		ProfileDTO[] dto = null;
		try {
			Commission commisionRemote = getCommisionBean();

			dto = commisionRemote.getProfileList();
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
		return dto;

	}

	/**
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public String createProfile(ProfileDTO dto) throws Exception {
		String id = null;
		try {
			Commission commisionRemote = getCommisionBean();

			id = commisionRemote.createProfile(dto);
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
		return id;

	}

	public String createCCProfile(ProfileDTO dto) throws Exception {
		String id = null;
		try {
			Commission commisionRemote = getCommisionBean();

			id = commisionRemote.createCCProfile(dto);
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
		return id;

	}

	public String createDCProfile(ProfileDTO dto) throws Exception {
		String id = null;
		try {
			Commission commisionRemote = getCommisionBean();

			id = commisionRemote.createDCProfile(dto);
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
		return id;

	}

	public boolean isCCProfileExist(String profileName) throws Exception {
		boolean isExist = false;
		try {
			Commission commisionRemote = getCommisionBean();
			isExist = commisionRemote.isCCProfileExist(profileName);
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}

		return isExist;
	}

	public boolean isDCProfileExist(String profileName) throws Exception {
		boolean isExist = false;
		try {
			Commission commisionRemote = getCommisionBean();
			isExist = commisionRemote.isDCProfileExist(profileName);
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}

		return isExist;
	}

	public void deleteCCProfile(String profileName) throws Exception {
		try {
			Commission commisionRemote = getCommisionBean();
			commisionRemote.deleteCCProfile(profileName);
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
	}

	public void deleteDCProfile(String profileName) throws Exception {
		try {
			Commission commisionRemote = getCommisionBean();
			commisionRemote.deleteDCProfile(profileName);
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String[] getCCProfileNames() throws Exception {
		String[] names = null;
		try {
			Commission commisionRemote = getCommisionBean();

			names = commisionRemote.getDistinctCCProfile();
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
		return names;

	}

	public String[] getDCProfileNames() throws Exception {
		String[] names = null;
		try {
			Commission commisionRemote = getCommisionBean();

			names = commisionRemote.getDistinctDCProfile();
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
		return names;

	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public ProfileDTO[] getCCProfileDetails() throws Exception {
		ProfileDTO[] dto = null;
		try {
			Commission commisionRemote = getCommisionBean();

			dto = commisionRemote.getCCProfileList();

		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
		return dto;

	}

	public ProfileDTO[] getDCProfileDetails() throws Exception {
		ProfileDTO[] dto = null;
		try {
			Commission commisionRemote = getCommisionBean();

			dto = commisionRemote.getDCProfileList();

		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
		return dto;

	}

	public void setCCProfileCommision(String[] stations, String profilename)
			throws Exception {
		try {
			Commission commisionRemote = getCommisionBean();
			commisionRemote.setCCProfileCommission(stations, profilename);
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
	}

	public void setDCProfileCommision(String[] stations, String profilename)
			throws Exception {
		try {
			Commission commisionRemote = getCommisionBean();
			commisionRemote.setDCProfileCommission(stations, profilename);
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
	}

	public boolean isProfileExist(String profileName) throws Exception {
		boolean isExist = false;
		try {
			Commission commisionRemote = getCommisionBean();
			isExist = commisionRemote.isProfileExist(profileName);
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}

		return isExist;
	}

	/**
	 * 
	 * @param profileId
	 * @throws Exception
	 */
	public void deleteProfile(String profileId) throws Exception {
		try {
			Commission commisionRemote = getCommisionBean();
			commisionRemote.deleteProfile(profileId);
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
	}

	/**
	 * 
	 * @param oldbpi
	 * @param newbpi
	 * @param effectivedate
	 * @throws Exception
	 */
	public void setBPIUpdate(Float[] oldbpi, Float[] newbpi, Date effectivedate)
			throws Exception {

		try {
			Sundry sundryRemote = getSundryBean();
			sundryRemote.setBPI(oldbpi, newbpi, effectivedate);
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}

	}

	/**
	 * 
	 * @param oldlrc
	 * @param newlrc
	 * @param effectivedate
	 * @throws Exception
	 */
	public void setLRCUpdate(Float[] oldlrc, Float[] newlrc, Date effectivedate)
			throws Exception {

		try {
			Sundry sundryRemote = getSundryBean();
			sundryRemote.setLRC(oldlrc, newlrc, effectivedate);
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}

	}

	/**
	 * 
	 * @param oldgsc
	 * @param newgsc
	 * @param effectivedate
	 * @throws Exception
	 */
	public void setGSCUpdate(Float[] oldgsc, Float[] newgsc, Date effectivedate)
			throws Exception {

		try {
			Sundry sundryRemote = getSundryBean();
			sundryRemote.setGSC(oldgsc, newgsc, effectivedate);
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}

	}

	/**
	 * Method
	 * 
	 * @param dto
	 * @throws Exception
	 */
	public void setBFIAndBFD(StationsDTO[] dto) throws Exception {

		try {
			Station station = getStationBean();
			station.setManageSundrySetting(dto);

		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}

	}

	/**
	 * 
	 * @param dto
	 * @throws Exception
	 */
	public void updateSundry(RegularSundryDTO[] dto) throws Exception {

		try {
			Sundry sundry = getSundryBean();
			sundry.setManageSundrySettings(dto);

		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}

	}

	/**
	 * 
	 * @param rsDTO
	 * @throws Exception
	 */
	public void setGeneralSettings(RegularSundryDTO rsDTO) throws Exception {
		try {
			Sundry sundryRemote = getSundryBean();
			sundryRemote.setGeneralSetting(rsDTO);
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}

	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public SpecialSundryDTO[] getSpecialSundrySettings() throws Exception {

		SpecialSundryDTO[] ssDTO = null;
		try {
			Sundry sundryRemote = getSundryBean();
			ssDTO = sundryRemote.getSpecialSundrySettings();
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}

		return ssDTO;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public InsuranceDTO[] getInsuranceChargeList() throws Exception {

		InsuranceDTO[] icDTO = null;
		try {
			Sundry sundryRemote = getSundryBean();
			icDTO = sundryRemote.getInsuranceChargeList();
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}

		return icDTO;
	}

	/**
	 * 
	 * @param icDTO
	 * @throws Exception
	 */
	public void setInsuranceChargeList(InsuranceDTO[] icDTO) throws Exception {

		try {
			Sundry sundryRemote = getSundryBean();
			sundryRemote.setInsuranceChargeList(icDTO);
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
	}

	/**
	 * 
	 * @param ssDTO
	 * @throws Exception
	 */
	public void setSpecialSundrySettings(SpecialSundryDTO[] ssDTO)
			throws Exception {
		try {
			Sundry sundryRemote = getSundryBean();
			sundryRemote.setSpecialSundrySettings(ssDTO);
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArticleDTO[] getAricleTypes() throws Exception {
		ArticleDTO[] artDTO = null;
		try {
			Goods goodsRemote = getGoodsBean();
			artDTO = goodsRemote.getArticleTypes();
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}

		return artDTO;
	}

	/**
	 * 
	 * @param artDTO
	 * @throws Exception
	 */
	public void insertCommodities(ArticleDTO[] artDTO) throws Exception {

		try {
			Goods goodsRemote = getGoodsBean();
			goodsRemote.insertCommodities(artDTO);
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
	}

	/**
	 * 
	 * @param artDTO
	 * @throws Exception
	 */
	public void updateCommodities(ArticleDTO[] artDTO) throws Exception {

		try {
			Goods goodsRemote = getGoodsBean();
			goodsRemote.updateCommodities(artDTO);
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
	}

	// BeanUtil
	/**
	 * 
	 * @param articleId
	 * @throws Exception
	 */
	public void deleteCommodity(String articleName) throws Exception {
		try {
			Goods goodsRemote = getGoodsBean();
			goodsRemote.deleteCommodity(articleName);
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
	}

	/**
	 * 
	 * @param stations
	 * @param profilename
	 * @throws Exception
	 */
	public void setBookingCommision(String[] stations, String profilename)
			throws Exception {
		try {
			Commission commisionRemote = getCommisionBean();
			commisionRemote.setBookingCommission(stations, profilename);
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
	}

	public void setBookingCommisionEffDate(String[] stations,
			String profilename, Date date) throws Exception {
		try {
			Commission commisionRemote = getCommisionBean();
			commisionRemote.setBookingCommissionEffDate(stations, profilename,
					date);
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
	}

	/**
	 * 
	 * @param stations
	 * @param delivery
	 * @throws Exception
	 */
	public void setDeliveryCommision(String[] stations, float delivery)
			throws Exception {
		try {
			Commission commisionRemote = getCommisionBean();
			commisionRemote.setDCCommissionList(stations, delivery);
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
	}

	/**
	 * 
	 * @param stationCode
	 * @param description
	 * @param installamount
	 * @throws Exception
	 */
	public void createRefundRegister(String[] stationCode, String contact,
			String description, HashMap<Integer, Float> installamount)
			throws Exception {
		try {
			Commission commissionRemote = getCommisionBean();
			commissionRemote.createRefundRegister(stationCode, contact,
					description, installamount);

		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
	}

	/**
	 * 
	 * @param stationCode
	 * @param description
	 * @param installamount
	 * @throws Exception
	 */
	public void createRecoveryRegister(String[] stationCode, String contact,
			String description, HashMap<Integer, Float> installamount)
			throws Exception {
		try {
			Commission commissionRemote = getCommisionBean();
			commissionRemote.createRecoveryRegister(stationCode, contact,
					description, installamount);

		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
	}

	/**
	 * 
	 * @param station
	 * @param parseFloat
	 * @throws Exception
	 */
	public void setCCCommissionList(String[] station, float parseFloat)
			throws Exception {

		try {
			Commission commissionRemote = getCommisionBean();
			commissionRemote.setCCCommissionList(station, parseFloat);

		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}

	}

	/**
	 * 
	 * @param branch_code
	 * @param day
	 * @param month
	 * @return
	 * @throws Exception
	 */
	public CommissionSummaryDTO[] getAdminCommission(String branch_code,
			int day, int month) throws Exception {
		CommissionSummaryDTO[] dto = null;
		try {
			Commission commissionRemote = getCommisionBean();
			dto = commissionRemote.getAdminCommission(branch_code, day, month);

		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
		return dto;
	}

	/** **************** Admin Customer Settings ********** */

	/**
	 * 
	 */
	public String[] getCustomerNames() throws Exception {
		String[] names = null;
		try {

			Customer customerRemote = getCustomerBean();
			names = customerRemote.getDistinctCustomer();
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
		return names;

	}

	/**
	 * 
	 * @param customerName
	 * @return
	 * @throws Exception
	 */
	public CustomerDTO getAdminCustomer(String customerName) throws Exception {
		if (null == customer) {
			try {
				Customer customerRemote = getCustomerBean();
				customer = customerRemote.getAdminCustomer(customerName);
			} catch (RemoteException remoteException) {
				throw remoteException;
			} catch (Exception exception) {
				throw exception;
			}
		}
		return customer;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public CustomerDTO[] getAdminCustomerDetails() throws Exception {
		//if (null == customerDetails) {
		try {
			Customer customerRemote = getCustomerBean();
			customerDetails = customerRemote.getCustomerDetails();
		} catch (RemoteException remoteException) {
			throw remoteException;
		} catch (Exception exception) {
			throw exception;
		}
		//}
		return customerDetails;
	}

	/**
	 * 
	 * @param dto
	 * @throws Exception
	 */
	public void createAdminCustomer(CustomerDTO dto) throws Exception {

		try {
			Customer customerRemote = getCustomerBean();
			customerRemote.createCustomer(dto);
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}

	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public CustomerDTO[] getCustomerBookingType() throws Exception {
		CustomerDTO[] dto = null;
		try {
			Customer customerRemote = getCustomerBean();
			dto = customerRemote.getCustomerBookingType();
		} catch (RemoteException remoteException) {
			throw remoteException;
		} catch (Exception exception) {
			throw exception;
		}

		return dto;
	}

	/**
	 * 
	 * @param stationcode
	 * @param customername
	 * @param customertype
	 * @param billdate
	 * @return
	 * @throws Exception
	 */
	public BookingDTO[] getBillingDetails(String customername,
			String stationcode, String month, String year, boolean customertype)
			throws Exception {
		BookingDTO[] dto = null;
		try {
			Goods goodsRemote = getGoodsBean();
			dto = goodsRemote.getViewBill(customername, stationcode, month,
					year, customertype);
			// Call the to Method to get Billing Details
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
		return dto;
	}

	// Quotation
	/**
	 * Method to Create Quotation
	 * 
	 * @param quotation
	 */
	public void createQuotation(QuotationDTO quotation) throws Exception {
		try {
			Quotation quotationRemote = getQuotationBean();
			quotationRemote.createQuotation(quotation);
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
	}

	/**
	 * Method to Update Quotation
	 * 
	 * @param quotation
	 */
	public void updateQuotation(QuotationDTO quotation) throws Exception {
		try {
			Quotation quotationRemote = getQuotationBean();
			quotationRemote.updateQuotation(quotation);
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
	}

	/**
	 * Method to get quotation list
	 * 
	 * @return
	 * @throws Exception
	 */
	public QuotationDTO[] getQuotationList() throws Exception {
		QuotationDTO[] quotDTO = null;
		try {
			Quotation quotationRemote = getQuotationBean();
			quotDTO = quotationRemote.getQuotationList();
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
		return quotDTO;
	}

	/**
	 * Method to get Customers list
	 * 
	 * @return
	 * @throws Exception
	 */
	public CustomerDTO[] getCustomers() throws Exception {
		CustomerDTO[] customers = null;
		try {
			Customer customerRemote = getCustomerBean();
			customers = customerRemote.getCustomerDetails();
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
		return customers;
	}

	/**
	 * Method to Update Price Index
	 * 
	 * @param map
	 * @param percent 
	 * @param increment 
	 * @throws Exception
	 */
	public void updatePriceIndex(HashMap<String, Float> map, byte increment,
			float percent) throws Exception {
		try {
			Quotation quotationRemote = getQuotationBean();
			quotationRemote.updatePriceIndex(map, increment, percent);
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}

	}

	/**
	 * @return the serverDate
	 */
	public static String getServerDate() {
		return serverDate;
	}

	/**
	 * @param serverDate
	 *            the serverDate to set
	 */
	public static void setServerDate(String serverDate) {
		BeanUtil.serverDate = serverDate;
	}

	/**
	 * 
	 * @param articleType
	 * @throws Exception
	 */
	public void insertSundryArticle(String articleType) throws Exception {

		try {
			Goods goodsRemote = getGoodsBean();
			goodsRemote.insertSundryArticle(articleType);
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public ArticleDTO[] getSundryAricleTypes() throws Exception {
		ArticleDTO[] artDTO = null;
		try {
			Goods goodsRemote = getGoodsBean();
			artDTO = goodsRemote.getSundryArticles();
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}

		return artDTO;
	}

	/**
	 * 
	 * @param articleType
	 * @throws Exception
	 */
	public void deleteSundryArticle(String articleType) throws Exception {
		try {
			Goods goodsRemote = getGoodsBean();
			goodsRemote.deleteSundryArticle(articleType);
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public RegularSundryDTO getGeneralSettings() throws Exception {
		RegularSundryDTO regularDTO = null;
		try {
			Sundry sundryRemote = getSundryBean();
			regularDTO = sundryRemote.getGeneralSettingsInfo();
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}

		return regularDTO;
	}

	/**
	 * 
	 * @param stationcodes
	 * @param bfdecrement
	 * @throws Exception
	 */
	public void setCCSundryLimit(String[] stationcodes, int bfdecrement)
			throws Exception {

		try {
			Station stationRemote = getStationBean();
			stationRemote.setCCSundryLimit(stationcodes, bfdecrement);
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}

	}

	/**
	 * 
	 * @param station
	 * @param parseFloat
	 * @throws Exception
	 */
	public void setCCRefunder(String[] station, int consider, int refund,
			int ccLimit) throws Exception {

		try {
			Commission commissionRemote = getCommisionBean();
			commissionRemote.setCCConsiderRefund(station, consider, refund,
					ccLimit);

		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}

	}

	/**
	 * 
	 * @param stationcode
	 * @throws Exception
	 */
	public DistanceListDTO[] getDistanceList(String stationcode)
			throws Exception {
		DistanceListDTO[] dto = null;
		try {
			Station station = getStationBean();
			dto = station.getDistanceList(stationcode);

		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
		return dto;
	}

	/**
	 * 
	 * @param stationcode
	 * @throws Exception
	 */
	public void setDistanceList(String stationcode, DistanceListDTO[] distance)
			throws Exception {

		try {
			Station station = getStationBean();
			station.setDistanceList(stationcode, distance);

		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public StationsDTO[] getManageCommission() throws Exception {
		StationsDTO[] dto = null;
		try {
			Station stationRemote = getStationBean();

			dto = stationRemote.getManageCommission();
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
		return dto;

	}

	public void updateManageCommission(StationsDTO[] dto) throws Exception {
		try {
			Station stationRemote = getStationBean();
			stationRemote.updateManageCommisssion(dto);
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}

	}

	/**
	 * 
	 * @param stationcodes
	 * @param cc
	 * @throws Exception
	 */
	public void setCCForSpecialSundry(String[] stationcodes, int cc)
			throws Exception {

		try {
			Station stationRemote = getStationBean();
			stationRemote.setCCForSpecialSundry(stationcodes, cc);
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
	}

	public void setCCForCommodity(String[] stationcodes, int cc)
			throws Exception {

		try {
			Station stationRemote = getStationBean();
			stationRemote.setCCForCommodity(stationcodes, cc);
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
	}

	public void setCCForHLC(String[] stationcodes, String customers,
			float ccArticle, float refundArticle) throws Exception {

		try {
			Station stationRemote = getStationBean();
			stationRemote.setCCForHLC(stationcodes, customers, ccArticle,
					refundArticle);
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
	}

	/**
	 * 
	 * @param name
	 * @throws Exception
	 */
	public void deleteAdminCustomer(String name, String QuotationId)
			throws Exception {
		try {
			Customer customerRemote = getCustomerBean();
			customerRemote.deleteAdminCustomer(name, QuotationId);
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}

	}

	/**
	 * 
	 * @param quotationId
	 * @param isInward 
	 * @throws Exception
	 */
	public void deleteQuotation(String quotationId, boolean isInward)
			throws Exception {
		try {
			Quotation quotationRemote = getQuotationBean();
			quotationRemote.deleteQuotation(quotationId, isInward);
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
	}

	public void approveQuotation(String quotationId) throws Exception {
		try {
			Quotation quotationRemote = getQuotationBean();
			quotationRemote.approveSalesQuotation(quotationId);
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
	}

	public void setCustomerLogin(String quotationId, String user, String pwd)
			throws Exception {
		try {
			Quotation quotationRemote = getQuotationBean();
			quotationRemote.setCustomerLogin(quotationId, user, pwd);
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
	}

	/**
	 * @return the adminRights
	 */
	public static int getAdminRights() {
		return adminRights;
	}

	/**
	 * @param adminRights the adminRights to set
	 */
	public static void setAdminRights(int adminRights) {
		BeanUtil.adminRights = adminRights;
	}

	public void setStationLanguage(String[] stationcodes, byte tamil)
			throws Exception {
		try {
			Station stationRemote = getStationBean();
			stationRemote.setStationLanguage(stationcodes, tamil);
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
	}

	public void setDiscounter(DiscounterDTO[] discount) throws Exception {

		try {
			Sundry sundryRemote = getSundryBean();
			sundryRemote.setDiscounter(discount);

		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public DiscounterDTO[] getDiscounter() throws Exception {
		DiscounterDTO[] discountDTO = null;
		try {
			Sundry sundryRemote = getSundryBean();
			discountDTO = sundryRemote.getDiscounter();
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}

		return discountDTO;
	}

	public void deleteDiscounter(String desc) throws Exception {

		try {
			Sundry sundryRemote = getSundryBean();
			sundryRemote.deleteDiscounter(desc);

		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
	}

	public void setDHC(String[] stationcodes, float dhc) throws Exception {

		try {
			Sundry sundryRemote = getSundryBean();
			sundryRemote.setDHC(stationcodes, dhc);
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}

	}

	public void setIncrementer(DiscounterDTO[] discount) throws Exception {

		try {
			Sundry sundryRemote = getSundryBean();
			sundryRemote.setIncrementer(discount);

		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public DiscounterDTO[] getIncrementer() throws Exception {
		DiscounterDTO[] discountDTO = null;
		try {
			Sundry sundryRemote = getSundryBean();
			discountDTO = sundryRemote.getIncrementer();
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}

		return discountDTO;
	}

	public void deleteIncrementer(String from, String to) throws Exception {
		try {
			Sundry sundryRemote = getSundryBean();
			sundryRemote.deleteIncrementer(from, to);

		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
	}

	public void updateIncrementer(int incr, String desc) throws Exception {
		try {
			Sundry sundryRemote = getSundryBean();
			sundryRemote.updateIncrementer(incr, desc);

		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
	}

	public DiscounterDTO[] searchIncrementer(String desc) throws Exception {
		DiscounterDTO[] dto = null;
		try {
			Sundry sundryRemote = getSundryBean();
			dto = sundryRemote.searchIncrementer(desc);

		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
		return dto;
	}

	public void deleteAllIncrementer(String desc) throws Exception {
		try {
			Sundry sundryRemote = getSundryBean();
			sundryRemote.deleteAllIncrementer(desc);
		} catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
	}

	/**
	 * 
	 * @param dto
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void setDRSFineSettings(StationsDTO[] dto) throws RemoteException,
			Exception {
		try {
			Station station = getStationBean();
			station.setDRSFine(dto);
		} catch (Exception exception) {
			throw exception;
		}
	}

	/**
	 * 
	 * @param date
	 * @param desc
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void setHolidaySettings(Date date, String desc)
			throws RemoteException, Exception {
		try {
			Station station = getStationBean();
			station.setHolidayEntry(date, desc);
		} catch (Exception exception) {
			throw exception;
		}
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

	/**
	 * 
	 * @param fromDate
	 * @param toDate
	 * @param branch
	 * @return
	 * @throws Exception
	 */
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
	
	
	

	
	/**
	 * 
	 * @param branch
	 * @param month
	 * @param year
	 * @return
	 * @throws Exception
	 */
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

	/**
	 * 
	 * @param month
	 * @param year
	 * @return
	 * @throws Exception
	 */
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

	/**
	 * 
	 * @param fromDate
	 * @param toDate
	 * @return
	 * @throws Exception
	 */
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

	/**
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public BookingCommissionDTO[] getBookingCommissionList(Date date,
			String station) throws Exception {
		try {
			Commission commRemote = getCommisionBean();
			if (null != date)
				currentBCommDTO = commRemote.getLRWithCommission(station, date,BeanUtil.getDbName());
			else
				currentBCommDTO = commRemote.getLRWithCommission(station,BeanUtil.getDbName());

		} catch (Exception exception) {
			throw exception;
		}

		return currentBCommDTO;
	}
	
	public BookingCommissionDTO[] getBookingCommissionListHistory(Date date,
			String station) throws Exception {
		try {
			Commission commRemote = getCommisionBean();
			
				currentBCommDTO = commRemote.getLRWithCommissionHistory(station, date,BeanUtil.getDbName());
			

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
	public DeliveryCommissionDTO[] getDeliveryCommissionList(Date date,
			String station) throws Exception {

		try {
			Commission commRemote = getCommisionBean();
			if (null != date)
				currentDCommDTO = commRemote.getDeliveryCommissionList(station,
						date,BeanUtil.getDbName());
			else
				currentDCommDTO = commRemote.getDeliveryCommissionList(station,BeanUtil.getDbName());

		} catch (Exception exception) {
			throw exception;
		}

		return currentDCommDTO;
	}

	public DeliveryCommissionDTO[] getDeliveryCommissionListHistory(Date date,
			String station) throws Exception {

		try {
			Commission commRemote = getCommisionBean();
			if (null != date)
				currentDCommDTO = commRemote.getDeliveryCommissionListHistory(station,
						date,BeanUtil.getDbName());
			else
				currentDCommDTO = commRemote.getDeliveryCommissionList(station,BeanUtil.getDbName());

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
	public RefundRecoveryDTO[] getRecoveryList(Date date, String station)
			throws Exception {
		try {
			Commission commRemote = getCommisionBean();
			if (null != date)
				currentRecoveryDTO = commRemote.getRecoveryDetailList(station, date,BeanUtil.getDbName());
			else
				currentRecoveryDTO = commRemote.getRecoveryDetailList(station,BeanUtil.getDbName());

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
	public RefundRecoveryDTO[] getRefundList(Date date, String station)
			throws Exception {

		try {
			Commission commRemote = getCommisionBean();
			if (null != date)
				currentRefundDTO = commRemote
						.getRefundDetailList(station, date,BeanUtil.getDbName());
			else
				currentRefundDTO = commRemote.getRefundDetailList(station,BeanUtil.getDbName());

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
	public CartageCommissionDTO[] getCartageCommissionList(Date date,
			String station) throws Exception {

		try {
			Commission commRemote = getCommisionBean();
			if (null != date)
				currentCCommDTO = commRemote.getCCCommissionList(station, date,BeanUtil.getDbName());
			else
				currentCCommDTO = commRemote.getCCCommissionList(station,BeanUtil.getDbName());

		} catch (Exception exception) {
			throw exception;
		}

		return currentCCommDTO;
	}
	
	public CartageCommissionDTO[] getCartageCommissionListHistory(Date date,
			String station) throws Exception {

		try {
			Commission commRemote = getCommisionBean();
			if (null != date)
				currentCCommDTO = commRemote.getCCCommissionListHistory(station, date,BeanUtil.getDbName());
			
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
	public DDDetailsDTO[] getDDDetails(Date date, String station)
			throws Exception {
		DDDetailsDTO[] ddDetails = null;
		try {
			Commission commRemote = getCommisionBean();
			ddDetails = commRemote.getDDDetailsList(station, date,BeanUtil.getDbName());
		} catch (Exception exception) {
			throw exception;
		}

		return ddDetails;
	}
	
	public DDDetailsDTO[] getDDDetailsHistory(Date date, String station)
	throws Exception {
		DDDetailsDTO[] ddDetails = null;
		try {
			Commission commRemote = getCommisionBean();
			ddDetails = commRemote.getDDDetailsListHistory(station, date,BeanUtil.getDbName());
		} catch (Exception exception) {
			throw exception;
		}
		
		return ddDetails;
	}
	/**
	 * 
	 * @param station 
	 * @param fromDate
	 * @param toDate
	 * @param branchCode
	 * @throws Exception
	 */
	
	public CommissionSummaryDTO getCommisionSummary(Date date, String station)
			throws Exception {

		try {
			Commission commRemote = getCommisionBean();
			if (null != date) {
				currentSummaryDTO = commRemote.getCommissionSummary(station,
						date,BeanUtil.getDbName());

			}

			else {
				currentSummaryDTO = commRemote
						.getCommissionSummary_month(station,BeanUtil.getDbName());

			}

		} catch (Exception exception) {
			throw exception;
		}

		return currentSummaryDTO;
	}
	
	public CommissionSummaryDTO getCommisionSummaryHistory(Date date, String station)
	throws Exception {

		try {
			Commission commRemote = getCommisionBean();
			if (null != date) {
				currentSummaryDTO = commRemote.getCommissionSummaryHistory(station,
						date,BeanUtil.getDbName());
		
			}
		
			else {
				currentSummaryDTO = commRemote
						.getCommissionSummary_month(station,BeanUtil.getDbName());
		
			}
		
		} catch (Exception exception) {
			throw exception;
		}
	
	return currentSummaryDTO;
	}

	@SuppressWarnings("unchecked")
	public void exportToReportTXT(ArrayList data, String jrxmlFile, HashMap<String, String> hashMap,  String fileName)
			throws JRException, Exception {
		try {
			JasperReport jasperReport;
			JasperPrint jasperPrint;
			//System.out.println("list-->"+data);

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
	
	public void exportToReportExcel(ArrayList data, String jrxmlFile, HashMap<String, String> hashMap,  String fileName)
	throws JRException, Exception {
try {
	JasperReport jasperReport;
	JasperPrint jasperPrint;
	//System.out.println("list-->"+data);
	
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

	public void exportToReportPDF(ArrayList data, String jrxmlFile, HashMap<String, String> hashMap,  String fileName)
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

	
	public GeneralSummaryDTO[] getCCCSummaryPDTPHistory(Date fromDate,
			Date toDate, String branch) throws Exception {

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

	public ServiceTaxDTO[] getHolidays(String year)throws Exception {
		// TODO Auto-generated method stub
		
		ServiceTaxDTO[] holiday = null;
		try{
			Reporting reporting  = getReportingBean();
			holiday =reporting.getHolidays(year);
		}catch (RemoteException remoteexception) {
			throw remoteexception;
		} catch (Exception exception) {
			throw exception;
		}
		return holiday;
	}
	
	

}
