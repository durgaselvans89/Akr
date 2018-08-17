/**
 * 
 */
package hm.akr.admin.commission;

import hm.akr.admin.commission.handler.StationCommissionCompositeHandler;
import hm.akr.admin.workspace.AdminComposite;
import hm.akr.admin.workspace.SWTResourceManager;
import hm.akr.common.BeanUtil;
import hm.akr.common.MonthDialog;
import hm.akr.admin.commission.CommissionComposite.*;
import hm.akr.dto.BookingCommissionDTO;
import hm.akr.dto.CartageCommissionDTO;
import hm.akr.dto.CommissionSummaryDTO;
import hm.akr.dto.DDDetailsDTO;
import hm.akr.dto.DeliveryCommissionDTO;
import hm.akr.dto.RefundRecoveryDTO;
import hm.akr.dto.StationsDTO;
import hm.akr.dto.VersionDTO;
import hm.akr.common.IUIConstants;
import hm.akr.admin.history.HistoryHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.Collator;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
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

import com.sun.corba.se.impl.io.ValueUtility;

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
 * @author user
 * 
 */
public class StationCommissionComposite extends Composite implements Listener,IUIConstants,
		MouseListener, KeyListener {
	private static Label lblStatusBar;
	
	private TabFolder tbfCommission;
	private TabItem tiBooking;
	private TabItem tiDelivery;
	private Group gpMonth;
	private Button btnGo;
	private Text txtMonth;
	private TableColumn colBLrno;
	private TableColumn ColDLrno;
	private TableColumn colDLrType;
	private TableColumn colDActualWeight;
	private Table tblRecovery;
	private Table tblRefund;
	private Table tblDelivery;
	private Table tblBooking;
	private TabItem tiRecovery;
	private TabItem tiRefund;
	private TableColumn colBPercent;
	private TableColumn colRefContact;
	private TableColumn colRefAmount;
	private TableColumn colRefDesc;
	private TableColumn colBMode;
	private TableColumn colBComnAmt;
	private TableColumn colRecDesc;
	private TableColumn colRecAmount;
	private TableColumn colRecContact;
	private TableColumn colBBookingDate;
	private TableColumn colBBasicFreight;
	private TableColumn colDBookingDate;
	private TabItem tiCCC;
	private TableColumn colDCommission;
	private Table tblCCC;
	private TableColumn colCLrNo;
	private TableColumn colCBookingDate;
	private TableColumn colCBasicFreight;
	private TableColumn colCLrType;
	private TableColumn colCPercentCharged;
	private TableColumn colBSNo;
	private TableColumn colDSNo;
	private TableColumn colRefSNo;
	private TableColumn colRecSNo;
	private TableColumn colCSNo;
	private Button btnMonth;	
	BeanUtil beanutil = null;
	private static final String SUMMARY_TAB = "Summary";
	private static final String BOOKING_TAB = "Booking";
	private static final String DELIVERY_TAB = "Delivery";
	private static final String REFUND_TAB = "Refund";
	private static final String RECOVERY_TAB = "Recovery";
	private static final String CCC_TAB = "CC Commission";
	private static final String DDDETAILS_TAB = "DD Details";
	private Label lblCommssion;
	private Label lblParticulars;
	private Text txtSTotal;
	private Label lblTotalCommission;
	private Text txtSCBeforeDD;
	private Label lblSCommDD;
	private Text txtSRecovery;
	private Label lblSRecovery;
	private Text txtSRefund;
	private Label lblSRefund;
	private Text txtSDDCharge;
	private Label lblSDDCharge;
	private Text txtSCCCharge;
	private Label lblSCCCommission;
	private Text txtSDelCommission;
	private Label lblSDelCommission;
	private Text txtSBooking;
	private Label lblSBooking;
	BookingCommissionDTO[] BCommDTO = null;
	DeliveryCommissionDTO[] DCommDTO = null;
	CartageCommissionDTO[] CCommDTO = null;
	RefundRecoveryDTO[] RecoveryDTO = null;
	RefundRecoveryDTO[] RefundDTO = null;
	DDDetailsDTO[] ddDetailsDTO = null;
	private TableColumn colCommissionAmt;
	private CommissionSummaryDTO CSummaryDTo;
	SimpleDateFormat viewdateformat = null;
	private TabItem tiDDDetails;
	private Table tblDDDetails;
	private TableColumn colDdSNo;
	private TableColumn ColDdLrno;
	private TableColumn colDdBookingDate;
	private TableColumn colDdLrType;
	private TableColumn colDdCharge;
	private TableColumn colDdExtra;
	private TableColumn ColDdOthers;
	private TabItem tiCommissionSummary;

	private String selectedDate = null;
	private String selectedDateDDC = null;
	private Shell shell;
		
	private TableColumn colDdDemurrage;
	private TableColumn colDdUndCrg;
	private TableColumn colDdPTCrg;
	private TableColumn colDdTotal;
	

	private DecimalFormat decimalFormat;
	private TableColumn colDDeliveredDate;
	private TableColumn colRecInst;
	private TableColumn colRefInst;
	private TableColumn colRecDate;
	private TableColumn colRefDate;
	
	boolean own_station=false;
	
	private StationCommissionCompositeHandler handler;
	private Label label12;
	private Combo cbBranch;
	private Combo cbStation;

	private String prevSelectedStation;
	
	private Button btnprint;
	
	private Button btnExportXL;
	
	private Button btnExportPDF;
	
	private static final String BOOKING_JRXML = "hm/akr/resources/printer/Booking.jrxml";
	
	private static final String DELIVERY_JRXML = "hm/akr/resources/printer/Delivery.jrxml";
	
	private static final String CCC_JRXML = "hm/akr/resources/printer/cccommission.jrxml";
	
	private static final String DDDETAILS_JRXML = "hm/akr/resources/printer/Dddetails.jrxml";
	
	private static final String RECOVERY_JRXML = "hm/akr/resources/printer/Recovery.jrxml";
	
	private static final String REFUND_JRXML = "hm/akr/resources/printer/Refund.jrxml";
	
	VersionDTO[] vDto = null;
	
	HistoryHandler historyH = null;

	
	/**
	 * Commission Composite Constructor
	 */
	public StationCommissionComposite(Composite shell, int swtValue) {
		super(shell, swtValue);	
		this.shell = shell.getShell();
		try {
			handler = new StationCommissionCompositeHandler();
			beanutil = BeanUtil.getInstance();
			viewdateformat = new SimpleDateFormat("dd-MM-yyyy");
			decimalFormat = new DecimalFormat("0.00");
			historyH = new HistoryHandler();
			vDto = historyH.getHistoryYears();
			if(vDto != null && vDto.length > 0){
				BeanUtil.setThree_month_updated(vDto[0].getUpdated_date());
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Composite loadComposite() {

		try {
			
			{
				lblStatusBar = new Label(this, SWT.NONE);
				FormData lblStatusBarLData = new FormData();
				lblStatusBarLData.width = 500;
				lblStatusBarLData.height = 32;
				lblStatusBarLData.left = new FormAttachment(0, 1000, 200);
				lblStatusBarLData.top = new FormAttachment(0, 1000, 689);
				lblStatusBar.setLayoutData(lblStatusBarLData);
				lblStatusBar.setFont(STATUS_SUCCESS);
				lblStatusBar.setText("STATUS");
				lblStatusBar.setForeground(LINK_FOCUS_COLOR);
			}
			
			/*public static void display(String status,Font font,Color color)
			{
				lblStatusBar.setText(status);
				lblStatusBar.setFont(font);
				lblStatusBar.setForeground(LINK_FOCUS_COLOR);
			}*/

			{
				label12 = new Label(this, SWT.NONE);
				label12.setText("Select Branch");
				label12.setBounds(30, 28, 68, 17);
			}
			{
				cbBranch = new Combo(this,
						SWT.DROP_DOWN | SWT.READ_ONLY);
				cbBranch.setBounds(105, 28, 141, 21);
				cbBranch.addSelectionListener(new CommissionAction());
			}

			{
				label12 = new Label(this, SWT.NONE);
				label12.setText("Select Station");
				label12.setBounds(258, 28, 65, 21);
			}
			{
				cbStation = new Combo(this,
						SWT.DROP_DOWN | SWT.READ_ONLY);
				cbStation.setBounds(328, 28, 143, 21);		
				cbStation.addSelectionListener(new CommissionAction());
			}
						
			{
				gpMonth = new Group(this, SWT.NONE);
				gpMonth.setText("Month");
				gpMonth.setBounds(487, 9, 140, 54);
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
							
							String selectedTab = "";
							if(tbfCommission != null)
								selectedTab = tbfCommission.getSelection()[0].getText();
							if (selectedTab.equals(DDDETAILS_TAB)) {
								selectedDateDDC = txtMonth.getText();
							}
							txtMonth.setText(obj.toString());

						}
					}

				});
			}
			{
				btnGo = new Button(gpMonth, SWT.PUSH | SWT.CENTER);
				btnGo.setText("Go");
				btnGo.setBounds(100, 17, 30, 24);
				btnGo.addSelectionListener(new CommissionAction());
			}
			
			loadCommissionComposite();
			
			populateBranches(cbBranch);
		
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this;
	}

	private void populateBranches(Combo cbBranch) {
		StationsDTO[] station = null;
		try {
			station = handler.getAllBranches();
			if (null != station) {
				int len = station.length;
				for (int i = 0; i < len; i++) {
					cbBranch.add(station[i].getName() + " - "
							+ station[i].getId());
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
	private void loadCommissionComposite() throws Exception {
		{
			this.setSize(1030, 746);
			{
				tbfCommission = new TabFolder(this, SWT.NONE);
				tbfCommission.setBounds(10, 72, 830, 460);
				
				StationsDTO[] stations = beanutil.getAvailableStations();
				String stationCode = beanutil.getActingStationCode();
				
				for (int i = 0; i < stations.length; i++) {
					if (stationCode.equalsIgnoreCase(stations[i].getId())) {
						if ((stations[i].getOwner().equalsIgnoreCase("akr"))) {
							own_station=false;
						}
						
					}
				}

				System.out.println("OWN Startion"+own_station);
				
				{	
					btnprint = new Button(this, SWT.PUSH | SWT.CENTER);
					btnprint.setText("Print");
					btnprint.setBounds(540, 535, 55, 25);
					btnprint.setEnabled(false);
					btnprint.addSelectionListener(new CommissionAction());

				}
				
				{
					btnExportXL = new Button(this, SWT.PUSH | SWT.CENTER);
					btnExportXL.setText("Export Excel");
					btnExportXL.setBounds(610, 535, 75, 25);
					btnExportXL.setEnabled(false);
					btnExportXL.addSelectionListener(new CommissionAction());
					
					
				}
				
				{
					btnExportPDF = new Button(this, SWT.PUSH | SWT.CENTER);
					btnExportPDF.setText("Export PDF");
					btnExportPDF.setEnabled(false);
					btnExportPDF.setBounds(700,535,75,25);
					btnExportPDF.addSelectionListener(new CommissionAction());

					
				}
				


				{
					tiCommissionSummary = new TabItem(tbfCommission, SWT.NONE | SWT.RIGHT);
					tiCommissionSummary.setText(SUMMARY_TAB);

					{
						Canvas cvsSummary = new Canvas(tbfCommission,
								SWT.BORDER);
						tiCommissionSummary.setControl(cvsSummary);
						{
							if (!own_station || beanutil.isAdminHead()) {
								{
									lblSBooking = new Label(cvsSummary,
											SWT.NONE);
									lblSBooking.setText("Booking Summary");
									lblSBooking.setBounds(156, 50, 110, 24);
								}
								{
									txtSBooking = new Text(cvsSummary,
											SWT.BORDER);
									txtSBooking.setBounds(295, 50, 100, 22);
									txtSBooking.setEnabled(false);
								}
								{
									lblSDelCommission = new Label(cvsSummary,
											SWT.NONE);
									lblSDelCommission
											.setText("Delivery Commission");
									lblSDelCommission.setBounds(156, 94, 125,
											20);
								}
								{
									txtSDelCommission = new Text(cvsSummary,
											SWT.BORDER);
									txtSDelCommission.setBounds(295, 92, 100,
											22);
									txtSDelCommission.setEnabled(false);
								}
								{
									lblSCCCommission = new Label(cvsSummary,
											SWT.NONE);
									lblSCCCommission.setText("CC Charge");
									lblSCCCommission.setBounds(156, 137, 101,
											22);
								}
								{
									txtSCCCharge = new Text(cvsSummary,
											SWT.BORDER);
									txtSCCCharge.setBounds(295, 137, 100, 22);
									txtSCCCharge.setEnabled(false);
								}
								{
									lblSDDCharge = new Label(cvsSummary,
											SWT.NONE);
									lblSDDCharge.setText("DD Charge");
									lblSDDCharge.setBounds(156, 184, 101, 22);
								}
								{
									txtSDDCharge = new Text(cvsSummary,
											SWT.BORDER);
									txtSDDCharge.setBounds(295, 183, 100, 22);
									txtSDDCharge.setEnabled(false);
								}
								{
									lblSRefund = new Label(cvsSummary, SWT.NONE);
									lblSRefund.setText("Refund");
									lblSRefund.setBounds(156, 231, 75, 22);
								}
								{
									txtSRefund = new Text(cvsSummary,
											SWT.BORDER);
									txtSRefund.setBounds(295, 229, 100, 22);
									txtSRefund.setEnabled(false);
								}
								{
									lblSRecovery = new Label(cvsSummary,
											SWT.NONE);
									lblSRecovery.setText("Recovery");
									lblSRecovery.setBounds(156, 277, 70, 19);
								}
								{
									txtSRecovery = new Text(cvsSummary,
											SWT.BORDER);
									txtSRecovery.setBounds(295, 275, 100, 22);
									txtSRecovery.setEnabled(false);
								}
								{
									lblSCommDD = new Label(cvsSummary, SWT.NONE);
									lblSCommDD.setText("Commission before DD");
									lblSCommDD.setBounds(156, 323, 125, 23);
								}
								{
									txtSCBeforeDD = new Text(cvsSummary,
											SWT.BORDER);
									txtSCBeforeDD.setBounds(295, 322, 100, 22);
									txtSCBeforeDD.setEnabled(false);
								}
								{
									lblTotalCommission = new Label(cvsSummary,
											SWT.NONE);
									lblTotalCommission
											.setText("Total Commission");
									lblTotalCommission.setBounds(156, 371, 120,
											30);
								}
								{
									txtSTotal = new Text(cvsSummary, SWT.BORDER);
									txtSTotal.setBounds(295, 370, 100, 20);
									txtSTotal.setEnabled(false);
								}
								{
									lblParticulars = new Label(cvsSummary,
											SWT.NONE);
									lblParticulars.setText("Particulars");
									lblParticulars.setBounds(156, 12, 80, 20);
								}
								{
									lblCommssion = new Label(cvsSummary,
											SWT.NONE);
									lblCommssion.setText("Commission");
									lblCommssion.setBounds(298, 12, 70, 20);
								}
							}
						}

					}
				}

				{
					tiBooking = new TabItem(tbfCommission, SWT.NONE);
					tiBooking.setText(BOOKING_TAB);
					
					
					{
						if (!own_station || beanutil.isAdminHead()) {
							Canvas cvsBook = new Canvas(tbfCommission, SWT.NONE);
							tblBooking = new Table(cvsBook, SWT.H_SCROLL
									| SWT.FULL_SELECTION | SWT.V_SCROLL
									| SWT.BORDER | SWT.MULTI);

							tblBooking.setHeaderVisible(true);
							tblBooking.setLinesVisible(true);
							tblBooking.setBounds(19, 12, 576, 450);
							tiBooking.setControl(cvsBook);
							{
								colBSNo = new TableColumn(tblBooking, SWT.NONE | SWT.RIGHT);
								colBSNo.setText("S.No");
								colBSNo.setWidth(40);
								colBSNo.addListener(SWT.Selection,
										new sortListener());

							}
							{
								colBLrno = new TableColumn(tblBooking, SWT.NONE | SWT.RIGHT);
								colBLrno.setText("LR No");
								colBLrno.setWidth(70);
								colBLrno.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colBBookingDate = new TableColumn(tblBooking,
										SWT.NONE | SWT.RIGHT);
								colBBookingDate.setText("LR Date");
								colBBookingDate.setWidth(80);
								colBBookingDate.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colBMode = new TableColumn(tblBooking, SWT.NONE | SWT.RIGHT);
								colBMode.setText("Mode");
								colBMode.setWidth(94);
								colBMode.addListener(SWT.Selection,
										new sortListener());
							}

							{
								colBBasicFreight = new TableColumn(tblBooking,
										SWT.NONE | SWT.RIGHT);
								colBBasicFreight.setText("Basic Freight");
								colBBasicFreight.setWidth(80);
								colBBasicFreight.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colBPercent = new TableColumn(tblBooking,
										SWT.NONE | SWT.RIGHT);
								colBPercent.setText("%");
								colBPercent.setWidth(94);
								colBPercent.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colBComnAmt = new TableColumn(tblBooking,
										SWT.NONE | SWT.RIGHT);
								colBComnAmt.setText("Commission");
								colBComnAmt.setWidth(95);
								colBComnAmt.addListener(SWT.Selection,
										new sortListener());
							}
						}
					}
				}
				{

					tiCCC = new TabItem(tbfCommission, SWT.NONE);
					tiCCC.setText(CCC_TAB);

					{
						if (!own_station || beanutil.isAdminHead()) {
							Canvas cvsCCC = new Canvas(tbfCommission, SWT.NONE);
							// cvsCCC.setSize(600, 349);
							tblCCC = new Table(cvsCCC, SWT.H_SCROLL
									| SWT.FULL_SELECTION | SWT.V_SCROLL
									| SWT.BORDER | SWT.MULTI);

							tblCCC.setHeaderVisible(true);
							tblCCC.setLinesVisible(true);
							tblCCC.setBounds(20, 10, 576, 450);
							tiCCC.setControl(cvsCCC);
							{
								colCSNo = new TableColumn(tblCCC, SWT.NONE | SWT.RIGHT);
								colCSNo.setText("S.No");
								colCSNo.setWidth(40);
								colCSNo.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colCLrNo = new TableColumn(tblCCC, SWT.NONE | SWT.RIGHT);
								colCLrNo.setText("LR No");
								colCLrNo.setWidth(82);
								colCLrNo.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colCBookingDate = new TableColumn(tblCCC,
										SWT.NONE | SWT.RIGHT);
								colCBookingDate.setText("Date");
								colCBookingDate.setWidth(90);
								colCBookingDate.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colCLrType = new TableColumn(tblCCC, SWT.NONE | SWT.RIGHT);
								colCLrType.setText("LR Type");
								colCLrType.setWidth(75);
								colCLrType.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colCBasicFreight = new TableColumn(tblCCC,
										SWT.NONE | SWT.RIGHT);
								colCBasicFreight.setText("Basic Freight");
								colCBasicFreight.setWidth(95);
								colCBasicFreight.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colCPercentCharged = new TableColumn(tblCCC,
										SWT.NONE | SWT.RIGHT);
								colCPercentCharged.setText("% Charged");
								colCPercentCharged.setWidth(80);
								colCPercentCharged.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colCommissionAmt = new TableColumn(tblCCC,
										SWT.NONE | SWT.RIGHT);
								colCommissionAmt.setText("CC Refunded");
								colCommissionAmt.setWidth(93);
								colCommissionAmt.addListener(SWT.Selection,
										new sortListener());
							}

						}
					}
				}
				{
					tiDelivery = new TabItem(tbfCommission, SWT.NONE);
					tiDelivery.setText(DELIVERY_TAB);
					{
						if (!own_station || beanutil.isAdminHead()) {
							Canvas cvsDel = new Canvas(tbfCommission, SWT.NONE);
							tblDelivery = new Table(cvsDel, SWT.H_SCROLL
									| SWT.FULL_SELECTION | SWT.V_SCROLL
									| SWT.BORDER | SWT.MULTI);

							tblDelivery.setHeaderVisible(true);
							tblDelivery.setLinesVisible(true);
							tblDelivery.setBounds(20, 10, 691, 450);
							tiDelivery.setControl(cvsDel);
							{
								colDSNo = new TableColumn(tblDelivery, SWT.NONE | SWT.RIGHT);
								colDSNo.setText("S.No");
								colDSNo.setWidth(50);
								colDSNo.addListener(SWT.Selection,
										new sortListener());
							}
							{
								ColDLrno = new TableColumn(tblDelivery,
										SWT.NONE | SWT.RIGHT);
								ColDLrno.setText("LR No");
								ColDLrno.setWidth(107);
								ColDLrno.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colDBookingDate = new TableColumn(tblDelivery,
										SWT.NONE | SWT.RIGHT);
								colDBookingDate.setText("Lr Date");
								colDBookingDate.setWidth(115);
								colDBookingDate.addListener(SWT.Selection,
										new sortListener());
							}

							{
								colDDeliveredDate = new TableColumn(
										tblDelivery, SWT.NONE | SWT.RIGHT);
								colDDeliveredDate.setText("Delivered Date");
								colDDeliveredDate.setWidth(115);
								colDDeliveredDate.addListener(SWT.Selection,
										new sortListener());
							}

							{
								colDLrType = new TableColumn(tblDelivery,
										SWT.NONE | SWT.RIGHT);
								colDLrType.setText("LR Type");
								colDLrType.setWidth(107);
								colDLrType.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colDActualWeight = new TableColumn(tblDelivery,
										SWT.NONE | SWT.RIGHT);
								colDActualWeight.setText("Weight");
								colDActualWeight.setWidth(86);
								colDActualWeight.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colDCommission = new TableColumn(tblDelivery,
										SWT.NONE | SWT.RIGHT);
								colDCommission.setText("Commision");
								colDCommission.setWidth(88);
								colDCommission.addListener(SWT.Selection,
										new sortListener());
							}
						}
					}
				}

				/*{
					tiDDDetails = new TabItem(tbfCommission, SWT.NONE);
					tiDDDetails.setText(DDDETAILS_TAB);
					{
						if (!own_station || beanutil.isAdminHead()) {
							Canvas cvsDDDetails = new Canvas(tbfCommission,
									SWT.NONE);
							tblDDDetails = new Table(cvsDDDetails, SWT.H_SCROLL
									| SWT.FULL_SELECTION | SWT.V_SCROLL
									| SWT.BORDER | SWT.MULTI);

							tblDDDetails.setHeaderVisible(true);
							tblDDDetails.setLinesVisible(true);
							tblDDDetails.setBounds(20, 10, 780, 415);
							tiDDDetails.setControl(cvsDDDetails);
							{
								colDdSNo = new TableColumn(tblDDDetails,
										SWT.NONE);
								colDdSNo.setText("S.No");
								colDdSNo.setWidth(50);
								colDdSNo.addListener(SWT.Selection,
										new sortListener());
							}
							{
								ColDdLrno = new TableColumn(tblDDDetails,
										SWT.NONE);
								ColDdLrno.setText("LR No");
								ColDdLrno.setWidth(70);
								ColDdLrno.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colDdBookingDate = new TableColumn(
										tblDDDetails, SWT.NONE);
								colDdBookingDate.setText("Lr Date");
								colDdBookingDate.setWidth(80);
								colDdBookingDate.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colDdLrType = new TableColumn(tblDDDetails,
										SWT.NONE);
								colDdLrType.setText("LR Type");
								colDdLrType.setWidth(70);
								colDdLrType.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colDdCharge = new TableColumn(tblDDDetails,
										SWT.NONE);
								colDdCharge.setText("DD Charge");
								colDdCharge.setWidth(70);
								colDdCharge.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colDdExtra = new TableColumn(tblDDDetails,
										SWT.NONE);
								colDdExtra.setText("DD Extra");
								colDdExtra.setWidth(70);
								colDdExtra.addListener(SWT.Selection,
										new sortListener());
							}

							{
								ColDdOthers = new TableColumn(tblDDDetails,
										SWT.NONE);
								ColDdOthers.setText("Others");
								ColDdOthers.setWidth(70);
								ColDdOthers.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colDdDemurrage = new TableColumn(tblDDDetails,
										SWT.NONE);
								colDdDemurrage.setText("Demurrage");
								colDdDemurrage.setWidth(70);
								colDdDemurrage.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colDdUndCrg = new TableColumn(tblDDDetails,
										SWT.NONE);
								colDdUndCrg.setText("Under Crg");
								colDdUndCrg.setWidth(80);
								colDdUndCrg.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colDdPTCrg = new TableColumn(tblDDDetails,
										SWT.NONE);
								colDdPTCrg.setText("P&T Crg");
								colDdPTCrg.setWidth(70);
								colDdPTCrg.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colDdTotal = new TableColumn(tblDDDetails,
										SWT.NONE);
								colDdTotal.setText("Total");
								colDdTotal.setWidth(70);
								colDdTotal.addListener(SWT.Selection,
										new sortListener());
							}
						}
					}
				}*/

				{
					tiRefund = new TabItem(tbfCommission, SWT.NONE);
					tiRefund.setText(REFUND_TAB);
					{
						Canvas cvsRef = new Canvas(tbfCommission, SWT.NONE);
						tblRefund = new Table(cvsRef, SWT.H_SCROLL
								| SWT.FULL_SELECTION | SWT.V_SCROLL
								| SWT.BORDER | SWT.MULTI);
						tblRefund.setHeaderVisible(true);
						tblRefund.setLinesVisible(true);
						tblRefund.setBounds(20, 10, 740, 450);
						tiRefund.setControl(cvsRef);
						{
							colRefSNo = new TableColumn(tblRefund, SWT.NONE | SWT.RIGHT);
							colRefSNo.setText("S.No");
							colRefSNo.setWidth(70);
							colRefSNo.addListener(SWT.Selection,
									new sortListener());
						}
						{
							colRefDesc = new TableColumn(tblRefund, SWT.NONE | SWT.RIGHT);
							colRefDesc.setText("Description");
							colRefDesc.setWidth(200);
							colRefDesc.addListener(SWT.Selection,
									new sortListener());
						}
						{
							colRefContact = new TableColumn(tblRefund, SWT.NONE | SWT.RIGHT);
							colRefContact.setText("Contact");
							colRefContact.setWidth(177);
							colRefContact.addListener(SWT.Selection,
									new sortListener());
						}
						{
							colRefAmount = new TableColumn(tblRefund, SWT.NONE | SWT.RIGHT);
							colRefAmount.setText("Amount");
							colRefAmount.setWidth(107);
							colRefAmount.addListener(SWT.Selection,
									new sortListener());
						}
						{
							colRefInst = new TableColumn(tblRefund, SWT.NONE | SWT.RIGHT);
							colRefInst.setText("Installment");
							colRefInst.setWidth(80);
							colRefInst.addListener(SWT.Selection,
									new sortListener());
						}
						{
							colRefDate = new TableColumn(tblRefund, SWT.NONE | SWT.RIGHT);
							colRefDate.setText("Date Created");
							colRefDate.setWidth(80);
							colRefDate.addListener(SWT.Selection,
									new sortListener());
						}
					}
				}
				{
					tiRecovery = new TabItem(tbfCommission, SWT.NONE);
					tiRecovery.setText(RECOVERY_TAB);
					{
						Canvas cvsRec = new Canvas(tbfCommission, SWT.NONE | SWT.RIGHT);
						tblRecovery = new Table(cvsRec, SWT.H_SCROLL
								| SWT.FULL_SELECTION | SWT.V_SCROLL
								| SWT.BORDER | SWT.MULTI);
						tblRecovery.setHeaderVisible(true);
						tblRecovery.setLinesVisible(true);
						tblRecovery.setBounds(20, 10, 740, 450);
						tiRecovery.setControl(cvsRec);
						{
							colRecSNo = new TableColumn(tblRecovery, SWT.NONE | SWT.RIGHT);
							colRecSNo.setText("S.No");
							colRecSNo.setWidth(70);
							colRecSNo.addListener(SWT.Selection,
									new sortListener());
						}
						{
							colRecDesc = new TableColumn(tblRecovery, SWT.NONE | SWT.RIGHT);
							colRecDesc.setText("Description");
							colRecDesc.setWidth(200);
							colRecDesc.addListener(SWT.Selection,
									new sortListener());
						}
						{
							colRecContact = new TableColumn(tblRecovery,
									SWT.NONE | SWT.RIGHT);
							colRecContact.setText("Contact");
							colRecContact.setWidth(177);
							colRecContact.addListener(SWT.Selection,
									new sortListener());
						}
						{
							colRecAmount = new TableColumn(tblRecovery,
									SWT.NONE | SWT.RIGHT);
							colRecAmount.setText("Amount");
							colRecAmount.setWidth(107);
							colRecAmount.addListener(SWT.Selection,
									new sortListener());
						}

						{
							colRecInst = new TableColumn(tblRecovery, SWT.NONE | SWT.RIGHT);
							colRecInst.setText("Installment");
							colRecInst.setWidth(80);
							colRecInst.addListener(SWT.Selection,
									new sortListener());
						}

						{
							colRecDate = new TableColumn(tblRecovery, SWT.NONE | SWT.RIGHT);
							colRecDate.setText("Date Created");
							colRecDate.setWidth(80);
							colRecDate.addListener(SWT.Selection,
									new sortListener());
						}

					}
				}

			}
			
			tbfCommission.addSelectionListener(new CommissionAction());
			tbfCommission.setSelection(0);
		}

		if (null != handler &&(!own_station || beanutil.isAdminHead())) {
			selectedDate = txtMonth.getText();
			
			String station = cbStation.getText();			
			if(!station.equalsIgnoreCase("")){
				int index = station.indexOf(" - ");
				station = station.substring(index + 3);
			
				CSummaryDTo = handler.getCommisionSummary(null, station);
				clearSummaryValues();
			}
			if(CSummaryDTo != null)
				fillSummaryValues(CSummaryDTo);
		}

	}
	

	


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

			int selectionIndex = tbfCommission.getSelectionIndex();
			TableColumn column = (TableColumn) e.widget;

			if (selectionIndex == 1) {
				if (column == colBSNo) {
					sortByFloat(0, tblBooking);
				} else if (column == colBLrno) {
					sortByLrNo(1, tblBooking);
				} else if (column == colBBookingDate) {
					sortByDate(2, tblBooking);
				} else if (column == colBMode) {
					sortByString(3, tblBooking);
				} else if (column == colBBasicFreight) {
					sortByFloat(4, tblBooking);
				} else if (column == colBPercent) {
					sortByFloat(5, tblBooking);
				} else if (column == colBComnAmt) {
					sortByFloat(6, tblBooking);

				}
			} else if (selectionIndex == 2) {
				if (column == colCSNo) {
					sortByFloat(0, tblCCC);
				} else if (column == colCLrNo) {
					sortByLrNo(1, tblCCC);
				} else if (column == colCBookingDate) {
					sortByDate(2, tblCCC);
				} else if (column == colCLrType) {
					sortByString(3, tblCCC);
				} else if (column == colCBasicFreight) {
					sortByFloat(4, tblCCC);
				} else if (column == colCPercentCharged) {
					sortByFloat(5, tblCCC);
				} else if (column == colCommissionAmt) {
					sortByFloat(6, tblCCC);

				}
			} else if (selectionIndex == 3) {
				if (column == colDSNo) {
					sortByFloat(0, tblDelivery);
				} else if (column == ColDLrno) {
					sortByLrNo(1, tblDelivery);
				} else if (column == colDBookingDate) {
					sortByDate(2, tblDelivery);
				} else if (column == colDDeliveredDate) {
					sortByDate(3, tblDelivery);
				} else if (column == colDLrType) {
					sortByString(4, tblDelivery);
				} else if (column == colDActualWeight) {
					sortByFloat(5, tblDelivery);
				} else if (column == colDCommission) {
					sortByFloat(6, tblDelivery);

				}
			}

			else if (selectionIndex == 4) {
				if (column == colDdSNo) {
					sortByFloat(0, tblDDDetails);
				} else if (column == ColDdLrno) {
					sortByLrNo(1, tblDDDetails);
				} else if (column == colDdBookingDate) {
					sortByDate(2, tblDDDetails);
				} else if (column == colDdLrType) {
					sortByString(3, tblDDDetails);
				} else if (column == colDdCharge) {
					sortByFloat(4, tblDDDetails);
				} else if (column == colDdExtra) {
					sortByFloat(5, tblDDDetails);
				} else if (column == ColDdOthers) {
					sortByFloat(6, tblDDDetails);

				} else if (column == colDdDemurrage) {
					sortByFloat(7, tblDDDetails);

				} else if (column == colDdUndCrg) {
					sortByFloat(8, tblDDDetails);

				} else if (column == colDdPTCrg) {
					sortByFloat(9, tblDDDetails);

				} else if (column == colDdTotal) {
					sortByFloat(10, tblDDDetails);

				}
			} else if (selectionIndex == 5) {
				if (column == colRefSNo) {
					sortByFloat(0, tblRefund);
				} else if (column == colRefDesc) {
					sortByString(1, tblRefund);
				} else if (column == colRefContact) {
					sortByString(2, tblRefund);
				} else if (column == colRefAmount) {
					sortByFloat(3, tblRefund);
				} else if (column == colRefInst) {
					sortByString(4, tblRefund);
				} else if (column == colRefDate) {
					sortByDate(5, tblRefund);
				}
			} else if (selectionIndex == 6) {
				if (column == colRecSNo) {
					sortByFloat(0, tblRecovery);
				} else if (column == colRecDesc) {
					sortByString(1, tblRecovery);
				} else if (column == colRecContact) {
					sortByString(2, tblRecovery);
				} else if (column == colRecAmount) {
					sortByFloat(3, tblRecovery);
				} else if (column == colRecInst) {
					sortByString(4, tblRecovery);
				} else if (column == colRecDate) {
					sortByDate(5, tblRecovery);
				}
			}
		}
	}

	public class CommissionAction implements SelectionListener {
	
		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {
			// TODO Auto-generated method stub

		}
		public void display(String status,Font font,Color color)
		{
			lblStatusBar.setText(status);
			lblStatusBar.setFont(font);
			lblStatusBar.setForeground(LINK_FOCUS_COLOR);
		}

		@Override
		public void widgetSelected(SelectionEvent event) {
			
			Object source = event.getSource();
			Date date = null;
			try {
								
				if(source == cbBranch){
					handleBranchAction(cbBranch, cbStation);
				}else if(source == cbStation){				
					/*String station = cbStation.getText();			
					if(!station.equalsIgnoreCase("")){
						int index = station.indexOf(" - ");
						station = station.substring(index + 3);
					}
					prevSelectedStation = station;*/
					
				}else if (source == btnGo || source == tbfCommission){
					
					SimpleDateFormat format = new SimpleDateFormat("MM-yyyy");
					date = format.parse(txtMonth.getText());
					String station = cbStation.getText();			
					if(!station.equalsIgnoreCase("")){
						int index = station.indexOf(" - ");
						station = station.substring(index + 3);
					}
					
					String selectedTab = tbfCommission.getSelection()[0].getText();
					
					if (selectedTab.equals(SUMMARY_TAB)
							&& (!own_station || beanutil.isAdminHead())) {
					/*	if (CSummaryDTo == null
								|| !selectedDate.equals(txtMonth.getText()) 
								|| !prevSelectedStation.equalsIgnoreCase(station)) {*/
							
							btnExportXL.setEnabled(false);
							btnExportPDF.setEnabled(false);
							btnprint.setEnabled(false);
							prevSelectedStation = station;
							
							int month = date.getMonth(); 
 							int year =  date.getYear();
 							Date beanDate = BeanUtil.getThree_month_updated();
 							int smonth = beanDate.getMonth();
							int syear = beanDate.getYear();
 							
 										
							if((month <= smonth) && (year <= syear)){
					       
					       
					        	CSummaryDTo = handler.getCommisionSummaryHistory(date,station);
					        }else{
					        	//if(BeanUtil.getDbName() == null || BeanUtil.getDbName().equals("")){
					        		CSummaryDTo = handler.getCommisionSummary(date,station);
					        	//}
					        }
							//CSummaryDTo = handler.getCommisionSummary(date, station);
							clearSummaryValues();
							if (CSummaryDTo != null) {
								fillSummaryValues(CSummaryDTo);
							}
							selectedDate = txtMonth.getText();
						//}
					} else if (selectedTab.equals(BOOKING_TAB)
							&& (!own_station || beanutil.isAdminHead())) {
						/*if (BCommDTO == null
								|| (BCommDTO[0].getBookingDate().getMonth() != date.getMonth()) 
								|| !prevSelectedStation.equalsIgnoreCase(station)) {*/
							prevSelectedStation = station;
							int month = date.getMonth(); 
 							int year =  date.getYear();
 							Date beanDate = BeanUtil.getThree_month_updated();
 							int smonth = beanDate.getMonth();
							int syear = beanDate.getYear();
 														
							if((month <= smonth) && (year <= syear)){
								BCommDTO = handler.getBookingCommissionListHistory(date, station);
							}
							else
							{
								BCommDTO = handler.getBookingCommissionList(date, station);
							}
							if (tblBooking != null)
								tblBooking.removeAll();

							if (BCommDTO != null) {
								fillBookingCommValues(BCommDTO);
							}
						//}

					} else if (selectedTab.equals(DELIVERY_TAB)
							&& (!own_station || beanutil.isAdminHead())) {
						/*if (DCommDTO == null
								|| (DCommDTO[0].getDeliveredDate().getMonth() != date.getMonth())
								|| !prevSelectedStation.equalsIgnoreCase(station)) {*/
							prevSelectedStation = station;
							int month = date.getMonth(); 
 							int year =  date.getYear();
 							Date beanDate = BeanUtil.getThree_month_updated();
 							int smonth = beanDate.getMonth();
							int syear = beanDate.getYear();
 														
							if((month <= smonth) && (year <= syear)){
								DCommDTO = handler.getDeliveryCommissionListHistory(date, station);
							}
							else{
								DCommDTO = handler.getDeliveryCommissionList(date, station);
							}
							if (tblDelivery != null)
								tblDelivery.removeAll();

							if (DCommDTO != null) {
								fillDeliveryCommValues(DCommDTO);
							}
					//	}

					} else if (selectedTab.equals(REFUND_TAB)) {
						RefundDTO = handler.getRefundList(date, station);
						if (tblRefund != null)
							tblRefund.removeAll();
						if (RefundDTO != null) {
							fillRefundValues(RefundDTO);
						}

					} else if (selectedTab.equals(RECOVERY_TAB)) {
						RecoveryDTO = handler.getRecoveryList(date, station);
						if (tblRecovery != null)
							tblRecovery.removeAll();
						if (RecoveryDTO != null) {
							fillRecoveryValues(RecoveryDTO);
						}

					} else if (selectedTab.equals(CCC_TAB)
							&&(!own_station || beanutil.isAdminHead())) {
						/*if (CCommDTO == null
								|| (CCommDTO[0].getBookingDate().getMonth() != date.getMonth())
								|| !prevSelectedStation.equalsIgnoreCase(station)) {*/
							prevSelectedStation = station;
							int month = date.getMonth(); 
 							int year =  date.getYear();
 							Date beanDate = BeanUtil.getThree_month_updated();
 							int smonth = beanDate.getMonth();
							int syear = beanDate.getYear();
 														
							if((month <= smonth) && (year <= syear)){
								CCommDTO = handler.getCartageCommissionListHistory(date, station);
							}
							else{
								CCommDTO = handler.getCartageCommissionList(date, station);
							}
							
							if (tblCCC != null){
								tblCCC.removeAll();
							}
							if (CCommDTO != null) {
								fillCartageCommValues(CCommDTO);
							}
						//}
					} else if (selectedTab.equals(DDDETAILS_TAB)
							&& (!own_station || beanutil.isAdminHead())) {
						/*if (ddDetailsDTO == null
								|| !selectedDateDDC.equals(txtMonth.getText())
								|| !prevSelectedStation.equalsIgnoreCase(station)) {*/
							prevSelectedStation = station;
							int month = date.getMonth(); 
 							int year =  date.getYear();
 							Date beanDate = BeanUtil.getThree_month_updated();
 							int smonth = beanDate.getMonth();
							int syear = beanDate.getYear();
 														
							if((month <= smonth) && (year <= syear)){
								ddDetailsDTO = handler.getDDDetailsHistory(date, station);
							}
							else{
								ddDetailsDTO = handler.getDDDetails(date, station);
							}
							if (tblDDDetails != null){
								tblDDDetails.removeAll();
							}
							if (ddDetailsDTO != null) {
								fillDDDetailsValues(ddDetailsDTO);
							}
							selectedDateDDC = txtMonth.getText();
						//}
					}
				}
				
				else if(source == btnExportXL)
				{	
					String selectedTab = tbfCommission.getSelection()[0].getText();
					if (selectedTab.equals(BOOKING_TAB)
							&& (!own_station || beanutil.isAdminHead())) {
						ArrayList<BookingCommissionDTO> list = null;
						String value[] = getHeading();
						
						try {
													
							list = getBookingTable();
							prepareXL(list, "BOOKING_TAB",BOOKING_JRXML ,value);
						
							btnExportXL.setEnabled(false);
						} catch (Exception exception) {
							displayError("Problem while Exporting Excel");
						}

					} else if (selectedTab.equals(DELIVERY_TAB)
							&& (!own_station || beanutil.isAdminHead())) {
						ArrayList<DeliveryCommissionDTO> list = null;
						String value[] = getHeading();
						try {
							
							list = getDeliveryTable();
							prepareXL(list, "DELIVERY_TAB",DELIVERY_JRXML ,value);
							btnExportXL.setEnabled(false);
						} catch (Exception exception) {
							exception.printStackTrace();
							displayError("Problem while Exporting Excel");
						}
					} else if (selectedTab.equals(CCC_TAB)
							&&(!own_station || beanutil.isAdminHead())) {
						ArrayList<CartageCommissionDTO> list = null;
						String value[] = getHeading();
						try {
							
							list = getCCCommissionTable();
							prepareXL(list, "CCC_TAB",CCC_JRXML ,value);
													
							btnExportXL.setEnabled(false);
						} catch (Exception exception) {
							displayError("Problem while Exporting Excel");
						}
					} else if (selectedTab.equals(DDDETAILS_TAB)
							&& (!own_station || beanutil.isAdminHead())) {
						
						ArrayList<DDDetailsDTO> list = null;
						String value[] = getHeading();
						try {
							
							list = getDDDetailsTable();
							prepareXL(list, "DDDETAILS_TAB",DDDETAILS_JRXML ,value);
							
							btnExportXL.setEnabled(false);
						} catch (Exception exception) {
							displayError("Problem while Exporting Excel");
						}
					}else if (selectedTab.equals(RECOVERY_TAB)
							&& (!own_station || beanutil.isAdminHead())) {
						
						ArrayList<RefundRecoveryDTO> list = null;
						String value[] = getHeading();
						try {
							
							list = getRecoveryTable();
							prepareXL(list, "RECOVERY_TAB",RECOVERY_JRXML ,value);
							
							btnExportXL.setEnabled(false);
						} catch (Exception exception) {
							displayError("Problem while Exporting Excel");
						}
					}else if (selectedTab.equals(REFUND_TAB)
							&& (!own_station || beanutil.isAdminHead())) {
						
						ArrayList<RefundRecoveryDTO> list = null;
						String value[] = getHeading();
						try {
							
							list = getRefundTable();
							prepareXL(list, "REFUND_TAB",REFUND_JRXML ,value);
							
							btnExportXL.setEnabled(false);
						} catch (Exception exception) {
							displayError("Problem while Exporting Excel");
						}
					}
					
				}
				
				else if(source == btnExportPDF)
				{	
					String selectedTab = tbfCommission.getSelection()[0].getText();
					if (selectedTab.equals(BOOKING_TAB)
							&& (!own_station || beanutil.isAdminHead())) {
						ArrayList<BookingCommissionDTO> list = null;
						String value[] = getHeading();
						try {
													
							list = getBookingTable();
							preparePDF(list, "BOOKING_TAB",BOOKING_JRXML ,value);
							
							btnExportPDF.setEnabled(false);
						} catch (Exception exception) {
							displayError("Problem while Exporting PDF");
						}

					} else if (selectedTab.equals(DELIVERY_TAB)
							&& (!own_station || beanutil.isAdminHead())) {
						ArrayList<DeliveryCommissionDTO> list = null;
						String value[] = getHeading();
						try {
							
							list = getDeliveryTable();
							preparePDF(list, "DELIVERY_TAB",DELIVERY_JRXML ,value);
							btnExportPDF.setEnabled(false);
						} catch (Exception exception) {
							exception.printStackTrace();
							displayError("Problem while Exporting PDF");
						}
					} else if (selectedTab.equals(CCC_TAB)
							&&(!own_station || beanutil.isAdminHead())) {
						ArrayList<CartageCommissionDTO> list = null;
						String value[] = getHeading();
						try {
							
							list = getCCCommissionTable();
							preparePDF(list, "CCC_TAB",CCC_JRXML ,value);
													
							btnExportPDF.setEnabled(false);
						} catch (Exception exception) {
							displayError("Problem while Exporting PDF");
						}
					} else if (selectedTab.equals(DDDETAILS_TAB)
							&& (!own_station || beanutil.isAdminHead())) {
						
						ArrayList<DDDetailsDTO> list = null;
						String value[] = getHeading();
						try {
							
							list = getDDDetailsTable();
							preparePDF(list, "DDDETAILS_TAB",DDDETAILS_JRXML ,value);
							
							btnExportPDF.setEnabled(false);
						} catch (Exception exception) {
							displayError("Problem while Exporting PDF");
						}
					}else if (selectedTab.equals(RECOVERY_TAB)
							&& (!own_station || beanutil.isAdminHead())) {
						
						ArrayList<RefundRecoveryDTO> list = null;
						String value[] = getHeading();
						try {
							
							list = getRecoveryTable();
							preparePDF(list, "RECOVERY_TAB",RECOVERY_JRXML ,value);
							
							btnExportPDF.setEnabled(false);
						} catch (Exception exception) {
							displayError("Problem while Exporting PDF");
						}
					}else if (selectedTab.equals(REFUND_TAB)
							&& (!own_station || beanutil.isAdminHead())) {
						
						ArrayList<RefundRecoveryDTO> list = null;
						String value[] = getHeading();
						
						try {
							
							list = getRefundTable();
							preparePDF(list, "REFUND_TAB",REFUND_JRXML ,value);
							
							btnExportPDF.setEnabled(false);
						} catch (Exception exception) {
							displayError("Problem while Exporting PDF");
						}
					}
					
				}

				
				else if(source == btnprint)
				{
					String selectedTab = tbfCommission.getSelection()[0].getText();
					if (selectedTab.equals(BOOKING_TAB)
							&& (!own_station || beanutil.isAdminHead())) {
						ArrayList<BookingCommissionDTO> list = null;
						String value[] = getHeading();
						try {
													
							list = getBookingTable();
							handler.preparePrint(list, "BOOKING_TAB",BOOKING_JRXML ,value);
							
							btnprint.setEnabled(false);
						} catch (Exception exception) {
							displayError("Problem while Printing");
						}

					} else if (selectedTab.equals(DELIVERY_TAB)
							&& (!own_station || beanutil.isAdminHead())) {
						ArrayList<DeliveryCommissionDTO> list = null;
						String value[] = getHeading();
						try {
							
							list = getDeliveryTable();
							handler.preparePrint(list, "DELIVERY_TAB",DELIVERY_JRXML ,value);
							btnprint.setEnabled(false);
						} catch (Exception exception) {
							exception.printStackTrace();
							displayError("Problem while Printing");
						}
					} else if (selectedTab.equals(CCC_TAB)
							&&(!own_station || beanutil.isAdminHead())) {
						ArrayList<CartageCommissionDTO> list = null;
						String value[] = getHeading();
						try {
							
							list = getCCCommissionTable();
							handler.preparePrint(list, "CCC_TAB", CCC_JRXML,value);
													
							btnprint.setEnabled(false);
						} catch (Exception exception) {
							displayError("Problem while Printing");
						}
					} else if (selectedTab.equals(DDDETAILS_TAB)
							&& (!own_station || beanutil.isAdminHead())) {
						
						ArrayList<DDDetailsDTO> list = null;
						String value[] = getHeading();
						try {
							
							list = getDDDetailsTable();
							handler.preparePrint(list, "DDDETAILS_TAB",DDDETAILS_JRXML ,value);
							
							btnprint.setEnabled(false);
						} catch (Exception exception) {
							displayError("Problem while Printing");
						}
					}else if (selectedTab.equals(RECOVERY_TAB)
							&& (!own_station || beanutil.isAdminHead())) {
						
						ArrayList<RefundRecoveryDTO> list = null;
						String value[] = getHeading();
						try {
							
							list = getRecoveryTable();
							handler.preparePrint(list, "RECOVERY_TAB",RECOVERY_JRXML ,value);
							
							btnprint.setEnabled(false);
						} catch (Exception exception) {
							displayError("Problem while Printing");
						}
					}else if (selectedTab.equals(REFUND_TAB)
							&& (!own_station || beanutil.isAdminHead())) {
						
						ArrayList<RefundRecoveryDTO> list = null;
						String value[] = getHeading();
						try {
							
							list = getRefundTable();
							handler.preparePrint(list, "REFUND_TAB",REFUND_JRXML ,value);
							
							btnprint.setEnabled(false);
						} catch (Exception exception) {
							displayError("Problem while Printing");
						}
					}
					
				}



				
				
			} catch (Exception exception) {
				exception.printStackTrace();
			}

		}

		/*private CommissionSummaryDTO getCommisionSummary(Date date,
				String station) {
			// TODO Auto-generated method stub
			CommissionSummaryDTO CSummaryDTo =null;
			try {
				int month = date.getmonth;
				int smonth = BeanUtil.getThree_month_updated().getMonth();
				int syear = BeanUtil.getThree_month_updated().getYear();
			if((month ==smonth) && (year == syear) ){
				System.out.println("in ddr histry-->"+date);
				CSummaryDTo = handler.getCommisionSummary(date, station);
			}else{
				CSummaryDTo = handler.getCommisionSummary(date, station);
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return CSummaryDTo;
			
			
		}*/
		private String[] getHeading() {
			
			String value[] = new String[3]; 
			
			String branchCode = cbBranch.getText();
			if (!branchCode.equalsIgnoreCase("All")) {
				int index = branchCode.indexOf(" - ");
				value[0] = branchCode.substring(index + 3);
			}
			else
				value[0] ="All";
			
			String stationCode = cbStation.getText();
			
			if (!stationCode.equalsIgnoreCase("All")) {
				int index = stationCode.indexOf(" - ");
				value[1] = stationCode.substring(index + 3);
			}
			else
				value[1] ="All";
			
			
			value[2] = txtMonth.getText();
			
			return value;
		}

	}
	
	
	private void preparePDF(ArrayList list,
			String fileName, String jrxmlFile, String[] value) {
		// TODO Auto-generated method stub
		
		try {
			StationCommissionComposite.display("Exporting...", STATUS_SUCCESS,SUCCESS_FONT_COLOR);
			handler.printReportPDF(list, fileName, jrxmlFile,value);
			
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
					StationCommissionComposite.display("Saving...", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					copyFile(xl, df, dialog.getFileName());
				}
				StationCommissionComposite.display("PDF Saved Successfully",	STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			}

		} catch (Exception e) {
			StationCommissionComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			e.printStackTrace();
		}

		
	}

	private void prepareXL(ArrayList list,
			String fileName, String jrxmlFile, String[] value) {
		// TODO Auto-generated method stub
		try {
			//AdminComposite.display("Exporting...", STATUS_SUCCESS,SUCCESS_FONT_COLOR);
			handler.printReportExcel(list, fileName, jrxmlFile,value);

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
					StationCommissionComposite.display("Saving...", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					copyFile(xl, df, dialog.getFileName());
				}
				StationCommissionComposite.display("Excel Saved Successfully",STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			}
			else
			{
				StationCommissionComposite.display("Excel Saving Cancelled",STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			}
		} catch (Exception e) {
			//AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			e.printStackTrace();
		}

		
	}



private static void display(String status, Font font,
			Color successFontColor) {
		// TODO Auto-generated method stub
	lblStatusBar.setText(status);
	lblStatusBar.setFont(font);
	lblStatusBar.setForeground(LINK_FOCUS_COLOR);
		
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


private ArrayList<DeliveryCommissionDTO> getDeliveryTable() throws Exception {
	DeliveryCommissionDTO dto = null;
	ArrayList<DeliveryCommissionDTO> list = new ArrayList<DeliveryCommissionDTO>();
	int length = 0;
	DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	TableItem[] items = tblDelivery.getItems();
	if (items != null) {
		length = items.length;
	}
	if (length > 0) {
		for (int i = 0; i < length; i++) {
			dto = new DeliveryCommissionDTO();
			
			dto.setLrno(items[i].getText(1));
			
			if (!items[i].getText(2).equals(""))
				dto.setLrDate(dateFormat.parse(items[i].getText(2)));
			
			if (!items[i].getText(3).equals(""))
				dto.setDeliveredDate(dateFormat.parse(items[i].getText(3))); 
			
			if (!items[i].getText(4).equals(""))
				dto.setLrType(items[i].getText(4));
			
			if (!items[i].getText(5).equals(""))
				dto.setActualWeight(Float.parseFloat(items[i].getText(5)));
			
			if (!items[i].getText(6).equals(""))
				dto.setDc(Float.parseFloat(items[i].getText(6)));
			
			list.add(dto);
		}
	}

	return list;
}


private ArrayList<BookingCommissionDTO> getBookingTable() throws Exception {
	BookingCommissionDTO dto = null;
	ArrayList<BookingCommissionDTO> list = new ArrayList<BookingCommissionDTO>();
	int length = 0;
	DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
	TableItem[] items = tblBooking.getItems();
	if (items != null) {
		length = items.length;
	}
	if (length > 0) {
		for (int i = 0; i < length; i++) {
			dto = new BookingCommissionDTO();
			
			dto.setLrno(items[i].getText(1));
			
			if (!items[i].getText(2).equals(""))
				dto.setBookingDate(dateFormat.parse(items[i].getText(2)));
			
			dto.setLrType(items[i].getText(3)); 
			
			if (!items[i].getText(4).equals(""))
			dto.setBft(Float.parseFloat(items[i].getText(4)));
			
			if (!items[i].getText(5).equals(""))
				dto.setBookingCommissionPercent(Float.parseFloat(items[i].getText(5)));
			else
				dto.setBookingCommissionPercent(Float.parseFloat("-1.00"));
			if (!items[i].getText(6).equals(""))
				dto.setBookingCommission(Float.parseFloat(items[i].getText(6)));
			
			list.add(dto);
		}
	}
	
	return list;
}


private ArrayList<CartageCommissionDTO> getCCCommissionTable() throws Exception {
	CartageCommissionDTO dto = null;
	ArrayList<CartageCommissionDTO> list = new ArrayList<CartageCommissionDTO>();
	int length = 0;
	DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
	TableItem[] items = tblCCC.getItems();
	if (items != null) {
		length = items.length;
	}
	if (length > 0) {
		for (int i = 0; i < length; i++) {
			dto = new CartageCommissionDTO();
			
			dto.setLrno(items[i].getText(1));
							
			if (!items[i].getText(2).equals(""))
				dto.setBookingDate(dateFormat.parse(items[i].getText(2)));
			
			dto.setLrType(items[i].getText(3)); 
			
			if (!items[i].getText(4).equals(""))
				dto.setBft(Float.parseFloat(items[i].getText(4)));
			
			if (!items[i].getText(5).equals(""))
				dto.setCcPercentage(Float.parseFloat(items[i].getText(5)));
			else
				dto.setCcPercentage(Float.parseFloat("-1.00"));
			if (!items[i].getText(6).equals(""))
				dto.setCommissionAmount(Float.parseFloat(items[i].getText(6)));
			list.add(dto);
		}
	}

	return list;
}


private ArrayList<DDDetailsDTO> getDDDetailsTable() throws Exception {
	DDDetailsDTO dto = null;
	ArrayList<DDDetailsDTO> list = new ArrayList<DDDetailsDTO>();
	int length = 0;
	DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
	TableItem[] items = tblDDDetails.getItems();
	if (items != null) {
		length = items.length;
	}
	if (length > 0) {
		for (int i = 0; i < length; i++) {
			dto = new DDDetailsDTO();
			
			dto.setLrNo(items[i].getText(1));
			
			if (!items[i].getText(2).equals(""))
				dto.setLrDate(dateFormat.parse(items[i].getText(2)));
			
			dto.setMode(items[i].getText(3)); 
		
			if (!items[i].getText(4).equals(""))
			dto.setDdcFree(Float.parseFloat(items[i].getText(4)));
			
			if (!items[i].getText(5).equals(""))
				dto.setDdExtra(Float.parseFloat(items[i].getText(5)));
			
			if (!items[i].getText(6).equals(""))
				dto.setOthers(Float.parseFloat(items[i].getText(6)));
			
			if (!items[i].getText(7).equals(""))
				dto.setDemurrage(Float.parseFloat(items[i].getText(7)));
			
			if (!items[i].getText(8).equals(""))
				dto.setUnderCharge(Float.parseFloat(items[i].getText(8)));
			
			if (!items[i].getText(9).equals(""))
				dto.setPostageCharge(Float.parseFloat(items[i].getText(9)));
			
			if (!items[i].getText(10).equals(""))
				dto.setTotal(Float.parseFloat(items[i].getText(10)));
			
				
			list.add(dto);
		}
	}

	return list;
}


private ArrayList<RefundRecoveryDTO> getRefundTable() throws Exception {
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


private ArrayList<RefundRecoveryDTO> getRecoveryTable() throws Exception {
	
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


	private void handleBranchAction(Combo branch, Combo cbStation) {

		StationsDTO[] station = null;
		String value = "";
		String stationCode = "";

		try {
			value = branch.getText();
			int index = value.indexOf(" - ");
			stationCode = value.substring(index + 3);

			if (stationCode != null) {
				station = handler.getStations(stationCode);
			}
			if (null != station) {

				int len = station.length;
				cbStation.removeAll();				
				for (int i = 0; i < len; i++) {
					cbStation.add(station[i].getName() + " - "
							+ station[i].getId());
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param BCommDTO
	 * @throws Exception
	 */
	private void fillBookingCommValues(BookingCommissionDTO[] BCommDTO)
			throws Exception {

		float totalBft = 0;
		float totalPercent = 0;
		float totalBComm = 0;

		if (null != BCommDTO) {
			btnExportXL.setEnabled(true);
			btnExportPDF.setEnabled(true);
			btnprint.setEnabled(true);
			for (int i = 0; i < BCommDTO.length; i++) {
				TableItem itemBCommission = new TableItem(tblBooking, SWT.NONE);
				itemBCommission.setText(0, Integer.toString((i + 1)));
				itemBCommission.setText(1, BCommDTO[i].getLrno());
				String cDate = viewdateformat.format(BCommDTO[i]
						.getBookingDate());
				itemBCommission.setText(2, cDate);
				itemBCommission.setText(3, BCommDTO[i].getLrType());

				totalBft = totalBft + BCommDTO[i].getBft();
				itemBCommission.setText(4, decimalFormat.format(BCommDTO[i]
						.getBft()));

				totalPercent = totalPercent
						+ BCommDTO[i].getBookingCommissionPercent();

				itemBCommission.setText(5, decimalFormat.format(BCommDTO[i]
						.getBookingCommissionPercent()));

				totalBComm = totalBComm + BCommDTO[i].getBookingCommission();
				itemBCommission.setText(6, decimalFormat.format(BCommDTO[i]
						.getBookingCommission()));

			}

			final TableItem item1 = new TableItem(tblBooking, SWT.NONE);
			Font font1 = new Font(Display.getDefault(), "Tahoma", 8, SWT.BOLD);
			item1.setFont(font1);
			item1.setText(3, "TOTAL");
			item1.setText(4, decimalFormat.format(totalBft));

			/*
			 * item1.setText(5, decimalFormat .format((totalBComm / totalBft) *
			 * 100));
			 */
			item1.setText(6, decimalFormat.format(totalBComm));
			

		}else{
			btnExportXL.setEnabled(false);
			btnExportPDF.setEnabled(false);
			btnprint.setEnabled(false);
		}
	}

	/**
	 * 
	 * @param DCommDTO
	 * @throws Exception
	 */
	private void fillDeliveryCommValues(DeliveryCommissionDTO[] DCommDTO)
			throws Exception {

		if (null != DCommDTO) {
			btnExportXL.setEnabled(true);
			btnExportPDF.setEnabled(true);
			btnprint.setEnabled(true);

			float totalActWt = 0;
			float totalDc = 0;
			String dDate = "";
			for (int i = 0; i < DCommDTO.length; i++) {
				TableItem itemDCommission = new TableItem(tblDelivery, SWT.NONE);
				itemDCommission.setText(0, Integer.toString((i + 1)));
				itemDCommission.setText(1, DCommDTO[i].getLrno());
				if (!DCommDTO[i].getLrDate().equals(null)) {
					dDate = viewdateformat.format(DCommDTO[i].getLrDate());
					itemDCommission.setText(2, dDate);
				}

				if (!DCommDTO[i].getDeliveredDate().equals(null)) {
					dDate = viewdateformat.format(DCommDTO[i]
							.getDeliveredDate());
					itemDCommission.setText(3, dDate);
				}

				itemDCommission.setText(4, DCommDTO[i].getLrType());

				totalActWt = totalActWt + DCommDTO[i].getActualWeight();
				itemDCommission.setText(5, decimalFormat.format(DCommDTO[i]
						.getActualWeight()));
				if (DCommDTO[i].getRate_type() != 6) {
					// if (DCommDTO[i].getDdcFree() == 0) {
					totalDc = totalDc + DCommDTO[i].getDc();
					itemDCommission.setText(6, decimalFormat.format(DCommDTO[i]
							.getDc()));

				} else {
					itemDCommission.setText(6, "0.00");
				}
			}

			final TableItem item1 = new TableItem(tblDelivery, SWT.NONE);
			Font font1 = new Font(Display.getDefault(), "Tahoma", 8, SWT.BOLD);
			item1.setFont(font1);
			item1.setText(4, "TOTAL");
			item1.setText(5, decimalFormat.format(totalActWt));
			item1.setText(6, decimalFormat.format(totalDc));
			

		}else{
			btnExportXL.setEnabled(false);
			btnExportPDF.setEnabled(false);
			btnprint.setEnabled(false);
		}
		
	}

	/**
	 * 
	 * @param CCommDTO
	 * @throws Exception
	 */
	private void fillCartageCommValues(CartageCommissionDTO[] CCommDTO)
			throws Exception {

		if (null != CCommDTO) {
			btnExportXL.setEnabled(true);
			btnExportPDF.setEnabled(true);
			btnprint.setEnabled(true);
		
			float totalBft = 0;
			float totalPercent = 0;
			float totalCComm = 0;
			float ccPercent = 0;

			int len = CCommDTO.length;
			for (int i = 0; i < len; i++) {
				TableItem itemCCommission = new TableItem(tblCCC, SWT.NONE);
				itemCCommission.setText(0, Integer.toString((i + 1)));
				itemCCommission.setText(1, CCommDTO[i].getLrno());
				String cDate = viewdateformat.format(CCommDTO[i]
						.getBookingDate());
				itemCCommission.setText(2, cDate);
				itemCCommission.setText(3, CCommDTO[i].getLrType());

				totalBft = totalBft + CCommDTO[i].getBft();
				itemCCommission.setText(4, decimalFormat.format(CCommDTO[i]
						.getBft()));

				ccPercent = (CCommDTO[i].getCcCharges() / CCommDTO[i].getBft()) * 100;

				ccPercent = getRounded2Decimal(ccPercent);
				totalPercent = totalPercent + ccPercent;

				itemCCommission.setText(5, decimalFormat.format(ccPercent));

				totalCComm = totalCComm + CCommDTO[i].getCommissionAmount();
				itemCCommission.setText(6, decimalFormat.format(CCommDTO[i]
						.getCommissionAmount()));
			}

			final TableItem item1 = new TableItem(tblCCC, SWT.NONE);
			Font font1 = new Font(Display.getDefault(), "Tahoma", 8, SWT.BOLD);
			item1.setFont(font1);
			item1.setText(3, "TOTAL");
			item1.setText(4, decimalFormat.format(totalBft));
			/* item1.setText(5, decimalFormat.format(totalPercent / len)); */
			item1.setText(6, decimalFormat.format(totalCComm));
		
				
		}else{
			btnExportXL.setEnabled(false);
			btnExportPDF.setEnabled(false);
			btnprint.setEnabled(false);
		}
			
		
	}

	/**
	 * 
	 * @param RecoveryDTO
	 * @throws Exception
	 */
	private void fillRecoveryValues(RefundRecoveryDTO[] RecoveryDTO)
			throws Exception {

		if (null != RecoveryDTO) {
			btnExportXL.setEnabled(true);
			btnExportPDF.setEnabled(true);
			btnprint.setEnabled(true);
			float totAmt = 0;
			String inst = "";
			for (int i = 0; i < RecoveryDTO.length; i++) {
				TableItem itemRecovery = new TableItem(tblRecovery, SWT.NONE);
				itemRecovery.setText(0, Integer.toString((i + 1)));
				if (RecoveryDTO[i].getDescription() != null)
					itemRecovery.setText(1, RecoveryDTO[i].getDescription());
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

				itemRecovery.setText(5, viewdateformat.format(RecoveryDTO[i]
						.getRr_date()));

			}
			final TableItem item1 = new TableItem(tblRecovery, SWT.NONE);
			Font font1 = new Font(Display.getDefault(), "Tahoma", 8, SWT.BOLD);
			item1.setFont(font1);
			item1.setText(2, "TOTAL");
			item1.setText(3, "-" + decimalFormat.format(totAmt));
		}
	}

	/**
	 * 
	 * @param RefundDTO
	 * @throws Exception
	 */
	private void fillRefundValues(RefundRecoveryDTO[] RefundDTO)
			throws Exception {

		if (null != RefundDTO) {
			btnExportXL.setEnabled(true);
			btnExportPDF.setEnabled(true);
			btnprint.setEnabled(true);
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
			Font font1 = new Font(Display.getDefault(), "Tahoma", 8, SWT.BOLD);
			item1.setFont(font1);
			item1.setText(2, "TOTAL");
			item1.setText(3, decimalFormat.format(totAmt));
		}
	}

	
	private void clearSummaryValues() {
		txtSBooking.setText("0.00");
		txtSDelCommission.setText("0.00");
		txtSCCCharge.setText("0.00");
		txtSDDCharge.setText("0.00");
		txtSRefund.setText("0.00");
		txtSRecovery.setText("0.00");
		txtSCBeforeDD.setText("0.00");
		txtSTotal.setText("0.00");		
	}
	
	/**
	 * 
	 * @param summaryDTo
	 */
	private void fillSummaryValues(CommissionSummaryDTO summaryDTo) {

		if (null != summaryDTo) {
			
			
			txtSBooking.setText(decimalFormat.format(summaryDTo.getBcTotal()));
			txtSDelCommission.setText(decimalFormat.format(summaryDTo
					.getDcTotal()));
			txtSCCCharge
					.setText(decimalFormat.format(summaryDTo.getCccTotal()));
			txtSDDCharge.setText(decimalFormat.format(summaryDTo.getDdc()));
			txtSRefund.setText(decimalFormat.format(summaryDTo
					.getRefundAmount()));
			txtSRecovery.setText(decimalFormat.format(summaryDTo
					.getRecoveryAmount()));
			txtSCBeforeDD.setText(decimalFormat.format(summaryDTo.getBcTotal()
					+ summaryDTo.getCccTotal() + summaryDTo.getDcTotal()
					+ summaryDTo.getRefundAmount()
					- summaryDTo.getRecoveryAmount()));
			txtSTotal.setText(decimalFormat.format(summaryDTo.getNetAmount()));
		}
	}

	/**
	 * 
	 * @param ddDetailsDTO
	 */
	private void fillDDDetailsValues(DDDetailsDTO[] ddDetailsDTO) {

		if (null != ddDetailsDTO) {
			btnExportXL.setEnabled(true);
			btnExportPDF.setEnabled(true);
			btnprint.setEnabled(true);
		
			float totDdc = 0;
			float totDde = 0;
			float totothers = 0;
			float totDemurrage = 0;
			float totUnderCharge = 0;
			float totPTCharge = 0;
			float totalDDD = 0;

			for (int i = 0; i < ddDetailsDTO.length; i++) {
				TableItem itemDDD = new TableItem(tblDDDetails, SWT.NONE);
				itemDDD.setText(0, Integer.toString((i + 1)));
				itemDDD.setText(1, ddDetailsDTO[i].getLrNo());
				itemDDD.setText(2, viewdateformat.format((ddDetailsDTO[i]
						.getLrDate())));
				itemDDD.setText(3, ddDetailsDTO[i].getMode());
				float ddcfree = ddDetailsDTO[i].getDdcFree();

				if (ddcfree != 0) {
					totDdc = totDdc + ddcfree;
					itemDDD.setText(4, decimalFormat.format(ddcfree));
				} else {
					totDdc = totDdc + ddDetailsDTO[i].getDdc();
					itemDDD.setText(4, decimalFormat.format(ddDetailsDTO[i]
							.getDdc()));
				}

				totDde = totDde + ddDetailsDTO[i].getDdExtra();
				itemDDD.setText(5, decimalFormat.format(ddDetailsDTO[i]
						.getDdExtra()));
				totothers = totothers + ddDetailsDTO[i].getOthers();
				itemDDD.setText(6, decimalFormat.format(ddDetailsDTO[i]
						.getOthers()));
				totDemurrage = totDemurrage + ddDetailsDTO[i].getDemurrage();
				itemDDD.setText(7, decimalFormat.format(ddDetailsDTO[i]
						.getDemurrage()));
				totUnderCharge = totUnderCharge
						+ ddDetailsDTO[i].getUnderCharge();
				itemDDD.setText(8, decimalFormat.format(ddDetailsDTO[i]
						.getUnderCharge()));
				totPTCharge = totPTCharge + ddDetailsDTO[i].getPostageCharge();
				itemDDD.setText(9, decimalFormat.format(ddDetailsDTO[i]
						.getPostageCharge()));
				totalDDD = totalDDD + ddDetailsDTO[i].getTotal();
				itemDDD.setText(10, decimalFormat.format(ddDetailsDTO[i]
						.getTotal()));
			}

			final TableItem item1 = new TableItem(tblDDDetails, SWT.NONE);
			Font font1 = new Font(Display.getDefault(), "Tahoma", 8, SWT.BOLD);
			item1.setFont(font1);
			item1.setText(3, "TOTAL");
			item1.setText(4, decimalFormat.format(totDdc));
			item1.setText(5, decimalFormat.format(totDde));
			item1.setText(6, decimalFormat.format(totothers));
			item1.setText(7, decimalFormat.format(totDemurrage));
			item1.setText(8, decimalFormat.format(totUnderCharge));
			item1.setText(9, decimalFormat.format(totPTCharge));
			item1.setText(10, decimalFormat.format(totalDDD));
			
		}else{
			btnExportXL.setEnabled(false);
			btnExportPDF.setEnabled(false);
			btnprint.setEnabled(false);
		}

	}

	@Override
	public void handleEvent(Event event) {
		event.height = 30;
	}

	/**
	 * Method to show the errors
	 * 
	 * @param errorText
	 */
	private void displayError(String errorText) {
		MessageBox messageBox = new MessageBox(shell, SWT.OK);
		messageBox.setMessage(errorText);
		messageBox.setText("Alert");
		messageBox.open();
	}

	@Override
	public void mouseDoubleClick(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDown(MouseEvent arg0) {
		
	}

	@Override
	public void mouseUp(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent event) {
		
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	private float getRounded2Decimal(float value) {
		value = value * 100;
		value = (float) Math.round(value);
		value = value / 100;

		return value;
	}
	
	
}
