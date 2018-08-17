package hm.akr.admin.reports.handler;

/**
 * 
 */

import hm.akr.admin.reports.AdminCompositeHandler;
import hm.akr.admin.workspace.SWTResourceManager;
import hm.akr.common.BeanUtil;
import hm.akr.common.KalendarDialog;
import hm.akr.dto.DailyStatusDTO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

/**
 * 
 * @author Class to create MonitorReportComposite
 * 
 */
public class MonitorReportsComposite extends Composite {

	Shell shell = null;

	private Group group1;

	private Button btnReport;

	AdminCompositeHandler handler = null;

	BeanUtil beanutil = null;

	public MonitorReportsComposite(Composite composite, int style)
			throws Exception {
		super(composite, style);
		shell = new Shell();
		try {

			handler = new AdminCompositeHandler();

		} catch (Exception e) {

			throw e;
		}
	}

	private Button btnSendReport;

	private Button btnGo;

	private TableColumn colsno;

	private Button btnTo;

	private Text txtTo;

	private Label lblTo;

	private Button btnFrom;

	private Text txtFrom;

	private Label lblfrom;

	private TableColumn tblcolBilling;

	private TableColumn tblcolPaid;

	private TableColumn tblcolTopay;

	private TableColumn tblcolStation;

	private Table tblReport;

	private Display display;

	private Canvas canvas2;

	private TabItem tabItem1;

	private TabFolder tabReport;

	private Text txtDate;

	Date reportDate = null;

	/**
	 * Method
	 * 
	 * @return
	 */
	public Composite loadComposite() {
		this.setSize(974, 708);

		{
			btnSendReport = new Button(this, SWT.PUSH | SWT.CENTER);
			btnSendReport.setText("Generate Report");
			btnSendReport.setBounds(741, 25, 91, 31);
			btnSendReport.addSelectionListener(new MoveAction());
		}

		{
			btnGo = new Button(this, SWT.PUSH | SWT.CENTER);
			btnGo.setImage(SWTResourceManager
					.getImage("hm/akr/resources/Calendar.jpg"));
			btnGo.setBounds(659, 25, 34, 25);
			btnGo.addSelectionListener(new MoveAction());
		}

		{
			txtDate = new Text(this, SWT.BORDER);
			txtDate.setBounds(550, 25, 104, 29);
			txtDate.setEditable(false);
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date currentDate = new java.util.Date();
			String date = dateFormat.format(currentDate);
			txtDate.setText(date);

		}
		{
			tabReport = new TabFolder(this, SWT.NONE);
			tabReport.setBounds(13, 101, 819, 441);
			{
				tabItem1 = new TabItem(tabReport, SWT.NONE);
				tabItem1.setText("USAGE REPORT");
				{
					canvas2 = new Canvas(tabReport, SWT.NONE);
					tabItem1.setControl(canvas2);
					{
						tblReport = new Table(canvas2, SWT.FULL_SELECTION
								| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);

						tblReport.setHeaderVisible(true);
						tblReport.setLinesVisible(true);
						tblReport.setBounds(59, 43, 757, 360);
						{
							colsno = new TableColumn(tblReport, SWT.NONE);
							colsno.setText("S NO");
							colsno.setWidth(82);
						}
						{
							tblcolStation = new TableColumn(tblReport, SWT.NONE);
							tblcolStation.setText("STATION NAME");
							tblcolStation.setWidth(300);
						}
						{
							tblcolTopay = new TableColumn(tblReport, SWT.NONE);
							tblcolTopay.setText("TOPAY");
							tblcolTopay.setWidth(103);
						}
						{
							tblcolPaid = new TableColumn(tblReport, SWT.NONE);
							tblcolPaid.setText("PAID");
							tblcolPaid.setWidth(113);
						}
						{
							tblcolBilling = new TableColumn(tblReport, SWT.NONE);
							tblcolBilling.setText("BILLING");
							tblcolBilling.setWidth(133);
						}

					}
					{
						lblfrom = new Label(canvas2, SWT.NONE);
						lblfrom.setText("From");
						lblfrom.setBounds(66, 5, 34, 22);
					}

					{
						txtFrom = new Text(canvas2, SWT.BORDER);
						txtFrom.setBounds(104, 5, 94, 22);
						txtFrom.setEditable(false);
						DateFormat dateFormat = new SimpleDateFormat(
								"dd-MM-yyyy");
						java.util.Date currentDate = new java.util.Date();
						String date = dateFormat.format(currentDate);
						txtFrom.setText(date);

					}
					{
						btnFrom = new Button(canvas2, SWT.PUSH | SWT.CENTER);
						btnFrom.setBounds(200, 4, 31, 23);
						btnFrom.setImage(SWTResourceManager
								.getImage("hm/akr/resources/Calendar.jpg"));
						btnFrom.addSelectionListener(new MoveAction());
					}
					{
						lblTo = new Label(canvas2, SWT.NONE);
						lblTo.setText("To");
						lblTo.setBounds(306, 5, 32, 22);
					}
					{
						txtTo = new Text(canvas2, SWT.BORDER);
						txtTo.setBounds(340, 5, 92, 22);
						txtTo.setEditable(false);
						DateFormat dateFormat = new SimpleDateFormat(
								"dd-MM-yyyy");
						java.util.Date currentDate = new java.util.Date();
						String date = dateFormat.format(currentDate);
						txtTo.setText(date);

					}
					{
						btnTo = new Button(canvas2, SWT.PUSH | SWT.CENTER);
						btnTo.setBounds(433, 4, 32, 23);
						btnTo.setImage(SWTResourceManager
								.getImage("hm/akr/resources/Calendar.jpg"));
						btnTo.addSelectionListener(new MoveAction());
					}
					{
						btnReport = new Button(canvas2, SWT.PUSH | SWT.CENTER);
						btnReport.setText("GO");
						btnReport.setBounds(544, 5, 47, 22);
						btnReport.addSelectionListener(new MoveAction());
					}
				}
			}
			tabReport.setSelection(0);
		}
		{
			group1 = new Group(this, SWT.NONE);
			GridLayout group1Layout = new GridLayout();
			group1Layout.makeColumnsEqualWidth = true;
			group1.setLayout(group1Layout);
			group1.setText("Monitoring Reports");
			group1.setBounds(538, 9, 296, 70);
		}
		populateReport();
		return this;
	}

