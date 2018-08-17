package hm.akr.container.admin;

import java.io.File;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import hm.akr.common.BeanUtil;
import hm.akr.common.IUIConstants;
import hm.akr.container.commission.CommissionCompositeHandler;
import hm.akr.container.history.HistoryHandler;
import hm.akr.dto.ArticleDTO;
import hm.akr.dto.BookingDTO;
import hm.akr.dto.GMRInTimeDTO;
import hm.akr.dto.GMROutTimeDTO;
import hm.akr.dto.VersionDTO;
import hm.akr.common.sortListener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
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

public class Stock extends Composite implements SelectionListener,IUIConstants,Listener {

	private TableColumn tblcolstockConsignee;
	private TableColumn tblcolstockconsignor;
	private TableColumn tblcolstockvehicle;
	private TableColumn tblcolstocksent;
	private TableColumn tblcolstockinward;
	private TableColumn tblcolstockarticletype;
	private TableColumn tblcolstockarticlevalue;
	private TableColumn tblcolstockfreight;
	private TableColumn tblcolstockactual;
	private TableColumn tblcolstocknoa;
	private TableColumn tblcolfrom;
	private TableColumn tblcollrtype;
	private TableColumn tblcolstocklrdate;
	private TableColumn tblstocklrno;
	private TableColumn colstocksno;
	private TableColumn tblcolstockddc;
	private Table tblstockreport;
	private Canvas canstock;
	private TabItem tistock;
	
	
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
	private BeanUtil beanutil  = null;;
	
