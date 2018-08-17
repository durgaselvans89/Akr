package hm.akr.container.admin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import hm.akr.common.BeanUtil;
import hm.akr.common.IUIConstants;
import hm.akr.common.KalendarDialog;
import hm.akr.common.SWTResourceManager;
import hm.akr.dto.BookedLRDTO;
import hm.akr.dto.StationsDTO;
import hm.akr.dto.VersionDTO;
import hm.akr.common.sortListener;
import hm.akr.container.admin.MonitorReportsComposite.MoveAction;
import hm.akr.container.history.HistoryHandler;

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
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.TableItem;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

public class BookedLrs extends Composite implements SelectionListener,IUIConstants,Listener{
	
	AdminCompositeHandler handler = null;
	VersionDTO[] versiondto = null;
	BeanUtil beanutil = null;
	private Shell shell = null;
	private String SERVER_DATE = "";
	private TabFolder tabReport;
	private Button btnPrint;
	private Button btnExportXL;
	private Button btnExportPDF;
	private Display display;
	
	
	
	Date reportDate = null;

	

	private Canvas canvas7;

	private Table tblBookedLR;

	private TableColumn colLsno;

	private TableColumn tblcolLStationName;

	private TableColumn colLRPaid;

	private TableColumn colLRTopay;

	private TableColumn colLRBilling;

	private Label lblLfrom;

	private Text txtLFrom;

	private Button btnLFrom;

	private Label lblLTo;

	private Text txtLTo;

	private Button btnLTo;

	private Button btnLGo;

	private TableColumn colLRTotal;
	
	private TabItem tiBookedLR;
	
	private Label label1;
	
	private Combo cbLSB; 

