package hm.akr.admin.commission;

/**
 * 
 */

import hm.akr.admin.commission.handler.AgencyCommisionHandler;
import hm.akr.admin.workspace.AdminComposite;
import hm.akr.admin.workspace.SWTResourceManager;
import hm.akr.common.BeanUtil;
import hm.akr.common.FloatLimitValidation;
import hm.akr.common.IUIConstants;
import hm.akr.common.KalendarDialog;
import hm.akr.common.NumericValidation;
import hm.akr.dto.CustomerDTO;
import hm.akr.msb.TreeComposite;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
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
 * @author
 * 
 */
public class CommissionComposite extends Composite implements IUIConstants {

	private TabFolder tbfSundry;

	private TabItem tbiBooking;

	private TabItem tbiDelivery;

	private TabItem tbiRecovery;

	private TabItem tbiRefund;

	Composite treeComposite;

	TreeComposite tree;

	private Label lblName;

	private Combo cbProfile;

	private Label lblHeader;

	private static final String BOOKING_TAB_NAME = "Booking";

	private static final String DELIVERY_TAB_NAME = "Delivery";

	private static final String RECOVERY_TAB_NAME = "Recovery";

	private static final String REFUND_TAB_NAME = "Refund";

	private static final String EMPTY_STRING = "";

	private static final String CC_NORMS_TAB_NAME = "CC Norms";

	private static final String MANAGE_COMMISSION = "Manage Commission";

	private Label lblContact;

	private Button btnSet;

	private Label lblDescription;

	private Text txtAreaDescription;

	private Label lblAmount;

	private Text txtAmount;

	private Label lblInstallments;

	private Text txtInstallments;

	private Button rdAutomatic;

	private Button rdManual;

	private Table tblInstallment;

	private TableColumn column;

	private int ROW_SIZE = 4;

	private int TBL_HEIGHT = (ROW_SIZE * 14) + 20;

	private TableEditor editor;

	private TableItem item;

	Label[] lblInst = null;

	Composite cptinstallment = null;

	Text[] text = null;

	private Label lblCCLimit;

	private Button btnCommisionSet;

	private Button btnDelivery;

	private Button btnrecoveryrefund;

	private static String[] COLUMN_HEADINGS = { "Installment", "Amount" };

	AgencyCommisionHandler handler = null;

	Composite cptRefund = null;

	private Text txtAreaContact;

	private TabItem tbiCCNorms;

	private Text txtHint;

	private Label lblDate;

	private Text txtDate;

	private Button btnGo;

	private String SERVER_DATE = null;

	private TabItem tbiManageCommission;

	String[] comboNames = null;

	String[] comboDCNames = null;

	private TabFolder tbfSub;

	private TabItem tbiSubCCCommission;

	private Button rdCCEnable;

	private Button rdCCDisable;

	private Button btnCCSpecial;

	private Composite cptCCSpltree;

	private String CC_FOR_COMM = "CC Commodity";

	private String HLC = "HLC";

	private String CC_COMMISSION = "CC Commission";

	private String CC_SPECIAL = "CC Special";

	private Composite treeCCCommodity;

	private Button btnCCCommodity;

	private Button rdCCCommodityEnable;

	private Button rdCCCommodityDisable;

	private TabItem tbiSubHLC;

	private Composite treeHLC;

	private Label lblCustomer;

	private static Combo cbCustomers;

	private Label lblCCArticle;

	private Text txtccArticle;

	private Label lblRefundArticle;

	private Text txtrefundArticle;

	private Button btnCCHLC;

	private Composite cptHLC;

	private Composite cptCCCommodity;

	private Composite cptCCSpecial;

	private Canvas cvsCCComm;

	private Composite treeCCCommission;

	private Composite treeBooking;

	private Composite treeDelivery;

	private Canvas cvsBooking = null;

	private Canvas cvsDelivery = null;

	private Composite cpttemp;

	private Composite cptManageSundry;

	private Canvas cvsMC;

	private Combo cbCCProfile;

	private String[] ccComboNames;

	private Combo cbDCProfile;

	private Label lblPlus;

	private Label lblMinus;

	private Label lblWarning;

	static CustomerDTO[] sundryCustomers = null;
	
	

