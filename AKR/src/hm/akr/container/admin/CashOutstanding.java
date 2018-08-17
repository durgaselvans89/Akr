package hm.akr.container.admin;

import hm.akr.common.BeanUtil;
import hm.akr.common.IUIConstants;
import hm.akr.container.commission.CommissionCompositeHandler;
import hm.akr.container.history.HistoryHandler;
import hm.akr.dto.BookingDTO;
import hm.akr.dto.OutstandingDTO;
import hm.akr.dto.StationsDTO;
import hm.akr.dto.VersionDTO;

import java.io.File;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import hm.akr.common.sortListener;
public class CashOutstanding extends Composite implements SelectionListener,IUIConstants,Listener{
	
	
	
	private TabItem outstandingtab;

	private Canvas canoutstanding;

	private Table tbloutstanding;

	private TableColumn tableColumn2;

	private TableColumn coloutbranch;

	private TableColumn colStationName2;

	private TableColumn colcode;

	private TableColumn colpbdd;

	private TableColumn coltpod;

	private TableColumn colpdbooking;

	private TableColumn coltotal;

	

	private Combo cbbranch;

	private Button btnoutstanding;
	private Display display;
	BookingDTO[] outstandingdto = null;
	VersionDTO[] dto = null;
	private TabFolder tabReport = null;
	private Label lblStatusBar;
	private Shell shell = null;
	DateFormat viewdateformat = null;
	private Button btnPrint;
	private Button btnExportXL;
	private Button btnExportPDF;
	private ReportsMenuHandler handler;
	HistoryHandler historyH = null;
	private String SERVER_DATE = null;
	private CommissionCompositeHandler comnHandler = null;
	private DecimalFormat decimalFormat;
	private BeanUtil beanutil  = null;
	
	
	public CashOutstanding(Composite composite, int swtValue) {
		super(composite, swtValue);
		
		try {
			shell = composite.getShell();
			handler = new ReportsMenuHandler();
			comnHandler = new CommissionCompositeHandler();
			SERVER_DATE = handler.getServerDate();
			beanutil = BeanUtil.getInstance();
			decimalFormat = new DecimalFormat("0.00");
			viewdateformat = new SimpleDateFormat("dd-MM-yyyy");
			historyH = new HistoryHandler();
			dto = historyH.getHistoryYears();
			if(dto != null && dto.length > 0){
				BeanUtil.setThree_month_updated(dto[0].getUpdated_date());
			}
		} catch (Exception e) {
				e.printStackTrace();
		}
	}
	
