package hm.akr.workspace;

import hm.akr.common.BeanUtil;
import hm.akr.common.SWTResourceManager;
import hm.akr.common.SecondAKR;
import hm.akr.container.ContainerManager;
import hm.akr.dto.StationsDTO;
import hm.akr.exceptions.BusinessException;
import hm.akr.interfaces.Security;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.naming.NamingException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

/**
 * 
 * @version 1.0
 */
public class MainForm {

	Display display = null;

	private ToolItem toolLr;

	ToolItem toolItem1;

	private ToolItem toolGmr;

	private ToolItem toolDrs;

	private ToolItem toolCr;

	// private ToolItem toolCustomer;

	private ToolItem toolAdmin;

	private ToolItem toolMonitorReport;

	private ToolItem toolReports;

	private ToolItem toolMessages;

	private ToolItem toolScreen;

	private ToolItem toolHistory;
	
	private ToolBar tools;

	private Canvas canvas1;

	BeanUtil beanutil = null;

	Shell shell = null;

	Composite screen = null;

	ContainerManager manager = null;

	private String stationCode;

	private String stationName;

	public static String VERSION_NO = "3.03";

	private String isUpdate = null;

	private String date;

	private String SERVER_DATE;

	private String SERVER_DATE_TIME;

	private String VALIDATION_FAILED = "Error Code 20";

	//private ToolItem toolAbout;

	private static String PROPERTY_FILE_NAME = "lib/version.properties";

	Properties pro = null;

	private String tempversion;

	private ToolItem toolCommision;

	//private ToolItem toolInwardAnalysis;

	/**
	 * Constructor method
	 * 
	 * @throws Exception
	 * @throws NamingException
	 */
	public MainForm(final String macId) throws NamingException, Exception {
		if (display != null)
			display = new Display();
		else
			display = Display.getDefault();

		beanutil = BeanUtil.getInstance();

		manager = new ContainerManager();

		if (null != beanutil) {
			String currYear = BeanUtil.getDbYear();
			if(currYear.equals("")){
				currYear = "2011-12";
			}
			shell = new Shell(display);
			shell.setText("AKR Parcel Service" + " | "
					+ beanutil.getActingStationName() + " | "
					+ beanutil.getActingStationCode() + " | "
					+ currYear+
					"                                  "+
					"                                  "+
					"                                  "+
					"        "+
					 "Version - "+VERSION_NO);
			{
				SWTResourceManager.registerResourceUser(shell);
				loadApplication(macId);
			}

		}

	}

