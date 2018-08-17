/**
 * 
 */
package hm.akr.container.admin;

import hm.akr.common.KalendarDialog;
import hm.akr.common.MonthDialog;
import hm.akr.common.SWTResourceManager;
import hm.akr.dto.InwardAnalysisDTO;
import hm.akr.dto.StationsDTO;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.text.html.Option;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
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
/**
 * @author user
 *
 */
public class InwardAnalysisComposite extends ReportsMenu implements
		SelectionListener {

	/*private TabFolder tabReport;
	private TabItem tiInwardAnalysis;*/
	private Canvas cvsInwardAnalysis;
	private Table tblInwardAnalysis;	
	private Button btnGo;	
	private Combo cbInwardStation;
	private Combo cbFromStation;
	private Label lblInwardStation;
	private Label label2;
	
	private Label label8;	
	private Text txtIAMonth;
	private Label label9;	
	private Text txtIAToMonth;
	

	private ReportsMenuHandler handler = null;
	private TableColumn column;

	private String[] BRANCHES = null;

	private String[] COLUMNS = null;
	private Button btnMonth;
	private Button btnToMonth;
	private Combo cbOption;
	private final int COLUMN_WIDTH = 60;
	
	private final int TBL_WIDTH = 880;//965
	private final int TBL_HEIGHT = 500;//510;
	
	private String COUNT = "Count";
	private String BASIC_FREIGHT = "Basic Freight";
	private String TOTAL_FREIGHT = "Total Freight";
	private String ACTUAL_WEIGHT = "Actual Weight";
	private String CHARGED_WEIGHT = "Charged Weight";
	private String NOA = "Noa";
	
	private float clientRights = -1;
	private String currentStationName = "";
	private String currentStationCode = "";
	
	
	public InwardAnalysisComposite(Composite shell, int value, float clientRights) {
		super(shell, value);
		try {
			this.clientRights = clientRights;
			currentStationName = beanutil.getActingStationName();
			currentStationCode = beanutil.getActingStationCode();
			handler = new ReportsMenuHandler();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Composite loadcomposite() throws Exception {

		this.setSize(1029, 650);

		/*tabReport = new TabFolder(this, SWT.NONE);
		tabReport.setBounds(0, 0, 1000, 650);

		tiInwardAnalysis = new TabItem(tabReport, SWT.NONE);
		tiInwardAnalysis.setText("");*/

		cvsInwardAnalysis = new Canvas(this, SWT.NONE);
		cvsInwardAnalysis.setBounds(45, 100, 900, 550);
		
		{
			tblInwardAnalysis = new Table(cvsInwardAnalysis, SWT.FULL_SELECTION
					| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
			tblInwardAnalysis.setBounds(5, 40, TBL_WIDTH, TBL_HEIGHT);

			tblInwardAnalysis.setHeaderVisible(true);
			tblInwardAnalysis.setLinesVisible(true);			
		}

		{
			lblInwardStation = new Label(cvsInwardAnalysis, SWT.NONE);
			lblInwardStation.setText("Inward Station");
			lblInwardStation.setBounds(330, 14, 75, 23);
		}
		{
			label2 = new Label(cvsInwardAnalysis, SWT.NONE);
			label2.setText("From Station");
			label2.setBounds(520, 14, 70, 22);
		}
		
		
		
		
		/*{
			label8 = new Label(cvsInwardAnalysis, SWT.NONE);
			label8.setText("From Date");
			label8.setBounds(42, 14, 55, 16);
		}		
		{
			txtIAMonth = new Text(cvsInwardAnalysis, SWT.BORDER);
			txtIAMonth.setBounds(100, 13, 60, 22);
			txtIAMonth.setEnabled(false);
			DateFormat dateFormat = new SimpleDateFormat("MM-yyyy");
			java.util.Date currentDate = new java.util.Date();
			String date = dateFormat.format(currentDate);
			txtIAMonth.setText(date);

		}
		{
			btnMonth = new Button(cvsInwardAnalysis, SWT.PUSH | SWT.CENTER);
			btnMonth.setImage(SWTResourceManager
					.getImage("hm/akr/resources/Calendar.jpg"));
			btnMonth.setBounds(160, 12, 26, 23);
			btnMonth.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
				}

				@Override
				public void widgetSelected(SelectionEvent event) {
					MonthDialog cd = new MonthDialog(Display
							.getCurrent().getActiveShell());
					Object obj = cd.open();
					if (obj != null) {
						
						txtIAMonth.setText(obj.toString());
					}
				}

			});
		}
		
				
		
		{
			cbInwardStation = new Combo(cvsInwardAnalysis, SWT.READ_ONLY);
			cbInwardStation.setBounds(280, 13, 100, 21);
			cbInwardStation.add("All");
		}
		{
			cbFromStation = new Combo(cvsInwardAnalysis, SWT.READ_ONLY);
			cbFromStation.setBounds(470, 13, 92, 25);
			cbFromStation.add("All");
		}
		
		{
			cbOption = new Combo(cvsInwardAnalysis, SWT.READ_ONLY);
			cbOption.setBounds(572, 13, 110, 25);
			cbOption.add(COUNT);
			cbOption.add(BASIC_FREIGHT);
			cbOption.add(TOTAL_FREIGHT);
			cbOption.add(ACTUAL_WEIGHT);
			cbOption.add(CHARGED_WEIGHT);
			cbOption.add(NOA);
			//cbOption.select(0);
			
		}
		
		{
			btnGo = new Button(cvsInwardAnalysis, SWT.PUSH | SWT.CENTER);
			btnGo.setText("Go");
			btnGo.setBounds(692, 12, 36, 23);
			btnGo.addSelectionListener(this);
		}*/
		
		
		{
			label8 = new Label(cvsInwardAnalysis, SWT.NONE);
			label8.setText("From Date");
			label8.setBounds(10, 14, 55, 16);
		}		
		{
			txtIAMonth = new Text(cvsInwardAnalysis, SWT.BORDER);
			txtIAMonth.setBounds(75, 13, 60, 22);
			txtIAMonth.setEnabled(false);
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date currentDate = new java.util.Date();
			String date = dateFormat.format(currentDate);
			txtIAMonth.setText(date);

		}
		{
			btnMonth = new Button(cvsInwardAnalysis, SWT.PUSH | SWT.CENTER);
			btnMonth.setImage(SWTResourceManager
					.getImage("hm/akr/resources/Calendar.jpg"));
			btnMonth.setBounds(135, 12, 25, 23);
			btnMonth.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
				}

				@Override
				public void widgetSelected(SelectionEvent event) {
					KalendarDialog cd = new KalendarDialog(Display
							.getCurrent().getActiveShell());
					Object obj = cd.open();
					if (obj != null) {
						
						txtIAMonth.setText(obj.toString());
					}
				}

			});
		}
		
		{
			label9 = new Label(cvsInwardAnalysis, SWT.NONE);
			label9.setText("To Date");
			label9.setBounds(170, 14, 55, 23);
		}		
		{
			txtIAToMonth = new Text(cvsInwardAnalysis, SWT.BORDER);
			txtIAToMonth.setBounds(235, 13, 60, 22);
			txtIAToMonth.setEnabled(false);
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date currentDate = new java.util.Date();
			String date = dateFormat.format(currentDate);
			txtIAToMonth.setText(date);

		}
		{
			btnToMonth = new Button(cvsInwardAnalysis, SWT.PUSH | SWT.CENTER);
			btnToMonth.setImage(SWTResourceManager
					.getImage("hm/akr/resources/Calendar.jpg"));
			btnToMonth.setBounds(295, 12, 25, 23);
			btnToMonth.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
				}

				@Override
				public void widgetSelected(SelectionEvent event) {
					KalendarDialog cd = new KalendarDialog(Display
							.getCurrent().getActiveShell());
					Object obj = cd.open();
					if (obj != null) {
						
						txtIAToMonth.setText(obj.toString());
					}
				}

			});
		}

		
		
		{
			cbInwardStation = new Combo(cvsInwardAnalysis, SWT.READ_ONLY);
			cbInwardStation.setBounds(410, 13, 100, 21);
			if(clientRights == 1 || clientRights == 1.0){
				
				try{
				
					getCurrentBranch_code(currentStationCode,cbInwardStation);
					cbInwardStation.select(0);
					//handleBranchActionUT(cbUTBranch, cbUTStation);
					//cbUTBranch.addSelectionListener(this);
				}
				catch(Exception e){
					e.printStackTrace();
				}
				
				
			}
			else{
				cbInwardStation.add("All");
			}
		}
		{
			cbFromStation = new Combo(cvsInwardAnalysis, SWT.READ_ONLY);
			cbFromStation.setBounds(600, 13, 95, 25);
			/*if(clientRights == 1 || clientRights == 1.0){
				
				try{
				
					getCurrentBranch_code(currentStationCode,cbFromStation);
					cbFromStation.select(0);
					//handleBranchActionUT(cbUTBranch, cbUTStation);
					//cbUTBranch.addSelectionListener(this);
				}
				catch(Exception e){
					e.printStackTrace();
				}
				
				
			}
			else*/{
				cbFromStation.add("All");
			}
		}
		
		{
			cbOption = new Combo(cvsInwardAnalysis, SWT.READ_ONLY);
			cbOption.setBounds(705, 13, 110, 25);
			cbOption.add(COUNT);
			cbOption.add(BASIC_FREIGHT);
			cbOption.add(TOTAL_FREIGHT);
			cbOption.add(ACTUAL_WEIGHT);
			cbOption.add(CHARGED_WEIGHT);
			cbOption.add(NOA);
			//cbOption.select(0);
			
		}
		
		{
			btnGo = new Button(cvsInwardAnalysis, SWT.PUSH | SWT.CENTER);
			btnGo.setText("Go");
			btnGo.setBounds(825, 12, 35, 23);
			btnGo.addSelectionListener(this);
		}
		
			try{
				populateComboStations();
			}
			catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		

		return this;
	}

	private void getCurrentBranch_code(String station, Combo cbInwardStation) throws Exception {
		StationsDTO[] stations = null;

		stations = handler.getAllStations();
		int size = 0;
		if (null != stations) {
			size = stations.length;

			for (int i = 0; i < size; i++) {
				if (station.equalsIgnoreCase(stations[i].getId())) {
					if(cbInwardStation == cbInwardStation){
						cbInwardStation.add(stations[i].getBranch_code());	
					}
					else{
						cbFromStation.add(stations[i].getBranch_code());
					}
					// getCurrentBranchName(stations[i].getBranch_code());
					
					//return stations[i].getBranch_code();
				}
			}
		}

		//return null;
	}
	
