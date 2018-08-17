package hm.akr.admin.sundry;

import hm.akr.admin.distance.handler.DistanceCompositeHandler;
import hm.akr.admin.workspace.AdminComposite;
import hm.akr.common.BeanUtil;
import hm.akr.common.FloatLimitValidation;
import hm.akr.common.FloatValidation;
import hm.akr.common.IUIConstants;
import hm.akr.common.NumericValidation;
import hm.akr.dto.RegularSundryDTO;
import hm.akr.dto.StationsDTO;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Properties;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class ManageSundryComposite extends Composite implements IUIConstants {

	Properties properties = null;

	private Table tblManageSundry;

	private TableColumn column;

	private TableItem item;

	private Combo cbSB = null;

	private Label label1;

	private Canvas canvas2;

	DistanceCompositeHandler handler = null;

	private String[] column_head = { "Station", "BPI", "LRC", "GSC", "Min Freight", "Min Weight", "BFI", "BFD" };

	private TableEditor editor;

	private Text[] txtBPI;

	private Text[] txtLRC;

	private Text[] txtGSC;
	
	private Text[] txtMinFt;

	private Text[] txtMinWt;
	
	private Text[] txtBFI;

	private Text[] txtBFD;


	private Button btnUpdate = null;

	private DecimalFormat decimalFormat;

	/**
	 * 
	 * @param shell
	 * @param swtValue
	 * @throws Exception
	 */
	public ManageSundryComposite(Composite composite, int swtValue)
			throws Exception {
		super(composite, swtValue);

		try {

			handler = new DistanceCompositeHandler();

		} catch (Exception e) {

			throw e;
		}

	}

	/**
	 * 
	 * @return
	 */
	public Composite loadComposite() {
		try {
			{
				this.setSize(450, 400);

			}
			{
				canvas2 = new Canvas(this, SWT.NONE);
				canvas2.setBounds(10, 0, 800, 583);
				Label lblHead = new Label(canvas2, SWT.NONE);
				lblHead.setText("Manage Sundry");
				lblHead.setBounds(625, 10, 150, 30);
				lblHead.setFont(HEAD_FONT);
				// lblHead.setForeground(HEAD_FONT_COLOR);

				{
					label1 = new Label(canvas2, SWT.NONE);
					label1.setText("Select Branch");
					label1.setBounds(10, 15, 75, 15);
					label1.setFont(LABLE_FONT);
				}

				/*
				 * { chATED = new Button(canvas2, SWT.CHECK | SWT.LEFT);
				 * chATED.setText("Allow To Edit Distance");
				 * chATED.setBounds(677, 1, 131, 20); }
				 */

				{
					cbSB = new Combo(canvas2, SWT.DROP_DOWN | SWT.READ_ONLY);
					cbSB.setBounds(85, 13, 233, 10);
					cbSB.setFont(COMBO_FONT);
					cbSB.addKeyListener(new KeyListener() {

						@Override
						public void keyPressed(KeyEvent e) {

							String selectedText = cbSB.getText();
							if (e.keyCode == SWT.ARROW_UP
									|| e.keyCode == SWT.ARROW_DOWN
									|| e.keyCode == SWT.CR)
								return;

							if (e.character == '\b') {
								int len = selectedText.length();

								if (len > 1)
									selectedText = selectedText.substring(0,
											len - 2);
								else
									selectedText = "";

								cbSB.remove(0, cbSB.getItemCount() - 1);
								populateBranches();
							} else if (e.keyCode == 32
									|| (e.keyCode > 64 && e.keyCode < 91)
									|| (e.keyCode > 96 && e.keyCode < 123)) {
								selectedText += e.character;
							} else {
								e.doit = false;
								return;
							}

							if (selectedText.length() > 0) {
								selectedText = selectedText.toLowerCase();

								String[] items = cbSB.getItems();
								int len = items.length;

								int startIndex = -1;
								for (int i = 0; i < len; i++) {
									String temp = items[i].toLowerCase();
									if (temp.contains(selectedText)) {
										startIndex = i;
										break;
									}
								}

								if (startIndex == -1) {
									e.doit = false;
								} else {
									cbSB.remove(0, len - 1);

									for (int i = startIndex; i < len; i++) {
										String temp = items[i].toLowerCase();
										if (temp.contains(selectedText)) {
											cbSB.add(items[i]);
										}
									}
									if (selectedText.length() == 1) {
										showPopup(cbSB, true);
									}
								}
							}
						}

						@Override
						public void keyReleased(KeyEvent e) {
							String temp = cbSB.getText();
							int len = temp.length();
							if (len == 0) {
								cbSB.remove(0, cbSB.getItemCount() - 1);
								populateBranches();
							}
						}
					});
					cbSB.addSelectionListener(new SundryActionListener());
					populateBranches();
				}
				{

					String value = cbSB.getItem(0);
					cbSB.setText(value);
					int index = value.indexOf(" - ");
					String branchCode = value.substring(index + 3);

					handleItemChangeAction(tblManageSundry, branchCode);
					RegularSundryDTO[] sundrydetails = getSundryDetails();
					StationsDTO[] bfdetails = getBFDetails();

					populateManageSundry(sundrydetails, bfdetails);

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;

	}

	/**
	 * 
	 * @param dto
	 */
	private void populateManageSundry(RegularSundryDTO[] dto,
			StationsDTO[] stationdto) {

		decimalFormat = new DecimalFormat("0.00");
		TableItem[] items = tblManageSundry.getItems();
		int size = items.length;
		for (int i = 0; i < size; i++) {
			String scode = items[i].getText(0);
			int index = scode.indexOf("-");
			scode = scode.substring(index + 1).trim();
			if (null != dto) {
				int length = dto.length;
				for (int j = 0; j < length; j++) {
					String code = dto[j].getStationCode();
					if (scode.equalsIgnoreCase(code)) {
						if ((dto[j].getBpi() % 1) == new Float(0)) {
							txtBPI[i].setText(decimalFormat.format(dto[j]
									.getBpi()));
						} else {
							txtBPI[i].setText(String.valueOf(dto[j].getBpi()));
						}

						txtLRC[i].setText(decimalFormat.format(dto[j]
								.getLrCharge()));
						txtGSC[i].setText(decimalFormat.format(dto[j].getGsc()));

						txtMinFt[i].setText(decimalFormat.format(dto[j]
								.getMinFreight()));
						txtMinWt[i].setText(decimalFormat.format(dto[j]
								.getMinWeight()));

						break;
					}
				}

			}
			if (null != stationdto) {
				int length = stationdto.length;
				for (int j = 0; j < length; j++) {
					String code = stationdto[j].getId();
					if (scode.equalsIgnoreCase(code)) {
						txtBFI[i].setText(String.valueOf(stationdto[j]
								.getBf_increment()));
						txtBFD[i].setText(String.valueOf(stationdto[j]
								.getDiscount()));
					
						break;
					}
				}
			}
		}

	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	private RegularSundryDTO[] getSundryDetails() throws Exception {
		RegularSundryDTO[] dto = null;
		if (null != handler) {
			dto = handler.getSundryDetails();
		}

		return dto;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	private StationsDTO[] getBFDetails() throws Exception {
		StationsDTO[] dto = null;
		if (null != handler) {
			dto = handler.getBFDetails();
		}

		return dto;
	}

	private void populateBranches() {
		try {
			StationsDTO[] stations = handler.getAllBranches();
			if (null != stations) {
				int len = stations.length;
				for (int i = 0; i < len; i++) {
					cbSB.add(stations[i].getName() + " - "
							+ stations[i].getId());
				}
			}
		} catch (Exception exception) {

		}
	}

	private static void showPopup(Combo combo, boolean show) {
		if (!combo.isDisposed()) {
			OS.SendMessage(combo.handle, OS.CB_SHOWDROPDOWN, show ? 1 : 0, 0);
		}
	}

	private void createTable(int rows, String[] column_name,
			Object[] row_data) {

		int initial = rows;
		int columns = column_name.length;

		tblManageSundry = new Table(canvas2, SWT.MULTI | SWT.BORDER
				| SWT.FULL_SELECTION);
		tblManageSundry.setLinesVisible(true);
		tblManageSundry.setHeaderVisible(true);

		for (int i = 0; i < columns; i++) {
			column = new TableColumn(tblManageSundry, SWT.NONE);

			if (i == 0) {
				column.setText(column_name[i]);
				column.setWidth(160);
			} else if (i == 1 || i == 2) {
				column.setText(column_name[i]);
				column.setWidth(70);
			} else {
				column.setText(column_name[i]);
				column.setWidth(70);
			}
		}

		// Drawing initial table items

		txtBPI = new Text[rows];
		txtLRC = new Text[rows];
		txtGSC = new Text[rows];
		txtMinFt = new Text[rows];
		txtMinWt = new Text[rows];	
		txtBFI = new Text[rows];
		txtBFD = new Text[rows];
		
		
		for (int rowId = 0; rowId < rows; rowId++) {

			item = new TableItem(tblManageSundry, SWT.NONE);
			String name = ((StationsDTO) row_data[rowId]).getName();
			String code = ((StationsDTO) row_data[rowId]).getId();
			// First column
			item.setText(0, name + " - " + code);

			// Draw Text Field
			editor = new TableEditor(tblManageSundry);

			txtBPI[rowId] = new Text(tblManageSundry, SWT.NONE);
			txtBPI[rowId].setFont(TEXT_FONT);
			txtBPI[rowId].addVerifyListener(new FloatValidation());
			// text[rowId].setText(Integer.toString(rowId));
			// text[rowId].setEnabled(false);
			editor.grabHorizontal = true;
			editor.setEditor(txtBPI[rowId], item, 1);

			// Draw Text Field
			editor = new TableEditor(tblManageSundry);

			txtLRC[rowId] = new Text(tblManageSundry, SWT.NONE);
			txtLRC[rowId].setFont(TEXT_FONT);
			txtLRC[rowId].addVerifyListener(new FloatLimitValidation());

			final int index = rowId;
			// For double click and edit values
			txtLRC[rowId].addMouseListener(new MouseListener() {

				@Override
				public void mouseDoubleClick(MouseEvent event) {

					txtLRC[index]
							.removeVerifyListener(new FloatLimitValidation());
					txtLRC[index].setMessage("DC");
					//System.out.println("sel conut==>"+txtLRC[index].getSelectionCount());
				}

				@Override
				public void mouseDown(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseUp(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

			});

			txtLRC[rowId].addKeyListener(new KeyListener() {

				@Override
				public void keyPressed(KeyEvent arg0) {
					if (txtLRC[index].getMessage().equals("DC")) {

						txtLRC[index].setText("");
						txtLRC[index].setMessage("");

					}

				}

				@Override
				public void keyReleased(KeyEvent arg0) {
					// TODO Auto-generated method stub

				}

			});
			
			txtLRC[rowId].addFocusListener(new FocusListener(){

				@Override
				public void focusGained(FocusEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void focusLost(FocusEvent arg0) {
					txtLRC[index].setMessage("");
					
				}
				
			});

			editor.grabHorizontal = true;
			editor.setEditor(txtLRC[rowId], item, 2);

			// Draw Text Field
			editor = new TableEditor(tblManageSundry);

			txtGSC[rowId] = new Text(tblManageSundry, SWT.NONE);
			txtGSC[rowId].setFont(TEXT_FONT);
			
			txtGSC[rowId].addVerifyListener(new FloatLimitValidation());
			// For double click and edit values
			txtGSC[rowId].addMouseListener(new MouseListener() {

				@Override
				public void mouseDoubleClick(MouseEvent event) {

					txtGSC[index]
							.removeVerifyListener(new FloatLimitValidation());
					txtGSC[index].setMessage("DC");
					//System.out.println("sel conut==>"+txtLRC[index].getSelectionCount());
				}

				@Override
				public void mouseDown(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseUp(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

			});

			txtGSC[rowId].addKeyListener(new KeyListener() {

				@Override
				public void keyPressed(KeyEvent arg0) {
					if (txtGSC[index].getMessage().equals("DC")) {

						txtGSC[index].setText("");
						txtGSC[index].setMessage("");

					}

				}

				@Override
				public void keyReleased(KeyEvent arg0) {
					// TODO Auto-generated method stub

				}

			});

			txtGSC[rowId].addFocusListener(new FocusListener(){

				@Override
				public void focusGained(FocusEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void focusLost(FocusEvent arg0) {
					txtGSC[index].setMessage("");
					
				}
				
			});
			
			editor.grabHorizontal = true;
			editor.setEditor(txtGSC[rowId], item, 3);

			
			// Draw Text Field
			editor = new TableEditor(tblManageSundry);
			txtMinFt[rowId] = new Text(tblManageSundry, SWT.NONE);
			txtMinFt[rowId].setFont(TEXT_FONT);
			txtMinFt[rowId].addVerifyListener(new FloatLimitValidation());
			// For double click and edit values
			txtMinFt[rowId].addMouseListener(new MouseListener() {

				@Override
				public void mouseDoubleClick(MouseEvent event) {

					txtMinFt[index]
							.removeVerifyListener(new FloatLimitValidation());
					txtMinFt[index].setMessage("DC");
				}

				@Override
				public void mouseDown(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseUp(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

			});

			txtMinFt[rowId].addKeyListener(new KeyListener() {

				@Override
				public void keyPressed(KeyEvent arg0) {
					if (txtMinFt[index].getMessage().equals("DC")) {

						txtMinFt[index].setText("");
						txtMinFt[index].setMessage("");

					}

				}

				@Override
				public void keyReleased(KeyEvent arg0) {
					// TODO Auto-generated method stub

				}

			});
			
			txtMinFt[rowId].addFocusListener(new FocusListener(){

				@Override
				public void focusGained(FocusEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void focusLost(FocusEvent arg0) {
					txtMinFt[index].setMessage("");
					
				}
				
			});

			editor.grabHorizontal = true;
			editor.setEditor(txtMinFt[rowId], item, 4);

			// Draw Text Field
			editor = new TableEditor(tblManageSundry);
			txtMinWt[rowId] = new Text(tblManageSundry, SWT.NONE);
			txtMinWt[rowId].setFont(TEXT_FONT);
			txtMinWt[rowId].addVerifyListener(new FloatLimitValidation());

			// For double click and edit values
			txtMinWt[rowId].addMouseListener(new MouseListener() {

				@Override
				public void mouseDoubleClick(MouseEvent event) {

					txtMinWt[index]
							.removeVerifyListener(new FloatLimitValidation());
					txtMinWt[index].setMessage("DC");
				}

				@Override
				public void mouseDown(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseUp(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

			});

			txtMinWt[rowId].addKeyListener(new KeyListener() {

				@Override
				public void keyPressed(KeyEvent arg0) {
					if (txtMinWt[index].getMessage().equals("DC")) {

						txtMinWt[index].setText("");
						txtMinWt[index].setMessage("");
					}

				}

				@Override
				public void keyReleased(KeyEvent arg0) {
					// TODO Auto-generated method stub

				}

			});

			txtMinWt[rowId].addFocusListener(new FocusListener(){

				@Override
				public void focusGained(FocusEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void focusLost(FocusEvent arg0) {
					txtMinWt[index].setMessage("");
					
				}
				
			});
			
			editor.grabHorizontal = true;
			editor.setEditor(txtMinWt[rowId], item, 5);
			
			// Draw BFI Text Field
			editor = new TableEditor(tblManageSundry);

			txtBFI[rowId] = new Text(tblManageSundry, SWT.NONE);
			txtBFI[rowId].setFont(TEXT_FONT);
			txtBFI[rowId].addVerifyListener(new NumericValidation());
			editor.grabHorizontal = true;
			editor.setEditor(txtBFI[rowId], item, 6);

			// Draw BFD Text Field
			editor = new TableEditor(tblManageSundry);
			txtBFD[rowId] = new Text(tblManageSundry, SWT.NONE);
			txtBFD[rowId].setFont(TEXT_FONT);
			txtBFD[rowId].addVerifyListener(new NumericValidation());
			editor.grabHorizontal = true;
			editor.setEditor(txtBFD[rowId], item, 7);

			

		}

		columns = columns * column.getWidth();
		columns += 205;
		int item_height = tblManageSundry.getItemHeight();
		rows = (rows * item_height) + 23;

		if (rows < 430 && initial > 10) {
			tblManageSundry.setBounds(10, 45, 660, rows + 12);
		} else if (rows > 430 && initial > 10) {
			tblManageSundry.setBounds(10, 45, 675, 430);
		} else {
			tblManageSundry.setBounds(10, 45, 660, rows + 12);
		}

		if (btnUpdate != null) {
			btnUpdate.dispose();
		}
		Rectangle rt = tblManageSundry.getBounds();
		btnUpdate = new Button(canvas2, SWT.PUSH);
		btnUpdate.setText("Update");
		btnUpdate.setBounds(rt.width - 40, rt.height + 47, 54, 24);
		btnUpdate.addSelectionListener(new SundryActionListener());
	}

	/**
	 * 
	 * @param reftable
	 * @param branch
	 */
	private void handleItemChangeAction(Table reftable, String branch) {

		int index = cbSB.getSelectionIndex();
		String name = null;

		if (index > -1) {
			name = cbSB.getItem(index);

		} else {
			name = cbSB.getText();
			if (name.trim().length() == 0)
				name = null;
		}
		try {
			if (null != name) {
				if (null != reftable)
					reftable.dispose();
				StationsDTO rowDTO[] = null;
				rowDTO = handler.getStations(branch);
				int rows = rowDTO.length;
				Object[] rowData = rowDTO;
				createTable(rows, column_head, rowData);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public class SundryActionListener implements SelectionListener {

		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void widgetSelected(SelectionEvent event) {
			// TODO Auto-generated method stub
			Object source = event.getSource();

			if (cbSB == source) {
				try {
					String value = cbSB.getText();
					int index = value.indexOf(" - ");
					String branchCode = value.substring(index + 3);
					handleItemChangeAction(tblManageSundry, branchCode);
					RegularSundryDTO[] sundrydetails = getSundryDetails();
					StationsDTO[] bfdetails = getBFDetails();

					populateManageSundry(sundrydetails, bfdetails);

				} catch (Exception exception) {
					exception.printStackTrace();
				}
			} else if (btnUpdate == source) {
				AdminComposite.display("", STATUS_SUCCESS,SUCCESS_FONT_COLOR);
				// Fuctionalities for Update Regular Sundry settings
				try {
					updateSundry();
					
					BeanUtil.isBPI_Changed = true;

				} catch (Exception e) {
					AdminComposite.display("Updation Failed", STATUS_SUCCESS,
							SUCCESS_FONT_COLOR);
					e.printStackTrace();
				}
			}
		}

		/**
		 * 
		 * @throws Exception
		 */
		private void updateSundry() throws Exception {
			try {
				String EMPTY_STRING = "";
				TableItem[] items = tblManageSundry.getItems();
				StationsDTO[] stationdto = null;
				RegularSundryDTO[] regulardto = null;
				StationsDTO sdto = null;
				RegularSundryDTO rdto = null;
				ArrayList<StationsDTO> slist = new ArrayList<StationsDTO>();
				ArrayList<RegularSundryDTO> rlist = new ArrayList<RegularSundryDTO>();

				int size = items.length;

				for (int i = 0; i < size; i++) {

					sdto = new StationsDTO();
					rdto = new RegularSundryDTO();
					String scode = items[i].getText(0);
					int index = scode.indexOf("-");
					scode = scode.substring(index + 2).trim();
					float bpi = 0;
					float lrc = 0;
					float gsc = 0;
					float min_freight = 0;
					float min_weight = 0;				
					int bfincrement = 0;
					int bfdecrement = 0;
					
					
					if (!txtBFI[i].getText().equals(EMPTY_STRING)) {
						bfincrement = Integer.parseInt(txtBFI[i].getText());
					}
					if (!txtBFD[i].getText().equals(EMPTY_STRING)) {
						bfdecrement = Integer.parseInt(txtBFD[i].getText());
					}
					
					sdto.setId(scode);
					sdto.setBf_increment(bfincrement);
					sdto.setDiscount(bfdecrement);									
					slist.add(sdto);

					if (!txtBPI[i].getText().equals(EMPTY_STRING)) {
						bpi = Float.parseFloat(txtBPI[i].getText());
					}
					if (!txtLRC[i].getText().equals(EMPTY_STRING)) {
						lrc = Float.parseFloat(txtLRC[i].getText());
					}
					if (!txtGSC[i].getText().equals(EMPTY_STRING)) {
						gsc = Float.parseFloat(txtGSC[i].getText());
					}
					if (!txtMinFt[i].getText().equals(EMPTY_STRING)) {
						min_freight = Float.parseFloat(txtMinFt[i].getText());
					}
					if (!txtMinWt[i].getText().equals(EMPTY_STRING)) {
						min_weight = Float.parseFloat(txtMinWt[i].getText());
					}
									
					
					rdto.setStationCode(scode);
					rdto.setBpi(bpi);
					rdto.setLrCharge(lrc);
					rdto.setGsc(gsc);
					rdto.setMinFreight(min_freight);
					rdto.setMinWeight(min_weight);
					rlist.add(rdto);

				}

				int length = slist.size();

				if (length > 0) {
					stationdto = slist.toArray(new StationsDTO[size]);
					if (null != stationdto) {
						// Call Method to set BFI and BFD
						if (null != handler) {
							handler.setBFIAndBFD(stationdto);
						}
					}
				}

				length = rlist.size();

				if (length > 0) {
					regulardto = rlist.toArray(new RegularSundryDTO[size]);
					if (null != regulardto) {
						// Call Method to set Sundry except BFI and BFD
						if (null != handler) {							
							handler.updateSundry(regulardto);		
							AdminComposite.display("Updation Successful",
									STATUS_SUCCESS, SUCCESS_FONT_COLOR);

							
							}
						}					
				}
			} catch (NumberFormatException ne) {
				if(ne.getMessage().startsWith("For input string")){
					AdminComposite.display("Please give valid value",
							STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				}							
				
			}

		}
	}

}