	/**
	 * Method to load the initial parameters and the data
	 * 
	 * @throws Exception
	 */
	private void loadApplication(final String macId) throws Exception {
		final Image image = new Image(display, 500, 500);
		GC gc = new GC(image);
		gc.setBackground(display.getSystemColor(SWT.COLOR_CYAN));
		gc.fillRectangle(image.getBounds());
		gc.drawText("Splash Screen", 10, 10);
		gc.dispose();
		final Shell splash = new Shell(SWT.BACKGROUND);
		final ProgressBar bar = new ProgressBar(splash, SWT.NONE);
		bar.setMinimum(10);
		bar.setMaximum(50);

		Label label = new Label(splash, SWT.NONE);
		label.setBounds(300, 50, 100, 540);
		label.setImage(SWTResourceManager
				.getImage("hm/akr/resources/SplashScreen.png"));

		label.setBackground(display.getSystemColor(SWT.TRANSPARENCY_ALPHA));

		FormLayout layout = new FormLayout();
		splash.setLayout(layout);

		FormData labelData = new FormData();
		labelData.right = new FormAttachment(100, 0);
		labelData.bottom = new FormAttachment(100, 0);
		label.setLayoutData(labelData);
		/* Setting the progress bar layout */
		FormData progressData = new FormData();
		progressData.left = new FormAttachment(0, 5);
		progressData.right = new FormAttachment(100, -5);
		progressData.bottom = new FormAttachment(100, -5);
		bar.setLayoutData(progressData);
		splash.pack();

		/* To have the splash screen in the center of the screen */
		Rectangle splashRect = splash.getBounds();
		Rectangle displayRect = display.getBounds();
		int x = (displayRect.width - splashRect.width) / 2;
		int y = (displayRect.height - splashRect.height) / 2;
		splash.setLocation(x, y);
		splash.open();

		bar.setSelection(20);
		/*
		 * if (validate(macId)) { bar.setSelection(40);
		 * 
		 * beanutil.getAvailableStations(); beanutil.getLRRange(); stationCode =
		 * beanutil.getActingStationCode(); stationName =
		 * beanutil.getActingStationName(); // beanutil.getMessage(stationCode);
		 * 
		 * image.dispose(); splash.close();
		 * 
		 * shell.open(); }
		 */
		if (validate(macId)) {
			System.out.println("Validation Successful");

			/*
			 * String javaPath = System.getProperty("java.home");
			 * 
			 * File file = new File(javaPath + "/temp.txt"); if (file.exists()) {
			 * beanutil.setSecondThread(true); } else { file.createNewFile();
			 * beanutil.setSecondThread(false); }
			 */
			SecondAKR second = new SecondAKR();
			int count = second.getSecondAKR("javaw.exe");
			if (count > 1) {
				beanutil.setSecondThread(true);
			} else {
				count = second.getSecondAKR("java.exe");
				if (count > 1) {
					beanutil.setSecondThread(true);
				} else {
					beanutil.setSecondThread(false);
				}
				beanutil.setSecondThread(false);
			}

			bar.setSelection(40);
			System.out.println("Getting All Available Stations");
			beanutil.getAvailableStations();
			System.out.println("Getting LR Range Values");
			beanutil.getLRRange(false);
			System.out.println("Lr Range Successful");
			stationCode = beanutil.getActingStationCode();
			stationName = beanutil.getActingStationName();
			// beanutil.getMessage(stationCode);

			System.out.println("Getting All Quotation Customers");
			beanutil.getCustomers(stationCode, false);
			System.out.println("Getting All Quotation Customers Successfull");

			System.out.println("Getting Sundry Customer details");
			beanutil.populateCustomersFromRemote(false);
			System.out.println("Getting Sundry Customer details Successfull");

			System.out.println("Getting Sundry details");
			beanutil.getSundryDetails(stationCode, false);
			System.out.println("Got  Sundry details");
			System.out.println("Getting Distance details");
			beanutil.getDistanceList(stationCode, false);
			System.out.println("Got  Distance List");

			System.out.println("Getting Measurements details");
			beanutil.getMeasurements();
			System.out.println("Got  Measurements");

			System.out.println("Getting Profile details");
			beanutil.getCardRateProfile();
			System.out.println("Got  Profile");

			System.out.println("Getting CC details");
			beanutil.getCCDetails();
			System.out.println("Got  CC");

			image.dispose();
			splash.close();

			shell.open();

		} else {

			image.dispose();
			splash.close();
			BusinessException business = new BusinessException();
			business.setResponseMessage(VALIDATION_FAILED);
			business.printStackTrace();
			throw business;
		}

	}

	/* *************************************************************************************************** */

	/**
	 * Method to validate the Machine
	 */
	private boolean validate(String macId) throws Exception {

		stationCode = beanutil.getActingStationCode();

		Security sr = beanutil.getSecurityBean();
		if (sr == null) {
			BeanUtil.isSecondServer = 1;
			sr = beanutil.getSecurityBean();
		}

		boolean isAdmin = false;

		boolean isNew = false;
		boolean isAdminHead = false;
		FileInputStream stream = null;
		stream = new FileInputStream(PROPERTY_FILE_NAME);
		if (null != stream) {
			isNew = true;
			pro = new Properties();
			pro.load(stream);
			VERSION_NO = pro.getProperty("VersionId");
			isUpdate = pro.getProperty("isUpdate");
			if (null != isUpdate)
				tempversion = VERSION_NO + "|" + isUpdate;
			else
				tempversion = VERSION_NO;
		}

		if (null != sr) {
			boolean isValid = false;
			float clientRights = -1;

			System.out.println("Passing macID"+macId);
			if (!isNew)
				clientRights = sr.getClientDetails(macId, stationCode,
						VERSION_NO);
			else {
				clientRights = sr.getClientDetails(macId, stationCode,
						tempversion);
			}

			if (clientRights >= 0 && clientRights != 2 ) {
				isValid = true;
			}
			beanutil.setValid(isValid);
			if (isValid && null != isUpdate
					&& isUpdate.equalsIgnoreCase("true")) {
				pro.setProperty("isUpdate", "false");
				pro.store(new FileOutputStream(PROPERTY_FILE_NAME), null);
				System.out.println(isUpdate);
			}

			date = sr.getServerDate();
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date currentDate = dateFormat.parse(date);
			SERVER_DATE = dateFormat.format(currentDate);
			beanutil.setServerDate(SERVER_DATE);

			/*
			 * DateFormat timeFormat = new SimpleDateFormat("dd-MM-yyyy
			 * HH:mm:ss"); java.util.Date dateTime = timeFormat.parse(date);
			 * SERVER_DATE_TIME = timeFormat.format(dateTime);
			 * beanutil.setServerDateTime(SERVER_DATE_TIME);
			 */

			System.out.println("client rights" + clientRights);
			beanutil.setClientRights(clientRights);
			if ((int)clientRights == 1) {
				beanutil.setAdmin(true);
			}

			if ((int)clientRights == 2) {
				beanutil.setAdmin(true);
				beanutil.setAdminHead(true);
			}

			if ((int)clientRights == 3) {

				beanutil.setAdmin(true);
				beanutil.setAdminHead(true);
				beanutil.setAdminHeadStationary(true);
			}

			return isValid;
		}
		return false;
	}

