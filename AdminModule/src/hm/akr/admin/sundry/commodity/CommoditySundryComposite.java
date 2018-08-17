package hm.akr.admin.sundry.commodity;

/**
 * 
 */

import hm.akr.admin.sundry.handler.SundryHandler;
import hm.akr.admin.workspace.AdminComposite;
import hm.akr.common.FloatLimitValidation;
import hm.akr.common.IUIConstants;
import hm.akr.dto.ArticleDTO;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.TableEditor;
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
 * 
 * @author
 * 
 */
public class CommoditySundryComposite extends Composite implements IUIConstants {

	private int ROW_HEIGHT;

	private int ROW_SIZE = 1;

	private final int COLUMN_SIZE = 3;

	private final int COLUMN_WIDTH = 100;

	private final int DEL_COLUMN_WIDTH = 17;

	private int TBL_HEIGHT = (ROW_SIZE * 18) + 20;

	private static int ITEM_MAX = 8;

	private static String[] COLUMN_HEADINGS = { "Commodity Name", "Rate/km", "" };

	private Image img = null;

	private static final String DEL_IMG = "hm/akr/resources/delete.PNG";

	private static final String ADD_IMG = "hm/akr/resources/add.PNG";

	private Button btnAdd;

	private Table tblValueRange;

	private TableEditor editor = null;

	private TableColumn column = null;

	private TabFolder tbfCommoditySundry;

	private TabItem tbiCommoditySundry;

	private boolean adjust = true;

	Composite cptCommodity;

	private Group group;

	private Button btnSet;

	ArrayList<CCombo> listCbName = new ArrayList<CCombo>();

	ArrayList<Text> listTxtValue = new ArrayList<Text>();

	SundryHandler handler = null;

	String EMPTY_STRING = "";

	ArticleDTO[] allArticlesDTO = null;

	ArticleDTO[] valuesArticlesDTO = null;

	private DecimalFormat decimalFormat;

	String[] COMBO_ITEMS = null;
	
	//private String[] CB_CUSTOMERS = null;
	
	private String COMM_SETTING = "Commodity Setting";
	
	

