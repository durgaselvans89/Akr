package hm.akr.container.gmr;

import hm.akr.common.BeanUtil;
import hm.akr.common.CellValidation;
import hm.akr.common.FloatValidation;
import hm.akr.common.Hour;
import hm.akr.common.KalendarDialog;
import hm.akr.common.RoundOff;
import hm.akr.common.SMS;
import hm.akr.common.SWTResourceManager;
import hm.akr.dto.CashDTO;
import hm.akr.dto.DCProfileDTO;
import hm.akr.dto.GMRDTO;
import hm.akr.dto.GMRInTimeDTO;
import hm.akr.dto.GMROutTimeDTO;
import hm.akr.dto.GMRReportDTO;
import hm.akr.dto.LRNumberRangeDTO;
import hm.akr.dto.SMSDTO;
import hm.akr.dto.StationsDTO;
import hm.akr.dto.VehicleDTO;
import hm.akr.exceptions.BusinessException;

import java.rmi.RemoteException;
import java.text.Collator;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Locale;
import java.util.TreeSet;

import javax.naming.NamingException;

import jxl.biff.drawing.ComboBox;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

/**
 * This code was edited or generated using CloudGarden's Jigloo
 * SWT/Swing GUI Builder, which is free for non-commercial
 * use. If Jigloo is being used commercially (ie, by a corporation,
 * company or business for any purpose whatever) then you
 * should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details.
 * Use of Jigloo implies acceptance of these licensing terms.
 * A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
 * THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
 * LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
/**
 * 
 * 
 * @version 1.0
 */
public class GMRComposite extends Composite {

	{
		// Register as a resource user - SWTResourceManager will
		// handle the obtaining and disposing of resources
		SWTResourceManager.registerResourceUser(this);
	}

	GMRCompositeHandler handler = null;

	private Button btnDelivery;

	private Button btnDispatch;

	private Text txtDriverName;

	private CCombo cbVehicle;

	private Label label6;

	private Label label5;

	private Label lblName;

	private Text txtDestCode;

	private Canvas canvas1;

	private Combo cbDestStationName;

	private Label label4;

	private Label label3;

	private Canvas canvas4;

	private Display display;

	private TableColumn colCosigneeOut;

	private TableColumn colSNo;

	private TableColumn colOutSNo;

	private TableColumn colConsignorOut;

	private TableColumn colOutWeight;

	private TableColumn colActualWeight;

	private TableColumn colOutNOA;

	private TableColumn colOutDate;

	private TableColumn colOutNdsb;

	private TableColumn colOutNdsi;

	private TableColumn colOutTo;

	private TableColumn colOutFrom;

	private TableColumn colOutLrNo;

	private Table tblGmrOuttime;

	private Canvas canvas3;

	private TabItem tiGmrOutTime;

	private Button btnAssignInTime;

	private TableColumn colArticleNo;

	private TableColumn colDate;

	private TableColumn colCosignee;

	private TableColumn colCosignor;

	private TableColumn colTo;

	private TableColumn colFrom;

	private TableColumn colVehicle;

	private TableColumn colLr;

	private Table tblgmrintime;

	private Canvas canvas2;

	private TabItem tiGmrInTime;

	private TabFolder gmrTabFold;

	private static final int LR_IN_COLUMN = 1;

	private static final int LAST_INWARD_COLUMN = 15;

	private Shell shell;

	private ArrayList inTimeGoods;

	private ArrayList outTimeGoods;

	ArrayList<String> tempList = null;

	private TabItem tiGmrReport;

	private Canvas canvas5;

	private Table tblGmrReport;

	private TableColumn colRSNo;

	private TableColumn colRDestination;

	private TableColumn colRTimeOfDispatch;

	private TableColumn colRVehicleNo;

	private TableColumn colRNoOfLr;

	private TableColumn colRTotalWeight;

	private TableColumn colRNOA;

	private TableColumn colRDriverName;

	private Text txtStatementNo;

	private Label lblStatementNo;

	private Button btnGo;

	private Text txtDate;

	private Label lblDate;

	private static final String OUT_TIME_TAB_NAME = "GMROUTTIME";

	private static final String IN_TIME_TAB_NAME = "GMRINTIME";

	private static final String REPORT_TAB_NAME = "GMR REPORT";

	private static String EMPTY_STRING = "";

	private GMRReportDTO[] gmr = null;

	private String DATE_FORMAT = "dd-MM-yyyy";

	private Button btnDetails;

	private TableColumn colOutDDC;

	private TableColumn colOutLRTotal;

	private TableColumn colOutArticleValue;
	private Combo txtModelNo;
	private Text txtVehicleRate;
	private Text txtBrokerName;
	private Label lblModelNo;
	private Label lblBrokerName;
	private Label lblVehicleRate;

	private Button chkMarketVehicle;

	private Group gpMargetVehile;

	private TableColumn colSentDate;

	private TableColumn colSentDays;

	private TableColumn colFreight;

	private TableColumn colPTB;

	private TableColumn colDDc;

	private String SERVER_DATE = null;

	private boolean isDispatchRunning = false;

	private boolean isDeliveryRunning = false;

	private TableColumn colHiddenInwardTime;

	private Label lblBrokerPhone;

	private Text txtBrokerPhone;

	private DecimalFormat decimalFormat;

	private TableColumn colOutLrType;

	private TableColumn colCnorPhone;

	private TableColumn colCneePhone;

	private Button btnSendSMS;

	private TableColumn colSMSNotify;

	private TableColumn colRateType;
	
	private ArrayList<String> listStn;
	
	private ArrayList<String> listBranch;
	
	private String[] listDest;

	LRNumberRangeDTO[] lrrangeDTO = null;
	
	/**
	 * Constructor method
	 * 
	 * @param shell
	 */
	public GMRComposite(Shell shell, int swtValue) {

		super(shell, swtValue);

		this.shell = shell;

		try {
			handler = new GMRCompositeHandler();
			SERVER_DATE = handler.getServerDate();
			decimalFormat = new DecimalFormat("0.00");			
		} catch (Exception e1) {
		}

	}

	/**
	 * Method to create GMR OutTime Components
	 * 
	 * @throws Exception
	 */
	private void populateOutTimeForm() throws Exception {
		populateStations();

		populateVehicles();

		try {
			if (null == outTimeGoods || outTimeGoods.size() == 0) {
				String stationCode = handler.getStationCode();
				outTimeGoods = handler.getOutTimeGoods(stationCode);
				if (null != outTimeGoods && outTimeGoods.size() > 0) {
					populateOutTimeTable();
				} else {
					displayError("No Records Available");
				}
			}

		} catch (BusinessException businessexception) {
			displayError(businessexception.getResponseMessage());
		} catch (Exception exception) {
			displayError("Error while retrieving the Out Time Records");
		}

	}

	/**
	 * Method to Populate All Vehicles
	 */
	private void populateVehicles() {
		int len = 0;

		try {
			cbVehicle.removeAll();

			String stationCode = handler.getStationCode();
			VehicleDTO[] vehicles = handler.getVehicles(stationCode);

			if (null != vehicles && (len = vehicles.length) > 0) {
				for (int i = 0; i < len; i++) {
					cbVehicle.add(vehicles[i].getVehicle_number());
				}
			}
		} catch (Exception exception) {

		}
	}

