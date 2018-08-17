/**
 * 
 */
package hm.akr.container.commission;

import hm.akr.container.history.HistoryHandler;
import hm.akr.common.BeanUtil;
import hm.akr.common.MonthDialog;
import hm.akr.common.SWTResourceManager;
import hm.akr.dto.BookingCommissionDTO;
import hm.akr.dto.CartageCommissionDTO;
import hm.akr.dto.CommissionSummaryDTO;
import hm.akr.dto.DDDetailsDTO;
import hm.akr.dto.DeliveryCommissionDTO;
import hm.akr.dto.RefundRecoveryDTO;
import hm.akr.dto.StationsDTO;
import hm.akr.dto.VersionDTO;

import java.text.Collator;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;


import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.FileDialog;
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
 * @author user
 * 
 */
public class CommissionComposite extends Composite implements Listener,
		MouseListener, KeyListener {
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
	private Table tblTotal;
	private Group gpTotalCalculation;
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
	CommissionCompositeHandler handler = null;
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
	//private Text txtSDDCharge;
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
	private Button btnChangePwd;
	private Text txtConfimPwd;
	private Label lblConfirmPwd;
	private Text txtNewPwd;
	private Label lblNewPwd;
	private Text txtOldPwd;
	private Label lblOldPwd;
	private Label lblPwd;
	private Text txtpwd;

	private String givenPwd;
	private Button btnLogin;
	private Canvas cvsLogin;
	private TableColumn colDdDemurrage;
	private TableColumn colDdUndCrg;
	private TableColumn colDdPTCrg;
	private TableColumn colDdTotal;
	private String password = null;
	private Group gpChangePwd;
	private Group gpChangePwdPanel;

	private DecimalFormat decimalFormat;
	private TableColumn colDDeliveredDate;
	private TableColumn colRecInst;
	private TableColumn colRefInst;
	private TableColumn colRecDate;
	private TableColumn colRefDate;
	
	boolean own_station=false;
	private Label lblDDR;
	private Text txtDDR;
	private Button btnprint;
	
	private Button btnExportXL;
	
	private Button btnExportPDF;
	
private static final String BOOKING_JRXML = "hm/akr/resources/printer/Booking.jrxml";
	
	private static final String DELIVERY_JRXML = "hm/akr/resources/printer/Delivery.jrxml";
	
	private static final String CCC_JRXML = "hm/akr/resources/printer/cccommission.jrxml";
	
	private static final String DDDETAILS_JRXML = "hm/akr/resources/printer/Dddetails.jrxml";
	
	private static final String RECOVERY_JRXML = "hm/akr/resources/printer/Recovery.jrxml";
	
	private static final String REFUND_JRXML = "hm/akr/resources/printer/Refund.jrxml";
	

	private String serverDt = "";
	

	VersionDTO[] vDto = null;
	
	HistoryHandler historyH = null;
	
	/**
	 * Commission Composite Constructor
	 */
	public CommissionComposite(Shell shell, int swtValue) {
		super(shell, swtValue);
		this.shell = shell;
		try {
			handler = new CommissionCompositeHandler();
			beanutil = BeanUtil.getInstance();
			serverDt = beanutil.getServerDate(); 
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

			loginForm();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return this;
	}

	private void loadCommissionComposite() throws Exception {
		{
			this.setSize(1030, 746);
			{
				tbfCommission = new TabFolder(this, SWT.NONE);
				tbfCommission.setBounds(90, 162, 850, 500);
				
				StationsDTO[] stations = beanutil.getAvailableStations();
				String stationCode = beanutil.getActingStationCode();
				
				for (int i = 0; i < stations.length; i++) {
					if (stationCode.equalsIgnoreCase(stations[i].getId())) {
						if ((stations[i].getOwner().equalsIgnoreCase("akr"))) {
							own_station=true;
						}
						
					}
				}

				System.out.println("OWN Startion"+own_station);
				
				{	
					btnprint = new Button(this, SWT.PUSH | SWT.CENTER);
					btnprint.setText("Print");
					/*FormData btnprintLData = new FormData();
					btnprintLData.width = 106;
					btnprintLData.height = 30;
					btnprintLData.left = new FormAttachment(865, 1000, 0);
					btnprintLData.right = new FormAttachment(969, 1000, 0);
					btnprintLData.top = new FormAttachment(939, 1000, 0);
					btnprintLData.bottom = new FormAttachment(978, 1000, 0);
					btnprint.setLayoutData(btnprintLData);*/
					btnprint.setBounds(880, 670, 55, 25);
					btnprint.setEnabled(false);
					btnprint.addSelectionListener(new CommissionAction());

				}
				
				{
					btnExportXL = new Button(this, SWT.PUSH | SWT.CENTER);
					btnExportXL.setText("Export Excel");
					FormData btnExportXLData = new FormData();
					/*btnExportXLData.width = 106;
					btnExportXLData.height = 30;
					btnExportXLData.left = new FormAttachment(765, 1000, 0);
					btnExportXLData.right = new FormAttachment(849, 1000, 0);
					btnExportXLData.top = new FormAttachment(939, 1000, 0);
					btnExportXLData.bottom = new FormAttachment(978, 1000, 0);
					btnExportXL.setLayoutData(btnExportXLData);*/
					btnExportXL.setBounds(790, 670, 75, 25);
					btnExportXL.setEnabled(false);
					btnExportXL.addSelectionListener(new CommissionAction());
					
					
				}
				
				{
					btnExportPDF = new Button(this, SWT.PUSH | SWT.CENTER);
					btnExportPDF.setText("Export PDF");
					FormData btnExportPDFData = new FormData();
					/*btnExportPDFData.width = 106;
					btnExportPDFData.height = 30;
					btnExportPDFData.left = new FormAttachment(665, 1000, 0);
					btnExportPDFData.right = new FormAttachment(749, 1000, 0);
					btnExportPDFData.top = new FormAttachment(939, 1000, 0);
					btnExportPDFData.bottom = new FormAttachment(978, 1000, 0);
					btnExportPDF.setLayoutData(btnExportPDFData);*/
					btnExportPDF.setEnabled(false);
					btnExportPDF.setBounds(700,670,75,25);
					btnExportPDF.addSelectionListener(new CommissionAction());

					
				}
				

				
				{
					tiCommissionSummary = new TabItem(tbfCommission, SWT.NONE);
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
									lblSBooking.setText("Booking Commission");
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
									lblSCCCommission.setText("CC Commission");
									lblSCCCommission.setBounds(156, 137, 101,
											22);
								}
								{
									txtSCCCharge = new Text(cvsSummary,
											SWT.BORDER);
									txtSCCCharge.setBounds(295, 137, 100, 22);
									txtSCCCharge.setEnabled(false);
								}
								/*{
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
								}*/
								{
									lblSRefund = new Label(cvsSummary, SWT.NONE);
									lblSRefund.setText("Refund");
									lblSRefund.setBounds(156, 188, 71, 22);
								}
								{
									txtSRefund = new Text(cvsSummary,
											SWT.BORDER);
									txtSRefund.setBounds(295, 186, 100, 22);
									txtSRefund.setEnabled(false);
								}
								{
									lblSRecovery = new Label(cvsSummary,
											SWT.NONE);
									lblSRecovery.setText("Recovery");
									lblSRecovery.setBounds(156, 234, 71, 22);
								}
								{
									txtSRecovery = new Text(cvsSummary,
											SWT.BORDER);
									txtSRecovery.setBounds(295, 232, 100, 22);
									txtSRecovery.setEnabled(false);
								}
								{
									lblSCommDD = new Label(cvsSummary, SWT.NONE);
									lblSCommDD.setText("Total Commission");
									lblSCommDD.setBounds(156, 280, 100, 22);
								}
								{
									txtSCBeforeDD = new Text(cvsSummary,
											SWT.BORDER);
									txtSCBeforeDD.setBounds(295, 279, 100, 22);
									txtSCBeforeDD.setEnabled(false);
								}
								{
									lblTotalCommission = new Label(cvsSummary,
											SWT.NONE);
									lblTotalCommission
											.setText("Commission with DDR");
									lblTotalCommission.setBounds(452, 280, 120, 22);
								}
								{
									txtSTotal = new Text(cvsSummary, SWT.BORDER);
									txtSTotal.setBounds(591, 279, 100, 22);
									txtSTotal.setEnabled(false);
								}
								
								{
									lblDDR = new Label(cvsSummary,
											SWT.NONE);
									lblDDR
											.setText("DDR");
									lblDDR.setBounds(452, 234, 120, 22);
								}
								{
									txtDDR = new Text(cvsSummary, SWT.BORDER);
									txtDDR.setBounds(591, 232, 100, 22);
									txtDDR.setEnabled(false);
								}
								
							/*	{
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
								}*/
							}
						}

					}
				}

				/*{
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
								colBSNo = new TableColumn(tblBooking, SWT.NONE);
								colBSNo.setText("S.No");
								colBSNo.setWidth(40);
								colBSNo.addListener(SWT.Selection,
										new sortListener());

							}
							{
								colBLrno = new TableColumn(tblBooking, SWT.NONE);
								colBLrno.setText("LR No");
								colBLrno.setWidth(70);
								colBLrno.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colBBookingDate = new TableColumn(tblBooking,
										SWT.NONE);
								colBBookingDate.setText("LR Date");
								colBBookingDate.setWidth(80);
								colBBookingDate.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colBMode = new TableColumn(tblBooking, SWT.NONE);
								colBMode.setText("Mode");
								colBMode.setWidth(94);
								colBMode.addListener(SWT.Selection,
										new sortListener());
							}

							{
								colBBasicFreight = new TableColumn(tblBooking,
										SWT.NONE);
								colBBasicFreight.setText("Basic Freight");
								colBBasicFreight.setWidth(80);
								colBBasicFreight.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colBPercent = new TableColumn(tblBooking,
										SWT.NONE);
								colBPercent.setText("%");
								colBPercent.setWidth(94);
								colBPercent.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colBComnAmt = new TableColumn(tblBooking,
										SWT.NONE);
								colBComnAmt.setText("Commission");
								colBComnAmt.setWidth(95);
								colBComnAmt.addListener(SWT.Selection,
										new sortListener());
							}
						}
					}
				}*/
				
				/*{
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
								colDSNo = new TableColumn(tblDelivery, SWT.NONE);
								colDSNo.setText("S.No");
								colDSNo.setWidth(50);
								colDSNo.addListener(SWT.Selection,
										new sortListener());
							}
							{
								ColDLrno = new TableColumn(tblDelivery,
										SWT.NONE);
								ColDLrno.setText("LR No");
								ColDLrno.setWidth(107);
								ColDLrno.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colDBookingDate = new TableColumn(tblDelivery,
										SWT.NONE);
								colDBookingDate.setText("Lr Date");
								colDBookingDate.setWidth(115);
								colDBookingDate.addListener(SWT.Selection,
										new sortListener());
							}

							{
								colDDeliveredDate = new TableColumn(
										tblDelivery, SWT.NONE);
								colDDeliveredDate.setText("Delivered Date");
								colDDeliveredDate.setWidth(115);
								colDDeliveredDate.addListener(SWT.Selection,
										new sortListener());
							}

							{
								colDLrType = new TableColumn(tblDelivery,
										SWT.NONE);
								colDLrType.setText("LR Type");
								colDLrType.setWidth(107);
								colDLrType.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colDActualWeight = new TableColumn(tblDelivery,
										SWT.NONE);
								colDActualWeight.setText("Weight");
								colDActualWeight.setWidth(86);
								colDActualWeight.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colDCommission = new TableColumn(tblDelivery,
										SWT.NONE);
								colDCommission.setText("Commision");
								colDCommission.setWidth(88);
								colDCommission.addListener(SWT.Selection,
										new sortListener());
							}
						}
					}
				}*/
				
				/*{


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
								colCSNo = new TableColumn(tblCCC, SWT.NONE);
								colCSNo.setText("S.No");
								colCSNo.setWidth(40);
								colCSNo.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colCLrNo = new TableColumn(tblCCC, SWT.NONE);
								colCLrNo.setText("LR No");
								colCLrNo.setWidth(82);
								colCLrNo.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colCBookingDate = new TableColumn(tblCCC,
										SWT.NONE);
								colCBookingDate.setText("Date");
								colCBookingDate.setWidth(90);
								colCBookingDate.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colCLrType = new TableColumn(tblCCC, SWT.NONE);
								colCLrType.setText("LR Type");
								colCLrType.setWidth(75);
								colCLrType.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colCBasicFreight = new TableColumn(tblCCC,
										SWT.NONE);
								colCBasicFreight.setText("Basic Freight");
								colCBasicFreight.setWidth(95);
								colCBasicFreight.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colCPercentCharged = new TableColumn(tblCCC,
										SWT.NONE);
								colCPercentCharged.setText("% Charged");
								colCPercentCharged.setWidth(80);
								colCPercentCharged.addListener(SWT.Selection,
										new sortListener());
							}
							{
								colCommissionAmt = new TableColumn(tblCCC,
										SWT.NONE);
								colCommissionAmt.setText("CC Refunded");
								colCommissionAmt.setWidth(93);
								colCommissionAmt.addListener(SWT.Selection,
										new sortListener());
							}

						}
					}
				
				}*/

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
							tblDDDetails.setBounds(20, 10, 800, 450);
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
				{
					tiRecovery = new TabItem(tbfCommission, SWT.NONE);
					tiRecovery.setText(RECOVERY_TAB);
					{
						Canvas cvsRec = new Canvas(tbfCommission, SWT.NONE);
						tblRecovery = new Table(cvsRec, SWT.H_SCROLL
								| SWT.FULL_SELECTION | SWT.V_SCROLL
								| SWT.BORDER | SWT.MULTI);
						tblRecovery.setHeaderVisible(true);
						tblRecovery.setLinesVisible(true);
						tblRecovery.setBounds(20, 10, 740, 450);
						tiRecovery.setControl(cvsRec);
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

			}
			{
				gpMonth = new Group(this, SWT.NONE);
				gpMonth.setText("Month");
				gpMonth.setBounds(566, 102, 140, 54);
			}

			{
				txtMonth = new Text(gpMonth, SWT.BORDER);
				txtMonth.setEnabled(false);
				//DateFormat dateFormat = new SimpleDateFormat("MM-yyyy");
				//java.util.Date currentDate = new java.util.Date();
				//String date = dateFormat.format(currentDate);
				
				SimpleDateFormat frmt = new SimpleDateFormat("dd-MM-yyyy");
				Date to = frmt.parse(serverDt);			
				to.setMonth(to.getMonth() -1);
				String prevMonth = frmt.format(to);
				prevMonth = prevMonth.substring(prevMonth.indexOf("-")+1);
				
				txtMonth.setText(prevMonth);
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
								.getActiveShell(), 22);
						Object obj = cd.open();
						if (obj != null) {
							selectedDate = txtMonth.getText();
							String selectedTab = tbfCommission.getSelection()[0]
									.getText();
							if (selectedTab.equals(DDDETAILS_TAB)) {
								selectedDateDDC = txtMonth.getText();
							}
							txtMonth.setText(obj.toString());

						}
					}

				});
			}
			{
				tbfCommission.addSelectionListener(new CommissionAction());
				tbfCommission.setSelection(0);

				btnGo = new Button(gpMonth, SWT.PUSH | SWT.CENTER);
				btnGo.setText("Go");
				btnGo.setBounds(100, 17, 30, 24);
				btnGo.addSelectionListener(new CommissionAction());
			}
		}

		if (null != handler &&(!own_station || beanutil.isAdminHead())) {
			SimpleDateFormat format = new SimpleDateFormat("MM-yyyy");
			Date selectdate = format.parse(txtMonth.getText());
			 
			
			/*int monthDiff = 0;
			Date curDate = new Date();			
			int m1 = selectdate.getYear() * 12 + selectdate.getMonth();
	        int m2 = curDate.getYear() * 12 + curDate.getMonth();
	        monthDiff = m2 - m1;*/
			int month = selectdate.getMonth(); 
				int year =  selectdate.getYear();
				Date beanDate = BeanUtil.getThree_month_updated();
				int smonth = beanDate.getMonth();
			int syear = beanDate.getYear();
	        if((month <=smonth) && (year <= syear)){
	       
	        	CSummaryDTo = handler.getCommisionSummaryHistory(selectdate);
	        }else{
	        	//if(BeanUtil.getDbName() == null || BeanUtil.getDbName().equals("")){
	        	CSummaryDTo = handler.getCommisionSummary(selectdate);
	        	//}
	        }
			
			
	       
	        //if(monthDiff > 3){
	        	
	       
			if(null != CSummaryDTo){
				fillSummaryValues(CSummaryDTo);
			}
			
		}

	}

	private void loginForm() throws Exception {
		cvsLogin = new Canvas(this, SWT.BORDER);
		cvsLogin.setBounds(194, 141, 405, 343);
		lblPwd = new Label(cvsLogin, SWT.NULL);
		lblPwd.setText("Password: ");
		lblPwd.setBounds(55, 76, 64, 27);

		txtpwd = new Text(cvsLogin, SWT.SINGLE | SWT.BORDER);

		txtpwd.setEchoChar('*');
		txtpwd.setTextLimit(30);
		txtpwd.setBounds(131, 76, 89, 22);
		txtpwd.addKeyListener(this);

		btnLogin = new Button(cvsLogin, SWT.PUSH);
		btnLogin.setText("Login");
		btnLogin.setBounds(225, 78, 50, 24);
		btnLogin.addSelectionListener(new CommissionAction());

		gpChangePwdPanel = new Group(cvsLogin, SWT.NONE);
		gpChangePwdPanel.setText("Change Password");
		gpChangePwdPanel.setBounds(32, 172, 300, 145);
		// gpChangePwdPanel.addMouseListener(this);

		// gpChangePwd = new Group(gpChangePwdPanel, SWT.NONE);
		// gpChangePwd.setBounds(5, 15, 290, 129);

		{
			lblOldPwd = new Label(gpChangePwdPanel, SWT.NONE);
			lblOldPwd.setText("Old Password");
			lblOldPwd.setBounds(12, 26, 83, 22);
		}
		{
			txtOldPwd = new Text(gpChangePwdPanel, SWT.SINGLE | SWT.BORDER);

			txtOldPwd.setEchoChar('*');
			txtOldPwd.setBounds(112, 23, 80, 22);
		}
		{
			lblNewPwd = new Label(gpChangePwdPanel, SWT.NONE);
			lblNewPwd.setText("New Password");
			lblNewPwd.setBounds(12, 62, 85, 29);
		}
		{
			txtNewPwd = new Text(gpChangePwdPanel, SWT.SINGLE | SWT.BORDER);
			txtNewPwd.setBounds(112, 62, 80, 22);

			txtNewPwd.setEchoChar('*');
		}
		{
			lblConfirmPwd = new Label(gpChangePwdPanel, SWT.NONE);
			lblConfirmPwd.setText("Confirm Password");
			lblConfirmPwd.setBounds(12, 97, 87, 22);
		}
		{
			txtConfimPwd = new Text(gpChangePwdPanel, SWT.SINGLE | SWT.BORDER);

			txtConfimPwd.setEchoChar('*');
			txtConfimPwd.setBounds(111, 97, 80, 22);
			txtConfimPwd.addKeyListener(this);
		}
		{
			btnChangePwd = new Button(gpChangePwdPanel, SWT.PUSH | SWT.CENTER);
			btnChangePwd.setText("Change");
			btnChangePwd.setBounds(199, 98, 54, 22);
			btnChangePwd.addSelectionListener(new CommissionAction());
		}

		if (handler != null) {
			password = handler.getPassword();
		}

		txtpwd.setFocus();

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

		@Override
		public void widgetSelected(SelectionEvent event) {
			Object source = event.getSource();
			Date date = null;
			try {

				if (source == btnLogin) {
					loginAction();
				} else if (source == btnChangePwd) {
					changePwd();

				} else if (source == btnGo || source == tbfCommission) {

					String selectedTab = tbfCommission.getSelection()[0]
							.getText();
					SimpleDateFormat format = new SimpleDateFormat("MM-yyyy");
					date = format.parse(txtMonth.getText());
					if (selectedTab.equals(SUMMARY_TAB)
							&& (!own_station || beanutil.isAdminHead())) {
						if (CSummaryDTo == null
								|| !selectedDate.equals(txtMonth.getText())) {
							/*int monthDiff = 0;
							Date curDate = new Date();			
							int m1 = date.getYear() * 12 + date.getMonth();
					        int m2 = curDate.getYear() * 12 + curDate.getMonth();
					        monthDiff = m2 - m1;*/
							int month = date.getMonth(); 
 							int year =  date.getYear();
 							Date beanDate = BeanUtil.getThree_month_updated();
 							int smonth = beanDate.getMonth();
							int syear = beanDate.getYear();
					        if((month <=smonth) && (year <= syear)){
					       
					        	CSummaryDTo = handler.getCommisionSummaryHistory(date);
					        }else{
					        	//if(BeanUtil.getDbName() == null || BeanUtil.getDbName().equals("")){
					        		CSummaryDTo = handler.getCommisionSummary(date);
					        	//}
					        }
							
							if (CSummaryDTo != null) {
								fillSummaryValues(CSummaryDTo);
							}
							selectedDate = txtMonth.getText();
						}
					} else if (selectedTab.equals(BOOKING_TAB)
							&& (!own_station || beanutil.isAdminHead())) {
						if (BCommDTO == null
								|| (BCommDTO[0].getBookingDate().getMonth() != date
										.getMonth())) {
							/*int monthDiff = 0;
							Date curDate = new Date();			
							int m1 = date.getYear() * 12 + date.getMonth();
					        int m2 = curDate.getYear() * 12 + curDate.getMonth();
					        monthDiff = m2 - m1;*/		
							int month = date.getMonth(); 
 							int year =  date.getYear();
 							Date beanDate = BeanUtil.getThree_month_updated();
 							int smonth = beanDate.getMonth();
							int syear = beanDate.getYear();
					        if((month <=smonth) && (year <= syear)){
					        	BCommDTO = handler.getBookingCommissionListHistory(date);
					        }else{
					        	
					        		BCommDTO = handler.getBookingCommissionList(date);
					        	
					        }
							
							if (tblBooking != null)
								tblBooking.removeAll();

							if (BCommDTO != null) {
								fillBookingCommValues(BCommDTO);
							}
						}

					} else if (selectedTab.equals(DELIVERY_TAB)
							&& (!own_station || beanutil.isAdminHead())) {
						if (DCommDTO == null
								|| (DCommDTO[0].getDeliveredDate().getMonth() != date
										.getMonth())) {

							/*int monthDiff = 0;
							Date curDate = new Date();			
							int m1 = date.getYear() * 12 + date.getMonth();
					        int m2 = curDate.getYear() * 12 + curDate.getMonth();
					        monthDiff = m2 - m1;*/
							int month = date.getMonth(); 
 							int year =  date.getYear();
 							Date beanDate = BeanUtil.getThree_month_updated();
 							int smonth = beanDate.getMonth();
							int syear = beanDate.getYear();
					        if((month <= smonth) && (year <= syear)){
					        
					        	DCommDTO = handler.getDeliveryCommissionListHistory(date);
					        }else{
					        	//if(BeanUtil.getDbName() == null || BeanUtil.getDbName().equals("")){
					        		DCommDTO = handler.getDeliveryCommissionList(date);
					        	//}
					        }

							if (tblDelivery != null)
								tblDelivery.removeAll();

							if (DCommDTO != null) {
								fillDeliveryCommValues(DCommDTO);
							}
						}

					} else if (selectedTab.equals(REFUND_TAB)) {
						
						RefundDTO = handler.getRefundList(date);
						if (tblRefund != null)
							tblRefund.removeAll();
						if (RefundDTO != null) {
							btnExportXL.setEnabled(true);
							btnExportPDF.setEnabled(true);
							btnprint.setEnabled(true);
							fillRefundValues(RefundDTO);
						}

					} else if (selectedTab.equals(RECOVERY_TAB)) {
						RecoveryDTO = handler.getRecoveryList(date);
						if (tblRecovery != null)
							tblRecovery.removeAll();
						if (RecoveryDTO != null) {
							btnExportXL.setEnabled(true);
							btnExportPDF.setEnabled(true);
							btnprint.setEnabled(true);
							fillRecoveryValues(RecoveryDTO);
						}

					} else if (selectedTab.equals(CCC_TAB)
							&&(!own_station || beanutil.isAdminHead())) {
						if (CCommDTO == null
								|| (CCommDTO[0].getBookingDate().getMonth() != date
										.getMonth())) {
							/*int monthDiff = 0;
							Date curDate = new Date();			
							int m1 = date.getYear() * 12 + date.getMonth();
					        int m2 = curDate.getYear() * 12 + curDate.getMonth();
					        monthDiff = m2 - m1;*/					      
							int month = date.getMonth(); 
 							int year =  date.getYear();
 							Date beanDate = BeanUtil.getThree_month_updated();
 							int smonth = beanDate.getMonth();
							int syear = beanDate.getYear();
					        if((month <=smonth) && (year <= syear)){
							
					        	CCommDTO = handler.getCartageCommissionListHistory(date);
					        }else{
					        	//if(BeanUtil.getDbName() == null || BeanUtil.getDbName().equals("")){
					        		CCommDTO = handler.getCartageCommissionList(date);
					        	//}
					        }
							
							if (tblCCC != null)
							{
								btnprint.setEnabled(false);
								btnExportXL.setEnabled(false);
								btnExportPDF.setEnabled(false);
								tblCCC.removeAll();
							}
							if (CCommDTO != null) {
								fillCartageCommValues(CCommDTO);
							}
						}
					} else if (selectedTab.equals(DDDETAILS_TAB)
							&& (!own_station || beanutil.isAdminHead())) {
						if (ddDetailsDTO == null
								|| !selectedDateDDC.equals(txtMonth.getText())) {
							/*(int monthDiff = 0;
							Date curDate = new Date();			
							int m1 = date.getYear() * 12 + date.getMonth();
					        int m2 = curDate.getYear() * 12 + curDate.getMonth();
					        monthDiff = m2 - m1;*/
							int month = date.getMonth(); 
 							int year =  date.getYear();
 							Date beanDate = BeanUtil.getThree_month_updated();
 							int smonth = beanDate.getMonth();
							int syear = beanDate.getYear();
					        if((month <=smonth) && (year <= syear)){
					        
					        	ddDetailsDTO = handler.getDDDetailsHistory(date);
					        }else{
					        	ddDetailsDTO = handler.getDDDetails(date);
					        }
					        							
							if (tblDDDetails != null)
							{
								btnprint.setEnabled(false);
								btnExportXL.setEnabled(false);
								btnExportPDF.setEnabled(false);
								tblDDDetails.removeAll();
							}
							if (ddDetailsDTO != null) {
								fillDDDetailsValues(ddDetailsDTO);
							}
							selectedDateDDC = txtMonth.getText();
						}
					}
				}
				
				else if(source == btnExportXL)
				{	
					String selectedTab = tbfCommission.getSelection()[0].getText();
					if (selectedTab.equals(BOOKING_TAB)
							&& (!own_station || beanutil.isAdminHead())) {
						ArrayList<BookingCommissionDTO> list = null;
						String value = txtMonth.getText();
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
						String value = txtMonth.getText();
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
						String value = txtMonth.getText();
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
						String value = txtMonth.getText();
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
						String value = txtMonth.getText();
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
						String value = txtMonth.getText();
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
						String value = txtMonth.getText();
						try {
													
							list = getBookingTable();
							preparePDF(list, "BOOKING_TAB",BOOKING_JRXML ,value);
							
							btnExportPDF.setEnabled(false);
						} catch (Exception exception) {
							displayError("Problem while Exporting Excel");
						}

					} else if (selectedTab.equals(DELIVERY_TAB)
							&& (!own_station || beanutil.isAdminHead())) {
						ArrayList<DeliveryCommissionDTO> list = null;
						String value = txtMonth.getText();
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
						String value = txtMonth.getText();
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
						String value = txtMonth.getText();
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
						String value = txtMonth.getText();
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
						String value = txtMonth.getText();
						
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
						String value = txtMonth.getText();
						try {
													
							list = getBookingTable();
							handler.preparePrint(list, "BOOKING_TAB",BOOKING_JRXML ,value);
							
							btnprint.setEnabled(false);
						} catch (Exception exception) {
							displayError("Problem while Exporting Excel");
						}

					} else if (selectedTab.equals(DELIVERY_TAB)
							&& (!own_station || beanutil.isAdminHead())) {
						ArrayList<DeliveryCommissionDTO> list = null;
						String value = txtMonth.getText();
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
						String value = txtMonth.getText();
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
						String value = txtMonth.getText();
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
						String value = txtMonth.getText();
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
						String value = txtMonth.getText();
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

	}

	private void preparePDF(ArrayList list,
			String fileName, String jrxmlFile, String value) {
		// TODO Auto-generated method stub
		
		try {
			//AdminComposite.display("Exporting...", STATUS_SUCCESS,SUCCESS_FONT_COLOR);
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
					//AdminComposite.display("Saving...", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					copyFile(xl, df, dialog.getFileName());
				}
				//AdminComposite.display("PDF Saved Successfully",	STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			}

		} catch (Exception e) {
			//AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			e.printStackTrace();
		}

		
	}

	private void prepareXL(ArrayList list,
			String fileName, String jrxmlFile, String value) {
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
					//AdminComposite.display("Saving...", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					copyFile(xl, df, dialog.getFileName());
				}
				//AdminComposite.display("Excel Saved Successfully",STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			}
			else
			{
				//AdminComposite.display("Excel Saving Cancelled",STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			}
		} catch (Exception e) {
			//AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			e.printStackTrace();
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

	
	private void loginAction() throws Exception {
		givenPwd = txtpwd.getText();

		if (givenPwd == "") {
			displayError("Enter the Password");
		} else {
			if (givenPwd.equals(password)) {
				if (cvsLogin != null) {
					cvsLogin.setVisible(false);
				}
				loadCommissionComposite();
			} else {
				displayError("Enter correct the Password");
				txtpwd.setText("");
			}
		}

	}

	private void changePwd() throws Exception {
		String oldPwd = txtOldPwd.getText();

		if (!oldPwd.equals(password)) {
			displayError("Enter Old Password Correctly");
			txtOldPwd.setText("");
		} else if (!txtNewPwd.getText().equals(txtConfimPwd.getText())) {
			displayError("Enter Confirm Password Correctly");
			txtConfimPwd.setText("");
		} else if (!txtConfimPwd.getText().equals("")) {
			if (handler != null) {
				handler.setPassword(txtConfimPwd.getText());
				displayError("Password Changed Successfully");
				password = txtConfimPwd.getText();
				txtNewPwd.setText("");
				txtConfimPwd.setText("");
				txtOldPwd.setText("");

			}
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

	/**
	 * 
	 * @param summaryDTo
	 */
	private void fillSummaryValues(CommissionSummaryDTO summaryDTo) {

		if (null != summaryDTo) {
			btnExportXL.setEnabled(false);
			btnExportPDF.setEnabled(false);
			btnprint.setEnabled(false);
			
			txtSBooking.setText(decimalFormat.format(summaryDTo.getBcTotal()));
			txtSDelCommission.setText(decimalFormat.format(summaryDTo
					.getDcTotal()));
			txtSCCCharge
					.setText(decimalFormat.format(summaryDTo.getCccTotal()));
			//txtSDDCharge.setText(decimalFormat.format(summaryDTo.getDdc()));
			txtSRefund.setText(decimalFormat.format(summaryDTo
					.getRefundAmount()));
			txtSRecovery.setText(decimalFormat.format(summaryDTo
					.getRecoveryAmount()));
			txtSCBeforeDD.setText(decimalFormat.format(summaryDTo.getBcTotal()
					+ summaryDTo.getCccTotal() + summaryDTo.getDcTotal()
					+ summaryDTo.getRefundAmount()
					- summaryDTo.getRecoveryAmount()));
			txtSTotal.setText(decimalFormat.format(summaryDTo.getNetAmount()));
			
			float dDR = 0;			
			dDR = summaryDTo.getNetAmount() - Float.parseFloat(txtSCBeforeDD.getText());
			txtDDR.setText(decimalFormat.format(dDR));
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
		if (gpChangePwd.isVisible()) {
			gpChangePwd.setVisible(false);
			gpChangePwdPanel.setBounds(32, 172, 300, 20);

		} else {
			gpChangePwd.setVisible(true);
			gpChangePwdPanel.setBounds(32, 172, 300, 150);
		}

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
		Object source = event.getSource();

		if (source == txtpwd) {
			if (event.keyCode == 13) {
				try {
					loginAction();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		} else if (source == txtConfimPwd) {
			if (event.keyCode == 13) {
				try {
					changePwd();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

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
