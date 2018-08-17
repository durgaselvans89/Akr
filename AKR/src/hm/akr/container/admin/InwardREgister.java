package hm.akr.container.admin;

import java.io.File;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import hm.akr.common.BeanUtil;
import hm.akr.common.IUIConstants;
import hm.akr.common.KalendarDialog;
import hm.akr.common.SWTResourceManager;
import hm.akr.container.commission.CommissionCompositeHandler;
import hm.akr.container.history.HistoryHandler;
import hm.akr.common.sortListener;
import hm.akr.dto.BookingDTO;
import hm.akr.dto.GMROutTimeDTO;
import hm.akr.dto.StationsDTO;
import hm.akr.dto.VersionDTO;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class InwardREgister extends ReportsMenu implements SelectionListener,IUIConstants,Listener {

	private TabItem tiInwardRegister;
	private Table tblInwardRegister;
	private Canvas canIR;
	private GMROutTimeDTO[] irDto = null;
	private String irST;
	private TableColumn colSNo;
	private TableColumn colLr;
	private TableColumn colDate;
	private TableColumn  colSentDate;
	private TableColumn colLRType;
	private TableColumn colActualWeight;
	private TableColumn colCrgWeight;
	private TableColumn colNoOfArticles;
	private TableColumn colFrom;
	private TableColumn colTo;
	private TableColumn colSignature;
	private TableColumn colBft;
	private TableColumn colConsignee;
	private TableColumn colCC;
	private TableColumn colTotalFreight;
	private TableColumn colOtherCharges;
	private TableColumn colArrivalVehicle;
	private Text txtDate;
	private Label label3;
	private Text txtToDate;
	private Label label4;
	private Button btnRepDate;
	private Button btnRepToDate;
	private Text txtSta;
	private Label lblSta;
	private Button btnGo;
	
	private String EMPTY_STRING = "";
	int temp;
	private String DATE_FORMAT = "dd-MM-yyyy";


	
	private Display display;
	BookingDTO[] outstandingdto = null;
	VersionDTO[] dto = null;
	private TabFolder tabReport = null;
	private Label lblStatusBar;
	private Shell shell = null;
	DateFormat viewdateformat = null;
	private Button btnPrint;
	private Button btnExportXL;
	private Button btnExportPDF;
	private ReportsMenuHandler handler;
	HistoryHandler historyH = null;
	private String SERVER_DATE = null;
	private CommissionCompositeHandler comnHandler = null;
	private DecimalFormat decimalFormat;
	//private BeanUtil beanutil  = null;;
	
	private Combo cbIRBranch;
	private Combo cbIRStation;
	private Label lblIRBranch;
	private Label lblIRStation;
	
	private String currentStationName = "";
	private String currentStationCode = "";
	
	
	public InwardREgister(Composite composite, int swtValue) {
		super(composite, swtValue);
		
		try {
			currentStationName = beanutil.getActingStationName();
			currentStationCode = beanutil.getActingStationCode();
			shell = composite.getShell();
			handler = new ReportsMenuHandler();
			comnHandler = new CommissionCompositeHandler();
			SERVER_DATE = handler.getServerDate();
			beanutil = BeanUtil.getInstance();
			decimalFormat = new DecimalFormat("0.00");
			viewdateformat = new SimpleDateFormat("dd-MM-yyyy");
			historyH = new HistoryHandler();
			dto = historyH.getHistoryYears();
			if(dto != null && dto.length > 0){
				BeanUtil.setThree_month_updated(dto[0].getUpdated_date());
			}
		} catch (Exception e) {
				e.printStackTrace();
		}
	}
	
	
	
	public Composite loadcomposite() {
		
		this.setBounds(45,100, 1020,700);
		tabReport = new TabFolder(this, SWT.NONE);
		tabReport.setBounds(30, 15, 870, 540);
		tabReport.addSelectionListener(this);
		
		
		if ( BeanUtil.getDbName() == null || BeanUtil.getDbName().equals("")){
			tiInwardRegister = new TabItem(tabReport, SWT.NONE);
			tiInwardRegister.setText("Inward Register");
			{
				canIR = new Canvas(tabReport, SWT.NONE);
				tiInwardRegister.setControl(canIR);
				{
					tblInwardRegister = new Table(canIR,
							SWT.FULL_SELECTION | SWT.H_SCROLL
									| SWT.V_SCROLL | SWT.BORDER);

					tblInwardRegister.setHeaderVisible(true);
					tblInwardRegister.setLinesVisible(true);
					tblInwardRegister.setBounds(5, 40, 850, 470);
					{
						colSNo = new TableColumn(tblInwardRegister,
								SWT.NONE);
						colSNo.setText("S.No");
						colSNo.setWidth(40);
					}
					{
						colLr = new TableColumn(tblInwardRegister,
								SWT.NONE);
						colLr.setText("LR No.");
						colLr.setWidth(59);
					}
					{
						colDate = new TableColumn(tblInwardRegister,
								SWT.NONE);
						colDate.setText("LR Date");
						colDate.setWidth(71);
					}
					{
						colLRType = new TableColumn(tblInwardRegister,
								SWT.NONE);
						colLRType.setText("LR Type");
						colLRType.setWidth(86);
					}
					{
						colSentDate = new TableColumn(
								tblInwardRegister, SWT.NONE);
						colSentDate.setText("Sent Date");
						colSentDate.setWidth(75);
					}
					{
						colTotalFreight = new TableColumn(
								tblInwardRegister, SWT.NONE);
						colTotalFreight.setText("Total Freight");
						colTotalFreight.setWidth(82);
					}

					{
						colFrom = new TableColumn(tblInwardRegister,
								SWT.NONE);
						colFrom.setText("From");
						colFrom.setWidth(71);
					}

					{
						colNoOfArticles = new TableColumn(
								tblInwardRegister, SWT.NONE);
						colNoOfArticles.setText("NOA");
						colNoOfArticles.setWidth(75);
					}

					{
						colCrgWeight = new TableColumn(
								tblInwardRegister, SWT.NONE);
						colCrgWeight.setText("Crg Wt");
						colCrgWeight.setWidth(71);
					}
					{
						colArrivalVehicle = new TableColumn(
								tblInwardRegister, SWT.NONE);
						colArrivalVehicle.setText("Arrival Vehicle");
						colArrivalVehicle.setWidth(90);
					}
					{
						colConsignee = new TableColumn(
								tblInwardRegister, SWT.NONE);
						colConsignee.setText("Consignee");
						colConsignee.setWidth(100);
					}
					{
						colSignature = new TableColumn(
								tblInwardRegister, SWT.NONE);
						colSignature.setText("Signature");
						colSignature.setWidth(83);
					}
					
					for (int index = 0; index < tblInwardRegister.getColumnCount(); index++) {
						tblInwardRegister.getColumn(index).addListener(SWT.Selection, this);
					}
				}

			}
		}
			
		
		{
			btnPrint = new Button(this, SWT.PUSH | SWT.CENTER);
			btnPrint.setText("Print");
			btnPrint.setBounds(529, 560, 60, 23);
			btnPrint.addSelectionListener(this);
		}
		{
			btnExportXL = new Button(this, SWT.PUSH | SWT.CENTER);
			btnExportXL.setText("Export Excel");
			btnExportXL.setBounds(600, 560, 80, 23);		
			btnExportXL.addSelectionListener(this);
		}
		{
			btnExportPDF = new Button(this, SWT.PUSH | SWT.CENTER);
			btnExportPDF.setText("Export PDF");
			btnExportPDF.setBounds(690, 560, 80, 23);		
			btnExportPDF.addSelectionListener(this);
			
		}
		
		{
			label3 = new Label(canIR, SWT.NONE);
			label3.setText("Date");
			/*FormData label3LData = new FormData();
			label3LData.width = 29;
			label3LData.height = 18;
			label3LData.left = new FormAttachment(0, 1000, 640);
			label3LData.top = new FormAttachment(0, 1000, 104);
			label3.setLayoutData(label3LData);*/
			label3.setBounds(100, 10, 40, 25);
		}
		
		{
			txtDate = new Text(canIR, SWT.BORDER | SWT.READ_ONLY);
			/*FormData txtDateLData = new FormData();
			txtDateLData.width = 60;
			txtDateLData.height = 17;
			txtDateLData.left = new FormAttachment(0, 1000, 670);
			txtDateLData.top = new FormAttachment(0, 1000, 100);
			txtDate.setLayoutData(txtDateLData);*/
			txtDate.setBounds(150, 10, 70, 25);
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date currentDate = new java.util.Date();
			String date = dateFormat.format(currentDate);

			if (SERVER_DATE != null) {
				txtDate.setText(SERVER_DATE);
			} else {
				txtDate.setText(date);
			}
		}
		{
			btnRepDate = new Button(canIR, SWT.PUSH | SWT.CENTER);				
			/*FormData txtDateLData = new FormData();
			txtDateLData.width = 31;
			txtDateLData.height = 23;
			txtDateLData.left = new FormAttachment(0, 1000, 743);
			txtDateLData.top = new FormAttachment(0, 1000, 100);
			btnRepDate.setLayoutData(txtDateLData);*/
			btnRepDate.setBounds(220, 10, 25, 25);
			btnRepDate.setImage(SWTResourceManager
					.getImage("hm/akr/resources/Calendar.jpg"));
			btnRepDate.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					KalendarDialog cd = new KalendarDialog(shell);
					Object obj = cd.open();
					if (obj != null) {
						String date = obj.toString();
						txtDate.setText(date);
					}
				}
			});
		}
					
		{
			{
				label4 = new Label(canIR, SWT.NONE);
				label4.setText("To Date");
				/*FormData label3LData = new FormData();
				label3LData.width = 38;
				label3LData.height = 18;
				label3LData.left = new FormAttachment(0, 1000, 785);
				label3LData.top = new FormAttachment(0, 1000, 104);
				label4.setLayoutData(label3LData);*/
				label4.setBounds(255, 10, 55, 25);
				
			}
			
			{
				txtToDate = new Text(canIR, SWT.BORDER | SWT.READ_ONLY);
				/*FormData txtDateLData = new FormData();
				txtDateLData.width = 60;
				txtDateLData.height = 17;
				txtDateLData.left = new FormAttachment(0, 1000, 830);
				txtDateLData.top = new FormAttachment(0, 1000, 100);
				txtToDate.setLayoutData(txtDateLData);*/
				txtToDate.setBounds(320, 10, 70, 25);
				
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date currentDate = new java.util.Date();
				String date = dateFormat.format(currentDate);

				if (SERVER_DATE != null) {
					txtToDate.setText(SERVER_DATE);
				} else {
					txtToDate.setText(date);
				}
			}
			{
				btnRepToDate = new Button(canIR, SWT.PUSH | SWT.CENTER);				
				/*FormData txtDateLData = new FormData();
				txtDateLData.width = 31;
				txtDateLData.height = 23;
				txtDateLData.left = new FormAttachment(0, 1000, 903);
				txtDateLData.top = new FormAttachment(0, 1000, 100);
				btnRepToDate.setLayoutData(txtDateLData);*/
				btnRepToDate.setBounds(390, 10, 25, 25);
				btnRepToDate.setImage(SWTResourceManager
						.getImage("hm/akr/resources/Calendar.jpg"));
				btnRepToDate.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						KalendarDialog cd = new KalendarDialog(shell);
						Object obj = cd.open();
						if (obj != null) {
							String date = obj.toString();
							txtToDate.setText(date);
						}
					}
				});
			}
							
		}
		
		{
			btnGo = new Button(canIR, SWT.PUSH | SWT.CENTER);
			btnGo.setText("Go");
			/*FormData butDateLData = new FormData();
			butDateLData.width = 37;
			butDateLData.height = 25;
			butDateLData.left = new FormAttachment(0, 1000, 940);
			butDateLData.top = new FormAttachment(0, 1000, 99);
			btnGo.setLayoutData(butDateLData);*/
			btnGo.setBounds(790,10,35,25);
			btnGo.addSelectionListener(this);

			/*label4.setVisible(false);
			txtToDate.setVisible(false);
			btnRepToDate.setVisible(false);*/
			
		}	
		
		/*{
			lblSta = new Label(canIR, SWT.NONE);
			FormData lblStaLData = new FormData();
			/*lblStaLData.width = 70;
			lblStaLData.height = 13;
			lblStaLData.left = new FormAttachment(0, 1000, 400);
			lblStaLData.top = new FormAttachment(0, 1000, 104);
			lblSta.setLayoutData(lblStaLData);*\/
			lblSta.setBounds(100, 10, 80, 25);
			lblSta.setText("Statement No.");
		}
		{
			/*FormData txtStaLData = new FormData();
			txtStaLData.width = 155;
			txtStaLData.height = 18;
			txtStaLData.left = new FormAttachment(0, 1000, 475);
			txtStaLData.top = new FormAttachment(0, 1000, 100);*\/
			txtSta = new Text(canIR, SWT.BORDER | SWT.READ_ONLY);
			//txtSta.setLayoutData(txtStaLData);
			txtSta.setFont(SWTResourceManager.getFont("Tahoma", 9, 1,
					false, false));
			txtSta.setBounds(190, 10, 80, 25);
		}	*/	
		
		{
			lblIRStation = new Label(canIR, SWT.NONE);
			lblIRStation.setText("Station");
			lblIRStation.setBounds(595, 10, 40, 21);
		}
		{
			cbIRStation = new Combo(canIR, SWT.READ_ONLY);
			cbIRStation.setBounds(655, 10, 110, 21);
			if(clientRights == 0 || clientRights == 0.0){
				cbIRStation.add(currentStationName+" - "+currentStationCode);
				cbIRStation.select(0);

			}else
			{
				cbIRStation.add("All");
			}
		}
		
		{
			cbIRBranch = new Combo(canIR, SWT.READ_ONLY);
			cbIRBranch.setBounds(475, 10, 110, 20);
			//cbDDEBranch.addSelectionListener(this);
			//cbDDEBranch.add("All");
			if(clientRights == 0 || clientRights == 0.0){
				
				try{
					
					getCurrentBranch_code(currentStationCode);
					cbIRBranch.select(0);
				}
				catch(Exception e){
					e.printStackTrace();
				}
				//cbStation
				//cbStation.add();
				
			}else if(clientRights == 1 || clientRights == 1.0){
				
				try{
				
					getCurrentBranch_code(currentStationCode);
					cbIRBranch.select(0);
					//populateStationForCLR();
					handleBranchActionDDE(cbIRBranch, cbIRStation);
					cbIRBranch.addSelectionListener(this);
				}
				catch(Exception e){
					e.printStackTrace();
				}
				
				
			}
			else{
				try{
					StationsDTO[] dto = handler.getAllBranches();
					if (null != dto) {
						cbIRBranch.add("All");
						for (int i = 0; i < dto.length; i++) {
							cbIRBranch.add(dto[i].getName() + " - "
									+ dto[i].getId());
						}
					}
					cbIRBranch.addSelectionListener(this);
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		{
			lblIRBranch = new Label(canIR, SWT.NONE);
			lblIRBranch.setText("Branch");
			lblIRBranch.setBounds(425, 10, 40, 15);
			
		}
		
		
		/*String statementNo = calculateStatementNumber(txtDate
				.getText(), "IR");
		if (null != statementNo)
			txtSta.setText(statementNo);*/
		
		return this;
	}

	
	private void handleBranchActionDDE(Combo branch, Combo cbStation) {

		StationsDTO[] station = null;
		String value = "";
		String stationCode = "";

		try {
				value = branch.getText();
			
			
				int index = value.indexOf(" - ");
				stationCode = value.substring(index + 3);
	
				if (stationCode != null) {
					station = handler.getStations(stationCode);
				}
				if (null != station) {
	
					int len = station.length;
					cbStation.removeAll();
					cbStation.add("All");
					if(!branch.getText().equalsIgnoreCase("All")){
						
						for (int i = 0; i < len; i++) {
							cbStation.add(station[i].getName() + " - "
									+ station[i].getId());
						}
					}
				
				}
			
		} catch (Exception exception) {

		}
	}
	
	private void getCurrentBranch_code(String station) throws Exception {
		StationsDTO[] stations = null;

		stations = handler.getAllStations();
		int size = 0;
		if (null != stations) {
			size = stations.length;

			for (int i = 0; i < size; i++) {
				if (station.equalsIgnoreCase(stations[i].getId())) {
					
					 getCurrentBranchName(stations[i].getBranch_code());
					
					//return stations[i].getBranch_code();
				}
			}
		}

		//return null;
	}
	
	private void getCurrentBranchName(String branch_code) {
		// TODO Auto-generated method stub
		try{
			StationsDTO[] stations = null;
	
			stations = handler.getAllStations();
			int size = 0;
			if (null != stations) {
				size = stations.length;
	
				for (int i = 0; i < size; i++) {
					if (branch_code.equalsIgnoreCase(stations[i].getId())) {
					
						cbIRBranch.add(stations[i].getName()+" - "+branch_code);
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

	
	private void populateIRRecords(boolean refresh) {
		if (refresh || null == irDto) {		
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date date = null;
			Date toDate = null;
			String stnCode = "";
			String branchCode = "";
			try {
				date = dateFormat.parse(txtDate.getText());
		
				toDate = dateFormat.parse(txtToDate.getText());
				stnCode = cbIRStation.getText();
				branchCode = cbIRBranch.getText();
				if((branchCode.equalsIgnoreCase("All")) &&(stnCode.equalsIgnoreCase("All"))){
					stnCode = "%";
					branchCode = "%";
				}
				else if ((!branchCode.equalsIgnoreCase("All")) &&(stnCode.equalsIgnoreCase("All"))) {
					/*int index = stnCode.indexOf(" - ");
					stnCode = stnCode.substring(index + 3);*/
					int indexbranch = branchCode.indexOf(" - ");
					branchCode = branchCode.substring(indexbranch + 3);
					stnCode = "%";
				} else {
					int index = stnCode.indexOf(" - ");
					stnCode = stnCode.substring(index + 3);
					int indexbranch = branchCode.indexOf(" - ");
					branchCode = branchCode.substring(indexbranch + 3);
				}
				
				
				
				if(toDate.before(BeanUtil.getThree_month_updated())){
					//System.out.println("in inw regtr hist-->"+BeanUtil.getThree_month_updated());
					irDto = handler.getInwardRegisterHistory(date, toDate,branchCode,stnCode);
				}else if(date.after(BeanUtil.getThree_month_updated())){
					//System.out.println("in inw regtr curr-->"+BeanUtil.getThree_month_updated());
				irDto = handler.getInwardRegister(date, toDate,branchCode,stnCode);
                }else{
                	irDto = handler.getInwardRegisterUnion(date, toDate,branchCode,stnCode);
                }

				//firsttab = false;
			} catch (Exception exception) {
				exception.printStackTrace();
				displayError("Error occurred while populating");
			}
			if (null != irDto) {
				/*btnExportXL.setEnabled(true);
				btnExportPDF.setEnabled(true);
				btnPrint.setEnabled(true);*/
				populateIRTable(irDto);
				
			} else {
				/*btnExportXL.setEnabled(false);
				btnExportPDF.setEnabled(false);
				btnPrint.setEnabled(false);*/
				tblInwardRegister.removeAll();
				displayError("No record Available");
			}
		}
	}
	
	private void displayError(String errorText) {
		MessageBox messageBox = new MessageBox(shell, SWT.OK);
		messageBox.setMessage(errorText);
		messageBox.setText("Alert");
		messageBox.open();
	}
	
	private void populateIRTable(GMROutTimeDTO[] irDto) {

		
		tblInwardRegister.removeAll();
		
		int len = irDto.length;
		String date = txtDate.getText();
		String serialPrefix = calculateStatementNumber(date, "IR");
		//txtSta.setText(serialPrefix);
		irST = serialPrefix;
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		float total = 0;
		int totalNoa = 0;
		float totalActWt = 0;
				
		for (int i = 0, j = 1; i < len; i++) {
			TableItem item = new TableItem(tblInwardRegister, SWT.NONE);
			item.setText(0, String.valueOf(j));
			item.setText(1, irDto[i].getLr_no());			
			item.setText(2, dateFormat.format(irDto[i].getLrDate()));
			item.setText(3, irDto[i].getLr_type());
			item.setText(4, dateFormat.format(irDto[i].getOutTimeDate()));
			total = total + irDto[i].getTotal();
			item.setText(5, getRoundedValue(irDto[i].getTotal()));
			item.setText(6, irDto[i].getFrom());
			totalNoa = totalNoa + irDto[i].getNo_of_articles();
			item.setText(7, String.valueOf(irDto[i].getNo_of_articles()));
			totalActWt = totalActWt + irDto[i].getActual_weight();
			item.setText(8, getRoundedValue(irDto[i].getActual_weight()));
			if(null != irDto[i].getArticle_type()){
				item.setText(9, irDto[i].getArticle_type());
			}			
			item.setText(10, irDto[i].getConsigneeName());
			j++;
		}

		TableItem item1 = new TableItem(tblInwardRegister, SWT.NONE | SWT.BOLD);
		Font font1 = new Font(display, "Tahoma", 8, SWT.BOLD);
		item1.setFont(font1);

		item1.setText(3, "TOTAL");

		item1.setText(5, getRoundedValue(total));
		item1.setText(7, String.valueOf(totalNoa));
		item1.setText(8, getRoundedValue(totalActWt));
		
		
	}
	
	private String calculateStatementNumber(String dateString, String stmt) {
		String serialPrefix = EMPTY_STRING;

		if (null != dateString && dateString.length() > 0) {
			serialPrefix = handler.getStationCode() + stmt;
			SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);

			try {

				GregorianCalendar givenDate = new GregorianCalendar();
				Date date = format.parse(dateString);
				givenDate.setTime(date);

				int month = givenDate.get(Calendar.MONTH);
				int year = givenDate.get(Calendar.YEAR);
				int day = givenDate.get(Calendar.DATE);
				int mo = month + 1;

				givenDate.set(year, mo, day);

				if (month < 4)
					year--;
				GregorianCalendar cal = new GregorianCalendar();
				cal.set(year, 3, 31);
				long mill1 = givenDate.getTimeInMillis();
				long mill2 = cal.getTimeInMillis();
				long diff = mill1 - mill2;

				// long diff = date.getTime() - cal.getTime().getTime();

				temp = (int) (diff / (24 * 60 * 60 * 1000)); // Number Of
				// days since
				// financial year
			} catch (Exception exception) {
				return EMPTY_STRING;
			}
		}
		return serialPrefix + temp;
	}

	private String getRoundedValue(float cardRate) {
		DecimalFormat decimalFormat;
		decimalFormat = new DecimalFormat("0.00");
		cardRate = cardRate * 100;
		cardRate = (float) Math.round(cardRate);
		cardRate = cardRate / 100;

		return decimalFormat.format(cardRate);
	}
	
	private ArrayList<GMROutTimeDTO> buildInwardDTO() throws ParseException {
		// TODO Auto-generated method stub
		GMROutTimeDTO dto = null;
		ArrayList<GMROutTimeDTO> list = new ArrayList<GMROutTimeDTO>();
		if (null != tblInwardRegister) {
			TableItem[] item = tblInwardRegister.getItems();
			int len = item.length;
			SimpleDateFormat dateform = new SimpleDateFormat(
						"dd-MM-yyyy");
			Date tempdate = null;
			Date temp = null;
			for (int i = 0; i < len; i++) {
					dto = new GMROutTimeDTO();
					dto.setLr_no(item[i].getText(1));
					if(!(item[i].getText(2).equals(""))){
						tempdate = dateform.parse(item[i].getText(2));
						dto.setOutTimeDate(tempdate);
					}
					dto.setLr_type(item[i].getText(3));
					if(!(item[i].getText(4).equals(""))){
						temp = dateform.parse(item[i].getText(4));
						dto.setLrDate(temp);
					}
					if(!(item[i].getText(5).equals("")))
						dto.setTotal(Float.parseFloat(item[i].getText(5)));
					dto.setFrom(item[i].getText(6));
					if(!(item[i].getText(7).equals("")))	
						dto.setNo_of_articles(Integer.parseInt(item[i].getText(7)));
					if(!(item[i].getText(8).equals("")))	
						dto.setActual_weight(Float.parseFloat(item[i].getText(8)));
					dto.setArticle_type(item[i].getText(9));
						
					dto.setConsigneeName((item[i].getText(10)));
					
					dto.setConsignorName(item[i].getText(11));
					
					list.add(dto);
			}
			
		}
		return list;

	}
	
	private void preparePDFInward(ArrayList<GMROutTimeDTO> list,
			String irSt, String fromdate, String todate, String fileName) {
		// TODO Auto-generated method stub
		try {
			/*AdminComposite.display("Exporting...", STATUS_SUCCESS,
					SUCCESS_FONT_COLOR);*/
			handler.printInwardPDF(list,irSt,fromdate,todate,fileName);

			FileDialog dialog = new FileDialog(shell, SWT.SAVE);
			dialog.setFilterExtensions(new String[] { "*.pdf" });
			dialog.setFilterNames(new String[] { "*.pdf" });
			dialog.setFilterPath("WINDOWS/Desktop"); // Windows path
			dialog.setFileName(fileName);
			String filepath = dialog.open();

			if (null != filepath) {
				File df = new File(filepath);
				File xl = new File("lib/" + fileName + ".pdf");

				if (xl.exists()) {
					//AdminComposite.display("Saving...", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					new sortListener().copyFile(xl, df, dialog.getFileName());
				}
				/*AdminComposite.display("PDF Saved Successfully",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);*/
			}

		} catch (Exception e) {
			//AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			e.printStackTrace();
			displayError("Status Success");
		}
		
	}

	private void prepareXLInward(ArrayList dto, String irSt, String fromdate, String todate, String fileName)throws Exception {
		// TODO Auto-generated method stub
		try {
			/*ReportsComposite.display("Exporting...", STATUS_SUCCESS,
			SUCCESS_FONT_COLOR);*/
				handler.printInwardReportExcel(dto,irSt,fromdate,todate, fileName);

				FileDialog dialog = new FileDialog(shell, SWT.SAVE);
				
				dialog.setFilterExtensions(new String[] { "*.xls" });
				dialog.setFilterNames(new String[] { "*.xls" });
				dialog.setFilterPath("WINDOWS/Desktop"); // Windows path
				dialog.setFileName(fileName);
				String filepath = dialog.open();

				if (null != filepath) {
					File df = new File(filepath);
					File xl = new File("lib/" + fileName + ".xls");

					if (xl.exists()) {
						//	AdminComposite.display("Saving...", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						new sortListener().copyFile(xl, df, dialog.getFileName());
					}
					/*AdminComposite.display("Excel Saved Successfully",
					STATUS_SUCCESS, SUCCESS_FONT_COLOR);*/
				}
				/*else
				{
				AdminComposite.display("Excel Saving Cancelled",
					STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				}*/
			} catch (Exception e) {
				//	AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				e.printStackTrace();
				displayError("Status Success");
			}

	}
	
	
	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void widgetSelected(SelectionEvent event) {
		// TODO Auto-generated method stub
		Object source = event.getSource();
		//String selectedTab = reportsTabFolder.getSelection()[0].getText();
		if (source == cbIRBranch) {
			handleBranchActionDDE(cbIRBranch, cbIRStation);
			
		} else if (source == btnGo) {
			populateIRRecords(true);
		}else if(source == btnExportPDF){
			
			
			try {
				ArrayList<GMROutTimeDTO> dto = buildInwardDTO();
				preparePDFInward(dto,irST,txtDate.getText(),txtToDate.getText(),"Inward Register");
				btnExportPDF.setEnabled(false);
			} catch (Exception exception) {
				displayError("Problem while Exporting PDF");
			}
		}else if(source == btnExportXL){
			
			
			try {
				ArrayList<GMROutTimeDTO> dto = buildInwardDTO();
				prepareXLInward(dto,irST,txtDate.getText(),txtToDate.getText(),"Inward Register");
				btnExportXL.setEnabled(false);
			} catch (Exception exception) {
				displayError("Problem while Exporting Excel");
			}
		}
		else if(source == btnPrint){
		
			
			try {
				ArrayList<GMROutTimeDTO> dto = buildInwardDTO();
				handler.printInwardRegister(dto,irST,txtDate.getText(),txtToDate.getText(),"Inward Register");
				//btnprint.setEnabled(false);
			} catch (Exception exception) {
				displayError("Problem while Printing");
			}
		}
			
		
	}

	@Override
	public void handleEvent(Event e) {
		// TODO Auto-generated method stub
		
		String selection = tabReport.getSelection()[0].getText();
		TableColumn column = (TableColumn) e.widget;
		
		TableColumn[] cols = tblInwardRegister.getColumns();
		int len = cols.length;

		for (int i = 0; i < len; i++) {
			if (column == cols[i]) {
				if (i == 1) {
					new sortListener().sortByLrNo(i, tblInwardRegister);
				}else if (i == 2 || i == 4) {
					new sortListener().sortByDate(i, tblInwardRegister);
				}else if (i == 3 || i == 6 || i == 9 || i == 10) {
					new sortListener().sortByString(i, tblInwardRegister);
				} else if (i ==0 || i == 5 || i == 7 || i == 8) {
					new sortListener().sortByFloat(i, tblInwardRegister);
				}
			}

		}
		
	}
	
}
