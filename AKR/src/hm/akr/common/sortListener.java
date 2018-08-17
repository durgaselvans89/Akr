package hm.akr.common;



import hm.akr.container.admin.ReportsMenu;
import hm.akr.container.admin.ReportsMenuHandler;
import hm.akr.dto.StationsDTO;
import hm.akr.dto.TableDecorator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.Collator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

public class sortListener implements IUIConstants {
	
	ReportsMenuHandler handler = null;
	//Shell shell = null;
	ReportsMenu Reports = null;
	
	public static void display(String status,Font font,Color color, Label lblStatusBar)
	{
		lblStatusBar.setText(status);
		lblStatusBar.setFont(font);
		lblStatusBar.setForeground(REPLINK_FOCUS_COLOR);
	}
	
	public void fillZerosOnEmpty(Table refTbl) {
		
		TableItem[] items = refTbl.getItems();
		int len = items.length;		
		if (len > 0) {
			/*if(refTbl == tblIIStates || refTbl == tblInwardAnalysis|| refTbl == tblDRSAttendance){
				len+=1;
			}*/
			for (int i = 0; i < len-1; i++) {
				for (int j = 1; j < refTbl.getColumnCount(); j++) {
					if (items[i].getText(j).equalsIgnoreCase("")
							|| items[i].getText(j).equalsIgnoreCase("0.00")) {
						items[i].setText(j, "0");
					}
				}
			}
		}
	}
	
