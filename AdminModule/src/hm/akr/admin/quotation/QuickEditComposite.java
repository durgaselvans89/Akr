package hm.akr.admin.quotation;

import hm.akr.admin.quotation.handler.QuotationHandler;
import hm.akr.admin.workspace.AdminComposite;
import hm.akr.common.FloatLimitValidation;
import hm.akr.common.IUIConstants;
import hm.akr.dto.QuotationDTO;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class QuickEditComposite extends Composite implements SelectionListener, IUIConstants {

	private Table tblQuickEdit;

	private TableColumn colStationName;

	private TableColumn colCustomerName;

	private TableColumn colPriceIndex;

	private Combo cbAdjust;

	private Text txtAdjust;

	private Button btnSet;

	private Composite cptEdit;

	private Label lblPercent;

	private QuotationHandler handler = null;

	private QuotationDTO[] quotList = null;

	//private Text[] txtPI = null;

	private Text[] txtId = null;

	private String INCREMENT_BY = "Increment By";

	private String DECREMENT_BY = "Decrement By";
	
	private String EMPTY_STRING = "";

	private DecimalFormat decimalFormat;

	/**
	 * 
	 * @param parent
	 * @param style
	 */
	public QuickEditComposite(Composite parent, int style, QuotationDTO[] quotList) {
		super(parent, style);
		try {
			handler = new QuotationHandler();
			this.quotList = quotList;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @return
	 * @throws Exception 
	 */
	public Composite loadComposite() {
		GridLayout gl = new GridLayout();
		gl.numColumns = 4;
		gl.horizontalSpacing = 30;
		gl.verticalSpacing = 30;
		gl.marginWidth = 10;
		gl.marginHeight = 10;
		this.setLayout(gl);
		GridData gd = new GridData();
		gd.heightHint = 410;
		gd.widthHint = 385;

		tblQuickEdit = new Table(this, SWT.CHECK | SWT.FULL_SELECTION | SWT.MULTI | SWT.BORDER);
		{

			colCustomerName = new TableColumn(tblQuickEdit, SWT.NONE);
			colCustomerName.setText("Customer Name");
			colCustomerName.setWidth(200);

			colStationName = new TableColumn(tblQuickEdit, SWT.NONE);
			colStationName.setText("Station Code");
			colStationName.setWidth(100);

			colPriceIndex = new TableColumn(tblQuickEdit, SWT.NONE);
			colPriceIndex.setText("PI");
			colPriceIndex.setWidth(80);

		}
		tblQuickEdit.setLinesVisible(true);
		tblQuickEdit.setHeaderVisible(true);
		tblQuickEdit.setLayoutData(gd);
		
		if (quotList != null) {
			populateQuickEditTable(quotList);
		}

		gl = new GridLayout();
		gl.numColumns = 4;
		gl.horizontalSpacing = 5;
		cptEdit = new Composite(this, SWT.NONE);
		cptEdit.setLayout(gl);

		cbAdjust = new Combo(cptEdit, SWT.READ_ONLY);
		cbAdjust.add(INCREMENT_BY);
		cbAdjust.add(DECREMENT_BY);
		

		gd = new GridData();
		gd.widthHint = 30;
		txtAdjust = new Text(cptEdit, SWT.BORDER);
		txtAdjust.setLayoutData(gd);
		txtAdjust.addVerifyListener(new FloatLimitValidation());
		
		lblPercent = new Label(cptEdit, SWT.NONE);
		lblPercent.setText("%");

		gd = new GridData();
		//gd.heightHint=50;
		gd.widthHint = 30;
		btnSet = new Button(cptEdit, SWT.NONE);
		btnSet.setText("Set");
		btnSet.setLayoutData(gd);
		btnSet.addSelectionListener(this);

		return this;

	}

	/**
	 * Method to populate Quick Edit Table
	 */
	private void populateQuickEditTable(QuotationDTO[] quotList) {
		int len = quotList.length;
		TableEditor editor = null;
		TableItem item = null;
		decimalFormat = new DecimalFormat("0.00");
		if (len > 0) {
			//txtPI = new Text[len];
			txtId = new Text[len];
			for (int i = 0; i < len; i++) {
				item = new TableItem(tblQuickEdit, SWT.NONE);

				//Customer Name
				item.setText(0, quotList[i].getCustomerName());

				//Station Name
				item.setText(1, quotList[i].getStationCode());

				//Quotation Id			
				editor = new TableEditor(tblQuickEdit);
				txtId[i] = new Text(tblQuickEdit, SWT.NONE);
				if (quotList != null)
					txtId[i].setText(quotList[i].getId());
				
				txtId[i].setVisible(false);
				editor.grabHorizontal = true;
				editor.setEditor(txtId[i], item, 3);
				
				//System.out.println("quotname==>"+quotList[i].getCustomerName()+"pi==>"+quotList[i].getPriceIndex());
				if((quotList[i].getPriceIndex()%1) == new Float(0)){
					item.setText(2,decimalFormat.format(quotList[i].getPriceIndex()));
				}else{
					item.setText(2,String.valueOf(getRoundFourDecimals(quotList[i].getPriceIndex())));
				}
				

			}
		}

	}

	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {

	}

	@Override
	public void widgetSelected(SelectionEvent event) {
		Object source = event.getSource();
		float priceIndex = 0;
		float adjustedPercent = 0;
		float availPI = 0;
		int len = 0;
		boolean isValid = true;
		HashMap<String, Float> hMap = null;
		byte increment = 0;
		float percent = 0;
		
		if (source == btnSet) {			
			//Update Quick Edit			
			
			TableItem[] items = tblQuickEdit.getItems();
			len = items.length;
			if (len > 0) {
				 hMap = new HashMap<String, Float>();
				for (int i = 0; i < len; i++) {									
					if (items[i].getChecked()) {	
						if(!items[i].getText(2).equals(EMPTY_STRING)){
							availPI = Float.parseFloat(items[i].getText(2));
						}
						
						
						if(!txtAdjust.getText().equals(EMPTY_STRING)){
							percent = Float.parseFloat(txtAdjust.getText());
							adjustedPercent = availPI * ( percent / 100);
						}else{
							isValid = false;
						}
						

						if (cbAdjust.getText().equals(INCREMENT_BY)) {
							increment = 1;
							priceIndex = availPI + adjustedPercent;
						} else {
							increment = 0;
							priceIndex = availPI - adjustedPercent;
						}
						
						priceIndex = getRoundFourDecimals(priceIndex);

						hMap.put(txtId[i].getText(), priceIndex);						
					}

				}

			}

			try {
				if(hMap.size() == 0){
					AdminComposite.display("Please select one item", STATUS_SUCCESS, SUCCESS_FONT_COLOR);					
				}else if(cbAdjust.getSelectionIndex() == -1){
					AdminComposite.display("Please Select Increment/Decrement", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				}else if(!isValid){
					AdminComposite.display("Please enter Adjust Percent", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				}else{					
					handler.updatePriceIndex(hMap, increment, percent);
					AdminComposite.display("Price Index Updated Successfully", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					QuotationViewOptionComposite.isBpiChanged = true;
					txtAdjust.setText(EMPTY_STRING);
					cbAdjust.removeAll();
					cbAdjust.add(INCREMENT_BY);
					cbAdjust.add(DECREMENT_BY);
					for(int i = 0; i < tblQuickEdit.getItemCount(); i++){
						List<String> hashKey = new ArrayList<String>(hMap.keySet());
						for (int j = 0; j < hashKey.size(); j++) {
							if(hashKey.get(j).equals(txtId[i].getText())){
								//System.out.println("new==>"+hMap.get(hashKey.get(j)));
								tblQuickEdit.getItem(i).setText(2, String.valueOf(hMap.get(hashKey.get(j))));
								//quotList[i].setPriceIndex(hMap.get(hashKey.get(j)));								
							}
						}
						tblQuickEdit.getItem(i).setChecked(false);
					}
				}
					
				
			} catch (Exception e) {					
				AdminComposite.display("Price Index Updation Failed", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				e.printStackTrace();
			}

		}

	}

	private float getRoundFourDecimals(float priceIndex) {
		if (priceIndex == 0) {
			return 0;
		} else {
			priceIndex = ((float)(int)(priceIndex * 100000)) / (float)100000;
			priceIndex = priceIndex * 10000;	
			priceIndex = ((float)(int)(priceIndex * 10)) / (float)10;		
			priceIndex = (float) Math.ceil(priceIndex);
			priceIndex = priceIndex / 10000;
		}
		return priceIndex;
	}

}
