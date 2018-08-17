package hm.akr.admin.sundry;

import hm.akr.admin.sundry.handler.SundryHandler;
import hm.akr.admin.workspace.AdminComposite;
import hm.akr.common.IUIConstants;
import hm.akr.common.NumericValidation;
import hm.akr.dto.DiscounterDTO;
import hm.akr.exceptions.BusinessException;
import hm.akr.msb.TreeComposite;

import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
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
public class Incrementer extends Composite implements SelectionListener,
		IUIConstants {
	
	private Composite fromTree;
	private Composite toTree;
	private Text txtIncr;
	private Button btnSetInc;
	private Button btnGo;
	private Label lblInc;
	private Text txtEditIncrement;
	private Button btnUpdate;
	private Label label1;
	private Combo cbDesc;
	private Label lblFrom;
	private Label lblTo;
	private Label lblDec;

	SundryHandler handler = null;
	private Text txtDescription;
	private Button btnDeleteProfile;
	private DiscounterDTO[] discountDTO;

	private Table tblIncr;
	private TableColumn colSno;
	private TableColumn colDesc;
	private TableColumn colValue;
	private Button btnDelete;
	private TabFolder tbfIncr;
	private String EDIT_TAB = "Edit";
	private String DELETE_TAB = "Delete";
	private String CREATE_TAB = "Create";
	private Label lblDesc;
	private Combo cbEditDesc;

	public Incrementer(Composite parent, int style) {
		super(parent, style);
		try {
			handler = new SundryHandler();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Composite loadComposite() throws Exception {
		this.setSize(800, 500);

		tbfIncr = new TabFolder(this, SWT.NONE);
		tbfIncr.setBounds(0, 0, 820, 550);
		tbfIncr.addSelectionListener(this);

		TabItem tiCreate = new TabItem(tbfIncr, SWT.NONE);
		tiCreate.setText(CREATE_TAB);
		Canvas cvsCreate = new Canvas(tbfIncr, SWT.None);
		tiCreate.setControl(cvsCreate);

		lblFrom = new Label(cvsCreate, SWT.NONE);
		lblFrom.setBounds(50, 30, 80, 22);
		lblFrom.setText("From Stations");

		lblTo = new Label(cvsCreate, SWT.NONE);
		lblTo.setBounds(320, 30, 80, 22);
		lblTo.setText("To Stations");

		fromTree = new TreeComposite(cvsCreate, SWT.NONE, 300).loadComposite();
		fromTree.setBounds(30, 50, 200, 350);

		toTree = new ToTreeComposite(cvsCreate, SWT.NONE, 300).loadComposite();
		toTree.setBounds(300, 50, 200, 350);

		lblDec = new Label(cvsCreate, SWT.NONE);
		lblDec.setBounds(530, 180, 60, 20);
		lblDec.setText("Increment");

		txtIncr = new Text(cvsCreate, SWT.BORDER);
		txtIncr.setBounds(530, 200, 60, 22);
		txtIncr.addVerifyListener(new NumericValidation());

		lblDesc = new Label(cvsCreate, SWT.NONE);
		lblDesc.setBounds(532, 243, 71, 20);
		lblDesc.setText("Description");

		txtDescription = new Text(cvsCreate, SWT.V_SCROLL | SWT.BORDER
				| SWT.WRAP);
		txtDescription.setBounds(531, 267, 130, 50);
		txtDescription.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

		});
		txtDescription.addTraverseListener(new TraverseListener() {
			public void keyTraversed(TraverseEvent e) {
				if (e.detail == SWT.TRAVERSE_TAB_NEXT
						|| e.detail == SWT.TRAVERSE_TAB_PREVIOUS) {
					e.doit = true;
				}
			}
		});

		btnSetInc = new Button(cvsCreate, SWT.None);
		btnSetInc.setBounds(538, 353, 48, 22);
		btnSetInc.setText("Set");
		btnSetInc.addSelectionListener(this);
		

		TabItem tiEdit = new TabItem(tbfIncr, SWT.NONE);
		tiEdit.setText(EDIT_TAB);
		Canvas cvsEdit = new Canvas(tbfIncr, SWT.None);
		tiEdit.setControl(cvsEdit);

		{
			cbEditDesc = new Combo(cvsEdit, SWT.READ_ONLY);
			cbEditDesc.setBounds(148, 32, 217, 21);
			cbEditDesc.addSelectionListener(this);
		}
		{
			label1 = new Label(cvsEdit, SWT.NONE);
			label1.setText("Description");
			label1.setBounds(84, 32, 60, 21);
		}
		
		{
			txtEditIncrement = new Text(cvsEdit, SWT.BORDER);
			txtEditIncrement.setBounds(149, 71, 156, 23);
			txtEditIncrement.addVerifyListener(new NumericValidation());
		}
		{
			lblInc = new Label(cvsEdit, SWT.NONE);
			lblInc.setText("Increment");
			lblInc.setBounds(82, 71, 63, 31);
		}
		{
			btnUpdate = new Button(cvsEdit, SWT.PUSH | SWT.CENTER);
			btnUpdate.setText("Update");
			btnUpdate.setBounds(313, 71, 55, 23);
			btnUpdate.addSelectionListener(this);
		}
		

		TabItem tiDelete = new TabItem(tbfIncr, SWT.NONE);
		tiDelete.setText(DELETE_TAB);
		Canvas cvsDelete = new Canvas(tbfIncr, SWT.None);
		tiDelete.setControl(cvsDelete);

		tblIncr = new Table(cvsDelete, SWT.CHECK | SWT.MULTI | SWT.BORDER
				| SWT.FULL_SELECTION);
		tblIncr.setLinesVisible(true);
		tblIncr.setHeaderVisible(true);
		tblIncr.setBounds(82, 70, 338, 360);
		tblIncr.addSelectionListener(this);

		{
			colSno = new TableColumn(tblIncr, SWT.NONE);
			colSno.setText("S No");
			colSno.setWidth(50);
		}

		{
			colDesc = new TableColumn(tblIncr, SWT.NONE);
			colDesc.setText("From Station");
			colDesc.setWidth(136);
		}
		{
			colValue = new TableColumn(tblIncr, SWT.NONE);
			colValue.setText("To Station");
			colValue.setWidth(116);
		}

		btnDelete = new Button(cvsDelete, SWT.NONE);
		btnDelete.setBounds(350, 450, 65, 23);
		btnDelete.setText("Delete");
		{
			cbDesc = new Combo(cvsDelete, SWT.READ_ONLY);
			cbDesc.setBounds(148, 32, 140, 21);
			cbDesc.addSelectionListener(this);
		}
		{
			label1 = new Label(cvsDelete, SWT.NONE);
			label1.setText("Description");
			label1.setBounds(84, 32, 60, 21);
		}
		{
			btnGo = new Button(cvsDelete, SWT.PUSH | SWT.CENTER);
			btnGo.setText("Go");
			btnGo.setBounds(290, 30, 45, 23);
			btnGo.addSelectionListener(this);
		}
		{
			btnDeleteProfile = new Button(cvsDelete, SWT.PUSH | SWT.CENTER);
			btnDeleteProfile.setText("Delete Profile");
			btnDeleteProfile.setBounds(340, 30, 75, 23);
			btnDeleteProfile.addSelectionListener(this);
		}

		btnDelete.addSelectionListener(this);
		
		

		discountDTO = handler.getIncrementer();

		return this;
	}

	private void populateDescCombo(DiscounterDTO[] incr, Combo cbDesc) {
		if (tblIncr != null)
			tblIncr.removeAll();

		int len = incr.length;

		if(cbDesc != null)
			cbDesc.removeAll();
		
		for (int i = 0; i < len; i++) {			
			cbDesc.add(incr[i].getDescription());
		}
		
		String[] items = cbDesc.getItems();
		cbDesc.removeAll();
		Arrays.sort(items, String.CASE_INSENSITIVE_ORDER);
		cbDesc.setItems(items);

	}
		

	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void widgetSelected(SelectionEvent event) {
		Object source = event.getSource();

		String selectedTab = tbfIncr.getSelection()[0].getText();

		if (source == tbfIncr) {
			AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			if (selectedTab.equals(DELETE_TAB)) {
				try {	
					if (discountDTO != null)
						populateDescCombo(discountDTO, cbDesc);
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else if (selectedTab.equals(EDIT_TAB)) {
				try {
					txtEditIncrement.setText("");
					if (discountDTO != null)
						populateDescCombo(discountDTO, cbEditDesc);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else if (source == tblIncr) {
			AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
		} else if (source == btnSetInc) {
			String[] fromStations = null;
			String[] toStations = null;
			int fromLen = 0;
			int toLen = 0;

			ArrayList<DiscounterDTO> list = new ArrayList<DiscounterDTO>();

			try {
				fromStations = ((TreeComposite) fromTree)
						.getSelectedMSBStation();

				toStations = ((ToTreeComposite) toTree).getSelectedMSBStation();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			if (isValidate(fromStations, toStations) && isAvailDesc()) {
				if (fromStations != null && toStations != null
						&& !txtIncr.getText().equals("")) {
					fromLen = fromStations.length;
					toLen = toStations.length;
					// System.out.println("from-->"+fromLen);
					// System.out.println("to-->"+toLen);

					for (int i = 0; i < fromLen; i++) {
						for (int j = 0; j < toLen; j++) {
							DiscounterDTO dto = new DiscounterDTO();
							dto.setFromStation(fromStations[i]);
							dto.setToStation(toStations[j]);
							dto.setIncrement(Integer.parseInt(txtIncr.getText()));
							dto.setDescription(txtDescription.getText());
							list.add(dto);
						}
					}

					int size = list.size();
					if (size > 0) {
						DiscounterDTO[] dtos = list
								.toArray(new DiscounterDTO[size]);

						try {
							handler.setIncrementer(dtos);
							((TreeComposite) fromTree).clearTree();
							((ToTreeComposite) toTree).clearTree();
							txtIncr.setText("");
							txtDescription.setText("");
							AdminComposite.display(
									"Incrementer value set successfully",
									STATUS_SUCCESS, SUCCESS_FONT_COLOR);

							// Refresh
							try {
								// System.out.println("sdfsadfsd");
								discountDTO = handler.getIncrementer();
							} catch (Exception e) {
								e.printStackTrace();
							}
						} catch (BusinessException bus) {
							String msg = bus.getResponseMessage();
							int index = msg.indexOf("for");
							// System.out.println(msg.substring(0, index));
							AdminComposite.display(msg.substring(0, index),
									STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						} catch (Exception e) {
							AdminComposite.display(
									"Incrementer value cant be set",
									STATUS_SUCCESS, SUCCESS_FONT_COLOR);
							// e.printStackTrace();
						}
					}

				}

			}

		} else if (source == btnDelete) {
			TableItem[] items = tblIncr.getItems();
			int len = 0;
			String from = null;
			String to = null;
			boolean isDeleted = false;
			boolean isChecked = false;
			// ArrayList<String> list = new ArrayList<String>();
			if (items != null)
				len = items.length;
			for (int i = 0; i < len; i++) {

				if (items[i].getChecked()) {

					isChecked = true;

					from = items[i].getText(1);
					to = items[i].getText(2);

					
					try {
						handler.deleteIncrementer(from, to);
						items[i].dispose();
						isDeleted = true;
						// AdminComposite.display("Discounter value cant be
						// set", STATUS_SUCCESS,SUCCESS_FONT_COLOR);

					} catch (Exception e) {
						isDeleted = false;
						// AdminComposite.display("Discounter value cant be
						// deleted", STATUS_SUCCESS,SUCCESS_FONT_COLOR);
						// e.printStackTrace();
					}

				}

			}

			if (isChecked) {
				if (isDeleted) {
					AdminComposite.display(
							"Incrementer value deleted successfully",
							STATUS_SUCCESS, SUCCESS_FONT_COLOR);

					TableItem[] itm = tblIncr.getItems();
					for(int k = 0; k < tblIncr.getItemCount(); k++){
						itm[k].setText(0,String.valueOf(k+1));
					}
				
				} else {
					AdminComposite.display("Incrementer value cant be delete",
							STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				}

				try {										
					int length = tblIncr.getItemCount();
					if(length == 0){
						discountDTO = handler.getIncrementer();
						populateDescCombo(discountDTO, cbDesc);						
					}					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}else if (source == btnDeleteProfile) {
			try{
				String desc = cbDesc.getText();
				handler.deleteAllIncrementer(desc);
				AdminComposite.display(
						"Incrementer value deleted successfully",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);

				discountDTO = handler.getIncrementer();
				populateDescCombo(discountDTO, cbDesc);	
				
			}catch(Exception e){
				AdminComposite.display("Incrementer value cant be delete",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				e.printStackTrace();
			}
		}else if(source == btnGo){
			try{
				String desc = cbDesc.getText();
				DiscounterDTO[] search = handler.searchIncrementer(desc);
				if(search != null){
					if(tblIncr != null)
						tblIncr.removeAll();
					
					populateFromToTable(search);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(source == cbEditDesc){
			String desc = cbEditDesc.getText();
			if(desc != null && !desc.equals("")){
				if(discountDTO != null){
					for(int i = 0; i < discountDTO.length; i++){
						if(discountDTO[i].getDescription().equalsIgnoreCase(desc)){
							txtEditIncrement.setText(String.valueOf(discountDTO[i].getIncrement()));							
							break;
						}
					}
				}
				
			}
		}else if(btnUpdate == source){
			int inc = 0;
			if(cbEditDesc != null && !cbEditDesc.getText().equals("") && !txtEditIncrement.getText().equals("")){
				try{
					inc = Integer.parseInt(txtEditIncrement.getText());
					handler.updateIncrementer(inc, cbEditDesc.getText());
					AdminComposite.display(	"Incrementer value updated successfully",
							STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					
					discountDTO = handler.getIncrementer();
					
				}catch(Exception e){
					AdminComposite.display(	"Incrementer value updation failed",
							STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					e.printStackTrace();
				}
			}
		}

	}

	private void populateFromToTable(DiscounterDTO[] search) {
		int len = search.length;
		for (int i = 0; i < len; i++) {
			TableItem item = new TableItem(tblIncr, SWT.NONE);
			item.setText(0, String.valueOf(i + 1));
			item.setText(1, search[i].getFromStation());
			item.setText(2, search[i].getToStation());
		}
	}

	private boolean isAvailDesc() {
		if (discountDTO != null) {
			int len = discountDTO.length;

			for (int i = 0; i < len; i++) {
				if (discountDTO[i].getDescription().equalsIgnoreCase(
						txtDescription.getText())) {
					AdminComposite.display(
							"Description already exists. Give another name",
							STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					return false;
				}
			}
		}
		return true;
	}

	private boolean isValidate(String[] fromStations, String[] toStations) {

		if (fromStations == null || fromStations.length == 0) {
			AdminComposite.display("Please select From station",
					STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;
		} else if (toStations == null || toStations.length == 0) {
			AdminComposite.display("Please select To station", STATUS_SUCCESS,
					SUCCESS_FONT_COLOR);
			return false;
		}
		if (txtIncr.getText().equals("")) {
			AdminComposite.display("Please enter increment value",
					STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;
		}
		if (txtDescription.getText().equals("")) {
			AdminComposite.display("Please enter description", STATUS_SUCCESS,
					SUCCESS_FONT_COLOR);
			return false;
		}

		return true;
	}
}
