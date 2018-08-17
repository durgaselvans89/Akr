package hm.akr.container.admin;

import java.text.SimpleDateFormat;
import java.util.Date;

import hm.akr.common.BeanUtil;
import hm.akr.common.IUIConstants;
import hm.akr.common.TableSort;
import hm.akr.container.admin.AdminComposite.SetUpAction;
import hm.akr.common.sortListener;
import hm.akr.dto.DeliveryVerificationDTO;
import hm.akr.dto.StationsDTO;
import hm.akr.dto.VersionDTO;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class Deliveryverification  extends Composite implements SelectionListener,IUIConstants,Listener{

	
	
	private TabItem tiContactReport;

	private Canvas canContact;

	private Table tblContactReport;

	private Label lblContactBranch;

	private Combo cbContactBranch;

	private Label lblDVSelectstation;

	private Combo cbDVSelectstation;

	private Label lblDVLrType;

	private Combo cbDVLrType;

	private Label lblDVDeliveryType;

	private Combo cbDVDeliveryType;

	private Label lblDVInwardDays;

	private Text txtDVInwardDays;

	private Label lblDVAmount;

	private Text txtDVAmount;

	private Button btnDVSubmit;

	private TableColumn colContactSno;

	private TableColumn colContactLrno;

	private TableColumn colContactLrdate;

	private TableColumn colContactCnorName;

	private TableColumn colContactCneeName;

	private TableColumn colContactCneeAddress;

	private TableColumn colContactNoa;
	private TableColumn colContactActWt;

	private TableColumn colContactArtID;

	private TableColumn colContactLrTotal;

	private TableColumn colContactDDCrg;

	private TableColumn colContactLrType;

	private TableColumn colContactInwarddays;

	private TableColumn colContactFrom;

	private TableColumn colContactTo;


	
	AdminCompositeHandler handler = null;
	VersionDTO[] versiondto = null;
	BeanUtil beanutil = null;
	private Shell shell = null;
	private String SERVER_DATE = "";
	private TabFolder tabReport;
	private Button btnPrint;
	private Button btnExportXL;
	private Button btnExportPDF;
	private Display display;
	private String CONTACT_REPORT_TAB = "Delivery Verification";
	private SimpleDateFormat user_formate;
	
	
	public Deliveryverification(Composite composite, int style) throws Exception {
		super(composite, style);

		shell = composite.getShell();

		handler = new AdminCompositeHandler();
		beanutil = BeanUtil.getInstance();
		SERVER_DATE = beanutil.getServerDate();
		user_formate = new SimpleDateFormat("dd-MM-yyyy");

	}
	
	
	
	public Composite loadcomposite(){
		
		
		this.setBounds(45,100, 1020,700);
		tabReport = new TabFolder(this, SWT.NONE);
		tabReport.setBounds(50, 25, 820, 500);
		tabReport.addSelectionListener(this);
		
		
		{
			tiContactReport = new TabItem(tabReport,
					SWT.NONE);
			tiContactReport.setText(CONTACT_REPORT_TAB);

			{
				canContact = new Canvas(tabReport, SWT.NONE);
				tiContactReport.setControl(canContact);
				tblContactReport = new Table(canContact,
						SWT.MULTI | SWT.BORDER
								| SWT.FULL_SELECTION);
				tblContactReport.setLinesVisible(true);
				tblContactReport.setHeaderVisible(true);
				tblContactReport.setBounds(16, 119, 780,
						315);

				{
					lblContactBranch = new Label(
							canContact, SWT.NONE);
					lblContactBranch
							.setText("Select Branch");
					lblContactBranch.setBounds(10, 21, 70,
							20);
				}
				{
					cbContactBranch = new Combo(canContact,
							SWT.NONE);
					cbContactBranch.setBounds(90, 19, 95,
							21);
					cbContactBranch
							.addSelectionListener(this);
				}
				{
					lblDVSelectstation = new Label(
							canContact, SWT.NONE);
					lblDVSelectstation
							.setText("Select Station");
					lblDVSelectstation.setBounds(206, 19,
							78, 21);
				}
				{
					cbDVSelectstation = new Combo(
							canContact, SWT.NONE);
					cbDVSelectstation.setBounds(291, 19,
							118, 21);
				}
				{
					lblDVLrType = new Label(canContact,
							SWT.NONE);
					lblDVLrType.setText("LrType");
					lblDVLrType.setBounds(430, 19, 41, 21);
				}
				{
					cbDVLrType = new Combo(canContact,
							SWT.NONE);
					cbDVLrType.setBounds(477, 19, 103, 21);
					cbDVLrType.add("All");
					cbDVLrType.add("Topay");
					cbDVLrType.add("Paid");
					cbDVLrType.add("Billing");

				}
				{
					lblDVDeliveryType = new Label(
							canContact, SWT.NONE);
					lblDVDeliveryType.setText("Delivery");
					lblDVDeliveryType.setBounds(599, 19,
							42, 21);
				}
				{
					cbDVDeliveryType = new Combo(
							canContact, SWT.NONE);
					cbDVDeliveryType.setBounds(647, 18, 81,
							21);
					cbDVDeliveryType.add("All");
					cbDVDeliveryType.add("Door");
					cbDVDeliveryType.add("Office");
				}
				{
					lblDVInwardDays = new Label(canContact,
							SWT.NONE);
					lblDVInwardDays.setText("InwardDays");
					lblDVInwardDays.setBounds(12, 77, 67,
							22);
				}
				{
					txtDVInwardDays = new Text(canContact,
							SWT.BORDER);
					txtDVInwardDays.setBounds(85, 73, 75,
							22);
				}
				{
					lblDVAmount = new Label(canContact,
							SWT.NONE);
					lblDVAmount.setText("Amount");
					lblDVAmount.setBounds(197, 75, 47, 22);
				}
				{
					txtDVAmount = new Text(canContact,
							SWT.BORDER);
					txtDVAmount.setBounds(258, 75, 60, 22);
				}
				{
					btnDVSubmit = new Button(canContact,
							SWT.PUSH | SWT.CENTER);
					btnDVSubmit.setText("View");
					btnDVSubmit.setBounds(344, 72, 60, 30);
					btnDVSubmit
							.addSelectionListener(this);
				}

				{
					colContactSno = new TableColumn(
							tblContactReport, SWT.NONE);
					colContactSno.setText("S.No");
					colContactSno.setWidth(50);
					colContactSno.addListener(
							SWT.Selection,
							this);

				}
				{
					colContactLrno = new TableColumn(
							tblContactReport, SWT.NONE);
					colContactLrno.setText("Lrno");
					colContactLrno.setWidth(100);
					colContactLrno.addListener(
							SWT.Selection,
							this);
				}
				{
					colContactLrdate = new TableColumn(
							tblContactReport, SWT.NONE);
					colContactLrdate.setText("Lrdate");
					colContactLrdate.setWidth(84);
					colContactLrdate.addListener(
							SWT.Selection,
							this);
				}
				{
					colContactCnorName = new TableColumn(
							tblContactReport, SWT.NONE);
					colContactCnorName.setText("Cnor Name");
					colContactCnorName.setWidth(84);
					colContactCnorName.addListener(
							SWT.Selection,
							this);
				}
				{
					colContactCneeName = new TableColumn(
							tblContactReport, SWT.NONE);
					colContactCneeName.setText("Cnee Name");
					colContactCneeName.setWidth(84);
					colContactCneeName.addListener(
							SWT.Selection,
							this);
				}
				{
					colContactCneeAddress = new TableColumn(
							tblContactReport, SWT.NONE);
					colContactCneeAddress
							.setText("CneeAddress");
					colContactCneeAddress.setWidth(84);
					colContactCneeAddress.addListener(
							SWT.Selection,
							this);
				}
				{
					colContactNoa = new TableColumn(
							tblContactReport, SWT.NONE);
					colContactNoa.setText("Noa");
					colContactNoa.setWidth(60);
					colContactNoa.addListener(
							SWT.Selection,
							this);
				}
				{
					colContactActWt = new TableColumn(
							tblContactReport, SWT.NONE);
					colContactActWt.setText("ActWt");
					colContactActWt.setWidth(60);
					colContactActWt.addListener(
							SWT.Selection,
							this);
				}
				{
					colContactArtID = new TableColumn(
							tblContactReport, SWT.NONE);
					colContactArtID.setText("ArtID");
					colContactArtID.setWidth(60);
					colContactArtID.addListener(
							SWT.Selection,
							this);
				}
				{
					colContactLrTotal = new TableColumn(
							tblContactReport, SWT.NONE);
					colContactLrTotal.setText("LrTotal");
					colContactLrTotal.setWidth(60);
					colContactLrTotal.addListener(
							SWT.Selection,
							this);
				}
				{
					colContactDDCrg = new TableColumn(
							tblContactReport, SWT.NONE);
					colContactDDCrg.setText("DDCrg");
					colContactDDCrg.setWidth(60);
					colContactDDCrg.addListener(
							SWT.Selection,
							this);
				}
				{
					colContactLrType = new TableColumn(
							tblContactReport, SWT.NONE);
					colContactLrType.setText("LrType");
					colContactLrType.setWidth(60);
					colContactLrType.addListener(
							SWT.Selection,
							this);

				}
				{
					colContactInwarddays = new TableColumn(
							tblContactReport, SWT.NONE);
					colContactInwarddays
							.setText("InwarDays");
					colContactInwarddays.setWidth(60);
					colContactInwarddays.addListener(
							SWT.Selection,
							this);
				}
				{
					colContactFrom = new TableColumn(
							tblContactReport, SWT.NONE);
					colContactFrom.setText("From");
					colContactFrom.setWidth(60);
					colContactFrom.addListener(
							SWT.Selection,
							this);
				}
				{
					colContactTo = new TableColumn(
							tblContactReport, SWT.NONE);
					colContactTo.setText("To");
					colContactTo.setWidth(60);
					colContactTo.addListener(SWT.Selection,
							this);
				}

			}
			populateBranchForDV();

		}
		
		/*{
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
		
		/*{
		lblStatusBar = new Label(this, SWT.NONE);
		lblStatusBar.setBounds(60,570 ,300,25);
		lblStatusBar.setFont(STATUS_SUCCESS);
		lblStatusBar.setText("STATUS");
		lblStatusBar.setForeground(LINK_FOCUS_COLOR);
		lblStatusBar.setVisible(true);
		}*/
	
		
		
		return this;
	}

	
	private DeliveryVerificationDTO[] getVerificationReport() {
		try {
			String branch = cbContactBranch.getText();
			String station = cbDVSelectstation.getText();
			String lr_type = cbDVLrType.getText();
			String delivery_type = cbDVDeliveryType.getText();
			String inward_days = txtDVInwardDays.getText();
			String amount = txtDVAmount.getText();
			
			int index = branch.indexOf("-");
			if (index > -1) {
				branch = branch.substring(index + 1).trim();
			}
			index = station.indexOf("-");
			if (index > -1) {
				station = station.substring(index + 1).trim();
			}

			String[] details = { branch, station, lr_type, delivery_type,
					inward_days, amount };
			return handler.getVerificationReport(details);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param dto
	 */
	private void populateVerificationReport(DeliveryVerificationDTO[] dto)
			throws Exception {
		if (dto != null) {
			int len = dto.length;

			if (tblContactReport != null) {
				tblContactReport.removeAll();
				Date today = user_formate.parse(beanutil.getServerDate());
				for (int i = 0; i < len; i++) {

					TableItem item = new TableItem(tblContactReport,
							SWT.NONE);

					item.setText(0, String.valueOf(i + 1));
					item.setText(1, dto[i].getLr_no());
					item.setText(2, getStringFromDate(dto[i].getLr_date()));
					item.setText(3, dto[i].getCnor_name());
					item.setText(4, dto[i].getCnee_name());
					item.setText(5, dto[i].getCnee_address());
					item.setText(6, getStringFromInt(dto[i].getNoa()));
					item.setText(7, getStringFromFloat(dto[i]
							.getActual_wt()));
					item.setText(8, dto[i].getArt_id());
					item.setText(9,
							getStringFromFloat(dto[i].getLr_total()));
					item.setText(10, getStringFromFloat(dto[i].getDdc()));
					item.setText(11, dto[i].getLr_type());

					if (null != dto[i].getOutward_date()) {
						long diff = today.getTime()
								- dto[i].getOutward_date().getTime();
						float days = (diff / (1000 * 60 * 60 * 24));

						item.setText(12, getStringFromFloat(days));
					}

					item.setText(13, dto[i].getFrom());
					item.setText(14, dto[i].getTo());

				}
			}
		} else {
			if (tblContactReport != null) {
				tblContactReport.removeAll();
			}
			displayError("No Records found");
		}

	}

	private void displayError(String errorText) {
		MessageBox messageBox = new MessageBox(shell, SWT.OK);
		messageBox.setMessage(errorText);
		messageBox.setText("Alert");
		messageBox.open();
	}
	
	private String getStringFromDate(Date date_date) {
		String date_string = user_formate.format(date_date);
		return date_string;
	}

	/**
	 * 
	 * @param date_date
	 * @return
	 */
	private String getStringFromFloat(Float float_float) {
		String float_string = String.valueOf(float_float);
		return float_string;
	}

	private String getStringFromInt(int int_int) {
		String int_string = String.valueOf(int_int);
		return int_string;
	}

	/**
	 * 
	 */
	private void populateAllStationsForDV() {

		try {
			String stationCode = handler.getStationCode();
			StationsDTO[] station = null;
			if (stationCode != null) {
				station = handler.getAllStations();
			}
			if (null != station) {
				int len = station.length;
				cbDVSelectstation.removeAll();
				cbDVSelectstation.add("All");
				for (int i = 0; i < len; i++) {
					cbDVSelectstation.add(station[i].getName() + " - "
							+ station[i].getId());
				}

			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}

	
	
	private void populateBranchForDV() {

		StationsDTO[] station = null;
		try {
			station = handler.getAllBranches();
			if (null != station) {
				int len = station.length;
				cbContactBranch.add("All");
				for (int i = 0; i < len; i++) {
					cbContactBranch.add(station[i].getName() + " - "
							+ station[i].getId());
				}

			}
		} catch (Exception exception) {
			exception.printStackTrace();
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
		String stationName = null;
		String stationCode = null;
		String value = null;
		
		 if (btnDVSubmit == source) {
				try {
					DeliveryVerificationDTO[] dto = getVerificationReport();
					populateVerificationReport(dto);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if (cbContactBranch == source) {

				StationsDTO[] station = null;
				try {

					value = cbContactBranch.getText();
					if (!value.equalsIgnoreCase("All")) {
						int index = value.indexOf(" - ");
						stationCode = value.substring(index + 3);

						if (stationCode != null) {
							station = handler.getStations(stationCode);
						}
						if (null != station) {

							int len = station.length;
							cbDVSelectstation.removeAll();
							cbDVSelectstation.add("All");
							for (int i = 0; i < len; i++) {
								cbDVSelectstation.add(station[i].getName()
										+ " - " + station[i].getId());
							}

						}
					} else {
						populateAllStationsForDV();
					}
				} catch (Exception exception) {

				}

			} 
		
	}



	@Override
	public void handleEvent(Event e) {
		// TODO Auto-generated method stub
		
		TableColumn column = (TableColumn) e.widget;
		
		if (column == colContactSno) {
			TableSort.SortByFloat(0, tblContactReport);
		} else if (column == colContactLrno) {
			TableSort.sortByLrNo(1, tblContactReport);
		} else if (column == colContactLrdate) {
			TableSort.SortByDate(2, tblContactReport);
		} else if (column == colContactCnorName) {
			TableSort.sortByString(3, tblContactReport);
		} else if (column == colContactCneeName) {
			TableSort.sortByString(4, tblContactReport);
		} else if (column == colContactCneeAddress) {
			TableSort.sortByString(5, tblContactReport);
		} else if (column == colContactNoa) {
			TableSort.SortByFloat(6, tblContactReport);
		} else if (column == colContactActWt) {
			TableSort.SortByFloat(7, tblContactReport);
		} else if (column == colContactArtID) {
			TableSort.SortByFloat(8, tblContactReport);
		} else if (column == colContactLrTotal) {
			TableSort.SortByFloat(9, tblContactReport);
		} else if (column == colContactDDCrg) {
			TableSort.SortByFloat(10, tblContactReport);
		} else if (column == colContactLrType) {
			TableSort.sortByString(11, tblContactReport);
		} else if (column == colContactInwarddays) {
			TableSort.SortByFloat(12, tblContactReport);
		} else if (column == colContactFrom) {
			TableSort.sortByString(13, tblContactReport);
		} else if (column == colContactTo) {
			TableSort.sortByString(14, tblContactReport);
		}
		
	}
	
}
