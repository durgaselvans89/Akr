package hm.akr.admin.sundry;

import java.util.ArrayList;

import hm.akr.admin.sundry.handler.SundryHandler;
import hm.akr.admin.workspace.AdminComposite;
import hm.akr.common.IUIConstants;
import hm.akr.common.NumericValidation;
import hm.akr.dto.DiscounterDTO;
import hm.akr.exceptions.BusinessException;
import hm.akr.msb.TreeComposite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
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
public class Discounter extends Composite implements IUIConstants, SelectionListener {

	private Composite fromTree;
	private Composite toTree;
	private Text txtDiscount;
	private Button btnSetDec;
	private Label lblFrom;
	private Label lblTo;
	private Label lblDec;

	SundryHandler handler = null;
	private Text txtDescription;
	private DiscounterDTO[] discountDTO;
	
	private Table tblDiscount;
	private TableColumn colSno;
	private TableColumn colDesc;
	private TableColumn colValue;
	private Button btnDelete;
	private TabFolder tbfDiscount;
	
	private String DELETE_TAB = "Delete";
	private String CREATE_TAB = "Create";
	private Label lblDesc;
	
	public Discounter(Composite parent, int style) {
		super(parent, style);
		try {
			handler = new SundryHandler();
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
	}
	
	public Composite loadComposite() throws Exception{
		this.setSize(800, 500);
		
		tbfDiscount = new TabFolder(this, SWT.NONE);
		tbfDiscount.setBounds(0,0,820,550);
		tbfDiscount.addSelectionListener(this);
		
		TabItem tiCreate = new TabItem(tbfDiscount, SWT.NONE);
		tiCreate.setText(CREATE_TAB);		
		Canvas cvsCreate = new Canvas(tbfDiscount, SWT.None);
		tiCreate.setControl(cvsCreate);
		
		lblFrom = new Label(cvsCreate, SWT.NONE);
		lblFrom.setBounds(50, 30, 80, 22);
		lblFrom.setText("From Stations");
		
		lblTo = new Label(cvsCreate, SWT.NONE);
		lblTo.setBounds(320, 30, 80, 22);
		lblTo.setText("To Stations");
		
		fromTree = new TreeComposite(cvsCreate, SWT.NONE, 300).loadComposite();
		fromTree.setBounds(30,50,200,350);
		
		toTree = new ToTreeComposite(cvsCreate, SWT.NONE, 300).loadComposite();
		toTree.setBounds(300,50,200,350);			
		
		lblDec = new Label(cvsCreate, SWT.NONE);
		lblDec.setBounds(530, 180, 60, 20);
		lblDec.setText("Discount");
		
		txtDiscount = new Text(cvsCreate, SWT.BORDER);
		txtDiscount.setBounds(530,200,60,22);
		txtDiscount.addVerifyListener(new NumericValidation());		
		

		lblDesc = new Label(cvsCreate, SWT.NONE);
		lblDesc.setBounds(532, 243, 71, 20);
		lblDesc.setText("Description");
		
		txtDescription = new Text(cvsCreate, SWT.V_SCROLL | SWT.BORDER
				| SWT.WRAP);
		txtDescription.setBounds(531, 267, 130, 50);
		txtDescription.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				AdminComposite.display("", STATUS_SUCCESS,SUCCESS_FONT_COLOR);
				
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

		
		btnSetDec = new Button(cvsCreate, SWT.None);
		btnSetDec.setBounds(538, 353, 48, 22);
		btnSetDec.setText("Set");
		btnSetDec.addSelectionListener(this);
		
		
		TabItem tiDelete = new TabItem(tbfDiscount, SWT.NONE);
		tiDelete.setText(DELETE_TAB);
		Canvas cvsDelete = new Canvas(tbfDiscount, SWT.None);
		tiDelete.setControl(cvsDelete);
		
		
		tblDiscount = new Table(cvsDelete, SWT.CHECK | SWT.MULTI | SWT.BORDER
				| SWT.FULL_SELECTION);
		tblDiscount.setLinesVisible(true);
		tblDiscount.setHeaderVisible(true);
		tblDiscount.setBounds(80, 10, 338, 450);
		tblDiscount.addSelectionListener(this);
		
		{
			colSno = new TableColumn(tblDiscount, SWT.NONE);
			colSno.setText("S No");
			colSno.setWidth(50);
		}
		
		{
			colDesc = new TableColumn(tblDiscount, SWT.NONE);
			colDesc.setText("Description");
			colDesc.setWidth(200);
		}	
		{
			colValue = new TableColumn(tblDiscount, SWT.NONE);
			colValue.setText("Discount");
			colValue.setWidth(60);
		}	
		
		btnDelete = new Button(cvsDelete, SWT.NONE);
		btnDelete.setBounds(350,475,65,23);
		btnDelete.setText("Delete");
		btnDelete.addSelectionListener(this);
		
		discountDTO = handler.getDiscounter();	
		
		
		return this;
	}
	

	private void populateDiscounter(DiscounterDTO[] discount) {
		if(tblDiscount != null)
			tblDiscount.removeAll();
		
		int len = discount.length;
		
		for(int i = 0; i < len; i++){
			TableItem item = new TableItem(tblDiscount, SWT.NONE);
			item.setText(0, String.valueOf(i+1));
			item.setText(1, discount[i].getDescription());
			item.setText(2, String.valueOf(discount[i].getDiscount()));
		}
		
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void widgetSelected(SelectionEvent event) {
		Object source = event.getSource();
		
		String selectedTab = tbfDiscount.getSelection()[0].getText();
		
		if(source == tbfDiscount){
			AdminComposite.display("", STATUS_SUCCESS,SUCCESS_FONT_COLOR);
			if(selectedTab.equals(DELETE_TAB)){
				try {
					//discountDTO = handler.getDiscounter();
					if(discountDTO != null)
						populateDiscounter(discountDTO);
				} catch (Exception e) {					
					e.printStackTrace();
				}
				
			}else if(selectedTab.equals(CREATE_TAB)){
				try {
					//discountDTO = handler.getDiscounter();				
				} catch (Exception e) {					
					e.printStackTrace();
				}
			}
		}else if (source == tblDiscount){
			AdminComposite.display("", STATUS_SUCCESS,SUCCESS_FONT_COLOR);
		}else if(source == btnSetDec){			
			String[] fromStations = null;
			String[] toStations = null;
			int fromLen = 0; 
			int toLen = 0;
			
			ArrayList<DiscounterDTO> list = new ArrayList<DiscounterDTO>();
			
			try {
				fromStations = ((TreeComposite) fromTree).getSelectedMSBStation();
										
				toStations = ((ToTreeComposite) toTree).getSelectedMSBStation();
			} catch (Exception e1) {				
				e1.printStackTrace();
			}
			if(isValidate(fromStations, toStations) && isAvailDesc()){
			if(fromStations != null && toStations != null && !txtDiscount.getText().equals("")){
				fromLen = fromStations.length;
				toLen = toStations.length;
				//System.out.println("from-->"+fromLen);
				//System.out.println("to-->"+toLen);
				
				for(int i = 0; i < fromLen; i++){
					for(int j = 0; j < toLen; j++){
						DiscounterDTO dto = new DiscounterDTO();
						dto.setFromStation(fromStations[i]);
						dto.setToStation(toStations[j]);
						dto.setDiscount(Integer.parseInt(txtDiscount.getText()));
						dto.setDescription(txtDescription.getText());
						list.add(dto);
					}
				}
				
				int size = list.size();
				if(size > 0){
					DiscounterDTO[] dtos = list.toArray(new DiscounterDTO[size]);
					
					try {
						handler.setDiscounter(dtos);
						((TreeComposite) fromTree).clearTree();
						((ToTreeComposite) toTree).clearTree();
						txtDiscount.setText("");
						txtDescription.setText("");
						AdminComposite.display("Discounter value set successfully", STATUS_SUCCESS,SUCCESS_FONT_COLOR);
						
						//Refresh
						try {
							//System.out.println("sdfsadfsd");
							discountDTO = handler.getDiscounter();						
						} catch (Exception e) {					
							e.printStackTrace();
						}
					}catch(BusinessException bus){
						String msg = bus.getResponseMessage();
						int index = msg.indexOf("for");
						//System.out.println(msg.substring(0, index));
						AdminComposite.display(msg.substring(0, index), STATUS_SUCCESS,SUCCESS_FONT_COLOR);
					} catch (Exception e) {						
						AdminComposite.display("Discounter value cant be set", STATUS_SUCCESS,SUCCESS_FONT_COLOR);
						//e.printStackTrace();
					}
				}
				
			}
			
		}		
		
	} else if (source == btnDelete) {
			TableItem[] items = tblDiscount.getItems();
			int len = 0;
			String desc = null;
			boolean isDeleted = false;
			boolean isChecked = false;
		//	ArrayList<String> list = new ArrayList<String>();
			if (items != null)
				len = items.length;
			for (int i = 0; i < len; i++) {

				if (items[i].getChecked()) {

					isChecked = true;
					
					desc = items[i].getText(1);

					try {
						handler.deleteDiscounter(desc);
						items[i].dispose();						
						isDeleted = true;
						//AdminComposite.display("Discounter value cant be set", STATUS_SUCCESS,SUCCESS_FONT_COLOR);

					} catch (Exception e) {
						isDeleted = false;
						//AdminComposite.display("Discounter value cant be deleted", STATUS_SUCCESS,SUCCESS_FONT_COLOR);
						//e.printStackTrace();
					}

				}

			}
			
			if(isChecked){
			if(isDeleted){
				AdminComposite.display("Discounter value deleted successfully", STATUS_SUCCESS,SUCCESS_FONT_COLOR);
			}else{
				AdminComposite.display("Discounter value cant be delete", STATUS_SUCCESS,SUCCESS_FONT_COLOR);
			}
			
			try {
				discountDTO = handler.getDiscounter();
				if(discountDTO != null){
					populateDiscounter(discountDTO);
				}
			} catch (Exception e) {					
				e.printStackTrace();
			}
			}
			
		} 
	
	}

	private boolean isAvailDesc() {
		if(discountDTO != null){
			int len = discountDTO.length;
			
			for(int i = 0; i < len; i++){
				if(discountDTO[i].getDescription().equalsIgnoreCase(txtDescription.getText())){
					AdminComposite.display("Description already exists. Give another name", STATUS_SUCCESS,SUCCESS_FONT_COLOR);
					return false;
				}
			}
		}
		return true;
	}

	private boolean isValidate(String[] fromStations, String[] toStations) {
		
		if(fromStations == null || fromStations.length == 0){
			AdminComposite.display("Please select From station", STATUS_SUCCESS,SUCCESS_FONT_COLOR);
			return false;
		}else if(toStations == null || toStations.length == 0){
			AdminComposite.display("Please select To station", STATUS_SUCCESS,SUCCESS_FONT_COLOR);
			return false;
		}if(txtDiscount.getText().equals("")){
			AdminComposite.display("Please enter discount value", STATUS_SUCCESS,SUCCESS_FONT_COLOR);
			return false;
		}if(txtDescription.getText().equals("")){
			AdminComposite.display("Please enter description", STATUS_SUCCESS,SUCCESS_FONT_COLOR);
			return false;
		}
		
		return true;
	}

}
