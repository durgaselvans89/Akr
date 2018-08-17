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
import hm.akr.dto.CustomerBusinessAnalysisDTO;
import hm.akr.dto.TableDecorator;
import hm.akr.dto.VersionDTO;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
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

public class Inter_Intra extends Composite implements IUIConstants,Listener,SelectionListener  {
	
	private TabItem tiStates;
	private Canvas canStates;
	private Table tblIIStates;
	/*
	 * private Label lblStatesMonth; private Text txtStatesMonth; private Button
	 * btnStatesMonth;
	 */
	private TableColumn colstatesSno;
	private TableColumn colStatesTN;
	private TableColumn colStatesPHY;
	private TableColumn colStatesAP;
	private TableColumn colStatesKarnataka;
	private TableColumn colStatesKerala;
	private TableColumn colStatesTotal;
	private static final String IIS_EXCEL_JRXML = "hm/akr/resources/printer/IIS.jrxml";
	private String INTER_INTRA = "Inter & Intra States";
	private String[] STATES = { "TamilNadu", "Pondicherry", "AndhraPradesh",
			"Karnataka", "Kerala", "Total" };
	private String COUNT = "Count";
	private String BASIC_FREIGHT = "Basic Freight";
	private String TOTAL_FREIGHT = "Total Freight";
	private String ACTUAL_WEIGHT = "Actual Weight";
	private String CHARGED_WEIGHT = "Charged Weight";
	private String NOA = "Noa";
	private Button btnIISGo;
	private Combo cbIISChoice;
	private Label label17;
	private Label label16;
	private Label label15;
	private Label label18;
	private Label label10;
	private Label label9;
	private Label lblToState;
	private Label lblFrom;
	private Label lblFromDate;
	private Label lblTo;
	private Text txtIISFrom;
	private Button btnIISFromDate;
	private Text txtIISTo;
	private Button btnIISTo;
	
	
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
	
