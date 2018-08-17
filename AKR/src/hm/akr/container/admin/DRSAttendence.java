package hm.akr.container.admin;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


import hm.akr.common.MonthDialog;
import hm.akr.common.SWTResourceManager;
import hm.akr.common.BeanUtil;
import hm.akr.common.IUIConstants;
import hm.akr.common.UIColors;
import hm.akr.common.sortListener;
import hm.akr.container.history.HistoryHandler;
import hm.akr.dto.DRSAttendanceDTO;
import hm.akr.dto.StationsDTO;
import hm.akr.dto.TableDecorator;
import hm.akr.dto.VersionDTO;

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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;



public class DRSAttendence  extends ReportsMenu implements IUIConstants,Listener,SelectionListener {
	
	private static final String DRS_VEHICLE_EXCEL_JRXML = "hm/akr/resources/printer/DRS.jrxml";
	private String DRS = "DRS Attendance";
	private TabItem tiDRSAttendance;
	private Canvas cvsDRSAttendance;
	private Table tblDRSAttendance;
	private TableColumn colDRSsno;
	private TableColumn colDRSBranchCode;
	private TableColumn colDRSStationCode;
	private TableColumn colDRSMon1Ontime;
	private TableColumn colDRSMon1Total;
	private TableColumn colDRSMon1Percent;
	
	private Text txtDRSMonth;
	private Button btnDRSMonth;
	private Combo cbDRSAReportType;
	private Combo cbDRSAttendanceBranch;
	private Button btnDRSAttendance;
	private Label label11;
	private Label label12;
	private Label label13;
	
	
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
	
