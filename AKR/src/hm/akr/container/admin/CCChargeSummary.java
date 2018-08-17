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

public class CCChargeSummary extends ReportsMenu implements IUIConstants,Listener,SelectionListener {

	
	private TabItem tiCCSummary;
	private Canvas cvsCCCSummary;
	private Table tblCCCSummary;
	private TableColumn colCCSsno;
	private TableColumn colCCSBranchCode;
	private TableColumn colCCSStationCode;
	private TableColumn colCCSTotalPT;
	private TableColumn colCCLRs;
	private TableColumn colCCSBFTLr;
	private TableColumn colCCSTotalCCC;
	private TableColumn colCCbftofTot;
	private TableColumn colCCperofCConTot;
	private TableColumn colCCCOnBasFrt;
	private Combo cbCCCBranch;
	private Label label7;
	private Label lblFromDate;
	private Label lblToDate;

	private Text txtCCCFrom;
	private Button btnCCCFromDate;
	private Text txtCCCTo;
	private Button btnCCCTo;
	private Button btnCCCGo;
	private String CCC_SUMMARY_TAB = "CCC Summary";
	private static final String CCSUMMARY_EXCEL_JRXML = "hm/akr/resources/printer/CCSummary.jrxml";
	
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
	//private BeanUtil beanutil = null;
	
