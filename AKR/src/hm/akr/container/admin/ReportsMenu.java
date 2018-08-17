package hm.akr.container.admin;



import hm.akr.common.BeanUtil;
import hm.akr.common.IUIConstants;
import hm.akr.container.ContainerManager;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.events.IHyperlinkListener;



public class ReportsMenu extends Composite implements IHyperlinkListener,IUIConstants,PaintListener{
	
	
	
	
	
	
	
	private Label lblMonitor;
	
	private Label lblAccount;
	
	private Label lblOperation;
	
	private Label lblMarketing;
	
	private String drsattendance = " DRS Attendence";
	
	private String remittanceshortage = " Remittance Shortage";
	
	private String staxannexure = " Service Tax Annexure - Consolidated";
	
	private String staxannexurelrwise = " Service Tax Annexure - LR Wise";
	
	private String cancelledlr = " Cancelled LRs Report";
	
	private String ccchargesummary = " CC Charge Summary";
	
	private String counter = " Counter";
	
	private String ddcharge = " DD Charge + DD Extra";
	
	private String foclr = " FOC LR";
	
	private String inwardregister = " Inward Register";
	
	private String openlr = " Open LR";
	
	private String totalundeliveredtopay = " Total Undelivered Topay";
	
	private String updready = " UPD Ready";
	
	private String leftovergoods = " Leftover Goods";
	
	private String marketvehicle = " Market Vehicle Utilisation";
	
	private String businessperformanceanalysis = " Business Performance Analysis";
	
	private String consignorbookinganalysis = " Consignor Booking Analysis";
	
	private String consigneebookinganalysis = " Consignee Booking Analysis";
	
	private String dailybookinganalysis = " Daily Booking Analysis";
	
	private String interintrastate = " Inter & Intra State";
	
	private String inwardanalysis = " Inward Analysis";
	
	private String missingcustomers = " Missing Customers";
	
	private String sundrybookinganalysis = " Sundry Booking Analysis";
	
	private String bookedlrs = " Booked LRs";
	
	private String versionreport = " Version Report";
	
	private String speedydelivery = " Speedy delivery";
	
	private String usagereport = " Usage Report";
	
	private String lrtrackcount = " LR Track Count";
	
	private String dailydeliverystatus = " Daily Delivery Status";
	
	private String deliveryverification = " Delivery Verification";
	
	private String stock = " Stock";
	
	private String cashoutstanding = " Cash Outstanding";
	
	private String dailyswactivity = " Daily Software Activity Report";
	
	private Hyperlink linkdrsattendance;
	
	private Hyperlink linkremittanceshortage;
	
	private Hyperlink linkstaxannexure;
	
	private Hyperlink linkstaxannexurelrwise;
	
	private Hyperlink linkcancelledlr;
	
	private Hyperlink linkccchargesummary;
	
	private Hyperlink linkcounter;
	
	private Hyperlink linkddcharge;
	
	private Hyperlink linkfoclr;
	
	private Hyperlink linkinwardregister;
	
	private Hyperlink linkopenlr;

	private Hyperlink linktotalundeliveredtopay;
	
	private Hyperlink linkupdready;
		
	private Hyperlink linkleftovergoods;
	
	private Hyperlink linkmarketvehicle;
	
	private Hyperlink linkbusinessperformanceanalysis;
	
	private Hyperlink linkconsignorbookinganalysis;
	
	private Hyperlink linkconsigneebookinganalysis;
	
	private Hyperlink linkdailybookinganalysis;
	
	private Hyperlink linkinterintrastate;
	
	private Hyperlink linkinwardanalysis;
	
	private Hyperlink linkmissingcustomers;
	
	private Hyperlink linksundrybookinganalysis;
	
	private Hyperlink linkbookedlrs;
	
	private Hyperlink linkversionreport;
	
	private Hyperlink linkspeedydelivery;
	
	private Hyperlink linkusagereport;
	
	private Hyperlink linklrtrackcount;
	
	private Hyperlink linkdailydeliverystatus;
	
	private Hyperlink linkdailyswactivity;
	
	private Hyperlink linkdeliveryverification;
	
	private Hyperlink linkstock;
	
	private Hyperlink linkcashoutstanding;
	
	/*String[] version0 = {cancelledlr,inwardregister,updready};
	String[] version1 = {drsattendance,remittanceshortage,ddcharge,inwardanalysis,businessperformanceanalysis,cancelledlr,ccchargesummary,consignorbookinganalysis,consigneebookinganalysis,counter,dailybookinganalysis,totalundeliveredtopay,foclr,leftovergoods,openlr,updready,sundrybookinganalysis,inwardregister,versionreport};
	String[] version2_1 = {drsattendance,remittanceshortage,staxannexure,staxannexurelrwise};
	String[] version2_2 = {ddcharge,cancelledlr,ccchargesummary,counter,totalundeliveredtopay,foclr,openlr,updready,inwardregister,usagereport,versionreport,speedydelivery,bookedlrs,lrtrackcount,dailydeliverystatus,deliveryverification,stock,cashoutstanding};
	String[] version2_3 = {updready,leftovergoods,marketvehicle};
	String[] version2_4 = {ccchargesummary,ddcharge,inwardanalysis,businessperformanceanalysis,consignorbookinganalysis,consigneebookinganalysis,dailybookinganalysis,interintrastate,leftovergoods,missingcustomers,sundrybookinganalysis,lrtrackcount};
					*/
	//private static Label lblStatusBar;
	
	/*public static final String STATUS_SUCEESS = "Verdana";
	
	public static final int SUCCESS_FONT_SIZE = 10;
	
	public static final Font STATUS_SUCCESS = new Font(Display.getCurrent(),STATUS_SUCEESS, SUCCESS_FONT_SIZE, 1);*/
	
