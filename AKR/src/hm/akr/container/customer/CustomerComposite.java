package hm.akr.container.customer;

import hm.akr.common.BeanUtil;
import hm.akr.common.SWTResourceManager;
import hm.akr.dto.CustomerDTO;

import java.util.Arrays;
import java.util.Properties;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
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
public class CustomerComposite extends Composite implements FocusListener {

	{
		// Register as a resource user - SWTResourceManager will
		// handle the obtaining and disposing of resources
		SWTResourceManager.registerResourceUser(this);
	}

	Properties properties = null;

	private Label lblCustomerName;

	private Text txtAreaCustomerAddress;

	private Button btnSave;

	private Button btnDelete;

	private Combo cbCustomerName;

	private Label lblCustomerAddress;

	CustomerCompositeHandler handler = null;

	// ContactsManager contacts = null;

	CustomerDTO dto = null;

	private Button rdConsignor;

	private Button rdConsignee;

	BeanUtil beanutil = null;

	String stationCode = null;
	private Label lblCustLandline;

	private static final String EMPTY_STRING = "";

	private Text txtTin;
	private Label lblTin;
	private Label lblName;

	private Label lblPhone;

	private Text txtPhone;

	private Text txtLandline;

	//private Button btnSTax;

	public CustomerComposite(Composite parent, int swtValue) {
		super(parent, swtValue);

		try {
			handler = new CustomerCompositeHandler();
			beanutil = BeanUtil.getInstance();
			// contacts = new ContactsManager();
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	/**
	 * Method to load Customer Composite
	 * 
	 * @return
	 */
	public Composite loadComposite() {
		try {
			{
				this.setSize(500, 450);
				{
					lblCustomerName = new Label(this, SWT.NONE);
					lblCustomerName.setText("Customer Name");
					lblCustomerName.setBounds(76, 76, 98, 30);
					lblCustomerName.setAlignment(SWT.RIGHT);

				}
				{
					cbCustomerName = new Combo(this, SWT.NONE);
					cbCustomerName.setBounds(182, 75, 179, 21);
					cbCustomerName.addSelectionListener(new CustomerListener());
					cbCustomerName.addFocusListener(this);
					cbCustomerName.setTextLimit(40);

				}
				{
					lblCustomerAddress = new Label(this, SWT.NONE);
					lblCustomerAddress.setText("Customer Address");
					lblCustomerAddress.setBounds(78, 115, 98, 30);
					lblCustomerAddress.setAlignment(SWT.RIGHT);
				}
				{
					txtAreaCustomerAddress = new Text(this, SWT.V_SCROLL
							| SWT.BORDER | SWT.WRAP);
					txtAreaCustomerAddress.setBounds(184, 113, 178, 138);
					txtAreaCustomerAddress
							.addTraverseListener(new TraverseListener() {
								public void keyTraversed(TraverseEvent e) {
									if (e.detail == SWT.TRAVERSE_TAB_NEXT
											|| e.detail == SWT.TRAVERSE_TAB_PREVIOUS) {
										e.doit = true;
									}
								}
							});
				}

				{
					lblTin = new Label(this, SWT.NONE);
					lblTin.setText("Tin No");
					lblTin.setBounds(114, 265, 64, 23);
					lblTin.setAlignment(SWT.RIGHT);
				}
				{
					txtTin = new Text(this, SWT.BORDER);
					txtTin.setBounds(183, 262, 179, 25);
				}

				{
					lblPhone = new Label(this, SWT.NONE);
					lblPhone.setText("Mobile");
					lblPhone.setBounds(94, 305, 85, 30);
					lblPhone.setAlignment(SWT.RIGHT);
				}
				{
					txtPhone = new Text(this, SWT.BORDER);
					txtPhone.setBounds(183, 302, 179, 25);
				}

				{

					txtLandline = new Text(this, SWT.BORDER);
					txtLandline.setBounds(183, 340, 179, 25);

				}
				/*{
					btnSTax = new Button(this, SWT.CHECK | SWT.LEFT);
					btnSTax.setText("Service Tax");
					btnSTax.setBounds(183, 370, 179, 25);
				}*/
				{
					btnSave = new Button(this, SWT.PUSH | SWT.CENTER);
					btnSave.setText("Save");
					btnSave.setBounds(219, 400, 60, 30);
					btnSave.addSelectionListener(new CustomerListener());
				}
				{
					btnDelete = new Button(this, SWT.PUSH | SWT.CENTER);
					btnDelete.setText("Delete");
					btnDelete.setBounds(280, 400, 60, 30);
					btnDelete.addSelectionListener(new CustomerListener());
				}
				{

					FormData canvas1LData = new FormData();
					canvas1LData.width = 199;
					canvas1LData.height = 96;
					canvas1LData.left = new FormAttachment(0, 1000, 49);
					canvas1LData.top = new FormAttachment(0, 1000, 23);

				}
				{
					rdConsignee = new Button(this, SWT.RADIO | SWT.LEFT);
					rdConsignee.setText("Consignee");
					rdConsignee.setBounds(268, 32, 70, 27);
					rdConsignee.addSelectionListener(new CustomerListener());
				}
				{
					rdConsignor = new Button(this, SWT.RADIO | SWT.LEFT);
					rdConsignor.setText("Consignor");
					rdConsignor.setBounds(182, 32, 78, 28);
					rdConsignor.setSelection(true);
					{
						lblCustomerName.setText("Consignor Name");
						lblCustomerAddress.setText("Consignor Address");
						txtAreaCustomerAddress.setText("");

						try {
							populateConsignorNames();
						} catch (Exception exception) {
							exception.printStackTrace();
						}
					}
					rdConsignor.addSelectionListener(new CustomerListener());
				}
				{
					lblName = new Label(this, SWT.NONE);
					lblName.setText("CUSTOMER");
					lblName.setBounds(831, 20, 160, 32);
					lblName.setFont(SWTResourceManager.getFont("Tahoma", 20, 1,
							false, false));
				}
				{
					lblCustLandline = new Label(this, SWT.NONE);
					lblCustLandline.setText("Land Line");
					lblCustLandline.setBounds(106, 342, 73, 25);
					lblCustLandline.setAlignment(SWT.RIGHT);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;

	}

	/**
	 * Method to populate ConsignorNames
	 */
	private void populateConsignorNames() {
		String[] consignor = null;
		if (null != handler) {
			consignor = handler.getSundryCustomers(false);

			if (null != consignor) {
				Arrays.sort(consignor);
				cbCustomerName.removeAll();
				cbCustomerName.setItems(consignor);

			} else {
				cbCustomerName.removeAll();
			}
		}

	}

	/**
	 * Method to populate consignee name from Local
	 */
	private void populateConsigneeNames() {
		String[] consignee = null;
		if (null != handler) {
			consignee = handler.getSundryCustomers(true);
			if (null != consignee) {
				Arrays.sort(consignee);
				cbCustomerName.removeAll();
				cbCustomerName.setItems(consignee);

			} else {
				cbCustomerName.removeAll();
			}
		}

	}

	/**
	 * 
	 * @author Administrator
	 * 
	 */
	public class CustomerListener implements SelectionListener {

		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {

		}

		@Override
		public void widgetSelected(SelectionEvent event) {

			Object source = event.getSource();
			if (btnSave == source) {
				if ((cbCustomerName.getText() != "")) {
					if ((txtAreaCustomerAddress.getText() != "")) {
						try {
							handleSubmitAction();

						} catch (Exception exception) {
							exception.printStackTrace();
						}
					} else {
						displayError("Enter Address!");
					}
				} else {
					displayError("Enter Name!");
				}

			} else if (btnDelete == source) {
				try {
					handleDeleteAction();

				} catch (Exception exception) {
					exception.printStackTrace();

				}
			} else if (cbCustomerName == source) {
				try {
					handleItemChangeAction();
				} catch (Exception exception) {

				}
			} else if (rdConsignee == source) {
				lblCustomerName.setText("Consignee Name");
				lblCustomerAddress.setText("Consignee Address");
				txtAreaCustomerAddress.setText("");
				txtTin.setText("");
				txtPhone.setText("");
				txtLandline.setText("");
				//btnSTax.setSelection(false);
				try {

					populateConsigneeNames();
				} catch (Exception exception) {

				}
			} else if (rdConsignor == source) {
				lblCustomerName.setText("Consignor Name");
				lblCustomerAddress.setText("Consignor Address");
				txtAreaCustomerAddress.setText("");
				txtTin.setText("");
				txtPhone.setText("");
				txtLandline.setText("");
				//btnSTax.setSelection(false);
				try {
					populateConsignorNames();
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}

		}

		/**
		 * 
		 */
		private void handleItemChangeAction() {

			txtAreaCustomerAddress.setText("");
			txtTin.setText("");
			txtPhone.setText("");
			txtLandline.setText("");
			//btnSTax.setSelection(false);
			int index = cbCustomerName.getSelectionIndex();
			String name = null;

			if (index > -1) {
				name = cbCustomerName.getItem(index);
			} else {
				name = cbCustomerName.getText();
				if (name.trim().length() == 0)
					name = null;
			}
			try {
				if (null != name) {
					if (null != handler) {
						CustomerDTO dto = null;
						if (rdConsignee.getSelection()) {
							dto = handler.getAddressByName(name, true);
						} else {
							dto = handler.getAddressByName(name, false);
						}

						if (dto != null) {
							if (dto.getAddress() != null)
								txtAreaCustomerAddress
										.setText(dto.getAddress());
							if (dto.getTinNo() != null)
								txtTin.setText(dto.getTinNo());
							if (dto.getPhone() != null)
								txtPhone.setText(dto.getPhone());
							if (dto.getLandLine() != null)
								txtLandline.setText(dto.getLandLine());
							//if (dto.getService_stax() == 1)
								//btnSTax.setSelection(true);

						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		/**
		 * Method to delete customer Name
		 */
		private void handleDeleteAction() {
			int index = cbCustomerName.getSelectionIndex();
			String name = null;
			if (index > -1) {
				name = cbCustomerName.getItem(index);
			}
			try {
				handler.deleteCustomer(name);
				cbCustomerName.remove(index);
				txtAreaCustomerAddress.setText(EMPTY_STRING);
				txtTin.setText(EMPTY_STRING);
				txtPhone.setText(EMPTY_STRING);
				txtLandline.setText(EMPTY_STRING);
				//btnSTax.setSelection(false);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		/**
		 * Local method to handle the Submit Action
		 */
		private void handleSubmitAction() {
			int index = cbCustomerName.getSelectionIndex();
			String name = null;
			boolean isUpdate = false;
			if (index > -1) {
				name = cbCustomerName.getItem(index);
				isUpdate = true;
			} else {
				name = cbCustomerName.getText();
				if (name.trim().length() == 0)
					name = null;
			}

			try {

				if (beanutil != null) {
					stationCode = beanutil.getActingStationCode();
					if (rdConsignee.getSelection()) {
						if (null != handler) {
							handler.addConsignee(name, txtAreaCustomerAddress
									.getText(), txtTin.getText(), txtPhone
									.getText(), txtLandline.getText(), false);
							if (!isUpdate)
								cbCustomerName.add(name);
							displayError("Customer Saved Successfully");
							txtTin.setText("");
							txtPhone.setText("");
							txtLandline.setText("");
							//btnSTax.setSelection(false);
							txtAreaCustomerAddress.setText("");
							cbCustomerName.setText("");
						}
					} else {
						if (null != handler) {
							handler.addConsignor(name, txtAreaCustomerAddress
									.getText(), txtTin.getText(), txtPhone
									.getText(), txtLandline.getText(),false);
							if (!isUpdate)
								cbCustomerName.add(name);
							displayError("Customer Saved Successfully");
							txtTin.setText("");
							txtPhone.setText("");
							txtLandline.setText("");
							//btnSTax.setSelection(false);
							txtAreaCustomerAddress.setText("");
							cbCustomerName.setText("");
						}
					}
				}
			} catch (Exception e) {
				displayError("Customer Not Saved");
				e.printStackTrace();
			}
		}

	}

	public void focusLost(FocusEvent event) {

		Object source = event.getSource();

		if (cbCustomerName == source) {
			if (cbCustomerName.getSelectionIndex() == -1) {
				try {
					String name = cbCustomerName.getText();
					CustomerDTO dto = null;

					if (null != name) {

						if (rdConsignee.getSelection()) {
							dto = handler.getAddressByName(name, true);
						} else {
							dto = handler.getAddressByName(name, false);
						}

					}
					if (null != dto) {
						displayError("Name already exists, Type a new name");
						cbCustomerName.setText("");
						txtAreaCustomerAddress.setText("");
						txtTin.setText("");
						txtPhone.setText(EMPTY_STRING);
						txtLandline.setText(EMPTY_STRING);
						//btnSTax.setSelection(false);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
	}

	/**
	 * Method to display Status for Customer Composite class
	 * 
	 * @param errorText
	 */
	private void displayError(String errorText) {
		MessageBox messageBox = new MessageBox(Display.getDefault()
				.getActiveShell(), SWT.OK);
		messageBox.setMessage(errorText);
		messageBox.setText("Alert");
		messageBox.open();
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub

	}

}