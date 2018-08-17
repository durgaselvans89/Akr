package hm.akr.container.gmr;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
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
 * @author
 * 
 */
public class GMRPriorityPop extends Composite {
	private Table tblpriority;
	private Shell shell;
	private Label lblfrom;
	private Combo cbTo;
	private Label lblto_station;
	private Label lblfrom_station;
	private TableColumn colpriority;
	private TableColumn colstationcode;
	private ArrayList set;
	private ArrayList dateset;
	String[][] pri = null;
	String to = null;
	Text[] text = null;
	private TableColumn coldate;
	private TreeSet tree;
	private String fromstation = null;
	private TableColumn colsno;

	/**
	 * Constructor Method for GMRpriority composite class
	 * 
	 * @param shell
	 * @param swt
	 * @param set
	 */
	GMRPriorityPop(Shell shell, int swt, ArrayList set, ArrayList set1,
			TreeSet tree, String fromstation) {
		super(shell, swt);
		this.shell = shell;
		this.set = set;
		this.dateset = set1;
		this.tree = tree;
		this.fromstation = fromstation;

	}

	/**
	 * Method to load Composite
	 */
	public void loadComposite() {
		shell.setBounds(200, 200, 405, 320);
		shell.open();
		shell.addDisposeListener(new PriorityListener());
		try {
			{
				this.setSize(405, 320);
				{
					tblpriority = new Table(this, SWT.NONE);
					tblpriority.setBounds(44, 55, 320, 184);
					tblpriority.setHeaderVisible(true);
					tblpriority.setLinesVisible(true);
					{
						colsno = new TableColumn(tblpriority, SWT.NONE);
						colsno.setText("Sno");
						colsno.setWidth(40);
					}
					{
						colstationcode = new TableColumn(tblpriority, SWT.NONE);
						colstationcode.setText("Stationcode");
						colstationcode.setWidth(100);
					}
					{
						coldate = new TableColumn(tblpriority, SWT.NONE);
						coldate.setText("Dispatch Time");
						coldate.setWidth(101);

					}
					{
						colpriority = new TableColumn(tblpriority, SWT.NONE);
						colpriority.setText("Priority");
						colpriority.setWidth(65);
					}
				}
				Button btnset = new Button(this, SWT.NONE);
				btnset.setText("Set");
				btnset.setBounds(217, 252, 50, 27);
				btnset.addSelectionListener(new PriorityListener());
				populatePriorityTable();

			}
			{
				lblfrom_station = new Label(this, SWT.NONE);
				lblfrom_station.setText("From");
				lblfrom_station.setBounds(44, 19, 40, 19);
			}
			{
				lblfrom = new Label(this, SWT.NONE);
				lblfrom.setBounds(84, 19, 88, 19);
				lblfrom.setText(fromstation);
			}
			{
				lblto_station = new Label(this, SWT.NONE);
				lblto_station.setText("To");
				lblto_station.setBounds(229, 19, 26, 19);
			}
			{
				cbTo = new Combo(this, SWT.NONE);
				cbTo.setBounds(271, 18, 93, 21);
				Iterator iterate = tree.iterator();
				while (iterate.hasNext()) {
					cbTo.add((String) iterate.next());
				}
				cbTo.select(0);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (shell != null) {
			shell.open();
			Display display = shell.getDisplay();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		}
	}

	/**
	 * 
	 * @author
	 * 
	 */
	public class PriorityListener implements DisposeListener, SelectionListener {

		@Override
		public void widgetDisposed(DisposeEvent arg0) {
			getPriorities();
			getTostation();

		}

		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void widgetSelected(SelectionEvent arg0) {

			shell.dispose();
		}

	}

	/**
	 * Populate default priorities values
	 */
	private void populatePriorityTable() {
		if (null != tblpriority) {
			int size = set.size();
			Object[] obj = set.toArray(new Object[size]);
			Object[] obj1 = dateset.toArray(new Object[size]);
			text = new Text[size];
			for (int i = 0; i < size; i++) {
				TableItem item = new TableItem(tblpriority, SWT.NONE);
				TableEditor editor = new TableEditor(tblpriority);
				item.setText(0, String.valueOf(i + 1));
				item.setText(1, obj[i].toString());
				item.setText(2, obj1[i].toString());
				item.setText(3, String.valueOf(i + 1));
				text[i] = new Text(tblpriority, SWT.NONE);
				text[i].setText(String.valueOf(i + 1));
				editor.grabHorizontal = true;
				editor.setEditor(text[i], item, 3);
			}

		}

	}

	/**
	 * Getting all assigned priorities
	 * 
	 * @return
	 */
	public String[][] getPriorities() {
		TableItem[] item = tblpriority.getItems();
		pri = new String[item.length][3];
		for (int i = 0; i < item.length; i++) {
			pri[i][0] = item[i].getText(1);
			pri[i][1] = item[i].getText(2);
			pri[i][2] = text[i].getText();
			if (Integer.parseInt(pri[i][2]) > item.length) {
				System.out.println("The Value of wrong");
			}

		}
		return pri;
	}

	/**
	 * 
	 * @return
	 */
	public String getTostation() {
		int index = cbTo.getSelectionIndex();
		if (index > -1) {
			to = cbTo.getItem(index);
		} else {
			to = cbTo.getText();
			if (to.equals(""))
				to = null;
		}
		return to;
	}

}