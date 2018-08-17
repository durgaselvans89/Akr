/**
 * 
 */
package hm.akr.container.admin;

import hm.akr.common.BeanUtil;
import hm.akr.common.KalendarDialog;
import hm.akr.common.MonthDialog;
import hm.akr.common.SWTResourceManager;
//import hm.akr.container.inward.InwardAnalysisComposite;
import hm.akr.dto.BookedLRDTO;
import hm.akr.dto.DailyStatusDTO;
import hm.akr.dto.StationsDTO;
import hm.akr.dto.TranshipmentDTO;
import hm.akr.dto.VersionDTO;
import hm.akr.dto.printer.DailyStatusDTODecorator;

import java.text.Collator;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
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

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
/**
 * 
 * @author kibaitachi
 * 
 */
public class MonitorReportsComposite extends Composite {

	Shell shell = null;

	private Label lbltbranch;

	private Combo cbtbranch;

	private TableColumn tableColumn1;

	private Button btnoutdate;

	private Text txtoutdate;

	private TableColumn colStationName;

	private Group group1;

	private Button btnReport;

	AdminCompositeHandler handler = null;

	VersionDTO[] versiondto = null;

	BeanUtil beanutil = null;

	/**
	 * 
	 * @param shell
	 * @param style
	 * @throws Exception
	 */
	public MonitorReportsComposite(Shell shell, int style) throws Exception {
		super(shell, style);

		this.shell = shell;

		handler = new AdminCompositeHandler();
		beanutil = BeanUtil.getInstance();
		SERVER_DATE = beanutil.getServerDate();

	}

	private Button btnSendReport;

	private Button btnGo;

	private Button btnTo;

	private Text txtTo;

	private Label lblTo;

	private Button btnFrom;

	private Text txtFrom;

	private Label lblfrom;

	private TableColumn tblcolBilling;

	private TableColumn tblcolPaid;

	private TableColumn tblcolTopay;

	private TableColumn tblcolStation;

	private Table tblReport;

	private Label lblName;

	private Display display;

	private static final String JRXML_FILE = "hm/akr/resources/printer/DAILYREPORT.jrxml";

	private Canvas canvas2;

	private TabItem tabItem1;

	private TabFolder tabReport;

	private Text txtDate;

	Date reportDate = null;

	private TabItem tabItem3;

	private Canvas versioncanvas;

	private Table tblversion;

	private TableColumn colbranch;

	private TableColumn colbranchcode;

	private TableColumn coltpod1;

	private TabItem tiBookedLR;

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

	private Text txtTFrom;

	private String SERVER_DATE = "";

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

	private Combo cbLSB;

	private TableColumn colLRTotal;

	private TableColumn colTsno;

	private Label label1;

	private TableColumn tblcolIntime1day;

	private Button btnMonth;

	private TabItem tiSendReport;

	private Canvas canvas8;

	private TabItem tiInwardAnalysis;

	private Canvas canvas9;

	private Composite composite;

