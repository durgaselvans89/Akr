package hm.akr.msb;

import hm.akr.admin.commission.CommissionComposite;
import hm.akr.admin.customer.CustomerComposite;
import hm.akr.admin.workspace.AdminComposite;
import hm.akr.common.BeanUtil;
import hm.akr.common.IUIConstants;
import hm.akr.dto.CustomerDTO;
import hm.akr.dto.MsgDTO;
import hm.akr.dto.StationsDTO;

import java.util.ArrayList;
import java.util.Properties;
import java.util.TreeSet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

/**
 * 
 * @author Class to create TreeComposite
 * 
 */
public class TreeComposite extends Composite implements IUIConstants {

	Properties properties = null;

	TreeCompositeHandler handler = null;

	public static Tree tree;

	public static TreeItem items;

	String s = "";

	public static TreeItem items1;

	public static TreeItem items2;

	Shell shell = null;

	Display display = null;

	BeanUtil beanutil = null;

	MsgDTO[] msgs = null;

	public static TreeSet<String> set = null;

	ArrayList<String> selectedmsbstation = null;

	private int height;

	public static String parent = null;

	private static boolean isTrue = false;

	/**
	 * Constructor Method
	 * 
	 * @param shell
	 * @param swtValue
	 * @throws Exception
	 */
	public TreeComposite(Composite cpt, int swtValue, int height)
			throws Exception {
		super(cpt, swtValue);
		parent = "";
		handler = new TreeCompositeHandler();
		this.height = height;
	}

	public TreeComposite(Composite cptHLC, int none, int height, String HLC)
			throws Exception {
		super(cptHLC, none);
		parent = "HLC";
		handler = new TreeCompositeHandler();
		this.height = height;
	}