	ContainerManager manager;
	Canvas CvsRpt = null;
	Shell shell = null;
	private Composite cptReport = null;
	
	BeanUtil beanutil = null;
	float clientRights = -1;
	
	private float RIGHTS_0 = new Float(0);
	private float RIGHTS_1 = new Float(1);
	private float RIGHTS_20 = new Float(2);
	private float RIGHTS_21 = new Float(2.1);
	private float RIGHTS_22 = new Float(2.2);
	private float RIGHTS_23 = new Float(2.3);
	private float RIGHTS_24 = new Float(2.4);
	private float RIGHTS_25 = new Float(2.5);
	
	private float RIGHTS_3 = new Float(3);
	
	public static int yvalue; 
	
	public static int rtyvalue; 

	
	
	
	public ReportsMenu(Composite composite,int style ) {
		super(composite, style);
		shell = composite.getShell();
		try {
			beanutil = BeanUtil.getInstance();
			clientRights = beanutil.getClientRights();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public Composite loadComposite() {
		
		  
	
		 yvalue = 135;
		 rtyvalue = 135;
		 
		
		CvsRpt = new Canvas(this, SWT.NONE);
		//CvsRpt.setBounds(50,90,924 ,620);
		CvsRpt.setBounds(10,10,1024 ,800);
		CvsRpt.setVisible(true);
		CvsRpt.addPaintListener(this);
			
		
		/*if(cptReport != null){
			cptReport.setVisible(false);
		}*/
		
		/*{
			lblStatusBar = new Label(this, SWT.NONE);
			FormData lblStatusBarLData = new FormData();
			lblStatusBarLData.width = 500;
			lblStatusBarLData.height = 32;
			lblStatusBarLData.left = new FormAttachment(0, 1000, 200);
			lblStatusBarLData.top = new FormAttachment(0, 1000, 755);
			lblStatusBar.setLayoutData(lblStatusBarLData);
			lblStatusBar.setFont(STATUS_SUCCESS);
			lblStatusBar.setText("STATUS");
			lblStatusBar.setForeground(LINK_FOCUS_COLOR);
		}*/
			
			
			
		{
			lblAccount = new Label(CvsRpt,SWT.NONE);
			lblAccount.setText("ACCOUNTS");
			lblAccount.setFont(REPLABEL_FONT);
			if(clientRights == RIGHTS_3 || clientRights == RIGHTS_25 || clientRights == RIGHTS_20 || clientRights == RIGHTS_1){
				lblAccount.setBounds(600, 120, 100, 25);
			}else if(clientRights == RIGHTS_21){
				lblAccount.setBounds(50, 120, 100, 25);
			}
			
		}
		{
			lblMonitor = new Label(CvsRpt,SWT.NONE);
			lblMonitor.setText("MONITORING");
			lblMonitor.setFont(REPLABEL_FONT);
			if(!(clientRights == RIGHTS_21)){
				lblMonitor.setBounds(50, 120, 100, 25);
			}
			
		}
		
		
	
		//System.out.println("cln right-->"+beanutil.getClientRights());
		
		
		{
			linkbookedlrs = new Hyperlink(CvsRpt, SWT.NORMAL);
			linkbookedlrs.setText(bookedlrs);
			linkbookedlrs.setFont(REPLINK_FONT);
					
			if(clientRights == RIGHTS_22 || clientRights == RIGHTS_25 || clientRights == RIGHTS_3){
				yvalue = yvalue + 25;
				linkbookedlrs.setBounds(75, yvalue, 120, 25);
				linkbookedlrs.addHyperlinkListener(this);
				
			}else{
				linkbookedlrs.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
			}
		}
		
		{
			linkcashoutstanding = new Hyperlink(CvsRpt, SWT.NORMAL);
			linkcashoutstanding.setText(cashoutstanding);
			linkcashoutstanding.setFont(REPLINK_FONT);
				
			if(clientRights == RIGHTS_22 || clientRights == RIGHTS_25 || clientRights == RIGHTS_3){
				yvalue = yvalue + 25;
				linkcashoutstanding.setBounds(75, yvalue, 200, 25);
				linkcashoutstanding.addHyperlinkListener(this);
				
			}else{
				linkcashoutstanding.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
			}
		}
		
		
		{
			linkcancelledlr = new Hyperlink(CvsRpt, SWT.NORMAL);
			linkcancelledlr.setText(cancelledlr);
			linkcancelledlr.setFont(REPLINK_FONT);
			
			if(clientRights != RIGHTS_21 && clientRights != RIGHTS_23 && clientRights != RIGHTS_24 ){
				yvalue = yvalue + 25;
				linkcancelledlr.setBounds(75, yvalue, 150, 25);
				linkcancelledlr.addHyperlinkListener(this);
			}else{
				linkcancelledlr.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
			}
		}
		
		{
			linkccchargesummary = new Hyperlink(CvsRpt, SWT.NORMAL);
			linkccchargesummary.setText(ccchargesummary);
			linkccchargesummary.setFont(REPLINK_FONT);
			
			if(clientRights != RIGHTS_0 && clientRights != RIGHTS_21 && clientRights != RIGHTS_23){
				yvalue = yvalue + 25;
				linkccchargesummary.setBounds(75, yvalue, 150, 25);
				linkccchargesummary.addHyperlinkListener(this);
			}else{
				linkccchargesummary.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
			}
		}
					
		{
			linkcounter = new Hyperlink(CvsRpt, SWT.NORMAL);
			linkcounter.setText(counter);
			linkcounter.setFont(REPLINK_FONT);
				
			if(clientRights != RIGHTS_0 && clientRights != RIGHTS_21 && clientRights != RIGHTS_23 && clientRights != RIGHTS_24){
				yvalue = yvalue + 25;
				linkcounter.setBounds(75, yvalue, 120, 25);
				linkcounter.addHyperlinkListener(this);
			}else{
				linkcounter.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
			}
		}
		
		{
			linkdailydeliverystatus = new Hyperlink(CvsRpt, SWT.NORMAL);
			linkdailydeliverystatus.setText(dailydeliverystatus);
			linkdailydeliverystatus.setFont(REPLINK_FONT);
				
			if(clientRights == RIGHTS_22 || clientRights == RIGHTS_25 || clientRights == RIGHTS_3){
				yvalue = yvalue + 25;
				linkdailydeliverystatus.setBounds(75, yvalue, 150, 25);
				linkdailydeliverystatus.addHyperlinkListener(this);
			}else{
				linkdailydeliverystatus.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
			}
		}
		
		
		{
			linkdailyswactivity = new Hyperlink(CvsRpt, SWT.NORMAL);
			linkdailyswactivity.setText(dailyswactivity);
			linkdailyswactivity.setFont(REPLINK_FONT);
			if(clientRights == RIGHTS_25 || clientRights == RIGHTS_3){
				
				yvalue = yvalue + 25;
				linkdailyswactivity.setBounds(75, yvalue, 250, 25);		
				linkdailyswactivity.addHyperlinkListener(this);
			}else{
				//linkdailydeliverystatus.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
			}
		}
	
		
		{
			linkddcharge = new Hyperlink(CvsRpt, SWT.NORMAL);
			linkddcharge.setText(ddcharge);
			linkddcharge.setFont(REPLINK_FONT);
					
			if(clientRights != RIGHTS_0 && clientRights != RIGHTS_21 && clientRights != RIGHTS_23){
				yvalue = yvalue + 25;
				linkddcharge.setBounds(75, yvalue, 200, 25);
				linkddcharge.addHyperlinkListener(this);
			}else{
				linkddcharge.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
			}
		}
		
		{
			linkdeliveryverification = new Hyperlink(CvsRpt, SWT.NORMAL);
			linkdeliveryverification.setText(deliveryverification);
			linkdeliveryverification.setFont(REPLINK_FONT);
					
			if(clientRights == RIGHTS_22 || clientRights == RIGHTS_25 || clientRights == RIGHTS_3){
				yvalue = yvalue + 25;
				linkdeliveryverification.setBounds(75, yvalue, 150, 25);
				linkdeliveryverification.addHyperlinkListener(this);
			}else{
				linkdeliveryverification.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
			}
		}
	
		
		{
			linkfoclr = new Hyperlink(CvsRpt, SWT.NORMAL);
			linkfoclr.setText(foclr);
			linkfoclr.setFont(REPLINK_FONT);
					
			if(clientRights == RIGHTS_1 || clientRights == RIGHTS_22 || clientRights == RIGHTS_25 || clientRights == RIGHTS_3){
				yvalue = yvalue + 25;
				linkfoclr.setBounds(75, yvalue, 120, 25);
				linkfoclr.addHyperlinkListener(this);
			}else{
				linkfoclr.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
			}
		}
		
		{
			linkinwardregister = new Hyperlink(CvsRpt, SWT.NORMAL);
			linkinwardregister.setText(inwardregister);
			linkinwardregister.setFont(REPLINK_FONT);
					
			if(clientRights != RIGHTS_21 && clientRights != RIGHTS_23 && clientRights != RIGHTS_24){
				yvalue = yvalue + 25;
				linkinwardregister.setBounds(75, yvalue, 120, 25);
				linkinwardregister.addHyperlinkListener(this);
			}else{
				linkinwardregister.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
			}
		}
		
		{
			linklrtrackcount = new Hyperlink(CvsRpt, SWT.NORMAL);
			linklrtrackcount.setText(lrtrackcount);
			linklrtrackcount.setFont(REPLINK_FONT);
					
			if(clientRights == RIGHTS_22 || clientRights == RIGHTS_24 || clientRights == RIGHTS_25 || clientRights == RIGHTS_3){
				yvalue = yvalue + 25;
				linklrtrackcount.setBounds(75, yvalue, 120, 25);
				linklrtrackcount.addHyperlinkListener(this);
			}else{
				linklrtrackcount.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
			}
		}
		
		{
			linkopenlr = new Hyperlink(CvsRpt, SWT.NORMAL);
			linkopenlr.setText(openlr);
			linkopenlr.setFont(REPLINK_FONT);
					
			if(clientRights == RIGHTS_1 || clientRights == RIGHTS_22 || clientRights == RIGHTS_25 || clientRights == RIGHTS_3){
				yvalue = yvalue + 25;
				linkopenlr.setBounds(75, yvalue, 120, 25);
				linkopenlr.addHyperlinkListener(this);
			}else{
				linkopenlr.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
			}
		}
		
		{
			linkspeedydelivery = new Hyperlink(CvsRpt, SWT.NORMAL);
			linkspeedydelivery.setText(speedydelivery);
			linkspeedydelivery.setFont(REPLINK_FONT);
					
			if(clientRights == RIGHTS_22 || clientRights == RIGHTS_25 || clientRights == RIGHTS_3){
				yvalue = yvalue + 25;
				linkspeedydelivery.setBounds(75, yvalue, 120, 25);
				linkspeedydelivery.addHyperlinkListener(this);
			}else{
				linkspeedydelivery.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
			}
		}
		
		{
			linkstock = new Hyperlink(CvsRpt, SWT.NORMAL);
			linkstock.setText(stock);
			linkstock.setFont(REPLINK_FONT);
					
			if(clientRights == RIGHTS_22 || clientRights == RIGHTS_25 || clientRights == RIGHTS_3){
				yvalue = yvalue + 25;
				linkstock.setBounds(75, yvalue, 150, 25);
				linkstock.addHyperlinkListener(this);
			}else{
				linkstock.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
			}
		}
	
		
		{
			linktotalundeliveredtopay = new Hyperlink(CvsRpt, SWT.NORMAL);
			linktotalundeliveredtopay.setText(totalundeliveredtopay);
			linktotalundeliveredtopay.setFont(REPLINK_FONT);
					
			if(clientRights == RIGHTS_1 || clientRights == RIGHTS_22 || clientRights == RIGHTS_25 || clientRights == RIGHTS_3){
				yvalue = yvalue + 25;
				linktotalundeliveredtopay.setBounds(75, yvalue, 200, 25);
				linktotalundeliveredtopay.addHyperlinkListener(this);
			}else{
				linktotalundeliveredtopay.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
			}
		}
		
		{
			linkupdready = new Hyperlink(CvsRpt, SWT.NORMAL);
			linkupdready.setText(updready);
			linkupdready.setFont(REPLINK_FONT);
					
			if(clientRights != RIGHTS_21 && clientRights != RIGHTS_24){
				yvalue = yvalue + 25;
				linkupdready.setBounds(75, yvalue, 120, 25);
				linkupdready.addHyperlinkListener(this);
			}else{
				linkupdready.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
			}
		}
		
		{
			linkusagereport = new Hyperlink(CvsRpt, SWT.NORMAL);
			linkusagereport.setText(usagereport);
			linkusagereport.setFont(REPLINK_FONT);
					
			if(clientRights == RIGHTS_22 || clientRights == RIGHTS_25 || clientRights == RIGHTS_3){
				yvalue = yvalue + 25;
				linkusagereport.setBounds(75, yvalue, 120, 25);
				linkusagereport.addHyperlinkListener(this);
			}else{
				linkusagereport.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
			}
		}
		
		
		{
			linkversionreport = new Hyperlink(CvsRpt, SWT.NORMAL);
			linkversionreport.setText(versionreport);
			linkversionreport.setFont(REPLINK_FONT);
					
			if(clientRights == RIGHTS_1 || clientRights == RIGHTS_22 || clientRights == RIGHTS_25 || clientRights == RIGHTS_3){
				yvalue = yvalue + 25;
				linkversionreport.setBounds(75, yvalue, 120, 25);
				linkversionreport.addHyperlinkListener(this);
			}else{
				linkversionreport.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
			}
		}
		
		
		{
			linkdrsattendance = new Hyperlink(CvsRpt, SWT.NORMAL);
			linkdrsattendance.setText(drsattendance);
			linkdrsattendance.setFont(REPLINK_FONT);
					
			if(clientRights != RIGHTS_0 && clientRights != RIGHTS_22 && clientRights != RIGHTS_23 && clientRights != RIGHTS_24){
				rtyvalue = rtyvalue + 25;
				 if(clientRights == RIGHTS_21){
					 linkdrsattendance.setBounds(75, rtyvalue, 120, 25);
				 }else{
					 linkdrsattendance.setBounds(625, rtyvalue, 120, 25);
				 }
				
				linkdrsattendance.addHyperlinkListener(this);				
			}else{
				linkdrsattendance.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
			}
			
		}
		
		{
			linkremittanceshortage = new Hyperlink(CvsRpt, SWT.NORMAL);
			linkremittanceshortage.setText(remittanceshortage);
			linkremittanceshortage.setFont(REPLINK_FONT);
					
			if(clientRights != RIGHTS_0 && clientRights != RIGHTS_22 && clientRights != RIGHTS_23 && clientRights != RIGHTS_24){
				rtyvalue = rtyvalue + 25;
				if(clientRights == RIGHTS_21){
					linkremittanceshortage.setBounds(75, rtyvalue, 150, 25);
				 }else{
					 linkremittanceshortage.setBounds(625, rtyvalue, 150, 25);
				 }
				linkremittanceshortage.addHyperlinkListener(this);				
			}else{
				linkremittanceshortage.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
			}
		}
		
		{
			linkstaxannexure = new Hyperlink(CvsRpt, SWT.NORMAL);
			linkstaxannexure.setText(staxannexure);
			linkstaxannexure.setFont(REPLINK_FONT);
					
			if(clientRights == RIGHTS_25 || clientRights == RIGHTS_3){
				rtyvalue = rtyvalue + 25;
				 linkstaxannexure.setBounds(625, rtyvalue, 300, 25);
				linkstaxannexure.addHyperlinkListener(this);
			}else if(clientRights == RIGHTS_21){
				rtyvalue = rtyvalue + 25;
				linkstaxannexure.setBounds(75, rtyvalue, 300, 25);
				linkstaxannexure.addHyperlinkListener(this);
			 }
			else{
				linkstaxannexure.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
			}
		}
		
		{
			linkstaxannexurelrwise = new Hyperlink(CvsRpt, SWT.NORMAL);
			linkstaxannexurelrwise.setText(staxannexurelrwise);
			linkstaxannexurelrwise.setFont(REPLINK_FONT);
					
			if(clientRights == RIGHTS_25 || clientRights == RIGHTS_3){
				rtyvalue = rtyvalue + 25;
				linkstaxannexurelrwise.setBounds(625, rtyvalue, 250, 25);
				linkstaxannexurelrwise.addHyperlinkListener(this);
			}else if(clientRights == RIGHTS_21){
				rtyvalue = rtyvalue + 25;
				linkstaxannexurelrwise.setBounds(75, rtyvalue, 250, 25);
				linkstaxannexurelrwise.addHyperlinkListener(this);
			 }else{
				linkstaxannexurelrwise.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
			}
		}
		
		{
			lblOperation = new Label(CvsRpt,SWT.NONE);
			lblOperation.setText("OPERATION");
			
			lblOperation.setFont(REPLABEL_FONT);
			if(clientRights == RIGHTS_3 || clientRights == RIGHTS_25 || clientRights == RIGHTS_20 || clientRights == RIGHTS_1){
				rtyvalue = rtyvalue + 30;
				lblOperation.setBounds(600, rtyvalue, 100, 25);
			}else if(clientRights == RIGHTS_24 || clientRights == RIGHTS_23){
				
				lblOperation.setBounds(600, rtyvalue, 100, 25);
			}
		}
		
		
		{
			linkleftovergoods = new Hyperlink(CvsRpt, SWT.NORMAL);
			linkleftovergoods.setText(leftovergoods);
			linkleftovergoods.setFont(REPLINK_FONT);
					
			if(clientRights != RIGHTS_0 && clientRights != RIGHTS_21 && clientRights != RIGHTS_22){
				rtyvalue = rtyvalue + 25;
				linkleftovergoods.setBounds(625, rtyvalue, 120, 25);
				linkleftovergoods.addHyperlinkListener(this);
			}else{
				linkleftovergoods.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
			}
		}
		
		{
			linkmarketvehicle = new Hyperlink(CvsRpt, SWT.NORMAL);
			linkmarketvehicle.setText(marketvehicle);
			linkmarketvehicle.setFont(REPLINK_FONT);
					
			if(clientRights == RIGHTS_23 || clientRights == RIGHTS_25 || clientRights == RIGHTS_3){
				rtyvalue = rtyvalue + 25;
				linkmarketvehicle.setBounds(625, rtyvalue, 220, 25);
				linkmarketvehicle.addHyperlinkListener(this);
			}else{
				linkmarketvehicle.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
			}			
		}
		
		{
			lblMarketing = new Label(CvsRpt,SWT.NONE);
			lblMarketing.setText("MARKETING");
			lblMarketing.setFont(REPLABEL_FONT);
			if(clientRights == RIGHTS_3 || clientRights == RIGHTS_25 || clientRights == RIGHTS_24 || clientRights == RIGHTS_1){
				rtyvalue = rtyvalue + 30;
				lblMarketing.setBounds(600, rtyvalue, 100, 25);
			}
			
		}
		
		{
			linkbusinessperformanceanalysis = new Hyperlink(CvsRpt, SWT.NORMAL);
			linkbusinessperformanceanalysis.setText(businessperformanceanalysis);
			linkbusinessperformanceanalysis.setFont(REPLINK_FONT);
					
			if(clientRights == RIGHTS_1 || clientRights == RIGHTS_24 || clientRights == RIGHTS_25 || clientRights == RIGHTS_3){
				rtyvalue = rtyvalue + 25;
				linkbusinessperformanceanalysis.setBounds(625, rtyvalue, 240, 25);
				linkbusinessperformanceanalysis.addHyperlinkListener(this);
			}else{
				linkbusinessperformanceanalysis.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
			}	
		}

		{
			linkconsignorbookinganalysis = new Hyperlink(CvsRpt, SWT.NORMAL);
			linkconsignorbookinganalysis.setText(consignorbookinganalysis);
			linkconsignorbookinganalysis.setFont(REPLINK_FONT);
					
			if(clientRights == RIGHTS_1 || clientRights == RIGHTS_24 || clientRights == RIGHTS_25 || clientRights == RIGHTS_3){
				rtyvalue = rtyvalue + 25;
				linkconsignorbookinganalysis.setBounds(625, rtyvalue, 240, 25);
				linkconsignorbookinganalysis.addHyperlinkListener(this);
			}else{
				linkconsignorbookinganalysis.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
			}	
		}


		{
			linkconsigneebookinganalysis = new Hyperlink(CvsRpt, SWT.NORMAL);
			linkconsigneebookinganalysis.setText(consigneebookinganalysis);
			linkconsigneebookinganalysis.setFont(REPLINK_FONT);
					
			if(clientRights == RIGHTS_1 || clientRights == RIGHTS_24 || clientRights == RIGHTS_25 || clientRights == RIGHTS_3){
				rtyvalue = rtyvalue + 25;
				linkconsigneebookinganalysis.setBounds(625, rtyvalue, 240, 25);
				linkconsigneebookinganalysis.addHyperlinkListener(this);
			}else{
				linkconsigneebookinganalysis.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
			}
		}

		
		{
			linkdailybookinganalysis = new Hyperlink(CvsRpt, SWT.NORMAL);
			linkdailybookinganalysis.setText(dailybookinganalysis);
			linkdailybookinganalysis.setFont(REPLINK_FONT);
					
			if(clientRights == RIGHTS_1 || clientRights == RIGHTS_24 || clientRights == RIGHTS_25 || clientRights == RIGHTS_3){
				rtyvalue = rtyvalue + 25;
				linkdailybookinganalysis.setBounds(625, rtyvalue, 200, 25);
				linkdailybookinganalysis.addHyperlinkListener(this);
			}else{
				linkdailybookinganalysis.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
			}
		}

		
		{
			linkinterintrastate = new Hyperlink(CvsRpt, SWT.NORMAL);
			linkinterintrastate.setText(interintrastate);
			linkinterintrastate.setFont(REPLINK_FONT);
					
			if(clientRights == RIGHTS_24 || clientRights == RIGHTS_25 || clientRights == RIGHTS_3){
				rtyvalue = rtyvalue + 25;
				linkinterintrastate.setBounds(625, rtyvalue, 180, 25);
				linkinterintrastate.addHyperlinkListener(this);
			}else{
				linkinterintrastate.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
			}
		}

		{
			linkinwardanalysis = new Hyperlink(CvsRpt, SWT.NORMAL);
			linkinwardanalysis.setText(inwardanalysis);
			linkinwardanalysis.setFont(REPLINK_FONT);
					
			if(clientRights == RIGHTS_1 || clientRights == RIGHTS_24 || clientRights == RIGHTS_25 || clientRights == RIGHTS_3){
				rtyvalue = rtyvalue + 25;
				linkinwardanalysis.setBounds(625, rtyvalue, 120, 25);
				linkinwardanalysis.addHyperlinkListener(this);
			}else{
				linkinwardanalysis.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
			}
		}

		{
			linkmissingcustomers = new Hyperlink(CvsRpt, SWT.NORMAL);
			linkmissingcustomers.setText(missingcustomers);
			linkmissingcustomers.setFont(REPLINK_FONT);
					
			if(clientRights == RIGHTS_24 || clientRights == RIGHTS_25 || clientRights == RIGHTS_3){
				rtyvalue = rtyvalue + 25;
				linkmissingcustomers.setBounds(625, rtyvalue, 180, 25);
				linkmissingcustomers.addHyperlinkListener(this);
			}else{
				linkmissingcustomers.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
			}
		}

		{
			linksundrybookinganalysis = new Hyperlink(CvsRpt, SWT.NORMAL);
			linksundrybookinganalysis.setText(sundrybookinganalysis);
			linksundrybookinganalysis.setFont(REPLINK_FONT);
					
			if(clientRights == RIGHTS_1 || clientRights == RIGHTS_24 || clientRights == RIGHTS_25 || clientRights == RIGHTS_3){
				rtyvalue = rtyvalue + 25;
				linksundrybookinganalysis.setBounds(625,rtyvalue, 180, 25);
				linksundrybookinganalysis.addHyperlinkListener(this);
			}else{
				linksundrybookinganalysis.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_GRAY));
			}
		}	
		
		
		return this;
		
	}
	
	
	
