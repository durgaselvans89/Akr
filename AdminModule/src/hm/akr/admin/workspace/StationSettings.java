package hm.akr.admin.workspace;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * This code was edited or generated using CloudGarden's Jigloo
 * SWT/Swing GUI Builder, which is free for non-commercial
 * use. If Jigloo is being used commercially (ie, by a corporation,
 * company or business for any purpose whatever) then you
 * should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details.
 * Use of Jigloo implies acceptance of these licensing terms.
 * A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
 * THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
 * LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class StationSettings implements KeyListener {

	private Label lblStationCode;
	private Text txtStationCode;
	private Shell shell = null;
	private Button btnSet;
	private Display display;
	private String stationCode;
	private String stationName;

	public StationSettings() throws Exception {

		display = new Display();
		shell = new Shell(display,SWT.TITLE | SWT.CLOSE | SWT.BORDER);
		shell.setBounds(400, 300, 300, 200);
		
	}

	public boolean loadComposite() throws Exception {
		boolean flag = false;

		String curDir = System.getProperty("user.dir");
		File f = new File(curDir + "/lib/akrAdmin.properties");
		if (f.exists()) {		
			//Property File Exists
			flag = false;			

		} else {
			// Create Property File
			
			shell.open();
			
			lblStationCode = new Label(shell, SWT.NONE);
			lblStationCode.setText("Enter Station Code");
			lblStationCode.setFont(SWTResourceManager.getFont("Verdana", 10, 0,false, false));
			lblStationCode.setBounds(20, 53, 125, 25);

			txtStationCode = new Text(shell, SWT.BORDER);
			txtStationCode.setBounds(150, 50, 100, 25);		
			txtStationCode.addKeyListener(this);
			txtStationCode.setFocus();

			btnSet = new Button(shell, SWT.PUSH);
			btnSet.setBounds(150, 90, 40, 25);
			btnSet.setText("Set");

			btnSet.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void widgetSelected(SelectionEvent arg0) {
					try {
						setStation();
					} catch (Exception e) {						
						e.printStackTrace();
					}
				}				

			});

			while (!shell.isDisposed())
				if (!display.readAndDispatch())
					display.sleep();

			display.dispose();
			flag = true;
		}
		
		return flag;
	}
	
	private void setStation() throws Exception{
		try {
			String stnCodeLoc = "lib/references.txt";
			BufferedInputStream bis = null;
			DataInputStream dis = null;
			FileInputStream fis = null;
			fis = new FileInputStream(stnCodeLoc);
			bis = new BufferedInputStream(fis);
			dis = new DataInputStream(bis);
			stationCode = txtStationCode.getText().toUpperCase();
			if(stationCode=="")
			{
				displayError("Enter Your Station Code");
			}
			else
			{
				boolean check=false;
			while (dis.available() != 0) {

				String folder = dis.readLine();
				String[] station = folder.split("\t");						
				
				stationName = "";
				//for(int i = 0; i < station.length; i++){
				if (station[0].equalsIgnoreCase(stationCode)) {
					stationName = station[1];
					check=true;
					break;
				} 
				//}
			}
			if(!check) {
				displayError("Enter Valid Station");
				txtStationCode.setText("");
				
			}else{
			fis.close();
			bis.close();
			dis.close();
			Properties props = new Properties();
			props.setProperty("StationCode", stationCode);
			props.setProperty("CashBeanJNDI", "ejb/Cash");
			props.setProperty("ReportingBeanJNDI", "ejb/Reporting");
			props.setProperty("InitialContextFactory",
					"org.jnp.interfaces.NamingContextFactory");
			props.setProperty("GoodsBeanJNDI", "ejb/Goods");
			props.setProperty("SecurityBeanJNDI", "ejb/Security");
			props.setProperty("StationBeanJNDI", "ejb/Station");
			props.setProperty("UrlPrefix",
					"org.jboss.naming/:org.jnp.interfaces");
			props.setProperty("VehicleBeanJNDI", "ejb/Vehicle");
			props.setProperty("ProviderUrl", "87.248.113.14:1099");
			props.setProperty("StationName", stationName);
			OutputStream out = new FileOutputStream(new File(
					"lib/akrAdmin.properties"));
			props.store(out, "akr");
			out.close();					

			shell.dispose();
			}

		} 
		}catch (Exception e) {
			e.printStackTrace();
		}					
	}
	
	/**
	 * 
	 * @param errorText
	 */
	private void displayError(String errorText) {
		MessageBox messageBox = new MessageBox(shell, SWT.OK);
		messageBox.setMessage(errorText);
		messageBox.setText("Alert");
		messageBox.open();
	}

	@Override
	public void keyPressed(KeyEvent event) {
		Object source = event.getSource();

		if (source == txtStationCode) {
			if (event.keyCode == 13) {
				try {
					setStation();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
