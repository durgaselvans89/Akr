package hm.akr.container.admin;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import hm.akr.common.KalendarDialog;
import hm.akr.common.sortListener;
import hm.akr.common.SWTResourceManager;
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
import org.eclipse.swt.widgets.Text;

public class OpenLr extends ReportsMenu implements IUIConstants,Listener,SelectionListener {
	
	private Combo cbOpenLrBranch;
	private Label lblOpenLrBranch;
	private Button btnOpenLrTo;
	private Button btnOpenView;
	private Text txtOpenLrTo;
	private Label lblOpenLrTo;
	private Button btnOpenLrFrom;
	private Text txtOpenLrFrom;
	private Label lblOpenLrFromDate;
	private TableColumn colOpenLRCnee;
	private TabItem tiOpenLr;
	private Canvas canOpenLr;
	private Table tblOpenLr;
	private TableColumn colOpenLrBranchCode;
	private TableColumn colOpenLrStationCode;
	private TableColumn colOpenLrLrNo;
	private TableColumn colOpenLrTo;
	private TableColumn colOpenLrLrDate;
	private TableColumn colOpenLrLrType;
	private TableColumn colOpenLrCardRate;
	private TableColumn colOpenLrBasicFreight;
	private TableColumn colOpenLrCCCrg;
	private TableColumn colOpenLrDDCrg;
	private TableColumn colOpenLrOthers;
	private TableColumn colOpensno;
	private TableColumn colOpenLRTotal;
	private TableColumn colOpenLRDiscount;
	private TableColumn colOpenLRCrgWt;
	private TableColumn colOpenLRCnor;
	private String OPENLR = "Open Lr";
	private static final String OPENLR_EXCEL_JRXML = "hm/akr/resources/printer/OpenLr.jrxml";
	
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
	
