/**
 * 
 */
package hm.akr.admin.customer;

import hm.akr.admin.customer.handler.CustomerCompositeHandler;
import hm.akr.admin.workspace.AdminComposite;
import hm.akr.common.IUIConstants;
import hm.akr.common.NumericValidation;
import hm.akr.dto.CustomerDTO;
import hm.akr.dto.StationsDTO;
import hm.akr.msb.TreeComposite;

import java.util.Arrays;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
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
 * @author HM
 * 
 */
public class CustomerComposite extends Composite implements IUIConstants {

	public static final String BASICFORMAT1 = "BasicFormat 1";
	public static final String BASICFORMAT2 = "BasicFormat 2";
	public static final String ADDONFORMAT1 = "Add-on-Format 1";
	private Button btnNew;
	private Button rdPaid;
	private Button rdTopay;
	
	public static final String ADDONFORMAT2 = "Add-on-Format 2";
	public static final String ADDONFORMAT3 = "Add-on-Format 3";

	private Label lblCustomerName;
	private Group gpBillingDetails;
	private Text txtMailId;
	private Label lblMailId;
	private Text txtPhone;
	private Label lblPhone;
	private Group gpNotification;
	private Button chkEdit;
	private Text txtMobile;
	private Button btnCreate;
	private Button btnView;
	private Label lblBasicFormat;
	private Combo cbBasicFormat;
	private Label lblMobile;
	private Text txtContactPerson;
	private Label lblContactPerson;
	private Label lblAddress2;
	private Label lblAddress1;
	private Text txtAddress2;
	private Text txtCity;
	private Label lblPincode;
	private Label lblState;
	private Label lblCity;
	private Group gpAddress;
	private Label lblStation;
	private Combo cbStation;
	private Text txtState;
	private Text txtPincode;
	private Text txtAddress1;
	private Group gpContact;
	private Combo cbCustomerName;
	private Button chkAddOnFormat1;
	private Button chkAddOnFormat2;
	private Button chkAddOnFormat3;
	

	private CustomerDTO customerDetails = null;
	private CustomerCompositeHandler handler;
	private String EMPTY_STRING = "";

	boolean isUpdatecustomer = false;
	
	private Button rdAgent;
	private Label lblTin;
	private Text txtTin;
	private Group gpConsignor;
	private Button rdConsignor;
	private Button rdConsignee;

	CustomerDTO[] customernames = null;
	private Text txtQuotationId;
	private Button chkConsignor;
	private Button chkConsignee;
	private Button rdBilling;
	private TabFolder tabCustomer;
	private TabItem tabItem1;
	private TabItem tabItem2;
	private Canvas canCustomer;
	private Canvas canDRSShortage;
	
	protected String[] stations;
	private Button cbDRSShortage;
	private Button btnDRSDay;
	private Text txtDRSDay;
	private Label lblDRSDay;
	private Label lblDRSBranch;
	private Combo cbDRSBranch;
	protected TreeComposite tree;
	protected Composite cpttree;
	
	public static boolean isCustomer = false;
	public static String selectedbranch = null;
	
	private String DRS_TAB = "DRS Shortage";
	private String CUSTOMER_TAB = "Create Customer";
	
	/**
	 * @throws Exception
	 * 
	 */
	public CustomerComposite(Composite parent, int swtValue) throws Exception {
		super(parent, swtValue);
		handler = new CustomerCompositeHandler();		
	}

