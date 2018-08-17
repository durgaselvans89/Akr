package hm.akr.admin.quotation;

import hm.akr.admin.quotation.handler.QuotationHandler;
import hm.akr.admin.workspace.AdminComposite;
import hm.akr.common.FloatLimitValidation;
import hm.akr.common.FloatValidation;
import hm.akr.common.IUIConstants;
import hm.akr.common.NumericValidation;
import hm.akr.common.ValueRangeComposite;
import hm.akr.dto.BftDTO;
import hm.akr.dto.DhcDto;
import hm.akr.dto.InsuranceDTO;
import hm.akr.dto.OtherChargesDTO;
import hm.akr.dto.QuotationDTO;
import hm.akr.dto.QuotationDetailsDTO;
import hm.akr.dto.StationsDTO;
import hm.akr.exceptions.BusinessException;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
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
 * Quotation Details Composite
 * 
 */
public class ManualEditComposite extends Composite implements IQuotationConstants, IUIConstants, FocusListener {

	private Label lblBookingType;

	private Label lblLRC;

	private Combo cbBookingType;

	private Label lblPriceIndex;

	public Text txtPriceIndex;

	private Label lblMinCF;

	private Text txtMinCF;

	private Label lblMinCW;

	private Text txtMinCW;

	public Text txtCW = null;

	private Combo cbLRC;

	private Combo cbCCC;

	private Combo cbDCC;

	private Combo cbDDC;

	private Combo cbIEC;

	private Combo cbLoadingCharge;

	private Label lblST;

	private Button btnAdd;

	Composite parent;

	private Text txtArticleName;

	private Text txtDDC_minPerLR;

	private Text txtDDC_chargeArticle;

	private Text txtIEC_article;

	private Text txtLC_article;

	private Label lblGSC;

	public Table tblArticle;

	private Group cccGroup;

	private Text txtCCCValue;

	private Button chkServiceTax;

	private Group dccGroup;

	private Text txtDCCValue;

	private Group ddcGroup;

	private Group iecGroup;
	private Label lblType;
	private Text txtType;
	private Label lblCustomerNames;

	private Button btnLRCPop;

	private Button btnInsurancePop;

	private Group loadingchargeGroup;

	private Button btnGSCPop;

	public Composite cptTableLRC;

	//private Group gpArticleTable;

	private Combo cbGSC;

	private Combo cbInsurance;

	private Label lblInsurance;

	private Label lblCW;

	private Label lblArtilcleName;

	static String EMPTYSTRING = "";

	public OtherChargesDTO[] otherChargesDTO = null;

	public QuotationDTO quotationDTO = null;

	public OtherChargesDTO[] gscDTO = null;

	public OtherChargesDTO[] lrcDTO = null;

	public InsuranceDTO[] insuranceDTO = null;

	private String TYPE = null;

	private QuotationHandler handler = null;

	private Label lblStationName;

	private Text txtStation;

	private List lstCustomerNames;

	private QuotationDTO[] quotList = null;

	private DecimalFormat decimalFormat;

	private Label lblMinLR;

	private Label lblchargeArticle;

	private Label lblRoundOffDigits;

	private Text txtRoundOffDigits;

	private Combo cbDHC;

	private Button btnDHCPop;

	private Label lblDHC;

