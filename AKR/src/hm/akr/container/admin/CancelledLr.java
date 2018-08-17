package hm.akr.container.admin;

import hm.akr.common.SWTResourceManager;
import hm.akr.container.history.HistoryHandler;
//import hm.akr.admin.reports.AdminCompositeHandler;
//import hm.akr.admin.reports.AdminReports.sortListener;
//import hm.akr.admin.workspace.AdminComposite;
import hm.akr.container.admin.ReportsMenuHandler;
import hm.akr.common.BeanUtil;
import hm.akr.common.IUIConstants;
import hm.akr.common.KalendarDialog;
import hm.akr.dto.BookingDTO;
import hm.akr.dto.StationsDTO;
import hm.akr.dto.TableDecorator;
import hm.akr.dto.VersionDTO;
import hm.akr.common.sortListener;


import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
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


public class CancelledLr extends ReportsMenu implements SelectionListener,Listener,IUIConstants{
	
	
	private static Label lblStatusBar;
	
	private String CANCELLEDLR = "Cancelled LRs";
	private TabFolder tabReport;
	private TabItem tiCancelledLRs;
	private Canvas cvsCancelledLRs;
	private Label label3;
	private Label label4;
	private Label label5;
	private Label label6;
	private Label lblCLRBranch;
	private Combo cbStation;
	private Combo cbCLReportType;
	private Button btnCancelledLrGo;
	private Text txtCancelledLRfromdate;
	private Button btncancelledlrfromdate;
	private Button btncancelledlrtodate;
	private Text txtcancelledlrtodate;
	private Table tblCancelledLR;
	private TableColumn colCLsno;
	private TableColumn colCLBranchCode;
	private TableColumn colCLStationCode;
	private TableColumn colCLNoOfCancelLR;
	private TableColumn colCLTotalFreight;
	private TableColumn colCLAvgFreight;
	private TableColumn colCancelledDetailedSno;
	private TableColumn colCancelledDetailedLrno;
	private TableColumn colCancelledDetailedLrdate;
	private TableColumn colCancelledDetailedLrtype;
	private TableColumn colCancelledDetailedFrom;
	private TableColumn colCancelledDetailedTo;
	private TableColumn colCancelledDetailedNoa;
	private TableColumn colCancelledDetailedArtValue;
	private TableColumn colCancelledDetailedBft;
	private TableColumn colCancelledDetailedCC;
	private TableColumn colCancelledDetailedIec;
	private TableColumn colCancelledDetailedOthers;
	private TableColumn colCancelledDetailedDD;
	private TableColumn colCancelledDetailedTotal;
	private Combo cbCLRBranch;
	BookingDTO[] cancelledSummaryLRS = null;
	BookingDTO[] cancelledDetailedLRS = null;
	VersionDTO[] vDto = null;
	private Shell shell = null;
	DateFormat dateFormat = null;
	private Button btnPrint;
	private Button btnExportXL;
	private Button btnExportPDF;
	private ReportsMenuHandler handler;
	HistoryHandler historyH = null;
	
	private float clientRights = -1;
	Composite cptCancelledlr;
	private String currentStationName = "";
	private String currentStationCode = "";

	private static final String CANCELLEDLRS_EXCEL_JRXML = "hm/akr/resources/printer/Cancelled.jrxml";
	
