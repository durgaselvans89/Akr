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
import hm.akr.common.MonthDialog;
import hm.akr.container.history.HistoryHandler;
import hm.akr.dto.RemittanceShortageDTO;
import hm.akr.dto.StationsDTO;
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




public class RemittanceShortage extends ReportsMenu implements IUIConstants,Listener,SelectionListener {

	private String RS = "Remittance Shortage";
	private static final String RS_EXCEL_JRXML = "hm/akr/resources/printer/RemittanceShortage.jrxml";
	private TabItem tiRemittance;
	private Canvas cvsRemittance;
	private Table tblRemittance;
	private TableColumn colRSfrom;
	private TableColumn colRSsno;
	private TableColumn colRSto;
	private TableColumn colRSLrNo;
	private TableColumn colRSLrdate;
	private TableColumn colRSType;
	private TableColumn colDRSNo;
	private TableColumn colDRSdate;
	private TableColumn colRSCRno;
	private TableColumn colRSCnor;
	private TableColumn colRSCnee;
	private TableColumn colRSamount;
	private TableColumn colRSrecoveryDt;
	private Button btnRSGo;
	private Label label7;
	private Button rdFull;
	private Button rdUnrecover;

	private Combo cbRSBranch;
	private Label label11;
	private Text txtRSMonth;
	private Button btnRSMonth;
	RemittanceShortageDTO dto[] = null;
	
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
	
