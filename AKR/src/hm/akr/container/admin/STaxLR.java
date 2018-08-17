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
import hm.akr.dto.StationsDTO;
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

public class STaxLR extends Composite implements IUIConstants,Listener,SelectionListener {

	private String Service_Tax_LR ="Service Tax Annexure - LR Wise";
	private static final String SERVICETAX_LR_JRXML = "hm/akr/resources/printer/ServicetaxLR.jrxml";
	private TabItem tiServicetaxLR;
	private Canvas cvsServicetaxLR;
	private Table tblServicetaxLR;
	private TableColumn colSertaxLRSno;
	private TableColumn colSertaxLRLrno;
	private TableColumn colSertaxLRLrdate;
	private TableColumn colSertaxLRFrom;
	private TableColumn colSertaxLRTo;
	private TableColumn colSertaxLRCnor;
	private TableColumn colSertaxLRCnee;
	private TableColumn colSertaxLRTft;
	private TableColumn colSertaxLRTftof25;
	private TableColumn colSertaxLRService;
	private TableColumn colSertaxLREdu;
	private TableColumn colSertaxLRHrsec;
	private TableColumn colSertaxLRTotalst;
	private Combo cbStLRBranch;
	private Combo cbStLRStation;
	private Label lblStLRBranch;
	private Label lblStLRStation;
	private Label lblStLRFromDate;
	private Label lblStLRToDate;
	private Text txtStLRFrom;
	private Button btnStLRFromDate;
	private Text txtStLRTo;
	private Button btnStLRGo;
	private Button btnStLRTo;
	
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
	
