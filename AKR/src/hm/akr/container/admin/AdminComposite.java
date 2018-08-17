package hm.akr.container.admin;

import hm.akr.common.BeanUtil;
import hm.akr.common.FloatValidation;
import hm.akr.common.KalendarDialog;
import hm.akr.common.NumericValidation;
import hm.akr.common.SWTResourceManager;
import hm.akr.common.TableSort;
import hm.akr.container.ContainerManager;
import hm.akr.dto.DailyDeliveryStatusDTO;
import hm.akr.dto.DeliveryVerificationDTO;
import hm.akr.dto.LRNumberRangeDTO;
import hm.akr.dto.MsgDTO;
import hm.akr.dto.StationsDTO;
import hm.akr.msb.TreeComposite;
import hm.akr.workspace.MainForm;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.naming.NamingException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
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
 * This code was edited or generated using CloudGarden's Jigloo
 * SWT/Swing GUI Builder, which is free for non-commercial
 * use. If Jigloo is being used commercially (ie, by a corporation,
 * company or business for any purpose whatever) then you
 * should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details.
 * Use of Jigloo implies acceptance of these licensing terms.
 * A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
 * THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
 * LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
/**
 * Class for Admin Composite
 * 
 * @author kibaitachi
 * 
 */
public class AdminComposite extends Composite {

	Properties properties = null;

	AdminCompositeHandler handler = null;

	private Button btnSetup;

	private Combo coStation;

	private Label lblStation;

	private Label lblBranch;

	private Combo coBranch;

	private Canvas canvas1;

	private Label lblName;

	private Button btnNew;

	ContainerManager manager = null;

	Shell shell = null;
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
	private Group gpLrTrack;
	private Button btnLRTrackView;
	private Button btnLRTRackToDate;
	private Text txtLRTrackToDate;
	private Label lblLRTrackToDate;
	private Button btnLRTrack;
	private Text txtLRTRackDate;
	private Label lblLRTRackDate;
	private Label lblLrTrackResult;
	private Label lblLrtrackDisplay;
	private Canvas canLRTrack;
	private TabItem tiLRTrack;
	private Group gpCFTSet;
	private Button btnCFTSet;
	private Text txtCm;
	private Text txtCFTFt;
	private Text txtCFTInch;
	private Label lblCFTCm;
	private Label lblCFTFt;
	private Label lblCFTInch;

	private Button btnAssignStationary;

	private Button btnAll;

	private Button chkCR;

	private Button chkBilling;

	private Button chkPaid;

	private Button chkTopay;

	private Group group1;

	Display display = null;

	BeanUtil beanutil = null;

	MsgDTO[] msgs = null;

	ArrayList<String> branchList = null;

	ArrayList<String> stationList = null;

	private TabFolder tabReport;

	private TabItem tiStationarySettings;

	private Canvas canvas2;

	private Button btnUpdate;

	private Label label1;

	private Combo cbSB;

	private Table tblStationary;

	private TableColumn column;

	private TableItem item;

	private TableEditor[] editor1;

	private TableEditor[] editor2;

	private TableEditor[] editor3;

	private TableEditor[] editor4;

	private String[] column_head = { "S.No", "Station", "TOPAY", "PAID",
			"BILLING", "CR" };

	private Text[] txtPaid;

	private Text[] txtTopay;

	private Text[] txtBilling;

	private Text[] txtCr;

	private TabItem tiAssignStationary;

	private Canvas canvas3;

	private Button btnAssign;

	private Composite cptTree1;

	private Canvas canvas4;

	private Button btnSetAlarm;

	private Text txtTopayAlarm;

	private Text txtPaidAlarm;

	private Text txtBillingAlarm;

	private Text txtCRAlarm;

	private TabItem tiStationaryReport;

	private Canvas canvas5;

	private Table tblStationaryReport;

	private TableColumn colSno;

	private TableColumn colStation;

	private TableColumn colTopay;

	private TableColumn colPaid;

	private TableColumn colBilling;

	private TableColumn colCR;

	private String EMPTY_STRING = "";

	private String STATIONARY_REPORT_TAB = "Stationary Reports";

	private String ASSIGN_STATIONARY_TAB = "Assign Stationary";

	private String STATIONARY_SETTINGS_TAB = "Stationary Settings";

	private String CONTACT_REPORT_TAB = "Delivery Verification";

	private String DAILY_DELIVERY_STATUS = "Daily Delivery Status";

	private TabItem tiCFT;

	private Canvas canCFT;

	private String SERVER_DATE;

	private TabItem tiContactReport;

	private Canvas canContact;

	private Table tblContactReport;

	private Label lblContactBranch;

	private Combo cbContactBranch;

	private Label lblDVSelectstation;

	private Combo cbDVSelectstation;

	private Label lblDVLrType;

	private Combo cbDVLrType;

	private Label lblDVDeliveryType;

	private Combo cbDVDeliveryType;

	private Label lblDVInwardDays;

	private Text txtDVInwardDays;

	private Label lblDVAmount;

	private Text txtDVAmount;

	private Button btnDVSubmit;

	private TableColumn colContactSno;

	private TableColumn colContactLrno;

	private TableColumn colContactLrdate;

	private TableColumn colContactCnorName;

	private TableColumn colContactCneeName;

	private TableColumn colContactCneeAddress;

	private TableColumn colContactNoa;

	private TableColumn colContactActWt;

	private TableColumn colContactArtID;

	private TableColumn colContactLrTotal;

	private TableColumn colContactDDCrg;

	private TableColumn colContactLrType;

	private TableColumn colContactInwarddays;

	private TableColumn colContactFrom;

	private TableColumn colContactTo;

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

	
	float clientRights = -1;
	
	private float RIGHTS_22 = new Float(2.2);
	private float RIGHTS_25 = new Float(2.5);
	private float RIGHTS_3 = new Float(3);
	
	public AdminComposite(Shell shell, int swtValue) throws Exception {
		super(shell, swtValue);
		this.shell = shell;

		try {

			handler = new AdminCompositeHandler();
			beanutil = BeanUtil.getInstance();
			SERVER_DATE = beanutil.getServerDate();
			clientRights = beanutil.getClientRights();

			user_formate = new SimpleDateFormat("dd-MM-yyyy");

		} catch (Exception e) {

			throw e;
		}

	}

