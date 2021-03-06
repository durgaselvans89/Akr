package hm.akr.container.lr;

import hm.akr.common.AlphaNumericValidation;
import hm.akr.common.BeanUtil;
import hm.akr.common.FloatExtendedValidation;
import hm.akr.common.FloatLimitValidation;
import hm.akr.common.FloatValidation;
import hm.akr.common.NumericExtendedValidation;
import hm.akr.common.NumericValidation;
import hm.akr.common.SWTResourceManager;
import hm.akr.container.customer.CustomerComposite;
import hm.akr.container.customer.CustomerCompositeHandler;
import hm.akr.dto.ActivityLog;
import hm.akr.dto.ArticleDTO;
import hm.akr.dto.ArticleUIDTO;
import hm.akr.dto.BookingDTO;
import hm.akr.dto.CCProfileDTO;
import hm.akr.dto.CardRateDTO;
import hm.akr.dto.CardRateProfileDTO;
import hm.akr.dto.ClientQuotationDTO;
import hm.akr.dto.ClientQuotationDetailsDTO;
import hm.akr.dto.CustomerDTO;
import hm.akr.dto.DistanceListDTO;
import hm.akr.dto.GMRDTO;
import hm.akr.dto.InsuranceDTO;
import hm.akr.dto.LRNumberRangeDTO;
import hm.akr.dto.LRTrackDTO;
import hm.akr.dto.RegularSundryDTO;
import hm.akr.dto.SMSDTO;
import hm.akr.dto.SpecialSundryDTO;
import hm.akr.dto.StationsDTO;
import hm.akr.exceptions.BusinessException;
import hm.akr.util.ContactsManager;

import java.io.InputStream;
import java.text.Collator;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.TreeSet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
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
 * LRComposite class
 * 
 * 
 * 
 * 
 * @version 1.0
 */
public class LRComposite extends Composite {

	{
		SWTResourceManager.registerResourceUser(this);
	}

	{
		// Register as a resource user - SWTResourceManager will
		// handle the obtaining and disposing of resources
		// SWTResourceManager.registerResourceUser(this);
	}

	LRCompositeHandler handler = null;

	private Text txtTotal;

	private Text txtStax;

	private Text txtGsc;

	private Text txtLc;

	private Text txtOldFrt;

	private Text txtPost;

	private Text txtDemu;

	private Text txtIec;

	private Text txtDcc;

	private Text txtCcc;

	private Text txtLrc;

	private Text txtBft;

	private Label label140;

	private Label label138;

	private Label label137;

	private Label label136;

	private Label label23;

	private Label lblOldFrt;

	private Label lblPost;

	private Label lblDemu;

	private Label label22;

	private Label label21;

	private Label label20;

	private Label lblDdc;

	private Label label19;

	private float actualBft;

	private Label lblTopay;

	boolean isNewRecord = true;

	String cancelType = null;

	private Label label16;

	private Label label13;

	private Label label12;

	private Label label11;

	private Label label10;

	private Label label9;

	private Label label8;

	private Label label6;

	private Label label7;

	private Label label4;

	private Label label3;

	private Canvas canvas;

	private TabItem[] tabItem;

	int booking_type = 0;

	private TabFolder lrTab;

	private Text txtFrom;

	private Text txtLrno;

	private Combo cbNewLrno;

	private Label lblNewlrno;

	private Label lblOldlrno;

	private Label lblweight;

	private Label lblnoofariticles;

	private Label lbldestination;

	private Label lblsource;

	private Label lblstatus;

	private Text lblobookeddate;

	private Text lbloweight;

	private Text txtOldlrno;

	private Text lblonoofarticles;

	private Text lblodestination;

	private Text lblosource;

	private boolean isDeliveryRunning = false;

	DecimalFormat decimalFormat = new DecimalFormat("0.00");

	private Text lblostatus;

	private Label lblDeliveryDate;

	private Label lblBookedDate;

	private Button btnGo;

	private Text txtTrackNo;

	private Label lblLrNoTrack;

	private TableColumn tableColumn1;

	private TableColumn tblcolDate;

	private Table tbllrtrack;

	private Canvas canvas2;

	private Combo cbTo;

	private Text txtDate;

	private Text txtDdc;

	private Text txtConsignorCST;

	private Button btnSubmit;

	private Button btnView;

	private Button btnPrint;

	private Label lblName;

	private Button btnCancel;

	private Label label5;

	private Button btnNew;

	private Text txtConsignor;

	private Text txtConsignee;

	public Color redColor = null;

	BookingDTO booking = null;

	LRTrackDTO[] track = null;

	CustomerDTO consignor = null;

	private String printarticles = null;

	private String printarticles_desc = null;

	ArrayList<ArticleUIDTO> artlist = null;

	ClientQuotationDTO quotationAllDetails = null;
	private Label lblConsigneePhone;
	private Text txtCneeLandline;
	private Label lblCneeLandline;
	private Text txtCnorLandline;
	private Label lblCnorLandline;
	private Label lblUserInfo;
	private Button btnWeightRoundOff;
	private Text txtConsigneePhone;
	private Button btnFOCLr;

	private Button btnopenlr;

	private Button btnToUPD;

	private Button btnRebooklr;

	ArticleDTO[] articles = null;

	String quotationID = null;

	private boolean isCommodity = false;

	private int roundwt = 0;

	private String SERVER_DATE = null;

	private String[] comboElemrnt;

	String EMPTY_STRING = "";

	private Shell shell;

	private Button btnStax;

	private Group group1;

	private Combo cbdc;

	private Combo cbic;

	private Label lblic;

	private Label lbldc;

	private Button btnRate;

	private TableColumn coldelete;

	private Button btnArtAdd;

	private Group gpArticle;

	private Group gpupd;

	private Label lblConsinortinno;

	private Combo cbUnit;

	private Label lblUnit;

	private Label lblDimensions;

	private Label label14;

	private Label label2;

	private Label label1;

	private TableColumn colDescription;

	private TableColumn colChargedWeight;

	private TableColumn colHeight;

	private TableColumn colBreadth;

	private TableColumn colDimensions1;

	private TableColumn colActualWeight;

	private TableColumn colArticleValue;

	private TableColumn colNoofArticle;

	private TableColumn colArticleName;

	private Table tblArticle;

	private Button btnSpecial;

	private Label lblCancel;

	private Label lblDeliver;

	private Combo cbConsigneeName;

	private Combo cbConsignorName;

	private Text lblodeliverydate;

	private Button btnSaveConsigneeDetails;

	private Button btnSaveConsignorDetails;

	public static Display myDisplay;

	String[] allConsignors = null;

	String[] allConsignee = null;

	ArrayList<String> tempList = null;

	// static LRNumberRangeDTO[] lrrangeDTO = null;
	LRNumberRangeDTO[] lrrangeDTO = null;

	int count = 0;

	private String[] lrType = { "TOPAY", "PAID", "BILLING", "UPD", "CARD RATE",
			"LR VIEW", "LR CANCEL", "LR TRACK", "STATIONARY", "CUSTOMER", "CFT" };

	// private Composite shell;

	ContactsManager contacts = null;

	private Canvas canvas3;

	private Canvas canvas4;

	private Table tblStationary;

	private TableColumn tblcolSn;

	private TableColumn tableColTopay;

	private TableColumn tableColPaid;

	private TableColumn tableColBilling;

	private TableColumn tableColCR;

	private Text txtConsigneeCST;

	private Image img = null;

	private Label lblBFT;

	private Text txtTrackBFT;

	private Label lblLRType;

	private Text txtTrackLRType;

	private int rate_type = 0;

	private float ddcfree = 0;

	private Canvas canvas5;

	private Button btnCFTInch;

	private Button btnCFTCm;

	private Button btnCFTFt;

	private Label lblCFTLength;

	private Label lblCFTBreath;

	private Label lblCFTHeight;

	private Text txtCFTLength;

	private Text txtCFTBreath;

	private Text txtCFTHeight;

	private Button btnCFTResult;

	private Label lblCFTResult;

	private Group gpMeasurements;

	private Canvas canCardRate;
	private Table tblcardRate;
	private TableColumn colCardRateSno;
	private TableColumn colCardRateStationName;
	private Text txtConsignorPhone;
	private Label lblConsignorPhone;
	private Text txteffchargedCWT;
	private Label lbleffectivecwt;
	private Button btnccc;
	private Text txtmaxccc;
	private Label lblmaxccc;
	private Button btnCardRatePrint;
	private TableColumn colCardRateBpi;
	private TableColumn colCardRateDistance;
	private TableColumn colCardRateCR;
	private Label lblCardRateStation;
	private Text txtCardRateFromStation;

	private int isViewrateType = 0;

	boolean isQST = false;

	private static final String DEL_IMG = "hm/akr/resources/delete.PNG";

	private static final String ADD_IMG = "hm/akr/resources/add.PNG";

	private CustomerCompositeHandler customerHandler = null;

	DistanceListDTO[] distanceDTO = null;

	public float singlrArt = 0;

	boolean isValidLRNO = false;

	boolean isReadyToSubmit = false;

	boolean isWeightBased = false;

	int quotationType = -1;

	private Label lblTrackCnor;

	private Text txtTrackCnor;

	private Label lblTrackCnee;

	private Text txtTrackCnee;

	private Label lblTrackDdc;

	private Text txtTrack;

	private Text txtTrackDdc;

	boolean isMixedCommodity = false;

	float mixedCommodityValue = 0;

	boolean isCCCommodity = false;

	private Text txtCnorSMS;

	private Text txtCneeSMS;

	private Label lblDhc;

	private Text txtDhc;

	private float dhcPercent = 0;
	BookingDTO[] unUsedList = null;
	private String customerType = "sundry";

	/**
	 * Constructor method
	 * 
	 * @param shell
	 */

