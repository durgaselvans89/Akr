package hm.akr.admin.sundry;

/**
 * 
 * 
 * 
 */

import hm.akr.admin.sundry.handler.SundryHandler;
import hm.akr.admin.workspace.AdminComposite;
import hm.akr.common.FloatLimitValidation;
import hm.akr.common.IUIConstants;
import hm.akr.common.NumericExtendedValidation;
import hm.akr.common.NumericValidation;
import hm.akr.common.ValueRangeComposite;
import hm.akr.dto.RegularSundryDTO;
import hm.akr.msb.TreeComposite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
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
 * Class for RegularSundryComposite
 * 
 */
public class RegularSundryComposite extends Composite implements IUIConstants {

	private TabFolder tbfSundry;

	private TabItem tbiInsurance;

	private TabItem tbiBPI;

	private TabItem tbiLRCharges;

	private TabItem tbiBFInc;

	private TabItem tbiGSC;

	private TabItem tbiMinWeight;

	private TabItem tbiBFDec;

	private TabItem tbiView;

	private static final String BPI_TAB_NAME = "BPI";

	private static final String LRC_TAB_NAME = "LRC";

	private static final String GSC_TAB_NAME = "GSC";

	private static final String MINFT_TAB_NAME = "Min Frt";

	private static final String MINWT_TAB_NAME = "Min Wt";

	private static final String CC_TAB_NAME = "CC Settings";

	private static final String INS_TAB_NAME = "Insurance";

	private static final String WGHT_FGHT_TAB_NAME = "General Settings";

	private static final String BFINC_TAB_NAME = "Bas Frt Inc";

	private static final String BFDEC_TAB_NAME = "Bas Frt Dec";
	
	private static final String BFDEC = "Discounter";

	private static final String ARTICLE_TAB = "Article";

	private static final String VIEW_TAB_NAME = "Manage Sundry";

	private static final String EMPTY_STRING = "";

	private static final String DHC_TAB = "DHC";
	
	private static final String BFINCREMETER = "FIncrementer";
	
	TreeComposite tree = null;

	String[] ROUNDOFF_VALUES = { "1", "2", "3", "4", "5", "6", "7", "8", "9",
			"10" };

	private Composite cpttree;

	private ValueRangeComposite insurance;

	private Label lblName;

	private Text txtValue;

	private Label lblUnit;

	private Button btnSet;

	private Composite cptinsurance;

	private Composite cptGeneral;

	private Label lblRound;

	private Combo cbRound;

	private Label lblKG;

	private Text txtSTRange;

	private Text txtSTValue;

	private Text txtDistance;

	private Button btnUpdate;

	private Label lblHeader;

	SundryHandler handler = null;

	private TabItem tbiArticle;

	private TabItem tbiMinFt;

	private TabItem tbiMinWt;

	private Text txtCCLimit;

	private TabItem tbiDiscountor;

	private TabItem tbiDHC;

	private TabItem tbiIncrementer;