	/**
	 * MEthod to load Monitoring Composite
	 * 
	 * @return
	 * @throws Exception
	 */
	public Composite loadComposite() throws Exception {
		this.setSize(974, 735);

		
		{
			tabReport = new TabFolder(this, SWT.NONE);
			tabReport.setBounds(10, 100, 990, 585);
			tabReport.addSelectionListener(new MoveAction());
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
						tblReport.setBounds(67, 49, 745, 431);
						{
							colsno = new TableColumn(tblReport, SWT.NONE);
							colsno.setText("S NO");
							colsno.setWidth(82);
							colsno.addListener(SWT.Selection,
									new sortListener());
						}
						{
							colbranch = new TableColumn(tblReport, SWT.NONE);
							colbranch.setText("Branch Code");
							colbranch.setWidth(100);
							colbranch.addListener(SWT.Selection,
									new sortListener());

						}
						{
							tblcolStation = new TableColumn(tblReport, SWT.NONE);
							tblcolStation.setText("STATION NAME");
							tblcolStation.setWidth(200);
							tblcolStation.addListener(SWT.Selection,
									new sortListener());

						}
						{
							tblcolTopay = new TableColumn(tblReport, SWT.NONE);
							tblcolTopay.setText("TOPAY");
							tblcolTopay.setWidth(103);
							tblcolTopay.addListener(SWT.Selection,
									new sortListener());
						}
						{
							tblcolPaid = new TableColumn(tblReport, SWT.NONE);
							tblcolPaid.setText("PAID");
							tblcolPaid.setWidth(113);
							tblcolPaid.addListener(SWT.Selection,
									new sortListener());
						}
						{
							tblcolBilling = new TableColumn(tblReport, SWT.NONE);
							tblcolBilling.setText("BILLING");
							tblcolBilling.setWidth(133);
							tblcolBilling.addListener(SWT.Selection,
									new sortListener());
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
						btnFrom.addSelectionListener(new MoveAction());
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
						btnTo.addSelectionListener(new MoveAction());
					}
					{
						btnReport = new Button(canvas2, SWT.PUSH | SWT.CENTER);
						btnReport.setText("GO");
						btnReport.setBounds(544, 5, 47, 22);
						btnReport.addSelectionListener(new MoveAction());
					}
				}
			}

			
			if(BeanUtil.getDbName() == null || BeanUtil.getDbName().equals(""))
			{tabItem3 = new TabItem(tabReport, SWT.NONE);
				tabItem3.setText("VERSION REPORT");

				versioncanvas = new Canvas(tabReport, SWT.NONE);
				tabItem3.setControl(versioncanvas);
				{
					tblversion = new Table(versioncanvas, SWT.FULL_SELECTION
							| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
					tblversion.setBounds(30, 26, 800, 418);

					tblversion.setHeaderVisible(true);
					tblversion.setLinesVisible(true);

					{
						tableColumn1 = new TableColumn(tblversion, SWT.NONE);
						tableColumn1.setText("SNO");
						tableColumn1.setWidth(45);
						tableColumn1.addListener(SWT.Selection,
								new sortListener());

					}
					{
						colbranchcode = new TableColumn(tblversion, SWT.NONE);
						colbranchcode.setText("Branch Code");
						colbranchcode.setWidth(100);
						colbranchcode.addListener(SWT.Selection,
								new sortListener());
					}
					{
						colStationName = new TableColumn(tblversion, SWT.NONE);
						colStationName.setText("Station Name");
						colStationName.setWidth(267);
						colStationName.addListener(SWT.Selection,
								new sortListener());
					}

					{
						coltpod1 = new TableColumn(tblversion, SWT.NONE);
						coltpod1.setText("Version");
						coltpod1.setWidth(365);
					}

				}
			}

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
							tblTranshipment.setBounds(8, 54, 798, 431);
							{
								colTsno = new TableColumn(tblTranshipment,
										SWT.NONE);
								colTsno.setText("S NO");
								colTsno.setWidth(40);
								colTsno.addListener(SWT.Selection,
										new sortListener());

							}
							{
								tblcolBranchName = new TableColumn(
										tblTranshipment, SWT.NONE);
								tblcolBranchName.setText("Station  Name");
								tblcolBranchName.setWidth(180);
								tblcolBranchName.addListener(SWT.Selection,
										new sortListener());

							}
							{
								colbranchCode = new TableColumn(
										tblTranshipment, SWT.NONE);
								colbranchCode.setText("Station Code");
								colbranchCode.setWidth(80);
								colbranchCode.addListener(SWT.Selection,
										new sortListener());
							}
							{
								tblcolInwardedLRs = new TableColumn(
										tblTranshipment, SWT.NONE);
								tblcolInwardedLRs.setText("Inwarded LRs");
								tblcolInwardedLRs.setWidth(85);
								tblcolInwardedLRs.addListener(SWT.Selection,
										new sortListener());
							}
							{
								tblcol1Day = new TableColumn(tblTranshipment,
										SWT.NONE);
								tblcol1Day.setText("1 Day %");
								tblcol1Day.setWidth(60);
								tblcol1Day.addListener(SWT.Selection,
										new sortListener());

							}
							{
								tblcol2Day = new TableColumn(tblTranshipment,
										SWT.NONE);
								tblcol2Day.setText("2 Day %");
								tblcol2Day.setWidth(60);
								tblcol2Day.addListener(SWT.Selection,
										new sortListener());

							}

							{
								tblcol3Day = new TableColumn(tblTranshipment,
										SWT.NONE);
								tblcol3Day.setText("3 Day %");
								tblcol3Day.setWidth(60);
								tblcol3Day.addListener(SWT.Selection,
										new sortListener());

							}
							{
								tblcolAbove3 = new TableColumn(tblTranshipment,
										SWT.NONE);
								tblcolAbove3.setText(" > 3 Day %");
								tblcolAbove3.setWidth(80);
								tblcolAbove3.addListener(SWT.Selection,
										new sortListener());

							}
							{
								tblcolIntime1day = new TableColumn(
										tblTranshipment, SWT.NONE);
								tblcolIntime1day.setText("Intime1Day %");
								tblcolIntime1day.setWidth(85);
								tblcolIntime1day.addListener(SWT.Selection,
										new sortListener());

							}
							{
								tblcolStock = new TableColumn(tblTranshipment,
										SWT.NONE);
								tblcolStock.setText("Stock");
								tblcolStock.setWidth(60);
								tblcolStock.addListener(SWT.Selection,
										new sortListener());

							}

						}
						{
							lblTfrom = new Label(canvas6, SWT.NONE);
							lblTfrom.setText("Month");
							lblTfrom.setBounds(285, 23, 32, 17);
						}
						{
							txtTFrom = new Text(canvas6, SWT.BORDER);
							txtTFrom.setBounds(321, 22, 61, 21);
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
							btnMonth.setBounds(386, 21, 26, 23);
							btnMonth.addSelectionListener(new MoveAction());
						}

