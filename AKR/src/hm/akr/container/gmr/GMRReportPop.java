/**
 * 
 */
package hm.akr.container.gmr;

import java.text.Collator;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import hm.akr.container.gmr.GMRComposite.sortListener;
import hm.akr.dto.ArticleDTO;
import hm.akr.dto.GMRReportDTO;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
/**
 * @author user
 * 
 */
public class GMRReportPop extends Composite {

	private Shell shell;
	private Button btnPrint;
	private Table tblGmrDetail;
	private TableColumn colSNo;
	private Text txtDriverName;
	private Text txtVehicleNo;
	private Label lblDriverName;
	private Label lblVehicleNo;
	private Text txtDate;
	private Text txtInvoiceNo;
	private Label lblDate;
	private Label lblInvoiceNo;
	private TableColumn colLRNO;
	private TableColumn colBookingDate;
	private TableColumn colFrom;
	private TableColumn colTo;
	private TableColumn colWeight;
	private TableColumn colFreight;
	private TableColumn colConsignee;
	private TableColumn colNOA;
	private TableColumn colNatureOfArticle;
	private GMRReportDTO[] gmrReportDTO;
	private String statementNo;
	private String date;
	GMRCompositeHandler handler = null;
	private String deststation;
	private String fromstation;
	private boolean isconsignor;