	public Composite loadcomposite() {
		
		this.setBounds(45,100, 1020,700);
		tabReport = new TabFolder(this, SWT.NONE);
		tabReport.setBounds(50, 25, 820, 500);
		tabReport.addSelectionListener(this);
		

		
		
		//if (beanutil.isAdmin() && (BeanUtil.getDbName() == null || BeanUtil.getDbName().equals("")) ) {
			
			outstandingtab = new TabItem(tabReport, SWT.NONE);
			outstandingtab.setText("CASH OUTSTANDING");
			
			canoutstanding = new Canvas(tabReport, SWT.NONE);
			outstandingtab.setControl(canoutstanding);
			{
				tbloutstanding = new Table(canoutstanding,
						SWT.FULL_SELECTION | SWT.H_SCROLL
								| SWT.V_SCROLL | SWT.BORDER);
				tbloutstanding.setBounds(35, 47, 775, 418);

				tbloutstanding.setHeaderVisible(true);
				tbloutstanding.setLinesVisible(true);

				{
					tableColumn2 = new TableColumn(tbloutstanding,
							SWT.NONE);
					tableColumn2.setText("SNO");
					tableColumn2.setWidth(45);
					//tableColumn2.addSelectionListener(new ReportingListener());
				}
				{
					coloutbranch = new TableColumn(tbloutstanding,
							SWT.NONE);
					coloutbranch.setText("Branch Code");
					coloutbranch.setWidth(95);
					//coloutbranch.addSelectionListener(new ReportingListener());
					coloutbranch.addListener(SWT.Selection,
							this);
				}
				{
					colStationName2 = new TableColumn(
							tbloutstanding, SWT.NONE);
					colStationName2.setText("Station Name");
					colStationName2.setWidth(207);
					//colStationName2.addSelectionListener(new ReportingListener());
					colStationName2.addListener(SWT.Selection,
							this);
				}
				{
					colcode = new TableColumn(tbloutstanding,
							SWT.NONE);
					colcode.setText("Station Code");
					colcode.setWidth(77);
					//colcode.addSelectionListener(new ReportingListener());
					colcode.addListener(SWT.Selection,
							this);
				}
				{
					colpbdd = new TableColumn(tbloutstanding,
							SWT.NONE);
					colpbdd.setText("TP \u2013 DD");
					colpbdd.setWidth(72);
					//colpbdd.addSelectionListener(new ReportingListener());
					colpbdd.addListener(SWT.Selection,
							this);
				}
				{
					coltpod = new TableColumn(tbloutstanding,
							SWT.NONE);
					coltpod.setText("TP-OD");
					coltpod.setWidth(68);
					//coltpod.addSelectionListener(new ReportingListener());
					coltpod.addListener(SWT.Selection,
							this);
				}
				{
					colpdbooking = new TableColumn(tbloutstanding,
							SWT.NONE);
					colpdbooking.setText("PDBOOKING");
					colpdbooking.setWidth(107);
					//colpdbooking.addSelectionListener(new ReportingListener());
					colpdbooking.addListener(SWT.Selection,
							this);

				}
				{
					coltotal = new TableColumn(tbloutstanding,
							SWT.NONE);
					coltotal.setText("TOTAL");
					coltotal.setWidth(78);
					//coltotal.addSelectionListener(new ReportingListener());
					coltotal.addListener(SWT.Selection,
							this);
				}
				{
					cbbranch = new Combo(canoutstanding, SWT.NONE);
					cbbranch.setBounds(577, 6, 187, 21);
					populateSelectedBranches();
					cbbranch.addSelectionListener(this);
				}
				{
					btnoutstanding = new Button(canoutstanding,
							SWT.PUSH | SWT.CENTER);
					btnoutstanding.setText("Go");
					btnoutstanding.setBounds(776, 6, 36, 26);
					btnoutstanding.addSelectionListener(this);
				}
				/*if (beanutil.isAdmin() && !beanutil.isAdminHead()
						&& !beanutil.isAdminHeadStationary()) {
					cbbranch.setEnabled(false);
					btnoutstanding.setEnabled(false);
				}*/

			}
		//}
		
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
		
		/*{
			lblStatusBar = new Label(this, SWT.NONE);
			lblStatusBar.setBounds(60,570 ,300,25);
			lblStatusBar.setFont(STATUS_SUCCESS);
			lblStatusBar.setText("STATUS");
			lblStatusBar.setForeground(LINK_FOCUS_COLOR);
			lblStatusBar.setVisible(true);
		}*/
		
		return this;

		
		
	}
	
	public StationsDTO[] getAllBranches() throws Exception {

		StationsDTO[] stations = beanutil.getAvailableStations();
		StationsDTO[] branches = null;
		ArrayList<StationsDTO> list = new ArrayList<StationsDTO>();
		if (null != stations) {
			ArrayList<String> branchNameList = new ArrayList<String>();

			// Get the list of branch codes
			int len = stations.length;
			for (int i = 0; i < len; i++) {
				if (!branchNameList.contains(stations[i].getBranch_code())) {
					branchNameList.add(stations[i].getBranch_code());
				}
			}
			java.util.Collections.sort(branchNameList);
			int size = branchNameList.size();
			branches = new StationsDTO[size];

			// Get the associate name of the branch code
			for (int j = 0; j < size; j++) {
				String branchcode = branchNameList.get(j);

				for (int i = 0; i < len; i++) {
					if (branchcode.equals(stations[i].getId())) {
						// branches[j] = stations[i];
						list.add(stations[i]);
						break;
					}
				}
			}

		}
		int size = list.size();
		branches = list.toArray(new StationsDTO[size]);
		return branches;
	}
		
		
			
