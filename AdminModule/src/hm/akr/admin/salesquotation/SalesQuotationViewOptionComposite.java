package hm.akr.admin.salesquotation;

import hm.akr.admin.salesquotation.handler.SalesQuotationHandler;
import hm.akr.admin.workspace.AdminComposite;
import hm.akr.admin.workspace.SWTResourceManager;
import hm.akr.common.IUIConstants;
import hm.akr.dto.QuotationDTO;
import hm.akr.exceptions.BusinessException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * 
 * @author 
 * 
 */
public class SalesQuotationViewOptionComposite extends Composite implements
		SelectionListener, SalesIQuotationConstants, IUIConstants {

	private Button rdQuick;

	public Button rdManual;	
	
	private Composite composite = null;

	//private QuickEditComposite cptQuick = null;

	Composite BPISetComposite = null;

	private Button btnNext;

	private Button btnPrevious;	

	private SalesManualEditComposite cptManual = null;

	private SalesQuotationFinishComposite finishComposite = null;	

	QuotationDTO quotationDTO = null;
	
	SalesQuotationHandler handler = null;

	QuotationDTO[] quotList = null;
	
	static boolean isBpiChanged = false; 

	/**
	 * 
	 * @param parent
	 * @param style
	 */
	public SalesQuotationViewOptionComposite(Composite parent, int style) {
		super(parent, style);
		try {
			handler = new SalesQuotationHandler();
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return
	 */
	public Composite loadComposite() {		
		

		rdQuick = new Button(this, SWT.RADIO);
		rdQuick.setText("QUICK");
		rdQuick.setSelection(true);
		rdQuick.setBounds(5, 5, 100, 25);
		rdQuick.addSelectionListener(this);
		
		
		rdManual = new Button(this, SWT.RADIO);
		rdManual.setText("MANUAL");
		rdManual.setBounds(110, 5, 100, 25);
		rdManual.addSelectionListener(this);
		
		{
			btnPrevious = new Button(this, SWT.NONE);
			btnPrevious.setText("Previous");
			btnPrevious.setBounds(663, 540, 53, 21);
			btnPrevious.addSelectionListener(this);
			btnPrevious.setVisible(false);
		}
		{
			btnNext = new Button(this, SWT.NONE);
			btnNext.setText("Next");
			btnNext.setBounds(721, 540, 45, 21);
			btnNext.addSelectionListener(this);
			btnNext.setVisible(false);
		}
		
		
		
		try {
			
			quotList = handler.getQuotationList();
			
			rdQuick.setFont(SWTResourceManager.getFont("Tahoma", 9, 1, false,
					false));		
			composite = new SalesQuickEditComposite(this, SWT.BORDER, quotList).loadComposite();			
			composite.setBounds(15, 35, 800, 500);
			
		} catch (Exception exception) {
			exception.printStackTrace();
		}		

		return this;
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
		composite = new SalesManualEditComposite(this, SWT.BORDER, index, quotationDTO, quotList).loadComposite();
		composite.setBounds(15, 35, 800, 500);
		
		

		//Change buttons
		btnPrevious.setEnabled(false);
		btnNext.setEnabled(true);
		btnNext.setText("Next");
		
		rdQuick.setEnabled(true);
		rdManual.setEnabled(true);
	}
	
	
	/**
	 * Method for handle the next action
	 */
	private void handleNextAction() {
		try {

			if ("Finish".equals(btnNext.getText())) {
				AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				updateQuotation();
			} else {
				if (validateComposite()) {
					AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					// De Initialize the create Quotation Composite
					cptManual = (SalesManualEditComposite) composite;
					if(cptManual != null){
						
						quotationDTO = cptManual.getQuotationInformation();
						
						//composite.deInitialize();
						cptManual.dispose();
						cptManual = null;									
					}
					
					// Load Finish composite
					if(quotationDTO != null){
						//System.out.println("digits==>"+quotationDTO.getRoundOffDigits());
						finishComposite = new SalesQuotationFinishComposite(this, SWT.BORDER, quotationDTO);					
						finishComposite.loadComposite();
						finishComposite.setBounds(15, 35, 800, 500);
					}

					// Change buttons					
					btnPrevious.setEnabled(true);
					btnNext.setEnabled(true);
					btnNext.setText("Finish");		
					
					rdQuick.setEnabled(false);
					rdManual.setEnabled(false);
					}
			}

		} catch (BusinessException business) {
			//TBD - Set the status label with the business message
			business.printStackTrace();
		} catch (Exception exception) {
			//Set the status label
			exception.printStackTrace();
		}
	}

	
	@Override
	public void widgetDefaultSelected(SelectionEvent event) {

	}

	@Override
	public void widgetSelected(SelectionEvent event) {

		Object source = event.getSource();
		if (rdQuick == source) {
			AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			if(btnNext.isVisible()){
			rdManual.setFont(SWTResourceManager.getFont("Tahoma", 9, 0, false,
					false));
			rdQuick.setFont(SWTResourceManager.getFont("Tahoma", 9, 1, false,
					false));

			if(composite != null){
				composite.dispose();
				composite = null;
			}
			
			
			composite = new SalesQuickEditComposite(this, SWT.BORDER, quotList).loadComposite();			
			composite.setBounds(15, 35, 800, 500);
			
			btnPrevious.setVisible(false);
			btnNext.setVisible(false);
			}

		} else if (rdManual == source) {		
			AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			
			if(!btnNext.isVisible()){
			rdManual.setFont(SWTResourceManager.getFont("Tahoma", 9, 1, false,
					false));
			rdQuick.setFont(SWTResourceManager.getFont("Tahoma", 9, 0, false,
					false));

			
			if(composite != null){
				composite.dispose();
				composite = null;
			}
			
			if(isBpiChanged){				
				try {
					quotList = handler.getQuotationList();
					isBpiChanged = false;
					quotationDTO = null;
				} catch (Exception e) {					
					e.printStackTrace();
				}
			}
			
			composite = (SalesManualEditComposite) new SalesManualEditComposite(this, SWT.BORDER, 0, quotationDTO, quotList).loadComposite();			
			composite.setBounds(15, 35, 800, 500);			

			btnNext.setVisible(true);
			btnPrevious.setVisible(true);
			btnPrevious.setEnabled(false);
			}

		}else if (source == btnPrevious) {
			handlePreviousAction();
		} else if (source == btnNext) {			
			handleNextAction();	
		}

	}
	
	/**
	 * Method to create quotation
	 */
	public void updateQuotation() {
		try {
			// De-Initialize Finish Composite
			if (null != finishComposite) {
				quotationDTO = finishComposite.getBFTDetails();
			}if(quotationDTO != null){
			// Call handler method to update quotation
			SalesQuotationHandler handler = new SalesQuotationHandler();
			handler.updateQuotation(quotationDTO);
			}

			AdminComposite.display("Quotation Updated Successfully", STATUS_SUCCESS, SUCCESS_FONT_COLOR);

			//Disable both buttons
			btnNext.setEnabled(false);
			btnPrevious.setEnabled(false);
			finishComposite.setVisible(false);
			
		} catch (Exception exception) {
			AdminComposite.display("Quotation Updation Failed", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			exception.printStackTrace();
		}
	}
	
	/**
	 * Method to validate composite. This method checks for customer name,
	 * station code and type and quotation values
	 * 
	 * @return boolean  <li> true - if the validation succeeds </li>
	 * 					<li> false - if the validation fails </li>
	 **/
	private boolean validateComposite() {
		boolean isValid = true;
		
		//Method to validate Quotation Values
		cptManual = (SalesManualEditComposite) composite;
		isValid = cptManual.validateQuotationValues();
		
		return isValid;
	}

}