	/**
	 * Method to populate GMR OutTime Table Records
	 * 
	 * @throws Exception
	 * @throws NamingException
	 */
	private void populateOutTimeTable() throws NamingException, Exception {

		int len = 0;
		int booking = 0;
		int inward = 0;
		int noa = 0;
		float weight = 0;

		int ddc = 0;
		int lrtotal = 0;
		long articlevalue = 0;

		DecimalFormat intFormat = new DecimalFormat("0");

		if (null != outTimeGoods)
			len = outTimeGoods.size();
		tblGmrOuttime.removeAll();
		BeanUtil util = BeanUtil.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date today = dateFormat.parse(util.getServerDate());
		for (int i = 0; i < len; i++) {
			GMROutTimeDTO dto = (GMROutTimeDTO) outTimeGoods.get(i);

			TableItem item = new TableItem(tblGmrOuttime, SWT.NONE);
			Font itemFont = new Font(display, "Tahoma", 9, 0);
			item.setFont(itemFont);
			item.setText(0, String.valueOf(i + 1));
			item.setText(1, dto.getLr_no());
			item.setText(2, dto.getLr_type());
			Date lrdate = dto.getLrDate();
			item.setText(3, dateFormat.format(lrdate));

			long diff = today.getTime() - lrdate.getTime();
			float days = (diff / (1000 * 60 * 60 * 24));
			item.setText(4, String.valueOf((int) days));
			booking = booking + (int) days;

			/** Need to change Number of days since inward * */
			Date outtimedate = dto.getOutTimeDate();
			//System.out.println("ot-->"+dto.getOutTimeDate());
			//System.out.println("otllll-->"+dto.getLast_inwarded_date());
			long diff1 = today.getTime() - outtimedate.getTime();
			float days1 = (diff1 / (1000 * 60 * 60 * 24));
			inward = inward + (int) days1;

			item.setText(5, String.valueOf((int) days1));
			item.setText(6, dto.getFrom());
			item.setText(7, dto.getTo());
			item.setText(8, String.valueOf(dto.getNo_of_articles()));
			noa = noa + dto.getNo_of_articles();
			item.setText(9, decimalFormat.format(dto.getActual_weight()));
			weight = weight + dto.getActual_weight();

			ddc = ddc + (int) dto.getDdc();
			item.setText(10, String.valueOf((int) dto.getDdc()));

			lrtotal = lrtotal + (int) dto.getTotal();
			item.setText(11, String.valueOf((int) dto.getTotal()));

			articlevalue = articlevalue + (long) dto.getArticle_value();
			item.setText(12, intFormat.format(dto.getArticle_value()));
			item.setText(13, dto.getConsignorName());
			item.setText(14, dto.getConsigneeName());
			item.setText(15, dateFormat1.format(dto.getLast_inwarded_date()));
		}

		final TableItem item1 = new TableItem(tblGmrOuttime, SWT.NONE);
		Font font1 = new Font(display, "Tahoma", 8, SWT.BOLD);
		item1.setFont(font1);
		item1.setText(0, "TOTAL");
		if(len > 0){
			item1.setText(4, String.valueOf(booking / len));
			item1.setText(5, String.valueOf(inward / len));
		}
		item1.setText(8, Integer.toString(noa));
		item1.setText(9, decimalFormat.format(weight));

		item1.setText(10, String.valueOf(ddc));
		item1.setText(11, String.valueOf(lrtotal));
		item1.setText(12, String.valueOf(articlevalue));

		tblGmrOuttime.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {

			}
		});

	}

	/**
	 * Method to populate All Stations
	 */
	private void populateStations() {
		int len = 0;
		String currStn = EMPTY_STRING;
		String currBranch = EMPTY_STRING;
		
		try {
			cbDestStationName.removeAll();
			StationsDTO[] stations = handler.getAvailableStations();

			if (null != stations && (len = stations.length) > 0) {
				currStn = handler.getStationCode();

				currBranch = checkCityStation(currStn);
				listBranch = getBranches();
				int branchLen = listBranch.size();
			
				for (int i = 0; i < len; i++) {
					if (!stations[i].getType().equalsIgnoreCase("City") && !stations[i].getId().equalsIgnoreCase(currStn)) {						
						// Non city stations
						cbDestStationName.add(stations[i].getName());
					}					
				}
				if(branchLen > 0){
					for(int i = 0; i < branchLen; i++){
						cbDestStationName.add(listBranch.get(i));
					}							
				}	
				if (currBranch != null){				
					
					//City
					if(currStn.equalsIgnoreCase(currBranch)){
						//TR
						listStn = getCityStations(currStn);
						int stnLen = 0;
						if(listStn != null){
							stnLen = listStn.size();
						}
						if(stnLen > 0){
							for(int i = 0; i < stnLen; i++){
								cbDestStationName.add(listStn.get(i));
							}							
						}	
						
					}			
				} 
			}			
			
			listDest = cbDestStationName.getItems();
			
			Arrays.sort(listDest, String.CASE_INSENSITIVE_ORDER);			
			cbDestStationName.removeAll();
			cbDestStationName.setItems(listDest);
			
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private ArrayList<String> getCityStations(String branchcode) throws Exception {
		StationsDTO[] stations = handler.getAvailableStations();
		ArrayList<String> stationList = new ArrayList<String>();
		int size = 0;
		if (null != stations) {
			size = stations.length;
			for (int i = 0; i < size; i++) {
				if (branchcode.equals(stations[i].getBranch_code()) && !branchcode.equalsIgnoreCase(stations[i].getId()) 
						&& stations[i].getType().equalsIgnoreCase("City")) {
					stationList.add(stations[i].getName());
				}
			}
		}

		return stationList;
	}

	private ArrayList<String> getBranches() throws Exception {
		ArrayList<String> branchNameList = new ArrayList<String>();
		StationsDTO[] stations = handler.getAvailableStations();
		String currStn = handler.getStationCode();
		// Get the list of branch codes
		int len = stations.length;
		for (int i = 0; i < len; i++) {
			if (stations[i].getId().equalsIgnoreCase(stations[i].getBranch_code()) && !stations[i].getId().equalsIgnoreCase(currStn)) {
				branchNameList.add(stations[i].getName());
			}
		}
		return branchNameList;
	}

	/**
	 * Method to Populate GMR INTIME Records
	 */
	private void populateGMRInTimeTable() {
		int len = inTimeGoods.size();
		int noa = 0;
		float weight = 0;
		int sentdays = 0;
		int freight = 0;
		int ddc = 0;

		tblgmrintime.removeAll();
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		for (int i = 0; i < len; i++) {
			GMRInTimeDTO dto = (GMRInTimeDTO) inTimeGoods.get(i);

			TableItem item = new TableItem(tblgmrintime, SWT.NONE);
			Font itemFont = new Font(display, "Tahoma", 9, 0);
			item.setFont(itemFont);
			item.setText(0, String.valueOf(i + 1));
			item.setText(1, dto.getLr_no());

			Date lrdate = dto.getDate();
			item.setText(2, dateFormat.format(lrdate));
			lrdate = dto.getSent_date();
			item.setText(3, dateFormat.format(lrdate));
			Date today = dto.getToday_date();

			long diff = today.getTime() - lrdate.getTime();

			diff = diff / (1000 * 60 * 60 * 24);
			sentdays = sentdays + (int) diff;

			item.setText(4, String.valueOf((int) diff));

			freight = freight + (int) dto.getTotal();
			item.setText(5, String.valueOf((int) dto.getTotal()));
			item.setText(6, dto.getLr_type());

			ddc = ddc + (int) dto.getDdc();
			item.setText(7, String.valueOf((int) dto.getDdc()));
			item.setText(8, dto.getFrom());
			item.setText(9, dto.getTo());
			item.setText(10, String.valueOf(dto.getNo_of_articles()));
			noa = noa + dto.getNo_of_articles();
			item.setText(11, decimalFormat.format(dto.getActual_weight()));
			weight = weight + dto.getActual_weight();

			if (null != dto.getVehicle_no())
				item.setText(12, dto.getVehicle_no());

			item.setText(13, dto.getConsignorName());
			item.setText(14, dto.getConsigneeName());
			item.setText(15, dto.getCnorPhone());
			item.setText(16, dto.getCneePhone());
			item.setText(17, String.valueOf(dto.getSmsNotify()));
			item.setText(18, String.valueOf(dto.getRate_type()));
		}

		final TableItem totalItem = new TableItem(tblgmrintime, SWT.NONE);
		Font totalFont = new Font(display, "Tahoma", 8, SWT.BOLD);
		totalItem.setFont(totalFont);
		totalItem.setText(0, "TOTAL");

		totalItem.setText(4, String.valueOf(sentdays));
		totalItem.setText(5, String.valueOf(freight));
		totalItem.setText(7, String.valueOf(ddc));
		totalItem.setText(10, Integer.toString(noa));
		totalItem.setText(11, decimalFormat.format(weight));
		tblgmrintime.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
			}
		});

	}

	/**
	 * Method to Populate GMR INTIME Table
	 */
	private void populateGMRInTime() {

		if (null == inTimeGoods || inTimeGoods.size() == 0) {
			try {
				String stationCode = handler.getStationCode();
				inTimeGoods = handler.getInTimeGoods(stationCode);

				if (null != inTimeGoods && inTimeGoods.size() > 0) {
					populateGMRInTimeTable();
				}
			} catch (BusinessException businessexception) {
				displayError(businessexception.getResponseMessage());
			}

			catch (Exception exception) {
				displayError("Error occurred while populating the InTime Goods");
			}
		}
	}

	/**
	 * Method to populate GMR Report
	 */

	private void populateGMRRecords(boolean refresh) {
		if (refresh || null == gmr) {
			String stationCode = handler.getStationCode();
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date date = null;
			try {
				date = dateFormat.parse(txtDate.getText());
				
				Date curDate = new Date();			
				int m1 = date.getYear() * 12 + date.getMonth();
		        int m2 = curDate.getYear() * 12 + curDate.getMonth();
		        int monthDiff = m2 - m1;
		        if(monthDiff > 3){
		        	//System.out.println("history");
					gmr = handler.getGoodsMovementHistory(stationCode, date);
		        }else{
		        	//System.out.println("current");
					gmr = handler.getGoodsMovement(stationCode, date);
		        }


				// firsttab = false;

				if (null != gmr) {
					populateGMRTable(gmr);

				} else {
					tblGmrReport.removeAll();
					displayError("No Records Available");
				}

			} catch (BusinessException businessexception) {
				displayError(businessexception.getResponseMessage());
			} catch (Exception exception) {
				exception.printStackTrace();
				displayError("Error occurred while populating the GMR Reports");
			}

		}
	}

	/**
	 * Method to populate GMR Table
	 */

	public void populateGMRTable(GMRReportDTO[] gmr) {
		tblGmrReport.removeAll();
		int len = gmr.length;
		String availDispatchTo = EMPTY_STRING;
		String tblDispatchTo = EMPTY_STRING;
		String tblDispatchTime = null;
		boolean isEqual = false;
		int tblLen = 0;
		String date = txtDate.getText();
		String serialPrefix = calculateStatementNumber(date, "GMR");
		txtStatementNo.setText(serialPrefix);
		String movedto = null;
		String vehicle = null;
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String driver = null;
		DecimalFormat intFormat = new DecimalFormat("0");

		{
			TableItem itemGmr = new TableItem(tblGmrReport, SWT.NONE);
			itemGmr.setText(0, Integer.toString(0 + 1));
			movedto = gmr[0].getMoved_to();
			if (null != movedto && !movedto.equals(""))
				itemGmr.setText(1, movedto);
			else
				itemGmr.setText(1, "Delivery");

			dateFormat.format(gmr[0].getVehicleDate());

			String vehiledate = String.valueOf(dateFormat.format(gmr[0]
					.getVehicleDate()));
			int intex = vehiledate.indexOf(":");
			itemGmr.setText(2, vehiledate.substring(0, intex + 3));

			vehicle = gmr[0].getVehicleNo();
			if (vehicle != null)
				itemGmr.setText(3, vehicle);
			itemGmr.setText(4, String.valueOf(1));
			itemGmr.setText(5, decimalFormat.format(gmr[0].getWeight()));
			itemGmr.setText(6, intFormat.format(gmr[0].getNo_of_articles()));
			driver = gmr[0].getDriverName();
			if (driver != null)
				itemGmr.setText(7, driver);
		}

		for (int i = 1; i < len; i++) {

			availDispatchTo = gmr[i].getMoved_to();

			// String vehiledate = String.valueOf(gmr[i].getVehicleDate());
			String vehiledate = String.valueOf(dateFormat.format(gmr[i]
					.getVehicleDate()));
			int intex = vehiledate.indexOf(":");
			vehiledate = vehiledate.substring(0, intex + 3);

			if (availDispatchTo != null
					&& !availDispatchTo.equals(EMPTY_STRING)) {
				tblLen = tblGmrReport.getItemCount();
				if (tblLen > 0) {
					TableItem[] items = tblGmrReport.getItems();
					for (int j = 0; j < tblLen; j++) {

						tblDispatchTo = items[j].getText(1);
						tblDispatchTime = (items[j].getText(2));
						if (tblDispatchTo.equals(availDispatchTo)
								&& tblDispatchTime.equals(vehiledate)) {

							items[j].setText(4, String.valueOf(Integer
									.parseInt(items[j].getText(4)) + 1));
							items[j].setText(5, decimalFormat.format(Float
									.parseFloat(items[j].getText(5))
									+ gmr[i].getWeight()));
							items[j].setText(6, intFormat.format(Float
									.parseFloat(items[j].getText(6))
									+ gmr[i].getNo_of_articles()));
							isEqual = true;
							break;

						} else {
							isEqual = false;
						}
					}
					if (!isEqual) {
						TableItem itemGmr = new TableItem(tblGmrReport,
								SWT.NONE);
						itemGmr.setText(0, Integer.toString(tblLen + 1));
						movedto = gmr[i].getMoved_to();
						if (null != movedto && !movedto.equals(""))
							itemGmr.setText(1, movedto);
						else
							itemGmr.setText(1, "Delivery");
						itemGmr.setText(2, vehiledate);
						vehicle = gmr[i].getVehicleNo();
						if (vehicle != null)
							itemGmr.setText(3, vehicle);

						itemGmr.setText(4, String.valueOf(1));
						itemGmr.setText(5, decimalFormat.format(gmr[i]
								.getWeight()));
						itemGmr.setText(6, intFormat.format(gmr[i]
								.getNo_of_articles()));
						driver = gmr[i].getDriverName();
						if (driver != null)
							itemGmr.setText(7, driver);

					}
				}

			} else {
				tblLen = tblGmrReport.getItemCount();
				if (tblLen > 0) {
					TableItem[] items = tblGmrReport.getItems();
					for (int j = 0; j < tblLen; j++) {

						tblDispatchTime = (items[j].getText(2));
						if (tblDispatchTime.equals(vehiledate)) {
							items[j].setText(4, String.valueOf(Integer
									.parseInt(items[j].getText(4)) + 1));
							items[j].setText(5, decimalFormat.format(Float
									.parseFloat(items[j].getText(5))
									+ gmr[i].getWeight()));
							items[j].setText(6, intFormat.format(Float
									.parseFloat(items[j].getText(6))
									+ gmr[i].getNo_of_articles()));
							isEqual = true;
							break;

						} else {
							isEqual = false;
						}
					}
					if (!isEqual) {
						TableItem itemGmr = new TableItem(tblGmrReport,
								SWT.NONE);
						itemGmr.setText(0, Integer.toString(tblLen + 1));
						movedto = gmr[i].getMoved_to();

						if (null != movedto && !movedto.equals(""))
							itemGmr.setText(1, movedto);
						else
							itemGmr.setText(1, "Delivery");

						itemGmr.setText(2, vehiledate);
						itemGmr.setText(4, String.valueOf(1));
						itemGmr.setText(5, decimalFormat.format(gmr[i]
								.getWeight()));
						itemGmr.setText(6, intFormat.format(gmr[i]
								.getNo_of_articles()));

					}
				}

			}

		}
		SortByDateTime(2, tblGmrReport);
		applySno(tblGmrReport);
	}

	
	/**
	 * 
	 * @param tblGmrReport_in
	 */
	private void applySno(Table tblGmrReport_in) {
		if (null != tblGmrReport_in) {
			TableItem[] items = tblGmrReport_in.getItems();

			int len = items.length;
			if (len > 0) {
				for (int i = 0; i < len; i++) {
					items[i].setText(0, String.valueOf(i + 1));
				}
			}

		}

	}

	/**
	 * Method to CalculateStatementNumber
	 * 
	 * @param dateString
	 * @param stmt
	 * @return
	 */
	private String calculateStatementNumber(String dateString, String stmt) {
		String serialPrefix = EMPTY_STRING;
		int temp = 0;

		if (null != dateString && dateString.length() > 0) {
			serialPrefix = handler.getStationCode() + stmt;
			SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);

			try {

				GregorianCalendar givenDate = new GregorianCalendar();
				Date date = format.parse(dateString);
				givenDate.setTime(date);

				int month = givenDate.get(Calendar.MONTH);
				int year = givenDate.get(Calendar.YEAR);
				int day = givenDate.get(Calendar.DATE);
				int mo = month + 1;

				givenDate.set(year, mo, day);

				if (month < 4)
					year--;
				GregorianCalendar cal = new GregorianCalendar();
				cal.set(year, 3, 31);
				long mill1 = givenDate.getTimeInMillis();
				long mill2 = cal.getTimeInMillis();
				long diff = mill1 - mill2;

				temp = (int) (diff / (24 * 60 * 60 * 1000)); // Number Of
				// days since
				// financial year
			} catch (Exception exception) {
				return EMPTY_STRING;
			}
		}

		return serialPrefix + temp;
	}

	/**
	 * 
	 * @return
	 */
	public Composite loadComposite() {

		try {
			this.setLayout(new FormLayout());
			this.setSize(1024, 768);
			{
				lblName = new Label(this, SWT.NONE);
				FormData lblNameLData = new FormData();
				lblNameLData.width = 420;
				lblNameLData.height = 31;
				lblNameLData.left = new FormAttachment(0, 1000, 592);
				lblNameLData.top = new FormAttachment(0, 1000, 20);
				lblName.setLayoutData(lblNameLData);
				lblName.setText("GOODS MOVEMENT REGISTER");
				lblName.setFont(SWTResourceManager.getFont("Tahoma", 20, 1,
						false, false));
			}

			{
				gmrTabFold = new TabFolder(this, SWT.NONE);
				FormData tabFolder1LData = new FormData();
				tabFolder1LData.width = 928;
				tabFolder1LData.height = 538;
				tabFolder1LData.left = new FormAttachment(0, 1000, 50);
				tabFolder1LData.top = new FormAttachment(0, 1000, 130);
				gmrTabFold.setLayoutData(tabFolder1LData);
				gmrTabFold.addSelectionListener(new GMRListener());
				{
					tiGmrInTime = new TabItem(gmrTabFold, SWT.NONE);
					tiGmrInTime.setText(IN_TIME_TAB_NAME);
					{
						canvas2 = new Canvas(gmrTabFold, SWT.NONE);
						tiGmrInTime.setControl(canvas2);
						{
							tblgmrintime = new Table(canvas2, SWT.H_SCROLL
									| SWT.FULL_SELECTION | SWT.CHECK
									| SWT.V_SCROLL | SWT.BORDER | SWT.MULTI);
							tblgmrintime.setHeaderVisible(true);
							tblgmrintime.setLinesVisible(true);
							tblgmrintime.setBounds(12, 20, 908, 460);
							Font header1Font = new Font(display, "Tahoma", 9, 0);
							tblgmrintime.setFont(header1Font);
							{
								colSNo = new TableColumn(tblgmrintime, SWT.NONE);
								colSNo.setText("S.No");
								colSNo.setWidth(60);
								colSNo.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colLr = new TableColumn(tblgmrintime, SWT.NONE);
								colLr.setText("LR No");
								colLr.setWidth(80);
								colLr.addListener(SWT.Selection,
										new sortListener());
							}

							{
								colDate = new TableColumn(tblgmrintime,
										SWT.NONE);
								colDate.setText("DATE");
								colDate.setWidth(90);
								colDate.addListener(SWT.Selection,
										new sortListener());
							}

							{
								colSentDate = new TableColumn(tblgmrintime,
										SWT.NONE);
								colSentDate.setText("SENT DATE");
								colSentDate.setWidth(90);
								colSentDate.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colSentDays = new TableColumn(tblgmrintime,
										SWT.NONE);
								colSentDays.setText("SENT DAYS");
								colSentDays.setWidth(90);
								colSentDays.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colFreight = new TableColumn(tblgmrintime,
										SWT.NONE);
								colFreight.setText("Freight");
								colFreight.setWidth(90);
								colFreight.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colPTB = new TableColumn(tblgmrintime, SWT.NONE);
								colPTB.setText("TYPE");
								colPTB.setWidth(90);
								colPTB.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colDDc = new TableColumn(tblgmrintime, SWT.NONE);
								colDDc.setText("DDC");
								colDDc.setWidth(90);
								colDDc.addListener(SWT.Selection,
										new sortListener());
							}

							{
								colFrom = new TableColumn(tblgmrintime,
										SWT.NONE);
								colFrom.setText("FROM");
								colFrom.setWidth(60);
								colFrom.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colTo = new TableColumn(tblgmrintime, SWT.NONE);
								colTo.setText("TO");
								colTo.setWidth(60);
								colTo.addListener(SWT.Selection,
										new sortListener());
							}

							{
								colArticleNo = new TableColumn(tblgmrintime,
										SWT.NONE);
								colArticleNo.setText("NOA");
								colArticleNo.setWidth(60);
								colArticleNo.addListener(SWT.Selection,
										new sortListener());
							}

							{
								colActualWeight = new TableColumn(tblgmrintime,
										SWT.NONE);
								colActualWeight.setText("WEIGHT");
								colActualWeight.setWidth(60);
								colActualWeight.addListener(SWT.Selection,
										new sortListener());
							}

							{
								colVehicle = new TableColumn(tblgmrintime,
										SWT.NONE);
								colVehicle.setText("ARRIVAL VEHICLE NO");
								colVehicle.setWidth(120);
								colVehicle.addListener(SWT.Selection,
										new sortListener());
							}

							{
								colCosignor = new TableColumn(tblgmrintime,
										SWT.NONE);
								colCosignor.setText("CONSIGNOR NAME");
								colCosignor.setWidth(139);
								colCosignor.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colCosignee = new TableColumn(tblgmrintime,
										SWT.NONE);
								colCosignee.setText("CONSIGNEE NAME");
								colCosignee.setWidth(180);
								colCosignee.addListener(SWT.Selection,
										new sortListener());
							}
							
							{
								colCnorPhone = new TableColumn(tblgmrintime,
										SWT.NONE);
								colCnorPhone.setText("Cnor Phone");
								colCnorPhone.setWidth(0);								
							}
							
							{
								colCneePhone = new TableColumn(tblgmrintime,
										SWT.NONE);
								colCneePhone.setText("Cnee Phone");
								colCneePhone.setWidth(0);							
							}
							{
								colSMSNotify = new TableColumn(tblgmrintime,
										SWT.NONE);
								colSMSNotify.setText("SMS");
								colSMSNotify.setWidth(0);							
							}
							{
								colRateType = new TableColumn(tblgmrintime,
										SWT.NONE);
								colRateType.setText("rate_type");
								colRateType.setWidth(0);							
							}
							

						}
						if (!BeanUtil.getInstance().isSecondThread()
								|| BeanUtil.getInstance().isDiffStation()) {
							btnAssignInTime = new Button(canvas2, SWT.PUSH
									| SWT.CENTER);
							btnAssignInTime.setText("Assign Intime");
							btnAssignInTime.setBounds(791, 499, 110, 30);

							btnAssignInTime
									.addSelectionListener(new GMRListener());
						}

					}
				}
				{
					tiGmrOutTime = new TabItem(gmrTabFold, SWT.NONE);
					tiGmrOutTime.setText(OUT_TIME_TAB_NAME);
					{
						canvas3 = new Canvas(gmrTabFold, SWT.BORDER);
						tiGmrOutTime.setControl(canvas3);
						{

							tblGmrOuttime = new Table(canvas3, SWT.H_SCROLL
									| SWT.FULL_SELECTION | SWT.CHECK
									| SWT.V_SCROLL | SWT.BORDER | SWT.MULTI);
							tblGmrOuttime.setHeaderVisible(true);
							tblGmrOuttime.setLinesVisible(true);
							tblGmrOuttime.setBounds(40, 20, 872, 321);

							Font headerFont = new Font(display, "Tahoma", 9, 0);
							tblGmrOuttime.setFont(headerFont);

							{
								colOutSNo = new TableColumn(tblGmrOuttime,
										SWT.NONE);
								colOutSNo.setText("S.No");
								colOutSNo.setWidth(60);
								colOutSNo.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colOutLrNo = new TableColumn(tblGmrOuttime,
										SWT.NONE);
								colOutLrNo.setText("LR No");
								colOutLrNo.setWidth(80);
								colOutLrNo.addListener(SWT.Selection,
										new sortListener());

							}
							{
								colOutLrType = new TableColumn(tblGmrOuttime,
										SWT.NONE);
								colOutLrType.setText("LR Type");
								colOutLrType.setWidth(60);
								colOutLrType.addListener(SWT.Selection,
										new sortListener());
							}

							{
								colOutDate = new TableColumn(tblGmrOuttime,
										SWT.NONE);
								colOutDate.setText("DATE");
								colOutDate.setWidth(90);
								colOutDate.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colOutNdsb = new TableColumn(tblGmrOuttime,
										SWT.NONE);
								colOutNdsb.setText("BOOKING DAYS");
								colOutNdsb.setWidth(60);
								colOutNdsb.addListener(SWT.Selection,
										new sortListener());

							}
							{
								colOutNdsi = new TableColumn(tblGmrOuttime,
										SWT.NONE);
								colOutNdsi.setText("INWARD DAYS");
								colOutNdsi.setWidth(60);
								colOutNdsi.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colOutFrom = new TableColumn(tblGmrOuttime,
										SWT.NONE);
								colOutFrom.setText("FROM");
								colOutFrom.setWidth(60);
								colOutFrom.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colOutTo = new TableColumn(tblGmrOuttime,
										SWT.NONE);
								colOutTo.setText("TO");
								colOutTo.setWidth(60);
								colOutTo.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colOutNOA = new TableColumn(tblGmrOuttime,
										SWT.NONE);
								colOutNOA.setText("NO OF ARTICLES");
								colOutNOA.setWidth(50);
								colOutNOA.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colOutWeight = new TableColumn(tblGmrOuttime,
										SWT.NONE);
								colOutWeight.setText("WEIGHT");
								colOutWeight.setWidth(90);
								colOutWeight.addListener(SWT.Selection,
										new sortListener());
							}

							{
								colOutDDC = new TableColumn(tblGmrOuttime,
										SWT.NONE);
								colOutDDC.setText("DDC");
								colOutDDC.setWidth(60);
								colOutDDC.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colOutLRTotal = new TableColumn(tblGmrOuttime,
										SWT.NONE);
								colOutLRTotal.setText("LR Total");
								colOutLRTotal.setWidth(60);
								colOutLRTotal.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colOutArticleValue = new TableColumn(
										tblGmrOuttime, SWT.NONE);
								colOutArticleValue.setText("Article Value");
								colOutArticleValue.setWidth(115);
								colOutArticleValue.addListener(SWT.Selection,
										new sortListener());
							}

							{
								colConsignorOut = new TableColumn(
										tblGmrOuttime, SWT.NONE);
								colConsignorOut.setText("CONSIGNOR NAME");
								colConsignorOut.setWidth(107);
								colConsignorOut.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colCosigneeOut = new TableColumn(tblGmrOuttime,
										SWT.NONE);
								colCosigneeOut.setText("CONSIGNEE NAME");
								colCosigneeOut.setWidth(123);
								colCosigneeOut.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colHiddenInwardTime = new TableColumn(
										tblGmrOuttime, SWT.NONE);
								colHiddenInwardTime.setText("INWARD TIME");
								colHiddenInwardTime.setWidth(0);
								colHiddenInwardTime.setResizable(false);

							}

						}
						{
							canvas4 = new Canvas(canvas3, SWT.BORDER);
							canvas4.setBounds(43, 349, 873, 141);
							{
								label3 = new Label(canvas4, SWT.NONE);
								label3.setText("Destination");
								label3.setBounds(19, 23, 54, 13);
							}
							{
								label4 = new Label(canvas4, SWT.NONE);
								label4.setText("Dest. Code");
								label4.setBounds(19, 88, 54, 13);
							}
							{
								cbDestStationName = new Combo(canvas4,
										SWT.DROP_DOWN);
								cbDestStationName.setBounds(85, 19, 151, 26);
							}

							cbDestStationName.addKeyListener(new KeyListener() {

								@Override
								public void keyPressed(KeyEvent e) {

									String selectedText = cbDestStationName
											.getText();
									if (e.keyCode == SWT.ARROW_UP
											|| e.keyCode == SWT.ARROW_DOWN
											|| e.keyCode == SWT.CR)
										return;

									if (e.character == '\b') {
										int len = selectedText.length();

										if (len > 1)
											selectedText = selectedText
													.substring(0, len - 2);
										else
											selectedText = "";

										cbDestStationName.remove(0,
												cbDestStationName
														.getItemCount() - 1);
										populateDestStationCodes();
									} else if (e.keyCode == 32
											|| (e.keyCode > 64 && e.keyCode < 91)
											|| (e.keyCode > 96 && e.keyCode < 123)) {
										selectedText += e.character;
									} else {
										e.doit = false;
										return;
									}

									if (selectedText.length() > 0) {
										selectedText = selectedText
												.toLowerCase();

										String[] items = cbDestStationName
												.getItems();
										int len = items.length;

										int startIndex = -1;
										for (int i = 0; i < len; i++) {
											String temp = items[i]
													.toLowerCase();
											if (temp.contains(selectedText)) {
												startIndex = i;
												break;
											}
										}

										if (startIndex == -1) {
											e.doit = false;
										} else {
											cbDestStationName
													.remove(0, len - 1);

											for (int i = startIndex; i < len; i++) {
												String temp = items[i]
														.toLowerCase();
												if (temp.contains(selectedText)) {
													cbDestStationName
															.add(items[i]);
												}
											}
											if (selectedText.length() == 1) {
												showPopup(cbDestStationName,
														true);
											}
										}
									}
								}

								@Override
								public void keyReleased(KeyEvent e) {
									String temp = cbDestStationName.getText();
									int len = temp.length();
									if (len == 0) {
										cbDestStationName.remove(0,
												cbDestStationName
														.getItemCount() - 1);
										populateDestStationCodes();
									}
								}
							});

							cbDestStationName
									.addFocusListener(new FocusListener() {
										@Override
										public void focusGained(FocusEvent arg0) {

										}

										@Override
										public void focusLost(FocusEvent arg0) {

											if (cbDestStationName
													.getSelectionIndex() == -1) {
												cbDestStationName.select(0);
											}
										}

									});
							{
								txtDestCode = new Text(canvas4, SWT.BORDER);
								txtDestCode.setBounds(87, 86, 151, 22);
								txtDestCode.addFocusListener(new GMRListener());

							}
							{
								label5 = new Label(canvas4, SWT.NONE);
								label5.setText("Vehicle No.");
								label5.setBounds(242, 21, 53, 13);
							}
							{
								label6 = new Label(canvas4, SWT.NONE);
								label6.setText("Driver");
								label6.setBounds(266, 89, 29, 13);
							}
							{
								cbVehicle = new CCombo(canvas4, SWT.BORDER);
								cbVehicle.setBounds(307, 17, 168, 28);
								cbVehicle.setTextLimit(15);
							}
							{
								txtDriverName = new Text(canvas4, SWT.BORDER);
								txtDriverName.setBounds(307, 86, 168, 26);
								txtDriverName.setTextLimit(30);

							}
							{
								chkMarketVehicle = new Button(canvas4,
										SWT.CHECK | SWT.LEFT);
								chkMarketVehicle.setText("Market Vehicle");
								chkMarketVehicle.setBounds(485, 50, 87, 30);
								chkMarketVehicle
										.addSelectionListener(new GMRListener());
							}
							{
								gpMargetVehile = new Group(canvas4, SWT.NONE);
								gpMargetVehile
										.setText("Market Vehicle Details");
								gpMargetVehile.setBounds(576, 0, 289, 136);
								{
									lblVehicleRate = new Label(gpMargetVehile,
											SWT.NONE);

									lblVehicleRate.setText("VehicleRate");
									lblVehicleRate.setBounds(17, 84, 77, 20);
								}
								{
									lblBrokerName = new Label(gpMargetVehile,
											SWT.NONE);
									lblBrokerName.setText("BrokerName");
									lblBrokerName.setBounds(17, 22, 77, 20);
								}
								{
									lblBrokerPhone = new Label(gpMargetVehile,
											SWT.NONE);
									lblBrokerPhone.setText("BrokerPhone");
									lblBrokerPhone.setBounds(17, 50, 77, 20);
								}
								{
									lblModelNo = new Label(gpMargetVehile,
											SWT.NONE);
									lblModelNo.setText("ModelNo");
									lblModelNo.setBounds(17, 112, 77, 20);
								}
								{
									txtBrokerName = new Text(gpMargetVehile,
											SWT.BORDER);
									txtBrokerName.setBounds(113, 18, 128, 20);
								}
								{
									txtBrokerPhone = new Text(gpMargetVehile,
											SWT.BORDER);
									txtBrokerPhone.setBounds(113, 47, 128, 20);
								}
								{
									txtVehicleRate = new Text(gpMargetVehile,
											SWT.BORDER);
									txtVehicleRate.setBounds(113, 80, 128, 20);
									txtVehicleRate
											.addVerifyListener(new FloatValidation());
								}
								{
									txtModelNo = new Combo(gpMargetVehile,
											SWT.BORDER | SWT.READ_ONLY);
									txtModelNo.setBounds(113, 112, 128, 20);
									
								}
							}

							gpMargetVehile.setEnabled(false);
						}
						if (!BeanUtil.getInstance().isSecondThread()
								|| BeanUtil.getInstance().isDiffStation()) {
							{
								btnDispatch = new Button(canvas3, SWT.PUSH
										| SWT.CENTER);
								btnDispatch.setText("Dispatch");
								btnDispatch.setBounds(822, 496, 75, 30);
								btnDispatch.setEnabled(false);
								btnDispatch
										.addSelectionListener(new GMRListener());

							}
							
							{
								btnDelivery = new Button(canvas3, SWT.PUSH
										| SWT.CENTER);
								btnDelivery.setText(" Delivery");
								if (!BeanUtil.getInstance().isBranch()) {

									btnDelivery.setBounds(42, 496, 89, 30);
									btnDelivery
											.addSelectionListener(new GMRListener());
								}
							}
							
							
							/*{
								btnSendSMS = new Button(canvas3, SWT.PUSH
										| SWT.CENTER);
								btnSendSMS.setText("Send SMS");
								btnSendSMS.setBounds(300, 496, 89, 30);			
								btnSendSMS.addSelectionListener(new GMRListener());
							}*/
						}

						/*
						 * tblGmrOuttime.addListener(SWT.Selection, new
						 * Listener() { public void handleEvent(Event event) {
						 * 
						 * if (event.detail== SWT.CHECK) {
						 * tblGmrOuttime.setSelection((TableItem) event.item); } }
						 * });
						 */
					}
				}

				{

					tiGmrReport = new TabItem(gmrTabFold, SWT.NONE);
					tiGmrReport.setText(REPORT_TAB_NAME);
					{
						canvas5 = new Canvas(gmrTabFold, SWT.BORDER);
						tiGmrReport.setControl(canvas5);

						{
							lblStatementNo = new Label(canvas5, SWT.NONE);
							lblStatementNo.setText("Statement No");
							lblStatementNo.setBounds(513, 13, 73, 24);
						}

						{
							txtStatementNo = new Text(canvas5, SWT.BORDER
									| SWT.READ_ONLY);
							txtStatementNo.setFont(SWTResourceManager.getFont(
									"Tahoma", 9, 1, false, false));
							txtStatementNo.setBounds(588, 9, 92, 22);
						}

						{
							btnGo = new Button(canvas5, SWT.PUSH | SWT.CENTER);
							btnGo.setText("Go");
							btnGo.setBounds(846, 8, 35, 24);
							btnGo.addSelectionListener(new GMRListener());
						}
						{
							txtDate = new Text(canvas5, SWT.BORDER
									| SWT.READ_ONLY);
							txtDate.setBounds(748, 9, 92, 22);
							DateFormat dateFormat = new SimpleDateFormat(
									"dd-MM-yyyy");
							java.util.Date currentDate = new java.util.Date();
							String date = dateFormat.format(currentDate);

							if (SERVER_DATE != null) {
								txtDate.setText(SERVER_DATE);
							} else {
								txtDate.setText(date);

							}
						}
						{
							lblDate = new Label(canvas5, SWT.NONE);
							lblDate.setText("Date");
							lblDate.setBounds(715, 13, 33, 20);
						}

						{
							btnDetails = new Button(canvas5, SWT.NONE);
							btnDetails.setText("Details");
							btnDetails.setBounds(835, 488, 67, 24);
							btnDetails.addSelectionListener(new GMRListener());
						}

						{
							tblGmrReport = new Table(canvas5, SWT.H_SCROLL
									| SWT.FULL_SELECTION | SWT.CHECK
									| SWT.V_SCROLL | SWT.BORDER | SWT.MULTI);
							tblGmrReport.setHeaderVisible(true);
							tblGmrReport.setLinesVisible(true);
							tblGmrReport.setBounds(28, 43, 872, 430);
							{
								colRSNo = new TableColumn(tblGmrReport,
										SWT.NONE);
								colRSNo.setText("S.No");
								colRSNo.setWidth(70);
								colRSNo.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colRDestination = new TableColumn(tblGmrReport,
										SWT.NONE);
								colRDestination.setText("DESTINATION");
								colRDestination.setWidth(100);
								colRDestination.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colRTimeOfDispatch = new TableColumn(
										tblGmrReport, SWT.NONE);
								colRTimeOfDispatch.setText("TIME OF DISPATCH");
								colRTimeOfDispatch.setWidth(150);
								colRTimeOfDispatch.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colRVehicleNo = new TableColumn(tblGmrReport,
										SWT.NONE);
								colRVehicleNo.setText("VEHICLE NO");
								colRVehicleNo.setWidth(120);
								colRVehicleNo.addListener(SWT.Selection,
										new sortListener());

							}
							{
								colRNoOfLr = new TableColumn(tblGmrReport,
										SWT.NONE);
								colRNoOfLr.setText("NO.OF LRs");
								colRNoOfLr.setWidth(80);
								colRNoOfLr.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colRTotalWeight = new TableColumn(tblGmrReport,
										SWT.NONE);
								colRTotalWeight.setText("TOTAL WEIGHT");
								colRTotalWeight.setWidth(110);
								colRTotalWeight.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colRNOA = new TableColumn(tblGmrReport,
										SWT.NONE);
								colRNOA.setText("NO.OF ARTICLE");
								colRNOA.setWidth(100);
								colRNOA.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colRDriverName = new TableColumn(tblGmrReport,
										SWT.NONE);
								colRDriverName.setText("DRIVER NAME");
								colRDriverName.setWidth(130);
								colRDriverName.addListener(SWT.Selection,
										new sortListener());
							}

						}
					}
				}

				gmrTabFold.setSelection(0);
				populateGMRInTime();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return this;
	}

	/**
	 * 
	 */
	private GMRDTO[] getSelectedInTimeRecords() throws Exception {
		TableItem[] items = tblgmrintime.getItems();
		int len = 0;
		GMRDTO dto = null;

		Date limitDate = null;
		
		ArrayList<GMRDTO> checkedList = new ArrayList<GMRDTO>();

		String stationCode = handler.getStationCode();

		if (null != items && (len = items.length) > 0) {
			for (int i = 0; i < len; i++) {
				if (items[i].getChecked()) {
					dto = new GMRDTO();
					dto.setLrNumber(items[i].getText(LR_IN_COLUMN));
					dto.setStationCode(stationCode);
					
					// For SMS
										
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			
					dto.setLrDate(dateFormat.parse(items[i].getText(2)));	
					dto.setLrTotal(items[i].getText(5));
					dto.setLrType(items[i].getText(6));					
					dto.setDestinationStn(items[i].getText(9));
					dto.setCnorName(items[i].getText(13));
					dto.setCneeName(items[i].getText(14));
					dto.setCnorPhone(items[i].getText(15));
					dto.setCneePhone(items[i].getText(16));					
					dto.setDestinationPhone(getPhoneNumber(handler.getStationCode()));
					if(!items[i].getText(17).equalsIgnoreCase(EMPTY_STRING))						
						dto.setSmsNotify(Integer.parseInt(items[i].getText(17)));
					if(!items[i].getText(18).equalsIgnoreCase(EMPTY_STRING))	
						dto.setRate_type(Integer.parseInt(items[i].getText(18)));
					
					if(!items[i].getText(7).equals(EMPTY_STRING) && Integer.parseInt(items[i].getText(7)) == 0 ){
						limitDate = dateFormat.parse("25-04-2010");
						if(dto.getLrDate().after(limitDate)){
							handleSMS(dto);
						}
					}
					
					checkedList.add(dto);
				}
			}
		}

		int size = checkedList.size();
		if (size > 0) {
			return (GMRDTO[]) checkedList.toArray(new GMRDTO[size]);
		}
		return null;
	}
		
	/**
	 * 
	 * @param errorText
	 */
	private void displayError(String errorText) {
		MessageBox messageBox = new MessageBox(shell, SWT.OK);
		messageBox.setMessage(errorText);
		messageBox.setText("Alert");
		messageBox.open();
	}

	/**
	 * 
	 */
	private String[][] getSelectedOutTimeRecords() {
		TableItem[] items = tblGmrOuttime.getItems();
		int len = 0;
		// ArrayList<String> checkedList = new ArrayList<String>();
		String[][] lrnowithinwardtime = null;

		if (null != items && (len = items.length) > 0) {
			int count = 0;

			for (int i = 0; i < len; i++) {
				if (items[i].getChecked()) {
					count++;
				}
			}
			if (count > 0) {
				lrnowithinwardtime = new String[count][5];
				int k = 0;
				for (int i = 0; i < len; i++) {
					if (items[i].getChecked()) {
						lrnowithinwardtime[k][0] = items[i]
								.getText(LR_IN_COLUMN);
						lrnowithinwardtime[k][1] = items[i]
								.getText(LAST_INWARD_COLUMN);
						lrnowithinwardtime[k][2] = items[i].getText(6);
						
						String temp = items[i].getText(11);
						if (temp.length() > 0){
							if(items[i].getText(2).equalsIgnoreCase("Topay")){
								lrnowithinwardtime[k][3] = temp;
							}else{
								lrnowithinwardtime[k][3] = "0";
							}							
						}

						lrnowithinwardtime[k++][4] = items[i].getText(14);
					}
				}
			}
		}

		return lrnowithinwardtime;
	}

	/**
	 * Method to Remove Selected GMROUTIME Records
	 * 
	 * @param lrnos
	 * @throws Exception
	 * @throws NamingException
	 */
	private void removeSelectedOutItems(String[][] lrnos)
			throws NamingException, Exception {
		int len = lrnos.length;
		int size = outTimeGoods.size();

		for (int i = 0; i < len; i++) {
			size = outTimeGoods.size();
			for (int j = 0; j < size; j++) {
				GMROutTimeDTO dto = (GMROutTimeDTO) outTimeGoods.get(j);
				if (lrnos[i][0].equals(dto.getLr_no())) {
					outTimeGoods.remove(j);
					break;
				}
			}
		}

		populateOutTimeTable();
	}

	/**
	 * 
	 */
	private void removeSelectedInItems(GMRDTO[] lrnos) {
		int len = lrnos.length;
		int size = inTimeGoods.size();

		for (int i = 0; i < len; i++) {
			size = inTimeGoods.size();
			for (int j = 0; j < size; j++) {
				GMRInTimeDTO dto = (GMRInTimeDTO) inTimeGoods.get(j);
				if (lrnos[i].getLrNumber().equals(dto.getLr_no())) {
					inTimeGoods.remove(j);
					break;
				}
			}
		}

		populateGMRInTimeTable();
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	private boolean validateOutTime(String[][] items) throws Exception {

		if (null == items || items.length == 0
				|| txtDestCode.getText().length() == 0
				|| txtDriverName.getText().length() == 0) {
			displayError("Mandatory parameter(s) missing");
			return false;
		}
		if (chkMarketVehicle.getSelection()) {
			if (txtModelNo.getText().length() == 0
					|| txtVehicleRate.getText().length() == 0
					|| txtBrokerName.getText().length() == 0
					|| txtBrokerPhone.getText().length() == 0) {
				displayError("Mandatory parameter(s) missing");
				return false;
			}
		}
		return true;
	}

	/**
	 * Method to avoid Delivery to the Current Station
	 * 
	 * @return
	 * @throws Exception
	 */
	private boolean validateDelivery() throws Exception {
		TableItem[] items = tblGmrOuttime.getItems();
		int len = items.length;
		BeanUtil util = BeanUtil.getInstance();
		String stationCode = util.getActingStationCode();

		if (null != items || len > 0) {
			for (int i = 0; i < len; i++) {
				if (items[i].getChecked()) {
					if (stationCode.equals(items[i].getText(6))) {
						displayError("Goods Can not  be Deliver/Dispacth to Same Station");
						return false;
					}
				}
			}
		}

		return true;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	private boolean validateDispatch() throws Exception {
		BeanUtil util = BeanUtil.getInstance();
		String stationCode = util.getActingStationCode();
		if (txtDestCode != null) {
			String same = txtDestCode.getText();
			if (stationCode.equals(same)) {
				displayError("Goods Can not  be Dispacth to Same Station");
				return false;
			}

		}
		String stationName = cbDestStationName.getText();
		if (stationName != "") {
			StationsDTO[] stations = null;
			try {
				stations = handler.getAvailableStations();
			} catch (Exception exception) {
				exception.printStackTrace();
			}

			for (int i = 0; i < stations.length; i++) {
				if (stationName.equals(stations[i].getName())) {

					if (!stations[i].getId().equals(txtDestCode.getText())) {
						displayError("Station Name And Code does not match");
						btnDispatch.setEnabled(false);
						return false;
					} else {
						btnDispatch.setEnabled(true);
					}
				}
			}
		}
		return true;
	}

	/**
	 * 
	 * @return
	 */

	private GMRDTO[] populateGMROutTimeDTO(String[][] lrnos) {
		int len = lrnos.length;

		GMRDTO[] gmrList = new GMRDTO[len];

		String stationCode = handler.getStationCode();
		String drivername = txtDriverName.getText();
		int index = cbVehicle.getSelectionIndex();
		String vehicleno = null;
		
		if (index > -1) {
			vehicleno = cbVehicle.getItem(index);
		} else {
			vehicleno = cbVehicle.getText();
			if (vehicleno.trim().length() == 0)
				vehicleno = null;
		}
		String dispatch = txtDestCode.getText();

		for (int i = 0; i < len; i++) {

			gmrList[i] = new GMRDTO();
			gmrList[i].setLrNumber(lrnos[i][0]);
			System.out.println("inwardtime"+lrnos[i][1]);
			gmrList[i].setLastInwardTime(lrnos[i][1]);
			gmrList[i].setDestinationStn(lrnos[i][2]);
			
			if(!lrnos[i][3].equals("")){
				gmrList[i].setCrTotal(Float.parseFloat(lrnos[i][3]));
			}			
			
			gmrList[i].setCneeName(lrnos[i][4]);
			
			
			//System.out.println("-->"+gmrList[i].getLrNumber()+"-->"+gmrList[i].getDestinationStn());
			
			if (null != drivername && drivername.trim().length() > 0) {
				gmrList[i].setDriverName(drivername);
			}
			System.out.println("station_code"+stationCode);
			System.out.println("vehicle_no "+vehicleno);
			gmrList[i].setStationCode(stationCode);
			gmrList[i].setVehicleNumber(vehicleno);
			
			
			
			if (dispatch.length() > 0) {
				gmrList[i].setDispatchTo(dispatch);
			}

			if (i == 0 && chkMarketVehicle.getSelection()) {
				gmrList[i].setMarketVehile(true);

				String temp = null;
				temp = txtModelNo.getText();
				gmrList[i].setModelNo(temp);

				temp = txtBrokerName.getText();
				gmrList[i].setVehicleName(temp);

				temp = txtBrokerPhone.getText();
				gmrList[i].setVehiclePhone(temp);

				temp = txtVehicleRate.getText();
				if (temp != null && !temp.equals("")) {
					gmrList[i].setVehicleRate(Float.parseFloat(temp));
				}

			}
		}

		return gmrList;

	}

	private void SortByDateTime(int index, Table refTable) {
		try {

			TableItem[] items = refTable.getItems();

			SimpleDateFormat parse = new SimpleDateFormat("dd-MM-yyyy HH:mm");

			Date date1 = null;
			Date date2 = null;

			int len = items.length;

			// Sorting need to happen only if the number of rows available
			// is greater than one excluding total
			if (len > 2) {
				if (refTable != tblGmrReport)
					len--;
				for (int i = 1; i < len; i++) {
					if (!items[i].getText(index).isEmpty()) {

						date1 = parse.parse(items[i].getText(index));

						for (int j = 0; j < i; j++) {
							if (!items[j].getText(index).isEmpty()) {

								date2 = parse.parse(items[j].getText(index));

							}

							if (date1.before(date2)) {
								String[] values = { items[i].getText(0),
										items[i].getText(1),
										items[i].getText(2),
										items[i].getText(3),
										items[i].getText(4),
										items[i].getText(5),
										items[i].getText(6),
										items[i].getText(7),
										items[i].getText(8),
										items[i].getText(9),
										items[i].getText(10),
										items[i].getText(11),
										items[i].getText(12),
										items[i].getText(13),
										items[i].getText(14),
										items[i].getText(15) };
								items[i].dispose();
								TableItem item = new TableItem(refTable,
										SWT.NONE, j);
								item.setText(values);
								items = refTable.getItems();
								break;
							}
						}
					}
				}
			}
		} catch (Exception exp) {
			exp.printStackTrace();
		}

	}

	/**
	 * 
	 */

	public class sortListener implements Listener {

		/**
		 * 
		 */
		private void sortByLrNo(int index, Table refTable) {
			TableItem[] items = refTable.getItems();

			int value1 = 0;
			int value2 = 0;

			int len = items.length;

			// Sorting need to happen only if the number of rows available
			// is greater than one excluding total
			if (len > 2) {
				len = len - 1; // Ignoring the last row which is meant to be
				// Total

				for (int i = 1; i < len; i++) {
					if (!items[i].getText(index).isEmpty()) {
						value1 = Integer.parseInt(items[i].getText(index)
								.substring(1));
						for (int j = 0; j < i; j++) {
							value2 = Integer.parseInt(items[j].getText(index)
									.substring(1));
							if (value1 < value2) {
								String[] values = { items[i].getText(0),
										items[i].getText(1),
										items[i].getText(2),
										items[i].getText(3),
										items[i].getText(4),
										items[i].getText(5),
										items[i].getText(6),
										items[i].getText(7),
										items[i].getText(8),
										items[i].getText(9),
										items[i].getText(10),
										items[i].getText(11),
										items[i].getText(12),
										items[i].getText(13),
										items[i].getText(14),
										items[i].getText(15),
										items[i].getText(16),
										items[i].getText(17),
										items[i].getText(18)};
								items[i].dispose();
								TableItem item = new TableItem(refTable,
										SWT.NONE, j);
								item.setText(values);
								items = refTable.getItems();
								break;
							}
						}
					}
				}
			}

		}

		/**
		 * 
		 * @param index
		 * @param isOutTime
		 */
		private void sortByString(int index, Table refTable) {
			if (refTable != tblGmrReport)
				sortByLrNo(1, refTable);
			TableItem[] items = refTable.getItems();
			Collator collator = Collator.getInstance(Locale.getDefault());

			int len = items.length;

			// Sorting need to happen only if the number of rows available
			// is greater than one excluding total
			if (len > 2) {
				if (refTable != tblGmrReport)
					len--; // Ignoring the last row as it is meant to be Total

				for (int i = 1; i < len; i++) {
					if (!items[i].getText(index).isEmpty()) {
						String value1 = items[i].getText(index);
						for (int j = 0; j < i; j++) {
							String value2 = items[j].getText(index);
							if (collator.compare(value1, value2) < 0) {
								String[] values = { items[i].getText(0),
										items[i].getText(1),
										items[i].getText(2),
										items[i].getText(3),
										items[i].getText(4),
										items[i].getText(5),
										items[i].getText(6),
										items[i].getText(7),
										items[i].getText(8),
										items[i].getText(9),
										items[i].getText(10),
										items[i].getText(11),
										items[i].getText(12),
										items[i].getText(13),
										items[i].getText(14),
										items[i].getText(15),										
										items[i].getText(16),
										items[i].getText(17),
										items[i].getText(18)										
										};
								items[i].dispose();

								TableItem item = new TableItem(refTable,
										SWT.NONE, j);
								item.setText(values);
								items = refTable.getItems();
								break;
							}
						}
					}
				}
			}
		}

		private void SortByDate(int index, Table refTable) {
			try {

				if (refTable != tblGmrReport)
					sortByLrNo(1, refTable);
				TableItem[] items = refTable.getItems();

				SimpleDateFormat parse = new SimpleDateFormat("dd-MM-yyyy");

				Date date1 = null;
				Date date2 = null;

				int len = items.length;

				// Sorting need to happen only if the number of rows available
				// is greater than one excluding total
				if (len > 2) {
					if (refTable != tblGmrReport)
						len--;
					for (int i = 1; i < len; i++) {
						if (!items[i].getText(index).isEmpty()) {

							date1 = parse.parse(items[i].getText(index));

							for (int j = 0; j < i; j++) {
								if (!items[j].getText(index).isEmpty()) {

									date2 = parse
											.parse(items[j].getText(index));

								}

								if (date1.before(date2)) {
									String[] values = { items[i].getText(0),
											items[i].getText(1),
											items[i].getText(2),
											items[i].getText(3),
											items[i].getText(4),
											items[i].getText(5),
											items[i].getText(6),
											items[i].getText(7),
											items[i].getText(8),
											items[i].getText(9),
											items[i].getText(10),
											items[i].getText(11),
											items[i].getText(12),
											items[i].getText(13),
											items[i].getText(14),
											items[i].getText(15),	
											items[i].getText(16),
											items[i].getText(17),
											items[i].getText(18)	 };
									items[i].dispose();
									TableItem item = new TableItem(refTable,
											SWT.NONE, j);
									item.setText(values);
									items = refTable.getItems();
									break;
								}
							}
						}
					}
				}
			} catch (Exception exp) {
				exp.printStackTrace();
			}

		}

		/**
		 * 
		 */
		private void SortByFloat(int index, Table refTable) {
			if (refTable != tblGmrReport)
				sortByLrNo(1, refTable);
			TableItem[] items = refTable.getItems();
			float value2 = 0;
			float value1 = 0;
			int len = items.length;

			// Sorting need to happen only if the number of rows available
			// is greater than one excluding total
			if (len > 2) {
				if (refTable != tblGmrReport)
					len--;
				for (int i = 1; i < len; i++) {
					if (!items[i].getText(index).isEmpty()) {

						value1 = Float.parseFloat(items[i].getText(index));

						for (int j = 0; j < i; j++) {
							if (!items[j].getText(index).isEmpty())
								value2 = Float.parseFloat(items[j]
										.getText(index));

							if (value1 < value2) {
								String[] values = { items[i].getText(0),
										items[i].getText(1),
										items[i].getText(2),
										items[i].getText(3),
										items[i].getText(4),
										items[i].getText(5),
										items[i].getText(6),
										items[i].getText(7),
										items[i].getText(8),
										items[i].getText(9),
										items[i].getText(10),
										items[i].getText(11),
										items[i].getText(12),
										items[i].getText(13),
										items[i].getText(14),
										items[i].getText(15),	
										items[i].getText(16),
										items[i].getText(17),
										items[i].getText(18)	 };

								items[i].dispose();
								TableItem item = new TableItem(refTable,
										SWT.NONE, j);
								item.setText(values);
								items = refTable.getItems();
								break;
							}
						}
					}
				}
			}

		}

		/**
		 * 
		 */
		public void handleEvent(Event e) {
			int selectionIndex = gmrTabFold.getSelectionIndex();
			TableColumn column = (TableColumn) e.widget;

			if (selectionIndex == 1) {
				if (column == colOutSNo) {
					SortByFloat(0, tblGmrOuttime);
				} else if (column == colOutLrNo) {
					sortByLrNo(1, tblGmrOuttime);
				} else if (column == colOutLrType) {
					sortByString(2, tblGmrOuttime);
				} else if (column == colOutDate) {
					SortByDate(3, tblGmrOuttime);
				} else if (column == colOutNdsb) {
					SortByFloat(4, tblGmrOuttime);
				} else if (column == colOutNdsi) {
					SortByFloat(5, tblGmrOuttime);
				} else if (column == colOutFrom) {
					sortByString(6, tblGmrOuttime);
				} else if (column == colOutTo) {
					sortByString(7, tblGmrOuttime);
				} else if (column == colOutNOA) {
					SortByFloat(8, tblGmrOuttime);
				} else if (column == colOutWeight) {
					SortByFloat(9, tblGmrOuttime);
				} else if (column == colOutDDC) {
					SortByFloat(10, tblGmrOuttime);
				} else if (column == colOutLRTotal) {
					SortByFloat(11, tblGmrOuttime);
				} else if (column == colOutArticleValue) {
					SortByFloat(12, tblGmrOuttime);
				} else if (column == colConsignorOut) {
					sortByString(13, tblGmrOuttime);
				} else if (column == colCosigneeOut) {
					sortByString(14, tblGmrOuttime);
				}
			} else if (selectionIndex == 0) {
				if (column == colFrom) {
					sortByString(8, tblgmrintime);
				} else if (column == colTo) {
					sortByString(9, tblgmrintime);
				} else if (column == colLr) {
					sortByLrNo(1, tblgmrintime);
				} else if (column == colSNo) {
					SortByFloat(0, tblgmrintime);
				} else if (column == colDate) {
					SortByDate(2, tblgmrintime);
				} else if (column == colSentDate) {
					SortByDate(3, tblgmrintime);
				} else if (column == colSentDays) {
					SortByFloat(4, tblgmrintime);
				} else if (column == colFreight) {
					SortByFloat(5, tblgmrintime);
				} else if (column == colPTB) {
					sortByString(6, tblgmrintime);
				} else if (column == colDDc) {
					SortByFloat(7, tblgmrintime);
				} else if (column == colArticleNo) {
					SortByFloat(10, tblgmrintime);
				} else if (column == colActualWeight) {
					SortByFloat(11, tblgmrintime);
				} else if (column == colVehicle) {
					sortByString(12, tblgmrintime);
				} else if (column == colCosignor) {
					sortByString(13, tblgmrintime);
				} else if (column == colCosignee) {
					sortByString(14, tblgmrintime);
				}
			} else if (selectionIndex == 2) {
				if (column == colRSNo) {
					SortByFloat(0, tblGmrReport);
				} else if (column == colRDestination) {
					sortByString(1, tblGmrReport);
				} else if (column == colRTimeOfDispatch) {
					SortByDateTime(2, tblGmrReport);
				} else if (column == colRVehicleNo) {
					sortByString(3, tblGmrReport);
				} else if (column == colRNoOfLr) {
					SortByFloat(4, tblGmrReport);
				} else if (column == colRTotalWeight) {
					SortByFloat(5, tblGmrReport);
				} else if (column == colRNOA) {
					SortByFloat(6, tblGmrReport);
				} else if (column == colRDriverName) {
					sortByString(7, tblGmrReport);
				}
			}
		}
	}

	/**
	 * 
	 */
	public class GMRListener implements SelectionListener,
			org.eclipse.swt.events.FocusListener {

		public void widgetSelected(SelectionEvent event) {
			Object source = event.getSource();
			String selectedTab = gmrTabFold.getSelection()[0].getText();
			try {

				if (gmrTabFold == source) {

					if (OUT_TIME_TAB_NAME.equals(selectedTab)) {
						populateOutTimeForm();
						lrrangeDTO = handler.getCRRange(handler.getStationCode());
							
					} else if (IN_TIME_TAB_NAME.equals(selectedTab)) {
						populateGMRInTime();
					} else if (REPORT_TAB_NAME.equals(selectedTab)) {
						populateGMRRecords(false);
					}
				} else if (btnAssignInTime == source) {
					btnAssignInTime.setEnabled(false);
					GMRDTO[] gmrDtoList = getSelectedInTimeRecords();
					
					if (null != gmrDtoList && handler.assignGoods(gmrDtoList)) {
						removeSelectedInItems(gmrDtoList);
						if (null != outTimeGoods)
							outTimeGoods.clear();
					}					
					btnAssignInTime.setEnabled(true);					
					
				} else if (btnDelivery == source) {
					if (isDeliveryRunning == false) {
						isDeliveryRunning = true;
						try {
							btnDelivery.setEnabled(false);
							btnDispatch.setEnabled(false);
							String[][] lrnos = getSelectedOutTimeRecords();
							if (null != lrnos) {
								BeanUtil util = BeanUtil.getInstance();
								if (util.isAdmin()
										|| util.isAdminHead()
										|| util.isAdminHeadStationary()
										|| util.getActingStationCode().equals(
												"HSR") || validateDelivery()) {
									
									System.out.println("in-->");
									GMRDTO[] gmrDtoList = populateGMROutTimeDTO(lrnos);

									try {
										// CR create logic
										gmrDtoList = buildCashDTO(gmrDtoList);
										System.out.println("got CR-->");
									
										if (handler.deliverGoods(gmrDtoList)) {
											removeSelectedOutItems(lrnos);
											if (null != inTimeGoods)
												inTimeGoods.clear();
											createCRSMS();											
										}										
									} catch (BusinessException e) {
										isDeliveryRunning = false;
										displayError(e.getResponseMessage());
									}
								}
							} else {
								displayError("Please select Atleast One LR");
							}
							isDeliveryRunning = false;
							btnDelivery.setEnabled(true);
							btnDispatch.setEnabled(true);
						} catch (Exception e) {
							isDeliveryRunning = false;
							e.printStackTrace();
						}
					} else {
						displayError("Already running Please wait");
					}

				} else if (btnDispatch == source) {

					if (isDispatchRunning == false) {
						isDispatchRunning = true;
						try {
							btnDispatch.setEnabled(false);
							btnDelivery.setEnabled(false);

							String[][] lrnos = getSelectedOutTimeRecords();
							if (null != lrnos) {
								if (validateDispatch()) {
									if (validateOutTime(lrnos)) {
										GMRDTO[] gmrDtoList = populateGMROutTimeDTO(lrnos);
										if (handler.dispatchGoods(gmrDtoList)) {
											chkMarketVehicle
													.setSelection(false);
											gpMargetVehile.setEnabled(false);
											txtBrokerName.setText("");
											txtBrokerPhone
													.setText(EMPTY_STRING);
											txtModelNo.setText("");
											txtVehicleRate.setText("");

											cbDestStationName.setText("");
											txtDestCode.setText("");
											txtDriverName.setText("");
											cbVehicle.setText("");

											removeSelectedOutItems(lrnos);
											if (null != inTimeGoods) {
												inTimeGoods.clear();
											}

										}
									}
								}
							} else {
								displayError("Please select Atleast One LR");
							}
							isDispatchRunning = false;
							btnDelivery.setEnabled(true);
						} catch (BusinessException business) {
							isDispatchRunning = false;
							displayError(business.getResponseMessage());

						} catch (Exception exception) {
							exception.printStackTrace();
							isDispatchRunning = false;
							displayError("Error occured while booking. Please try again");
						}
					} else {
						displayError("Already running Please wait");
					}

				} else if (btnGo == source) {
					KalendarDialog cd = new KalendarDialog(shell);
					Object obj = cd.open();
					if (obj != null) {
						txtDate.setText(obj.toString());
					}

					String statementNo = calculateStatementNumber(txtDate
							.getText(), "GMR");
					if (null != statementNo)
						txtStatementNo.setText(statementNo);

					if (REPORT_TAB_NAME.equals(selectedTab)) {
						populateGMRRecords(true);
					}

				} else if (btnDetails == source) {

					boolean isconsignor = false;
					ArrayList set = new ArrayList();
					ArrayList dateset = new ArrayList();
					TreeSet tree = new TreeSet();

					TableItem[] items = tblGmrReport.getItems();
					int len = 0;
					int checkedItems = 0;
					String tblDispatchTo = null;
					String tblDispatchTime = null;
					String tostation = null;
					int index = 0;
					ArrayList<GMRReportDTO> list = new ArrayList<GMRReportDTO>();

					ArrayList<Integer> indexes = new ArrayList<Integer>();
					if (null != items && (len = items.length) > 0) {
						for (int i = 0; i < len; i++) {
							if (items[i].getChecked()) {
								index = i;
								checkedItems++;
								indexes.add(index);
							}
						}
						int size = indexes.size();
						Integer[] selectedindex = indexes
								.toArray(new Integer[size]);

						for (int j = 0; j < selectedindex.length; j++) {
							tblDispatchTo = items[selectedindex[j]].getText(1);

							tblDispatchTime = items[selectedindex[j]]
									.getText(2);
							if (!tblDispatchTo.equalsIgnoreCase("Delivery")) {
								set.add(tblDispatchTo);
								dateset.add(tblDispatchTime);
								tree.add(tblDispatchTo);
							}

						}
						String fromstations = handler.getStationCode();

						Iterator itration = tree.iterator();
						while (itration.hasNext()) {
							if (getToStationType((String) itration.next(),
									fromstations)) {
								isconsignor = true;
								break;
							}
						}

						int t = set.size();
						if (t > 0) {
							Shell popPriority = new Shell(shell,
									SWT.PRIMARY_MODAL | SWT.TOP);
							GMRPriorityPop popref = new GMRPriorityPop(
									popPriority, 0, set, dateset, tree, handler
											.getStationCode());

							popref.loadComposite();
							String[][] finalpri = popref.pri;
							tostation = popref.to;

							int temp = 1;
							while (temp <= finalpri.length) {
								for (int a = 0; a < finalpri.length; a++) {
									String tempcomp = String.valueOf(temp);
									if (finalpri[a][2].equals(tempcomp)) {
										for (int j = 0; j < selectedindex.length; j++) {
											tblDispatchTo = items[selectedindex[j]]
													.getText(1);
											tblDispatchTime = items[selectedindex[j]]
													.getText(2);
											if (tblDispatchTo
													.equals(finalpri[a][0])
													&& tblDispatchTime
															.equals(finalpri[a][1])) {
												int length = gmr.length;
												for (int gmrcount = 0; gmrcount < length; gmrcount++) {

													DateFormat dateFormat = new SimpleDateFormat(
															"dd-MM-yyyy HH:mm:ss");
													String vehicledate = dateFormat
															.format(gmr[gmrcount]
																	.getVehicleDate());
													int index1 = vehicledate
															.indexOf(":");
													vehicledate = vehicledate
															.substring(0,
																	index1 + 3);
													if ((tblDispatchTo
															.equals(gmr[gmrcount]
																	.getMoved_to()) && tblDispatchTime
															.equals(vehicledate))
															|| ((tblDispatchTo == "Delivery" && gmr[gmrcount]
																	.getMoved_to() == null) && (tblDispatchTime
																	.equals(vehicledate)))) {
														list.add(gmr[gmrcount]);
													}
												}

											}

										}

									}
								}
								temp++;
							}
						}

						for (int j = 0; j < selectedindex.length; j++) {
							tblDispatchTo = items[selectedindex[j]].getText(1);
							if (tblDispatchTo.equals("Delivery")) {
								tblDispatchTo = "";
								tblDispatchTime = items[selectedindex[j]]
										.getText(2);

								int length = gmr.length;
								for (int gmrcount = 0; gmrcount < length; gmrcount++) {
									/*
									 * String vehicledate = gmr[gmrcount]
									 * .getVehicleDate().toString();
									 */

									DateFormat dateFormat = new SimpleDateFormat(
											"dd-MM-yyyy HH:mm:ss");
									String vehicledate = dateFormat
											.format(gmr[gmrcount]
													.getVehicleDate());
									int index1 = vehicledate.indexOf(":");
									vehicledate = vehicledate.substring(0,
											index1 + 3);
									if ((tblDispatchTo.equals(gmr[gmrcount]
											.getMoved_to()) && tblDispatchTime
											.equals(vehicledate))
											|| ((tblDispatchTo == "" && gmr[gmrcount]
													.getMoved_to() == null) && (tblDispatchTime
													.equals(vehicledate)))) {
										list.add(gmr[gmrcount]);
									}
								}

							}

						}

						// }
					}

					int size = list.size();

					if (size > 0) {
						Shell popShell = new Shell(shell, SWT.PRIMARY_MODAL
								| SWT.TOP);
						new GMRReportPop(popShell, 0, txtStatementNo.getText(),
								txtDate.getText(), list
										.toArray(new GMRReportDTO[size]),
								tostation, handler.getStationCode(),
								isconsignor).loadComposite();
					}
					populateGMRRecords(true);
				}

				else if (source == chkMarketVehicle) {
					if (chkMarketVehicle.getSelection()) {
						gpMargetVehile.setEnabled(true);
						try{
							txtModelNo.removeAll();
							VehicleDTO[] dto = handler.getVehicles();
							if(null != dto){
								int len = dto.length;
								if (len > 0) {
									for (int i = 0; i < len; i++) {
										txtModelNo.add(dto[i].getVehicle_model());
									}
								}
							}
						}catch(Exception e){
							
						}
						
						
					} else {
						gpMargetVehile.setEnabled(false);
						txtBrokerName.setText("");
						txtBrokerPhone.setText(EMPTY_STRING);
						txtModelNo.setText("");
						txtVehicleRate.setText("");
					}
				}
			} catch (RemoteException remoteexception) {
				remoteexception.printStackTrace();

			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}

		
		
		private void createCRSMS() {

			try {
				CashDTO[] dto = buildCashDTO();
				if (null != dto && dto.length > 0) {				
					handler.submitCashReceipt(dto);
				}
			} catch (BusinessException b) {/*				
				System.out.println(b.getResponseMessage());
				String newLastUsed = "";
				newLastUsed = b.getResponseMessage();
				if(!newLastUsed.equals("")){
					CashDTO[] dto = buildCashDTO(newLastUsed);
					if (null != dto && dto.length > 0) {
						try {
							handler.submitCashReceipt(dto);
						}catch (BusinessException be) {
							System.out.println("CR exists. trying again: "+be.getResponseMessage());
							//be.printStackTrace();
						}catch (Exception e) {							
							e.printStackTrace();
						}
					}
				}
				
			*/} catch (Exception e) {
				e.printStackTrace();
			}	
			
		}



		private GMRDTO[] buildCashDTO(GMRDTO[] gmr) {
			try {
				
				int len = 0;
				if(gmr != null){
					len = gmr.length;
				}
				if (len == 0) {
					return null;
				}
			
				ArrayList<String> list = populateCRStationary(false);

				if (null != list) {
					int size = list.size();
					//System.out.println("inital list size-->"+list.size());
					while (size - len < 20) {
						BeanUtil util = BeanUtil.getInstance();
						LRNumberRangeDTO[] dto = new LRNumberRangeDTO[1];
						dto[0] = new LRNumberRangeDTO();
						dto[0].setBilling(0);
						dto[0].setCr(1);
						dto[0].setPaid(0);
						dto[0].setTopay(0);
						dto[0].setStationCode(util.getActingStationCode());
						util.assignStationary(dto);

						System.out.println("in populate cr station");
						
						list = populateCRStationary(true);
						if (null != list) {
							size = list.size();
						}

					}

				}			
								
				for (int i = 0; i < len; i++) {		
					gmr[i].setCrNo(list.get(i));					
					System.out.println("CR-->"+list.get(i));					
				}					
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return gmr;

		}

		
		/**
		 * 
		 * @return
		 */
		private CashDTO[] buildCashDTO() {
			try {
				TableItem[] items = tblGmrOuttime.getItems();
				int len = 0;
				int cheked = 0;
				if (null != items && (len = items.length) > 0) {
					for (int k = 0; k < len; k++) {
						if (items[k].getChecked()) {
							cheked++;
						}
					}
				}
				if (cheked == 0) {
					return null;
				}
				ArrayList<CashDTO> checkedList = new ArrayList<CashDTO>();
				ArrayList<SMSDTO> smsList = new ArrayList<SMSDTO>();

			
				
				if (null != items && (len = items.length) > 0) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				
					for (int i = 0; i < len - 1; i++) {

						if (items[i].getChecked()) {
							
							CashDTO cashdto = new CashDTO();
							String temp = null;
							temp = items[i].getText(1);
						
							cashdto.setLr_no(temp);
							temp = items[i].getText(3);
							cashdto.setLr_date(dateFormat.parse(temp));

						
							cashdto.setStation_code(handler.getStationCode());
							cashdto.setTo_station(items[i].getText(7));
							//float actual_weight = Float.parseFloat(items[i].getText(9));
							
							temp = items[i].getText(10);
							cashdto.setDdc(Float.parseFloat(temp));
														
							temp = items[i].getText(11);
							if (temp.length() > 0){
								if(items[i].getText(2).equalsIgnoreCase("Topay")){
									cashdto.setTotal_amount(Float.parseFloat(temp));
								}else{
									cashdto.setTotal_amount(0);
								}
								
							}

							cashdto.setConsignorName(items[i].getText(13));
							cashdto.setConsigneeName(items[i].getText(14));

							/*cashdto.setCnor_phone(items[i].getText(18));
							cashdto.setCnee_phone(items[i].getText(19));
							
							cashdto.setRate_type(Integer.parseInt(items[i]
									.getText(21)));
							cashdto.setSms_alert(Integer.parseInt(items[i]
									.getText(22)));*/
							
							//float dc_commission = getDeliveryCommission(actual_weight);
							//dc_commission = RoundOff.getRounded2Decimal(dc_commission);
							//System.out.println("DDCCC--->" + dc_commission);

							//cashdto.setDelivery_ommission(dc_commission);

							boolean isDoorDelivery = isDoorDelivey(cashdto);

							/*
							 * if (isDoorDelivery) { isConsignee =
							 * isDoorDeliveryInDestination(cashdto); }
							 */

							String type = items[i].getText(2);

							SMSDTO smsdto = null;
							Date limitDate = null;
							String curBranch = null;
							String destBranch = null;

							limitDate = dateFormat.parse("25-04-2010");
							curBranch = getBranch(handler.getStationCode());
							destBranch = getBranch(cashdto.getTo_station());

							if (cashdto.getLr_date().after(limitDate)
									&& curBranch.equalsIgnoreCase(destBranch)) {
								smsdto = createSMSMessage(isDoorDelivery, type,
										cashdto, dateFormat.parse(items[i]
												.getText(3)));
							}
							if (null != smsdto)
								smsList.add(smsdto);
							/*
							 * if (isConsignee) sendSMS(cashdto.getCnee_phone(),
							 * message); else sendSMS(cashdto.getCnor_phone(),
							 * message);
							 */
						
							checkedList.add(cashdto);							
						}
					}					
				}

				int smssize = smsList.size();
				SMSDTO[] to_add = null;
				if (smssize > 0) {
					to_add = (SMSDTO[]) smsList.toArray(new SMSDTO[smssize]);
				}

				int size = checkedList.size();
				if (size > 0) {
					CashDTO[] dto = (CashDTO[]) checkedList
							.toArray(new CashDTO[size]);
					dto[0].setSms(to_add);
					return dto;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;

		}
		
		/**
		 * 
		 * @param actual_weight
		 * @return
		 * @throws Exception
		 */
		private float getDeliveryCommission(float actual_weight) throws Exception {
			float dc_commission_in = 0;
			dc_commission_in = (actual_weight / 1000 * getDCCommissionPerTon());
			return dc_commission_in;
		}
		
		/**
		 * 
		 * @return
		 * @throws Exception
		 */
		private float getDCCommissionPerTon() throws Exception {

			DCProfileDTO[] dcProfiledto = handler.getDCDetails();
			if (null != dcProfiledto) {
				for (int k = 0; k < dcProfiledto.length; k++) {
					if (dcProfiledto[k].getStation_code().equalsIgnoreCase(
							handler.getStationCode())) {
						//System.out.println("Assigned DC:"+ dcProfiledto[k].getDc_per_ton());
						return dcProfiledto[k].getDc_per_ton();
					}

				}
			}
			return 0;
		}
		
		private boolean isDoorDelivey(CashDTO cashdto) {
			if (cashdto.getDd_extra() != 0 || cashdto.getDdc() != 0) {
				return true;
			}
			return false;
		}
		
		private ArrayList<String> populateCRStationary(boolean callUnused) {
			ArrayList<String> list = new ArrayList<String>();
			String usedCR = null;
			try {				
				usedCR = handler.getLastUsedCR(handler.getStationCode());
				System.out.println("last used->"+usedCR);				
				
			} catch (Exception e) {
				e.printStackTrace();
			}

			ArrayList<LRNumberRangeDTO> unusedLrlist = null;
			try {
				unusedLrlist = getUnUsedCRList(usedCR, callUnused);
				//System.out.println("unusedLst--->"+unusedLrlist);				
			} catch (Exception e) {
				e.printStackTrace();
			}

			int size = 0;
			String type = "";
			int cr = 1;
			if (null != unusedLrlist && (size = unusedLrlist.size()) > 1) {
				for (int display = 1; display < size; display++) {

					LRNumberRangeDTO dto = (LRNumberRangeDTO) unusedLrlist
							.get(display);
					type = dto.getType();

					if (type.equalsIgnoreCase("CR")) {
						list.add(dto.getLrNumber());
						cr++;
					}
					System.out.println("crnos"+dto.getLrNumber());
				}

			}
			return list;
		}
		
		public ArrayList<LRNumberRangeDTO> getUnUsedCRList(String crno, boolean callUnused)
		throws Exception {

			int len = 0;
			LRNumberRangeDTO temp = null;
			ArrayList<LRNumberRangeDTO> unusedList = new ArrayList<LRNumberRangeDTO>();
			int fromNumber = 0;
			int toNumber = 0;
			String stationcode = handler.getStationCode();
			String lrprefix;
			String lrNumber = null;
			boolean isAlreadyAvail = true;			
			
			if(lrrangeDTO == null || callUnused){
				lrrangeDTO = handler.getCRRange(handler.getStationCode());				
			}

			if (null != lrrangeDTO && (len = lrrangeDTO.length) > 0) {
				for (int i = len - 1; i > -1; i--) {
					temp = (LRNumberRangeDTO) lrrangeDTO[i];
					if (temp.getStationCode().equalsIgnoreCase(stationcode)) {
						lrprefix = temp.getRangeFrom().substring(0, 1);
						fromNumber = Integer.parseInt(temp.getRangeFrom()
								.substring(1));
						toNumber = Integer.parseInt(temp.getRangeTo()
								.substring(1));

						for (int j = fromNumber; j <= toNumber; j++) {
							lrNumber = (lrprefix + "") + j;
							if (null != crno) {
								if (lrNumber.equalsIgnoreCase(crno)) {
									isAlreadyAvail = false;
								}
							}

							if (!isAlreadyAvail || null == crno) {
								LRNumberRangeDTO tempdto = new LRNumberRangeDTO();
								tempdto.setType(temp.getType());
								tempdto.setLrNumber(lrNumber);
								unusedList.add(tempdto);
							}
						}
					}

				}

				if (isAlreadyAvail) {
					for (int i = len - 1; i > -1; i--) {
						temp = (LRNumberRangeDTO) lrrangeDTO[i];
						if (temp.getStationCode().equalsIgnoreCase(stationcode)) {
							lrprefix = temp.getRangeFrom().substring(0, 1);
							fromNumber = Integer.parseInt(temp.getRangeFrom()
									.substring(1));
							toNumber = Integer.parseInt(temp.getRangeTo()
									.substring(1));

							for (int j = fromNumber; j <= toNumber; j++) {
								lrNumber = (lrprefix + "") + j;

								LRNumberRangeDTO tempdto = new LRNumberRangeDTO();
								tempdto.setType(temp.getType());
								tempdto.setLrNumber(lrNumber);
								unusedList.add(tempdto);

							}
						}

					}
				}
			}

			return unusedList;
		}
		
		/**
		 * 
		 * @param isDoorDelivery
		 * @param lr_type
		 * @param dto
		 * @return
		 */
		private SMSDTO createSMSMessage(boolean isDoorDelivery, String lr_type,
				CashDTO dto, Date inward) {

			SMSDTO temp = null;
			DateFormat smsFormat = new SimpleDateFormat("dd/MMM/yy");
			boolean send = false;
			String currStn = handler.getStationCode();
			try {
				String ConsignorMsg = null;
				String ConsigneeMsg = null;

				if (isDoorDelivery) {
					if (lr_type.equalsIgnoreCase("Topay")) {

						ConsignorMsg = "Greetings from AKR! Your LR "
								+ dto.getLr_no()
								+ " dt "
								+ smsFormat.format(dto.getLr_date())
								+ " to "
								+ getTruncatedName(dto.getConsigneeName(), 20)
								+ " is out for delivery and will be delivered around "
								+ Hour.getHour(3) + ".Thank you for using AKR!";

						ConsigneeMsg = "AKR-Greetings.LR " + dto.getLr_no()
								+ " dt " + smsFormat.format(dto.getLr_date())
								+ " from "
								+ getTruncatedName(dto.getConsignorName(), 20)
								+ " will be delivered around " + Hour.getHour(3)
								+ ".Total is Rs." + (int) dto.getTotal_amount()
								+ ".Pl inform " + getPhoneNumber(currStn.trim())
								+ "  if you are unavailable";

					} else if (lr_type.equalsIgnoreCase("Paid")) {

						ConsignorMsg = "Greetings from AKR! Your LR "
								+ dto.getLr_no()
								+ " dt "
								+ smsFormat.format(dto.getLr_date())
								+ " to "
								+ getTruncatedName(dto.getConsigneeName(), 20)
								+ " is out for delivery and will be delivered around "
								+ Hour.getHour(3) + ".Thank you for using AKR!";

						ConsigneeMsg = "AKR-Greetings.LR " + dto.getLr_no()
								+ " dt " + smsFormat.format(dto.getLr_date())
								+ " from "
								+ getTruncatedName(dto.getConsignorName(), 20)
								+ " will be delivered around " + Hour.getHour(3)
								+ ".Total is Rs." + (int) dto.getTotal_amount()
								+ ".Pl inform " + getPhoneNumber(currStn.trim())
								+ " if you are unavailable";

					} else if (lr_type.equalsIgnoreCase("Billing")) {
						ConsignorMsg = "AKR-Greetings! Your LR "
								+ dto.getLr_no()
								+ " dt "
								+ smsFormat.format(dto.getLr_date())
								+ " to "
								+ getTruncatedName(dto.getConsigneeName(), 27)
								+ " is out for delivery and will be delivered around "
								+ Hour.getHour(3) + ". Thank you for using AKR!";

						ConsigneeMsg = "Greetings from AKR.LR " + dto.getLr_no()
								+ " dt " + smsFormat.format(dto.getLr_date())
								+ " from "
								+ getTruncatedName(dto.getConsignorName(), 20)
								+ " will be delivered around " + Hour.getHour(3)
								+ ".Pl inform " + getPhoneNumber(currStn.trim())
								+ " if you are unavailable.Thank you!";
					}

				} else {
					if (lr_type.equalsIgnoreCase("Topay")) {
						send = true;
						int diff = getDateDifference(inward);
						if (diff < 4) {
							ConsignorMsg = "Greetings from AKR! Your LR "
									+ dto.getLr_no() + " dt "
									+ smsFormat.format(dto.getLr_date()) + " to "
									+ getTruncatedName(dto.getConsigneeName(), 30)
									+ " was delivered at " + Hour.getHourOnly()
									+ " on " + Hour.getDate() + "!"
									+ " Thank you for using AKR!";
						}

					} else if (lr_type.equalsIgnoreCase("Paid")) {
						send = true;
						int diff = getDateDifference(inward);
						if (diff < 4) {
							ConsignorMsg = "Greetings from AKR! Your LR "
									+ dto.getLr_no() + " dt "
									+ smsFormat.format(dto.getLr_date()) + " to "
									+ getTruncatedName(dto.getConsigneeName(), 30)
									+ " was delivered at " + Hour.getHourOnly()
									+ " on " + Hour.getDate() + "!"
									+ " Thank you for using AKR!";
						}

					} else if (lr_type.equalsIgnoreCase("Billing")) {
						ConsignorMsg = "AKR-Greetings! Your LR  "
								+ dto.getLr_no()
								+ " dt "
								+ smsFormat.format(dto.getLr_date())
								+ " to "
								+ getTruncatedName(dto.getConsigneeName(), 27)
								+ " is out for delivery and will be delivered around "
								+ Hour.getHour(3) + ". Thank you for using AKR!";

						ConsigneeMsg = "Greetings from AKR.LR " + dto.getLr_no()
								+ " dt " + smsFormat.format(dto.getLr_date())
								+ " from "
								+ getTruncatedName(dto.getConsignorName(), 20)
								+ " will be delivered around " + Hour.getHour(3)
								+ ".Pl inform " + getPhoneNumber(currStn.trim())
								+ " if you are unavailable.Thank you!";

					}

				}

				if (dto.getRate_type() == 4 || dto.getRate_type() == 5) {

					if (dto.getSms_alert() == 1) {
						ConsignorMsg = null;
					} else if (dto.getSms_alert() == 2) {
						ConsigneeMsg = null;
					} else if (dto.getSms_alert() == 0) {
						ConsigneeMsg = null;
						ConsignorMsg = null;
					}

				}

				if (lr_type.equalsIgnoreCase("Billing")) {
					ConsignorMsg = null;
				}

				// Sending SMS
				boolean isUnTime = isUntime();
				//boolean isUnTime = false;
				if (isUnTime == false) {
					if (null != ConsignorMsg) {
						sendSMS(dto.getCnor_phone(), ConsignorMsg);
					}
					if (null != ConsigneeMsg) {
						sendSMS(dto.getCnee_phone(), ConsigneeMsg);
					}
				} else if (send) {
					temp = new SMSDTO();
					temp.setCnor_phone(dto.getCnor_phone());
					temp.setCnor_message(ConsignorMsg);
					temp.setCnee_phone(dto.getCnee_phone());
					temp.setCnee_message(ConsigneeMsg);

				}

				//System.out.println(ConsignorMsg);
				//System.out.println(ConsigneeMsg);

			} catch (Exception e) {
				e.printStackTrace();
			}

			return temp;
		}

		private String getTruncatedName(String name, int index) {
			if (null != name) {
				int len = name.length();
				if (len > index) {
					return name.substring(0, index);
				} else {
					return name;
				}
			}
			return null;
		}

		/**
		 * 
		 * @param inward
		 * @return
		 */

		private int getDateDifference(Date inward) {

			try {
				DateFormat form = new SimpleDateFormat("dd-MM-yyyy");
				long diff = form.parse(BeanUtil.getInstance().getServerDate())
						.getTime()
						- inward.getTime();
				diff = diff / (1000 * 60 * 60 * 24);
				return (int) diff;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
		}

		private boolean getToStationType(String next, String fromstation) {

			try {
				StationsDTO[] dto = handler.getAvailableStations();
				if (null != dto) {
					for (int i = 0; i < dto.length; i++) {
						if (fromstation.equalsIgnoreCase(dto[i].getId())) {
							if (next.equalsIgnoreCase(dto[i].getBranch_code())
									&& !fromstation.equalsIgnoreCase(dto[i]
											.getBranch_code())) {
								return true;
							}
						}
					}
				}
			} catch (Exception e) {

			}
			return false;

		}

		public void widgetDefaultSelected(SelectionEvent arg0) {

		}

		public void focusGained(org.eclipse.swt.events.FocusEvent arg0) {

		}

		public void focusLost(org.eclipse.swt.events.FocusEvent arg0) {

			String stationName = cbDestStationName.getText();
			if (stationName != "") {
				StationsDTO[] stations = null;
				try {
					stations = handler.getAvailableStations();
				} catch (Exception exception) {
					exception.printStackTrace();
				}

				for (int i = 0; i < stations.length; i++) {
					if (stationName.equals(stations[i].getName())) {

						if (!stations[i].getId().equals(txtDestCode.getText())) {
							displayError("Station Name And Code does not match");
							btnDispatch.setEnabled(false);
						} else {
							btnDispatch.setEnabled(true);
						}
					}
				}
			}

		}

	}

	/**
	 * 
	 */
	private void populateDestStationCodes() {/*
		int len = 0;
		String currStn = EMPTY_STRING;
		String currBranch = EMPTY_STRING;
	
		try {
			
			StationsDTO[] stations = handler.getAvailableStations();

			if (null != stations && (len = stations.length) > 0) {
				currStn = handler.getStationCode();
				
				currBranch = checkCityStation(currStn);
				
				if(currStn.equalsIgnoreCase(currBranch)){
					// City
					for (int i = 0; i < len; i++) {		
						if(stations[i].getType().equalsIgnoreCase("City") && currStn.equalsIgnoreCase(stations[i].getBranch_code()))
							cbDestStationName.add(stations[i].getName());	
										
					}
				}else{
					// Non city stations
					for (int i = 0; i < len; i++) {
						if(!stations[i].getType().equalsIgnoreCase("City"))
							cbDestStationName.add(stations[i].getName());					
					}
				}}
			if (null != stations) {
				int len = stations.length;
				for (int i = 0; i < len; i++) {
					cbDestStationName.add(stations[i].getName());
				}

			}
		} catch (Exception exception) {

		}
	*/
		
	if(listDest != null){
		int len = listDest.length;
		for(int i = 0; i < len; i++){
			cbDestStationName.add(listDest[i]);
		}
	}
	
	}

	/**
	 * 
	 * @param combo
	 * @param show
	 */
	private static void showPopup(Combo combo, boolean show) {
		if (!combo.isDisposed()) {
			OS.SendMessage(combo.handle, OS.CB_SHOWDROPDOWN, show ? 1 : 0, 0);
		}
	}
		
	
	private void handleSMS(GMRDTO gmr) throws Exception{		
		String cnorMobile = null;
		String cneeMobile = null;
		String cneeMsg = null;
		String cnorMsg = null;
		String dest = EMPTY_STRING;
		String currStn = EMPTY_STRING;
		String currBranch = EMPTY_STRING;
		String destCityBranch = null;
		DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yy");
		
		currStn = handler.getStationCode();
	
		cnorMobile = gmr.getCnorPhone();
		cneeMobile = gmr.getCneePhone();
		dest = gmr.getDestinationStn().trim();
		destCityBranch = checkCityStation(dest);
		currBranch = getBranch(currStn);
				
		if(!isBranch(currStn)){

		if (destCityBranch != null ? (!currStn.equalsIgnoreCase(destCityBranch) && currBranch.equalsIgnoreCase(destCityBranch)) : currStn.equalsIgnoreCase(dest)) {
		//if (currStn.equalsIgnoreCase(dest)) {	
			//System.out.println(cityBranch);			

			if (gmr.getLrType().equalsIgnoreCase("TOPAY")) {
				cneeMsg = "AKR-Greetings.LR "
						+ gmr.getLrNumber()
						+ " dt "
						+ dateFormat.format(gmr.getLrDate())
						+ " from "
						+ getTruncatedName(gmr.getCnorName())
						+ " has reached "
						+ currStn
						+ " office.Collect soon to avoid demurrage.Contact "
						+ gmr.getDestinationPhone() + ".Total is Rs."
						+ gmr.getLrTotal();
			} else if (gmr.getLrType().equalsIgnoreCase("PAID")) {
				cneeMsg = "AKR-Greetings.LR "
						+ gmr.getLrNumber()
						+ " dt "
						+ dateFormat.format(gmr.getLrDate())
						+ " from "
						+ getTruncatedName(gmr.getCnorName())
						+ " has reached "
						+ currStn
						+ " office.Collect soon to avoid demurrage.Contact "
						+ gmr.getDestinationPhone() + ".Thank you!";
			}

			if (!gmr.getLrType().equalsIgnoreCase("BILLING")) {
				cnorMsg = "Greetings from AKR.LR "
					+ gmr.getLrNumber()
					+ " dt "
					+ dateFormat.format(gmr.getLrDate())
					+ " has reached "
					+ currStn
					+ " office." 					
					+ getTruncatedName(gmr.getCneeName())					
					+ " can pick it up anytime.An SMS was sent to them informing the same";
					
			}
						
			
			if(gmr.getRate_type() == 4 || gmr.getRate_type() == 5){				
				// Quotation SMS
				if(gmr.getSmsNotify() == 1){
					cnorMobile = null;
					cnorMsg = null;
				}else if(gmr.getSmsNotify() == 2){
					cneeMobile = null;
					cneeMsg = null;
				}else if(gmr.getSmsNotify() == 0){
					cneeMobile = null;
					cneeMsg = null;
					
					cnorMobile = null;
					cnorMsg = null;
				}
			}
			
			// System.out.println("SMS cnor"+"-->"+cnorMsg);

			// System.out.println("SMS cnee"+"-->"+cneeMsg);
			
			if(isUntime()){
				SMSDTO smsDto = new SMSDTO();
				if (null != cnorMsg && null != cnorMobile) {
					smsDto.setCnor_phone(cnorMobile);
					smsDto.setCnor_message(cnorMsg);
				}
				if (null != cneeMsg && null != cneeMobile) {
					smsDto.setCnee_phone(cneeMobile);
					smsDto.setCnee_message(cneeMsg);
				}
				gmr.setSmsDto(smsDto);				
			
			}else{
				if (null != cnorMsg && null != cnorMobile) {
					sendSMS(cnorMobile, cnorMsg);				
				}
				if (null != cneeMsg && null != cneeMobile) {				
					sendSMS(cneeMobile, cneeMsg);				
				}
				
			}			
			
		}
		}

	}
	
	private boolean isBranch(String stnCode) {
		StationsDTO[] stations = null;
		try {
			stations = handler.getAvailableStations();
			for (int i = 0; i < stations.length; i++) {
				if (stnCode.equalsIgnoreCase(stations[i].getBranch_code())) {
					return true;
				}
			}
		} catch (Exception e) {
			
		}
		
		return false;
	}

	/**
	 * 
	 * @param phone
	 * @param message
	 */
	private void sendSMS(String phone, String message) {

		if(phone != null){
		if (CellValidation.checkCellNumber(phone)) {
			SMS sms = new SMS();
			sms.sendSMS(phone, message);
		}
		}
	}

	private String getPhoneNumber(String code) {
		StationsDTO[] stations = null;
		try {
			stations = handler.getAvailableStations();
			for (int i = 0; i < stations.length; i++) {
				if (code.equalsIgnoreCase(stations[i].getId())) {
					return stations[i].getMobile();
				}
			}
		} catch (Exception e) {
			
		}

		return null;
	}
	
	/**
	 * 
	 * @param stationCode
	 * @return
	 */
	private String checkCityStation(String stationCode) {
		try {
			StationsDTO[] stationsDTO = handler.getAvailableStations();
			
			for(int i = 0; i < stationsDTO.length; i++){
				if(stationsDTO[i].getId().equalsIgnoreCase(stationCode) && 
						 stationsDTO[i].getType().equalsIgnoreCase("City")){
			
					return stationsDTO[i].getBranch_code();					
				}
			}
			
		} catch (Exception e) {			
			
		}		
		return null;
	}	
	
	private boolean isUntime() throws Exception{
		// Getting Server Date
		DateFormat hourFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String serverDt = handler.getServerDateTime();
		Date today = hourFormat.parse(serverDt);		
		int hour = today.getHours();	
		int min = today.getMinutes();
		//System.out.println("min==>"+ min);
		
		if(hour > 20 || hour < 9){	
			//System.out.println("untimeeeeeee==>");
			return true;
		}else if(hour == 20 && min > 50){
			//System.out.println("untimeeeeeee==>");
			return true;
		}else if(hour == 9 && min < 15){
			//System.out.println("untimeeeeeee==>");
			return true;
		}else{
			//System.out.println("==>");
			return false;
		}		
		
	}
	
	private String getTruncatedName(String name) {
		if (null != name) {
			int len = name.length();
			if (len > 27) {
				return name.substring(0, 20);
			} else {
				return name;
			}
		}
		return null;
	}
	
	private String getBranch(String stationCode) {
		try {
			StationsDTO[] stationsDTO = handler.getAvailableStations();
			
			for(int i = 0; i < stationsDTO.length; i++){
				if(stationsDTO[i].getId().equalsIgnoreCase(stationCode)){			
					return stationsDTO[i].getBranch_code();					
				}
			}
			
		} catch (Exception e) {			
			
		}		
		return null;
	}	
	
	
}