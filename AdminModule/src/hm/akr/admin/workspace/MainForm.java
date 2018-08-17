package hm.akr.admin.workspace;

import hm.akr.common.BeanUtil;
import hm.akr.exceptions.BusinessException;
import hm.akr.interfaces.Security;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;

public class MainForm {
	private Display display;
	private Shell shell;
	private Composite cptAdmin;
	BeanUtil beanutil = null;
	private String VALIDATION_FAILED = "Error Code 20";
	public String VERSION_NO = "2.35";
	private String SERVER_DATE;

	/**
	 * @throws Exception
	 * 
	 */
	public MainForm(final String macId) throws Exception {	

		if (display != null)
			display = new Display();
		else
			display = Display.getDefault();

		beanutil = BeanUtil.getInstance();

		if (null != beanutil) {
			shell = new Shell(display);
			/*shell.setText("Admin" + " | "
					+ beanutil.getActingStationName() + " | "
					+ beanutil.getActingStationCode());*/
			
			shell.setText("Admin");
			
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

			FormLayout layout = new FormLayout();
			splash.setLayout(layout);

			FormData labelData = new FormData();
			labelData.right = new FormAttachment(100, 0);
			labelData.bottom = new FormAttachment(100, 0);
			label.setLayoutData(labelData);
			label.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
			
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

		//	try {
			if (validate(macId,splash)) {
				System.out.println("Validation Successful");
				bar.setSelection(50);
				System.out.println("Getting All Available Stations");
				beanutil.getAvailableStations();
				bar.setSelection(90);
				image.dispose();
				splash.close();

				//shell.open();

			} else {
				image.dispose();
				splash.close();
				BusinessException business = new BusinessException();
				business.setResponseMessage(VALIDATION_FAILED);
				throw business;
			}
		/*} catch (BusinessException b) {
			System.out.println(b.getResponseMessage());
			b.printStackTrace();			
			//System.exit(0);			
		} catch (Exception e) {
			e.printStackTrace();
			//System.exit(0);
			if(splash != null && !splash.isDisposed())
				splash.close();
		}*/

	}

	/* *************************************************************************************************** */

	/**
	 * Method to validate the Machine
	 */
	private boolean validate(String macId,Shell toclose) {
		boolean isValid = false;
		try {
			Security sr=null;
			isValid = false;
				int rights = 0;
				BeanUtil beanutil = BeanUtil.getInstance();
				String stationCode = null;
				String date = null;

				

				stationCode = beanutil.getActingStationCode();

				System.out.println("Mac id of this machine  " + macId);
				
				sr = beanutil.getSecurityBean();
				
				if (sr == null) {				
					BeanUtil.isSecondServer = 1;
					sr = beanutil.getSecurityBean();				
				}
				
				if (null != sr) {
					rights = sr.getAdminDetails(macId, stationCode, VERSION_NO);

					System.out.println("Security Check result  " + rights);

					date = sr.getServerDate();
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date currentDate = dateFormat.parse(date);
					SERVER_DATE = dateFormat.format(currentDate);

					BeanUtil.setServerDate(SERVER_DATE);

					if (rights >= 0) {
						BeanUtil.setAdminRights(rights);
						isValid = true;
					}
				}
		} catch (Exception e) {		
			isValid = false;
			e.printStackTrace();
			System.exit(0);
		}		

		return isValid;

	}

	public void loadForm() throws Exception {
		shell.setBounds(0,0,1024,768);
		cptAdmin = (new AdminComposite(shell, SWT.BORDER)).loadComposite();
		cptAdmin.setBounds(0, 0, 1024, 768);
		/*cptAdmin.setBounds(shell.getBounds());
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int r=dim.width;
		int s=dim.height;
		//shell.setMaximized(true);
		cptAdmin.setBounds(0,0,r,s);
		/*Rectangle rect=Display.getDefault().getClientArea();
		cptAdmin.setBounds(rect);*/
		shell.open();
				
		while (!shell.isDisposed())
			if (!display.readAndDispatch())
				display.sleep();

		display.dispose();
	}

}
