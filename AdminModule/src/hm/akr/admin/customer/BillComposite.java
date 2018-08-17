/**
 * 
 */
package hm.akr.admin.customer;

import hm.akr.admin.customer.handler.CustomerCompositeHandler;
import hm.akr.admin.workspace.AdminComposite;
import hm.akr.admin.workspace.SWTResourceManager;
import hm.akr.common.IUIConstants;
import hm.akr.common.MonthDialog;
import hm.akr.dto.BookingDTO;
import hm.akr.dto.CustomerDTO;
import hm.akr.dto.StationsDTO;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

/**
 * @author
 * 
 */
public class BillComposite extends Composite implements IUIConstants {

	private Combo cbCustomer;

	private Group gpMonth;

	private Text txtMonth;

	private Button btnMonth;

	private Label lblStation;

	private Label lblCustomer;

	private Combo cbStation;

	private Button btnPrint;

	private Button btnGo;

	private Table tblBill;

	private TableColumn colSNo;

	private TableColumn colLrno;

	private TableColumn colDate;

	private TableColumn colNOA;

	private TableColumn colDestination;

	private TableColumn colCW;

	private TableColumn colFreight;

	private TableColumn colLRC;

	private TableColumn colCCC;	

	private TableColumn colDDC;

	private TableColumn colGSC;

	private TableColumn colIEC;

	private TableColumn colLC;

	private TableColumn colTotal;

	private TableColumn colDeliveryDate;

	private TableColumn colInvoiceNo;

	CustomerCompositeHandler handler = null;

	CustomerDTO[] customerDTO = null;

	BookingDTO[] bookingdto = null;

	private TableColumn colOthers;
	
	private byte basicFormat = 0;
	private byte addOnFormat1 = 0;
	private byte addOnFormat2 = 0;
	private byte addOnFormat3 = 0;

	/**
	 * @throws Exception
	 * 
	 */
	public BillComposite(Composite parent, int swtValue) throws Exception {
		super(parent, swtValue);
		handler = new CustomerCompositeHandler();
	}

