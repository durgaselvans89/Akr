package hm.akr.admin.sundry;

import hm.akr.admin.sundry.handler.SundryHandler;
import hm.akr.admin.workspace.AdminComposite;
import hm.akr.admin.workspace.SWTResourceManager;
import hm.akr.common.BeanUtil;
import hm.akr.common.FloatLimitValidation;
import hm.akr.common.FloatValidation;
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
 * 
 * @author Class to create OptionComposite
 * 
 */
public class OptionComposite extends Composite implements IUIConstants {

	private Button rdSet;

	private Button rdEdit;

	Composite cptValueEdit = null;

	Composite cpttree = null;

	TreeComposite tree = null;

	Composite temp;

	private Button btnSet;

	private Label lblunit;

	private Text txtValue;

	private Label lblName;

	private String selectedTab;

	public String EMPTY_STRING = "";

	SundryHandler handler = null;
	
	Composite tbf = null;

	/**
	 * Constructor for OptionComposite
	 * 
	 * @param parent
	 * @param style
	 * @param selectedTab
	 */

	public OptionComposite(Composite parent, int style, String selectedTab) {
		super(parent, style);
		this.selectedTab = selectedTab;
		this.tbf = parent;
		this.temp = this;
		try {
			tree = new TreeComposite(this, SWT.NONE, 300);
			handler = new SundryHandler();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to Create OptionComposite
	 * 
	 * @return Composite
	 */

	public Composite loadComposite() {

		rdSet = new Button(this, SWT.RADIO);
		rdSet.setText("SET VALUES");
		rdSet.setSelection(true);
		rdSet.setBounds(10, 22, 120, 20);
		rdSet.addSelectionListener(new RadioAction());

		rdEdit = new Button(this, SWT.RADIO);
		rdEdit.setText("EDIT VALUES");
		rdEdit.setBounds(130, 22, 95, 20);
		rdEdit.addSelectionListener(new RadioAction());

		Label lblHeader = new Label(this, SWT.NONE);
		lblHeader.setAlignment(SWT.RIGHT);		
		lblHeader.setText(selectedTab);
		lblHeader.setFont(HEAD_FONT);
		//lblHeader.setForeground(HEAD_FONT_COLOR);
		lblHeader.setBounds(610, 4, 112, 25);

		{
			lblName = new Label(this, SWT.NONE);
			lblName.setText(selectedTab);
			lblName.setBounds(270, 220, 60, 17);
			lblName.setFont(LABLE_FONT);

		}
		{
			txtValue = new Text(this, SWT.BORDER);
			txtValue.setBounds(270, 245, 60, 22);
			txtValue.setFont(TEXT_FONT);
			txtValue.addVerifyListener(new FloatValidation());
		}
		{
			lblunit = new Label(this, SWT.NONE);
			lblunit.setBounds(335, 246, 20, 19);
			lblunit.setText("Rs");
			lblunit.setFont(LABLE_FONT);

			if (selectedTab.equals("BPI")) {
				lblunit.setVisible(false);
			} else
			{
				lblunit.setVisible(true);
				txtValue.addVerifyListener(new FloatLimitValidation());
			}

		}
		{
			btnSet = new Button(this, SWT.NONE);
			btnSet.setText("Set");
			btnSet.setBounds(366, 245, 40, 23);
			btnSet.setFont(BUTTON_FONT);
			btnSet.addSelectionListener(new RadioAction());
		}

		try {

			rdSet.setFont(SWTResourceManager.getFont("Tahoma", 9, 1, false,
					false));
			//rdSet.setBounds(12, 21, 78, 20);

			cpttree = tree.loadComposite();
			cpttree.setBounds(35, 90, 200, 350);

			cptValueEdit = new ValueEditComposite(this, SWT.BORDER, selectedTab);
			cptValueEdit.setBounds(100, 70, 240, 270);
			cptValueEdit.setVisible(false);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return this;
	}

	/**
	 * 
	 * @author kibaitachi
	 * 
	 */
	class RadioAction implements SelectionListener {

		@Override
		public void widgetDefaultSelected(SelectionEvent event) {

		}

		@Override
		public void widgetSelected(SelectionEvent event) {

			Object source = event.getSource();
			if (rdSet == source) {
				AdminComposite.display("STATUS", STATUS_SUCCESS,
						SUCCESS_FONT_COLOR);
				if (null != cptValueEdit)
					cptValueEdit.dispose();
				tree.clearTree();
				cpttree.setVisible(true);
				rdSet.setFont(SWTResourceManager.getFont("Tahoma", 9, 1, false,
						false));
				rdEdit.setFont(SWTResourceManager.getFont("Tahoma", 9, 0,
						false, false));
				lblName.setVisible(true);
				lblunit.setVisible(true);
				txtValue.setVisible(true);
				btnSet.setVisible(true);
				txtValue.setText(EMPTY_STRING);

			} else if (rdEdit == source) {
				AdminComposite.display("STATUS", STATUS_SUCCESS,
						SUCCESS_FONT_COLOR);
				if (null != cptValueEdit)
					cptValueEdit.dispose();
				cptValueEdit = new ValueEditComposite(temp, SWT.BORDER,
						selectedTab).loadComposite();
				cptValueEdit.setBounds(100, 70, 240, 270);
				cpttree.setVisible(false);
				cptValueEdit.setVisible(true);
				rdEdit.setFont(SWTResourceManager.getFont("Tahoma", 9, 1,
						false, false));
				rdSet.setFont(SWTResourceManager.getFont("Tahoma", 9, 0, false,
						false));
				lblName.setVisible(false);
				lblunit.setVisible(false);
				txtValue.setVisible(false);
				btnSet.setVisible(false);

			} else if (btnSet == source) {
				AdminComposite.display("", STATUS_SUCCESS,SUCCESS_FONT_COLOR);
				if (rdSet.getSelection()) {
					//String selected = tbf.getSelection()[0].getText();
					if (selectedTab.equals("BPI")) {						
						setBPI();
						
					} else if (selectedTab.equals("LRC")) {
						setLRC();
					} else if (selectedTab.equals("GSC")) {
						setGSC();
					}
				}
			}

		}

		/**
		 * 
		 */
		private void setGSC() {

			float gsc = 0;
			
				
				String[] stations = null;
				try {

					stations = tree.getSelectedMSBStation();				
					if(isvalidMSB(stations)){
				gsc = Float.parseFloat(txtValue.getText());
					if (null != handler){						
							handler.setGSC(stations, gsc);
							AdminComposite.display(
									"GSC Value Set Successfully",
									STATUS_SUCCESS, SUCCESS_FONT_COLOR);
							txtValue.setText(EMPTY_STRING);
							tree.clearTree();
					}}	} catch (Exception e) {
						AdminComposite.display(
								"GSC Settings Failed",
								STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						e.printStackTrace();
					}
			
			
		}

		/**
		 * 
		 */
		private void setLRC() {
			float lrc = 0;

			String[] stations = null;
			try {
				stations = tree.getSelectedMSBStation();
				if (isvalidMSB(stations)) {
					lrc = Float.parseFloat(txtValue.getText());
					if (null != handler) {
						handler.setLRC(stations, lrc);
						AdminComposite.display("LRC Value Set Successfully",
								STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						txtValue.setText(EMPTY_STRING);
						tree.clearTree();
					}
				}
			} catch (Exception e) {
				AdminComposite.display("LRC Value not set", STATUS_SUCCESS,
						SUCCESS_FONT_COLOR);
				e.printStackTrace();
			}

		}

		/**
		 * 
		 */
		private void setBPI() {
			float value = 0;
				String[] stations = null;				
				try {
					stations = tree.getSelectedMSBStation();	
					if(isvalidMSB(stations)){
					value = Float.parseFloat(txtValue.getText());
					if (null != handler){						
							handler.setBPIValues(stations, value);
							AdminComposite.display(
									"BPI Value Set Successfully",
									STATUS_SUCCESS, SUCCESS_FONT_COLOR);
							txtValue.setText(EMPTY_STRING);
							tree.clearTree();
							
							BeanUtil.isBPI_Changed = true;
							
					}						 
					}
				} catch (Exception e) {
					AdminComposite.display("BPI Value not set",
							STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					e.printStackTrace();
				}
			
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
		}else if (txtValue.getText().length() == 0) {
			AdminComposite.display("Please enter "+selectedTab+" value",STATUS_SUCCESS, SUCCESS_FONT_COLOR);
			return false;
		}
		return true;
	}

}
