package hm.akr.admin.reports;

import hm.akr.admin.history.HistoryHandler;
import hm.akr.admin.workspace.AdminComposite;
import hm.akr.admin.workspace.SWTResourceManager;
import hm.akr.common.BeanUtil;
import hm.akr.common.IUIConstants;
import hm.akr.common.KalendarDialog;
import hm.akr.common.MonthDialog;
import hm.akr.common.NumericValidation;
import hm.akr.common.UIColors;
import hm.akr.dto.BookingDTO;
import hm.akr.dto.BusinessPerformanceDTO;
import hm.akr.dto.CancelledLRDetailedDTODecorator;
import hm.akr.dto.CancelledLRSummaryDTODecorator;
import hm.akr.dto.CustomerBusinessAnalysisDTO;
import hm.akr.dto.DDRDTO;
import hm.akr.dto.DRSAttendanceDTO;
import hm.akr.dto.FocLRDetailedDTODecorator;
import hm.akr.dto.FocLRSummaryDTODecorator;
import hm.akr.dto.GeneralSummaryDTO;
import hm.akr.dto.InwardAnalysisDTO;
import hm.akr.dto.MarketVehicleDTO;
import hm.akr.dto.MissingCustomersDTO;
import hm.akr.dto.OpenLrDTODecorator;
import hm.akr.dto.RemittanceShortageDTO;
//import hm.akr.dto.ServiceTaxDTO;
import hm.akr.dto.StationsDTO;
import hm.akr.dto.TableDecorator;
import hm.akr.dto.VehicleDTO;

import hm.akr.exceptions.BusinessException;
import hm.akr.dto.VersionDTO;
import hm.akr.dto.TableDecorator;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.Collator;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.text.TableView.TableRow;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

import org.apache.poi.poifs.property.Parent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import sun.util.calendar.CalendarDate;

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
public class AdminReports extends Composite implements SelectionListener,
		IUIConstants {

	private TabFolder tabReport;
	private TabItem tiDDExtra;
	private Canvas cvsDDExtra;
	private Table tblDDExtra;
	private Button rdUnrecover;
	private Button rdFull;
	private Button btnDDEGo;
	private Combo cbDDEStation;
	private TableColumn colCCSsno;
	private TableColumn colDDELrNo;
	private TableColumn colDDETo;
	private TableColumn colDDELrType;
	private TableColumn colDDEDD;
	private TableColumn colDDEDDE;
	private TableColumn colDDELrTotal;
	private TableColumn colDDEPerTotal;
	private TableColumn colCCCOnBasFrt;
	private Combo cbDDEBranch;
	private Label label7;
	private Label lblFromDate;
	private Label lblDDEStation;
	private Label lblToDate;
	private Text txtDDEFrom;
	private Button btnDDETo;
	private Button btnDDEFromDate;
	private Text txtDDEAbove;
	private Label lblAbove;
	private Text txtDDETo;

	private AdminCompositeHandler handler;
	private TableColumn colDDEbft;
	private TabItem tiRemittance;
	private Canvas cvsRemittance;
	private Table tblRemittance;
	private TableColumn colRSfrom;
	private TableColumn colRSto;
	private TableColumn colRSLrNo;
	private TableColumn colRSLrdate;
	private TableColumn colRSType;
	private TableColumn colDRSNo;
	private TableColumn colDRSdate;
	private TableColumn colRSCRno;
	private TableColumn colRSCnor;
	private TableColumn colRSCnee;
	private TableColumn colRSamount;
	private TableColumn colRSrecoveryDt;
	private Button btnRSGo;

	private Combo cbRSBranch;
	private Label label11;
	private Text txtRSMonth;
	private Button btnRSMonth;
	RemittanceShortageDTO dto[] = null;
	BookingDTO[] bpaDto = null;

	private TabItem tiBPA;
	private Canvas cvsBPA;
	private Table tblBPA;

	private String DD_EXTRA = "DD Extra";
	private String RS = "Remittance Shortage";
	private String BPA = "BPA";
	private String CCC_SUMMARY_TAB = "CCC Summary";
	private String UT_TOPAY = "Undelivered Topay";
	private String DAILY_BOOKING_TAB = "Daily Booking Analysis";
	private String COUNTER_REPORT = "Counter Report";
	private String UPD_READY = "UPD Ready";
	private String MISSING_CUSTOMER = "Missing Customer";

	private TableColumn colBPABranch;
	private TableColumn colBPANolr;
	private TableColumn colBPANoa;
	private TableColumn colBPAActwt;
	private TableColumn colBPACrgwt;
	private TableColumn colBPABft;
	private TableColumn colBPALrc;
	private TableColumn colBPADhc;
	private TableColumn colBPACcc;
	private TableColumn colBPADdc;
	private TableColumn colBPADcc;
	private TableColumn colBPAIec;
	private TableColumn colBPALc;
	private TableColumn colBPAStax;
	private TableColumn colBPATotal;
	
	private Text txtBPAFrom;
	private Button btnBPAFromDate;
	private Text txtBPATo;
	private Button btnBPAGo;
	private Combo cbBPABranch;
	private Button btnBPATo;
	private Combo cbBPAChoice;

	private String COUNT = "Count";
	private String BASIC_FREIGHT = "Basic Freight";
	private String TOTAL_FREIGHT = "Total Freight";
	private String ACTUAL_WEIGHT = "Actual Weight";
	private String CHARGED_WEIGHT = "Charged Weight";
	private String NOA = "Noa";
	private Combo cbMCOption;
	private Combo cbBPAReportType;

	private TabItem tiCCSummary;
	private Canvas cvsCCCSummary;
	private Table tblCCCSummary;
	private TableColumn colCCSBranchCode;
	private TableColumn colCCSStationCode;
	private TableColumn colCCSTotalPT;
	private TableColumn colCCLRs;
	private TableColumn colCCSBFTLr;
	private TableColumn colCCSTotalCCC;
	private Combo cbCCCBranch;

	private Text txtCCCFrom;
	private Button btnCCCFromDate;
	private Text txtCCCTo;
	private Button btnCCCTo;
	private Button btnCCCGo;

	// Total U delivered
	private TabItem tiUndeliveredTopay;
	private Canvas cvsUndeliveredTopay;
	private Table tblUndeliveredTopayDet;
	private TableColumn colTUTsno;
	private TableColumn colUTLrno;
	private TableColumn colUTLrdate;
	private TableColumn colUTFrom;
	private TableColumn colUTTo;
	private TableColumn colUTbft;
	private TableColumn colUTnoa;
	private Text txtUTFrom;
	private Text txtUTTo;
	//private Button btnTUTMonth;
	private Combo cbUTBranch;
	private Combo cbUTStation;
	private Combo cbUTReportType;
	private TableColumn colUTActWt;
	private TableColumn colUTtotalFt;
	private TableColumn colUTCurStatus;
	private Button btnUTGo;
	private Button btnUTFromDate;
	private Button btnUTTo;
	private TableColumn colTUTBranchCode;
	private TableColumn colTUTStationCode;
	private TableColumn colTUTBoookedNoOfLRs;
	private TableColumn colTUTBoookedTotFrt;
	private TableColumn colTUTDeliverNoOfLRs;
	private TableColumn colTUTDeliverTotFrt;
	private Table tblUndeliveredTopay;
	private Label lblUTFromDate;
	private Label lblUTToDate;
	private Label label12;
	private Label label13;
	private Label label14;
	private TableColumn colCCbftofTot;
	private TableColumn colCCperofCConTot;

	private Button btnDBAGo;
	private Text txtDBAFrom;
	private Button btnDBAFrom;
	private Label lblTo;
	private Text txtDBATo;

	private Combo cbDBABranch;
	private Combo cbDBAReportType;
	private TabItem tiDailyBookingAnalysis;
	private Canvas cvsDailyBookingAnalysis;
	private Table tblDailyBookingAnalysis;
	private TableColumn colDBAsno;

	private TableColumn colDBABranchCode;

	private TableColumn colDBAStationCode;

	private TableColumn colDBADate1;

	private TableColumn colDBADate2;

	private TableColumn colDBADate3;

	private TableColumn colDBADate4;

	private TableColumn colDBADate5;

	private TableColumn colDBADate6;

	private TableColumn colDBADate7;

	private TableColumn colDBATotal;

	//
	private TabItem tiCounterReport;
	private Canvas cvsCounterReport;
	
	private Combo cbDBAMOC;
	private Label lblupdOption;
	private Combo cbUPDOption;
	private Table tblCounterReport;
  	private TableColumn colCRsno;          
	private TableColumn colCRBranchCode;
	private TableColumn colCRStationCode;
	private TableColumn colCRC1;
	private TableColumn colCRC2;
	private TableColumn colCRC3;
	private TableColumn colCRC4;
	private TableColumn colCRC5;
	private TableColumn colCRC6;
	private TableColumn colCRC7;
	private TableColumn colCRC8;
	private Text txtCRFrom;
	private Button btnCRFromDate;
	private Text txtCRTo;
	private Button btnCRTo;
	private Combo cbCounterBranch;
	private Combo cbCRReportType;
	private Label lblCounterSet;
	private Combo cbCRCounterSet;
	private Button btnCounterGo;

	private TabItem tiSundryBookingAnalysis;
	private Canvas canSundryBookAnaysis;
	private Table tblSundryBookAnalysis;
	private TableColumn colSodSNo;
	private TableColumn colBranchCode;
	private TableColumn colStationCode;
	private TableColumn colTotalLr;
	private TableColumn colTotalSundryLrs;
	private TableColumn colTotalLrFreight;
	private TableColumn colTotalSundryFreight;
	private TableColumn colTotalSundryBft;
	private TableColumn colTotalSundryNoa;
	private TableColumn colTotalSundryAwt;
	private TableColumn colTotalSundryCrgWt;
	private TableColumn colOnTurnOver;
	private Text txtSBAFrom;
	private Button btnSBAFrom;
	private Text txtSBATo;
	private Combo cbSBABranch;

	private Button btnSBATo;
	private Button btnSBAGo;
	private Combo cbSBAStation;
	private String SUNDRY_BOOKING_TAB = "Sundry Booking Analysis";

	private TableColumn colUPDRTotalFreight;

	private TableColumn colUPDRInwardDays;

	private TableColumn colUPDCneePhone;

	private TableColumn colUPDRCnee;

	private TableColumn colUPDCnorPhone;

	private TableColumn colUPDRLrType;

	private TableColumn colUPDRLrDate;

	private TableColumn colUPDRLrNo;

	private TableColumn colUPDRStationCode;

	private TableColumn colUPDRBranchCode;
	private TabItem tiUPDReady;
	private Canvas canUPDReady;
	private Table tblUPDReady;
	private TableColumn colUpdSno;
	private TableColumn colUPDRCnor;
	private Label lblUPDRBranch;
	private Combo cbUPDRBranch;
	private Label lblUPDRStation;
	private Combo cbUPDRStation;
	private Label lblUPDRInwardDays;
	private Combo cbUPDRInwardDays;
	private Button btnUPDGo;

	private TabItem tiStates;
	private Canvas canStates;
	private Table tblIIStates;
	/*
	 * private Label lblStatesMonth; private Text txtStatesMonth; private Button
	 * btnStatesMonth;
	 */
	private TableColumn colstatesSno;
	private TableColumn colStatesTN;
	private TableColumn colStatesPHY;
	private TableColumn colStatesAP;
	private TableColumn colStatesKarnataka;
	private TableColumn colStatesKerala;
	private TableColumn colStatesTotal;

	private String INTER_INTRA = "Inter & Intra States";
	private String[] STATES = { "TamilNadu", "Pondicherry", "AndhraPradesh",
			"Karnataka", "Kerala", "Total" };
	private Button btnIISGo;
	private Combo cbIISChoice;

	// Missing customer
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
	private Combo cbMCpercent;
	private MissingCustomersDTO[] mcDto;

	// cancel L R
	private TabItem tiCancelledLRs;
	private Canvas cvsCancelledLRs;
	private Label label3;
	private Label label4;
	private Label label5;
	private Label label6;
	private Combo cbStation;
	private Combo cbCLReportType;
	private Button btnCancelledLrGo;
	private Text txtCancelledLRfromdate;
	private Button btncancelledlrfromdate;
	private Text txtcancelledlrtodate;
	private Table tblCancelledLR;
	private TableColumn colCLsno;
	private TableColumn colCLBranchCode;
	private TableColumn colCLStationCode;
	private TableColumn colCLNoOfCancelLR;
	private TableColumn colCLTotalFreight;
	private TableColumn colCLAvgFreight;
	private TableColumn colCancelledDetailedSno;
	private TableColumn colCancelledDetailedLrno;
	private TableColumn colCancelledDetailedLrdate;
	private TableColumn colCancelledDetailedLrtype;
	private TableColumn colCancelledDetailedTo;
	private TableColumn colCancelledDetailedNoa;
	private TableColumn colCancelledDetailedArtValue;
	private TableColumn colCancelledDetailedBft;
	private TableColumn colCancelledDetailedCC;
	private TableColumn colCancelledDetailedIec;
	private TableColumn colCancelledDetailedOthers;
	private TableColumn colCancelledDetailedDD;
	private TableColumn colCancelledDetailedTotal;
	private Combo cbCLRBranch;
	BookingDTO[] cancelledSummaryLRS = null;
	BookingDTO[] cancelledDetailedLRS = null;

	// analysis
	private TabItem tiCnorBookingAnalysis;
	private Canvas cvsCnorBookingAnalysis;
	private Table tblCnorBookingAnalysis;
	private TableColumn colCnorBDsno;
	private TableColumn colCnorBDBranchCode;
	private TableColumn colCnorBDCnorName;

	private Label lblCneeBABranch;
	private Combo cbCneeBABranch;
	private Label lblCneeBAMonths;
	private Combo cbCneeBAMonths;
	private Label lblCneeBAPickMonths;
	private List lstCneePickMonths;
	private Combo cbCneeBAOption;
	private Button btnCneeBAView;
	private Label lblCnorBABranch;
	private Combo cbCnorBABranch;
	private Label lblCnorBAMonths;
	private Combo cbCnorBAMonths;
	private Label lblCnorBAPickMonths;
	private List lstCnorPickMonths;
	private Combo cbCnorBAOption;
	private Button btnCnorBAView;
	private TableColumn colCneeBDCneeName;
	private TableColumn colCneeBDBranchCode;
	private Table tblCneeBookingAnalysis;
	private TableColumn colCneeBDsno;
	private TableColumn colCneeBDMonth4;
	private TableColumn colCnorBDMonths;

	// D R S
	private TabItem tiDRSAttendance;
	private Canvas cvsDRSAttendance;
	private Table tblDRSAttendance;
	private TableColumn colDRSsno;
	private TableColumn colDRSBranchCode;
	private TableColumn colDRSStationCode;
	private TableColumn colDRSMon1Ontime;
	private TableColumn colDRSMon1Total;
	private TableColumn colDRSMon1Percent;
	private TableColumn colDRSMon2Ontime;
	private TableColumn colDRSMon2Total;
	private TableColumn colDRSMon2Percent;
	private TableColumn colDRSMon3Ontime;
	private TableColumn colDRSMon3Total;
	private TableColumn colDRSMon3Percent;
	private Text txtDRSMonth;
	private Button btnDRSMonth;
	private Combo cbDRSAReportType;
	private Combo cbDRSAttendanceBranch;
	private Button btnDRSAttendance;

	/* OPen Lr */
	private Combo cbOpenLrBranch;
	private Label lblOpenLrBranch;
	private Button btnOpenLrTo;
	private Text txtOpenLrTo;
	private Label lblOpenLrTo;
	private Button btnOpenLrFrom;
	private Text txtOpenLrFrom;
	private Label lblOpenLrFromDate;
	private TableColumn colOpenLRCnee;
	private TabItem tiOpenLr;
	private Canvas canOpenLr;
	private Table tblOpenLr;
	private TableColumn colOpenLrBranchCode;
	private TableColumn colOpenLrStationCode;
	private TableColumn colOpenLrLrNo;
	private TableColumn colOpenLrLrDate;
	private TableColumn colOpenLrLrType;
	private TableColumn colOpenLrCardRate;
	private TableColumn colOpenLrBasicFreight;
	private TableColumn colOpenLrCCCrg;
	private TableColumn colOpenLrDDCrg;
	private TableColumn colOpenLrOthers;
	private TableColumn colOpensno;
	private TableColumn colOpenLRTotal;
	private TableColumn colOpenLRDiscount;
	private TableColumn colOpenLRCrgWt;
	private TableColumn colOpenLRCnor;
	/* Open LR Ends */

	private TabItem tiFOCLr;
	private Canvas canFOCLr;
	private Table tblFOCLr;
	private TableColumn colFOCLrLrNo;
	private TableColumn colFOCLrLrDate;
	private TableColumn colFOCLrLrFrom;
	private TableColumn colFOCLrTo;
	private TableColumn colFOCLrNoa;
	private TableColumn colFOCLrCrgWt;
	private TableColumn colFOCLrArticleType;
	private TableColumn colFOCLrArticleValue;
	private Label lblFOCLrFromDate;
	private Text txtFOCLrFrom;
	private Button btnFOCLrFrom;
	private Label lblFOCLrTo;
	private Text txtFOCLrTo;
	private Button btnFOCLrTo;
	private Label lblFOCLrBranch;
	private Combo cbFOCLrBranch;
	private TableColumn colFOCSno;
	private Label lblFOCLrReportType;
	private Combo cbFOCLrReportType;

	private TabItem tiMarketVehiUtilisation;
	private Canvas canMarkVehiUtil;
	private Table tblMarketVehicleUtilisation;

	private Label lblMarkVehiUtilSelectMonth;
	private Text txtMarkVehiUtilSelectMonth;
	private Button btnMarkVehiUtilSelectMonth;
	private Label lblMarkVehiUtilSelectBranch;
	private Combo cbMarkVehiUtilSelectBranch;

	private TabItem tiInwardAnalysis;
	private Canvas cvsInwardAnalysis;
	private Table tblInwardAnalysis;
	private Button btnGo;
	private Combo cbInwardStation;
	private Combo cbFromStation;
	private Label lblInwardStation;
	private Label label2;
	private Label label8;
	private Text txtLOGFrom;

	private Button btncancelledlrtodate;

	// 
	private Label lblTime;
	private Text txtLOGToDate;
	private Label lblLOGToDate;
	//private Combo cbLOGTime;
	//private Combo cbLOGFromMin;
	//private Combo cbLOGFromHour;
	private Button btnMargetVehicleGo;
	private Button btnFocLrGo;
	private Label lblCBAOption;
	private TabItem tiCneeBookingAnalysis;
	private Canvas cvsCneeBookingAnalysis;
	private Label lblCLRBranch;
	private Button btnOpenView;
	private Button btnLOGFrom;
	private Button btnLOGTo;
	private Combo cbOption;
	//Service Tax 
	private TabItem tiServicetax;
	private Canvas cvsServicetax;
	private Table tblServicetax;
	private TableColumn colSertaxSno;
	private TableColumn colSertaxStname;
	private TableColumn colSertaxTft;
	private TableColumn colSertaxTftof25;
	private TableColumn colSertaxService;
	private TableColumn colSertaxEdu;
	private TableColumn colSertaxHrsec;
	private TableColumn colSertaxTotalst;
	private Combo cbStReptype;
	private Label lblStReptype;
	private Label lblStFromDate;
	private Label lblStToDate;
	private Text txtStFrom;
	private Button btnStFromDate;
	private Text txtStTo;
	private Button btnStGo;
	private Button btnStTo;
	
	
	private TabItem tiServicetaxLR;
	private Canvas cvsServicetaxLR;
	private Table tblServicetaxLR;
	private TableColumn colSertaxLRSno;
	private TableColumn colSertaxLRLrno;
	private TableColumn colSertaxLRLrdate;
	private TableColumn colSertaxLRFrom;
	private TableColumn colSertaxLRTo;
	private TableColumn colSertaxLRCnor;
	private TableColumn colSertaxLRCnee;
	private TableColumn colSertaxLRTft;
	private TableColumn colSertaxLRTftof25;
	private TableColumn colSertaxLRService;
	private TableColumn colSertaxLREdu;
	private TableColumn colSertaxLRHrsec;
	private TableColumn colSertaxLRTotalst;
	private Combo cbStLRBranch;
	private Combo cbStLRStation;
	private Label lblStLRBranch;
	private Label lblStLRStation;
	private Label lblStLRFromDate;
	private Label lblStLRToDate;
	private Text txtStLRFrom;
	private Button btnStLRFromDate;
	private Text txtStLRTo;
	private Button btnStLRGo;
	private Button btnStLRTo;

	private TableColumn column;

	private String[] BRANCHES = null;

	private String[] COLUMNS = null;

	private final int COLUMN_WIDTH = 60;

	private final int TBL_WIDTH = 750;
	private final int TBL_HEIGHT = 450;
	private TableColumn colDDEOTHERS;
	private TableColumn colBPADiff1;
	private TableColumn colBPAHigh;
	private Text txtIISFrom;
	private Button btnIISFromDate;
	private Text txtIISTo;
	private Button btnIISTo;
	private TableColumn colOpenLrTo;
	private TableColumn colCancelledDetailedFrom;
	private TableColumn colDDEsno;
	private TableColumn colRSsno;
	private TableColumn colBPAsno;
	private TableColumn colBPADiff2;
	private TableColumn colUTsno;
	private TableColumn colSBASNo;
	private String MARKET_VEHICLE = "Market Vehicle Utilisation";
	private String FOC = "FOC Lr";
	private String OPENLR = "Open Lr";
	private String CONSINORBA = "Consignor Booking Analysis";
	private String CONSINEEBA = "Consignee Booking Analysis";
	private String CANCELLEDLR = "Cancelled LR's";
	private String LOG = "Left Over Goods";
	private String DRS = "DRS Attendance";
	private String Service_Tax = "Service Tax Annexure";
	private String Service_Tax_LR ="Service Tax Annexure - LR Wise";
	
	private Button btnExportXL;
	private Button btnExportPDF;
	//private Button btnExportTXT;
	DateFormat dateFormat = null;

	private static final String DDE_EXCEL_JRXML = "hm/akr/resources/printer/DDE.jrxml";
	private Label label17;
	private Label label16;
	private Label label15;
	private Label label18;
	private Label label10;
	private Label label9;
	private Label lblToState;
	private Label lblFrom;
	private Button btnPrint;
	private static final String CNEEBA_EXCEL_JRXML = "hm/akr/resources/printer/Cnee_BA.jrxml";
	private static final String RS_EXCEL_JRXML = "hm/akr/resources/printer/RemittanceShortage.jrxml";
	private static final String BPA_EXCEL_JRXML = "hm/akr/resources/printer/BPA.jrxml";
	private static final String CCSUMMARY_EXCEL_JRXML = "hm/akr/resources/printer/CCSummary.jrxml";
	private static final String UT_EXCEL_JRXML = "hm/akr/resources/printer/Undelivered_Topay.jrxml";
	private static final String UT_DET_EXCEL_JRXML = "hm/akr/resources/printer/Undelivered_Topay_Det.jrxml";
	private static final String DBA_EXCEL_JRXML = "hm/akr/resources/printer/DBA.jrxml";
	private static final String COUNTER_EXCEL_JRXML = "hm/akr/resources/printer/Counter.jrxml";
	private static final String COUNTER_MISC_EXCEL_JRXML = "hm/akr/resources/printer/Countermisc.jrxml";
	private static final String SBA_EXCEL_JRXML = "hm/akr/resources/printer/SBA.jrxml";
	private static final String UPD_EXCEL_JRXML = "hm/akr/resources/printer/UPD_Ready.jrxml";
	private static final String MISSINGCUSTOMER_EXCEL_JRXML = "hm/akr/resources/printer/Missing_Customer.jrxml";
	private static final String OPENLR_EXCEL_JRXML = "hm/akr/resources/printer/OpenLr.jrxml";
	private static final String IIS_EXCEL_JRXML = "hm/akr/resources/printer/IIS.jrxml";
	private static final String CNORBA_EXCEL_JRXML = "hm/akr/resources/printer/Cnor_BA.jrxml";
	
	private static final String MARKET_VEHICLE_EXCEL_JRXML = "hm/akr/resources/printer/MarketVehicle.jrxml";
	private static final String LOG_VEHICLE_EXCEL_JRXML = "hm/akr/resources/printer/LOG.jrxml";
	private static final String DRS_VEHICLE_EXCEL_JRXML = "hm/akr/resources/printer/DRS.jrxml";
	//private static final String DRS_VEHICLE_REP_EXCEL_JRXML = "hm/akr/resources/printer/DRS.jrxml";
	private static final String CANCELLEDLRS_EXCEL_JRXML = "hm/akr/resources/printer/Cancelled.jrxml";
	private static final String FOCLRS_EXCEL_JRXML = "hm/akr/resources/printer/FOCLRs.jrxml";
	private static final String SERVICETAX_JRXML = "hm/akr/resources/printer/Servicetax.jrxml";
	private static final String SERVICETAX_LR_JRXML = "hm/akr/resources/printer/ServicetaxLR.jrxml";
	private Shell shell = null; 
	VersionDTO[] vDto = null;
	
	HistoryHandler historyH = null;
	public AdminReports(Composite composite, int style) {
		super(composite, style);
		try {
				shell = composite.getShell();
			dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			handler = new AdminCompositeHandler();
			historyH = new HistoryHandler();
			vDto = historyH.getHistoryYears();
			if(vDto != null && vDto.length > 0){
				BeanUtil.setThree_month_updated(vDto[0].getUpdated_date());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Composite loadComposite() throws Exception {

		tabReport = new TabFolder(this, SWT.NONE);
		tabReport.setBounds(5, 30, 820, 500);
		tabReport.addSelectionListener(this);

		{
			tiDDExtra = new TabItem(tabReport, SWT.NONE);
			tiDDExtra.setText(DD_EXTRA);

			cvsDDExtra = new Canvas(tabReport, SWT.NONE);
			tiDDExtra.setControl(cvsDDExtra);

			{
				{
					tblDDExtra = new Table(cvsDDExtra, SWT.FULL_SELECTION
							| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);

					tblDDExtra.setHeaderVisible(true);
					tblDDExtra.setLinesVisible(true);
					tblDDExtra.setBounds(10, 72, 765, 400);
					{
						colDDEsno = new TableColumn(tblDDExtra, SWT.NONE);
						colDDEsno.setText("S NO");
						colDDEsno.setWidth(49);
						colDDEsno
								.addListener(SWT.Selection, new sortListener());

					}
					{
						colDDELrNo = new TableColumn(tblDDExtra, SWT.NONE);
						colDDELrNo.setText("LR No");
						colDDELrNo.setWidth(90);
						colDDELrNo.addListener(SWT.Selection,
								new sortListener());
					}
					
					{
						colDDETo = new TableColumn(tblDDExtra, SWT.NONE);
						colDDETo.setText("TO");
						colDDETo.setWidth(90);
						colDDETo.addListener(SWT.Selection,
								new sortListener());
					}
					{
						colDDELrType = new TableColumn(tblDDExtra, SWT.NONE);
						colDDELrType.setText("LR Type");
						colDDELrType.setWidth(90);
						colDDELrType.addListener(SWT.Selection,
								new sortListener());

					}
					{
						colDDEbft = new TableColumn(tblDDExtra, SWT.NONE);
						colDDEbft.setText("Basic Freight");
						colDDEbft.setWidth(90);
						colDDEbft
								.addListener(SWT.Selection, new sortListener());

					}
					{
						colDDEDD = new TableColumn(tblDDExtra, SWT.NONE);
						colDDEDD.setText("DD");
						colDDEDD.setWidth(80);
						colDDEDD.addListener(SWT.Selection, new sortListener());

					}
					{
						colDDEDDE = new TableColumn(tblDDExtra, SWT.NONE);
						colDDEDDE.setText("DD Extra");
						colDDEDDE.setWidth(80);
						colDDEDDE
								.addListener(SWT.Selection, new sortListener());

					}
					{
						colDDEOTHERS = new TableColumn(tblDDExtra, SWT.NONE);
						colDDEOTHERS.setText("Others");
						colDDEOTHERS.setWidth(80);
						colDDEOTHERS.addListener(SWT.Selection,
								new sortListener());

					}

					{
						colDDELrTotal = new TableColumn(tblDDExtra, SWT.NONE);
						colDDELrTotal.setText("LR Total");
						colDDELrTotal.setWidth(90);
						colDDELrTotal.addListener(SWT.Selection,
								new sortListener());

					}

					{
						colDDEPerTotal = new TableColumn(tblDDExtra, SWT.NONE);
						colDDEPerTotal.setText("% on Total");
						colDDEPerTotal.setWidth(90);
						colDDEPerTotal.addListener(SWT.Selection,
								new sortListener());

					}

				}

				{
					lblFromDate = new Label(cvsDDExtra, SWT.NONE);
					lblFromDate.setText("From Date");
					lblFromDate.setBounds(11, 28, 54, 20);
				}
				{
					lblToDate = new Label(cvsDDExtra, SWT.NONE);
					lblToDate.setText("To Date");
					lblToDate.setBounds(173, 28, 37, 16);
				}

				{
					txtDDEFrom = new Text(cvsDDExtra, SWT.BORDER);
					txtDDEFrom.setBounds(66, 26, 70, 22);
					txtDDEFrom.setEditable(false);
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					txtDDEFrom.setText(date);

				}
				{
					btnDDEFromDate = new Button(cvsDDExtra, SWT.PUSH
							| SWT.CENTER);
					btnDDEFromDate.setBounds(137, 25, 31, 23);
					btnDDEFromDate.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btnDDEFromDate.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent e) {
							KalendarDialog cd = new KalendarDialog(shell, 1);
							Object obj = cd.open();
							if (obj != null) {
								String date = obj.toString();
								txtDDEFrom.setText(date);
							}
						}
					});
				}

				{
					txtDDETo = new Text(cvsDDExtra, SWT.BORDER);
					txtDDETo.setBounds(213, 25, 70, 22);
					txtDDETo.setEditable(false);
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					txtDDETo.setText(date);

				}
				{
					btnDDETo = new Button(cvsDDExtra, SWT.PUSH | SWT.CENTER);
					btnDDETo.setBounds(283, 24, 32, 23);
					btnDDETo.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btnDDETo.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent e) {
							KalendarDialog cd = new KalendarDialog(shell);
							Object obj = cd.open();
							if (obj != null) {
								String date = obj.toString();
								txtDDETo.setText(date);
							}
						}
					});
				}

				{
					cbDDEBranch = new Combo(cvsDDExtra, SWT.READ_ONLY);
					cbDDEBranch.setBounds(361, 27, 130, 20);
					cbDDEBranch.addSelectionListener(this);
					cbDDEBranch.add("All");
				}
				{
					label7 = new Label(cvsDDExtra, SWT.NONE);
					label7.setText("Branch");
					label7.setBounds(322, 28, 39, 15);
					
				}
				{
					lblDDEStation = new Label(cvsDDExtra, SWT.NONE);
					lblDDEStation.setText("Station");
					lblDDEStation.setBounds(502, 27, 36, 21);
				}
				{
					cbDDEStation = new Combo(cvsDDExtra, SWT.READ_ONLY);
					cbDDEStation.setBounds(540, 27, 130, 21);
					cbDDEStation.add("All");
				}

				{
					lblAbove = new Label(cvsDDExtra, SWT.NONE);
					lblAbove.setText("Above");
					lblAbove.setBounds(682, 28, 32, 21);
				}
				{
					txtDDEAbove = new Text(cvsDDExtra, SWT.BORDER);
					txtDDEAbove.setBounds(716, 27, 45, 21);
					txtDDEAbove.addVerifyListener(new NumericValidation());
				}

				{
					btnDDEGo = new Button(cvsDDExtra, SWT.PUSH | SWT.CENTER);
					btnDDEGo.setText("Go");
					btnDDEGo.setBounds(768, 27, 37, 23);
					btnDDEGo.addSelectionListener(this);
				}
			}
		}
		{
			// Remittance

			tiRemittance = new TabItem(tabReport, SWT.NONE);
			tiRemittance.setText(RS);

			cvsRemittance = new Canvas(tabReport, SWT.NONE);
			tiRemittance.setControl(cvsRemittance);

			{
				{
					tblRemittance = new Table(cvsRemittance, SWT.FULL_SELECTION
							| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);

					tblRemittance.setHeaderVisible(true);
					tblRemittance.setLinesVisible(true);
					tblRemittance.setBounds(10, 72, 795, 400);

					// tblRemittance.getColumn(11).addListener(SWT.Selection,
					// new sortListener());
					{
						colRSsno = new TableColumn(tblRemittance, SWT.NONE);
						colRSsno.setText("S NO");
						colRSsno.setWidth(49);
					}
					{
						colRSfrom = new TableColumn(tblRemittance, SWT.NONE);
						colRSfrom.setText("From");
						colRSfrom.setWidth(80);

					}
					{
						colRSto = new TableColumn(tblRemittance, SWT.NONE);
						colRSto.setText("To");
						colRSto.setWidth(80);

					}
					{
						colRSLrNo = new TableColumn(tblRemittance, SWT.NONE);

						colRSLrNo.setText("LR No");
						colRSLrNo.setWidth(80);

					}

					{
						colRSLrdate = new TableColumn(tblRemittance, SWT.NONE);
						colRSLrdate.setText("LR Date");
						colRSLrdate.setWidth(80);

					}
					{
						colRSType = new TableColumn(tblRemittance, SWT.NONE);
						colRSType.setText("LR Mode");
						colRSType.setWidth(80);

					}
					{
						colDRSNo = new TableColumn(tblRemittance, SWT.NONE);
						colDRSNo.setText("DRS No");
						colDRSNo.setWidth(90);

					}
					{
						colDRSdate = new TableColumn(tblRemittance, SWT.NONE);
						colDRSdate.setText("DRS Date");
						colDRSdate.setWidth(90);

					}
					{
						colRSCRno = new TableColumn(tblRemittance, SWT.NONE);
						colRSCRno.setText("CR No");
						colRSCRno.setWidth(90);

					}

					{
						colRSCnor = new TableColumn(tblRemittance, SWT.NONE);
						colRSCnor.setText("Cnor");
						colRSCnor.setWidth(90);

					}

					{
						colRSCnee = new TableColumn(tblRemittance, SWT.NONE);
						colRSCnee.setText("Cnee");
						colRSCnee.setWidth(90);

					}

					{
						colRSamount = new TableColumn(tblRemittance, SWT.NONE);
						colRSamount.setText("Amount");
						colRSamount.setWidth(100);

					}

					{
						colRSrecoveryDt = new TableColumn(tblRemittance,
								SWT.NONE);
						colRSrecoveryDt.setText("Recovery Dt");
						colRSrecoveryDt.setWidth(90);

					}

					for (int index = 0; index < tblRemittance.getColumnCount(); index++) {
						tblRemittance.getColumn(index).addListener(
								SWT.Selection, new sortListener());
					}

				}

				{
					cbRSBranch = new Combo(cvsRemittance, SWT.READ_ONLY);
					cbRSBranch.setBounds(61, 27, 130, 20);
					cbRSBranch.addSelectionListener(this);
				}
				{
					label7 = new Label(cvsRemittance, SWT.NONE);
					label7.setText("Branch");
					label7.setBounds(22, 28, 39, 15);
				}

				{
					label11 = new Label(cvsRemittance, SWT.NONE);
					label11.setText("Select Month");
					label11.setBounds(208, 28, 64, 17);
				}
				{
					txtRSMonth = new Text(cvsRemittance, SWT.BORDER
							| SWT.READ_ONLY);
					txtRSMonth.setBounds(272, 27, 57, 21);
					DateFormat dateFormat = new SimpleDateFormat("MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					txtRSMonth.setText(date);
				}
				{
					btnRSMonth = new Button(cvsRemittance, SWT.PUSH
							| SWT.CENTER);
					btnRSMonth.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btnRSMonth.setBounds(331, 25, 26, 23);
					btnRSMonth.addSelectionListener(new SelectionListener() {

						@Override
						public void widgetDefaultSelected(SelectionEvent arg0) {

						}

						@Override
						public void widgetSelected(SelectionEvent event) {
							MonthDialog cd = new MonthDialog(Display
									.getCurrent().getActiveShell());
							Object obj = cd.open();
							if (obj != null) {
								txtRSMonth.setText(obj.toString());
							}
						}

					});
				}

				{
					btnRSGo = new Button(cvsRemittance, SWT.PUSH | SWT.CENTER);
					btnRSGo.setText("Go");
					btnRSGo.setBounds(568, 27, 37, 23);
					btnRSGo.addSelectionListener(this);
				}
				{
					rdFull = new Button(cvsRemittance, SWT.RADIO | SWT.LEFT);
					rdFull.setText("Full Report");
					rdFull.setBounds(391, 27, 72, 21);
					rdFull.addSelectionListener(this);
				}
				{
					rdUnrecover = new Button(cvsRemittance, SWT.RADIO
							| SWT.LEFT);
					rdUnrecover.setText("Unrecovered");
					rdUnrecover.setBounds(465, 27, 88, 21);
					rdUnrecover.setSelection(true);
					rdUnrecover.addSelectionListener(this);
				}
			}

		}

		/*{

			tiBPA = new TabItem(tabReport, SWT.NONE);
			tiBPA.setText(BPA);

			cvsBPA = new Canvas(tabReport, SWT.NONE);
			tiBPA.setControl(cvsBPA);

			{
				{
					tblBPA = new Table(cvsBPA, SWT.FULL_SELECTION
							| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);

					tblBPA.setHeaderVisible(true);
					tblBPA.setLinesVisible(true);
					tblBPA.setBounds(10, 72, 800, 400);
					{
						colBPAsno = new TableColumn(tblBPA, SWT.NONE);
						colBPAsno.setText("S NO");
						colBPAsno.setWidth(50);

					}
					{
						colBPABranch = new TableColumn(tblBPA, SWT.NONE);
						colBPABranch.setText("Name");
						colBPABranch.setWidth(180);

					}
					{
						colBPAFigure = new TableColumn(tblBPA, SWT.NONE);
						colBPAFigure.setText("Booking Figure");
						colBPAFigure.setWidth(110);

					}
					{
						colBPAAvg = new TableColumn(tblBPA, SWT.NONE);
						colBPAAvg.setText("Average");
						colBPAAvg.setWidth(90);

					}

					{
						colBPALastMonth = new TableColumn(tblBPA, SWT.NONE);
						colBPALastMonth.setText("Last Month");
						colBPALastMonth.setWidth(90);

					}
					{
						colBPADiff1 = new TableColumn(tblBPA, SWT.NONE);
						colBPADiff1.setText("Difference1");
						colBPADiff1.setWidth(90);
					}

					{
						colBPALastYear = new TableColumn(tblBPA, SWT.NONE);
						colBPALastYear.setText("Last Year");
						colBPALastYear.setWidth(90);

					}
					{
						colBPADiff2 = new TableColumn(tblBPA, SWT.NONE);
						colBPADiff2.setText("Difference2");
						colBPADiff2.setWidth(90);
					}
					{
						colBPAHigh = new TableColumn(tblBPA, SWT.NONE);
						colBPAHigh.setText("Highest");
						colBPAHigh.setWidth(90);
					}

					for (int index = 0; index < tblBPA.getColumnCount(); index++) {
						tblBPA.getColumn(index).addListener(SWT.Selection,
								new sortListener());
					}

				}

				{
					lblFromDate = new Label(cvsBPA, SWT.NONE);
					lblFromDate.setText("From Date");
					lblFromDate.setBounds(11, 28, 54, 20);
				}
				{
					lblToDate = new Label(cvsBPA, SWT.NONE);
					lblToDate.setText("To Date");
					lblToDate.setBounds(173, 28, 37, 16);
				}

				{
					txtBPAFrom = new Text(cvsBPA, SWT.BORDER);
					txtBPAFrom.setBounds(66, 26, 70, 22);
					txtBPAFrom.setEditable(false);
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					txtBPAFrom.setText(date);

				}
				{
					btnBPAFromDate = new Button(cvsBPA, SWT.PUSH | SWT.CENTER);
					btnBPAFromDate.setBounds(137, 25, 31, 23);
					btnBPAFromDate.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btnBPAFromDate.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent e) {
							KalendarDialog cd = new KalendarDialog(new Shell());
							Object obj = cd.open();
							if (obj != null) {
								String date = obj.toString();
								txtBPAFrom.setText(date);
								int index = 0;
								String fromDate = txtBPAFrom.getText();
								index = fromDate.indexOf("-");
								int dt = Integer.parseInt(fromDate.substring(0,
										index));
								int month = Integer.parseInt(fromDate
										.substring(index + 1, index + 3));
								int year = Integer.parseInt(fromDate
										.substring(index + 4));
								// System.out.println("d===>" + dt + "-" +
								// (month - 1) + "-" + (year - 1));

							}
						}
					});
				}

				{
					txtBPATo = new Text(cvsBPA, SWT.BORDER);
					txtBPATo.setBounds(213, 25, 70, 22);
					txtBPATo.setEditable(false);
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					txtBPATo.setText(date);

				}
				{
					btnBPATo = new Button(cvsBPA, SWT.PUSH | SWT.CENTER);
					btnBPATo.setBounds(283, 24, 32, 23);
					btnBPATo.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btnBPATo.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent e) {
							KalendarDialog cd = new KalendarDialog(shell);
							Object obj = cd.open();
							if (obj != null) {
								String date = obj.toString();
								txtBPATo.setText(date);
							}
						}
					});
				}

				{
					label7 = new Label(cvsBPA, SWT.NONE);
					label7.setText("Branch");
					label7.setBounds(322, 28, 39, 15);
				}
				{
					cbBPABranch = new Combo(cvsBPA, SWT.READ_ONLY);
					cbBPABranch.setBounds(361, 27, 130, 20);
					cbBPABranch.addSelectionListener(this);
					cbBPABranch.add("All");
				}

				{
					cbBPAChoice = new Combo(cvsBPA, SWT.READ_ONLY);
					cbBPAChoice.setBounds(500, 27, 72, 21);
					cbBPAChoice.addSelectionListener(this);
					cbBPAChoice.add(COUNT);
					cbBPAChoice.add(BASIC_FREIGHT);
					cbBPAChoice.add(TOTAL_FREIGHT);
					cbBPAChoice.add(ACTUAL_WEIGHT);
					cbBPAChoice.add(CHARGED_WEIGHT);
					cbBPAChoice.add(NOA);
					cbBPAChoice.addSelectionListener(this);
				}
				{
					cbBPAReportType = new Combo(cvsBPA, SWT.READ_ONLY);
					cbBPAReportType.setBounds(650, 27, 90, 20);
					cbBPAReportType.addSelectionListener(this);
					cbBPAReportType.add("Consolidated");
					cbBPAReportType.add("Detailed");
				}
				{
					btnBPAGo = new Button(cvsBPA, SWT.PUSH | SWT.CENTER);
					btnBPAGo.setText("Go");
					btnBPAGo.setBounds(742, 26, 37, 23);
					btnBPAGo.addSelectionListener(this);
				}
				{
					lblBPARepType = new Label(cvsBPA, SWT.NONE);
					lblBPARepType.setText("Report Type");
					lblBPARepType.setBounds(584, 28, 60, 21);
				}
			}

		}*/
		
		
		
		tiBPA = new TabItem(tabReport, SWT.NONE);
		tiBPA.setText(BPA);

		cvsBPA = new Canvas(tabReport, SWT.NONE);
		tiBPA.setControl(cvsBPA);

		{
			{
				tblBPA = new Table(cvsBPA, SWT.FULL_SELECTION
						| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);

				tblBPA.setHeaderVisible(true);
				tblBPA.setLinesVisible(true);
				tblBPA.setBounds(10, 72, 800, 400);
				{
					colBPAsno = new TableColumn(tblBPA, SWT.NONE);
					colBPAsno.setText("S NO");
					colBPAsno.setWidth(50);

				}
				{
					colBPABranch = new TableColumn(tblBPA, SWT.NONE);
					colBPABranch.setText("Station Name");
					colBPABranch.setWidth(180);

				}
				{
					colBPANolr = new TableColumn(tblBPA, SWT.NONE);
					colBPANolr.setText("No. of Lrs");
					colBPANolr.setWidth(70);

				}
				{
					colBPANoa = new TableColumn(tblBPA, SWT.NONE);
					colBPANoa.setText("NOA");
					colBPANoa.setWidth(70);

				}

				{
					colBPAActwt = new TableColumn(tblBPA, SWT.NONE);
					colBPAActwt.setText("Actual Weight");
					colBPAActwt.setWidth(70);

				}
				{
					colBPACrgwt = new TableColumn(tblBPA, SWT.NONE);
					colBPACrgwt.setText("Charged Weight");
					colBPACrgwt.setWidth(70);
				}

				{
					colBPABft = new TableColumn(tblBPA, SWT.NONE);
					colBPABft.setText("Basic Freight");
					colBPABft.setWidth(70);

				}
				{
					colBPALrc = new TableColumn(tblBPA, SWT.NONE);
					colBPALrc.setText("LRC");
					colBPALrc.setWidth(70);
				}
				{
					colBPADhc = new TableColumn(tblBPA, SWT.NONE);
					colBPADhc.setText("DHC");
					colBPADhc.setWidth(70);
				}
				
				{
					colBPACcc = new TableColumn(tblBPA, SWT.NONE);
					colBPACcc.setText("CCC");
					colBPACcc.setWidth(70);
				}
				{
					colBPADcc = new TableColumn(tblBPA, SWT.NONE);
					colBPADcc.setText("DCC");
					colBPADcc.setWidth(70);
				}
				{
					colBPADdc = new TableColumn(tblBPA, SWT.NONE);
					colBPADdc.setText("DDC");
					colBPADdc.setWidth(70);
				}
				
				{
					colBPAIec = new TableColumn(tblBPA, SWT.NONE);
					colBPAIec.setText("IEC");
					colBPAIec.setWidth(70);
				}
				{
					colBPALc = new TableColumn(tblBPA, SWT.NONE);
					colBPALc.setText("LC");
					colBPALc.setWidth(70);
				}
				
				{
					colBPAStax = new TableColumn(tblBPA, SWT.NONE);
					colBPAStax.setText("Stax");
					colBPAStax.setWidth(70);
				}
				
				{
					colBPATotal = new TableColumn(tblBPA, SWT.NONE);
					colBPATotal.setText("Total");
					colBPATotal.setWidth(70);
				}
				
				for (int index = 0; index < tblBPA.getColumnCount(); index++) {
					tblBPA.getColumn(index).addListener(SWT.Selection,
							new sortListener());
				}
				
				
				{
					lblFromDate = new Label(cvsBPA, SWT.NONE);
					lblFromDate.setText("From Date");
					lblFromDate.setBounds(11, 28, 54, 20);
				}
				{
					lblToDate = new Label(cvsBPA, SWT.NONE);
					lblToDate.setText("To Date");
					lblToDate.setBounds(173, 28, 37, 16);
				}

				{
					txtBPAFrom = new Text(cvsBPA, SWT.BORDER);
					txtBPAFrom.setBounds(66, 26, 70, 22);
					txtBPAFrom.setEditable(false);
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					txtBPAFrom.setText(date);

				}
				{
					btnBPAFromDate = new Button(cvsBPA, SWT.PUSH | SWT.CENTER);
					btnBPAFromDate.setBounds(137, 25, 31, 23);
					btnBPAFromDate.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btnBPAFromDate.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent e) {
							KalendarDialog cd = new KalendarDialog(new Shell());
							Object obj = cd.open();
							if (obj != null) {
								String date = obj.toString();
								txtBPAFrom.setText(date);
								int index = 0;
								String fromDate = txtBPAFrom.getText();
								index = fromDate.indexOf("-");
								int dt = Integer.parseInt(fromDate.substring(0,
										index));
								int month = Integer.parseInt(fromDate
										.substring(index + 1, index + 3));
								int year = Integer.parseInt(fromDate
										.substring(index + 4));
								// System.out.println("d===>" + dt + "-" +
								// (month - 1) + "-" + (year - 1));

							}
						}
					});
				}

				{
					txtBPATo = new Text(cvsBPA, SWT.BORDER);
					txtBPATo.setBounds(213, 25, 70, 22);
					txtBPATo.setEditable(false);
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					txtBPATo.setText(date);

				}
				{
					btnBPATo = new Button(cvsBPA, SWT.PUSH | SWT.CENTER);
					btnBPATo.setBounds(283, 24, 32, 23);
					btnBPATo.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btnBPATo.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent e) {
							KalendarDialog cd = new KalendarDialog(shell);
							Object obj = cd.open();
							if (obj != null) {
								String date = obj.toString();
								txtBPATo.setText(date);
							}
						}
					});
				}

				{
					label7 = new Label(cvsBPA, SWT.NONE);
					label7.setText("Branch");
					label7.setBounds(322, 28, 39, 15);
				}
				{
					cbBPABranch = new Combo(cvsBPA, SWT.READ_ONLY);
					cbBPABranch.setBounds(361, 27, 130, 20);
					cbBPABranch.addSelectionListener(this);
					cbBPABranch.add("All");
				}

				
				{
					cbBPAReportType = new Combo(cvsBPA, SWT.READ_ONLY);
					cbBPAReportType.setBounds(500, 27, 90, 20);
					cbBPAReportType.addSelectionListener(this);
					cbBPAReportType.add("Consolidated");
					cbBPAReportType.add("Detailed");
				}
				{
					btnBPAGo = new Button(cvsBPA, SWT.PUSH | SWT.CENTER);
					btnBPAGo.setText("Go");
					btnBPAGo.setBounds(600, 26, 37, 23);
					btnBPAGo.addSelectionListener(this);
				}
				
			}

		}
		
		

		{

			tiCCSummary = new TabItem(tabReport, SWT.NONE);
			tiCCSummary.setText(CCC_SUMMARY_TAB);

			cvsCCCSummary = new Canvas(tabReport, SWT.NONE);
			tiCCSummary.setControl(cvsCCCSummary);

			{

				{
					tblCCCSummary = new Table(cvsCCCSummary, SWT.FULL_SELECTION
							| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);

					tblCCCSummary.setHeaderVisible(true);
					tblCCCSummary.setLinesVisible(true);
					tblCCCSummary.setBounds(10, 72, 800, 400);
					{
						colCCSsno = new TableColumn(tblCCCSummary, SWT.NONE);
						colCCSsno.setText("S NO");
						colCCSsno.setWidth(40);

					}
					{
						colCCSBranchCode = new TableColumn(tblCCCSummary,
								SWT.NONE);
						colCCSBranchCode.setText("Branch Code");
						colCCSBranchCode.setWidth(80);

					}
					{
						colCCSStationCode = new TableColumn(tblCCCSummary,
								SWT.NONE);
						colCCSStationCode.setText("Station Code");
						colCCSStationCode.setWidth(80);

					}
					{
						colCCSTotalPT = new TableColumn(tblCCCSummary, SWT.NONE);
						colCCSTotalPT.setText("Total PD/TP Lrs");
						colCCSTotalPT.setWidth(100);

					}
					{
						colCCLRs = new TableColumn(tblCCCSummary, SWT.NONE);
						colCCLRs.setText("Total CC LRs");
						colCCLRs.setWidth(100);

					}

					{
						colCCbftofTot = new TableColumn(tblCCCSummary, SWT.NONE);
						colCCbftofTot.setText("BasFrt of Total LRs");
						colCCbftofTot.setWidth(100);

					}

					{
						colCCSBFTLr = new TableColumn(tblCCCSummary, SWT.NONE);
						colCCSBFTLr.setText("Bas.Frt.of CC Lrs");
						colCCSBFTLr.setWidth(100);

					}

					{
						colCCSTotalCCC = new TableColumn(tblCCCSummary,
								SWT.NONE);
						colCCSTotalCCC.setText("Total CC Charge");
						colCCSTotalCCC.setWidth(100);

					}

					{
						colCCperofCConTot = new TableColumn(tblCCCSummary,
								SWT.NONE);
						colCCperofCConTot.setText("%of CC on Total LRs");
						colCCperofCConTot.setWidth(100);

					}

					{
						colCCCOnBasFrt = new TableColumn(tblCCCSummary,
								SWT.NONE);
						colCCCOnBasFrt.setText("% of CC on CC LRs");
						colCCCOnBasFrt.setWidth(120);

					}

					for (int index = 0; index < tblCCCSummary.getColumnCount(); index++) {
						tblCCCSummary.getColumn(index).addListener(
								SWT.Selection, new sortListener());
					}

				}
				{
					cbCCCBranch = new Combo(cvsCCCSummary, SWT.READ_ONLY);
					cbCCCBranch.setBounds(485, 27, 121, 21);
					cbCCCBranch.add("All");
					cbCCCBranch.addSelectionListener(this);
				}
				{
					label7 = new Label(cvsCCCSummary, SWT.NONE);
					label7.setText("Branch");
					label7.setBounds(446, 28, 37, 15);
				}
				{
					lblFromDate = new Label(cvsCCCSummary, SWT.NONE);
					lblFromDate.setText("From Date");
					lblFromDate.setBounds(52, 28, 54, 20);
				}
				{
					lblToDate = new Label(cvsCCCSummary, SWT.NONE);
					lblToDate.setText("To Date");
					lblToDate.setBounds(255, 28, 43, 16);
				}

				{
					txtCCCFrom = new Text(cvsCCCSummary, SWT.BORDER);
					txtCCCFrom.setBounds(110, 26, 94, 22);
					txtCCCFrom.setEditable(false);
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					txtCCCFrom.setText(date);

				}
				{
					btnCCCFromDate = new Button(cvsCCCSummary, SWT.PUSH
							| SWT.CENTER);
					btnCCCFromDate.setBounds(204, 25, 31, 23);
					btnCCCFromDate.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btnCCCFromDate.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent e) {
							KalendarDialog cd = new KalendarDialog(shell);
							Object obj = cd.open();
							if (obj != null) {
								String date = obj.toString();
								txtCCCFrom.setText(date);
							}
						}
					});
				}

				{
					txtCCCTo = new Text(cvsCCCSummary, SWT.BORDER);
					txtCCCTo.setBounds(298, 25, 92, 22);
					txtCCCTo.setEditable(false);
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					txtCCCTo.setText(date);

				}
				{
					btnCCCTo = new Button(cvsCCCSummary, SWT.PUSH | SWT.CENTER);
					btnCCCTo.setBounds(389, 23, 32, 23);
					btnCCCTo.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btnCCCTo.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent e) {
							KalendarDialog cd = new KalendarDialog(shell);
							Object obj = cd.open();
							if (obj != null) {
								String date = obj.toString();
								txtCCCTo.setText(date);
							}
						}
					});
				}
				{
					btnCCCGo = new Button(cvsCCCSummary, SWT.PUSH | SWT.CENTER);
					btnCCCGo.setText("Go");
					btnCCCGo.setBounds(611, 27, 37, 22);
					btnCCCGo.addSelectionListener(this);
				}
			}
		}

		{

			tiUndeliveredTopay = new TabItem(tabReport, SWT.NONE);
			tiUndeliveredTopay.setText(UT_TOPAY);

			cvsUndeliveredTopay = new Canvas(tabReport, SWT.NONE);
			tiUndeliveredTopay.setControl(cvsUndeliveredTopay);

			{

				{
					tblUndeliveredTopayDet = new Table(cvsUndeliveredTopay,
							SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL
									| SWT.BORDER);

					tblUndeliveredTopayDet.setHeaderVisible(true);
					tblUndeliveredTopayDet.setLinesVisible(true);
					tblUndeliveredTopayDet.setBounds(10, 72, 800, 400);
					{
						colUTsno = new TableColumn(tblUndeliveredTopayDet,
								SWT.NONE);
						colUTsno.setText("S NO");
						colUTsno.setWidth(40);

					}
					{
						colUTLrno = new TableColumn(tblUndeliveredTopayDet,
								SWT.NONE);
						colUTLrno.setText("LR NO");
						colUTLrno.setWidth(80);

					}
					{
						colUTLrdate = new TableColumn(tblUndeliveredTopayDet,
								SWT.NONE);
						colUTLrdate.setText("LR Date");
						colUTLrdate.setWidth(80);
					}
					{
						colUTFrom = new TableColumn(tblUndeliveredTopayDet,
								SWT.NONE);
						colUTFrom.setText("From");
						colUTFrom.setWidth(70);

					}
					{
						colUTTo = new TableColumn(tblUndeliveredTopayDet,
								SWT.NONE);
						colUTTo.setText("To");
						colUTTo.setWidth(70);

					}

					{
						colUTnoa = new TableColumn(tblUndeliveredTopayDet,
								SWT.NONE);
						colUTnoa.setText("NOA");
						colUTnoa.setWidth(50);

					}
					{
						colUTActWt = new TableColumn(tblUndeliveredTopayDet,
								SWT.NONE);
						colUTActWt.setText("Act Wt");
						colUTActWt.setWidth(70);

					}
					{
						colUTbft = new TableColumn(tblUndeliveredTopayDet,
								SWT.NONE);
						colUTbft.setText("Basic Freight");
						colUTbft.setWidth(100);

					}
					{
						colUTtotalFt = new TableColumn(tblUndeliveredTopayDet,
								SWT.NONE);
						colUTtotalFt.setText("Total Freight");
						colUTtotalFt.setWidth(100);

					}
					{
						colUTCurStatus = new TableColumn(
								tblUndeliveredTopayDet, SWT.NONE);
						colUTCurStatus.setText("Current Status");
						colUTCurStatus.setWidth(110);

					}

					for (int index = 0; index < tblUndeliveredTopayDet
							.getColumnCount(); index++) {
						tblUndeliveredTopayDet.getColumn(index).addListener(
								SWT.Selection, new sortListener());
					}

				}
				/*{
					label11 = new Label(cvsUndeliveredTopay, SWT.NONE);
					label11.setText("Month");
					label11.setBounds(71, 34, 34, 17);
				}
				{
					txtTUTMonth = new Text(cvsUndeliveredTopay, SWT.BORDER);
					txtTUTMonth.setBounds(108, 32, 57, 21);
					DateFormat dateFormat = new SimpleDateFormat("MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					txtTUTMonth.setText(date);
					txtTUTMonth.setEnabled(false);
				}
				{
					btnTUTMonth = new Button(cvsUndeliveredTopay, SWT.PUSH
							| SWT.CENTER);
					btnTUTMonth.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btnTUTMonth.setBounds(174, 32, 26, 23);
					btnTUTMonth.addSelectionListener(new SelectionListener() {

						@Override
						public void widgetDefaultSelected(SelectionEvent arg0) {

						}

						@Override
						public void widgetSelected(SelectionEvent event) {
							MonthDialog cd = new MonthDialog(Display
									.getCurrent().getActiveShell());
							Object obj = cd.open();
							if (obj != null) {
								txtTUTMonth.setText(obj.toString());
							}
						}

					});
				}*/
				
				{
					lblUTFromDate = new Label(cvsUndeliveredTopay, SWT.NONE);
					lblUTFromDate.setText("From Date");
					lblUTFromDate.setBounds(15, 28, 54, 20);
				}
				{
					lblUTToDate = new Label(cvsUndeliveredTopay, SWT.NONE);
					lblUTToDate.setText("To Date");
					lblUTToDate.setBounds(180, 28, 40, 16);
				}

				{
					txtUTFrom = new Text(cvsUndeliveredTopay, SWT.BORDER);
					txtUTFrom.setBounds(80, 26, 70, 22);
					txtUTFrom.setEditable(false);
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					txtUTFrom.setText(date);

				}
				{
					btnUTFromDate = new Button(cvsUndeliveredTopay, SWT.PUSH
							| SWT.CENTER);
					btnUTFromDate.setBounds(150, 25, 31, 23);
					btnUTFromDate.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btnUTFromDate.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent e) {
							KalendarDialog cd = new KalendarDialog(shell, 1);
							Object obj = cd.open();
							if (obj != null) {
								String date = obj.toString();
								txtUTFrom.setText(date);
							}
						}
					});
				}

				{
					txtUTTo = new Text(cvsUndeliveredTopay, SWT.BORDER);
					txtUTTo.setBounds(235, 25, 70, 22);
					txtUTTo.setEditable(false);
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					txtUTTo.setText(date);

				}
				{
					btnUTTo = new Button(cvsUndeliveredTopay, SWT.PUSH | SWT.CENTER);
					btnUTTo.setBounds(305, 24, 32, 23);
					btnUTTo.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btnUTTo.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent e) {
							KalendarDialog cd = new KalendarDialog(shell);
							Object obj = cd.open();
							if (obj != null) {
								String date = obj.toString();
								txtUTTo.setText(date);
							}
						}
					});
				}


				{
					label13 = new Label(cvsUndeliveredTopay, SWT.NONE);
					label13.setText("Report Type");
					label13.setBounds(342, 24, 68, 20);
				}
				{
					cbUTReportType = new Combo(cvsUndeliveredTopay,
							SWT.READ_ONLY);
					cbUTReportType.setBounds(415, 24, 86, 21);
					cbUTReportType.add("Detailed");
					cbUTReportType.add("Summary");
					cbUTReportType.select(1);
					cbUTReportType.addSelectionListener(this);
				}

				{
					label12 = new Label(cvsUndeliveredTopay, SWT.NONE);
					label12.setText("Branch");
					label12.setBounds(515, 13, 72, 19);
				}
				{
					cbUTBranch = new Combo(cvsUndeliveredTopay, SWT.READ_ONLY);
					cbUTBranch.setBounds(590, 13, 96, 21);
					cbUTBranch.add("All");
					cbUTBranch.select(0);
					cbUTBranch.setEnabled(true);
					cbUTBranch.addSelectionListener(this);
				}

				{
					label14 = new Label(cvsUndeliveredTopay, SWT.NONE);
					label14.setText("Station");
					label14.setBounds(515, 40, 72, 19);
				}
				{
					cbUTStation = new Combo(cvsUndeliveredTopay, SWT.READ_ONLY);
					cbUTStation.setBounds(590, 40, 96, 21);
					cbUTStation.add("All");
					cbUTStation.select(0);
					cbUTStation.setEnabled(false);
					cbUTStation.addSelectionListener(this);
					
				}
				{
					btnUTGo = new Button(cvsUndeliveredTopay, SWT.PUSH);
					btnUTGo.setBounds(725, 30, 36, 23);
					btnUTGo.setText("Go");
					btnUTGo.addSelectionListener(this);
				}
			}

			{
				tblUndeliveredTopay = new Table(cvsUndeliveredTopay,
						SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL
								| SWT.BORDER);

				tblUndeliveredTopay.setHeaderVisible(true);
				tblUndeliveredTopay.setLinesVisible(true);
				tblUndeliveredTopay.setBounds(10, 72, 800, 400);
				{
					colTUTsno = new TableColumn(tblUndeliveredTopay, SWT.NONE);
					colTUTsno.setText("S NO");
					colTUTsno.setWidth(40);

				}
				{
					colTUTBranchCode = new TableColumn(tblUndeliveredTopay,
							SWT.NONE);
					colTUTBranchCode.setText("Branch Code");
					colTUTBranchCode.setWidth(94);

				}
				{
					colTUTStationCode = new TableColumn(tblUndeliveredTopay,
							SWT.NONE);
					colTUTStationCode.setText("Station Code");
					colTUTStationCode.setWidth(94);
				}
				{
					colTUTBoookedNoOfLRs = new TableColumn(tblUndeliveredTopay,
							SWT.NONE);
					colTUTBoookedNoOfLRs.setText("Inwarded No.Of.LRs");
					colTUTBoookedNoOfLRs.setWidth(120);

				}
				{
					colTUTBoookedTotFrt = new TableColumn(tblUndeliveredTopay,
							SWT.NONE);
					colTUTBoookedTotFrt.setText("Inwarded Total Freight");
					colTUTBoookedTotFrt.setWidth(130);

				}
				{
					colTUTDeliverNoOfLRs = new TableColumn(tblUndeliveredTopay,
							SWT.NONE);
					colTUTDeliverNoOfLRs.setText("Yet to Deliver No.Of.LRs");
					colTUTDeliverNoOfLRs.setWidth(140);

				}
				{
					colTUTDeliverTotFrt = new TableColumn(tblUndeliveredTopay,
							SWT.NONE);
					colTUTDeliverTotFrt.setText("Yet to Deliver Total Freight");
					colTUTDeliverTotFrt.setWidth(150);

				}

				for (int index = 0; index < tblUndeliveredTopay
						.getColumnCount(); index++) {
					tblUndeliveredTopay.getColumn(index).addListener(
							SWT.Selection, new sortListener());
				}

			}

			tblUndeliveredTopay.setVisible(true);
			tblUndeliveredTopayDet.setVisible(false);
			// cbUTBranch.setEnabled(false);

		}
		// Daily Booking Analysis
		{
			tiDailyBookingAnalysis = new TabItem(tabReport, SWT.NONE);
			tiDailyBookingAnalysis.setText(DAILY_BOOKING_TAB);

			cvsDailyBookingAnalysis = new Canvas(tabReport, SWT.NONE);
			tiDailyBookingAnalysis.setControl(cvsDailyBookingAnalysis);

			{

				{
					tblDailyBookingAnalysis = new Table(
							cvsDailyBookingAnalysis, SWT.FULL_SELECTION
									| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);

					tblDailyBookingAnalysis.setHeaderVisible(true);
					tblDailyBookingAnalysis.setLinesVisible(true);
					tblDailyBookingAnalysis.setBounds(10, 72, 800, 400);
					{
						colDBAsno = new TableColumn(tblDailyBookingAnalysis,
								SWT.NONE);
						colDBAsno.setText("S NO");
						colDBAsno.setWidth(40);

					}
					{
						colDBABranchCode = new TableColumn(
								tblDailyBookingAnalysis, SWT.NONE);
						colDBABranchCode.setText("Branch Code");
						colDBABranchCode.setWidth(90);

					}
					{
						colDBAStationCode = new TableColumn(
								tblDailyBookingAnalysis, SWT.NONE);
						colDBAStationCode.setText("Station Code");
						colDBAStationCode.setWidth(90);
					}
					{
						colDBADate1 = new TableColumn(tblDailyBookingAnalysis,
								SWT.NONE);
						colDBADate1.setText("Day1");
						colDBADate1.setWidth(90);

					}
					{
						colDBADate2 = new TableColumn(tblDailyBookingAnalysis,
								SWT.NONE);
						colDBADate2.setText("Day2");
						colDBADate2.setWidth(90);

					}
					{
						colDBADate3 = new TableColumn(tblDailyBookingAnalysis,
								SWT.NONE);
						colDBADate3.setText("Day3");
						colDBADate3.setWidth(90);

					}
					{
						colDBADate4 = new TableColumn(tblDailyBookingAnalysis,
								SWT.NONE);
						colDBADate4.setText("Day4");
						colDBADate4.setWidth(90);

					}
					{
						colDBADate5 = new TableColumn(tblDailyBookingAnalysis,
								SWT.NONE);
						colDBADate5.setText("Day5");
						colDBADate5.setWidth(90);

					}
					{
						colDBADate6 = new TableColumn(tblDailyBookingAnalysis,
								SWT.NONE);
						colDBADate6.setText("Day6");
						colDBADate6.setWidth(90);

					}
					{
						colDBADate7 = new TableColumn(tblDailyBookingAnalysis,
								SWT.NONE);
						colDBADate7.setText("Day7");
						colDBADate7.setWidth(90);

					}
					{
						colDBATotal = new TableColumn(tblDailyBookingAnalysis,
								SWT.NONE);
						colDBATotal.setText("Total");
						colDBATotal.setWidth(70);

					}

					for (int index = 0; index < tblDailyBookingAnalysis
							.getColumnCount(); index++) {
						tblDailyBookingAnalysis.getColumn(index).addListener(
								SWT.Selection, new sortListener());
					}

				}
				{
					label11 = new Label(cvsDailyBookingAnalysis, SWT.NONE);
					label11.setText("From Date");
					label11.setBounds(14, 33, 50, 18);
				}

				{
					txtDBAFrom = new Text(cvsDailyBookingAnalysis, SWT.BORDER);
					txtDBAFrom.setBounds(68, 32, 70, 22);
					txtDBAFrom.setEditable(false);
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					currentDate.setDate(currentDate.getDate() - 6);
					String date = dateFormat.format(currentDate);
					txtDBAFrom.setText(date);

				}

				{
					btnDBAFrom = new Button(cvsDailyBookingAnalysis, SWT.PUSH
							| SWT.CENTER);
					btnDBAFrom.setBounds(139, 30, 31, 23);
					btnDBAFrom.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btnDBAFrom.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent e) {
							KalendarDialog cd = new KalendarDialog(shell);
							Object obj = cd.open();
							if (obj != null) {
								setDBADates(obj.toString());
							}
						}
					});
				}

				{
					lblTo = new Label(cvsDailyBookingAnalysis, SWT.NONE);
					lblTo.setText("To Date");
					lblTo.setBounds(175, 33, 41, 17);
				}
				{
					txtDBATo = new Text(cvsDailyBookingAnalysis, SWT.BORDER);
					txtDBATo.setBounds(217, 32, 70, 22);
					txtDBATo.setEditable(false);
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					txtDBATo.setText(date);

				}

				setDBADates(txtDBAFrom.getText());

				{
					label12 = new Label(cvsDailyBookingAnalysis, SWT.NONE);
					label12.setText("Branch");
					label12.setBounds(298, 32, 36, 21);
				}
				{
					cbDBABranch = new Combo(cvsDailyBookingAnalysis,
							SWT.DROP_DOWN | SWT.READ_ONLY);
					cbDBABranch.setBounds(335, 32, 150, 23);
					cbDBABranch.add("All");
					cbDBABranch.addSelectionListener(this);
				}
				{
					label13 = new Label(cvsDailyBookingAnalysis, SWT.NONE);
					label13.setText("Report Type");
					label13.setBounds(501, 32, 60, 20);
				}
				{
					cbDBAReportType = new Combo(cvsDailyBookingAnalysis,
							SWT.READ_ONLY);
					cbDBAReportType.setBounds(564, 32, 85, 21);
					cbDBAReportType.add("Consolidated");
					cbDBAReportType.add("Detailed");
					cbDBAReportType.addSelectionListener(this);
				}
				{
					btnDBAGo = new Button(cvsDailyBookingAnalysis, SWT.PUSH
							| SWT.CENTER);
					btnDBAGo.setText("Go");
					btnDBAGo.setBounds(756, 31, 39, 24);
					btnDBAGo.addSelectionListener(this);
				}
				{
					cbDBAMOC = new Combo(cvsDailyBookingAnalysis, SWT.READ_ONLY);
					cbDBAMOC.setBounds(663, 32, 90, 21);
					cbDBAMOC.add(COUNT);
					cbDBAMOC.add(BASIC_FREIGHT);
					cbDBAMOC.add(TOTAL_FREIGHT);
					cbDBAMOC.add(ACTUAL_WEIGHT);
					cbDBAMOC.add(CHARGED_WEIGHT);
					cbDBAMOC.add(NOA);
				}

			}
		}

		{

			tiCounterReport = new TabItem(tabReport, SWT.NONE);
			tiCounterReport.setText(COUNTER_REPORT);

			cvsCounterReport = new Canvas(tabReport, SWT.NONE);
			tiCounterReport.setControl(cvsCounterReport);

			{

				{
					tblCounterReport = new Table(cvsCounterReport,
							SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL
									| SWT.BORDER);

					tblCounterReport.setHeaderVisible(true);
					tblCounterReport.setLinesVisible(true);
					tblCounterReport.setBounds(10, 100, 800, 360);
					{
						colCRsno = new TableColumn(tblCounterReport, SWT.NONE);
						colCRsno.setText("S NO");
						colCRsno.setWidth(40);

					}
					{
						colCRBranchCode = new TableColumn(tblCounterReport,
								SWT.NONE);
						colCRBranchCode.setText("Branch Code");
						colCRBranchCode.setWidth(80);

					}
					{
						colCRStationCode = new TableColumn(tblCounterReport,
								SWT.NONE);
						colCRStationCode.setText("Station Code");
						colCRStationCode.setWidth(100);
					}
					{
						colCRC1 = new TableColumn(tblCounterReport, SWT.NONE);
						colCRC1.setText("Total Lrs");
						colCRC1.setWidth(80);

					}
					{
						colCRC2 = new TableColumn(tblCounterReport, SWT.NONE);
						colCRC2.setText("Topay Lrs");
						colCRC2.setWidth(80);

					}
					{
						colCRC3 = new TableColumn(tblCounterReport, SWT.NONE);
						colCRC3.setText("Paid Lrs");
						colCRC3.setWidth(80);

					}
					{
						colCRC4 = new TableColumn(tblCounterReport, SWT.NONE);
						colCRC4.setText("Billing Lrs");
						colCRC4.setWidth(80);

					}
					{
						colCRC5 = new TableColumn(tblCounterReport, SWT.NONE);
						colCRC5.setText("Sundry Lrs");
						colCRC5.setWidth(90);

					}
					{
						colCRC6 = new TableColumn(tblCounterReport, SWT.NONE);
						colCRC6.setText("Quotation Lrs");
						colCRC6.setWidth(90);

					}
					{
						colCRC7 = new TableColumn(tblCounterReport, SWT.NONE);
						colCRC7.setText("FOC Lrs");
						colCRC7.setWidth(90);

					}
					{
						colCRC8 = new TableColumn(tblCounterReport, SWT.NONE);
						colCRC8.setText("Cancelled Lrs");
						colCRC8.setWidth(90);

					}

					for (int index = 0; index < tblCounterReport
							.getColumnCount(); index++) {
						tblCounterReport.getColumn(index).addListener(
								SWT.Selection, new sortListener());
					}

				}

				{
					label11 = new Label(cvsCounterReport, SWT.NONE);
					label11.setText("From Date");
					label11.setBounds(32, 37, 50, 18);
				}

				{
					txtCRFrom = new Text(cvsCounterReport, SWT.BORDER);
					txtCRFrom.setBounds(84, 34, 92, 22);
					txtCRFrom.setEditable(false);
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					txtCRFrom.setText(date);

				}
				{
					btnCRFromDate = new Button(cvsCounterReport, SWT.PUSH
							| SWT.CENTER);
					btnCRFromDate.setBounds(179, 34, 31, 23);
					btnCRFromDate.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btnCRFromDate.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent e) {
							KalendarDialog cd = new KalendarDialog(shell);
							Object obj = cd.open();
							if (obj != null) {
								String date = obj.toString();
								txtCRFrom.setText(date);
							}
						}
					});
				}
				{
					lblTo = new Label(cvsCounterReport, SWT.NONE);
					lblTo.setText("To Date");
					lblTo.setBounds(220, 35, 41, 17);
				}
				{
					txtCRTo = new Text(cvsCounterReport, SWT.BORDER);
					txtCRTo.setBounds(261, 35, 92, 22);
					txtCRTo.setEditable(false);
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					txtCRTo.setText(date);

				}
				{
					btnCRTo = new Button(cvsCounterReport, SWT.PUSH
							| SWT.CENTER);
					btnCRTo.setBounds(356, 35, 32, 23);
					btnCRTo.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btnCRTo.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent e) {
							KalendarDialog cd = new KalendarDialog(shell);
							Object obj = cd.open();
							if (obj != null) {
								String date = obj.toString();
								txtCRTo.setText(date);
							}
						}
					});

				}

				{
					label12 = new Label(cvsCounterReport, SWT.NONE);
					label12.setText("Select Branch");
					label12.setBounds(398, 34, 68, 20);
				}
				{
					cbCounterBranch = new Combo(cvsCounterReport, SWT.READ_ONLY);
					cbCounterBranch.setBounds(466, 32, 86, 21);
					cbCounterBranch.add("All");
					cbCounterBranch.addSelectionListener(this);
					
				}
				{
					label13 = new Label(cvsCounterReport, SWT.NONE);
					label13.setText("Report Type");
					label13.setBounds(560, 34, 60, 19);
				}
				{
					cbCRReportType = new Combo(cvsCounterReport, SWT.READ_ONLY);
					cbCRReportType.setBounds(627, 34, 97, 21);
					cbCRReportType.add("Consolidated");
					cbCRReportType.add("Detailed");
				}
				{
					lblCounterSet = new Label(cvsCounterReport, SWT.NONE);
					lblCounterSet.setText("Counter Set");
					lblCounterSet.setBounds(562, 72, 60, 20);
				}
				{
					cbCRCounterSet = new Combo(cvsCounterReport, SWT.READ_ONLY);
					cbCRCounterSet.setBounds(626, 72, 98, 21);
					cbCRCounterSet.add("LR Set");
					cbCRCounterSet.add("Misc");
					cbCRCounterSet.select(0);
					cbCRCounterSet.addSelectionListener(this);
				}
				{
					btnCounterGo = new Button(cvsCounterReport, SWT.PUSH
							| SWT.CENTER);
					btnCounterGo.setText("View");
					btnCounterGo.setBounds(736, 47, 41, 30);
					btnCounterGo.addSelectionListener(this);
				}

			}

		}

		{

			tiSundryBookingAnalysis = new TabItem(tabReport, SWT.NONE);
			tiSundryBookingAnalysis.setText(SUNDRY_BOOKING_TAB);
			{
				canSundryBookAnaysis = new Canvas(tabReport, SWT.BORDER);

				tiSundryBookingAnalysis.setControl(canSundryBookAnaysis);
				{
					tblSundryBookAnalysis = new Table(canSundryBookAnaysis,
							SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL
									| SWT.BORDER);
					tblSundryBookAnalysis.setHeaderVisible(true);
					tblSundryBookAnalysis.setLinesVisible(true);
					tblSundryBookAnalysis.setBounds(12, 70, 780, 400);
					{
						colSBASNo = new TableColumn(tblSundryBookAnalysis,
								SWT.NONE);
						colSBASNo.setText("S.No.");
						colSBASNo.setWidth(51);
					}
					{
						colBranchCode = new TableColumn(tblSundryBookAnalysis,
								SWT.NONE);
						colBranchCode.setText("Branch Code");
						colBranchCode.setWidth(100);
					}
					{
						colStationCode = new TableColumn(tblSundryBookAnalysis,
								SWT.NONE);
						colStationCode.setText("Station Code");
						colStationCode.setWidth(100);
					}
					{
						colTotalLr = new TableColumn(tblSundryBookAnalysis,
								SWT.NONE);
						colTotalLr.setText("Total Lrs");
						colTotalLr.setWidth(100);
					}
					{
						colTotalSundryLrs = new TableColumn(
								tblSundryBookAnalysis, SWT.NONE);
						colTotalSundryLrs.setText("Total Sundry Lrs");
						colTotalSundryLrs.setWidth(101);
					}

					{
						colTotalLrFreight = new TableColumn(
								tblSundryBookAnalysis, SWT.NONE);
						colTotalLrFreight.setText("Total Freight");
						colTotalLrFreight.setWidth(100);
					}
					{
						colTotalSundryFreight = new TableColumn(
								tblSundryBookAnalysis, SWT.NONE);
						colTotalSundryFreight.setText("Total Sundry Freight");
						colTotalSundryFreight.setWidth(100);
					}
					{
						colTotalSundryBft = new TableColumn(
								tblSundryBookAnalysis, SWT.NONE);
						colTotalSundryBft.setText("Total Sundry Basic Freight");
						colTotalSundryBft.setWidth(100);
					}
					{
						colTotalSundryNoa = new TableColumn(
								tblSundryBookAnalysis, SWT.NONE);
						colTotalSundryNoa
								.setText("Total Sundry No. of .Articles");
						colTotalSundryNoa.setWidth(100);
					}
					{
						colTotalSundryAwt = new TableColumn(
								tblSundryBookAnalysis, SWT.NONE);
						colTotalSundryAwt.setText("Total Sundry Actual Wt");
						colTotalSundryAwt.setWidth(100);
					}

					{
						colTotalSundryCrgWt = new TableColumn(
								tblSundryBookAnalysis, SWT.NONE);
						colTotalSundryCrgWt.setText("Total Sundry Crg Wt");
						colTotalSundryCrgWt.setWidth(100);
					}

					{
						colOnTurnOver = new TableColumn(tblSundryBookAnalysis,
								SWT.NONE);
						colOnTurnOver.setText("% of Sundry/Total");
						colOnTurnOver.setWidth(100);
					}

					for (int index = 0; index < tblSundryBookAnalysis
							.getColumnCount(); index++) {
						tblSundryBookAnalysis.getColumn(index).addListener(
								SWT.Selection, new sortListener());
					}

					{
						label11 = new Label(canSundryBookAnaysis, SWT.NONE);
						label11.setText("From Date");
						label11.setBounds(12, 35, 54, 18);
					}

					{
						txtSBAFrom = new Text(canSundryBookAnaysis, SWT.BORDER);
						txtSBAFrom.setBounds(68, 35, 68, 20);
						txtSBAFrom.setEditable(false);
						DateFormat dateFormat = new SimpleDateFormat(
								"dd-MM-yyyy");
						java.util.Date currentDate = new java.util.Date();
						currentDate.setDate(currentDate.getDate() - 6);
						String date = dateFormat.format(currentDate);
						txtSBAFrom.setText(date);

					}
					{
						btnSBAFrom = new Button(canSundryBookAnaysis, SWT.PUSH
								| SWT.CENTER);
						btnSBAFrom.setBounds(135, 36, 33, 20);
						btnSBAFrom.setImage(SWTResourceManager
								.getImage("hm/akr/resources/Calendar.jpg"));
						btnSBAFrom.addSelectionListener(new SelectionAdapter() {
							public void widgetSelected(SelectionEvent e) {
								KalendarDialog cd = new KalendarDialog(
										shell);
								Object obj = cd.open();
								if (obj != null) {
									String date = obj.toString();
									txtSBAFrom.setText(date);
								}
							}
						});
					}
					{
						lblTo = new Label(canSundryBookAnaysis, SWT.NONE);
						lblTo.setText("To Date");
						lblTo.setBounds(168, 36, 38, 18);
					}
					{
						txtSBATo = new Text(canSundryBookAnaysis, SWT.BORDER);
						txtSBATo.setBounds(207, 36, 68, 20);
						txtSBATo.setEditable(false);
						DateFormat dateFormat = new SimpleDateFormat(
								"dd-MM-yyyy");
						java.util.Date currentDate = new java.util.Date();
						String date = dateFormat.format(currentDate);
						txtSBATo.setText(date);

					}

					{
						btnSBATo = new Button(canSundryBookAnaysis, SWT.PUSH
								| SWT.CENTER);
						btnSBATo.setBounds(275, 36, 30, 21);
						btnSBATo.setImage(SWTResourceManager
								.getImage("hm/akr/resources/Calendar.jpg"));
						btnSBATo.addSelectionListener(new SelectionAdapter() {
							public void widgetSelected(SelectionEvent e) {
								KalendarDialog cd = new KalendarDialog(
										shell);
								Object obj = cd.open();
								if (obj != null) {
									String date = obj.toString();
									txtSBATo.setText(date);
								}
							}
						});

					}

					{
						label12 = new Label(canSundryBookAnaysis, SWT.NONE);
						label12.setText("Select Branch");
						label12.setBounds(307, 37, 68, 17);
					}
					{
						cbSBABranch = new Combo(canSundryBookAnaysis,
								SWT.DROP_DOWN | SWT.READ_ONLY);
						cbSBABranch.setBounds(375, 35, 85, 21);
						cbSBABranch.add("All");
						cbSBABranch.addSelectionListener(this);
					}

					{
						label12 = new Label(canSundryBookAnaysis, SWT.NONE);
						label12.setText("Select Station");
						label12.setBounds(466, 35, 65, 21);
					}
					{
						cbSBAStation = new Combo(canSundryBookAnaysis,
								SWT.DROP_DOWN | SWT.READ_ONLY);
						cbSBAStation.setBounds(536, 35, 83, 21);
						cbSBAStation.addSelectionListener(this);
					}

					/*
					 * { label13 = new Label(canSundryBookAnaysis, SWT.NONE);
					 * label13.setText("Report Type"); label13.setBounds(622,
					 * 35, 63, 19); } { cbSBAReportType = new
					 * Combo(canSundryBookAnaysis, SWT.READ_ONLY);
					 * cbSBAReportType.setBounds(685, 35, 73, 21);
					 * cbSBAReportType.add("Consolidated");
					 * cbSBAReportType.add("Detailed");
					 * cbSBAReportType.addSelectionListener(this); }
					 */
					{
						btnSBAGo = new Button(canSundryBookAnaysis, SWT.PUSH
								| SWT.CENTER);
						btnSBAGo.setText("Go");
						btnSBAGo.setBounds(630, 30, 36, 28);
						btnSBAGo.addSelectionListener(this);
					}

				}

			}

		}

		{		
			
			

			tiUPDReady = new TabItem(tabReport, SWT.NONE);
			tiUPDReady.setText(UPD_READY);
			{
				canUPDReady = new Canvas(tabReport, SWT.BORDER);
				tiUPDReady.setControl(canUPDReady);
				{
					tblUPDReady = new Table(canUPDReady, SWT.FULL_SELECTION
							| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
					tblUPDReady.setHeaderVisible(true);
					tblUPDReady.setLinesVisible(true);
					tblUPDReady.setBounds(10, 72, 800, 395);
					{
						colUpdSno = new TableColumn(tblUPDReady, SWT.NONE);
						colUpdSno.setText("S.No");
						colUpdSno.setWidth(46);
					}
					{
						colUPDRBranchCode = new TableColumn(tblUPDReady,
								SWT.NONE);
						colUPDRBranchCode.setText("Branch Code");
						colUPDRBranchCode.setWidth(80);
					}
					{
						colUPDRStationCode = new TableColumn(tblUPDReady,
								SWT.NONE);
						colUPDRStationCode.setText("Station Code");
						colUPDRStationCode.setWidth(80);
					}
					{
						colUPDRLrNo = new TableColumn(tblUPDReady, SWT.NONE);
						colUPDRLrNo.setText("Lr No");
						colUPDRLrNo.setWidth(60);
					}
					{
						colUPDRLrDate = new TableColumn(tblUPDReady, SWT.NONE);
						colUPDRLrDate.setText("LR Date");
						colUPDRLrDate.setWidth(83);
					}
					{
						colUPDRLrType = new TableColumn(tblUPDReady, SWT.NONE);
						colUPDRLrType.setText("LR Type");
						colUPDRLrType.setWidth(60);
					}
					{
						colUPDRTotalFreight = new TableColumn(tblUPDReady,
								SWT.NONE);
						colUPDRTotalFreight.setText("Total Freight");
						colUPDRTotalFreight.setWidth(80);
					}
					{
						colUPDRInwardDays = new TableColumn(tblUPDReady,
								SWT.NONE);
						colUPDRInwardDays.setText("Inward Days");
						colUPDRInwardDays.setWidth(80);
					}
					{
						colUPDRCnor = new TableColumn(tblUPDReady, SWT.NONE);
						colUPDRCnor.setText("Cnor");
						colUPDRCnor.setWidth(90);
					}
					{
						colUPDCnorPhone = new TableColumn(tblUPDReady, SWT.NONE);
						colUPDCnorPhone.setText("Cnor Phone");
						colUPDCnorPhone.setWidth(120);
					}
					{
						colUPDRCnee = new TableColumn(tblUPDReady, SWT.NONE);
						colUPDRCnee.setText("Cnee");
						colUPDRCnee.setWidth(90);
					}
					{
						colUPDCneePhone = new TableColumn(tblUPDReady, SWT.NONE);
						colUPDCneePhone.setText("Cnee Phone");
						colUPDCneePhone.setWidth(120);
					}

					for (int index = 0; index < tblUPDReady.getColumnCount(); index++) {
						tblUPDReady.getColumn(index).addListener(SWT.Selection,
								new sortListener());
					}

				}
				{
					lblUPDRBranch = new Label(canUPDReady, SWT.NONE);
					lblUPDRBranch.setText("Select Branch");
					lblUPDRBranch.setBounds(24, 28, 68, 21);
				}
				{
					cbUPDRBranch = new Combo(canUPDReady, SWT.READ_ONLY);
					cbUPDRBranch.add("All");
					cbUPDRBranch.setBounds(98, 29, 88, 21);
					cbUPDRBranch.addSelectionListener(this);
				}
				{
					lblUPDRStation = new Label(canUPDReady, SWT.NONE);
					lblUPDRStation.setText("Select Station");
					lblUPDRStation.setBounds(192, 28, 69, 22);
				}
				{
					cbUPDRStation = new Combo(canUPDReady, SWT.READ_ONLY);
					cbUPDRStation.add("All");
					cbUPDRStation.setBounds(261, 29, 96, 21);
				}
				{
					cbUPDOption = new Combo(canUPDReady, SWT.READ_ONLY);
					cbUPDOption.setBounds(401, 29, 102, 21);
					cbUPDOption.add("More Than");
					cbUPDOption.add("Equal To");
				}
				{
					lblupdOption = new Label(canUPDReady, SWT.NONE);
					lblupdOption.setText("Option");
					lblupdOption.setBounds(364, 28, 33, 18);
				}
				{
					lblUPDRInwardDays = new Label(canUPDReady, SWT.NONE);
					lblUPDRInwardDays.setText("InwardDays");
					lblUPDRInwardDays.setBounds(511, 28, 60, 21);
				}
				{
					cbUPDRInwardDays = new Combo(canUPDReady, SWT.NONE);
					cbUPDRInwardDays.add("7");
					cbUPDRInwardDays.add("15");
					cbUPDRInwardDays.add("30");
					cbUPDRInwardDays.add("45");
					cbUPDRInwardDays.setBounds(573, 29, 60, 21);
				}

				{
					btnUPDGo = new Button(canUPDReady, SWT.PUSH);
					btnUPDGo.setBounds(636, 27, 43, 25);
					btnUPDGo.addSelectionListener(this);
					btnUPDGo.setText("Go");
				}

			}
		}

		{
			tiStates = new TabItem(tabReport, SWT.NONE);
			tiStates.setText(INTER_INTRA);

			canStates = new Canvas(tabReport, SWT.BORDER);
			tiStates.setControl(canStates);

			tblIIStates = new Table(canStates, SWT.FULL_SELECTION | SWT.H_SCROLL
					| SWT.V_SCROLL | SWT.BORDER);
			tblIIStates.setBounds(30, 75, 740, 200);
			tblIIStates.setHeaderVisible(true);
			tblIIStates.setLinesVisible(true);

			{
				colstatesSno = new TableColumn(tblIIStates, SWT.NONE);
				colstatesSno.setText("States");
				colstatesSno.setWidth(130);
			}
			{
				colStatesTN = new TableColumn(tblIIStates, SWT.NONE);
				colStatesTN.setText("Tamilnadu");
				colStatesTN.setWidth(100);
			}
			{
				colStatesPHY = new TableColumn(tblIIStates, SWT.NONE);
				colStatesPHY.setText("Pondicherry");
				colStatesPHY.setWidth(100);
			}
			{
				colStatesAP = new TableColumn(tblIIStates, SWT.NONE);
				colStatesAP.setText("AndhraPradesh");
				colStatesAP.setWidth(100);
			}
			{
				colStatesKarnataka = new TableColumn(tblIIStates, SWT.NONE);
				colStatesKarnataka.setText("Karnataka");
				colStatesKarnataka.setWidth(100);
			}

			{
				colStatesKerala = new TableColumn(tblIIStates, SWT.NONE);
				colStatesKerala.setText("Kerala");
				colStatesKerala.setWidth(100);
			}
			{
				colStatesTotal = new TableColumn(tblIIStates, SWT.NONE);
				colStatesTotal.setText("Total");
				colStatesTotal.setWidth(100);

			}
			
			for (int index = 0; index < tblIIStates.getColumnCount(); index++) {
				tblIIStates.getColumn(index).addListener(SWT.Selection,
						new sortListener());
			}
			/*
			 * { lblStatesMonth = new Label(canStates, SWT.NONE);
			 * lblStatesMonth.setText("Select Month");
			 * lblStatesMonth.setBounds(60, 23, 68, 21); } { txtStatesMonth =
			 * new Text(canStates, SWT.BORDER | SWT.READ_ONLY);
			 * txtStatesMonth.setBounds(128, 22, 52, 21); DateFormat dateFormat =
			 * new SimpleDateFormat("MM-yyyy"); java.util.Date currentDate = new
			 * java.util.Date(); String date = dateFormat.format(currentDate);
			 * .txtStatesMonth.setText(date); } { btnStatesMonth = new
			 * Button(canStates, SWT.PUSH | SWT.CENTER);
			 * btnStatesMonth.setImage(SWTResourceManager.getImage("hm/akr/resources/Calendar.jpg"));
			 * btnStatesMonth.setBounds(181, 21, 26, 23);
			 * btnStatesMonth.addSelectionListener(new SelectionListener() {
			 * 
			 * @Override public void widgetDefaultSelected(SelectionEvent arg0) { }
			 * 
			 * @Override public void widgetSelected(SelectionEvent event) {
			 * MonthDialog cd = new
			 * MonthDialog(Display.getCurrent().getActiveShell()); Object obj =
			 * cd.open(); if (obj != null) {
			 * txtStatesMonth.setText(obj.toString()); } }
			 * 
			 * }); }
			 */
											
			
			{
				lblFromDate = new Label(canStates, SWT.NONE);
				lblFromDate.setText("From Date");
				lblFromDate.setBounds(18, 20, 53, 17);
			}

			{
				txtIISFrom = new Text(canStates, SWT.BORDER);
				txtIISFrom.setBounds(73, 20, 92, 22);
				txtIISFrom.setEditable(false);
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date currentDate = new java.util.Date();
				String date = dateFormat.format(currentDate);
				txtIISFrom.setText(date);

			}
			{
				btnIISFromDate = new Button(canStates, SWT.PUSH | SWT.CENTER);
				btnIISFromDate.setBounds(165, 19, 31, 23);
				btnIISFromDate.setImage(SWTResourceManager
						.getImage("hm/akr/resources/Calendar.jpg"));
				btnIISFromDate.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						KalendarDialog cd = new KalendarDialog(shell);
						Object obj = cd.open();
						if (obj != null) {
							String date = obj.toString();
							txtIISFrom.setText(date);
						}
					}
				});
			}
			{
				lblTo = new Label(canStates, SWT.NONE);
				lblTo.setText("To Date");
				lblTo.setBounds(211, 20, 41, 23);
			}
			{
				txtIISTo = new Text(canStates, SWT.BORDER);
				txtIISTo.setBounds(253, 20, 92, 22);
				txtIISTo.setEditable(false);
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date currentDate = new java.util.Date();
				String date = dateFormat.format(currentDate);
				txtIISTo.setText(date);

			}
			{
				btnIISTo = new Button(canStates, SWT.PUSH | SWT.CENTER);
				btnIISTo.setBounds(345, 18, 32, 23);
				btnIISTo.setImage(SWTResourceManager
						.getImage("hm/akr/resources/Calendar.jpg"));
				btnIISTo.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						KalendarDialog cd = new KalendarDialog(shell);
						Object obj = cd.open();
						if (obj != null) {
							String date = obj.toString();
							txtIISTo.setText(date);
						}
					}
				});

			}

			{
				cbIISChoice = new Combo(canStates, SWT.READ_ONLY);
				cbIISChoice.setBounds(387, 21, 90, 21);
				//cbIISChoice.addSelectionListener(this);
				cbIISChoice.add(COUNT);
				cbIISChoice.add(BASIC_FREIGHT);
				cbIISChoice.add(TOTAL_FREIGHT);
				cbIISChoice.add(ACTUAL_WEIGHT);
				cbIISChoice.add(CHARGED_WEIGHT);
				cbIISChoice.add(NOA);
			}

			{
				btnIISGo = new Button(canStates, SWT.PUSH | SWT.CENTER);
				btnIISGo.setBounds(480, 19, 32, 24);
				btnIISGo.setText("Go");
				btnIISGo.addSelectionListener(this);

			}
			lblFrom = new Label(canStates, SWT.NONE);
				lblFrom.setText("FROM STATE");
				lblFrom.setBounds(167, 52, 79, 21);
			}
			{
				lblToState = new Label(canStates, SWT.VERTICAL);
				lblToState.setText("T");
				lblToState.setBounds(14, 77, 14, 14);
			}
			{
				label9 = new Label(canStates, SWT.VERTICAL);
				label9.setText("O");
				label9.setBounds(14, 93, 14, 14);
			}
			{
				label10 = new Label(canStates, SWT.VERTICAL);
				label10.setText("S");
				label10.setBounds(14, 115, 14, 14);
			}
			{
				label18 = new Label(canStates, SWT.VERTICAL);
				label18.setText("T");
				label18.setBounds(14, 130, 14, 14);
			}
			{
				label15 = new Label(canStates, SWT.VERTICAL);
				label15.setText("A");
				label15.setBounds(14, 145, 14, 14);
			}
			{
				label16 = new Label(canStates, SWT.VERTICAL);
				label16.setText("T");
				label16.setBounds(14, 160, 14, 14);
			}
			{
				label17 = new Label(canStates, SWT.VERTICAL);
				label17.setText("E");
				label17.setBounds(14, 175, 14, 14);
			}

		

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
							SWT.Selection, new sortListener());
				}

			}

			{
				lblMSpercent = new Label(canMissingCustomer, SWT.NONE);
				lblMSpercent.setText("Business Drop %");
				lblMSpercent.setBounds(177, 13, 86, 22);
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

			tiCancelledLRs = new TabItem(tabReport, SWT.NONE);
			tiCancelledLRs.setText(CANCELLEDLR);

			cvsCancelledLRs = new Canvas(tabReport, SWT.NONE);
			tiCancelledLRs.setControl(cvsCancelledLRs);

			{

				tblCancelledLR = getSummaryCancelledLRTable();

				{
					label3 = new Label(cvsCancelledLRs, SWT.NONE);
					label3.setText("Station");
					label3.setBounds(546, 58, 35, 19);
					// label3.setVisible(false);
					label3.setEnabled(false);
				}
				{
					lblCLRBranch = new Label(cvsCancelledLRs, SWT.NONE);
					lblCLRBranch.setText("Branch");
					lblCLRBranch.setBounds(546, 23, 35, 19);
				}
				{
					label4 = new Label(cvsCancelledLRs, SWT.NONE);
					label4.setText("Report Type");
					label4.setBounds(365, 22, 68, 17);
				}
				{
					label5 = new Label(cvsCancelledLRs, SWT.NONE);
					label5.setText("To  Date");
					label5.setBounds(190, 28, 41, 20);
				}
				{
					label6 = new Label(cvsCancelledLRs, SWT.NONE);
					label6.setText("From Date");
					label6.setBounds(11, 28, 56, 19);
				}
				{
					cbCLRBranch = new Combo(cvsCancelledLRs, SWT.NONE);
					cbCLRBranch.setBounds(590, 20, 123, 23);
					StationsDTO[] dto = handler.getAllBranches();
					if (null != dto) {
						cbCLRBranch.add("All");
						for (int i = 0; i < dto.length; i++) {
							cbCLRBranch.add(dto[i].getName() + " - "
									+ dto[i].getId());
						}
					}
					cbCLRBranch.addSelectionListener(this);
				}
				{
					cbStation = new Combo(cvsCancelledLRs, SWT.NONE);
					cbStation.setBounds(590, 53, 122, 23);
					cbStation.addSelectionListener(this);
					
					// cbStation.setVisible(false);
					cbStation.setEnabled(false);

				}
				{
					cbCLReportType = new Combo(cvsCancelledLRs, SWT.NONE);
					cbCLReportType.setBounds(434, 20, 94, 23);
					cbCLReportType.add("Summary");
					cbCLReportType.add("Detailed");
					cbCLReportType.select(0);

					cbCLReportType.addSelectionListener(this);
				}
				{
					btnCancelledLrGo = new Button(cvsCancelledLRs, SWT.NONE);
					btnCancelledLrGo.setText("View");
					btnCancelledLrGo.setBounds(734, 32, 41, 32);
					btnCancelledLrGo.addSelectionListener(this);
				}
				{
					txtCancelledLRfromdate = new Text(cvsCancelledLRs,
							SWT.BORDER);
					txtCancelledLRfromdate.setBounds(67, 27, 72, 24);
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					txtCancelledLRfromdate.setText(date);
					txtCancelledLRfromdate.setEditable(false);
				}
				{
					btncancelledlrfromdate = new Button(cvsCancelledLRs,
							SWT.PUSH);
					// btncancelledlrfromdate.setText("Go");
					btncancelledlrfromdate.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btncancelledlrfromdate.setBounds(142, 27, 26, 23);
					btncancelledlrfromdate.addSelectionListener(this);
				}
				{
					txtcancelledlrtodate = new Text(cvsCancelledLRs, SWT.BORDER);
					txtcancelledlrtodate.setBounds(239, 25, 71, 24);
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					txtcancelledlrtodate.setText(date);
					txtcancelledlrtodate.setEditable(false);
				}
				{
					btncancelledlrtodate = new Button(cvsCancelledLRs, SWT.PUSH);
					// btncancelledlrtodate.setText("Go");
					btncancelledlrtodate.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btncancelledlrtodate.setBounds(313, 25, 26, 23);
					btncancelledlrtodate.addSelectionListener(this);
				}				
			}

		}
		{

			tiCnorBookingAnalysis = new TabItem(tabReport, SWT.NONE);

			tiCnorBookingAnalysis.setText(CONSINORBA);
			cvsCnorBookingAnalysis = getCnorCanvas();
			tiCnorBookingAnalysis.setControl(cvsCnorBookingAnalysis);

		}
		{

			tiCneeBookingAnalysis = new TabItem(tabReport, SWT.NONE);

			tiCneeBookingAnalysis.setText(CONSINEEBA);
			cvsCneeBookingAnalysis = getCneeCanvas();
			tiCneeBookingAnalysis.setControl(cvsCneeBookingAnalysis);

		}
		{

			tiDRSAttendance = new TabItem(tabReport, SWT.NONE);
			tiDRSAttendance.setText(DRS);

			cvsDRSAttendance = new Canvas(tabReport, SWT.NONE);
			tiDRSAttendance.setControl(cvsDRSAttendance);

			{

				{
					label11 = new Label(cvsDRSAttendance, SWT.NONE);
					label11.setText("Select Month");
					label11.setBounds(38, 34, 64, 17);
				}
				{
					txtDRSMonth = new Text(cvsDRSAttendance, SWT.BORDER);
					txtDRSMonth.setBounds(108, 32, 57, 21);
					txtDRSMonth.setEditable(false);
					DateFormat dateFormat = new SimpleDateFormat("MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					txtDRSMonth.setText(date);
				}
				{
					btnDRSMonth = new Button(cvsDRSAttendance, SWT.PUSH
							| SWT.CENTER);
					btnDRSMonth.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btnDRSMonth.setBounds(169, 30, 26, 23);
					btnDRSMonth.addSelectionListener(this);

				}

				{
					label12 = new Label(cvsDRSAttendance, SWT.NONE);
					label12.setText("Select Branch");
					label12.setBounds(406, 34, 76, 20);
				}

				{
					label13 = new Label(cvsDRSAttendance, SWT.NONE);
					label13.setText("Report Type");
					label13.setBounds(215, 34, 70, 19);
				}
				{
					cbDRSAReportType = new Combo(cvsDRSAttendance, SWT.NONE);
					cbDRSAReportType.setBounds(289, 34, 98, 23);
					cbDRSAReportType.add("Summary");
					cbDRSAReportType.add("%Report");
					cbDRSAReportType.addSelectionListener(this);
					cbDRSAReportType.select(0);

				}
				{
					cbDRSAttendanceBranch = new Combo(cvsDRSAttendance,
							SWT.NONE);
					cbDRSAttendanceBranch.setBounds(482, 34, 100, 23);
					cbDRSAttendanceBranch.addSelectionListener(this);
					StationsDTO[] dto = handler.getAllBranches();
					if (null != dto) {
						cbDRSAttendanceBranch.add("All");
						for (int i = 0; i < dto.length; i++) {
							cbDRSAttendanceBranch.add(dto[i].getName() + " - "
									+ dto[i].getBranch_code());
						}
					}
				}
				{
					btnDRSAttendance = new Button(cvsDRSAttendance, SWT.PUSH
							| SWT.CENTER);
					btnDRSAttendance.setText("View");
					btnDRSAttendance.setBounds(601, 30, 50, 36);
					btnDRSAttendance.addSelectionListener(this);

				}

				{
					createDRSSummaryTable();
				}

			}

		}
		{

			tiOpenLr = new TabItem(tabReport, SWT.NONE);

			tiOpenLr.setText(OPENLR);

			canOpenLr = new Canvas(tabReport, SWT.NONE);
			tiOpenLr.setControl(canOpenLr);

			tblOpenLr = new Table(canOpenLr, SWT.BORDER);
			tblOpenLr.setHeaderVisible(true);
			tblOpenLr.setLinesVisible(true);
			tblOpenLr.setBounds(9, 43, 790, 431);
			{
				colOpensno = new TableColumn(tblOpenLr, SWT.NONE);
				colOpensno.setText("Sno");
				colOpensno.setWidth(35);
			}
			{
				colOpenLrBranchCode = new TableColumn(tblOpenLr, SWT.NONE);
				colOpenLrBranchCode.setText("Branch Code");
				colOpenLrBranchCode.setWidth(87);
			}
			{
				colOpenLrStationCode = new TableColumn(tblOpenLr, SWT.NONE);
				colOpenLrStationCode.setText("From");
				colOpenLrStationCode.setWidth(77);
			}
			{
				colOpenLrTo = new TableColumn(tblOpenLr, SWT.NONE);
				colOpenLrTo.setText("To");
				colOpenLrTo.setWidth(76);
			}

			{
				colOpenLrLrNo = new TableColumn(tblOpenLr, SWT.NONE);
				colOpenLrLrNo.setText("Lr No");
				colOpenLrLrNo.setWidth(67);
			}
			{
				colOpenLrLrDate = new TableColumn(tblOpenLr, SWT.NONE);
				colOpenLrLrDate.setText("Lr Date");
				colOpenLrLrDate.setWidth(78);
			}
			{
				colOpenLrLrType = new TableColumn(tblOpenLr, SWT.NONE);
				colOpenLrLrType.setText("Lr Type");
				colOpenLrLrType.setWidth(65);
			}
			{
				colOpenLrCardRate = new TableColumn(tblOpenLr, SWT.NONE);
				colOpenLrCardRate.setText("Card Rate");
				colOpenLrCardRate.setWidth(90);
			}
			{
				colOpenLrBasicFreight = new TableColumn(tblOpenLr, SWT.NONE);
				colOpenLrBasicFreight.setText("Basic Freight");
				colOpenLrBasicFreight.setWidth(60);
			}
			{
				colOpenLrCCCrg = new TableColumn(tblOpenLr, SWT.NONE);
				colOpenLrCCCrg.setText("CC Crg");
				colOpenLrCCCrg.setWidth(90);
			}
			{
				colOpenLrDDCrg = new TableColumn(tblOpenLr, SWT.NONE);
				colOpenLrDDCrg.setText("DD Crg");
				colOpenLrDDCrg.setWidth(90);
			}
			{
				colOpenLrOthers = new TableColumn(tblOpenLr, SWT.NONE);
				colOpenLrOthers.setText("Others");
				colOpenLrOthers.setWidth(90);
			}
			{
				colOpenLRTotal = new TableColumn(tblOpenLr, SWT.NONE);
				colOpenLRTotal.setText("Total");
				colOpenLRTotal.setWidth(65);
			}
			{
				colOpenLRDiscount = new TableColumn(tblOpenLr, SWT.NONE);
				colOpenLRDiscount.setText("Discount %");
				colOpenLRDiscount.setWidth(85);
			}
			{
				colOpenLRCrgWt = new TableColumn(tblOpenLr, SWT.NONE);
				colOpenLRCrgWt.setText("Crg Wt");
				colOpenLRCrgWt.setWidth(100);
			}
			{
				colOpenLRCnor = new TableColumn(tblOpenLr, SWT.NONE);
				colOpenLRCnor.setText("Cnor");
				colOpenLRCnor.setWidth(100);
			}
			{
				colOpenLRCnee = new TableColumn(tblOpenLr, SWT.NONE);
				colOpenLRCnee.setText("Cnee");
				colOpenLRCnee.setWidth(100);
			}

			for (int index = 0; index < tblOpenLr.getColumnCount(); index++) {
				tblOpenLr.getColumn(index).addListener(SWT.Selection,
						new sortListener());
			}
			{
				lblOpenLrFromDate = new Label(canOpenLr, SWT.NONE);
				lblOpenLrFromDate.setText("From");
				lblOpenLrFromDate.setBounds(18, 9, 28, 17);
			}
			{
				txtOpenLrFrom = new Text(canOpenLr, SWT.NONE | SWT.BORDER);
				txtOpenLrFrom.setBounds(51, 9, 79, 23);

				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date currentDate = new java.util.Date();
				String date = dateFormat.format(currentDate);
				txtOpenLrFrom.setText(date);
				txtOpenLrFrom.setEditable(false);
			}
			{
				btnOpenLrFrom = new Button(canOpenLr, SWT.PUSH);
				// btnOpenLrFrom.setText("Go");
				btnOpenLrFrom.setBounds(134, 8, 26, 23);
				btnOpenLrFrom.setImage(SWTResourceManager
						.getImage("hm/akr/resources/Calendar.jpg"));
				btnOpenLrFrom.addSelectionListener(this);
			}
			{
				lblOpenLrTo = new Label(canOpenLr, SWT.NONE);
				lblOpenLrTo.setText("To");
				lblOpenLrTo.setBounds(230, 10, 31, 19);
			}
			{
				txtOpenLrTo = new Text(canOpenLr, SWT.BORDER);
				txtOpenLrTo.setBounds(266, 9, 83, 23);

				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date currentDate = new java.util.Date();
				String date = dateFormat.format(currentDate);
				txtOpenLrTo.setText(date);
				txtOpenLrTo.setEditable(false);
			}
			{
				btnOpenLrTo = new Button(canOpenLr, SWT.PUSH);
				// btnOpenLrTo.setText("Go");
				btnOpenLrTo.setBounds(354, 8, 26, 23);
				btnOpenLrTo.setImage(SWTResourceManager
						.getImage("hm/akr/resources/Calendar.jpg"));
				btnOpenLrTo.addSelectionListener(this);
			}
			{
				lblOpenLrBranch = new Label(canOpenLr, SWT.NONE);
				lblOpenLrBranch.setText("Select Branch");
				lblOpenLrBranch.setBounds(488, 9, 77, 25);
			}
			{
				cbOpenLrBranch = new Combo(canOpenLr, SWT.NONE);
				cbOpenLrBranch.setBounds(572, 9, 117, 21);
				StationsDTO[] dto = handler.getAllBranches();
				if (null != dto) {
					cbOpenLrBranch.add("All");
					for (int i = 0; i < dto.length; i++) {
						cbOpenLrBranch.add(dto[i].getName() + " - "
								+ dto[i].getId());
					}
				}
				cbOpenLrBranch.addSelectionListener(this);
			}
			{
				btnOpenView = new Button(canOpenLr, SWT.PUSH | SWT.CENTER);
				btnOpenView.setText("View");
				btnOpenView.setBounds(699, 7, 48, 26);
				btnOpenView.addSelectionListener(this);
			}
			
		}
		{

			tiFOCLr = new TabItem(tabReport, SWT.NONE);
			tiFOCLr.setText(FOC);

			canFOCLr = new Canvas(tabReport, SWT.NONE);
			tiFOCLr.setControl(canFOCLr);

			tblFOCLr = new Table(canFOCLr, SWT.BORDER);
			tblFOCLr.setHeaderVisible(true);
			tblFOCLr.setLinesVisible(true);
			tblFOCLr.setBounds(16, 43, 781, 419);
			{
				colFOCSno = new TableColumn(tblFOCLr, SWT.NONE);
				colFOCSno.setText("Sno");
				colFOCSno.setWidth(35);
			}

			{
				colFOCLrLrNo = new TableColumn(tblFOCLr, SWT.NONE);
				colFOCLrLrNo.setText("Lr No");
				colFOCLrLrNo.setWidth(95);
			}
			{
				colFOCLrLrDate = new TableColumn(tblFOCLr, SWT.NONE);
				colFOCLrLrDate.setText("Lr Date");
				colFOCLrLrDate.setWidth(101);
			}
			{
				colFOCLrLrFrom = new TableColumn(tblFOCLr, SWT.NONE);
				colFOCLrLrFrom.setText("From");
				colFOCLrLrFrom.setWidth(90);
			}
			{
				colFOCLrTo = new TableColumn(tblFOCLr, SWT.NONE);
				colFOCLrTo.setText("To");
				colFOCLrTo.setWidth(90);
			}
			{
				colFOCLrNoa = new TableColumn(tblFOCLr, SWT.NONE);
				colFOCLrNoa.setText("No. of Articles");
				colFOCLrNoa.setWidth(81);
			}
			{
				colFOCLrCrgWt = new TableColumn(tblFOCLr, SWT.NONE);
				colFOCLrCrgWt.setText("Crg Wt");
				colFOCLrCrgWt.setWidth(88);
			}
			{
				colFOCLrArticleType = new TableColumn(tblFOCLr, SWT.NONE);
				colFOCLrArticleType.setText("Article Type");
				colFOCLrArticleType.setWidth(97);
			}
			{
				colFOCLrArticleValue = new TableColumn(tblFOCLr, SWT.NONE);
				colFOCLrArticleValue.setText("Article Value");
				colFOCLrArticleValue.setWidth(84);
			}
			for (int index = 0; index < tblFOCLr.getColumnCount(); index++) {
				tblFOCLr.getColumn(index).addListener(SWT.Selection,
						new sortListener());
			}

			{
				lblFOCLrFromDate = new Label(canFOCLr, SWT.NONE);
				lblFOCLrFromDate.setText("From");
				lblFOCLrFromDate.setBounds(13, 8, 41, 26);
			}
			{
				txtFOCLrFrom = new Text(canFOCLr, SWT.BORDER);
				txtFOCLrFrom.setBounds(57, 7, 79, 23);

				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date currentDate = new java.util.Date();
				String date = dateFormat.format(currentDate);
				txtFOCLrFrom.setText(date);
				txtFOCLrFrom.setEditable(false);
			}
			{
				btnFOCLrFrom = new Button(canFOCLr, SWT.PUSH);
				// btnFOCLrFrom.setText("Go");
				btnFOCLrFrom.setBounds(140, 6, 26, 23);
				btnFOCLrFrom.setImage(SWTResourceManager
						.getImage("hm/akr/resources/Calendar.jpg"));
				btnFOCLrFrom.addSelectionListener(this);
			}
			{
				lblFOCLrTo = new Label(canFOCLr, SWT.NONE);
				lblFOCLrTo.setText("To");
				lblFOCLrTo.setBounds(201, 8, 26, 23);
			}
			{
				txtFOCLrTo = new Text(canFOCLr, SWT.BORDER);
				txtFOCLrTo.setBounds(231, 7, 79, 23);

				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date currentDate = new java.util.Date();
				String date = dateFormat.format(currentDate);
				txtFOCLrTo.setText(date);
				txtFOCLrTo.setEditable(false);
			}
			{
				btnFOCLrTo = new Button(canFOCLr, SWT.PUSH);
				// btnFOCLrTo.setText("Go");
				btnFOCLrTo.setBounds(315, 6, 26, 23);
				btnFOCLrTo.setImage(SWTResourceManager
						.getImage("hm/akr/resources/Calendar.jpg"));
				btnFOCLrTo.addSelectionListener(this);
			}
			{
				lblFOCLrBranch = new Label(canFOCLr, SWT.NONE);
				lblFOCLrBranch.setText("Select Branch");
				lblFOCLrBranch.setBounds(376, 9, 74, 25);
			}
			{
				cbFOCLrBranch = new Combo(canFOCLr, SWT.NONE);
				cbFOCLrBranch.setBounds(456, 8, 91, 23);
				StationsDTO[] dto = handler.getAllBranches();
				if (null != dto) {
					cbFOCLrBranch.add("All");
					for (int i = 0; i < dto.length; i++) {
						cbFOCLrBranch.add(dto[i].getName() + " - "
								+ dto[i].getId());
					}
				}
				cbFOCLrBranch.addSelectionListener(this);
			}
			{
				lblFOCLrReportType = new Label(canFOCLr, SWT.NONE);
				lblFOCLrReportType.setText("Report Type");
				lblFOCLrReportType.setBounds(568, 8, 67, 21);
			}
			{
				cbFOCLrReportType = new Combo(canFOCLr, SWT.NONE);
				cbFOCLrReportType.setBounds(637, 8, 86, 23);
				cbFOCLrReportType.add("Detailed");
				cbFOCLrReportType.add("Summary");
				cbFOCLrReportType.select(0);
				cbFOCLrReportType.addSelectionListener(this);
			}
			{
				btnFocLrGo = new Button(canFOCLr, SWT.PUSH | SWT.CENTER);
				btnFocLrGo.setText("Go");
				btnFocLrGo.setBounds(729, 6, 44, 26);
				btnFocLrGo.addSelectionListener(this);
			}
			
		}
		{

			tiMarketVehiUtilisation = new TabItem(tabReport, SWT.NONE);
			tiMarketVehiUtilisation.setText(MARKET_VEHICLE);
			{
				canMarkVehiUtil = new Canvas(tabReport, SWT.NONE);
				tiMarketVehiUtilisation.setControl(canMarkVehiUtil);
				{
					tblMarketVehicleUtilisation = new Table(canMarkVehiUtil,
							SWT.BORDER);
					tblMarketVehicleUtilisation.setHeaderVisible(true);
					tblMarketVehicleUtilisation.setLinesVisible(true);
					tblMarketVehicleUtilisation.setBounds(10, 37, 750, 400);

				}
				{
					lblMarkVehiUtilSelectMonth = new Label(canMarkVehiUtil,
							SWT.NONE);
					lblMarkVehiUtilSelectMonth.setText("Select Month");
					lblMarkVehiUtilSelectMonth.setBounds(15, 8, 78, 20);
				}

				{
					txtMarkVehiUtilSelectMonth = new Text(canMarkVehiUtil,
							SWT.BORDER);
					txtMarkVehiUtilSelectMonth.setBounds(96, 7, 58, 23);
					txtMarkVehiUtilSelectMonth.setEditable(false);
					DateFormat dateFormat = new SimpleDateFormat("MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					txtMarkVehiUtilSelectMonth.setText(date);
				}
				{
					btnMarkVehiUtilSelectMonth = new Button(canMarkVehiUtil,
							SWT.PUSH);
					btnMarkVehiUtilSelectMonth.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					// btnMarkVehiUtilSelectMonth.setText("Go");
					btnMarkVehiUtilSelectMonth.setBounds(158, 6, 26, 23);
					btnMarkVehiUtilSelectMonth.addSelectionListener(this);
				}

				{
					lblMarkVehiUtilSelectBranch = new Label(canMarkVehiUtil,
							SWT.NONE);
					lblMarkVehiUtilSelectBranch.setText("Select Branch");
					lblMarkVehiUtilSelectBranch.setBounds(250, 9, 74, 18);
				}
				{
					cbMarkVehiUtilSelectBranch = new Combo(canMarkVehiUtil,
							SWT.NONE);
					cbMarkVehiUtilSelectBranch.setBounds(330, 9, 117, 23);
					StationsDTO[] dto = handler.getAllBranches();
					if (null != dto) {
						cbMarkVehiUtilSelectBranch.add("All");
						for (int i = 0; i < dto.length; i++) {
							cbMarkVehiUtilSelectBranch.add(dto[i].getName()
									+ " - " + dto[i].getId());
						}
					}
					cbMarkVehiUtilSelectBranch.addSelectionListener(this);
				}
				{
					btnMargetVehicleGo = new Button(canMarkVehiUtil, SWT.PUSH
							| SWT.CENTER);
					btnMargetVehicleGo.setText("Go");
					btnMargetVehicleGo.setBounds(454, 5, 37, 26);
					btnMargetVehicleGo.addSelectionListener(this);
				}
			}

		}
		{

			tiInwardAnalysis = new TabItem(tabReport, SWT.NONE);
			tiInwardAnalysis.setText(LOG);

			cvsInwardAnalysis = new Canvas(tabReport, SWT.NONE);
			tiInwardAnalysis.setControl(cvsInwardAnalysis);
			{
				tblInwardAnalysis = new Table(cvsInwardAnalysis,
						SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL
								| SWT.BORDER);
				tblInwardAnalysis.setBounds(5, 90, 750, 380);

				tblInwardAnalysis.setHeaderVisible(true);
				tblInwardAnalysis.setLinesVisible(true);
			}

			{
				lblInwardStation = new Label(cvsInwardAnalysis, SWT.NONE);
				lblInwardStation.setText("To Station");
				lblInwardStation.setBounds(335, 19, 75, 21);
			}
			{
				label2 = new Label(cvsInwardAnalysis, SWT.NONE);
				label2.setText("From Station");
				label2.setBounds(468, 20, 76, 22);
			}
			{
				label8 = new Label(cvsInwardAnalysis, SWT.NONE);
				label8.setText("From");
				label8.setBounds(8, 56, 33, 16);
			}
			/*{
				cbLOGFromHour = new Combo(cvsInwardAnalysis, SWT.NONE);
				cbLOGFromHour.setBounds(152, 36, 41, 23);
				for (int h = 1; h < 12; h++) {
					cbLOGFromHour.add(String.valueOf(h));
				}
				cbLOGFromHour.select(0);
			}
			{
				cbLOGFromMin = new Combo(cvsInwardAnalysis, SWT.NONE);
				cbLOGFromMin.setBounds(199, 36, 42, 23);
				for (int m = 1; m < 60; m++) {
					cbLOGFromMin.add(String.valueOf(m));
				}
				cbLOGFromMin.select(0);
			}*/
			{
				txtLOGFrom = new Text(cvsInwardAnalysis, SWT.BORDER);
				txtLOGFrom.setBounds(45, 49, 71, 22);
				txtLOGFrom.setEnabled(false);
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date currentDate = new java.util.Date();
				String date = dateFormat.format(currentDate);
				txtLOGFrom.setText(date);

			}
			/*{
				cbLOGTime = new Combo(cvsInwardAnalysis, SWT.NONE);
				cbLOGTime.setBounds(245, 37, 50, 23);
				cbLOGTime.add("AM");
				cbLOGTime.add("PM");
				cbLOGTime.select(0);
			}*/
			/*{
				lblTime = new Label(cvsInwardAnalysis, SWT.NONE);
				lblTime.setText("Time");
				lblTime.setBounds(158, 15, 113, 15);
			}*/
			{
				btnLOGFrom = new Button(cvsInwardAnalysis, SWT.PUSH
						| SWT.CENTER);
				btnLOGFrom.setImage(SWTResourceManager
						.getImage("hm/akr/resources/Calendar.jpg"));
				btnLOGFrom.setBounds(116, 48, 26, 23);
				btnLOGFrom.addSelectionListener(this);
			}

			{
				lblLOGToDate = new Label(cvsInwardAnalysis, SWT.NONE);
				lblLOGToDate.setText("To");
				lblLOGToDate.setBounds(167, 56, 25, 20);
			}
			{
				txtLOGToDate = new Text(cvsInwardAnalysis, SWT.BORDER);
				txtLOGToDate.setBounds(197, 49, 68, 24);
				txtLOGToDate.setEnabled(false);
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date currentDate = new java.util.Date();
				String date = dateFormat.format(currentDate);
				txtLOGToDate.setText(date);
			}
			{
				btnLOGTo = new Button(cvsInwardAnalysis, SWT.PUSH | SWT.CENTER);
				btnLOGTo.setImage(SWTResourceManager
						.getImage("hm/akr/resources/Calendar.jpg"));
				btnLOGTo.setBounds(265, 48, 26, 23);
				btnLOGTo.addSelectionListener(this);
			}
			{
				cbInwardStation = new Combo(cvsInwardAnalysis, SWT.READ_ONLY);
				cbInwardStation.setBounds(333, 50, 100, 23);
				cbInwardStation.add("All");
			}
			{
				cbFromStation = new Combo(cvsInwardAnalysis, SWT.READ_ONLY);
				cbFromStation.setBounds(464, 51, 92, 23);
				cbFromStation.add("All");
			}

			{
				cbOption = new Combo(cvsInwardAnalysis, SWT.READ_ONLY);
				cbOption.setBounds(586, 51, 110, 23);
				cbOption.add(COUNT);
				cbOption.add(BASIC_FREIGHT);
				cbOption.add(TOTAL_FREIGHT);
				cbOption.add(ACTUAL_WEIGHT);
				cbOption.add(CHARGED_WEIGHT);
				cbOption.add(NOA);
				// cbOption.select(0);

			}

			{
				btnGo = new Button(cvsInwardAnalysis, SWT.PUSH | SWT.CENTER);
				btnGo.setText("Go");
				btnGo.setBounds(701, 50, 36, 23);
				btnGo.addSelectionListener(this);
			}

			populateComboStations();

		}
		
		
		{
			tiServicetax = new TabItem(tabReport, SWT.NONE);
			tiServicetax.setText(Service_Tax);

			cvsServicetax = new Canvas(tabReport, SWT.NONE);
			tiServicetax.setControl(cvsServicetax);

			{
				{
					tblServicetax = new Table(cvsServicetax, SWT.FULL_SELECTION
							| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);

					tblServicetax.setHeaderVisible(true);
					tblServicetax.setLinesVisible(true);
					tblServicetax.setBounds(30, 72, 630, 400);
					{
						colSertaxSno = new TableColumn(tblServicetax, SWT.NONE);
						colSertaxSno.setText("S NO");
						colSertaxSno.setWidth(49);
						colSertaxSno
								.addListener(SWT.Selection, new sortListener());

					}
					{
						colSertaxStname = new TableColumn(tblServicetax, SWT.NONE);
						colSertaxStname.setText("Station Name");
						colSertaxStname.setWidth(160);
						colSertaxStname.addListener(SWT.Selection,
								new sortListener());
					}
					{
						colSertaxTft = new TableColumn(tblServicetax, SWT.NONE);
						colSertaxTft.setText("Total before Tax");
						colSertaxTft.setWidth(100);
						colSertaxTft.addListener(SWT.Selection,
								new sortListener());

					}
					
					/*{
						colSertaxTftof25 = new TableColumn(tblServicetax, SWT.NONE);
						colSertaxTftof25.setText("25% of Total Freight");
						colSertaxTftof25.setWidth(90);
						colSertaxTftof25.addListener(SWT.Selection,
								new sortListener());

					}*/
					{
						colSertaxService = new TableColumn(tblServicetax, SWT.NONE);
						colSertaxService.setText("Service Tax");
						colSertaxService.setWidth(100);
						colSertaxService
								.addListener(SWT.Selection, new sortListener());

					}
					/*{
						colSertaxEdu = new TableColumn(tblServicetax, SWT.NONE);
						colSertaxEdu.setText("Edu.Cess");
						colSertaxEdu.setWidth(80);
						colSertaxEdu.addListener(SWT.Selection, new sortListener());

					}
					{
						colSertaxHrsec = new TableColumn(tblServicetax, SWT.NONE);
						colSertaxHrsec.setText("Hr.Sec.Edu.Cess");
						colSertaxHrsec.setWidth(80);
						colSertaxHrsec
								.addListener(SWT.Selection, new sortListener());

					}*/
					{
						colSertaxTotalst = new TableColumn(tblServicetax, SWT.NONE);
						colSertaxTotalst.setText("Total after Tax");
						colSertaxTotalst.setWidth(100);
						colSertaxTotalst.addListener(SWT.Selection,
								new sortListener());

					}

		
				}

				{
					lblStFromDate = new Label(cvsServicetax, SWT.NONE);
					lblStFromDate.setText("From Date");
					lblStFromDate.setBounds(35, 28, 54, 20);
				}
				{
					lblStToDate = new Label(cvsServicetax, SWT.NONE);
					lblStToDate.setText("To Date");
					lblStToDate.setBounds(220, 28, 40, 16);
				}

				{
					txtStFrom = new Text(cvsServicetax, SWT.BORDER);
					txtStFrom.setBounds(100, 26, 70, 22);
					txtStFrom.setEditable(false);
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					txtStFrom.setText(date);

				}
				{
					btnStFromDate = new Button(cvsServicetax, SWT.PUSH
							| SWT.CENTER);
					btnStFromDate.setBounds(170, 25, 31, 23);
					btnStFromDate.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btnStFromDate.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent e) {
							KalendarDialog cd = new KalendarDialog(shell, 1);
							Object obj = cd.open();
							if (obj != null) {
								String date = obj.toString();
								txtStFrom.setText(date);
							}
						}
					});
				}

				{
					txtStTo = new Text(cvsServicetax, SWT.BORDER);
					txtStTo.setBounds(275, 25, 70, 22);
					txtStTo.setEditable(false);
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					txtStTo.setText(date);

				}
				{
					btnStTo = new Button(cvsServicetax, SWT.PUSH | SWT.CENTER);
					btnStTo.setBounds(345, 24, 32, 23);
					btnStTo.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btnStTo.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent e) {
							KalendarDialog cd = new KalendarDialog(shell);
							Object obj = cd.open();
							if (obj != null) {
								String date = obj.toString();
								txtStTo.setText(date);
							}
						}
					});
				}
				
				{
					lblStReptype = new Label(cvsServicetax, SWT.NONE);
					lblStReptype.setText("Report Type");
					lblStReptype.setBounds(410, 28, 67, 21);
				}
				{
					cbStReptype = new Combo(cvsServicetax, SWT.NONE);
					cbStReptype.setBounds(480, 28, 86, 23);
					cbStReptype.add("Consolidated");
					cbStReptype.add("Detailed");
					cbStReptype.select(0);
					cbStReptype.addSelectionListener(this);
				}
								

				{
					btnStGo = new Button(cvsServicetax, SWT.PUSH | SWT.CENTER);
					btnStGo.setText("Go");
					btnStGo.setBounds(600, 27, 37, 23);
					btnStGo.addSelectionListener(this);
				}
			}
		}
		
		
		{
			tiServicetaxLR = new TabItem(tabReport, SWT.NONE);
			tiServicetaxLR.setText(Service_Tax_LR);

			cvsServicetaxLR = new Canvas(tabReport, SWT.NONE);
			tiServicetaxLR.setControl(cvsServicetaxLR);

			{
				{
					tblServicetaxLR = new Table(cvsServicetaxLR, SWT.FULL_SELECTION
							| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);

					tblServicetaxLR.setHeaderVisible(true);
					tblServicetaxLR.setLinesVisible(true);
					tblServicetaxLR.setBounds(10, 72, 765, 400);
					{
						colSertaxLRSno = new TableColumn(tblServicetaxLR, SWT.NONE);
						colSertaxLRSno.setText("S NO");
						colSertaxLRSno.setWidth(49);
						colSertaxLRSno
								.addListener(SWT.Selection, new sortListener());

					}
					{
						colSertaxLRLrno = new TableColumn(tblServicetaxLR, SWT.NONE);
						colSertaxLRLrno.setText("LR No");
						colSertaxLRLrno.setWidth(90);
						colSertaxLRLrno.addListener(SWT.Selection,
								new sortListener());
					}
					{
						colSertaxLRLrdate = new TableColumn(tblServicetaxLR, SWT.NONE);
						colSertaxLRLrdate.setText("LR Date");
						colSertaxLRLrdate.setWidth(90);
						colSertaxLRLrdate.addListener(SWT.Selection,
								new sortListener());
					}
					{
						colSertaxLRFrom = new TableColumn(tblServicetaxLR, SWT.NONE);
						colSertaxLRFrom.setText("From");
						colSertaxLRFrom.setWidth(90);
						colSertaxLRFrom.addListener(SWT.Selection,
								new sortListener());
					}
					{
						colSertaxLRTo = new TableColumn(tblServicetaxLR, SWT.NONE);
						colSertaxLRTo.setText("To");
						colSertaxLRTo.setWidth(90);
						colSertaxLRTo.addListener(SWT.Selection,
								new sortListener());
					}
					{
						colSertaxLRCnor= new TableColumn(tblServicetaxLR, SWT.NONE);
						colSertaxLRCnor.setText("Cnor");
						colSertaxLRCnor.setWidth(90);
						colSertaxLRCnor.addListener(SWT.Selection,
								new sortListener());
					}
					{
						colSertaxLRCnee = new TableColumn(tblServicetaxLR, SWT.NONE);
						colSertaxLRCnee.setText("Cnee");
						colSertaxLRCnee.setWidth(90);
						colSertaxLRCnee.addListener(SWT.Selection,
								new sortListener());
					}
					{
						colSertaxLRTft = new TableColumn(tblServicetaxLR, SWT.NONE);
						colSertaxLRTft.setText("Total before Tax");
						colSertaxLRTft.setWidth(90);
						colSertaxLRTft.addListener(SWT.Selection,
								new sortListener());

					}
					
					/*{
						colSertaxLRTftof25 = new TableColumn(tblServicetaxLR, SWT.NONE);
						colSertaxLRTftof25.setText("25% of Total Freight");
						colSertaxLRTftof25.setWidth(90);
						colSertaxLRTftof25.addListener(SWT.Selection,
								new sortListener());

					}*/
					{
						colSertaxLRService = new TableColumn(tblServicetaxLR, SWT.NONE);
						colSertaxLRService.setText("Service Tax");
						colSertaxLRService.setWidth(90);
						colSertaxLRService
								.addListener(SWT.Selection, new sortListener());

					}
					/*{
						colSertaxLREdu = new TableColumn(tblServicetaxLR, SWT.NONE);
						colSertaxLREdu.setText("Edu.Cess");
						colSertaxLREdu.setWidth(80);
						colSertaxLREdu.addListener(SWT.Selection, new sortListener());

					}
					{
						colSertaxLRHrsec = new TableColumn(tblServicetaxLR, SWT.NONE);
						colSertaxLRHrsec.setText("Hr.Sec.Edu.Cess");
						colSertaxLRHrsec.setWidth(80);
						colSertaxLRHrsec
								.addListener(SWT.Selection, new sortListener());

					}*/
					{
						colSertaxLRTotalst = new TableColumn(tblServicetaxLR, SWT.NONE);
						colSertaxLRTotalst.setText("Total after Tax");
						colSertaxLRTotalst.setWidth(80);
						colSertaxLRTotalst.addListener(SWT.Selection,
								new sortListener());

					}

		
				}

				{
					lblStLRFromDate = new Label(cvsServicetaxLR, SWT.NONE);
					lblStLRFromDate.setText("From Date");
					lblStLRFromDate.setBounds(15, 25, 54, 20);
				}
				{
					lblStLRToDate = new Label(cvsServicetaxLR, SWT.NONE);
					lblStLRToDate.setText("To Date");
					lblStLRToDate.setBounds(185, 25, 40, 16);
				}

				{
					txtStLRFrom = new Text(cvsServicetaxLR, SWT.BORDER);
					txtStLRFrom.setBounds(80, 25, 70, 22);
					txtStLRFrom.setEditable(false);
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					txtStLRFrom.setText(date);

				}
				{
					btnStLRFromDate = new Button(cvsServicetaxLR, SWT.PUSH
							| SWT.CENTER);
					btnStLRFromDate.setBounds(150, 25, 31, 23);
					btnStLRFromDate.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btnStLRFromDate.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent e) {
							KalendarDialog cd = new KalendarDialog(shell, 1);
							Object obj = cd.open();
							if (obj != null) {
								String date = obj.toString();
								txtStLRFrom.setText(date);
							}
						}
					});
				}

				{
					txtStLRTo = new Text(cvsServicetaxLR, SWT.BORDER);
					txtStLRTo.setBounds(240, 25, 70, 22);
					txtStLRTo.setEditable(false);
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					txtStLRTo.setText(date);

				}
				{
					btnStLRTo = new Button(cvsServicetaxLR, SWT.PUSH | SWT.CENTER);
					btnStLRTo.setBounds(310, 25, 32, 23);
					btnStLRTo.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btnStLRTo.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent e) {
							KalendarDialog cd = new KalendarDialog(shell);
							Object obj = cd.open();
							if (obj != null) {
								String date = obj.toString();
								txtStLRTo.setText(date);
							}
						}
					});
				}
				
				{
					lblStLRBranch = new Label(cvsServicetaxLR, SWT.NONE);
					lblStLRBranch.setText("Select Branch");
					lblStLRBranch.setBounds(345, 25, 70, 17);
				}
				{
					cbStLRBranch = new Combo(cvsServicetaxLR,
							SWT.DROP_DOWN | SWT.READ_ONLY);
					cbStLRBranch.setBounds(420, 27, 85, 21);
					cbStLRBranch.add("All");
					cbStLRBranch.addSelectionListener(this);
				}

				{
					lblStLRStation = new Label(cvsServicetaxLR, SWT.NONE);
					lblStLRStation.setText("Select Station");
					lblStLRStation.setBounds(520, 27, 70, 21);
				}
				{
					cbStLRStation = new Combo(cvsServicetaxLR,
							SWT.DROP_DOWN | SWT.READ_ONLY);
					cbStLRStation.setBounds(600, 27, 85, 21);
					cbStLRStation.add("All");
					cbStLRStation.addSelectionListener(this);
				}

				{
					btnStLRGo = new Button(cvsServicetaxLR, SWT.PUSH | SWT.CENTER);
					btnStLRGo.setText("Go");
					btnStLRGo.setBounds(720, 27, 37, 23);
					btnStLRGo.addSelectionListener(this);
				}
			}
		
		}
		
		btnPrint = new Button(this, SWT.PUSH | SWT.CENTER);
		btnPrint.setText("Print");
		btnPrint.setBounds(529, 530, 60, 23);
		btnPrint.addSelectionListener(this);

		btnExportXL = new Button(this, SWT.PUSH | SWT.CENTER);
		btnExportXL.setText("Export Excel");
		btnExportXL.setBounds(600, 530, 80, 23);		
		btnExportXL.addSelectionListener(this);
		
		btnExportPDF = new Button(this, SWT.PUSH | SWT.CENTER);
		btnExportPDF.setText("Export PDF");
		btnExportPDF.setBounds(690, 530, 80, 23);		
		btnExportPDF.addSelectionListener(this);
		
		/*btnExportTXT = new Button(this, SWT.PUSH | SWT.CENTER);
		btnExportTXT.setText("Export TEXT");
		btnExportTXT.setBounds(440, 530, 80, 23);		
		btnExportTXT.addSelectionListener(this);*/

		populateBranches(cbDDEBranch);

		return this;
	}

	/**
	 * 
	 * @throws Exception
	 */
	private void populateComboStations() throws Exception {
		StationsDTO[] stations = null;
		stations = handler.getAllStations();
		if (stations != null) {
			String stnCode = null;
			int len = stations.length;
			ArrayList<String> list = new ArrayList<String>();

			for (int i = 0; i < len; i++) {
				stnCode = stations[i].getId();
				if (stnCode.equalsIgnoreCase(stations[i].getBranch_code())
						&& !stations[i].getBranch_code().equalsIgnoreCase("HO")) {
					list.add(stnCode);
					cbFromStation.add(stnCode);
					cbInwardStation.add(stnCode);
				}
			}

			int size = list.size();
			if (size > 0)
				BRANCHES = (String[]) list.toArray(new String[size]);
		}
	}

	private Canvas getCneeCanvas() throws Exception {

		cvsCneeBookingAnalysis = new Canvas(tabReport, SWT.NONE);

		{

			{
				tblCneeBookingAnalysis = new Table(cvsCneeBookingAnalysis,
						SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL
								| SWT.BORDER);

				tblCneeBookingAnalysis.setHeaderVisible(true);
				tblCneeBookingAnalysis.setLinesVisible(true);
				tblCneeBookingAnalysis.setBounds(30, 100, 710, 350);

			}
			{
				colCneeBDsno = new TableColumn(tblCneeBookingAnalysis, SWT.NONE);
				colCneeBDsno.setText("S NO");
				colCneeBDsno.setWidth(40);

			}
			{
				colCneeBDBranchCode = new TableColumn(tblCneeBookingAnalysis,
						SWT.NONE);
				colCneeBDBranchCode.setText("Branch Code");
				colCneeBDBranchCode.setWidth(80);

			}
			{
				colCneeBDCneeName = new TableColumn(tblCneeBookingAnalysis,
						SWT.NONE);
				colCneeBDCneeName.setText("Cnee Name");
				colCneeBDCneeName.setWidth(100);

			}

			{
				lblCneeBABranch = new Label(cvsCneeBookingAnalysis, SWT.NONE);
				lblCneeBABranch.setText("Select Branch");
				lblCneeBABranch.setBounds(31, 59, 74, 21);
			}
			
			{
				cbCneeBABranch = new Combo(cvsCneeBookingAnalysis, SWT.NONE);
				cbCneeBABranch.setBounds(110, 59, 89, 21);
				StationsDTO[] dto = handler.getAllBranches();
				if (null != dto) {
					cbCneeBABranch.add("All");
					for (int i = 0; i < dto.length; i++) {
						cbCneeBABranch.add(dto[i].getName() + " - "
								+ dto[i].getId());
					}
				}
			}

			{
				lblCneeBAMonths = new Label(cvsCneeBookingAnalysis, SWT.NONE);
				lblCneeBAMonths.setText("Select n Months");
				lblCneeBAMonths.setBounds(210, 60, 79, 21);
			}
			{
				cbCneeBAMonths = new Combo(cvsCneeBookingAnalysis, SWT.NONE);
				cbCneeBAMonths.setBounds(294, 59, 75, 21);
				for (int i = 1; i < 13; i++) {
					cbCneeBAMonths.add(String.valueOf(i));
				}
				cbCneeBAMonths.addSelectionListener(this);
			}

			{
				lblCneeBAPickMonths = new Label(cvsCneeBookingAnalysis,
						SWT.NONE);
				lblCneeBAPickMonths.setText("Pick Months");
				lblCneeBAPickMonths.setBounds(379, 59, 60, 21);
			}

			{
				lstCneePickMonths = new List(cvsCneeBookingAnalysis, SWT.BORDER
						| SWT.MULTI | SWT.V_SCROLL);
				lstCneePickMonths.setBounds(440, 14, 138, 83);
				lstCneePickMonths.addSelectionListener(this);

			}
			{
				cbCneeBAOption = new Combo(cvsCneeBookingAnalysis,
						SWT.READ_ONLY);
				cbCneeBAOption.setBounds(627, 39, 110, 25);
				cbCneeBAOption.add(COUNT);
				cbCneeBAOption.add(BASIC_FREIGHT);
				cbCneeBAOption.add(TOTAL_FREIGHT);
				cbCneeBAOption.add(ACTUAL_WEIGHT);
				cbCneeBAOption.add(CHARGED_WEIGHT);
				cbCneeBAOption.add(NOA);
				// cbOption.select(0);

			}
			{
				btnCneeBAView = new Button(cvsCneeBookingAnalysis, SWT.PUSH
						| SWT.CENTER);
				btnCneeBAView.setText("View");
				btnCneeBAView.setBounds(679, 65, 60, 30);
				btnCneeBAView.addSelectionListener(this);
			}

		}
		return cvsCneeBookingAnalysis;
	}

	private Canvas getCnorCanvas() throws Exception {

		cvsCnorBookingAnalysis = new Canvas(tabReport, SWT.NONE);

		{

			{
				tblCnorBookingAnalysis = new Table(cvsCnorBookingAnalysis,
						SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL
								| SWT.BORDER);

				tblCnorBookingAnalysis.setHeaderVisible(true);
				tblCnorBookingAnalysis.setLinesVisible(true);
				tblCnorBookingAnalysis.setBounds(30, 100, 710, 350);

			}
			{
				colCnorBDsno = new TableColumn(tblCnorBookingAnalysis, SWT.NONE);
				colCnorBDsno.setText("S NO");
				colCnorBDsno.setWidth(40);

			}
			{
				colCnorBDBranchCode = new TableColumn(tblCnorBookingAnalysis,
						SWT.NONE);
				colCnorBDBranchCode.setText("Branch Code");
				colCnorBDBranchCode.setWidth(80);

			}
			{
				colCnorBDCnorName = new TableColumn(tblCnorBookingAnalysis,
						SWT.NONE);
				colCnorBDCnorName.setText("Cnor Name");
				colCnorBDCnorName.setWidth(100);

			}
						
			{
				lblCnorBABranch = new Label(cvsCnorBookingAnalysis, SWT.NONE);
				lblCnorBABranch.setText("Select Branch");
				lblCnorBABranch.setBounds(31, 59, 74, 21);
			}
			{
				cbCnorBABranch = new Combo(cvsCnorBookingAnalysis, SWT.NONE);
				cbCnorBABranch.setBounds(110, 59, 89, 21);
				StationsDTO[] dto = handler.getAllBranches();
				if (null != dto) {
					cbCnorBABranch.add("All");
					for (int i = 0; i < dto.length; i++) {
						cbCnorBABranch.add(dto[i].getName() + " - "
								+ dto[i].getId());
					}
				}
			}

			{
				lblCnorBAMonths = new Label(cvsCnorBookingAnalysis, SWT.NONE);
				lblCnorBAMonths.setText("Select n Months");
				lblCnorBAMonths.setBounds(210, 60, 79, 21);
			}
			{
				cbCnorBAMonths = new Combo(cvsCnorBookingAnalysis, SWT.NONE);
				cbCnorBAMonths.setBounds(294, 59, 75, 21);
				for (int i = 1; i < 13; i++) {
					cbCnorBAMonths.add(String.valueOf(i));
				}
				cbCnorBAMonths.addSelectionListener(this);
			}

			{
				lblCnorBAPickMonths = new Label(cvsCnorBookingAnalysis,
						SWT.NONE);
				lblCnorBAPickMonths.setText("Pick Months");
				lblCnorBAPickMonths.setBounds(379, 59, 60, 21);
			}

			{
				lstCnorPickMonths = new List(cvsCnorBookingAnalysis, SWT.BORDER
						| SWT.MULTI | SWT.V_SCROLL);
				lstCnorPickMonths.setBounds(440, 14, 138, 83);
				lstCnorPickMonths.addSelectionListener(this);

			}
			{
				cbCnorBAOption = new Combo(cvsCnorBookingAnalysis,
						SWT.READ_ONLY);
				cbCnorBAOption.setBounds(627, 39, 110, 25);
				cbCnorBAOption.add(COUNT);
				cbCnorBAOption.add(BASIC_FREIGHT);
				cbCnorBAOption.add(TOTAL_FREIGHT);
				cbCnorBAOption.add(ACTUAL_WEIGHT);
				cbCnorBAOption.add(CHARGED_WEIGHT);
				cbCnorBAOption.add(NOA);
				// cbOption.select(0);

			}
			{
				btnCnorBAView = new Button(cvsCnorBookingAnalysis, SWT.PUSH
						| SWT.CENTER);
				btnCnorBAView.setText("View");
				btnCnorBAView.setBounds(679, 65, 60, 30);
				btnCnorBAView.addSelectionListener(this);
			}

		}
		return cvsCnorBookingAnalysis;
	}

	/**
	 * 
	 * @return
	 */
	private Table getSummaryCancelledLRTable() {

		if (tblCancelledLR != null) {
			tblCancelledLR.dispose();
		}
		tblCancelledLR = new Table(cvsCancelledLRs, SWT.FULL_SELECTION
				| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);

		tblCancelledLR.setHeaderVisible(true);
		tblCancelledLR.setLinesVisible(true);
		tblCancelledLR.setBounds(10, 90, 670, 370);
		{
			colCLsno = new TableColumn(tblCancelledLR, SWT.NONE);
			colCLsno.setText("S NO");
			colCLsno.setWidth(40);

		}
		{
			colCLBranchCode = new TableColumn(tblCancelledLR, SWT.NONE);
			colCLBranchCode.setText("Branch Code");
			colCLBranchCode.setWidth(110);

		}
		{
			colCLStationCode = new TableColumn(tblCancelledLR, SWT.NONE);
			colCLStationCode.setText("Station Code");
			colCLStationCode.setWidth(110);

		}
		{
			colCLNoOfCancelLR = new TableColumn(tblCancelledLR, SWT.NONE);
			colCLNoOfCancelLR.setText("No Of Cancelled LRs");
			colCLNoOfCancelLR.setWidth(130);

		}
		{
			colCLTotalFreight = new TableColumn(tblCancelledLR, SWT.NONE);
			colCLTotalFreight.setText("Total Freight");
			colCLTotalFreight.setWidth(130);

		}

		{
			colCLAvgFreight = new TableColumn(tblCancelledLR, SWT.NONE);
			colCLAvgFreight.setText("Average Freight");
			colCLAvgFreight.setWidth(120);

		}

		for (int index = 0; index < tblCancelledLR.getColumnCount(); index++) {
			tblCancelledLR.getColumn(index).addListener(SWT.Selection,
					new sortListener());
		}

		return tblCancelledLR;

	}

	/**
	 * 
	 * @return
	 */
	private Table getDetailedCancelledLRTable() {

		if (tblCancelledLR != null) {
			tblCancelledLR.dispose();
		}
		tblCancelledLR = new Table(cvsCancelledLRs, SWT.FULL_SELECTION
				| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);

		tblCancelledLR.setHeaderVisible(true);
		tblCancelledLR.setLinesVisible(true);
		tblCancelledLR.setBounds(17, 92, 782, 368);

		{
			colCancelledDetailedSno = new TableColumn(tblCancelledLR, SWT.NONE);
			colCancelledDetailedSno.setText("Sno");
			colCancelledDetailedSno.setWidth(60);
		}
		{
			colCancelledDetailedLrno = new TableColumn(tblCancelledLR, SWT.NONE);
			colCancelledDetailedLrno.setText("Lrno");
			colCancelledDetailedLrno.setWidth(60);
		}
		{
			colCancelledDetailedLrdate = new TableColumn(tblCancelledLR,
					SWT.NONE);
			colCancelledDetailedLrdate.setText("Lrdate");
			colCancelledDetailedLrdate.setWidth(60);
		}
		{
			colCancelledDetailedLrtype = new TableColumn(tblCancelledLR,
					SWT.NONE);
			colCancelledDetailedLrtype.setText("LrType");
			colCancelledDetailedLrtype.setWidth(60);
		}
		{
			colCancelledDetailedFrom = new TableColumn(tblCancelledLR, SWT.NONE);
			colCancelledDetailedFrom.setText("From");
			colCancelledDetailedFrom.setWidth(60);
		}
		{
			colCancelledDetailedTo = new TableColumn(tblCancelledLR, SWT.NONE);
			colCancelledDetailedTo.setText("To");
			colCancelledDetailedTo.setWidth(60);
		}
		{
			colCancelledDetailedNoa = new TableColumn(tblCancelledLR, SWT.NONE);
			colCancelledDetailedNoa.setText("Noa");
			colCancelledDetailedNoa.setWidth(60);
		}
		{
			colCancelledDetailedArtValue = new TableColumn(tblCancelledLR,
					SWT.NONE);
			colCancelledDetailedArtValue.setText("ArtValue");
			colCancelledDetailedArtValue.setWidth(60);
		}
		{
			colCancelledDetailedBft = new TableColumn(tblCancelledLR, SWT.NONE);
			colCancelledDetailedBft.setText("Bft");
			colCancelledDetailedBft.setWidth(60);
		}
		{
			colCancelledDetailedCC = new TableColumn(tblCancelledLR, SWT.NONE);
			colCancelledDetailedCC.setText("CC");
			colCancelledDetailedCC.setWidth(60);
		}
		{
			colCancelledDetailedIec = new TableColumn(tblCancelledLR, SWT.NONE);
			colCancelledDetailedIec.setText("Iec");
			colCancelledDetailedIec.setWidth(60);
		}
		{
			colCancelledDetailedOthers = new TableColumn(tblCancelledLR,
					SWT.NONE);
			colCancelledDetailedOthers.setText("Others");
			colCancelledDetailedOthers.setWidth(60);
		}
		{
			colCancelledDetailedDD = new TableColumn(tblCancelledLR, SWT.NONE);
			colCancelledDetailedDD.setText("DD");
			colCancelledDetailedDD.setWidth(60);
		}
		{
			colCancelledDetailedTotal = new TableColumn(tblCancelledLR,
					SWT.NONE);
			colCancelledDetailedTotal.setText("Total");
			colCancelledDetailedTotal.setWidth(60);
		}

		for (int index = 0; index < tblCancelledLR.getColumnCount(); index++) {
			tblCancelledLR.getColumn(index).addListener(SWT.Selection,
					new sortListener());
		}
		return tblCancelledLR;

	}

	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		

	}

	@Override
	public void widgetSelected(SelectionEvent event) {

		AdminComposite.display("STATUS", STATUS_SUCCESS, SUCCESS_FONT_COLOR);

		Object source = event.getSource();
		if(source == tabReport){
			String selectedTab = tabReport.getSelection()[0].getText();
			if (selectedTab.equalsIgnoreCase(RS)) {
				if(cbRSBranch.getItemCount() < 3){
					populateBranches(cbRSBranch);
				}
			} else if (selectedTab.equalsIgnoreCase(BPA)) {
				if(cbBPABranch.getItemCount() < 3){
					populateBranches(cbBPABranch);
				}
			} else if (selectedTab.equalsIgnoreCase(CCC_SUMMARY_TAB)) {
				if(cbCCCBranch.getItemCount() < 3){
					populateBranches(cbCCCBranch);
				}
			} else if (selectedTab.equalsIgnoreCase(DAILY_BOOKING_TAB)) {
				if(cbDBABranch.getItemCount() < 3){
					populateBranches(cbDBABranch);
				}
			} else if (selectedTab.equalsIgnoreCase(UT_TOPAY)) {
				if(cbUTBranch.getItemCount() < 3){
					populateBranches(cbUTBranch);
				}
				//handleBranchAction(cbUTBranch,cbUTStation);
			} else if (selectedTab.equalsIgnoreCase(COUNTER_REPORT)) {
				if(cbCounterBranch.getItemCount() < 3){
					populateBranches(cbCounterBranch);
				}
			} else if (selectedTab.equalsIgnoreCase(SUNDRY_BOOKING_TAB)) {
				if(cbSBABranch.getItemCount() < 3){
					populateBranches(cbSBABranch);
				}
			} else if (selectedTab.equalsIgnoreCase(UPD_READY)) {
				if(cbUPDRBranch.getItemCount() < 3){
					populateBranches(cbUPDRBranch);
				}
			
			} else if (selectedTab.equalsIgnoreCase(Service_Tax_LR)) {
				if(cbStLRBranch.getItemCount() < 3){
					populateBranches(cbStLRBranch);
				}
			
			}
			
			else if (selectedTab.equalsIgnoreCase(INTER_INTRA)&& btnExportXL!= source && btnExportPDF!= source && btnPrint!= source) {
				if (tblIIStates != null)
					tblIIStates.removeAll();
	
				populateIIColumns();
			}
		}
		if (source == cbDDEBranch) {
			handleBranchActionDDE(cbDDEBranch, cbDDEStation);
			txtDDEAbove.setText("");
		} else if (source == btnDDEGo) {
			AdminComposite.display("Generating Report...", STATUS_SUCCESS,
					SUCCESS_FONT_COLOR);
			handleDDE();
			AdminComposite.display("Report Loaded Successfully!",
					STATUS_SUCCESS, SUCCESS_FONT_COLOR);
		} else if (source == btnRSGo) {
			AdminComposite.display("Generating Report...", STATUS_SUCCESS,
					SUCCESS_FONT_COLOR);

			handleRS();
			AdminComposite.display("Report Loaded Successfully!",
					STATUS_SUCCESS, SUCCESS_FONT_COLOR);
		} else if (source == rdFull) {
			if (tblRemittance != null)
				tblRemittance.removeAll();

			if (dto != null) {
				populateRSReport(dto);
			}
		} else if (source == rdUnrecover) {
			if (tblRemittance != null)
				tblRemittance.removeAll();

			if (dto != null) {
				populateRSReport(dto);
			}
		} else if (source == cbBPABranch) {
			if (!cbBPABranch.getText().equalsIgnoreCase("All")) {
				cbBPAReportType.select(1);
				cbBPAReportType.setEnabled(false);
			} else {
				cbBPAReportType.select(0);
				cbBPAReportType.setEnabled(true);
			}
		} /*else if (source == cbBPAChoice) {
			colBPAFigure.setText(cbBPAChoice.getText());
		}*/ else if (source == btnBPAGo) {
			AdminComposite.display("Generating Report...", STATUS_SUCCESS,
					SUCCESS_FONT_COLOR);

			handleBPA();
			AdminComposite.display("Report Loaded Successfully!",
					STATUS_SUCCESS, SUCCESS_FONT_COLOR);
		} else if (source == btnCCCGo) {
			AdminComposite.display("Generating Report...", STATUS_SUCCESS,
					SUCCESS_FONT_COLOR);

			handleCCC();
			AdminComposite.display("Report Loaded Successfully!",
					STATUS_SUCCESS, SUCCESS_FONT_COLOR);
		} 
		else if(source == cbUTBranch ){
			
			handleBranchActionUT(cbUTBranch, cbUTStation);
			
			
		}
			
		else if (source == cbUTReportType) {
			try {
				if (cbUTReportType.getText().equalsIgnoreCase("Detailed")) {
					tblUndeliveredTopayDet.setVisible(true);
					tblUndeliveredTopayDet.removeAll();
					tblUndeliveredTopay.setVisible(false);
					cbUTStation.setEnabled(true);
				} else {
					tblUndeliveredTopayDet.setVisible(false);
					tblUndeliveredTopay.removeAll();
					tblUndeliveredTopay.setVisible(true);
					cbUTStation.setEnabled(false);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (source == btnUTGo) {
			AdminComposite.display("Generating Report...", STATUS_SUCCESS,
					SUCCESS_FONT_COLOR);

			handleUT();
			AdminComposite.display("Report Loaded Successfully!",
					STATUS_SUCCESS, SUCCESS_FONT_COLOR);
		}
		if (source == cbDBABranch) {
			if (cbDBABranch.getSelectionIndex() != -1) {
				if (!cbDBABranch.getText().equalsIgnoreCase("All")) {
					// populateSelectedStations(cbDBABranch.getText(),
					// tblDailyBookingAnalysis);
					cbDBAReportType.setText("Detailed");
					cbDBAReportType.setEnabled(false);
				} else {
					cbDBAReportType.setText("Consolidated");
					cbDBAReportType.setEnabled(true);
					// populateAllBranches(tblDailyBookingAnalysis);
				}

			}
		} else if (source == cbDBAReportType) {

		} else if (source == btnDBAGo) {
			AdminComposite.display("Generating Report...", STATUS_SUCCESS,
					SUCCESS_FONT_COLOR);

			String branch = "";
			if (cbDBABranch.getSelectionIndex() == -1) {
				// AdminComposite.display("Please select branch",
				// STATUS_SUCCESS,SUCCESS_FONT_COLOR);
			} else {
				if (handler != null) {

					branch = cbDBABranch.getText();
					int index = branch.indexOf(" - ");
					branch = branch.substring(index + 3);

					if (!cbDBABranch.getText().equalsIgnoreCase("All")) {
						populateSelectedStations(branch,
								tblDailyBookingAnalysis);
					} else if (cbDBABranch.getText().equalsIgnoreCase("All")
							&& cbDBAReportType.getText().equalsIgnoreCase(
									"Consolidated")) {
						populateAllBranches(tblDailyBookingAnalysis);
					} else if (cbDBABranch.getText().equalsIgnoreCase("All")
							&& cbDBAReportType.getText().equalsIgnoreCase(
									"Detailed")) {
						
						
						populateAllStations(tblDailyBookingAnalysis);
					}

					try {
						GeneralSummaryDTO[] bookedLr = null;
						DateFormat dateFormat = new SimpleDateFormat(
								"dd-MM-yyyy");
						Date fromDt = dateFormat.parse(txtDBAFrom.getText());
						Date toDate = dateFormat.parse(txtDBATo.getText());

						 bookedLr = getDailyBooking(fromDt, toDate);

						if (bookedLr != null) {
							populateDailyBooking(fromDt, bookedLr);
						} else {
							if (tblDailyBookingAnalysis != null)
								tblDailyBookingAnalysis.removeAll();
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			AdminComposite.display("Report Loaded Successfully!",
					STATUS_SUCCESS, SUCCESS_FONT_COLOR);
		} else if (source == cbCounterBranch) {
			if (!cbCounterBranch.getText().equalsIgnoreCase("All")) {
				cbCRReportType.select(1);
				cbCRReportType.setEnabled(false);
			} else {
				cbCRReportType.select(0);
				cbCRReportType.setEnabled(true);
			}
		} else if (btnCounterGo == source) {
			AdminComposite.display("Generating Report...", STATUS_SUCCESS,
					SUCCESS_FONT_COLOR);

			try {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Date from_date = dateFormat.parse(txtCRFrom.getText());
				Date to_date = dateFormat.parse(txtCRTo.getText());

				int index = -1;

				index = cbCounterBranch.getSelectionIndex();
				
				if (index > -1) {
					
						String branch_code = cbCounterBranch.getText();
						// System.out.println(branch_code);

						if (branch_code.equalsIgnoreCase("All")) {
							if (cbCRReportType.getText().equalsIgnoreCase(
									"Detailed")) {
								branch_code = "AllD";
							} else {
								branch_code = "AllC";
							}

						} else {
							index = branch_code.indexOf(" - ");
							branch_code = branch_code.substring(index + 3);
						}
						if(cbCRCounterSet.getText().equalsIgnoreCase("LR Set")){
							getCounterReport(from_date, to_date, branch_code);
						}
						else
						{	
							getCounterReportMisc(from_date, to_date, branch_code);
						}
					}
				
				

			} catch (Exception e) {
				e.printStackTrace();
			}
			AdminComposite.display("Report Loaded Successfully!",
					STATUS_SUCCESS, SUCCESS_FONT_COLOR);
		} else if (cbCRCounterSet == source) {
			int index = cbCRCounterSet.getSelectionIndex();
			if (index > -1) {
				if (index == 0) {
					colCRC1.setText("Total Lrs");
					colCRC2.setText("Topay Lrs");
					colCRC3.setText("Paid Lrs");
					colCRC4.setText("Billing Lrs");
					colCRC5.setText("Sundry Lrs");
					colCRC6.setText("Quotation Lrs");
					colCRC7.setText("FOC Lrs");
					colCRC8.setText("Cancelled Lrs");

				} else if (index == 1) {
				  
				  colCRC1.setText("NOA");
				  colCRC2.setText("Actual weight");
				  colCRC3.setText("Charged Weight");
				  colCRC4.setText("Door Delivery");
				  colCRC5.setText("Office Delivery");
				  colCRC6.setText("Inwarded LRs");
				  colCRC7.setText("Total CRs");
				  colCRC8.setText("Station Compliants");
				 
				}
			}

		}// Sundry booking analysis
		else if (source == cbSBABranch) {
				if(cbSBABranch.getText().equalsIgnoreCase("All")){
				cbSBAStation.removeAll();
				cbSBAStation.add("Group");
				cbSBAStation.add("All");
			}else{
				String branch = cbSBABranch.getText();
				int index = branch.indexOf(" - ");
				branch = branch.substring(index + 3);
				// if (cbSBABranch.getSelectionIndex() != -1) {
				sundryBranchComboAction(branch, cbSBAStation);
				

			// }
		}
		} else if (source == cbSBAStation) {

		} else if (source == btnSBAGo) {
			AdminComposite.display("Generating Report...", STATUS_SUCCESS,
					SUCCESS_FONT_COLOR);
			
			if (cbSBABranch.getSelectionIndex() == -1 && cbSBAStation
					.getSelectionIndex() == -1)
					/*|| (!cbSBABranch.getText().equalsIgnoreCase("All") && cbSBAStation
							.getSelectionIndex() == -1))*/ {
				// AdminComposite.display("Please select",
				// STATUS_SUCCESS,SUCCESS_FONT_COLOR);
			} else {
				if (handler != null) {
					try {
						GeneralSummaryDTO[] sundryBooking = null;
						DateFormat dateFormat = new SimpleDateFormat(
								"dd-MM-yyyy");
						Date fromDt = dateFormat.parse(txtSBAFrom.getText());
						Date toDate = dateFormat.parse(txtSBATo.getText());
						String branch = cbSBABranch.getText();
						if(branch.equalsIgnoreCase("All")){
							branch = "%";
						}
						else{
							int index = branch.indexOf(" - ");
							branch = branch.substring(index + 3);
						}
						
						String station = cbSBAStation.getText();
						if(station.equalsIgnoreCase("All")){
							station = "%";
						}else if(station.equalsIgnoreCase("Group")){
							station = "Group";
						}
						sundryBooking = getSundryBooking(fromDt, toDate, branch, station);
						
						if (sundryBooking != null) {
							designSundryTable();
							if ((cbSBABranch.getText().equalsIgnoreCase("All"))
											&& (cbSBAStation.getText().equalsIgnoreCase("Group"))) {
								// Only TRs
								populateSundryBooking(sundryBooking, 1);
							}else if ((cbSBABranch.getText().equalsIgnoreCase("All"))
									&& (cbSBAStation.getText().equalsIgnoreCase("All"))) {
								populateSundryBooking(sundryBooking);
							}
							else {
								populateSundryBooking(sundryBooking);
							}

						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			AdminComposite.display("Report Loaded Successfully!",
					STATUS_SUCCESS, SUCCESS_FONT_COLOR);
		} else if (source == cbUPDRBranch) {
			String branch = cbUPDRBranch.getText();
			int index = branch.indexOf(" - ");
			branch = branch.substring(index + 3);
			// if (cbSBABranch.getSelectionIndex() != -1) {
			sundryBranchComboAction(branch, cbUPDRStation);

			// }
		} else if (source == btnUPDGo) {
			AdminComposite.display("Generating Report...", STATUS_SUCCESS,
					SUCCESS_FONT_COLOR);

			if ((cbUPDRBranch.getSelectionIndex() == -1 && cbUPDRStation
					.getSelectionIndex() == -1)
					|| (!cbUPDRBranch.getText().equalsIgnoreCase("All") && cbUPDRStation
							.getSelectionIndex() == -1)) {
				// AdminComposite.display("Please select",
				// STATUS_SUCCESS,SUCCESS_FONT_COLOR);
			} else {
				if (handler != null) {
					try {
						BookingDTO[] updReady = null;
						boolean isMoreThan = false;

						if (!cbUPDRInwardDays.getText().equals("")) {
							if (cbUPDOption.getText().equalsIgnoreCase(
									"More Than")) {
								isMoreThan = true;
							}
							updReady = designAndGetUPDTable(isMoreThan);

							if (updReady != null) {
								if (cbUPDRBranch.getText().equalsIgnoreCase(
										"All")
										&& cbUPDRStation.getSelectionIndex() == -1) {
									// Display Only TRs
									populateUPDReady(updReady, true);

								} else {
									populateUPDReady(updReady, false);
								}
							}

						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			AdminComposite.display("Report Loaded Successfully!",
					STATUS_SUCCESS, SUCCESS_FONT_COLOR);
		} else if (source == btnIISGo) {
			AdminComposite.display("Generating Report...", STATUS_SUCCESS,
					SUCCESS_FONT_COLOR);

			handleIIS();
			AdminComposite.display("Report Loaded Successfully!",
					STATUS_SUCCESS, SUCCESS_FONT_COLOR);
		} else if (source == btnMSgo) {
			AdminComposite.display("Generating Report...", STATUS_SUCCESS,
					SUCCESS_FONT_COLOR);

			handleMissingCustomer();
			AdminComposite.display("Report Loaded Successfully!",
					STATUS_SUCCESS, SUCCESS_FONT_COLOR);
		} else if (source == cbCLReportType) {
			int index = cbCLReportType.getSelectionIndex();
			String selected = cbCLReportType.getItem(index);
			if (selected.equalsIgnoreCase("Detailed")) {
				// cbStation.setVisible(true);
				cbStation.setEnabled(true);
				
				// label3.setVisible(true);
				label3.setEnabled(true);
				getDetailedCancelledLRTable();
			} else if (selected.equalsIgnoreCase("Summary")) {
				// cbStation.setVisible(false);
				cbStation.setEnabled(false);
				// label3.setVisible(false);
				label3.setEnabled(false);
				getSummaryCancelledLRTable();
			}
		} else if (source == btncancelledlrtodate) {
			KalendarDialog cd = new KalendarDialog(shell);
			Object obj = cd.open();
			if (obj != null) {
				txtcancelledlrtodate.setText(obj.toString());
			}

		}

		else if (source == btncancelledlrfromdate) {
			KalendarDialog cd = new KalendarDialog(shell);
			Object obj = cd.open();
			if (obj != null) {
				txtCancelledLRfromdate.setText(obj.toString());
			}

		} else if (source == btnCancelledLrGo) {
			AdminComposite.display("Generating Report...", STATUS_SUCCESS,
					SUCCESS_FONT_COLOR);

			handleCancelLR(true);
			AdminComposite.display("Report Loaded Successfully!",
					STATUS_SUCCESS, SUCCESS_FONT_COLOR);
		} else if (source == cbCLRBranch) {
			try {
					populateStationForCLR();
					//handleCancelLR(false);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (source == cbStation) {
			try {
				//handleCancelLR(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (source == cbCnorBAMonths) {
			String DATE_FORMAT = "MMM yyyy";
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
					DATE_FORMAT);
			Calendar c1 = Calendar.getInstance();
			int index = cbCnorBAMonths.getSelectionIndex();
			
			if (index > -1) {
				String noMonth = cbCnorBAMonths.getItem(index);
				
				int temp = Integer.parseInt(noMonth );
				temp = temp - 1;
				lstCnorPickMonths.removeAll();
				for (int i = temp; i >= 0; i--) {
					if (i == temp)
						c1.add(Calendar.MONTH, -(temp));
					else
						c1.add(Calendar.MONTH, 1);
					lstCnorPickMonths.add(sdf.format(c1.getTime()));
				}
				

				try {
					int count = lstCnorPickMonths.getItemCount();
					String[] list = new String[count];
					
					for(int i = 0,k = count-1; k>=0; k--,i++ ){
						list[i] = lstCnorPickMonths.getItem(k);
						
						 						
					}
					
					lstCnorPickMonths.removeAll();
					lstCnorPickMonths.setItems(list);					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else if (source == cbCneeBAMonths) {
			String DATE_FORMAT = "MMM yyyy";
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
					DATE_FORMAT);
			Calendar c1 = Calendar.getInstance();
			int index = cbCneeBAMonths.getSelectionIndex();
			if (index > -1) {
				String noMonth = cbCneeBAMonths.getItem(index);
				int temp = Integer.parseInt(noMonth) ;
				temp = temp - 1;
				lstCneePickMonths.removeAll();
				for (int i = temp; i >= 0; i--) {
					
					if (i == temp)
						c1.add(Calendar.MONTH, -(temp));
						
					else
						c1.add(Calendar.MONTH, 1);
					
				lstCneePickMonths.add((sdf.format(c1.getTime())));
				}
				
				
				try {
					int count = lstCneePickMonths.getItemCount();
					String[] list = new String[count];
					
					for(int i = 0,k = count-1; k>=0; k--,i++ ){
						list[i] = lstCneePickMonths.getItem(k);
						
						 						
					}
					
					lstCneePickMonths.removeAll();
					lstCneePickMonths.setItems(list);					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
								
			}	
				
			
		} else if (source == lstCnorPickMonths) {
			createCnorBATable();

		} else if (source == lstCneePickMonths) {
			createCneeBATable();

		} else if (source == btnCneeBAView) {
			AdminComposite.display("Generating Report...", STATUS_SUCCESS,
					SUCCESS_FONT_COLOR);

			try {
				String Date_format = "yyyy-MM-dd";
				java.text.SimpleDateFormat sdf2 = new java.text.SimpleDateFormat(Date_format);
				String DATE_FORMAT = "yyyy-MM";
				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
						DATE_FORMAT);
				Calendar c1 = Calendar.getInstance();
				int index = cbCneeBAMonths.getSelectionIndex();
				if (index > -1) {
					String noMonth = cbCneeBAMonths.getItem(index);
					int temp = Integer.parseInt(noMonth) ;
												
					/*String to_date = sdf2.format(c1.getTime());
					c1.add(Calendar.MONTH, -(temp - 1));
					String from_date = sdf.format(c1.getTime()) + "-01";
					
					//c1.add(Calendar.MONTH, temp - 1);
					//String to_date = sdf.format(c1.getTime()) + "-30";*/
					int dat[] =  lstCneePickMonths.getSelectionIndices();
					c1.add(Calendar.MONTH, -(dat[0]));
					String to_date = sdf.format(c1.getTime()) + "-31";
					Calendar c2 = Calendar.getInstance();
					c2.add(Calendar.MONTH, -(dat[lstCneePickMonths.getSelectionCount() - 1] ));
					String from_date = sdf.format(c2.getTime()) + "-01";
					//System.out.println("from_date-->"+from_date);
					//System.out.println("to_date-->"+to_date);
					int bIndex = cbCneeBABranch.getSelectionIndex();
					String branch = null;
					if (bIndex > -1) {
						branch = cbCneeBABranch.getItem(bIndex);
					}
					if (branch != null && branch.equalsIgnoreCase("All")) {
						branch = "%";
					} else if (branch != null) {
						int hIndex = branch.indexOf("-");
						branch = branch.substring(hIndex + 1).trim();
					}
					CustomerBusinessAnalysisDTO[] dto = handler.getCneeBusinessAnalysisReport(false, branch,from_date, to_date);
					if (tblCneeBookingAnalysis != null)
						tblCneeBookingAnalysis.removeAll();

					String DATE_FORMAT1 = "MMM yyyy";
					java.text.SimpleDateFormat sdf1 = new java.text.SimpleDateFormat(DATE_FORMAT1);
					if (null != dto) {
						int len = dto.length;
						for (int i = 0; i < len; i++) {
							
							String col_date = sdf1.format(dto[i].getLr_date());
							int count = tblCneeBookingAnalysis.getColumnCount();
							for (int j = 0; j < count; j++) {
								
								int selectedIndex = -1;
								
								if (col_date.equalsIgnoreCase(tblCneeBookingAnalysis.getColumn(j).getText())) {
									
									selectedIndex = cbCneeBAOption.getSelectionIndex();
									int noa = 0;
									float bft = 0;
									if (selectedIndex > -1) {
										TableItem item = null;
										item = isAlreadyAvail(tblCneeBookingAnalysis,String.valueOf(dto[i].getCustomerName()),String.valueOf(dto[i].getFromStation()));
										if (item == null) {
											
											item = new TableItem(tblCneeBookingAnalysis,SWT.NONE);
										
											
										}
										
										
										item.setText(1, String.valueOf(dto[i].getFromStation()));
										String tempUnit = cbCneeBAOption.getItem(selectedIndex);
										item.setText(2, String.valueOf(dto[i].getCustomerName()));
											
											String join = null;
											if (tempUnit != null	&& tempUnit.equalsIgnoreCase(COUNT)) {
												//join = item.getText(j);
												//if (join.equalsIgnoreCase(""))
												join = "0";
												item.setText(j,String.valueOf(Integer.parseInt(join) + dto[i].getCount()));
												
											} else if (tempUnit != null && tempUnit.equalsIgnoreCase(BASIC_FREIGHT)) {
												//join = item.getText(j);
												//if (join.equalsIgnoreCase(""))
												join = "0";
												item.setText(j,getRoundedValue(Float.parseFloat(join)	+ dto[i].getBasic_freight()));
												
											} else if (tempUnit != null	&& tempUnit.equalsIgnoreCase(TOTAL_FREIGHT)) {
												//join = item.getText(j);
												//if (join.equalsIgnoreCase(""))
												join = "0";
												item.setText(j,getRoundedValue(Float.parseFloat(join)	+ dto[i].getTotal_freight()));
												
											} else if (tempUnit != null	&& tempUnit.equalsIgnoreCase(ACTUAL_WEIGHT)) {
												//join = item.getText(j);
												//if (join.equalsIgnoreCase(""))
												join = "0";
												item.setText(j,getRoundedValue(Float.parseFloat(join)	+ dto[i].getActual_weight()));
												
											} else if (tempUnit != null	&& tempUnit.equalsIgnoreCase(CHARGED_WEIGHT)) {
												//join = item.getText(j);
												//if (join.equalsIgnoreCase(""))
												join = "0";
												item.setText(j, getRoundedValue(Float.parseFloat(join)	+ dto[i].getCharged_weight()));
												
											} else if (tempUnit != null	&& tempUnit.equalsIgnoreCase(NOA)) {
												//join = item.getText(j);
												//if (join.equalsIgnoreCase(""))
												join = "0";
												item.setText(j, String.valueOf(Integer.parseInt(join)+ dto[i].getNoa()));
											}
	
											
										//}
										break;
									}
								}
							}
							
						}
						int column = tblCneeBookingAnalysis.getColumnCount();

						TableItem[] items = tblCneeBookingAnalysis.getItems();
						int size = items.length;
						for (int p = 0; p < size; p++) {
							if(items[p].getText().equalsIgnoreCase("")){
								items[p].setText(0,String.valueOf(p + 1));
							}
							
							for (int cl = 3; cl < column; cl++) {
								if (items[p].getText(cl).equalsIgnoreCase("")) {
									items[p].setText(cl, "0");
								}
							}
						}

						float[] total = new float[column];
						for (int i = 3; i < column; i++) {
							// TableItem
							TableItem[] item = tblCneeBookingAnalysis
									.getItems();
							if (item != null) {
								int len1 = item.length;
								for (int j = 0; j < len1; j++) {
									total[i] = total[i]
											+ Float.parseFloat(item[j]
													.getText(i));
									//System.out.println(item[j].getText(i));
								}
							}
						}
						
						TableItem item = new TableItem(tblCneeBookingAnalysis,
								SWT.NONE);
						Font font1 = new Font(Display.getCurrent(), "Tahoma", 8, SWT.BOLD);
						item.setFont(font1);
						item.setText(2, String.valueOf("Total"));
						for (int i = 3; i < column; i++) {
							if(cbCnorBAOption.getText().equalsIgnoreCase("Count") || 
									cbCnorBAOption.getText().equalsIgnoreCase("Noa")){
								item.setText(i, String.valueOf(total[i]));
							}else{
								item.setText(i, getRoundedValue(total[i]));
							}
						}
					}

				}
			} catch (NumberFormatException e) {			
				e.printStackTrace();
			} 
			 catch (Exception e) {
				e.printStackTrace();
			}
			AdminComposite.display("Report Loaded Successfully!",
					STATUS_SUCCESS, SUCCESS_FONT_COLOR);
		} else if (btnCnorBAView == source) {
			AdminComposite.display("Generating Report...", STATUS_SUCCESS,
					SUCCESS_FONT_COLOR);

			try {
				String Date_format = "yyyy-MM-dd";
				java.text.SimpleDateFormat sdf2 = new java.text.SimpleDateFormat(Date_format);
				
				String DATE_FORMAT = "yyyy-MM";
				java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
						DATE_FORMAT);
				Calendar c1 = Calendar.getInstance();
				int index = cbCnorBAMonths.getSelectionIndex();
				
				
				if (index > -1) {
					String noMonth = cbCnorBAMonths.getItem(index); 
									
					int temp = Integer.parseInt(noMonth);
				
					/*String to_date = sdf2.format(c1.getTime());
					//Date d = new Date();
					//String to_date = sdf2.format(d);
					c1.add(Calendar.MONTH, -(temp - 1));
					String from_date = sdf.format(c1.getTime()) + "-01";*/
					//c1.add(Calendar.MONTH, temp - 1);
					//String to_date = sdf.format(c1.getTime()) + "-30";
					int dat[] =  lstCnorPickMonths.getSelectionIndices();
					c1.add(Calendar.MONTH, -(dat[0]));
					String to_date = sdf.format(c1.getTime()) + "-31";
					Calendar c2 = Calendar.getInstance();
					c2.add(Calendar.MONTH, -(dat[lstCnorPickMonths.getSelectionCount() - 1] ));
					String from_date = sdf.format(c2.getTime()) + "-01";
					//System.out.println("from_date-->"+from_date);
					//System.out.println("to_date-->"+to_date);
			
					int bIndex = cbCnorBABranch.getSelectionIndex();
					String branch = null;
					if (bIndex > -1) {
						branch = cbCnorBABranch.getItem(bIndex);
					}
					if (branch != null && branch.equalsIgnoreCase("All")) {
						branch = "%";
					} else if (branch != null) {
						int hIndex = branch.indexOf("-");
						branch = branch.substring(hIndex + 1).trim();
					}
					//CustomerBusinessAnalysisDTO[] dto =  handler.getCustomerBusinessAnalysisReport(true, branch, null , null );
					CustomerBusinessAnalysisDTO[] dto =  handler.getCnorBusinessAnalysisReport(true, branch,from_date, to_date);
					if (tblCnorBookingAnalysis != null)
						tblCnorBookingAnalysis.removeAll();

					String DATE_FORMAT1 = "MMM yyyy";
					java.text.SimpleDateFormat sdf1 = new java.text.SimpleDateFormat(
							DATE_FORMAT1);
					if (null != dto) {
						
						int len = dto.length;
						for (int i = 0; i < len; i++) {
							int k = 0;
							String col_date = sdf1.format(dto[i].getLr_date());
							int count = tblCnorBookingAnalysis.getColumnCount();
							for (int j = 0; j < count; j++) {
								
								int selectedIndex = -1;
																
								if (col_date
										.equalsIgnoreCase(tblCnorBookingAnalysis
												.getColumn(j).getText())) {
									
									selectedIndex = cbCnorBAOption
											.getSelectionIndex();
									int noa = 0;
									float bft = 0;
									if (selectedIndex > -1) {
										TableItem item = null;
										item = isAlreadyAvail(tblCnorBookingAnalysis,String.valueOf(dto[i].getCustomerName()),String.valueOf(dto[i].getFromStation()));
										if (item == null) {
											item = new TableItem(tblCnorBookingAnalysis,SWT.NONE);
											
										}
										
										
										
										item.setText(1, String.valueOf(dto[i]
												.getFromStation()));
										String tempUnit = cbCnorBAOption
										.getItem(selectedIndex);
										boolean istrue = false;
										item.setText(2, String.valueOf(dto[i].getCustomerName()));
											
											
											String join = null;
											if (tempUnit != null	&& tempUnit.equalsIgnoreCase(COUNT)) {
												//join = item.getText(j);
												//if (item.getText(j).equalsIgnoreCase(""))
													join = "0";
												item.setText(j,String.valueOf(Integer.parseInt(join)	+ dto[i].getCount()));
												
											} else if (tempUnit != null
													&& tempUnit
															.equalsIgnoreCase(BASIC_FREIGHT)) {
												//join = item.getText(j);
												//if (join.equalsIgnoreCase(""))
													join = "0";
												item.setText(j,getRoundedValue(Integer.parseInt(join) + dto[i].getBasic_freight()));
											
											} else if (tempUnit != null	&& tempUnit.equalsIgnoreCase(TOTAL_FREIGHT)) {
												//join = item.getText(j);
												//if (join.equalsIgnoreCase(""))
												join = "0";
												item.setText(j,getRoundedValue(Integer.parseInt(join)	+ dto[i].getTotal_freight()));
											
											} else if (tempUnit != null && tempUnit.equalsIgnoreCase(ACTUAL_WEIGHT)) {
												//join = item.getText(j);
												//if (join.equalsIgnoreCase(""))
													join = "0";
												item.setText(j,getRoundedValue(Integer.parseInt(join)+ dto[i].getActual_weight()));
											
											} else if (tempUnit != null	&& tempUnit.equalsIgnoreCase(CHARGED_WEIGHT)) {
												//join = item.getText(j);
												//if (join.equalsIgnoreCase(""))
													join = "0";
												item.setText(j,getRoundedValue(Integer.parseInt(join)+ dto[i].getCharged_weight()));
											
											} else if (tempUnit != null	&& tempUnit.equalsIgnoreCase(NOA)) {
												//join = item.getText(j);
												//if (join.equalsIgnoreCase(""))
													join = "0";
												item.setText(j, String.valueOf(Integer.parseInt(join)+ dto[i].getNoa()));
											}
									}
									
								}
							}
							
						}
						
						int column = tblCnorBookingAnalysis.getColumnCount();

						TableItem[] items = tblCnorBookingAnalysis.getItems();
						int size = items.length;
						for (int p = 0; p < size; p++) {
							if(items[p].getText().equalsIgnoreCase("")){
								items[p].setText(0,String.valueOf(p + 1));
								
							}
							for (int cl = 3; cl < column; cl++) {
								
								if (items[p].getText(cl).equalsIgnoreCase("")) {
									items[p].setText(cl, "0");
									
									
								}
							}
						}

						float[] total = new float[column];
						for (int i = 3; i < column; i++) {
							// TableItem
							TableItem[] item = tblCnorBookingAnalysis
									.getItems();
							if (item != null) {
								int len1 = item.length;
								for (int j = 0; j < len1; j++) {
									total[i] = total[i]
											+ Float.parseFloat(item[j]
													.getText(i));
									//System.out.println(item[j].getText(i));
								}
							}
						}
						TableItem item = new TableItem(tblCnorBookingAnalysis,
								SWT.NONE);
						Font font1 = new Font(Display.getCurrent(), "Tahoma", 8, SWT.BOLD);
						item.setFont(font1);
						item.setText(2, String.valueOf("Total"));
						for (int i = 3; i < column; i++) {
							if(cbCneeBAOption.getText().equalsIgnoreCase("Count") || 
									cbCneeBAOption.getText().equalsIgnoreCase("Noa")){
								item.setText(i, String.valueOf(total[i]));
							}else{
								item.setText(i, getRoundedValue(total[i]));
							}							
						}
					}

				}
			} catch (NumberFormatException e) {				
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			AdminComposite.display("Report Loaded Successfully!",
					STATUS_SUCCESS, SUCCESS_FONT_COLOR);
		} else if (btnDRSMonth == source) {
			MonthDialog cd = new MonthDialog(shell);
			Object obj = cd.open();
			if (obj != null) {
				txtDRSMonth.setText(obj.toString());
			}
		} else if (source == cbMarkVehiUtilSelectBranch) {
			try {
				//createMarketTable();
				//populateDefaultTable(cbMarkVehiUtilSelectBranch.getText(),tblMarketVehicleUtilisation, 0);
			} catch (Exception e) {			
				e.printStackTrace();
			}
		}

		else if (btnMarkVehiUtilSelectMonth == source) {
			MonthDialog cd = new MonthDialog(shell);
			Object obj = cd.open();
			if (obj != null) {
				txtMarkVehiUtilSelectMonth.setText(obj.toString());
			}
		} else if (source == btnMargetVehicleGo) {
			AdminComposite.display("Generating Report...", STATUS_SUCCESS,
					SUCCESS_FONT_COLOR);
			try {
				
				createMarketTable();
				populateDefaultTable(cbMarkVehiUtilSelectBranch.getText(),tblMarketVehicleUtilisation, 0);
			} catch (Exception e) {				
				e.printStackTrace();
			}
			handleMarketVehicle();

			AdminComposite.display("Report Loaded Successfully!",
					STATUS_SUCCESS, SUCCESS_FONT_COLOR);
		} else if (cbDRSAttendanceBranch == source) {
			//populateStationOrBranchForDRS();
		} else if (btnDRSAttendance == source) {
			AdminComposite.display("Generating Report...", STATUS_SUCCESS,
					SUCCESS_FONT_COLOR);

			populateStationOrBranchForDRS();
			getDRSAttendance();
			
			AdminComposite.display("Report Loaded Successfully!",
					STATUS_SUCCESS, SUCCESS_FONT_COLOR);
		} else if (source == cbDRSAReportType) {
			try {
				int index = cbDRSAReportType.getSelectionIndex();
				if (index == 0)
					createDRSSummaryTable();
				else
					createDRSPercentReportTable();
			} catch (Exception e) {				
				e.printStackTrace();
			}

		} else if (source == btnOpenLrFrom) {
			KalendarDialog cd = new KalendarDialog(shell);
			Object obj = cd.open();
			if (obj != null) {
				txtOpenLrFrom.setText(obj.toString());
			}

		} else if (source == btnOpenLrTo) {
			KalendarDialog cd = new KalendarDialog(shell);
			Object obj = cd.open();
			if (obj != null) {
				txtOpenLrTo.setText(obj.toString());
			}

		} else if (source == btnOpenView) {
			AdminComposite.display("Generating Report...", STATUS_SUCCESS,
					SUCCESS_FONT_COLOR);

			handleOpenLRs();
			AdminComposite.display("Report Loaded Successfully!",
					STATUS_SUCCESS, SUCCESS_FONT_COLOR);
		} else if (source == btnFOCLrFrom) {
			KalendarDialog cd = new KalendarDialog(shell);
			Object obj = cd.open();
			if (obj != null) {
				txtFOCLrFrom.setText(obj.toString());
			}

		} else if (source == btnFOCLrTo) {
			KalendarDialog cd = new KalendarDialog(shell);
			Object obj = cd.open();
			if (obj != null) {
				txtFOCLrTo.setText(obj.toString());
			}

		} else if (source == cbFOCLrReportType) {
			int index = cbFOCLrReportType.getSelectionIndex();
			String selected = cbFOCLrReportType.getItem(index);
			if (selected.equalsIgnoreCase("Detailed")) {

				getDetailedFOCLRTable();
			} else if (selected.equalsIgnoreCase("Summary")) {
				// cbStation.setVisible(false);
				cbStation.setEnabled(false);
				// label3.setVisible(false);
				label3.setEnabled(false);
				getSummaryFOCLRTable();
			}
		} else if (source == btnFocLrGo) {
			AdminComposite.display("Generating Report...", STATUS_SUCCESS,
					SUCCESS_FONT_COLOR);

			handleFOCLR();
			AdminComposite.display("Report Loaded Successfully!",
					STATUS_SUCCESS, SUCCESS_FONT_COLOR);
		} else if (source == btnLOGFrom) {
			KalendarDialog cd = new KalendarDialog(shell);
			Object obj = cd.open();
			if (obj != null) {
				txtLOGFrom.setText(obj.toString());
			}

		} else if (source == btnLOGTo) {
			KalendarDialog cd = new KalendarDialog(shell);
			Object obj = cd.open();
			if (obj != null) {
				txtLOGToDate.setText(obj.toString());
			}

		} else if (source == btnGo) {
			AdminComposite.display("Generating Report...", STATUS_SUCCESS,
					SUCCESS_FONT_COLOR);

			try {
				// validate();
				if ((cbFromStation.getSelectionIndex() != -1)
						&& (cbInwardStation.getSelectionIndex() != -1)) {
					
					if (handler != null) {
						InwardAnalysisDTO[] dto = null;

						String fdate = txtLOGFrom.getText();
						String tdate = txtLOGToDate.getText();
						/*Date d = new Date();
						System.out.println("DAte-->"+d);
						d.getTime()
						String hour = cbLOGFromHour.getText();
						int hours = Integer.parseInt(hour);
						String min = cbLOGFromMin.getText();
						int minutes = Integer.parseInt(min);
						String timeForm = cbLOGTime.getText();
						/*if (timeForm.equalsIgnoreCase("PM")) {
							int temp = Integer.parseInt(hour);
							temp = temp + 12;
							hour = String.valueOf(temp);
						}
						if (fdate != null && !fdate.equalsIgnoreCase("")) {

							fdate = fdate + " " + hour + ":" + min + ":00";
							tdate = tdate + " " + hour + ":" + min + ":00";
							String DATE_FORMAT1 = "dd-MM-yyy HH:mm:ss";
							java.text.SimpleDateFormat sdf1 = new java.text.SimpleDateFormat(
									DATE_FORMAT1);
							Date fromdate = sdf1.parse(fdate);
							Date todate = sdf1.parse(tdate);*/
							String DATE_FORMAT1 = "dd-MM-yyyy";
							java.text.SimpleDateFormat sdf2 = new java.text.SimpleDateFormat(DATE_FORMAT1);
							 
							Date fromdate = sdf2.parse(fdate);
						
							Date todate = sdf2.parse(tdate);
							Calendar c1 = Calendar.getInstance();
							String date = sdf2.format(c1.getTime());
							Date curdate = sdf2.parse(date);
							curdate.setHours(00);
							curdate.setMinutes(00);
							curdate.setSeconds(00);
							if((fromdate.equals(curdate))&&(todate.equals(curdate))){
							
								/*if((timeForm.equalsIgnoreCase("PM"))&&((hours > 3) || ((hours == 3) && (minutes > 30)))){
									designTable();

									dto = handler.getLOG(fromdate, todate,cbFromStation.getText(), cbInwardStation.getText());
															
								}
								else{*/
									
									MessageBox messageBox = new MessageBox(shell, SWT.OK);
									messageBox.setMessage("The details will be available only after 6:00 AM ");
									messageBox.setText("Alert");
									messageBox.open();                                 
								//}
							}
							else if(todate.equals(curdate)){
								designTable();                            

								/*if((timeForm.equalsIgnoreCase("PM"))&&((hours > 3) || ((hours == 3) && (minutes > 30)))){
									
									dto = handler.getLOG(fromdate, todate,cbFromStation.getText(), cbInwardStation.getText());
															
								}
								else{*/
									
									int value = todate.getDate() - 1;
									todate.setDate(value);
									dto = handler.getLOG(fromdate, todate,cbFromStation.getText(), cbInwardStation.getText());          
								//}
								
							}
							else{
								designTable();         

								dto = handler.getLOG(fromdate, todate,cbFromStation.getText(), cbInwardStation.getText());
							}
						
							if (dto != null) {
								populateInwardAnalysis(dto);
							}
						//}
					}

				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			AdminComposite.display("Report Loaded Successfully!",
					STATUS_SUCCESS, SUCCESS_FONT_COLOR);
		} 
		else if(source == cbStLRBranch ){
			handleBranchAction(cbStLRBranch, cbStLRStation);
		}else if(source == btnStGo){
			
			AdminComposite.display("Generating Report...", STATUS_SUCCESS,
					SUCCESS_FONT_COLOR);
			handleServicetax();
			
			AdminComposite.display("Report Loaded Successfully!",
					STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			
		}else if(source == btnStLRGo){
			
			AdminComposite.display("Generating Report...", STATUS_SUCCESS,
					SUCCESS_FONT_COLOR);
			handleServicetaxLR();
			
			AdminComposite.display("Report Loaded Successfully!",
					STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			
		}
		else if (source == btnExportXL) {
			try {
				handleExcel();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else if (source == btnPrint) {
			try {
				handlePrint();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		else if (source == btnExportPDF) {
			try {
				handlePDF();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		/*else if (source == btnExportTXT) {
			try {
				handleTXT();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	*/


	}

	

	private GeneralSummaryDTO[] getSundryBooking(Date fromDt, Date toDate, String branch, String station) {
		// TODO Auto-generated method stub
		GeneralSummaryDTO[] sundryBooking = null;
		try{
			Date date = BeanUtil.getThree_month_updated();
			if(fromDt.before(date)){
				System.out.println("in ddr histry-->"+date);
				sundryBooking = handler.getSundryBookingHistory(fromDt, toDate,branch, station);
			}
			else{
				sundryBooking = handler.getSundryBooking(fromDt, toDate,branch, station);				
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return sundryBooking;
	}

	/*private void fillconsignor(int noa2, int j, TableItem item) {
		// TODO Auto-generated method stub
		
		//TableItem items[] = new TableItem 
		//private void fillST(StationsDTO[] dto, TableItem item, float tft,float stx,
			//	String stn_code) {
			// TODO Auto-generated method stub
				//DecimalFormat df = new DecimalFormat("0.00");
				//float totalfreightin25 = 0;
				/*float servicetax = 0;
				float educess = 0;
				float hreducess = 0;*
				float totalst = 0;
				
				//totalst = tft - stx ;
				item.setText(2, "Sundry");
				
				
				item.setText(j, String.valueOf(noa2));
				
				/*servicetax = totalfreightin25 / 10;
				item.setText(4, df.format(servicetax));
				
				educess	= servicetax / 50;
				item.setText(5, df.format(educess));
				
				hreducess = educess / 100;
				item.setText(6, df.format(hreducess));
				
				totalst = servicetax + educess + hreducess;*
				
				//item.setText(4, df.format(tft));
				
			//}
		
		
	}*/

	/*private CustomerBusinessAnalysisDTO[] getCustomerBusinessAnalysisReport(
			boolean b, String branch, String fromdate, String todate) {
		int monthDiff = 0;
		CustomerBusinessAnalysisDTO[] bookedLr = null;
		try {
			Date curDate = new Date();
			int m1 = fromdate.get() * 12 + fromdate.getMonth();
	        int m2 = curDate.getYear() * 12 + curDate.getMonth();
	        monthDiff = m2 - m1;
		
		if(monthDiff > 3){
			//System.out.println("in ddr histry-->"+date);
			bookedLr = handler.getCustomerBusinessAnalysisReportHistory(b,branch,fromdate, todate);
		}else{
			bookedLr = handler.getCustomerBusinessAnalysisReport(b,branch,fromdate, todate);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookedLr;
		
	}*/

	private void handleServicetaxLR() {
		// TODO Auto-generated method stub
		try {

			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date fromDt = dateFormat.parse(txtStLRFrom.getText());
			Date toDate = dateFormat.parse(txtStLRTo.getText());
			StationsDTO[] utDTO = null;
			utDTO = getServicetaxLR(fromDt,toDate);
			if (tblServicetaxLR != null)
					tblServicetaxLR.removeAll();
			if (utDTO != null) {
				populateStLR(utDTO);
			} else {
				// AdminComposite.display("No Records Found",
				// STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void populateStLR(StationsDTO[] dto) throws Exception{
		// TODO Auto-generated method stub
		int len = dto.length;
		DecimalFormat df = new DecimalFormat("0.00");
		float totalfreightin25 = 0;
		float servicetax = 0;
		float educess = 0;
		float hreducess = 0;
		float totalst = 0;
		
		float tot1 = 0;
		float tot2 = 0;
		float tot3 = 0;
		/*float tot4 = 0;
		float tot5 = 0;
		float tot6 = 0;*/
		
		String branchcode = cbStLRBranch.getText();
		String Branch = branchcode;
		int index = branchcode.indexOf(" - ");
		branchcode = branchcode.substring(index + 3);
		String Stationcode = cbStLRStation.getText();
		String Station = Stationcode;
		int ind = Stationcode.indexOf(" - ");
		Stationcode = Stationcode.substring(ind + 3);
		float whSttotal = 0;
		for (int i = 0, j = 1; i < len; i++) {
			
			String stn_code = getBranch_code(dto[i].getFrom());
			
			if((Branch.equalsIgnoreCase("All"))&&(Station.equalsIgnoreCase("All"))){
				
				TableItem item = new TableItem(tblServicetaxLR, SWT.NONE);
				item.setText(0, String.valueOf(i + 1));
				item.setText(1, dto[i].getLrno());
				item.setText(2, String.valueOf(dto[i].getLrdate()));
				item.setText(3, dto[i].getFrom());
				item.setText(4, dto[i].getTo());
				item.setText(5, dto[i].getCnor());
				item.setText(6, dto[i].getCnee());
				whSttotal = dto[i].getTotalfreight() ;
				servicetax = dto[i].getServicetax();
				totalst = whSttotal - servicetax;
				tot1 = tot1 + totalst;
				item.setText(7, df.format(totalst));
				tot2 =tot2 + servicetax ; 
				item.setText(8,df.format(servicetax));
				/*totalfreightin25 = woSttotal / 4 ;
				tot2 = tot2 + totalfreightin25; 
				item.setText(8, df.format(totalfreightin25));
				servicetax = totalfreightin25 / 10;
				tot3 = tot3 + servicetax;
				item.setText(9, df.format(servicetax));
				educess	= servicetax / 50;
				tot4 = tot4 + educess;
				item.setText(10, df.format(educess));
				hreducess = educess / 100;
				tot5 = tot5 + hreducess; 
				item.setText(11, df.format(hreducess));
				totalst = servicetax + educess + hreducess;*/
				
				tot3 = tot3 + whSttotal;
				item.setText(9, df.format(whSttotal));

				j++;

			}
			else if((branchcode.equalsIgnoreCase(stn_code)) && (Station.equalsIgnoreCase("All"))) {
			
					TableItem item = new TableItem(tblServicetaxLR, SWT.NONE);
					item.setText(0, String.valueOf(i + 1));
					item.setText(1, dto[i].getLrno());
					item.setText(2, String.valueOf(dto[i].getLrdate()));
					item.setText(3, dto[i].getFrom());
					item.setText(4, dto[i].getTo());
					item.setText(5, dto[i].getCnor());
					item.setText(6, dto[i].getCnee());
					whSttotal = dto[i].getTotalfreight() ;
					servicetax = dto[i].getServicetax();
					totalst = whSttotal - servicetax;
					tot1 = tot1 + totalst;
					item.setText(7, df.format(totalst));
					tot2 =tot2 + servicetax ; 
					item.setText(8,df.format(servicetax));
					/*totalfreightin25 = woSttotal / 4 ;
					tot2 = tot2 + totalfreightin25; 
					item.setText(8, df.format(totalfreightin25));
					servicetax = totalfreightin25 / 10;
					tot3 = tot3 + servicetax;
					item.setText(9, df.format(servicetax));
					educess	= servicetax / 50;
					tot4 = tot4 + educess;
					item.setText(10, df.format(educess));
					hreducess = educess / 100;
					tot5 = tot5 + hreducess; 
					item.setText(11, df.format(hreducess));
					totalst = servicetax + educess + hreducess;*/
					
					tot3 = tot3 + whSttotal;
					item.setText(9, df.format(whSttotal));

					j++;
				}
			else if ((Stationcode.equalsIgnoreCase(dto[i].getFrom())) && (!Branch.equals(" "))) {
				
				TableItem item = new TableItem(tblServicetaxLR, SWT.NONE);
				item.setText(0, String.valueOf(i + 1));
				item.setText(1, dto[i].getLrno());
				item.setText(2, String.valueOf(dto[i].getLrdate()));
				item.setText(3, dto[i].getFrom());
				item.setText(4, dto[i].getTo());
				item.setText(5, dto[i].getCnor());
				item.setText(6, dto[i].getCnee());
				
				whSttotal = dto[i].getTotalfreight() ;
				servicetax = dto[i].getServicetax();
				totalst = whSttotal - servicetax;
				tot1 = tot1 + totalst;
				item.setText(7, df.format(totalst));
				tot2 =tot2 + servicetax ;
				item.setText(8,df.format(servicetax));
				/*totalfreightin25 = woSttotal / 4 ;
				tot2 = tot2 + totalfreightin25; 
				item.setText(8, df.format(totalfreightin25));
				servicetax = totalfreightin25 / 10;
				tot3 = tot3 + servicetax;
				item.setText(9, df.format(servicetax));
				educess	= servicetax / 50;
				tot4 = tot4 + educess;
				item.setText(10, df.format(educess));
				hreducess = educess / 100;
				tot5 = tot5 + hreducess; 
				item.setText(11, df.format(hreducess));
				totalst = servicetax + educess + hreducess;*/
				
				tot3 = tot3 + whSttotal;
				item.setText(9, df.format(whSttotal));

				j++;
			}
			
			} 
		final TableItem item1 = new TableItem(tblServicetaxLR, SWT.NONE);
		Font font1 = new Font(Display.getCurrent(), "Tahoma", 8, SWT.BOLD);
		item1.setFont(font1);
		item1.setText(6, "TOTAL");
		item1.setText(7, getRoundedValue(tot1));
		item1.setText(8, getRoundedValue(tot2));
		item1.setText(9, getRoundedValue(tot3));
		/*item1.setText(10, getRoundedValue(tot4));
		item1.setText(11, getRoundedValue(tot5));
		item1.setText(12, getRoundedValue(tot6));*/
		fillZerosOnEmpty(tblServicetaxLR);
		}
	
	

	private void populateSt(StationsDTO[] dto)throws Exception {
		// TODO Auto-generated method stub
		
		String Reptype = cbStReptype.getText();
		Boolean isConsolidated = false;
		if(Reptype.equalsIgnoreCase("Consolidated")) {
			 populateStAllBranches(tblServicetax);
			 isConsolidated = true;
		 } else{
	
			 populateStAllStations(tblServicetax);
		 }
		
		Boolean isBranch = false; 
		int len = dto.length;
		TableItem[] item = tblServicetax.getItems();
		int itemlen = item.length;
		float tft[] = new float[itemlen];
		float stx[] = new float[itemlen];
		String stn_code = "";
		
		
		
		
		for (int i = 0; i < len; i++) {
			
			for(int j = 0; j < itemlen; j++){
				
				
				
				if(isConsolidated == true) {
				 	stn_code = getBranch_code(dto[i].getStationname());
				 	isBranch = true;
				}
				else
					stn_code = dto[i].getStationname();
				
				String branchCode = item[j].getText(1);
				int index = branchCode.indexOf(" - ");
				branchCode = branchCode.substring(index + 3);
				
				if (stn_code.equalsIgnoreCase(branchCode)){
					if(isBranch == true){
						tft[j] = tft[j] + dto[i].getTotalfreight();
						stx[j] = stx[j] + dto[i].getServicetax();
						fillST(dto,item[j],tft[j],stx[j],stn_code);
						
					}
					else{
						tft[j] = dto[i].getTotalfreight() ; 
						stx[j] = dto[i].getServicetax();
						fillST(dto,item[j],tft[j],stx[j],stn_code);
						
					}
					
				}
				
			}
				
		}
		callTotal();
			
	}
	
		private void callTotal() {
		// TODO Auto-generated method stub
			TableItem[] items = tblServicetax.getItems();
			float tot1 = 0;
			float tot2 = 0;
			float tot3 = 0;
			/*float tot4 = 0;
			float tot5 = 0;
			float tot6 = 0;*/
			
			if (items != null) {
				for (int i = 0; i < items.length; i++) {
					
					if (!items[i].getText(2).equals(""))
						tot1 = tot1 + Float.parseFloat(items[i].getText(2));
					if (!items[i].getText(3).equals(""))
						tot2 = tot2 + Float.parseFloat(items[i].getText(3));
					if (!items[i].getText(4).equals(""))
						tot3 = tot3 + Float.parseFloat(items[i].getText(4));
					/*if (!items[i].getText(5).equals(""))
						tot4 = tot4 + Float.parseFloat(items[i].getText(5));
					if (!items[i].getText(6).equals(""))
						tot5 = tot5 + Float.parseFloat(items[i].getText(6));
					if (!items[i].getText(7).equals(""))
						tot6 = tot6 + Float.parseFloat(items[i].getText(7));*/
					
					
				}

			}

			final TableItem item1 = new TableItem(tblServicetax, SWT.NONE);
			Font font1 = new Font(Display.getCurrent(), "Tahoma", 8, SWT.BOLD);
			item1.setFont(font1);
			item1.setText(1, "TOTAL");
			item1.setText(2, getRoundedValue(tot1));
			item1.setText(3, getRoundedValue(tot2));
			item1.setText(4, getRoundedValue(tot3));
			/*item1.setText(5, getRoundedValue(tot4));
			item1.setText(6, getRoundedValue(tot5));
			item1.setText(7, getRoundedValue(tot6));*/
			
			fillZerosOnEmpty(tblServicetax);
	}

		private void fillST(StationsDTO[] dto, TableItem item, float tft,float stx,
			String stn_code) {
		// TODO Auto-generated method stub
			DecimalFormat df = new DecimalFormat("0.00");
			float totalfreightin25 = 0;
			/*float servicetax = 0;
			float educess = 0;
			float hreducess = 0;*/
			float totalst = 0;
			
			totalst = tft - stx ;
			item.setText(2, df.format(totalst));
			
			
			item.setText(3, df.format(stx));
			
			/*servicetax = totalfreightin25 / 10;
			item.setText(4, df.format(servicetax));
			
			educess	= servicetax / 50;
			item.setText(5, df.format(educess));
			
			hreducess = educess / 100;
			item.setText(6, df.format(hreducess));
			
			totalst = servicetax + educess + hreducess;*/
			
			item.setText(4, df.format(tft));
			
		}

	private void handleServicetax() {
		// TODO Auto-generated method stub
		
		
		try {
			
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date fromDt = dateFormat.parse(txtStFrom.getText());
			Date toDate = dateFormat.parse(txtStTo.getText());
			StationsDTO[] utDTO = null;
			utDTO = getServicetax(fromDt,toDate);
			
			if (tblServicetax != null)
				tblServicetax.removeAll();
			if (utDTO != null) {
				populateSt(utDTO);
			} else {
					// AdminComposite.display("No Records Found",
				// STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	private void populateStAllStations(Table table ) {
		// TODO Auto-generated method stub
		StationsDTO[] stations = null;
		try {
			stations = handler.getAllStations();

			int size = 0;
			if (null != stations) {
				if (table != null) {
					table.removeAll();
				}
				size = stations.length;
				
				for (int i = 0; i < size; i++) {
					TableItem item = new TableItem(table, SWT.NONE);
					item.setText(0, String.valueOf(i + 1));
					item.setText(1, stations[i].getName()+ " - "
							+ stations[i].getId());
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		
	}

	private void populateStAllBranches(Table table) {
		// TODO Auto-generated method stub
		StationsDTO[] stations = null;
		try {

			stations = handler.getAllBranches();

			int size = 0;
			if (null != stations) {
				if (table != null) {
					table.removeAll();
				}
				size = stations.length;
				for (int i = 0; i < size; i++) {

					TableItem item = new TableItem(table, SWT.NONE);
					item.setText(0, String.valueOf(i + 1));
					item.setText(1, stations[i].getName()+ " - "
							+ stations[i].getId());

				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		
	}
	

	private StationsDTO[] getServicetax(Date fromDt, Date toDate) {
		// TODO Auto-generated method stub
		//int monthDiff = 0;
		StationsDTO[] bookedLr = null;
		try {
			/*Date curDate = new Date();
			int m1 = fromDt.getYear() * 12 + fromDt.getMonth();
	        int m2 = curDate.getYear() * 12 + curDate.getMonth();
	        monthDiff = m2 - m1;*/
			Date date = BeanUtil.getThree_month_updated();
		if(fromDt.before(date)){
			System.out.println("in ddr histry-->"+date);
			bookedLr = handler.getServicetaxHistory(fromDt, toDate);
		}else{
			bookedLr = handler.getServicetax(fromDt, toDate);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookedLr;
	}
	
	
	
	private StationsDTO[] getServicetaxLR(Date fromDt, Date toDate) {
		// TODO Auto-generated method stub
		//int monthDiff = 0;
		StationsDTO[] bookedLr = null;
		try {
			/*Date curDate = new Date();
			int m1 = fromDt.getYear() * 12 + fromDt.getMonth();
	        int m2 = curDate.getYear() * 12 + curDate.getMonth();
	        monthDiff = m2 - m1;*/
			Date date = BeanUtil.getThree_month_updated();
		if(fromDt.before(date)){
			System.out.println("in ddr histry-->"+date);
			bookedLr = handler.getServicetaxLRHistory(fromDt, toDate);
		}else{
			bookedLr = handler.getServicetaxLR(fromDt, toDate);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookedLr;
	}
	
	
	private GeneralSummaryDTO[] getDailyBooking(Date fromDt, Date toDate) {
		//int monthDiff = 0;
		GeneralSummaryDTO[] bookedLr = null;
		try {
			/*Date curDate = new Date();
			int m1 = fromDt.getYear() * 12 + fromDt.getMonth();
	        int m2 = curDate.getYear() * 12 + curDate.getMonth();
	        monthDiff = m2 - m1;*/
			Date date = BeanUtil.getThree_month_updated();
		if(fromDt.before(date)){
			System.out.println("in ddr histry-->"+date);
			bookedLr = handler.getDailyBookingHistory(fromDt, toDate);
		}else{
			bookedLr = handler.getDailyBooking(fromDt, toDate);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookedLr;
		
	}

	private void handlePrint() throws Exception{

		String selectedTab = tabReport.getSelection()[0].getText();
		if (selectedTab.equalsIgnoreCase(DD_EXTRA)) {
			ArrayList<DDRDTO> list = null;
			String[] value = null;
			value = getDDEHeading();
			list = getDDETable();
			handler.preparePrint(list, "DDExtra", DDE_EXCEL_JRXML, null,value);
		} else if (selectedTab.equalsIgnoreCase(RS)) {
			ArrayList<RemittanceShortageDTO> list = null;
			String[] value = null;
			list = getRSTable();
			value = getRSTHeading();
			handler.preparePrint(list, "RemittanceShortage", RS_EXCEL_JRXML, null,value);
		} else if (selectedTab.equalsIgnoreCase(BPA)) {
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			list = getBPATable(tblBPA);
			value = getBPAHeading();
			//String[] param = { cbBPAChoice.getText() };
			handler.preparePrint(list, "BPA", BPA_EXCEL_JRXML, null,value);
		} else if (selectedTab.equalsIgnoreCase(CCC_SUMMARY_TAB)) {
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			list = getBPATable(tblCCCSummary);
			value = getCCCSummaryHeading(tblCCCSummary);
			handler.preparePrint(list, "CCSummary", CCSUMMARY_EXCEL_JRXML, null,value);
		} else if (selectedTab.equalsIgnoreCase(UT_TOPAY)) {
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			if (cbUTReportType.getText().equalsIgnoreCase("Summary")) {
				list = getBPATable(tblUndeliveredTopay);
				value = getUttopayHeading(tblUndeliveredTopay);
				handler.preparePrint(list, "Undelivered_Topay", UT_EXCEL_JRXML, null,value);
			} else {
				value = getUttopayHeading(tblUndeliveredTopay);
				list = getBPATable(tblUndeliveredTopayDet);
				handler.preparePrint(list, "Undelivered_Topay_Detailed",
						UT_DET_EXCEL_JRXML, null,value);
			}
		} else if (selectedTab.equalsIgnoreCase(DAILY_BOOKING_TAB)) {
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			String[] dates  = null;
			list = getBPATable(tblDailyBookingAnalysis);
			value = getDailybookingHeading(tblDailyBookingAnalysis);
			dates = getDaillybookingDates(tblDailyBookingAnalysis);
			handler.preparePrint(list, "Daily_Booking", DBA_EXCEL_JRXML, dates,value);
		} else if (selectedTab.equalsIgnoreCase(COUNTER_REPORT)) {
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			list = getBPATable(tblCounterReport);
			value = getCounterHeading(tblCounterReport);
			if (cbCRCounterSet.getText().equalsIgnoreCase("LR Set")) {
				
				handler.preparePrint(list, "CounterReport", COUNTER_EXCEL_JRXML, null,value);
			} else {
				
				handler.preparePrint(list, "CounterReport", COUNTER_MISC_EXCEL_JRXML, null,value);
			}
		} else if (selectedTab.equalsIgnoreCase(SUNDRY_BOOKING_TAB)) {
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			list = getBPATable(tblSundryBookAnalysis);
			value = getSundryHeading(tblSundryBookAnalysis);
			handler.preparePrint(list, "Sundry_Booking", SBA_EXCEL_JRXML, null,value);
		} else if (selectedTab.equalsIgnoreCase(UPD_READY)) {
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			value = getUpdreadyHeading(tblUPDReady);
			list = getBPATable(tblUPDReady);
			handler.preparePrint(list, "UPD_Ready", UPD_EXCEL_JRXML, null,value);
		} else if (selectedTab.equalsIgnoreCase(MISSING_CUSTOMER)) {
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			list = getBPATable(tblMissingCustomer);
			value = getMissingCustHeading(tblMissingCustomer);
			handler.preparePrint(list, "Missing_Customer", MISSINGCUSTOMER_EXCEL_JRXML,
					null,value);
		}else if(selectedTab.equalsIgnoreCase(CANCELLEDLR)){
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			if (cbCLReportType.getText().equalsIgnoreCase("Detailed")) {
				list = getBPATable(tblCancelledLR);
				value = getCancelledHeading(tblCancelledLR);
				TableColumn[] col = tblCancelledLR.getColumns();	
				if(col != null){
					int len = col.length;
					String param[] = new String[len];
					for(int i = 0; i < len; i++){
						param[i] = col[i].getText();
					}
					handler.preparePrint(list, "Cancelled LRs", CANCELLEDLRS_EXCEL_JRXML, param,value);
				}
			}
			else
			{
				list = getBPATable(tblCancelledLR);
				value = getCancelledHeading(tblCancelledLR);
				TableColumn[] col = tblCancelledLR.getColumns();
				if(col != null){
					int len = col.length;
					String param[] = new String[len];
					for(int i = 0; i < len; i++){
						param[i] = col[i].getText();
					}
					handler.preparePrint(list, "Cancelled LRs", CANCELLEDLRS_EXCEL_JRXML, param,value);
				}
			}
		}else if(selectedTab.equalsIgnoreCase(FOC)){			
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			if (cbFOCLrReportType.getText().equalsIgnoreCase("Detailed")) {
				list = getBPATable(tblFOCLr);
				value = getFocHeading(tblFOCLr);
				TableColumn[] col = tblFOCLr.getColumns();
				if(col != null){
					int len = col.length;
					String param[] = new String[len];
					for(int i = 0; i < len; i++){
						param[i] = col[i].getText();
					}
					handler.preparePrint(list, "FOC LRs", FOCLRS_EXCEL_JRXML, param,value);
				}
			}
			else
			{
				list = getBPATable(tblFOCLr);
				value = getFocHeading(tblFOCLr);
				TableColumn[] col = tblFOCLr.getColumns();
				if(col != null){
					int len = col.length;
					String param[] = new String[len];
					for(int i = 0; i < len; i++){
						param[i] = col[i].getText();
					}
					handler.preparePrint(list, "FOC LRs", FOCLRS_EXCEL_JRXML, param,value);
				}
			}
			
		}else if(selectedTab.equalsIgnoreCase(OPENLR)){
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			list = getBPATable(tblOpenLr);
			value = getOpenlrHeading(tblOpenLr);
			handler.preparePrint(list, "OpenLr", OPENLR_EXCEL_JRXML,
					null,value);
		}else if(selectedTab.equalsIgnoreCase(INTER_INTRA)){
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			list = getIISTable(tblIIStates);
			value = getIISHeading(tblIIStates);
			handler.preparePrint(list, "Inter_Intra", IIS_EXCEL_JRXML,null,value);
			
		}else if(selectedTab.equalsIgnoreCase(CONSINORBA)){
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			list = getBPATable(tblCnorBookingAnalysis);
			value = getconsinorbaHeading(tblCnorBookingAnalysis);
			TableColumn[] col = tblCnorBookingAnalysis.getColumns();
			if(col != null){
				int len = col.length;
				String param[] = new String[len];
				for(int i = 0; i < len; i++){
					param[i] = col[i].getText();
				}
				handler.preparePrint(list, "CnorBookingAnalysis", CNORBA_EXCEL_JRXML, param,value);
			}			
		}else if(selectedTab.equalsIgnoreCase(CONSINEEBA)){
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			list = getBPATable(tblCneeBookingAnalysis);
			value = getConsineebaHeading(tblCneeBookingAnalysis);
			TableColumn[] col = tblCneeBookingAnalysis.getColumns();
			if(col != null){
				int len = col.length;
				String param[] = new String[len];
				for(int i = 0; i < len; i++){
					param[i] = col[i].getText();
				}
				handler.preparePrint(list, "CneeBookingAnalysis", CNEEBA_EXCEL_JRXML, param,value);
			}			
		}else if(selectedTab.equalsIgnoreCase(MARKET_VEHICLE)){
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			list = getBPATable(tblMarketVehicleUtilisation);
			value = getMarketvehicleHeading(tblMarketVehicleUtilisation);
			TableColumn[] col = tblMarketVehicleUtilisation.getColumns();
			if(col != null){
				int len = col.length;
				String param[] = new String[len];
				for(int i = 0; i < len; i++){
					param[i] = col[i].getText();
				}
				handler.preparePrint(list, "Market Vehicle", MARKET_VEHICLE_EXCEL_JRXML, param,value);
			}			
		}else if(selectedTab.equalsIgnoreCase(LOG)){
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			list = getLOGTable(tblInwardAnalysis);
			value = getLOGHeading(tblInwardAnalysis);
			TableColumn[] col = tblInwardAnalysis.getColumns();
			if(col != null){
				int len = col.length;
				String param[] = new String[len];
				for(int i = 0; i < len; i++){
					param[i] = col[i].getText();
					
				}
				handler.preparePrint(list, "LeftOverGoods", LOG_VEHICLE_EXCEL_JRXML, param,value);
			}			
		}else if(selectedTab.equalsIgnoreCase(DRS)){
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			if (cbDRSAReportType.getText().equalsIgnoreCase("Summary")) {
				list = getLOGTable(tblDRSAttendance);
				value = getDRSHeading(tblDRSAttendance);
				TableColumn[] col = tblDRSAttendance.getColumns();
				if(col != null){
					int len = col.length;
					String param[] = new String[len];
					for(int i = 0; i < len; i++){
						param[i] = col[i].getText();
					}
					handler.preparePrint(list, "DRS Attendance", DRS_VEHICLE_EXCEL_JRXML, param,value);
				}
			}
			else
			{
				value = getDRSHeading(tblDRSAttendance);
				list = getLOGTable(tblDRSAttendance);
				TableColumn[] col = tblDRSAttendance.getColumns();
				if(col != null){
					int len = col.length;
					String param[] = new String[len];
					for(int i = 0; i < len; i++){
						param[i] = col[i].getText();
					}
					handler.preparePrint(list, "DRS Attendance", DRS_VEHICLE_EXCEL_JRXML, param,value);
				}
				
			}
		}
		
		else if(selectedTab.equalsIgnoreCase(Service_Tax)){
			ArrayList<StationsDTO> list = null;
			String[] value = null;
			list = getServicetaxTable(tblServicetax);
			value = getServicetaxHeading(tblServicetax);
			handler.preparePrint(list, "Service Tax Annexure", SERVICETAX_JRXML,
					null,value);
		}
		else if (selectedTab.equalsIgnoreCase(Service_Tax_LR)) {
			ArrayList<StationsDTO> list = null;
			String[] value = null;
			value = getServicetaxLRHeading(tblServicetaxLR);
			list = getServicetaxLRTable(tblServicetaxLR);
			handler.preparePrint(list, "Service Tax Annexure - LR Wise", SERVICETAX_LR_JRXML, null,value);
		}

	}

	private String[] getDaillybookingDates(Table tblDailyBookingAnalysis2) {
		String list[] = new String[7];
		
		list[0] =colDBADate1.getText();
		list[1] =colDBADate2.getText();
		list[2] =colDBADate3.getText();
		list[3] =colDBADate4.getText();
		list[4] =colDBADate5.getText();
		list[5] =colDBADate6.getText();
		list[6] =colDBADate7.getText();
			
		return list;
	}

	private void handleExcel() throws Exception {
		String selectedTab = tabReport.getSelection()[0].getText();
		if (selectedTab.equalsIgnoreCase(DD_EXTRA)) {
			ArrayList<DDRDTO> list = null;
			String[] value = null;
			value = getDDEHeading();
			list = getDDETable();
			prepareExcel(list, "DDExtra", DDE_EXCEL_JRXML, null,value);
		} else if (selectedTab.equalsIgnoreCase(RS)) {
			ArrayList<RemittanceShortageDTO> list = null;
			String[] value = null;
			list = getRSTable();
			value = getRSTHeading();
			prepareExcel(list, "RemittanceShortage", RS_EXCEL_JRXML, null,value);
		} else if (selectedTab.equalsIgnoreCase(BPA)) {
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			list = getBPATable(tblBPA);
			value = getBPAHeading();
			//String[] param = { cbBPAChoice.getText() };
			prepareExcel(list, "BPA", BPA_EXCEL_JRXML, null,value);
		} else if (selectedTab.equalsIgnoreCase(CCC_SUMMARY_TAB)) {
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			list = getBPATable(tblCCCSummary);
			value = getCCCSummaryHeading(tblCCCSummary);
			prepareExcel(list, "CCSummary", CCSUMMARY_EXCEL_JRXML, null,value);
		} else if (selectedTab.equalsIgnoreCase(UT_TOPAY)) {
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			if (cbUTReportType.getText().equalsIgnoreCase("Summary")) {
				list = getBPATable(tblUndeliveredTopay);
				value = getUttopayHeading(tblUndeliveredTopay);
				prepareExcel(list, "Undelivered_Topay", UT_EXCEL_JRXML, null,value);
			} else {
				value = getUttopayHeading(tblUndeliveredTopay);
				list = getBPATable(tblUndeliveredTopayDet);
				prepareExcel(list, "Undelivered_Topay_Detailed",
						UT_DET_EXCEL_JRXML, null,value);
			}
		} else if (selectedTab.equalsIgnoreCase(DAILY_BOOKING_TAB)) {
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			String[] dates = null;
			list = getBPATable(tblDailyBookingAnalysis);
			value = getDailybookingHeading(tblDailyBookingAnalysis);
			dates = getDaillybookingDates(tblDailyBookingAnalysis);
			prepareExcel(list, "Daily_Booking", DBA_EXCEL_JRXML, dates,value);
		} else if (selectedTab.equalsIgnoreCase(COUNTER_REPORT)) {
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			list = getBPATable(tblCounterReport);
			value = getCounterHeading(tblCounterReport);
			if (cbCRCounterSet.getText().equalsIgnoreCase("LR Set")) {
				
				prepareExcel(list, "CounterReport", COUNTER_EXCEL_JRXML, null,value);
			} else {
				
				prepareExcel(list, "CounterReport", COUNTER_MISC_EXCEL_JRXML, null,value);
			}
		} else if (selectedTab.equalsIgnoreCase(SUNDRY_BOOKING_TAB)) {
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			list = getBPATable(tblSundryBookAnalysis);
			value = getSundryHeading(tblSundryBookAnalysis);
			prepareExcel(list, "Sundry_Booking", SBA_EXCEL_JRXML, null,value);
		} else if (selectedTab.equalsIgnoreCase(UPD_READY)) {
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			value = getUpdreadyHeading(tblUPDReady);
			list = getBPATable(tblUPDReady);
			prepareExcel(list, "UPD_Ready", UPD_EXCEL_JRXML, null,value);
		} else if (selectedTab.equalsIgnoreCase(MISSING_CUSTOMER)) {
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			list = getBPATable(tblMissingCustomer);
			value = getMissingCustHeading(tblMissingCustomer);
			prepareExcel(list, "Missing_Customer", MISSINGCUSTOMER_EXCEL_JRXML,
					null,value);
		}else if(selectedTab.equalsIgnoreCase(CANCELLEDLR)){
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			if (cbCLReportType.getText().equalsIgnoreCase("Detailed")) {
				list = getBPATable(tblCancelledLR);
				value = getCancelledHeading(tblCancelledLR);
				TableColumn[] col = tblCancelledLR.getColumns();	
				if(col != null){
					int len = col.length;
					String param[] = new String[len];
					for(int i = 0; i < len; i++){
						param[i] = col[i].getText();
					}
					prepareExcel(list, "Cancelled LRs", CANCELLEDLRS_EXCEL_JRXML, param,value);
				}
			}
			else
			{
				list = getBPATable(tblCancelledLR);
				value = getCancelledHeading(tblCancelledLR);
				TableColumn[] col = tblCancelledLR.getColumns();
				if(col != null){
					int len = col.length;
					String param[] = new String[len];
					for(int i = 0; i < len; i++){
						param[i] = col[i].getText();
					}
					prepareExcel(list, "Cancelled LRs", CANCELLEDLRS_EXCEL_JRXML, param,value);
				}
			}
		}else if(selectedTab.equalsIgnoreCase(FOC)){			
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			if (cbFOCLrReportType.getText().equalsIgnoreCase("Detailed")) {
				list = getBPATable(tblFOCLr);
				value = getFocHeading(tblFOCLr);
				TableColumn[] col = tblFOCLr.getColumns();
				if(col != null){
					int len = col.length;
					String param[] = new String[len];
					for(int i = 0; i < len; i++){
						param[i] = col[i].getText();
					}
					prepareExcel(list, "FOC LRs", FOCLRS_EXCEL_JRXML, param,value);
				}
			}
			else
			{
				list = getBPATable(tblFOCLr);
				value = getFocHeading(tblFOCLr);
				TableColumn[] col = tblFOCLr.getColumns();
				if(col != null){
					int len = col.length;
					String param[] = new String[len];
					for(int i = 0; i < len; i++){
						param[i] = col[i].getText();
					}
					prepareExcel(list, "FOC LRs", FOCLRS_EXCEL_JRXML, param,value);
				}
			}
			
		}else if(selectedTab.equalsIgnoreCase(OPENLR)){
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			list = getBPATable(tblOpenLr);
			value = getOpenlrHeading(tblOpenLr);
			prepareExcel(list, "OpenLr", OPENLR_EXCEL_JRXML,
					null,value);
		}else if(selectedTab.equalsIgnoreCase(INTER_INTRA)){
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			list = getIISTable(tblIIStates);
			value = getIISHeading(tblIIStates);
			prepareExcel(list, "Inter_Intra", IIS_EXCEL_JRXML,null,value);
			
		}else if(selectedTab.equalsIgnoreCase(CONSINORBA)){
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			list = getBPATable(tblCnorBookingAnalysis);
			value = getconsinorbaHeading(tblCnorBookingAnalysis);
			TableColumn[] col = tblCnorBookingAnalysis.getColumns();
			if(col != null){
				int len = col.length;
				String param[] = new String[len];
				for(int i = 0; i < len; i++){
					param[i] = col[i].getText();
				}
				prepareExcel(list, "CnorBookingAnalysis", CNORBA_EXCEL_JRXML, param,value);
			}			
		}else if(selectedTab.equalsIgnoreCase(CONSINEEBA)){
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			list = getBPATable(tblCneeBookingAnalysis);
			value = getConsineebaHeading(tblCneeBookingAnalysis);
			TableColumn[] col = tblCneeBookingAnalysis.getColumns();
			if(col != null){
				int len = col.length;
				String param[] = new String[len];
				for(int i = 0; i < len; i++){
					param[i] = col[i].getText();
				}
				prepareExcel(list, "CneeBookingAnalysis", CNEEBA_EXCEL_JRXML, param,value);
			}			
		}else if(selectedTab.equalsIgnoreCase(MARKET_VEHICLE)){
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			list = getBPATable(tblMarketVehicleUtilisation);
			value = getMarketvehicleHeading(tblMarketVehicleUtilisation);
			TableColumn[] col = tblMarketVehicleUtilisation.getColumns();
			if(col != null){
				int len = col.length;
				String param[] = new String[len];
				for(int i = 0; i < len; i++){
					param[i] = col[i].getText();
				}
				prepareExcel(list, "Market Vehicle", MARKET_VEHICLE_EXCEL_JRXML, param,value);
			}			
			
		}else if(selectedTab.equalsIgnoreCase(LOG)){
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			list = getLOGTable(tblInwardAnalysis);
			value = getLOGHeading(tblInwardAnalysis);
			TableColumn[] col = tblInwardAnalysis.getColumns();
			if(col != null){
				int len = col.length;
				String param[] = new String[len];
				for(int i = 0; i < len; i++){
					param[i] = col[i].getText();
					
				}
				prepareExcel(list, "LeftOverGoods", LOG_VEHICLE_EXCEL_JRXML, param,value);
			}			
		}else if(selectedTab.equalsIgnoreCase(DRS)){
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			if (cbDRSAReportType.getText().equalsIgnoreCase("Summary")) {
				list = getLOGTable(tblDRSAttendance);
				value = getDRSHeading(tblDRSAttendance);
				TableColumn[] col = tblDRSAttendance.getColumns();
				if(col != null){
					int len = col.length;
					String param[] = new String[len];
					for(int i = 0; i < len; i++){
						param[i] = col[i].getText();
					}
					prepareExcel(list, "DRS Attendance", DRS_VEHICLE_EXCEL_JRXML, param,value);
				}
			}
			else
			{
				value = getDRSHeading(tblDRSAttendance);
				list = getLOGTable(tblDRSAttendance);
				TableColumn[] col = tblDRSAttendance.getColumns();
				if(col != null){
					int len = col.length;
					String param[] = new String[len];
					for(int i = 0; i < len; i++){
						param[i] = col[i].getText();
					}
					prepareExcel(list, "DRS Attendance", DRS_VEHICLE_EXCEL_JRXML, param,value);
				}
				
			}
		}else if(selectedTab.equalsIgnoreCase(Service_Tax)){
			ArrayList<StationsDTO> list = null;
			String[] value = null;
			list = getServicetaxTable(tblServicetax);
			value = getServicetaxHeading(tblServicetax);
			prepareExcel(list, "Service Tax Annexure", SERVICETAX_JRXML,
					null,value);
		}
		else if (selectedTab.equalsIgnoreCase(Service_Tax_LR)) {
			ArrayList<StationsDTO> list = null;
			String[] value = null;
			value = getServicetaxLRHeading(tblServicetaxLR);
			list = getServicetaxLRTable(tblServicetaxLR);
			prepareExcel(list, "Service Tax Annexure - LR Wise", SERVICETAX_LR_JRXML, null,value);
		}
		

	}
	
		private ArrayList<StationsDTO> getServicetaxLRTable(Table tblServicetaxLR) {
		// TODO Auto-generated method stub
			StationsDTO dto = null;
			ArrayList<StationsDTO> list = new ArrayList<StationsDTO>();
			int length = 0;

			TableItem[] items = tblServicetaxLR.getItems();
			if (items != null) {
				length = items.length;
			}
			if (length > 0) {
				for (int i = 0; i < length; i++) {
					dto = new StationsDTO();
					
					dto.setLrno(items[i].getText(1));
					
					dto.setLrdate(items[i].getText(2));
					
					dto.setFrom(items[i].getText(3)); 
					
					dto.setTo(items[i].getText(4));
					
					dto.setCnor(items[i].getText(5));
					
					dto.setCnee(items[i].getText(6));
					
					if (!items[i].getText(7).equals(""))
						dto.setTotalfreight(Float.parseFloat(items[i].getText(7)));
					
					/*if (!items[i].getText(8).equals(""))
						dto.setTotalfreightin25(Float.parseFloat(items[i].getText(8)));*/
					
					if (!items[i].getText(8).equals(""))
						dto.setServicetax(Float.parseFloat(items[i].getText(8)));
					
					/*if (!items[i].getText(10).equals(""))
						dto.setEducess(Float.parseFloat(items[i].getText(10)));
					
					if (!items[i].getText(11).equals(""))
						dto.setHreducess(Float.parseFloat(items[i].getText(11)));*/
					
					if (!items[i].getText(9).equals(""))
						dto.setTotalst(Float.parseFloat(items[i].getText(9)));
					
					list.add(dto);
				}
			}

			return list;
		
	}

		private ArrayList<StationsDTO> getServicetaxTable(Table tblServicetax) {
		// TODO Auto-generated method stub
			StationsDTO dto = null;
			ArrayList<StationsDTO> list = new ArrayList<StationsDTO>();
			int length = 0;

			TableItem[] items = tblServicetax.getItems();
			if (items != null) {
				length = items.length;
			}
			if (length > 0) {
				for (int i = 0; i < length; i++) {
					dto = new StationsDTO();
					
					dto.setStationname(items[i].getText(1));

					if (!items[i].getText(2).equals(""))
						dto.setTotalfreight(Float.parseFloat(items[i].getText(2))); 
					
					/*if (!items[i].getText(3).equals(""))
						dto.setTotalfreightin25(Float.parseFloat(items[i].getText(3)));*/
					
					if (!items[i].getText(3).equals(""))
						dto.setServicetax(Float.parseFloat(items[i].getText(3)));
					
					/*if (!items[i].getText(5).equals(""))
						dto.setEducess(Float.parseFloat(items[i].getText(5)));
					
					if (!items[i].getText(6).equals(""))
						dto.setHreducess(Float.parseFloat(items[i].getText(6)));*/					
					
					if (!items[i].getText(4).equals(""))
						dto.setTotalst(Float.parseFloat(items[i].getText(4)));
						
					list.add(dto);
				}
			}

			return list;
	}

		/*private void handleTXT() throws Exception {
		String selectedTab = tabReport.getSelection()[0].getText();
		if (selectedTab.equalsIgnoreCase(DD_EXTRA)) {
			ArrayList<DDRDTO> list = null;
			String[] value = null;
			value = getDDEHeading();
			list = getDDETable();
			prepareTXT(list, "DDExtra", DDE_EXCEL_JRXML, null,value);
		} else if (selectedTab.equalsIgnoreCase(RS)) {
			ArrayList<RemittanceShortageDTO> list = null;
			String[] value = null;
			list = getRSTable();
			value = getRSTHeading();
			prepareTXT(list, "RemittanceShortage", RS_EXCEL_JRXML, null,value);
		} else if (selectedTab.equalsIgnoreCase(BPA)) {
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			list = getBPATable(tblBPA);
			value = getBPAHeading();
			String[] param = { cbBPAChoice.getText() };
			prepareTXT(list, "BPA", BPA_EXCEL_JRXML, param,value);
		} else if (selectedTab.equalsIgnoreCase(CCC_SUMMARY_TAB)) {
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			list = getBPATable(tblCCCSummary);
			value = getCCCSummaryHeading(tblCCCSummary);
			prepareTXT(list, "CCSummary", CCSUMMARY_EXCEL_JRXML, null,value);
		} else if (selectedTab.equalsIgnoreCase(UT_TOPAY)) {
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			if (cbUTReportType.getText().equalsIgnoreCase("Summary")) {
				list = getBPATable(tblUndeliveredTopay);
				value = getUttopayHeading(tblUndeliveredTopay);
				prepareTXT(list, "Undelivered_Topay", UT_EXCEL_JRXML, null,value);
			} else {
				value = getUttopayHeading(tblUndeliveredTopay);
				list = getBPATable(tblUndeliveredTopayDet);
				prepareTXT(list, "Undelivered_Topay_Detailed",
						UT_DET_EXCEL_JRXML, null,value);
			}
		} else if (selectedTab.equalsIgnoreCase(DAILY_BOOKING_TAB)) {
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			list = getBPATable(tblDailyBookingAnalysis);
			value = getDailybookingHeading(tblDailyBookingAnalysis);
			prepareTXT(list, "Daily_Booking", DBA_EXCEL_JRXML, null,value);
		} else if (selectedTab.equalsIgnoreCase(COUNTER_REPORT)) {
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			//if (cbCRCounterSet.getText().equalsIgnoreCase("LR Set")) {
				list = getBPATable(tblCounterReport);
				value = getCounterHeading(tblCounterReport);
				prepareTXT(list, "CounterReport", COUNTER_EXCEL_JRXML, null,value);
			/*} else {
				list = getBPATable(tblCounterReport);
				prepareExcel(list, "CounterReport", COUNTER_EXCEL_JRXML, null);
			}* /
		} else if (selectedTab.equalsIgnoreCase(SUNDRY_BOOKING_TAB)) {
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			list = getBPATable(tblSundryBookAnalysis);
			value = getSundryHeading(tblSundryBookAnalysis);
			prepareTXT(list, "Sundry_Booking", SBA_EXCEL_JRXML, null,value);
		} else if (selectedTab.equalsIgnoreCase(UPD_READY)) {
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			value = getUpdreadyHeading(tblUPDReady);
			list = getBPATable(tblUPDReady);
			prepareTXT(list, "UPD_Ready", UPD_EXCEL_JRXML, null,value);
		} else if (selectedTab.equalsIgnoreCase(MISSING_CUSTOMER)) {
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			list = getBPATable(tblMissingCustomer);
			value = getMissingCustHeading(tblMissingCustomer);
			prepareTXT(list, "Missing_Customer", MISSINGCUSTOMER_EXCEL_JRXML,
					null,value);
		}else if(selectedTab.equalsIgnoreCase(CANCELLEDLR)){
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			if (cbCLReportType.getText().equalsIgnoreCase("Detailed")) {
				list = getBPATable(tblCancelledLR);
				value = getCancelledHeading(tblCancelledLR);
				TableColumn[] col = tblCancelledLR.getColumns();	
				if(col != null){
					int len = col.length;
					String param[] = new String[len];
					for(int i = 0; i < len; i++){
						param[i] = col[i].getText();
					}
					prepareTXT(list, "Cancelled LRs", CANCELLEDLRS_EXCEL_JRXML, param,value);
				}
			}
			else
			{
				list = getBPATable(tblCancelledLR);
				value = getCancelledHeading(tblCancelledLR);
				TableColumn[] col = tblCancelledLR.getColumns();
				if(col != null){
					int len = col.length;
					String param[] = new String[len];
					for(int i = 0; i < len; i++){
						param[i] = col[i].getText();
					}
					prepareTXT(list, "Cancelled LRs", CANCELLEDLRS_EXCEL_JRXML, param,value);
				}
			}
		}else if(selectedTab.equalsIgnoreCase(FOC)){			
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			if (cbFOCLrReportType.getText().equalsIgnoreCase("Detailed")) {
				list = getBPATable(tblFOCLr);
				value = getFocHeading(tblFOCLr);
				TableColumn[] col = tblFOCLr.getColumns();
				if(col != null){
					int len = col.length;
					String param[] = new String[len];
					for(int i = 0; i < len; i++){
						param[i] = col[i].getText();
					}
					prepareTXT(list, "FOC LRs", FOCLRS_EXCEL_JRXML, param,value);
				}
			}
			else
			{
				list = getBPATable(tblFOCLr);
				value = getFocHeading(tblFOCLr);
				TableColumn[] col = tblFOCLr.getColumns();
				if(col != null){
					int len = col.length;
					String param[] = new String[len];
					for(int i = 0; i < len; i++){
						param[i] = col[i].getText();
					}
					prepareTXT(list, "FOC LRs", FOCLRS_EXCEL_JRXML, param,value);
				}
			}
			
		}else if(selectedTab.equalsIgnoreCase(OPENLR)){
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			list = getBPATable(tblOpenLr);
			value = getOpenlrHeading(tblOpenLr);
			prepareTXT(list, "OpenLr", OPENLR_EXCEL_JRXML,
					null,value);
		}else if(selectedTab.equalsIgnoreCase(INTER_INTRA)){
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			list = getIISTable(tblIIStates);
			value = getIISHeading(tblIIStates);
			prepareTXT(list, "Inter_Intra", IIS_EXCEL_JRXML,null,value);
			
		}else if(selectedTab.equalsIgnoreCase(CONSINORBA)){
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			list = getBPATable(tblCnorBookingAnalysis);
			value = getconsinorbaHeading(tblCnorBookingAnalysis);
			TableColumn[] col = tblCnorBookingAnalysis.getColumns();
			if(col != null){
				int len = col.length;
				String param[] = new String[len];
				for(int i = 0; i < len; i++){
					param[i] = col[i].getText();
				}
				prepareTXT(list, "CnorBookingAnalysis", CNORBA_EXCEL_JRXML, param,value);
			}			
		}else if(selectedTab.equalsIgnoreCase(CONSINEEBA)){
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			list = getBPATable(tblCneeBookingAnalysis);
			value = getConsineebaHeading(tblCneeBookingAnalysis);
			TableColumn[] col = tblCneeBookingAnalysis.getColumns();
			if(col != null){
				int len = col.length;
				String param[] = new String[len];
				for(int i = 0; i < len; i++){
					param[i] = col[i].getText();
				}
				prepareTXT(list, "CneeBookingAnalysis", CNEEBA_EXCEL_JRXML, param,value);
			}			
		}else if(selectedTab.equalsIgnoreCase(MARKET_VEHICLE)){
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			list = getBPATable(tblMarketVehicleUtilisation);
			value = getMarketvehicleHeading(tblMarketVehicleUtilisation);
			TableColumn[] col = tblMarketVehicleUtilisation.getColumns();
			if(col != null){
				int len = col.length;
				String param[] = new String[len];
				for(int i = 0; i < len; i++){
					param[i] = col[i].getText();
				}
				prepareTXT(list, "Market Vehicle", MARKET_VEHICLE_EXCEL_JRXML, param,value);
			}			
		}else if(selectedTab.equalsIgnoreCase(LOG)){
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			list = getLOGTable(tblInwardAnalysis);
			value = getLOGHeading(tblInwardAnalysis);
			TableColumn[] col = tblInwardAnalysis.getColumns();
			if(col != null){
				int len = col.length;
				String param[] = new String[len];
				for(int i = 0; i < len; i++){
					param[i] = col[i].getText();
					
				}
				prepareTXT(list, "LeftOverGoods", LOG_VEHICLE_EXCEL_JRXML, param,value);
			}			
		}else if(selectedTab.equalsIgnoreCase(DRS)){
			ArrayList<TableDecorator> list = null;
			String[] value = null;
			if (cbDRSAReportType.getText().equalsIgnoreCase("Summary")) {
				list = getLOGTable(tblDRSAttendance);
				value = getDRSHeading(tblDRSAttendance);
				TableColumn[] col = tblDRSAttendance.getColumns();
				if(col != null){
					int len = col.length;
					String param[] = new String[len];
					for(int i = 0; i < len; i++){
						param[i] = col[i].getText();
					}
					prepareTXT(list, "DRS Attendance", DRS_VEHICLE_EXCEL_JRXML, param,value);
				}
			}
			else
			{
				value = getDRSHeading(tblDRSAttendance);
				list = getLOGTable(tblDRSAttendance);
				TableColumn[] col = tblDRSAttendance.getColumns();
				if(col != null){
					int len = col.length;
					String param[] = new String[len];
					for(int i = 0; i < len; i++){
						param[i] = col[i].getText();
					}
					prepareTXT(list, "DRS Attendance", DRS_VEHICLE_EXCEL_JRXML, param,value);
				}
				
			}
		}

	}*/

	 private void handlePDF() throws Exception {
		 
		 String selectedTab = tabReport.getSelection()[0].getText();
			if (selectedTab.equalsIgnoreCase(DD_EXTRA)) {
				ArrayList<DDRDTO> list = null;
				String[] value = null;
				value = getDDEHeading();
				list = getDDETable();
				preparePDF(list, "DDExtra", DDE_EXCEL_JRXML, null,value);
			} else if (selectedTab.equalsIgnoreCase(RS)) {
				ArrayList<RemittanceShortageDTO> list = null;
				String[] value = null;
				list = getRSTable();
				value = getRSTHeading();
				preparePDF(list, "RemittanceShortage", RS_EXCEL_JRXML, null,value);
			} else if (selectedTab.equalsIgnoreCase(BPA)) {
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				list = getBPATable(tblBPA);
				value = getBPAHeading();
				//String[] param = { cbBPAChoice.getText() };
				preparePDF(list, "BPA", BPA_EXCEL_JRXML, null,value);
			} else if (selectedTab.equalsIgnoreCase(CCC_SUMMARY_TAB)) {
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				list = getBPATable(tblCCCSummary);
				value = getCCCSummaryHeading(tblCCCSummary);
				preparePDF(list, "CCSummary", CCSUMMARY_EXCEL_JRXML, null,value);
			} else if (selectedTab.equalsIgnoreCase(UT_TOPAY)) {
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				if (cbUTReportType.getText().equalsIgnoreCase("Summary")) {
					list = getBPATable(tblUndeliveredTopay);
					value = getUttopayHeading(tblUndeliveredTopay);
					preparePDF(list, "Undelivered_Topay", UT_EXCEL_JRXML, null,value);
				} else {
					value = getUttopayHeading(tblUndeliveredTopay);
					list = getBPATable(tblUndeliveredTopayDet);
					preparePDF(list, "Undelivered_Topay_Detailed",
							UT_DET_EXCEL_JRXML, null,value);
				}
			} else if (selectedTab.equalsIgnoreCase(DAILY_BOOKING_TAB)) {
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				String[] dates = null;
				list = getBPATable(tblDailyBookingAnalysis);
				value = getDailybookingHeading(tblDailyBookingAnalysis);
				dates = getDaillybookingDates(tblDailyBookingAnalysis);
				preparePDF(list, "Daily_Booking", DBA_EXCEL_JRXML, dates,value);
			} else if (selectedTab.equalsIgnoreCase(COUNTER_REPORT)) {
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				list = getBPATable(tblCounterReport);
				value = getCounterHeading(tblCounterReport);
				if (cbCRCounterSet.getText().equalsIgnoreCase("LR Set")) {
					
					preparePDF(list, "CounterReport", COUNTER_EXCEL_JRXML, null,value);
				} else {
					list = getBPATable(tblCounterReport);
					preparePDF(list, "CounterReport", COUNTER_MISC_EXCEL_JRXML, null,value);
				}
			} else if (selectedTab.equalsIgnoreCase(SUNDRY_BOOKING_TAB)) {
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				list = getBPATable(tblSundryBookAnalysis);
				value = getSundryHeading(tblSundryBookAnalysis);
				preparePDF(list, "Sundry_Booking", SBA_EXCEL_JRXML, null,value);
			} else if (selectedTab.equalsIgnoreCase(UPD_READY)) {
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				value = getUpdreadyHeading(tblUPDReady);
				list = getBPATable(tblUPDReady);
				preparePDF(list, "UPD_Ready", UPD_EXCEL_JRXML, null,value);
			} else if (selectedTab.equalsIgnoreCase(MISSING_CUSTOMER)) {
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				list = getBPATable(tblMissingCustomer);
				value = getMissingCustHeading(tblMissingCustomer);
				preparePDF(list, "Missing_Customer", MISSINGCUSTOMER_EXCEL_JRXML,
						null,value);
			}else if(selectedTab.equalsIgnoreCase(CANCELLEDLR)){
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				if (cbCLReportType.getText().equalsIgnoreCase("Detailed")) {
					list = getBPATable(tblCancelledLR);
					value = getCancelledHeading(tblCancelledLR);
					TableColumn[] col = tblCancelledLR.getColumns();	
					if(col != null){
						int len = col.length;
						String param[] = new String[len];
						for(int i = 0; i < len; i++){
							param[i] = col[i].getText();
						}
						preparePDF(list, "Cancelled LRs", CANCELLEDLRS_EXCEL_JRXML, param,value);
					}
				}
				else
				{
					list = getBPATable(tblCancelledLR);
					value = getCancelledHeading(tblCancelledLR);
					TableColumn[] col = tblCancelledLR.getColumns();
					if(col != null){
						int len = col.length;
						String param[] = new String[len];
						for(int i = 0; i < len; i++){
							param[i] = col[i].getText();
						}
						preparePDF(list, "Cancelled LRs", CANCELLEDLRS_EXCEL_JRXML, param,value);
					}
				}
			}else if(selectedTab.equalsIgnoreCase(FOC)){			
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				if (cbFOCLrReportType.getText().equalsIgnoreCase("Detailed")) {
					list = getBPATable(tblFOCLr);
					value = getFocHeading(tblFOCLr);
					TableColumn[] col = tblFOCLr.getColumns();
					if(col != null){
						int len = col.length;
						String param[] = new String[len];
						for(int i = 0; i < len; i++){
							param[i] = col[i].getText();
						}
						preparePDF(list, "FOC LRs", FOCLRS_EXCEL_JRXML, param,value);
					}
				}
				else
				{
					list = getBPATable(tblFOCLr);
					value = getFocHeading(tblFOCLr);
					TableColumn[] col = tblFOCLr.getColumns();
					if(col != null){
						int len = col.length;
						String param[] = new String[len];
						for(int i = 0; i < len; i++){
							param[i] = col[i].getText();
						}
						preparePDF(list, "FOC LRs", FOCLRS_EXCEL_JRXML, param,value);
					}
				}
				
			}else if(selectedTab.equalsIgnoreCase(OPENLR)){
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				list = getBPATable(tblOpenLr);
				value = getOpenlrHeading(tblOpenLr);
				preparePDF(list, "OpenLr", OPENLR_EXCEL_JRXML,
						null,value);
			}else if(selectedTab.equalsIgnoreCase(INTER_INTRA)){
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				list = getIISTable(tblIIStates);
				value = getIISHeading(tblIIStates);
				preparePDF(list, "Inter_Intra", IIS_EXCEL_JRXML,null,value);
				
			}else if(selectedTab.equalsIgnoreCase(CONSINORBA)){
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				list = getBPATable(tblCnorBookingAnalysis);
				value = getconsinorbaHeading(tblCnorBookingAnalysis);
				TableColumn[] col = tblCnorBookingAnalysis.getColumns();
				if(col != null){
					int len = col.length;
					String param[] = new String[len];
					for(int i = 0; i < len; i++){
						param[i] = col[i].getText();
					}
					preparePDF(list, "CnorBookingAnalysis", CNORBA_EXCEL_JRXML, param,value);
				}			
			}else if(selectedTab.equalsIgnoreCase(CONSINEEBA)){
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				list = getBPATable(tblCneeBookingAnalysis);
				value = getConsineebaHeading(tblCneeBookingAnalysis);
				TableColumn[] col = tblCneeBookingAnalysis.getColumns();
				if(col != null){
					int len = col.length;
					String param[] = new String[len];
					for(int i = 0; i < len; i++){
						param[i] = col[i].getText();
					}
					preparePDF(list, "CneeBookingAnalysis", CNEEBA_EXCEL_JRXML, param,value);
				}			
			}else if(selectedTab.equalsIgnoreCase(MARKET_VEHICLE)){
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				list = getBPATable(tblMarketVehicleUtilisation);
				value = getMarketvehicleHeading(tblMarketVehicleUtilisation);
				TableColumn[] col = tblMarketVehicleUtilisation.getColumns();
				if(col != null){
					int len = col.length;
					String param[] = new String[len];
					for(int i = 0; i < len; i++){
						param[i] = col[i].getText();
					}
					preparePDF(list, "Market Vehicle", MARKET_VEHICLE_EXCEL_JRXML, param,value);
				}			
			}else if(selectedTab.equalsIgnoreCase(LOG)){
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				list = getLOGTable(tblInwardAnalysis);
				value = getLOGHeading(tblInwardAnalysis);
				TableColumn[] col = tblInwardAnalysis.getColumns();
				if(col != null){
					int len = col.length;
					String param[] = new String[len];
					for(int i = 0; i < len; i++){
						param[i] = col[i].getText();
						
					}
					preparePDF(list, "LeftOverGoods", LOG_VEHICLE_EXCEL_JRXML, param,value);
				}			
			}else if(selectedTab.equalsIgnoreCase(DRS)){
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				if (cbDRSAReportType.getText().equalsIgnoreCase("Summary")) {
					list = getLOGTable(tblDRSAttendance);
					value = getDRSHeading(tblDRSAttendance);
					TableColumn[] col = tblDRSAttendance.getColumns();
					if(col != null){
						int len = col.length;
						String param[] = new String[len];
						for(int i = 0; i < len; i++){
							param[i] = col[i].getText();
						}
						preparePDF(list, "DRS Attendance", DRS_VEHICLE_EXCEL_JRXML, param,value);
					}
				}
				else
				{
					value = getDRSHeading(tblDRSAttendance);
					list = getLOGTable(tblDRSAttendance);
					TableColumn[] col = tblDRSAttendance.getColumns();
					if(col != null){
						int len = col.length;
						String param[] = new String[len];
						for(int i = 0; i < len; i++){
							param[i] = col[i].getText();
						}
						preparePDF(list, "DRS Attendance", DRS_VEHICLE_EXCEL_JRXML, param,value);
					}
					
				}
			}
			else if(selectedTab.equalsIgnoreCase(Service_Tax)){
				ArrayList<StationsDTO> list = null;
				String[] value = null;
				list = getServicetaxTable(tblServicetax);
				value = getServicetaxHeading(tblServicetax);
				preparePDF(list, "Service Tax Annexure", SERVICETAX_JRXML,
						null,value);
			}
			else if (selectedTab.equalsIgnoreCase(Service_Tax_LR)) {
				ArrayList<StationsDTO> list = null;
				String[] value = null;
				value = getServicetaxLRHeading(tblServicetaxLR);
				list = getServicetaxLRTable(tblServicetaxLR);
				preparePDF(list, "Service Tax Annexure - LR Wise", SERVICETAX_LR_JRXML, null,value);
			}
	

		 
		}

	  
	 
	
	private ArrayList<TableDecorator> getIISTable(Table refTable) {
		TableDecorator dto = null;
		ArrayList<TableDecorator> list = new ArrayList<TableDecorator>();
		int length = 0;		

		TableItem[] items = tblIIStates.getItems();
		
		if (items != null) {
			length = items.length;
		}
		if (length > 0) {
			for (int i = 0; i < length; i++) {
				dto = new TableDecorator();			
				dto.setCol1(items[i].getText(0));
				dto.setCol2(items[i].getText(1));
				dto.setCol3(items[i].getText(2));
				dto.setCol4(items[i].getText(3));
				dto.setCol5(items[i].getText(4));
				dto.setCol6(items[i].getText(5));
				dto.setCol7(items[i].getText(6));			
			
				list.add(dto);
			}
		}

		return list;
	}

	private ArrayList<TableDecorator> getLOGTable(Table refTable) {
		TableDecorator dto = null;
		ArrayList<TableDecorator> list = new ArrayList<TableDecorator>();
		int length = 0;		

		TableItem[] items = refTable.getItems();
		if (items != null) {
			length = items.length;
		}
		if (length > 0) {
			for (int i = 0; i < length; i++) {
				dto = new TableDecorator();			
				
				dto.setCol0(items[i].getText(0));
				dto.setCol1(items[i].getText(1));
				dto.setCol2(items[i].getText(2));
				dto.setCol3(items[i].getText(3));
				dto.setCol4(items[i].getText(4));
				dto.setCol5(items[i].getText(5));
				dto.setCol6(items[i].getText(6));
				dto.setCol7(items[i].getText(7));
				dto.setCol8(items[i].getText(8));
				dto.setCol9(items[i].getText(9));
				dto.setCol10(items[i].getText(10));
				dto.setCol11(items[i].getText(11));
				dto.setCol12(items[i].getText(12));
				dto.setCol13(items[i].getText(13));
				dto.setCol14(items[i].getText(14));
				dto.setCol15(items[i].getText(15));
				dto.setCol16(items[i].getText(16));
				
				dto.setCol17(items[i].getText(17));
				dto.setCol18(items[i].getText(18));
				dto.setCol19(items[i].getText(19));
				dto.setCol20(items[i].getText(20));				
				dto.setCol21(items[i].getText(21));
				dto.setCol22(items[i].getText(22));
				dto.setCol23(items[i].getText(23));
				dto.setCol24(items[i].getText(24));
				dto.setCol25(items[i].getText(25));
				dto.setCol26(items[i].getText(26));
				dto.setCol27(items[i].getText(27));
				dto.setCol28(items[i].getText(28));
				dto.setCol29(items[i].getText(29));
				dto.setCol30(items[i].getText(30));
				dto.setCol31(items[i].getText(31));
				dto.setCol32(items[i].getText(32));
				dto.setCol33(items[i].getText(33));
				dto.setCol34(items[i].getText(34));
				
				list.add(dto);
			}
		}

		return list;
	}
	
	private String[]  getLOGHeading(Table refTable) {
		
		String list[] = new String[5];
						
		list[0] = txtLOGFrom.getText();
		list[1] = txtLOGToDate.getText();
		//list[2] = cbLOGFromHour.getText() +":"+ cbLOGFromMin.getText() + cbLOGTime.getText();
		list[2] = cbInwardStation.getText();
		list[3] = cbFromStation.getText();
		list[4] = cbOption.getText();
	
		return list;
	}
	
	private String[] getDDEHeading(){
		
		String list[] = new String[5];
		
		list[0] =txtDDEFrom.getText();
		list[1] =txtDDETo.getText();
		String branchCode = cbDDEBranch.getText();
		if (!branchCode.equalsIgnoreCase("All")) {
			int index = branchCode.indexOf(" - ");
			list[2] = branchCode.substring(index + 3);
		}
		else
			list[2] ="All";
			
		String stationCode = cbDDEStation.getText();
		if (!stationCode.equalsIgnoreCase("All")) {
			int index = stationCode.indexOf(" - ");
			list[3] = stationCode.substring(index + 3);
		}
		else
			list[3] ="All";
		
		
		list[4] =txtDDEAbove.getText();
		
			
		return list;
	}
	
	private String[] getRSTHeading(){
		
		String list[] = new String[3];
		
		String branchCode = cbRSBranch.getText();
		if (!branchCode.equalsIgnoreCase("All")) {
			int index = branchCode.indexOf(" - ");
			list[0] = branchCode.substring(index + 3);
		}
		else
			list[0] ="All";
			
		
		list[1] =txtRSMonth.getText();
		if(rdFull.getSelection() == true)
			list[2] =rdFull.getText();
		else
			list[2] =rdUnrecover.getText();
		
		
			
		return list;
	}
	
	private String[] getBPAHeading(){
		
		String list[] = new String[4];
		
		list[0] =txtBPAFrom.getText();
		list[1] =txtBPATo.getText();
		String branchCode = cbBPABranch.getText();
		if (!branchCode.equalsIgnoreCase("All")) {
			int index = branchCode.indexOf(" - ");
			list[2] = branchCode.substring(index + 3);
		}
		else
			list[2] ="All";
		
		
		if(!cbBPABranch.getText().equals("All"))
			list[3] = "Detailed";
		else
			list[3] =cbBPAReportType.getText();
		
			
		return list;
	}
	
	private String[] getCCCSummaryHeading(Table refTable){
		
		String list[] = new String[3];
		
		list[0] =txtCCCFrom.getText();
		list[1] =txtCCCTo.getText();
		String branchCode = cbCCCBranch.getText();
		if (!branchCode.equalsIgnoreCase("All")) {
			int index = branchCode.indexOf(" - ");
			list[2] = branchCode.substring(index + 3);
		}
		else
			list[2] ="All";
			
		return list;
	}
	
	private String[] getUttopayHeading(Table refTable){
		
		String list[] = new String[5];
		
		list[0] = txtUTFrom.getText();
		list[1] = txtUTTo.getText();
		if(cbUTReportType.getText().equals("Summary"))
			list[2] = "Summary";
		else
			list[2]= "Detailed";
		
		String branchCode = cbUTBranch.getText();
		if (!branchCode.equalsIgnoreCase("All")) {
			int index = branchCode.indexOf(" - ");
			list[3] = branchCode.substring(index + 3);
		}
		else
			list[3] ="All";
		
		String stationCode = cbUTStation.getText();
		if (!stationCode.equalsIgnoreCase("All")) {
			int index = stationCode.indexOf(" - ");
			list[4] = stationCode.substring(index + 3);
		}
		else
			list[4] ="All";
		
		return list;
	}
	
	private String[] getDailybookingHeading(Table refTable){
		
		String list[] = new String[5];
		
		list[0] =txtDBAFrom.getText();
		list[1] =txtDBATo.getText();
		String branchCode = cbDBABranch.getText();
		if (!branchCode.equalsIgnoreCase("All")) {
			int index = branchCode.indexOf(" - ");
			list[2] = branchCode.substring(index + 3);
		}
		else
			list[2] ="All";
		if((cbDBAReportType.getText().equals("Detailed"))||(!cbDBABranch.getText().equals("All")))
			list[3] = "Detailed";
		else
			list[3] = "Consolidated";
			
		list[4] =cbDBAMOC.getText();
		
			
		return list;
	}
	
	private String[] getCounterHeading(Table refTable){
	
		String list[] = new String[5];
		
		list[0] =txtCRFrom.getText();
		list[1] =txtCRTo.getText();
		String branchCode = cbCounterBranch.getText();
		if (!branchCode.equalsIgnoreCase("All")) {
			int index = branchCode.indexOf(" - ");
			list[2] = branchCode.substring(index + 3);
		}
		else
			list[2] ="All";
		if(cbCRReportType.getText().equals("Consolidated"))
			list[3] = "Consolidated";
		else
			list[3] = "Detailed";
		if(cbCRCounterSet.getText().equals("LR Set"))
			list[4] = "LR Set";
		else
			list[4] = "MISC";
		
			
		return list;
	}
	
	private String[] getSundryHeading(Table refTable){
	
		String list[] = new String[4];
		
		list[0] = txtSBAFrom.getText();
		list[1] = txtSBATo.getText();
		String branchCode = cbSBABranch.getText();
		if (!branchCode.equalsIgnoreCase("All")) {
			int index = branchCode.indexOf(" - ");
			list[2] = branchCode.substring(index + 3);
		}
		else
			list[2] ="All";
		
		String stationCode = cbSBAStation.getText();
		if (!stationCode.equalsIgnoreCase("All")) {
			int index = stationCode.indexOf(" - ");
			list[3] = stationCode.substring(index + 3);
		}
		else
			list[3] ="All";
		
		return list;
	}

	private String[] getUpdreadyHeading(Table refTable){
	
		String list[] = new String[4];
		
		String branchCode = cbUPDRBranch.getText();
		if (!branchCode.equalsIgnoreCase("All")) {
			int index = branchCode.indexOf(" - ");
			list[0] = branchCode.substring(index + 3);
		}
		else
			list[0] ="All";
		
		String stationCode = cbUPDRStation.getText();
		if (!stationCode.equalsIgnoreCase("All")) {
			int index = stationCode.indexOf(" - ");
			list[1] = stationCode.substring(index + 3);
		}
		else
			list[1] ="All";
		
		if(cbUPDOption.getText().equals("More Than"))
			list[2] = "More Than";
		else
			list[2] = "Equal To";
		
		list[3] = cbUPDRInwardDays.getText();
		
		
			
		return list;
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
	private String[] getCancelledHeading(Table refTable){
		
		String list[] = new String[5];
		
		list[0] =txtCancelledLRfromdate.getText();
		list[1] =txtcancelledlrtodate.getText();
		list[2] =cbCLReportType.getText();
		String branchCode = cbCLRBranch.getText();
		if (!branchCode.equalsIgnoreCase("All")) {
			int index = branchCode.indexOf(" - ");
			list[3] = branchCode.substring(index + 3);
		}
		else
			list[3] ="All";
		
		String stationCode = cbStation.getText();
		
		if(!(list[2].equalsIgnoreCase("Summary"))){
			if (!stationCode.equalsIgnoreCase("All")) {
				int index = stationCode.indexOf(" - ");
				list[4] = stationCode.substring(index + 3);
			}
			else
				list[4] ="All";
		}
		else
			list[4] = " ";
		
		
			
		return list;
	}
	
	private String[] getFocHeading(Table refTable){
		
		String list[] = new String[4];
		
		list[0] = txtFOCLrFrom.getText();
		list[1] = txtFOCLrTo.getText();
		String branchCode = cbFOCLrBranch.getText();
		if (!branchCode.equalsIgnoreCase("All")) {
			int index = branchCode.indexOf(" - ");
			list[2] = branchCode.substring(index + 3);
		}
		else
			list[2] ="All";
		if(cbFOCLrReportType.getText().equals("Detailed"))
			list[3] = "Detailed";
		else
			list[3] = "Summary";
		
			
		return list;
	}
	
	private String[] getOpenlrHeading(Table refTable){
		
		String list[] = new String[3];
		
		list[0] = txtOpenLrFrom.getText();
		list[1] = txtOpenLrTo.getText();
		
		String branchCode = cbOpenLrBranch.getText();
		if (!branchCode.equalsIgnoreCase("All")) {
			int index = branchCode.indexOf(" - ");
			list[2] = branchCode.substring(index + 3);
		}
		else
			list[2] ="All";
			
		return list;
	}
	
	private String[] getIISHeading(Table refTable){
		
		String list[] = new String[3];
		
		list[0] = txtIISFrom.getText();
		list[1] = txtIISTo.getText();
		list[2] = cbIISChoice.getText();

			
		return list;
	}
	private String[] getconsinorbaHeading(Table refTable){
		
		String list[] = new String[4];
		int[] indexs = lstCnorPickMonths.getSelectionIndices();
		int len = indexs.length;
		String[] months = new String[len];
		list[2] = " ";
		
		String branchCode = cbCnorBABranch.getText();
		if (!branchCode.equalsIgnoreCase("All")) {
			int index = branchCode.indexOf(" - ");
			list[0] = branchCode.substring(index + 3);
		}
		else
			list[0] ="All";
		list[1] = cbCnorBAMonths.getText();
		for (int i = 0; i < len; i++){ 
			months[i] = lstCnorPickMonths.getItem(indexs[i]);
			
			list[2] = list[2]+months[i];
		}
		list[3] = cbCnorBAOption.getText();
		
			
		return list;
	}
	
	private String[] getConsineebaHeading(Table refTable){
	
		String list[] = new String[4];
		int[] indexs = lstCneePickMonths.getSelectionIndices();
		int len = indexs.length;
		String[] months = new String[len];
		list[2] = " ";			
		
		String branchCode = cbCneeBABranch.getText();
		if (!branchCode.equalsIgnoreCase("All")) {
			int index = branchCode.indexOf(" - ");
			list[0] = branchCode.substring(index + 3);
		}
		else
			list[0] ="All";
		list[1] =cbCneeBAMonths.getText();
		for (int i = 0; i < len; i++){ 
			months[i] = lstCneePickMonths.getItem(indexs[i]);
			list[2] = list[2] + months[i];
		}
		list[3] = cbCneeBAOption.getText();
		
			
		return list;
	}
	
	private String[] getMarketvehicleHeading(Table refTable){
		
		String list[] = new String[2];
		
		list[0] = txtMarkVehiUtilSelectMonth.getText();
		
		String branchCode = cbMarkVehiUtilSelectBranch.getText();
		if (!branchCode.equalsIgnoreCase("All")) {
			int index = branchCode.indexOf(" - ");
			list[1] = branchCode.substring(index + 3);
		}
		else
			list[1] ="All";
			
		return list;
	}
	
	private String[] getDRSHeading(Table refTable){
		
		String list[] = new String[3];
		
		list[0] = txtDRSMonth.getText();
		if(cbDRSAReportType.getText().equals("Summary"))
			list[1] = "Summary";
		else
			list[1] = "%Report";
		
		String branchCode = cbDRSAttendanceBranch.getText();
		if (!branchCode.equalsIgnoreCase("All")) {
			int index = branchCode.indexOf(" - ");
			list[2] = branchCode.substring(index + 3);
		}
		else
			list[2] ="All";
			
		return list;
	}
	
	private String[] getServicetaxHeading(Table tblServicetax) {
		// TODO Auto-generated method stub
		
		
		String list[] = new String[3];
		
		list[0] =txtStFrom.getText();
		
		list[1] =txtStTo.getText();
				
		if(cbStReptype.getText().equalsIgnoreCase("Detailed"))
			list[2] = "Detailed";
		else
			list[2] = "Consolidated";
		
			
		return list;
	}

	private String[] getServicetaxLRHeading(Table tblServicetax) {
		// TODO Auto-generated method stub
		String list[] = new String[4];
		
		list[0] =txtStLRFrom.getText();
		list[1] =txtStLRTo.getText();
		String branchCode = cbStLRBranch.getText();
		if (!branchCode.equalsIgnoreCase("All")) {
			int index = branchCode.indexOf(" - ");
			list[2] = branchCode.substring(index + 3);
		}
		else
			list[2] ="All";
			
		String stationCode = cbStLRStation.getText();
		if (!stationCode.equalsIgnoreCase("All")) {
			int index = stationCode.indexOf(" - ");
			list[3] = stationCode.substring(index + 3);
		}
		else
			list[3] ="All";
			
			
		return list;
	
	}

	
	private ArrayList<TableDecorator> getBPATable(Table refTable) {
		TableDecorator dto = null;
		ArrayList<TableDecorator> list = new ArrayList<TableDecorator>();
		int length = 0;
		String colStr1 = null;
		String colStr2 = null;
		int index = -1;

		TableItem[] items = refTable.getItems();
		if (items != null) {
			length = items.length;
		}
		if (length > 0) {
			for (int i = 0; i < length; i++) {
				dto = new TableDecorator();				
				colStr1 = items[i].getText(1);
				index = colStr1.indexOf(" - ");
				if (index > 0) {
					colStr1 = colStr1.substring(index + 2);
				}

				colStr2 = items[i].getText(2);
				index = colStr2.indexOf(" - ");
				if (index > 0) {
					colStr2 = colStr2.substring(index + 1);
				}
				
				dto.setCol1(colStr1);
				dto.setCol2(colStr2);
				dto.setCol3(items[i].getText(3));
				dto.setCol4(items[i].getText(4));
				dto.setCol5(items[i].getText(5));
				dto.setCol6(items[i].getText(6));
				dto.setCol7(items[i].getText(7));
				dto.setCol8(items[i].getText(8));
				dto.setCol9(items[i].getText(9));
				dto.setCol10(items[i].getText(10));
				dto.setCol11(items[i].getText(11));
				dto.setCol12(items[i].getText(12));
				dto.setCol13(items[i].getText(13));
				dto.setCol14(items[i].getText(14));
				dto.setCol15(items[i].getText(15));
				dto.setCol16(items[i].getText(16));
				
				list.add(dto);
			}
		}

		return list;
	}

	private ArrayList<RemittanceShortageDTO> getRSTable() throws Exception {

		RemittanceShortageDTO dto = null;
		ArrayList<RemittanceShortageDTO> list = new ArrayList<RemittanceShortageDTO>();
		int length = 0;

		TableItem[] items = tblRemittance.getItems();
		if (items != null) {
			length = items.length;
		}
		if (length > 0) {
			for (int i = 0; i < length; i++) {
				dto = new RemittanceShortageDTO();
				dto.setFromStation(items[i].getText(1));
				dto.setToStation(items[i].getText(2));
				dto.setLrNo(items[i].getText(3));
				if (items[i].getText(4) != "")
					dto.setLrDate(dateFormat.parse(items[i].getText(4)));

				dto.setLrType(items[i].getText(5));
				dto.setDrsNo(items[i].getText(6));
				if (items[i].getText(7) != "")
					dto.setDrsDate(dateFormat.parse(items[i].getText(7)));

				dto.setCrNo(items[i].getText(8));
				dto.setCnor(items[i].getText(9));
				dto.setCnee(items[i].getText(10));

				if (!items[i].getText(11).equals(""))
					dto.setAmount(Float.parseFloat(items[i].getText(11)));

				if (!items[i].getText(12).equals(""))
					dto.setRecoveryDate(dateFormat.parse(items[i].getText(12)));

				list.add(dto);
			}
		}

		return list;

	}

	private ArrayList<DDRDTO> getDDETable() {
		DDRDTO dto = null;
		ArrayList<DDRDTO> list = new ArrayList<DDRDTO>();
		int length = 0;

		TableItem[] items = tblDDExtra.getItems();
		if (items != null) {
			length = items.length;
		}
		if (length > 0) {
			for (int i = 0; i < length; i++) {
				dto = new DDRDTO();
				dto.setLrNo(items[i].getText(1));
				dto.setFromStation(items[i].getText(2));
				dto.setLrType(items[i].getText(3));
				if (!items[i].getText(4).equals(""))
					dto.setBasicFreight(Float.parseFloat(items[i].getText(4))); 
				else
					dto.setBasicFreight(-1);
				if (!items[i].getText(5).equals(""))
					dto.setDdc(Float.parseFloat(items[i].getText(5)));
				else
					dto.setDdc(-1);
				if (!items[i].getText(6).equals(""))
					dto.setDdExtra(Float.parseFloat(items[i].getText(6)));
				else
					dto.setDdExtra(-1);
				if (!items[i].getText(7).equals(""))
					dto.setOthers(Float.parseFloat(items[i].getText(7)));
				else
					dto.setOthers(-1);
				if (!items[i].getText(8).equals(""))
					dto.setTotal(Float.parseFloat(items[i].getText(8)));
				else
					dto.setTotal(-1);
				if (!items[i].getText(9).equals(""))
					dto.setDdcFree(Float.parseFloat(items[i].getText(9)));

				list.add(dto);
			}
		}

		return list;
	}
	
	private void generateCancelledLrExcel() {

		try {			
			if (tblCancelledLR != null) {
				String fileName = "";
				int index = cbCLReportType.getSelectionIndex();
				if (index == 1) {
					fileName = "CancelLRDetailed";
					TableItem items[] = tblCancelledLR.getItems();
					if (null != items) {
						int len = items.length;
						CancelledLRDetailedDTODecorator[] dto = null;
						if (len > 0) {
							dto = new CancelledLRDetailedDTODecorator[len];
						}
						for (int i = 0; i < len; i++) {
							dto[i] = new CancelledLRDetailedDTODecorator();

							dto[i].setSlNo(items[i].getText(0));
							dto[i].setLrNo(items[i].getText(1));
							dto[i].setLrDate(items[i].getText(2));
							dto[i].setLrType(items[i].getText(3));
							dto[i].setFrom(items[i].getText(4));
							dto[i].setTo(items[i].getText(5));
							dto[i].setNoa(items[i].getText(6));
							dto[i].setArtValue(items[i].getText(7));
							dto[i].setBft(items[i].getText(8));
							dto[i].setCc(items[i].getText(9));
							dto[i].setIec(items[i].getText(10));
							dto[i].setOthers(items[i].getText(11));
							dto[i].setDd(items[i].getText(12));
							dto[i].setTotal(items[i].getText(13));

						}
						handler.printLR(dto);
					}
				} else {
					fileName = "CancelLRSummary";
					TableItem items[] = tblCancelledLR.getItems();
					if (null != items) {
						int len = items.length;
						CancelledLRSummaryDTODecorator[] dto = null;
						if (len > 0) {
							dto = new CancelledLRSummaryDTODecorator[len];
						}
						for (int i = 0; i < len; i++) {
							dto[i] = new CancelledLRSummaryDTODecorator();

							dto[i].setSlNo(items[i].getText(0));
							dto[i].setBranchCode(items[i].getText(1));
							dto[i].setStationCode(items[i].getText(2));
							dto[i].setNoC(items[i].getText(3));
							dto[i].setTotalFt(items[i].getText(4));
							dto[i].setAvgFt(items[i].getText(5));

						}
						handler.printLR(dto);
					}
				}
				
				FileDialog dialog = new FileDialog(this.getShell(), SWT.SAVE);
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
					AdminComposite.display("Excel Saved Successfully",
							STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				}
			
			
			}
		} catch (Exception e) {			
			e.printStackTrace();
		}

	}

	private void generateFocExcel() {
		try {
			if (tblFOCLr != null) {
				String fileName = "";
				int index = cbFOCLrReportType.getSelectionIndex();
				if (index == 0) {
					fileName = "FocDetailed";
					TableItem items[] = tblFOCLr.getItems();
					if (null != items) {
						int len = items.length;
						FocLRDetailedDTODecorator[] dto = null;
						if (len > 0) {
							dto = new FocLRDetailedDTODecorator[len];
						}
						for (int i = 0; i < len; i++) {
							dto[i] = new FocLRDetailedDTODecorator();

							dto[i].setSlNo(items[i].getText(0));
							dto[i].setLrNo(items[i].getText(1));
							dto[i].setLrDate(items[i].getText(2));
							dto[i].setFrom(items[i].getText(3));
							dto[i].setTo(items[i].getText(4));
							dto[i].setNoa(items[i].getText(5));
							dto[i].setCrgWt(items[i].getText(6));
							dto[i].setArtType(items[i].getText(7));
							dto[i].setArtValue(items[i].getText(8));

						}
						handler.printLR(dto);
					}
				} else {
					fileName = "FocSummary";
					TableItem items[] = tblFOCLr.getItems();
					if (null != items) {
						int len = items.length;
						FocLRSummaryDTODecorator[] dto = null;
						if (len > 0) {
							dto = new FocLRSummaryDTODecorator[len];
						}
						for (int i = 0; i < len; i++) {
							dto[i] = new FocLRSummaryDTODecorator();

							dto[i].setSlNo(items[i].getText(0));
							dto[i].setBranchCode(items[i].getText(1));
							dto[i].setStationCode(items[i].getText(2));
							dto[i].setTotalLr(items[i].getText(3));
							dto[i].setFocLr(items[i].getText(4));
							dto[i].setLrPercent(items[i].getText(5));
							dto[i].setTotalWt(items[i].getText(6));
							dto[i].setFocWt(items[i].getText(7));
							dto[i].setWtPercent(items[i].getText(8));

						}
						handler.printLR(dto);
					}
				}
				
				FileDialog dialog = new FileDialog(this.getShell(), SWT.SAVE);
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
					AdminComposite.display("Excel Saved Successfully",
							STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				}
			}			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param table
	 * @return
	 */
	private OpenLrDTODecorator[] getOpenLrExcelDecorator() {
		if (null != tblOpenLr) {
			TableItem[] items = tblOpenLr.getItems();
			if (null != items) {
				int len = items.length;
				OpenLrDTODecorator[] dto = null;
				if (len > 0) {
					dto = new OpenLrDTODecorator[len];

					for (int i = 0; i < len; i++) {
						dto[i] = new OpenLrDTODecorator();
						dto[i].setSlNo(String.valueOf(i + 1));
						dto[i].setBranchCode(String
								.valueOf(items[i].getText(1)));
						dto[i].setFrom(String.valueOf(items[i].getText(2)));
						dto[i].setTo(String.valueOf(items[i].getText(3)));
						dto[i].setLrNo(String.valueOf(items[i].getText(4)));
						dto[i].setLrDate(String.valueOf(items[i].getText(5)));
						dto[i].setLrType(String.valueOf(items[i].getText(6)));
						dto[i].setCardRate(String.valueOf(items[i].getText(7)));
						dto[i].setBft(String.valueOf(items[i].getText(8)));
						dto[i].setCcc(String.valueOf(items[i].getText(9)));
						dto[i].setDdc(String.valueOf(items[i].getText(10)));
						dto[i].setOthers(String.valueOf(items[i].getText(11)));
						dto[i].setTotal(String.valueOf(items[i].getText(12)));
						dto[i]
								.setDiscount(String.valueOf(items[i]
										.getText(13)));
						dto[i].setCrgWt(String.valueOf(items[i].getText(14)));
						dto[i].setConsignorName(String.valueOf(items[i]
								.getText(15)));
						dto[i].setConsigneeName(String.valueOf(items[i]
								.getText(16)));

					}
					return dto;
				}
			}
		}
		return null;
	}


	@SuppressWarnings("unchecked")
	private void prepareExcel(ArrayList list, String fileName,
			String jrxmlFile, String[] param,String[] value) {
		try {
			AdminComposite.display("Exporting...", STATUS_SUCCESS,
					SUCCESS_FONT_COLOR);
			handler.printReportExcel(list, fileName, jrxmlFile, param,value);

			FileDialog dialog = new FileDialog(this.getShell(), SWT.SAVE);
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
				AdminComposite.display("Excel Saved Successfully",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			}
			else
			{
				AdminComposite.display("Excel Saving Cancelled",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			}
		} catch (Exception e) {
			AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			e.printStackTrace();
		}

	}
	
	private void prepareTXT(ArrayList list, String fileName,
			String jrxmlFile, String[] param,String[] value) {
		try {
			AdminComposite.display("Exporting...", STATUS_SUCCESS,
					SUCCESS_FONT_COLOR);
			handler.printReportTXT(list, fileName, jrxmlFile, param,value);

			FileDialog dialog = new FileDialog(this.getShell(), SWT.SAVE);
			dialog.setFilterExtensions(new String[] { "*.txt" });
			dialog.setFilterNames(new String[] { "*.txt" });
			dialog.setFilterPath("WINDOWS/Desktop"); // Windows path
			dialog.setFileName(fileName);
			String filepath = dialog.open();

			if (null != filepath) {
				File df = new File(filepath);
				File xl = new File("lib/" + fileName + ".xls");

				if (xl.exists()) {
					//AdminComposite.display("Saving...", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					copyFileToTxt(xl, df, dialog.getFileName());
				}
				AdminComposite.display("TEXT File Saved Successfully",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			}
			else
			{
				AdminComposite.display("TEXT File Saving Cancelled",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			}
		} catch (Exception e) {
			AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			e.printStackTrace();
		}

	}
	
	public void copyFileToTxt(File source, File dest, String fileName)
	throws IOException {

		if (!dest.exists()) {
			dest.createNewFile();
		} else {
			dest.setWritable(true);

		}
		 try
		    {
		      String filename = source.getAbsolutePath();
		      WorkbookSettings ws = new WorkbookSettings();
		      ws.setLocale(new Locale("en", "EN"));
		      Workbook w = Workbook.getWorkbook(new File(filename),ws);
		      File f = dest;
		      OutputStream os = (OutputStream)new FileOutputStream(f);
		      String encoding = "UTF8";
		      OutputStreamWriter osw = new OutputStreamWriter(os, encoding);
		      BufferedWriter bw = new BufferedWriter(osw);


		      for (int sheet = 0; sheet < w.getNumberOfSheets(); sheet++)
		      {
		        Sheet s = w.getSheet(sheet);

		        bw.write(s.getName());
		        bw.newLine();

		        Cell[] row = null;
		        
		        for (int i = 1 ; i < s.getRows(); i++)
		        {
		          row = s.getRow(i);
		          if (row.length > 0)
		          {
		            bw.write(row[0].getContents());
		            for (int j = 1; j < row.length; j++)
		            {
		            	bw.write('	');
		                bw.write(row[j].getContents());
		            		
		            }
		          }
		          bw.newLine();
		        }
		      }
		      bw.flush();
		      bw.close();
		    }
		    catch (Exception e)
		    {
		      System.err.println(e);
		    }	

	}

		
	

		
	
	private void preparePDF(ArrayList list, String fileName,
			String jrxmlFile, String[] param,String[] value) {
		try {
			AdminComposite.display("Exporting...", STATUS_SUCCESS,
					SUCCESS_FONT_COLOR);
			handler.printReportPDF(list, fileName, jrxmlFile, param,value);

			FileDialog dialog = new FileDialog(this.getShell(), SWT.SAVE);
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
				AdminComposite.display("PDF Saved Successfully",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			}

		} catch (Exception e) {
			AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			e.printStackTrace();
		}

	}
	
	
	
	@SuppressWarnings("unchecked")
	private void prepareIISExcel(ArrayList list, String fileName,
			String jrxmlFile,String[] value) {
		try {
			AdminComposite.display("Exporting...", STATUS_SUCCESS,
					SUCCESS_FONT_COLOR);
		
		
			handler.printReportExcel(list, fileName, jrxmlFile,null,value);
			
			FileDialog dialog = new FileDialog(this.getShell(), SWT.SAVE);
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
				AdminComposite.display("Excel Saved Successfully",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			}
			else
			{
				AdminComposite.display("Excel Saving Cancelled",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			}
		} catch (Exception e) {
			AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
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

	private void handleIIS() {
		try {
			SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
			Date from = dt.parse(txtIISFrom.getText());
			Date to = dt.parse(txtIISTo.getText());

			CustomerBusinessAnalysisDTO[] dto = getIIStates(from, to);

			if (tblIIStates != null)
				tblIIStates.removeAll();
			populateIIColumns();
			if (dto != null) {
				populateIIS(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private CustomerBusinessAnalysisDTO[] getIIStates(Date from, Date to) {
		// TODO Auto-generated method stub
		CustomerBusinessAnalysisDTO[] dto = null;
		try {
			/*Date curDate = new Date();
			int m1 = fromDt.getYear() * 12 + fromDt.getMonth();
	        int m2 = curDate.getYear() * 12 + curDate.getMonth();
	        monthDiff = m2 - m1;*/
			Date date = BeanUtil.getThree_month_updated();
		if(from.before(date)){
			System.out.println("in ddr histry-->"+date);
			dto = handler.getIIStatesHistory(from, to);
		}else{
			dto = handler.getIIStates(from, to);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
		

		
	}

	private void populateIIS(CustomerBusinessAnalysisDTO[] dto)
			throws Exception {
		String from = "";
		String to = "";
		int colVal = 0;
		float colFloatVal = 0;
		int colTotIndex = tblIIStates.getColumnCount();
		DecimalFormat df = new DecimalFormat("0.00");

		TableItem[] items = tblIIStates.getItems();
		int itemsLen = items.length;
		for (int i = 0; i < dto.length; i++) {
			from = dto[i].getFromStation();
			to = dto[i].getCustomerName();

			for (int j = 0; j < items.length; j++) {
				if (to.equalsIgnoreCase(items[j].getText(0))) {
					for (int k = 0; k < STATES.length; k++) {
						if (from.equalsIgnoreCase(STATES[k])) {

							if (cbIISChoice.getText().equals(COUNT)) {

								items[j].setText(k + 1, String.valueOf(dto[i]
										.getCount()));

								// last table item total
								if (!items[itemsLen - 1].getText(k + 1).equals(
										"")) {
									colVal = Integer
											.parseInt(items[itemsLen - 1]
													.getText(k + 1));
								} else {
									colVal = 0;
								}
								items[itemsLen - 1].setText(k + 1, String
										.valueOf(colVal + dto[i].getCount()));

								// last COL total
								if (!items[j].getText(colTotIndex - 1).equals(
										"")) {
									colVal = Integer.parseInt(items[j]
											.getText(colTotIndex - 1));
								} else {
									colVal = 0;
								}
								items[j].setText(colTotIndex - 1, String
										.valueOf(colVal + dto[i].getCount()));

							} else if (cbIISChoice.getText().equals(                 
									BASIC_FREIGHT)) {
								items[j].setText(k + 1, df.format(dto[i]
										.getBasic_freight()));
								// total
								if (!items[itemsLen - 1].getText(k + 1).equals(
										"")) {
									colFloatVal = Float
											.parseFloat(items[itemsLen - 1]
													.getText(k + 1));
								} else {
									colFloatVal = 0;
								}
								items[itemsLen - 1].setText(k + 1, df
										.format(colFloatVal
												+ dto[i].getBasic_freight()));

								// COL total
								if (!items[j].getText(colTotIndex - 1).equals(
										"")) {
									colFloatVal = Float.parseFloat(items[j]
											.getText(colTotIndex - 1));
								} else {
									colFloatVal = 0;
								}
								items[j].setText(colTotIndex - 1, df
										.format(colFloatVal
												+ dto[i].getBasic_freight()));
							} else if (cbIISChoice.getText().equals(
									TOTAL_FREIGHT)) {
								items[j].setText(k + 1, df.format(dto[i]
										.getTotal_freight()));
								// total
								if (!items[itemsLen - 1].getText(k + 1).equals(
										"")) {
									colFloatVal = Float
											.parseFloat(items[itemsLen - 1]
													.getText(k + 1));
								} else {
									colFloatVal = 0;
								}
								items[itemsLen - 1].setText(k + 1, df
										.format(colFloatVal
												+ dto[i].getTotal_freight()));

								// COL total
								if (!items[j].getText(colTotIndex - 1).equals(
										"")) {
									colFloatVal = Float.parseFloat(items[j]
											.getText(colTotIndex - 1));
								} else {
									colFloatVal = 0;
								}
								items[j].setText(colTotIndex - 1, df
										.format(colFloatVal
												+ dto[i].getTotal_freight()));

							} else if (cbIISChoice.getText().equals(
									ACTUAL_WEIGHT)) {
								items[j].setText(k + 1, df.format(dto[i]
										.getActual_weight()));
								

								// total
								if (!items[itemsLen - 1].getText(k + 1).equals(
										"")) {
									colFloatVal = Float
											.parseFloat(items[itemsLen - 1]
													.getText(k + 1));
								} else {
									colFloatVal = 0;
								}
								items[itemsLen - 1].setText(k + 1, df
										.format(colFloatVal
												+ dto[i].getActual_weight()));

								// COL total
								if (!items[j].getText(colTotIndex - 1).equals(
										"")) {
									colFloatVal = Float.parseFloat(items[j]
											.getText(colTotIndex - 1));
								} else {
									colFloatVal = 0;
								}
								items[j].setText(colTotIndex - 1, String
										.valueOf(colFloatVal
												+ dto[i].getActual_weight()));
							} else if (cbIISChoice.getText().equals(
									CHARGED_WEIGHT)) {
								items[j].setText(k + 1, df.format(dto[i]
										.getCharged_weight()));

								// total
								if (!items[itemsLen - 1].getText(k + 1).equals(
										"")) {
									colFloatVal = Float
											.parseFloat(items[itemsLen - 1]
													.getText(k + 1));
								} else {
									colFloatVal = 0;
								}
								items[itemsLen - 1].setText(k + 1, df
										.format(colFloatVal
												+ dto[i].getCharged_weight()));

								// COL total
								if (!items[j].getText(colTotIndex - 1).equals(
										"")) {
									colFloatVal = Float.parseFloat(items[j]
											.getText(colTotIndex - 1));
								} else {
									colFloatVal = 0;
								}
								items[j].setText(colTotIndex - 1, df
										.format(colFloatVal
												+ dto[i].getCharged_weight()));
							} else if (cbIISChoice.getText().equals(NOA)) {
								items[j].setText(k + 1, String.valueOf(dto[i]
										.getNoa()));

								// total
								if (!items[itemsLen - 1].getText(k + 1).equals(
										"")) {
									colVal = Integer
											.parseInt(items[itemsLen - 1]
													.getText(k + 1));
								} else {
									colVal = 0;
								}
								items[itemsLen - 1].setText(k + 1, String
										.valueOf(colVal + dto[i].getNoa()));

								// Col total
								if (!items[j].getText(colTotIndex - 1).equals(
										"")) {
									colVal = Integer.parseInt(items[j]
											.getText(colTotIndex - 1));
								} else {
									colVal = 0;
								}
								items[j].setText(colTotIndex - 1, String
										.valueOf(colVal + dto[i].getNoa()));
							}

						}
					}
				}
			}
		}

		if (cbIISChoice.getText().equals(COUNT)
				|| cbIISChoice.getText().equals(NOA)) {
			int finalVal = 0;
			for (int i = 0; i < itemsLen; i++) {
				// Final Total
				if (!items[i].getText(colTotIndex - 1).equals("")) {
					colVal = Integer
							.parseInt(items[i].getText(colTotIndex - 1));
				} else {
					colVal = 0;
				}

				finalVal = colVal + finalVal;

			}
			items[itemsLen - 1].setText(colTotIndex - 1, String
					.valueOf(finalVal));
		} else {
			float finalVal = 0;
			for (int i = 0; i < itemsLen; i++) {
				// Final Total
				if (!items[i].getText(colTotIndex - 1).equals("")) {
					colFloatVal = Float.parseFloat(items[i]
							.getText(colTotIndex - 1));
				} else {
					colFloatVal = 0;
				}

				finalVal = colFloatVal + finalVal;

			}
			items[itemsLen - 1].setText(colTotIndex - 1, df.format(finalVal));
		}

		fillZerosOnEmpty(tblIIStates);
		
	}

	private void populateIIColumns() {

		tblIIStates.addListener(SWT.MeasureItem, new Listener() {
			public void handleEvent(Event event) {
				event.height = 28;
			}
		});

		for (int i = 0; i < STATES.length; i++) {
			TableItem item = new TableItem(tblIIStates, SWT.NONE);
			item.setText(0, STATES[i]);
		}

	}

	private void handleUT() {
		try {

			/*String year = txtTUTMonth.getText();
			int index1 = year.indexOf("-");
			String month = year.substring(0, index1);
			year = year.substring(index1 + 1);*/
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date fromDt = dateFormat.parse(txtUTFrom.getText());
			Date toDate = dateFormat.parse(txtUTTo.getText());

			String branch = cbUTBranch.getText();

			if (cbUTReportType.getText().equalsIgnoreCase("Detailed")) {
				if (cbUTBranch.getSelectionIndex() != -1) {
					BookingDTO[] utDTO = null;

					if (cbUTBranch.getText().equalsIgnoreCase("All")) {
						utDTO = getUnDeliveredTopayDetailed("%", fromDt,toDate);
					} else {
						branch = cbUTBranch.getText();
						int index = branch.indexOf(" - ");
						branch = branch.substring(index + 3);
						
						 utDTO = getUnDeliveredTopayDetailed(branch,fromDt,toDate);
						
					}

					if (tblUndeliveredTopayDet != null)
						tblUndeliveredTopayDet.removeAll();
					if (utDTO != null) {
						populateUTDetailed(utDTO);
					} else {
						// AdminComposite.display("No Records Found",
						// STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					}

				}
			} else if (cbUTReportType.getText().equalsIgnoreCase("Summary")) {
				// summary

				

				GeneralSummaryDTO[] dto = null;
				// System.out.println("br-->"+branch);
				branch = cbUTBranch.getText();
				if (!branch.equalsIgnoreCase("All")) {
					int index = branch.indexOf(" - ");
					branch = branch.substring(index + 3);
					populateSelectedStations(branch,tblUndeliveredTopay);
				}
				else{
					populateAllStations(tblUndeliveredTopay);
				}

				dto = getUnDeliveredTopay(fromDt,
						toDate, branch);
				
				/*
				 * if(tblUndeliveredTopay != null)
				 * tblUndeliveredTopay.removeAll();
				 */

				if (dto != null) {
					populateUT(dto);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private GeneralSummaryDTO[] getUnDeliveredTopay(Date fromDt, Date toDate,
			String branch) {
		//int monthDiff = 0;
		GeneralSummaryDTO[] dto = null;
		try {
			/*Date curDate = new Date();
			int m1 = year * 12 + month;
	        int m2 = curDate.getYear() * 12 + curDate.getMonth();
	        monthDiff = m2 - m1;
			Date curDate = new Date();
			int m1 = fromDt.getYear() * 12 + fromDt.getMonth();
	        int m2 = curDate.getYear() * 12 + curDate.getMonth();
	        monthDiff = m2 - m1;*/
			Date date = BeanUtil.getThree_month_updated();
		if(fromDt.before(date)){
			System.out.println("in ddr histry-->"+date);
			dto = handler.getUnDeliveredTopayHistory(fromDt,toDate,branch);
		}else{
			dto = handler.getUnDeliveredTopay(fromDt,toDate,branch);
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	
		
	}

	private BookingDTO[] getUnDeliveredTopayDetailed(String branch,
			Date fromDate, Date toDate) {
		//int monthDiff = 0;
		BookingDTO[] utDTO = null;
		try {
			/*Date curDate = new Date();
			int m1 = year * 12 + month;
	        int m2 = curDate.getYear() * 12 + curDate.getMonth();
	        monthDiff = m2 - m1;*/
			/*Date curDate = new Date();
			int m1 = fromDate.getYear() * 12 + fromDate.getMonth();
	        int m2 = curDate.getYear() * 12 + curDate.getMonth();
	        monthDiff = m2 - m1;*/
			Date date = BeanUtil.getThree_month_updated();
		if(fromDate.before(date)){
			System.out.println("in ddr histry-->"+date);
			utDTO = handler.getUnDeliveredTopayDetailedHistory(branch,fromDate,toDate);
		}else{
			utDTO = handler.getUnDeliveredTopayDetailed(branch,fromDate,toDate);
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return utDTO;
	
	}

	private void handleCCC() {

		if (cbCCCBranch.getSelectionIndex() != -1) {
			String branch = "";

			if (handler != null) {
				try {
					branch = cbCCCBranch.getText();
					int index = branch.indexOf(" - ");
					branch = branch.substring(index + 3);

					GeneralSummaryDTO[] cccSummary = null;
					GeneralSummaryDTO[] cccSummaryPDTP = null;
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Date fromDt = dateFormat.parse(txtCCCFrom.getText());
					Date toDate = dateFormat.parse(txtCCCTo.getText());

					if (!cbCCCBranch.getText().equalsIgnoreCase("All")) {
						
						populateSelectedStations(branch, tblCCCSummary);
						
						 cccSummary = getCCChargeSummary(fromDt, toDate, branch);
						
						 cccSummaryPDTP = getCCCSummaryPDTP(fromDt, toDate, branch);
						
						
					} else {
						
						populateAllStations(tblCCCSummary);
						
						cccSummary = getCCChargeSummary(fromDt, toDate,	"%");
						
						cccSummaryPDTP = getCCCSummaryPDTP(fromDt,toDate, "%");
					}

									
					// System.out.println("summ-->"+cccSummary+"pddt-->"+cccSummaryPDTP);
					if (cccSummary != null) {
						populateCCCSummary(cccSummary);
					}

					if (cccSummaryPDTP != null) {
						populateCCCSummaryPDTP(cccSummaryPDTP);
					}

					calcCCSummaryTotal();
					fillZerosOnEmpty(tblCCCSummary);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	private GeneralSummaryDTO[] getCCCSummaryPDTP(Date fromDt, Date toDate,
			String branch) {
		
		//int monthDiff = 0;
		GeneralSummaryDTO[] cccSummaryPDTP = null;

		try {
			/*Date curDate = new Date();
			int m1 = fromDt.getYear() * 12 + fromDt.getMonth();
	        int m2 = curDate.getYear() * 12 + curDate.getMonth();
	        monthDiff = m2 - m1;*/
			Date date = BeanUtil.getThree_month_updated();
		if(fromDt.before(date)){
			System.out.println("in ddr histry-->"+date);
			cccSummaryPDTP = handler.getCCCSummaryPDTPHistory(fromDt, toDate, branch);
		}else{
			cccSummaryPDTP = handler.getCCCSummaryPDTP(fromDt, toDate, branch);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cccSummaryPDTP;

		
	}

	private GeneralSummaryDTO[] getCCChargeSummary(Date fromDt, Date toDate,
			String branch) {
		
		//int monthDiff = 0;
		GeneralSummaryDTO[] cccSummary = null;
		
		try {			

			/*Date curDate = new Date();
			int m1 = fromDt.getYear() * 12 + fromDt.getMonth();
	        int m2 = curDate.getYear() * 12 + curDate.getMonth();
	        monthDiff = m2 - m1;*/
			Date date = BeanUtil.getThree_month_updated();
		if(fromDt.before(date)){
			System.out.println("in ddr histry-->"+date);
			cccSummary = handler.getCCChargeSummaryHistory(fromDt, toDate, branch);
		}else{
			cccSummary = handler.getCCChargeSummary(fromDt, toDate, branch);
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cccSummary;

		
	}
	private void populateBPAReport(BookingDTO[] dto, Date from,
			Date to) throws Exception {
		DecimalFormat df = new DecimalFormat("0.00");
		int len = dto.length;
		
		String Reptype = cbBPAReportType.getText();
		Boolean isConsolidated = false;
		if(Reptype.equalsIgnoreCase("Consolidated")) {
			 populateStAllBranches(tblBPA);
			 isConsolidated = true;
		 } else{
			 if (cbBPABranch.getText().equalsIgnoreCase("All")){
				 populateStAllStations(tblBPA);
			 }else{
				 	String branch_Code = cbBPABranch.getText();
					int index = branch_Code.indexOf(" - ");
					branch_Code = branch_Code.substring(index + 3);
					populateStSelectedStations(branch_Code, tblBPA);
				 
			 }
				 
		 }
		
		Boolean isBranch = false; 
		
		TableItem[] item = tblBPA.getItems();
		
		int itemlen = item.length;
		int lr_no[] = new int[itemlen]; 
		int noa[] = new int[itemlen];
		float Actwt[] = new float[itemlen];
		float Crwt[] = new float[itemlen];
		float bft[] = new float[itemlen];
		float lrc[] = new float[itemlen];
		float dhc[] = new float[itemlen];
		float ccc[] = new float[itemlen];
		float dcc[] = new float[itemlen];
		float ddc[] = new float[itemlen];
		float iec[] = new float[itemlen];
		float lc[] = new float[itemlen];
		float stax[] = new float[itemlen];
		float tot[] = new float[itemlen];
		String stn_code = "";
		
		
		
		
		for (int i = 0; i < len; i++) {
			
			for(int j = 0; j < itemlen; j++){
				
							
				if(isConsolidated == true) {
				 	stn_code = getBranch_code(dto[i].getFrom());
				 	isBranch = true;
				}
				else
					stn_code = dto[i].getFrom();
				
				String branchCode = item[j].getText(1);
				int index = branchCode.indexOf(" - ");
				branchCode = branchCode.substring(index + 3);
				
				if (stn_code.equalsIgnoreCase(branchCode)){
					if(isBranch == true){
						
						
						if(! item[j].getText(2).equalsIgnoreCase(" ")){
							
							lr_no[j] = lr_no[j] + dto[i].getRate_type();
							item[j].setText(2, String.valueOf(lr_no[j]));
						}
						if(! item[j].getText(3).equalsIgnoreCase(" ")){
							noa[j] = noa[j] + dto[i].getNo_of_articles();
							item[j].setText(3, String.valueOf(noa[j]));
						}
						if(! item[j].getText(4).equalsIgnoreCase(" ")){
							Actwt[j] = Actwt[j] + dto[i].getActual_weight();
							item[j].setText(4, df.format((Actwt[j])));
						}
						if(! item[j].getText(5).equalsIgnoreCase(" ")){
							Crwt[j] = Crwt[j] + dto[i].getCharged_weight();
							item[j].setText(5, df.format((Crwt[j])));
						}
						if(! item[j].getText(6).equalsIgnoreCase(" ")){
							bft[j] = bft[j] + dto[i].getBft();
							item[j].setText(6, df.format((bft[j])));
						}
						if(! item[j].getText(7).equalsIgnoreCase(" ")){
							lrc[j] = lrc[j] + dto[i].getLrc();
							item[j].setText(7, df.format((lrc[j])));
						}
						if(! item[j].getText(8).equalsIgnoreCase(" ")){
							dhc[j] = dhc[j] + dto[i].getDhc();
							item[j].setText(8, df.format((dhc[j])));
						}
						if(! item[j].getText(9).equalsIgnoreCase(" ")){
							ccc[j] = ccc[j] + dto[i].getCcc();
							item[j].setText(9, df.format((ccc[j])));
						}
						if(! item[j].getText(10).equalsIgnoreCase(" ")){
							dcc[j] = dcc[j] + dto[i].getDcc();
							item[j].setText(10, df.format((dcc[j])));
						}
						if(! item[j].getText(11).equalsIgnoreCase(" ")){
							ddc[j] = ddc[j] + dto[i].getDdc();
							item[j].setText(11, df.format((ddc[j])));
						}
						if(! item[j].getText(12).equalsIgnoreCase(" ")){
							//System.out.println("Iec-->"+iec[j]);
							iec[j] = iec[j] + dto[i].getIec();
							item[j].setText(12, df.format((iec[j])));
						}
						if(! item[j].getText(13).equalsIgnoreCase(" ")){
							//System.out.println("lc-->"+lc[j]);
							lc[j] = lc[j] + dto[i].getLc();
							item[j].setText(13, df.format((lc[j])));
						}
						if(! item[j].getText(14).equalsIgnoreCase(" ")){
							stax[j] = stax[j] + dto[i].getStax();
							item[j].setText(14, df.format((stax[j])));
						}
						if(! item[j].getText(15).equalsIgnoreCase(" ")){
							tot[j] = tot[j] + dto[i].getTotal();
							item[j].setText(15, df.format((tot[j])));
						}				
						
									
					}
					else{
						
						if(! item[j].getText(2).equalsIgnoreCase(" ")){
							
							item[j].setText(2, String.valueOf(dto[i].getRate_type()));
						}
						if(! item[j].getText(3).equalsIgnoreCase(" ")){
							
							item[j].setText(3, String.valueOf(dto[i].getNo_of_articles()));
						}
						if(! item[j].getText(4).equalsIgnoreCase(" ")){
							
							item[j].setText(4, df.format((dto[i].getActual_weight())));
						}
						if(! item[j].getText(5).equalsIgnoreCase(" ")){
							
							item[j].setText(5, df.format((dto[i].getCharged_weight())));
						}
						if(! item[j].getText(6).equalsIgnoreCase(" ")){
							
							item[j].setText(6, df.format((dto[i].getBft())));
						}
						if(! item[j].getText(7).equalsIgnoreCase(" ")){
							
							item[j].setText(7, df.format((dto[i].getLrc())));
						}
						if(! item[j].getText(8).equalsIgnoreCase(" ")){
							
							item[j].setText(8, df.format((dto[i].getDhc())));
						}
						if(! item[j].getText(9).equalsIgnoreCase(" ")){
							
							item[j].setText(9, df.format((dto[i].getCcc())));
						}
						if(! item[j].getText(10).equalsIgnoreCase(" ")){
							
							item[j].setText(10, df.format((dto[i].getDcc())));
						}
						if(! item[j].getText(11).equalsIgnoreCase(" ")){
							
							item[j].setText(11, df.format((dto[i].getDdc())));
						}
						if(! item[j].getText(12).equalsIgnoreCase(" ")){
							
							item[j].setText(12, df.format((dto[i].getIec())));
						}
						if(! item[j].getText(13).equalsIgnoreCase(" ")){
							
							item[j].setText(13, df.format((dto[i].getLc())));
						}
						if(! item[j].getText(14).equalsIgnoreCase(" ")){
							
							item[j].setText(14, df.format((dto[i].getStax())));
						}
						if(! item[j].getText(15).equalsIgnoreCase(" ")){
							
							item[j].setText(15, df.format((dto[i].getTotal())));
						}				
					
							
						
					}
					
				}
				
			}
				
		}
		
		calculatTotal();
	
		
	}
	
	private void calculatTotal() {
		// TODO Auto-generated method stub
			TableItem[] items = tblBPA.getItems();
			int tot1 = 0;
			int tot2 = 0;
			double tot3 = 0;
			double tot4 = 0;
			double tot5 = 0;
			double tot6 = 0;
			double tot7 = 0;
			double tot8 = 0;
			double tot9 = 0;
			double tot10 = 0;
			double tot11 = 0;
			double tot12 = 0;
			double tot13 = 0;
			double tot14 = 0;
			
			if (items != null) {
				for (int i = 0; i < items.length; i++) {
					
					if (!items[i].getText(2).equals(""))
						tot1 = tot1 + Integer.parseInt(items[i].getText(2));
					if (!items[i].getText(3).equals(""))
						tot2 = tot2 + Integer.parseInt(items[i].getText(3));
					if (!items[i].getText(4).equals(""))
						tot3 = tot3 + (double) Float.parseFloat(items[i].getText(4));
					if (!items[i].getText(5).equals(""))
						tot4 = tot4 + (double) Float.parseFloat(items[i].getText(5));
					if (!items[i].getText(6).equals(""))
						tot5 = tot5 + (double) Float.parseFloat(items[i].getText(6));
					if (!items[i].getText(7).equals(""))
						tot6 = tot6 + (double) Float.parseFloat(items[i].getText(7));
					if (!items[i].getText(8).equals(""))
						tot7 = tot7 + (double) Float.parseFloat(items[i].getText(8));
					if (!items[i].getText(9).equals(""))
						tot8 = tot8 + (double) Float.parseFloat(items[i].getText(9));
					if (!items[i].getText(10).equals(""))
						tot9 = tot9 + (double) Float.parseFloat(items[i].getText(10));
					if (!items[i].getText(11).equals(""))
						tot10 = tot10 + (double) Float.parseFloat(items[i].getText(11));
					if (!items[i].getText(12).equals(""))
						tot11 = tot11 + (double) Float.parseFloat(items[i].getText(12));
					if (!items[i].getText(13).equals(""))
						tot12 = tot12 + (double) Float.parseFloat(items[i].getText(13));
					if (!items[i].getText(14).equals(""))
						tot13 = tot13 + (double) Float.parseFloat(items[i].getText(14));
					if (!items[i].getText(15).equals(""))
						tot14 = tot14 + (double) Float.parseFloat(items[i].getText(15));
					
					
					
				}

			}

			final TableItem item1 = new TableItem(tblBPA, SWT.NONE);
			Font font1 = new Font(Display.getCurrent(), "Tahoma", 8, SWT.BOLD);
			item1.setFont(font1);
			item1.setText(1, "TOTAL");
			item1.setText(2, getRoundedValue(tot1));
			item1.setText(3, getRoundedValue(tot2));
			item1.setText(4, getRoundValue(tot3));
			item1.setText(5, getRoundValue(tot4));
			item1.setText(6, getRoundValue(tot5));
			item1.setText(7, getRoundValue(tot6));
			item1.setText(8, getRoundValue(tot7));
			item1.setText(9, getRoundValue(tot8));
			item1.setText(10, getRoundValue(tot9));
			item1.setText(11, getRoundValue(tot10));
			item1.setText(12, getRoundValue(tot11));
			item1.setText(13, getRoundValue(tot12));
			item1.setText(14, getRoundValue(tot13));
			item1.setText(15, getRoundValue(tot14));
			
			fillZerosOnEmpty(tblBPA);
	}


	/*private void populateBPAReport(BusinessPerformanceDTO[] bpaDto, Date from,
			Date to) {
		int len = bpaDto.length;
		float avg = 0;
		int lrIndex = 0;
		int itemsLen = 0;
		String station = "";
		int days = 0;
		days = bpaDto[0].getWorking_days();
		// System.out.println("day-->"+bpaDto[0].getWorking_days());
		boolean isExist = false;
		int matched = 0;
		boolean isIntValue = false;
		if (len > 0) {
			if (cbBPAChoice.getText().equalsIgnoreCase("Count")
					|| cbBPAChoice.getText().equalsIgnoreCase("Noa")) {
				isIntValue = true;
			}
			for (int i = 0, j = 1; i < len; i++) {
				isExist = false;
				lrIndex = bpaDto[i].getTbl_index();
				TableItem[] items = tblBPA.getItems();
				itemsLen = tblBPA.getItemCount();
				if (itemsLen > 0) {
					itemsLen = items.length;
					for (int k = 0; k < itemsLen; k++) {
						station = items[k].getText(1);
						String stnCode = bpaDto[i].getStation();
						if (station.equalsIgnoreCase(getStationName(stnCode)
								+ " - " + stnCode)) {
							isExist = true;
							matched = k;
							break;
						}
					}

					if (isExist) {

						TableItem item = items[matched];
						if (cbBPAChoice.getText().equalsIgnoreCase(COUNT)) {
							item.setText(lrIndex, String.valueOf(bpaDto[i]
									.getCount()));
							if (!item.getText(2).equals("")) {
								avg = Float.parseFloat(item.getText(2));
								item.setText(3, getRoundedValue(avg / days));
							}
						} else if (cbBPAChoice.getText().equalsIgnoreCase(
								BASIC_FREIGHT)) {
							item.setText(lrIndex, getRoundedValue(bpaDto[i]
									.getBasic_freight()));
							if (!item.getText(2).equals("")) {
								avg = Float.parseFloat(item.getText(2));
								item.setText(3, getRoundedValue(avg / days));
							}
						} else if (cbBPAChoice.getText().equalsIgnoreCase(
								TOTAL_FREIGHT)) {
							item.setText(lrIndex, getRoundedValue(bpaDto[i]
									.getTotal_freight()));
							if (!item.getText(2).equals("")) {
								avg = Float.parseFloat(item.getText(2));
								item.setText(3, getRoundedValue(avg / days));
							}
						} else if (cbBPAChoice.getText().equalsIgnoreCase(
								ACTUAL_WEIGHT)) {
							item.setText(lrIndex, getRoundedValue(bpaDto[i]
									.getActual_weight()));
							if (!item.getText(2).equals("")) {
								avg = Float.parseFloat(item.getText(2));
								item.setText(3, getRoundedValue(avg / days));
							}
						} else if (cbBPAChoice.getText().equalsIgnoreCase(
								CHARGED_WEIGHT)) {
							item.setText(lrIndex, getRoundedValue(bpaDto[i]
									.getCharged_weight()));
							if (!item.getText(2).equals("")) {
								avg = Float.parseFloat(item.getText(2));
								item.setText(3, getRoundedValue(avg / days));
							}
						} else if (cbBPAChoice.getText().equalsIgnoreCase(NOA)) {
							item.setText(lrIndex, String.valueOf(bpaDto[i]
									.getNoa()));
							if (!item.getText(2).equals("")) {
								avg = Float.parseFloat(item.getText(2));
								item.setText(3, getRoundedValue(avg / days));
							}
						}

					} else {
						TableItem item = new TableItem(tblBPA, SWT.NONE);
						item.setText(0, String.valueOf(j));
						String stnCode = bpaDto[i].getStation();
						item.setText(1, getStationName(stnCode) + " - "
								+ stnCode);
						if (cbBPAChoice.getText().equalsIgnoreCase(COUNT)) {
							item.setText(lrIndex, String.valueOf(bpaDto[i]
									.getCount()));
							if (!item.getText(2).equals("")) {
								avg = Float.parseFloat(item.getText(2));
								item.setText(3, getRoundedValue(avg / days));
							}

						} else if (cbBPAChoice.getText().equalsIgnoreCase(
								BASIC_FREIGHT)) {
							item.setText(lrIndex, getRoundedValue(bpaDto[i]
									.getBasic_freight()));
							if (!item.getText(2).equals("")) {
								avg = Float.parseFloat(item.getText(2));
								item.setText(3, getRoundedValue(avg / days));
							}
							item.setText(8, getRoundedValue(bpaDto[i]
									.getHigh_basic_freight()));
						} else if (cbBPAChoice.getText().equalsIgnoreCase(
								TOTAL_FREIGHT)) {
							item.setText(lrIndex, getRoundedValue(bpaDto[i]
									.getTotal_freight()));
							if (!item.getText(2).equals("")) {
								avg = Float.parseFloat(item.getText(2));
								item.setText(3, getRoundedValue(avg / days));
							}
							item.setText(8, getRoundedValue(bpaDto[i]
									.getHigh_total_freight()));
						} else if (cbBPAChoice.getText().equalsIgnoreCase(
								ACTUAL_WEIGHT)) {
							item.setText(lrIndex, getRoundedValue(bpaDto[i]
									.getActual_weight()));
							if (!item.getText(2).equals("")) {
								avg = Float.parseFloat(item.getText(2));
								item.setText(3, getRoundedValue(avg / days));
							}
							item.setText(8, getRoundedValue(bpaDto[i]
									.getHigh_actual_weight()));
						} else if (cbBPAChoice.getText().equalsIgnoreCase(
								CHARGED_WEIGHT)) {
							item.setText(lrIndex, getRoundedValue(bpaDto[i]
									.getCharged_weight()));
							if (!item.getText(2).equals("")) {
								avg = Float.parseFloat(item.getText(2));
								item.setText(3, getRoundedValue(avg / days));
							}
							item.setText(8, getRoundedValue(bpaDto[i]
									.getHigh_charged_weight()));
						} else if (cbBPAChoice.getText().equalsIgnoreCase(NOA)) {
							item.setText(lrIndex, String.valueOf(bpaDto[i]
									.getNoa()));
							if (!item.getText(2).equals("")) {
								avg = Float.parseFloat(item.getText(2));
								item.setText(3, getRoundedValue(avg / days));
							}
							item.setText(8, String.valueOf(bpaDto[i]
									.getHigh_noa()));
						}

						j++;

					}
				} else {
					TableItem item = new TableItem(tblBPA, SWT.NONE);
					item.setText(0, String.valueOf(j));
					String stnCode = bpaDto[i].getStation();
					item.setText(1, getStationName(stnCode) + " - " + stnCode);
					if (cbBPAChoice.getText().equalsIgnoreCase(COUNT)) {
						item.setText(lrIndex, String.valueOf(bpaDto[i]
								.getCount()));
						if (!item.getText(2).equals("")) {
							avg = Float.parseFloat(item.getText(2));
							item.setText(3, getRoundedValue(avg / days));
						}
					} else if (cbBPAChoice.getText().equalsIgnoreCase(
							BASIC_FREIGHT)) {
						item.setText(lrIndex, getRoundedValue(bpaDto[i]
								.getBasic_freight()));
						if (!item.getText(2).equals("")) {
							avg = Float.parseFloat(item.getText(2));
							item.setText(3, getRoundedValue(avg / days));
						}
						item.setText(8, getRoundedValue(bpaDto[i]
								.getHigh_basic_freight()));
					} else if (cbBPAChoice.getText().equalsIgnoreCase(
							TOTAL_FREIGHT)) {
						item.setText(lrIndex, getRoundedValue(bpaDto[i]
								.getTotal_freight()));
						if (!item.getText(2).equals("")) {
							avg = Float.parseFloat(item.getText(2));
							item.setText(3, getRoundedValue(avg / days));
						}
						item.setText(8, getRoundedValue(bpaDto[i]
								.getHigh_total_freight()));
					} else if (cbBPAChoice.getText().equalsIgnoreCase(
							ACTUAL_WEIGHT)) {
						item.setText(lrIndex, getRoundedValue(bpaDto[i]
								.getActual_weight()));
						if (!item.getText(2).equals("")) {
							avg = Float.parseFloat(item.getText(2));
							item.setText(3, getRoundedValue(avg / days));
						}
						item.setText(8, getRoundedValue(bpaDto[i]
								.getHigh_actual_weight()));
					} else if (cbBPAChoice.getText().equalsIgnoreCase(
							CHARGED_WEIGHT)) {
						item.setText(lrIndex, getRoundedValue(bpaDto[i]
								.getCharged_weight()));
						if (!item.getText(2).equals("")) {
							avg = Float.parseFloat(item.getText(2));
							item.setText(3, getRoundedValue(avg / days));
						}
						item.setText(8, String.valueOf(bpaDto[i]
								.getHigh_charged_weight()));
					} else if (cbBPAChoice.getText().equalsIgnoreCase(NOA)) {
						item.setText(lrIndex, String
								.valueOf(bpaDto[i].getNoa()));
						if (!item.getText(2).equals("")) {
							avg = Float.parseFloat(item.getText(2));
							item.setText(3, getRoundedValue(avg / days));
						}
						item
								.setText(8, String.valueOf(bpaDto[i]
										.getHigh_noa()));
					}

					j++;
				}

			}

			calcBPAdifference(isIntValue);
			
			fillZerosOnEmpty(tblBPA);

		}		

	}*/

	private void fillZerosOnEmpty(Table refTbl) {
		
		TableItem[] items = refTbl.getItems();
		int len = items.length;		
		if (len > 0) {
			if(refTbl == tblIIStates || refTbl == tblInwardAnalysis|| refTbl == tblDRSAttendance){
				len+=1;
			}
			for (int i = 0; i < len-1; i++) {
				for (int j = 1; j < refTbl.getColumnCount(); j++) {
					if (items[i].getText(j).equalsIgnoreCase("")
							|| items[i].getText(j).equalsIgnoreCase("0.00")) {
						items[i].setText(j, "0");
					}
				}
			}
		}
	}

	/*private void calcBPAdifference(boolean isInt) {
		TableItem[] items = tblBPA.getItems();
		float curr = 0;
		float lastMon = 0;
		float lastYr = 0;
		String result = "";

		float floatTotal = 0;
		float floatLM = 0;
		float floatLY = 0;
		float floatHigh = 0;
		float floatavgTot=0;
		float floataverage=0;
		
		int intTotal = 0;
		int intLM = 0;
		int intLY = 0;
		int intHigh = 0;
		//int intavgTot=0;
		//int intaverage=0;
		int count=0;
		for (int i = 0; i < items.length; i++) {
			count++;
			curr = 0;
			lastMon = 0;
			lastYr = 0;
			result = "";
			if (!items[i].getText(2).equals(""))
				curr = Float.parseFloat(items[i].getText(2));
			if (!items[i].getText(4).equals(""))
				lastMon = Float.parseFloat(items[i].getText(4));
			if (!items[i].getText(6).equals(""))
				lastYr = Float.parseFloat(items[i].getText(6));

			result = getRoundedValue((curr - lastMon));
			if (result.charAt(0) != '-' && (curr - lastMon) != 0) {
				result = "+" + result;
			}
			items[i].setText(5, result);
			result = "";
			result = getRoundedValue((curr - lastYr));
			if (result.charAt(0) != '-' && (curr - lastYr) != 0) {
				result = "+" + result;
			}
			items[i].setText(7, result);

			if (!items[i].getText(2).equals("")) {
				if (isInt) {
					intTotal = intTotal + Integer.parseInt(items[i].getText(2));
				} else {
					floatTotal = floatTotal
							+ Float.parseFloat(items[i].getText(2));
				}
			}
			
			if (!items[i].getText(3).equals("")) {
				
				floatavgTot = floatavgTot
							+ Float.parseFloat(items[i].getText(3));
				
			}

			if (!items[i].getText(4).equals("")) {
				if (isInt) {
					intLM = intLM + Integer.parseInt(items[i].getText(4));
				} else {
					floatLM = floatLM + Float.parseFloat(items[i].getText(4));
				}
			}

			if (!items[i].getText(6).equals("")) {
				if (isInt) {
					intLY = intLY + Integer.parseInt(items[i].getText(6));
				} else {
					floatLY = floatLY + Float.parseFloat(items[i].getText(6));
				}
			}

			if (!items[i].getText(8).equals("")) {
				if (isInt) {
					intHigh = intHigh + Integer.parseInt(items[i].getText(8));
				} else {
					floatHigh = floatHigh
							+ Float.parseFloat(items[i].getText(8));
				}
			}

		}
		
		floataverage = floatavgTot / count;
		final TableItem item1 = new TableItem(tblBPA, SWT.NONE);
		Font font1 = new Font(Display.getCurrent(), "Tahoma", 8, SWT.BOLD);
		item1.setFont(font1);
		item1.setText(1, "TOTAL");
		final TableItem item2 = new TableItem(tblBPA, SWT.NONE);
		item2.setFont(font1);
		item2.setText(2, "Average");
		if (isInt) {
			item1.setText(2, String.valueOf(intTotal));
			item1.setText(4, String.valueOf(intLM));
			item1.setText(5," ");
			item1.setText(6, String.valueOf(intLY));
			item1.setText(7," ");
			item1.setText(8, String.valueOf(intHigh));
		} else {
			item1.setText(2, getRoundedValue(floatTotal));
			item1.setText(4, getRoundedValue(floatLM));
			item1.setText(5," ");
			item1.setText(6, getRoundedValue(floatLY));
			item1.setText(7," ");
			item1.setText(8, getRoundedValue(floatHigh));
			
		}
		
		item1.setText(3, String.valueOf(getRoundedValue(floatavgTot)));
		item2.setText(3, String.valueOf(getRoundedValue(floataverage)));
	}*/

	private void handleBPA() {
		String branch = "";
		try {
			if (!cbBPABranch.getText().equals("")) {
				SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
				Date from = dt.parse(txtBPAFrom.getText());
				Date to = dt.parse(txtBPATo.getText());
				branch = cbBPABranch.getText();

				if (branch.equalsIgnoreCase("All")) {
						branch = "All";
					

				} else {
					int index = branch.indexOf(" - ");
					branch = branch.substring(index + 3);
				}
				BookingDTO[] bpaDto = getBPAReport(from, to, branch);

				if (tblBPA != null)
					tblBPA.removeAll();

				if (bpaDto != null) {
					populateBPAReport(bpaDto, from, to);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private BookingDTO[] getBPAReport(Date from, Date to,String branch)throws Exception {
		//int monthDiff = 0;
		try {
			
			
			/*Date curDate = new Date();
			int m1 = from.getYear() * 12 + from.getMonth();
	        int m2 = curDate.getYear() * 12 + curDate.getMonth();
	        monthDiff = m2 - m1;*/
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		Date date =BeanUtil.getThree_month_updated();
		if(from.before(date)){
			//System.out.println("in ddr histry-->"+date);
			bpaDto = handler.getBPAReportHistory(from, to, branch);
		}else{
			bpaDto = handler.getBPAReport(from, to, branch);
		}
		return bpaDto;
	}

	private void handleRS() {
		String value = "";
		String branch = "";
		try {
			if (!cbRSBranch.getText().equals("")
					&& !txtRSMonth.getText().equals("")) {
				value = cbRSBranch.getText();
				int index = value.indexOf(" - ");
				branch = value.substring(index + 3);
				String year = txtRSMonth.getText();
				int index1 = year.indexOf("-");
				String month = year.substring(0, index1);
				year = year.substring(index1 + 1);
				
				RemittanceShortageDTO[] dto = getRSReport(branch, Integer.parseInt(month),
						Integer.parseInt(year));
				

				if (tblRemittance != null)
					tblRemittance.removeAll();

				if (dto != null) {
					populateRSReport(dto);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	

	private RemittanceShortageDTO[] getRSReport(String branch, int month,
			int year) {
		int monthDiff = 0;
		try {
			Date curDate = new Date();
			int m1 = year * 12 + month;
	        int m2 = curDate.getYear() * 12 + curDate.getMonth();
	        monthDiff = m2 - m1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(monthDiff > 3){
			//System.out.println("in ddr histry-->"+date);
			dto = handler.getRSReportHistory(branch,month,year);
		}else{
			dto = handler.getRSReport(branch,month,year);
		}
		return dto;
	}

	private void populateRSReport(RemittanceShortageDTO[] dto) {
		int len = dto.length;

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		if (len > 0) {
			for (int i = 0, j = 1; i < len; i++) {
				if (rdFull.getSelection()) {
					TableItem item = new TableItem(tblRemittance, SWT.NONE);
					item.setText(0, String.valueOf(j));
					item.setText(1, dto[i].getFromStation());
					item.setText(2, dto[i].getToStation());
					item.setText(3, dto[i].getLrNo());
					item.setText(4, dateFormat.format(dto[i].getLrDate()));
					item.setText(5, dto[i].getLrType());
					item.setText(6, dto[i].getDrsNo());
					item.setText(7, dateFormat.format(dto[i].getDrsDate()));
					if (dto[i].getCrNo() != null)
						item.setText(8, dto[i].getCrNo());
					item.setText(9, dto[i].getCnor());
					item.setText(10, dto[i].getCnee());
					item.setText(11, getRoundedValue(dto[i].getAmount()));
					if (dto[i].getRecoveryDate() != null)
						item.setText(12, dateFormat.format(dto[i]
								.getRecoveryDate()));
					j++;
				} else {
					if (dto[i].getRecoveryDate() == null) {
						TableItem item = new TableItem(tblRemittance, SWT.NONE);
						item.setText(0, String.valueOf(j));
						item.setText(1, dto[i].getFromStation());
						item.setText(2, dto[i].getToStation());
						item.setText(3, dto[i].getLrNo());
						item.setText(4, dateFormat.format(dto[i].getLrDate()));
						item.setText(5, dto[i].getLrType());
						item.setText(6, dto[i].getDrsNo());
						item.setText(7, dateFormat.format(dto[i].getDrsDate()));
						if (dto[i].getCrNo() != null)
							item.setText(8, dto[i].getCrNo());
						item.setText(9, dto[i].getCnor());
						item.setText(10, dto[i].getCnee());
						item.setText(11, getRoundedValue(dto[i].getAmount()));
						j++;
					}
				}

			}

			calcRSTotal();

		}

	}

	private void calcRSTotal() {
		TableItem[] items = tblRemittance.getItems();
		float totAmt = 0;
		if (items != null) {
			for (int i = 0; i < items.length; i++) {
				totAmt = totAmt + Float.parseFloat(items[i].getText(11));
			}

		}

		final TableItem item1 = new TableItem(tblRemittance, SWT.NONE);
		Font font1 = new Font(Display.getCurrent(), "Tahoma", 8, SWT.BOLD);
		item1.setFont(font1);
		item1.setText(10, "TOTAL");
		item1.setText(11, getRoundedValue(totAmt));

	}

	private void handleDDE() {
		try {
			float limit = 0;
			if (!txtDDEAbove.getText().equals("")) {
				limit = Float.parseFloat(txtDDEAbove.getText());
			}

			DDRDTO[] ddDetails = getDDReport();
			if (tblDDExtra != null)
				tblDDExtra.removeAll();

			if (ddDetails != null) {
				populateDDReport(ddDetails, limit);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void populateDDReport(DDRDTO[] ddDetails, float limit) {
		int len = ddDetails.length;
		float dd = 0;
		float dde = 0;
		float others = 0;
		int count=0;
		float totBft = 0;
		float totDDC = 0;
		float totDDE = 0;
		float totOthers = 0;
		float totTotal = 0;
		float totPercent = 0;
		float totPercentavg=0;
		if (len > 0) {
			for (int i = 0, j = 1; i < len; i++) {
				dd = ddDetails[i].getDdc();
				dde = ddDetails[i].getDdExtra();
				others = ddDetails[i].getOthers();
			
				if ((dd >= limit || others >= limit) || dde >= limit) {
					TableItem item = new TableItem(tblDDExtra, SWT.NONE);
					item.setText(0, String.valueOf(j));
					item.setText(1, ddDetails[i].getLrNo());
					item.setText(2, ddDetails[i].getFromStation());
					item.setText(3, ddDetails[i].getLrType());
					totBft = totBft + ddDetails[i].getBasicFreight();
					item.setText(4, getRoundedValue(ddDetails[i]
							.getBasicFreight()));
					totDDC = totDDC + ddDetails[i].getDdc();
					item.setText(5, getRoundedValue(ddDetails[i].getDdc()));
					totDDE = totDDE + ddDetails[i].getDdExtra();
					item.setText(6, getRoundedValue(ddDetails[i].getDdExtra()));
					totOthers = totOthers + ddDetails[i].getOthers();
					item.setText(7, getRoundedValue(ddDetails[i].getOthers()));
					totTotal = totTotal + ddDetails[i].getTotal();
					item.setText(8, getRoundedValue(ddDetails[i].getTotal()));

					item.setText(9, getRoundedValue(((ddDetails[i].getDdc()
							+ ddDetails[i].getDdExtra() + ddDetails[i]
							.getOthers()) / ddDetails[i].getTotal()) * 100));

					totPercent = totPercent + Float.valueOf(item.getText(9));
					count++;
					j++;
				}
			}
			
			totPercentavg = totPercent /count ;
			final TableItem item1 = new TableItem(tblDDExtra, SWT.NONE);
			Font font1 = new Font(Display.getCurrent(), "Tahoma", 8, SWT.BOLD);
			item1.setFont(font1);
			item1.setText(3, "TOTAL");
			item1.setText(4, getRoundedValue(totBft));
			item1.setText(5, getRoundedValue(totDDC));
			item1.setText(6, getRoundedValue(totDDE));
			item1.setText(7, getRoundedValue(totOthers));
			item1.setText(8, getRoundedValue(totTotal));
			item1.setText(9, getRoundedValue(totPercent));
			final TableItem item2 = new TableItem(tblDDExtra, SWT.NONE);
			item2.setFont(font1);
			item2.setText(2, "Average");
			item2.setText(9, getRoundedValue(totPercentavg));
			
			
		}

	}

	private String getRoundedValue(float cardRate) {
		DecimalFormat decimalFormat;
		decimalFormat = new DecimalFormat("0.00");
		cardRate = cardRate * 100;
		cardRate = (float) Math.round(cardRate);
		cardRate = cardRate / 100;

		return decimalFormat.format(cardRate);
	}
	
	private String getRoundValue(double cardRate) {
		DecimalFormat decimalFormat;
		decimalFormat = new DecimalFormat("0.00");
		cardRate = cardRate * 100;
		cardRate = (double) Math.round(cardRate);
		cardRate = cardRate / 100;

		return decimalFormat.format(cardRate);
	}

	private DDRDTO[] getDDReport() {
		DDRDTO[] ddDetails = null;
		String stnCode = "";
		String branchCode = "";
		try {
			if (!cbDDEStation.getText().equals("")) {
				SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
				Date from = dt.parse(txtDDEFrom.getText());
				Date to = dt.parse(txtDDETo.getText());
				stnCode = cbDDEStation.getText();
				branchCode = cbDDEBranch.getText();
				if((branchCode.equalsIgnoreCase("All")) &&(stnCode.equalsIgnoreCase("All"))){
					stnCode = "%";
				}
				else if (!stnCode.equalsIgnoreCase("All")) {
					int index = stnCode.indexOf(" - ");
					stnCode = stnCode.substring(index + 3);
				} else {
					int index = branchCode.indexOf(" - ");
					branchCode = branchCode.substring(index + 3);

					stnCode = "All-" + branchCode;
				}

				// System.out.println("stncode--->"+from+"to-->"+to);

				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Date date = null;
				//int monthDiff = 0;
				try {
					date = dateFormat.parse(txtDDEFrom.getText());
					/*Date curDate = new Date();			
					int m1 = date.getYear() * 12 + date.getMonth();
			        int m2 = curDate.getYear() * 12 + curDate.getMonth();
			        monthDiff = m2 - m1;*/
					
				} catch (ParseException e) {
					e.printStackTrace();
				}
				Date date1 = BeanUtil.getThree_month_updated();
				if(date.before(date1)){
					//System.out.println("in ddr histry-->"+date1);
					ddDetails = handler.getDDReportHistory(from, to, stnCode);
				}else{
					ddDetails = handler.getDDReport(from, to, stnCode);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ddDetails;

	}

	private void handleBranchActionUT(Combo branch, Combo cbStation) {

		StationsDTO[] station = null;
		String value = "";
		String stationCode = "";

		try {
			value = branch.getText();
			if(! value.equalsIgnoreCase("All")){
				
			
			
				int index = value.indexOf(" - ");
				stationCode = value.substring(index + 3);
	
				if (stationCode != null) {
					station = handler.getStations(stationCode);
				}
				if (null != station) {
	
					int len = station.length;
					cbStation.removeAll();
					cbStation.add("All");
					for (int i = 0; i < len; i++) {
						cbStation.add(station[i].getName() + " - "
								+ station[i].getId());
					}
				
				}
			}
		} catch (Exception exception) {

		}
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
					cbStation.add("All");
					for (int i = 0; i < len; i++) {
						cbStation.add(station[i].getName() + " - "
								+ station[i].getId());
					}
				
				}
			
		} catch (Exception exception) {

		}
	}

	private void handleBranchActionDDE(Combo branch, Combo cbStation) {

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
					cbStation.add("All");
					if(!branch.getText().equalsIgnoreCase("All")){
						
						for (int i = 0; i < len; i++) {
							cbStation.add(station[i].getName() + " - "
									+ station[i].getId());
						}
					}
				
				}
			
		} catch (Exception exception) {

		}
	}

	private void populateBranches(Combo cbBranch) {
		StationsDTO[] station = null;
		try {
			//cbBranch.removeAll();
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
						item.setText(1, branchCode);
						item.setText(2, stations[i].getId());

						sNo += 1;
					}
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
	
	private void populateStSelectedStations(String branchCode, Table table) {
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
						item.setText(1, stations[i].getName()+ " - "
								+stations[i].getId());

						sNo += 1;
					}
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void populateAllBranches(Table table) {
		StationsDTO[] stations = null;
		try {

			stations = handler.getAllBranches();

			int size = 0;
			if (null != stations) {
				if (table != null) {
					table.removeAll();
				}
				size = stations.length;
				for (int i = 0; i < size; i++) {

					TableItem item = new TableItem(table, SWT.NONE);
					item.setText(0, String.valueOf(i + 1));
					item.setText(1, stations[i].getBranch_code());
					item.setText(2, stations[i].getId());

				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}

	private void populateAllStations(Table table) {
		StationsDTO[] stations = null;
		try {
			stations = handler.getAllStations();

			int size = 0;
			if (null != stations) {
				if (table != null) {
					table.removeAll();
				}
				size = stations.length;
				for (int i = 0; i < size; i++) {
					TableItem item = new TableItem(table, SWT.NONE);
					item.setText(0, String.valueOf(i + 1));
					item.setText(1, stations[i].getBranch_code());
					item.setText(2, stations[i].getId());
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}

	private void populateCCCSummary(GeneralSummaryDTO[] cccSummary) {
		TableItem[] items = tblCCCSummary.getItems();

		for (int i = 0; i < cccSummary.length; i++) {
			for (int j = 0; j < items.length; j++) {
				if (cccSummary[i].getStation_code().equalsIgnoreCase(
						items[j].getText(2))) {
					items[j].setText(4, String
							.valueOf(cccSummary[i].getLr_no()));
					items[j]
							.setText(6, getRoundedValue(cccSummary[i].getBft()));
					items[j]
							.setText(7, getRoundedValue(cccSummary[i].getCcc()));
					if (cccSummary[i].getBft() != 0) {
						items[j].setText(9, getRoundedValue(cccSummary[i]
								.getCcc()
								/ cccSummary[i].getBft() * 100));
					}
				}
			}
		}

	}

	private void populateCCCSummaryPDTP(GeneralSummaryDTO[] cccSummaryPDTP) {
		TableItem[] items = tblCCCSummary.getItems();

		for (int i = 0; i < cccSummaryPDTP.length; i++) {
			for (int j = 0; j < items.length; j++) {
				if (cccSummaryPDTP[i].getStation_code().equalsIgnoreCase(
						items[j].getText(2))) {
					items[j].setText(3, String.valueOf(cccSummaryPDTP[i]
							.getNo_of_articles()));
					items[j].setText(5, getRoundedValue(cccSummaryPDTP[i]
							.getBft()));
					items[j].setText(8, getRoundedValue(cccSummaryPDTP[i]
							.getCcc()
							/ cccSummaryPDTP[i].getBft() * 100));
				}
			}
		}		

	}

	private void calcCCSummaryTotal() {
		TableItem[] items = tblCCCSummary.getItems();
		int tot3 = 0;
		int tot4 = 0;
		float tot5 = 0;
		float tot6 = 0;
		float tot7 = 0;
		float tot8 = 0;
		float tot9 = 0;
		float tot8average=0;
		float tot9average=0;
		int count=0;
		
		if (items != null) {
			for (int i = 0; i < items.length; i++) {
				if (!items[i].getText(3).equals(""))
					tot3 = tot3 + Integer.parseInt(items[i].getText(3));
				if (!items[i].getText(4).equals(""))
					tot4 = tot4 + Integer.parseInt(items[i].getText(4));
				if (!items[i].getText(5).equals(""))
					tot5 = tot5 + Float.parseFloat(items[i].getText(5));
				if (!items[i].getText(6).equals(""))
					tot6 = tot6 + Float.parseFloat(items[i].getText(6));
				if (!items[i].getText(7).equals(""))
					tot7 = tot7 + Float.parseFloat(items[i].getText(7));
				if (!items[i].getText(8).equals(""))
					tot8 = tot8 + Float.parseFloat(items[i].getText(8));
				if (!items[i].getText(9).equals(""))
					tot9 = tot9 + Float.parseFloat(items[i].getText(9));
				count++;
			}

		}
		
		tot8average = tot8 / count;
		tot9average = tot9 / count;
		final TableItem item1 = new TableItem(tblCCCSummary, SWT.NONE);
		Font font1 = new Font(Display.getCurrent(), "Tahoma", 8, SWT.BOLD);
		item1.setFont(font1);
		item1.setText(2, "TOTAL");
		item1.setText(3, String.valueOf(tot3));
		item1.setText(4, String.valueOf(tot4));
		item1.setText(5, getRoundedValue(tot5));
		item1.setText(6, getRoundedValue(tot6));
		item1.setText(7, getRoundedValue(tot7));
		item1.setText(8, getRoundedValue(tot8));
		item1.setText(9, getRoundedValue(tot9));
		final TableItem item2 = new TableItem(tblCCCSummary, SWT.NONE);
		item2.setFont(font1);
		item2.setText(7, "Average");
		item2.setText(8, getRoundedValue(tot8average));
		item2.setText(9, getRoundedValue(tot9average));
	}

	
	private void populateUT(GeneralSummaryDTO[] dto) throws Exception {

		TableItem[] items = tblUndeliveredTopay.getItems();
		int tot3 = 0;
		float tot4 = 0;
		int tot5 = 0;
		float tot6 = 0;
		
		
		if (items != null) {
			for (int j = 0; j < items.length; j++) {
				int lr_no = 0;
				float Total = 0;
				for (int i = 0; i < dto.length; i++) {
					if (dto[i].getStation_code().equalsIgnoreCase(
							items[j].getText(2))) {
						if ( ! dto[i].getSundry_type().equalsIgnoreCase(
								"Delivered")) {
							items[j].setText(5, String.valueOf(dto[i]
							   									.getLr_no()));
							items[j].setText(6, getRoundedValue(dto[i]
							   									.getTotal()));
						} 
						lr_no = lr_no + dto[i].getLr_no();
						items[j].setText(3, String.valueOf(lr_no));
						Total = Total + dto[i].getTotal();
						items[j].setText(4, getRoundedValue(Total));
							
						
					}
				}

				if (!items[j].getText(3).equals(""))
					tot3 = tot3 + Integer.parseInt(items[j].getText(3));
				if (!items[j].getText(4).equals(""))
					tot4 = tot4 + Float.parseFloat(items[j].getText(4));
				if (!items[j].getText(5).equals(""))
					tot5 = tot5 + Integer.parseInt(items[j].getText(5));
				if (!items[j].getText(6).equals(""))
					tot6 = tot6 + Float.parseFloat(items[j].getText(6));

			}

			final TableItem item1 = new TableItem(tblUndeliveredTopay, SWT.NONE);
			Font font1 = new Font(Display.getCurrent(), "Tahoma", 8, SWT.BOLD);
			item1.setFont(font1);
			item1.setText(2, "TOTAL");
			item1.setText(3, String.valueOf(tot3));
			item1.setText(4, getRoundedValue(tot4));
			item1.setText(5, String.valueOf(tot5));
			item1.setText(6, getRoundedValue(tot6));
		}

		fillZerosOnEmpty(tblUndeliveredTopay);
		
	}

	private void populateUTDetailed(BookingDTO[] utDTO) {
		int tot5 = 0;
		float tot6 = 0;
		float tot7 = 0;
		float tot8 = 0;
		String Branchcode = cbUTStation.getText();
		if(Branchcode.equalsIgnoreCase("All")){
			Branchcode = "All";
		}
		else{
			int index1 = Branchcode.indexOf(" - ");
			Branchcode = Branchcode.substring(index1 + 3);
		}
		String stationcode = cbUTStation.getText();
		if(stationcode.equalsIgnoreCase("All")){
			stationcode = "All";
		}
		else{
			int index = stationcode.indexOf(" - ");
			stationcode = stationcode.substring(index + 3);
		}	
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		int j = 0;
		for (int i = 0;  i < utDTO.length; i++) {
			
			if(( !(Branchcode.equalsIgnoreCase("All"))) && ( !(stationcode.equalsIgnoreCase("All")))){
				
				if(stationcode.equalsIgnoreCase(utDTO[i].getArticle_description())){
				
					TableItem item = new TableItem(tblUndeliveredTopayDet, SWT.NONE);
					j++;
					item.setText(0, String.valueOf(j));
					item.setText(1, utDTO[i].getLrno());
					item.setText(2, dateFormat.format(utDTO[i].getDate()));
					item.setText(3, utDTO[i].getFrom());
					item.setText(4, utDTO[i].getTo());
					tot5 = tot5 + utDTO[i].getNo_of_articles();
					item.setText(5, String.valueOf(utDTO[i].getNo_of_articles()));
					tot6 = tot6 + utDTO[i].getActual_weight();
					item.setText(6, getRoundedValue(utDTO[i].getActual_weight()));
					tot7 = tot7 + utDTO[i].getBft();
					item.setText(7, getRoundedValue(utDTO[i].getBft()));
					tot8 = tot8 + utDTO[i].getTotal();
					item.setText(8, getRoundedValue(utDTO[i].getTotal()));
					item.setText(9, utDTO[i].getType());
					
				}
			}
			else{
				TableItem item = new TableItem(tblUndeliveredTopayDet, SWT.NONE);
				
				item.setText(0, String.valueOf(i + 1));
				item.setText(1, utDTO[i].getLrno());
				item.setText(2, dateFormat.format(utDTO[i].getDate()));
				item.setText(3, utDTO[i].getFrom());
				item.setText(4, utDTO[i].getTo());
				tot5 = tot5 + utDTO[i].getNo_of_articles();
				item.setText(5, String.valueOf(utDTO[i].getNo_of_articles()));
				tot6 = tot6 + utDTO[i].getActual_weight();
				item.setText(6, getRoundedValue(utDTO[i].getActual_weight()));
				tot7 = tot7 + utDTO[i].getBft();
				item.setText(7, getRoundedValue(utDTO[i].getBft()));
				tot8 = tot8 + utDTO[i].getTotal();
				item.setText(8, getRoundedValue(utDTO[i].getTotal()));
				item.setText(9, utDTO[i].getType());
			}
			
				

		}

		final TableItem item1 = new TableItem(tblUndeliveredTopayDet, SWT.NONE);
		Font font1 = new Font(Display.getCurrent(), "Tahoma", 8, SWT.BOLD);
		item1.setFont(font1);
		item1.setText(4, "TOTAL");
		item1.setText(5, String.valueOf(tot5));
		item1.setText(6, getRoundedValue(tot6));
		item1.setText(7, getRoundedValue(tot7));
		item1.setText(8, getRoundedValue(tot8));
		
		fillZerosOnEmpty(tblUndeliveredTopayDet);
	}

	private String getBranch_code(String station) throws Exception {
		StationsDTO[] stations = null;

		stations = handler.getAllStations();
		int size = 0;
		if (null != stations) {
			size = stations.length;

			for (int i = 0; i < size; i++) {
				if (station.equalsIgnoreCase(stations[i].getId())) {
					return stations[i].getBranch_code();
				}
			}
		}

		return null;
	}

	/**
	 * 
	 * @param fromDt
	 * @param bookedLr
	 * @throws Exception
	 */
	private void populateDailyBooking(Date fromDt, GeneralSummaryDTO[] bookedLr)
			throws Exception {

		TableItem[] items = tblDailyBookingAnalysis.getItems();
		int dayCount = 0;
		long diff = 0;

		String stnCode = "";
		int count[][] = new int[items.length][7];
		float bft[][] = new float[items.length][7];
		float totalBft = 0;
		int noa = 0;
		float actWt = 0;
		float chrgWt = 0;
		
		for (int i = 0; i < bookedLr.length; i++) {
			
			for (int j = 0; j < items.length; j++) {
				
				if (cbDBAReportType.getText().equalsIgnoreCase("Consolidated")) {
					stnCode = getBranch_code(bookedLr[i].getStation_code());
					
					
				} else {
					stnCode = bookedLr[i].getStation_code();
				}
				
								
					if (stnCode.equalsIgnoreCase(items[j].getText(2))) {
						
						diff = bookedLr[i].getLrDate().getTime() - fromDt.getTime();
						dayCount = (int) (diff / (1000 * 60 * 60 * 24));
						
								
							if (cbDBAMOC.getText().equalsIgnoreCase(COUNT)) {
								
								count[j][dayCount] = count[j][dayCount] +  bookedLr[i].getLr_no();
							} else if (cbDBAMOC.getText().equalsIgnoreCase(
									BASIC_FREIGHT)) {
								bft[j][dayCount] =  bft[j][dayCount] + bookedLr[i].getBft();
							} else if (cbDBAMOC.getText().equalsIgnoreCase(
									TOTAL_FREIGHT)) {
								bft[j][dayCount] = bft[j][dayCount] + bookedLr[i].getTotal();
							} else if (cbDBAMOC.getText().equalsIgnoreCase(NOA)) {
								count[j][dayCount] = count[j][dayCount] + bookedLr[j].getNo_of_articles();
							} else if (cbDBAMOC.getText().equalsIgnoreCase(
									ACTUAL_WEIGHT)) {
								bft[j][dayCount] =  bft[j][dayCount] + bookedLr[i].getActual_weight();
							} else if (cbDBAMOC.getText().equalsIgnoreCase(
									CHARGED_WEIGHT)) {
								bft[j][dayCount] =  bft[j][dayCount] + bookedLr[i].getCharged_weight();
							}
							
							if (cbDBAMOC.getText().equalsIgnoreCase(COUNT)
									|| cbDBAMOC.getText().equalsIgnoreCase(NOA)) {
								fillBPA(count[j][dayCount], items[j], dayCount);
							} else {
								fillBPA(bft[j][dayCount], items[j], dayCount);
							}
							
						
						
						
					}
				
			}
		}

		if (cbDBAMOC.getText().equalsIgnoreCase(COUNT)
				|| cbDBAMOC.getText().equalsIgnoreCase(NOA)) {
			calcDBATotal();
		} else {
			calcDBATotal(true);
		}
		
		fillZerosOnEmpty(tblDailyBookingAnalysis);

	}

	private void fillBPA(int count, TableItem item, int dayCount) {
		int total = 0;
		int total1 = 0;
		int total2 = 0;
		int total3 = 0;
		int total4 = 0;
		int total5 = 0;
		int total6 = 0;
		int itemTotal = 0;
		
		if (dayCount == 0) {
			item.setText(3, String.valueOf(count));
			total = total + count;
			
		} else if (dayCount == 1) {
			item.setText(4, String.valueOf(count));
			total = total + count;
			
		} else if (dayCount == 2) {
			item.setText(5, String.valueOf(count));
			total = total + count;
			
		} else if (dayCount == 3) {
			item.setText(6, String.valueOf(count));
			total = total + count;
			
		} else if (dayCount == 4) {
			item.setText(7, String.valueOf(count));
			total = total + count;
			
		} else if (dayCount == 5) {
			item.setText(8, String.valueOf(count));
			total = total + count;
			
		} else if (dayCount == 6) {
			item.setText(9, String.valueOf(count));
			total = total + count;
			
		}
		//System.out.println("Total"+total);
		if ((! item.getText(10).equals(""))||(! item.getText(10).equals("0"))) {
			
			if (!item.getText(3).equals("")){
				itemTotal = itemTotal + Integer.parseInt(item.getText(3));
			}
			if (!item.getText(4).equals("")){
				itemTotal = itemTotal + Integer.parseInt(item.getText(4));
			}
			if (!item.getText(5).equals("")){
				itemTotal = itemTotal + Integer.parseInt(item.getText(5));
			}
			if (!item.getText(6).equals("")){
				itemTotal = itemTotal + Integer.parseInt(item.getText(6));
			}
			if (!item.getText(7).equals("")){
				itemTotal = itemTotal + Integer.parseInt(item.getText(7));
			}
			if (!item.getText(8).equals("")){
				itemTotal = itemTotal + Integer.parseInt(item.getText(8));
			}
			if (!item.getText(9).equals("")){
				itemTotal = itemTotal + Integer.parseInt(item.getText(9));
			}
			
		}
		//total = count + itemTotal;
		item.setText(10, String.valueOf(itemTotal));
	
		

	}

	private void fillBPA(float floatValue, TableItem item, int dayCount) {
		float total = 0;
		float itemTot = 0;

		if (dayCount == 0) {
			item.setText(3, getRoundedValue(floatValue));
			total = total + floatValue;
		} else if (dayCount == 1) {
			item.setText(4, getRoundedValue(floatValue));
			total = total + floatValue;
		} else if (dayCount == 2) {
			item.setText(5, getRoundedValue(floatValue));
			total = total + floatValue;
		} else if (dayCount == 3) {
			item.setText(6, getRoundedValue(floatValue));
			total = total + floatValue;
		} else if (dayCount == 4) {
			item.setText(7, getRoundedValue(floatValue));
			total = total + floatValue;
		} else if (dayCount == 5) {
			item.setText(8, getRoundedValue(floatValue));
			total = total + floatValue;
			;
		} else if (dayCount == 6) {
			item.setText(9, getRoundedValue(floatValue));
			total = total + floatValue;
		}

		if (item.getText(10).equals("")) {
			itemTot = 0;
		} else {
			//itemTot = Float.parseFloat(item.getText(10));
			
			if (!item.getText(3).equals(""))
				itemTot = itemTot + Float.parseFloat(item.getText(3));
			if (!item.getText(4).equals(""))
				itemTot = itemTot + Float.parseFloat(item.getText(4));
			if (!item.getText(5).equals(""))
				itemTot = itemTot + Float.parseFloat(item.getText(5));
			if (!item.getText(6).equals(""))
				itemTot = itemTot + Float.parseFloat(item.getText(6));
			if (!item.getText(7).equals(""))
				itemTot = itemTot + Float.parseFloat(item.getText(7));
			if (!item.getText(8).equals(""))
				itemTot = itemTot + Float.parseFloat(item.getText(8));
			if (!item.getText(9).equals(""))
				itemTot = itemTot + Float.parseFloat(item.getText(9));
			
		}
		//total = floatValue + itemTot;
		item.setText(10, getRoundedValue(itemTot));
		

	}

	private void calcDBATotal() {
		TableItem[] items = tblDailyBookingAnalysis.getItems();
		int tot3 = 0;
		int tot4 = 0;
		int tot5 = 0;
		int tot6 = 0;
		int tot7 = 0;
		int tot8 = 0;
		int tot9 = 0;
		int tot10 = 0;
		

		if (items != null) {
			for (int i = 0; i < items.length; i++) {
				if (!items[i].getText(3).equals("")){
					tot3 = tot3 + Integer.parseInt(items[i].getText(3));
					
				}
				if (!items[i].getText(4).equals("")){
					tot4 = tot4 + Integer.parseInt(items[i].getText(4));
					
				}
				if (!items[i].getText(5).equals("")){
					tot5 = tot5 + Integer.parseInt(items[i].getText(5));
					
				}
				if (!items[i].getText(6).equals("")){
					tot6 = tot6 + Integer.parseInt(items[i].getText(6));
					
				}
				if (!items[i].getText(7).equals("")){
					tot7 = tot7 + Integer.parseInt(items[i].getText(7));
					
				}
				if (!items[i].getText(8).equals("")){
					tot8 = tot8 + Integer.parseInt(items[i].getText(8));
					
				}
				if (!items[i].getText(9).equals("")){
					tot9 = tot9 + Integer.parseInt(items[i].getText(9));
					
				}
				if (!items[i].getText(10).equals(""))
					tot10 = tot10 + Integer.parseInt(items[i].getText(10));
			}

		}
			
		final TableItem item1 = new TableItem(tblDailyBookingAnalysis, SWT.NONE);
		Font font1 = new Font(Display.getCurrent(), "Tahoma", 8, SWT.BOLD);
		item1.setFont(font1);
		item1.setText(2, "TOTAL");
		item1.setText(3, String.valueOf(tot3));
		item1.setText(4, String.valueOf(tot4));
		item1.setText(5, String.valueOf(tot5));
		item1.setText(6, String.valueOf(tot6));
		item1.setText(7, String.valueOf(tot7));
		item1.setText(8, String.valueOf(tot8));
		item1.setText(9, String.valueOf(tot9));
		item1.setText(10, String.valueOf(tot10));
		}
	

	private void calcDBATotal(boolean isFloat) {
		TableItem[] items = tblDailyBookingAnalysis.getItems();
		float tot3 = 0;
		float tot4 = 0;
		float tot5 = 0;
		float tot6 = 0;
		float tot7 = 0;
		float tot8 = 0;
		float tot9 = 0;
		float tot10 = 0;

		if (items != null) {
			for (int i = 0; i < items.length; i++) {
				if (!items[i].getText(3).equals(""))
					tot3 = tot3 + Float.parseFloat(items[i].getText(3));
				if (!items[i].getText(4).equals(""))
					tot4 = tot4 + Float.parseFloat(items[i].getText(4));
				if (!items[i].getText(5).equals(""))
					tot5 = tot5 + Float.parseFloat(items[i].getText(5));
				if (!items[i].getText(6).equals(""))
					tot6 = tot6 + Float.parseFloat(items[i].getText(6));
				if (!items[i].getText(7).equals(""))
					tot7 = tot7 + Float.parseFloat(items[i].getText(7));
				if (!items[i].getText(8).equals(""))
					tot8 = tot8 + Float.parseFloat(items[i].getText(8));
				if (!items[i].getText(9).equals(""))
					tot9 = tot9 + Float.parseFloat(items[i].getText(9));
				if (!items[i].getText(10).equals(""))
					tot10 = tot10 + Float.parseFloat(items[i].getText(10));
			}

		}

		final TableItem item1 = new TableItem(tblDailyBookingAnalysis, SWT.NONE);
		Font font1 = new Font(Display.getCurrent(), "Tahoma", 8, SWT.BOLD);
		item1.setFont(font1);
		item1.setText(2, "TOTAL");
		item1.setText(3, getRoundedValue(tot3));
		item1.setText(4, getRoundedValue(tot4));
		item1.setText(5, getRoundedValue(tot5));
		item1.setText(6, getRoundedValue(tot6));
		item1.setText(7, getRoundedValue(tot7));
		item1.setText(8, getRoundedValue(tot8));
		item1.setText(9, getRoundedValue(tot9));
		item1.setText(10, getRoundedValue(tot10));

	}

	/**
	 * 
	 * @param from_date
	 * @param to_date
	 * @param branch_code
	 */
	private void getCounterReport(Date from_date, Date to_date,
			String branch_code) {
		try {
			if (null != handler) {

				StationsDTO[] station = handler.getAllStations();
				int len = station.length;

				StationsDTO[] newStation = new StationsDTO[len];
				for (int i = 0; i < len; i++) {
					newStation[i] = new StationsDTO();
					newStation[i].setId(station[i].getId());
					newStation[i].setBranch_code(station[i].getBranch_code());

				}
				StationsDTO[] dto = getCounterReport(from_date,
						to_date, branch_code, newStation);

				if (tblCounterReport != null)
					tblCounterReport.removeAll();

				if (dto != null) {
					populateCounterReport(dto, branch_code);
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

	}
	
	private void getCounterReportMisc(Date from_date, Date to_date,
			String branch_code) {
		// TODO Auto-generated method stub
		try {
			if (null != handler) {

				StationsDTO[] station = handler.getAllStations();
				int len = station.length;

				StationsDTO[] newStation = new StationsDTO[len];
				for (int i = 0; i < len; i++) {
					newStation[i] = new StationsDTO();
					newStation[i].setId(station[i].getId());
					newStation[i].setBranch_code(station[i].getBranch_code());

				}
				StationsDTO[] dto = getCounterReportMisc(from_date,
						to_date, branch_code, newStation);

				if (tblCounterReport != null)
					tblCounterReport.removeAll();

				if (dto != null) {
					populateCounterReportMisc(dto, branch_code);
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		
	}

	private StationsDTO[] getCounterReportMisc(Date from_date, Date to_date,
			String branch_code, StationsDTO[] newStation) {
		// TODO Auto-generated method stub
		
		//int monthDiff = 0;
		StationsDTO[] dto = null;
		try {
				/*Date curDate = new Date();
				int m1 = from_date.getYear() * 12 + from_date.getMonth();
		        int m2 = curDate.getYear() * 12 + curDate.getMonth();
		        monthDiff = m2 - m1;*/
				Date date = BeanUtil.getThree_month_updated();
			if(from_date.before(date)){
				
				dto = handler.getCounterReportMiscHistory(from_date, to_date, branch_code,newStation);
			}else{
				dto = handler.getCounterReportMisc(from_date, to_date, branch_code,newStation);
			}
		}
		catch (Exception e) {
				e.printStackTrace();
		}
		return dto;
	}

	private StationsDTO[] getCounterReport(Date from_date, Date to_date,
			String branch_code, StationsDTO[] newStation) {
	
		//int monthDiff = 0;
		StationsDTO[] dto = null;
		try {
			/*Date curDate = new Date();
			int m1 = from_date.getYear() * 12 + from_date.getMonth();
	        int m2 = curDate.getYear() * 12 + curDate.getMonth();
	        monthDiff = m2 - m1;*/
			Date date = BeanUtil.getThree_month_updated();
		if(from_date.before(date)){
			//System.out.println("in ddr histry-->"+date);
			dto = handler.getCounterReportHistory(from_date, to_date, branch_code,newStation);
		}else{
			dto = handler.getCounterReport(from_date, to_date, branch_code,newStation);
		}
	} catch (Exception e) {
			e.printStackTrace();
	}
		return dto;
	}

	private void populateCounterReport(StationsDTO[] dto, String branch)
			throws Exception {
		int len = dto.length;
		
		for (int i = 0, j = 1; i < len; i++) {
			if (cbCounterBranch.getText().equalsIgnoreCase("All")
					&& cbCRReportType.getText().startsWith("C")) {
				if (dto[i].getId().equalsIgnoreCase(dto[i].getBranch_code())) {
					TableItem item = new TableItem(tblCounterReport, SWT.NONE);

					item.setText(0, String.valueOf(j));
					item.setText(1, dto[i].getBranch_code());
					item.setText(2, dto[i].getId());
					item.setText(3, String.valueOf(dto[i].getCc_limit()
							+ dto[i].getCc_consider() + dto[i].getCc_refund()));
					item.setText(4, String.valueOf(dto[i].getCc_limit()));
					item.setText(5, String.valueOf(dto[i].getCc_consider()));
					item.setText(6, String.valueOf(dto[i].getCc_refund()));
					item.setText(7, String.valueOf(dto[i].getCc_special()));
					item.setText(8, String.valueOf(dto[i].getCc_commodity()));
					item.setText(9, String.valueOf(dto[i].getBf_increment()));
					item.setText(10, String.valueOf(dto[i].getDiscount()));

					j++;
				}
			} else if (!cbCounterBranch.getText().equalsIgnoreCase("All")) {

				if (branch.equalsIgnoreCase(dto[i].getBranch_code())) {
					TableItem item = new TableItem(tblCounterReport, SWT.NONE);
					item.setText(0, String.valueOf(j));
					item.setText(1, dto[i].getBranch_code());
					item.setText(2, dto[i].getId());
					item.setText(3, String.valueOf(dto[i].getCc_limit()
							+ dto[i].getCc_consider() + dto[i].getCc_refund()));
					item.setText(4, String.valueOf(dto[i].getCc_limit()));
					item.setText(5, String.valueOf(dto[i].getCc_consider()));
					item.setText(6, String.valueOf(dto[i].getCc_refund()));
					item.setText(7, String.valueOf(dto[i].getCc_special()));
					item.setText(8, String.valueOf(dto[i].getCc_commodity()));
					item.setText(9, String.valueOf(dto[i].getBf_increment()));
					item.setText(10, String.valueOf(dto[i].getDiscount()));
					j++;
				}
			} else {
				TableItem item = new TableItem(tblCounterReport, SWT.NONE);
				item.setText(0, String.valueOf(j));
				item.setText(1, dto[i].getBranch_code());
				item.setText(2, dto[i].getId());
				item.setText(3, String.valueOf(dto[i].getCc_limit()
						+ dto[i].getCc_consider() + dto[i].getCc_refund()));
				item.setText(4, String.valueOf(dto[i].getCc_limit()));
				item.setText(5, String.valueOf(dto[i].getCc_consider()));
				item.setText(6, String.valueOf(dto[i].getCc_refund()));
				item.setText(7, String.valueOf(dto[i].getCc_special()));
				item.setText(8, String.valueOf(dto[i].getCc_commodity()));
				item.setText(9, String.valueOf(dto[i].getBf_increment()));
				item.setText(10, String.valueOf(dto[i].getDiscount()));

				j++;
			}

		}

		calcCounterTotal();

	}

	private void calcCounterTotal() {
		TableItem[] items = tblCounterReport.getItems();
		int tot3 = 0;
		int tot4 = 0;
		int tot5 = 0;
		int tot6 = 0;
		int tot7 = 0;
		int tot8 = 0;
		int tot9 = 0;
		int tot10 = 0;

		if (items != null) {
			for (int i = 0; i < items.length; i++) {
				if (!items[i].getText(3).equals(""))
					tot3 = tot3 + Integer.parseInt(items[i].getText(3));
				if (!items[i].getText(4).equals(""))
					tot4 = tot4 + Integer.parseInt(items[i].getText(4));
				if (!items[i].getText(5).equals(""))
					tot5 = tot5 + Integer.parseInt(items[i].getText(5));
				if (!items[i].getText(6).equals(""))
					tot6 = tot6 + Integer.parseInt(items[i].getText(6));
				if (!items[i].getText(7).equals(""))
					tot7 = tot7 + Integer.parseInt(items[i].getText(7));
				if (!items[i].getText(8).equals(""))
					tot8 = tot8 + Integer.parseInt(items[i].getText(8));
				if (!items[i].getText(9).equals(""))
					tot9 = tot9 + Integer.parseInt(items[i].getText(9));
				if (!items[i].getText(10).equals(""))
					tot10 = tot10 + Integer.parseInt(items[i].getText(10));
			}

		}

		final TableItem item1 = new TableItem(tblCounterReport, SWT.NONE);
		Font font1 = new Font(Display.getCurrent(), "Tahoma", 8, SWT.BOLD);
		item1.setFont(font1);
		item1.setText(2, "TOTAL");
		item1.setText(3, String.valueOf(tot3));
		item1.setText(4, String.valueOf(tot4));
		item1.setText(5, String.valueOf(tot5));
		item1.setText(6, String.valueOf(tot6));
		item1.setText(7, String.valueOf(tot7));
		item1.setText(8, String.valueOf(tot8));
		item1.setText(9, String.valueOf(tot9));
		item1.setText(10, String.valueOf(tot10));
	}
	
	
	
	
	private void populateCounterReportMisc(StationsDTO[] dto, String branch)
	throws Exception {
		int len = dto.length;
		
		for (int i = 0, j = 1; i < len; i++) {
			if (cbCounterBranch.getText().equalsIgnoreCase("All")
					&& cbCRReportType.getText().startsWith("C")) {
				if (dto[i].getId().equalsIgnoreCase(dto[i].getBranch_code())) {
					TableItem item = new TableItem(tblCounterReport, SWT.NONE);
		
					item.setText(0, String.valueOf(j));
					item.setText(1, dto[i].getBranch_code());
					item.setText(2, dto[i].getId());
					item.setText(3, String.valueOf(dto[i].getBf_increment()));
					item.setText(4, String.valueOf(dto[i].getBpi()));
					item.setText(5, String.valueOf(dto[i].getCc_hlc()));
					item.setText(6, String.valueOf(dto[i].getCc_limit()));
					item.setText(7, String.valueOf(dto[i].getCc_refund()));
					item.setText(8, String.valueOf(dto[i].getCc_special()));
					item.setText(9, String.valueOf(dto[i].getDiscount()));
					item.setText(10, String.valueOf("0"));
		
					j++;
				}
			} else if (!cbCounterBranch.getText().equalsIgnoreCase("All")) {
		
				if (branch.equalsIgnoreCase(dto[i].getBranch_code())) {
					TableItem item = new TableItem(tblCounterReport, SWT.NONE);
					item.setText(0, String.valueOf(j));
					item.setText(1, dto[i].getBranch_code());
					item.setText(2, dto[i].getId());
					item.setText(3, String.valueOf(dto[i].getBf_increment()));
					item.setText(4, String.valueOf(dto[i].getBpi()));
					item.setText(5, String.valueOf(dto[i].getCc_hlc()));
					item.setText(6, String.valueOf(dto[i].getCc_limit()));
					item.setText(7, String.valueOf(dto[i].getCc_refund()));
					item.setText(8, String.valueOf(dto[i].getCc_special()));
					item.setText(9, String.valueOf(dto[i].getDiscount()));
					item.setText(10, String.valueOf("0"));
					j++;
				}
			} else {
				TableItem item = new TableItem(tblCounterReport, SWT.NONE);
				item.setText(0, String.valueOf(j));
				item.setText(1, dto[i].getBranch_code());
				item.setText(2, dto[i].getId());
				item.setText(3, String.valueOf(dto[i].getBf_increment()));
				item.setText(4, String.valueOf(dto[i].getBpi()));
				item.setText(5, String.valueOf(dto[i].getCc_hlc()));
				item.setText(6, String.valueOf(dto[i].getCc_limit()));
				item.setText(7, String.valueOf(dto[i].getCc_refund()));
				item.setText(8, String.valueOf(dto[i].getCc_special()));
				item.setText(9, String.valueOf(dto[i].getDiscount()));
				item.setText(10, String.valueOf("0"));
		
				j++;
			}
		
		}
		
		calcCounterMiscTotal();
		
	}

	

	private void calcCounterMiscTotal() {
		// TODO Auto-generated method stub
	TableItem[] items = tblCounterReport.getItems();
			int tot3 = 0;
			float tot4 = 0;
			float tot5 = 0;
			int tot6 = 0;
			int tot7 = 0;
			int tot8 = 0;
			int tot9 = 0;
			int tot10 = 0;

			if (items != null) {
				for (int i = 0; i < items.length; i++) {
					if (!items[i].getText(3).equals(""))
						tot3 = tot3 + Integer.parseInt(items[i].getText(3));
					if (!items[i].getText(4).equals(""))
						tot4 = tot4 + Float.parseFloat(items[i].getText(4));
					if (!items[i].getText(5).equals(""))
						tot5 = tot5 + Float.parseFloat(items[i].getText(5));
					if (!items[i].getText(6).equals(""))
						tot6 = tot6 + Integer.parseInt(items[i].getText(6));
					if (!items[i].getText(7).equals(""))
						tot7 = tot7 + Integer.parseInt(items[i].getText(7));
					if (!items[i].getText(8).equals(""))
						tot8 = tot8 + Integer.parseInt(items[i].getText(8));
					if (!items[i].getText(9).equals(""))
						tot9 = tot9 + Integer.parseInt(items[i].getText(9));
					if (!items[i].getText(10).equals(""))
						tot10 = tot10 + Integer.parseInt(items[i].getText(10));
				}

			}

			final TableItem item1 = new TableItem(tblCounterReport, SWT.NONE);
			Font font1 = new Font(Display.getCurrent(), "Tahoma", 8, SWT.BOLD);
			item1.setFont(font1);
			item1.setText(2, "TOTAL");
			item1.setText(3, String.valueOf(tot3));
			item1.setText(4, String.valueOf(tot4));
			item1.setText(5, String.valueOf(tot5));
			item1.setText(6, String.valueOf(tot6));
			item1.setText(7, String.valueOf(tot7));
			item1.setText(8, String.valueOf(tot8));
			item1.setText(9, String.valueOf(tot9));
			item1.setText(10, String.valueOf(tot10));
		

		
	}

	private void sundryBranchComboAction(String branch, Combo cbStation) {

		StationsDTO[] stations = null;
		try {
			stations = handler.getAllStations();
			int size = 0;
			if (null != stations) {
				size = stations.length;
				cbStation.removeAll();
				cbStation.add("All");
				if (!branch.equalsIgnoreCase("All")) {
					for (int i = 0; i < size; i++) {
						if (branch.equals(stations[i].getBranch_code())) {
							cbStation.add(stations[i].getId());
						}
					}
				} else {
					for (int i = 0; i < size; i++) {
						cbStation.add(stations[i].getId());
					}
				}

			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}

	private void populateSundryBooking(GeneralSummaryDTO[] sundryBooking,
			boolean isAdmin) throws Exception {
		TableItem[] items = tblSundryBookAnalysis.getItems();
		String dtoBranchCode = null;
		String tableBranchCode = null;
		Boolean isBranch = false;
		//System.out.println("sundry-->"+sundryBooking.length);
		//System.out.println("items-->"+items.length);
		for (int i = 0; i < sundryBooking.length; i++) {
			for (int j = 0; j < items.length; j++) {
			int availLrNo = 0;
			float availTotal = 0;
			int lrNo = 0;
			float total = 0;
			float bft = 0;
			int noa = 0;
			float actWt = 0;
			float cw = 0;
			float  sundryoftotal = 0;
				if(!isAdmin){
					dtoBranchCode = sundryBooking[i].getStation_code();
					
				}else{
					dtoBranchCode = getBranch_code(sundryBooking[i]
						.getStation_code());
					isBranch = true;
				}
				tableBranchCode = items[j].getText(2);
				
				if (dtoBranchCode.equalsIgnoreCase(tableBranchCode)) {
					
						if (!items[j].getText(3).equals("")) {
							availLrNo = Integer.parseInt(items[j].getText(3));
						}
	
						if (!items[j].getText(5).equals("")) {
							availTotal = Float.parseFloat(items[j].getText(5));
						}
	
						if (!items[j].getText(4).equals("")) {
							lrNo = Integer.parseInt(items[j].getText(4));
						}
	
						if (!items[j].getText(6).equals("")) {
							total = Float.parseFloat(items[j].getText(6));
						}
	
						if (!items[j].getText(7).equals("")) {
							bft = Float.parseFloat(items[j].getText(7));
						}
	
						if (!items[j].getText(8).equals("")) {
							noa = Integer.parseInt(items[j].getText(8));
						}
	
						if (!items[j].getText(9).equals("")) {
							actWt = Float.parseFloat(items[j].getText(9));
						}
	
						if (!items[j].getText(10).equals("")) {
							cw = Float.parseFloat(items[j].getText(10));
						}
					}

					if (sundryBooking[i].getSundry_type().equalsIgnoreCase(
							"Sundry")) {
						items[j].setText(3, String.valueOf(sundryBooking[i]
								.getLr_no()
								+ availLrNo));
						items[j].setText(5, getRoundedValue(sundryBooking[i]
								.getTotal()
								+ availTotal));
						items[j].setText(4, String.valueOf(sundryBooking[i]
								.getLr_no()
								+ lrNo));
						items[j].setText(6, getRoundedValue(sundryBooking[i]
								.getTotal()
								+ total));
						items[j].setText(7, getRoundedValue(sundryBooking[i]
								.getBft()
								+ bft));
						items[j].setText(8, String.valueOf(sundryBooking[i]
								.getNo_of_articles()
								+ noa));
						items[j].setText(9, getRoundedValue(sundryBooking[i]
								.getActual_weight()
								+ actWt));
						items[j].setText(10, getRoundedValue(sundryBooking[i]
								.getCharged_weight()
								+ cw));
					} else {
						items[j].setText(3, String.valueOf(sundryBooking[i]
								.getLr_no()
								+ availLrNo));
						items[j].setText(5, getRoundedValue(sundryBooking[i]
								.getTotal()
								+ availTotal));
					}
					
					if((!items[j].getText(5).equalsIgnoreCase(""))&& (!items[j].getText(6).equalsIgnoreCase("")))
						{
							sundryoftotal = (((Float.parseFloat(items[j].getText(6))) / (Float.parseFloat(items[j].getText(5)))) * 100);
							items[j].setText(11, getRoundValue(sundryoftotal));
						}
					
					
				//}
			}
		}

		calcSBAtotal();
		fillZerosOnEmpty(tblSundryBookAnalysis);
	}

	private void calcSBAtotal() {
		TableItem[] items = tblSundryBookAnalysis.getItems();
		int tot3 = 0;
		int tot4 = 0;
		double tot5 = 0;
		double tot6 = 0;
		double tot7 = 0;
		int tot8 = 0;
		double tot9 = 0;
		double tot10 = 0;
		double tot11=0;
		double average=0;
		
		int count=0;
		

		if (items != null) {
			for (int i = 0; i < items.length; i++) {
				if (!items[i].getText(3).equals(""))
					tot3 = tot3 + Integer.parseInt(items[i].getText(3));
				if (!items[i].getText(4).equals(""))
					tot4 = tot4 + Integer.parseInt(items[i].getText(4));
				if (!items[i].getText(5).equals(""))
					tot5 = tot5 + Double.parseDouble(items[i].getText(5));
				if (!items[i].getText(6).equals(""))
					tot6 = tot6 + Double.parseDouble(items[i].getText(6));
				if (!items[i].getText(7).equals(""))
					tot7 = tot7 + Double.parseDouble(items[i].getText(7));
				if (!items[i].getText(8).equals(""))
					tot8 = tot8 + Integer.parseInt(items[i].getText(8));
				if (!items[i].getText(9).equals(""))
					tot9 = tot9 + Double.parseDouble(items[i].getText(9));
				if (!items[i].getText(10).equals(""))
					tot10 = tot10 + Double.parseDouble(items[i].getText(10));
				if (!items[i].getText(11).equals(""))
					tot11 = tot11 + Double.parseDouble(items[i].getText(11));
				count++;
			}

		}
		
		average =  tot11 / count; 
		final TableItem item1 = new TableItem(tblSundryBookAnalysis, SWT.NONE);
		Font font1 = new Font(Display.getCurrent(), "Tahoma", 8, SWT.BOLD);
		item1.setFont(font1);
		item1.setText(1, "");
		item1.setText(2, "TOTAL");
		item1.setText(3, String.valueOf(tot3));
		item1.setText(4, String.valueOf(tot4));
		item1.setText(5, getRoundValue(tot5));
		item1.setText(6, getRoundValue(tot6));
		item1.setText(7, getRoundValue(tot7));
		item1.setText(8, String.valueOf(tot8));
		item1.setText(9, getRoundValue(tot9));
		item1.setText(10, getRoundValue(tot10));
		item1.setText(11, getRoundValue(tot11));
		final TableItem item2 = new TableItem(tblSundryBookAnalysis, SWT.NONE);
		item2.setFont(font1);
		item2.setText(10, "Average");
		item2.setText(11, getRoundValue(average));
	}

	private boolean isBranchStation(String from) throws Exception {

		StationsDTO[] stations = null;

		stations = handler.getAllStations();

		if (null != stations) {
			for (int i = 0; i < stations.length; i++) {
				if (from.equalsIgnoreCase(stations[i].getId())) {
					if (from.equalsIgnoreCase(stations[i].getBranch_code())) {
						return true;
					}
				}
			}
		}

		return false;
	}

	private void populateSundryBooking(GeneralSummaryDTO[] sundryBooking) {
		TableItem[] items = tblSundryBookAnalysis.getItems();
		 float sundryoftotal = 0;
		//System.out.println("sundry-->"+sundryBooking.length);
		//System.out.println("items-->"+items.length);
		for (int i = 0; i < sundryBooking.length; i++) {
			
			for (int j = 0; j < items.length; j++) {
				if (sundryBooking[i].getStation_code().equalsIgnoreCase(
						items[j].getText(2))) {
					int availLrNo = 0;
					float availTotal = 0;
					if (!items[j].getText(3).equals("")) {
						availLrNo = Integer.parseInt(items[j].getText(3));
					}

					if (!items[j].getText(5).equals("")) {
						availTotal = Float.parseFloat(items[j].getText(5));
					}

					if (sundryBooking[i].getSundry_type().equalsIgnoreCase(
							"Sundry")) {
						items[j].setText(3, String.valueOf(sundryBooking[i]
								.getLr_no()
								+ availLrNo));
						items[j].setText(5, getRoundedValue(sundryBooking[i]
								.getTotal()
								+ availTotal));
						items[j].setText(4, String.valueOf(sundryBooking[i]
								.getLr_no()));
						items[j].setText(6, getRoundedValue(sundryBooking[i]
								.getTotal()));
						items[j].setText(7, getRoundedValue(sundryBooking[i]
								.getBft()));
						items[j].setText(8, String.valueOf(sundryBooking[i]
								.getNo_of_articles()));
						items[j].setText(9, getRoundedValue(sundryBooking[i]
								.getActual_weight()));
						items[j].setText(10, getRoundedValue(sundryBooking[i]
								.getCharged_weight()));
					} else {
						items[j].setText(3, String.valueOf(sundryBooking[i]
								.getLr_no()
								+ availLrNo));
						items[j].setText(5, getRoundedValue(sundryBooking[i]
								.getTotal()
								+ availTotal));
					}
					
					if((!items[j].getText(5).equalsIgnoreCase(""))&& (!items[j].getText(6).equalsIgnoreCase("")))
					{
						sundryoftotal = (((Float.parseFloat(items[j].getText(6))) / (Float.parseFloat(items[j].getText(5)))) * 100);
						items[j].setText(11, getRoundValue(sundryoftotal));
					}
				
				}
			}
		}

		calcSBAtotal();
		fillZerosOnEmpty(tblSundryBookAnalysis);

	}
	private void populateSundryBooking(GeneralSummaryDTO[] sundryBooking, int isGroup) {
		TableItem[] items = tblSundryBookAnalysis.getItems();
		float sundryoftotal = 0;
		String branchCode = "";
		for (int i = 0; i < sundryBooking.length; i++) {
			
			try {
				for (int j = 0; j < items.length; j++) {
					branchCode = getBranch_code(sundryBooking[i].getStation_code());
					//System.out.println("group brabch-->"+branchCode);
					if (branchCode.equalsIgnoreCase(
							items[j].getText(2))) {
						int availLrNo = 0;
						float availTotal = 0;
						if (!items[j].getText(3).equals("")) {
							availLrNo = Integer.parseInt(items[j].getText(3));
						}

						if (!items[j].getText(5).equals("")) {
							availTotal = Float.parseFloat(items[j].getText(5));
						}

						if (sundryBooking[i].getSundry_type().equalsIgnoreCase(
								"Sundry")) {
							items[j].setText(3, String.valueOf(sundryBooking[i]
									.getLr_no()
									+ availLrNo));
							items[j].setText(5, getRoundedValue(sundryBooking[i]
									.getTotal()
									+ availTotal));
							items[j].setText(4, String.valueOf(sundryBooking[i]
									.getLr_no()));
							items[j].setText(6, getRoundedValue(sundryBooking[i]
									.getTotal()));
							items[j].setText(7, getRoundedValue(sundryBooking[i]
									.getBft()));
							items[j].setText(8, String.valueOf(sundryBooking[i]
									.getNo_of_articles()));
							items[j].setText(9, getRoundedValue(sundryBooking[i]
									.getActual_weight()));
							items[j].setText(10, getRoundedValue(sundryBooking[i]
									.getCharged_weight()));
						} else {
							items[j].setText(3, String.valueOf(sundryBooking[i]
									.getLr_no()
									+ availLrNo));
							items[j].setText(5, getRoundedValue(sundryBooking[i]
									.getTotal()
									+ availTotal));
						}
						
						if((!items[j].getText(5).equalsIgnoreCase(""))&& (!items[j].getText(6).equalsIgnoreCase("")))
						{
							sundryoftotal = (((Float.parseFloat(items[j].getText(6))) / (Float.parseFloat(items[j].getText(5)))) * 100);
							items[j].setText(11, String.valueOf(sundryoftotal));
						}
					}
				}
			} catch (NumberFormatException e) {				
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		calcSBAtotal();
		fillZerosOnEmpty(tblSundryBookAnalysis);

	}

	private void designSundryTable() throws Exception {
		if (tblSundryBookAnalysis != null) {
			tblSundryBookAnalysis.removeAll();
		}
		if (cbSBABranch.getText().equalsIgnoreCase("All")
				&& cbSBAStation.getSelectionIndex() == -1) {
			populateAllBranches(tblSundryBookAnalysis);
		} else if (cbSBABranch.getText().equalsIgnoreCase("All")
				&& cbSBAStation.getText().equalsIgnoreCase("Group")) {
			populateAllBranches(tblSundryBookAnalysis);
		} else if (cbSBABranch.getText().equalsIgnoreCase("All")
				&& cbSBAStation.getText().equalsIgnoreCase("All")) {
			populateAllStations(tblSundryBookAnalysis);
		}else if (!cbSBABranch.getText().equalsIgnoreCase("All")
				&& cbSBAStation.getText().equalsIgnoreCase("All")) {
			String branchCode = cbSBABranch.getText();
			int index = branchCode.indexOf(" - ");
			branchCode = branchCode.substring(index + 3);
			populateSelectedStations(branchCode, tblSundryBookAnalysis);
		} else if (!cbSBABranch.getText().equalsIgnoreCase("All")
				&& !cbSBAStation.getText().equalsIgnoreCase("All")) {
			 //populateSingleStation(cbSBAStation.getText(),tblSundryBookAnalysis);

			TableItem item = new TableItem(tblSundryBookAnalysis, SWT.NONE);
			item.setText(0, String.valueOf(1));
			item.setText(1, getBranch_code(cbSBAStation.getText()));
			item.setText(2, cbSBAStation.getText());
		}

	}

	private void populateUPDReady(BookingDTO[] updReady, boolean isBranch)
			throws Exception {

		for (int i = 0, j = 0; i < updReady.length; i++) {

			if (isBranch) {
				if (isBranchStation(updReady[i].getFrom())) {
					fillUPD(updReady[i], j);
					j++;
				}
			} else {
				fillUPD(updReady[i], j);
				j++;
			}

		}

		calcUPDReadyTotal();

	}

	private void calcUPDReadyTotal() {
		TableItem[] items = tblUPDReady.getItems();
		float totAmt = 0;
		if (items != null) {
			for (int i = 0; i < items.length; i++) {
				totAmt = totAmt + Float.parseFloat(items[i].getText(6));
			}

		}

		final TableItem item1 = new TableItem(tblUPDReady, SWT.NONE);
		Font font1 = new Font(Display.getCurrent(), "Tahoma", 8, SWT.BOLD);
		item1.setFont(font1);
		item1.setText(5, "TOTAL");
		item1.setText(6, getRoundedValue(totAmt));

	}

	private void fillUPD(BookingDTO updReady, int j) throws Exception {

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		long diff = 0;
		int dayCount = 0;
		Date today = new Date();
		String fromNo = null;
		String toNo = null;

		TableItem item = new TableItem(tblUPDReady, SWT.NONE);
		item.setText(0, String.valueOf(j + 1));
		
		item.setText(1, getBranch_code(updReady.getFrom()));
		item.setText(2, updReady.getFrom());
		item.setText(3, updReady.getLrno());
		item.setText(4, dateFormat.format(updReady.getDate()));
		item.setText(5, updReady.getType());

		item.setText(6, getRoundedValue(updReady.getTotal()));
		diff = today.getTime() - updReady.getDeliveredDate().getTime();
		dayCount = (int) (diff / (1000 * 60 * 60 * 24));

		item.setText(7, String.valueOf(dayCount));

		item.setText(8, updReady.getConsignorName());
		fromNo = updReady.getFromMobile();
		if (fromNo != null) {
			item.setText(9, fromNo);
		} else {
			item.setText(9, "NIL");
		}

		item.setText(10, updReady.getConsigneeName());
		toNo = updReady.getToMobile();
		if (toNo != null) {
			item.setText(11, toNo);
		} else {
			item.setText(11, "NIL");
		}

	}

	private String getPhoneNo(String station) throws Exception {

		StationsDTO[] stations = null;

		stations = handler.getAllStations();

		if (null != stations) {
			for (int i = 0; i < stations.length; i++) {
				if (station.equalsIgnoreCase(stations[i].getId())) {
					if (stations[i].getMobile() != null) {
						return stations[i].getMobile();
					} else {
						return stations[i].getPhone1();
					}
				}
			}
		}

		return null;
	}

	private BookingDTO[] designAndGetUPDTable(boolean isMoreThan)
			throws Exception {
		if (tblUPDReady != null) {
			tblUPDReady.removeAll();
		}

		BookingDTO[] dto = null;

		String station = cbUPDRStation.getText();
		int inwardDays = Integer.parseInt(cbUPDRInwardDays.getText());

		if (cbUPDRBranch.getText().equalsIgnoreCase("All")
				&& cbUPDRStation.getSelectionIndex() == -1) {
			
			// populateAllBranches(tblUPDReady);
			try{
				Date curDate = new Date();
				Date date = BeanUtil.getThree_month_updated();
				if(curDate.before(date)){
						//System.out.println("in ddr histry-->"+date);
					dto = handler.getUPDReadyHistory("%", "%", inwardDays, isMoreThan);
				}else{
					dto = handler.getUPDReady("%", "%", inwardDays, isMoreThan);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (cbUPDRBranch.getText().equalsIgnoreCase("All")
				&& cbUPDRStation.getText().equalsIgnoreCase("All")) {
			// populateAllStations(tblUPDReady);
			try{
				Date curDate = new Date();
				Date date = BeanUtil.getThree_month_updated();
				if(curDate.before(date)){
						//System.out.println("in ddr histry-->"+date);
					dto = handler.getUPDReadyHistory("%", "%", inwardDays, isMoreThan);
				}else{
					dto = handler.getUPDReady("%", "%", inwardDays, isMoreThan);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (!cbUPDRBranch.getText().equalsIgnoreCase("All")
				&& cbUPDRStation.getText().equalsIgnoreCase("All")) {
			// populateSelectedStations(cbUPDRBranch.getText(),tblUPDReady);
			String branchCode = cbUPDRBranch.getText();
			int index = branchCode.indexOf(" - ");
			branchCode = branchCode.substring(index + 3);
			try{
				Date curDate = new Date();
				Date date = BeanUtil.getThree_month_updated();
				if(curDate.before(date)){
						//System.out.println("in ddr histry-->"+date);
					dto = handler.getUPDReadyHistory("%", branchCode, inwardDays, isMoreThan);
				}else{
					dto = handler.getUPDReady("%", branchCode, inwardDays, isMoreThan);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (cbUPDRBranch.getText().equalsIgnoreCase("All")
				&& !cbUPDRStation.getText().equalsIgnoreCase("All")) {

			/*
			 * TableItem item = new TableItem(tblUPDReady,SWT.NONE);
			 * item.setText(0, String.valueOf(1)); item.setText(1,
			 * getBranch_code(cbUPDRStation.getText())); item.setText(2,
			 * cbUPDRStation.getText());
			 */
			// System.out.println("fsdfsfsf");
			try{
				Date curDate = new Date();
				Date date = BeanUtil.getThree_month_updated();
				if(curDate.before(date)){
						//System.out.println("in ddr histry-->"+date);
					dto = handler.getUPDReadyHistory(station, getBranch_code(station),
							inwardDays, isMoreThan);
				}else{
					dto = handler.getUPDReady(station, getBranch_code(station),
							inwardDays, isMoreThan);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (!cbUPDRBranch.getText().equalsIgnoreCase("All")
				&& !cbUPDRStation.getText().equalsIgnoreCase("All")) {
			String branchCode = cbUPDRBranch.getText();
			int index = branchCode.indexOf(" - ");
			branchCode = branchCode.substring(index + 3);
			try{
				Date curDate = new Date();
				Date date = BeanUtil.getThree_month_updated();
				if(curDate.before(date)){
						//System.out.println("in ddr histry-->"+date);
					dto = handler.getUPDReadyHistory(station, branchCode, inwardDays,
							isMoreThan);
				}else{
					dto = handler.getUPDReady(station, branchCode, inwardDays,
							isMoreThan);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

		return dto;

	}

	// integrated methods
	/**
	 * 
	 * @param dto
	 */
	private void populateInwardAnalysis(InwardAnalysisDTO[] dto) {
		int dtoLen = dto.length;
		int colLen = COLUMNS.length;
		final TableItem item1 = new TableItem(tblInwardAnalysis, SWT.NONE);
		Font font1 = new Font(Display.getDefault(), "Tahoma", 8, SWT.BOLD);
		item1.setFont(font1);
		item1.setText(0, "TOTAL");
		int colVal = 0;
		float colFloatVal = 0;
		TableItem[] items = tblInwardAnalysis.getItems();
		int itemsLen = items.length;

		int colTotIndex = tblInwardAnalysis.getColumnCount();

		// Col Total
		column = new TableColumn(tblInwardAnalysis, SWT.CENTER);
		column.setText("TOTAL");
		column.setWidth(60);
		column.setAlignment(SWT.RIGHT);

		DecimalFormat df = new DecimalFormat("0.00");

		for (int i = 0; i < dtoLen; i++) {

			for (int j = 0; j < itemsLen; j++) {
				if (dto[i].getInwardStation().equalsIgnoreCase(
						items[j].getText(0))) {

					for (int k = 0; k < colLen; k++) {
						if (dto[i].getFromStation()
								.equalsIgnoreCase(COLUMNS[k])) {

							if (cbOption.getText().equals(COUNT)) {

								
								items[j].setText(k + 1, String.valueOf(dto[i]
										.getCount()));

								// total
								if (!items[itemsLen - 1].getText(k + 1).equals(
										"")) {
									colVal = Integer
											.parseInt(items[itemsLen - 1]
													.getText(k + 1));
								} else {
									colVal = 0;
								}
								items[itemsLen - 1].setText(k + 1, String
										.valueOf(colVal + dto[i].getCount()));

								// COL
								if (!items[j].getText(colTotIndex).equals("")) {
									colVal = Integer.parseInt(items[j]
											.getText(colTotIndex));
								} else {
									colVal = 0;
								}
								items[j].setText(colTotIndex, String
										.valueOf(colVal + dto[i].getCount()));

							} else if (cbOption.getText().equals(BASIC_FREIGHT)) {
								items[j].setText(k + 1, df.format(dto[i]
										.getBasic_freight()));
								// total
								if (!items[itemsLen - 1].getText(k + 1).equals(
										"")) {
									colFloatVal = Float
											.parseFloat(items[itemsLen - 1]
													.getText(k + 1));
								} else {
									colFloatVal = 0;
								}
								items[itemsLen - 1].setText(k + 1, df
										.format(colFloatVal
												+ dto[i].getBasic_freight()));

								// COL total
								if (!items[j].getText(colTotIndex).equals("")) {
									colFloatVal = Float.parseFloat(items[j]
											.getText(colTotIndex));
								} else {
									colFloatVal = 0;
								}
								items[j].setText(colTotIndex, df
										.format(colFloatVal
												+ dto[i].getBasic_freight()));
							} else if (cbOption.getText().equals(TOTAL_FREIGHT)) {
								items[j].setText(k + 1, df.format(dto[i]
										.getTotal_freight()));
								// total
								if (!items[itemsLen - 1].getText(k + 1).equals(
										"")) {
									colFloatVal = Float
											.parseFloat(items[itemsLen - 1]
													.getText(k + 1));
								} else {
									colFloatVal = 0;
								}
								items[itemsLen - 1].setText(k + 1, df
										.format(colFloatVal
												+ dto[i].getTotal_freight()));

								// COL total
								if (!items[j].getText(colTotIndex).equals("")) {
									colFloatVal = Float.parseFloat(items[j]
											.getText(colTotIndex));
								} else {
									colFloatVal = 0;
								}
								items[j].setText(colTotIndex, df
										.format(colFloatVal
												+ dto[i].getTotal_freight()));

							} else if (cbOption.getText().equals(ACTUAL_WEIGHT)) {
								items[j].setText(k + 1, df.format(dto[i]
										.getActual_weight()));

								// total
								if (!items[itemsLen - 1].getText(k + 1).equals(
										"")) {
									colFloatVal = Float
											.parseFloat(items[itemsLen - 1]
													.getText(k + 1));
								} else {
									colFloatVal = 0;
								}
								items[itemsLen - 1].setText(k + 1, df
										.format(colFloatVal
												+ dto[i].getActual_weight()));

								// COL total
								if (!items[j].getText(colTotIndex).equals("")) {
									colFloatVal = Float.parseFloat(items[j]
											.getText(colTotIndex));
								} else {
									colFloatVal = 0;
								}
								items[j].setText(colTotIndex, String
										.valueOf(colFloatVal
												+ dto[i].getActual_weight()));
							} else if (cbOption.getText()
									.equals(CHARGED_WEIGHT)) {
								items[j].setText(k + 1, df.format(dto[i]
										.getCharged_weight()));

								// total
								if (!items[itemsLen - 1].getText(k + 1).equals(
										"")) {
									colFloatVal = Float
											.parseFloat(items[itemsLen - 1]
													.getText(k + 1));
								} else {
									colFloatVal = 0;
								}
								items[itemsLen - 1].setText(k + 1, df
										.format(colFloatVal
												+ dto[i].getCharged_weight()));

								// COL total
								if (!items[j].getText(colTotIndex).equals("")) {
									colFloatVal = Float.parseFloat(items[j]
											.getText(colTotIndex));
								} else {
									colFloatVal = 0;
								}
								items[j].setText(colTotIndex, df
										.format(colFloatVal
												+ dto[i].getCharged_weight()));
							} else if (cbOption.getText().equals(NOA)) {
								items[j].setText(k + 1, String.valueOf(dto[i]
										.getNoa()));

								// total
								if (!items[itemsLen - 1].getText(k + 1).equals(
										"")) {
									colVal = Integer
											.parseInt(items[itemsLen - 1]
													.getText(k + 1));
								} else {
									colVal = 0;
								}
								items[itemsLen - 1].setText(k + 1, String
										.valueOf(colVal + dto[i].getNoa()));

								// Col total
								if (!items[j].getText(colTotIndex).equals("")) {
									colVal = Integer.parseInt(items[j]
											.getText(colTotIndex));
								} else {
									colVal = 0;
								}
								items[j].setText(colTotIndex, String
										.valueOf(colVal + dto[i].getNoa()));
							}

						}
					}

				}

			}
		}

		if (cbOption.getText().equals(COUNT) || cbOption.getText().equals(NOA)) {
			int finalVal = 0;
			for (int i = 0; i < itemsLen; i++) {
				// Final Total
				if (!items[i].getText(colTotIndex).equals("")) {
					colVal = Integer.parseInt(items[i].getText(colTotIndex));
				} else {
					colVal = 0;
				}

				finalVal = colVal + finalVal;

			}
			items[itemsLen - 1].setText(colTotIndex, String.valueOf(finalVal));
		} else {
			float finalVal = 0;
			for (int i = 0; i < itemsLen; i++) {
				// Final Total
				if (!items[i].getText(colTotIndex).equals("")) {
					colFloatVal = Float.parseFloat(items[i]
							.getText(colTotIndex));
				} else {
					colFloatVal = 0;
				}

				finalVal = colFloatVal + finalVal;

			}
			items[itemsLen - 1].setText(colTotIndex, df.format(finalVal));
		}
		
		fillZerosOnEmpty(tblInwardAnalysis);

	}

	private boolean designTable() throws Exception {

		if (tblInwardAnalysis != null) {
			tblInwardAnalysis.dispose();
		}

		tblInwardAnalysis = new Table(cvsInwardAnalysis, SWT.FULL_SELECTION
				| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		tblInwardAnalysis.setBounds(5, 90, 750, 380);

		tblInwardAnalysis.setHeaderVisible(true);
		tblInwardAnalysis.setLinesVisible(true);

		if (cbFromStation.getText().equalsIgnoreCase("All")
				&& cbInwardStation.getText().equalsIgnoreCase("All")) {
			COLUMNS = BRANCHES;
			allFieldsTable();
		} else {
			String[] rows = null;
			if (cbInwardStation.getText().equalsIgnoreCase("All")) {
				rows = BRANCHES;
				COLUMNS = getBranchStations(cbFromStation.getText());

			} else if (cbFromStation.getText().equalsIgnoreCase("All")) {
				rows = getBranchStations(cbInwardStation.getText());
				COLUMNS = BRANCHES;
			} else {
				rows = getBranchStations(cbInwardStation.getText());
				COLUMNS = getBranchStations(cbFromStation.getText());
			}

			createDynamicTable(rows, COLUMNS);
		}

		return true;

	}

	/**
	 * 
	 * @param rows
	 * @param columns
	 */
	private void createDynamicTable(String[] rows, String[] columns) {
		int colLen = columns.length;
		int rowLen = rows.length;

		// TOTAL 1
		int width = ((colLen + 2) * COLUMN_WIDTH) + 30;
		int height = ((rowLen + 2) * tblInwardAnalysis.getItemHeight()) + 40;
		if (colLen == BRANCHES.length) {
			// width = width - 3;
		}

		for (int i = 0; i < colLen + 1; i++) {
			column = new TableColumn(tblInwardAnalysis, SWT.CENTER);
			column.setAlignment(SWT.RIGHT);
			if (i != 0) {
				column.setText(columns[i - 1]);
				tblInwardAnalysis.getColumn(i).setWidth(COLUMN_WIDTH);
			} else {
				tblInwardAnalysis.getColumn(i).setWidth(COLUMN_WIDTH - 10);
			}

		}

		for (int j = 0; j < rowLen; j++) {
			TableItem item = new TableItem(tblInwardAnalysis, SWT.NONE);
			item.setText(0, rows[j]);
			// item.setBackground(0, new Color(Display.getCurrent(),
			// 210,180,140));
			item.setBackground(0,
					new Color(Display.getCurrent(), 237, 234, 219));
		}

		/*
		 * if(width > TBL_WIDTH){ height = height + 20; }
		 * 
		 * if(height > TBL_HEIGHT){ width = width + 20; }
		 */
		// For Horizontal bar
		width = (width < TBL_WIDTH) ? width : TBL_WIDTH + 10;
		// For Vertical bar
		height = (height < TBL_HEIGHT) ? height : TBL_HEIGHT;

	}

	/**
	 * 
	 * @param branch
	 * @return
	 * @throws Exception
	 */
	private String[] getBranchStations(String branch) throws Exception {
		StationsDTO[] stations = null;
		stations = handler.getAllStations();
		if (stations != null) {
			int len = stations.length;
			ArrayList<String> list = new ArrayList<String>();
			for (int i = 0; i < len; i++) {
				if (branch.equalsIgnoreCase(stations[i].getBranch_code())) {
					list.add(stations[i].getId());
				}
			}

			int size = list.size();
			if (size > 0) {
				return (String[]) list.toArray(new String[size]);
			}
		}

		return null;
	}

	/**
	 * 
	 */
	private void allFieldsTable() {

		int colLen = BRANCHES.length;
		int width = ((colLen + 2) * COLUMN_WIDTH) + 30;
		int height = ((colLen + 2) * tblInwardAnalysis.getItemHeight()) + 40;

		// System.out.println("ht-->" + tblInwardAnalysis.getItemHeight());
		for (int i = 0; i < colLen + 1; i++) {
			column = new TableColumn(tblInwardAnalysis, SWT.CENTER);
			column.setAlignment(SWT.RIGHT);
			if (i != 0)
				column.setText(BRANCHES[i - 1]);
			tblInwardAnalysis.getColumn(i).setWidth(COLUMN_WIDTH);

			if (i != colLen) {
				TableItem item = new TableItem(tblInwardAnalysis, SWT.NONE);
				item.setText(0, BRANCHES[i]);
				item.setBackground(0, new Color(Display.getCurrent(), 237, 234,
						219));
			}
		}

		/*
		 * if(width > TBL_WIDTH){ height = height + 20; }
		 * 
		 * if(height > TBL_HEIGHT){ width = width + 20; }
		 */
		// For Horizontal bar
		width = (width < TBL_WIDTH) ? width : TBL_WIDTH + 10;
		// For Vertical bar
		height = (height < TBL_HEIGHT) ? height : TBL_HEIGHT;

	}

	/**
	 * 
	 * @throws Exception
	 */
	private void createMarketTable() throws Exception {
		
		VehicleDTO[] dto = handler.getVehicles();
		
		if (null != dto) {
			tblMarketVehicleUtilisation.dispose();
			{
				tblMarketVehicleUtilisation = new Table(canMarkVehiUtil,
						SWT.BORDER);
				tblMarketVehicleUtilisation.setHeaderVisible(true);
				tblMarketVehicleUtilisation.setLinesVisible(true);
				tblMarketVehicleUtilisation.setBounds(10, 37, 750, 400);

			}
			int len = dto.length;
			if (len > 0) {
				TableColumn column = new TableColumn(
						tblMarketVehicleUtilisation, SWT.NONE);
				column.setText("Sno");
				column.setWidth(40);
				column = new TableColumn(tblMarketVehicleUtilisation, SWT.NONE);
				column.setText("Station Code");
				column.setWidth(80);
				for (int i = 0; i < len; i++) {
					column = new TableColumn(tblMarketVehicleUtilisation,
							SWT.NONE);
					column.setText(dto[i].getVehicle_model()
							+ "-No of vehicles");
					column.setWidth(120);

					column = new TableColumn(tblMarketVehicleUtilisation,
							SWT.NONE);
					column
							.setText(dto[i].getVehicle_model() + "-"
									+ " Amount");
					column.setWidth(100);
					/*column.setText(dto[i].getVehicle_model());
					column.setWidth(220);
					TableRow column1 = new TableRow(tblMarketVehicleUtilisation,1);
					column1.setText("No of vehicles");
					column1.setWidth(120);
					column1 = new TableRow(tblMarketVehicleUtilisation,
							SWT.NONE);
					column1.setText("Amount");
					column1.setWidth(100);*/
				}
				for (int index = 0; index < tblMarketVehicleUtilisation
						.getColumnCount(); index++) {
					tblMarketVehicleUtilisation.getColumn(index).addListener(
							SWT.Selection, new sortListener());
				}

			}
		}
	}

	private void handleMarketVehicle() {

		try {

			String monthyear = txtMarkVehiUtilSelectMonth.getText();
			int index = monthyear.indexOf("-");
			int month = Integer.parseInt(monthyear.substring(0, index).trim());
			int year = Integer.parseInt(monthyear.substring(index + 1).trim());

			if (null != handler) {
				// System.out.println(month + " " + year);
				MarketVehicleDTO[] dto = handler.getMarketVehicles(
						getSelectedBranch(cbMarkVehiUtilSelectBranch), month,
						year);
				if (null != dto) {
					populateMarketVehicle(dto);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*private MarketVehicleDTO[] getMarketVehicles(String selectedBranch,
			int month, int year) {
		// TODO Auto-generated method stub
		MarketVehicleDTO[] dto = null;
		try {
			/*Date curDate = new Date();
			int m1 = fromDt.getYear() * 12 + fromDt.getMonth();
	        int m2 = curDate.getYear() * 12 + curDate.getMonth();
	        monthDiff = m2 - m1;
			int smonth = BeanUtil.getThree_month_updated().getMonth();
			int syear = BeanUtil.getThree_month_updated().getYear();
		if((month ==smonth) && (year == syear) ){
			//System.out.println("in ddr histry-->"+date);
			dto = handler.getMarketVehicles(getSelectedBranch(cbMarkVehiUtilSelectBranch), month,year);
		}else{
			dto = handler.getMarketVehicles(getSelectedBranch(cbMarkVehiUtilSelectBranch), month,year);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
		
		
		
	}*/

	/**
	 * 
	 * @param dto
	 * @throws Exception 
	 */
	private void populateMarketVehicle(MarketVehicleDTO[] dto) throws Exception {
		
		if (null != tblMarketVehicleUtilisation) {
			if (null != dto) {
				
				int len = dto.length;
				if (len > 0) {
						
					for (int j = 0; j < len; j++) {
						TableItem item = isTableItemAvailForMarket(getBranch_code(dto[j].getTo_station()));
						if (null == item){
							item = new TableItem(tblMarketVehicleUtilisation,
									SWT.NONE);
						}
						TableColumn[] columns = tblMarketVehicleUtilisation
								.getColumns();
						//item.setText(1, getBranch_code(dto[j].getTo_station()));
						for (int k = 2; k < columns.length;) {
							//if(!(cbMarkVehiUtilSelectBranch.getText().equalsIgnoreCase("All"))){
													 
							String name = columns[k].getText().substring(0,columns[k].getText().indexOf("-"));
							
							if (name.equalsIgnoreCase(dto[j].getVehicle_name())) {
								item.setText(k++, String.valueOf(dto[j]
										.getNo_vehicle()));
								item.setText(k++, getRoundedValue(dto[j]
										.getAmount()));
							} else
								k = k + 2;
						}
					}

					int columns = tblMarketVehicleUtilisation.getColumnCount();
					int[] no = new int[(columns - 2)];
					float[] amount = null;
					amount = new float[(columns - 2)];
					int one = 0;
					int two = 0;
					isTotalTableItemAvailForMarket("Total");
					for (int j = 2; j < columns; j++) {

						TableItem items[] = tblMarketVehicleUtilisation
								.getItems();
						int len1 = items.length;

						for (int i = 0; i < len1; i++) {
							if (j % 2 == 0) {
								if(!items[i].getText(j).equals("")){
								no[one] = no[one]
										+ Integer.parseInt(items[i].getText(j));
								}
							} else {
								if(!items[i].getText(j).equals("")){
								amount[two] = amount[two]
										+ Float.parseFloat(items[i].getText(j));
								}
								//System.out.println(amount[two]);
							}
						}
						if (j % 2 == 0) {
							one++;

						} else {
							two++;
						}
					}

					if (null != no) {
						int size = no.length;
						if (size > 0) {
							TableItem item = null;
							item = isTotalTableItemAvailForMarket("Total");
							if (item == null)
								item = new TableItem(
										tblMarketVehicleUtilisation, SWT.NONE);
							Font font1 = new Font(Display.getDefault(), "Tahoma", 8, SWT.BOLD);
							item.setFont(font1);
							item.setText(1, String.valueOf("Total"));
							int index = 2;
							for (int k = 0; k < size; k++) {
								item.setText(index++, String.valueOf(no[k]));
								item
										.setText(index++, getRoundedValue(amount[k]));
							}
						}
					}

				}
			}
		}

	}

	private TableItem isTableItemAvailForMarket(String to_station) {
		if (null != tblMarketVehicleUtilisation) {
			TableItem[] items = tblMarketVehicleUtilisation.getItems();
			if (null != items) {
				int len = items.length;
				if (len > 0) {
					for (int i = 0; i < len; i++) {
						if (items[i].getText(1).equalsIgnoreCase(to_station)) {
							return items[i];
						}
					}
				}
			}
		}
		return null;

	}

	private TableItem isTotalTableItemAvailForMarket(String to_station) {
		if (null != tblMarketVehicleUtilisation) {
			TableItem[] items = tblMarketVehicleUtilisation.getItems();
			if (null != items) {
				int len = items.length;
				if (len > 0) {
					for (int i = 0; i < len; i++) {
						if (items[i].getText(0).equalsIgnoreCase(to_station)) {
							items[i].dispose();
							return null;
						}
					}
				}
			}
		}
		return null;

	}

	private void handleFOCLR() {

		if (null != tblFOCLr)
			tblFOCLr.removeAll();

		try {
			int index = -1;

			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date from_date = dateFormat.parse(txtFOCLrFrom.getText());
			Date to_date = dateFormat.parse(txtFOCLrTo.getText());

			index = cbFOCLrBranch.getSelectionIndex();
			if (index > -1) {
				String branch_code = null;
				if (index != 0) {
					branch_code = cbFOCLrBranch.getItem(index);
					index = branch_code.indexOf("-");
					branch_code = branch_code.substring(index + 1).trim();
				} else {
					branch_code = "%";
				}

				index = cbFOCLrReportType.getSelectionIndex();
				if (index > -1) {

					// System.out.println(from_date + "To" + to_date +
					// branch_code);
					populateFOCLRTable(from_date, to_date, branch_code, index);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void populateFOCLRTable(Date from_date, Date to_date,
			String branch_code, int type) {
		try {

			if (null != handler) {

				BookingDTO[] focLRS = getFOCLRS(from_date, to_date,
						branch_code, type);

				if (type == 0) {
					if (null != focLRS) {
						int len = focLRS.length;
						int j = 1;
						int noa = 0;
						float chargedWt = 0;
						float artValue = 0;
						for (int i = 0; i < len; i++) {

							TableItem item = new TableItem(tblFOCLr, SWT.NONE);
							item.setText(0, String.valueOf(j++));
							item
									.setText(1, String.valueOf(focLRS[i]
											.getLrno()));
							item
									.setText(2, String.valueOf(focLRS[i]
											.getDate()));
							item
									.setText(3, String.valueOf(focLRS[i]
											.getFrom()));
							item.setText(4, String.valueOf(focLRS[i].getTo()));

							noa = noa + focLRS[i].getNo_of_articles();
							item.setText(5, String.valueOf(focLRS[i]
									.getNo_of_articles()));

							chargedWt = chargedWt
									+ focLRS[i].getCharged_weight();
							item.setText(6, getRoundedValue(focLRS[i]
									.getCharged_weight()));

							item.setText(7, String.valueOf(focLRS[i]
									.getArticle_id()));

							artValue = artValue + focLRS[i].getArticle_value();
							item.setText(8, getRoundedValue(focLRS[i]
									.getArticle_value()));

						}
						if (len > 0) {
							TableItem item = new TableItem(tblFOCLr, SWT.NONE);
							Font font1 = new Font(Display.getDefault(), "Tahoma", 8, SWT.BOLD);
							item.setFont(font1);
							item.setText(1, String.valueOf("Total"));
							item.setText(5, String.valueOf(noa));
							item.setText(6, getRoundedValue(chargedWt));
							item.setText(8, getRoundedValue(artValue));
						}
					}
				} else {
					populateDefaultTable(branch_code, tblFOCLr, 9);
					if (null != focLRS) {
						int len = focLRS.length;
						int j = 1;
						for (int i = 0; i < len; i++) {

							if (null != tblFOCLr) {
								boolean isAvail = false;
								TableItem[] items = tblFOCLr.getItems();
								if (null != items) {
									int noItem = items.length;
									if (noItem > 0) {
										String station = null;
										for (int k = 0; k < noItem; k++) {
											station = items[k].getText(2);
											int lr = 0;
											int foc = 0;
											float lrwt = 0;
											float focwt = 0;
											String temp = null;
											if (station
													.equalsIgnoreCase(focLRS[i]
															.getFrom())) {

												temp = items[k].getText(3);
												if (!temp.equalsIgnoreCase(""))
													lr = Integer.parseInt(temp);

												temp = items[k].getText(4);
												if (!temp.equalsIgnoreCase(""))

													foc = Integer
															.parseInt(temp);

												temp = items[k].getText(6);
												if (!temp.equalsIgnoreCase(""))
													lrwt = Float
															.parseFloat(temp);

												temp = items[k].getText(7);
												if (!temp.equalsIgnoreCase(""))
													focwt = Float
															.parseFloat(temp);

												if (focLRS[i].getRate_type() != 6) {
													lr = lr
															+ (focLRS[i]
																	.getNo_of_articles());
													lrwt = lrwt
															+ focLRS[i]
																	.getActual_weight();
												} else {
													foc = foc
															+ (focLRS[i]
																	.getNo_of_articles());
													focwt = focwt
															+ focLRS[i]
																	.getActual_weight();
												}

												items[k].setText(3, String
														.valueOf(lr + foc));
												items[k].setText(4, String
														.valueOf(foc));

												items[k].setText(6, getRoundedValue(lrwt + focwt));
												items[k].setText(7, getRoundedValue(focwt));

												items[k].setText(5, getRoundedValue((float) foc
																/ (foc + lr)
																* 100));

												items[k]
														.setText(
																8,
																getRoundedValue(focwt
																				/ (focwt + lrwt)
																				* 100));

												isAvail = true;

											}
										}
									}
								}

								if (!isAvail) {

									TableItem item = new TableItem(tblFOCLr,
											SWT.NONE);
									item.setText(0, String.valueOf(j++));
									item.setText(1, String.valueOf(focLRS[i]
											.getTo()));
									item.setText(2, String.valueOf(focLRS[i]
											.getFrom()));
									int totallr = 0;
									int foc = 0;
									float totalwt = 0;
									float focwt = 0;
									if (focLRS[i].getRate_type() != 6) {
										item.setText(3, String
												.valueOf(focLRS[i]
														.getNo_of_articles()));
										totallr = focLRS[i].getNo_of_articles();
										item.setText(6, getRoundedValue(focLRS[i]
														.getActual_weight()));
										totalwt = focLRS[i].getActual_weight();

										item.setText(4, String.valueOf(0));
										item.setText(7, String.valueOf(0));
									} else {
										item.setText(3, String.valueOf(0));
										item.setText(6, String.valueOf(0));

										item.setText(4, String
												.valueOf(focLRS[i]
														.getNo_of_articles()));
										foc = focLRS[i].getNo_of_articles();
										item.setText(7, getRoundedValue(focLRS[i]
														.getActual_weight()));
										focwt = focLRS[i].getActual_weight();
									}

									item.setText(5, getRoundedValue((float) foc
											/ (totallr + foc) * 100));

									item.setText(8, getRoundedValue(focwt
											/ (totalwt + focwt) * 100));

								}
							}

						}
						if (null != tblFOCLr) {
							TableItem[] items = tblFOCLr.getItems();
							if (null != items) {
								int len1 = items.length;
								int lr = 0;
								int foc = 0;
								float lrPercent = 0;
								float lrWt = 0;
								float focWt = 0;
								float wtPercent = 0;
								float lraverage = 0;
								float wtaverage = 0;
								for (int i = 0; i < len1; i++) {
									lr = lr
											+ Integer.parseInt(items[i]
													.getText(3));
									foc = foc
											+ Integer.parseInt(items[i]
													.getText(4));
									lrPercent = lrPercent
											+ Float.parseFloat(items[i]
													.getText(5));
									lrWt = lrWt
											+ Float.parseFloat(items[i]
													.getText(6));
									focWt = focWt
											+ Float.parseFloat(items[i]
													.getText(7));
									wtPercent = wtPercent
											+ Float.parseFloat(items[i]
													.getText(8));
								}
								if (len1 > 0) {
									TableItem item = new TableItem(tblFOCLr,
											SWT.NONE);
									Font font1 = new Font(Display.getDefault(), "Tahoma", 8, SWT.BOLD);
									item.setFont(font1);
									item.setText(2, String.valueOf("Total"));
									
									TableItem item1 = new TableItem(tblFOCLr,
											SWT.NONE);
									item1.setFont(font1);
									item1.setText(4, String.valueOf("Average"));
									lraverage = lrPercent / len1;
									wtaverage = wtPercent / len1;
									
									item.setText(3, String.valueOf(lr));
									item.setText(4, String.valueOf(foc));
									item.setText(5, getRoundedValue(lrPercent));
									item.setText(6, getRoundedValue(lrWt));
									item.setText(7, getRoundedValue(focWt));
									item.setText(8, getRoundedValue(wtPercent));
									item1.setText(5, getRoundedValue(lraverage));
									item1.setText(8, getRoundedValue(wtaverage));
									
								}
							}
						}
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	private BookingDTO[] getFOCLRS(Date from_date, Date to_date,
			String branch_code, int type) {
		//int monthDiff = 0;
		BookingDTO[] bookedLr = null;
		try {
			/*Date curDate = new Date();
			int m1 = from_date.getYear() * 12 + from_date.getMonth();
	        int m2 = curDate.getYear() * 12 + curDate.getMonth();
	        monthDiff = m2 - m1;*/
			Date date = BeanUtil.getThree_month_updated();
		if(from_date.before(date)){
			//System.out.println("in ddr histry-->"+date);
			bookedLr = handler.getFOCLRSHistory(from_date, to_date,branch_code,type);
		}else{
			bookedLr = handler.getFOCLRS(from_date, to_date,branch_code,type);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookedLr;
		
	}

	private void getDetailedFOCLRTable() {

		if (null != tblFOCLr)
			tblFOCLr.removeAll();

		colFOCSno.setText("Sno");

		colFOCLrLrNo.setText("Lr No");

		colFOCLrLrDate.setText("Lr Date");

		colFOCLrLrFrom.setText("From");

		colFOCLrTo.setText("To");

		colFOCLrNoa.setText("No. of Articles");

		colFOCLrCrgWt.setText("Crg Wt");

		colFOCLrArticleType.setText("Article Type");

		colFOCLrArticleValue.setText("Article Value");

	}

	private void getSummaryFOCLRTable() {

		if (null != tblFOCLr)
			tblFOCLr.removeAll();

		colFOCSno.setText("Sno");

		colFOCLrLrNo.setText("Branch Code");

		colFOCLrLrDate.setText("Station Code");

		colFOCLrLrFrom.setText("Total Lrs");

		colFOCLrTo.setText("FOC Lrs");

		colFOCLrNoa.setText("LR%");

		colFOCLrCrgWt.setText("Total Weight");

		colFOCLrArticleType.setText("FOC Weight");

		colFOCLrArticleValue.setText("WT%");

	}

	private void handleOpenLRs() {

		if (null != tblOpenLr)
			tblOpenLr.removeAll();

		try {
			int index = -1;

			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date from_date = dateFormat.parse(txtOpenLrFrom.getText());
			Date to_date = dateFormat.parse(txtOpenLrTo.getText());

			index = cbOpenLrBranch.getSelectionIndex();
			if (index > -1) {
				String branch_code = null;
				if (index != 0) {
					branch_code = cbOpenLrBranch.getItem(index);
					index = branch_code.indexOf("-");
					branch_code = branch_code.substring(index + 1).trim();
				} else {
					branch_code = "%";
				}

				populateOpenLRTable(from_date, to_date, branch_code);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param from_date
	 * @param to_date
	 * @param branch
	 */
	private void populateOpenLRTable(Date from_date, Date to_date, String branch) {
		try {

			if (null != handler) {

				BookingDTO[] openLRS = getOpenLRS(from_date, to_date,
						branch);

				if (null != openLRS) {
					int len = openLRS.length;
					int j = 1;
					float discount = 0;
					float other = 0;

					/* Total */
					float cr = 0;
					float bft = 0;
					float ccc = 0;
					float ddc = 0;
					float others = 0;
					float total = 0;
					float dis = 0;
					float cwt = 0;
					for (int i = 0; i < len; i++) {

						TableItem item = new TableItem(tblOpenLr, SWT.NONE);
						item.setText(0, String.valueOf(j++));
						item.setText(1, String.valueOf(openLRS[i]
								.getCreatedby()));
						item.setText(2, String.valueOf(openLRS[i].getFrom()));
						item.setText(3, String.valueOf(openLRS[i].getTo()));
						item.setText(4, String.valueOf(openLRS[i].getLrno()));
						item.setText(5, String.valueOf(openLRS[i].getDate()));
						item.setText(6, String.valueOf(openLRS[i].getType()));
						discount = (openLRS[i].getBft()/openLRS[i].getActual_bft() * 100) - 100;
						// if(discount>0)
						item.setText(7, String
								.valueOf(getRoundedValue(openLRS[i]
										.getActual_bft()
										)));
						cr = cr + Float.parseFloat(item.getText(7));
						// else
						// item.setText(7, String.valueOf(0));

						other = openLRS[i].getTotal()
								- (openLRS[i].getBft() + openLRS[i].getCcc() + openLRS[i]
										.getDdc());
						item.setText(8, getRoundedValue(openLRS[i].getBft()));
						bft = bft + Float.parseFloat(item.getText(8));
						item.setText(9, getRoundedValue(openLRS[i].getCcc()));
						ccc = ccc + Float.parseFloat(item.getText(9));
						item.setText(10, getRoundedValue(openLRS[i].getDdc()));
						ddc = ddc + Float.parseFloat(item.getText(10));
						item.setText(11, getRoundedValue(other));
						others = others + (other);
						item.setText(12, getRoundedValue(openLRS[i].getTotal()));
						total = total + Float.parseFloat(item.getText(12));
						
						if(discount > 0){
							item.setText(13,"+" + getRoundedValue(discount));
						}else{
							item.setText(13, getRoundedValue(discount));
						}
						
						
						dis = dis + Float.parseFloat(item.getText(13));

						/*
						 * item.setText(13, String.valueOf(openLRS[i]
						 * .getNo_of_articles()));
						 */
						item.setText(14, getRoundedValue(openLRS[i]
								.getCharged_weight()));
						cwt = cwt + Float.parseFloat(item.getText(14));
						item.setText(15, String.valueOf(openLRS[i]
								.getConsignorName()));
						item.setText(16, String.valueOf(openLRS[i]
								.getConsigneeName()));

					}
					TableItem item = new TableItem(tblOpenLr, SWT.NONE);
					Font font1 = new Font(Display.getDefault(), "Tahoma", 8, SWT.BOLD);
					item.setFont(font1);
					item.setText(1, String.valueOf("Total"));
					item.setText(7, String.valueOf(getRoundedValue(cr)));
					item.setText(8, String.valueOf(getRoundedValue(bft)));
					item.setText(9, getRoundedValue(ccc));
					item.setText(10, getRoundedValue(ddc));
					item.setText(11, getRoundedValue(others));
					item.setText(12, getRoundedValue(total));
					item.setText(13, getRoundedValue(dis));
					item.setText(14, getRoundedValue(cwt));

				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	private BookingDTO[] getOpenLRS(Date from_date, Date to_date, String branch) {
		//int monthDiff = 0;
		BookingDTO[] openLRS = null;
		try {
			/*Date curDate = new Date();
			int m1 = from_date.getYear() * 12 + from_date.getMonth();
	        int m2 = curDate.getYear() * 12 + curDate.getMonth();
	        monthDiff = m2 - m1;*/
			Date date = BeanUtil.getThree_month_updated();
		if(from_date.before(date)){
			//System.out.println("in ddr histry-->"+date);
			openLRS = handler.getOpenLRSHistory(from_date, to_date,branch);
		}else{
			openLRS = handler.getOpenLRS(from_date, to_date,branch);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return openLRS;
	}

	private void populateStationOrBranchForDRS() {
		try {
			if (tblDRSAttendance != null) {
				tblDRSAttendance.removeAll();

				int index = -1;
				index = cbDRSAttendanceBranch.getSelectionIndex();
				if (index > -1) {
					String branch = cbDRSAttendanceBranch.getItem(index);
					StationsDTO[] dto = null;
					index = -1;
					if (!branch.equalsIgnoreCase("All")) {
						index = branch.indexOf("-");
						branch = branch.substring(index + 1).trim();
						dto = handler.getStations(branch);
					} else if (branch.equalsIgnoreCase("All")) {
						branch = "%";
						dto = handler.getAllStations();
					}

					if (null != dto) {
						int len = dto.length;
						int cols = tblDRSAttendance.getColumnCount();
						for (int i = 0; i < len; i++) {
							TableItem item = new TableItem(tblDRSAttendance,
									SWT.NONE);
							item.setText(0, String.valueOf(i + 1));
							item.setText(1, dto[i].getBranch_code());
							item.setText(2, dto[i].getId());
							for (int j = 3; j < cols; j++) {
								item.setText(j, "0");
							}
						}
						
						
					}

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getDRSAttendance() {

		try {

			String monthyear = txtDRSMonth.getText();
			int index = monthyear.indexOf("-");
			int month = Integer.parseInt(monthyear.substring(0, index).trim());
			int year = Integer.parseInt(monthyear.substring(index + 1).trim());

			if (null != handler) {
				 //System.out.println(month + " " + year);
				DRSAttendanceDTO[] dto = handler.getDRSAttendance(
						cbDRSAReportType.getSelectionIndex(),
						getSelectedBranch(cbDRSAttendanceBranch), month, year);
				if (null != dto) {
					populateDRSAttendance(cbDRSAReportType.getSelectionIndex(),
							dto, month, year);
				}
				else
				{
					populateDRSAttendancenull(cbDRSAReportType.getSelectionIndex(),
							dto, month, year);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void populateDRSAttendancenull(int type, DRSAttendanceDTO[] dto,
			int month, int year) throws Exception {
		if (tblDRSAttendance != null) {

			/*int len = dto.length;
			/SimpleDateFormat monthformate = new SimpleDateFormat("MM");
			SimpleDateFormat dayformate = new SimpleDateFormat("dd");*/

			TableItem[] items = tblDRSAttendance.getItems();
			int colunms = tblDRSAttendance.getColumnCount();
			for (int j = 0; j < items.length; j++) {
				for (int k = 3; k < colunms; k++) {

					items[j].setBackground(k, new UIColors().red);

				}
			}
		}
		//fillZerosOnEmpty(tblDRSAttendance);
		TableItem item1 = new TableItem(tblDRSAttendance, SWT.NONE);
		Font font1 = new Font(Display.getDefault(), "Tahoma", 8, SWT.BOLD);
		item1.setFont(font1);
		item1.setText(2, String.valueOf("Total"));
		if(type == 1 )
		{
			TableItem item2 = new TableItem(tblDRSAttendance, SWT.NONE);
			item2.setFont(font1);
			item2.setText(4, String.valueOf("Average"));
			item2.setText(5, "0");
			item2.setText(8, "0");
			item2.setText(11, "0");
		}
		for(int i=3;i<tblDRSAttendance.getColumnCount();i++)
			item1.setText(i,"0" );
		
		
	}
	
	
	
	
	
	private void populateDRSAttendance(int type, DRSAttendanceDTO[] dto,
			int month, int year) throws Exception {
		
		Integer finalvalue[] =new Integer[tblDRSAttendance.getColumnCount()];
			for(int i=0;i<tblDRSAttendance.getColumnCount();i++)
				finalvalue[i]=0;
		String month1 = "01-" + month + "-" + year;
		if (month != 1) {
			month = month - 1;
		} else {
			month = 12;
			year = year - 1;
		}
		String month2 = "01-" + month + "-" + year;

		if (month != 1) {
			month = month - 1;
		} else {
			month = 12;
			year = year - 1;
		}
		String month3 = "01-" + month + "-" + year;
		
		float average1 = 0;
		float average2 = 0;
		float average3 = 0;
		
		int count=0;
		
		if (tblDRSAttendance != null) {

			int len = dto.length;
			SimpleDateFormat monthformate = new SimpleDateFormat("MM");
			SimpleDateFormat dayformate = new SimpleDateFormat("dd");

			TableItem[] items = tblDRSAttendance.getItems();
			int colunms = tblDRSAttendance.getColumnCount();
			for (int j = 0; j < items.length; j++) {
				for (int k = 3; k < colunms; k++) {

					items[j].setBackground(k, new UIColors().red);

				}
			}
			

			
			for (int i = 0; i < len; i++) {
				
				TableItem[] item = tblDRSAttendance.getItems();
				for (int j = 0; j < item.length; j++) {
				count = item.length;  	
					String stationcode = item[j].getText(2).trim();
					if (stationcode.equalsIgnoreCase(dto[i].getStation_code())) {
						if (type == 1) {
							if (monthformate.format(getDate(month1)).equals(
									monthformate.format(dto[i].getDrs_date()))) {
								if (dto[i].getStatus()
										.equalsIgnoreCase("total")){
									item[j].setText(10, String.valueOf(dto[i]
											.getCount()));
									finalvalue[10]=finalvalue[10] + Integer.parseInt(item[j].getText(10));
									}
								else {
									item[j].setText(9, String.valueOf(dto[i]
											.getCount()));
									finalvalue[9]=finalvalue[9] +  Integer.parseInt(item[j].getText(9));
								}
							}
							if (monthformate.format(getDate(month2)).equals(
									monthformate.format(dto[i].getDrs_date()))) {
								if (dto[i].getStatus()
										.equalsIgnoreCase("total"))
								{
									
									item[j].setText(7, String.valueOf(dto[i]
											.getCount()));
									finalvalue[7]=finalvalue[7] +  Integer.parseInt(item[j].getText(7));
								}
								else {
									item[j].setText(6, String.valueOf(dto[i]
											.getCount()));
									finalvalue[6]=finalvalue[6] +  Integer.parseInt(item[j].getText(6));
								}
							}

							if (monthformate.format(getDate(month3)).equals(
									monthformate.format(dto[i].getDrs_date()))) {
								if (dto[i].getStatus()
										.equalsIgnoreCase("total"))
								{
									item[j].setText(4, String.valueOf(dto[i]
											.getCount()));
									finalvalue[4]=finalvalue[4] +  Integer.parseInt(item[j].getText(4));
								}
								else {
									item[j].setText(3, String.valueOf(dto[i]
											.getCount()));
									finalvalue[3]=finalvalue[3] +  Integer.parseInt(item[j].getText(3));
								}
							}
						} else if ((type == 0)||(type==1)) {
							
							int itIndex = Integer.parseInt(dayformate
									.format(dto[i].getDrs_date())) + 2;
							int value = dto[i].getCount();
							if (value == 0)
								item[j].setBackground(itIndex,
										new UIColors().green);
								
							else {
								
								item[j].setText(itIndex, String.valueOf(value));
								finalvalue[itIndex]=finalvalue[itIndex]+ Integer.parseInt(item[j].getText(itIndex));
								
								item[j].setBackground(itIndex,
										new UIColors().blue);
								
								                                                                                                                      
							}

						}
					}
				
				}
				
			}

		}
		average1 = finalvalue[5] / count;
		average2 = finalvalue[8] / count;
		average3 = finalvalue[11] / count;
		
		fillZerosOnEmpty(tblDRSAttendance);
		TableItem item1 = new TableItem(tblDRSAttendance, SWT.NONE);
		Font font1 = new Font(Display.getDefault(), "Tahoma", 8, SWT.BOLD);
		item1.setFont(font1);
		item1.setText(2, String.valueOf("Total"));
		for(int i=3;i<tblDRSAttendance.getColumnCount();i++)
			item1.setText(i, String.valueOf(finalvalue[i]));
		if(type == 1)
		{
			TableItem item2 = new TableItem(tblDRSAttendance, SWT.NONE);
			item2.setFont(font1);
			item2.setText(4, String.valueOf("Average"));
			item2.setText(5, String.valueOf(getRoundedValue(average1)));
			item2.setText(8, String.valueOf(getRoundedValue(average2)));
			item2.setText(11, String.valueOf(getRoundedValue(average3)));
		}
		
	}

	/**
	 * 
	 * @param month1
	 * @return
	 * @throws Exception
	 */
	private Date getDate(String month1) throws Exception {
		SimpleDateFormat fr = new SimpleDateFormat("dd-MM-yyyy");
		return fr.parse(month1);

	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	private String getSelectedBranch(Combo cb) throws Exception {
		int index = cb.getSelectionIndex();
		if (index > -1) {
			if (index == 0)
				return "%";
			else {
				String branch = cb.getItem(index);
				index = 0;
				index = branch.indexOf("-");
				return branch = branch.substring(index + 1).trim();
			}

		} else {

			throw new Exception();
		}

	}

	private TableItem isAlreadyAvail(Table table, String valueOf, String station) {
		TableItem[] items = table.getItems();
		if (items != null) {
			int len = items.length;
			if (len > 0) {
				for (int i = 0; i < len; i++) {
					String itemText = items[i].getText(2);
					String itemStation = items[i].getText(1);
					if (valueOf.equalsIgnoreCase(itemText)
							&& station.equalsIgnoreCase(itemStation)) {
												
						return items[i];
					}
				}
			}
		}

		return null;
	}

	private void createDRSSummaryTable() throws Exception {

		if (null != tblDRSAttendance)
			tblDRSAttendance.dispose();

		tblDRSAttendance = new Table(cvsDRSAttendance, SWT.FULL_SELECTION
				| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);

		tblDRSAttendance.setHeaderVisible(true);
		tblDRSAttendance.setLinesVisible(true);
		tblDRSAttendance.setBounds(10, 72, 800, 400);
		{
			colDRSsno = new TableColumn(tblDRSAttendance, SWT.NONE);
			colDRSsno.setText("S NO");
			colDRSsno.setWidth(40);

		}
		{
			colDRSBranchCode = new TableColumn(tblDRSAttendance, SWT.NONE);
			colDRSBranchCode.setText("Branch Code");
			colDRSBranchCode.setWidth(80);

		}
		{
			colDRSStationCode = new TableColumn(tblDRSAttendance, SWT.NONE);
			colDRSStationCode.setText("Station Code");
			colDRSStationCode.setWidth(80);
		}
		{

			String DATE_FORMAT = "dd";

			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
					DATE_FORMAT);
			String DATE_FORMAT1 = "dd-MM-yyyy";
			java.text.SimpleDateFormat sdf1 = new java.text.SimpleDateFormat(
					DATE_FORMAT1);

			Calendar c1 = Calendar.getInstance();
			c1.setTime(sdf1.parse("01-" + txtDRSMonth.getText()));
			int month = c1.getActualMaximum(Calendar.DATE);

			int i = 0;
			do {
				if (i == 0)
					c1.add(Calendar.DATE, 0);
				else
					c1.add(Calendar.DATE, 1);
				i++;

				colDRSMon1Ontime = new TableColumn(tblDRSAttendance, SWT.NONE);
				colDRSMon1Ontime.setText(sdf.format(c1.getTime()));
				colDRSMon1Ontime.setWidth(40);

			} while ((i < month));
		}
		//populateStationOrBranchForDRS();
	}

	private void createDRSPercentReportTable() throws ParseException {

		if (null != tblDRSAttendance)
			tblDRSAttendance.dispose();

		tblDRSAttendance = new Table(cvsDRSAttendance, SWT.FULL_SELECTION
				| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);

		tblDRSAttendance.setHeaderVisible(true);
		tblDRSAttendance.setLinesVisible(true);
		tblDRSAttendance.setBounds(30, 70, 750, 400);
		{
			colDRSsno = new TableColumn(tblDRSAttendance, SWT.NONE);
			colDRSsno.setText("S NO");
			colDRSsno.setWidth(40);

		}
		{
			colDRSBranchCode = new TableColumn(tblDRSAttendance, SWT.NONE);
			colDRSBranchCode.setText("Branch Code");
			colDRSBranchCode.setWidth(80);

		}
		{
			colDRSStationCode = new TableColumn(tblDRSAttendance, SWT.NONE);
			colDRSStationCode.setText("Station Code");
			colDRSStationCode.setWidth(80);
		}

		String DATE_FORMAT = "MMM yyyy";
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				DATE_FORMAT);

		String DATE_FORMAT1 = "dd-MM-yyyy";
		java.text.SimpleDateFormat sdf1 = new java.text.SimpleDateFormat(
				DATE_FORMAT1);

		Calendar c1 = Calendar.getInstance();
		// c1.setTime(new Date());
		c1.setTime(sdf1.parse("01-" + txtDRSMonth.getText()));

		for (int j = 3; j > 0; j--) {
			if (j == 3)
				c1.add(Calendar.MONTH, 0);
			else
				c1.add(Calendar.MONTH, -1);

			colDRSMon1Ontime = new TableColumn(tblDRSAttendance, SWT.NONE);
			colDRSMon1Ontime.setText(sdf.format(c1.getTime()) + " Ontime");
			colDRSMon1Ontime.setWidth(120);

			colDRSMon1Total = new TableColumn(tblDRSAttendance, SWT.NONE);
			colDRSMon1Total.setText(sdf.format(c1.getTime()) + " Total");
			colDRSMon1Total.setWidth(120);

			colDRSMon1Percent = new TableColumn(tblDRSAttendance, SWT.NONE);
			colDRSMon1Percent.setText(sdf.format(c1.getTime()) + " %");
			colDRSMon1Percent.setWidth(150);

		}
		//populateStationOrBranchForDRS();

	}

	private void createCneeBATable() {
		int[] indexs = lstCneePickMonths.getSelectionIndices();
		String[] months = null;
		int len = indexs.length;
		if (len > 0) {
			months = new String[len];

			if (null != tblCneeBookingAnalysis)
				tblCneeBookingAnalysis.dispose();

			{
				tblCneeBookingAnalysis = new Table(cvsCneeBookingAnalysis,
						SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL
								| SWT.BORDER);

				tblCneeBookingAnalysis.setHeaderVisible(true);
				tblCneeBookingAnalysis.setLinesVisible(true);
				tblCneeBookingAnalysis.setBounds(30, 100, 710, 350);

			}
			{
				lblCBAOption = new Label(cvsCneeBookingAnalysis, SWT.NONE);
				lblCBAOption.setText("Select Option");
				lblCBAOption.setBounds(627, 13, 72, 22);
			}
			{
				colCneeBDsno = new TableColumn(tblCneeBookingAnalysis, SWT.NONE);
				colCneeBDsno.setText("S NO");
				colCneeBDsno.setWidth(40);

			}
			{
				colCneeBDBranchCode = new TableColumn(tblCneeBookingAnalysis,
						SWT.NONE);
				colCneeBDBranchCode.setText("Branch Code");
				colCneeBDBranchCode.setWidth(80);

			}
			{
				colCneeBDCneeName = new TableColumn(tblCneeBookingAnalysis,
						SWT.NONE);
				colCneeBDCneeName.setText("Cnee Name");
				colCneeBDCneeName.setWidth(100);

			}
			for (int i = 0; i < len; i++) {
				months[i] = lstCneePickMonths.getItem(indexs[i]);
				{
					colCneeBDMonth4 = new TableColumn(tblCneeBookingAnalysis,
							SWT.NONE);
					colCneeBDMonth4.setText(months[i]);
					colCneeBDMonth4.setWidth(80);

				}

			}
			for (int index = 0; index < tblCneeBookingAnalysis.getColumnCount(); index++) {
				tblCneeBookingAnalysis.getColumn(index).addListener(
						SWT.Selection, new sortListener());
			}
		}

	}

	private void createCnorBATable() {
		int[] indexs = lstCnorPickMonths.getSelectionIndices();
		String[] months = null;
		int len = indexs.length;
		if (len > 0) {
			months = new String[len];

			if (null != tblCnorBookingAnalysis)
				tblCnorBookingAnalysis.dispose();

			{
				tblCnorBookingAnalysis = new Table(cvsCnorBookingAnalysis,
						SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL
								| SWT.BORDER);

				tblCnorBookingAnalysis.setHeaderVisible(true);
				tblCnorBookingAnalysis.setLinesVisible(true);
				tblCnorBookingAnalysis.setBounds(30, 100, 710, 350);

			}
			{
				lblCBAOption = new Label(cvsCnorBookingAnalysis, SWT.NONE);
				lblCBAOption.setText("Select Option");
				lblCBAOption.setBounds(627, 13, 72, 22);
			}
			{
				colCnorBDsno = new TableColumn(tblCnorBookingAnalysis, SWT.NONE);
				colCnorBDsno.setText("S NO");
				colCnorBDsno.setWidth(40);

			}
			{
				colCnorBDBranchCode = new TableColumn(tblCnorBookingAnalysis,
						SWT.NONE);
				colCnorBDBranchCode.setText("Branch Code");
				colCnorBDBranchCode.setWidth(80);

			}
			{
				colCnorBDCnorName = new TableColumn(tblCnorBookingAnalysis,
						SWT.NONE);
				colCnorBDCnorName.setText("Cnor Name");
				colCnorBDCnorName.setWidth(100);

			}
			for (int i = 0; i < len; i++) {
				months[i] = lstCnorPickMonths.getItem(indexs[i]);
				{
					colCnorBDMonths = new TableColumn(tblCnorBookingAnalysis,
							SWT.NONE);
					colCnorBDMonths.setText(months[i]);
					colCnorBDMonths.setWidth(80);

				}

			}
			for (int index = 0; index < tblCnorBookingAnalysis.getColumnCount(); index++) {
				tblCnorBookingAnalysis.getColumn(index).addListener(
						SWT.Selection, new sortListener());
			}
		}

	}

	private void handleCancelLR(boolean refresh) {

		if (null != tblCancelledLR)
			tblCancelledLR.removeAll();

		try {
			int index = -1;

			index = cbCLReportType.getSelectionIndex();
			String selected = cbCLReportType.getItem(index);
			String Branch_code = null;
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date from_date = dateFormat.parse(txtCancelledLRfromdate.getText());
			Date to_date = dateFormat.parse(txtcancelledlrtodate.getText());
			if (selected.equalsIgnoreCase("Detailed")) {
				index = cbCLRBranch.getSelectionIndex();
				if (index > -1) {
					
					if (index != 0) {
						Branch_code = cbCLRBranch.getItem(index);
						index = Branch_code.indexOf("-");
						Branch_code = Branch_code.substring(index + 1).trim();
					} else {
						Branch_code = "All";
					}
				
				}
				index = cbStation.getSelectionIndex();
				if (index > -1) {
					String station_code = null;
					if (index != 0) {
						station_code = cbStation.getItem(index);
						index = station_code.indexOf("-");
						station_code = station_code.substring(index + 1).trim();
					} else {
						station_code = "All";
					}
					populateDetailedCancelledLRTable(from_date, to_date,
							Branch_code,station_code, selected, refresh);
				}

			} else {
				index = cbCLRBranch.getSelectionIndex();
				if (index > -1) {
					String branch_code = null;
					if (index != 0) {
						branch_code = cbCLRBranch.getItem(index);
						index = branch_code.indexOf("-");
						branch_code = branch_code.substring(index + 1).trim();
					} else {
						branch_code = "All";
					}
					populateSummaryCancelledLRTable(from_date, to_date,
							branch_code, selected, refresh);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @throws Exception
	 */
	private void populateStationForCLR() throws Exception {
		int index = cbCLRBranch.getSelectionIndex();
		String branch_code = null;
		if (index > -1) {
			
			branch_code = cbCLRBranch.getItem(index);
			index = branch_code.indexOf("-");
			branch_code = branch_code.substring(index + 1).trim();
			
			StationsDTO[] dto = handler.getAllStations();
			if (null != dto) {
				if (null != cbStation)
					cbStation.removeAll();
					cbStation.add("All");
				if(!(branch_code.equals("All"))){
					
					for (int i = 0; i < dto.length; i++) {
						if (branch_code == null
								|| dto[i].getBranch_code().equalsIgnoreCase(
										branch_code)) 
							cbStation
									.add(dto[i].getName() + " - " + dto[i].getId());
					}
				}
			}

		}

	}
	
	private void populateStationUndelivered_topay() throws Exception {
		int index = cbCLRBranch.getSelectionIndex();
		if (index > -1) {
			String branch_code = null;
			if (index > 0) {
				branch_code = cbCLRBranch.getItem(index);
				index = branch_code.indexOf("-");
				branch_code = branch_code.substring(index + 1).trim();
			}
			StationsDTO[] dto = handler.getAllStations();
			if (null != dto) {
				if (null != cbStation)
					cbStation.removeAll();
					cbStation.add("All");
				for (int i = 0; i < dto.length; i++) {
					if (branch_code == null
							|| dto[i].getBranch_code().equalsIgnoreCase(
									branch_code))
						cbStation
								.add(dto[i].getName() + " - " + dto[i].getId());
				}
			}

		}

	}

	/**
	 * 
	 * @param from_date
	 * @param to_date
	 * @param branch_code
	 */
	private void populateDetailedCancelledLRTable(Date from_date, Date to_date,String Branch_code,
			String station_code, String type, boolean refresh) {
		try {
			
			if (null != handler) {
				if (refresh)
					cancelledDetailedLRS = getCancelledLR(from_date,
							to_date, type,Branch_code,station_code);

				if (null != cancelledDetailedLRS) {
					boolean isAvail = false;
					int len = cancelledDetailedLRS.length;
					int j = 1;

					/* Total */
					int noa = 0;
					float art_value = 0;
					float bft = 0;
					float cc = 0;
					float iec = 0;
					float others = 0;
					float dd = 0;
					float total = 0;
					
					for (int i = 0; i < len; i++) {
						
						if ((Branch_code.equalsIgnoreCase("All"))&&(station_code.equalsIgnoreCase("All"))){
							isAvail = true;
							TableItem item = new TableItem(tblCancelledLR,
									SWT.NONE);
							item.setText(0, String.valueOf(j++));
							item
									.setText(1, String
											.valueOf(cancelledDetailedLRS[i]
													.getLrno()));
							item
									.setText(2, String
											.valueOf(cancelledDetailedLRS[i]
													.getDate()));
							item
									.setText(3, String
											.valueOf(cancelledDetailedLRS[i]
													.getType()));
							item
									.setText(4, String
											.valueOf(cancelledDetailedLRS[i]
													.getFrom()));
							item.setText(5, String
									.valueOf(cancelledDetailedLRS[i].getTo()));
							item.setText(6, String
									.valueOf(cancelledDetailedLRS[i]
											.getNo_of_articles()));
							noa = noa
									+ cancelledDetailedLRS[i]
											.getNo_of_articles();
							item.setText(7, getRoundedValue(cancelledDetailedLRS[i]
											.getArticle_value()));
							art_value = art_value
									+ cancelledDetailedLRS[i]
											.getArticle_value();
							item.setText(8, getRoundedValue(cancelledDetailedLRS[i].getBft()));
							bft = bft + cancelledDetailedLRS[i].getBft();
							item.setText(9, getRoundedValue(cancelledDetailedLRS[i].getCcc()));
							cc = cc + cancelledDetailedLRS[i].getCcc();
							item.setText(10, getRoundedValue(cancelledDetailedLRS[i].getIec()));
							iec = iec + cancelledDetailedLRS[i].getIec();
							item.setText(11, getRoundedValue(cancelledDetailedLRS[i]
											.getOther_charges()));
							others = others
									+ cancelledDetailedLRS[i]
											.getOther_charges();
							item.setText(12, getRoundedValue(cancelledDetailedLRS[i].getDdc()));
							dd = dd + cancelledDetailedLRS[i].getDdc();
							item.setText(13,
									getRoundedValue(cancelledDetailedLRS[i]
											.getTotal()));
							total = total + cancelledDetailedLRS[i].getTotal();
						}else if((Branch_code.equalsIgnoreCase(getBranch_code(cancelledDetailedLRS[i]
						                   										.getFrom())))&&(station_code.equalsIgnoreCase
						                   												("All"))){
							isAvail = true;
							TableItem item = new TableItem(tblCancelledLR,
									SWT.NONE);
							item.setText(0, String.valueOf(j++));
							item
									.setText(1, String
											.valueOf(cancelledDetailedLRS[i]
													.getLrno()));
							item
									.setText(2, String
											.valueOf(cancelledDetailedLRS[i]
													.getDate()));
							item
									.setText(3, String
											.valueOf(cancelledDetailedLRS[i]
													.getType()));
							item
									.setText(4, String
											.valueOf(cancelledDetailedLRS[i]
													.getFrom()));
							item.setText(5, String
									.valueOf(cancelledDetailedLRS[i].getTo()));
							item.setText(6, String
									.valueOf(cancelledDetailedLRS[i]
											.getNo_of_articles()));
							noa = noa
									+ cancelledDetailedLRS[i]
											.getNo_of_articles();
							item.setText(7, getRoundedValue(cancelledDetailedLRS[i]
											.getArticle_value()));
							art_value = art_value
									+ cancelledDetailedLRS[i]
											.getArticle_value();
							item.setText(8, getRoundedValue(cancelledDetailedLRS[i].getBft()));
							bft = bft + cancelledDetailedLRS[i].getBft();
							item.setText(9, getRoundedValue(cancelledDetailedLRS[i].getCcc()));
							cc = cc + cancelledDetailedLRS[i].getCcc();
							item.setText(10, getRoundedValue(cancelledDetailedLRS[i].getIec()));
							iec = iec + cancelledDetailedLRS[i].getIec();
							item.setText(11, getRoundedValue(cancelledDetailedLRS[i]
											.getOther_charges()));
							others = others
									+ cancelledDetailedLRS[i]
											.getOther_charges();
							item.setText(12, getRoundedValue(cancelledDetailedLRS[i].getDdc()));
							dd = dd + cancelledDetailedLRS[i].getDdc();
							item.setText(13,
									getRoundedValue(cancelledDetailedLRS[i]
											.getTotal()));
							total = total + cancelledDetailedLRS[i].getTotal();
						}
						else if ((station_code.equalsIgnoreCase(cancelledDetailedLRS[i].getFrom())) && (!Branch_code.equals(" "))){
							isAvail = true;
							TableItem item = new TableItem(tblCancelledLR,
									SWT.NONE);
							item.setText(0, String.valueOf(j++));
							item
									.setText(1, String
											.valueOf(cancelledDetailedLRS[i]
													.getLrno()));
							item
									.setText(2, String
											.valueOf(cancelledDetailedLRS[i]
													.getDate()));
							item
									.setText(3, String
											.valueOf(cancelledDetailedLRS[i]
													.getType()));
							item
									.setText(4, String
											.valueOf(cancelledDetailedLRS[i]
													.getFrom()));
							item.setText(5, String
									.valueOf(cancelledDetailedLRS[i].getTo()));
							item.setText(6, String
									.valueOf(cancelledDetailedLRS[i]
											.getNo_of_articles()));
							noa = noa
									+ cancelledDetailedLRS[i]
											.getNo_of_articles();
							item.setText(7, getRoundedValue(cancelledDetailedLRS[i]
											.getArticle_value()));
							art_value = art_value
									+ cancelledDetailedLRS[i]
											.getArticle_value();
							item.setText(8, getRoundedValue(cancelledDetailedLRS[i].getBft()));
							bft = bft + cancelledDetailedLRS[i].getBft();
							item.setText(9, getRoundedValue(cancelledDetailedLRS[i].getCcc()));
							cc = cc + cancelledDetailedLRS[i].getCcc();
							item.setText(10, getRoundedValue(cancelledDetailedLRS[i].getIec()));
							iec = iec + cancelledDetailedLRS[i].getIec();
							item.setText(11, getRoundedValue(cancelledDetailedLRS[i]
											.getOther_charges()));
							others = others
									+ cancelledDetailedLRS[i]
											.getOther_charges();
							item.setText(12, getRoundedValue(cancelledDetailedLRS[i].getDdc()));
							dd = dd + cancelledDetailedLRS[i].getDdc();
							item.setText(13,
									getRoundedValue(cancelledDetailedLRS[i]
											.getTotal()));
							total = total + cancelledDetailedLRS[i].getTotal();
						}

					}
					TableItem item = new TableItem(tblCancelledLR, SWT.NONE);
					Font font1 = new Font(Display.getDefault(), "Tahoma", 8, SWT.BOLD);
					item.setFont(font1);
					item.setText(1, String.valueOf("Total"));
					item.setText(6, String.valueOf(noa));
					item.setText(7, getRoundedValue(art_value));
					item.setText(8, getRoundedValue(bft));
					item.setText(9, getRoundedValue(cc));
					item.setText(10, getRoundedValue(iec));
					item.setText(11, getRoundedValue(others));
					item.setText(12, getRoundedValue(dd));
					item.setText(13, getRoundedValue(total));

					if (!isAvail) {
						// displayError("No Records Available");
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	private BookingDTO[] getCancelledLR(Date from_date, Date to_date,
			String type, String branch_code, String station_code) {
		
		//int monthDiff = 0;
		 //cancelledDetailedLRS = null;
		try {
			/*Date curDate = new Date();
			int m1 = from_date.getYear() * 12 + from_date.getMonth();
	        int m2 = curDate.getYear() * 12 + curDate.getMonth();
	        monthDiff = m2 - m1;*/
			Date date = BeanUtil.getThree_month_updated();
		if(from_date.before(date)){
			//System.out.println("in ddr histry-->"+date);
			cancelledDetailedLRS = handler.getCancelledLRHistory(from_date, to_date,type,branch_code,station_code);
		}else{
			cancelledDetailedLRS = handler.getCancelledLR(from_date, to_date,type,branch_code,station_code);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cancelledDetailedLRS;
	}
	
	private BookingDTO[] getCancelledLRsummary(Date from_date, Date to_date,
			String type, String branch_code) {
		
		//int monthDiff = 0;
		
		try {
			/*Date curDate = new Date();
			int m1 = from_date.getYear() * 12 + from_date.getMonth();
	        int m2 = curDate.getYear() * 12 + curDate.getMonth();
	        monthDiff = m2 - m1;*/
			Date date = BeanUtil.getThree_month_updated();
		if(from_date.before(date)){
			//System.out.println("in ddr histry-->"+date);
			cancelledSummaryLRS = handler.getCancelledLRHistory(from_date, to_date,type,branch_code,"%");
		}else{
			cancelledSummaryLRS = handler.getCancelledLR(from_date, to_date,type,branch_code,"%");
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cancelledSummaryLRS;
	}

	/**
	 * 
	 * @param from_date
	 * @param to_date
	 * @param branch_code
	 * @param type
	 * @param refresh
	 */
	private void populateSummaryCancelledLRTable(Date from_date, Date to_date,
			String branch_code, String type, boolean refresh) {
		try {
			
			float average =0;
			int count=0;
			
			if (null != handler) {
				populateDefaultTable(branch_code, tblCancelledLR, 6);
				if (refresh)
					cancelledSummaryLRS = getCancelledLRsummary(from_date,
							to_date, type,branch_code);

				if (null != cancelledSummaryLRS) {
					int len = cancelledSummaryLRS.length;

					int nolr = 0;
					float total = 0;
					float avgftTotal = 0;
					float avg = 0;
					for (int i = 0; i < len; i++) {
						String from = cancelledSummaryLRS[i].getFrom();
						if (null != branch_code
								&& (branch_code.equalsIgnoreCase("All") || branch_code
										.equalsIgnoreCase(getBranchCode(from)))) {

							TableItem item = getTableItem(tblCancelledLR, 2,from);
							if (item != null) {
								
								int noa = cancelledSummaryLRS[i].getNo_of_articles();
								float tot = cancelledSummaryLRS[i].getBft();
								
								item.setText(3, String.valueOf(noa));
								nolr = nolr	+ noa;
								
								item.setText(4, getRoundedValue(tot));
								total = total + tot;
								
								avgftTotal = tot / noa;
								item.setText(5,String.valueOf(getRoundedValue(avgftTotal)));
								avg = avg + avgftTotal;
								
							}
						}
						//count++;
					}
					
					average = avg / nolr;
					TableItem item = new TableItem(tblCancelledLR, SWT.NONE);
					item.setText(1, "Total");
					Font font1 = new Font(Display.getDefault(), "Tahoma", 8, SWT.BOLD);
					item.setFont(font1);
					item.setText(3, String.valueOf(nolr));
					item.setText(4, getRoundedValue(total));
					item.setText(5, getRoundedValue(avg));
					TableItem item1 = new TableItem(tblCancelledLR, SWT.NONE);
					item1.setFont(font1);
					item1.setText(4, "Average");
					item1.setText(5, getRoundedValue(average));

				}
			}
		} catch (Exception e) {

		}
	}

	private TableItem getTableItem(Table table, int index, String value) {
		if (null != table) {
			TableItem[] items = table.getItems();
			int item = items.length;
			for (int i = 0; i < item; i++) {
				if (items[i].getText(index).trim().equalsIgnoreCase(value))
					return items[i];
			}
		}
		return null;
	}

	private void populateDefaultTable(String branch, Table tblName, int cols)
			throws Exception {
		StationsDTO[] dto = null;
		if (null != tblCancelledLR && tblName != tblMarketVehicleUtilisation) {

			if (branch.equalsIgnoreCase("All") || branch.equalsIgnoreCase("%"))
				dto = handler.getAllStations();
			else
				dto = handler.getStations(branch);
			if (null != dto) {
				int len = dto.length;

				for (int i = 0; i < len; i++) {
					TableItem item = new TableItem(tblName, SWT.NONE);
					item.setText(0, String.valueOf(i + 1));
					item.setText(1, dto[i].getBranch_code());
					item.setText(2, dto[i].getId());
					for (int j = 3; j < cols; j++)
						item.setText(j, String.valueOf(0));

				}
			}

		} else if(tblName == tblMarketVehicleUtilisation && tblMarketVehicleUtilisation != null){
			dto = handler.getAllBranches();
			
				if(dto != null){
				int len = dto.length;
				for (int i = 0; i < len; i++) {
					TableItem item = new TableItem(tblName, SWT.NONE);
					item.setText(0, String.valueOf(i + 1));
					item.setText(1, dto[i].getBranch_code());
					int size = tblName.getColumnCount();
					for (int j = 2; j < size; j++) {
						item.setText(j, String.valueOf(0));
					}
				}			

				}
		} else {

			dto = handler.getAllBranches();
			int len = dto.length;
			for (int i = 0; i < len; i++) {
				TableItem item = new TableItem(tblName, SWT.NONE);
				item.setText(0, String.valueOf(i + 1));
				item.setText(1, dto[i].getBranch_code());
				int size = tblName.getColumnCount();
				for (int j = 2; j < size; j++) {
					item.setText(j, String.valueOf(0));
				}
			}

		}

	}

	/**
	 * 
	 * @param station_code
	 * @return
	 * @throws Exception
	 */
	private String getBranchCode(String station_code) throws Exception {
		StationsDTO[] dto = handler.getAllStations();
		if (null != dto) {
			for (int i = 0; i < dto.length; i++) {
				if (dto[i].getId().equalsIgnoreCase(station_code)) {
					return dto[i].getBranch_code();
				}
			}
		}
		return null;
	}

	private String getStationName(String stnCode) {
		int size = 0;
		try {
			StationsDTO[] stations = handler.getAllStations();
			size = stations.length;
			if (size > 0) {
				for (int i = 0; i < size; i++) {
					if (stations[i].getId().equals(stnCode)) {
						return stations[i].getName();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private void setDBADates(String date) {

		txtDBAFrom.setText(date);
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date sevenDay = null;
		Date secondDay = null;
		Date thirdDay = null;
		Date fourthDay = null;
		Date fifthDay = null;
		Date sixthDay = null;
		try {
			secondDay = dateFormat.parse(date);
			secondDay.setDate(secondDay.getDate() + 1);

			thirdDay = dateFormat.parse(date);
			thirdDay.setDate(thirdDay.getDate() + 2);

			fourthDay = dateFormat.parse(date);
			fourthDay.setDate(fourthDay.getDate() + 3);

			fifthDay = dateFormat.parse(date);
			fifthDay.setDate(fifthDay.getDate() + 4);

			sixthDay = dateFormat.parse(date);
			sixthDay.setDate(sixthDay.getDate() + 5);

			sevenDay = dateFormat.parse(date);
			sevenDay.setDate(sevenDay.getDate() + 6);

			txtDBATo.setText(dateFormat.format(sevenDay));

			colDBADate1.setText(date);
			colDBADate2.setText(dateFormat.format(secondDay));
			colDBADate3.setText(dateFormat.format(thirdDay));
			colDBADate4.setText(dateFormat.format(fourthDay));
			colDBADate5.setText(dateFormat.format(fifthDay));
			colDBADate6.setText(dateFormat.format(sixthDay));
			colDBADate7.setText(dateFormat.format(sevenDay));

		} catch (Exception e1) {
			e1.printStackTrace();
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
					len = len - 1; // Ignoring the last row as it is meant to be Total

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
								if (!items[j].getText(index).isEmpty()) {
									value2 = Integer.parseInt(items[j].getText(
											index).substring(1));
								}
								
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

				SimpleDateFormat parse = new SimpleDateFormat("yyyy-MM-dd");

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
							//System.out.println("date1--->"+date1);
							for (int j = 0; j < i; j++) {
								if (!items[j].getText(index).isEmpty()) {

									date2 = parse
											.parse(items[j].getText(index));
									//System.out.println("date2--->"+date2);
								}

								if ((date1 != null && date2 != null)
										&& (date1.before(date2))) {
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
											items[i].getText(18),
											};
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
		public void sortByFloat(int index, Table refTable) {

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

			String selection = tabReport.getSelection()[0].getText();
			TableColumn column = (TableColumn) e.widget;

			if (selection == DD_EXTRA) {
				if (column == colDDEsno) {
					sortByFloat(0, tblDDExtra);
				} else if (column == colDDELrNo) {
					sortByLrNo(1, tblDDExtra);
				} else if (column == colDDETo) {
					sortByString(2, tblDDExtra);
				}else if (column == colDDELrType) {
					sortByString(3, tblDDExtra);
				} else if (column == colDDEbft) {
					sortByFloat(4, tblDDExtra);
				} else if (column == colDDEDD) {
					sortByFloat(5, tblDDExtra);
				} else if (column == colDDEDDE) {
					sortByFloat(6, tblDDExtra);
				} else if (column == colDDEOTHERS) {
					sortByFloat(7, tblDDExtra);
				} else if (column == colDDELrTotal) {
					sortByFloat(8, tblDDExtra);
				} else if (column == colDDEPerTotal) {
					sortByFloat(9, tblDDExtra);
				}

			} else if (selection == RS) {
				if (column == colRSsno) {
					sortByFloat(0, tblRemittance);
				} else if (column == colRSfrom) {
					sortByString(1, tblRemittance);
				} else if (column == colRSto) {
					sortByString(2, tblRemittance);
				} else if (column == colRSLrNo) {
					sortByLrNo(3, tblRemittance);
				} else if (column == colRSLrdate) {
					sortByDate(4, tblRemittance);
				} else if (column == colRSType) {
					sortByString(5, tblRemittance);
				} else if (column == colDRSNo) {
					sortByString(6, tblRemittance);
				} else if (column == colDRSdate) {
					sortByDate(7, tblRemittance);
				} else if (column == colRSCRno) {
					sortByLrNo(8, tblRemittance);
				} else if (column == colRSCnor) {
					sortByString(9, tblRemittance);
				} else if (column == colRSCnee) {
					sortByString(10, tblRemittance);
				} else if (column == colRSamount) {
					sortByFloat(11, tblRemittance);
				} else if (column == colRSrecoveryDt) {
					sortByDate(12, tblRemittance);
				}

			} else if (selection == BPA) {
				if (column == colBPAsno) {
					sortByFloat(0, tblBPA);
				} else if (column == colBPABranch) {
					sortByString(1, tblBPA);
				} else if (column == colBPANolr) {
					sortByFloat(2, tblBPA);
				} else if (column == colBPANoa) {
					sortByFloat(3, tblBPA);
				} else if (column == colBPAActwt) {
					sortByFloat(4, tblBPA);
				} else if (column == colBPACrgwt) {
					sortByFloat(5, tblBPA);
				} else if (column == colBPABft) {
					sortByFloat(6, tblBPA);
				} else if (column == colBPALrc) {
					sortByFloat(7, tblBPA);
				} else if (column == colBPADhc) {
					sortByFloat(8, tblBPA);
				}else if (column == colBPACcc) {
					sortByFloat(9, tblBPA);
				}else if (column == colBPADcc) {
					sortByFloat(10, tblBPA);
				}else if (column == colBPADdc) {
					sortByFloat(11, tblBPA);
				}else if (column == colBPADdc) {
					sortByFloat(12, tblBPA);
				}
				else if (column == colBPADdc) {
					sortByFloat(11, tblBPA);
				}else if (column == colBPADdc) {
					sortByFloat(11, tblBPA);
				}else if (column == colBPADdc) {
					sortByFloat(11, tblBPA);
				}
				
				

			} else if (selection == CCC_SUMMARY_TAB) {
				if (column == colCCSsno) {
					sortByFloat(0, tblCCCSummary);
				} else if (column == colCCSBranchCode) {
					sortByString(1, tblCCCSummary);
				} else if (column == colCCSStationCode) {
					sortByString(2, tblCCCSummary);
				} else if (column == colCCSTotalPT) {
					sortByFloat(3, tblCCCSummary);
				} else if (column == colCCLRs) {
					sortByFloat(4, tblCCCSummary);
				} else if (column == colCCbftofTot) {
					sortByFloat(5, tblCCCSummary);
				} else if (column == colCCSBFTLr) {
					sortByFloat(6, tblCCCSummary);
				} else if (column == colCCSTotalCCC) {
					sortByFloat(7, tblCCCSummary);
				} else if (column == colCCperofCConTot) {
					sortByFloat(8, tblCCCSummary);
				} else if (column == colCCCOnBasFrt) {
					sortByFloat(9, tblCCCSummary);
				}

			} else if (selection == UT_TOPAY) {
				if (!cbUTReportType.getText().equalsIgnoreCase("Summary")) {
					if (column == colUTsno) {
						sortByFloat(0, tblUndeliveredTopayDet);
					} else if (column == colUTLrno) {
						sortByLrNo(1, tblUndeliveredTopayDet);
					} else if (column == colUTLrdate) {
						sortByDate(2, tblUndeliveredTopayDet);
					} else if (column == colUTFrom) {
						sortByString(3, tblUndeliveredTopayDet);
					} else if (column == colUTTo) {
						sortByString(4, tblUndeliveredTopayDet);
					} else if (column == colUTnoa) {
						sortByFloat(5, tblUndeliveredTopayDet);
					} else if (column == colUTActWt) {
						sortByFloat(6, tblUndeliveredTopayDet);
					} else if (column == colUTbft) {
						sortByFloat(7, tblUndeliveredTopayDet);
					} else if (column == colUTtotalFt) {
						sortByFloat(8, tblUndeliveredTopayDet);
					} else if (column == colUTCurStatus) {
						sortByString(9, tblUndeliveredTopayDet);
					}
				} else {
					if (column == colTUTsno) {
						sortByFloat(0, tblUndeliveredTopay);
					} else if (column == colTUTBranchCode) {
						sortByString(1, tblUndeliveredTopay);
					} else if (column == colTUTStationCode) {
						sortByString(2, tblUndeliveredTopay);
					} else if (column == colTUTBoookedNoOfLRs) {
						sortByFloat(3, tblUndeliveredTopay);
					} else if (column == colTUTBoookedTotFrt) {
						sortByFloat(4, tblUndeliveredTopay);
					} else if (column == colTUTDeliverNoOfLRs) {
						sortByFloat(5, tblUndeliveredTopay);
					} else if (column == colTUTDeliverTotFrt) {
						sortByFloat(6, tblUndeliveredTopay);
					}
				}

			} else if (selection == DAILY_BOOKING_TAB) {
				if (column == colDBAsno) {
					sortByFloat(0, tblDailyBookingAnalysis);
				} else if (column == colDBABranchCode) {
					sortByString(1, tblDailyBookingAnalysis);
				} else if (column == colDBAStationCode) {
					sortByString(2, tblDailyBookingAnalysis);
				} else if (column == colDBADate1) {
					sortByFloat(3, tblDailyBookingAnalysis);
				} else if (column == colDBADate2) {
					sortByFloat(4, tblDailyBookingAnalysis);
				} else if (column == colDBADate3) {
					sortByFloat(5, tblDailyBookingAnalysis);
				} else if (column == colDBADate4) {
					sortByFloat(6, tblDailyBookingAnalysis);
				} else if (column == colDBADate5) {
					sortByFloat(7, tblDailyBookingAnalysis);
				} else if (column == colDBADate6) {
					sortByFloat(8, tblDailyBookingAnalysis);
				} else if (column == colDBADate7) {
					sortByFloat(9, tblDailyBookingAnalysis);
				}
				else if (column == colDBATotal) {
					sortByFloat(10, tblDailyBookingAnalysis);
				}
			} else if (selection == COUNTER_REPORT) {
				if (column == colCRsno) {
					sortByFloat(0, tblCounterReport);
				} else if (column == colCRBranchCode) {
					sortByString(1, tblCounterReport);
				} else if (column == colCRStationCode) {
					sortByString(2, tblCounterReport);
				} else if (column == colCRC1) {
					sortByFloat(3, tblCounterReport);
				} else if (column == colCRC2) {
					sortByFloat(4, tblCounterReport);
				} else if (column == colCRC3) {
					sortByFloat(5, tblCounterReport);
				} else if (column == colCRC4) {
					sortByFloat(6, tblCounterReport);
				} else if (column == colCRC5) {
					sortByFloat(7, tblCounterReport);
				} else if (column == colCRC6) {
					sortByFloat(8, tblCounterReport);
				} else if (column == colCRC7) {
					sortByFloat(9, tblCounterReport);
				} else if (column == colCRC8) {
					sortByFloat(10, tblCounterReport);
				}
			} else if (selection == SUNDRY_BOOKING_TAB) {
				if (column == colSBASNo) {
					sortByFloat(0, tblSundryBookAnalysis);
				} else if (column == colBranchCode) {
					sortByString(1, tblSundryBookAnalysis);
				} else if (column == colStationCode) {
					sortByString(2, tblSundryBookAnalysis);
				} else if (column == colTotalLr) {
					sortByFloat(3, tblSundryBookAnalysis);
				} else if (column == colTotalSundryLrs) {
					sortByFloat(4, tblSundryBookAnalysis);
				} else if (column == colTotalLrFreight) {
					sortByFloat(5, tblSundryBookAnalysis);
				} else if (column == colTotalSundryFreight) {
					sortByFloat(6, tblSundryBookAnalysis);
				} else if (column == colTotalSundryBft) {
					sortByFloat(7, tblSundryBookAnalysis);
				} else if (column == colTotalSundryNoa) {
					sortByFloat(8, tblSundryBookAnalysis);
				} else if (column == colTotalSundryAwt) {
					sortByFloat(9, tblSundryBookAnalysis);
				} else if (column == colTotalSundryCrgWt) {
					sortByFloat(10, tblSundryBookAnalysis);
				} else if (column == colOnTurnOver) {
					sortByFloat(11, tblSundryBookAnalysis);
				}
			} else if (selection == UPD_READY) {
				if (column == colUpdSno) {
					sortByFloat(0, tblUPDReady);
				} else if (column == colUPDRBranchCode) {
					sortByString(1, tblUPDReady);
				} else if (column == colUPDRStationCode) {
					sortByString(2, tblUPDReady);
				} else if (column == colUPDRLrNo) {
					sortByLrNo(3, tblUPDReady);
				} else if (column == colUPDRLrDate) {
					sortByDate(4, tblUPDReady);
				} else if (column == colUPDRLrType) {
					sortByString(5, tblUPDReady);
				} else if (column == colUPDRTotalFreight) {
					sortByFloat(6, tblUPDReady);
				} else if (column == colUPDRInwardDays) {
					sortByFloat(7, tblUPDReady);
				} else if (column == colUPDRCnor) {
					sortByString(8, tblUPDReady);
				} else if (column == colUPDCnorPhone) {
					sortByString(9, tblUPDReady);
				} else if (column == colUPDRCnee) {
					sortByString(10, tblUPDReady);
				} else if (column == colUPDCneePhone) {
					sortByString(11, tblUPDReady);
				}
			}	
			else if (selection == MISSING_CUSTOMER) {
				if (column == colMCsno) {
					sortByFloat(0, tblMissingCustomer);
				} else if (column == colMissCustCustomerName) {
					sortByString(1, tblMissingCustomer);
				} else if (column == colMissCustHighTotalFreight) {
					sortByFloat(2, tblMissingCustomer);
				} else if (column == colMissCustHighMonth) {
					sortByString(3, tblMissingCustomer);
				} else if (column == colMissCustLowTotalFreight) {
					sortByFloat(4, tblMissingCustomer);
				} else if (column == colMissCustLowMonth) {
					sortByString(5, tblMissingCustomer);
				} else if (column == colMissCustLastMonth) {
					sortByFloat(6, tblMissingCustomer);
				} else if (column == colMissCustCurrentMonth) {
					sortByFloat(7, tblMissingCustomer);
				}
			} else if (selection == MARKET_VEHICLE) {
				TableColumn[] cols = tblMarketVehicleUtilisation.getColumns();
				int len = cols.length;
				for (int i = 0; i < len; i++) {

					if (i != 1 && column == cols[i]) {
						sortByFloat(i, tblMarketVehicleUtilisation);
					} else if (i == 1 && column == cols[i]) {
						sortByString(i, tblMarketVehicleUtilisation);
					}
				}

			} else if (selection == FOC) {
				TableColumn[] cols = tblFOCLr.getColumns();
				int len = cols.length;
				int index = cbFOCLrReportType.getSelectionIndex();
				for (int i = 0; i < len; i++) {
					if (column == cols[i] && index == 0) {
						if (i == 1) {
							sortByLrNo(i, tblFOCLr);
						} else if (i == 2) {
							sortByDate(i, tblFOCLr);
						} else if (i > 2 && i < 5) {
							sortByString(i, tblFOCLr);
						} else if (i > 4 || i == 0) {
							sortByFloat(i, tblFOCLr);
						}
					} else if (column == cols[i] && index == 1) {
						if (i == 1 || i == 2) {
							sortByString(i, tblFOCLr);
						} else if (i > 2 || i == 0) {
							sortByFloat(i, tblFOCLr);
						}
					}
				}

			} else if (selection == OPENLR) {
				TableColumn[] cols = tblOpenLr.getColumns();
				int len = cols.length;

				for (int i = 0; i < len; i++) {
					if (column == cols[i]) {
						if (i == 4) {
							sortByLrNo(i, tblOpenLr);
						} else if (i == 5) {
							sortByDate(i, tblOpenLr);
						} else if ((i > 0 && i < 4) || (i == 6) || (i == 15)
								|| (i == 16)) {
							sortByString(i, tblOpenLr);
						} else if ((i > 6 && i < 15) || i == 0) {
							sortByFloat(i, tblOpenLr);
						}
					}

				}

			} else if (selection == CONSINORBA) {
				TableColumn[] cols = tblCnorBookingAnalysis.getColumns();
				int len = cols.length;

				for (int i = 0; i < len; i++) {
					if (column == cols[i]) {
						if (i == 1 || i == 2) {
							sortByString(i, tblCnorBookingAnalysis);
						} else if (i == 0 || i > 2) {
							sortByFloat(i, tblCnorBookingAnalysis);
						}
					}

				}

			} else if (selection == CONSINEEBA) {
				TableColumn[] cols = tblCneeBookingAnalysis.getColumns();
				int len = cols.length;

				for (int i = 0; i < len; i++) {
					if (column == cols[i]) {
						if (i == 1 || i == 2) {
							sortByString(i, tblCneeBookingAnalysis);
						} else if (i == 0 || i > 2) {
							sortByFloat(i, tblCneeBookingAnalysis);
						}
					}

				}

			} else if (selection == CANCELLEDLR) {
				TableColumn[] cols = tblCancelledLR.getColumns();
				int len = cols.length;
				int index = cbCLReportType.getSelectionIndex();
				for (int i = 0; i < len; i++) {
					if (column == cols[i] && index == 0) {
						if (i == 2 || i == 1) {
							sortByString(i, tblCancelledLR);
						} else if (i > 2 || i == 0) {
							sortByFloat(i, tblCancelledLR);
						}
					} else if (column == cols[i] && index == 1) {
						if (i == 3 || i == 4 || i == 5) {
							sortByString(i, tblCancelledLR);
						} else if (i > 5 || i == 0) {
							sortByFloat(i, tblCancelledLR);
						} else if (i == 1) {
							sortByLrNo(i, tblCancelledLR);
						} else if (i == 2) {
							sortByDate(i, tblCancelledLR);
						}
					}
				}

			}else if (selection == Service_Tax) {
				TableColumn[] cols = tblServicetax.getColumns();
				int len = cols.length;

				for (int i = 0; i < len; i++) {
					if (column == cols[i]) {
						 if  (i == 1) {
							sortByString(i, tblServicetax);
						} else if (i > 1 || i == 0) {
							sortByFloat(i, tblServicetax);
						}
					}

				}

			}else if (selection == Service_Tax_LR) {
				TableColumn[] cols = tblServicetaxLR.getColumns();
				int len = cols.length;

				for (int i = 0; i < len; i++) {
					if (column == cols[i]) {
						 if  (i == 1) {
							 sortByLrNo(i, tblServicetaxLR);
						} else if (i > 6 || i == 0) {
							sortByFloat(i, tblServicetaxLR);
						} else if (i == 2) {
							sortByDate(i, tblServicetaxLR);
						}else if (i > 2 && i < 7) {
							sortByString(i, tblServicetaxLR);
						}
					}

				}

			}
			
			
		}
	}

}