	/**
	 * Method to load composite
	 * 
	 * @return
	 */
	public Composite loadComposite() {
		try {
			{
				this.setSize(974, 749);
				if (beanutil.isAdminHead()) {

					{
						lblBranch = new Label(this, SWT.NONE);
						lblBranch.setText("Select Branch ");
						lblBranch.setBounds(345, 114, 76, 18);
					}
					{
						coBranch = new Combo(this, SWT.BORDER | SWT.READ_ONLY);
						coBranch.setBounds(440, 112, 187, 21);
						populateBranches();
						coBranch.addSelectionListener(new SetUpAction());
					}

					if (clientRights == RIGHTS_22 || clientRights == RIGHTS_25 || clientRights == RIGHTS_3) {
						{
							tabReport = new TabFolder(this, SWT.NONE);
							tabReport.setBounds(50, 210, 915, 480);
							tabReport.addSelectionListener(new SetUpAction());
							
							if(BeanUtil.getDbName() == null || BeanUtil.getDbName().equals("")){
								tiStationarySettings = new TabItem(tabReport,
										SWT.NONE);
								tiStationarySettings
										.setText(STATIONARY_SETTINGS_TAB);
								{
									canvas2 = new Canvas(tabReport, SWT.NONE);
									tiStationarySettings.setControl(canvas2);
									{
										label1 = new Label(canvas2, SWT.NONE);
										label1.setText("Select");
										label1.setBounds(30, 16, 75, 15);
									}

									{
										cbSB = new Combo(canvas2, SWT.DROP_DOWN
												| SWT.READ_ONLY);
										cbSB.setBounds(110, 13, 150, 10);

										cbSB
												.addSelectionListener(new SetUpAction());
										populateSelectedBranches();
									}

									{
										try {
											String value = cbSB.getItem(0);
											cbSB.setText(value);
											int index = value.indexOf(" - ");
											String branchCode = value
													.substring(index + 3);
											StationsDTO rowDTO[] = null;
											rowDTO = handler
													.getStations(branchCode);
											if (rowDTO != null) {
												int rows = rowDTO.length;
												Object[] rowData = rowDTO;
												createTable(6, rows,
														column_head, rowData);
												populateStationaryValues(branchCode);
											}

										} catch (Exception exception) {
											exception.printStackTrace();
										}
									}
									{
										btnUpdate = new Button(canvas2,
												SWT.PUSH | SWT.CENTER);
										btnUpdate.setText("Update");
										btnUpdate.setBounds(730, 427, 50, 25);
										btnUpdate
												.addSelectionListener(new SetUpAction());
									}
								}
							}

							if(BeanUtil.getDbName() == null || BeanUtil.getDbName().equals(""))
							{tiAssignStationary = new TabItem(tabReport,
									SWT.NONE);
							tiAssignStationary.setText(ASSIGN_STATIONARY_TAB);
							{
								canvas3 = new Canvas(tabReport, SWT.NONE);
								tiAssignStationary.setControl(canvas3);

								cptTree1 = new TreeComposite(canvas3, SWT.NONE,
										300).loadComposite();
								cptTree1.setBounds(50, 50, 250, 350);

								btnAssign = new Button(canvas3, SWT.PUSH);
								btnAssign.setBounds(425, 198, 50, 25);
								btnAssign.setText("Assign");
								btnAssign
										.addSelectionListener(new SetUpAction());
								{
									chkTopay = new Button(canvas3, SWT.CHECK
											| SWT.LEFT);
									chkTopay.setText("Topay");
									chkTopay.setBounds(345, 158, 60, 27);
								}
								{
									chkPaid = new Button(canvas3, SWT.CHECK
											| SWT.LEFT);
									chkPaid.setText("Paid");
									chkPaid.setBounds(345, 182, 62, 33);
								}
								{
									chkBilling = new Button(canvas3, SWT.CHECK
											| SWT.LEFT);
									chkBilling.setText("Billing");
									chkBilling.setBounds(345, 210, 62, 30);
								}
								{
									chkCR = new Button(canvas3, SWT.CHECK
											| SWT.LEFT);
									chkCR.setText("CR");
									chkCR.setBounds(345, 236, 60, 33);
								}
							}
							}

							if(BeanUtil.getDbName() == null || BeanUtil.getDbName().equals("")){
								tiStationaryReport = new TabItem(tabReport,
										SWT.NONE);
								tiStationaryReport
										.setText(STATIONARY_REPORT_TAB);

								{
									canvas5 = new Canvas(tabReport, SWT.NONE);
									tiStationaryReport.setControl(canvas5);
									tblStationaryReport = new Table(canvas5,
											SWT.MULTI | SWT.BORDER
													| SWT.FULL_SELECTION
													| SWT.CHECK);
									tblStationaryReport.setLinesVisible(true);
									tblStationaryReport.setHeaderVisible(true);
									tblStationaryReport.setBounds(100, 40, 500,
											380);
									{
										btnAll = new Button(canvas5, SWT.CHECK
												| SWT.CENTER);
										btnAll.setText("Assign to all");
										btnAll.setBounds(516, 15, 83, 20);
										btnAll
												.addSelectionListener(new SetUpAction());
									}
									{
										btnAssignStationary = new Button(
												canvas5, SWT.PUSH | SWT.CENTER);
										btnAssignStationary.setText("Assign");
										btnAssignStationary.setBounds(516, 425,
												67, 26);
										btnAssignStationary
												.addSelectionListener(new SetUpAction());
									}

									{
										colSno = new TableColumn(
												tblStationaryReport, SWT.NONE);
										colSno.setText("S.No");
										colSno.setWidth(40);
									}
									{
										colStation = new TableColumn(
												tblStationaryReport, SWT.NONE);
										colStation.setText("Station");
										colStation.setWidth(100);
									}
									{
										colTopay = new TableColumn(
												tblStationaryReport, SWT.NONE);
										colTopay.setText("Topay");
										colTopay.setWidth(84);
									}
									{
										colPaid = new TableColumn(
												tblStationaryReport, SWT.NONE);
										colPaid.setText("Paid");
										colPaid.setWidth(84);
									}
									{
										colBilling = new TableColumn(
												tblStationaryReport, SWT.NONE);
										colBilling.setText("Billing");
										colBilling.setWidth(84);
									}
									{
										colCR = new TableColumn(
												tblStationaryReport, SWT.NONE);
										colCR.setText("CR");
										colCR.setWidth(84);
									}

								}
							}

							
							/*{
							{
								tiLRTrack = new TabItem(tabReport, SWT.NONE);
								tiLRTrack.setText("LRTrackCount");
								{

									canLRTrack = new Canvas(tabReport, SWT.NONE);
									tiLRTrack.setControl(canLRTrack);
									{
										lblLrtrackDisplay = new Label(
												canLRTrack, SWT.NONE);
										lblLrtrackDisplay
												.setText("No of LR Track");
										lblLrtrackDisplay.setBounds(273, 133,
												72, 30);
									}
									{
										lblLrTrackResult = new Label(
												canLRTrack, SWT.BORDER);
										lblLrTrackResult.setBounds(357, 133,
												60, 25);
									}
									{
										lblLRTRackDate = new Label(canLRTrack,
												SWT.NONE);
										lblLRTRackDate.setText("From Date");
										lblLRTRackDate.setBounds(221, 72, 52,
												30);
									}
									{
										txtLRTRackDate = new Text(canLRTrack,
												SWT.BORDER);
										txtLRTRackDate.setBounds(274, 72, 81,
												22);
										txtLRTRackDate.setEditable(false);
										DateFormat dateFormat = new SimpleDateFormat(
												"dd-MM-yyyy");
										java.util.Date currentDate = new java.util.Date();
										String date = dateFormat
												.format(currentDate);

										if (SERVER_DATE != null) {
											txtLRTRackDate.setText(SERVER_DATE);
										} else {
											txtLRTRackDate.setText(date);
										}
									}
									{
										btnLRTrack = new Button(canLRTrack,
												SWT.PUSH | SWT.CENTER);
										btnLRTrack.setText("Go");
										btnLRTrack.setBounds(362, 71, 33, 30);
										btnLRTrack
												.addSelectionListener(new SetUpAction());

									}
									{
										lblLRTrackToDate = new Label(
												canLRTrack, SWT.NONE);
										lblLRTrackToDate.setText("To Date");
										lblLRTrackToDate.setBounds(422, 73, 43,
												27);
									}
									{
										txtLRTrackToDate = new Text(canLRTrack,
												SWT.BORDER);
										txtLRTrackToDate.setBounds(472, 74, 80,
												24);
										txtLRTrackToDate.setEditable(false);
										DateFormat dateFormat = new SimpleDateFormat(
												"dd-MM-yyyy");
										java.util.Date currentDate = new java.util.Date();
										String date = dateFormat
												.format(currentDate);

										if (SERVER_DATE != null) {
											txtLRTrackToDate
													.setText(SERVER_DATE);
										} else {
											txtLRTrackToDate.setText(date);
										}
									}
									{
										btnLRTRackToDate = new Button(
												canLRTrack, SWT.PUSH
														| SWT.CENTER);
										btnLRTRackToDate.setText("Go");
										btnLRTRackToDate.setBounds(564, 70, 33,
												30);
										btnLRTRackToDate
												.addSelectionListener(new SetUpAction());
									}
									{
										btnLRTrackView = new Button(canLRTrack,
												SWT.PUSH | SWT.CENTER);
										btnLRTrackView.setText("View");
										btnLRTrackView.setBounds(603, 70, 37,
												30);
										btnLRTrackView
												.addSelectionListener(new SetUpAction());
									}
									{
										gpLrTrack = new Group(canLRTrack,
												SWT.NONE);
										GridLayout gpLrTrackLayout = new GridLayout();
										gpLrTrackLayout.makeColumnsEqualWidth = true;
										gpLrTrack.setLayout(gpLrTrackLayout);
										gpLrTrack.setText("Lr Track Details");
										gpLrTrack.setBounds(195, 35, 478, 142);
									}
								}
							}
							}*/
							if(BeanUtil.getDbName() == null || BeanUtil.getDbName().equals("")){
								tiCFT = new TabItem(tabReport, SWT.NONE);
								tiCFT.setText("CFT");
								canCFT = new Canvas(tabReport, SWT.NONE);
								tiCFT.setControl(canCFT);
								{
									lblCFTInch = new Label(canCFT, SWT.NONE);
									lblCFTInch.setText("Inch");
									lblCFTInch.setBounds(285, 150, 39, 23);
								}
								{
									lblCFTFt = new Label(canCFT, SWT.NONE);
									lblCFTFt.setText("Ft");
									lblCFTFt.setBounds(285, 189, 39, 21);
								}
								{
									lblCFTCm = new Label(canCFT, SWT.NONE);
									lblCFTCm.setText("Cm");
									lblCFTCm.setBounds(285, 232, 39, 19);
								}
								{
									txtCFTInch = new Text(canCFT, SWT.BORDER);
									txtCFTInch.setBounds(353, 145, 60, 24);
									txtCFTInch
											.addVerifyListener(new FloatValidation());
								}
								{
									txtCFTFt = new Text(canCFT, SWT.BORDER);
									txtCFTFt.setBounds(353, 185, 60, 24);
									txtCFTFt
											.addVerifyListener(new FloatValidation());
								}
								{
									txtCm = new Text(canCFT, SWT.BORDER);
									txtCm.setBounds(353, 226, 60, 24);
									txtCm
											.addVerifyListener(new FloatValidation());
								}
								{
									btnCFTSet = new Button(canCFT, SWT.PUSH
											| SWT.CENTER);
									btnCFTSet.setText("Set");
									btnCFTSet.setBounds(369, 259, 45, 30);
									btnCFTSet
											.addSelectionListener(new SetUpAction());
								}
								{
									gpCFTSet = new Group(canCFT, SWT.NONE);
									GridLayout gpCFTSetLayout = new GridLayout();
									gpCFTSetLayout.makeColumnsEqualWidth = true;
									gpCFTSet.setLayout(gpCFTSetLayout);
									gpCFTSet.setText("CFT Settings");
									gpCFTSet.setBounds(253, 112, 234, 208);
								}

							}
							
							/*{
								tiContactReport = new TabItem(tabReport,
										SWT.NONE);
								tiContactReport.setText(CONTACT_REPORT_TAB);

								{
									canContact = new Canvas(tabReport, SWT.NONE);
									tiContactReport.setControl(canContact);
									tblContactReport = new Table(canContact,
											SWT.MULTI | SWT.BORDER
													| SWT.FULL_SELECTION);
									tblContactReport.setLinesVisible(true);
									tblContactReport.setHeaderVisible(true);
									tblContactReport.setBounds(16, 119, 780,
											315);

									{
										lblContactBranch = new Label(
												canContact, SWT.NONE);
										lblContactBranch
												.setText("Select Branch");
										lblContactBranch.setBounds(12, 21, 67,
												20);
									}
									{
										cbContactBranch = new Combo(canContact,
												SWT.NONE);
										cbContactBranch.setBounds(85, 19, 95,
												21);
										cbContactBranch
												.addSelectionListener(new SetUpAction());
									}
									{
										lblDVSelectstation = new Label(
												canContact, SWT.NONE);
										lblDVSelectstation
												.setText("Select Station");
										lblDVSelectstation.setBounds(206, 19,
												78, 21);
									}
									{
										cbDVSelectstation = new Combo(
												canContact, SWT.NONE);
										cbDVSelectstation.setBounds(291, 19,
												118, 21);
									}
									{
										lblDVLrType = new Label(canContact,
												SWT.NONE);
										lblDVLrType.setText("LrType");
										lblDVLrType.setBounds(430, 19, 41, 21);
									}
									{
										cbDVLrType = new Combo(canContact,
												SWT.NONE);
										cbDVLrType.setBounds(477, 19, 103, 21);
										cbDVLrType.add("All");
										cbDVLrType.add("Topay");
										cbDVLrType.add("Paid");
										cbDVLrType.add("Billing");

									}
									{
										lblDVDeliveryType = new Label(
												canContact, SWT.NONE);
										lblDVDeliveryType.setText("Delivery");
										lblDVDeliveryType.setBounds(599, 19,
												42, 21);
									}
									{
										cbDVDeliveryType = new Combo(
												canContact, SWT.NONE);
										cbDVDeliveryType.setBounds(647, 18, 81,
												21);
										cbDVDeliveryType.add("All");
										cbDVDeliveryType.add("Door");
										cbDVDeliveryType.add("Office");
									}
									{
										lblDVInwardDays = new Label(canContact,
												SWT.NONE);
										lblDVInwardDays.setText("InwardDays");
										lblDVInwardDays.setBounds(12, 77, 67,
												22);
									}
									{
										txtDVInwardDays = new Text(canContact,
												SWT.BORDER);
										txtDVInwardDays.setBounds(85, 73, 75,
												22);
									}
									{
										lblDVAmount = new Label(canContact,
												SWT.NONE);
										lblDVAmount.setText("Amount");
										lblDVAmount.setBounds(197, 75, 47, 22);
									}
									{
										txtDVAmount = new Text(canContact,
												SWT.BORDER);
										txtDVAmount.setBounds(258, 75, 60, 22);
									}
									{
										btnDVSubmit = new Button(canContact,
												SWT.PUSH | SWT.CENTER);
										btnDVSubmit.setText("View");
										btnDVSubmit.setBounds(344, 72, 60, 30);
										btnDVSubmit
												.addSelectionListener(new SetUpAction());
									}

									{
										colContactSno = new TableColumn(
												tblContactReport, SWT.NONE);
										colContactSno.setText("S.No");
										colContactSno.setWidth(50);
										colContactSno.addListener(
												SWT.Selection,
												new sortListener());

									}
									{
										colContactLrno = new TableColumn(
												tblContactReport, SWT.NONE);
										colContactLrno.setText("Lrno");
										colContactLrno.setWidth(100);
										colContactLrno.addListener(
												SWT.Selection,
												new sortListener());
									}
									{
										colContactLrdate = new TableColumn(
												tblContactReport, SWT.NONE);
										colContactLrdate.setText("Lrdate");
										colContactLrdate.setWidth(84);
										colContactLrdate.addListener(
												SWT.Selection,
												new sortListener());
									}
									{
										colContactCnorName = new TableColumn(
												tblContactReport, SWT.NONE);
										colContactCnorName.setText("Cnor Name");
										colContactCnorName.setWidth(84);
										colContactCnorName.addListener(
												SWT.Selection,
												new sortListener());
									}
									{
										colContactCneeName = new TableColumn(
												tblContactReport, SWT.NONE);
										colContactCneeName.setText("Cnee Name");
										colContactCneeName.setWidth(84);
										colContactCneeName.addListener(
												SWT.Selection,
												new sortListener());
									}
									{
										colContactCneeAddress = new TableColumn(
												tblContactReport, SWT.NONE);
										colContactCneeAddress
												.setText("CneeAddress");
										colContactCneeAddress.setWidth(84);
										colContactCneeAddress.addListener(
												SWT.Selection,
												new sortListener());
									}
									{
										colContactNoa = new TableColumn(
												tblContactReport, SWT.NONE);
										colContactNoa.setText("Noa");
										colContactNoa.setWidth(60);
										colContactNoa.addListener(
												SWT.Selection,
												new sortListener());
									}
									{
										colContactActWt = new TableColumn(
												tblContactReport, SWT.NONE);
										colContactActWt.setText("ActWt");
										colContactActWt.setWidth(60);
										colContactActWt.addListener(
												SWT.Selection,
												new sortListener());
									}
									{
										colContactArtID = new TableColumn(
												tblContactReport, SWT.NONE);
										colContactArtID.setText("ArtID");
										colContactArtID.setWidth(60);
										colContactArtID.addListener(
												SWT.Selection,
												new sortListener());
									}
									{
										colContactLrTotal = new TableColumn(
												tblContactReport, SWT.NONE);
										colContactLrTotal.setText("LrTotal");
										colContactLrTotal.setWidth(60);
										colContactLrTotal.addListener(
												SWT.Selection,
												new sortListener());
									}
									{
										colContactDDCrg = new TableColumn(
												tblContactReport, SWT.NONE);
										colContactDDCrg.setText("DDCrg");
										colContactDDCrg.setWidth(60);
										colContactDDCrg.addListener(
												SWT.Selection,
												new sortListener());
									}
									{
										colContactLrType = new TableColumn(
												tblContactReport, SWT.NONE);
										colContactLrType.setText("LrType");
										colContactLrType.setWidth(60);
										colContactLrType.addListener(
												SWT.Selection,
												new sortListener());

									}
									{
										colContactInwarddays = new TableColumn(
												tblContactReport, SWT.NONE);
										colContactInwarddays
												.setText("InwarDays");
										colContactInwarddays.setWidth(60);
										colContactInwarddays.addListener(
												SWT.Selection,
												new sortListener());
									}
									{
										colContactFrom = new TableColumn(
												tblContactReport, SWT.NONE);
										colContactFrom.setText("From");
										colContactFrom.setWidth(60);
										colContactFrom.addListener(
												SWT.Selection,
												new sortListener());
									}
									{
										colContactTo = new TableColumn(
												tblContactReport, SWT.NONE);
										colContactTo.setText("To");
										colContactTo.setWidth(60);
										colContactTo.addListener(SWT.Selection,
												new sortListener());
									}

								}
								populateBranchForDV();

							}

							{
								// Daily Delivery status

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
									tblDailyDelvStatus.setBounds(13, 65, 884,
											374);

									{
										lblDDSBranch = new Label(
												cvsDailyDelvStatus, SWT.NONE);
										lblDDSBranch.setText("Select Branch");
										lblDDSBranch.setBounds(12, 16, 67, 20);
									}
									{
										cbDDSBranch = new Combo(
												cvsDailyDelvStatus,
												SWT.READ_ONLY);
										cbDDSBranch.setBounds(85, 14, 145, 21);
									}
									{
										txtDDSDate = new Text(
												cvsDailyDelvStatus, SWT.BORDER
														| SWT.READ_ONLY);
										txtDDSDate.setBounds(233, 13, 70, 22);
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
										btnDDSdt.setBounds(305, 12, 26, 23);
										// btnGo.setFont(BUTTON_FONT);
										btnDDSdt
												.addSelectionListener(new SelectionAdapter() {
													public void widgetSelected(
															SelectionEvent e) {
														KalendarDialog cd = new KalendarDialog(
																new Shell());
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
										btnDDSgo.setBounds(334, 13, 36, 24);
										btnDDSgo.setText("Go");
										btnDDSgo
												.addSelectionListener(new SetUpAction());
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
									 *\/
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
							}*/

							tabReport.setSelection(0);
						}

					}

				}

				beanutil = BeanUtil.getInstance();

				{
					lblStation = new Label(this, SWT.NONE);
					lblStation.setText("Select Station ");
					lblStation.setBounds(345, 166, 76, 18);
				}
				{
					coStation = new Combo(this, SWT.BORDER | SWT.READ_ONLY);
					coStation.setBounds(440, 163, 187, 21);
					populateDestStationCodes();
				}
				{
					btnSetup = new Button(this, SWT.PUSH | SWT.CENTER);
					btnSetup.setText("Set");
					btnSetup.setBounds(636, 163, 67, 23);
					btnSetup.addSelectionListener(new SetUpAction());
				}
				{
					group1 = new Group(this, SWT.NONE);
					GridLayout group1Layout = new GridLayout();
					group1Layout.makeColumnsEqualWidth = true;
					group1.setLayout(group1Layout);
					group1.setText("Change Station");
					group1.setBounds(336, 95, 378, 106);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;

	}

	public class sortListener implements Listener {

		public void handleEvent(Event e) {

			int selectionIndex = tabReport.getSelectionIndex();
			String selectedTab = tabReport.getItem(selectionIndex).getText();
			TableColumn column = (TableColumn) e.widget;

			if (selectedTab.equalsIgnoreCase(CONTACT_REPORT_TAB)) {
				if (column == colContactSno) {
					TableSort.SortByFloat(0, tblContactReport);
				} else if (column == colContactLrno) {
					TableSort.sortByLrNo(1, tblContactReport);
				} else if (column == colContactLrdate) {
					TableSort.SortByDate(2, tblContactReport);
				} else if (column == colContactCnorName) {
					TableSort.sortByString(3, tblContactReport);
				} else if (column == colContactCneeName) {
					TableSort.sortByString(4, tblContactReport);
				} else if (column == colContactCneeAddress) {
					TableSort.sortByString(5, tblContactReport);
				} else if (column == colContactNoa) {
					TableSort.SortByFloat(6, tblContactReport);
				} else if (column == colContactActWt) {
					TableSort.SortByFloat(7, tblContactReport);
				} else if (column == colContactArtID) {
					TableSort.SortByFloat(8, tblContactReport);
				} else if (column == colContactLrTotal) {
					TableSort.SortByFloat(9, tblContactReport);
				} else if (column == colContactDDCrg) {
					TableSort.SortByFloat(10, tblContactReport);
				} else if (column == colContactLrType) {
					TableSort.sortByString(11, tblContactReport);
				} else if (column == colContactInwarddays) {
					TableSort.SortByFloat(12, tblContactReport);
				} else if (column == colContactFrom) {
					TableSort.sortByString(13, tblContactReport);
				} else if (column == colContactTo) {
					TableSort.sortByString(14, tblContactReport);
				}
			}

		}

	}

	/**
	 * 
	 */
	private void populateBranchForDV() {

		StationsDTO[] station = null;
		try {
			station = handler.getAllBranches();
			if (null != station) {
				int len = station.length;
				cbContactBranch.add("All");
				for (int i = 0; i < len; i++) {
					cbContactBranch.add(station[i].getName() + " - "
							+ station[i].getId());
				}

			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

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

	/**
	 * Method populate stationary values
	 * 
	 * @param branch
	 */
	private void populateStationaryValues(String branch) {
		LRNumberRangeDTO[] stationaryDTO = null;
		String branchCode = null;
		String value = null;
		int index = 0;
		try {

			stationaryDTO = handler.getStationaryValues();

			if (null != stationaryDTO) {
				if (branch == "") {
					value = cbSB.getText();
					index = value.indexOf(" - ");
					branchCode = value.substring(index + 3);
				} else {
					branchCode = branch;
				}
				int len = stationaryDTO.length;

				for (int i = 0; i < len; i++) {
					if (branchCode.equalsIgnoreCase(stationaryDTO[i]
							.getBranchCode())) {
						TableItem[] items = tblStationary.getItems();

						for (int j = 0; j < items.length; j++) {
							value = items[j].getText(1);
							index = value.indexOf(" - ");
							String stationCode = value.substring(index + 3);
							if (stationCode.equalsIgnoreCase(stationaryDTO[i]
									.getStationCode())) {

								txtTopay[j].setText(Integer
										.toString(stationaryDTO[i].getTopay()));
								txtPaid[j].setText(Integer
										.toString(stationaryDTO[i].getPaid()));
								txtBilling[j]
										.setText(Integer
												.toString(stationaryDTO[i]
														.getBilling()));
								txtCr[j].setText(Integer
										.toString(stationaryDTO[i].getCr()));
							}
						}

					}
				}

			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}

	private void populateLRTRackCount() {

		try {
			try {
				if (null != txtLRTRackDate && !txtLRTRackDate.equals("")
						&& null != txtLRTrackToDate
						&& !txtLRTrackToDate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Date date = null;
					Date to_date = null;
					date = dateFormat.parse(txtLRTRackDate.getText());
					to_date = dateFormat.parse(txtLRTrackToDate.getText());
					if (handler != null) {
						int result = handler.getLRTrackCount(date, to_date);
						lblLrTrackResult.setText(String.valueOf(result));
					}

				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Method to Populate Stationary Report Table
	 */
	private void populateStationaryReport() {
		LRNumberRangeDTO[] stationaryReportDTO = null;
		int len = 0;
		int value = 0;
		try {
			tblStationaryReport.removeAll();
			stationaryReportDTO = handler.getStationaryReport();

			if (stationaryReportDTO != null) {
				len = stationaryReportDTO.length;
				for (int i = 0; i < len; i++) {
					TableItem item = new TableItem(tblStationaryReport,
							SWT.NONE);

					item.setText(0, String.valueOf(i + 1));
					item.setText(1, stationaryReportDTO[i].getStationCode());
					value = stationaryReportDTO[i].getTopay();
					if (value == 1) {
						item.setText(2, "Assign");
						item.setForeground(2, Display.getCurrent()
								.getSystemColor(SWT.COLOR_RED));
					} else {
						item.setText(2, EMPTY_STRING);
					}
					value = stationaryReportDTO[i].getPaid();
					if (value == 1) {
						item.setText(3, "Assign");
						item.setForeground(3, Display.getCurrent()
								.getSystemColor(SWT.COLOR_RED));
					} else {
						item.setText(3, EMPTY_STRING);
					}

					value = stationaryReportDTO[i].getBilling();
					if (value == 1) {
						item.setText(4, "Assign");
						item.setForeground(4, Display.getCurrent()
								.getSystemColor(SWT.COLOR_RED));
					} else {
						item.setText(4, EMPTY_STRING);
					}

					value = stationaryReportDTO[i].getCr();
					if (value == 1) {
						item.setText(5, "Assign");
						item.setForeground(5, Display.getCurrent()
								.getSystemColor(SWT.COLOR_RED));
					} else {
						item.setText(5, EMPTY_STRING);
					}
				}
			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}

	/**
	 * Method to populate destination station codes
	 */
	private void populateDestStationCodes() {
		try {
			String stationCode = handler.getStationCode();
			StationsDTO[] station = null;
			if (stationCode != null) {
				station = handler.getAllAssociatedStations();
			}
			if (null != station) {
				int len = station.length;

				for (int i = 0; i < len; i++) {
					coStation.add(station[i].getName() + " - "
							+ station[i].getId());
				}

			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Method to populate branches
	 */
	private void populateBranches() {
		StationsDTO[] station = null;
		try {
			station = handler.getAllBranches();
			if (null != station) {
				int len = station.length;
				for (int i = 0; i < len; i++) {
					coBranch.add(station[i].getName() + " - "
							+ station[i].getId());
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Method to create Table
	 * 
	 * @param columns
	 * @param rows
	 * @param column_name
	 * @param row_data
	 */
	private void createTable(int columns, int rows, String[] column_name,
			Object[] row_data) {
		tblStationary = new Table(canvas2, SWT.MULTI | SWT.BORDER
				| SWT.FULL_SELECTION);
		tblStationary.setLinesVisible(true);
		tblStationary.setHeaderVisible(true);

		for (int i = 0; i < columns; i++) {
			column = new TableColumn(tblStationary, SWT.NONE);

			if (i == 0) {
				column.setText(column_name[i]);
				column.setWidth(50);
			} else if (i == 1) {
				column.setText(column_name[i]);
				column.setWidth(230);
			} else {
				column.setText(column_name[i]);
				column.setWidth(100);
			}
		}

		editor1 = new TableEditor[rows];
		txtTopay = new Text[rows];

		editor2 = new TableEditor[rows];
		txtPaid = new Text[rows];

		editor3 = new TableEditor[rows];
		txtBilling = new Text[rows];

		editor4 = new TableEditor[rows];
		txtCr = new Text[rows];

		// Drawing initial table items
		for (int rowId = 0; rowId < rows; rowId++) {

			item = new TableItem(tblStationary, SWT.NONE);

			item.setText(0, Integer.toString((rowId + 1)));

			// First Column station
			item.setText(1, (((StationsDTO) (row_data[rowId])).getName()
					+ " - " + ((StationsDTO) row_data[rowId]).getId()));

			// Draw Text Field
			editor1[rowId] = new TableEditor(tblStationary);
			txtTopay[rowId] = new Text(tblStationary, SWT.NONE);
			txtTopay[rowId].addVerifyListener(new NumericValidation());
			editor1[rowId].grabHorizontal = true;
			editor1[rowId].setEditor(txtTopay[rowId], item, 2);

			// Draw Text Field
			editor2[rowId] = new TableEditor(tblStationary);
			txtPaid[rowId] = new Text(tblStationary, SWT.NONE);
			txtPaid[rowId].addVerifyListener(new NumericValidation());
			editor2[rowId].grabHorizontal = true;
			editor2[rowId].setEditor(txtPaid[rowId], item, 3);

			// Draw Text Field
			editor3[rowId] = new TableEditor(tblStationary);
			txtBilling[rowId] = new Text(tblStationary, SWT.NONE);
			txtBilling[rowId].addVerifyListener(new NumericValidation());
			editor3[rowId].grabHorizontal = true;
			editor3[rowId].setEditor(txtBilling[rowId], item, 4);

			// Draw Text Field
			editor4[rowId] = new TableEditor(tblStationary);
			txtCr[rowId] = new Text(tblStationary, SWT.NONE);
			txtCr[rowId].addVerifyListener(new NumericValidation());
			editor4[rowId].grabHorizontal = true;
			editor4[rowId].setEditor(txtCr[rowId], item, 5);

		}
		tblStationary.setBounds(32, 43, 750, 380);

	}

	/**
	 * Method to populate selected Branches
	 */
	private void populateSelectedBranches() {
		try {
			StationsDTO[] stations = handler.getAllBranches();
			if (null != stations) {
				int len = stations.length;
				for (int i = 0; i < len; i++) {
					cbSB.add(stations[i].getName() + " - "
							+ stations[i].getId());
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * 
	 * @param reftable
	 * @param branch
	 */
	private void handleItemChangeAction(Table reftable, String branch) {

		int index = cbSB.getSelectionIndex();
		String name = null;

		if (index > -1) {
			name = cbSB.getItem(index);

		} else {

			name = cbSB.getText();
			if (name.trim().length() == 0)
				name = null;
		}
		try {
			if (null != name) {
				reftable.dispose();
				StationsDTO rowDTO[] = null;
				rowDTO = handler.getStations(branch);
				int rows = rowDTO.length;
				Object[] rowData = rowDTO;
				createTable(6, rows, column_head, rowData);
				// createTableEditor();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Listener class for Admincomposite class
	 * 
	 * @author
	 * 
	 */
	public class SetUpAction implements SelectionListener,
			org.eclipse.swt.events.FocusListener {

		public void widgetDefaultSelected(SelectionEvent arg0) {

		}

		public void widgetSelected(SelectionEvent event) {
			Object source = event.getSource();
			String stationName = null;
			String stationCode = null;
			String value = null;

			if (btnNew == source) {
				Shell shell = new Shell(SWT.TOP);
				manager = new ContainerManager();
				Composite composite;
				try {
					composite = manager.addMonitorReportsContainer(shell);
					composite.setBounds(10, 10, 1000, 690);
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else if (source == btnAll) {
				if (tblStationaryReport != null) {
					TableItem[] items = tblStationaryReport.getItems();

					boolean isChecked = btnAll.getSelection();
					for (int i = 0; i < items.length; i++) {
						items[i].setChecked(isChecked);
					}
				}
			}

			else if (source == btnAssignStationary) {
				boolean flag = false;
				if (null != tblStationaryReport) {
					LRNumberRangeDTO[] dto = getSelectedStationary();
					if (null != dto) {
						try {
							flag = handler.assignStationary(dto);
							if (flag) {
								displayError("Stationary Assigned Successfully");
								populateStationaryReport();
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				}
			} else if (btnSetup == source) {

				if (coStation.getText() != "") {

					properties = new Properties();
					value = coStation.getText();
					int index = value.indexOf(" - ");
					stationName = value.substring(0, index);
					stationCode = value.substring(index + 3);

					BeanUtil bean;
					try {
						bean = BeanUtil.getInstance();
						bean.setStationName(stationName);
						bean.setStationCode(stationCode);
						displayError("Successfully Changed to " + stationName
								+ " Station!");
						// String shellText = shell.getText();

						String currYear = BeanUtil.getDbYear();
						if(currYear.equals("")){
							currYear = "2010-11";
						}
						
						shell.setText("AKR Parcel Service" + " | "
								+ beanutil.getActualStationName() + " | "
								+ beanutil.getActualStationCode() + " ("
								+ stationCode + ") | " + currYear + 
								"                                 "+
								"                                  "+
								"                                  "+
								
								 "Version - "+MainForm.VERSION_NO);
						if (beanutil.getActualStationCode().equalsIgnoreCase(
								stationCode)) {
							beanutil.setDiffStation(false);
						} else {
							beanutil.setDiffStation(true);
						}
						bean.getLRRange(true);

						System.out.println("Getting All Quotation Customers");
						beanutil.getCustomers(stationCode, true);
						System.out
								.println("Getting All Quotation Customers Successfull");

						System.out.println("Getting Sundry Customer details");						
						beanutil.populateCustomersFromRemote(true);
						System.out
								.println("Getting Sundry Customer details Successfull");

						System.out.println("Getting Sundry details");
						beanutil.getSundryDetails(stationCode, true);
						System.out.println("Got  Sundry details");

						System.out.println("Getting Distance details");
						beanutil.getDistanceList(stationCode, true);
						System.out.println("Got  Distance List");

					} catch (NamingException ne) {
						displayError("Not Changed");
						ne.printStackTrace();
					} catch (Exception exception) {
						displayError("Not Changed");
						exception.printStackTrace();
					}
				}

			} else if (coBranch == source) {

				StationsDTO[] station = null;
				try {

					value = coBranch.getText();
					int index = value.indexOf(" - ");
					stationCode = value.substring(index + 3);

					if (stationCode != null) {
						station = handler.getStations(stationCode);
					}
					if (null != station) {

						int len = station.length;
						coStation.removeAll();
						for (int i = 0; i < len; i++) {
							coStation.add(station[i].getName() + " - "
									+ station[i].getId());
						}
					}
				} catch (Exception exception) {

				}

			} else if (cbSB == source) {
				try {
					String comboValue = cbSB.getText();
					int index = comboValue.indexOf(" - ");
					String branchCode = comboValue.substring(index + 3);
					handleItemChangeAction(tblStationary, branchCode);
					populateStationaryValues(branchCode);
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			} else if (btnUpdate == source) {
				try {
					String comboValue = cbSB.getText();
					int ind = comboValue.indexOf(" - ");
					String branchCode = comboValue.substring(ind + 3);

					int len = tblStationary.getItemCount();
					LRNumberRangeDTO lrFormat = null;
					ArrayList<LRNumberRangeDTO> list = new ArrayList<LRNumberRangeDTO>();
					LRNumberRangeDTO[] lrArray = null;
					TableItem[] items = tblStationary.getItems();
					for (int i = 0; i < len; i++) {
						if (!txtTopay[i].getText().equals("")
								|| !txtPaid[i].getText().equals("")
								|| !txtBilling[i].getText().equals("")
								|| !txtCr[i].getText().equals("")) {

							String stationValue = items[i].getText(1);
							int index = stationValue.indexOf(" - ");
							stationName = stationValue.substring(0, index);
							stationCode = stationValue.substring(index + 3);

							lrFormat = new LRNumberRangeDTO();
							lrFormat.setBranchCode(branchCode);
							lrFormat.setStationCode(stationCode);
							if (txtTopay[i].getText() != "")
								lrFormat.setTopay(Integer.parseInt(txtTopay[i]
										.getText()));
							if (txtPaid[i].getText() != "")
								lrFormat.setPaid(Integer.parseInt(txtPaid[i]
										.getText()));
							if (txtBilling[i].getText() != "")
								lrFormat.setBilling(Integer
										.parseInt(txtBilling[i].getText()));
							if (txtCr[i].getText() != "")
								lrFormat.setCr(Integer.parseInt(txtCr[i]
										.getText()));

							list.add(lrFormat);
						}
					}

					int size = list.size();
					if (size > 0) {
						lrArray = (LRNumberRangeDTO[]) list
								.toArray(new LRNumberRangeDTO[size]);
					}

					boolean result = handler.setStationaryValues(lrArray);

					if (result) {
						displayError("Stationary Settings Updated");
					} else {
						displayError("Please Enter Values");
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (btnAssign == source) {
				int[] types = new int[4];

				if (chkTopay.getSelection()) {
					types[0] = 1;
				} else {
					types[0] = 0;
				}

				if (chkPaid.getSelection()) {
					types[1] = 1;
				} else {
					types[1] = 0;
				}

				if (chkBilling.getSelection()) {
					types[2] = 1;
				} else {
					types[2] = 0;
				}

				if (chkCR.getSelection()) {
					types[3] = 1;
				} else {
					types[3] = 0;
				}

				boolean flagselection = false;
				TreeComposite tComposite;
				try {
					boolean assign = false;
					tComposite = new TreeComposite(canvas3.getParent(),
							SWT.NONE, 300);
					String[] stations = tComposite.getSelectedMSBStation();
					if (stations != null) {
						int len = stations.length;
						if (chkTopay.getSelection() || chkPaid.getSelection()
								|| chkBilling.getSelection()
								|| chkCR.getSelection()) {
							flagselection = true;
						}
						if (len > 0 && flagselection) {
							assign = handler.assignStationary(stations, types);
							if (assign) {
								displayError("Stationary Settings Assigned");
							} else {
								displayError("Stationary Settings Not Updated");
							}

							chkTopay.setSelection(false);
							chkPaid.setSelection(false);
							chkBilling.setSelection(false);
							chkCR.setSelection(false);
							// Clear Tree Items
							tComposite.clearTree();

							cptTree1.dispose();
							cptTree1 = new TreeComposite(canvas3, SWT.NONE, 300)
									.loadComposite();
							cptTree1.setBounds(50, 50, 250, 350);

						} else {
							displayError("Select Stations & Options");
						}

					}
				} catch (Exception e) {
					displayError("Stationary Settings Not Updated.");
					e.printStackTrace();
				}
			} else if (btnSetAlarm == source) {

				TreeComposite tc;
				boolean isSet = false;
				int topay = 0;
				int paid = 0;
				int billing = 0;
				int cr = 0;

				try {
					tc = new TreeComposite(canvas4.getParent(), SWT.NONE, 300);
					String[] stations = tc.getSelectedMSBStation();
					if (stations != null) {
						int len = stations.length;
						if (len > 0) {
							if (!txtTopayAlarm.getText().equals(EMPTY_STRING))
								topay = Integer.parseInt(txtTopayAlarm
										.getText());

							if (!txtPaidAlarm.getText().equals(EMPTY_STRING))
								paid = Integer.parseInt(txtPaidAlarm.getText());

							if (!txtBillingAlarm.getText().equals(EMPTY_STRING))
								billing = Integer.parseInt(txtBillingAlarm
										.getText());

							if (!txtCRAlarm.getText().equals(EMPTY_STRING))
								cr = Integer.parseInt(txtCRAlarm.getText());

							if (topay != 0 || paid != 0 || billing != 0
									|| cr != 0) {

								isSet = handler.setAlarmSettings(stations,
										topay, paid, billing, cr);

								if (isSet) {
									displayError("Alarm Settings Done");
								} else {
									displayError("Alarm can not be Set");
								}

								txtTopayAlarm.setText(EMPTY_STRING);
								txtPaidAlarm.setText(EMPTY_STRING);
								txtBillingAlarm.setText(EMPTY_STRING);
								txtCRAlarm.setText(EMPTY_STRING);

								// Clear Tree Items
								tc.clearTree();

							} else {
								displayError("Enter Alarm Settings");
							}
						} else {
							displayError("Select Stations");
						}

					}
				} catch (Exception exception) {
					exception.printStackTrace();

				}

			} else if (tabReport == source) {
				String selectedTab = tabReport.getSelection()[0].getText();
				if (STATIONARY_REPORT_TAB.equals(selectedTab)) {
					// Populate Stationary Table
					populateStationaryReport();

				} else if (selectedTab.equalsIgnoreCase("LRTrackCount")) {
					// populateLRTRackCount();
				}

			} else if (btnCFTSet == source) {
				try {
					String inch = txtCFTInch.getText();
					String cm = txtCm.getText();
					String ft = txtCFTFt.getText();

					if (inch.equalsIgnoreCase(EMPTY_STRING)
							|| cm.equalsIgnoreCase(EMPTY_STRING)
							|| ft.equalsIgnoreCase(EMPTY_STRING)) {
						displayError("Parameters Are Missing");

					} else {
						float cm_v = Float.parseFloat(cm);
						float feet = Float.parseFloat(ft);
						float inch_v = Float.parseFloat(inch);

						if (null != handler) {
							handler.setUnitVlaues(inch_v, feet, cm_v);
							displayError("CFT Set Successfully");
						}

					}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (btnLRTrack == source) {
				KalendarDialog cd = new KalendarDialog(shell);
				Object obj = cd.open();
				if (obj != null) {
					boolean isTrue = isValidDate(obj);
					if (isTrue)
						txtLRTRackDate.setText(obj.toString());
				}

			} else if (btnLRTRackToDate == source) {
				KalendarDialog cd = new KalendarDialog(shell);
				Object obj = cd.open();
				if (obj != null) {
					boolean isTrue = isValidDate(obj);
					if (isTrue)
						txtLRTrackToDate.setText(obj.toString());
				}

			}

			else if (btnLRTrackView == source) {

				populateLRTRackCount();
			}

			else if (cbContactBranch == source) {

				StationsDTO[] station = null;
				try {

					value = cbContactBranch.getText();
					if (!value.equalsIgnoreCase("All")) {
						int index = value.indexOf(" - ");
						stationCode = value.substring(index + 3);

						if (stationCode != null) {
							station = handler.getStations(stationCode);
						}
						if (null != station) {

							int len = station.length;
							cbDVSelectstation.removeAll();
							cbDVSelectstation.add("All");
							for (int i = 0; i < len; i++) {
								cbDVSelectstation.add(station[i].getName()
										+ " - " + station[i].getId());
							}

						}
					} else {
						populateAllStationsForDV();
					}
				} catch (Exception exception) {

				}

			} else if (btnDVSubmit == source) {
				try {
					DeliveryVerificationDTO[] dto = getVerificationReport();
					populateVerificationReport(dto);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (btnDDSgo == source) {
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
				return handler.getDailyDeliveryStatus(date,branch);
			} catch (Exception exception) {

			}
			return null;
		}

		/**
		 * @throws Exception
		 * @throws RemoteException
		 * 
		 */
		private DeliveryVerificationDTO[] getVerificationReport() {
			try {
				String branch = cbContactBranch.getText();
				String station = cbDVSelectstation.getText();
				String lr_type = cbDVLrType.getText();
				String delivery_type = cbDVDeliveryType.getText();
				String inward_days = txtDVInwardDays.getText();
				String amount = txtDVAmount.getText();

				int index = branch.indexOf("-");
				if (index > -1) {
					branch = branch.substring(index + 1).trim();
				}
				index = station.indexOf("-");
				if (index > -1) {
					station = station.substring(index + 1).trim();
				}

				String[] details = { branch, station, lr_type, delivery_type,
						inward_days, amount };
				return handler.getVerificationReport(details);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
			return null;
		}

		/**
		 * 
		 * @param dto
		 */
		private void populateVerificationReport(DeliveryVerificationDTO[] dto)
				throws Exception {
			if (dto != null) {
				int len = dto.length;

				if (tblContactReport != null) {
					tblContactReport.removeAll();
					Date today = user_formate.parse(beanutil.getServerDate());
					for (int i = 0; i < len; i++) {

						TableItem item = new TableItem(tblContactReport,
								SWT.NONE);

						item.setText(0, String.valueOf(i + 1));
						item.setText(1, dto[i].getLr_no());
						item.setText(2, getStringFromDate(dto[i].getLr_date()));
						item.setText(3, dto[i].getCnor_name());
						item.setText(4, dto[i].getCnee_name());
						item.setText(5, dto[i].getCnee_address());
						item.setText(6, getStringFromInt(dto[i].getNoa()));
						item.setText(7, getStringFromFloat(dto[i]
								.getActual_wt()));
						item.setText(8, dto[i].getArt_id());
						item.setText(9,
								getStringFromFloat(dto[i].getLr_total()));
						item.setText(10, getStringFromFloat(dto[i].getDdc()));
						item.setText(11, dto[i].getLr_type());

						if (null != dto[i].getOutward_date()) {
							long diff = today.getTime()
									- dto[i].getOutward_date().getTime();
							float days = (diff / (1000 * 60 * 60 * 24));

							item.setText(12, getStringFromFloat(days));
						}

						item.setText(13, dto[i].getFrom());
						item.setText(14, dto[i].getTo());

					}
				}
			} else {
				if (tblContactReport != null) {
					tblContactReport.removeAll();
				}
				displayError("No Records found");
			}

		}

		/***********************************************************************
		 * 
		 * @param date
		 * @return
		 */
		private String getStringFromDate(Date date_date) {
			String date_string = user_formate.format(date_date);
			return date_string;
		}

		/**
		 * 
		 * @param date_date
		 * @return
		 */
		private String getStringFromFloat(Float float_float) {
			String float_string = String.valueOf(float_float);
			return float_string;
		}

		private String getStringFromInt(int int_int) {
			String int_string = String.valueOf(int_int);
			return int_string;
		}

		/**
		 * 
		 */
		private void populateAllStationsForDV() {

			try {
				String stationCode = handler.getStationCode();
				StationsDTO[] station = null;
				if (stationCode != null) {
					station = handler.getAllStations();
				}
				if (null != station) {
					int len = station.length;
					cbDVSelectstation.removeAll();
					cbDVSelectstation.add("All");
					for (int i = 0; i < len; i++) {
						cbDVSelectstation.add(station[i].getName() + " - "
								+ station[i].getId());
					}

				}
			} catch (Exception exception) {
				exception.printStackTrace();
			}

		}

		/**
		 * @param obj
		 * 
		 */
		private boolean isValidDate(Object obj) {

			try {

				SimpleDateFormat form = new SimpleDateFormat("dd-MM-yyyy");
				Date date = form.parse(obj.toString());
				if (date.after(form.parse(SERVER_DATE))) {
					displayError("Date exceeds today's Date,Please select another Date");
					return false;
				} else if (date.before(form.parse("25-09-2009"))) {
					displayError("Date is Wrong,Please select another Date");
					return false;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return true;
		}

		private LRNumberRangeDTO[] getSelectedStationary() {
			String ASSIGN = "Assign";
			TableItem[] items = tblStationaryReport.getItems();
			LRNumberRangeDTO dto = null;
			ArrayList<LRNumberRangeDTO> list = new ArrayList<LRNumberRangeDTO>();
			int len = items.length;
			for (int i = 0; i < len; i++) {
				String check = null;
				int chck = 0;
				if (items[i].getChecked()) {
					dto = new LRNumberRangeDTO();
					dto.setStationCode(items[i].getText(1));
					check = items[i].getText(2);
					chck = check.equals(ASSIGN) ? 1 : 0;
					dto.setTopay(chck);
					check = items[i].getText(3);
					chck = check.equals(ASSIGN) ? 1 : 0;
					dto.setPaid(chck);
					check = items[i].getText(4);
					chck = check.equals(ASSIGN) ? 1 : 0;
					dto.setBilling(chck);
					check = items[i].getText(5);
					chck = check.equals(ASSIGN) ? 1 : 0;
					dto.setCr(chck);

					list.add(dto);
				}
			}
			len = list.size();
			if (len > 0)
				return list.toArray(new LRNumberRangeDTO[len]);
			return null;
		}

		public void focusGained(FocusEvent arg0) {
		}

		public void focusLost(FocusEvent arg0) {
		}

	}

	/**
	 * Method to display user Messages
	 * 
	 * @param errorText
	 */
	private void displayError(String errorText) {
		MessageBox messageBox = new MessageBox(shell, SWT.OK);
		messageBox.setMessage(errorText);
		messageBox.setText("Alert");
		messageBox.open();
	}

}