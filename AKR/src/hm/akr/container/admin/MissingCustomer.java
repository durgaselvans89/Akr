package hm.akr.container.admin;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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

import hm.akr.common.sortListener;
import hm.akr.common.BeanUtil;
import hm.akr.common.IUIConstants;
import hm.akr.container.history.HistoryHandler;
import hm.akr.dto.MissingCustomersDTO;
import hm.akr.dto.TableDecorator;
import hm.akr.dto.VersionDTO;



public class MissingCustomer extends Composite implements IUIConstants,SelectionListener,Listener {

	
	private TableColumn colMissCustCustomerName;

	private TableColumn colMissCustHighTotalFreight;

	private TableColumn colMissCustHighMonth;

	private TableColumn colMissCustLowTotalFreight;

	private TableColumn colMissCustLowMonth;

	private TableColumn colMissCustLastMonth;

	private TableColumn colMissCustCurrentMonth;
	private TabItem tiMissingCustomer;
	private Canvas canMissingCustomer;
	private Table tblMissingCustomer;
	private TableColumn colMCsno;
	private Button btnMSgo;
	private Label lblMSpercent;
	private Label lblStatusBar;
	private Combo cbMCpercent;
	private Combo cbMCOption;
	private MissingCustomersDTO[] mcDto;
	private static final String MISSINGCUSTOMER_EXCEL_JRXML = "hm/akr/resources/printer/Missing_Customer.jrxml";
	private String MISSING_CUSTOMER = "Missing Customer";
	
