package hm.akr.admin.distance;

import hm.akr.admin.distance.handler.DistanceCompositeHandler;
import hm.akr.admin.workspace.AdminComposite;
import hm.akr.common.BeanUtil;
import hm.akr.common.IUIConstants;
import hm.akr.common.NumericValidation;
import hm.akr.dto.DistanceExcelDecorator;
import hm.akr.dto.DistanceListDTO;
import hm.akr.dto.StationsDTO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.Collator;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Properties;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class DistanceComposite extends Composite implements IUIConstants {

	Properties properties = null;

	private Table tblDistance;

	private TableColumn column;	

	private Combo cbSB = null;

	private Button rdOBSC;

	private Button rdOBSN;

	private Button chkEdit;

	private Label label1;

	private Canvas canvas2;	

	DistanceCompositeHandler handler = null;

	private Button btnSet;

	private DecimalFormat decimalFormat;

	private Button btnExcel;
	
	public static ArrayList<Text> txtList;
	
	DistanceListDTO[] dto = null;
	
	String[] column_head = { "State", "Branch", "Station Name",
			"Code", "Distance", "Net Charge", "Increment", "Card Rate" };
	

	/**
	 * 
	 * @param shell
	 * @param swtValue
	 * @throws Exception
	 */
	public DistanceComposite(Composite composite, int swtValue)
			throws Exception {
		super(composite, swtValue);
		
		try {
			handler = new DistanceCompositeHandler();			

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
				this.setSize(877, 595);				

			}
			
			decimalFormat = new DecimalFormat("0.00");
			
			{
				canvas2 = new Canvas(this, SWT.NONE);
				canvas2.setBounds(10, 0, 844, 583);
				{
					label1 = new Label(canvas2, SWT.NONE);
					label1.setText("Source Branch");
					label1.setBounds(10, 3, 75, 15);
				}

				{
					if(BeanUtil.getAdminRights() == 3){
					chkEdit = new Button(canvas2, SWT.CHECK | SWT.LEFT);
					chkEdit.setText("Allow To Edit Distance");
					chkEdit.setBounds(677, 12, 131, 20);
					}
					if(chkEdit != null)
					chkEdit.addSelectionListener(new SelectionListener(){

						@Override
						public void widgetDefaultSelected(SelectionEvent arg0) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void widgetSelected(SelectionEvent arg0) {
							try {
								if(chkEdit.getSelection()){
									populateDistanceTableEdit();								
								}else{
									if(txtList != null){
									for(int i = 0; i < txtList.size(); i++){
										txtList.get(i).dispose();
									}
									txtList = null;
									}
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							
						}
						
					});

				}

				{
					cbSB = new Combo(canvas2, SWT.DROP_DOWN);
					cbSB.setBounds(85, 1, 233, 10);

					cbSB.addKeyListener(new KeyListener() {

						@Override
						public void keyPressed(KeyEvent e) {

							String selectedText = cbSB.getText();
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

								cbSB.remove(0, cbSB.getItemCount() - 1);
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

								String[] items = cbSB.getItems();
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
									cbSB.remove(0, len - 1);

									for (int i = startIndex; i < len; i++) {
										String temp = items[i].toLowerCase();
										if (temp.contains(selectedText)) {
											cbSB.add(items[i]);
										}
									}
									if (selectedText.length() == 1) {
										showPopup(cbSB, true);
									}
								}
							}
						}

						@Override
						public void keyReleased(KeyEvent e) {
							String temp = cbSB.getText();
							int len = temp.length();
							if (len == 0) {
								cbSB.remove(0, cbSB.getItemCount() - 1);
								populateDestStationCodes();
							}
						}
					});
					cbSB.addSelectionListener(new SourceBranchListener());
					populateDestStationCodes();
				}
				{

					createTable(8, 0, column_head, null);
					
				}
				{
					rdOBSN = new Button(canvas2, SWT.RADIO | SWT.LEFT);
					rdOBSN.setText("Order By Station Name");
					rdOBSN.setBounds(7, 543, 140, 20);
					rdOBSN.setSelection(true);
									
					rdOBSN.addSelectionListener(new DistanceListener());
				}
				{
					rdOBSC = new Button(canvas2, SWT.RADIO | SWT.LEFT);
					rdOBSC.setText("Order By Station Code");
					rdOBSC.setBounds(150, 543, 140, 23);
					rdOBSC.addSelectionListener(new DistanceListener());

				}
				if(BeanUtil.getAdminRights() == 3){
				{
					btnSet = new Button(canvas2, SWT.NONE);
					btnSet.setText("Set");
					btnSet.setBounds(350, 540, 40, 23);
					btnSet.addSelectionListener(new SourceBranchListener());
				}
				}
				
				{
					btnExcel = new Button(canvas2, SWT.NONE);
					btnExcel.setText("Export To Excel");
					btnExcel.setBounds(400, 540, 120, 23);
					btnExcel.addSelectionListener(new SourceBranchListener());
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;

	}

	

	/**
	 * 
	 */
	private void populateDestStationCodes() {
		try {
			StationsDTO[] stations = handler.getAllStations();
			if (null != stations) {
				int len = stations.length;
				for (int i = 0; i < len; i++) {
					//if( (stations[i].getId().equalsIgnoreCase(stations[i].getBranch_code())) || 
						//	 !stations[i].getType().equalsIgnoreCase("City")){ 
						cbSB.add(stations[i].getName() + " - "
								+ stations[i].getId());
					//}					
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

	/**
	 * 
	 * @param columns
	 * @param rows
	 * @param column_name
	 * @param row_data
	 */
	private void createTable(int columns, int rows, String[] column_name,
			Object[] row_data) {

		tblDistance = new Table(canvas2, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		tblDistance.setLinesVisible(true);
		tblDistance.setHeaderVisible(true);
		tblDistance.setBounds(24, 51, 779, 489);

		for (int i = 0; i < columns; i++) {
			column = new TableColumn(tblDistance, SWT.NONE);

			if (i == 0) {
				column.setText(column_name[i]);
				column.setWidth(100);
			} else if (i == 2) {
				column.setText(column_name[i]);
				column.setWidth(170);
			} else {
				column.setText(column_name[i]);
				column.setWidth(80);
			}
		}

	}

	

	/**
	 * 
	 * @param reftable
	 */
	private void handleItemChangeAction(Table reftable) {

		int index = cbSB.getSelectionIndex();
		String name = null;

		if (index > -1) {
			name = cbSB.getItem(index);

		} else {
			name = cbSB.getText();
			if (name.trim().length() == 0)
				name = null;
		}
		try {
			if (null != name) {
				reftable.dispose();
				
				StationsDTO row[] = null;
				row = handler.getAllStations();
				int rows = row.length;
				Object[] row1 = row;
				createTable(8, rows, column_head, row1);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param index
	 * @param refTable
	 */
	
	private void sortByString(int index, Table refTable) {
		
		TableItem[] items = refTable.getItems();
		Collator collator = Collator.getInstance(Locale.getDefault());

		int len = items.length;
		
		for (int i = 1; i < len; i++) {
			if (!items[i].getText(index).isEmpty()) {
				String value1 = items[i].getText(index);
				for (int j = 0; j < i; j++) {
					String value2 = items[j].getText(index);
					if (collator.compare(value1, value2) < 0) {
						String[] values = { items[i].getText(0),
								items[i].getText(1), items[i].getText(2),
								items[i].getText(3), items[i].getText(4),
								items[i].getText(5), items[i].getText(6), 
								items[i].getText(7)};
						items[i].dispose();

						
						
						TableItem item = new TableItem(refTable, SWT.NONE, j);
						item.setText(values);
						items = refTable.getItems();
						break;
					}
				}
			}
		}
		if(txtList != null)
		{
		for(int j = 0 ; j < txtList.size(); j++){			
			txtList.get(j).dispose();
		}
		txtList = null;		
		}
		
		if(chkEdit != null)
			chkEdit.setSelection(false);

	}
	
	/**
	 * 
	 * @author
	 * 
	 */
	public class DistanceListener implements SelectionListener {

		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void widgetSelected(SelectionEvent event) {
			// TODO Auto-generated method stub
			Object source = event.getSource();

			if (rdOBSN == source) {
				try {
					sortByString(2, tblDistance);
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			} else if (rdOBSC == source) {
				try {
					sortByString(3, tblDistance);
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		}

	}

	/**
	 * 
	 * @author
	 * 
	 */
	public class SourceBranchListener implements SelectionListener {

		
		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void widgetSelected(SelectionEvent event) {

			Object source = event.getSource();
			
			if (cbSB == source) {
				AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				try {					
					if(BeanUtil.isBPI_Changed){
						handler.refreshAllStations();
						//AdminComposite.display("BPI Value Changed",	STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						BeanUtil.isBPI_Changed = false;
					}
					StationsDTO[] stations = handler.getAllStations();
					String stationcode = null;
					float frombpi = 0;
					handleItemChangeAction(tblDistance);
					int index = cbSB.getSelectionIndex();
					if (index > -1) {
						stationcode = cbSB.getItem(index);
						index = stationcode.indexOf("-");
						stationcode = stationcode.substring(index + 2);						
						//stationcode = checkCityStation(stationcode);						
						dto = getDistanceDTO(stationcode);

						for (int bpi = 0; bpi < stations.length; bpi++) {
							if (stationcode.equals(stations[bpi]
									.getId())) {
								frombpi = stations[bpi].getBpi();
								
								break;
							}
						}
						
						if (null != dto) {
							try {
								boolean isUpdate = false;
								//Arrays.sort(dto);
								isUpdate = populateDistanceTableView(dto, stations);
								
								if(isUpdate){
									//submitDistance();
								}
							} catch (Exception e) {								
								e.printStackTrace();
							}
						}

					}
					sortByString(2, tblDistance);

				} catch (Exception exception) {
					exception.printStackTrace();
				}
			} else if (btnSet == source) {
				AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				submitDistance();
			} else if (btnExcel == source) {
				AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				exportToExcel();
			}

		}
		

		private void submitDistance() {
			DistanceListDTO[] distancedto = null;
			distancedto = buildDistanceDTO();
			String fromstation = null;
			if (null != distancedto) {
				int index = cbSB.getSelectionIndex();
				if (index > -1) {
					fromstation = cbSB.getItem(index);
					index = fromstation.indexOf("-");
					if (index > -1) {
						fromstation = fromstation.substring(index + 2);
					}
					}
				try {
					//fromstation = checkCityStation(fromstation);
					setDistanceDTO(fromstation, distancedto);
					//displayError("Distance Modified");
					AdminComposite.display("Distance Update  Successfull",
							STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				} catch (Exception e) {
					//displayError("Problem While Updating Distance");
					AdminComposite.display("Distance Updation  Failed",
							STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					e.printStackTrace();
				}
			}
			
		}		
		
		
		private boolean populateDistanceTableView(DistanceListDTO[] dto, StationsDTO[] stations) {			
			
			AdminComposite.display("",STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			boolean isChanged = false;
			float finalRate = 0;
			int incr = 0;
			
			if (null != tblDistance) {
				tblDistance.removeAll();

				txtList = new ArrayList<Text>();
				if (stations != null) {
					for (int j = 0; j < dto.length; j++) {
						for (int i = 0; i < stations.length ; i++) {
							
							if (stations[i].getId().equalsIgnoreCase(
									dto[j].getDestStation())) {
								
								float dtoCardRate = 0;
								String strCardRate = "";
								
								final TableItem item = new TableItem(tblDistance, SWT.NONE);
								if (stations[i].getState() != null)
									item.setText(0, stations[i].getState());
								if (stations[i].getBranch_code() != null)
									item.setText(1, stations[i]
											.getBranch_code());
								if (stations[i].getName() != null)
									item.setText(2, stations[i].getName());
								if (stations[i].getId() != null)
									item.setText(3, stations[i].getId());

								item.setText(4, String.valueOf(dto[j].getDistance()));
																
								dtoCardRate = dto[j].getCardRate();
								//System.out.println("stn-->"+dto[j].getDestStation()+"cr-->"+dtoCardRate);
								strCardRate = getRoundedCeilValue(dtoCardRate);
								item.setText(5, strCardRate);		
								
								dtoCardRate = Float.parseFloat(strCardRate);
								incr = dto[j].getIncrementer();
								item.setText(6, String.valueOf(incr));								 
								finalRate = (dtoCardRate * incr / 100) + dtoCardRate;
								item.setText(7, getRoundedCeilValue(finalRate));
								
							}

						}
					}
				}

			}
			
			return isChanged;
		}		
		

		/**
		 * 
		 * @return
		 * 
		 */
		private DistanceListDTO[] buildDistanceDTO() {
			DistanceListDTO dto = null;
			ArrayList<DistanceListDTO> list = new ArrayList<DistanceListDTO>();

			TableItem[] items = tblDistance.getItems();
			int length = items.length;
			for (int i = 0; i < length; i++) {
				dto = new DistanceListDTO();
				dto.setDestStation(items[i].getText(3));
				dto.setDistance(Integer.parseInt(items[i].getText(4)));
				dto.setCardRate(Float.parseFloat(items[i].getText(5)));

				list.add(dto);
			}

			int size = list.size();
			if (size > 0) {
				return list
						.toArray(new DistanceListDTO[size]);
			}

			return null;
		}
		
		private void exportToExcel() {
			DistanceListDTO dto = null;
			ArrayList<DistanceExcelDecorator> list = new ArrayList<DistanceExcelDecorator>();
			String fromStation = cbSB.getText();
			int length = 0; 
			
			TableItem[] items = tblDistance.getItems();
			if(items != null){
				length = items.length;
			}
			if(length > 0){
			for (int i = 0; i < length; i++) {
				dto = new DistanceListDTO();
				dto.setDestStation(getStationName(items[i].getText(3)));
				dto.setDistance(Integer.parseInt(items[i].getText(4)));
				dto.setCardRate(Float.parseFloat(items[i].getText(5)));

				list.add(new DistanceExcelDecorator(dto, i+1));
			}
			
			if (null != handler) {
				try {
					AdminComposite.display("Exporting...", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					handler.printDistanceExcel(list, fromStation);
					
					FileDialog dialog = new FileDialog(canvas2.getShell(), SWT.SAVE);
					dialog.setFilterExtensions(new String[] { "*.xls" });
					dialog.setFilterNames(new String[] { "*.xls" });
					dialog.setFilterPath("WINDOWS/Desktop"); // Windows path
					dialog.setFileName(fromStation);								
					String filepath = dialog.open();

					if (null != filepath) {
						File df = new File(filepath);
						File xl = new File("lib/Distance.xls");
						
						if(xl.exists()){
							//AdminComposite.display("Saving...", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
							copyFile(xl, df, dialog.getFileName());
						}
						
						AdminComposite.display("Excel Saved Successfully", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						
					}
				
					
				} catch(Exception e) {	
					AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					e.printStackTrace();
				}	
			}
			}else{
				AdminComposite.display("Please Select Source Station", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			}
			
		}
		
			
	public void copyFile(File source, File dest, String fileName)
				throws IOException {

			if (!dest.exists()) {
				dest.createNewFile();
			} else {
				dest.setWritable(true);
				/*
				 * MessageBox mb = new MessageBox(cvs.getShell(),
				 * SWT.ICON_WARNING | SWT.YES | SWT.NO);
				 *  // We really should read this string from a // resource
				 * bundle mb.setMessage(fileName + " already exists. Do you want
				 * to replace it?");
				 * 
				 * if(SWT.YES){
				 *  }
				 */

				// If they click Yes, we're done and we drop out. If
				// they click No, we redisplay the File Dialog
				// done = mb.open() == SWT.YES;
			}

			InputStream in = null;

			OutputStream out = null;

			try {

				in = new FileInputStream(source);

				out = new FileOutputStream(dest);

				byte[] buf = new byte[1024];

				int len;

				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}

			}

			finally {
				if(in != null)
					in.close();				
				if(out != null)
					out.close();
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

		/**
		 * 
		 * @param code
		 * @param distance
		 * @throws Exception
		 */
		private void setDistanceDTO(String code, DistanceListDTO[] dto) throws Exception {
			if (null != handler) {
				handler.setDisanceList(code, dto);
			}

		}
		
		/**
		 * 
		 * @param stationcode
		 * @return
		 * @throws Exception
		 */
		private DistanceListDTO[] getDistanceDTO(String stationcode)
				throws Exception {
			DistanceListDTO[] dto = null;
			if (null != handler) {
				dto = handler.getDisanceList(stationcode);
				return dto;
			}

			return null;
		}

	}
	
	/**
	 * Method to check the station is city station. If it is city station , the branch will be returned.
	 * @param stationCode
	 * @return
	 */
	/*private String checkCityStation(String stationCode) {
		String station = null;
		try {
			StationsDTO[] stationsDTO = handler.getAllStations();
			
			for(int i = 0; i < stationsDTO.length; i++){
				if(stationsDTO[i].getId().equalsIgnoreCase(stationCode) && 
						 stationsDTO[i].getType().equalsIgnoreCase("City")){
					station = stationsDTO[i].getBranch_code();					
				}
			}
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		if(station == null){
			station = stationCode;
		}
		
		return station;
	}*/
	
	private float getBPI(String destStnCode) {
		float destBpi = 0;
		float fromBpi = 0;
		try {
			int index = cbSB.getSelectionIndex();
			String frmstnCode = null;
			if (index > -1) {
				frmstnCode = cbSB.getItem(index);
				index = frmstnCode.indexOf("-");
				frmstnCode = frmstnCode.substring(index + 2);
			}
			
			StationsDTO[] stations = handler.getAllStations();
			
			if(frmstnCode != null){
				for (int i = 0; i < stations.length; i++) {
					if (frmstnCode.equals(stations[i].getId())) {
						fromBpi = stations[i].getBpi();													
						break;
					}
				}
			}										
			
			if(stations != null){
				for (int i = 0; i < stations.length; i++) {
					if (destStnCode.equals(stations[i].getId())) {
						destBpi = stations[i].getBpi();													
						break;
					}
				}
			}
			
			if(fromBpi < destBpi){
				return destBpi;
			}else{
				return fromBpi;
			}
			
		} catch (Exception e) {										
			e.printStackTrace();
		}
	
		
		return 0;
	}
	
	private String getRoundedCeilValue(float cardRate) {		
		// cut five digits
		cardRate = ((float) (int) (cardRate * 100000)) / (float) 100000;
		//System.out.println("cr==>"+cardRate);
		cardRate = cardRate * 100;		
		cardRate = (float) Math.ceil(cardRate);
		cardRate = cardRate / 100;		
		//System.out.println("cr after==>"+cardRate);
		
		return decimalFormat.format(cardRate);
	}
	
	private void populateDistanceTableEdit() {			
		
		AdminComposite.display("",STATUS_SUCCESS, SUCCESS_FONT_COLOR);
		
		if (null != tblDistance) {
			TableItem[] items = tblDistance.getItems();
			
			
			
			TableEditor editor = null;
			txtList = new ArrayList<Text>();
			final int len = items.length;
				for (int i = 0; i < len; i++) {					
							
							//final TableItem item = new TableItem(tblDistance, SWT.NONE);	
							final TableItem item = items[i];
							item.setText(0, items[i].getText(0));
							item.setText(1, items[i].getText(1));
							item.setText(2, items[i].getText(2));
							item.setText(3, items[i].getText(3));
							item.setText(4, item.getText(4));
							item.setText(5, items[i].getText(5));	

							
							// Draw Value Text Field
							editor = new TableEditor(tblDistance);
							final Text txtValue = new Text(tblDistance, SWT.NONE);
							txtValue.addVerifyListener(new NumericValidation());								
							editor.grabHorizontal = true;
							editor.setEditor(txtValue, item, 4);				
							//item.setText(4, item.getText(4));
							txtValue.setText(item.getText(4));							
							
							txtList.add(txtValue);
							final int index = i; 
							txtValue.addFocusListener(new FocusListener(){

								@Override
								public void focusGained(FocusEvent arg0) {
									// TODO Auto-generated method stub
									
								}

								@Override
								public void focusLost(FocusEvent arg0) {
									try {
										float bpi = getBPI(item.getText(3));
										//System.out.println("bi==>"+bpi+"index==>"+index);
										String distance = txtValue.getText();
										float cardRate = 0;
										float finalRate = 0;
										int incr = 0;
										String strIncr = "";
										String strCardRate = "";
										
										if(distance.equals("")){
											distance = "0";
											txtValue.setText("0");
										}
										cardRate = bpi * Integer.parseInt(distance);
										//System.out.println("actual"+cardRate);
													
										strCardRate = getRoundedCeilValue(cardRate);
										item.setText(5, strCardRate);		
										item.setText(4, distance);					
										
										
										strIncr = item.getText(6);
										if(!strIncr.equals(""))
											incr = Integer.parseInt(strIncr);
													
										cardRate = Float.parseFloat(strCardRate);
										finalRate = (cardRate * incr / 100) + cardRate;
										item.setText(7, getRoundedCeilValue(finalRate));
										
										if(index != (len-1)){
											txtList.get(index+1).selectAll();
										}
										// Scrollbar									
										Rectangle rt = txtValue.getBounds();
										int yPosition = rt.y;									
										int topIndex = tblDistance.getTopIndex();
										int width = 0;									
										
										if(index != (len-1) && (yPosition < 470 && yPosition > 460)){
											tblDistance.setTopIndex(topIndex+15);
											tblDistance.setSelection(index+1);	
											
											width = tblDistance.getColumn(4).getWidth();
											tblDistance.getColumn(4).pack();
											tblDistance.getColumn(4).setWidth(width);
											tblDistance.redraw();
										}
									} catch (Exception e) {										
										e.printStackTrace();
									}
									
									}								
								
							});
							
							
							txtValue.addKeyListener(new KeyListener(){

								@Override
								public void keyPressed(KeyEvent arg0) {
									// TODO Auto-generated method stub
									
								}

								@Override
								public void keyReleased(KeyEvent event) {
									
									try {
										if(event.keyCode == 13){
										float bpi = getBPI(item.getText(3));								
										String distance = txtValue.getText();
										float cardRate = 0;
										float finalRate = 0;
										int incr = 0;
										String strIncr = "";
										String strCardRate = "";
										if(distance.equals("")){
											distance = "0";
											txtValue.setText("0");										
										}
										cardRate = bpi * Integer.parseInt(distance);									
										
										strCardRate = getRoundedCeilValue(cardRate);
										item.setText(5, strCardRate);		
										item.setText(4, distance);		
										
										strIncr = item.getText(6);
										if(!strIncr.equals(""))
											incr = Integer.parseInt(strIncr);
													
										cardRate = Float.parseFloat(strCardRate);
										finalRate = (cardRate * incr / 100) + cardRate;
										item.setText(7, getRoundedCeilValue(finalRate));
										
										// Scrollbar									
										Rectangle rt = txtValue.getBounds();
										int yPosition = rt.y;									
										int topIndex = tblDistance.getTopIndex();
										int width = 0;									
										//System.out.println(yPosition);
										if(index != (len-1) && (yPosition < 470 && yPosition > 460)){
											tblDistance.setTopIndex(topIndex+15);
											tblDistance.setSelection(index+1);	
											
											width = tblDistance.getColumn(4).getWidth();
											tblDistance.getColumn(4).pack();
											tblDistance.getColumn(4).setWidth(width);
											tblDistance.redraw();
										}
										
										if(index != (len-1)){
											txtList.get(index+1).setFocus();
											txtList.get(index+1).selectAll();
										}
										
										}
									} catch (Exception e) {										
										e.printStackTrace();
									}
								}
								
							});
							
								
						}

					}
				
		
	}

}
