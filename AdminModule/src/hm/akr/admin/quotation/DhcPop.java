/**
 * 
 */
package hm.akr.admin.quotation;

import hm.akr.admin.workspace.AdminComposite;
import hm.akr.common.FloatLimitValidation;
import hm.akr.common.IUIConstants;
import hm.akr.dto.DhcDto;
import hm.akr.msb.TreeComposite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
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
/**
 * @author user
 *
 */
public class DhcPop extends Composite implements SelectionListener, IUIConstants {

	private Shell shell;

	private Composite composite;

	private Text txtValue;

	private Button btnSet;
	
	public DhcDto[] dhcDTO = null;
	
	Display display;

	/**
	 * 
	 */
	public DhcPop(Shell shell, int swtValue) {
		super(shell, swtValue);
		this.shell = shell;		
	}

	public Composite loadComposite() throws Exception {
	
		shell.setBounds(300, 200, 350, 275);
		shell.open();
		shell.setText("DHC");
		
		composite = this.getShell();

		Composite cptTree = new TreeComposite(composite, SWT.BORDER, 190).loadComposite();
		cptTree.setBounds(10, 10, 200, 220);

		txtValue = new Text(composite, SWT.BORDER);
		txtValue.setBounds(220, 110, 50, 21);
		txtValue.addVerifyListener(new FloatLimitValidation());

		btnSet = new Button(composite, SWT.PUSH);
		btnSet.setText("Set");
		btnSet.setBounds(275, 110, 30, 21);
		btnSet.addSelectionListener(this);
		
		if(shell != null){
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

	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void widgetSelected(SelectionEvent arg0) {
		int len = 0;
		String[] stations = null;

		TreeComposite cptTree;
		try {
			cptTree = (TreeComposite) new TreeComposite(this, SWT.NONE, 300);
			stations = cptTree.getSelectedMSBStation();
			cptTree.clearTree();
			
			float value = 0;

			if (stations != null)
				len = stations.length;

			if (len > 0) {
				if(!txtValue.getText().equals("")){				
				dhcDTO = new DhcDto[len];
				for (int i = 0; i < len; i++) {
					value = 0;
					dhcDTO[i] = new DhcDto();
					dhcDTO[i].setStationCode(stations[i]);
					value = Float.parseFloat(txtValue.getText());
					dhcDTO[i].setDhc(value);										
				}
				this.shell.close();
				}else{
					AdminComposite.display("Please Enter Value", STATUS_SUCCESS, SUCCESS_FONT_COLOR);					
				}
			}else{
				AdminComposite.display("Please Select any station", STATUS_SUCCESS, SUCCESS_FONT_COLOR);	
				return;
			}
			
		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}

}
