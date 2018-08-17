package hm.akr.admin.commission;

import hm.akr.admin.commission.handler.AgencyCommisionHandler;
import hm.akr.admin.workspace.AdminComposite;
import hm.akr.common.FloatLimitValidation;
import hm.akr.common.IUIConstants;
import hm.akr.common.NumericExtendedValidation;
import hm.akr.common.NumericValidation;
import hm.akr.dto.CardDTO;
import hm.akr.dto.ProfileDTO;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
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
 * @author
 * 
 */
public class BookingProfileComposite extends Composite implements IUIConstants {

	/*{

		SWTResourceManager.registerResourceUser(this);
	}*/

	public Color redColor = null;

	private Button btnnew;	

	private Button btnview;	

	private Button btnCreate;

	private Combo cbProfileName;

	private Label lblprofilename;

	private Label lblxy;

	private Text txtquotationbl4;

	private Text txtquotationbl3;

	private Label lblcolumn1;

	private Label lblcolumn2;

	private Label lblcolumn3;

	private Label lblcolumn4;

	private Label lblregular;

	private Text txtregular1;

	private Text txtregular2;

	private Text txtregular3;

	private Text txtregular4;
	private Label lblPerton;
	private Button chkCommodity;
	private Button chkSpecial;
	

	private Label lblSeb1;
	
	private Label lblSep1;

	private Text txtspecial4;

	private Text txtcommodity4;

	private Text txtquotationpt4;

	private Text txtquotationpt3;

	private Text txtquotationbl2;

	private Text txtquotationbl1;

	private Label lblquotationbl;

	private Label lblquotation;

	private Text txtquotationpt1;

	private Text txtquotationpt2;

	private Text txtcommodity2;

	private Text txtcommodity3;

	private Text txtspecial3;

	private Text txtspecial2;

	private Text txtspecial1;

	private Text txtcommodity1;

	private Label lblcommodity;

	private Label lblspecial;

	private Canvas canvas2;

	private TabItem tabItem1;

	private TabFolder tabFolder1;

	private String REGULAR = "regular";

	private String SPECIAL = "special";

	private String COMMODITY = "commodity";

	private String QUOTATION_IN_PDTP = "quotation in PD/TP";

	private String QUOTATION_BL = "BILLING LRs";
	
	private String HLC_PDTP = "HLC in PD/TP";

	private ProfileDTO dto = null;

	private AgencyCommisionHandler handler;

	private String EMPTY_STRING = "";

	private DecimalFormat decimalFormat;

	private Text txtHLCpdtp1;

	private Text txtHLCpdtp2;

	private Text txtHLCpdtp3;

	private Text txtHLCpdtp4;

	private Label lblHLCpdtp;

	private Button btnDelete;

	private Text txtProfileId;
	
	String[] comboProfiles = null;

	private TabItem tabItem2;

	private Canvas canvas3;

	private Button btnCCCreate;

	private Button btnCCview;

	private Button btnCCDelete;

	private Text txtCCProfileId;

	private Combo cbCCProfileName;

	private Text txtCCLimit;

	private Text txtCCConsider;

	private Text txtCCRefund;

	private TabItem tabItem3;

	private Canvas canvas4;

	private Label lblDC;

	private Combo cbDCProfileName;

	private Text txtDC;

	private Text txtDCProfileId;

	private Button btnDCCreate;

	private Button btnDCview;

	private Button btnDCDelete;

	/**
	 * Constructor method
	 * 
	 * @param shell
	 */

	public BookingProfileComposite(Composite composite, int swtValue)
			throws Exception {
		super(composite, swtValue);
		handler = new AgencyCommisionHandler();
	}