	/* ********************************************************************* */

	/**
	 * 
	 */

	/**
	 * Method to load the GUI
	 * 
	 * @throws Exception
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void LoadForm() throws Exception {
		// shell.setMaximized(true);
		shell.setBounds(0, 0, 1024, 768);
		final CoolBar bar = new CoolBar(shell, SWT.BORDER);
		bar.setBounds(0, 0, 385, 28);
		bar.setSize(1024, 24);

		CoolItem item1 = new CoolItem(bar, SWT.NONE);
		{
			tools = new ToolBar(bar, SWT.NONE);
			{

				toolLr = new ToolItem(tools, SWT.NONE);
				toolLr.setText("LR");
				ToolItem toolSep = new ToolItem(tools, SWT.NONE);
				toolSep.setText("|");
				toolGmr = new ToolItem(tools, SWT.NONE);
				toolGmr.setText("GMR");
				toolSep = new ToolItem(tools, SWT.NONE);
				toolSep.setText("|");
				toolCr = new ToolItem(tools, SWT.NONE);
				toolCr.setText("CR");
				toolSep = new ToolItem(tools, SWT.NONE);
				toolSep.setText("|");
				toolDrs = new ToolItem(tools, SWT.NONE);
				toolDrs.setText("DRS");
				toolSep = new ToolItem(tools, SWT.NONE);
				toolSep.setText("|");
				toolReports = new ToolItem(tools, SWT.NONE);
				toolReports.setText("REPORTS");

				/*
				 * toolCommision = new ToolItem(tools, SWT.NONE);
				 * toolCommision.setText("COMMISSION");
				 */

				/*
				 * toolSep = new ToolItem(tools, SWT.NONE);
				 * toolSep.setText("|");
				 * 
				 * toolCommision = new ToolItem(tools, SWT.NONE);
				 * toolCommision.setText("COMMISSION");
				 * 
				 * toolCommision.addListener(SWT.Selection, new Listener() {
				 * 
				 * public void handleEvent(Event e) {
				 * 
				 * try { StationsDTO[] stations = beanutil
				 * .getAvailableStations(); String stationCode = beanutil
				 * .getActingStationCode();
				 * 
				 * for (int i = 0; i < stations.length; i++) { if
				 * (stationCode.equalsIgnoreCase(stations[i] .getId())) { if
				 * (!(stations[i].getOwner() .equalsIgnoreCase("akr"))) {
				 * canvas1.setVisible(false); Composite composite = manager
				 * .addCommissionContainer(shell);
				 * 
				 * composite.setBounds(10, 10, 1020, 700); break; } } } } catch
				 * (Exception e1) { // TODO Auto-generated catch block
				 * e1.printStackTrace(); } }
				 * 
				 * });
				 */

				StationsDTO[] stations = beanutil.getAvailableStations();
				String stationCode = beanutil.getActingStationCode();

