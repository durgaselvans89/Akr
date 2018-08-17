package hm.akr.container.admin;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import hm.akr.common.sortListener;
import hm.akr.common.SWTResourceManager;
import hm.akr.common.BeanUtil;
import hm.akr.common.IUIConstants;
import hm.akr.common.KalendarDialog;
import hm.akr.container.history.HistoryHandler;
import hm.akr.dto.BookingDTO;
import hm.akr.dto.GeneralSummaryDTO;
import hm.akr.dto.StationsDTO;
import hm.akr.dto.TableDecorator;
import hm.akr.dto.VersionDTO;

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

public class TotalUdTopay extends ReportsMenu implements IUIConstants,Listener,SelectionListener {

	
	private TabItem tiUndeliveredTopay;
	private Canvas cvsUndeliveredTopay;
	private Table tblUndeliveredTopayDet;
	private TableColumn colTUTsno;
	private TableColumn colUTLrno;
	private TableColumn colUTLrdate;
	private TableColumn colUTFrom;
	private TableColumn colUTTo;
	private TableColumn colUTbft;
	private TableColumn colUTnoa;
	private Text txtUTFrom;
	private Text txtUTTo;
	//private Button btnTUTMonth;
	private Combo cbUTBranch;
	private Combo cbUTStation;
	private Combo cbUTReportType;
	private TableColumn colUTActWt;
	private TableColumn colUTtotalFt;
	private TableColumn colUTCurStatus;
	private Button btnUTGo;
	private Button btnUTFromDate;
	private Button btnUTTo;
	private TableColumn colUTsno;
	private TableColumn colTUTBranchCode;
	private TableColumn colTUTStationCode;
	private TableColumn colTUTBoookedNoOfLRs;
	private TableColumn colTUTBoookedTotFrt;
	private TableColumn colTUTDeliverNoOfLRs;
	private TableColumn colTUTDeliverTotFrt;
	private Table tblUndeliveredTopay;
	private Label lblUTFromDate;
	private Label lblUTToDate;
	private Label label12;
	private Label label13;
	private Label label14;
	private TableColumn colCCbftofTot;
	private TableColumn colCCperofCConTot;
	private String UT_TOPAY = "Undelivered Topay";
	private static final String UT_EXCEL_JRXML = "hm/akr/resources/printer/Undelivered_Topay.jrxml";
	private static final String UT_DET_EXCEL_JRXML = "hm/akr/resources/printer/Undelivered_Topay_Det.jrxml";
	
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
	
