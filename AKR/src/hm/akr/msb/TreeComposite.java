package hm.akr.msb;

import hm.akr.common.BeanUtil;
import hm.akr.dto.MsgDTO;
import hm.akr.dto.StationsDTO;

import java.util.ArrayList;
import java.util.Properties;
import java.util.TreeSet;

import org.eclipse.swt.SWT;
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
public class TreeComposite extends Composite {

	Properties properties = null;

	TreeCompositeHandler handler = null;

	private Tree tree;

	public static TreeItem items = null;

	String s = "";

	private TreeItem items1;

	private TreeItem items2;

	Shell shell = null;

	Display display = null;

	BeanUtil beanutil = null;

	MsgDTO[] msgs = null;

	public static TreeSet<String> set = null;

	ArrayList<String> selectedmsbstation = null;

	private int height;

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

				beanutil =BeanUtil.getInstance();

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

					items = new TreeItem(tree, SWT.NONE);
					items.setText("Akr Stations");
					populateBranches();
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;

	}

	/**
	 * Method to get All selected Station from Tree
	 * 
	 * @return
	 */
	public String[] getSelectedMSBStation() {
		String station = null;
		int ind = 0;
		int len = set.size();
		String[] stations = (String[]) set.toArray(new String[len]);
		for (int i = 0; i < stations.length; i++) {
			station = stations[i];
			ind = station.indexOf(" - ");
			station = station.substring(ind + 3);
			stations[i] = station;
		}
		return stations;
	}

	/**
	 * Method to clear Tree items.
	 */
	public void clearTree() {
		items.setChecked(false);
		TreeItem[] subItem = items.getItems();
		for (int i = 0; i < subItem.length; i++) {
			subItem[i].setChecked(false);
			TreeItem[] stationItem = subItem[i].getItems();
			for (int j = 0; j < stationItem.length; j++) {
				stationItem[j].setChecked(false);
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
	 * Method to populate Destination Station Codes
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
					items2 = new TreeItem(items1, SWT.NONE);
					items2.setText(station[i].getName() + " - "
							+ station[i].getId());

				}
				items.setExpanded(true);
				items1.setExpanded(true);
			}
		} catch (Exception exception) {

		}
	}

	/**
	 * Method to populate all branches
	 */
	private void populateBranches() {
		StationsDTO[] station = null;
		try {

			station = handler.getAllBranches();

			if (null != station) {
				int len = station.length;

				for (int i = 0; i < len; i++) {

					items1 = new TreeItem(items, SWT.NONE);
					items1.setText(station[i].getName() + " - "
							+ station[i].getId());
					populateDestStationCodes(station[i].getId());

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
	 * Method to Store all Checked Items
	 * 
	 * @param item
	 * @param checked
	 */
	private void checkItems(TreeItem item, boolean checked) {

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

	}

}