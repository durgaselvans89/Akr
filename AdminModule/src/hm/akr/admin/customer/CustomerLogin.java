package hm.akr.admin.customer;

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
public class CustomerLogin extends Composite implements SelectionListener,
 IUIConstants {

	private Label lblCustomer;
	private Label lblStation;
	private Label lblType;
	private Combo cbCustomer;
	private Combo cbStation;
	private Label lblBookingType;
	private Label lblUser;
	private Text txtUser;
	private Button btnSet;
	private Text txtConfirmPwd;
	private Text txtPwd;
	private QuotationHandler handler = null;
	private Text txtQuotationId;
	private QuotationDTO[] quotList = null;
	

	public CustomerLogin(Composite parent, int style) {
		super(parent, style);
		try {
			handler = new QuotationHandler();
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}

	
	public Composite loadComposite() throws Exception{
		
		this.setSize(484, 299);
		
		Canvas cvs = new Canvas(this, SWT.BORDER);
		cvs.setBounds(24, 20, 354, 300);
		
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
			lblType.setText("Password");
			lblType.setBounds(36, 171, 86, 19);
			//lblType.setFont(LABLE_FONT);
			//lblType.setForeground(QUOTATION_COLOR);
		}

		
		{
			cbStation = new Combo(cvs, SWT.READ_ONLY);
			cbStation.setBounds(36, 31, 120, 21);
			cbStation.addSelectionListener(this);
		}
		{
			cbCustomer = new Combo(cvs, SWT.READ_ONLY);
			cbCustomer.setBounds(201, 31, 120, 21);	
			cbCustomer.addSelectionListener(this);
		}

		{
			txtUser = new Text(cvs, SWT.BORDER);
			txtUser.setBounds(36, 113, 120, 21);
		}
		
		{
			txtPwd = new Text(cvs, SWT.BORDER);
			txtPwd.setBounds(36, 191, 120, 21);
			txtPwd.setEchoChar('*');
			txtPwd.setTextLimit(30);
			
		}
		{
			lblBookingType = new Label(cvs, SWT.NONE);
			lblBookingType.setText("Confirm Password");
			lblBookingType.setBounds(201, 171, 110, 20);
		}
		{
			txtConfirmPwd = new Text(cvs, SWT.BORDER);
			txtConfirmPwd.setBounds(201, 191, 120, 21);
			txtConfirmPwd.setEchoChar('*');
			txtConfirmPwd.setTextLimit(30);
			
		}
		{
			btnSet = new Button(cvs, SWT.PUSH | SWT.CENTER);
			btnSet.setText("Set");
			btnSet.setBounds(261, 239, 63, 30);
			btnSet.addSelectionListener(this);
		}

		//HIDDEN
		{
			txtQuotationId = new Text(cvs, SWT.BORDER);
			txtQuotationId.setBounds(350, 102, 120, 21);
			txtQuotationId.setVisible(false);
		}
		
		{
			lblUser = new Label(cvs, SWT.NONE);
			lblUser.setText("User Name");
			lblUser.setBounds(36, 92, 61, 18);
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
		
		if(source == btnSet){
			AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			try {
				
				if(isValid() && txtQuotationId != null && !txtQuotationId.getText().equals("")){
					
					handler.setCustomerLogin(txtQuotationId.getText(), txtUser.getText(), txtPwd.getText());
					AdminComposite.display("Customer settings saved", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					
					cbCustomer.removeAll();
					cbStation.removeAll();
					populateStations();
					
					txtConfirmPwd.setText("");
					txtPwd.setText("");
					txtQuotationId.setText("");
					txtUser.setText("");
				}
				
			} catch (Exception e) {		
				AdminComposite.display("Customer settings  cant be saved", STATUS_SUCCESS, SUCCESS_FONT_COLOR);				
			}
		}else if(source == cbStation){
			handleStationAction();
		}else if(source == cbCustomer){
			handleCustomerAction();
		}
		
	}
	
	private void handleCustomerAction() {
		String customer = cbCustomer.getText();
		
		if(!customer.equals("")){
			for (int i = 0; i < quotList.length; i++) {
				if (customer.equalsIgnoreCase(quotList[i].getCustomerName())) {
					
					// Hidden QId
					txtQuotationId.setText(quotList[i].getId());
					
					//System.out.println("QID==>"+txtQuotationId.getText());
					
					break;
				}
			}
		}
			
	}
	


	private boolean isValid() {
		if(txtUser.getText().equals("")){
			AdminComposite.display("Please Enter Username", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;
		}else if(txtPwd.getText().equals("")){
			AdminComposite.display("Please Enter Password", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;
		}else if(!txtPwd.getText().equals(txtConfirmPwd.getText())){
			AdminComposite.display("Please Enter Correct Confirm Password", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;
		}
		
		return true;
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
			
			txtConfirmPwd.setText("");
			txtPwd.setText("");
			txtQuotationId.setText("");
			txtUser.setText("");
			}catch(Exception exception){
				exception.printStackTrace();
			}
		}
	}

	
	
}