		/**
		* Method to populate outstanding Details.
		* 
		* @param dto
		*/
	
	
	private void populateOutstandingReport() throws ParseException,
	Exception {
	
		if (null != tbloutstanding) {
			tbloutstanding.removeAll();
		
			
			/*StationsDTO[] stations = getAllBranches();
			int len = stations.length;
			System.out.println("length-->"+len);
			int index = -1;
			String selectednames = null;
			int tableindex = 1;
			for (int i = 0; i < len; i++) {
				index = cbbranch.getSelectionIndex();
				if (index > -1) {
					selectednames = cbbranch.getItem(index);
				}
				if (null != selectednames) {
					index = selectednames.indexOf("-");
					if (index > -1) {
						selectednames = selectednames.substring(index + 1);
		
					}
				}
				if (null != selectednames) {
					selectednames = selectednames.trim();
				} else
					break;
				System.out.println("selecte-->"+selectednames);
				System.out.println("delivery323-->"+stations[i].getBranch_code());
				if (selectednames.equals(stations[i].getBranch_code())) {
					System.out.println("deliver-->"+stations[i].getBranch_code());
					TableItem item = new TableItem(tbloutstanding, SWT.NONE);
					item.setText(0, String.valueOf(tableindex++));
					item.setText(1, stations[i].getBranch_code());
					item.setText(2, stations[i].getName());
					item.setText(3, stations[i].getId());
					item.setText(4, String.valueOf("0.00"));
					item.setText(5, String.valueOf("0.00"));
					item.setText(6, String.valueOf("0.00"));
					item.setText(7, String.valueOf("0.00"));
				}
			}*/
			int index = -1;
			String selectednames = null;
			try{
				index = cbbranch.getSelectionIndex();
				if (index > -1) {
					selectednames = cbbranch.getItem(index);
				}
				if (null != selectednames) {
					index = selectednames.indexOf("-");
					if (index > -1) {
						selectednames = selectednames.substring(index + 1);
		
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
			populateSelectedStations(selectednames,tbloutstanding);
			
			Date date = null;
		
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			date = dateFormat.parse("2008-09-09");
			if (null == outstandingdto)
				outstandingdto = beanutil.getOutstandingReport(null, date);
			if (null != outstandingdto) {
				populateOutstandingDetails(outstandingdto);
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
					
					
					if (branchCode.equalsIgnoreCase(stations[i].getBranch_code())) {
						TableItem item = new TableItem(table, SWT.NONE);
						
						item.setText(0, String.valueOf(sNo));
						item.setText(1, branchCode);
						item.setText(2, stations[i].getName());
						item.setText(3, stations[i].getId());
						item.setText(4, String.valueOf("0.00"));
						item.setText(5, String.valueOf("0.00"));
						item.setText(6, String.valueOf("0.00"));
						item.setText(7, String.valueOf("0.00"));

						sNo += 1;
					}
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
		private void populateOutstandingDetails(BookingDTO[] dto) {
		TableItem[] items = tbloutstanding.getItems();
		String stationcode = null;
		String deliveredcode = null;
		float tpdd = 0;
		float tpod = 0;
		float paid = 0;
		float total = 0;
		//btnExportXL.setEnabled(true);
		//btnExportPDF.setEnabled(true);
		//btnprint.setEnabled(true);
		
		
		int length = items.length;
		for (int i = 0; i < dto.length; i++) {
			stationcode = dto[i].getFrom();
		
			for (int j = 0; j < length; j++) {
		
				deliveredcode = items[j].getText(3);
			
				if (stationcode.equalsIgnoreCase(deliveredcode)) {
					tpdd = Float.parseFloat(items[j].getText(4));
					tpod = Float.parseFloat(items[j].getText(5));
					paid = Float.parseFloat(items[j].getText(6));
					total = Float.parseFloat(items[j].getText(7));
					if (dto[i].getType().equalsIgnoreCase("topay")) {
		
						if (dto[i].getDdc() != 0.0) {
							tpdd = tpdd + dto[i].getTotal();
						} else {
							tpod = tpod + dto[i].getTotal();
						}
		
					} else if (dto[i].getType().equalsIgnoreCase("paid")) {
						paid = paid + dto[i].getTotal();
					}
		
					total = tpdd + tpod + paid;
					items[j].setText(4, String.valueOf(tpdd));
					items[j].setText(5, String.valueOf(tpod));
					items[j].setText(6, String.valueOf(paid));
					items[j].setText(7, String.valueOf(total));
					break;
		
				}
			}
		}
		tpdd = 0;
		tpod = 0;
		paid = 0;
		total = 0;
		for (int i = 0; i < length; i++) {
			tpdd = tpdd + Float.parseFloat(items[i].getText(4));
			tpod = tpod + Float.parseFloat(items[i].getText(5));
			paid = paid + Float.parseFloat(items[i].getText(6));
			total = total + Float.parseFloat(items[i].getText(7));
		}
		if (length > 0) {
		
			final TableItem totalItem = new TableItem(tbloutstanding,
					SWT.NONE);
			Font totalFont = new Font(display, "Tahoma", 8, SWT.BOLD);
			totalItem.setFont(totalFont);
			totalItem.setText(2, "TOTAL");
			totalItem.setText(4, String.valueOf(tpdd));
			totalItem.setText(5, String.valueOf(tpod));
			totalItem.setText(6, String.valueOf(paid));
			totalItem.setText(7, String.valueOf(total));
		}
		
		}

	
	
		private OutstandingDTO[] buildOutstandingDTO() {
			// TODO Auto-generated method stub
			OutstandingDTO[] dto = null;
			if (null != tbloutstanding) {
				TableItem[] items = tbloutstanding.getItems();
				int len = items.length;
				dto = new OutstandingDTO[len];
				
				for (int i = 0; i < len; i++) {
					
					dto[i] = new OutstandingDTO();
					
					dto[i].setBranchcode(items[i].getText(1));
					
					dto[i].setStationname(items[i].getText(2));
					
					dto[i].setStationcode(items[i].getText(3));
					
					if (!items[i].getText(4).equals(""))
						dto[i].setTpdd(Float.parseFloat(items[i].getText(4)));
						
					
					if (!items[i].getText(5).equals(""))
						dto[i].setTpod(Float.parseFloat(items[i].getText(5)));
						
					
					if (!items[i].getText(6).equals(""))
						dto[i].setPdbooking(Float.parseFloat(items[i].getText(6)));
					
					if (!items[i].getText(7).equals(""))
						dto[i].setTotal(Float.parseFloat(items[i].getText(7)));
					
				
				}
			}
			return dto;

			
		}
	private void populateSelectedBranches() {
		try {
			AdminCompositeHandler adminhandler = new AdminCompositeHandler();
			StationsDTO[] stations = adminhandler.getAllBranches();
			if (null != stations) {
				int len = stations.length;
				for (int i = 0; i < len; i++) {
					cbbranch.add(stations[i].getName() + "-"
							+ stations[i].getId());
					if (stations[i].getId().equals(
							beanutil.getActingStationCode())) {
						cbbranch.select(i);
					}
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	private void prepareOutStandingPDF(OutstandingDTO[] dto, String fileName,
			String selectednames) {
		// TODO Auto-generated method stub
		try {
			/*AdminComposite.display("Exporting...", STATUS_SUCCESS,
					SUCCESS_FONT_COLOR);*/
			handler.printOutStandingReportPDF(dto,fileName,selectednames);

			FileDialog dialog = new FileDialog(shell, SWT.SAVE);
			dialog.setFilterExtensions(new String[] { "*.pdf" });
			dialog.setFilterNames(new String[] { "*.pdf" });
			dialog.setFilterPath("WINDOWS/Desktop"); // Windows path
			dialog.setFileName(fileName);
			String filepath = dialog.open();

			if (null != filepath) {
				File df = new File(filepath);
				File xl = new File("lib/" + fileName + ".pdf");

				if (xl.exists()) {
					//AdminComposite.display("Saving...", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					new sortListener().copyFile(xl, df, dialog.getFileName());
				}
				/*AdminComposite.display("PDF Saved Successfully",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);*/
			}

		} catch (Exception e) {
			//AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			e.printStackTrace();
			displayError("Status Success");
		}

		
	}
	
	private void prepareXLOutstandingDetails(OutstandingDTO[] dto,
			String fileName, String selectednames) {
		// TODO Auto-generated method stub
		try {
			
			handler.printOutstandingReportExcel(dto, fileName,selectednames);

			FileDialog dialog = new FileDialog(shell, SWT.SAVE);
			dialog.setFilterExtensions(new String[] { "*.xls" });
			dialog.setFilterNames(new String[] { "*.xls" });
			dialog.setFilterPath("WINDOWS/Desktop"); // Windows path
			dialog.setFileName(fileName);
			String filepath = dialog.open();

			if (null != filepath) {
				File df = new File(filepath);
				File xl = new File("lib/" + fileName + ".xls");

				if (xl.exists()) {
					//AdminComposite.display("Saving...", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					new sortListener().copyFile(xl, df, dialog.getFileName());
				}
				/*AdminComposite.display("Excel Saved Successfully",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);*/
			}
			/*else
			{
				AdminComposite.display("Excel Saving Cancelled",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			}*/
		} catch (Exception e) {
			//AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			e.printStackTrace();
			displayError("Status Success");
		}

		
		
	}
	
	private void displayError(String errorText) {
		MessageBox messageBox = new MessageBox(shell, SWT.OK);
		messageBox.setMessage(errorText);
		messageBox.setText("Alert");
		messageBox.open();
	}

	@Override
	public void widgetSelected(SelectionEvent event) {
		// TODO Auto-generated method stub
		Object source = event.getSource();
		
		if (source == btnoutstanding) {
				try {
					populateOutstandingReport();
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else if (source == btnPrint) {
				OutstandingDTO[] dto = buildOutstandingDTO();
				int index = -1;
				String selectednames = null;
				index = cbbranch.getSelectionIndex();
				
				if (index > -1) {
					selectednames = cbbranch.getItem(index);
				}
				try {
					handler.printOutstandingDetails(dto,selectednames,"OUTSTANDING REPORT");
					//btnprint.setEnabled(false);
					
				} catch (Exception exception) {
					displayError("Problem while printing");
					exception.printStackTrace();
				}
			}else if (source == btnExportXL) {
				int index = -1;
				String selectednames = null;
				OutstandingDTO[] dto = buildOutstandingDTO();
				index = cbbranch.getSelectionIndex();
				if (index > -1) {
					selectednames = cbbranch.getItem(index);
				}
				try {
					prepareXLOutstandingDetails(dto,"OUTSTANDING REPORT",selectednames);
					
					btnExportXL.setEnabled(false);
					
				} catch (Exception exception) {
					displayError("Problem while Exporting Excel");
				}
			}
			else if (source == btnExportPDF) {
				int index = -1;
				String selectednames = null;
				OutstandingDTO[] dto = buildOutstandingDTO();
				index = cbbranch.getSelectionIndex();
				if (index > -1) {
					selectednames = cbbranch.getItem(index);
				}
				try {
					prepareOutStandingPDF(dto,"OUTSTANDING REPORT",selectednames);
					
					btnExportPDF.setEnabled(false);
				} catch (Exception exception) {
					displayError("Problem while Exporting PDF");
				}
			}
		
	}

	@Override
	public void handleEvent(Event e) {
		// TODO Auto-generated method stub
		String selection = tabReport.getSelection()[0].getText();
		TableColumn column = (TableColumn) e.widget;
		
		if (column == coloutbranch) {
			new sortListener().sortByString(1, tbloutstanding);
		} else if (column == colStationName2) {
			new sortListener().sortByString(2, tbloutstanding);
		} else if (column == colcode) {
			new sortListener().sortByString(3, tbloutstanding);
		} else if (column == colpbdd) {
			new sortListener().sortByFloat(4, tbloutstanding);
		} else if (column == coltpod) {
			new sortListener().sortByFloat(5, tbloutstanding);
		} else if (column == colpdbooking) {
			new sortListener().sortByFloat(6, tbloutstanding);

		}

		else if (column == coltotal) {
			new sortListener().sortByFloat(7, tbloutstanding);

		}
	}

}