	/**
	 * 
	 */
	public CommoditySundryComposite(Composite cpt, int value) {
		super(cpt, value);
		try {
			handler = new SundryHandler();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @return
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
			tbfCommoditySundry = new TabFolder(this, SWT.NONE);
			tbfCommoditySundry.setLayoutData(gd);
			tbfCommoditySundry.setLayout(gl);
			tbfCommoditySundry.addSelectionListener(new SetupAction());

			{
				tbiCommoditySundry = new TabItem(tbfCommoditySundry, SWT.BORDER);
				tbiCommoditySundry.setText(COMM_SETTING);
			}

			cptCommodity = new Composite(tbfCommoditySundry, SWT.BORDER);
			tbiCommoditySundry.setControl(cptCommodity);
			gl = new GridLayout();
			gl.numColumns = 1;
			gl.marginTop = 30;
			gl.marginRight = 250;
			cptCommodity.setLayout(gl);

			
			
			
			
			// Group
			gl = new GridLayout();
			gl.numColumns = 1;
			gl.marginLeft = 40;
			gd = new GridData();
			gd.grabExcessHorizontalSpace = true;
			gd.horizontalAlignment = GridData.END;
			gd.heightHint = 270;
			gd.widthHint = 300;

			group = new Group(cptCommodity, SWT.NONE);
			group.setText("Commodity");
			group.setLayout(gl);
			group.setLayoutData(gd);

			btnSet = new Button(cptCommodity, SWT.NONE);
			GridData btnSetLData = new GridData();
			btnSetLData.horizontalAlignment = GridData.END;
			btnSetLData.widthHint = 40;
			btnSetLData.heightHint = 35;
			btnSet.setLayoutData(btnSetLData);
			btnSet.setText("Set");
			btnSet.addSelectionListener(new SetupAction());

			{
				gd = new GridData();

				gd.horizontalAlignment = GridData.END;
				btnAdd = new Button(group, SWT.NONE);
				InputStream stream = CommoditySundryComposite.class
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
							final CCombo[] cbName = new CCombo[len];
							cbName[rowId] = new CCombo(tblValueRange,
									SWT.READ_ONLY);
							cbName[rowId].setItems(COMBO_ITEMS);
							editor.grabHorizontal = true;
							editor.setEditor(cbName[rowId], item, 0);
							listCbName.add(cbName[rowId]);

							// Draw Distance To Text Field
							editor = new TableEditor(tblValueRange);
							final Text[] txtValue = new Text[len];
							txtValue[rowId] = new Text(tblValueRange, SWT.NONE);
							if (txtValue[rowId].isEnabled()) {
								txtValue[rowId]
										.addVerifyListener(new FloatLimitValidation());
							}

							editor.grabHorizontal = true;
							editor.setEditor(txtValue[rowId], item, 1);
							listTxtValue.add(txtValue[rowId]);

							// Draw Delete Button
							editor = new TableEditor(tblValueRange);

							final Button[] deleteButtons = new Button[len];
							deleteButtons[rowId] = new Button(tblValueRange,
									SWT.PUSH);
							deleteButtons[rowId].setSize(37, 18);
							InputStream stream = CommoditySundryComposite.class
									.getClassLoader().getResourceAsStream(
											DEL_IMG);
							img = new Image(Display.getDefault(), stream);
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
							editor.setEditor(deleteButtons[rowId], item, 2);

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

											txtValue[delete].dispose();

											listTxtValue
													.remove(txtValue[delete]);

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
												adjust = true;
											} else {
												tblValueRange.setSize(rt.width,
														TBL_HEIGHT);
											}

											// Delete
											try {
												AdminComposite.display("", STATUS_SUCCESS,SUCCESS_FONT_COLOR);
												if (!cbName[delete].getText()
														.equals(EMPTY_STRING)) {
													handler
															.deleteCommodity(cbName[delete]
																	.getText());
												}
											} catch (Exception e) {
												e.printStackTrace();
											}
											AdminComposite
													.display(
															"Commoditity Deleted Successfully",
															STATUS_SUCCESS,
															SUCCESS_FONT_COLOR);

											// COMBO_ITEMS
											ArrayList<String> listCombo = new ArrayList<String>();
											for (int i = 0; i < COMBO_ITEMS.length; i++) {
												listCombo.add(COMBO_ITEMS[i]);
											}

											listCombo.add(cbName[delete]
													.getText());
											cbName[delete].dispose();
											int size = listCombo.size();
											COMBO_ITEMS = new String[size];
											for (int k = 0; k < size; k++) {
												COMBO_ITEMS[k] = listCombo
														.get(k);
											}

											Arrays
													.sort(
															COMBO_ITEMS,
															String.CASE_INSENSITIVE_ORDER);

											listCbName.remove(cbName[delete]);

											for (int l = 0; l < listCbName
													.size(); l++) {
												if (listCbName.get(l)
														.isEnabled()) {
													listCbName.get(l).setItems(
															COMBO_ITEMS);
												}
											}

										}
									});
						}
					}
				});

			}

			try {
				allArticlesDTO = handler.getAricleTypes();

				if (allArticlesDTO != null) {
					int len = allArticlesDTO.length;

					ArrayList<String> listItems = new ArrayList<String>();
					ArrayList<ArticleDTO> listArt = new ArrayList<ArticleDTO>();

					for (int i = 0; i < len; i++) {
						if (allArticlesDTO[i].getValue_per_km() > 0) {
							listArt.add(allArticlesDTO[i]);
						} else {
							listItems.add(allArticlesDTO[i].getType());
						}
					}

					int size = listItems.size();
					if (size > 0) {
						COMBO_ITEMS = listItems.toArray(new String[size]);
					}

					size = listArt.size();
					if (size > 0) {
						valuesArticlesDTO = listArt
								.toArray(new ArticleDTO[size]);
						ROW_SIZE = size + 1;
					}
				}

			} catch (Exception exception) {
				exception.printStackTrace();
			}

			createTable(valuesArticlesDTO);

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		return this;
	}

	public void createTable(ArticleDTO[] valuesArticlesDTO) {
		// Draw Table
		GridData gd = new GridData();
		gd.horizontalAlignment = GridData.CENTER;

		if (ROW_SIZE <= 8) {
			gd.widthHint = (COLUMN_SIZE - 1) * COLUMN_WIDTH + DEL_COLUMN_WIDTH
					- 15;
			gd.heightHint = (ROW_SIZE * 18) + 20;
			TBL_HEIGHT = (ROW_SIZE * 18) + 20;
		} else {
			gd.widthHint = (COLUMN_SIZE - 1) * COLUMN_WIDTH + DEL_COLUMN_WIDTH;
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
			if (i != COLUMN_SIZE - 1) {
				tblValueRange.getColumn(i).setWidth(COLUMN_WIDTH);
			} else {
				tblValueRange.getColumn(i).setWidth(DEL_COLUMN_WIDTH);
			}
		}

		{
			decimalFormat = new DecimalFormat("0.00");
			final CCombo[] cbName = new CCombo[ROW_SIZE];
			final Text[] txtValue = new Text[ROW_SIZE];
			final Text[] txtId = new Text[ROW_SIZE];
			final Button[] deleteButtons = new Button[ROW_SIZE];

			// Drawing initial table items
			for (int rowId = 0; rowId < ROW_SIZE; rowId++) {

				final TableItem item = new TableItem(tblValueRange, SWT.NONE);

				// Draw Distance From Text Field
				editor = new TableEditor(tblValueRange);
				cbName[rowId] = new CCombo(tblValueRange, SWT.READ_ONLY);

				if (rowId != (ROW_SIZE - 1)) {
					cbName[rowId].setEnabled(false);
					if (valuesArticlesDTO != null)
						cbName[rowId].setText(valuesArticlesDTO[rowId]
								.getType());
				} else {
					cbName[rowId].setItems(COMBO_ITEMS);
				}

				editor.grabHorizontal = true;
				editor.setEditor(cbName[rowId], item, 0);
				listCbName.add(cbName[rowId]);

				// Draw Distance To Text Field
				editor = new TableEditor(tblValueRange);
				txtValue[rowId] = new Text(tblValueRange, SWT.NONE);

				if (rowId != (ROW_SIZE - 1)) {
					if (valuesArticlesDTO != null
							&& valuesArticlesDTO[rowId].getValue_per_km() != 0) {
						txtValue[rowId].setText(decimalFormat
								.format(valuesArticlesDTO[rowId]
										.getValue_per_km()));
					}
				}
				txtValue[rowId].addVerifyListener(new FloatLimitValidation());
				editor.grabHorizontal = true;
				editor.setEditor(txtValue[rowId], item, 1);
				listTxtValue.add(txtValue[rowId]);

				// Id
				editor = new TableEditor(tblValueRange);
				txtId[rowId] = new Text(tblValueRange, SWT.NONE);
				if (rowId != (ROW_SIZE - 1)) {
					if (valuesArticlesDTO != null) {
						txtId[rowId].setText(Integer
								.toString(valuesArticlesDTO[rowId]
										.getArticleId()));
					}
				}
				txtId[rowId].setVisible(false);
				editor.grabHorizontal = true;
				editor.setEditor(txtId[rowId], item, 1);

				// Draw initial Delete Buttons
				editor = new TableEditor(tblValueRange);
				deleteButtons[rowId] = new Button(tblValueRange, SWT.PUSH);
				deleteButtons[rowId].setSize(37, 18);
				InputStream stream = CommoditySundryComposite.class
						.getClassLoader().getResourceAsStream(DEL_IMG);
				img = new Image(Display.getDefault(), stream);
				deleteButtons[rowId].setImage(img);
				deleteButtons[rowId].computeSize(SWT.DEFAULT, tblValueRange
						.getItemHeight());
				editor.grabHorizontal = true;
				editor.minimumHeight = deleteButtons[rowId].getSize().y;
				editor.minimumWidth = deleteButtons[rowId].getSize().x;
				deleteButtons[rowId].setText(Integer.toString(rowId));
				editor.setEditor(deleteButtons[rowId], item, 2);

				// Delete Button Action for initial row buttons

				deleteButtons[rowId].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseDown(MouseEvent event) {
						AdminComposite.display("", STATUS_SUCCESS,SUCCESS_FONT_COLOR);
						// Disposing initial Table Items
						Button obj = (Button) event.getSource();
						int delete = Integer.parseInt(obj.getText());
						deleteButtons[delete].dispose();

						txtValue[delete].dispose();

						try {
							handler.deleteCommodity(cbName[delete].getText());
							AdminComposite.display(
									"Commoditity Deleted Successfully",
									STATUS_SUCCESS, SUCCESS_FONT_COLOR);

							// COMBO_ITEMS
							ArrayList<String> listCombo = new ArrayList<String>();
							for (int i = 0; i < COMBO_ITEMS.length; i++) {
								listCombo.add(COMBO_ITEMS[i]);
							}

							listCombo.add(cbName[delete].getText());
							cbName[delete].dispose();
							int size = listCombo.size();
							COMBO_ITEMS = new String[size];
							for (int k = 0; k < size; k++) {
								COMBO_ITEMS[k] = listCombo.get(k);
							}

							Arrays.sort(COMBO_ITEMS,
									String.CASE_INSENSITIVE_ORDER);

							listCbName.remove(cbName[delete]);

							for (int l = 0; l < listCbName.size(); l++) {
								if (listCbName.get(l).isEnabled()) {
									listCbName.get(l).setItems(COMBO_ITEMS);
								}
							}

						} catch (NumberFormatException ne) {
							ne.printStackTrace();
						} catch (Exception exception) {
							exception.printStackTrace();
						}

						txtId[delete].dispose();

						listTxtValue.remove(txtValue[delete]);

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
	}

	/**
	 * 
	 * Setup Action
	 * 
	 */
	public class SetupAction implements SelectionListener {

		@Override
		public void widgetDefaultSelected(SelectionEvent event) {
		}

		@Override
		public void widgetSelected(SelectionEvent event) {
			Object source = event.getSource();
			
			if (source == btnSet) {
				AdminComposite.display("", STATUS_SUCCESS,SUCCESS_FONT_COLOR);
				ArticleDTO artDTO = null;
				ArrayList<ArticleDTO> listNew = new ArrayList<ArticleDTO>();
				ArrayList<ArticleDTO> listOld = new ArrayList<ArticleDTO>();
				int len = tblValueRange.getItemCount();

				try {
					if (allArticlesDTO == null) {
						// Inserting Initial Commodities
						for (int i = 0; i < len; i++) {
							if (listCbName != null
									&& listCbName.get(i).getText() != EMPTY_STRING
									&& listTxtValue != null
									&& listTxtValue.get(i).getText() != EMPTY_STRING) {

								artDTO = new ArticleDTO();
								artDTO.setType(listCbName.get(i).getText());
								artDTO.setValue_per_km(Float
										.parseFloat(listTxtValue.get(i)
												.getText()));
								listNew.add(artDTO);
							}
						}

						int size = listNew.size();
						if (size > 0) {
							handler.insertCommodities(listNew
									.toArray(new ArticleDTO[size]));
							AdminComposite.display(
									"Commoditity Value Inserted Successfully",
									STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						}

					} else {
						for (int i = 0; i < len; i++) {
							boolean newname = true;

							String name = listCbName.get(i).getText();
							String temp = listTxtValue.get(i).getText();

							if (!name.equals(EMPTY_STRING)
									&& !temp.equals(EMPTY_STRING)) {
								float value = Float.parseFloat(temp);

								for (int j = 0; j < allArticlesDTO.length; j++) {

									String availname = allArticlesDTO[j]
											.getType();
									float availvalue = allArticlesDTO[j]
											.getValue_per_km();

									if (name.equals(availname)
											&& value != availvalue) {
										// Updating Commodities
										newname = false;
										artDTO = new ArticleDTO();
										artDTO.setType(name);
										artDTO.setValue_per_km(value);

										listOld.add(artDTO);

										break;
									} else if (name.equals(availname)
											&& value == availvalue) {
										newname = false;
										break;

									}
								}
								if (newname) {
									// Inserting Commodities
									artDTO = new ArticleDTO();
									artDTO.setType(name);
									artDTO.setValue_per_km(value);

									listNew.add(artDTO);
								}
							}

						}

						int oldSize = listOld.size();
						if (oldSize > 0) {
							ArticleDTO[] dto = null;
							dto = listOld.toArray(new ArticleDTO[oldSize]);
							handler.updateCommodities(dto);
							AdminComposite.display(
									"Commodity Successfully updated",
									STATUS_SUCCESS, SUCCESS_FONT_COLOR);
							
							

							ArrayList<String> listCombo = new ArrayList<String>();
							for (int i = 0; i < COMBO_ITEMS.length; i++) {
								listCombo.add(COMBO_ITEMS[i]);
							}

							for (int j = 0; j < oldSize; j++) {
								listCombo.remove(dto[j].getType());
							}

							int size = listCombo.size();
							COMBO_ITEMS = new String[size];
							for (int k = 0; k < size; k++) {
								COMBO_ITEMS[k] = listCombo.get(k);
							}

							Arrays.sort(COMBO_ITEMS,
									String.CASE_INSENSITIVE_ORDER);

							for (int l = 0; l < listCbName.size(); l++) {
								if (listCbName.get(l).getText().equals(
										EMPTY_STRING)) {
									listCbName.get(l).setItems(COMBO_ITEMS);
								} else {
									listCbName.get(l).setEnabled(false);
								}
							}

						}

						int newSize = listNew.size();
						if (newSize > 0) {
							System.out.println("new");
							ArticleDTO[] dto = null;
							dto = listNew.toArray(new ArticleDTO[newSize]);
							handler.insertCommodities(dto);
							AdminComposite.display(
									"Commodity Successfully inserted",
									STATUS_SUCCESS, SUCCESS_FONT_COLOR);

							ArrayList<String> listCombo = new ArrayList<String>();
							for (int i = 0; i < COMBO_ITEMS.length; i++) {
								listCombo.add(COMBO_ITEMS[i]);
							}

							for (int j = 0; j < oldSize; j++) {
								listCombo.remove(dto[j].getType());
							}

							int size = listCombo.size();
							COMBO_ITEMS = new String[size];
							for (int k = 0; k < size; k++) {
								COMBO_ITEMS[k] = listCombo.get(k);
							}

							Arrays.sort(COMBO_ITEMS,
									String.CASE_INSENSITIVE_ORDER);

							for (int l = 0; l < listCbName.size(); l++) {
								System.out.println("fasdfad==>"
										+ listCbName.get(l).getText());
								if (listCbName.get(l).getText().equals(
										EMPTY_STRING)) {
									listCbName.get(l).setItems(COMBO_ITEMS);
								} else {
									listCbName.get(l).setEnabled(false);
								}
							}
						}
					}
				} catch (Exception exception) {
					AdminComposite.display("Commodity Updation Failed",
							STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					exception.printStackTrace();
				}
			}

		}
	}
	
}