	/**
	 * Method to populate the reports
	 */
	private void populateReport() {
		System.out.println("Report created");
		DailyStatusDTO[] dto = null;
		Date fromdate = null;
		Date todate = null;
		try {
			beanutil = new BeanUtil();
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

			fromdate = dateFormat.parse(txtFrom.getText());
			todate = dateFormat.parse(txtTo.getText());
			dto = beanutil.getReport(fromdate, todate);
			if (null != dto) {
				tblReport.removeAll();
				int len = dto.length;
				for (int i = 0; i < len; i++) {
					TableItem item = new TableItem(tblReport, SWT.NONE);
					Font font1 = new Font(display, "Tahoma", 8, SWT.NONE);
					item.setFont(font1);
					item.setText(0, Integer.toString(dto[i].getDs_no()));
					item.setText(1, dto[i].getName());
					item.setText(2, Integer.toString(dto[i].getTopay_count()));
					item.setText(3, Integer.toString(dto[i].getPaid_count()));
					item
							.setText(4, Integer.toString(dto[i]
									.getBilling_count()));

				}
			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * 
	 * @author
	 * 
	 * Listener Class Monitoring Reports Composite
	 * 
	 */
	public class MoveAction implements SelectionListener {

		@Override
		public void widgetDefaultSelected(SelectionEvent event) {

		}

		@Override
		public void widgetSelected(SelectionEvent event) {
			Object source = event.getSource();

			if (btnGo == source) {
				KalendarDialog cd = new KalendarDialog(shell);

				Object obj = cd.open();
				if (obj != null)
					txtDate.setText(obj.toString());

			} else if (btnFrom == source) {
				KalendarDialog cd = new KalendarDialog(shell);

				Object obj = cd.open();
				if (obj != null)
					txtFrom.setText(obj.toString());

			} else if (btnReport == source) {
				populateReport();

			} else if (btnTo == source) {
				KalendarDialog cd = new KalendarDialog(shell);

				Object obj = cd.open();
				if (obj != null)
					txtTo.setText(obj.toString());
			}

		}
	}

}
