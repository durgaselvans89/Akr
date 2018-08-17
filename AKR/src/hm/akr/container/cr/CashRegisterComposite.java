package hm.akr.container.cr;

import hm.akr.common.AlphaNumericValidation;
import hm.akr.common.BeanUtil;
import hm.akr.common.CellValidation;
import hm.akr.common.FloatLimitValidation;
import hm.akr.common.Hour;
import hm.akr.common.KalendarDialog;
import hm.akr.common.NumericValidation;
import hm.akr.common.RoundOff;
import hm.akr.common.SMS;
import hm.akr.common.SWTResourceManager;
import hm.akr.container.lr.LRCompositeHandler;
import hm.akr.dto.ArticleDTO;
import hm.akr.dto.BookingDTO;
import hm.akr.dto.CashDTO;
import hm.akr.dto.CashRegisterDTO;
import hm.akr.dto.DCProfileDTO;
import hm.akr.dto.LRNumberRangeDTO;
import hm.akr.dto.SMSDTO;
import hm.akr.dto.StationsDTO;
import hm.akr.dto.printer.CashDTODecorator;
import hm.akr.dto.printer.CashPrinterDTO;
import hm.akr.exceptions.BusinessException;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.naming.NamingException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
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
 * 
 * @version 1.0
 */
public class CashRegisterComposite extends Composite {

	CashRegisterContainerHandler handler = null;

	LRCompositeHandler lrhandler = null;

	String[] crTabType = new String[] { "CR UPDATE", "CR PRINT", "CR CANCEL" };

	String[] crType = new String[] { "TOPAY", "PAID", "BILLING" };

	private Text txtLrdate;

	private Label lblDdExtra;

	private Text text4;

	private Label label7;

	private String printarticles = null;

	private String printarticles_desc = null;

	private Label label6;

	private Label label5;

	private Text text5;

	private Text text3;

	private Text text2;

	ArrayList<CashRegisterUI> cashuiinfo = null;

	CashRegisterDTO[] topay = null;

	CashRegisterDTO[] paid = null;

	CashRegisterDTO[] billing = null;

	CashDTO[] cashdto = null;

	private Label label3;

	private Label label2;

	private Label lblLrNo;

	private Text txtLrNo;

	private List listLrBill;

	private List listLrPaid;

	private List listLrTopay;

	private TabFolder tabList;

	private Button btnPrint;

	private Label lblName;

	private Button btnView;

	private Button btnNew;

	private Label label38;

	private Text txtCr;

	private Button btnCancel;

	private Canvas canvas1;

	private Text txtDate;

	private Text txtRec;

	private Label label1;

	private Label lblThanks;

	boolean crflag = false;

	private Label lblDate;

	private Text txtTotal;

	private Text txtPt;

	private Text txtUnder;

	private Text txtDemu;

	private Text txtOthers;

	private Text txtDdExtra;

	private Text txtDd;

	private Text txtFreight;

	private Label lblTotal;

	private Label lblPT;

	private Label lblUnder;

	private Label lblDemu;

	private Label lblOthers;

	private Label lblDDCharges;

	private Label lblFreight;

	private Label lblSep;

	private Text txtWeight;

	private Text txtArticles;

	private Text txtTo;

	private Text txtFrom;

	private Label lblWeight;

	private Label lblArticle;

	private Label lblTo;

	ArrayList<CashRegisterDTO> topaylist = null;

	ArrayList<CashRegisterDTO> paidlist = null;

	ArrayList<CashRegisterDTO> billinglist = null;

	ArrayList<CashRegisterDTO> cashlist = new ArrayList<CashRegisterDTO>();

	CashRegisterUI[] cashuidto = null;

	private Label lblFrom;

	private Label lblLrDate;

	private Label lablParticulars;

	private Label lblSum;

	private TabFolder crTab;

	private Text txtSum;

	private TabItem[] tabItem;

	public Color redColor;

	private Text txtStaName;

	private Button btncrprint;

	private TableColumn tblprintcrno;

	private Button btncrprintfrom;

	private Text txtcrprintfrom;

	private Label lblcrprintfrom;

	private Label lblcdate;

	private Text txtcdate;

	private TableColumn tblctotal;

	private Button crcbilling;

	private Button crcpaid;

	private Button crctopay;

	private Canvas canvas;

	private Text txtStaCode;

	private Label lblStationcode;

	private Label lblStationname;

	Shell shell;

	private Table tblStationary;

	float globalTotal = 0;

	float temp1 = 0;

	boolean gflag = false;

	private Label lblMessage;

	private String SERVER_DATE = null;

	private TableColumn tblpsno;

	private TableColumn tblprfrom;

	private TableColumn tblpsumofrs;

	private TableColumn tblplrno;

	private TableColumn tblplrdate;

	private TableColumn tblrfrom;

	private TableColumn tblpto;

	private TableColumn tblpnoa;

	private TableColumn tblpweight;

	private TableColumn tblpfreight;

	private TableColumn tblpddc;

	private TableColumn tblpddextra;

	private TableColumn tblpothers;

	private TableColumn tblpdemurrage;

	private TableColumn tblpundercharge;

	private TableColumn tblpptc;

	private Canvas canvasCRPrint;

	private Table tblCRPrint;

	private Canvas canvasCRCreate;

	private Table tblCRCreate;

	private TableColumn tblpinwarddate;

	private CashRegisterUI uidto;

	private TableEditor editor;

	private Button btncsubmit;

	private Display display;

	private TableColumn tblccnorphone;

	private TableColumn tblccneephone;

	private TableColumn tblccnorname;

	private Text txtConsignorName;

	private Text txtCnorPhone;

	private Text txtCneePhone;

	private TableColumn tblcratetype;

	private TableColumn tblcsms;

	private String currStn = null;

	private TableColumn tblcactualweight;

	private TableColumn tblcCRNO;

	private boolean drsConfirmed = false;