	/**
	 * Method to Create TreeComposite
	 * 
	 * @return Composite
	 */
	public Composite loadComposite() {
		try {
			{
				set = new TreeSet<String>();
				GridLayout gl = new GridLayout();
				gl.numColumns = 1;
				gl.horizontalSpacing = 0;
				gl.verticalSpacing = 0;
				this.setLayout(gl);

				beanutil = new BeanUtil();

				{
					GridData gd = new GridData();
					gd.widthHint = 170;
					gd.heightHint = height;

					tree = new Tree(this, SWT.CHECK | SWT.BORDER | SWT.MULTI
							| SWT.FULL_SELECTION | SWT.V_SCROLL);
					tree.setLayoutData(gd);

					tree.addListener(SWT.Selection, new Listener() {

						public void handleEvent(Event event) {

							if (event.detail == SWT.CHECK) {

								AdminComposite.display("", STATUS_SUCCESS,
										SUCCESS_FONT_COLOR);

								TreeItem item = (TreeItem) event.item;

								boolean checked = item.getChecked();

								checkItems(item, checked);

								checkPath(

								item.getParentItem(),

								checked,

								false);

							}
						}

					});

					tree.addFocusListener(new FocusListener() {

						@Override
						public void focusGained(FocusEvent arg0) {
							// TODO Auto-generated method stub

						}

						@Override
						public void focusLost(FocusEvent event) {
							if (parent.equalsIgnoreCase("HLC")) {
								String[] stations = getSelectedMSBStation();

								try {
									CommissionComposite
											.populateStationCustomers(stations);
								} catch (Exception e) {
									e.printStackTrace();
								}

							}

						}

					});

					/*	items = new TreeItem(tree, SWT.NONE);
						items.setText("Akr Stations");
						populateBranches();*/

					if (CustomerComposite.isCustomer == false) {
						isTrue = CustomerComposite.isCustomer;
						items = new TreeItem(tree, SWT.NONE);
						items.setText("Akr Stations");
						populateBranches();
					} else if (CustomerComposite.isCustomer == true) {
						isTrue = CustomerComposite.isCustomer;
						items = new TreeItem(tree, SWT.NONE);
						items.setText(CustomerComposite.selectedbranch);
						populateStations(CustomerComposite.selectedbranch);
					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;

	}

	/**
	 * 
	 * @author Method to get Selected Stations
	 * @return StationName
	 * 
	 */

	public String[] getSelectedMSBStation() {
		String station = null;
		int ind = 0;
		int len = set.size();
		String[] stations = set.toArray(new String[len]);
		for (int i = 0; i < stations.length; i++) {
			station = stations[i];
			ind = station.indexOf(" - ");
			station = station.substring(ind + 3);
			stations[i] = station;
		}

		return stations;
	}

	/**
	 * Clear Tree
	 */
	public void clearTree() {
		items.setChecked(false);
		TreeItem[] item1 = items.getItems();
		for (int i = 0; i < item1.length; i++) {
			item1[i].setChecked(false);
			TreeItem[] newit = item1[i].getItems();
			for (int j = 0; j < newit.length; j++) {
				newit[j].setChecked(false);
			}
		}
		set = new TreeSet<String>();
	}

	/**
	 * 
	 * @author Inner Class for Listener
	 * 
	 */

	/**
	 * 
	 * @param stationcode
	 */
	private void populateDestStationCodes(String stationcode) {

		try {

			String stationCode = stationcode;
			StationsDTO[] station = null;

			if (stationCode != null) {

				station = handler.getAllAssociatedStations(stationcode);
			}

			if (null != station) {
				int len = station.length;

				for (int i = 0; i < len; i++) {
					// if (!(station[i].getId().equals(stationcode))) {
					items2 = new TreeItem(items1, SWT.NONE);
					items2.setText(station[i].getName() + " - "
							+ station[i].getId());
					// }
				}
				items.setExpanded(true);
				items1.setExpanded(true);
			}
		} catch (Exception exception) {

		}
	}

	/**
	 * 
	 */
	private void populateBranches() {
		StationsDTO[] station = null;
		try {

			station = handler.getAllBranches();

			if (null != station) {
				int len = station.length;

				for (int i = 0; i < len; i++) {

					// if (null != station[i].getId() || null !=
					// station[i].getName()) {
					items1 = new TreeItem(items, SWT.NONE);
					items1.setText(station[i].getName() + " - "
							+ station[i].getId());
					populateDestStationCodes(station[i].getId());

					// }

				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * 
	 * @param item
	 * @param checked
	 * @param grayed
	 */
	public void checkPath(

	TreeItem item,

	boolean checked,

	boolean grayed) {

		if (item == null)
			return;

		if (grayed) {

			checked = true;

		} else {

			int index = 0;

			TreeItem[] items = item.getItems();

			while (index < items.length) {

				TreeItem child = items[index];

				if (child.getGrayed()

				|| checked != child.getChecked()) {

					checked = grayed = true;

					break;

				}

				index++;

			}

		}

		item.setChecked(checked);

		item.setGrayed(grayed);

		checkPath(item.getParentItem(), checked, grayed);

	}

	/**
	 * 
	 * @param item
	 * @param checked
	 */
	private void checkItems(TreeItem item, boolean checked) {/*

		item.setGrayed(false);

		item.setChecked(checked);
		TreeItem[] items = item.getItems();
		if (item.getChecked() && items.length == 0) {
			set.add(item.getText());
		} else {
			set.remove(item.getText());
		}

		for (int i = 0; i < items.length; i++) {

			checkItems(items[i], checked);

		}
	*/
	


		item.setGrayed(false);

		item.setChecked(checked);
		TreeItem[] items = item.getItems();
		if (item.getChecked() && items.length == 0) {
			if (isTrue == false) {
				set.add(item.getText());
			} else if (isTrue == true) {

				if (item.getParentItem().getParentItem() != null)
					set.add(item.getText() + "|"
							+ item.getParentItem().getText());

			}

		} else {
			if (isTrue == false) {
				set.remove(item.getText());
			} else if (isTrue == true) {
				if (item.getParentItem() != null)
					if (item.getParentItem().getParentItem() != null)
						set.remove(item.getText() + "|"
								+ item.getParentItem().getText());

			}

		}

		for (int i = 0; i < items.length; i++) {

			checkItems(items[i], checked);

		}

	
		
	}

	public String[] getSelectedMSBCustomer() {
		String station = null;
		int len = set.size();
		String[] stations = set.toArray(new String[len]);
		for (int i = 0; i < stations.length; i++) {
			station = stations[i];
			stations[i] = station;			
		}

		return stations;
	}

	private void populateStations(String branch) {

		int index = branch.indexOf(" - ");

		String station_code = branch.substring(index + 2).trim();

		StationsDTO[] station = null;
		try {

			station = handler.getStations(station_code);

			if (null != station) {
				int len = station.length;

				for (int i = 0; i < len; i++) {

					// if (null != station[i].getId() || null !=
					// station[i].getName()) {
					items1 = new TreeItem(items, SWT.NONE);
					items1.setText(station[i].getName() + " - "
							+ station[i].getId());
					populateCustomers(station[i].getId());

					// }

				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}

	private void populateCustomers(String id) {

		try {
			CustomerDTO[] dto = handler.getCustomerDetails();
			if (null != dto) {
				for (int i = 0; i < dto.length; i++) {
					if (id.equalsIgnoreCase(dto[i].getStation())
							&& dto[i].getQuotationId() != null) {
						items2 = new TreeItem(items1, SWT.NONE);
						items2.setText(dto[i].getName());
						items.setExpanded(true);
						items1.setExpanded(true);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}