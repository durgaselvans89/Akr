package hm.akr.container.admin;


import hm.akr.common.BeanUtil;
import hm.akr.common.IUIConstants;
import hm.akr.common.sortListener;
import hm.akr.container.history.HistoryHandler;
import hm.akr.dto.CustomerBusinessAnalysisDTO;
import hm.akr.dto.StationsDTO;
import hm.akr.dto.TableDecorator;
import hm.akr.dto.VersionDTO;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

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

public class ConsigneeBooking extends ReportsMenu implements IUIConstants,Listener,SelectionListener {
	
	private Label lblCneeBABranch;
	private Label lblCBAOption;
	private Combo cbCneeBABranch;
	private Label lblCneeBAMonths;
	private Combo cbCneeBAMonths;
	private Label lblCneeBAPickMonths;
	private List lstCneePickMonths;
	private Combo cbCneeBAOption;
	private Button btnCneeBAView;
	private Canvas cvsCneeBookingAnalysis;
	private TabItem tiCneeBookingAnalysis;
	private TableColumn colCneeBDCneeName;
	private TableColumn colCneeBDBranchCode;
	private Table tblCneeBookingAnalysis;
	private TableColumn colCneeBDsno;
	private TableColumn colCneeBDMonth4;
	private TableColumn colCnorBDMonths;
	private String CONSINEEBA = "Consignee Booking Analysis";
	private static final String CNEEBA_EXCEL_JRXML = "hm/akr/resources/printer/Cnee_BA.jrxml";
	
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
	
