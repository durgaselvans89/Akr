package hm.akr.container.admin;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import hm.akr.common.sortListener;
import hm.akr.common.SWTResourceManager;
import hm.akr.common.BeanUtil;
import hm.akr.common.IUIConstants;
import hm.akr.common.KalendarDialog;
import hm.akr.common.NumericValidation;
import hm.akr.container.history.HistoryHandler;
import hm.akr.dto.DDRDTO;
import hm.akr.dto.StationsDTO;
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

public class DDcharge extends ReportsMenu implements IUIConstants,Listener,SelectionListener {

	private TabItem tiDDExtra;
	private Canvas cvsDDExtra;
	private Table tblDDExtra;
	private Button btnDDEGo;
	private Combo cbDDEStation;
	private TableColumn colDDEsno;
	private TableColumn colDDELrNo;
	private TableColumn colDDETo;
	private TableColumn colDDELrType;
	private TableColumn colDDEDD;
	private TableColumn colDDEDDE;
	private TableColumn colDDELrTotal;
	private TableColumn colDDEPerTotal;
	private TableColumn colDDEbft;
	private TableColumn colDDEOTHERS;
	private Combo cbDDEBranch;
	private Label label7;
	private Label lblFromDate;
	private Label lblDDEStation;
	private Label lblToDate;
	private Text txtDDEFrom;
	private Button btnDDETo;
	private Button btnDDEFromDate;
	private Text txtDDEAbove;
	private Label lblAbove;
	private Text txtDDETo;
	private String DD_EXTRA = "DD Extra";
	private static final String DDE_EXCEL_JRXML = "hm/akr/resources/printer/DDE.jrxml";
	
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
	
