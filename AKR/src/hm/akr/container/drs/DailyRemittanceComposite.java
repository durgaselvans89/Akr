package hm.akr.container.drs;

import hm.akr.common.AlphaNumericValidation;
import hm.akr.common.BeanUtil;
import hm.akr.common.FloatValidation;
import hm.akr.common.KalendarDialog;
import hm.akr.common.SWTResourceManager;
import hm.akr.dto.CashRegisterDTO;
import hm.akr.dto.DRSDTO;
import hm.akr.dto.printer.DRSPrinterDTO;
import hm.akr.exceptions.BusinessException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * 
 * 
 * @version 1.0
 */
public class DailyRemittanceComposite extends Composite {

	{
		// Register as a resource user - SWTResourceManager will
		// handle the obtaining and disposing of resources
		SWTResourceManager.registerResourceUser(this);
	}

	private Text txtStationName;

	private Text txtTo;

	private Button butOk;

	private Text txtGrand;

	private Text txtLess;

	private Text txtAdd;

	private Text txtTotal;

	private Label lblGrand;

	private Label lblLess;

	private Label lblAdd;

	// private Button btnScreen;

	private Button btnconfirm;

	private Label lblTotal;

	private Label lblSep5;

	private Label lblTopay;

	private Label lblPaid;

	private Text txtAmount2;

	private Text txtTo1;

	private Text txtFrom1;

	private Text txtAmount1;

	private Text txtFrom;

	private Label lblSep4;

	private Label lblSep3;

	private Label lblSep2;

	private Label lblTo;

	private Label lblFrom;

	private Label lblSep1;

	private Label lblSep;

	private Label lblAmount;

	private Label lblParticulars;

	private Canvas canvas2;

	private Label lblSep6;

	private Label lblStation;

	private Text txtDate;

	private Text txtStationcode;

	private Label lblStationcode;

	private Label lblDate;

	private Label lblNo;

	private Text txtNo;

	private Button btnPrint;

	private Label lblName;

	private Canvas canvas1;

	private Button btnCal;

	private Text txtDdr;

	private Label lblddr;

	private Shell shell;

	Color redColor = null;

	DailyRemittanceCompositeHandler handler = null;

	private String SERVER_DATE = null;

	CashRegisterDTO[] topay = null;

	CashRegisterDTO[] paid = null;

	CashRegisterDTO[] billing = null;
	
