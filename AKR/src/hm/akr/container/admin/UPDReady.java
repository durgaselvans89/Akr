package hm.akr.container.admin;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import hm.akr.common.sortListener;
import hm.akr.common.BeanUtil;
import hm.akr.common.IUIConstants;
import hm.akr.container.history.HistoryHandler;
import hm.akr.dto.BookingDTO;
import hm.akr.dto.StationsDTO;
import hm.akr.dto.TableDecorator;
import hm.akr.dto.VersionDTO;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class UPDReady  extends ReportsMenu implements IUIConstants,Listener,SelectionListener {
	
	private TableColumn colUPDRTotalFreight;

	private TableColumn colUPDRInwardDays;

	private TableColumn colUPDCneePhone;

	private TableColumn colUPDRCnee;

	private TableColumn colUPDCnorPhone;

	private TableColumn colUPDRLrType;

	private TableColumn colUPDRLrDate;

	private TableColumn colUPDRLrNo;

	private TableColumn colUPDRStationCode;

	private TableColumn colUPDRBranchCode;
	private TabItem tiUPDReady;
	private Canvas canUPDReady;
	private Table tblUPDReady;
	private TableColumn colUpdSno;
	private TableColumn colUPDRCnor;
	private Label lblUPDRBranch;
	private Combo cbUPDRBranch;
	private Label lblUPDRStation;
	private Combo cbUPDRStation;
	private Label lblUPDRInwardDays;
	private Combo cbUPDRInwardDays;
	private Button btnUPDGo;
	private Label lblupdOption;
	private Combo cbUPDOption;
	private String UPD_READY = "UPD Ready";
	private static final String UPD_EXCEL_JRXML = "hm/akr/resources/printer/UPD_Ready.jrxml";
	
	private TabFolder tabReport = null;
	private Label lblStatusBar;
	VersionDTO[] vDto = null;
	private Shell shell = null;
	DateFormat dateFormat = null;
	private Button btnPrint;
	private Button btnExportXL;
	private Button btnExportPDF;
	private ReportsMenuHandler handler;
	HistoryHandler historyH = null;
	
	private float clientRights = -1;
	private String currentStationName = "";
	private String currentStationCode = "";
	
	 public UPDReady(Composite composite,int style, float clientRights) {
		super(composite,style);
		// TODO Auto-generated constructor stub
		
		try {
			this.clientRights = clientRights;
			currentStationName = beanutil.getActingStationName();
			currentStationCode = beanutil.getActingStationCode();
			shell = composite.getShell();
			dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			handler = new ReportsMenuHandler();
			historyH = new HistoryHandler();
			vDto = historyH.getHistoryYears();
			if(vDto != null && vDto.length > 0){
				BeanUtil.setThree_month_updated(vDto[0].getUpdated_date());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Composite loadcomposite(){
		
		this.setBounds(45,100, 1020,700);
		tabReport = new TabFolder(this, SWT.NONE);
		tabReport.setBounds(50, 25, 820, 500);
		tabReport.addSelectionListener(this);
		
		
		{		
			tiUPDReady = new TabItem(tabReport, SWT.NONE);
			tiUPDReady.setText(UPD_READY);
			{
				canUPDReady = new Canvas(tabReport, SWT.BORDER);
				tiUPDReady.setControl(canUPDReady);
				{
					tblUPDReady = new Table(canUPDReady, SWT.FULL_SELECTION
							| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
					tblUPDReady.setHeaderVisible(true);
					tblUPDReady.setLinesVisible(true);
					tblUPDReady.setBounds(10, 72, 800, 395);
					{
						colUpdSno = new TableColumn(tblUPDReady, SWT.NONE);
						colUpdSno.setText("S.No");
						colUpdSno.setWidth(46);
					}
					{
						colUPDRBranchCode = new TableColumn(tblUPDReady,
								SWT.NONE);
						colUPDRBranchCode.setText("Branch Code");
						colUPDRBranchCode.setWidth(80);
					}
					{
						colUPDRStationCode = new TableColumn(tblUPDReady,
								SWT.NONE);
						colUPDRStationCode.setText("Station Code");
						colUPDRStationCode.setWidth(80);
					}
					{
						colUPDRLrNo = new TableColumn(tblUPDReady, SWT.NONE);
						colUPDRLrNo.setText("Lr No");
						colUPDRLrNo.setWidth(60);
					}
					{
						colUPDRLrDate = new TableColumn(tblUPDReady, SWT.NONE);
						colUPDRLrDate.setText("LR Date");
						colUPDRLrDate.setWidth(83);
					}
					{
						colUPDRLrType = new TableColumn(tblUPDReady, SWT.NONE);
						colUPDRLrType.setText("LR Type");
						colUPDRLrType.setWidth(60);
					}
					{
						colUPDRTotalFreight = new TableColumn(tblUPDReady,
								SWT.NONE);
						colUPDRTotalFreight.setText("Total Freight");
						colUPDRTotalFreight.setWidth(80);
					}
					{
						colUPDRInwardDays = new TableColumn(tblUPDReady,
								SWT.NONE);
						colUPDRInwardDays.setText("Inward Days");
						colUPDRInwardDays.setWidth(80);
					}
					{
						colUPDRCnor = new TableColumn(tblUPDReady, SWT.NONE);
						colUPDRCnor.setText("Cnor");
						colUPDRCnor.setWidth(90);
					}
					{
						colUPDCnorPhone = new TableColumn(tblUPDReady, SWT.NONE);
						colUPDCnorPhone.setText("Cnor Phone");
						colUPDCnorPhone.setWidth(120);
					}
					{
						colUPDRCnee = new TableColumn(tblUPDReady, SWT.NONE);
						colUPDRCnee.setText("Cnee");
						colUPDRCnee.setWidth(90);
					}
					{
						colUPDCneePhone = new TableColumn(tblUPDReady, SWT.NONE);
						colUPDCneePhone.setText("Cnee Phone");
						colUPDCneePhone.setWidth(120);
					}

					for (int index = 0; index < tblUPDReady.getColumnCount(); index++) {
						tblUPDReady.getColumn(index).addListener(SWT.Selection,
								this);
					}

				}
				
				
				{
					lblUPDRStation = new Label(canUPDReady, SWT.NONE);
					lblUPDRStation.setText("Select Station");
					lblUPDRStation.setBounds(192, 28, 69, 22);
				}
				{
					cbUPDRStation = new Combo(canUPDReady, SWT.READ_ONLY);
					//cbUPDRStation.add("All");
					cbUPDRStation.setBounds(261, 29, 96, 21);
					if(clientRights == 0 || clientRights == 0.0){
						cbUPDRStation.add(currentStationCode);
						cbUPDRStation.select(0);

					}
				}
				
				
				{
					lblUPDRBranch = new Label(canUPDReady, SWT.NONE);
					lblUPDRBranch.setText("Select Branch");
					lblUPDRBranch.setBounds(24, 28, 68, 21);
				}
				{
					cbUPDRBranch = new Combo(canUPDReady, SWT.READ_ONLY);
					cbUPDRBranch.setBounds(98, 29, 88, 21);
					//cbUPDRBranch.add("All");
					if(clientRights == 0 || clientRights == 0.0){
						
						try{
							
							getCurrentBranch_code(currentStationCode);
							cbUPDRBranch.select(0);
						}
						catch(Exception e){
							e.printStackTrace();
						}
						//cbStation
						//cbStation.add();
						
					}else if(clientRights == 1 || clientRights == 1.0){
						
						try{
						
							getCurrentBranch_code(currentStationCode);
							cbUPDRBranch.select(0);
							//populateStationForCLR();
							String branch = cbUPDRBranch.getText();
							int index = branch.indexOf(" - ");
							branch = branch.substring(index + 3);
							// if (cbSBABranch.getSelectionIndex() != -1) {
							sundryBranchComboAction(branch, cbUPDRStation);
							cbUPDRBranch.addSelectionListener(this);
						}
						catch(Exception e){
							e.printStackTrace();
						}
						
						
					}
					else{
					
					
						try{
							StationsDTO[] dto = handler.getAllBranches();
							if (null != dto) {
								cbUPDRBranch.add("All");
								for (int i = 0; i < dto.length; i++) {
									cbUPDRBranch.add(dto[i].getName() + " - "
											+ dto[i].getId());
								}
							}
							cbUPDRBranch.addSelectionListener(this);
						}
						catch(Exception e){
							e.printStackTrace();
						}
						
					}
					
				}
				
				{
					cbUPDOption = new Combo(canUPDReady, SWT.READ_ONLY);
					cbUPDOption.setBounds(401, 29, 102, 21);
					cbUPDOption.add("More Than");
					cbUPDOption.add("Equal To");
				}
				{
					lblupdOption = new Label(canUPDReady, SWT.NONE);
					lblupdOption.setText("Option");
					lblupdOption.setBounds(364, 28, 33, 18);
				}
				{
					lblUPDRInwardDays = new Label(canUPDReady, SWT.NONE);
					lblUPDRInwardDays.setText("InwardDays");
					lblUPDRInwardDays.setBounds(511, 28, 60, 21);
				}
				{
					cbUPDRInwardDays = new Combo(canUPDReady, SWT.NONE);
					cbUPDRInwardDays.add("7");
					cbUPDRInwardDays.add("15");
					cbUPDRInwardDays.add("30");
					cbUPDRInwardDays.add("45");
					cbUPDRInwardDays.setBounds(573, 29, 60, 21);
				}

				{
					btnUPDGo = new Button(canUPDReady, SWT.PUSH);
					btnUPDGo.setBounds(636, 27, 43, 25);
					btnUPDGo.addSelectionListener(this);
					btnUPDGo.setText("Go");
				}

			}
		}
		
		
		{
			btnPrint = new Button(this, SWT.PUSH | SWT.CENTER);
			btnPrint.setText("Print");
			btnPrint.setBounds(529, 530, 60, 23);
			btnPrint.addSelectionListener(this);
		}
		{
			btnExportXL = new Button(this, SWT.PUSH | SWT.CENTER);
			btnExportXL.setText("Export Excel");
			btnExportXL.setBounds(600, 530, 80, 23);		
			btnExportXL.addSelectionListener(this);
		}
		{
			btnExportPDF = new Button(this, SWT.PUSH | SWT.CENTER);
			btnExportPDF.setText("Export PDF");
			btnExportPDF.setBounds(690, 530, 80, 23);		
			btnExportPDF.addSelectionListener(this);
			
		}
		
		{
			lblStatusBar = new Label(this, SWT.NONE);
			lblStatusBar.setBounds(60,570 ,300,25);
			lblStatusBar.setFont(STATUS_SUCCESS);
			lblStatusBar.setText("STATUS");
			lblStatusBar.setForeground(LINK_FOCUS_COLOR);
			lblStatusBar.setVisible(true);
		}
		
	
		return this;
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
					//cbUPDRBranch.add(stations[i].getBranch_code());
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
					
						cbUPDRBranch.add(stations[i].getName()+" - "+branch_code);
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


	
	private void sundryBranchComboAction(String branch, Combo cbStation) {

		StationsDTO[] stations = null;
		try {
			stations = handler.getAllStations();
			int size = 0;
			if (null != stations) {
				size = stations.length;
				cbStation.removeAll();
				cbStation.add("All");
				if (!branch.equalsIgnoreCase("All")) {
					for (int i = 0; i < size; i++) {
						if (branch.equals(stations[i].getBranch_code())) {
							cbStation.add(stations[i].getId());
						}
					}
				} else {
					for (int i = 0; i < size; i++) {
						cbStation.add(stations[i].getId());
					}
				}

			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}
	
	private void populateUPDReady(BookingDTO[] updReady, boolean isBranch)
	throws Exception {

		for (int i = 0, j = 0; i < updReady.length; i++) {
		
			if (isBranch) {
				if (isBranchStation(updReady[i].getFrom())) {
					fillUPD(updReady[i], j);
					j++;
				}
			} else {
				fillUPD(updReady[i], j);
				j++;
			}
		
		}
		
		calcUPDReadyTotal();
		
		}
		
		private void calcUPDReadyTotal() {
		TableItem[] items = tblUPDReady.getItems();
		float totAmt = 0;
		if (items != null) {
			for (int i = 0; i < items.length; i++) {
				totAmt = totAmt + Float.parseFloat(items[i].getText(6));
			}
		
		}
		
		final TableItem item1 = new TableItem(tblUPDReady, SWT.NONE);
		Font font1 = new Font(Display.getCurrent(), "Tahoma", 8, SWT.BOLD);
		item1.setFont(font1);
		item1.setText(5, "TOTAL");
		item1.setText(6, getRoundedValue(totAmt));
		
		}
		
		private void fillUPD(BookingDTO updReady, int j) throws Exception {
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		long diff = 0;
		int dayCount = 0;
		Date today = new Date();
		String fromNo = null;
		String toNo = null;
		
		TableItem item = new TableItem(tblUPDReady, SWT.NONE);
		item.setText(0, String.valueOf(j + 1));
		
		item.setText(1, getBranch_code(updReady.getFrom()));
		item.setText(2, updReady.getFrom());
		item.setText(3, updReady.getLrno());
		item.setText(4, dateFormat.format(updReady.getDate()));
		item.setText(5, updReady.getType());
		
		item.setText(6, getRoundedValue(updReady.getTotal()));
		diff = today.getTime() - updReady.getDeliveredDate().getTime();
		dayCount = (int) (diff / (1000 * 60 * 60 * 24));
		
		item.setText(7, String.valueOf(dayCount));
		
		item.setText(8, updReady.getConsignorName());
		fromNo = updReady.getFromMobile();
		if (fromNo != null) {
			item.setText(9, fromNo);
		} else {
			item.setText(9, "NIL");
		}
		
		item.setText(10, updReady.getConsigneeName());
		toNo = updReady.getToMobile();
		if (toNo != null) {
			item.setText(11, toNo);
		} else {
			item.setText(11, "NIL");
		}
		
		}
		
		private String getBranch_code(String station) throws Exception {
			StationsDTO[] stations = null;

			stations = handler.getAllStations();
			int size = 0;
			if (null != stations) {
				size = stations.length;

				for (int i = 0; i < size; i++) {
					if (station.equalsIgnoreCase(stations[i].getId())) {
						return stations[i].getBranch_code();
					}
				}
			}

			return null;
		}
		
		private String getRoundedValue(float cardRate) {
			DecimalFormat decimalFormat;
			decimalFormat = new DecimalFormat("0.00");
			cardRate = cardRate * 100;
			cardRate = (float) Math.round(cardRate);
			cardRate = cardRate / 100;

			return decimalFormat.format(cardRate);
		}
		
		private String getPhoneNo(String station) throws Exception {
		
		StationsDTO[] stations = null;
		
		stations = handler.getAllStations();
		
		if (null != stations) {
			for (int i = 0; i < stations.length; i++) {
				if (station.equalsIgnoreCase(stations[i].getId())) {
					if (stations[i].getMobile() != null) {
						return stations[i].getMobile();
					} else {
						return stations[i].getPhone1();
					}
				}
			}
		}
		
		return null;
		}
		
		
		private boolean isBranchStation(String from) throws Exception {

			StationsDTO[] stations = null;

			stations = handler.getAllStations();

			if (null != stations) {
				for (int i = 0; i < stations.length; i++) {
					if (from.equalsIgnoreCase(stations[i].getId())) {
						if (from.equalsIgnoreCase(stations[i].getBranch_code())) {
							return true;
						}
					}
				}
			}

			return false;
		}
		
		
		private BookingDTO[] designAndGetUPDTable(boolean isMoreThan)
			throws Exception {
		if (tblUPDReady != null) {
			tblUPDReady.removeAll();
		}
		
		BookingDTO[] dto = null;
		
		String station = cbUPDRStation.getText();
		int inwardDays = Integer.parseInt(cbUPDRInwardDays.getText());
		
		if (cbUPDRBranch.getText().equalsIgnoreCase("All")
				&& cbUPDRStation.getSelectionIndex() == -1) {
			
			// populateAllBranches(tblUPDReady);
			try{
				Date curDate = new Date();
				Date date = BeanUtil.getThree_month_updated();
				if(curDate.before(date)){
						//System.out.println("in ddr histry-->"+date);
					dto = handler.getUPDReadyHistory("%", "%", inwardDays, isMoreThan);
				}else{
					dto = handler.getUPDReady("%", "%", inwardDays, isMoreThan);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (cbUPDRBranch.getText().equalsIgnoreCase("All")
				&& cbUPDRStation.getText().equalsIgnoreCase("All")) {
			// populateAllStations(tblUPDReady);
			try{
				Date curDate = new Date();
				Date date = BeanUtil.getThree_month_updated();
				if(curDate.before(date)){
						//System.out.println("in ddr histry-->"+date);
					dto = handler.getUPDReadyHistory("%", "%", inwardDays, isMoreThan);
				}else{
					dto = handler.getUPDReady("%", "%", inwardDays, isMoreThan);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (!cbUPDRBranch.getText().equalsIgnoreCase("All")
				&& cbUPDRStation.getText().equalsIgnoreCase("All")) {
			// populateSelectedStations(cbUPDRBranch.getText(),tblUPDReady);
			String branchCode = cbUPDRBranch.getText();
			int index = branchCode.indexOf(" - ");
			branchCode = branchCode.substring(index + 3);
			try{
				Date curDate = new Date();
				Date date = BeanUtil.getThree_month_updated();
				if(curDate.before(date)){
						//System.out.println("in ddr histry-->"+date);
					dto = handler.getUPDReadyHistory("%", branchCode, inwardDays, isMoreThan);
				}else{
					dto = handler.getUPDReady("%", branchCode, inwardDays, isMoreThan);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (cbUPDRBranch.getText().equalsIgnoreCase("All")
				&& !cbUPDRStation.getText().equalsIgnoreCase("All")) {
		
			/*
			 * TableItem item = new TableItem(tblUPDReady,SWT.NONE);
			 * item.setText(0, String.valueOf(1)); item.setText(1,
			 * getBranch_code(cbUPDRStation.getText())); item.setText(2,
			 * cbUPDRStation.getText());
			 */
			// System.out.println("fsdfsfsf");
			try{
				Date curDate = new Date();
				Date date = BeanUtil.getThree_month_updated();
				if(curDate.before(date)){
						//System.out.println("in ddr histry-->"+date);
					dto = handler.getUPDReadyHistory(station, getBranch_code(station),
							inwardDays, isMoreThan);
				}else{
					dto = handler.getUPDReady(station, getBranch_code(station),
							inwardDays, isMoreThan);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (!cbUPDRBranch.getText().equalsIgnoreCase("All")
				&& !cbUPDRStation.getText().equalsIgnoreCase("All")) {
			String branchCode = cbUPDRBranch.getText();
			int index = branchCode.indexOf(" - ");
			branchCode = branchCode.substring(index + 3);
			try{
				Date curDate = new Date();
				Date date = BeanUtil.getThree_month_updated();
				if(curDate.before(date)){
						//System.out.println("in ddr histry-->"+date);
					dto = handler.getUPDReadyHistory(station, branchCode, inwardDays,
							isMoreThan);
				}else{
					dto = handler.getUPDReady(station, branchCode, inwardDays,
							isMoreThan);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return dto;
		
		}

	
	
	private String[] getUpdreadyHeading(Table refTable){
		
		String list[] = new String[4];
		
		String branchCode = cbUPDRBranch.getText();
		if (!branchCode.equalsIgnoreCase("All")) {
			int index = branchCode.indexOf(" - ");
			list[0] = branchCode.substring(index + 3);
		}
		else
			list[0] ="All";
		
		String stationCode = cbUPDRStation.getText();
		if (!stationCode.equalsIgnoreCase("All")) {
			int index = stationCode.indexOf(" - ");
			list[1] = stationCode.substring(index + 3);
		}
		else
			list[1] ="All";
		
		if(cbUPDOption.getText().equals("More Than"))
			list[2] = "More Than";
		else
			list[2] = "Equal To";
		
		list[3] = cbUPDRInwardDays.getText();
		
		
			
		return list;
	}
	
	@Override
	public void handleEvent(Event e) {
		// TODO Auto-generated method stub
		String selection = tabReport.getSelection()[0].getText();
		TableColumn column = (TableColumn) e.widget;
		
		if (column == colUpdSno) {
			new sortListener().sortByFloat(0, tblUPDReady);
		} else if (column == colUPDRBranchCode) {
			new sortListener().sortByString(1, tblUPDReady);
		} else if (column == colUPDRStationCode) {
			new sortListener().sortByString(2, tblUPDReady);
		} else if (column == colUPDRLrNo) {
			new sortListener().sortByLrNo(3, tblUPDReady);
		} else if (column == colUPDRLrDate) {
			new sortListener().sortByDate(4, tblUPDReady);
		} else if (column == colUPDRLrType) {
			new sortListener().sortByString(5, tblUPDReady);
		} else if (column == colUPDRTotalFreight) {
			new sortListener().sortByFloat(6, tblUPDReady);
		} else if (column == colUPDRInwardDays) {
			new sortListener().sortByFloat(7, tblUPDReady);
		} else if (column == colUPDRCnor) {
			new sortListener().sortByString(8, tblUPDReady);
		} else if (column == colUPDCnorPhone) {
			new sortListener().sortByString(9, tblUPDReady);
		} else if (column == colUPDRCnee) {
			new sortListener().sortByString(10, tblUPDReady);
		} else if (column == colUPDCneePhone) {
			new sortListener().sortByString(11, tblUPDReady);
		}
		
	}
	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void widgetSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub
		Object source = arg0.getSource();
		
		if (source == cbUPDRBranch) {
			String branch = cbUPDRBranch.getText();
			int index = branch.indexOf(" - ");
			branch = branch.substring(index + 3);
			// if (cbSBABranch.getSelectionIndex() != -1) {
			sundryBranchComboAction(branch, cbUPDRStation);

			// }
		} else if (source == btnUPDGo) {
			new sortListener().display("Generating Report...", STATUS_SUCCESS,
					SUCCESS_FONT_COLOR,lblStatusBar);

			if ((cbUPDRBranch.getSelectionIndex() == -1 && cbUPDRStation
					.getSelectionIndex() == -1)
					|| (!cbUPDRBranch.getText().equalsIgnoreCase("All") && cbUPDRStation
							.getSelectionIndex() == -1)) {
				// AdminComposite.display("Please select",
				// STATUS_SUCCESS,SUCCESS_FONT_COLOR);
			} else {
				if (handler != null) {
					try {
						BookingDTO[] updReady = null;
						boolean isMoreThan = false;

						if (!cbUPDRInwardDays.getText().equals("")) {
							if (cbUPDOption.getText().equalsIgnoreCase(
									"More Than")) {
								isMoreThan = true;
							}
							updReady = designAndGetUPDTable(isMoreThan);

							if (updReady != null) {
								if (cbUPDRBranch.getText().equalsIgnoreCase(
										"All")
										&& cbUPDRStation.getSelectionIndex() == -1) {
									// Display Only TRs
									populateUPDReady(updReady, true);

								} else {
									populateUPDReady(updReady, false);
								}
							}

						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			new sortListener().display("Report Loaded Successfully!",
					STATUS_SUCCESS, SUCCESS_FONT_COLOR,lblStatusBar);
		}
		else if (source == btnExportXL) {
			try {
				//handleExcel();
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				value = getUpdreadyHeading(tblUPDReady);
				list = new sortListener().getBPATable(tblUPDReady);
				new sortListener().prepareExcel(list, "UPD_Ready", UPD_EXCEL_JRXML, null,value,shell,lblStatusBar);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else if (source == btnPrint) {
			try {
				//handlePrint();
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				value = getUpdreadyHeading(tblUPDReady);
				list = new sortListener().getBPATable(tblUPDReady);
				handler.preparePrint(list, "UPD_Ready", UPD_EXCEL_JRXML, null,value);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		else if (source == btnExportPDF) {
			try {
				//handlePDF();
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				value = getUpdreadyHeading(tblUPDReady);
				list = new sortListener().getBPATable(tblUPDReady);
				new sortListener().preparePDF(list, "UPD_Ready", UPD_EXCEL_JRXML, null,value,shell,lblStatusBar);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		
	}
		

}
