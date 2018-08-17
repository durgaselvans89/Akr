package hm.akr.admin.sundry.special;

/**
 * 
 */

import hm.akr.admin.sundry.handler.SundryHandler;
import hm.akr.admin.workspace.AdminComposite;
import hm.akr.common.FloatLimitExtended;
import hm.akr.common.FloatLimitValidation;
import hm.akr.common.IUIConstants;
import hm.akr.common.NumericExtended;
import hm.akr.common.NumericValidation;
import hm.akr.dto.SpecialSundryDTO;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

/**
 * @author Class for SpecialSundryComposite
 * 
 */
public class SpecialSundryComposite extends Composite implements IUIConstants {

	private int ROW_HEIGHT;

	private int ROW_SIZE = 5;

	private final int COLUMN_SIZE = 7;

	private final int COLUMN_WIDTH = 85;

	private final int DEL_COLUMN_WIDTH = 17;

	private int TBL_HEIGHT = (ROW_SIZE * 18) + 20;;

	private static int ITEM_MAX = 8;

	private static String[] COLUMN_HEADINGS = { "Distance From", "Distance To",
			"Weight From", "Weight To", "Frieght", "Others", "" };

	private Image img = null;

	private static final String DEL_IMG = "hm/akr/resources/delete.PNG";

	private static final String ADD_IMG = "hm/akr/resources/add.PNG";

	private Button btnAdd;

	private Table tblValueRange;

	private TableEditor editor = null;

	private TableColumn column = null;

	private TabFolder tbfSpecialSundry;

	private TabItem tbiSpecialSundry;

	private boolean adjust = true;

	Composite cptSpecial;

	private Group group;

	private Button btnSet;

	ArrayList<Text> listTxtDFrom = new ArrayList<Text>();

	ArrayList<Text> listTxtDTo = new ArrayList<Text>();

	ArrayList<Text> listTxtWFrom = new ArrayList<Text>();

	ArrayList<Text> listTxtWTo = new ArrayList<Text>();

	ArrayList<Text> listTxtBFT = new ArrayList<Text>();

	ArrayList<Text> listTxtOthers = new ArrayList<Text>();

	SundryHandler handler = null;

	String EMPTY_STRING = "";

	private DecimalFormat decimalFormat;
	
