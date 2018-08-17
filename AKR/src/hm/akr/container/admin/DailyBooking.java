package hm.akr.container.admin;


import hm.akr.common.SWTResourceManager;
import hm.akr.common.BeanUtil;
import hm.akr.common.IUIConstants;
import hm.akr.common.sortListener;
import hm.akr.common.KalendarDialog;
import hm.akr.container.history.HistoryHandler;
import hm.akr.dto.GeneralSummaryDTO;
import hm.akr.dto.StationsDTO;
import hm.akr.dto.TableDecorator;
import hm.akr.dto.VersionDTO;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class DailyBooking extends ReportsMenu implements IUIConstants,Listener,SelectionListener  {
	
	private static final String DBA_EXCEL_JRXML = "hm/akr/resources/printer/DBA.jrxml";
	private String DAILY_BOOKING_TAB = "Daily Booking Analysis";
	private Button btnDBAGo;
	private Text txtDBAFrom;
	private Button btnDBAFrom;
	private Label lblTo;
	private Label label11;
	private Label label12;
	private Label label13;
	private Text txtDBATo;

	private Combo cbDBABranch;
	private Combo cbDBAMOC;
	private Combo cbDBAReportType;
	private TabItem tiDailyBookingAnalysis;
	private Canvas cvsDailyBookingAnalysis;
	private Table tblDailyBookingAnalysis;
	private TableColumn colDBAsno;
	private String COUNT = "Count";
	private String BASIC_FREIGHT = "Basic Freight";
	private String TOTAL_FREIGHT = "Total Freight";
	private String ACTUAL_WEIGHT = "Actual Weight";
	private String CHARGED_WEIGHT = "Charged Weight";
	private String NOA = "Noa";

	private TableColumn colDBABranchCode;

	private TableColumn colDBAStationCode;

	private TableColumn colDBADate1;

	private TableColumn colDBADate2;

	private TableColumn colDBADate3;

	private TableColumn colDBADate4;

	private TableColumn colDBADate5;

	private TableColumn colDBADate6;

	private TableColumn colDBADate7;

	private TableColumn colDBATotal;

	
	private TabFolder tabReport = null;
	private Label lblStatusBar;
	VersionDTO[] vDto = null;
	private Shell shell = null;
	DateFormat dateFormat = null;
	private Button btnPrint;
	private Button btnExportXL;
	private Button btnExportPDF;
	private ReportsMenuHandler handler;
	HistoryHandler historyH = null;
	
	private float clientRights = -1;
	private String currentStationName = "";
	private String currentStationCode = "";
	
	 public DailyBooking(Composite composite,int style, float clientRights) {
		super(composite,style);
		// TODO Auto-generated constructor stub
		
		try {
			
			this.clientRights = clientRights;
			currentStationName = beanutil.getActingStationName();
			currentStationCode = beanutil.getActingStationCode();
			shell = composite.getShell();
			dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			handler = new ReportsMenuHandler();
			historyH = new HistoryHandler();
			vDto = historyH.getHistoryYears();
			if(vDto != null && vDto.length > 0){
				BeanUtil.setThree_month_updated(vDto[0].getUpdated_date());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Composite loadcomposite(){
		
		this.setBounds(45,100, 1020,700);
		tabReport = new TabFolder(this, SWT.NONE);
		tabReport.setBounds(50, 25, 820, 500);
		tabReport.addSelectionListener(this);
			
		{
			tiDailyBookingAnalysis = new TabItem(tabReport, SWT.NONE);
			tiDailyBookingAnalysis.setText(DAILY_BOOKING_TAB);

			cvsDailyBookingAnalysis = new Canvas(tabReport, SWT.NONE);
			tiDailyBookingAnalysis.setControl(cvsDailyBookingAnalysis);

			{

				{
					tblDailyBookingAnalysis = new Table(
							cvsDailyBookingAnalysis, SWT.FULL_SELECTION
									| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);

					tblDailyBookingAnalysis.setHeaderVisible(true);
					tblDailyBookingAnalysis.setLinesVisible(true);
					tblDailyBookingAnalysis.setBounds(10, 72, 800, 400);
					{
						colDBAsno = new TableColumn(tblDailyBookingAnalysis,
								SWT.NONE);
						colDBAsno.setText("S NO");
						colDBAsno.setWidth(40);

					}
					{
						colDBABranchCode = new TableColumn(
								tblDailyBookingAnalysis, SWT.NONE);
						colDBABranchCode.setText("Branch Code");
						colDBABranchCode.setWidth(90);

					}
					{
						colDBAStationCode = new TableColumn(
								tblDailyBookingAnalysis, SWT.NONE);
						colDBAStationCode.setText("Station Code");
						colDBAStationCode.setWidth(90);
					}
					{
						colDBADate1 = new TableColumn(tblDailyBookingAnalysis,
								SWT.NONE);
						colDBADate1.setText("Day1");
						colDBADate1.setWidth(90);

					}
					{
						colDBADate2 = new TableColumn(tblDailyBookingAnalysis,
								SWT.NONE);
						colDBADate2.setText("Day2");
						colDBADate2.setWidth(90);

					}
					{
						colDBADate3 = new TableColumn(tblDailyBookingAnalysis,
								SWT.NONE);
						colDBADate3.setText("Day3");
						colDBADate3.setWidth(90);

					}
					{
						colDBADate4 = new TableColumn(tblDailyBookingAnalysis,
								SWT.NONE);
						colDBADate4.setText("Day4");
						colDBADate4.setWidth(90);

					}
					{
						colDBADate5 = new TableColumn(tblDailyBookingAnalysis,
								SWT.NONE);
						colDBADate5.setText("Day5");
						colDBADate5.setWidth(90);

					}
					{
						colDBADate6 = new TableColumn(tblDailyBookingAnalysis,
								SWT.NONE);
						colDBADate6.setText("Day6");
						colDBADate6.setWidth(90);

					}
					{
						colDBADate7 = new TableColumn(tblDailyBookingAnalysis,
								SWT.NONE);
						colDBADate7.setText("Day7");
						colDBADate7.setWidth(90);

					}
					{
						colDBATotal = new TableColumn(tblDailyBookingAnalysis,
								SWT.NONE);
						colDBATotal.setText("Total");
						colDBATotal.setWidth(70);

					}

					for (int index = 0; index < tblDailyBookingAnalysis
							.getColumnCount(); index++) {
						tblDailyBookingAnalysis.getColumn(index).addListener(
								SWT.Selection, this);
					}

				}
				{
					label11 = new Label(cvsDailyBookingAnalysis, SWT.NONE);
					label11.setText("From Date");
					label11.setBounds(10, 33, 55, 18);
				}

				{
					txtDBAFrom = new Text(cvsDailyBookingAnalysis, SWT.BORDER);
					txtDBAFrom.setBounds(68, 32, 70, 22);
					txtDBAFrom.setEditable(false);
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					currentDate.setDate(currentDate.getDate() - 6);
					String date = dateFormat.format(currentDate);
					txtDBAFrom.setText(date);

				}

				{
					btnDBAFrom = new Button(cvsDailyBookingAnalysis, SWT.PUSH
							| SWT.CENTER);
					btnDBAFrom.setBounds(139, 30, 31, 23);
					btnDBAFrom.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btnDBAFrom.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent e) {
							KalendarDialog cd = new KalendarDialog(shell);
							Object obj = cd.open();
							if (obj != null) {
								setDBADates(obj.toString());
							}
						}
					});
				}

				{
					lblTo = new Label(cvsDailyBookingAnalysis, SWT.NONE);
					lblTo.setText("To Date");
					lblTo.setBounds(175, 33, 41, 17);
				}
				{
					txtDBATo = new Text(cvsDailyBookingAnalysis, SWT.BORDER);
					txtDBATo.setBounds(217, 32, 70, 22);
					txtDBATo.setEditable(false);
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					txtDBATo.setText(date);

				}

				setDBADates(txtDBAFrom.getText());

				{
					label12 = new Label(cvsDailyBookingAnalysis, SWT.NONE);
					label12.setText("Branch");
					label12.setBounds(298, 32, 36, 21);
				}
				{
					cbDBABranch = new Combo(cvsDailyBookingAnalysis,
							SWT.DROP_DOWN | SWT.READ_ONLY);
					cbDBABranch.setBounds(335, 32, 150, 23);
					//cbDBABranch.add("All");
					//cbDBABranch.addSelectionListener(this);
					if(clientRights == 1 || clientRights == 1.0){
						
						try{
						
							getCurrentBranch_code(currentStationCode);
							cbDBABranch.select(0);
							//populateStationForCLR();
							cbDBABranch.addSelectionListener(this);
						}
						catch(Exception e){
							e.printStackTrace();
						}
						
						
					}
					else{
					
						try{
							StationsDTO[] dto = handler.getAllBranches();
							if (null != dto) {
								cbDBABranch.add("All");
								for (int i = 0; i < dto.length; i++) {
									cbDBABranch.add(dto[i].getName() + " - "
											+ dto[i].getId());
								}
							}
							cbDBABranch.addSelectionListener(this);
						}
						catch(Exception e){
							e.printStackTrace();
						}
					}
				}
				{
					label13 = new Label(cvsDailyBookingAnalysis, SWT.NONE);
					label13.setText("Report Type");
					label13.setBounds(495, 32, 60, 20);
				}
				{
					cbDBAReportType = new Combo(cvsDailyBookingAnalysis,
							SWT.READ_ONLY);
					cbDBAReportType.setBounds(564, 32, 85, 21);
					if(clientRights == 1 || clientRights == 1.0){
						cbDBAReportType.add("Detailed");
						cbDBAReportType.select(0);
					}
					else{
						cbDBAReportType.add("Consolidated");
						cbDBAReportType.add("Detailed");
					}
					cbDBAReportType.addSelectionListener(this);
				}
				{
					btnDBAGo = new Button(cvsDailyBookingAnalysis, SWT.PUSH
							| SWT.CENTER);
					btnDBAGo.setText("Go");
					btnDBAGo.setBounds(756, 31, 39, 24);
					btnDBAGo.addSelectionListener(this);
				}
				{
					cbDBAMOC = new Combo(cvsDailyBookingAnalysis, SWT.READ_ONLY);
					cbDBAMOC.setBounds(663, 32, 90, 21);
					cbDBAMOC.add(COUNT);
					cbDBAMOC.add(BASIC_FREIGHT);
					cbDBAMOC.add(TOTAL_FREIGHT);
					cbDBAMOC.add(ACTUAL_WEIGHT);
					cbDBAMOC.add(CHARGED_WEIGHT);
					cbDBAMOC.add(NOA);
				}

			}
			
		}
		
		{
			btnPrint = new Button(this, SWT.PUSH | SWT.CENTER);
			btnPrint.setText("Print");
			btnPrint.setBounds(529, 530, 60, 23);
			btnPrint.addSelectionListener(this);
		}
		{
			btnExportXL = new Button(this, SWT.PUSH | SWT.CENTER);
			btnExportXL.setText("Export Excel");
			btnExportXL.setBounds(600, 530, 80, 23);		
			btnExportXL.addSelectionListener(this);
		}
		{
			btnExportPDF = new Button(this, SWT.PUSH | SWT.CENTER);
			btnExportPDF.setText("Export PDF");
			btnExportPDF.setBounds(690, 530, 80, 23);		
			btnExportPDF.addSelectionListener(this);
			
		}
		
		{
			lblStatusBar = new Label(this, SWT.NONE);
			lblStatusBar.setBounds(60,570 ,300,25);
			lblStatusBar.setFont(STATUS_SUCCESS);
			lblStatusBar.setText("STATUS");
			lblStatusBar.setForeground(LINK_FOCUS_COLOR);
			lblStatusBar.setVisible(true);
		}
		
		return this;
	}
	
	private void getCurrentBranch_code(String station) throws Exception {
		StationsDTO[] stations = null;

		stations = handler.getAllStations();
		int size = 0;
		if (null != stations) {
			size = stations.length;

			for (int i = 0; i < size; i++) {
				if (station.equalsIgnoreCase(stations[i].getId())) {
					
					 getCurrentBranchName(stations[i].getBranch_code());
					
					//return stations[i].getBranch_code();
				}
			}
		}

		//return null;
	}
	
	private void getCurrentBranchName(String branch_code) {
		// TODO Auto-generated method stub
		try{
			StationsDTO[] stations = null;
	
			stations = handler.getAllStations();
			int size = 0;
			if (null != stations) {
				size = stations.length;
	
				for (int i = 0; i < size; i++) {
					if (branch_code.equalsIgnoreCase(stations[i].getId())) {
					
						cbDBABranch.add(stations[i].getName()+" - "+branch_code);
						//return stations[i].getBranch_code();
					}
				}
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}


	
	private void setDBADates(String date) {

		txtDBAFrom.setText(date);
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date sevenDay = null;
		Date secondDay = null;
		Date thirdDay = null;
		Date fourthDay = null;
		Date fifthDay = null;
		Date sixthDay = null;
		try {
			secondDay = dateFormat.parse(date);
			secondDay.setDate(secondDay.getDate() + 1);

			thirdDay = dateFormat.parse(date);
			thirdDay.setDate(thirdDay.getDate() + 2);

			fourthDay = dateFormat.parse(date);
			fourthDay.setDate(fourthDay.getDate() + 3);

			fifthDay = dateFormat.parse(date);
			fifthDay.setDate(fifthDay.getDate() + 4);

			sixthDay = dateFormat.parse(date);
			sixthDay.setDate(sixthDay.getDate() + 5);

			sevenDay = dateFormat.parse(date);
			sevenDay.setDate(sevenDay.getDate() + 6);

			txtDBATo.setText(dateFormat.format(sevenDay));

			colDBADate1.setText(date);
			colDBADate2.setText(dateFormat.format(secondDay));
			colDBADate3.setText(dateFormat.format(thirdDay));
			colDBADate4.setText(dateFormat.format(fourthDay));
			colDBADate5.setText(dateFormat.format(fifthDay));
			colDBADate6.setText(dateFormat.format(sixthDay));
			colDBADate7.setText(dateFormat.format(sevenDay));

		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}
	
	private void populateAllBranches(Table table) {
		StationsDTO[] stations = null;
		try {

			stations = handler.getAllBranches();

			int size = 0;
			if (null != stations) {
				if (table != null) {
					table.removeAll();
				}
				size = stations.length;
				for (int i = 0; i < size; i++) {

					TableItem item = new TableItem(table, SWT.NONE);
					item.setText(0, String.valueOf(i + 1));
					item.setText(1, stations[i].getBranch_code());
					item.setText(2, stations[i].getId());

				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}

	private void populateAllStations(Table table) {
		StationsDTO[] stations = null;
		try {
			stations = handler.getAllStations();

			int size = 0;
			if (null != stations) {
				if (table != null) {
					table.removeAll();
				}
				size = stations.length;
				for (int i = 0; i < size; i++) {
					TableItem item = new TableItem(table, SWT.NONE);
					item.setText(0, String.valueOf(i + 1));
					item.setText(1, stations[i].getBranch_code());
					item.setText(2, stations[i].getId());
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}
	
	private void populateSelectedStations(String branchCode, Table table) {
		StationsDTO[] stations = null;
		try {

			stations = handler.getAllStations();

			int size = 0;
			if (null != stations) {
				if (table != null) {
					table.removeAll();
				}
				size = stations.length;
				for (int i = 0, sNo = 1; i < size; i++) {
					if (branchCode.equals(stations[i].getBranch_code())) {
						TableItem item = new TableItem(table, SWT.NONE);
						item.setText(0, String.valueOf(sNo));
						item.setText(1, branchCode);
						item.setText(2, stations[i].getId());

						sNo += 1;
					}
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
	private String getBranch_code(String station) throws Exception {
		StationsDTO[] stations = null;

		stations = handler.getAllStations();
		int size = 0;
		if (null != stations) {
			size = stations.length;

			for (int i = 0; i < size; i++) {
				if (station.equalsIgnoreCase(stations[i].getId())) {
					return stations[i].getBranch_code();
				}
			}
		}

		return null;
	}
	private void populateDailyBooking(Date fromDt, GeneralSummaryDTO[] bookedLr)
	throws Exception {

		TableItem[] items = tblDailyBookingAnalysis.getItems();
		int dayCount = 0;
		long diff = 0;
		
		String stnCode = "";
		int count[][] = new int[items.length][7];
		float bft[][] = new float[items.length][7];
		float totalBft = 0;
		int noa = 0;
		float actWt = 0;
		float chrgWt = 0;
		
		for (int i = 0; i < bookedLr.length; i++) {
			
			for (int j = 0; j < items.length; j++) {
				
				if (cbDBAReportType.getText().equalsIgnoreCase("Consolidated")) {
					stnCode = getBranch_code(bookedLr[i].getStation_code());
					
					
				} else {
					stnCode = bookedLr[i].getStation_code();
				}
				
								
					if (stnCode.equalsIgnoreCase(items[j].getText(2))) {
						
						diff = bookedLr[i].getLrDate().getTime() - fromDt.getTime();
						dayCount = (int) (diff / (1000 * 60 * 60 * 24));
						
								
							if (cbDBAMOC.getText().equalsIgnoreCase(COUNT)) {
								
								count[j][dayCount] = count[j][dayCount] +  bookedLr[i].getLr_no();
							} else if (cbDBAMOC.getText().equalsIgnoreCase(
									BASIC_FREIGHT)) {
								bft[j][dayCount] =  bft[j][dayCount] + bookedLr[i].getBft();
							} else if (cbDBAMOC.getText().equalsIgnoreCase(
									TOTAL_FREIGHT)) {
								bft[j][dayCount] = bft[j][dayCount] + bookedLr[i].getTotal();
							} else if (cbDBAMOC.getText().equalsIgnoreCase(NOA)) {
								count[j][dayCount] = count[j][dayCount] + bookedLr[j].getNo_of_articles();
							} else if (cbDBAMOC.getText().equalsIgnoreCase(
									ACTUAL_WEIGHT)) {
								bft[j][dayCount] =  bft[j][dayCount] + bookedLr[i].getActual_weight();
							} else if (cbDBAMOC.getText().equalsIgnoreCase(
									CHARGED_WEIGHT)) {
								bft[j][dayCount] =  bft[j][dayCount] + bookedLr[i].getCharged_weight();
							}
							
							if (cbDBAMOC.getText().equalsIgnoreCase(COUNT)
									|| cbDBAMOC.getText().equalsIgnoreCase(NOA)) {
								fillBPA(count[j][dayCount], items[j], dayCount);
							} else {
								fillBPA(bft[j][dayCount], items[j], dayCount);
							}
							
						
						
						
					}
				
			}
		}
		
		if (cbDBAMOC.getText().equalsIgnoreCase(COUNT)
				|| cbDBAMOC.getText().equalsIgnoreCase(NOA)) {
			calcDBATotal();
		} else {
			calcDBATotal(true);
		}
		
		new sortListener().fillZerosOnEmpty(tblDailyBookingAnalysis);
		
		}
		
		private void fillBPA(int count, TableItem item, int dayCount) {
		int total = 0;
		int total1 = 0;
		int total2 = 0;
		int total3 = 0;
		int total4 = 0;
		int total5 = 0;
		int total6 = 0;
		int itemTotal = 0;
		
		if (dayCount == 0) {
			item.setText(3, String.valueOf(count));
			total = total + count;
			
		} else if (dayCount == 1) {
			item.setText(4, String.valueOf(count));
			total = total + count;
			
		} else if (dayCount == 2) {
			item.setText(5, String.valueOf(count));
			total = total + count;
			
		} else if (dayCount == 3) {
			item.setText(6, String.valueOf(count));
			total = total + count;
			
		} else if (dayCount == 4) {
			item.setText(7, String.valueOf(count));
			total = total + count;
			
		} else if (dayCount == 5) {
			item.setText(8, String.valueOf(count));
			total = total + count;
			
		} else if (dayCount == 6) {
			item.setText(9, String.valueOf(count));
			total = total + count;
			
		}
		//System.out.println("Total"+total);
		if ((! item.getText(10).equals(""))||(! item.getText(10).equals("0"))) {
			
			if (!item.getText(3).equals("")){
				itemTotal = itemTotal + Integer.parseInt(item.getText(3));
			}
			if (!item.getText(4).equals("")){
				itemTotal = itemTotal + Integer.parseInt(item.getText(4));
			}
			if (!item.getText(5).equals("")){
				itemTotal = itemTotal + Integer.parseInt(item.getText(5));
			}
			if (!item.getText(6).equals("")){
				itemTotal = itemTotal + Integer.parseInt(item.getText(6));
			}
			if (!item.getText(7).equals("")){
				itemTotal = itemTotal + Integer.parseInt(item.getText(7));
			}
			if (!item.getText(8).equals("")){
				itemTotal = itemTotal + Integer.parseInt(item.getText(8));
			}
			if (!item.getText(9).equals("")){
				itemTotal = itemTotal + Integer.parseInt(item.getText(9));
			}
			
		}
		//total = count + itemTotal;
		item.setText(10, String.valueOf(itemTotal));
		
		
		
		}
		
		private void fillBPA(float floatValue, TableItem item, int dayCount) {
		float total = 0;
		float itemTot = 0;
		
		if (dayCount == 0) {
			item.setText(3, getRoundedValue(floatValue));
			total = total + floatValue;
		} else if (dayCount == 1) {
			item.setText(4, getRoundedValue(floatValue));
			total = total + floatValue;
		} else if (dayCount == 2) {
			item.setText(5, getRoundedValue(floatValue));
			total = total + floatValue;
		} else if (dayCount == 3) {
			item.setText(6, getRoundedValue(floatValue));
			total = total + floatValue;
		} else if (dayCount == 4) {
			item.setText(7, getRoundedValue(floatValue));
			total = total + floatValue;
		} else if (dayCount == 5) {
			item.setText(8, getRoundedValue(floatValue));
			total = total + floatValue;
			;
		} else if (dayCount == 6) {
			item.setText(9, getRoundedValue(floatValue));
			total = total + floatValue;
		}
		
		if (item.getText(10).equals("")) {
			itemTot = 0;
		} else {
			//itemTot = Float.parseFloat(item.getText(10));
			
			if (!item.getText(3).equals(""))
				itemTot = itemTot + Float.parseFloat(item.getText(3));
			if (!item.getText(4).equals(""))
				itemTot = itemTot + Float.parseFloat(item.getText(4));
			if (!item.getText(5).equals(""))
				itemTot = itemTot + Float.parseFloat(item.getText(5));
			if (!item.getText(6).equals(""))
				itemTot = itemTot + Float.parseFloat(item.getText(6));
			if (!item.getText(7).equals(""))
				itemTot = itemTot + Float.parseFloat(item.getText(7));
			if (!item.getText(8).equals(""))
				itemTot = itemTot + Float.parseFloat(item.getText(8));
			if (!item.getText(9).equals(""))
				itemTot = itemTot + Float.parseFloat(item.getText(9));
			
		}
		//total = floatValue + itemTot;
		item.setText(10, getRoundedValue(itemTot));
		
		
		}
		
		private void calcDBATotal() {
		TableItem[] items = tblDailyBookingAnalysis.getItems();
		int tot3 = 0;
		int tot4 = 0;
		int tot5 = 0;
		int tot6 = 0;
		int tot7 = 0;
		int tot8 = 0;
		int tot9 = 0;
		int tot10 = 0;
		
		
		if (items != null) {
			for (int i = 0; i < items.length; i++) {
				if (!items[i].getText(3).equals("")){
					tot3 = tot3 + Integer.parseInt(items[i].getText(3));
					
				}
				if (!items[i].getText(4).equals("")){
					tot4 = tot4 + Integer.parseInt(items[i].getText(4));
					
				}
				if (!items[i].getText(5).equals("")){
					tot5 = tot5 + Integer.parseInt(items[i].getText(5));
					
				}
				if (!items[i].getText(6).equals("")){
					tot6 = tot6 + Integer.parseInt(items[i].getText(6));
					
				}
				if (!items[i].getText(7).equals("")){
					tot7 = tot7 + Integer.parseInt(items[i].getText(7));
					
				}
				if (!items[i].getText(8).equals("")){
					tot8 = tot8 + Integer.parseInt(items[i].getText(8));
					
				}
				if (!items[i].getText(9).equals("")){
					tot9 = tot9 + Integer.parseInt(items[i].getText(9));
					
				}
				if (!items[i].getText(10).equals(""))
					tot10 = tot10 + Integer.parseInt(items[i].getText(10));
			}
		
		}
			
		final TableItem item1 = new TableItem(tblDailyBookingAnalysis, SWT.NONE);
		Font font1 = new Font(Display.getCurrent(), "Tahoma", 8, SWT.BOLD);
		item1.setFont(font1);
		item1.setText(2, "TOTAL");
		item1.setText(3, String.valueOf(tot3));
		item1.setText(4, String.valueOf(tot4));
		item1.setText(5, String.valueOf(tot5));
		item1.setText(6, String.valueOf(tot6));
		item1.setText(7, String.valueOf(tot7));
		item1.setText(8, String.valueOf(tot8));
		item1.setText(9, String.valueOf(tot9));
		item1.setText(10, String.valueOf(tot10));
		}
		
		
		private void calcDBATotal(boolean isFloat) {
		TableItem[] items = tblDailyBookingAnalysis.getItems();
		float tot3 = 0;
		float tot4 = 0;
		float tot5 = 0;
		float tot6 = 0;
		float tot7 = 0;
		float tot8 = 0;
		float tot9 = 0;
		float tot10 = 0;
		
		if (items != null) {
			for (int i = 0; i < items.length; i++) {
				if (!items[i].getText(3).equals(""))
					tot3 = tot3 + Float.parseFloat(items[i].getText(3));
				if (!items[i].getText(4).equals(""))
					tot4 = tot4 + Float.parseFloat(items[i].getText(4));
				if (!items[i].getText(5).equals(""))
					tot5 = tot5 + Float.parseFloat(items[i].getText(5));
				if (!items[i].getText(6).equals(""))
					tot6 = tot6 + Float.parseFloat(items[i].getText(6));
				if (!items[i].getText(7).equals(""))
					tot7 = tot7 + Float.parseFloat(items[i].getText(7));
				if (!items[i].getText(8).equals(""))
					tot8 = tot8 + Float.parseFloat(items[i].getText(8));
				if (!items[i].getText(9).equals(""))
					tot9 = tot9 + Float.parseFloat(items[i].getText(9));
				if (!items[i].getText(10).equals(""))
					tot10 = tot10 + Float.parseFloat(items[i].getText(10));
			}
		
		}
		
		final TableItem item1 = new TableItem(tblDailyBookingAnalysis, SWT.NONE);
		Font font1 = new Font(Display.getCurrent(), "Tahoma", 8, SWT.BOLD);
		item1.setFont(font1);
		item1.setText(2, "TOTAL");
		item1.setText(3, getRoundedValue(tot3));
		item1.setText(4, getRoundedValue(tot4));
		item1.setText(5, getRoundedValue(tot5));
		item1.setText(6, getRoundedValue(tot6));
		item1.setText(7, getRoundedValue(tot7));
		item1.setText(8, getRoundedValue(tot8));
		item1.setText(9, getRoundedValue(tot9));
		item1.setText(10, getRoundedValue(tot10));
		
		}

	private GeneralSummaryDTO[] getDailyBooking(Date fromDt, Date toDate) {
		//int monthDiff = 0;
		GeneralSummaryDTO[] bookedLr = null;
		try {
			/*Date curDate = new Date();
			int m1 = fromDt.getYear() * 12 + fromDt.getMonth();
	        int m2 = curDate.getYear() * 12 + curDate.getMonth();
	        monthDiff = m2 - m1;*/
			Date date = BeanUtil.getThree_month_updated();
		if(toDate.before(date)){
			//System.out.println("in ddr histry-->"+date);
			bookedLr = handler.getDailyBookingHistory(fromDt, toDate);
		}else if(fromDt.after(date)){
			//System.out.println("in ddr histry-->"+date);
			bookedLr = handler.getDailyBooking(fromDt, toDate);
		}else{
			bookedLr = handler.getDailyBookingUnion(fromDt, toDate);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookedLr;
		
	}
	
	private String getRoundedValue(float cardRate) {
		DecimalFormat decimalFormat;
		decimalFormat = new DecimalFormat("0.00");
		cardRate = cardRate * 100;
		cardRate = (float) Math.round(cardRate);
		cardRate = cardRate / 100;

		return decimalFormat.format(cardRate);
	}
	
	private String[] getDaillybookingDates(Table tblDailyBookingAnalysis2) {
		String list[] = new String[7];
		
		list[0] =colDBADate1.getText();
		list[1] =colDBADate2.getText();
		list[2] =colDBADate3.getText();
		list[3] =colDBADate4.getText();
		list[4] =colDBADate5.getText();
		list[5] =colDBADate6.getText();
		list[6] =colDBADate7.getText();
			
		return list;
	}

	
	
	private String[] getDailybookingHeading(Table refTable){
		
		String list[] = new String[5];
		
		list[0] =txtDBAFrom.getText();
		list[1] =txtDBATo.getText();
		String branchCode = cbDBABranch.getText();
		if (!branchCode.equalsIgnoreCase("All")) {
			int index = branchCode.indexOf(" - ");
			list[2] = branchCode.substring(index + 3);
		}
		else
			list[2] ="All";
		if((cbDBAReportType.getText().equals("Detailed"))||(!cbDBABranch.getText().equals("All")))
			list[3] = "Detailed";
		else
			list[3] = "Consolidated";
			
		list[4] =cbDBAMOC.getText();
		
			
		return list;
	}
	
	@Override
	public void handleEvent(Event e) {
		// TODO Auto-generated method stub
		String selection = tabReport.getSelection()[0].getText();
		TableColumn column = (TableColumn) e.widget;
		
		//else if (selection == DAILY_BOOKING_TAB) {
			if (column == colDBAsno) {
				new sortListener().sortByFloat(0, tblDailyBookingAnalysis);
			} else if (column == colDBABranchCode) {
				new sortListener().sortByString(1, tblDailyBookingAnalysis);
			} else if (column == colDBAStationCode) {
				new sortListener().sortByString(2, tblDailyBookingAnalysis);
			} else if (column == colDBADate1) {
				new sortListener().sortByFloat(3, tblDailyBookingAnalysis);
			} else if (column == colDBADate2) {
				new sortListener().sortByFloat(4, tblDailyBookingAnalysis);
			} else if (column == colDBADate3) {
				new sortListener().sortByFloat(5, tblDailyBookingAnalysis);
			} else if (column == colDBADate4) {
				new sortListener().sortByFloat(6, tblDailyBookingAnalysis);
			} else if (column == colDBADate5) {
				new sortListener().sortByFloat(7, tblDailyBookingAnalysis);
			} else if (column == colDBADate6) {
				new sortListener().sortByFloat(8, tblDailyBookingAnalysis);
			} else if (column == colDBADate7) {
				new sortListener().sortByFloat(9, tblDailyBookingAnalysis);
			}
			else if (column == colDBATotal) {
				new sortListener().sortByFloat(10, tblDailyBookingAnalysis);
			}
		//}
		
	}
	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void widgetSelected(SelectionEvent event) {
		// TODO Auto-generated method stub
		Object source = event.getSource();
		
		if (source == cbDBABranch) {
			if (cbDBABranch.getSelectionIndex() != -1) {
				if (!cbDBABranch.getText().equalsIgnoreCase("All")) {
					// populateSelectedStations(cbDBABranch.getText(),
					// tblDailyBookingAnalysis);
					cbDBAReportType.setText("Detailed");
					cbDBAReportType.setEnabled(false);
				} else {
					cbDBAReportType.setText("Consolidated");
					cbDBAReportType.setEnabled(true);
					// populateAllBranches(tblDailyBookingAnalysis);
				}

			}
		} else if (source == cbDBAReportType) {

		} else if (source == btnDBAGo) {
			new sortListener().display("Generating Report...", STATUS_SUCCESS,
					SUCCESS_FONT_COLOR,lblStatusBar);

			String branch = "";
			if (cbDBABranch.getSelectionIndex() == -1) {
				// AdminComposite.display("Please select branch",
				// STATUS_SUCCESS,SUCCESS_FONT_COLOR);
			} else {
				if (handler != null) {

					branch = cbDBABranch.getText();
					int index = branch.indexOf(" - ");
					branch = branch.substring(index + 3);

					if (!cbDBABranch.getText().equalsIgnoreCase("All")) {
						populateSelectedStations(branch,
								tblDailyBookingAnalysis);
					} else if (cbDBABranch.getText().equalsIgnoreCase("All")
							&& cbDBAReportType.getText().equalsIgnoreCase(
									"Consolidated")) {
						populateAllBranches(tblDailyBookingAnalysis);
					} else if (cbDBABranch.getText().equalsIgnoreCase("All")
							&& cbDBAReportType.getText().equalsIgnoreCase(
									"Detailed")) {
						
						
						populateAllStations(tblDailyBookingAnalysis);
					}

					try {
						GeneralSummaryDTO[] bookedLr = null;
						DateFormat dateFormat = new SimpleDateFormat(
								"dd-MM-yyyy");
						Date fromDt = dateFormat.parse(txtDBAFrom.getText());
						Date toDate = dateFormat.parse(txtDBATo.getText());

						 bookedLr = getDailyBooking(fromDt, toDate);

						if (bookedLr != null) {
							populateDailyBooking(fromDt, bookedLr);
						} else {
							if (tblDailyBookingAnalysis != null)
								tblDailyBookingAnalysis.removeAll();
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			new sortListener().display("Report Loaded Successfully!",
					STATUS_SUCCESS, SUCCESS_FONT_COLOR,lblStatusBar);
		}else if (source == btnExportXL) {
			try {
				//handleExcel();
				//if (selectedTab.equalsIgnoreCase(DAILY_BOOKING_TAB)) {
					ArrayList<TableDecorator> list = null;
					String[] value = null;
					String[] dates = null;
					list = new sortListener().getBPATable(tblDailyBookingAnalysis);
					value = getDailybookingHeading(tblDailyBookingAnalysis);
					dates = getDaillybookingDates(tblDailyBookingAnalysis);
					new sortListener().prepareExcel(list, "Daily_Booking", DBA_EXCEL_JRXML, dates,value,shell,lblStatusBar);
				//} 
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else if (source == btnPrint) {
			try {
				//handlePrint();
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				String[] dates  = null;
				list = new sortListener().getBPATable(tblDailyBookingAnalysis);
				value = getDailybookingHeading(tblDailyBookingAnalysis);
				dates = getDaillybookingDates(tblDailyBookingAnalysis);
				handler.preparePrint(list, "Daily_Booking", DBA_EXCEL_JRXML, dates,value);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		else if (source == btnExportPDF) {
			try {
				//handlePDF();
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				String[] dates = null;
				list = new sortListener().getBPATable(tblDailyBookingAnalysis);
				value = getDailybookingHeading(tblDailyBookingAnalysis);
				dates = getDaillybookingDates(tblDailyBookingAnalysis);
				new sortListener().preparePDF(list, "Daily_Booking", DBA_EXCEL_JRXML, dates,value,shell,lblStatusBar);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		
	}
		
		
}
