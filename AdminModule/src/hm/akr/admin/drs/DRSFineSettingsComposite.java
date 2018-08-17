package hm.akr.admin.drs;

import java.rmi.RemoteException;
import java.util.ArrayList;

import hm.akr.admin.drs.handler.DRSFineCompositeHandler;
import hm.akr.admin.workspace.AdminComposite;
import hm.akr.common.IUIConstants;
import hm.akr.common.NumericValidation;
import hm.akr.dto.StationsDTO;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

/**
 * 
 * @author kibaitachi
 * 
 */
public class DRSFineSettingsComposite extends Composite implements IUIConstants {

	DRSFineCompositeHandler handler = null;

	private Canvas canDRSFine;

	private Table tblDRSFine;
	private Button btnDRSFineSubmit;
	private TableColumn colDRSFineDuration;
	private TableColumn colDRSFineDRSFine;
	private TableColumn colDRSFineStationCode;
	private TableColumn colDRSFineStationName;
	private TableColumn colDRSFineBranchCode;
	private TableColumn colDRSFineSno;

	private TableEditor[] editor1;

	private Text[] txtDuration;

	private TableEditor[] editor2;

	private Text[] txtFine;

	private TableItem item;

	private String EMPTY_STRING = "";

	/**
	 * 
	 * @param shell
	 * @param swtValue
	 * @throws Exception
	 */
	public DRSFineSettingsComposite(Composite composite, int swtValue)
			throws Exception {
		super(composite, swtValue);

		try {
			handler = new DRSFineCompositeHandler();

		} catch (Exception e) {

			throw e;
		}

	}

	/**
	 * 
	 * @return
	 */
	public Composite loadComposite() {

		{
			this.setSize(877, 595);
			{
				canDRSFine = new Canvas(this, SWT.NONE);
				canDRSFine.setBounds(14, 7, 841, 557);
				{
					tblDRSFine = new Table(canDRSFine, SWT.NONE | SWT.BORDER
							| SWT.FULL_SELECTION);
					tblDRSFine.setBounds(80, 31, 630, 482);
					tblDRSFine.setHeaderVisible(true);
					tblDRSFine.setLinesVisible(true);

					{
						colDRSFineSno = new TableColumn(tblDRSFine, SWT.NONE);
						colDRSFineSno.setText("Sno");
						colDRSFineSno.setWidth(48);
					}
					{
						colDRSFineBranchCode = new TableColumn(tblDRSFine,
								SWT.NONE);
						colDRSFineBranchCode.setText("BranchCode");
						colDRSFineBranchCode.setWidth(83);
					}
					{
						colDRSFineStationName = new TableColumn(tblDRSFine,
								SWT.NONE);
						colDRSFineStationName.setText("StationName");
						colDRSFineStationName.setWidth(159);
					}
					{
						colDRSFineStationCode = new TableColumn(tblDRSFine,
								SWT.NONE);
						colDRSFineStationCode.setText("StationCode");
						colDRSFineStationCode.setWidth(115);
					}
					{
						colDRSFineDuration = new TableColumn(tblDRSFine,
								SWT.NONE);
						colDRSFineDuration.setText("Duration(Days)");
						colDRSFineDuration.setWidth(101);
					}
					{
						colDRSFineDRSFine = new TableColumn(tblDRSFine,
								SWT.NONE);
						colDRSFineDRSFine.setText("Fine(Rs)");
						colDRSFineDRSFine.setWidth(99);
					}

					populateDRSFineTable();
				}
				{
					btnDRSFineSubmit = new Button(canDRSFine, SWT.PUSH
							| SWT.CENTER);
					btnDRSFineSubmit.setText("Submit");
					btnDRSFineSubmit.setBounds(651, 519, 60, 31);
					btnDRSFineSubmit
							.addSelectionListener(new DRSSelectionListener());
				}
			}

		}

		return this;

	}

	/**
	 * 
	 * @author kibaitachi
	 * 
	 */
	public class DRSSelectionListener implements SelectionListener {

		@Override
		public void widgetDefaultSelected(SelectionEvent event) {

		}

		@Override
		public void widgetSelected(SelectionEvent event) {
			try {
				Object source = event.getSource();
				if (source == btnDRSFineSubmit) {
					if (tblDRSFine != null) {
						TableItem[] item = tblDRSFine.getItems();
						int len = item.length;
						int duration = 0;
						int fine = 0;
						//StationsDTO[] drsfine = new StationsDTO[len];
						StationsDTO drsfine = null;
						ArrayList<StationsDTO> list  = new ArrayList<StationsDTO>();
						for (int i = 0; i < len; i++) {							
							if (!txtDuration[i].getText().equals(EMPTY_STRING) && 
									!txtFine[i].getText().equals(EMPTY_STRING)) {
							drsfine = new StationsDTO();							
							
							duration = Integer.parseInt(txtDuration[i]
										.getText());							
							
							fine = Integer.parseInt(txtFine[i].getText());							

							drsfine.setDrs_duration(duration);
							drsfine.setDrs_fine(fine);
							drsfine.setId(item[i].getText(3));
							
							list.add(drsfine);
							}
						}
						
						int size = list.size();
						if (size > 0) {
							StationsDTO[] stations = list.toArray(new StationsDTO[size]);
							handler.setDRSFineSettings(stations);
							AdminComposite.display("DRS Fine Settings Saved", STATUS_SUCCESS,SUCCESS_FONT_COLOR);
						}

					}
				}
			}catch (Exception e) {
				AdminComposite.display("DRS Fine Settings Not Saved", STATUS_SUCCESS,SUCCESS_FONT_COLOR);
				e.printStackTrace();
			}

		}

	}

	/**
	 * 
	 */
	private void populateDRSFineTable() {

		try {
			int rows = 0;
			StationsDTO[] row_data = handler.getStations();
			if (null != row_data) {
				rows = row_data.length;
			}
			if (rows > 0) {
				editor1 = new TableEditor[rows];
				txtDuration = new Text[rows];

				editor2 = new TableEditor[rows];
				txtFine = new Text[rows];

				// Drawing initial table items
				for (int rowId = 0; rowId < rows; rowId++) {

					item = new TableItem(tblDRSFine, SWT.NONE);

					item.setText(0, Integer.toString((rowId + 1)));

					// First Column station
					item.setText(1, (((row_data[rowId]))
							.getBranch_code()));

					item.setText(2, (((row_data[rowId]))
							.getName()));

					item
							.setText(3, (((row_data[rowId]))
									.getId()));

					// Draw Text Field
					editor1[rowId] = new TableEditor(tblDRSFine);
					txtDuration[rowId] = new Text(tblDRSFine, SWT.NONE);
					txtDuration[rowId]
							.addVerifyListener(new NumericValidation());
					editor1[rowId].grabHorizontal = true;
					editor1[rowId].setEditor(txtDuration[rowId], item, 4);
					txtDuration[rowId]
							.setText(row_data[rowId].getBranch_code());

					// Draw Text Field
					editor2[rowId] = new TableEditor(tblDRSFine);
					txtFine[rowId] = new Text(tblDRSFine, SWT.NONE);
					txtFine[rowId].addVerifyListener(new NumericValidation());
					editor2[rowId].grabHorizontal = true;
					editor2[rowId].setEditor(txtFine[rowId], item, 5);

				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