	/**
	 * Constructor Method
	 */
	public RegularSundryComposite(Composite cpt, int value) {
		super(cpt, value);
		
		try {
			handler = new SundryHandler();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Composite loadGeneralComposite() throws Exception {
		try {

			// GridData cptGd = new GridData();

			cptGeneral = new Composite(tbfSundry, SWT.NONE);
			cptGeneral.setSize(566, 355);

			{

				Group gpWeightRound = new Group(cptGeneral, SWT.NONE);
				gpWeightRound.setText("Weight Round Off");
				gpWeightRound.setBounds(102, 108, 212, 94);

				{
					lblRound = new Label(gpWeightRound, SWT.NONE);
					lblRound.setText("Rounded To");
					lblRound.setBounds(12, 42, 67, 20);
					lblRound.setFont(LABLE_FONT);
				}

				{
					cbRound = new Combo(gpWeightRound, SWT.READ_ONLY);
					cbRound.setItems(ROUNDOFF_VALUES);
					cbRound.setBounds(85, 41, 42, 22);
					cbRound.setFont(COMBO_FONT);
				}

				{
					lblKG = new Label(gpWeightRound, SWT.NONE);
					lblKG.setText("kg");
					lblKG.setBounds(132, 42, 24, 20);
					lblKG.setFont(LABLE_FONT);
				}

			}

			{

				Group gpMinWeight = new Group(cptGeneral, SWT.NONE);
				gpMinWeight.setText("Distance settings");
				gpMinWeight.setBounds(101, 227, 213, 94);

				{
					lblRound = new Label(gpMinWeight, SWT.NONE);
					lblRound.setText("TR - City");
					lblRound.setBounds(10, 42, 62, 18);
					lblRound.setFont(LABLE_FONT);
				}

				{
					txtDistance = new Text(gpMinWeight, SWT.BORDER);
					txtDistance.setBounds(78, 39, 60, 20);
					txtDistance.setFont(TEXT_FONT);
					txtDistance.addVerifyListener(new NumericValidation());

				}

				{
					lblKG = new Label(gpMinWeight, SWT.NONE);
					lblKG.setText("km");
					lblKG.setBounds(147, 39, 34, 26);
					lblKG.setFont(LABLE_FONT);

				}

			}

			{

				Group gpServiceTax = new Group(cptGeneral, SWT.NONE);
				gpServiceTax.setText("Service Tax Setting");
				gpServiceTax.setBounds(326, 112, 232, 90);

				{
					lblRound = new Label(gpServiceTax, SWT.NONE);
					lblRound.setText(">");
					lblRound.setBounds(10, 42, 13, 30);
					lblRound.setFont(SPECIAL_FONT);

				}
				{
					txtSTRange = new Text(gpServiceTax, SWT.BORDER);
					txtSTRange.setBounds(29, 42, 60, 20);
					txtSTRange.setFont(TEXT_FONT);
					txtSTRange.addVerifyListener(new NumericValidation());
				}
				{
					lblKG = new Label(gpServiceTax, SWT.NONE);
					lblKG.setText("Rs.");
					lblKG.setBounds(123, 43, 17, 17);
					lblKG.setFont(LABLE_FONT);
				}
				{
					txtSTValue = new Text(gpServiceTax, SWT.BORDER);
					txtSTValue.setBounds(144, 42, 60, 20);
					txtSTValue.setFont(TEXT_FONT);
					txtSTValue.addVerifyListener(new FloatLimitValidation(4));

				}
				/*
				 * { btnSetST = new Button(gpServiceTax, SWT.PUSH);
				 * btnSetST.setText("Set"); }
				 */
			}
			Label lblEmpty = new Label(cptGeneral, SWT.NONE);
			lblEmpty.setText("");
			lblEmpty.setFont(LABLE_FONT);

			btnUpdate = new Button(cptGeneral, SWT.PUSH);
			btnUpdate.setText("Update");
			btnUpdate.setBounds(331, 262, 57, 24);
			btnUpdate.setFont(BUTTON_FONT);

			btnUpdate.addSelectionListener(new SundryTabListener());

			loadGeneralValues();

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		return cptGeneral;

	}

	/**
	 * Method to load Composite
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
					tbiBPI = new TabItem(tbfSundry, SWT.NONE);
					tbiBPI.setText(BPI_TAB_NAME);

				}

				{
					tbiLRCharges = new TabItem(tbfSundry, SWT.NONE);
					tbiLRCharges.setText(LRC_TAB_NAME);
				}
				{
					tbiGSC = new TabItem(tbfSundry, SWT.NONE);
					tbiGSC.setText(GSC_TAB_NAME);
				}

				{
					tbiMinFt = new TabItem(tbfSundry, SWT.NONE);
					tbiMinFt.setText(MINFT_TAB_NAME);
				}
				{
					tbiMinWt = new TabItem(tbfSundry, SWT.NONE);
					tbiMinWt.setText(MINWT_TAB_NAME);
				}

				/*{

					tbiCC = new TabItem(tbfSundry, SWT.NONE);
					tbiCC.setText(CC_TAB_NAME);
				}*/

				{
					tbiInsurance = new TabItem(tbfSundry, SWT.NONE);
					tbiInsurance.setText(INS_TAB_NAME);
				}

				{
					tbiMinWeight = new TabItem(tbfSundry, SWT.NONE);
					tbiMinWeight.setText(WGHT_FGHT_TAB_NAME);
				}

				{
					tbiBFInc = new TabItem(tbfSundry, SWT.NONE);
					tbiBFInc.setText(BFINC_TAB_NAME);
				}

				{
					tbiBFDec = new TabItem(tbfSundry, SWT.NONE);
					tbiBFDec.setText(BFDEC_TAB_NAME);
				}
				
				{
					tbiDiscountor = new TabItem(tbfSundry, SWT.NONE);
					tbiDiscountor.setText(BFDEC);
				}
				
				{
					tbiIncrementer = new TabItem(tbfSundry, SWT.NONE);
					tbiIncrementer.setText(BFINCREMETER);
				}

				{
					tbiDHC = new TabItem(tbfSundry, SWT.NONE);
					tbiDHC.setText(DHC_TAB);
				}
				
				{
					tbiArticle = new TabItem(tbfSundry, SWT.NONE);
					tbiArticle.setText(ARTICLE_TAB);
				}

				{
					tbiView = new TabItem(tbfSundry, SWT.NONE);
					tbiView.setText(VIEW_TAB_NAME);
				}

				/*
				 * tbfSundry.setBounds(36, 29, 500, 500);
				 * tbfSundry.setBackground(new Color(Display.getCurrent(), 216,
				 * 216, 216));
				 */
				tbfSundry.setSelection(0);
				tbfSundry.addSelectionListener(new SundryTabListener());
				tree = new TreeComposite(tbfSundry, SWT.NONE, 300);

				insurance = new ValueRangeComposite(tbfSundry, SWT.NONE,
						"Insurance");

				lblName = new Label(tbfSundry, SWT.NONE);
				lblName.setFont(LABLE_FONT);

				txtValue = new Text(tbfSundry, SWT.BORDER);
				txtCCLimit = new Text(tbfSundry, SWT.BORDER);
				lblUnit = new Label(tbfSundry, SWT.NONE);
				lblUnit.setFont(LABLE_FONT);

				btnSet = new Button(tbfSundry, SWT.NONE);
				btnSet.setFont(BUTTON_FONT);

				lblHeader = new Label(tbfSundry, SWT.NONE);
				lblHeader.setBounds(615, 30, 170, 30);
				lblHeader.setVisible(false);
				lblHeader.setAlignment(SWT.RIGHT);
				lblHeader.setFont(HEAD_FONT);
				// lblHeader.setForeground(HEAD_FONT_COLOR);

			}

			if (cptinsurance != null)
				cptinsurance.setVisible(false);

			cpttree = tree.loadComposite();
			cpttree.setVisible(false);
			cpttree.setBounds(30, 120, 200, 400);

			lblName.setBounds(260, 240, 70, 20);
			lblName.setFont(LABLE_FONT);

			txtCCLimit.setBounds(260, 265, 60, 24);
			txtCCLimit.setFont(TEXT_FONT);
			txtCCLimit.addVerifyListener(new NumericExtendedValidation());

			txtValue.setBounds(260, 265, 60, 24);
			txtValue.setFont(TEXT_FONT);

			txtValue.addVerifyListener(new NumericValidation());

			/*
			 * lblUnit.setBounds(330, 265, 40, 40); lblUnit.setText("per Ton.");
			 * lblUnit.setFont(LABLE_FONT);
			 */

			btnSet.setBounds(370, 265, 50, 27);
			btnSet.setText("Set");
			btnSet.setFont(BUTTON_FONT);
			btnSet.addSelectionListener(new SundryTabListener());
			setVisibleComponenets(false);

			{
				tbiBPI.setControl(new OptionComposite(tbfSundry, SWT.BORDER,
						BPI_TAB_NAME).loadComposite());
				cpttree.setVisible(false);

				if (cptinsurance != null)
					cptinsurance.setVisible(false);
				setVisibleComponenets(false);
				lblHeader.setVisible(false);
				txtCCLimit.setVisible(false);
			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		return this;
	}

	/**
	 * Method to Manage components Visible
	 * 
	 * @param disable
	 */
	private void setVisibleComponenets(boolean disable) {
		cpttree.setVisible(disable);
		lblName.setVisible(disable);
		txtValue.setVisible(disable);
		lblUnit.setVisible(disable);
		btnSet.setVisible(disable);

	}

	/**
	 * 
	 * Listener for RegularSundry Tab
	 * 
	 */
	public class SundryTabListener implements SelectionListener {

		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {

		}

		@Override
		public void widgetSelected(SelectionEvent event) {
			Object object = event.getSource();

			try {

				if (tbfSundry == object) {
					AdminComposite.display("STATUS", STATUS_SUCCESS,
							SUCCESS_FONT_COLOR);
					String selectedTab = tbfSundry.getSelection()[0].getText();
					if (BPI_TAB_NAME.equals(selectedTab)) {
						//tree.clearTree();
						tbiBPI.setControl(new OptionComposite(tbfSundry,
								SWT.BORDER, selectedTab).loadComposite());
						cpttree.setVisible(false);

						if (cptinsurance != null)
							cptinsurance.setVisible(false);
						setVisibleComponenets(false);
						lblHeader.setVisible(false);
						txtCCLimit.setVisible(false);

					} else if (LRC_TAB_NAME.equals(selectedTab)) {
						tbiLRCharges.setControl(new OptionComposite(tbfSundry,
								SWT.NONE, selectedTab).loadComposite());
						if (cptinsurance != null)
							cptinsurance.setVisible(false);
						cpttree.setVisible(false);
						setVisibleComponenets(false);
						lblHeader.setVisible(false);
						txtCCLimit.setVisible(false);
					} else if (GSC_TAB_NAME.equals(selectedTab)) {
						tbiGSC.setControl(new OptionComposite(tbfSundry,
								SWT.NONE, selectedTab).loadComposite());

						cpttree.setVisible(false);
						setVisibleComponenets(false);
						if (cptinsurance != null)
							cptinsurance.setVisible(false);
						lblHeader.setVisible(false);
						txtCCLimit.setVisible(false);
					} else if (BFINC_TAB_NAME.equals(selectedTab)) {
						txtValue.setText("");
						txtValue.setTextLimit(3);
						lblName.setText("Increment %");

						setVisibleComponenets(false);
						tree = new TreeComposite(tbfSundry, SWT.NONE, 300);
						cpttree = tree.loadComposite();
						cpttree.setVisible(true);
						cpttree.setBounds(30, 120, 200, 400);
						lblName.setVisible(true);
						txtValue.setVisible(true);
						lblUnit.setVisible(true);
						btnSet.setVisible(true);

						if (cptinsurance != null)
							cptinsurance.setVisible(false);
						lblHeader.setVisible(true);
						lblHeader.setText(BFINC_TAB_NAME);
						txtCCLimit.setVisible(false);
						txtValue.setEnabled(true);

					} else if (WGHT_FGHT_TAB_NAME.equals(selectedTab)) {
						tbiMinWeight.setControl(loadGeneralComposite());
						if (cptinsurance != null)
							cptinsurance.setVisible(false);
						setVisibleComponenets(false);
						lblHeader.setVisible(true);
						lblHeader.setText(WGHT_FGHT_TAB_NAME);
						txtCCLimit.setVisible(false);
					} else if (BFDEC_TAB_NAME.equals(selectedTab)) {
						txtValue.setText("");
						txtValue.setTextLimit(3);
						lblName.setText("Decrement %");
						if (cptinsurance != null)
							cptinsurance.setVisible(false);
						setVisibleComponenets(false);
						tree = new TreeComposite(tbfSundry, SWT.NONE, 300);
						cpttree = tree.loadComposite();
						cpttree.setVisible(true);
						cpttree.setBounds(30, 120, 200, 400);
						lblName.setVisible(true);
						txtValue.setVisible(true);
						lblUnit.setVisible(true);
						btnSet.setVisible(true);
						lblHeader.setText(BFDEC_TAB_NAME);
						lblHeader.setVisible(true);
						txtCCLimit.setVisible(false);
						txtValue.setEnabled(true);
					}else if (BFDEC.equals(selectedTab)) {
						tbiDiscountor.setControl(new Discounter(tbfSundry,
								SWT.NONE).loadComposite());
						tbfSundry.layout();
						if (cptinsurance != null)
							cptinsurance.setVisible(false);
						lblHeader.setVisible(false);
						setVisibleComponenets(false);
						txtCCLimit.setVisible(false);
					}else if (BFINCREMETER.equals(selectedTab)) {
						tbiIncrementer.setControl(new Incrementer(tbfSundry,
								SWT.NONE).loadComposite());
						tbfSundry.layout();
						if (cptinsurance != null)
							cptinsurance.setVisible(false);
						lblHeader.setVisible(false);
						setVisibleComponenets(false);
						txtCCLimit.setVisible(false);
					}else if (DHC_TAB.equals(selectedTab)) {
						tbiDHC.setControl(new DhcComposite(tbfSundry,
								SWT.NONE).loadComposite());
						tbfSundry.layout();
						if (cptinsurance != null)
							cptinsurance.setVisible(false);
						lblHeader.setVisible(false);
						setVisibleComponenets(false);
						txtCCLimit.setVisible(false);
					}else if (MINFT_TAB_NAME.equals(selectedTab)
							|| MINWT_TAB_NAME.equals(selectedTab)) {
						txtValue.setText("");
						txtValue.setTextLimit(3);
						lblName.setText("Value");

						setVisibleComponenets(false);
						tree = new TreeComposite(tbfSundry, SWT.NONE, 300);
						cpttree = tree.loadComposite();
						cpttree.setVisible(true);
						cpttree.setBounds(30, 120, 200, 400);
						lblName.setVisible(true);
						txtValue.setVisible(true);
						lblUnit.setVisible(true);
						btnSet.setVisible(true);

						if (cptinsurance != null)
							cptinsurance.setVisible(false);
						lblHeader.setVisible(true);
						lblHeader.setText(selectedTab);
						txtCCLimit.setVisible(false);
						txtValue.setEnabled(true);

					} else if (INS_TAB_NAME.equals(selectedTab)) {
						/*
						 * tbiInsurance.setControl(new SelectedTabComposite(
						 * tbfSundry, SWT.NONE, selectedTab) .loadComposite());
						 */

						cptinsurance = insurance.loadComposite();
						cptinsurance.setBounds(30, 70, 720, 320);

						cptinsurance.setVisible(true);
						cpttree.setVisible(false);
						lblHeader.setText(INS_TAB_NAME);
						lblHeader.setVisible(true);
					} else if (VIEW_TAB_NAME.equals(selectedTab)) {
						tbiView.setControl(new ManageSundryComposite(tbfSundry,
								SWT.NONE).loadComposite());
						tbfSundry.layout();
						if (cptinsurance != null)
							cptinsurance.setVisible(false);
						lblHeader.setVisible(false);
						setVisibleComponenets(false);
						txtCCLimit.setVisible(false);
					} else if (CC_TAB_NAME.equals(selectedTab)) {

						txtValue.setEnabled(false);

						txtCCLimit.setTextLimit(6);
						txtCCLimit.setVisible(true);
						/*txtValue.setText("");
						txtValue.setTextLimit(6);*/

						lblName.setText("CC Limit");
						txtCCLimit.addKeyListener(new KeyListener() {

							@Override
							public void keyPressed(KeyEvent arg0) {
								// TODO Auto-generated method stub

							}

							@Override
							public void keyReleased(KeyEvent event) {
								if (event.character == 'a'
										|| event.character == 'A') {
									txtCCLimit.setText("All");
								}

							}

						});

						setVisibleComponenets(false);
						tree = new TreeComposite(tbfSundry, SWT.NONE, 300);
						cpttree = tree.loadComposite();
						cpttree.setVisible(true);
						cpttree.setBounds(30, 120, 200, 400);
						lblName.setVisible(true);

						lblUnit.setVisible(true);
						btnSet.setVisible(true);

						if (cptinsurance != null)
							cptinsurance.setVisible(false);
						lblHeader.setVisible(true);
						lblHeader.setText(CC_TAB_NAME);

					} else if (ARTICLE_TAB.equals(selectedTab)) {
						tbiArticle.setControl(new ArticleSundry(tbfSundry,
								SWT.NONE).loadComposite());
						tbfSundry.layout();
						if (cptinsurance != null)
							cptinsurance.setVisible(false);
						lblHeader.setVisible(false);
						setVisibleComponenets(false);
						txtCCLimit.setVisible(false);
					}

				} else if (object == btnSet) {
					AdminComposite.display("", STATUS_SUCCESS,
							SUCCESS_FONT_COLOR);
					String selectedTab = tbfSundry.getSelection()[0].getText();
					if (selectedTab.equals(BFINC_TAB_NAME)) {
						int bfiincrement = 0;
						String[] stations = null;
						try {
							stations = tree.getSelectedMSBStation();
							if (isvalidMSB(stations, txtValue.getText(),
									BFINC_TAB_NAME)) {
								bfiincrement = Integer.parseInt(txtValue
										.getText());
								if (bfiincrement <= 100) {
									if (null != handler) {
										handler.setBFIIncrement(stations,
												bfiincrement);
										AdminComposite
												.display(
														"BF Increment Value Set Successfully",
														STATUS_SUCCESS,
														SUCCESS_FONT_COLOR);
										txtValue.setText(EMPTY_STRING);
										tree.clearTree();
									}

								} else {
									AdminComposite
											.display(
													"Please Enter the Value between 0 and 100",
													STATUS_SUCCESS,
													SUCCESS_FONT_COLOR);
								}
							}

						} catch (Exception e) {
							AdminComposite.display(
									"BF Increment Value not set",
									STATUS_SUCCESS, SUCCESS_FONT_COLOR);
							e.printStackTrace();
						}

					} else if (selectedTab.equals(BFDEC_TAB_NAME)) {
						int bfidecrement = 0;
						String[] stations = null;
						try {
							stations = tree.getSelectedMSBStation();
							if (isvalidMSB(stations, txtValue.getText(),
									BFDEC_TAB_NAME)) {
								bfidecrement = Integer.parseInt(txtValue
										.getText());
								if (bfidecrement <= 100) {
									if (null != handler) {

										handler.setBFIDecrement(stations,
												bfidecrement);
										AdminComposite
												.display(
														"BF Decrement Value Set Successfully",
														STATUS_SUCCESS,
														SUCCESS_FONT_COLOR);
										txtValue.setText(EMPTY_STRING);
										tree.clearTree();
									}

								} else {
									AdminComposite
											.display(
													"Please Enter the Value between 0 and 100",
													STATUS_SUCCESS,
													SUCCESS_FONT_COLOR);
								}

							}
						} catch (Exception e) {
							AdminComposite.display(
									"BF Decrement Value not set",
									STATUS_SUCCESS, SUCCESS_FONT_COLOR);
							e.printStackTrace();
						}

					} else if (selectedTab.equals(CC_TAB_NAME)) {

						int ccvalue = 0;
						String[] stations = null;
						String ccLimit = "";
						try {
							stations = tree.getSelectedMSBStation();
							if (isvalidMSB(stations, txtCCLimit.getText(),
									CC_TAB_NAME)) {

								ccLimit = txtCCLimit.getText();

								if (!ccLimit.equals(EMPTY_STRING)) {

									if (ccLimit.equalsIgnoreCase("All")) {
										ccvalue = -1;
									} else {
										ccvalue = Integer.parseInt(ccLimit);
									}

									if (null != handler) {

										handler.setCCSundryLimit(stations,
												ccvalue);
										AdminComposite
												.display(
														"CC Sundry Limit Set Successfully",
														STATUS_SUCCESS,
														SUCCESS_FONT_COLOR);
										txtCCLimit.setText(EMPTY_STRING);
										tree.clearTree();
									}
								}

							}
						} catch (NumberFormatException ne) {
							if(ne.getMessage().startsWith("For input string")){
								AdminComposite.display("Please give valid value",
										STATUS_SUCCESS, SUCCESS_FONT_COLOR);
							}							
							
						}catch (Exception e) {
							AdminComposite.display("CC Sundry Limit not set",
									STATUS_SUCCESS, SUCCESS_FONT_COLOR);
							e.printStackTrace();
						}

					} else if (selectedTab.equals(MINFT_TAB_NAME)
							|| selectedTab.equals(MINWT_TAB_NAME)) {
						int minFreight = 0;
						String[] stations = null;
						try {
							stations = tree.getSelectedMSBStation();
							if (isvalidMSB(stations, txtValue.getText(),
									selectedTab)) {
								minFreight = Integer.parseInt(txtValue
										.getText());

								if (null != handler) {
									if (selectedTab.equals(MINFT_TAB_NAME)) {
										handler.setMinimumFreight(stations,
												minFreight);
										AdminComposite
												.display(
														"Minimum Freight Value Set Successfully",
														STATUS_SUCCESS,
														SUCCESS_FONT_COLOR);

										tree.clearTree();
										txtValue.setText(EMPTY_STRING);

									} else {
										handler.setMinimumWeight(stations,
												minFreight);
										AdminComposite
												.display(
														"Minimum Weight Value Set Successfully",
														STATUS_SUCCESS,
														SUCCESS_FONT_COLOR);
										tree.clearTree();
										txtValue.setText(EMPTY_STRING);
									}
								}

							}
						} catch (Exception e) {

							AdminComposite.display("The  Value not set",
									STATUS_SUCCESS, SUCCESS_FONT_COLOR);
							e.printStackTrace();
						}

					}

				} else if (object == btnUpdate) {
					AdminComposite.display("", STATUS_SUCCESS,
							SUCCESS_FONT_COLOR);
					RegularSundryDTO rsDTO = new RegularSundryDTO();

					if ("" != cbRound.getText() && null != cbRound.getText())
						rsDTO.setWeightRoundOff(Integer.parseInt(cbRound
								.getText()));

					if ("" != txtDistance.getText()
							&& null != txtDistance.getText())
						rsDTO.setDistance(Integer.parseInt(txtDistance
								.getText()));

					if ("" != txtSTRange.getText()
							&& null != txtSTRange.getText())
						rsDTO.setStaxLimit(Integer.parseInt(txtSTRange
								.getText()));

					if ("" != txtSTValue.getText()
							&& null != txtSTValue.getText())
						rsDTO.setServiceTax(Float.parseFloat(txtSTValue
								.getText()));

					try {
						handler.setGeneralSettings(rsDTO);
						AdminComposite.display("Updation Successfull",
								STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					} catch (Exception exception) {
						AdminComposite.display("Updation Failed",
								STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						exception.printStackTrace();
					}
				}

			} catch (Exception exception) {
				AdminComposite.display("Updation Failed", STATUS_SUCCESS,
						SUCCESS_FONT_COLOR);
				exception.printStackTrace();
			}

		}

		/**
		 * Method to validate MSB
		 * 
		 * @param stations
		 * @param text
		 * @return
		 */
		private boolean isvalidMSB(String[] stations, String text,
				String selectedTab) {
			if (stations == null || stations.length <= 0) {
				AdminComposite.display("Please select atleast one station",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				return false;
			} else if (text.length() == 0) {
				AdminComposite.display(
						"Please enter " + selectedTab + " VALUE",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				return false;
			}
			return true;
		}

	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	private RegularSundryDTO loadGeneralValues() throws Exception {
		RegularSundryDTO dto = null;
		dto = handler.getGeneralSettings();

		if (dto != null) {
			/*System.out.println("min ft " + dto.getMinFreight() + "min wt "
					+ dto.getMinWeight() + "s lim " + dto.getStaxLimit()
					+ "s v " + dto.getServiceTax() + "wt round "
					+ dto.getWeightRoundOff());*/

			txtDistance.setText(String.valueOf((int) dto.getDistance()));
			txtSTRange.setText(String.valueOf(dto.getStaxLimit()));
			txtSTValue.setText(String.valueOf(dto.getServiceTax()));
			//System.out.println(dto.getWeightRoundOff());
			cbRound.setText(String.valueOf(dto.getWeightRoundOff()));
		}
		return dto;
	}

}
