package hm.akr.admin.sundry;

import hm.akr.admin.sundry.handler.SundryHandler;
import hm.akr.admin.workspace.AdminComposite;
import hm.akr.common.FloatLimitValidation;
import hm.akr.common.IUIConstants;
import hm.akr.msb.TreeComposite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
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
public class DhcComposite extends Composite implements IUIConstants, SelectionListener {

	private TreeComposite tree;
	private Label lblValue;
	private Button btnDHC;
	private Text txtDHC;
	private SundryHandler handler = null;
	private String EMPTY_STRING = "";

	public DhcComposite(Composite parent, int style) {
		super(parent, style);
		try {
			handler = new SundryHandler();
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
	
	public Composite loadComposite() throws Exception{
		this.setSize(800, 500);
		
		
		tree = new TreeComposite(this, SWT.NONE, 300);
		tree.loadComposite();
		tree.setBounds(50, 100, 200, 400);
		
		txtDHC = new Text(this, SWT.BORDER);
		txtDHC.setBounds(280, 245, 60, 24);
		txtDHC.setFont(TEXT_FONT);
		{
			btnDHC = new Button(this, SWT.PUSH | SWT.CENTER);
			btnDHC.setText("Set");
			btnDHC.setBounds(361, 246, 51, 24);
			btnDHC.addSelectionListener(this);
		}
		{
			lblValue = new Label(this, SWT.NONE);
			lblValue.setText("DHC");
			lblValue.setBounds(282, 223, 60, 16);
		}
		txtDHC.addVerifyListener(new FloatLimitValidation());	
		
		
		return this;
	}
	

	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void widgetSelected(SelectionEvent event) {
		Object source = event.getSource();
		if(source == btnDHC){
			setDHC();
		}
		
	}
	
	private void setDHC() {
		float dhc = 0;

		String[] stations = null;
		try {
			stations = tree.getSelectedMSBStation();
			if (isvalidMSB(stations)) {
				dhc = Float.parseFloat(txtDHC.getText());
				if (null != handler) {
					handler.setDHC(stations, dhc);
					AdminComposite.display("DHC Value Set Successfully",
							STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					txtDHC.setText(EMPTY_STRING);
					tree.clearTree();
				}
			}
		} catch (Exception e) {
			AdminComposite.display("DHC Value not set", STATUS_SUCCESS,
					SUCCESS_FONT_COLOR);
			e.printStackTrace();
		}

	}
	
	/**
	 * Method to validate MSB
	 * @param stations
	 * @param text
	 * @return
	 */
	private boolean isvalidMSB(String[] stations) {
		if (stations == null || stations.length <= 0) {
			AdminComposite.display("Please select atleast one station",STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;
		}else if (txtDHC.getText().length() == 0) {
			AdminComposite.display("Please enter DHC value",STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;
		}
		return true;
	}

}
