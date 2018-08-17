package hm.akr.container.admin;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;



import hm.akr.common.MonthDialog;
import hm.akr.common.SWTResourceManager;
import hm.akr.common.BeanUtil;
import hm.akr.common.IUIConstants;
import hm.akr.container.history.HistoryHandler;
import hm.akr.dto.MarketVehicleDTO;
import hm.akr.dto.StationsDTO;
import hm.akr.dto.TableDecorator;
import hm.akr.dto.VehicleDTO;
import hm.akr.dto.VersionDTO;
import hm.akr.common.sortListener;

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

public class MarketVehicle  extends Composite implements IUIConstants,Listener,SelectionListener  {
	

	private TabItem tiMarketVehiUtilisation;
	private Canvas canMarkVehiUtil;
	private Table tblMarketVehicleUtilisation;
	private Button btnMargetVehicleGo;
	private Label lblMarkVehiUtilSelectMonth;
	private Text txtMarkVehiUtilSelectMonth;
	private Button btnMarkVehiUtilSelectMonth;
	private Label lblMarkVehiUtilSelectBranch;
	private Combo cbMarkVehiUtilSelectBranch;
	private String MARKET_VEHICLE = "Market Vehicle Utilisation";
	private static final String MARKET_VEHICLE_EXCEL_JRXML = "hm/akr/resources/printer/MarketVehicle.jrxml";

	
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
	
