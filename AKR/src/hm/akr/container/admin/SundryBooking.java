package hm.akr.container.admin;


import hm.akr.common.sortListener;
import hm.akr.common.SWTResourceManager;
import hm.akr.common.BeanUtil;
import hm.akr.common.IUIConstants;
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


public class SundryBooking extends ReportsMenu implements IUIConstants,Listener,SelectionListener {

	private static Label lblStatusBar;
	private TabFolder tabReport;
	private TabItem tiSundryBookingAnalysis;
	private Canvas canSundryBookAnaysis;
	private Table tblSundryBookAnalysis;
	private TableColumn colSBASNo;
	private TableColumn colBranchCode;
	private TableColumn colStationCode;
	private TableColumn colTotalLr;
	private TableColumn colTotalSundryLrs;
	private TableColumn colTotalLrFreight;
	private TableColumn colTotalSundryFreight;
	private TableColumn colTotalSundryBft;
	private TableColumn colTotalSundryNoa;
	private TableColumn colTotalSundryAwt;
	private TableColumn colTotalSundryCrgWt;
	private TableColumn colOnTurnOver;
	private Text txtSBAFrom;
	private Button btnSBAFrom;
	private Text txtSBATo;
	private Combo cbSBABranch;
	private Label label11;
	private Label label12;
	private Label label13;
	private Label lblTo;
	

	private Button btnSBATo;
	private Button btnSBAGo;
	private Combo cbSBAStation;
	private String SUNDRY_BOOKING_TAB = "Sundry Booking Analysis";
	private static final String SBA_EXCEL_JRXML = "hm/akr/resources/printer/SBA.jrxml";

	
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
	