	/**
	 * Constructor for specialSundryComposite
	 */
	public SpecialSundryComposite(Composite cpt, int value) {
		super(cpt, value);
		try {
			handler = new SundryHandler();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to Create SpecialSundryComposite
	 * 
	 * @return Composite
	 */

	public Composite loadComposite() {

		try {
			GridLayout gl = new GridLayout();
			this.setLayout(gl);

			GridData gd = new GridData();
			gd.horizontalSpan = 2;
			gd.horizontalAlignment = GridData.FILL;
			gd.verticalAlignment = GridData.FILL;
			gd.grabExcessHorizontalSpace = true;
			gd.grabExcessVerticalSpace = true;
			tbfSpecialSundry = new TabFolder(this, SWT.NONE);
			tbfSpecialSundry.setLayoutData(gd);
			tbfSpecialSundry.setLayout(gl);

			{
				tbiSpecialSundry = new TabItem(tbfSpecialSundry, SWT.BORDER);
				tbiSpecialSundry.setText("LRC Setting");
			}

			cptSpecial = new Composite(tbfSpecialSundry, SWT.BORDER);

			tbiSpecialSundry.setControl(cptSpecial);
			gl = new GridLayout();
			gl.numColumns = 1;
			gl.marginRight = 50;
			gl.marginTop = 30;
			cptSpecial.setLayout(gl);		

			// Group
			gl = new GridLayout();
			gl.numColumns = 1;
			gl.marginLeft = 40;
			gd = new GridData();

			gd.grabExcessHorizontalSpace = true;
			gd.horizontalAlignment = GridData.END;
			gd.heightHint = 270;
			gd.widthHint = 600;
			group = new Group(cptSpecial, SWT.NONE);
			group.setText("Special Sundry");
			group.setLayout(gl);
			group.setLayoutData(gd);

			btnSet = new Button(cptSpecial, SWT.NONE);
			gd = new GridData();
			gd.heightHint = 30;
			gd.widthHint = 50;
			gd.horizontalAlignment = GridData.END;
			btnSet.setLayoutData(gd);
			btnSet.setText("Set");
			btnSet.addSelectionListener(new SetupAction());

			{
				gd = new GridData();
				gd.horizontalAlignment = GridData.END;
				btnAdd = new Button(group, SWT.NONE);
				InputStream stream = SpecialSundryComposite.class
						.getClassLoader().getResourceAsStream(ADD_IMG);
				img = new Image(Display.getDefault(), stream);
				btnAdd.setImage(img);
				btnAdd.setLayoutData(gd);

				// Add button Action
				btnAdd.addSelectionListener(new SelectionListener() {

					@Override
					public void widgetDefaultSelected(SelectionEvent arg0) {

					}

					@Override
					public void widgetSelected(SelectionEvent arg0) {
						{

							Rectangle rt = tblValueRange.getBounds();
							if (TBL_HEIGHT < ((ITEM_MAX * ROW_HEIGHT) + tblValueRange
									.getHeaderHeight()) + 18) {
								// Increasing Table height
								TBL_HEIGHT += ROW_HEIGHT + 4;
								tblValueRange.setSize(rt.width, TBL_HEIGHT);

							} else {
								if (adjust) {
									// Increasing Table height with vertical
									// bar(15)
									tblValueRange.setSize(rt.width + 15,
											TBL_HEIGHT);
									adjust = false;
								}
							}

							// Adding Table items with the existing Table
							final TableItem item = new TableItem(tblValueRange,
									SWT.NONE);

							int len = tblValueRange.getItems().length;
							int rowId = len - 1;

							// Draw Distance From Text Field
							editor = new TableEditor(tblValueRange);
							final Text[] txtDFrom = new Text[len];
							txtDFrom[rowId] = new Text(tblValueRange, SWT.NONE);
							txtDFrom[rowId]
									.addVerifyListener(new NumericValidation());
							editor.grabHorizontal = true;
							txtDFrom[rowId].setFont(TEXT_FONT);
							editor.setEditor(txtDFrom[rowId], item, 0);
							listTxtDFrom.add(txtDFrom[rowId]);

							// Draw Distance To Text Field
							editor = new TableEditor(tblValueRange);
							final Text[] txtDTo = new Text[len];
							txtDTo[rowId] = new Text(tblValueRange, SWT.NONE);
							txtDTo[rowId]
									.addVerifyListener(new NumericExtended());
							txtDTo[rowId].setFont(TEXT_FONT);
							editor.grabHorizontal = true;
							editor.setEditor(txtDTo[rowId], item, 1);
							listTxtDTo.add(txtDTo[rowId]);

							// Draw Weight From Text Field
							editor = new TableEditor(tblValueRange);
							final Text[] txtWFrom = new Text[len];
							txtWFrom[rowId] = new Text(tblValueRange, SWT.NONE);
							txtWFrom[rowId]
									.addVerifyListener(new FloatLimitValidation());
							editor.grabHorizontal = true;
							editor.setEditor(txtWFrom[rowId], item, 2);
							listTxtWFrom.add(txtWFrom[rowId]);
							txtWFrom[rowId].setFont(TEXT_FONT);

							// Draw Weight To Text Field
							editor = new TableEditor(tblValueRange);
							final Text[] txtWTo = new Text[len];
							txtWTo[rowId] = new Text(tblValueRange, SWT.NONE);
							txtWTo[rowId]
									.addVerifyListener(new FloatLimitExtended());
							editor.grabHorizontal = true;
							editor.setEditor(txtWTo[rowId], item, 3);
							listTxtWTo.add(txtWTo[rowId]);
							txtWTo[rowId].setFont(TEXT_FONT);

							// Draw Fright Value Text Field
							editor = new TableEditor(tblValueRange);
							final Text[] txtBFT = new Text[len];
							txtBFT[rowId] = new Text(tblValueRange, SWT.NONE);
							// txtBFT[rowId].addVerifyListener(new
							// FloatLimitValidation());

							final int i = rowId;
							txtBFT[rowId].addKeyListener(new KeyListener() {

								@Override
								public void keyPressed(KeyEvent arg0) {
									// TODO Auto-generated method stub

								}

								@Override
								public void keyReleased(KeyEvent event) {
									if (event.character == 'a'
											|| event.character == 'A') {
										txtBFT[i].setText("Actual Card Rate");
									}

								}

							});

							editor.grabHorizontal = true;
							editor.setEditor(txtBFT[rowId], item, 4);
							listTxtBFT.add(txtBFT[rowId]);
							txtBFT[rowId].setFont(TEXT_FONT);

							// Draw Others Value Text Field
							editor = new TableEditor(tblValueRange);
							final Text[] txtOthers = new Text[len];
							txtOthers[rowId] = new Text(tblValueRange, SWT.NONE);
							txtOthers[rowId]
									.addVerifyListener(new FloatLimitValidation());
							editor.grabHorizontal = true;
							editor.setEditor(txtOthers[rowId], item, 5);
							listTxtOthers.add(txtOthers[rowId]);
							txtOthers[rowId].setFont(TEXT_FONT);

							// Draw Delete Button
							editor = new TableEditor(tblValueRange);
							final Button[] deleteButtons = new Button[len];
							deleteButtons[rowId] = new Button(tblValueRange,
									SWT.PUSH);
							deleteButtons[rowId].setFont(BUTTON_FONT);
							InputStream stream = SpecialSundryComposite.class
									.getClassLoader().getResourceAsStream(
											DEL_IMG);
							img = new Image(Display.getDefault(), stream);
							deleteButtons[rowId].setSize(37, 18);
							deleteButtons[rowId].setImage(img);
							deleteButtons[rowId].computeSize(SWT.DEFAULT,
									tblValueRange.getItemHeight());
							editor.grabHorizontal = true;
							editor.minimumHeight = deleteButtons[rowId]
									.getSize().y;
							editor.minimumWidth = deleteButtons[rowId]
									.getSize().x;
							deleteButtons[rowId].setText(Integer
									.toString(rowId));
							editor.setEditor(deleteButtons[rowId], item, 6);

							// Delete Button Action
							deleteButtons[rowId]
									.addMouseListener(new MouseAdapter() {
										@Override
										public void mouseDown(MouseEvent event) {

											// Disposing Items
											Button obj = (Button) event
													.getSource();

											int delete = Integer.parseInt(obj
													.getText());
											deleteButtons[delete].dispose();
											txtDFrom[delete].dispose();
											txtDTo[delete].dispose();
											txtBFT[delete].dispose();
											txtWFrom[delete].dispose();
											txtWTo[delete].dispose();
											txtOthers[delete].dispose();

											listTxtDFrom
													.remove(txtDFrom[delete]);
											listTxtDTo.remove(txtDTo[delete]);
											listTxtWFrom
													.remove(txtWFrom[delete]);
											listTxtWTo.remove(txtWTo[delete]);
											listTxtBFT.remove(txtBFT[delete]);
											listTxtOthers
													.remove(txtOthers[delete]);

											item.dispose();

											// Decreasing Table height for a row
											TBL_HEIGHT -= 18;
											Rectangle rt = tblValueRange
													.getBounds();
											int len = tblValueRange
													.getItemCount();
											int temp = len * 18;
											if (temp > (ITEM_MAX * 18)) {
												TBL_HEIGHT += 18;
												tblValueRange.setSize(rt.width,
														TBL_HEIGHT);
											} else if (temp == (ITEM_MAX * 18)) {
												TBL_HEIGHT += 18;
												int wid = rt.width;
												wid -= 15;
												tblValueRange.setSize(wid,
														TBL_HEIGHT);

												/*
												 * if(tblValueRange.getHorizontalBar().getThumb() ==
												 * 511){
												 * tblValueRange.setSize(wid+15,TBL_HEIGHT); }
												 */
												adjust = true;
											} else {
												tblValueRange.setSize(rt.width,
														TBL_HEIGHT);
											}
										}
									});
						}
					}
				});

			}

			SpecialSundryDTO[] ssDTO = null;
			try {
				ssDTO = handler.getSpecialSundrySettings();
				if (ssDTO != null)
					ROW_SIZE = ssDTO.length;
			} catch (Exception exception) {
				exception.printStackTrace();
			}

			// Draw Table
			gd = new GridData();
			gd.horizontalAlignment = GridData.CENTER;

			if (ROW_SIZE <= 8) {
				gd.widthHint = (COLUMN_SIZE - 1) * COLUMN_WIDTH
						+ DEL_COLUMN_WIDTH - 15;
				gd.heightHint = (ROW_SIZE * 18) + 20;
				TBL_HEIGHT = (ROW_SIZE * 18) + 20;
			} else {
				gd.widthHint = (COLUMN_SIZE - 1) * COLUMN_WIDTH
						+ DEL_COLUMN_WIDTH;
				gd.heightHint = (ITEM_MAX * 18) + 2;
				TBL_HEIGHT = (ITEM_MAX * 18) + 20;
				adjust = false;
			}

			tblValueRange = new Table(group, SWT.FULL_SELECTION
					| SWT.HIDE_SELECTION);
			tblValueRange.setHeaderVisible(true);
			tblValueRange.setLinesVisible(true);
			tblValueRange.setLayoutData(gd);

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
				if (i == 4) {
					tblValueRange.getColumn(i).setWidth(100);
				} else if (i == 5) {
					tblValueRange.getColumn(i).setWidth(70);
				} else if (i != COLUMN_SIZE - 1) {
					tblValueRange.getColumn(i).setWidth(COLUMN_WIDTH);
				} else {
					tblValueRange.getColumn(i).setWidth(DEL_COLUMN_WIDTH);
				}
			}

			{
				decimalFormat = new DecimalFormat("0.00");
				final Text[] txtDFrom = new Text[ROW_SIZE];
				final Text[] txtDTo = new Text[ROW_SIZE];
				final Text[] txtWFrom = new Text[ROW_SIZE];
				final Text[] txtWTo = new Text[ROW_SIZE];
				final Text[] txtBFT = new Text[ROW_SIZE];
				final Text[] txtOthers = new Text[ROW_SIZE];
				final Button[] deleteButtons = new Button[ROW_SIZE];
				// Drawing initial table items
				for (int rowId = 0; rowId < ROW_SIZE; rowId++) {

					final TableItem item = new TableItem(tblValueRange,
							SWT.NONE);

					// Draw Distance From Text Field
					editor = new TableEditor(tblValueRange);
					txtDFrom[rowId] = new Text(tblValueRange, SWT.NONE);
					if (ssDTO != null)
						txtDFrom[rowId].setText(String.valueOf(ssDTO[rowId]
								.getMinDistance()));
					txtDFrom[rowId].addVerifyListener(new NumericValidation());
					editor.grabHorizontal = true;
					editor.setEditor(txtDFrom[rowId], item, 0);
					listTxtDFrom.add(txtDFrom[rowId]);
					txtDFrom[rowId].setFont(TEXT_FONT);

					// Draw Distance To Text Field
					editor = new TableEditor(tblValueRange);
					txtDTo[rowId] = new Text(tblValueRange, SWT.NONE);
					txtDTo[rowId].setFont(TEXT_FONT);
					txtDTo[rowId].addVerifyListener(new NumericExtended());
					if (ssDTO != null) {
						if (ssDTO[rowId].getMaxDistance() == 9999) {
							txtDTo[rowId].setText(">");
						} else {
							txtDTo[rowId].setText(String.valueOf(ssDTO[rowId]
									.getMaxDistance()));
						}
					}

					editor.grabHorizontal = true;
					editor.setEditor(txtDTo[rowId], item, 1);
					listTxtDTo.add(txtDTo[rowId]);

					// Draw Weight From Text Field
					editor = new TableEditor(tblValueRange);
					txtWFrom[rowId] = new Text(tblValueRange, SWT.NONE);
					txtWFrom[rowId].setFont(TEXT_FONT);

					if (ssDTO != null)
						txtWFrom[rowId].setText(decimalFormat
								.format(ssDTO[rowId].getMinWeight()));
					txtWFrom[rowId]
							.addVerifyListener(new FloatLimitValidation());
					editor.grabHorizontal = true;
					editor.setEditor(txtWFrom[rowId], item, 2);
					listTxtWFrom.add(txtWFrom[rowId]);

					// Draw Weight To Text Field
					editor = new TableEditor(tblValueRange);
					txtWTo[rowId] = new Text(tblValueRange, SWT.NONE);
					txtWTo[rowId].setFont(TEXT_FONT);

					if (ssDTO != null) {
						if (ssDTO[rowId].getMaxWeight() == 9999) {
							txtWTo[rowId].setText(">");
						} else {
							txtWTo[rowId].setText(decimalFormat
									.format(ssDTO[rowId].getMaxWeight()));
						}

					}
					txtWTo[rowId].addVerifyListener(new FloatLimitExtended());
					editor.grabHorizontal = true;
					editor.setEditor(txtWTo[rowId], item, 3);
					listTxtWTo.add(txtWTo[rowId]);

					// Draw Fright Value Text Field
					editor = new TableEditor(tblValueRange);
					txtBFT[rowId] = new Text(tblValueRange, SWT.NONE);
					txtBFT[rowId].setFont(TEXT_FONT);

					if (ssDTO != null) {
						if (ssDTO[rowId].getBft() == 0) {
							txtBFT[rowId].setText("Actual Card Rate");
						} else {
							txtBFT[rowId].setText(decimalFormat
									.format(ssDTO[rowId].getBft()));
						}

					}
					final int i = rowId;
					txtBFT[rowId].addKeyListener(new KeyListener() {

						@Override
						public void keyPressed(KeyEvent arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void keyReleased(KeyEvent event) {
							if (event.character == 'a'
									|| event.character == 'A') {
								txtBFT[i].setText("Actual Card Rate");
							}

						}

					});
					editor.grabHorizontal = true;
					editor.setEditor(txtBFT[rowId], item, 4);
					listTxtBFT.add(txtBFT[rowId]);

					// Draw Others Value Text Field
					editor = new TableEditor(tblValueRange);
					txtOthers[rowId] = new Text(tblValueRange, SWT.NONE);
					txtOthers[rowId].setFont(TEXT_FONT);

					if (ssDTO != null)
						txtOthers[rowId].setText(decimalFormat
								.format(ssDTO[rowId].getLrc()));
					txtOthers[rowId]
							.addVerifyListener(new FloatLimitValidation());
					editor.grabHorizontal = true;
					editor.setEditor(txtOthers[rowId], item, 5);
					listTxtOthers.add(txtOthers[rowId]);

					// Draw initial Delete Buttons

					editor = new TableEditor(tblValueRange);
					deleteButtons[rowId] = new Button(tblValueRange, SWT.PUSH);
					deleteButtons[rowId].setFont(BUTTON_FONT);
					deleteButtons[rowId].setSize(37, 18);
					InputStream stream = SpecialSundryComposite.class
							.getClassLoader().getResourceAsStream(DEL_IMG);
					img = new Image(Display.getDefault(), stream);
					deleteButtons[rowId].setImage(img);
					deleteButtons[rowId].computeSize(SWT.DEFAULT, tblValueRange
							.getItemHeight());
					editor.grabHorizontal = true;
					editor.minimumHeight = deleteButtons[rowId].getSize().y;
					editor.minimumWidth = deleteButtons[rowId].getSize().x;
					deleteButtons[rowId].setText(Integer.toString(rowId));
					editor.setEditor(deleteButtons[rowId], item, 6);

					// Delete Button Action for initial row buttons
					deleteButtons[rowId].addMouseListener(new MouseAdapter() {
						@Override
						public void mouseDown(MouseEvent event) {
							// Disposing initial Table Items
							Button obj = (Button) event.getSource();

							int delete = Integer.parseInt(obj.getText());
							deleteButtons[delete].dispose();
							txtDFrom[delete].dispose();
							txtDTo[delete].dispose();
							txtBFT[delete].dispose();
							txtWFrom[delete].dispose();
							txtWTo[delete].dispose();
							txtOthers[delete].dispose();

							listTxtDFrom.remove(txtDFrom[delete]);
							listTxtDTo.remove(txtDTo[delete]);
							listTxtWFrom.remove(txtWFrom[delete]);
							listTxtWTo.remove(txtWTo[delete]);
							listTxtBFT.remove(txtBFT[delete]);
							listTxtOthers.remove(txtOthers[delete]);

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

								/*
								 * if(tblValueRange.getHorizontalBar().getThumb() ==
								 * 511){
								 * tblValueRange.setSize(wid+15,TBL_HEIGHT); }
								 */
								adjust = true;
							} else {
								tblValueRange.setSize(rt.width, TBL_HEIGHT);
							}

						}
					});
				}
			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		return this;
	}

	/**
	 * 
	 * Setup Action
	 * 
	 */
	public class SetupAction implements SelectionListener {

		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void widgetSelected(SelectionEvent event) {
			Object source = event.getSource();
			boolean isProceed = true;

			if (source == btnSet) {
				AdminComposite.display("", STATUS_SUCCESS,SUCCESS_FONT_COLOR);
				SpecialSundryDTO ssDTO = null;
				ArrayList<SpecialSundryDTO> list = new ArrayList<SpecialSundryDTO>();
				int len = tblValueRange.getItemCount();
				float toValue = 0;
				String toVal = null;

				for (int i = 0; i < len; i++) {

					if (listTxtDFrom != null
							&& listTxtDFrom.get(i).getText() != EMPTY_STRING
							&& listTxtDTo != null && listTxtWFrom != null
							&& listTxtWFrom.get(i).getText() != EMPTY_STRING
							&& listTxtWTo != null && listTxtBFT != null
							&& listTxtBFT.get(i).getText() != EMPTY_STRING
							&& listTxtOthers != null
							&& listTxtOthers.get(i).getText() != EMPTY_STRING) {

						ssDTO = new SpecialSundryDTO();

						toVal = listTxtDTo.get(i).getText();
						if (!toVal.equals("") && toVal.equalsIgnoreCase(">")) {
							toValue = 9999;
						} else {
							toValue = Float.parseFloat(toVal);
						}

						ssDTO.setMinDistance(Integer.parseInt(listTxtDFrom.get(
								i).getText()));

						ssDTO.setMaxDistance((int) toValue);

						toVal = listTxtWTo.get(i).getText();
						if (!toVal.equalsIgnoreCase("")
								&& toVal.equalsIgnoreCase(">")) {
							toValue = 9999;
						} else {
							toValue = Float.parseFloat(toVal);
						}

						ssDTO.setMinWeight(Float.parseFloat(listTxtWFrom.get(i)
								.getText()));

						ssDTO.setMaxWeight(toValue);

						if (listTxtBFT.get(i).getText().equalsIgnoreCase(
								"Actual Card Rate")) {
							ssDTO.setBft(0);
						} else {
							ssDTO.setBft(Float.parseFloat(listTxtBFT.get(i)
									.getText()));
						}

						ssDTO.setLrc(Float.parseFloat(listTxtOthers.get(i)
								.getText()));

						list.add(ssDTO);
					} else {
						if (listTxtDFrom.get(i).getText() == EMPTY_STRING
								&& listTxtDTo.get(i).getText() == EMPTY_STRING
								&& listTxtWFrom.get(i).getText() == EMPTY_STRING
								&& listTxtWTo.get(i).getText() == EMPTY_STRING
								&& listTxtBFT.get(i).getText() == EMPTY_STRING
								&& listTxtOthers.get(i).getText() == EMPTY_STRING) {
							isProceed = true;
						} else {
							isProceed = false;
							AdminComposite.display("Enter values",
									STATUS_SUCCESS, SUCCESS_FONT_COLOR);
							break;
						}
					}
				}

				if (isProceed) {
					try {
						int size = list.size();
						if (size > 0) {
							SpecialSundryDTO[] special = list
									.toArray(new SpecialSundryDTO[size]);

							boolean isValid = doValidate(special);
							// System.out.println("valid===>"+isValid);
							if (isValid) {
								handler.setSpecialSundrySettings(special);
								AdminComposite.display(
										"Special Sundry Successfully updated",
										STATUS_SUCCESS, SUCCESS_FONT_COLOR);
							} else {
								AdminComposite.display("Value overlaps",
										STATUS_SUCCESS, SUCCESS_FONT_COLOR);
							}

						}
					} catch (Exception exception) {
						AdminComposite.display("Updation Failed",
								STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						exception.printStackTrace();
					}
				}
			} 

		}

	}

	/**
	 * Method to Validate Special Sundry
	 * 
	 * @param special
	 * @return
	 */
	private boolean doValidate(SpecialSundryDTO[] special) {

		int len = special.length;

		boolean result = false;
		
		result = validateWeight(special, len);
		
		if(result)
			result = validateRange(special, len);
		
		

		return result;

	}

	private boolean validateRange(SpecialSundryDTO[] special, int len) {
		
		SpecialSundryDTO temp[] = new SpecialSundryDTO[len];
		boolean isAvail = false;
		
		int k = 1;
		for (int i = 0; i < len; i++) {

		if (i == 0) {
		temp[0] = new SpecialSundryDTO();
		temp[0].setMinDistance(special[i].getMinDistance());
		temp[0].setMaxDistance(special[i].getMaxDistance());
		temp[0].setMinWeight(special[i].getMinWeight());
		temp[0].setMaxWeight(special[i].getMaxWeight());

		continue;
		} else

		for (int j = 0; j < temp.length; j++) {
		if (null != temp[j]) {

		if ((special[i].getMinDistance() >= temp[j]

		.getMinDistance() && special[i]

		.getMinDistance() <= temp[j].getMaxDistance())

		|| (special[i].getMaxDistance() >= temp[j]

		.getMinDistance() && special[i]

		.getMaxDistance() <= temp[j]

		.getMaxDistance())) {


		if ((special[i].getMinWeight() >= temp[j]

		.getMinWeight() && special[i]

		.getMinWeight() <= temp[j].getMaxWeight())

		|| (special[i].getMaxWeight() >= temp[j]


		.getMinWeight() && special[i]


		.getMaxWeight() <= temp[j]


		.getMinWeight())) {

		isAvail = true;

		return !isAvail;


		}

		}



		}
		}
		if (isAvail)
		return !isAvail;
		else {
		temp[k] = new SpecialSundryDTO();
		temp[k].setMinDistance(special[i].getMinDistance());
		temp[k].setMaxDistance(special[i].getMaxDistance());
		temp[k].setMinWeight(special[i].getMinWeight());
		temp[k].setMaxWeight(special[i].getMaxWeight());

		}
		}

		k = 1;
		temp = new SpecialSundryDTO[len];
		for (int i = len - 1; i >= 0; i--) {

		if (i == len - 1) {
		temp[0] = new SpecialSundryDTO();
		temp[0].setMinDistance(special[i].getMinDistance());
		temp[0].setMaxDistance(special[i].getMaxDistance());
		temp[0].setMinWeight(special[i].getMinWeight());
		temp[0].setMaxWeight(special[i].getMaxWeight());

		continue;
		} else

		for (int j = 0; j < temp.length; j++) {
		if (null != temp[j]) {

		if ((special[i].getMinDistance() >= temp[j]

		.getMinDistance() && special[i]

		.getMinDistance() <= temp[j].getMaxDistance())

		|| (special[i].getMaxDistance() >= temp[j]

		.getMinDistance() && special[i]

		.getMaxDistance() <= temp[j]

		.getMaxDistance())) {


		if ((special[i].getMinWeight() >= temp[j]

		.getMinWeight() && special[i]

		.getMinWeight() <= temp[j].getMaxWeight())

		|| (special[i].getMaxWeight() >= temp[j]


		.getMinWeight() && special[i]


		.getMaxWeight() <= temp[j]


		.getMinWeight())) {

		isAvail = true;

		return !isAvail;


		}

		}


		if ((special[i].getMinDistance() < temp[j]

		.getMinDistance() && special[i]

		.getMinDistance() > temp[j].getMaxDistance())

		|| (special[i].getMaxDistance() < temp[j]

		.getMinDistance() && special[i]

		.getMaxDistance() > temp[j]

		.getMaxDistance())) {


		if ((special[i].getMinWeight() < temp[j]

		.getMinWeight() && special[i]

		.getMinWeight() > temp[j].getMaxWeight())

		|| (special[i].getMaxWeight() < temp[j]


		.getMinWeight() && special[i]


		.getMaxWeight() > temp[j]


		.getMinWeight())) {

		isAvail = true;

		return !isAvail;


		}

		}
		}
		}
		if (isAvail)
		return !isAvail;
		else {
		temp[k] = new SpecialSundryDTO();
		temp[k].setMinDistance(special[i].getMinDistance());
		temp[k].setMaxDistance(special[i].getMaxDistance());
		temp[k].setMinWeight(special[i].getMinWeight());
		temp[k].setMaxWeight(special[i].getMaxWeight());

		}

		}

		return true;
		
		/*

		SpecialSundryDTO temp[] = new SpecialSundryDTO[len];
		boolean isAvail = false;

		float defaul = 0;
		int k = 1;
		for (int i = 0; i < len; i++) {

			if (i == 0) {
				temp[0] = new SpecialSundryDTO();
				temp[0].setMinDistance(special[i].getMinDistance());
				temp[0].setMaxDistance(special[i].getMaxDistance());
				temp[0].setMinWeight(special[i].getMinWeight());
				temp[0].setMaxWeight(special[i].getMaxWeight());

				continue;
			} else

				for (int j = 0; j < temp.length; j++) {
					if (null != temp[j])
						if ((special[i].getMinDistance() >= temp[j]
								.getMinDistance() && special[i]
								.getMinDistance() <= temp[j].getMaxDistance())
								|| (special[i].getMaxDistance() >= temp[j]
										.getMinDistance() && special[i]
										.getMaxDistance() <= temp[j]
										.getMaxDistance())) {

							if ((special[i].getMinWeight() >= temp[j]
									.getMinWeight() && special[i]
									.getMinWeight() <= temp[j].getMaxWeight())
									|| (special[i].getMaxWeight() >= temp[j]
											.getMinWeight() && special[i]
											.getMaxWeight() <= temp[j]
											.getMinWeight())) {
								isAvail = true;
								return !isAvail;

							}
						}
				
				
					   if (null != temp[j])
						      if (((special[i].getMinDistance() >= temp[j]
						        .getMinDistance() && special[i]
						        .getMinDistance() <= temp[j].getMaxDistance()) || (temp[j]
						        .getMinDistance() >= special[i]
						        .getMinDistance() && temp[j].getMaxDistance() <= special[i]
						        .getMinDistance()))
						        || ((special[i].getMaxDistance() >= temp[j]
						          .getMinDistance() && special[i]
						          .getMaxDistance() <= temp[j]
						          .getMaxDistance()) || (temp[j]
						          .getMinDistance() >= special[i]
						          .getMaxDistance() && temp[j]
						          .getMaxDistance() <= special[i]
						          .getMaxDistance()))) {

						       if (((special[i].getMinWeight() >= temp[j]
						         .getMinWeight() && special[i]
						         .getMinWeight() <= temp[j].getMaxWeight()) || (temp[j]
						         .getMinWeight() >= special[i]
						         .getMinWeight() && temp[j].getMaxWeight() <= special[i]
						         .getMinWeight()))
						         || ((special[i].getMaxWeight() >= temp[j]
						           .getMinWeight() && special[i]
						           .getMaxWeight() <= temp[j]
						           .getMinWeight()) || (temp[j]
						           .getMinWeight() >= special[i]
						           .getMaxWeight() && temp[j]
						           .getMinWeight() <= special[i]
						           .getMaxWeight()))) {
						        isAvail = true;
						        return !isAvail;

						       }
						      }
					
						
				
				}
			if (isAvail)
				return !isAvail;
			else {
				temp[k] = new SpecialSundryDTO();
				temp[k].setMinDistance(special[i].getMinDistance());
				temp[k].setMaxDistance(special[i].getMaxDistance());
				temp[k].setMinWeight(special[i].getMinWeight());
				temp[k].setMaxWeight(special[i].getMaxWeight());

			}

		}

		return true;
	*/}	

	/**
	 * Validate Weight
	 * 
	 * @param special
	 * @param len
	 * @return
	 */
	private boolean validateWeight(SpecialSundryDTO[] special, int len) {		
			for (int i = 0; i < len; i++) {
				if(special[i].getMaxDistance() <= special[i].getMinDistance()){
					AdminComposite.display(	"Distance To Should be greater than Distance From",
							STATUS_SUCCESS, SUCCESS_FONT_COLOR);

				}else if (special[i].getMaxWeight() <= special[i]
								.getMinWeight()) {
					AdminComposite.display(
							"Weight To Should be greater than Weight From",
							STATUS_SUCCESS, SUCCESS_FONT_COLOR);

					return false;
				}

			}
		
		return true;
	}

}
