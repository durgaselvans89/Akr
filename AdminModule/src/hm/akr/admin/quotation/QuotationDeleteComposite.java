package hm.akr.admin.quotation;

import hm.akr.admin.quotation.handler.QuotationHandler;
import hm.akr.admin.workspace.AdminComposite;
import hm.akr.common.IUIConstants;
import hm.akr.dto.QuotationDTO;

import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
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
public class QuotationDeleteComposite extends Composite implements SelectionListener,
IQuotationConstants, IUIConstants {

	private Label lblCustomer;
	private Label lblStation;
	private Label lblType;
	private Combo cbCustomer;
	private Combo cbStation;
	private Label lblBookingType;
	private Button btnDelete;
	private Text txtBookingType;
	private Text txtType;
	private QuotationHandler handler = null;
	private Text txtQuotationId;
	private QuotationDTO[] quotList = null;
	private String TYPE = "";

	public QuotationDeleteComposite(Composite parent, int style) {
		super(parent, style);
		try {
			handler = new QuotationHandler();
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}

	
	public Composite loadComposite() throws Exception{
		
		this.setSize(484, 220);
		
		Canvas cvs = new Canvas(this, SWT.BORDER);
		cvs.setBounds(24, 20, 384, 196);
		
		{
			lblCustomer = new Label(cvs, SWT.NONE);
			lblCustomer.setText("Customer");
			lblCustomer.setBounds(201, 12, 70, 17);
			//lblCustomer.setFont(LABLE_FONT);
			//lblCustomer.setForeground(QUOTATION_COLOR);
		}
		{
			lblStation = new Label(cvs, SWT.NONE);
			lblStation.setText("Station");
			lblStation.setBounds(36, 12, 67, 17);
			//lblStation.setFont(LABLE_FONT);
			//lblStation.setForeground(QUOTATION_COLOR);
		}
		{
			lblType = new Label(cvs, SWT.NONE);
			lblType.setText("Quotation Type");
			lblType.setBounds(36, 82, 86, 19);
			//lblType.setFont(LABLE_FONT);
			//lblType.setForeground(QUOTATION_COLOR);
		}

		{
			cbCustomer = new Combo(cvs, SWT.READ_ONLY);
			cbCustomer.setBounds(201, 31, 170, 21);	
			cbCustomer.addSelectionListener(this);
		}

		{
			cbStation = new Combo(cvs, SWT.READ_ONLY);
			cbStation.setBounds(36, 31, 120, 21);
			cbStation.addSelectionListener(this);
		}

		{
			txtType = new Text(cvs, SWT.BORDER);
			txtType.setBounds(36, 102, 120, 21);
			txtType.setEnabled(false);		
			
		}
		{
			lblBookingType = new Label(cvs, SWT.NONE);
			lblBookingType.setText("Booking Type");
			lblBookingType.setBounds(201, 82, 65, 20);
		}
		{
			txtBookingType = new Text(cvs, SWT.BORDER);
			txtBookingType.setBounds(201, 102, 170, 21);
			txtBookingType.setEnabled(false);
		}
		{
			btnDelete = new Button(cvs, SWT.PUSH | SWT.CENTER);
			btnDelete.setText("Delete");
			btnDelete.setBounds(261, 150, 63, 30);
			btnDelete.addSelectionListener(this);
		}

		//HIDDEN
		{
			txtQuotationId = new Text(cvs, SWT.BORDER);
			txtQuotationId.setBounds(350, 102, 120, 21);
			txtQuotationId.setVisible(false);
		}
		
		try {
			quotList = handler.getQuotationList();
			populateStations();
		} catch (Exception exception) {
			exception.printStackTrace();
		}		
		
		
		
		return this;
	}
	
	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void widgetSelected(SelectionEvent event) {
		Object source = event.getSource();
		
		if(source == btnDelete){
			try {
				if(txtQuotationId != null && !txtQuotationId.getText().equals("")){
					String stn = cbStation.getText();
					handler.deleteQuotation(txtQuotationId.getText(), isInwardType());
					AdminComposite.display("Quotation Deleted Successfully", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					
					cbCustomer.removeAll();
					//cbStation.setText("");
					txtBookingType.setText("");
					txtType.setText("");
					txtQuotationId.setText("");
					
					cbStation.remove(stn);
					String[] items = cbStation.getItems();
					cbStation.removeAll();
					Arrays.sort(items, String.CASE_INSENSITIVE_ORDER);
					cbStation.setItems(items);
				}
				
			} catch (Exception e) {		
				AdminComposite.display("Quotation Deletion Failed", STATUS_SUCCESS, SUCCESS_FONT_COLOR);				
			}
		}else if(source == cbStation){
			AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			handleStationAction();
		}else if(source == cbCustomer){
			AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			handleCustomerAction();
		}
		
	}
	
	


	/**
	 * Method to populate the Customers
	 */
	private void populateStations() throws Exception{	
		
		TreeSet<String> set = new TreeSet<String>();
		if(quotList != null){			
			for(int i = 0; i < quotList.length; i++){				
				set.add(quotList[i].getStationCode());
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
		
		if(quotList != null){
			try{
				cbCustomer.removeAll();
			for(int i = 0; i < quotList.length; i++){
				if(station.equals(quotList[i].getStationCode())){
					cbCustomer.add(quotList[i].getCustomerName());					
				}
			}
			
			String[] items = cbCustomer.getItems();
			cbCustomer.removeAll();
			Arrays.sort(items, String.CASE_INSENSITIVE_ORDER);
			cbCustomer.setItems(items);
			
			txtBookingType.setText("");
			txtType.setText("");
			txtQuotationId.setText("");
			}catch(Exception exception){
				exception.printStackTrace();
			}
		}
	}

	private void handleCustomerAction() {
		String customer = cbCustomer.getText();
		String type = "";
		if(!customer.equals("")){
			for (int i = 0; i < quotList.length; i++) {
				if (customer.equalsIgnoreCase(quotList[i].getCustomerName())) {
					
					//Booking Type
					if (quotList[i].isInward()){
						txtBookingType.setText(INWARD_TYPE);
					}else{
						txtBookingType.setText(OUTWARD_TYPE);
					}
					
					// Quotation Type
					type = findTypeName((byte) quotList[i].getType());
					txtType.setText(type);					
					
					// Hidden QId
					txtQuotationId.setText(quotList[i].getId());
					
					//System.out.println("QID==>"+txtQuotationId.getText());
					
					break;
				}
			}
		}
			
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
	
	private boolean isInwardType() {
		boolean isInward = false;

		//Booking Type
		if (txtBookingType.getText().equals(INWARD_TYPE))
			isInward = true;

		return isInward;
	}

}