	public LRComposite(Shell shell, int swtValue) throws Exception {
		super(shell, swtValue);

		redColor = shell.getDisplay().getSystemColor(SWT.COLOR_RED);
		this.shell = shell;
		try {
			handler = new LRCompositeHandler();
			// lrrangeDTO = handler.getAllocatedLrnumbers(false);
			unUsedList = handler.getUnUsedLR();
			System.out.println("unUsedList--->" + unUsedList.length);
			SERVER_DATE = handler.getServerDate();
			customerHandler = new CustomerCompositeHandler();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	private void deInitializeState() {
		// Method to freeing all used Objects especially for String Object
		allConsignee = null;
		allConsignors = null;
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	private float getNormalRounded2Decimal(float value) {
		value = value * 100;
		value = (float) Math.round(value);
		value = value / 100;

		return value;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public Composite loadComposite() throws Exception {

		try {

			{
				this.setSize(630, 378);
			}
			this.setLayout(new FormLayout());
			this.setSize(1024, 752);

			{
				lblName = new Label(this, SWT.NONE);
				FormData lblNameLData = new FormData();
				lblNameLData.width = 44;
				lblNameLData.height = 32;
				lblNameLData.left = new FormAttachment(0, 1000, 920);
				lblNameLData.top = new FormAttachment(0, 1000, 20);
				lblName.setLayoutData(lblNameLData);
				lblName.setText("LR");
				lblName.setFont(SWTResourceManager.getFont("Tahoma", 20, 1,
						false, false));
			}

			{
				btnNew = new Button(this, SWT.PUSH | SWT.CENTER);
				FormData btnNewLData = new FormData();
				btnNewLData.width = 75;
				btnNewLData.height = 24;
				btnNewLData.left = new FormAttachment(0, 1000, 925);
				btnNewLData.top = new FormAttachment(0, 1000, 99);
				btnNew.setLayoutData(btnNewLData);
				btnNew.setText("New");
				btnNew.addSelectionListener(new submitAction());
			}

			{

				lrTab = new TabFolder(this, SWT.NONE);
				FormData lrTabLayout = new FormData();
				lrTabLayout.width = 953;
				lrTabLayout.height = 557;
				lrTabLayout.left = new FormAttachment(0, 1000, 46);
				lrTabLayout.top = new FormAttachment(0, 1000, 128);
				lrTab.setLayoutData(lrTabLayout);

				int len = lrType.length;
				tabItem = new TabItem[len];
				for (int i = 0; i < len; i++) {
					tabItem[i] = new TabItem(lrTab, SWT.NONE);
					tabItem[i].setText(lrType[i]);
				}

				if (BeanUtil.getInstance().isBranch()) {
					if (BeanUtil.getDbName() != null
							&& !BeanUtil.getDbName().equals("")) {
						canvas = getCanvas(lrType[5]);
						canvas.setSize(968, 559);
						btnView.setVisible(true);
						btnSaveConsigneeDetails.setVisible(false);
						btnSaveConsignorDetails.setVisible(false);
						tabItem[5].setControl(canvas);
						lrTab.setSelection(5);
					} else {
						canvas = getCanvas(lrType[3]);
						canvas.setSize(968, 559);
						tabItem[3].setControl(canvas);
						lrTab.setSelection(3);
					}

				} else {
					if (BeanUtil.getDbName() != null
							&& !BeanUtil.getDbName().equals("")) {
						canvas = getCanvas(lrType[5]);
						canvas.setSize(968, 559);
						btnView.setVisible(true);
						btnSaveConsigneeDetails.setVisible(false);
						btnSaveConsignorDetails.setVisible(false);
						tabItem[5].setControl(canvas);
						lrTab.setSelection(5);
					} else {
						canvas = getCanvas(lrType[0]);
						canvas.setSize(968, 559);
						tabItem[0].setControl(canvas);
						lrTab.setSelection(0);
					}
				}
				artlist = new ArrayList<ArticleUIDTO>();
				lrTab.addSelectionListener(new SelectionAdapter() {

					private Canvas canvas7;

					public void widgetSelected(
							org.eclipse.swt.events.SelectionEvent event) {
						try {
							String selectedTab = lrTab.getSelection()[0]
									.getText();
							artlist = new ArrayList<ArticleUIDTO>();
							if (null != canvas) {
								canvas.dispose();
								canvas = null;
							}

							if (!BeanUtil.getInstance().isBranch()
									|| (!(lrType[2].equals(selectedTab)
											|| lrType[1].equals(selectedTab) || lrType[0]
											.equals(selectedTab)))) {
								canvas = getCanvas(selectedTab);
								int index = lrTab.getSelectionIndex();
								tabItem[index].setControl(canvas);
							}

							if (lrType[2].equals(selectedTab)
									&& !BeanUtil.getInstance().isBranch()) {

								cbConsigneeName.removeAll();
								cbConsignorName.removeAll();

								populateConsignorNames();
								populateConsigneeNames();

								txtConsignee.setEnabled(true);
								txtConsignor.setEnabled(true);

								btnSaveConsigneeDetails.setVisible(true);
								btnSaveConsignorDetails.setVisible(true);

								// btnopenlr.setVisible(false);
								btnSpecial.setVisible(false);
								cbic.setVisible(false);
								cbdc.setVisible(false);
								lblic.setVisible(false);
								lbldc.setVisible(false);
								// btnStax.setVisible(false);
								txtmaxccc.setVisible(false);
								lblmaxccc.setVisible(false);
							} else if (lrType[3].equals(selectedTab)) {
								populateTopayStationary();
								// lrrangeDTO =
								// handler.getCRRange(handler.getStationCode());

							} else if (lrType[5].equals(selectedTab)) {
								btnView.setVisible(true);
								btnSubmit.setVisible(false);
								btnSaveConsigneeDetails.setVisible(false);
								btnSaveConsignorDetails.setVisible(false);
							} else if (lrType[6].equals(selectedTab)) {
								btnCancel.setVisible(true);
								btnSubmit.setVisible(false);
								btnPrint.setVisible(false);
								btnView.setVisible(true);
								btnSaveConsigneeDetails.setVisible(false);
								btnSaveConsignorDetails.setVisible(false);
							} else if (lrType[7].equals(selectedTab)) {
								canvas2 = getCanvas();
								int index1 = lrTab.getSelectionIndex();
								tabItem[index1].setControl(canvas2);

							} else if (lrType[8].equals(selectedTab)) {
								canvas3 = getStationaryCanvas();
								int index1 = lrTab.getSelectionIndex();
								tabItem[index1].setControl(canvas3);
							} else if (lrType[9].equals(selectedTab)) {

								canvas4 = getCustomerCanvas();
								int index1 = lrTab.getSelectionIndex();
								tabItem[index1].setControl(canvas4);

							} else if (lrType[10].equals(selectedTab)) {
								canvas4 = getCFTCanvas();
								int index1 = lrTab.getSelectionIndex();
								tabItem[index1].setControl(canvas4);

							} else if (lrType[4].equals(selectedTab)) {

								canvas7 = getCardRateCanvas();
								int index1 = lrTab.getSelectionIndex();
								tabItem[index1].setControl(canvas7);

								populateCardRateReport();

							}

							if (BeanUtil.getDbName() != null
									&& !BeanUtil.getDbName().equals("")) {
								if (lrType[5].equals(selectedTab)) {
									btnView.setVisible(true);
									btnSubmit.setVisible(false);
									btnSaveConsigneeDetails.setVisible(false);
									btnSaveConsignorDetails.setVisible(false);
								} else if (lrType[7].equals(selectedTab)) {
									canvas2 = getCanvas();
									int index1 = lrTab.getSelectionIndex();
									tabItem[index1].setControl(canvas2);
								} else {
									// System.out.println("disabled-------->"+BeanUtil.getDbName()+"-->");
									if (canvas != null)
										canvas.dispose();
									if (canvas3 != null)
										canvas3.dispose();
									if (canvas4 != null)
										canvas4.dispose();
									if (canvas7 != null)
										canvas7.dispose();
								}

							}

						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				});

			}

			this.layout();
		} catch (Exception e) {
			throw e;
		}

		return this;
	}

	/**
	 * 
	 */
	private void populateCardRateReport() {
		try {
			if (tblcardRate != null) {
				tblcardRate.removeAll();
				distanceDTO = handler.getDistanceList(handler.getStationCode());

				if (null != distanceDTO) {
					populateCardRateRecods(distanceDTO);
				} else {
					displayError("No Records Available");
				}
			}
		} catch (Exception e) {
			displayError("Error occured While Processing");
		}

	}

	/**
	 * 
	 * @param dto
	 */
	private void populateCardRateRecods(DistanceListDTO[] dto) throws Exception {

		try {

			float finalRate = 0;
			float cardRate = 0;
			String strCardRate = "";
			int distance = 0;
			String stnCode = "";
			int j = 1;
			RegularSundryDTO regulerdto = handler.getSundryDetails(handler
					.getStationCode());

			for (int i = 0; i < dto.length; i++) {
				stnCode = dto[i].getDestStation();
				distance = dto[i].getDistance();
				cardRate = dto[i].getCardRate();

				// CITY
				/*
				 * if (checkCityStation(handler.getStationCode())
				 * .equalsIgnoreCase(
				 * checkCityStation(dto[i].getDestStation()))) {
				 * if(regulerdto.getDistance() > distance){ distance =
				 * regulerdto.getDistance(); //cardRate = distance *
				 * dto[i].getCardRate(); } }
				 */

				TableItem item = new TableItem(tblcardRate, SWT.NONE);
				item.setText(0, String.valueOf(j++));
				item.setText(1, getStationName(stnCode));
				item.setText(2, String.valueOf(stnCode));
				item.setText(3, String.valueOf(distance));

				strCardRate = getRoundedCeilValue(cardRate);
				cardRate = Float.parseFloat(strCardRate);
				finalRate = (cardRate * dto[i].getIncrementer() / 100)
						+ cardRate;
				item.setText(4, getRoundedCeilValue(finalRate));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getStationName(String stnCode) {
		int size = 0;
		try {
			StationsDTO[] stations = handler.getStations();
			size = stations.length;
			if (size > 0) {
				for (int i = 0; i < size; i++) {
					if (stations[i].getId().equalsIgnoreCase(stnCode)) {
						return stations[i].getName();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	/**
	 * 
	 * @param stationCode
	 * @return
	 */
	private String checkCityStation(String stationCode) {
		String station = null;
		try {
			StationsDTO[] stationsDTO = handler.getStations();
			if (null != stationsDTO) {
				for (int i = 0; i < stationsDTO.length; i++) {
					if (stationsDTO[i].getId().equalsIgnoreCase(stationCode)
							&& stationsDTO[i].getType()
									.equalsIgnoreCase("City")) {
						station = stationsDTO[i].getBranch_code();
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (station == null) {
			station = stationCode;
		}

		return station;
	}

	/**
	 * 
	 * @param cardRate
	 * @return
	 */
	private String getRoundedCeilValue(float cardRate) {
		// cardRate = ((float) (int) (cardRate * 1000)) / (float) 1000;

		cardRate = ((float) (int) (cardRate * 100000)) / (float) 100000;
		// System.out.println("cr==>"+cardRate);
		cardRate = cardRate * 100;
		cardRate = (float) Math.ceil(cardRate);
		cardRate = cardRate / 100;
		// System.out.println("cr after==>"+cardRate);

		return decimalFormat.format(cardRate);
	}

	/**
	 * 
	 * @return
	 */
	protected Canvas getCFTCanvas() {

		{
			canvas5 = new Canvas(lrTab, SWT.NONE);
			canvas5.setSize(998, 696);
			{
				btnCFTInch = new Button(canvas5, SWT.RADIO | SWT.LEFT);
				btnCFTInch.setText("Inch");
				btnCFTInch.setBounds(250, 83, 42, 30);
			}
			{
				btnCFTCm = new Button(canvas5, SWT.RADIO | SWT.LEFT);
				btnCFTCm.setText("Cm");
				btnCFTCm.setBounds(316, 83, 35, 30);
			}
			{
				btnCFTFt = new Button(canvas5, SWT.RADIO | SWT.LEFT);
				btnCFTFt.setText("Ft");
				btnCFTFt.setBounds(375, 83, 29, 30);
			}
			{
				lblCFTLength = new Label(canvas5, SWT.NONE);
				lblCFTLength.setText("Length");
				lblCFTLength.setBounds(250, 148, 42, 25);
			}
			{
				lblCFTBreath = new Label(canvas5, SWT.NONE);
				lblCFTBreath.setText("Breath");
				lblCFTBreath.setBounds(250, 191, 36, 20);
			}
			{
				lblCFTHeight = new Label(canvas5, SWT.NONE);
				lblCFTHeight.setText("Height");
				lblCFTHeight.setBounds(250, 233, 42, 26);
			}
			{
				txtCFTLength = new Text(canvas5, SWT.BORDER);
				txtCFTLength.setBounds(308, 144, 60, 23);
				txtCFTLength.addVerifyListener(new FloatValidation());
			}
			{
				txtCFTBreath = new Text(canvas5, SWT.BORDER);
				txtCFTBreath.setBounds(307, 184, 60, 24);
				txtCFTBreath.addVerifyListener(new FloatValidation());
			}
			{
				txtCFTHeight = new Text(canvas5, SWT.BORDER);
				txtCFTHeight.setBounds(306, 227, 60, 24);
				txtCFTHeight.addVerifyListener(new FloatValidation());
			}
			{
				btnCFTResult = new Button(canvas5, SWT.PUSH | SWT.CENTER);
				btnCFTResult.setText("Result");
				btnCFTResult.setBounds(392, 184, 43, 30);
				btnCFTResult.addSelectionListener(new submitAction());
			}
			{
				lblCFTResult = new Label(canvas5, SWT.BORDER);
				lblCFTResult.setBounds(461, 186, 78, 25);
			}
			{
				gpMeasurements = new Group(canvas5, SWT.NONE);
				GridLayout gpMeasurementsLayout = new GridLayout();
				gpMeasurementsLayout.makeColumnsEqualWidth = true;
				gpMeasurements.setLayout(gpMeasurementsLayout);
				gpMeasurements.setText("Measurements");
				gpMeasurements.setBounds(225, 60, 345, 237);
			}
			{

			}

		}
		return canvas5;

	}

	/**
	 * Method for loading canvas
	 * 
	 * @return
	 */
	public Canvas getCanvas() {
		{
			canvas2 = new Canvas(lrTab, SWT.NONE);
			canvas2.setSize(998, 696);
			{
				tbllrtrack = new Table(canvas2, SWT.FULL_SELECTION
						| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);

				tbllrtrack.setHeaderVisible(true);
				tbllrtrack.setLinesVisible(true);
				// tbllrtrack.setBounds(36, 28, 901, 453);
				tbllrtrack.setBounds(340, 44, 600, 383);
				{
					tblcolDate = new TableColumn(tbllrtrack, SWT.NONE);
					tblcolDate.setText("Date");
					tblcolDate.setWidth(161);
				}
				{
					tableColumn1 = new TableColumn(tbllrtrack, SWT.NONE);
					tableColumn1.setText("Activity");
					tableColumn1.setWidth(430);
				}

			}
			{
				lblLrNoTrack = new Label(canvas2, SWT.NONE);
				lblLrNoTrack.setText("LR NO");
				lblLrNoTrack.setBounds(36, 23, 81, 18);
				lblLrNoTrack.setAlignment(SWT.RIGHT);
			}
			{
				txtTrackNo = new Text(canvas2, SWT.BORDER);
				txtTrackNo.setBounds(129, 22, 105, 21);
			}
			{
				btnGo = new Button(canvas2, SWT.PUSH | SWT.CENTER);
				btnGo.setText("Go");
				btnGo.setBounds(242, 21, 60, 23);
				btnGo.addSelectionListener(new submitAction());
			}
			{
				lblLRType = new Label(canvas2, SWT.NONE);
				lblLRType.setText("LR Type");
				lblLRType.setBounds(49, 58, 67, 17);
				lblLRType.setAlignment(SWT.RIGHT);
			}
			{
				txtTrackLRType = new Text(canvas2, SWT.BORDER);
				txtTrackLRType.setBounds(129, 58, 173, 21);
				txtTrackLRType.setEnabled(false);
			}
			{
				lblBookedDate = new Label(canvas2, SWT.NONE);
				lblBookedDate.setText("Booked Date");
				lblBookedDate.setBounds(24, 94, 92, 20);
				lblBookedDate.setAlignment(SWT.RIGHT);
			}
			{
				lblDeliveryDate = new Label(canvas2, SWT.NONE);
				lblDeliveryDate.setText("Delivery Date");
				lblDeliveryDate.setBounds(24, 131, 92, 19);
				lblDeliveryDate.setAlignment(SWT.RIGHT);
			}
			{
				lblobookeddate = new Text(canvas2, SWT.BORDER);
				lblobookeddate.setEnabled(false);
				lblobookeddate.setBounds(129, 93, 173, 21);
			}

			{
				lblstatus = new Label(canvas2, SWT.NONE);
				lblstatus.setText("Status");
				lblstatus.setAlignment(SWT.RIGHT);
				lblstatus.setBounds(31, 165, 85, 18);
			}
			{
				lblsource = new Label(canvas2, SWT.NONE);
				lblsource.setText("Source");
				lblsource.setAlignment(SWT.RIGHT);
				lblsource.setBounds(24, 200, 92, 18);
			}
			{
				lbldestination = new Label(canvas2, SWT.NONE);
				lbldestination.setText("Destination");
				lbldestination.setAlignment(SWT.RIGHT);
				lbldestination.setBounds(24, 233, 92, 18);
			}
			{
				lblnoofariticles = new Label(canvas2, SWT.NONE);
				lblnoofariticles.setText("No Of Articles");
				lblnoofariticles.setAlignment(SWT.RIGHT);
				lblnoofariticles.setBounds(24, 269, 92, 17);
			}
			{
				lblweight = new Label(canvas2, SWT.NONE);
				lblweight.setText("Weight");
				lblweight.setAlignment(SWT.RIGHT);
				lblweight.setBounds(24, 305, 92, 17);
			}
			{
				lblostatus = new Text(canvas2, SWT.BORDER);
				lblostatus.setEnabled(false);
				lblostatus.setBounds(129, 164, 173, 21);
			}
			{
				lblosource = new Text(canvas2, SWT.BORDER);
				lblosource.setEnabled(false);
				lblosource.setBounds(129, 199, 173, 21);
			}
			{
				lblodestination = new Text(canvas2, SWT.BORDER);
				lblodestination.setEnabled(false);
				lblodestination.setBounds(129, 233, 173, 21);
			}
			{
				lblonoofarticles = new Text(canvas2, SWT.BORDER);
				lblonoofarticles.setEnabled(false);
				lblonoofarticles.setBounds(129, 268, 173, 21);
			}
			{
				lbloweight = new Text(canvas2, SWT.BORDER);
				lbloweight.setEnabled(false);
				lbloweight.setBounds(129, 303, 173, 21);
			}
			{
				lblodeliverydate = new Text(canvas2, SWT.BORDER);
				lblodeliverydate.setEnabled(false);
				lblodeliverydate.setBounds(129, 130, 173, 21);
			}

			{
				lblTrackDdc = new Label(canvas2, SWT.NONE);
				lblTrackDdc.setBounds(12, 339, 105, 18);
				lblTrackDdc.setText("Door Delivery Charge");
				lblTrackDdc.setAlignment(SWT.RIGHT);
			}
			{
				txtTrackDdc = new Text(canvas2, SWT.BORDER);
				txtTrackDdc.setBounds(129, 337, 173, 21);
				txtTrackDdc.setEnabled(false);
			}
			{
				lblBFT = new Label(canvas2, SWT.NONE);
				lblBFT.setBounds(44, 438, 73, 18);
				lblBFT.setText("Total Freight");
				lblBFT.setAlignment(SWT.RIGHT);
				lblBFT.setVisible(false);
			}
			{
				txtTrackBFT = new Text(canvas2, SWT.BORDER);
				txtTrackBFT.setBounds(129, 435, 173, 21);
				txtTrackBFT.setEnabled(false);
				txtTrackBFT.setVisible(false);
			}

			{
				lblTrackCnor = new Label(canvas2, SWT.NONE);
				lblTrackCnor.setBounds(24, 372, 96, 20);
				lblTrackCnor.setText("Consignor Name");
				lblTrackCnor.setAlignment(SWT.RIGHT);
				lblTrackCnor.setVisible(false);
			}
			{
				txtTrackCnor = new Text(canvas2, SWT.BORDER);
				txtTrackCnor.setBounds(129, 370, 173, 21);
				txtTrackCnor.setEnabled(false);
				txtTrackCnor.setVisible(false);
			}
			{
				lblTrackCnee = new Label(canvas2, SWT.NONE);
				lblTrackCnee.setBounds(24, 404, 96, 21);
				lblTrackCnee.setText("Consignee Name");
				lblTrackCnee.setAlignment(SWT.RIGHT);
				lblTrackCnee.setVisible(false);
			}
			{
				txtTrackCnee = new Text(canvas2, SWT.BORDER);
				txtTrackCnee.setBounds(129, 402, 173, 21);
				txtTrackCnee.setEnabled(false);
				txtTrackCnee.setVisible(false);
			}

			{
				lblDeliver = new Label(canvas2, SWT.NONE);
				lblDeliver.setBounds(36, 475, 786, 30);
				lblDeliver.setVisible(false);
			}
			{
				lblCancel = new Label(canvas2, SWT.NONE);
				lblCancel.setBounds(36, 505, 922, 106);
				lblCancel.setVisible(false);
			}

		}
		return canvas2;
	}

	/**
	 * Method to load stationary canvas
	 * 
	 * @return
	 */
	public Canvas getStationaryCanvas() {
		try {
			{
				canvas3 = new Canvas(lrTab, SWT.NONE);
				canvas3.setSize(998, 696);
				{
					tblStationary = new Table(canvas3, SWT.FULL_SELECTION
							| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);

					tblStationary.setHeaderVisible(true);
					tblStationary.setLinesVisible(true);
					tblStationary.setBounds(160, 48, 496, 479);
					final int columnCount = 4;
					if (!BeanUtil.getInstance().isBranch()) {
						tblStationary.addListener(SWT.MouseDoubleClick,
								new Listener() {
									public void handleEvent(Event event) {
										Rectangle clientArea = tblStationary
												.getClientArea();
										Point pt = new Point(event.x, event.y);
										int index = tblStationary.getTopIndex();
										while (index < tblStationary
												.getItemCount()) {
											boolean visible = false;
											TableItem item = tblStationary
													.getItem(index);
											for (int i = 0; i < columnCount; i++) {
												Rectangle rect = item
														.getBounds(i);
												if (rect.contains(pt)) {
													// System.out.println("Item
													// " + index + "-"
													// + i);

													lrTab.setSelection(i - 1);
													String selectedTab = lrTab
															.getSelection()[0]
															.getText();
													canvas = getCanvas(selectedTab);
													tabItem[i - 1]
															.setControl(canvas);
													txtLrno.setText(item
															.getText(i));
													isValidLRNO = true;
												}
												if (!visible
														&& rect
																.intersects(clientArea)) {
													visible = true;
												}

											}
											if (!visible)
												return;
											index++;
										}
									}
								});
					}
					{
						tblcolSn = new TableColumn(tblStationary, SWT.NONE);
						tblcolSn.setText("S No");
						tblcolSn.setWidth(61);
					}
					{
						tableColTopay = new TableColumn(tblStationary, SWT.NONE);
						tableColTopay.setText("Topay");
						tableColTopay.setWidth(136);
					}
					{
						tableColPaid = new TableColumn(tblStationary, SWT.NONE);
						tableColPaid.setText("Paid");
						tableColPaid.setWidth(136);
					}
					{
						tableColBilling = new TableColumn(tblStationary,
								SWT.NONE);
						tableColBilling.setText("Billing");
						tableColBilling.setWidth(136);
					}

					{
						tableColCR = new TableColumn(tblStationary, SWT.NONE);
						tableColCR.setText("CR");
						tableColCR.setWidth(0);
						tableColCR.setResizable(false);
					}

				}
				{
					lblUserInfo = new Label(canvas3, SWT.NONE);
					lblUserInfo
							.setText("Double click on an LR number to jump to the respective LR Page.");
					lblUserInfo.setBounds(161, 22, 314, 22);
				}

				/*
				 * BookingDTO[] usedLrBilling = null; BookingDTO[] usedLrPaid =
				 * null; BookingDTO[] usedLrTopay = null;
				 * 
				 * try { /*lrrangeDTO = handler.getAllocatedLrnumbers(false);
				 * int rangeLen = lrrangeDTO.length; if (null != lrrangeDTO &&
				 * rangeLen > 0) { // for all types for(int l = 0; l < rangeLen;
				 * l++){ if(lrrangeDTO[l].getType().equalsIgnoreCase("topay")){
				 * usedLrTopay =
				 * handler.getUsedLR(lrrangeDTO[l].getAllocationDate(),"topay");
				 * break; } } for(int l = 0; l < rangeLen; l++){
				 * if(lrrangeDTO[l].getType().equalsIgnoreCase("paid")){
				 * usedLrPaid =
				 * handler.getUsedLR(lrrangeDTO[l].getAllocationDate(),"paid");
				 * break; } } for(int l = 0; l < rangeLen; l++){
				 * if(lrrangeDTO[l].getType().equalsIgnoreCase("billing")){
				 * usedLrBilling =
				 * handler.getUsedLR(lrrangeDTO[l].getAllocationDate(),"billing");
				 * break; } }
				 * 
				 * //System.out.println("len--?>"+lrrangeDTO.length);
				 * 
				 * ArrayList<LRNumberRangeDTO> unusedLrlistBilling =
				 * getUnUsedLRList( usedLrBilling, false);
				 * 
				 * ArrayList<LRNumberRangeDTO> unusedLrlistPaid =
				 * getUnUsedLRList( usedLrPaid, false);
				 * 
				 * ArrayList<LRNumberRangeDTO> unusedLrlistTopay =
				 * getUnUsedLRList( usedLrTopay, false);
				 * 
				 * int maxLen = 0; int topayL = 0; int paidL = 0; int billingL =
				 * 0;
				 * 
				 * if(unusedLrlistBilling != null){ billingL =
				 * unusedLrlistBilling.size(); } if(unusedLrlistPaid != null){
				 * paidL = unusedLrlistPaid.size(); } if(unusedLrlistTopay !=
				 * null){ topayL = unusedLrlistTopay.size(); }
				 * 
				 * maxLen = billingL > paidL ? (billingL > topayL ?
				 * billingL:topayL) : (paidL > topayL ? paidL:topayL);
				 * 
				 * //System.out.println("max==>"+maxLen); for (int i = 0; i <
				 * maxLen; i++) { TableItem item = new TableItem(tblStationary,
				 * SWT.NONE); item.setText(0, String.valueOf(i+1)); }
				 * 
				 * if (null != unusedLrlistBilling && unusedLrlistBilling.size() >
				 * 0) { TableItem[] item = tblStationary.getItems(); for (int
				 * set = 0; set < unusedLrlistBilling.size(); set++) {
				 * LRNumberRangeDTO dto = (LRNumberRangeDTO)
				 * unusedLrlistBilling.get(set); item[set].setText(3,
				 * String.valueOf(dto.getLrNumber())); } } if (null !=
				 * unusedLrlistPaid && unusedLrlistPaid.size() > 0) {
				 * TableItem[] item = tblStationary.getItems(); for (int set =
				 * 0; set < unusedLrlistPaid.size(); set++) { LRNumberRangeDTO
				 * dto = (LRNumberRangeDTO) unusedLrlistPaid.get(set);
				 * item[set].setText(2, String.valueOf(dto.getLrNumber())); } }
				 * if (null != unusedLrlistTopay && unusedLrlistTopay.size() >
				 * 0) { TableItem[] item = tblStationary.getItems(); for (int
				 * set = 0; set < unusedLrlistTopay.size(); set++) {
				 * LRNumberRangeDTO dto = (LRNumberRangeDTO)
				 * unusedLrlistTopay.get(set); item[set].setText(1,
				 * String.valueOf(dto.getLrNumber())); } }
				 * 
				 * 
				 * /*if (null != unusedLrlist && unusedLrlist.size() > 0) {
				 * 
				 * for (int display = 0; display < unusedLrlist.size();
				 * display++) { new TableItem(tblStationary, SWT.NONE); } int
				 * topay = 0; int paid = 0; int billing = 0; int cr = 0; String
				 * type; TableItem[] item = tblStationary.getItems(); for (int
				 * set = 0; set < unusedLrlist.size(); set++) { LRNumberRangeDTO
				 * dto = (LRNumberRangeDTO) unusedLrlist .get(set); type =
				 * dto.getType(); if (type.equalsIgnoreCase("Topay")) {
				 * item[topay++].setText(1, dto.getLrNumber()); item[topay -
				 * 1].setText(0, String .valueOf(topay)); } else if
				 * (type.equalsIgnoreCase("Paid")) { item[paid++].setText(2,
				 * dto.getLrNumber()); item[paid - 1].setText(0, String
				 * .valueOf(paid)); } else if (type.equalsIgnoreCase("Billing")) {
				 * item[billing++].setText(3, dto .getLrNumber()); item[billing -
				 * 1].setText(0, String .valueOf(billing)); } /* else if
				 * (type.equalsIgnoreCase("CR")) { item[cr++].setText(4,
				 * dto.getLrNumber()); item[cr - 1].setText(0,
				 * String.valueOf(cr)); }
				 */

				/*
				 * } int x = topay > paid ? (topay > billing ? (topay > cr ?
				 * topay : cr) : billing > cr ? billing : cr) : paid > billing ?
				 * (paid > cr ? paid : cr) : billing > cr ? billing : cr;
				 * 
				 * for (int delete = x; delete < unusedLrlist.size(); delete++) {
				 * item[delete].dispose(); } } } } catch (Exception e) {
				 * 
				 * e.printStackTrace(); } } } catch (Exception e) {
				 * e.printStackTrace(); } return canvas3; }
				 */

				try {
					populateStationary();
				} catch (Exception e) {

					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return canvas3;
	}

	private void populateTopayStationary() {

		try {
			unUsedList = handler.getUnUsedLR();
			if (unUsedList != null) {
				int topay = 0;
				int paid = 0;
				int billing = 0;
				String type;
				int len = unUsedList.length;
				for (int i = 0; i < len; i++) {
					BookingDTO dto = unUsedList[i];
					type = dto.getType();
					if (type.equalsIgnoreCase("Topay")) {
						// item[topay++].setText(1, dto.getLrno());
						// item[topay - 1].setText(0, String.valueOf(topay));
						// System.out.println("i-->"+i);
						cbNewLrno.add(dto.getLrno());

					}

				}
				int x = billing > paid ? (billing > topay ? billing : topay)
						: (paid > topay ? paid : topay);

				for (int delete = x; delete < len; delete++) {
					// item[delete].dispose();
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	private void populateStationary() {
		try {
			unUsedList = handler.getUnUsedLR();
			if (unUsedList != null) {
				int len = unUsedList.length;

				for (int i = 0; i < len; i++) {
					new TableItem(tblStationary, SWT.None);
				}

				int topay = 0;
				int paid = 0;
				int billing = 0;
				String type;
				TableItem[] item = tblStationary.getItems();

				for (int i = 0; i < len; i++) {
					BookingDTO dto = unUsedList[i];
					type = dto.getType();
					if (type.equalsIgnoreCase("Topay")) {
						item[topay++].setText(1, dto.getLrno());
						item[topay - 1].setText(0, String.valueOf(topay));

					} else if (type.equalsIgnoreCase("Paid")) {
						item[paid++].setText(2, dto.getLrno());
						item[paid - 1].setText(0, String.valueOf(paid));
					} else if (type.equalsIgnoreCase("Billing")) {
						item[billing++].setText(3, dto.getLrno());
						item[billing - 1].setText(0, String.valueOf(billing));
					}

				}
				int x = billing > paid ? (billing > topay ? billing : topay)
						: (paid > topay ? paid : topay);

				for (int delete = x; delete < len; delete++) {
					item[delete].dispose();
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return
	 */
	private Canvas getCardRateCanvas() {
		canCardRate = new Canvas(lrTab, SWT.NONE);
		canCardRate.setSize(769, 465);
		{
			tblcardRate = new Table(canCardRate, SWT.FULL_SELECTION
					| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
			tblcardRate.setBounds(137, 49, 600, 371);

			tblcardRate.setHeaderVisible(true);
			tblcardRate.setLinesVisible(true);

			{
				colCardRateSno = new TableColumn(tblcardRate, SWT.NONE);
				colCardRateSno.setText("SNO");
				colCardRateSno.setWidth(60);

				colCardRateSno.addListener(SWT.Selection, new sortListener());
			}
			{
				colCardRateStationName = new TableColumn(tblcardRate, SWT.NONE);
				colCardRateStationName.setText("Station Name");
				colCardRateStationName.setWidth(214);

				colCardRateStationName.addListener(SWT.Selection,
						new sortListener());
			}
			{
				colCardRateBpi = new TableColumn(tblcardRate, SWT.NONE);
				colCardRateBpi.setText("Station Code");
				colCardRateBpi.setWidth(100);

				colCardRateBpi.addListener(SWT.Selection, new sortListener());
			}
			{
				colCardRateDistance = new TableColumn(tblcardRate, SWT.NONE);
				colCardRateDistance.setText("Distance");
				colCardRateDistance.setWidth(100);

				colCardRateDistance.addListener(SWT.Selection,
						new sortListener());
			}

			{
				colCardRateCR = new TableColumn(tblcardRate, SWT.NONE);
				colCardRateCR.setText("Card Rate");
				colCardRateCR.setWidth(100);

				colCardRateCR.addListener(SWT.Selection, new sortListener());
			}

		}
		{
			lblCardRateStation = new Label(canCardRate, SWT.NONE);
			lblCardRateStation.setText("From Station");
			lblCardRateStation.setBounds(137, 12, 67, 24);
		}
		{
			txtCardRateFromStation = new Text(canCardRate, SWT.BORDER);
			txtCardRateFromStation.setBounds(210, 6, 189, 20);
			txtCardRateFromStation.setText(handler.getStationName() + " - "
					+ handler.getStationCode());
			txtCardRateFromStation.setEnabled(false);

		}
		/*
		 * { btnCardRatePrint = new Button(canCardRate, SWT.NONE);
		 * btnCardRatePrint.setText("Print"); btnCardRatePrint.setBounds(660,
		 * 428, 75, 30); btnCardRatePrint.addSelectionListener(new
		 * ReportingListener()); }
		 */
		return canCardRate;
	}

	/**
	 * Class for Report composite Listener
	 * 
	 * @author Administrator
	 * 
	 */
	public class ReportingListener implements SelectionListener {

		@Override
		public void widgetDefaultSelected(SelectionEvent event) {

		}

		@Override
		public void widgetSelected(SelectionEvent event) {
			try {
				Object source = event.getSource();
				if (source == btnCardRatePrint) {
					CardRateDTO[] dto = buildCardRateDTO();
					if (null != dto)
						handler.printCardRate(dto, txtCardRateFromStation
								.getText());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		/**
		 * 
		 * @return
		 * @throws Exception
		 */
		private CardRateDTO[] buildCardRateDTO() throws Exception {
			ArrayList<CardRateDTO> list = new ArrayList<CardRateDTO>();
			if (tblcardRate != null) {
				int size = tblcardRate.getItemCount();
				if (size > 0) {
					TableItem[] item = tblcardRate.getItems();
					for (int i = 0; i < size; i++) {
						CardRateDTO dto = new CardRateDTO();
						dto.setStation_name(item[i].getText(1));
						dto.setStation_code(item[i].getText(2));
						dto.setDistance((Integer.parseInt(item[i].getText(3))));
						dto
								.setCard_rate((Float.parseFloat(item[i]
										.getText(4))));
						list.add(dto);

					}
				}
			}

			int len = list.size();

			if (len > 0) {
				return list.toArray(new CardRateDTO[len]);
			}
			return null;
		}

	}

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
					if (refTable != tblcardRate)
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
			TableColumn column = (TableColumn) e.widget;
			if (column == colCardRateSno) {
				sortByFloat(0, tblcardRate);
			} else if (column == colCardRateStationName) {
				sortByString(1, tblcardRate);
			} else if (column == colCardRateBpi) {
				sortByString(2, tblcardRate);
			} else if (column == colCardRateDistance) {
				sortByFloat(3, tblcardRate);
			} else if (column == colCardRateCR) {
				sortByFloat(4, tblcardRate);
			}

		}
	}

	public Canvas getCustomerCanvas() {

		{
			canvas4 = new Canvas(lrTab, SWT.NONE);
			canvas4.setSize(600, 500);

			try {
				CustomerComposite cptCustomer = new CustomerComposite(canvas4,
						SWT.BORDER);
				cptCustomer.setBounds(170, 40, 550, 400);
				cptCustomer.loadComposite();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return canvas4;

	}

	/**
	 * Method to get all the unused lr from the given used lr list and the
	 * range.
	 * 
	 * @param usedLr
	 *            Array of BookingDTO instance.
	 * @return ArrayList<LRNumberRangeDTO> List of unused lr's.
	 * @throws Exception
	 */
	/*
	 * public ArrayList<LRNumberRangeDTO> getUnUsedLRList(BookingDTO[] usedLr,
	 * boolean refresh) throws Exception {
	 * 
	 * int len = 0; LRNumberRangeDTO temp = null; ArrayList<LRNumberRangeDTO>
	 * unusedList = new ArrayList<LRNumberRangeDTO>(); int fromNumber = 0; int
	 * toNumber = 0; String stationcode = handler.getStationCode(); String
	 * lrprefix; String lrNumber = null; int usedLrLength = 0; boolean
	 * isAlreadyAvail = false;
	 * 
	 * if (null != usedLr){ usedLrLength = usedLr.length;
	 * 
	 * if (null != lrrangeDTO && (len = lrrangeDTO.length) > 0) { for (int i =
	 * 0; i < len; i++) { temp = (LRNumberRangeDTO) lrrangeDTO[i]; if
	 * (usedLr[0].getType().equalsIgnoreCase(temp.getType()) &&
	 * temp.getStationCode().equalsIgnoreCase(stationcode)) { lrprefix =
	 * temp.getRangeFrom().substring(0, 1); fromNumber =
	 * Integer.parseInt(temp.getRangeFrom() .substring(1)); toNumber =
	 * Integer.parseInt(temp.getRangeTo().substring(1));
	 * 
	 * for (int j = fromNumber; j <= toNumber; j++) { lrNumber = (lrprefix + "") +
	 * j; isAlreadyAvail = false;
	 * 
	 * for (int k = 0; k < usedLrLength; k++) { if
	 * (usedLr[k].getLrno().equalsIgnoreCase(lrNumber)) { isAlreadyAvail = true;
	 * break; } } if (!isAlreadyAvail) { LRNumberRangeDTO tempdto = new
	 * LRNumberRangeDTO(); tempdto.setType(temp.getType());
	 * tempdto.setLrNumber(lrNumber); unusedList.add(tempdto); } } } } } }
	 * 
	 * return unusedList; }
	 */

	/**
	 * Method to get Canvas
	 * 
	 * @return
	 */
	public Canvas getCanvas(String lrType) {
		{
			// System.out.println("lrtype:"+lrType);
			canvas = new Canvas(lrTab, SWT.BORDER);
			if (lrType.equalsIgnoreCase("UPD")) {

				{
					btnToUPD = new Button(canvas, SWT.CHECK | SWT.LEFT);
					btnToUPD.setText("TO UPD");
					btnToUPD.setBounds(154, 5, 60, 25);
					btnToUPD.addSelectionListener(new submitAction());
				}
				{
					btnRebooklr = new Button(canvas, SWT.CHECK | SWT.LEFT);
					btnRebooklr.setText("Rebook LR");
					btnRebooklr.setBounds(224, 5, 60, 25);
					btnRebooklr.addSelectionListener(new submitAction());
				}

				{
					gpupd = new Group(canvas, SWT.NONE);
					GridLayout gpArticleLayout = new GridLayout();
					gpArticleLayout.makeColumnsEqualWidth = true;
					gpupd.setLayout(gpArticleLayout);
					// gpArticle.setText("ArticleDetails");
					gpupd.setBounds(139, -3, 150, 37);
				}
				{

					lblNewlrno = new Label(canvas, SWT.RIGHT);
					lblNewlrno.setText("NEW LR NO.");
					lblNewlrno.setBounds(361, 8, 90, 19);
				}
				{
					cbNewLrno = new Combo(canvas, SWT.BORDER | SWT.V_SCROLL
							| SWT.READ_ONLY);
					cbNewLrno.setBounds(476, 4, 105, 25);
					cbNewLrno.setSize(105, 70);

					cbNewLrno.setTextLimit(7);

					// cbNewLrno.addVerifyListener(new
					// AlphaNumericValidation());
					// cbNewLrno.addFocusListener(new lrvalidation());

				}

				{

					lblOldlrno = new Label(canvas, SWT.RIGHT);
					lblOldlrno.setText("OLD LR NO.");
					lblOldlrno.setBounds(581, 8, 80, 19);
				}
				{
					txtOldlrno = new Text(canvas, SWT.BORDER);
					txtOldlrno.setBounds(671, 4, 105, 25);
					txtOldlrno.setTextLimit(7);
					txtOldlrno.addVerifyListener(new AlphaNumericValidation());
					txtOldlrno.addFocusListener(new Oldlrvalidation());

				}

			} else {
				{

					label3 = new Label(canvas, SWT.RIGHT);
					label3.setText("LR NO.");
					label3.setBounds(539, 8, 60, 19);
				}
				{
					txtLrno = new Text(canvas, SWT.BORDER);
					txtLrno.setBounds(611, 4, 105, 25);
					txtLrno.setTextLimit(7);
					txtLrno.addVerifyListener(new AlphaNumericValidation());
					txtLrno.addFocusListener(new lrvalidation());

				}
			}
			{
				label4 = new Label(canvas, SWT.RIGHT);
				label4.setText("Date");
				label4.setBounds(796, 8, 25, 14);
			}
			{
				txtDate = new Text(canvas, SWT.BORDER);
				txtDate.setBounds(833, 4, 104, 24);
				txtDate.setTextLimit(10);
				txtDate.setTopIndex(3);
				txtDate.setEnabled(false);

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
				btnView = new Button(canvas, SWT.PUSH | SWT.CENTER);
				btnView.setText("View");
				btnView.setBounds(730, 12, 60, 26);
				btnView.setVisible(false);
				btnView.addSelectionListener(new submitAction());
			}
			{
				label10 = new Label(canvas, SWT.NONE);
				label10.setText("From");
				label10.setBounds(71, 55, 25, 16);
			}
			{
				txtFrom = new Text(canvas, SWT.BORDER | SWT.READ_ONLY);
				txtFrom.setBounds(120, 51, 166, 23);
				txtFrom.setText(handler.getStationName() + " - "
						+ handler.getStationCode());

				txtFrom.setEnabled(false);

			}
			{
				label6 = new Label(canvas, SWT.NONE);
				label6.setText("To");
				label6.setBounds(568, 54, 17, 20);
			}
			{
				cbTo = new Combo(canvas, SWT.DROP_DOWN);
				cbTo.setBounds(598, 51, 166, 22);
				cbTo.addKeyListener(new LrKeyListener());
				cbTo.addSelectionListener(new submitAction());

				cbTo.addFocusListener(new FocusListener() {
					@Override
					public void focusGained(FocusEvent arg0) {

					}

					@Override
					public void focusLost(FocusEvent arg0) {

						String selectedStation = cbTo.getText();

						if (!selectedStation.equals("")) {
							try {
								StationsDTO[] stations = handler.getStations();

								if (null != stations) {
									int len = stations.length;
									boolean isAvailstation = false;
									String availstation = null;
									for (int j = 0; j < len; j++) {
										availstation = stations[j].getName()
												+ " - " + stations[j].getId();
										if (selectedStation
												.equals(availstation)) {

											isAvailstation = true;
											break;
										}
									}
									if (!isAvailstation) {
										displayError("Please Select a Valid To Station");
										cbTo.setText("");
										cbTo.setFocus();
									}
								}
							} catch (Exception exception) {
								exception.printStackTrace();
							}
						}
					}

				});

				populateArticleTypes();
				populateDestStationCodes();

			}
			{
				cbConsignorName = new Combo(canvas, SWT.NONE);
				cbConsignorName.setBounds(120, 85, 166, 25);

				cbConsignorName.addFocusListener(new submitAction());
				cbConsignorName.addSelectionListener(new submitAction());
			}
			{
				txtConsignor = new Text(canvas, SWT.V_SCROLL | SWT.BORDER
						| SWT.WRAP);
				txtConsignor.setBounds(120, 126, 232, 83);
				txtConsignor.setTextLimit(256);
				txtConsignor.addTraverseListener(new TraverseListener() {
					public void keyTraversed(TraverseEvent e) {
						if (e.detail == SWT.TRAVERSE_TAB_NEXT
								|| e.detail == SWT.TRAVERSE_TAB_PREVIOUS) {
							e.doit = true;
						}
					}
				});
			}
			{
				txtConsignorCST = new Text(canvas, SWT.LEFT | SWT.RIGHT
						| SWT.BORDER);
				txtConsignorCST.setBounds(117, 224, 75, 27);
				txtConsignorCST.setTextLimit(15);
				txtConsignorCST.addVerifyListener(new AlphaNumericValidation());

			}
			{
				lblConsignorPhone = new Label(canvas, SWT.NONE);
				lblConsignorPhone.setText("Mobile");
				lblConsignorPhone.setBounds(210, 225, 35, 25);
			}
			{
				txtConsignorPhone = new Text(canvas, SWT.BORDER);
				txtConsignorPhone.setBounds(248, 223, 85, 25);
				txtConsignorPhone
						.addVerifyListener(new NumericExtendedValidation());
				txtConsignorPhone.addKeyListener(new KeyListener() {

					@Override
					public void keyPressed(KeyEvent arg0) {

					}

					@Override
					public void keyReleased(KeyEvent event) {
						if (event.character == 'n' || event.character == 'N'
								|| event.character == 'I'
								|| event.character == 'L') {
							txtConsignorPhone.setText("NIL");
						}

					}

				});
			}
			{
				lblCnorLandline = new Label(canvas, SWT.NONE);
				lblCnorLandline.setText("Land Line");
				lblCnorLandline.setBounds(340, 224, 46, 21);
			}
			{
				txtCnorLandline = new Text(canvas, SWT.BORDER);
				txtCnorLandline.setBounds(393, 222, 85, 25);
				txtCnorLandline
						.addVerifyListener(new NumericExtendedValidation());
				txtCnorLandline.addKeyListener(new KeyListener() {

					@Override
					public void keyPressed(KeyEvent arg0) {

					}

					@Override
					public void keyReleased(KeyEvent event) {
						if (event.character == 'n' || event.character == 'N'
								|| event.character == 'I'
								|| event.character == 'L') {
							txtCnorLandline.setText("NIL");
						}

					}

				});
			}

			{
				txtCnorSMS = new Text(canvas, SWT.BORDER);
				txtCnorSMS.setBounds(500, 222, 30, 25);
				txtCnorSMS.setVisible(false);
			}

			{
				txtCneeSMS = new Text(canvas, SWT.BORDER);
				txtCneeSMS.setBounds(550, 222, 80, 25);
				txtCneeSMS.setVisible(false);
			}

			{
				btnSaveConsignorDetails = new Button(canvas, SWT.PUSH
						| SWT.CENTER);
				btnSaveConsignorDetails.setText("Save");
				btnSaveConsignorDetails.setBounds(287, 84, 37, 23);
				btnSaveConsignorDetails
						.addSelectionListener(new submitAction());
			}

			{
				cbConsigneeName = new Combo(canvas, SWT.NONE);
				cbConsigneeName.setBounds(598, 85, 166, 25);

				cbConsigneeName.addFocusListener(new submitAction());
				cbConsigneeName.addSelectionListener(new submitAction());

			}
			{
				txtConsignee = new Text(canvas, SWT.V_SCROLL | SWT.BORDER
						| SWT.WRAP);
				txtConsignee.setBounds(600, 126, 230, 83);
				txtConsignee.setTextLimit(256);
				txtConsignee.addTraverseListener(new TraverseListener() {
					public void keyTraversed(TraverseEvent e) {
						if (e.detail == SWT.TRAVERSE_TAB_NEXT
								|| e.detail == SWT.TRAVERSE_TAB_PREVIOUS) {
							e.doit = true;
						}
					}
				});

			}
			{
				txtConsigneeCST = new Text(canvas, SWT.LEFT | SWT.RIGHT
						| SWT.BORDER);
				txtConsigneeCST.setBounds(596, 219, 74, 24);
				txtConsigneeCST.setTextLimit(15);
				txtConsigneeCST.addVerifyListener(new AlphaNumericValidation());

			}
			{
				lblConsigneePhone = new Label(canvas, SWT.NONE);
				lblConsigneePhone.setText("Mobile");
				lblConsigneePhone.setBounds(690, 223, 33, 22);
			}
			{
				txtConsigneePhone = new Text(canvas, SWT.BORDER);
				txtConsigneePhone.setBounds(726, 221, 85, 25);
				txtConsigneePhone
						.addVerifyListener(new NumericExtendedValidation());
				txtConsigneePhone.addKeyListener(new KeyListener() {

					@Override
					public void keyPressed(KeyEvent arg0) {

					}

					@Override
					public void keyReleased(KeyEvent event) {
						if (event.character == 'n' || event.character == 'N'
								|| event.character == 'I'
								|| event.character == 'L') {
							txtConsigneePhone.setText("NIL");
						}

					}

				});
			}
			{
				lblCneeLandline = new Label(canvas, SWT.NONE);
				lblCneeLandline.setText("Land Line");
				lblCneeLandline.setBounds(814, 225, 46, 21);
			}
			{
				txtCneeLandline = new Text(canvas, SWT.BORDER);
				txtCneeLandline.setBounds(860, 223, 82, 25);
				txtCneeLandline
						.addVerifyListener(new NumericExtendedValidation());
				txtCneeLandline.addKeyListener(new KeyListener() {

					@Override
					public void keyPressed(KeyEvent arg0) {

					}

					@Override
					public void keyReleased(KeyEvent event) {
						if (event.character == 'n' || event.character == 'N'
								|| event.character == 'I'
								|| event.character == 'L') {
							txtCneeLandline.setText("NIL");
						}

					}

				});
			}

			{
				btnSaveConsigneeDetails = new Button(canvas, SWT.PUSH
						| SWT.CENTER);
				btnSaveConsigneeDetails.setText("Save");
				btnSaveConsigneeDetails.setBounds(765, 84, 39, 23);
				btnSaveConsigneeDetails
						.addSelectionListener(new submitAction());
			}

			{
				cbUnit = new Combo(canvas, SWT.READ_ONLY);
				cbUnit.setBounds(56, 302, 80, 21);
				cbUnit.add("Feet");
				cbUnit.add("Inches");
				cbUnit.add("Cm");
				cbUnit.addSelectionListener(new submitAction());

			}
			{

				lbleffectivecwt = new Label(canvas, SWT.NONE);
				lbleffectivecwt.setText("Total Charged Weight");
				lbleffectivecwt.setBounds(236, 513, 119, 14);

			}
			{
				txteffchargedCWT = new Text(canvas, SWT.BORDER);
				txteffchargedCWT.setBounds(361, 510, 78, 21);
				txteffchargedCWT.setEnabled(false);
			}

			{
				tblArticle = new Table(canvas, SWT.FULL_SELECTION
						| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
				tblArticle.setBounds(9, 360, 549, 139);
				tblArticle.setHeaderVisible(true);

				tblArticle.setLinesVisible(true);
				tblArticle.addListener(SWT.MeasureItem, new Listener() {
					public void handleEvent(Event event) {
						event.height = 22;
					}
				});

				{

					colArticleName = new TableColumn(tblArticle, SWT.NONE);
					colArticleName.setText("Article");
					colArticleName.setWidth(120);

				}
				{
					colNoofArticle = new TableColumn(tblArticle, SWT.NONE);
					colNoofArticle.setText("NOA");
					colNoofArticle.setWidth(40);
				}
				{
					colArticleValue = new TableColumn(tblArticle, SWT.NONE);
					colArticleValue.setText("Value");
					colArticleValue.setWidth(57);
				}
				{
					colActualWeight = new TableColumn(tblArticle, SWT.NONE);
					colActualWeight.setText("Act. Wt");
					colActualWeight.setWidth(53);
				}
				{
					colDimensions1 = new TableColumn(tblArticle, SWT.NONE);
					colDimensions1.setText("L");
					colDimensions1.setWidth(33);
				}
				{
					colBreadth = new TableColumn(tblArticle, SWT.NONE);
					colBreadth.setText("B");
					colBreadth.setWidth(36);
				}
				{
					colHeight = new TableColumn(tblArticle, SWT.NONE);
					colHeight.setText("H");
					colHeight.setWidth(36);
				}
				{
					colChargedWeight = new TableColumn(tblArticle, SWT.NONE);
					colChargedWeight.setText("Crg. Wt");
					colChargedWeight.setWidth(53);
				}
				{
					colDescription = new TableColumn(tblArticle, SWT.NONE);
					colDescription.setText("Description");
					colDescription.setWidth(74);
				}
				{
					coldelete = new TableColumn(tblArticle, SWT.NONE);
					coldelete.setWidth(21);
				}
			}

			populateConsignorNames();
			populateConsigneeNames();
			{
				label7 = new Label(canvas, SWT.NONE);
				label7.setText("Consignor Name");
				label7.setBounds(18, 94, 82, 15);
			}

			{
				label9 = new Label(canvas, SWT.NONE);
				label9.setText("Consignor Address");
				label9.setBounds(7, 140, 95, 30);
			}

			{
				label5 = new Label(canvas, SWT.NONE);
				label5.setText("Consignee Name");
				label5.setBounds(511, 94, 82, 15);
			}
			{
				label8 = new Label(canvas, SWT.NONE);
				label8.setText("Consignee Address");
				label8.setBounds(494, 140, 95, 22);
			}

			{
				label11 = new Label(canvas, SWT.SEPARATOR | SWT.HORIZONTAL);
				label11.setText("label11");
				label11.setBounds(0, 39, 953, 4);
			}
			{
				label12 = new Label(canvas, SWT.SEPARATOR | SWT.HORIZONTAL);
				label12.setText("label12");
				label12.setBounds(0, 257, 953, 6);
			}
			{
				label13 = new Label(canvas, SWT.SEPARATOR);
				label13.setText("label13");
				label13.setBounds(479, 42, 14, 221);
			}
			{
				label16 = new Label(canvas, SWT.RIGHT);
				label16.setText("Consignee's TIN No.");
				label16.setBounds(494, 220, 100, 17);
			}

			{
				lblTopay = new Label(canvas, SWT.NONE);
				lblTopay.setText(lrType);

				if (lrTab.getSelection()[0].getText().equalsIgnoreCase("UPD")) {

					lblTopay.setBounds(59, 7, 75, 30);
				} else {
					lblTopay.setBounds(259, 5, 75, 30);
				}

			}
			{
				label19 = new Label(canvas, SWT.RIGHT);
				label19.setText("Bas Frt");
				label19.setBounds(577, 324, 42, 23);
			}
			{
				label20 = new Label(canvas, SWT.RIGHT);
				label20.setText("LRC");
				label20.setBounds(584, 353, 34, 25);
			}
			{
				label21 = new Label(canvas, SWT.RIGHT);
				label21.setText("DHC");
				label21.setBounds(594, 384, 25, 21);
			}

			if (!lrTab.getSelection()[0].getText().equalsIgnoreCase("UPD")) {
				label22 = new Label(canvas, SWT.RIGHT);
				label22.setText("CCC");
				label22.setBounds(591, 411, 29, 21);
			} else {
				label136 = new Label(canvas, SWT.RIGHT);
				label136.setText("LC");
				label136.setBounds(591, 438, 29, 21);
			}

			int selectindex = lrTab.getSelectionIndex();
			if (!lrTab.getSelection()[0].getText().equalsIgnoreCase("UPD")) {
				lblDdc = new Label(canvas, SWT.RIGHT);
				lblDdc.setText("DCC");
				lblDdc.setBounds(591, 438, 29, 19);
			}
			{
				lblDhc = new Label(canvas, SWT.RIGHT);
				lblDhc.setText("DDC");
				if (!lrTab.getSelection()[0].getText().equalsIgnoreCase("UPD")) {
					lblDhc.setBounds(591, 468, 29, 19);
				} else {
					lblDhc.setBounds(591, 411, 29, 19);
				}
			}
			if (!lrTab.getSelection()[0].getText().equalsIgnoreCase("UPD")) {
				{
					label23 = new Label(canvas, SWT.RIGHT);
					label23.setText("IEC");
					label23.setBounds(797, 324, 29, 16);
				}
				{
					label136 = new Label(canvas, SWT.RIGHT);
					label136.setText("LC");
					label136.setBounds(783, 360, 39, 30);
				}
				{
					label137 = new Label(canvas, SWT.RIGHT);
					label137.setText("INS");

					label137.setBounds(799, 397, 22, 18);
				}

			} else {
				{
					lblOldFrt = new Label(canvas, SWT.RIGHT);
					lblOldFrt.setText("OLD Frt");
					lblOldFrt.setBounds(767, 324, 59, 16);
				}
				{
					lblPost = new Label(canvas, SWT.RIGHT);
					lblPost.setText("Post");
					lblPost.setBounds(783, 360, 39, 30);
				}
				{
					lblDemu = new Label(canvas, SWT.RIGHT);
					lblDemu.setText("Demu");
					lblDemu.setBounds(789, 397, 32, 18);
				}
			}

			{
				label138 = new Label(canvas, SWT.RIGHT);
				label138.setText("STAX");

				label138.setBounds(793, 423, 29, 25);
			}
			{
				label140 = new Label(canvas, SWT.RIGHT);
				label140.setText("Total");

				label140.setBounds(789, 460, 37, 30);
			}
			{
				btnRate = new Button(canvas, SWT.PUSH | SWT.CENTER);
				btnRate.setText("Calculate");
				btnRate.setBounds(588, 262, 63, 28);
				btnRate.addSelectionListener(new submitAction());
			}
			{
				txtBft = new Text(canvas, SWT.LEFT | SWT.BORDER);
				txtBft.setBounds(631, 324, 64, 25);
				txtBft.setTextLimit(10);
				txtBft.addVerifyListener(new NumericValidation());
				txtBft.addFocusListener(new submitAction());
				txtBft.setEnabled(false);
			}
			{
				txtLrc = new Text(canvas, SWT.LEFT | SWT.BORDER);
				txtLrc.setBounds(630, 353, 68, 25);
				txtLrc.setTextLimit(10);
				txtLrc.addVerifyListener(new NumericValidation());
				txtLrc.addFocusListener(new submitAction());
				txtLrc.setEnabled(false);
			}

			{
				txtDhc = new Text(canvas, SWT.LEFT | SWT.BORDER);

				txtDhc.setBounds(630, 382, 68, 25);
				txtDhc.setTextLimit(10);

				txtDhc.addVerifyListener(new NumericValidation());
				txtDhc.addFocusListener(new submitAction());
				txtDhc.setEnabled(false);
			}
			if (!lrTab.getSelection()[0].getText().equalsIgnoreCase("UPD")) {
				{
					btnccc = new Button(canvas, SWT.CHECK | SWT.LEFT);
					btnccc.setBounds(577, 406, 16, 26);
					btnccc.addSelectionListener(new submitAction());
				}
				{
					txtCcc = new Text(canvas, SWT.LEFT | SWT.BORDER);
					txtCcc.setBounds(630, 410, 68, 25);
					txtCcc.setTextLimit(10);
					txtCcc.addVerifyListener(new FloatLimitValidation());
					txtCcc.addFocusListener(new submitAction());
					txtCcc.setEnabled(false);

				}

				{
					lblmaxccc = new Label(canvas, SWT.LEFT);
					lblmaxccc.setText("Max");
					lblmaxccc.setBounds(698, 410, 22, 21);
				}
				{
					txtmaxccc = new Text(canvas, SWT.LEFT | SWT.BORDER);
					txtmaxccc.setBounds(724, 410, 41, 22);
					txtmaxccc.setEnabled(false);
				}

				{
					txtDcc = new Text(canvas, SWT.LEFT | SWT.BORDER);
					txtDcc.setBounds(630, 438, 68, 25);
					txtDcc.setTextLimit(10);
					txtDcc.addVerifyListener(new FloatLimitValidation());
					txtDcc.addFocusListener(new submitAction());
					txtDcc.setEnabled(false);
				}
				
				{
					txtDdc = new Text(canvas, SWT.LEFT | SWT.BORDER);
					txtDdc.setBounds(630, 467, 68, 25);
					txtDdc.setTextLimit(10);
					txtDdc.addVerifyListener(new NumericValidation());
					txtDdc.addFocusListener(new submitAction());
					txtDdc.setEnabled(false);
				}
			} else {
				
				{
					txtDdc = new Text(canvas, SWT.LEFT | SWT.BORDER);
					txtDdc.setBounds(630, 410, 68, 25);
					txtDdc.setTextLimit(10);
					txtDdc.addVerifyListener(new NumericValidation());
					txtDdc.addFocusListener(new submitAction());
					txtDdc.setEnabled(false);
				}
				
				{
					txtLc = new Text(canvas, SWT.LEFT | SWT.BORDER);
					txtLc.setBounds(630, 438, 68, 25);
					txtLc.setTextLimit(10);
					txtLc.addVerifyListener(new FloatLimitValidation());
					txtLc.addFocusListener(new submitAction());
					txtLc.setEnabled(false);

				}
			}
			

			if (!lrTab.getSelection()[0].getText().equalsIgnoreCase("UPD")) {
				{
					txtIec = new Text(canvas, SWT.LEFT | SWT.BORDER);
					txtIec.setBounds(836, 319, 80, 25);
					txtIec.setTextLimit(10);
					txtIec.addVerifyListener(new FloatLimitValidation());
					txtIec.addFocusListener(new submitAction());
					txtIec.setEnabled(false);

				}
				{
					txtLc = new Text(canvas, SWT.LEFT | SWT.BORDER);
					txtLc.setBounds(836, 355, 80, 25);
					txtLc.setTextLimit(10);
					txtLc.addVerifyListener(new FloatLimitValidation());
					txtLc.addFocusListener(new submitAction());
					txtLc.setEnabled(false);

				}
				{
					txtGsc = new Text(canvas, SWT.LEFT | SWT.BORDER);

					txtGsc.setBounds(836, 390, 80, 25);

					txtGsc.setTextLimit(10);
					txtGsc.addVerifyListener(new NumericValidation());
					txtGsc.addFocusListener(new submitAction());
					txtGsc.setEnabled(false);

				}
			} else {

				{
					txtOldFrt = new Text(canvas, SWT.LEFT | SWT.BORDER);
					txtOldFrt.setBounds(836, 319, 80, 25);
					txtOldFrt.setTextLimit(10);
					txtOldFrt.addVerifyListener(new FloatLimitValidation());
					txtOldFrt.addFocusListener(new submitAction());
					txtOldFrt.setEnabled(false);

				}
				{
					txtPost = new Text(canvas, SWT.LEFT | SWT.BORDER);
					txtPost.setBounds(836, 355, 80, 25);
					txtPost.setTextLimit(10);
					txtPost.addVerifyListener(new FloatLimitValidation());
					txtPost.addFocusListener(new submitAction());
					txtPost.setEnabled(false);

				}
				{
					txtDemu = new Text(canvas, SWT.LEFT | SWT.BORDER);

					txtDemu.setBounds(836, 390, 80, 25);

					txtDemu.setTextLimit(10);
					txtDemu.addVerifyListener(new NumericValidation());
					txtDemu.addFocusListener(new submitAction());
					txtDemu.setEnabled(false);

				}
			}
			{
				txtStax = new Text(canvas, SWT.LEFT | SWT.BORDER);

				txtStax.setBounds(836, 423, 80, 25);

				txtStax.setTextLimit(10);
				txtStax.addVerifyListener(new FloatLimitValidation());
				txtStax.addFocusListener(new submitAction());
				txtStax.setEnabled(false);

			}
			{
				btnStax = new Button(canvas, SWT.CHECK | SWT.LEFT);
				btnStax.setText("NO");
				btnStax.setBounds(917, 422, 38, 26);
				btnStax.addSelectionListener(new submitAction());
			}

			{
				txtTotal = new Text(canvas, SWT.LEFT | SWT.BORDER);

				txtTotal.setBounds(836, 458, 80, 25);

				txtTotal.setTextLimit(10);
				txtTotal.addVerifyListener(new FloatValidation());
				txtTotal.setEnabled(false);
			}

			{
				btnSubmit = new Button(canvas, SWT.PUSH | SWT.CENTER);
				btnSubmit.setText("Submit");
				btnSubmit.setBounds(864, 503, 75, 22);
				btnSubmit.addSelectionListener(new submitAction());
				btnSubmit.setEnabled(false);

			}
			{
				btnCancel = new Button(canvas, SWT.PUSH | SWT.CENTER);
				btnCancel.setText("Cancel");
				btnCancel.setBounds(864, 504, 75, 22);
				btnCancel.setVisible(false);
				btnCancel.addSelectionListener(new submitAction());
				btnCancel.setEnabled(false);

			}
			{
				btnPrint = new Button(canvas, SWT.PUSH | SWT.CENTER);
				btnPrint.setText("Print");
				btnPrint.setBounds(786, 502, 72, 22);
				btnPrint.addSelectionListener(new submitAction());
				btnPrint.setEnabled(false);
			}
			{
				btnSpecial = new Button(canvas, SWT.CHECK | SWT.LEFT);
				btnSpecial.setText("Special");
				btnSpecial.setBounds(420, 1, 60, 30);
				btnSpecial.addSelectionListener(new submitAction());
				btnSpecial.setVisible(false);
				int index = lrTab.getSelectionIndex();
				if (index == 2)
					btnSpecial.setVisible(false);

			}

			{
				label1 = new Label(canvas, SWT.SEPARATOR | SWT.HORIZONTAL);
				label1.setText("label1");
				label1.setBounds(280, 319, 104, 10);
			}
			{
				label2 = new Label(canvas, SWT.SEPARATOR);
				label2.setText("label2");
				label2.setBounds(277, 322, 9, 37);
			}
			{
				label14 = new Label(canvas, SWT.SEPARATOR);
				label14.setText("label2");
				label14.setBounds(380, 322, 12, 38);
			}
			{
				lblDimensions = new Label(canvas, SWT.NONE);
				lblDimensions.setText("Enter Dimensions \n of 1 article only");
				lblDimensions.setBounds(288, 329, 93, 30);
			}
			{
				lblUnit = new Label(canvas, SWT.NONE);
				lblUnit.setText("Unit");
				lblUnit.setBounds(12, 306, 38, 18);
			}

			{
				lblConsinortinno = new Label(canvas, SWT.NONE);
				lblConsinortinno.setText("Consignor's TIN No");
				lblConsinortinno.setBounds(5, 225, 93, 23);
			}
			{
				btnArtAdd = new Button(canvas, SWT.PUSH | SWT.CENTER);
				btnArtAdd.setBounds(511, 329, 25, 25);
				InputStream stream = LRComposite.class.getClassLoader()
						.getResourceAsStream(ADD_IMG);
				img = new Image(Display.getDefault(), stream);
				btnArtAdd.setImage(img);
				btnArtAdd.addSelectionListener(new submitAction());
				btnArtAdd.setEnabled(false);
			}

			{
				gpArticle = new Group(canvas, SWT.NONE);
				GridLayout gpArticleLayout = new GridLayout();
				gpArticleLayout.makeColumnsEqualWidth = true;
				gpArticle.setLayout(gpArticleLayout);
				gpArticle.setText("ArticleDetails");
				gpArticle.setBounds(5, 288, 563, 219);
			}

			if (!lrTab.getSelection()[0].getText().equalsIgnoreCase("UPD")) {
				{
					lbldc = new Label(canvas, SWT.NONE);
					lbldc.setText("DC");
					lbldc.setBounds(754, 302, 27, 18);
				}
			}
			{
				lblic = new Label(canvas, SWT.NONE);
				lblic.setText("IC");
				lblic.setBounds(717, 301, 28, 18);
			}
			{
				cbic = new Combo(canvas, SWT.READ_ONLY);
				cbic.setBounds(704, 322, 42, 21);
				cbic.addSelectionListener(new submitAction());
				cbic.setEnabled(false);

			}
			if (!lrTab.getSelection()[0].getText().equalsIgnoreCase("UPD")) {
				{
					cbdc = new Combo(canvas, SWT.READ_ONLY);
					cbdc.setBounds(749, 322, 44, 21);
					cbdc.addSelectionListener(new submitAction());
					cbdc.setEnabled(false);
				}
			}
			{
				group1 = new Group(canvas, SWT.NONE);
				GridLayout group1Layout = new GridLayout();
				group1Layout.makeColumnsEqualWidth = true;
				group1.setLayout(group1Layout);
				group1.setText("FrieghtDetails");
				group1.setBounds(574, 296, 377, 202);
			}
			{
				btnopenlr = new Button(canvas, SWT.CHECK | SWT.LEFT);
				btnopenlr.setText("Open");
				if (lrTab.getSelection()[0].getText().equalsIgnoreCase("UPD")) {
					btnopenlr.setBounds(304, 5, 60, 29);
				} else {
					btnopenlr.setBounds(354, -1, 60, 29);
				}
				btnopenlr.addSelectionListener(new submitAction());
			}
			{
				btnFOCLr = new Button(canvas, SWT.CHECK | SWT.LEFT);
				btnFOCLr.setText("FOC");
				btnFOCLr.setBounds(484, 2, 60, 25);
				btnFOCLr.addSelectionListener(new submitAction());
				int index = lrTab.getSelectionIndex();
				if (index != 1 && index != 5)
					btnFOCLr.setVisible(false);
			}
			{
				btnWeightRoundOff = new Button(canvas, SWT.CHECK | SWT.LEFT);
				btnWeightRoundOff.setText("WeightRoundOff");
				btnWeightRoundOff.setBounds(453, 511, 99, 20);
				btnWeightRoundOff.addSelectionListener(new submitAction());
				btnWeightRoundOff.setSelection(true);

			}

		}
		return canvas;
	}

	/**
	 * Method to populate Article Type
	 */
	private void populateArticleTypes() {

		try {
			articles = handler.getArticleTypes();

		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}

	/**
	 * Method to validate the LR Number 1. The first character should be
	 * uppercase and should not be digit 2. The rest of the characters should be
	 * digit 3. The second character should not be '0'
	 */

	public boolean isValidNewLRNumberFormat() {
		String checklr = null;
		if (!lrTab.getSelection()[0].getText().equals("UPD")) {
			checklr = txtLrno.getText();
		} else {
			int index = cbNewLrno.getSelectionIndex();
			checklr = cbNewLrno.getItem(index);
		}
		int len = checklr.length();
		boolean isValid = true;

		if (len < 2)

			isValid = false;
		else {
			char first = checklr.charAt(0);

			char newchar = Character.toUpperCase(first);
			checklr = checklr.replace(first, newchar);
			// The first character should be Character and it should be upper
			// case
			if (Character.isDigit(first)) {
				isValid = false;
			} else {
				// The rest of the character should be digits
				for (int i = 1; i < len; i++) {
					if (!Character.isDigit(checklr.charAt(i))) {
						isValid = false;
						break;
					}
				}
				if (isValid) {
					// The second character should not be zero
					if (checklr.charAt(1) == '0')
						isValid = false;
					else {
						/*
						 * if(!lrTab.getSelection()[0].getText().equals("UPD")){
						 * txtLrno.setText(checklr); } else{
						 * cbNewLrno.setText(checklr); }
						 */

					}

				}
			}
		}

		return isValid;
	}

	public boolean isValidLRNumberFormat() {
		String checklr = null;
		if (!lrTab.getSelection()[0].getText().equals("UPD")) {
			checklr = txtLrno.getText();
		} else {
			checklr = txtOldlrno.getText();
		}
		int len = checklr.length();
		boolean isValid = true;

		if (len < 2)

			isValid = false;
		else {
			char first = checklr.charAt(0);

			char newchar = Character.toUpperCase(first);
			checklr = checklr.replace(first, newchar);
			// The first character should be Character and it should be upper
			// case
			if (Character.isDigit(first)) {
				isValid = false;
			} else {
				// The rest of the character should be digits
				for (int i = 1; i < len; i++) {
					if (!Character.isDigit(checklr.charAt(i))) {
						isValid = false;
						break;
					}
				}
				if (isValid) {
					// The second character should not be zero
					if (checklr.charAt(1) == '0')
						isValid = false;
					else {
						if (!lrTab.getSelection()[0].getText().equals("UPD")) {
							txtLrno.setText(checklr);
						} else {
							txtOldlrno.setText(checklr);
						}

					}

				}
			}
		}

		return isValid;
	}

	/**
	 * 
	 * @author Naruto1
	 * 
	 */
	class LrKeyListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {

			String selectedText = cbTo.getText();
			if (e.keyCode == SWT.ARROW_UP || e.keyCode == SWT.ARROW_DOWN
					|| e.keyCode == SWT.CR)
				return;

			if (e.character == '\b') {
				int len = selectedText.length();

				if (len > 1)
					selectedText = selectedText.substring(0, len - 2);
				else
					selectedText = "";

				cbTo.remove(0, cbTo.getItemCount() - 1);
				populateDestStationCodes();
			} else if (e.keyCode == 32 || (e.keyCode > 64 && e.keyCode < 91)
					|| (e.keyCode > 96 && e.keyCode < 123)) {
				selectedText += e.character;
			} else {
				e.doit = false;
				return;
			}

			if (selectedText.length() > 0) {
				selectedText = selectedText.toLowerCase();

				String[] items = cbTo.getItems();
				int len = items.length;

				int startIndex = -1;
				for (int i = 0; i < len; i++) {
					String temp = items[i].toLowerCase();
					if (temp.contains(selectedText)) {
						startIndex = i;
						break;
					}
				}

				if (startIndex == -1) {
					e.doit = false;
				} else {
					cbTo.remove(0, len - 1);

					for (int i = startIndex; i < len; i++) {
						String temp = items[i].toLowerCase();
						if (temp.contains(selectedText)) {
							cbTo.add(items[i]);
						}
					}
					if (selectedText.length() == 1) {
						showPopup(cbTo, true);
					}
				}
			}
		}

		public void keyReleased(KeyEvent e) {
			String temp = cbTo.getText();
			int len = temp.length();
			if (len == 0) {
				cbTo.remove(0, cbTo.getItemCount() - 1);
				populateDestStationCodes();
			}
		}
	}

	/**
	 * Class for focus Liste
	 * 
	 * @author kibaitachi
	 * 
	 */
	public class lrvalidation implements org.eclipse.swt.events.FocusListener {

		public void focusGained(FocusEvent arg0) {

		}

		/**
		 * 
		 */

		public void focusLost(FocusEvent e) {

			Object source = e.getSource();
			String stationcode = txtFrom.getText();
			int index = stationcode.indexOf('-');
			if (index > -1)
				stationcode = stationcode.substring(index + 2);
			String selectedTab = lrTab.getSelection()[0].getText();
			String lrno = null;
			if (selectedTab.equalsIgnoreCase("UPD")) {
				int index1 = cbNewLrno.getSelectionIndex();
				lrno = cbNewLrno.getItem(index1);
			} else {
				lrno = txtLrno.getText();
			}
			if ((txtLrno == source || cbNewLrno == source) && lrno != "") {
				if (!isValidLRNumberFormat()) {
					isValidLRNO = false;
					btnSubmit.setEnabled(false);
					cbTo.setFocus();
					displayError("LR Number format is not valid");

				} else if ((!selectedTab.equalsIgnoreCase("LR VIEW") && !selectedTab
						.equalsIgnoreCase("LR CANCEL"))
						&& !isAllocatedLRNumber(lrno, selectedTab, stationcode)) {
					isValidLRNO = false;
					btnSubmit.setEnabled(false);
					cbTo.setFocus();
					displayError("Invalid LR Number");

				} else {
					isValidLRNO = true;
					if (isReadyToSubmit)
						btnSubmit.setEnabled(true);
				}

			} else {
				btnSubmit.setEnabled(false);
			}
		}

	}

	public class Oldlrvalidation implements
			org.eclipse.swt.events.FocusListener {

		public void focusGained(FocusEvent arg0) {

		}

		/**
		 * 
		 */

		public void focusLost(FocusEvent e) {

			Object source = e.getSource();
			String stationcode = txtFrom.getText();
			int index = stationcode.indexOf('-');
			if (index > -1)
				stationcode = stationcode.substring(index + 2);
			String selectedTab = lrTab.getSelection()[0].getText();
			String lrno = txtOldlrno.getText();
			if (txtOldlrno == source && lrno != "") {
				if (!isValidLRNumberFormat()) {
					isValidLRNO = false;
					btnSubmit.setEnabled(false);
					cbTo.setFocus();
					displayError("LR Number format is not valid");

				}/*
					 * else if ((!selectedTab.equalsIgnoreCase("LR VIEW") &&
					 * !selectedTab .equalsIgnoreCase("LR CANCEL")) &&
					 * !isAllocatedLRNumber(lrno, selectedTab, stationcode)) {
					 * isValidLRNO = false; btnSubmit.setEnabled(false);
					 * cbTo.setFocus(); displayError("Invalid LR Number"); }
					 */
				else {
					if (selectedTab.equalsIgnoreCase("UPD")) {

						String Oldlrstatus = null;
						try {
							BookingDTO[] book = handler.getOldlrstatus(lrno,
									stationcode);
							int size = book.length;
							
							if (book[0].getLrno().equalsIgnoreCase("Arrived") && ! book[0].getFrom().equalsIgnoreCase(stationcode) ) {
								long diff1 = 0;
								float inwDays = 0;
								DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
								// Checking inward days
								BeanUtil util = BeanUtil.getInstance();
						Date today = dateFormat.parse(util
								.getServerDate());
						Date inTime = book[0].getDate();
						System.out.println(inTime);
						if (inTime != null) {
							diff1 = today.getTime()
									- inTime.getTime();
							inwDays = (diff1 / (1000 * 60 * 60 * 24));
						}

						if (inTime != null && inwDays >= 30 && btnToUPD .getSelection()) {
							
							isValidLRNO = true;
							handleUPDView();
							isReadyToSubmit = true;
							if (isReadyToSubmit)
								btnSubmit.setEnabled(true);
							
							/*cbConsignorName.setText(book[0].getConsignorName());
							cbConsigneeName.setText(book[0].getConsigneeName());
							txtConsignorCST.setText(book[0].getConsignor_CST());
							txtConsigneeCST.setText(book[0].getConsignee_GST());
							txtConsignorPhone.setText(book[0].getCnorPhone());
							txtConsigneePhone.setText(book[0].getCneePhone());
							txtCnorLandline.setText(book[0].getCnorLandLine());
							txtCneeLandline.setText(book[0].getCneeLandLine());
							txtConsignor.setText(book[0].getConsignor_address());
							txtConsignee.setText(book[0].getConsignee_address());
							cbConsignorName.setEnabled(false);
							cbConsigneeName.setEnabled(false);
							txtConsignorCST.setEnabled(false);
							txtConsigneeCST.setEnabled(false);
							txtConsignorPhone.setEnabled(false);
							txtConsigneePhone.setEnabled(false);
							txtCnorLandline.setEnabled(false);
							txtCneeLandline.setEnabled(false);
							txtConsignor.setEnabled(false);
							txtConsignee.setEnabled(false);
							btnSaveConsignorDetails.setEnabled(false);
							btnSaveConsigneeDetails.setEnabled(false);
							clearArticleTable();
							txteffchargedCWT.setText(EMPTY_STRING);
							findBookingType();*/
							
													
							
						}else if(btnRebooklr.getSelection()){
							isValidLRNO = true;
							if (isReadyToSubmit)
								btnSubmit.setEnabled(true);
								cbTo.setFocus();
						}else{
							 isValidLRNO = false;
								btnSubmit.setEnabled(false);
								cbTo.setFocus();
								displayError("Not a valid UPD LR");
						}
								
					}
							/*
								 * else
								 * if(book[0].getFrom().equalsIgnoreCase(stationcode)){
								 * isValidLRNO = false;
								 * btnSubmit.setEnabled(false); cbTo.setFocus();
								 * displayError("Same station LR can't be
								 * Rebooked"); }
								 */else {
									 isValidLRNO = false;
										btnSubmit.setEnabled(false);
										cbTo.setFocus();
										displayError("Not a valid UPD LR");
							}

						} catch (Exception e1) {
							e1.printStackTrace();
							// TODO: handle exception
						}

					} else {
						isValidLRNO = true;
						if (isReadyToSubmit)
							btnSubmit.setEnabled(true);
					}

				}

			} else {
				btnSubmit.setEnabled(false);
			}
		}
		
		private void handleUPDView() {
			if (!isValidLRNumberFormat()) {
				displayError("LR Number format is not valid");
			} else {
				String lrno = txtOldlrno.getText();
				if (lrno.length() > 0) {
					try {
						BookingDTO booking = handler.getBookingDetail(lrno);
						if (null != booking) {
							populateUPDDetail(booking);
							/*if (booking.getFrom().equalsIgnoreCase(
									handler.getStationCode())
									|| (handler.isBranch() && BeanUtil
											.getInstance().isAdmin())) {
								populateBookingDetail(booking);

								if (handler.getBranch(handler.getStationCode())
										.equalsIgnoreCase(
												handler.getBranch(booking
														.getFrom())))
									btnPrint.setEnabled(true);
								//btnCancel.setEnabled(true);
								// On successful population, the view button
								// needs to be disabled and the user can
								// cancel the record and hence the cancel button
								// needs to be enabled.
								btnView.setEnabled(false);
								if (booking.getStatus()) { // Record Cancelled
									displayError("LR Cancelled");
									btnCancel.setEnabled(false);
								} else {
									btnCancel.setEnabled(true);
								}

							} else {
								displayError("Not Allowed to view Other Stations Record");
							}*/
						}
					} catch (BusinessException business) {
						displayError(business.getResponseMessage());
					} catch (Exception exception) {
						exception.printStackTrace();
						displayError("Error occurred while retrieving the records");
					}
				}
			}
		}
		
		
		private void populateUPDDetail(BookingDTO dto) {

			isViewrateType = 0;
			isViewrateType = dto.getRate_type();
			String namecode = "";
			cancelType = null;
			// Populate the record
			cancelType = dto.getType();
			DateFormat dateformate = new SimpleDateFormat("dd-MM-yyyy");
			
			txtDate.setText(dateformate.format(dto.getDate()));
			// txtDate.setEnabled(false);

			txtBft.setText(String.valueOf((int) dto.getBft()));
			txtBft.setEnabled(false);

			/*
			 * txtChargedWeight.setText(Float.toString(dto.getCharged_weight()));
			 * txtChargedWeight.setEnabled(false);
			 */

			if (dto.getConsignorName() != null)
				cbConsignorName.setText(dto.getConsignorName());
			cbConsignorName.setEnabled(false);

			if (dto.getConsigneeName() != null)
				cbConsigneeName.setText(dto.getConsigneeName());
			cbConsigneeName.setEnabled(false);

			if (dto.getConsignee_address() != null)
				txtConsignee.setText(dto.getConsignee_address());
			txtConsignee.setEnabled(false);

			if (dto.getConsignee_GST() != null)
				txtConsigneeCST.setText(dto.getConsignee_GST());
			txtConsigneeCST.setEnabled(false);

			if (dto.getConsignor_address() != null)
				txtConsignor.setText(dto.getConsignor_address());
			txtConsignor.setEnabled(false);

			if (dto.getConsignor_CST() != null)
				txtConsignorCST.setText(dto.getConsignor_CST());
			txtConsignorCST.setEnabled(false);

			if (dto.getCnorPhone() != null)
				txtConsignorPhone.setText(dto.getCnorPhone());
			txtConsignorPhone.setEnabled(false);

			if (dto.getCneePhone() != null)
				txtConsigneePhone.setText(dto.getCneePhone());
			txtConsigneePhone.setEnabled(false);

			if (dto.getCnorLandLine() != null)
				txtCnorLandline.setText(dto.getCnorLandLine());
			txtCnorLandline.setEnabled(false);

			if (dto.getCneeLandLine() != null)
				txtCneeLandline.setText(dto.getCneeLandLine());
			txtCneeLandline.setEnabled(false);

			txtDdc.setText(String.valueOf((Float) dto.getDdc()));
			txtDdc.setEnabled(false);
			
			txtLc.setText(String.valueOf((Float) dto.getLc()));
			txtLc.setEnabled(false);
			if(dto.getType().equalsIgnoreCase("TOPAY")){
				txtOldFrt.setText(String.valueOf((Float) dto.getOldLrTotal()));
				txtOldFrt.setEnabled(false);
			}else{
				txtOldFrt.setText("0.0");
				txtOldFrt.setEnabled(false);
			}
			
			txtDhc.setText(String.valueOf((int) dto.getDhc()));
			txtDhc.setEnabled(false);

			txtLrc.setText(String.valueOf((int) dto.getLrc()));
			txtLrc.setEnabled(false);

			txtStax.setText(Float.toString(dto.getStax()));
			txtStax.setEnabled(false);
			
			txtTotal.setText(Float.toString(dto.getTotal()));
			txtTotal.setEnabled(false);

			btnRate.setEnabled(false);
			cbic.setEnabled(false);

			btnStax.setEnabled(false);
			btnSpecial.setEnabled(false);
			
			
			

		
			if (null != tblArticle) {
				tblArticle.removeAll();
				ArticleDTO[] artdto = dto.getArticledto();
				int len = 0;
				if (null != artdto) {
					len = artdto.length;

					for (int j = 0; j < len; j++) {
						TableItem item = new TableItem(tblArticle, SWT.NONE);
						item.setText(0, artdto[j].getArticleName());
						item
								.setText(1, String.valueOf(artdto[j]
										.getNoOfArticles()));
						item
								.setText(2, String.valueOf(artdto[j]
										.getArticleValue()));
						item
								.setText(3, String.valueOf(artdto[j]
										.getActualWeight()));
						item.setText(4, String.valueOf(artdto[j].getArt_length()));
						item.setText(5, String.valueOf(artdto[j].getArt_breath()));
						item.setText(6, String.valueOf(artdto[j].getArt_height()));
						item.setText(7, String
								.valueOf(artdto[j].getChargedWeight()));
						item.setText(8, String.valueOf(artdto[j].getArticleDesc()));

					}
					int unitindex = dto.getArt_unit();
					if (unitindex == 0)
						cbUnit.setText("Feet");
					else if (unitindex == 1)
						cbUnit.setText("Inches");
					else if (unitindex == 2)
						cbUnit.setText("Cm");

				} else {
					TableItem item = new TableItem(tblArticle, SWT.NONE);
					item.setText(0, String.valueOf(dto.getArticle_id()));
					item.setText(1, String.valueOf(dto.getNo_of_articles()));
					item.setText(2, String.valueOf(dto.getArticle_value()));
					item.setText(3, String.valueOf(dto.getActual_weight()));
					item.setText(4, String.valueOf(EMPTY_STRING));
					item.setText(5, String.valueOf(EMPTY_STRING));
					item.setText(6, String.valueOf(EMPTY_STRING));
					item.setText(7, String.valueOf(dto.getCharged_weight()));
					item.setText(8, String.valueOf(dto.getArticle_description()));
				}
				cbUnit.setEnabled(false);
			}

			// Total CW
			txteffchargedCWT.setText(String.valueOf(dto.getTotal_charged_wt()));
		}


	}

	/**
	 * 
	 * @return
	 */
	private BookingDTO buildDTO() {
		String selectedTab = lrTab.getSelection()[0].getText();
		booking = new BookingDTO();
		if (selectedTab.equals("UPD")) {
			int index = cbNewLrno.getSelectionIndex();
			booking.setLrno(cbNewLrno.getItem(index));
			booking.setOldLrno(txtOldlrno.getText());

			if (btnToUPD.getSelection()) {
				booking.setIsUpd("ToUPD");
			} else {
				booking.setIsUpd("Rebook");
			}
		} else {
			booking.setLrno(txtLrno.getText());
			booking.setIsUpd("lr");
		}

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		int index1 = -1;
		try {
			// TBR - comment needs to be removed

			String temp = null;
			//if (!selectedTab.equalsIgnoreCase("UPD")) {
				temp = txtDdc.getText();
				if (temp.length() > 0)
					booking.setDdc(Float.parseFloat(temp));
			//}

			boolean od = true;
			if (booking.getDdc() != 0) {
				od = false;
				booking.setDuplicate("Door Delivery");
			} else {
				booking.setDuplicate("Office Delivery");
			}

			if (lrType[5].equals(selectedTab)) {
				booking.setType(lblTopay.getText());
				booking.setFrom(txtFrom.getText());
				if (od)
					booking.setDuplicate("Duplicate         Office Delivery");
				else
					booking.setDuplicate("Duplicate         Door Delivery");
				String dest = cbTo.getText();
				booking.setTo(dest);
			} else {
				if (selectedTab.equals("UPD")) {
					booking.setType("TOPAY");
				} else {
					booking.setType(lrTab.getSelection()[0].getText());
				}

				booking.setFrom(handler.getStationCode());

				String dest = cbTo.getText();
				int index = dest.indexOf("-");

				if (index > -1)
					dest = dest.substring(index + 2, dest.length());

				booking.setTo(dest);
			}

			StationsDTO[] stations = handler.getStations();

			int size = stations.length;

			String fromStation = booking.getFrom();
			String toStation = booking.getTo();

			int index = fromStation.indexOf("-");
			fromStation = fromStation.substring(index + 1);

			index = toStation.indexOf("-");
			toStation = toStation.substring(index + 1);

			for (int i = 0; i < size; i++) {
				boolean from = false;

				boolean to = false;

				if (stations[i].getId().equalsIgnoreCase(fromStation)) {
					booking.setFromMobile(stations[i].getMobile() + ", "
							+ stations[i].getPhone1());
					from = true;
				} else if (stations[i].getId().equalsIgnoreCase(toStation)) {
					booking.setToMobile(stations[i].getMobile() + ", "
							+ stations[i].getPhone1());
					to = true;
				}
				if (to && from)
					break;
			}

			booking.setCreatedby(handler.getCreatedBy());
			if (selectedTab.equals("UPD")) {
				int ind = cbNewLrno.getSelectionIndex();
				booking.setLrno(cbNewLrno.getItem(ind));
			} else {
				booking.setLrno(txtLrno.getText());
			}

			index1 = cbConsignorName.getSelectionIndex();
			String consignorName = null;

			if (index1 > -1) {

				consignorName = cbConsignorName.getItem(index1);
			} else {
				consignorName = cbConsignorName.getText();
				if (consignorName.trim().length() == 0)
					consignorName = null;
			}
			booking.setConsignorName(consignorName);

			index1 = cbConsigneeName.getSelectionIndex();
			String consigneeName = null;

			if (index1 > -1) {

				consigneeName = cbConsigneeName.getItem(index1);
			} else {
				consigneeName = cbConsigneeName.getText();
				if (consigneeName.trim().length() == 0)
					consigneeName = null;
			}

			booking.setConsigneeName(consigneeName);
			booking.setConsignor_address(txtConsignor.getText());
			booking.setConsignee_address(txtConsignee.getText());
			booking.setDate(dateFormat.parse(txtDate.getText()));

			temp = txtConsigneeCST.getText();

			if (temp.length() > 0)
				booking.setConsignee_GST(temp);
			temp = txtConsignorCST.getText();
			if (temp.length() > 0)
				booking.setConsignor_CST(temp);

			temp = txtBft.getText();
			if (temp.length() > 0)
				booking.setBft(Float.parseFloat(temp));

			temp = txtLrc.getText();
			if (temp.length() > 0)
				booking.setLrc(Float.parseFloat(temp));

			temp = txtDhc.getText();
			if (temp.length() > 0)
				booking.setDhc(Float.parseFloat(temp));
			if (selectedTab.equals("UPD")) {
				temp = txtOldFrt.getText();
				if (temp.length() > 0)
					booking.setOldLrTotal(Float.parseFloat(temp));

				temp = txtDemu.getText();
				if (temp.length() > 0)
					booking.setDemu(Float.parseFloat(temp));

				temp = txtPost.getText();
				if (temp.length() > 0)
					booking.setPost(Float.parseFloat(temp));
			} else {
				temp = txtCcc.getText();
				if (temp.length() > 0)
					booking.setCcc(Float.parseFloat(temp));

				temp = txtDcc.getText();
				if (temp.length() > 0)
					booking.setDcc(Float.parseFloat(temp));

				temp = txtIec.getText();
				if (temp.length() > 0)
					booking.setIec(Float.parseFloat(temp));

				temp = txtGsc.getText();
				if (temp.length() > 0)
					booking.setGsc(Float.parseFloat(temp));

			}

			temp = txtLc.getText();
			if (temp.length() > 0)
				booking.setLc(Float.parseFloat(temp));

			temp = txtStax.getText();
			if (temp.length() > 0)
				booking.setStax(Float.parseFloat(txtStax.getText()));

			if (btnStax != null) {
				booking.setStaxNo(btnStax.getSelection());
			}

			temp = txtTotal.getText();
			if (temp.length() > 0)
				booking.setTotal(Float.parseFloat(temp));

			temp = txtConsignorPhone.getText();
			if (temp.length() > 0)
				booking.setCnorPhone(temp);

			temp = txtConsigneePhone.getText();
			if (temp.length() > 0)
				booking.setCneePhone(temp);

			temp = txtCnorLandline.getText();
			if (temp.length() > 0)
				booking.setCnorLandLine(temp);

			temp = txtCneeLandline.getText();
			if (temp.length() > 0)
				booking.setCneeLandLine(temp);

			ArticleDTO[] artdto = getArticleDetails();
			if (null != artdto) {
				int noa = 0;
				float artvalue = 0;
				float actualweight = 0;
				float chargedweight = 0;
				float totalChargedWt = 0;

				String arttype = null;
				String Articledesc = null;
				printarticles = null;
				printarticles_desc = null;
				for (int i = 0; i < artdto.length; i++) {
					noa = noa + artdto[i].getNoOfArticles();
					float tempartvalue = 0;
					try {
						tempartvalue = Float.parseFloat(artdto[i]
								.getArticleValue());
					} catch (Exception exception) {
						tempartvalue = 0;
					}
					artvalue = artvalue + tempartvalue;
					actualweight = actualweight + artdto[i].getActualWeight();
					chargedweight = chargedweight
							+ artdto[i].getChargedWeight();
					arttype = artdto[0].getArticleName();

					// Total Charged weight text
					if (!txteffchargedCWT.getText().equals(EMPTY_STRING))
						totalChargedWt = Float.parseFloat(txteffchargedCWT
								.getText());

					if (null == printarticles)
						printarticles = artdto[i].getArticleName();
					else
						printarticles = printarticles + ","
								+ artdto[i].getArticleName();

					Articledesc = artdto[0].getArticleDesc();

					if (null == printarticles_desc)
						printarticles_desc = artdto[i].getArticleDesc();
					else
						printarticles_desc = printarticles_desc + ","
								+ artdto[i].getArticleDesc();

					Articledesc = artdto[0].getArticleDesc();

				}
				int unitindex = cbUnit.getSelectionIndex();
				if (unitindex > -1) {
					booking.setArt_unit(unitindex);

				}
				booking.setArticle_description(Articledesc);
				booking.setArticle_id(getSelectedArticleId(arttype));
				booking.setActual_weight(actualweight);
				booking.setArticle_value(artvalue);
				booking.setCharged_weight(chargedweight);
				booking.setNo_of_articles(noa);
				booking.setActual_bft(actualBft);

				if (null != booking && null != btnopenlr
						&& btnopenlr.getSelection()) {
					booking.setRate_type(7 + rate_type);// Open Lr Type

				}
				booking.setRate_type(rate_type);
				booking.setDdcFree(ddcfree);
				booking.setTotal_charged_wt(totalChargedWt);

				if (rate_type == 4 || rate_type == 5) {
					// SMS notify
					int sms = 0;
					int cnor = 0;
					int cnee = 0;

					if (txtCnorSMS.getText() != EMPTY_STRING)
						cnor = Integer.parseInt(txtCnorSMS.getText());

					if (txtCneeSMS.getText() != EMPTY_STRING)
						cnee = Integer.parseInt(txtCneeSMS.getText());

					if (cnor == 0 && cnee == 1) {
						booking.setSmsNotify(1);
					} else if (cnor == 1 && cnee == 0) {
						booking.setSmsNotify(2);
					} else if (cnor == 1 && cnee == 1) {
						booking.setSmsNotify(3);
					}
				}

			}

			booking.setArticledto(artdto);

		} catch (Exception exception) {
			exception.printStackTrace();
			booking = null;
		}

		return booking;
	}

	/**
	 * 
	 * @param type
	 * @return
	 */
	private int getSelectedArticleId(String type) {

		int typeid = 0;

		try {
			ArticleDTO[] articles = handler.getArticleTypes();

			if (null != articles) {
				int len = articles.length;
				for (int i = 0; i < len; i++) {
					if (type.equals(articles[i].getType())) {
						typeid = articles[i].getArticleId();
						break;
					}
				}
			}
		} catch (Exception exception) {

		}

		return typeid;
	}

	/**
	 * Method to get Article details
	 * 
	 * @return
	 */
	private ArticleDTO[] getArticleDetails() {
		try {
			ArrayList<ArticleDTO> templist = null;
			if (null != artlist) {
				int len = artlist.size();
				if (len > 0) {
					templist = new ArrayList<ArticleDTO>();

					for (int i = 0; i < len; i++) {
						ArticleDTO dto = new ArticleDTO();
						String artname = null;
						int index = artlist.get(i).getCbname()
								.getSelectionIndex();
						if (index > -1)
							artname = artlist.get(i).getCbname().getItem(index);
						else {
							if (!artlist.get(i).getCbname().getText()
									.equals("")) {
								artname = artlist.get(i).getCbname().getText();
							}
						}

						dto.setArticleName(artname);

						String temp = null;

						temp = artlist.get(i).getTxtnoofarticle().getText();

						if (!temp.equalsIgnoreCase(EMPTY_STRING))
							dto.setNoOfArticles(Integer.parseInt(temp));
						temp = null;

						temp = artlist.get(i).getTxtartvalue().getText();

						// if (!temp.equalsIgnoreCase(EMPTY_STRING))
						dto.setArticleValue(temp);

						temp = null;

						temp = artlist.get(i).getTxtweight().getText();

						if (!temp.equalsIgnoreCase(EMPTY_STRING))
							dto.setActualWeight(Float.parseFloat(temp));

						String lenth = null;
						String breath = null;
						String heiht = null;
						float l = 0;
						float b = 0;
						float h = 0;

						lenth = artlist.get(i).getTxtlength().getText();
						breath = artlist.get(i).getTxtbreadth().getText();
						heiht = artlist.get(i).getTxtheight().getText();

						if (!lenth.equalsIgnoreCase(EMPTY_STRING)) {
							l = Float.parseFloat(lenth);
							dto.setArt_length(Float.parseFloat(lenth));
						}

						if (!breath.equalsIgnoreCase(EMPTY_STRING)) {
							b = Float.parseFloat(breath);
							dto.setArt_breath(Float.parseFloat(breath));
						}

						if (!heiht.equalsIgnoreCase(EMPTY_STRING)) {
							h = Float.parseFloat(heiht);
							dto.setArt_height(Float.parseFloat(heiht));
						}

						float volume = l * b * h;
						// Check the Unit here
						dto.setVolumetricWeight(volume);

						temp = null;
						temp = artlist.get(i).getTxtchargeweight().getText();

						if (!temp.equalsIgnoreCase(EMPTY_STRING))
							dto.setChargedWeight(Float.parseFloat(temp));

						temp = null;

						temp = artlist.get(i).getTxtdesc().getText();
						// if (!temp.equalsIgnoreCase(EMPTY_STRING))
						dto.setArticleDesc(temp);

						templist.add(dto);

					}
				} else {

					if (null != tblArticle) {
						TableItem[] items = tblArticle.getItems();
						len = items.length;
						if (len > 0) {
							templist = new ArrayList<ArticleDTO>();
							String value = null;
							for (int b = 0; b < len; b++) {
								ArticleDTO dto = new ArticleDTO();

								dto.setArticleName(items[b].getText(0));

								value = items[b].getText(1);
								if (!value.equals(EMPTY_STRING))
									dto
											.setNoOfArticles(Integer
													.parseInt(value));
								value = items[b].getText(2);
								if (!value.equals(EMPTY_STRING))
									dto.setArticleValue((value));
								value = items[b].getText(3);
								if (!value.equals(EMPTY_STRING))
									dto
											.setActualWeight(Float
													.parseFloat(value));
								value = items[b].getText(4);
								if (!value.equals(EMPTY_STRING))
									dto.setArt_length(Float.parseFloat(value));
								value = items[b].getText(5);
								if (!value.equals(EMPTY_STRING))
									dto.setArt_breath(Float.parseFloat(value));
								value = items[b].getText(6);
								if (!value.equals(EMPTY_STRING))
									dto.setArt_height(Float.parseFloat(value));
								value = items[b].getText(7);
								if (!value.equals(EMPTY_STRING))
									dto.setChargedWeight(Float
											.parseFloat(value));
								dto.setArticleDesc(items[b].getText(8));
								templist.add(dto);
							}
						}
					}

				}
			}
			int size = templist.size();
			if (size > 0)
				return templist.toArray(new ArticleDTO[size]);

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Method to validate the captured Data
	 * 
	 * @param booking
	 * @return
	 */
	private boolean validateData(BookingDTO booking) {
		// errorLabel.setText("");

		if (null != btnopenlr && !btnopenlr.getSelection())
			if (!isValidCCValue()) {
				return false;
			}

		String stationcode = txtFrom.getText().trim();
		int index = stationcode.indexOf('-');
		if (index > -1)
			stationcode = stationcode.substring(index + 2);
		String selectedTab = lrTab.getSelection()[0].getText().trim();
		String lrno = null;
		String tabName = null;
		if (selectedTab.equalsIgnoreCase("UPD")) {
			int inde = cbNewLrno.getSelectionIndex();
			lrno = cbNewLrno.getItem(inde);
			tabName = "TOPAY";
			System.out.println("lrno-->" + lrno);
		} else {
			lrno = txtLrno.getText();
			tabName = lrTab.getSelection()[0].getText().trim();
		}

		if (!isValidNewLRNumberFormat()) {
			displayError("LR Number format is not valid");
			return false;
		} else if (!isAllocatedLRNumber(lrno, tabName, stationcode)) {
			System.out.println("error");
			displayError("Invalid LR Number");
			return false;
		}

		else {

			String param = "Mandatory Parameter ";

			if (booking.getLrno().length() == 0)
				param = param + "LrNumber,";
			if (booking.getArticledto().length == 0)
				param = param + "Articles Details,";
			if (booking.getTo().length() == 0)
				param = param + "To Station,";
			if (null == booking.getConsigneeName()
					|| booking.getConsigneeName().length() == 0)
				param = param + "ConsigneeName,";
			if (booking.getConsignee_address().length() == 0)
				param = param + "Consignee Address,";
			if (null == booking.getConsignorName()
					|| booking.getConsignorName().length() == 0)
				param = param + "ConsignorName,";
			if (booking.getConsignor_address().length() == 0)
				param = param + "Consignor Address,";

			if (null == booking.getCnorPhone()
					|| booking.getCnorPhone().length() == 0)
				param = param + "Consignor Phone Number,";
			if (booking.getCneePhone() == null
					|| booking.getCneePhone().length() == 0)
				param = param + "Consignee Phone Number,";

			if (booking.getCnorLandLine() == null
					|| booking.getCnorLandLine().length() == 0)
				param = param + "Consignor Land Line Number,";

			if (booking.getCneeLandLine() == null
					|| booking.getCneeLandLine().length() == 0)
				param = param + "Consignee Land Line Number,";

			if (booking.getLrno().length() == 0
					|| booking.getArticledto().length == 0
					|| booking.getTo().length() == 0
					|| booking.getConsigneeName() == null
					|| booking.getConsigneeName().length() == 0
					|| booking.getConsignee_address().length() == 0
					|| booking.getConsignorName() == null
					|| booking.getConsignorName().length() == 0
					|| booking.getConsignor_address().length() == 0
					|| booking.getCnorPhone() == null
					|| booking.getCnorPhone().length() == 0
					|| booking.getCneePhone() == null
					|| booking.getCneePhone().length() == 0
					|| booking.getCnorLandLine() == null
					|| booking.getCnorLandLine().length() == 0
					|| booking.getCneeLandLine() == null
					|| booking.getCneeLandLine().length() == 0

			) {
				int last = param.lastIndexOf(",");
				if (last > -1) {
					param = param.substring(0, last);
				}

				displayError(param + " is(are) missing");
				return false;
			}
		}

		return true;
	}

	/**
	 * 
	 * @return
	 */
	private boolean isValidCCValue() {
		try {
			String selectedTab = lrTab.getSelection()[0].getText();
			if ((null == quotationID)
					&& booking_type != 4
					&& (lrType[0].equals(selectedTab) || lrType[1]
							.equals(selectedTab))) {
				if (null != txtBft) {
					float bft = 0;
					if (!txtBft.getText().equalsIgnoreCase(EMPTY_STRING))
						bft = Float.parseFloat(txtBft.getText());

					float cc = 0;
					if (!txtCcc.getText().equalsIgnoreCase(EMPTY_STRING))
						cc = Float.parseFloat(txtCcc.getText());

					int limit = getCCLimit();

					if (limit == 0) {
						txtCcc.setText("0.0");
						txtmaxccc.setText("");
						txtCcc.setEnabled(false);
						btnccc.setEnabled(false);
						btnccc.setVisible(false);

						return true;
					} else if (limit == -1) {
						txtCcc.setEnabled(true);
						btnccc.setVisible(true);
						txtmaxccc.setText("");
						// txtCcc.setText("0.0");

					} else {

						if (isMixedCommodity == true && isCCCommodity == false) {

							if (cc > (mixedCommodityValue * limit / 100)) {
								displayError("The CC Value should not exceed "
										+ mixedCommodityValue * limit / 100);
								txtCcc
										.setText(String
												.valueOf(getNormalRounded2Decimal(mixedCommodityValue
														* limit / 100)));
								return false;
							} else {
								return true;
							}

						} else {
							if (cc > (bft * limit / 100)) {
								displayError("The CC Value should not exceed "
										+ bft * limit / 100);
								txtCcc.setText(String
										.valueOf(getNormalRounded2Decimal(bft
												* limit / 100)));
								return false;
							} else {
								return true;
							}
						}

					}

				}
			}
			return true;

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	private int getCCLimit() throws Exception {
		CCProfileDTO[] dto = handler.getCCDetails();
		if (null != dto) {
			int len = dto.length;
			for (int i = 0; i < len; i++) {
				if (dto[i].getStation_code().equalsIgnoreCase(
						handler.getStationCode())) {
					return dto[i].getCc_limit();
				}
			}
		}
		return 0;
	}

	/**
	 * Method to fresh up the components
	 */
	private void clearAndEnableFields() {

		quotationID = null;
		quotationType = -1;
		String empty = "";
		customerType = "sundry";

		if (null != cbUnit) {
			cbUnit.removeAll();
			cbUnit.add("Feet");
			cbUnit.add("Inches");
			cbUnit.add("Cm");
		}
		if (cbic != null) {
			cbic.removeAll();
			cbic.setEnabled(false);
		}
		if (cbdc != null) {
			cbdc.removeAll();
			cbdc.setEnabled(false);
		}
		if (null != txteffchargedCWT) {
			txteffchargedCWT.setText(empty);
		}

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date currentDate = new java.util.Date();
		String selectedTab = lrTab.getSelection()[0].getText();
		if (lrType[5].equals(selectedTab))
			lblTopay.setText("LR VIEW");
		txtLrno.setText(empty);
		txtLrno.setEnabled(true);

		txtDate.setText(dateFormat.format(currentDate));

		txtBft.setText(empty);
		txtBft.setEnabled(false);

		txtCcc.setText(empty);
		txtCcc.setEnabled(false);

		txtConsignee.setText(empty);
		txtConsignee.setEnabled(true);

		txtConsigneeCST.setText(empty);
		txtConsigneeCST.setEnabled(true);

		btnWeightRoundOff.setEnabled(true);
		btnWeightRoundOff.setSelection(true);

		txtConsignor.setText(empty);
		txtConsignor.setEnabled(true);

		txtConsignorCST.setText(empty);
		txtConsignorCST.setEnabled(true);

		txtDcc.setText(empty);
		txtDcc.setEnabled(false);

		txtDdc.setText(empty);
		txtDdc.setEnabled(false);

		txtGsc.setText(empty);
		txtGsc.setEnabled(false);

		txtIec.setText(empty);
		txtIec.setEnabled(false);

		txtLc.setText(empty);
		txtLc.setEnabled(false);

		txtLrc.setText(empty);
		txtLrc.setEnabled(false);

		txtDhc.setText(empty);
		txtDhc.setEnabled(false);

		txtStax.setText(empty);
		txtStax.setEnabled(false);

		txtConsignorPhone.setText(empty);
		txtConsignorPhone.setEnabled(true);

		txtConsigneePhone.setText(empty);
		txtConsigneePhone.setEnabled(true);

		txtCneeLandline.setText(empty);
		txtCneeLandline.setEnabled(true);

		txtCnorLandline.setText(empty);
		txtCnorLandline.setEnabled(true);

		cbTo.removeAll();
		cbTo.setEnabled(true);

		txtTotal.setText(empty);
		txtTotal.setEnabled(false);

		cbConsigneeName.removeAll();
		cbConsigneeName.setEnabled(true);

		cbConsignorName.removeAll();
		cbConsignorName.setEnabled(true);

		populateDestStationCodes();
		populateConsignorNames();
		populateConsigneeNames();

		txtFrom.setText(handler.getStationName() + " - "
				+ handler.getStationCode());

		clearArticleTable();
		
		btnSubmit.setEnabled(false);
		btnPrint.setEnabled(false);
		btnView.setEnabled(true);
		btnCancel.setEnabled(false);
		if (null != tblArticle) {
			tblArticle.removeAll();
			tblArticle.setEnabled(true);
			cbUnit.setText("");
			cbUnit.setEnabled(true);
		}
		btnSaveConsignorDetails.setEnabled(true);
		btnSaveConsigneeDetails.setEnabled(true);
		btnopenlr.setSelection(false);
		btnSpecial.setSelection(false);
		btnFOCLr.setSelection(false);
		btnopenlr.setEnabled(true);
		btnSpecial.setEnabled(true);
		btnFOCLr.setEnabled(true);
		btnRate.setEnabled(true);

		if (null != btnccc) {
			btnccc.setSelection(false);
			btnccc.setEnabled(true);
		}

		if (null != btnStax) {
			btnStax.setSelection(false);
			btnStax.setEnabled(true);
		}

		if (null != txtmaxccc)
			txtmaxccc.setText("");
	}

	/**
	 * Method to viewing for given LR number
	 * 
	 * @param dto
	 */
	private void populateBookingDetail(BookingDTO dto) {

		isViewrateType = 0;
		isViewrateType = dto.getRate_type();
		String namecode = "";
		cancelType = null;
		// Populate the record
		cancelType = dto.getType();
		lblTopay.setText(dto.getType());
		txtLrno.setText(dto.getLrno());
		txtLrno.setEnabled(false);
		DateFormat dateformate = new SimpleDateFormat("dd-MM-yyyy");
		if (dto.getIsUPd().equalsIgnoreCase("Rebook") || dto.getIsUPd().equalsIgnoreCase("ToUPD")) {

			
			txtDdc.setVisible(false);
			cbdc.setVisible(false);
			btnccc.setVisible(false);
			lbldc.setVisible(false);
			lblDhc.setVisible(false);
			lblmaxccc.setVisible(false);
			txtmaxccc.setVisible(false);

			label22.setText("DDC");
			label23.setText("OLD Frt");
			label136.setText("Post");
			label137.setText("Demu");
			lblDdc.setText("LC");
			label137.setBounds(789, 397, 32, 18);
			label23.setBounds(767, 324, 59, 16);

			txtCcc.setText(Float.toString(dto.getDdc()));
			txtCcc.setEnabled(false);
			
			txtDcc.setText(Float.toString(dto.getLc()));
			txtDcc.setEnabled(false);
			
			txtIec.setText(Float.toString(dto.getOldLrTotal()));
			txtIec.setEnabled(false);

			txtLc.setText(Float.toString(dto.getPost()));
			txtLc.setEnabled(false);

			txtGsc.setText(String.valueOf((int) dto.getDemu()));
			txtGsc.setEnabled(false);

		} else {

			txtCcc.setText(Float.toString(dto.getCcc()));
			txtCcc.setEnabled(false);

			txtDcc.setText(String.valueOf((int) dto.getDcc()));
			txtDcc.setEnabled(false);

			txtDdc.setText(String.valueOf((int) dto.getDdc()));
			txtDdc.setEnabled(false);

			txtGsc.setText(String.valueOf((int) dto.getGsc()));
			txtGsc.setEnabled(false);

			txtIec.setText(Float.toString(dto.getIec()));
			txtIec.setEnabled(false);

			cbdc.setEnabled(false);

			txtLc.setText(Float.toString(dto.getLc()));
			txtLc.setEnabled(false);

		}

		txtDate.setText(dateformate.format(dto.getDate()));
		// txtDate.setEnabled(false);

		txtBft.setText(String.valueOf((int) dto.getBft()));
		txtBft.setEnabled(false);

		/*
		 * txtChargedWeight.setText(Float.toString(dto.getCharged_weight()));
		 * txtChargedWeight.setEnabled(false);
		 */

		if (dto.getConsignorName() != null)
			cbConsignorName.setText(dto.getConsignorName());
		cbConsignorName.setEnabled(false);

		if (dto.getConsigneeName() != null)
			cbConsigneeName.setText(dto.getConsigneeName());
		cbConsigneeName.setEnabled(false);

		if (dto.getConsignee_address() != null)
			txtConsignee.setText(dto.getConsignee_address());
		txtConsignee.setEnabled(false);

		if (dto.getConsignee_GST() != null)
			txtConsigneeCST.setText(dto.getConsignee_GST());
		txtConsigneeCST.setEnabled(false);

		if (dto.getConsignor_address() != null)
			txtConsignor.setText(dto.getConsignor_address());
		txtConsignor.setEnabled(false);

		if (dto.getConsignor_CST() != null)
			txtConsignorCST.setText(dto.getConsignor_CST());
		txtConsignorCST.setEnabled(false);

		if (dto.getCnorPhone() != null)
			txtConsignorPhone.setText(dto.getCnorPhone());
		txtConsignorPhone.setEnabled(false);

		if (dto.getCneePhone() != null)
			txtConsigneePhone.setText(dto.getCneePhone());
		txtConsigneePhone.setEnabled(false);

		if (dto.getCnorLandLine() != null)
			txtCnorLandline.setText(dto.getCnorLandLine());
		txtCnorLandline.setEnabled(false);

		if (dto.getCneeLandLine() != null)
			txtCneeLandline.setText(dto.getCneeLandLine());
		txtCneeLandline.setEnabled(false);

		if (btnccc != null) {
			btnccc.setEnabled(false);
		}

		txtDhc.setText(String.valueOf((int) dto.getDhc()));
		txtDhc.setEnabled(false);

		txtLrc.setText(String.valueOf((int) dto.getLrc()));
		txtLrc.setEnabled(false);

		txtStax.setText(Float.toString(dto.getStax()));
		txtStax.setEnabled(false);
		namecode = stationNameAndCode(dto.getTo());

		cbTo.setText(namecode);

		cbTo.setEnabled(false);
		namecode = stationNameAndCode(dto.getFrom());
		txtFrom.setText(namecode);

		txtTotal.setText(Float.toString(dto.getTotal()));
		txtTotal.setEnabled(false);

		btnCancel.setEnabled(true);

		btnRate.setEnabled(false);
		cbic.setEnabled(false);

		btnStax.setEnabled(false);
		btnSpecial.setEnabled(false);
		btnopenlr.setEnabled(false);
		if (null != btnFOCLr) {
			btnFOCLr.setEnabled(false);
		}

		int rateType = dto.getRate_type();

		if (rateType == 1) {
			btnSpecial.setSelection(true);
		} else if (rateType == 6) {
			if (null != btnFOCLr) {
				btnFOCLr.setSelection(true);

			}
		} else if (rateType > 6) {
			btnopenlr.setSelection(true);
		} else if (rateType != 4 && rateType != 5) {
			float ccc = dto.getCcc();

			if (ccc > 0) {
				btnccc.setSelection(true);

			}
			if (dto.isStaxNo()) {
				if (null != btnStax) {
					btnStax.setSelection(true);
					btnStax.setEnabled(false);
				}
			}

		}

		if (null != tblArticle) {
			tblArticle.removeAll();
			ArticleDTO[] artdto = dto.getArticledto();
			int len = 0;
			if (null != artdto) {
				len = artdto.length;

				for (int j = 0; j < len; j++) {
					TableItem item = new TableItem(tblArticle, SWT.NONE);
					item.setText(0, artdto[j].getArticleName());
					item
							.setText(1, String.valueOf(artdto[j]
									.getNoOfArticles()));
					item
							.setText(2, String.valueOf(artdto[j]
									.getArticleValue()));
					item
							.setText(3, String.valueOf(artdto[j]
									.getActualWeight()));
					item.setText(4, String.valueOf(artdto[j].getArt_length()));
					item.setText(5, String.valueOf(artdto[j].getArt_breath()));
					item.setText(6, String.valueOf(artdto[j].getArt_height()));
					item.setText(7, String
							.valueOf(artdto[j].getChargedWeight()));
					item.setText(8, String.valueOf(artdto[j].getArticleDesc()));

				}
				int unitindex = dto.getArt_unit();
				if (unitindex == 0)
					cbUnit.setText("Feet");
				else if (unitindex == 1)
					cbUnit.setText("Inches");
				else if (unitindex == 2)
					cbUnit.setText("Cm");

			} else {
				TableItem item = new TableItem(tblArticle, SWT.NONE);
				item.setText(0, String.valueOf(dto.getArticle_id()));
				item.setText(1, String.valueOf(dto.getNo_of_articles()));
				item.setText(2, String.valueOf(dto.getArticle_value()));
				item.setText(3, String.valueOf(dto.getActual_weight()));
				item.setText(4, String.valueOf(EMPTY_STRING));
				item.setText(5, String.valueOf(EMPTY_STRING));
				item.setText(6, String.valueOf(EMPTY_STRING));
				item.setText(7, String.valueOf(dto.getCharged_weight()));
				item.setText(8, String.valueOf(dto.getArticle_description()));
			}
			cbUnit.setEnabled(false);
		}

		// Total CW
		txteffchargedCWT.setText(String.valueOf(dto.getTotal_charged_wt()));
	}

	/**
	 * 
	 * @param stationcode
	 * @return
	 */
	private String stationNameAndCode(String stationcode) {
		try {
			StationsDTO[] stations = handler.getStations();
			for (int i = 0; i < stations.length; i++) {
				if (stations[i].getId().equals(stationcode))
					return stations[i].getName() + "-" + stationcode;
			}
		} catch (Exception exception) {

			exception.printStackTrace();
		}
		return "";
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

	/**
	 * 
	 * @author kibaitachi
	 * 
	 */
	public class submitAction implements SelectionListener,
			org.eclipse.swt.events.FocusListener {

		/**
		 * Method to handle the submit Action
		 */
		private void handleSubmitAction() {
			booking = buildDTO();

			if (null != booking && null != btnopenlr
					&& btnopenlr.getSelection()) {
				booking.setRate_type(7 + rate_type);// Open Lr Type
			}

			float openlrbft = booking.getActual_bft();
			if (openlrbft == 0 && btnopenlr.getSelection()) {
				try {
					openlrbft = getOpenLrActualBFT();
					booking.setActual_bft(openlrbft);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (isNewRecord) {
				boolean result = validateData(booking);
				if (result) {
					try {
						btnNew.setFocus();
						btnSubmit.setEnabled(false);
						MessageBox messageBox = new MessageBox(shell, SWT.NO
								| SWT.YES);
						messageBox.setMessage("Do you want to confirm?");
						messageBox.setText("Warning");
						if (messageBox.open() == SWT.YES) {
							btnNew.setFocus();

							booking = applyCommission(booking);

							// System.out.println("type-->"+customerType);
							booking.setInvoiceNo(customerType);

							handler.bookParcelForm(booking);
							customerType = "sundry";
							// Printing option should be enabled only after user
							// submits the form
							btnSubmit.setEnabled(false);
							displayError("Booked Successfully");
							btnSubmit.setEnabled(false);
							btnPrint.setEnabled(true);

							setCompEnabled(false);
							setAllCompEnabled(false);
							if (!lrTab.getSelection()[0].getText()
									.equalsIgnoreCase("UPD")) {
								if (null != btnccc) {

									btnccc.setSelection(false);
									btnccc.setEnabled(false);

								}
							} else {
								oldlr_delivery(booking);
							}
						} else {
							btnSubmit.setEnabled(true);
							btnSubmit.setFocus();
							btnPrint.setEnabled(false);
						}

					} catch (BusinessException business) {
						displayError(business.getResponseMessage());
						btnSubmit.setEnabled(true);
						btnSubmit.setFocus();
						btnPrint.setEnabled(false);
					} catch (Exception exception) {
						exception.printStackTrace();
						displayError("Error occured while booking. Please try again");
					}
				}
			}
		}

		/**
		 * 
		 * @param booking
		 * @return
		 * 
		 */
		
		
		/*private void handleUPDbookingSMS(BookingDTO book) throws Exception{		
			String cnorMobile = null;
			String cneeMobile = null;
			String cneeMsg = null;
			String cnorMsg = null;
			String dest = EMPTY_STRING;
			String currStn = EMPTY_STRING;
			String currBranch = EMPTY_STRING;
			String destCityBranch = null;
			DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yy");
			
			currStn = handler.getStationCode();
		
			cnorMobile = book.getCnorPhone();
			cneeMobile = book.getCneePhone();
			dest = book.getDestinationStn().trim();
			destCityBranch = checkCityStation(dest);
			currBranch = getBranch(currStn);
					
				cneeMsg = "Greetings from AKR! Your LR No. "
							+ book.getLrNumber()
							+ " dated "
							+ dateFormat.format(book.getLrDate())
							+ " from "
							+ getTruncatedName(book.getCnorName())
							+ " was not claimed. So it is booked to HO for auction. Contact "
							+ book.getDestinationPhone() + ".Thank you!";
				

				
					cnorMsg = "Greetings from AKR! Your LR No. "
						+ book.getLrNumber()
						+ " dated "
						+ dateFormat.format(book.getLrDate())
						+ " to "
						+ getTruncatedName(book.getCneeName())
						+ " was not claimed. So it is booked to HO for auction. Contact "
						+ book.getDestinationPhone() + ".Thank you!";
						
				
							
				
				
				
				// System.out.println("SMS cnor"+"-->"+cnorMsg);

				// System.out.println("SMS cnee"+"-->"+cneeMsg);
				
				if(isUntime()){
					SMSDTO smsDto = new SMSDTO();
					if (null != cnorMsg && null != cnorMobile) {
						smsDto.setCnor_phone(cnorMobile);
						smsDto.setCnor_message(cnorMsg);
					}
					if (null != cneeMsg && null != cneeMobile) {
						smsDto.setCnee_phone(cneeMobile);
						smsDto.setCnee_message(cneeMsg);
					}
					book.setSmsDto(smsDto);				
				
				}else{
					if (null != cnorMsg && null != cnorMobile) {
						sendSMS(cnorMobile, cnorMsg);				
					}
					if (null != cneeMsg && null != cneeMobile) {				
						sendSMS(cneeMobile, cneeMsg);				
					}
					
				}			
			}
		
		private boolean isUntime() throws Exception{
			// Getting Server Date
			DateFormat hourFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			String serverDt = handler.getServerDateTime();
			Date today = hourFormat.parse(serverDt);		
			int hour = today.getHours();	
			int min = today.getMinutes();
			//System.out.println("min==>"+ min);
			
			if(hour > 20 || hour < 9){	
				//System.out.println("untimeeeeeee==>");
				return true;
			}else if(hour == 20 && min > 50){
				//System.out.println("untimeeeeeee==>");
				return true;
			}else if(hour == 9 && min < 15){
				//System.out.println("untimeeeeeee==>");
				return true;
			}else{
				//System.out.println("==>");
				return false;
			}		
			
		}*/
		
		private String getTruncatedName(String name) {
			if (null != name) {
				int len = name.length();
				if (len > 27) {
					return name.substring(0, 20);
				} else {
					return name;
				}
			}
			return null;
		}
		
		private BookingDTO applyCommission(BookingDTO booking) throws Exception {
			int rateType = booking.getRate_type();
			String commType = null;

			if (rateType > 6) {
				rateType = rateType - 7;
			}

			if (rateType != 6) {
				float bft_in = booking.getBft();
				float cardrate = bft_in / booking.getActual_bft();
				if (rateType == 0) {
					commType = "regular";
				} else if (rateType == 1) {
					commType = "special";
				}
				if (rateType == 2) {
					commType = "commodity";
				}
				if (rateType == 3) {
					commType = "HLC in PD/TP";
				}
				if (rateType == 4) {
					commType = "quotation in PD/TP";
				}
				if (rateType == 5) {
					commType = "BILLING LRs";
				}

				System.out.println("Open rate" + rateType + "booking"
						+ booking.getRate_type());
				float percent = getCommissionPercentage(cardrate, commType,
						booking.getFrom());

				booking.setBooking_percent(percent);

				float bookingCommssion = percent * booking.getBft() / 100;
				bookingCommssion = getRounded2Decimal(bookingCommssion);

				System.out.println("bookingCommssion-->" + bookingCommssion);

				float ccCommssion = getCCCommission(booking);
				ccCommssion = getRounded2Decimal(ccCommssion);

				System.out.println("CCCCCCCCCCCommssion-->" + ccCommssion);
				if (lrTab.getSelection()[0].getText().equals("UPD")) {
					booking.setBooking_commission(0);
				} else {
					booking.setBooking_commission(bookingCommssion);
				}

				booking.setCc_commission(ccCommssion);
			}

			return booking;
		}

		/**
		 * @param booking
		 * 
		 */
		private float getCCCommission(BookingDTO booking) throws Exception {

			int rateType = booking.getRate_type();
			if (rateType > 6)
				rateType = rateType - 7;

			String station = booking.getFrom();
			int cc_consider = 0;
			int cc_refund = 0;
			float cccommission_in = 0;
			float refundarticle = 0;

			StationsDTO[] dto = handler.getStations();
			if (null != dto) {
				for (int j = 0; j < dto.length; j++) {
					if (station.equalsIgnoreCase(dto[j].getId())) {
						/*
						 * cc_consider = dto[j].getCc_consider(); cc_refund =
						 * dto[j].getCc_refund();
						 */
						refundarticle = dto[j].getRefundperarticle();
						break;
					}
				}
			}

			if (rateType != 3) {
				CCProfileDTO[] ccProfiledto = handler.getCCDetails();
				if (null != ccProfiledto) {
					for (int k = 0; k < ccProfiledto.length; k++) {
						if (ccProfiledto[k].getStation_code().equalsIgnoreCase(
								station)) {
							cc_consider = ccProfiledto[k].getCc_consider();
							cc_refund = ccProfiledto[k].getCc_refund();
							break;

						}

					}
				}

				float ccvalue = booking.getBft() * cc_consider / 100;
				if (ccvalue > booking.getCcc()) {
					ccvalue = booking.getCcc();
				}

				cccommission_in = ((cc_refund * ccvalue) / 100);
			}

			else if (rateType == 3)
				if (booking.getCcc() == 0) {
					cccommission_in = 0;
				} else {
					float noa = booking.getNo_of_articles();
					float temp = 0;
					/*
					 * select refundperarticle into refundarticle from customer
					 * c where c.name=customer and
					 * c.station_code=station_code_in;
					 */
					String customer = booking.getConsignorName().trim();

					temp = handler.getRefundPerArticleForHLC(customer);

					cccommission_in = noa * temp;

					if (temp == 0) {
						cccommission_in = noa * refundarticle;

					}
				}
			return cccommission_in;
		}

		/**
		 * 
		 * @param cardrate
		 * @param commType
		 * @param station_code
		 * @param dto
		 * @return
		 */
		private float getCommissionPercentage(float cardrate, String commType,
				String station_code) throws Exception {
			CardRateProfileDTO[] dto = handler.getCardRateProfile();
			float percent = 0;
			if (null != dto) {
				int len = dto.length;
				for (int i = 0; i < len; i++) {
					String temp = dto[i].getStation_code();
					String temp_type = dto[i].getLr_type();
					if (station_code.equalsIgnoreCase(temp)) {
						if (temp_type.equalsIgnoreCase(commType)) {
							if (cardrate == 1) {
								percent = dto[i].getEquals_card_rate();

							} else if (cardrate > 1) {
								percent = dto[i].getAbove_card_rate();

							} else if (cardrate < 1 && cardrate > 0.8) {
								percent = dto[i].getUpto_20_card_rate();

							} else if (cardrate < 0.8) {
								percent = dto[i].getAbove_20_card_rate();

							}
							break;
						}

					}

				}
			}
			return percent;
		}

		/**
		 * 
		 * @param count
		 */
		private void handlePrintAction() {
			try {
				booking = buildDTO();
				if (null != booking && null != btnopenlr
						&& btnopenlr.getSelection()) {
					booking.setRate_type(7 + rate_type);// Open Lr Type
				}

				booking.setArticle_description(printarticles_desc);
				handler.printBookingDetails(booking, null, null, printarticles);

				btnPrint.setEnabled(false);

				String selectedTab = lrTab.getSelection()[0].getText();
				if (!selectedTab.equalsIgnoreCase("LR VIEW")
						&& !selectedTab.equalsIgnoreCase("LR CANCEL"))
					setCompEnabled(false);
				setAllCompEnabled(false);
			} catch (Exception exception) {
				exception.printStackTrace();
				displayError("Problem while printing");
			}
		}

		/**
		 * 
		 * 
		 */
		private void handleViewAction() {
			if (!isValidLRNumberFormat()) {
				displayError("LR Number format is not valid");
			} else {
				String lrno = txtLrno.getText();
				if (lrno.length() > 0) {
					try {
						BookingDTO booking = handler.getBookingDetail(lrno);
						if (null != booking) {
							if (booking.getFrom().equalsIgnoreCase(
									handler.getStationCode())
									|| (handler.isBranch() && BeanUtil
											.getInstance().isAdmin())) {
								populateBookingDetail(booking);

								if (handler.getBranch(handler.getStationCode())
										.equalsIgnoreCase(
												handler.getBranch(booking
														.getFrom())))
									btnPrint.setEnabled(true);
								btnCancel.setEnabled(true);
								// On successful population, the view button
								// needs to be disabled and the user can
								// cancel the record and hence the cancel button
								// needs to be enabled.
								btnView.setEnabled(false);
								if (booking.getStatus()) { // Record Cancelled
									displayError("LR Cancelled");
									btnCancel.setEnabled(false);
								} else {
									btnCancel.setEnabled(true);
								}

							} else {
								displayError("Not Allowed to view Other Stations Record");
							}
						}
					} catch (BusinessException business) {
						displayError(business.getResponseMessage());
					} catch (Exception exception) {
						exception.printStackTrace();
						displayError("Error occurred while retrieving the records");
					}
				}
			}
		}

		/**
		 * 
		 */
		private void handleGoAction() {
			try {
				int len = 0;
				track = handler.trackLR(txtTrackNo.getText());

				if (null != track) {
					len = track.length;
					tbllrtrack.removeAll();
					lblDeliver.setVisible(false);
					lblCancel.setVisible(false);

					DateFormat dateFormat = new SimpleDateFormat(
							"dd-MM-yyyy HH:mm:ss");

					ActivityLog[] dto = track[0].getActivities();
					if (dto != null)
						len = dto.length;
					String status = null;
					Date date = null;
					Date deliverydate = null;
					String code = null;
					String activity = null;
					String lrstatus = null;
					String phoneno = null;
					String cancelstation = null;
					String stationName = null;
					String movedStation = null;
					String delStation = null;
					Date cancelledDeliverydate = null;
					String crNo = null;

					for (int i = 0; i < len; i++) {
						TableItem item = new TableItem(tbllrtrack, SWT.NONE);
						status = dto[i].getActivity();
						date = dto[i].getActivityTime();
						code = dto[i].getStationCode();
						// System.out.println("date------->"+date);
						crNo = dto[i].getCrno();
						if (crNo == null) {
							crNo = EMPTY_STRING;
						}
						stationName = getStationName(code);

						if (date != null) {
							item.setText(0, String.valueOf(date));
						} else {
							item.setText(0, EMPTY_STRING);
						}// System.out.println("i-->"+i);
						// System.out.println("rate_type-->"+track[0].getRate_type());
						if (i == 0) {
							activity = "Booked at " + stationName;
							item.setText(1, activity);
							// lrstatus = "Present at " + stationName;
							if (track[0].getCancelledOption() != null) {

								TableItem item1 = new TableItem(tbllrtrack,
										SWT.NONE);
								item1.setText(0, String.valueOf(track[0]
										.getCancelDate()));
								item1.setText(1, "Lr Cancelled" + " - "
										+ track[0].getCancelledOption());
								lrstatus = "Lr was Cancelled.";
								lblostatus.setText(lrstatus);

							}
						} else if (track[0].getRate_type() == i) {
							// System.out.println("to_station-->"+track[0].getTo_station());
							// System.out.println("i-->"+i);
							// System.out.println("rate_type-->"+track[0].getRate_type());
							// System.out.println("lr_no-->"+track[0].getIsUPd().substring(0,track[0].getIsUPd().indexOf("
							// - ")).trim());
							
							if(track[0].getTo_station().equals("UPD")){
								activity = "Booked as UPD LR "
									+ track[0].getIsUPd().substring(0,
											track[0].getIsUPd().indexOf(" - "))
											.trim();
							item.setText(1, activity);
							}else{
								activity = "Rebooked to "
									+ track[0].getTo_station()
									+ " as LR NO. "
									+ track[0].getIsUPd().substring(0,
											track[0].getIsUPd().indexOf(" - "))
											.trim();
							item.setText(1, activity);
							}
							
							// lrstatus = "Present at " + stationName;
							if (track[0].getLr_status() == 1) {
								// System.out.println("lr---date"+track[0].getLr_date());
								item.setText(0, track[0].getLr_date());
								TableItem item1 = new TableItem(tbllrtrack,
										SWT.NONE);
								System.out.println("createdon"
										+ track[0].getCreatedon());
								item1.setText(0, String.valueOf(track[0]
										.getCreatedon()));
								item1.setText(1, "Lr Cancelled" + " - "
										+ track[0].getDesc());
								// lrstatus = "Lr was Cancelled.";
								// lblostatus.setText(lrstatus);
							}
						}

						else {
							if (status.equalsIgnoreCase("Arrived")) {
								activity = "Received at " + stationName;
								item.setText(1, activity);
								// lrstatus = "Present at " + stationName;
							} else if (status.equalsIgnoreCase("Dispatched")) {
								movedStation = getStationName(dto[i]
										.getToStationCode());
								activity = "Moved to " + movedStation
										+ "  in Vehicle No: "
										+ dto[i].getVehicleNo();
								lblostatus.setText(activity.replaceFirst(
										"Moved to ", "On the way to "));
								item.setText(1, activity);
								// lrstatus = "Present at " + stationName;
							} else if (status.equalsIgnoreCase("Delivered")) {
								if (i == len - 1) {
									deliverydate = date;
								}

								activity = "Delivered* " + crNo;
								item.setText(1, activity);
								delStation = stationName;
								/*
								 * lrstatus = "Delevered at " + stationName;
								 * lblDeliver .setText("* - The consignment is
								 * out for delivery and the actual delivery time
								 * will vary upto to additional 2 hours.");
								 * lblDeliver.setVisible(true);
								 */
							} else if (status.equalsIgnoreCase("CR Cancelled")) {
								activity = "Delivery Cancelled** Received at "
										+ stationName;
								cancelstation = stationName;

								// For Delivered*

								{
									TableItem newItem = new TableItem(
											tbllrtrack, SWT.NONE);
									cancelledDeliverydate = dto[i]
											.getCancelledDeliverTime();
									if (cancelledDeliverydate != null) {
										newItem
												.setText(
														0,
														String
																.valueOf(cancelledDeliverydate));
									}
									newItem.setText(1, "Delivered*");
								}
								item.setText(1, activity);

								/*
								 * lrstatus = "Cancelled at " + stationName;
								 * lblCancel .setText("** - The delivery has
								 * been cancelled. Kindly get in touch with our" +
								 * stationName + " office for more information.
								 * The Contact no is " + phoneno);
								 * lblCancel.setVisible(true);
								 */
							}
						}

					}

					// sortByDate(0, tbllrtrack);

					if (null != track) {
						txtTrackLRType.setText(track[0].getLrType());
						lblobookeddate.setText(String.valueOf(dateFormat
								.format(track[0].getLrDate())));
						if (null != deliverydate)
							lblodeliverydate.setText(String.valueOf(dateFormat
									.format(deliverydate)));
						lblosource.setText(getStationName(track[0]
								.getFromLocation()));
						lblodestination.setText(getStationName(track[0]
								.getToLocation()));
						lblonoofarticles.setText(String.valueOf(track[0]
								.getNo_of_articles()));
						lbloweight
								.setText(String.valueOf(track[0].getWeight()));
						txtTrackBFT.setText(String.valueOf(track[0].getBft()));
						txtTrackDdc.setText(String.valueOf(track[0].getDdc()));
						// System.out.println("n70452==>"+track[0].getConsignorName()+"cnee==>"+track[0].getConsigneeName());
						txtTrackCnor.setText(track[0].getConsignorName());
						txtTrackCnee.setText(track[0].getConsigneeName());

					}

					// For Status
					if (tbllrtrack != null) {
						int tblLen = tbllrtrack.getItemCount();
						String statusMsg = EMPTY_STRING;
						if (tblLen > 0) {
							statusMsg = tbllrtrack.getItem(tblLen - 1).getText(
									1);
							if (statusMsg.startsWith("Booked at")) {
								statusMsg = statusMsg.replaceFirst("Booked at",
										"Present at ");
								lblDeliver.setVisible(false);
								lblCancel.setVisible(false);
							} else if (statusMsg.startsWith("Received at")) {
								statusMsg = statusMsg.replaceFirst(
										"Received at", "Present at ");
								lblDeliver.setVisible(false);
								lblCancel.setVisible(false);
							} else if (statusMsg.startsWith("Moved to")) {
								statusMsg = statusMsg.replaceFirst("Moved to",
										"On the way to");
								int ind = statusMsg.indexOf(" in ");
								statusMsg = statusMsg.substring(0, ind);
								lblDeliver.setVisible(false);
								lblCancel.setVisible(false);
							} else if (statusMsg.startsWith("Delivered")) {
								statusMsg = "Delivered at " + delStation;

								lblDeliver
										.setText("* - The consignment is out for delivery and the actual delivery time will vary upto to additional 2 hours.");
								lblDeliver.setVisible(true);
								lblCancel.setVisible(false);
							} else if (statusMsg
									.startsWith("Delivery Cancelled")) {
								statusMsg = "Cancelled at " + cancelstation;
								phoneno = getPhoneNumber(code);
								lblCancel
										.setText("** - The delivery has been cancelled. Kindly get in touch with our"
												+ stationName
												+ " office for more information. The Contact no is "
												+ phoneno);
								lblDeliver.setVisible(false);
								lblCancel.setVisible(true);
							}
							lblostatus.setText(statusMsg);

						}
					}

					if (handler.isAdmin()) {
						txtTrackBFT.setVisible(true);
						lblBFT.setVisible(true);
						lblTrackCnor.setVisible(true);
						lblTrackCnee.setVisible(true);
						txtTrackCnor.setVisible(true);
						txtTrackCnee.setVisible(true);
					} else {
						txtTrackBFT.setVisible(false);
						lblBFT.setVisible(false);
						lblTrackCnor.setVisible(false);
						lblTrackCnee.setVisible(false);
						txtTrackCnor.setVisible(false);
						txtTrackCnee.setVisible(false);
					}
				} else
					displayError("INVALID NUMBER");

			} catch (BusinessException b) {
				if (b.getResponseMessage().equals("LR Number does not exist")) {
					displayError(b.getResponseMessage());
					clearLRTrackFields();
				}

			} catch (Exception exception) {
				exception.printStackTrace();
			}

		}

		private void clearLRTrackFields() {
			txtTrackBFT.setText(EMPTY_STRING);
			txtTrackCnee.setText(EMPTY_STRING);
			txtTrackCnor.setText(EMPTY_STRING);
			txtTrackDdc.setText(EMPTY_STRING);
			txtTrackLRType.setText(EMPTY_STRING);
			txtTrackDdc.setText(EMPTY_STRING);
			lblobookeddate.setText(EMPTY_STRING);
			lblodeliverydate.setText(EMPTY_STRING);
			lblodestination.setText(EMPTY_STRING);
			lblonoofarticles.setText(EMPTY_STRING);
			lblosource.setText(EMPTY_STRING);
			lblostatus.setText(EMPTY_STRING);
			lbloweight.setText(EMPTY_STRING);

			if (tbllrtrack != null)
				tbllrtrack.removeAll();
		}

		/**
		 * 
		 * @param index
		 * @param refTable
		 */
		private void sortByDate(int index, Table refTable) {
			try {

				TableItem[] items = refTable.getItems();

				SimpleDateFormat parse = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");

				Date date1 = null;
				Date date2 = null;
				String dateOne = "";
				String dateTwo = "";
				int len = items.length;

				// Sorting need to happen only if the number of rows available
				// is greater than one excluding total
				if (len > 2) {
					for (int i = 1; i < len; i++) {
						if (!items[i].getText(index).isEmpty()) {

							dateOne = items[i].getText(index);
							if (dateOne != null
									&& !dateOne.equals(EMPTY_STRING)) {
								date1 = parse.parse(dateOne);
							}

							for (int j = 0; j < i; j++) {
								if (!items[j].getText(index).isEmpty()) {

									dateTwo = items[j].getText(index);
									if (dateTwo != null
											&& !dateTwo.equals(EMPTY_STRING)) {
										date2 = parse.parse(dateTwo);
									}

								}

								if (date1 != null && date2 != null) {
									if (date1.before(date2)) {
										String[] values = {
												items[i].getText(0),
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
												items[i].getText(14) };
										items[i].dispose();
										TableItem item = new TableItem(
												refTable, SWT.NONE, j);
										item.setText(values);
										items = refTable.getItems();
										break;
									}
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
		 * @param code
		 * @return
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
		 * 
		 * @param code
		 * @return
		 */
		private String getPhoneNumber(String code) {
			StationsDTO[] stations = null;
			try {
				stations = handler.getStations();
				for (int i = 0; i < stations.length; i++) {
					if (code.equalsIgnoreCase(stations[i].getId())) {
						return stations[i].getMobile();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}

		/**
		 * 
		 */
		private void handleCancelAction() {
			String lrno = txtLrno.getText();
			if (lrno.length() > 0) {
				try {
					Shell popPriority = new Shell(shell, SWT.PRIMARY_MODAL
							| SWT.TOP);

					LRCancelDialog lrcancel = new LRCancelDialog(popPriority,
							SWT.TITLE);
					lrcancel.loadComposite(cancelType);

					handler.cancelBooking(lrno, lrcancel.selectedOption);
					displayError("Cancelled Successfully");
					btnCancel.setEnabled(false);
				} catch (BusinessException business) {
					displayError(business.getResponseMessage());
				} catch (Exception exception) {
					displayError("Error occurred while retrieving the records");
				}
			}
		}

		/**
		 * 
		 */
		public void widgetSelected(SelectionEvent event) {
			// errorLabel.setText("");
			Object source = event.getSource();

			if (btnToUPD == source) {
				if (btnToUPD.getSelection()) {
					btnRebooklr.setEnabled(false);
					cbTo.removeAll();
					cbTo.add("Unclaimed Propery Department - UPD");
					cbTo.select(0);
					cbTo.setEnabled(false);
					txtPost.setEnabled(true);
					txtDemu.setEnabled(true);
				} else {
					if (cbTo != null) {
						cbTo.removeAll();
					}
					cbTo.setEnabled(true);
					//changeToEditableFields();
					populateConsignorNames();
					populateConsignorNames();
					populateDestStationCodes();
					btnRebooklr.setEnabled(true);
					clearRatesFields(false);
					
				}

			} else if (btnRebooklr == source) {
				if (btnRebooklr.getSelection()) {
					btnToUPD.setEnabled(false);
					populateDestStationCodes();
					changeToEditableFields();
					btnopenlr.setSelection(true);
				} else {
					btnToUPD.setEnabled(true);
					if (cbTo != null) {
						cbTo.removeAll();
					}
					populateDestStationCodes();
					btnopenlr.setSelection(false);
					clearRatesFields(false);
				}

			}

			else if (btnSubmit == source) {
				int distance = isDistanceAvailable();
				System.out.println("distance"+distance);
				if (distance != 0) {
					try {
						handleSubmitAction();

					} catch (Exception exception) {
						exception.printStackTrace();
						displayError("Error Occurred while submitting the LR");

					}
				} else {
					displayError("Distance for the Destination is not set. Kindly contact"
							+ " Head Office immediately to rectify this issue.");
				}

			} else if (btnNew == source) {
				clearAndEnableFields();

			} else if (btnView == source) {
				handleViewAction();

			} else if (btnGo == source) {
				handleGoAction();
			} else if (btnCFTResult == source) {
				calculateUnitValues();
			}

			else if (btnCancel == source) {
				handleCancelAction();

			} else if (btnPrint == source) {
				// for (int i = 0; i < 4; i++)
				handlePrintAction();
			} else if (btnSaveConsigneeDetails == source) {

				int index1 = cbConsigneeName.getSelectionIndex();
				String consigneeName = null;

				if (index1 > -1) {
					consigneeName = cbConsigneeName.getItem(index1);
				} else {
					consigneeName = cbConsigneeName.getText();
					if (consigneeName.trim().length() == 0)
						consigneeName = null;
				}

				if (consigneeName != null
						&& !consigneeName.equalsIgnoreCase("self")) {
					try {
						String value = txtFrom.getText();
						int index = value.indexOf(" - ");
						value.substring(index + 3);

						customerHandler.addConsignee(consigneeName,
								txtConsignee.getText(), txtConsigneeCST
										.getText(),
								txtConsigneePhone.getText(), txtCneeLandline
										.getText(), false);

						cbConsigneeName.add(consigneeName);

						txtConsigneeCST.setFocus();

					} catch (Exception exception) {
						exception.printStackTrace();
					}
				}

			} else if (btnSaveConsignorDetails == source) {
				consignor = new CustomerDTO();
				int index1 = cbConsignorName.getSelectionIndex();
				String consignorName = null;

				if (index1 > -1) {

					consignorName = cbConsignorName.getItem(index1);
				} else {
					consignorName = cbConsignorName.getText();
					if (consignorName.trim().length() == 0)
						consignorName = null;
				}
				if (consignorName != null
						&& !consignorName.equalsIgnoreCase("self")) {
					consignor.setName(consignorName);
					consignor.setAddress(txtConsignor.getText());
					String value = txtFrom.getText();
					int index = value.indexOf(" - ");
					String station_Code = value.substring(index + 3);
					consignor.setStation(station_Code);
					try {
						customerHandler.addConsignor(consignorName,
								txtConsignor.getText(), txtConsignorCST
										.getText(),
								txtConsignorPhone.getText(), txtCnorLandline
										.getText(), false);

						cbConsignorName.add(consignorName);
						cbConsigneeName.setFocus();
					} catch (Exception exception) {

						exception.printStackTrace();
					}
				}

			} else if (cbConsignorName == source) {
				if (cbConsignorName.getSelectionIndex() != -1) {
					clearArticleTable();
					txteffchargedCWT.setText(EMPTY_STRING);
					findBookingType();
					Combo selection = null;
					Text address = null;
					selection = cbConsignorName;
					address = txtConsignor;
					txtConsignor.setText(EMPTY_STRING);
					txtConsignorCST.setText(EMPTY_STRING);
					consignorAction(selection, address, txtConsignorCST);
					String selectedTab = lrTab.getSelection()[0].getText();
					if (selectedTab.equalsIgnoreCase("BILLING")) {
						txtConsignor.setEnabled(false);
					}
				}
			} else if (cbConsigneeName == source) {
				if (cbConsigneeName.getSelectionIndex() != -1) {
					clearArticleTable();
					txteffchargedCWT.setText(EMPTY_STRING);
					findBookingType();
					Combo selection = null;
					Text address = null;

					selection = cbConsigneeName;
					address = txtConsignee;

					txtConsignee.setText(EMPTY_STRING);
					txtConsigneeCST.setText(EMPTY_STRING);

					consigneeAction(selection, address, txtConsigneeCST);
					String selectedTab = lrTab.getSelection()[0].getText();
					if (selectedTab.equalsIgnoreCase("BILLING")) {
						txtConsignee.setEnabled(false);
					}
				}
			} else if (btnArtAdd == source) {

				if (!isHLC()) {
					if (null != txteffchargedCWT) {
						txteffchargedCWT.setText("");
					}
					populateArticle();
				}
			} else if (btnRate == source) {

				try {
					calculateLRRates(false);
					if (btnopenlr.getSelection()) {
						changeToEditableFields();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else if (btnopenlr == source) {
				if (lrTab.getSelection()[0].getText().equals("UPD")) {
					if (btnopenlr.getSelection())
						changeToEditableFields();
					else {
						clearRatesFields(false);
						//txtPost.setEnabled(true);
						//txtDemu.setEnabled(true);
					}

				} else {
					if (btnopenlr.getSelection()) {
						btnSpecial.setSelection(false);
						btnSpecial.setEnabled(false);
						btnFOCLr.setSelection(false);
						btnFOCLr.setEnabled(false);
						changeToEditableFields();
					} else {
						try {
							if (quotationID == null) {
								btnSpecial.setEnabled(true);
								btnFOCLr.setEnabled(true);
							}
							if (isCustomerSelected()) {
								if (isTotalChargedWeightAvailable())
									calculateLRRates(true);
								else
									clearRatesFields(false);
							}
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
				}
			} else if (btnSpecial == source) {

				try {
					btnopenlr.setEnabled(!btnSpecial.getSelection());
					btnFOCLr.setEnabled(!btnSpecial.getSelection());
					if (isCustomerSelected()) {

						if (isTotalChargedWeightAvailable()) {
							cbdc.setEnabled(!btnSpecial.getSelection());
							calculateLRRates(true);
						} else {
							clearRatesFields(false);
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

			} else if (btnFOCLr == source) {
				try {

					btnSpecial.setSelection(false);
					btnSpecial.setEnabled(!btnFOCLr.getSelection());
					btnopenlr.setEnabled(!btnFOCLr.getSelection());
					btnopenlr.setSelection(false);
					if (isCustomerSelected()) {
						calculateLRRates(true);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (cbUnit == source) {
				try {
					int index = cbUnit.getSelectionIndex();
					if (index > -1) {
						if (null != artlist) {
							String len = null;
							String bredth = null;
							String height = null;
							String weight = null;
							String noa = null;
							int size = artlist.size();
							if (size > 0) {
								for (int i = 0; i < size; i++) {
									len = artlist.get(i).getTxtlength()
											.getText();
									bredth = artlist.get(i).getTxtbreadth()
											.getText();
									height = artlist.get(i).getTxtheight()
											.getText();
									weight = artlist.get(i).getTxtweight()
											.getText();

									noa = artlist.get(i).getTxtnoofarticle()
											.getText();

									if (!(len.equals(EMPTY_STRING)
											|| bredth.equals(EMPTY_STRING)
											|| height.equals(EMPTY_STRING) || noa
											.equals(EMPTY_STRING))) {
										if (index > -1) {
											float chargewt = Float
													.parseFloat(len)
													* Float.parseFloat(bredth)
													* Float.parseFloat(height)
													* Float.parseFloat(noa);
											/*
											 * if (index == 0) { chargewt *=
											 * 8.5; } else if (index == 1) {
											 * chargewt *= 0.00492; } else if
											 * (index == 2) { chargewt *=
											 * 0.0003; }
											 */
											double[] units = handler
													.getMeasurements();
											if (index == 0) {

												chargewt *= units[0];
											} else if (index == 1) {

												chargewt *= units[2];
											} else if (index == 2) {

												chargewt *= units[1];
											}
											if (!weight.equals(EMPTY_STRING)) {
												chargewt = Float
														.parseFloat(weight) > chargewt ? Float
														.parseFloat(weight)
														: chargewt;
												float check = chargewt;
												chargewt = Math.round(chargewt);
												if (chargewt < check)
													chargewt += 1;

												artlist
														.get(i)
														.getTxtchargeweight()
														.setText(
																Float
																		.toString(chargewt));
											}
										} else {
											artlist.get(i).getTxtchargeweight()
													.setText("");
											displayError("Select the Unit");
										}
									} else
										artlist.get(i).getTxtchargeweight()
												.setText(weight);
								}
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (btnStax == source) {
				applyServiceTax();
			} else if (cbdc == source || source == cbic) {
				txtBft.setText(String.valueOf((int) Math.ceil(getBFT(String
						.valueOf(actualBft)))));
				applyDHCValue(txtBft.getText());

				if (null != btnopenlr && !btnopenlr.getSelection())
					applyValidCCValue();
				else if (null == btnopenlr)
					applyValidCCValue();

				applyServiceTax();
			} else if (btnccc == source) {
				try {

					if (null != btnopenlr && !btnopenlr.getSelection())
						applyValidCCValue();
					else if (null == btnopenlr)
						applyValidCCValue();

					applyServiceTax();

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (source == cbTo) {
				populateConsigneeWithQuotation();
			} else if (source == btnWeightRoundOff) {
				try {
					calculateLRRates(false);

				} catch (Exception e) {
					// TODO: handle exception
				}
			}

		}

		private void applyDHCValue(String bft) {
			float temp = Float.parseFloat(bft);
			System.out.println("DHC PERCENT" + dhcPercent);
			float dhctemp = dhcPercent * temp / 100;
			txtDhc.setText(String.valueOf((int) Math.ceil(dhctemp)));

		}

		private void populateConsigneeWithQuotation() {
			try {
				CustomerDTO[] dto = null;

				int index = cbTo.getSelectionIndex();
				if (index > -1) {
					cbConsigneeName.removeAll();
					String tostation = cbTo.getItem(index);
					index = tostation.indexOf("-");
					tostation = tostation.substring(index + 2);

					allConsignee = customerHandler.getSundryCustomers(true);

					int len = 0;
					int dtolen = 0;
					int templen = 0;
					if (null != allConsignee)
						Arrays.sort(allConsignee);
					String selectedTab = lrTab.getSelection()[0].getText();
					if (null != allConsignee) {
						len = allConsignee.length;
					}

					dto = handler.getQuotationCustomers(handler
							.getStationCode(), true);

					// if (!selectedTab.equalsIgnoreCase("BILLING")) {
					String[] tempconsignee = null;
					if (null != dto) {
						dtolen = dto.length;
						tempconsignee = new String[dto.length];
					}

					for (int j = 0; j < dtolen; j++) {

						if (selectedTab.equalsIgnoreCase("TOPAY")) {
							if (dto[j].getQuotation_type() != 2
									&& dto[j].isBookingType()
									&& tostation.equalsIgnoreCase(dto[j]
											.getStation())) {

								tempconsignee[j] = dto[j].getName();
							} else {
								tempconsignee[j] = "";
							}
						} else if (selectedTab.equalsIgnoreCase("PAID")) {

							if (dto[j].isBookingType()
									&& tostation.equalsIgnoreCase(dto[j]
											.getStation())) {
								tempconsignee[j] = dto[j].getName();
							} else {
								tempconsignee[j] = "";
							}
						} else if (selectedTab.equalsIgnoreCase("BILLING")) {
							if (dto[j].getQuotation_type() == 3
									&& dto[j].isBookingType()
									&& tostation.equalsIgnoreCase(dto[j]
											.getStation())) {
								tempconsignee[j] = dto[j].getName();
							} else {
								tempconsignee[j] = "";
							}
						}
					}
					String[] finalconsingee = null;
					if (null != tempconsignee) {
						templen = tempconsignee.length;
					}
					finalconsingee = new String[len + templen];
					if (len > 0)
						System.arraycopy(allConsignee, 0, finalconsingee, 0,
								len);
					if (templen > 0)
						System.arraycopy(tempconsignee, 0, finalconsingee, len,
								templen);

					if (finalconsingee != null) {
						len = finalconsingee.length;
						TreeSet<String> set = new TreeSet<String>(
								String.CASE_INSENSITIVE_ORDER);
						for (int i = 0; i < len; i++) {
							if (finalconsingee[i] != null
									&& !finalconsingee[i].equals(EMPTY_STRING))
								set.add(finalconsingee[i]);
							// cbConsigneeName.add(finalconsingee[i]);
						}

						Iterator<String> iterator = set.iterator();
						// Displaying the Tree set data
						while (iterator.hasNext()) {
							cbConsigneeName.add(iterator.next());
						}

					}

				}

			} catch (Exception exception) {
				exception.printStackTrace();
			}
			deInitializeState();

		}

		private boolean isTotalChargedWeightAvailable() {
			if (null != txteffchargedCWT) {
				if (!txteffchargedCWT.getText().equals(EMPTY_STRING))
					return true;
			}
			return false;
		}

		private boolean isCustomerSelected() {
			boolean consignor = false;
			boolean consignee = false;
			int index = -1;
			index = cbConsignorName.getSelectionIndex();
			if (index > -1) {
				consignor = true;
				index = -1;
			} else {
				if (!cbConsignorName.getText().equalsIgnoreCase(EMPTY_STRING))
					consignor = true;
			}

			index = -1;
			index = cbConsigneeName.getSelectionIndex();
			if (index > -1) {
				consignee = true;
				index = -1;
			} else {
				if (!cbConsigneeName.getText().equalsIgnoreCase(EMPTY_STRING))
					consignee = true;
			}
			if (consignor && consignee)
				return true;
			return false;
		}

		private int isDistanceAvailable() {

			try {
				String tostation = null;
				Integer distance = null;
				int index = -1;
				int ind = cbTo.getSelectionIndex();
				tostation = cbTo.getText();
				
				if (tostation != null && !tostation.equals("")) {
					index = tostation.indexOf("-");
					tostation = tostation.substring(index + 2);
					String code = handler.getStationCode();
					
					String distancestation = checkCityStation(code);
					RegularSundryDTO regulerdto = handler
							.getSundryDetails(code);
					DistanceListDTO[] dto = handler
							.getDistance(distancestation);
					
					if (null != dto) {
						int len = dto.length;
						for (int i = 0; i < len; i++) {
							// Getting the distance and bpi for TR-City
							// station
							/*
							 * if (checkCityStation(code).equalsIgnoreCase(
							 * checkCityStation(tostation))) { return distance =
							 * regulerdto.getDistance(); }
							 */

							if (dto[i].getDestStation().equalsIgnoreCase(
									tostation)) {
								return distance = dto[i].getDistance();

							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}

		private void changeToEditableFields() {

			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date currentDate = new java.util.Date();
			String selectedTab = lrTab.getSelection()[0].getText();
			if (lrType[5].equals(selectedTab))
				lblTopay.setText("LR VIEW");
			// txtLrno.setText(empty);
			if (selectedTab.equals("UPD")) {
				if (btnRebooklr.getSelection() && btnopenlr.getSelection() ) {
					txtPost.setEnabled(true);
					txtDemu.setEnabled(true);
					txtConsigneeCST.setEnabled(true);
					txtConsignorCST.setEnabled(true);
					cbConsigneeName.setEnabled(true);
					cbConsignorName.setEnabled(true);
					btnSaveConsignorDetails.setEnabled(true);
					btnSaveConsigneeDetails.setEnabled(true);
				}else if(btnToUPD.getSelection()){
					txtPost.setEnabled(true);
					txtDemu.setEnabled(true);	
				}
			} else {
				txtLrno.setEnabled(true);
				txtCcc.setEnabled(true);
				txtDcc.setEnabled(true);
				txtDdc.setEnabled(true);
				txtIec.setEnabled(true);
				txtGsc.setEnabled(true);
				txtConsigneeCST.setEnabled(true);
				txtConsignorCST.setEnabled(true);
				cbConsigneeName.setEnabled(true);
				cbConsignorName.setEnabled(true);
				btnSaveConsignorDetails.setEnabled(true);
				btnSaveConsigneeDetails.setEnabled(true);
				if (quotationID == null) {
					// cbic.setEnabled(true);
					cbdc.setEnabled(true);
				}

			}
			txtDdc.setEnabled(true);
			txtDate.setText(dateFormat.format(currentDate));

			// txtBft.setText(empty);
			txtBft.setEnabled(true);

			// txtCcc.setText(empty);

			// txtCcc.setText(empty);
			txtDhc.setEnabled(true);

			// txtConsignee.setText(empty);
			// txtConsignee.setEnabled(true);

			// txtConsigneeCST.setText(empty);
			

			// txtConsignor.setText(empty);
			// txtConsignor.setEnabled(true);

			// txtConsignorCST.setText(empty);
			

			// txtDcc.setText(empty);

			// txtDdc.setText(empty);

			// txtGsc.setText(empty);

			// if (null != quotationID) {
			// txtIec.setText(empty);

			// txtLc.setText(empty);
			txtLc.setEnabled(true);
			// }

			// txtLrc.setText(empty);
			txtLrc.setEnabled(true);

			// txtStax.setText(empty);
			txtStax.setEnabled(false);

			// cbTo.setText(empty);
			if (lrTab.getSelection()[0].getText().equalsIgnoreCase("UPD")
					&& btnToUPD.getSelection()) {
				cbTo.setEnabled(false);
				
				
			} else {
				cbTo.setEnabled(true);
			}
			// txtTotal.setText(empty);
			txtTotal.setEnabled(false);

			// comConsigneeName.setText(empty);
			

			// comConsignorName.setText(empty);
			

			txtFrom.setText(handler.getStationName() + " - "
					+ handler.getStationCode());

			// clearArticleTable();

			// btnSubmit.setEnabled(true);
			btnPrint.setEnabled(false);
			btnView.setEnabled(true);
			btnCancel.setEnabled(false);
			// if (null != tblArticle) {
			// tblArticle.removeAll();
			// tblArticle.setEnabled(true);
			// cbUnit.setText("");
			// cbUnit.setEnabled(true);
			// }*/
		
			if (quotationID == null) {
				cbic.setEnabled(true);
				// // cbdc.setEnabled(true);
			}

		}

		private void calculateUnitValues() {
			lblCFTResult.setText("");

			String len = txtCFTLength.getText();
			String bre = txtCFTBreath.getText();
			String hei = txtCFTHeight.getText();
			if (len.equalsIgnoreCase(EMPTY_STRING)
					|| bre.equalsIgnoreCase(EMPTY_STRING)
					|| hei.equalsIgnoreCase(EMPTY_STRING)) {
				displayError("Parameters Are Missing");

			} else {
				try {
					double length = Float.parseFloat(len);
					double breath = Float.parseFloat(bre);
					double height = Float.parseFloat(hei);
					double result = length * breath * height;

					if (null != handler) {
						double[] units = handler.getMeasurements();

						if (btnCFTCm.getSelection()) {

							result = result * units[1];
							result = (Math.round(result * 10));
							result = result / 10;

							lblCFTResult.setText(String.valueOf(result));
						} else if (btnCFTFt.getSelection()) {
							result = result * units[0];
							result = (Math.round(result * 10));
							result = result / 10;
							lblCFTResult.setText(String.valueOf(result));

						} else if (btnCFTInch.getSelection()) {
							result = result * units[2];
							result = (Math.round(result * 10));
							result = result / 10;
							lblCFTResult.setText(String.valueOf(result));
						} else {
							lblCFTResult.setText(String.valueOf(""));
							displayError("Please select Any one of units");
						}

					}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

		/**
		 * Method to display all Article for quotation
		 * 
		 */


		/**
		 * Method to check the station is city station. If it is city station ,
		 * the branch will be returned.
		 * 
		 * @param stationCode
		 * @return
		 */
		private String checkCityStation(String stationCode) {
			String station = null;
			try {
				StationsDTO[] stationsDTO = handler.getStations();
				if (null != stationsDTO) {
					
					for (int i = 0; i < stationsDTO.length; i++) {
						if (stationsDTO[i].getId()
								.equalsIgnoreCase(stationCode)
								&& stationsDTO[i].getType().equalsIgnoreCase(
										"City")) {
							station = stationsDTO[i].getBranch_code();
						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			if (station == null) {
				station = stationCode;
			}
			System.out.println("dfdsf"+station);
			return station;
		}

		/**
		 * Method to calculate LR rate Details
		 * 
		 * @throws Exception
		 */
		private void calculateLRRates(boolean visible) throws Exception {
			if (artlist != null && artlist.size() > 0
					&& isValidArticleDetails(artlist)) {

				isReadyToSubmit = true;
				if (isValidLRNO) {
					btnSubmit.setEnabled(true);
				}

				boolean isValidLr = false;
				String selected = lrTab.getSelection()[0].getText();
				if (selected.equalsIgnoreCase("PAID")
						&& btnFOCLr.getSelection() == true) {
					applyFOC();
					rate_type = 6;
				} else {
					BookingDTO temp = null;
					int noofarticle = 0;
					float chargedweight = 0;
					float[] tempcw = null;
					float artvalue = 0;
					float bft = 0;
					float insurance = 0;
					float lrc = 0;
					float dhc = 0;
					float ccc = 0;
					float ddc = 0;

					float dcc = 0;

					float iec = 0;
					float lcc = 0;
					float stax = 0;
					int taxlimit = 0;
					int fdiscount = 0;
					int fincrement = 0;
					int tdiscount = 0;
					int tincrement = 0;
					float bpi = 0;
					float gsc = 0;
					int size = 0;
					rate_type = 0;
					isQST = false;
					String code = handler.getStationCode();

					RegularSundryDTO regulerdto = handler
							.getSundryDetails(code);

					InsuranceDTO[] insurancedto = regulerdto.getInsuranceDTO();
					if (null != insurancedto)
						size = insurancedto.length;
					float fromvalue = 0;
					float tovalue = 0;

					if (null != artlist) {
						int size1 = artlist.size();
						tempcw = new float[size1];
						String isEmpty = null;

						for (int i = 0; i < size1; i++) {
							isEmpty = artlist.get(i).getTxtnoofarticle()
									.getText();
							if (!isEmpty.trim().equalsIgnoreCase("")) {
								noofarticle += Integer.parseInt(isEmpty);
							}

							isEmpty = artlist.get(i).getTxtchargeweight()
									.getText();

							if (!isEmpty.trim().equalsIgnoreCase("")) {
								tempcw[i] = Float.parseFloat(isEmpty);
								chargedweight += Float.parseFloat(isEmpty);
							}

							isEmpty = artlist.get(i).getTxtartvalue().getText();
							try {
								// if (!isEmpty.trim().equalsIgnoreCase("")) {
								artvalue += Float.parseFloat(isEmpty);
								// }
							} catch (NumberFormatException e) {
								artvalue = -1;
							}

							if (!visible) {
								artlist.get(i).getCbname().setEnabled(visible);
								artlist.get(i).getTxtnoofarticle().setEnabled(
										visible);
								artlist.get(i).getTxtweight().setEnabled(
										visible);
								artlist.get(i).getTxtartvalue().setEnabled(
										visible);
								artlist.get(i).getTxtlength().setEnabled(
										visible);
								artlist.get(i).getTxtbreadth().setEnabled(
										visible);
								artlist.get(i).getTxtheight().setEnabled(
										visible);
								artlist.get(i).getTxtchargeweight().setEnabled(
										visible);
								artlist.get(i).getTxtdesc().setEnabled(visible);
								if (null != cbUnit) {
									cbUnit.setEnabled(false);
								}
							}

						}

						if (btnWeightRoundOff.getSelection()) {
							roundwt = getRoundableWeight();

							if (roundwt != 0
									&& (int) chargedweight % roundwt != 0) {
								chargedweight = (int) chargedweight
										- ((int) chargedweight % roundwt)
										+ roundwt;
							}
						}

						chargedweight = originalChargedWeight(chargedweight);
						if (null != txteffchargedCWT) {
							txteffchargedCWT.setText(String
									.valueOf(chargedweight));
						}

					}
					if (artlist != null && artlist.size() > 0) {
						for (int ins = 0; ins < size; ins++) {
							fromvalue = insurancedto[ins].getFromValue();
							tovalue = insurancedto[ins].getToValue();

							if (tovalue == 0)
								tovalue = artvalue;

							if (artvalue >= fromvalue && artvalue <= tovalue) {
								insurance = insurancedto[ins]
										.getInsuranceChargeValue();
								break;
							}
						}
						dhcPercent = 0;

						float actualQuotationBft = 0;

						if (null == quotationID) {
							// Calculation of rates Sundry LR's

							insurance = 0;
							btnStax.setEnabled(true);
							try {
								float mweight = 0;
								float mfreight = 0;
								int index = 0;
								String tostation = null;
								Integer distance = null;
								int minDistance = 0;
								tostation = cbTo.getText();
								if (tostation != null && !tostation.equals("")) {
									index = tostation.indexOf("-");
									tostation = tostation.substring(index + 2);

									StationsDTO[] stations = handler
											.getStations();
									if (null != stations) {
										int len = stations.length;
										float frombpi = 0;
										float tobpi = 0;
										float fromlrc = 0;
										float tolrc = 0;
										float fromgsc = 0;
										float togsc = 0;
										float fromdhc = 0;
										// float todhc = 0;
										for (int i = 0; i < len; i++) {
											if (stations[i].getId()
													.equalsIgnoreCase(code)) {
												fdiscount = stations[i]
														.getDiscount();
												fincrement = stations[i]
														.getBf_increment();
												frombpi = stations[i].getBpi();
												fromlrc = stations[i]
														.getLrcharge();
												fromgsc = stations[i].getGsc();
												mweight = stations[i]
														.getMin_weight_value();
												mfreight = stations[i]
														.getMin_freight_value();
												fromdhc = stations[i].getDhc();
											} else if (stations[i]
													.getId()
													.equalsIgnoreCase(tostation)) {
												tobpi = stations[i].getBpi();
												tolrc = stations[i]
														.getLrcharge();
												togsc = stations[i].getGsc();
												tdiscount = stations[i]
														.getDiscount();
												tincrement = stations[i]
														.getBf_increment();
												// todhc = stations[i].getDhc();
											}
										}
										bpi = frombpi > tobpi ? frombpi : tobpi;
										lrc = fromlrc > tolrc ? fromlrc : tolrc;
										gsc = fromgsc > togsc ? fromgsc : togsc;
										// dhc = fromdhc > todhc ? fromdhc :
										// todhc;
										dhc = fromdhc;
										fincrement = fincrement > tincrement ? fincrement
												: tincrement;
										fdiscount = fdiscount > tdiscount ? fdiscount
												: tdiscount;

										if (cbic != null)
											cbic.removeAll();
										for (int inc = 0; inc <= fincrement; inc++) {
											cbic.add(String.valueOf(inc));
										}

										// Apply Special Discount For Bangalore
										// Stations

										int SpecialDiscount = handler
												.getSpecialDiscounter(code,
														tostation);

										fdiscount = fdiscount > SpecialDiscount ? fdiscount
												: SpecialDiscount;

										if (!(lrTab.getSelection()[0].getText()
												.equals("UPD"))) {
											if (null != cbdc)
												cbdc.removeAll();
											for (int dec = 0; dec <= fdiscount; dec++) {
												cbdc.add(String.valueOf(dec));
											}

											cbdc.setEnabled(true);
											cbdc.select(0);
										}
										cbic.setEnabled(true);
										cbic.select(0);

									}

									// getting Distance for current station to
									// all
									// other
									// stations
									String distancestation = checkCityStation(code);
									DistanceListDTO[] dto = handler
											.getDistance(distancestation);
									if (null != dto) {
										int len = dto.length;
										for (int i = 0; i < len; i++) {
											// Getting the distance and bpi for
											// TR-City
											// station

											/*
											 * if (checkCityStation(code)
											 * .equalsIgnoreCase(
											 * checkCityStation(tostation))) {
											 * minDistance = regulerdto
											 * .getDistance(); distance =
											 * dto[i].getDistance();
											 * 
											 * bpi = bpi * distance; bpi = (bpi *
											 * dto[i].getIncrementer() / 100) +
											 * bpi;
											 * 
											 * break; }
											 */
											if (dto[i]
													.getDestStation()
													.equalsIgnoreCase(tostation)) {
												// Getting distance and card
												// rate
												// for
												// from
												// station and tostation
												distance = dto[i].getDistance();
												bpi = dto[i].getCardRate();
												bpi = Float
														.parseFloat(getRoundedCeilValue(bpi));
												bpi = (bpi
														* dto[i]
																.getIncrementer() / 100)
														+ bpi;

												bpi = Float
														.parseFloat(getRoundedCeilValue(bpi));
												break;
											}
										}
										System.out.println("didt-->" + distance
												+ "cr-->" + bpi);
									}
									if (null == distance)
										distance = new Integer("0");
									stax = regulerdto.getServiceTax();
									taxlimit = regulerdto.getStaxLimit();

									if (btnSpecial.getSelection()
											&& noofarticle == 1
											&& !isCommodity()) {
										isMixedCommodity = false;
										isCCCommodity = false;
										mixedCommodityValue = 0;
										if (null != btnSpecial)
											btnSpecial.setEnabled(true);
										if (!(lrTab.getSelection()[0].getText()
												.equals("UPD"))) {
											cbdc.setEnabled(false);
											btnccc.setVisible(isAllowedCCC());
										}
										booking_type = 1;

										rate_type = 1;
										int index1 = lrTab.getSelectionIndex();
										if (index1 == 2)
											rate_type = 5;
										// Logic for Special Sundry LR goes
										// here.
										SpecialSundryDTO[] specialdto = handler
												.getSpecialSundryDetails();
										float fromdis = 0;
										float todis = 0;
										float minwt = 0;
										float maxwt = 0;

										for (int spc = 0; spc < specialdto.length; spc++) {
											fromdis = specialdto[spc]
													.getMinDistance();
											todis = specialdto[spc]
													.getMaxDistance();

											if (todis == 9999)
												todis = distance;

											minwt = specialdto[spc]
													.getMinWeight();
											maxwt = specialdto[spc]
													.getMaxWeight();

											if (maxwt == 9999) {
												maxwt = chargedweight;
											}
											if ((distance >= fromdis && distance <= todis)
													&& (chargedweight >= minwt && chargedweight <= maxwt)) {
												bft = specialdto[spc].getBft();

												if (bft == 0) {
													float roundCardRate = 0;
													roundCardRate = bpi;
													roundCardRate = roundCardRate * 100;
													roundCardRate = (float) Math
															.ceil(roundCardRate);
													roundCardRate = roundCardRate / 100;
													bft = roundCardRate
															* chargedweight;
													bft = (float) Math
															.ceil(bft);
												}

												lrc = specialdto[spc].getLrc()
														+ gsc;

											}
										}

										if (bft == 0) {

											float roundCardRate = 0;
											roundCardRate = bpi;
											roundCardRate = roundCardRate * 100;
											roundCardRate = (float) Math
													.ceil(roundCardRate);
											roundCardRate = roundCardRate / 100;
											bft = roundCardRate * chargedweight;
											bft = (float) Math.ceil(bft);

										}
										dhcPercent = dhc;
										dhc = dhc * bft / 100;
									} else if (isCommodity()) {
										isMixedCommodity = false;
										isCCCommodity = false;
										mixedCommodityValue = 0;

										if (null != btnSpecial)
											btnSpecial.setEnabled(false);
										if (!(lrTab.getSelection()[0].getText()
												.equals("UPD"))) {
											cbdc.setEnabled(false);
										}
										booking_type = 2;

										lrc = lrc + gsc;
										bft = getCommodityBFT(distance, bpi,
												mweight);
										btnccc.setVisible(isAllowedCCC());
										if (bft < mfreight)
											bft = mfreight;

										dhcPercent = dhc;
										dhc = dhc * bft / 100;

									} else if (isHLC()) {
										isMixedCommodity = false;
										isCCCommodity = false;
										mixedCommodityValue = 0;
										rate_type = 3;
										int index1 = lrTab.getSelectionIndex();
										if (index1 == 2)
											rate_type = 5;
										booking_type = 4;
										// Logic for HLC LR goes here.
										lrc = lrc + gsc;
										float roundCardRate = 0;
										roundCardRate = bpi;
										roundCardRate = roundCardRate * 100;
										roundCardRate = (float) Math
												.ceil(roundCardRate);
										roundCardRate = roundCardRate / 100;
										bft = roundCardRate * chargedweight;
										bft = (float) Math.ceil(bft);

										dhcPercent = dhc;
										dhc = dhc * bft / 100;

									}

									else {
										isMixedCommodity = false;
										isCCCommodity = false;
										mixedCommodityValue = 0;
										if (!(lrTab.getSelection()[0].getText()
												.equals("UPD"))) {
											if (null != btnSpecial
													&& !btnopenlr
															.getSelection())
												btnSpecial.setEnabled(true);

											btnccc.setVisible(true);
										}
										booking_type = 0;
										rate_type = 0;
										int index1 = lrTab.getSelectionIndex();
										if (index1 == 2)
											rate_type = 5;
										// Logic for Regular Sundry LR goes
										// here.
										lrc = lrc + gsc;
										float roundCardRate = 0;
										roundCardRate = bpi;
										roundCardRate = roundCardRate * 100;
										roundCardRate = (float) Math
												.ceil(roundCardRate);
										roundCardRate = roundCardRate / 100;

										if (chargedweight >= mweight)
											bft = roundCardRate * chargedweight;
										else if (chargedweight < mweight)
											bft = roundCardRate * mweight;

										if (bft < mfreight)
											bft = mfreight;
										bft = (float) Math.ceil(bft);

										dhcPercent = dhc;
										dhc = dhc * bft / 100;

									}
								} else {
									displayError("Select To Station");
								}
							} catch (Exception exception) {
								exception.printStackTrace();
							}

						} else if (null != quotationID) {
							int distance = 0;
							String tostation = "";
							int ind = 0;

							tostation = cbTo.getText();
							if (tostation != null && !tostation.equals("")) {
								ind = tostation.indexOf("-");
								tostation = tostation.substring(ind + 2);

								String distancestation = checkCityStation(code);
								DistanceListDTO[] dto = handler
										.getDistance(distancestation);
								if (null != dto) {
									int leng = dto.length;
									for (int i = 0; i < leng; i++) {
										// Getting the distance and bpi for

										if (dto[i].getDestStation()
												.equalsIgnoreCase(tostation)) {
											// Getting distance and card
											// rate
											// for
											// from
											// station and tostation
											distance = dto[i].getDistance();
											bpi = dto[i].getCardRate();
											bpi = Float
													.parseFloat(getRoundedCeilValue(bpi));
											bpi = (bpi
													* dto[i].getIncrementer() / 100)
													+ bpi;

											bpi = Float
													.parseFloat(getRoundedCeilValue(bpi));
											break;
										}
									}
								}
							}

							actualQuotationBft = getActualbftForQuotation(bpi,
									chargedweight);

							isMixedCommodity = false;
							isCCCommodity = false;
							mixedCommodityValue = 0;

							StationsDTO[] stations = handler.getStations();
							if (null != stations) {
								int len = stations.length;

								float fromlrc = 0;
								float tolrc = 0;
								float fromgsc = 0;
								float togsc = 0;
								float fromdhc = 0;
								// float todhc = 0;
								for (int i = 0; i < len; i++) {
									if (stations[i].getId().equalsIgnoreCase(
											code)) {
										fromlrc = stations[i].getLrcharge();
										fromgsc = stations[i].getGsc();
										fromdhc = stations[i].getDhc();

									} else if (stations[i].getId()
											.equalsIgnoreCase(tostation)) {

										tolrc = stations[i].getLrcharge();
										togsc = stations[i].getGsc();
										// todhc = stations[i].getDhc();

									}
								}

								lrc = fromlrc > tolrc ? fromlrc : tolrc;
								// dhc = fromdhc > todhc ? fromdhc : todhc;
								dhc = fromdhc;
								gsc = fromgsc > togsc ? fromgsc : togsc;
							}
							if (!(lrTab.getSelection()[0].getText().equals(
									"UPD") )) {
								btnccc.setVisible(false);
							}
							btnStax.setEnabled(false);
							// Calculation of rate for Quotation LR's
							booking_type = 3;

							String selectedTab = lrTab.getSelection()[0]
									.getText();
							if (lrType[0].equals(selectedTab)
									|| lrType[1].equals(selectedTab)) {
								rate_type = 4;
							} else if (lrType[2].equals(selectedTab)) {
								rate_type = 5;
							}

							cbic.add("0");

							cbic.select(0);

							cbic.setEnabled(false);
							if (!(lrTab.getSelection()[0].getText().equals(
									"UPD") && btnRebooklr.getSelection())) {
								cbdc.add("0");
								cbdc.select(0);
								cbdc.setEnabled(false);
							}

							if (null != tostation
									&& null != quotationAllDetails) {
								// getting all the quotation values
								/*
								 * if (quotationType == 0) quotationAllDetails =
								 * handler.getQuotationDetails( quotationID,
								 * checkCityStation(tostation)); else
								 * quotationAllDetails =
								 * handler.getQuotationDetails( quotationID,
								 * checkCityStation(handler .getStationCode()));
								 */
								
								isQST = quotationAllDetails.isServiceTax();
								if (quotationAllDetails.getType() == 0
										|| quotationAllDetails.getType() == 1) {
									// For calculation for weight and Article
									// based
									// Quotation here.
									bft = quotationAllDetails
											.getQuotationDetails()[0].getBft();
									// bft = bft * chargedweight;
									float minbf = quotationAllDetails
											.getMinFreightValue();
									if (quotationAllDetails.getType() == 1) {

										bft = bft * noofarticle;

									} else if (quotationAllDetails.getType() == 0) {// For
										// Weight
										// Based
										// Quotation
										float mincwt = quotationAllDetails
												.getMinWeightValue();
										if (mincwt < chargedweight)
											bft = bft * chargedweight;
										else
											bft = bft * mincwt;
									}

									if (bft < minbf) {
										bft = minbf;
									}

									bft = (int) Math.ceil(bft);

									if (quotationAllDetails.getLrchargeType() == 0)
										// lrc is same as sundry
										// lrc = regulerdto.getLrCharge();
										lrc = lrc;
									else
										// Manual lrc value
										lrc = quotationAllDetails.getLrCharge();

									if (quotationAllDetails.getDhcType() == 0) {
										// lrc is same as sundry
										// lrc = regulerdto.getLrCharge();
										dhcPercent = dhc;
										dhc = dhc * bft / 100;
									} else {
										// Manual lrc value
										dhcPercent = quotationAllDetails
												.getDhc();
										dhc = quotationAllDetails.getDhc()
												* bft / 100;
									}

									if (quotationAllDetails.getGscType() == 0)
										gsc = gsc;
									else
										// Manual lrc value
										gsc = quotationAllDetails.getGsc();

									lrc = gsc + lrc;

									if (quotationAllDetails
											.getQuotationDetails()[0]
											.getCcchargeType() == 0) {
										// %bft for CCC
										ccc = quotationAllDetails
												.getQuotationDetails()[0]
												.getCcchargeValue()
												* bft / 100;
										txtCcc.setEnabled(false);
										if (bft == 0) {
											txtCcc.setEnabled(false);
										}
									} else if (quotationAllDetails
											.getQuotationDetails()[0]
											.getCcchargeType() == 1) {
										// perarticle for CCC
										ccc = quotationAllDetails
												.getQuotationDetails()[0]
												.getCcchargeValue()
												* noofarticle;
										txtCcc.setEnabled(false);
									} else if (quotationAllDetails
											.getQuotationDetails()[0]
											.getCcchargeType() == 2) {
										// Open for CCC
										ccc = 0;
										txtCcc.setEnabled(true);
									} else if (quotationAllDetails
											.getQuotationDetails()[0]
											.getCcchargeType() == 3) {
										ccc = 0;
										txtCcc.setEnabled(false);
									}
									if (quotationAllDetails
											.getQuotationDetails()[0]
											.getDcchargeType() == 0) {
										// %bft for DCC
										dcc = quotationAllDetails
												.getQuotationDetails()[0]
												.getDcchargeValue()
												* bft / 100;
										txtDcc.setEnabled(false);
										if (dcc == 0) {
											txtDcc.setEnabled(false);
										}
									} else if (quotationAllDetails
											.getQuotationDetails()[0]
											.getDcchargeType() == 1) {
										// per article for DCC
										dcc = quotationAllDetails
												.getQuotationDetails()[0]
												.getDcchargeValue()
												* noofarticle;
										txtDcc.setEnabled(false);
									} else if (quotationAllDetails
											.getQuotationDetails()[0]
											.getDcchargeType() == 2) {
										// Open DCC
										dcc = 0;
										txtDcc.setEnabled(true);
									} else if (quotationAllDetails
											.getQuotationDetails()[0]
											.getDcchargeType() == 3) {
										// Dont Charge
										dcc = 0;
										txtDcc.setEnabled(false);
									}
									if (quotationAllDetails
											.getQuotationDetails()[0]
											.getDdchargeType() == 0) {
										ddc = 0;
										txtDdc.setEnabled(false);

										float tempvalueperart = 0;

										tempvalueperart = noofarticle
												* quotationAllDetails
														.getQuotationDetails()[0]
														.getDdchargeArticle();
										ddcfree = tempvalueperart;

									} else if (quotationAllDetails
											.getQuotationDetails()[0]
											.getDdchargeType() == 2) {
										ddc = 0;
										
										txtDdc.setEnabled(true);
									} else {
										ddc = noofarticle
												* quotationAllDetails
														.getQuotationDetails()[0]
														.getDdchargeArticle();
										ddc = quotationAllDetails
												.getQuotationDetails()[0]
												.getMinDdchargeValue() > ddc ? quotationAllDetails
												.getQuotationDetails()[0]
												.getMinDdchargeValue()
												: ddc;
										txtDdc.setEnabled(false);
									}

									if (quotationAllDetails
											.getQuotationDetails()[0]
											.getLcchargeType() == 0) {
										lcc = bft
												* quotationAllDetails
														.getQuotationDetails()[0]
														.getLcchargeValue()
												/ 100;
										txtLc.setEnabled(false);
										if (lcc == 0) {
											txtLc.setEnabled(false);
										}
									} else if (quotationAllDetails
											.getQuotationDetails()[0]
											.getLcchargeType() == 1) {
										lcc = noofarticle
												* quotationAllDetails
														.getQuotationDetails()[0]
														.getLcchargeValue();
										txtLc.setEnabled(false);
									} else if (quotationAllDetails
											.getQuotationDetails()[0]
											.getLcchargeType() == 3) {
										lcc = 0;
										txtLc.setEnabled(false);

									}
									if (quotationAllDetails
											.getQuotationDetails()[0]
											.getIechargeType() == 0) {
										iec = bft
												* quotationAllDetails
														.getQuotationDetails()[0]
														.getIechargeValue()
												/ 100;
										if (iec == 0) {
											txtIec.setEnabled(false);
										}
									} else if (quotationAllDetails
											.getQuotationDetails()[0]
											.getIechargeType() == 1) {
										iec = noofarticle
												* quotationAllDetails
														.getQuotationDetails()[0]
														.getIechargeValue();
									} else if (quotationAllDetails
											.getQuotationDetails()[0]
											.getIechargeType() == 3) {
										iec = 0;
										txtIec.setEnabled(false);

									}

									if (quotationAllDetails
											.getInsuranceChargeType() == 1) {
										InsuranceDTO[] insdto = quotationAllDetails
												.getInsuranceCharges();
										int inslen = insdto.length;
										for (int ins = 0; ins < inslen; ins++) {
											fromvalue = insdto[ins]
													.getFromValue();
											tovalue = insdto[ins].getToValue();
											if (tovalue == 0)
												tovalue = artvalue;
											if (artvalue >= fromvalue
													&& artvalue <= tovalue) {
												insurance = insdto[ins]
														.getInsuranceChargeValue();
												break;
											}
										}

									} else if (quotationAllDetails
											.getInsuranceChargeType() == 2) {
										insurance = 0;
									}

								} else if (quotationAllDetails.getType() == 2) {
									float tempbft = 0;
									int tempnoofarticle = 0;
									ClientQuotationDetailsDTO[] client = quotationAllDetails
											.getQuotationDetails();
									int len = client.length;
									String isEmpty = null;
									for (int j = 0; j < len; j++) {
										for (int k = 0; k < artlist.size(); k++) {
											int indextemp = artlist.get(k)
													.getCbname()
													.getSelectionIndex();
											if (indextemp > -1) {
												String artname = artlist.get(k)
														.getCbname().getItem(
																indextemp);

												if (artname.equals(client[j]
														.getArticleName())) {

													isEmpty = artlist
															.get(k)
															.getTxtnoofarticle()
															.getText().trim();
													if (!isEmpty
															.equalsIgnoreCase(""))
														tempnoofarticle = Integer
																.parseInt(isEmpty);

													tempbft = client[j]
															.getBft()
															* tempnoofarticle;
													bft += tempbft;
													if (artlist.size() == 1) {
														float chkbft = quotationAllDetails
																.getMinFreightValue();
														if (chkbft > tempbft) {
															tempbft = chkbft;

														}
														tempbft = (int) Math
																.ceil(tempbft);
													}
													if (client[j]
															.getCcchargeType() == 0) {
														ccc += tempbft
																* client[j]
																		.getCcchargeValue()
																/ 100;
														txtCcc
																.setEnabled(false);
													}

													else if (client[j]
															.getCcchargeType() == 1) {
														ccc += tempnoofarticle
																* client[j]
																		.getCcchargeValue();
														txtCcc
																.setEnabled(false);
													} else if (client[j]
															.getCcchargeType() == 2) {
														ccc += 0;
														txtCcc.setEnabled(true);

													} else if (client[j]
															.getCcchargeType() == 3) {
														ccc += 0;
														txtCcc
																.setEnabled(false);

													}
													if (client[j]
															.getDcchargeType() == 0) {
														dcc += tempbft
																* client[j]
																		.getDcchargeValue()
																/ 100;
														txtDcc
																.setEnabled(false);
													} else if (client[j]
															.getDcchargeType() == 1) {
														dcc += tempnoofarticle
																* client[j]
																		.getDcchargeValue();
														txtDcc
																.setEnabled(false);
													} else if (client[j]
															.getDcchargeType() == 2) {
														dcc += 0;
														txtDcc.setEnabled(true);
													} else if (client[j]
															.getDcchargeType() == 3) {
														dcc += 0;
														txtDcc
																.setEnabled(false);
													}

													if (client[j]
															.getIechargeType() == 0) {
														iec += tempbft
																* client[j]
																		.getIechargeValue()
																/ 100;
													} else if (client[j]
															.getIechargeType() == 1) {
														iec += tempnoofarticle
																* client[j]
																		.getIechargeValue();
													} else if (client[j]
															.getIechargeType() == 3) {
														iec += 0;
														txtIec
																.setEnabled(false);
													}

													if (client[j]
															.getLcchargeType() == 0) {
														lcc += tempbft
																* client[j]
																		.getLcchargeValue()
																/ 100;
														txtLc.setEnabled(false);
													} else if (client[j]
															.getLcchargeType() == 1) {
														lcc += tempnoofarticle
																* client[j]
																		.getLcchargeValue();
														txtLc.setEnabled(false);
													} else if (client[j]
															.getLcchargeType() == 3) {
														lcc += 0;
														txtLc.setEnabled(false);
													}

													if (client[j]
															.getDdchargeType() == 0) {
														ddc += 0;
														txtDdc
																.setEnabled(false);

														float tempvalueperart = 0;
														tempvalueperart = tempnoofarticle
																* client[j]
																		.getDdchargeArticle();

														ddcfree += tempvalueperart;
													} else if (client[j]
															.getDdchargeType() == 2) {
														ddc += 0;
														txtDdc.setEnabled(true);
													} else {
														float tempweightperlr = 0;
														tempweightperlr = client[j]
																.getMinDdchargeValue();

														float tempvalueperart = 0;
														tempvalueperart = tempnoofarticle
																* client[j]
																		.getDdchargeArticle();

														tempvalueperart = tempvalueperart > tempweightperlr ? tempvalueperart
																: tempweightperlr;

														ddc += tempvalueperart;
														txtDdc
																.setEnabled(false);
													}

													break;
												}
											}
										}
									}

									if (quotationAllDetails.getLrchargeType() != 0)
										lrc = quotationAllDetails.getLrCharge();
									else
										// lrc = regulerdto.getLrCharge();
										lrc = lrc;

									if (quotationAllDetails.getDhcType() != 0) {
										dhcPercent = quotationAllDetails
												.getDhc();
										dhc = quotationAllDetails.getDhc()
												* bft / 100;
									} else {
										// lrc = regulerdto.getLrCharge();
										dhcPercent = dhc;
										dhc = dhc * bft / 100;
									}

									if (quotationAllDetails.getGscType() == 0)
										gsc = gsc;
									else
										// Manual lrc value
										gsc = quotationAllDetails.getGsc();

									lrc = gsc + lrc;

									if (quotationAllDetails
											.getInsuranceChargeType() == 1) {
										InsuranceDTO[] insdto = quotationAllDetails
												.getInsuranceCharges();
										int inslen = insdto.length;
										for (int ins = 0; ins < inslen; ins++) {
											fromvalue = insdto[ins]
													.getFromValue();
											tovalue = insdto[ins].getToValue();
											if (tovalue == 0)
												tovalue = artvalue;
											if (artvalue >= fromvalue
													&& artvalue <= tovalue) {
												insurance = insdto[ins]
														.getInsuranceChargeValue();
												break;
											}
										}

									} else if (quotationAllDetails
											.getInsuranceChargeType() == 2) {
										insurance = 0;
									}

									float chkbft = quotationAllDetails
											.getMinFreightValue();
									if (chkbft > bft) {
										bft = chkbft;

									}
									bft = (int) Math.ceil(bft);
								}
							}
						}

						actualBft = 0;
						temp = new BookingDTO();

						if (quotationID == null) {
							actualBft = (int) Math.ceil(bft);

							temp.setBft((int) Math.ceil(getBFT(String
									.valueOf(actualBft))));
							float incrementBft = temp.getBft();
							
							System.out.println("Sundry-Actual" + actualBft);
						} else {
							actualBft = actualQuotationBft;
							temp.setBft((int) Math.ceil(bft));
							System.out.println("Quotation-Actual" + actualBft
									+ "Quo" + actualQuotationBft);
						}

						temp.setLrc(lrc);
						temp.setDhc(dhc);
						temp.setInsuranceCharge(insurance);
						temp.setCcc(ccc);
						temp.setDdc(ddc);

						temp.setDcc(dcc);
						temp.setIec(iec);
						temp.setLc(getNormalRounded2Decimal(lcc));
						if (null != temp)
							populateRates(temp);
					} else {
						displayError("Please Provide Articles");
					}

				}
			} else {
				clearRatesFields(false);
			}
		}

		/**
		 * 
		 * @param bpi
		 * @param chargedweight
		 * @param mweight
		 * @param mfreight
		 * @return
		 */
		private float getActualbftForQuotation(float bpi, float chargedweight)
				throws Exception {
			// System.out.println("quot BPI-->"+bpi);

			float temp = 0;
			float roundCardRate = 0;
			float tmweight = 0;
			float tmfreight = 0;

			StationsDTO[] stations = handler.getStations();
			String code = handler.getStationCode();
			if (null != stations) {
				int len = stations.length;
				for (int i = 0; i < len; i++) {
					if (stations[i].getId().equalsIgnoreCase(code)) {

						tmweight = stations[i].getMin_weight_value();
						tmfreight = stations[i].getMin_freight_value();

					}
				}
			}
			roundCardRate = bpi;
			roundCardRate = roundCardRate * 100;
			roundCardRate = (float) Math.ceil(roundCardRate);
			roundCardRate = roundCardRate / 100;

			if (chargedweight >= tmweight)
				temp = roundCardRate * chargedweight;
			else if (chargedweight < tmweight)
				temp = roundCardRate * tmweight;
			if (temp < tmfreight)
				temp = tmfreight;
			temp = (float) Math.ceil(temp);
			System.out.println("quot BPI put-->" + temp);
			return temp;
		}

		/**
		 * 
		 * @param artlist
		 * @return
		 */
		private boolean isValidArticleDetails(ArrayList<ArticleUIDTO> artlist) {
			try {
				for (int i = 0; i < artlist.size(); i++) {
					String artname = artlist.get(i).getCbname().getText();
					String noa = artlist.get(i).getTxtnoofarticle().getText();
					String chargedwt = artlist.get(i).getTxtchargeweight()
							.getText();
					String selectedTab = lrTab.getSelection()[0].getText();
					if (artname.equalsIgnoreCase(EMPTY_STRING)
							|| noa.equalsIgnoreCase(EMPTY_STRING)
							|| chargedwt.equalsIgnoreCase(EMPTY_STRING)) {
						displayError("Please Provide Values for Articles");
						return false;
					}

					else if ((artname.equalsIgnoreCase("House Hold Goods")
							|| artname.equalsIgnoreCase("Two wheeler")
							|| artname.equalsIgnoreCase("Return Goods") || artname
							.equalsIgnoreCase("Two Wheeler - Bullet"))
							&& (!lrType[1].equals(selectedTab))) {
						return false;
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return true;
		}

		/**
		 * 
		 */
		private void applyFOC() {
			String zero = "0";
			boolean enable = false;
			txtBft.setText(zero);
			txtBft.setEnabled(enable);
			txtLrc.setText(zero);
			txtLrc.setEnabled(enable);
			txtDhc.setText(zero);
			txtDhc.setEnabled(enable);
			txtCcc.setText(zero);
			txtCcc.setEnabled(enable);
			txtDcc.setText(zero);
			txtDcc.setEnabled(enable);
			txtDdc.setText(zero);
			txtDdc.setEnabled(enable);
			txtIec.setText(zero);
			txtIec.setEnabled(enable);
			txtLc.setText(zero);
			txtLc.setEnabled(enable);
			txtGsc.setText(zero);
			txtGsc.setEnabled(enable);
			txtStax.setText(zero);
			txtStax.setEnabled(enable);
			txtTotal.setText(zero);
			txtTotal.setEnabled(enable);
			cbic.setText(zero);
			cbic.setEnabled(enable);
			cbdc.setText(zero);
			cbdc.setEnabled(enable);
			btnStax.setSelection(false);
			btnStax.setEnabled(false);

		}

		/**
		 * Method to populate article in the Article Table
		 * 
		 * @param clientdto
		 */


		/**
		 * 
		 */
		
		/**
		 * 
		 * @return
		 * @throws Exception
		 */
		public int getRoundableWeight() throws Exception {
			String code = handler.getStationCode();
			RegularSundryDTO regulerdto;
			try {
				regulerdto = handler.getSundryDetails(code);
				return roundwt = regulerdto.getWeightRoundOff();
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception();
			}
		}

		/**
		 * 
		 * Method to populate all rates
		 * 
		 * @param temp
		 */
		private void populateRates(BookingDTO temp) {

			try {
				if (null != temp) {
					txtBft.setText(String.valueOf((int) Math
							.ceil(temp.getBft())));
					if (!(lrTab.getSelection()[0].getText().equals("UPD") && (btnRebooklr.getSelection()|| btnToUPD.getSelection()))) {
						txtGsc.setText(String.valueOf((int) Math.ceil(temp
								.getInsuranceCharge())));

						txtIec
								.setText(String
										.valueOf(getNormalRounded2Decimal(temp
												.getIec())));
					}
					txtLrc.setText(String.valueOf((int) Math
							.ceil(temp.getLrc())));

					txtDhc.setText(String.valueOf((int) Math
							.ceil(temp.getDhc())));

					txtStax.setText(String.valueOf(getRounded2Decimal(temp
							.getStax())));

					boolean isCCCEnable = false;
					if (!(lrTab.getSelection()[0].getText().equals("UPD") && (btnRebooklr.getSelection() || btnToUPD.getSelection()))) {
						if (booking_type == 0 || booking_type == 1
								|| booking_type == 2) {
							if (booking_type == 0 || isAllowedCCC()) {
								isCCCEnable = true;
								applyValidCCValue();
							} else {
								txtmaxccc.setText("0.00");
							}
						} else if (booking_type == 4) {
							applyHLCCCValue();
						} else

							txtCcc.setText(String
									.valueOf(getNormalRounded2Decimal(temp
											.getCcc())));
						txtDcc
								.setText(String
										.valueOf(getNormalRounded2Decimal(temp
												.getDcc())));
						
						txtGsc.setEnabled(false);
						txtIec.setEnabled(false);
						txtLrc.setEnabled(false);
					}
					txtDdc.setText(String.valueOf((int) Math.ceil(temp
							.getDdc())));

					txtLc.setText(String.valueOf(getNormalRounded2Decimal(temp
							.getLc())));

					txtBft.setEnabled(false);

					txtDhc.setEnabled(false);
					txtStax.setEnabled(false);

					if (booking_type == 0)
						setFieldsStatus(true, true, true, false, false);
					else if (booking_type == 1 || booking_type == 2)
						setFieldsStatus(isCCCEnable, true, true, false, false);
					else if (booking_type == 4) {
						setCompEnabled(true);
						txtCcc.setEnabled(false);

					}

				}
				applyServiceTax();

				System.runFinalization();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}

		/**
		 * 
		 */
		private void applyHLCCCValue() {
			if (btnccc.getSelection()) {
				CustomerDTO[] dto = customerHandler
						.populateCustomersFromRemote();
				boolean cchlcstation = false;
				if (null != dto) {
					String selectedCustomer = null;
					int index = -1;
					index = cbConsignorName.getSelectionIndex();
					if (index > -1) {
						selectedCustomer = cbConsignorName.getItem(index);
					} else {
						selectedCustomer = cbConsignorName.getText();
					}

					for (int i = 0; i < dto.length; i++) {
						if (selectedCustomer.equalsIgnoreCase(dto[i].getName())) {
							cchlcstation = true;
							if (null != artlist && artlist.size() > 0) {
								if (!artlist.get(0).getTxtnoofarticle()
										.getText().equalsIgnoreCase("")) {
									float ccc = dto[i].getCcperarticle()
											* Float.parseFloat(artlist.get(0)
													.getTxtnoofarticle()
													.getText());
									if (ccc == 0) {
										ccc = getCCForHLC();
										// System.out.println("CCC Value for the
										// station"+ ccc);
									}

									txtCcc
											.setText(String
													.valueOf(getNormalRounded2Decimal(ccc)));
									txtCcc.setEnabled(false);
									// btnccc.setVisible(false);

								}
							}
						}
					}

				}
				if (cchlcstation == false) {
					if (null != artlist && artlist.size() > 0) {
						if (!artlist.get(0).getTxtnoofarticle().getText()
								.equalsIgnoreCase("")) {
							float ccc = getCCForHLC()
									* Float.parseFloat(artlist.get(0)
											.getTxtnoofarticle().getText());
							txtCcc.setText(String
									.valueOf(getNormalRounded2Decimal(ccc)));
							txtCcc.setEnabled(false);
							// btnccc.setVisible(false);

						}
					}
				}
			} else {
				txtCcc.setText(String.valueOf("0"));
				txtCcc.setEnabled(false);

			}

		}

		/**
		 * 
		 * @return
		 */
		private float getCCForHLC() {
			try {
				StationsDTO[] dto = handler.getStations();
				if (null != dto) {
					int len = dto.length;
					for (int i = 0; i < len; i++) {
						if (dto[i].getId().equalsIgnoreCase(
								handler.getStationCode())) {
							return dto[i].getCc_hlc();
						}
					}
				}
			} catch (Exception e) {

			}
			return 0;
		}

		/**
		 * 
		 * @return
		 * @throws Exception
		 */
		private boolean isAllowedCCC() throws Exception {
			CCProfileDTO[] dto = handler.getCCDetails();
			if (null != dto) {
				int len = dto.length;
				for (int i = 0; i < len; i++) {
					if (dto[i].getStation_code().equalsIgnoreCase(
							handler.getStationCode())) {
						if (booking_type == 1) {
							if (dto[i].getCc_special() == 1) {
								return true;
							} else {
								txtCcc.setText("0.00");
								txtCcc.setEnabled(false);
								return false;
							}
						} else if (booking_type == 2) {
							if (dto[i].getCc_commodity() == 1) {
								isCCCommodity = true;
								return true;
							} else if (isMixedCommodity) {
								return true;
							} else {
								txtCcc.setText("0.00");
								txtCcc.setEnabled(false);
								return false;
							}
						}
					}
				}
			}
			return false;
		}

		/**
		 * 
		 */
		private void applyValidCCValue() {
			try {
				String selectedTab = lrTab.getSelection()[0].getText();
				if ((null == quotationID)
						&& booking_type != 4
						&& (lrType[0].equals(selectedTab) || lrType[1]
								.equals(selectedTab))) {
					if (null != txtBft) {
						float bft = 0;
						if (!txtBft.getText().equalsIgnoreCase(EMPTY_STRING))
							bft = Float.parseFloat(txtBft.getText());

						int limit = getCCLimit();

						if (limit == 0) {

							txtCcc.setText("0.0");
							txtmaxccc.setText("");
							txtCcc.setEnabled(false);
							btnccc.setEnabled(false);
							btnccc.setVisible(false);

						} else if (limit == -1) {
							if (btnccc.getSelection()) {
								// ALL
								float defaultCC = bft / 10;
								defaultCC = getNormalRounded2Decimal(defaultCC);
								txtmaxccc.setText("");
								// txtCcc.setText("0.0");
								txtCcc.setEnabled(true);
								txtCcc.setText(String.valueOf(defaultCC));
								btnccc.setVisible(true);
							} else {
								// txtCcc.setText("0");
								txtCcc.setEnabled(false);
								txtmaxccc.setText("0");
								txtCcc.setText("");
							}

						} else {
							if ((booking_type == 0 || isAllowedCCC())
									&& btnccc.getSelection()) {

								if (isMixedCommodity == true
										&& isCCCommodity == false) {
									txtCcc
											.setText(String
													.valueOf(getNormalRounded2Decimal(mixedCommodityValue
															* limit / 100)));
									txtCcc.setEnabled(true);
									txtmaxccc
											.setText(String
													.valueOf(getNormalRounded2Decimal(mixedCommodityValue
															* limit / 100)));
								} else {

									txtCcc
											.setText(String
													.valueOf(getNormalRounded2Decimal(bft
															* limit / 100)));
									txtCcc.setEnabled(true);
									txtmaxccc
											.setText(String
													.valueOf(getNormalRounded2Decimal(bft
															* limit / 100)));
								}
							} else {
								txtCcc.setText("0");
								txtCcc.setEnabled(false);
								txtmaxccc.setText("0");
							}

							System.out.println("Mixed commodity Value"
									+ mixedCommodityValue + "CC Limit" + limit
									+ "Totalbft" + bft + "isCCCommmodity"
									+ isCCCommodity);
						}
					}

				} else if (booking_type == 4) {
					applyHLCCCValue();
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		/**
		 * 
		 * @param ccc
		 * @param dcc
		 * @param ddc
		 * @param iec
		 * @param lcc
		 */
		private void setFieldsStatus(boolean ccc, boolean dcc, boolean ddc,
				boolean iec, boolean lcc) {

			/*
			 * if (ccc == true) { txtCcc.setEnabled(true); } else {
			 * txtCcc.setEnabled(false); txtCcc.setText("0"); }
			 */
			if (!(lrTab.getSelection()[0].getText().equals("UPD") && (btnRebooklr.getSelection() || btnToUPD.getSelection()))) {
				if (dcc == true) {
					txtDcc.setEnabled(true);
				} else {
					txtDcc.setEnabled(false);
					txtDcc.setText("0");
				}

				

				if (iec == true) {
					txtIec.setEnabled(true);
				} else {
					txtIec.setEnabled(false);
					txtIec.setText("");
				}
			}
			
			if (ddc == true) {
				txtDdc.setEnabled(true);
			} else {
				txtDdc.setEnabled(false);
				txtDdc.setText("0");
			}
			if (lcc == true) {

				txtLc.setEnabled(true);
			} else {
				txtLc.setEnabled(false);
				txtLc.setText("");
			}

		}

		/**
		 * 
		 * @param value
		 * @return
		 */
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

		/**
		 * 
		 * @param txtConsignorCST
		 * @param source
		 */
		private void consignorAction(Combo selection, Text address,
				Text txtConsignorCST) {

			int index = selection.getSelectionIndex();
			String name = null;

			if (index > -1) {
				name = selection.getItem(index);
			} else {
				address.setEnabled(true);
				name = null;
			}
			try {
				if (null != name) {
					CustomerDTO dto = null;
					dto = customerHandler.getAddressByName(name, false);

					if (null != dto) {

						if (dto.getAddress() != null)
							address.setText(dto.getAddress());
						address.setEnabled(false);
						if (dto.getTinNo() != null)
							txtConsignorCST.setText(dto.getTinNo());
						if (dto.getPhone() != null)
							txtConsignorPhone.setText(dto.getPhone());
						if (dto.getLandLine() != null)
							txtCnorLandline.setText(dto.getLandLine());

						if (dto.getQuotationId() != null) {
							txtCnorSMS
									.setText(String.valueOf(dto.getCnorSMS()));
							txtCneeSMS
									.setText(String.valueOf(dto.getCneeSMS()));
						}

						// System.out.println("name-->"+name+"--->"+dto.getCnorSMS()+"--->"+dto.getCneeSMS()+"Qty--->"+dto.getQuotationId());

						btnSaveConsignorDetails.setEnabled(false);
					} else {
						address.setText(EMPTY_STRING);
						txtConsignorCST.setText(EMPTY_STRING);
						txtConsignorPhone.setText(EMPTY_STRING);
						txtCnorLandline.setText(EMPTY_STRING);
						address.setEnabled(true);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		/**
		 * 
		 * @param selection
		 * @param address
		 * @param txtConsigneeCST
		 */
		private void consigneeAction(Combo selection, Text address,
				Text txtConsigneeCST) {

			int index = selection.getSelectionIndex();
			String name = null;

			if (index > -1) {
				name = selection.getItem(index);
			} else {
				address.setEnabled(true);
				name = null;
			}
			try {
				if (null != name) {
					CustomerDTO dto = null;
					dto = customerHandler.getAddressByName(name, true);

					if (null != dto) {
						if (dto.getAddress() != null)
							address.setText(dto.getAddress());
						address.setEnabled(false);
						if (dto.getTinNo() != null)
							txtConsigneeCST.setText(dto.getTinNo());
						if (dto.getPhone() != null)
							txtConsigneePhone.setText(dto.getPhone());

						if (dto.getLandLine() != null)
							txtCneeLandline.setText(dto.getLandLine());

						if (dto.getQuotationId() != null) {
							txtCnorSMS
									.setText(String.valueOf(dto.getCnorSMS()));
							txtCneeSMS
									.setText(String.valueOf(dto.getCneeSMS()));
						}

						btnSaveConsigneeDetails.setEnabled(false);
					} else {
						address.setText(EMPTY_STRING);
						txtConsigneeCST.setText(EMPTY_STRING);
						txtConsigneePhone.setText(EMPTY_STRING);
						txtCneeLandline.setText(EMPTY_STRING);
						address.setEnabled(true);
					}

				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		public void widgetDefaultSelected(SelectionEvent arg0) {

		}

		public void focusGained(FocusEvent arg0) {

		}

		/**
		 * 
		 */
		public void focusLost(FocusEvent event) {

			Object source = event.getSource();

			if (cbConsigneeName == source) {
				if (cbConsigneeName.getSelectionIndex() == -1
						&& !cbConsigneeName.getText().equals(EMPTY_STRING)) {
					txtConsignee.setEnabled(true);
					btnSaveConsigneeDetails.setEnabled(true);
					txtConsignee.setText("");
					try {
						String name = cbConsigneeName.getText();
						CustomerDTO dto = null;

						if (null != name) {

							dto = customerHandler.getAddressByName(name, true);

						}
						if (null != dto) {
							displayError("Name already exists, Type a new name");
							cbConsigneeName.setText("");
						}

					} catch (Exception e) {
						e.printStackTrace();
					}

					clearArticleTable();
					txteffchargedCWT.setText(EMPTY_STRING);
					findBookingType();
					Combo selection = null;
					Text address = null;
					selection = cbConsigneeName;
					address = txtConsignee;
					consigneeAction(selection, address, txtConsigneeCST);
				}

			} else if (cbConsignorName == source) {

				if (cbConsignorName.getSelectionIndex() == -1
						&& !cbConsignorName.getText().equals(EMPTY_STRING)) {
					txtConsignor.setEnabled(true);
					btnSaveConsignorDetails.setEnabled(true);
					txtConsignor.setText("");

					try {
						String name = cbConsignorName.getText();
						CustomerDTO dto = null;
						if (null != name) {
							dto = customerHandler.getAddressByName(name, false);
						}

						if (null != dto) {
							displayError("Name already exists, Type a new name");
							cbConsignorName.setText("");
						}

					} catch (Exception e) {
						e.printStackTrace();
					}

					clearArticleTable();
					txteffchargedCWT.setText(EMPTY_STRING);
					findBookingType();
					Combo selection = null;
					Text address = null;
					selection = cbConsignorName;
					address = txtConsignor;
					consignorAction(selection, address, txtConsignorCST);
				}

			} else {
				if (source == txtCcc) {
					if (null != btnopenlr && !btnopenlr.getSelection())
						isValidCCValue();
					else if (null == btnopenlr)
						isValidCCValue();
				} else if (source == txtBft) {
					applyMinimumFreightForOpenLr();
					applyDHCValue(txtBft.getText());
					if (null != btnopenlr && !btnopenlr.getSelection())
						applyValidCCValue();
					else if (null == btnopenlr)
						applyValidCCValue();

				}
				applyServiceTax();
			}
		}

		/**
		 * 
		 */
		private void applyMinimumFreightForOpenLr() {
			try {
				String selectedTab = lrTab.getSelection()[0].getText();

				String tempbft = txtBft.getText();
				if (!selectedTab.equalsIgnoreCase("Billing")) {
					// if (!tempbft.equalsIgnoreCase(EMPTY_STRING)) {
					float tempbftin = 0;
					float tempbftactual = 0;
					if (quotationID == null) {
						StationsDTO[] dto = handler.getStations();
						int len = dto.length;
						for (int i = 0; i < len; i++) {
							if (dto[i].getId().equalsIgnoreCase(
									handler.getStationCode())) {
								tempbftactual = dto[i].getMin_freight_value();
								break;
							}
						}
					} else if (quotationID != null) {

						int index = cbTo.getSelectionIndex();
						String tostation = null;
						if (index > -1) {
							tostation = cbTo.getItem(index);
							index = tostation.indexOf("-");
							tostation = tostation.substring(index + 2);
						}
						/*
						 * if (quotationType == 0) quotationAllDetails =
						 * handler.getQuotationDetails(quotationID,
						 * checkCityStation(tostation)); else
						 * quotationAllDetails =
						 * handler.getQuotationDetails(quotationID,
						 * checkCityStation(handler.getStationCode()));
						 */
						tempbftactual = quotationAllDetails
								.getMinFreightValue();
					}

					if (!tempbft.equalsIgnoreCase(EMPTY_STRING)) {
						tempbftin = Float.parseFloat(tempbft);
					}
					tempbftin = tempbftin > tempbftactual ? tempbftin
							: tempbftactual;

					if (quotationID != null) {
						applyPercentageBftForOpenLrWithQuotation((int) Math
								.ceil(tempbftin), quotationAllDetails);
					}
					txtBft.setText(String.valueOf((int) Math.ceil(tempbftin)));
				} else {
					txtBft.setText(String.valueOf((int) Math.ceil(Float
							.parseFloat(tempbft))));
				}

				// }
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		/**
		 * 
		 * @param bft
		 * @param clientalldto
		 */
		private void applyPercentageBftForOpenLrWithQuotation(int bft,
				ClientQuotationDTO clientalldto) {
			float ccc = 0;
			float dcc = 0;
			float lcc = 0;
			float iec = 0;
			if (clientalldto.getType() == 0 || clientalldto.getType() == 1) {

				if (clientalldto.getQuotationDetails()[0].getCcchargeType() == 0) {
					// %bft for CCC
					ccc = clientalldto.getQuotationDetails()[0]
							.getCcchargeValue()
							* bft / 100;

					txtCcc.setText(String
							.valueOf(getNormalRounded2Decimal(ccc)));

				}
				if (clientalldto.getQuotationDetails()[0].getDcchargeType() == 0) {
					// %bft for DCC
					dcc = clientalldto.getQuotationDetails()[0]
							.getDcchargeValue()
							* bft / 100;
					txtDcc.setText(String
							.valueOf(getNormalRounded2Decimal(dcc)));
				}

				if (clientalldto.getQuotationDetails()[0].getLcchargeType() == 0) {
					lcc = bft
							* clientalldto.getQuotationDetails()[0]
									.getLcchargeValue() / 100;

					txtLc
							.setText(String
									.valueOf(getNormalRounded2Decimal(lcc)));
				}
				if (clientalldto.getQuotationDetails()[0].getIechargeType() == 0) {
					iec = bft
							* clientalldto.getQuotationDetails()[0]
									.getIechargeValue() / 100;
					txtIec.setText(String
							.valueOf(getNormalRounded2Decimal(iec)));
				}

			} else if (clientalldto.getType() == 2) {

				ClientQuotationDetailsDTO[] client = clientalldto
						.getQuotationDetails();
				int len = client.length;

				for (int j = 0; j < len; j++) {
					if (artlist.size() == 1) {
						for (int k = 0; k < artlist.size(); k++) {
							int indextemp = artlist.get(k).getCbname()
									.getSelectionIndex();
							if (indextemp > -1) {
								String artname = artlist.get(k).getCbname()
										.getItem(indextemp);

								if (artname.equals(client[j].getArticleName())) {

									if (client[j].getCcchargeType() == 0) {
										ccc = bft
												* client[j].getCcchargeValue()
												/ 100;
										txtCcc
												.setText(String
														.valueOf(getNormalRounded2Decimal(ccc)));
									}

									if (client[j].getDcchargeType() == 0) {
										dcc += bft
												* client[j].getDcchargeValue()
												/ 100;
										txtDcc
												.setText(String
														.valueOf(getNormalRounded2Decimal(dcc)));
									}

									if (client[j].getIechargeType() == 0) {
										iec += bft
												* client[j].getIechargeValue()
												/ 100;
										txtIec
												.setText(String
														.valueOf(getNormalRounded2Decimal(iec)));

									}
									if (client[j].getLcchargeType() == 0) {
										lcc += bft
												* client[j].getLcchargeValue()
												/ 100;
										txtLc
												.setText(String
														.valueOf(getNormalRounded2Decimal(lcc)));

									}

									break;
								}
							}
						}
					}
				}

			}

		}

		/**
		 * 
		 */
		private void applyServiceTax() {
			try {
				float total = 0;

				String temp = txtBft.getText();
				if (temp.length() > 0)
					total += Float.parseFloat(temp);

				temp = txtLrc.getText();
				if (temp.length() > 0)
					total += Float.parseFloat(temp);

				if (!(lrTab.getSelection()[0].getText().equals("UPD") && (btnRebooklr.getSelection()|| btnToUPD.getSelection()))) {
					if (((booking_type == 0 || isAllowedCCC()) && btnccc
							.getSelection())
							|| (booking_type != 0 && booking_type != 1 && booking_type != 2)) {
						temp = txtCcc.getText();
						if (temp.length() > 0)
							total += Float.parseFloat(temp);

					} else if (null != btnopenlr && btnopenlr.getSelection()) {

						temp = txtCcc.getText();
						if (temp.length() > 0)
							total += Float.parseFloat(temp);

					}

					temp = txtDcc.getText();
					if (temp.length() > 0)
						total += Float.parseFloat(temp);

					

					temp = txtIec.getText();
					if (temp.length() > 0)
						total += Float.parseFloat(temp);

					temp = txtGsc.getText();
					if (temp.length() > 0)
						total += Float.parseFloat(temp);

				}
				
				temp = txtDdc.getText();
				if (temp.length() > 0)
					total += Float.parseFloat(temp);
				
				temp = txtDhc.getText();
				if (temp.length() > 0)
					total += Float.parseFloat(temp);

				temp = txtLc.getText();
				if (temp.length() > 0)
					total += Float.parseFloat(temp);

				RegularSundryDTO regular = handler.getSundryDetails(handler
						.getStationCode());

				int limt = regular.getStaxLimit();
				float stax = regular.getServiceTax();

				if ((total > limt && !btnStax.getSelection() && quotationID == null)
						|| (quotationID != null && isQST && total > limt)) {
					stax = total * stax / 100;
					stax = getRounded2Decimal(stax);
					DecimalFormat df = new DecimalFormat("0.00");
					txtStax.setText(df.format(stax));
				} else {
					stax = 0;
					txtStax.setText("0.00");
				}

				temp = String.valueOf(stax);

				if (temp.length() > 0)
					total += Float.parseFloat(temp);

				if ((lrTab.getSelection()[0].getText().equals("UPD") && (btnRebooklr.getSelection() || btnToUPD.getSelection()))) {

					temp = txtPost.getText();
					if (temp.length() > 0)
						total += Float.parseFloat(temp);

					temp = txtDemu.getText();
					if (temp.length() > 0)
						total += Float.parseFloat(temp);

					Float oldLrTotal = handler.getOldLrTotal(txtOldlrno.getText());
					
					txtOldFrt.setText(String.valueOf(getRounded2Decimal(oldLrTotal)));

					temp = txtOldFrt.getText();
					if (temp.length() > 0)
						total += Float.parseFloat(temp);
				}

				txtTotal.setText(String.valueOf((int) Math.ceil(total)));
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		/**
		 * Method to apply BFt increment/Decrement
		 * 
		 * @param temp
		 * @return
		 */
		private float getBFT(String temp) {
			float bft = 0;
			if (temp != null && temp != EMPTY_STRING) {

				int index = cbic.getSelectionIndex();
				int bftinc = 0;
				int bftdec = 0;
				if (index > -1) {
					bftinc = Integer.parseInt(cbic.getItem(index));
				} else {
					if (cbic.getText() != null
							&& !cbic.getText().trim().equals(""))
						bftinc = Integer.parseInt(cbic.getText());
				}

				if (!(lrTab.getSelection()[0].getText().equals("UPD") && (btnRebooklr.getSelection() || btnToUPD.getSelection()))) {
					index = cbdc.getSelectionIndex();
					if (index > -1) {
						bftdec = Integer.parseInt(cbdc.getItem(index));
					} else {
						if (cbdc.getText() != null
								&& !cbdc.getText().trim().equals(""))
							bftdec = Integer.parseInt(cbdc.getText());
					}
				}
				bftinc = bftinc - bftdec;
				if (bftinc < 0) {
					bft = Float.parseFloat(temp)
							- (Math.abs(bftinc) * Float.parseFloat(temp) / 100);
					float minFrieght = 0;
					try {
						StationsDTO[] dto = handler.getStations();
						if (null != dto) {
							int len = dto.length;

							for (int i = 0; i < len; i++) {
								if (dto[i].getId().equalsIgnoreCase(
										handler.getStationCode())) {
									minFrieght = dto[i].getMin_freight_value();
								}
							}
						}
					} catch (Exception e) {

					}

					bft = bft > minFrieght ? bft : minFrieght;

				} else
					bft = Float.parseFloat(temp)
							+ (bftinc * Float.parseFloat(temp) / 100);
			}
			return bft;

		}

		/**
		 * Method to find Bolooking LR Type (Quotation or Sundry)
		 */


	}

	/**
	 * Method To populate all Stations
	 */
	private void populateDestStationCodes() {
		try {
			StationsDTO[] stations = handler.getStations();
			// cbTo.removeAll();
			if (null != stations) {
				int len = stations.length;
				for (int i = 0; i < len; i++) {
					cbTo.add(stations[i].getName() + " - "
							+ stations[i].getId());
				}
			}
		} catch (Exception exception) {

		}
	}

	/**
	 * 
	 * @return
	 */
	private boolean isCommodity() {
		isCommodity = false;
		rate_type = 2;
		int index = lrTab.getSelectionIndex();
		if (index == 2)
			rate_type = 5;
		// Logic for Commodity LR goes here.

		for (int c = 0; c < artlist.size(); c++) {
			int indextemp = artlist.get(c).getCbname().getSelectionIndex();
			if (indextemp > -1) {
				String artname = artlist.get(c).getCbname().getItem(indextemp);
				if (null != articles) {
					int len = articles.length;
					for (int i = 0; i < len; i++) {

						if (articles[i].getType().equalsIgnoreCase(artname)
								&& articles[i].getValue_per_km() != 0) {
							isCommodity = true;
							break;
						}
					}
				}

			}
		}

		return isCommodity;
	}

	/**
	 * 
	 * @return
	 */
	private boolean isHLC() {
		if (artlist != null) {
			for (int i = 0; i < artlist.size(); i++) {
				if (artlist.get(i).getCbname().getText()
						.equalsIgnoreCase("HLC")) {
					return true;
				}
			}
		}
		return false;
	}

	public float getOpenLrActualBFT() throws Exception {
		float bft = 0;
		StationsDTO[] stations = handler.getStations();
		float mweight = 0;
		float mfreight = 0;
		int noofarticle = 0;
		float chargedweight = 0;
		float[] tempcw = null;
		float artvalue = 0;
		if (null != stations) {
			int len = stations.length;
			float frombpi = 0;
			float tobpi = 0;

			if (null != artlist) {
				int size1 = artlist.size();
				tempcw = new float[size1];
				String isEmpty = null;
				for (int i = 0; i < size1; i++) {
					isEmpty = artlist.get(i).getTxtnoofarticle().getText();
					if (!isEmpty.trim().equalsIgnoreCase("")) {
						noofarticle += Integer.parseInt(isEmpty);
					}

					isEmpty = artlist.get(i).getTxtchargeweight().getText();

					if (!isEmpty.trim().equalsIgnoreCase("")) {
						tempcw[i] = Float.parseFloat(isEmpty);
						chargedweight += Float.parseFloat(isEmpty);
					}

					isEmpty = artlist.get(i).getTxtartvalue().getText();
					if (!isEmpty.trim().equalsIgnoreCase("")) {
						artvalue += Float.parseFloat(isEmpty);
					}

				}

				String code = handler.getStationCode();

				String tostation = null;
				Integer distance = null;
				tostation = cbTo.getText();
				int index = -1;
				float bpi = 0;
				if (tostation != null && !tostation.equals("")) {
					index = tostation.indexOf("-");
					tostation = tostation.substring(index + 2);
					for (int i = 0; i < len; i++) {
						if (stations[i].getId().equalsIgnoreCase(code)) {

							frombpi = stations[i].getBpi();

							mweight = stations[i].getMin_weight_value();
							mfreight = stations[i].getMin_freight_value();
						} else if (stations[i].getId().equalsIgnoreCase(
								tostation)) {
							tobpi = stations[i].getBpi();

						}
					}
					bpi = frombpi > tobpi ? frombpi : tobpi;

				}
				if (quotationID == null) {
					if (isCommodity()) {
						bft = getCommodityBFT(distance, bpi, mweight);
						if (bft < mfreight)
							bft = mfreight;

					} else if (isHLC()) {
						float roundCardRate = 0;
						roundCardRate = bpi * distance;
						roundCardRate = roundCardRate * 100;
						roundCardRate = (float) Math.ceil(roundCardRate);
						roundCardRate = roundCardRate / 100;
						bft = roundCardRate * chargedweight;
						bft = (float) Math.ceil(bft);

					}

					else {
						float roundCardRate = 0;
						roundCardRate = bpi;
						roundCardRate = roundCardRate * 100;
						roundCardRate = (float) Math.ceil(roundCardRate);
						roundCardRate = roundCardRate / 100;

						if (chargedweight >= mweight)
							bft = roundCardRate * chargedweight;
						else if (chargedweight < mweight)
							bft = roundCardRate * mweight;
						if (bft < mfreight)
							bft = mfreight;
						bft = (float) Math.ceil(bft);

					}
				} else if (quotationID != null) {

					int index1 = cbTo.getSelectionIndex();

					if (null != tostation && null != quotationAllDetails) {
						// getting all the quotation values
						/*
						 * if (quotationType == 0) quotationAllDetails =
						 * handler.getQuotationDetails( quotationID,
						 * checkCityStation(tostation)); else
						 * quotationAllDetails = handler.getQuotationDetails(
						 * quotationID, checkCityStation(handler
						 * .getStationCode()));
						 */

						isQST = quotationAllDetails.isServiceTax();

						if (quotationAllDetails.getType() == 0
								|| quotationAllDetails.getType() == 1) {
							// For calculation for weight and Article based
							// Quotation here.
							bft = quotationAllDetails.getQuotationDetails()[0]
									.getBft();
							// bft = bft * chargedweight;
							float minbf = quotationAllDetails
									.getMinFreightValue();
							if (quotationAllDetails.getType() == 1) {

								bft = bft * noofarticle;

							} else if (quotationAllDetails.getType() == 0) {// For
								// Weight
								// Based
								// Quotation
								float mincwt = quotationAllDetails
										.getMinWeightValue();
								if (mincwt < chargedweight)
									bft = bft * chargedweight;
								else
									bft = bft * mincwt;
							}

							if (bft < minbf) {
								bft = minbf;
							}

						} else if (quotationAllDetails.getType() == 2) {
							float tempbft = 0;
							int tempnoofarticle = 0;
							ClientQuotationDetailsDTO[] client = quotationAllDetails
									.getQuotationDetails();
							int len2 = client.length;
							String isEmpty2 = null;
							for (int j = 0; j < len2; j++) {
								for (int k = 0; k < artlist.size(); k++) {
									int indextemp = artlist.get(k).getCbname()
											.getSelectionIndex();
									if (indextemp > -1) {
										String artname = artlist.get(k)
												.getCbname().getItem(indextemp);

										if (artname.equals(client[j]
												.getArticleName())) {
											tempbft = client[j].getBft();
											isEmpty2 = artlist.get(k)
													.getTxtnoofarticle()
													.getText().trim();
											if (!isEmpty2.equalsIgnoreCase(""))
												tempnoofarticle = Integer
														.parseInt(isEmpty2);
											bft += tempbft * tempnoofarticle;
											break;
										}
									}
								}
							}

							float chkbft = quotationAllDetails
									.getMinFreightValue();
							if (chkbft > bft) {
								bft = chkbft;

							}

						}
					}

				}
			}

		}
		return bft;
	}

	/**
	 * 
	 * @param distance
	 * @param bpi
	 * @param mweight
	 * @return
	 */
	public float getCommodityBFT(int distance, float bpi, float mweight) {
		try {

			isMixedCommodity = false;
			isCCCommodity = false;
			mixedCommodityValue = 0;
			// Logic for Commodity LR goes here.
			float bft = 0;
			float chargedweight = 0;
			for (int c = 0; c < artlist.size(); c++) {
				int indextemp = artlist.get(c).getCbname().getSelectionIndex();
				if (indextemp > -1) {
					int noa = 0;
					float valueperkm = 0;
					String artname = artlist.get(c).getCbname().getItem(
							indextemp);
					String empty = artlist.get(c).getTxtnoofarticle().getText();
					if (!empty.equalsIgnoreCase(EMPTY_STRING))
						noa = Integer.parseInt(empty);

					if (null != articles) {
						int len = articles.length;
						for (int i = 0; i < len; i++) {

							if (articles[i].getType().equalsIgnoreCase(artname)
									&& articles[i].getValue_per_km() != 0) {
								valueperkm = articles[i].getValue_per_km();
								bft = bft + (valueperkm * distance * noa);
							} else if (articles[i].getType().equalsIgnoreCase(
									artname)
									&& articles[i].getValue_per_km() == 0) {
								String isEmpty = artlist.get(c)
										.getTxtchargeweight().getText();
								if (!isEmpty.equalsIgnoreCase(EMPTY_STRING)) {
									chargedweight = chargedweight
											+ Float.parseFloat(isEmpty);
									isMixedCommodity = true;
								}
							}
						}
					}

				}
			}

			if (isMixedCommodity) {

				// roundwt = getRoundableWeight();

				if (roundwt != 0 && (int) chargedweight % roundwt != 0) {
					chargedweight = (int) chargedweight
							- ((int) chargedweight % roundwt) + roundwt;
				}

				chargedweight = originalChargedWeight(chargedweight);

				float roundCardRate = 0;
				roundCardRate = bpi;

				roundCardRate = roundCardRate * 100;
				roundCardRate = (float) Math.ceil(roundCardRate);
				roundCardRate = roundCardRate / 100;

				if (chargedweight >= mweight) {
					bft += roundCardRate * chargedweight;
					mixedCommodityValue = roundCardRate * chargedweight;
				} else if (chargedweight < mweight) {
					bft += roundCardRate * mweight;
					mixedCommodityValue = roundCardRate * mweight;
				}
			}
			return bft;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * Method to check whether the given lr number of the given type is between
	 * the range of allocated lr's for the given station This method returns
	 * true if it is allocated and returns false if it is not.
	 * 
	 * @param lrno
	 *            LR Number
	 * @param lrType
	 *            LR Type
	 * @param stationCode
	 *            Station Code
	 * @return boolean
	 *         <li>true - if it is in the range of allocated lr's </li>
	 *         <li>false - if not in the range of allocated lr's </li>
	 */
	private boolean isAllocatedLRNumber(String lrno, String lrtype,
			String stationCode) {
		int len = 0;
		boolean flag = false;
		int fromnumber = 0;
		int tonumber = 0;
		int temp = 0;

		try {

			/*
			 * if (null != lrrangeDTO && (len = lrrangeDTO.length) > 0) { for
			 * (int i = 0; i < len; i++) { LRNumberRangeDTO dto =
			 * (LRNumberRangeDTO) lrrangeDTO[i]; if
			 * (stationCode.equalsIgnoreCase(dto.getStationCode()) &&
			 * lrtype.equalsIgnoreCase(dto.getType()) && lrno.substring(0,
			 * 1).equalsIgnoreCase( dto.getRangeFrom().substring(0, 1))) {
			 * fromnumber = Integer.parseInt(dto.getRangeFrom() .substring(1));
			 * tonumber = Integer.parseInt(dto.getRangeTo().substring( 1));
			 * 
			 * temp = Integer.parseInt(lrno.substring(1)); if (temp >=
			 * fromnumber && temp <= tonumber) {
			 */

			if (null != unUsedList && (len = unUsedList.length) > 0) {
				for (int i = 0; i < len; i++) {
					BookingDTO dto = (BookingDTO) unUsedList[i];
					if (lrtype.equalsIgnoreCase(dto.getType())) {
						if (lrno.equals(dto.getLrno())) {
							flag = true;
							break;
						}

					}
				}
			}
		} catch (Exception exception) {
			// Log the exception
			exception.printStackTrace();

		}
		System.out.println("flag-->" + flag);
		return flag;
	}

	/**
	 * Method to display ConsignorNames
	 */
	
	
	
	private void findBookingType() {
		int index1 = -1;
		try {

			//
			index1 = cbConsignorName.getSelectionIndex();
			String consignorName = null;

			if (index1 > -1) {

				consignorName = cbConsignorName.getItem(index1);
			} else {
				consignorName = cbConsignorName.getText();
				if (consignorName.trim().length() == 0)
					consignorName = null;
			}

			index1 = cbConsigneeName.getSelectionIndex();
			String consigneeName = null;

			if (index1 > -1) {

				consigneeName = cbConsigneeName.getItem(index1);
			} else {
				consigneeName = cbConsigneeName.getText();
				if (consigneeName.trim().length() == 0)
					consigneeName = null;
			}
			if (null != consigneeName && null != consignorName) {
				String code = handler.getStationCode();
				CustomerDTO[] cusdto = handler.getQuotationCustomers(code,
						true);
				int len = 0;
				if (null != cusdto && cusdto.length > 0) {
					len = cusdto.length;
					for (int i = 0; i < len; i++) {
						String tempcus = cusdto[i].getName();

						// System.out.println("Q cus name -- > "+tempcus);
						// System.out.println("cnee name -- >
						// "+consigneeName+ "cnor-->"+consignorName);

						if (tempcus.equals(consigneeName)) {
							customerType = "consignee";
						} else if (tempcus.equals(consignorName)) {
							customerType = "consignor";
						}

						if (tempcus.equals(consigneeName)
								|| tempcus.equals(consignorName)) {

							quotationID = cusdto[i].getQuotationId(); // This
							// shows
							// As
							// Quotation

							// System.out.println("The Quotation ID
							// Now:->"+quotationID);

							btnSpecial.setEnabled(false);
							btnFOCLr.setEnabled(false);

							if (cusdto[i].isBookingType()) {
								quotationType = 1;
							} else {
								quotationType = 0;
							}
							if (cusdto[i].getCustomerId() != 0) {

								btnArtAdd.setEnabled(true);
								if (null != cbUnit)
									cbUnit.setEnabled(false);
								break;
							} else {

								btnArtAdd.setEnabled(true);
								if (null != cbUnit)
									cbUnit.setEnabled(true);
								break;
							}

						} else {

							quotationID = null;
							quotationType = -1;
							btnArtAdd.setEnabled(true);
							if (null != cbUnit)
								cbUnit.setEnabled(true);
							if (!btnopenlr.getSelection()) {
								btnSpecial.setEnabled(true);
								btnFOCLr.setEnabled(true);
							}
						}

					}

				} else {

					btnArtAdd.setEnabled(true);
					if (null != cbUnit)
						cbUnit.setEnabled(true);
				}

				String tab = lrTab.getSelection()[0].getText();
				/*
				 * if (quotationID == null &&
				 * tab.equalsIgnoreCase("BILLING")) {
				 * btnArtAdd.setEnabled(false); if (null != cbUnit)
				 * cbUnit.setEnabled(false); displayError("Please Select Any
				 * quotation Customer"); } else {
				 */
				populateArticle();
				// }

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	private void populateConsignorNames() {

		try {
			CustomerDTO[] dto = null;

			allConsignors = customerHandler.getSundryCustomers(false);
			int len = 0;
			int dtolen = 0;
			int templen = 0;
			if (null != allConsignors)
				Arrays.sort(allConsignors);

			String selectedTab = lrTab.getSelection()[0].getText();
			if (null != allConsignors) {
				len = allConsignors.length;
			}

			dto = handler.getQuotationCustomers(handler.getStationCode(), true);

			String[] tempconsignor = null;
			if (null != dto) {
				dtolen = dto.length;
				tempconsignor = new String[dtolen];
			}

			for (int j = 0; j < dtolen; j++) {
				if (selectedTab.equalsIgnoreCase("TOPAY")) {
					if (dto[j].getQuotation_type() != 2
							&& !dto[j].isBookingType()) {
						tempconsignor[j] = dto[j].getName();
					} else {
						tempconsignor[j] = "";
					}
				} else if (selectedTab.equalsIgnoreCase("PAID")) {
					if (!dto[j].isBookingType()) {
						tempconsignor[j] = dto[j].getName();
					} else {
						tempconsignor[j] = "";
					}
				} else if (selectedTab.equalsIgnoreCase("BILLING")) {
					if (dto[j].getQuotation_type() == 3
							&& !dto[j].isBookingType()) {
						tempconsignor[j] = dto[j].getName();
					} else {
						tempconsignor[j] = "";
					}
				}
			}
			if (tempconsignor != null) {
				templen = tempconsignor.length;
			}
			String[] dest = new String[len + templen];
			if (len > 0)
				System.arraycopy(allConsignors, 0, dest, 0, len);
			if (dtolen > 0)
				System.arraycopy(tempconsignor, 0, dest, len,
						tempconsignor.length);

			/*
			 * if (dest != null) { len = dest.length; for (int i = 0; i < len;
			 * i++) { if (dest[i] != null && !dest[i].equals(EMPTY_STRING))
			 * cbConsignorName.add(dest[i]); } }
			 */

			if (dest != null) {
				len = dest.length;
				TreeSet<String> set = new TreeSet<String>(
						String.CASE_INSENSITIVE_ORDER);
				for (int i = 0; i < len; i++) {
					if (dest[i] != null && !dest[i].equals(EMPTY_STRING))
						set.add(dest[i]);
					// cbConsigneeName.add(finalconsingee[i]);
				}

				Iterator<String> iterator = set.iterator();
				// Displaying the Tree set data
				while (iterator.hasNext()) {
					cbConsignorName.add(iterator.next());
				}

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	
	private void populateArticle() {
		if (quotationID == null || isWeightBased)
			if (null != cbUnit) {
				cbUnit.setEnabled(true);
			}
		clearRatesFields(false);
		if (artlist != null) {
			int size = artlist.size();
			if (size > 0) {
				int table = tblArticle.getItemCount();
				if (size != table) {
					tblArticle.getItem(size).dispose();
				}
			}

		}
		// System.out.println("Quotation ID" + quotationID);
		boolean isquotation = false;
		boolean isweight = false;
		boolean isdispose = false;
		if (null != quotationID)
			isquotation = true;
		// Code to

		final ArticleUIDTO dto;
		TableEditor editor = null;
		final TableItem item;
		final Combo cbArticle;
		final Text txtnoofarticle;
		final Text txtartvalue;
		final Text txtweight;
		final Text txtlength;
		final Text txtbreadth;
		final Text txtheight;
		final Text txtchargeweight;
		final Text txtdesc;
		final Button btndelete;
		if (null != tblArticle) {

			dto = new ArticleUIDTO();
			item = new TableItem(tblArticle, SWT.NONE);

			editor = new TableEditor(tblArticle);
			editor.grabHorizontal = true;
			cbArticle = new Combo(tblArticle, SWT.DROP_DOWN);
			editor.setEditor(cbArticle, item, 0);
			dto.setCbname(cbArticle);

			editor = new TableEditor(tblArticle);
			editor.grabHorizontal = true;
			txtnoofarticle = new Text(tblArticle, SWT.NONE);
			editor.setEditor(txtnoofarticle, item, 1);
			dto.setTxtnoofarticle(txtnoofarticle);

			editor = new TableEditor(tblArticle);
			editor.grabHorizontal = true;
			txtartvalue = new Text(tblArticle, SWT.NONE);
			editor.setEditor(txtartvalue, item, 2);
			dto.setTxtartvalue(txtartvalue);

			editor = new TableEditor(tblArticle);
			editor.grabHorizontal = true;
			txtweight = new Text(tblArticle, SWT.NONE);
			editor.setEditor(txtweight, item, 3);
			dto.setTxtweight(txtweight);

			editor = new TableEditor(tblArticle);
			editor.grabHorizontal = true;
			txtlength = new Text(tblArticle, SWT.NONE);
			editor.setEditor(txtlength, item, 4);
			dto.setTxtlength(txtlength);

			editor = new TableEditor(tblArticle);
			editor.grabHorizontal = true;
			txtbreadth = new Text(tblArticle, SWT.NONE);
			editor.setEditor(txtbreadth, item, 5);
			dto.setTxtbreadth(txtbreadth);

			editor = new TableEditor(tblArticle);
			editor.grabHorizontal = true;
			txtheight = new Text(tblArticle, SWT.NONE);
			editor.setEditor(txtheight, item, 6);
			dto.setTxtheight(txtheight);

			editor = new TableEditor(tblArticle);
			editor.grabHorizontal = true;
			txtchargeweight = new Text(tblArticle, SWT.NONE);
			editor.setEditor(txtchargeweight, item, 7);
			dto.setTxtchargeweight(txtchargeweight);

			editor = new TableEditor(tblArticle);
			editor.grabHorizontal = true;
			txtdesc = new Text(tblArticle, SWT.NONE);
			editor.setEditor(txtdesc, item, 8);
			dto.setTxtdesc(txtdesc);

			if (isquotation == true) {
				btnStax.setSelection(false);
				btnStax.setVisible(false);
				int index = 0;
				String tostation = null;
				tostation = cbTo.getText();
				if (tostation != null && !tostation.equals("")) {
					index = tostation.indexOf("-");
					tostation = tostation.substring(index + 2);
				}
				if (tostation == null || tostation.equals(EMPTY_STRING)) {
					cbArticle.dispose();
					txtnoofarticle.dispose();
					txtartvalue.dispose();
					txtweight.dispose();
					txtlength.dispose();
					txtbreadth.dispose();
					txtheight.dispose();
					txtchargeweight.dispose();
					txtdesc.dispose();
					item.dispose();
					isdispose = true;
					displayError("Please Select TO Station");
					cbTo.setFocus();
				} else {
					String[][] artarray = DisplayArticleForQuotation();
					if (null != artarray
							&& !artarray[0][0].equalsIgnoreCase("weight")) {
						isWeightBased = false;
						int length = artarray.length;
						for (int j = 0; j < length; j++) {
							cbArticle.add(artarray[j][0]);
						}
					} else {
						isweight = true;
						isWeightBased = true;
						if (null != articles) {
							int len = articles.length;
							for (int i = 0; i < len; i++) {
								if (!articles[i].getType()
										.equalsIgnoreCase("HLC")) {
									cbArticle.add(articles[i].getType());
								}

							}
						}

					}
				}
			}

			else {
				btnStax.setVisible(true);
				isWeightBased = false;
				if (null != articles) {
					int len = articles.length;
					for (int i = 0; i < len; i++) {
						cbArticle.add(articles[i].getType());

					}
				}
			}
			if (null != cbArticle)
				comboElemrnt = cbArticle.getItems();
			if (null != comboElemrnt) {
				Arrays.sort(comboElemrnt, String.CASE_INSENSITIVE_ORDER);

				if (null != cbArticle) {
					cbArticle.removeAll();
					// for (int sort = 0; sort < comboElemrnt.length;
					// sort++) {
					cbArticle.setItems(comboElemrnt);
					// }
				}
			}

			if (isdispose == false) {
				editor = new TableEditor(tblArticle);
				editor.grabHorizontal = true;
				btndelete = new Button(tblArticle, SWT.NONE);
				InputStream stream = LRComposite.class.getClassLoader()
						.getResourceAsStream(DEL_IMG);
				img = new Image(Display.getDefault(), stream);
				btndelete.setImage(img);
				editor.setEditor(btndelete, item, 9);
				dto.setBtndelete(btndelete);
				artlist.add(dto);

				btndelete.addMouseListener(new MouseAdapter() {
					public void mouseDown(MouseEvent event) {
						if (null != txteffchargedCWT) {
							txteffchargedCWT.setText("");
						}
						if (quotationID == null || isWeightBased)
							if (null != cbUnit) {
								cbUnit.setEnabled(true);
							}
						clearRatesFields(false);
						//txtPost.setEnabled(true);
						//txtDemu.setEnabled(true);
						/*
						 * if (null != btnSpecial) {
						 * btnSpecial.setSelection(false);
						 * btnSpecial.setEnabled(true); } if (null !=
						 * btnopenlr) { btnopenlr.setSelection(false);
						 * btnopenlr.setEnabled(true); }
						 */
						if (!lrTab.getSelection()[0].getText()
								.equalsIgnoreCase("UPD")) {
							if (null != btnccc) {
								btnccc.setSelection(false);
								btnccc.setEnabled(true);
								btnccc.setVisible(true);
							}
						}
						cbArticle.dispose();
						txtnoofarticle.dispose();
						txtartvalue.dispose();
						txtweight.dispose();
						txtlength.dispose();
						txtbreadth.dispose();
						txtheight.dispose();
						txtchargeweight.dispose();
						txtdesc.dispose();
						btndelete.dispose();
						item.dispose();
						artlist.remove(dto);
						if (artlist != null) {
							int removesize = artlist.size();
							if (removesize == 0)
								tblArticle.removeAll();
							else if (removesize == 1) {
								if (tblArticle.getItemCount() > 1) {
									tblArticle.getItem(1).dispose();
								}
							}
						}

					}
				});

				/**
				 * 
				 * @author Naruto1
				 * 
				 */
				class LrKeyListeners implements KeyListener {

					@Override
					public void keyPressed(KeyEvent e) {
						try {
							String[] items = comboElemrnt;
							String selectedText = cbArticle.getText();
							if (e.keyCode == SWT.ARROW_UP
									|| e.keyCode == SWT.ARROW_DOWN
									|| e.keyCode == SWT.CR)
								return;

							if (e.character == '\b') {
								int len = selectedText.length();

								if (len > 1) {
									selectedText = selectedText.substring(
											0, len - 1);
								}
							} else if (e.keyCode == 32
									|| (e.keyCode > 64 && e.keyCode < 91)
									|| (e.keyCode > 96 && e.keyCode < 123)) {
								selectedText += e.character;

							} else {
								e.doit = false;
								return;
							}

							if (selectedText.length() > 0) {
								selectedText = selectedText.toLowerCase();

								int len = items.length;

								int startIndex = -1;
								for (int i = 0; i < len; i++) {
									String temp = items[i].toLowerCase();

									if (temp.contains(selectedText)) {
										startIndex = i;

										break;
									}
								}

								if (startIndex == -1) {
									e.doit = false;
								} else {

									cbArticle.remove(0, cbArticle
											.getItemCount() - 1);

									for (int i = startIndex; i < len; i++) {
										String temp = items[i]
												.toLowerCase();
										if (temp.contains(selectedText)) {
											cbArticle.add(items[i]);

										}
									}

									if (selectedText.length() == 1) {
										showPopup(cbArticle, true);
									}
								}
							}
						} catch (Exception excvvxc) {
							excvvxc.printStackTrace();
						}
					}

					public void keyReleased(KeyEvent e) {
						String temp = cbArticle.getText();

						int len = temp.length();
						if (len == 0) {
							cbArticle.remove(0,
									cbArticle.getItemCount() - 1);
							populateKeyArticlkes();
						}

					}

					private void populateKeyArticlkes() {

						try {

							if (null != comboElemrnt) {
								int len = comboElemrnt.length;
								for (int i = 0; i < len; i++) {
									cbArticle.add(comboElemrnt[i]);
									// cbArticle.setItems(comboElemrnt);
								}
							}
						} catch (Exception exception) {
							exception.printStackTrace();
						}

					}
				}

				/**
				 * 
				 * @author kibaitachi
				 * 
				 */
				class ArticleQuotationAction implements FocusListener {

					@Override
					public void focusGained(FocusEvent arg0) {

					}

					@Override
					public void focusLost(FocusEvent event) {

						Object source = event.getSource();
						if (source == txtnoofarticle) {
							int index = cbArticle.getSelectionIndex();
							if (index > -1) {
								String artname = cbArticle.getItem(index);

								String[][] artarray = DisplayArticleForQuotation();
								if (null != artarray) {
									int length = artarray.length;
									for (int j = 0; j < length; j++) {
										if (artname.equals(artarray[j][0])) {
											if (!txtnoofarticle.getText()
													.equalsIgnoreCase(
															EMPTY_STRING)) {
												int noofarticle = Integer
														.parseInt(txtnoofarticle
																.getText());
												txtchargeweight
														.setText(String
																.valueOf((Float
																		.parseFloat(artarray[j][1]) * noofarticle)));
											}
										}

									}
								}

							}
						}

					}

				}

				/**
				 * Class for Article Table Components FocusListeners
				 * 
				 * @author
				 * 
				 */
				class tableListener implements FocusListener,
						SelectionListener {
					String len = null;
					String bredth = null;
					String height = null;
					String wigth = null;
					String noa = null;

					@Override
					public void focusGained(FocusEvent arg0) {

					}

					@Override
					public void focusLost(FocusEvent event) {

						try {
							Object source = event.getSource();
							if (source == txtlength || source == txtbreadth
									|| source == txtheight
									|| source == txtnoofarticle
									|| source == txtweight) {
								popluateChargedWeight(true);

							} else if (txtchargeweight == source) {
								float chargewt = 0;
								if (!txtchargeweight.getText().equals(
										EMPTY_STRING)) {
									chargewt = Float
											.parseFloat(txtchargeweight
													.getText());
								}

								if (quotationID == null) {
									float checkWeight = popluateChargedWeight(false);

									chargewt = chargewt > checkWeight ? chargewt
											: checkWeight;
								}

								txtchargeweight.setText(Float
										.toString(chargewt));

							} else if (source == cbArticle) {

								String selectedArticle = cbArticle
										.getText();

								String selectedTab = lrTab.getSelection()[0]
										.getText();
								if (!selectedArticle.equals("")) {

									if ((selectedArticle
											.equalsIgnoreCase("House Hold Goods")
											|| selectedArticle
													.equalsIgnoreCase("Two wheeler")
											|| selectedArticle
													.equalsIgnoreCase("Return Goods") || selectedArticle
											.equalsIgnoreCase("Two Wheeler - Bullet"))
											&& (!lrType[1]
													.equals(selectedTab))) {
										displayError("These goods are to be booked only in Paid");
									} else if (selectedArticle
											.equalsIgnoreCase("Chemicals")) {
										displayError("WARNING: Dangerous and Flammable Chemicals should not be booked. You will be held responsible in case of any issue arising out of this booking");
									}

									try {
										// StationsDTO[] stations =
										// handler.getStations();

										if (null != comboElemrnt) {
											int len = comboElemrnt.length;
											boolean isAvailArticle = false;
											String availArticle = null;
											for (int j = 0; j < len; j++) {
												availArticle = comboElemrnt[j];
												if (selectedArticle
														.equals(availArticle)) {

													isAvailArticle = true;
													break;
												}
											}
											if (!isAvailArticle) {
												displayError("Please Select a Valid Article");
												cbArticle.setText("");
												txtnoofarticle.forceFocus();
											}
										}
									} catch (Exception exception) {
										exception.printStackTrace();
									}
								}
								if (quotationID == null) {
									if (isCommodity()
											&& btnSpecial.getSelection()) {
										btnSpecial.setSelection(false);
										btnSpecial.setEnabled(false);
										if (btnopenlr != null) {
											btnopenlr.setEnabled(true);
										}
										if (btnFOCLr != null) {
											btnFOCLr.setEnabled(true);
										}

									} else if (isCommodity()) {
										btnSpecial.setEnabled(false);
									}
								}

							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					/*
					 * private void applyMinimumChargedWeight(float single)
					 * throws Exception {
					 * 
					 * if (artlist != null) { int size = artlist.size(); int
					 * tablesize = tblArticle.getItemCount(); if (size == 1) {
					 * singlrArt = 0; artlist .get(0) .getTxtchargeweight()
					 * .setText( String
					 * .valueOf(originalChargedWeight(single))); singlrArt =
					 * single; } else if (size > 1) { if (tablesize != size) {
					 * tblArticle .getItem(tablesize - 1) .setText( 7,
					 * String
					 * .valueOf(originalChargedWeight(sumAllChargeg()))); }
					 * else { TableItem item = new TableItem( tblArticle,
					 * SWT.NONE); item .setText( 7, String
					 * .valueOf(originalChargedWeight(sumAllChargeg()))); } } } }
					 */
					/*
					 * private float sumAllChargeg() { if (null != artlist) {
					 * int artlen = artlist.size(); if (artlen > 0) { float
					 * tempwt = 0; artlist.get(0).getTxtchargeweight()
					 * .setText(String.valueOf(singlrArt)); for (int i = 1;
					 * i < artlen; i++) { if (!artlist.get(i)
					 * .getTxtchargeweight().getText()
					 * .equalsIgnoreCase("")) tempwt +=
					 * Float.parseFloat(artlist .get(i)
					 * .getTxtchargeweight() .getText()); } return tempwt +
					 * singlrArt; } } return 0; }
					 */
					/**
					 * 
					 * @param calwt
					 * @return
					 */
					private float originalChargedWeight(float calwt)
							throws Exception {
						if (null == quotationID) {
							StationsDTO[] dto = handler.getStations();
							if (null != dto) {
								int size = dto.length;
								for (int i = 0; i < size; i++) {
									if (dto[i].getId().equalsIgnoreCase(
											handler.getStationCode())) {
										calwt = calwt > dto[i]
												.getMin_weight_value() ? calwt
												: dto[i]
														.getMin_weight_value();

										return calwt;
									}
								}
							}
						} else {
							String tostation = null;
							tostation = cbTo.getText();
							if (tostation != null && !tostation.equals("")) {
								/*
								 * int index = tostation.indexOf("-");
								 * tostation = tostation.substring(index +
								 * 2); if (quotationType == 0)
								 * quotationAllDetails = handler
								 * .getQuotationDetails( quotationID,
								 * checkCityStation(tostation)); else
								 * quotationAllDetails = handler
								 * .getQuotationDetails( quotationID,
								 * checkCityStation(handler
								 * .getStationCode()));
								 */
								calwt = calwt > quotationAllDetails
										.getMinWeightValue() ? calwt
										: quotationAllDetails
												.getMinWeightValue();
								return calwt;
							}
						}
						return 0;
					}

					/**
					 * 
					 * @param show
					 * @return
					 * @throws Exception
					 */
					private float popluateChargedWeight(boolean show)
							throws Exception {
						len = txtlength.getText();
						bredth = txtbreadth.getText();
						height = txtheight.getText();
						wigth = txtweight.getText();
						noa = txtnoofarticle.getText();
						float chargewt = 0;

						if (!(len.equals(EMPTY_STRING)
								|| bredth.equals(EMPTY_STRING)
								|| height.equals(EMPTY_STRING) || noa
								.equals(EMPTY_STRING))) {
							int index = cbUnit.getSelectionIndex();
							if (index > -1) {
								chargewt = 0;
								chargewt = Float.parseFloat(len)
										* Float.parseFloat(bredth)
										* Float.parseFloat(height)
										* Float.parseFloat(noa);
								double[] units = handler.getMeasurements();
								if (index == 0) {

									chargewt *= units[0];
								} else if (index == 1) {
									chargewt *= units[2];
								} else if (index == 2) {

									chargewt *= units[1];
								}

								if (!wigth.equals(EMPTY_STRING)) {
									chargewt = Float.parseFloat(wigth) > chargewt ? Float
											.parseFloat(wigth)
											: chargewt;
								}

								float check = chargewt;
								chargewt = Math.round(chargewt);
								if (chargewt < check)
									chargewt += 1;

								txtchargeweight.setText(Float
										.toString(chargewt));

							} else {
								if (show)
									displayError("Please select Unit");
								else
									return 0;
							}

						} else {
							String tempweight = txtweight.getText();
							if (!tempweight.equalsIgnoreCase(EMPTY_STRING)) {
								chargewt = 0;
								chargewt = Float.parseFloat(tempweight);
								float check = chargewt;
								chargewt = Math.round(chargewt);
								if (chargewt < check)
									chargewt += 1;

								txtchargeweight.setText(Float
										.toString(chargewt));
							}
						}

						return chargewt;
					}

					@Override
					public void widgetDefaultSelected(SelectionEvent arg0) {

					}

					@Override
					public void widgetSelected(SelectionEvent event) {
						Object source = event.getSource();
						if (source == cbArticle || source == txtnoofarticle) {
							int index = cbArticle.getSelectionIndex();
							if (index > -1) {
								String artname = cbArticle.getItem(index);
								if (artname.equalsIgnoreCase("HLC")) {
									if (tblArticle.getItemCount() > 1) {
										displayError("HLC should be a single article");
										cbArticle.setText("");
									}
								} else {
									String[][] artarray = DisplayArticleForQuotation();
									if (null != artarray) {
										int length = artarray.length;
										for (int j = 0; j < length; j++) {
											if (artname
													.equals(artarray[j][0])) {
												if (!txtnoofarticle
														.getText()
														.equalsIgnoreCase(
																EMPTY_STRING)) {
													int noofarticle = Integer
															.parseInt(txtnoofarticle
																	.getText());
													txtchargeweight
															.setText(String
																	.valueOf((Float
																			.parseFloat(artarray[j][1]) * noofarticle)));
												}
											}

										}
									}
								}

							}
						}
					}

				}

				txtweight.addVerifyListener(new FloatLimitValidation());

				txtlength.addVerifyListener(new FloatValidation());

				txtbreadth.addVerifyListener(new FloatValidation());

				txtheight.addVerifyListener(new FloatValidation());
				txtnoofarticle.addVerifyListener(new NumericValidation());
				txtartvalue
						.addVerifyListener(new FloatExtendedValidation());
				txtartvalue.addKeyListener(new KeyListener() {

					@Override
					public void keyPressed(KeyEvent arg0) {

					}

					@Override
					public void keyReleased(KeyEvent event) {
						if (event.character == 'n'
								|| event.character == 'N'
								|| event.character == 'C'
								|| event.character == 'V') {
							txtartvalue.setText("NCV");
						}

					}

				});
				txtchargeweight
						.addVerifyListener(new FloatLimitValidation());
				cbArticle.addKeyListener(new LrKeyListeners());
				cbArticle.addFocusListener(new tableListener());

				if (quotationID == null || isweight == true) {
					txtchargeweight.addFocusListener(new tableListener());
					txtweight.addFocusListener(new tableListener());
					txtbreadth.addFocusListener(new tableListener());
					txtlength.addFocusListener(new tableListener());
					txtheight.addFocusListener(new tableListener());
					txtnoofarticle.addFocusListener(new tableListener());
				} else {
					cbArticle.addSelectionListener(new tableListener());
					txtnoofarticle
							.addFocusListener(new ArticleQuotationAction());
				}
			}
		}
	}
	
	
	/**
	 * 
	 * @param isEnabled
	 */
	private void setCompEnabled(boolean isEnabled) {
		if (!(lrTab.getSelection()[0].getText().equals("UPD") && (btnRebooklr.getSelection() || btnToUPD.getSelection()))) {

			txtCcc.setEnabled(isEnabled);
			txtDcc.setEnabled(isEnabled);
			txtLrc.setEnabled(isEnabled);
			txtIec.setEnabled(isEnabled);
			txtGsc.setEnabled(isEnabled);
			cbdc.setEnabled(isEnabled);
		}
		txtDdc.setEnabled(isEnabled);
		txtBft.setEnabled(isEnabled);
		txtDhc.setEnabled(isEnabled);
		txtLc.setEnabled(isEnabled);
		txtStax.setEnabled(isEnabled);
		btnStax.setEnabled(isEnabled);
		cbic.setEnabled(isEnabled);

	}

	private void oldlr_delivery(BookingDTO booking) {

		if (isDeliveryRunning == false) {
			isDeliveryRunning = true;
			try {
				// btnDelivery.setEnabled(false);
				// btnDispatch.setEnabled(false);
				String lrno = booking.getOldLrno();
				if (null != lrno) {
					/*
					 * BeanUtil util = BeanUtil.getInstance(); if
					 * (util.isAdmin() || util.isAdminHead() ||
					 * util.isAdminHeadStationary() ||
					 * util.getActingStationCode().equals("HSR")){ //||
					 * validateDelivery()) {
					 */

					// System.out.println("lrno-->"+lrno);
					// System.out.println("from-->"+booking.getFrom());
					GMRDTO gmrDtoList = handler.getoldLrGMROdetails(lrno,
							booking.getFrom());

					try {
						// CR create logic
						gmrDtoList = buildCashDTO(gmrDtoList);
						gmrDtoList.setLrNumber(lrno);
						gmrDtoList.setStationCode(booking.getFrom());

						gmrDtoList.setVehicleNumber("");
						gmrDtoList.setDriverName("");
						gmrDtoList.setCrTotal(0);
						gmrDtoList.setDispatchTo(booking.getTo());
						gmrDtoList.setLrType(booking.getLrno() + " - "
								+ booking.getIsUPd());

						if (handler.deliver_oldlr_goods(gmrDtoList)) {
							// removeSelectedOutItems(lrnos);
							// if (null != inTimeGoods)
							// inTimeGoods.clear();
							// createCRSMS();
						}
					} catch (BusinessException e) {
						isDeliveryRunning = false;
						displayError(e.getResponseMessage());
					}
					// }
				} else {
					//displayError("Please select Atleast One LR");
				}
				isDeliveryRunning = false;
				// btnDelivery.setEnabled(true);
				// btnDispatch.setEnabled(true);
			} catch (Exception e) {
				isDeliveryRunning = false;
				e.printStackTrace();
			}
		} else {
			displayError("Already running Please wait");
		}
	}

	/*
	 * private String[] getSelectedOutTimeRecords() { //TableItem[] items =
	 * tblGmrOuttime.getItems(); int len = 0; // ArrayList<String> checkedList =
	 * new ArrayList<String>(); String[] lrnowithinwardtime = null;
	 * 
	 * /*if (null != items && (len = items.length) > 0) { int count = 0;
	 * 
	 * for (int i = 0; i < len; i++) { if (items[i].getChecked()) { count++; } }
	 * //if (count > 0) { lrnowithinwardtime = new String[5]; int k = 0; for
	 * (int i = 0; i < 5; i++) { //if (items[i].getChecked()) {
	 * lrnowithinwardtime[0] = items[i] .getText(LR_IN_COLUMN);
	 * lrnowithinwardtime[1] = items[i] .getText(LAST_INWARD_COLUMN);
	 * lrnowithinwardtime[2] = items[i].getText(6);
	 * 
	 * /*String temp = items[i].getText(11); if (temp.length() > 0){
	 * if(items[i].getText(2).equalsIgnoreCase("Topay")){
	 * lrnowithinwardtime[k][3] = temp; }else{ lrnowithinwardtime[3] = "0"; //}
	 * //}
	 * 
	 * lrnowithinwardtime[4] = items[i].getText(14); //} //} //} }
	 * 
	 * return lrnowithinwardtime; }
	 */

	private GMRDTO populateGMROutTimeDTO(String[] lrnos) {
		/*
		 * int len = lrnos.length;
		 * 
		 * GMRDTO gmrList = new GMRDTO();
		 * 
		 * String stationCode = handler.getStationCode(); String drivername =
		 * txtDriverName.getText(); int index = cbVehicle.getSelectionIndex();
		 * String vehicleno = null;
		 * 
		 * if (index > -1) { vehicleno = cbVehicle.getItem(index); } else {
		 * vehicleno = cbVehicle.getText(); if (vehicleno.trim().length() == 0)
		 * vehicleno = null; } String dispatch = txtDestCode.getText();
		 * 
		 * for (int i = 0; i < len; i++) {
		 * 
		 * //gmrList = new GMRDTO(); gmrList.setLrNumber(lrnos[0]);
		 * System.out.println("inwardtime"+lrnos[1]);
		 * gmrList.setLastInwardTime(lrnos[1]);
		 * gmrList.setDestinationStn(lrnos[2]);
		 * 
		 * if(!lrnos[3].equals("")){
		 * gmrList.setCrTotal(Float.parseFloat(lrnos[3])); }
		 * 
		 * gmrList.setCneeName(lrnos[4]);
		 * 
		 * 
		 * //System.out.println("-->"+gmrList[i].getLrNumber()+"-->"+gmrList[i].getDestinationStn());
		 * 
		 * /*if (null != drivername && drivername.trim().length() > 0) {
		 * gmrList[i].setDriverName(drivername); }
		 * System.out.println("station_code"+stationCode);
		 * //System.out.println("vehicle_no "+vehicleno);
		 * gmrList.setStationCode(stationCode);
		 * //gmrList[i].setVehicleNumber(vehicleno);
		 * 
		 * 
		 * 
		 * if (dispatch.length() > 0) { gmrList.setDispatchTo(dispatch); }
		 * 
		 * /*if (i == 0 && chkMarketVehicle.getSelection()) {
		 * gmrList[i].setMarketVehile(true);
		 * 
		 * String temp = null; temp = txtModelNo.getText();
		 * gmrList[i].setModelNo(temp);
		 * 
		 * temp = txtBrokerName.getText(); gmrList[i].setVehicleName(temp);
		 * 
		 * temp = txtBrokerPhone.getText(); gmrList[i].setVehiclePhone(temp);
		 * 
		 * temp = txtVehicleRate.getText(); if (temp != null &&
		 * !temp.equals("")) {
		 * gmrList[i].setVehicleRate(Float.parseFloat(temp)); } } }
		 * 
		 * return gmrList;
		 */

		GMRDTO gmrList = new GMRDTO();

		String stationCode = handler.getStationCode();

		return gmrList;

	}

	private GMRDTO buildCashDTO(GMRDTO gmr) {
		try {

			int len = 0;
			if (gmr != null) {
				len = 1;// gmr.length;
			}
			if (len == 0) {
				return null;
			}

			ArrayList<String> list = populateCRStationary(false);

			if (null != list) {
				int size = list.size();
				// System.out.println("inital list size-->"+list.size());
				while (size - len < 20) {
					BeanUtil util = BeanUtil.getInstance();
					LRNumberRangeDTO[] dto = new LRNumberRangeDTO[1];
					dto[0] = new LRNumberRangeDTO();
					dto[0].setBilling(0);
					dto[0].setCr(1);
					dto[0].setPaid(0);
					dto[0].setTopay(0);
					dto[0].setStationCode(util.getActingStationCode());
					util.assignStationary(dto);

					System.out.println("in populate cr station");

					list = populateCRStationary(true);
					if (null != list) {
						size = list.size();
					}

				}

			}

			for (int i = 0; i < len; i++) {
				gmr.setCrNo(list.get(i));
				System.out.println("CR-->" + list.get(i));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return gmr;

	}

	private ArrayList<String> populateCRStationary(boolean callUnused) {
		ArrayList<String> list = new ArrayList<String>();
		String usedCR = null;
		try {
			usedCR = handler.getLastUsedCR(handler.getStationCode());
			System.out.println("last used->" + usedCR);

		} catch (Exception e) {
			e.printStackTrace();
		}

		ArrayList<LRNumberRangeDTO> unusedLrlist = null;
		try {
			unusedLrlist = getUnUsedCRList(usedCR, callUnused);
			// System.out.println("unusedLst--->"+unusedLrlist);
		} catch (Exception e) {
			e.printStackTrace();
		}

		int size = 0;
		String type = "";
		int cr = 1;
		if (null != unusedLrlist && (size = unusedLrlist.size()) > 1) {
			for (int display = 1; display < size; display++) {

				LRNumberRangeDTO dto = (LRNumberRangeDTO) unusedLrlist
						.get(display);
				type = dto.getType();

				if (type.equalsIgnoreCase("CR")) {
					list.add(dto.getLrNumber());
					cr++;
				}

			}

		}
		return list;
	}

	public ArrayList<LRNumberRangeDTO> getUnUsedCRList(String crno,
			boolean callUnused) throws Exception {

		int len = 0;
		LRNumberRangeDTO temp = null;
		ArrayList<LRNumberRangeDTO> unusedList = new ArrayList<LRNumberRangeDTO>();
		int fromNumber = 0;
		int toNumber = 0;
		String stationcode = handler.getStationCode();
		String lrprefix;
		String lrNumber = null;
		boolean isAlreadyAvail = true;

		if (lrrangeDTO == null || callUnused) {
			lrrangeDTO = handler.getCRRange(handler.getStationCode());
		}

		if (null != lrrangeDTO && (len = lrrangeDTO.length) > 0) {
			for (int i = len - 1; i > -1; i--) {
				temp = (LRNumberRangeDTO) lrrangeDTO[i];
				if (temp.getStationCode().equalsIgnoreCase(stationcode)) {
					lrprefix = temp.getRangeFrom().substring(0, 1);
					fromNumber = Integer.parseInt(temp.getRangeFrom()
							.substring(1));
					toNumber = Integer.parseInt(temp.getRangeTo().substring(1));

					for (int j = fromNumber; j <= toNumber; j++) {
						lrNumber = (lrprefix + "") + j;
						if (null != crno) {
							if (lrNumber.equalsIgnoreCase(crno)) {
								isAlreadyAvail = false;
							}
						}

						if (!isAlreadyAvail || null == crno) {
							LRNumberRangeDTO tempdto = new LRNumberRangeDTO();
							tempdto.setType(temp.getType());
							tempdto.setLrNumber(lrNumber);
							unusedList.add(tempdto);
						}
					}
				}

			}

			if (isAlreadyAvail) {
				for (int i = len - 1; i > -1; i--) {
					temp = (LRNumberRangeDTO) lrrangeDTO[i];
					if (temp.getStationCode().equalsIgnoreCase(stationcode)) {
						lrprefix = temp.getRangeFrom().substring(0, 1);
						fromNumber = Integer.parseInt(temp.getRangeFrom()
								.substring(1));
						toNumber = Integer.parseInt(temp.getRangeTo()
								.substring(1));

						for (int j = fromNumber; j <= toNumber; j++) {
							lrNumber = (lrprefix + "") + j;

							LRNumberRangeDTO tempdto = new LRNumberRangeDTO();
							tempdto.setType(temp.getType());
							tempdto.setLrNumber(lrNumber);
							unusedList.add(tempdto);

						}
					}

				}
			}
		}

		return unusedList;
	}

	/**
	 * 
	 * @param isEnabled
	 */
	private void setAllCompEnabled(boolean isEnabled) {
		cbTo.setEnabled(isEnabled);
		cbConsigneeName.setEnabled(isEnabled);
		cbConsignorName.setEnabled(isEnabled);
		txtConsignor.setEnabled(isEnabled);
		txtConsignee.setEnabled(isEnabled);
		txtConsigneeCST.setEnabled(isEnabled);
		txtConsignorCST.setEnabled(isEnabled);
		btnSaveConsigneeDetails.setEnabled(isEnabled);
		btnSaveConsignorDetails.setEnabled(isEnabled);
		btnopenlr.setEnabled(isEnabled);
		btnSpecial.setEnabled(isEnabled);
		cbUnit.setEnabled(isEnabled);
		if (lrTab.getSelection()[0].getText().equals("UPD")) {
			txtOldlrno.setEnabled(isEnabled);
			cbNewLrno.setEnabled(isEnabled);
			txtPost.setEnabled(isEnabled);
			txtDemu.setEnabled(isEnabled);
		} else {
			txtLrno.setEnabled(isEnabled);
		}

		btnArtAdd.setEnabled(isEnabled);
		btnRate.setEnabled(isEnabled);
		txtConsignorPhone.setEnabled(isEnabled);
		txtConsigneePhone.setEnabled(isEnabled);
		txtCnorLandline.setEnabled(isEnabled);
		txtCneeLandline.setEnabled(isEnabled);
		btnWeightRoundOff.setEnabled(isEnabled);

		if (artlist != null) {
			int len = artlist.size();
			if (len > 0) {
				for (int i = 0; i < len; i++) {
					artlist.get(i).getBtndelete().setEnabled(false);
				}
			}
		}
		if (null != btnFOCLr)
			btnFOCLr.setEnabled(isEnabled);

	}

	/**
	 * Method to display ConsigneeNames by merging xml and from DB
	 */
	private void populateConsigneeNames() {

		try {
			allConsignee = customerHandler.getSundryCustomers(true);

			int len = 0;
			if (null != allConsignee)
				Arrays.sort(allConsignee);

			if (null != allConsignee) {
				len = allConsignee.length;
			}

			if (len > 0)
				for (int i = 0; i < len; i++) {
					if (allConsignee[i] != null
							&& !allConsignee[i].equals(EMPTY_STRING))
						cbConsigneeName.add(allConsignee[i]);
				}

		} catch (Exception exception) {
			exception.printStackTrace();
		}
		deInitializeState();
	}

	/**
	 * 
	 * @param calwt
	 * @return
	 */
	private float originalChargedWeight(float calwt) throws Exception {
		if (null == quotationID) {
			StationsDTO[] dto = handler.getStations();
			if (null != dto) {
				int size = dto.length;
				for (int i = 0; i < size; i++) {
					if (dto[i].getId().equalsIgnoreCase(
							handler.getStationCode())) {

						calwt = calwt > dto[i].getMin_weight_value() ? calwt
								: dto[i].getMin_weight_value();

						return calwt;
					}
				}
			}
		} else {
			String tostation = null;
			tostation = cbTo.getText();
			if (tostation != null && !tostation.equals("")) {
				int index = tostation.indexOf("-");
				tostation = tostation.substring(index + 2);
				/*
				 * if (quotationType == 0) quotationAllDetails =
				 * handler.getQuotationDetails(quotationID,
				 * checkCityStation(tostation)); else { quotationAllDetails =
				 * handler.getQuotationDetails(quotationID,
				 * checkCityStation(handler.getStationCode())); }
				 */

				calwt = calwt > quotationAllDetails.getMinWeightValue() ? calwt
						: quotationAllDetails.getMinWeightValue();

				return calwt;
			}
		}
		return 0;
	}

	private String[][] DisplayArticleForQuotation() {
		String[][] qart = null;
		try {

			// Display Article for Quotation
			int index = 0;
			String tostation = cbTo.getText();

			if (null != tostation && !tostation.equals(EMPTY_STRING)) {
				index = tostation.indexOf("-");
				tostation = tostation.substring(index + 2);
				if (quotationType == 0)
					quotationAllDetails = handler.getQuotationDetails(
							quotationID, checkCityStation(tostation));
				else {

					quotationAllDetails = handler.getQuotationDetails(
							quotationID, checkCityStation(handler
									.getStationCode()));
				}

				if (null != quotationAllDetails) {
					ClientQuotationDetailsDTO[] clientdetails = quotationAllDetails
							.getQuotationDetails();
					int size = clientdetails.length;

					// clearArticleTable();
					if (size > 0) {
						qart = new String[size][2];
					}

					for (int i = 0; i < size; i++) {
						// populateArticle(clientdetails[i]);
						if (!clientdetails[i].getArticleName()
								.equalsIgnoreCase("weight")) {
							qart[i][0] = clientdetails[i].getArticleName();
							qart[i][1] = String.valueOf(clientdetails[i]
									.getChargedWeight());
						} else {
							qart[i][0] = clientdetails[i].getArticleName();
							qart[i][1] = String.valueOf(clientdetails[i]
									.getChargedWeight());
							break;
						}

					}
				}
			} else {
				displayError("Please Select To Station");
				cbTo.setFocus();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return qart;
	}
	
	private void clearRatesFields(boolean isEnable) {
		isReadyToSubmit = false;
		btnSubmit.setEnabled(false);

		txtBft.setText(EMPTY_STRING);
		if (lrTab.getSelection()[0].getText().equals("UPD")) {
			/*if (btnRebooklr.getSelection() || btnToUPD.getSelection()) {
				txtPost.setText(EMPTY_STRING);
				txtPost.setEnabled(isEnable);
				txtDemu.setText(EMPTY_STRING);
				txtDemu.setEnabled(isEnable);
			} else {
				txtPost.setText(EMPTY_STRING);
				txtPost.setEnabled(isEnable);
				txtDemu.setText(EMPTY_STRING);
				txtDemu.setEnabled(isEnable);
			}*/

		} else {
			txtCcc.setText(EMPTY_STRING);
			txtmaxccc.setText(EMPTY_STRING);
			txtDcc.setText(EMPTY_STRING);
			
			txtGsc.setText(EMPTY_STRING);
			txtCcc.setEnabled(isEnable);
			txtmaxccc.setEnabled(isEnable);
			txtDcc.setEnabled(isEnable);
			
			txtGsc.setEnabled(isEnable);
			txtIec.setText(EMPTY_STRING);
			txtIec.setEnabled(isEnable);
			if (null != cbdc)
				cbdc.setText(EMPTY_STRING);
			if (null != btnccc)
				btnccc.setSelection(isEnable);

			if (null != cbdc) {
				cbdc.removeAll();
				cbdc.setEnabled(isEnable);
			}

		}
		txtDdc.setText(EMPTY_STRING);
		txtDdc.setEnabled(isEnable);
		txtLrc.setText(EMPTY_STRING);
		txtDhc.setText(EMPTY_STRING);

		if (null != cbic)
			cbic.setText(EMPTY_STRING);

		txtLc.setText(EMPTY_STRING);

		txtStax.setText(EMPTY_STRING);
		txtTotal.setText(EMPTY_STRING);

		txtBft.setEnabled(isEnable);
		txtLrc.setEnabled(isEnable);
		txtDhc.setEnabled(isEnable);

		txtLc.setEnabled(isEnable);

		txtStax.setEnabled(isEnable);
		txtTotal.setEnabled(isEnable);

		if (null != btnStax)
			btnStax.setSelection(isEnable);

		if (null != cbic) {
			cbic.removeAll();
			cbic.setEnabled(isEnable);
		}

	}

	
	/**
	 * Methos to remove/clear artcle
	 */
	private void clearArticleTable() {
		if (null != tblArticle)
			tblArticle.removeAll();

		// Removing Article that displayed previous Action.

		if (null != artlist) {

			int sizeart = artlist.size();
			for (int k = 0; k < sizeart; k++) {
				ArticleUIDTO dto = artlist.get(k);
				dto.getCbname().dispose();
				dto.getTxtnoofarticle().dispose();
				dto.getTxtweight().dispose();
				dto.getTxtlength().dispose();
				dto.getTxtbreadth().dispose();
				dto.getTxtheight().dispose();
				dto.getTxtchargeweight().dispose();
				dto.getTxtdesc().dispose();
				dto.getBtndelete().dispose();
				dto.getTxtartvalue().dispose();
			}
			artlist = new ArrayList<ArticleUIDTO>();
		}
	}

	/**
	 * 
	 * @param combo
	 * @param show
	 */
	private static void showPopup(Combo combo, boolean show) {
		if (!combo.isDisposed()) {
			OS.SendMessage(combo.handle, OS.CB_SHOWDROPDOWN, show ? 1 : 0, 0);
		}
	}

}