/*	private void getCurrentBranchName(String branch_code) {
		// TODO Auto-generated method stub
		try{
			StationsDTO[] stations = null;
	
			stations = handler.getAllStations();
			int size = 0;
			if (null != stations) {
				size = stations.length;
	
				for (int i = 0; i < size; i++) {
					if (branch_code.equalsIgnoreCase(stations[i].getId())) {
					
						cbFromStation.add(stations[i].getName()+" - "+branch_code);
						cbInwardStation.add(stations[i].getName()+" - "+branch_code);
						//return stations[i].getBranch_code();
					}
				}
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
*/
	

	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void widgetSelected(SelectionEvent event) {
		try {
			Object source = event.getSource();

			if (source == btnGo) {
				//validate();
				if ((cbFromStation.getSelectionIndex() != -1)
						&& (cbInwardStation.getSelectionIndex() != -1)) {
					designTable();
					
					if(handler != null){
						InwardAnalysisDTO[] dto = null;						
												
						String date = txtIAMonth.getText();
						int index = date.indexOf("-");
						String month = date.substring(0, index);
						String year = date.substring(index+1);
						DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
						Date fromDt = dateFormat.parse(txtIAMonth.getText());
						Date toDate = dateFormat.parse(txtIAToMonth.getText());						
						dto = handler.getinwardAnalysis(fromDt, toDate, cbFromStation.getText(), cbInwardStation.getText());
						
						if(dto != null) {
							populateInwardAnalysis(dto);
						}
					}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param dto
	 */
	private void populateInwardAnalysis(InwardAnalysisDTO[] dto) {
		int dtoLen = dto.length;
		int colLen = COLUMNS.length;
		final TableItem item1 = new TableItem(tblInwardAnalysis, SWT.NONE);
		Font font1 = new Font(Display.getDefault(), "Tahoma", 8, SWT.BOLD);
		item1.setFont(font1);
		item1.setText(0, "TOTAL");
		int colVal = 0;
		float colFloatVal = 0;
		TableItem[] items = tblInwardAnalysis.getItems();
		int itemsLen = items.length;
					
		int colTotIndex = tblInwardAnalysis.getColumnCount();
		
		// Col Total
		column = new TableColumn(tblInwardAnalysis, SWT.CENTER);
		column.setText("TOTAL");
		column.setWidth(60);
		column.setAlignment(SWT.RIGHT);
		DecimalFormat df = new DecimalFormat("0.00");
		
		for(int i = 0; i < dtoLen; i++){
			
			for(int j = 0; j < itemsLen; j++){
				if(dto[i].getInwardStation().equalsIgnoreCase(items[j].getText(0))){
					
					for(int k = 0; k < colLen; k++){
						if(dto[i].getFromStation().equalsIgnoreCase(COLUMNS[k])){
							if(cbOption.getText().equals(COUNT)){
								
								items[j].setText(k+1, String.valueOf(dto[i].getCount()));
								
								//total
								if(!items[itemsLen-1].getText(k+1).equals("")){
									colVal = Integer.parseInt(items[itemsLen-1].getText(k+1));
								}else{
									colVal = 0;
								}
								items[itemsLen-1].setText(k+1, String.valueOf(colVal + dto[i].getCount()));
								
								
								// COL
								if(!items[j].getText(colTotIndex).equals("")){
									colVal = Integer.parseInt(items[j].getText(colTotIndex));
								}else{
									colVal = 0;
								}
								items[j].setText(colTotIndex, String.valueOf(colVal + dto[i].getCount()));
								
							}else if(cbOption.getText().equals(BASIC_FREIGHT)){
								items[j].setText(k+1, df.format(dto[i].getBasic_freight()));
								//total
								if(!items[itemsLen-1].getText(k+1).equals("")){
									colFloatVal = Float.parseFloat(items[itemsLen-1].getText(k+1));
								}else{
									colFloatVal = 0;
								}
								items[itemsLen-1].setText(k+1, df.format(colFloatVal + dto[i].getBasic_freight()));
								
								//COL total
								if(!items[j].getText(colTotIndex).equals("")){
									colFloatVal = Float.parseFloat(items[j].getText(colTotIndex));
								}else{
									colFloatVal = 0;
								}
								items[j].setText(colTotIndex, df.format(colFloatVal + dto[i].getBasic_freight()));
							}else if(cbOption.getText().equals(TOTAL_FREIGHT)){
								items[j].setText(k+1, df.format(dto[i].getTotal_freight()));
								//total
								if(!items[itemsLen-1].getText(k+1).equals("")){
									colFloatVal = Float.parseFloat(items[itemsLen-1].getText(k+1));
								}else{
									colFloatVal = 0;
								}
								items[itemsLen-1].setText(k+1, df.format(colFloatVal + dto[i].getTotal_freight()));
								
								//COL total
								if(!items[j].getText(colTotIndex).equals("")){
									colFloatVal = Float.parseFloat(items[j].getText(colTotIndex));
								}else{
									colFloatVal = 0;
								}
								items[j].setText(colTotIndex, df.format(colFloatVal + dto[i].getTotal_freight()));
								
							}else if(cbOption.getText().equals(ACTUAL_WEIGHT)){
								items[j].setText(k+1, df.format(dto[i].getActual_weight()));
								
								//total
								if(!items[itemsLen-1].getText(k+1).equals("")){
									colFloatVal = Float.parseFloat(items[itemsLen-1].getText(k+1));
								}else{
									colFloatVal = 0;
								}
								items[itemsLen-1].setText(k+1, df.format(colFloatVal + dto[i].getActual_weight()));
								
								//COL total
								if(!items[j].getText(colTotIndex).equals("")){
									colFloatVal = Float.parseFloat(items[j].getText(colTotIndex));
								}else{
									colFloatVal = 0;
								}
								items[j].setText(colTotIndex, String.valueOf(colFloatVal + dto[i].getActual_weight()));
							}else if(cbOption.getText().equals(CHARGED_WEIGHT)){
								items[j].setText(k+1, df.format(dto[i].getCharged_weight()));
								
								//total
								if(!items[itemsLen-1].getText(k+1).equals("")){
									colFloatVal = Float.parseFloat(items[itemsLen-1].getText(k+1));
								}else{
									colFloatVal = 0;
								}
								items[itemsLen-1].setText(k+1, df.format(colFloatVal + dto[i].getCharged_weight()));
								
								//COL total
								if(!items[j].getText(colTotIndex).equals("")){
									colFloatVal = Float.parseFloat(items[j].getText(colTotIndex));
								}else{
									colFloatVal = 0;
								}
								items[j].setText(colTotIndex, df.format(colFloatVal + dto[i].getCharged_weight()));
							}else if(cbOption.getText().equals(NOA)){
								items[j].setText(k+1, String.valueOf(dto[i].getNoa()));
								
								//total
								if(!items[itemsLen-1].getText(k+1).equals("")){
									colVal = Integer.parseInt(items[itemsLen-1].getText(k+1));
								}else{
									colVal = 0;
								}
								items[itemsLen-1].setText(k+1, String.valueOf(colVal + dto[i].getNoa()));
								
								//Col total
								if(!items[j].getText(colTotIndex).equals("")){
									colVal = Integer.parseInt(items[j].getText(colTotIndex));
								}else{
									colVal = 0;
								}
								items[j].setText(colTotIndex, String.valueOf(colVal + dto[i].getNoa()));
							}					
							
						}
					}
					
				}
							
				
			}
		}
		
				
		if(cbOption.getText().equals(COUNT) || cbOption.getText().equals(NOA)){
			
		int finalVal = 0;
		for(int i = 0; i < itemsLen; i++){
			//Final Total
			if(!items[i].getText(colTotIndex).equals("")){
				colVal = Integer.parseInt(items[i].getText(colTotIndex));
			}else{
				colVal = 0;
			}
			
			finalVal = colVal + finalVal;
			
		}
		items[itemsLen-1].setText(colTotIndex, String.valueOf(finalVal));
		}else{
			float finalVal = 0;
			for(int i = 0; i < itemsLen; i++){
				//Final Total
				if(!items[i].getText(colTotIndex).equals("")){
					colFloatVal = Float.parseFloat(items[i].getText(colTotIndex));
				}else{
					colFloatVal = 0;
				}
				
				finalVal = colFloatVal + finalVal;
				
			}
			items[itemsLen-1].setText(colTotIndex, df.format(finalVal));
		}
		
		fillZerosOnEmpty(tblInwardAnalysis);
	}
	
	private void fillZerosOnEmpty(Table refTbl) {
		
		TableItem[] items = refTbl.getItems();
		int len = items.length;		
		if (len > 0) {
			if(refTbl == tblInwardAnalysis){
				len+=1;
			}
			for (int i = 0; i < len-1; i++) {
				for (int j = 1; j < refTbl.getColumnCount(); j++) {
					if (items[i].getText(j).equalsIgnoreCase("")
							|| items[i].getText(j).equalsIgnoreCase("0.00")) {
						items[i].setText(j, "0");
					}
				}
			}
		}
	}

	private boolean designTable() throws Exception {

		if (tblInwardAnalysis != null) {
			tblInwardAnalysis.dispose();
		}

		tblInwardAnalysis = new Table(cvsInwardAnalysis, SWT.FULL_SELECTION
				| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		tblInwardAnalysis.setBounds(5, 40, TBL_WIDTH, TBL_HEIGHT);

		tblInwardAnalysis.setHeaderVisible(true);
		tblInwardAnalysis.setLinesVisible(true);

		if (cbFromStation.getText().equalsIgnoreCase("All")
				&& cbInwardStation.getText().equalsIgnoreCase("All")) {
			COLUMNS = BRANCHES;
			allFieldsTable();
		} else {
			String[] rows = null;			
			if (cbInwardStation.getText().equalsIgnoreCase("All")) {
				rows = BRANCHES;
				COLUMNS = getBranchStations(cbFromStation.getText());
				
			} else if (cbFromStation.getText().equalsIgnoreCase("All")) {
				rows = getBranchStations(cbInwardStation.getText());				
				COLUMNS = BRANCHES;
			} else {
				rows = getBranchStations(cbInwardStation.getText());
				COLUMNS = getBranchStations(cbFromStation.getText());			
			}

			createDynamicTable(rows, COLUMNS);
		}
		
		return true;

	}
	
	/**
	 * 
	 * @param rows
	 * @param columns
	 */
	private void createDynamicTable(String[] rows, String[] columns) {
		int colLen = columns.length;
		int rowLen = rows.length;
	
		//TOTAL 1
		int width = ((colLen+2) * COLUMN_WIDTH)+30;
		int height = ((rowLen+2) * tblInwardAnalysis.getItemHeight()) + 40;
		if(colLen == BRANCHES.length){			
			//width = width - 3;
		}
		
		for (int i = 0; i < colLen + 1; i++) {
			column = new TableColumn(tblInwardAnalysis, SWT.CENTER);
			column.setAlignment(SWT.RIGHT);
			if (i != 0){
				column.setText(columns[i - 1]);
				tblInwardAnalysis.getColumn(i).setWidth(COLUMN_WIDTH);
			}else{
				tblInwardAnalysis.getColumn(i).setWidth(COLUMN_WIDTH-10);
			}
			
			
		}

		for (int j = 0; j < rowLen; j++) {
			TableItem item = new TableItem(tblInwardAnalysis, SWT.NONE);
			item.setText(0, rows[j]);
			//item.setBackground(0, new Color(Display.getCurrent(), 210,180,140));
			item.setBackground(0, new Color(Display.getCurrent(), 237, 234,	219));
		}

				
		
		/*if(width > TBL_WIDTH){
			height = height + 20;
		}
		
		if(height > TBL_HEIGHT){
			width = width + 20;
		}*/
		// For Horizontal bar
		width = (width < TBL_WIDTH) ? width : TBL_WIDTH+10;
		// For Vertical bar
		height = (height < TBL_HEIGHT) ? height : TBL_HEIGHT ;

		tblInwardAnalysis.setSize(width, height);

	}

	/**
	 * 
	 */
	private void allFieldsTable() {

		int colLen = BRANCHES.length;
		int width = ((colLen+2) * COLUMN_WIDTH)+30;
		int height = ((colLen+2) * tblInwardAnalysis.getItemHeight()) + 40;
		
		
		for (int i = 0; i < colLen + 1; i++) {
			column = new TableColumn(tblInwardAnalysis, SWT.CENTER);
			column.setAlignment(SWT.RIGHT);
			if (i != 0)
				column.setText(BRANCHES[i - 1]);
			tblInwardAnalysis.getColumn(i).setWidth(COLUMN_WIDTH);

			if (i != colLen) {
				TableItem item = new TableItem(tblInwardAnalysis, SWT.NONE);
				item.setText(0, BRANCHES[i]);
				item.setBackground(0, new Color(Display.getCurrent(), 237, 234,	219));
			}
		}

		
		/*if(width > TBL_WIDTH){
			height = height + 20;
		}
		
		if(height > TBL_HEIGHT){
			width = width + 20;
		}*/
		// For Horizontal bar
		width = (width < TBL_WIDTH) ? width : TBL_WIDTH+10;
		// For Vertical bar
		height = (height < TBL_HEIGHT) ? height : TBL_HEIGHT;
		

		tblInwardAnalysis.setSize(width, height);

	}

	/**
	 * 
	 * @param branch
	 * @return
	 * @throws Exception
	 */
	private String[] getBranchStations(String branch) throws Exception {
		StationsDTO[] stations = null;
		stations = handler.getAllStations();
		if (stations != null) {
			int len = stations.length;
			ArrayList<String> list = new ArrayList<String>();
			for (int i = 0; i < len; i++) {
				if (branch.equalsIgnoreCase(stations[i].getBranch_code())) {
					list.add(stations[i].getId());
				}
			}

			int size = list.size();
			if (size > 0) {
				return (String[]) list.toArray(new String[size]);
			}
		}

		return null;
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	private void populateComboStations() throws Exception {
		StationsDTO[] stations = null;
		stations = handler.getAllStations();
		if (stations != null) {
			String stnCode = null;
			int len = stations.length;
			ArrayList<String> list = new ArrayList<String>();

			for (int i = 0; i < len; i++) {
				stnCode = stations[i].getId();
				if (stnCode.equalsIgnoreCase(stations[i].getBranch_code()) 
						&& !stations[i].getBranch_code().equalsIgnoreCase("HO")) {
					list.add(stnCode);
					cbFromStation.add(stnCode);
					if(!(clientRights == 1 || clientRights == 1.0)){
						
						cbInwardStation.add(stnCode);
					}
					
				}
			}

			int size = list.size();
			if (size > 0)
				BRANCHES = (String[]) list.toArray(new String[size]);
		}
	}

}
