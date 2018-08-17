package hm.akr.container.admin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import hm.akr.common.BeanUtil;
import hm.akr.common.IUIConstants;
import hm.akr.common.KalendarDialog;
import hm.akr.common.SWTResourceManager;
import hm.akr.container.admin.AdminComposite.SetUpAction;
import hm.akr.dto.DailyDeliveryStatusDTO;
import hm.akr.dto.StationsDTO;
import hm.akr.dto.VersionDTO;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
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

public class DailyDeliveryStatus extends Composite implements SelectionListener,IUIConstants,Listener {

	
	private TabItem tiDailyDelvStatus;

	private Canvas cvsDailyDelvStatus;

	private Table tblDailyDelvStatus;

	private TableColumn colDDSSno;

	private TableColumn colDDSBranch;

	private TableColumn colDDSTodayOD;

	private TableColumn colDDSTodayDD;

	private TableColumn colDDSTodayTotal;

	private TableColumn colDDS830_OD;

	private TableColumn colDDS830_AD;

	private TableColumn colDDS830_DD;

	private TableColumn colDDS930_OD;

	private TableColumn colDDS930_DD;

	private TableColumn colDDS1030_DD;

	private TableColumn colDDS1030_OD;

	private TableColumn colDDS1130_OD;

	private TableColumn colDDS1130_DD;

	private TableColumn colDDS1230_OD;

	private TableColumn colDDS1230_DD;

	private TableColumn colDDS1330_OD;

	private TableColumn colDDS1330_DD;

	private TableColumn colDDS1430_OD;

	private TableColumn colDDS1530_DD;

	private TableColumn colDDSAfter1530_OD;

	private TableColumn colDDSAfter1530_DD;

	private TableColumn colDDSpending_OD;

	private TableColumn colDDSpending_DD;

	private TableColumn colDDSstock_OD;

	private TableColumn colDDSstock_DD;

	private Label lblDDSBranch;

	private Combo cbDDSBranch;

	private Button btnDDSgo;

	private Button btnDDSdt;

	private Text txtDDSDate;

	private TableColumn colDDS_before830_OD;

	private TableColumn colDDS_before830_DD;

	private SimpleDateFormat user_formate;
	
	private String EMPTY_STRING = "";

	private Label label13;
	private Label label12;
	private Label label11;
	private Label label10;
	private Label label9;
	private Label label8;
	private Label label7;
	private Label label6;
	private Label label5;
	private Label label4;
	private Label label3;
	private Label label2;
	private Label lblInward;
	
	
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
	private String DAILY_DELIVERY_STATUS = "Daily Delivery Status";
	
