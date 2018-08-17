package hm.akr.admin.commission;

import hm.akr.admin.commission.handler.AgencyCommisionHandler;
import hm.akr.admin.workspace.AdminComposite;
import hm.akr.common.BeanUtil;
import hm.akr.common.FloatLimitValidation;
import hm.akr.common.IUIConstants;
import hm.akr.common.NumericExtendedValidation;
import hm.akr.common.NumericValidation;
import hm.akr.dto.StationsDTO;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Properties;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
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
public class ManageCommissionComposite extends Composite implements IUIConstants {

	Properties properties = null;

	private Table tblManageSundry;

	private TableColumn column;

	private TableItem item;

	private Combo cbBranch = null;

	private Label label1;

	private Canvas canvas2;

	AgencyCommisionHandler handler = null;

	private String[] column_head = { "Station", "Booking", "Delivery", "CC Limit", "CC Consider", "CC refund"};

	private TableEditor editor;

	private CCombo[] cbBooking;

	private Text[] txtDelivery;

	private Text[] txtCCConsider = null;

	private Text[] txtCCrefund;	

	private Button btnUpdate = null;

	private DecimalFormat decimalFormat;

	private Text[] txtCCLimit;

	/**
	 * 
	 * @param shell
	 * @param swtValue
	 * @throws Exception
	 */
	public ManageCommissionComposite(Composite composite, int swtValue)
			throws Exception {
		super(composite, swtValue);

		try {

			handler = new AgencyCommisionHandler();

		} catch (Exception e) {

			throw e;
		}

	}

