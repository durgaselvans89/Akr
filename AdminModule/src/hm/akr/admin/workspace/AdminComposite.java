package hm.akr.admin.workspace;

import hm.akr.admin.commission.BookingProfileComposite;
import hm.akr.admin.commission.CommissionComposite;
import hm.akr.admin.commission.CommissionViewComposite;
import hm.akr.admin.commission.StationCommissionComposite;
import hm.akr.admin.customer.BillComposite;
import hm.akr.admin.customer.CustomerComposite;
import hm.akr.admin.customer.CustomerLogin;
import hm.akr.admin.distance.DistanceComposite;
import hm.akr.admin.drs.DRSFineSettingsComposite;
import hm.akr.admin.history.HistoryComposite;
import hm.akr.admin.holidays.HolidaysSettingsComposite;
import hm.akr.admin.quotation.QuotationComposite;
import hm.akr.admin.quotation.QuotationDeleteComposite;
import hm.akr.admin.quotation.QuotationViewOptionComposite;
import hm.akr.admin.reports.AdminReports;
import hm.akr.admin.salesquotation.SalesQuotationApproveComposite;
import hm.akr.admin.salesquotation.SalesQuotationComposite;
import hm.akr.admin.salesquotation.SalesQuotationViewOptionComposite;
import hm.akr.admin.station.StationComposite;
import hm.akr.admin.sundry.RegularSundryComposite;
import hm.akr.admin.sundry.commodity.CommoditySundryComposite;
import hm.akr.admin.sundry.special.SpecialSundryComposite;
import hm.akr.common.BeanUtil;
import hm.akr.common.HeaderComposite;
import hm.akr.common.IUIConstants;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ExpandEvent;
import org.eclipse.swt.events.ExpandListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.events.IHyperlinkListener;
import org.eclipse.ui.forms.widgets.Hyperlink;

/**
 * 
 * @author
 * 
 */
public class AdminComposite extends Composite implements IUIConstants, IHyperlinkListener {

	private static Label lblStatusBar;

	private Composite cptAdmin;
	
	Composite composite;

	GridLayout layout;
	
	private Label lblHead;	

	private FormData layoutAlign;	
		
	private String COMMODITY = " Commodity";
	
	private String REGULAR = " Regular Sundry";
	
	private String SPECIAL = " Special Sundry";
	
	private String CREATE_CUSTOMER = " Manage Customer";
	
	private String SHOW_BILL = " Show Bill";
	
	private String CUSTOMER_LOGIN = " Customer Login";
	
	private String CREATE_QUOTATION = " Create Quotation";

	private String EDIT_QUOTATION = " Edit Quotation";
	
	private String DELETE_QUOTATION = " Delete Quotation";
	
	private String SALES_QUOTATION = " Sales Quotation";
	
	private String EDIT_SALES_QUOTATION = " Edit Sales Quotation";
	
	private String APPROVE_SALES_QUOTATION = " Approve Sales Quotation";
	
	private String CREATE_PROFILE = " Create Profile";
	
	private String COMMISSION = " Commission";
	
	private String VIEW_COMMISSION = " View Commission";	
	
	private String STATION_COMMISSION = " Station Commission";
	
	private String DISTANCE = " Distance";
	
	private String STATION = " Station";
	
	private String HISTORY = " History";
	
	private Hyperlink linkSpecial;
	
	private Hyperlink linkRegular;
	
	private Hyperlink linkCommodity;
	
	private Hyperlink linkCreateCustomer;

	private Hyperlink linkShowBill;

	private Hyperlink linkCreateQuotation;

	private Hyperlink linkEditQuotation;

	private Hyperlink linkCreateProfile;

	private Hyperlink linkCommission;

	private Hyperlink linkViewCommission;

	private Hyperlink linkDistance;

	private Hyperlink linkStation;
	
	private Hyperlink linkHistory;

	private Hyperlink linkDeleteQuotation;
	
	private boolean isShowAll = true;
	
	BeanUtil beanutil = null;

	private Hyperlink linkSalesQuotation;

	private Hyperlink linkEditSalesQuotation;

