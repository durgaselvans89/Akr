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

public class FOCLr extends ReportsMenu implements IUIConstants,Listener,SelectionListener {
	
	
	private TabItem tiFOCLr;
	private Canvas canFOCLr;
	private Table tblFOCLr;
	private TableColumn colFOCLrLrNo;
	private TableColumn colFOCLrLrDate;
	private TableColumn colFOCLrLrFrom;
	private TableColumn colFOCLrTo;
	private TableColumn colFOCLrNoa;
	private TableColumn colFOCLrCrgWt;
	private TableColumn colFOCLrArticleType;
	private TableColumn colFOCLrArticleValue;
	private Label lblFOCLrFromDate;
	private Text txtFOCLrFrom;
	private Button btnFOCLrFrom;
	private Button btnFocLrGo;
	private Label lblFOCLrTo;
	private Text txtFOCLrTo;
	private Button btnFOCLrTo;
	private Label lblFOCLrBranch;
	private Combo cbFOCLrBranch;
	private TableColumn colFOCSno;
	private Label lblFOCLrReportType;
	private Combo cbFOCLrReportType;
	private String FOC = "FOC Lr";
	private static final String FOCLRS_EXCEL_JRXML = "hm/akr/resources/printer/FOCLRs.jrxml";
	
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
	
