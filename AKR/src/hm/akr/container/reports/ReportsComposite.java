package hm.akr.container.reports;

import hm.akr.common.BeanUtil;
import hm.akr.common.KalendarDialog;
import hm.akr.common.MonthDialog;
import hm.akr.common.SWTResourceManager;
import hm.akr.container.admin.AdminCompositeHandler;
import hm.akr.container.commission.CommissionCompositeHandler;
import hm.akr.container.commission.CommissionComposite.sortListener;
import hm.akr.container.history.HistoryHandler;
import hm.akr.dto.ArticleDTO;
import hm.akr.dto.BookingDTO;
import hm.akr.dto.CurrentStatusDTO;
import hm.akr.dto.DDRDTO;
import hm.akr.dto.DistanceListDTO;
import hm.akr.dto.GMRInTimeDTO;
import hm.akr.dto.GMROutTimeDTO;
import hm.akr.dto.GMRReportDTO;
import hm.akr.dto.RefundRecoveryDTO;
import hm.akr.dto.SODDTO;
import hm.akr.dto.OutstandingDTO;
import hm.akr.dto.StationsDTO;
import hm.akr.dto.VersionDTO;
import hm.akr.exceptions.BusinessException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.text.Collator;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Locale;
import java.util.TreeSet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
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
 * LRComposite class
 * 
 * @version 1.0
 */

public class ReportsComposite extends Composite {

	{
		// Register as a resource user - SWTResourceManager will
		// handle the obtaining and disposing of resources
		SWTResourceManager.registerResourceUser(this);
	}

	private ReportsCompositeHandler handler = null;

	private TabFolder reportsTabFolder;
	
	//Inward Register
	private TabItem tiInwardRegister;
	private Table tblInwardRegister;	
	private TableColumn colSentDate;

	private TableColumn colTotalFreight;

	private TableColumn colFrom;

	private TableColumn colArrivalVehicle;
	
	private TableColumn colConsignee;

	private TableColumn colSignature;
	
	private TableColumn colCrgWeight;
	
	private Canvas canIR;
	
	private GMROutTimeDTO[] irDto = null;
	
	private String irST; 
	
	private String selectedDate = null;
	
	/** Statement of Booking * */
	private TabItem tiSOB;

	private Table tblSob;

	private Canvas canSob;

	private TableColumn colDdrLrType;

	

	private TableColumn colDdrDate;

	private TableColumn colDdrCrno;

	private TableColumn colDdrOther;

	private TableColumn colDdrDdExtra;

	private TableColumn colDdrDd;

	private TableColumn colDdrFreight;

	private TableColumn colDdrWeight;

	private TableColumn colDdrArticles;

	private TableColumn colDdrFrom;

	private Display display;

	private TableColumn colDdrLrDate;

	DistanceListDTO[] distanceDTO = null;

	private TableColumn colDdrLrno;

	
	private Text txtSta;

	private Label lblSta;

	private Table tblGmr;

	private Label lblName;

	private TableColumn colDdrSno;

	private Table tblddr;

	private Canvas canDDR;

	/** Columns for table SOB * */
	private TableColumn colSodDD;

	private TableColumn colSodTotalAmt;

	private TableColumn colSNo;

	private TableColumn colDate;

	private TableColumn colLRType;

	private TableColumn colNoOfArticles;

	private TableColumn colTo;

	private TableColumn colBft;

	private TableColumn colCC;

	private TableColumn colOtherCharges;

	private Button btnGo;

	private Text txtDate;

	private Label label3;

	private TableColumn ColDd;

	private TableColumn colTotal;

	private TableColumn colLr;

	private TableColumn colActualWeight;

	private TableColumn colSodWeight;

	private BookingDTO[] bookings = null;

	String[] trips = null;

	/** ********************************* */

	/** * Statement of Delivery ** */
	private TabItem tiSOD;

	private Table tblSod;

	private Canvas canSod;

	/** Columns for Table SOD * */
	private TableColumn colSodSNo;

	private TableColumn colSodDate;

	private TableColumn colSodNoOfArticles;

	private TableColumn colSodLRType;

	private TableColumn colSodFrom;

	private TableColumn colSodLrNo;

	private TableColumn colSodDemurrage;

	private TableColumn colSodUnderCharge;

	private TableColumn colSodPostage;

	private TableColumn colSodDDExtra;

	private TableColumn colSodOthers;

	private TableColumn colSodCRNo;

	private TableColumn colSodBookingDays;

	private TableColumn colSodInwardDays;

	private TabItem tiDDR;

	private TableColumn colSodCRAmt;

	// private Button btnGenerateSOD;

	private SODDTO[] deliveries = null;

	private DDRDTO[] door = null;

	private boolean firsttab = true;

	BookingDTO[] outstandingdto = null;

	int temp;

	private Shell shell;
	private Button btnCurrentGo;
	private Label lblCurrentStatus;
	private Combo cbCurrentStatus;
	private TableColumn colAgentCneeName;
	private TableColumn colAgentCnorName;
	private TableColumn colAgentTotal;
	private TableColumn colAgentDDC;
	private TableColumn colAgentWeight;
	private TableColumn colAgentNoa;
	private TableColumn colAgentCurrentStatus;
	private TableColumn colAgentBookingdays;
	private TableColumn colAgentTo;
	private TableColumn colAgentFrom;
	private TableColumn colAgentLrDate;
	private TableColumn colAgentLrno;
	private TableColumn colAgentSno;
	private Table tblAgentReport;
	private TabItem tiAgent;
	private TableColumn tblcolstockConsignee;
	private TableColumn tblcolstockconsignor;
	private TableColumn tblcolstockvehicle;
	private TableColumn tblcolstocksent;
	private TableColumn tblcolstockinward;
	private TableColumn tblcolstockarticletype;
	private TableColumn tblcolstockarticlevalue;
	private TableColumn tblcolstockfreight;
	private TableColumn tblcolstockactual;
	private TableColumn tblcolstocknoa;
	private TableColumn tblcolfrom;
	private TableColumn tblcollrtype;
	private TableColumn tblcolstocklrdate;
	private TableColumn tblstocklrno;
	private TableColumn colstocksno;
	private Table tblstockreport;
	

	private Combo comVehicleNo;

	private String EMPTY_STRING = "";

	private String DATE_FORMAT = "dd-MM-yyyy";

	private String SERVER_DATE = null;

	private String sobST = null;

	private String sodST = null;

	private String soddrST = null;

	private TableColumn colIEC;

	private float totaltopay = 0;
	private float totalpaid = 0;
	private float totalbillng = 0;

	private TableColumn colTotalFrt;

	private TabItem outstandingtab;

	private Canvas canoutstanding;

	private Table tbloutstanding;

	private TableColumn tableColumn2;

	private TableColumn coloutbranch;

	private TableColumn colStationName2;

	private TableColumn colcode;

	private TableColumn colpbdd;

	private TableColumn coltpod;

	private TableColumn colpdbooking;

	private TableColumn coltotal;

	BeanUtil beanutil = null;

	private Combo cbbranch;

	private Button btnoutstanding;

	private TableColumn tblcolstockddc;

	private Canvas canAgentReport;

	private DecimalFormat decimalFormat;

	private Button btnprint;
	
	private Button btnExportXL;
	
	private Button btnExportPDF;

	private Text txtToDate;

	private Button btnRepToDate;

	private Button btnRepDate;

	private Label label4;

	//RR
	private TabItem tiRecovery;
	private TabItem tiRefund;
	private Table tblRecovery;
	private Table tblRefund;
	private TableColumn colRecInst;
	private TableColumn colRefInst;
	private TableColumn colRecDate;
	private TableColumn colRefDate;
	private TableColumn colRecDesc;
	private TableColumn colRecAmount;
	private TableColumn colRecContact;

	private TableColumn colRefSNo;

	private TableColumn colRefDesc;

	private TableColumn colRefContact;

	private TableColumn colRefAmount;

	private TableColumn colRecSNo;

	private Group gpMonth;

	private Text txtMonth;

	private Button btnMonth;
	private static final String REFUND_TAB = "Refund";
	private static final String RECOVERY_TAB = "Recovery";
	
	
	private CommissionCompositeHandler comnHandler = null;
	SimpleDateFormat viewdateformat = null;
	RefundRecoveryDTO[] RecoveryDTO = null;
	RefundRecoveryDTO[] RefundDTO = null;

	private Text txtMonthRef;

	private Button btnMonthRef;
	
	VersionDTO[] dto = null;
	
	HistoryHandler historyH = null;
	/**
	 * Constructor method
	 * 
	 * @param shell
	 * @param swtValue
	 */
	public ReportsComposite(Shell shell, int swtValue) {
		super(shell, swtValue);
		this.shell = shell;
		try {
			handler = new ReportsCompositeHandler();
			comnHandler = new CommissionCompositeHandler();
			SERVER_DATE = handler.getServerDate();
			beanutil = BeanUtil.getInstance();
			decimalFormat = new DecimalFormat("0.00");
			viewdateformat = new SimpleDateFormat("dd-MM-yyyy");
			historyH = new HistoryHandler();
			dto = historyH.getHistoryYears();
			if(dto != null && dto.length > 0){
				BeanUtil.setThree_month_updated(dto[0].getUpdated_date());
			}
		} catch (Exception e) {
				e.printStackTrace();
		}
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

	/**
	 * Method to Report Composite
	 * 
	 * @return
	 */
	public Composite loadComposite() {

		try {
			this.setLayout(new FormLayout());
			this.setSize(1032, 717);
			

			{
				lblSta = new Label(this, SWT.NONE);
				FormData lblStaLData = new FormData();
				lblStaLData.width = 70;
				lblStaLData.height = 13;
				lblStaLData.left = new FormAttachment(0, 1000, 400);
				lblStaLData.top = new FormAttachment(0, 1000, 104);
				lblSta.setLayoutData(lblStaLData);
				lblSta.setText("Statement No.");
			}
			{
				FormData txtStaLData = new FormData();
				txtStaLData.width = 155;
				txtStaLData.height = 18;
				txtStaLData.left = new FormAttachment(0, 1000, 475);
				txtStaLData.top = new FormAttachment(0, 1000, 100);
				txtSta = new Text(this, SWT.BORDER | SWT.READ_ONLY);
				txtSta.setLayoutData(txtStaLData);
				txtSta.setFont(SWTResourceManager.getFont("Tahoma", 9, 1,
						false, false));
			}			
			
			{
				lblName = new Label(this, SWT.RIGHT);
				FormData lblNameLData = new FormData();
				lblNameLData.width = 372;
				lblNameLData.height = 47;
				lblNameLData.left = new FormAttachment(0, 1000, 632);
				lblNameLData.top = new FormAttachment(0, 1000, 20);
				lblName.setLayoutData(lblNameLData);
				lblName.setFont(SWTResourceManager.getFont("Tahoma", 20, 1,
						false, false));
				lblName.setText("STATEMENT OF BOOKING");
			}

				
			
			{
				btnprint = new Button(this, SWT.PUSH | SWT.CENTER);
				btnprint.setText("Print");
				FormData btnprintLData = new FormData();
				btnprintLData.width = 60;
				btnprintLData.height = 33;
				btnprintLData.left = new FormAttachment(865, 1000, 0);
				btnprintLData.right = new FormAttachment(925, 1000, 0);
				btnprintLData.top = new FormAttachment(939, 1000, 0);
				btnprintLData.bottom = new FormAttachment(972, 1000, 0);
				btnprint.setLayoutData(btnprintLData);
				//btnprint.setBounds(865, 670, 60, 22);
				btnprint.setEnabled(false);
				btnprint.addSelectionListener(new ReportingListener());

			}
			
			{
				
				btnExportXL = new Button(this, SWT.PUSH | SWT.CENTER);
				btnExportXL.setText("Export Excel");
				FormData btnExportXLData = new FormData();
				btnExportXLData.width = 80;
				btnExportXLData.height = 33;
				btnExportXLData.left = new FormAttachment(765, 1000, 0);
				btnExportXLData.right = new FormAttachment(845, 1000, 0);
				btnExportXLData.top = new FormAttachment(939, 1000, 0);
				btnExportXLData.bottom = new FormAttachment(972, 1000, 0);
				btnExportXL.setLayoutData(btnExportXLData);
				btnExportXL.setEnabled(false);
				btnExportXL.addSelectionListener(new ReportingListener());

				
			}
			
			{
				btnExportPDF = new Button(this, SWT.PUSH | SWT.CENTER);
				btnExportPDF.setText("Export PDF");
				FormData btnExportPDFData = new FormData();
				btnExportPDFData.width = 80;
				btnExportPDFData.height = 33;
				btnExportPDFData.left = new FormAttachment(665, 1000, 0);
				btnExportPDFData.right = new FormAttachment(745, 1000, 0);
				btnExportPDFData.top = new FormAttachment(939, 1000, 0);
				btnExportPDFData.bottom = new FormAttachment(972, 1000, 0);
				btnExportPDF.setLayoutData(btnExportPDFData);
				btnExportPDF.setEnabled(false);
				btnExportPDF.addSelectionListener(new ReportingListener());

				
			}

			
			{
				label3 = new Label(this, SWT.NONE);
				label3.setText("Date");
				FormData label3LData = new FormData();
				label3LData.width = 29;
				label3LData.height = 18;
				label3LData.left = new FormAttachment(0, 1000, 640);
				label3LData.top = new FormAttachment(0, 1000, 104);
				label3.setLayoutData(label3LData);
			}
			
			{
				txtDate = new Text(this, SWT.BORDER | SWT.READ_ONLY);
				FormData txtDateLData = new FormData();
				txtDateLData.width = 60;
				txtDateLData.height = 17;
				txtDateLData.left = new FormAttachment(0, 1000, 670);
				txtDateLData.top = new FormAttachment(0, 1000, 100);
				txtDate.setLayoutData(txtDateLData);
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
				btnRepDate = new Button(this, SWT.PUSH | SWT.CENTER);				
				FormData txtDateLData = new FormData();
				txtDateLData.width = 31;
				txtDateLData.height = 23;
				txtDateLData.left = new FormAttachment(0, 1000, 743);
				txtDateLData.top = new FormAttachment(0, 1000, 100);
				btnRepDate.setLayoutData(txtDateLData);
				btnRepDate.setImage(SWTResourceManager
						.getImage("hm/akr/resources/Calendar.jpg"));
				btnRepDate.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						KalendarDialog cd = new KalendarDialog(shell);
						Object obj = cd.open();
						if (obj != null) {
							String date = obj.toString();
							txtDate.setText(date);
						}
					}
				});
			}
						
