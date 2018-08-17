package hm.akr.common;

/**
 * 
 */

import hm.akr.admin.sundry.handler.SundryHandler;
import hm.akr.admin.workspace.AdminComposite;
import hm.akr.dto.InsuranceDTO;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

/**
 * @author Class to create ValueRangeComposite
 * 
 */
public class ValueRangeComposite extends Composite implements SelectionListener, IUIConstants {

	Shell shell = null;

	private int ROW_HEIGHT;

	private int ROW_SIZE = 4;

	private final int COLUMN_SIZE = 4;

	private int COLUMN_WIDTH = 125;

	private final int DEL_COLUMN_WIDTH = 17;

	private int TBL_HEIGHT = 0;

	private static int ITEM_MAX = 8;

	private static String[] COMBO_ITEMS;

	private static String[] COLUMN_HEADINGS = { "From", "To", "Value", "" };

	private boolean adjust = true;

	private Image img = null;

	private static final String DEL_IMG = "hm/akr/resources/delete.PNG";

	private static final String ADD_IMG = "hm/akr/resources/add.PNG";

	private Button btnAdd;

	private Button btnSet;

	private TableEditor editor = null;

	private TableColumn column = null;

	SundryHandler handler = null;

	Text txtValue;

	public static Table tblValueRange;

	public static ArrayList<Text> txtList;

	public static ArrayList<CCombo> cbFromList;

	public static ArrayList<CCombo> cbToList;

	String selectedTab = null;

	public InsuranceDTO[] insuranceDTO = null;

	Composite composite = null;

	private Composite cptLRC;

	Display display;

	Shell parent;

	private DecimalFormat decimalFormat;

