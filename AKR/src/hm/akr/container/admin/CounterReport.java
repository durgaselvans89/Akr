package hm.akr.container.admin;


import hm.akr.common.sortListener;
import hm.akr.common.SWTResourceManager;
import hm.akr.common.BeanUtil;
import hm.akr.common.IUIConstants;
import hm.akr.common.KalendarDialog;
import hm.akr.container.history.HistoryHandler;
import hm.akr.dto.StationsDTO;
import hm.akr.dto.TableDecorator;
import hm.akr.dto.VersionDTO;

import java.text.DateFormat;
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

public class CounterReport extends ReportsMenu implements SelectionListener,Listener,IUIConstants{
	
	private static Label lblStatusBar;
	private String COUNTER_REPORT = "Counter Report";
	private TabFolder tabReport;
	private TabItem tiCounterReport;
	private Canvas cvsCounterReport;
	private Table tblCounterReport;
  	private TableColumn colCRsno;          
	private TableColumn colCRBranchCode;
	private TableColumn colCRStationCode;
	private TableColumn colCRC1;
	private TableColumn colCRC2;
	private TableColumn colCRC3;
	private TableColumn colCRC4;
	private TableColumn colCRC5;
	private TableColumn colCRC6;
	private TableColumn colCRC7;
	private TableColumn colCRC8;
	private Label label11;
	private Label label12;
	private Label label13;
	private Label lblTo;
	private Text txtCRFrom;
	private Button btnCRFromDate;
	private Text txtCRTo;
	private Button btnCRTo;
	private Combo cbCounterBranch;
	private Combo cbCRReportType;
	private Label lblCounterSet;
	private Combo cbCRCounterSet;
	private Button btnCounterGo;
	private static final String COUNTER_EXCEL_JRXML = "hm/akr/resources/printer/Counter.jrxml";
	private static final String COUNTER_MISC_EXCEL_JRXML = "hm/akr/resources/printer/Countermisc.jrxml";
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
	