	/**
	 * Constructor method
	 * 
	 * @param shell
	 */
	public CashRegisterComposite(Shell shell, int swtValue) {
		super(shell, swtValue);
		this.shell = shell;
		redColor = shell.getDisplay().getSystemColor(SWT.COLOR_RED);

		try {
			handler = new CashRegisterContainerHandler();
			SERVER_DATE = handler.getServerDate();
			lrhandler = new LRCompositeHandler();
			currStn = handler.getStationCode();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	{
		// Register as a resource user - SWTResourceManager will
		// handle the obtaining and disposing of resources
		SWTResourceManager.registerResourceUser(this);
	}

	/**
	 * Method to load Cash register Composite
	 * 
	 * @return
	 */
	public Composite loadComposite() {
		try {
			this.setLayout(new FormLayout());
			this.setSize(1024, 692);
			{
				lblName = new Label(this, SWT.NONE);
				FormData lblNameLData = new FormData();
				lblNameLData.width = 211;
				lblNameLData.height = 32;
				lblNameLData.left = new FormAttachment(0, 1000, 791);
				lblNameLData.top = new FormAttachment(0, 1000, 20);
				lblName.setLayoutData(lblNameLData);
				lblName.setText("CASH RECEIPT");
				lblName.setFont(SWTResourceManager.getFont("Tahoma", 20, 1,
						false, false));
			}
			{
				btnView = new Button(this, SWT.PUSH | SWT.CENTER);
				FormData btnViewLData = new FormData();
				btnViewLData.width = 34;
				btnViewLData.height = 23;
				btnViewLData.left = new FormAttachment(0, 1000, 960);
				btnViewLData.top = new FormAttachment(0, 1000, 153);
				btnView.setLayoutData(btnViewLData);
				btnView.setText("View");
				btnView.addSelectionListener(new CRListener());
				btnView.setEnabled(false);
			}
			{
				btnNew = new Button(this, SWT.PUSH | SWT.CENTER);
				FormData btnNewLData = new FormData();
				btnNewLData.width = 33;
				btnNewLData.height = 23;
				btnNewLData.left = new FormAttachment(0, 1000, 921);
				btnNewLData.top = new FormAttachment(0, 1000, 153);
				btnNew.setLayoutData(btnNewLData);
				btnNew.setText("New");
				btnNew.addSelectionListener(new CRListener());
				btnNew.setEnabled(false);
			}
			{
				FormData canvas1LData = new FormData();
				canvas1LData.width = 199;
				canvas1LData.height = 97;
				canvas1LData.left = new FormAttachment(0, 1000, 49);
				canvas1LData.top = new FormAttachment(0, 1000, 23);

			}
			{
				txtStaName = new Text(this, SWT.BORDER);
				FormData coStaNameLData = new FormData();
				coStaNameLData.width = 110;
				coStaNameLData.height = 18;
				coStaNameLData.left = new FormAttachment(0, 1000, 673);
				coStaNameLData.top = new FormAttachment(0, 1000, 107);
				txtStaName.setLayoutData(coStaNameLData);
				txtStaName.setText(handler.getStationName());
				txtStaName.setEnabled(false);

			}
			{
				lblStationname = new Label(this, SWT.NONE);
				FormData label3LData = new FormData();
				label3LData.width = 64;
				label3LData.height = 13;
				label3LData.left = new FormAttachment(0, 1000, 597);
				label3LData.top = new FormAttachment(0, 1000, 111);
				lblStationname.setLayoutData(label3LData);
				lblStationname.setText("Station Name");
			}
			{
				label38 = new Label(this, SWT.NONE);
				label38.setText("Station Code");
				FormData label38LData = new FormData();
				label38LData.width = 62;
				label38LData.height = 13;
				label38LData.left = new FormAttachment(0, 1000, 812);
				label38LData.top = new FormAttachment(0, 1000, 113);
				label38.setLayoutData(label38LData);
			}
			{
				txtStaCode = new Text(this, SWT.BORDER);
				txtStaCode.setEnabled(false);
				FormData txtBillStaCodeLData = new FormData();
				txtBillStaCodeLData.width = 110;
				txtBillStaCodeLData.height = 18;
				txtBillStaCodeLData.left = new FormAttachment(0, 1000, 881);
				txtBillStaCodeLData.top = new FormAttachment(0, 1000, 109);
				txtStaCode.setLayoutData(txtBillStaCodeLData);
				txtStaCode.setText(handler.getStationCode());

			}
			{
				crTab = new TabFolder(this, SWT.NONE);
				FormData tabFolder1LData = new FormData();
				tabFolder1LData.width = 950;
				tabFolder1LData.height = 450;
				tabFolder1LData.left = new FormAttachment(0, 1000, 40);
				tabFolder1LData.top = new FormAttachment(0, 1000, 176);
				crTab.setLayoutData(tabFolder1LData);

				lblMessage = new Label(crTab, SWT.NONE);
				lblMessage.setBounds(10, 400, 170, 22);
				lblMessage.setText("* Date given above is Inward Date");

				int len = crTabType.length;
				tabItem = new TabItem[len];
				
					if(BeanUtil.getDbName() == null || BeanUtil.getDbName().equals("")){
						for (int i = 0; i < len; i++) {
						tabItem[i] = new TabItem(crTab, SWT.NONE);
						tabItem[i].setText(crTabType[i]);
						}
					}else{
						tabItem[0] = new TabItem(crTab, SWT.NONE);
						tabItem[0].setText(crTabType[1]);
					}
					
				

				if(BeanUtil.getDbName() == null || BeanUtil.getDbName().equals("")){
					canvas = getCRCreateCanvas();
					canvas.setSize(963, 464);
					tabItem[0].setControl(canvas);
					lblMessage.setVisible(false);

					populateCashDetails("TOPAY", false);
					crTab.setSelection(0);

				}else{
					btnNew.setEnabled(false);
					btnView.setEnabled(false);
					canvas = getCRPrintCanvas();
					canvas.setSize(963, 464);
					tabItem[0].setControl(canvas);
					lblMessage.setVisible(false);
					crTab.setSelection(0);
				} 
				
				crTab.addSelectionListener(new SelectionListener() {

					@Override
					public void widgetDefaultSelected(SelectionEvent arg0) {

					}

					@Override
					public void widgetSelected(SelectionEvent arg0) {
						String selectedTab = crTab.getSelection()[0].getText();

						
						
						if ((BeanUtil.getDbName() == null || BeanUtil.getDbName().equals("")) && 
								 crTabType[2].equals(selectedTab)) {
							canvas = getCanvas();
							btnNew.setEnabled(true);
							btnView.setEnabled(true);
							canvas.setSize(963, 464);
							populateLR(0);
							tabItem[2].setControl(canvas);
							lblMessage.setVisible(false);
						} else if (crTabType[1].equals(selectedTab)) {
							btnNew.setEnabled(false);
							btnView.setEnabled(false);
							canvas = getCRPrintCanvas();
							canvas.setSize(963, 464);
							tabItem[1].setControl(canvas);
							lblMessage.setVisible(false);
						} else if ((BeanUtil.getDbName() == null || BeanUtil.getDbName().equals("")) && 
								 crTabType[0].equals(selectedTab)) {
							btnNew.setEnabled(false);
							btnView.setEnabled(false);
							canvas = getCRCreateCanvas();
							canvas.setSize(963, 464);
							tabItem[0].setControl(canvas);
							lblMessage.setVisible(false);

							populateCashDetails("TOPAY", false);
						}
						
						

					}

				});
				

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	/**
	 * Method to Display status Message for Every Method
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
	 * Method to return Cash Register Canvas
	 * 
	 * @return
	 */
	private Canvas getCanvas() {
		canvas = new Canvas(crTab, SWT.BORDER);
		{
			txtDate = new Text(canvas, SWT.BORDER);
			txtDate.setEnabled(false);
			txtDate.setBounds(817, 38, 89, 22);
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
			txtCr = new Text(canvas, SWT.BORDER);
			txtCr.setBounds(817, 6, 89, 22);
			txtCr.setTextLimit(7);
			txtCr.addVerifyListener(new AlphaNumericValidation());
			txtCr.addFocusListener(new crvalidation());

		}
		{
			txtSum = new Text(canvas, SWT.BORDER);
			txtSum.setEnabled(false);
		}		
		{
			lblSum = new Label(canvas, SWT.NONE);
			FormData label9LData = new FormData();
			label9LData.width = 90;
			label9LData.height = 18;
			lblSum.setLayoutData(label9LData);
			lblSum.setText("a sum of Rs.");
			lblSum.setFont(SWTResourceManager.getFont("Verdana", 9, 1, false,
					false));
		}

		{
			lablParticulars = new Label(canvas, SWT.CENTER);
			lablParticulars.setText("PARTICULARS");
			lablParticulars.setBounds(514, 74, 138, 24);
			lablParticulars.setFont(SWTResourceManager.getFont("Verdana", 10,
					1, false, false));
		}
		{
			lblLrDate = new Label(canvas, SWT.RIGHT);
			lblLrDate.setText("LR Date");
			lblLrDate.setBounds(269, 152, 60, 15);
		}
		{
			lblFrom = new Label(canvas, SWT.RIGHT);
			lblFrom.setText("From");
			lblFrom.setBounds(269, 183, 60, 17);
		}
		{
			lblTo = new Label(canvas, SWT.RIGHT);
			lblTo.setText("To");
			lblTo.setBounds(269, 213, 60, 23);
		}
		{
			lblArticle = new Label(canvas, SWT.RIGHT);
			lblArticle.setText("No. of Articles");
			lblArticle.setBounds(249, 243, 80, 21);
		}
		{
			lblWeight = new Label(canvas, SWT.RIGHT);
			lblWeight.setText("Weight");
			lblWeight.setBounds(269, 275, 60, 20);
		}
		{
			txtLrdate = new Text(canvas, SWT.BORDER);
			txtLrdate.setBounds(363, 151, 100, 20);
			txtLrdate.setTextLimit(10);
			txtLrdate.setEnabled(false);
		}
		{
			txtFrom = new Text(canvas, SWT.BORDER);
			txtFrom.setBounds(362, 181, 100, 20);
			txtFrom.setEnabled(false);
		}
		{
			txtTo = new Text(canvas, SWT.BORDER);
			txtTo.setBounds(362, 211, 100, 20);
			txtTo.setEnabled(false);
		}
		{
			txtArticles = new Text(canvas, SWT.BORDER);
			txtArticles.setBounds(363, 241, 100, 20);
			txtArticles.setEnabled(false);
		}
		{
			txtWeight = new Text(canvas, SWT.BORDER);
			txtWeight.setBounds(363, 271, 100, 20);
			txtWeight.setEnabled(false);
		}
		{
			lblSep = new Label(canvas, SWT.SEPARATOR);
			lblSep.setText("lblSep");
			lblSep.setBounds(568, 110, 11, 241);
		}
		{
			lblFreight = new Label(canvas, SWT.RIGHT);
			lblFreight.setText("Freight");
			lblFreight.setBounds(269, 303, 60, 16);
		}
		{
			lblDDCharges = new Label(canvas, SWT.RIGHT);
			lblDDCharges.setText("DD Charges");
			lblDDCharges.setBounds(269, 333, 60, 20);
		}
		{
			lblDdExtra = new Label(canvas, SWT.RIGHT);
			lblDdExtra.setText("DD Extra");
			lblDdExtra.setBounds(647, 153, 60, 19);
		}
		{
			lblOthers = new Label(canvas, SWT.RIGHT);
			lblOthers.setText("Others");
			lblOthers.setBounds(647, 120, 60, 18);
		}
		{
			lblDemu = new Label(canvas, SWT.RIGHT);
			lblDemu.setText("Demurrage");
			lblDemu.setBounds(647, 182, 60, 20);
		}
		{
			lblUnder = new Label(canvas, SWT.RIGHT);
			lblUnder.setText("Under Charge");
			lblUnder.setBounds(635, 212, 72, 15);
		}
		{
			lblPT = new Label(canvas, SWT.RIGHT);
			lblPT.setText("P&T Charges");
			lblPT.setBounds(626, 240, 81, 21);
		}
		{
			lblTotal = new Label(canvas, SWT.RIGHT);
			lblTotal.setText("Total");
			lblTotal.setBounds(644, 272, 61, 16);
		}
		{
			txtFreight = new Text(canvas, SWT.LEFT | SWT.BORDER);
			txtFreight.setBounds(363, 301, 100, 20);
			txtFreight.setEnabled(false);
		}
		{
			txtDd = new Text(canvas, SWT.LEFT | SWT.BORDER);
			txtDd.setBounds(363, 331, 100, 20);
			txtDd.setEnabled(false);
		}
		{
			txtOthers = new Text(canvas, SWT.LEFT | SWT.BORDER);
			txtOthers.setBounds(738, 120, 100, 20);
			txtOthers.setEnabled(false);
			txtOthers.setTextLimit(10);
			txtOthers.addFocusListener(new CRListener());
			txtOthers.addVerifyListener(new FloatLimitValidation());

		}
		{
			txtDdExtra = new Text(canvas, SWT.LEFT | SWT.BORDER);
			txtDdExtra.setBounds(738, 150, 100, 20);
			txtDdExtra.addFocusListener(new CRListener());
			txtDdExtra.setTextLimit(10);
			txtDdExtra.addVerifyListener(new FloatLimitValidation());

		}

		{
			txtDemu = new Text(canvas, SWT.LEFT | SWT.BORDER);
			txtDemu.setBounds(738, 180, 100, 20);
			txtDemu.addFocusListener(new CRListener());
			txtDemu.setTextLimit(10);
			txtDemu.addVerifyListener(new FloatLimitValidation());

		}
		{
			txtUnder = new Text(canvas, SWT.LEFT | SWT.BORDER);
			txtUnder.setBounds(738, 210, 100, 20);
			txtUnder.addFocusListener(new CRListener());
			txtUnder.setTextLimit(10);
			txtUnder.addVerifyListener(new FloatLimitValidation());

		}
		{
			txtPt = new Text(canvas, SWT.LEFT | SWT.BORDER);
			txtPt.setBounds(738, 240, 100, 20);
			txtPt.addFocusListener(new CRListener());
			txtPt.setTextLimit(10);
			txtPt.addVerifyListener(new FloatLimitValidation());

		}
		{
			txtTotal = new Text(canvas, SWT.LEFT | SWT.BORDER);
			txtTotal.setBounds(738, 270, 100, 20);
			txtTotal.setEnabled(false);
		}
		{
			txtConsignorName = new Text(canvas, SWT.LEFT | SWT.BORDER);
			txtConsignorName.setBounds(738, 320, 100, 20);
			txtConsignorName.setEnabled(false);
		}
		{
			txtCnorPhone = new Text(canvas, SWT.LEFT | SWT.BORDER);

		}
		{
			txtCneePhone = new Text(canvas, SWT.LEFT | SWT.BORDER);

		}

		{
			lblStationcode = new Label(canvas, SWT.NONE);
			lblStationcode.setText("CR No.");
			lblStationcode.setBounds(765, 10, 42, 13);
		}
		{
			lblDate = new Label(canvas, SWT.NONE);
			lblDate.setText("Date");
			lblDate.setBounds(772, 41, 40, 16);
		}

		{
			lblThanks = new Label(canvas, SWT.NONE);
			lblThanks.setText("Received with Thanks From");
			lblThanks.setBounds(185, 27, 137, 20);
		}
		{
			label1 = new Label(canvas, SWT.NONE);
			label1.setText("a sum of Rs.");
			label1.setBounds(514, 28, 63, 20);
		}
		{
			txtRec = new Text(canvas, SWT.BORDER);
			txtRec.setBounds(324, 24, 175, 24);
			txtRec.setEnabled(false);
		}
		{
			txtSum = new Text(canvas, SWT.BORDER);
			txtSum.setBounds(578, 24, 139, 23);
			txtSum.setEnabled(false);
		}

		{

			btnCancel = new Button(canvas, SWT.PUSH | SWT.CENTER);
			btnCancel.setText("Cancel");
			btnCancel.setBounds(652, 383, 66, 23);
			txtDdExtra.setEnabled(false);
			txtDemu.setEnabled(false);
			txtUnder.setEnabled(false);
			txtPt.setEnabled(false);
			btnCancel.setEnabled(false);
			btnCancel.addSelectionListener(new CRListener());

		}

		{
			txtLrNo = new Text(canvas, SWT.LEFT | SWT.BORDER);
			txtLrNo.setBounds(363, 121, 100, 20);
			txtLrNo.setEnabled(false);
		}
		{
			lblLrNo = new Label(canvas, SWT.NONE);
			lblLrNo.setText("LrNo");
			lblLrNo.setBounds(269, 122, 60, 23);
			lblLrNo.setAlignment(SWT.RIGHT);
		}
		{
			label2 = new Label(canvas, SWT.RIGHT);
			label2.setText("DD Charges");
			label2.setBounds(269, 333, 60, 20);
		}
		{
			label3 = new Label(canvas, SWT.RIGHT);
			label3.setText("Freight");
			label3.setBounds(269, 303, 60, 16);
		}
		{
			text2 = new Text(canvas, SWT.BORDER);
			text2.setEnabled(false);
			text2.setBounds(363, 271, 100, 20);
		}
		{
			text3 = new Text(canvas, SWT.BORDER);
			text3.setEnabled(false);
			text3.setBounds(363, 241, 100, 20);
		}
		{
			text4 = new Text(canvas, SWT.BORDER);
			text4.setEnabled(false);
			text4.setBounds(362, 211, 100, 20);
		}
		{
			text5 = new Text(canvas, SWT.BORDER);
			text5.setEnabled(false);
			text5.setBounds(362, 181, 100, 20);
		}
		{
			label5 = new Label(canvas, SWT.RIGHT);
			label5.setText("No. of Articles");
			label5.setBounds(249, 243, 80, 21);
		}
		{
			label6 = new Label(canvas, SWT.RIGHT);
			label6.setText("To");
			label6.setBounds(269, 213, 60, 23);
		}
		{
			label7 = new Label(canvas, SWT.RIGHT);
			label7.setText("From");
			label7.setBounds(269, 183, 60, 17);
		}

		return canvas;
	}

	public ArrayList<LRNumberRangeDTO> getUnUsedCRList(String crno)
			throws Exception {

		int len = 0;
		LRNumberRangeDTO temp = null;
		ArrayList<LRNumberRangeDTO> unusedList = new ArrayList<LRNumberRangeDTO>();
		int fromNumber = 0;
		int toNumber = 0;
		String stationcode = handler.getStationCode();
		String lrprefix;
		String lrNumber = null;

		boolean isAlreadyAvail = true;

		LRNumberRangeDTO[] lrrangeDTO = handler.getCRRange(handler
				.getStationCode());

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
	 * Method to return Stationary Canvas
	 * 
	 * @return
	 */

	private ArrayList<String> populateCRStationary(boolean isdisplay) {
		ArrayList<String> list = new ArrayList<String>();
		String UsedCR = null;
		try {

			UsedCR = handler.getLastUsedCR(handler.getStationCode());

			System.out.println("last used->"+UsedCR);
		} catch (Exception e) {
			e.printStackTrace();
		}

		ArrayList<LRNumberRangeDTO> unusedLrlist = null;
		try {
			unusedLrlist = getUnUsedCRList(UsedCR);

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

	/**
	 * 
	 * @return
	 */
	private CashDTO[] buildCashDTO() {
		try {
			TableItem[] items = tblCRCreate.getItems();
			int len = 0;
			int cheked = 0;
			if (null != items && (len = items.length) > 0) {
				for (int k = 0; k < len; k++) {
					if (items[k].getChecked()) {
						cheked++;

					}
				}
			}
			if (cheked == 0) {
				return null;
			}
			ArrayList<CashDTO> checkedList = new ArrayList<CashDTO>();
			ArrayList<SMSDTO> smsList = new ArrayList<SMSDTO>();
			

			int j = 0;
			if (null != items && (len = items.length) > 0) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				boolean flag = false;
				for (int i = 0; i < len - 1; i++) {

					if (items[i].getChecked()) {
						int alert = 0;
					
						CashDTO cashdto = new CashDTO();
						String temp = null;
						temp = items[i].getText(1);						
						cashdto.setLr_no(temp);
						cashdto.setStation_code(handler.getStationCode());
						cashdto.setCr_no(items[i].getText(4));
						temp = items[i].getText(18);
						if (temp.length() > 0)
							cashdto.setTotal_amount(Float.parseFloat(temp));

						temp = cashuidto[i].getTxtdemurrage().getText();
						if (temp.length() > 0)
							cashdto.setDemurrage(Float.parseFloat(temp));
						if (temp.equalsIgnoreCase("0")) {
							alert++;
						}

						temp = cashuidto[i].getTxtptc().getText();
						if (temp.length() > 0)
							cashdto.setPostage(Float.parseFloat(temp));
						if (temp.equalsIgnoreCase("0")) {
							alert++;
						}

						temp = cashuidto[i].getTxtundercharge().getText();
						if (temp.length() > 0)
							cashdto.setUnder_charge(Float.parseFloat(temp));
						if (temp.equalsIgnoreCase("0")) {
							alert++;
						}

						temp = cashuidto[i].getTxtextra().getText();

						if (temp.length() > 0)
							cashdto.setDd_extra(Float.parseFloat(temp));
						if (temp.equalsIgnoreCase("0")) {
							alert++;
						}

						temp = cashuidto[i].getTxtothers().getText();

						if (temp.length() > 0) {
							cashdto.setOther(Float.parseFloat(temp));
						}
						if (temp.equalsIgnoreCase("0")) {
							alert++;
						}

						temp = cashuidto[i].getTxtconsignee().getText();

						if (temp.length() > 0) {
							cashdto.setConsigneeName(temp);
						}

						temp = items[i].getText(2);
						cashdto.setLr_date(dateFormat.parse(temp));
						cashdto.setTo_station(items[i].getText(6));
						
						cashdto.setCnor_phone(items[i].getText(19));
						cashdto.setCnee_phone(items[i].getText(20));
						cashdto.setConsignorName(items[i].getText(21));
						cashdto.setRate_type(Integer.parseInt(items[i]
								.getText(22)));
						cashdto.setSms_alert(Integer.parseInt(items[i]
								.getText(23)));
						float actual_weight = Float.parseFloat(items[i]
								.getText(24));

						float dc_commission = getDeliveryCommission(actual_weight);
						dc_commission = RoundOff
								.getRounded2Decimal(dc_commission);
						//System.out.println("DDCCC--->" + dc_commission);

						cashdto.setDelivery_ommission(dc_commission);

						boolean isDoorDelivery = isDoorDelivey(cashdto);

						/*
						 * if (isDoorDelivery) { isConsignee =
						 * isDoorDeliveryInDestination(cashdto); }
						 */

						String type = crctopay.getSelection() ? "Topay"
								: crcpaid.getSelection() ? "PAID" : crcbilling
										.getSelection() ? "Billing" : "None";

						SMSDTO smsdto = null;
						Date limitDate = null;
						String curBranch = null;
						String destBranch = null;

						limitDate = dateFormat.parse("25-04-2010");
						curBranch = getBranch(handler.getStationCode());
						destBranch = getBranch(cashdto.getTo_station());

						if (cashdto.getLr_date().after(limitDate)
								&& curBranch.equalsIgnoreCase(destBranch)) {
							smsdto = createSMSMessage(isDoorDelivery, type,
									cashdto, dateFormat.parse(items[i]
											.getText(2)));
						}
						if (null != smsdto)
							smsList.add(smsdto);
						/*
						 * if (isConsignee) sendSMS(cashdto.getCnee_phone(),
						 * message); else sendSMS(cashdto.getCnor_phone(),
						 * message);
						 */

						j++;

						checkedList.add(cashdto);
						if (alert == 5) {
							flag = true;
						}
					}
				}
				if (flag)
					displayError("All CR Values are Zero");
			}

			int smssize = smsList.size();
			SMSDTO[] to_add = null;
			if (smssize > 0) {
				to_add = (SMSDTO[]) smsList.toArray(new SMSDTO[smssize]);
			}

			int size = checkedList.size();
			if (size > 0) {
				CashDTO[] dto = (CashDTO[]) checkedList
						.toArray(new CashDTO[size]);
				dto[0].setSms(to_add);
				return dto;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 
	 * @param actual_weight
	 * @return
	 * @throws Exception
	 */
	private float getDeliveryCommission(float actual_weight) throws Exception {
		float dc_commission_in = 0;
		dc_commission_in = (actual_weight / 1000 * getDCCommissionPerTon());
		return dc_commission_in;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	private float getDCCommissionPerTon() throws Exception {

		DCProfileDTO[] dcProfiledto = handler.getDCDetails();
		if (null != dcProfiledto) {
			for (int k = 0; k < dcProfiledto.length; k++) {
				if (dcProfiledto[k].getStation_code().equalsIgnoreCase(
						handler.getStationCode())) {
					//System.out.println("Assigned DC:"+ dcProfiledto[k].getDc_per_ton());
					return dcProfiledto[k].getDc_per_ton();
				}

			}
		}
		return 0;
	}

	/**
	 * 
	 * @param stationCode
	 * @return
	 */
	private String getBranch(String stationCode) {
		try {
			StationsDTO[] stationsDTO = handler.getAvailableStations();

			for (int i = 0; i < stationsDTO.length; i++) {
				if (stationsDTO[i].getId().equalsIgnoreCase(stationCode)) {
					return stationsDTO[i].getBranch_code();
				}
			}

		} catch (Exception e) {

		}
		return null;
	}

	/**
	 * 
	 * @param phone
	 * @param message
	 */
	private void sendSMS(String phone, String message) {

		if (CellValidation.checkCellNumber(phone)) {
			SMS sms = new SMS();
			sms.sendSMS(phone, message);
		}

	}

	/**
	 * 
	 * @param isDoorDelivery
	 * @param lr_type
	 * @param dto
	 * @return
	 */
	private SMSDTO createSMSMessage(boolean isDoorDelivery, String lr_type,
			CashDTO dto, Date inward) {

		SMSDTO temp = null;
		DateFormat smsFormat = new SimpleDateFormat("dd/MMM/yy");
		boolean send = false;

		try {
			String ConsignorMsg = null;
			String ConsigneeMsg = null;

			if (isDoorDelivery) {
				if (lr_type.equalsIgnoreCase("Topay")) {

					ConsignorMsg = "Greetings from AKR! Your LR "
							+ dto.getLr_no()
							+ " dt "
							+ smsFormat.format(dto.getLr_date())
							+ " to "
							+ getTruncatedName(dto.getConsigneeName(), 20)
							+ " is out for delivery and will be delivered around "
							+ Hour.getHour(3) + ".Thank you for using AKR!";

					ConsigneeMsg = "AKR-Greetings.LR " + dto.getLr_no()
							+ " dt " + smsFormat.format(dto.getLr_date())
							+ " from "
							+ getTruncatedName(dto.getConsignorName(), 20)
							+ " will be delivered around " + Hour.getHour(3)
							+ ".Total is Rs." + (int) dto.getTotal_amount()
							+ ".Pl inform " + getPhoneNumber(currStn.trim())
							+ "  if you are unavailable";

				} else if (lr_type.equalsIgnoreCase("Paid")) {

					ConsignorMsg = "Greetings from AKR! Your LR "
							+ dto.getLr_no()
							+ " dt "
							+ smsFormat.format(dto.getLr_date())
							+ " to "
							+ getTruncatedName(dto.getConsigneeName(), 20)
							+ " is out for delivery and will be delivered around "
							+ Hour.getHour(3) + ".Thank you for using AKR!";

					ConsigneeMsg = "AKR-Greetings.LR " + dto.getLr_no()
							+ " dt " + smsFormat.format(dto.getLr_date())
							+ " from "
							+ getTruncatedName(dto.getConsignorName(), 20)
							+ " will be delivered around " + Hour.getHour(3)
							+ ".Total is Rs." + (int) dto.getTotal_amount()
							+ ".Pl inform " + getPhoneNumber(currStn.trim())
							+ " if you are unavailable";

				} else if (lr_type.equalsIgnoreCase("Billing")) {
					ConsignorMsg = "AKR-Greetings! Your LR "
							+ dto.getLr_no()
							+ " dt "
							+ smsFormat.format(dto.getLr_date())
							+ " to "
							+ getTruncatedName(dto.getConsigneeName(), 27)
							+ " is out for delivery and will be delivered around "
							+ Hour.getHour(3) + ". Thank you for using AKR!";

					ConsigneeMsg = "Greetings from AKR.LR " + dto.getLr_no()
							+ " dt " + smsFormat.format(dto.getLr_date())
							+ " from "
							+ getTruncatedName(dto.getConsignorName(), 20)
							+ " will be delivered around " + Hour.getHour(3)
							+ ".Pl inform " + getPhoneNumber(currStn.trim())
							+ " if you are unavailable.Thank you!";
				}

			} else {
				if (lr_type.equalsIgnoreCase("Topay")) {
					send = true;
					int diff = getDateDifference(inward);
					if (diff < 4) {
						ConsignorMsg = "Greetings from AKR! Your LR "
								+ dto.getLr_no() + " dt "
								+ smsFormat.format(dto.getLr_date()) + " to "
								+ getTruncatedName(dto.getConsigneeName(), 30)
								+ " was delivered at " + Hour.getHourOnly()
								+ " on " + Hour.getDate() + "!"
								+ " Thank you for using AKR!";
					}

				} else if (lr_type.equalsIgnoreCase("Paid")) {
					send = true;
					int diff = getDateDifference(inward);
					if (diff < 4) {
						ConsignorMsg = "Greetings from AKR! Your LR "
								+ dto.getLr_no() + " dt "
								+ smsFormat.format(dto.getLr_date()) + " to "
								+ getTruncatedName(dto.getConsigneeName(), 30)
								+ " was delivered at " + Hour.getHourOnly()
								+ " on " + Hour.getDate() + "!"
								+ " Thank you for using AKR!";
					}

				} else if (lr_type.equalsIgnoreCase("Billing")) {
					ConsignorMsg = "AKR-Greetings! Your LR  "
							+ dto.getLr_no()
							+ " dt "
							+ smsFormat.format(dto.getLr_date())
							+ " to "
							+ getTruncatedName(dto.getConsigneeName(), 27)
							+ " is out for delivery and will be delivered around "
							+ Hour.getHour(3) + ". Thank you for using AKR!";

					ConsigneeMsg = "Greetings from AKR.LR " + dto.getLr_no()
							+ " dt " + smsFormat.format(dto.getLr_date())
							+ " from "
							+ getTruncatedName(dto.getConsignorName(), 20)
							+ " will be delivered around " + Hour.getHour(3)
							+ ".Pl inform " + getPhoneNumber(currStn.trim())
							+ " if you are unavailable.Thank you!";

				}

			}

			if (dto.getRate_type() == 4 || dto.getRate_type() == 5) {

				if (dto.getSms_alert() == 1) {
					ConsignorMsg = null;
				} else if (dto.getSms_alert() == 2) {
					ConsigneeMsg = null;
				} else if (dto.getSms_alert() == 0) {
					ConsigneeMsg = null;
					ConsignorMsg = null;
				}

			}

			if (lr_type.equalsIgnoreCase("Billing")) {
				ConsignorMsg = null;
			}

			// Sending SMS
			//boolean isUnTime = false;//isUntime();
			boolean isUnTime = isUntime();
			
			if (isUnTime == false) {
				if (null != ConsignorMsg) {
					sendSMS(dto.getCnor_phone(), ConsignorMsg);
				}
				if (null != ConsigneeMsg) {
					sendSMS(dto.getCnee_phone(), ConsigneeMsg);
				}
			} else if (send) {
				temp = new SMSDTO();
				temp.setCnor_phone(dto.getCnor_phone());
				temp.setCnor_message(ConsignorMsg);
				temp.setCnee_phone(dto.getCnee_phone());
				temp.setCnee_message(ConsigneeMsg);

			}

			System.out.println(ConsignorMsg);
			System.out.println(ConsigneeMsg);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return temp;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
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
		
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	private String getTruncatedName(String name, int index) {
		if (null != name) {
			int len = name.length();
			if (len > index) {
				return name.substring(0, index);
			} else {
				return name;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param inward
	 * @return
	 */

	private int getDateDifference(Date inward) {

		try {
			DateFormat form = new SimpleDateFormat("dd-MM-yyyy");
			long diff = form.parse(BeanUtil.getInstance().getServerDate())
					.getTime()
					- inward.getTime();
			diff = diff / (1000 * 60 * 60 * 24);
			return (int) diff;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 
	 * @param code
	 * @return
	 */
	private String getPhoneNumber(String code) {
		StationsDTO[] stations = null;
		try {
			stations = BeanUtil.getInstance().getAvailableStations();
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
	 * @param cashdto
	 * @return
	 */
	private boolean isDoorDeliveryInDestination(CashDTO cashdto) {
		String station_code = handler.getStationCode();
		String dest_code = cashdto.getTo_station();

		String branch_code = getStationBranchCode(dest_code);

		if (station_code.equalsIgnoreCase(dest_code)
				|| station_code.equalsIgnoreCase(branch_code)) {
			return true;
		}

		return false;
	}

	/**
	 * 
	 * @param dest_code
	 * @return
	 */
	private String getStationBranchCode(String dest_code) {
		try {
			StationsDTO[] dto = BeanUtil.getInstance().getAvailableStations();
			if (null != dto) {
				int len = dto.length;
				for (int i = 0; i < len; i++) {
					if (dest_code.equalsIgnoreCase(dto[i].getId())) {
						return dto[i].getId();
					}
				}
			}
		} catch (Exception e) {

		}
		return null;
	}

	/**
	 * 
	 * @param cashdto
	 * @return
	 */
	private boolean isDoorDelivey(CashDTO cashdto) {
		if (cashdto.getDd_extra() != 0 || cashdto.getDdc() != 0) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @return
	 */
	private Canvas getCRCreateCanvas() {
		canvasCRCreate = new Canvas(crTab, SWT.BORDER);
		canvasCRCreate.setSize(995, 475);
		{
			tblCRCreate = new Table(canvasCRCreate, SWT.FULL_SELECTION
					| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER | SWT.CHECK);
			tblCRCreate.setBounds(8, 28, 942, 365);
			tblCRCreate.setHeaderVisible(true);
			tblCRCreate.setLinesVisible(true);

			{
				tblpsno = new TableColumn(tblCRCreate, SWT.NONE);
				tblpsno.setText("S No");
				tblpsno.setWidth(48);
			}
			{
				tblprfrom = new TableColumn(tblCRCreate, SWT.NONE);
				tblprfrom.setWidth(50);
				tblprfrom.setText("Lr No");
			}

			{
				tblplrdate = new TableColumn(tblCRCreate, SWT.NONE);
				tblplrdate.setText("LR Date");
				tblplrdate.setWidth(68);
			}
			{
				tblpinwarddate = new TableColumn(tblCRCreate, SWT.NONE);
				tblpinwarddate.setWidth(70);
				tblpinwarddate.setText("Inward Date");
			}
			{
				tblcCRNO = new TableColumn(tblCRCreate, SWT.NONE);
				tblcCRNO.setText("CR No");
				tblcCRNO.setWidth(63);
				tblcCRNO.setResizable(false);
			}
			{
				tblrfrom = new TableColumn(tblCRCreate, SWT.NONE);
				tblrfrom.setText("From");
				tblrfrom.setWidth(42);
			}
			{
				tblpto = new TableColumn(tblCRCreate, SWT.NONE);
				tblpto.setText("To");
				tblpto.setWidth(42);
			}
			{
				tblplrno = new TableColumn(tblCRCreate, SWT.NONE);
				tblplrno.setText("Received From");
				tblplrno.setWidth(90);
			}
			{
				tblpnoa = new TableColumn(tblCRCreate, SWT.NONE);
				tblpnoa.setText("NOA");
				tblpnoa.setWidth(30);
			}
			{
				tblpweight = new TableColumn(tblCRCreate, SWT.NONE);
				tblpweight.setText("Weight");
				tblpweight.setWidth(45);
			}
			{
				tblpsumofrs = new TableColumn(tblCRCreate, SWT.NONE);
				tblpsumofrs.setText("LR Total");
				tblpsumofrs.setWidth(55);
			}

			{
				tblpfreight = new TableColumn(tblCRCreate, SWT.NONE);
				tblpfreight.setText("Freight");
				tblpfreight.setWidth(55);
			}
			{
				tblpddc = new TableColumn(tblCRCreate, SWT.NONE);
				tblpddc.setText("DDC");
				tblpddc.setWidth(43);
			}
			{
				tblpddextra = new TableColumn(tblCRCreate, SWT.NONE);
				tblpddextra.setText("DD Extra");
				tblpddextra.setWidth(43);
			}
			{
				tblpothers = new TableColumn(tblCRCreate, SWT.NONE);
				tblpothers.setText("Others");
				tblpothers.setWidth(43);
			}
			{
				tblpdemurrage = new TableColumn(tblCRCreate, SWT.NONE);
				tblpdemurrage.setText("Demurrage");
				tblpdemurrage.setWidth(43);
			}
			{
				tblpundercharge = new TableColumn(tblCRCreate, SWT.NONE);
				tblpundercharge.setText("Under Charge");
				tblpundercharge.setWidth(43);
			}
			{
				tblpptc = new TableColumn(tblCRCreate, SWT.NONE);
				tblpptc.setText("P&T Charge");
				tblpptc.setWidth(43);
			}
			{
				tblctotal = new TableColumn(tblCRCreate, SWT.NONE);
				tblctotal.setText("Total");
				tblctotal.setWidth(60);
			}
			{
				tblccnorphone = new TableColumn(tblCRCreate, SWT.NONE);
				tblccnorphone.setText("CnorPhone");
				tblccnorphone.setWidth(0);
				tblccnorphone.setResizable(false);
			}
			{
				tblccneephone = new TableColumn(tblCRCreate, SWT.NONE);
				tblccneephone.setText("CneePhone");
				tblccneephone.setWidth(0);
				tblccneephone.setResizable(false);
			}
			{
				tblccnorname = new TableColumn(tblCRCreate, SWT.NONE);
				tblccnorname.setText("CnorName");
				tblccnorname.setWidth(0);
				tblccnorname.setResizable(false);
			}
			{
				tblcratetype = new TableColumn(tblCRCreate, SWT.NONE);
				tblcratetype.setText("Rate Type");
				tblcratetype.setWidth(0);
				tblcratetype.setResizable(false);
			}
			{
				tblcsms = new TableColumn(tblCRCreate, SWT.NONE);
				tblcsms.setText("SMS");
				tblcsms.setWidth(0);
				tblcsms.setResizable(false);
			}
			{
				tblcactualweight = new TableColumn(tblCRCreate, SWT.NONE);
				tblcactualweight.setText("AWe");
				tblcactualweight.setWidth(60);
				tblcactualweight.setResizable(false);
			}
			

		}
		{
			crctopay = new Button(canvasCRCreate, SWT.RADIO | SWT.LEFT);
			crctopay.setText("Topay");
			crctopay.setBounds(10, 2, 52, 18);
			crctopay.addSelectionListener(new CRListener());
			crctopay.setSelection(true);
		}
		{
			crcpaid = new Button(canvasCRCreate, SWT.RADIO | SWT.LEFT);
			crcpaid.setText("Paid");
			crcpaid.setBounds(66, 3, 41, 18);
			crcpaid.addSelectionListener(new CRListener());
		}
		{
			crcbilling = new Button(canvasCRCreate, SWT.RADIO | SWT.LEFT);
			crcbilling.setText("Billing");
			crcbilling.setBounds(114, 4, 60, 17);
			crcbilling.addSelectionListener(new CRListener());
		}
		{
			txtcdate = new Text(canvasCRCreate, SWT.BORDER);
			txtcdate.setBounds(869, 4, 71, 22);
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date currentDate = new Date();
			String date = dateFormat.format(currentDate);
			if (SERVER_DATE != null) {
				txtcdate.setText(SERVER_DATE);
			} else {
				txtcdate.setText(date);
			}
			txtcdate.setEnabled(false);
		}
		{
			lblcdate = new Label(canvasCRCreate, SWT.NONE);
			lblcdate.setText("Date");
			lblcdate.setBounds(833, 6, 39, 16);
		}
		try {
			if (!BeanUtil.getInstance().isSecondThread()
					|| BeanUtil.getInstance().isDiffStation()) {
				btncsubmit = new Button(canvasCRCreate, SWT.NONE);
				btncsubmit.setText("Submit");
				btncsubmit.setBounds(845, 401, 82, 26);
				btncsubmit.addSelectionListener(new CRListener());
			}
		} catch (Exception e) {

		}
		return canvasCRCreate;

	}

	@SuppressWarnings("deprecation")
	private Canvas getCRPrintCanvas() {
		canvasCRPrint = new Canvas(crTab, SWT.BORDER);
		canvasCRPrint.setSize(995, 493);
		{
			tblCRPrint = new Table(canvasCRPrint, SWT.FULL_SELECTION
					| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER | SWT.CHECK);
			tblCRPrint.setBounds(12, 33, 930, 378);
			tblCRPrint.setHeaderVisible(true);
			tblCRPrint.setLinesVisible(true);

			{
				tblpsno = new TableColumn(tblCRPrint, SWT.NONE);
				tblpsno.setText("S No");
				tblpsno.setWidth(47);
			}
			{
				tblprfrom = new TableColumn(tblCRPrint, SWT.NONE);
				tblprfrom.setText("Received From");
				tblprfrom.setWidth(93);
			}

			{
				tblplrno = new TableColumn(tblCRPrint, SWT.NONE);
				tblplrno.setText("Lr No");
				tblplrno.setWidth(55);
			}
			{
				tblplrdate = new TableColumn(tblCRPrint, SWT.NONE);
				tblplrdate.setText("LR Date");
				tblplrdate.setWidth(70);
			}
			{
				tblprintcrno = new TableColumn(tblCRPrint, SWT.NONE);
				tblprintcrno.setText("CrNo");
				tblprintcrno.setWidth(60);
			}

			{
				tblpsumofrs = new TableColumn(tblCRPrint, SWT.NONE);
				tblpsumofrs.setText("Total");
				tblpsumofrs.setWidth(59);
			}
			{
				tblrfrom = new TableColumn(tblCRPrint, SWT.NONE);
				tblrfrom.setText("From");
				tblrfrom.setWidth(46);
			}
			{
				tblpto = new TableColumn(tblCRPrint, SWT.NONE);
				tblpto.setText("To");
				tblpto.setWidth(36);
			}
			{
				tblpnoa = new TableColumn(tblCRPrint, SWT.NONE);
				tblpnoa.setText("NOA");
				tblpnoa.setWidth(45);
			}
			{
				tblpweight = new TableColumn(tblCRPrint, SWT.NONE);
				tblpweight.setText("Weight");
				tblpweight.setWidth(54);
			}

			{
				tblpfreight = new TableColumn(tblCRPrint, SWT.NONE);
				tblpfreight.setText("Freight");
				tblpfreight.setWidth(49);
			}
			{
				tblpddc = new TableColumn(tblCRPrint, SWT.NONE);
				tblpddc.setText("DDC");
				tblpddc.setWidth(43);
			}
			{
				tblpddextra = new TableColumn(tblCRPrint, SWT.NONE);
				tblpddextra.setText("DD Extra");
				tblpddextra.setWidth(49);
			}
			{
				tblpothers = new TableColumn(tblCRPrint, SWT.NONE);
				tblpothers.setText("Others");
				tblpothers.setWidth(49);
			}
			{
				tblpdemurrage = new TableColumn(tblCRPrint, SWT.NONE);
				tblpdemurrage.setText("Demurrage");
				tblpdemurrage.setWidth(49);
			}
			{
				tblpundercharge = new TableColumn(tblCRPrint, SWT.NONE);
				tblpundercharge.setText("Under Charge");
				tblpundercharge.setWidth(49);
			}
			{
				tblpptc = new TableColumn(tblCRPrint, SWT.NONE);
				tblpptc.setText("P & T Charges");
				tblpptc.setWidth(49);
			}

		}
		{
			lblcrprintfrom = new Label(canvasCRPrint, SWT.NONE);
			lblcrprintfrom.setText("From");
			lblcrprintfrom.setBounds(805, 9, 29, 15);
		}

		{
			txtcrprintfrom = new Text(canvasCRPrint, SWT.BORDER);
			txtcrprintfrom.setBounds(838, 6, 68, 20);
			txtcrprintfrom.setEditable(false);
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date currentDate = new java.util.Date();
			String date = dateFormat.format(currentDate);
			if (SERVER_DATE != null) {
				txtcrprintfrom.setText(SERVER_DATE);
			} else {
				txtcrprintfrom.setText(date);
			}
		}

		{
			btncrprintfrom = new Button(canvasCRPrint, SWT.PUSH | SWT.CENTER);
			btncrprintfrom.setBounds(908, 4, 31, 23);
			btncrprintfrom.setImage(SWTResourceManager
					.getImage("hm/akr/resources/Calendar.jpg"));
			btncrprintfrom.addSelectionListener(new CRListener());
		}

		{
			btncrprint = new Button(canvasCRPrint, SWT.PUSH | SWT.CENTER);
			btncrprint.setText("Print");
			btncrprint.setBounds(848, 416, 60, 25);
			btncrprint.addSelectionListener(new CRListener());
		}
		try {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");			
			Date fromdate = dateFormat.parse(txtcrprintfrom.getText());
			Date curDate = new Date();			
			int m1 = fromdate.getYear() * 12 + fromdate.getMonth();
	        int m2 = curDate.getYear() * 12 + curDate.getMonth();
	        int monthDiff = m2 - m1;
	        if(monthDiff > 3){
	        	cashdto = handler.getCRDetailsHistory(handler.getStationCode(), fromdate,
						fromdate);
	        }else{
	        	if(BeanUtil.getDbName() == null || BeanUtil.getDbName().equals("")){
	        		cashdto = handler.getCRDetails(handler.getStationCode(), fromdate,
							fromdate);
	        	}	        	
	        }
			
			
			if (null != cashdto) {
				populateCRPrintDetails(cashdto);
			}
		} catch (BusinessException business) {
			business.printStackTrace();
			displayError(business.getResponseMessage());
		} catch (Exception exception) {
			exception.printStackTrace();
			displayError("Error occurred while retrieving the records");
		}
		return canvasCRPrint;

	}

	/**
	 * 
	 * @return
	 */
	private CashPrinterDTO[] buildCashPrinterDTO() {
		CashPrinterDTO cashdto = null;
		ArrayList<CashPrinterDTO> list = new ArrayList<CashPrinterDTO>();
		if (null != tblCRPrint) {
			TableItem[] items = tblCRPrint.getItems();
			int len = items.length;
			for (int i = 0; i < len; i++) {
				if (items[i].getChecked()) {
					cashdto = new CashPrinterDTO();
					cashdto.setLr_no(items[i].getText(2));
					cashdto.setCr_no(items[i].getText(4));
					cashdto.setStation_code(txtStaCode.getText());
					cashdto.setConsignee_name(items[i].getText(1));

					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					if (SERVER_DATE != null) {
						cashdto.setDate(SERVER_DATE);

					} else {
						cashdto.setDate(date);
					}
					cashdto.setDd_charges(items[i].getText(11));
					cashdto.setDd_extra(items[i].getText(12));
					cashdto.setDemurrage(items[i].getText(14));
					cashdto.setFreight(items[i].getText(10));
					cashdto.setFrom(items[i].getText(6));
					cashdto.setTo(items[i].getText(7));
					cashdto.setLr_date(items[i].getText(3));
					cashdto.setNo_of_Articles(items[i].getText(8));
					cashdto.setOthers(items[i].getText(13));
					cashdto.setPostage(items[i].getText(16));
					cashdto.setStation_name(txtStaName.getText());
					cashdto.setUnder_charge(items[i].getText(15));
					cashdto.setWeight(items[i].getText(9));
					cashdto.setTotal_amount(items[i].getText(5));
					list.add(cashdto);
				}
			}
		}
		int size = list.size();
		if (size > 0) {
			return list.toArray(new CashPrinterDTO[size]);
		}
		return null;
	}

	/**
	 * Method to build Cash DTO for registering cash
	 * 
	 * @return
	 */
	private CashDTO buildDTO() throws Exception {
		CashDTO cashdto = new CashDTO();
		DateFormat form = new SimpleDateFormat("dd-MM-yyyy");
		String temp = null;

		cashdto.setLr_no(txtLrNo.getText());
		cashdto.setCr_no(txtCr.getText());

		cashdto.setStation_code(txtStaCode.getText());

		temp = txtTotal.getText();
		if (temp.length() > 0)
			cashdto.setTotal_amount(Float.parseFloat(temp));

		temp = txtDemu.getText();
		if (temp.length() > 0)
			cashdto.setDemurrage(Float.parseFloat(temp));

		temp = txtPt.getText();
		if (temp.length() > 0)
			cashdto.setPostage(Float.parseFloat(temp));

		temp = txtUnder.getText();
		if (temp.length() > 0)
			cashdto.setUnder_charge(Float.parseFloat(temp));

		temp = txtDdExtra.getText();
		if (temp.length() > 0)
			cashdto.setDd_extra(Float.parseFloat(temp));

		temp = txtOthers.getText();
		if (temp.length() > 0) {
			cashdto.setOther(Float.parseFloat(temp));
		}
		temp = txtRec.getText();
		if (temp.length() > 0) {
			cashdto.setConsigneeName(temp);
		}
		temp = txtConsignorName.getText();
		if (temp.length() > 0) {
			cashdto.setConsignorName(temp);
		}
		temp = txtLrdate.getText();
		if (temp.length() > 0) {
			cashdto.setLr_date(form.parse(temp));
		}

		temp = txtDate.getText();
		if (temp.length() > 0) {
			cashdto.setDate(form.parse(temp));
		}

		temp = txtTo.getText();
		if (temp.length() > 0) {
			cashdto.setTo_station(temp);
		}

		temp = txtCnorPhone.getText();
		if (temp.length() > 0) {
			cashdto.setCnor_phone(temp);
		}

		temp = txtCneePhone.getText();
		if (temp.length() > 0) {
			cashdto.setCnee_phone(temp);
		}

		return cashdto;
	}

	/**
	 * Method to validate the CR Number 1. The first character should be
	 * uppercase and should not be digit 2. The rest of the characters should be
	 * digit 3. The second character should not be '0'
	 */
	public boolean isValidCRNumberFormat() {
		String checklr = txtCr.getText();
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
					else
						txtCr.setText(checklr);
				}
			}
		}

		return isValid;
	}

	/**
	 * Method to populate LR Details
	 * 
	 * @param lrno
	 */
	private void populateLRDetails(String lrno, int selectedIndex) {
		BookingDTO bookingdto = new BookingDTO();
		String empty = "";

		try {
			bookingdto = handler.getDetails(lrno, handler.getStationCode());

			if (null != bookingdto) {
				txtFrom.setText(bookingdto.getFrom());
				txtTo.setText(bookingdto.getTo());
				txtArticles.setText(Integer.toString(bookingdto
						.getNo_of_articles()));
				txtLrNo.setText(bookingdto.getLrno());
				txtWeight
						.setText(Float.toString(bookingdto.getActual_weight()));
				DateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");

				txtLrdate.setText(dateformat.format(bookingdto.getDate()));
				txtFreight.setText(Float.toString(bookingdto.getTotal()
						- bookingdto.getDdc()));
				txtDd.setText(Float.toString(bookingdto.getDdc()));

				// Only for the TOPAY LR Type, the total needs to be added.
				if (selectedIndex == 0) {
					txtTotal.setText(Float.toString(bookingdto.getTotal()));
					txtSum.setText(Float.toString(bookingdto.getTotal()));
					globalTotal = bookingdto.getTotal();
				} else {
					globalTotal = 0;
					txtTotal.setText("0.0");
					txtSum.setText("0.0");
				}

				txtRec.setText(bookingdto.getConsigneeName());
				txtDemu.setText(empty);
				txtPt.setText(empty);
				txtUnder.setText(empty);
				txtDdExtra.setText(empty);
			}

		} catch (Exception exception) {
			displayError("Error occurred while populating the LR");
		}

	}

	/**
	 * Method is used for Populating cash Details
	 * 
	 * @param type
	 * @param refresh
	 */
	private void populateCashDetails(String type, boolean refresh) {
		try {

			final DecimalFormat decimalFormat = new DecimalFormat("0.00");

			if (type.equalsIgnoreCase("topay")) {
				if (null == topay || refresh) {
					topay = null;
					topaylist = null;
					topay = handler.getLRList(type);
					
					
				}
				if (null != topay ) {
					int length = topay.length;
					if (null == topaylist && length > 0) {
						topaylist = new ArrayList<CashRegisterDTO>();
						for (int j = 0; j < length; j++) {
							topaylist.add(topay[j]);

						}
					}
				}
			} else if (type.equalsIgnoreCase("paid")) {
				if (null == paid || refresh) {
					paid = null;
					paidlist = null;
					paid = handler.getLRList(type);
				
				}
				if (null != paid) {
					int length = paid.length;
					if (null == paidlist && length > 0) {
						paidlist = new ArrayList<CashRegisterDTO>();
						for (int j = 0; j < length; j++) {
							paidlist.add(paid[j]);

						}
					}
				}
			} else if (type.equalsIgnoreCase("billing")) {
				if (null == billing || refresh) {
					billing = null;
					billinglist = null;
					billing = handler.getLRList(type);
				}
				if (null != billing) {
					int length = billing.length;					
					if (null == billinglist && length > 0) {
						billinglist = new ArrayList<CashRegisterDTO>();
						for (int j = 0; j < length; j++) {
							billinglist.add(billing[j]);

						}
					}
				}
			}

			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			if (null != tblCRCreate) {
				tblCRCreate.removeAll();
				if (null != cashuidto) {
					int lenth = cashuidto.length;
					for (int l = 0; l < lenth; l++) {
						cashuidto[l].getTxtothers().dispose();
						cashuidto[l].getTxtdemurrage().dispose();
						cashuidto[l].getTxtextra().dispose();
						cashuidto[l].getTxtptc().dispose();
						cashuidto[l].getTxtundercharge().dispose();
						cashuidto[l].getTxtconsignee().dispose();
						cashuidto[l].getTxthidden().dispose();
					}
				}

				if (type.equalsIgnoreCase("topay")) {
					cashlist = topaylist;
				} else if (type.equalsIgnoreCase("paid")) {
					cashlist = paidlist;
				} else if (type.equalsIgnoreCase("billing")) {
					cashlist = billinglist;
				}

				if (cashlist != null && cashlist.size() > 0) {
					final Text[] txtothers;
					final Text[] txtddextra;
					final Text[] txtconsignee;
					final Text[] txtdemurage;
					final Text[] txtuc;
					final Text[] txtptc;
					final Text[] txthidden;
					int len = cashlist.size();
					txtothers = new Text[len];
					txtddextra = new Text[len];
					txtconsignee = new Text[len];
					txtdemurage = new Text[len];
					txtuc = new Text[len];
					txtptc = new Text[len];
					txthidden = new Text[len];
					cashuiinfo = new ArrayList<CashRegisterUI>();
					String lrno = null;
					String inwarddate = null;
					int noa = 0;
					int weight = 0;
					float lrtotal = 0;
					int freight = 0;
					int ddc = 0;
					for (int i = 0; i < len; i++) {
						CashRegisterDTO dto = cashlist.get(i);
						final int j = i;
						final TableItem item = new TableItem(tblCRCreate,
								SWT.NONE);
						item.setText(0, String.valueOf(i + 1));
						lrno = dto.getLrno();
						int a = lrno.indexOf("|");
						inwarddate = lrno.substring(a + 1);
						inwarddate = inwarddate.trim();
						lrno = lrno.substring(0, a);
						item.setText(1, lrno.trim());
						item.setText(3, inwarddate);
						//
						item.setText(4, dto.getCreatedby());
						item.setText(7, dto.getConsigneeName());
						item.setText(2, dateFormat.format((dto.getDate())));
						item.setText(5, dto.getFrom());
						item.setText(6, dto.getTo());
						item
								.setText(8, String.valueOf(dto
										.getNo_of_articles()));

						noa = noa + dto.getNo_of_articles();

						item.setText(9, String.valueOf((int) dto
								.getCharged_weight()));

						weight = weight + (int) dto.getCharged_weight();
						freight = freight
								+ (int) (dto.getTotal() - dto.getDdc());
						item
								.setText(11, String.valueOf((int) (dto
										.getTotal() - dto.getDdc())));

						ddc = ddc + (int) dto.getDdc();
						item.setText(12, String.valueOf((int) dto.getDdc()));
						/* Dynamic UI STRATS */

						uidto = new CashRegisterUI();

						editor = new TableEditor(tblCRCreate);
						editor.grabHorizontal = true;
						txtconsignee[i] = new Text(tblCRCreate, SWT.NONE);
						txtconsignee[i].setText(dto.getConsigneeName());
						editor.setEditor(txtconsignee[i], item, 7);
						uidto.setTxtconsignee(txtconsignee[i]);

						editor = new TableEditor(tblCRCreate);
						editor.grabHorizontal = true;
						txtddextra[i] = new Text(tblCRCreate, SWT.NONE);
						txtddextra[i].setText("0");
						editor.setEditor(txtddextra[i], item, 13);
						uidto.setTxtextra(txtddextra[i]);
						txtddextra[i]
								.addVerifyListener(new NumericValidation());

						editor = new TableEditor(tblCRCreate);
						editor.grabHorizontal = true;
						txtothers[i] = new Text(tblCRCreate, SWT.NONE);
						txtothers[i].setText("0");
						editor.setEditor(txtothers[i], item, 14);
						uidto.setTxtothers(txtothers[i]);
						txtothers[i].addVerifyListener(new NumericValidation());

						editor = new TableEditor(tblCRCreate);
						editor.grabHorizontal = true;
						txtdemurage[i] = new Text(tblCRCreate, SWT.NONE);
						txtdemurage[i].setText("0");
						editor.setEditor(txtdemurage[i], item, 15);
						uidto.setTxtdemurrage(txtdemurage[i]);
						txtdemurage[i]
								.addVerifyListener(new FloatLimitValidation());

						editor = new TableEditor(tblCRCreate);
						editor.grabHorizontal = true;
						txtuc[i] = new Text(tblCRCreate, SWT.NONE);
						txtuc[i].setText("0");
						editor.setEditor(txtuc[i], item, 16);
						uidto.setTxtundercharge(txtuc[i]);
						txtuc[i].addVerifyListener(new FloatLimitValidation());

						editor = new TableEditor(tblCRCreate);
						editor.grabHorizontal = true;
						txtptc[i] = new Text(tblCRCreate, SWT.NONE);
						txtptc[i].setText("0");
						editor.setEditor(txtptc[i], item, 17);
						uidto.setTxtptc(txtptc[i]);
						txtptc[i].addVerifyListener(new FloatLimitValidation());

						// cashuiinfo.add(uidto);

						if (type.equalsIgnoreCase("TOPAY")) {
							item.setText(18, decimalFormat.format(dto
									.getTotal()));
							item.setText(10, decimalFormat
									.format(dto.getTotal()));
							txthidden[i] = new Text(tblCRCreate, SWT.NONE);
							txthidden[i].setText(decimalFormat.format(dto
									.getTotal()));

						} else {
							item.setText(18, "0");
							item.setText(10, "0");
							txthidden[i] = new Text(tblCRCreate, SWT.NONE);
							txthidden[i].setText(String.valueOf(0));
						}
						item.setText(19, dto.getFromMobile());
						item.setText(20, dto.getToMobile());
						item.setText(21, dto.getConsignorName());
						item.setText(22, String.valueOf(dto.getRate_type()));
						item.setText(23, String.valueOf(dto.getSms()));
						item
								.setText(24, String.valueOf(dto
										.getActual_weight()));
						

						/*
						 * int rate_type = dto.getRate_type(); if (rate_type ==
						 * 5 || rate_type == 4) {
						 * txtddextra[i].setEnabled(false);
						 * txtothers[i].setEnabled(false);
						 * txtdemurage[i].setEnabled(false);
						 * txtuc[i].setEnabled(false);
						 * txtptc[i].setEnabled(false);
						 * txtconsignee[i].setEnabled(false); }
						 */

						lrtotal = lrtotal + Float.parseFloat(item.getText(10));

						class tableListener implements FocusListener {

							@Override
							public void focusGained(FocusEvent arg0) {

							}

							@Override
							public void focusLost(FocusEvent event) {

								Object source = event.getSource();
								if (source == txtothers[j]
										|| source == txtddextra[j]
										|| source == txtdemurage[j]
										|| source == txtuc[j]
										|| source == txtptc[j]) {

									float total = Float.parseFloat(txthidden[j]
											.getText());

									String temp = null;

									temp = txtothers[j].getText();
									if (temp.length() > 0)
										total += Float.parseFloat(temp);

									temp = txtddextra[j].getText();
									if (temp.length() > 0)
										total += Float.parseFloat(temp);

									temp = txtdemurage[j].getText();
									if (temp.length() > 0)
										total += Float.parseFloat(temp);

									temp = txtuc[j].getText();
									if (temp.length() > 0)
										total += Float.parseFloat(temp);

									temp = txtptc[j].getText();
									if (temp.length() > 0)
										total += Float.parseFloat(temp);

									item.setText(18, decimalFormat
											.format(total));

								}
							}

						}
						uidto.setTxthidden(txthidden[i]);
						cashuiinfo.add(uidto);
						txtothers[i].addFocusListener(new tableListener());
						txtddextra[i].addFocusListener(new tableListener());
						txtdemurage[i].addFocusListener(new tableListener());
						txtuc[i].addFocusListener(new tableListener());
						txtptc[i].addFocusListener(new tableListener());

					}

					final TableItem totalItem = new TableItem(tblCRCreate,
							SWT.NONE);
					Font font1 = new Font(display, "Tahoma", 8, SWT.BOLD);
					totalItem.setFont(font1);
					totalItem.setText(1, "TOTAL");

					totalItem.setText(8, String.valueOf(noa));
					totalItem.setText(9, String.valueOf(weight));
					totalItem.setText(10, decimalFormat.format(lrtotal));
					totalItem.setText(11, String.valueOf(freight));
					totalItem.setText(12, String.valueOf(ddc));

					int size = cashuiinfo.size();
					if (size > 0) {
						cashuidto = cashuiinfo
								.toArray(new CashRegisterUI[size]);
					} else
						cashuidto = null;
					/**
					 * Class for Article Table Components FocusListeners
					 * 
					 * @author
					 * 
					 */

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to populate LR
	 * 
	 * @param index
	 */
	private void populateLR(int index) {

	}

	/**
	 * Inner class for Cash Register Composite
	 * 
	 * 
	 * 
	 */
	public class crvalidation implements org.eclipse.swt.events.FocusListener {

		public void focusGained(FocusEvent arg0) {

		}

		/**
		 * 
		 */
		public void focusLost(FocusEvent arg0) {
			try {
				String intputnumber = txtCr.getText();
				if (intputnumber != "" && !isValidCRNumberFormat()) {
					txtDdExtra.setFocus();

					displayError("CR Number format is not valid");

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 
	 * @param dto
	 * @throws NamingException
	 * @throws Exception
	 */
	private void populateCRPrintDetails(CashDTO[] dto) throws NamingException,
			Exception {
		if (null != dto) {
			int len = dto.length;
			int j = 0;
			DateFormat dateformate = new SimpleDateFormat("dd-MM-yyyy");
			if (null != tblCRPrint) {
				tblCRPrint.removeAll();
				BeanUtil util = BeanUtil.getInstance();
				for (int i = 0; i < len; i++) {
					if (!dto[i].isStatus()
							&& util.getActingStationCode().equalsIgnoreCase(
									dto[i].getStation_code())) {
						TableItem item = new TableItem(tblCRPrint, SWT.NONE);
						if (dto[i].isCrprint()) {
							item.setBackground(Display.getDefault()
									.getSystemColor(
											SWT.COLOR_WIDGET_LIGHT_SHADOW));
						}
						j++;
						item.setText(0, String.valueOf((j)));
						item.setText(1, String.valueOf(dto[i]
								.getConsigneeName()));

						item.setText(2, String.valueOf(dto[i].getLr_no()));
						item.setText(3, String.valueOf(dateformate
								.format(dto[i].getLr_date())));
						item.setText(4, String.valueOf(dto[i].getCr_no()));
						item.setText(5, String
								.valueOf(dto[i].getTotal_amount()));
						item.setText(6, String
								.valueOf(dto[i].getFrom_station()));
						item.setText(7, String.valueOf(dto[i].getTo_station()));
						item.setText(8, String.valueOf(dto[i]
								.getNo_of_articles()));
						item.setText(9, String.valueOf(dto[i]
								.getActual_weight()));
						item.setText(10, String.valueOf(dto[i].getLr_total()
								- dto[i].getDdc()));
						item.setText(11, String.valueOf(dto[i].getDdc()));
						item.setText(12, String.valueOf(dto[i].getDd_extra()));
						item.setText(13, String.valueOf(dto[i].getOther()));
						item.setText(14, String.valueOf(dto[i].getDemurrage()));
						item.setText(15, String.valueOf(dto[i]
								.getUnder_charge()));
						item.setText(16, String.valueOf(dto[i].getPostage()));

					}

				}
			}
		} else {
			if (null != tblCRPrint) {
				tblCRPrint.removeAll();
			}
		}

	}

	/**
	 * Inner class for CR Listeners
	 * 
	 * @author
	 * 
	 */
	public class CRListener implements org.eclipse.swt.events.FocusListener,
			SelectionListener {

		public void focusGained(org.eclipse.swt.events.FocusEvent arg0) {

		}

		/**
		 * 
		 */
		public void focusLost(org.eclipse.swt.events.FocusEvent arg0) {
			float total = globalTotal;

			String temp = null;

			temp = txtOthers.getText();
			if (temp.length() > 0)
				total += Float.parseFloat(temp);

			temp = txtDdExtra.getText();
			if (temp.length() > 0)
				total += Float.parseFloat(temp);

			temp = txtDemu.getText();
			if (temp.length() > 0)
				total += Float.parseFloat(temp);

			temp = txtUnder.getText();
			if (temp.length() > 0)
				total += Float.parseFloat(temp);

			temp = txtPt.getText();
			if (temp.length() > 0)
				total += Float.parseFloat(temp);

			txtTotal.setText(String.valueOf(total));
			txtSum.setText(String.valueOf(total));

		}

		/**
		 * 
		 */
		@SuppressWarnings("deprecation")
		public void widgetSelected(SelectionEvent event) {

			Object source = event.getSource();

			if (btncrprint == source) {
				String[] crno = null;
				try {

					CashPrinterDTO[] printDTO = buildCashPrinterDTO();
					ArrayList list = new ArrayList();
					if (null != printDTO && printDTO.length > 0) {
						int len = printDTO.length;

						if (len > 0) {
							crno = new String[len];

							for (int i = 0; i < len; i++) {
								crno[i] = printDTO[i].getCr_no();
								if (null != cashdto) {
									for (int j = 0; j < cashdto.length; j++) {
										if (cashdto[j].getCr_no()
												.equalsIgnoreCase(crno[i])) {
											BookingDTO booking = buildLRDTO(cashdto[j]);

											HashMap<String, String> hashMap = new HashMap<String, String>();
											hashMap
													.put("Title",
															"CASH RECEIPT");
											hashMap.put("ArticleType",
													selectArticleType((booking
															.getArticle_id())));

											ArticleDTO[] artdto = booking
													.getArticledto();
											printarticles = null;
											printarticles_desc = null;
											if (null != artdto)
												for (int k = 0; k < artdto.length; k++) {
													if (null == printarticles)
														printarticles = artdto[k]
																.getArticleName();
													else
														printarticles = printarticles
																+ ","
																+ artdto[k]
																		.getArticleName();

													if (null == printarticles_desc)
														printarticles_desc = artdto[k]
																.getArticleDesc();
													else
														printarticles_desc = printarticles_desc
																+ ","
																+ artdto[k]
																		.getArticleDesc();
												}

											if (printarticles_desc == null) {
												printarticles_desc = "";
											}
											if (printarticles == null) {
												printarticles = "";
											}
											booking
													.setArticle_description(printarticles_desc);

											if (booking.getType()
													.equalsIgnoreCase("PAID")
													&& booking.getBft() == 0) {
												booking.setType("PAID - FOC");
											}
											

											list.add(new CashDTODecorator(
													printDTO[i], booking,
													printarticles));

										}
									}
								}
							}
							int size = list.size();
							if (size > 0) {
								handler.printCashReceipt(list);
							}

						}

						handler.updatePrintStatus(crno);

						DateFormat dateFormat = new SimpleDateFormat(
								"dd-MM-yyyy");
						Date fromdate = dateFormat.parse(txtcrprintfrom
								.getText());
						cashdto = handler.getCRDetails(
								handler.getStationCode(), fromdate, fromdate);

						populateCRPrintDetails(cashdto);
					} else {
						displayError("Please select Atleast One Record");
					}
				} catch (Exception exception) {
					exception.printStackTrace();
					displayError("Problem while printing");
				}
			}

			else if (btnNew == source) {
				clearAndEnableFields();
			} else if (btnView == source) {
				try {
					String crno = txtCr.getText();
					if (crno.length() > 0) {
						try {
							Date date = null;
							DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");							
							date = dateFormat.parse(txtDate.getText());
							CashDTO cash = handler.getCRDetails(crno, date);
							if ( cash != null && isCancellableCR(cash, true)) {
								if (null != cash) {
									
									populateCRDetails(date);
									//System.out.println("drs-->"+ cash.getIsDRSConfirmed());
									displayError("Records Retrived");
									if (crTab.getSelectionIndex() == 0)
										btnPrint.setEnabled(true);

								} else
									displayError("NO Record  Found");
							}
						} catch (BusinessException business) {
							displayError(business.getResponseMessage());
						} catch (Exception exception) {
							displayError("Error occurred while retrieving the records");
							exception.printStackTrace();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (listLrTopay == source) {
				btnPrint.setEnabled(false);
				String[] lrnos = listLrTopay.getSelection();
				if (null != lrnos && lrnos.length > 0) {
					int index = lrnos[0].indexOf("|");

					if (index >= -1) {
						lrnos[0] = lrnos[0].substring(0, index).trim();
					}

					populateLRDetails(lrnos[0], tabList.getSelectionIndex());

				}

			} else if (crTab == source) {
				if (null != canvas) {
					canvas.dispose();
					canvas = null;
				}

				canvas = getCanvas();
				int index = crTab.getSelectionIndex();
				tabItem[index].setControl(canvas);
				if (crTab.getSelectionIndex() == 0)
					populateLR(index);

			} else if (btnCancel == source) { // Cancellation
				try {
					CashDTO dto = buildDTO();
					Date curDate = new Date();
					
					int m1 = dto.getDate().getYear() * 12 + dto.getDate().getMonth();
			        int m2 = curDate.getYear() * 12 + curDate.getMonth();
			        int monthDiff = m2 - m1;
			       /* if(monthDiff > 3){
			        	displayError("Can not Cancel old records");
			        }else{*/
			        
			        if(!drsConfirmed){
			        	if (isCancellableCR(dto, false)) {			        		
							boolean cancel = false;
							cancel = handler.cancelCashRegister(dto);
							if (cancel) {
								displayError("Cancel Successful");
								sendCancelSMS(dto);
								btnCancel.setEnabled(false);
							}
						}
			        }else{
			        	displayError("Can not Cancel CR. DRS Submitted");
			        }
			        
			        //}
				} catch (BusinessException business) {
					displayError(business.getResponseMessage());
				} catch (Exception exception) {
					exception.printStackTrace();
					displayError("Error occurred while Cancelling. Please Try again");
				}
			} else if (tabList == source) {
				int index = tabList.getSelectionIndex();
				populateLR(index);
			} else if (listLrPaid == source) {
				btnPrint.setEnabled(false);
				String[] lrnos = listLrPaid.getSelection();
				if (null != lrnos && lrnos.length > 0) {
					int index = lrnos[0].indexOf("|");

					if (index >= -1) {
						lrnos[0] = lrnos[0].substring(0, index).trim();
					}
					populateLRDetails(lrnos[0], tabList.getSelectionIndex());
				}
			} else if (listLrBill == source) {
				btnPrint.setEnabled(false);
				String[] lrnos = listLrBill.getSelection();
				if (null != lrnos && lrnos.length > 0) {
					int index = lrnos[0].indexOf("|");

					if (index >= -1) {
						lrnos[0] = lrnos[0].substring(0, index).trim();
					}
					populateLRDetails(lrnos[0], tabList.getSelectionIndex());
				}

			} else if (crctopay == source) {

				populateCashDetails("TOPAY", true);

			} else if (crcpaid == source) {

				populateCashDetails("PAID", true);

			} else if (crcbilling == source) {

				populateCashDetails("BILLING", true);

			} else if (btncrprintfrom == source) {
				KalendarDialog cd = new KalendarDialog(shell);
				Object obj = cd.open();
				if (obj != null)
					txtcrprintfrom.setText(obj.toString());

				try {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Date fromdate = dateFormat.parse(txtcrprintfrom.getText());
					
					Date curDate = new Date();
					
					int m1 = fromdate.getYear() * 12 + fromdate.getMonth();
			        int m2 = curDate.getYear() * 12 + curDate.getMonth();
			        int monthDiff = m2 - m1;
			        if(monthDiff > 3){
			        	cashdto = handler.getCRDetailsHistory(handler.getStationCode(), fromdate,
								fromdate);
			        }else{
			        	cashdto = handler.getCRDetails(handler.getStationCode(), fromdate,
								fromdate);
			        }

					populateCRPrintDetails(cashdto);

				} catch (BusinessException business) {
					business.printStackTrace();
					displayError(business.getResponseMessage());
				} catch (Exception exception) {
					exception.printStackTrace();
					displayError("Error occurred while retrieving the records");
				}

			}

			else if (btncsubmit == source) {
				try {
					CashDTO[] dto = buildCashDTO();
					if (null != dto && dto.length > 0) {

						// update cr
						handler.updateCashReceipt(dto);
						
						int len = dto.length;

						if (crctopay.getSelection()) {
							for (int i = 0; i < len; i++) {
								String temp = null;
								int size = topaylist.size();
								for (int j = 0; j < size; j++) {
									temp = topaylist.get(j).getLrno();
									int index = temp.indexOf(" | ");
									temp = temp.substring(0, index);
									if (temp
											.equalsIgnoreCase(dto[i].getLr_no())) {
										topaylist.remove(j);
										break;
									}
								}

							}
							populateCashDetails("TOPAY", true);
						} else if (crcpaid.getSelection()) {
							for (int i = 0; i < len; i++) {
								String temp = null;
								int size = paidlist.size();
								for (int j = 0; j < size; j++) {
									temp = paidlist.get(j).getLrno();
									int index = temp.indexOf(" | ");
									temp = temp.substring(0, index);
									if (temp
											.equalsIgnoreCase(dto[i].getLr_no())) {
										paidlist.remove(j);
										break;
									}
								}

							}
							populateCashDetails("PAID", true);
						} else if (crcbilling.getSelection()) {
							for (int i = 0; i < len; i++) {
								String temp = null;
								int size = billinglist.size();
								for (int j = 0; j < size; j++) {
									temp = billinglist.get(j).getLrno();
									int index = temp.indexOf(" | ");
									temp = temp.substring(0, index);
									if (temp
											.equalsIgnoreCase(dto[i].getLr_no())) {
										billinglist.remove(j);
										break;
									}
								}

							}
							populateCashDetails("BILLING", true);
						}

					} else {
						displayError("Please Select Atleast One Record");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		/**
		 * 
		 * @param dto
		 * @return
		 */
		private boolean isCancellableCR(CashDTO dto, boolean isView) {
			if (null != dto) {
				String stationCode = dto.getStation_code();
								
				if (stationCode.equalsIgnoreCase(handler.getStationCode())) {
					if (!isView && Hour.getDateDifference(dto.getDate()) < 2) {
						return true;
					} else if (!isView) {
						displayError("Old Crs cannot be Cancelled");
						return false;
					}
					return true;
				} else {
					displayError("Other Station Crs cannot be viewed");
				}

			}
			return false;
		}

		/**
		 * 
		 * @param dto
		 */
		private void sendCancelSMS(CashDTO dto) {

			SMSDTO temp = null;
			DateFormat smsFormat = new SimpleDateFormat("dd/MMM/yy");
			try {
				String ConsignorMsg = null;
				String ConsigneeMsg = null;

				ConsignorMsg = "Greetings from AKR!The delivery for LR "
						+ dto.getLr_no() + " dt "
						+ smsFormat.format(dto.getLr_date()) + " to "
						+ getTruncatedName(dto.getConsigneeName(), 20)
						+ " was cancelled at " + Hour.getHourOnly()
						+ ".Kindly contact  " + getPhoneNumber(currStn.trim())
						+ " for more details.";

				ConsigneeMsg = "Greetings from AKR!The delivery for LR "
						+ dto.getLr_no() + " dt "
						+ smsFormat.format(dto.getLr_date()) + " from "
						+ getTruncatedName(dto.getConsignorName(), 20)
						+ " was cancelled at " + Hour.getHourOnly()
						+ ".Kindly contact " + getPhoneNumber(currStn.trim())
						+ " for more details.";

				/*
				 * if (null != ConsignorMsg) { sendSMS(dto.getCnor_phone(),
				 * ConsignorMsg); } if (null != ConsigneeMsg) {
				 * sendSMS(dto.getCnee_phone(), ConsigneeMsg); }
				 */

				boolean isUnTime = isUntime();
				//boolean isUnTime = false;
				if (isUnTime == false) {
					if (null != ConsignorMsg) {
						sendSMS(dto.getCnor_phone(), ConsignorMsg);
					}
					if (null != ConsigneeMsg) {
						sendSMS(dto.getCnee_phone(), ConsigneeMsg);
					}
				} else {
					temp = new SMSDTO();
					temp.setCnor_phone(dto.getCnor_phone());
					temp.setCnor_message(ConsignorMsg);
					temp.setCnee_phone(dto.getCnee_phone());
					temp.setCnee_message(ConsigneeMsg);
					SMSDTO[] sms = new SMSDTO[1];
					sms[0] = temp;
					handler.storeSMS(sms);

				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		private String selectArticleType(int id) {/*
													 * try { ArticleDTO[]
													 * articles =
													 * lrhandler.getArticleTypes();
													 * 
													 * if (null != articles) {
													 * int len =
													 * articles.length; for (int
													 * i = 0; i < len; i++) { if
													 * (id ==
													 * articles[i].getId()) {
													 * return
													 * articles[i].getType(); } } } }
													 * catch (Exception
													 * exception) {
													 * exception.printStackTrace(); }
													 */
			return null;
		}

		/**
		 * 
		 * @param cashDTO
		 * @return
		 */
		private BookingDTO buildLRDTO(CashDTO cashDTO) {

			BookingDTO dto = new BookingDTO();
			try {
				dto.setLrno(cashDTO.getLr_no());
				dto.setFrom(cashDTO.getFrom_station());
				dto.setTo(cashDTO.getTo_station());
				dto.setActual_weight(cashDTO.getActual_weight());
				dto.setDdc(cashDTO.getDdc());
				
				dto.setConsigneeName(cashDTO.getConsigneeName());
				dto.setNo_of_articles((int) (cashDTO.getNo_of_articles()));

				StationsDTO[] stations = lrhandler.getStations();

				int size = stations.length;

				String fromStation = dto.getFrom();
				String toStation = dto.getTo();

				for (int i = 0; i < size; i++) {
					boolean from = false;

					boolean to = false;

					if (stations[i].getId().equalsIgnoreCase(fromStation)) {
						dto.setFromMobile(stations[i].getMobile() + "  "
								+ stations[i].getPhone1());
						from = true;
						dto
								.setFrom(stations[i].getName() + " - "
										+ fromStation);
					} else if (stations[i].getId().equalsIgnoreCase(toStation)) {
						dto.setToMobile(stations[i].getMobile() + "  "
								+ stations[i].getPhone1());
						to = true;
						dto.setTo(stations[i].getName() + " - " + toStation);
					}
					if (to && from)
						break;
				}

				CashRegisterDTO cash = cashDTO.getCashdto();
				dto.setArticle_description(cash.getArticle_description());
				dto.setBft(cash.getBft());
				dto.setType(cash.getType());
				dto.setConsignor_address(cash.getConsignor_address());
				dto.setConsignee_address(cash.getConsignee_address());
				dto.setConsignorName(cash.getConsignorName());
				dto.setConsignee_GST(cash.getConsignee_GST());
				dto.setConsignor_CST(cash.getConsignor_CST());
				dto.setArticle_id(cash.getArticle_id());
				dto.setArticle_value(cash.getArticle_value());
				dto.setCharged_weight(cash.getCharged_weight());
				dto.setBft(cash.getBft());
				dto.setLrc(cash.getLrc());
				dto.setDhc(cash.getDhc());
				dto.setCcc(cash.getCcc());
				dto.setDcc(cash.getDcc());
				dto.setIec(cash.getIec());
				dto.setArticledto(cash.getArticledto());
				dto.setLc(cash.getLc());
				dto.setGsc(cash.getGsc());
				dto.setStax(cash.getStax());
				dto.setOther_charges(cash.getOther_charges());
				dto.setTotal(cashDTO.getLr_total());

				dto.setStatus(cash.getStatus());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return dto;
		}

		/**
		 * Method to populate CR Details
		 */
		private void populateCRDetails(Date date) {
			String crno = txtCr.getText();
			CashDTO dto = null;

			if (crno.length() > 0) {
				try {
					dto = handler.getCRDetails(crno, date);
					if (null != dto) {
						DateFormat dateformate = new SimpleDateFormat(
								"dd-MM-yyyy");
						txtArticles.setText(Float.toString(dto
								.getNo_of_articles()));
						txtDate.setText(dateformate.format(dto.getDate()));
						txtDd.setText(Float.toString(dto.getDdc()));
						txtDd.setEnabled(false);
						txtDdExtra.setText(Float.toString(dto.getDd_extra()));
						txtDdExtra.setEnabled(false);
						txtDemu.setText(Float.toString(dto.getDemurrage()));
						txtDemu.setEnabled(false);
						txtUnder.setText(Float.toString(dto.getUnder_charge()));
						txtUnder.setEnabled(false);
						txtPt.setText(Float.toString(dto.getPostage()));
						txtPt.setEnabled(false);
						txtCr.setEnabled(false);
						txtFreight.setText(Float.toString(dto.getBft()));
						txtLrNo.setEnabled(false);
						txtFrom.setText(dto.getFrom_station());
						txtLrdate.setText(dateformate.format(dto.getLr_date()));
						txtWeight.setText(Float
								.toString(dto.getActual_weight()));
						txtTo.setText(dto.getTo_station());
						txtTotal.setText(Float.toString(dto.getTotal_amount()));
						txtStaCode.setText(dto.getStation_code());
						txtOthers.setText(String.valueOf(dto.getOther()));
						txtLrNo.setText(dto.getLr_no());
						txtSum.setText(Float.toString(dto.getTotal_amount()));
						txtRec.setText(dto.getConsigneeName());
						txtConsignorName.setText(dto.getConsignorName());
						txtCnorPhone.setText(dto.getCnor_phone());
						txtCneePhone.setText(dto.getCnee_phone());
						txtOthers.setEnabled(false);
						if (dto.isStatus()) {
							btnCancel.setEnabled(false);
							displayError("Already Cancelled");
						} else if (!dto.isStatus()){
							btnCancel.setEnabled(true);
						}
						
						if(dto.getIsDRSConfirmed() == 1){
							drsConfirmed = true;
						}else{
							drsConfirmed = false;
						}
					}
				} catch (Exception exception) {
					exception.printStackTrace();
					displayError("Error occurred while retrieving the CR");
				}
			}

		}

		/**
		 * Method to Clearing and Enabling the Components
		 */
		private void clearAndEnableFields() {
			String empty = "";
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date currentDate = new java.util.Date();
			txtDate.setText(dateFormat.format(currentDate));
			txtCr.setText(empty);
			txtCr.setEnabled(true);
			txtArticles.setText(empty);
			txtDd.setText(empty);
			txtDdExtra.setText(empty);

			txtDemu.setText(empty);

			txtFreight.setText(empty);
			txtFrom.setText(empty);
			txtLrdate.setText(empty);
			txtOthers.setText(empty);
			txtPt.setText(empty);
			txtRec.setText(empty);
			txtSum.setText(empty);
			txtTo.setText(empty);
			txtTotal.setText(empty);
			txtWeight.setText(empty);
			txtUnder.setText(empty);
			btnCancel.setEnabled(false);
			txtLrNo.setText(empty);

		}

		/**
		 * 
		 */
		public void widgetDefaultSelected(SelectionEvent arg0) {

		}
	}
}