	public DailyDeliveryStatus(Composite composite, int style) throws Exception {
		super(composite, style);

		shell = composite.getShell();

		handler = new AdminCompositeHandler();
		beanutil = BeanUtil.getInstance();
		SERVER_DATE = beanutil.getServerDate();

	}

	
	public Composite loadcomposite(){
		
		
		this.setBounds(45,100, 1020,700);
		tabReport = new TabFolder(this, SWT.NONE);
		tabReport.setBounds(20, 25, 920, 500);
		tabReport.addSelectionListener(this);
		
		
		
		{
			// Daily Delivery statusr

			tiDailyDelvStatus = new TabItem(tabReport,
					SWT.NONE);
			tiDailyDelvStatus
					.setText(DAILY_DELIVERY_STATUS);

			{
				cvsDailyDelvStatus = new Canvas(tabReport,
						SWT.NONE);
				tiDailyDelvStatus
						.setControl(cvsDailyDelvStatus);
				tblDailyDelvStatus = new Table(
						cvsDailyDelvStatus, SWT.MULTI
								| SWT.BORDER
								| SWT.FULL_SELECTION);
				tblDailyDelvStatus.setLinesVisible(true);
				tblDailyDelvStatus.setHeaderVisible(true);
				tblDailyDelvStatus.setBounds(13, 65, 884,374);

				{
					lblDDSBranch = new Label(
							cvsDailyDelvStatus, SWT.NONE);
					lblDDSBranch.setText("Select Branch");
					lblDDSBranch.setBounds(10, 16, 75, 20);
				}
				{
					cbDDSBranch = new Combo(
							cvsDailyDelvStatus,
							SWT.READ_ONLY);
					cbDDSBranch.setBounds(95, 14, 145, 21);
				}
				{
					txtDDSDate = new Text(
							cvsDailyDelvStatus, SWT.BORDER
									| SWT.READ_ONLY);
					txtDDSDate.setBounds(250, 13, 70, 22);
					DateFormat dateFormat = new SimpleDateFormat(
							"dd-MM-yyyy");
					Calendar c1 = Calendar.getInstance();
					c1.add(Calendar.DATE, -1);
					txtDDSDate.setText(dateFormat.format(c1
							.getTime()));

				}
				{
					btnDDSdt = new Button(
							cvsDailyDelvStatus, SWT.PUSH
									| SWT.CENTER);
					btnDDSdt
							.setImage(SWTResourceManager
									.getImage("hm/akr/resources/Calendar.jpg"));
					btnDDSdt.setBounds(320, 12, 26, 23);
					// btnGo.setFont(BUTTON_FONT);
					btnDDSdt
							.addSelectionListener(new SelectionAdapter() {
								public void widgetSelected(
										SelectionEvent e) {
									KalendarDialog cd = new KalendarDialog(
											shell);
									Object obj = cd.open();
									if (obj != null) {
										String date = obj
												.toString();
										try {
											SimpleDateFormat parse = new SimpleDateFormat(
													"dd-MM-yyyy");
											Date curDate = parse
													.parse(SERVER_DATE);

											Date selectedDate = parse
													.parse(date);
											if (selectedDate
													.before(curDate)) {
												txtDDSDate
														.setText(date);
											} else {
												displayError("Date should be less than current date");
												txtDDSDate
														.setText("");
											}

										} catch (Exception exception) {
											exception
													.printStackTrace();
										}

									}
								}
							});

				}

				{
					btnDDSgo = new Button(
							cvsDailyDelvStatus, SWT.None);
					btnDDSgo.setBounds(356, 13, 36, 24);
					btnDDSgo.setText("Go");
					btnDDSgo
							.addSelectionListener(this);
				}
				{
					lblInward = new Label(
							cvsDailyDelvStatus, SWT.NONE);
					lblInward.setText("Inward");
					lblInward.setBounds(96, 49, 57, 16);
					lblInward.setAlignment(SWT.CENTER);
					lblInward.setBackground(Display
							.getDefault().getSystemColor(
									SWT.COLOR_GRAY));
				}
				{
					label3 = new Label(cvsDailyDelvStatus,
							SWT.NONE);
					label3.setText("Before 8:30");
					label3.setAlignment(SWT.CENTER);
					label3.setBounds(197, 49, 57, 16);
					label3.setBackground(Display
							.getDefault().getSystemColor(
									SWT.COLOR_GRAY));
				}
				{
					label4 = new Label(cvsDailyDelvStatus,
							SWT.NONE);
					label4.setText("8:30-9:30");
					label4.setAlignment(SWT.CENTER);
					label4.setBounds(254, 49, 63, 16);
				}
				{
					label5 = new Label(cvsDailyDelvStatus,
							SWT.NONE);
					label5.setText("9:30-10:30");
					label5.setAlignment(SWT.CENTER);
					label5.setBounds(315, 49, 58, 16);
					label5.setBackground(Display
							.getDefault().getSystemColor(
									SWT.COLOR_GRAY));
				}
				{
					label6 = new Label(cvsDailyDelvStatus,
							SWT.NONE);
					label6.setText("10:30-11:30");
					label6.setAlignment(SWT.CENTER);
					label6.setBounds(375, 49, 63, 16);
				}
				{
					label7 = new Label(cvsDailyDelvStatus,
							SWT.NONE);
					label7.setText("11:30-12:30");
					label7.setAlignment(SWT.CENTER);
					label7.setBounds(439, 49, 63, 16);
					label7.setBackground(Display
							.getDefault().getSystemColor(
									SWT.COLOR_GRAY));
				}
				{
					label9 = new Label(cvsDailyDelvStatus,
							SWT.NONE);
					label9.setText("12:30-13:30");
					label9.setAlignment(SWT.CENTER);
					label9.setBounds(503, 49, 63, 16);
				}
				{
					label8 = new Label(cvsDailyDelvStatus,
							SWT.NONE);
					label8.setText("13:30-14:30");
					label8.setAlignment(SWT.CENTER);
					label8.setBounds(567, 49, 63, 16);
					label8.setBackground(Display
							.getDefault().getSystemColor(
									SWT.COLOR_GRAY));
				}
				{
					label10 = new Label(cvsDailyDelvStatus,
							SWT.NONE);
					label10.setText("14:30-15:30");
					label10.setAlignment(SWT.CENTER);
					label10.setBounds(631, 49, 63, 16);
				}
				{
					label11 = new Label(cvsDailyDelvStatus,
							SWT.NONE);
					label11.setText("After 15:30");
					label11.setAlignment(SWT.CENTER);
					label11.setBounds(696, 49, 58, 16);
					label11.setBackground(Display
							.getDefault().getSystemColor(
									SWT.COLOR_GRAY));
				}
				{
					label12 = new Label(cvsDailyDelvStatus,
							SWT.NONE);
					label12.setText("Pending");
					label12.setAlignment(SWT.CENTER);
					label12.setBounds(756, 49, 58, 16);
				}
				{
					label13 = new Label(cvsDailyDelvStatus,
							SWT.NONE);
					label13.setText("Stock");
					label13.setAlignment(SWT.CENTER);
					label13.setBounds(815, 49, 58, 16);
					label13.setBackground(Display
							.getDefault().getSystemColor(
									SWT.COLOR_GRAY));
				}

				/*
				 * { colDDSSno = new TableColumn(
				 * tblDailyDelvStatus, SWT.NONE);
				 * colDDSSno.setText("S.No");
				 * colDDSSno.setWidth(50); }
				 */
				{
					colDDSBranch = new TableColumn(
							tblDailyDelvStatus, SWT.NONE);
					colDDSBranch.setText("Branch Office");
					colDDSBranch.setWidth(80);
				}
				{
					colDDSTodayOD = new TableColumn(
							tblDailyDelvStatus, SWT.NONE);
					colDDSTodayOD.setText("OD");
					colDDSTodayOD.setWidth(30);
				}
				{
					colDDSTodayDD = new TableColumn(
							tblDailyDelvStatus, SWT.NONE);
					colDDSTodayDD.setText("DD");
					colDDSTodayDD.setWidth(30);
					// colDDSTodayDD.setImage(SWTResourceManager.getImage("hm/akr/resources/Calendar.jpg"));
					colDDSTodayDD.setAlignment(SWT.LEFT);
				}
				{
					colDDSTodayTotal = new TableColumn(
							tblDailyDelvStatus, SWT.NONE);
					colDDSTodayTotal.setText("Total");
					colDDSTodayTotal.setWidth(40);
				}
				{
					colDDS_before830_OD = new TableColumn(
							tblDailyDelvStatus, SWT.NONE);
					colDDS_before830_OD.setText("OD");
					colDDS_before830_OD.setWidth(30);
				}
				{
					colDDS_before830_DD = new TableColumn(
							tblDailyDelvStatus, SWT.NONE);
					colDDS_before830_DD.setText("DD");
					colDDS_before830_DD.setWidth(30);
				}
				{
					colDDS830_OD = new TableColumn(
							tblDailyDelvStatus, SWT.NONE);
					colDDS830_OD.setText("OD");
					colDDS830_OD.setWidth(30);
				}
				{
					colDDS830_DD = new TableColumn(
							tblDailyDelvStatus, SWT.NONE);
					colDDS830_DD.setText("DD");
					colDDS830_DD.setWidth(30);
				}
				{
					colDDS930_OD = new TableColumn(
							tblDailyDelvStatus, SWT.NONE);
					colDDS930_OD.setText("OD");
					colDDS930_OD.setWidth(30);
				}
				{
					colDDS930_DD = new TableColumn(
							tblDailyDelvStatus, SWT.NONE);
					colDDS930_DD.setText("DD");
					colDDS930_DD.setWidth(30);
				}
				{
					colDDS1030_OD = new TableColumn(
							tblDailyDelvStatus, SWT.NONE);
					colDDS1030_OD.setText("OD");
					colDDS1030_OD.setWidth(32);
				}
				{
					colDDS1030_DD = new TableColumn(
							tblDailyDelvStatus, SWT.NONE);
					colDDS1030_DD.setText("DD");
					colDDS1030_DD.setWidth(32);
				}
				{
					colDDS1130_OD = new TableColumn(
							tblDailyDelvStatus, SWT.NONE);
					colDDS1130_OD.setText("OD");
					colDDS1130_OD.setWidth(32);
				}
				{
					colDDS1130_DD = new TableColumn(
							tblDailyDelvStatus, SWT.NONE);
					colDDS1130_DD.setText("DD");
					colDDS1130_DD.setWidth(32);
				}
				{
					colDDS1230_OD = new TableColumn(
							tblDailyDelvStatus, SWT.NONE);
					colDDS1230_OD.setText("OD");
					colDDS1230_OD.setWidth(32);
				}
				{
					colDDS1230_DD = new TableColumn(
							tblDailyDelvStatus, SWT.NONE);
					colDDS1230_DD.setText("DD");
					colDDS1230_DD.setWidth(32);
				}
				{
					colDDS1330_OD = new TableColumn(
							tblDailyDelvStatus, SWT.NONE);
					colDDS1330_OD.setText("OD");
					colDDS1330_OD.setWidth(32);
				}
				{
					colDDS1330_DD = new TableColumn(
							tblDailyDelvStatus, SWT.NONE);
					colDDS1330_DD.setText("DD");
					colDDS1330_DD.setWidth(32);
				}
				{
					colDDS1430_OD = new TableColumn(
							tblDailyDelvStatus, SWT.NONE);
					colDDS1430_OD.setText("OD");
					colDDS1430_OD.setWidth(32);
				}
				{
					colDDS1530_DD = new TableColumn(
							tblDailyDelvStatus, SWT.NONE);
					colDDS1530_DD.setText("DD");
					colDDS1530_DD.setWidth(32);
				}

				{
					colDDSAfter1530_OD = new TableColumn(
							tblDailyDelvStatus, SWT.NONE);
					colDDSAfter1530_OD.setText("OD");
					colDDSAfter1530_OD.setWidth(30);
				}
				{
					colDDSAfter1530_DD = new TableColumn(
							tblDailyDelvStatus, SWT.NONE);
					colDDSAfter1530_DD.setText("DD");
					colDDSAfter1530_DD.setWidth(30);
				}
				{
					colDDSpending_OD = new TableColumn(
							tblDailyDelvStatus, SWT.NONE);
					colDDSpending_OD.setText("OD");
					colDDSpending_OD.setWidth(30);
				}
				{
					colDDSpending_DD = new TableColumn(
							tblDailyDelvStatus, SWT.NONE);
					colDDSpending_DD.setText("DD");
					colDDSpending_DD.setWidth(30);
				}
				{
					colDDSstock_OD = new TableColumn(
							tblDailyDelvStatus, SWT.NONE);
					colDDSstock_OD.setText("OD");
					colDDSstock_OD.setWidth(30);
				}
				{
					colDDSstock_DD = new TableColumn(
							tblDailyDelvStatus, SWT.NONE);
					colDDSstock_DD.setText("DD");
					colDDSstock_DD.setWidth(30);
				}

			}
			populateBranchForDDS();
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
	
	
	private void displayError(String errorText) {
		MessageBox messageBox = new MessageBox(shell, SWT.OK);
		messageBox.setMessage(errorText);
		messageBox.setText("Alert");
		messageBox.open();
	}
	
	
	private void populateBranchForDDS() {

		StationsDTO[] station = null;
		try {
			station = handler.getAllBranches();
			if (null != station) {
				int len = station.length;
				for (int i = 0; i < len; i++) {
					cbDDSBranch.add(station[i].getName() + " - "
							+ station[i].getId());
				}
				cbDDSBranch.add("All");
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}
	
	private DailyDeliveryStatusDTO[] getDailyDeliveryStatus() {
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			Date date = null;
			date = format.parse(txtDDSDate.getText());
			String branch = EMPTY_STRING;
			String value = EMPTY_STRING;
			int index = -1;

			value = cbDDSBranch.getText();
			index = value.indexOf(" - ");
			branch = value.substring(index + 3);
			//System.out.println("branch-->"+branch);
			return handler.getDailyDeliveryStatus(date,branch);
		} catch (Exception exception) {

		}
		return null;
	}

	
	private void populateDailyDeliveryStatus(DailyDeliveryStatusDTO[] dto) {
		int len = dto.length;
		String branch = EMPTY_STRING;
		String value = EMPTY_STRING;
		String stnCode = EMPTY_STRING;
		int index = -1;

		value = cbDDSBranch.getText();
		index = value.indexOf(" - ");
		branch = value.substring(index + 3);

		// System.out.println("br-->"+branch);
		if (len > 0) {
			for (int i = 0; i < len; i++) {
				stnCode = dto[i].getStationCode();
				if (value.equalsIgnoreCase("All") ? true : branch.trim()
						.equalsIgnoreCase(getBranchCode(stnCode))) {
					TableItem item = new TableItem(tblDailyDelvStatus,
							SWT.NONE);
					item.setText(0, stnCode);
					item.setText(1, String.valueOf(dto[i].getInward_od()));
					item.setText(2, String.valueOf(dto[i].getInward_dd()));
					// inward total
					item.setText(3, String
							.valueOf((dto[i].getInward_od() + dto[i]
									.getInward_dd())));

					item.setText(4, String.valueOf(dto[i]
							.getDelv_before830_od()));
					item.setText(5, String.valueOf(dto[i]
							.getDelv_before830_dd()));
					item
							.setText(6, String.valueOf(dto[i]
									.getDelv_830_od()));
					item
							.setText(7, String.valueOf(dto[i]
									.getDelv_830_dd()));
					item
							.setText(8, String.valueOf(dto[i]
									.getDelv_930_od()));
					item
							.setText(9, String.valueOf(dto[i]
									.getDelv_930_dd()));
					item.setText(10, String.valueOf(dto[i]
							.getDelv_1030_od()));
					item.setText(11, String.valueOf(dto[i]
							.getDelv_1030_dd()));
					item.setText(12, String.valueOf(dto[i]
							.getDelv_1130_od()));
					item.setText(13, String.valueOf(dto[i]
							.getDelv_1130_dd()));
					item.setText(14, String.valueOf(dto[i]
							.getDelv_1230_od()));
					item.setText(15, String.valueOf(dto[i]
							.getDelv_1230_dd()));
					item.setText(16, String.valueOf(dto[i]
							.getDelv_1330_od()));
					item.setText(17, String.valueOf(dto[i]
							.getDelv_1330_dd()));
					item.setText(18, String.valueOf(dto[i]
							.getDelv_1430_od()));
					item.setText(19, String.valueOf(dto[i]
							.getDelv_1430_dd()));
					item.setText(20, String.valueOf(dto[i]
							.getDelv_after1530_od()));
					item.setText(21, String.valueOf(dto[i]
							.getDelv_after1530_dd()));
					item
							.setText(22, String.valueOf(dto[i]
									.getPending_od()));
					item
							.setText(23, String.valueOf(dto[i]
									.getPending_dd()));
					item.setText(24, String.valueOf(dto[i].getStock_od()));
					item.setText(25, String.valueOf(dto[i].getStock_dd()));
				}
			}
		}

	}

	private String getBranchCode(String stationCode) {
		String station = EMPTY_STRING;
		try {
			StationsDTO[] stationsDTO = beanutil.getAvailableStations();

			for (int i = 0; i < stationsDTO.length; i++) {
				if (stationsDTO[i].getId().equalsIgnoreCase(stationCode)) {
					station = stationsDTO[i].getBranch_code();
				}
			}

		} catch (Exception e) {

		}
		return station;
	}

	
	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void widgetSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub
		Object source = arg0.getSource();
		if (btnDDSgo == source) {
			try {
				if(tblDailyDelvStatus != null)
					tblDailyDelvStatus.removeAll();
				
				if (!cbDDSBranch.getText().equals(EMPTY_STRING)
						&& !txtDDSDate.getText().equals(EMPTY_STRING)) {
					DailyDeliveryStatusDTO[] dto = getDailyDeliveryStatus();

					if (dto != null) {							
						populateDailyDeliveryStatus(dto);
					} 
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void handleEvent(Event e) {
		// TODO Auto-generated method stub
		
		TableColumn column = (TableColumn) e.widget;
		
	}
}