	/**
	 * Constructor method
	 * 
	 * @param shell
	 */
	public DailyRemittanceComposite(Shell shell, int swtValue) {
		super(shell, swtValue);
		this.shell = shell;

		try {
			handler = new DailyRemittanceCompositeHandler();
			SERVER_DATE = handler.getServerDate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to load daily remittance components
	 * 
	 * @return
	 */
	public Composite loadComposite() {
		try {
			this.setLayout(new FormLayout());
			this.setSize(1024, 768);
			this.setBackgroundMode(1);
			{
				btnconfirm = new Button(this, SWT.PUSH | SWT.CENTER);
				btnconfirm.setText("2. Confirm");
				FormData btnconfirmLData = new FormData(61, 23);
				btnconfirmLData.width = 61;
				btnconfirmLData.height = 23;
				btnconfirmLData.left = new FormAttachment(0, 1000, 867);
				btnconfirmLData.top = new FormAttachment(0, 1000, 671);
				btnconfirm.setLayoutData(btnconfirmLData);
				btnconfirm.setSize(61, 23);
				btnconfirm.addSelectionListener(new DRSListener());
			}
			{
				btnPrint = new Button(this, SWT.PUSH | SWT.CENTER);
				FormData btnPrintLData = new FormData();
				btnPrintLData.width = 60;
				btnPrintLData.height = 23;
				btnPrintLData.left = new FormAttachment(0, 1000, 795);
				btnPrintLData.top = new FormAttachment(0, 1000, 631);
				btnPrint.addSelectionListener(new DRSListener());
				btnPrint.setLayoutData(btnPrintLData);
				btnPrint.setText("Print");
			}
			{
				lblName = new Label(this, SWT.NONE);
				FormData lblNameLData = new FormData();
				lblNameLData.width = 449;
				lblNameLData.height = 32;
				lblNameLData.left = new FormAttachment(0, 1000, 557);
				lblNameLData.top = new FormAttachment(0, 1000, 20);
				lblName.setLayoutData(lblNameLData);
				lblName.setText("DAILY REMITTANCE STATEMENT");
				lblName.setFont(SWTResourceManager.getFont("Tahoma", 20, 1,
						false, false));
			}

			{
				btnCal = new Button(this, SWT.PUSH | SWT.CENTER);
				FormData btnCalLData = new FormData();
				btnCalLData.width = 25;
				btnCalLData.height = 23;
				btnCalLData.left = new FormAttachment(0, 1000, 927);
				btnCalLData.top = new FormAttachment(0, 1000, 107);
				btnCal.setLayoutData(btnCalLData);
				btnCal.setText("Go");
				btnCal.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						KalendarDialog cd = new KalendarDialog(shell);
						Object obj = cd.open();
						if (obj != null)
							txtDate.setText(obj.toString());
						clearAndEnableFields();
						populateDailyRemittance();

					}
				});

			}

			{
				butOk = new Button(this, SWT.PUSH | SWT.CENTER);
				FormData button1LData = new FormData();
				button1LData.width = 61;
				button1LData.height = 23;
				button1LData.left = new FormAttachment(0, 1000, 867);
				button1LData.top = new FormAttachment(0, 1000, 631);
				butOk.setLayoutData(button1LData);
				butOk.setText("1. Submit");

				butOk.addSelectionListener(new DRSListener());

			}
			{
				FormData canvas2LData = new FormData();
				canvas2LData.width = 806;
				canvas2LData.height = 372;
				canvas2LData.left = new FormAttachment(0, 1000, 116);
				canvas2LData.top = new FormAttachment(0, 1000, 246);
				canvas2 = new Canvas(this, SWT.BORDER);
				canvas2.setLayoutData(canvas2LData);
				{
					lblParticulars = new Label(canvas2, SWT.CENTER);
					lblParticulars.setText("PARTICULARS");
					lblParticulars.setBounds(204, 12, 132, 21);
					lblParticulars.setFont(SWTResourceManager.getFont(
							"Verdana", 10, 1, false, false));
				}
				{
					lblAmount = new Label(canvas2, SWT.CENTER);
					lblAmount.setText("AMOUNT");
					lblAmount.setBounds(637, 12, 132, 24);
					lblAmount.setFont(SWTResourceManager.getFont("Verdana", 10,
							1, false, false));
				}
				{
					lblSep = new Label(canvas2, SWT.SEPARATOR | SWT.HORIZONTAL);
					lblSep.setText("label11");
					lblSep.setBounds(1, 42, 804, 5);
				}
				{
					lblSep1 = new Label(canvas2, SWT.SEPARATOR);
					lblSep1.setText("label12");
					lblSep1.setBounds(573, -2, 7, 374);
				}
				{
					lblFrom = new Label(canvas2, SWT.LEFT);
					lblFrom.setText("FROM");
					lblFrom.setBounds(348, 61, 60, 15);
				}
				{
					lblTo = new Label(canvas2, SWT.LEFT);
					lblTo.setText("TO");
					lblTo.setBounds(489, 61, 60, 15);
				}
				{
					lblSep2 = new Label(canvas2, SWT.SEPARATOR);
					lblSep2.setText("label15");
					lblSep2.setBounds(424, 43, 8, 143);
				}
				{
					lblSep3 = new Label(canvas2, SWT.SEPARATOR);
					lblSep3.setText("label16");
					lblSep3.setBounds(271, 45, 6, 141);
				}
				{
					lblSep4 = new Label(canvas2, SWT.SEPARATOR | SWT.HORIZONTAL);
					lblSep4.setText("label17");
					lblSep4.setBounds(1, 134, 807, 5);
				}
				{
					txtFrom = new Text(canvas2, SWT.READ_ONLY | SWT.BORDER);
					txtFrom.setBounds(297, 97, 113, 25);
					txtFrom.addVerifyListener(new AlphaNumericValidation());

				}
				{
					txtTo = new Text(canvas2, SWT.READ_ONLY | SWT.BORDER);
					txtTo.setBounds(454, 95, 107, 25);
					txtTo.addVerifyListener(new AlphaNumericValidation());
				}
				{
					txtAmount1 = new Text(canvas2, SWT.READ_ONLY | SWT.BORDER);
					txtAmount1.setBounds(609, 94, 96, 23);
					txtAmount1.addVerifyListener(new FloatValidation());
					txtAmount1.addFocusListener(new DRSListener());
				}
				{
					txtFrom1 = new Text(canvas2, SWT.CENTER | SWT.LEFT
							| SWT.READ_ONLY | SWT.BORDER);
					txtFrom1.setBounds(296, 147, 113, 24);
					txtFrom1.addVerifyListener(new AlphaNumericValidation());
				}
				{
					txtTo1 = new Text(canvas2, SWT.LEFT | SWT.READ_ONLY
							| SWT.BORDER);
					txtTo1.setBounds(454, 149, 105, 24);
					txtTo1.addVerifyListener(new AlphaNumericValidation());
				}
				{
					txtAmount2 = new Text(canvas2, SWT.CENTER | SWT.LEFT
							| SWT.READ_ONLY | SWT.BORDER);
					txtAmount2.setBounds(611, 150, 96, 25);
					txtAmount2.addVerifyListener(new FloatValidation());
					txtAmount2.addFocusListener(new DRSListener());
				}
				{
					lblPaid = new Label(canvas2, SWT.RIGHT);
					lblPaid.setText("Paid Collection: Paid LR Nos.");
					lblPaid.setBounds(46, 97, 204, 30);
					lblPaid.setFont(SWTResourceManager.getFont("Tahoma", 9, 0,
							false, false));
				}
				{
					lblTopay = new Label(canvas2, SWT.RIGHT);
					lblTopay.setText("Cash Collection:Cash Receipt Nos. ");
					lblTopay.setBounds(46, 153, 198, 30);
					lblTopay.setFont(SWTResourceManager.getFont("Tahoma", 9, 0,
							false, false));
				}
				{
					lblSep5 = new Label(canvas2, SWT.SEPARATOR | SWT.HORIZONTAL);
					lblSep5.setText("label20");
					lblSep5.setBounds(2, 183, 804, 13);
				}
				{
					lblTotal = new Label(canvas2, SWT.RIGHT);
					lblTotal.setText("TOTAL");
					lblTotal.setBounds(478, 203, 60, 13);
				}
				{
					lblAdd = new Label(canvas2, SWT.RIGHT);
					lblAdd.setText("ADD REMITTANCE");
					lblAdd.setBounds(401, 238, 160, 12);

				}
				{
					lblLess = new Label(canvas2, SWT.RIGHT);
					lblLess.setText("LESS REMITTANCE");
					lblLess.setBounds(433, 276, 128, 14);
				}
				{
					lblGrand = new Label(canvas2, SWT.RIGHT);
					lblGrand.setText("GRAND TOTAL");
					lblGrand.setBounds(466, 344, 95, 13);
				}
				{
					txtTotal = new Text(canvas2, SWT.LEFT | SWT.BORDER);
					txtTotal.setBounds(609, 197, 100, 26);
					txtTotal.setEnabled(false);
				}
				{
					txtAdd = new Text(canvas2, SWT.LEFT | SWT.BORDER);
					txtAdd.setBounds(609, 231, 100, 27);
					txtAdd.setEnabled(false);

				}
				{
					txtLess = new Text(canvas2, SWT.LEFT | SWT.BORDER);
					txtLess.setBounds(610, 266, 100, 29);
					txtLess.setEnabled(false);

				}
				{
					txtGrand = new Text(canvas2, SWT.LEFT | SWT.BORDER);
					txtGrand.setBounds(609, 337, 100, 29);
					txtGrand.setEnabled(false);
				}
				{
					lblddr = new Label(canvas2, SWT.NONE);
					lblddr.setText("DELIVERY REIMBURSEMENT");
					lblddr.setBounds(428, 308, 137, 20);
				}
				{
					txtDdr = new Text(canvas2, SWT.READ_ONLY | SWT.BORDER);
					txtDdr.setBounds(609, 300, 100, 30);
					txtDdr.setTextLimit(10);
					txtDdr.addVerifyListener(new FloatValidation());
					txtDdr.addFocusListener(new DRSListener());
				}
			}
			{
				lblSep6 = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
				FormData label6LData = new FormData();
				label6LData.width = 803;
				label6LData.height = 13;
				label6LData.left = new FormAttachment(0, 1000, 114);
				label6LData.top = new FormAttachment(0, 1000, 225);
				lblSep6.setLayoutData(label6LData);
				lblSep6.setText("label6");
			}
			{
				txtStationcode = new Text(this, SWT.BORDER);
				FormData cCombo2LData = new FormData();
				cCombo2LData.width = 118;
				cCombo2LData.height = 17;
				cCombo2LData.left = new FormAttachment(0, 1000, 749);
				cCombo2LData.top = new FormAttachment(0, 1000, 196);
				txtStationcode.setLayoutData(cCombo2LData);
				txtStationcode.setEnabled(false);
				txtStationcode.setText(handler.getStationCode());
			}
			{
				lblStationcode = new Label(this, SWT.NONE);
				FormData label7LData = new FormData();
				label7LData.width = 62;
				label7LData.height = 13;
				label7LData.left = new FormAttachment(0, 1000, 659);
				label7LData.top = new FormAttachment(0, 1000, 198);
				lblStationcode.setLayoutData(label7LData);
				lblStationcode.setText("Station Code");
			}
			{
				lblDate = new Label(this, SWT.HORIZONTAL);
				FormData label5LData = new FormData();
				label5LData.width = 23;
				label5LData.height = 13;
				label5LData.left = new FormAttachment(0, 1000, 769);
				label5LData.top = new FormAttachment(0, 1000, 110);
				lblDate.setLayoutData(label5LData);
				lblDate.setText("Date");
			}
			{
				lblNo = new Label(this, SWT.HORIZONTAL);
				FormData label4LData = new FormData();
				label4LData.width = 19;
				label4LData.height = 13;
				label4LData.left = new FormAttachment(0, 1000, 697);
				label4LData.top = new FormAttachment(0, 1000, 168);
				lblNo.setLayoutData(label4LData);
				lblNo.setText("No");
			}
			{
				txtDate = new Text(this, SWT.BORDER | SWT.READ_ONLY);
				FormData text2LData = new FormData();
				text2LData.width = 101;
				text2LData.height = 16;
				text2LData.left = new FormAttachment(0, 1000, 806);
				text2LData.top = new FormAttachment(0, 1000, 107);
				txtDate.setLayoutData(text2LData);
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
								

				int MILLIS_IN_DAY = 1000 * 60 * 60 * 24;				
				String prevDate = "";
				
				if (SERVER_DATE != null) {					
					Date serverDate = dateFormat.parse(SERVER_DATE);
					prevDate = dateFormat.format(serverDate.getTime() - MILLIS_IN_DAY);
					txtDate.setText(prevDate);
				} else {
					Date currentDate = new Date();
					prevDate = dateFormat.format(currentDate.getTime() - MILLIS_IN_DAY);
					txtDate.setText(prevDate);
				}		
				
				
				
			}
			{
				txtNo = new Text(this, SWT.READ_ONLY | SWT.BORDER);
				FormData text1LData = new FormData();
				text1LData.width = 119;
				text1LData.height = 16;
				text1LData.left = new FormAttachment(0, 1000, 748);
				text1LData.top = new FormAttachment(0, 1000, 166);
				txtNo.setLayoutData(text1LData);
			}
			{
				lblStation = new Label(this, SWT.NONE);
				FormData label8LData = new FormData();
				label8LData.width = 64;
				label8LData.height = 13;
				label8LData.left = new FormAttachment(0, 1000, 137);
				label8LData.top = new FormAttachment(0, 1000, 198);
				lblStation.setLayoutData(label8LData);
				lblStation.setText("Station Name");
			}
			{
				txtStationName = new Text(this, SWT.BORDER);
				FormData cCombo3LData = new FormData();
				cCombo3LData.width = 223;
				cCombo3LData.height = 17;
				cCombo3LData.left = new FormAttachment(0, 1000, 233);
				cCombo3LData.top = new FormAttachment(0, 1000, 196);
				txtStationName.setLayoutData(cCombo3LData);
				txtStationName.setEnabled(false);
				txtStationName.setText(handler.getStationName());
				// txtStationName.setText("cCombo1");
			}
			populateDailyRemittance();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;

	}

