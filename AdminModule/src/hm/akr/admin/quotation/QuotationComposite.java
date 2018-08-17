package hm.akr.admin.quotation;

import hm.akr.admin.quotation.handler.QuotationHandler;
import hm.akr.admin.workspace.AdminComposite;
import hm.akr.common.IUIConstants;
import hm.akr.dto.CustomerDTO;
import hm.akr.dto.QuotationDTO;
import hm.akr.exceptions.BusinessException;

import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * Quotation Composite class for creating quotation 
 */
public class QuotationComposite extends Composite implements SelectionListener,
		IQuotationConstants, IUIConstants {

	private Label lblType;

	private Combo cbType;

	private QuotationDetailsComposite composite = null;

	private QuotationFinishComposite finishComposite = null;

	private Label lblCustomer;

	private Button btnNext;

	private Label lblStation;
	
	private Button btnPrevious;

	QuotationDTO quotationDTO = null;

	private Combo cbCustomer;
	
	private QuotationHandler handler = null;
	
	private CustomerDTO[] customerDTO = null;

	private Combo cbStation;

	/**
	 * Constructor method
	 * 
	 * @param parent
	 * @param style
	 */
	public QuotationComposite(Composite parent, int style) {
		super(parent, style);
		try {
			handler = new QuotationHandler();
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}

	/**
	 * Method to load Quotation Composite
	 * 
	 * @return
	 */
	public Composite loadComposite() throws Exception {
		//try{

		{
			lblCustomer = new Label(this, SWT.NONE);
			lblCustomer.setText("Customer");
			lblCustomer.setBounds(201, 12, 70, 17);
			lblCustomer.setFont(LABLE_FONT);
			lblCustomer.setForeground(QUOTATION_COLOR);
		}
		{
			lblStation = new Label(this, SWT.NONE);
			lblStation.setText("Station");
			lblStation.setBounds(36, 12, 67, 17);
			lblStation.setFont(LABLE_FONT);
			lblStation.setForeground(QUOTATION_COLOR);
		}
		{
			lblType = new Label(this, SWT.NONE);
			lblType.setText("Quotation Type");
			lblType.setBounds(364, 12, 84, 19);
			lblType.setForeground(QUOTATION_COLOR);
		}

		{
			cbStation = new Combo(this, SWT.READ_ONLY);
			cbStation.setBounds(36, 31, 120, 21);
			cbStation.addSelectionListener(this);
		}
		
		{
			cbCustomer = new Combo(this, SWT.READ_ONLY);
			cbCustomer.setBounds(201, 31, 120, 21);			
		}

		{
			cbType = new Combo(this, SWT.READ_ONLY);
			cbType.setBounds(364, 31, 120, 21);
			cbType.add(WEIGHT_BASED);
			cbType.add(ARTICLE_BASED);
			cbType.add(MIXED_ARTICLE);
			//cbType.select(0);
			cbType.addSelectionListener(this);
		}

		{
			composite = new QuotationDetailsComposite(this, SWT.BORDER, 0,quotationDTO); //0 - Weight Based
		    composite.loadComposite();
			composite.setBounds(15, 60, 750, 470);
		}

		{
			btnPrevious = new Button(this, SWT.NONE);
			btnPrevious.setText("Previous");
			btnPrevious.setBounds(663, 540, 53, 21);
			btnPrevious.addSelectionListener(this);
			btnPrevious.setEnabled(false);
		}
		{
			btnNext = new Button(this, SWT.NONE);
			btnNext.setText("Next");
			btnNext.setBounds(721, 540, 45, 21);
			btnNext.addSelectionListener(this);
		}

		
		populateStations();
		/*}
		catch(Exception e){
			
			long minRunningMemory = (1024*1024);

			Runtime runtime = Runtime.getRuntime();

			if(runtime.freeMemory()<minRunningMemory)
			 System.gc();
		}*/
		
		return this;
		
	}

	/**
	 * Method to populate the Customers
	 */
	private void populateStations() throws Exception{		
		customerDTO = handler.getCustomers();
		TreeSet<String> set = new TreeSet<String>();
		if(customerDTO != null){			
			for(int i = 0; i < customerDTO.length; i++){
				if(customerDTO[i].getQuotationId() == null && customerDTO[i].getQuotation_type() != 0){
					//System.out.println("qt==>"++"name "+customerDTO[i].getName());
					set.add(customerDTO[i].getStation());					
				}
			}			
			
			Iterator<String> itr = set.iterator();
			while(itr.hasNext()){
				cbStation.add(itr.next());
			}			
		}		
	}
	
	/**
	 * Method to handle customer
	 * @throws Exception
	 */
	private void handleStationAction(){
		String station = cbStation.getText();
		
		if(customerDTO != null){
			try{
				cbCustomer.removeAll();
			for(int i = 0; i < customerDTO.length; i++){
				if(station.equals(customerDTO[i].getStation()) && customerDTO[i].getQuotationId() == null && customerDTO[i].getQuotation_type() != 0){
					cbCustomer.add(customerDTO[i].getName());					
				}
			}
			
			String[] items = cbCustomer.getItems();
			cbCustomer.removeAll();
			Arrays.sort(items, String.CASE_INSENSITIVE_ORDER);
			cbCustomer.setItems(items);
			}catch(Exception exception){
				exception.printStackTrace();
			}
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent event) {		

	}

	/**
	 * Method to handle Previous Action
	 */
	private void handlePreviousAction() {
		int index = 0;
		//De-Initialize Finish Composite
		if (null != finishComposite) {
			quotationDTO = finishComposite.getBFTDetails();
			if(quotationDTO != null){
				index = quotationDTO.getType();
			}
			//finishComposite.deInitialize();
			finishComposite.dispose();
			finishComposite = null;
		}

		//Load composite		
		composite = new QuotationDetailsComposite(this, SWT.BORDER, index, quotationDTO); //0 - Weight Based
		composite.loadComposite();
		composite.setBounds(15, 60, 750, 470);
		
		

		//Change buttons
		btnPrevious.setEnabled(false);
		btnNext.setEnabled(true);
		btnNext.setText("Next");
	}

	/**
	 * Method to get the Type
	 * @return byte  Quotation Type
	 */
	private byte getType() {
		byte type = 0;
		String typeString = cbType.getText();

		if (WEIGHT_BASED.equals(typeString))
			type = 0;
		else if (ARTICLE_BASED.equals(typeString))
			type = 1;
		else if (MIXED_ARTICLE.equals(typeString))
			type = 2;

		return type;
	}

	/**
	 * Method to validate composite. This method checks for valid customer name,
	 * station code and type and quotation values
	 * 
	 * @return boolean  <li> true - if the validation succeeds </li>
	 * 					<li> false - if the validation fails </li>
	 **/
	private boolean validateComposite() {
		boolean isValid = true;

		String customer = cbCustomer.getText();
		String station = cbStation.getText();
		String qType = cbType.getText();
		if (null == station || station.trim().length() == 0){
			AdminComposite.display("Please Select Station", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;
		} else if (null == customer || customer.trim().length() == 0){
			AdminComposite.display("Please Select Customer", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;
		} else if (null == qType || qType.trim().length() == 0){
			AdminComposite.display("Please Select Quotation Type", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;
		}			

		//Method to validate Quotation Values
		isValid = composite.validateQuotationValues();
		
		return isValid;
	}

	/**
	 * Method for handle the next action
	 */
	private void handleNextAction() {
		try {

			if ("Finish".equals(btnNext.getText())) {
				AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				createQuotation();
			} else {
				if (validateComposite()) {
					
					AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					
					cbCustomer.setEnabled(false);
					cbType.setEnabled(false);
					cbStation.setEnabled(false);

					// De Initialize the create Quotation Composite
					if (null != composite) {		
						if(quotationDTO != null){
							
						}
						quotationDTO = composite.getQuotationInformation();
						//composite.deInitialize();
						composite.dispose();
						composite = null;
					}

					// Set the customer, station code and quotationType
					quotationDTO.setCustomerName(cbCustomer.getText());
					quotationDTO.setStationCode(cbStation.getText());
					quotationDTO.setType(getType());

					// Load Finish composite							
					
					finishComposite = new QuotationFinishComposite(this, SWT.BORDER, quotationDTO);					
					finishComposite.loadComposite();
					finishComposite.setBounds(15, 60, 750, 470);

					// Change buttons
					btnPrevious.setEnabled(true);
					btnNext.setEnabled(true);
					btnNext.setText("Finish");
				}
			}

		} catch (BusinessException business) {
			AdminComposite.display(business.getResponseMessage(), STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			business.printStackTrace();
		} catch (Exception exception) {
			AdminComposite.display(exception.toString(), STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			exception.printStackTrace();
		}
	}
	
	

	/**
	 * Method to create quotation
	 */
	public void createQuotation() {
		try {
		
			if (null != finishComposite) {
				quotationDTO = finishComposite.getBFTDetails();
			}if(quotationDTO != null){

			// De-Initialize Finish Composite
			finishComposite.dispose();
						
			// Call handler method to create quotation		
			handler.createQuotation(quotationDTO);
			}

			AdminComposite.display("Quotation Created Successfully", STATUS_SUCCESS, SUCCESS_FONT_COLOR);

			//Disable both buttons
			btnNext.setEnabled(false);
			btnPrevious.setEnabled(false);
		} catch (Exception exception) {
			AdminComposite.display("Quotation Creation Failed", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			exception.printStackTrace();
		}
	}

	/**
	 * Method for handling all user actions in the current Screen
	 */
	@Override
	public void widgetSelected(SelectionEvent event) {
		Object source = event.getSource();

		if (source == cbType) { //Quotation Type Change Event
			String quotationType = cbType.getText();
			if (null != quotationType && quotationType.length() > 0)
				composite.changeType(quotationType);
		} else if (source == btnPrevious) { //Previous button handler
			handlePreviousAction();
		} else if (source == btnNext) {
			handleNextAction();
		} else if(source == cbStation){
			handleStationAction();
		}

	}

}