	/**
	 * 
	 * @return
	 */
	public Composite loadComposite() throws Exception {

		try {			
			
			this.setSize(818, 600);
			this.setDragDetect(false);

			{
				btnnew = new Button(this, SWT.NONE);				
				btnnew.setBounds(700, 23, 60, 25);
				btnnew.setText("New");
				btnnew.addSelectionListener(new AgentListener());
				btnnew.setFont(BUTTON_FONT);
			}
			{

				tabFolder1 = new TabFolder(this, SWT.NONE);
				tabFolder1.setBounds(30,50,750,450);
				tabFolder1.addSelectionListener(new AgentListener());
				{
					tabItem1 = new TabItem(tabFolder1, SWT.NONE);
					tabItem1.setText("COMMISION");
					{
						canvas2 = new Canvas(tabFolder1, SWT.NONE);
						
						tabItem1.setControl(canvas2);
						{
							lblspecial = new Label(canvas2, SWT.NONE);
							lblspecial.setText("Special");
							lblspecial.setAlignment(SWT.RIGHT);
							lblspecial.setBounds(126, 145, 100, 28);
							lblspecial.setFont(LABLE_FONT);
						}
						{
							lblcommodity = new Label(canvas2, SWT.NONE);
							lblcommodity.setText("Commodity");
							lblcommodity.setAlignment(SWT.RIGHT);
							lblcommodity.setBounds(126, 186, 100, 28);
							lblcommodity.setFont(LABLE_FONT);
						}
						{
							cbProfileName = new Combo(canvas2, SWT.BORDER);
							cbProfileName.setBounds(377, 15, 189, 26);
							cbProfileName.setFont(COMBO_FONT);
						}
						{
							txtregular1 = new Text(canvas2, SWT.BORDER);
							txtregular1.setBounds(252, 103, 100, 22);
							txtregular1.setFont(TEXT_FONT);							
							txtregular1.addVerifyListener(new FloatLimitValidation());														
						}
						{
							txtregular2 = new Text(canvas2, SWT.BORDER);
							txtregular2.setBounds(377, 103, 100, 22);
							txtregular2.setFont(TEXT_FONT);							
							txtregular2.addVerifyListener(new FloatLimitValidation());														
						}
						{
							txtregular3 = new Text(canvas2, SWT.BORDER);
							txtregular3.setBounds(502, 103, 100, 22);
							txtregular3.setFont(TEXT_FONT);							
							txtregular3.addVerifyListener(new FloatLimitValidation());
						}
						{
							txtregular4 = new Text(canvas2, SWT.BORDER);
							txtregular4.setBounds(627, 103, 100, 22);
							txtregular4.setFont(TEXT_FONT);							
							txtregular4.addVerifyListener(new FloatLimitValidation());							
						}
						{
							txtspecial1 = new Text(canvas2, SWT.BORDER);
							txtspecial1.setBounds(252, 145, 100, 22);
							txtspecial1.setFont(TEXT_FONT);							
							txtspecial1.addVerifyListener(new FloatLimitValidation());														
						}
						{
							txtspecial2 = new Text(canvas2, SWT.BORDER);
							txtspecial2.setBounds(377, 145, 100, 22);
							txtspecial2.setFont(TEXT_FONT);							
							txtspecial2.addVerifyListener(new FloatLimitValidation());							
						}
						{
							txtspecial3 = new Text(canvas2, SWT.BORDER);
							txtspecial3.setBounds(502, 145, 100, 22);
							txtspecial3.setFont(TEXT_FONT);							
							txtspecial3.addVerifyListener(new FloatLimitValidation());
							
						}
						{
							txtspecial4 = new Text(canvas2, SWT.BORDER);
							txtspecial4.setBounds(627, 145, 100, 22);
							txtspecial4.setFont(TEXT_FONT);							
							txtspecial4.addVerifyListener(new FloatLimitValidation());
							
						}
						{
							txtcommodity1 = new Text(canvas2, SWT.BORDER);
							txtcommodity1.setBounds(252, 187, 100, 22);
							txtcommodity1.setFont(TEXT_FONT);
							txtcommodity1.addVerifyListener(new FloatLimitValidation());
							
						}
						{
							txtcommodity2 = new Text(canvas2, SWT.BORDER);
							txtcommodity2.setBounds(377, 187, 100, 22);
							txtcommodity2.setFont(TEXT_FONT);							
							txtcommodity2.addVerifyListener(new FloatLimitValidation());
							
						}
						{
							txtcommodity3 = new Text(canvas2, SWT.BORDER);
							txtcommodity3.setBounds(502, 187, 100, 22);
							txtcommodity3.setFont(TEXT_FONT);
							txtcommodity3.addVerifyListener(new FloatLimitValidation());
							
						}
						{
							txtcommodity4 = new Text(canvas2, SWT.BORDER);
							txtcommodity4.setBounds(627, 187, 100, 22);
							txtcommodity4.setFont(TEXT_FONT);							
							txtcommodity4.addVerifyListener(new FloatLimitValidation());
														
						}
						{
							txtHLCpdtp1 = new Text(canvas2, SWT.BORDER);
							txtHLCpdtp1.setBounds(252, 229, 100, 22);
							txtHLCpdtp1.setFont(TEXT_FONT);							
							txtHLCpdtp1.addVerifyListener(new FloatLimitValidation());
							
						}
						{
							txtHLCpdtp2 = new Text(canvas2, SWT.BORDER);
							txtHLCpdtp2.setBounds(379, 229, 100, 22);
							txtHLCpdtp2.setFont(TEXT_FONT);							
							txtHLCpdtp2.addVerifyListener(new FloatLimitValidation());
							
						}
						{
							txtHLCpdtp3 = new Text(canvas2, SWT.BORDER);
							txtHLCpdtp3.setBounds(504, 229, 100, 22);
							txtHLCpdtp3.setFont(TEXT_FONT);							
							txtHLCpdtp3.addVerifyListener(new FloatLimitValidation());
							
						}
						{
							txtHLCpdtp4 = new Text(canvas2, SWT.BORDER);
							txtHLCpdtp4.setBounds(629, 229, 100, 22);
							txtHLCpdtp4.setFont(TEXT_FONT);							
							txtHLCpdtp4.addVerifyListener(new FloatLimitValidation());
							
						}
						
						{
							lblHLCpdtp = new Label(canvas2, SWT.NONE);
							lblHLCpdtp.setText("HLC In PD/TP");
							lblHLCpdtp.setAlignment(SWT.RIGHT);
							lblHLCpdtp.setBounds(127, 230, 100, 31);
							lblHLCpdtp.setFont(LABLE_FONT);
						}
						{
							txtquotationpt1 = new Text(canvas2, SWT.BORDER);
							txtquotationpt1.setBounds(252, 276, 100, 22);
							txtquotationpt1.setFont(TEXT_FONT);
							txtquotationpt1.addVerifyListener(new FloatLimitValidation());
														
						}
						{
							txtquotationpt2 = new Text(canvas2, SWT.BORDER);
							txtquotationpt2.setBounds(377, 276, 100, 22);
							txtquotationpt2.setFont(TEXT_FONT);							
							txtquotationpt2.addVerifyListener(new FloatLimitValidation());
							
						}
						{
							txtquotationpt3 = new Text(canvas2, SWT.BORDER);
							txtquotationpt3.setBounds(502, 276, 100, 22);
							txtquotationpt3.setFont(TEXT_FONT);							
							txtquotationpt3.addVerifyListener(new FloatLimitValidation());
							
						}
						{
							txtquotationpt4 = new Text(canvas2, SWT.BORDER);
							txtquotationpt4.setBounds(627, 276, 100, 22);
							txtquotationpt4.setFont(TEXT_FONT);							
							txtquotationpt4.addVerifyListener(new FloatLimitValidation());
							
						}
						{
							txtquotationbl1 = new Text(canvas2, SWT.BORDER);
							txtquotationbl1.setBounds(252, 320, 100, 22);
							txtquotationbl1.setFont(TEXT_FONT);							
							txtquotationbl1.addVerifyListener(new FloatLimitValidation());
							
						}
						{
							txtquotationbl2 = new Text(canvas2, SWT.BORDER);
							txtquotationbl2.setBounds(377, 320, 100, 22);
							txtquotationbl2.setFont(TEXT_FONT);							
							txtquotationbl2.addVerifyListener(new FloatLimitValidation());
							
						}
						{
							txtquotationbl3 = new Text(canvas2, SWT.BORDER);
							txtquotationbl3.setBounds(502, 320, 100, 22);
							txtquotationbl3.setFont(TEXT_FONT);							
							txtquotationbl3.addVerifyListener(new FloatLimitValidation());
							
						}
						{
							txtquotationbl4 = new Text(canvas2, SWT.BORDER);
							txtquotationbl4.setBounds(627, 320, 100, 22);
							txtquotationbl4.setFont(TEXT_FONT);							
							txtquotationbl4.addVerifyListener(new FloatLimitValidation());
							
						}
						
						
						
						{
							lblquotation = new Label(canvas2, SWT.NONE);
							lblquotation.setText("Quotation In PD/TP");
							lblquotation.setAlignment(SWT.RIGHT);
							lblquotation.setBounds(126, 278, 100, 28);
							lblquotation.setFont(LABLE_FONT);
						}
						{
							lblquotationbl = new Label(canvas2, SWT.NONE);
							lblquotationbl.setText("BILLING LRs");
							lblquotationbl.setAlignment(SWT.RIGHT);
							lblquotationbl.setBounds(126, 321, 100, 28);
							lblquotationbl.setFont(LABLE_FONT);
						}
						{
							lblregular = new Label(canvas2, SWT.NONE);
							lblregular.setText("Regular");
							lblregular.setAlignment(SWT.RIGHT);
							lblregular.setBounds(126, 102, 100, 28);
							lblregular.setFont(LABLE_FONT);

						}
						{
							lblcolumn4 = new Label(canvas2, SWT.NONE);
							lblcolumn4.setText("Above 20% Discount");
							lblcolumn4.setBounds(627, 64, 100, 22);
							lblcolumn4.setFont(LABLE_FONT);
						}
						{
							lblcolumn3 = new Label(canvas2, SWT.NONE);
							lblcolumn3.setText("Upto 20% Discount");
							lblcolumn3.setBounds(502, 64, 100, 22);
							lblcolumn3.setFont(LABLE_FONT);
						}
						{
							lblcolumn2 = new Label(canvas2, SWT.NONE);
							lblcolumn2.setText("Card Rate");
							lblcolumn2.setBounds(377, 64, 100, 22);
							lblcolumn2.setFont(LABLE_FONT);
						}
						{
							lblcolumn1 = new Label(canvas2, SWT.NONE);
							lblcolumn1.setText("Above Card Rate");
							lblcolumn1.setBounds(252, 64, 100, 22);
							lblcolumn1.setFont(LABLE_FONT);
						}
						{
							lblxy = new Label(canvas2, SWT.NONE);
							lblxy.setText("Type/Rate");
							lblxy.setAlignment(SWT.RIGHT);
							lblxy.setBounds(126, 64, 100, 22);
							lblxy.setFont(LABLE_FONT);
						}
						{
							lblprofilename = new Label(canvas2, SWT.NONE);
							lblprofilename.setText("Profile Name");
							lblprofilename.setBounds(252, 19, 100, 23);
							lblprofilename.setAlignment(SWT.DOWN);
							lblprofilename.setFont(LABLE_FONT);

						}
						{
							btnCreate = new Button(canvas2, SWT.PUSH
									| SWT.CENTER);
							btnCreate.setText("Create");
							btnCreate.setBounds(660, 373, 60, 25);
							btnCreate.addSelectionListener(new AgentListener());
							btnCreate.setFont(BUTTON_FONT);
						}

						{
							btnview = new Button(canvas2, SWT.PUSH | SWT.CENTER);
							btnview.setText("View");
							btnview.setBounds(596, 12, 60, 25);
							btnview.addSelectionListener(new AgentListener());
							btnview.setFont(BUTTON_FONT);
						}
						
						{
							btnDelete = new Button(canvas2, SWT.PUSH | SWT.CENTER);
							btnDelete.setText("Delete");
							btnDelete.setBounds(670, 12, 50, 25);
							btnDelete.addSelectionListener(new AgentListener());
							btnDelete.setFont(BUTTON_FONT);
						}
						
						
						{
							txtProfileId = new Text(canvas2, SWT.BORDER);
							txtProfileId.setBounds(10, 12, 100, 22);
							txtProfileId.setVisible(false);								
						}
						
						{
							lblSep1 = new Label(canvas2, SWT.SEPARATOR
									| SWT.HORIZONTAL);
							lblSep1.setBounds(173, 82, 574, 15);
							lblSep1.setFont(LABLE_FONT);
						}
						{
							lblSeb1 = new Label(canvas2, SWT.SEPARATOR);
							lblSeb1.setBounds(209, 60, 61, 290);
							lblSeb1.setFont(LABLE_FONT);
						}

					}
				}
				{
					tabItem2 = new TabItem(tabFolder1, SWT.NONE);
					tabItem2.setText("CC COMMISION");
					{
						canvas3 = new Canvas(tabFolder1, SWT.NONE);
						
						tabItem2.setControl(canvas3);
						
						{
							{
								lblregular = new Label(canvas3, SWT.NONE);
								lblregular.setText("CC Limit");
								lblregular.setAlignment(SWT.RIGHT);
								lblregular.setBounds(254, 97, 64, 15);
								lblregular.setFont(LABLE_FONT);
							}
							
							{
								lblspecial = new Label(canvas3, SWT.NONE);
								lblspecial.setText("CC Consider");
								lblspecial.setAlignment(SWT.RIGHT);
								lblspecial.setBounds(379, 97, 72, 20);
								lblspecial.setFont(LABLE_FONT);
							}
							{
								lblcommodity = new Label(canvas3, SWT.NONE);
								lblcommodity.setText("CC Refund");
								lblcommodity.setAlignment(SWT.RIGHT);
								lblcommodity.setBounds(498, 97, 65, 20);
								lblcommodity.setFont(LABLE_FONT);
							}
							{
								lblprofilename = new Label(canvas3, SWT.NONE);
								lblprofilename.setText("Profile Name");
								lblprofilename.setBounds(252, 19, 100, 23);
								lblprofilename.setAlignment(SWT.DOWN);
								lblprofilename.setFont(LABLE_FONT);

							}
							{
								cbCCProfileName = new Combo(canvas3, SWT.BORDER);
								cbCCProfileName.setBounds(377, 15, 189, 26);
								cbCCProfileName.setFont(COMBO_FONT);
								cbCCProfileName.addSelectionListener(new AgentListener());
							}
							{
								txtCCLimit = new Text(canvas3, SWT.BORDER);
								txtCCLimit.setBounds(252, 123, 100, 22);
								txtCCLimit.setFont(TEXT_FONT);							
								txtCCLimit.addVerifyListener(new NumericExtendedValidation());
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
							}
							{
								txtCCConsider = new Text(canvas3, SWT.BORDER);
								txtCCConsider.setBounds(377, 123, 100, 22);
								txtCCConsider.setFont(TEXT_FONT);							
								txtCCConsider.addVerifyListener(new NumericExtendedValidation());
								txtCCConsider.addKeyListener(new KeyListener() {
									@Override
									public void keyPressed(KeyEvent arg0) {
										// TODO Auto-generated method stub

									}

									@Override
									public void keyReleased(KeyEvent event) {
										if (event.character == 'a'
												|| event.character == 'A') {
											txtCCConsider.setText("All");
										}
									}

								});																	
							}
							{
								txtCCRefund = new Text(canvas3, SWT.BORDER);
								txtCCRefund.setBounds(502, 123, 100, 22);
								txtCCRefund.setFont(TEXT_FONT);							
								txtCCRefund.addVerifyListener(new NumericValidation());
							}
							
							{
								btnCCCreate = new Button(canvas3, SWT.PUSH
										| SWT.CENTER);
								btnCCCreate.setText("Create");
								btnCCCreate.setBounds(522, 197, 56, 28);
								btnCCCreate.addSelectionListener(new AgentListener());
								btnCCCreate.setFont(BUTTON_FONT);
							}

							{
								btnCCview = new Button(canvas3, SWT.PUSH | SWT.CENTER);
								btnCCview.setText("View");
								btnCCview.setBounds(596, 12, 60, 25);
								btnCCview.addSelectionListener(new AgentListener());
								btnCCview.setFont(BUTTON_FONT);
							}
							
							{
								btnCCDelete = new Button(canvas3, SWT.PUSH | SWT.CENTER);
								btnCCDelete.setText("Delete");
								btnCCDelete.setBounds(670, 12, 50, 25);
								btnCCDelete.addSelectionListener(new AgentListener());
								btnCCDelete.setFont(BUTTON_FONT);
							}
							
							
							{
								txtCCProfileId = new Text(canvas3, SWT.BORDER);
								txtCCProfileId.setBounds(10, 12, 100, 22);
								txtCCProfileId.setVisible(false);								
							}
							{
								chkSpecial = new Button(canvas3, SWT.CHECK | SWT.LEFT);
								chkSpecial.setText("Special Enable");
								chkSpecial.setBounds(254, 196, 100, 30);
							}
							{
								chkCommodity = new Button(canvas3, SWT.CHECK | SWT.LEFT);
								chkCommodity.setText("Commodity Enable");
								chkCommodity.setBounds(379, 197, 105, 27);
							}
													
						}
					}
				}
				
				{

					tabItem3 = new TabItem(tabFolder1, SWT.NONE);
					tabItem3.setText("DC COMMISION");
					{
						canvas4 = new Canvas(tabFolder1, SWT.NONE);
						
						tabItem3.setControl(canvas4);
						
						{
							{
								lblDC = new Label(canvas4, SWT.NONE);
								lblDC.setText("Delivery Commission");
								lblDC.setAlignment(SWT.RIGHT);
								lblDC.setBounds(166, 98, 110, 15);
								lblDC.setFont(LABLE_FONT);
							}
							
						
							{
								lblprofilename = new Label(canvas4, SWT.NONE);
								lblprofilename.setText("Profile Name");
								lblprofilename.setBounds(211, 26, 74, 19);
								lblprofilename.setAlignment(SWT.DOWN);
								lblprofilename.setFont(LABLE_FONT);

							}
							{
								cbDCProfileName = new Combo(canvas4, SWT.BORDER);
								cbDCProfileName.setBounds(289, 25, 189, 26);
								cbDCProfileName.setFont(COMBO_FONT);
								cbDCProfileName.addSelectionListener(new AgentListener());
							}
							{
								txtDC = new Text(canvas4, SWT.BORDER);
								txtDC.setBounds(291, 95, 80, 22);
								txtDC.setFont(TEXT_FONT);							
								txtDC.addVerifyListener(new NumericValidation());								
							}
						
							
							{
								btnDCCreate = new Button(canvas4, SWT.PUSH
										| SWT.CENTER);
								btnDCCreate.setText("Create");
								btnDCCreate.setBounds(423, 93, 56, 28);
								btnDCCreate.addSelectionListener(new AgentListener());
								btnDCCreate.setFont(BUTTON_FONT);
							}

							{
								btnDCview = new Button(canvas4, SWT.PUSH | SWT.CENTER);
								btnDCview.setText("View");
								btnDCview.setBounds(487, 24, 50, 25);
								btnDCview.addSelectionListener(new AgentListener());
								btnDCview.setFont(BUTTON_FONT);
							}
							
							{
								btnDCDelete = new Button(canvas4, SWT.PUSH | SWT.CENTER);
								btnDCDelete.setText("Delete");
								btnDCDelete.setBounds(544, 24, 50, 25);
								btnDCDelete.addSelectionListener(new AgentListener());
								btnDCDelete.setFont(BUTTON_FONT);
							}
							
							
							{
								txtDCProfileId = new Text(canvas4, SWT.BORDER);
								txtDCProfileId.setBounds(10, 12, 100, 22);
								txtDCProfileId.setVisible(false);								
							}
							{
								lblPerton = new Label(canvas4, SWT.NONE);
								lblPerton.setText("Per Ton");
								lblPerton.setBounds(376, 97, 43, 21);
							}

						}
					}
				
				}
				FormData tabFolder1LData = new FormData();
				tabFolder1LData.width = 774;
				tabFolder1LData.height = 372;
				tabFolder1LData.left = new FormAttachment(0, 1000, 36);
				tabFolder1LData.top = new FormAttachment(0, 1000, 59);
				tabFolder1.setLayoutData(tabFolder1LData);
				tabFolder1.setSelection(0);
			}
			comboProfiles = populateProfileNames();
			
			if (null != comboProfiles) {
				Arrays.sort(comboProfiles, String.CASE_INSENSITIVE_ORDER);
				cbProfileName.setItems(comboProfiles);
			}

			this.layout();
		} catch (Exception exception) {
			throw exception;
		}

		return this;
	}

