package hm.akr.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class MonthDialog extends Dialog {

	private Display display = null;

	private Date nowDate = null; // current date

	private String selectedMonth = null; // selected date

	private Shell shell = null;

	private GridLayout gridLayout = null;

	private GridData gridData = null;

	private Button yearUp = null;

	private Button yearNext = null;

	private CLabel lblMY = null;
	
	BeanUtil beanutil = null;

	// private CLabel lblMonth;

	private CLabel[] lblMonths = new CLabel[12];

	private String[] months = { "Jan", "Feb", "Mar", "Apr", "May", "Jun",
			"Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };

	public MonthDialog(Shell parent, int style) {
		super(parent, style);
		try {
			beanutil = BeanUtil.getInstance();
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}

	public MonthDialog(Shell parent) {
		this(parent, 0);
	}

	public boolean isLeapYear(int year) {
		return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
	}

	private void moveTo(int type, int value) {
		Calendar now = Calendar.getInstance(); // get current Calendar
		// object
		now.setTime(nowDate); // set current date
		now.add(type, value); // add to spec time.
		nowDate = new Date(now.getTimeInMillis()); // result
		SimpleDateFormat formatter = new SimpleDateFormat("MM-yyyy");// format
		// Month-Year
		lblMY.setText(formatter.format(nowDate)); // set to label
		setDayForDisplay(now);
	}

	private void setDayForDisplay(Calendar now) {
		now.add(Calendar.DAY_OF_MONTH, -(now.get(Calendar.DATE) - 1));
		int month = now.get(Calendar.MONTH) + 1; //				
		// lblMonth.setText(months[month-1]);

		int startday = 1;
		for (int i = 0; i < 12; i++) {
			Color temp = lblMonths[i].getBackground();
			if (temp.equals(display.getSystemColor(SWT.COLOR_BLUE))) {
				lblMonths[i].setBackground(display
						.getSystemColor(SWT.COLOR_WHITE));
			}
		}

		for (int i = 0; i < 12; i++) {
			lblMonths[i].setText(months[i]);
			if (startday == month) {
				lblMonths[i].setBackground(display
						.getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND)); //
			}
			startday++;
		}

	}

	public void previousYear() {
		moveTo(Calendar.YEAR, -1);
	}

	public void nextYear() {
		moveTo(Calendar.YEAR, 1);
	}

	public Object open() {
		Shell parent = getParent();
		display = Display.getDefault();
		shell = new Shell(parent, SWT.TITLE | SWT.PRIMARY_MODAL | SWT.CLOSE);
		shell.setText("AKR Parcel");
		shell.setSize(210, 140);
		shell.setLocation(460, 80);

		gridLayout = new GridLayout();
		gridLayout.numColumns = 6;
		shell.setLayout(gridLayout);

		gridData = new GridData(GridData.FILL_HORIZONTAL);
		yearUp = new Button(shell, SWT.PUSH | SWT.FLAT);
		yearUp.setText("<");
		yearUp.setLayoutData(gridData);
		yearUp.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				previousYear();
			}
		});

		lblMY = new CLabel(shell, SWT.CENTER | SWT.SHADOW_OUT);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 4;
		lblMY.setLayoutData(gridData);
		SimpleDateFormat formatter = new SimpleDateFormat("MM-yyyy");
		lblMY.setText(formatter.format(new Date()));
		lblMY.setBackground(display.getSystemColor(SWT.COLOR_GRAY));

		gridData = new GridData(GridData.FILL_HORIZONTAL);
		yearNext = new Button(shell, SWT.PUSH | SWT.FLAT);
		yearNext.setText(">");
		yearNext.setLayoutData(gridData);
		yearNext.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				nextYear();
			}
		});

		for (int i = 0; i < 12; i++) {
			lblMonths[i] = new CLabel(shell, SWT.FLAT | SWT.CENTER);
			gridData = new GridData(GridData.FILL_HORIZONTAL);
			gridData.heightHint = 30;
			lblMonths[i].setLayoutData(gridData);
			lblMonths[i].setBackground(display
					.getSystemColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));
			final int index = i;

			lblMonths[i].addMouseListener(new MouseListener() {

				@Override
				public void mouseDoubleClick(MouseEvent event) {
					String monthYear = null;
					String year = lblMY.getText().substring(2);
					if (index >= 9) {
						monthYear = (index + 1) + year;
					} else {
						monthYear = ("0" + (index + 1)) + year;
					}
					selectedMonth = monthYear;
					
					SimpleDateFormat parse = new SimpleDateFormat("MM-yyyy");
					String fromDate = "04-2010";
					//String toDate = "08-2011";
					String serverDt = beanutil.getServerDate(); 
					String toDate =  serverDt;
					toDate = toDate.substring(toDate.indexOf("-")+1);
					
					if(!BeanUtil.getDbName().equals("")){
						fromDate = "03-2009";
						toDate = "03-2010";
					}
					
					
					
					try {
						Date from = parse.parse(fromDate);
						Date to = parse.parse(toDate);

						Date selected = parse.parse(selectedMonth);
						//System.out.println("from-->"+from+"to-->"+to+"sele-->"+selected);
						if (selected.before(from)
								|| selected.after(to)) {
							//System.out.println("not valid");
							selectedMonth = toDate;
						} else {
							//System.out.println("valid");
							shell.close();
						}
					} catch (ParseException e) {						
						e.printStackTrace();
					}

				}

				@Override
				public void mouseDown(MouseEvent event) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseUp(MouseEvent event) {

				}

			});
			lblMonths[i].setToolTipText("Double click get current Month");
		}

		Calendar now = Calendar.getInstance(); //
		nowDate = new Date(now.getTimeInMillis());
		setDayForDisplay(now);

		shell.open();
		Display display = parent.getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return selectedMonth;
	}

}