	 public DDcharge(Composite composite,int style, float clientRights) {
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
			tiDDExtra = new TabItem(tabReport, SWT.NONE);
			tiDDExtra.setText(DD_EXTRA);

			cvsDDExtra = new Canvas(tabReport, SWT.NONE);
			tiDDExtra.setControl(cvsDDExtra);

			{
				{
					tblDDExtra = new Table(cvsDDExtra, SWT.FULL_SELECTION
							| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);

					tblDDExtra.setHeaderVisible(true);
					tblDDExtra.setLinesVisible(true);
					tblDDExtra.setBounds(10, 72, 765, 400);
					{
						colDDEsno = new TableColumn(tblDDExtra, SWT.NONE);
						colDDEsno.setText("S NO");
						colDDEsno.setWidth(49);
						colDDEsno
								.addListener(SWT.Selection, this);

					}
					{
						colDDELrNo = new TableColumn(tblDDExtra, SWT.NONE);
						colDDELrNo.setText("LR No");
						colDDELrNo.setWidth(90);
						colDDELrNo.addListener(SWT.Selection,
								this);
					}
					
					{
						colDDETo = new TableColumn(tblDDExtra, SWT.NONE);
						colDDETo.setText("TO");
						colDDETo.setWidth(90);
						colDDETo.addListener(SWT.Selection,
								this);
					}
					{
						colDDELrType = new TableColumn(tblDDExtra, SWT.NONE);
						colDDELrType.setText("LR Type");
						colDDELrType.setWidth(90);
						colDDELrType.addListener(SWT.Selection,
								this);

					}
					{
						colDDEbft = new TableColumn(tblDDExtra, SWT.NONE);
						colDDEbft.setText("Basic Freight");
						colDDEbft.setWidth(90);
						colDDEbft
								.addListener(SWT.Selection, this);

					}
					{
						colDDEDD = new TableColumn(tblDDExtra, SWT.NONE);
						colDDEDD.setText("DD");
						colDDEDD.setWidth(80);
						colDDEDD.addListener(SWT.Selection, this);

					}
					{
						colDDEDDE = new TableColumn(tblDDExtra, SWT.NONE);
						colDDEDDE.setText("DD Extra");
						colDDEDDE.setWidth(80);
						colDDEDDE
								.addListener(SWT.Selection, this);

					}
					{
						colDDEOTHERS = new TableColumn(tblDDExtra, SWT.NONE);
						colDDEOTHERS.setText("Others");
						colDDEOTHERS.setWidth(80);
						colDDEOTHERS.addListener(SWT.Selection,
								this);

					}

					{
						colDDELrTotal = new TableColumn(tblDDExtra, SWT.NONE);
						colDDELrTotal.setText("LR Total");
						colDDELrTotal.setWidth(90);
						colDDELrTotal.addListener(SWT.Selection,
									this);

					}

					{
						colDDEPerTotal = new TableColumn(tblDDExtra, SWT.NONE);
						colDDEPerTotal.setText("% on Total");
						colDDEPerTotal.setWidth(90);
						colDDEPerTotal.addListener(SWT.Selection,
								this);

					}

				}

				{
					lblFromDate = new Label(cvsDDExtra, SWT.NONE);
					lblFromDate.setText("From Date");
					lblFromDate.setBounds(9, 28, 54, 20);
				}
				{
					lblToDate = new Label(cvsDDExtra, SWT.NONE);
					lblToDate.setText("To Date");
					lblToDate.setBounds(169, 28, 40, 16);
				}

				{
					txtDDEFrom = new Text(cvsDDExtra, SWT.BORDER);
					txtDDEFrom.setBounds(66, 26, 70, 22);
					txtDDEFrom.setEditable(false);
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					txtDDEFrom.setText(date);

				}
				{
					btnDDEFromDate = new Button(cvsDDExtra, SWT.PUSH
							| SWT.CENTER);
					btnDDEFromDate.setBounds(136, 25, 31, 23);
					btnDDEFromDate.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btnDDEFromDate.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent e) {
							KalendarDialog cd = new KalendarDialog(shell, 1);
							Object obj = cd.open();
							if (obj != null) {
								String date = obj.toString();
								txtDDEFrom.setText(date);
							}
						}
					});
				}

				{
					txtDDETo = new Text(cvsDDExtra, SWT.BORDER);
					txtDDETo.setBounds(213, 25, 70, 22);
					txtDDETo.setEditable(false);
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					txtDDETo.setText(date);

				}
				{
					btnDDETo = new Button(cvsDDExtra, SWT.PUSH | SWT.CENTER);
					btnDDETo.setBounds(283, 24, 32, 23);
					btnDDETo.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btnDDETo.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent e) {
							KalendarDialog cd = new KalendarDialog(shell);
							Object obj = cd.open();
							if (obj != null) {
								String date = obj.toString();
								txtDDETo.setText(date);
							}
						}
					});
				}

				
				{
					lblDDEStation = new Label(cvsDDExtra, SWT.NONE);
					lblDDEStation.setText("Station");
					lblDDEStation.setBounds(502, 27, 36, 21);
				}
				{
					cbDDEStation = new Combo(cvsDDExtra, SWT.READ_ONLY);
					cbDDEStation.setBounds(540, 27, 130, 21);
					cbDDEStation.add("All");
				}
				
				{
					cbDDEBranch = new Combo(cvsDDExtra, SWT.READ_ONLY);
					cbDDEBranch.setBounds(361, 27, 130, 20);
					//cbDDEBranch.addSelectionListener(this);
					//cbDDEBranch.add("All");

					if(clientRights == 1 || clientRights == 1.0){
						
						try{
						
							getCurrentBranch_code(currentStationCode);
							cbDDEBranch.select(0);
							//populateStationForCLR();
							handleBranchActionDDE(cbDDEBranch, cbDDEStation);
							cbDDEBranch.addSelectionListener(this);
						}
						catch(Exception e){
							e.printStackTrace();
						}
						
						
					}
					else{
						try{
							StationsDTO[] dto = handler.getAllBranches();
							if (null != dto) {
								cbDDEBranch.add("All");
								for (int i = 0; i < dto.length; i++) {
									cbDDEBranch.add(dto[i].getName() + " - "
											+ dto[i].getId());
								}
							}
							cbDDEBranch.addSelectionListener(this);
						}
						catch(Exception e){
							e.printStackTrace();
						}
					}
				}
				{
					label7 = new Label(cvsDDExtra, SWT.NONE);
					label7.setText("Branch");
					label7.setBounds(322, 28, 39, 15);
					
				}
				

				{
					lblAbove = new Label(cvsDDExtra, SWT.NONE);
					lblAbove.setText("Above");
					lblAbove.setBounds(678, 28, 34, 21);
				}
				{
					txtDDEAbove = new Text(cvsDDExtra, SWT.BORDER);
					txtDDEAbove.setBounds(716, 27, 45, 21);
					txtDDEAbove.addVerifyListener(new NumericValidation());
				}

				{
					btnDDEGo = new Button(cvsDDExtra, SWT.PUSH | SWT.CENTER);
					btnDDEGo.setText("Go");
					btnDDEGo.setBounds(768, 27, 37, 23);
					btnDDEGo.addSelectionListener(this);
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
					
						cbDDEBranch.add(stations[i].getName()+" - "+branch_code);
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

	
	private void handleBranchActionDDE(Combo branch, Combo cbStation) {

		StationsDTO[] station = null;
		String value = "";
		String stationCode = "";

		try {
				value = branch.getText();
			
			
				int index = value.indexOf(" - ");
				stationCode = value.substring(index + 3);
	
				if (stationCode != null) {
					station = handler.getStations(stationCode);
				}
				if (null != station) {
	
					int len = station.length;
					cbStation.removeAll();
					cbStation.add("All");
					if(!branch.getText().equalsIgnoreCase("All")){
						
						for (int i = 0; i < len; i++) {
							cbStation.add(station[i].getName() + " - "
									+ station[i].getId());
						}
					}
				
				}
			
		} catch (Exception exception) {

		}
	}
	
	private void handleDDE() {
		try {
			float limit = 0;
			if (!txtDDEAbove.getText().equals("")) {
				limit = Float.parseFloat(txtDDEAbove.getText());
			}

			DDRDTO[] ddDetails = getDDReport();
			if (tblDDExtra != null)
				tblDDExtra.removeAll();

			if (ddDetails != null) {
				populateDDReport(ddDetails, limit);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	private DDRDTO[] getDDReport() {
		DDRDTO[] ddDetails = null;
		String stnCode = "";
		String branchCode = "";
		try {
			if (!cbDDEStation.getText().equals("")) {
				SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
				Date from = dt.parse(txtDDEFrom.getText());
				Date to = dt.parse(txtDDETo.getText());
				stnCode = cbDDEStation.getText();
				branchCode = cbDDEBranch.getText();
				if((branchCode.equalsIgnoreCase("All")) &&(stnCode.equalsIgnoreCase("All"))){
					stnCode = "%";
				}
				else if (!stnCode.equalsIgnoreCase("All")) {
					int index = stnCode.indexOf(" - ");
					stnCode = stnCode.substring(index + 3);
				} else {
					int index = branchCode.indexOf(" - ");
					branchCode = branchCode.substring(index + 3);

					stnCode = "All-" + branchCode;
				}

				// System.out.println("stncode--->"+from+"to-->"+to);

				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Date date = null;
				//int monthDiff = 0;
				try {
					date = dateFormat.parse(txtDDEFrom.getText());
					/*Date curDate = new Date();			
					int m1 = date.getYear() * 12 + date.getMonth();
			        int m2 = curDate.getYear() * 12 + curDate.getMonth();
			        monthDiff = m2 - m1;*/
					
				} catch (ParseException e) {
					e.printStackTrace();
				}
				Date date1 = BeanUtil.getThree_month_updated();
				if(to.before(date1)){
					//System.out.println("in ddr histry-->"+date1);
					ddDetails = handler.getDDReportHistory(from, to, stnCode);
				}else if(from.after(date1)){
					ddDetails = handler.getDDReport(from, to, stnCode);
				}else {
					ddDetails = handler.getDDReportUnion(from, to, stnCode);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ddDetails;

	}

	
	private void populateDDReport(DDRDTO[] ddDetails, float limit) {
		int len = ddDetails.length;
		float dd = 0;
		float dde = 0;
		float others = 0;
		int count=0;
		float totBft = 0;
		float totDDC = 0;
		float totDDE = 0;
		float totOthers = 0;
		float totTotal = 0;
		float totPercent = 0;
		float totPercentavg=0;
		if (len > 0) {
			for (int i = 0, j = 1; i < len; i++) {
				dd = ddDetails[i].getDdc();
				dde = ddDetails[i].getDdExtra();
				others = ddDetails[i].getOthers();
			
				if ((dd >= limit || others >= limit) || dde >= limit) {
					TableItem item = new TableItem(tblDDExtra, SWT.NONE);
					item.setText(0, String.valueOf(j));
					item.setText(1, ddDetails[i].getLrNo());
					item.setText(2, ddDetails[i].getFromStation());
					item.setText(3, ddDetails[i].getLrType());
					totBft = totBft + ddDetails[i].getBasicFreight();
					item.setText(4, getRoundedValue(ddDetails[i]
							.getBasicFreight()));
					totDDC = totDDC + ddDetails[i].getDdc();
					item.setText(5, getRoundedValue(ddDetails[i].getDdc()));
					totDDE = totDDE + ddDetails[i].getDdExtra();
					item.setText(6, getRoundedValue(ddDetails[i].getDdExtra()));
					totOthers = totOthers + ddDetails[i].getOthers();
					item.setText(7, getRoundedValue(ddDetails[i].getOthers()));
					totTotal = totTotal + ddDetails[i].getTotal();
					item.setText(8, getRoundedValue(ddDetails[i].getTotal()));

					item.setText(9, getRoundedValue(((ddDetails[i].getDdc()
							+ ddDetails[i].getDdExtra() + ddDetails[i]
							.getOthers()) / ddDetails[i].getTotal()) * 100));

					totPercent = totPercent + Float.valueOf(item.getText(9));
					count++;
					j++;
				}
			}
			
			totPercentavg = totPercent /count ;
			final TableItem item1 = new TableItem(tblDDExtra, SWT.NONE);
			Font font1 = new Font(Display.getCurrent(), "Tahoma", 8, SWT.BOLD);
			item1.setFont(font1);
			item1.setText(3, "TOTAL");
			item1.setText(4, getRoundedValue(totBft));
			item1.setText(5, getRoundedValue(totDDC));
			item1.setText(6, getRoundedValue(totDDE));
			item1.setText(7, getRoundedValue(totOthers));
			item1.setText(8, getRoundedValue(totTotal));
			item1.setText(9, getRoundedValue(totPercent));
			final TableItem item2 = new TableItem(tblDDExtra, SWT.NONE);
			item2.setFont(font1);
			item2.setText(2, "Average");
			item2.setText(9, getRoundedValue(totPercentavg));
			
			
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

	
	private String[] getDDEHeading(){
		
		String list[] = new String[5];
		
		list[0] =txtDDEFrom.getText();
		list[1] =txtDDETo.getText();
		String branchCode = cbDDEBranch.getText();
		if (!branchCode.equalsIgnoreCase("All")) {
			int index = branchCode.indexOf(" - ");
			list[2] = branchCode.substring(index + 3);
		}
		else
			list[2] ="All";
			
		String stationCode = cbDDEStation.getText();
		if (!stationCode.equalsIgnoreCase("All")) {
			int index = stationCode.indexOf(" - ");
			list[3] = stationCode.substring(index + 3);
		}
		else
			list[3] ="All";
		
		
		list[4] =txtDDEAbove.getText();
		
			
		return list;
	}
	
	private ArrayList<DDRDTO> getDDETable() {
		DDRDTO dto = null;
		ArrayList<DDRDTO> list = new ArrayList<DDRDTO>();
		int length = 0;

		TableItem[] items = tblDDExtra.getItems();
		if (items != null) {
			length = items.length;
		}
		if (length > 0) {
			for (int i = 0; i < length; i++) {
				dto = new DDRDTO();
				dto.setLrNo(items[i].getText(1));
				dto.setFromStation(items[i].getText(2));
				dto.setLrType(items[i].getText(3));
				if (!items[i].getText(4).equals(""))
					dto.setBasicFreight(Float.parseFloat(items[i].getText(4))); 
				else
					dto.setBasicFreight(-1);
				if (!items[i].getText(5).equals(""))
					dto.setDdc(Float.parseFloat(items[i].getText(5)));
				else
					dto.setDdc(-1);
				if (!items[i].getText(6).equals(""))
					dto.setDdExtra(Float.parseFloat(items[i].getText(6)));
				else
					dto.setDdExtra(-1);
				if (!items[i].getText(7).equals(""))
					dto.setOthers(Float.parseFloat(items[i].getText(7)));
				else
					dto.setOthers(-1);
				if (!items[i].getText(8).equals(""))
					dto.setTotal(Float.parseFloat(items[i].getText(8)));
				else
					dto.setTotal(-1);
				if (!items[i].getText(9).equals(""))
					dto.setDdcFree(Float.parseFloat(items[i].getText(9)));

				list.add(dto);
			}
		}

		return list;
	}
	
	
	@Override
	public void handleEvent(Event e) {
		// TODO Auto-generated method stub
		String selection = tabReport.getSelection()[0].getText();
		TableColumn column = (TableColumn) e.widget;
		
		if (column == colDDEsno) {
			new sortListener().sortByFloat(0, tblDDExtra);
		} else if (column == colDDELrNo) {
			new sortListener().sortByLrNo(1, tblDDExtra);
		} else if (column == colDDETo) {
			new sortListener().sortByString(2, tblDDExtra);
		}else if (column == colDDELrType) {
			new sortListener().sortByString(3, tblDDExtra);
		} else if (column == colDDEbft) {
			new sortListener().sortByFloat(4, tblDDExtra);
		} else if (column == colDDEDD) {
			new sortListener().sortByFloat(5, tblDDExtra);
		} else if (column == colDDEDDE) {
			new sortListener().sortByFloat(6, tblDDExtra);
		} else if (column == colDDEOTHERS) {
			new sortListener().sortByFloat(7, tblDDExtra);
		} else if (column == colDDELrTotal) {
			new sortListener().sortByFloat(8, tblDDExtra);
		} else if (column == colDDEPerTotal) {
			new sortListener().sortByFloat(9, tblDDExtra);
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
		
		if (source == cbDDEBranch) {
			handleBranchActionDDE(cbDDEBranch, cbDDEStation);
			txtDDEAbove.setText("");
		} else if (source == btnDDEGo) {
			new sortListener().display("Generating Report...", STATUS_SUCCESS,
					SUCCESS_FONT_COLOR,lblStatusBar);
			handleDDE();
			new sortListener().display("Report Loaded Successfully!",
					STATUS_SUCCESS, SUCCESS_FONT_COLOR,lblStatusBar);
		}
		else if (source == btnExportXL) {
			try {
				//handleExcel();
				ArrayList<DDRDTO> list = null;
				String[] value = null;
				value = getDDEHeading();
				list = getDDETable();
				new sortListener().prepareExcel(list, "DDExtra", DDE_EXCEL_JRXML, null,value,shell,lblStatusBar);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else if (source == btnPrint) {
			try {
				//handlePrint();
				ArrayList<DDRDTO> list = null;
				String[] value = null;
				value = getDDEHeading();
				list = getDDETable();
				handler.preparePrint(list, "DDExtra", DDE_EXCEL_JRXML, null,value);
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		else if (source == btnExportPDF) {
			try {
				//handlePDF();
				ArrayList<DDRDTO> list = null;
				String[] value = null;
				value = getDDEHeading();
				list = getDDETable();
				new sortListener().preparePDF(list, "DDExtra", DDE_EXCEL_JRXML, null,value,shell,lblStatusBar);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		
	}

}