	VersionDTO[] vDto = null;
	HistoryHandler historyH = null;
	
		
	public BookedLrs(Composite composite, int style) throws Exception {
		super(composite, style);

		try{
			shell = composite.getShell();

			handler = new AdminCompositeHandler();
			beanutil = BeanUtil.getInstance();
			SERVER_DATE = beanutil.getServerDate();
			historyH = new HistoryHandler();
			vDto = historyH.getHistoryYears();
			if(vDto != null && vDto.length > 0){
				BeanUtil.setThree_month_updated(vDto[0].getUpdated_date());
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		

	}
	
	public Composite loadcomposite() {
		
		this.setBounds(45,100, 1020,700);
		tabReport = new TabFolder(this, SWT.NONE);
		tabReport.setBounds(50, 25, 820, 500);
		tabReport.addSelectionListener(this);
		{
		tiBookedLR = new TabItem(tabReport, SWT.NONE);
		tiBookedLR.setText("BookedLR'S");

		canvas7 = new Canvas(tabReport, SWT.NONE);
		tiBookedLR.setControl(canvas7);

		{
			label1 = new Label(canvas7, SWT.NONE);
			label1.setText("Select");
			label1.setBounds(4, 11, 31, 15);
		}

		{
			cbLSB = new Combo(canvas7, SWT.DROP_DOWN
					| SWT.READ_ONLY);
			cbLSB.setBounds(40, 9, 150, 21);

			cbLSB.addSelectionListener(this);
			populateLRBranches();
		}
		
		{

			{
				tblBookedLR = new Table(canvas7, SWT.FULL_SELECTION
						| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);

				tblBookedLR.setHeaderVisible(true);
				tblBookedLR.setLinesVisible(true);
				tblBookedLR.setBounds(50, 58, 556, 400);
				{
					colLsno = new TableColumn(tblBookedLR, SWT.NONE);
					colLsno.setText("S NO");
					colLsno.setWidth(40);

				}
				{
					tblcolLStationName = new TableColumn(
							tblBookedLR, SWT.NONE);
					tblcolLStationName.setText("Station");
					tblcolLStationName.setWidth(180);

				}
				{
					colLRTopay = new TableColumn(tblBookedLR,
							SWT.NONE);
					colLRTopay.setText("Topay");
					colLRTopay.setWidth(85);

				}
				{
					colLRPaid = new TableColumn(tblBookedLR,
							SWT.NONE);
					colLRPaid.setText("Paid");
					colLRPaid.setWidth(80);

				}

				{
					colLRBilling = new TableColumn(tblBookedLR,
							SWT.NONE);
					colLRBilling.setText("Billing");
					colLRBilling.setWidth(80);

				}

				{
					colLRTotal = new TableColumn(tblBookedLR,
							SWT.NONE);
					colLRTotal.setText("Total");
					colLRTotal.setWidth(80);

				}

			}
			{
				lblLfrom = new Label(canvas7, SWT.NONE);
				lblLfrom.setText("From");
				lblLfrom.setBounds(220, 9, 28, 17);
			}

			{
				txtLFrom = new Text(canvas7, SWT.BORDER);
				txtLFrom.setBounds(254, 9, 94, 22);
				txtLFrom.setEditable(false);
				DateFormat dateFormat = new SimpleDateFormat(
						"dd-MM-yyyy");
				java.util.Date currentDate = new java.util.Date();
				String date = dateFormat.format(currentDate);
				if (SERVER_DATE != null) {
					txtLFrom.setText(SERVER_DATE);
				} else {
					txtLFrom.setText(date);
				}

			}
			{
				btnLFrom = new Button(canvas7, SWT.PUSH
						| SWT.CENTER);
				btnLFrom.setBounds(350, 8, 31, 23);
				btnLFrom.setImage(SWTResourceManager
						.getImage("hm/akr/resources/Calendar.jpg"));
				btnLFrom.addSelectionListener(this);
			}
			{
				lblLTo = new Label(canvas7, SWT.NONE);
				lblLTo.setText("To");
				lblLTo.setBounds(410, 11, 24, 17);
			}
			{
				txtLTo = new Text(canvas7, SWT.BORDER);
				txtLTo.setBounds(435, 9, 92, 22);
				txtLTo.setEditable(false);
				DateFormat dateFormat = new SimpleDateFormat(
						"dd-MM-yyyy");
				java.util.Date currentDate = new java.util.Date();
				String date = dateFormat.format(currentDate);
				if (SERVER_DATE != null) {
					txtLTo.setText(SERVER_DATE);
				} else {
					txtLTo.setText(date);
				}
			}
			{
				btnLTo = new Button(canvas7, SWT.PUSH | SWT.CENTER);
				btnLTo.setBounds(528, 8, 32, 23);
				btnLTo.setImage(SWTResourceManager
						.getImage("hm/akr/resources/Calendar.jpg"));
				btnLTo.addSelectionListener(this);
			}
			{
				btnLGo = new Button(canvas7, SWT.PUSH | SWT.CENTER);
				btnLGo.setText("GO");
				btnLGo.setBounds(572, 8, 35, 23);
				btnLGo.addSelectionListener(this);
			}
		}
	
		}
		
		/*{
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
	
	private void populateLRBranches() {
		try {
			StationsDTO[] stations = handler.getAllBranches();
			if (null != stations) {
				int len = stations.length;
				for (int i = 0; i < len; i++) {
					if (null != cbLSB)
						cbLSB.add(stations[i].getName() + " - "
								+ stations[i].getId());
					/*if (null != cbtbranch)
						cbtbranch.add(stations[i].getName() + " - "
								+ stations[i].getId());*/
				}
				if (null != cbLSB)
					cbLSB.select(0);
				/*if (null != cbtbranch)
					cbtbranch.select(0);*/
			}
		} catch (Exception exception) {
			exception.printStackTrace();
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
						//item.setText(1, branchCode);
						item.setText(1, stations[i].getId());
						item.setText(2, String.valueOf(0));
						item.setText(3, String.valueOf(0));
						item.setText(4, String.valueOf(0));
						item.setText(5, String.valueOf(0));

						sNo += 1;
					}
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
	private void populateBookedLRs(Date fromDate, Date toDate, String branchCode)
	throws Exception {
		BookedLRDTO[] bookedLRDto = null;
		String stationCode = null;
		
		try {
			bookedLRDto = getBookedLRs(fromDate, toDate, branchCode);
			if (null != bookedLRDto) {
				int len = bookedLRDto.length;
				if (null != tblBookedLR) {
					tblBookedLR.removeAll();
				}
				int length = 0;
				if (len > 0) {
					TableItem item = new TableItem(tblBookedLR, SWT.NONE);
					item.setText(0, String.valueOf(1));
					item.setText(1, bookedLRDto[0].getStationCode());
					item.setText(2, String.valueOf(0));
					item.setText(3, String.valueOf(0));
					item.setText(4, String.valueOf(0));
					item.setText(5, String.valueOf(0));
		
					if (bookedLRDto[0].getLrType().equalsIgnoreCase("Topay")) {
						item.setText(2, String.valueOf(bookedLRDto[0]
								.getLrCount()));
					} else if (bookedLRDto[0].getLrType().equalsIgnoreCase(
							"Paid")) {
						item.setText(3, String.valueOf(bookedLRDto[0]
								.getLrCount()));
					} else if (bookedLRDto[0].getLrType().equalsIgnoreCase(
							"Billing")) {
						item.setText(4, String.valueOf(bookedLRDto[0]
								.getLrCount()));
					}
					int total = Integer.parseInt(String
							.valueOf(item.getText(2)))
							+ Integer.parseInt(String.valueOf(item.getText(3)))
							+ Integer.parseInt(String.valueOf(item.getText(4)));
					item.setText(5, String.valueOf(total));
				}
		
				for (int j = 1; j < len; j++) {
					stationCode = bookedLRDto[j].getStationCode();
					TableItem[] items = tblBookedLR.getItems();
					length = items.length;
					boolean isAvail = false;
		
					for (int k = 0; k < length; k++) {
						if (stationCode.equalsIgnoreCase(items[k].getText(1))) {
							isAvail = true;
							if (bookedLRDto[j].getLrType().equalsIgnoreCase(
									"topay")) {
								items[k].setText(2, String.valueOf(Integer.parseInt(items[k].getText(2)) + 
										bookedLRDto[j].getLrCount()));
							} else if (bookedLRDto[j].getLrType()
									.equalsIgnoreCase("paid")) {
								items[k].setText(3, String.valueOf(Integer.parseInt(items[k].getText(3)) + 
										bookedLRDto[j].getLrCount()));
							} else if (bookedLRDto[j].getLrType()
									.equalsIgnoreCase("billing")) {
								items[k].setText(4, String.valueOf(Integer.parseInt(items[k].getText(4)) + 
										bookedLRDto[j].getLrCount()));
							}
		
							int total = Integer.parseInt(String
									.valueOf(items[k].getText(2)))
									+ Integer.parseInt(String.valueOf(items[k]
											.getText(3)))
									+ Integer.parseInt(String.valueOf(items[k]
											.getText(4)));
		
							items[k].setText(5, String.valueOf(total));
							break;
		
						}
		
					}
					if (!isAvail) {
		
						TableItem item = new TableItem(tblBookedLR, SWT.NONE);
						item.setText(2, String.valueOf(0));
						item.setText(3, String.valueOf(0));
						item.setText(4, String.valueOf(0));
						item.setText(0, String.valueOf(length + 1));
						item.setText(5, String.valueOf(0));
						item.setText(1, bookedLRDto[j].getStationCode());
						if (bookedLRDto[j].getLrType()
								.equalsIgnoreCase("Topay")) {
							item.setText(2, String.valueOf(bookedLRDto[j]
									.getLrCount()));
						} else if (bookedLRDto[j].getLrType().equalsIgnoreCase(
								"Paid")) {
							item.setText(3, String.valueOf(bookedLRDto[j]
									.getLrCount()));
						} else if (bookedLRDto[j].getLrType().equalsIgnoreCase(
								"Billing")) {
							item.setText(4, String.valueOf(bookedLRDto[j]
									.getLrCount()));
						}
		
						int total = Integer.parseInt(String.valueOf(item
								.getText(2)))
								+ Integer.parseInt(String.valueOf(item
										.getText(3)))
								+ Integer.parseInt(String.valueOf(item
										.getText(4)));
		
						item.setText(5, String.valueOf(total));
		
					}
		
				}
				TableItem[] items = tblBookedLR.getItems();
				int size = items.length;
				int topay = 0;
				int paid = 0;
				int billing = 0;
				int globaltotal = 0;
				for (int h = 0; h < size; h++) {
		
					topay = topay + Integer.parseInt(items[h].getText(2));
					paid = paid
							+ Integer.parseInt(String.valueOf(items[h]
									.getText(3)));
					billing = billing
							+ Integer.parseInt(String.valueOf(items[h]
									.getText(4)));
					globaltotal = topay + paid + billing;
				}
		
				TableItem item = new TableItem(tblBookedLR, SWT.NONE);
				Font font1 = new Font(Display.getCurrent(), "Tahoma", 8, SWT.BOLD);
				item.setFont(font1);
				item.setText(2, String.valueOf(topay));
				item.setText(3, String.valueOf(paid));
				item.setText(4, String.valueOf(billing));
				item.setText(1, "Total");
				item.setText(5, String.valueOf(globaltotal));
		
			}
			
			
			
			/*if (null != bookedLRDto) {
				int len = bookedLRDto.length;
				if (null != tblBookedLR) {
					tblBookedLR.removeAll();
				}
				int length = 0;
				if (len > 0) {
					String branch = "";
					branch = cbLSB.getText();
					int index = branch.indexOf(" - ");
					branch = branch.substring(index + 3);
					populateSelectedStations(branch, tblBookedLR);
					
					TableItem[] items = tblBookedLR.getItems();
					length = items.length;
					for(int i = 0;i < length; i++){
						
						for(int j = 0; j < len; j++){
							if(items[i].getText(1).equalsIgnoreCase(bookedLRDto[j].getStationCode())){
								if (bookedLRDto[j].getLrType().equalsIgnoreCase("Topay")) {
									items[i].setText(2, String.valueOf(Integer.parseInt(String.valueOf(items[i].getText(2))) + bookedLRDto[j]
											.getLrCount()));
								} else if (bookedLRDto[j].getLrType().equalsIgnoreCase(
										"Paid")) {
									items[i].setText(3, String.valueOf(Integer.parseInt(String.valueOf(items[i].getText(3))) + bookedLRDto[j]
											.getLrCount()));
								} else if (bookedLRDto[j].getLrType().equalsIgnoreCase(
										"Billing")) {
									items[i].setText(4, String.valueOf(Integer.parseInt(String.valueOf(items[i].getText(4))) + bookedLRDto[j]
											.getLrCount()));
								}
								
							}
						}
						int total = Integer.parseInt(String
								.valueOf(items[i].getText(2)))
								+ Integer.parseInt(String.valueOf(items[i].getText(3)))
								+ Integer.parseInt(String.valueOf(items[i].getText(4)))
								+ Integer.parseInt(String.valueOf(items[i].getText(5)));
						items[i].setText(5, String.valueOf(total));
					}
		
					
				}
		
				
				TableItem[] items = tblBookedLR.getItems();
				int size = items.length;
				int topay = 0;
				int paid = 0;
				int billing = 0;
				int globaltotal = 0;
				for (int h = 0; h < size; h++) {
		
					topay = topay + Integer.parseInt(items[h].getText(2));
					paid = paid
							+ Integer.parseInt(String.valueOf(items[h]
									.getText(3)));
					billing = billing
							+ Integer.parseInt(String.valueOf(items[h]
									.getText(4)));
					globaltotal = topay + paid + billing;
				}
		
				TableItem item = new TableItem(tblBookedLR, SWT.NONE);
				Font font1 = new Font(Display.getCurrent(), "Tahoma", 8, SWT.BOLD);
				item.setFont(font1);
				item.setText(2, String.valueOf(topay));
				item.setText(3, String.valueOf(paid));
				item.setText(4, String.valueOf(billing));
				item.setText(1, "Total");
				item.setText(5, String.valueOf(globaltotal));
		
			}*/
			
			else {
				if (null != tblBookedLR) {
					tblBookedLR.removeAll();
				}
				displayError("No record's Available");
				
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
}

	private void displayError(String errorText) {
		MessageBox messageBox = new MessageBox(shell, SWT.OK);
		messageBox.setMessage(errorText);
		messageBox.setText("Alert");
		messageBox.open();
	}

	private BookedLRDTO[] getBookedLRs(Date fromDate, Date toDate,
			String branchCode) throws Exception	 {
		// TODO Auto-generated method stub
		BookedLRDTO[] bookedLRDto = null;
		try {
			
			Date date = BeanUtil.getThree_month_updated();
			if(toDate.before(date) ){
				//System.out.println("in ddr histry-->"+date);
				bookedLRDto = handler.getBookedLRsHistory(fromDate, toDate, branchCode);
			}else /*if(fromDate.after(date))*/{
				bookedLRDto = handler.getBookedLRs(fromDate, toDate, branchCode);
			}/*else{
				bookedLRDto = handler.getBookedLRsUnion(fromDate, toDate, branchCode);
			}*/
			} catch (Exception e) {
				e.printStackTrace();
			}
			return bookedLRDto;
		
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void widgetSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub
		
		Object source = arg0.getSource();
		
		if (btnLFrom == source) {
			KalendarDialog cd = new KalendarDialog(shell);
			Object obj = cd.open();
			if (obj != null)
				txtLFrom.setText(obj.toString());

		} else if (btnLTo == source) {
			KalendarDialog cd = new KalendarDialog(shell);
			Object obj = cd.open();
			if (obj != null)
				txtLTo.setText(obj.toString());
		} else if (btnLGo == source) {
			try {
				String value = cbLSB.getText();
				int index = value.indexOf(" - ");
				String branchCode = value.substring(index + 3);
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Date fromdate = dateFormat.parse(txtLFrom.getText());
				Date todate = dateFormat.parse(txtLTo.getText());
				populateBookedLRs(fromdate, todate, branchCode);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
	}

	@Override
	public void handleEvent(Event e) {
		// TODO Auto-generated method stub
		int selectionIndex = tabReport.getSelectionIndex();
		TableColumn column = (TableColumn) e.widget;

	}
		
}