	/**
	 * Method to get Profile names
	 * 
	 * @return
	 * @throws Exception
	 */
	private String[] populateProfileNames() throws Exception {
		String[] names = null;
		if (null != handler) {
			names = handler.getProfileNames();
		}

		return names;
	}

	/**
	 * Method to get Profile names
	 * 
	 * @return
	 * @throws Exception
	 */
	private String[] populateCCProfileNames() throws Exception {
		String[] names = null;
		if (null != handler) {
			names = handler.getCCProfileNames();
		}

		return names;
	}
	
	private String[] populateDCProfileNames() throws Exception {
		String[] names = null;
		if (null != handler) {
			names = handler.getDCProfileNames();
		}

		return names;
	}

	
	/**
	 * 
	 * @author
	 * 
	 */
	class AgentListener implements SelectionListener {

		private String[] comboCCProfiles;
		private String[] comboDCProfiles;
		
		@Override
		public void widgetDefaultSelected(SelectionEvent event) {

		}

		/**
		 * Method to build Creation of profile DTO
		 * 
		 * @return
		 */
		private ProfileDTO buildDTO() {

			ProfileDTO agent = null;
			CardDTO[] carddto = null;
			CardDTO cdto = null;
			ArrayList<CardDTO> list = new ArrayList<CardDTO>();

			String profilename = null;
			profilename = cbProfileName.getText();
			if (!profilename.equals(EMPTY_STRING)) {
				agent = new ProfileDTO();
				agent.setProfileName(profilename);
			} else {
				int index = cbProfileName.getSelectionIndex();
				if (index > -1) {
					agent = new ProfileDTO();
					profilename = cbProfileName.getItem(index);
					agent.setProfileName(profilename);
				} else
					profilename = null;
			}
			if (null != profilename) {

				cdto = new CardDTO();
				cdto.setType(REGULAR);
				cdto.setAboveCardrate(Float.parseFloat(txtregular1.getText()));
				cdto.setEqualCardrate(Float.parseFloat(txtregular2.getText()));
				cdto.setUpto20Cardrate(Float.parseFloat(txtregular3.getText()));
				cdto
						.setAbove20Cardrate(Float.parseFloat(txtregular4
								.getText()));
				list.add(cdto);

				cdto = new CardDTO();
				cdto.setType(SPECIAL);
				cdto.setAboveCardrate(Float.parseFloat(txtspecial1.getText()));
				cdto.setEqualCardrate(Float.parseFloat(txtspecial2.getText()));
				cdto.setUpto20Cardrate(Float.parseFloat(txtspecial3.getText()));
				cdto
						.setAbove20Cardrate(Float.parseFloat(txtspecial4
								.getText()));
				list.add(cdto);

				cdto = new CardDTO();
				cdto.setType(COMMODITY);
				cdto
						.setAboveCardrate(Float.parseFloat(txtcommodity1
								.getText()));
				cdto
						.setEqualCardrate(Float.parseFloat(txtcommodity2
								.getText()));
				cdto.setUpto20Cardrate(Float
						.parseFloat(txtcommodity3.getText()));
				cdto.setAbove20Cardrate(Float.parseFloat(txtcommodity4
						.getText()));
				list.add(cdto);

				cdto = new CardDTO();
				cdto.setType(QUOTATION_IN_PDTP);
				cdto.setAboveCardrate(Float.parseFloat(txtquotationpt1
						.getText()));
				cdto.setEqualCardrate(Float.parseFloat(txtquotationpt2
						.getText()));
				cdto.setUpto20Cardrate(Float.parseFloat(txtquotationpt3
						.getText()));
				cdto.setAbove20Cardrate(Float.parseFloat(txtquotationpt4
						.getText()));
				list.add(cdto);

				cdto = new CardDTO();
				cdto.setType(QUOTATION_BL);
				cdto.setAboveCardrate(Float.parseFloat(txtquotationbl1
						.getText()));
				cdto.setEqualCardrate(Float.parseFloat(txtquotationbl2
						.getText()));
				cdto.setUpto20Cardrate(Float.parseFloat(txtquotationbl3
						.getText()));
				cdto.setAbove20Cardrate(Float.parseFloat(txtquotationbl4
						.getText()));
				list.add(cdto);
				
				cdto = new CardDTO();
				cdto.setType(HLC_PDTP);
				cdto.setAboveCardrate(Float.parseFloat(txtHLCpdtp1
						.getText()));
				cdto.setEqualCardrate(Float.parseFloat(txtHLCpdtp2
						.getText()));
				cdto.setUpto20Cardrate(Float.parseFloat(txtHLCpdtp3
						.getText()));
				cdto.setAbove20Cardrate(Float.parseFloat(txtHLCpdtp4
						.getText()));
				list.add(cdto);
			}
			int size = list.size();
			if (size > 0) {
				carddto = list.toArray(new CardDTO[size]);
				agent.setCard(carddto);
				return agent;
			}

			return null;
		}