						{
							cbtbranch = new Combo(canvas6, SWT.READ_ONLY);
							cbtbranch.setBounds(116, 21, 136, 21);
							cbtbranch.addSelectionListener(new MoveAction());
							populateLRBranches();
						}
						{
							lbltbranch = new Label(canvas6, SWT.NONE);
							lbltbranch.setText("Select Branch");
							lbltbranch.setBounds(41, 23, 73, 19);
						}
					}

				}
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

						cbLSB.addSelectionListener(new MoveAction());
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
							btnLFrom.addSelectionListener(new MoveAction());
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
							btnLTo.addSelectionListener(new MoveAction());
						}
						{
							btnLGo = new Button(canvas7, SWT.PUSH | SWT.CENTER);
							btnLGo.setText("GO");
							btnLGo.setBounds(572, 8, 35, 23);
							btnLGo.addSelectionListener(new MoveAction());
						}
					}
				}
				
				if(BeanUtil.getDbName() == null || BeanUtil.getDbName().equals("")){
					
					tiSendReport = new TabItem(tabReport, SWT.NONE);
					tiSendReport.setText("Send Report");
					canvas8 = new Canvas(tabReport, SWT.BORDER);					
					tiSendReport.setControl(canvas8);	
					
					group1 = new Group(canvas8, SWT.NONE);							
					GridLayout group1Layout = new GridLayout();
					group1Layout.makeColumnsEqualWidth = true;
					group1.setLayout(group1Layout);
					group1.setText("Monitoring Reports");
					group1.setBounds(100,50, 300, 100);

					{
						txtDate = new Text(group1, SWT.BORDER);
						txtDate.setBounds(33, 40, 80, 25);
						txtDate.setEditable(false);
						DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
						java.util.Date currentDate = new java.util.Date();
						String date = dateFormat.format(currentDate);

						if (SERVER_DATE != null) {
							txtDate.setText(SERVER_DATE);
						} else {
							txtDate.setText(date);
						}
					}
					
					{
						btnGo = new Button(group1, SWT.PUSH | SWT.CENTER);
						btnGo.setImage(SWTResourceManager
								.getImage("hm/akr/resources/Calendar.jpg"));
						btnGo.setBounds(110, 40, 34, 25);
						btnGo.addSelectionListener(new MoveAction());
					}
					
					{
						btnSendReport = new Button(group1, SWT.PUSH | SWT.CENTER);
						btnSendReport.setText("Generate Report");
						btnSendReport.setBounds(150, 38, 95, 31);
						btnSendReport.addSelectionListener(new MoveAction());
					}
					
				}
				
				{					
					tiInwardAnalysis = new TabItem(tabReport, SWT.NONE);
					tiInwardAnalysis.setText("Inward Analysis");	
					
					canvas9 = new Canvas(tabReport, SWT.BORDER);
					canvas9.setBounds(0,0,1000,650);
					tiInwardAnalysis.setControl(canvas9);
					
					//composite = new InwardAnalysisComposite(canvas9, SWT.BORDER).loadcomposite();								
					composite.setBounds(0,0,1000,650);
					
				}				

			}
			tabReport.setSelection(0);
		
			
		populateReport();
		// populateOutstandingReport();
		// populateVersionReport();
		
		
		return this;
	}

	/**
	 * 
	 * @throws Exception
	 */
	private void populateVersionReport() throws Exception {
		try {

			if (tblversion != null)
				tblversion.removeAll();
			if (null == versiondto)
				versiondto = beanutil.getVersionDetails();
			StationsDTO[] stations = beanutil.getAvailableStations();

			if (null != versiondto) {
				for (int i = 0; i < versiondto.length; i++) {
					boolean flag = false;
					TableItem[] items = tblversion.getItems();
					if (items.length == 0) {
						TableItem item = new TableItem(tblversion, SWT.NONE);
						item.setText(0, String.valueOf(i + 1));
						item.setText(1, versiondto[i].getBranch_code());
						item.setText(2, getStationName(versiondto[i]
								.getStation_code(), stations));
						item.setText(3, versiondto[i].getVersion_id());

					} else {
						for (int j = 0; j < items.length; j++) {
							String code = getStationCode(items[j].getText(2),
									stations);
							if (null != code)
								if (code
										.equals(versiondto[i].getStation_code())) {
									String id = items[j].getText(3);
									items[j].setText(3, id + ","
											+ versiondto[i].getVersion_id());
									flag = true;
									break;
								}

						}
						if (!flag) {
							TableItem item = new TableItem(tblversion, SWT.NONE);
							item.setText(0, String.valueOf(i + 1));
							item.setText(1, versiondto[i].getBranch_code());
							if (null != versiondto[i].getStation_code()) {
								String name = getStationName(versiondto[i]
										.getStation_code(), stations);
								if (null != name)
									item.setText(2, name);

								item.setText(3, versiondto[i].getVersion_id());
							}
						}
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param stations
	 * @return
	 */
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

	/**
	 * 
	 * @param name
	 * @param stations
	 * @return
	 */
	private String getStationCode(String name, StationsDTO[] stations) {
		int size = 0;
		size = stations.length;
		if (size > 0) {
			for (int i = 0; i < size; i++) {
				if (stations[i].getName().equals(name)) {
					return stations[i].getId();
				}
			}
		}
		return null;
	}

	/**
	 * Method to populate Report
	 * 
	 * @throws Exception
	 */
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

	/**
	 * 
	 * @param index
	 * @param refTable
	 */
	private void sortByString(int index, Table refTable) {

		TableItem[] items = refTable.getItems();
		Collator collator = Collator.getInstance(Locale.getDefault());

		int len = items.length;

		// Sorting need to happen only if the number of rows available
		// is greater than one excluding total
		if (len > 1) {
			for (int i = 1; i < len; i++) {
				if (!items[i].getText(index).isEmpty()) {
					String value1 = items[i].getText(index);
					for (int j = 0; j < i; j++) {
						String value2 = items[j].getText(index);
						if (collator.compare(value1, value2) < 0) {
							String[] values = { items[i].getText(0),
									items[i].getText(1), items[i].getText(2),
									items[i].getText(3), items[i].getText(4),
									items[i].getText(5), items[i].getText(6),
									items[i].getText(7), items[i].getText(8),
									items[i].getText(9), items[i].getText(10),
									items[i].getText(11), items[i].getText(12),
									items[i].getText(13), items[i].getText(14) };
							items[i].dispose();

							TableItem item = new TableItem(refTable, SWT.NONE,
									j);
							item.setText(values);
							items = refTable.getItems();
							break;
						}
					}
				}
			}

		}
	}

	/**
	 * 
	 * @param refTable
	 */
	private void setSerialNumber(Table refTable) {
		if (null != refTable) {
			TableItem[] items = refTable.getItems();
			if (null != items) {
				int len = items.length;
				for (int i = 0; i < len; i++) {
					items[i].setText(0, String.valueOf(i + 1));
				}
			}
		}

	}

	/**
	 * 
	 * @param index
	 * @param refTable
	 */
	private void SortByFloat(int index, Table refTable) {
		TableItem[] items = refTable.getItems();
		float value2 = 0;
		float value1 = 0;
		int len = items.length;

		// Sorting need to happen only if the number of rows available
		// is greater than one excluding total
		if (len > 1) {

			for (int i = 1; i < len; i++) {
				if (!items[i].getText(index).isEmpty()) {

					value1 = Float.parseFloat(items[i].getText(index));

					for (int j = 0; j < i; j++) {
						if (!items[j].getText(index).isEmpty())
							value2 = Float.parseFloat(items[j].getText(index));

						if (value1 < value2) {
							String[] values = { items[i].getText(0),
									items[i].getText(1), items[i].getText(2),
									items[i].getText(3), items[i].getText(4),
									items[i].getText(5), items[i].getText(6),
									items[i].getText(7), items[i].getText(8),
									items[i].getText(9), items[i].getText(10),
									items[i].getText(11), items[i].getText(12),
									items[i].getText(13), items[i].getText(14) };

							items[i].dispose();
							TableItem item = new TableItem(refTable, SWT.NONE,
									j);
							item.setText(values);
							items = refTable.getItems();
							break;
						}
					}
				}
			}

		}

	}

	/**
	 * 
	 * @author kibaitachi
	 * 
	 */
	public class sortListener implements Listener {

		@Override
		public void handleEvent(Event e) {

			int selectionIndex = tabReport.getSelectionIndex();
			TableColumn column = (TableColumn) e.widget;

			if (selectionIndex == 0) {
				if (column == colsno) {
					SortByFloat(0, tblReport);
				} else if (column == colbranch) {
					sortByString(1, tblReport);
				} else if (column == tblcolStation) {
					sortByString(2, tblReport);
				} else if (column == tblcolTopay) {
					SortByFloat(3, tblReport);
				} else if (column == tblcolPaid) {
					SortByFloat(4, tblReport);
				} else if (column == tblcolBilling) {
					SortByFloat(5, tblReport);
				}
			} else if (selectionIndex == 1) {
				if (column == tableColumn1) {
					SortByFloat(0, tblversion);
				} else if (column == colbranchcode) {
					sortByString(1, tblversion);
				} else if (column == colStationName) {
					sortByString(2, tblversion);
				}
			} else if (selectionIndex == 2) {
				if (column == colTsno) {
					SortByFloat(0, tblTranshipment);
				} else if (column == tblcolBranchName) {
					sortByString(1, tblTranshipment);
				} else if (column == colbranchCode) {
					sortByString(2, tblTranshipment);
				} else if (column == tblcolInwardedLRs) {
					SortByFloat(3, tblTranshipment);
				} else if (column == tblcol1Day) {
					SortByFloat(4, tblTranshipment);
				} else if (column == tblcol2Day) {
					SortByFloat(5, tblTranshipment);
				} else if (column == tblcol3Day) {
					SortByFloat(6, tblTranshipment);
				} else if (column == tblcolAbove3) {
					SortByFloat(7, tblTranshipment);
				} else if (column == tblcolIntime1day) {
					SortByFloat(8, tblTranshipment);
				} else if (column == tblcolStock) {
					SortByFloat(9, tblTranshipment);
				}
				setSerialNumber(tblTranshipment);
			}

		}

	}

	/**
	 * 
	 * @author kibaitachi
	 * 
	 */
	public class MoveAction implements SelectionListener {

		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {

		}

		@Override
		public void widgetSelected(SelectionEvent event) {
			Object source = event.getSource();

			int selectionIndex = tabReport.getSelectionIndex();
			String selectedTab = tabReport.getItem(selectionIndex).getText();

			if (btnGo == source) {
				KalendarDialog cd = new KalendarDialog(shell);

				Object obj = cd.open();
				if (obj != null)
					txtDate.setText(obj.toString());

			} else if (btnFrom == source) {
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

			}

			else if (selectedTab.equalsIgnoreCase("VERSION REPORT")) {
				try {
					populateVersionReport();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			else if (btnTo == source) {
				KalendarDialog cd = new KalendarDialog(shell);

				Object obj = cd.open();
				if (obj != null)
					txtTo.setText(obj.toString());
			} else if (btnoutdate == source) {
				KalendarDialog cd = new KalendarDialog(shell);

				Object obj = cd.open();
				if (obj != null)
					txtoutdate.setText(obj.toString());
			}

			else if (btnSendReport == source) {

				if (txtDate.equals("")) {
					displayError("Select Date");
				} else {

					DailyStatusDTO[] dto = null;
					HashMap<String, String> hashMap = new HashMap<String, String>();
					hashMap.put("Title", "DAILYSTATUS");
					hashMap.put("Date", txtDate.getText());

					ArrayList<DailyStatusDTODecorator> list = new ArrayList<DailyStatusDTODecorator>();
					try {

						DateFormat dateFormat = new SimpleDateFormat(
								"dd-MM-yyyy");

						try {
							reportDate = dateFormat.parse(txtDate.getText());
						} catch (ParseException e) {
							e.printStackTrace();
						}
						dto = beanutil.getDailyStatus(reportDate);

						if (null != dto) {
							int size = dto.length;
							for (int i = 0; i < size; i++) {
								list.add(new DailyStatusDTODecorator(dto[i],
										i + 1));
							}
						}
						if (null != beanutil) {
							boolean sent = beanutil.sendMessage(list,
									JRXML_FILE, hashMap);
							if (sent) {
								displayError("Report Mail Sent Successfully");
							}
						}

					} catch (Exception exception) {
						exception.printStackTrace();
					}

				}

			} else if (btnLFrom == source) {
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

			} else if (btnMonth == source) {
				MonthDialog cd = new MonthDialog(new Shell());
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

		/**
		 * 
		 */

		/**
		 * @throws ParseException,Exception
		 * 
		 */

		/**
		 * 
		 * @param dto
		 */
	}

	/**
	 * 
	 * @param errorText
	 */
	private void displayError(String errorText) {
		MessageBox messageBox = new MessageBox(shell, SWT.OK);
		messageBox.setMessage(errorText);
		messageBox.setText("Alert");
		messageBox.open();
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
					if (null != cbtbranch)
						cbtbranch.add(stations[i].getName() + " - "
								+ stations[i].getId());
				}
				if (null != cbLSB)
					cbLSB.select(0);
				if (null != cbtbranch)
					cbtbranch.select(0);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}

	/**
	 * 
	 * @param fromDate
	 * @param toDate
	 * @param branchCode
	 * @throws Exception
	 */
	private void populateBookedLRs(Date fromDate, Date toDate, String branchCode)
			throws Exception {
		BookedLRDTO[] bookedLRDto = null;
		String stationCode = null;

		try {
			bookedLRDto = handler.getBookedLRs(fromDate, toDate, branchCode);
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

			} else {
				if (null != tblBookedLR) {
					tblBookedLR.removeAll();
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