	 public CCChargeSummary(Composite composite,int style, float clientRights) {
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

			tiCCSummary = new TabItem(tabReport, SWT.NONE);
			tiCCSummary.setText(CCC_SUMMARY_TAB);

			cvsCCCSummary = new Canvas(tabReport, SWT.NONE);
			tiCCSummary.setControl(cvsCCCSummary);

			{

				{
					tblCCCSummary = new Table(cvsCCCSummary, SWT.FULL_SELECTION
							| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);

					tblCCCSummary.setHeaderVisible(true);
					tblCCCSummary.setLinesVisible(true);
					tblCCCSummary.setBounds(10, 72, 800, 400);
					{
						colCCSsno = new TableColumn(tblCCCSummary, SWT.NONE);
						colCCSsno.setText("S NO");
						colCCSsno.setWidth(40);

					}
					{
						colCCSBranchCode = new TableColumn(tblCCCSummary,
								SWT.NONE);
						colCCSBranchCode.setText("Branch Code");
						colCCSBranchCode.setWidth(80);

					}
					{
						colCCSStationCode = new TableColumn(tblCCCSummary,
								SWT.NONE);
						colCCSStationCode.setText("Station Code");
						colCCSStationCode.setWidth(80);

					}
					{
						colCCSTotalPT = new TableColumn(tblCCCSummary, SWT.NONE);
						colCCSTotalPT.setText("Total PD/TP Lrs");
						colCCSTotalPT.setWidth(100);

					}
					{
						colCCLRs = new TableColumn(tblCCCSummary, SWT.NONE);
						colCCLRs.setText("Total CC LRs");
						colCCLRs.setWidth(100);

					}

					{
						colCCbftofTot = new TableColumn(tblCCCSummary, SWT.NONE);
						colCCbftofTot.setText("BasFrt of Total LRs");
						colCCbftofTot.setWidth(100);

					}

					{
						colCCSBFTLr = new TableColumn(tblCCCSummary, SWT.NONE);
						colCCSBFTLr.setText("Bas.Frt.of CC Lrs");
						colCCSBFTLr.setWidth(100);

					}

					{
						colCCSTotalCCC = new TableColumn(tblCCCSummary,
								SWT.NONE);
						colCCSTotalCCC.setText("Total CC Charge");
						colCCSTotalCCC.setWidth(100);

					}

					{
						colCCperofCConTot = new TableColumn(tblCCCSummary,
								SWT.NONE);
						colCCperofCConTot.setText("%of CC on Total LRs");
						colCCperofCConTot.setWidth(100);

					}

					{
						colCCCOnBasFrt = new TableColumn(tblCCCSummary,
								SWT.NONE);
						colCCCOnBasFrt.setText("% of CC on CC LRs");
						colCCCOnBasFrt.setWidth(120);

					}

					for (int index = 0; index < tblCCCSummary.getColumnCount(); index++) {
						tblCCCSummary.getColumn(index).addListener(
								SWT.Selection, this);
					}

				}
				{
					cbCCCBranch = new Combo(cvsCCCSummary, SWT.READ_ONLY);
					cbCCCBranch.setBounds(485, 27, 121, 21);
					//cbCCCBranch.add("All");
					//cbCCCBranch.addSelectionListener(this);
					
					if(clientRights == 1 || clientRights == 1.0){
						
						try{
						
							getCurrentBranch_code(currentStationCode);
							cbCCCBranch.select(0);
							//populateStationForCLR();
							cbCCCBranch.addSelectionListener(this);
						}
						catch(Exception e){
							e.printStackTrace();
						}
						
						
					}
					else{
							try{
							StationsDTO[] dto = handler.getAllBranches();
							if (null != dto) {
								cbCCCBranch.add("All");
								for (int i = 0; i < dto.length; i++) {
									cbCCCBranch.add(dto[i].getName() + " - "
											+ dto[i].getId());
								}
							}
							cbCCCBranch.addSelectionListener(this);
						}
						catch(Exception e){
							e.printStackTrace();
						}
					}
				}
				{
					label7 = new Label(cvsCCCSummary, SWT.NONE);
					label7.setText("Branch");
					label7.setBounds(446, 28, 37, 15);
				}
				{
					lblFromDate = new Label(cvsCCCSummary, SWT.NONE);
					lblFromDate.setText("From Date");
					lblFromDate.setBounds(47, 28, 54, 20);
				}
				{
					lblToDate = new Label(cvsCCCSummary, SWT.NONE);
					lblToDate.setText("To Date");
					lblToDate.setBounds(250, 28, 43, 16);
				}

				{
					txtCCCFrom = new Text(cvsCCCSummary, SWT.BORDER);
					txtCCCFrom.setBounds(110, 26, 94, 22);
					txtCCCFrom.setEditable(false);
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					txtCCCFrom.setText(date);

				}
				{
					btnCCCFromDate = new Button(cvsCCCSummary, SWT.PUSH
							| SWT.CENTER);
					btnCCCFromDate.setBounds(204, 25, 31, 23);
					btnCCCFromDate.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btnCCCFromDate.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent e) {
							KalendarDialog cd = new KalendarDialog(shell);
							Object obj = cd.open();
							if (obj != null) {
								String date = obj.toString();
								txtCCCFrom.setText(date);
							}
						}
					});
				}

				{
					txtCCCTo = new Text(cvsCCCSummary, SWT.BORDER);
					txtCCCTo.setBounds(298, 25, 92, 22);
					txtCCCTo.setEditable(false);
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					txtCCCTo.setText(date);

				}
				{
					btnCCCTo = new Button(cvsCCCSummary, SWT.PUSH | SWT.CENTER);
					btnCCCTo.setBounds(389, 23, 32, 23);
					btnCCCTo.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btnCCCTo.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent e) {
							KalendarDialog cd = new KalendarDialog(shell);
							Object obj = cd.open();
							if (obj != null) {
								String date = obj.toString();
								txtCCCTo.setText(date);
							}
						}
					});
				}
				{
					btnCCCGo = new Button(cvsCCCSummary, SWT.PUSH | SWT.CENTER);
					btnCCCGo.setText("Go");
					btnCCCGo.setBounds(611, 27, 37, 22);
					btnCCCGo.addSelectionListener(this);
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
					
						cbCCCBranch.add(stations[i].getName()+" - "+branch_code);
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

	
	
	private void handleCCC() {

		if (cbCCCBranch.getSelectionIndex() != -1) {
			String branch = "";

			if (handler != null) {
				try {
					branch = cbCCCBranch.getText();
					int index = branch.indexOf(" - ");
					branch = branch.substring(index + 3);

					GeneralSummaryDTO[] cccSummary = null;
					GeneralSummaryDTO[] cccSummaryPDTP = null;
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Date fromDt = dateFormat.parse(txtCCCFrom.getText());
					Date toDate = dateFormat.parse(txtCCCTo.getText());

					if (!cbCCCBranch.getText().equalsIgnoreCase("All")) {
						
						populateSelectedStations(branch, tblCCCSummary);
						
						 cccSummary = getCCChargeSummary(fromDt, toDate, branch);
						
						 cccSummaryPDTP = getCCCSummaryPDTP(fromDt, toDate, branch);
						
						
					} else {
						
						populateAllStations(tblCCCSummary);
						
						cccSummary = getCCChargeSummary(fromDt, toDate,	"%");
						
						cccSummaryPDTP = getCCCSummaryPDTP(fromDt,toDate, "%");
					}

									
					// System.out.println("summ-->"+cccSummary+"pddt-->"+cccSummaryPDTP);
					if (cccSummary != null) {
						populateCCCSummary(cccSummary);
					}

					if (cccSummaryPDTP != null) {
						populateCCCSummaryPDTP(cccSummaryPDTP);
					}

					calcCCSummaryTotal();
					new sortListener().fillZerosOnEmpty(tblCCCSummary);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
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
	
	private void populateCCCSummary(GeneralSummaryDTO[] cccSummary) {
		TableItem[] items = tblCCCSummary.getItems();

		for (int i = 0; i < cccSummary.length; i++) {
			for (int j = 0; j < items.length; j++) {
				if (cccSummary[i].getStation_code().equalsIgnoreCase(
						items[j].getText(2))) {
					items[j].setText(4, String
							.valueOf(cccSummary[i].getLr_no()));
					items[j]
							.setText(6, getRoundedValue(cccSummary[i].getBft()));
					items[j]
							.setText(7, getRoundedValue(cccSummary[i].getCcc()));
					if (cccSummary[i].getBft() != 0) {
						items[j].setText(9, getRoundedValue(cccSummary[i]
								.getCcc()
								/ cccSummary[i].getBft() * 100));
					}
				}
			}
		}

	}
	
	private String getRoundedValue(float cardRate) {
		DecimalFormat decimalFormat;
		decimalFormat = new DecimalFormat("0.00");
		cardRate = cardRate * 100;
		cardRate = (float) Math.round(cardRate);
		cardRate = cardRate / 100;

		return decimalFormat.format(cardRate);
	}
	
	private void populateCCCSummaryPDTP(GeneralSummaryDTO[] cccSummaryPDTP) {
		TableItem[] items = tblCCCSummary.getItems();

		for (int i = 0; i < cccSummaryPDTP.length; i++) {
			for (int j = 0; j < items.length; j++) {
				if (cccSummaryPDTP[i].getStation_code().equalsIgnoreCase(
						items[j].getText(2))) {
					items[j].setText(3, String.valueOf(cccSummaryPDTP[i]
							.getNo_of_articles()));
					items[j].setText(5, getRoundedValue(cccSummaryPDTP[i]
							.getBft()));
					items[j].setText(8, getRoundedValue(cccSummaryPDTP[i]
							.getCcc()
							/ cccSummaryPDTP[i].getBft() * 100));
				}
			}
		}		

	}

	private void calcCCSummaryTotal() {
		TableItem[] items = tblCCCSummary.getItems();
		int tot3 = 0;
		int tot4 = 0;
		float tot5 = 0;
		float tot6 = 0;
		float tot7 = 0;
		float tot8 = 0;
		float tot9 = 0;
		float tot8average=0;
		float tot9average=0;
		int count=0;
		
		if (items != null) {
			for (int i = 0; i < items.length; i++) {
				if (!items[i].getText(3).equals(""))
					tot3 = tot3 + Integer.parseInt(items[i].getText(3));
				if (!items[i].getText(4).equals(""))
					tot4 = tot4 + Integer.parseInt(items[i].getText(4));
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
				count++;
			}

		}
		
		tot8average = tot8 / count;
		tot9average = tot9 / count;
		final TableItem item1 = new TableItem(tblCCCSummary, SWT.NONE);
		Font font1 = new Font(Display.getCurrent(), "Tahoma", 8, SWT.BOLD);
		item1.setFont(font1);
		item1.setText(2, "TOTAL");
		item1.setText(3, String.valueOf(tot3));
		item1.setText(4, String.valueOf(tot4));
		item1.setText(5, getRoundedValue(tot5));
		item1.setText(6, getRoundedValue(tot6));
		item1.setText(7, getRoundedValue(tot7));
		item1.setText(8, getRoundedValue(tot8));
		item1.setText(9, getRoundedValue(tot9));
		final TableItem item2 = new TableItem(tblCCCSummary, SWT.NONE);
		item2.setFont(font1);
		item2.setText(7, "Average");
		item2.setText(8, getRoundedValue(tot8average));
		item2.setText(9, getRoundedValue(tot9average));
	}

	
	private GeneralSummaryDTO[] getCCCSummaryPDTP(Date fromDt, Date toDate,
			String branch) {
		
		//int monthDiff = 0;
		GeneralSummaryDTO[] cccSummaryPDTP = null;

		try {
			/*Date curDate = new Date();
			int m1 = fromDt.getYear() * 12 + fromDt.getMonth();
	        int m2 = curDate.getYear() * 12 + curDate.getMonth();
	        monthDiff = m2 - m1;*/
			Date date = BeanUtil.getThree_month_updated();
		if(toDate.before(date)){
			//System.out.println("in ddr histry-->"+date);
			cccSummaryPDTP = handler.getCCCSummaryPDTPHistory(fromDt, toDate, branch);
		}else if(fromDt.after(date)){			
			cccSummaryPDTP = handler.getCCCSummaryPDTP(fromDt, toDate, branch);
		}else {
			cccSummaryPDTP = handler.getCCCSummaryPDTPUnion(fromDt, toDate, branch);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cccSummaryPDTP;

		
	}

	private GeneralSummaryDTO[] getCCChargeSummary(Date fromDt, Date toDate,
			String branch) {
		
		//int monthDiff = 0;
		GeneralSummaryDTO[] cccSummary = null;
		
		try {			

			/*Date curDate = new Date();
			int m1 = fromDt.getYear() * 12 + fromDt.getMonth();
	        int m2 = curDate.getYear() * 12 + curDate.getMonth();
	        monthDiff = m2 - m1;*/
			Date date = BeanUtil.getThree_month_updated();
		if(toDate.before(date)){
			//System.out.println("in ddr histry-->"+date);
			cccSummary = handler.getCCChargeSummaryHistory(fromDt, toDate, branch);
		}else if(fromDt.after(date)){
			
			cccSummary = handler.getCCChargeSummary(fromDt, toDate, branch);
		}else{
			cccSummary = handler.getCCChargeSummaryUnion(fromDt, toDate, branch);
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cccSummary;

		
	}

	
	private String[] getCCCSummaryHeading(Table refTable){
		
		String list[] = new String[3];
		
		list[0] =txtCCCFrom.getText();
		list[1] =txtCCCTo.getText();
		String branchCode = cbCCCBranch.getText();
		if (!branchCode.equalsIgnoreCase("All")) {
			int index = branchCode.indexOf(" - ");
			list[2] = branchCode.substring(index + 3);
		}
		else
			list[2] ="All";
			
		return list;
	}
	
	
	@Override
	public void handleEvent(Event e) {
		// TODO Auto-generated method stub
		String selection = tabReport.getSelection()[0].getText();
		TableColumn column = (TableColumn) e.widget;
		
		if (column == colCCSsno) {
			new sortListener().sortByFloat(0, tblCCCSummary);
		} else if (column == colCCSBranchCode) {
			new sortListener().sortByString(1, tblCCCSummary);
		} else if (column == colCCSStationCode) {
			new sortListener().sortByString(2, tblCCCSummary);
		} else if (column == colCCSTotalPT) {
			new sortListener().sortByFloat(3, tblCCCSummary);
		} else if (column == colCCLRs) {
			new sortListener().sortByFloat(4, tblCCCSummary);
		} else if (column == colCCbftofTot) {
			new sortListener().sortByFloat(5, tblCCCSummary);
		} else if (column == colCCSBFTLr) {
			new sortListener().sortByFloat(6, tblCCCSummary);
		} else if (column == colCCSTotalCCC) {
			new sortListener().sortByFloat(7, tblCCCSummary);
		} else if (column == colCCperofCConTot) {
			new sortListener().sortByFloat(8, tblCCCSummary);
		} else if (column == colCCCOnBasFrt) {
			new sortListener().sortByFloat(9, tblCCCSummary);
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
		
		if (source == btnCCCGo) {
			new sortListener().display("Generating Report...", STATUS_SUCCESS,
					SUCCESS_FONT_COLOR,lblStatusBar);

			handleCCC();
			new sortListener().display("Report Loaded Successfully!",
					STATUS_SUCCESS, SUCCESS_FONT_COLOR,lblStatusBar);
		} 
		else if (source == btnExportXL) {
			try {
				//handleExcel();
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				list = new sortListener().getBPATable(tblCCCSummary);
				value = getCCCSummaryHeading(tblCCCSummary);
				new sortListener().prepareExcel(list, "CCSummary", CCSUMMARY_EXCEL_JRXML, null,value,shell,lblStatusBar);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else if (source == btnPrint) {
			try {
				//handlePrint();
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				list = new sortListener().getBPATable(tblCCCSummary);
				value = getCCCSummaryHeading(tblCCCSummary);
				handler.preparePrint(list, "CCSummary", CCSUMMARY_EXCEL_JRXML, null,value);
			
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		else if (source == btnExportPDF) {
			try {
				//handlePDF();
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				list = new sortListener().getBPATable(tblCCCSummary);
				value = getCCCSummaryHeading(tblCCCSummary);
				new sortListener().preparePDF(list, "CCSummary", CCSUMMARY_EXCEL_JRXML, null,value,shell,lblStatusBar);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		
	}

}