	public Composite loadComposite() throws Exception {

		try {

			this.setSize(800, 600);
			
			AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			
			
			tabCustomer = new TabFolder(this, SWT.NONE);
			{
				tabItem1 = new TabItem(tabCustomer, SWT.NONE);
				tabItem1.setText(CUSTOMER_TAB);
			}
			/*{
				tabItem2 = new TabItem(tabCustomer, SWT.NONE);
				tabItem2.setText(DRS_TAB);

			}*/

			tabCustomer.addSelectionListener(new SelectionAdapter() {

				@Override
				public void widgetSelected(
						org.eclipse.swt.events.SelectionEvent event) {
					String selectedTab = tabCustomer.getSelection()[0]
							.getText();

					try {
						if (selectedTab.equalsIgnoreCase(DRS_TAB)) {
							canDRSShortage = getDRSShortage();

						} else {
							canCustomer = getCreateCanvas();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});

			tabCustomer.setSelection(0);
			tabCustomer.setBounds(0, 0, 800, 600);
			canCustomer = getCreateCanvas();			

			customernames =  getCustomerDetails();
			
			this.layout();
		} catch (Exception exception) {
			exception.printStackTrace();
				
			/*long minRunningMemory = (1024*1024);

			Runtime runtime = Runtime.getRuntime();

			if(runtime.freeMemory()<minRunningMemory)
			 System.gc();*/
			 
			 
		
		}

		return this;
	}
	
	
	private Canvas getDRSShortage() throws Exception {
		{
			//this.setSize(800, 600);
		}		

		canDRSShortage = new Canvas(tabCustomer, SWT.NONE);
		tabItem2.setControl(canDRSShortage);

		isCustomer = true;

		{
			cbDRSBranch = new Combo(canDRSShortage, SWT.NONE);
			cbDRSBranch.setBounds(106, 18, 170, 21);
			cbDRSBranch.addSelectionListener(new CustomerListener());

			StationsDTO[] station = null;
			try {
				

				station = handler.getAllBranches();

				if (null != station) {
					int len = station.length;

					for (int i = 0; i < len; i++) {

						cbDRSBranch.add(station[i].getName() + " - "
								+ station[i].getId());

					}
					cbDRSBranch.select(0);
				}
			} catch (Exception exception) {
				exception.printStackTrace();
			}

		}
		{
			lblDRSBranch = new Label(canDRSShortage, SWT.NONE);
			lblDRSBranch.setText("Select Branch");
			lblDRSBranch.setBounds(12, 18, 78, 21);
		}

		int index = cbDRSBranch.getSelectionIndex();
		if (index > -1) {
			selectedbranch = cbDRSBranch.getItem(index);
			tree = new TreeComposite(canDRSShortage, SWT.NONE, 300);
			{
				lblDRSDay = new Label(canDRSShortage, SWT.NONE);
				lblDRSDay.setText("Days");
				lblDRSDay.setBounds(459, 280, 28, 23);
			}
			{
				txtDRSDay = new Text(canDRSShortage, SWT.BORDER);
				txtDRSDay.setBounds(497, 275, 60, 28);
				txtDRSDay.addVerifyListener(new NumericValidation());
			}
			{
				btnDRSDay = new Button(canDRSShortage, SWT.PUSH | SWT.CENTER);
				btnDRSDay.setText("Set");
				btnDRSDay.setBounds(581, 277, 37, 30);
				btnDRSDay.addSelectionListener(new CustomerListener());
			}
			{
				cbDRSShortage = new Button(canDRSShortage, SWT.CHECK | SWT.LEFT);
				cbDRSShortage.setText("Status");
				cbDRSShortage.setBounds(396, 274, 60, 30);
			}

			cpttree = tree.loadComposite();
			cpttree.setVisible(true);
			cpttree.setBounds(30, 120, 200, 400);
			isCustomer = false;
			selectedbranch = null;
		}

		return canDRSShortage;
	}
	
	private Canvas getCreateCanvas() throws Exception{
		//this.setSize(725, 600);
		canCustomer = new Canvas(tabCustomer, SWT.NONE);
		tabItem1.setControl(canCustomer);
		
		{
			lblCustomerName = new Label(canCustomer, SWT.NONE);
			lblCustomerName.setText("Customer");
			lblCustomerName.setFont(null);
			lblCustomerName.setBounds(202, 13, 76, 20);
		}
		
		{
			lblStation = new Label(canCustomer, SWT.NONE);
			lblStation.setText("Station");
			lblStation.setFont(null);
			lblStation.setBounds(53, 13, 63, 20);
		}
		{
			cbStation = new Combo(canCustomer, SWT.NONE);
			cbStation.setBounds(53, 33, 120, 20);
			cbStation.addSelectionListener(new CustomerListener());
			cbStation.addKeyListener(new KeyListener() {

				@Override
				public void keyPressed(KeyEvent e) {

					String selectedText = cbStation.getText();
					if (e.keyCode == SWT.ARROW_UP
							|| e.keyCode == SWT.ARROW_DOWN
							|| e.keyCode == SWT.CR)
						return;

					if (e.character == '\b') {
						int len = selectedText.length();

						if (len > 1)
							selectedText = selectedText.substring(0,
									len - 2);
						else
							selectedText = "";

						cbStation.remove(0, cbStation.getItemCount() - 1);
						populateDestStationCodes();
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

						String[] items = cbStation.getItems();
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
							cbStation.remove(0, len - 1);

							for (int i = startIndex; i < len; i++) {
								String temp = items[i].toLowerCase();
								if (temp.contains(selectedText)) {
									cbStation.add(items[i]);
								}
							}
							if (selectedText.length() == 1) {
								showPopup(cbStation, true);
							}
						}
					}
				}

				public void keyReleased(KeyEvent e) {
					String temp = cbStation.getText();
					// System.out.println("temp==>"+temp);
					int len = temp.length();
					if (len == 0) {
						cbStation.remove(0, cbStation.getItemCount() - 1);
						populateDestStationCodes();
					}
				}
			});

			cbStation.addFocusListener(new FocusListener() {

				@Override
				public void focusGained(FocusEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void focusLost(FocusEvent arg0) {
					// System.out.println("selected==>"+cbStation.getSelectionIndex());
					if (cbStation.getSelectionIndex() == -1) {
						cbStation.select(0);
					}
				}

			});
			// cbStation.addSelectionListener(new CustomerListener());
			populateDestStationCodes();
		}
		
		{
			cbCustomerName = new Combo(canCustomer, SWT.NONE);
			cbCustomerName.setFont(null);
			cbCustomerName.setBounds(202, 33, 120, 20);
			cbCustomerName.setTextLimit(30);
		}
		
		{
			
			rdBilling = new Button(canCustomer, SWT.RADIO | SWT.LEFT);
			rdBilling.setText("Billing");
			rdBilling.setBounds(53, 71, 56, 31);
			rdBilling.addSelectionListener(new CustomerListener());
			
		}
		{
			rdTopay = new Button(canCustomer, SWT.RADIO | SWT.LEFT);
			rdTopay.setText("Topay");
			rdTopay.setBounds(121, 74, 60, 26);			
			rdTopay.addSelectionListener(new CustomerListener());
		}
		{
			rdPaid = new Button(canCustomer, SWT.RADIO | SWT.LEFT);
			rdPaid.setText("Paid");
			rdPaid.setBounds(193, 73, 55, 30);
			rdPaid.addSelectionListener(new CustomerListener());
		}
		{
			rdAgent = new Button(canCustomer, SWT.RADIO | SWT.LEFT);
			rdAgent.setText("Agent");
			rdAgent.setBounds(248, 71, 47, 33);
			rdAgent.addSelectionListener(new CustomerListener());
		}
		{
			gpConsignor = new Group(canCustomer, SWT.NONE);
			gpConsignor.setBounds(301, 70, 111, 70);
			
			rdConsignor = new Button(gpConsignor, SWT.RADIO | SWT.LEFT);
			rdConsignor.setBounds(10,10, 70, 20);
			rdConsignor.setText("Consignor");
			rdConsignor.setSelection(true);
			
			rdConsignee = new Button(gpConsignor, SWT.RADIO | SWT.LEFT);
			rdConsignee.setBounds(10,40, 70, 20);
			rdConsignee.setText("Consignee");
			
			gpConsignor.setVisible(false);
		}
		
		{
			gpAddress = new Group(canCustomer, SWT.NONE);
			gpAddress.setText("Address");
			gpAddress.setBounds(44, 176, 611, 150);
			{
				txtAddress1 = new Text(gpAddress, SWT.BORDER);
				txtAddress1.setBounds(9, 43, 263, 23);
				txtAddress1.setFont(null);
				txtAddress1.setTextLimit(100);
			}

			{
				txtAddress2 = new Text(gpAddress, SWT.BORDER);
				txtAddress2.setBounds(308, 44, 263, 21);
				txtAddress2.setFont(null);
				txtAddress2.setTextLimit(100);
			}

			{
				lblCity = new Label(gpAddress, SWT.NONE);
				lblCity.setText("City");
				lblCity.setBounds(12, 90, 60, 20);
				lblCity.setFont(null);
			}
			{
				lblState = new Label(gpAddress, SWT.NONE);
				lblState.setText("State");
				lblState.setBounds(161, 91, 60, 20);
				lblState.setFont(null);
			}
			{
				lblPincode = new Label(gpAddress, SWT.NONE);
				lblPincode.setText("Pincode");
				lblPincode.setBounds(311, 90, 45, 20);
				lblPincode.setFont(null);
			}
			{
				txtCity = new Text(gpAddress, SWT.BORDER);
				txtCity.setBounds(10, 110, 120, 20);
				txtCity.setFont(null);
				txtCity.setTextLimit(15);
			}

			{
				txtState = new Text(gpAddress, SWT.BORDER);
				txtState.setBounds(162, 111, 111, 20);
				txtState.setFont(null);
				txtState.setTextLimit(15);
			}

			{
				txtPincode = new Text(gpAddress, SWT.BORDER);
				txtPincode.setBounds(310, 110, 80, 20);
				txtPincode.setFont(null);
				txtPincode.addVerifyListener(new NumericValidation());
				txtPincode.setTextLimit(10);
			}
			
			{
				lblTin = new Label(gpAddress, SWT.NONE);
				lblTin.setText("Tin No");
				lblTin.setBounds(454, 90, 40, 20);
			}
			
			{
				txtTin = new Text(gpAddress, SWT.BORDER);
				txtTin.setBounds(454, 110, 80, 20);					
				txtTin.setTextLimit(10);
			}
			
			{
				lblAddress1 = new Label(gpAddress, SWT.NONE);
				lblAddress1.setText("Address1");
				lblAddress1.setBounds(9, 24, 60, 17);
			}
			{
				lblAddress2 = new Label(gpAddress, SWT.NONE);
				lblAddress2.setText("Address2");
				lblAddress2.setBounds(308, 24, 235, 21);
			}

		}

		{
			gpContact = new Group(canCustomer, SWT.NONE);
			gpContact.setText("Contact");
			gpContact.setBounds(44, 348, 616, 101);
			{
				lblContactPerson = new Label(gpContact, SWT.NONE);
				lblContactPerson.setText("Contact Person");
				lblContactPerson.setBounds(9, 24, 85, 19);
			}
			{
				txtContactPerson = new Text(gpContact, SWT.BORDER);
				txtContactPerson.setBounds(9, 43, 120, 20);
				txtContactPerson.setTextLimit(30);
			}
			{
				lblMobile = new Label(gpContact, SWT.NONE);
				lblMobile.setText("Mobile");
				lblMobile.setBounds(164, 24, 60, 15);
			}
			{
				txtMobile = new Text(gpContact, SWT.BORDER);
				txtMobile.setBounds(164, 44, 111, 20);
				txtMobile.setTextLimit(15);
			}
			{
				lblPhone = new Label(gpContact, SWT.NONE);
				lblPhone.setText("Landline");
				lblPhone.setBounds(313, 24, 60, 21);
			}
			{
				txtPhone = new Text(gpContact, SWT.BORDER);
				txtPhone.setBounds(313, 45, 100, 20);
				txtPhone.setTextLimit(15);
			}
			{
				lblMailId = new Label(gpContact, SWT.NONE);
				lblMailId.setText("Mail Id");
				lblMailId.setBounds(454, 24, 60, 18);
			}
			{
				txtMailId = new Text(gpContact, SWT.BORDER);
				txtMailId.setBounds(454, 45, 150, 20);
				txtMailId.setTextLimit(40);
			}
		}
		{
			gpBillingDetails = new Group(canCustomer, SWT.NONE);
			gpBillingDetails.setText("Billing Details");
			gpBillingDetails.setBounds(47, 469, 302, 88);
			{
				cbBasicFormat = new Combo(gpBillingDetails, SWT.READ_ONLY);
				cbBasicFormat.setBounds(6, 45, 124, 21);
				cbBasicFormat.add(BASICFORMAT1);
				cbBasicFormat.add(BASICFORMAT2);					
				cbBasicFormat.addSelectionListener(new CustomerListener());
			}
			{
				lblBasicFormat = new Label(gpBillingDetails, SWT.NONE);
				lblBasicFormat.setText("Basic Format");
				lblBasicFormat.setBounds(6, 23, 95, 22);
			}
			
			{
				chkAddOnFormat1 = new Button(gpBillingDetails, SWT.CHECK);
				chkAddOnFormat1.setBounds(166, 20, 124, 20);
				chkAddOnFormat1.setText(ADDONFORMAT1);
				chkAddOnFormat1.addSelectionListener(new CustomerListener());
			}
			{
				chkAddOnFormat2 = new Button(gpBillingDetails, SWT.CHECK);
				chkAddOnFormat2.setBounds(166, 40, 124, 20);
				chkAddOnFormat2.setText(ADDONFORMAT2);
				chkAddOnFormat2.addSelectionListener(new CustomerListener());
			}
			{
				chkAddOnFormat3 = new Button(gpBillingDetails, SWT.CHECK);
				chkAddOnFormat3.setBounds(166, 60, 124, 20);
				chkAddOnFormat3.setText(ADDONFORMAT3);
				chkAddOnFormat3.addSelectionListener(new CustomerListener());
			}
		}
		{
			btnCreate = new Button(canCustomer, SWT.PUSH | SWT.CENTER);
			btnCreate.setText("Create");
			btnCreate.setBounds(449, 509, 94, 30);
			btnCreate.addSelectionListener(new CustomerListener());
		}
		{
			btnView = new Button(canCustomer, SWT.PUSH | SWT.CENTER);
			btnView.setText("View");
			btnView.setBounds(330, 30, 48, 26);
			btnView.addSelectionListener(new CustomerListener());
		}
		{
			btnNew = new Button(canCustomer, SWT.PUSH | SWT.CENTER);
			btnNew.setText("New");
			btnNew.setBounds(383, 30, 48, 26);
			btnNew.addSelectionListener(new CustomerListener());
		}
		
		
		{
			chkEdit = new Button(canCustomer, SWT.CHECK);
			chkEdit.setText("Edit");
			chkEdit.setBounds(437, 30, 48, 26);
			chkEdit.addSelectionListener(new CustomerListener());
			chkEdit.setVisible(false);
		}
		
		{
			txtQuotationId = new Text(canCustomer, SWT.None);
			txtQuotationId.setBounds(500, 42, 48, 26);
			txtQuotationId.setVisible(false);
		}
		{
			gpNotification = new Group(canCustomer, SWT.NONE);
			gpNotification.setText("SMS Notification");
			gpNotification.setBounds(53, 105, 182, 55);
			
			chkConsignor = new Button(gpNotification, SWT.CHECK | SWT.LEFT);
			chkConsignor.setBounds(10,20, 70, 20);
			chkConsignor.setText("Consignor");
			
			
			chkConsignee = new Button(gpNotification, SWT.CHECK | SWT.LEFT);
			chkConsignee.setBounds(110,20, 70, 20);
			chkConsignee.setText("Consignee");
			
			gpNotification.setVisible(false);
		}
		return canCustomer;
	}

	private void populateCustomerNames(String stnCode) throws Exception {
		cbCustomerName.removeAll();
		if (null != customernames) {
			int length = customernames.length;
			for (int i = 0; i < length; i++) {
				if(stnCode.equals(customernames[i].getStation())){
					cbCustomerName.add(customernames[i].getName());
				}
			}
			
			String[] items = cbCustomerName.getItems();
			cbCustomerName.removeAll();
			Arrays.sort(items, String.CASE_INSENSITIVE_ORDER);
			cbCustomerName.setItems(items);

		}
	}
	
	

	class CustomerListener implements SelectionListener {

		@Override
		public void widgetDefaultSelected(SelectionEvent event) {

		}		

		@Override
		public void widgetSelected(SelectionEvent event) {

			Object source = event.getSource();

			if(source == cbStation){
				AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				String stationCode = EMPTY_STRING;
				
				if(!cbStation.getText().equals("")){
					try {
						stationCode = cbStation.getText();
						int index = stationCode.indexOf("-");
						if(index > -1){
							stationCode = stationCode.substring(index + 2);
							populateCustomerNames(stationCode);
						}
						
					} catch (Exception e) {					
						e.printStackTrace();
					}
				}
				
			}else if (source == btnCreate) {
				AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				
				if(btnCreate.getText().equals("Create") || btnCreate.getText().equals("Update")){
				
				if (validateCustomerDetails()){
					customerDetails = buildDTO();
				}else{
					customerDetails = null;
				}
				
				if (null != customerDetails && null != handler) {
					try {						
						handler.createAdminCustomer(customerDetails);
						if (!isUpdatecustomer){
							AdminComposite.display("Customer Creation Successful", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
							clearAllFields();
							customernames =  getCustomerDetails();
						}else{
							AdminComposite
									.display("Customer Updation Successful", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
							clearAllFields();
							enableFields();
							chkEdit.setSelection(false);
							chkEdit.setVisible(false);
							btnCreate.setText("Create");
							customernames =  getCustomerDetails();
						}
						
						
					} catch (Exception e) {
						if (!isUpdatecustomer)
							AdminComposite.display("Customer Creation Failed", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						else
							AdminComposite.display("Customer Updation Failed", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						e.printStackTrace();
					}
				}
				}else if(btnCreate.getText().equals("Delete")){
					try {					
						String delCustomer = cbCustomerName.getText();
						handler.deleteAdminCustomer(delCustomer, txtQuotationId.getText());
						AdminComposite.display("Customer Deleted Successfully", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						clearAllFields();
						enableFields();
						cbCustomerName.removeAll();
						chkEdit.setSelection(false);
						chkEdit.setVisible(false);
						btnCreate.setText("Create");		
						
						if (null != customernames) {
							int length = customernames.length;
							for (int i = 0; i < length; i++) {
								if(delCustomer.equals(customernames[i].getName())){									
									customernames[i].setStation(null);
								}
							}
						}
						
					} catch (Exception e) {
						AdminComposite.display("Customer Deletion Failed ", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					}					
				}
			} else if (source == btnView) {
				if(!cbStation.getText().equals(EMPTY_STRING) && !cbCustomerName.getText().equals(EMPTY_STRING)){
				
				try {					
					if (null != customernames) {
						//clearAllFields();
						populateCustomerDetails(customernames);
						AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				}
			}else if(source == btnNew){
				clearAllFields();
				enableFields();
				chkEdit.setSelection(false);
				chkEdit.setVisible(false);
				btnCreate.setText("Create");
				AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			}else if(source == rdAgent){
				gpConsignor.setVisible(true);
				gpNotification.setVisible(false);
				rdConsignor.setSelection(true);
			}else if(source == rdTopay){
				gpConsignor.setVisible(false);
				gpNotification.setVisible(true);
			}else if(source == rdPaid){
				gpConsignor.setVisible(false);
				gpNotification.setVisible(true);
			}else if(source == rdBilling){
				gpConsignor.setVisible(false);
				gpNotification.setVisible(true);
			}else if(source == chkEdit){
				if(chkEdit.getSelection()){
					btnCreate.setText("Update");
					enableFields();
				}else{
					btnCreate.setText("Delete");
					disableFields();
				}
			}else if (source == cbDRSBranch) {
				int index = cbDRSBranch.getSelectionIndex();
				if (index > -1) {
					selectedbranch = cbDRSBranch.getItem(index);
					try {
						if (null != tree)
							tree.dispose();

						isCustomer = true;
						tree = new TreeComposite(canDRSShortage, SWT.NONE, 300);
						cpttree = tree.loadComposite();
						cpttree.setVisible(true);
						cpttree.setBounds(30, 120, 200, 400);
						isCustomer = false;
						selectedbranch = null;
					} catch (Exception e) {					
						e.printStackTrace();
					}
				}
			} else if (source == btnDRSDay) {
				try {
					String[] customers = tree.getSelectedMSBCustomer();
					if (null != customers) {
						//System.out.println("cus-->"+customers[0]);
						boolean isDRSShortage = cbDRSShortage.getSelection();
						int drsdays = Integer.parseInt(txtDRSDay.getText());
						handler.changeRemittanceShortagePrivilage(customers,
								isDRSShortage, drsdays);

						AdminComposite.display(
								"DRSShortage Settings Successful",
								STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					}

				} catch (Exception e) {
					AdminComposite.display("DRSShortage Settimgs Failed",
							STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					e.printStackTrace();
				}
			}
		}
	}

	
	/**
	 * 
	 * @return
	 */
	private CustomerDTO buildDTO() {

		CustomerDTO customer = null;
		String stationCode = EMPTY_STRING;
		String basicFormat = EMPTY_STRING;		

		customer = new CustomerDTO();

		int index = cbCustomerName.getSelectionIndex();
		//System.out.println("index==>"+index);
		if (index > -1) {
			isUpdatecustomer = true;
		}else{
			isUpdatecustomer = false;
		}
		customer.setName(cbCustomerName.getText());

		stationCode = cbStation.getText();
		
		index = stationCode.indexOf("-");
		if(index > -1)
			stationCode = stationCode.substring(index + 2);
		
		
		
		customer.setStation(stationCode);
		//System.out.println("stationCode:" + stationCode+"==>"+index);

		basicFormat = cbBasicFormat.getText();
		if (basicFormat.equalsIgnoreCase(BASICFORMAT1)) {
			customer.setBasicFormat((byte) 0);
		} else if (basicFormat.equalsIgnoreCase(BASICFORMAT2)) {
			customer.setBasicFormat((byte) 1);
		}else{
			customer.setBasicFormat((byte) -1);
		}			
		
		if(chkAddOnFormat1.getSelection()){
			customer.setAddOnFormat1((byte) 1);
		}else{
			customer.setAddOnFormat1((byte) 0);
		}
		
		if(chkAddOnFormat2.getSelection()){
			customer.setAddOnFormat2((byte) 1);
		}else{
			customer.setAddOnFormat2((byte) 0);
		}
		
		if(chkAddOnFormat3.getSelection()){
			customer.setAddOnFormat3((byte) 1);
		}else{
			customer.setAddOnFormat3((byte) 0);
		}
		
		customer.setAddress(txtAddress1.getText());
		
		customer.setAddress2(txtAddress2.getText());
		
		customer.setCity(txtCity.getText());
		
		customer.setState(txtState.getText());
		
		if (EMPTY_STRING != txtPincode.getText())
			customer.setPincode(Integer.parseInt(txtPincode.getText()));
		
		customer.setLandLine(txtPhone.getText());

		
		customer.setPhone(txtMobile.getText());

		
		customer.setContactPerson(txtContactPerson.getText());
		
		customer.setEmailId(txtMailId.getText());
		
		if(rdAgent.getSelection()){
			customer.setQuotation_type(0);
			if(rdConsignor.getSelection()){
				customer.setTypeOfCustomer("Consignor");
			}else{
				customer.setTypeOfCustomer("Consignee");
			}
			
		}else{
			
			if(rdTopay.getSelection()){
				customer.setQuotation_type(1);
			}else if(rdPaid.getSelection()){
				customer.setQuotation_type(2);
			}else if(rdBilling.getSelection()){
				customer.setQuotation_type(3);
			}
			
			// SMS
			if(chkConsignor.getSelection()){
				customer.setCnorSMS((byte)1);
			}else{
				customer.setCnorSMS((byte)0);
			}
			if(chkConsignee.getSelection()){
				customer.setCneeSMS((byte)1);
			}else{
				customer.setCneeSMS((byte)0);
			}
		}		
				
		customer.setTinNo(txtTin.getText());
		
		
		return customer;
	}

	

	/**
	 * 
	 */
	private void clearAllFields() {
		cbCustomerName.setText(EMPTY_STRING);
		cbBasicFormat.removeAll();
		cbBasicFormat.add(BASICFORMAT1);
		cbBasicFormat.add(BASICFORMAT2);		
		cbStation.setText(EMPTY_STRING);
		txtAddress1.setText(EMPTY_STRING);
		txtAddress2.setText(EMPTY_STRING);
		txtCity.setText(EMPTY_STRING);
		txtState.setText(EMPTY_STRING);
		txtPincode.setText(EMPTY_STRING);
		txtContactPerson.setText(EMPTY_STRING);
		txtMobile.setText(EMPTY_STRING);
		txtPhone.setText(EMPTY_STRING);
		txtMailId.setText(EMPTY_STRING);		
		txtTin.setText(EMPTY_STRING);
		rdTopay.setSelection(false);
		rdPaid.setSelection(false);
		rdBilling.setSelection(false);
		rdAgent.setSelection(false);
		gpConsignor.setVisible(false);
		rdConsignee.setSelection(false);
		rdConsignor.setSelection(false);
		chkAddOnFormat1.setSelection(false);
		chkAddOnFormat2.setSelection(false);
		chkAddOnFormat3.setSelection(false);
		gpNotification.setVisible(false);
	}
	
	private void disableFields() {
		cbCustomerName.setEnabled(false);
		cbStation.setEnabled(false);
		cbBasicFormat.setEnabled(false);
		
		
		txtAddress1.setEnabled(false);
		txtAddress2.setEnabled(false);
		txtCity.setEnabled(false);
		txtState.setEnabled(false);
		txtPincode.setEnabled(false);
		txtContactPerson.setEnabled(false);
		txtMobile.setEnabled(false);
		txtPhone.setEnabled(false);
		txtMailId.setEnabled(false);		
		txtTin.setEnabled(false);
		
		
		rdTopay.setEnabled(false);
		rdPaid.setEnabled(false);
		rdBilling.setEnabled(false);
		rdAgent.setEnabled(false);
		
		gpConsignor.setEnabled(false);
		rdConsignee.setEnabled(false);
		rdConsignor.setEnabled(false);
		
		chkAddOnFormat1.setEnabled(false);
		chkAddOnFormat2.setEnabled(false);
		chkAddOnFormat3.setEnabled(false);		
		
		gpNotification.setEnabled(false);
		
	}
	
	private void enableFields() {
		cbCustomerName.setEnabled(true);
		cbStation.setEnabled(true);
		cbBasicFormat.setEnabled(true);		
		
		txtAddress1.setEnabled(true);
		txtAddress2.setEnabled(true);
		txtCity.setEnabled(true);
		txtState.setEnabled(true);
		txtPincode.setEnabled(true);
		txtContactPerson.setEnabled(true);
		txtMobile.setEnabled(true);
		txtPhone.setEnabled(true);
		txtMailId.setEnabled(true);		
		txtTin.setEnabled(true);		
		
		rdTopay.setEnabled(true);
		rdPaid.setEnabled(true);
		rdBilling.setEnabled(true);
		rdAgent.setEnabled(true);
		
		gpConsignor.setEnabled(true);
		rdConsignee.setEnabled(true);
		rdConsignor.setEnabled(true);
		
		chkAddOnFormat1.setEnabled(true);
		chkAddOnFormat2.setEnabled(true);
		chkAddOnFormat3.setEnabled(true);		
		
		gpNotification.setEnabled(true);
		
	}

	/**
	 * Method to validate Customer Details
	 * 
	 * @return boolean  <li> true - if the validation succeeds </li>
	 * 					<li> false - if the validation fails </li>
	 */
	private boolean validateCustomerDetails() {

		if (cbCustomerName.getText().equals(EMPTY_STRING)) {
			AdminComposite.display("Please provide Customer Name", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;
		} else if (cbStation.getText().equals(EMPTY_STRING)) {
			AdminComposite.display("Please provide Station Name", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;
		} else if (!rdTopay.getSelection() && !rdPaid.getSelection() && !rdBilling.getSelection() && !rdAgent.getSelection() ) {
			AdminComposite.display("Please select customer type", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;
		} else if (txtMailId.getText().equals(EMPTY_STRING)) {
			AdminComposite.display("Please provide Mail Id", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;
		} else if (rdBilling.getSelection() && cbBasicFormat.getText().equals(EMPTY_STRING)) {
			AdminComposite.display("Please select basic format", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;
		} 

		return true;
	}
	
	/**
	 * 
	 * @param details
	 */
	public void populateCustomerDetails(CustomerDTO[] details) {
		try {			
			String text = "";
			String stnName = "";
			int length = details.length;
			
			
			
			for (int i = 0; i < length; i++) {
				String selectedname = cbCustomerName.getText();
				String name = details[i].getName();
				if (selectedname.equals(name)) {

					text = details[i].getName();
					if (text != null)
						cbCustomerName.setText(text);

					text = details[i].getQuotationId();
					if (text != null)
						txtQuotationId.setText(text);

					text = details[i].getStation();
					stnName = getStationName(text);
					if (text != null && stnName != null)
						cbStation.setText(stnName + " - " + text);
					text = details[i].getAddress();
					if (text != null)
						txtAddress1.setText(text);
					text = details[i].getAddress2();
					if (text != null)
						txtAddress2.setText(text);
					text = details[i].getCity();
					if (text != null)
						txtCity.setText(text);
					text = details[i].getState();
					if (text != null)
						txtState.setText(text);
					text = String.valueOf(details[i].getPincode());
					if (text != null)
						txtPincode.setText(text);
					text = details[i].getContactPerson();
					if (text != null)
						txtContactPerson.setText(text);
					text = details[i].getPhone();
					if (text != null)
						txtMobile.setText(text);
					text = details[i].getLandLine();
					if (text != null)
						txtPhone.setText(text);
					text = details[i].getEmailId();
					if (text != null)
						txtMailId.setText(text);
					if (details[i].getBasicFormat() == 0) {
						cbBasicFormat.setText(BASICFORMAT1);
					} else if (details[i].getBasicFormat() == 1){
						cbBasicFormat.setText(BASICFORMAT2);
					}

					if (details[i].getAddOnFormat1() == 1) {
						chkAddOnFormat1.setSelection(true);
					} else {
						chkAddOnFormat1.setSelection(false);
					}

					if (details[i].getAddOnFormat2() == 1) {
						chkAddOnFormat2.setSelection(true);
					} else {
						chkAddOnFormat2.setSelection(false);
					}

					if (details[i].getAddOnFormat3() == 1) {
						chkAddOnFormat3.setSelection(true);
					} else {
						chkAddOnFormat3.setSelection(false);
					}

					if (details[i].getQuotation_type() == 1) {
						rdTopay.setSelection(true);
						rdPaid.setSelection(false);
						rdBilling.setSelection(false);
						rdAgent.setSelection(false);
						//SMS
						if (details[i].getCneeSMS() == 1) {
							chkConsignee.setSelection(true);
						} else {
							chkConsignee.setSelection(false);
						}
						gpNotification.setVisible(true);
						gpConsignor.setVisible(false);
					} else if (details[i].getQuotation_type() == 2) {
						rdTopay.setSelection(false);
						rdPaid.setSelection(true);
						rdBilling.setSelection(false);
						rdAgent.setSelection(false);
						if (details[i].getCneeSMS() == 1) {
							chkConsignee.setSelection(true);
						} else {
							chkConsignee.setSelection(false);
						}
						gpNotification.setVisible(true);
						gpConsignor.setVisible(false);
					} else if (details[i].getQuotation_type() == 3) {
						rdTopay.setSelection(false);
						rdPaid.setSelection(false);
						rdBilling.setSelection(true);
						rdAgent.setSelection(false);
						if (details[i].getCneeSMS() == 1) {
							chkConsignee.setSelection(true);
						} else {
							chkConsignee.setSelection(false);
						}
						gpNotification.setVisible(true);
						gpConsignor.setVisible(false);
					} else if (details[i].getQuotation_type() == 0) {
						rdTopay.setSelection(false);
						rdPaid.setSelection(false);
						rdBilling.setSelection(false);
						rdAgent.setSelection(true);
						gpConsignor.setVisible(true);
						gpNotification.setVisible(false);
						if (details[i].getTypeOfCustomer().equalsIgnoreCase(
								"consignor")) {
							rdConsignor.setSelection(true);
							rdConsignee.setSelection(false);
						} else {
							rdConsignee.setSelection(true);
							rdConsignor.setSelection(false);
						}
					} else if (details[i].getQuotation_type() == -1) {
						rdTopay.setSelection(false);
						rdPaid.setSelection(false);
						rdBilling.setSelection(false);
						rdAgent.setSelection(false);
					}
					if (details[i].getTinNo() != null
							&& !details[i].getTinNo().equals(""))
						txtTin.setText(details[i].getTinNo());
					
					//SMS
					if (details[i].getCnorSMS() == 1) {
						chkConsignor.setSelection(true);
					} else {
						chkConsignor.setSelection(false);
					}
					
					
					

					chkEdit.setVisible(true);
					chkEdit.setSelection(false);
					btnCreate.setText("Delete");
					disableFields();

					break;
				}

			}
			
		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}

	private String getStationName(String stnCode) {	
			int size = 0;			
			try {
				StationsDTO[] stations = handler.getAvailableStations();
				size = stations.length;
				if (size > 0) {
					for (int i = 0; i < size; i++) {
						if (stations[i].getId().equals(stnCode)) {
							return stations[i].getName();
						}
					}
				}
			} catch (Exception e) {				
				e.printStackTrace();
			}
		
		return null;
	}

	public CustomerDTO[] getCustomerDetails() throws Exception {
		CustomerDTO[] customer = null;
		if (null != handler) {
			customer = handler.getAdminCustomerDetails();
		}
		return customer;
	}

	private void populateDestStationCodes() {
		try {
			StationsDTO[] stations = handler.getAvailableStations();
			if (null != stations) {
				int len = stations.length;
				for (int i = 0; i < len; i++) {
					cbStation.add(stations[i].getName() + " - " + stations[i].getId());
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private static void showPopup(Combo combo, boolean show) {
		if (!combo.isDisposed()) {
			OS.SendMessage(combo.handle, OS.CB_SHOWDROPDOWN, show ? 1 : 0, 0);
		}
	}
	
	
	/*private void clearFields(){
		txtAddress1.setText(EMPTY_STRING);
		txtAddress2.setText(EMPTY_STRING);
		txtCity.setText(EMPTY_STRING);
		txtContactPerson.setText(EMPTY_STRING);
		txtMailId.setText(EMPTY_STRING);
		txtMobile.setText(EMPTY_STRING);
		txtPhone.setText(EMPTY_STRING);
		txtPincode.setText(EMPTY_STRING);
		txtState.setText(EMPTY_STRING);		
		cbBasicFormat.setText(EMPTY_STRING);
		cbCustomerName.setText(EMPTY_STRING);
		cbStation.setText(EMPTY_STRING);
	}*/
	
}
