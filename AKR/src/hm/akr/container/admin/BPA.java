package hm.akr.container.admin;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import hm.akr.common.sortListener;
import hm.akr.common.SWTResourceManager;
import hm.akr.common.BeanUtil;
import hm.akr.common.IUIConstants;
import hm.akr.common.KalendarDialog;
import hm.akr.container.history.HistoryHandler;
import hm.akr.dto.BookingDTO;
import hm.akr.dto.StationsDTO;
import hm.akr.dto.TableDecorator;
import hm.akr.dto.VersionDTO;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
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
import org.eclipse.swt.widgets.Text;

public class BPA extends ReportsMenu implements IUIConstants,Listener,SelectionListener {
	
	
	private String BPA = "BPA";
	private TableColumn colBPAsno;
	private TableColumn colBPABranch;
	private TableColumn colBPANolr;
	private TableColumn colBPANoa;
	private TableColumn colBPAActwt;
	private TableColumn colBPACrgwt;
	private TableColumn colBPABft;
	private TableColumn colBPALrc;
	private TableColumn colBPADhc;
	private TableColumn colBPACcc;
	private TableColumn colBPADdc;
	private TableColumn colBPADcc;
	private TableColumn colBPAIec;
	private TableColumn colBPALc;
	private TableColumn colBPAStax;
	private TableColumn colBPATotal;
	private TabItem tiBPA;
	private Table tblBPA;
	private Canvas cvsBPA;
	private Label label7;
	private Label lblFromDate;
	private Label lblToDate;
	private Text txtBPAFrom;
	private Button btnBPAFromDate;
	private Text txtBPATo;
	private Button btnBPAGo;
	private Combo cbBPABranch;
	private Combo cbBPAReportType;
	private Button btnBPATo;
	BookingDTO[] bpaDto = null;
	private static final String BPA_EXCEL_JRXML = "hm/akr/resources/printer/BPA.jrxml";

	
	private String COUNT = "Count";
	private String BASIC_FREIGHT = "Basic Freight";
	private String TOTAL_FREIGHT = "Total Freight";
	private String ACTUAL_WEIGHT = "Actual Weight";
	private String CHARGED_WEIGHT = "Charged Weight";
	private String NOA = "Noa";
	
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
	
