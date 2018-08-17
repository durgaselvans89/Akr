package hm.akr.container.admin;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


import hm.akr.common.BeanUtil;
import hm.akr.common.IUIConstants;
import hm.akr.common.sortListener;
import hm.akr.container.history.HistoryHandler;
import hm.akr.dto.CustomerBusinessAnalysisDTO;
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
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class ConsignorBooking extends ReportsMenu implements IUIConstants,Listener,SelectionListener {

	
	private Label lblCnorBABranch;
	private Label lblCBAOption;
	private Combo cbCnorBABranch;
	private Label lblCnorBAMonths;
	private Combo cbCnorBAMonths;
	private Label lblCnorBAPickMonths;
	private List lstCnorPickMonths;
	private Combo cbCnorBAOption;
	
	private Button btnCnorBAView;
	private TabItem tiCnorBookingAnalysis;
	private Canvas cvsCnorBookingAnalysis;
	private Table tblCnorBookingAnalysis;
	private TableColumn colCnorBDsno;
	private TableColumn colCnorBDBranchCode;
	private TableColumn colCnorBDCnorName;
	private TableColumn colCnorBDMonths;
	private String CONSINORBA = "Consignor Booking Analysis";
	private static final String CNORBA_EXCEL_JRXML = "hm/akr/resources/printer/Cnor_BA.jrxml";
	
	private String COUNT = "Count";
	private String BASIC_FREIGHT = "Basic Freight";
	private String TOTAL_FREIGHT = "Total Freight";
	private String ACTUAL_WEIGHT = "Actual Weight";
	private String CHARGED_WEIGHT = "Charged Weight";
	private String NOA = "Noa";
	
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
	
	 public ConsignorBooking(Composite composite,int style, float clientRights) {
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

			tiCnorBookingAnalysis = new TabItem(tabReport, SWT.NONE);

			tiCnorBookingAnalysis.setText(CONSINORBA);
			try{
				cvsCnorBookingAnalysis = getCnorCanvas();
			}
			catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
			tiCnorBookingAnalysis.setControl(cvsCnorBookingAnalysis);

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
	
	private Canvas getCnorCanvas() throws Exception {

		cvsCnorBookingAnalysis = new Canvas(tabReport, SWT.NONE);

		{

			{
				tblCnorBookingAnalysis = new Table(cvsCnorBookingAnalysis,
						SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL
								| SWT.BORDER);

				tblCnorBookingAnalysis.setHeaderVisible(true);
				tblCnorBookingAnalysis.setLinesVisible(true);
				tblCnorBookingAnalysis.setBounds(30, 100, 710, 350);

			}
			{
				colCnorBDsno = new TableColumn(tblCnorBookingAnalysis, SWT.NONE);
				colCnorBDsno.setText("S NO");
				colCnorBDsno.setWidth(40);

			}
			{
				colCnorBDBranchCode = new TableColumn(tblCnorBookingAnalysis,
						SWT.NONE);
				colCnorBDBranchCode.setText("Branch Code");
				colCnorBDBranchCode.setWidth(80);

			}
			{
				colCnorBDCnorName = new TableColumn(tblCnorBookingAnalysis,
						SWT.NONE);
				colCnorBDCnorName.setText("Cnor Name");
				colCnorBDCnorName.setWidth(100);

			}
						
			{
				lblCnorBABranch = new Label(cvsCnorBookingAnalysis, SWT.NONE);
				lblCnorBABranch.setText("Select Branch");
				lblCnorBABranch.setBounds(31, 59, 74, 21);
			}
			{
				cbCnorBABranch = new Combo(cvsCnorBookingAnalysis, SWT.READ_ONLY);
				cbCnorBABranch.setBounds(110, 59, 89, 21);
				if(clientRights == 1 || clientRights == 1.0){
					
					try{
					
						getCurrentBranch_code(currentStationCode);
						cbCnorBABranch.select(0);
						//populateStationForCLR();
						cbCnorBABranch.addSelectionListener(this);
					}
					catch(Exception e){
						e.printStackTrace();
					}
					
					
				}
				else{
				
					try{
						StationsDTO[] dto = handler.getAllBranches();
						if (null != dto) {
							cbCnorBABranch.add("All");
							for (int i = 0; i < dto.length; i++) {
								cbCnorBABranch.add(dto[i].getName() + " - "
										+ dto[i].getId());
							}
						}
					}
					catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
			}

			{
				lblCnorBAMonths = new Label(cvsCnorBookingAnalysis, SWT.NONE);
				lblCnorBAMonths.setText("Select n Months");
				lblCnorBAMonths.setBounds(210, 60, 79, 21);
			}
			{
				cbCnorBAMonths = new Combo(cvsCnorBookingAnalysis, SWT.NONE);
				cbCnorBAMonths.setBounds(294, 59, 75, 21);
				for (int i = 1; i < 13; i++) {
					cbCnorBAMonths.add(String.valueOf(i));
				}
				cbCnorBAMonths.addSelectionListener(this);
			}

			{
				lblCnorBAPickMonths = new Label(cvsCnorBookingAnalysis,
						SWT.NONE);
				lblCnorBAPickMonths.setText("Pick Months");
				lblCnorBAPickMonths.setBounds(379, 59, 60, 21);
			}

			{
				lstCnorPickMonths = new List(cvsCnorBookingAnalysis, SWT.BORDER
						| SWT.MULTI | SWT.V_SCROLL);
				lstCnorPickMonths.setBounds(440, 14, 138, 83);
				lstCnorPickMonths.addSelectionListener(this);

			}
			{
				cbCnorBAOption = new Combo(cvsCnorBookingAnalysis,
						SWT.READ_ONLY);
				cbCnorBAOption.setBounds(627, 39, 110, 25);
				cbCnorBAOption.add(COUNT);
				cbCnorBAOption.add(BASIC_FREIGHT);
				cbCnorBAOption.add(TOTAL_FREIGHT);
				cbCnorBAOption.add(ACTUAL_WEIGHT);
				cbCnorBAOption.add(CHARGED_WEIGHT);
				cbCnorBAOption.add(NOA);
				// cbOption.select(0);

			}
			{
				btnCnorBAView = new Button(cvsCnorBookingAnalysis, SWT.PUSH
						| SWT.CENTER);
				btnCnorBAView.setText("View");
				btnCnorBAView.setBounds(679, 65, 60, 30);
				btnCnorBAView.addSelectionListener(this);
			}

		}
		return cvsCnorBookingAnalysis;
	}
	
	private void createCnorBATable() {
		int[] indexs = lstCnorPickMonths.getSelectionIndices();
		String[] months = null;
		int len = indexs.length;
		if (len > 0) {
			months = new String[len];

			if (null != tblCnorBookingAnalysis)
				tblCnorBookingAnalysis.dispose();

			{
				tblCnorBookingAnalysis = new Table(cvsCnorBookingAnalysis,
						SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL
								| SWT.BORDER);

				tblCnorBookingAnalysis.setHeaderVisible(true);
				tblCnorBookingAnalysis.setLinesVisible(true);
				tblCnorBookingAnalysis.setBounds(30, 100, 710, 350);

			}
			{
				lblCBAOption = new Label(cvsCnorBookingAnalysis, SWT.NONE);
				lblCBAOption.setText("Select Option");
				lblCBAOption.setBounds(627, 13, 72, 22);
			}
			{
				colCnorBDsno = new TableColumn(tblCnorBookingAnalysis, SWT.NONE);
				colCnorBDsno.setText("S NO");
				colCnorBDsno.setWidth(40);

			}
			{
				colCnorBDBranchCode = new TableColumn(tblCnorBookingAnalysis,
						SWT.NONE);
				colCnorBDBranchCode.setText("Branch Code");
				colCnorBDBranchCode.setWidth(80);

			}
			{
				colCnorBDCnorName = new TableColumn(tblCnorBookingAnalysis,
						SWT.NONE);
				colCnorBDCnorName.setText("Cnor Name");
				colCnorBDCnorName.setWidth(100);

			}
			for (int i = 0; i < len; i++) {
				months[i] = lstCnorPickMonths.getItem(indexs[i]);
				{
					colCnorBDMonths = new TableColumn(tblCnorBookingAnalysis,
							SWT.NONE);
					colCnorBDMonths.setText(months[i]);
					colCnorBDMonths.setWidth(80);

				}

			}
			for (int index = 0; index < tblCnorBookingAnalysis.getColumnCount(); index++) {
				tblCnorBookingAnalysis.getColumn(index).addListener(
						SWT.Selection, this);
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
					
						cbCnorBABranch.add(stations[i].getName()+" - "+branch_code);
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


	
	private TableItem isAlreadyAvail(Table table, String valueOf, String station) {
		TableItem[] items = table.getItems();
		if (items != null) {
			int len = items.length;
			if (len > 0) {
				for (int i = 0; i < len; i++) {
					String itemText = items[i].getText(2);
					String itemStation = items[i].getText(1);
					if (valueOf.equalsIgnoreCase(itemText)
							&& station.equalsIgnoreCase(itemStation)) {
												
						return items[i];
					}
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
	
	private String[] getconsinorbaHeading(Table refTable){
		
		String list[] = new String[4];
		int[] indexs = lstCnorPickMonths.getSelectionIndices();
		int len = indexs.length;
		String[] months = new String[len];
		list[2] = " ";
		
		String branchCode = cbCnorBABranch.getText();
		if (!branchCode.equalsIgnoreCase("All")) {
			int index = branchCode.indexOf(" - ");
			list[0] = branchCode.substring(index + 3);
		}
		else
			list[0] ="All";
		list[1] = cbCnorBAMonths.getText();
		for (int i = 0; i < len; i++){ 
			months[i] = lstCnorPickMonths.getItem(indexs[i]);
			
			list[2] = list[2]+months[i];
		}
		list[3] = cbCnorBAOption.getText();
		
			
		return list;
	}
	@Override
	public void handleEvent(Event e) {
		// TODO Auto-generated method stub
		String selection = tabReport.getSelection()[0].getText();
		TableColumn column = (TableColumn) e.widget;
		TableColumn[] cols = tblCnorBookingAnalysis.getColumns();
		int len = cols.length;

		for (int i = 0; i < len; i++) {
			if (column == cols[i]) {
				if (i == 1 || i == 2) {
					new sortListener().sortByString(i, tblCnorBookingAnalysis);
				} else if (i == 0 || i > 2) {
					new sortListener().sortByFloat(i, tblCnorBookingAnalysis);
				}
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
		if (source == cbCnorBAMonths) {
			String DATE_FORMAT = "MMM yyyy";
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
					DATE_FORMAT);
			Calendar c1 = Calendar.getInstance();
			int index = cbCnorBAMonths.getSelectionIndex();
			
			if (index > -1) {
				String noMonth = cbCnorBAMonths.getItem(index);
				
				int temp = Integer.parseInt(noMonth );
				temp = temp - 1;
				lstCnorPickMonths.removeAll();
				for (int i = temp; i >= 0; i--) {
					if (i == temp)
						c1.add(Calendar.MONTH, -(temp));
					else
						c1.add(Calendar.MONTH, 1);
					lstCnorPickMonths.add(sdf.format(c1.getTime()));
				}
				

				try {
					int count = lstCnorPickMonths.getItemCount();
					String[] list = new String[count];
					
					for(int i = 0,k = count-1; k>=0; k--,i++ ){
						list[i] = lstCnorPickMonths.getItem(k);
						
						 						
					}
					
					lstCnorPickMonths.removeAll();
					lstCnorPickMonths.setItems(list);					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else if (source == lstCnorPickMonths) {
			createCnorBATable();

		} else if (btnCnorBAView == source) {
			new sortListener().display("Generating Report...", STATUS_SUCCESS,
					SUCCESS_FONT_COLOR,lblStatusBar);

			try {
				String Date_format = "yyyy-MM-dd";
				java.text.SimpleDateFormat sdf2 = new java.text.SimpleDateFormat(Date_format);
				
				String DATE_FORMAT = "yyyy-MM";
				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
						DATE_FORMAT);
				Calendar c1 = Calendar.getInstance();
				int index = cbCnorBAMonths.getSelectionIndex();
				
				
				if (index > -1) {
					String noMonth = cbCnorBAMonths.getItem(index); 
									
					int temp = Integer.parseInt(noMonth);
				
					/*String to_date = sdf2.format(c1.getTime());
					//Date d = new Date();
					//String to_date = sdf2.format(d);
					c1.add(Calendar.MONTH, -(temp - 1));
					String from_date = sdf.format(c1.getTime()) + "-01";*/
					//c1.add(Calendar.MONTH, temp - 1);
					//String to_date = sdf.format(c1.getTime()) + "-30";
					int dat[] =  lstCnorPickMonths.getSelectionIndices();
					c1.add(Calendar.MONTH, -(dat[0]));
					String to_date = sdf.format(c1.getTime()) + "-31";
					Calendar c2 = Calendar.getInstance();
					c2.add(Calendar.MONTH, -(dat[lstCnorPickMonths.getSelectionCount() - 1] ));
					String from_date = sdf.format(c2.getTime()) + "-01";
					//System.out.println("from_date-->"+from_date);
					//System.out.println("to_date-->"+to_date);
			
					int bIndex = cbCnorBABranch.getSelectionIndex();
					String branch = null;
					if (bIndex > -1) {
						branch = cbCnorBABranch.getItem(bIndex);
					}
					if (branch != null && branch.equalsIgnoreCase("All")) {
						branch = "%";
					} else if (branch != null) {
						int hIndex = branch.indexOf("-");
						branch = branch.substring(hIndex + 1).trim();
					}
					//CustomerBusinessAnalysisDTO[] dto =  handler.getCustomerBusinessAnalysisReport(true, branch, null , null );
					CustomerBusinessAnalysisDTO[] dto =  handler.getCnorBusinessAnalysisReport(true, branch,from_date, to_date);
					if (tblCnorBookingAnalysis != null)
						tblCnorBookingAnalysis.removeAll();

					String DATE_FORMAT1 = "MMM yyyy";
					java.text.SimpleDateFormat sdf1 = new java.text.SimpleDateFormat(
							DATE_FORMAT1);
					if (null != dto) {
						
						int len = dto.length;
						for (int i = 0; i < len; i++) {
							int k = 0;
							String col_date = sdf1.format(dto[i].getLr_date());
							int count = tblCnorBookingAnalysis.getColumnCount();
							for (int j = 0; j < count; j++) {
								
								int selectedIndex = -1;
																
								if (col_date
										.equalsIgnoreCase(tblCnorBookingAnalysis
												.getColumn(j).getText())) {
									
									selectedIndex = cbCnorBAOption
											.getSelectionIndex();
									int noa = 0;
									float bft = 0;
									if (selectedIndex > -1) {
										TableItem item = null;
										item = isAlreadyAvail(tblCnorBookingAnalysis,String.valueOf(dto[i].getCustomerName()),String.valueOf(dto[i].getFromStation()));
										if (item == null) {
											item = new TableItem(tblCnorBookingAnalysis,SWT.NONE);
											
										}
										
										
										
										item.setText(1, String.valueOf(dto[i]
												.getFromStation()));
										String tempUnit = cbCnorBAOption
										.getItem(selectedIndex);
										boolean istrue = false;
										item.setText(2, String.valueOf(dto[i].getCustomerName()));
											
											
											String join = null;
											if (tempUnit != null	&& tempUnit.equalsIgnoreCase(COUNT)) {
												//join = item.getText(j);
												//if (item.getText(j).equalsIgnoreCase(""))
													join = "0";
												item.setText(j,String.valueOf(Integer.parseInt(join)	+ dto[i].getCount()));
												
											} else if (tempUnit != null
													&& tempUnit
															.equalsIgnoreCase(BASIC_FREIGHT)) {
												//join = item.getText(j);
												//if (join.equalsIgnoreCase(""))
													join = "0";
												item.setText(j,getRoundedValue(Integer.parseInt(join) + dto[i].getBasic_freight()));
											
											} else if (tempUnit != null	&& tempUnit.equalsIgnoreCase(TOTAL_FREIGHT)) {
												//join = item.getText(j);
												//if (join.equalsIgnoreCase(""))
												join = "0";
												item.setText(j,getRoundedValue(Integer.parseInt(join)	+ dto[i].getTotal_freight()));
											
											} else if (tempUnit != null && tempUnit.equalsIgnoreCase(ACTUAL_WEIGHT)) {
												//join = item.getText(j);
												//if (join.equalsIgnoreCase(""))
													join = "0";
												item.setText(j,getRoundedValue(Integer.parseInt(join)+ dto[i].getActual_weight()));
											
											} else if (tempUnit != null	&& tempUnit.equalsIgnoreCase(CHARGED_WEIGHT)) {
												//join = item.getText(j);
												//if (join.equalsIgnoreCase(""))
													join = "0";
												item.setText(j,getRoundedValue(Integer.parseInt(join)+ dto[i].getCharged_weight()));
											
											} else if (tempUnit != null	&& tempUnit.equalsIgnoreCase(NOA)) {
												//join = item.getText(j);
												//if (join.equalsIgnoreCase(""))
													join = "0";
												item.setText(j, String.valueOf(Integer.parseInt(join)+ dto[i].getNoa()));
											}
									}
									
								}
							}
							
						}
						
						int column = tblCnorBookingAnalysis.getColumnCount();

						TableItem[] items = tblCnorBookingAnalysis.getItems();
						int size = items.length;
						for (int p = 0; p < size; p++) {
							if(items[p].getText().equalsIgnoreCase("")){
								items[p].setText(0,String.valueOf(p + 1));
								
							}
							for (int cl = 3; cl < column; cl++) {
								
								if (items[p].getText(cl).equalsIgnoreCase("")) {
									items[p].setText(cl, "0");
									
									
								}
							}
						}

						float[] total = new float[column];
						for (int i = 3; i < column; i++) {
							// TableItem
							TableItem[] item = tblCnorBookingAnalysis
									.getItems();
							if (item != null) {
								int len1 = item.length;
								for (int j = 0; j < len1; j++) {
									total[i] = total[i]
											+ Float.parseFloat(item[j]
													.getText(i));
									//System.out.println(item[j].getText(i));
								}
							}
						}
						TableItem item = new TableItem(tblCnorBookingAnalysis,
								SWT.NONE);
						Font font1 = new Font(Display.getCurrent(), "Tahoma", 8, SWT.BOLD);
						item.setFont(font1);
						item.setText(2, String.valueOf("Total"));
						for (int i = 3; i < column; i++) {
							if(cbCnorBAOption.getText().equalsIgnoreCase("Count") || 
									cbCnorBAOption.getText().equalsIgnoreCase("Noa")){
								item.setText(i, String.valueOf(total[i]));
							}else{
								item.setText(i, getRoundedValue(total[i]));
							}							
						}
					}

				}
			} catch (NumberFormatException e) {				
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			new sortListener().display("Report Loaded Successfully!",
					STATUS_SUCCESS, SUCCESS_FONT_COLOR,lblStatusBar);
		}
		else if (source == btnExportXL) {
			try {
				//handleExcel();
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				list = new sortListener().getBPATable(tblCnorBookingAnalysis);
				value = getconsinorbaHeading(tblCnorBookingAnalysis);
				TableColumn[] col = tblCnorBookingAnalysis.getColumns();
				if(col != null){
					int len = col.length;
					String param[] = new String[len];
					for(int i = 0; i < len; i++){
						param[i] = col[i].getText();
					}
					new sortListener().prepareExcel(list, "CnorBookingAnalysis", CNORBA_EXCEL_JRXML, param,value,shell,lblStatusBar);
				}			
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else if (source == btnPrint) {
			try {
				//handlePrint();
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				list = new sortListener().getBPATable(tblCnorBookingAnalysis);
				value = getconsinorbaHeading(tblCnorBookingAnalysis);
				TableColumn[] col = tblCnorBookingAnalysis.getColumns();
				if(col != null){
					int len = col.length;
					String param[] = new String[len];
					for(int i = 0; i < len; i++){
						param[i] = col[i].getText();
					}
					handler.preparePrint(list, "CnorBookingAnalysis", CNORBA_EXCEL_JRXML, param,value);
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
				list = new sortListener().getBPATable(tblCnorBookingAnalysis);
				value = getconsinorbaHeading(tblCnorBookingAnalysis);
				TableColumn[] col = tblCnorBookingAnalysis.getColumns();
				if(col != null){
					int len = col.length;
					String param[] = new String[len];
					for(int i = 0; i < len; i++){
						param[i] = col[i].getText();
					}
					new sortListener().preparePDF(list, "CnorBookingAnalysis", CNORBA_EXCEL_JRXML, param,value,shell,lblStatusBar);
				}			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		
	}

}