	public SundryBooking(Composite composite,int style, float clientRights)
	{
		super(composite,style);
		
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
		
		this.setBounds(45, 100, 1020, 700);
		tabReport = new TabFolder(this, SWT.NONE);
		tabReport.setBounds(50, 30, 820, 500);
		tabReport.addSelectionListener(this);
		{
			
			tiSundryBookingAnalysis = new TabItem(tabReport, SWT.NONE);
			tiSundryBookingAnalysis.setText(SUNDRY_BOOKING_TAB);
			{
				canSundryBookAnaysis = new Canvas(tabReport, SWT.BORDER);

				tiSundryBookingAnalysis.setControl(canSundryBookAnaysis);
				{
					tblSundryBookAnalysis = new Table(canSundryBookAnaysis,
							SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL
									| SWT.BORDER);
					tblSundryBookAnalysis.setHeaderVisible(true);
					tblSundryBookAnalysis.setLinesVisible(true);
					tblSundryBookAnalysis.setBounds(10, 70, 780, 400);
					{
						colSBASNo = new TableColumn(tblSundryBookAnalysis,
								SWT.NONE);
						colSBASNo.setText("S.No.");
						colSBASNo.setWidth(51);
					}
					{
						colBranchCode = new TableColumn(tblSundryBookAnalysis,
								SWT.NONE);
						colBranchCode.setText("Branch Code");
						colBranchCode.setWidth(100);
					}
					{
						colStationCode = new TableColumn(tblSundryBookAnalysis,
								SWT.NONE);
						colStationCode.setText("Station Code");
						colStationCode.setWidth(100);
					}
					{
						colTotalLr = new TableColumn(tblSundryBookAnalysis,
								SWT.NONE);
						colTotalLr.setText("Total Lrs");
						colTotalLr.setWidth(100);
					}
					{
						colTotalSundryLrs = new TableColumn(
								tblSundryBookAnalysis, SWT.NONE);
						colTotalSundryLrs.setText("Total Sundry Lrs");
						colTotalSundryLrs.setWidth(101);
					}

					{
						colTotalLrFreight = new TableColumn(
								tblSundryBookAnalysis, SWT.NONE);
						colTotalLrFreight.setText("Total Freight");
						colTotalLrFreight.setWidth(100);
					}
					{
						colTotalSundryFreight = new TableColumn(
								tblSundryBookAnalysis, SWT.NONE);
						colTotalSundryFreight.setText("Total Sundry Freight");
						colTotalSundryFreight.setWidth(100);
					}
					{
						colTotalSundryBft = new TableColumn(
								tblSundryBookAnalysis, SWT.NONE);
						colTotalSundryBft.setText("Total Sundry Basic Freight");
						colTotalSundryBft.setWidth(100);
					}
					{
						colTotalSundryNoa = new TableColumn(
								tblSundryBookAnalysis, SWT.NONE);
						colTotalSundryNoa
								.setText("Total Sundry No. of .Articles");
						colTotalSundryNoa.setWidth(100);
					}
					{
						colTotalSundryAwt = new TableColumn(
								tblSundryBookAnalysis, SWT.NONE);
						colTotalSundryAwt.setText("Total Sundry Actual Wt");
						colTotalSundryAwt.setWidth(100);
					}

					{
						colTotalSundryCrgWt = new TableColumn(
								tblSundryBookAnalysis, SWT.NONE);
						colTotalSundryCrgWt.setText("Total Sundry Crg Wt");
						colTotalSundryCrgWt.setWidth(100);
					}

					{
						colOnTurnOver = new TableColumn(tblSundryBookAnalysis,
								SWT.NONE);
						colOnTurnOver.setText("% of Sundry/Total");
						colOnTurnOver.setWidth(100);
					}

					for (int index = 0; index < tblSundryBookAnalysis
							.getColumnCount(); index++) {
						tblSundryBookAnalysis.getColumn(index).addListener(
								SWT.Selection, this);
					}

					{
						label11 = new Label(canSundryBookAnaysis, SWT.NONE);
						label11.setText("From Date");
						label11.setBounds(10, 35, 55, 18);
					}

					{
						txtSBAFrom = new Text(canSundryBookAnaysis, SWT.BORDER);
						txtSBAFrom.setBounds(70, 35, 70, 20);
						txtSBAFrom.setEditable(false);
						DateFormat dateFormat = new SimpleDateFormat(
								"dd-MM-yyyy");
						java.util.Date currentDate = new java.util.Date();
						currentDate.setDate(currentDate.getDate() - 6);
						String date = dateFormat.format(currentDate);
						txtSBAFrom.setText(date);

					}
					{
						btnSBAFrom = new Button(canSundryBookAnaysis, SWT.PUSH
								| SWT.CENTER);
						btnSBAFrom.setBounds(140, 36, 30, 21);
						btnSBAFrom.setImage(SWTResourceManager
								.getImage("hm/akr/resources/Calendar.jpg"));
						btnSBAFrom.addSelectionListener(new SelectionAdapter() {
							public void widgetSelected(SelectionEvent e) {
								KalendarDialog cd = new KalendarDialog(
										shell);
								Object obj = cd.open();
								if (obj != null) {
									String date = obj.toString();
									txtSBAFrom.setText(date);
								}
							}
						});
					}
					{
						lblTo = new Label(canSundryBookAnaysis, SWT.NONE);
						lblTo.setText("To Date");
						lblTo.setBounds(185, 36, 43, 18);
					}
					{
						txtSBATo = new Text(canSundryBookAnaysis, SWT.BORDER);
						txtSBATo.setBounds(235, 36, 70, 20);
						txtSBATo.setEditable(false);
						DateFormat dateFormat = new SimpleDateFormat(
								"dd-MM-yyyy");
						java.util.Date currentDate = new java.util.Date();
						String date = dateFormat.format(currentDate);
						txtSBATo.setText(date);

					}

					{
						btnSBATo = new Button(canSundryBookAnaysis, SWT.PUSH
								| SWT.CENTER);
						btnSBATo.setBounds(305, 36, 30, 21);
						btnSBATo.setImage(SWTResourceManager
								.getImage("hm/akr/resources/Calendar.jpg"));
						btnSBATo.addSelectionListener(new SelectionAdapter() {
							public void widgetSelected(SelectionEvent e) {
								KalendarDialog cd = new KalendarDialog(
										shell);
								Object obj = cd.open();
								if (obj != null) {
									String date = obj.toString();
									txtSBATo.setText(date);
								}
							}
						});

					}

					{
						label13 = new Label(canSundryBookAnaysis, SWT.NONE);
						label13.setText("Select Station");
						label13.setBounds(515, 35, 80, 21);
					}
					{
						cbSBAStation = new Combo(canSundryBookAnaysis,
								SWT.DROP_DOWN | SWT.READ_ONLY);
						cbSBAStation.setBounds(605, 35, 85, 21);
						cbSBAStation.addSelectionListener(this);
					}
					
					{
						label12 = new Label(canSundryBookAnaysis, SWT.NONE);
						label12.setText("Select Branch");
						label12.setBounds(340, 37, 70, 17);
					}
					{
						cbSBABranch = new Combo(canSundryBookAnaysis,
								SWT.DROP_DOWN | SWT.READ_ONLY);
						cbSBABranch.setBounds(420, 35, 85, 21);
						//cbSBABranch.add("All");
						//cbSBABranch.addSelectionListener(this);
						if(clientRights == 1 || clientRights == 1.0){
							
							try{
							
								getCurrentBranch_code(currentStationCode);
								cbSBABranch.select(0);
								String branch = cbSBABranch.getText();
								int index = branch.indexOf(" - ");
								branch = branch.substring(index + 3);
								// if (cbSBABranch.getSelectionIndex() != -1) {
								sundryBranchComboAction(branch, cbSBAStation);
								cbSBABranch.addSelectionListener(this);
							}
							catch(Exception e){
								e.printStackTrace();
							}
							
							
						}
						else{
						
						
							try{
								StationsDTO[] dto = handler.getAllBranches();
								if (null != dto) {
									cbSBABranch.add("All");
									for (int i = 0; i < dto.length; i++) {
										cbSBABranch.add(dto[i].getName() + " - "
												+ dto[i].getId());
									}
								}
								cbSBABranch.addSelectionListener(this);
							}
							catch(Exception e){
								e.printStackTrace();
							}
						}
					}

					

					/*
					 * { label13 = new Label(canSundryBookAnaysis, SWT.NONE);
					 * label13.setText("Report Type"); label13.setBounds(622,
					 * 35, 63, 19); } { cbSBAReportType = new
					 * Combo(canSundryBookAnaysis, SWT.READ_ONLY);
					 * cbSBAReportType.setBounds(685, 35, 73, 21);
					 * cbSBAReportType.add("Consolidated");
					 * cbSBAReportType.add("Detailed");
					 * cbSBAReportType.addSelectionListener(this); }
					 */
					{
						btnSBAGo = new Button(canSundryBookAnaysis, SWT.PUSH
								| SWT.CENTER);
						btnSBAGo.setText("Go");
						btnSBAGo.setBounds(700, 30, 36, 28);
						btnSBAGo.addSelectionListener(this);
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

				}

			}

			
			/*if (shell != null) {
				shell.open();
				Display display = shell.getDisplay();
				while (!shell.isDisposed()) {
					if (!display.readAndDispatch()) {
						display.sleep();
					}
				}
			}*/
			
			
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
					
						cbSBABranch.add(stations[i].getName()+" - "+branch_code);
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


	
	private void sundryBranchComboAction(String branch, Combo cbStation) {

		StationsDTO[] stations = null;
		try {
			stations = handler.getAllStations();
			int size = 0;
			if (null != stations) {
				size = stations.length;
				cbStation.removeAll();
				cbStation.add("All");
				if (!branch.equalsIgnoreCase("All")) {
					for (int i = 0; i < size; i++) {
						if (branch.equals(stations[i].getBranch_code())) {
							cbStation.add(stations[i].getId());
						}
					}
				} else {
					for (int i = 0; i < size; i++) {
						cbStation.add(stations[i].getId());
					}
				}

			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}

	private void populateSundryBooking(GeneralSummaryDTO[] sundryBooking,
			boolean isAdmin) throws Exception {
		TableItem[] items = tblSundryBookAnalysis.getItems();
		String dtoBranchCode = null;
		String tableBranchCode = null;
		Boolean isBranch = false;
		//System.out.println("sundry-->"+sundryBooking.length);
		//System.out.println("items-->"+items.length);
		for (int i = 0; i < sundryBooking.length; i++) {
			for (int j = 0; j < items.length; j++) {
			int availLrNo = 0;
			float availTotal = 0;
			int lrNo = 0;
			float total = 0;
			float bft = 0;
			int noa = 0;
			float actWt = 0;
			float cw = 0;
			float  sundryoftotal = 0;
				if(!isAdmin){
					dtoBranchCode = sundryBooking[i].getStation_code();
					
				}else{
					dtoBranchCode = getBranch_code(sundryBooking[i]
						.getStation_code());
					isBranch = true;
				}
				tableBranchCode = items[j].getText(2);
				
				if (dtoBranchCode.equalsIgnoreCase(tableBranchCode)) {
					
						if (!items[j].getText(3).equals("")) {
							availLrNo = Integer.parseInt(items[j].getText(3));
						}
	
						if (!items[j].getText(5).equals("")) {
							availTotal = Float.parseFloat(items[j].getText(5));
						}
	
						if (!items[j].getText(4).equals("")) {
							lrNo = Integer.parseInt(items[j].getText(4));
						}
	
						if (!items[j].getText(6).equals("")) {
							total = Float.parseFloat(items[j].getText(6));
						}
	
						if (!items[j].getText(7).equals("")) {
							bft = Float.parseFloat(items[j].getText(7));
						}
	
						if (!items[j].getText(8).equals("")) {
							noa = Integer.parseInt(items[j].getText(8));
						}
	
						if (!items[j].getText(9).equals("")) {
							actWt = Float.parseFloat(items[j].getText(9));
						}
	
						if (!items[j].getText(10).equals("")) {
							cw = Float.parseFloat(items[j].getText(10));
						}
					}

					if (sundryBooking[i].getSundry_type().equalsIgnoreCase(
							"Sundry")) {
						items[j].setText(3, String.valueOf(sundryBooking[i]
								.getLr_no()
								+ availLrNo));
						items[j].setText(5, getRoundedValue(sundryBooking[i]
								.getTotal()
								+ availTotal));
						items[j].setText(4, String.valueOf(sundryBooking[i]
								.getLr_no()
								+ lrNo));
						items[j].setText(6, getRoundedValue(sundryBooking[i]
								.getTotal()
								+ total));
						items[j].setText(7, getRoundedValue(sundryBooking[i]
								.getBft()
								+ bft));
						items[j].setText(8, String.valueOf(sundryBooking[i]
								.getNo_of_articles()
								+ noa));
						items[j].setText(9, getRoundedValue(sundryBooking[i]
								.getActual_weight()
								+ actWt));
						items[j].setText(10, getRoundedValue(sundryBooking[i]
								.getCharged_weight()
								+ cw));
					} else {
						items[j].setText(3, String.valueOf(sundryBooking[i]
								.getLr_no()
								+ availLrNo));
						items[j].setText(5, getRoundedValue(sundryBooking[i]
								.getTotal()
								+ availTotal));
					}
					
					if((!items[j].getText(5).equalsIgnoreCase(""))&& (!items[j].getText(6).equalsIgnoreCase("")))
						{
							sundryoftotal = (((Float.parseFloat(items[j].getText(6))) / (Float.parseFloat(items[j].getText(5)))) * 100);
							items[j].setText(11, getRoundValue(sundryoftotal));
						}
					
					
				//}
			}
		}

		calcSBAtotal();
		new sortListener().fillZerosOnEmpty(tblSundryBookAnalysis);
	}

	private void calcSBAtotal() {
		TableItem[] items = tblSundryBookAnalysis.getItems();
		int tot3 = 0;
		int tot4 = 0;
		double tot5 = 0;
		double tot6 = 0;
		double tot7 = 0;
		int tot8 = 0;
		double tot9 = 0;
		double tot10 = 0;
		double tot11=0;
		double average=0;
		
		int count=0;
		

		if (items != null) {
			for (int i = 0; i < items.length; i++) {
				if (!items[i].getText(3).equals(""))
					tot3 = tot3 + Integer.parseInt(items[i].getText(3));
				if (!items[i].getText(4).equals(""))
					tot4 = tot4 + Integer.parseInt(items[i].getText(4));
				if (!items[i].getText(5).equals(""))
					tot5 = tot5 + Double.parseDouble(items[i].getText(5));
				if (!items[i].getText(6).equals(""))
					tot6 = tot6 + Double.parseDouble(items[i].getText(6));
				if (!items[i].getText(7).equals(""))
					tot7 = tot7 + Double.parseDouble(items[i].getText(7));
				if (!items[i].getText(8).equals(""))
					tot8 = tot8 + Integer.parseInt(items[i].getText(8));
				if (!items[i].getText(9).equals(""))
					tot9 = tot9 + Double.parseDouble(items[i].getText(9));
				if (!items[i].getText(10).equals(""))
					tot10 = tot10 + Double.parseDouble(items[i].getText(10));
				if (!items[i].getText(11).equals(""))
					tot11 = tot11 + Double.parseDouble(items[i].getText(11));
				count++;
			}

		}
		
		average =  tot11 / count; 
		final TableItem item1 = new TableItem(tblSundryBookAnalysis, SWT.NONE);
		Font font1 = new Font(Display.getCurrent(), "Tahoma", 8, SWT.BOLD);
		item1.setFont(font1);
		item1.setText(1, "");
		item1.setText(2, "TOTAL");
		item1.setText(3, String.valueOf(tot3));
		item1.setText(4, String.valueOf(tot4));
		item1.setText(5, getRoundValue(tot5));
		item1.setText(6, getRoundValue(tot6));
		item1.setText(7, getRoundValue(tot7));
		item1.setText(8, String.valueOf(tot8));
		item1.setText(9, getRoundValue(tot9));
		item1.setText(10, getRoundValue(tot10));
		item1.setText(11, getRoundValue(tot11));
		final TableItem item2 = new TableItem(tblSundryBookAnalysis, SWT.NONE);
		item2.setFont(font1);
		item2.setText(10, "Average");
		item2.setText(11, getRoundValue(average));
	}

	private String getRoundValue(double cardRate) {
		DecimalFormat decimalFormat;
		decimalFormat = new DecimalFormat("0.00");
		cardRate = cardRate * 100;
		cardRate = (double) Math.round(cardRate);
		cardRate = cardRate / 100;

		return decimalFormat.format(cardRate);
	}
	

	private String getRoundedValue(float cardRate) {
		DecimalFormat decimalFormat;
		decimalFormat = new DecimalFormat("0.00");
		cardRate = cardRate * 100;
		cardRate = (float) Math.round(cardRate);
		cardRate = cardRate / 100;

		return decimalFormat.format(cardRate);
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
	
	private GeneralSummaryDTO[] getSundryBooking(Date fromDt, Date toDate, String branch, String station) {
		// TODO Auto-generated method stub
		GeneralSummaryDTO[] sundryBooking = null;
		try{
			Date date = BeanUtil.getThree_month_updated();
			if(toDate.before(date)){
				System.out.println("in ddr histry-->"+date);
				sundryBooking = handler.getSundryBookingHistory(fromDt, toDate,branch, station);
			}else if(fromDt.after(date)){
				sundryBooking = handler.getSundryBooking(fromDt, toDate,branch, station);				
			}else{
				sundryBooking = handler.getSundryBookingUnion(fromDt, toDate,branch, station);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return sundryBooking;
	}
	
	private void populateSundryBooking(GeneralSummaryDTO[] sundryBooking) {
		TableItem[] items = tblSundryBookAnalysis.getItems();
		 float sundryoftotal = 0;
		//System.out.println("sundry-->"+sundryBooking.length);
		//System.out.println("items-->"+items.length);
		for (int i = 0; i < sundryBooking.length; i++) {
			
			for (int j = 0; j < items.length; j++) {
				if (sundryBooking[i].getStation_code().equalsIgnoreCase(
						items[j].getText(2))) {
					int availLrNo = 0;
					float availTotal = 0;
					if (!items[j].getText(3).equals("")) {
						availLrNo = Integer.parseInt(items[j].getText(3));
					}

					if (!items[j].getText(5).equals("")) {
						availTotal = Float.parseFloat(items[j].getText(5));
					}

					if (sundryBooking[i].getSundry_type().equalsIgnoreCase(
							"Sundry")) {
						items[j].setText(3, String.valueOf(sundryBooking[i]
								.getLr_no()
								+ availLrNo));
						items[j].setText(5, getRoundedValue(sundryBooking[i]
								.getTotal()
								+ availTotal));
						items[j].setText(4, String.valueOf(sundryBooking[i]
								.getLr_no()));
						items[j].setText(6, getRoundedValue(sundryBooking[i]
								.getTotal()));
						items[j].setText(7, getRoundedValue(sundryBooking[i]
								.getBft()));
						items[j].setText(8, String.valueOf(sundryBooking[i]
								.getNo_of_articles()));
						items[j].setText(9, getRoundedValue(sundryBooking[i]
								.getActual_weight()));
						items[j].setText(10, getRoundedValue(sundryBooking[i]
								.getCharged_weight()));
					} else {
						items[j].setText(3, String.valueOf(sundryBooking[i]
								.getLr_no()
								+ availLrNo));
						items[j].setText(5, getRoundedValue(sundryBooking[i]
								.getTotal()
								+ availTotal));
					}
					
					if((!items[j].getText(5).equalsIgnoreCase(""))&& (!items[j].getText(6).equalsIgnoreCase("")))
					{
						sundryoftotal = (((Float.parseFloat(items[j].getText(6))) / (Float.parseFloat(items[j].getText(5)))) * 100);
						items[j].setText(11, getRoundValue(sundryoftotal));
					}
				
				}
			}
		}

		calcSBAtotal();
		new sortListener().fillZerosOnEmpty(tblSundryBookAnalysis);

	}
	private void populateSundryBooking(GeneralSummaryDTO[] sundryBooking, int isGroup) {
		TableItem[] items = tblSundryBookAnalysis.getItems();
		float sundryoftotal = 0;
		String branchCode = "";
		for (int i = 0; i < sundryBooking.length; i++) {
			
			try {
				for (int j = 0; j < items.length; j++) {
					branchCode = getBranch_code(sundryBooking[i].getStation_code());
					//System.out.println("group brabch-->"+branchCode);
					if (branchCode.equalsIgnoreCase(
							items[j].getText(2))) {
						int availLrNo = 0;
						float availTotal = 0;
						if (!items[j].getText(3).equals("")) {
							availLrNo = Integer.parseInt(items[j].getText(3));
						}

						if (!items[j].getText(5).equals("")) {
							availTotal = Float.parseFloat(items[j].getText(5));
						}

						if (sundryBooking[i].getSundry_type().equalsIgnoreCase(
								"Sundry")) {
							items[j].setText(3, String.valueOf(sundryBooking[i]
									.getLr_no()
									+ availLrNo));
							items[j].setText(5, getRoundedValue(sundryBooking[i]
									.getTotal()
									+ availTotal));
							items[j].setText(4, String.valueOf(sundryBooking[i]
									.getLr_no()));
							items[j].setText(6, getRoundedValue(sundryBooking[i]
									.getTotal()));
							items[j].setText(7, getRoundedValue(sundryBooking[i]
									.getBft()));
							items[j].setText(8, String.valueOf(sundryBooking[i]
									.getNo_of_articles()));
							items[j].setText(9, getRoundedValue(sundryBooking[i]
									.getActual_weight()));
							items[j].setText(10, getRoundedValue(sundryBooking[i]
									.getCharged_weight()));
						} else {
							items[j].setText(3, String.valueOf(sundryBooking[i]
									.getLr_no()
									+ availLrNo));
							items[j].setText(5, getRoundedValue(sundryBooking[i]
									.getTotal()
									+ availTotal));
						}
						
						if((!items[j].getText(5).equalsIgnoreCase(""))&& (!items[j].getText(6).equalsIgnoreCase("")))
						{
							sundryoftotal = (((Float.parseFloat(items[j].getText(6))) / (Float.parseFloat(items[j].getText(5)))) * 100);
							items[j].setText(11, String.valueOf(sundryoftotal));
						}
					}
				}
			} catch (NumberFormatException e) {				
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		calcSBAtotal();
		new sortListener().fillZerosOnEmpty(tblSundryBookAnalysis);

	}

	private void designSundryTable() throws Exception {
		if (tblSundryBookAnalysis != null) {
			tblSundryBookAnalysis.removeAll();
		}
		if (cbSBABranch.getText().equalsIgnoreCase("All")
				&& cbSBAStation.getSelectionIndex() == -1) {
			populateAllBranches(tblSundryBookAnalysis);
		} else if (cbSBABranch.getText().equalsIgnoreCase("All")
				&& cbSBAStation.getText().equalsIgnoreCase("Group")) {
			populateAllBranches(tblSundryBookAnalysis);
		} else if (cbSBABranch.getText().equalsIgnoreCase("All")
				&& cbSBAStation.getText().equalsIgnoreCase("All")) {
			populateAllStations(tblSundryBookAnalysis);
		}else if (!cbSBABranch.getText().equalsIgnoreCase("All")
				&& cbSBAStation.getText().equalsIgnoreCase("All")) {
			String branchCode = cbSBABranch.getText();
			int index = branchCode.indexOf(" - ");
			branchCode = branchCode.substring(index + 3);
			populateSelectedStations(branchCode, tblSundryBookAnalysis);
		} else if (!cbSBABranch.getText().equalsIgnoreCase("All")
				&& !cbSBAStation.getText().equalsIgnoreCase("All")) {
			 //populateSingleStation(cbSBAStation.getText(),tblSundryBookAnalysis);

			TableItem item = new TableItem(tblSundryBookAnalysis, SWT.NONE);
			item.setText(0, String.valueOf(1));
			item.setText(1, getBranch_code(cbSBAStation.getText()));
			item.setText(2, cbSBAStation.getText());
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
	
	private String[] getSundryHeading(Table refTable){
		
		String list[] = new String[4];
		
		list[0] = txtSBAFrom.getText();
		list[1] = txtSBATo.getText();
		String branchCode = cbSBABranch.getText();
		if (!branchCode.equalsIgnoreCase("All")) {
			int index = branchCode.indexOf(" - ");
			list[2] = branchCode.substring(index + 3);
		}
		else
			list[2] ="All";
		
		String stationCode = cbSBAStation.getText();
		if (!stationCode.equalsIgnoreCase("All")) {
			int index = stationCode.indexOf(" - ");
			list[3] = stationCode.substring(index + 3);
		}
		else
			list[3] ="All";
		
		return list;
	}
	
	@Override
	public void handleEvent(Event e) {
		// TODO Auto-generated method stub
		
		String selection = tabReport.getSelection()[0].getText();
		TableColumn column = (TableColumn) e.widget;
		
		if (selection == SUNDRY_BOOKING_TAB) {
			if (column == colSBASNo) {
				new sortListener().sortByFloat(0, tblSundryBookAnalysis);
			} else if (column == colBranchCode) {
				new sortListener().sortByString(1, tblSundryBookAnalysis);
			} else if (column == colStationCode) {
				new sortListener().sortByString(2, tblSundryBookAnalysis);
			} else if (column == colTotalLr) {
				new sortListener().sortByFloat(3, tblSundryBookAnalysis);
			} else if (column == colTotalSundryLrs) {
				new sortListener().sortByFloat(4, tblSundryBookAnalysis);
			} else if (column == colTotalLrFreight) {
				new sortListener().sortByFloat(5, tblSundryBookAnalysis);
			} else if (column == colTotalSundryFreight) {
				new sortListener().sortByFloat(6, tblSundryBookAnalysis);
			} else if (column == colTotalSundryBft) {
				new sortListener().sortByFloat(7, tblSundryBookAnalysis);
			} else if (column == colTotalSundryNoa) {
				new sortListener().sortByFloat(8, tblSundryBookAnalysis);
			} else if (column == colTotalSundryAwt) {
				new sortListener().sortByFloat(9, tblSundryBookAnalysis);
			} else if (column == colTotalSundryCrgWt) {
				new sortListener().sortByFloat(10, tblSundryBookAnalysis);
			} else if (column == colOnTurnOver) {
				new sortListener().sortByFloat(11, tblSundryBookAnalysis);
			}
		} 
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent event) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void widgetSelected(SelectionEvent event) {
		// TODO Auto-generated method stub
		Object source = event.getSource();
		/*if(source == tabReport){
			if (selectedTab.equalsIgnoreCase(SUNDRY_BOOKING_TAB)) {
				if(cbSBABranch.getItemCount() < 3){
					populateBranches(cbSBABranch);
				}
			}
		}else*/
		if (source == cbSBABranch) {
			if(cbSBABranch.getText().equalsIgnoreCase("All")){
			cbSBAStation.removeAll();
			cbSBAStation.add("Group");
			cbSBAStation.add("All");
		}else{
			String branch = cbSBABranch.getText();
			int index = branch.indexOf(" - ");
			branch = branch.substring(index + 3);
			// if (cbSBABranch.getSelectionIndex() != -1) {
			sundryBranchComboAction(branch, cbSBAStation);
			

		// }
		}
	} else if (source == cbSBAStation) {

	} else if (source == btnSBAGo) {
		new sortListener().display("Generating Report...", STATUS_SUCCESS,
				SUCCESS_FONT_COLOR,lblStatusBar);
		
		if (cbSBABranch.getSelectionIndex() == -1 && cbSBAStation
				.getSelectionIndex() == -1)
				/*|| (!cbSBABranch.getText().equalsIgnoreCase("All") && cbSBAStation
						.getSelectionIndex() == -1))*/ {
			// AdminComposite.display("Please select",
			// STATUS_SUCCESS,SUCCESS_FONT_COLOR);
		} else {
			if (handler != null) {
				try {
					GeneralSummaryDTO[] sundryBooking = null;
					DateFormat dateFormat = new SimpleDateFormat(
							"dd-MM-yyyy");
					Date fromDt = dateFormat.parse(txtSBAFrom.getText());
					Date toDate = dateFormat.parse(txtSBATo.getText());
					String branch = cbSBABranch.getText();
					if(branch.equalsIgnoreCase("All")){
						branch = "%";
					}
					else{
						int index = branch.indexOf(" - ");
						branch = branch.substring(index + 3);
					}
					
					String station = cbSBAStation.getText();
					if(station.equalsIgnoreCase("All")){
						station = "%";
					}else if(station.equalsIgnoreCase("Group")){
						station = "Group";
					}
					sundryBooking = getSundryBooking(fromDt, toDate, branch, station);
					
					if (sundryBooking != null) {
						designSundryTable();
						if ((cbSBABranch.getText().equalsIgnoreCase("All"))
										&& (cbSBAStation.getText().equalsIgnoreCase("Group"))) {
							// Only TRs
							populateSundryBooking(sundryBooking, 1);
						}else if ((cbSBABranch.getText().equalsIgnoreCase("All"))
								&& (cbSBAStation.getText().equalsIgnoreCase("All"))) {
							populateSundryBooking(sundryBooking);
						}
						else {
							populateSundryBooking(sundryBooking);
						}

					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		new sortListener().display("Report Loaded Successfully!",
				STATUS_SUCCESS, SUCCESS_FONT_COLOR,lblStatusBar);
	}else if(source == btnExportXL){
		//if (selectedTab.equalsIgnoreCase(SUNDRY_BOOKING_TAB)) {
		try{
			ArrayList<TableDecorator> list = null;
		
			String[] value = null;
			list = new sortListener().getBPATable(tblSundryBookAnalysis);
			value = getSundryHeading(tblSundryBookAnalysis);
			new sortListener().prepareExcel(list, "Sundry_Booking", SBA_EXCEL_JRXML, null,value,shell,lblStatusBar);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}else if (source == btnPrint) {
		try {
			//handlePrint();
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			list = new sortListener().getBPATable(tblSundryBookAnalysis);
			value = getSundryHeading(tblSundryBookAnalysis);
			handler.preparePrint(list, "Sundry_Booking", SBA_EXCEL_JRXML, null,value);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	else if (source == btnExportPDF) {
		try {
			//handlePDF();
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			list = new sortListener().getBPATable(tblSundryBookAnalysis);
			value = getSundryHeading(tblSundryBookAnalysis);
			new sortListener().preparePDF(list, "Sundry_Booking", SBA_EXCEL_JRXML, null,value,shell,lblStatusBar);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
		
		
	}
	
	
	
}