	/**
	 * Method to refresh the fields
	 */

	private void clearAndEnableFields() {
		String empty = "";

		txtFrom.setText(empty);
		txtFrom1.setText(empty);
		txtTo.setText(empty);
		txtTo1.setText(empty);
		txtAmount1.setText(empty);
		txtAmount2.setText(empty);
		txtTotal.setText(empty);
		txtDdr.setText(empty);
		txtGrand.setText(empty);
		// errorLabel.setText(empty);
		txtNo.setText(empty);

	}

	/**
	 * Method to populate daily remittance
	 */
	private void populateDailyRemittance() {
		DRSDTO dto = null;
		boolean flag = false;
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = null;
		Date date1 = null;
		int monthDiff = 0;
		try {
			date = dateFormat.parse(txtDate.getText());
			Date curDate = new Date();			
			int m1 = date.getYear() * 12 + date.getMonth();
	        int m2 = curDate.getYear() * 12 + curDate.getMonth();
	        monthDiff = m2 - m1;

		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {

			if(monthDiff > 3){
				dto = handler.getDailyRemittanceStatementHistory(date);
			}else{
				if(BeanUtil.getDbName() == null || BeanUtil.getDbName().equals("")){
					dto = handler.getDailyRemittanceStatement(date);
				}
			}
			

			Date currentDate = new Date();
			String s1 = dateFormat.format(currentDate);
			date1 = dateFormat.parse(s1);
			if (date.equals(date1)) {
				flag = true;
				btnPrint.setEnabled(false);
				btnconfirm.setEnabled(false);
			}

			if (null != dto) {
				txtFrom.setEnabled(false);
				txtFrom1.setEnabled(false);
				txtTo1.setEnabled(false);
				txtTo.setEnabled(false);
				txtTotal.setEnabled(false);
				txtAmount1.setEnabled(false);
				txtAmount2.setEnabled(false);
				txtDdr.setEnabled(false);

				float total = dto.getPaid_collection_amount()
						+ dto.getTopay_collection_amount();
				if (null != dto.getNo())
					txtNo.setText(dto.getNo());
				if (null != dto.getFrom_LR_NO())
					txtFrom.setText(dto.getFrom_LR_NO());
				if (null != dto.getFrom_CR_NO())
					txtFrom1.setText(dto.getFrom_CR_NO());
				if (null != dto.getTo_LR_NO())
					txtTo.setText(dto.getTo_LR_NO());
				if (null != dto.getTo_CR_NO())
					txtTo1.setText(dto.getTo_CR_NO());
				txtTotal.setText(Float.toString(total));

				txtAmount1.setText(Float.toString(dto
						.getPaid_collection_amount()));

				txtAmount2.setText(Float.toString(dto
						.getTopay_collection_amount()));
				float grantTotal = total + dto.getAdd_remittance()
						- dto.getLess_remittance() - dto.getDd_reimbursement();

				txtGrand.setText(Float.toString(grantTotal));

				txtDdr.setText(Float.toString(dto.getDd_reimbursement()));
				txtAdd.setText(Float.toString(dto.getAdd_remittance()));
				txtLess.setText(Float.toString(dto.getLess_remittance()));
				if (dto.isStatus() && !flag) {
					btnconfirm.setEnabled(false);
					btnPrint.setEnabled(true);
					txtAdd.setEnabled(false);
					txtLess.setEnabled(false);
					butOk.setEnabled(false);
				} else if (!flag) {
					btnconfirm.setEnabled(true);
					btnPrint.setEnabled(false);
					// txtAdd.setEnabled(true);
					// txtLess.setEnabled(true);
					butOk.setEnabled(true);
				}

			}
		} catch (BusinessException businessexception) {
			displayError(businessexception.getResponseMessage());
		} catch (Exception exception) {
			exception.printStackTrace();
			displayError("Error occurred while populating the Daily Remittance");
		}

	}

	/**
	 * Method to buildDTO
	 */
	private DRSPrinterDTO buildPrinterDTO() {
		DRSPrinterDTO dto = new DRSPrinterDTO();
		dto.setAdd_remittance(Float.parseFloat(txtAdd.getText()));
		dto.setDate(txtDate.getText());
		dto.setGrand_total(Float.parseFloat(txtGrand.getText()));
		dto.setLess_remittance(Float.parseFloat(txtDdr.getText()));
		dto.setNo(txtNo.getText());
		dto.setPaid_collection_amount(Float.parseFloat(txtAmount1.getText()));
		dto.setPaid_collection_from(txtFrom.getText());
		dto.setPaid_collection_to(txtTo.getText());
		dto.setStation_code(txtStationcode.getText());
		dto.setStation_name(txtStationName.getText());
		dto.setTopay_collection_amount(Float.parseFloat(txtAmount2.getText()));
		dto.setTopay_collection_from(txtFrom1.getText());
		dto.setTopay_collection_to(txtTo1.getText());
		dto.setTotal(Float.parseFloat(txtTotal.getText()));
		return dto;
	}

	/**
	 * Method to build DTO
	 */
	private DRSDTO buildDTO() {
		DRSDTO drs = new DRSDTO();
		drs.setNo(txtNo.getText());
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = null;
		try {
			date = dateFormat.parse(txtDate.getText());
			//System.out.println("dt-->"+date);
		} catch (ParseException e) {
			displayError("Mandatory parameter(s) missing");
		}
		drs.setDate(date);
		drs.setDd_reimbursement(Float.parseFloat(txtDdr.getText()));
		drs.setStation_code(txtStationcode.getText());
		drs.setPaid_collection_amount(Float.parseFloat(txtAmount1.getText()));
		drs.setTopay_collection_amount(Float.parseFloat(txtAmount2.getText()));
		drs.setAdd_remittance(Float.parseFloat(txtAdd.getText()));
		drs.setLess_remittance(Float.parseFloat(txtLess.getText()));
		drs.setGrand_total(Float.parseFloat(txtGrand.getText()));

		return drs;
	}

	/**
	 * Method to validate Data
	 * 
	 * @param drs
	 * @return
	 */
	private boolean validateData(DRSDTO drs) {

		/*if (drs.getNo().length() == 0 || drs.getGrand_total() == 0
				|| txtAmount1.getText().length() == 0
				|| txtAmount2.getText().length() == 0
				|| txtDdr.getText().length() == 0) {
			displayError("Mandatory parameter(s) missing");
			return false;
		}*/
		return true;
	}

	/**
	 * Method to show the user Message
	 * 
	 * @param errorText
	 */
	private void displayError(String errorText) {
		MessageBox messageBox = new MessageBox(shell, SWT.OK);
		messageBox.setMessage(errorText);
		messageBox.setText("Alert");
		messageBox.open();
	}

	/**
	 * Inner class for DRS listeners
	 * 
	 * @author Administrator
	 * 
	 */
	public class DRSListener implements SelectionListener, FocusListener {

		public void widgetSelected(SelectionEvent event) {
			Object source = event.getSource();

			if (btnPrint == source) {

				try {
					DRSPrinterDTO printerDTO = buildPrinterDTO();
					handler.printDRS(printerDTO);
				} catch (Exception exception) {
					displayError("Error While printing");
				}

			}

			else if (butOk == source) {
				try {
					
						DRSDTO drs = buildDTO();
						if (validateData(drs)) {
							handler = new DailyRemittanceCompositeHandler();
							handler.submitDRSForm(drs);
							displayError("Booked Successfully");
						}					
					
				} catch (BusinessException business) {
					displayError(business.getResponseMessage());

				} catch (Exception exception) {
					exception.printStackTrace();
					displayError("Error Occurred while submitting the DRS");
				}
			} else if (btnconfirm == source) {
				try {
					// Check CR
					int isCRSubmitted = 0;

					isCRSubmitted = checkAvailCR();

					if (isCRSubmitted == 4) {
						MessageBox messageBox = new MessageBox(shell, SWT.NO
								| SWT.YES);
						messageBox.setMessage("Are want to confirm this? ");
						messageBox.setText("Warning");
						if (messageBox.open() == SWT.YES) {
							try {
								DRSDTO drs = buildDTO();
								drs.setStatus(true);
								if (validateData(drs)) {
									handler = new DailyRemittanceCompositeHandler();
									handler.submitDRSForm(drs);
									displayError("Confirmation Successfull");
									btnconfirm.setEnabled(false);
									btnPrint.setEnabled(true);
									butOk.setEnabled(false);
								}
							} catch (BusinessException businessexception) {
								displayError(businessexception
										.getResponseMessage());
							} catch (Exception exception) {
								displayError("Error Occurred while submitting the DRS");
								exception.printStackTrace();
							}
						} else {
							displayError("Confimation Cancelled");
						}
					} else {
						displayError("A few CRs are not yet submitted. Kindly submit them now. \n" +
								"Note: If you have to cancel any CR, do so before submitting the CR");
						if (isCRSubmitted == 1) {
							handleCRpop("TOPAY");
						} else if (isCRSubmitted == 2) {
							handleCRpop("PAID");
						} else if (isCRSubmitted == 3) {
							handleCRpop("BILLING");
						}
						populateDailyRemittance();
					}
				} catch (Exception e) {				
					e.printStackTrace();
				}
			}

		}

		private void handleCRpop(String type) throws Exception {
			Shell shl = new Shell(shell, SWT.PRIMARY_MODAL
					| SWT.TOP);
			Composite composite = null;

			if (null != composite && !composite.isDisposed()) {
				composite.dispose();
			}

			CashRegisterCompositePop cr = (new CashRegisterCompositePop(shl, SWT.BORDER, type));
			composite = cr.loadComposite();			

		}

		private int checkAvailCR() throws Exception{
			
			topay = handler.getLRList("TOPAY");
			if(topay != null && topay.length > 0){
				//System.out.println("t->"+topay.length);
				return 1;
			}
			
			paid = handler.getLRList("PAID");
			if(paid != null && paid.length > 0){
				//System.out.println("p->"+paid.length);
				return 2;
			}
			
			billing = handler.getLRList("BILLING");
			if(billing != null && billing.length > 0){
				//System.out.println("b->"+billing.length);
				return 3;
			}
			
			return 4;
		}

		/**
		 * 
		 */
		public void widgetDefaultSelected(SelectionEvent event) {
			// Object source = event.getSource();
			try {
				/*
				 * if(btnView ==source) { populateDailyRemittance(); }
				 */
			} catch (Exception exception) {
				exception.printStackTrace();

			}

		}

		public void focusGained(org.eclipse.swt.events.FocusEvent arg0) {
			// TODO Auto-generated method stub

		}

		/**
		 * 
		 */
		public void focusLost(org.eclipse.swt.events.FocusEvent arg0) {

			float total = 0;

			String temp = txtAmount1.getText();
			if (temp.length() > 0)
				total += Float.parseFloat(temp);

			temp = txtAmount2.getText();
			if (temp.length() > 0)
				total += Float.parseFloat(temp);

			txtTotal.setText(String.valueOf(total));

			temp = txtAdd.getText();
			if (temp.length() > 0)
				total += Float.parseFloat(temp);

			temp = txtLess.getText();
			if (temp.length() > 0)
				total -= Float.parseFloat(temp);

			temp = txtDdr.getText();
			if (temp.length() > 0)
				total -= Float.parseFloat(temp);

			txtGrand.setText(String.valueOf(total));
		}

	}
}
