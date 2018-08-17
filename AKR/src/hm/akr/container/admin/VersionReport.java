package hm.akr.container.admin;

import java.util.Date;

import hm.akr.common.BeanUtil;
import hm.akr.common.IUIConstants;
import hm.akr.common.sortListener;
import hm.akr.dto.StationsDTO;
import hm.akr.dto.VersionDTO;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class VersionReport extends Composite implements SelectionListener,IUIConstants,Listener{

	AdminCompositeHandler handler = null;
	VersionDTO[] versiondto = null;
	BeanUtil beanutil = null;
	private Shell shell = null;
	private String SERVER_DATE = "";
	private TabFolder tabReport;
	private Button btnPrint;
	private Button btnExportXL;
	private Button btnExportPDF;
	private Display display;
	
	

	

	

	Date reportDate = null;

	private TabItem tabItem3;

	private Canvas versioncanvas;

	private Table tblversion;



	private TableColumn colbranchcode;
	
	private TableColumn  tableColumn1;
	
	private TableColumn  colStationName;

	private TableColumn coltpod1;


	
	public VersionReport(Composite composite, int style) throws Exception {
		super(composite, style);

		shell = composite.getShell();

		handler = new AdminCompositeHandler();
		beanutil = BeanUtil.getInstance();
		SERVER_DATE = beanutil.getServerDate();

	}
	
	public Composite loadcomposite() {
		
		this.setBounds(45,100, 1020,700);
		tabReport = new TabFolder(this, SWT.NONE);
		tabReport.setBounds(50, 25, 820, 500);
		tabReport.addSelectionListener(this);
		
		if(BeanUtil.getDbName() == null || BeanUtil.getDbName().equals(""))
		{tabItem3 = new TabItem(tabReport, SWT.NONE);
			tabItem3.setText("VERSION REPORT");

			versioncanvas = new Canvas(tabReport, SWT.NONE);
			tabItem3.setControl(versioncanvas);
			{
				tblversion = new Table(versioncanvas, SWT.FULL_SELECTION
						| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
				tblversion.setBounds(20, 26, 790, 420);

				tblversion.setHeaderVisible(true);
				tblversion.setLinesVisible(true);

				{
					tableColumn1 = new TableColumn(tblversion, SWT.NONE);
					tableColumn1.setText("SNO");
					tableColumn1.setWidth(45);
					tableColumn1.addListener(SWT.Selection,
							this);

				}
				{
					colbranchcode = new TableColumn(tblversion, SWT.NONE);
					colbranchcode.setText("Branch Code");
					colbranchcode.setWidth(100);
					colbranchcode.addListener(SWT.Selection,
							this);
				}
				{
					colStationName = new TableColumn(tblversion, SWT.NONE);
					colStationName.setText("Station Name");
					colStationName.setWidth(267);
					colStationName.addListener(SWT.Selection,
							this);
				}

				{
					coltpod1 = new TableColumn(tblversion, SWT.NONE);
					coltpod1.setText("Version");
					coltpod1.setWidth(365);
					coltpod1.addListener(SWT.Selection,
							this);
				}

			}
		}

		
		/*{
			btnPrint = new Button(this, SWT.PUSH | SWT.CENTER);
			btnPrint.setText("Print");
			btnPrint.setBounds(529, 530, 60, 23);
			btnPrint.addSelectionListener(this);
		}
		{
			btnExportXL = new Button(this, SWT.PUSH | SWT.CENTER);
			btnExportXL.setText("Export Excel");
			btnExportXL.setBounds(600, 530, 80, 23);		
			btnExportXL.addSelectionListener(this);
		}
		{
			btnExportPDF = new Button(this, SWT.PUSH | SWT.CENTER);
			btnExportPDF.setText("Export PDF");
			btnExportPDF.setBounds(690, 530, 80, 23);		
			btnExportPDF.addSelectionListener(this);
			
		}
		
		/*{
			lblStatusBar = new Label(this, SWT.NONE);
			lblStatusBar.setBounds(60,570 ,300,25);
			lblStatusBar.setFont(STATUS_SUCCESS);
			lblStatusBar.setText("STATUS");
			lblStatusBar.setForeground(LINK_FOCUS_COLOR);
			lblStatusBar.setVisible(true);
		}*/
		
		
		try {
			populateVersionReport();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return this;
		
	}
	
	private void populateVersionReport() throws Exception {
		try {

			if (tblversion != null)
				tblversion.removeAll();
			if (null == versiondto)
				versiondto = beanutil.getVersionDetails();
			StationsDTO[] stations = beanutil.getAvailableStations();

			if (null != versiondto) {
				for (int i = 0; i < versiondto.length; i++) {
					boolean flag = false;
					TableItem[] items = tblversion.getItems();
					if (items.length == 0) {
						TableItem item = new TableItem(tblversion, SWT.NONE);
						item.setText(0, String.valueOf(i + 1));
						item.setText(1, versiondto[i].getBranch_code());
						item.setText(2, getStationName(versiondto[i]
								.getStation_code(), stations));
						item.setText(3, versiondto[i].getVersion_id());

					} else {
						for (int j = 0; j < items.length; j++) {
							String code = getStationCode(items[j].getText(2),
									stations);
							if (null != code)
								if (code
										.equals(versiondto[i].getStation_code())) {
									String id = items[j].getText(3);
									items[j].setText(3, id + ","
											+ versiondto[i].getVersion_id());
									flag = true;
									break;
								}

						}
						if (!flag) {
							TableItem item = new TableItem(tblversion, SWT.NONE);
							item.setText(0, String.valueOf(i + 1));
							item.setText(1, versiondto[i].getBranch_code());
							if (null != versiondto[i].getStation_code()) {
								String name = getStationName(versiondto[i]
										.getStation_code(), stations);
								if (null != name)
									item.setText(2, name);

								item.setText(3, versiondto[i].getVersion_id());
							}
						}
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getStationName(String id, StationsDTO[] stations) {
		if (null != id && null != stations) {
			int size = 0;
			size = stations.length;
			if (size > 0) {
				for (int i = 0; i < size; i++) {
					if (stations[i].getId().equals(id)) {
						return stations[i].getName();
					}
				}
			}
		}
		return null;
	}
	
	private String getStationCode(String name, StationsDTO[] stations) {
		int size = 0;
		size = stations.length;
		if (size > 0) {
			for (int i = 0; i < size; i++) {
				if (stations[i].getName().equals(name)) {
					return stations[i].getId();
				}
			}
		}
		return null;
	}
	
	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void widgetSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleEvent(Event e) {
		// TODO Auto-generated method stub
		int selectionIndex = tabReport.getSelectionIndex();
		TableColumn column = (TableColumn) e.widget;
		
		if (column == tableColumn1) {
			new sortListener().sortByFloat(0, tblversion);
		} else if (column == colbranchcode) {
			new sortListener().sortByString(1, tblversion);
		} else if (column == colStationName) {
			new sortListener().sortByString(2, tblversion);
		}

		
	}
}
