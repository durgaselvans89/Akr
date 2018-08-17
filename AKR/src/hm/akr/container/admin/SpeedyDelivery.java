package hm.akr.container.admin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import hm.akr.common.BeanUtil;
import hm.akr.common.IUIConstants;
import hm.akr.common.MonthDialog;
import hm.akr.common.SWTResourceManager;
import hm.akr.common.sortListener;
import hm.akr.dto.StationsDTO;
import hm.akr.dto.TranshipmentDTO;
import hm.akr.dto.VersionDTO;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
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

public class SpeedyDelivery extends Composite implements SelectionListener,IUIConstants,Listener{

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
	
	
	private static final String JRXML_FILE = "hm/akr/resources/printer/DAILYREPORT.jrxml";

	Date reportDate = null;

	private TabItem tiTranshipment;

	private Canvas canvas6;

	private Table tblTranshipment;

	private TableColumn colbranchCode;

	private TableColumn colsno;

	private TableColumn tblcolBranchName;

	private TableColumn tblcolInwardedLRs;

	private TableColumn tblcol1Day;

	private TableColumn tblcol2Day;

	private TableColumn tblcol3Day;

	private TableColumn tblcolAbove3;

	private TableColumn tblcolStock;

	private Label lblTfrom;
	private Label lbltbranch;
	private Combo cbtbranch;
	private Text txtTFrom;
	private TableColumn colTsno;
	
	private TableColumn tblcolIntime1day;

	private Button btnMonth;

	
	public SpeedyDelivery(Composite composite, int style) throws Exception {
		super(composite, style);

		shell = composite.getShell();

		handler = new AdminCompositeHandler();
		beanutil = BeanUtil.getInstance();
		SERVER_DATE = beanutil.getServerDate();

	}
	