	public CancelledLr(Composite composite,int style, float clientRights){
	
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
		
		this.setBounds(45,100, 1020,700);
		tabReport = new TabFolder(this, SWT.NONE);
		tabReport.setBounds(50, 25, 820, 500);
		tabReport.addSelectionListener(this);
		
		

		{
			
			tiCancelledLRs = new TabItem(tabReport,SWT.NONE);
			tiCancelledLRs.setText(CANCELLEDLR);

			cvsCancelledLRs = new Canvas(tabReport, SWT.NONE);
			tiCancelledLRs.setControl(cvsCancelledLRs);
			

			{

				tblCancelledLR = getSummaryCancelledLRTable();

				{
					label3 = new Label(cvsCancelledLRs, SWT.NONE);
					label3.setText("Station");
					label3.setBounds(546, 58, 35, 19);
					// label3.setVisible(false);
					//label3.setEnabled(false);
				}
				{
					lblCLRBranch = new Label(cvsCancelledLRs, SWT.NONE);
					lblCLRBranch.setText("Branch");
					lblCLRBranch.setBounds(540, 23, 35, 19);
				}
				{
					label4 = new Label(cvsCancelledLRs, SWT.NONE);
					label4.setText("Report Type");
					label4.setBounds(365, 22, 68, 17);
				}
				{
					label5 = new Label(cvsCancelledLRs, SWT.NONE);
					label5.setText("To Date");
					label5.setBounds(185, 28, 41, 20);
				}
				{
					label6 = new Label(cvsCancelledLRs, SWT.NONE);
					label6.setText("From Date");
					label6.setBounds(11, 28, 56, 19);
				}
				
				
				{
					cbStation = new Combo(cvsCancelledLRs, SWT.READ_ONLY);
					cbStation.setBounds(590, 53, 122, 23);
					cbStation.addSelectionListener(this);
					if(clientRights == 0 || clientRights == 0.0){
						cbStation.add(currentStationName+" - "+currentStationCode);
						cbStation.select(0);

					}
					// cbStation.setVisible(false);
					//cbStation.setEnabled(false);

				}
				
				{
					cbCLRBranch = new Combo(cvsCancelledLRs,  SWT.READ_ONLY);
					cbCLRBranch.setBounds(590, 20, 123, 23);
					if(clientRights == 0 || clientRights == 0.0){
						
						try{
							
							getCurrentBranch_code(currentStationCode);
							cbCLRBranch.select(0);
						}
						catch(Exception e){
							e.printStackTrace();
						}
						//cbStation
						//cbStation.add();
						
					}else if(clientRights == 1 || clientRights == 1.0){
						
						try{
						
							getCurrentBranch_code(currentStationCode);
							cbCLRBranch.select(0);
							populateStationForCLR();
							cbCLRBranch.addSelectionListener(this);
						}
						catch(Exception e){
							e.printStackTrace();
						}
						
						
					}
					else{
						try{
							StationsDTO[] dto = handler.getAllBranches();
							if (null != dto) {
								cbCLRBranch.add("All");
								for (int i = 0; i < dto.length; i++) {
									
									cbCLRBranch.add(dto[i].getName() + " - "
											+ dto[i].getId());
								}
							}
							cbCLRBranch.addSelectionListener(this);
						}
						catch(Exception e){
							e.printStackTrace();
						}
					}
				}
				
				{
					cbCLReportType = new Combo(cvsCancelledLRs, SWT.READ_ONLY);
					cbCLReportType.setBounds(434, 20, 94, 23);
					if(clientRights == 0 || clientRights == 0.0){
						cbCLReportType.add("Detailed");
						cbCLReportType.select(0);
						getDetailedCancelledLRTable();
					}
					else{
						cbCLReportType.add("Summary");
						cbCLReportType.add("Detailed");
						cbCLReportType.select(1);
						getDetailedCancelledLRTable();
						cbCLReportType.addSelectionListener(this);

					}
				}
				{
					btnCancelledLrGo = new Button(cvsCancelledLRs, SWT.NONE);
					btnCancelledLrGo.setText("View");
					btnCancelledLrGo.setBounds(734, 32, 41, 32);
					btnCancelledLrGo.addSelectionListener(this);
				}
				{
					txtCancelledLRfromdate = new Text(cvsCancelledLRs,
							SWT.BORDER);
					txtCancelledLRfromdate.setBounds(70, 27, 72, 24);
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					txtCancelledLRfromdate.setText(date);
					txtCancelledLRfromdate.setEditable(false);
				}
				{
					btncancelledlrfromdate = new Button(cvsCancelledLRs,
							SWT.PUSH);
					// btncancelledlrfromdate.setText("Go");
					btncancelledlrfromdate.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btncancelledlrfromdate.setBounds(142, 27, 26, 23);
					btncancelledlrfromdate.addSelectionListener(this);
				}
				{
					txtcancelledlrtodate = new Text(cvsCancelledLRs, SWT.BORDER);
					txtcancelledlrtodate.setBounds(242, 25, 71, 24);
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					txtcancelledlrtodate.setText(date);
					txtcancelledlrtodate.setEditable(false);
				}
				{
					btncancelledlrtodate = new Button(cvsCancelledLRs, SWT.PUSH);
					// btncancelledlrtodate.setText("Go");
					btncancelledlrtodate.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btncancelledlrtodate.setBounds(313, 25, 26, 23);
					btncancelledlrtodate.addSelectionListener(this);
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
			/*FormData lblStatusBarLData = new FormData();
			lblStatusBarLData.width = 500;
			lblStatusBarLData.height = 32;
			lblStatusBarLData.left = new FormAttachment(0, 1000, 200);
			lblStatusBarLData.top = new FormAttachment(0, 1000, 755);
			lblStatusBar.setLayoutData(lblStatusBarLData);*/
			lblStatusBar.setBounds(60,570 ,300,25);
			lblStatusBar.setFont(STATUS_SUCCESS);
			lblStatusBar.setText("STATUS");
			lblStatusBar.setForeground(LINK_FOCUS_COLOR);
			lblStatusBar.setVisible(true);
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
		
		return this;
		
	}
	


	
	private Table getSummaryCancelledLRTable() {

		if (tblCancelledLR != null) {
			tblCancelledLR.dispose();
		}
		tblCancelledLR = new Table(cvsCancelledLRs, SWT.FULL_SELECTION
				| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);

		tblCancelledLR.setHeaderVisible(true);
		tblCancelledLR.setLinesVisible(true);
		tblCancelledLR.setBounds(10, 90, 670, 370);
		
		{
			colCLsno = new TableColumn(tblCancelledLR, SWT.NONE);
			colCLsno.setText("S NO");
			colCLsno.setWidth(40);

		}
		{
			colCLBranchCode = new TableColumn(tblCancelledLR, SWT.NONE);
			colCLBranchCode.setText("Branch Code");
			colCLBranchCode.setWidth(110);

		}
		{
			colCLStationCode = new TableColumn(tblCancelledLR, SWT.NONE);
			colCLStationCode.setText("Station Code");
			colCLStationCode.setWidth(110);

		}
		{
			colCLNoOfCancelLR = new TableColumn(tblCancelledLR, SWT.NONE);
			colCLNoOfCancelLR.setText("No Of Cancelled LRs");
			colCLNoOfCancelLR.setWidth(130);

		}
		{
			colCLTotalFreight = new TableColumn(tblCancelledLR, SWT.NONE);
			colCLTotalFreight.setText("Total Freight");
			colCLTotalFreight.setWidth(130);
			
		}

		{
			colCLAvgFreight = new TableColumn(tblCancelledLR, SWT.NONE);
			colCLAvgFreight.setText("Average Freight");
			colCLAvgFreight.setWidth(120);

		}

		for (int index = 0; index < tblCancelledLR.getColumnCount(); index++) {
			tblCancelledLR.getColumn(index).addListener(SWT.Selection, this);
		}

		return tblCancelledLR;

	}
	
	private Table getDetailedCancelledLRTable() {

		if (tblCancelledLR != null) {
			tblCancelledLR.dispose();
		}
		tblCancelledLR = new Table(cvsCancelledLRs, SWT.FULL_SELECTION
				| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);

		tblCancelledLR.setHeaderVisible(true);
		tblCancelledLR.setLinesVisible(true);
		tblCancelledLR.setBounds(17, 92, 782, 368);

		{
			colCancelledDetailedSno = new TableColumn(tblCancelledLR, SWT.NONE);
			colCancelledDetailedSno.setText("Sno");
			colCancelledDetailedSno.setWidth(60);
		}
		{
			colCancelledDetailedLrno = new TableColumn(tblCancelledLR, SWT.NONE);
			colCancelledDetailedLrno.setText("Lrno");
			colCancelledDetailedLrno.setWidth(60);
		}
		{
			colCancelledDetailedLrdate = new TableColumn(tblCancelledLR,
					SWT.NONE);
			colCancelledDetailedLrdate.setText("Lrdate");
			colCancelledDetailedLrdate.setWidth(60);
		}
		{
			colCancelledDetailedLrtype = new TableColumn(tblCancelledLR,
					SWT.NONE);
			colCancelledDetailedLrtype.setText("LrType");
			colCancelledDetailedLrtype.setWidth(60);
		}
		{
			colCancelledDetailedFrom = new TableColumn(tblCancelledLR, SWT.NONE);
			colCancelledDetailedFrom.setText("From");
			colCancelledDetailedFrom.setWidth(60);
		}
		{
			colCancelledDetailedTo = new TableColumn(tblCancelledLR, SWT.NONE);
			colCancelledDetailedTo.setText("To");
			colCancelledDetailedTo.setWidth(60);
		}
		{
			colCancelledDetailedNoa = new TableColumn(tblCancelledLR, SWT.NONE);
			colCancelledDetailedNoa.setText("Noa");
			colCancelledDetailedNoa.setWidth(60);
		}
		{
			colCancelledDetailedArtValue = new TableColumn(tblCancelledLR,
					SWT.NONE);
			colCancelledDetailedArtValue.setText("ArtValue");
			colCancelledDetailedArtValue.setWidth(60);
		}
		{
			colCancelledDetailedBft = new TableColumn(tblCancelledLR, SWT.NONE);
			colCancelledDetailedBft.setText("Bft");
			colCancelledDetailedBft.setWidth(60);
		}
		{
			colCancelledDetailedCC = new TableColumn(tblCancelledLR, SWT.NONE);
			colCancelledDetailedCC.setText("CC");
			colCancelledDetailedCC.setWidth(60);
		}
		{
			colCancelledDetailedIec = new TableColumn(tblCancelledLR, SWT.NONE);
			colCancelledDetailedIec.setText("Iec");
			colCancelledDetailedIec.setWidth(60);
		}
		{
			colCancelledDetailedOthers = new TableColumn(tblCancelledLR,
					SWT.NONE);
			colCancelledDetailedOthers.setText("Others");
			colCancelledDetailedOthers.setWidth(60);
		}
		{
			colCancelledDetailedDD = new TableColumn(tblCancelledLR, SWT.NONE);
			colCancelledDetailedDD.setText("DD");
			colCancelledDetailedDD.setWidth(60);
		}
		{
			colCancelledDetailedTotal = new TableColumn(tblCancelledLR,
					SWT.NONE);
			colCancelledDetailedTotal.setText("Total");
			colCancelledDetailedTotal.setWidth(60);
		}

		for (int index = 0; index < tblCancelledLR.getColumnCount(); index++) {
			tblCancelledLR.getColumn(index).addListener(SWT.Selection,this);
		}
		return tblCancelledLR;

	}
	
	private void handleCancelLR(boolean refresh) {

		if (null != tblCancelledLR)
			tblCancelledLR.removeAll();

		try {
			int index = -1;

			index = cbCLReportType.getSelectionIndex();
			String selected = cbCLReportType.getItem(index);
			String Branch_code = null;
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date from_date = dateFormat.parse(txtCancelledLRfromdate.getText());
			Date to_date = dateFormat.parse(txtcancelledlrtodate.getText());
			if (selected.equalsIgnoreCase("Detailed")) {
				index = cbCLRBranch.getSelectionIndex();
				if (index > -1) {
					
					if (index != 0) {
						Branch_code = cbCLRBranch.getItem(index);
						index = Branch_code.indexOf("-");
						Branch_code = Branch_code.substring(index + 1).trim();
					} else {
						Branch_code = cbCLRBranch.getItem(index);
						if(Branch_code.equalsIgnoreCase("All")){
							Branch_code = "All";
						}else{
							index = Branch_code.indexOf("-");
							Branch_code = Branch_code.substring(index + 1).trim();
						}
						
					}
				
				}
				index = cbStation.getSelectionIndex();
				if (index > -1) {
					String station_code = null;
					if (index != 0) {
						station_code = cbStation.getItem(index);
						index = station_code.indexOf("-");
						station_code = station_code.substring(index + 1).trim();
					} else {
						station_code = cbStation.getItem(index);
						if(station_code.equalsIgnoreCase("All")){
							station_code = "All";
						}else{
							index = station_code.indexOf("-");
							station_code = station_code.substring(index + 1).trim();
						}
						
					}
				
					populateDetailedCancelledLRTable(from_date, to_date,
							Branch_code,station_code, selected, refresh);
				}

			} else {
				index = cbCLRBranch.getSelectionIndex();
				if (index > -1) {
					String branch_code = null;
					if (index != 0) {
						branch_code = cbCLRBranch.getItem(index);
						index = branch_code.indexOf("-");
						branch_code = branch_code.substring(index + 1).trim();
					} else {
						
						branch_code = cbCLRBranch.getItem(index);
						if(branch_code.equalsIgnoreCase("All")){
							branch_code = "All";
						}else{
							index = branch_code.indexOf("-");
							branch_code = branch_code.substring(index + 1).trim();
						}
						
					}
					populateSummaryCancelledLRTable(from_date, to_date,
							branch_code, selected, refresh);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	private void populateDetailedCancelledLRTable(Date from_date, Date to_date,String Branch_code,
			String station_code, String type, boolean refresh) {
		try {
			
			if (null != handler) {
				if (refresh)
					cancelledDetailedLRS = getCancelledLR(from_date,
							to_date, type,Branch_code,station_code);

				if (null != cancelledDetailedLRS) {
					boolean isAvail = false;
					int len = cancelledDetailedLRS.length;
					int j = 1;

					/* Total */
					int noa = 0;
					float art_value = 0;
					float bft = 0;
					float cc = 0;
					float iec = 0;
					float others = 0;
					float dd = 0;
					float total = 0;
					
					for (int i = 0; i < len; i++) {
						
						if ((Branch_code.equalsIgnoreCase("All"))&&(station_code.equalsIgnoreCase("All"))){
							isAvail = true;
							TableItem item = new TableItem(tblCancelledLR,
									SWT.NONE);
							item.setText(0, String.valueOf(j++));
							item
									.setText(1, String
											.valueOf(cancelledDetailedLRS[i]
													.getLrno()));
							item
									.setText(2, String
											.valueOf(cancelledDetailedLRS[i]
													.getDate()));
							item
									.setText(3, String
											.valueOf(cancelledDetailedLRS[i]
													.getType()));
							item
									.setText(4, String
											.valueOf(cancelledDetailedLRS[i]
													.getFrom()));
							item.setText(5, String
									.valueOf(cancelledDetailedLRS[i].getTo()));
							item.setText(6, String
									.valueOf(cancelledDetailedLRS[i]
											.getNo_of_articles()));
							noa = noa
									+ cancelledDetailedLRS[i]
											.getNo_of_articles();
							item.setText(7, getRoundedValue(cancelledDetailedLRS[i]
											.getArticle_value()));
							art_value = art_value
									+ cancelledDetailedLRS[i]
											.getArticle_value();
							item.setText(8, getRoundedValue(cancelledDetailedLRS[i].getBft()));
							bft = bft + cancelledDetailedLRS[i].getBft();
							item.setText(9, getRoundedValue(cancelledDetailedLRS[i].getCcc()));
							cc = cc + cancelledDetailedLRS[i].getCcc();
							item.setText(10, getRoundedValue(cancelledDetailedLRS[i].getIec()));
							iec = iec + cancelledDetailedLRS[i].getIec();
							item.setText(11, getRoundedValue(cancelledDetailedLRS[i]
											.getOther_charges()));
							others = others
									+ cancelledDetailedLRS[i]
											.getOther_charges();
							item.setText(12, getRoundedValue(cancelledDetailedLRS[i].getDdc()));
							dd = dd + cancelledDetailedLRS[i].getDdc();
							item.setText(13,
									getRoundedValue(cancelledDetailedLRS[i]
											.getTotal()));
							total = total + cancelledDetailedLRS[i].getTotal();
						}else if((Branch_code.equalsIgnoreCase(getBranch_code(cancelledDetailedLRS[i].getFrom())))&&(station_code.equalsIgnoreCase("All"))){
							isAvail = true;
							TableItem item = new TableItem(tblCancelledLR,
									SWT.NONE);
							item.setText(0, String.valueOf(j++));
							item
									.setText(1, String
											.valueOf(cancelledDetailedLRS[i]
													.getLrno()));
							item
									.setText(2, String
											.valueOf(cancelledDetailedLRS[i]
													.getDate()));
							item
									.setText(3, String
											.valueOf(cancelledDetailedLRS[i]
													.getType()));
							item
									.setText(4, String
											.valueOf(cancelledDetailedLRS[i]
													.getFrom()));
							item.setText(5, String
									.valueOf(cancelledDetailedLRS[i].getTo()));
							item.setText(6, String
									.valueOf(cancelledDetailedLRS[i]
											.getNo_of_articles()));
							noa = noa
									+ cancelledDetailedLRS[i]
											.getNo_of_articles();
							item.setText(7, getRoundedValue(cancelledDetailedLRS[i]
											.getArticle_value()));
							art_value = art_value
									+ cancelledDetailedLRS[i]
											.getArticle_value();
							item.setText(8, getRoundedValue(cancelledDetailedLRS[i].getBft()));
							bft = bft + cancelledDetailedLRS[i].getBft();
							item.setText(9, getRoundedValue(cancelledDetailedLRS[i].getCcc()));
							cc = cc + cancelledDetailedLRS[i].getCcc();
							item.setText(10, getRoundedValue(cancelledDetailedLRS[i].getIec()));
							iec = iec + cancelledDetailedLRS[i].getIec();
							item.setText(11, getRoundedValue(cancelledDetailedLRS[i]
											.getOther_charges()));
							others = others
									+ cancelledDetailedLRS[i]
											.getOther_charges();
							item.setText(12, getRoundedValue(cancelledDetailedLRS[i].getDdc()));
							dd = dd + cancelledDetailedLRS[i].getDdc();
							item.setText(13,
									getRoundedValue(cancelledDetailedLRS[i]
											.getTotal()));
							total = total + cancelledDetailedLRS[i].getTotal();
						}
						else if ((station_code.equalsIgnoreCase(cancelledDetailedLRS[i].getFrom())) && (!Branch_code.equals(" "))){
							isAvail = true;
							TableItem item = new TableItem(tblCancelledLR,
									SWT.NONE);
							item.setText(0, String.valueOf(j++));
							item
									.setText(1, String
											.valueOf(cancelledDetailedLRS[i]
													.getLrno()));
							item
									.setText(2, String
											.valueOf(cancelledDetailedLRS[i]
													.getDate()));
							item
									.setText(3, String
											.valueOf(cancelledDetailedLRS[i]
													.getType()));
							item
									.setText(4, String
											.valueOf(cancelledDetailedLRS[i]
													.getFrom()));
							item.setText(5, String
									.valueOf(cancelledDetailedLRS[i].getTo()));
							item.setText(6, String
									.valueOf(cancelledDetailedLRS[i]
											.getNo_of_articles()));
							noa = noa
									+ cancelledDetailedLRS[i]
											.getNo_of_articles();
							item.setText(7, getRoundedValue(cancelledDetailedLRS[i]
											.getArticle_value()));
							art_value = art_value
									+ cancelledDetailedLRS[i]
											.getArticle_value();
							item.setText(8, getRoundedValue(cancelledDetailedLRS[i].getBft()));
							bft = bft + cancelledDetailedLRS[i].getBft();
							item.setText(9, getRoundedValue(cancelledDetailedLRS[i].getCcc()));
							cc = cc + cancelledDetailedLRS[i].getCcc();
							item.setText(10, getRoundedValue(cancelledDetailedLRS[i].getIec()));
							iec = iec + cancelledDetailedLRS[i].getIec();
							item.setText(11, getRoundedValue(cancelledDetailedLRS[i]
											.getOther_charges()));
							others = others
									+ cancelledDetailedLRS[i]
											.getOther_charges();
							item.setText(12, getRoundedValue(cancelledDetailedLRS[i].getDdc()));
							dd = dd + cancelledDetailedLRS[i].getDdc();
							item.setText(13,
									getRoundedValue(cancelledDetailedLRS[i]
											.getTotal()));
							total = total + cancelledDetailedLRS[i].getTotal();
						}

					}
					TableItem item = new TableItem(tblCancelledLR, SWT.NONE);
					Font font1 = new Font(Display.getDefault(), "Tahoma", 8, SWT.BOLD);
					item.setFont(font1);
					item.setText(1, String.valueOf("Total"));
					item.setText(6, String.valueOf(noa));
					item.setText(7, getRoundedValue(art_value));
					item.setText(8, getRoundedValue(bft));
					item.setText(9, getRoundedValue(cc));
					item.setText(10, getRoundedValue(iec));
					item.setText(11, getRoundedValue(others));
					item.setText(12, getRoundedValue(dd));
					item.setText(13, getRoundedValue(total));

					if (!isAvail) {
						// displayError("No Records Available");
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	private BookingDTO[] getCancelledLR(Date from_date, Date to_date,
			String type, String branch_code, String station_code) {
		
		//int monthDiff = 0;
		 //cancelledDetailedLRS = null;
		try {
			/*Date curDate = new Date();
			int m1 = from_date.getYear() * 12 + from_date.getMonth();
	        int m2 = curDate.getYear() * 12 + curDate.getMonth();
	        monthDiff = m2 - m1;*/
			Date date = BeanUtil.getThree_month_updated();
		if(to_date.before(date) ){
			//System.out.println("in ddr histry-->"+date);
			cancelledDetailedLRS = handler.getCancelledLRHistory(from_date, to_date,type,branch_code,station_code);
		}else if(from_date.after(date)){
			cancelledDetailedLRS = handler.getCancelledLR(from_date, to_date,type,branch_code,station_code);
		}else{
			cancelledDetailedLRS = handler.getCancelledLRUnion(from_date, to_date,type,branch_code,station_code);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cancelledDetailedLRS;
	}
	
	private BookingDTO[] getCancelledLRsummary(Date from_date, Date to_date,
			String type, String branch_code) {
		
		//int monthDiff = 0;
		
		try {
			/*Date curDate = new Date();
			int m1 = from_date.getYear() * 12 + from_date.getMonth();
	        int m2 = curDate.getYear() * 12 + curDate.getMonth();
	        monthDiff = m2 - m1;*/
			Date date = BeanUtil.getThree_month_updated();
		if(from_date.before(date)){
			//System.out.println("in ddr histry-->"+date);
			cancelledSummaryLRS = handler.getCancelledLRHistory(from_date, to_date,type,branch_code,"%");
		}else{
			cancelledSummaryLRS = handler.getCancelledLR(from_date, to_date,type,branch_code,"%");
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cancelledSummaryLRS;
	}

	/**
	 * 
	 * @param from_date
	 * @param to_date
	 * @param branch_code
	 * @param type
	 * @param refresh
	 */
	private void populateSummaryCancelledLRTable(Date from_date, Date to_date,
			String branch_code, String type, boolean refresh) {
		try {
			
			float average =0;
			int count=0;
			
			if (null != handler) {
				populateDefaultTable(branch_code, tblCancelledLR, 6);
				if (refresh)
					cancelledSummaryLRS = getCancelledLRsummary(from_date,
							to_date, type,branch_code);

				if (null != cancelledSummaryLRS) {
					int len = cancelledSummaryLRS.length;

					int nolr = 0;
					float total = 0;
					float avgftTotal = 0;
					float avg = 0;
					for (int i = 0; i < len; i++) {
						String from = cancelledSummaryLRS[i].getFrom();
						if (null != branch_code
								&& (branch_code.equalsIgnoreCase("All") || branch_code
										.equalsIgnoreCase(getBranchCode(from)))) {

							TableItem item = getTableItem(tblCancelledLR, 2,from);
							if (item != null) {
								
								int noa = cancelledSummaryLRS[i].getNo_of_articles();
								float tot = cancelledSummaryLRS[i].getBft();
								
								item.setText(3, String.valueOf(noa));
								nolr = nolr	+ noa;
								
								item.setText(4, getRoundedValue(tot));
								total = total + tot;
								
								avgftTotal = tot / noa;
								item.setText(5,String.valueOf(getRoundedValue(avgftTotal)));
								avg = avg + avgftTotal;
								
							}
						}
						//count++;
					}
					
					average = avg / nolr;
					TableItem item = new TableItem(tblCancelledLR, SWT.NONE);
					item.setText(1, "Total");
					Font font1 = new Font(Display.getDefault(), "Tahoma", 8, SWT.BOLD);
					item.setFont(font1);
					item.setText(3, String.valueOf(nolr));
					item.setText(4, getRoundedValue(total));
					item.setText(5, getRoundedValue(avg));
					TableItem item1 = new TableItem(tblCancelledLR, SWT.NONE);
					item1.setFont(font1);
					item1.setText(4, "Average");
					item1.setText(5, getRoundedValue(average));

				}
			}
		} catch (Exception e) {

		}
	}

	private void populateStationForCLR() throws Exception {
		int index = cbCLRBranch.getSelectionIndex();
		String branch_code = null;
		if (index > -1) {
			
			branch_code = cbCLRBranch.getItem(index);
			index = branch_code.indexOf("-");
			branch_code = branch_code.substring(index + 1).trim();
			
			StationsDTO[] dto = handler.getAllStations();
			if (null != dto) {
				if (null != cbStation)
					cbStation.removeAll();
					cbStation.add("All");
				if(!(branch_code.equals("All"))){
					
					for (int i = 0; i < dto.length; i++) {
						if (branch_code == null
								|| dto[i].getBranch_code().equalsIgnoreCase(
										branch_code)) 
							cbStation
									.add(dto[i].getName() + " - " + dto[i].getId());
					}
				}
			}

		}

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
					
						cbCLRBranch.add(stations[i].getName()+" - "+branch_code);
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
	
	private String getRoundedValue(float cardRate) {
		DecimalFormat decimalFormat;
		decimalFormat = new DecimalFormat("0.00");
		cardRate = cardRate * 100;
		cardRate = (float) Math.round(cardRate);
		cardRate = cardRate / 100;

		return decimalFormat.format(cardRate);
	}
	
	private String getBranchCode(String station_code) throws Exception {
		StationsDTO[] dto = handler.getAllStations();
		if (null != dto) {
			for (int i = 0; i < dto.length; i++) {
				if (dto[i].getId().equalsIgnoreCase(station_code)) {
					return dto[i].getBranch_code();
				}
			}
		}
		return null;
	}
	
	private TableItem getTableItem(Table table, int index, String value) {
		if (null != table) {
			TableItem[] items = table.getItems();
			int item = items.length;
			for (int i = 0; i < item; i++) {
				if (items[i].getText(index).trim().equalsIgnoreCase(value))
					return items[i];
			}
		}
		return null;
	}
	
	private void populateDefaultTable(String branch, Table tblName, int cols)
	throws Exception {
		StationsDTO[] dto = null;
		if (null != tblCancelledLR) {
		
			if (branch.equalsIgnoreCase("All") || branch.equalsIgnoreCase("%"))
				dto = handler.getAllStations();
			else
				dto = handler.getStations(branch);
			if (null != dto) {
				int len = dto.length;
		
				for (int i = 0; i < len; i++) {
					TableItem item = new TableItem(tblName, SWT.NONE);
					item.setText(0, String.valueOf(i + 1));
					item.setText(1, dto[i].getBranch_code());
					item.setText(2, dto[i].getId());
					for (int j = 3; j < cols; j++)
						item.setText(j, String.valueOf(0));
		
				}
			}
		
		} 
		
	}
	
	private String[] getCancelledHeading(Table refTable){
		
		String list[] = new String[5];
		
		list[0] =txtCancelledLRfromdate.getText();
		list[1] =txtcancelledlrtodate.getText();
		list[2] =cbCLReportType.getText();
		String branchCode = cbCLRBranch.getText();
		if (!branchCode.equalsIgnoreCase("All")) {
			int index = branchCode.indexOf(" - ");
			list[3] = branchCode.substring(index + 3);
		}
		else
			list[3] ="All";
		
		String stationCode = cbStation.getText();
		
		if(!(list[2].equalsIgnoreCase("Summary"))){
			if (!stationCode.equalsIgnoreCase("All")) {
				int index = stationCode.indexOf(" - ");
				list[4] = stationCode.substring(index + 3);
			}
			else
				list[4] ="All";
		}
		else
			list[4] = " ";
		
		
			
		return list;
	}
	
	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void widgetSelected(SelectionEvent event) {
		// TODO Auto-generated method stub
		//display("STATUS", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
		Object source = event.getSource();
		
		/*if(source == tabReport){
			String selectedTab = tabReport.getSelection()[0].getText();
			if (selectedTab.equalsIgnoreCase(COUNTER_REPORT)) {
				if(.getItemCount() < 3){
					new sortListener().populateBranches(cbCounterBranch);
				}
			} 
		}else */if (source == cbCLReportType) {
			int index = cbCLReportType.getSelectionIndex();
			String selected = cbCLReportType.getItem(index);
			if (selected.equalsIgnoreCase("Detailed")) {
				// cbStation.setVisible(true);
				cbStation.setEnabled(true);
				
				// label3.setVisible(true);
				label3.setEnabled(true);
				getDetailedCancelledLRTable();
			} else if (selected.equalsIgnoreCase("Summary")) {
				// cbStation.setVisible(false);
				cbStation.setEnabled(false);
				// label3.setVisible(false);
				label3.setEnabled(false);
				getSummaryCancelledLRTable();
			}
		} else if (source == btncancelledlrtodate) {
			KalendarDialog cd = new KalendarDialog(shell);
			Object obj = cd.open();
			if (obj != null) {
				txtcancelledlrtodate.setText(obj.toString());
			}

		}

		else if (source == btncancelledlrfromdate) {
			KalendarDialog cd = new KalendarDialog(shell);
			Object obj = cd.open();
			if (obj != null) {
				txtCancelledLRfromdate.setText(obj.toString());
			}

		} else if (source == btnCancelledLrGo) {
			new sortListener().display("Generating Report...", STATUS_SUCCESS,SUCCESS_FONT_COLOR,lblStatusBar);

			handleCancelLR(true);
			new sortListener().display("Report Loaded Successfully!",STATUS_SUCCESS, SUCCESS_FONT_COLOR,lblStatusBar);
		} else if (source == cbCLRBranch) {
			try {
					populateStationForCLR();
					//handleCancelLR(false);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (source == cbStation) {
			try {
				//handleCancelLR(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (source == btnExportXL) {
			try {
				//handleExcel();
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				if (cbCLReportType.getText().equalsIgnoreCase("Detailed")) {
					list = new sortListener().getBPATable(tblCancelledLR);
					value = getCancelledHeading(tblCancelledLR);
					TableColumn[] col = tblCancelledLR.getColumns();	
					if(col != null){
						int len = col.length;
						String param[] = new String[len];
						for(int i = 0; i < len; i++){
							param[i] = col[i].getText();
						}
						new sortListener().prepareExcel(list, "Cancelled LRs", CANCELLEDLRS_EXCEL_JRXML, param,value,shell,lblStatusBar);
					}
				}
				else
				{
					list = new sortListener().getBPATable(tblCancelledLR);
					value = getCancelledHeading(tblCancelledLR);
					TableColumn[] col = tblCancelledLR.getColumns();
					if(col != null){
						int len = col.length;
						String param[] = new String[len];
						for(int i = 0; i < len; i++){
							param[i] = col[i].getText();
							System.out.println("param--"+param[i]);
						}
						new sortListener().prepareExcel(list, "Cancelled LRs", CANCELLEDLRS_EXCEL_JRXML, param,value,shell,lblStatusBar);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else if (source == btnPrint) {
			try {
				//handlePrint();
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				if (cbCLReportType.getText().equalsIgnoreCase("Detailed")) {
					list = new sortListener().getBPATable(tblCancelledLR);
					value = getCancelledHeading(tblCancelledLR);
					TableColumn[] col = tblCancelledLR.getColumns();	
					if(col != null){
						int len = col.length;
						String param[] = new String[len];
						for(int i = 0; i < len; i++){
							param[i] = col[i].getText();
						}
						handler.preparePrint(list, "Cancelled LRs", CANCELLEDLRS_EXCEL_JRXML, param,value);
					}
				}
				else
				{
					list = new sortListener().getBPATable(tblCancelledLR);
					value = getCancelledHeading(tblCancelledLR);
					TableColumn[] col = tblCancelledLR.getColumns();
					if(col != null){
						int len = col.length;
						String param[] = new String[len];
						for(int i = 0; i < len; i++){
							param[i] = col[i].getText();
						}
						handler.preparePrint(list, "Cancelled LRs", CANCELLEDLRS_EXCEL_JRXML, param,value);
					}
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
				if (cbCLReportType.getText().equalsIgnoreCase("Detailed")) {
					list = new sortListener().getBPATable(tblCancelledLR);
					value = getCancelledHeading(tblCancelledLR);
					TableColumn[] col = tblCancelledLR.getColumns();	
					if(col != null){
						int len = col.length;
						String param[] = new String[len];
						for(int i = 0; i < len; i++){
							param[i] = col[i].getText();
						}
						new sortListener().preparePDF(list, "Cancelled LRs", CANCELLEDLRS_EXCEL_JRXML, param,value,shell,lblStatusBar);
					}
				}
				else
				{
					list = new sortListener().getBPATable(tblCancelledLR);
					value = getCancelledHeading(tblCancelledLR);
					TableColumn[] col = tblCancelledLR.getColumns();
					if(col != null){
						int len = col.length;
						String param[] = new String[len];
						for(int i = 0; i < len; i++){
							param[i] = col[i].getText();
						}
						new sortListener().preparePDF(list, "Cancelled LRs", CANCELLEDLRS_EXCEL_JRXML, param,value,shell,lblStatusBar);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
	@Override
	public void handleEvent(Event e) {
		// TODO Auto-generated method stub
		String selection = tabReport.getSelection()[0].getText();
		TableColumn column = (TableColumn) e.widget;
		 if (selection == CANCELLEDLR) {
			TableColumn[] cols = tblCancelledLR.getColumns();
			int len = cols.length;
			int index = cbCLReportType.getSelectionIndex();
			for (int i = 0; i < len; i++) {
				if (column == cols[i] && index == 0) {
					if (i == 2 || i == 1) {
						new sortListener().sortByString(i, tblCancelledLR);
					} else if (i > 2 || i == 0) {
						new sortListener().sortByFloat(i, tblCancelledLR);
					}
				} else if (column == cols[i] && index == 1) {
					if (i == 3 || i == 4 || i == 5) {
						new sortListener().sortByString(i, tblCancelledLR);
					} else if (i > 5 || i == 0) {
						new sortListener().sortByFloat(i, tblCancelledLR);
					} else if (i == 1) {
						new sortListener().sortByLrNo(i, tblCancelledLR);
					} else if (i == 2) {
						new sortListener().sortByDate(i, tblCancelledLR);
					}
				}
			}

		}
		
	}
}