	 public ConsigneeBooking(Composite composite,int style, float clientRights) {
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

			tiCneeBookingAnalysis = new TabItem(tabReport, SWT.NONE);

			tiCneeBookingAnalysis.setText(CONSINEEBA);
			try{
				cvsCneeBookingAnalysis = getCneeCanvas();
			}
			catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
			tiCneeBookingAnalysis.setControl(cvsCneeBookingAnalysis);

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
	
	
	private Canvas getCneeCanvas() throws Exception {

		cvsCneeBookingAnalysis = new Canvas(tabReport, SWT.NONE);

		{

			{
				tblCneeBookingAnalysis = new Table(cvsCneeBookingAnalysis,
						SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL
								| SWT.BORDER);

				tblCneeBookingAnalysis.setHeaderVisible(true);
				tblCneeBookingAnalysis.setLinesVisible(true);
				tblCneeBookingAnalysis.setBounds(30, 100, 710, 350);

			}
			{
				colCneeBDsno = new TableColumn(tblCneeBookingAnalysis, SWT.NONE);
				colCneeBDsno.setText("S NO");
				colCneeBDsno.setWidth(40);

			}
			{
				colCneeBDBranchCode = new TableColumn(tblCneeBookingAnalysis,
						SWT.NONE);
				colCneeBDBranchCode.setText("Branch Code");
				colCneeBDBranchCode.setWidth(80);

			}
			{
				colCneeBDCneeName = new TableColumn(tblCneeBookingAnalysis,
						SWT.NONE);
				colCneeBDCneeName.setText("Cnee Name");
				colCneeBDCneeName.setWidth(100);

			}

			{
				lblCneeBABranch = new Label(cvsCneeBookingAnalysis, SWT.NONE);
				lblCneeBABranch.setText("Select Branch");
				lblCneeBABranch.setBounds(31, 59, 74, 21);
			}
			
			{
				cbCneeBABranch = new Combo(cvsCneeBookingAnalysis, SWT.READ_ONLY);
				cbCneeBABranch.setBounds(110, 59, 89, 21);
				if(clientRights == 1 || clientRights == 1.0){
					
					try{
					
						getCurrentBranch_code(currentStationCode);
						cbCneeBABranch.select(0);
						//populateStationForCLR();
						cbCneeBABranch.addSelectionListener(this);
					}
					catch(Exception e){
						e.printStackTrace();
					}
					
					
				}
				else{
				
					try{
				
						StationsDTO[] dto = handler.getAllBranches();
						if (null != dto) {
							cbCneeBABranch.add("All");
							for (int i = 0; i < dto.length; i++) {
								cbCneeBABranch.add(dto[i].getName() + " - "
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
				lblCneeBAMonths = new Label(cvsCneeBookingAnalysis, SWT.NONE);
				lblCneeBAMonths.setText("Select n Months");
				lblCneeBAMonths.setBounds(210, 60, 79, 21);
			}
			{
				cbCneeBAMonths = new Combo(cvsCneeBookingAnalysis, SWT.NONE);
				cbCneeBAMonths.setBounds(294, 59, 75, 21);
				for (int i = 1; i < 13; i++) {
					cbCneeBAMonths.add(String.valueOf(i));
				}
				cbCneeBAMonths.addSelectionListener(this);
			}

			{
				lblCneeBAPickMonths = new Label(cvsCneeBookingAnalysis,
						SWT.NONE);
				lblCneeBAPickMonths.setText("Pick Months");
				lblCneeBAPickMonths.setBounds(379, 59, 60, 21);
			}

			{
				lstCneePickMonths = new List(cvsCneeBookingAnalysis, SWT.BORDER
						| SWT.MULTI | SWT.V_SCROLL);
				lstCneePickMonths.setBounds(440, 14, 138, 83);
				lstCneePickMonths.addSelectionListener(this);

			}
			{
				cbCneeBAOption = new Combo(cvsCneeBookingAnalysis,
						SWT.READ_ONLY);
				cbCneeBAOption.setBounds(627, 39, 110, 25);
				cbCneeBAOption.add(COUNT);
				cbCneeBAOption.add(BASIC_FREIGHT);
				cbCneeBAOption.add(TOTAL_FREIGHT);
				cbCneeBAOption.add(ACTUAL_WEIGHT);
				cbCneeBAOption.add(CHARGED_WEIGHT);
				cbCneeBAOption.add(NOA);
				// cbOption.select(0);

			}
			{
				btnCneeBAView = new Button(cvsCneeBookingAnalysis, SWT.PUSH
						| SWT.CENTER);
				btnCneeBAView.setText("View");
				btnCneeBAView.setBounds(679, 65, 60, 30);
				btnCneeBAView.addSelectionListener(this);
			}

		}
		return cvsCneeBookingAnalysis;
	}

	private void createCneeBATable() {
		int[] indexs = lstCneePickMonths.getSelectionIndices();
		String[] months = null;
		int len = indexs.length;
		if (len > 0) {
			months = new String[len];

			if (null != tblCneeBookingAnalysis)
				tblCneeBookingAnalysis.dispose();

			{
				tblCneeBookingAnalysis = new Table(cvsCneeBookingAnalysis,
						SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL
								| SWT.BORDER);

				tblCneeBookingAnalysis.setHeaderVisible(true);
				tblCneeBookingAnalysis.setLinesVisible(true);
				tblCneeBookingAnalysis.setBounds(30, 100, 710, 350);

			}
			{
				lblCBAOption = new Label(cvsCneeBookingAnalysis, SWT.NONE);
				lblCBAOption.setText("Select Option");
				lblCBAOption.setBounds(627, 13, 72, 22);
			}
			{
				colCneeBDsno = new TableColumn(tblCneeBookingAnalysis, SWT.NONE);
				colCneeBDsno.setText("S NO");
				colCneeBDsno.setWidth(40);

			}
			{
				colCneeBDBranchCode = new TableColumn(tblCneeBookingAnalysis,
						SWT.NONE);
				colCneeBDBranchCode.setText("Branch Code");
				colCneeBDBranchCode.setWidth(80);

			}
			{
				colCneeBDCneeName = new TableColumn(tblCneeBookingAnalysis,
						SWT.NONE);
				colCneeBDCneeName.setText("Cnee Name");
				colCneeBDCneeName.setWidth(100);

			}
			for (int i = 0; i < len; i++) {
				months[i] = lstCneePickMonths.getItem(indexs[i]);
				{
					colCneeBDMonth4 = new TableColumn(tblCneeBookingAnalysis,
							SWT.NONE);
					colCneeBDMonth4.setText(months[i]);
					colCneeBDMonth4.setWidth(80);

				}

			}
			for (int index = 0; index < tblCneeBookingAnalysis.getColumnCount(); index++) {
				tblCneeBookingAnalysis.getColumn(index).addListener(
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
					
						cbCneeBABranch.add(stations[i].getName()+" - "+branch_code);
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
	
	private String[] getConsineebaHeading(Table refTable){
		
		String list[] = new String[4];
		int[] indexs = lstCneePickMonths.getSelectionIndices();
		int len = indexs.length;
		String[] months = new String[len];
		list[2] = " ";			
		
		String branchCode = cbCneeBABranch.getText();
		if (!branchCode.equalsIgnoreCase("All")) {
			int index = branchCode.indexOf(" - ");
			list[0] = branchCode.substring(index + 3);
		}
		else
			list[0] ="All";
		list[1] =cbCneeBAMonths.getText();
		for (int i = 0; i < len; i++){ 
			months[i] = lstCneePickMonths.getItem(indexs[i]);
			list[2] = list[2] + months[i];
		}
		list[3] = cbCneeBAOption.getText();
		
			
		return list;
	}
	@Override
	public void handleEvent(Event e) {
		// TODO Auto-generated method stub
		String selection = tabReport.getSelection()[0].getText();
		TableColumn column = (TableColumn) e.widget;
		//else if (selection == CONSINEEBA) {
			TableColumn[] cols = tblCneeBookingAnalysis.getColumns();
			int len = cols.length;

			for (int i = 0; i < len; i++) {
				if (column == cols[i]) {
					if (i == 1 || i == 2) {
						new sortListener().sortByString(i, tblCneeBookingAnalysis);
					} else if (i == 0 || i > 2) {
						new sortListener().sortByFloat(i, tblCneeBookingAnalysis);
					}
				}

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
		
		if (source == cbCneeBAMonths) {
			String DATE_FORMAT = "MMM yyyy";
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
					DATE_FORMAT);
			Calendar c1 = Calendar.getInstance();
			int index = cbCneeBAMonths.getSelectionIndex();
			if (index > -1) {
				String noMonth = cbCneeBAMonths.getItem(index);
				int temp = Integer.parseInt(noMonth) ;
				temp = temp - 1;
				lstCneePickMonths.removeAll();
				for (int i = temp; i >= 0; i--) {
					
					if (i == temp)
						c1.add(Calendar.MONTH, -(temp));
						
					else
						c1.add(Calendar.MONTH, 1);
					
				lstCneePickMonths.add((sdf.format(c1.getTime())));
				}
				
				
				try {
					int count = lstCneePickMonths.getItemCount();
					String[] list = new String[count];
					
					for(int i = 0,k = count-1; k>=0; k--,i++ ){
						list[i] = lstCneePickMonths.getItem(k);
						
						 						
					}
					
					lstCneePickMonths.removeAll();
					lstCneePickMonths.setItems(list);					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
								
			}	
				
			
		}  else if (source == lstCneePickMonths) {
			createCneeBATable();

		} else if (source == btnCneeBAView) {
			new sortListener().display("Generating Report...", STATUS_SUCCESS,
					SUCCESS_FONT_COLOR,lblStatusBar);

			try {
				String Date_format = "yyyy-MM-dd";
				java.text.SimpleDateFormat sdf2 = new java.text.SimpleDateFormat(Date_format);
				String DATE_FORMAT = "yyyy-MM";
				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
						DATE_FORMAT);
				Calendar c1 = Calendar.getInstance();
				int index = cbCneeBAMonths.getSelectionIndex();
				if (index > -1) {
					String noMonth = cbCneeBAMonths.getItem(index);
					int temp = Integer.parseInt(noMonth) ;
												
					/*String to_date = sdf2.format(c1.getTime());
					c1.add(Calendar.MONTH, -(temp - 1));
					String from_date = sdf.format(c1.getTime()) + "-01";
					
					//c1.add(Calendar.MONTH, temp - 1);
					//String to_date = sdf.format(c1.getTime()) + "-30";*/
					int dat[] =  lstCneePickMonths.getSelectionIndices();
					c1.add(Calendar.MONTH, -(dat[0]));
					String to_date = sdf.format(c1.getTime()) + "-31";
					Calendar c2 = Calendar.getInstance();
					c2.add(Calendar.MONTH, -(dat[lstCneePickMonths.getSelectionCount() - 1] ));
					String from_date = sdf.format(c2.getTime()) + "-01";
					//System.out.println("from_date-->"+from_date);
					//System.out.println("to_date-->"+to_date);
					int bIndex = cbCneeBABranch.getSelectionIndex();
					String branch = null;
					if (bIndex > -1) {
						branch = cbCneeBABranch.getItem(bIndex);
					}
					if (branch != null && branch.equalsIgnoreCase("All")) {
						branch = "%";
					} else if (branch != null) {
						int hIndex = branch.indexOf("-");
						branch = branch.substring(hIndex + 1).trim();
					}
					CustomerBusinessAnalysisDTO[] dto = handler.getCneeBusinessAnalysisReport(false, branch,from_date, to_date);
					if (tblCneeBookingAnalysis != null)
						tblCneeBookingAnalysis.removeAll();

					String DATE_FORMAT1 = "MMM yyyy";
					java.text.SimpleDateFormat sdf1 = new java.text.SimpleDateFormat(DATE_FORMAT1);
					if (null != dto) {
						int len = dto.length;
						for (int i = 0; i < len; i++) {
							
							String col_date = sdf1.format(dto[i].getLr_date());
							int count = tblCneeBookingAnalysis.getColumnCount();
							for (int j = 0; j < count; j++) {
								
								int selectedIndex = -1;
								
								if (col_date.equalsIgnoreCase(tblCneeBookingAnalysis.getColumn(j).getText())) {
									
									selectedIndex = cbCneeBAOption.getSelectionIndex();
									int noa = 0;
									float bft = 0;
									if (selectedIndex > -1) {
										TableItem item = null;
										item = isAlreadyAvail(tblCneeBookingAnalysis,String.valueOf(dto[i].getCustomerName()),String.valueOf(dto[i].getFromStation()));
										if (item == null) {
											
											item = new TableItem(tblCneeBookingAnalysis,SWT.NONE);
										
											
										}
										
										
										item.setText(1, String.valueOf(dto[i].getFromStation()));
										String tempUnit = cbCneeBAOption.getItem(selectedIndex);
										item.setText(2, String.valueOf(dto[i].getCustomerName()));
											
											String join = null;
											if (tempUnit != null	&& tempUnit.equalsIgnoreCase(COUNT)) {
												//join = item.getText(j);
												//if (join.equalsIgnoreCase(""))
												join = "0";
												item.setText(j,String.valueOf(Integer.parseInt(join) + dto[i].getCount()));
												
											} else if (tempUnit != null && tempUnit.equalsIgnoreCase(BASIC_FREIGHT)) {
												//join = item.getText(j);
												//if (join.equalsIgnoreCase(""))
												join = "0";
												item.setText(j,getRoundedValue(Float.parseFloat(join)	+ dto[i].getBasic_freight()));
												
											} else if (tempUnit != null	&& tempUnit.equalsIgnoreCase(TOTAL_FREIGHT)) {
												//join = item.getText(j);
												//if (join.equalsIgnoreCase(""))
												join = "0";
												item.setText(j,getRoundedValue(Float.parseFloat(join)	+ dto[i].getTotal_freight()));
												
											} else if (tempUnit != null	&& tempUnit.equalsIgnoreCase(ACTUAL_WEIGHT)) {
												//join = item.getText(j);
												//if (join.equalsIgnoreCase(""))
												join = "0";
												item.setText(j,getRoundedValue(Float.parseFloat(join)	+ dto[i].getActual_weight()));
												
											} else if (tempUnit != null	&& tempUnit.equalsIgnoreCase(CHARGED_WEIGHT)) {
												//join = item.getText(j);
												//if (join.equalsIgnoreCase(""))
												join = "0";
												item.setText(j, getRoundedValue(Float.parseFloat(join)	+ dto[i].getCharged_weight()));
												
											} else if (tempUnit != null	&& tempUnit.equalsIgnoreCase(NOA)) {
												//join = item.getText(j);
												//if (join.equalsIgnoreCase(""))
												join = "0";
												item.setText(j, String.valueOf(Integer.parseInt(join)+ dto[i].getNoa()));
											}
	
											
										//}
										break;
									}
								}
							}
							
						}
						int column = tblCneeBookingAnalysis.getColumnCount();

						TableItem[] items = tblCneeBookingAnalysis.getItems();
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
							TableItem[] item = tblCneeBookingAnalysis
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
						
						TableItem item = new TableItem(tblCneeBookingAnalysis,
								SWT.NONE);
						Font font1 = new Font(Display.getCurrent(), "Tahoma", 8, SWT.BOLD);
						item.setFont(font1);
						item.setText(2, String.valueOf("Total"));
						for (int i = 3; i < column; i++) {
							if(cbCneeBAOption.getText().equalsIgnoreCase("Count") || 
									cbCneeBAOption.getText().equalsIgnoreCase("Noa")){
								item.setText(i, String.valueOf(total[i]));
							}else{
								item.setText(i, getRoundedValue(total[i]));
							}
						}
					}

				}
			} catch (NumberFormatException e) {			
				e.printStackTrace();
			} 
			 catch (Exception e) {
				e.printStackTrace();
			}
			 new sortListener().display("Report Loaded Successfully!",
					STATUS_SUCCESS, SUCCESS_FONT_COLOR,lblStatusBar);
		}else if (source == btnExportXL) {
			try {
				//handleExcel();
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				list = new sortListener().getBPATable(tblCneeBookingAnalysis);
				value = getConsineebaHeading(tblCneeBookingAnalysis);
				TableColumn[] col = tblCneeBookingAnalysis.getColumns();
				if(col != null){
					int len = col.length;
					String param[] = new String[len];
					for(int i = 0; i < len; i++){
						param[i] = col[i].getText();
					}
					new sortListener().prepareExcel(list, "CneeBookingAnalysis", CNEEBA_EXCEL_JRXML, param,value,shell,lblStatusBar);
				}			
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else if (source == btnPrint) {
			try {
				//handlePrint();
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				list = new sortListener().getBPATable(tblCneeBookingAnalysis);
				value = getConsineebaHeading(tblCneeBookingAnalysis);
				TableColumn[] col = tblCneeBookingAnalysis.getColumns();
				if(col != null){
					int len = col.length;
					String param[] = new String[len];
					for(int i = 0; i < len; i++){
						param[i] = col[i].getText();
					}
					handler.preparePrint(list, "CneeBookingAnalysis", CNEEBA_EXCEL_JRXML, param,value);
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
				list = new sortListener().getBPATable(tblCneeBookingAnalysis);
				value = getConsineebaHeading(tblCneeBookingAnalysis);
				TableColumn[] col = tblCneeBookingAnalysis.getColumns();
				if(col != null){
					int len = col.length;
					String param[] = new String[len];
					for(int i = 0; i < len; i++){
						param[i] = col[i].getText();
					}
					new sortListener().preparePDF(list, "CneeBookingAnalysis", CNEEBA_EXCEL_JRXML, param,value,shell,lblStatusBar);
				}			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		
	}
}