	public Composite loadComposite() throws Exception {

		this.setSize(900, 500);

		{
			cbStation = new Combo(this, SWT.NONE);
			cbStation.setBounds(11, 71, 122, 21);

			cbStation.addKeyListener(new KeyListener() {

				@Override
				public void keyPressed(KeyEvent e) {

					String selectedText = cbStation.getText();
					if (e.keyCode == SWT.ARROW_UP || e.keyCode == SWT.ARROW_DOWN || e.keyCode == SWT.CR)
						return;

					if (e.character == '\b') {
						int len = selectedText.length();

						if (len > 1)
							selectedText = selectedText.substring(0, len - 2);
						else
							selectedText = "";

						cbStation.remove(0, cbStation.getItemCount() - 1);
						populateDestStationCodes();
					} else if (e.keyCode == 32 || (e.keyCode > 64 && e.keyCode < 91)
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
						handleStationCombo();
					}
				}

			});

			cbStation.addSelectionListener(new CustomerListener());
			populateDestStationCodes();

			customerDTO = handler.getCustomerBookingType();

		}

		{
			cbCustomer = new Combo(this, SWT.READ_ONLY);
			cbCustomer.setBounds(145, 71, 123, 21);

		}

		{
			gpMonth = new Group(this, SWT.NONE);
			gpMonth.setText("Month");
			gpMonth.setBounds(279, 54, 140, 49);
		}

		{
			txtMonth = new Text(gpMonth, SWT.BORDER);
			txtMonth.setEnabled(false);
			DateFormat dateFormat = new SimpleDateFormat("MM-yyyy");
			java.util.Date currentDate = new java.util.Date();
			String date = dateFormat.format(currentDate);
			txtMonth.setText(date);
			txtMonth.setBounds(5, 19, 57, 19);
		}
		{
			btnMonth = new Button(gpMonth, SWT.PUSH | SWT.CENTER);
			btnMonth.setImage(SWTResourceManager.getImage("hm/akr/resources/Calendar.jpg"));
			btnMonth.setBounds(69, 16, 26, 23);
			// btnMonth.setBounds(627, 100, 27, 23);
			btnMonth.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {

				}

				@Override
				public void widgetSelected(SelectionEvent event) {
					MonthDialog cd = new MonthDialog(Display.getCurrent().getActiveShell());
					Object obj = cd.open();
					if (obj != null) {
						txtMonth.setText(obj.toString());
					}
				}

			});
		}
		{
			btnGo = new Button(gpMonth, SWT.PUSH | SWT.CENTER);
			btnGo.setText("Go");
			btnGo.setBounds(100, 16, 30, 24);
			btnGo.addSelectionListener(new CustomerListener());
		}

		{
			btnPrint = new Button(this, SWT.PUSH | SWT.CENTER);
			btnPrint.setText("Print");
			btnPrint.setBounds(426, 68, 45, 24);
			btnPrint.addSelectionListener(new CustomerListener());
		}
		
		// Bill Table
		tblBill = new Table(this, SWT.H_SCROLL | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.BORDER | SWT.MULTI);
		tblBill.setHeaderVisible(true);
		tblBill.setLinesVisible(true);
		tblBill.setBounds(0, 125, 829, 350);
		
		{
			lblCustomer = new Label(this, SWT.NONE);
			lblCustomer.setText("Customer");
			lblCustomer.setBounds(148, 47, 121, 21);
		}
		{
			lblStation = new Label(this, SWT.NONE);
			lblStation.setText("Station");
			lblStation.setBounds(13, 48, 121, 19);
		}

		{
			colSNo = new TableColumn(tblBill, SWT.NONE);
			colSNo.setText("SNo");
			colSNo.setWidth(34);

		}
		//width Lr-43,date-40,noa-37,dest-70,cw-32,freight-49,others-50;
		{
			colLrno = new TableColumn(tblBill, SWT.NONE);
			colLrno.setText("LR No");
			colLrno.setWidth(50);
		}
		{
			colDate = new TableColumn(tblBill, SWT.NONE);
			colDate.setText("Date");
			colDate.setWidth(68);
		}
		{
			colNOA = new TableColumn(tblBill, SWT.NONE);
			colNOA.setText("NOA");
			colNOA.setWidth(37);
		}
		{
			colDestination = new TableColumn(tblBill, SWT.NONE);
			colDestination.setText("Destination");
			colDestination.setWidth(70);
		}
		{
			colCW = new TableColumn(tblBill, SWT.NONE);
			colCW.setText("CW");
			colCW.setWidth(50);
		}
		{
			colFreight = new TableColumn(tblBill, SWT.NONE);
			colFreight.setText("Freight");
			colFreight.setWidth(53);
		}
		{
			colOthers = new TableColumn(tblBill, SWT.NONE);
			colOthers.setText("Others");
			colOthers.setWidth(55);
		}

		{
			{
				colLRC = new TableColumn(tblBill, SWT.NONE);
				colLRC.setText("LRC");

			}

			{
				colCCC = new TableColumn(tblBill, SWT.NONE);
				colCCC.setText("CCC");
				colCCC.setWidth(0);
				colCCC.setResizable(false);
			}
			
			{
				colDDC = new TableColumn(tblBill, SWT.NONE);
				colDDC.setText("DDC");
				colDDC.setWidth(0);
				colDDC.setResizable(false);
			}
			{
				colGSC = new TableColumn(tblBill, SWT.NONE);
				colGSC.setText("GSC");
				colGSC.setWidth(0);
				colGSC.setResizable(false);
			}
			{
				colIEC = new TableColumn(tblBill, SWT.NONE);
				colIEC.setText("IEC");
				colIEC.setWidth(0);
				colIEC.setResizable(false);
			}
			{
				colLC = new TableColumn(tblBill, SWT.NONE);
				colLC.setText("LC");
				colLC.setWidth(0);
				colLC.setResizable(false);
			}			
		}
		{
			colTotal = new TableColumn(tblBill, SWT.NONE);
			colTotal.setText("Total");
			colTotal.setWidth(55);
		}
		{
			colDeliveryDate = new TableColumn(tblBill, SWT.NONE);
			colDeliveryDate.setText("Delivery Date");
			colDeliveryDate.setWidth(0);
			colDeliveryDate.setResizable(false);
		}
		{
			colInvoiceNo = new TableColumn(tblBill, SWT.NONE);
			colInvoiceNo.setText("Invoice No");
			colInvoiceNo.setWidth(0);
			colInvoiceNo.setResizable(false);
		}

		return this;
	}

	/**
	 * 
	 * @author
	 * 
	 */
	public class CustomerListener implements SelectionListener {

		private DecimalFormat decimalFormat;

		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {

		}

		@Override
		public void widgetSelected(SelectionEvent event) {
			Object source = event.getSource();
			String stationcode = null;
			
			
			if (source == cbStation) {
				int index = cbStation.getSelectionIndex();
				if (index > -1) {
					handleStationCombo();
				}
			} else if (source == btnGo) {
				int index = cbStation.getSelectionIndex();
				if (index > -1) {
					stationcode = cbStation.getItem(index);
					index = stationcode.indexOf("-");
					stationcode = stationcode.substring(index + 2);
					index = cbCustomer.getSelectionIndex();
					if (index > -1) {
						String customername = cbCustomer.getItem(index);
						boolean customertype = false;
						

						if (null != customerDTO) {
							for (int i = 0; i < customerDTO.length; i++) {
								if (customerDTO[i].getStation().equals(stationcode)
										&& customerDTO[i].getName().equals(customername)) {
									customertype = customerDTO[i].isBookingType();
									basicFormat = customerDTO[i].getBasicFormat();
									addOnFormat1 = customerDTO[i].getAddOnFormat1();
									addOnFormat2 = customerDTO[i].getAddOnFormat2();
									addOnFormat3 = customerDTO[i].getAddOnFormat3();

									break;
								}
							}
						}
						if (null != handler) {

							try {

								String year = txtMonth.getText();
								int index1 = year.indexOf("-");
								String month = year.substring(0, index1);
								year = year.substring(index1 + 1);

								// Call the Method to get all LR details for
								// selected Customer
								// Call the Method to populate Billing Table

								bookingdto = handler.getBillingDetails(customername, stationcode, month, year,
										customertype);
								
							} catch (Exception e) {
								e.printStackTrace();
							}
							if(tblBill != null){
								tblBill.removeAll();
							}
							if (bookingdto != null && bookingdto.length > 0) {
								tblBill.redraw();
								populateLRDetails(bookingdto, basicFormat, addOnFormat1, addOnFormat2, addOnFormat3);
								btnPrint.setEnabled(true);
							}else{
								btnPrint.setEnabled(false);	
								AdminComposite.display("No Records Available", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
							};

						}

					}
				}
			} else if (source == btnPrint) {
				if (null != bookingdto) {
					try {						
						handler.printViewBill(bookingdto, basicFormat, addOnFormat1, addOnFormat2);
						btnPrint.setEnabled(false);
						AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					} catch (Exception e) {
						AdminComposite.display("Probem while printing", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						e.printStackTrace();
					}
				}
			}

		}

		/**
		 * Method to populate all LR details for given Month
		 * @param dto
		 */
		private void populateLRDetails(BookingDTO[] tempdto, byte basicFormat, byte addOn1, byte addOn2, byte addOn3) {
			float othersTotal = 0;
			BookingDTO[] bookingDto = null;
			System.out.println("basic==>" + basicFormat + " addon1==>" + addOn1 + " addOn2==>" + addOn2);
			AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			formatChanges(basicFormat, addOn1, addOn2, addOn3);
			
			// Add-On Format3			
			if(addOn3 == 1){
				//For sorting
				bookingDto = getSortedDTO(tempdto);
			}else{
				bookingDto = tempdto;
			}			
			
			if (null != tempdto) {
				SimpleDateFormat simple = new SimpleDateFormat("dd-MM-yyyy");
				decimalFormat = new DecimalFormat("0.00");

				for (int j = 0; j < bookingDto.length; j++) {
					TableItem item = new TableItem(tblBill, SWT.NONE);
					item.setText(0, String.valueOf(j + 1));					
					item.setText(1, bookingDto[j].getLrno());
					item.setText(2, simple.format(bookingDto[j].getDate()));
					item.setText(3, String.valueOf(bookingDto[j].getNo_of_articles()));
					item.setText(4, String.valueOf(bookingDto[j].getTo()));
					item.setText(5, decimalFormat.format(bookingDto[j].getCharged_weight()));
					item.setText(6, decimalFormat.format(bookingDto[j].getBft()));

					if (basicFormat == 1) {
						item.setText(8, decimalFormat.format(bookingDto[j].getLrc()));
						item.setText(9, decimalFormat.format(bookingDto[j].getCcc()));					
						item.setText(10, decimalFormat.format(bookingDto[j].getDdc()));
						item.setText(11, decimalFormat.format(bookingDto[j].getGsc()));
						item.setText(12, decimalFormat.format(bookingDto[j].getIec()));
						item.setText(13, decimalFormat.format(bookingDto[j].getLc()));						
					} else if (basicFormat == 0) {
						othersTotal = bookingDto[j].getLrc() + bookingDto[j].getCcc() + bookingDto[j].getDdc()  
									+ bookingDto[j].getGsc() + bookingDto[j].getIec() + bookingDto[j].getLc();									
						//others = others + total;
						bookingDto[j].setOther_charges(othersTotal);
						item.setText(7, decimalFormat.format(othersTotal));
					}

					item.setText(14, decimalFormat.format(bookingDto[j].getTotal()));

					if (addOn1 == 1) {						
						item.setText(15, simple.format(bookingDto[j].getDeliveredDate()));
					}
					
					if (addOn2 == 1) {						
						item.setText(16, String.valueOf(bookingDto[j].getInvoiceNo()));
					} 					
				}
			}
		}		

		/**
		 * 
		 * @param dto
		 * @return
		 */
		private BookingDTO[] getSortedDTO(BookingDTO[] dto) {

			BookingDTO[] tempdto = new BookingDTO[dto.length];
			int isless = 0;
			for (int i = 0; i < dto.length; i++) {
				if (dto[i].getTotal() <= 750) {					
					tempdto[isless++] = dto[i];					
				}
			}
			for (int i = 0; i < dto.length; i++) {
				if (dto[i].getTotal() > 750) {					
					tempdto[isless++] = dto[i];					
				}
			}

			return tempdto;
		}		

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

	/**
	 * Method to handle station combo action
	 * @param stationCode
	 */
	private void handleStationCombo() {
		String stationCode = cbStation.getText();
		int index = stationCode.indexOf("-");
		stationCode = stationCode.substring(index + 2);
		if (null != customerDTO) {
			cbCustomer.removeAll();
			for (int i = 0; i < customerDTO.length; i++) {
				if (customerDTO[i].getStation().equals(stationCode)) {
					cbCustomer.add(customerDTO[i].getName());
				}
			}
			
			String items[] = cbCustomer.getItems();
			if(items != null){
				Arrays.sort(items, String.CASE_INSENSITIVE_ORDER);
				cbCustomer.removeAll();
				cbCustomer.setItems(items);
			}
		}
	}
	
	/**
	 * 
	 * @param basicFormat
	 * @param addOn1
	 * @param addOn2
	 * @param addOn3
	 */
	private void formatChanges(byte basicFormat, byte addOn1, byte addOn2, byte addOn3) {
		
		
		tblBill.setBounds(0, 125, 829, 350);
		
		if (basicFormat == 1) {
			Rectangle rt = tblBill.getBounds();
			if(addOn1 ==0 && addOn2 == 0){				
				tblBill.setSize(rt.width-70, rt.height);
			}
			// Basic Format2
			//tblBill.setSize(829, rt.height);
			showColumns();
		} else {
			// Basic Format1
			//tblBill.setSize(553, rt.height);
			hideColumns();
		}				
		
		if (addOn1 == 1) {
			// Add-On Format1
			//tblBill.setSize(rt.width+70, rt.height);
			if(addOn2 == 0 || basicFormat == 0){
				colDeliveryDate.setWidth(80);
			}else{
				colDeliveryDate.setWidth(70);
			}
			
			colDeliveryDate.setResizable(true);					
		} else {
			
			colDeliveryDate.setWidth(0);
			colDeliveryDate.setResizable(false);
			
			Rectangle rt = tblBill.getBounds();
			
			if(basicFormat==1){
				colDate.setWidth(68);
				colCW.setWidth(65);
				colTotal.setWidth(65);				
				colIEC.setWidth(65);
				colLC.setWidth(65);				
			}else{				
				tblBill.setSize(rt.width-70, rt.height);
			}
		}

		if (addOn2 == 1) {
			//tblBill.setSize(rt.width+66, rt.height);
			// Add-On Format2
			colInvoiceNo.setWidth(66);
			colInvoiceNo.setResizable(true);					
		} else {
			
			colInvoiceNo.setWidth(0);
			colInvoiceNo.setResizable(false);
			
			Rectangle rt = tblBill.getBounds();
			if(basicFormat==1){
				colDate.setWidth(68);				
				colCW.setWidth(65);
				colTotal.setWidth(65);
				colIEC.setWidth(60);
				colLC.setWidth(60);				
			}else{				
				tblBill.setSize(rt.width-60, rt.height);
			}
			
		}				
		
	}

	/**
	 * Method to enable/disable columns based on formats
	 * @param basic
	 * @param addOn1
	 * @param addOn2
	 */
	private void showColumns() {
		
		// Width LRC-34,CCC-36,DDC-36,GSC-35,IEC-32,LC-27,SC-28
		
		refreshColumns();
		
		colLRC.setWidth(45);
		colLRC.setResizable(true);

		colCCC.setWidth(45);
		colCCC.setResizable(true);
		
		colDDC.setWidth(45);
		colDDC.setResizable(true);

		colGSC.setWidth(45);
		colGSC.setResizable(true);

		colIEC.setWidth(45);
		colIEC.setResizable(true);

		colLC.setWidth(45);
		colLC.setResizable(true);		

		colOthers.setWidth(0);
		colOthers.setResizable(false);
		
	}

	private void refreshColumns() {
		colLrno.setWidth(50);
		colDate.setWidth(68);
		colNOA.setWidth(37);
		colDestination.setWidth(70);
		colCW.setWidth(50);
		colFreight.setWidth(53);
		colTotal.setWidth(55);
		
	}

	private void hideColumns() {
		// Width LRC-34,CCC-36,DCC-36,DDC-36,GSC-35,IEC-32,LC-27,Insurance-63,SC-28 :327-others(50):277
	
		//width Lr-43+50,date-40+50,noa-37,dest-70,cw-32+50,freight-49+50,others-50+27,total-50+50;
		
		refreshColumns();
		
		colLRC.setWidth(0);
		colLRC.setResizable(false);

		colCCC.setWidth(0);
		colCCC.setResizable(false);
		
		colDDC.setWidth(0);
		colDDC.setResizable(false);

		colGSC.setWidth(0);
		colGSC.setResizable(false);

		colIEC.setWidth(0);
		colIEC.setResizable(false);

		colLC.setWidth(0);
		colLC.setResizable(false);		
		
		colLrno.setWidth(93);
		colDate.setWidth(90);		
		colCW.setWidth(82);
		colFreight.setWidth(99);
		colTotal.setWidth(97);

		colOthers.setWidth(77);
		colOthers.setResizable(true);
	}

}