	 public OpenLr(Composite composite,int style, float clientRights) {
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

			tiOpenLr = new TabItem(tabReport, SWT.NONE);

			tiOpenLr.setText(OPENLR);

			canOpenLr = new Canvas(tabReport, SWT.NONE);
			tiOpenLr.setControl(canOpenLr);

			tblOpenLr = new Table(canOpenLr, SWT.BORDER);
			tblOpenLr.setHeaderVisible(true);
			tblOpenLr.setLinesVisible(true);
			tblOpenLr.setBounds(9, 43, 790, 431);
			{
				colOpensno = new TableColumn(tblOpenLr, SWT.NONE);
				colOpensno.setText("Sno");
				colOpensno.setWidth(35);
			}
			{
				colOpenLrBranchCode = new TableColumn(tblOpenLr, SWT.NONE);
				colOpenLrBranchCode.setText("Branch Code");
				colOpenLrBranchCode.setWidth(87);
			}
			{
				colOpenLrStationCode = new TableColumn(tblOpenLr, SWT.NONE);
				colOpenLrStationCode.setText("From");
				colOpenLrStationCode.setWidth(77);
			}
			{
				colOpenLrTo = new TableColumn(tblOpenLr, SWT.NONE);
				colOpenLrTo.setText("To");
				colOpenLrTo.setWidth(76);
			}

			{
				colOpenLrLrNo = new TableColumn(tblOpenLr, SWT.NONE);
				colOpenLrLrNo.setText("Lr No");
				colOpenLrLrNo.setWidth(67);
			}
			{
				colOpenLrLrDate = new TableColumn(tblOpenLr, SWT.NONE);
				colOpenLrLrDate.setText("Lr Date");
				colOpenLrLrDate.setWidth(78);
			}
			{
				colOpenLrLrType = new TableColumn(tblOpenLr, SWT.NONE);
				colOpenLrLrType.setText("Lr Type");
				colOpenLrLrType.setWidth(65);
			}
			{
				colOpenLrCardRate = new TableColumn(tblOpenLr, SWT.NONE);
				colOpenLrCardRate.setText("Card Rate");
				colOpenLrCardRate.setWidth(90);
			}
			{
				colOpenLrBasicFreight = new TableColumn(tblOpenLr, SWT.NONE);
				colOpenLrBasicFreight.setText("Basic Freight");
				colOpenLrBasicFreight.setWidth(60);
			}
			{
				colOpenLrCCCrg = new TableColumn(tblOpenLr, SWT.NONE);
				colOpenLrCCCrg.setText("CC Crg");
				colOpenLrCCCrg.setWidth(90);
			}
			{
				colOpenLrDDCrg = new TableColumn(tblOpenLr, SWT.NONE);
				colOpenLrDDCrg.setText("DD Crg");
				colOpenLrDDCrg.setWidth(90);
			}
			{
				colOpenLrOthers = new TableColumn(tblOpenLr, SWT.NONE);
				colOpenLrOthers.setText("Others");
				colOpenLrOthers.setWidth(90);
			}
			{
				colOpenLRTotal = new TableColumn(tblOpenLr, SWT.NONE);
				colOpenLRTotal.setText("Total");
				colOpenLRTotal.setWidth(65);
			}
			{
				colOpenLRDiscount = new TableColumn(tblOpenLr, SWT.NONE);
				colOpenLRDiscount.setText("Discount %");
				colOpenLRDiscount.setWidth(85);
			}
			{
				colOpenLRCrgWt = new TableColumn(tblOpenLr, SWT.NONE);
				colOpenLRCrgWt.setText("Crg Wt");
				colOpenLRCrgWt.setWidth(100);
			}
			{
				colOpenLRCnor = new TableColumn(tblOpenLr, SWT.NONE);
				colOpenLRCnor.setText("Cnor");
				colOpenLRCnor.setWidth(100);
			}
			{
				colOpenLRCnee = new TableColumn(tblOpenLr, SWT.NONE);
				colOpenLRCnee.setText("Cnee");
				colOpenLRCnee.setWidth(100);
			}

			for (int index = 0; index < tblOpenLr.getColumnCount(); index++) {
				tblOpenLr.getColumn(index).addListener(SWT.Selection,
						this);
			}
			{
				lblOpenLrFromDate = new Label(canOpenLr, SWT.NONE);
				lblOpenLrFromDate.setText("From");
				lblOpenLrFromDate.setBounds(18, 9, 28, 17);
			}
			{
				txtOpenLrFrom = new Text(canOpenLr, SWT.NONE | SWT.BORDER);
				txtOpenLrFrom.setBounds(51, 9, 79, 23);

				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date currentDate = new java.util.Date();
				String date = dateFormat.format(currentDate);
				txtOpenLrFrom.setText(date);
				txtOpenLrFrom.setEditable(false);
			}
			{
				btnOpenLrFrom = new Button(canOpenLr, SWT.PUSH);
				// btnOpenLrFrom.setText("Go");
				btnOpenLrFrom.setBounds(134, 8, 26, 23);
				btnOpenLrFrom.setImage(SWTResourceManager
						.getImage("hm/akr/resources/Calendar.jpg"));
				btnOpenLrFrom.addSelectionListener(this);
			}
			{
				lblOpenLrTo = new Label(canOpenLr, SWT.NONE);
				lblOpenLrTo.setText("To");
				lblOpenLrTo.setBounds(230, 10, 31, 19);
			}
			{
				txtOpenLrTo = new Text(canOpenLr, SWT.BORDER);
				txtOpenLrTo.setBounds(266, 9, 83, 23);

				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date currentDate = new java.util.Date();
				String date = dateFormat.format(currentDate);
				txtOpenLrTo.setText(date);
				txtOpenLrTo.setEditable(false);
			}
			{
				btnOpenLrTo = new Button(canOpenLr, SWT.PUSH);
				// btnOpenLrTo.setText("Go");
				btnOpenLrTo.setBounds(354, 8, 26, 23);
				btnOpenLrTo.setImage(SWTResourceManager
						.getImage("hm/akr/resources/Calendar.jpg"));
				btnOpenLrTo.addSelectionListener(this);
			}
			{
				lblOpenLrBranch = new Label(canOpenLr, SWT.NONE);
				lblOpenLrBranch.setText("Select Branch");
				lblOpenLrBranch.setBounds(488, 9, 77, 25);
			}
			{
				cbOpenLrBranch = new Combo(canOpenLr, SWT.READ_ONLY);
				cbOpenLrBranch.setBounds(572, 9, 117, 21);
				if(clientRights == 1 || clientRights == 1.0){
					
					try{
					
						getCurrentBranch_code(currentStationCode);
						cbOpenLrBranch.select(0);
						//populateStationForCLR();
						//cbOpenLrBranch.addSelectionListener(this);
					}
					catch(Exception e){
						e.printStackTrace();
					}
					
					
				}
				else{
					try{
						StationsDTO[] dto = handler.getAllBranches();
						if (null != dto) {
							cbOpenLrBranch.add("All");
							for (int i = 0; i < dto.length; i++) {
								cbOpenLrBranch.add(dto[i].getName() + " - "
										+ dto[i].getId());
							}
						}
						cbOpenLrBranch.addSelectionListener(this);
					}
					catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
			}
			{
				btnOpenView = new Button(canOpenLr, SWT.PUSH | SWT.CENTER);
				btnOpenView.setText("View");
				btnOpenView.setBounds(699, 7, 48, 26);
				btnOpenView.addSelectionListener(this);
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
					
						cbOpenLrBranch.add(stations[i].getName()+" - "+branch_code);
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


	
	private void handleOpenLRs() {

		if (null != tblOpenLr)
			tblOpenLr.removeAll();

		try {
			int index = -1;

			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date from_date = dateFormat.parse(txtOpenLrFrom.getText());
			Date to_date = dateFormat.parse(txtOpenLrTo.getText());

			index = cbOpenLrBranch.getSelectionIndex();
			if (index > -1) {
				String branch_code = null;
				if (index != 0) {
					branch_code = cbOpenLrBranch.getItem(index);
					index = branch_code.indexOf("-");
					branch_code = branch_code.substring(index + 1).trim();
				} else {
					branch_code = cbOpenLrBranch.getItem(index);
					if(branch_code.equalsIgnoreCase("All")){
						branch_code = "%";
					}else{
						index = branch_code.indexOf("-");
						branch_code = branch_code.substring(index + 1).trim();
					}
				}

				populateOpenLRTable(from_date, to_date, branch_code);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param from_date
	 * @param to_date
	 * @param branch
	 */
	private void populateOpenLRTable(Date from_date, Date to_date, String branch) {
		try {

			if (null != handler) {

				BookingDTO[] openLRS = getOpenLRS(from_date, to_date,
						branch);

				if (null != openLRS) {
					int len = openLRS.length;
					int j = 1;
					float discount = 0;
					float other = 0;

					/* Total */
					float cr = 0;
					float bft = 0;
					float ccc = 0;
					float ddc = 0;
					float others = 0;
					float total = 0;
					float dis = 0;
					float cwt = 0;
					for (int i = 0; i < len; i++) {

						TableItem item = new TableItem(tblOpenLr, SWT.NONE);
						item.setText(0, String.valueOf(j++));
						item.setText(1, String.valueOf(openLRS[i]
								.getCreatedby()));
						item.setText(2, String.valueOf(openLRS[i].getFrom()));
						item.setText(3, String.valueOf(openLRS[i].getTo()));
						item.setText(4, String.valueOf(openLRS[i].getLrno()));
						item.setText(5, String.valueOf(openLRS[i].getDate()));
						item.setText(6, String.valueOf(openLRS[i].getType()));
						discount = (openLRS[i].getBft()/openLRS[i].getActual_bft() * 100) - 100;
						// if(discount>0)
						item.setText(7, String
								.valueOf(getRoundedValue(openLRS[i]
										.getActual_bft()
										)));
						cr = cr + Float.parseFloat(item.getText(7));
						// else
						// item.setText(7, String.valueOf(0));

						other = openLRS[i].getTotal()
								- (openLRS[i].getBft() + openLRS[i].getCcc() + openLRS[i]
										.getDdc());
						item.setText(8, getRoundedValue(openLRS[i].getBft()));
						bft = bft + Float.parseFloat(item.getText(8));
						item.setText(9, getRoundedValue(openLRS[i].getCcc()));
						ccc = ccc + Float.parseFloat(item.getText(9));
						item.setText(10, getRoundedValue(openLRS[i].getDdc()));
						ddc = ddc + Float.parseFloat(item.getText(10));
						item.setText(11, getRoundedValue(other));
						others = others + (other);
						item.setText(12, getRoundedValue(openLRS[i].getTotal()));
						total = total + Float.parseFloat(item.getText(12));
						
						if(discount > 0){
							item.setText(13,"+" + getRoundedValue(discount));
						}else{
							item.setText(13, getRoundedValue(discount));
						}
						
						
						dis = dis + Float.parseFloat(item.getText(13));

						/*
						 * item.setText(13, String.valueOf(openLRS[i]
						 * .getNo_of_articles()));
						 */
						item.setText(14, getRoundedValue(openLRS[i]
								.getCharged_weight()));
						cwt = cwt + Float.parseFloat(item.getText(14));
						item.setText(15, String.valueOf(openLRS[i]
								.getConsignorName()));
						item.setText(16, String.valueOf(openLRS[i]
								.getConsigneeName()));

					}
					TableItem item = new TableItem(tblOpenLr, SWT.NONE);
					Font font1 = new Font(Display.getDefault(), "Tahoma", 8, SWT.BOLD);
					item.setFont(font1);
					item.setText(1, String.valueOf("Total"));
					item.setText(7, String.valueOf(getRoundedValue(cr)));
					item.setText(8, String.valueOf(getRoundedValue(bft)));
					item.setText(9, getRoundedValue(ccc));
					item.setText(10, getRoundedValue(ddc));
					item.setText(11, getRoundedValue(others));
					item.setText(12, getRoundedValue(total));
					item.setText(13, getRoundedValue(dis));
					item.setText(14, getRoundedValue(cwt));

				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
	
	private String getRoundedValue(float cardRate) {
		DecimalFormat decimalFormat;
		decimalFormat = new DecimalFormat("0.00");
		cardRate = cardRate * 100;
		cardRate = (float) Math.round(cardRate);
		cardRate = cardRate / 100;

		return decimalFormat.format(cardRate);
	}
	

	private BookingDTO[] getOpenLRS(Date from_date, Date to_date, String branch) {
		//int monthDiff = 0;
		BookingDTO[] openLRS = null;
		try {
			/*Date curDate = new Date();
			int m1 = from_date.getYear() * 12 + from_date.getMonth();
	        int m2 = curDate.getYear() * 12 + curDate.getMonth();
	        monthDiff = m2 - m1;*/
			Date date = BeanUtil.getThree_month_updated();
		if(to_date.before(date)){
			//System.out.println("in ddr histry-->"+date);
			openLRS = handler.getOpenLRSHistory(from_date, to_date,branch);
		}else if(from_date.after(date)){
			openLRS = handler.getOpenLRS(from_date, to_date,branch);
		}else{
			openLRS = handler.getOpenLRSUnion(from_date, to_date,branch);
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return openLRS;
	}


	
	private String[] getOpenlrHeading(Table refTable){
		
		String list[] = new String[3];
		
		list[0] = txtOpenLrFrom.getText();
		list[1] = txtOpenLrTo.getText();
		
		String branchCode = cbOpenLrBranch.getText();
		if (!branchCode.equalsIgnoreCase("All")) {
			int index = branchCode.indexOf(" - ");
			list[2] = branchCode.substring(index + 3);
		}
		else
			list[2] ="All";
			
		return list;
	}
	
	@Override
	public void handleEvent(Event e) {
		// TODO Auto-generated method stub
		String selection = tabReport.getSelection()[0].getText();
		TableColumn column = (TableColumn) e.widget;
		
		TableColumn[] cols = tblOpenLr.getColumns();
		int len = cols.length;

		for (int i = 0; i < len; i++) {
			if (column == cols[i]) {
				if (i == 4) {
					new sortListener().sortByLrNo(i, tblOpenLr);
				} else if (i == 5) {
					new sortListener().sortByDate(i, tblOpenLr);
				} else if ((i > 0 && i < 4) || (i == 6) || (i == 15)
						|| (i == 16)) {
					new sortListener().sortByString(i, tblOpenLr);
				} else if ((i > 6 && i < 15) || i == 0) {
					new sortListener().sortByFloat(i, tblOpenLr);
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
		
		if (source == btnOpenLrFrom) {
			KalendarDialog cd = new KalendarDialog(shell);
			Object obj = cd.open();
			if (obj != null) {
				txtOpenLrFrom.setText(obj.toString());
			}

		} else if (source == btnOpenLrTo) {
			KalendarDialog cd = new KalendarDialog(shell);
			Object obj = cd.open();
			if (obj != null) {
				txtOpenLrTo.setText(obj.toString());
			}

		} else if (source == btnOpenView) {
			new sortListener().display("Generating Report...", STATUS_SUCCESS,
					SUCCESS_FONT_COLOR,lblStatusBar);

			handleOpenLRs();
			new sortListener().display("Report Loaded Successfully!",
					STATUS_SUCCESS, SUCCESS_FONT_COLOR,lblStatusBar);
		}else if (source == btnExportXL) {
			try {
				//handleExcel();
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				list =new sortListener().getBPATable(tblOpenLr);
				value = getOpenlrHeading(tblOpenLr);
				new sortListener().prepareExcel(list, "OpenLr", OPENLR_EXCEL_JRXML,
						null,value,shell,lblStatusBar);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else if (source == btnPrint) {
			try {
				//handlePrint();
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				list = new sortListener().getBPATable(tblOpenLr);
				value = getOpenlrHeading(tblOpenLr);
				handler.preparePrint(list, "OpenLr", OPENLR_EXCEL_JRXML,
						null,value);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		else if (source == btnExportPDF) {
			try {
				//handlePDF();
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				list = new sortListener().getBPATable(tblOpenLr);
				value = getOpenlrHeading(tblOpenLr);
				new sortListener().preparePDF(list, "OpenLr", OPENLR_EXCEL_JRXML,
						null,value,shell,lblStatusBar);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	 
		
	}

}