	public void populateBranches(Combo cbBranch) {
		StationsDTO[] station = null;
		try {
			//cbBranch.removeAll();
			station = handler.getAllBranches();
			if (null != station) {
				int len = station.length;
				for (int i = 0; i < len; i++) {
					cbBranch.add(station[i].getName() + " - "
							+ station[i].getId());
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	
	
	public void preparePDF(ArrayList list, String fileName,
			String jrxmlFile, String[] param,String[] value, Shell shell, Label lblStatusBar) {
		try {
			
			display("Exporting...", STATUS_SUCCESS,	SUCCESS_FONT_COLOR,lblStatusBar);
			handler = new ReportsMenuHandler();
			handler.printReportPDF(list, fileName, jrxmlFile, param,value);

			FileDialog dialog = new FileDialog(shell, SWT.SAVE);
			dialog.setFilterExtensions(new String[] { "*.pdf" });
			dialog.setFilterNames(new String[] { "*.pdf" });
			dialog.setFilterPath("WINDOWS/Desktop"); // Windows path
			dialog.setFileName(fileName);
			String filepath = dialog.open();

			if (null != filepath) {
				File df = new File(filepath);
				File xl = new File("lib/" + fileName + ".pdf");

				if (xl.exists()) {
					display("Saving...", STATUS_SUCCESS, SUCCESS_FONT_COLOR,lblStatusBar);
					copyFile(xl, df, dialog.getFileName());
				}
				display("PDF Saved Successfully",		STATUS_SUCCESS, SUCCESS_FONT_COLOR,lblStatusBar);
			}

		} catch (Exception e) {
			display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR,lblStatusBar);
			e.printStackTrace();
		}

	}
	
	public void prepareExcel(ArrayList list, String fileName,
			String jrxmlFile, String[] param,String[] value, Shell shell, Label lblStatusBar) {
		try {
			display("Exporting...", STATUS_SUCCESS,SUCCESS_FONT_COLOR,lblStatusBar);
			handler = new ReportsMenuHandler();
			handler.printReportExcel(list, fileName, jrxmlFile, param,value);

			FileDialog dialog = new FileDialog(shell, SWT.SAVE);
			dialog.setFilterExtensions(new String[] { "*.xls" });
			dialog.setFilterNames(new String[] { "*.xls" });
			dialog.setFilterPath("WINDOWS/Desktop"); // Windows path
			dialog.setFileName(fileName);
			String filepath = dialog.open();

			if (null != filepath) {
				File df = new File(filepath);
				File xl = new File("lib/" + fileName + ".xls");

				if (xl.exists()) {
					display("Saving...", STATUS_SUCCESS, SUCCESS_FONT_COLOR,lblStatusBar);
					copyFile(xl, df, dialog.getFileName());
				}
				display("Excel Saved Successfully",STATUS_SUCCESS, SUCCESS_FONT_COLOR,lblStatusBar);
			}
			else
			{
				display("Excel Saving Cancelled",STATUS_SUCCESS, SUCCESS_FONT_COLOR,lblStatusBar);
			}
		} catch (Exception e) {
			display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR,lblStatusBar);
			e.printStackTrace();
		}

	}
	
	public void copyFile(File source, File dest, String fileName)
	throws IOException {
	
		if (!dest.exists()) {
			dest.createNewFile();
		} else {
			dest.setWritable(true);
		
		}
		
		InputStream in = null;
		
		OutputStream out = null;
		
		try {
		
			in = new FileInputStream(source);
		
			out = new FileOutputStream(dest);
		
			byte[] buf = new byte[1024];
		
			int len;
		
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
		
		}
		
		finally {
			if (in != null)
				in.close();
			if (out != null)
				out.close();
		}
		
	}
	
	public ArrayList<TableDecorator> getBPATable(Table refTable) {
		TableDecorator dto = null;
		ArrayList<TableDecorator> list = new ArrayList<TableDecorator>();
		int length = 0;
		String colStr1 = null;
		String colStr2 = null;
		int index = -1;

		TableItem[] items = refTable.getItems();
		if (items != null) {
			length = items.length;
			
		}
		if (length > 0) {
			for (int i = 0; i < length; i++) {
				dto = new TableDecorator();				
				colStr1 = items[i].getText(1);
				index = colStr1.indexOf(" - ");
				if (index > 0) {
					colStr1 = colStr1.substring(index + 2);
				}

				colStr2 = items[i].getText(2);
				index = colStr2.indexOf(" - ");
				if (index > 0) {
					colStr2 = colStr2.substring(index + 1);
				}
				
				dto.setCol1(colStr1);
				dto.setCol2(colStr2);
				dto.setCol3(items[i].getText(3));
				dto.setCol4(items[i].getText(4));
				dto.setCol5(items[i].getText(5));
				dto.setCol6(items[i].getText(6));
				dto.setCol7(items[i].getText(7));
				dto.setCol8(items[i].getText(8));
				dto.setCol9(items[i].getText(9));
				dto.setCol10(items[i].getText(10));
				dto.setCol11(items[i].getText(11));
				dto.setCol12(items[i].getText(12));
				dto.setCol13(items[i].getText(13));
				dto.setCol14(items[i].getText(14));
				dto.setCol15(items[i].getText(15));
				dto.setCol16(items[i].getText(16));
				
				list.add(dto);
			}
		}

		return list;
	}

	/**
	 * 
	 * @param index
	 * @param isOutTime
	 */
	public void sortByString(int index, Table refTable) {
		try {

			TableItem[] items = refTable.getItems();
			Collator collator = Collator.getInstance(Locale.getDefault());

			int len = items.length;
		
			// Sorting need to happen only if the number of rows available
			// is greater than one excluding total
			if (len > 2) {
				len = len - 1; // Ignoring the last row as it is meant to be Total

				for (int i = 1; i < len; i++) {
					if (!items[i].getText(index).isEmpty()) {
						String value1 = items[i].getText(index);
						for (int j = 0; j < i; j++) {
							String value2 = items[j].getText(index);
							if (collator.compare(value1, value2) < 0) {
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
				// setSerialNumber(refTable);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param refTable
	 */
	public void setSerialNumber(Table refTable) {
		if (null != refTable) {
			TableItem[] items = refTable.getItems();
			if (null != items) {
				int len = items.length;
				len = len - 1;
				for (int i = 0; i < len; i++) {
					items[i].setText(0, String.valueOf(i + 1));
				}
			}
		}

	}

	public void sortByLrNo(int index, Table refTable) {
		try {
			TableItem[] items = refTable.getItems();

			int value1 = 0;
			int value2 = 0;
			
			int len = items.length;

			// Sorting need to happen only if the number of rows available
			// is greater than one excluding total
			if (len > 2) {
				len = len - 1; // Ignoring the last row which is meant to
				// be
				// Total

				for (int i = 1; i < len; i++) {
					if (!items[i].getText(index).isEmpty()) {
						value1 = Integer.parseInt(items[i].getText(index)
								.substring(1));
						for (int j = 0; j < i; j++) {
							if (!items[j].getText(index).isEmpty()) {
								value2 = Integer.parseInt(items[j].getText(
										index).substring(1));
							}
							
							if (value1 < value2) {
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
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param index
	 * @param refTable
	 */
	public void sortByDate(int index, Table refTable) {
		try {

			TableItem[] items = refTable.getItems();

			SimpleDateFormat parse = new SimpleDateFormat("yyyy-MM-dd");

			Date date1 = null;
			Date date2 = null;

			int len = items.length;

			// Sorting need to happen only if the number of rows available
			// is greater than one excluding total
			if (len > 2) {
				len--;
				for (int i = 1; i < len; i++) {
					if (!items[i].getText(index).isEmpty()) {

						date1 = parse.parse(items[i].getText(index));
						//System.out.println("date1--->"+date1);
						for (int j = 0; j < i; j++) {
							if (!items[j].getText(index).isEmpty()) {

								date2 = parse
										.parse(items[j].getText(index));
								//System.out.println("date2--->"+date2);
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
										items[i].getText(15),
										items[i].getText(16),
										items[i].getText(17),
										items[i].getText(18),
										};
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
	public void sortByFloat(int index, Table refTable) {

		try {
			TableItem[] items = refTable.getItems();
			float value2 = 0;
			float value1 = 0;
			int len = items.length;

			// Sorting need to happen only if the number of rows available
			// is greater than one excluding total
			if (len > 2) {
				len--;
				for (int i = 1; i < len; i++) {
					if (!items[i].getText(index).isEmpty()) {

						value1 = Float.parseFloat(items[i].getText(index));

						for (int j = 0; j < i; j++) {
							if (!items[j].getText(index).isEmpty())
								value2 = Float.parseFloat(items[j]
										.getText(index));

							if (value1 < value2) {
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
				// setSerialNumber(refTable);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