	private Hyperlink linkApproveSalesQuotation;

	private Hyperlink linkCustomerLogin;
	
	ExpandItem itemSundry = null;
	
	ExpandItem item2 = null;	
	
	private String DRS_FINE = "DRS Fine";

	private String HOLIDAYS = "Holidays";
	
	private String REPORTS = "Reports";
	
	private Hyperlink linkDRSFine;

	private Hyperlink linkHoliday;

	private Hyperlink linkReports;

	private Hyperlink linkStationCommission;
	
	/**
	 * 
	 * @param shell
	 * @param style
	 */
	public AdminComposite(Shell shell, int style) {
		super(shell, style);
		try {
			beanutil = BeanUtil.getInstance();
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to load the components
	 * 
	 * @return Composite
	 * @throws Exception
	 */
	public Composite loadComposite() throws Exception {

		int tabIndex=0;
		FormLayout thisLayout = new FormLayout();
		this.setLayout(thisLayout);
		//this.setSize(1020, 789);
		this.setBounds(0,0,1020,700);
		{
			layoutAlign = new FormData();
			layoutAlign.width = 830;
			layoutAlign.height = 564;
			layoutAlign.left = new FormAttachment(0, 1000, 181);
			layoutAlign.top = new FormAttachment(0, 1000, 115);
		}
		{
			lblStatusBar = new Label(this, SWT.NONE);
			FormData lblStatusBarLData = new FormData();
			lblStatusBarLData.width = 500;
			lblStatusBarLData.height = 32;
			lblStatusBarLData.left = new FormAttachment(0, 1000, 200);
			lblStatusBarLData.top = new FormAttachment(0, 1000, 689);
			lblStatusBar.setLayoutData(lblStatusBarLData);
			lblStatusBar.setFont(STATUS_SUCCESS);
			lblStatusBar.setText("STATUS");
			lblStatusBar.setForeground(LINK_FOCUS_COLOR);
		}

		FormData gdStatus1 = new FormData();
		gdStatus1.width = 162;
		gdStatus1.height = 609;
		gdStatus1.left = new FormAttachment(0, 1000, 0);
		gdStatus1.right = new FormAttachment(174, 1000, 0);
		gdStatus1.top = new FormAttachment(141, 1000, 0);
		gdStatus1.bottom = new FormAttachment(1000, 1000, 0);

		new HeaderComposite(this, SWT.BORDER).loadComposite();

		ExpandBar bar = new ExpandBar(this, SWT.V_SCROLL);
		bar.setLayoutData(gdStatus1);
		bar.setBackground(BACK_COLOR);
		bar.setForeground(FORE_COLOR);
		//bar.setBackgroundImage();
		
		// Show/Hide sundry		
		if(BeanUtil.getAdminRights() == 3){
		composite = new Composite(bar, SWT.NONE);

		layout = new GridLayout();
		layout.numColumns = 1;
		layout.verticalSpacing = 10;
		composite.setLayout(layout);
		//composite.setBackground(FORE_COLOR);
		
		linkRegular = new Hyperlink(composite, SWT.NORMAL);		
		linkRegular.setText(REGULAR);
		linkRegular.setFont(LINK_FONT);
		linkRegular.setForeground(LINK_COLOR);		
		linkRegular.addHyperlinkListener(this);		
		

		linkSpecial = new Hyperlink(composite, SWT.NORMAL);
		linkSpecial.setText(SPECIAL);
		linkSpecial.setFont(LINK_FONT);
		linkSpecial.setForeground(LINK_COLOR);		
		linkSpecial.addHyperlinkListener(this);
		
		linkCommodity = new Hyperlink(composite, SWT.NONE);		
		linkCommodity.setText(COMMODITY);
		linkCommodity.setFont(LINK_FONT);
		linkCommodity.setForeground(LINK_COLOR);
		linkCommodity.addHyperlinkListener(this);
		
		itemSundry = new ExpandItem(bar, SWT.NONE, tabIndex);
		itemSundry.setText("Sundry");
		itemSundry.setHeight(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		itemSundry.setControl(composite);
		itemSundry.setExpanded(false);
		tabIndex++;
		}
		
		// To hide sundry menu
		
			 
		if(BeanUtil.getAdminRights() > 0){
		composite = new Composite(bar, SWT.NONE);
		layout = new GridLayout();
		layout.verticalSpacing = 10;
		composite.setLayout(layout);

		linkCreateQuotation = new Hyperlink(composite, SWT.NONE);		
		linkCreateQuotation.setText(CREATE_QUOTATION);
		linkCreateQuotation.setFont(LINK_FONT);
		linkCreateQuotation.setForeground(LINK_COLOR);
		linkCreateQuotation.addHyperlinkListener(this);		

		
		linkEditQuotation = new Hyperlink(composite, SWT.NONE);		
		linkEditQuotation.setText(EDIT_QUOTATION);
		linkEditQuotation.setFont(LINK_FONT);
		linkEditQuotation.setForeground(LINK_COLOR);
		linkEditQuotation.addHyperlinkListener(this);
		
		linkDeleteQuotation = new Hyperlink(composite, SWT.NONE);		
		linkDeleteQuotation.setText(DELETE_QUOTATION);
		linkDeleteQuotation.setFont(LINK_FONT);
		linkDeleteQuotation.setForeground(LINK_COLOR);
		linkDeleteQuotation.addHyperlinkListener(this);
		
		linkSalesQuotation = new Hyperlink(composite, SWT.NONE);		
		linkSalesQuotation.setText(SALES_QUOTATION);
		linkSalesQuotation.setFont(LINK_FONT);
		linkSalesQuotation.setForeground(LINK_COLOR);
		linkSalesQuotation.addHyperlinkListener(this);
		
		linkEditSalesQuotation = new Hyperlink(composite, SWT.NONE);		
		linkEditSalesQuotation.setText(EDIT_SALES_QUOTATION);
		linkEditSalesQuotation.setFont(LINK_FONT);
		linkEditSalesQuotation.setForeground(LINK_COLOR);
		linkEditSalesQuotation.addHyperlinkListener(this);
		
		linkApproveSalesQuotation = new Hyperlink(composite, SWT.NONE);		
		linkApproveSalesQuotation.setText(APPROVE_SALES_QUOTATION);
		linkApproveSalesQuotation.setFont(LINK_FONT);
		linkApproveSalesQuotation.setForeground(LINK_COLOR);
		linkApproveSalesQuotation.addHyperlinkListener(this);
	

		item2 = new ExpandItem(bar, SWT.NONE, tabIndex);
		item2.setText("Quotation");
		item2.setHeight(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		item2.setControl(composite);
		item2.setExpanded(false);
		/*if(BeanUtil.getAdminRights() == 3){
			item2.setExpanded(false);
		}else{
			item2.setExpanded(true);
		}*/
		tabIndex++;
		}
		
		// Expand Item
		bar.addExpandListener(new ExpandListener(){

			@Override
			public void itemCollapsed(ExpandEvent e) {
				//System.out.println("sundHT==>"+e.widget);
				
			}

			@Override
			public void itemExpanded(ExpandEvent e) {
				//if(BeanUtil.getAdminRights() == 3){				

				//System.out.println("expan==>"+bar.getBorderWidth());
				//System.out.println("sundHT exxx==>"+e.item);
				String s = e.item.toString();
				//System.out.println(s);
				if(s.contains("Sundry")){
					if(item2 != null && item2.getExpanded()){
						item2.setExpanded(false);
					}
				}else if(s.contains("Quotation")){
					if(itemSundry != null && itemSundry.getExpanded()){
						itemSundry.setExpanded(false);
					}
				}				
				}
			//}
			
		});
		
		// Show/Hide commission
		if(isShowAll)
		{
		composite = new Composite(bar, SWT.NONE);
		layout = new GridLayout();
		layout.verticalSpacing = 10;
		composite.setLayout(layout);

		
		if(BeanUtil.getAdminRights() == 3){
		linkCreateProfile = new Hyperlink(composite, SWT.NONE);		
		linkCreateProfile.setText(CREATE_PROFILE);
		linkCreateProfile.setFont(LINK_FONT);
		linkCreateProfile.setForeground(LINK_COLOR);
		linkCreateProfile.addHyperlinkListener(this);
		}

		if(BeanUtil.getAdminRights() != 1){
		linkCommission = new Hyperlink(composite, SWT.NONE);		
		linkCommission.setText(COMMISSION);
		linkCommission.setFont(LINK_FONT);
		linkCommission.setForeground(LINK_COLOR);
		linkCommission.addHyperlinkListener(this);
		
		linkViewCommission = new Hyperlink(composite, SWT.NONE);		
		linkViewCommission.setText(VIEW_COMMISSION);
		linkViewCommission.setFont(LINK_FONT);
		linkViewCommission.setForeground(LINK_COLOR);
		linkViewCommission.addHyperlinkListener(this);
		
		linkStationCommission = new Hyperlink(composite, SWT.NONE);		
		linkStationCommission.setText(STATION_COMMISSION);
		linkStationCommission.setFont(LINK_FONT);
		linkStationCommission.setForeground(LINK_COLOR);
		linkStationCommission.addHyperlinkListener(this);
				
		ExpandItem item3 = new ExpandItem(bar, SWT.NONE, tabIndex);
		item3.setText("Agency");
		item3.setHeight(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		item3.setControl(composite);
		item3.setExpanded(false);
		tabIndex++;
		}
		}
		
		// Show/Hide distance
		
		//if(isShowAll)
		//{
		composite = new Composite(bar, SWT.NONE);
		layout = new GridLayout();
		layout.verticalSpacing = 10;
		composite.setLayout(layout);

		linkDistance = new Hyperlink(composite, SWT.NONE);		
		linkDistance.setText(DISTANCE);
		linkDistance.setFont(LINK_FONT);
		linkDistance.setForeground(LINK_COLOR);
		linkDistance.addHyperlinkListener(this);

		ExpandItem item4 = new ExpandItem(bar, SWT.NONE, tabIndex);
		item4.setText("Distance Map");
		item4.setHeight(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		item4.setControl(composite);
		item4.setExpanded(true);
		tabIndex++;
		//}

		composite = new Composite(bar, SWT.NONE);
		layout = new GridLayout();
		layout.verticalSpacing = 10;
		composite.setLayout(layout);		
		
		linkCreateCustomer = new Hyperlink(composite, SWT.NONE);		
		linkCreateCustomer.setText(CREATE_CUSTOMER);
		linkCreateCustomer.setFont(LINK_FONT);
		linkCreateCustomer.setForeground(LINK_COLOR);
		linkCreateCustomer.addHyperlinkListener(this);
		
		linkShowBill = new Hyperlink(composite, SWT.NONE);		
		linkShowBill.setText(SHOW_BILL);
		linkShowBill.setFont(LINK_FONT);
		linkShowBill.setForeground(LINK_COLOR);
		linkShowBill.addHyperlinkListener(this);
		
		if(BeanUtil.getAdminRights() == 1 || BeanUtil.getAdminRights() == 3){
		linkCustomerLogin = new Hyperlink(composite, SWT.NONE);		
		linkCustomerLogin.setText(CUSTOMER_LOGIN);
		linkCustomerLogin.setFont(LINK_FONT);
		linkCustomerLogin.setForeground(LINK_COLOR);
		linkCustomerLogin.addHyperlinkListener(this);
		}
		
		// Customer Bar
		ExpandItem itemCustomer = new ExpandItem(bar, SWT.NONE, tabIndex);
		itemCustomer.setText("Customer");
		itemCustomer.setHeight(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		itemCustomer.setControl(composite);
		itemCustomer.setExpanded(true);
		tabIndex++;

		// Staion Menu		
		composite = new Composite(bar, SWT.NONE);
		layout = new GridLayout();
		layout.verticalSpacing = 10;
		composite.setLayout(layout);	
		
		if(BeanUtil.getAdminRights() == 2 || BeanUtil.getAdminRights() == 3){
		linkStation = new Hyperlink(composite, SWT.NONE);		
		linkStation.setText(STATION);
		linkStation.setFont(LINK_FONT);
		linkStation.setForeground(LINK_COLOR);		
		linkStation.addHyperlinkListener(this);
		
		ExpandItem item5 = new ExpandItem(bar, SWT.NONE, tabIndex);
		item5.setText("Station Settings");
		item5.setHeight(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		item5.setControl(composite);
		item5.setExpanded(true);
		tabIndex++;
	}
		bar.setSpacing(10);

		
		/* DRS Item Starts */
		composite = new Composite(bar, SWT.NONE);
		layout = new GridLayout();
		layout.verticalSpacing = 10;
		composite.setLayout(layout);

		linkDRSFine = new Hyperlink(composite, SWT.NONE);
		linkDRSFine.setText(DRS_FINE);
		linkDRSFine.setFont(LINK_FONT);
		linkDRSFine.setForeground(LINK_COLOR);
		linkDRSFine.addHyperlinkListener(this);
		
		linkHoliday = new Hyperlink(composite, SWT.NONE);
		linkHoliday.setText(HOLIDAYS);
		linkHoliday.setFont(LINK_FONT);
		linkHoliday.setForeground(LINK_COLOR);
		linkHoliday.addHyperlinkListener(this);
		
		ExpandItem itemDRS = new ExpandItem(bar, SWT.NONE, tabIndex);
		itemDRS.setText("DRS");
		itemDRS.setHeight(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		itemDRS.setControl(composite);
		itemDRS.setExpanded(true);
		tabIndex++;
		/* DRS Item Ends */
	
		
		/* Reports Item starts */
		composite = new Composite(bar, SWT.NONE);
		layout = new GridLayout();
		layout.verticalSpacing = 10;
		composite.setLayout(layout);

		/*linkReports = new Hyperlink(composite, SWT.NONE);
		linkReports.setText(REPORTS);
		linkReports.setFont(LINK_FONT);
		linkReports.setForeground(LINK_COLOR);
		linkReports.addHyperlinkListener(this);
		ExpandItem itemReports = new ExpandItem(bar, SWT.NONE, tabIndex);
		itemReports.setText(REPORTS);
		itemReports
				.setHeight(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		itemReports.setControl(composite);
		itemReports.setExpanded(true);
		/* Reports Item ends */
		
		//history
		composite = new Composite(bar, SWT.NONE);
		layout = new GridLayout();
		layout.verticalSpacing = 10;
		composite.setLayout(layout);
		
		/*linkHistory = new Hyperlink(composite, SWT.NONE);		
		linkHistory.setText(HISTORY);
		linkHistory.setFont(LINK_FONT);
		linkHistory.setForeground(LINK_COLOR);		
		linkHistory.addHyperlinkListener(this);
		
		ExpandItem item5 = new ExpandItem(bar, SWT.NONE, tabIndex);
		item5.setText("History");
		item5.setHeight(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		item5.setControl(composite);
		item5.setExpanded(true);*/
		tabIndex++;
		//history ends
		
		lblHead = new Label(this, SWT.NONE);
		FormData lblnameLData = new FormData();
		lblnameLData.width = 170;
		lblnameLData.height = 24;
		lblnameLData.left = new FormAttachment(830, 1000, 0);
		//lblnameLData.right = new FormAttachment(954, 1000, 0);
		lblnameLData.top = new FormAttachment(140, 1000, 0);
		//lblnameLData.bottom = new FormAttachment(176, 1000, 0);
		lblHead.setLayoutData(lblnameLData);
		lblHead.setFont(HEAD_FONT);
		lblHead.setForeground(HEAD_FONT_COLOR);
		lblHead.setAlignment(SWT.LEFT);
		// hidden
		lblHead.setVisible(false);
		
		
	
		
		return this;
	}
	public static void display(String status,Font font,Color color)
	{
		lblStatusBar.setText(status);
		lblStatusBar.setFont(font);
		lblStatusBar.setForeground(LINK_FOCUS_COLOR);
	}	

	@Override
	public void linkActivated(HyperlinkEvent event) {		
		Object source = event.getSource();
		
		if (source == linkRegular) {
			
			//linkRegular.setForeground(LINK_CLICKED_COLOR);
			if (null != cptAdmin)
				cptAdmin.dispose();

			lblHead.setText("Regular Sundry");			
			try {
				AdminComposite.display(
						"STATUS",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				cptAdmin = new RegularSundryComposite(this, SWT.BORDER)
						.loadComposite();
				cptAdmin.setLayoutData(layoutAlign);
				this.layout();
			} catch (Exception exception) {
				exception.printStackTrace();
			}

		} else if (source == linkSpecial) {
			//linkSpecial.setForeground(LINK_CLICKED_COLOR);
			if (null != cptAdmin)
				cptAdmin.dispose();
			lblHead.setText("Special Sundry");
			try {
				AdminComposite.display(
						"STATUS",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				cptAdmin = new SpecialSundryComposite(this, SWT.BORDER)
						.loadComposite();

				cptAdmin.setLayoutData(layoutAlign);

				this.layout();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		} else if (source == linkCommodity) {
			//linkCommodity.setForeground(LINK_CLICKED_COLOR);
			lblHead.setText("Commodity Sundry");

			try {
				AdminComposite.display(
						"STATUS",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				if (null != cptAdmin)
					cptAdmin.dispose();
				cptAdmin = new CommoditySundryComposite(this, SWT.BORDER)
						.loadComposite();

				cptAdmin.setLayoutData(layoutAlign);
			} catch (Exception exception) {
				exception.printStackTrace();
			}

			this.layout();
		} else if (source == linkCreateCustomer) {
			//linkCreateCustomer.setForeground(LINK_CLICKED_COLOR);
			lblHead.setText("Create Customer");

			try {
				AdminComposite.display(
						"STATUS",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				if (null != cptAdmin)
					cptAdmin.dispose();

				cptAdmin = new CustomerComposite(this, SWT.BORDER).loadComposite();
				cptAdmin.setLayoutData(layoutAlign);
			} catch (Exception exception) {
				exception.printStackTrace();
			}

			this.layout();
		} else if (source == linkShowBill) {
			//linkShowBill.setForeground(LINK_CLICKED_COLOR);
			lblHead.setText("View Bill");

			try {
				AdminComposite.display(
						"STATUS",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				if (null != cptAdmin)
					cptAdmin.dispose();

				cptAdmin = new BillComposite(this, SWT.BORDER).loadComposite();
				cptAdmin.setLayoutData(layoutAlign);
			} catch (Exception exception) {
				exception.printStackTrace();
			}

			this.layout();
		}else if (source == linkCustomerLogin) {
			//linkShowBill.setForeground(LINK_CLICKED_COLOR);
			lblHead.setText("Customer Login");

			try {
				AdminComposite.display(
						"STATUS",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				if (null != cptAdmin)
					cptAdmin.dispose();

				cptAdmin = new CustomerLogin(this, SWT.BORDER).loadComposite();
				cptAdmin.setLayoutData(layoutAlign);
			} catch (Exception exception) {
				exception.printStackTrace();
			}

			this.layout();
		} else if (source == linkCreateQuotation) {
			//linkCreateQuotation.setForeground(LINK_CLICKED_COLOR);
			lblHead.setText("Quotation");

			try {
				AdminComposite.display(
						"STATUS",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				if (null != cptAdmin)
					cptAdmin.dispose();

				cptAdmin = new QuotationComposite(this, SWT.BORDER)
						.loadComposite();

				cptAdmin.setLayoutData(layoutAlign);
			} catch (Exception exception) {

				exception.printStackTrace();
			}

			this.layout();
		} else if (source == linkEditQuotation) {
			//linkEditQuotation.setForeground(LINK_CLICKED_COLOR);
			lblHead.setText("Quotation View/Edit");

			try {
				AdminComposite.display(
						"STATUS",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				if (null != cptAdmin)
					cptAdmin.dispose();
				cptAdmin = new QuotationViewOptionComposite(this, SWT.BORDER)
						.loadComposite();

				cptAdmin.setLayoutData(layoutAlign);
			} catch (Exception exception) {

				exception.printStackTrace();
			}

			this.layout();

		} else if (source == linkDeleteQuotation) {
			//linkEditQuotation.setForeground(LINK_CLICKED_COLOR);
			lblHead.setText("Quotation Delete");

			try {
				AdminComposite.display(
						"STATUS",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				if (null != cptAdmin)
					cptAdmin.dispose();
				cptAdmin = new QuotationDeleteComposite(this, SWT.BORDER)
						.loadComposite();

				cptAdmin.setLayoutData(layoutAlign);
			} catch (Exception exception) {

				exception.printStackTrace();
			}

			this.layout();

		}  else if (source == linkSalesQuotation) {
			//linkEditQuotation.setForeground(LINK_CLICKED_COLOR);
			lblHead.setText("Sales Quotation");

			try {
				AdminComposite.display(
						"STATUS",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				if (null != cptAdmin)
					cptAdmin.dispose();
				cptAdmin = new SalesQuotationComposite(this, SWT.BORDER)
						.loadComposite();

				cptAdmin.setLayoutData(layoutAlign);
			} catch (Exception exception) {

				exception.printStackTrace();
			}

			this.layout();

		} else if (source == linkEditSalesQuotation) {
			//linkEditQuotation.setForeground(LINK_CLICKED_COLOR);
			lblHead.setText("Edit Sales Quotation");

			try {
				AdminComposite.display(
						"STATUS",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				if (null != cptAdmin)
					cptAdmin.dispose();
				cptAdmin = new SalesQuotationViewOptionComposite(this, SWT.BORDER)
						.loadComposite();

				cptAdmin.setLayoutData(layoutAlign);
			} catch (Exception exception) {
				exception.printStackTrace();
			}

			this.layout();

		} else if (source == linkApproveSalesQuotation) {
			//linkEditQuotation.setForeground(LINK_CLICKED_COLOR);
			lblHead.setText("Edit Sales Quotation");

			try {
				AdminComposite.display(
						"STATUS",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				if (null != cptAdmin)
					cptAdmin.dispose();
				cptAdmin = new SalesQuotationApproveComposite(this, SWT.BORDER)
						.loadComposite();

				cptAdmin.setLayoutData(layoutAlign);
			} catch (Exception exception) {
				exception.printStackTrace();
			}

			this.layout();

		} else if (source == linkCreateProfile) {
			//linkCreateProfile.setForeground(LINK_CLICKED_COLOR);
			try {
				AdminComposite.display(
						"STATUS",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				if (null != cptAdmin)
					cptAdmin.dispose();

				lblHead.setText("Profile Creation");

				cptAdmin = new BookingProfileComposite(this, SWT.BORDER)
						.loadComposite();

				cptAdmin.setLayoutData(layoutAlign);
			} catch (Exception exception) {

				exception.printStackTrace();
			}

			this.layout();

		}

		else if (source == linkCommission) {
			//linkCommission.setForeground(LINK_CLICKED_COLOR);
			try {
				AdminComposite.display(
						"STATUS",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				if (null != cptAdmin)
					cptAdmin.dispose();

				lblHead.setText("Commision");
				cptAdmin = new CommissionComposite(this, SWT.BORDER)
						.loadComposite();

				cptAdmin.setLayoutData(layoutAlign);
			} catch (Exception exception) {
				exception.printStackTrace();
			}

			this.layout();

		}

		else if (source == linkViewCommission) {
			//linkViewCommission.setForeground(LINK_CLICKED_COLOR);
			lblHead.setText("Commision View");

			try {
				AdminComposite.display(
						"STATUS",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				if (null != cptAdmin)
					cptAdmin.dispose();
				cptAdmin = new CommissionViewComposite(this, SWT.BORDER)
						.loadComposite();

			} catch (Exception exception) {

				exception.printStackTrace();
			}
			if (null != cptAdmin && !cptAdmin.isDisposed())
				cptAdmin.setLayoutData(layoutAlign);
			this.layout();

		} 
		else if (source == linkStationCommission) {
			//linkViewCommission.setForeground(LINK_CLICKED_COLOR);
			lblHead.setText("Station Commision");

			try {
				AdminComposite.display(
						"STATUS",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				if (null != cptAdmin)
					cptAdmin.dispose();
				cptAdmin = new StationCommissionComposite(this, SWT.BORDER)
						.loadComposite();

			} catch (Exception exception) {

				exception.printStackTrace();
			}
			if (null != cptAdmin && !cptAdmin.isDisposed())
				cptAdmin.setLayoutData(layoutAlign);
			this.layout();

		}

		else if (source == linkDistance) {
			
			lblHead.setText("Distance Map");

			try {
				AdminComposite.display(
						"STATUS",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				if (null != cptAdmin)
					cptAdmin.dispose();

				cptAdmin = new DistanceComposite(this, SWT.BORDER)
						.loadComposite();

			} catch (Exception exception) {

				exception.printStackTrace();
			}
			if (null != cptAdmin && !cptAdmin.isDisposed())
				cptAdmin.setLayoutData(layoutAlign);
			this.layout();

		} else if (source == linkStation) {
			//linkReport.setForeground(LINK_CLICKED_COLOR);
			lblHead.setText("Station Settings");

			try {
				AdminComposite.display(
						"STATUS",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				if (null != cptAdmin)
					cptAdmin.dispose();
				cptAdmin = new StationComposite(this, SWT.BORDER)
						.loadComposite();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
			if (null != cptAdmin && !cptAdmin.isDisposed())
				cptAdmin.setLayoutData(layoutAlign);
			this.layout();

		}else if (source == linkDRSFine) {
			//linkReport.setForeground(LINK_CLICKED_COLOR);
			lblHead.setText("DRS Fine Settings");

			try {
				AdminComposite.display(
						"STATUS",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				if (null != cptAdmin)
					cptAdmin.dispose();
				cptAdmin = new DRSFineSettingsComposite(this, SWT.BORDER)
						.loadComposite();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
			if (null != cptAdmin && !cptAdmin.isDisposed())
				cptAdmin.setLayoutData(layoutAlign);
			this.layout();

		}else if (source == linkHoliday) {
			//linkReport.setForeground(LINK_CLICKED_COLOR);
			lblHead.setText("Holiday Settings");

			try {
				AdminComposite.display(
						"STATUS",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				if (null != cptAdmin)
					cptAdmin.dispose();
				cptAdmin = new HolidaysSettingsComposite(this, SWT.BORDER)
						.loadComposite();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
			if (null != cptAdmin && !cptAdmin.isDisposed())
				cptAdmin.setLayoutData(layoutAlign);
			this.layout();

		}else if (source == linkReports) {
			lblHead.setText("Reports");

			try {
				AdminComposite.display("STATUS", STATUS_SUCCESS,
						SUCCESS_FONT_COLOR);
				if (null != cptAdmin)
					cptAdmin.dispose();

				cptAdmin = new AdminReports(this, SWT.BORDER).loadComposite();

			} catch (Exception exception) {

				exception.printStackTrace();
			}
			if (null != cptAdmin && !cptAdmin.isDisposed())
				cptAdmin.setLayoutData(layoutAlign);
			this.layout();

		} else if (source == linkHistory) {
			lblHead.setText("History");

			try {
				AdminComposite.display("STATUS", STATUS_SUCCESS,
						SUCCESS_FONT_COLOR);
				if (null != cptAdmin)
					cptAdmin.dispose();

				cptAdmin = new HistoryComposite(this, SWT.BORDER).loadComposite();

			} catch (Exception exception) {

				exception.printStackTrace();
			}
			if (null != cptAdmin && !cptAdmin.isDisposed())
				cptAdmin.setLayoutData(layoutAlign);
			this.layout();

		}
		
	}

	@Override
	public void linkEntered(HyperlinkEvent event) {
		Hyperlink link = (Hyperlink) event.getSource();
		link.setForeground(LINK_FOCUS_COLOR);
		link.setSize(130,25);
		link.setFont(LINK_FOCUS_FONT);
		
	}

	@Override
	public void linkExited(HyperlinkEvent event) {
		Hyperlink link = (Hyperlink) event.getSource();
		link.setForeground(LINK_COLOR);		
		link.setFont(LINK_FONT);
	}

}
