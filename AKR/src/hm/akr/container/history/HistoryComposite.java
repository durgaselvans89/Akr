/**
 * 
 */
package hm.akr.container.history;

import hm.akr.common.BeanUtil;
import hm.akr.common.SWTResourceManager;
import hm.akr.dto.VersionDTO;
import hm.akr.workspace.MainForm;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;


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
/**
 * 
 */
public class HistoryComposite extends Composite implements SelectionListener {

	private HistoryHandler handler;

	private Shell shell;

	BeanUtil beanutil = null;

	{	
		SWTResourceManager.registerResourceUser(this);
	}	

	public HistoryComposite(Shell shell, int style) throws Exception {
		super(shell, style);	
		this.shell = shell;
		handler = new HistoryHandler();
		
	}
	
	private Button btnSetup;

	private Combo cbYears;

	private Label lblYears;
	
	private Group group1;

	private Label hidnYears;
	
	VersionDTO[] dto = null;
		

	public Composite loadComposite() {		

		{
			lblYears = new Label(this, SWT.NONE);
			lblYears.setText("Select Year ");
			lblYears.setBounds(365, 166, 76, 18);
		}
		{
			cbYears = new Combo(this, SWT.BORDER | SWT.READ_ONLY);
			cbYears.setBounds(445, 163, 87, 21);
			populateYears();
		}
		
		{
			hidnYears = new Label(this, SWT.NONE);
			hidnYears.setText("");
			hidnYears.setBounds(365, 266, 76, 18);
			hidnYears.setVisible(false);
		}
		
		{
			btnSetup = new Button(this, SWT.PUSH | SWT.CENTER);
			btnSetup.setText("Set");
			btnSetup.setBounds(536, 163, 60, 23);
			btnSetup.addSelectionListener(this);
		}
		{
			group1 = new Group(this, SWT.NONE);
			GridLayout group1Layout = new GridLayout();
			group1Layout.makeColumnsEqualWidth = true;
			group1.setLayout(group1Layout);
			group1.setText("Change Year");
			group1.setBounds(336, 130, 360, 80);
		}
		
		return this;
	}


	private void populateYears() {
		if(handler != null){
			try {
				dto = handler.getHistoryYears();
				
				if(dto != null){
					for(int i = 0; i < dto.length; i++){
						cbYears.add(dto[i].getBranch_code());
						
					}
				}
			} catch (Exception e) {			
				e.printStackTrace();
			}
		}
		cbYears.add("2011-12");				
	}


	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		
		
	}


	@Override
	public void widgetSelected(SelectionEvent e) {
		if(!cbYears.getText().equals("")){
			try {
				String dbName = getDBName(cbYears.getText());
				if(cbYears.getText().equals("2011-12")){
					BeanUtil.setDbName("");
					BeanUtil.setDbYear("");
				}else {
					BeanUtil.setDbName(dbName);
					BeanUtil.setDbYear(cbYears.getText());
				}
				
				displayError("Year "+cbYears.getText()+" Changed Successfully");

				String currYear = BeanUtil.getDbYear();
				if(currYear.equals("")){
					currYear = "2011-12";
				}
				beanutil = BeanUtil.getInstance();
				String actual = beanutil.getActualStationCode();
				String acting = beanutil.getActingStationCode();
				String putActing = " ";
				if(!actual.equalsIgnoreCase(acting)){
					putActing = " ("+acting+")";
				}
				
				
				shell.setText("AKR Parcel Service" + " | "
						+ beanutil.getActualStationName() + " | "
						+ actual + putActing + " | " + currYear + 
						"                                 "+
						"                                  "+
						"                                  "+
						
						 "Version - "+MainForm.VERSION_NO);
				
				
			} catch (Exception e1) {			
				e1.printStackTrace();
			}
		}
		
	}


	private String getDBName(String year) {
		if(dto != null){
			for(int i = 0; i < dto.length; i++){
				if(year.equalsIgnoreCase(dto[i].getBranch_code())){
					return dto[i].getStation_code();
				}				
			}
		}		
		return null;
	}
	
	private void displayError(String errorText) {
		MessageBox messageBox = new MessageBox(shell, SWT.OK);
		messageBox.setMessage(errorText);
		messageBox.setText("Alert");
		messageBox.open();
	}

}