	 public STaxLR(Composite composite,int style) {
		super(composite,style);
		// TODO Auto-generated constructor stub
		
		try {
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
			tiServicetaxLR = new TabItem(tabReport, SWT.NONE);
			tiServicetaxLR.setText(Service_Tax_LR);

			cvsServicetaxLR = new Canvas(tabReport, SWT.NONE);
			tiServicetaxLR.setControl(cvsServicetaxLR);

			{
				{
					tblServicetaxLR = new Table(cvsServicetaxLR, SWT.FULL_SELECTION
							| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);

					tblServicetaxLR.setHeaderVisible(true);
					tblServicetaxLR.setLinesVisible(true);
					tblServicetaxLR.setBounds(10, 72, 765, 400);
					{
						colSertaxLRSno = new TableColumn(tblServicetaxLR, SWT.NONE);
						colSertaxLRSno.setText("S NO");
						colSertaxLRSno.setWidth(49);
						colSertaxLRSno
								.addListener(SWT.Selection, this);

					}
					{
						colSertaxLRLrno = new TableColumn(tblServicetaxLR, SWT.NONE);
						colSertaxLRLrno.setText("LR No");
						colSertaxLRLrno.setWidth(90);
						colSertaxLRLrno.addListener(SWT.Selection,
								this);
					}
					{
						colSertaxLRLrdate = new TableColumn(tblServicetaxLR, SWT.NONE);
						colSertaxLRLrdate.setText("LR Date");
						colSertaxLRLrdate.setWidth(90);
						colSertaxLRLrdate.addListener(SWT.Selection,
								this);
					}
					{
						colSertaxLRFrom = new TableColumn(tblServicetaxLR, SWT.NONE);
						colSertaxLRFrom.setText("From");
						colSertaxLRFrom.setWidth(90);
						colSertaxLRFrom.addListener(SWT.Selection,
								this);
					}
					{
						colSertaxLRTo = new TableColumn(tblServicetaxLR, SWT.NONE);
						colSertaxLRTo.setText("To");
						colSertaxLRTo.setWidth(90);
						colSertaxLRTo.addListener(SWT.Selection,
								this);
					}
					{
						colSertaxLRCnor= new TableColumn(tblServicetaxLR, SWT.NONE);
						colSertaxLRCnor.setText("Cnor");
						colSertaxLRCnor.setWidth(90);
						colSertaxLRCnor.addListener(SWT.Selection,
								this);
					}
					{
						colSertaxLRCnee = new TableColumn(tblServicetaxLR, SWT.NONE);
						colSertaxLRCnee.setText("Cnee");
						colSertaxLRCnee.setWidth(90);
						colSertaxLRCnee.addListener(SWT.Selection,
								this);
					}
					{
						colSertaxLRTft = new TableColumn(tblServicetaxLR, SWT.NONE);
						colSertaxLRTft.setText("Total before Tax");
						colSertaxLRTft.setWidth(90);
						colSertaxLRTft.addListener(SWT.Selection,
								this);

					}
					
					/*{
						colSertaxLRTftof25 = new TableColumn(tblServicetaxLR, SWT.NONE);
						colSertaxLRTftof25.setText("25% of Total Freight");
						colSertaxLRTftof25.setWidth(90);
						colSertaxLRTftof25.addListener(SWT.Selection,
								new sortListener());

					}*/
					{
						colSertaxLRService = new TableColumn(tblServicetaxLR, SWT.NONE);
						colSertaxLRService.setText("Service Tax");
						colSertaxLRService.setWidth(90);
						colSertaxLRService
								.addListener(SWT.Selection, this);

					}
					/*{
						colSertaxLREdu = new TableColumn(tblServicetaxLR, SWT.NONE);
						colSertaxLREdu.setText("Edu.Cess");
						colSertaxLREdu.setWidth(80);
						colSertaxLREdu.addListener(SWT.Selection, new sortListener());

					}
					{
						colSertaxLRHrsec = new TableColumn(tblServicetaxLR, SWT.NONE);
						colSertaxLRHrsec.setText("Hr.Sec.Edu.Cess");
						colSertaxLRHrsec.setWidth(80);
						colSertaxLRHrsec
								.addListener(SWT.Selection, new sortListener());

					}*/
					{
						colSertaxLRTotalst = new TableColumn(tblServicetaxLR, SWT.NONE);
						colSertaxLRTotalst.setText("Total after Tax");
						colSertaxLRTotalst.setWidth(80);
						colSertaxLRTotalst.addListener(SWT.Selection,
								this);

					}

		
				}

				{
					lblStLRFromDate = new Label(cvsServicetaxLR, SWT.NONE);
					lblStLRFromDate.setText("From Date");
					lblStLRFromDate.setBounds(15, 25, 55, 20);
				}
				{
					lblStLRToDate = new Label(cvsServicetaxLR, SWT.NONE);
					lblStLRToDate.setText("To Date");
					lblStLRToDate.setBounds(183, 25, 40, 16);
				}

				{
					txtStLRFrom = new Text(cvsServicetaxLR, SWT.BORDER);
					txtStLRFrom.setBounds(80, 25, 70, 22);
					txtStLRFrom.setEditable(false);
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					txtStLRFrom.setText(date);

				}
				{
					btnStLRFromDate = new Button(cvsServicetaxLR, SWT.PUSH
							| SWT.CENTER);
					btnStLRFromDate.setBounds(150, 25, 31, 23);
					btnStLRFromDate.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btnStLRFromDate.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent e) {
							KalendarDialog cd = new KalendarDialog(shell, 1);
							Object obj = cd.open();
							if (obj != null) {
								String date = obj.toString();
								txtStLRFrom.setText(date);
							}
						}
					});
				}