	/**
	 * Constructor for GMR Report PopupWindow
	 * 
	 * @param shell
	 * @param swtValue
	 * @param statementNo
	 * @param date
	 * @param gmrReportDTO
	 */
	public GMRReportPop(Shell shell, int swtValue, String statementNo,
			String date, GMRReportDTO[] gmrReportDTO, String deststation,
			String fromstation, boolean consignor) {
		super(shell, swtValue);
		this.shell = shell;
		this.gmrReportDTO = gmrReportDTO;
		this.statementNo = statementNo;
		this.date = date;
		this.deststation = deststation;
		this.fromstation = fromstation;
		this.isconsignor = consignor;
		try {
			handler = new GMRCompositeHandler();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Method to create components for GMR report popupWindow
	 * 
	 * @return
	 */
	public Composite loadComposite() {

		this.setSize(935, 570);

		shell.setBounds(62, 161, 935, 568);
		shell.open();

		// Details Table

		{
			tblGmrDetail = new Table(this, SWT.H_SCROLL | SWT.FULL_SELECTION
					| SWT.V_SCROLL | SWT.BORDER | SWT.MULTI);
			tblGmrDetail.setHeaderVisible(true);
			tblGmrDetail.setLinesVisible(true);
			tblGmrDetail.setBounds(17, 130, 870, 310);
			{
				colSNo = new TableColumn(tblGmrDetail, SWT.NONE);
				colSNo.setText("S.No");
				colSNo.setWidth(40);
				colSNo.addListener(SWT.Selection, new sortListener());
			}
			{
				colLRNO = new TableColumn(tblGmrDetail, SWT.NONE);
				colLRNO.setText("LR NO");
				colLRNO.setWidth(70);
				colLRNO.addListener(SWT.Selection, new sortListener());

			}
			{
				colBookingDate = new TableColumn(tblGmrDetail, SWT.NONE);
				colBookingDate.setText("BOOKING DATE");
				colBookingDate.setWidth(140);
				colBookingDate.addListener(SWT.Selection, new sortListener());
			}
			{
				colFrom = new TableColumn(tblGmrDetail, SWT.NONE);
				colFrom.setText("FROM");
				colFrom.setWidth(80);
				colFrom.addListener(SWT.Selection, new sortListener());

			}
			{
				colTo = new TableColumn(tblGmrDetail, SWT.NONE);
				colTo.setText("TO");
				colTo.setWidth(80);
				colTo.addListener(SWT.Selection, new sortListener());
			}
			{
				colNOA = new TableColumn(tblGmrDetail, SWT.NONE);
				colNOA.setText("NO.OF ARTICLE");
				colNOA.setWidth(100);
				colNOA.addListener(SWT.Selection, new sortListener());
			}
			{
				colWeight = new TableColumn(tblGmrDetail, SWT.NONE);
				colWeight.setText("WEIGHT");
				colWeight.setWidth(70);
				colWeight.addListener(SWT.Selection, new sortListener());
			}
			{
				colFreight = new TableColumn(tblGmrDetail, SWT.NONE);
				colFreight.setText("FREIGHT");
				colFreight.setWidth(70);
				colFreight.addListener(SWT.Selection, new sortListener());
			}
			{
				colConsignee = new TableColumn(tblGmrDetail, SWT.NONE);
				colConsignee.setText("CONSIGNEE");
				colConsignee.setWidth(80);
				colConsignee.addListener(SWT.Selection, new sortListener());
			}

			{
				colNatureOfArticle = new TableColumn(tblGmrDetail, SWT.NONE);
				colNatureOfArticle.setText("NATURE OF ARTICLE");
				colNatureOfArticle.setWidth(130);
				colNatureOfArticle.addListener(SWT.Selection,
						new sortListener());
			}

		}

		btnPrint = new Button(this, SWT.PUSH);
		btnPrint.setText("Print");
		btnPrint.setBounds(828, 466, 60, 22);
		btnPrint.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				try {
					handler.printGMRDetails(gmrReportDTO, date, statementNo,
							txtDriverName.getText(), txtVehicleNo.getText(),
							fromstation, deststation, isconsignor);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		});
		{
			lblInvoiceNo = new Label(this, SWT.NONE);
			lblInvoiceNo.setText("Invoice No");
			lblInvoiceNo.setBounds(12, 41, 60, 24);
			lblInvoiceNo.setAlignment(SWT.RIGHT);
		}
		{
			lblDate = new Label(this, SWT.NONE);
			lblDate.setText("Date");
			lblDate.setBounds(13, 89, 60, 22);
			lblDate.setAlignment(SWT.RIGHT);
		}
		{
			txtInvoiceNo = new Text(this, SWT.BORDER | SWT.READ_ONLY);
			txtInvoiceNo.setBounds(79, 38, 100, 22);
		}
		{
			txtDate = new Text(this, SWT.BORDER | SWT.READ_ONLY);
			txtDate.setBounds(79, 85, 100, 22);
		}
		{
			lblVehicleNo = new Label(this, SWT.NONE);
			lblVehicleNo.setText("Vehicle No");
			lblVehicleNo.setBounds(651, 39, 60, 19);
			lblVehicleNo.setAlignment(SWT.RIGHT);
		}
		{
			lblDriverName = new Label(this, SWT.NONE);
			lblDriverName.setText("Driver Name");
			lblDriverName.setBounds(650, 85, 60, 22);
			lblDriverName.setAlignment(SWT.RIGHT);
		}
		{
			txtVehicleNo = new Text(this, SWT.BORDER | SWT.READ_ONLY);
			txtVehicleNo.setBounds(717, 37, 100, 22);
		}
		{
			txtDriverName = new Text(this, SWT.BORDER | SWT.READ_ONLY);
			txtDriverName.setBounds(717, 82, 100, 22);
		}

		try {
			populateGMRReportDetails();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (shell != null) {
			shell.open();
			Display display = shell.getDisplay();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		}

		return this;
	}

	public class sortListener implements Listener {

		/**
		 * 
		 */
		private void sortByLrNo(int index, Table refTable) {
			TableItem[] items = refTable.getItems();

			int value1 = 0;
			int value2 = 0;

			int len = items.length;

			// Sorting need to happen only if the number of rows available
			// is greater than one excluding total
			if (len > 2) {
				len = len - 1; // Ignoring the last row which is meant to be
				// Total

				for (int i = 1; i < len; i++) {
					if (!items[i].getText(index).isEmpty()) {
						value1 = Integer.parseInt(items[i].getText(index)
								.substring(1));
						for (int j = 0; j < i; j++) {
							value2 = Integer.parseInt(items[j].getText(index)
									.substring(1));
							if (value1 < value2) {
								String[] values = { items[i].getText(0),
										items[i].getText(1),
										items[i].getText(2),
										items[i].getText(3),
										items[i].getText(4),
										items[i].getText(5),
										items[i].getText(6),
										items[i].getText(7),
										items[i].getText(8),
										items[i].getText(9),
										items[i].getText(10),
										items[i].getText(11),
										items[i].getText(12),
										items[i].getText(13),
										items[i].getText(14) };
								items[i].dispose();
								TableItem item = new TableItem(refTable,
										SWT.NONE, j);
								item.setText(values);
								items = refTable.getItems();
								break;
							}
						}
					}
				}
			}

		}

		/**
		 * 
		 * @param index
		 * @param isOutTime
		 */
		private void sortByString(int index, Table refTable) {

			TableItem[] items = refTable.getItems();
			Collator collator = Collator.getInstance(Locale.getDefault());

			int len = items.length;

			// Sorting need to happen only if the number of rows available
			// is greater than one excluding total
			if (len > 2) {

				for (int i = 1; i < len; i++) {
					if (!items[i].getText(index).isEmpty()) {
						String value1 = items[i].getText(index);
						for (int j = 0; j < i; j++) {
							String value2 = items[j].getText(index);
							if (collator.compare(value1, value2) < 0) {
								String[] values = { items[i].getText(0),
										items[i].getText(1),
										items[i].getText(2),
										items[i].getText(3),
										items[i].getText(4),
										items[i].getText(5),
										items[i].getText(6),
										items[i].getText(7),
										items[i].getText(8),
										items[i].getText(9),
										items[i].getText(10),
										items[i].getText(11),
										items[i].getText(12),
										items[i].getText(13),
										items[i].getText(14) };
								items[i].dispose();

								TableItem item = new TableItem(refTable,
										SWT.NONE, j);
								item.setText(values);
								items = refTable.getItems();
								break;
							}
						}
					}
				}
			}
		}

		private void SortByDate(int index, Table refTable) {
			try {

				TableItem[] items = refTable.getItems();

				SimpleDateFormat parse = new SimpleDateFormat("dd-MM-yyyy");

				Date date1 = null;
				Date date2 = null;

				int len = items.length;

				// Sorting need to happen only if the number of rows available
				// is greater than one excluding total
				if (len > 2) {

					for (int i = 1; i < len; i++) {
						if (!items[i].getText(index).isEmpty()) {

							date1 = parse.parse(items[i].getText(index));

							for (int j = 0; j < i; j++) {
								if (!items[j].getText(index).isEmpty()) {

									date2 = parse
											.parse(items[j].getText(index));

								}

								if (date1.before(date2)) {
									String[] values = { items[i].getText(0),
											items[i].getText(1),
											items[i].getText(2),
											items[i].getText(3),
											items[i].getText(4),
											items[i].getText(5),
											items[i].getText(6),
											items[i].getText(7),
											items[i].getText(8),
											items[i].getText(9),
											items[i].getText(10),
											items[i].getText(11),
											items[i].getText(12),
											items[i].getText(13),
											items[i].getText(14) };
									items[i].dispose();
									TableItem item = new TableItem(refTable,
											SWT.NONE, j);
									item.setText(values);
									items = refTable.getItems();
									break;
								}
							}
						}
					}
				}
			} catch (Exception exp) {
				exp.printStackTrace();
			}

		}

		/**
		 * 
		 */
		private void SortByFloat(int index, Table refTable) {
			TableItem[] items = refTable.getItems();
			float value2 = 0;
			float value1 = 0;
			int len = items.length;

			// Sorting need to happen only if the number of rows available
			// is greater than one excluding total
			if (len > 2) {

				for (int i = 1; i < len; i++) {
					if (!items[i].getText(index).isEmpty()) {

						value1 = Float.parseFloat(items[i].getText(index));

						for (int j = 0; j < i; j++) {
							if (!items[j].getText(index).isEmpty())
								value2 = Float.parseFloat(items[j]
										.getText(index));

							if (value1 < value2) {
								String[] values = { items[i].getText(0),
										items[i].getText(1),
										items[i].getText(2),
										items[i].getText(3),
										items[i].getText(4),
										items[i].getText(5),
										items[i].getText(6),
										items[i].getText(7),
										items[i].getText(8),
										items[i].getText(9),
										items[i].getText(10),
										items[i].getText(11),
										items[i].getText(12),
										items[i].getText(13),
										items[i].getText(14) };

								items[i].dispose();
								TableItem item = new TableItem(refTable,
										SWT.NONE, j);
								item.setText(values);
								items = refTable.getItems();
								break;
							}
						}
					}
				}
			}

		}

		/**
		 * 
		 */
		public void handleEvent(Event e) {

			TableColumn column = (TableColumn) e.widget;

			if (column == colSNo) {
				SortByFloat(0, tblGmrDetail);
			} else if (column == colLRNO) {
				sortByLrNo(1, tblGmrDetail);
			} else if (column == colBookingDate) {
				SortByDate(2, tblGmrDetail);
			} else if (column == colFrom) {
				sortByString(3, tblGmrDetail);
			} else if (column == colTo) {
				sortByString(4, tblGmrDetail);
			} else if (column == colNOA) {
				SortByFloat(5, tblGmrDetail);
			} else if (column == colWeight) {
				SortByFloat(6, tblGmrDetail);
			} else if (column == colFreight) {
				SortByFloat(7, tblGmrDetail);
			} else if (column == colConsignee) {
				sortByString(8, tblGmrDetail);
			} else if (column == colNatureOfArticle) {
				sortByString(9, tblGmrDetail);
			}

		}

	}

	/**
	 * Method to Populate GMR Report Details
	 * 
	 * @throws Exception
	 */
	private void populateGMRReportDetails() throws Exception {
		if (gmrReportDTO != null) {

			if (gmrReportDTO[0].getDriverName() != null)
				txtDriverName.setText(gmrReportDTO[0].getDriverName());

			if (gmrReportDTO[0].getVehicleNo() != null)
				txtVehicleNo.setText(gmrReportDTO[0].getVehicleNo());

			if (statementNo != null)
				txtInvoiceNo.setText(statementNo);

			if (date != null)
				txtDate.setText(date);

			int len = gmrReportDTO.length;
			int noa = 0;
			float totalWeight = 0;
			float totalBft = 0;
			for (int i = 0; i < len; i++) {
				TableItem item = new TableItem(tblGmrDetail, SWT.NONE);

				item.setText(0, String.valueOf((i + 1)));
				item.setText(1, gmrReportDTO[i].getLrNo());
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				String date = dateFormat.format(gmrReportDTO[i]
						.getBookingDate());
				item.setText(2, String.valueOf(date));
				item.setText(3, String.valueOf(gmrReportDTO[i]
						.getFrom_station()));
				item
						.setText(4, String.valueOf(gmrReportDTO[i]
								.getTo_station()));
				item.setText(5, String.valueOf(gmrReportDTO[i]
						.getNo_of_articles()));
				noa = noa + gmrReportDTO[i].getNo_of_articles();
				item.setText(6, String.valueOf(gmrReportDTO[i].getWeight()));
				totalWeight = totalWeight + gmrReportDTO[i].getWeight();
				item.setText(7, String.valueOf(gmrReportDTO[i].getBft()));
				totalBft = totalBft + gmrReportDTO[i].getBft();

				item.setText(8, String.valueOf(gmrReportDTO[i]
						.getConsignee_name()));
				item
						.setText(9, getArticleDesc(gmrReportDTO[i]
								.getArticle_id()));

			}

			TableItem item1 = new TableItem(tblGmrDetail, SWT.NONE);
			Font font1 = new Font(shell.getDisplay(), "Tahoma", 8, SWT.BOLD);
			item1.setFont(font1);
			item1.setText(4, "TOTAL");
			item1.setText(5, Integer.toString(noa));
			item1.setText(6, Float.toString(totalWeight));
			item1.setText(7, Float.toString(totalBft));
		}

	}

	/**
	 * Method to get Article Description for given article ID
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	private String getArticleDesc(int id) throws Exception {
		ArticleDTO[] articles = null;
		articles = handler.getArticleTypes();
		for (int i = 0; i < articles.length; i++) {
			if (id == articles[i].getArticleId()) {
				return articles[i].getType();
			}
		}

		return "";
	}

}