	 public TotalUdTopay(Composite composite,int style, float clientRights) {
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

			tiUndeliveredTopay = new TabItem(tabReport, SWT.NONE);
			tiUndeliveredTopay.setText(UT_TOPAY);

			cvsUndeliveredTopay = new Canvas(tabReport, SWT.NONE);
			tiUndeliveredTopay.setControl(cvsUndeliveredTopay);

			{

				{
					tblUndeliveredTopayDet = new Table(cvsUndeliveredTopay,
							SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL
									| SWT.BORDER);

					tblUndeliveredTopayDet.setHeaderVisible(true);
					tblUndeliveredTopayDet.setLinesVisible(true);
					tblUndeliveredTopayDet.setBounds(10, 72, 800, 400);
					{
						colUTsno = new TableColumn(tblUndeliveredTopayDet,
								SWT.NONE);
						colUTsno.setText("S NO");
						colUTsno.setWidth(40);

					}
					{
						colUTLrno = new TableColumn(tblUndeliveredTopayDet,
								SWT.NONE);
						colUTLrno.setText("LR NO");
						colUTLrno.setWidth(80);

					}
					{
						colUTLrdate = new TableColumn(tblUndeliveredTopayDet,
								SWT.NONE);
						colUTLrdate.setText("LR Date");
						colUTLrdate.setWidth(80);
					}
					{
						colUTFrom = new TableColumn(tblUndeliveredTopayDet,
								SWT.NONE);
						colUTFrom.setText("From");
						colUTFrom.setWidth(70);

					}
					{
						colUTTo = new TableColumn(tblUndeliveredTopayDet,
								SWT.NONE);
						colUTTo.setText("To");
						colUTTo.setWidth(70);

					}

					{
						colUTnoa = new TableColumn(tblUndeliveredTopayDet,
								SWT.NONE);
						colUTnoa.setText("NOA");
						colUTnoa.setWidth(50);

					}
					{
						colUTActWt = new TableColumn(tblUndeliveredTopayDet,
								SWT.NONE);
						colUTActWt.setText("Act Wt");
						colUTActWt.setWidth(70);

					}
					{
						colUTbft = new TableColumn(tblUndeliveredTopayDet,
								SWT.NONE);
						colUTbft.setText("Basic Freight");
						colUTbft.setWidth(100);

					}
					{
						colUTtotalFt = new TableColumn(tblUndeliveredTopayDet,
								SWT.NONE);
						colUTtotalFt.setText("Total Freight");
						colUTtotalFt.setWidth(100);

					}
					{
						colUTCurStatus = new TableColumn(
								tblUndeliveredTopayDet, SWT.NONE);
						colUTCurStatus.setText("Current Status");
						colUTCurStatus.setWidth(110);

					}

					for (int index = 0; index < tblUndeliveredTopayDet
							.getColumnCount(); index++) {
						tblUndeliveredTopayDet.getColumn(index).addListener(
								SWT.Selection, this);
					}

				}
				/*{
					label11 = new Label(cvsUndeliveredTopay, SWT.NONE);
					label11.setText("Month");
					label11.setBounds(71, 34, 34, 17);
				}
				{
					txtTUTMonth = new Text(cvsUndeliveredTopay, SWT.BORDER);
					txtTUTMonth.setBounds(108, 32, 57, 21);
					DateFormat dateFormat = new SimpleDateFormat("MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					txtTUTMonth.setText(date);
					txtTUTMonth.setEnabled(false);
				}
				{
					btnTUTMonth = new Button(cvsUndeliveredTopay, SWT.PUSH
							| SWT.CENTER);
					btnTUTMonth.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btnTUTMonth.setBounds(174, 32, 26, 23);
					btnTUTMonth.addSelectionListener(new SelectionListener() {

						@Override
						public void widgetDefaultSelected(SelectionEvent arg0) {

						}

						@Override
						public void widgetSelected(SelectionEvent event) {
							MonthDialog cd = new MonthDialog(Display
									.getCurrent().getActiveShell());
							Object obj = cd.open();
							if (obj != null) {
								txtTUTMonth.setText(obj.toString());
							}
						}

					});
				}*/
				
				{
					lblUTFromDate = new Label(cvsUndeliveredTopay, SWT.NONE);
					lblUTFromDate.setText("From Date");
					lblUTFromDate.setBounds(12, 28, 55, 20);
				}
				{
					lblUTToDate = new Label(cvsUndeliveredTopay, SWT.NONE);
					lblUTToDate.setText("To Date");
					lblUTToDate.setBounds(180, 28, 44, 16);
				}

				{
					txtUTFrom = new Text(cvsUndeliveredTopay, SWT.BORDER);
					txtUTFrom.setBounds(80, 26, 70, 22);
					txtUTFrom.setEditable(false);
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					txtUTFrom.setText(date);

				}
				{
					btnUTFromDate = new Button(cvsUndeliveredTopay, SWT.PUSH
							| SWT.CENTER);
					btnUTFromDate.setBounds(150, 25, 31, 23);
					btnUTFromDate.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btnUTFromDate.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent e) {
							KalendarDialog cd = new KalendarDialog(shell, 1);
							Object obj = cd.open();
							if (obj != null) {
								String date = obj.toString();
								txtUTFrom.setText(date);
							}
						}
					});
				}

				{
					txtUTTo = new Text(cvsUndeliveredTopay, SWT.BORDER);
					txtUTTo.setBounds(235, 25, 70, 22);
					txtUTTo.setEditable(false);
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					txtUTTo.setText(date);

				}
				{
					btnUTTo = new Button(cvsUndeliveredTopay, SWT.PUSH | SWT.CENTER);
					btnUTTo.setBounds(305, 24, 32, 23);
					btnUTTo.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btnUTTo.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent e) {
							KalendarDialog cd = new KalendarDialog(shell);
							Object obj = cd.open();
							if (obj != null) {
								String date = obj.toString();
								txtUTTo.setText(date);
							}
						}
					});
				}

				
				
				{
					label14 = new Label(cvsUndeliveredTopay, SWT.NONE);
					label14.setText("Station");
					label14.setBounds(515, 40, 72, 19);
				}
				{
					cbUTStation = new Combo(cvsUndeliveredTopay, SWT.READ_ONLY);
					cbUTStation.setBounds(590, 40, 96, 21);
					//cbUTStation.add("All");
					cbUTStation.select(0);
					cbUTStation.setEnabled(false);
					cbUTStation.addSelectionListener(this);
					
				}
				
				{
					label13 = new Label(cvsUndeliveredTopay, SWT.NONE);
					label13.setText("Report Type");
					label13.setBounds(342, 24, 68, 20);
				}
				{
					cbUTReportType = new Combo(cvsUndeliveredTopay,
							SWT.READ_ONLY);
					cbUTReportType.setBounds(415, 24, 86, 21);
					cbUTReportType.add("Detailed");
					cbUTReportType.add("Summary");
					cbUTReportType.select(1);
					cbUTReportType.addSelectionListener(this);
				}

				{
					label12 = new Label(cvsUndeliveredTopay, SWT.NONE);
					label12.setText("Branch");
					label12.setBounds(515, 13, 72, 19);
				}
				{
					cbUTBranch = new Combo(cvsUndeliveredTopay, SWT.READ_ONLY);
					cbUTBranch.setBounds(590, 13, 96, 21);
					//cbUTBranch.add("All");
					 if(clientRights == 1 || clientRights == 1.0){
							
							try{
							
								getCurrentBranch_code(currentStationCode);
								cbUTBranch.select(0);
								handleBranchActionUT(cbUTBranch, cbUTStation);
								cbUTBranch.addSelectionListener(this);
							}
							catch(Exception e){
								e.printStackTrace();
							}
							
							
						}
						else{
					
							try{
								StationsDTO[] dto = handler.getAllBranches();
								if (null != dto) {
									cbUTBranch.add("All");
									for (int i = 0; i < dto.length; i++) {
										cbUTBranch.add(dto[i].getName() + " - "
												+ dto[i].getId());
									}
								}
								handleBranchActionUT(cbUTBranch, cbUTStation);
								cbUTBranch.addSelectionListener(this);
							}
							catch(Exception e){
								e.printStackTrace();
							}
							cbUTBranch.select(0);
							cbUTBranch.setEnabled(true);
							//cbUTBranch.addSelectionListener(this);
						}
				}

				
				{
					btnUTGo = new Button(cvsUndeliveredTopay, SWT.PUSH);
					btnUTGo.setBounds(725, 30, 36, 23);
					btnUTGo.setText("Go");
					btnUTGo.addSelectionListener(this);
				}
			}

			{
				tblUndeliveredTopay = new Table(cvsUndeliveredTopay,
						SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL
								| SWT.BORDER);

				tblUndeliveredTopay.setHeaderVisible(true);
				tblUndeliveredTopay.setLinesVisible(true);
				tblUndeliveredTopay.setBounds(10, 72, 800, 400);
				{
					colTUTsno = new TableColumn(tblUndeliveredTopay, SWT.NONE);
					colTUTsno.setText("S NO");
					colTUTsno.setWidth(40);

				}
				{
					colTUTBranchCode = new TableColumn(tblUndeliveredTopay,
							SWT.NONE);
					colTUTBranchCode.setText("Branch Code");
					colTUTBranchCode.setWidth(94);

				}
				{
					colTUTStationCode = new TableColumn(tblUndeliveredTopay,
							SWT.NONE);
					colTUTStationCode.setText("Station Code");
					colTUTStationCode.setWidth(94);
				}
				{
					colTUTBoookedNoOfLRs = new TableColumn(tblUndeliveredTopay,
							SWT.NONE);
					colTUTBoookedNoOfLRs.setText("Inwarded No.Of.LRs");
					colTUTBoookedNoOfLRs.setWidth(120);

				}
				{
					colTUTBoookedTotFrt = new TableColumn(tblUndeliveredTopay,
							SWT.NONE);
					colTUTBoookedTotFrt.setText("Inwarded Total Freight");
					colTUTBoookedTotFrt.setWidth(130);

				}
				{
					colTUTDeliverNoOfLRs = new TableColumn(tblUndeliveredTopay,
							SWT.NONE);
					colTUTDeliverNoOfLRs.setText("Yet to Deliver No.Of.LRs");
					colTUTDeliverNoOfLRs.setWidth(140);

				}
				{
					colTUTDeliverTotFrt = new TableColumn(tblUndeliveredTopay,
							SWT.NONE);
					colTUTDeliverTotFrt.setText("Yet to Deliver Total Freight");
					colTUTDeliverTotFrt.setWidth(150);

				}

				for (int index = 0; index < tblUndeliveredTopay
						.getColumnCount(); index++) {
					tblUndeliveredTopay.getColumn(index).addListener(
							SWT.Selection, this);
				}

			}

			tblUndeliveredTopay.setVisible(true);
			tblUndeliveredTopayDet.setVisible(false);
			// cbUTBranch.setEnabled(false);

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
					
						cbUTBranch.add(stations[i].getName()+" - "+branch_code);
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

	
	private void handleBranchActionUT(Combo branch, Combo cbStation) {

		StationsDTO[] station = null;
		String value = "";
		String stationCode = "";

		try {
			value = branch.getText();
			if(value.equalsIgnoreCase("All")){
				cbStation.removeAll();
				cbStation.add("All");
			}
			else{
						
				int index = value.indexOf(" - ");
				stationCode = value.substring(index + 3);
	
				if (stationCode != null) {
					station = handler.getStations(stationCode);
				}
				if (null != station) {
	
					int len = station.length;
					cbStation.removeAll();
					cbStation.add("All");
					for (int i = 0; i < len; i++) {
						cbStation.add(station[i].getName() + " - "
								+ station[i].getId());
					}
				
				}
			}
		} catch (Exception exception) {

		}
	}
	
	
	private void handleUT() {
		try {

			/*String year = txtTUTMonth.getText();
			int index1 = year.indexOf("-");
			String month = year.substring(0, index1);
			year = year.substring(index1 + 1);*/
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date fromDt = dateFormat.parse(txtUTFrom.getText());
			Date toDate = dateFormat.parse(txtUTTo.getText());

			String branch = cbUTBranch.getText();

			if (cbUTReportType.getText().equalsIgnoreCase("Detailed")) {
				if (cbUTBranch.getSelectionIndex() != -1) {
					BookingDTO[] utDTO = null;

					if (cbUTBranch.getText().equalsIgnoreCase("All")) {
						utDTO = getUnDeliveredTopayDetailed("%", fromDt,toDate);
					} else {
						branch = cbUTBranch.getText();
						int index = branch.indexOf(" - ");
						branch = branch.substring(index + 3);
						
						 utDTO = getUnDeliveredTopayDetailed(branch,fromDt,toDate);
						
					}

					if (tblUndeliveredTopayDet != null)
						tblUndeliveredTopayDet.removeAll();
					if (utDTO != null) {
						populateUTDetailed(utDTO);
					} else {
						// AdminComposite.display("No Records Found",
						// STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					}

				}
			} else if (cbUTReportType.getText().equalsIgnoreCase("Summary")) {
				// summary

				

				GeneralSummaryDTO[] dto = null;
				// System.out.println("br-->"+branch);
				branch = cbUTBranch.getText();
				if (!branch.equalsIgnoreCase("All")) {
					int index = branch.indexOf(" - ");
					branch = branch.substring(index + 3);
					populateSelectedStations(branch,tblUndeliveredTopay);
				}
				else{
					populateAllStations(tblUndeliveredTopay);
				}

				dto = getUnDeliveredTopay(fromDt,
						toDate, branch);
				
				/*
				 * if(tblUndeliveredTopay != null)
				 * tblUndeliveredTopay.removeAll();
				 */

				if (dto != null) {
					populateUT(dto);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
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
	
	private GeneralSummaryDTO[] getUnDeliveredTopay(Date fromDt, Date toDate,
			String branch) {
		//int monthDiff = 0;
		GeneralSummaryDTO[] dto = null;
		try {
			/*Date curDate = new Date();
			int m1 = year * 12 + month;
	        int m2 = curDate.getYear() * 12 + curDate.getMonth();
	        monthDiff = m2 - m1;
			Date curDate = new Date();
			int m1 = fromDt.getYear() * 12 + fromDt.getMonth();
	        int m2 = curDate.getYear() * 12 + curDate.getMonth();
	        monthDiff = m2 - m1;*/
			Date date = BeanUtil.getThree_month_updated();
		if(toDate.before(date)){
			System.out.println("in ddr histry-->"+date);
			dto = handler.getUnDeliveredTopayHistory(fromDt,toDate,branch);
		}else if(fromDt.after(date)){
			dto = handler.getUnDeliveredTopay(fromDt,toDate,branch);
		}else{
			dto = handler.getUnDeliveredTopayUnion(fromDt,toDate,branch);
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	
		
	}

	private BookingDTO[] getUnDeliveredTopayDetailed(String branch,
			Date fromDate, Date toDate) {
		//int monthDiff = 0;
		BookingDTO[] utDTO = null;
		try {
			/*Date curDate = new Date();
			int m1 = year * 12 + month;
	        int m2 = curDate.getYear() * 12 + curDate.getMonth();
	        monthDiff = m2 - m1;*/
			/*Date curDate = new Date();
			int m1 = fromDate.getYear() * 12 + fromDate.getMonth();
	        int m2 = curDate.getYear() * 12 + curDate.getMonth();
	        monthDiff = m2 - m1;*/
			Date date = BeanUtil.getThree_month_updated();
		if(toDate.before(date)){
			System.out.println("in ddr histry-->"+date);
			utDTO = handler.getUnDeliveredTopayDetailedHistory(branch,fromDate,toDate);
		}else if (fromDate.after(date)){
			utDTO = handler.getUnDeliveredTopayDetailedUnion(branch,fromDate,toDate);
		}else{
			utDTO = handler.getUnDeliveredTopayDetailed(branch,fromDate,toDate);
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return utDTO;
	
	}
	
	private String getRoundedValue(float cardRate) {
		DecimalFormat decimalFormat;
		decimalFormat = new DecimalFormat("0.00");
		cardRate = cardRate * 100;
		cardRate = (float) Math.round(cardRate);
		cardRate = cardRate / 100;

		return decimalFormat.format(cardRate);
	}
	
	private void populateUT(GeneralSummaryDTO[] dto) throws Exception {

		TableItem[] items = tblUndeliveredTopay.getItems();
		int tot3 = 0;
		float tot4 = 0;
		int tot5 = 0;
		float tot6 = 0;
		
		
		if (items != null) {
			for (int j = 0; j < items.length; j++) {
				int lr_no = 0;
				float Total = 0;
				for (int i = 0; i < dto.length; i++) {
					if (dto[i].getStation_code().equalsIgnoreCase(
							items[j].getText(2))) {
						if ( ! dto[i].getSundry_type().equalsIgnoreCase(
								"Delivered")) {
							items[j].setText(5, String.valueOf(dto[i]
							   									.getLr_no()));
							items[j].setText(6, getRoundedValue(dto[i]
							   									.getTotal()));
						} 
						lr_no = lr_no + dto[i].getLr_no();
						items[j].setText(3, String.valueOf(lr_no));
						Total = Total + dto[i].getTotal();
						items[j].setText(4, getRoundedValue(Total));
							
						
					}
				}

				if (!items[j].getText(3).equals(""))
					tot3 = tot3 + Integer.parseInt(items[j].getText(3));
				if (!items[j].getText(4).equals(""))
					tot4 = tot4 + Float.parseFloat(items[j].getText(4));
				if (!items[j].getText(5).equals(""))
					tot5 = tot5 + Integer.parseInt(items[j].getText(5));
				if (!items[j].getText(6).equals(""))
					tot6 = tot6 + Float.parseFloat(items[j].getText(6));

			}

			final TableItem item1 = new TableItem(tblUndeliveredTopay, SWT.NONE);
			Font font1 = new Font(Display.getCurrent(), "Tahoma", 8, SWT.BOLD);
			item1.setFont(font1);
			item1.setText(2, "TOTAL");
			item1.setText(3, String.valueOf(tot3));
			item1.setText(4, getRoundedValue(tot4));
			item1.setText(5, String.valueOf(tot5));
			item1.setText(6, getRoundedValue(tot6));
		}

		new sortListener().fillZerosOnEmpty(tblUndeliveredTopay);
		
	}

	private void populateUTDetailed(BookingDTO[] utDTO) {
		int tot5 = 0;
		float tot6 = 0;
		float tot7 = 0;
		float tot8 = 0;
		String Branchcode = cbUTStation.getText();
		if(Branchcode.equalsIgnoreCase("All")){
			Branchcode = "All";
		}
		else{
			int index1 = Branchcode.indexOf(" - ");
			Branchcode = Branchcode.substring(index1 + 3);
		}
		String stationcode = cbUTStation.getText();
		if(stationcode.equalsIgnoreCase("All")){
			stationcode = "All";
		}
		else{
			int index = stationcode.indexOf(" - ");
			stationcode = stationcode.substring(index + 3);
		}	
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		int j = 0;
		for (int i = 0;  i < utDTO.length; i++) {
			
			if(( !(Branchcode.equalsIgnoreCase("All"))) && ( !(stationcode.equalsIgnoreCase("All")))){
				
				if(stationcode.equalsIgnoreCase(utDTO[i].getArticle_description())){
				
					TableItem item = new TableItem(tblUndeliveredTopayDet, SWT.NONE);
					j++;
					item.setText(0, String.valueOf(j));
					item.setText(1, utDTO[i].getLrno());
					item.setText(2, dateFormat.format(utDTO[i].getDate()));
					item.setText(3, utDTO[i].getFrom());
					item.setText(4, utDTO[i].getTo());
					tot5 = tot5 + utDTO[i].getNo_of_articles();
					item.setText(5, String.valueOf(utDTO[i].getNo_of_articles()));
					tot6 = tot6 + utDTO[i].getActual_weight();
					item.setText(6, getRoundedValue(utDTO[i].getActual_weight()));
					tot7 = tot7 + utDTO[i].getBft();
					item.setText(7, getRoundedValue(utDTO[i].getBft()));
					tot8 = tot8 + utDTO[i].getTotal();
					item.setText(8, getRoundedValue(utDTO[i].getTotal()));
					item.setText(9, utDTO[i].getType());
					
				}
			}
			else{
				TableItem item = new TableItem(tblUndeliveredTopayDet, SWT.NONE);
				
				item.setText(0, String.valueOf(i + 1));
				item.setText(1, utDTO[i].getLrno());
				item.setText(2, dateFormat.format(utDTO[i].getDate()));
				item.setText(3, utDTO[i].getFrom());
				item.setText(4, utDTO[i].getTo());
				tot5 = tot5 + utDTO[i].getNo_of_articles();
				item.setText(5, String.valueOf(utDTO[i].getNo_of_articles()));
				tot6 = tot6 + utDTO[i].getActual_weight();
				item.setText(6, getRoundedValue(utDTO[i].getActual_weight()));
				tot7 = tot7 + utDTO[i].getBft();
				item.setText(7, getRoundedValue(utDTO[i].getBft()));
				tot8 = tot8 + utDTO[i].getTotal();
				item.setText(8, getRoundedValue(utDTO[i].getTotal()));
				item.setText(9, utDTO[i].getType());
			}
			
				

		}

		final TableItem item1 = new TableItem(tblUndeliveredTopayDet, SWT.NONE);
		Font font1 = new Font(Display.getCurrent(), "Tahoma", 8, SWT.BOLD);
		item1.setFont(font1);
		item1.setText(4, "TOTAL");
		item1.setText(5, String.valueOf(tot5));
		item1.setText(6, getRoundedValue(tot6));
		item1.setText(7, getRoundedValue(tot7));
		item1.setText(8, getRoundedValue(tot8));
		
		new sortListener().fillZerosOnEmpty(tblUndeliveredTopayDet);
	}

	
	private String[] getUttopayHeading(Table refTable){
		
		String list[] = new String[5];
		
		list[0] = txtUTFrom.getText();
		list[1] = txtUTTo.getText();
		if(cbUTReportType.getText().equals("Summary"))
			list[2] = "Summary";
		else
			list[2]= "Detailed";
		
		String branchCode = cbUTBranch.getText();
		if (!branchCode.equalsIgnoreCase("All")) {
			int index = branchCode.indexOf(" - ");
			list[3] = branchCode.substring(index + 3);
		}
		else
			list[3] ="All";
		
		String stationCode = cbUTStation.getText();
		if (!stationCode.equalsIgnoreCase("All")) {
			int index = stationCode.indexOf(" - ");
			list[4] = stationCode.substring(index + 3);
		}
		else
			list[4] ="All";
		
		return list;
	}
	
	@Override
	public void handleEvent(Event e) {
		// TODO Auto-generated method stub
		String selection = tabReport.getSelection()[0].getText();
		TableColumn column = (TableColumn) e.widget;
		
		if (!cbUTReportType.getText().equalsIgnoreCase("Summary")) {
			if (column == colUTsno) {
				new sortListener().sortByFloat(0, tblUndeliveredTopayDet);
			} else if (column == colUTLrno) {
				new sortListener().sortByLrNo(1, tblUndeliveredTopayDet);
			} else if (column == colUTLrdate) {
				new sortListener().sortByDate(2, tblUndeliveredTopayDet);
			} else if (column == colUTFrom) {
				new sortListener().sortByString(3, tblUndeliveredTopayDet);
			} else if (column == colUTTo) {
				new sortListener().sortByString(4, tblUndeliveredTopayDet);
			} else if (column == colUTnoa) {
				new sortListener().sortByFloat(5, tblUndeliveredTopayDet);
			} else if (column == colUTActWt) {
				new sortListener().sortByFloat(6, tblUndeliveredTopayDet);
			} else if (column == colUTbft) {
				new sortListener().sortByFloat(7, tblUndeliveredTopayDet);
			} else if (column == colUTtotalFt) {
				new sortListener().sortByFloat(8, tblUndeliveredTopayDet);
			} else if (column == colUTCurStatus) {
				new sortListener().sortByString(9, tblUndeliveredTopayDet);
			}
		} else {
			if (column == colTUTsno) {
				new sortListener().sortByFloat(0, tblUndeliveredTopay);
			} else if (column == colTUTBranchCode) {
				new sortListener().sortByString(1, tblUndeliveredTopay);
			} else if (column == colTUTStationCode) {
				new sortListener().sortByString(2, tblUndeliveredTopay);
			} else if (column == colTUTBoookedNoOfLRs) {
				new sortListener().sortByFloat(3, tblUndeliveredTopay);
			} else if (column == colTUTBoookedTotFrt) {
				new sortListener().sortByFloat(4, tblUndeliveredTopay);
			} else if (column == colTUTDeliverNoOfLRs) {
				new sortListener().sortByFloat(5, tblUndeliveredTopay);
			} else if (column == colTUTDeliverTotFrt) {
				new sortListener().sortByFloat(6, tblUndeliveredTopay);
			}
		}

	}

	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void widgetSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub
		Object source = arg0.getSource();
		
		if(source == cbUTBranch ){
			
			handleBranchActionUT(cbUTBranch, cbUTStation);
			
			
		}
			
		else if (source == cbUTReportType) {
			try {
				if (cbUTReportType.getText().equalsIgnoreCase("Detailed")) {
					tblUndeliveredTopayDet.setVisible(true);
					tblUndeliveredTopayDet.removeAll();
					tblUndeliveredTopay.setVisible(false);
					cbUTStation.setEnabled(true);
				} else {
					tblUndeliveredTopayDet.setVisible(false);
					tblUndeliveredTopay.removeAll();
					tblUndeliveredTopay.setVisible(true);
					cbUTStation.setEnabled(false);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (source == btnUTGo) {
			new sortListener().display("Generating Report...", STATUS_SUCCESS,
					SUCCESS_FONT_COLOR,lblStatusBar);

			handleUT();
			new sortListener().display("Report Loaded Successfully!",
					STATUS_SUCCESS, SUCCESS_FONT_COLOR,lblStatusBar);
		}
		
		else if (source == btnExportXL) {
			try {
				//handleExcel();
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				if (cbUTReportType.getText().equalsIgnoreCase("Summary")) {
					list = new sortListener().getBPATable(tblUndeliveredTopay);
					value = getUttopayHeading(tblUndeliveredTopay);
					new sortListener().prepareExcel(list, "Undelivered_Topay", UT_EXCEL_JRXML, null,value,shell,lblStatusBar);
				} else {
					value = getUttopayHeading(tblUndeliveredTopay);
					list = new sortListener(). getBPATable(tblUndeliveredTopayDet);
					new sortListener().prepareExcel(list, "Undelivered_Topay_Detailed",
							UT_DET_EXCEL_JRXML, null,value,shell,lblStatusBar);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else if (source == btnPrint) {
			try {
				//handlePrint();
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				if (cbUTReportType.getText().equalsIgnoreCase("Summary")) {
					list = new sortListener().getBPATable(tblUndeliveredTopay);
					value = getUttopayHeading(tblUndeliveredTopay);
					handler.preparePrint(list, "Undelivered_Topay", UT_EXCEL_JRXML, null,value);
				} else {
					value = getUttopayHeading(tblUndeliveredTopay);
					list = new sortListener().getBPATable(tblUndeliveredTopayDet);
					handler.preparePrint(list, "Undelivered_Topay_Detailed",
							UT_DET_EXCEL_JRXML, null,value);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		else if (source == btnExportPDF) {
			try {
				//handlePDF();
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				if (cbUTReportType.getText().equalsIgnoreCase("Summary")) {
					list = new sortListener().getBPATable(tblUndeliveredTopay);
					value = getUttopayHeading(tblUndeliveredTopay);
					new sortListener().preparePDF(list, "Undelivered_Topay", UT_EXCEL_JRXML, null,value,shell,lblStatusBar);
				} else {
					value = getUttopayHeading(tblUndeliveredTopay);
					list = new sortListener().getBPATable(tblUndeliveredTopayDet);
					new sortListener().preparePDF(list, "Undelivered_Topay_Detailed",
							UT_DET_EXCEL_JRXML, null,value,shell,lblStatusBar);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		
	}

}
