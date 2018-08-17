package hm.akr.common;

import java.text.Collator;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

public class TableSort {

	/**
	 * 
	 */
	public static void sortByLrNo(int index, Table refTable) {
		TableItem[] items = refTable.getItems();
		int value1 = 0;
		int value2 = 0;

		int len = items.length;

		// Sorting need to happen only if the number of rows available
		// is greater than one excluding total
		if (len > 2) {
			//len = len - 1; // Ignoring the last row which is meant to be
			// Total

			for (int i = 1; i < len; i++) {
				if (!items[i].getText(index).isEmpty()) {
					value1 = Integer.parseInt(items[i].getText(index)
							.substring(1));
					for (int j = 0; j < i; j++) {
						value2 = Integer.parseInt(items[j].getText(index)
								.substring(1));
						if (value1 < value2) {
							String[] values = { items[i].getText(0),
									items[i].getText(1), items[i].getText(2),
									items[i].getText(3), items[i].getText(4),
									items[i].getText(5), items[i].getText(6),
									items[i].getText(7), items[i].getText(8),
									items[i].getText(9), items[i].getText(10),
									items[i].getText(11), items[i].getText(12),
									items[i].getText(13), items[i].getText(14),
									items[i].getText(15) };
							items[i].dispose();
							TableItem item = new TableItem(refTable, SWT.NONE,
									j);
							item.setText(values);
							items = refTable.getItems();
							break;
						}
					}
				}
			}
		}

	}

	/**
	 * 
	 * @param index
	 * @param isOutTime
	 */
	public static void sortByString(int index, Table refTable) {
		/*
		 * if (refTable != tblGmrReport) sortByLrNo(1, refTable);
		 */
		TableItem[] items = refTable.getItems();
		Collator collator = Collator.getInstance(Locale.getDefault());

		int len = items.length;

		// Sorting need to happen only if the number of rows available
		// is greater than one excluding total
		if (len > 2) {
			/*
			 * if (refTable != tblGmrReport) len--; // Ignoring the last row as
			 * it is meant to be Total
			 */
			for (int i = 1; i < len; i++) {
				if (!items[i].getText(index).isEmpty()) {
					String value1 = items[i].getText(index);
					for (int j = 0; j < i; j++) {
						String value2 = items[j].getText(index);
						if (collator.compare(value1, value2) < 0) {
							String[] values = { items[i].getText(0),
									items[i].getText(1), items[i].getText(2),
									items[i].getText(3), items[i].getText(4),
									items[i].getText(5), items[i].getText(6),
									items[i].getText(7), items[i].getText(8),
									items[i].getText(9), items[i].getText(10),
									items[i].getText(11), items[i].getText(12),
									items[i].getText(13), items[i].getText(14),
									items[i].getText(15) };
							items[i].dispose();

							TableItem item = new TableItem(refTable, SWT.NONE,
									j);
							item.setText(values);
							items = refTable.getItems();
							break;
						}
					}
				}
			}
		}
	}

	public static void SortByDate(int index, Table refTable) {
		try {

			/*
			 * if (refTable != tblGmrReport) sortByLrNo(1, refTable);
			 */
			TableItem[] items = refTable.getItems();

			SimpleDateFormat parse = new SimpleDateFormat("dd-MM-yyyy");

			Date date1 = null;
			Date date2 = null;

			int len = items.length;

			// Sorting need to happen only if the number of rows available
			// is greater than one excluding total
			if (len > 2) {
				/*
				 * if (refTable != tblGmrReport) len--;
				 */
				for (int i = 1; i < len; i++) {
					if (!items[i].getText(index).isEmpty()) {

						date1 = parse.parse(items[i].getText(index));

						for (int j = 0; j < i; j++) {
							if (!items[j].getText(index).isEmpty()) {

								date2 = parse.parse(items[j].getText(index));

							}

							if (date1.before(date2)) {
								String[] values = { items[i].getText(0),
										items[i].getText(1),
										items[i].getText(2),
										items[i].getText(3),
										items[i].getText(4),
										items[i].getText(5),
										items[i].getText(6),
										items[i].getText(7),
										items[i].getText(8),
										items[i].getText(9),
										items[i].getText(10),
										items[i].getText(11),
										items[i].getText(12),
										items[i].getText(13),
										items[i].getText(14),
										items[i].getText(15) };
								items[i].dispose();
								TableItem item = new TableItem(refTable,
										SWT.NONE, j);
								item.setText(values);
								items = refTable.getItems();
								break;
							}
						}
					}
				}
			}
		} catch (Exception exp) {
			exp.printStackTrace();
		}

	}

	/**
	 * 
	 */
	public static void SortByFloat(int index, Table refTable) {
		/*
		 * if (refTable != tblGmrReport) sortByLrNo(1, refTable);
		 */
		TableItem[] items = refTable.getItems();
		float value2 = 0;
		float value1 = 0;
		int len = items.length;

		// Sorting need to happen only if the number of rows available
		// is greater than one excluding total
		if (len > 2) {
			/*
			 * if (refTable != tblGmrReport) len--;
			 */
			for (int i = 1; i < len; i++) {
				if (!items[i].getText(index).isEmpty()) {

					value1 = Float.parseFloat(items[i].getText(index));

					for (int j = 0; j < i; j++) {
						if (!items[j].getText(index).isEmpty())
							value2 = Float.parseFloat(items[j].getText(index));

						if (value1 < value2) {
							String[] values = { items[i].getText(0),
									items[i].getText(1), items[i].getText(2),
									items[i].getText(3), items[i].getText(4),
									items[i].getText(5), items[i].getText(6),
									items[i].getText(7), items[i].getText(8),
									items[i].getText(9), items[i].getText(10),
									items[i].getText(11), items[i].getText(12),
									items[i].getText(13), items[i].getText(14),
									items[i].getText(15) };

							items[i].dispose();
							TableItem item = new TableItem(refTable, SWT.NONE,
									j);
							item.setText(values);
							items = refTable.getItems();
							break;
						}
					}
				}
			}
		}

	}

}