	/**
	 * For Quotation pop up passing Shell
	 * 
	 * @param cpt
	 * @param value
	 * @param selectedab
	 */
	public ValueRangeComposite(Shell shell, int value, String selectedab, int width, InsuranceDTO[] insuranceDTO) {
		super(shell, value);
		this.shell = shell;

		COLUMN_WIDTH = width;
		this.selectedTab = selectedab;
		this.insuranceDTO = insuranceDTO;
		try {
			handler = new SundryHandler();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * For Sundry passing Composite
	 * @param cpt
	 * @param swtValue
	 * @param selectedTab
	 */

	public ValueRangeComposite(TabFolder cpt, int value, String selectedab) {
		super(cpt, value);
		composite = this;

		this.selectedTab = selectedab;

		try {
			handler = new SundryHandler();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Composite loadComposite() {

		try {		

			cbFromList = new ArrayList<CCombo>();

			cbToList = new ArrayList<CCombo>();

			txtList = new ArrayList<Text>();

			if (shell != null) {
				display = Display.getDefault();
				shell.setBounds(400, 200, 270, 320);
				shell.open();
				shell.setText("Insurance");
				composite = this.getShell();

			} else {
				composite.setBounds(10, 10, 500, 500);
				cptLRC = new Composite(composite, SWT.BORDER);
				cptLRC.setBounds(100, 20, 450, 240);
			}

			final Color white = Display.getCurrent().getSystemColor(SWT.COLOR_GREEN);
			{
				btnSet = new Button(composite, SWT.PUSH | SWT.CENTER);
				btnSet.setText("Set");

				if (selectedTab.equals("quotation")) {
					btnSet.setBounds(200, 240, 40, 22);
				} else {
					btnSet.setBounds(510, 270, 40, 22);
				}
				btnSet.addSelectionListener(this);
			}

			{
				if (shell != null) {
					btnAdd = new Button(composite, SWT.NONE);
					btnAdd.setBounds(218, 20, 23, 21);
				} else {
					btnAdd = new Button(cptLRC, SWT.NONE);
					btnAdd.setBounds(390, 20, 23, 21);
				}

				InputStream stream = ValueRangeComposite.class.getClassLoader().getResourceAsStream(ADD_IMG);
				img = new Image(Display.getDefault(), stream);
				btnAdd.setImage(img);

				// Add button Action
				btnAdd.addSelectionListener(new SelectionListener() {

					@Override
					public void widgetDefaultSelected(SelectionEvent arg0) {

					}

					@Override
					public void widgetSelected(SelectionEvent arg0) {
						{
							if(shell == null){
								if(ROW_SIZE <= 8)
									tblValueRange.setSize(394, 94);
							}else{
								if(ROW_SIZE <= 8)
									tblValueRange.setSize(229, 94);
							}
							
							Rectangle rt = tblValueRange.getBounds();
							if (TBL_HEIGHT < ((ITEM_MAX * ROW_HEIGHT) + tblValueRange.getHeaderHeight()) + 18) {
								// Increasing Table height
								TBL_HEIGHT += ROW_HEIGHT + 4;
								tblValueRange.setSize(rt.width, TBL_HEIGHT);

							} else {
								if (adjust) {
									// Increasing Table height with vertical
									// bar(15)
									tblValueRange.setSize(rt.width + 15, TBL_HEIGHT);
									adjust = false;
								}
							}

							// Adding Table items with the existing Table
							final TableItem item = new TableItem(tblValueRange, SWT.NONE);

							int len = tblValueRange.getItems().length;
							int rowId = len - 1;
							ROW_SIZE = len;

							// Draw From Combo Box
							editor = new TableEditor(tblValueRange);
							final CCombo[] cbFrom = new CCombo[len];
							cbFrom[rowId] = new CCombo(tblValueRange, SWT.READ_ONLY);
							cbFrom[rowId].setItems(COMBO_ITEMS);
							editor.grabHorizontal = true;
							editor.setEditor(cbFrom[rowId], item, 0);
							cbFromList.add(cbFrom[rowId]);

							// Draw To Combo Box
							editor = new TableEditor(tblValueRange);
							final CCombo[] cbTo = new CCombo[len];
							cbTo[rowId] = new CCombo(tblValueRange, SWT.READ_ONLY);
							cbTo[rowId].setItems(COMBO_ITEMS);
							cbTo[rowId].remove("0");
							cbTo[rowId].add("$");
							editor.grabHorizontal = true;
							editor.setEditor(cbTo[rowId], item, 1);
							cbToList.add(cbTo[rowId]);

							// Draw Value Text Field
							editor = new TableEditor(tblValueRange);
							final Text[] txtValue = new Text[len];
							txtValue[rowId] = new Text(tblValueRange, SWT.NONE);
							txtValue[rowId].addVerifyListener(new FloatLimitValidation());
							editor.grabHorizontal = true;
							editor.setEditor(txtValue[rowId], item, 2);
							txtList.add(txtValue[rowId]);
							
							// Draw Delete Button
							editor = new TableEditor(tblValueRange);

							final Button[] deleteButtons = new Button[len];
							deleteButtons[rowId] = new Button(tblValueRange, SWT.PUSH);
							deleteButtons[rowId].setSize(37, 18);

							InputStream stream = ValueRangeComposite.class.getClassLoader()
									.getResourceAsStream(DEL_IMG);
							img = new Image(Display.getDefault(), stream);
							deleteButtons[rowId].setBackground(white);
							deleteButtons[rowId].setImage(img);
							deleteButtons[rowId].computeSize(SWT.DEFAULT, tblValueRange.getItemHeight());
							editor.grabHorizontal = true;
							editor.minimumHeight = deleteButtons[rowId].getSize().y;
							editor.minimumWidth = deleteButtons[rowId].getSize().x;
							deleteButtons[rowId].setText(Integer.toString(rowId));
							editor.grabHorizontal = true;
							editor.setEditor(deleteButtons[rowId], item, 3);

							// Delete Button Action
							deleteButtons[rowId].addMouseListener(new MouseAdapter() {
								@Override
								public void mouseDown(MouseEvent event) {

									// Disposing Items
									Button obj = (Button) event.getSource();

									int delete = Integer.parseInt(obj.getText());
									deleteButtons[delete].dispose();
									txtValue[delete].dispose();
									cbFrom[delete].dispose();
									cbTo[delete].dispose();
									txtList.remove(txtValue[delete]);
									cbFromList.remove(cbFrom[delete]);
									cbToList.remove(cbTo[delete]);
									item.dispose();

									// Decreasing Table height for a row
									TBL_HEIGHT -= 18;
									Rectangle rt = tblValueRange.getBounds();
									int len = tblValueRange.getItemCount();
									int temp = len * 18;
									if (temp > (ITEM_MAX * 18)) {
										TBL_HEIGHT += 18;
										tblValueRange.setSize(rt.width, TBL_HEIGHT);
									} else if (temp == (ITEM_MAX * 18)) {
										TBL_HEIGHT += 18;
										int wid = rt.width;
										wid -= 15;
										tblValueRange.setSize(wid, TBL_HEIGHT);
										adjust = true;
									} else {
										tblValueRange.setSize(rt.width, TBL_HEIGHT);
									}
								}
							});
						}

					}
				});

			}

			InsuranceDTO[] icDTO = null;
			if (shell == null) {
				//Sundry				
				try {
					icDTO = handler.getInsuranceChargeList();
					if (icDTO != null)
						ROW_SIZE = icDTO.length;
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			} else {
				//Quotation				
				if (insuranceDTO != null) {
					icDTO = insuranceDTO;
					if (icDTO != null)
						ROW_SIZE = icDTO.length;
				} else {
					ROW_SIZE = 4;
				}
			}

			// Draw Table
			GridData gd = new GridData();
			gd.horizontalAlignment = GridData.CENTER;

			if (ROW_SIZE <= 8) {
				gd.widthHint = (COLUMN_SIZE - 1) * COLUMN_WIDTH + DEL_COLUMN_WIDTH - 15;
				gd.heightHint = (ROW_SIZE * 18) + 20;
				TBL_HEIGHT = (ROW_SIZE * 18) + 20;
			} else {
				gd.widthHint = (COLUMN_SIZE - 1) * COLUMN_WIDTH + DEL_COLUMN_WIDTH;
				gd.heightHint = (ITEM_MAX * 18) + 2;
				TBL_HEIGHT = (ITEM_MAX * 18) + 20;
				adjust = false;
			}

			// Creating Table
			if (shell != null) {
				
				if(ROW_SIZE <= 8){
				tblValueRange = new Table(composite, SWT.FULL_SELECTION | SWT.HIDE_SELECTION);
				tblValueRange.setBounds(10, 50, 229, 94);
				}else{
					
					tblValueRange = new Table(composite, SWT.FULL_SELECTION | SWT.HIDE_SELECTION);
					tblValueRange.setBounds(10, 50, 245, 94);
				}
				
				
			} else {
				int height = 0;
				if(ROW_SIZE <= 8){
					height = (ROW_SIZE * 18) + 20;
				tblValueRange = new Table(cptLRC, SWT.FULL_SELECTION | SWT.HIDE_SELECTION);
				tblValueRange.setBounds(20, 50, 394, height);
				//System.out.println("wid 394  ht==>"+height);				
				}else{
					height = (ITEM_MAX * 18) + 2;
					tblValueRange = new Table(cptLRC, SWT.FULL_SELECTION | SWT.HIDE_SELECTION);
					tblValueRange.setBounds(20, 50, 410, height);
					//System.out.println("wid 410  ht==>"+height);
				}
			}

			
			
			tblValueRange.setHeaderVisible(true);
			tblValueRange.setLinesVisible(true);
			
			
			tblValueRange.addListener(SWT.MeasureItem, new Listener() {
				public void handleEvent(Event event) {
					event.height = 18;					
				}
			});

			ROW_HEIGHT = tblValueRange.getItemHeight();

			for (int i = 0; i < COLUMN_SIZE; i++) {
				column = new TableColumn(tblValueRange, SWT.CENTER, i);
				column.setText(COLUMN_HEADINGS[i]);

			}
			column.pack();
			for (int i = 0; i < COLUMN_SIZE; i++) {
				if (i != COLUMN_SIZE - 1) {
					tblValueRange.getColumn(i).setWidth(COLUMN_WIDTH);
				} else {
					tblValueRange.getColumn(i).setWidth(DEL_COLUMN_WIDTH);
				}
			}

			{
				ArrayList<String> listCombo = new ArrayList<String>();
				for (int i = 0; i < 200000; i += 5000) {
					listCombo.add(Integer.toString(i));
				}
				for(int i = 200000; i < 500000; i += 50000){
					listCombo.add(Integer.toString(i));
				}
				for(int i = 500000; i < 1000000; i += 100000){
					listCombo.add(Integer.toString(i));
				}
				for(int i = 1000000; i <= 10000000; i += 500000){
					listCombo.add(Integer.toString(i));
				}
				
				int size = listCombo.size();
				COMBO_ITEMS = listCombo.toArray(new String[size]);

				final CCombo[] cbFrom = new CCombo[ROW_SIZE];
				final CCombo[] cbTo = new CCombo[ROW_SIZE];
				final Text[] txtValue = new Text[ROW_SIZE];
				
				// Drawing initial table items
				for (int rowId = 0; rowId < ROW_SIZE; rowId++) {

					final TableItem item = new TableItem(tblValueRange, SWT.NONE);

					// Draw initial From Combo Boxes
					editor = new TableEditor(tblValueRange);
					cbFrom[rowId] = new CCombo(tblValueRange, SWT.READ_ONLY);
					cbFrom[rowId].setItems(COMBO_ITEMS);
					if (icDTO != null) {
						
						cbFrom[rowId].setText(String.valueOf((int)icDTO[rowId].getFromValue()));
					}
					editor.grabHorizontal = true;
					editor.setEditor(cbFrom[rowId], item, 0);
					cbFromList.add(cbFrom[rowId]);

					// Draw initial To Combo Boxes
					editor = new TableEditor(tblValueRange);
					cbTo[rowId] = new CCombo(tblValueRange, SWT.READ_ONLY);
					cbTo[rowId].setItems(COMBO_ITEMS);
					cbTo[rowId].remove("0");
					cbTo[rowId].add("$");
					String toValue = "";
					int toVal = 0;
					if (icDTO != null) {
						toVal = (int)icDTO[rowId].getToValue(); 
						if( toVal == 0){
							toValue = "$";
						}else{
							toValue = String.valueOf(toVal);
						}
						cbTo[rowId].setText(toValue);
					}
					editor.grabHorizontal = true;
					editor.setEditor(cbTo[rowId], item, 1);
					cbToList.add(cbTo[rowId]);

					// Draw initial Value Text Fields
					decimalFormat = new DecimalFormat("0.00");
					editor = new TableEditor(tblValueRange);
					txtValue[rowId] = new Text(tblValueRange, SWT.NONE);
					if (icDTO != null) {
						txtValue[rowId].setText(decimalFormat.format(icDTO[rowId].getInsuranceChargeValue()));
					}
					txtValue[rowId].addVerifyListener(new FloatLimitValidation());
					editor.grabHorizontal = true;
					editor.setEditor(txtValue[rowId], item, 2);
					txtList.add(txtValue[rowId]);

					// Draw initial Delete Buttons
					final Button[] deleteButtons = new Button[ROW_SIZE];
					editor = new TableEditor(tblValueRange);
					deleteButtons[rowId] = new Button(tblValueRange, SWT.PUSH);
					deleteButtons[rowId].setSize(37, 18);
					InputStream stream = ValueRangeComposite.class.getClassLoader().getResourceAsStream(DEL_IMG);
					img = new Image(Display.getDefault(), stream);
					deleteButtons[rowId].setImage(img);
					deleteButtons[rowId].computeSize(SWT.DEFAULT, tblValueRange.getItemHeight());
					editor.grabHorizontal = true;
					editor.minimumHeight = deleteButtons[rowId].getSize().y;
					editor.minimumWidth = deleteButtons[rowId].getSize().x;
					deleteButtons[rowId].setText(Integer.toString(rowId));
					editor.setEditor(deleteButtons[rowId], item, 3);

					// Delete Button Action for initial row buttons
					deleteButtons[rowId].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseDown(MouseEvent event) {
							// Disposing initial Table Items
							Button obj = (Button) event.getSource();
							int delete = Integer.parseInt(obj.getText());
							deleteButtons[delete].dispose();
							txtValue[delete].dispose();
							cbFrom[delete].dispose();
							cbTo[delete].dispose();

							txtList.remove(txtValue[delete]);
							cbFromList.remove(cbFrom[delete]);
							cbToList.remove(cbTo[delete]);
							item.dispose();

							// Decreasing Table height for a row
							TBL_HEIGHT -= 18;
							Rectangle rt = tblValueRange.getBounds();
							int len = tblValueRange.getItemCount();
							int temp = len * 18;
							if (temp > (ITEM_MAX * 18)) {
								TBL_HEIGHT += 18;
								tblValueRange.setSize(rt.width, TBL_HEIGHT);
							} else if (temp == (ITEM_MAX * 18)) {
								TBL_HEIGHT += 18;
								int wid = rt.width;
								wid -= 15;
								tblValueRange.setSize(wid, TBL_HEIGHT);
								adjust = true;
							} else {
								tblValueRange.setSize(rt.width, TBL_HEIGHT);
							}
						}
					});
				}
			}
			GridLayout gl = new GridLayout();
			gl.numColumns = 2;

			if (shell != null) {
				shell.open();
				Display display = shell.getDisplay();
				while (!shell.isDisposed()) {
					if (!display.readAndDispatch()) {
						display.sleep();
					}
				}
			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		return composite;
	}

	/**
	 * Getting Insurance Table Values for Quotation
	 * 
	 * @return
	 * @throws Exception
	 */
	public static InsuranceDTO[] getInsuranceTable() throws Exception {
		InsuranceDTO quotInsurance = null;
		ArrayList<InsuranceDTO> list = new ArrayList<InsuranceDTO>();

		try {
			int len = tblValueRange.getItemCount();
			for (int i = 0; i < len; i++) {
				if (cbFromList != null && cbFromList.get(i).getText() != "" && cbToList != null
						&& cbToList.get(i).getText() != "" && txtList != null && txtList.get(i).getText() != "") {
					quotInsurance = new InsuranceDTO();
					quotInsurance.setFromValue(Float.parseFloat(cbFromList.get(i).getText()));
					quotInsurance.setToValue(Float.parseFloat(cbToList.get(i).getText()));
					quotInsurance.setInsuranceChargeValue(Float.parseFloat(txtList.get(i).getText()));

					list.add(quotInsurance);
				}
			}

			int size = list.size();
			if (size > 0) {
				return list.toArray(new InsuranceDTO[size]);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		return null;
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {

	}

	@Override
	public void widgetSelected(SelectionEvent event) {

		Object source = event.getSource();
		boolean isProceed = true;

		if (btnSet == source) {

			InsuranceDTO icDTO = null;
			ArrayList<InsuranceDTO> list = new ArrayList<InsuranceDTO>();
			int len = tblValueRange.getItemCount();
			float toValue = 0;
			String toVal = null;
			for (int i = 0; i < len; i++) {
				if (cbFromList != null && cbFromList.get(i).getText() != "" && cbToList != null
						&& cbToList.get(i).getText() != "" && txtList != null && txtList.get(i).getText() != "") {
					
					toVal = cbToList.get(i).getText(); 
					if(toVal.equalsIgnoreCase("$")){
						toValue = 0;
					}else{
						toValue = Float.parseFloat(toVal);
					}
					
					icDTO = new InsuranceDTO();
					icDTO.setFromValue(Float.parseFloat(cbFromList.get(i).getText()));
					icDTO.setToValue(toValue);
					icDTO.setInsuranceChargeValue(Float.parseFloat(txtList.get(i).getText()));

					list.add(icDTO);
				}else{
					if(cbFromList.get(i).getText() == "" && cbToList.get(i).getText() == "" && txtList.get(i).getText() == ""){
					isProceed = true;					
					}else{
						isProceed = false;
						AdminComposite.display("Enter values", STATUS_SUCCESS,SUCCESS_FONT_COLOR);
						break;
					}
				}
			}

			if(isProceed){
			try {
				int size = list.size();
				if (size > 0) {
					InsuranceDTO[] insurance = list.toArray(new InsuranceDTO[size]);	
					boolean isValid = doValidate(insurance);
					//System.out.println("isValid==>"+isValid);
					if (shell == null) {
						//InsuranceDTO[] insurance = list.toArray(new InsuranceDTO[size]);						
						if (isValid){
							handler.setInsuranceChargeList(insurance);
							AdminComposite.display("Insurance updated successfully", STATUS_SUCCESS,SUCCESS_FONT_COLOR);
						}
					} else {						
						insuranceDTO = insurance;
						if (isValid){
							AdminComposite.display("", STATUS_SUCCESS,SUCCESS_FONT_COLOR);
							this.shell.close();
						}
					}

				}
			} catch (Exception exception) {
				AdminComposite.display("Insurance updation failed", STATUS_SUCCESS,
						SUCCESS_FONT_COLOR);
				exception.printStackTrace();
			}
			}
		}

	}

	/**
	 * Method to validate InsuranceDTO
	 * @param insurance
	 */
	private boolean doValidate(InsuranceDTO[] insurance) {

		int len = insurance.length;

		if (insurance[0].getFromValue() != 0) {
			AdminComposite.display("First From Value Should be 0", STATUS_SUCCESS, SUCCESS_FONT_COLOR);

			return false;
		} else {
			for (int i = 0; i < len; i++) {
				if ((i != len - 1) && insurance[i].getToValue() <= insurance[i].getFromValue()) {
					AdminComposite.display("To Value Should be greater than From Value", STATUS_SUCCESS,
							SUCCESS_FONT_COLOR);

					return false;
				}
				if ((i != 0) && insurance[i].getFromValue() != insurance[i - 1].getToValue()) {
					AdminComposite.display("Value Range Overlaps. Please Check ", STATUS_SUCCESS, SUCCESS_FONT_COLOR);

					return false;
				}
			}
		}

		if (insurance[len - 1].getToValue() != 0) {
			AdminComposite.display("Last To Value Should be infinity", STATUS_SUCCESS, SUCCESS_FONT_COLOR);

			return false;
		}

		return true;
	}
}