	 public FOCLr(Composite composite,int style, float clientRights) {
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

			tiFOCLr = new TabItem(tabReport, SWT.NONE);
			tiFOCLr.setText(FOC);

			canFOCLr = new Canvas(tabReport, SWT.NONE);
			tiFOCLr.setControl(canFOCLr);

			tblFOCLr = new Table(canFOCLr, SWT.BORDER);
			tblFOCLr.setHeaderVisible(true);
			tblFOCLr.setLinesVisible(true);
			tblFOCLr.setBounds(16, 43, 781, 419);
			{
				colFOCSno = new TableColumn(tblFOCLr, SWT.NONE);
				colFOCSno.setText("Sno");
				colFOCSno.setWidth(35);
			}

			{
				colFOCLrLrNo = new TableColumn(tblFOCLr, SWT.NONE);
				colFOCLrLrNo.setText("Lr No");
				colFOCLrLrNo.setWidth(95);
			}
			{
				colFOCLrLrDate = new TableColumn(tblFOCLr, SWT.NONE);
				colFOCLrLrDate.setText("Lr Date");
				colFOCLrLrDate.setWidth(101);
			}
			{
				colFOCLrLrFrom = new TableColumn(tblFOCLr, SWT.NONE);
				colFOCLrLrFrom.setText("From");
				colFOCLrLrFrom.setWidth(90);
			}
			{
				colFOCLrTo = new TableColumn(tblFOCLr, SWT.NONE);
				colFOCLrTo.setText("To");
				colFOCLrTo.setWidth(90);
			}
			{
				colFOCLrNoa = new TableColumn(tblFOCLr, SWT.NONE);
				colFOCLrNoa.setText("No. of Articles");
				colFOCLrNoa.setWidth(81);
			}
			{
				colFOCLrCrgWt = new TableColumn(tblFOCLr, SWT.NONE);
				colFOCLrCrgWt.setText("Crg Wt");
				colFOCLrCrgWt.setWidth(88);
			}
			{
				colFOCLrArticleType = new TableColumn(tblFOCLr, SWT.NONE);
				colFOCLrArticleType.setText("Article Type");
				colFOCLrArticleType.setWidth(97);
			}
			{
				colFOCLrArticleValue = new TableColumn(tblFOCLr, SWT.NONE);
				colFOCLrArticleValue.setText("Article Value");
				colFOCLrArticleValue.setWidth(84);
			}
			for (int index = 0; index < tblFOCLr.getColumnCount(); index++) {
				tblFOCLr.getColumn(index).addListener(SWT.Selection,
						this);
			}

			{
				lblFOCLrFromDate = new Label(canFOCLr, SWT.NONE);
				lblFOCLrFromDate.setText("From");
				lblFOCLrFromDate.setBounds(13, 8, 41, 26);
			}
			{
				txtFOCLrFrom = new Text(canFOCLr, SWT.BORDER);
				txtFOCLrFrom.setBounds(57, 7, 79, 23);

				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date currentDate = new java.util.Date();
				String date = dateFormat.format(currentDate);
				txtFOCLrFrom.setText(date);
				txtFOCLrFrom.setEditable(false);
			}
			{
				btnFOCLrFrom = new Button(canFOCLr, SWT.PUSH);
				// btnFOCLrFrom.setText("Go");
				btnFOCLrFrom.setBounds(140, 6, 26, 23);
				btnFOCLrFrom.setImage(SWTResourceManager
						.getImage("hm/akr/resources/Calendar.jpg"));
				btnFOCLrFrom.addSelectionListener(this);
			}
			{
				lblFOCLrTo = new Label(canFOCLr, SWT.NONE);
				lblFOCLrTo.setText("To");
				lblFOCLrTo.setBounds(201, 8, 26, 23);
			}
			{
				txtFOCLrTo = new Text(canFOCLr, SWT.BORDER);
				txtFOCLrTo.setBounds(231, 7, 79, 23);

				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date currentDate = new java.util.Date();
				String date = dateFormat.format(currentDate);
				txtFOCLrTo.setText(date);
				txtFOCLrTo.setEditable(false);
			}
			{
				btnFOCLrTo = new Button(canFOCLr, SWT.PUSH);
				// btnFOCLrTo.setText("Go");
				btnFOCLrTo.setBounds(315, 6, 26, 23);
				btnFOCLrTo.setImage(SWTResourceManager
						.getImage("hm/akr/resources/Calendar.jpg"));
				btnFOCLrTo.addSelectionListener(this);
			}
			{
				lblFOCLrBranch = new Label(canFOCLr, SWT.NONE);
				lblFOCLrBranch.setText("Select Branch");
				lblFOCLrBranch.setBounds(376, 9, 74, 25);
			}
			{
				cbFOCLrBranch = new Combo(canFOCLr, SWT.READ_ONLY);
				cbFOCLrBranch.setBounds(456, 8, 91, 23);
				if(clientRights == 1 || clientRights == 1.0){
					
					try{
					
						getCurrentBranch_code(currentStationCode);
						cbFOCLrBranch.select(0);
						//populateStationForCLR();
						//cbFOCLrBranch.addSelectionListener(this);
					}
					catch(Exception e){
						e.printStackTrace();
					}
					
					
				}
				else{
				
					try{
						StationsDTO[] dto = handler.getAllBranches();
						if (null != dto) {
							cbFOCLrBranch.add("All");
							for (int i = 0; i < dto.length; i++) {
								cbFOCLrBranch.add(dto[i].getName() + " - "
										+ dto[i].getId());
							}
						}
						cbFOCLrBranch.addSelectionListener(this);
					}
					catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
			}
			{
				lblFOCLrReportType = new Label(canFOCLr, SWT.NONE);
				lblFOCLrReportType.setText("Report Type");
				lblFOCLrReportType.setBounds(568, 8, 67, 21);
			}
			{
				cbFOCLrReportType = new Combo(canFOCLr, SWT.READ_ONLY);
				cbFOCLrReportType.setBounds(637, 8, 86, 23);
				if(clientRights == 1 || clientRights == 1.0){
					cbFOCLrReportType.add("Detailed");
					cbFOCLrReportType.select(0);
					getDetailedFOCLRTable();
				}else{
					cbFOCLrReportType.add("Detailed");
					cbFOCLrReportType.add("Summary");
					cbFOCLrReportType.select(0);
					cbFOCLrReportType.addSelectionListener(this);
				}
			}
			{
				btnFocLrGo = new Button(canFOCLr, SWT.PUSH | SWT.CENTER);
				btnFocLrGo.setText("Go");
				btnFocLrGo.setBounds(729, 6, 44, 26);
				btnFocLrGo.addSelectionListener(this);
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
					
						cbFOCLrBranch.add(stations[i].getName()+" - "+branch_code);
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

	
	private void handleFOCLR() {

		if (null != tblFOCLr)
			tblFOCLr.removeAll();

		try {
			int index = -1;

			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date from_date = dateFormat.parse(txtFOCLrFrom.getText());
			Date to_date = dateFormat.parse(txtFOCLrTo.getText());

			index = cbFOCLrBranch.getSelectionIndex();
			if (index > -1) {
				String branch_code = null;
				if (index != 0) {
					branch_code = cbFOCLrBranch.getItem(index);
					index = branch_code.indexOf("-");
					branch_code = branch_code.substring(index + 1).trim();
				} else {
					branch_code = cbFOCLrBranch.getItem(index);
					if(branch_code.equalsIgnoreCase("All")){
						branch_code = "%";
					}else{
						index = branch_code.indexOf("-");
						branch_code = branch_code.substring(index + 1).trim();
					}
					
				}

				index = cbFOCLrReportType.getSelectionIndex();
				if (index > -1) {

					// System.out.println(from_date + "To" + to_date +
					// branch_code);
					populateFOCLRTable(from_date, to_date, branch_code, index);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void populateFOCLRTable(Date from_date, Date to_date,
			String branch_code, int type) {
		try {

			if (null != handler) {

				BookingDTO[] focLRS = getFOCLRS(from_date, to_date,
						branch_code, type);

				if (type == 0) {
					if (null != focLRS) {
						int len = focLRS.length;
						int j = 1;
						int noa = 0;
						float chargedWt = 0;
						float artValue = 0;
						for (int i = 0; i < len; i++) {

							TableItem item = new TableItem(tblFOCLr, SWT.NONE);
							item.setText(0, String.valueOf(j++));
							item
									.setText(1, String.valueOf(focLRS[i]
											.getLrno()));
							item
									.setText(2, String.valueOf(focLRS[i]
											.getDate()));
							item
									.setText(3, String.valueOf(focLRS[i]
											.getFrom()));
							item.setText(4, String.valueOf(focLRS[i].getTo()));

							noa = noa + focLRS[i].getNo_of_articles();
							item.setText(5, String.valueOf(focLRS[i]
									.getNo_of_articles()));

							chargedWt = chargedWt
									+ focLRS[i].getCharged_weight();
							item.setText(6, getRoundedValue(focLRS[i]
									.getCharged_weight()));

							item.setText(7, String.valueOf(focLRS[i]
									.getArticle_id()));

							artValue = artValue + focLRS[i].getArticle_value();
							item.setText(8, getRoundedValue(focLRS[i]
									.getArticle_value()));

						}
						if (len > 0) {
							TableItem item = new TableItem(tblFOCLr, SWT.NONE);
							Font font1 = new Font(Display.getDefault(), "Tahoma", 8, SWT.BOLD);
							item.setFont(font1);
							item.setText(1, String.valueOf("Total"));
							item.setText(5, String.valueOf(noa));
							item.setText(6, getRoundedValue(chargedWt));
							item.setText(8, getRoundedValue(artValue));
						}
					}
				} else {
					populateDefaultTable(branch_code, tblFOCLr, 9);
					if (null != focLRS) {
						int len = focLRS.length;
						int j = 1;
						for (int i = 0; i < len; i++) {

							if (null != tblFOCLr) {
								boolean isAvail = false;
								TableItem[] items = tblFOCLr.getItems();
								if (null != items) {
									int noItem = items.length;
									if (noItem > 0) {
										String station = null;
										for (int k = 0; k < noItem; k++) {
											station = items[k].getText(2);
											int lr = 0;
											int foc = 0;
											float lrwt = 0;
											float focwt = 0;
											String temp = null;
											if (station
													.equalsIgnoreCase(focLRS[i]
															.getFrom())) {

												temp = items[k].getText(3);
												if (!temp.equalsIgnoreCase(""))
													lr = Integer.parseInt(temp);

												temp = items[k].getText(4);
												if (!temp.equalsIgnoreCase(""))

													foc = Integer
															.parseInt(temp);

												temp = items[k].getText(6);
												if (!temp.equalsIgnoreCase(""))
													lrwt = Float
															.parseFloat(temp);

												temp = items[k].getText(7);
												if (!temp.equalsIgnoreCase(""))
													focwt = Float
															.parseFloat(temp);

												if (focLRS[i].getRate_type() != 6) {
													lr = lr
															+ (focLRS[i]
																	.getNo_of_articles());
													lrwt = lrwt
															+ focLRS[i]
																	.getActual_weight();
												} else {
													foc = foc
															+ (focLRS[i]
																	.getNo_of_articles());
													focwt = focwt
															+ focLRS[i]
																	.getActual_weight();
												}

												items[k].setText(3, String
														.valueOf(lr + foc));
												items[k].setText(4, String
														.valueOf(foc));

												items[k].setText(6, getRoundedValue(lrwt + focwt));
												items[k].setText(7, getRoundedValue(focwt));

												items[k].setText(5, getRoundedValue((float) foc
																/ (foc + lr)
																* 100));

												items[k]
														.setText(
																8,
																getRoundedValue(focwt
																				/ (focwt + lrwt)
																				* 100));

												isAvail = true;

											}
										}
									}
								}

								if (!isAvail) {

									TableItem item = new TableItem(tblFOCLr,
											SWT.NONE);
									item.setText(0, String.valueOf(j++));
									item.setText(1, String.valueOf(focLRS[i]
											.getTo()));
									item.setText(2, String.valueOf(focLRS[i]
											.getFrom()));
									int totallr = 0;
									int foc = 0;
									float totalwt = 0;
									float focwt = 0;
									if (focLRS[i].getRate_type() != 6) {
										item.setText(3, String
												.valueOf(focLRS[i]
														.getNo_of_articles()));
										totallr = focLRS[i].getNo_of_articles();
										item.setText(6, getRoundedValue(focLRS[i]
														.getActual_weight()));
										totalwt = focLRS[i].getActual_weight();

										item.setText(4, String.valueOf(0));
										item.setText(7, String.valueOf(0));
									} else {
										item.setText(3, String.valueOf(0));
										item.setText(6, String.valueOf(0));

										item.setText(4, String
												.valueOf(focLRS[i]
														.getNo_of_articles()));
										foc = focLRS[i].getNo_of_articles();
										item.setText(7, getRoundedValue(focLRS[i]
														.getActual_weight()));
										focwt = focLRS[i].getActual_weight();
									}

									item.setText(5, getRoundedValue((float) foc
											/ (totallr + foc) * 100));

									item.setText(8, getRoundedValue(focwt
											/ (totalwt + focwt) * 100));

								}
							}

						}
						if (null != tblFOCLr) {
							TableItem[] items = tblFOCLr.getItems();
							if (null != items) {
								int len1 = items.length;
								int lr = 0;
								int foc = 0;
								float lrPercent = 0;
								float lrWt = 0;
								float focWt = 0;
								float wtPercent = 0;
								float lraverage = 0;
								float wtaverage = 0;
								for (int i = 0; i < len1; i++) {
									lr = lr
											+ Integer.parseInt(items[i]
													.getText(3));
									foc = foc
											+ Integer.parseInt(items[i]
													.getText(4));
									lrPercent = lrPercent
											+ Float.parseFloat(items[i]
													.getText(5));
									lrWt = lrWt
											+ Float.parseFloat(items[i]
													.getText(6));
									focWt = focWt
											+ Float.parseFloat(items[i]
													.getText(7));
									wtPercent = wtPercent
											+ Float.parseFloat(items[i]
													.getText(8));
								}
								if (len1 > 0) {
									TableItem item = new TableItem(tblFOCLr,
											SWT.NONE);
									Font font1 = new Font(Display.getDefault(), "Tahoma", 8, SWT.BOLD);
									item.setFont(font1);
									item.setText(2, String.valueOf("Total"));
									
									TableItem item1 = new TableItem(tblFOCLr,
											SWT.NONE);
									item1.setFont(font1);
									item1.setText(4, String.valueOf("Average"));
									lraverage = lrPercent / len1;
									wtaverage = wtPercent / len1;
									
									item.setText(3, String.valueOf(lr));
									item.setText(4, String.valueOf(foc));
									item.setText(5, getRoundedValue(lrPercent));
									item.setText(6, getRoundedValue(lrWt));
									item.setText(7, getRoundedValue(focWt));
									item.setText(8, getRoundedValue(wtPercent));
									item1.setText(5, getRoundedValue(lraverage));
									item1.setText(8, getRoundedValue(wtaverage));
									
								}
							}
						}
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
	
	
	private void populateDefaultTable(String branch, Table tblName, int cols)
	throws Exception {
		StationsDTO[] dto = null;
		
		dto = handler.getAllBranches();
		int len = dto.length;
		for (int i = 0; i < len; i++) {
			TableItem item = new TableItem(tblName, SWT.NONE);
			item.setText(0, String.valueOf(i + 1));
			item.setText(1, dto[i].getBranch_code());
			int size = tblName.getColumnCount();
			for (int j = 2; j < size; j++) {
				item.setText(j, String.valueOf(0));
			}
		}
	}
	private BookingDTO[] getFOCLRS(Date from_date, Date to_date,
			String branch_code, int type) {
		//int monthDiff = 0;
		BookingDTO[] bookedLr = null;
		try {
			/*Date curDate = new Date();
			int m1 = from_date.getYear() * 12 + from_date.getMonth();
	        int m2 = curDate.getYear() * 12 + curDate.getMonth();
	        monthDiff = m2 - m1;*/
			Date date = BeanUtil.getThree_month_updated();
		if(to_date.before(date)){
			//System.out.println("in ddr histry-->"+date);
			bookedLr = handler.getFOCLRSHistory(from_date, to_date,branch_code,type);
		}else if(from_date.after(date)){
			bookedLr = handler.getFOCLRS(from_date, to_date,branch_code,type);
		}else {
			bookedLr = handler.getFOCLRSUnion(from_date, to_date,branch_code,type);
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookedLr;
		
	}

	private void getDetailedFOCLRTable() {

		if (null != tblFOCLr)
			tblFOCLr.removeAll();

		colFOCSno.setText("Sno");

		colFOCLrLrNo.setText("Lr No");

		colFOCLrLrDate.setText("Lr Date");

		colFOCLrLrFrom.setText("From");

		colFOCLrTo.setText("To");

		colFOCLrNoa.setText("No. of Articles");

		colFOCLrCrgWt.setText("Crg Wt");

		colFOCLrArticleType.setText("Article Type");

		colFOCLrArticleValue.setText("Article Value");

	}
	
	private void getSummaryFOCLRTable() {

		if (null != tblFOCLr)
			tblFOCLr.removeAll();

		colFOCSno.setText("Sno");

		colFOCLrLrNo.setText("Branch Code");

		colFOCLrLrDate.setText("Station Code");

		colFOCLrLrFrom.setText("Total Lrs");

		colFOCLrTo.setText("FOC Lrs");

		colFOCLrNoa.setText("LR%");

		colFOCLrCrgWt.setText("Total Weight");

		colFOCLrArticleType.setText("FOC Weight");

		colFOCLrArticleValue.setText("WT%");

	}

	private String getRoundedValue(float cardRate) {
		DecimalFormat decimalFormat;
		decimalFormat = new DecimalFormat("0.00");
		cardRate = cardRate * 100;
		cardRate = (float) Math.round(cardRate);
		cardRate = cardRate / 100;

		return decimalFormat.format(cardRate);
	}
	
	
	private String[] getFocHeading(Table refTable){
		
		String list[] = new String[4];
		
		list[0] = txtFOCLrFrom.getText();
		list[1] = txtFOCLrTo.getText();
		String branchCode = cbFOCLrBranch.getText();
		if (!branchCode.equalsIgnoreCase("All")) {
			int index = branchCode.indexOf(" - ");
			list[2] = branchCode.substring(index + 3);
		}
		else
			list[2] ="All";
		if(cbFOCLrReportType.getText().equals("Detailed"))
			list[3] = "Detailed";
		else
			list[3] = "Summary";
		
			
		return list;
	}
	
	@Override
	public void handleEvent(Event e) {
		// TODO Auto-generated method stub
		String selection = tabReport.getSelection()[0].getText();
		TableColumn column = (TableColumn) e.widget;
		
		TableColumn[] cols = tblFOCLr.getColumns();
		int len = cols.length;
		int index = cbFOCLrReportType.getSelectionIndex();
		for (int i = 0; i < len; i++) {
			if (column == cols[i] && index == 0) {
				if (i == 1) {
					new sortListener().sortByLrNo(i, tblFOCLr);
				} else if (i == 2) {
					new sortListener().sortByDate(i, tblFOCLr);
				} else if (i > 2 && i < 5) {
					new sortListener().sortByString(i, tblFOCLr);
				} else if (i > 4 || i == 0) {
					new sortListener().sortByFloat(i, tblFOCLr);
				}
			} else if (column == cols[i] && index == 1) {
				if (i == 1 || i == 2) {
					new sortListener().sortByString(i, tblFOCLr);
				} else if (i > 2 || i == 0) {
					new sortListener().sortByFloat(i, tblFOCLr);
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
		
		if (source == btnFOCLrFrom) {
			KalendarDialog cd = new KalendarDialog(shell);
			Object obj = cd.open();
			if (obj != null) {
				txtFOCLrFrom.setText(obj.toString());
			}

		} else if (source == btnFOCLrTo) {
			KalendarDialog cd = new KalendarDialog(shell);
			Object obj = cd.open();
			if (obj != null) {
				txtFOCLrTo.setText(obj.toString());
			}

		}else if (source == cbFOCLrBranch) {
			if (!cbFOCLrBranch.getText().equalsIgnoreCase("All")) {
				cbFOCLrReportType.select(0);
				getDetailedFOCLRTable();
				cbFOCLrReportType.setEnabled(false);
			} else {
				cbFOCLrReportType.select(0);
				getDetailedFOCLRTable();
				cbFOCLrReportType.setEnabled(true);
			}
		}
		else if (source == cbFOCLrReportType) {
			int index = cbFOCLrReportType.getSelectionIndex();
			String selected = cbFOCLrReportType.getItem(index);
			if (selected.equalsIgnoreCase("Detailed")) {

				getDetailedFOCLRTable();
			} else if (selected.equalsIgnoreCase("Summary")) {
				// cbStation.setVisible(false);
				//cbStation.setEnabled(false);
				// label3.setVisible(false);
				//label3.setEnabled(false);
				getSummaryFOCLRTable();
			}
		} else if (source == btnFocLrGo) {
			new sortListener().display("Generating Report...", STATUS_SUCCESS,
					SUCCESS_FONT_COLOR,lblStatusBar);

			handleFOCLR();
			new sortListener().display("Report Loaded Successfully!",
					STATUS_SUCCESS, SUCCESS_FONT_COLOR,lblStatusBar);
		}
		else if (source == btnExportXL) {
			try {
				//handleExcel();
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				if (cbFOCLrReportType.getText().equalsIgnoreCase("Detailed")) {
					list = new sortListener().getBPATable(tblFOCLr);
					value = getFocHeading(tblFOCLr);
					TableColumn[] col = tblFOCLr.getColumns();
					if(col != null){
						int len = col.length;
						String param[] = new String[len];
						for(int i = 0; i < len; i++){
							param[i] = col[i].getText();
						}
						new sortListener().prepareExcel(list, "FOC LRs", FOCLRS_EXCEL_JRXML, param,value,shell,lblStatusBar);
					}
				}
				else
				{
					list = new sortListener().getBPATable(tblFOCLr);
					value = getFocHeading(tblFOCLr);
					TableColumn[] col = tblFOCLr.getColumns();
					if(col != null){
						int len = col.length;
						String param[] = new String[len];
						for(int i = 0; i < len; i++){
							param[i] = col[i].getText();
						}
						new sortListener().prepareExcel(list, "FOC LRs", FOCLRS_EXCEL_JRXML, param,value,shell,lblStatusBar);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else if (source == btnPrint) {
			try {
				//handlePrint();
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				if (cbFOCLrReportType.getText().equalsIgnoreCase("Detailed")) {
					list = new sortListener().getBPATable(tblFOCLr);
					value = getFocHeading(tblFOCLr);
					TableColumn[] col = tblFOCLr.getColumns();
					if(col != null){
						int len = col.length;
						String param[] = new String[len];
						for(int i = 0; i < len; i++){
							param[i] = col[i].getText();
						}
						handler.preparePrint(list, "FOC LRs", FOCLRS_EXCEL_JRXML, param,value);
					}
				}
				else
				{
					list = new sortListener().getBPATable(tblFOCLr);
					value = getFocHeading(tblFOCLr);
					TableColumn[] col = tblFOCLr.getColumns();
					if(col != null){
						int len = col.length;
						String param[] = new String[len];
						for(int i = 0; i < len; i++){
							param[i] = col[i].getText();
						}
						handler.preparePrint(list, "FOC LRs", FOCLRS_EXCEL_JRXML, param,value);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		else if (source == btnExportPDF) {
			try {
				//handlePDF();
				ArrayList<TableDecorator> list = null;
				String[] value = null;
				if (cbFOCLrReportType.getText().equalsIgnoreCase("Detailed")) {
					list = new sortListener().getBPATable(tblFOCLr);
					value = getFocHeading(tblFOCLr);
					TableColumn[] col = tblFOCLr.getColumns();
					if(col != null){
						int len = col.length;
						String param[] = new String[len];
						for(int i = 0; i < len; i++){
							param[i] = col[i].getText();
						}
						new sortListener().preparePDF(list, "FOC LRs", FOCLRS_EXCEL_JRXML, param,value,shell,lblStatusBar);
					}
				}
				else
				{
					list = new sortListener().getBPATable(tblFOCLr);
					value = getFocHeading(tblFOCLr);
					TableColumn[] col = tblFOCLr.getColumns();
					if(col != null){
						int len = col.length;
						String param[] = new String[len];
						for(int i = 0; i < len; i++){
							param[i] = col[i].getText();
						}
						new sortListener().preparePDF(list, "FOC LRs", FOCLRS_EXCEL_JRXML, param,value,shell,lblStatusBar);
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		
	}
		

}
