package hm.akr.container.admin;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import hm.akr.common.KalendarDialog;
import hm.akr.common.SWTResourceManager;
import hm.akr.common.BeanUtil;
import hm.akr.common.IUIConstants;
import hm.akr.common.sortListener;
import hm.akr.container.history.HistoryHandler;
import hm.akr.dto.InwardAnalysisDTO;
import hm.akr.dto.StationsDTO;
import hm.akr.dto.TableDecorator;
import hm.akr.dto.VersionDTO;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class LeftOverGoods extends ReportsMenu implements IUIConstants,Listener,SelectionListener  {
	
	private String LOG = "Left Over Goods";
	private static final String LOG_VEHICLE_EXCEL_JRXML = "hm/akr/resources/printer/LOG.jrxml";
	private TabItem tiInwardAnalysis;
	private Canvas cvsInwardAnalysis;
	private Table tblInwardAnalysis;
	private Button btnGo;
	private Button btnLOGFrom;
	private Combo cbInwardStation;
	private Combo cbFromStation;
	private Label lblInwardStation;
	private Label label2;
	private Label label8;
	private Text txtLOGFrom;
	private Label lblLOGToDate;
	private Text txtLOGToDate;
	private Button btnLOGTo;
	private Combo cbOption;
	private String[] BRANCHES = null;
	private String[] COLUMNS = null;
	private TableColumn column;
	private final int COLUMN_WIDTH = 60;

	private final int TBL_WIDTH = 750;
	private final int TBL_HEIGHT = 450;
	
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
	
	
	 public LeftOverGoods(Composite composite,int style, float clientRights) {
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

			tiInwardAnalysis = new TabItem(tabReport, SWT.NONE);
			tiInwardAnalysis.setText(LOG);

			cvsInwardAnalysis = new Canvas(tabReport, SWT.NONE);
			tiInwardAnalysis.setControl(cvsInwardAnalysis);
			{
				tblInwardAnalysis = new Table(cvsInwardAnalysis,
						SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL
								| SWT.BORDER);
				tblInwardAnalysis.setBounds(5, 90, 750, 380);

				tblInwardAnalysis.setHeaderVisible(true);
				tblInwardAnalysis.setLinesVisible(true);
			}

			{
				lblInwardStation = new Label(cvsInwardAnalysis, SWT.NONE);
				lblInwardStation.setText("To Station");
				lblInwardStation.setBounds(335, 19, 75, 21);
			}
			{
				label2 = new Label(cvsInwardAnalysis, SWT.NONE);
				label2.setText("From Station");
				label2.setBounds(468, 20, 76, 22);
			}
			{
				label8 = new Label(cvsInwardAnalysis, SWT.NONE);
				label8.setText("From");
				label8.setBounds(8, 56, 33, 16);
			}
			/*{
				cbLOGFromHour = new Combo(cvsInwardAnalysis, SWT.NONE);
				cbLOGFromHour.setBounds(152, 36, 41, 23);
				for (int h = 1; h < 12; h++) {
					cbLOGFromHour.add(String.valueOf(h));
				}
				cbLOGFromHour.select(0);
			}
			{
				cbLOGFromMin = new Combo(cvsInwardAnalysis, SWT.NONE);
				cbLOGFromMin.setBounds(199, 36, 42, 23);
				for (int m = 1; m < 60; m++) {
					cbLOGFromMin.add(String.valueOf(m));
				}
				cbLOGFromMin.select(0);
			}*/
			{
				txtLOGFrom = new Text(cvsInwardAnalysis, SWT.BORDER);
				txtLOGFrom.setBounds(45, 49, 71, 22);
				txtLOGFrom.setEnabled(false);
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date currentDate = new java.util.Date();
				String date = dateFormat.format(currentDate);
				txtLOGFrom.setText(date);

			}
			/*{
				cbLOGTime = new Combo(cvsInwardAnalysis, SWT.NONE);
				cbLOGTime.setBounds(245, 37, 50, 23);
				cbLOGTime.add("AM");
				cbLOGTime.add("PM");
				cbLOGTime.select(0);
			}*/
			/*{
				lblTime = new Label(cvsInwardAnalysis, SWT.NONE);
				lblTime.setText("Time");
				lblTime.setBounds(158, 15, 113, 15);
			}*/
			{
				btnLOGFrom = new Button(cvsInwardAnalysis, SWT.PUSH
						| SWT.CENTER);
				btnLOGFrom.setImage(SWTResourceManager
						.getImage("hm/akr/resources/Calendar.jpg"));
				btnLOGFrom.setBounds(116, 48, 26, 23);
				btnLOGFrom.addSelectionListener(this);
			}

			{
				lblLOGToDate = new Label(cvsInwardAnalysis, SWT.NONE);
				lblLOGToDate.setText("To");
				lblLOGToDate.setBounds(167, 56, 25, 20);
			}
			{
				txtLOGToDate = new Text(cvsInwardAnalysis, SWT.BORDER);
				txtLOGToDate.setBounds(197, 49, 68, 24);
				txtLOGToDate.setEnabled(false);
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date currentDate = new java.util.Date();
				String date = dateFormat.format(currentDate);
				txtLOGToDate.setText(date);
			}
			{
				btnLOGTo = new Button(cvsInwardAnalysis, SWT.PUSH | SWT.CENTER);
				btnLOGTo.setImage(SWTResourceManager
						.getImage("hm/akr/resources/Calendar.jpg"));
				btnLOGTo.setBounds(265, 48, 26, 23);
				btnLOGTo.addSelectionListener(this);
			}
			{
				cbInwardStation = new Combo(cvsInwardAnalysis, SWT.READ_ONLY);
				cbInwardStation.setBounds(333, 50, 100, 23);
				if(clientRights == 1 || clientRights == 1.0){
					
					try{
					
						getCurrentBranch_code(currentStationCode,cbInwardStation);
						cbInwardStation.select(0);
						//handleBranchActionUT(cbUTBranch, cbUTStation);
						//cbUTBranch.addSelectionListener(this);
					}
					catch(Exception e){
						e.printStackTrace();
					}
					
					
				}
				else{				
					cbInwardStation.add("All");
				}
				
			}
			{
				cbFromStation = new Combo(cvsInwardAnalysis, SWT.READ_ONLY);
				cbFromStation.setBounds(464, 51, 92, 23);
				/*if(clientRights == 1 || clientRights == 1.0){
					
					try{
					
						getCurrentBranch_code(currentStationCode,cbFromStation);
						cbFromStation.select(0);
						//handleBranchActionUT(cbUTBranch, cbUTStation);
						//cbUTBranch.addSelectionListener(this);
					}
					catch(Exception e){
						e.printStackTrace();
					}
					
					
				}
				else*/{
					cbFromStation.add("All");
				}
			}

			{
				cbOption = new Combo(cvsInwardAnalysis, SWT.READ_ONLY);
				cbOption.setBounds(586, 51, 110, 23);
				cbOption.add(COUNT);
				cbOption.add(BASIC_FREIGHT);
				cbOption.add(TOTAL_FREIGHT);
				cbOption.add(ACTUAL_WEIGHT);
				cbOption.add(CHARGED_WEIGHT);
				cbOption.add(NOA);
				// cbOption.select(0);

			}

			{
				btnGo = new Button(cvsInwardAnalysis, SWT.PUSH | SWT.CENTER);
				btnGo.setText("Go");
				btnGo.setBounds(701, 50, 36, 23);
				btnGo.addSelectionListener(this);
			}
		
				try{
					populateComboStations();
				}
				catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
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
	
	
	private void getCurrentBranch_code(String station, Combo cbInwardStation) throws Exception {
		StationsDTO[] stations = null;

		stations = handler.getAllStations();
		int size = 0;
		if (null != stations) {
			size = stations.length;

			for (int i = 0; i < size; i++) {
				if (station.equalsIgnoreCase(stations[i].getId())) {
					
					if(cbInwardStation == cbInwardStation){
						cbInwardStation.add(stations[i].getBranch_code());	
					}
					else{
						cbFromStation.add(stations[i].getBranch_code());
					}
					//getCurrentBranchName(stations[i].getBranch_code());
					
					//return stations[i].getBranch_code();
				}
			}
		}

		//return null;
	}
	
	/*private void getCurrentBranchName(String branch_code) {
		// TODO Auto-generated method stub
		try{
			StationsDTO[] stations = null;
	
			stations = handler.getAllStations();
			int size = 0;
			if (null != stations) {
				size = stations.length;
	
				for (int i = 0; i < size; i++) {
					if (branch_code.equalsIgnoreCase(stations[i].getId())) {
					
						cbFromStation.add(stations[i].getName()+" - "+branch_code);
						cbInwardStation.add(stations[i].getName()+" - "+branch_code);
						//return stations[i].getBranch_code();
					}
				}
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}*/

	
	private void populateComboStations() throws Exception {
		StationsDTO[] stations = null;
		stations = handler.getAllStations();
		if (stations != null) {
			String stnCode = null;
			int len = stations.length;
			ArrayList<String> list = new ArrayList<String>();

			for (int i = 0; i < len; i++) {
				stnCode = stations[i].getId();
				if (stnCode.equalsIgnoreCase(stations[i].getBranch_code())
						&& !stations[i].getBranch_code().equalsIgnoreCase("HO")) {
					list.add(stnCode);
					cbFromStation.add(stnCode);
					if(!(clientRights == 1 || clientRights == 1.0)){
						
						cbInwardStation.add(stnCode);
					}
				}
			}

			int size = list.size();
			if (size > 0)
				BRANCHES = (String[]) list.toArray(new String[size]);
		}
	}
	
	private boolean designTable() throws Exception {

		if (tblInwardAnalysis != null) {
			tblInwardAnalysis.dispose();
		}

		tblInwardAnalysis = new Table(cvsInwardAnalysis, SWT.FULL_SELECTION
				| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		tblInwardAnalysis.setBounds(5, 90, 750, 380);

		tblInwardAnalysis.setHeaderVisible(true);
		tblInwardAnalysis.setLinesVisible(true);

		if (cbFromStation.getText().equalsIgnoreCase("All")
				&& cbInwardStation.getText().equalsIgnoreCase("All")) {
			COLUMNS = BRANCHES;
			allFieldsTable();
		} else {
			String[] rows = null;
			if (cbInwardStation.getText().equalsIgnoreCase("All")) {
				rows = BRANCHES;
				COLUMNS = getBranchStations(cbFromStation.getText());

			} else if (cbFromStation.getText().equalsIgnoreCase("All")) {
				rows = getBranchStations(cbInwardStation.getText());
				COLUMNS = BRANCHES;
			} else {
				rows = getBranchStations(cbInwardStation.getText());
				COLUMNS = getBranchStations(cbFromStation.getText());
			}

			createDynamicTable(rows, COLUMNS);
		}

		return true;

	}
	
	private void allFieldsTable() {

		int colLen = BRANCHES.length;
		int width = ((colLen + 2) * COLUMN_WIDTH) + 30;
		int height = ((colLen + 2) * tblInwardAnalysis.getItemHeight()) + 40;

		// System.out.println("ht-->" + tblInwardAnalysis.getItemHeight());
		for (int i = 0; i < colLen + 1; i++) {
			column = new TableColumn(tblInwardAnalysis, SWT.CENTER);
			column.setAlignment(SWT.RIGHT);
			if (i != 0)
				column.setText(BRANCHES[i - 1]);
			tblInwardAnalysis.getColumn(i).setWidth(COLUMN_WIDTH);

			if (i != colLen) {
				TableItem item = new TableItem(tblInwardAnalysis, SWT.NONE);
				item.setText(0, BRANCHES[i]);
				item.setBackground(0, new Color(Display.getCurrent(), 237, 234,
						219));
			}
		}

		/*
		 * if(width > TBL_WIDTH){ height = height + 20; }
		 * 
		 * if(height > TBL_HEIGHT){ width = width + 20; }
		 */
		// For Horizontal bar
		width = (width < TBL_WIDTH) ? width : TBL_WIDTH + 10;
		// For Vertical bar
		height = (height < TBL_HEIGHT) ? height : TBL_HEIGHT;

	}
	
	private void createDynamicTable(String[] rows, String[] columns) {
		int colLen = columns.length;
		int rowLen = rows.length;

		// TOTAL 1
		int width = ((colLen + 2) * COLUMN_WIDTH) + 30;
		int height = ((rowLen + 2) * tblInwardAnalysis.getItemHeight()) + 40;
		if (colLen == BRANCHES.length) {
			// width = width - 3;
		}

		for (int i = 0; i < colLen + 1; i++) {
			column = new TableColumn(tblInwardAnalysis, SWT.CENTER);
			column.setAlignment(SWT.RIGHT);
			if (i != 0) {
				column.setText(columns[i - 1]);
				tblInwardAnalysis.getColumn(i).setWidth(COLUMN_WIDTH);
			} else {
				tblInwardAnalysis.getColumn(i).setWidth(COLUMN_WIDTH - 10);
			}

		}

		for (int j = 0; j < rowLen; j++) {
			TableItem item = new TableItem(tblInwardAnalysis, SWT.NONE);
			item.setText(0, rows[j]);
			// item.setBackground(0, new Color(Display.getCurrent(),
			// 210,180,140));
			item.setBackground(0,
					new Color(Display.getCurrent(), 237, 234, 219));
		}

		/*
		 * if(width > TBL_WIDTH){ height = height + 20; }
		 * 
		 * if(height > TBL_HEIGHT){ width = width + 20; }
		 */
		// For Horizontal bar
		width = (width < TBL_WIDTH) ? width : TBL_WIDTH + 10;
		// For Vertical bar
		height = (height < TBL_HEIGHT) ? height : TBL_HEIGHT;

	}

	
	private String[] getBranchStations(String branch) throws Exception {
		StationsDTO[] stations = null;
		stations = handler.getAllStations();
		if (stations != null) {
			int len = stations.length;
			ArrayList<String> list = new ArrayList<String>();
			for (int i = 0; i < len; i++) {
				if (branch.equalsIgnoreCase(stations[i].getBranch_code())) {
					list.add(stations[i].getId());
				}
			}

			int size = list.size();
			if (size > 0) {
				return (String[]) list.toArray(new String[size]);
			}
		}

		return null;
	}

	private void populateInwardAnalysis(InwardAnalysisDTO[] dto) {
		int dtoLen = dto.length;
		int colLen = COLUMNS.length;
		final TableItem item1 = new TableItem(tblInwardAnalysis, SWT.NONE);
		Font font1 = new Font(Display.getDefault(), "Tahoma", 8, SWT.BOLD);
		item1.setFont(font1);
		item1.setText(0, "TOTAL");
		int colVal = 0;
		float colFloatVal = 0;
		TableItem[] items = tblInwardAnalysis.getItems();
		int itemsLen = items.length;

		int colTotIndex = tblInwardAnalysis.getColumnCount();

		// Col Total
		column = new TableColumn(tblInwardAnalysis, SWT.CENTER);
		column.setText("TOTAL");
		column.setWidth(60);
		column.setAlignment(SWT.RIGHT);

		DecimalFormat df = new DecimalFormat("0.00");

		for (int i = 0; i < dtoLen; i++) {

			for (int j = 0; j < itemsLen; j++) {
				if (dto[i].getInwardStation().equalsIgnoreCase(
						items[j].getText(0))) {

					for (int k = 0; k < colLen; k++) {
						if (dto[i].getFromStation()
								.equalsIgnoreCase(COLUMNS[k])) {

							if (cbOption.getText().equals(COUNT)) {

								
								items[j].setText(k + 1, String.valueOf(dto[i]
										.getCount()));

								// total
								if (!items[itemsLen - 1].getText(k + 1).equals(
										"")) {
									colVal = Integer
											.parseInt(items[itemsLen - 1]
													.getText(k + 1));
								} else {
									colVal = 0;
								}
								items[itemsLen - 1].setText(k + 1, String
										.valueOf(colVal + dto[i].getCount()));

								// COL
								if (!items[j].getText(colTotIndex).equals("")) {
									colVal = Integer.parseInt(items[j]
											.getText(colTotIndex));
								} else {
									colVal = 0;
								}
								items[j].setText(colTotIndex, String
										.valueOf(colVal + dto[i].getCount()));

							} else if (cbOption.getText().equals(BASIC_FREIGHT)) {
								items[j].setText(k + 1, df.format(dto[i]
										.getBasic_freight()));
								// total
								if (!items[itemsLen - 1].getText(k + 1).equals(
										"")) {
									colFloatVal = Float
											.parseFloat(items[itemsLen - 1]
													.getText(k + 1));
								} else {
									colFloatVal = 0;
								}
								items[itemsLen - 1].setText(k + 1, df
										.format(colFloatVal
												+ dto[i].getBasic_freight()));

								// COL total
								if (!items[j].getText(colTotIndex).equals("")) {
									colFloatVal = Float.parseFloat(items[j]
											.getText(colTotIndex));
								} else {
									colFloatVal = 0;
								}
								items[j].setText(colTotIndex, df
										.format(colFloatVal
												+ dto[i].getBasic_freight()));
							} else if (cbOption.getText().equals(TOTAL_FREIGHT)) {
								items[j].setText(k + 1, df.format(dto[i]
										.getTotal_freight()));
								// total
								if (!items[itemsLen - 1].getText(k + 1).equals(
										"")) {
									colFloatVal = Float
											.parseFloat(items[itemsLen - 1]
													.getText(k + 1));
								} else {
									colFloatVal = 0;
								}
								items[itemsLen - 1].setText(k + 1, df
										.format(colFloatVal
												+ dto[i].getTotal_freight()));

								// COL total
								if (!items[j].getText(colTotIndex).equals("")) {
									colFloatVal = Float.parseFloat(items[j]
											.getText(colTotIndex));
								} else {
									colFloatVal = 0;
								}
								items[j].setText(colTotIndex, df
										.format(colFloatVal
												+ dto[i].getTotal_freight()));

							} else if (cbOption.getText().equals(ACTUAL_WEIGHT)) {
								items[j].setText(k + 1, df.format(dto[i]
										.getActual_weight()));

								// total
								if (!items[itemsLen - 1].getText(k + 1).equals(
										"")) {
									colFloatVal = Float
											.parseFloat(items[itemsLen - 1]
													.getText(k + 1));
								} else {
									colFloatVal = 0;
								}
								items[itemsLen - 1].setText(k + 1, df
										.format(colFloatVal
												+ dto[i].getActual_weight()));

								// COL total
								if (!items[j].getText(colTotIndex).equals("")) {
									colFloatVal = Float.parseFloat(items[j]
											.getText(colTotIndex));
								} else {
									colFloatVal = 0;
								}
								items[j].setText(colTotIndex, String
										.valueOf(colFloatVal
												+ dto[i].getActual_weight()));
							} else if (cbOption.getText()
									.equals(CHARGED_WEIGHT)) {
								items[j].setText(k + 1, df.format(dto[i]
										.getCharged_weight()));

								// total
								if (!items[itemsLen - 1].getText(k + 1).equals(
										"")) {
									colFloatVal = Float
											.parseFloat(items[itemsLen - 1]
													.getText(k + 1));
								} else {
									colFloatVal = 0;
								}
								items[itemsLen - 1].setText(k + 1, df
										.format(colFloatVal
												+ dto[i].getCharged_weight()));

								// COL total
								if (!items[j].getText(colTotIndex).equals("")) {
									colFloatVal = Float.parseFloat(items[j]
											.getText(colTotIndex));
								} else {
									colFloatVal = 0;
								}
								items[j].setText(colTotIndex, df
										.format(colFloatVal
												+ dto[i].getCharged_weight()));
							} else if (cbOption.getText().equals(NOA)) {
								items[j].setText(k + 1, String.valueOf(dto[i]
										.getNoa()));

								// total
								if (!items[itemsLen - 1].getText(k + 1).equals(
										"")) {
									colVal = Integer
											.parseInt(items[itemsLen - 1]
													.getText(k + 1));
								} else {
									colVal = 0;
								}
								items[itemsLen - 1].setText(k + 1, String
										.valueOf(colVal + dto[i].getNoa()));

								// Col total
								if (!items[j].getText(colTotIndex).equals("")) {
									colVal = Integer.parseInt(items[j]
											.getText(colTotIndex));
								} else {
									colVal = 0;
								}
								items[j].setText(colTotIndex, String
										.valueOf(colVal + dto[i].getNoa()));
							}

						}
					}

				}

			}
		}

		if (cbOption.getText().equals(COUNT) || cbOption.getText().equals(NOA)) {
			int finalVal = 0;
			for (int i = 0; i < itemsLen; i++) {
				// Final Total
				if (!items[i].getText(colTotIndex).equals("")) {
					colVal = Integer.parseInt(items[i].getText(colTotIndex));
				} else {
					colVal = 0;
				}

				finalVal = colVal + finalVal;

			}
			items[itemsLen - 1].setText(colTotIndex, String.valueOf(finalVal));
		} else {
			float finalVal = 0;
			for (int i = 0; i < itemsLen; i++) {
				// Final Total
				if (!items[i].getText(colTotIndex).equals("")) {
					colFloatVal = Float.parseFloat(items[i]
							.getText(colTotIndex));
				} else {
					colFloatVal = 0;
				}

				finalVal = colFloatVal + finalVal;

			}
			items[itemsLen - 1].setText(colTotIndex, df.format(finalVal));
		}
		
		fillZerosOnEmpty(tblInwardAnalysis);

	}
	
	private void fillZerosOnEmpty(Table refTbl) {
		
		TableItem[] items = refTbl.getItems();
		int len = items.length;		
		if (len > 0) {
			if(refTbl == tblInwardAnalysis){
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
	
	private String[]  getLOGHeading(Table refTable) {
		
		String list[] = new String[5];
						
		list[0] = txtLOGFrom.getText();
		list[1] = txtLOGToDate.getText();
		//list[2] = cbLOGFromHour.getText() +":"+ cbLOGFromMin.getText() + cbLOGTime.getText();
		list[2] = cbInwardStation.getText();
		list[3] = cbFromStation.getText();
		list[4] = cbOption.getText();
	
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
		 if (source == btnLOGFrom) {
				KalendarDialog cd = new KalendarDialog(shell);
				Object obj = cd.open();
				if (obj != null) {
					txtLOGFrom.setText(obj.toString());
				}

			} else if (source == btnLOGTo) {
				KalendarDialog cd = new KalendarDialog(shell);
				Object obj = cd.open();
				if (obj != null) {
					txtLOGToDate.setText(obj.toString());
				}

			} else if (source == btnGo) {
				new sortListener().display("Generating Report...", STATUS_SUCCESS,
						SUCCESS_FONT_COLOR,lblStatusBar);

				try {
					// validate();
					if ((cbFromStation.getSelectionIndex() != -1)
							&& (cbInwardStation.getSelectionIndex() != -1)) {
						
						if (handler != null) {
							InwardAnalysisDTO[] dto = null;

							String fdate = txtLOGFrom.getText();
							String tdate = txtLOGToDate.getText();
							/*Date d = new Date();
							System.out.println("DAte-->"+d);
							d.getTime()
							String hour = cbLOGFromHour.getText();
							int hours = Integer.parseInt(hour);
							String min = cbLOGFromMin.getText();
							int minutes = Integer.parseInt(min);
							String timeForm = cbLOGTime.getText();
							/*if (timeForm.equalsIgnoreCase("PM")) {
								int temp = Integer.parseInt(hour);
								temp = temp + 12;
								hour = String.valueOf(temp);
							}
							if (fdate != null && !fdate.equalsIgnoreCase("")) {

								fdate = fdate + " " + hour + ":" + min + ":00";
								tdate = tdate + " " + hour + ":" + min + ":00";
								String DATE_FORMAT1 = "dd-MM-yyy HH:mm:ss";
								java.text.SimpleDateFormat sdf1 = new java.text.SimpleDateFormat(
										DATE_FORMAT1);
								Date fromdate = sdf1.parse(fdate);
								Date todate = sdf1.parse(tdate);*/
								String DATE_FORMAT1 = "dd-MM-yyyy";
								java.text.SimpleDateFormat sdf2 = new java.text.SimpleDateFormat(DATE_FORMAT1);
								 
								Date fromdate = sdf2.parse(fdate);
							
								Date todate = sdf2.parse(tdate);
								Calendar c1 = Calendar.getInstance();
								String date = sdf2.format(c1.getTime());
								Date curdate = sdf2.parse(date);
								curdate.setHours(00);
								curdate.setMinutes(00);
								curdate.setSeconds(00);
								if((fromdate.equals(curdate))&&(todate.equals(curdate))){
								
									/*if((timeForm.equalsIgnoreCase("PM"))&&((hours > 3) || ((hours == 3) && (minutes > 30)))){
										designTable();

										dto = handler.getLOG(fromdate, todate,cbFromStation.getText(), cbInwardStation.getText());
																
									}
									else{*/
										
										MessageBox messageBox = new MessageBox(shell, SWT.OK);
										messageBox.setMessage("The details will be available only after 6:00 AM ");
										messageBox.setText("Alert");
										messageBox.open();                                 
									//}
								}
								else if(todate.equals(curdate)){
									designTable();                            

									/*if((timeForm.equalsIgnoreCase("PM"))&&((hours > 3) || ((hours == 3) && (minutes > 30)))){
										
										dto = handler.getLOG(fromdate, todate,cbFromStation.getText(), cbInwardStation.getText());
																
									}
									else{*/
										
										int value = todate.getDate() - 1;
										todate.setDate(value);
										dto = handler.getLOG(fromdate, todate,cbFromStation.getText(), cbInwardStation.getText());          
									//}
									
								}
								else{
									designTable();         

									dto = handler.getLOG(fromdate, todate,cbFromStation.getText(), cbInwardStation.getText());
								}
							
								if (dto != null) {
									populateInwardAnalysis(dto);
								}
							//}
						}

					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				new sortListener().display("Report Loaded Successfully!",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR,lblStatusBar);
			} else if (source == btnExportXL) {
				try {
					//handleExcel();
					ArrayList<TableDecorator> list = null;
					String[] value = null;
					list = getLOGTable(tblInwardAnalysis);
					value = getLOGHeading(tblInwardAnalysis);
					TableColumn[] col = tblInwardAnalysis.getColumns();
					if(col != null){
						int len = col.length;
						String param[] = new String[len];
						for(int i = 0; i < len; i++){
							param[i] = col[i].getText();
							
						}
						new sortListener().prepareExcel(list, "LeftOverGoods", LOG_VEHICLE_EXCEL_JRXML, param,value,shell,lblStatusBar);
					}		
				} catch (Exception e) {
					e.printStackTrace();
				}

			}else if (source == btnPrint) {
				try {
					//handlePrint();
					ArrayList<TableDecorator> list = null;
					String[] value = null;
					list = getLOGTable(tblInwardAnalysis);
					value = getLOGHeading(tblInwardAnalysis);
					TableColumn[] col = tblInwardAnalysis.getColumns();
					if(col != null){
						int len = col.length;
						String param[] = new String[len];
						for(int i = 0; i < len; i++){
							param[i] = col[i].getText();
							
						}
						handler.preparePrint(list, "LeftOverGoods", LOG_VEHICLE_EXCEL_JRXML, param,value);
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
					list = getLOGTable(tblInwardAnalysis);
					value = getLOGHeading(tblInwardAnalysis);
					TableColumn[] col = tblInwardAnalysis.getColumns();
					if(col != null){
						int len = col.length;
						String param[] = new String[len];
						for(int i = 0; i < len; i++){
							param[i] = col[i].getText();
							
						}
						new sortListener().preparePDF(list, "LeftOverGoods", LOG_VEHICLE_EXCEL_JRXML, param,value,shell,lblStatusBar);
					}		
				} catch (Exception e) {
					e.printStackTrace();
				}
			}	
		
	}

}