	@Override
	public void linkActivated(HyperlinkEvent event) {
		// TODO Auto-generated method stub
		Object source = event.getSource();
		
		if(source == linkbookedlrs){
			
			CvsRpt.setVisible(false);
			if (null != cptReport)
				cptReport.dispose();
	
			//lblHead.setText("Regular Sundry");			
			try {
				
				//AdminComposite.display("STATUS",STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				cptReport =	new BookedLrs(this,SWT.BORDER).loadcomposite();
				cptReport.setVisible(true);
				
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}else if(source == linkcashoutstanding){
			
			CvsRpt.setVisible(false);
			if (null != cptReport)
				cptReport.dispose();
	
			//lblHead.setText("Regular Sundry");			
			try {
				
				//AdminComposite.display("STATUS",STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				cptReport =	new CashOutstanding(this,SWT.BORDER).loadcomposite();
				cptReport.setVisible(true);
				
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}else if(source == linkcancelledlr){
			
			CvsRpt.setVisible(false);
			if (null != cptReport)
				cptReport.dispose();
	
			//lblHead.setText("Regular Sundry");			
			try {
				
				//AdminComposite.display("STATUS",STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				cptReport =	new CancelledLr(this,SWT.BORDER,clientRights).loadcomposite();
				cptReport.setVisible(true);
				
			} catch (Exception exception) {
				exception.printStackTrace();
			}
			
			/*Composite composite;
			try {
				//composite = manager.addMonitorReportsContainer(shell);
				System.out.println("shell"+shell);
				manager = new ContainerManager();
				composite = manager.CancelledLrContainer(shell);
				System.out.println("composite"+composite);
				composite.setBounds(10, 100, 1020, 700);
				composite.setLayoutData(layoutAlign);
				this.layout();
			} catch (Exception e1) {
				e1.printStackTrace();
			}*/
			
		}else if(source == linkccchargesummary){
			
			CvsRpt.setVisible(false);
			if (null != cptReport)
				cptReport.dispose();
	
			//lblHead.setText("Regular Sundry");			
			try {
				
				//AdminComposite.display("STATUS",STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				cptReport =	new CCChargeSummary(this,SWT.BORDER,clientRights).loadcomposite();
				cptReport.setVisible(true);
				
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}else if(source == linkcounter){
			
			CvsRpt.setVisible(false);
			if (null != cptReport)
				cptReport.dispose();
	
			//lblHead.setText("Regular Sundry");			
			try {
				
				//AdminComposite.display("STATUS",STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				cptReport =	new CounterReport(this,SWT.BORDER,clientRights).loadcomposite();
				cptReport.setVisible(true);
				
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		
			
		}else if(source == linkdailydeliverystatus){
			
			CvsRpt.setVisible(false);
			if (null != cptReport)
				cptReport.dispose();
	
			//lblHead.setText("Regular Sundry");			
			try {
				
				//AdminComposite.display("STATUS",STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				cptReport =	new DailyDeliveryStatus(this,SWT.BORDER).loadcomposite();
				cptReport.setVisible(true);
				
			} catch (Exception exception) {
				exception.printStackTrace();
			}
			
		}else if(source == linkddcharge){
			
			CvsRpt.setVisible(false);
			if (null != cptReport)
				cptReport.dispose();
	
			//lblHead.setText("Regular Sundry");			
			try {
				
				//AdminComposite.display("STATUS",STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				cptReport =	new DDcharge(this,SWT.BORDER,clientRights).loadcomposite();
				cptReport.setVisible(true);
				
			} catch (Exception exception) {
				exception.printStackTrace();
			}
			
		}else if(source == linkdeliveryverification){
			CvsRpt.setVisible(false);
			if (null != cptReport)
				cptReport.dispose();
	
			//lblHead.setText("Regular Sundry");			
			try {
				
				//AdminComposite.display("STATUS",STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				cptReport =	new Deliveryverification(this,SWT.BORDER).loadcomposite();
				cptReport.setVisible(true);
				
			} catch (Exception exception) {
				exception.printStackTrace();
			}
			
		}else if(source == linkfoclr){
			CvsRpt.setVisible(false);
			if (null != cptReport)
				cptReport.dispose();
	
			//lblHead.setText("Regular Sundry");			
			try {
				
				//AdminComposite.display("STATUS",STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				cptReport =	new FOCLr(this,SWT.BORDER,clientRights).loadcomposite();
				cptReport.setVisible(true);
				
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}else if(source == linkinwardregister){
			CvsRpt.setVisible(false);
			if (null != cptReport)
				cptReport.dispose();
	
			//lblHead.setText("Regular Sundry");			
			try {
				
				//AdminComposite.display("STATUS",STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				cptReport =	new InwardREgister(this,SWT.BORDER).loadcomposite();
				cptReport.setVisible(true);
				
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}else if(source == linklrtrackcount){
			CvsRpt.setVisible(false);
			if (null != cptReport)
				cptReport.dispose();
	
			//lblHead.setText("Regular Sundry");			
			try {
				
				//AdminComposite.display("STATUS",STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				cptReport =	new LRTrackCount(this,SWT.BORDER).loadcomposite();
				cptReport.setVisible(true);
				
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}else if(source == linkopenlr){
			CvsRpt.setVisible(false);
			if (null != cptReport)
				cptReport.dispose();
	
			//lblHead.setText("Regular Sundry");			
			try {
				
				//AdminComposite.display("STATUS",STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				cptReport =	new OpenLr(this,SWT.BORDER,clientRights).loadcomposite();
				cptReport.setVisible(true);
				
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}else if(source == linkspeedydelivery){
			CvsRpt.setVisible(false);
			if (null != cptReport)
				cptReport.dispose();
	
			//lblHead.setText("Regular Sundry");			
			try {
			
				//AdminComposite.display("STATUS",STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				cptReport =	new SpeedyDelivery(this,SWT.BORDER).loadcomposite();
				cptReport.setVisible(true);
				
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}else if(source == linkstock){
			CvsRpt.setVisible(false);
			if (null != cptReport)
				cptReport.dispose();
	
			//lblHead.setText("Regular Sundry");			
			try {
				
				//AdminComposite.display("STATUS",STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				cptReport =	new Stock(this,SWT.BORDER).loadcomposite();
				cptReport.setVisible(true);
				
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}else if(source == linktotalundeliveredtopay){
			CvsRpt.setVisible(false);
			if (null != cptReport)
				cptReport.dispose();
	
			//lblHead.setText("Regular Sundry");			
			try {
			
				//AdminComposite.display("STATUS",STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				cptReport =	new TotalUdTopay(this,SWT.BORDER,clientRights).loadcomposite();
				cptReport.setVisible(true);
				
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}else if(source == linkupdready){
			CvsRpt.setVisible(false);
			if (null != cptReport)
				cptReport.dispose();
	
			//lblHead.setText("Regular Sundry");			
			try {
				
				//AdminComposite.display("STATUS",STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				cptReport =	new UPDReady(this,SWT.BORDER,clientRights).loadcomposite();
				cptReport.setVisible(true);
				
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}else if(source == linkusagereport){
			CvsRpt.setVisible(false);
			if (null != cptReport)
				cptReport.dispose();
	
			//lblHead.setText("Regular Sundry");			
			try {
				
				//AdminComposite.display("STATUS",STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				cptReport =	new UsageReport(this,SWT.BORDER).loadcomposite();
				cptReport.setVisible(true);
				
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}else if(source == linkversionreport){
			CvsRpt.setVisible(false);
			if (null != cptReport)
				cptReport.dispose();
	
			//lblHead.setText("Regular Sundry");			
			try {
				
				//AdminComposite.display("STATUS",STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				cptReport =	new VersionReport(this,SWT.BORDER).loadcomposite();
				cptReport.setVisible(true);
				
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}else if(source == linkdrsattendance){
			CvsRpt.setVisible(false);
			if (null != cptReport)
				cptReport.dispose();
	
			//lblHead.setText("Regular Sundry");			
			try {
				
				//AdminComposite.display("STATUS",STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				cptReport =	new DRSAttendence(this,SWT.BORDER,clientRights).loadcomposite();
				cptReport.setVisible(true);
				
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}else if(source == linkremittanceshortage){
			CvsRpt.setVisible(false);
			if (null != cptReport)
				cptReport.dispose();
	
			//lblHead.setText("Regular Sundry");			
			try {
				
				//AdminComposite.display("STATUS",STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				cptReport =	new RemittanceShortage(this,SWT.BORDER,clientRights).loadcomposite();
				cptReport.setVisible(true);
				
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}else if(source == linkstaxannexure){
			CvsRpt.setVisible(false);
			if (null != cptReport)
				cptReport.dispose();
	
			//lblHead.setText("Regular Sundry");			
			try {

				//AdminComposite.display("STATUS",STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				cptReport =	new STaxConsolidated(this,SWT.BORDER).loadcomposite();
				cptReport.setVisible(true);
				
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}else if(source == linkstaxannexurelrwise){
			CvsRpt.setVisible(false);
			if (null != cptReport)
				cptReport.dispose();
	
			//lblHead.setText("Regular Sundry");			
			try {
			
				//AdminComposite.display("STATUS",STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				cptReport =	new STaxLR(this,SWT.BORDER).loadcomposite();
				cptReport.setVisible(true);
				
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}else if(source == linkleftovergoods){
			CvsRpt.setVisible(false);
			if (null != cptReport)
				cptReport.dispose();
	
			//lblHead.setText("Regular Sundry");			
			try {
				
				//AdminComposite.display("STATUS",STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				cptReport =	new LeftOverGoods(this,SWT.BORDER,clientRights).loadcomposite();
				cptReport.setVisible(true);
				
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}else if(source == linkmarketvehicle){
			CvsRpt.setVisible(false);
			if (null != cptReport)
				cptReport.dispose();
	
			//lblHead.setText("Regular Sundry");			
			try {
			
				//AdminComposite.display("STATUS",STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				cptReport =	new MarketVehicle(this,SWT.BORDER).loadcomposite();
				cptReport.setVisible(true);
				
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}else if(source == linkbusinessperformanceanalysis){
			CvsRpt.setVisible(false);
			if (null != cptReport)
				cptReport.dispose();
	
			//lblHead.setText("Regular Sundry");			
			try {
				
				//AdminComposite.display("STATUS",STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				cptReport =	new BPA(this,SWT.BORDER,clientRights).loadcomposite();
				cptReport.setVisible(true);
				
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}else if(source == linkconsignorbookinganalysis){
			CvsRpt.setVisible(false);
			if (null != cptReport)
				cptReport.dispose();
	
			//lblHead.setText("Regular Sundry");			
			try {
				
				//AdminComposite.display("STATUS",STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				cptReport =	new ConsignorBooking(this,SWT.BORDER,clientRights).loadcomposite();
				cptReport.setVisible(true);
				
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}else if(source == linkconsigneebookinganalysis){
			CvsRpt.setVisible(false);
			if (null != cptReport)
				cptReport.dispose();
	
			//lblHead.setText("Regular Sundry");			
			try {
				
				//AdminComposite.display("STATUS",STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				cptReport =	new ConsigneeBooking(this,SWT.BORDER,clientRights).loadcomposite();
				cptReport.setVisible(true);
				
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}else if(source == linkdailybookinganalysis){
			CvsRpt.setVisible(false);
			if (null != cptReport)
				cptReport.dispose();
	
			//lblHead.setText("Regular Sundry");			
			try {
				
				//AdminComposite.display("STATUS",STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				cptReport =	new DailyBooking(this,SWT.BORDER,clientRights).loadcomposite();
				cptReport.setVisible(true);
				
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}else if(source == linkinterintrastate){
			CvsRpt.setVisible(false);
			if (null != cptReport)
				cptReport.dispose();
	
			//lblHead.setText("Regular Sundry");			
			try {
				
				//AdminComposite.display("STATUS",STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				cptReport =	new Inter_Intra(this,SWT.BORDER).loadcomposite();
				cptReport.setVisible(true);
				
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}else if(source == linkinwardanalysis){
			CvsRpt.setVisible(false);
			if (null != cptReport)
				cptReport.dispose();
	
			//lblHead.setText("Regular Sundry");			
			try {
				
				//AdminComposite.display("STATUS",STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				cptReport =	new InwardAnalysisComposite(this,SWT.BORDER,clientRights).loadcomposite();
				cptReport.setVisible(true);
				
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}else if(source == linkmissingcustomers){
			CvsRpt.setVisible(false);
			if (null != cptReport)
				cptReport.dispose();
	
			//lblHead.setText("Regular Sundry");			
			try {
				
				//AdminComposite.display("STATUS",STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				cptReport =	new MissingCustomer(this,SWT.BORDER).loadcomposite();
				cptReport.setVisible(true);
				
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}else if(source == linksundrybookinganalysis) {
			CvsRpt.setVisible(false);
			if (null != cptReport)
			cptReport.dispose();

			//lblHead.setText("Regular Sundry");	
			
			try {
			
					//AdminComposite.display("STATUS",STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					
					cptReport =	new SundryBooking(this,SWT.BORDER,clientRights).loadcomposite();
					cptReport.setVisible(true);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
		else if(source == linkdailyswactivity) {
			CvsRpt.setVisible(false);
			if (null != cptReport)
			cptReport.dispose();

			//lblHead.setText("Regular Sundry");	
			
			try {
			
					//AdminComposite.display("STATUS",STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					
					cptReport =	new DailySWActivity(this,SWT.BORDER).loadcomposite();
					cptReport.setVisible(true);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
		
		
		
		
		
	}
	@Override
	public void linkEntered(HyperlinkEvent event) {
		// TODO Auto-generated method stub
		Hyperlink link = (Hyperlink) event.getSource();
		link.setForeground(REPLINK_FOCUS_COLOR);
		//link.setSize(130,25);
		link.setFont(REPLINK_FOCUS_FONT);
		
	}
	@Override
	public void linkExited(HyperlinkEvent event) {
		// TODO Auto-generated method stub
		Hyperlink link = (Hyperlink) event.getSource();
		link.setForeground(REPLINK_COLOR);		
		link.setFont(REPLINK_FONT);
	}
	@Override
	public void paintControl(PaintEvent e) {
		// TODO Auto-generated method stub
		
			e.gc.drawLine(450, 50,450 , 750);
		
		
	}
	
	
	/*public static void display(String status,Font font,Color color)
	{
		lblStatusBar.setText(status);
		lblStatusBar.setFont(font);
		lblStatusBar.setForeground(REPLINK_FOCUS_COLOR);
	}	*/

	
	

}
