package hm.akr.container.admin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import hm.akr.common.BeanUtil;
import hm.akr.common.IUIConstants;
import hm.akr.common.KalendarDialog;
import hm.akr.common.SWTResourceManager;
import hm.akr.common.sortListener;
import hm.akr.dto.DailyStatusDTO;
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

public class UsageReport extends Composite implements SelectionListener,IUIConstants,Listener{
	
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

	
	private Button btnReport;

	private Button btnTo;

	private Text txtTo;

	private Label lblTo;

	private Button btnFrom;

	private Text txtFrom;

	private Label lblfrom;

	private TableColumn tblcolBilling;
	
	private TableColumn colsno;

	private TableColumn colbranch;
	private TableColumn tblcolPaid;

	private TableColumn tblcolTopay;

	private TableColumn tblcolStation;

	private Table tblReport;

	
	private static final String JRXML_FILE = "hm/akr/resources/printer/DAILYREPORT.jrxml";

	private Canvas canvas2;

	private TabItem tabItem1;


	Date reportDate = null;


	
	
	
	

	public UsageReport(Composite composite, int style) throws Exception {
		super(composite, style);

		shell = composite.getShell();

		handler = new AdminCompositeHandler();
		beanutil = BeanUtil.getInstance();
		SERVER_DATE = beanutil.getServerDate();

	}
	
	public Composite loadcomposite() {
		
		this.setBounds(45,100, 1020,700);
		tabReport = new TabFolder(this, SWT.NONE);
		tabReport.setBounds(50, 25, 820, 520);
		tabReport.addSelectionListener(this);
		
		{
			tabItem1 = new TabItem(tabReport, SWT.NONE);
			tabItem1.setText("USAGE REPORT");
			{
				canvas2 = new Canvas(tabReport, SWT.NONE);
				tabItem1.setControl(canvas2);
				{
					tblReport = new Table(canvas2, SWT.FULL_SELECTION
							| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);

					tblReport.setHeaderVisible(true);
					tblReport.setLinesVisible(true);
					tblReport.setBounds(45, 49, 745, 431);
					{
						colsno = new TableColumn(tblReport, SWT.NONE);
						colsno.setText("S NO");
						colsno.setWidth(82);
						colsno.addListener(SWT.Selection,
								this);
					}
					{
						colbranch = new TableColumn(tblReport, SWT.NONE);
						colbranch.setText("Branch Code");
						colbranch.setWidth(100);
						colbranch.addListener(SWT.Selection,
								this);

					}
					{
						tblcolStation = new TableColumn(tblReport, SWT.NONE);
						tblcolStation.setText("STATION NAME");
						tblcolStation.setWidth(200);
						tblcolStation.addListener(SWT.Selection,
								this);

					}
					{
						tblcolTopay = new TableColumn(tblReport, SWT.NONE);
						tblcolTopay.setText("TOPAY");
						tblcolTopay.setWidth(103);
						tblcolTopay.addListener(SWT.Selection,
								this);
					}
					{
						tblcolPaid = new TableColumn(tblReport, SWT.NONE);
						tblcolPaid.setText("PAID");
						tblcolPaid.setWidth(113);
						tblcolPaid.addListener(SWT.Selection,
								this);
					}
					{
						tblcolBilling = new TableColumn(tblReport, SWT.NONE);
						tblcolBilling.setText("BILLING");
						tblcolBilling.setWidth(133);
						tblcolBilling.addListener(SWT.Selection,
								this);
					}

				}
				{
					lblfrom = new Label(canvas2, SWT.NONE);
					lblfrom.setText("From");
					lblfrom.setBounds(66, 5, 34, 22);
				}

				{
					txtFrom = new Text(canvas2, SWT.BORDER);
					txtFrom.setBounds(104, 5, 94, 22);
					txtFrom.setEditable(false);
					DateFormat dateFormat = new SimpleDateFormat(
							"dd-MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					if (SERVER_DATE != null) {
						txtFrom.setText(SERVER_DATE);
					} else {
						txtFrom.setText(date);
					}

				}
				{
					btnFrom = new Button(canvas2, SWT.PUSH | SWT.CENTER);
					btnFrom.setBounds(200, 4, 31, 23);
					btnFrom.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btnFrom.addSelectionListener(this);
				}
				{
					lblTo = new Label(canvas2, SWT.NONE);
					lblTo.setText("To");
					lblTo.setBounds(306, 5, 32, 22);
				}
				{
					txtTo = new Text(canvas2, SWT.BORDER);
					txtTo.setBounds(340, 5, 92, 22);
					txtTo.setEditable(false);
					DateFormat dateFormat = new SimpleDateFormat(
							"dd-MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					if (SERVER_DATE != null) {
						txtTo.setText(SERVER_DATE);
					} else {
						txtTo.setText(date);
					}
				}
				{
					btnTo = new Button(canvas2, SWT.PUSH | SWT.CENTER);
					btnTo.setBounds(433, 4, 32, 23);
					btnTo.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btnTo.addSelectionListener(this);
				}
				{
					btnReport = new Button(canvas2, SWT.PUSH | SWT.CENTER);
					btnReport.setText("GO");
					btnReport.setBounds(544, 5, 47, 22);
					btnReport.addSelectionListener(this);
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
		
		
		try {
			populateReport();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return this;
		
	}
	
	
	private void populateReport() throws Exception {
		DailyStatusDTO[] dto = null;
		Date fromdate = null;
		Date todate = null;

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		fromdate = dateFormat.parse(txtFrom.getText());
		todate = dateFormat.parse(txtTo.getText());
		try{
			dto = beanutil.getReport(fromdate, todate);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if (null != dto) {			
			tblReport.removeAll();
			int len = dto.length;
			for (int i = 0; i < len; i++) {
				TableItem item = new TableItem(tblReport, SWT.NONE);
				Font font1 = new Font(Display.getCurrent(), "Tahoma", 8, SWT.NONE);
				item.setFont(font1);
				item.setText(0, String.valueOf(i + 1));
				item.setText(1, dto[i].getBranch_code());
				item.setText(2, dto[i].getName());
				item.setText(3, Integer.toString(dto[i].getTopay_count()));
				item.setText(4, Integer.toString(dto[i].getPaid_count()));
				item.setText(5, Integer.toString(dto[i].getBilling_count()));

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
		
		if (btnFrom == source) {
			KalendarDialog cd = new KalendarDialog(shell);

			Object obj = cd.open();
			if (obj != null)
				txtFrom.setText(obj.toString());

		} else if (btnReport == source) {
			try {
				populateReport();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else if (btnTo == source) {
			KalendarDialog cd = new KalendarDialog(shell);

			Object obj = cd.open();
			if (obj != null)
				txtTo.setText(obj.toString());
		}

		
	}

	@Override
	public void handleEvent(Event e) {
		// TODO Auto-generated method stub
		int selectionIndex = tabReport.getSelectionIndex();
		TableColumn column = (TableColumn) e.widget;

		if (column == colsno) {
			new sortListener().sortByFloat(0, tblReport);
		} else if (column == colbranch) {
			new sortListener().sortByString(1, tblReport);
		} else if (column == tblcolStation) {
			new sortListener().sortByString(2, tblReport);
		} else if (column == tblcolTopay) {
			new sortListener().sortByFloat(3, tblReport);
		} else if (column == tblcolPaid) {
			new sortListener().sortByFloat(4, tblReport);
		} else if (column == tblcolBilling) {
			new sortListener().sortByFloat(5, tblReport);
		}
		
	}
	
}