	/**
	 * 
	 * @return
	 */
	public Composite loadComposite() {
		try {
			{
				this.setSize(450, 400);

			}
			{
				canvas2 = new Canvas(this, SWT.NONE);
				canvas2.setBounds(10, 0, 800, 583);
				/*Label lblHead = new Label(canvas2, SWT.NONE);
				lblHead.setText("Manage Commission");
				lblHead.setBounds(625, 10, 150, 30);
				lblHead.setFont(HEAD_FONT);*/
				// lblHead.setForeground(HEAD_FONT_COLOR);

				{
					label1 = new Label(canvas2, SWT.NONE);
					label1.setText("Select Branch");
					label1.setBounds(10, 15, 75, 15);
					label1.setFont(LABLE_FONT);
				}			

				{
					cbBranch = new Combo(canvas2, SWT.DROP_DOWN | SWT.READ_ONLY);
					cbBranch.setBounds(85, 13, 233, 10);
					cbBranch.setFont(COMBO_FONT);
					cbBranch.addKeyListener(new KeyListener() {

						@Override
						public void keyPressed(KeyEvent e) {

							String selectedText = cbBranch.getText();
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

								cbBranch.remove(0, cbBranch.getItemCount() - 1);
								populateBranches();
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

								String[] items = cbBranch.getItems();
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
									cbBranch.remove(0, len - 1);

									for (int i = startIndex; i < len; i++) {
										String temp = items[i].toLowerCase();
										if (temp.contains(selectedText)) {
											cbBranch.add(items[i]);
										}
									}
									if (selectedText.length() == 1) {
										showPopup(cbBranch, true);
									}
								}
							}
						}

						@Override
						public void keyReleased(KeyEvent e) {
							String temp = cbBranch.getText();
							int len = temp.length();
							if (len == 0) {
								cbBranch.remove(0, cbBranch.getItemCount() - 1);
								populateBranches();
							}
						}
					});
					cbBranch.addSelectionListener(new SundryActionListener());
					populateBranches();
				}
				{
					String value = cbBranch.getItem(0);
					cbBranch.setText(value);
					int index = value.indexOf(" - ");
					String branchCode = value.substring(index + 3);

					handleItemChangeAction(tblManageSundry, branchCode);
					
					StationsDTO[] stations = getManageCommission();

					populateManageCommission(stations);

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;

	}

	/**
	 * 
	 * @param dto
	 */
	private void populateManageCommission(StationsDTO[] stationdto) {		
		
		String[] profiles = getComboProfiles();		
		decimalFormat = new DecimalFormat("0.00");
		TableItem[] items = tblManageSundry.getItems();
		int size = items.length;
		int refund = 0;
		int consider = 0;
		int limit = 0;
		for (int i = 0; i < size; i++) {
			String scode = items[i].getText(0);
			int index = scode.indexOf("-");
			scode = scode.substring(index + 1).trim();
			
			if (null != stationdto) {
				int length = stationdto.length;
				for (int j = 0; j < length; j++) {
					String code = stationdto[j].getId();
					if (scode.equalsIgnoreCase(code)) {
						if(profiles != null)
							cbBooking[i].setItems(profiles);
						
						cbBooking[i].setText(stationdto[j].getProfileName());
						
						txtDelivery[i].setText(decimalFormat.format(getRoundedValue(stationdto[j].getDeliveryCommission())));
						
						limit = stationdto[j].getCc_limit();				
						if(limit == -1){
							txtCCLimit[i].setText("All");
						}else{
							txtCCLimit[i].setText(String.valueOf(limit));
						}		
						
						consider = stationdto[j].getCc_consider();		
						if(consider == 100){
							txtCCConsider[i].setText("All");
						}else{
							txtCCConsider[i].setText(String.valueOf(consider));
						}	
									
						refund = stationdto[j].getCc_refund();
						txtCCrefund[i].setText(String.valueOf(refund));

						break;

					}
				}
			}
		}

	}

	

	private float getRoundedValue(float company) {
		company = company * 100;
		company = Math.round(company);
		company = company / 100;	
		
		return company;
	}

	private String[] getComboProfiles() {
		String[] names = null;
		if (null != handler) {			
			try {
				names = handler.getProfileNames();				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return names;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	private StationsDTO[] getManageCommission() throws Exception {
		StationsDTO[] dto = null;
		if (null != handler) {
			dto = handler.getManageCommission();
		}

		return dto;
	}

	private void populateBranches() {
		try {
			StationsDTO[] stations = handler.getAllBranches();
			if (null != stations) {
				int len = stations.length;
				for (int i = 0; i < len; i++) {
					cbBranch.add(stations[i].getName() + " - "
							+ stations[i].getId());
				}
			}
		} catch (Exception exception) {

		}
	}

	private static void showPopup(Combo combo, boolean show) {
		if (!combo.isDisposed()) {
			OS.SendMessage(combo.handle, OS.CB_SHOWDROPDOWN, show ? 1 : 0, 0);
		}
	}

	private void createTable(int columns, int rows, String[] column_name,
			Object[] row_data) {

		int initial = rows;

		tblManageSundry = new Table(canvas2, SWT.MULTI | SWT.BORDER
				| SWT.FULL_SELECTION);
		tblManageSundry.setLinesVisible(true);
		tblManageSundry.setHeaderVisible(true);

		for (int i = 0; i < columns; i++) {
			column = new TableColumn(tblManageSundry, SWT.NONE);

			if (i == 0) {
				column.setText(column_name[i]);
				column.setWidth(190);
			} else if (i == 1 ) {
				column.setText(column_name[i]);
				column.setWidth(120);
			} else {
				column.setText(column_name[i]);
				column.setWidth(80);
			}
		}

		// Drawing initial table items

		cbBooking = new CCombo[rows];
		txtDelivery = new Text[rows];
		txtCCConsider = new Text[rows];
		txtCCrefund = new Text[rows];
		txtCCLimit = new Text[rows];
		
		for (int rowId = 0; rowId < rows; rowId++) {

			item = new TableItem(tblManageSundry, SWT.NONE);
			String name = ((StationsDTO) row_data[rowId]).getName();
			String code = ((StationsDTO) row_data[rowId]).getId();
			// First column
			item.setText(0, name + " - " + code);

			// Draw Text Field
			editor = new TableEditor(tblManageSundry);

			cbBooking[rowId] = new CCombo(tblManageSundry, SWT.READ_ONLY);
			cbBooking[rowId].setFont(TEXT_FONT);		
			editor.grabHorizontal = true;
			editor.setEditor(cbBooking[rowId], item, 1);

			// Draw Text Field
			editor = new TableEditor(tblManageSundry);

			txtDelivery[rowId] = new Text(tblManageSundry, SWT.NONE);
			txtDelivery[rowId].setFont(TEXT_FONT);
			txtDelivery[rowId].addVerifyListener(new FloatLimitValidation());
			final int index = rowId;
			// For double click and edit values
			txtDelivery[rowId].addMouseListener(new MouseListener(){

				@Override
				public void mouseDoubleClick(MouseEvent event) {					
					//txtDelivery[index].removeVerifyListener(new FloatLimitValidation());
					txtDelivery[index].setMessage("DC");					
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
			
			txtDelivery[rowId].addKeyListener(new KeyListener(){

				@Override
				public void keyPressed(KeyEvent arg0) {
					if(txtDelivery[index].getMessage().equals("DC")){						
							txtDelivery[index].setText("");
							txtDelivery[index].setMessage("");
					}				
					
				}

				@Override
				public void keyReleased(KeyEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
			});
			
			txtDelivery[rowId].addFocusListener(new FocusListener(){

				@Override
				public void focusGained(FocusEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void focusLost(FocusEvent arg0) {
					txtDelivery[index].setMessage("");
					
				}
				
			});
			
			editor.grabHorizontal = true;
			editor.setEditor(txtDelivery[rowId], item, 2);

			
			// Draw CC Limit Text Field
			editor = new TableEditor(tblManageSundry);

			txtCCLimit[rowId] = new Text(tblManageSundry, SWT.NONE);
			txtCCLimit[rowId].setFont(TEXT_FONT);
			txtCCLimit[rowId].addVerifyListener(new NumericExtendedValidation());	
			txtCCLimit[rowId].addKeyListener(new KeyListener() {

				@Override
				public void keyPressed(KeyEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void keyReleased(KeyEvent event) {
					if (event.character == 'a'
							|| event.character == 'A') {
						txtCCLimit[index].setText("All");
					}

				}

			});
			
			txtCCLimit[rowId].addFocusListener(new FocusListener(){

				@Override
				public void focusGained(FocusEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void focusLost(FocusEvent arg0) {
					if(!txtCCLimit[index].getText().equals("") && !txtCCLimit[index].getText().equalsIgnoreCase("All")){
						int limit = Integer.parseInt(txtCCLimit[index].getText());
						int consider = 0;
						if(!txtCCConsider[index].getText().equals(""))
							consider = Integer.parseInt(txtCCConsider[index].getText());
						
						if(limit < consider){
							AdminComposite.display("CC Consider should be lesser than CC Limit", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
							txtCCLimit[index].setText("");
						}
						
					}
					
				}
				
			});			
			editor.grabHorizontal = true;
			editor.setEditor(txtCCLimit[rowId], item, 3);
			
			
			
			// Draw CC consider Text Field
			editor = new TableEditor(tblManageSundry);

			txtCCConsider[rowId] = new Text(tblManageSundry, SWT.NONE);
			txtCCConsider[rowId].setFont(TEXT_FONT);
			txtCCConsider[rowId].addVerifyListener(new NumericExtendedValidation());	
			txtCCConsider[rowId].addKeyListener(new KeyListener() {

				@Override
				public void keyPressed(KeyEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void keyReleased(KeyEvent event) {
					if (event.character == 'a'
							|| event.character == 'A') {
						txtCCConsider[index].setText("All");
					}

				}

			});		
			txtCCConsider[rowId].addFocusListener(new FocusListener(){

				@Override
				public void focusGained(FocusEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void focusLost(FocusEvent arg0) {
					if(!txtCCConsider[index].getText().equals("")){
						
						int limit = 0;						
						int consider = 0;
						if(!txtCCLimit[index].getText().equals(""))
							limit = Integer.parseInt(txtCCLimit[index].getText());
						if(txtCCConsider[index].getText().equalsIgnoreCase("All")){
							consider = 100;
						}else{
							consider = Integer.parseInt(txtCCConsider[index].getText()); 
						}
							
						
						if(limit < consider){
							AdminComposite.display("CC Consider should be lesser than CC Limit", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
							txtCCConsider[index].setText("");
						}
					}
					
				}
				
			});
			
			editor.grabHorizontal = true;
			editor.setEditor(txtCCConsider[rowId], item, 4);

			// Draw CC refund Text Field
			editor = new TableEditor(tblManageSundry);

			txtCCrefund[rowId] = new Text(tblManageSundry, SWT.NONE);
			txtCCrefund[rowId].setFont(TEXT_FONT);
			txtCCrefund[rowId].addVerifyListener(new NumericValidation());
			
			
			editor.grabHorizontal = true;
			editor.setEditor(txtCCrefund[rowId], item, 5);

		}

		columns = columns * column.getWidth();
		columns += 205;
		int item_height = tblManageSundry.getItemHeight();
		rows = (rows * item_height) + 23;

		if (rows < 430 && initial > 10) {
			tblManageSundry.setBounds(10, 45, 640, rows + 12);
		} else if (rows > 430 && initial > 10) {
			tblManageSundry.setBounds(10, 45, 660, 430);
		} else {
			tblManageSundry.setBounds(10, 45, 640, rows + 12);
		}

		if (btnUpdate != null) {
			btnUpdate.dispose();
		}
		Rectangle rt = tblManageSundry.getBounds();
		btnUpdate = new Button(canvas2, SWT.PUSH);
		btnUpdate.setText("Update");
		btnUpdate.setBounds(rt.width - 40, rt.height + 47, 54, 24);
		btnUpdate.addSelectionListener(new SundryActionListener());
	}

	/**
	 * 
	 * @param reftable
	 * @param branch
	 */
	private void handleItemChangeAction(Table reftable, String branch) {

		int index = cbBranch.getSelectionIndex();
		String name = null;

		if (index > -1) {
			name = cbBranch.getItem(index);

		} else {
			name = cbBranch.getText();
			if (name.trim().length() == 0)
				name = null;
		}
		try {
			if (null != name) {
				if (null != reftable)
					reftable.dispose();
				StationsDTO rowDTO[] = null;
				rowDTO = handler.getStations(branch);
				int rows = rowDTO.length;
				Object[] rowData = rowDTO;
				createTable(6, rows, column_head, rowData);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public class SundryActionListener implements SelectionListener {

		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void widgetSelected(SelectionEvent event) {
			// TODO Auto-generated method stub
			Object source = event.getSource();

			if (cbBranch == source) {
				try {
					String value = cbBranch.getText();
					int index = value.indexOf(" - ");
					String branchCode = value.substring(index + 3);
					handleItemChangeAction(tblManageSundry, branchCode);
					
					StationsDTO[] dto = getManageCommission();

					populateManageCommission(dto);

				} catch (Exception exception) {
					exception.printStackTrace();
				}
			} else if (btnUpdate == source) {
				// Fuctionalities for Update Commission
				try {
					if(isValidCCValues()){
					updateManageCommission();
					AdminComposite.display("Updation Successful",
							STATUS_SUCCESS, SUCCESS_FONT_COLOR);

					BeanUtil.isBPI_Changed = true;
					}
				} catch (Exception e) {
					AdminComposite.display("Updation Failed", STATUS_SUCCESS,
							SUCCESS_FONT_COLOR);
					e.printStackTrace();
				}
			}
		}

		private boolean isValidCCValues() {
			TableItem[] items = tblManageSundry.getItems();
			int size = items.length;
			for (int i = 0; i < size; i++) {
				if (txtCCLimit[i].getText().equals("")) {	
					AdminComposite.display("Please enter CC Limit", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					return false;
				}else if(txtCCConsider[i].getText().equals("")) {	
					AdminComposite.display("Please enter CC Consider", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					return false;
				}
			}
			return true;
		}

		/**
		 * 
		 * @throws Exception
		 */
		private void updateManageCommission() throws Exception {
			String EMPTY_STRING = "";
			TableItem[] items = tblManageSundry.getItems();
			StationsDTO[] stationdto = null;
			
			StationsDTO sdto = null;
			
			ArrayList<StationsDTO> slist = new ArrayList<StationsDTO>();
			

			int size = items.length;

			for (int i = 0; i < size; i++) {

				sdto = new StationsDTO();
			
				String scode = items[i].getText(0);
				int index = scode.indexOf("-");
				scode = scode.substring(index + 2).trim();
				
				String booking = EMPTY_STRING;
				float delivery = 0;
				int consider = 0;
				int refund = 0;
				int limit = 0;
				
				if (!cbBooking[i].getText().equals(EMPTY_STRING)) {
					booking = cbBooking[i].getText();
				}
				if (!txtDelivery[i].getText().equals(EMPTY_STRING)) {
					delivery = Float.parseFloat(txtDelivery[i].getText());
				}
				
				if (!txtCCLimit[i].getText().equals(EMPTY_STRING)) {
					if (txtCCLimit[i].getText().equalsIgnoreCase("All")) {
						limit = -1;
					} else {
						limit = Integer.parseInt(txtCCLimit[i].getText());
					}					
				}
				
				if (!txtCCConsider[i].getText().equals(EMPTY_STRING)) {					
					if (txtCCConsider[i].getText().equalsIgnoreCase("All")) {
						consider = 100;
					} else {
						consider = Integer.parseInt(txtCCConsider[i].getText());
					}
				}
				
				if (!txtCCrefund[i].getText().equals(EMPTY_STRING)) {
					refund = Integer.parseInt(txtCCrefund[i].getText());
				}
							
				sdto.setId(scode);
				sdto.setProfileName(booking);
				sdto.setDeliveryCommission(delivery);
				sdto.setCc_limit(limit);
				sdto.setCc_consider(consider);
				sdto.setCc_refund(refund);
				slist.add(sdto);

			}

			int length = slist.size();

			if (length > 0) {
				stationdto = slist.toArray(new StationsDTO[size]);
				if (null != stationdto) {					
					if (null != handler) {
						handler.updateManageCommission(stationdto);
					}
				}
			}			

		}
	}

}