	public Stock(Composite composite, int swtValue) {
		super(composite, swtValue);
		
		try {
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
		tabReport.setBounds(50, 25, 820, 530);
		tabReport.addSelectionListener(this);
		
		
		if ( BeanUtil.getDbName() == null || BeanUtil.getDbName().equals("")){
			tistock = new TabItem(tabReport, SWT.NONE);
			tistock.setText("STOCK");
			{
				canstock = new Canvas(tabReport, SWT.NONE);
				tistock.setControl(canstock);
				{
					tblstockreport = new Table(canstock, SWT.BORDER);
					tblstockreport.setHeaderVisible(true);
					tblstockreport.setLinesVisible(true);
					tblstockreport.setBounds(10, 30, 810, 470);
					{
						colstocksno = new TableColumn(tblstockreport,
								SWT.NONE);
						colstocksno.setText("Sno");
						colstocksno.setWidth(50);
						colstocksno.addListener(SWT.Selection,
								this);
					}
					{
						tblstocklrno = new TableColumn(tblstockreport,
								SWT.NONE);
						tblstocklrno.setText("Lr No");
						tblstocklrno.setWidth(87);
						tblstocklrno.addListener(SWT.Selection,
								this);
					}
					{
						tblcolstocklrdate = new TableColumn(
								tblstockreport, SWT.NONE);
						tblcolstocklrdate.setText("Lr Date");
						tblcolstocklrdate.setWidth(89);
						tblcolstocklrdate.addListener(SWT.Selection,
								this);
					}
					{
						tblcollrtype = new TableColumn(tblstockreport,
								SWT.NONE);
						tblcollrtype.setText("Lr Type");
						tblcollrtype.setWidth(67);
						tblcollrtype.addListener(SWT.Selection,
								this);
					}
					{
						tblcolfrom = new TableColumn(tblstockreport,
								SWT.NONE);
						tblcolfrom.setText("From");
						tblcolfrom.setWidth(78);
						tblcolfrom.addListener(SWT.Selection,
								this);
					}
					{
						tblcolstocknoa = new TableColumn(
								tblstockreport, SWT.NONE);
						tblcolstocknoa.setText("NOA");
						tblcolstocknoa.setWidth(40);
						tblcolstocknoa.addListener(SWT.Selection,
								this);
					}
					{
						tblcolstockactual = new TableColumn(
								tblstockreport, SWT.NONE);
						tblcolstockactual.setText("Actual Weight");
						tblcolstockactual.setWidth(90);
						tblcolstockactual.addListener(SWT.Selection,
								this);
					}
					{
						tblcolstockfreight = new TableColumn(
								tblstockreport, SWT.NONE);
						tblcolstockfreight.setText("Freight");
						tblcolstockfreight.setWidth(60);
						tblcolstockfreight.addListener(SWT.Selection,
								this);
					}
					{
						tblcolstockarticlevalue = new TableColumn(
								tblstockreport, SWT.NONE);
						tblcolstockarticlevalue
								.setText("Article Value");
						tblcolstockarticlevalue.setWidth(90);
						tblcolstockarticlevalue.addListener(
								SWT.Selection, this);
					}
					{
						tblcolstockarticletype = new TableColumn(
								tblstockreport, SWT.NONE);
						tblcolstockarticletype.setText("Article Type");
						tblcolstockarticletype.setWidth(90);
						tblcolstockarticletype.addListener(
								SWT.Selection, this);
					}
					{
						tblcolstockinward = new TableColumn(
								tblstockreport, SWT.NONE);
						tblcolstockinward.setText("Inward Days");
						tblcolstockinward.setWidth(90);
						tblcolstockinward.addListener(SWT.Selection,
								this);
					}
					{
						tblcolstocksent = new TableColumn(
								tblstockreport, SWT.NONE);
						tblcolstocksent.setText("Sent Days");
						tblcolstocksent.setWidth(65);
						tblcolstocksent.addListener(SWT.Selection,
								this);
					}
					{
						tblcolstockddc = new TableColumn(
								tblstockreport, SWT.NONE);
						tblcolstockddc.setText("DD");
						tblcolstockddc.setWidth(65);
						tblcolstockddc.addListener(SWT.Selection,
								this);
					}
					{
						tblcolstockvehicle = new TableColumn(
								tblstockreport, SWT.NONE);
						tblcolstockvehicle.setText("Arrival Vehicle");
						tblcolstockvehicle.setWidth(85);
						tblcolstockvehicle.addListener(SWT.Selection,
								this);
					}
					{
						tblcolstockconsignor = new TableColumn(
								tblstockreport, SWT.NONE);
						tblcolstockconsignor.setText("Consignor Name");
						tblcolstockconsignor.setWidth(100);
						tblcolstockconsignor.addListener(SWT.Selection,
								this);
					}
					{
						tblcolstockConsignee = new TableColumn(
								tblstockreport, SWT.NONE);
						tblcolstockConsignee.setText("Consignee  Name");
						tblcolstockConsignee.setWidth(100);
						tblcolstockConsignee.addListener(SWT.Selection,
								this);
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
		
		try{
			ArrayList list = handler.getStockDetails(handler
					.getStationCode());
			//lblName.setText("STOCK STATEMENT");

			populateStockDetails(list);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			displayError("Error occured while Printing");
		}
		
		return this;
	
	}
	
	private void populateStockDetails(ArrayList list) {

		int len = 0;
		ArrayList<GMROutTimeDTO> outtime = (ArrayList<GMROutTimeDTO>) list
				.get(0);
		ArrayList<GMRInTimeDTO> intime = (ArrayList<GMRInTimeDTO>) list
				.get(1);
		ArrayList<GMROutTimeDTO> cr = (ArrayList<GMROutTimeDTO>) list
				.get(2);
		if (null != tblstockreport) {
			/*btnExportXL.setEnabled(false);
			btnExportPDF.setEnabled(false);
			btnprint.setEnabled(false);*/
			tblstockreport.removeAll();
		}

		try {
			/*btnExportXL.setEnabled(true);
			btnExportPDF.setEnabled(true);
			btnprint.setEnabled(true);*/
			populateOutAndCRDetails(outtime);
			populateOutAndCRDetails(cr);
			populateInTimeDetails(intime);
			displayTotalForStock();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void displayTotalForStock() {
		if (null != tblstockreport) {
			TableItem[] item = tblstockreport.getItems();
			int len = item.length;
			int noa = 0;
			float actual_wt = 0;
			float freight = 0;
			long art_value = 0;
			float inward = 0;
			float sentdays = 0;
			float dd = 0;
			String compare = null;
			if (len > 0) {
				for (int i = 0; i < len; i++) {
					compare = item[i].getText(5);
					if (!compare.equals(""))
						noa = noa + Integer.parseInt(compare);

					compare = item[i].getText(6);
					if (!compare.equals(""))
						actual_wt = actual_wt + Float.parseFloat(compare);

					compare = item[i].getText(7);
					if (!compare.equals(""))
						freight = freight + Float.parseFloat(compare);

					compare = item[i].getText(8);
					if (!compare.equals(""))
						art_value = art_value
								+ (long) Float.parseFloat(compare);

					compare = item[i].getText(10);
					if (!compare.equals(""))
						inward = inward + Float.parseFloat(compare);

					compare = item[i].getText(11);
					if (!compare.equals(""))
						sentdays = sentdays + Float.parseFloat(compare);
					compare = item[i].getText(12);
					if (!compare.equals(""))
						dd = dd + Float.parseFloat(compare);

				}

				TableItem item1 = new TableItem(tblstockreport, SWT.NONE);
				Font font1 = new Font(display, "Tahoma", 8, SWT.BOLD);
				item1.setFont(font1);
				item1.setText(0, "TOTAL");
				item1.setText(5, String.valueOf(noa));
				item1.setText(6, String.valueOf(actual_wt));
				item1.setText(7, String.valueOf(freight));
				item1.setText(8, String.valueOf(art_value));
				item1.setText(10, String.valueOf(inward));
				item1.setText(11, String.valueOf(sentdays));
				item1.setText(12, String.valueOf(dd));
			}

		}

	}

	/**
	 * 
	 * @param intime
	 * @throws Exception
	 * @throws NumberFormatException
	 */
	private void populateInTimeDetails(ArrayList<GMRInTimeDTO> intime)
			throws NumberFormatException, Exception {

		int len = intime.size();
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		int j = tblstockreport.getItemCount();
		j++;
		for (int i = 0; i < len; i++) {
			GMRInTimeDTO dto = (GMRInTimeDTO) intime.get(i);

			TableItem item = new TableItem(tblstockreport, SWT.NONE);
			Font itemFont = new Font(display, "Tahoma", 9, 0);
			item.setFont(itemFont);
			item.setText(0, String.valueOf(j++));
			item.setText(1, dto.getLr_no());
			Date lrdate = dto.getDate();
			item.setText(2, dateFormat.format(lrdate));
			item.setText(3, dto.getLr_type());

			item.setText(4, dto.getFrom());

			item.setText(5, String.valueOf(dto.getNo_of_articles()));

			item.setText(6, String.valueOf(dto.getActual_weight()));
			item.setText(7, String.valueOf(dto.getTotal()));
			item.setText(8, String.valueOf(dto.getArticle_value()));

			String arttype = getArticleType(Integer.parseInt(dto
					.getArticle_type()));
			if (null != arttype)
				item.setText(9, arttype);

			lrdate = dto.getSent_date();
			Date today = dto.getToday_date();

			long diff = today.getTime() - lrdate.getTime();

			diff = diff / (1000 * 60 * 60 * 24);
			item.setText(11, String.valueOf(diff));
			item.setText(12, String.valueOf(dto.getDdc()));

			if (null != dto.getVehicle_no())
				item.setText(13, dto.getVehicle_no());

			item.setText(14, dto.getConsignorName());
			item.setText(15, dto.getConsigneeName());
		}

	}

	/**
	 * Method to populate OUTIME and CR details for Stock Report
	 * 
	 * @param outtime
	 * @param len
	 * @throws Exception
	 * @throws NumberFormatException
	 */
	private void populateOutAndCRDetails(ArrayList<GMROutTimeDTO> outtime)
			throws NumberFormatException, Exception {

		int len = outtime.size();
		Date today = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		int j = tblstockreport.getItemCount();
		j++;
		for (int i = 0; i < len; i++) {
			GMROutTimeDTO dto = (GMROutTimeDTO) outtime.get(i);

			TableItem item = new TableItem(tblstockreport, SWT.NONE);
			Font itemFont = new Font(display, "Tahoma", 9, 0);
			item.setFont(itemFont);
			item.setText(0, String.valueOf(j++));
			item.setText(1, dto.getLr_no());

			Date lrdate = dto.getLrDate();
			item.setText(2, dateFormat.format(lrdate));
			item.setText(3, dto.getLr_type());
			item.setText(4, dto.getFrom());
			item.setText(5, String.valueOf(dto.getNo_of_articles()));
			item.setText(6, String.valueOf(dto.getActual_weight()));
			item.setText(7, String.valueOf(dto.getTotal()));
			item.setText(8, String.valueOf(dto.getArticle_value()));

			String arttype = getArticleType(Integer.parseInt(dto
					.getArticle_type()));
			if (null != arttype)
				item.setText(9, arttype);

			/** Need to change Number of days since inward * */
			Date outtimedate = dto.getOutTimeDate();
			long diff1 = today.getTime() - outtimedate.getTime();
			float days1 = (diff1 / (1000 * 60 * 60 * 24));

			item.setText(10, String.valueOf(days1));
			item.setText(12, String.valueOf(dto.getDdc()));
			item.setText(14, dto.getConsignorName());
			item.setText(15, dto.getConsigneeName());
		}

	}
	
	private String getArticleType(int parseInt) throws Exception {
		ArticleDTO[] articles = handler.getArticleType(parseInt);
		if (null != articles) {
			int len = articles.length;
			for (int i = 0; i < len; i++) {
				if (parseInt == articles[i].getArticleId()) {
					return articles[i].getType();

				}
			}
		}
		return null;
	}

	
	private GMRInTimeDTO[] buildStockDTO() throws ParseException {
		GMRInTimeDTO[] dto = null;
		if (null != tblstockreport) {
			TableItem[] items = tblstockreport.getItems();
			int len = items.length;
			dto = new GMRInTimeDTO[len];
			SimpleDateFormat dateform = new SimpleDateFormat("dd-MM-yyyy");
			Date tempdate = null;
			for (int i = 0; i < len; i++) {
				dto[i] = new GMRInTimeDTO();
				dto[i].setLr_no(items[i].getText(1));
				if(!(items[i].getText(2).equals("")))
				{	tempdate = dateform.parse(items[i].getText(2));
					dto[i].setDate(tempdate);
				}
				dto[i].setLr_type(items[i].getText(3));
				dto[i].setFrom(items[i].getText(4));
				if(!(items[i].getText(5).equals("")))
					dto[i].setNo_of_articles(Integer.parseInt(items[i].getText(5)));
				if(!(items[i].getText(6).equals("")))
					dto[i].setActual_weight(Float.parseFloat(items[i].getText(6)));
				if(!(items[i].getText(7).equals("")))
					dto[i].setTotal(Float.parseFloat(items[i].getText(7)));
				dto[i].setArticle_value(items[i].getText(8));
				dto[i].setArticle_type(items[i].getText(9));
				dto[i].setInward_days((items[i].getText(10)));
				dto[i].setSent_days(items[i].getText(11));
				dto[i].setDdc(Float.parseFloat(items[i].getText(12)));
				dto[i].setVehicle_no(items[i].getText(13));
				dto[i].setConsignor_name(items[i].getText(14));
				dto[i].setConsignee_name(items[i].getText(15));

			}
		}
		return dto;
	}
	

	private void prepareStockPDF(GMRInTimeDTO[]  dto,  String fileName) {
		// TODO Auto-generated method stub
		try {
			/*AdminComposite.display("Exporting...", STATUS_SUCCESS,
					SUCCESS_FONT_COLOR);*/
			handler.printStackReportPDF(dto,fileName);

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

	private void prepareXLStockDetails(GMRInTimeDTO[]  dto,  String fileName) throws Exception{
		try {
			/*ReportsComposite.display("Exporting...", STATUS_SUCCESS,
					SUCCESS_FONT_COLOR);*/
			handler.printSTOCKReportExcel(dto, fileName);

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
					//AdminComposite.display("Saving...", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
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
			//AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			e.printStackTrace();
			displayError("Status Success");
		}

	}

	private void displayError(String errorText) {
		MessageBox messageBox = new MessageBox(shell, SWT.OK);
		messageBox.setMessage(errorText);
		messageBox.setText("Alert");
		messageBox.open();
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
		if(source == btnExportPDF)
		{
			
			try {
				GMRInTimeDTO[] dto = buildStockDTO();
				prepareStockPDF(dto,"STOCK");
				
				btnExportPDF.setEnabled(false);
			} catch (Exception exception) {
				displayError("Problem while Exporting PDF");
			}
			

		}else if(source == btnPrint){
			
			try {
				GMRInTimeDTO[] dto = buildStockDTO();
				handler.printStockDetails(dto,"STOCK");
				//btnprint.setEnabled(false);
			} catch (Exception exception) {
				displayError("Problem while printing");
			}
		}else if(source == btnExportXL){
			
			try {
				GMRInTimeDTO[] dto = buildStockDTO();
				prepareXLStockDetails(dto,"STOCK");
				
				btnExportXL.setEnabled(false);
				
			} catch (Exception exception) {
				displayError("Problem while Exporting Excel");
			}
		}
			
	}

	@Override
	public void handleEvent(Event e) {
		// TODO Auto-generated method stub
		
		String selection = tabReport.getSelection()[0].getText();
		TableColumn column = (TableColumn) e.widget;
		
		if (column == colstocksno) {
			new sortListener().sortByFloat(0, tblstockreport);
		} else if (column == tblstocklrno) {
			new sortListener().sortByLrNo(1, tblstockreport);
		} else if (column == tblcolstocklrdate) {
			new sortListener().sortByDate(2, tblstockreport);
		} else if (column == tblcollrtype) {
			new sortListener().sortByString(3, tblstockreport);
		} else if (column == tblcolfrom) {
			new sortListener().sortByString(4, tblstockreport);
		} else if (column == tblcolstocknoa) {
			new sortListener().sortByFloat(5, tblstockreport);
		} else if (column == tblcolstockactual) {
			new sortListener().sortByFloat(6, tblstockreport);
		} else if (column == tblcolstockfreight) {
			new sortListener().sortByFloat(7, tblstockreport);
		} else if (column == tblcolstockarticlevalue) {
			new sortListener().sortByFloat(8, tblstockreport);
		} else if (column == tblcolstockarticletype) {
			new sortListener().sortByString(9, tblstockreport);
		} else if (column == tblcolstockinward) {
			new sortListener().sortByFloat(10, tblstockreport);
		}

		else if (column == tblcolstocksent) {
			new sortListener().sortByFloat(11, tblstockreport);
		} else if (column == tblcolstockddc) {
			new sortListener().sortByFloat(12, tblstockreport);
		} else if (column == tblcolstockvehicle) {
			new sortListener().sortByString(13, tblstockreport);
		} else if (column == tblcolstockconsignor) {
			new sortListener().sortByString(14, tblstockreport);
		} else if (column == tblcolstockConsignee) {
			new sortListener().sortByString(15, tblstockreport);
		}
		
	}
	
}