				for (int i = 0; i < stations.length; i++) {
					if (stationCode.equalsIgnoreCase(stations[i].getId())) {

						if(beanutil.getClientRights() != new Float(1) 
						&& beanutil.getClientRights() != new Float(2.2) 
						&& beanutil.getClientRights() != new Float(2.3) 
					    && beanutil.getClientRights() != new Float(2.4))	{
						toolSep = new ToolItem(tools, SWT.NONE);
						toolSep.setText("|");

						toolCommision = new ToolItem(tools, SWT.NONE);
						toolCommision.setText("COMMISSION");

						toolCommision.addListener(SWT.Selection,
								new Listener() {

									public void handleEvent(Event e) {

										canvas1.setVisible(false);
										Composite composite = manager
												.addCommissionContainer(shell);

										composite.setBounds(10, 10, 1020, 700);

									}
								});
						
						}

						

					}
				}

				if (beanutil.isAdmin()) {

					toolItem1 = new ToolItem(tools, SWT.NONE);
					toolItem1.setText("|");
					toolAdmin = new ToolItem(tools, SWT.NONE);
					toolAdmin.setText("ADMIN");
				}
				
				{
						toolItem1 = new ToolItem(tools, SWT.NONE);
						toolItem1.setText("|");
						toolMonitorReport = new ToolItem(tools, SWT.NONE);
						toolMonitorReport.setText("MONITORING REPORTS");
				}

				{
					toolItem1 = new ToolItem(tools, SWT.NONE);
					toolItem1.setText("|");
					toolMessages = new ToolItem(tools, SWT.NONE);
					toolMessages.setText("MESSAGES");

					Composite composite;

					composite = manager.addMsgContainer(shell);
					composite.setBounds(10, 10, 1020, 700);
				}

				{
					/*toolItem1 = new ToolItem(tools, SWT.NONE);
					toolItem1.setText("|");
					toolInwardAnalysis = new ToolItem(tools, SWT.NONE);
					toolInwardAnalysis.setText("INWARD ANALYSIS");

					Composite composite;

					composite = manager.addMsgContainer(shell);
					composite.setBounds(10, 10, 1020, 700);*/
				}

				{
					toolItem1 = new ToolItem(tools, SWT.NONE);
					toolItem1.setText("|");
					toolHistory = new ToolItem(tools, SWT.NONE);
					toolHistory.setText("HISTORY");
				}
				{
					toolItem1 = new ToolItem(tools, SWT.NONE);
					toolItem1.setText("|");
				}
				/*if (beanutil.isAdmin()) {
					toolItem1 = new ToolItem(tools, SWT.NONE);
					toolItem1.setText("|");
					toolAbout = new ToolItem(tools, SWT.NONE);
					toolAbout.setText("ABOUT");
					if (beanutil.isAdminHead()) {
						{
							toolItem1 = new ToolItem(tools, SWT.NONE);
							toolItem1
									.setText("                                                                ");
						}
					} else {
						toolItem1 = new ToolItem(tools, SWT.NONE);
						toolItem1
								.setText("                                                                                                               ");
					}
				} else {			
					
					toolItem1 = new ToolItem(tools, SWT.NONE);
					toolItem1.setText("|");
					toolAbout = new ToolItem(tools, SWT.NONE);
					toolAbout.setText("ABOUT");
					{
						toolItem1 = new ToolItem(tools, SWT.NONE);
						toolItem1
								.setText("                                                                                                                                  ");
					}
				}*/

				{
					toolScreen = new ToolItem(tools, SWT.NONE);
					toolScreen.setText("CAPTURE SCREEN");
				}

