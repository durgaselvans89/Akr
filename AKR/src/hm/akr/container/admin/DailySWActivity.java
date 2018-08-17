package hm.akr.container.admin;

import hm.akr.common.BeanUtil;
import hm.akr.common.KalendarDialog;
import hm.akr.common.SWTResourceManager;
import hm.akr.dto.DailyStatusDTO;
import hm.akr.dto.printer.DailyStatusDTODecorator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

public class DailySWActivity extends Composite implements SelectionListener{

	BeanUtil beanutil = null;
	private Shell shell = null;
	private Group group1;
	private Button btnSendReport;
	private Button btnGo;
	private TabFolder tabReport;
	private Canvas canvas8;
	private TabItem tiSendReport;
	private Text txtDate;
	Date reportDate = null;
	private Display display;
	private String SERVER_DATE = "";

	private static final String JRXML_FILE = "hm/akr/resources/printer/DAILYREPORT.jrxml";
	
	
	
	public DailySWActivity(Composite composite, int style) throws Exception {
		super(composite, style);

		shell = composite.getShell();

		//handler = new AdminCompositeHandler();
		beanutil = BeanUtil.getInstance();
		SERVER_DATE = beanutil.getServerDate();
		//user_formate = new SimpleDateFormat("dd-MM-yyyy");

	}
	
	
	
	public Composite loadcomposite(){
		
		
		this.setBounds(45,100, 1020,700);
		tabReport = new TabFolder(this, SWT.NONE);
		tabReport.setBounds(50, 25, 820, 500);
		tabReport.addSelectionListener(this);
		
			
		if(BeanUtil.getDbName() == null || BeanUtil.getDbName().equals("")){
			
					
			tiSendReport = new TabItem(tabReport, SWT.NONE);
			tiSendReport.setText("Daily Software Activity Report");
			canvas8 = new Canvas(tabReport, SWT.BORDER);					
			tiSendReport.setControl(canvas8);	
			
			group1 = new Group(canvas8, SWT.NONE);							
			GridLayout group1Layout = new GridLayout();
			group1Layout.makeColumnsEqualWidth = true;
			group1.setLayout(group1Layout);
			group1.setText("Monitoring Reports");
			group1.setBounds(100,50, 300, 100);
	
			{
				txtDate = new Text(group1, SWT.BORDER);
				txtDate.setBounds(33, 40, 80, 25);
				txtDate.setEditable(false);
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date currentDate = new java.util.Date();
				String date = dateFormat.format(currentDate);
	
				if (SERVER_DATE != null) {
					txtDate.setText(SERVER_DATE);
				} else {
					txtDate.setText(date);
				}
			}
			
			{
				btnGo = new Button(group1, SWT.PUSH | SWT.CENTER);
				btnGo.setImage(SWTResourceManager
						.getImage("hm/akr/resources/Calendar.jpg"));
				btnGo.setBounds(110, 40, 34, 25);
				btnGo.addSelectionListener(new SelectionAdapter(){
					public void widgetSelected(SelectionEvent e){
						KalendarDialog cd = new KalendarDialog(shell);
						
						Object obj = cd.open();
						if (obj != null)
							txtDate.setText(obj.toString());
			
					}
					
				});
			}
			
			{
				btnSendReport = new Button(group1, SWT.PUSH | SWT.CENTER);
				btnSendReport.setText("Generate Report");
				btnSendReport.setBounds(150, 38, 95, 31);
				btnSendReport.addSelectionListener(this);
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


	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub
		
		
	}



	@Override
	public void widgetSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub
		Object source = arg0.getSource();
		
		if (btnSendReport == source) {
			
			if (txtDate.equals("")) {
				displayError("Select Date");
			} else {

				DailyStatusDTO[] dto = null;
				HashMap<String, String> hashMap = new HashMap<String, String>();
				hashMap.put("Title", "DAILYSTATUS");
				hashMap.put("Date", txtDate.getText());

				ArrayList<DailyStatusDTODecorator> list = new ArrayList<DailyStatusDTODecorator>();
				try {

					DateFormat dateFormat = new SimpleDateFormat(
							"dd-MM-yyyy");

					try {
						reportDate = dateFormat.parse(txtDate.getText());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					dto = beanutil.getDailyStatus(reportDate);

					if (null != dto) {
						int size = dto.length;
						for (int i = 0; i < size; i++) {
							list.add(new DailyStatusDTODecorator(dto[i],
									i + 1));
						}
					}
					if (null != beanutil) {
						boolean sent = beanutil.sendMessage(list,
								JRXML_FILE, hashMap);
						if (sent) {
							displayError("Report Mail Sent Successfully");
						}
					}

				} catch (Exception exception) {
					exception.printStackTrace();
				}

			}

		}
		
	}
	
}