	private TabFolder tabReport = null;
	VersionDTO[] vDto = null;
	private Shell shell = null;
	DateFormat dateFormat = null;
	private Button btnPrint;
	private Button btnExportXL;
	private Button btnExportPDF;
	private ReportsMenuHandler handler;
	HistoryHandler historyH = null;
	
	
	public MissingCustomer(Composite composite,int style) {
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
			tiMissingCustomer = new TabItem(tabReport, SWT.NONE);
			tiMissingCustomer.setText(MISSING_CUSTOMER);

			canMissingCustomer = new Canvas(tabReport, SWT.NONE);
			tiMissingCustomer.setControl(canMissingCustomer);
			{
				tblMissingCustomer = new Table(canMissingCustomer,
						SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL
								| SWT.BORDER);
				tblMissingCustomer.setBounds(40, 49, 685, 418);

				tblMissingCustomer.setHeaderVisible(true);
				tblMissingCustomer.setLinesVisible(true);

				{
					colMCsno = new TableColumn(tblMissingCustomer, SWT.NONE);
					colMCsno.setText("SNO");
					colMCsno.setWidth(45);
				}
				{
					colMissCustCustomerName = new TableColumn(
							tblMissingCustomer, SWT.NONE);
					colMissCustCustomerName.setText("Customer Name");
					colMissCustCustomerName.setWidth(95);

				}
				{
					colMissCustHighTotalFreight = new TableColumn(
							tblMissingCustomer, SWT.NONE);
					colMissCustHighTotalFreight
							.setText("Highest Total Freight-");
					colMissCustHighTotalFreight.setWidth(119);

				}
				{
					colMissCustHighMonth = new TableColumn(tblMissingCustomer,
							SWT.NONE);
					colMissCustHighMonth.setText("Month");
					colMissCustHighMonth.setWidth(62);

				}
				{
					colMissCustLowTotalFreight = new TableColumn(
							tblMissingCustomer, SWT.NONE);
					colMissCustLowTotalFreight.setText("Lowest Total Freight-");
					colMissCustLowTotalFreight.setWidth(119);

				}
				{
					colMissCustLowMonth = new TableColumn(tblMissingCustomer,
							SWT.NONE);
					colMissCustLowMonth.setText("Month");
					colMissCustLowMonth.setWidth(60);

				}
				{
					colMissCustLastMonth = new TableColumn(tblMissingCustomer,
							SWT.NONE);
					colMissCustLastMonth.setText("Last Month");
					colMissCustLastMonth.setWidth(68);

				}
				{
					colMissCustCurrentMonth = new TableColumn(
							tblMissingCustomer, SWT.NONE);
					colMissCustCurrentMonth.setText("Current Month");
					colMissCustCurrentMonth.setWidth(85);

				}

				for (int index = 0; index < tblMissingCustomer.getColumnCount(); index++) {
					tblMissingCustomer.getColumn(index).addListener(
							SWT.Selection, this);
				}

			}

			{
				lblMSpercent = new Label(canMissingCustomer, SWT.NONE);
				lblMSpercent.setText("Business Drop %");
				lblMSpercent.setBounds(170, 13, 86, 22);
			}
			{
				cbMCpercent = new Combo(canMissingCustomer, SWT.NONE);
				cbMCpercent.setBounds(263, 13, 81, 19);
				cbMCpercent.add("50");
				cbMCpercent.add("75");
				cbMCpercent.add("100");
				cbMCpercent.addSelectionListener(this);

			}
			{
				btnMSgo = new Button(canMissingCustomer, SWT.PUSH | SWT.CENTER);
				btnMSgo.setText("Go");
				btnMSgo.setBounds(469, 12, 38, 24);
				btnMSgo.addSelectionListener(this);
			}
			{
				cbMCOption = new Combo(canMissingCustomer, SWT.READ_ONLY);
				cbMCOption.setBounds(356, 13, 105, 21);
				cbMCOption.add("BFT");
				cbMCOption.add("Charged Weight");
				cbMCOption.addSelectionListener(this);
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
	

	
	
	private void handleMissingCustomer() {
		try {
			if (mcDto == null) {
				mcDto = handler.getMissingCustomers();
				MissingCustomersDTO[] mcLastYrDto = handler
						.getMissingCustomersLastYear();
				
				if (mcDto != null && mcLastYrDto != null) {

					mcDto = arrangeCurrentMonth(mcDto);
					
					mcLastYrDto = arrangeLastYr(mcLastYrDto);
					for (int i = 0; i < mcDto.length; i++) {
						String customer = mcDto[i].getCustomer();
						boolean isAvail = false;
						int ind = -1;
						for (int j = 0; j < mcLastYrDto.length; j++) {
							if (customer.equalsIgnoreCase(mcLastYrDto[j]
									.getCustomer())) {
								
								isAvail = true;
								ind = j;
								break;
							}
						}
						if (isAvail) {
							mcDto[i].setMaxBft(mcLastYrDto[ind].getMaxBft());
							mcDto[i].setMinBft(mcLastYrDto[ind].getMinBft());
							mcDto[i].setMaxCW(mcLastYrDto[ind].getMaxCW());
							mcDto[i].setMinCW(mcLastYrDto[ind].getMinCW());
							mcDto[i].setMaxBftDate(mcLastYrDto[ind]
									.getMaxBftDate());
							mcDto[i].setMinBftDate(mcLastYrDto[ind]
									.getMinBftDate());
							mcDto[i].setMaxCWDate(mcLastYrDto[ind]
									.getMaxCWDate());
							mcDto[i].setMinCWDate(mcLastYrDto[ind]
									.getMinCWDate());
						}
					}
					
					populateMCtable(mcDto);
				}
			} else {
				populateMCtable(mcDto);
			}

		} catch (Exception e) {

		}

	}

	private MissingCustomersDTO[] arrangeCurrentMonth(
			MissingCustomersDTO[] mcDto) {

		int len = mcDto.length;
		ArrayList<MissingCustomersDTO> list = new ArrayList<MissingCustomersDTO>();
		for (int i = 0; i < len; i++) {
			if (i == 0) {
				list.add(mcDto[i]);
			} else {
				boolean isAvail = false;
				int index = -1;
				for (int j = 0; j < list.size(); j++) {
					if (mcDto[i].getCustomer().equalsIgnoreCase(
							list.get(j).getCustomer())) {
						isAvail = true;
						index = j;
						break;
					}

				}

				if (isAvail) {
					if (mcDto[i].getLastMonthBft() > 0) {
						list.get(index).setLastMonthBft(
								mcDto[i].getLastMonthBft());
					}
					if (mcDto[i].getLastMonthCW() > 0) {
						list.get(index).setLastMonthCW(
								mcDto[i].getLastMonthCW());
					}
					if (mcDto[i].getCurrMonthBft() > 0) {
						list.get(index).setCurrMonthBft(
								mcDto[i].getCurrMonthBft());
					}
					if (mcDto[i].getCurrMonthCW() > 0) {
						list.get(index).setCurrMonthCW(
								mcDto[i].getCurrMonthCW());
					}
				} else {
					list.add(mcDto[i]);
				}

			}

		}

		int size = list.size();
		if (size > 0) {
			return (MissingCustomersDTO[]) list
					.toArray(new MissingCustomersDTO[size]);
		}

		return null;
	}

	private float getRounded2Decimal(float value) {

		if (value == 0) {
			return 0;
		} else {
			value = value * 100;
			value = (float) Math.ceil(value);
			value = value / 100;
		}
		return value;
	}

	private void populateMCtable(MissingCustomersDTO[] mcDto) {
		if (tblMissingCustomer != null)
			tblMissingCustomer.removeAll();
		
		int len = mcDto.length;
		DateFormat dateFormat = new SimpleDateFormat("MMM-yyyy");
		float max = 0;
		float lastMonth = 0;
		float percent = 0;
		for (int i = 0, j = 1; i < len; i++) {
			if (cbMCOption.getText().equalsIgnoreCase("BFT")) {
				max = mcDto[i].getMaxBft();
				lastMonth = mcDto[i].getLastMonthBft();
				 //System.out.println("lm-->"+lastMonth);
				if (!cbMCpercent.getText().equalsIgnoreCase("")) {
					percent = (max * Integer.parseInt(cbMCpercent.getText())) / 100;
				}

				max = max - percent;
				max = getRounded2Decimal(max);
				lastMonth = getRounded2Decimal(lastMonth);
				// System.out.println(cbMCpercent.getText() + "-- perc-->" +
				// percent + "max-->" + max + "LM-->" + lastMonth);
				if (max == lastMonth) {
					TableItem item = new TableItem(tblMissingCustomer, SWT.NONE);
					item.setText(0, String.valueOf(j));
					item.setText(1, mcDto[i].getCustomer());
					
					item.setText(2, getRoundedValue(mcDto[i].getMaxBft()));
					item
							.setText(3, dateFormat.format(mcDto[i]
									.getMaxBftDate()));
					item.setText(4, getRoundedValue(mcDto[i].getMinBft()));
					item
							.setText(5, dateFormat.format(mcDto[i]
									.getMinBftDate()));
					item
							.setText(6, getRoundedValue(mcDto[i]
									.getLastMonthBft()));
					item
							.setText(7, getRoundedValue(mcDto[i]
									.getCurrMonthBft()));
					j++;
				}
			} else if (cbMCOption.getText().equalsIgnoreCase("Charged Weight")) {
				max = mcDto[i].getMaxCW();
				lastMonth = mcDto[i].getLastMonthCW();

				if (!cbMCpercent.getText().equalsIgnoreCase("")) {
					percent = (max * Integer.parseInt(cbMCpercent.getText())) / 100;
				}
				max = max - percent;
				max = getRounded2Decimal(max);
				lastMonth = getRounded2Decimal(lastMonth);
				if (max == lastMonth) {
					TableItem item = new TableItem(tblMissingCustomer, SWT.NONE);
					item.setText(0, String.valueOf(j));
					item.setText(1, mcDto[i].getCustomer());
					item.setText(2, getRoundedValue(mcDto[i].getMaxCW()));
					item.setText(3, dateFormat.format(mcDto[i].getMaxCWDate()));
					item.setText(4, getRoundedValue(mcDto[i].getMinCW()));
					item.setText(5, dateFormat.format(mcDto[i].getMinCWDate()));
					item.setText(6, getRoundedValue(mcDto[i].getLastMonthCW()));
					item.setText(7, getRoundedValue(mcDto[i].getCurrMonthCW()));
					j++;
				}
			}

		}
		calcMCTotal();
	}

	private void calcMCTotal() {

		TableItem[] items = tblMissingCustomer.getItems();

		float tot6 = 0;
		float tot7 = 0;

		if (items != null) {
			for (int i = 0; i < items.length; i++) {
				if (!items[i].getText(6).equals(""))
					tot6 = tot6 + Float.parseFloat(items[i].getText(6));
				if (!items[i].getText(7).equals(""))
					tot7 = tot7 + Float.parseFloat(items[i].getText(7));
			}

		}

		final  TableItem item1 = new TableItem(tblMissingCustomer, SWT.NONE);
		Font font1 = new Font(Display.getCurrent(), "Tahoma", 8, SWT.BOLD);
		item1.setFont(font1);
		item1.setText(2, "TOTAL");
		item1.setText(6, getRoundedValue(tot6));
		item1.setText(7, getRoundedValue(tot7));

	}

	private MissingCustomersDTO[] arrangeLastYr(
			MissingCustomersDTO[] mcLastYrDto) {
		int len = mcLastYrDto.length;
		ArrayList<MissingCustomersDTO> list = new ArrayList<MissingCustomersDTO>();
		for (int i = 0; i < len; i++) {
			if (i == 0) {
				mcLastYrDto[i].setMinBft(mcLastYrDto[i].getMaxBft());
				mcLastYrDto[i].setMinCW(mcLastYrDto[i].getMaxCW());
				mcLastYrDto[i].setMaxBftDate(mcLastYrDto[i].getMaxBftDate());
				mcLastYrDto[i].setMinBftDate(mcLastYrDto[i].getMaxBftDate());
				mcLastYrDto[i].setMaxCWDate(mcLastYrDto[i].getMaxBftDate());
				mcLastYrDto[i].setMinCWDate(mcLastYrDto[i].getMaxBftDate());
				list.add(mcLastYrDto[i]);
			} else {
				boolean isAvail = false;
				int index = -1;
				for (int j = 0; j < list.size(); j++) {
					if (mcLastYrDto[i].getCustomer().equalsIgnoreCase(
							list.get(j).getCustomer())) {
						isAvail = true;
						index = j;
						break;
					}

				}

				if (isAvail) {
					float maxBft = mcLastYrDto[i].getMaxBft();
					// float minBft = mcLastYrDto[i].getMinBft();

					float maxCW = mcLastYrDto[i].getMaxCW();
					// float minCW = mcLastYrDto[i].getMinCW();
					// BFT
					if (maxBft > list.get(index).getMaxBft()) {
						list.get(index).setMaxBft(maxBft);
						list.get(index).setMaxBftDate(
								mcLastYrDto[i].getMaxBftDate());
					}

					if (maxBft < list.get(index).getMinBft()) {
						list.get(index).setMinBft(maxBft);
						list.get(index).setMinBftDate(
								mcLastYrDto[i].getMaxBftDate());
					}

					// CW
					if (maxCW > list.get(index).getMaxCW()) {
						list.get(index).setMaxCW(maxCW);
						list.get(index).setMaxCWDate(
								mcLastYrDto[i].getMaxBftDate());
					}

					if (maxCW < list.get(index).getMinCW()) {
						list.get(index).setMinCW(maxCW);
						list.get(index).setMinCWDate(
								mcLastYrDto[i].getMaxBftDate());
					}

				} else {
					mcLastYrDto[i].setMinBft(mcLastYrDto[i].getMaxBft());
					mcLastYrDto[i].setMinCW(mcLastYrDto[i].getMaxCW());
					mcLastYrDto[i]
							.setMaxBftDate(mcLastYrDto[i].getMaxBftDate());
					mcLastYrDto[i]
							.setMinBftDate(mcLastYrDto[i].getMaxBftDate());
					mcLastYrDto[i].setMaxCWDate(mcLastYrDto[i].getMaxBftDate());
					mcLastYrDto[i].setMinCWDate(mcLastYrDto[i].getMaxBftDate());

					list.add(mcLastYrDto[i]);
				}

			}

		}

		int size = list.size();
		if (size > 0) {
			return (MissingCustomersDTO[]) list
					.toArray(new MissingCustomersDTO[size]);
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
	
	private String[] getMissingCustHeading(Table refTable){
		
		String list[] = new String[2];
		
		if(cbMCpercent.getText().equals("50"))
			list[0] = "50";
		else if(cbMCpercent.getText().equals("75"))
			list[0] = "75";
		else
			list[0] = "100";
		if(cbMCOption.getText().equals("BFT"))
			list[1] = "BFT";
		else
			list[1] = "Charged Weight";
		
			
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
		if (source == btnMSgo) {
			new sortListener().display("Generating Report...", STATUS_SUCCESS,
					SUCCESS_FONT_COLOR,lblStatusBar);

			handleMissingCustomer();
			new sortListener().display("Report Loaded Successfully!",
					STATUS_SUCCESS, SUCCESS_FONT_COLOR,lblStatusBar);
		}else if(source == btnExportXL){
			//if (selectedTab.equalsIgnoreCase(SUNDRY_BOOKING_TAB)) {
			try{
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				list = new sortListener().getBPATable(tblMissingCustomer);
				value = getMissingCustHeading(tblMissingCustomer);
				new sortListener().prepareExcel(list, "Missing_Customer", MISSINGCUSTOMER_EXCEL_JRXML,	null,value,shell,lblStatusBar);
				
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}else if (source == btnPrint) {
			try {
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				list = new sortListener().getBPATable(tblMissingCustomer);
				value = getMissingCustHeading(tblMissingCustomer);
				handler.preparePrint(list, "Missing_Customer", MISSINGCUSTOMER_EXCEL_JRXML,	null,value);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		else if (source == btnExportPDF) {
			try {
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				list = new sortListener().getBPATable(tblMissingCustomer);
				value = getMissingCustHeading(tblMissingCustomer);
				new sortListener().preparePDF(list, "Missing_Customer", MISSINGCUSTOMER_EXCEL_JRXML,null,value,shell,lblStatusBar);
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
		
		//else if (selection == MISSING_CUSTOMER) {
			if (column == colMCsno) {
				new sortListener().sortByFloat(0, tblMissingCustomer);
			} else if (column == colMissCustCustomerName) {
				new sortListener().sortByString(1, tblMissingCustomer);
			} else if (column == colMissCustHighTotalFreight) {
				new sortListener().sortByFloat(2, tblMissingCustomer);
			} else if (column == colMissCustHighMonth) {
				new sortListener().sortByString(3, tblMissingCustomer);
			} else if (column == colMissCustLowTotalFreight) {
				new sortListener().sortByFloat(4, tblMissingCustomer);
			} else if (column == colMissCustLowMonth) {
				new sortListener().sortByString(5, tblMissingCustomer);
			} else if (column == colMissCustLastMonth) {
				new sortListener().sortByFloat(6, tblMissingCustomer);
			} else if (column == colMissCustCurrentMonth) {
				new sortListener().sortByFloat(7, tblMissingCustomer);
			}
		//}
		
	}
	

}