	public Composite loadcomposite() {
		
		this.setBounds(45,100, 1020,700);
		tabReport = new TabFolder(this, SWT.NONE);
		tabReport.setBounds(50, 25, 820, 500);
		tabReport.addSelectionListener(this);
		
		
		{
			tiTranshipment = new TabItem(tabReport, SWT.NONE);
			tiTranshipment.setText("Speedy Delivery");

			{
				canvas6 = new Canvas(tabReport, SWT.NONE);
				tiTranshipment.setControl(canvas6);
				{
					tblTranshipment = new Table(canvas6,
							SWT.FULL_SELECTION | SWT.H_SCROLL
									| SWT.V_SCROLL | SWT.BORDER);

					tblTranshipment.setHeaderVisible(true);
					tblTranshipment.setLinesVisible(true);
					tblTranshipment.setBounds(8, 54, 798, 400);
					{
						colTsno = new TableColumn(tblTranshipment,
								SWT.NONE);
						colTsno.setText("S NO");
						colTsno.setWidth(40);
						colTsno.addListener(SWT.Selection,
								this);

					}
					{
						tblcolBranchName = new TableColumn(
								tblTranshipment, SWT.NONE);
						tblcolBranchName.setText("Station  Name");
						tblcolBranchName.setWidth(180);
						tblcolBranchName.addListener(SWT.Selection,
								this);

					}
					{
						colbranchCode = new TableColumn(
								tblTranshipment, SWT.NONE);
						colbranchCode.setText("Station Code");
						colbranchCode.setWidth(80);
						colbranchCode.addListener(SWT.Selection,
								this);
					}
					{
						tblcolInwardedLRs = new TableColumn(
								tblTranshipment, SWT.NONE);
						tblcolInwardedLRs.setText("Inwarded LRs");
						tblcolInwardedLRs.setWidth(85);
						tblcolInwardedLRs.addListener(SWT.Selection,
								this);
					}
					{
						tblcol1Day = new TableColumn(tblTranshipment,
								SWT.NONE);
						tblcol1Day.setText("1 Day %");
						tblcol1Day.setWidth(60);
						tblcol1Day.addListener(SWT.Selection,
								this);

					}
					{
						tblcol2Day = new TableColumn(tblTranshipment,
								SWT.NONE);
						tblcol2Day.setText("2 Day %");
						tblcol2Day.setWidth(60);
						tblcol2Day.addListener(SWT.Selection,
								this);

					}

					{
						tblcol3Day = new TableColumn(tblTranshipment,
								SWT.NONE);
						tblcol3Day.setText("3 Day %");
						tblcol3Day.setWidth(60);
						tblcol3Day.addListener(SWT.Selection,
								this);

					}
					{
						tblcolAbove3 = new TableColumn(tblTranshipment,
								SWT.NONE);
						tblcolAbove3.setText(" > 3 Day %");
						tblcolAbove3.setWidth(80);
						tblcolAbove3.addListener(SWT.Selection,
								this);

					}
					{
						tblcolIntime1day = new TableColumn(
								tblTranshipment, SWT.NONE);
						tblcolIntime1day.setText("Intime1Day %");
						tblcolIntime1day.setWidth(85);
						tblcolIntime1day.addListener(SWT.Selection,
								this);

					}
					{
						tblcolStock = new TableColumn(tblTranshipment,
								SWT.NONE);
						tblcolStock.setText("Stock");
						tblcolStock.setWidth(60);
						tblcolStock.addListener(SWT.Selection,
								this);

					}

				}
				{
					lblTfrom = new Label(canvas6, SWT.NONE);
					lblTfrom.setText("Month");
					lblTfrom.setBounds(285, 23, 37, 17);
				}
				{
					txtTFrom = new Text(canvas6, SWT.BORDER);
					txtTFrom.setBounds(332, 22, 60, 21);
					txtTFrom.setEditable(false);
					DateFormat dateFormat = new SimpleDateFormat(
							"MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					txtTFrom.setText(date);
				}
				{
					btnMonth = new Button(canvas6, SWT.PUSH
							| SWT.CENTER);
					btnMonth.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btnMonth.setBounds(392, 21, 26, 23);
					btnMonth.addSelectionListener(this);
				}

				{
					cbtbranch = new Combo(canvas6, SWT.READ_ONLY);
					cbtbranch.setBounds(116, 21, 136, 21);
					cbtbranch.addSelectionListener(this);
					populateLRBranches();
				}
				{
					lbltbranch = new Label(canvas6, SWT.NONE);
					lbltbranch.setText("Select Branch");
					lbltbranch.setBounds(41, 23, 73, 19);
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
					/*if (null != cbLSB)
						cbLSB.add(stations[i].getName() + " - "
								+ stations[i].getId());*/
					if (null != cbtbranch)
						cbtbranch.add(stations[i].getName() + " - "
								+ stations[i].getId());
				}
				/*if (null != cbLSB)
					cbLSB.select(0);*/
				if (null != cbtbranch)
					cbtbranch.select(0);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}
	
	private void populateTranshipment(TranshipmentDTO[] dto)
	throws Exception {
		if (null != tblTranshipment) {
			tblTranshipment.removeAll();
			StationsDTO[] stations = beanutil.getAvailableStations();
			int len = dto.length;
			int total = 0;
			int inwardLr = 0;
			int day1Percent = 0;
			int day2Percent = 0;
			int day3Percent = 0;
			int day4Percent = 0;
			int intimePercent = 0;
		
			for (int i = 0; i < len; i++) {
				TableItem item = new TableItem(tblTranshipment, SWT.NONE);
		
				item.setText(0, String.valueOf(i + 1));
				item.setText(2, dto[i].getStation_code());
				item.setText(1, getStationName(dto[i].getStation_code(),
						stations));
		
				inwardLr = dto[i].getInwardLr();
				item.setText(3, String.valueOf(inwardLr));
		
				total = dto[i].getDelivery1Day() + dto[i].getDelivery2Day()
						+ dto[i].getDelivery3Day() + dto[i].getDeliveryM3();
		
				if (total == 0) {
					item.setText(4, String
							.valueOf(dto[i].getDelivery1Day()));
					item.setText(5, String
							.valueOf(dto[i].getDelivery2Day()));
					item.setText(6, String
							.valueOf(dto[i].getDelivery3Day()));
					item.setText(7, String.valueOf(dto[i].getDeliveryM3()));
				} else {
		
					day1Percent = (dto[i].getDelivery1Day() * 100) / total;
					day2Percent = (dto[i].getDelivery2Day() * 100) / total;
					day3Percent = (dto[i].getDelivery3Day() * 100) / total;
					day4Percent = (dto[i].getDeliveryM3() * 100) / total;
		
					item.setText(4, String.valueOf(day1Percent));
					item.setText(5, String.valueOf(day2Percent));
					item.setText(6, String.valueOf(day3Percent));
					item.setText(7, String.valueOf(day4Percent));
				}
		
				if (inwardLr == 0) {
					item.setText(8, String.valueOf(dto[i].getIntime1Day()));
				} else {
					intimePercent = (dto[i].getIntime1Day() * 100)
							/ inwardLr;
					item.setText(8, String.valueOf(intimePercent));
				}
		
				item.setText(9, String.valueOf(dto[i].getStock()
						+ dto[i].getIntime1Day()));
			}
		}

	}

	
	
	private String getStationName(String id, StationsDTO[] stations) {
		if (null != id && null != stations) {
			int size = 0;
			size = stations.length;
			if (size > 0) {
				for (int i = 0; i < size; i++) {
					if (stations[i].getId().equals(id)) {
						return stations[i].getName();
					}
				}
			}
		}
		return null;
	}
	
	private void displayError(String errorText) {
		MessageBox messageBox = new MessageBox(shell, SWT.OK);
		messageBox.setMessage(errorText);
		messageBox.setText("Alert");
		messageBox.open();
	}

	

	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void widgetSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub
		Object source = arg0.getSource();
		if (btnMonth == source) {
			MonthDialog cd = new MonthDialog(shell);
			Object obj = cd.open();
			if (obj != null)
				txtTFrom.setText(obj.toString());

			try {
				String value = cbtbranch.getText();
				int index = value.indexOf(" - ");
				String branchCode = value.substring(index + 3);
				String date = txtTFrom.getText();
				index = date.indexOf("-");
				int month = Integer.parseInt(date.substring(0, index));
				int year = Integer.parseInt(date.substring(index + 1));

				TranshipmentDTO[] dto = handler.getTranshipment(month,
						year, branchCode);
				if (null != tblTranshipment) {
					tblTranshipment.removeAll();
				}
				if (null != dto) {
					populateTranshipment(dto);
				} else {
					displayError("No Records Available");
				}
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

		if (column == colTsno) {
			new sortListener().sortByFloat(0, tblTranshipment);
		} else if (column == tblcolBranchName) {
			new sortListener().sortByString(1, tblTranshipment);
		} else if (column == colbranchCode) {
			new sortListener().sortByString(2, tblTranshipment);
		} else if (column == tblcolInwardedLRs) {
			new sortListener().sortByFloat(3, tblTranshipment);
		} else if (column == tblcol1Day) {
			new sortListener().sortByFloat(4, tblTranshipment);
		} else if (column == tblcol2Day) {
			new sortListener().sortByFloat(5, tblTranshipment);
		} else if (column == tblcol3Day) {
			new sortListener().sortByFloat(6, tblTranshipment);
		} else if (column == tblcolAbove3) {
			new sortListener().sortByFloat(7, tblTranshipment);
		} else if (column == tblcolIntime1day) {
			new sortListener().sortByFloat(8, tblTranshipment);
		} else if (column == tblcolStock) {
			new sortListener().sortByFloat(9, tblTranshipment);
		}
		new sortListener().setSerialNumber(tblTranshipment);
		
	}
		
}