		/**
		 * 
		 */
		private void clearAllFields() {

			String EMPTY_STRING = "";

			txtregular1.setText(EMPTY_STRING);
			txtregular2.setText(EMPTY_STRING);
			txtregular3.setText(EMPTY_STRING);
			txtregular4.setText(EMPTY_STRING);

			txtspecial1.setText(EMPTY_STRING);
			txtspecial2.setText(EMPTY_STRING);
			txtspecial3.setText(EMPTY_STRING);
			txtspecial4.setText(EMPTY_STRING);

			txtcommodity1.setText(EMPTY_STRING);
			txtcommodity2.setText(EMPTY_STRING);
			txtcommodity3.setText(EMPTY_STRING);
			txtcommodity4.setText(EMPTY_STRING);

			txtquotationpt1.setText(EMPTY_STRING);
			txtquotationpt2.setText(EMPTY_STRING);
			txtquotationpt3.setText(EMPTY_STRING);
			txtquotationpt4.setText(EMPTY_STRING);

			txtquotationbl1.setText(EMPTY_STRING);
			txtquotationbl2.setText(EMPTY_STRING);
			txtquotationbl3.setText(EMPTY_STRING);
			txtquotationbl4.setText(EMPTY_STRING);
			
			txtHLCpdtp1.setText(EMPTY_STRING);
			txtHLCpdtp2.setText(EMPTY_STRING);
			txtHLCpdtp3.setText(EMPTY_STRING);
			txtHLCpdtp4.setText(EMPTY_STRING);

			cbProfileName.setText(EMPTY_STRING);
			
			

		}