				{
					txtStLRTo = new Text(cvsServicetaxLR, SWT.BORDER);
					txtStLRTo.setBounds(240, 25, 70, 22);
					txtStLRTo.setEditable(false);
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					txtStLRTo.setText(date);

				}
				{
					btnStLRTo = new Button(cvsServicetaxLR, SWT.PUSH | SWT.CENTER);
					btnStLRTo.setBounds(310, 25, 32, 23);
					btnStLRTo.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btnStLRTo.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent e) {
							KalendarDialog cd = new KalendarDialog(shell);
							Object obj = cd.open();
							if (obj != null) {
								String date = obj.toString();
								txtStLRTo.setText(date);
							}
						}
					});
				}
				
				{
					lblStLRBranch = new Label(cvsServicetaxLR, SWT.NONE);
					lblStLRBranch.setText("Select Branch");
					lblStLRBranch.setBounds(345, 25, 70, 17);
				}
				{
					cbStLRBranch = new Combo(cvsServicetaxLR,
							SWT.DROP_DOWN | SWT.READ_ONLY);
					cbStLRBranch.setBounds(420, 27, 85, 21);
					//cbStLRBranch.add("All");
					//cbStLRBranch.addSelectionListener(this);
					try{
						StationsDTO[] dto = handler.getAllBranches();
						if (null != dto) {
							cbStLRBranch.add("All");
							for (int i = 0; i < dto.length; i++) {
								cbStLRBranch.add(dto[i].getName() + " - "
										+ dto[i].getId());
							}
						}
						cbStLRBranch.addSelectionListener(this);
					}
					catch(Exception e){
						e.printStackTrace();
					}

				}

				{
					lblStLRStation = new Label(cvsServicetaxLR, SWT.NONE);
					lblStLRStation.setText("Select Station");
					lblStLRStation.setBounds(520, 27, 70, 21);
				}
				{
					cbStLRStation = new Combo(cvsServicetaxLR,
							SWT.DROP_DOWN | SWT.READ_ONLY);
					cbStLRStation.setBounds(600, 27, 85, 21);
					cbStLRStation.add("All");
					cbStLRStation.addSelectionListener(this);
				}

				{
					btnStLRGo = new Button(cvsServicetaxLR, SWT.PUSH | SWT.CENTER);
					btnStLRGo.setText("Go");
					btnStLRGo.setBounds(720, 27, 37, 23);
					btnStLRGo.addSelectionListener(this);
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

	private void handleBranchAction(Combo branch, Combo cbStation) {

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
					for (int i = 0; i < len; i++) {
						cbStation.add(station[i].getName() + " - "
								+ station[i].getId());
					}
				
				}
			
		} catch (Exception exception) {

		}
	}
	
	
	private void handleServicetaxLR() {
		// TODO Auto-generated method stub
		try {

			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date fromDt = dateFormat.parse(txtStLRFrom.getText());
			Date toDate = dateFormat.parse(txtStLRTo.getText());
			StationsDTO[] utDTO = null;
			utDTO = getServicetaxLR(fromDt,toDate);
			if (tblServicetaxLR != null)
					tblServicetaxLR.removeAll();
			if (utDTO != null) {
				populateStLR(utDTO);
			} else {
				// AdminComposite.display("No Records Found",
				// STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private StationsDTO[] getServicetaxLR(Date fromDt, Date toDate) {
		// TODO Auto-generated method stub
		//int monthDiff = 0;
		StationsDTO[] bookedLr = null;
		try {
			/*Date curDate = new Date();
			int m1 = fromDt.getYear() * 12 + fromDt.getMonth();
	        int m2 = curDate.getYear() * 12 + curDate.getMonth();
	        monthDiff = m2 - m1;*/
			Date date = BeanUtil.getThree_month_updated();
		if(toDate.before(date)){
			//System.out.println("in ddr histry-->"+date);
			bookedLr = handler.getServicetaxLRHistory(fromDt, toDate);
		}else if(fromDt.after(date)){
			bookedLr = handler.getServicetaxLR(fromDt, toDate);
		}else{
			bookedLr = handler.getServicetaxLRUnion(fromDt, toDate);
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookedLr;
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
	
	private void populateStLR(StationsDTO[] dto) throws Exception{
		// TODO Auto-generated method stub
		int len = dto.length;
		DecimalFormat df = new DecimalFormat("0.00");
		float totalfreightin25 = 0;
		float servicetax = 0;
		float educess = 0;
		float hreducess = 0;
		float totalst = 0;
		
		float tot1 = 0;
		float tot2 = 0;
		float tot3 = 0;
		/*float tot4 = 0;
		float tot5 = 0;
		float tot6 = 0;*/
		
		String branchcode = cbStLRBranch.getText();
		String Branch = branchcode;
		int index = branchcode.indexOf(" - ");
		branchcode = branchcode.substring(index + 3);
		String Stationcode = cbStLRStation.getText();
		String Station = Stationcode;
		int ind = Stationcode.indexOf(" - ");
		Stationcode = Stationcode.substring(ind + 3);
		float whSttotal = 0;
		for (int i = 0, j = 1; i < len; i++) {
			
			String stn_code = getBranch_code(dto[i].getFrom());
			
			if((Branch.equalsIgnoreCase("All"))&&(Station.equalsIgnoreCase("All"))){
				
				TableItem item = new TableItem(tblServicetaxLR, SWT.NONE);
				item.setText(0, String.valueOf(i + 1));
				item.setText(1, dto[i].getLrno());
				item.setText(2, String.valueOf(dto[i].getLrdate()));
				item.setText(3, dto[i].getFrom());
				item.setText(4, dto[i].getTo());
				item.setText(5, dto[i].getCnor());
				item.setText(6, dto[i].getCnee());
				whSttotal = dto[i].getTotalfreight() ;
				servicetax = dto[i].getServicetax();
				totalst = whSttotal - servicetax;
				tot1 = tot1 + totalst;
				item.setText(7, df.format(totalst));
				tot2 =tot2 + servicetax ; 
				item.setText(8,df.format(servicetax));
				/*totalfreightin25 = woSttotal / 4 ;
				tot2 = tot2 + totalfreightin25; 
				item.setText(8, df.format(totalfreightin25));
				servicetax = totalfreightin25 / 10;
				tot3 = tot3 + servicetax;
				item.setText(9, df.format(servicetax));
				educess	= servicetax / 50;
				tot4 = tot4 + educess;
				item.setText(10, df.format(educess));
				hreducess = educess / 100;
				tot5 = tot5 + hreducess; 
				item.setText(11, df.format(hreducess));
				totalst = servicetax + educess + hreducess;*/
				
				tot3 = tot3 + whSttotal;
				item.setText(9, df.format(whSttotal));

				j++;

			}
			else if((branchcode.equalsIgnoreCase(stn_code)) && (Station.equalsIgnoreCase("All"))) {
			
					TableItem item = new TableItem(tblServicetaxLR, SWT.NONE);
					item.setText(0, String.valueOf(i + 1));
					item.setText(1, dto[i].getLrno());
					item.setText(2, String.valueOf(dto[i].getLrdate()));
					item.setText(3, dto[i].getFrom());
					item.setText(4, dto[i].getTo());
					item.setText(5, dto[i].getCnor());
					item.setText(6, dto[i].getCnee());
					whSttotal = dto[i].getTotalfreight() ;
					servicetax = dto[i].getServicetax();
					totalst = whSttotal - servicetax;
					tot1 = tot1 + totalst;
					item.setText(7, df.format(totalst));
					tot2 =tot2 + servicetax ; 
					item.setText(8,df.format(servicetax));
					/*totalfreightin25 = woSttotal / 4 ;
					tot2 = tot2 + totalfreightin25; 
					item.setText(8, df.format(totalfreightin25));
					servicetax = totalfreightin25 / 10;
					tot3 = tot3 + servicetax;
					item.setText(9, df.format(servicetax));
					educess	= servicetax / 50;
					tot4 = tot4 + educess;
					item.setText(10, df.format(educess));
					hreducess = educess / 100;
					tot5 = tot5 + hreducess; 
					item.setText(11, df.format(hreducess));
					totalst = servicetax + educess + hreducess;*/
					
					tot3 = tot3 + whSttotal;
					item.setText(9, df.format(whSttotal));

					j++;
				}
			else if ((Stationcode.equalsIgnoreCase(dto[i].getFrom())) && (!Branch.equals(" "))) {
				
				TableItem item = new TableItem(tblServicetaxLR, SWT.NONE);
				item.setText(0, String.valueOf(i + 1));
				item.setText(1, dto[i].getLrno());
				item.setText(2, String.valueOf(dto[i].getLrdate()));
				item.setText(3, dto[i].getFrom());
				item.setText(4, dto[i].getTo());
				item.setText(5, dto[i].getCnor());
				item.setText(6, dto[i].getCnee());
				
				whSttotal = dto[i].getTotalfreight() ;
				servicetax = dto[i].getServicetax();
				totalst = whSttotal - servicetax;
				tot1 = tot1 + totalst;
				item.setText(7, df.format(totalst));
				tot2 =tot2 + servicetax ;
				item.setText(8,df.format(servicetax));
				/*totalfreightin25 = woSttotal / 4 ;
				tot2 = tot2 + totalfreightin25; 
				item.setText(8, df.format(totalfreightin25));
				servicetax = totalfreightin25 / 10;
				tot3 = tot3 + servicetax;
				item.setText(9, df.format(servicetax));
				educess	= servicetax / 50;
				tot4 = tot4 + educess;
				item.setText(10, df.format(educess));
				hreducess = educess / 100;
				tot5 = tot5 + hreducess; 
				item.setText(11, df.format(hreducess));
				totalst = servicetax + educess + hreducess;*/
				
				tot3 = tot3 + whSttotal;
				item.setText(9, df.format(whSttotal));

				j++;
			}
			
			} 
		final TableItem item1 = new TableItem(tblServicetaxLR, SWT.NONE);
		Font font1 = new Font(Display.getCurrent(), "Tahoma", 8, SWT.BOLD);
		item1.setFont(font1);
		item1.setText(6, "TOTAL");
		item1.setText(7, getRoundedValue(tot1));
		item1.setText(8, getRoundedValue(tot2));
		item1.setText(9, getRoundedValue(tot3));
		/*item1.setText(10, getRoundedValue(tot4));
		item1.setText(11, getRoundedValue(tot5));
		item1.setText(12, getRoundedValue(tot6));*/
		new sortListener().fillZerosOnEmpty(tblServicetaxLR);
	
	}
	
	private String getRoundedValue(float cardRate) {
		DecimalFormat decimalFormat;
		decimalFormat = new DecimalFormat("0.00");
		cardRate = cardRate * 100;
		cardRate = (float) Math.round(cardRate);
		cardRate = cardRate / 100;

		return decimalFormat.format(cardRate);
	}
	
	
	private ArrayList<StationsDTO> getServicetaxLRTable(Table tblServicetaxLR) {
		// TODO Auto-generated method stub
			StationsDTO dto = null;
			ArrayList<StationsDTO> list = new ArrayList<StationsDTO>();
			int length = 0;

			TableItem[] items = tblServicetaxLR.getItems();
			if (items != null) {
				length = items.length;
			}
			if (length > 0) {
				for (int i = 0; i < length; i++) {
					dto = new StationsDTO();
					
					dto.setLrno(items[i].getText(1));
					
					dto.setLrdate(items[i].getText(2));
					
					dto.setFrom(items[i].getText(3)); 
					
					dto.setTo(items[i].getText(4));
					
					dto.setCnor(items[i].getText(5));
					
					dto.setCnee(items[i].getText(6));
					
					if (!items[i].getText(7).equals(""))
						dto.setTotalfreight(Float.parseFloat(items[i].getText(7)));
					
					/*if (!items[i].getText(8).equals(""))
						dto.setTotalfreightin25(Float.parseFloat(items[i].getText(8)));*/
					
					if (!items[i].getText(8).equals(""))
						dto.setServicetax(Float.parseFloat(items[i].getText(8)));
					
					/*if (!items[i].getText(10).equals(""))
						dto.setEducess(Float.parseFloat(items[i].getText(10)));
					
					if (!items[i].getText(11).equals(""))
						dto.setHreducess(Float.parseFloat(items[i].getText(11)));*/
					
					if (!items[i].getText(9).equals(""))
						dto.setTotalst(Float.parseFloat(items[i].getText(9)));
					
					list.add(dto);
				}
			}

			return list;
		
	}
	
	private String[] getServicetaxLRHeading(Table tblServicetax) {
		// TODO Auto-generated method stub
		String list[] = new String[4];
		
		list[0] =txtStLRFrom.getText();
		list[1] =txtStLRTo.getText();
		String branchCode = cbStLRBranch.getText();
		if (!branchCode.equalsIgnoreCase("All")) {
			int index = branchCode.indexOf(" - ");
			list[2] = branchCode.substring(index + 3);
		}
		else
			list[2] ="All";
			
		String stationCode = cbStLRStation.getText();
		if (!stationCode.equalsIgnoreCase("All")) {
			int index = stationCode.indexOf(" - ");
			list[3] = stationCode.substring(index + 3);
		}
		else
			list[3] ="All";
			
			
		return list;
	
	}
	
	@Override
	public void handleEvent(Event e) {
		// TODO Auto-generated method stub
		String selection = tabReport.getSelection()[0].getText();
		TableColumn column = (TableColumn) e.widget;
		TableColumn[] cols = tblServicetaxLR.getColumns();
		int len = cols.length;

		for (int i = 0; i < len; i++) {
			if (column == cols[i]) {
				 if  (i == 1) {
					 new sortListener().sortByLrNo(i, tblServicetaxLR);
				} else if (i > 6 || i == 0) {
					new sortListener().sortByFloat(i, tblServicetaxLR);
				} else if (i == 2) {
					new sortListener().sortByDate(i, tblServicetaxLR);
				}else if (i > 2 && i < 7) {
					new sortListener().sortByString(i, tblServicetaxLR);
				}
			}

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
		 if(source == cbStLRBranch ){
				handleBranchAction(cbStLRBranch, cbStLRStation);
			}else if(source == btnStLRGo){
				
				new sortListener().display("Generating Report...", STATUS_SUCCESS,
						SUCCESS_FONT_COLOR,lblStatusBar);
				handleServicetaxLR();
				
				new sortListener().display("Report Loaded Successfully!",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR,lblStatusBar);
				
			}else if (source == btnExportXL) {
				try {
					//handleExcel();
					ArrayList<StationsDTO> list = null;
					String[] value = null;
					value = getServicetaxLRHeading(tblServicetaxLR);
					list = getServicetaxLRTable(tblServicetaxLR);
					new sortListener().prepareExcel(list, "Service Tax Annexure - LR Wise", SERVICETAX_LR_JRXML, null,value,shell,lblStatusBar);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}else if (source == btnPrint) {
				try {
					//handlePrint();
					ArrayList<StationsDTO> list = null;
					String[] value = null;
					value = getServicetaxLRHeading(tblServicetaxLR);
					list = getServicetaxLRTable(tblServicetaxLR);
					handler.preparePrint(list, "Service Tax Annexure - LR Wise", SERVICETAX_LR_JRXML, null,value);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
			else if (source == btnExportPDF) {
				try {
					//handlePDF();
					ArrayList<StationsDTO> list = null;
					String[] value = null;
					value = getServicetaxLRHeading(tblServicetaxLR);
					list = getServicetaxLRTable(tblServicetaxLR);
					new sortListener().preparePDF(list, "Service Tax Annexure - LR Wise", SERVICETAX_LR_JRXML, null,value,shell,lblStatusBar);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}	
		
	}

}