	 public RemittanceShortage(Composite composite,int style, float clientRights) {
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
			// Remittance

			tiRemittance = new TabItem(tabReport, SWT.NONE);
			tiRemittance.setText(RS);

			cvsRemittance = new Canvas(tabReport, SWT.NONE);
			tiRemittance.setControl(cvsRemittance);

			{
				{
					tblRemittance = new Table(cvsRemittance, SWT.FULL_SELECTION
							| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);

					tblRemittance.setHeaderVisible(true);
					tblRemittance.setLinesVisible(true);
					tblRemittance.setBounds(10, 72, 795, 400);

					// tblRemittance.getColumn(11).addListener(SWT.Selection,
					// new sortListener());
					{
						colRSsno = new TableColumn(tblRemittance, SWT.NONE);
						colRSsno.setText("S NO");
						colRSsno.setWidth(49);
					}
					{
						colRSfrom = new TableColumn(tblRemittance, SWT.NONE);
						colRSfrom.setText("From");
						colRSfrom.setWidth(80);

					}
					{
						colRSto = new TableColumn(tblRemittance, SWT.NONE);
						colRSto.setText("To");
						colRSto.setWidth(80);

					}
					{
						colRSLrNo = new TableColumn(tblRemittance, SWT.NONE);

						colRSLrNo.setText("LR No");
						colRSLrNo.setWidth(80);

					}

					{
						colRSLrdate = new TableColumn(tblRemittance, SWT.NONE);
						colRSLrdate.setText("LR Date");
						colRSLrdate.setWidth(80);

					}
					{
						colRSType = new TableColumn(tblRemittance, SWT.NONE);
						colRSType.setText("LR Mode");
						colRSType.setWidth(80);

					}
					{
						colDRSNo = new TableColumn(tblRemittance, SWT.NONE);
						colDRSNo.setText("DRS No");
						colDRSNo.setWidth(90);

					}
					{
						colDRSdate = new TableColumn(tblRemittance, SWT.NONE);
						colDRSdate.setText("DRS Date");
						colDRSdate.setWidth(90);

					}
					{
						colRSCRno = new TableColumn(tblRemittance, SWT.NONE);
						colRSCRno.setText("CR No");
						colRSCRno.setWidth(90);

					}

					{
						colRSCnor = new TableColumn(tblRemittance, SWT.NONE);
						colRSCnor.setText("Cnor");
						colRSCnor.setWidth(90);

					}

					{
						colRSCnee = new TableColumn(tblRemittance, SWT.NONE);
						colRSCnee.setText("Cnee");
						colRSCnee.setWidth(90);

					}

					{
						colRSamount = new TableColumn(tblRemittance, SWT.NONE);
						colRSamount.setText("Amount");
						colRSamount.setWidth(100);

					}

					{
						colRSrecoveryDt = new TableColumn(tblRemittance,
								SWT.NONE);
						colRSrecoveryDt.setText("Recovery Dt");
						colRSrecoveryDt.setWidth(90);

					}

					for (int index = 0; index < tblRemittance.getColumnCount(); index++) {
						tblRemittance.getColumn(index).addListener(
								SWT.Selection, this);
					}

				}

				{
					cbRSBranch = new Combo(cvsRemittance, SWT.READ_ONLY);
					cbRSBranch.setBounds(61, 27, 130, 20);
					//cbRSBranch.addSelectionListener(this);
					if(clientRights == 1 || clientRights == 1.0){
						
						try{
						
							getCurrentBranch_code(currentStationCode);
							cbRSBranch.select(0);
							//handleBranchActionUT(cbUTBranch, cbUTStation);
							cbRSBranch.addSelectionListener(this);
						}
						catch(Exception e){
							e.printStackTrace();
						}
						
						
					}
					else{
						
						try{
							StationsDTO[] dto = handler.getAllBranches();
							if (null != dto) {
								//cbSBABranch.add("All");
								for (int i = 0; i < dto.length; i++) {
									cbRSBranch.add(dto[i].getName() + " - "
											+ dto[i].getId());
								}
							}
							cbRSBranch.addSelectionListener(this);
						}
						catch(Exception e){
							e.printStackTrace();
						}
					}
				}
				{
					label7 = new Label(cvsRemittance, SWT.NONE);
					label7.setText("Branch");
					label7.setBounds(22, 28, 39, 15);
					
				}

				{
					label11 = new Label(cvsRemittance, SWT.NONE);
					label11.setText("Select Month");
					label11.setBounds(206, 28, 70, 17);
				}
				{
					txtRSMonth = new Text(cvsRemittance, SWT.BORDER
							| SWT.READ_ONLY);
					txtRSMonth.setBounds(281, 27, 60, 21);
					DateFormat dateFormat = new SimpleDateFormat("MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					txtRSMonth.setText(date);
				}
				{
					btnRSMonth = new Button(cvsRemittance, SWT.PUSH
							| SWT.CENTER);
					btnRSMonth.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btnRSMonth.setBounds(341, 25, 26, 23);
					btnRSMonth.addSelectionListener(new SelectionListener() {

						@Override
						public void widgetDefaultSelected(SelectionEvent arg0) {

						}

						@Override
						public void widgetSelected(SelectionEvent event) {
							MonthDialog cd = new MonthDialog(Display
									.getCurrent().getActiveShell());
							Object obj = cd.open();
							if (obj != null) {
								txtRSMonth.setText(obj.toString());
							}
						}

					});
				}

				{
					btnRSGo = new Button(cvsRemittance, SWT.PUSH | SWT.CENTER);
					btnRSGo.setText("Go");
					btnRSGo.setBounds(568, 27, 37, 23);
					btnRSGo.addSelectionListener(this);
				}
				{
					rdFull = new Button(cvsRemittance, SWT.RADIO | SWT.LEFT);
					rdFull.setText("Full Report");
					rdFull.setBounds(391, 27, 72, 21);
					rdFull.addSelectionListener(this);
				}
				{
					rdUnrecover = new Button(cvsRemittance, SWT.RADIO
							| SWT.LEFT);
					rdUnrecover.setText("Unrecovered");
					rdUnrecover.setBounds(465, 27, 88, 21);
					rdUnrecover.setSelection(true);
					rdUnrecover.addSelectionListener(this);
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
					
						cbRSBranch.add(stations[i].getName()+" - "+branch_code);
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

	
	private void handleRS() {
		String value = "";
		String branch = "";
		try {
			if (!cbRSBranch.getText().equals("")
					&& !txtRSMonth.getText().equals("")) {
				value = cbRSBranch.getText();
				int index = value.indexOf(" - ");
				branch = value.substring(index + 3);
				String year = txtRSMonth.getText();
				int index1 = year.indexOf("-");
				String month = year.substring(0, index1);
				year = year.substring(index1 + 1);
				
				RemittanceShortageDTO[] dto = getRSReport(branch, Integer.parseInt(month),
						Integer.parseInt(year));
				

				if (tblRemittance != null)
					tblRemittance.removeAll();

				if (dto != null) {
					populateRSReport(dto);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	

	private RemittanceShortageDTO[] getRSReport(String branch, int month,
			int year) {
		int monthDiff = 0;
		try {
			Date curDate = new Date();
			int m1 = year * 12 + month;
	        int m2 = curDate.getYear() * 12 + curDate.getMonth();
	        monthDiff = m2 - m1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(monthDiff > 3){
			//System.out.println("in ddr histry-->"+date);
			dto = handler.getRSReportHistory(branch,month,year);
		}else{
			dto = handler.getRSReport(branch,month,year);
		}
		return dto;
	}

	private String getRoundedValue(float cardRate) {
		DecimalFormat decimalFormat;
		decimalFormat = new DecimalFormat("0.00");
		cardRate = cardRate * 100;
		cardRate = (float) Math.round(cardRate);
		cardRate = cardRate / 100;

		return decimalFormat.format(cardRate);
	}
	
	private void populateRSReport(RemittanceShortageDTO[] dto) {
		int len = dto.length;

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		if (len > 0) {
			for (int i = 0, j = 1; i < len; i++) {
				if (rdFull.getSelection()) {
					TableItem item = new TableItem(tblRemittance, SWT.NONE);
					item.setText(0, String.valueOf(j));
					item.setText(1, dto[i].getFromStation());
					item.setText(2, dto[i].getToStation());
					item.setText(3, dto[i].getLrNo());
					item.setText(4, dateFormat.format(dto[i].getLrDate()));
					item.setText(5, dto[i].getLrType());
					item.setText(6, dto[i].getDrsNo());
					item.setText(7, dateFormat.format(dto[i].getDrsDate()));
					if (dto[i].getCrNo() != null)
						item.setText(8, dto[i].getCrNo());
					item.setText(9, dto[i].getCnor());
					item.setText(10, dto[i].getCnee());
					item.setText(11, getRoundedValue(dto[i].getAmount()));
					if (dto[i].getRecoveryDate() != null)
						item.setText(12, dateFormat.format(dto[i]
								.getRecoveryDate()));
					j++;
				} else {
					if (dto[i].getRecoveryDate() == null) {
						TableItem item = new TableItem(tblRemittance, SWT.NONE);
						item.setText(0, String.valueOf(j));
						item.setText(1, dto[i].getFromStation());
						item.setText(2, dto[i].getToStation());
						item.setText(3, dto[i].getLrNo());
						item.setText(4, dateFormat.format(dto[i].getLrDate()));
						item.setText(5, dto[i].getLrType());
						item.setText(6, dto[i].getDrsNo());
						item.setText(7, dateFormat.format(dto[i].getDrsDate()));
						if (dto[i].getCrNo() != null)
							item.setText(8, dto[i].getCrNo());
						item.setText(9, dto[i].getCnor());
						item.setText(10, dto[i].getCnee());
						item.setText(11, getRoundedValue(dto[i].getAmount()));
						j++;
					}
				}

			}

			calcRSTotal();

		}

	}

	private void calcRSTotal() {
		TableItem[] items = tblRemittance.getItems();
		float totAmt = 0;
		if (items != null) {
			for (int i = 0; i < items.length; i++) {
				totAmt = totAmt + Float.parseFloat(items[i].getText(11));
			}

		}

		final TableItem item1 = new TableItem(tblRemittance, SWT.NONE);
		Font font1 = new Font(Display.getCurrent(), "Tahoma", 8, SWT.BOLD);
		item1.setFont(font1);
		item1.setText(10, "TOTAL");
		item1.setText(11, getRoundedValue(totAmt));

	}

	private String[] getRSTHeading(){
		
		String list[] = new String[3];
		
		String branchCode = cbRSBranch.getText();
		if (!branchCode.equalsIgnoreCase("All")) {
			int index = branchCode.indexOf(" - ");
			list[0] = branchCode.substring(index + 3);
		}
		else
			list[0] ="All";
			
		
		list[1] =txtRSMonth.getText();
		if(rdFull.getSelection() == true)
			list[2] =rdFull.getText();
		else
			list[2] =rdUnrecover.getText();
		
		
			
		return list;
	}
	
	private ArrayList<RemittanceShortageDTO> getRSTable() throws Exception {

		RemittanceShortageDTO dto = null;
		ArrayList<RemittanceShortageDTO> list = new ArrayList<RemittanceShortageDTO>();
		int length = 0;

		TableItem[] items = tblRemittance.getItems();
		if (items != null) {
			length = items.length;
		}
		if (length > 0) {
			for (int i = 0; i < length; i++) {
				dto = new RemittanceShortageDTO();
				dto.setFromStation(items[i].getText(1));
				dto.setToStation(items[i].getText(2));
				dto.setLrNo(items[i].getText(3));
				if (items[i].getText(4) != "")
					dto.setLrDate(dateFormat.parse(items[i].getText(4)));

				dto.setLrType(items[i].getText(5));
				dto.setDrsNo(items[i].getText(6));
				if (items[i].getText(7) != "")
					dto.setDrsDate(dateFormat.parse(items[i].getText(7)));

				dto.setCrNo(items[i].getText(8));
				dto.setCnor(items[i].getText(9));
				dto.setCnee(items[i].getText(10));

				if (!items[i].getText(11).equals(""))
					dto.setAmount(Float.parseFloat(items[i].getText(11)));

				if (!items[i].getText(12).equals(""))
					dto.setRecoveryDate(dateFormat.parse(items[i].getText(12)));

				list.add(dto);
			}
		}

		return list;

	}
	
	@Override
	public void handleEvent(Event e) {
		// TODO Auto-generated method stub
		String selection = tabReport.getSelection()[0].getText();
		TableColumn column = (TableColumn) e.widget;
		
		if (column == colRSsno) {
			new sortListener().sortByFloat(0, tblRemittance);
		} else if (column == colRSfrom) {
			new sortListener().sortByString(1, tblRemittance);
		} else if (column == colRSto) {
			new sortListener().sortByString(2, tblRemittance);
		} else if (column == colRSLrNo) {
			new sortListener().sortByLrNo(3, tblRemittance);
		} else if (column == colRSLrdate) {
			new sortListener().sortByDate(4, tblRemittance);
		} else if (column == colRSType) {
			new sortListener().sortByString(5, tblRemittance);
		} else if (column == colDRSNo) {
			new sortListener().sortByString(6, tblRemittance);
		} else if (column == colDRSdate) {
			new sortListener().sortByDate(7, tblRemittance);
		} else if (column == colRSCRno) {
			new sortListener().sortByLrNo(8, tblRemittance);
		} else if (column == colRSCnor) {
			new sortListener().sortByString(9, tblRemittance);
		} else if (column == colRSCnee) {
			new sortListener().sortByString(10, tblRemittance);
		} else if (column == colRSamount) {
			new sortListener().sortByFloat(11, tblRemittance);
		} else if (column == colRSrecoveryDt) {
			new sortListener().sortByDate(12, tblRemittance);
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
		if (source == btnRSGo) {
			new sortListener().display("Generating Report...", STATUS_SUCCESS,
					SUCCESS_FONT_COLOR,lblStatusBar);

			handleRS();
			new sortListener().display("Report Loaded Successfully!",
					STATUS_SUCCESS, SUCCESS_FONT_COLOR,lblStatusBar);
		} else if (source == rdFull) {
			if (tblRemittance != null)
				tblRemittance.removeAll();

			if (dto != null) {
				populateRSReport(dto);
			}
		} else if (source == rdUnrecover) {
			if (tblRemittance != null)
				tblRemittance.removeAll();

			if (dto != null) {
				populateRSReport(dto);
			}
		}else if (source == btnExportXL) {
			try {
				//handleExcel();
				ArrayList<RemittanceShortageDTO> list = null;
				String[] value = null;
				list = getRSTable();
				value = getRSTHeading();
				new sortListener().prepareExcel(list, "RemittanceShortage", RS_EXCEL_JRXML, null,value,shell,lblStatusBar);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else if (source == btnPrint) {
			try {
				//handlePrint();
				ArrayList<RemittanceShortageDTO> list = null;
				String[] value = null;
				list = getRSTable();
				value = getRSTHeading();
				handler.preparePrint(list, "RemittanceShortage", RS_EXCEL_JRXML, null,value);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		else if (source == btnExportPDF) {
			try {
				//handlePDF();
				ArrayList<RemittanceShortageDTO> list = null;
				String[] value = null;
				list = getRSTable();
				value = getRSTHeading();
				new sortListener().preparePDF(list, "RemittanceShortage", RS_EXCEL_JRXML, null,value,shell,lblStatusBar);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		
	}
		
	
}		
