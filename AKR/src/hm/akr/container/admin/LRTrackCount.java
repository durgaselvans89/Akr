package hm.akr.container.admin;

import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import hm.akr.common.BeanUtil;
import hm.akr.common.IUIConstants;
import hm.akr.common.KalendarDialog;
import hm.akr.common.SWTResourceManager;

import hm.akr.dto.VersionDTO;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

public class LRTrackCount extends Composite implements SelectionListener,IUIConstants,Listener{

	
	private Group gpLrTrack;
	private Button btnLRTrackView;
	private Button btnLRTRackToDate;
	private Text txtLRTrackToDate;
	private Label lblLRTrackToDate;
	private Button btnLRTrack;
	private Text txtLRTRackDate;
	private Label lblLRTRackDate;
	private Label lblLrTrackResult;
	private Label lblLrtrackDisplay;
	private Canvas canLRTrack;
	private TabItem tiLRTrack;
	
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
	
	public LRTrackCount(Composite composite, int style) throws Exception {
		super(composite, style);

		shell = composite.getShell();

		handler = new AdminCompositeHandler();
		beanutil = BeanUtil.getInstance();
		SERVER_DATE = beanutil.getServerDate();

	}
	
	public Composite loadcomposite(){
		
		
		this.setBounds(45,100, 1020,700);
		tabReport = new TabFolder(this, SWT.NONE);
		tabReport.setBounds(50, 25, 820, 500);
		tabReport.addSelectionListener(this);
		
	
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
	
		
		{
			tiLRTrack = new TabItem(tabReport, SWT.NONE);
			tiLRTrack.setText("LRTrackCount");
			{

				canLRTrack = new Canvas(tabReport, SWT.NONE);
				tiLRTrack.setControl(canLRTrack);
				{
					lblLrtrackDisplay = new Label(
							canLRTrack, SWT.NONE);
					lblLrtrackDisplay
							.setText("No of LR Track");
					lblLrtrackDisplay.setBounds(273, 133,
							72, 30);
				}
				{
					lblLrTrackResult = new Label(
							canLRTrack, SWT.BORDER);
					lblLrTrackResult.setBounds(357, 133,
							60, 25);
				}
				{
					lblLRTRackDate = new Label(canLRTrack,
							SWT.NONE);
					lblLRTRackDate.setText("From Date");
					lblLRTRackDate.setBounds(221, 72, 52,
							30);
				}
				{
					txtLRTRackDate = new Text(canLRTrack,
							SWT.BORDER);
					txtLRTRackDate.setBounds(274, 72, 81,
							22);
					txtLRTRackDate.setEditable(false);
					DateFormat dateFormat = new SimpleDateFormat(
							"dd-MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat
							.format(currentDate);

					if (SERVER_DATE != null) {
						txtLRTRackDate.setText(SERVER_DATE);
					} else {
						txtLRTRackDate.setText(date);
					}
				}
				{
					btnLRTrack = new Button(canLRTrack,
							SWT.PUSH | SWT.CENTER);
					//btnLRTrack.setText("Go");
					btnLRTrack.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btnLRTrack.setBounds(362, 71, 33, 30);
					btnLRTrack
							.addSelectionListener(this);

				}
				{
					lblLRTrackToDate = new Label(
							canLRTrack, SWT.NONE);
					lblLRTrackToDate.setText("To Date");
					lblLRTrackToDate.setBounds(422, 73, 43,
							27);
				}
				{
					txtLRTrackToDate = new Text(canLRTrack,
							SWT.BORDER);
					txtLRTrackToDate.setBounds(472, 74, 80,
							24);
					txtLRTrackToDate.setEditable(false);
					DateFormat dateFormat = new SimpleDateFormat(
							"dd-MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat
							.format(currentDate);

					if (SERVER_DATE != null) {
						txtLRTrackToDate
								.setText(SERVER_DATE);
					} else {
						txtLRTrackToDate.setText(date);
					}
				}
				{
					btnLRTRackToDate = new Button(
							canLRTrack, SWT.PUSH
									| SWT.CENTER);
					//btnLRTRackToDate.setText("Go");
					btnLRTRackToDate.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btnLRTRackToDate.setBounds(564, 70, 33,
							30);
					btnLRTRackToDate
							.addSelectionListener(this);
				}
				{
					btnLRTrackView = new Button(canLRTrack,
							SWT.PUSH | SWT.CENTER);
					btnLRTrackView.setText("View");
					btnLRTrackView.setBounds(603, 70, 37,
							30);
					btnLRTrackView
							.addSelectionListener(this);
				}
				{
					gpLrTrack = new Group(canLRTrack,
							SWT.NONE);
					GridLayout gpLrTrackLayout = new GridLayout();
					gpLrTrackLayout.makeColumnsEqualWidth = true;
					gpLrTrack.setLayout(gpLrTrackLayout);
					gpLrTrack.setText("Lr Track Details");
					gpLrTrack.setBounds(195, 35, 478, 142);
				}
			}
		}
		
		
		return this;
	}
	
	
	private void displayError(String errorText) {
		MessageBox messageBox = new MessageBox(shell, SWT.OK);
		messageBox.setMessage(errorText);
		messageBox.setText("Alert");
		messageBox.open();
	}
	
	private boolean isValidDate(Object obj) {

		try {

			SimpleDateFormat form = new SimpleDateFormat("dd-MM-yyyy");
			Date date = form.parse(obj.toString());
			if (date.after(form.parse(SERVER_DATE))) {
				displayError("Date exceeds today's Date,Please select another Date");
				return false;
			} else if (date.before(form.parse("25-09-2009"))) {
				displayError("Date is Wrong,Please select another Date");
				return false;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	
	private void populateLRTRackCount() {

		try {
			try {
				if (null != txtLRTRackDate && !txtLRTRackDate.equals("")
						&& null != txtLRTrackToDate
						&& !txtLRTrackToDate.equals("")) {
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Date date = null;
					Date to_date = null;
					date = dateFormat.parse(txtLRTRackDate.getText());
					to_date = dateFormat.parse(txtLRTrackToDate.getText());
					if (handler != null) {
						int result = handler.getLRTrackCount(date, to_date);
						
						lblLrTrackResult.setText(String.valueOf(result));
					}

				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void widgetSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub
		
		Object source = arg0.getSource();
		
		if (btnLRTrack == source) {
			KalendarDialog cd = new KalendarDialog(shell);
			Object obj = cd.open();
			if (obj != null) {
				boolean isTrue = isValidDate(obj);
				if (isTrue)
					txtLRTRackDate.setText(obj.toString());
			}

		} else if (btnLRTRackToDate == source) {
			KalendarDialog cd = new KalendarDialog(shell);
			Object obj = cd.open();
			if (obj != null) {
				boolean isTrue = isValidDate(obj);
				if (isTrue)
					txtLRTrackToDate.setText(obj.toString());
			}

		}

		else if (btnLRTrackView == source) {

			populateLRTRackCount();
		}
		
		
	}

	@Override
	public void handleEvent(Event e) {
		// TODO Auto-generated method stub
		TableColumn column = (TableColumn) e.widget;
		
	}

}