	private DhcDto[] dhcDto = null;
	
	
	/**
	 * Constructor method
	 * 
	 * @param parent 
	 * @param style
	 * @param selectionIndex
	 * @param quotDTO
	 */
	public ManualEditComposite(Composite parent, int style, int selectionIndex, QuotationDTO quotationDto,
			QuotationDTO[] quotList) {
		super(parent, style);
		this.parent = parent;
		this.quotationDTO = quotationDto;
		this.quotList = quotList;
		try {
			handler = new QuotationHandler();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * 
	 * @param index
	 */
	public void changeType(String quotationType) {
		if (WEIGHT_BASED.equals(quotationType)) {
			//Disable the Article Table
			tblArticle.setVisible(false);
			if(tblArticle != null){
				tblArticle.removeAll();
			}
			//gpArticleTable.setVisible(false);
			//Disable the Article Name		
			txtArticleName.setText(EMPTYSTRING);
			txtArticleName.setEnabled(false);
			//Disable the Charged Weight
			txtCW.setText(EMPTYSTRING);
			txtCW.setEnabled(false);
			//Disable the Add button
			btnAdd.setEnabled(false);			
			txtMinCW.setEnabled(true);
		} else if (ARTICLE_BASED.equals(quotationType)) {
			//Disable the Article Table
			tblArticle.setVisible(false);
			if(tblArticle != null){
				tblArticle.removeAll();
			}
			//gpArticleTable.setVisible(false);
			//Enable the Article Name
			txtArticleName.setEnabled(true);
			//Enable the Charged Weight
			txtCW.setEnabled(true);
			//TBD - Disable the Add button
			btnAdd.setEnabled(false);
			txtMinCW.setText(EMPTYSTRING);
			txtMinCW.setEnabled(false);
		} else if (MIXED_ARTICLE.equals(quotationType)) {
			//Enable the Article Table
			tblArticle.setVisible(true);
			//gpArticleTable.setVisible(true);
			//Enable the Article Name
			txtArticleName.setEnabled(true);
			//Enable the Charged Weight
			txtCW.setEnabled(true);
			//Enable the Add button
			btnAdd.setEnabled(true);
			txtMinCW.setText(EMPTYSTRING);
			txtMinCW.setEnabled(false);
		}
	}

	/**
	 * 
	 */
	public void loadValues(QuotationDTO quotDto) {
		System.out.println("QId==>" + quotDto.getId());
		decimalFormat = new DecimalFormat("0.00");
		this.quotationDTO = quotDto;
		String type = null;
		//Booking Type
		if (quotDto.isInward())
			cbBookingType.setText(INWARD_TYPE);
		else
			cbBookingType.setText(OUTWARD_TYPE);

		//Price Index
		if((quotDto.getPriceIndex()%1) == new Float(0) ){
			txtPriceIndex.setText(decimalFormat.format(quotDto.getPriceIndex()));
		}else{
			txtPriceIndex.setText(String.valueOf(getRoundFourDecimals(quotDto.getPriceIndex())));
		}		

		//Min Freight Value
		txtMinCF.setText(decimalFormat.format(quotDto.getMinFreightValue()));
		
		// Round off digits
		txtRoundOffDigits.setText(String.valueOf(quotDto.getRoundOffDigits()));
		
		//Min Weight Value		
		if(quotDto.getType() == 0)
			txtMinCW.setText(decimalFormat.format(quotDto.getMinWeightValue()));

		//Service Tax
		if (quotDto.isServiceTax()) {
			chkServiceTax.setSelection(true);
		} else {
			chkServiceTax.setSelection(false);
		}

		//GSC Type
		cbGSC.setText(optionA[quotDto.getGscType()]);		
		handleGSCTypeChange();

		//DHC Type
		cbDHC.setText(optionA[quotDto.getDhcChargeType()]);
		handleDHCTypeChange();
		dhcDto = quotDto.getDhcDto();
		
		//Insurance Type
		cbInsurance.setText(optionA[quotDto.getInsuranceChargeType()]);
		handleInsuranceTypeChange();

		//LR Charge Type
		cbLRC.setText(optionA[quotDto.getLrchargeType()]);		
		handleLRCTypeChange();

		//Insurance Charges
		insuranceDTO = quotDto.getInsuranceCharges();

		// Quotation Details
		QuotationDetailsDTO[] details = quotDto.getQuotationDetails();

		if (quotDto.getType() == 2) { //Mixed Article
			tblArticle.removeAll();
			int count = details.length;
			for (int i = 0; i < count; i++) {
				addToArticleTable(details[i]);
			}
			if (tblArticle.getItemCount() > 0) {
				tblArticle.setSelection(0);
				viewEditTblArticle();
			}

		} else {
			populateQuotationDetails(details[0]);
		}

		txtStation.setText(quotDto.getStationCode());
		type = findTypeName((byte) quotDto.getType());
		txtType.setText(type);
		changeType(type);
	}

	/**
	 * Method to find Quotation Type Name
	 * @param selectionIndex
	 * @return
	 */
	private String findTypeName(byte selectionIndex) {
		if (selectionIndex == 0) {
			TYPE = WEIGHT_BASED;
		} else if (selectionIndex == 1) {
			TYPE = ARTICLE_BASED;
		} else {
			TYPE = MIXED_ARTICLE;
		}
		return TYPE;
	}

	/**
	 * Method to populate the quotation details
	 * 
	 * @param detail  An instance of QuotationDetailsDTO
	 */
	private void populateQuotationDetails(QuotationDetailsDTO detail) {

		decimalFormat = new DecimalFormat("0.00");
		
		//Article Name
		if (detail.getArticleName() != null)
			txtArticleName.setText(detail.getArticleName());

		//Charged Weight		
		txtCW.setText(decimalFormat.format(detail.getChargedWeight()));

		//CC Charge Type
		cbCCC.setText(optionB[detail.getCcchargeType()]);
		txtCCCValue.setText(decimalFormat.format(detail.getCcchargeValue()));
		handleCCCTypeChange();

		//DC Charge
		cbDCC.setText(optionB[detail.getDcchargeType()]);
		txtDCCValue.setText(decimalFormat.format(detail.getDcchargeValue()));
		handleDCCTypeChange();

		//DD Charge
		cbDDC.setText(optionC[detail.getDdchargeType()]);
		txtDDC_minPerLR.setText(decimalFormat.format(detail.getMinDdchargeValue()));
		txtDDC_chargeArticle.setText(decimalFormat.format(detail.getDdchargeArticle()));
		handleDDCTypeChange();

		//IE Charge
		cbIEC.setText(optionB[detail.getIechargeType()]);
		txtIEC_article.setText(decimalFormat.format(detail.getIechargeValue()));
		handleIECTypeChange();

		//LC Charge
		cbLoadingCharge.setText(optionB[detail.getLcchargeType()]);
		txtLC_article.setText(decimalFormat.format(detail.getLcchargeValue()));
		handleLCTypeChange();
	}

	/**
	 * Method to get the details set by the user.
	 * 
	 * @return QuotationDTO
	 * @throws Business Exception 
	 */
	public QuotationDTO getQuotationInformation() throws BusinessException {
		String temp = null;

		if (null == quotationDTO)
			quotationDTO = new QuotationDTO();

		//Booking Type
		quotationDTO.setInward(isInwardType());

		//Minimum Freight Value
		temp = txtMinCF.getText();
		if (temp.length() > 0) {
			quotationDTO.setMinFreightValue(Float.parseFloat(temp));
		}

		//Minimum Weight value
		temp = txtMinCW.getText();
		if (temp.length() > 0) {
			quotationDTO.setMinWeightValue(Float.parseFloat(temp));
		}

		//LR Charge Type
		quotationDTO.setLrchargeType(getLRChargeIndex());

		//Service Tax
		if (chkServiceTax.getSelection()) {
			quotationDTO.setServiceTax(true);
		} else {
			quotationDTO.setServiceTax(false);
		}

		// GSC Type
		quotationDTO.setGscType(getGSCIndex());

		//Other Charges		
		if (quotationDTO.getLrchargeType() == 1 || quotationDTO.getGscType() == 1) {

			otherChargesDTO = getOtherCharges();
			if (otherChargesDTO != null) {
				//System.out.println("others on set==>" + otherChargesDTO.length);
				if (quotationDTO.getLrchargeType() != 1) {
					otherChargesDTO = deInitializeLRC(otherChargesDTO);
				} else if (quotationDTO.getGscType() != 1) {
					otherChargesDTO = deInitializeGSC(otherChargesDTO);
				}
			}

			quotationDTO.setOtherCharges(otherChargesDTO);
		}

		//Insurance Type		
		quotationDTO.setInsuranceChargeType(getInsuranceType());

		//Insurance Charges
		if (quotationDTO.getInsuranceChargeType() == 1) {
			quotationDTO.setInsuranceCharges(insuranceDTO);
		}

		// DHC
		quotationDTO.setDhcChargeType(getDHCType());
		if(quotationDTO.getDhcChargeType() == 1){
			quotationDTO.setDhcDto(dhcDto);
		}		
		
		//Quotation Details
		quotationDTO.setQuotationDetails(getQuotationDetails());

		//Basic Price Index
		temp = txtPriceIndex.getText();
		if (temp.length() > 0) {
			quotationDTO.setPriceIndex(Float.parseFloat(temp));
		}
		
		//Round off digits
		temp = txtRoundOffDigits.getText();
		if (temp.length() > 0) {
			quotationDTO.setRoundOffDigits(Integer.parseInt(temp));
		}
		
		return quotationDTO;
	}

	/**
	 * Method to construct the quotationDetails DTO from the Article Table
	 * 
	 * @return QuotationDetailsDTO[]
	 */
	private QuotationDetailsDTO[] getQuotationDetailsForMixedArticle() {
		QuotationDetailsDTO[] details = null;

		TableItem[] items = tblArticle.getItems();
		int len = items.length;

		if (quotationDTO != null && quotationDTO.getQuotationDetails() != null) {
			//Available Quotation Details DTO
			details = quotationDTO.getQuotationDetails();

			// To check Price Index Value and updating BFT	
			checkPriceIndex(details);

			details = updateArticleTable(items, details);

		} else {
			// Create New Quotation Details DTO
			details = new QuotationDetailsDTO[len];
			for (int i = 0; i < len; i++) {
				details[i] = new QuotationDetailsDTO();

				// Create QuotationDetails for Mixed Article From Article Table				
				details[i] = createQuotationDetailsForMixedArticle(details[i], items[i]);
			}
		}

		return details;
	}

	/**
	 * Method to check PriceIndex changed or not
	 */
	private boolean checkPriceIndex(QuotationDetailsDTO[] details) {
		if (quotationDTO.getPriceIndex() != Float.parseFloat(txtPriceIndex.getText())) {
			for (int i = 0; i < details.length; i++) {
				details[i].setBft(null);
			}
			//System.out.println("Price Index Changed");
			return false;
		}
		
		
		
		// round digit changed
		if (quotationDTO.getRoundOffDigits() != Integer.parseInt(txtRoundOffDigits.getText())) {
			/*QuotationDetailsDTO[] initialQDetails = null;
			for (int k = 0; k < quotList.length; k++) {
				if (quotationDTO.getCustomerName().equals(quotList[k].getCustomerName())) {
					initialQDetails = quotList[k].getQuotationDetails();
					System.out.println("sdfasfaf==>"+quotationDTO.getCustomerName());
					break;														
				}				
			}*/
			
			for (int i = 0; i < details.length; i++) {				
				BftDTO[] cardRate = details[i].getBft();				
				if(cardRate != null){
					int len = cardRate.length;
					for(int j = 0; j < len; j++){						
						if(cardRate[j].getBftChanged() == 0){
							cardRate[j].setBpi(-1);
						}
					}
				}
				//details[i].setBft(null);
			}
		//	System.out.println("Round off Changed");
			return false;
		}
		
		return true;
	}

	/**
	 * Method to check Charged Weight
	 */
	private QuotationDetailsDTO checkChargedWeight(TableItem[] items, QuotationDetailsDTO detail) {
		float chargedWt = 0;

		for (int j = 0; j < items.length; j++) {

			chargedWt = Float.parseFloat(items[j].getText(1));

			if (items[j].getText(0).equals(detail.getArticleName())) {
				if (chargedWt != detail.getChargedWeight()) {
					//System.out.println("CW changed for " + detail.getArticleName());
					detail.setBft(null);

					break;
				}

			} else {
			}

		}
		return detail;
	}

	/**
	 * Method to update QuotationDetailsDTO
	 * @param details
	 * @return
	 */
	private QuotationDetailsDTO[] updateArticleTable(TableItem[] items, QuotationDetailsDTO[] details) {
		QuotationDetailsDTO[] newDetails = null;
		int size = items.length;
		newDetails = new QuotationDetailsDTO[size];

		for (int i = 0; i < size; i++) {
			newDetails[i] = new QuotationDetailsDTO();
			for (int j = 0; j < details.length; j++) {
				if (items[i].getText(0).equals(details[j].getArticleName())) {
					newDetails[i] = details[j];
					checkChargedWeight(items, newDetails[i]);
					break;
				}
			}
			newDetails[i] = createQuotationDetailsForMixedArticle(newDetails[i], items[i]);
		}
		return newDetails;
	}

	/**
	 * Method to create Quotation Details for Mixed Article.
	 * @param detail
	 * @param item
	 */
	private QuotationDetailsDTO createQuotationDetailsForMixedArticle(QuotationDetailsDTO detail, TableItem item) {
		
		detail.setArticleName(item.getText(0));
		detail.setChargedWeight(Float.parseFloat(item.getText(1)));

		if (item.getText(2).equalsIgnoreCase(optionB[0])) {
			detail.setCcchargeType((byte) 0);
		} else if (item.getText(2).equalsIgnoreCase(optionB[1])) {
			detail.setCcchargeType((byte) 1);
		} else if (item.getText(2).equalsIgnoreCase(optionB[2])) {
			detail.setCcchargeType((byte) 2);
		} else if (item.getText(2).equalsIgnoreCase(optionB[3])) {
			detail.setCcchargeType((byte) 3);
		}
		if (!item.getText(3).equals(EMPTYSTRING))
			detail.setCcchargeValue(Float.parseFloat(item.getText(3)));

		if (item.getText(4).equalsIgnoreCase(optionB[0])) {
			detail.setDcchargeType((byte) 0);
		} else if (item.getText(4).equalsIgnoreCase(optionB[1])) {
			detail.setDcchargeType((byte) 1);
		} else if (item.getText(4).equalsIgnoreCase(optionB[2])) {
			detail.setDcchargeType((byte) 2);
		} else if (item.getText(4).equalsIgnoreCase(optionB[3])) {
			detail.setDcchargeType((byte) 3);
		}
		if (!item.getText(5).equals(EMPTYSTRING))
			detail.setDcchargeValue(Float.parseFloat(item.getText(5)));

		if (item.getText(6).equalsIgnoreCase(optionB[0])) {
			detail.setIechargeType((byte) 0);
		} else if (item.getText(6).equalsIgnoreCase(optionB[1])) {
			detail.setIechargeType((byte) 1);
		} else if (item.getText(6).equalsIgnoreCase(optionB[3])) {
			detail.setIechargeType((byte) 3);
		}
		if (!item.getText(7).equals(EMPTYSTRING))
			detail.setIechargeValue(Float.parseFloat(item.getText(7)));

		if (item.getText(8).equalsIgnoreCase(optionB[0])) {
			detail.setLcchargeType((byte) 0);
		} else if (item.getText(8).equalsIgnoreCase(optionB[1])) {
			detail.setLcchargeType((byte) 1);
		}else if (item.getText(8).equalsIgnoreCase(optionB[3])) {
			detail.setLcchargeType((byte) 3);
		}
		if (!item.getText(9).equals(EMPTYSTRING))
			detail.setLcchargeValue(Float.parseFloat(item.getText(9)));

		if (item.getText(10).equalsIgnoreCase(optionC[0])) {
			detail.setDdchargeType((byte) 0);
		} else if (item.getText(10).equalsIgnoreCase(optionC[1])) {
			detail.setDdchargeType((byte) 1);
		} else if (item.getText(10).equalsIgnoreCase(optionC[2])) {
			detail.setDdchargeType((byte) 2);
		}
		if (!item.getText(11).equals(EMPTYSTRING))
			detail.setMinDdchargeValue(Float.parseFloat(item.getText(11)));
		if (!item.getText(12).equals(EMPTYSTRING))
			detail.setDdchargeArticle(Float.parseFloat(item.getText(12)));

		return detail;
	}

	/**
	 * Method to get the quotation details for weight and article based quotation
	 * 
	 * @return  QuotationDetailsDTO 
	 */
	private QuotationDetailsDTO getQuotationDetailsDTO() {
		QuotationDetailsDTO detail = null;
		try {
			if (quotationDTO != null && quotationDTO.getQuotationDetails() != null) {
				detail = quotationDTO.getQuotationDetails()[0];
				// bpi changed
				if (quotationDTO.getPriceIndex() != Float.parseFloat(txtPriceIndex.getText())) {
					detail.setBft(null);
				}

				// round digit changed
				if (quotationDTO.getRoundOffDigits() != Integer.parseInt(txtRoundOffDigits.getText())) {
					BftDTO[] cardRate = detail.getBft();
					if(cardRate != null){
						int len = cardRate.length;
						for(int i = 0; i < len; i++){
							if(cardRate[i].getBftChanged() == 0){
								cardRate[i].setBpi(-1);
							}
						}
					}
					//detail.setBft(null);
				}
				
				if (txtCW.getText() != EMPTYSTRING) {
					if (detail.getChargedWeight() != Float.parseFloat(txtCW.getText())) {
						detail.setBft(null);
					}
				}
				detail = createQuotationDetail(detail);
			} else {
				detail = new QuotationDetailsDTO();

				detail = createQuotationDetail(detail);

			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return detail;
	}

	/**
	 * Method to create Quotation Details for Weight/Article based Quotation
	 * @param details
	 * @return
	 * @throws Exception
	 */
	private QuotationDetailsDTO createQuotationDetail(QuotationDetailsDTO detail) throws Exception {
		String temp = null;

		//Article Name
		if (txtArticleName.getEnabled()) {
			temp = txtArticleName.getText();
			if (temp.length() > 0)
				detail.setArticleName(temp);
		}

		//Charged Weight
		if (txtCW.getEnabled()) {
			temp = txtCW.getText();
			if (temp.length() > 0) {
				detail.setChargedWeight(Float.parseFloat(temp));
			} else {
				detail.setChargedWeight(0);
			}
		}

		//CC Charge Type
		detail.setCcchargeType(getCCChargeIndex());

		//CC Charge Value

		temp = txtCCCValue.getText();
		if (temp.length() > 0) {
			detail.setCcchargeValue(Float.parseFloat(temp));
		} else {
			detail.setCcchargeValue(0);
		}

		//DC Charge Type
		detail.setDcchargeType(getDCChargeType());

		//DC Charge Value		
		temp = txtDCCValue.getText();
		if (temp.length() > 0) {
			detail.setDcchargeValue(Float.parseFloat(temp));
		} else {
			detail.setDcchargeValue(0);
		}

		//DD Charge Type
		detail.setDdchargeType(getDDChargeType());

		//DD Charge per article

		temp = txtDDC_chargeArticle.getText();
		if (temp.length() > 0) {
			detail.setDdchargeArticle(Float.parseFloat(temp));
		} else {
			detail.setDdchargeArticle(0);
		}

		//Minimum DD Charge value

		temp = txtDDC_minPerLR.getText();
		if (temp.length() > 0) {
			detail.setMinDdchargeValue(Float.parseFloat(temp));
		} else {
			detail.setMinDdchargeValue(0);
		}

		//IE Charge Type
		detail.setIechargeType(getIEChargeType());

		//IE Charge Value
		//if(txtIEC_article.getEnabled()) {
		temp = txtIEC_article.getText();
		if (temp.length() > 0) {
			detail.setIechargeValue(Float.parseFloat(temp));
		} else {
			detail.setIechargeValue(0);
		}
		//}

		//LC Charge Type
		detail.setLcchargeType(getLCChargeType());

		//LC Charge Value
		//if(txtLC_article.getEnabled()) {
		temp = txtLC_article.getText();
		if (temp.length() > 0) {
			detail.setLcchargeValue(Float.parseFloat(temp));
		} else {
			detail.setLcchargeValue(0);
		}
		//}

		return detail;
	}

	/**
	 * Method to add quotation details to the article table
	 * 
	 * @param detail An instance of QuotationDetailsDTO
	 */
	private void addToArticleTable(QuotationDetailsDTO detail) {
		decimalFormat = new DecimalFormat("0.00");
		TableItem[] items = tblArticle.getItems();
		int len = items.length;
		boolean isPresent = false;
		TableItem tiArticle;
		int index = 0;
		for (int i = 0; i < len; i++) {
			if (detail.getArticleName().equalsIgnoreCase(items[i].getText(0))) {
				isPresent = true;
				/*if (detail.getChargedWeight() != Float.parseFloat(items[i].getText(1))) {
					if (quotationDTO != null) {
						QuotationDetailsDTO[] quotDetails = quotationDTO.getQuotationDetails();
						quotDetails[i].setBft(null);
					}
				}*/
				index = i;
				break;
			}

		}
		if (!isPresent) {
			tiArticle = new TableItem(tblArticle, SWT.NONE);

			tiArticle.setText(0, detail.getArticleName());
			tiArticle.setText(1, decimalFormat.format(detail.getChargedWeight()));
			tiArticle.setText(2, optionB[detail.getCcchargeType()]);
			tiArticle.setText(3, decimalFormat.format(detail.getCcchargeValue()));
			tiArticle.setText(4, optionB[detail.getDcchargeType()]);
			tiArticle.setText(5, decimalFormat.format(detail.getDcchargeValue()));
			tiArticle.setText(6, optionB[detail.getIechargeType()]);
			tiArticle.setText(7, decimalFormat.format(detail.getIechargeValue()));
			tiArticle.setText(8, optionB[detail.getLcchargeType()]);
			tiArticle.setText(9, decimalFormat.format(detail.getLcchargeValue()));
			tiArticle.setText(10, optionC[detail.getDdchargeType()]);
			tiArticle.setText(11, decimalFormat.format(detail.getMinDdchargeValue()));
			tiArticle.setText(12, decimalFormat.format(detail.getDdchargeArticle()));
		} else {

			TableItem item = tblArticle.getItem(index);
			item.setText(0, detail.getArticleName());
			item.setText(1, decimalFormat.format(detail.getChargedWeight()));
			item.setText(2, optionB[detail.getCcchargeType()]);
			item.setText(3, decimalFormat.format(detail.getCcchargeValue()));
			item.setText(4, optionB[detail.getDcchargeType()]);
			item.setText(5, decimalFormat.format(detail.getDcchargeValue()));
			item.setText(6, optionB[detail.getIechargeType()]);
			item.setText(7, decimalFormat.format(detail.getIechargeValue()));
			item.setText(8, optionB[detail.getLcchargeType()]);
			item.setText(9, decimalFormat.format(detail.getLcchargeValue()));
			item.setText(10, optionC[detail.getDdchargeType()]);
			item.setText(11, decimalFormat.format(detail.getMinDdchargeValue()));
			item.setText(12, decimalFormat.format(detail.getDdchargeArticle()));
		}

	}

	/**
	 * Method to check whether the selected booking type is inward or not.
	 * 
	 * @return boolean 
	 */
	private boolean isInwardType() {
		boolean isInward = false;

		//Booking Type
		if (cbBookingType.getText().equals(INWARD_TYPE))
			isInward = true;

		return isInward;
	}

	/**
	 * Method to get the quotation details
	 * 
	 * @return QuotationDetailsDTO[]  Array instance of QuotationDetailsDTO
	 */
	private QuotationDetailsDTO[] getQuotationDetails() {
		QuotationDetailsDTO[] details = null;

		if (tblArticle.isVisible()) {
			details = getQuotationDetailsForMixedArticle();
		} else {
			details = new QuotationDetailsDTO[1];
			details[0] = getQuotationDetailsDTO();
		}

		return details;
	}

	/**
	 * Method to get the LRC & GSC Values
	 * @return OtherChargesDTO[]
	 */
	private OtherChargesDTO[] getOtherCharges() {
		OtherChargesDTO[] others = null;
		//System.out.println("lrc==>" + lrcDTO);
		//System.out.println("gsc==>" + gscDTO);

		if (lrcDTO != null) {
			if (gscDTO != null) {
				//Combine LRC GSC
				others = combineLrcGsc(lrcDTO, gscDTO);
			} else {
				others = lrcDTO;
			}
		} else if (gscDTO != null) {
			others = gscDTO;
		}
		if (others == null) {
			/*if(quotationDTO.getOtherCharges() != null){
				for(int i = 0; i < quotationDTO.getOtherCharges().length; i++){
					System.out.println("Avail Staions==>" + quotationDTO.getOtherCharges()[i].getStationCode());
					System.out.println("Avail LRC==>" + quotationDTO.getOtherCharges()[i].getLrCharge());
					System.out.println("Avail GSC==>" + quotationDTO.getOtherCharges()[i].getGsc());
				}}*/
			return quotationDTO.getOtherCharges();
		} else {
			if (quotationDTO != null)
				otherChargesDTO = quotationDTO.getOtherCharges();
			if (otherChargesDTO != null) {
				//Update OtherChargesDTO with existing DTO
				otherChargesDTO = updateOtherCharges(others, otherChargesDTO);
				/*for(int i = 0; i < otherChargesDTO.length; i++){
					System.out.println("Updated Staions==>" + otherChargesDTO[i].getStationCode());
					System.out.println("Updated LRC==>" + otherChargesDTO[i].getLrCharge());
					System.out.println("Updated GSC==>" + otherChargesDTO[i].getGsc());
				}*/
				return otherChargesDTO;
			} else {
				OtherChargesDTO[] othersAllStations = null;
				try {
					othersAllStations = convertOthersAllStations(others);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return othersAllStations;
			}
		}
	}

	/**
	 * Method to Set OtherCharges for AllStations
	 * @param others
	 * @return
	 * @throws Exception
	 */
	private OtherChargesDTO[] convertOthersAllStations(OtherChargesDTO[] others) throws Exception {
		StationsDTO[] allStations = null;
		OtherChargesDTO[] othersAllStations = null;
		int len = 0;

		allStations = handler.getAllStations();
		len = allStations.length;
		othersAllStations = new OtherChargesDTO[len];
		for (int i = 0; i < len; i++) {
			othersAllStations[i] = new OtherChargesDTO();
			othersAllStations[i].setStationCode(allStations[i].getId());
			othersAllStations[i].setLrCharge(0);
			othersAllStations[i].setGsc(0);

			//Setting LRC , GSC for Selected Stations
			for (int j = 0; j < others.length; j++) {
				if (others[j].getStationCode().equals(othersAllStations[i].getStationCode())) {
					othersAllStations[i].setGsc(others[j].getGsc());
					othersAllStations[i].setLrCharge(others[j].getLrCharge());
					break;
				}
			}

			/*System.out.println("All Stations==>" + othersAllStations[i].getStationCode());
			System.out.println("All LRC==>" + othersAllStations[i].getLrCharge());
			System.out.println("All GSC==>" + othersAllStations[i].getGsc());*/
		}

		return othersAllStations;
	}

	/**
	 * Method to Combine LRC, GSC
	 * @param lrcDTO
	 * @param gscDTO
	 * @return
	 */
	private OtherChargesDTO[] combineLrcGsc(OtherChargesDTO[] lrcDTO, OtherChargesDTO[] gscDTO) {
		int gscLen = 0;
		int lrcLen = 0;
		int finalLen = 0;
		ArrayList<OtherChargesDTO> listGsc = null;
		OtherChargesDTO[] others = null;

		lrcLen = lrcDTO.length;
		gscLen = gscDTO.length;
		listGsc = new ArrayList<OtherChargesDTO>(Arrays.asList(gscDTO));
		for (int i = 0; i < lrcLen; i++) {
			for (int j = 0; j < gscLen; j++) {
				if (gscDTO[j].getStationCode().equals(lrcDTO[i].getStationCode())) {
					lrcDTO[i].setGsc(gscDTO[j].getGsc());
					listGsc.remove(gscDTO[j]);
				}
			}
		}
		finalLen = lrcLen + listGsc.size();
		others = new OtherChargesDTO[finalLen];
		OtherChargesDTO newGSCDTO = null;
		for (int k = 0, l = 0; k < finalLen; k++) {
			others[k] = new OtherChargesDTO();
			if (k < lrcLen) {
				others[k] = lrcDTO[k];
			} else {
				newGSCDTO = (OtherChargesDTO) listGsc.get(l);
				others[k].setStationCode(newGSCDTO.getStationCode());
				//others[k].setLrCharge(0);
				others[k].setGsc(newGSCDTO.getGsc());
				l++;
			}
		/*	System.out.println("Staions==>" + others[k].getStationCode());
			System.out.println("LRC==>" + others[k].getLrCharge());
			System.out.println("GSC==>" + others[k].getGsc());*/
		}

		return others;
	}

	/**
	 * Method to update OtherChargesDTO
	 * @param newOthers
	 * @param availOthers
	 * @return
	 */
	private OtherChargesDTO[] updateOtherCharges(OtherChargesDTO[] newOthers, OtherChargesDTO[] availOthers) {

		for (int i = 0; i < newOthers.length; i++) {
			for (int j = 0; j < availOthers.length; j++) {
				if (newOthers[i].getStationCode().equals(availOthers[j].getStationCode())) {
					if (newOthers[i].getGsc() != 0)
						availOthers[j].setGsc(newOthers[i].getGsc());
					if (newOthers[i].getLrCharge() != 0)
						availOthers[j].setLrCharge(newOthers[i].getLrCharge());

					break;
				}
			}

		}
		return availOthers;
	}

	/**
	 * Method to deInitialize LRC values
	 * @param otherChargesDTO
	 * @return
	 */
	private OtherChargesDTO[] deInitializeLRC(OtherChargesDTO[] otherChargesDTO) {
		for (int i = 0; i < otherChargesDTO.length; i++) {
			otherChargesDTO[i].setLrCharge(0);
		}
		return otherChargesDTO;
	}

	/**
	 * Method to deInitialize GSC values
	 * @param otherChargesDTO
	 * @return
	 */
	private OtherChargesDTO[] deInitializeGSC(OtherChargesDTO[] otherChargesDTO) {
		for (int i = 0; i < otherChargesDTO.length; i++) {
			otherChargesDTO[i].setGsc(0);
		}
		return otherChargesDTO;
	}

	/**
	 * 
	 * @param view
	 * @return
	 */
	public Composite loadComposite() {

		//this.setSize(800, 500);

		{
			{
				lblCustomerNames = new Label(this, SWT.NONE);
				lblCustomerNames.setText("Customer");
				lblCustomerNames.setBounds(8, 58, 60, 18);
				lblCustomerNames.setForeground(QUOTATION_COLOR);
			}
			{
				txtType = new Text(this, SWT.BORDER | SWT.READ_ONLY);
				txtType.setBounds(127, 24, 100, 21);
			}
			{
				lblType = new Label(this, SWT.NONE);
				lblType.setText("Type");
				lblType.setBounds(127, 5, 56, 13);
				lblType.setForeground(QUOTATION_COLOR);
			}
			
			{
				lblStationName = new Label(this, SWT.NONE);
				lblStationName.setText("Station Code");
				lblStationName.setBounds(8, 5, 78, 14);
				lblStationName.setForeground(QUOTATION_COLOR);
			}
			{
				txtStation = new Text(this, SWT.BORDER | SWT.READ_ONLY);
				txtStation.setBounds(9, 24, 100, 21);
			}

			{
				lstCustomerNames = new List(this, SWT.BORDER | SWT.V_SCROLL);
				lstCustomerNames.setBounds(7, 81, 100, 389);
				lstCustomerNames.addSelectionListener(new QuotationAction());
			}
			{
				lblBookingType = new Label(this, SWT.NONE);
				lblBookingType.setText("Booking Type");
				lblBookingType.setBounds(127, 60, 83, 15);
				lblBookingType.setForeground(QUOTATION_COLOR);
			}

			{
				lblPriceIndex = new Label(this, SWT.NONE);
				lblPriceIndex.setText("Price Index");
				lblPriceIndex.setBounds(291, 60, 61, 17);
				lblPriceIndex.setForeground(QUOTATION_COLOR);
			}

			{
				lblMinCW = new Label(this, SWT.NONE);
				lblMinCW.setText("Min Chargable Weight");
				lblMinCW.setBounds(455, 60, 114, 16);
				lblMinCW.setForeground(QUOTATION_COLOR);
			}

			{
				lblMinCF = new Label(this, SWT.NONE);
				lblMinCF.setText("Min Chargable Freight");
				lblMinCF.setBounds(623, 60, 110, 16);
				lblMinCF.setForeground(QUOTATION_COLOR);
			}

			{
				cbBookingType = new Combo(this, SWT.READ_ONLY);
				cbBookingType.setBounds(127, 80, 100, 21);
				cbBookingType.add(INWARD_TYPE);
				cbBookingType.add(OUTWARD_TYPE);
				//cbBookingType.select(1);
			}

			{
				txtPriceIndex = new Text(this, SWT.BORDER);
				txtPriceIndex.setBounds(291, 80, 100, 21);
				txtPriceIndex.addSelectionListener(new QuotationAction());
				txtPriceIndex.addVerifyListener(new FloatValidation());
			}

			{
				txtMinCW = new Text(this, SWT.BORDER);
				txtMinCW.setBounds(455, 80, 100, 21);
				txtMinCW.addVerifyListener(new FloatLimitValidation());
			
				// To enter values
				txtMinCW.addMouseListener(new MouseListener() {

					@Override
					public void mouseDoubleClick(MouseEvent event) {						
						txtMinCW.setMessage("DC");
						//System.out.println("sel conut==>"+txtLRC[index].getSelectionCount());
					}

					@Override
					public void mouseDown(MouseEvent arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseUp(MouseEvent arg0) {
						// TODO Auto-generated method stub

					}

				});

				txtMinCW.addKeyListener(new KeyListener() {

					@Override
					public void keyPressed(KeyEvent event) {
						if (txtMinCW.getMessage().equals("DC") && event.keyCode != 39) {							
							txtMinCW.setText("");
							txtMinCW.setMessage("");

						}

					}

					@Override
					public void keyReleased(KeyEvent arg0) {
						// TODO Auto-generated method stub

					}

				});
				
				txtMinCW.addFocusListener(new FocusListener(){

					@Override
					public void focusGained(FocusEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void focusLost(FocusEvent arg0) {
						txtMinCW.setMessage("");
						
					}
					
				});
			}
			{
				txtMinCF = new Text(this, SWT.BORDER);
				txtMinCF.setBounds(623, 80, 100, 21);
				txtMinCF.addVerifyListener(new FloatLimitValidation());
				
				// To enter values
				txtMinCF.addMouseListener(new MouseListener() {

					@Override
					public void mouseDoubleClick(MouseEvent event) {						
						txtMinCF.setMessage("DC");
						//System.out.println("sel conut==>"+txtLRC[index].getSelectionCount());
					}

					@Override
					public void mouseDown(MouseEvent arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseUp(MouseEvent arg0) {
						// TODO Auto-generated method stub

					}

				});

				txtMinCF.addKeyListener(new KeyListener() {

					@Override
					public void keyPressed(KeyEvent arg0) {
						if (txtMinCF.getMessage().equals("DC")) {

							txtMinCF.setText("");
							txtMinCF.setMessage("");

						}

					}

					@Override
					public void keyReleased(KeyEvent arg0) {
						// TODO Auto-generated method stub

					}

				});
				
				txtMinCF.addFocusListener(new FocusListener(){

					@Override
					public void focusGained(FocusEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void focusLost(FocusEvent arg0) {
						txtMinCF.setMessage("");
						
					}
					
				});

			}
			{
				lblLRC = new Label(this, SWT.NONE);
				lblLRC.setText("LRC");
				lblLRC.setBounds(127, 126, 91, 19);
				lblLRC.setForeground(QUOTATION_COLOR);
			}

			//Second Row

			{
				lblGSC = new Label(this, SWT.NONE);
				lblGSC.setText("GSC");
				lblGSC.setBounds(291, 126, 87, 17);
				lblGSC.setForeground(QUOTATION_COLOR);

			}
			{
				lblInsurance = new Label(this, SWT.NONE);
				lblInsurance.setText("Insurance");
				lblInsurance.setBounds(455, 126, 112, 19);
				lblInsurance.setForeground(QUOTATION_COLOR);

			}

			{
				lblDHC = new Label(this, SWT.NONE);
				lblDHC.setText("DHC");
				lblDHC.setBounds(623, 126, 80, 17);
				lblDHC.setForeground(QUOTATION_COLOR);

			}
			
			//
			{
				cbLRC = new Combo(this, SWT.READ_ONLY);
				cbLRC.setBounds(127, 145, 100, 21);
				cbLRC.addSelectionListener(new QuotationAction());

				cbLRC.add(optionA[0]);
				cbLRC.add(optionA[1]);
				//cbLRC.select(0);

				btnLRCPop = new Button(this, SWT.PUSH);
				btnLRCPop.setBounds(229, 145, 20, 21);
				btnLRCPop.setEnabled(false);
				btnLRCPop.addSelectionListener(new QuotationAction());

			}
			{
				cbGSC = new Combo(this, SWT.READ_ONLY);
				cbGSC.setBounds(291, 145, 100, 21);
				cbGSC.add(optionA[0]);
				cbGSC.add(optionA[1]);
				//cbGSC.select(0);
				cbGSC.addSelectionListener(new QuotationAction());

				btnGSCPop = new Button(this, SWT.PUSH);
				btnGSCPop.setBounds(393, 145, 20, 21);
				btnGSCPop.setEnabled(false);
				btnGSCPop.addSelectionListener(new QuotationAction());

			}
			{

				cbInsurance = new Combo(this, SWT.READ_ONLY);
				cbInsurance.setBounds(455, 145, 100, 21);
				cbInsurance.add(optionA[0]);
				cbInsurance.add(optionA[1]);
				cbInsurance.add(optionA[2]);
				//cbInsurance.select(0);
				cbInsurance.addSelectionListener(new QuotationAction());

				btnInsurancePop = new Button(this, SWT.PUSH);
				btnInsurancePop.setBounds(559, 146, 20, 21);
				btnInsurancePop.setEnabled(false);
				btnInsurancePop.addSelectionListener(new QuotationAction());
			}

			
			//DHC
			{
				cbDHC = new Combo(this, SWT.READ_ONLY);
				cbDHC.setBounds(623, 145, 75, 21);
				cbDHC.addSelectionListener(new QuotationAction());

				cbDHC.add(optionA[0]);
				cbDHC.add(optionA[1]);				

				btnDHCPop = new Button(this, SWT.PUSH);
				btnDHCPop.setBounds(698, 145, 20, 21);
				btnDHCPop.setEnabled(false);
				btnDHCPop.addSelectionListener(new QuotationAction());
			}
			
			//ST
			{
				chkServiceTax = new Button(this, SWT.CHECK);
				chkServiceTax.setBounds(722, 150, 14, 13);

			}
			{
				lblST = new Label(this, SWT.NONE);
				lblST.setText("Service Tax");
				lblST.setBounds(741, 150, 82, 20);
				lblST.setForeground(QUOTATION_COLOR);
			}

			/*Group articleGroup = new Group(this, SWT.NONE);
			articleGroup.setText("dasdasd");
			articleGroup.setBounds(118, 151, 627, 161);			
			 */
			{

				{
					txtArticleName = new Text(this, SWT.BORDER);
					txtArticleName.setBounds(127, 218, 100, 21);
					txtArticleName.addFocusListener(this);

				}
			}

			{
				{
					lblCW = new Label(this, SWT.NONE);
					lblCW.setText("ChargedWeight");
					lblCW.setBounds(291, 198, 92, 15);
					lblCW.setForeground(QUOTATION_COLOR);
				}

				{
					txtCW = new Text(this, SWT.BORDER);
					txtCW.setBounds(291, 218, 100, 21);
					txtCW.addVerifyListener(new FloatLimitValidation());
					
					// To enter values
					txtCW.addMouseListener(new MouseListener() {

						@Override
						public void mouseDoubleClick(MouseEvent event) {						
							txtCW.setMessage("DC");
							//System.out.println("sel conut==>"+txtLRC[index].getSelectionCount());
						}

						@Override
						public void mouseDown(MouseEvent arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void mouseUp(MouseEvent arg0) {
							// TODO Auto-generated method stub

						}

					});

					txtCW.addKeyListener(new KeyListener() {

						@Override
						public void keyPressed(KeyEvent arg0) {
							if (txtCW.getMessage().equals("DC")) {

								txtCW.setText("");
								txtCW.setMessage("");

							}

						}

						@Override
						public void keyReleased(KeyEvent arg0) {
							// TODO Auto-generated method stub

						}

					});
					
					txtCW.addFocusListener(new FocusListener(){

						@Override
						public void focusGained(FocusEvent arg0) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void focusLost(FocusEvent arg0) {
							txtCW.setMessage("");
							
						}
						
					});
				}
			}

			{
				cccGroup = new Group(this, SWT.NONE);
				cccGroup.setText("CCC");
				cccGroup.setBounds(128, 254, 121, 83);
				{
					cbCCC = new Combo(cccGroup, SWT.READ_ONLY);
					cbCCC.setBounds(6, 18, 100, 21);
					cbCCC.add(optionB[0]);
					cbCCC.add(optionB[1]);
					cbCCC.add(optionB[2]);
					cbCCC.add(optionB[3]);
					//cbCCC.select(2);
					cbCCC.addSelectionListener(new QuotationAction());

					txtCCCValue = new Text(cccGroup, SWT.BORDER);
					txtCCCValue.setEnabled(false);
					txtCCCValue.setBounds(6, 48, 100, 21);
					txtCCCValue.addVerifyListener(new FloatLimitValidation());
					
					// To enter values
					txtCCCValue.addMouseListener(new MouseListener() {

						@Override
						public void mouseDoubleClick(MouseEvent event) {						
							txtCCCValue.setMessage("DC");
							//System.out.println("sel conut==>"+txtLRC[index].getSelectionCount());
						}

						@Override
						public void mouseDown(MouseEvent arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void mouseUp(MouseEvent arg0) {
							// TODO Auto-generated method stub

						}

					});

					txtCCCValue.addKeyListener(new KeyListener() {

						@Override
						public void keyPressed(KeyEvent arg0) {
							if (txtCCCValue.getMessage().equals("DC")) {

								txtCCCValue.setText("");
								txtCCCValue.setMessage("");

							}

						}

						@Override
						public void keyReleased(KeyEvent arg0) {
							// TODO Auto-generated method stub

						}

					});
					
					txtCCCValue.addFocusListener(new FocusListener(){

						@Override
						public void focusGained(FocusEvent arg0) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void focusLost(FocusEvent arg0) {
							txtCCCValue.setMessage("");
							
						}
						
					});

				}
			}
			{
				dccGroup = new Group(this, SWT.NONE);
				dccGroup.setText("DCC");
				dccGroup.setBounds(290, 253, 123, 84);
				{
					cbDCC = new Combo(dccGroup, SWT.READ_ONLY);
					cbDCC.setBounds(6, 18, 100, 21);
					cbDCC.add(optionB[0]);
					cbDCC.add(optionB[1]);
					cbDCC.add(optionB[2]);
					cbDCC.add(optionB[3]);
					//cbDCC.select(2);
					cbDCC.addSelectionListener(new QuotationAction());

					txtDCCValue = new Text(dccGroup, SWT.BORDER);
					txtDCCValue.setEnabled(false);
					txtDCCValue.setBounds(6, 48, 100, 21);
					txtDCCValue.addVerifyListener(new FloatLimitValidation());
					
					// To enter values
					txtDCCValue.addMouseListener(new MouseListener() {

						@Override
						public void mouseDoubleClick(MouseEvent event) {						
							txtDCCValue.setMessage("DC");
							//System.out.println("sel conut==>"+txtLRC[index].getSelectionCount());
						}

						@Override
						public void mouseDown(MouseEvent arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void mouseUp(MouseEvent arg0) {
							// TODO Auto-generated method stub

						}

					});

					txtDCCValue.addKeyListener(new KeyListener() {

						@Override
						public void keyPressed(KeyEvent arg0) {
							if (txtDCCValue.getMessage().equals("DC")) {

								txtDCCValue.setText("");
								txtDCCValue.setMessage("");

							}

						}

						@Override
						public void keyReleased(KeyEvent arg0) {
							// TODO Auto-generated method stub

						}

					});
					
					txtDCCValue.addFocusListener(new FocusListener(){

						@Override
						public void focusGained(FocusEvent arg0) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void focusLost(FocusEvent arg0) {
							txtDCCValue.setMessage("");
							
						}
						
					});


				}
			}

			{
				iecGroup = new Group(this, SWT.NONE);
				iecGroup.setText("IEC");
				iecGroup.setBounds(455, 198, 119, 81);
				{
					cbIEC = new Combo(iecGroup, SWT.READ_ONLY);
					cbIEC.setBounds(6, 18, 100, 21);
					cbIEC.add(optionB[0]);
					cbIEC.add(optionB[1]);
					cbIEC.add(optionB[3]);
					//cbIEC.select(1);
					cbIEC.addSelectionListener(new QuotationAction());

					txtIEC_article = new Text(iecGroup, SWT.BORDER);
					txtIEC_article.setEnabled(true);
					txtIEC_article.setBounds(6, 48, 100, 21);
					txtIEC_article.addVerifyListener(new FloatLimitValidation());
					
					// To enter values
					txtIEC_article.addMouseListener(new MouseListener() {

						@Override
						public void mouseDoubleClick(MouseEvent event) {						
							txtIEC_article.setMessage("DC");
							//System.out.println("sel conut==>"+txtLRC[index].getSelectionCount());
						}

						@Override
						public void mouseDown(MouseEvent arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void mouseUp(MouseEvent arg0) {
							// TODO Auto-generated method stub

						}

					});

					txtIEC_article.addKeyListener(new KeyListener() {

						@Override
						public void keyPressed(KeyEvent arg0) {
							if (txtIEC_article.getMessage().equals("DC")) {

								txtIEC_article.setText("");
								txtIEC_article.setMessage("");

							}

						}

						@Override
						public void keyReleased(KeyEvent arg0) {
							// TODO Auto-generated method stub

						}

					});
					
					txtIEC_article.addFocusListener(new FocusListener(){

						@Override
						public void focusGained(FocusEvent arg0) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void focusLost(FocusEvent arg0) {
							txtIEC_article.setMessage("");
							
						}
						
					});

				}
			}
			{
				loadingchargeGroup = new Group(this, SWT.NONE);

				loadingchargeGroup.setText("Loading Charge");
				loadingchargeGroup.setBounds(626, 198, 118, 85);
				{
					cbLoadingCharge = new Combo(loadingchargeGroup, SWT.READ_ONLY);
					cbLoadingCharge.setBounds(6, 18, 100, 21);
					cbLoadingCharge.add(optionB[0]);
					cbLoadingCharge.add(optionB[1]);
					cbLoadingCharge.add(optionB[3]);
					//cbLoadingCharge.select(1);
					cbLoadingCharge.addSelectionListener(new QuotationAction());

					txtLC_article = new Text(loadingchargeGroup, SWT.BORDER);
					txtLC_article.setEnabled(true);
					txtLC_article.setBounds(8, 50, 100, 21);
					txtLC_article.addVerifyListener(new FloatLimitValidation());
					
					// To enter values
					txtLC_article.addMouseListener(new MouseListener() {

						@Override
						public void mouseDoubleClick(MouseEvent event) {						
							txtLC_article.setMessage("DC");
							//System.out.println("sel conut==>"+txtLRC[index].getSelectionCount());
						}

						@Override
						public void mouseDown(MouseEvent arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void mouseUp(MouseEvent arg0) {
							// TODO Auto-generated method stub

						}

					});

					txtLC_article.addKeyListener(new KeyListener() {

						@Override
						public void keyPressed(KeyEvent arg0) {
							if (txtLC_article.getMessage().equals("DC")) {

								txtLC_article.setText("");
								txtLC_article.setMessage("");

							}

						}

						@Override
						public void keyReleased(KeyEvent arg0) {
							// TODO Auto-generated method stub

						}

					});
					
					txtLC_article.addFocusListener(new FocusListener(){

						@Override
						public void focusGained(FocusEvent arg0) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void focusLost(FocusEvent arg0) {
							txtLC_article.setMessage("");
							
						}
						
					});


				}
			}
			{
				ddcGroup = new Group(this, SWT.NONE);

				ddcGroup.setText("DDC");
				ddcGroup.setBounds(451, 292, 294, 45);

				{
					cbDDC = new Combo(ddcGroup, SWT.READ_ONLY);
					cbDDC.setBounds(6, 18, 65, 21);
					cbDDC.add(optionC[0]);
					cbDDC.add(optionC[1]);
					cbDDC.add(optionC[2]);
					//cbDDC.select(2);
					cbDDC.addSelectionListener(new QuotationAction());
				}
				
				{
					lblMinLR = new Label(ddcGroup, SWT.None);
					lblMinLR.setBounds(75, 20, 33, 21);
					lblMinLR.setText("Min/LR");

					txtDDC_minPerLR = new Text(ddcGroup, SWT.BORDER);
					txtDDC_minPerLR.setEnabled(false);
					txtDDC_minPerLR.setBounds(110, 18, 50, 21);
					txtDDC_minPerLR.addVerifyListener(new FloatLimitValidation());
					
					// To enter values
					txtDDC_minPerLR.addMouseListener(new MouseListener() {

						@Override
						public void mouseDoubleClick(MouseEvent event) {						
							txtDDC_minPerLR.setMessage("DC");
							//System.out.println("sel conut==>"+txtLRC[index].getSelectionCount());
						}

						@Override
						public void mouseDown(MouseEvent arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void mouseUp(MouseEvent arg0) {
							// TODO Auto-generated method stub

						}

					});

					txtDDC_minPerLR.addKeyListener(new KeyListener() {

						@Override
						public void keyPressed(KeyEvent arg0) {
							if (txtDDC_minPerLR.getMessage().equals("DC")) {

								txtDDC_minPerLR.setText("");
								txtDDC_minPerLR.setMessage("");

							}

						}

						@Override
						public void keyReleased(KeyEvent arg0) {
							// TODO Auto-generated method stub

						}

					});
					
					txtDDC_minPerLR.addFocusListener(new FocusListener(){

						@Override
						public void focusGained(FocusEvent arg0) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void focusLost(FocusEvent arg0) {
							txtDDC_minPerLR.setMessage("");
							
						}
						
					});


					lblchargeArticle = new Label(ddcGroup, SWT.None);
					lblchargeArticle.setBounds(165, 20, 70, 21);
					lblchargeArticle.setText("Charge/Article");
					
					txtDDC_chargeArticle = new Text(ddcGroup, SWT.BORDER);
					txtDDC_chargeArticle.setEnabled(false);
					txtDDC_chargeArticle.setBounds(237, 18, 50, 21);
					txtDDC_chargeArticle.addVerifyListener(new FloatLimitValidation());
					
					// To enter values
					txtDDC_chargeArticle.addMouseListener(new MouseListener() {

						@Override
						public void mouseDoubleClick(MouseEvent event) {						
							txtDDC_chargeArticle.setMessage("DC");
							//System.out.println("sel conut==>"+txtLRC[index].getSelectionCount());
						}

						@Override
						public void mouseDown(MouseEvent arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void mouseUp(MouseEvent arg0) {
							// TODO Auto-generated method stub

						}

					});

					txtDDC_chargeArticle.addKeyListener(new KeyListener() {

						@Override
						public void keyPressed(KeyEvent arg0) {
							if (txtDDC_chargeArticle.getMessage().equals("DC")) {

								txtDDC_chargeArticle.setText("");
								txtDDC_chargeArticle.setMessage("");

							}

						}

						@Override
						public void keyReleased(KeyEvent arg0) {
							// TODO Auto-generated method stub

						}

					});
					
					txtDDC_chargeArticle.addFocusListener(new FocusListener(){

						@Override
						public void focusGained(FocusEvent arg0) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void focusLost(FocusEvent arg0) {
							txtDDC_chargeArticle.setMessage("");
							
						}
						
					});
				}
				
			}
			{
				btnAdd = new Button(this, SWT.PUSH | SWT.CENTER);
				btnAdd.setText("Add");
				btnAdd.setBounds(753, 310, 40, 25);
				btnAdd.addSelectionListener(new QuotationAction());
			}
			{
				lblArtilcleName = new Label(this, SWT.NONE);
				lblArtilcleName.setText("ArticleName");
				lblArtilcleName.setBounds(127, 198, 63, 19);
				lblArtilcleName.setForeground(QUOTATION_COLOR);
			}

			{
				/*gpArticleTable = new Group(this, SWT.NONE);
				gpArticleTable.setText("MultipleArticle");
				gpArticleTable.setBounds(115, 344, 673, 126);*/

				{

					tblArticle = new Table(this, SWT.H_SCROLL | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.BORDER
							| SWT.MULTI);
					tblArticle.setHeaderVisible(true);
					tblArticle.setLinesVisible(true);
					tblArticle.setBounds(123, 345, 622, 120);

					tblArticle.addSelectionListener(new QuotationAction());

					tblArticle.addKeyListener(new KeyListener() {
						@Override
						public void keyPressed(KeyEvent arg0) {

						}

						@Override
						public void keyReleased(KeyEvent e) {
							int itemsLen = tblArticle.getItemCount();
							int index = tblArticle.getSelectionIndex();
							if (e.keyCode == SWT.DEL) {
								if (tblArticle.getSelectionCount() == 1) {
									tblArticle.remove(index);

									if (itemsLen == 1) {
										clearArticleFields();
									} else if (index < (itemsLen - 1)) {
										tblArticle.setSelection(index);
									} else if (index == (itemsLen - 1)) {
										tblArticle.setSelection(index - 1);
									}
									viewEditTblArticle();
								}
							}
						}

					});

					{
						TableColumn colName = new TableColumn(tblArticle, SWT.NONE);
						colName.setText("Name");
						colName.setWidth(88);
					}

					{
						TableColumn colChrgWt = new TableColumn(tblArticle, SWT.NONE);
						colChrgWt.setText("CW");
						colChrgWt.setWidth(33);
					}
					{
						TableColumn colCCC = new TableColumn(tblArticle, SWT.NONE);
						colCCC.setText("CCC");
						colCCC.setWidth(38);
					}
					{
						TableColumn colCCCValue = new TableColumn(tblArticle, SWT.NONE);
						colCCCValue.setText("CCCValue");
						colCCCValue.setWidth(62);
					}
					{
						TableColumn colDCC = new TableColumn(tblArticle, SWT.NONE);
						colDCC.setText("DCC");
						colDCC.setWidth(40);
					}
					{
						TableColumn colDCCValue = new TableColumn(tblArticle, SWT.NONE);
						colDCCValue.setText("DCCValue");
						colDCCValue.setWidth(63);
					}
					{
						TableColumn colIEC = new TableColumn(tblArticle, SWT.NONE);
						colIEC.setText("IEC");
						colIEC.setWidth(34);
					}
					{
						TableColumn colIECValue = new TableColumn(tblArticle, SWT.NONE);
						colIECValue.setText("IECValue");
						colIECValue.setWidth(59);
					}
					{
						TableColumn colLC = new TableColumn(tblArticle, SWT.NONE);
						colLC.setText("LC");
						colLC.setWidth(34);
					}
					{
						TableColumn colLCValue = new TableColumn(tblArticle, SWT.NONE);
						colLCValue.setText("LCValue");
						colLCValue.setWidth(53);
					}
					{
						TableColumn colDDC = new TableColumn(tblArticle, SWT.NONE);
						colDDC.setText("DDC");
						colDDC.setWidth(40);
					}
					{
						TableColumn colDDCMinLR = new TableColumn(tblArticle, SWT.NONE);
						colDDCMinLR.setText("DDC Min/LR");
						colDDCMinLR.setWidth(73);
					}
					{
						TableColumn colDDCCost = new TableColumn(tblArticle, SWT.NONE);
						colDDCCost.setText("DDC Cost/Art");
						colDDCCost.setWidth(88);
					}
				}
			}
			{
				lblRoundOffDigits = new Label(this, SWT.NONE);
				lblRoundOffDigits.setText("Round Off Digits");
				lblRoundOffDigits.setBounds(610, 472, 90, 16);
				lblRoundOffDigits.setForeground(QUOTATION_COLOR);
			}
			
			{
				txtRoundOffDigits = new Text(this, SWT.BORDER);
				txtRoundOffDigits.setBounds(700, 472, 50, 20);
				txtRoundOffDigits.addVerifyListener(new NumericValidation());
			}
			

			/*{
				lblRoundOffDigits = new Label(this, SWT.NONE);
				lblRoundOffDigits.setText("Round Off Digits");
				lblRoundOffDigits.setBounds(291, 5, 100, 16);
				lblRoundOffDigits.setForeground(QUOTATION_COLOR);
			}
			
			{
				txtRoundOffDigits = new Text(this, SWT.BORDER);
				txtRoundOffDigits.setBounds(291, 24, 100, 21);
				txtRoundOffDigits.addVerifyListener(new NumericValidation());
			}*/

			
			
			try {
				if (quotList != null)
					populateCustomers(quotList);
				if (quotationDTO != null) {				
					loadValues(quotationDTO);
					for (int i = 0; i < quotList.length; i++) {
						if (quotationDTO.getCustomerName().equals(quotList[i].getCustomerName())) {
							lstCustomerNames.select(i);
							break;
						}
					}
				}
			} catch (Exception exception) {
				exception.printStackTrace();
			}
			return this;
		}
	}

	/**
	 * Method to populate Customers
	 * @param quotList
	 */
	private void populateCustomers(QuotationDTO[] quotList) {		
		for (int i = 0; i < quotList.length; i++) {
			lstCustomerNames.add(quotList[i].getCustomerName());
		}
		/*String[] items = lstCustomerNames.getItems();
		Arrays.sort(items, String.CASE_INSENSITIVE_ORDER);
		lstCustomerNames.removeAll();
		lstCustomerNames.setItems(items);*/
	}

	/**
	 * Method to populate Quotation
	 * @param customer
	 */
	private void populateQuotation(String customer) {
		for (int i = 0; i < quotList.length; i++) {
			if (customer.equalsIgnoreCase(quotList[i].getCustomerName())) {
				this.quotationDTO = quotList[i];
				loadValues(quotList[i]);
				break;
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	public boolean validateQuotationValues() {

		int customer = lstCustomerNames.getSelectionIndex();
		if (customer < 0) {
			AdminComposite.display("Please Select Customer", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;
		} else if (txtPriceIndex.getText().equals(EMPTYSTRING)) {
			AdminComposite.display("Please Enter Price Index", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;
		} else if (quotationDTO != null && (btnLRCPop.isEnabled() || btnGSCPop.isEnabled())
				&& quotationDTO.getOtherCharges() == null && lrcDTO == null && gscDTO == null) {
			AdminComposite.display("Please provide LRC/GSC Charges", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;
		} else if (quotationDTO != null && btnInsurancePop.isEnabled() && quotationDTO.getInsuranceCharges() == null && insuranceDTO == null) {
			AdminComposite.display("Please Provide Insurance Charges", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;
		} else if( quotationDTO != null && btnDHCPop.isEnabled() && quotationDTO.getDhcDto() == null && dhcDto == null){
			AdminComposite.display("Please provide DHC Charges", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;			
		} else if (txtArticleName.isEnabled() && txtArticleName.getText().equals(EMPTYSTRING)) {
			AdminComposite.display("Please Enter Article Name", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;
		} else if (txtCW.isEnabled() && txtCW.getText().equals(EMPTYSTRING)) {
			AdminComposite.display("Please Enter Charged Weight", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;
		} else if (tblArticle.isVisible() && tblArticle.getItemCount() == 0) {
			AdminComposite.display("Please Add a Article", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;
		}

		return true;
	}

	/**
	 * Method to get the Insurance Type
	 */
	private byte getInsuranceType() {
		String insuranceType = cbInsurance.getText();

		//Check with optionA
		for (byte i = 0; i < 3; i++) {
			if (optionA[i].equals(insuranceType))
				return i;
		}

		return 0;
	}

	/**
	 * Method to get the LR Charge Type
	 */
	private byte getLRChargeIndex() {
		String lrcType = cbLRC.getText();

		//Check with optionA
		for (byte i = 0; i < 3; i++) {
			if (optionA[i].equals(lrcType))
				return i;
		}

		return 0;
	}

	/**
	 * Method to get the LR Charge Type
	 */
	private byte getGSCIndex() {
		String gscType = cbGSC.getText();

		//Check with optionA
		for (byte i = 0; i < 3; i++) {
			if (optionA[i].equals(gscType))
				return i;
		}

		return 0;
	}

	/**
	 * CC Charge Index
	 * @return byte
	 */
	private byte getCCChargeIndex() {
		String ccCharge = cbCCC.getText();

		//Check with optionB
		for (byte i = 0; i < 4; i++) {
			if (optionB[i].equals(ccCharge))
				return i;
		}
		return 0;
	}

	/**
	 * DC Charge Type
	 * @return byte
	 */
	private byte getDCChargeType() {
		String dcCharge = cbDCC.getText();

		//Check with optionB
		for (byte i = 0; i < 4; i++) {
			if (optionB[i].equals(dcCharge))
				return i;
		}
		return 0;
	}

	/**
	 * DD Charge Type
	 * @return byte
	 */
	private byte getDDChargeType() {
		String ddCharge = cbDDC.getText();

		//Check with optionC
		for (byte i = 0; i < 3; i++) {
			if (optionC[i].equals(ddCharge))
				return i;
		}
		return 0;
	}

	/**
	 * DD Charge Type
	 * @return byte
	 */
	private byte getIEChargeType() {
		String ieCharge = cbIEC.getText();

		//Check with optionB
		for (byte i = 0; i < 4; i++) {
			if (optionB[i].equals(ieCharge))
				return i;
		}

		return 0;
	}

	/**
	 * LC Charge Type
	 * @return byte
	 */
	private byte getLCChargeType() {
		String lcCharge = cbLoadingCharge.getText();

		//Check with optionB
		for (byte i = 0; i < 4; i++) {
			if (optionB[i].equals(lcCharge))
				return i;
		}

		return 0;
	}

	/**
	 * Handler class for various user defined actions
	 * 
	 */
	public class QuotationAction implements SelectionListener {

		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {

		}

		/**
		 * Handle method for widget Selected
		 */
		@Override
		public void widgetSelected(SelectionEvent event) {

			Object source = event.getSource();

			if (source == cbLRC) { //LR Charge value change
				AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				handleLRCTypeChange();
			} else if (source == cbGSC) { //GSC value change
				AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				handleGSCTypeChange();
			} else if (source == cbDHC) { //DHC value change
				AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				handleDHCTypeChange();
			} else if (source == cbInsurance) { //Insurance Value Change
				AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				handleInsuranceTypeChange();
			} else if (source == cbCCC) { //CC Charge Type change
				AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				handleCCCTypeChange();
			} else if (source == cbDCC) { //DC Charge Type change
				AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				handleDCCTypeChange();
			} else if (source == cbIEC) { //IE Charge Type change
				AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				handleIECTypeChange();
			} else if (source == cbLoadingCharge) { //Loading Charge Type change
				AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				handleLCTypeChange();
			} else if (source == cbDDC) { //DD Charge Type change
				AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				handleDDCTypeChange();
			} else if (source == btnAdd) {
				AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				addArticle();
			} else if (source == tblArticle) {
				AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				viewEditTblArticle();
			}
			if (source == btnLRCPop) {
				AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				handleLRCPop();
			} else if (source == btnGSCPop) {
				AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				handleGSCPop();
			} else if (source == btnInsurancePop) {
				AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				handleInsurancePop();
			} else if (source == btnDHCPop) {
				AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				handleDHCPop();
			} else if (source == lstCustomerNames) {	
				AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				String customer = null;
				int index = -1;
				index = lstCustomerNames.getSelectionIndex();				
				if(index != -1){
					customer = lstCustomerNames.getItem(index);
					populateQuotation(customer);
					handleMixedArticleDropdown();
				}
					
			}
		}

	}

	/**
	 * Method to handle the LRC Value change
	 */
	private void handleLRCTypeChange() {
		String lrc = cbLRC.getText();

		if (optionA[1].equals(lrc))
			btnLRCPop.setEnabled(true);
		else {
			btnLRCPop.setEnabled(false);
		}
	}

	/**
	 * Method to handle the GSC Value change
	 */
	private void handleGSCTypeChange() {
		String gsc = cbGSC.getText();

		if (optionA[1].equals(gsc))
			btnGSCPop.setEnabled(true);
		else {
			btnGSCPop.setEnabled(false);
		}
	}

	/**
	 * Method to handle the Insurance value change event
	 */
	private void handleInsuranceTypeChange() {
		String insurance = cbInsurance.getText();

		if (optionA[1].equals(insurance))
			btnInsurancePop.setEnabled(true);
		else {
			btnInsurancePop.setEnabled(false);
		}
	}

	/**
	 * Method to handle the CCC Type change event
	 */
	private void handleCCCTypeChange() {
		String ccc = cbCCC.getText();

		if (optionB[2].equals(ccc) || optionB[3].equals(ccc)) {
			txtCCCValue.setEnabled(false);
			txtCCCValue.setText(EMPTYSTRING);			
		} else {
			txtCCCValue.setEnabled(true);
		}
	}

	/**
	 * Method to handle the DCC Type change event
	 */
	private void handleDCCTypeChange() {
		String dcc = cbDCC.getText();
		if (optionB[2].equals(dcc) || optionB[3].equals(dcc)) {
			txtDCCValue.setEnabled(false);
			txtDCCValue.setText(EMPTYSTRING);
		} else {
			txtDCCValue.setEnabled(true);
		}
	}
	
	/**
	 * Method to handle the IEC Type change event
	 */
	private void handleIECTypeChange() {
		String iec = cbIEC.getText();
		if (optionB[3].equals(iec)) {
			txtIEC_article.setEnabled(false);
			txtIEC_article.setText(EMPTYSTRING);
		} else {
			txtIEC_article.setEnabled(true);
		}
	}
	
	/**
	 * Method to handle the LC Type change event
	 */
	private void handleLCTypeChange() {
		String lc = cbLoadingCharge.getText();
		if (optionB[3].equals(lc)) {
			txtLC_article.setEnabled(false);
			txtLC_article.setText(EMPTYSTRING);
		} else {
			txtLC_article.setEnabled(true);
		}
	}
	
	
	
	/**
	 * Method to handle the DDC Type change event
	 */
	private void handleDDCTypeChange() {
		String ddc = cbDDC.getText();

		if (optionC[1].equals(ddc)) {
			txtDDC_minPerLR.setEnabled(true);
			txtDDC_chargeArticle.setEnabled(true);
		} else {
			txtDDC_minPerLR.setText(EMPTYSTRING);
			txtDDC_minPerLR.setEnabled(false);

			if (optionC[0].equals(ddc)) {
				txtDDC_chargeArticle.setEnabled(true);
			} else {
				txtDDC_chargeArticle.setEnabled(false);
				txtDDC_chargeArticle.setText(EMPTYSTRING);
			}
		}
	}

	/**
	 * Method to add Article To the Article Table
	 */
	private void addArticle() {
		QuotationDetailsDTO detail = new QuotationDetailsDTO();
		try {
			detail = createQuotationDetail(detail);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (validateMandatoryParameters(detail)) {
			addToArticleTable(detail);
		}
	}

	/**
	 * Method to validate the mandatory quotation detail parameters
	 */
	private boolean validateMandatoryParameters(QuotationDetailsDTO detail) {

		if (detail.getArticleName() == null || detail.getArticleName().equals(EMPTYSTRING)) {
			AdminComposite.display("Please Enter Article Name", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;
		}

		return true;
	}

	/**
	 * To View and Edit Article values
	 */
	private void viewEditTblArticle() {

		TableItem[] items = tblArticle.getSelection();

		int len = items.length;
		//tblArticle.remove(1);
		if (len == 1) {

			txtArticleName.setText(items[0].getText(0));
			txtCW.setText(items[0].getText(1));
			cbCCC.setText(items[0].getText(2));
			/*if (items[0].getText(2).equalsIgnoreCase(optionB[2])) {
				txtCCCValue.setEnabled(false);
			} else {
				txtCCCValue.setEnabled(true);
			}*/
			
			
			txtCCCValue.setText(items[0].getText(3));
			handleCCCTypeChange();
			
			cbDCC.setText(items[0].getText(4));
			/*if (items[0].getText(4).equalsIgnoreCase(optionB[2])) {
				txtDCCValue.setEnabled(false);
			} else {
				txtDCCValue.setEnabled(true);
			}*/
			txtDCCValue.setText(items[0].getText(5));
			handleDCCTypeChange();
			
			cbIEC.setText(items[0].getText(6));
			txtIEC_article.setText(items[0].getText(7));
			handleIECTypeChange();
			
			cbLoadingCharge.setText(items[0].getText(8));
			txtLC_article.setText((items[0].getText(9)));
			handleLCTypeChange();
			
			cbDDC.setText(items[0].getText(10));
			if (items[0].getText(10).equalsIgnoreCase(optionC[2])) {
				txtDDC_minPerLR.setEnabled(false);
				txtDDC_chargeArticle.setEnabled(false);
			} else if ((items[0].getText(10).equalsIgnoreCase(optionC[0]))) {
				txtDDC_chargeArticle.setEnabled(true);
				txtDDC_minPerLR.setEnabled(false);
			} else {
				txtDDC_chargeArticle.setEnabled(true);
				txtDDC_minPerLR.setEnabled(true);
			}
			txtDDC_minPerLR.setText(((items[0].getText(11))));
			txtDDC_chargeArticle.setText(((items[0].getText(12))));

			// }
		}
		handleMixedArticleDropdown();
	}

	/**
	 * Method to clear Article Values
	 */
	private void clearArticleFields() {
		txtArticleName.setText(EMPTYSTRING);
		txtCW.setText(EMPTYSTRING);

		//cbCCC.select(2);
		txtCCCValue.setText(EMPTYSTRING);
		txtCCCValue.setEnabled(false);

		//cbDCC.select(2);
		txtDCCValue.setText(EMPTYSTRING);
		txtDCCValue.setEnabled(false);

		//cbIEC.select(1);
		txtIEC_article.setText(EMPTYSTRING);

		//cbLoadingCharge.select(1);
		txtLC_article.setText(EMPTYSTRING);

		//cbDDC.select(2);
		txtDDC_chargeArticle.setText(EMPTYSTRING);
		txtDDC_minPerLR.setText(EMPTYSTRING);
		txtDDC_chargeArticle.setEnabled(false);
		txtDDC_minPerLR.setEnabled(false);
	}

	/**
	 * Method to Load and get LRCDTO[] from LRCPop
	 */
	private void handleLRCPop() {
		Shell shell = new Shell(parent.getShell(), SWT.PRIMARY_MODAL | SWT.TOP);
		Composite composite = null;
		try {
			if (null != composite && !composite.isDisposed()) {
				composite.dispose();
			}
			OtherChargesPop ocPop = new OtherChargesPop(shell, SWT.BORDER, true);
			composite = ocPop.loadComposite();
			this.lrcDTO = ocPop.otherChargesDTO;
			//System.out.println("from lrc popup==>"+lrcDTO[0].getLrCharge());
			//System.out.println("from lrc popup==>"+lrcDTO[0].getGsc());

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Method to Load and get GSCDTO[] from GSCPop
	 */
	private void handleGSCPop() {
		Shell shell = new Shell(parent.getShell(), SWT.PRIMARY_MODAL | SWT.TOP);
		Composite composite = null;
		try {
			if (null != composite && !composite.isDisposed()) {
				composite.dispose();
			}
			OtherChargesPop ocPop = new OtherChargesPop(shell, SWT.BORDER, false);
			composite = ocPop.loadComposite();
			if (ocPop.otherChargesDTO != null)
				this.gscDTO = ocPop.otherChargesDTO;
			//System.out.println("from gsc popup===>"+gscDTO[0].getLrCharge());
			//System.out.println("from gsc popup==>"+gscDTO[0].getGsc());
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
	private void handleDHCPop() {
		Shell shell = new Shell(parent.getShell(), SWT.PRIMARY_MODAL | SWT.TOP);
		Composite composite = null;
		try {
			if (null != composite && !composite.isDisposed()) {
				composite.dispose();			
			}
			
			DhcPop dhcPop = new DhcPop(shell, SWT.BORDER);
			composite = dhcPop.loadComposite();
						
			dhcDto = dhcPop.dhcDTO;
			
			//System.out.println("from gsc popup===>"+gscDTO[0].getLrCharge());
			//System.out.println("from gsc popup==>"+gscDTO[0].getGsc());
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Method to Load and get InsuranceDTO[] from InsurancePop
	 */
	private void handleInsurancePop() {
		Shell shell = new Shell(parent.getShell(), SWT.PRIMARY_MODAL | SWT.TOP);
		Composite composite = null;
		try {
			if (null != composite && !composite.isDisposed()) {
				composite.dispose();
			}

			ValueRangeComposite valueRangePop = (new ValueRangeComposite(shell, SWT.BORDER, "quotation", 70,
					insuranceDTO));
			composite = valueRangePop.loadComposite();
			insuranceDTO = valueRangePop.insuranceDTO;
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Method to get Price Index Value
	 * @return
	 */
	public float getAvailPriceIndex() {
		return Float.parseFloat(txtPriceIndex.getText());
	}
	
	private float getRoundFourDecimals(float priceIndex) {				
		priceIndex = ((float)(int)(priceIndex * 100000)) / (float)100000;
		priceIndex = priceIndex * 10000;		
		priceIndex = ((float)(int)(priceIndex * 10)) / (float)10;		
		priceIndex = (float) Math.ceil(priceIndex);
		priceIndex = priceIndex / 10000;
		
		return priceIndex;
	}

	//Mixed Article Table - Drop down enable, disable	
	private void disableMixedArticleDropdown(){
		cbCCC.setEnabled(false);
		cbDCC.setEnabled(false);
		cbIEC.setEnabled(false);
		cbLoadingCharge.setEnabled(false);
		cbDDC.setEnabled(false);
		txtDDC_minPerLR.setEnabled(false);
	}
	
	private void enableMixedArticleDropdown(){		
		cbCCC.setEnabled(true);
		cbDCC.setEnabled(true);
		cbIEC.setEnabled(true);
		cbLoadingCharge.setEnabled(true);
		cbDDC.setEnabled(true);
		txtDDC_minPerLR.setEnabled(true);
	}
	
	private void handleMixedArticleDropdown(){

		if(tblArticle.isVisible() && tblArticle.getItemCount() > 0){
			if(tblArticle.getItemCount() == 1 && 
					tblArticle.getItem(0).getText(0).equals(txtArticleName.getText())){
				enableMixedArticleDropdown();			
			}else{
				disableMixedArticleDropdown();
			}
		}else{
			enableMixedArticleDropdown();
		}		
	
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent event) {
		Object source = event.getSource();
		
		if(source == txtArticleName){
			handleMixedArticleDropdown();
		}
	}

	private void handleDHCTypeChange() {
		String gsc = cbDHC.getText();

		if (optionA[1].equals(gsc))
			btnDHCPop.setEnabled(true);
		else {
			btnDHCPop.setEnabled(false);
		}
	}
	
	//DHC
	private byte getDHCType() {
		String dhcType = cbDHC.getText();

		//Check with optionA
		for (byte i = 0; i < 3; i++) {
			if (optionA[i].equals(dhcType))
				return i;
		}
		return 0;
	}
	
}