			{
				{
					label4 = new Label(this, SWT.NONE);
					label4.setText("To Date");
					FormData label3LData = new FormData();
					label3LData.width = 38;
					label3LData.height = 18;
					label3LData.left = new FormAttachment(0, 1000, 785);
					label3LData.top = new FormAttachment(0, 1000, 104);
					label4.setLayoutData(label3LData);
				}
				
				{
					txtToDate = new Text(this, SWT.BORDER | SWT.READ_ONLY);
					FormData txtDateLData = new FormData();
					txtDateLData.width = 60;
					txtDateLData.height = 17;
					txtDateLData.left = new FormAttachment(0, 1000, 830);
					txtDateLData.top = new FormAttachment(0, 1000, 100);
					txtToDate.setLayoutData(txtDateLData);
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);

					if (SERVER_DATE != null) {
						txtToDate.setText(SERVER_DATE);
					} else {
						txtToDate.setText(date);
					}
				}
				{
					btnRepToDate = new Button(this, SWT.PUSH | SWT.CENTER);				
					FormData txtDateLData = new FormData();
					txtDateLData.width = 31;
					txtDateLData.height = 23;
					txtDateLData.left = new FormAttachment(0, 1000, 903);
					txtDateLData.top = new FormAttachment(0, 1000, 100);
					btnRepToDate.setLayoutData(txtDateLData);
					btnRepToDate.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btnRepToDate.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent e) {
							KalendarDialog cd = new KalendarDialog(shell);
							Object obj = cd.open();
							if (obj != null) {
								String date = obj.toString();
								txtToDate.setText(date);
							}
						}
					});
				}
								
			}
			
			{
				btnGo = new Button(this, SWT.PUSH | SWT.CENTER);
				btnGo.setText("Go");
				FormData butDateLData = new FormData();
				butDateLData.width = 37;
				butDateLData.height = 25;
				butDateLData.left = new FormAttachment(0, 1000, 915);
				butDateLData.top = new FormAttachment(0, 1000, 99);
				butDateLData.right = new FormAttachment(0, 1000,947);
				butDateLData.bottom = new FormAttachment(0, 1000, 124);
				btnGo.setLayoutData(butDateLData);
				btnGo.addSelectionListener(new ReportingListener());

				label4.setVisible(false);
				txtToDate.setVisible(false);
				btnRepToDate.setVisible(false);
				
			}			
			
			
			{
				reportsTabFolder = new TabFolder(this, SWT.NONE);

				FormData tabFolderLData = new FormData();
				tabFolderLData.width = 973;
				tabFolderLData.height = 603;
				tabFolderLData.left = new FormAttachment(0, 1000, 48);
				tabFolderLData.top = new FormAttachment(0, 1000, 129);
				tabFolderLData.bottom = new FormAttachment(1000, 1000, -5);
				tabFolderLData.right = new FormAttachment(1000, 1000, 5);
				reportsTabFolder.setLayoutData(tabFolderLData);
				reportsTabFolder.addSelectionListener(new ReportingListener());

				{
					tiSOB = new TabItem(reportsTabFolder, SWT.NONE);
					tiSOB.setText("SOB");
					{
						canSob = new Canvas(reportsTabFolder, SWT.NONE);
						tiSOB.setControl(canSob);
						{
							tblSob = new Table(canSob, SWT.FULL_SELECTION
									| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);

							tblSob.setHeaderVisible(true);
							tblSob.setLinesVisible(true);
							tblSob.setBounds(11, 28, 942, 472);
							{
								colSNo = new TableColumn(tblSob, SWT.NONE);
								colSNo.setText("S.No");
								colSNo.setWidth(47);
							}
							{
								colLr = new TableColumn(tblSob, SWT.NONE);
								colLr.setText("LR No.");
								colLr.setWidth(59);
							}
							{
								colDate = new TableColumn(tblSob, SWT.NONE);
								colDate.setText("Date");
								colDate.setWidth(71);
							}
							{
								colLRType = new TableColumn(tblSob, SWT.NONE);
								colLRType.setText("LR Type");
								colLRType.setWidth(86);
							}
							{
								colTo = new TableColumn(tblSob, SWT.NONE);
								colTo.setText("To");
								colTo.setWidth(75);
							}
							{
								colNoOfArticles = new TableColumn(tblSob,
										SWT.NONE);
								colNoOfArticles.setText("No_of_articles");
								colNoOfArticles.setWidth(97);
							}
							{
								colActualWeight = new TableColumn(tblSob,
										SWT.NONE);
								colActualWeight.setText("Actual_weight");
								colActualWeight.setWidth(100);
							}
							{
								colBft = new TableColumn(tblSob, SWT.NONE);
								colBft.setText("Basic Freight");
								colBft.setWidth(82);
							}
							{
								colCC = new TableColumn(tblSob, SWT.NONE);
								colCC.setText("CC");
								colCC.setWidth(71);
							}
							{
								colIEC = new TableColumn(tblSob, SWT.NONE);
								colIEC.setText("IEC");
								colIEC.setWidth(71);
							}
							{
								colOtherCharges = new TableColumn(tblSob,
										SWT.NONE);
								colOtherCharges.setText("Other Charges");
								colOtherCharges.setWidth(90);
							}
							{
								ColDd = new TableColumn(tblSob, SWT.NONE);
								ColDd.setText("Door Delivery");
								ColDd.setWidth(100);
							}
							{
								colTotal = new TableColumn(tblSob, SWT.NONE);
								colTotal.setText("Total");
								colTotal.setWidth(83);
							}
						}

					}
				}
				{
					tiSOD = new TabItem(reportsTabFolder, SWT.NONE);
					tiSOD.setText("SOD");
					{
						canSod = new Canvas(reportsTabFolder, SWT.BORDER);

						tiSOD.setControl(canSod);
						{
							tblSod = new Table(canSod, SWT.FULL_SELECTION
									| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
							tblSod.setHeaderVisible(true);
							tblSod.setLinesVisible(true);
							tblSod.setBounds(12, 20, 908, 480);
							{
								colSodSNo = new TableColumn(tblSod, SWT.NONE);
								colSodSNo.setText("S.No.");
								colSodSNo.setWidth(51);
							}
							
							{
								colSodLrNo = new TableColumn(tblSod, SWT.NONE);
								colSodLrNo.setText("LR No");
								colSodLrNo.setWidth(60);
							}

							{
								colSodDate = new TableColumn(tblSod, SWT.NONE);
								colSodDate.setText("LR DATE");
								colSodDate.setWidth(70);
							}

							{
								colSodFrom = new TableColumn(tblSod, SWT.NONE);
								colSodFrom.setText("FROM");
								colSodFrom.setWidth(60);
							}
							{
								colSodNoOfArticles = new TableColumn(tblSod,
										SWT.NONE);
								colSodNoOfArticles.setText("No.Of Articles");
								colSodNoOfArticles.setWidth(83);
							}
							
							{
								colSodWeight = new TableColumn(tblSod, SWT.NONE);
								colSodWeight.setText("WEIGHT");
								colSodWeight.setWidth(60);
							}
							{
								colSodLRType = new TableColumn(tblSod, SWT.NONE);
								colSodLRType.setText("LRType");
								colSodLRType.setWidth(90);
							}

							{
								colSodTotalAmt = new TableColumn(tblSod,
										SWT.NONE);
								colSodTotalAmt.setText("LR Total");
								colSodTotalAmt.setWidth(83);
							}

							{
								colSodDD = new TableColumn(tblSod, SWT.NONE);
								colSodDD.setText("DD");
								colSodDD.setWidth(60);
							}
							{
								colSodDemurrage = new TableColumn(tblSod,
										SWT.NONE);
								colSodDemurrage.setText("DEMURRAGE");
								colSodDemurrage.setWidth(79);
							}
							{
								colSodUnderCharge = new TableColumn(tblSod,
										SWT.NONE);
								colSodUnderCharge.setText("UNDERCHARGE");
								colSodUnderCharge.setWidth(101);
							}
							{
								colSodPostage = new TableColumn(tblSod,
										SWT.NONE);
								colSodPostage.setText("POSTAGE");
								colSodPostage.setWidth(60);
							}
							{
								colSodDDExtra = new TableColumn(tblSod,
										SWT.NONE);
								colSodDDExtra.setText("DD EXTRA");
								colSodDDExtra.setWidth(78);
							}
							{
								colSodOthers = new TableColumn(tblSod, SWT.NONE);
								colSodOthers.setText("OTHERS");
								colSodOthers.setWidth(60);
							}							
							
							{
								colSodCRNo = new TableColumn(tblSod, SWT.NONE);
								colSodCRNo.setText("CR NO");
								colSodCRNo.setWidth(60);
							}
						
							{
								colSodCRAmt = new TableColumn(tblSod, SWT.NONE);
								colSodCRAmt.setText("CR AMT");
								colSodCRAmt.setWidth(60);
							}
							
							{
								colSodBookingDays = new TableColumn(tblSod,
										SWT.NONE);
								colSodBookingDays.setText("BOOKING DAYS");
								colSodBookingDays.setWidth(93);
							}
							{
								colSodInwardDays = new TableColumn(tblSod,
										SWT.NONE);
								colSodInwardDays.setText("INWARD DAYS");
								colSodInwardDays.setWidth(87);

							}
						
						}

					}
				}
				{
					tiDDR = new TabItem(reportsTabFolder, SWT.NONE);
					tiDDR.setText("DDR");
					{
						canDDR = new Canvas(reportsTabFolder, SWT.BORDER);
						tiDDR.setControl(canDDR);
						{
							tblddr = new Table(canDDR, SWT.FULL_SELECTION
									| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
							tblddr.setHeaderVisible(true);
							tblddr.setLinesVisible(true);
							tblddr.setBounds(29, 32, 931, 468);
							{
								colDdrSno = new TableColumn(tblddr, SWT.NONE);
								colDdrSno.setText("S.No");
								colDdrSno.setWidth(46);
							}
							{
								colDdrLrno = new TableColumn(tblddr, SWT.NONE);
								colDdrLrno.setText("LR No");
								colDdrLrno.setWidth(60);
							}
							{
								colDdrLrDate = new TableColumn(tblddr, SWT.NONE);
								colDdrLrDate.setText("LR DATE");
								colDdrLrDate.setWidth(80);
							}
							{
								colDdrFrom = new TableColumn(tblddr, SWT.NONE);
								colDdrFrom.setText("FROM");
								colDdrFrom.setWidth(60);
							}
							{
								colDdrArticles = new TableColumn(tblddr,
										SWT.NONE);
								colDdrArticles.setText("No.Of Articles");
								colDdrArticles.setWidth(83);
							}
							{
								colDdrWeight = new TableColumn(tblddr, SWT.NONE);
								colDdrWeight.setText("WEIGHT");
								colDdrWeight.setWidth(60);
							}
							{
								colTotalFrt = new TableColumn(tblddr, SWT.NONE);
								colTotalFrt.setText("LR Total");
								colTotalFrt.setWidth(70);
							}
							{
								colDdrFreight = new TableColumn(tblddr,
										SWT.NONE);
								colDdrFreight.setText("BASIC FREIGHT / LR");
								colDdrFreight.setWidth(117);
							}
							{
								colDdrDd = new TableColumn(tblddr, SWT.NONE);
								colDdrDd.setText("DOOR DELIVERY");
								colDdrDd.setWidth(95);
							}
							{
								colDdrDdExtra = new TableColumn(tblddr,
										SWT.NONE);
								colDdrDdExtra.setText("DD EXTRA");
								colDdrDdExtra.setWidth(70);
							}
							{
								colDdrOther = new TableColumn(tblddr, SWT.NONE);
								colDdrOther.setText("OTHERS");
								colDdrOther.setWidth(60);
							}
							{
								colDdrCrno = new TableColumn(tblddr, SWT.NONE);
								colDdrCrno.setText("CR NO");
								colDdrCrno.setWidth(60);
							}
							{
								colDdrDate = new TableColumn(tblddr, SWT.NONE);
								colDdrDate.setText("DATE");
								colDdrDate.setWidth(80);
							}
							{
								colDdrLrType = new TableColumn(tblddr, SWT.NONE);
								colDdrLrType.setText("LR TYPE");
								colDdrLrType.setWidth(60);
							}
						}

					}
				}
				
				/*if ( BeanUtil.getDbName() == null || BeanUtil.getDbName().equals("")){
					tistock = new TabItem(reportsTabFolder, SWT.NONE);
					tistock.setText("STOCK");
					{
						canstock = new Canvas(reportsTabFolder, SWT.NONE);
						tistock.setControl(canstock);
						{
							tblstockreport = new Table(canstock, SWT.BORDER);
							tblstockreport.setHeaderVisible(true);
							tblstockreport.setLinesVisible(true);
							tblstockreport.setBounds(10, 37, 939, 445);
							{
								colstocksno = new TableColumn(tblstockreport,
										SWT.NONE);
								colstocksno.setText("Sno");
								colstocksno.setWidth(50);
								colstocksno.addListener(SWT.Selection,
										new sortListener());
							}
							{
								tblstocklrno = new TableColumn(tblstockreport,
										SWT.NONE);
								tblstocklrno.setText("Lr No");
								tblstocklrno.setWidth(87);
								tblstocklrno.addListener(SWT.Selection,
										new sortListener());
							}
							{
								tblcolstocklrdate = new TableColumn(
										tblstockreport, SWT.NONE);
								tblcolstocklrdate.setText("Lr Date");
								tblcolstocklrdate.setWidth(89);
								tblcolstocklrdate.addListener(SWT.Selection,
										new sortListener());
							}
							{
								tblcollrtype = new TableColumn(tblstockreport,
										SWT.NONE);
								tblcollrtype.setText("Lr Type");
								tblcollrtype.setWidth(67);
								tblcollrtype.addListener(SWT.Selection,
										new sortListener());
							}
							{
								tblcolfrom = new TableColumn(tblstockreport,
										SWT.NONE);
								tblcolfrom.setText("From");
								tblcolfrom.setWidth(78);
								tblcolfrom.addListener(SWT.Selection,
										new sortListener());
							}
							{
								tblcolstocknoa = new TableColumn(
										tblstockreport, SWT.NONE);
								tblcolstocknoa.setText("NOA");
								tblcolstocknoa.setWidth(40);
								tblcolstocknoa.addListener(SWT.Selection,
										new sortListener());
							}
							{
								tblcolstockactual = new TableColumn(
										tblstockreport, SWT.NONE);
								tblcolstockactual.setText("Actual Weight");
								tblcolstockactual.setWidth(90);
								tblcolstockactual.addListener(SWT.Selection,
										new sortListener());
							}
							{
								tblcolstockfreight = new TableColumn(
										tblstockreport, SWT.NONE);
								tblcolstockfreight.setText("Freight");
								tblcolstockfreight.setWidth(60);
								tblcolstockfreight.addListener(SWT.Selection,
										new sortListener());
							}
							{
								tblcolstockarticlevalue = new TableColumn(
										tblstockreport, SWT.NONE);
								tblcolstockarticlevalue
										.setText("Article Value");
								tblcolstockarticlevalue.setWidth(90);
								tblcolstockarticlevalue.addListener(
										SWT.Selection, new sortListener());
							}
							{
								tblcolstockarticletype = new TableColumn(
										tblstockreport, SWT.NONE);
								tblcolstockarticletype.setText("Article Type");
								tblcolstockarticletype.setWidth(90);
								tblcolstockarticletype.addListener(
										SWT.Selection, new sortListener());
							}
							{
								tblcolstockinward = new TableColumn(
										tblstockreport, SWT.NONE);
								tblcolstockinward.setText("Inward Days");
								tblcolstockinward.setWidth(90);
								tblcolstockinward.addListener(SWT.Selection,
										new sortListener());
							}
							{
								tblcolstocksent = new TableColumn(
										tblstockreport, SWT.NONE);
								tblcolstocksent.setText("Sent Days");
								tblcolstocksent.setWidth(65);
								tblcolstocksent.addListener(SWT.Selection,
										new sortListener());
							}
							{
								tblcolstockddc = new TableColumn(
										tblstockreport, SWT.NONE);
								tblcolstockddc.setText("DD");
								tblcolstockddc.setWidth(65);
								tblcolstockddc.addListener(SWT.Selection,
										new sortListener());
							}
							{
								tblcolstockvehicle = new TableColumn(
										tblstockreport, SWT.NONE);
								tblcolstockvehicle.setText("Arrival Vehicle");
								tblcolstockvehicle.setWidth(85);
								tblcolstockvehicle.addListener(SWT.Selection,
										new sortListener());
							}
							{
								tblcolstockconsignor = new TableColumn(
										tblstockreport, SWT.NONE);
								tblcolstockconsignor.setText("Consignor Name");
								tblcolstockconsignor.setWidth(100);
								tblcolstockconsignor.addListener(SWT.Selection,
										new sortListener());
							}
							{
								tblcolstockConsignee = new TableColumn(
										tblstockreport, SWT.NONE);
								tblcolstockConsignee.setText("Consignee  Name");
								tblcolstockConsignee.setWidth(100);
								tblcolstockConsignee.addListener(SWT.Selection,
										new sortListener());
							}
						}
					}
					if (beanutil.isAdmin() && 
							(BeanUtil.getDbName() == null || BeanUtil.getDbName().equals("")) ) {
						outstandingtab = new TabItem(reportsTabFolder, SWT.NONE);
						outstandingtab.setText("CASH OUTSTANDING");

						canoutstanding = new Canvas(reportsTabFolder, SWT.NONE);
						outstandingtab.setControl(canoutstanding);
						{
							tbloutstanding = new Table(canoutstanding,
									SWT.FULL_SELECTION | SWT.H_SCROLL
											| SWT.V_SCROLL | SWT.BORDER);
							tbloutstanding.setBounds(54, 47, 775, 418);

							tbloutstanding.setHeaderVisible(true);
							tbloutstanding.setLinesVisible(true);

							{
								tableColumn2 = new TableColumn(tbloutstanding,
										SWT.NONE);
								tableColumn2.setText("SNO");
								tableColumn2.setWidth(45);
								tableColumn2
										.addSelectionListener(new ReportingListener());
							}
							{
								coloutbranch = new TableColumn(tbloutstanding,
										SWT.NONE);
								coloutbranch.setText("Branch Code");
								coloutbranch.setWidth(95);
								coloutbranch
										.addSelectionListener(new ReportingListener());
								coloutbranch.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colStationName2 = new TableColumn(
										tbloutstanding, SWT.NONE);
								colStationName2.setText("Station Name");
								colStationName2.setWidth(207);
								colStationName2
										.addSelectionListener(new ReportingListener());
								colStationName2.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colcode = new TableColumn(tbloutstanding,
										SWT.NONE);
								colcode.setText("Station Code");
								colcode.setWidth(77);
								colcode
										.addSelectionListener(new ReportingListener());
								colcode.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colpbdd = new TableColumn(tbloutstanding,
										SWT.NONE);
								colpbdd.setText("TP \u2013 DD");
								colpbdd.setWidth(72);
								colpbdd
										.addSelectionListener(new ReportingListener());
								colpbdd.addListener(SWT.Selection,
										new sortListener());
							}
							{
								coltpod = new TableColumn(tbloutstanding,
										SWT.NONE);
								coltpod.setText("TP-OD");
								coltpod.setWidth(68);
								coltpod
										.addSelectionListener(new ReportingListener());
								coltpod.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colpdbooking = new TableColumn(tbloutstanding,
										SWT.NONE);
								colpdbooking.setText("PDBOOKING");
								colpdbooking.setWidth(107);
								colpdbooking
										.addSelectionListener(new ReportingListener());
								colpdbooking.addListener(SWT.Selection,
										new sortListener());

							}
							{
								coltotal = new TableColumn(tbloutstanding,
										SWT.NONE);
								coltotal.setText("TOTAL");
								coltotal.setWidth(78);
								coltotal
										.addSelectionListener(new ReportingListener());
								coltotal.addListener(SWT.Selection,
										new sortListener());
							}
							{
								cbbranch = new Combo(canoutstanding, SWT.NONE);
								cbbranch.setBounds(577, 6, 187, 21);
								populateSelectedBranches();
							}
							{
								btnoutstanding = new Button(canoutstanding,
										SWT.PUSH | SWT.CENTER);
								btnoutstanding.setText("Go");
								btnoutstanding.setBounds(776, 6, 36, 26);
								btnoutstanding
										.addSelectionListener(new ReportingListener());
							}
							if (beanutil.isAdmin() && !beanutil.isAdminHead()
									&& !beanutil.isAdminHeadStationary()) {
								cbbranch.setEnabled(false);
								btnoutstanding.setEnabled(false);
							}

						}
					}

				}*/
				if ( BeanUtil.getDbName() == null || BeanUtil.getDbName().equals("")){
					tiAgent = new TabItem(reportsTabFolder, SWT.NONE);
					tiAgent.setText("Current Status");

					canAgentReport = new Canvas(reportsTabFolder, SWT.NONE);
					tiAgent.setControl(canAgentReport);

					tblAgentReport = new Table(canAgentReport,
							SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL
									| SWT.BORDER);
					tblAgentReport.setBounds(28, 50, 891, 450);

					tblAgentReport.setHeaderVisible(true);
					tblAgentReport.setLinesVisible(true);
					{
						cbCurrentStatus = new Combo(canAgentReport, SWT.NONE);
						cbCurrentStatus.setBounds(638, 12, 159, 21);
					}
					{
						lblCurrentStatus = new Label(canAgentReport, SWT.NONE);
						lblCurrentStatus.setText("Select Station");
						lblCurrentStatus.setBounds(557, 12, 75, 21);
					}
					{
						btnCurrentGo = new Button(canAgentReport, SWT.PUSH
								| SWT.CENTER);
						btnCurrentGo.setText("Go");
						btnCurrentGo.setBounds(809, 7, 37, 26);
						btnCurrentGo
								.addSelectionListener(new ReportingListener());

					}
					{
						colAgentSno = new TableColumn(tblAgentReport, SWT.NONE);
						colAgentSno.setText("Sno");
						colAgentSno.setWidth(48);
						colAgentSno.addListener(SWT.Selection,
								new sortListener());
					}
					{
						colAgentLrno = new TableColumn(tblAgentReport, SWT.NONE);
						colAgentLrno.setText("Lrno");
						colAgentLrno.setWidth(60);
						colAgentLrno.addListener(SWT.Selection,
								new sortListener());
					}
					{
						colAgentLrDate = new TableColumn(tblAgentReport,
								SWT.NONE);
						colAgentLrDate.setText("LrDate");
						colAgentLrDate.setWidth(67);
						colAgentLrDate.addListener(SWT.Selection,
								new sortListener());
					}
					{
						colAgentFrom = new TableColumn(tblAgentReport, SWT.NONE);
						colAgentFrom.setText("From");
						colAgentFrom.setWidth(60);
						colAgentFrom.addListener(SWT.Selection,
								new sortListener());
					}
					{
						colAgentTo = new TableColumn(tblAgentReport, SWT.NONE);
						colAgentTo.setText("To");
						colAgentTo.setWidth(60);
						colAgentTo.addListener(SWT.Selection,
								new sortListener());
					}
					{
						colAgentBookingdays = new TableColumn(tblAgentReport,
								SWT.NONE);
						colAgentBookingdays.setText("BookingDays");
						colAgentBookingdays.setWidth(86);
						colAgentBookingdays.addListener(SWT.Selection,
								new sortListener());
					}
					{
						colAgentCurrentStatus = new TableColumn(tblAgentReport,
								SWT.NONE);
						colAgentCurrentStatus.setText("Current Status");
						colAgentCurrentStatus.setWidth(150);
						colAgentCurrentStatus.addListener(SWT.Selection,
								new sortListener());
					}
					{
						colAgentNoa = new TableColumn(tblAgentReport, SWT.NONE);
						colAgentNoa.setText("NOA");
						colAgentNoa.setWidth(60);
						colAgentNoa.addListener(SWT.Selection,
								new sortListener());
					}
					{
						colAgentWeight = new TableColumn(tblAgentReport,
								SWT.NONE);
						colAgentWeight.setText("Weight");
						colAgentWeight.setWidth(60);
						colAgentWeight.addListener(SWT.Selection,
								new sortListener());
					}
					{
						colAgentDDC = new TableColumn(tblAgentReport, SWT.NONE);
						colAgentDDC.setText("DDC");
						colAgentDDC.setWidth(60);
						colAgentDDC.addListener(SWT.Selection,
								new sortListener());
					}
					{
						colAgentTotal = new TableColumn(tblAgentReport,
								SWT.NONE);
						colAgentTotal.setText("Total");
						colAgentTotal.setWidth(60);
						colAgentTotal.addListener(SWT.Selection,
								new sortListener());
					}
					{
						colAgentCnorName = new TableColumn(tblAgentReport,
								SWT.NONE);
						colAgentCnorName.setText("CnorName");
						colAgentCnorName.setWidth(72);
						colAgentCnorName.addListener(SWT.Selection,
								new sortListener());
					}
					{
						colAgentCneeName = new TableColumn(tblAgentReport,
								SWT.NONE);
						colAgentCneeName.setText("CneeName");
						colAgentCneeName.setWidth(79);
						colAgentCneeName.addListener(SWT.Selection,
								new sortListener());
					}

				}
				
				/*if ( BeanUtil.getDbName() == null || BeanUtil.getDbName().equals("")){
					tiInwardRegister = new TabItem(reportsTabFolder, SWT.NONE);
					tiInwardRegister.setText("Inward Register");
					{
						canIR = new Canvas(reportsTabFolder, SWT.NONE);
						tiInwardRegister.setControl(canIR);
						{
							tblInwardRegister = new Table(canIR,
									SWT.FULL_SELECTION | SWT.H_SCROLL
											| SWT.V_SCROLL | SWT.BORDER);

							tblInwardRegister.setHeaderVisible(true);
							tblInwardRegister.setLinesVisible(true);
							tblInwardRegister.setBounds(5, 28, 930, 453);
							{
								colSNo = new TableColumn(tblInwardRegister,
										SWT.NONE);
								colSNo.setText("S.No");
								colSNo.setWidth(40);
							}
							{
								colLr = new TableColumn(tblInwardRegister,
										SWT.NONE);
								colLr.setText("LR No.");
								colLr.setWidth(59);
							}
							{
								colDate = new TableColumn(tblInwardRegister,
										SWT.NONE);
								colDate.setText("LR Date");
								colDate.setWidth(71);
							}
							{
								colLRType = new TableColumn(tblInwardRegister,
										SWT.NONE);
								colLRType.setText("LR Type");
								colLRType.setWidth(86);
							}
							{
								colSentDate = new TableColumn(
										tblInwardRegister, SWT.NONE);
								colSentDate.setText("Sent Date");
								colSentDate.setWidth(75);
							}
							{
								colTotalFreight = new TableColumn(
										tblInwardRegister, SWT.NONE);
								colTotalFreight.setText("Total Freight");
								colTotalFreight.setWidth(82);
							}

							{
								colFrom = new TableColumn(tblInwardRegister,
										SWT.NONE);
								colFrom.setText("From");
								colFrom.setWidth(71);
							}

							{
								colNoOfArticles = new TableColumn(
										tblInwardRegister, SWT.NONE);
								colNoOfArticles.setText("NOA");
								colNoOfArticles.setWidth(75);
							}

							{
								colCrgWeight = new TableColumn(
										tblInwardRegister, SWT.NONE);
								colCrgWeight.setText("Crg Wt");
								colCrgWeight.setWidth(71);
							}
							{
								colArrivalVehicle = new TableColumn(
										tblInwardRegister, SWT.NONE);
								colArrivalVehicle.setText("Arrival Vehicle");
								colArrivalVehicle.setWidth(90);
							}
							{
								colConsignee = new TableColumn(
										tblInwardRegister, SWT.NONE);
								colConsignee.setText("Consignee");
								colConsignee.setWidth(100);
							}
							{
								colSignature = new TableColumn(
										tblInwardRegister, SWT.NONE);
								colSignature.setText("Signature");
								colSignature.setWidth(83);
							}
							
							for (int index = 0; index < tblInwardRegister.getColumnCount(); index++) {
								tblInwardRegister.getColumn(index).addListener(SWT.Selection, new sortListener());
							}
						}

					}
				
				}*/
				
				
				if(beanutil.getClientRights() != new Float(2.2) && beanutil.getClientRights() != new Float(2.3) 
			    && beanutil.getClientRights() != new Float(2.4))				
			
				{
					
					

					tiRefund = new TabItem(reportsTabFolder, SWT.NONE);
					tiRefund.setText(REFUND_TAB);
					{
						Canvas cvsRef = new Canvas(reportsTabFolder, SWT.NONE);
						tblRefund = new Table(cvsRef, SWT.H_SCROLL
								| SWT.FULL_SELECTION | SWT.V_SCROLL
								| SWT.BORDER | SWT.MULTI);
						tblRefund.setHeaderVisible(true);
						tblRefund.setLinesVisible(true);
						tblRefund.setBounds(20, 50, 740, 450);
						tiRefund.setControl(cvsRef);
						
						{
							gpMonth = new Group(cvsRef, SWT.NONE);
							gpMonth.setText("Month");
							gpMonth.setBounds(566, 0, 140, 50);
						}

						{
							txtMonthRef = new Text(gpMonth, SWT.BORDER);
							txtMonthRef.setEnabled(false);
							DateFormat dateFormat = new SimpleDateFormat("MM-yyyy");
							java.util.Date currentDate = new java.util.Date();
							String date = dateFormat.format(currentDate);
							txtMonthRef.setText(date);
							txtMonthRef.setBounds(10, 20, 57, 19);
						}
						{
							btnMonthRef = new Button(gpMonth, SWT.PUSH | SWT.CENTER);
							btnMonthRef.setImage(SWTResourceManager
									.getImage("hm/akr/resources/Calendar.jpg"));
							btnMonthRef.setBounds(69, 17, 26, 23);
							btnMonthRef.addSelectionListener(new SelectionListener() {

								@Override
								public void widgetDefaultSelected(SelectionEvent arg0) {
								}

								@Override
								public void widgetSelected(SelectionEvent event) {
									MonthDialog cd = new MonthDialog(Display.getCurrent()
											.getActiveShell());
									Object obj = cd.open();
									if (obj != null) {
										selectedDate = txtMonthRef.getText();																		
										txtMonthRef.setText(obj.toString());
									}
								}

							});
						}
						
						{
							colRefSNo = new TableColumn(tblRefund, SWT.NONE);
							colRefSNo.setText("S.No");
							colRefSNo.setWidth(70);
							colRefSNo.addListener(SWT.Selection,
									new sortListener());
						}
						{
							colRefDesc = new TableColumn(tblRefund, SWT.NONE);
							colRefDesc.setText("Description");
							colRefDesc.setWidth(200);
							colRefDesc.addListener(SWT.Selection,
									new sortListener());
						}
						{
							colRefContact = new TableColumn(tblRefund, SWT.NONE);
							colRefContact.setText("Contact");
							colRefContact.setWidth(177);
							colRefContact.addListener(SWT.Selection,
									new sortListener());
						}
						{
							colRefAmount = new TableColumn(tblRefund, SWT.NONE);
							colRefAmount.setText("Amount");
							colRefAmount.setWidth(107);
							colRefAmount.addListener(SWT.Selection,
									new sortListener());
						}
						{
							colRefInst = new TableColumn(tblRefund, SWT.NONE);
							colRefInst.setText("Installment");
							colRefInst.setWidth(80);
							colRefInst.addListener(SWT.Selection,
									new sortListener());
						}
						{
							colRefDate = new TableColumn(tblRefund, SWT.NONE);
							colRefDate.setText("Date Created");
							colRefDate.setWidth(80);
							colRefDate.addListener(SWT.Selection,
									new sortListener());
						}
					}
				
				}
				
				if(beanutil.getClientRights() != new Float(2.2) && beanutil.getClientRights() != new Float(2.3) 
				    && beanutil.getClientRights() != new Float(2.4))				
				{

					tiRecovery = new TabItem(reportsTabFolder, SWT.NONE);
					tiRecovery.setText(RECOVERY_TAB);
					{
						Canvas cvsRec = new Canvas(reportsTabFolder, SWT.NONE);
						tblRecovery = new Table(cvsRec, SWT.H_SCROLL
								| SWT.FULL_SELECTION | SWT.V_SCROLL
								| SWT.BORDER | SWT.MULTI);
						tblRecovery.setHeaderVisible(true);
						tblRecovery.setLinesVisible(true);
						tblRecovery.setBounds(20, 50, 740, 450);
						tiRecovery.setControl(cvsRec);
						
						{
							gpMonth = new Group(cvsRec, SWT.NONE);
							gpMonth.setText("Month");
							gpMonth.setBounds(566, 0, 140, 50);
						}

						{
							txtMonth = new Text(gpMonth, SWT.BORDER);
							txtMonth.setEnabled(false);
							DateFormat dateFormat = new SimpleDateFormat("MM-yyyy");
							java.util.Date currentDate = new java.util.Date();
							String date = dateFormat.format(currentDate);
							txtMonth.setText(date);
							txtMonth.setBounds(10, 20, 57, 19);
						}
						{
							btnMonth = new Button(gpMonth, SWT.PUSH | SWT.CENTER);
							btnMonth.setImage(SWTResourceManager
									.getImage("hm/akr/resources/Calendar.jpg"));
							btnMonth.setBounds(69, 17, 26, 23);
							btnMonth.addSelectionListener(new SelectionListener() {

								@Override
								public void widgetDefaultSelected(SelectionEvent arg0) {
								}

								@Override
								public void widgetSelected(SelectionEvent event) {
									MonthDialog cd = new MonthDialog(Display.getCurrent()
											.getActiveShell());
									Object obj = cd.open();
									if (obj != null) {
										selectedDate = txtMonth.getText();																		
										txtMonth.setText(obj.toString());
									}
								}

							});
						}
						
						{
							colRecSNo = new TableColumn(tblRecovery, SWT.NONE);
							colRecSNo.setText("S.No");
							colRecSNo.setWidth(70);
							colRecSNo.addListener(SWT.Selection,
									new sortListener());
						}
						{
							colRecDesc = new TableColumn(tblRecovery, SWT.NONE);
							colRecDesc.setText("Description");
							colRecDesc.setWidth(200);
							colRecDesc.addListener(SWT.Selection,
									new sortListener());
						}
						{
							colRecContact = new TableColumn(tblRecovery,
									SWT.NONE);
							colRecContact.setText("Contact");
							colRecContact.setWidth(177);
							colRecContact.addListener(SWT.Selection,
									new sortListener());
						}
						{
							colRecAmount = new TableColumn(tblRecovery,
									SWT.NONE);
							colRecAmount.setText("Amount");
							colRecAmount.setWidth(107);
							colRecAmount.addListener(SWT.Selection,
									new sortListener());
						}

						{
							colRecInst = new TableColumn(tblRecovery, SWT.NONE);
							colRecInst.setText("Installment");
							colRecInst.setWidth(80);
							colRecInst.addListener(SWT.Selection,
									new sortListener());
						}

						{
							colRecDate = new TableColumn(tblRecovery, SWT.NONE);
							colRecDate.setText("Date Created");
							colRecDate.setWidth(80);
							colRecDate.addListener(SWT.Selection,
									new sortListener());
						}

					}
				
				}

				reportsTabFolder.setSelection(0);
			}

			populateSOBRecords(true);
			// populateSODRecords();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return this;
	}

	private void populateSelectedBranches() {
		try {
			AdminCompositeHandler adminhandler = new AdminCompositeHandler();
			StationsDTO[] stations = adminhandler.getAllBranches();
			if (null != stations) {
				int len = stations.length;
				for (int i = 0; i < len; i++) {
					cbbranch.add(stations[i].getName() + " - "
							+ stations[i].getId());
					if (stations[i].getId().equals(
							beanutil.getActingStationCode())) {
						cbbranch.select(i);
					}
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Method to populate SOD Records
	 * 
	 * @param refresh
	 */
	private void populateSODRecords(boolean refresh) {
		if (refresh || null == deliveries) {
			
			String stationCode = handler.getStationCode();
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date date = null;
			
			try {
				date = dateFormat.parse(txtDate.getText());
				
			} catch (ParseException e) {
				e.printStackTrace();
			}

			try {
				
				
				if(date.before(BeanUtil.getThree_month_updated())){
					//System.out.println("DELIVERY History");
					deliveries = handler.getStatementOfDeliveryHistory(stationCode, date);
		        }else{
		        	//System.out.println("DELIVERY");
		        	deliveries = handler.getStatementOfDelivery(stationCode, date);
		        }
				

				firsttab = false;
			} catch (BusinessException businessexception) {
				displayError(businessexception.getResponseMessage());
			}

			catch (Exception exception) {
				exception.printStackTrace();
				displayError("Error occurred while populating the SOD");
			}
			if (null != deliveries) {
				populateSODTable(deliveries);
			} else {
				
				tblSod.removeAll();
				displayError("No record Available");
			}
		}
	}

	/**
	 * Method to populate SOB records
	 * 
	 * @param refresh
	 */
	private void populateSOBRecords(boolean refresh) {
		if (refresh || null == bookings) {
			
			
			String stationCode = handler.getStationCode();
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date date = null;
		
			try {
				date = dateFormat.parse(txtDate.getText());
			
			} catch (ParseException e) {
				e.printStackTrace();
			}

			try {
				if(date.before(BeanUtil.getThree_month_updated())){
					//System.out.println("booking History");
					bookings = handler.getStatementOfBookingHistory(stationCode, date);
		        }else{
		        	//System.out.println("booking ");
		        	bookings = handler.getStatementOfBooking(stationCode, date);
		        }
				
				if (null != bookings){
					firsttab = false;					
				}
			} catch (BusinessException businessexception) {
				displayError(businessexception.getResponseMessage());
			} catch (Exception exception) {
				exception.printStackTrace();
				displayError("Error occurred while populating the SOB");
			}
			if (null != bookings) {	
				
				populateSOBTable(bookings);
			} else if (firsttab == false) {
			
				tblSob.removeAll();
				displayError("No Records Available");
			}
		}

	}

	/**
	 * Method to populate DDR records
	 * 
	 * @param refresh
	 */
	private void populateDDRRecords(boolean refresh) {
		if (refresh || null == door) {
			
			String stationCode = handler.getStationCode();
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date date = null;
			int monthDiff = 0;
			try {
				date = dateFormat.parse(txtDate.getText());
						        
		        
			} catch (ParseException e) {
				e.printStackTrace();
			}

			try {
				if(date.before(BeanUtil.getThree_month_updated())){
					door = handler.getStatementOfDoorDeliveryReImbursementHistory(
							stationCode, date);
		        }else{
		        	door = handler.getStatementOfDoorDeliveryReImbursement(
							stationCode, date);
		        }				

				firsttab = false;
			} catch (BusinessException businessexception) {
				displayError(businessexception.getResponseMessage());
			} catch (Exception exception) {
				displayError("Error occurred while populating the SOB");
			}
			if (null != door) {
				
				populateDDRTable(door);
			} else {
						
				tblddr.removeAll();
				displayError("No records Available");
			}
		}
	}
	
	private void populateIRRecords(boolean refresh) {
		if (refresh || null == irDto) {		
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date date = null;
			Date toDate = null;
			try {
				date = dateFormat.parse(txtDate.getText());
		
				toDate = dateFormat.parse(txtToDate.getText());
				
	if(				date.before(BeanUtil.getThree_month_updated())){
					System.out.println("in inw regtr hist-->"+BeanUtil.getThree_month_updated());
					irDto = handler.getInwardRegisterHistory(date, toDate);
				}else{
					System.out.println("in inw regtr curr-->"+BeanUtil.getThree_month_updated());
				irDto = handler.getInwardRegister(date, toDate);
              }

				firsttab = false;
			} catch (Exception exception) {
				exception.printStackTrace();
				displayError("Error occurred while populating");
			}
			if (null != irDto) {
				btnExportXL.setEnabled(true);
				btnExportPDF.setEnabled(true);
				btnprint.setEnabled(true);
				populateIRTable(irDto);
				
			} else {
				btnExportXL.setEnabled(false);
				btnExportPDF.setEnabled(false);
				btnprint.setEnabled(false);
				tblInwardRegister.removeAll();
				displayError("No record Available");
			}
		}
	}
	
	private void populateIRTable(GMROutTimeDTO[] irDto) {

		
		tblInwardRegister.removeAll();
		
		int len = irDto.length;
		String date = txtDate.getText();
		String serialPrefix = calculateStatementNumber(date, "IR");
		txtSta.setText(serialPrefix);
		irST = serialPrefix;
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		float total = 0;
		int totalNoa = 0;
		float totalActWt = 0;
				
		for (int i = 0, j = 1; i < len; i++) {
			TableItem item = new TableItem(tblInwardRegister, SWT.NONE);
			item.setText(0, String.valueOf(j));
			item.setText(1, irDto[i].getLr_no());			
			item.setText(2, dateFormat.format(irDto[i].getLrDate()));
			item.setText(3, irDto[i].getLr_type());
			item.setText(4, dateFormat.format(irDto[i].getOutTimeDate()));
			total = total + irDto[i].getTotal();
			item.setText(5, getRoundedValue(irDto[i].getTotal()));
			item.setText(6, irDto[i].getFrom());
			totalNoa = totalNoa + irDto[i].getNo_of_articles();
			item.setText(7, String.valueOf(irDto[i].getNo_of_articles()));
			totalActWt = totalActWt + irDto[i].getActual_weight();
			item.setText(8, getRoundedValue(irDto[i].getActual_weight()));
			if(null != irDto[i].getArticle_type()){
				item.setText(9, irDto[i].getArticle_type());
			}			
			item.setText(10, irDto[i].getConsigneeName());
			j++;
		}

		TableItem item1 = new TableItem(tblInwardRegister, SWT.NONE | SWT.BOLD);
		Font font1 = new Font(display, "Tahoma", 8, SWT.BOLD);
		item1.setFont(font1);

		item1.setText(3, "TOTAL");

		item1.setText(5, getRoundedValue(total));
		item1.setText(7, String.valueOf(totalNoa));
		item1.setText(8, getRoundedValue(totalActWt));
		
		
	}

	private String getRoundedValue(float cardRate) {
		DecimalFormat decimalFormat;
		decimalFormat = new DecimalFormat("0.00");
		cardRate = cardRate * 100;
		cardRate = (float) Math.round(cardRate);
		cardRate = cardRate / 100;

		return decimalFormat.format(cardRate);
	}
	
	/**
	 * Method to calculate Statement Number
	 * 
	 * @param dateString
	 * @param stmt
	 * @return
	 */
	private String calculateStatementNumber(String dateString, String stmt) {
		String serialPrefix = EMPTY_STRING;

		if (null != dateString && dateString.length() > 0) {
			serialPrefix = handler.getStationCode() + stmt;
			SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);

			try {

				GregorianCalendar givenDate = new GregorianCalendar();
				Date date = format.parse(dateString);
				givenDate.setTime(date);

				int month = givenDate.get(Calendar.MONTH);
				int year = givenDate.get(Calendar.YEAR);
				int day = givenDate.get(Calendar.DATE);
				int mo = month + 1;

				givenDate.set(year, mo, day);

				if (month < 4)
					year--;
				GregorianCalendar cal = new GregorianCalendar();
				cal.set(year, 3, 31);
				long mill1 = givenDate.getTimeInMillis();
				long mill2 = cal.getTimeInMillis();
				long diff = mill1 - mill2;

				// long diff = date.getTime() - cal.getTime().getTime();

				temp = (int) (diff / (24 * 60 * 60 * 1000)); // Number Of
				// days since
				// financial year
			} catch (Exception exception) {
				return EMPTY_STRING;
			}
		}
		return serialPrefix + temp;
	}

	/**
	 * Method to populate SOB table
	 * 
	 * @param bookings
	 */
	private void populateSOBTable(BookingDTO[] bookings) {
		tblSob.removeAll();
		btnExportXL.setEnabled(true);
		btnExportPDF.setEnabled(true);
		String date = txtDate.getText();
		String serialPrefix = calculateStatementNumber(date, "SOB");
		txtSta.setText(serialPrefix);
		sobST = serialPrefix;
		// DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

		int len = bookings.length;
		int article = 0;
		float actul_weight = 0;
		int bft = 0;
		float cc = 0;
		float otherCharges = 0;
		float totalOtherCharges = 0;
		int dd = 0;
		int total = 0;
		float iec = 0;
		totaltopay = 0;
		totalpaid = 0;
		totalbillng = 0;
		for (int i = 0; i < len; i++) {
			TableItem item = new TableItem(tblSob, SWT.NONE);
			item.setText(0, Integer.toString(i + 1));
			item.setText(1, bookings[i].getLrno());
			item.setText(2, date);
			item.setText(3, bookings[i].getType());

			item.setText(4, bookings[i].getTo());

			if (!bookings[i].getStatus()) {
				item.setText(5, Integer.toString(bookings[i]
						.getNo_of_articles()));
				article = article + bookings[i].getNo_of_articles();
				item.setText(6, decimalFormat.format(bookings[i].getActual_weight()));
				actul_weight = actul_weight + bookings[i].getActual_weight();
				item.setText(7, String.valueOf((int)bookings[i].getBft()));
				bft = bft + (int)bookings[i].getBft();
				item.setText(8, decimalFormat.format(bookings[i].getCcc()));
				cc = cc + bookings[i].getCcc();
				// Other Charges
				otherCharges = bookings[i].getOther_charges()
						+ bookings[i].getLrc() + bookings[i].getDcc()
						+ bookings[i].getLc() + bookings[i].getGsc()
						+ bookings[i].getStax();

				item.setText(9, decimalFormat.format(bookings[i].getIec()));
				iec = iec + bookings[i].getIec();
				item.setText(10, decimalFormat.format(otherCharges));

				bookings[i].setOther_charges(otherCharges);

				// Total Other charges
				totalOtherCharges = totalOtherCharges + otherCharges;
				item.setText(11, String.valueOf((int)bookings[i].getDdc()));
				dd = dd + (int)bookings[i].getDdc();
				item.setText(12, String.valueOf((int)bookings[i].getTotal()));
				total = total + (int)bookings[i].getTotal();
			} else {
				bookings[i].setNo_of_articles(0);
				bookings[i].setActual_weight(0);
				bookings[i].setBft(0);
				bookings[i].setCcc(0);
				bookings[i].setOther_charges(0);
				bookings[i].setDdc(0);
				bookings[i].setTotal(0);
				bookings[i].setIec(0);
				item.setText(5, "Cancelled");
			}
			if (bookings[i].getType().equalsIgnoreCase("BILLING")) {
				totalbillng = totalbillng + bookings[i].getTotal();
			} else if (bookings[i].getType().equalsIgnoreCase("TOPAY")) {
				totaltopay = totaltopay + bookings[i].getTotal();
			} else if (bookings[i].getType().equalsIgnoreCase("PAID")) {
				totalpaid = totalpaid + bookings[i].getTotal();
			}
		}

		TableItem item1 = new TableItem(tblSob, SWT.NONE);
		Font font1 = new Font(display, "Tahoma", 8, SWT.BOLD);
		item1.setFont(font1);
		item1.setText(3, "TOTAL");
		item1.setText(5, Integer.toString(article));
		item1.setText(6, decimalFormat.format(actul_weight));
		item1.setText(7, String.valueOf(bft));
		item1.setText(8, decimalFormat.format(cc));
		item1.setText(9, decimalFormat.format(iec));
		item1.setText(10, decimalFormat.format(totalOtherCharges));
		item1.setText(11, String.valueOf(dd));
		item1.setText(12, String.valueOf(total));
		
		if(bookings!= null && bookings.length > 0){
			// is DRS confirmed
			if(bookings[0].getRate_type() == 1){
				btnprint.setEnabled(true);
				
			}else{
				btnprint.setEnabled(false);
				btnExportXL.setEnabled(false);
				btnExportPDF.setEnabled(false);
			}			
		}

	}

	/**
	 * Method to populate GMR table
	 * 
	 * @param gmr
	 */
	public void populateGMRTable(GMRReportDTO[] gmr) {
		tblGmr.removeAll();
		comVehicleNo.removeAll();
		comVehicleNo.add("ALL");
		int len = gmr.length;
		float weight = 0;
		int article = 0;
		TreeSet<String> tree = new TreeSet<String>();
		String date = txtDate.getText();
		String serialPrefix = calculateStatementNumber(date, "GMR");
		txtSta.setText(serialPrefix);
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		for (int i = 0; i < len; i++) {
			TableItem itemGmr = new TableItem(tblGmr, SWT.NONE);

			itemGmr.setText(0, Integer.toString(i + 1));
			itemGmr.setText(1, gmr[i].getLrNo());
			itemGmr.setText(2, dateFormat.format(gmr[i].getBookingDate()));
			itemGmr.setText(3, gmr[i].getFrom_station());
			itemGmr.setText(4, gmr[i].getTo_station());
			itemGmr.setText(5, Float.toString(gmr[i].getWeight()));
			weight = weight + gmr[i].getWeight();
			itemGmr.setText(6, Integer.toString(gmr[i].getNo_of_articles()));
			article = article + gmr[i].getNo_of_articles();
			itemGmr.setText(7, gmr[i].getConsignor_name());
			itemGmr.setText(8, gmr[i].getConsignee_name());
			if (null != gmr[i].getVehicleNo()) {
				itemGmr.setText(9, gmr[i].getVehicleNo());
				tree.add(gmr[i].getVehicleNo());

			}
			if (null != gmr[i].getMoved_to())
				itemGmr.setText(10, gmr[i].getMoved_to());

		}
		comVehicleNo.select(0);

		Iterator<String> iterator = tree.iterator();
		while (iterator.hasNext()) {
			comVehicleNo.add(iterator.next());
		}
		TableItem item1 = new TableItem(tblGmr, SWT.NONE);
		Font font1 = new Font(display, "Tahoma", 8, SWT.BOLD);
		item1.setFont(font1);
		item1.setText(0, "TOTAL");
		item1.setText(5, Float.toString(weight));
		item1.setText(6, Integer.toString(article));
	}

	/**
	 * Method to populate DDR table
	 * 
	 * @param door
	 */
	public void populateDDRTable(DDRDTO[] door) {
		int article = 0;
		float weight = 0;
		float bft = 0;
		float dd = 0;
		float ddextra = 0;
		float others = 0;
		float total = 0;
		btnExportXL.setEnabled(true);
		btnExportPDF.setEnabled(true);
		tblddr.removeAll();
		int len = door.length;
		String date = txtDate.getText();
		String serialPrefix = calculateStatementNumber(date, "DDR");
		txtSta.setText(serialPrefix);
		soddrST = serialPrefix;
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

		for (int i = 0; i < len; i++) {
			TableItem itemDoor = new TableItem(tblddr, SWT.NONE);
			itemDoor.setText(0, Integer.toString(i + 1));
			itemDoor.setText(1, door[i].getLrNo());
			itemDoor.setText(2, dateFormat.format(door[i].getLrDate()));
			itemDoor.setText(3, door[i].getFromStation());

			itemDoor.setText(4, Integer.toString(door[i].getNoOfArticles()));
			article = article + door[i].getNoOfArticles();
			itemDoor.setText(5, Float.toString(door[i].getActualWeight()));
			weight = weight + door[i].getActualWeight();

			itemDoor.setText(6, String.valueOf(door[i].getTotal()));
			total = total + door[i].getTotal();
			itemDoor.setText(7, Float.toString(door[i].getBasicFreight()));
			bft = bft + door[i].getBasicFreight();

			float ddcfree = 0;
			float ddcbase = 0;
			ddcfree = door[i].getDdcFree();
			ddcbase = door[i].getDdc();
			if (ddcfree == 0) {
				itemDoor.setText(8, Float.toString(ddcbase));
				dd = dd + ddcbase;
			} else {
				itemDoor.setText(8, Float.toString(ddcfree));
				dd = dd + ddcfree;
			}
			itemDoor.setText(9, Float.toString(door[i].getDdExtra()));
			ddextra = ddextra + door[i].getDdExtra();

			itemDoor.setText(10, Float.toString(door[i].getOthers()));
			others = others + door[i].getOthers();
			if (null != door[i].getCrno())
				itemDoor.setText(11, door[i].getCrno());
			if (null != door[i].getCrDate())
				itemDoor.setText(12, dateFormat.format(door[i].getCrDate()));

			if (null != door[i].getLrType())
				itemDoor.setText(13, door[i].getLrType());
		}

		TableItem item1 = new TableItem(tblddr, SWT.NONE);
		Font font1 = new Font(display, "Tahoma", 8, SWT.BOLD);
		item1.setFont(font1);
		item1.setText(3, "TOTAL");
		item1.setText(4, Integer.toString(article));
		item1.setText(5, Float.toString(weight));
		item1.setText(6, String.valueOf(total));
		item1.setText(7, Float.toString(bft));
		item1.setText(8, Float.toString(dd));
		item1.setText(9, Float.toString(ddextra));
		item1.setText(10, Float.toString(others));
		
		if(door!= null && door.length > 0){
			if(door[0].getIsDRSConfirmed() == 1){
				btnprint.setEnabled(true);
				btnExportXL.setEnabled(true);
				btnExportPDF.setEnabled(true);
			}else{
				btnprint.setEnabled(false);
				btnExportXL.setEnabled(false);
				btnExportPDF.setEnabled(false);
			}			
		}
		
		 sortByCRNo(11, tblddr); 
		  TableItem[] items = tblddr.getItems(); 
		  for(int k = 0; k < items.length - 1; k++){ 
			 items[k].setText(0, String.valueOf(k + 1)); 
		  }

	}

	/**
	 * Method to get StationName
	 */
	private String getStationName(String code) {
		StationsDTO[] stations = null;
		try {
			stations = handler.getStations();
			for (int i = 0; i < stations.length; i++) {
				if (code.equalsIgnoreCase(stations[i].getId())) {
					return stations[i].getName();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Method to get Selected Records
	 * 
	 * @return
	 * @throws ParseException
	 */
	private GMRReportDTO[] getSelectedRecords() throws ParseException {
		TableItem[] items = tblGmr.getItems();
		GMRReportDTO dto = null;
		int len = 0;
		ArrayList<GMRReportDTO> checkedList = new ArrayList<GMRReportDTO>();
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		if (null != items && (len = items.length) > 0) {
			for (int i = 0; i < len; i++) {

				if (items[i].getChecked()) {

					dto = new GMRReportDTO();
					dto.setLrNo(items[i].getText(1));
					dto.setBookingDate(df.parse(items[i].getText(2)));
					dto.setFrom_station(items[i].getText(3));
					dto.setTo_station(items[i].getText(4));
					dto.setWeight(Float.parseFloat(items[i].getText(5)));
					dto
							.setNo_of_articles(Integer.parseInt(items[i]
									.getText(6)));
					dto.setConsignor_name(items[i].getText(7));
					dto.setConsignee_name(items[i].getText(8));
					dto.setVehicleNo(items[i].getText(9));
					dto.setMoved_to(items[i].getText(10));
					checkedList.add(dto);

				}
			}
		}

		int size = checkedList.size();

		if (size > 0) {
			return (GMRReportDTO[]) checkedList.toArray(new GMRReportDTO[size]);
		}

		return null;
	}

	/**
	 * Method to populate SOD table
	 * 
	 * @param deliveries
	 */
	private void populateSODTable(SODDTO[] deliveries) {
		tblSod.removeAll();
		btnExportXL.setEnabled(true);
		btnExportPDF.setEnabled(true);
		int len = deliveries.length;
		int article = 0;
		float weight = 0;
		float total = 0;
		int dd = 0;
		float demu = 0;
		float under = 0;
		float postage = 0;
		int ddextra = 0;
		int others = 0;
		float cramt = 0;
		long booking = 0;
		long inward = 0;

		String date = txtDate.getText();
		String serialPrefix = calculateStatementNumber(date, "SOD");
		txtSta.setText(serialPrefix);
		sodST = serialPrefix;
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		for (int i = 0; i < len; i++) {
			TableItem item = new TableItem(tblSod, SWT.NONE);
			item.setText(0, Integer.toString(i + 1));
			if (null != deliveries[i].getCrno())
				item.setText(14, deliveries[i].getCrno());
			if (!deliveries[i].getStatus()) {
				
				System.out.println("deliveries--->"+deliveries[i].getIsUPd().length()+"---->"+deliveries[i].getIsUPd().substring(deliveries[i].getIsUPd().indexOf(" - ")+2).trim());
				String isupd = deliveries[i].getIsUPd().substring(deliveries[i].getIsUPd().indexOf(" - ")+2).trim();
				if(deliveries[i].getIsUPd().length()>=10 && isupd.equalsIgnoreCase("Rebook")){
					item.setText(1, deliveries[i].getLrno());
					item.setText(2, "Rebooked to "+deliveries[i].getOldLrno()+" as LR.NO "+deliveries[i].getIsUPd().substring(0,deliveries[i].getIsUPd().indexOf(" - ")).trim());
					 
				}if(deliveries[i].getIsUPd().length()>=10 && isupd.equalsIgnoreCase("ToUPD")){
					item.setText(1, deliveries[i].getLrno());
					item.setText(2, "Sent to UPD as LR.NO "+deliveries[i].getIsUPd().substring(0,deliveries[i].getIsUPd().indexOf(" - ")).trim());
					 
				}else{
					
				
					float ddcfree = 0;
					float ddcbase = 0;
	
					ddcfree = deliveries[i].getDdcFree();
					ddcbase = deliveries[i].getDd();
	
					if (ddcfree == 0) {
						item.setText(8, String.valueOf((int)ddcbase));
						dd = dd + (int)ddcbase;
					} else {
						item.setText(8, String.valueOf((int)ddcfree));
						dd = dd + (int)ddcfree;
					}
					
					//if(deliveries[i].getCr_status() == 2 || deliveries[i].getLrtype().equalsIgnoreCase("Topay") ){
						item.setText(9, decimalFormat.format(deliveries[i].getDemurrage()));
						item.setText(10, decimalFormat.format(deliveries[i].getUnderCharge()));
						item.setText(11, decimalFormat.format(deliveries[i].getPostage()));
						item.setText(12, String.valueOf((int)deliveries[i].getDdExtra()));
						item.setText(13, String.valueOf((int)deliveries[i].getOthers()));				
						item.setText(15, decimalFormat.format(deliveries[i].getCrAmt()));					
				//	}					
					
					
					item.setText(1, deliveries[i].getLrno());
					item.setText(2, dateFormat.format(deliveries[i].getLrDate()));
					item.setText(3, deliveries[i].getFrom());
					item.setText(4, Integer.toString(deliveries[i]
							.getNoofarticles()));
	
					Date lrdate = deliveries[i].getLrDate();
					Date deleverdate = deliveries[i].getDelivered_date();
					long diff = deleverdate.getTime() - lrdate.getTime(); // BookingDays
					long days = (diff / (1000 * 60 * 60 * 24));
					item.setText(16, String.valueOf(days));
					booking = booking + days;
	
					Date inwarddate = deliveries[i].getInward_date();
					long diff1 = deleverdate.getTime() - inwarddate.getTime();
					long days1 = (diff1 / (1000 * 60 * 60 * 24));
					item.setText(17, String.valueOf(days1));
					inward = inward + days1;
	
					item.setText(5, decimalFormat.format(deliveries[i].getWeight()));
					weight = weight + deliveries[i].getWeight();
					item.setText(6, deliveries[i].getLrtype());
					item
							.setText(7, decimalFormat.format(deliveries[i]
									.getTotalAmount()));
	
					total = total + deliveries[i].getTotalAmount();
					article = article + deliveries[i].getNoofarticles();
	
					demu = demu + deliveries[i].getDemurrage();
	
					under = under + deliveries[i].getUnderCharge();
	
					postage = postage + deliveries[i].getPostage();
	
					ddextra = ddextra + (int) deliveries[i].getDdExtra();
	
					others = others + (int) deliveries[i].getOthers();
	
					cramt = cramt + deliveries[i].getCrAmt();
				}
			} else {
				item.setText(2, "Cancelled");
				item.setText(1, deliveries[i].getLrno());
				deliveries[i].setNoofarticles(0);
				deliveries[i].setWeight(0);
				deliveries[i].setTotalAmount(0);
				deliveries[i].setDd(0);
				deliveries[i].setDemurrage(0);
				deliveries[i].setUnderCharge(0);
				deliveries[i].setPostage(0);
				deliveries[i].setDdExtra(0);
				deliveries[i].setOthers(0);
				deliveries[i].setCrAmt(0);

			}

		}

		TableItem item1 = new TableItem(tblSod, SWT.NONE | SWT.BOLD);
		Font font1 = new Font(display, "Tahoma", 8, SWT.BOLD);
		item1.setFont(font1);

		item1.setText(3, "TOTAL");

		item1.setText(16, Long.toString(booking));
		item1.setText(17, Long.toString(inward));
		item1.setText(4, Integer.toString(article));
		item1.setText(5, decimalFormat.format(weight));
		item1.setText(7, decimalFormat.format(total));
		item1.setText(8, String.valueOf(dd));
		item1.setText(9, decimalFormat.format(demu));
		item1.setText(10, decimalFormat.format(under));
		item1.setText(11, decimalFormat.format(postage));
		item1.setText(12, String.valueOf(ddextra));
		item1.setText(13, String.valueOf(others));
		item1.setText(15, decimalFormat.format(cramt));
		
		  sortByCRNo(14, tblSod); 
		  TableItem[] items = tblSod.getItems(); 
		  for(int k = 0; k < items.length - 1; k++){ 
			 items[k].setText(0, String.valueOf(k + 1)); 
		  }
		 

		if(deliveries!= null && deliveries.length > 0){
			if(deliveries[0].getIsDRSConfirmed() == 1){
				btnprint.setEnabled(true);
				btnExportXL.setEnabled(true);
				btnExportPDF.setEnabled(true);
			}else{
				btnprint.setEnabled(false);
				btnExportXL.setEnabled(false);
				btnExportPDF.setEnabled(false);
			}			
		}
	}
	
	private void sortByCRNo(int index, Table refTable) {
		TableItem[] items = refTable.getItems();

		int value1 = 0;
		int value2 = 0;

		int len = items.length;

		// Sorting need to happen only if the number of rows available
		// is greater than one excluding total
		if (len > 2) {
			len = len - 1; // Ignoring the last row which is meant to be
			// Total

			for (int i = 1; i < len; i++) {
				if (!items[i].getText(index).isEmpty()) {
					value1 = Integer.parseInt(items[i].getText(index)
							.substring(1));
					for (int j = 0; j < i; j++) {
						value2 = Integer.parseInt(items[j].getText(index)
								.substring(1));
						if (value1 < value2) {
							String[] values = { items[i].getText(0),
									items[i].getText(1),
									items[i].getText(2),
									items[i].getText(3),
									items[i].getText(4),
									items[i].getText(5),
									items[i].getText(6),
									items[i].getText(7),
									items[i].getText(8),
									items[i].getText(9),
									items[i].getText(10),
									items[i].getText(11),
									items[i].getText(12),
									items[i].getText(13),
									items[i].getText(14),
									items[i].getText(15),
									items[i].getText(16),
									items[i].getText(17),
									items[i].getText(18)};
							items[i].dispose();
							TableItem item = new TableItem(refTable,
									SWT.NONE, j);
							item.setText(values);
							items = refTable.getItems();
							break;
						}
					}
				}
			}
		}

	}

	
	/*private void sortByCrNo(int index, Table refTable) {
		TableItem[] items = refTable.getItems();

		int value1 = 0;
		int value2 = 0;

		int len = items.length;
		if (len > 2) {
			len = len - 1;

			for (int i = 1; i < len; i++) {
				String type = items[i].getText(16);
				if (!items[i].getText(index).equals("")) {
					value1 = 0;
					for (int j = 0; j < i; j++) {
						String emp = items[j].getText(index);
						String t = items[j].getText(16);
						if (items[j].getText(index).equals("")
								&& (type.equals(items[j].getText(16)))) {
							value2 = 1;
							if (value1 < value2) {
								String[] values = { items[i].getText(0),
										items[i].getText(1),
										items[i].getText(2),
										items[i].getText(3),
										items[i].getText(4),
										items[i].getText(5),
										items[i].getText(6),
										items[i].getText(7),
										items[i].getText(8),
										items[i].getText(9),
										items[i].getText(10),
										items[i].getText(11),
										items[i].getText(12),
										items[i].getText(13),
										items[i].getText(14),
										items[i].getText(15),
										items[i].getText(16),
										items[i].getText(17) };
								items[i].dispose();
								TableItem item = new TableItem(refTable,
										SWT.NONE, j);
								item.setText(values);
								items = refTable.getItems();
								break;
							}
						}
					}
				}
			}
		}

	}*/

	/**
	 * 
	 */

	public class sortListener implements Listener {

		/**
		 * 
		 * @param index
		 * @param isOutTime
		 */
		private void sortByString(int index, Table refTable) {
			try {

				TableItem[] items = refTable.getItems();
				Collator collator = Collator.getInstance(Locale.getDefault());

				int len = items.length;

				// Sorting need to happen only if the number of rows available
				// is greater than one excluding total
				if (len > 2) {
					len--; // Ignoring the last row as it is meant to be Total

					for (int i = 1; i < len; i++) {
						if (!items[i].getText(index).isEmpty()) {
							String value1 = items[i].getText(index);
							for (int j = 0; j < i; j++) {
								String value2 = items[j].getText(index);
								if (collator.compare(value1, value2) < 0) {
									String[] values = { items[i].getText(0),
											items[i].getText(1),
											items[i].getText(2),
											items[i].getText(3),
											items[i].getText(4),
											items[i].getText(5),
											items[i].getText(6),
											items[i].getText(7),
											items[i].getText(8),
											items[i].getText(9),
											items[i].getText(10),
											items[i].getText(11),
											items[i].getText(12),
											items[i].getText(13),
											items[i].getText(14),
											items[i].getText(15) };
									items[i].dispose();

									TableItem item = new TableItem(refTable,
											SWT.NONE, j);
									item.setText(values);
									items = refTable.getItems();
									break;
								}
							}
						}
					}
					// setSerialNumber(refTable);

				}
			} catch (Exception e) {
				e.printStackTrace();
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
					len = len - 1;
					for (int i = 0; i < len; i++) {
						items[i].setText(0, String.valueOf(i + 1));
					}
				}
			}

		}

		private void sortByLrNo(int index, Table refTable) {
			try {
				TableItem[] items = refTable.getItems();

				int value1 = 0;
				int value2 = 0;

				int len = items.length;

				// Sorting need to happen only if the number of rows available
				// is greater than one excluding total
				if (len > 2) {
					len = len - 1; // Ignoring the last row which is meant to
					// be
					// Total

					for (int i = 1; i < len; i++) {
						if (!items[i].getText(index).isEmpty()) {
							value1 = Integer.parseInt(items[i].getText(index)
									.substring(1));
							for (int j = 0; j < i; j++) {
								value2 = Integer.parseInt(items[j].getText(
										index).substring(1));
								if (value1 < value2) {
									String[] values = { items[i].getText(0),
											items[i].getText(1),
											items[i].getText(2),
											items[i].getText(3),
											items[i].getText(4),
											items[i].getText(5),
											items[i].getText(6),
											items[i].getText(7),
											items[i].getText(8),
											items[i].getText(9),
											items[i].getText(10),
											items[i].getText(11),
											items[i].getText(12),
											items[i].getText(13),
											items[i].getText(14),
											items[i].getText(15) };
									items[i].dispose();
									TableItem item = new TableItem(refTable,
											SWT.NONE, j);
									item.setText(values);
									items = refTable.getItems();
									break;
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
		 * @param index
		 * @param refTable
		 */
		private void sortByDate(int index, Table refTable) {
			try {

				TableItem[] items = refTable.getItems();

				SimpleDateFormat parse = new SimpleDateFormat("dd-MM-yyyy");

				Date date1 = null;
				Date date2 = null;

				int len = items.length;

				// Sorting need to happen only if the number of rows available
				// is greater than one excluding total
				if (len > 2) {
					len--;
					for (int i = 1; i < len; i++) {
						if (!items[i].getText(index).isEmpty()) {

							date1 = parse.parse(items[i].getText(index));

							for (int j = 0; j < i; j++) {
								if (!items[j].getText(index).isEmpty()) {

									date2 = parse
											.parse(items[j].getText(index));

								}

								if (date1.before(date2)) {
									String[] values = { items[i].getText(0),
											items[i].getText(1),
											items[i].getText(2),
											items[i].getText(3),
											items[i].getText(4),
											items[i].getText(5),
											items[i].getText(6),
											items[i].getText(7),
											items[i].getText(8),
											items[i].getText(9),
											items[i].getText(10),
											items[i].getText(11),
											items[i].getText(12),
											items[i].getText(13),
											items[i].getText(14),
											items[i].getText(15) };
									items[i].dispose();
									TableItem item = new TableItem(refTable,
											SWT.NONE, j);
									item.setText(values);
									items = refTable.getItems();
									break;
								}
							}
						}
					}
				}
			} catch (Exception exp) {
				exp.printStackTrace();
			}

		}

		/**
		 * 
		 */
		private void sortByFloat(int index, Table refTable) {

			try {
				TableItem[] items = refTable.getItems();
				float value2 = 0;
				float value1 = 0;
				int len = items.length;

				// Sorting need to happen only if the number of rows available
				// is greater than one excluding total
				if (len > 2) {
					len--;
					for (int i = 1; i < len; i++) {
						if (!items[i].getText(index).isEmpty()) {

							value1 = Float.parseFloat(items[i].getText(index));

							for (int j = 0; j < i; j++) {
								if (!items[j].getText(index).isEmpty())
									value2 = Float.parseFloat(items[j]
											.getText(index));

								if (value1 < value2) {
									String[] values = { items[i].getText(0),
											items[i].getText(1),
											items[i].getText(2),
											items[i].getText(3),
											items[i].getText(4),
											items[i].getText(5),
											items[i].getText(6),
											items[i].getText(7),
											items[i].getText(8),
											items[i].getText(9),
											items[i].getText(10),
											items[i].getText(11),
											items[i].getText(12),
											items[i].getText(13),
											items[i].getText(14),
											items[i].getText(15) };

									items[i].dispose();
									TableItem item = new TableItem(refTable,
											SWT.NONE, j);
									item.setText(values);
									items = refTable.getItems();
									break;
								}
							}
						}
					}
					// setSerialNumber(refTable);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		/**
		 * 
		 */
		public void handleEvent(Event e) {

			int selectionIndex = reportsTabFolder.getSelectionIndex();
			TableColumn column = (TableColumn) e.widget;

			if (selectionIndex == 4) {
				if (column == coloutbranch) {
					sortByString(1, tbloutstanding);
				} else if (column == colStationName2) {
					sortByString(2, tbloutstanding);
				} else if (column == colcode) {
					sortByString(3, tbloutstanding);
				} else if (column == colpbdd) {
					sortByFloat(4, tbloutstanding);
				} else if (column == coltpod) {
					sortByFloat(5, tbloutstanding);
				} else if (column == colpdbooking) {
					sortByFloat(6, tbloutstanding);

				}

				else if (column == coltotal) {
					sortByFloat(7, tbloutstanding);

				}
			} else if (selectionIndex == 3) {
				if (column == colstocksno) {
					sortByFloat(0, tblstockreport);
				} else if (column == tblstocklrno) {
					sortByLrNo(1, tblstockreport);
				} else if (column == tblcolstocklrdate) {
					sortByDate(2, tblstockreport);
				} else if (column == tblcollrtype) {
					sortByString(3, tblstockreport);
				} else if (column == tblcolfrom) {
					sortByString(4, tblstockreport);
				} else if (column == tblcolstocknoa) {
					sortByFloat(5, tblstockreport);
				} else if (column == tblcolstockactual) {
					sortByFloat(6, tblstockreport);
				} else if (column == tblcolstockfreight) {
					sortByFloat(7, tblstockreport);
				} else if (column == tblcolstockarticlevalue) {
					sortByFloat(8, tblstockreport);
				} else if (column == tblcolstockarticletype) {
					sortByString(9, tblstockreport);
				} else if (column == tblcolstockinward) {
					sortByFloat(10, tblstockreport);
				}

				else if (column == tblcolstocksent) {
					sortByFloat(11, tblstockreport);
				} else if (column == tblcolstockddc) {
					sortByFloat(12, tblstockreport);
				} else if (column == tblcolstockvehicle) {
					sortByString(13, tblstockreport);
				} else if (column == tblcolstockconsignor) {
					sortByString(14, tblstockreport);
				} else if (column == tblcolstockConsignee) {
					sortByString(15, tblstockreport);
				}
			}

			else if (selectionIndex == 5) {
				if (column == colAgentSno) {
					sortByFloat(0, tblAgentReport);
				} else if (column == colAgentLrno) {
					sortByLrNo(1, tblAgentReport);
				} else if (column == colAgentLrDate) {
					sortByDate(2, tblAgentReport);
				} else if (column == colAgentFrom) {
					sortByString(3, tblAgentReport);
				} else if (column == colAgentTo) {
					sortByString(4, tblAgentReport);
				} else if (column == colAgentBookingdays) {
					sortByFloat(5, tblAgentReport);
				} else if (column == colAgentCurrentStatus) {
					sortByString(6, tblAgentReport);
				} else if (column == colAgentNoa) {
					sortByFloat(7, tblAgentReport);
				} else if (column == colAgentWeight) {
					sortByFloat(8, tblAgentReport);
				} else if (column == colAgentDDC) {
					sortByFloat(9, tblAgentReport);
				} else if (column == colAgentTotal) {
					sortByFloat(10, tblAgentReport);
				} else if (column == colAgentCnorName) {
					sortByString(11, tblAgentReport);
				} else if (column == colAgentCneeName) {
					sortByString(12, tblAgentReport);
				}
			}
			else if (selectionIndex == 6) {
				TableColumn[] cols = tblInwardRegister.getColumns();
				int len = cols.length;

				for (int i = 0; i < len; i++) {
					if (column == cols[i]) {
						if (i == 1) {
							sortByLrNo(i, tblInwardRegister);
						}else if (i == 2 || i == 4) {
							sortByDate(i, tblInwardRegister);
						}else if (i == 3 || i == 6 || i == 9 || i == 10) {
							sortByString(i, tblInwardRegister);
						} else if (i ==0 || i == 5 || i == 7 || i == 8) {
							sortByFloat(i, tblInwardRegister);
						}
					}

				}

			}
		}
	}

	/**
	 * Class for Report composite Listener
	 * 
	 * @author Administrator
	 * 
	 */
	public class ReportingListener implements SelectionListener {

		public void widgetSelected(SelectionEvent event) {

			Object source = event.getSource();
			String selectedTab = reportsTabFolder.getSelection()[0].getText();
			try {

				if (btnGo == source) {
					
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date currentDate = dateFormat.parse(txtDate
							.getText());

					if (dateFormat.format(currentDate).equals(SERVER_DATE)) {
						btnprint.setEnabled(false);
						btnExportXL.setEnabled(false);
						btnExportPDF.setEnabled(false);
					} else {
						btnprint.setVisible(true);
						btnprint.setEnabled(true);
						btnExportXL.setVisible(true);
						btnExportXL.setEnabled(true);
						btnExportPDF.setVisible(true);
						btnExportPDF.setEnabled(true);
					}

					if ("Inward Register".equals(selectedTab)){
						String statementNo = calculateStatementNumber(txtDate
								.getText(), "IR");
						if (null != statementNo)
							txtSta.setText(statementNo);
					}else{
						String statementNo = calculateStatementNumber(txtDate
								.getText(), selectedTab);
						if (null != statementNo)
							txtSta.setText(statementNo);
					}
					
					
					if ("SOB".equals(selectedTab)) {

						populateSOBRecords(true);
					} else if ("SOD".equals(selectedTab)) {

						populateSODRecords(true);
					} else if ("DDR".equals(selectedTab)) {
						populateDDRRecords(true);
					} else if ("Inward Register".equals(selectedTab)) {
						populateIRRecords(true);
					}else if (selectedTab.equals(REFUND_TAB)) {
						SimpleDateFormat format = new SimpleDateFormat("MM-yyyy");
						Date date = format.parse(txtMonthRef.getText());
						RefundDTO = comnHandler.getRefundList(date);
						if (tblRefund != null)
							tblRefund.removeAll();
						if (RefundDTO != null) {
							btnExportXL.setEnabled(true);
							btnExportPDF.setEnabled(true);
							btnprint.setEnabled(true);
							fillRefundValues(RefundDTO);
						}

					} else if (selectedTab.equals(RECOVERY_TAB)) {
						SimpleDateFormat format = new SimpleDateFormat("MM-yyyy");
						Date date = format.parse(txtMonth.getText());
						RecoveryDTO = comnHandler.getRecoveryList(date);
						if (tblRecovery != null)
							tblRecovery.removeAll();
						if (RecoveryDTO != null) {
							btnExportXL.setEnabled(true);
							btnExportPDF.setEnabled(true);
							btnprint.setEnabled(true);
							fillRecoveryValues(RecoveryDTO);
						}

					}
					
					

				} else if (reportsTabFolder == source) {
					label4.setVisible(false);
					txtToDate.setVisible(false);
					btnRepToDate.setVisible(false);
					
					if ( (BeanUtil.getDbName() == null || BeanUtil.getDbName().equals("")) && 
							("STOCK".equals(selectedTab)
							|| "OUTSTANDING REPORT".equals(selectedTab)
							|| "Current Status".equalsIgnoreCase(selectedTab))) {
						lblSta.setVisible(false);
						txtSta.setVisible(false);
						label3.setVisible(false);
						txtDate.setVisible(false);
						btnGo.setVisible(false);
					} else {
						lblSta.setVisible(true);
						txtSta.setVisible(true);
						label3.setVisible(true);
						txtDate.setVisible(true);
						btnRepDate.setVisible(true);
						btnGo.setVisible(true);
					}
					if ("SOB".equals(selectedTab)) {
						
						lblName.setText("STATEMENT OF BOOKING");
						populateSOBRecords(false);
					}

					else if ("SOD".equals(selectedTab)) {
						
						lblName.setText("STATEMENT OF DELIVERY");
						populateSODRecords(false);
					}

					else if ("DDR".equals(selectedTab)) {
						
						lblName.setText("STATEMENT OF DD REIMBURSEMENT");
						populateDDRRecords(false);
					} else if ((BeanUtil.getDbName() == null || BeanUtil.getDbName().equals("")) && 
							 "STOCK".equals(selectedTab)) {
						
						ArrayList list = handler.getStockDetails(handler
								.getStationCode());
						lblName.setText("STOCK STATEMENT");

						populateStockDetails(list);

					} else if ((BeanUtil.getDbName() == null || BeanUtil.getDbName().equals("")) && 
							 "OUTSTANDING REPORT".equals(selectedTab)) {
						
						populateOutstandingReport();
					} /*
						 * else if ("Card Rate".equals(selectedTab)) {
						 * populateCardRateReport(); }
						 */
					else if ((BeanUtil.getDbName() == null || BeanUtil.getDbName().equals("")) && 
							 "Current Status".equalsIgnoreCase(selectedTab)) {
						try {
							
							StationsDTO[] dto = handler
									.getAllAssociatedStations();
							if (null != cbCurrentStatus) {
								cbCurrentStatus.removeAll();
							}
							if (null != dto) {
								// Add all station to Commbo
								for (int i = 0; i < dto.length; i++) {
									cbCurrentStatus.add(dto[i].getName()
											+ " - " + dto[i].getId());
								}

							} else {
								lblCurrentStatus.setVisible(false);
								cbCurrentStatus.setVisible(false);
								btnCurrentGo.setVisible(false);
							}
							populateCurrentStatusReport(handler
									.getStationCode());
						} catch (Exception exception) {
							exception.printStackTrace();

						}
					}else if ("Inward Register".equals(selectedTab)) {
						label4.setVisible(true);
						txtToDate.setVisible(true);
						btnRepToDate.setVisible(true);
						
						btnprint.setVisible(true);
						lblName.setText("Inward Register");
						//populateIRRecords(false);
					}else if (REFUND_TAB.equals(selectedTab) || RECOVERY_TAB.equals(selectedTab)){
						lblSta.setVisible(false);
						txtSta.setVisible(false);
						label3.setVisible(false);
						txtDate.setVisible(false);
						btnRepDate.setVisible(false);
					}

				} else if (source == btnoutstanding) {
					try {
						populateOutstandingReport();
					} catch (Exception e) {
						e.printStackTrace();
					}

				} 
				
				 else if (btnprint == source) {
					if ("SOB".equals(selectedTab)) {
						try {
							String stno = txtSta.getText();
							handler.printStatementOfBooking(bookings, txtDate
									.getText(), sobST, totaltopay, totalpaid,
									totalbillng);
							btnprint.setEnabled(false);
						} catch (Exception exception) {
							displayError("Problem while printing");
						}

					} else if ("SOD".equals(selectedTab)) {
						try {
							SODDTO[] sto = buildSODDTO();
							handler.printStatementOfDelivery(sto, txtDate
									.getText(), sodST);
							btnprint.setEnabled(false);
						} catch (Exception exception) {
							exception.printStackTrace();
							displayError("Problem while printing");
						}
					} else if ("DDR".equals(selectedTab)) {
						try {
							handler.printDoorDeliveryReimbursement(door,
									txtDate.getText(), soddrST);
							btnprint.setEnabled(false);
						} catch (Exception exception) {
							displayError("Problem while printing");
						}
					} else if ("STOCK".equals(selectedTab)) {
						GMRInTimeDTO[] dto = buildStockDTO();
						try {
							handler.printStockDetails(dto,"STOCK");
							btnprint.setEnabled(false);
						} catch (Exception exception) {
							displayError("Problem while printing");
						}
					}else if ("OUTSTANDING REPORT".equals(selectedTab)) {
						OutstandingDTO[] dto = buildOutstandingDTO();
						int index = -1;
						String selectednames = null;
						index = cbbranch.getSelectionIndex();
						
						if (index > -1) {
							selectednames = cbbranch.getItem(index);
						}
						try {
							handler.printOutstandingDetails(dto,selectednames,"OUTSTANDING REPORT");
							btnprint.setEnabled(false);
							
						} catch (Exception exception) {
							displayError("Problem while printing");
						}
					}else if ("Current Status".equals(selectedTab)) {
						int index = -1;
						index = cbCurrentStatus.getSelectionIndex();
						
						String Currentstation = null;
						if(index  > -1){
							Currentstation = cbCurrentStatus.getItem(index);
						}
						 else
						 {
							 Currentstation = handler.getStationCode();
						 }
						CurrentStatusDTO[] dto = buildCurrentStatusDTO();
						
						try {
							
							handler.printCurrentstatusDetails(dto,"Current Status",Currentstation);
							btnprint.setEnabled(false);
							
						} catch (Exception exception) {
							displayError("Problem while printing");
						}
					}else if ("Inward Register".equals(selectedTab)) {
						ArrayList<GMROutTimeDTO> dto = buildInwardDTO();
						try {
							handler.printInwardRegister(dto,irST,txtDate.getText(),txtToDate.getText(),"Inward Register");
							btnprint.setEnabled(false);
						} catch (Exception exception) {
							displayError("Problem while Printing");
						}
					}else if ("Refund".equals(selectedTab)) {
						ArrayList<RefundRecoveryDTO> list = null;
						list = buildRefundDTO();
						try {
							handler.printRefund(list,txtMonthRef.getText(),"Refund");
							btnprint.setEnabled(false);
						} catch (Exception exception) {
							displayError("Problem while Printing");
						}
					}else if ("Recovery".equals(selectedTab)) {
						ArrayList<RefundRecoveryDTO> list = null;
						list = buildRecoveryDTO();
						try {
							handler.printRecovery(list,txtMonth.getText(),"Recovery");
							btnprint.setEnabled(false);
						} catch (Exception exception) {
							displayError("Problem while Printing");
						}
					}
					
				}else if (btnExportXL == source) {
					if ("SOB".equals(selectedTab)) {
						try {
							String stno = txtSta.getText();
							prepareXLStatementOfBooking(bookings, txtDate
									.getText(), sobST, totaltopay, totalpaid,
									totalbillng,"SOB");
							
							btnExportXL.setEnabled(false);
							
						} catch (Exception exception) {
							displayError("Problem while Exporting Excel");
						}

					} else if ("SOD".equals(selectedTab)) {
						try {
							SODDTO[] sto = buildSODDTO();
							prepareXLStatementOfDelivery(sto, txtDate
									.getText(), sodST,"SOD");
							
							btnExportXL.setEnabled(false);
							
						} catch (Exception exception) {
							exception.printStackTrace();
							displayError("Problem while Exporting Excel");
						}
					} else if ("DDR".equals(selectedTab)) {
						try {
								prepareXLDoorDeliveryReimbursement(door,
									txtDate.getText(), soddrST,"DDR");
						
							btnExportXL.setEnabled(false);
						
						} catch (Exception exception) {
							displayError("Problem while Exporting Excel");
						}
					} else if ("STOCK".equals(selectedTab)) {
						GMRInTimeDTO[] dto = buildStockDTO();
						try {
							prepareXLStockDetails(dto,"STOCK");
							
							btnExportXL.setEnabled(false);
							
						} catch (Exception exception) {
							displayError("Problem while Exporting Excel");
						}
					}else if ("OUTSTANDING REPORT".equals(selectedTab)) {
						int index = -1;
						String selectednames = null;
						OutstandingDTO[] dto = buildOutstandingDTO();
						index = cbbranch.getSelectionIndex();
						if (index > -1) {
							selectednames = cbbranch.getItem(index);
						}
						try {
							prepareXLOutstandingDetails(dto,"OUTSTANDING REPORT",selectednames);
							
							btnExportXL.setEnabled(false);
							
						} catch (Exception exception) {
							displayError("Problem while Exporting Excel");
						}
					}else if ("Current Status".equals(selectedTab)) {
						int index = -1;
						String Currentstation = null;
						 index = cbCurrentStatus.getSelectionIndex();
						 
						 CurrentStatusDTO[] dto = buildCurrentStatusDTO();
						 if (index > -1) {
							 Currentstation = cbCurrentStatus.getItem(index);
						 }
						 else
						 {
							 Currentstation = handler.getStationCode();
						 }
						try {
							prepareXLCurrentstatusDetails(dto,"Current Status",Currentstation);
							
							btnExportXL.setEnabled(false);
							
						} catch (Exception exception) {
							displayError("Problem while Exporting Excel");
						}
					}else if ("Inward Register".equals(selectedTab)) {
						ArrayList<GMROutTimeDTO> dto = buildInwardDTO();
						try {
							prepareXLInward(dto,irST,txtDate.getText(),txtToDate.getText(),"Inward Register");
							btnExportXL.setEnabled(false);
						} catch (Exception exception) {
							displayError("Problem while Exporting Excel");
						}
					}else if ("Refund".equals(selectedTab)) {
						ArrayList<RefundRecoveryDTO> list = null;
						list = buildRefundDTO();
						try {
							prepareXLRefund(list,txtMonthRef.getText(),"Refund");
							btnExportXL.setEnabled(false);
						} catch (Exception exception) {
							displayError("Problem while Exporting Excel");
						}
					}else if ("Recovery".equals(selectedTab)) {
						ArrayList<RefundRecoveryDTO> list = null;
						list = buildRecoveryDTO();
						try {
							prepareXLRecovery(list,txtMonth.getText(),"Recovery");
							btnExportXL.setEnabled(false);
						} catch (Exception exception) {
							displayError("Problem while Exporting Excel");
						}
					}

				}
				
				else if (btnExportPDF == source) {
					if ("SOB".equals(selectedTab)) {
						try {
							String stno = txtSta.getText();
							prepareSOBPDF(bookings, txtDate
									.getText(), sobST, totaltopay, totalpaid,
									totalbillng,"SOB");
							
							btnExportPDF.setEnabled(false);
						} catch (Exception exception) {
							displayError("Problem while Exporting PDF");
						}

					} else if ("SOD".equals(selectedTab)) {
						try {
							SODDTO[] sto = buildSODDTO();
							prepareSODPDF(sto, txtDate
									.getText(), sodST,"SOD");
							
							btnExportPDF.setEnabled(false);
						} catch (Exception exception) {
							exception.printStackTrace();
							displayError("Problem while Exporting PDF");
						}
					} else if ("DDR".equals(selectedTab)) {
						try {
							prepareDDRPDF(door,
									txtDate.getText(), soddrST,"DDR");
							
							btnExportPDF.setEnabled(false);
						} catch (Exception exception) {
							displayError("Problem while Exporting PDF");
						}
					} else if ("STOCK".equals(selectedTab)) {
						GMRInTimeDTO[] dto = buildStockDTO();
						try {
							prepareStockPDF(dto,"STOCK");
							
							btnExportPDF.setEnabled(false);
						} catch (Exception exception) {
							displayError("Problem while Exporting PDF");
						}
					}else if ("OUTSTANDING REPORT".equals(selectedTab)) {
						int index = -1;
						String selectednames = null;
						OutstandingDTO[] dto = buildOutstandingDTO();
						index = cbbranch.getSelectionIndex();
						if (index > -1) {
							selectednames = cbbranch.getItem(index);
						}
						try {
							prepareOutStandingPDF(dto,"OUTSTANDING REPORT",selectednames);
							
							btnExportPDF.setEnabled(false);
						} catch (Exception exception) {
							displayError("Problem while Exporting PDF");
						}
					}else if ("Current Status".equals(selectedTab)) {
						int index = -1;
						 String Currentstation = null;
						 index = cbCurrentStatus.getSelectionIndex();
						 CurrentStatusDTO[] dto = buildCurrentStatusDTO();
						 if(index > -1){
							 Currentstation = cbCurrentStatus.getItem(index);	
						 }
						 else
						 {
							 Currentstation = handler.getStationCode();
						 }
						try {
							prepareCurrentStatusPDF(dto,"Current Status",Currentstation);
							
							btnExportPDF.setEnabled(false);
						} catch (Exception exception) {
							displayError("Problem while Exporting PDF");
						}
					}else if ("Inward Register".equals(selectedTab)) {
						ArrayList<GMROutTimeDTO> dto = buildInwardDTO();
						try {
							preparePDFInward(dto,irST,txtDate.getText(),txtToDate.getText(),"Inward Register");
							btnExportPDF.setEnabled(false);
						} catch (Exception exception) {
							displayError("Problem while Exporting PDF");
						}
					}else if ("Refund".equals(selectedTab)) {
						ArrayList<RefundRecoveryDTO> list = null;
						list = buildRefundDTO();
						try {
							preparePDFRefund(list,txtMonthRef.getText(),"Refund");
							btnExportPDF.setEnabled(false);
						} catch (Exception exception) {
							displayError("Problem while Exporting PDF");
						}
					}else if ("Recovery".equals(selectedTab)) {
						ArrayList<RefundRecoveryDTO> list = null;
						list = buildRecoveryDTO();
						try {
							preparePDFRecovery(list,txtMonth.getText(),"Recovery");
							btnExportPDF.setEnabled(false);
						} catch (Exception exception) {
							displayError("Problem while Exporting PDF");
						}
					}

				}

				
				
				else if (source == btnCurrentGo) {
					try {
						int index = cbCurrentStatus.getSelectionIndex();
						if (index > -1) {
							String station = cbCurrentStatus.getItem(index);
							index = station.indexOf(" - ");
							station = (station.substring(index + 2)).trim();
							populateCurrentStatusReport(station);
						} else {
							displayError("Please Select a Station");
						}
					} catch (Exception exception) {
						exception.printStackTrace();
					}

				}
				
				if (BeanUtil.getDbName() != null && !BeanUtil.getDbName().equals("")){
					if(tbloutstanding != null)
						tbloutstanding.setVisible(false);
					if(tblstockreport != null)
						tblstockreport.setVisible(false);
					if(tblAgentReport != null)
						tblAgentReport.setVisible(false);
					if(cbbranch != null)
						cbbranch.setVisible(false);
					if(cbCurrentStatus != null)
						cbCurrentStatus.setVisible(false);
					if(btnCurrentGo != null)
						btnCurrentGo.setVisible(false);
					if(lblCurrentStatus != null)
						lblCurrentStatus.setVisible(false);
					if(btnoutstanding != null)
						btnoutstanding.setVisible(false);
					
				}

			} catch (Exception exception) {
				exception.printStackTrace();
				displayError("Error occurred while printing");
			}

		}
		
		
		






		private void preparePDFRecovery(ArrayList<RefundRecoveryDTO> list,
				String month, String fileName) {
			// TODO Auto-generated method stub
			try {
				/*AdminComposite.display("Exporting...", STATUS_SUCCESS,
						SUCCESS_FONT_COLOR);*/
				handler.printRecoveryPDF(list,month,fileName);

				FileDialog dialog = new FileDialog(shell, SWT.SAVE);
				dialog.setFilterExtensions(new String[] { "*.pdf" });
				dialog.setFilterNames(new String[] { "*.pdf" });
				dialog.setFilterPath("WINDOWS/Desktop"); // Windows path
				dialog.setFileName(fileName);
				String filepath = dialog.open();

				if (null != filepath) {
					File df = new File(filepath);
					File xl = new File("lib/" + fileName + ".pdf");

					if (xl.exists()) {
						//AdminComposite.display("Saving...", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						copyFile(xl, df, dialog.getFileName());
					}
					/*AdminComposite.display("PDF Saved Successfully",
							STATUS_SUCCESS, SUCCESS_FONT_COLOR);*/
				}

			} catch (Exception e) {
				//AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				e.printStackTrace();
				displayError("Status Success");
			}
		}
		private void preparePDFRefund(ArrayList<RefundRecoveryDTO> list,
				String month, String fileName) {
			// TODO Auto-generated method stub
			try {
				/*AdminComposite.display("Exporting...", STATUS_SUCCESS,
						SUCCESS_FONT_COLOR);*/
				handler.printRefundPDF(list,month,fileName);

				FileDialog dialog = new FileDialog(shell, SWT.SAVE);
				dialog.setFilterExtensions(new String[] { "*.pdf" });
				dialog.setFilterNames(new String[] { "*.pdf" });
				dialog.setFilterPath("WINDOWS/Desktop"); // Windows path
				dialog.setFileName(fileName);
				String filepath = dialog.open();

				if (null != filepath) {
					File df = new File(filepath);
					File xl = new File("lib/" + fileName + ".pdf");

					if (xl.exists()) {
						//AdminComposite.display("Saving...", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						copyFile(xl, df, dialog.getFileName());
					}
					/*AdminComposite.display("PDF Saved Successfully",
							STATUS_SUCCESS, SUCCESS_FONT_COLOR);*/
				}

			} catch (Exception e) {
				//AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				e.printStackTrace();
				displayError("Status Success");
			}
			
		}
		private void preparePDFInward(ArrayList<GMROutTimeDTO> list,
				String irSt, String fromdate, String todate, String fileName) {
			// TODO Auto-generated method stub
			try {
				/*AdminComposite.display("Exporting...", STATUS_SUCCESS,
						SUCCESS_FONT_COLOR);*/
				handler.printInwardPDF(list,irSt,fromdate,todate,fileName);

				FileDialog dialog = new FileDialog(shell, SWT.SAVE);
				dialog.setFilterExtensions(new String[] { "*.pdf" });
				dialog.setFilterNames(new String[] { "*.pdf" });
				dialog.setFilterPath("WINDOWS/Desktop"); // Windows path
				dialog.setFileName(fileName);
				String filepath = dialog.open();

				if (null != filepath) {
					File df = new File(filepath);
					File xl = new File("lib/" + fileName + ".pdf");

					if (xl.exists()) {
						//AdminComposite.display("Saving...", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						copyFile(xl, df, dialog.getFileName());
					}
					/*AdminComposite.display("PDF Saved Successfully",
							STATUS_SUCCESS, SUCCESS_FONT_COLOR);*/
				}

			} catch (Exception e) {
				//AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				e.printStackTrace();
				displayError("Status Success");
			}
			
		}









		private void prepareXLRecovery(ArrayList dto,String month,  String fileName)throws Exception {
			// TODO Auto-generated method stub
			try {
				/*ReportsComposite.display("Exporting...", STATUS_SUCCESS,
				SUCCESS_FONT_COLOR);*/
					handler.printRecoveryReportExcel(dto,month,fileName);

					FileDialog dialog = new FileDialog(shell, SWT.SAVE);
					
					dialog.setFilterExtensions(new String[] { "*.xls" });
					dialog.setFilterNames(new String[] { "*.xls" });
					dialog.setFilterPath("WINDOWS/Desktop"); // Windows path
					dialog.setFileName(fileName);
					String filepath = dialog.open();

					if (null != filepath) {
						File df = new File(filepath);
						File xl = new File("lib/" + fileName + ".xls");

						if (xl.exists()) {
							//	AdminComposite.display("Saving...", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
							copyFile(xl, df, dialog.getFileName());
						}
						/*AdminComposite.display("Excel Saved Successfully",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);*/
					}
					/*else
					{
					AdminComposite.display("Excel Saving Cancelled",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					}*/
				} catch (Exception e) {
					//	AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					e.printStackTrace();
					displayError("Status Success");
				}

		}









		private void prepareXLRefund(ArrayList dto, String month, String fileName)throws Exception {
			// TODO Auto-generated method stub
			try {
				/*ReportsComposite.display("Exporting...", STATUS_SUCCESS,
				SUCCESS_FONT_COLOR);*/
					handler.printRefundReportExcel(dto,month, fileName);

					FileDialog dialog = new FileDialog(shell, SWT.SAVE);
					
					dialog.setFilterExtensions(new String[] { "*.xls" });
					dialog.setFilterNames(new String[] { "*.xls" });
					dialog.setFilterPath("WINDOWS/Desktop"); // Windows path
					dialog.setFileName(fileName);
					String filepath = dialog.open();

					if (null != filepath) {
						File df = new File(filepath);
						File xl = new File("lib/" + fileName + ".xls");

						if (xl.exists()) {
							//	AdminComposite.display("Saving...", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
							copyFile(xl, df, dialog.getFileName());
						}
						/*AdminComposite.display("Excel Saved Successfully",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);*/
					}
					/*else
					{
					AdminComposite.display("Excel Saving Cancelled",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					}*/
				} catch (Exception e) {
					//	AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					e.printStackTrace();
					displayError("Status Success");
				}

		}









		private void prepareXLInward(ArrayList dto, String irSt, String fromdate, String todate, String fileName)throws Exception {
			// TODO Auto-generated method stub
			try {
				/*ReportsComposite.display("Exporting...", STATUS_SUCCESS,
				SUCCESS_FONT_COLOR);*/
					handler.printInwardReportExcel(dto,irSt,fromdate,todate, fileName);

					FileDialog dialog = new FileDialog(shell, SWT.SAVE);
					
					dialog.setFilterExtensions(new String[] { "*.xls" });
					dialog.setFilterNames(new String[] { "*.xls" });
					dialog.setFilterPath("WINDOWS/Desktop"); // Windows path
					dialog.setFileName(fileName);
					String filepath = dialog.open();

					if (null != filepath) {
						File df = new File(filepath);
						File xl = new File("lib/" + fileName + ".xls");

						if (xl.exists()) {
							//	AdminComposite.display("Saving...", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
							copyFile(xl, df, dialog.getFileName());
						}
						/*AdminComposite.display("Excel Saved Successfully",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);*/
					}
					/*else
					{
					AdminComposite.display("Excel Saving Cancelled",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					}*/
				} catch (Exception e) {
					//	AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					e.printStackTrace();
					displayError("Status Success");
				}

		}









		private ArrayList<RefundRecoveryDTO> buildRecoveryDTO() throws Exception{
			// TODO Auto-generated method stub
			RefundRecoveryDTO dto = null;
			ArrayList<RefundRecoveryDTO> list = new ArrayList<RefundRecoveryDTO>();
			int length = 0;
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			TableItem[] items = tblRecovery.getItems();
			if (items != null) {
				length = items.length;
			}
			if (length > 0) {
				for (int i = 0; i < length; i++) {
					dto = new RefundRecoveryDTO();
					
					dto.setDescription(items[i].getText(1));
					
					dto.setContacts(items[i].getText(2));
					
					if (!items[i].getText(3).equals(""))
						dto.setAmount(Float.parseFloat(items[i].getText(3))); 
				
					dto.setInstallment(items[i].getText(4));
					
					if (!items[i].getText(5).equals(""))
						dto.setRr_date(dateFormat.parse(items[i].getText(5)));
					
					
					list.add(dto);
				}
			}

			return list;
		}









		private ArrayList<RefundRecoveryDTO> buildRefundDTO() throws Exception{
			// TODO Auto-generated method stub
			RefundRecoveryDTO dto = null;
			ArrayList<RefundRecoveryDTO> list = new ArrayList<RefundRecoveryDTO>();
			int length = 0;
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			TableItem[] items = tblRefund.getItems();
			if (items != null) {
				length = items.length;
			}
			if (length > 0) {
				for (int i = 0; i < length; i++) {
					dto = new RefundRecoveryDTO();
					
					dto.setDescription(items[i].getText(1));
					
					dto.setContacts(items[i].getText(2));
					
					if (!items[i].getText(3).equals(""))
						dto.setAmount(Float.parseFloat(items[i].getText(3))); 
				
					dto.setInstallment(items[i].getText(4));
					
					if (!items[i].getText(5).equals(""))
						dto.setRr_date(dateFormat.parse(items[i].getText(5)));
					
					
						
					list.add(dto);
				}
			}

			return list;
		}









		private void prepareXLCurrentstatusDetails(CurrentStatusDTO[] dto,
				String fileName,String Currentstation) {
			// TODO Auto-generated method stub
				try {
				
				handler.printCurrentstatusReportExcel(dto, fileName,Currentstation);

				FileDialog dialog = new FileDialog(shell, SWT.SAVE);
				dialog.setFilterExtensions(new String[] { "*.xls" });
				dialog.setFilterNames(new String[] { "*.xls" });
				dialog.setFilterPath("WINDOWS/Desktop"); // Windows path
				dialog.setFileName(fileName);
				String filepath = dialog.open();

				if (null != filepath) {
					File df = new File(filepath);
					File xl = new File("lib/" + fileName + ".xls");

					if (xl.exists()) {
						//AdminComposite.display("Saving...", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						copyFile(xl, df, dialog.getFileName());
					}
					/*AdminComposite.display("Excel Saved Successfully",
							STATUS_SUCCESS, SUCCESS_FONT_COLOR);*/
				}
				/*else
				{
					AdminComposite.display("Excel Saving Cancelled",
							STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				}*/
			} catch (Exception e) {
				//AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				e.printStackTrace();
				displayError("Status Success");
			}


			
		}



		private void prepareXLOutstandingDetails(OutstandingDTO[] dto,
				String fileName, String selectednames) {
			// TODO Auto-generated method stub
			try {
				
				handler.printOutstandingReportExcel(dto, fileName,selectednames);

				FileDialog dialog = new FileDialog(shell, SWT.SAVE);
				dialog.setFilterExtensions(new String[] { "*.xls" });
				dialog.setFilterNames(new String[] { "*.xls" });
				dialog.setFilterPath("WINDOWS/Desktop"); // Windows path
				dialog.setFileName(fileName);
				String filepath = dialog.open();

				if (null != filepath) {
					File df = new File(filepath);
					File xl = new File("lib/" + fileName + ".xls");

					if (xl.exists()) {
						//AdminComposite.display("Saving...", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						copyFile(xl, df, dialog.getFileName());
					}
					/*AdminComposite.display("Excel Saved Successfully",
							STATUS_SUCCESS, SUCCESS_FONT_COLOR);*/
				}
				/*else
				{
					AdminComposite.display("Excel Saving Cancelled",
							STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				}*/
			} catch (Exception e) {
				//AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				e.printStackTrace();
				displayError("Status Success");
			}

			
			
		}



		private void prepareXLStatementOfDelivery(SODDTO[] sto, String text,
				String sodST, String fileName) {
			
			// TODO Auto-generated method stub
			try {
			/*ReportsComposite.display("Exporting...", STATUS_SUCCESS,
			SUCCESS_FONT_COLOR);*/
				handler.printSODReportExcel(sto,txtDate
						.getText(),	 sodST, fileName);

				FileDialog dialog = new FileDialog(shell, SWT.SAVE);
				
				dialog.setFilterExtensions(new String[] { "*.xls" });
				dialog.setFilterNames(new String[] { "*.xls" });
				dialog.setFilterPath("WINDOWS/Desktop"); // Windows path
				dialog.setFileName(fileName);
				String filepath = dialog.open();

				if (null != filepath) {
					File df = new File(filepath);
					File xl = new File("lib/" + fileName + ".xls");

					if (xl.exists()) {
						//	AdminComposite.display("Saving...", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						copyFile(xl, df, dialog.getFileName());
					}
					/*AdminComposite.display("Excel Saved Successfully",
					STATUS_SUCCESS, SUCCESS_FONT_COLOR);*/
				}
				/*else
				{
				AdminComposite.display("Excel Saving Cancelled",
					STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				}*/
			} catch (Exception e) {
				//	AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				e.printStackTrace();
				displayError("Status Success");
			}

			
	}
		
		private void prepareXLDoorDeliveryReimbursement(DDRDTO[] door,
				String date, String soddrST,String fileName)throws Exception
		
		{
			try {
				
				handler.printDDRReportExcel(door, txtDate
						.getText(), soddrST,fileName);

				FileDialog dialog = new FileDialog(shell, SWT.SAVE);
				dialog.setFilterExtensions(new String[] { "*.xls" });
				dialog.setFilterNames(new String[] { "*.xls" });
				dialog.setFilterPath("WINDOWS/Desktop"); // Windows path
				dialog.setFileName(fileName);
				String filepath = dialog.open();

				if (null != filepath) {
					File df = new File(filepath);
					File xl = new File("lib/" + fileName + ".xls");

					if (xl.exists()) {
						//AdminComposite.display("Saving...", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						copyFile(xl, df, dialog.getFileName());
					}
					/*AdminComposite.display("Excel Saved Successfully",
							STATUS_SUCCESS, SUCCESS_FONT_COLOR);*/
				}
				/*else
				{
					AdminComposite.display("Excel Saving Cancelled",
							STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				}*/
			} catch (Exception e) {
				//AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				e.printStackTrace();
				displayError("Status Success");
			}

			
		}

		private void prepareXLStatementOfBooking(BookingDTO[] dto, String date,
				String stno, float totaltopay, float totalpaid, float totalbilling, String fileName) throws Exception{
			try {
				/*ReportsComposite.display("Exporting...", STATUS_SUCCESS,
						SUCCESS_FONT_COLOR);*/
				handler.printSOBReportExcel(bookings, txtDate
						.getText(), sobST, totaltopay, totalpaid,
						totalbillng,fileName);

				FileDialog dialog = new FileDialog(shell, SWT.SAVE);
				dialog.setFilterExtensions(new String[] { "*.xls" });
				dialog.setFilterNames(new String[] { "*.xls" });
				dialog.setFilterPath("WINDOWS/Desktop"); // Windows path
				dialog.setFileName(fileName);
				String filepath = dialog.open();

				if (null != filepath) {
					File df = new File(filepath);
					File xl = new File("lib/" + fileName + ".xls");

					if (xl.exists()) {
						//AdminComposite.display("Saving...", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						copyFile(xl, df, dialog.getFileName());
					}
					/*AdminComposite.display("Excel Saved Successfully",
							STATUS_SUCCESS, SUCCESS_FONT_COLOR);*/
				}
				/*else
				{
					AdminComposite.display("Excel Saving Cancelled",
							STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				}*/
			} catch (Exception e) {
				//AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				e.printStackTrace();
				displayError("Status Success");
			}

		}
		
		private void prepareXLStockDetails(GMRInTimeDTO[]  dto,  String fileName) throws Exception{
			try {
				/*ReportsComposite.display("Exporting...", STATUS_SUCCESS,
						SUCCESS_FONT_COLOR);*/
				handler.printSTOCKReportExcel(dto, fileName);

				FileDialog dialog = new FileDialog(shell, SWT.SAVE);
				dialog.setFilterExtensions(new String[] { "*.xls" });
				dialog.setFilterNames(new String[] { "*.xls" });
				dialog.setFilterPath("WINDOWS/Desktop"); // Windows path
				dialog.setFileName(fileName);
				String filepath = dialog.open();

				if (null != filepath) {
					File df = new File(filepath);
					File xl = new File("lib/" + fileName + ".xls");

					if (xl.exists()) {
						//AdminComposite.display("Saving...", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						copyFile(xl, df, dialog.getFileName());
					}
					/*AdminComposite.display("Excel Saved Successfully",
							STATUS_SUCCESS, SUCCESS_FONT_COLOR);*/
				}
				/*else
				{
					AdminComposite.display("Excel Saving Cancelled",
							STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				}*/
			} catch (Exception e) {
				//AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				e.printStackTrace();
				displayError("Status Success");
			}

		}
		
		public void copyFile(File source, File dest, String fileName)
		throws IOException {

			if (!dest.exists()) {
				dest.createNewFile();
			} else {
				dest.setWritable(true);

			}

			InputStream in = null;

			OutputStream out = null;

			try {

				in = new FileInputStream(source);

				out = new FileOutputStream(dest);
				
				byte[] buf = new byte[1024];

				int len;

				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}

			}

			finally {
				if (in != null)
					in.close();
				if (out != null)
					out.close();
			}

		}
		
		private void prepareOutStandingPDF(OutstandingDTO[] dto, String fileName,
				String selectednames) {
			// TODO Auto-generated method stub
			try {
				/*AdminComposite.display("Exporting...", STATUS_SUCCESS,
						SUCCESS_FONT_COLOR);*/
				handler.printOutStandingReportPDF(dto,fileName,selectednames);

				FileDialog dialog = new FileDialog(shell, SWT.SAVE);
				dialog.setFilterExtensions(new String[] { "*.pdf" });
				dialog.setFilterNames(new String[] { "*.pdf" });
				dialog.setFilterPath("WINDOWS/Desktop"); // Windows path
				dialog.setFileName(fileName);
				String filepath = dialog.open();

				if (null != filepath) {
					File df = new File(filepath);
					File xl = new File("lib/" + fileName + ".pdf");

					if (xl.exists()) {
						//AdminComposite.display("Saving...", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						copyFile(xl, df, dialog.getFileName());
					}
					/*AdminComposite.display("PDF Saved Successfully",
							STATUS_SUCCESS, SUCCESS_FONT_COLOR);*/
				}

			} catch (Exception e) {
				//AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				e.printStackTrace();
				displayError("Status Success");
			}

			
		}



		private void prepareCurrentStatusPDF(CurrentStatusDTO[] dto, String fileName,String Currentstation) {
			// TODO Auto-generated method stub
			try {
				/*AdminComposite.display("Exporting...", STATUS_SUCCESS,
						SUCCESS_FONT_COLOR);*/
				handler.printCurrentStatusReportPDF(dto,fileName,Currentstation);

				FileDialog dialog = new FileDialog(shell, SWT.SAVE);
				dialog.setFilterExtensions(new String[] { "*.pdf" });
				dialog.setFilterNames(new String[] { "*.pdf" });
				dialog.setFilterPath("WINDOWS/Desktop"); // Windows path
				dialog.setFileName(fileName);
				String filepath = dialog.open();

				if (null != filepath) {
					File df = new File(filepath);
					File xl = new File("lib/" + fileName + ".pdf");

					if (xl.exists()) {
						//AdminComposite.display("Saving...", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						copyFile(xl, df, dialog.getFileName());
					}
					/*AdminComposite.display("PDF Saved Successfully",
							STATUS_SUCCESS, SUCCESS_FONT_COLOR);*/
				}

			} catch (Exception e) {
				//AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				e.printStackTrace();
				displayError("Status Success");
			}

		}
		
		private void prepareSOBPDF(BookingDTO[] dto, String date,
				String stno, float totaltopay, float totalpaid, float totalbilling, String fileName) {
			try {
				/*AdminComposite.display("Exporting...", STATUS_SUCCESS,
						SUCCESS_FONT_COLOR);*/
				handler.printSOBReportPDF(dto, txtDate
						.getText(), sobST, totaltopay, totalpaid,
						totalbillng,fileName);

				FileDialog dialog = new FileDialog(shell, SWT.SAVE);
				dialog.setFilterExtensions(new String[] { "*.pdf" });
				dialog.setFilterNames(new String[] { "*.pdf" });
				dialog.setFilterPath("WINDOWS/Desktop"); // Windows path
				dialog.setFileName(fileName);
				String filepath = dialog.open();

				if (null != filepath) {
					File df = new File(filepath);
					File xl = new File("lib/" + fileName + ".pdf");

					if (xl.exists()) {
						//AdminComposite.display("Saving...", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						copyFile(xl, df, dialog.getFileName());
					}
					/*AdminComposite.display("PDF Saved Successfully",
							STATUS_SUCCESS, SUCCESS_FONT_COLOR);*/
				}

			} catch (Exception e) {
				//AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				e.printStackTrace();
				displayError("Status Success");
			}

		}

		private void prepareSODPDF(SODDTO[] sto, String text, String sodST,
				String fileName) {
			// TODO Auto-generated method stub
			try {
				/*AdminComposite.display("Exporting...", STATUS_SUCCESS,
						SUCCESS_FONT_COLOR);*/
				handler.printSODReportPDF(sto, txtDate
						.getText(), sodST,fileName);

				FileDialog dialog = new FileDialog(shell, SWT.SAVE);
				dialog.setFilterExtensions(new String[] { "*.pdf" });
				dialog.setFilterNames(new String[] { "*.pdf" });
				dialog.setFilterPath("WINDOWS/Desktop"); // Windows path
				dialog.setFileName(fileName);
				String filepath = dialog.open();

				if (null != filepath) {
					File df = new File(filepath);
					File xl = new File("lib/" + fileName + ".pdf");

					if (xl.exists()) {
						//AdminComposite.display("Saving...", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						copyFile(xl, df, dialog.getFileName());
					}
					/*AdminComposite.display("PDF Saved Successfully",
							STATUS_SUCCESS, SUCCESS_FONT_COLOR);*/
				}

			} catch (Exception e) {
				//AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				e.printStackTrace();
				displayError("Status Success");
			}

		}

		private void prepareDDRPDF(DDRDTO[] door,
				String date, String soddrST,String fileName) {
			// TODO Auto-generated method stub
			try {
				/*AdminComposite.display("Exporting...", STATUS_SUCCESS,
						SUCCESS_FONT_COLOR);*/
				handler.printDDRReportPDF(door, txtDate
						.getText(), soddrST,fileName);

				FileDialog dialog = new FileDialog(shell, SWT.SAVE);
				dialog.setFilterExtensions(new String[] { "*.pdf" });
				dialog.setFilterNames(new String[] { "*.pdf" });
				dialog.setFilterPath("WINDOWS/Desktop"); // Windows path
				dialog.setFileName(fileName);
				String filepath = dialog.open();

				if (null != filepath) {
					File df = new File(filepath);
					File xl = new File("lib/" + fileName + ".pdf");

					if (xl.exists()) {
						//AdminComposite.display("Saving...", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						copyFile(xl, df, dialog.getFileName());
					}
					/*AdminComposite.display("PDF Saved Successfully",
							STATUS_SUCCESS, SUCCESS_FONT_COLOR);*/
				}

			} catch (Exception e) {
				//AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				e.printStackTrace();
				displayError("Status Success");
			}

		}

		private void prepareStockPDF(GMRInTimeDTO[]  dto,  String fileName) {
			// TODO Auto-generated method stub
			try {
				/*AdminComposite.display("Exporting...", STATUS_SUCCESS,
						SUCCESS_FONT_COLOR);*/
				handler.printStackReportPDF(dto,fileName);

				FileDialog dialog = new FileDialog(shell, SWT.SAVE);
				dialog.setFilterExtensions(new String[] { "*.pdf" });
				dialog.setFilterNames(new String[] { "*.pdf" });
				dialog.setFilterPath("WINDOWS/Desktop"); // Windows path
				dialog.setFileName(fileName);
				String filepath = dialog.open();

				if (null != filepath) {
					File df = new File(filepath);
					File xl = new File("lib/" + fileName + ".pdf");

					if (xl.exists()) {
						//AdminComposite.display("Saving...", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						copyFile(xl, df, dialog.getFileName());
					}
					/*AdminComposite.display("PDF Saved Successfully",
							STATUS_SUCCESS, SUCCESS_FONT_COLOR);*/
				}

			} catch (Exception e) {
				//AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				e.printStackTrace();
				displayError("Status Success");
			}

		}

		
		
		/**
		 * 
		 * @throws Exception
		 */
		private void populateCurrentStatusReport(String code) throws Exception {

			if (null != tblAgentReport)
				tblAgentReport.removeAll();
			int len = 0;
			float booking = 0;
			int noa = 0;
			float weight = 0;

			float ddc = 0;
			float lrtotal = 0;

			if (null != handler) {
				CurrentStatusDTO[] dto = handler.getCurrentStatusReport(code);
				if (null != dto) {
					btnExportXL.setEnabled(true);
					btnExportPDF.setEnabled(true);
					btnprint.setEnabled(true);
					len = dto.length;
					BeanUtil util = BeanUtil.getInstance();
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Date today = dateFormat.parse(util.getServerDate());

					DateFormat time = new SimpleDateFormat(
							"dd-MM-yyyy HH:mm:ss");
					String status = null;
					String stationcode = null;
					for (int i = 0; i < len; i++) {
						status = null;
						stationcode = null;
						TableItem item = new TableItem(tblAgentReport, SWT.NONE);
						Font itemFont = new Font(display, "Tahoma", 9, 0);
						item.setFont(itemFont);
						item.setText(0, String.valueOf(i + 1));
						item.setText(1, dto[i].getLr_no());

						Date lrdate = dto[i].getLrdate();
						item.setText(2, dateFormat.format(lrdate));
						item.setText(3, dto[i].getFrom());
						item.setText(4, dto[i].getTo());

						long diff = today.getTime() - lrdate.getTime();
						float days = (diff / (1000 * 60 * 60 * 24));
						item.setText(5, String.valueOf(days));
						booking = booking + days;

						stationcode = dto[i].getStation_code();
						status = dto[i].getLr_status();
						if (status.equalsIgnoreCase("arrived")) {
							item.setText(6, time
									.format(dto[i].getOutTimeDate())
									+ " | "
									+ "Present at "
									+ getStationName(stationcode));
						} else if (status.equalsIgnoreCase("toarrive")) {
							item.setText(6, time
									.format(dto[i].getOutTimeDate())
									+ " | "
									+ "Moved to "
									+ getStationName(stationcode));
						}

						item.setText(7, String.valueOf(dto[i]
								.getNo_of_articles()));
						noa = noa + dto[i].getNo_of_articles();
						item.setText(8, String.valueOf(dto[i]
								.getActual_weight()));
						weight = weight + dto[i].getActual_weight();

						ddc = ddc + dto[i].getDdc();
						item.setText(9, String.valueOf(dto[i].getDdc()));

						lrtotal = lrtotal + dto[i].getTotal();
						item.setText(10, String.valueOf(dto[i].getTotal()));

						item.setText(11, dto[i].getConsignorName());
						item.setText(12, dto[i].getConsigneeName());
					}

					final TableItem item1 = new TableItem(tblAgentReport,
							SWT.NONE);
					Font font1 = new Font(display, "Tahoma", 8, SWT.BOLD);
					item1.setFont(font1);
					item1.setText(3, "TOTAL");
					item1.setText(5, Float.toString(booking));
					item1.setText(7, Integer.toString(noa));
					item1.setText(8, Float.toString(weight));
					item1.setText(9, Float.toString(ddc));
					item1.setText(10, Float.toString(lrtotal));

				}else{
					btnExportXL.setEnabled(false);
					btnExportPDF.setEnabled(false);
					btnprint.setEnabled(false);
				}
					
			}

		}

		/**
		 * 
		 * @throws ParseException
		 * @throws Exception
		 */
		private void populateOutstandingReport() throws ParseException,
				Exception {

			if (null != tbloutstanding) {
				tbloutstanding.removeAll();
			
				
				StationsDTO[] stations = getAllBranches();
				int len = stations.length;
				int index = -1;
				String selectednames = null;
				int tableindex = 1;
				for (int i = 0; i < len; i++) {
					index = cbbranch.getSelectionIndex();
					if (index > -1) {
						selectednames = cbbranch.getItem(index);
					}
					if (null != selectednames) {
						index = selectednames.indexOf("-");
						if (index > -1) {
							selectednames = selectednames.substring(index + 1);

						}
					}
					if (null != selectednames) {
						selectednames = selectednames.trim();
					} else
						break;
					System.out.println("selecte-->"+selectednames);
					System.out.println("deliver-->"+stations[i].getBranch_code());
					if (selectednames.equals(stations[i].getBranch_code())) {
						TableItem item = new TableItem(tbloutstanding, SWT.NONE);
						item.setText(0, String.valueOf(tableindex++));
						item.setText(1, stations[i].getBranch_code());
						item.setText(2, stations[i].getName());
						item.setText(3, stations[i].getId());
						item.setText(4, String.valueOf("0.00"));
						item.setText(5, String.valueOf("0.00"));
						item.setText(6, String.valueOf("0.00"));
						item.setText(7, String.valueOf("0.00"));
					}
				}
				
				
				Date date = null;

				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				date = dateFormat.parse("2008-09-09");
				if (null == outstandingdto)
					outstandingdto = beanutil.getOutstandingReport(null, date);
				if (null != outstandingdto) {
					populateOutstandingDetails(outstandingdto);
				}

			}

		}

		/**
		 * Method to populate outstanding Details.
		 * 
		 * @param dto
		 */
		private void populateOutstandingDetails(BookingDTO[] dto) {
			TableItem[] items = tbloutstanding.getItems();
			String stationcode = null;
			String deliveredcode = null;
			float tpdd = 0;
			float tpod = 0;
			float paid = 0;
			float total = 0;
			btnExportXL.setEnabled(true);
			btnExportPDF.setEnabled(true);
			btnprint.setEnabled(true);

			int length = items.length;
			for (int i = 0; i < dto.length; i++) {
				stationcode = dto[i].getFrom();

				for (int j = 0; j < length; j++) {

					deliveredcode = items[j].getText(3);
					if (stationcode.equalsIgnoreCase(deliveredcode)) {
						tpdd = Float.parseFloat(items[j].getText(4));
						tpod = Float.parseFloat(items[j].getText(5));
						paid = Float.parseFloat(items[j].getText(6));
						total = Float.parseFloat(items[j].getText(7));
						if (dto[i].getType().equalsIgnoreCase("topay")) {

							if (dto[i].getDdc() != 0.0) {
								tpdd = tpdd + dto[i].getTotal();
							} else {
								tpod = tpod + dto[i].getTotal();
							}

						} else if (dto[i].getType().equalsIgnoreCase("paid")) {
							paid = paid + dto[i].getTotal();
						}

						total = tpdd + tpod + paid;
						items[j].setText(4, String.valueOf(tpdd));
						items[j].setText(5, String.valueOf(tpod));
						items[j].setText(6, String.valueOf(paid));
						items[j].setText(7, String.valueOf(total));
						break;

					}
				}
			}
			tpdd = 0;
			tpod = 0;
			paid = 0;
			total = 0;
			for (int i = 0; i < length; i++) {
				tpdd = tpdd + Float.parseFloat(items[i].getText(4));
				tpod = tpod + Float.parseFloat(items[i].getText(5));
				paid = paid + Float.parseFloat(items[i].getText(6));
				total = total + Float.parseFloat(items[i].getText(7));
			}
			if (length > 0) {

				final TableItem totalItem = new TableItem(tbloutstanding,
						SWT.NONE);
				Font totalFont = new Font(display, "Tahoma", 8, SWT.BOLD);
				totalItem.setFont(totalFont);
				totalItem.setText(2, "TOTAL");
				totalItem.setText(4, String.valueOf(tpdd));
				totalItem.setText(5, String.valueOf(tpod));
				totalItem.setText(6, String.valueOf(paid));
				totalItem.setText(7, String.valueOf(total));
			}

		}

		/**
		 * Method to getStations Sort By branch Code
		 * 
		 * @return
		 * @throws Exception
		 */
		private StationsDTO[] getAllBranches() throws Exception {

			StationsDTO[] stations = beanutil.getAvailableStations();
			StationsDTO[] branches = null;
			ArrayList<StationsDTO> list = new ArrayList<StationsDTO>();
			if (null != stations) {
				ArrayList<String> branchNameList = new ArrayList<String>();

				// Get the list of branch codes
				int len = stations.length;
				for (int i = 0; i < len; i++) {
					if (!branchNameList.contains(stations[i].getBranch_code())) {
						branchNameList.add(stations[i].getBranch_code());
					}
				}
				java.util.Collections.sort(branchNameList);
				int size = branchNameList.size();
				branches = new StationsDTO[size];

				// Get the associate name of the branch code
				for (int j = 0; j < size; j++) {
					String branchcode = branchNameList.get(j);

					for (int i = 0; i < len; i++) {
						if (branchcode.equals(stations[i].getBranch_code())) {
							// branches[j] = stations[i];
							list.add(stations[i]);
						}
					}
				}

			}
			int size = list.size();
			branches = list.toArray(new StationsDTO[size]);

			return branches;
		}

		/**
		 * Method to get SOD Values
		 * 
		 * @return
		 * @throws ParseException
		 */
		
		private ArrayList<GMROutTimeDTO> buildInwardDTO() throws ParseException {
			// TODO Auto-generated method stub
			GMROutTimeDTO dto = null;
			ArrayList<GMROutTimeDTO> list = new ArrayList<GMROutTimeDTO>();
			if (null != tblInwardRegister) {
				TableItem[] item = tblInwardRegister.getItems();
				int len = item.length;
				SimpleDateFormat dateform = new SimpleDateFormat(
							"dd-MM-yyyy");
				Date tempdate = null;
				Date temp = null;
				for (int i = 0; i < len; i++) {
						dto = new GMROutTimeDTO();
						dto.setLr_no(item[i].getText(1));
						if(!(item[i].getText(2).equals(""))){
							tempdate = dateform.parse(item[i].getText(2));
							dto.setOutTimeDate(tempdate);
						}
						dto.setLr_type(item[i].getText(3));
						if(!(item[i].getText(4).equals(""))){
							temp = dateform.parse(item[i].getText(4));
							dto.setLrDate(temp);
						}
						if(!(item[i].getText(5).equals("")))
							dto.setTotal(Float.parseFloat(item[i].getText(5)));
						dto.setFrom(item[i].getText(6));
						if(!(item[i].getText(7).equals("")))	
							dto.setNo_of_articles(Integer.parseInt(item[i].getText(7)));
						if(!(item[i].getText(8).equals("")))	
							dto.setActual_weight(Float.parseFloat(item[i].getText(8)));
						dto.setArticle_type(item[i].getText(9));
							
						dto.setConsigneeName((item[i].getText(10)));
						
						dto.setConsignorName(item[i].getText(11));
						
						list.add(dto);
				}
				
			}
			return list;

		}
		
		private SODDTO[] buildSODDTO() throws ParseException{
			SODDTO[] dto = null;
			if (null != tblSod) {
				TableItem[] item = tblSod.getItems();
				int len = item.length;
				if (len > 0) {
					dto = new SODDTO[len - 1];
					SimpleDateFormat dateform = new SimpleDateFormat(
							"dd-MM-yyyy");
					for (int i = 0; i < len - 1; i++) {
						dto[i] = new SODDTO();
						dto[i].setCrno(item[i].getText(14));
						String temp = item[i].getText(2);
						if (!temp.equalsIgnoreCase("Cancelled")) {
							if(!(item[i].getText(8).equals("")))	
								dto[i].setDd(Float.parseFloat(item[i].getText(8)));
							if(!(item[i].getText(9).equals("")))	
								dto[i].setDemurrage(Float.parseFloat(item[i].getText(9)));
							if(!(item[i].getText(10).equals("")))	
								dto[i].setUnderCharge(Float.parseFloat(item[i].getText(10)));
							if(!(item[i].getText(11).equals("")))	
								dto[i].setPostage(Float.parseFloat(item[i].getText(11)));
							if(!(item[i].getText(12).equals("")))	
								dto[i].setDdExtra(Float.parseFloat(item[i].getText(12)));
							if(!(item[i].getText(13).equals("")))	
								dto[i].setOthers(Float.parseFloat(item[i].getText(13)));
							if(!(item[i].getText(15).equals("")))	
								dto[i].setCrAmt(Float.parseFloat(item[i].getText(15)));
							dto[i].setLrno(item[i].getText(1));
							dto[i].setLrDate(dateform
									.parse(item[i].getText(2)));
							dto[i].setFrom(item[i].getText(3));
							if(!(item[i].getText(4).equals("")))	
								dto[i].setNoofarticles(Integer.parseInt(item[i].getText(4)));
							if(!(item[i].getText(5).equals("")))	
								dto[i].setWeight(Float.parseFloat(item[i].getText(5)));
							dto[i].setLrtype(item[i].getText(6));
							if(!(item[i].getText(7).equals("")))	
								dto[i].setTotalAmount(Float.parseFloat(item[i].getText(7)));
						} else {
							dto[i].setStatus(true);
							dto[i].setLrno(item[i].getText(1));
						}

					}

				}

			}
			return dto;
		}

		/**
		 * Method to build StockDetails DTO
		 * 
		 * @return
		 */
		private GMRInTimeDTO[] buildStockDTO() throws ParseException {
			GMRInTimeDTO[] dto = null;
			if (null != tblstockreport) {
				TableItem[] items = tblstockreport.getItems();
				int len = items.length;
				dto = new GMRInTimeDTO[len];
				SimpleDateFormat dateform = new SimpleDateFormat("dd-MM-yyyy");
				Date tempdate = null;
				for (int i = 0; i < len; i++) {
					dto[i] = new GMRInTimeDTO();
					dto[i].setLr_no(items[i].getText(1));
					if(!(items[i].getText(2).equals("")))
					{	tempdate = dateform.parse(items[i].getText(2));
						dto[i].setDate(tempdate);
					}
					dto[i].setLr_type(items[i].getText(3));
					dto[i].setFrom(items[i].getText(4));
					if(!(items[i].getText(5).equals("")))
						dto[i].setNo_of_articles(Integer.parseInt(items[i].getText(5)));
					if(!(items[i].getText(6).equals("")))
						dto[i].setActual_weight(Float.parseFloat(items[i].getText(6)));
					if(!(items[i].getText(7).equals("")))
						dto[i].setTotal(Float.parseFloat(items[i].getText(7)));
					dto[i].setArticle_value(items[i].getText(8));
					dto[i].setArticle_type(items[i].getText(9));
					dto[i].setInward_days((items[i].getText(10)));
					dto[i].setSent_days(items[i].getText(11));
					dto[i].setDdc(Float.parseFloat(items[i].getText(12)));
					dto[i].setVehicle_no(items[i].getText(13));
					dto[i].setConsignor_name(items[i].getText(14));
					dto[i].setConsignee_name(items[i].getText(15));

				}
			}
			return dto;
		}
		
		private OutstandingDTO[] buildOutstandingDTO() {
			// TODO Auto-generated method stub
			OutstandingDTO[] dto = null;
			if (null != tbloutstanding) {
				TableItem[] items = tbloutstanding.getItems();
				int len = items.length;
				dto = new OutstandingDTO[len];
				
				for (int i = 0; i < len; i++) {
					
					dto[i] = new OutstandingDTO();
					
					dto[i].setBranchcode(items[i].getText(1));
					
					dto[i].setStationname(items[i].getText(2));
					
					dto[i].setStationcode(items[i].getText(3));
					
					if (!items[i].getText(4).equals(""))
						dto[i].setTpdd(Float.parseFloat(items[i].getText(4)));
						
					
					if (!items[i].getText(5).equals(""))
						dto[i].setTpod(Float.parseFloat(items[i].getText(5)));
						
					
					if (!items[i].getText(6).equals(""))
						dto[i].setPdbooking(Float.parseFloat(items[i].getText(6)));
					
					if (!items[i].getText(7).equals(""))
						dto[i].setTotal(Float.parseFloat(items[i].getText(7)));
					
				
				}
			}
			return dto;

			
		}
		
		private CurrentStatusDTO[] buildCurrentStatusDTO()throws Exception {
			// TODO Auto-generated method stub
			CurrentStatusDTO[] dto = null;
			if (null != tblAgentReport) {
				TableItem[] items = tblAgentReport.getItems();
				int len = items.length;
				dto = new CurrentStatusDTO[len];
				SimpleDateFormat dateform = new SimpleDateFormat("dd-MM-yyyy");
				Date tempdate = null;
				for (int i = 0; i < len; i++) {
					
					dto[i] = new CurrentStatusDTO();
					
					dto[i].setLr_no(items[i].getText(1));
					
					if (!items[i].getText(2).equals("")){
						tempdate = dateform.parse(items[i].getText(2));
						dto[i].setLrdate(tempdate);
					}
					
					dto[i].setFrom(items[i].getText(3));
					
					dto[i].setTo(items[i].getText(4));
					
					if (!items[i].getText(5).equals(""))
						dto[i].setArticle_value(Float.parseFloat(items[i].getText(5)));
					
					if (!items[i].getText(6).equals("")){
						tempdate = dateform.parse(items[i].getText(6));
						dto[i].setOutTimeDate(tempdate);
					}
					
					
					if (!items[i].getText(7).equals(""))
						dto[i].setNo_of_articles(Integer.parseInt(items[i].getText(7)));
					
					if (!items[i].getText(8).equals(""))
						dto[i].setActual_weight(Float.parseFloat(items[i].getText(8)));
					
					
					if (!items[i].getText(9).equals(""))
						dto[i].setDdc(Float.parseFloat(items[i].getText(9)));
					
					if (!items[i].getText(10).equals(""))
						dto[i].setTotal(Float.parseFloat(items[i].getText(10)));
					
					dto[i].setConsignorName(items[i].getText(11));
					
					dto[i].setConsigneeName(items[i].getText(12));
					
				
				
				}
			}
			return dto;
			
			
		}


		
		/**
		 * Method to Populate Stock Information
		 * 
		 * @param list
		 */
		private void populateStockDetails(ArrayList list) {

			int len = 0;
			ArrayList<GMROutTimeDTO> outtime = (ArrayList<GMROutTimeDTO>) list
					.get(0);
			ArrayList<GMRInTimeDTO> intime = (ArrayList<GMRInTimeDTO>) list
					.get(1);
			ArrayList<GMROutTimeDTO> cr = (ArrayList<GMROutTimeDTO>) list
					.get(2);
			if (null != tblstockreport) {
				btnExportXL.setEnabled(false);
				btnExportPDF.setEnabled(false);
				btnprint.setEnabled(false);
				tblstockreport.removeAll();
			}

			try {
				btnExportXL.setEnabled(true);
				btnExportPDF.setEnabled(true);
				btnprint.setEnabled(true);
				populateOutAndCRDetails(outtime);
				populateOutAndCRDetails(cr);
				populateInTimeDetails(intime);
				displayTotalForStock();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		private void displayTotalForStock() {
			if (null != tblstockreport) {
				TableItem[] item = tblstockreport.getItems();
				int len = item.length;
				int noa = 0;
				float actual_wt = 0;
				float freight = 0;
				long art_value = 0;
				float inward = 0;
				float sentdays = 0;
				float dd = 0;
				String compare = null;
				if (len > 0) {
					for (int i = 0; i < len; i++) {
						compare = item[i].getText(5);
						if (!compare.equals(""))
							noa = noa + Integer.parseInt(compare);

						compare = item[i].getText(6);
						if (!compare.equals(""))
							actual_wt = actual_wt + Float.parseFloat(compare);

						compare = item[i].getText(7);
						if (!compare.equals(""))
							freight = freight + Float.parseFloat(compare);

						compare = item[i].getText(8);
						if (!compare.equals(""))
							art_value = art_value
									+ (long) Float.parseFloat(compare);

						compare = item[i].getText(10);
						if (!compare.equals(""))
							inward = inward + Float.parseFloat(compare);

						compare = item[i].getText(11);
						if (!compare.equals(""))
							sentdays = sentdays + Float.parseFloat(compare);
						compare = item[i].getText(12);
						if (!compare.equals(""))
							dd = dd + Float.parseFloat(compare);

					}

					TableItem item1 = new TableItem(tblstockreport, SWT.NONE);
					Font font1 = new Font(display, "Tahoma", 8, SWT.BOLD);
					item1.setFont(font1);
					item1.setText(0, "TOTAL");
					item1.setText(5, String.valueOf(noa));
					item1.setText(6, String.valueOf(actual_wt));
					item1.setText(7, String.valueOf(freight));
					item1.setText(8, String.valueOf(art_value));
					item1.setText(10, String.valueOf(inward));
					item1.setText(11, String.valueOf(sentdays));
					item1.setText(12, String.valueOf(dd));
				}

			}

		}

		/**
		 * 
		 * @param intime
		 * @throws Exception
		 * @throws NumberFormatException
		 */
		private void populateInTimeDetails(ArrayList<GMRInTimeDTO> intime)
				throws NumberFormatException, Exception {

			int len = intime.size();
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			int j = tblstockreport.getItemCount();
			j++;
			for (int i = 0; i < len; i++) {
				GMRInTimeDTO dto = (GMRInTimeDTO) intime.get(i);

				TableItem item = new TableItem(tblstockreport, SWT.NONE);
				Font itemFont = new Font(display, "Tahoma", 9, 0);
				item.setFont(itemFont);
				item.setText(0, String.valueOf(j++));
				item.setText(1, dto.getLr_no());
				Date lrdate = dto.getDate();
				item.setText(2, dateFormat.format(lrdate));
				item.setText(3, dto.getLr_type());

				item.setText(4, dto.getFrom());

				item.setText(5, String.valueOf(dto.getNo_of_articles()));

				item.setText(6, String.valueOf(dto.getActual_weight()));
				item.setText(7, String.valueOf(dto.getTotal()));
				item.setText(8, String.valueOf(dto.getArticle_value()));

				String arttype = getArticleType(Integer.parseInt(dto
						.getArticle_type()));
				if (null != arttype)
					item.setText(9, arttype);

				lrdate = dto.getSent_date();
				Date today = dto.getToday_date();

				long diff = today.getTime() - lrdate.getTime();

				diff = diff / (1000 * 60 * 60 * 24);
				item.setText(11, String.valueOf(diff));
				item.setText(12, String.valueOf(dto.getDdc()));

				if (null != dto.getVehicle_no())
					item.setText(13, dto.getVehicle_no());

				item.setText(14, dto.getConsignorName());
				item.setText(15, dto.getConsigneeName());
			}

		}

		/**
		 * Method to populate OUTIME and CR details for Stock Report
		 * 
		 * @param outtime
		 * @param len
		 * @throws Exception
		 * @throws NumberFormatException
		 */
		private void populateOutAndCRDetails(ArrayList<GMROutTimeDTO> outtime)
				throws NumberFormatException, Exception {

			int len = outtime.size();
			Date today = new Date();
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			int j = tblstockreport.getItemCount();
			j++;
			for (int i = 0; i < len; i++) {
				GMROutTimeDTO dto = (GMROutTimeDTO) outtime.get(i);

				TableItem item = new TableItem(tblstockreport, SWT.NONE);
				Font itemFont = new Font(display, "Tahoma", 9, 0);
				item.setFont(itemFont);
				item.setText(0, String.valueOf(j++));
				item.setText(1, dto.getLr_no());

				Date lrdate = dto.getLrDate();
				item.setText(2, dateFormat.format(lrdate));
				item.setText(3, dto.getLr_type());
				item.setText(4, dto.getFrom());
				item.setText(5, String.valueOf(dto.getNo_of_articles()));
				item.setText(6, String.valueOf(dto.getActual_weight()));
				item.setText(7, String.valueOf(dto.getTotal()));
				item.setText(8, String.valueOf(dto.getArticle_value()));

				String arttype = getArticleType(Integer.parseInt(dto
						.getArticle_type()));
				if (null != arttype)
					item.setText(9, arttype);

				/** Need to change Number of days since inward * */
				Date outtimedate = dto.getOutTimeDate();
				long diff1 = today.getTime() - outtimedate.getTime();
				float days1 = (diff1 / (1000 * 60 * 60 * 24));

				item.setText(10, String.valueOf(days1));
				item.setText(12, String.valueOf(dto.getDdc()));
				item.setText(14, dto.getConsignorName());
				item.setText(15, dto.getConsigneeName());
			}

		}

		/**
		 * Method to get Article Type by passing Article ID as Arguments
		 * 
		 * @param parseInt
		 * @return
		 * @throws Exception
		 */
		private String getArticleType(int parseInt) throws Exception {
			ArticleDTO[] articles = handler.getArticleType(parseInt);
			if (null != articles) {
				int len = articles.length;
				for (int i = 0; i < len; i++) {
					if (parseInt == articles[i].getArticleId()) {
						return articles[i].getType();

					}
				}
			}
			return null;
		}

		private void fillRecoveryValues(RefundRecoveryDTO[] RecoveryDTO)
		throws Exception {

			if (null != RecoveryDTO) {
				btnExportXL.setEnabled(true);
				btnExportPDF.setEnabled(true);
				btnprint.setEnabled(true);
				float totAmt = 0;
				String inst = "";
				
				for (int i = 0; i < RecoveryDTO.length; i++) {
					TableItem itemRecovery = new TableItem(tblRecovery,
							SWT.NONE);
					itemRecovery.setText(0, Integer.toString((i + 1)));
					if (RecoveryDTO[i].getDescription() != null)
						itemRecovery
								.setText(1, RecoveryDTO[i].getDescription());
					if (RecoveryDTO[i].getContacts() != null)
						itemRecovery.setText(2, RecoveryDTO[i].getContacts());
					totAmt = totAmt + RecoveryDTO[i].getAmount();
					itemRecovery.setText(3, "-"
							+ decimalFormat.format(RecoveryDTO[i].getAmount()));

					inst = RecoveryDTO[i].getInstallment();

					if (inst != null && !inst.equals("")) {
						inst = inst.replace("-", " of ");
						itemRecovery.setText(4, inst);
					}

					itemRecovery.setText(5, viewdateformat
							.format(RecoveryDTO[i].getRr_date()));

				}
				final TableItem item1 = new TableItem(tblRecovery, SWT.NONE);
				Font font1 = new Font(Display.getDefault(), "Tahoma", 8,
						SWT.BOLD);
				item1.setFont(font1);
				item1.setText(2, "TOTAL");
				item1.setText(3, "-" + decimalFormat.format(totAmt));

			}else{
				btnExportXL.setEnabled(false);
				btnExportPDF.setEnabled(false);
				btnprint.setEnabled(false);
			}
				
}
		
		private void fillRefundValues(RefundRecoveryDTO[] RefundDTO)
		throws Exception {

			if (null != RefundDTO) {
			
				float totAmt = 0;
				String inst = "";
				
				for (int i = 0; i < RefundDTO.length; i++) {
					TableItem itemRefund = new TableItem(tblRefund, SWT.NONE);
					itemRefund.setText(0, Integer.toString((i + 1)));
					if (RefundDTO[i].getDescription() != null)
						itemRefund.setText(1, RefundDTO[i].getDescription());
					if (RefundDTO[i].getContacts() != null)
						itemRefund.setText(2, RefundDTO[i].getContacts());
					totAmt = totAmt + RefundDTO[i].getAmount();
					itemRefund.setText(3, decimalFormat.format(RefundDTO[i]
							.getAmount()));

					inst = RefundDTO[i].getInstallment();

					if (inst != null && !inst.equals("")) {
						inst = inst.replace("-", " of ");
						itemRefund.setText(4, inst);
					}

					itemRefund.setText(5, viewdateformat.format(RefundDTO[i]
							.getRr_date()));

				}

				final TableItem item1 = new TableItem(tblRefund, SWT.NONE);
				Font font1 = new Font(Display.getDefault(), "Tahoma", 8,
						SWT.BOLD);
				item1.setFont(font1);
				item1.setText(2, "TOTAL");
				item1.setText(3, decimalFormat.format(totAmt));
			}else{
				btnExportXL.setEnabled(false);
				btnExportPDF.setEnabled(false);
				btnprint.setEnabled(false);
			}
		}

		
		/**
		 * 
		 */
		public void widgetDefaultSelected(SelectionEvent event) {

		}
	}
}