	 public BPA(Composite composite,int style, float clientRights) {
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
		
		
		
		tiBPA = new TabItem(tabReport, SWT.NONE);
		tiBPA.setText(BPA);

		cvsBPA = new Canvas(tabReport, SWT.NONE);
		tiBPA.setControl(cvsBPA);

		{
			{
				tblBPA = new Table(cvsBPA, SWT.FULL_SELECTION
						| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);

				tblBPA.setHeaderVisible(true);
				tblBPA.setLinesVisible(true);
				tblBPA.setBounds(10, 72, 800, 400);
				{
					colBPAsno = new TableColumn(tblBPA, SWT.NONE);
					colBPAsno.setText("S NO");
					colBPAsno.setWidth(50);

				}
				{
					colBPABranch = new TableColumn(tblBPA, SWT.NONE);
					colBPABranch.setText("Station Name");
					colBPABranch.setWidth(180);

				}
				{
					colBPANolr = new TableColumn(tblBPA, SWT.NONE);
					colBPANolr.setText("No. of Lrs");
					colBPANolr.setWidth(70);

				}
				{
					colBPANoa = new TableColumn(tblBPA, SWT.NONE);
					colBPANoa.setText("NOA");
					colBPANoa.setWidth(70);

				}

				{
					colBPAActwt = new TableColumn(tblBPA, SWT.NONE);
					colBPAActwt.setText("Actual Weight");
					colBPAActwt.setWidth(70);

				}
				{
					colBPACrgwt = new TableColumn(tblBPA, SWT.NONE);
					colBPACrgwt.setText("Charged Weight");
					colBPACrgwt.setWidth(70);
				}

				{
					colBPABft = new TableColumn(tblBPA, SWT.NONE);
					colBPABft.setText("Basic Freight");
					colBPABft.setWidth(70);

				}
				{
					colBPALrc = new TableColumn(tblBPA, SWT.NONE);
					colBPALrc.setText("LRC");
					colBPALrc.setWidth(70);
				}
				{
					colBPADhc = new TableColumn(tblBPA, SWT.NONE);
					colBPADhc.setText("DHC");
					colBPADhc.setWidth(70);
				}
				
				{
					colBPACcc = new TableColumn(tblBPA, SWT.NONE);
					colBPACcc.setText("CCC");
					colBPACcc.setWidth(70);
				}
				{
					colBPADcc = new TableColumn(tblBPA, SWT.NONE);
					colBPADcc.setText("DCC");
					colBPADcc.setWidth(70);
				}
				{
					colBPADdc = new TableColumn(tblBPA, SWT.NONE);
					colBPADdc.setText("DDC");
					colBPADdc.setWidth(70);
				}
				
				{
					colBPAIec = new TableColumn(tblBPA, SWT.NONE);
					colBPAIec.setText("IEC");
					colBPAIec.setWidth(70);
				}
				{
					colBPALc = new TableColumn(tblBPA, SWT.NONE);
					colBPALc.setText("LC");
					colBPALc.setWidth(70);
				}
				
				{
					colBPAStax = new TableColumn(tblBPA, SWT.NONE);
					colBPAStax.setText("Stax");
					colBPAStax.setWidth(70);
				}
				
				{
					colBPATotal = new TableColumn(tblBPA, SWT.NONE);
					colBPATotal.setText("Total");
					colBPATotal.setWidth(70);
				}
				
				for (int index = 0; index < tblBPA.getColumnCount(); index++) {
					tblBPA.getColumn(index).addListener(SWT.Selection,
							this);
				}
				
				
				{
					lblFromDate = new Label(cvsBPA, SWT.NONE);
					lblFromDate.setText("From Date");
					lblFromDate.setBounds(10, 28, 55, 20);
				}
				{
					lblToDate = new Label(cvsBPA, SWT.NONE);
					lblToDate.setText("To Date");
					lblToDate.setBounds(173, 28, 40, 16);
				}

				{
					txtBPAFrom = new Text(cvsBPA, SWT.BORDER);
					txtBPAFrom.setBounds(67, 26, 70, 22);
					txtBPAFrom.setEditable(false);
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					txtBPAFrom.setText(date);

				}
				{
					btnBPAFromDate = new Button(cvsBPA, SWT.PUSH | SWT.CENTER);
					btnBPAFromDate.setBounds(137, 25, 31, 23);
					btnBPAFromDate.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btnBPAFromDate.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent e) {
							KalendarDialog cd = new KalendarDialog(new Shell());
							Object obj = cd.open();
							if (obj != null) {
								String date = obj.toString();
								txtBPAFrom.setText(date);
								int index = 0;
								String fromDate = txtBPAFrom.getText();
								index = fromDate.indexOf("-");
								int dt = Integer.parseInt(fromDate.substring(0,
										index));
								int month = Integer.parseInt(fromDate
										.substring(index + 1, index + 3));
								int year = Integer.parseInt(fromDate
										.substring(index + 4));
								// System.out.println("d===>" + dt + "-" +
								// (month - 1) + "-" + (year - 1));

							}
						}
					});
				}

