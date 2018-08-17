package hm.akr.admin.sundry;

import hm.akr.admin.sundry.handler.SundryHandler;
import hm.akr.admin.workspace.AdminComposite;
import hm.akr.admin.workspace.SWTResourceManager;
import hm.akr.common.BeanUtil;
import hm.akr.common.FloatLimitValidation;
import hm.akr.common.FloatValidation;
import hm.akr.common.IUIConstants;
import hm.akr.common.KalendarDialog;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
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
 * 
 * @author Class for ValueEditComposite
 */
public class ValueEditComposite extends Composite implements IUIConstants {

	private Table tblValueEdit;
	private TableColumn column;
	private TableEditor editor;
	private Button btnSet;
	private Text txtDate;
	private Label lblDate;
	private String SERVER_DATE = null;
	private static String[] COLUMN_HEADINGS = { "Available Values", "Change To" };
	Float[] availValues = null;
	Float[] pendingBPI = null;
	Float[] pendingLRC = null;
	Text[] txtValue;
	private String tabName = null;
	private Button btnGo;
	private int rows = 0;

	SundryHandler handler = null;
	private DecimalFormat decimalFormat;

	/**
	 * Constructor Method
	 * 
	 * @param parent
	 * @param style
	 */
	public ValueEditComposite(Composite parent, int style, String selectedTab) {
		super(parent, style);
		this.tabName = selectedTab;
		try {
			handler = new SundryHandler();
			SERVER_DATE = handler.getServerDate();
		} catch (Exception exception) {

			exception.printStackTrace();
		}

	}

