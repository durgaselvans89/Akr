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

public class STaxConsolidated extends Composite implements IUIConstants,Listener,SelectionListener {

	private TabItem tiServicetax;
	private Canvas cvsServicetax;
	private Table tblServicetax;
	private TableColumn colSertaxSno;
	private TableColumn colSertaxStname;
	private TableColumn colSertaxTft;
	private TableColumn colSertaxTftof25;
	private TableColumn colSertaxService;
	private TableColumn colSertaxEdu;
	private TableColumn colSertaxHrsec;
	private TableColumn colSertaxTotalst;
	private Combo cbStReptype;
	private Label lblStReptype;
	private Label lblStFromDate;
	private Label lblStToDate;
	private Text txtStFrom;
	private Button btnStFromDate;
	private Text txtStTo;
	private Button btnStGo;
	private Button btnStTo;
	private String Service_Tax = "Service Tax Annexure";
	private static final String SERVICETAX_JRXML = "hm/akr/resources/printer/Servicetax.jrxml";
	
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
	
	 public STaxConsolidated(Composite composite,int style) {
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
			tiServicetax = new TabItem(tabReport, SWT.NONE);
			tiServicetax.setText(Service_Tax);

			cvsServicetax = new Canvas(tabReport, SWT.NONE);
			tiServicetax.setControl(cvsServicetax);

			{
				{
					tblServicetax = new Table(cvsServicetax, SWT.FULL_SELECTION
							| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);

					tblServicetax.setHeaderVisible(true);
					tblServicetax.setLinesVisible(true);
					tblServicetax.setBounds(30, 72, 630, 400);
					{
						colSertaxSno = new TableColumn(tblServicetax, SWT.NONE);
						colSertaxSno.setText("S NO");
						colSertaxSno.setWidth(49);
						colSertaxSno
								.addListener(SWT.Selection, this);

					}
					{
						colSertaxStname = new TableColumn(tblServicetax, SWT.NONE);
						colSertaxStname.setText("Station Name");
						colSertaxStname.setWidth(160);
						colSertaxStname.addListener(SWT.Selection,
								this);
					}
					{
						colSertaxTft = new TableColumn(tblServicetax, SWT.NONE);
						colSertaxTft.setText("Total before Tax");
						colSertaxTft.setWidth(100);
						colSertaxTft.addListener(SWT.Selection,
								this);

					}
					
					/*{
						colSertaxTftof25 = new TableColumn(tblServicetax, SWT.NONE);
						colSertaxTftof25.setText("25% of Total Freight");
						colSertaxTftof25.setWidth(90);
						colSertaxTftof25.addListener(SWT.Selection,
								new sortListener());

					}*/
					{
						colSertaxService = new TableColumn(tblServicetax, SWT.NONE);
						colSertaxService.setText("Service Tax");
						colSertaxService.setWidth(100);
						colSertaxService
								.addListener(SWT.Selection, this);

					}
					/*{
						colSertaxEdu = new TableColumn(tblServicetax, SWT.NONE);
						colSertaxEdu.setText("Edu.Cess");
						colSertaxEdu.setWidth(80);
						colSertaxEdu.addListener(SWT.Selection, new sortListener());

					}
					{
						colSertaxHrsec = new TableColumn(tblServicetax, SWT.NONE);
						colSertaxHrsec.setText("Hr.Sec.Edu.Cess");
						colSertaxHrsec.setWidth(80);
						colSertaxHrsec
								.addListener(SWT.Selection, new sortListener());

					}*/
					{
						colSertaxTotalst = new TableColumn(tblServicetax, SWT.NONE);
						colSertaxTotalst.setText("Total after Tax");
						colSertaxTotalst.setWidth(100);
						colSertaxTotalst.addListener(SWT.Selection,
								this);

					}

		
				}

				{
					lblStFromDate = new Label(cvsServicetax, SWT.NONE);
					lblStFromDate.setText("From Date");
					lblStFromDate.setBounds(32, 28, 55, 20);
				}
				{
					lblStToDate = new Label(cvsServicetax, SWT.NONE);
					lblStToDate.setText("To Date");
					lblStToDate.setBounds(217, 28, 42, 16);
				}

				{
					txtStFrom = new Text(cvsServicetax, SWT.BORDER);
					txtStFrom.setBounds(100, 26, 70, 22);
					txtStFrom.setEditable(false);
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					txtStFrom.setText(date);

				}
				{
					btnStFromDate = new Button(cvsServicetax, SWT.PUSH
							| SWT.CENTER);
					btnStFromDate.setBounds(170, 25, 31, 23);
					btnStFromDate.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btnStFromDate.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent e) {
							KalendarDialog cd = new KalendarDialog(shell, 1);
							Object obj = cd.open();
							if (obj != null) {
								String date = obj.toString();
								txtStFrom.setText(date);
							}
						}
					});
				}

				{
					txtStTo = new Text(cvsServicetax, SWT.BORDER);
					txtStTo.setBounds(275, 25, 70, 22);
					txtStTo.setEditable(false);
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					txtStTo.setText(date);

				}
				{
					btnStTo = new Button(cvsServicetax, SWT.PUSH | SWT.CENTER);
					btnStTo.setBounds(345, 24, 32, 23);
					btnStTo.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btnStTo.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent e) {
							KalendarDialog cd = new KalendarDialog(shell);
							Object obj = cd.open();
							if (obj != null) {
								String date = obj.toString();
								txtStTo.setText(date);
							}
						}
					});
				}
				
				{
					lblStReptype = new Label(cvsServicetax, SWT.NONE);
					lblStReptype.setText("Report Type");
					lblStReptype.setBounds(410, 28, 67, 21);
				}
				{
					cbStReptype = new Combo(cvsServicetax, SWT.NONE);
					cbStReptype.setBounds(480, 28, 86, 23);
					cbStReptype.add("Consolidated");
					cbStReptype.add("Detailed");
					cbStReptype.select(0);
					cbStReptype.addSelectionListener(this);
				}
								

				{
					btnStGo = new Button(cvsServicetax, SWT.PUSH | SWT.CENTER);
					btnStGo.setText("Go");
					btnStGo.setBounds(600, 27, 37, 23);
					btnStGo.addSelectionListener(this);
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
		
	
	private void handleServicetax() {
		// TODO Auto-generated method stub
		
		
		try {
			
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date fromDt = dateFormat.parse(txtStFrom.getText());
			Date toDate = dateFormat.parse(txtStTo.getText());
			StationsDTO[] utDTO = null;
			utDTO = getServicetax(fromDt,toDate);
			
			if (tblServicetax != null)
				tblServicetax.removeAll();
			if (utDTO != null) {
				populateSt(utDTO);
			} else {
					// AdminComposite.display("No Records Found",
				// STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void populateSt(StationsDTO[] dto)throws Exception {
		// TODO Auto-generated method stub
		
		String Reptype = cbStReptype.getText();
		Boolean isConsolidated = false;
		if(Reptype.equalsIgnoreCase("Consolidated")) {
			 populateStAllBranches(tblServicetax);
			 isConsolidated = true;
		 } else{
	
			 populateStAllStations(tblServicetax);
		 }
		
		Boolean isBranch = false; 
		int len = dto.length;
		TableItem[] item = tblServicetax.getItems();
		int itemlen = item.length;
		float tft[] = new float[itemlen];
		float stx[] = new float[itemlen];
		String stn_code = "";
		
		
		
		
		for (int i = 0; i < len; i++) {
			
			for(int j = 0; j < itemlen; j++){
				
				
				
				if(isConsolidated == true) {
				 	stn_code = getBranch_code(dto[i].getStationname());
				 	isBranch = true;
				}
				else
					stn_code = dto[i].getStationname();
				
				String branchCode = item[j].getText(1);
				int index = branchCode.indexOf(" - ");
				branchCode = branchCode.substring(index + 3);
				
				if (stn_code.equalsIgnoreCase(branchCode)){
					if(isBranch == true){
						tft[j] = tft[j] + dto[i].getTotalfreight();
						stx[j] = stx[j] + dto[i].getServicetax();
						fillST(dto,item[j],tft[j],stx[j],stn_code);
						
					}
					else{
						tft[j] = dto[i].getTotalfreight() ; 
						stx[j] = dto[i].getServicetax();
						fillST(dto,item[j],tft[j],stx[j],stn_code);
						
					}
					
				}
				
			}
				
		}
		callTotal();
			
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
	
	private void callTotal() {
		// TODO Auto-generated method stub
			TableItem[] items = tblServicetax.getItems();
			float tot1 = 0;
			float tot2 = 0;
			float tot3 = 0;
			/*float tot4 = 0;
			float tot5 = 0;
			float tot6 = 0;*/
			
			if (items != null) {
				for (int i = 0; i < items.length; i++) {
					
					if (!items[i].getText(2).equals(""))
						tot1 = tot1 + Float.parseFloat(items[i].getText(2));
					if (!items[i].getText(3).equals(""))
						tot2 = tot2 + Float.parseFloat(items[i].getText(3));
					if (!items[i].getText(4).equals(""))
						tot3 = tot3 + Float.parseFloat(items[i].getText(4));
					/*if (!items[i].getText(5).equals(""))
						tot4 = tot4 + Float.parseFloat(items[i].getText(5));
					if (!items[i].getText(6).equals(""))
						tot5 = tot5 + Float.parseFloat(items[i].getText(6));
					if (!items[i].getText(7).equals(""))
						tot6 = tot6 + Float.parseFloat(items[i].getText(7));*/
					
					
				}

			}

			final TableItem item1 = new TableItem(tblServicetax, SWT.NONE);
			Font font1 = new Font(Display.getCurrent(), "Tahoma", 8, SWT.BOLD);
			item1.setFont(font1);
			item1.setText(1, "TOTAL");
			item1.setText(2, getRoundedValue(tot1));
			item1.setText(3, getRoundedValue(tot2));
			item1.setText(4, getRoundedValue(tot3));
			/*item1.setText(5, getRoundedValue(tot4));
			item1.setText(6, getRoundedValue(tot5));
			item1.setText(7, getRoundedValue(tot6));*/
			
			new sortListener().fillZerosOnEmpty(tblServicetax);
	}
	
	private String getRoundedValue(float cardRate) {
		DecimalFormat decimalFormat;
		decimalFormat = new DecimalFormat("0.00");
		cardRate = cardRate * 100;
		cardRate = (float) Math.round(cardRate);
		cardRate = cardRate / 100;

		return decimalFormat.format(cardRate);
	}

	private void fillST(StationsDTO[] dto, TableItem item, float tft,float stx,
			String stn_code) {
		// TODO Auto-generated method stub
			DecimalFormat df = new DecimalFormat("0.00");
			float totalfreightin25 = 0;
			/*float servicetax = 0;
			float educess = 0;
			float hreducess = 0;*/
			float totalst = 0;
			
			totalst = tft - stx ;
			item.setText(2, df.format(totalst));
			
			
			item.setText(3, df.format(stx));
			
			/*servicetax = totalfreightin25 / 10;
			item.setText(4, df.format(servicetax));
			
			educess	= servicetax / 50;
			item.setText(5, df.format(educess));
			
			hreducess = educess / 100;
			item.setText(6, df.format(hreducess));
			
			totalst = servicetax + educess + hreducess;*/
			
			item.setText(4, df.format(tft));
			
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
		

		private StationsDTO[] getServicetax(Date fromDt, Date toDate) {
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
				bookedLr = handler.getServicetaxHistory(fromDt, toDate);
			}else if(fromDt.after(date)){
				bookedLr = handler.getServicetax(fromDt, toDate);
			}else{
				bookedLr = handler.getServicetaxUnion(fromDt, toDate);
			}
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			return bookedLr;
		}
		
		private ArrayList<StationsDTO> getServicetaxTable(Table tblServicetax) {
			// TODO Auto-generated method stub
				StationsDTO dto = null;
				ArrayList<StationsDTO> list = new ArrayList<StationsDTO>();
				int length = 0;

				TableItem[] items = tblServicetax.getItems();
				if (items != null) {
					length = items.length;
				}
				if (length > 0) {
					for (int i = 0; i < length; i++) {
						dto = new StationsDTO();
						
						dto.setStationname(items[i].getText(1));

						if (!items[i].getText(2).equals(""))
							dto.setTotalfreight(Float.parseFloat(items[i].getText(2))); 
						
						/*if (!items[i].getText(3).equals(""))
							dto.setTotalfreightin25(Float.parseFloat(items[i].getText(3)));*/
						
						if (!items[i].getText(3).equals(""))
							dto.setServicetax(Float.parseFloat(items[i].getText(3)));
						
						/*if (!items[i].getText(5).equals(""))
							dto.setEducess(Float.parseFloat(items[i].getText(5)));
						
						if (!items[i].getText(6).equals(""))
							dto.setHreducess(Float.parseFloat(items[i].getText(6)));*/					
						
						if (!items[i].getText(4).equals(""))
							dto.setTotalst(Float.parseFloat(items[i].getText(4)));
							
						list.add(dto);
					}
				}

				return list;
		}

		
		private String[] getServicetaxHeading(Table tblServicetax) {
			// TODO Auto-generated method stub
			
			
			String list[] = new String[3];
			
			list[0] =txtStFrom.getText();
			
			list[1] =txtStTo.getText();
					
			if(cbStReptype.getText().equalsIgnoreCase("Detailed"))
				list[2] = "Detailed";
			else
				list[2] = "Consolidated";
			
				
			return list;
		}
		
	@Override
	public void handleEvent(Event e) {
		// TODO Auto-generated method stub
		String selection = tabReport.getSelection()[0].getText();
		TableColumn column = (TableColumn) e.widget;
		TableColumn[] cols = tblServicetax.getColumns();
		int len = cols.length;

		for (int i = 0; i < len; i++) {
			if (column == cols[i]) {
				 if  (i == 1) {
					new sortListener().sortByString(i, tblServicetax);
				} else if (i > 1 || i == 0) {
					new sortListener().sortByFloat(i, tblServicetax);
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
		
		if(source == btnStGo){
			
			new sortListener().display("Generating Report...", STATUS_SUCCESS,
					SUCCESS_FONT_COLOR,lblStatusBar);
			handleServicetax();
			
			new sortListener().display("Report Loaded Successfully!",
					STATUS_SUCCESS, SUCCESS_FONT_COLOR,lblStatusBar);
			
		}
		
		else if (source == btnExportXL) {
				try {
					//handleExcel();
					ArrayList<StationsDTO> list = null;
					String[] value = null;
					list = getServicetaxTable(tblServicetax);
					value = getServicetaxHeading(tblServicetax);
					new sortListener().prepareExcel(list, "Service Tax Annexure", SERVICETAX_JRXML,
							null,value,shell,lblStatusBar);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}else if (source == btnPrint) {
				try {
					//handlePrint();
					ArrayList<StationsDTO> list = null;
					String[] value = null;
					list = getServicetaxTable(tblServicetax);
					value = getServicetaxHeading(tblServicetax);
					handler.preparePrint(list, "Service Tax Annexure", SERVICETAX_JRXML,
							null,value);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
			else if (source == btnExportPDF) {
				try {
					//handlePDF();
					ArrayList<StationsDTO> list = null;
					String[] value = null;
					list = getServicetaxTable(tblServicetax);
					value = getServicetaxHeading(tblServicetax);
					new sortListener().preparePDF(list, "Service Tax Annexure", SERVICETAX_JRXML,
							null,value,shell,lblStatusBar);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}	
	}

	
}