	/**
	 * Constructor Method
	 */
	public CommissionComposite(Composite cpt, int value) {
		super(cpt, value);
		try {
			handler = new AgencyCommisionHandler();
			SERVER_DATE = handler.getServerDate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to get Installments components
	 * 
	 * @param symbol
	 * 
	 * @return
	 */
	public Composite getComposite(String symbol) {

		cpttemp = new Composite(tbfSundry, SWT.NONE);
		cpttemp.setSize(420, 420);

		try {

			{
				lblContact = new Label(cpttemp, SWT.NONE);
				lblContact.setText("Contact");
				lblContact.setBounds(12, 92, 70, 20);
				lblContact.setFont(LABLE_FONT);
			}

			{
				lblDescription = new Label(cpttemp, SWT.NONE);
				lblDescription.setText("Description");
				lblDescription.setBounds(12, 6, 138, 19);
				lblDescription.setFont(LABLE_FONT);

			}
			{
				txtAreaDescription = new Text(cpttemp, SWT.V_SCROLL
						| SWT.BORDER | SWT.WRAP);
				txtAreaDescription.setBounds(12, 28, 143, 52);
				txtAreaDescription.setFont(TEXT_FONT);
				txtAreaDescription.setTextLimit(200);
				txtAreaDescription.addTraverseListener(new TraverseListener() {
					public void keyTraversed(TraverseEvent e) {
						if (e.detail == SWT.TRAVERSE_TAB_NEXT
								|| e.detail == SWT.TRAVERSE_TAB_PREVIOUS) {
							e.doit = true;
						}
					}
				});

				txtAreaDescription.addFocusListener(new FocusListener() {

					@Override
					public void focusGained(FocusEvent arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void focusLost(FocusEvent arg0) {
						rdAutomatic.setEnabled(true);
						rdManual.setEnabled(true);

					}

				});

			}
			{

				txtAreaContact = new Text(cpttemp, SWT.V_SCROLL | SWT.BORDER
						| SWT.WRAP);
				txtAreaContact.setBounds(12, 112, 145, 56);
				txtAreaContact.setFont(TEXT_FONT);
				txtAreaContact.addTraverseListener(new TraverseListener() {
					public void keyTraversed(TraverseEvent e) {
						if (e.detail == SWT.TRAVERSE_TAB_NEXT
								|| e.detail == SWT.TRAVERSE_TAB_PREVIOUS) {
							e.doit = true;
						}
					}
				});

			}
			{
				lblAmount = new Label(cpttemp, SWT.NONE);
				lblAmount.setText("Amount");
				lblAmount.setBounds(12, 181, 143, 17);
				lblAmount.setFont(LABLE_FONT);

			}

			{
				lblPlus = new Label(cpttemp, SWT.NONE);
				lblPlus.setText("+");
				lblPlus.setBounds(0, 201, 10, 28);
				lblPlus.setFont(PLUS_FONT);

			}

			{
				lblMinus = new Label(cpttemp, SWT.NONE);
				lblMinus.setText("-");
				lblMinus.setBounds(0, 201, 10, 28);
				lblMinus.setFont(PLUS_FONT);

			}

			if (symbol.equals("+")) {
				lblPlus.setVisible(true);
				lblMinus.setVisible(false);
			} else {
				lblPlus.setVisible(false);
				lblMinus.setVisible(true);
			}

			{
				txtAmount = new Text(cpttemp, SWT.BORDER);
				txtAmount.setBounds(12, 201, 141, 29);
				txtAmount.setFont(TEXT_FONT);
				txtAmount.addVerifyListener(new FloatLimitValidation());
			}
			{
				lblInstallments = new Label(cpttemp, SWT.NONE);
				lblInstallments.setText("Installments");
				lblInstallments.setBounds(12, 243, 140, 17);
				lblInstallments.setFont(LABLE_FONT);

			}
			{
				txtInstallments = new Text(cpttemp, SWT.BORDER);
				txtInstallments.setBounds(12, 263, 109, 25);

				txtInstallments.setFont(TEXT_FONT);
				txtInstallments.addVerifyListener(new NumericValidation());
				txtInstallments.addFocusListener(new SundryTabListener());
			}
			{
				rdAutomatic = new Button(cpttemp, SWT.RADIO | SWT.LEFT);
				rdAutomatic.setText("Automatic");
				rdAutomatic.setSelection(true);
				rdAutomatic.setBounds(188, 2, 92, 19);
				rdAutomatic.addSelectionListener(new SundryTabListener());
			}

			{
				rdManual = new Button(cpttemp, SWT.RADIO | SWT.LEFT);
				rdManual.setText("Manual");
				rdManual.setBounds(286, 4, 74, 19);
				rdManual.addSelectionListener(new SundryTabListener());
			}

			drawTable();

			{
				txtHint = new Text(cpttemp, SWT.BORDER);
				txtHint.setBounds(260, 269, 115, 20);
				txtHint.setFont(TEXT_FONT);
				txtHint.setVisible(false);
				txtHint.setEnabled(false);
			}

			{
				btnrecoveryrefund = new Button(cpttemp, SWT.NONE);
				btnrecoveryrefund.setBounds(275, 289, 40, 22);
				btnrecoveryrefund.setText("Set");
				btnrecoveryrefund.setFont(BUTTON_FONT);
				btnrecoveryrefund.addSelectionListener(new SundryTabListener());
			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return cpttemp;
	}

	private void drawTable() {

		// Draw Table
		tblInstallment = new Table(cpttemp, SWT.MULTI | SWT.BORDER
				| SWT.FULL_SELECTION);
		tblInstallment.setHeaderVisible(true);
		tblInstallment.setLinesVisible(true);
		tblInstallment.setBounds(188, 29, 185, 210);

		tblInstallment.addListener(SWT.MeasureItem, new Listener() {
			public void handleEvent(Event event) {
				event.height = 18;
			}
		});

		for (int i = 0; i < 2; i++) {
			column = new TableColumn(tblInstallment, SWT.CENTER, i);
			column.setText(COLUMN_HEADINGS[i]);
			if (i == 0) {
				tblInstallment.getColumn(i).setWidth(80);
			} else {
				tblInstallment.getColumn(i).setWidth(80);
			}
		}
	}

	/**
	 * Method to create Table for Installments
	 * 
	 * @param composite
	 * @param rows
	 */
	private void createTable(Composite composite, int rows) {

		tblInstallment.dispose();

		tblInstallment = new Table(composite, SWT.MULTI | SWT.BORDER
				| SWT.FULL_SELECTION);

		tblInstallment.setLinesVisible(true);
		tblInstallment.setHeaderVisible(true);

		tblInstallment.addListener(SWT.MeasureItem, new Listener() {
			public void handleEvent(Event event) {
				event.height = 18;
			}
		});

		for (int i = 0; i < 2; i++) {
			column = new TableColumn(tblInstallment, SWT.CENTER, i);
			column.setText(COLUMN_HEADINGS[i]);
			if (i == 0) {
				tblInstallment.getColumn(i).setWidth(80);
			} else {
				tblInstallment.getColumn(i).setWidth(80);
			}
		}

		TBL_HEIGHT = (rows * 18) + 20;

		if (TBL_HEIGHT < 239) {
			tblInstallment.setBounds(188, 29, 185, 239);
		} else {
			tblInstallment.setBounds(188, 29, 185, 239);
		}
		text = new Text[rows];
		lblInst = new Label[rows];
		// Drawing table items
		for (int rowId = 0; rowId < rows; rowId++) {

			// Adding Table items with the existing Table
			item = new TableItem(tblInstallment, SWT.NONE);

			// Label
			editor = new TableEditor(tblInstallment);

			lblInst[rowId] = new Label(tblInstallment, SWT.NONE);
			lblInst[rowId].setText(Integer.toString(rowId + 1));
			editor.grabHorizontal = true;
			editor.setEditor(lblInst[rowId], item, 0);
			lblInst[rowId].setFont(LABLE_FONT);

			// Draw Amount Text Field
			editor = new TableEditor(tblInstallment);

			text[rowId] = new Text(tblInstallment, SWT.NONE);
			text[rowId].addVerifyListener(new FloatLimitValidation());
			text[rowId].addFocusListener(new FocusListener() {

				@Override
				public void focusGained(FocusEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void focusLost(FocusEvent event) {
					if (txtHint.isVisible()) {
						float entered = 0;
						for (int i = 0; i < tblInstallment.getItemCount(); i++) {
							String inst = text[i].getText();
							if (!inst.equals(EMPTY_STRING)) {
								entered = entered + Float.parseFloat(inst);
							}

						}
						String total = txtAmount.getText();
						if (!total.equals(EMPTY_STRING)) {
							float remain = getRounded2Decimal(Float
									.valueOf(total)
									- entered);
							DecimalFormat df = new DecimalFormat("0.00");
							txtHint.setText(String.valueOf(df.format(entered))
									+ " [" + df.format(remain) + "]");
						}

					}

				}

			});

			editor.grabHorizontal = true;
			editor.setEditor(text[rowId], item, 1);
			text[rowId].setFont(TEXT_FONT);
			String tempInsAmount1 = "";
			if (!txtAmount.getText().equals("")) {
				tempInsAmount1 = (String.valueOf(Float.parseFloat(txtAmount
						.getText())
						/ rows));
			}
			int dotposition = tempInsAmount1.indexOf(".");
			String tempInsAmount = tempInsAmount1.substring(dotposition + 1);
			int len = tempInsAmount.length();
			if (len > 2) {
				int a = Integer.parseInt(tempInsAmount.substring(1, 2));
				a = a + 1;
				tempInsAmount = tempInsAmount.substring(0, 1)
						+ String.valueOf(a);
			} else if (len == 1) {
				tempInsAmount = tempInsAmount.substring(0, 1) + "0";
			}

			tempInsAmount1 = tempInsAmount1.substring(0, dotposition + 1)
					+ tempInsAmount;

			if (rdAutomatic.getSelection())
				text[rowId].setText(tempInsAmount1);

		}

		// }
		composite.layout();
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public Composite loadComposite() throws Exception {
		try {
			{

				GridLayout gl = new GridLayout();
				gl.numColumns = 4;
				gl.horizontalSpacing = 0;
				gl.verticalSpacing = 0;
				gl.marginWidth = 0;
				gl.marginHeight = 0;
				this.setLayout(gl);
				this.setSize(283, 143);
				GridData gd = new GridData();
				gd.verticalSpan = 2;
				gd.horizontalSpan = 2;
				gd.horizontalAlignment = GridData.FILL;
				gd.verticalAlignment = GridData.FILL;
				gd.grabExcessHorizontalSpace = true;
				gd.grabExcessVerticalSpace = true;
				tbfSundry = new TabFolder(this, SWT.NONE);
				tbfSundry.setLayoutData(gd);

				{
					if (BeanUtil.getAdminRights() == 3) {
						tbiBooking = new TabItem(tbfSundry, SWT.NONE);
						tbiBooking.setText(BOOKING_TAB_NAME);
						cvsBooking = new Canvas(tbfSundry, SWT.NONE);
						tbiBooking.setControl(cvsBooking);

						// Booking
						if (treeBooking == null) {
							treeBooking = new TreeComposite(cvsBooking,
									SWT.NONE, 350).loadComposite();
							treeBooking.setBounds(30, 50, 200, 400);
						}

						if (treeDelivery != null)
							treeDelivery.dispose();

						lblName = new Label(cvsBooking, SWT.NONE);
						lblName.setBounds(250, 240, 70, 20);
						lblName.setFont(LABLE_FONT);
						lblName.setText("Profile Name");

						cbProfile = new Combo(cvsBooking, SWT.READ_ONLY);
						cbProfile.setBounds(250, 270, 100, 25);

						{
							lblDate = new Label(cvsBooking, SWT.NONE);
							lblDate.setText("Date ");
							lblDate.setBounds(360, 250, 100, 20);
							lblDate.setFont(LABLE_FONT);
						}

						/*
						 * { lblWarning = new Label(cvsBooking, SWT.NONE);
						 * lblWarning.setText("WARNING:");
						 * lblWarning.setBounds(465, 250, 100, 20);
						 * lblWarning.setFont(LABLE_FONT); }
						 */

						{
							txtDate = new Text(cvsBooking, SWT.BORDER);
							txtDate.setBounds(360, 270, 80, 22);
							txtDate.setFont(TEXT_FONT);
							txtDate.setEnabled(false);
							txtDate.setText(SERVER_DATE);
						}
						{
							btnGo = new Button(cvsBooking, SWT.PUSH
									| SWT.CENTER);
							btnGo.setImage(SWTResourceManager
									.getImage("hm/akr/resources/Calendar.jpg"));
							btnGo.setBounds(445, 268, 26, 23);
							btnGo.setFont(BUTTON_FONT);
							btnGo.addSelectionListener(new SelectionAdapter() {
								public void widgetSelected(SelectionEvent e) {
									KalendarDialog cd = new KalendarDialog(
											new Shell());
									Object obj = cd.open();
									if (obj != null) {
										String date = obj.toString();
										try {
											SimpleDateFormat parse = new SimpleDateFormat(
													"dd-MM-yyyy");
											Date curDate = parse
													.parse(SERVER_DATE);

											Date selectedDate = parse
													.parse(date);
											if (selectedDate.before(curDate)
													|| selectedDate
															.equals(curDate)) {
												txtDate.setText(date);
												AdminComposite.display("",
														STATUS_SUCCESS,
														SUCCESS_FONT_COLOR);
											} else {
												txtDate.setText("");
												AdminComposite
														.display(
																"Selected date should be lesser or equal to current date",
																STATUS_SUCCESS,
																SUCCESS_FONT_COLOR);
											}

										} catch (Exception exception) {
											exception.printStackTrace();
										}

									}
								}
							});
						}

						btnSet = new Button(cvsBooking, SWT.NONE);
						btnSet.setBounds(475, 268, 42, 23);
						btnSet.setText("Set");
						btnSet.setFont(BUTTON_FONT);
						btnSet.addSelectionListener(new SundryTabListener());
					}

				}

				{
					if (BeanUtil.getAdminRights() == 3) {
						tbiDelivery = new TabItem(tbfSundry, SWT.NONE);
						tbiDelivery.setText(DELIVERY_TAB_NAME);
						cvsDelivery = new Canvas(tbfSundry, SWT.NONE);

						if (treeDelivery == null) {
							treeDelivery = new TreeComposite(cvsDelivery,
									SWT.NONE, 350).loadComposite();
							treeDelivery.setBounds(30, 50, 200, 400);
						}

						lblName = new Label(cvsDelivery, SWT.NONE);
						lblName.setBounds(250, 248, 70, 20);
						lblName.setFont(LABLE_FONT);
						lblName.setText("DC Profile");

						cbDCProfile = new Combo(cvsDelivery, SWT.READ_ONLY);
						cbDCProfile.setBounds(250, 270, 60, 22);

						btnDelivery = new Button(cvsDelivery, SWT.NONE);
						btnDelivery.setBounds(364, 270, 40, 22);
						btnDelivery.setText("Set");
						btnDelivery.setFont(BUTTON_FONT);
						btnDelivery
								.addSelectionListener(new SundryTabListener());

						tbiDelivery.setControl(cvsDelivery);

					}
				}

				{
					// if(BeanUtil.getAdminRights() != 1){
					tbiRefund = new TabItem(tbfSundry, SWT.NONE);
					tbiRefund.setText(REFUND_TAB_NAME);

					if (cptinstallment == null) {
						cptinstallment = getComposite("+");
						cptinstallment.setBounds(250, 100, 389, 310);
					}
					/*
					 * if(treeComposite == null){ tree = new
					 * TreeComposite(tbfSundry, SWT.NONE, 300); treeComposite =
					 * tree.loadComposite(); treeComposite.setBounds(30, 104,
					 * 200, 500); }
					 */
					// }
					/*
					 * cptRefund = new Composite(tbfSundry, SWT.NONE);
					 * 
					 * treeRefund = new TreeComposite(cptRefund, SWT.NONE,
					 * 350).loadComposite(); treeRefund.setBounds(30, 50, 200,
					 * 400);
					 *  { lblContact = new Label(cptRefund, SWT.NONE);
					 * lblContact.setText("Contact"); lblContact.setBounds(6,
					 * 92, 70, 20); lblContact.setFont(LABLE_FONT); }
					 *  { lblDescription = new Label(cptRefund, SWT.NONE);
					 * lblDescription.setText("Description");
					 * lblDescription.setBounds(6, 6, 138, 19);
					 * lblDescription.setFont(LABLE_FONT);
					 *  } { txtAreaDescription = new Text(cptRefund,
					 * SWT.V_SCROLL | SWT.BORDER | SWT.WRAP);
					 * txtAreaDescription.setBounds(4, 28, 143, 52);
					 * txtAreaDescription.setFont(TEXT_FONT);
					 * txtAreaDescription.addTraverseListener(new
					 * TraverseListener() { public void
					 * keyTraversed(TraverseEvent e) { if (e.detail ==
					 * SWT.TRAVERSE_TAB_NEXT || e.detail ==
					 * SWT.TRAVERSE_TAB_PREVIOUS) { e.doit = true; } } });
					 *  } {
					 * 
					 * txtAreaContact = new Text(cptRefund, SWT.V_SCROLL |
					 * SWT.BORDER | SWT.WRAP); txtAreaContact.setBounds(4, 112,
					 * 145, 56); txtAreaContact.setFont(TEXT_FONT);
					 * txtAreaContact.addTraverseListener(new TraverseListener() {
					 * public void keyTraversed(TraverseEvent e) { if (e.detail ==
					 * SWT.TRAVERSE_TAB_NEXT || e.detail ==
					 * SWT.TRAVERSE_TAB_PREVIOUS) { e.doit = true; } } });
					 *  } { lblAmount = new Label(cptRefund, SWT.NONE);
					 * lblAmount.setText("Amount"); lblAmount.setBounds(6, 181,
					 * 143, 17); lblAmount.setFont(LABLE_FONT);
					 *  } { txtAmount = new Text(cptRefund, SWT.BORDER);
					 * txtAmount.setBounds(4, 201, 141, 29);
					 * txtAmount.setFont(TEXT_FONT);
					 * txtAmount.addVerifyListener(new FloatLimitValidation()); } {
					 * lblInstallments = new Label(cptRefund, SWT.NONE);
					 * lblInstallments.setText("Installments");
					 * lblInstallments.setBounds(5, 243, 140, 17);
					 * lblInstallments.setFont(LABLE_FONT);
					 *  } { txtInstallments = new Text(cptRefund, SWT.BORDER);
					 * txtInstallments.setBounds(3, 263, 109, 25);
					 * 
					 * txtInstallments.setFont(TEXT_FONT);
					 * txtInstallments.addVerifyListener(new
					 * NumericValidation());
					 * txtInstallments.addFocusListener(new
					 * SundryTabListener()); }
					 *  { txtHint = new Text(cptRefund, SWT.BORDER);
					 * txtHint.setBounds(275, 275, 100, 22);
					 * txtHint.setFont(TEXT_FONT); txtHint.setVisible(false);
					 * txtHint.setEnabled(false); }
					 *  { rdAutomatic = new Button(cptRefund, SWT.RADIO |
					 * SWT.LEFT); rdAutomatic.setText("Automatic");
					 * rdAutomatic.setSelection(true);
					 * rdAutomatic.setBounds(188, 2, 92, 19);
					 * rdAutomatic.addSelectionListener(new
					 * SundryTabListener()); }
					 *  { rdManual = new Button(cptRefund, SWT.RADIO |
					 * SWT.LEFT); rdManual.setText("Manual");
					 * rdManual.setBounds(286, 4, 74, 19);
					 * rdManual.addSelectionListener(new SundryTabListener()); }
					 * 
					 * drawTable();
					 * 
					 * tbiRefund.setControl(cptRefund); tbfSundry.layout();
					 */
				}

				{
					// if(BeanUtil.getAdminRights() != 1){
					tbiRecovery = new TabItem(tbfSundry, SWT.NONE);
					tbiRecovery.setText(RECOVERY_TAB_NAME);
					// }
					/*
					 * cptRecovery = new Composite(tbfSundry, SWT.NONE);
					 * 
					 * treeRecovery = new TreeComposite(cptRecovery, SWT.NONE,
					 * 350).loadComposite(); treeRecovery.setBounds(30, 50, 200,
					 * 400);
					 *  { lblContact = new Label(cptRecovery, SWT.NONE);
					 * lblContact.setText("Contact"); lblContact.setBounds(6,
					 * 92, 70, 20); lblContact.setFont(LABLE_FONT); }
					 *  { lblDescription = new Label(cptRecovery, SWT.NONE);
					 * lblDescription.setText("Description");
					 * lblDescription.setBounds(6, 6, 138, 19);
					 * lblDescription.setFont(LABLE_FONT);
					 *  } { txtAreaDescription = new Text(cptRecovery,
					 * SWT.V_SCROLL | SWT.BORDER | SWT.WRAP);
					 * txtAreaDescription.setBounds(4, 28, 143, 52);
					 * txtAreaDescription.setFont(TEXT_FONT);
					 * txtAreaDescription.addTraverseListener(new
					 * TraverseListener() { public void
					 * keyTraversed(TraverseEvent e) { if (e.detail ==
					 * SWT.TRAVERSE_TAB_NEXT || e.detail ==
					 * SWT.TRAVERSE_TAB_PREVIOUS) { e.doit = true; } } });
					 *  } {
					 * 
					 * txtAreaContact = new Text(cptRecovery, SWT.V_SCROLL |
					 * SWT.BORDER | SWT.WRAP); txtAreaContact.setBounds(4, 112,
					 * 145, 56); txtAreaContact.setFont(TEXT_FONT);
					 * txtAreaContact.addTraverseListener(new TraverseListener() {
					 * public void keyTraversed(TraverseEvent e) { if (e.detail ==
					 * SWT.TRAVERSE_TAB_NEXT || e.detail ==
					 * SWT.TRAVERSE_TAB_PREVIOUS) { e.doit = true; } } });
					 *  } { lblAmount = new Label(cptRecovery, SWT.NONE);
					 * lblAmount.setText("Amount"); lblAmount.setBounds(6, 181,
					 * 143, 17); lblAmount.setFont(LABLE_FONT);
					 *  } { txtAmount = new Text(cptRecovery, SWT.BORDER);
					 * txtAmount.setBounds(4, 201, 141, 29);
					 * txtAmount.setFont(TEXT_FONT);
					 * txtAmount.addVerifyListener(new FloatLimitValidation()); } {
					 * lblInstallments = new Label(cptRecovery, SWT.NONE);
					 * lblInstallments.setText("Installments");
					 * lblInstallments.setBounds(5, 243, 140, 17);
					 * lblInstallments.setFont(LABLE_FONT);
					 *  } { txtInstallments = new Text(cptRecovery, SWT.BORDER);
					 * txtInstallments.setBounds(3, 263, 109, 25);
					 * 
					 * txtInstallments.setFont(TEXT_FONT);
					 * txtInstallments.addVerifyListener(new
					 * NumericValidation());
					 * txtInstallments.addFocusListener(new
					 * SundryTabListener()); }
					 *  { txtHint = new Text(cptRecovery, SWT.BORDER);
					 * txtHint.setBounds(275, 275, 100, 22);
					 * txtHint.setFont(TEXT_FONT); txtHint.setVisible(false);
					 * txtHint.setEnabled(false); }
					 *  { rdAutomatic = new Button(cptRecovery, SWT.RADIO |
					 * SWT.LEFT); rdAutomatic.setText("Automatic");
					 * rdAutomatic.setSelection(true);
					 * rdAutomatic.setBounds(188, 2, 92, 19);
					 * rdAutomatic.addSelectionListener(new
					 * SundryTabListener()); }
					 *  { rdManual = new Button(cptRecovery, SWT.RADIO |
					 * SWT.LEFT); rdManual.setText("Manual");
					 * rdManual.setBounds(286, 4, 74, 19);
					 * rdManual.addSelectionListener(new SundryTabListener()); }
					 * 
					 * drawTable();
					 * 
					 * tbiRecovery.setControl(cptRecovery); tbfSundry.layout();
					 */

				}

				{
					Canvas cvsCCNorms = null;
					if (BeanUtil.getAdminRights() == 3) {
						tbiCCNorms = new TabItem(tbfSundry, SWT.NONE);
						tbiCCNorms.setText(CC_NORMS_TAB_NAME);

						cvsCCNorms = new Canvas(tbfSundry, SWT.NONE);
						tbiCCNorms.setControl(cvsCCNorms);

						{
							tbfSub = new TabFolder(cvsCCNorms, SWT.NONE);
							tbfSub.setSize(800, 500);
							tbfSub.setSelection(0);
							tbfSub
									.addSelectionListener(new SundryTabListener());

							// Commission settings- L , R , C
							{

								tbiSubCCCommission = new TabItem(tbfSub,
										SWT.NONE);
								tbiSubCCCommission.setText(CC_COMMISSION);
								cvsCCComm = new Canvas(tbfSub, SWT.NONE);
								tbiSubCCCommission.setControl(cvsCCComm);

								treeCCCommission = new TreeComposite(cvsCCComm,
										SWT.NONE, 350).loadComposite();
								treeCCCommission.setBounds(30, 50, 200, 400);

								lblCCLimit = new Label(cvsCCComm, SWT.NONE);
								lblCCLimit.setText("CC Profile");
								lblCCLimit.setBounds(250, 240, 70, 20);
								lblCCLimit.setFont(LABLE_FONT);

								cbCCProfile = new Combo(cvsCCComm,
										SWT.READ_ONLY);
								cbCCProfile.setBounds(250, 270, 100, 25);

								/*
								 * lblConsider = new Label(cvsCCComm, SWT.NONE);
								 * lblConsider.setText("Consider");
								 * lblConsider.setBounds(325, 240, 70, 20);
								 * lblConsider.setFont(LABLE_FONT);
								 * 
								 * lblCCRefund = new Label(cvsCCComm, SWT.NONE);
								 * lblCCRefund.setText("Refund");
								 * lblCCRefund.setBounds(405, 240, 70, 20);
								 * lblCCRefund.setFont(LABLE_FONT);
								 * 
								 * 
								 * txtCCLimit = new Text(cvsCCComm, SWT.BORDER);
								 * txtCCLimit.setBounds(250, 270, 60, 22);
								 * txtCCLimit.setFont(TEXT_FONT);
								 * txtCCLimit.addVerifyListener(new
								 * NumericExtendedValidation());
								 * txtCCLimit.addKeyListener(new KeyListener() {
								 * 
								 * @Override public void keyPressed(KeyEvent
								 * arg0) { // TODO Auto-generated method stub
								 *  }
								 * 
								 * @Override public void keyReleased(KeyEvent
								 * event) { if (event.character == 'a' ||
								 * event.character == 'A') {
								 * txtCCLimit.setText("All"); }
								 *  }
								 * 
								 * });
								 * 
								 * txtCCconsider = new Text(cvsCCComm,
								 * SWT.BORDER); txtCCconsider.setBounds(325,
								 * 270, 60, 22); txtCCconsider.setTextLimit(6);
								 * txtCCconsider.setFont(TEXT_FONT);
								 * txtCCconsider.addVerifyListener(new
								 * NumericExtendedValidation());
								 * txtCCconsider.addKeyListener(new
								 * KeyListener() { @Override public void
								 * keyPressed(KeyEvent arg0) { // TODO
								 * Auto-generated method stub
								 *  }
								 * 
								 * @Override public void keyReleased(KeyEvent
								 * event) { if (event.character == 'a' ||
								 * event.character == 'A') {
								 * txtCCconsider.setText("All"); } }
								 * 
								 * });
								 * 
								 * txtCCrefund = new Text(cvsCCComm,
								 * SWT.BORDER); txtCCrefund.setBounds(405, 270,
								 * 60, 22); txtCCrefund.setTextLimit(6);
								 * txtCCrefund.setFont(TEXT_FONT);
								 * txtCCrefund.addVerifyListener(new
								 * NumericValidation());
								 */

								btnCommisionSet = new Button(cvsCCComm,
										SWT.NONE);
								btnCommisionSet.setBounds(350, 269, 40, 22);
								btnCommisionSet.setText("Set");
								btnCommisionSet.setFont(BUTTON_FONT);
								btnCommisionSet
										.addSelectionListener(new SundryTabListener());
							}

							// Special
							{/*
								 * tbiSubSpecial = new TabItem(tbfSub,
								 * SWT.NONE); tbiSubSpecial.setText(CC_SPECIAL);
								 *  // MSB Tab
								 * 
								 * cptCCSpecial = new Composite(tbfSub,
								 * SWT.BORDER); // cptMSB.setBounds(20, 0, 300,
								 * 500);
								 * 
								 *  // MSB treeCCSpecial = new
								 * TreeComposite(cptMSB, SWT.NONE, 300);
								 * treeCptCCSpecial =
								 * treeCCSpecial.loadComposite();
								 * treeCptCCSpecial.setBounds(30, 104, 200,
								 * 330);
								 * 
								 * rdCCEnable = new Button(cptCCSpecial,
								 * SWT.RADIO); rdCCEnable.setBounds(300, 200,
								 * 100, 30); rdCCEnable.setText("CC Enable");
								 * 
								 * rdCCDisable = new Button(cptCCSpecial,
								 * SWT.RADIO); rdCCDisable.setBounds(410, 200,
								 * 100, 30); rdCCDisable.setText("CC Disable");
								 * rdCCDisable.setSelection(true);
								 * 
								 * btnCCSpecial = new Button(cptCCSpecial,
								 * SWT.PUSH); btnCCSpecial.setBounds(520, 202,
								 * 50, 23); btnCCSpecial.setText("Set");
								 * btnCCSpecial.addSelectionListener(new
								 * SundryTabListener());
								 * 
								 * tbiSubSpecial.setControl(cptCCSpecial);
								 */
							}

							// Commodity
							{
								// MSB Tab

								/*
								 * { tbiSubCCCommodity = new TabItem(tbfSub,
								 * SWT.BORDER);
								 * tbiSubCCCommodity.setText(CC_FOR_COMM); }
								 * 
								 * cptCCCommodity = new Composite(tbfSub,
								 * SWT.BORDER); //cptMSB.setBounds(20, 0, 300,
								 * 500);
								 * 
								 * 
								 * 
								 * rdCCCommodityEnable = new
								 * Button(cptCCCommodity, SWT.RADIO);
								 * rdCCCommodityEnable.setBounds(300, 200, 100,
								 * 30); rdCCCommodityEnable.setText("CC
								 * Enable");
								 * 
								 * 
								 * rdCCCommodityDisable = new
								 * Button(cptCCCommodity, SWT.RADIO);
								 * rdCCCommodityDisable.setBounds(420, 200, 100,
								 * 30); rdCCCommodityDisable.setText("CC
								 * Disable");
								 * rdCCCommodityDisable.setSelection(true);
								 * 
								 * btnCCCommodity = new Button(cptCCCommodity,
								 * SWT.PUSH); btnCCCommodity.setBounds(530, 202,
								 * 50, 23); btnCCCommodity.setText("Set");
								 * btnCCCommodity.addSelectionListener(new
								 * SundryTabListener());
								 * 
								 * tbiSubCCCommodity.setControl(cptCCCommodity);
								 */

								// HLC Tab
								{
									tbiSubHLC = new TabItem(tbfSub, SWT.BORDER);
									tbiSubHLC.setText(HLC);
								}

								cptHLC = new Composite(tbfSub, SWT.BORDER);
								// cptMSB.setBounds(20, 0, 300, 500);

								lblCustomer = new Label(cptHLC, SWT.NONE);
								lblCustomer.setBounds(300, 200, 100, 30);
								lblCustomer.setText("Customers");

								cbCustomers = new Combo(cptHLC, SWT.READ_ONLY);
								cbCustomers.setBounds(300, 230, 100, 23);

								lblCCArticle = new Label(cptHLC, SWT.NONE);
								lblCCArticle.setBounds(420, 200, 100, 30);
								lblCCArticle.setText("CC/Article");

								txtccArticle = new Text(cptHLC, SWT.BORDER);
								txtccArticle.setBounds(420, 230, 100, 23);

								lblRefundArticle = new Label(cptHLC, SWT.NONE);
								lblRefundArticle.setBounds(530, 202, 100, 23);
								lblRefundArticle.setText("Refund/Article");

								txtrefundArticle = new Text(cptHLC, SWT.BORDER);
								txtrefundArticle.setBounds(530, 230, 100, 23);

								btnCCHLC = new Button(cptHLC, SWT.PUSH);
								btnCCHLC.setBounds(640, 230, 50, 23);
								btnCCHLC.setText("Set");
								btnCCHLC
										.addSelectionListener(new SundryTabListener());

								tbiSubHLC.setControl(cptHLC);
							}

						}
					}
				}

				{
					if (BeanUtil.getAdminRights() == 3) {
						tbiManageCommission = new TabItem(tbfSundry, SWT.NONE);
						tbiManageCommission.setText(MANAGE_COMMISSION);
						cvsMC = new Canvas(tbfSundry, SWT.None);
						cvsMC.setBounds(10, 10, 800, 500);
						tbiManageCommission.setControl(cvsMC);

						// tbiManageCommission.setControl(cvsManageSundry);
						tbfSundry.layout();
					}
				}

				tbfSundry.setSelection(0);
				tbfSundry.addSelectionListener(new SundryTabListener());

				lblHeader = new Label(tbfSundry, SWT.NONE);
				lblHeader.setBounds(200, 50, 200, 30);
				lblHeader.setAlignment(SWT.RIGHT);
				lblHeader.setFont(HEAD_FONT);
				// lblHeader.setText(BOOKING_TAB_NAME);

				String selected = tbfSundry.getSelection()[0].getText();

				if (selected.equals(BOOKING_TAB_NAME)) {
					if (null != handler) {
						try {
							comboNames = handler.getProfileNames();
							if (null != comboNames) {
								cbProfile.setItems(comboNames);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}

				/*
				 * { btnrecoveryrefund = new Button(tbfSundry, SWT.NONE);
				 * btnrecoveryrefund.setBounds(590, 410, 40, 22);
				 * btnrecoveryrefund.setText("Set");
				 * btnrecoveryrefund.setFont(BUTTON_FONT);
				 * btnrecoveryrefund.addSelectionListener(new
				 * SundryTabListener()); }
				 */

			}

			tree = new TreeComposite(tbfSundry, SWT.NONE, 300);
			treeComposite = tree.loadComposite();
			treeComposite.setBounds(30, 104, 200, 500);

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		return this;
	}

	/**
	 * 
	 * Listener for RegularSundry Tab
	 * 
	 */
	public class SundryTabListener implements SelectionListener, FocusListener {

		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {

		}

		@Override
		public void widgetSelected(SelectionEvent event) {
			AdminComposite
					.display("STATUS", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			Object source = event.getSource();
			String selectedSubTab = "";
			if (tbfSub != null)
				selectedSubTab = tbfSub.getSelection()[0].getText();
			if (tbfSub == source) {
				try {
					if (selectedSubTab.equalsIgnoreCase(HLC)) {
						lblHeader.setText(HLC);
						treeHLC = new TreeComposite(cptHLC, SWT.NONE, 350,
								"HLC").loadComposite();
						treeHLC.setBounds(30, 50, 200, 400);
						populateCustomers();

						if (treeCCCommodity != null)
							treeCCCommodity.dispose();
						if (cptCCSpltree != null)
							cptCCSpltree.dispose();

						if (treeCCCommission != null)
							treeCCCommission.dispose();

					} else if (selectedSubTab.equalsIgnoreCase(CC_FOR_COMM)) {
						lblHeader.setText(CC_FOR_COMM);
						treeCCCommodity = new TreeComposite(cptCCCommodity,
								SWT.NONE, 350).loadComposite();
						treeCCCommodity.setBounds(30, 50, 200, 400);

						if (treeHLC != null)
							treeHLC.dispose();
						if (cptCCSpltree != null)
							cptCCSpltree.dispose();
						if (treeCCCommission != null)
							treeCCCommission.dispose();
					} else if (selectedSubTab.equalsIgnoreCase(CC_SPECIAL)) {
						lblHeader.setText(CC_SPECIAL);
						cptCCSpltree = new TreeComposite(cptCCSpecial,
								SWT.NONE, 350).loadComposite();
						cptCCSpltree.setBounds(30, 50, 200, 400);

						if (treeCCCommodity != null)
							treeCCCommodity.dispose();
						if (treeHLC != null)
							treeHLC.dispose();
						if (treeCCCommission != null)
							treeCCCommission.dispose();

					} else if (selectedSubTab.equalsIgnoreCase(CC_COMMISSION)) {
						lblHeader.setText(CC_COMMISSION);
						treeCCCommission = new TreeComposite(cvsCCComm,
								SWT.NONE, 350).loadComposite();
						treeCCCommission.setBounds(30, 50, 200, 400);

						if (treeCCCommodity != null)
							treeCCCommodity.dispose();
						if (cptCCSpltree != null)
							cptCCSpltree.dispose();
						if (treeHLC != null)
							treeHLC.dispose();

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			String selectedTab = tbfSundry.getSelection()[0].getText();
			try {

				if (tbfSundry == source) {

					if (BOOKING_TAB_NAME.equals(selectedTab)) {
						// lblHeader.setText(BOOKING_TAB_NAME);

						if (treeBooking != null)
							treeBooking.dispose();

						treeBooking = new TreeComposite(cvsBooking, SWT.NONE,
								350).loadComposite();
						treeBooking.setBounds(30, 50, 200, 400);

						if (treeDelivery != null)
							treeDelivery.dispose();

						/*
						 * if (null != cptinstallment) cptinstallment.dispose();
						 * treeComposite.setVisible(true); tree.clearTree();
						 * lblName.setText("Profile Name");
						 * lblHeader.setText(BOOKING_TAB_NAME);
						 * 
						 * lblName.setVisible(true); cbProfile.setVisible(true);
						 * lblDate.setVisible(true); txtDate.setVisible(true);
						 * btnGo.setVisible(true); btnSet.setVisible(true);
						 * 
						 * txtDelivery.setVisible(false);
						 * txtCCconsider.setVisible(false);
						 * txtCCrefund.setVisible(false);
						 * txtCCLimit.setVisible(false);
						 * 
						 * btnCommisionSet.setVisible(false);
						 * btnDelivery.setVisible(false);
						 * btnrecoveryrefund.setVisible(false);
						 * 
						 * lblCCLimit.setVisible(false);
						 * lblConsider.setVisible(false);
						 * lblCCRefund.setVisible(false);
						 * lblUnit.setVisible(false); if (null !=
						 * cptinstallment) { cptinstallment.dispose();
						 * cptinstallment = null; }
						 */

					} else if (DELIVERY_TAB_NAME.equals(selectedTab)) {

						if (null != handler) {
							try {
								comboDCNames = handler.getDCProfileNames();
								if (null != comboDCNames) {
									cbDCProfile.setItems(comboDCNames);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}

						// lblHeader.setText(DELIVERY_TAB_NAME);
						if (treeDelivery != null)
							treeDelivery.dispose();
						treeDelivery = new TreeComposite(cvsDelivery, SWT.NONE,
								350).loadComposite();
						treeDelivery.setBounds(30, 50, 200, 400);

						if (treeBooking != null)
							treeBooking.dispose();
						/*
						 * if (null != cptinstallment) cptinstallment.dispose();
						 * treeComposite.setVisible(true); tree.clearTree();
						 * lblName.setText(DELIVERY_TAB_NAME);
						 * lblHeader.setText(DELIVERY_TAB_NAME);
						 * 
						 * lblName.setVisible(true);
						 * cbProfile.setVisible(false);
						 * lblDate.setVisible(false); txtDate.setVisible(false);
						 * btnGo.setVisible(false); btnSet.setVisible(false);
						 * 
						 * txtDelivery.setVisible(true);
						 * txtCCconsider.setVisible(false);
						 * txtCCrefund.setVisible(false);
						 * txtCCLimit.setVisible(false);
						 * 
						 * btnCommisionSet.setVisible(false);
						 * btnDelivery.setVisible(true);
						 * 
						 * lblCCLimit.setVisible(false);
						 * lblConsider.setVisible(false);
						 * lblCCRefund.setVisible(false);
						 * btnrecoveryrefund.setVisible(false);
						 * lblUnit.setVisible(true);
						 * 
						 * if (null != cptinstallment) {
						 * cptinstallment.dispose(); cptinstallment = null; }
						 */
					} else if (RECOVERY_TAB_NAME.equals(selectedTab)) {

						if (tree != null)
							tree.dispose();
						if (treeComposite != null)
							treeComposite.dispose();

						tree = new TreeComposite(tbfSundry, SWT.NONE, 300);
						treeComposite = tree.loadComposite();
						treeComposite.setBounds(30, 104, 200, 500);

						if (null != cptinstallment)
							cptinstallment.dispose();
						cptinstallment = getComposite("-");
						cptinstallment.setBounds(250, 100, 389, 310);

						lblHeader.setText(RECOVERY_TAB_NAME);

						/*
						 * lblName.setVisible(false);
						 * cbProfile.setVisible(false);
						 * lblDate.setVisible(false); txtDate.setVisible(false);
						 * btnGo.setVisible(false); btnSet.setVisible(false);
						 * 
						 * txtDelivery.setVisible(false);
						 * txtCCconsider.setVisible(false);
						 * txtCCrefund.setVisible(false);
						 * txtCCLimit.setVisible(false);
						 * 
						 * btnCommisionSet.setVisible(false);
						 * btnDelivery.setVisible(false);
						 * 
						 * lblCCLimit.setVisible(false);
						 * lblConsider.setVisible(false);
						 * lblCCRefund.setVisible(false);
						 * btnrecoveryrefund.setVisible(true);
						 * lblUnit.setVisible(false);
						 */

					} else if (REFUND_TAB_NAME.equals(selectedTab)) {
						if (tree != null)
							tree.dispose();
						if (treeComposite != null)
							treeComposite.dispose();

						tree = new TreeComposite(tbfSundry, SWT.NONE, 300);
						treeComposite = tree.loadComposite();
						treeComposite.setBounds(30, 104, 200, 500);

						if (null != cptinstallment)
							cptinstallment.dispose();
						cptinstallment = getComposite("+");
						cptinstallment.setBounds(250, 100, 389, 310);

						lblHeader.setText(REFUND_TAB_NAME);

						/*
						 * lblName.setVisible(false);
						 * cbProfile.setVisible(false);
						 * lblDate.setVisible(false); txtDate.setVisible(false);
						 * btnGo.setVisible(false); btnSet.setVisible(false);
						 * 
						 * txtDelivery.setVisible(false);
						 * txtCCconsider.setVisible(false);
						 * txtCCrefund.setVisible(false);
						 * txtCCLimit.setVisible(false);
						 * 
						 * btnCommisionSet.setVisible(false);
						 * btnDelivery.setVisible(false);
						 * 
						 * lblCCLimit.setVisible(false);
						 * lblConsider.setVisible(false);
						 * lblCCRefund.setVisible(false);
						 * btnrecoveryrefund.setVisible(true);
						 * lblUnit.setVisible(false);
						 */
					} else if (CC_NORMS_TAB_NAME.equals(selectedTab)) {

						/*
						 * if(treeDelivery != null) treeDelivery.dispose();
						 * 
						 * if(treeBooking != null) treeBooking.dispose();
						 * 
						 * if(tree != null) tree.dispose(); if(treeComposite !=
						 * null) treeComposite.dispose();
						 */

						if (null != handler) {
							try {
								ccComboNames = handler.getCCProfileNames();
								// System.out.println("--"+ccComboNames);
								if (null != ccComboNames) {
									cbCCProfile.setItems(ccComboNames);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}

						if (null != cptinstallment)
							cptinstallment.dispose();

						/*
						 * if (null != cptinstallment) cptinstallment.dispose();
						 * treeComposite.setVisible(true); tree.clearTree();
						 * 
						 * lblName.setVisible(false);
						 * cbProfile.setVisible(false);
						 * lblDate.setVisible(false); txtDate.setVisible(false);
						 * btnGo.setVisible(false); btnSet.setVisible(false);
						 * 
						 * lblCCLimit.setVisible(true);
						 * lblConsider.setVisible(true);
						 * lblCCRefund.setVisible(true);
						 * 
						 * txtCCconsider.setText(""); txtCCrefund.setText("");
						 * txtCCLimit.setText("");
						 * 
						 * txtDelivery.setVisible(false);
						 * txtCCconsider.setVisible(true);
						 * txtCCrefund.setVisible(true);
						 * txtCCLimit.setVisible(true);
						 * btnCommisionSet.setVisible(true);
						 * btnDelivery.setVisible(false);
						 * btnrecoveryrefund.setVisible(false);
						 * 
						 * lblHeader.setText(CC_NORMS_TAB_NAME);
						 * lblUnit.setVisible(false);
						 */
					} else if (MANAGE_COMMISSION.equals(selectedTab)) {

						if (tree != null)
							tree.dispose();
						if (treeComposite != null)
							treeComposite.dispose();

						if (cptinstallment != null)
							cptinstallment.dispose();

						if (cptManageSundry != null)
							cptManageSundry.dispose();

						if (treeDelivery != null)
							treeDelivery.dispose();

						if (treeBooking != null)
							treeBooking.dispose();

						cptManageSundry = new ManageCommissionComposite(cvsMC,
								SWT.NONE).loadComposite();
						cptManageSundry.setBounds(10, 10, 800, 500);

						// lblHeader.setText(MANAGE_COMMISSION);
						/*
						 * if (null != cptinstallment) cptinstallment.dispose();
						 * treeComposite.setVisible(false); tree.clearTree();
						 * 
						 * lblName.setVisible(false);
						 * cbProfile.setVisible(false);
						 * lblDate.setVisible(false); txtDate.setVisible(false);
						 * btnGo.setVisible(false); btnSet.setVisible(false);
						 * 
						 * lblCCLimit.setVisible(false);
						 * lblConsider.setVisible(false);
						 * lblCCRefund.setVisible(false);
						 * 
						 * txtDelivery.setVisible(false);
						 * txtCCconsider.setVisible(false);
						 * txtCCrefund.setVisible(false);
						 * txtCCLimit.setVisible(false);
						 * 
						 * //btnCommisionSet.setVisible(false);
						 * btnDelivery.setVisible(false);
						 * btnrecoveryrefund.setVisible(false);
						 * lblUnit.setVisible(false);
						 * 
						 * lblHeader.setText(MANAGE_COMMISSION);
						 */

					}

				} else if (btnSet == source) {
					AdminComposite.display("", STATUS_SUCCESS,
							SUCCESS_FONT_COLOR);
					setProfile();
				} else if (btnDelivery == source) {
					AdminComposite.display("", STATUS_SUCCESS,
							SUCCESS_FONT_COLOR);
					setDelivery();
				} else if (btnCommisionSet == source) {
					AdminComposite.display("", STATUS_SUCCESS,
							SUCCESS_FONT_COLOR);
					if (selectedTab.equals(CC_NORMS_TAB_NAME)) {
						setRefunder();
					}
				} else if (btnrecoveryrefund == source) {
					AdminComposite.display("", STATUS_SUCCESS,
							SUCCESS_FONT_COLOR);
					String[] stations = null;
					stations = tree.getSelectedMSBStation();
					boolean isValid = validateRecoveryRefund(stations);
					if (isValid) {
						if (selectedTab.equals(REFUND_TAB_NAME))
							setRefund(stations);
						else if (selectedTab.equals(RECOVERY_TAB_NAME)) {
							setRecovery(stations);
						}
					}
				} else if (rdAutomatic == source) {
					int rows = 0;
					if (!txtInstallments.getText().equals(EMPTY_STRING))
						rows = Integer.parseInt(txtInstallments.getText());
					if (rows > 0 && !txtAmount.getText().equals(EMPTY_STRING)) {
						try {
							createTable(cpttemp, rows);
						} catch (Exception exception) {
							exception.printStackTrace();
						}
					}
					txtHint.setVisible(false);
				} else if (rdManual == source) {
					int rows = 0;
					if (!txtInstallments.getText().equals(EMPTY_STRING))
						rows = Integer.parseInt(txtInstallments.getText());
					if (rows > 0 && !txtAmount.getText().equals(EMPTY_STRING)) {
						try {
							createTable(cpttemp, rows);
						} catch (Exception exception) {
							exception.printStackTrace();
						}
					}

					txtHint.setVisible(true);
					String amount = txtAmount.getText();
					if (!amount.equals(EMPTY_STRING)) {
						txtHint.setText(0 + " [" + amount + "]");
					}

				} else if (btnCCSpecial == source) {
					AdminComposite.display("", STATUS_SUCCESS,
							SUCCESS_FONT_COLOR);
					int cc = 0;
					String[] stations = null;

					try {

						stations = ((TreeComposite) cptCCSpltree)
								.getSelectedMSBStation();
						if (stations != null && stations.length > 0) {

							if (rdCCEnable.getSelection()) {
								cc = 1;
							} else if (rdCCDisable.getSelection()) {
								cc = 0;
							}
							if (null != handler) {
								handler.setCCForSpecialSundry(stations, cc);
								AdminComposite
										.display(
												"CC for Special Sundry Set Successfully",
												STATUS_SUCCESS,
												SUCCESS_FONT_COLOR);
								rdCCDisable.setSelection(true);
								rdCCEnable.setSelection(false);
								((TreeComposite) cptCCSpltree).clearTree();
							}
						} else {
							AdminComposite.display(
									"Please select atleast a station",
									STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						}
					} catch (Exception exception) {
						exception.printStackTrace();
						AdminComposite.display(
								"CC for Special Sundry can not be Set",
								STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					}

				} else if (btnCCCommodity == source) {
					AdminComposite.display("", STATUS_SUCCESS,
							SUCCESS_FONT_COLOR);
					int cc = 0;
					String[] stations = null;

					try {
						stations = ((TreeComposite) treeCCCommodity)
								.getSelectedMSBStation();
						if (stations != null && stations.length > 0) {

							if (rdCCCommodityEnable.getSelection()) {
								cc = 1;
							} else if (rdCCCommodityDisable.getSelection()) {
								cc = 0;
							}
							if (null != handler) {
								handler.setCCForCommodity(stations, cc);
								AdminComposite
										.display(
												"CC for Special Commodity Set Successfully",
												STATUS_SUCCESS,
												SUCCESS_FONT_COLOR);
								rdCCCommodityDisable.setSelection(true);
								rdCCCommodityEnable.setSelection(false);
								((TreeComposite) treeCCCommodity).clearTree();
								/*
								 * treeCCSpecial.dispose(); treeCCSpecial = new
								 * TreeComposite(cptMSB, SWT.NONE,
								 * 350).loadComposite();
								 * treeCCSpecial.setBounds(30, 50, 200, 400);
								 * 
								 * treeHLC.dispose(); treeHLC = new
								 * TreeComposite(cptHLC, SWT.NONE, 350,
								 * "HLC").loadComposite(); treeHLC.setBounds(30,
								 * 50, 200, 400); populateCustomers();
								 */
							}
						} else {
							AdminComposite.display(
									"Please select atleast a station",
									STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						}
					} catch (Exception exception) {
						exception.printStackTrace();
						AdminComposite.display(
								"CC for Special Commodity can not be Set",
								STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					}

				} else if (btnCCHLC == source) {
					AdminComposite.display("", STATUS_SUCCESS,
							SUCCESS_FONT_COLOR);
					String[] stations = null;

					try {

						stations = ((TreeComposite) treeHLC)
								.getSelectedMSBStation();

						if (isValidHLC(stations)) {
							if (null != handler) {
								if (cbCustomers.getText().equalsIgnoreCase(
										"All")) {
									handler.setCCForHLC(stations, "ALL",
											Float.parseFloat(txtccArticle
													.getText()),
											Float.parseFloat(txtrefundArticle
													.getText()));
								} else {
									handler.setCCForHLC(stations, cbCustomers
											.getText(),
											Float.parseFloat(txtccArticle
													.getText()),
											Float.parseFloat(txtrefundArticle
													.getText()));
								}

								AdminComposite.display(
										"CC for HLC Set Successfully",
										STATUS_SUCCESS, SUCCESS_FONT_COLOR);
								// rdCCDisable.setSelection(true);
								// rdCCEnable.setSelection(false);
								((TreeComposite) treeHLC).clearTree();
								/*
								 * cpttreeHLC.dispose(); cpttreeHLC = new
								 * TreeComposite(cptHLC, SWT.NONE, 350,
								 * "HLC").loadComposite();
								 * cpttreeHLC.setBounds(30, 50, 200, 400);
								 * populateCustomers();
								 */
								txtccArticle.setText(EMPTY_STRING);
								txtrefundArticle.setText(EMPTY_STRING);
								cbCustomers.removeAll();
							}
						}

					} catch (Exception exception) {
						exception.printStackTrace();
						AdminComposite.display("CC for HLC can not be Set",
								STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					}

				}

			} catch (Exception exception) {
				exception.printStackTrace();
			}

		}

		/**
		 * Method to validate Recovery/Refund
		 * 
		 * @return
		 */
		private boolean validateRecoveryRefund(String[] stations) {

			if (stations != null && stations.length <= 0) {
				AdminComposite.display("Please select atleast a station",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				return false;
			} else if (txtAreaDescription.getText().equals(EMPTY_STRING)) {
				AdminComposite.display("Enter Description", STATUS_SUCCESS,
						SUCCESS_FONT_COLOR);
				return false;
			} else if (txtAreaContact.getText().equals(EMPTY_STRING)) {
				AdminComposite.display("Enter Contact Details", STATUS_SUCCESS,
						SUCCESS_FONT_COLOR);
				return false;
			} else if (txtAmount.getText().equals(EMPTY_STRING)) {
				AdminComposite.display("Enter Amount", STATUS_SUCCESS,
						SUCCESS_FONT_COLOR);
				return false;
			} else if (txtInstallments.getText().equals(EMPTY_STRING)) {
				AdminComposite.display("Enter Installments", STATUS_SUCCESS,
						SUCCESS_FONT_COLOR);
				return false;
			} else if (rdManual.getSelection()) {
				float total = 0;
				for (int i = 0; i < tblInstallment.getItemCount(); i++) {
					if (text[i].getText() != EMPTY_STRING) {
						total = total + Float.parseFloat(text[i].getText());
					} else {
						AdminComposite.display("Enter Installment Amount",
								STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						return false;
					}
				}
				if (total != Float.parseFloat(txtAmount.getText())) {
					AdminComposite
							.display(
									"Installment Amount should be equal to total amount",
									STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					return false;
				}
			}

			return true;
		}

		/**
		 * 
		 */
		private void setRecovery(String[] stations) {
			String description = EMPTY_STRING;
			String contact = EMPTY_STRING;

			try {

				if (stations.length > 0) {
					description = txtAreaDescription.getText();
					contact = txtAreaContact.getText();

					int len = tblInstallment.getItemCount();
					if (len > 0) {
						HashMap<Integer, Float> InstAmount = new HashMap<Integer, Float>();
						for (int i = 0; i < len; i++) {
							if (txtAmount != null
									&& !lblInst[i].getText().equals(
											EMPTY_STRING)
									&& text[i].getText() != EMPTY_STRING) {

								InstAmount.put(Integer.parseInt(lblInst[i]
										.getText()), Float.parseFloat(text[i]
										.getText()));

							}
						}
						if (text[len - 1].getText() != EMPTY_STRING) {
							handler.createRecoveryRegister(stations,
									description, contact, InstAmount);
							AdminComposite.display(
									"Recovery Value Set Sucessfully",
									STATUS_SUCCESS, SUCCESS_FONT_COLOR);
							txtInstallments.setText(EMPTY_STRING);
							txtAmount.setText(EMPTY_STRING);
							txtAreaContact.setText(EMPTY_STRING);
							txtAreaDescription.setText(EMPTY_STRING);
							if (txtHint.isVisible()) {
								txtHint.setText(EMPTY_STRING);
							}
							tree.clearTree();
							if (tree != null)
								tree.dispose();
							if (treeComposite != null)
								treeComposite.dispose();

							tree = new TreeComposite(tbfSundry, SWT.NONE, 300);
							treeComposite = tree.loadComposite();
							treeComposite.setBounds(30, 104, 200, 500);

							tblInstallment.dispose();
							drawTable();

						}
					}

				} else
					AdminComposite.display("Please Select Atleast One Station",
							STATUS_SUCCESS, SUCCESS_FONT_COLOR);

			} catch (Exception exception) {
				exception.printStackTrace();
				AdminComposite.display("Recovery Value Set Failed",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			}

		}

		/**
		 * 
		 */
		private void setRefund(String[] stations) {
			String description = EMPTY_STRING;
			String contact = EMPTY_STRING;
			try {

				description = txtAreaDescription.getText();
				contact = txtAreaContact.getText();

				int len = tblInstallment.getItemCount();
				if (len > 0) {
					HashMap<Integer, Float> InstAmount = new HashMap<Integer, Float>();
					for (int i = 0; i < len; i++) {
						if (txtAmount != null
								&& !lblInst[i].getText().equals(EMPTY_STRING)
								&& text[i].getText() != EMPTY_STRING) {

							InstAmount.put(Integer.parseInt(lblInst[i]
									.getText()), Float.parseFloat(text[i]
									.getText()));

						}
					}
					if (text[len - 1].getText() != EMPTY_STRING) {
						handler.createRefundRegister(stations, description,
								contact, InstAmount);
						AdminComposite.display("Refund Value Set Sucessfully",
								STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						txtInstallments.setText(EMPTY_STRING);
						txtAmount.setText(EMPTY_STRING);
						txtAreaContact.setText(EMPTY_STRING);
						txtAreaDescription.setText(EMPTY_STRING);
						if (txtHint.isVisible()) {
							txtHint.setText(EMPTY_STRING);
						}
						tree.clearTree();
						if (tree != null)
							tree.dispose();
						if (treeComposite != null)
							treeComposite.dispose();

						tree = new TreeComposite(tbfSundry, SWT.NONE, 300);
						treeComposite = tree.loadComposite();
						treeComposite.setBounds(30, 104, 200, 500);

						tblInstallment.dispose();
						drawTable();
					}
				}

			} catch (Exception exception) {
				exception.printStackTrace();
			}

		}

		/**
		 * 
		 */
		private void setRefunder() {

			String[] stations = null;
			try {
				stations = ((TreeComposite) treeCCCommission)
						.getSelectedMSBStation();
				int index = cbCCProfile.getSelectionIndex();
				String profilename = null;
				if (index > -1)
					profilename = cbCCProfile.getItem(index);
				if (null != stations && stations.length > 0) {
					if (null != profilename) {
						if (null != handler) {
							handler
									.setCCProfileCommision(stations,
											profilename);
							AdminComposite.display(
									"Profile assigned Sucessfully",
									STATUS_SUCCESS, SUCCESS_FONT_COLOR);
							cbCCProfile.removeAll();
							if (ccComboNames != null) {
								cbCCProfile.setItems(ccComboNames);
							}
							((TreeComposite) treeCCCommission).clearTree();

							if (treeCCCommission != null) {
								treeCCCommission.dispose();
							}
							treeCCCommission = new TreeComposite(cvsCCComm,
									SWT.NONE, 350).loadComposite();
							treeCCCommission.setBounds(30, 50, 200, 400);

						}
					} else {
						AdminComposite.display("Please select a Profile",
								STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					}
				} else
					AdminComposite.display("Please select atleast One Station",
							STATUS_SUCCESS, SUCCESS_FONT_COLOR);

			} catch (Exception e) {
				AdminComposite.display("Profile Settings Failed",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				e.printStackTrace();
			}

		}

		/**
		 * 
		 */
		private void setDelivery() {
			String[] stations = null;
			stations = ((TreeComposite) treeDelivery).getSelectedMSBStation();

			String profilename = null;

			profilename = cbDCProfile.getText();
			if (stations != null && stations.length > 0) {
				if (profilename != null && !profilename.equals("")) {
					try {
						if (null != handler) {
							handler
									.setDCProfileCommision(stations,
											profilename);
							AdminComposite
									.display(
											"Delivery Commission  Profile Set Sucessfully",
											STATUS_SUCCESS, SUCCESS_FONT_COLOR);
							cbDCProfile.removeAll();
							if (comboDCNames != null) {
								cbDCProfile.setItems(comboDCNames);
							}

							((TreeComposite) treeDelivery).clearTree();
						}

					} catch (Exception e) {
						AdminComposite.display(
								"Delivery Commission Profile Setting Failed",
								STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						e.printStackTrace();
					}
				} else {
					AdminComposite.display("Please Select a Profile",
							STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				}
			} else {
				AdminComposite.display("Please select atleast a station",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			}

		}

		/**
		 * Method to setProfile Values
		 */
		private void setProfile() {
			String[] stations = null;
			try {
				stations = ((TreeComposite) treeBooking)
						.getSelectedMSBStation();
				int index = cbProfile.getSelectionIndex();
				String profilename = null;
				if (index > -1)
					profilename = cbProfile.getItem(index);
				if (null != stations && stations.length > 0) {
					if (null != profilename) {
						if (null != handler) {
							String date = EMPTY_STRING;
							SimpleDateFormat format = new SimpleDateFormat(
									"dd-MM-yyyy");
							Date effDate = null;
							if (!txtDate.getText().equals(EMPTY_STRING)) {
								date = txtDate.getText();

								if (!format.parse(date).equals(
										format.parseObject(SERVER_DATE))) {
									effDate = format.parse(date);
								} else {
									effDate = null;
								}
							}

							if (effDate != null) {
								handler.setBookingCommisionEffDate(stations,
										profilename, effDate);
								AdminComposite.display(
										"Profile assigned Sucessfully",
										STATUS_SUCCESS, SUCCESS_FONT_COLOR);
								cbProfile.removeAll();
								if (comboNames != null) {
									cbProfile.setItems(comboNames);
								}
								txtDate.setText(SERVER_DATE);
								((TreeComposite) treeBooking).clearTree();
								if (treeBooking != null) {
									treeBooking.dispose();
								}
								treeBooking = new TreeComposite(cvsBooking,
										SWT.NONE, 350).loadComposite();
								treeBooking.setBounds(30, 50, 200, 400);

							} else {
								handler.setBookingCommision(stations,
										profilename);
								AdminComposite.display(
										"Profile assigned Sucessfully",
										STATUS_SUCCESS, SUCCESS_FONT_COLOR);
								cbProfile.removeAll();
								if (comboNames != null) {
									cbProfile.setItems(comboNames);
								}
								txtDate.setText(SERVER_DATE);
								((TreeComposite) treeBooking).clearTree();

								if (treeBooking != null) {
									treeBooking.dispose();
								}
								treeBooking = new TreeComposite(cvsBooking,
										SWT.NONE, 350).loadComposite();
								treeBooking.setBounds(30, 50, 200, 400);

							}

						}
					} else {
						AdminComposite.display("Please select a Profile",
								STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					}
				} else
					AdminComposite.display("Please select atleast One Station",
							STATUS_SUCCESS, SUCCESS_FONT_COLOR);

			} catch (Exception e) {
				AdminComposite.display("Profile Settings Failed",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				e.printStackTrace();
			}

		}

		@Override
		public void focusGained(FocusEvent arg0) {

		}

		@Override
		public void focusLost(FocusEvent event) {
			Object source = event.getSource();
			if (source == txtInstallments
					&& !txtInstallments.getText().equals(EMPTY_STRING)
					&& !txtAmount.getText().equals(EMPTY_STRING)) {

				int rows = Integer.parseInt(txtInstallments.getText());
				try {
					// Max 48
					if (rows > 48) {
						txtInstallments.setText(String.valueOf(48));
						rows = 48;
					}
					createTable(cpttemp, rows);
					if (rdManual.getSelection()) {
						txtHint.setVisible(true);
						String amount = txtAmount.getText();
						if (!amount.equals(EMPTY_STRING)) {
							txtHint.setText(0 + " [" + amount + "]");
						}
						tblInstallment.forceFocus();

					} else {
						txtHint.setVisible(false);
					}

				} catch (Exception exception) {
					exception.printStackTrace();
				}

			}
		}

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

	private void populateCustomers() throws Exception {
		CustomerDTO[] customerDTO = handler.getCustomers();

		ArrayList<CustomerDTO> lstCustomers = new ArrayList<CustomerDTO>();
		if (customerDTO != null) {
			for (int i = 0; i < customerDTO.length; i++) {

				if (customerDTO[i].getQuotationId() == null
						&& customerDTO[i].getQuotation_type() != 3
						&& customerDTO[i].getTypeOfCustomer() != null
						&& customerDTO[i].getTypeOfCustomer().equalsIgnoreCase(
								"Consignor")) {
					// System.out.println("qt==>"++"name
					// "+customerDTO[i].getName());
					// cbCustomers.add(customerDTO[i].getName());

					lstCustomers.add(customerDTO[i]);
				}
			}

			int size = lstCustomers.size();
			if (size > 0) {
				sundryCustomers = lstCustomers.toArray(new CustomerDTO[size]);
			}
		}

	}

	private boolean isValidHLC(String[] stations) {
		if (stations == null || stations.length <= 0) {
			AdminComposite.display("Please select atleast one station",
					STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;
		} else if (cbCustomers.getText().equals(EMPTY_STRING)) {
			AdminComposite.display("Please select customer", STATUS_SUCCESS,
					SUCCESS_FONT_COLOR);
			return false;
		} else if (txtccArticle.getText().equals(EMPTY_STRING)) {
			AdminComposite.display("Please enter CC/Article value",
					STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;
		} else if (txtrefundArticle.getText().equals(EMPTY_STRING)) {
			AdminComposite.display("Please enter Refund/Article value",
					STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;
		}
		return true;
	}

	public static void populateStationCustomers(String[] stations)
			throws Exception {
		// CB_CUSTOMERS = cbCustomers.getItems();
		cbCustomers.removeAll();
		if (stations != null && stations.length > 0) {
			for (int i = 0; i < stations.length; i++) {
				for (int j = 0; j < sundryCustomers.length; j++) {

					if (stations[i].equals(sundryCustomers[j].getStation())) {
						cbCustomers.add(sundryCustomers[j].getName());
						// System.out.println("stn==>"+stations[i]+"stat===>"+sundryCustomers[j].getName());
					}
				}
			}
			String[] combo = cbCustomers.getItems();
			Arrays.sort(combo, String.CASE_INSENSITIVE_ORDER);
			cbCustomers.removeAll();
			if (combo != null) {
				int len = combo.length;
				cbCustomers.add("All");
				for (int k = 0; k < len; k++) {
					cbCustomers.add(combo[k]);
				}
			}

		}
	}

}