	 public DRSAttendence(Composite composite,int style, float clientRights) {
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

			tiDRSAttendance = new TabItem(tabReport, SWT.NONE);
			tiDRSAttendance.setText(DRS);

			cvsDRSAttendance = new Canvas(tabReport, SWT.NONE);
			tiDRSAttendance.setControl(cvsDRSAttendance);

			{

				{
					label11 = new Label(cvsDRSAttendance, SWT.NONE);
					label11.setText("Select Month");
					label11.setBounds(32, 34, 70, 17);
				}
				{
					txtDRSMonth = new Text(cvsDRSAttendance, SWT.BORDER);
					txtDRSMonth.setBounds(108, 32, 57, 21);
					txtDRSMonth.setEditable(false);
					DateFormat dateFormat = new SimpleDateFormat("MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					txtDRSMonth.setText(date);
				}
				{
					btnDRSMonth = new Button(cvsDRSAttendance, SWT.PUSH
							| SWT.CENTER);
					btnDRSMonth.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btnDRSMonth.setBounds(169, 30, 26, 23);
					btnDRSMonth.addSelectionListener(this);

				}

				{
					label12 = new Label(cvsDRSAttendance, SWT.NONE);
					label12.setText("Select Branch");
					label12.setBounds(406, 34, 76, 20);
				}

				{
					label13 = new Label(cvsDRSAttendance, SWT.NONE);
					label13.setText("Report Type");
					label13.setBounds(215, 34, 70, 19);
				}
				{
					cbDRSAReportType = new Combo(cvsDRSAttendance, SWT.READ_ONLY);
					cbDRSAReportType.setBounds(289, 34, 98, 23);
					cbDRSAReportType.add("Summary");
					cbDRSAReportType.add("%Report");
					cbDRSAReportType.addSelectionListener(this);
					cbDRSAReportType.select(0);

				}
				{
					cbDRSAttendanceBranch = new Combo(cvsDRSAttendance,
							SWT.READ_ONLY);
					cbDRSAttendanceBranch.setBounds(482, 34, 100, 23);
					cbDRSAttendanceBranch.addSelectionListener(this);
					if(clientRights == 1 || clientRights == 1.0){
						
						try{
						
							getCurrentBranch_code(currentStationCode);
							cbDRSAttendanceBranch.select(0);
							//handleBranchActionUT(cbUTBranch, cbUTStation);
							cbDRSAttendanceBranch.addSelectionListener(this);
						}
						catch(Exception e){
							e.printStackTrace();
						}
						
						
					}
					else{
						try{
							StationsDTO[] dto = handler.getAllBranches();
							if (null != dto) {
								cbDRSAttendanceBranch.add("All");
								for (int i = 0; i < dto.length; i++) {
									cbDRSAttendanceBranch.add(dto[i].getName() + " - "
											+ dto[i].getBranch_code());
								}
							}
						}
						catch (Exception e) {
							e.printStackTrace();
							// TODO: handle exception
						}
					}
				}
				{
					btnDRSAttendance = new Button(cvsDRSAttendance, SWT.PUSH
							| SWT.CENTER);
					btnDRSAttendance.setText("View");
					btnDRSAttendance.setBounds(601, 30, 50, 36);
					btnDRSAttendance.addSelectionListener(this);

				}

				try{
					
					createDRSSummaryTable();
				}catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
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
					
						cbDRSAttendanceBranch.add(stations[i].getName()+" - "+branch_code);
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

	
	private void createDRSSummaryTable() throws Exception {

		if (null != tblDRSAttendance)
			tblDRSAttendance.dispose();

		tblDRSAttendance = new Table(cvsDRSAttendance, SWT.FULL_SELECTION
				| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);

		tblDRSAttendance.setHeaderVisible(true);
		tblDRSAttendance.setLinesVisible(true);
		tblDRSAttendance.setBounds(10, 72, 800, 400);
		{
			colDRSsno = new TableColumn(tblDRSAttendance, SWT.NONE);
			colDRSsno.setText("S NO");
			colDRSsno.setWidth(40);

		}
		{
			colDRSBranchCode = new TableColumn(tblDRSAttendance, SWT.NONE);
			colDRSBranchCode.setText("Branch Code");
			colDRSBranchCode.setWidth(80);

		}
		{
			colDRSStationCode = new TableColumn(tblDRSAttendance, SWT.NONE);
			colDRSStationCode.setText("Station Code");
			colDRSStationCode.setWidth(80);
		}
		{

			String DATE_FORMAT = "dd";

			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
					DATE_FORMAT);
			String DATE_FORMAT1 = "dd-MM-yyyy";
			java.text.SimpleDateFormat sdf1 = new java.text.SimpleDateFormat(
					DATE_FORMAT1);

			Calendar c1 = Calendar.getInstance();
			c1.setTime(sdf1.parse("01-" + txtDRSMonth.getText()));
			int month = c1.getActualMaximum(Calendar.DATE);

			int i = 0;
			do {
				if (i == 0)
					c1.add(Calendar.DATE, 0);
				else
					c1.add(Calendar.DATE, 1);
				i++;

				colDRSMon1Ontime = new TableColumn(tblDRSAttendance, SWT.NONE);
				colDRSMon1Ontime.setText(sdf.format(c1.getTime()));
				colDRSMon1Ontime.setWidth(40);

			} while ((i < month));
		}
		//populateStationOrBranchForDRS();
	}

	private void createDRSPercentReportTable() throws ParseException {

		if (null != tblDRSAttendance)
			tblDRSAttendance.dispose();

		tblDRSAttendance = new Table(cvsDRSAttendance, SWT.FULL_SELECTION
				| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);

		tblDRSAttendance.setHeaderVisible(true);
		tblDRSAttendance.setLinesVisible(true);
		tblDRSAttendance.setBounds(30, 70, 750, 400);
		{
			colDRSsno = new TableColumn(tblDRSAttendance, SWT.NONE);
			colDRSsno.setText("S NO");
			colDRSsno.setWidth(40);

		}
		{
			colDRSBranchCode = new TableColumn(tblDRSAttendance, SWT.NONE);
			colDRSBranchCode.setText("Branch Code");
			colDRSBranchCode.setWidth(80);

		}
		{
			colDRSStationCode = new TableColumn(tblDRSAttendance, SWT.NONE);
			colDRSStationCode.setText("Station Code");
			colDRSStationCode.setWidth(80);
		}

		String DATE_FORMAT = "MMM yyyy";
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				DATE_FORMAT);

		String DATE_FORMAT1 = "dd-MM-yyyy";
		java.text.SimpleDateFormat sdf1 = new java.text.SimpleDateFormat(
				DATE_FORMAT1);

		Calendar c1 = Calendar.getInstance();
		// c1.setTime(new Date());
		c1.setTime(sdf1.parse("01-" + txtDRSMonth.getText()));

		for (int j = 3; j > 0; j--) {
			if (j == 3)
				c1.add(Calendar.MONTH, 0);
			else
				c1.add(Calendar.MONTH, -1);

			colDRSMon1Ontime = new TableColumn(tblDRSAttendance, SWT.NONE);
			colDRSMon1Ontime.setText(sdf.format(c1.getTime()) + " Ontime");
			colDRSMon1Ontime.setWidth(120);

			colDRSMon1Total = new TableColumn(tblDRSAttendance, SWT.NONE);
			colDRSMon1Total.setText(sdf.format(c1.getTime()) + " Total");
			colDRSMon1Total.setWidth(120);

			colDRSMon1Percent = new TableColumn(tblDRSAttendance, SWT.NONE);
			colDRSMon1Percent.setText(sdf.format(c1.getTime()) + " %");
			colDRSMon1Percent.setWidth(150);

		}
		//populateStationOrBranchForDRS();

	}
	
	private void populateStationOrBranchForDRS() {
		try {
			if (tblDRSAttendance != null) {
				tblDRSAttendance.removeAll();

				int index = -1;
				index = cbDRSAttendanceBranch.getSelectionIndex();
				if (index > -1) {
					String branch = cbDRSAttendanceBranch.getItem(index);
					StationsDTO[] dto = null;
					index = -1;
					if (!branch.equalsIgnoreCase("All")) {
						index = branch.indexOf("-");
						branch = branch.substring(index + 1).trim();
						dto = handler.getStations(branch);
					} else if (branch.equalsIgnoreCase("All")) {
						branch = "%";
						dto = handler.getAllStations();
					}

					if (null != dto) {
						int len = dto.length;
						int cols = tblDRSAttendance.getColumnCount();
						for (int i = 0; i < len; i++) {
							TableItem item = new TableItem(tblDRSAttendance,
									SWT.NONE);
							item.setText(0, String.valueOf(i + 1));
							item.setText(1, dto[i].getBranch_code());
							item.setText(2, dto[i].getId());
							for (int j = 3; j < cols; j++) {
								item.setText(j, "0");
							}
						}
						
						
					}

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getDRSAttendance() {

		try {

			String monthyear = txtDRSMonth.getText();
			int index = monthyear.indexOf("-");
			int month = Integer.parseInt(monthyear.substring(0, index).trim());
			int year = Integer.parseInt(monthyear.substring(index + 1).trim());

			if (null != handler) {
				 //System.out.println(month + " " + year);
				DRSAttendanceDTO[] dto = handler.getDRSAttendance(
						cbDRSAReportType.getSelectionIndex(),
						getSelectedBranch(cbDRSAttendanceBranch), month, year);
				if (null != dto) {
					populateDRSAttendance(cbDRSAReportType.getSelectionIndex(),
							dto, month, year);
				}
				else
				{
					populateDRSAttendancenull(cbDRSAReportType.getSelectionIndex(),
							dto, month, year);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String getSelectedBranch(Combo cb) throws Exception {
		int index = cb.getSelectionIndex();
		if (index > -1) {
			if (index == 0){
				String branch = cb.getItem(index);
				if(branch.equalsIgnoreCase("All")){
					return "%";
				}else{
					
					index = branch.indexOf("-");
					return branch = branch.substring(index + 1).trim();
				}
				
			}
				
			else {
				String branch = cb.getItem(index);
				index = 0;
				index = branch.indexOf("-");
				return branch = branch.substring(index + 1).trim();
			}

		} else {

			throw new Exception();
		}

	}
	
	private void populateDRSAttendancenull(int type, DRSAttendanceDTO[] dto,
			int month, int year) throws Exception {
		if (tblDRSAttendance != null) {

			/*int len = dto.length;
			/SimpleDateFormat monthformate = new SimpleDateFormat("MM");
			SimpleDateFormat dayformate = new SimpleDateFormat("dd");*/

			TableItem[] items = tblDRSAttendance.getItems();
			int colunms = tblDRSAttendance.getColumnCount();
			for (int j = 0; j < items.length; j++) {
				for (int k = 3; k < colunms; k++) {

					items[j].setBackground(k, new UIColors().red);

				}
			}
		}
		//fillZerosOnEmpty(tblDRSAttendance);
		TableItem item1 = new TableItem(tblDRSAttendance, SWT.NONE);
		Font font1 = new Font(Display.getDefault(), "Tahoma", 8, SWT.BOLD);
		item1.setFont(font1);
		item1.setText(2, String.valueOf("Total"));
		if(type == 1 )
		{
			TableItem item2 = new TableItem(tblDRSAttendance, SWT.NONE);
			item2.setFont(font1);
			item2.setText(4, String.valueOf("Average"));
			item2.setText(5, "0");
			item2.setText(8, "0");
			item2.setText(11, "0");
		}
		for(int i=3;i<tblDRSAttendance.getColumnCount();i++)
			item1.setText(i,"0" );
		
		
	}
	
	
	
	
	
	private void populateDRSAttendance(int type, DRSAttendanceDTO[] dto,
			int month, int year) throws Exception {
		
		Integer finalvalue[] =new Integer[tblDRSAttendance.getColumnCount()];
			for(int i=0;i<tblDRSAttendance.getColumnCount();i++)
				finalvalue[i]=0;
		String month1 = "01-" + month + "-" + year;
		if (month != 1) {
			month = month - 1;
		} else {
			month = 12;
			year = year - 1;
		}
		String month2 = "01-" + month + "-" + year;

		if (month != 1) {
			month = month - 1;
		} else {
			month = 12;
			year = year - 1;
		}
		String month3 = "01-" + month + "-" + year;
		
		float average1 = 0;
		float average2 = 0;
		float average3 = 0;
		
		int count=0;
		
		if (tblDRSAttendance != null) {

			int len = dto.length;
			SimpleDateFormat monthformate = new SimpleDateFormat("MM");
			SimpleDateFormat dayformate = new SimpleDateFormat("dd");

			TableItem[] items = tblDRSAttendance.getItems();
			int colunms = tblDRSAttendance.getColumnCount();
			for (int j = 0; j < items.length; j++) {
				for (int k = 3; k < colunms; k++) {

					items[j].setBackground(k, new UIColors().red);

				}
			}
			

			
			for (int i = 0; i < len; i++) {
				
				TableItem[] item = tblDRSAttendance.getItems();
				for (int j = 0; j < item.length; j++) {
				count = item.length;  	
					String stationcode = item[j].getText(2).trim();
					if (stationcode.equalsIgnoreCase(dto[i].getStation_code())) {
						if (type == 1) {
							if (monthformate.format(getDate(month1)).equals(
									monthformate.format(dto[i].getDrs_date()))) {
								if (dto[i].getStatus()
										.equalsIgnoreCase("total")){
									item[j].setText(10, String.valueOf(dto[i]
											.getCount()));
									finalvalue[10]=finalvalue[10] + Integer.parseInt(item[j].getText(10));
									}
								else {
									item[j].setText(9, String.valueOf(dto[i]
											.getCount()));
									finalvalue[9]=finalvalue[9] +  Integer.parseInt(item[j].getText(9));
								}
							}
							if (monthformate.format(getDate(month2)).equals(
									monthformate.format(dto[i].getDrs_date()))) {
								if (dto[i].getStatus()
										.equalsIgnoreCase("total"))
								{
									
									item[j].setText(7, String.valueOf(dto[i]
											.getCount()));
									finalvalue[7]=finalvalue[7] +  Integer.parseInt(item[j].getText(7));
								}
								else {
									item[j].setText(6, String.valueOf(dto[i]
											.getCount()));
									finalvalue[6]=finalvalue[6] +  Integer.parseInt(item[j].getText(6));
								}
							}

							if (monthformate.format(getDate(month3)).equals(
									monthformate.format(dto[i].getDrs_date()))) {
								if (dto[i].getStatus()
										.equalsIgnoreCase("total"))
								{
									item[j].setText(4, String.valueOf(dto[i]
											.getCount()));
									finalvalue[4]=finalvalue[4] +  Integer.parseInt(item[j].getText(4));
								}
								else {
									item[j].setText(3, String.valueOf(dto[i]
											.getCount()));
									finalvalue[3]=finalvalue[3] +  Integer.parseInt(item[j].getText(3));
								}
							}
						} else if ((type == 0)||(type==1)) {
							
							int itIndex = Integer.parseInt(dayformate
									.format(dto[i].getDrs_date())) + 2;
							int value = dto[i].getCount();
							if (value == 0)
								item[j].setBackground(itIndex,
										new UIColors().green);
								
							else {
								
								item[j].setText(itIndex, String.valueOf(value));
								finalvalue[itIndex]=finalvalue[itIndex]+ Integer.parseInt(item[j].getText(itIndex));
								
								item[j].setBackground(itIndex,
										new UIColors().blue);
								
								                                                                                                                      
							}

						}
					}
				
				}
				
			}

		}
		average1 = finalvalue[5] / count;
		average2 = finalvalue[8] / count;
		average3 = finalvalue[11] / count;
		
		fillZerosOnEmpty(tblDRSAttendance);
		TableItem item1 = new TableItem(tblDRSAttendance, SWT.NONE);
		Font font1 = new Font(Display.getDefault(), "Tahoma", 8, SWT.BOLD);
		item1.setFont(font1);
		item1.setText(2, String.valueOf("Total"));
		for(int i=3;i<tblDRSAttendance.getColumnCount();i++)
			item1.setText(i, String.valueOf(finalvalue[i]));
		if(type == 1)
		{
			TableItem item2 = new TableItem(tblDRSAttendance, SWT.NONE);
			item2.setFont(font1);
			item2.setText(4, String.valueOf("Average"));
			item2.setText(5, String.valueOf(getRoundedValue(average1)));
			item2.setText(8, String.valueOf(getRoundedValue(average2)));
			item2.setText(11, String.valueOf(getRoundedValue(average3)));
		}
		
	}
	
	private Date getDate(String month1) throws Exception {
		SimpleDateFormat fr = new SimpleDateFormat("dd-MM-yyyy");
		return fr.parse(month1);

	}

	private String getRoundedValue(float cardRate) {
		DecimalFormat decimalFormat;
		decimalFormat = new DecimalFormat("0.00");
		cardRate = cardRate * 100;
		cardRate = (float) Math.round(cardRate);
		cardRate = cardRate / 100;

		return decimalFormat.format(cardRate);
	}
	private void fillZerosOnEmpty(Table refTbl) {
		
		TableItem[] items = refTbl.getItems();
		int len = items.length;		
		if (len > 0) {
			if(refTbl == tblDRSAttendance){
				len+=1;
			}
			for (int i = 0; i < len-1; i++) {
				for (int j = 1; j < refTbl.getColumnCount(); j++) {
					if (items[i].getText(j).equalsIgnoreCase("")
							|| items[i].getText(j).equalsIgnoreCase("0.00")) {
						items[i].setText(j, "0");
					}
				}
			}
		}
	}
	
	private String[] getDRSHeading(Table refTable){
		
		String list[] = new String[3];
		
		list[0] = txtDRSMonth.getText();
		if(cbDRSAReportType.getText().equals("Summary"))
			list[1] = "Summary";
		else
			list[1] = "%Report";
		
		String branchCode = cbDRSAttendanceBranch.getText();
		if (!branchCode.equalsIgnoreCase("All")) {
			int index = branchCode.indexOf(" - ");
			list[2] = branchCode.substring(index + 3);
		}
		else
			list[2] ="All";
			
		return list;
	}
	
	private ArrayList<TableDecorator> getLOGTable(Table refTable) {
		TableDecorator dto = null;
		ArrayList<TableDecorator> list = new ArrayList<TableDecorator>();
		int length = 0;		

		TableItem[] items = refTable.getItems();
		if (items != null) {
			length = items.length;
		}
		if (length > 0) {
			for (int i = 0; i < length; i++) {
				dto = new TableDecorator();			
				
				dto.setCol0(items[i].getText(0));
				dto.setCol1(items[i].getText(1));
				dto.setCol2(items[i].getText(2));
				dto.setCol3(items[i].getText(3));
				dto.setCol4(items[i].getText(4));
				dto.setCol5(items[i].getText(5));
				dto.setCol6(items[i].getText(6));
				dto.setCol7(items[i].getText(7));
				dto.setCol8(items[i].getText(8));
				dto.setCol9(items[i].getText(9));
				dto.setCol10(items[i].getText(10));
				dto.setCol11(items[i].getText(11));
				dto.setCol12(items[i].getText(12));
				dto.setCol13(items[i].getText(13));
				dto.setCol14(items[i].getText(14));
				dto.setCol15(items[i].getText(15));
				dto.setCol16(items[i].getText(16));
				
				dto.setCol17(items[i].getText(17));
				dto.setCol18(items[i].getText(18));
				dto.setCol19(items[i].getText(19));
				dto.setCol20(items[i].getText(20));				
				dto.setCol21(items[i].getText(21));
				dto.setCol22(items[i].getText(22));
				dto.setCol23(items[i].getText(23));
				dto.setCol24(items[i].getText(24));
				dto.setCol25(items[i].getText(25));
				dto.setCol26(items[i].getText(26));
				dto.setCol27(items[i].getText(27));
				dto.setCol28(items[i].getText(28));
				dto.setCol29(items[i].getText(29));
				dto.setCol30(items[i].getText(30));
				dto.setCol31(items[i].getText(31));
				dto.setCol32(items[i].getText(32));
				dto.setCol33(items[i].getText(33));
				dto.setCol34(items[i].getText(34));
				
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
		
	}
	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void widgetSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub
		Object source = arg0.getSource();
		if (btnDRSAttendance == source) {
			new sortListener().display("Generating Report...", STATUS_SUCCESS,
					SUCCESS_FONT_COLOR,lblStatusBar);

			populateStationOrBranchForDRS();
			getDRSAttendance();
			
			new sortListener().display("Report Loaded Successfully!",
					STATUS_SUCCESS, SUCCESS_FONT_COLOR,lblStatusBar);
		} else if (source == cbDRSAReportType) {
			try {
				int index = cbDRSAReportType.getSelectionIndex();
				if (index == 0)
					createDRSSummaryTable();
				else
					createDRSPercentReportTable();
			} catch (Exception e) {				
				e.printStackTrace();
			}

		}else if (btnDRSMonth == source) {
			MonthDialog cd = new MonthDialog(shell);
			Object obj = cd.open();
			if (obj != null) {
				txtDRSMonth.setText(obj.toString());
			}
		}
		else if (source == btnExportXL) {
			try {
				//handleExcel();
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				if (cbDRSAReportType.getText().equalsIgnoreCase("Summary")) {
					list = getLOGTable(tblDRSAttendance);
					value = getDRSHeading(tblDRSAttendance);
					TableColumn[] col = tblDRSAttendance.getColumns();
					if(col != null){
						int len = col.length;
						String param[] = new String[len];
						for(int i = 0; i < len; i++){
							param[i] = col[i].getText();
						}
						new sortListener().prepareExcel(list, "DRS Attendance", DRS_VEHICLE_EXCEL_JRXML, param,value,shell,lblStatusBar);
					}
				}
				else
				{
					value = getDRSHeading(tblDRSAttendance);
					list = getLOGTable(tblDRSAttendance);
					TableColumn[] col = tblDRSAttendance.getColumns();
					if(col != null){
						int len = col.length;
						String param[] = new String[len];
						for(int i = 0; i < len; i++){
							param[i] = col[i].getText();
						}
						new sortListener().prepareExcel(list, "DRS Attendance", DRS_VEHICLE_EXCEL_JRXML, param,value,shell,lblStatusBar);
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
				if (cbDRSAReportType.getText().equalsIgnoreCase("Summary")) {
					list = getLOGTable(tblDRSAttendance);
					value = getDRSHeading(tblDRSAttendance);
					TableColumn[] col = tblDRSAttendance.getColumns();
					if(col != null){
						int len = col.length;
						String param[] = new String[len];
						for(int i = 0; i < len; i++){
							param[i] = col[i].getText();
						}
						handler.preparePrint(list, "DRS Attendance", DRS_VEHICLE_EXCEL_JRXML, param,value);
					}
				}
				else
				{
					value = getDRSHeading(tblDRSAttendance);
					list = getLOGTable(tblDRSAttendance);
					TableColumn[] col = tblDRSAttendance.getColumns();
					if(col != null){
						int len = col.length;
						String param[] = new String[len];
						for(int i = 0; i < len; i++){
							param[i] = col[i].getText();
						}
						handler.preparePrint(list, "DRS Attendance", DRS_VEHICLE_EXCEL_JRXML, param,value);
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
				if (cbDRSAReportType.getText().equalsIgnoreCase("Summary")) {
					list = getLOGTable(tblDRSAttendance);
					value = getDRSHeading(tblDRSAttendance);
					TableColumn[] col = tblDRSAttendance.getColumns();
					if(col != null){
						int len = col.length;
						String param[] = new String[len];
						for(int i = 0; i < len; i++){
							param[i] = col[i].getText();
						}
						new sortListener().preparePDF(list, "DRS Attendance", DRS_VEHICLE_EXCEL_JRXML, param,value,shell,lblStatusBar);
					}
				}
				else
				{
					value = getDRSHeading(tblDRSAttendance);
					list = getLOGTable(tblDRSAttendance);
					TableColumn[] col = tblDRSAttendance.getColumns();
					if(col != null){
						int len = col.length;
						String param[] = new String[len];
						for(int i = 0; i < len; i++){
							param[i] = col[i].getText();
						}
						new sortListener().preparePDF(list, "DRS Attendance", DRS_VEHICLE_EXCEL_JRXML, param,value,shell,lblStatusBar);
					}
					
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		
	}
		
}