		@Override
		public void widgetSelected(SelectionEvent event) {

			Object source = event.getSource();

			if (tabFolder1 == source){
				try {
					String selected = tabFolder1.getSelection()[0].getText();
					if (selected.equals("CC COMMISION")) {
						try {
							comboCCProfiles = populateCCProfileNames();
							clearCCFields();
						} catch (Exception e) {
							e.printStackTrace();
						}

						if (null != comboCCProfiles) {
							Arrays.sort(comboCCProfiles,
									String.CASE_INSENSITIVE_ORDER);
							cbCCProfileName.setItems(comboCCProfiles);
						}
					}else if (selected.equals("DC COMMISION")) {
						try {
							comboDCProfiles = populateDCProfileNames();
							clearDCFields();
						} catch (Exception e) {
							e.printStackTrace();
						}

						if (null != comboDCProfiles) {
							Arrays.sort(comboDCProfiles,
									String.CASE_INSENSITIVE_ORDER);
							cbDCProfileName.setItems(comboDCProfiles);
						}
					}
					AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			if (source == btnCreate) {
				AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				if(validateProfile())
					dto = buildDTO();
				String id = null;
				if (null != dto && null != handler) {
					try {
						id = handler.setProfileDetails(dto);
						
						if(id == null)
							AdminComposite.display("Updated Succesfully", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						else 
							AdminComposite.display("Created Succesfully", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						
						
						cbProfileName.add(dto.getProfileName());
						comboProfiles = cbProfileName.getItems();
						Arrays.sort(comboProfiles, String.CASE_INSENSITIVE_ORDER);						
						clearAllFields();
						cbProfileName.setItems(comboProfiles);
					} catch (Exception e) {
						AdminComposite.display("Creation Failed", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						e.printStackTrace();
					}
				}
			} else if (source == btnnew) {
				try {
					String selected = tabFolder1.getSelection()[0].getText(); 
					if(selected.equals("CC COMMISION")){					
						clearCCFields();
						//cbCCProfileName.setItems(comboCCProfiles);
					}else if(selected.equals("DC COMMISION")){					
						clearDCFields();
						//cbCCProfileName.setItems(comboCCProfiles);
					}else{
						enableFields();
						clearAllFields();
						if(cbProfileName != null && comboProfiles != null)
							cbProfileName.setItems(comboProfiles);
					}
				} catch (Exception e) {				
					e.printStackTrace();
				}
			} else if (source == btnview) {
				if(!cbProfileName.getText().equals(EMPTY_STRING)){
				ProfileDTO[] details = null;
				try {
					details = getProfileDetails();
					if (null != details) {					
						//txtregular1.setText(String.valueOf(1));
						clearAllFields();
						populateProfileDetails(details);
						
					}
					
				} catch (Exception e) {

					e.printStackTrace();
				}
				}
			} else if (source == btnDelete) {	
				AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				String profileId = txtProfileId.getText();
				String profileName = EMPTY_STRING;
				boolean isExist = false;
				int index = cbProfileName.getSelectionIndex(); 
				if( index != -1){
					profileName = cbProfileName.getItem(index);;
				}
				
				if(!profileName.equals(EMPTY_STRING)){
					try {
						isExist = handler.isProfileExist(profileName);
					} catch (Exception e) {						
						e.printStackTrace();
					}
				}
				
				if(profileId != EMPTY_STRING){
					
					if(!isExist){
					try {						
						handler.deleteProfile(profileId);	
						cbProfileName.remove(profileName);
						comboProfiles = cbProfileName.getItems();
						Arrays.sort(comboProfiles,  String.CASE_INSENSITIVE_ORDER);
						AdminComposite.display("Profile deleted successfully", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						clearAllFields();
						cbProfileName.setItems(comboProfiles);
					} catch (Exception e) {	
						AdminComposite.display("Deletion Failed", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						e.printStackTrace();
					}
					}else{
						AdminComposite.display("Profile Assigned. Can not be deleted", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					}
				}
				
			}else if(source == btnCCCreate){
				AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				if (null != handler) {
					String id = null;
					String profilename = null;
					int consider = 0;
					int refund = 0;
					String considerString = EMPTY_STRING;
					String ccLimitString = EMPTY_STRING;
					int ccvalue = 0;
					
					try {

						profilename = cbCCProfileName.getText();
						if (!profilename.equals(EMPTY_STRING)) {

							if (!txtCCRefund.getText().equals(EMPTY_STRING))
								refund = Integer
										.parseInt(txtCCRefund.getText());

							considerString = txtCCConsider.getText();
							if (!txtCCConsider.getText().equals(EMPTY_STRING)) {
								if (considerString.equalsIgnoreCase("All")) {
									consider = 100;
								} else {
									consider = Integer.parseInt(considerString);
								}
							}

							ccLimitString = txtCCLimit.getText();
							if (!ccLimitString.equals(EMPTY_STRING)) {
								if (ccLimitString.equalsIgnoreCase("All")) {
									ccvalue = -1;
								} else {
									ccvalue = Integer.parseInt(ccLimitString);
								}
							}

							boolean isValid = validateRefunder(consider,
									ccvalue);
							if (isValid) {

								ProfileDTO dto = new ProfileDTO();
								dto.setCc_limit(ccvalue);
								dto.setCc_consider(consider);
								dto.setCc_refund(refund);
								dto.setProfileName(profilename);
								if (chkSpecial.getSelection()) {
									dto.setCcforspecial(1);
								} else {
									dto.setCcforspecial(0);
								}

								if (chkCommodity.getSelection()) {
									dto.setCcforcommodity(1);
								} else {
									dto.setCcforcommodity(0);
								}

								id = handler.setCCProfileDetails(dto);

								if (id == null){
									AdminComposite.display(
											"Updated Succesfully",
											STATUS_SUCCESS, SUCCESS_FONT_COLOR);
								}
								else{
									AdminComposite.display(
											"Created Succesfully",
											STATUS_SUCCESS, SUCCESS_FONT_COLOR);

								cbCCProfileName.add(dto.getProfileName());
								comboCCProfiles = cbCCProfileName.getItems();
								Arrays.sort(comboCCProfiles,
										String.CASE_INSENSITIVE_ORDER);
								
								cbCCProfileName.setItems(comboCCProfiles);
								}
								clearCCFields();
							}
						}
					} catch (Exception e) {
						AdminComposite.display("Creation Failed", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						e.printStackTrace();
					}
				}
			} else if (source == btnCCview) {				
				String profileName = EMPTY_STRING;				
				int index = cbCCProfileName.getSelectionIndex(); 
				if( index != -1){
					profileName = cbCCProfileName.getItem(index);;
				}
				
				if(!profileName.equals(EMPTY_STRING)){
				ProfileDTO[] details = null;
				try {
					
					details = handler.getCCProfileDetails();
					
					if (null != details) {					
						//txtregular1.setText(String.valueOf(1));
						
						populateCCProfileDetails(details);						
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				}
			} else if (source == btnCCDelete) {
				AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				String profileName = EMPTY_STRING;
				boolean isExist = false;
				int index = cbCCProfileName.getSelectionIndex(); 
				if( index != -1){
					profileName = cbCCProfileName.getItem(index);;
				}
				
				if(!profileName.equals(EMPTY_STRING)){
					try {
						isExist = handler.isCCProfileExist(profileName);
					} catch (Exception e) {						
						e.printStackTrace();
					}
				
					
					if(!isExist){
					try {						
						handler.deleteCCProfile(profileName);	
						cbCCProfileName.remove(profileName);
						comboCCProfiles = cbCCProfileName.getItems();
						Arrays.sort(comboCCProfiles,  String.CASE_INSENSITIVE_ORDER);
						AdminComposite.display("Profile deleted successfully", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						clearCCFields();
						cbCCProfileName.setItems(comboCCProfiles);
					} catch (Exception e) {	
						AdminComposite.display("Deletion Failed", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						e.printStackTrace();
					}
					}else{
						AdminComposite.display("Profile Assigned. Can not be deleted", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					}
				
				}
			}else if(source == btnDCCreate){
				AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				if (null != handler) {
					String id = null;
					String profilename = null;					
					int dc = 0;
										
					try {

						profilename = cbDCProfileName.getText();
						if (!profilename.equals(EMPTY_STRING)) {

							if (!txtDC.getText().equals(EMPTY_STRING))
								dc = Integer.parseInt(txtDC.getText());							

								ProfileDTO dto = new ProfileDTO();
								dto.setDelivery_commission(dc);								
								dto.setProfileName(profilename);
								
								id = handler.setDCProfileDetails(dto);

								if (id == null){
									AdminComposite.display(
											"Updated Succesfully",
											STATUS_SUCCESS, SUCCESS_FONT_COLOR);
								}
								else{
									AdminComposite.display(
											"Created Succesfully",
											STATUS_SUCCESS, SUCCESS_FONT_COLOR);

								cbDCProfileName.add(dto.getProfileName());
								comboDCProfiles = cbDCProfileName.getItems();
								Arrays.sort(comboDCProfiles,
										String.CASE_INSENSITIVE_ORDER);
								
								cbDCProfileName.setItems(comboDCProfiles);
								}
								clearDCFields();
							
						}
					} catch (Exception e) {
						AdminComposite.display("Creation Failed", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						e.printStackTrace();
					}
				}
			} else if (source == btnDCview) {				
				String profileName = EMPTY_STRING;				
				int index = cbDCProfileName.getSelectionIndex(); 
				if( index != -1){
					profileName = cbDCProfileName.getItem(index);;
				}
				
				if(!profileName.equals(EMPTY_STRING)){
				ProfileDTO[] details = null;
				try {
					
					details = handler.getDCProfileDetails();
					
					if (null != details) {					
						//txtregular1.setText(String.valueOf(1));
						
						populateDCProfileDetails(details);						
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				}
			} else if (source == btnDCDelete) {
				AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				String profileName = EMPTY_STRING;
				boolean isExist = false;
				int index = cbDCProfileName.getSelectionIndex(); 
				if( index != -1){
					profileName = cbDCProfileName.getItem(index);;
				}
				
				if(!profileName.equals(EMPTY_STRING)){
					try {
						isExist = handler.isDCProfileExist(profileName);
					} catch (Exception e) {						
						e.printStackTrace();
					}
				
					
					if(!isExist){
					try {						
						handler.deleteDCProfile(profileName);	
						cbDCProfileName.remove(profileName);
						comboDCProfiles = cbDCProfileName.getItems();
						Arrays.sort(comboDCProfiles,  String.CASE_INSENSITIVE_ORDER);
						AdminComposite.display("Profile deleted successfully", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						clearDCFields();
						cbDCProfileName.setItems(comboDCProfiles);
					} catch (Exception e) {	
						AdminComposite.display("Deletion Failed", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						e.printStackTrace();
					}
					}else{
						AdminComposite.display("Profile Assigned. Can not be deleted", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					}
				
				}
				}
		}

		
		
		private void populateCCProfileDetails(ProfileDTO[] details) {
			AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			int len = details.length;
			int limit = 0;
			int consider = 0;
			
			/*int index = cbCCProfileName.getSelectionIndex();
			cbCCProfileName.select(index);
			if (index > -1) {*/
			String selectedname = cbCCProfileName.getText();
			//clearCCFields();
			for(int i = 0; i < len; i++){				
					String name = details[i].getProfileName();
					if (selectedname.equals(name)) {
						
						consider = details[i].getCc_consider();		
						if(consider == 100){
							txtCCConsider.setText("All");
						}else{
							txtCCConsider.setText(String.valueOf(details[i].getCc_consider()));
						}
						
						limit = details[i].getCc_limit();
						if(limit == -1){
							txtCCLimit.setText("All");
						}else{
							txtCCLimit.setText(String.valueOf(details[i].getCc_limit()));
						}
					
						txtCCRefund.setText(String.valueOf(details[i].getCc_refund()));
						
						if(details[i].getCcforcommodity() == 1){
							chkCommodity.setSelection(true);
						}else{
							chkCommodity.setSelection(false);
						}
						
						if(details[i].getCcforspecial() == 1){
							chkSpecial.setSelection(true);
						}else{
							chkSpecial.setSelection(false);
						}
					}
				}
			//}
			
			
		}
		
		private void populateDCProfileDetails(ProfileDTO[] details) {
			AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			int len = details.length;
			String selectedname = cbDCProfileName.getText();
			
			for(int i = 0; i < len; i++){				
					String name = details[i].getProfileName();
					if (selectedname.equals(name)) {
						
						txtDC.setText(String.valueOf(details[i].getDelivery_commission()));
						
					}
				}						
		}

		private boolean validateRefunder(int considerValue, int ccLimitValue) {
			String refund = txtCCRefund.getText();
			String consider = txtCCConsider.getText();
			String ccLimit = txtCCLimit.getText();
			
			if (ccLimit.equals(EMPTY_STRING)) {
				AdminComposite.display("Please Enter CC Limit",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				return false;
			}  else if (ccLimitValue > 100) {
				AdminComposite.display("CC Limit should be lesser than 100",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				return false;
			} else if (consider.equals(EMPTY_STRING)) {
				AdminComposite.display("Please Enter Consider Percentage",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				return false;
			} else if (considerValue > 100) {
				AdminComposite.display("Consider Percentage should be lesser than 100",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				return false;
			} else if ( (ccLimitValue != -1) && (ccLimitValue != 0) && (considerValue > ccLimitValue)) {
				AdminComposite.display("Consider Percentage should be lesser than CC Limit",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				return false;
			} else if (refund.equals(EMPTY_STRING)) {
				AdminComposite.display("Please Enter Refunder Percentage",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				return false;
			} else if (!refund.equals(EMPTY_STRING) && Integer.parseInt(refund) > 100) {
				AdminComposite.display("Refund Percentage should be lesser than 100",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				return false;
			}

			return true;
		}
		
		private void clearCCFields() {
			txtCCConsider.setText(EMPTY_STRING);
			txtCCLimit.setText(EMPTY_STRING);
			txtCCRefund.setText(EMPTY_STRING);
			
			chkCommodity.setSelection(false);
			chkSpecial.setSelection(false);
			
			cbCCProfileName.setText("");
			
		}
		
		private void clearDCFields() {
			txtDC.setText(EMPTY_STRING);
					
			cbDCProfileName.setText("");
			
		}

		private void enableFields() {
			txtregular1.setEnabled(true);
			txtregular2.setEnabled(true);
			txtregular3.setEnabled(true);
			txtregular4.setEnabled(true);

			txtspecial1.setEnabled(true);
			txtspecial2.setEnabled(true);
			txtspecial3.setEnabled(true);
			txtspecial4.setEnabled(true);

			txtcommodity1.setEnabled(true);
			txtcommodity2.setEnabled(true);
			txtcommodity3.setEnabled(true);
			txtcommodity4.setEnabled(true);

			txtquotationpt1.setEnabled(true);
			txtquotationpt2.setEnabled(true);
			txtquotationpt3.setEnabled(true);
			txtquotationpt4.setEnabled(true);

			txtquotationbl1.setEnabled(true);
			txtquotationbl2.setEnabled(true);
			txtquotationbl3.setEnabled(true);
			txtquotationbl4.setEnabled(true);
			
			txtHLCpdtp1.setEnabled(true);
			txtHLCpdtp2.setEnabled(true);
			txtHLCpdtp3.setEnabled(true);
			txtHLCpdtp4.setEnabled(true);
			
			btnCreate.setEnabled(true);
			
		}		

	}

	/**
	 * Method to get all Profile Details
	 * 
	 * @return
	 * @throws Exception
	 */
	public ProfileDTO[] getProfileDetails() throws Exception {
		ProfileDTO[] dto = null;
		if (null != handler) {
			dto = handler.getProfileDetails();
		}
		return dto;
	}

	/**
	 * Method Display Profile details for Selected Profile Name
	 * 
	 * @param details
	 */
	public void populateProfileDetails(ProfileDTO[] details) {
		AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
		decimalFormat = new DecimalFormat("0.00");
		int length = details.length;
		for (int i = 0; i < length; i++) {
			int index = cbProfileName.getSelectionIndex();
			cbProfileName.select(index);
			if (index > -1) {
				String selectedname = cbProfileName.getText();
				String name = details[i].getProfileName();
				if (selectedname.equals(name)) {
					
					//Hidden Profile Id					
					txtProfileId.setText(details[i].getProfileId());
					
					CardDTO[] dto = details[i].getCard();
					int size = dto.length;
					for (int j = 0; j < size; j++) {
						String type = dto[j].getType();
						float above = dto[j].getAboveCardrate();
						float equal = dto[j].getEqualCardrate();
						float upto20 = dto[j].getUpto20Cardrate();
						float above20 = dto[j].getAbove20Cardrate();
						if (type.equals(SPECIAL)) {
							txtspecial1.setText(decimalFormat.format(above));
							txtspecial2.setText(decimalFormat.format(equal));
							txtspecial3.setText(decimalFormat.format(upto20));
							txtspecial4.setText(decimalFormat.format(above20));
						} else if (type.equals(REGULAR)) {

							txtregular1.setText(decimalFormat.format(above));
							txtregular2.setText(decimalFormat.format(equal));
							txtregular3.setText(decimalFormat.format(upto20));
							txtregular4.setText(decimalFormat.format(above20));

						} else if (type.equals(COMMODITY)) {
							txtcommodity1.setText(decimalFormat.format(above));
							txtcommodity2.setText(decimalFormat.format(equal));
							txtcommodity3.setText(decimalFormat.format(upto20));
							txtcommodity4.setText(decimalFormat.format(above20));
						} else if (type.equals(QUOTATION_IN_PDTP)) {

							txtquotationpt1.setText(decimalFormat.format(above));
							txtquotationpt2.setText(decimalFormat.format(equal));
							txtquotationpt3.setText(decimalFormat.format(upto20));
							txtquotationpt4.setText(decimalFormat.format(above20));

						} else if (type.equals(QUOTATION_BL)) {

							txtquotationbl1.setText(decimalFormat.format(above));
							txtquotationbl2.setText(decimalFormat.format(equal));
							txtquotationbl3.setText(decimalFormat.format(upto20));
							txtquotationbl4.setText(decimalFormat.format(above20));

						} else if (type.equals(HLC_PDTP)) {

							txtHLCpdtp1.setText(decimalFormat.format(above));
							txtHLCpdtp2.setText(decimalFormat.format(equal));
							txtHLCpdtp3.setText(decimalFormat.format(upto20));
							txtHLCpdtp4.setText(decimalFormat.format(above20));

						}
					}

					disableFields();
					break;
				}
			}
		}
	}
	
	/**
	 * Method to validate Profile Values
	 * @return
	 */
	private boolean validateProfile() {
		if(cbProfileName.getText().equals(EMPTY_STRING)){
			AdminComposite.display("Please enter Customer Name", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;
		}else if (txtregular1.getText().equals(EMPTY_STRING)) {
			AdminComposite.display("Please enter Regular - Above Card Rate Value", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;
		}else if(txtregular2.getText().equals(EMPTY_STRING)) {
			AdminComposite.display("Please enter Regular - Card Rate Value", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;			
		}else if(txtregular3.getText().equals(EMPTY_STRING)) {
			AdminComposite.display("Please enter Regular - upto 20% discount value", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;			
		}else if(txtregular4.getText().equals(EMPTY_STRING)) {
			AdminComposite.display("Please enter Regular - above 20% discount value", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;			
		}else if(txtspecial1.getText().equals(EMPTY_STRING)) {
			AdminComposite.display("Please enter Special - Above Card Rate Value", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;			
		}else if(txtspecial2.getText().equals(EMPTY_STRING)) {
			AdminComposite.display("Please enter Special - Card Rate Value", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;			
		}else if(txtspecial3.getText().equals(EMPTY_STRING)) {
			AdminComposite.display("Please enter Special - upto 20% discount value", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;			
		}else if(txtspecial4.getText().equals(EMPTY_STRING)) {
			AdminComposite.display("Please enter Special - above 20% discount value", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;			
		}else if(txtcommodity1.getText().equals(EMPTY_STRING)) {
			AdminComposite.display("Please enter Commodity - Above Card Rate Value", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;			
		}else if(txtcommodity2.getText().equals(EMPTY_STRING)) {
			AdminComposite.display("Please enter Commodity - Card Rate Value", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;			
		}else if(txtcommodity3.getText().equals(EMPTY_STRING)) {
			AdminComposite.display("Please enter Commodity - upto 20% discount value", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;			
		}else if(txtcommodity4.getText().equals(EMPTY_STRING)) {
			AdminComposite.display("Please enter Commodity - above 20% discount value", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;			
		}else if(txtquotationpt1.getText().equals(EMPTY_STRING)) {
			AdminComposite.display("Please enter Quotation in PD/TP - Above Card Rate Value", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;			
		}else if(txtquotationpt2.getText().equals(EMPTY_STRING)) {
			AdminComposite.display("Please enter Quotation in PD/TP - Card Rate Value", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;			
		}else if(txtquotationpt3.getText().equals(EMPTY_STRING)) {
			AdminComposite.display("Please enter Quotation in PD/TP - upto 20% discount value", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;			
		}else if(txtquotationpt4.getText().equals(EMPTY_STRING)) {
			AdminComposite.display("Please enter Quotation in PD/TP - above 20% discount value", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;			
		}else if(txtquotationbl1.getText().equals(EMPTY_STRING)) {
			AdminComposite.display("Please enter Quotation in BL - Above Card Rate Value", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;			
		}else if(txtquotationbl2.getText().equals(EMPTY_STRING)) {
			AdminComposite.display("Please enter Quotation in BL - Card Rate Value", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;			
		}else if(txtquotationbl3.getText().equals(EMPTY_STRING)) {
			AdminComposite.display("Please enter Quotation in BL - upto 20% discount value", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;			
		}else if(txtquotationbl4.getText().equals(EMPTY_STRING)) {
			AdminComposite.display("Please enter Quotation in BL - above 20% discount value", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;			
		}else if(txtHLCpdtp1.getText().equals(EMPTY_STRING)) {
			AdminComposite.display("Please enter HLC in PD/TP - Above Card Rate Value", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;			
		}else if(txtHLCpdtp2.getText().equals(EMPTY_STRING)) {
			AdminComposite.display("Please enter HLC in PD/TP - Card Rate Value", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;			
		}else if(txtHLCpdtp3.getText().equals(EMPTY_STRING)) {
			AdminComposite.display("Please enter HLC in PD/TP - upto 20% discount value", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;			
		}else if(txtHLCpdtp4.getText().equals(EMPTY_STRING)) {
			AdminComposite.display("Please enter HLC in PD/TP - above 20% discount value", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;			
		}

		return true;
	}
	private void disableFields() {
		txtregular1.setEnabled(false);
		txtregular2.setEnabled(false);
		txtregular3.setEnabled(false);
		txtregular4.setEnabled(false);

		txtspecial1.setEnabled(false);
		txtspecial2.setEnabled(false);
		txtspecial3.setEnabled(false);
		txtspecial4.setEnabled(false);

		txtcommodity1.setEnabled(false);
		txtcommodity2.setEnabled(false);
		txtcommodity3.setEnabled(false);
		txtcommodity4.setEnabled(false);

		txtquotationpt1.setEnabled(false);
		txtquotationpt2.setEnabled(false);
		txtquotationpt3.setEnabled(false);
		txtquotationpt4.setEnabled(false);

		txtquotationbl1.setEnabled(false);
		txtquotationbl2.setEnabled(false);
		txtquotationbl3.setEnabled(false);
		txtquotationbl4.setEnabled(false);
		
		txtHLCpdtp1.setEnabled(false);
		txtHLCpdtp2.setEnabled(false);
		txtHLCpdtp3.setEnabled(false);
		txtHLCpdtp4.setEnabled(false);
		
		btnCreate.setEnabled(false);
		
	}	

}