	 public MarketVehicle(Composite composite,int style) {
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

			tiMarketVehiUtilisation = new TabItem(tabReport, SWT.NONE);
			tiMarketVehiUtilisation.setText(MARKET_VEHICLE);
			{
				canMarkVehiUtil = new Canvas(tabReport, SWT.NONE);
				tiMarketVehiUtilisation.setControl(canMarkVehiUtil);
				{
					tblMarketVehicleUtilisation = new Table(canMarkVehiUtil,
							SWT.BORDER);
					tblMarketVehicleUtilisation.setHeaderVisible(true);
					tblMarketVehicleUtilisation.setLinesVisible(true);
					tblMarketVehicleUtilisation.setBounds(10, 37, 750, 400);

				}
				{
					lblMarkVehiUtilSelectMonth = new Label(canMarkVehiUtil,
							SWT.NONE);
					lblMarkVehiUtilSelectMonth.setText("Select Month");
					lblMarkVehiUtilSelectMonth.setBounds(15, 8, 78, 20);
				}

				{
					txtMarkVehiUtilSelectMonth = new Text(canMarkVehiUtil,
							SWT.BORDER);
					txtMarkVehiUtilSelectMonth.setBounds(96, 7, 58, 23);
					txtMarkVehiUtilSelectMonth.setEditable(false);
					DateFormat dateFormat = new SimpleDateFormat("MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					txtMarkVehiUtilSelectMonth.setText(date);
				}
				{
					btnMarkVehiUtilSelectMonth = new Button(canMarkVehiUtil,
							SWT.PUSH);
					btnMarkVehiUtilSelectMonth.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					// btnMarkVehiUtilSelectMonth.setText("Go");
					btnMarkVehiUtilSelectMonth.setBounds(158, 6, 26, 23);
					btnMarkVehiUtilSelectMonth.addSelectionListener(this);
				}

				{
					lblMarkVehiUtilSelectBranch = new Label(canMarkVehiUtil,
							SWT.NONE);
					lblMarkVehiUtilSelectBranch.setText("Select Branch");
					lblMarkVehiUtilSelectBranch.setBounds(250, 9, 74, 18);
				}
				{
					cbMarkVehiUtilSelectBranch = new Combo(canMarkVehiUtil,
							SWT.NONE);
					cbMarkVehiUtilSelectBranch.setBounds(330, 9, 117, 23);
					try{
						StationsDTO[] dto = handler.getAllBranches();
						if (null != dto) {
							cbMarkVehiUtilSelectBranch.add("All");
							for (int i = 0; i < dto.length; i++) {
								cbMarkVehiUtilSelectBranch.add(dto[i].getName()
										+ " - " + dto[i].getId());
							}
						}
						cbMarkVehiUtilSelectBranch.addSelectionListener(this);
					}
					catch (Exception e) {
						e.printStackTrace();
						// TODO: handle exception
					}
				}
				{
					btnMargetVehicleGo = new Button(canMarkVehiUtil, SWT.PUSH
							| SWT.CENTER);
					btnMargetVehicleGo.setText("Go");
					btnMargetVehicleGo.setBounds(454, 5, 37, 26);
					btnMargetVehicleGo.addSelectionListener(this);
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
		
	
private void createMarketTable() throws Exception {
		
		VehicleDTO[] dto = handler.getVehicles();
		
		if (null != dto) {
			tblMarketVehicleUtilisation.dispose();
			{
				tblMarketVehicleUtilisation = new Table(canMarkVehiUtil,
						SWT.BORDER);
				tblMarketVehicleUtilisation.setHeaderVisible(true);
				tblMarketVehicleUtilisation.setLinesVisible(true);
				tblMarketVehicleUtilisation.setBounds(10, 37, 750, 400);

			}
			int len = dto.length;
			if (len > 0) {
				TableColumn column = new TableColumn(
						tblMarketVehicleUtilisation, SWT.NONE);
				column.setText("Sno");
				column.setWidth(40);
				column = new TableColumn(tblMarketVehicleUtilisation, SWT.NONE);
				column.setText("Station Code");
				column.setWidth(80);
				for (int i = 0; i < len; i++) {
					column = new TableColumn(tblMarketVehicleUtilisation,
							SWT.NONE);
					column.setText(dto[i].getVehicle_model()
							+ "-No of vehicles");
					column.setWidth(120);

					column = new TableColumn(tblMarketVehicleUtilisation,
							SWT.NONE);
					column
							.setText(dto[i].getVehicle_model() + "-"
									+ " Amount");
					column.setWidth(100);
					/*column.setText(dto[i].getVehicle_model());
					column.setWidth(220);
					TableRow column1 = new TableRow(tblMarketVehicleUtilisation,1);
					column1.setText("No of vehicles");
					column1.setWidth(120);
					column1 = new TableRow(tblMarketVehicleUtilisation,
							SWT.NONE);
					column1.setText("Amount");
					column1.setWidth(100);*/
				}
				for (int index = 0; index < tblMarketVehicleUtilisation
						.getColumnCount(); index++) {
					tblMarketVehicleUtilisation.getColumn(index).addListener(
							SWT.Selection,this);
				}

			}
		}
	}

	private void handleMarketVehicle() {

		try {

			String monthyear = txtMarkVehiUtilSelectMonth.getText();
			int index = monthyear.indexOf("-");
			int month = Integer.parseInt(monthyear.substring(0, index).trim());
			int year = Integer.parseInt(monthyear.substring(index + 1).trim());

			if (null != handler) {
				// System.out.println(month + " " + year);
				MarketVehicleDTO[] dto = handler.getMarketVehicles(
						getSelectedBranch(cbMarkVehiUtilSelectBranch), month,
						year);
				if (null != dto) {
					populateMarketVehicle(dto);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String getSelectedBranch(Combo cb) throws Exception {
		int index = cb.getSelectionIndex();
		if (index > -1) {
			if (index == 0)
				return "%";
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
	
	private void populateMarketVehicle(MarketVehicleDTO[] dto) throws Exception {
		
		if (null != tblMarketVehicleUtilisation) {
			if (null != dto) {
				
				int len = dto.length;
				if (len > 0) {
						
					for (int j = 0; j < len; j++) {
						TableItem item = isTableItemAvailForMarket(getBranch_code(dto[j].getTo_station()));
						if (null == item){
							item = new TableItem(tblMarketVehicleUtilisation,
									SWT.NONE);
						}
						TableColumn[] columns = tblMarketVehicleUtilisation
								.getColumns();
						//item.setText(1, getBranch_code(dto[j].getTo_station()));
						for (int k = 2; k < columns.length;) {
							//if(!(cbMarkVehiUtilSelectBranch.getText().equalsIgnoreCase("All"))){
													 
							String name = columns[k].getText().substring(0,columns[k].getText().indexOf("-"));
							
							if (name.equalsIgnoreCase(dto[j].getVehicle_name())) {
								item.setText(k++, String.valueOf(dto[j]
										.getNo_vehicle()));
								item.setText(k++, getRoundedValue(dto[j]
										.getAmount()));
							} else
								k = k + 2;
						}
					}

					int columns = tblMarketVehicleUtilisation.getColumnCount();
					int[] no = new int[(columns - 2)];
					float[] amount = null;
					amount = new float[(columns - 2)];
					int one = 0;
					int two = 0;
					isTotalTableItemAvailForMarket("Total");
					for (int j = 2; j < columns; j++) {

						TableItem items[] = tblMarketVehicleUtilisation
								.getItems();
						int len1 = items.length;

						for (int i = 0; i < len1; i++) {
							if (j % 2 == 0) {
								if(!items[i].getText(j).equals("")){
								no[one] = no[one]
										+ Integer.parseInt(items[i].getText(j));
								}
							} else {
								if(!items[i].getText(j).equals("")){
								amount[two] = amount[two]
										+ Float.parseFloat(items[i].getText(j));
								}
								//System.out.println(amount[two]);
							}
						}
						if (j % 2 == 0) {
							one++;

						} else {
							two++;
						}
					}

					if (null != no) {
						int size = no.length;
						if (size > 0) {
							TableItem item = null;
							item = isTotalTableItemAvailForMarket("Total");
							if (item == null)
								item = new TableItem(
										tblMarketVehicleUtilisation, SWT.NONE);
							Font font1 = new Font(Display.getDefault(), "Tahoma", 8, SWT.BOLD);
							item.setFont(font1);
							item.setText(1, String.valueOf("Total"));
							int index = 2;
							for (int k = 0; k < size; k++) {
								item.setText(index++, String.valueOf(no[k]));
								item
										.setText(index++, getRoundedValue(amount[k]));
							}
						}
					}

				}
			}
		}

	}

	private TableItem isTableItemAvailForMarket(String to_station) {
		if (null != tblMarketVehicleUtilisation) {
			TableItem[] items = tblMarketVehicleUtilisation.getItems();
			if (null != items) {
				int len = items.length;
				if (len > 0) {
					for (int i = 0; i < len; i++) {
						if (items[i].getText(1).equalsIgnoreCase(to_station)) {
							return items[i];
						}
					}
				}
			}
		}
		return null;

	}

	private TableItem isTotalTableItemAvailForMarket(String to_station) {
		if (null != tblMarketVehicleUtilisation) {
			TableItem[] items = tblMarketVehicleUtilisation.getItems();
			if (null != items) {
				int len = items.length;
				if (len > 0) {
					for (int i = 0; i < len; i++) {
						if (items[i].getText(0).equalsIgnoreCase(to_station)) {
							items[i].dispose();
							return null;
						}
					}
				}
			}
		}
		return null;

	}
	private void populateDefaultTable(String branch, Table tblName, int cols)
	throws Exception {
		StationsDTO[] dto = null;
	
		dto = handler.getAllBranches();
		
		if(dto != null){
			int len = dto.length;
			for (int i = 0; i < len; i++) {
				TableItem item = new TableItem(tblName, SWT.NONE);
				item.setText(0, String.valueOf(i + 1));
				item.setText(1, dto[i].getBranch_code());
				int size = tblName.getColumnCount();
				for (int j = 2; j < size; j++) {
					item.setText(j, String.valueOf(0));
				}
			}			
	
		}
	}
	
	private String[] getMarketvehicleHeading(Table refTable){
		
		String list[] = new String[2];
		
		list[0] = txtMarkVehiUtilSelectMonth.getText();
		
		String branchCode = cbMarkVehiUtilSelectBranch.getText();
		if (!branchCode.equalsIgnoreCase("All")) {
			int index = branchCode.indexOf(" - ");
			list[1] = branchCode.substring(index + 3);
		}
		else
			list[1] ="All";
			
		return list;
	}

	@Override
	public void handleEvent(Event e) {
		// TODO Auto-generated method stub
		String selection = tabReport.getSelection()[0].getText();
		TableColumn column = (TableColumn) e.widget;
		
		TableColumn[] cols = tblMarketVehicleUtilisation.getColumns();
		int len = cols.length;
		for (int i = 0; i < len; i++) {

			if (i != 1 && column == cols[i]) {
				new sortListener().sortByFloat(i, tblMarketVehicleUtilisation);
			} else if (i == 1 && column == cols[i]) {
				new sortListener().sortByString(i, tblMarketVehicleUtilisation);
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
		if (source == cbMarkVehiUtilSelectBranch) {
				try {
					//createMarketTable();
					//populateDefaultTable(cbMarkVehiUtilSelectBranch.getText(),tblMarketVehicleUtilisation, 0);
				} catch (Exception e) {			
					e.printStackTrace();
				}
			}

			else if (btnMarkVehiUtilSelectMonth == source) {
				MonthDialog cd = new MonthDialog(shell);
				Object obj = cd.open();
				if (obj != null) {
					txtMarkVehiUtilSelectMonth.setText(obj.toString());
				}
			} else if (source == btnMargetVehicleGo) {
				new sortListener().display("Generating Report...", STATUS_SUCCESS,
						SUCCESS_FONT_COLOR,lblStatusBar);
				try {
					
					createMarketTable();
					populateDefaultTable(cbMarkVehiUtilSelectBranch.getText(),tblMarketVehicleUtilisation, 0);
				} catch (Exception e) {				
					e.printStackTrace();
				}
				handleMarketVehicle();

				new sortListener().display("Report Loaded Successfully!",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR,lblStatusBar);
			} 
		else if (source == btnExportXL) {
			try {
				//handleExcel();
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				list = new sortListener().getBPATable(tblMarketVehicleUtilisation);
				value = getMarketvehicleHeading(tblMarketVehicleUtilisation);
				TableColumn[] col = tblMarketVehicleUtilisation.getColumns();
				if(col != null){
					int len = col.length;
					String param[] = new String[len];
					for(int i = 0; i < len; i++){
						param[i] = col[i].getText();
					}
					new sortListener().prepareExcel(list, "Market Vehicle", MARKET_VEHICLE_EXCEL_JRXML, param,value,shell,lblStatusBar);
				}			
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else if (source == btnPrint) {
			try {
				//handlePrint();
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				list = new sortListener().getBPATable(tblMarketVehicleUtilisation);
				value = getMarketvehicleHeading(tblMarketVehicleUtilisation);
				TableColumn[] col = tblMarketVehicleUtilisation.getColumns();
				if(col != null){
					int len = col.length;
					String param[] = new String[len];
					for(int i = 0; i < len; i++){
						param[i] = col[i].getText();
					}
					handler.preparePrint(list, "Market Vehicle", MARKET_VEHICLE_EXCEL_JRXML, param,value);
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
				list = new sortListener().getBPATable(tblMarketVehicleUtilisation);
				value = getMarketvehicleHeading(tblMarketVehicleUtilisation);
				TableColumn[] col = tblMarketVehicleUtilisation.getColumns();
				if(col != null){
					int len = col.length;
					String param[] = new String[len];
					for(int i = 0; i < len; i++){
						param[i] = col[i].getText();
					}
					new sortListener().preparePDF(list, "Market Vehicle", MARKET_VEHICLE_EXCEL_JRXML, param,value,shell,lblStatusBar);
				}			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		
	}

}