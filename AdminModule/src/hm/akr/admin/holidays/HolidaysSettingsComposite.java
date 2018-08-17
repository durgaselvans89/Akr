package hm.akr.admin.holidays;

import hm.akr.admin.holidays.handler.HolidaysSettingsCompositeHandler;
import hm.akr.admin.workspace.AdminComposite;
import hm.akr.admin.workspace.SWTResourceManager;
import hm.akr.common.BeanUtil;
import hm.akr.common.IUIConstants;
import hm.akr.common.KalendarDialog;
import hm.akr.dto.ServiceTaxDTO;


import java.io.InputStream;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.internal.ole.win32.ISpecifyPropertyPages;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
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
 * @author kibaitachi
 * 
 */
public class HolidaysSettingsComposite extends Composite implements
		IUIConstants {

	private Canvas canHolidays;
	
	private Text txtDate;
	
	private Button bntDate;
	
	private Text txtHolidayDesc;
	
	private Group gpHolidays;
	
	private Button btnHolidaySubmit;
	
	private Label lblHolidayDesc;
	
	private Label lblDate;
	
	private Button yearprev;
	
	private Button yearNext; 
	
	private CLabel yeardisp;

	private Date nowDate;
	
	private Table tblHoliday;
	
	private TableColumn coldateselect;
	
	private TableColumn coldesc;
	
	private TableColumn coldelete;
	
	BeanUtil beanutil = null;
	
	private Button btnAdd;
	
	private Button btndel;
	
	private Image img = null;
	
	private HolidaysSettingsCompositeHandler handler;
	
	private static final String DEL_IMG = "hm/akr/resources/delete.PNG";

	private static final String ADD_IMG = "hm/akr/resources/add.PNG";
	
	ServiceTaxDTO[] dto = null;


	/**
	 * 
	 * @param shell
	 * @param swtValue
	 * @throws Exception
	 */
	public HolidaysSettingsComposite(Composite composite, int swtValue)
			throws Exception {
		super(composite, swtValue);

		try {
			Calendar now = Calendar.getInstance();
			beanutil = BeanUtil.getInstance();
			handler = new HolidaysSettingsCompositeHandler();
			nowDate = new Date(now.getTimeInMillis());

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
				canHolidays = new Canvas(this, SWT.NONE);
				canHolidays.setBounds(0, 0, 841, 757);
				/*{
					lblDate = new Label(canHolidays, SWT.NONE);
					lblDate.setText("Select Date");
					lblDate.setBounds(167, 119, 60, 19);
				}
				{
					txtDate = new Text(canHolidays, SWT.BORDER);
					txtDate.setBounds(230, 118, 100, 24);
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					txtDate.setEnabled(false);
					txtDate.setText(date);
				}
				{
					bntDate = new Button(canHolidays, SWT.NONE);					
					bntDate.setBounds(333, 117, 26, 23);
					bntDate.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					bntDate.addSelectionListener(new HolidayListeners());
				}
				{
					lblHolidayDesc = new Label(canHolidays, SWT.NONE);
					lblHolidayDesc.setText("Description");
					lblHolidayDesc.setBounds(168, 176, 60, 20);
				}
				{
					txtHolidayDesc = new Text(canHolidays, SWT.BORDER);
					txtHolidayDesc.setBounds(230, 173, 130, 24);
				}
				{
					btnHolidaySubmit = new Button(canHolidays, SWT.PUSH
							| SWT.CENTER);
					btnHolidaySubmit.setText("Submit");
					btnHolidaySubmit.setBounds(252, 231, 60, 23);
					btnHolidaySubmit
							.addSelectionListener(new HolidayListeners());
				}
				{
					gpHolidays = new Group(canHolidays, SWT.NONE);
					gpHolidays.setText("Holidays Settings");
					gpHolidays.setBounds(99, 84, 346, 205);
				}*/
				
				
				
				
				//gridData = new GridData(GridData.FILL_HORIZONTAL);
				yearprev = new Button(canHolidays, SWT.PUSH | SWT.FLAT);
				yearprev.setText("<");
				yearprev.setBounds(300,30,40,30);
				//yearUp.setLayoutData(gridData);
				yearprev.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						previousYear();
					}
				});
				
				
				
				
				yearNext = new Button(canHolidays, SWT.PUSH | SWT.FLAT);
				yearNext.setText(">");
				//yearNext.setLayoutData(gridData);
				yearNext.setBounds(450,30,40,30);
				yearNext.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						nextYear();
					}
				});
				
				
				yeardisp = new CLabel(canHolidays, SWT.CENTER | SWT.SHADOW_OUT);
				//gridData = new GridData(GridData.FILL_HORIZONTAL);
				//gridData.horizontalSpan = 3;
				//nowLabel.setLayoutData(gridData);
				yeardisp.setBounds(360, 30, 70, 30);
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
				yeardisp.setText(formatter.format(new Date()));
				
				{
					tblHoliday = new Table(canHolidays, SWT.FULL_SELECTION
							| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
					tblHoliday.setBounds(65, 80, 690, 430);
					tblHoliday.setHeaderVisible(true);
	
					tblHoliday.setLinesVisible(true);
					/*tblHoliday.addListener(SWT.MeasureItem, new Listener() {
						public void handleEvent(Event event) {
							event.height = 22;
						}
					});*/
					
					{

						coldateselect = new TableColumn(tblHoliday, SWT.NONE);
						coldateselect.setText("DATE");
						coldateselect.setWidth(100);
						coldateselect.setResizable(false);

					}
					{
						coldesc = new TableColumn(tblHoliday, SWT.NONE);
						coldesc.setText("NAME OF HOLIDAY");
						coldesc.setWidth(500);
						coldesc.setResizable(false);
					}
					
					{
						coldelete = new TableColumn(tblHoliday, SWT.NONE);
						coldelete.setText("ADD/DEL");
						coldelete.setWidth(25);
						coldelete.setResizable(false);
					}
					
				}
				
				btndel = new Button(canHolidays, SWT.PUSH | SWT.CENTER);
				btndel.setBounds(650, 55, 25, 25);
				InputStream stream = HolidaysSettingsComposite.class.getClassLoader().getResourceAsStream(ADD_IMG);
				img = new Image(Display.getDefault(), stream);
				btndel.setImage(img);
				btndel.addSelectionListener(new HolidayListeners());
				//btndel.setEnabled(false);
				{
					btnHolidaySubmit = new Button(canHolidays, SWT.PUSH
							| SWT.CENTER);
					btnHolidaySubmit.setText("Submit");
					btnHolidaySubmit.setBounds(640, 530, 60, 23);
					btnHolidaySubmit
							.addSelectionListener(new HolidayListeners());
				}
				 dto= handler.getHolidays(yeardisp.getText());
				 populateHolidayView(dto);
				//populateTable();
				/*{
					btnArtAdd = new Button(canvas, SWT.PUSH | SWT.CENTER);
					btnArtAdd.setBounds(511, 329, 25, 25);
					InputStream stream = LRComposite.class.getClassLoader()
							.getResourceAsStream(ADD_IMG);
					img = new Image(Display.getDefault(), stream);
					btnArtAdd.setImage(img);
					btnArtAdd.addSelectionListener(new submitAction());
					btnArtAdd.setEnabled(false);
				}*/
			}

		}

		return this;

	}

	private void populateHolidayView(ServiceTaxDTO[] holi) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		// TODO Auto-generated method stub
		TableEditor editor = null;
		int len = holi.length;
		int dispyear = 0;
		int curyear = 0;
		try{
			String year = yeardisp.getText();
			dispyear = formatter.parse(year).getYear();
			curyear = new Date().getYear();
					
			if(curyear > dispyear){
				btnAdd.setEnabled(false);
				btndel.setEnabled(false);
			}	
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		if(len > 0){
			for(int i = 0;i < len; i++){
				
				TableItem item = new TableItem(tblHoliday, SWT.NONE);
				
				item.setText(0,holi[i].getLrdate());
				item.setText(1,holi[i].getFrom());
				if(curyear == dispyear){
					editor = new TableEditor(tblHoliday);
					editor.grabHorizontal = true;
					btndel = new Button(tblHoliday, SWT.PUSH | SWT.CENTER);
					//btnAdd.setBounds(511, 329, 25, 25);
					InputStream stream = HolidaysSettingsComposite.class.getClassLoader().getResourceAsStream(DEL_IMG);
					img = new Image(Display.getDefault(), stream);
					btndel.setImage(img);
					btndel.addSelectionListener(new HolidayListeners());
					//btndel.setEnabled(false);
					editor.setEditor(btndel, item, 2);
				}
			}
			
		}
		
		
		
	}

	private void populateTable() {
		// TODO Auto-generated method stub
		
		boolean isweight = false;
		boolean isdispose = false;
		TableEditor editor = null;
		final TableItem item;
		
		item = new TableItem(tblHoliday, SWT.NONE);
		if (null != tblHoliday) {
			{
				editor = new TableEditor(tblHoliday);
				editor.grabHorizontal = true;
				txtDate = new Text(tblHoliday, SWT.BORDER);
				//txtDate.setBounds(230, 118, 100, 24);
				//txtDate.setSize(coldateselect.getWidth()-30,tblHoliday.getItemHeight());
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date currentDate = new java.util.Date();
				String date = dateFormat.format(currentDate);
				txtDate.setEnabled(false);
				txtDate.setText(date);
				editor.setEditor(txtDate, item, 0);
				bntDate = new Button(tblHoliday, SWT.NONE);					
				//bntDate.setSize(30,tblHoliday.getItemHeight());
				bntDate.setImage(SWTResourceManager.getImage("hm/akr/resources/Calendar.jpg"));
				bntDate.addSelectionListener(new HolidayListeners());
				editor.setEditor(bntDate, item, 0);
				
				//dto.setCbname(cbArticle);
			}
			{
				editor = new TableEditor(tblHoliday);
				editor.grabHorizontal = true;
				txtHolidayDesc = new Text(tblHoliday, SWT.NONE);
				editor.setEditor(txtHolidayDesc, item, 1);
				//dto.setTxtnoofarticle(txtnoofarticle);
				
				
				
			}
			
			
			{	
				editor = new TableEditor(tblHoliday);
				editor.grabHorizontal = true;
				btndel = new Button(tblHoliday, SWT.PUSH | SWT.CENTER);
				//btnAdd.setBounds(511, 329, 25, 25);
				InputStream stream = HolidaysSettingsComposite.class.getClassLoader().getResourceAsStream(DEL_IMG);
				img = new Image(Display.getDefault(), stream);
				btndel.setImage(img);
				btndel.addSelectionListener(new HolidayListeners());
				//btndel.setEnabled(false);
				editor.setEditor(btndel, item, 2);
			}
		}
		
	}

	/**
	 * 
	 * @author kibaitachi
	 * 
	 */
	
	public void previousYear() {
		moveTo(Calendar.YEAR, -1);
	}

	public void nextYear() {
		moveTo(Calendar.YEAR, 1);
	}
	
	private void moveTo(int type, int value) {
		
		// object
		try{
			Calendar now = Calendar.getInstance(); // get current Calendar
			now.setTime(nowDate); // set current date
			now.add(type, value); // add to spec time.
			nowDate = new Date(now.getTimeInMillis()); // result
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy");// format
			// date
			yeardisp.setText(formatter.format(nowDate)); // set to label
			if (tblHoliday != null)
				tblHoliday.removeAll(); 
			
			dto= handler.getHolidays(yeardisp.getText());
			if(dto != null){ 
				populateHolidayView(dto);
			}
			//setDayForDisplay(now);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	
	
	public class HolidayListeners implements SelectionListener {

		@Override
		public void widgetDefaultSelected(SelectionEvent event) {

		}

		@Override
		public void widgetSelected(SelectionEvent event) {
			Object source = event.getSource();
			if (source == bntDate) {

				KalendarDialog cd = new KalendarDialog(Display.getCurrent()
						.getActiveShell());
				Object obj = cd.open();
				if (obj != null) {
					txtDate.setText(obj.toString());
				}

			} else if (source == btnHolidaySubmit) {
				try {
					if (null != txtDate) {
						String date = txtDate.getText();
						String desc = txtHolidayDesc.getText();
						boolean valid = validateHoliday(date, desc);

						if (valid) {
							SimpleDateFormat formate = new SimpleDateFormat("dd-MM-yyyy");
							Date date_h=formate.parse(date);
							handler.setHolidays(date_h, desc);
							txtHolidayDesc.setText("");
							AdminComposite.display("Holiday Added", STATUS_SUCCESS, SUCCESS_FONT_COLOR);							
						}
					}
				} catch (RemoteException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

		/**
		 * 
		 * 
		 * @param date
		 * @param desc
		 * @return
		 */
		private boolean validateHoliday(String date, String desc) {

			if (date.equals("") || desc.equals("")) {
				AdminComposite.display("Mandatory parameter missing",
						STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				return false;
			} else
				return true;

		}

	}

}