	/**
	 * Method to create ValueEditComposite
	 * 
	 * @return Composite
	 */
	public Composite loadComposite() {
		{

			{

				// Draw Table
				tblValueEdit = new Table(this, SWT.FULL_SELECTION
						| SWT.HIDE_SELECTION | SWT.V_SCROLL);
				tblValueEdit.setHeaderVisible(true);
				tblValueEdit.setLinesVisible(true);
				tblValueEdit.addListener(SWT.MeasureItem, new Listener() {
					public void handleEvent(Event event) {
						event.height = 18;
					}
				});

				if (null != handler && tblValueEdit.isEnabled()) {
					try {
						if (tabName.equalsIgnoreCase("BPI")) {
							availValues = handler.getBPI();
							if(availValues != null)
								Arrays.sort(availValues);
							pendingBPI = handler.getPendingBPI();
							//System.out.println("pend bpi==>"+pendingBPI);
						} else if (tabName.equalsIgnoreCase("LRC")) {
							availValues = handler.getLRCharges();
							if(availValues != null)
								Arrays.sort(availValues);
							pendingLRC = handler.getPendingLRC();
						} else if (tabName.equalsIgnoreCase("GSC")) {
							availValues = handler.getGSC();
							if(availValues != null)
								Arrays.sort(availValues);
						}
					} catch (Exception exception) {
						exception.printStackTrace();
					}
				}

				if (availValues != null)
					rows = availValues.length;

				if (rows > 3) {
					tblValueEdit.setBounds(21, 60, 180, 90);
				} else {
					tblValueEdit.setBounds(21, 60, 167, 90);
				}

				for (int i = 0; i < 2; i++) {
					column = new TableColumn(tblValueEdit, SWT.CENTER, i);
					column.setText(COLUMN_HEADINGS[i]);

				}

				for (int i = 0; i < 2; i++) {
					if (i == 0) {
						tblValueEdit.getColumn(i).setWidth(93);
					} else {
						tblValueEdit.getColumn(i).setWidth(67);
					}
				}

				// Drawing initial table items
				txtValue = new Text[rows];
				decimalFormat = new DecimalFormat("0.00");
				for (int rowId = 0; rowId < rows; rowId++) {
					// Adding Table items with the existing Table
					final TableItem item = new TableItem(tblValueEdit, SWT.NONE);

					// Available Values
					if (tabName.equalsIgnoreCase("BPI")) {
						if ((availValues[rowId] % 1) == new Float(0)) {
							item.setText(0, decimalFormat
									.format(availValues[rowId]));
						} else {
							item.setText(0, String.valueOf(availValues[rowId]));
						}

					} else {
						item.setText(0, decimalFormat
								.format(availValues[rowId]));
					}

					// Draw Value Text Field
					editor = new TableEditor(tblValueEdit);
					txtValue[rowId] = new Text(tblValueEdit, SWT.NONE);
					txtValue[rowId].setFont(TEXT_FONT);
					//if(txtValue[rowId].isFocusControl()){
					if (tabName.equalsIgnoreCase("BPI")) {
						txtValue[rowId]
								.addVerifyListener(new FloatValidation());
					} else {
						txtValue[rowId]
								.addVerifyListener(new FloatLimitValidation());
					}
					//}
					editor.grabHorizontal = true;
					editor.setEditor(txtValue[rowId], item, 1);
				}

			}

			if (!tabName.equalsIgnoreCase("GSC")) {
				{
					lblDate = new Label(this, SWT.NONE);
					lblDate.setText("Date");
					lblDate.setBounds(12, 194, 24, 20);
					lblDate.setFont(LABLE_FONT);
				}
				{
					txtDate = new Text(this, SWT.BORDER);
					txtDate.setBounds(42, 190, 80, 22);
					txtDate.setFont(TEXT_FONT);
					txtDate.setEditable(false);
				}
				{
					btnGo = new Button(this, SWT.PUSH | SWT.CENTER);
					btnGo.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btnGo.setBounds(128, 189, 26, 23);
					btnGo.setFont(BUTTON_FONT);
					btnGo.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent e) {
							KalendarDialog cd = new KalendarDialog(new Shell());
							Object obj = cd.open();
							if (obj != null) {
								String date = obj.toString();
								try {
									SimpleDateFormat parse = new SimpleDateFormat(
											"dd-MM-yyyy");
									Date curDate = parse.parse(SERVER_DATE);
									Date selectedDate = parse.parse(date);
									if (selectedDate.after(curDate)
											|| date.equals(SERVER_DATE)) {
										txtDate.setText(date);
										AdminComposite.display("",
												STATUS_SUCCESS,
												SUCCESS_FONT_COLOR);
									} else {
										txtDate.setText("");
										AdminComposite
												.display(
														"Selected date should be greater or equal to current date",
														STATUS_SUCCESS,
														SUCCESS_FONT_COLOR);
									}

								} catch (Exception exception) {
									exception.printStackTrace();
								}

							}
						}
					});
				}
			}
			{
				btnSet = new Button(this, SWT.PUSH | SWT.CENTER);
				btnSet.setText("Set");
				btnSet.setBounds(164, 189, 40, 25);
				btnSet.addSelectionListener(new ValueEditListener());
				btnSet.setFont(BUTTON_FONT);
			}

		}

		return this;
	}

	/**
	 * 
	 * @author
	 * 
	 */
	class ValueEditListener implements SelectionListener {

		private String EMPTYSTRING = "";
		private Float[] newValues;

		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {

		}

		@Override
		public void widgetSelected(SelectionEvent event) {
			Object source = event.getSource();
			if (source == btnSet) {
				AdminComposite.display("", STATUS_SUCCESS,SUCCESS_FONT_COLOR);
				ArrayList<Float> availlist = new ArrayList<Float>();
				ArrayList<Float> newlist = new ArrayList<Float>();

				for (int rowId = 0; rowId < rows; rowId++) {
					Float availvalue = null;
					Float newvalue = null;
					if (!txtValue[rowId].getText().equals(EMPTYSTRING)) {
						newvalue = Float.valueOf(txtValue[rowId].getText()
								.trim());
						availvalue = availValues[rowId];
						int len = 0;
						//Check BPI values with Pending update values
						if (tabName.equalsIgnoreCase("BPI")
								&& pendingBPI != null) {
							len = pendingBPI.length;

							for (int j = 0; j < len; j++) {
								System.out.println(availvalue);
								System.out.println(pendingBPI[j]);
								if (availvalue.floatValue() == pendingBPI[j]
										.floatValue()) {
									AdminComposite.display("BPI Value "
											+ availvalue
											+ " having a pending update",
											STATUS_SUCCESS, SUCCESS_FONT_COLOR);
									newvalue = null;
									return;
								}
							}

						} else if (tabName.equalsIgnoreCase("LRC")
								&& pendingLRC != null) {
							//Check LRC values with Pending update values
							len = pendingLRC.length;
							for (int j = 0; j < len; j++) {
								if (availvalue.floatValue() == pendingLRC[j]
										.floatValue()) {
									AdminComposite.display("LRC Value "
											+ availvalue
											+ " having a pending update",
											STATUS_SUCCESS, SUCCESS_FONT_COLOR);
									newvalue = null;
									return;
								}
							}

						}
					}

					if (null != newvalue
							&& availvalue.floatValue() != newvalue.floatValue()) {
						availlist.add(availvalue);
						newlist.add(newvalue);
					}
				}

				int size = availlist.size();

				availValues = availlist.toArray(new Float[size]);
				newValues = newlist.toArray(new Float[size]);

				try {
					SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
					Date date = null;
					String effectiveDate = null;
					if (!tabName.equalsIgnoreCase("GSC"))
						effectiveDate = txtDate.getText();
					if (null != effectiveDate
							&& !effectiveDate.equals(EMPTYSTRING)) {
						if (effectiveDate.equals(SERVER_DATE)) {
							date = null;
						} else {
							date = format.parse(effectiveDate);
						}
					}

					if (tabName.equalsIgnoreCase("BPI")) {

						if (newValues.length != 0) {
							handler.setBPIUpdate(availValues, newValues, date);
							AdminComposite.display(
									"BPI Value updated successfully",
									STATUS_SUCCESS, SUCCESS_FONT_COLOR);
							
							BeanUtil.isBPI_Changed = true;

						} else if (newValues.length == 0) {
							AdminComposite.display(
									"Please Enter change to value",
									STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						}

						refreshTable();
					} else if (tabName.equalsIgnoreCase("LRC")) {
						if (newValues.length != 0) {
							handler.setLRCUpdate(availValues, newValues, date);
							AdminComposite.display(
									"LRC Value updated successfully",
									STATUS_SUCCESS, SUCCESS_FONT_COLOR);

						} else if (newValues.length == 0) {
							AdminComposite.display(
									"Please Enter change to value",
									STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						}
						refreshTable();
					} else if (tabName.equalsIgnoreCase("GSC")) {
						if (newValues.length != 0) {
							handler.setGSCUpdate(availValues, newValues, date);
							AdminComposite.display(
									"GSC Value updated successfully",
									STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						} else if (newValues.length == 0) {
							AdminComposite.display(
									"Please Enter change to value",
									STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						}
						refreshTable();
					}

				} catch (Exception exception) {
					exception.printStackTrace();
					AdminComposite.display("Updation failed", STATUS_SUCCESS,
							SUCCESS_FONT_COLOR);
				}
			}

		}

		/**
		 * Method to refresh table with new values after update
		 * @param newValues
		 */
		private void refreshTable() {
			for (int i = 0; i < txtValue.length; i++) {
				if (!txtValue[i].getText().equals(EMPTYSTRING)) {
					TableItem item = tblValueEdit.getItem(i);

					if (tabName.equalsIgnoreCase("GSC")) {//GSC Formating
						item.setText(String.valueOf(decimalFormat.format(Float.parseFloat(txtValue[i].getText().trim()))));
						txtValue[i].setText(EMPTYSTRING);
					} else {
						if (txtDate.getText().equals(EMPTYSTRING)
								|| txtDate.getText().equals(SERVER_DATE)) {
							if(tabName.equalsIgnoreCase("BPI")){ //BPI Formating
								if((Float.parseFloat(txtValue[i].getText()) % 1)== new Float(0)){								
									item.setText(String.valueOf(decimalFormat.format(Float.parseFloat(txtValue[i].getText().trim()))));
								}else{
									item.setText(txtValue[i].getText().trim());
								}	
							}else{ //LR Charges Formating
								item.setText(String.valueOf(decimalFormat.format(Float.parseFloat(txtValue[i].getText().trim()))));
							}
							
						}
						txtValue[i].setText(EMPTYSTRING);
						txtDate.setText(EMPTYSTRING);
					}
				}
			}

			TableItem[] items = tblValueEdit.getItems();
			availValues = null;
			int len = items.length;
			availValues = new Float[len];

			for (int i = 0; i < len; i++) {
				availValues[i] = Float.valueOf(items[i].getText());
			}

		}

	}

}