				tools.pack();
			}

			Point size = tools.getSize();
			item1.setControl(tools);
			item1.setSize(item1.computeSize(size.x, size.y));
		}

		toolLr.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event e) {
				try {
					// if (!beanutil.isBranch()) {
					canvas1.setVisible(false);
					Composite composite = manager.addLRContainer(shell);

					composite.setBounds(10, 10, 1020, 700);
					// }

				} catch (Exception exception) {
					exception.printStackTrace();
					shell.setText(exception.getMessage());
				}
			}
		});

		
			toolGmr.addListener(SWT.Selection, new Listener() {

				public void handleEvent(Event e) {

					canvas1.setVisible(false);
					Composite composite = manager.addGMRContainer(shell);

					composite.setBounds(10, 10, 1020, 700);

				}
			});
		
	
		toolDrs.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event e) {

				canvas1.setVisible(false);
				Composite composite = manager.addDRSContainer(shell);

				composite.setBounds(10, 10, 1020, 700);

			}
		});

		/*
		 * toolCustomer.addListener(SWT.Selection, new Listener() {
		 * 
		 * public void handleEvent(Event e) {
		 * 
		 * canvas1.setVisible(false); Composite composite =
		 * manager.addCustomerContainer(shell);
		 * 
		 * composite.setBounds(10, 10, 1020, 700); } });
		 */

		toolCr.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event e) {

				canvas1.setVisible(false);
				Composite composite = manager.addCRContainer(shell);

				composite.setBounds(10, 10, 1020, 700);

			}
		});
		
		/*toolAbout.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event e) {

				canvas1.setVisible(false);
				Composite composite = null;
				try {
					composite = manager.addSoftwareContainer(shell, VERSION_NO);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				composite.setBounds(10, 10, 1020, 700);

			}
		});
*/
		toolReports.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event e) {

				canvas1.setVisible(false);
				Composite composite = manager.addReportsContainer(shell);

				composite.setBounds(10, 10, 1020, 700);

			}
		});

		toolMessages.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event e) {

				canvas1.setVisible(false);
				Composite composite;
				try {
					composite = manager.addMsgContainer(shell);
					composite.setBounds(10, 10, 1020, 700);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		/*toolInwardAnalysis.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event e) {

				canvas1.setVisible(false);
				Composite composite;
				try {
					composite = manager.addInwardContainer(shell);
					composite.setBounds(10, 10, 1020, 700);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});*/
		
		toolHistory.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event e) {

				canvas1.setVisible(false);
				Composite composite;
				try {
					composite = manager.addHistoryContainer(shell);
					composite.setBounds(10, 10, 1020, 700);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		if (beanutil.isAdmin())// validDTO.isAdmin()
		{
			toolAdmin.addListener(SWT.Selection, new Listener() {

				public void handleEvent(Event e) {

					canvas1.setVisible(false);
					Composite composite = null;

					try {
						composite = manager.addAdminContainer(shell);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					composite.setBounds(10, 10, 1020, 700);

				}
			});
		}

		//if (beanutil.isAdminHead()) // validDTO.isAdminHead()
			{

				toolMonitorReport.addListener(SWT.Selection, new Listener() {

					public void handleEvent(Event e) {

						canvas1.setVisible(false);
						Composite composite;
						try {
							//composite = manager.addMonitorReportsContainer(shell);
							composite = manager.ReprtsMenuContainer(shell);
							composite.setBounds(10, 10, 1020, 700);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});
			}

		{
			canvas1 = new Canvas(shell, SWT.BORDER);

		}

		{
			toolScreen.addListener(SWT.Selection, new Listener() {
				public void handleEvent(Event event) {
					GC gc = new GC(shell.getDisplay());
					final Image image = new Image(shell.getDisplay(), 1024, 752);
					gc.copyArea(image, 0, 0);
					gc.dispose();
					ImageData ideaImageData = image.getImageData();
					ImageLoader imageLoader = new ImageLoader();
					imageLoader.data = new ImageData[] { ideaImageData };

					imageLoader.save("lib/ScreenShot.jpg", SWT.IMAGE_JPEG);
					String curDir = System.getProperty("user.dir");
					String dirLoc = curDir + "/lib/ScreenShot.jpg";
					File f = new File(dirLoc);
					if (f.exists()) {
						AkrEmail akremail = new AkrEmail();
						AkrEmail.BODY = "ScreenShot";
						AkrEmail.SUBJECT = "AKR ParcelService | " + stationName
								+ " | " + stationCode;
						akremail.sendMail("akr.arun@gmail.com",
								"ScreenShot.jpg", dirLoc);
					} else {
						System.out.println("File not Exists");
					}
				}
			});

		}

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();

		}
		/*
		 * if (!beanutil.isSecondThread()) { String javaPath =
		 * System.getProperty("java.home");
		 * 
		 * File file = new File(javaPath + "/temp.txt"); if (file.exists()) {
		 * file.delete(); } }
		 */
		display.dispose();
	}

}