	public CounterReport(Composite parent, int style, float clientRights) {
		super(parent, style);
		// TODO Auto-generated constructor stub
		try {
			this.clientRights = clientRights;
			currentStationName = beanutil.getActingStationName();
			currentStationCode = beanutil.getActingStationCode();
			shell = parent.getShell();
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
		tabReport.setBounds(50, 30, 820, 500);
		tabReport.addSelectionListener(this);
		{

			tiCounterReport = new TabItem(tabReport, SWT.NONE);
			tiCounterReport.setText(COUNTER_REPORT);

			cvsCounterReport = new Canvas(tabReport, SWT.NONE);
			tiCounterReport.setControl(cvsCounterReport);

			{

				{
					tblCounterReport = new Table(cvsCounterReport,
							SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL
									| SWT.BORDER);

					tblCounterReport.setHeaderVisible(true);
					tblCounterReport.setLinesVisible(true);
					tblCounterReport.setBounds(10, 100, 800, 360);
					{
						colCRsno = new TableColumn(tblCounterReport, SWT.NONE);
						colCRsno.setText("S NO");
						colCRsno.setWidth(40);

					}
					{
						colCRBranchCode = new TableColumn(tblCounterReport,
								SWT.NONE);
						colCRBranchCode.setText("Branch Code");
						colCRBranchCode.setWidth(80);

					}
					{
						colCRStationCode = new TableColumn(tblCounterReport,
								SWT.NONE);
						colCRStationCode.setText("Station Code");
						colCRStationCode.setWidth(100);
					}
					{
						colCRC1 = new TableColumn(tblCounterReport, SWT.NONE);
						colCRC1.setText("Total Lrs");
						colCRC1.setWidth(80);

					}
					{
						colCRC2 = new TableColumn(tblCounterReport, SWT.NONE);
						colCRC2.setText("Topay Lrs");
						colCRC2.setWidth(80);

					}
					{
						colCRC3 = new TableColumn(tblCounterReport, SWT.NONE);
						colCRC3.setText("Paid Lrs");
						colCRC3.setWidth(80);

					}
					{
						colCRC4 = new TableColumn(tblCounterReport, SWT.NONE);
						colCRC4.setText("Billing Lrs");
						colCRC4.setWidth(80);

					}
					{
						colCRC5 = new TableColumn(tblCounterReport, SWT.NONE);
						colCRC5.setText("Sundry Lrs");
						colCRC5.setWidth(90);

					}
					{
						colCRC6 = new TableColumn(tblCounterReport, SWT.NONE);
						colCRC6.setText("Quotation Lrs");
						colCRC6.setWidth(90);

					}
					{
						colCRC7 = new TableColumn(tblCounterReport, SWT.NONE);
						colCRC7.setText("FOC Lrs");
						colCRC7.setWidth(90);

					}
					{
						colCRC8 = new TableColumn(tblCounterReport, SWT.NONE);
						colCRC8.setText("Cancelled Lrs");
						colCRC8.setWidth(90);

					}

					for (int index = 0; index < tblCounterReport
							.getColumnCount(); index++) {
						tblCounterReport.getColumn(index).addListener(
								SWT.Selection, this);
					}

				}

				{
					label11 = new Label(cvsCounterReport, SWT.NONE);
					label11.setText("From Date");
					label11.setBounds(10, 37, 55, 18);
				}

				{
					txtCRFrom = new Text(cvsCounterReport, SWT.BORDER);
					txtCRFrom.setBounds(70, 34, 80, 22);
					txtCRFrom.setEditable(false);
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					txtCRFrom.setText(date);

				}
				{
					btnCRFromDate = new Button(cvsCounterReport, SWT.PUSH
							| SWT.CENTER);
					btnCRFromDate.setBounds(150, 34, 30, 23);
					btnCRFromDate.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btnCRFromDate.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent e) {
							KalendarDialog cd = new KalendarDialog(shell);
							Object obj = cd.open();
							if (obj != null) {
								String date = obj.toString();
								txtCRFrom.setText(date);
							}
						}
					});
				}
				{
					lblTo = new Label(cvsCounterReport, SWT.NONE);
					lblTo.setText("To Date");
					lblTo.setBounds(190, 35, 45, 17);
				}
				{
					txtCRTo = new Text(cvsCounterReport, SWT.BORDER);
					txtCRTo.setBounds(250, 35, 90, 22);
					txtCRTo.setEditable(false);
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					txtCRTo.setText(date);

				}
				{
					btnCRTo = new Button(cvsCounterReport, SWT.PUSH
							| SWT.CENTER);
					btnCRTo.setBounds(340, 35, 30, 23);
					btnCRTo.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btnCRTo.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent e) {
							KalendarDialog cd = new KalendarDialog(shell);
							Object obj = cd.open();
							if (obj != null) {
								String date = obj.toString();
								txtCRTo.setText(date);
							}
						}
					});

				}

				{
					label12 = new Label(cvsCounterReport, SWT.NONE);
					label12.setText("Select Branch");
					label12.setBounds(380, 34, 70, 20);
				}
				{
					cbCounterBranch = new Combo(cvsCounterReport, SWT.READ_ONLY);
					cbCounterBranch.setBounds(455, 32, 85, 21);

					if(clientRights == 1 || clientRights == 1.0){
						
						try{
						
							getCurrentBranch_code(currentStationCode);
							cbCounterBranch.select(0);
							//populateStationForCLR();
							cbCounterBranch.addSelectionListener(this);
						}
						catch(Exception e){
							e.printStackTrace();
						}
						
						
					}
					else{
					
						try{
							StationsDTO[] dto = handler.getAllBranches();
							if (null != dto) {
								cbCounterBranch.add("All");
								for (int i = 0; i < dto.length; i++) {
									cbCounterBranch.add(dto[i].getName() + " - "
											+ dto[i].getId());
								}
							}
							cbCounterBranch.addSelectionListener(this);
						}
						catch(Exception e){
							e.printStackTrace();
						}
					}
					
					
				}
				{
					label13 = new Label(cvsCounterReport, SWT.NONE);
					label13.setText("Report Type");
					label13.setBounds(550, 34, 65, 19);
				}
				{
					cbCRReportType = new Combo(cvsCounterReport, SWT.READ_ONLY);
					cbCRReportType.setBounds(627, 34, 97, 21);
					if(clientRights == 1 || clientRights == 1.0){
						cbCRReportType.add("Detailed");
						cbCRReportType.select(0);
						
					}else{
						cbCRReportType.add("Consolidated");
						cbCRReportType.add("Detailed");
					}
				}
				{
					lblCounterSet = new Label(cvsCounterReport, SWT.NONE);
					lblCounterSet.setText("Counter Set");
					lblCounterSet.setBounds(550, 72, 65, 20);
				}
				{
					cbCRCounterSet = new Combo(cvsCounterReport, SWT.READ_ONLY);
					cbCRCounterSet.setBounds(626, 72, 98, 21);
					cbCRCounterSet.add("LR Set");
					cbCRCounterSet.add("Misc");
					cbCRCounterSet.select(0);
					cbCRCounterSet.addSelectionListener(this);
				}
				{
					btnCounterGo = new Button(cvsCounterReport, SWT.PUSH
							| SWT.CENTER);
					btnCounterGo.setText("View");
					btnCounterGo.setBounds(736, 47, 41, 30);
					btnCounterGo.addSelectionListener(this);
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
					
						cbCounterBranch.add(stations[i].getName()+" - "+branch_code);
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

	
	
	
	private void getCounterReport(Date from_date, Date to_date,
			String branch_code) {
		try {
			if (null != handler) {

				StationsDTO[] station = handler.getAllStations();
				int len = station.length;

				StationsDTO[] newStation = new StationsDTO[len];
				for (int i = 0; i < len; i++) {
					newStation[i] = new StationsDTO();
					newStation[i].setId(station[i].getId());
					newStation[i].setBranch_code(station[i].getBranch_code());

				}
				StationsDTO[] dto = getCounterReport(from_date,
						to_date, branch_code, newStation);

				if (tblCounterReport != null)
					tblCounterReport.removeAll();

				if (dto != null) {
					populateCounterReport(dto, branch_code);
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

	}
	
	private void getCounterReportMisc(Date from_date, Date to_date,
			String branch_code) {
		// TODO Auto-generated method stub
		try {
			if (null != handler) {

				StationsDTO[] station = handler.getAllStations();
				int len = station.length;

				StationsDTO[] newStation = new StationsDTO[len];
				for (int i = 0; i < len; i++) {
					newStation[i] = new StationsDTO();
					newStation[i].setId(station[i].getId());
					newStation[i].setBranch_code(station[i].getBranch_code());

				}
				StationsDTO[] dto = getCounterReportMisc(from_date,
						to_date, branch_code, newStation);

				if (tblCounterReport != null)
					tblCounterReport.removeAll();

				if (dto != null) {
					populateCounterReportMisc(dto, branch_code);
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		
	}

	private StationsDTO[] getCounterReportMisc(Date from_date, Date to_date,
			String branch_code, StationsDTO[] newStation) {
		// TODO Auto-generated method stub
		
		//int monthDiff = 0;
		StationsDTO[] dto = null;
		try {
				/*Date curDate = new Date();
				int m1 = from_date.getYear() * 12 + from_date.getMonth();
		        int m2 = curDate.getYear() * 12 + curDate.getMonth();
		        monthDiff = m2 - m1;*/
				Date date = BeanUtil.getThree_month_updated();
			if(to_date.before(date)){
				
				dto = handler.getCounterReportMiscHistory(from_date, to_date, branch_code,newStation);
			}else if(from_date.after(date)){				
				dto = handler.getCounterReportMisc(from_date, to_date, branch_code,newStation);
			}else{
				dto = handler.getCounterReportMiscUnion(from_date, to_date, branch_code,newStation);
			}
		}
		catch (Exception e) {
				e.printStackTrace();
		}
		return dto;
	}

	private StationsDTO[] getCounterReport(Date from_date, Date to_date,
			String branch_code, StationsDTO[] newStation) {
	
		//int monthDiff = 0;
		StationsDTO[] dto = null;
		try {
			/*Date curDate = new Date();
			int m1 = from_date.getYear() * 12 + from_date.getMonth();
	        int m2 = curDate.getYear() * 12 + curDate.getMonth();
	        monthDiff = m2 - m1;*/
			Date date = BeanUtil.getThree_month_updated();
		if(to_date.before(date)){
			//System.out.println("in ddr histry-->"+date);
			dto = handler.getCounterReportHistory(from_date, to_date, branch_code,newStation);
		}else if(from_date.after(date)){
			//System.out.println("in ddr histry-->"+date);
			dto = handler.getCounterReport(from_date, to_date, branch_code,newStation);
		}else{
			dto = handler.getCounterReportUnion(from_date, to_date, branch_code,newStation);
		}
	} catch (Exception e) {
			e.printStackTrace();
	}
		return dto;
	}

	private void populateCounterReport(StationsDTO[] dto, String branch)
			throws Exception {
		int len = dto.length;
		
		for (int i = 0, j = 1; i < len; i++) {
			if (cbCounterBranch.getText().equalsIgnoreCase("All")
					&& cbCRReportType.getText().startsWith("C")) {
				if (dto[i].getId().equalsIgnoreCase(dto[i].getBranch_code())) {
					TableItem item = new TableItem(tblCounterReport, SWT.NONE);

					item.setText(0, String.valueOf(j));
					item.setText(1, dto[i].getBranch_code());
					item.setText(2, dto[i].getId());
					item.setText(3, String.valueOf(dto[i].getCc_limit()
							+ dto[i].getCc_consider() + dto[i].getCc_refund()));
					item.setText(4, String.valueOf(dto[i].getCc_limit()));
					item.setText(5, String.valueOf(dto[i].getCc_consider()));
					item.setText(6, String.valueOf(dto[i].getCc_refund()));
					item.setText(7, String.valueOf(dto[i].getCc_special()));
					item.setText(8, String.valueOf(dto[i].getCc_commodity()));
					item.setText(9, String.valueOf(dto[i].getBf_increment()));
					item.setText(10, String.valueOf(dto[i].getDiscount()));

					j++;
				}
			} else if (!cbCounterBranch.getText().equalsIgnoreCase("All")) {

				if (branch.equalsIgnoreCase(dto[i].getBranch_code())) {
					TableItem item = new TableItem(tblCounterReport, SWT.NONE);
					item.setText(0, String.valueOf(j));
					item.setText(1, dto[i].getBranch_code());
					item.setText(2, dto[i].getId());
					item.setText(3, String.valueOf(dto[i].getCc_limit()
							+ dto[i].getCc_consider() + dto[i].getCc_refund()));
					item.setText(4, String.valueOf(dto[i].getCc_limit()));
					item.setText(5, String.valueOf(dto[i].getCc_consider()));
					item.setText(6, String.valueOf(dto[i].getCc_refund()));
					item.setText(7, String.valueOf(dto[i].getCc_special()));
					item.setText(8, String.valueOf(dto[i].getCc_commodity()));
					item.setText(9, String.valueOf(dto[i].getBf_increment()));
					item.setText(10, String.valueOf(dto[i].getDiscount()));
					j++;
				}
			} else {
				TableItem item = new TableItem(tblCounterReport, SWT.NONE);
				item.setText(0, String.valueOf(j));
				item.setText(1, dto[i].getBranch_code());
				item.setText(2, dto[i].getId());
				item.setText(3, String.valueOf(dto[i].getCc_limit()
						+ dto[i].getCc_consider() + dto[i].getCc_refund()));
				item.setText(4, String.valueOf(dto[i].getCc_limit()));
				item.setText(5, String.valueOf(dto[i].getCc_consider()));
				item.setText(6, String.valueOf(dto[i].getCc_refund()));
				item.setText(7, String.valueOf(dto[i].getCc_special()));
				item.setText(8, String.valueOf(dto[i].getCc_commodity()));
				item.setText(9, String.valueOf(dto[i].getBf_increment()));
				item.setText(10, String.valueOf(dto[i].getDiscount()));

				j++;
			}

		}

		calcCounterTotal();

	}

	private void calcCounterTotal() {
		TableItem[] items = tblCounterReport.getItems();
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
				if (!items[i].getText(3).equals(""))
					tot3 = tot3 + Integer.parseInt(items[i].getText(3));
				if (!items[i].getText(4).equals(""))
					tot4 = tot4 + Integer.parseInt(items[i].getText(4));
				if (!items[i].getText(5).equals(""))
					tot5 = tot5 + Integer.parseInt(items[i].getText(5));
				if (!items[i].getText(6).equals(""))
					tot6 = tot6 + Integer.parseInt(items[i].getText(6));
				if (!items[i].getText(7).equals(""))
					tot7 = tot7 + Integer.parseInt(items[i].getText(7));
				if (!items[i].getText(8).equals(""))
					tot8 = tot8 + Integer.parseInt(items[i].getText(8));
				if (!items[i].getText(9).equals(""))
					tot9 = tot9 + Integer.parseInt(items[i].getText(9));
				if (!items[i].getText(10).equals(""))
					tot10 = tot10 + Integer.parseInt(items[i].getText(10));
			}

		}

		final TableItem item1 = new TableItem(tblCounterReport, SWT.NONE);
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
	
	
	
	
	private void populateCounterReportMisc(StationsDTO[] dto, String branch)
	throws Exception {
		int len = dto.length;
		
		for (int i = 0, j = 1; i < len; i++) {
			if (cbCounterBranch.getText().equalsIgnoreCase("All")
					&& cbCRReportType.getText().startsWith("C")) {
				if (dto[i].getId().equalsIgnoreCase(dto[i].getBranch_code())) {
					TableItem item = new TableItem(tblCounterReport, SWT.NONE);
		
					item.setText(0, String.valueOf(j));
					item.setText(1, dto[i].getBranch_code());
					item.setText(2, dto[i].getId());
					item.setText(3, String.valueOf(dto[i].getBf_increment()));
					item.setText(4, String.valueOf(dto[i].getBpi()));
					item.setText(5, String.valueOf(dto[i].getCc_hlc()));
					item.setText(6, String.valueOf(dto[i].getCc_limit()));
					item.setText(7, String.valueOf(dto[i].getCc_refund()));
					item.setText(8, String.valueOf(dto[i].getCc_special()));
					item.setText(9, String.valueOf(dto[i].getDiscount()));
					item.setText(10, String.valueOf("0"));
		
					j++;
				}
			} else if (!cbCounterBranch.getText().equalsIgnoreCase("All")) {
		
				if (branch.equalsIgnoreCase(dto[i].getBranch_code())) {
					TableItem item = new TableItem(tblCounterReport, SWT.NONE);
					item.setText(0, String.valueOf(j));
					item.setText(1, dto[i].getBranch_code());
					item.setText(2, dto[i].getId());
					item.setText(3, String.valueOf(dto[i].getBf_increment()));
					item.setText(4, String.valueOf(dto[i].getBpi()));
					item.setText(5, String.valueOf(dto[i].getCc_hlc()));
					item.setText(6, String.valueOf(dto[i].getCc_limit()));
					item.setText(7, String.valueOf(dto[i].getCc_refund()));
					item.setText(8, String.valueOf(dto[i].getCc_special()));
					item.setText(9, String.valueOf(dto[i].getDiscount()));
					item.setText(10, String.valueOf("0"));
					j++;
				}
			} else {
				TableItem item = new TableItem(tblCounterReport, SWT.NONE);
				item.setText(0, String.valueOf(j));
				item.setText(1, dto[i].getBranch_code());
				item.setText(2, dto[i].getId());
				item.setText(3, String.valueOf(dto[i].getBf_increment()));
				item.setText(4, String.valueOf(dto[i].getBpi()));
				item.setText(5, String.valueOf(dto[i].getCc_hlc()));
				item.setText(6, String.valueOf(dto[i].getCc_limit()));
				item.setText(7, String.valueOf(dto[i].getCc_refund()));
				item.setText(8, String.valueOf(dto[i].getCc_special()));
				item.setText(9, String.valueOf(dto[i].getDiscount()));
				item.setText(10, String.valueOf("0"));
		
				j++;
			}
		
		}
		
		calcCounterMiscTotal();
		
	}

	

	private void calcCounterMiscTotal() {
		// TODO Auto-generated method stub
	TableItem[] items = tblCounterReport.getItems();
			int tot3 = 0;
			float tot4 = 0;
			float tot5 = 0;
			int tot6 = 0;
			int tot7 = 0;
			int tot8 = 0;
			int tot9 = 0;
			int tot10 = 0;

			if (items != null) {
				for (int i = 0; i < items.length; i++) {
					if (!items[i].getText(3).equals(""))
						tot3 = tot3 + Integer.parseInt(items[i].getText(3));
					if (!items[i].getText(4).equals(""))
						tot4 = tot4 + Float.parseFloat(items[i].getText(4));
					if (!items[i].getText(5).equals(""))
						tot5 = tot5 + Float.parseFloat(items[i].getText(5));
					if (!items[i].getText(6).equals(""))
						tot6 = tot6 + Integer.parseInt(items[i].getText(6));
					if (!items[i].getText(7).equals(""))
						tot7 = tot7 + Integer.parseInt(items[i].getText(7));
					if (!items[i].getText(8).equals(""))
						tot8 = tot8 + Integer.parseInt(items[i].getText(8));
					if (!items[i].getText(9).equals(""))
						tot9 = tot9 + Integer.parseInt(items[i].getText(9));
					if (!items[i].getText(10).equals(""))
						tot10 = tot10 + Integer.parseInt(items[i].getText(10));
				}

			}

			final TableItem item1 = new TableItem(tblCounterReport, SWT.NONE);
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
	
	private String[] getCounterHeading(Table refTable){
		
		String list[] = new String[5];
		
		list[0] =txtCRFrom.getText();
		list[1] =txtCRTo.getText();
		String branchCode = cbCounterBranch.getText();
		if (!branchCode.equalsIgnoreCase("All")) {
			int index = branchCode.indexOf(" - ");
			list[2] = branchCode.substring(index + 3);
		}
		else
			list[2] ="All";
		if(cbCRReportType.getText().equals("Consolidated"))
			list[3] = "Consolidated";
		else
			list[3] = "Detailed";
		if(cbCRCounterSet.getText().equals("LR Set"))
			list[4] = "LR Set";
		else
			list[4] = "MISC";
		
			
		return list;
	}
	
	
	
	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void widgetSelected(SelectionEvent event) {
		// TODO Auto-generated method stub
		Object source = event.getSource();
		/*if(source == tabReport){
			//String selectedTab = tabReport.getSelection()[0].getText();
			//if (selectedTab.equalsIgnoreCase(COUNTER_REPORT)) {
				if(cbCounterBranch.getItemCount() < 3){
					new sortListener().populateBranches(cbCounterBranch);
				//}
			} 
		}else*/ if (btnCounterGo == source) {
			new sortListener().display("Generating Report...", STATUS_SUCCESS,SUCCESS_FONT_COLOR,lblStatusBar);

			try {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Date from_date = dateFormat.parse(txtCRFrom.getText());
				Date to_date = dateFormat.parse(txtCRTo.getText());

				int index = -1;

				index = cbCounterBranch.getSelectionIndex();
				
				if (index > -1) {
					
						String branch_code = cbCounterBranch.getText();
						// System.out.println(branch_code);

						if (branch_code.equalsIgnoreCase("All")) {
							if (cbCRReportType.getText().equalsIgnoreCase(
									"Detailed")) {
								branch_code = "AllD";
							} else {
								branch_code = "AllC";
							}

						} else {
							index = branch_code.indexOf(" - ");
							branch_code = branch_code.substring(index + 3);
						}
						if(cbCRCounterSet.getText().equalsIgnoreCase("LR Set")){
							getCounterReport(from_date, to_date, branch_code);
						}
						else
						{	
							getCounterReportMisc(from_date, to_date, branch_code);
						}
					}
				
				

			} catch (Exception e) {
				e.printStackTrace();
			}
			new sortListener().display("Report Loaded Successfully!",STATUS_SUCCESS, SUCCESS_FONT_COLOR,lblStatusBar);
		} else if (cbCRCounterSet == source) {
			int index = cbCRCounterSet.getSelectionIndex();
			if (index > -1) {
				if (index == 0) {
					colCRC1.setText("Total Lrs");
					colCRC2.setText("Topay Lrs");
					colCRC3.setText("Paid Lrs");
					colCRC4.setText("Billing Lrs");
					colCRC5.setText("Sundry Lrs");
					colCRC6.setText("Quotation Lrs");
					colCRC7.setText("FOC Lrs");
					colCRC8.setText("Cancelled Lrs");

				} else if (index == 1) {
				  
				  colCRC1.setText("NOA");
				  colCRC2.setText("Actual weight");
				  colCRC3.setText("Charged Weight");
				  colCRC4.setText("Door Delivery");
				  colCRC5.setText("Office Delivery");
				  colCRC6.setText("Inwarded LRs");
				  colCRC7.setText("Total CRs");
				  colCRC8.setText("Station Compliants");
				 
				}
			}

		}else if (source == cbCounterBranch) {
			if (!cbCounterBranch.getText().equalsIgnoreCase("All")) {
				cbCRReportType.select(1);
				cbCRReportType.setEnabled(false);
			} else {
				cbCRReportType.select(0);
				cbCRReportType.setEnabled(true);
			}
		}else if (source == btnExportXL) {
			try {
				//handleExcel();
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				list = new sortListener().getBPATable(tblCounterReport);
				value = getCounterHeading(tblCounterReport);
				if (cbCRCounterSet.getText().equalsIgnoreCase("LR Set")) {
					
					new sortListener().prepareExcel(list, "CounterReport", COUNTER_EXCEL_JRXML, null,value,shell,lblStatusBar);
				} else {
					
					new sortListener().prepareExcel(list, "CounterReport", COUNTER_MISC_EXCEL_JRXML, null,value,shell,lblStatusBar);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else if (source == btnPrint) {
			try {
				//handlePrint();
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				list = new sortListener().getBPATable(tblCounterReport);
				value = getCounterHeading(tblCounterReport);
				if (cbCRCounterSet.getText().equalsIgnoreCase("LR Set")) {
					
					handler.preparePrint(list, "CounterReport", COUNTER_EXCEL_JRXML, null,value);
				} else {
					
					handler.preparePrint(list, "CounterReport", COUNTER_MISC_EXCEL_JRXML, null,value);
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
				list = new sortListener().getBPATable(tblCounterReport);
				value = getCounterHeading(tblCounterReport);
				if (cbCRCounterSet.getText().equalsIgnoreCase("LR Set")) {
					
					new sortListener().preparePDF(list, "CounterReport", COUNTER_EXCEL_JRXML, null,value,shell,lblStatusBar);
				} else {
					
					new sortListener().preparePDF(list, "CounterReport", COUNTER_MISC_EXCEL_JRXML, null,value,shell,lblStatusBar);
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

		if (selection == COUNTER_REPORT) {
			if (column == colCRsno) {
				new sortListener().sortByFloat(0, tblCounterReport);
			} else if (column == colCRBranchCode) {
				new sortListener().sortByString(1, tblCounterReport);
			} else if (column == colCRStationCode) {
				new sortListener().sortByString(2, tblCounterReport);
			} else if (column == colCRC1) {
				new sortListener().sortByFloat(3, tblCounterReport);
			} else if (column == colCRC2) {
				new sortListener().sortByFloat(4, tblCounterReport);
			} else if (column == colCRC3) {
				new sortListener().sortByFloat(5, tblCounterReport);
			} else if (column == colCRC4) {
				new sortListener().sortByFloat(6, tblCounterReport);
			} else if (column == colCRC5) {
				new sortListener().sortByFloat(7, tblCounterReport);
			} else if (column == colCRC6) {
				new sortListener().sortByFloat(8, tblCounterReport);
			} else if (column == colCRC7) {
				new sortListener().sortByFloat(9, tblCounterReport);
			} else if (column == colCRC8) {
				new sortListener().sortByFloat(10, tblCounterReport);
			}
		}
		
	}
	

	
}