	public Inter_Intra(Composite composite,int style) {
		super(composite,style);
		// TODO Auto-generated constructor stub
		
		try {
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
			tiStates = new TabItem(tabReport, SWT.NONE);
			tiStates.setText(INTER_INTRA);

			canStates = new Canvas(tabReport, SWT.BORDER);
			tiStates.setControl(canStates);

			tblIIStates = new Table(canStates, SWT.FULL_SELECTION | SWT.H_SCROLL
					| SWT.V_SCROLL | SWT.BORDER);
			tblIIStates.setBounds(30, 75, 740, 200);
			tblIIStates.setHeaderVisible(true);
			tblIIStates.setLinesVisible(true);

			{
				colstatesSno = new TableColumn(tblIIStates, SWT.NONE);
				colstatesSno.setText("States");
				colstatesSno.setWidth(130);
			}
			{
				colStatesTN = new TableColumn(tblIIStates, SWT.NONE);
				colStatesTN.setText("Tamilnadu");
				colStatesTN.setWidth(100);
			}
			{
				colStatesPHY = new TableColumn(tblIIStates, SWT.NONE);
				colStatesPHY.setText("Pondicherry");
				colStatesPHY.setWidth(100);
			}
			{
				colStatesAP = new TableColumn(tblIIStates, SWT.NONE);
				colStatesAP.setText("AndhraPradesh");
				colStatesAP.setWidth(100);
			}
			{
				colStatesKarnataka = new TableColumn(tblIIStates, SWT.NONE);
				colStatesKarnataka.setText("Karnataka");
				colStatesKarnataka.setWidth(100);
			}

			{
				colStatesKerala = new TableColumn(tblIIStates, SWT.NONE);
				colStatesKerala.setText("Kerala");
				colStatesKerala.setWidth(100);
			}
			{
				colStatesTotal = new TableColumn(tblIIStates, SWT.NONE);
				colStatesTotal.setText("Total");
				colStatesTotal.setWidth(100);

			}
			
			for (int index = 0; index < tblIIStates.getColumnCount(); index++) {
				tblIIStates.getColumn(index).addListener(SWT.Selection,this);
			}
			/*
			 * { lblStatesMonth = new Label(canStates, SWT.NONE);
			 * lblStatesMonth.setText("Select Month");
			 * lblStatesMonth.setBounds(60, 23, 68, 21); } { txtStatesMonth =
			 * new Text(canStates, SWT.BORDER | SWT.READ_ONLY);
			 * txtStatesMonth.setBounds(128, 22, 52, 21); DateFormat dateFormat =
			 * new SimpleDateFormat("MM-yyyy"); java.util.Date currentDate = new
			 * java.util.Date(); String date = dateFormat.format(currentDate);
			 * .txtStatesMonth.setText(date); } { btnStatesMonth = new
			 * Button(canStates, SWT.PUSH | SWT.CENTER);
			 * btnStatesMonth.setImage(SWTResourceManager.getImage("hm/akr/resources/Calendar.jpg"));
			 * btnStatesMonth.setBounds(181, 21, 26, 23);
			 * btnStatesMonth.addSelectionListener(new SelectionListener() {
			 * 
			 * @Override public void widgetDefaultSelected(SelectionEvent arg0) { }
			 * 
			 * @Override public void widgetSelected(SelectionEvent event) {
			 * MonthDialog cd = new
			 * MonthDialog(Display.getCurrent().getActiveShell()); Object obj =
			 * cd.open(); if (obj != null) {
			 * txtStatesMonth.setText(obj.toString()); } }
			 * 
			 * }); }
			 */
											
			
			{
				lblFromDate = new Label(canStates, SWT.NONE);
				lblFromDate.setText("From Date");
				lblFromDate.setBounds(15, 20, 55, 17);
			}

			{
				txtIISFrom = new Text(canStates, SWT.BORDER);
				txtIISFrom.setBounds(80, 20, 90, 22);
				txtIISFrom.setEditable(false);
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date currentDate = new java.util.Date();
				String date = dateFormat.format(currentDate);
				txtIISFrom.setText(date);

			}
			{
				btnIISFromDate = new Button(canStates, SWT.PUSH | SWT.CENTER);
				btnIISFromDate.setBounds(170, 19, 31, 23);
				btnIISFromDate.setImage(SWTResourceManager
						.getImage("hm/akr/resources/Calendar.jpg"));
				btnIISFromDate.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						KalendarDialog cd = new KalendarDialog(shell);
						Object obj = cd.open();
						if (obj != null) {
							String date = obj.toString();
							txtIISFrom.setText(date);
						}
					}
				});
			}
			{
				lblTo = new Label(canStates, SWT.NONE);
				lblTo.setText("To Date");
				lblTo.setBounds(215, 20, 45, 23);
			}
			{
				txtIISTo = new Text(canStates, SWT.BORDER);
				txtIISTo.setBounds(270, 20, 90, 22);
				txtIISTo.setEditable(false);
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date currentDate = new java.util.Date();
				String date = dateFormat.format(currentDate);
				txtIISTo.setText(date);

			}
			{
				btnIISTo = new Button(canStates, SWT.PUSH | SWT.CENTER);
				btnIISTo.setBounds(360, 18, 32, 23);
				btnIISTo.setImage(SWTResourceManager
						.getImage("hm/akr/resources/Calendar.jpg"));
				btnIISTo.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						KalendarDialog cd = new KalendarDialog(shell);
						Object obj = cd.open();
						if (obj != null) {
							String date = obj.toString();
							txtIISTo.setText(date);
						}
					}
				});

			}

			{
				cbIISChoice = new Combo(canStates, SWT.READ_ONLY);
				cbIISChoice.setBounds(400, 21, 90, 21);
				//cbIISChoice.addSelectionListener(this);
				cbIISChoice.add(COUNT);
				cbIISChoice.add(BASIC_FREIGHT);
				cbIISChoice.add(TOTAL_FREIGHT);
				cbIISChoice.add(ACTUAL_WEIGHT);
				cbIISChoice.add(CHARGED_WEIGHT);
				cbIISChoice.add(NOA);
			}

			{
				btnIISGo = new Button(canStates, SWT.PUSH | SWT.CENTER);
				btnIISGo.setBounds(510, 19, 32, 24);
				btnIISGo.setText("Go");
				btnIISGo.addSelectionListener(this);

			}
				lblFrom = new Label(canStates, SWT.NONE);
				lblFrom.setText("FROM STATE");
				lblFrom.setBounds(167, 52, 79, 21);
			}
			{
				lblToState = new Label(canStates, SWT.VERTICAL);
				lblToState.setText("T");
				lblToState.setBounds(14, 77, 14, 14);
			}
			{
				label9 = new Label(canStates, SWT.VERTICAL);
				label9.setText("O");
				label9.setBounds(14, 93, 14, 14);
			}
			{
				label10 = new Label(canStates, SWT.VERTICAL);
				label10.setText("S");
				label10.setBounds(14, 115, 14, 14);
			}
			{
				label18 = new Label(canStates, SWT.VERTICAL);
				label18.setText("T");
				label18.setBounds(14, 130, 14, 14);
			}
			{
				label15 = new Label(canStates, SWT.VERTICAL);
				label15.setText("A");
				label15.setBounds(14, 145, 14, 14);
			}
			{
				label16 = new Label(canStates, SWT.VERTICAL);
				label16.setText("T");
				label16.setBounds(14, 160, 14, 14);
			}
			{
				label17 = new Label(canStates, SWT.VERTICAL);
				label17.setText("E");
				label17.setBounds(14, 175, 14, 14);
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
			populateIIColumns();
		
		return this;
	}
	
	
	private void handleIIS() {
		try {
			SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
			Date from = dt.parse(txtIISFrom.getText());
			Date to = dt.parse(txtIISTo.getText());

			CustomerBusinessAnalysisDTO[] dto = getIIStates(from, to);

			if (tblIIStates != null)
				tblIIStates.removeAll();
			populateIIColumns();
			if (dto != null) {
				populateIIS(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private CustomerBusinessAnalysisDTO[] getIIStates(Date from, Date to) {
		// TODO Auto-generated method stub
		CustomerBusinessAnalysisDTO[] dto = null;
		try {
			/*Date curDate = new Date();
			int m1 = fromDt.getYear() * 12 + fromDt.getMonth();
	        int m2 = curDate.getYear() * 12 + curDate.getMonth();
	        monthDiff = m2 - m1;*/
			Date date = BeanUtil.getThree_month_updated();
		if(to.before(date)){
			System.out.println("in ddr histry-->"+date);
			dto = handler.getIIStatesHistory(from, to);
		}else if(from.after(date)){
			dto = handler.getIIStates(from, to);
		}else{
			dto = handler.getIIStatesUnion(from, to);
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
		

		
	}

	private void populateIIS(CustomerBusinessAnalysisDTO[] dto)
			throws Exception {
		String from = "";
		String to = "";
		int colVal = 0;
		float colFloatVal = 0;
		int colTotIndex = tblIIStates.getColumnCount();
		DecimalFormat df = new DecimalFormat("0.00");

		TableItem[] items = tblIIStates.getItems();
		int itemsLen = items.length;
		for (int i = 0; i < dto.length; i++) {
			from = dto[i].getFromStation();
			to = dto[i].getCustomerName();

			for (int j = 0; j < items.length; j++) {
				if (to.equalsIgnoreCase(items[j].getText(0))) {
					for (int k = 0; k < STATES.length; k++) {
						if (from.equalsIgnoreCase(STATES[k])) {

							if (cbIISChoice.getText().equals(COUNT)) {

								items[j].setText(k + 1, String.valueOf(dto[i]
										.getCount()));

								// last table item total
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

								// last COL total
								if (!items[j].getText(colTotIndex - 1).equals(
										"")) {
									colVal = Integer.parseInt(items[j]
											.getText(colTotIndex - 1));
								} else {
									colVal = 0;
								}
								items[j].setText(colTotIndex - 1, String
										.valueOf(colVal + dto[i].getCount()));

							} else if (cbIISChoice.getText().equals(                 
									BASIC_FREIGHT)) {
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
								if (!items[j].getText(colTotIndex - 1).equals(
										"")) {
									colFloatVal = Float.parseFloat(items[j]
											.getText(colTotIndex - 1));
								} else {
									colFloatVal = 0;
								}
								items[j].setText(colTotIndex - 1, df
										.format(colFloatVal
												+ dto[i].getBasic_freight()));
							} else if (cbIISChoice.getText().equals(
									TOTAL_FREIGHT)) {
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
								if (!items[j].getText(colTotIndex - 1).equals(
										"")) {
									colFloatVal = Float.parseFloat(items[j]
											.getText(colTotIndex - 1));
								} else {
									colFloatVal = 0;
								}
								items[j].setText(colTotIndex - 1, df
										.format(colFloatVal
												+ dto[i].getTotal_freight()));

							} else if (cbIISChoice.getText().equals(
									ACTUAL_WEIGHT)) {
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
								if (!items[j].getText(colTotIndex - 1).equals(
										"")) {
									colFloatVal = Float.parseFloat(items[j]
											.getText(colTotIndex - 1));
								} else {
									colFloatVal = 0;
								}
								items[j].setText(colTotIndex - 1, String
										.valueOf(colFloatVal
												+ dto[i].getActual_weight()));
							} else if (cbIISChoice.getText().equals(
									CHARGED_WEIGHT)) {
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
								if (!items[j].getText(colTotIndex - 1).equals(
										"")) {
									colFloatVal = Float.parseFloat(items[j]
											.getText(colTotIndex - 1));
								} else {
									colFloatVal = 0;
								}
								items[j].setText(colTotIndex - 1, df
										.format(colFloatVal
												+ dto[i].getCharged_weight()));
							} else if (cbIISChoice.getText().equals(NOA)) {
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
								if (!items[j].getText(colTotIndex - 1).equals(
										"")) {
									colVal = Integer.parseInt(items[j]
											.getText(colTotIndex - 1));
								} else {
									colVal = 0;
								}
								items[j].setText(colTotIndex - 1, String
										.valueOf(colVal + dto[i].getNoa()));
							}

						}
					}
				}
			}
		}

		if (cbIISChoice.getText().equals(COUNT)
				|| cbIISChoice.getText().equals(NOA)) {
			int finalVal = 0;
			for (int i = 0; i < itemsLen; i++) {
				// Final Total
				if (!items[i].getText(colTotIndex - 1).equals("")) {
					colVal = Integer
							.parseInt(items[i].getText(colTotIndex - 1));
				} else {
					colVal = 0;
				}

				finalVal = colVal + finalVal;

			}
			items[itemsLen - 1].setText(colTotIndex - 1, String
					.valueOf(finalVal));
		} else {
			float finalVal = 0;
			for (int i = 0; i < itemsLen; i++) {
				// Final Total
				if (!items[i].getText(colTotIndex - 1).equals("")) {
					colFloatVal = Float.parseFloat(items[i]
							.getText(colTotIndex - 1));
				} else {
					colFloatVal = 0;
				}

				finalVal = colFloatVal + finalVal;

			}
			items[itemsLen - 1].setText(colTotIndex - 1, df.format(finalVal));
		}

		fillZerosOnEmpty(tblIIStates);
		
	}
	
	
	private void fillZerosOnEmpty(Table refTbl) {
		
		TableItem[] items = refTbl.getItems();
		int len = items.length;		
		if (len > 0) {
			if(refTbl == tblIIStates){
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
	
	private void populateIIColumns() {

		tblIIStates.addListener(SWT.MeasureItem, new Listener() {
			public void handleEvent(Event event) {
				event.height = 28;
			}
		});

		for (int i = 0; i < STATES.length; i++) {
			TableItem item = new TableItem(tblIIStates, SWT.NONE);
			item.setText(0, STATES[i]);
		}

	}
	
	private String[] getIISHeading(Table refTable){
		
		String list[] = new String[3];
		
		list[0] = txtIISFrom.getText();
		list[1] = txtIISTo.getText();
		list[2] = cbIISChoice.getText();

			
		return list;
	}
	
	private ArrayList<TableDecorator> getIISTable(Table refTable) {
		TableDecorator dto = null;
		ArrayList<TableDecorator> list = new ArrayList<TableDecorator>();
		int length = 0;		

		TableItem[] items = tblIIStates.getItems();
		
		if (items != null) {
			length = items.length;
		}
		if (length > 0) {
			for (int i = 0; i < length; i++) {
				dto = new TableDecorator();			
				dto.setCol1(items[i].getText(0));
				dto.setCol2(items[i].getText(1));
				dto.setCol3(items[i].getText(2));
				dto.setCol4(items[i].getText(3));
				dto.setCol5(items[i].getText(4));
				dto.setCol6(items[i].getText(5));
				dto.setCol7(items[i].getText(6));			
			
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
	public void widgetDefaultSelected(SelectionEvent event) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void widgetSelected(SelectionEvent event) {
		// TODO Auto-generated method stub
		Object source = event.getSource();
		if (source == btnIISGo) {
			new sortListener().display("Generating Report...", STATUS_SUCCESS,
					SUCCESS_FONT_COLOR,lblStatusBar);

			handleIIS();
			new sortListener().display("Report Loaded Successfully!",
					STATUS_SUCCESS, SUCCESS_FONT_COLOR,lblStatusBar);
		}else if (source == btnExportXL) {
			try {
				//handleExcel();
				//if(selectedTab.equalsIgnoreCase(INTER_INTRA)){
					ArrayList<TableDecorator> list = null;
					String[] value = null;
					list = getIISTable(tblIIStates);
					value = getIISHeading(tblIIStates);
					new sortListener().prepareExcel(list, "Inter_Intra", IIS_EXCEL_JRXML,null,value,shell,lblStatusBar);
					
				//}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else if (source == btnPrint) {
			try {
				//handlePrint();
				//if(selectedTab.equalsIgnoreCase(INTER_INTRA)){
					ArrayList<TableDecorator> list = null;
					String[] value = null;
					list = getIISTable(tblIIStates);
					value = getIISHeading(tblIIStates);
					handler.preparePrint(list, "Inter_Intra", IIS_EXCEL_JRXML,null,value);
					
				//}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		else if (source == btnExportPDF) {
			try {
				//handlePDF();
				//if(selectedTab.equalsIgnoreCase(INTER_INTRA)){
					ArrayList<TableDecorator> list = null;
					String[] value = null;
					list = getIISTable(tblIIStates);
					value = getIISHeading(tblIIStates);
					new sortListener().preparePDF(list, "Inter_Intra", IIS_EXCEL_JRXML,null,value,shell,lblStatusBar);
					
				//}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
		
	
}