				{
					txtBPATo = new Text(cvsBPA, SWT.BORDER);
					txtBPATo.setBounds(215, 25, 70, 22);
					txtBPATo.setEditable(false);
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					txtBPATo.setText(date);

				}
				{
					btnBPATo = new Button(cvsBPA, SWT.PUSH | SWT.CENTER);
					btnBPATo.setBounds(285, 24, 32, 23);
					btnBPATo.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btnBPATo.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent e) {
							KalendarDialog cd = new KalendarDialog(shell);
							Object obj = cd.open();
							if (obj != null) {
								String date = obj.toString();
								txtBPATo.setText(date);
							}
						}
					});
				}

				{
					label7 = new Label(cvsBPA, SWT.NONE);
					label7.setText("Branch");
					label7.setBounds(322, 28, 39, 15);
				}
				{
					cbBPABranch = new Combo(cvsBPA, SWT.READ_ONLY);
					cbBPABranch.setBounds(361, 27, 130, 20);
					//cbBPABranch.addSelectionListener(this);
					//cbBPABranch.add("All");
					if(clientRights == 1 || clientRights == 1.0){
						
						try{
						
							getCurrentBranch_code(currentStationCode);
							cbBPABranch.select(0);
							//populateStationForCLR();
							cbBPABranch.addSelectionListener(this);
						}
						catch(Exception e){
							e.printStackTrace();
						}
						
						
					}
					else{
					
						try{
							StationsDTO[] dto = handler.getAllBranches();
							if (null != dto) {
								cbBPABranch.add("All");
								for (int i = 0; i < dto.length; i++) {
									cbBPABranch.add(dto[i].getName() + " - "
											+ dto[i].getId());
								}
							}
							cbBPABranch.addSelectionListener(this);
						}
						catch(Exception e){
							e.printStackTrace();
						}
					}
				}

				
				{
					cbBPAReportType = new Combo(cvsBPA, SWT.READ_ONLY);
					cbBPAReportType.setBounds(500, 27, 90, 20);
					cbBPAReportType.addSelectionListener(this);
					if(clientRights == 1 || clientRights == 1.0){
						cbBPAReportType.add("Detailed");
						cbBPAReportType.select(0);
					}
					else{
						cbBPAReportType.add("Consolidated");
						cbBPAReportType.add("Detailed");
					}
					
				}
				{
					btnBPAGo = new Button(cvsBPA, SWT.PUSH | SWT.CENTER);
					btnBPAGo.setText("Go");
					btnBPAGo.setBounds(600, 26, 37, 23);
					btnBPAGo.addSelectionListener(this);
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
					
						cbBPABranch.add(stations[i].getName()+" - "+branch_code);
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


	
	private void handleBPA() {
		String branch = "";
		try {
			if (!cbBPABranch.getText().equals("")) {
				SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
				Date from = dt.parse(txtBPAFrom.getText());
				Date to = dt.parse(txtBPATo.getText());
				branch = cbBPABranch.getText();

				if (branch.equalsIgnoreCase("All")) {
						branch = "All";
					

				} else {
					int index = branch.indexOf(" - ");
					branch = branch.substring(index + 3);
				}
				BookingDTO[] bpaDto = getBPAReport(from, to, branch);

				if (tblBPA != null)
					tblBPA.removeAll();

				if (bpaDto != null) {
					populateBPAReport(bpaDto, from, to);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private BookingDTO[] getBPAReport(Date from, Date to,String branch)throws Exception {
		//int monthDiff = 0;
		try {
			
			
			/*Date curDate = new Date();
			int m1 = from.getYear() * 12 + from.getMonth();
	        int m2 = curDate.getYear() * 12 + curDate.getMonth();
	        monthDiff = m2 - m1;*/
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		Date date =BeanUtil.getThree_month_updated();
		if(to.before(date) ){
			//System.out.println("in ddr histry-->"+date);
			bpaDto = handler.getBPAReportHistory(from, to, branch);
		}else if(from.after(date)){
			//System.out.println("in ddr histry-->"+date);
			bpaDto = handler.getBPAReport(from, to, branch);
		}else{
			bpaDto = handler.getBPAReportUnion(from, to, branch);
		}
		return bpaDto;
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
	
	private void populateStAllStations(Table table ) {
		// TODO Auto-generated method stub
		StationsDTO[] stations = null;
		try {
			stations = handler.getAllStations();

			int size = 0;
			if (null != stations) {
				if (table != null) {
					table.removeAll();
				}
				size = stations.length;
				
				for (int i = 0; i < size; i++) {
					TableItem item = new TableItem(table, SWT.NONE);
					item.setText(0, String.valueOf(i + 1));
					item.setText(1, stations[i].getName()+ " - "
							+ stations[i].getId());
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		
	}

	private void populateStAllBranches(Table table) {
		// TODO Auto-generated method stub
		StationsDTO[] stations = null;
		try {

			stations = handler.getAllBranches();

			int size = 0;
			if (null != stations) {
				if (table != null) {
					table.removeAll();
				}
				size = stations.length;
				for (int i = 0; i < size; i++) {

					TableItem item = new TableItem(table, SWT.NONE);
					item.setText(0, String.valueOf(i + 1));
					item.setText(1, stations[i].getName()+ " - "
							+ stations[i].getId());

				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		
	}
	
	private void populateStSelectedStations(String branchCode, Table table) {
		StationsDTO[] stations = null;
		try {

			stations = handler.getAllStations();

			int size = 0;
			if (null != stations) {
				if (table != null) {
					table.removeAll();
				}
				size = stations.length;
				for (int i = 0, sNo = 1; i < size; i++) {
					if (branchCode.equals(stations[i].getBranch_code())) {
						TableItem item = new TableItem(table, SWT.NONE);
						item.setText(0, String.valueOf(sNo));
						//item.setText(1, branchCode);
						item.setText(1, stations[i].getName()+ " - "
								+stations[i].getId());

						sNo += 1;
					}
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
	private void populateBPAReport(BookingDTO[] dto, Date from,
			Date to) throws Exception {
		DecimalFormat df = new DecimalFormat("0.00");
		int len = dto.length;
		
		String Reptype = cbBPAReportType.getText();
		Boolean isConsolidated = false;
		if(Reptype.equalsIgnoreCase("Consolidated")) {
			 populateStAllBranches(tblBPA);
			 isConsolidated = true;
		 } else{
			 if (cbBPABranch.getText().equalsIgnoreCase("All")){
				 populateStAllStations(tblBPA);
			 }else{
				 	String branch_Code = cbBPABranch.getText();
					int index = branch_Code.indexOf(" - ");
					branch_Code = branch_Code.substring(index + 3);
					populateStSelectedStations(branch_Code, tblBPA);
				 
			 }
				 
		 }
		
		Boolean isBranch = false; 
		
		TableItem[] item = tblBPA.getItems();
		
		int itemlen = item.length;
		int lr_no[] = new int[itemlen]; 
		int noa[] = new int[itemlen];
		float Actwt[] = new float[itemlen];
		float Crwt[] = new float[itemlen];
		float bft[] = new float[itemlen];
		float lrc[] = new float[itemlen];
		float dhc[] = new float[itemlen];
		float ccc[] = new float[itemlen];
		float dcc[] = new float[itemlen];
		float ddc[] = new float[itemlen];
		float iec[] = new float[itemlen];
		float lc[] = new float[itemlen];
		float stax[] = new float[itemlen];
		float tot[] = new float[itemlen];
		String stn_code = "";
		
		
		
		
		for (int i = 0; i < len; i++) {
			
			for(int j = 0; j < itemlen; j++){
				
							
				if(isConsolidated == true) {
				 	stn_code = getBranch_code(dto[i].getFrom());
				 	isBranch = true;
				}
				else
					stn_code = dto[i].getFrom();
				
				String branchCode = item[j].getText(1);
				int index = branchCode.indexOf(" - ");
				branchCode = branchCode.substring(index + 3);
				
				if (stn_code.equalsIgnoreCase(branchCode)){
					if(isBranch == true){
						
						
						if(! item[j].getText(2).equalsIgnoreCase(" ")){
							
							lr_no[j] = lr_no[j] + dto[i].getRate_type();
							item[j].setText(2, String.valueOf(lr_no[j]));
						}
						if(! item[j].getText(3).equalsIgnoreCase(" ")){
							noa[j] = noa[j] + dto[i].getNo_of_articles();
							item[j].setText(3, String.valueOf(noa[j]));
						}
						if(! item[j].getText(4).equalsIgnoreCase(" ")){
							Actwt[j] = Actwt[j] + dto[i].getActual_weight();
							item[j].setText(4, df.format((Actwt[j])));
						}
						if(! item[j].getText(5).equalsIgnoreCase(" ")){
							Crwt[j] = Crwt[j] + dto[i].getCharged_weight();
							item[j].setText(5, df.format((Crwt[j])));
						}
						if(! item[j].getText(6).equalsIgnoreCase(" ")){
							bft[j] = bft[j] + dto[i].getBft();
							item[j].setText(6, df.format((bft[j])));
						}
						if(! item[j].getText(7).equalsIgnoreCase(" ")){
							lrc[j] = lrc[j] + dto[i].getLrc();
							item[j].setText(7, df.format((lrc[j])));
						}
						if(! item[j].getText(8).equalsIgnoreCase(" ")){
							dhc[j] = dhc[j] + dto[i].getDhc();
							item[j].setText(8, df.format((dhc[j])));
						}
						if(! item[j].getText(9).equalsIgnoreCase(" ")){
							ccc[j] = ccc[j] + dto[i].getCcc();
							item[j].setText(9, df.format((ccc[j])));
						}
						if(! item[j].getText(10).equalsIgnoreCase(" ")){
							dcc[j] = dcc[j] + dto[i].getDcc();
							item[j].setText(10, df.format((dcc[j])));
						}
						if(! item[j].getText(11).equalsIgnoreCase(" ")){
							ddc[j] = ddc[j] + dto[i].getDdc();
							item[j].setText(11, df.format((ddc[j])));
						}
						if(! item[j].getText(12).equalsIgnoreCase(" ")){
							//System.out.println("Iec-->"+iec[j]);
							iec[j] = iec[j] + dto[i].getIec();
							item[j].setText(12, df.format((iec[j])));
						}
						if(! item[j].getText(13).equalsIgnoreCase(" ")){
							//System.out.println("lc-->"+lc[j]);
							lc[j] = lc[j] + dto[i].getLc();
							item[j].setText(13, df.format((lc[j])));
						}
						if(! item[j].getText(14).equalsIgnoreCase(" ")){
							stax[j] = stax[j] + dto[i].getStax();
							item[j].setText(14, df.format((stax[j])));
						}
						if(! item[j].getText(15).equalsIgnoreCase(" ")){
							tot[j] = tot[j] + dto[i].getTotal();
							item[j].setText(15, df.format((tot[j])));
						}				
						
									
					}
					else{
						
						if(! item[j].getText(2).equalsIgnoreCase(" ")){
							
							item[j].setText(2, String.valueOf(dto[i].getRate_type()));
						}
						if(! item[j].getText(3).equalsIgnoreCase(" ")){
							
							item[j].setText(3, String.valueOf(dto[i].getNo_of_articles()));
						}
						if(! item[j].getText(4).equalsIgnoreCase(" ")){
							
							item[j].setText(4, df.format((dto[i].getActual_weight())));
						}
						if(! item[j].getText(5).equalsIgnoreCase(" ")){
							
							item[j].setText(5, df.format((dto[i].getCharged_weight())));
						}
						if(! item[j].getText(6).equalsIgnoreCase(" ")){
							
							item[j].setText(6, df.format((dto[i].getBft())));
						}
						if(! item[j].getText(7).equalsIgnoreCase(" ")){
							
							item[j].setText(7, df.format((dto[i].getLrc())));
						}
						if(! item[j].getText(8).equalsIgnoreCase(" ")){
							
							item[j].setText(8, df.format((dto[i].getDhc())));
						}
						if(! item[j].getText(9).equalsIgnoreCase(" ")){
							
							item[j].setText(9, df.format((dto[i].getCcc())));
						}
						if(! item[j].getText(10).equalsIgnoreCase(" ")){
							
							item[j].setText(10, df.format((dto[i].getDcc())));
						}
						if(! item[j].getText(11).equalsIgnoreCase(" ")){
							
							item[j].setText(11, df.format((dto[i].getDdc())));
						}
						if(! item[j].getText(12).equalsIgnoreCase(" ")){
							
							item[j].setText(12, df.format((dto[i].getIec())));
						}
						if(! item[j].getText(13).equalsIgnoreCase(" ")){
							
							item[j].setText(13, df.format((dto[i].getLc())));
						}
						if(! item[j].getText(14).equalsIgnoreCase(" ")){
							
							item[j].setText(14, df.format((dto[i].getStax())));
						}
						if(! item[j].getText(15).equalsIgnoreCase(" ")){
							
							item[j].setText(15, df.format((dto[i].getTotal())));
						}				
					
							
						
					}
					
				}
				
			}
				
		}
		
		calculatTotal();
	
		
	}
	
	private void calculatTotal() {
		// TODO Auto-generated method stub
			TableItem[] items = tblBPA.getItems();
			int tot1 = 0;
			int tot2 = 0;
			double tot3 = 0;
			double tot4 = 0;
			double tot5 = 0;
			double tot6 = 0;
			double tot7 = 0;
			double tot8 = 0;
			double tot9 = 0;
			double tot10 = 0;
			double tot11 = 0;
			double tot12 = 0;
			double tot13 = 0;
			double tot14 = 0;
			
			if (items != null) {
				for (int i = 0; i < items.length; i++) {
					
					if (!items[i].getText(2).equals(""))
						tot1 = tot1 + Integer.parseInt(items[i].getText(2));
					if (!items[i].getText(3).equals(""))
						tot2 = tot2 + Integer.parseInt(items[i].getText(3));
					if (!items[i].getText(4).equals(""))
						tot3 = tot3 + (double) Float.parseFloat(items[i].getText(4));
					if (!items[i].getText(5).equals(""))
						tot4 = tot4 + (double) Float.parseFloat(items[i].getText(5));
					if (!items[i].getText(6).equals(""))
						tot5 = tot5 + (double) Float.parseFloat(items[i].getText(6));
					if (!items[i].getText(7).equals(""))
						tot6 = tot6 + (double) Float.parseFloat(items[i].getText(7));
					if (!items[i].getText(8).equals(""))
						tot7 = tot7 + (double) Float.parseFloat(items[i].getText(8));
					if (!items[i].getText(9).equals(""))
						tot8 = tot8 + (double) Float.parseFloat(items[i].getText(9));
					if (!items[i].getText(10).equals(""))
						tot9 = tot9 + (double) Float.parseFloat(items[i].getText(10));
					if (!items[i].getText(11).equals(""))
						tot10 = tot10 + (double) Float.parseFloat(items[i].getText(11));
					if (!items[i].getText(12).equals(""))
						tot11 = tot11 + (double) Float.parseFloat(items[i].getText(12));
					if (!items[i].getText(13).equals(""))
						tot12 = tot12 + (double) Float.parseFloat(items[i].getText(13));
					if (!items[i].getText(14).equals(""))
						tot13 = tot13 + (double) Float.parseFloat(items[i].getText(14));
					if (!items[i].getText(15).equals(""))
						tot14 = tot14 + (double) Float.parseFloat(items[i].getText(15));
					
					
					
				}

			}

			final TableItem item1 = new TableItem(tblBPA, SWT.NONE);
			Font font1 = new Font(Display.getCurrent(), "Tahoma", 8, SWT.BOLD);
			item1.setFont(font1);
			item1.setText(1, "TOTAL");
			item1.setText(2, getRoundedValue(tot1));
			item1.setText(3, getRoundedValue(tot2));
			item1.setText(4, getRoundValue(tot3));
			item1.setText(5, getRoundValue(tot4));
			item1.setText(6, getRoundValue(tot5));
			item1.setText(7, getRoundValue(tot6));
			item1.setText(8, getRoundValue(tot7));
			item1.setText(9, getRoundValue(tot8));
			item1.setText(10, getRoundValue(tot9));
			item1.setText(11, getRoundValue(tot10));
			item1.setText(12, getRoundValue(tot11));
			item1.setText(13, getRoundValue(tot12));
			item1.setText(14, getRoundValue(tot13));
			item1.setText(15, getRoundValue(tot14));
			
			new sortListener().fillZerosOnEmpty(tblBPA);
	}
	
	private String getRoundValue(double cardRate) {
		DecimalFormat decimalFormat;
		decimalFormat = new DecimalFormat("0.00");
		cardRate = cardRate * 100;
		cardRate = (double) Math.round(cardRate);
		cardRate = cardRate / 100;

		return decimalFormat.format(cardRate);
	}
	
	private String getRoundedValue(float cardRate) {
		DecimalFormat decimalFormat;
		decimalFormat = new DecimalFormat("0.00");
		cardRate = cardRate * 100;
		cardRate = (float) Math.round(cardRate);
		cardRate = cardRate / 100;

		return decimalFormat.format(cardRate);
	}
	
	private String[] getBPAHeading(){
		
		String list[] = new String[4];
		
		list[0] =txtBPAFrom.getText();
		list[1] =txtBPATo.getText();
		String branchCode = cbBPABranch.getText();
		if (!branchCode.equalsIgnoreCase("All")) {
			int index = branchCode.indexOf(" - ");
			list[2] = branchCode.substring(index + 3);
		}
		else
			list[2] ="All";
		
		
		if(!cbBPABranch.getText().equals("All"))
			list[3] = "Detailed";
		else
			list[3] =cbBPAReportType.getText();
		
			
		return list;
	}
	
	@Override
	public void handleEvent(Event e) {
		// TODO Auto-generated method stub
		String selection = tabReport.getSelection()[0].getText();
		TableColumn column = (TableColumn) e.widget;
		
		if (column == colBPAsno) {
			new sortListener().sortByFloat(0, tblBPA);
		} else if (column == colBPABranch) {
			new sortListener().sortByString(1, tblBPA);
		} else if (column == colBPANolr) {
			new sortListener().sortByFloat(2, tblBPA);
		} else if (column == colBPANoa) {
			new sortListener().sortByFloat(3, tblBPA);
		} else if (column == colBPAActwt) {
			new sortListener().sortByFloat(4, tblBPA);
		} else if (column == colBPACrgwt) {
			new sortListener().sortByFloat(5, tblBPA);
		} else if (column == colBPABft) {
			new sortListener().sortByFloat(6, tblBPA);
		} else if (column == colBPALrc) {
			new sortListener().sortByFloat(7, tblBPA);
		} else if (column == colBPADhc) {
			new sortListener().sortByFloat(8, tblBPA);
		}else if (column == colBPACcc) {
			new sortListener().sortByFloat(9, tblBPA);
		}else if (column == colBPADcc) {
			new sortListener().sortByFloat(10, tblBPA);
		}else if (column == colBPADdc) {
			new sortListener().sortByFloat(11, tblBPA);
		}else if (column == colBPADdc) {
			new sortListener().sortByFloat(12, tblBPA);
		}
		else if (column == colBPADdc) {
			new sortListener().sortByFloat(11, tblBPA);
		}else if (column == colBPADdc) {
			new sortListener().sortByFloat(11, tblBPA);
		}else if (column == colBPADdc) {
			new sortListener().sortByFloat(11, tblBPA);
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
		 if (source == cbBPABranch) {
			if (!cbBPABranch.getText().equalsIgnoreCase("All")) {
				cbBPAReportType.select(1);
				cbBPAReportType.setEnabled(false);
			} else {
				cbBPAReportType.select(0);
				cbBPAReportType.setEnabled(true);
			}
		} /*else if (source == cbBPAChoice) {
			colBPAFigure.setText(cbBPAChoice.getText());
		}*/ else if (source == btnBPAGo) {
			new sortListener().
			display("Generating Report...", STATUS_SUCCESS,
					SUCCESS_FONT_COLOR,lblStatusBar);

			handleBPA();
			new sortListener().display("Report Loaded Successfully!",
					STATUS_SUCCESS, SUCCESS_FONT_COLOR,lblStatusBar);
		}else if (source == btnExportXL) {
			try {
				//handleExcel();
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				list = new sortListener().getBPATable(tblBPA);
				value = getBPAHeading();
				//String[] param = { cbBPAChoice.getText() };
				new sortListener().prepareExcel(list, "BPA", BPA_EXCEL_JRXML, null,value,shell,lblStatusBar);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else if (source == btnPrint) {
			try {
				//handlePrint();
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				list = new sortListener().getBPATable(tblBPA);
				value = getBPAHeading();
				//String[] param = { cbBPAChoice.getText() };
				handler.preparePrint(list, "BPA", BPA_EXCEL_JRXML, null,value);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		else if (source == btnExportPDF) {
			try {
				//handlePDF();
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				list = new sortListener().getBPATable(tblBPA);
				value = getBPAHeading();
				//String[] param = { cbBPAChoice.getText() };
				new sortListener().preparePDF(list, "BPA", BPA_EXCEL_JRXML, null,value,shell,lblStatusBar);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}

}
