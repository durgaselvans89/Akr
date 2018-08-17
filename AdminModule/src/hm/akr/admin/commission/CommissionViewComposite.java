package hm.akr.admin.commission;

import hm.akr.admin.commission.handler.AgencyCommisionHandler;
import hm.akr.admin.distance.handler.DistanceCompositeHandler;
import hm.akr.admin.history.HistoryHandler;
import hm.akr.admin.workspace.SWTResourceManager;
import hm.akr.common.BeanUtil;
import hm.akr.common.IUIConstants;
import hm.akr.common.MonthDialog;
import hm.akr.dto.CommissionSummaryDTO;
import hm.akr.dto.StationsDTO;
import hm.akr.dto.VersionDTO;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.ArrayList;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.swing.table.TableCellRenderer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.FileDialog;





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
public class CommissionViewComposite extends Composite implements IUIConstants {

	Properties properties = null;

	private Table tblCommission;

	private TableColumn column;

	private TableItem item;

	private Combo cbSB = null;

	private Label label1;

	private Button btnDateGo;
	
	private Button btnPrint;
	
	private Button btnExportXL;

	private Text txtDate;

	private Label lblDate;

	private Canvas canvas2;
	// ContainerManager manager = null;

	BeanUtil beanutil = null;

	DistanceCompositeHandler handler = null;

	AgencyCommisionHandler agencyhandler = null;
	
	private static final String VIEW_COMMISSION_JRXML = "hm/akr/resources/printer/viewcommission.jrxml";

	private String[] column_head = { "Station", "Booking", "Delivery",
			"CC Commission","Refund", "Recovery ", "Total" };

	private Button btnMonth;

	private DecimalFormat decimalFormat;
	
	private Shell shell = null;
	
	VersionDTO[] vDto = null;
	
	HistoryHandler historyH = null;
	

	/**
	 * 
	 * @param shell
	 * @param swtValue
	 * @throws Exception
	 */
	public CommissionViewComposite(Composite composite, int swtValue)
			throws Exception {
		
		super(composite, swtValue);
		
		shell = composite.getShell();
		historyH = new HistoryHandler();
		vDto = historyH.getHistoryYears();
		if(vDto != null && vDto.length > 0){
			BeanUtil.setThree_month_updated(vDto[0].getUpdated_date());
		}

		try {

			handler = new DistanceCompositeHandler();
			agencyhandler = new AgencyCommisionHandler();
			

		} catch (Exception e) {

			throw e;
		}

	}

	/**
	 * 
	 * @return
	 */
	public Composite loadComposite() {
		try {
			{
				this.setSize(719, 566);

				beanutil = new BeanUtil();

			}
			{
				canvas2 = new Canvas(this, SWT.NONE);
				canvas2.setBounds(10, 0, 800, 583);
				{
					label1 = new Label(canvas2, SWT.NONE);
					label1.setText("Select Branch");
					label1.setBounds(27, 33, 75, 15);
					label1.setFont(LABLE_FONT);
				}
				
				{
					btnPrint = new Button(canvas2, SWT.PUSH | SWT.CENTER);
					btnPrint.setText("Print");
					btnPrint.setBounds(665, 530, 60, 23);
					//btnPrint.setEnabled(true);
					btnPrint.addSelectionListener(new SourceBranchListener());
				}
				
				{
					btnExportXL = new Button(canvas2, SWT.PUSH | SWT.CENTER);
					btnExportXL.setText("Export Excel");
					btnExportXL.setBounds(570, 530, 80, 23);		
					btnExportXL.addSelectionListener(new SourceBranchListener());
				}
				
				
				{
					cbSB = new Combo(canvas2, SWT.DROP_DOWN | SWT.READ_ONLY);
					cbSB.setBounds(102, 31, 230, 10);
					cbSB.setFont(COMBO_FONT);
					cbSB.add("All");
					cbSB.addSelectionListener(new SourceBranchListener());
					populateBranches();
				}
				{
					btnDateGo = new Button(canvas2, SWT.PUSH | SWT.CENTER);
					btnDateGo.setText("GO");
					btnDateGo.setBounds(500, 30, 40, 24);
					btnDateGo.addSelectionListener(new SourceBranchListener());
				}
				{
					lblDate = new Label(canvas2, SWT.NONE);
					lblDate.setText("Month");
					lblDate.setBounds(348, 32, 35, 21);
				}
				{
					txtDate = new Text(canvas2, SWT.BORDER | SWT.READ_ONLY);
					txtDate.setBounds(395, 31, 60, 21);
					DateFormat dateFormat = new SimpleDateFormat("MM-yyyy");
					java.util.Date currentDate = new java.util.Date();
					String date = dateFormat.format(currentDate);
					txtDate.setText(date);
				}
				{
					btnMonth = new Button(canvas2, SWT.PUSH | SWT.CENTER);
					btnMonth.setImage(SWTResourceManager
							.getImage("hm/akr/resources/Calendar.jpg"));
					btnMonth.setBounds(455, 30, 26, 23);
					btnMonth.addSelectionListener(new SelectionListener() {

						@Override
						public void widgetDefaultSelected(SelectionEvent arg0) {
							

						}

						@Override
						public void widgetSelected(SelectionEvent event) {
							MonthDialog cd = new MonthDialog(shell);
							Object obj = cd.open();
							if (obj != null) {
								txtDate.setText(obj.toString());
							}
						}

					});
				}
				tblCommission = new Table(canvas2, SWT.MULTI | SWT.BORDER
						| SWT.FULL_SELECTION);
				tblCommission.setLinesVisible(true);
				tblCommission.setHeaderVisible(true);
				tblCommission.setBounds(27, 70, 740, 452);
				for (int i = 0; i < 7; i++) {
					column = new TableColumn(tblCommission, SWT.NONE | SWT.RIGHT);

					if (i == 0) {
						column.setText(column_head[i]);
						column.setWidth(160);
					} else if (i == 1 || i == 2) {
						column.setText(column_head[i]);
						column.setWidth(85);
					} else {
						column.setText(column_head[i]);
						column.setWidth(114);
					}
				}

				{
					CommissionSummaryDTO[] rowDTO = null;
					String value = cbSB.getItem(0);
					cbSB.setText(value);
					int index = value.indexOf(" - ");
					String branch_code = "";
					
					if(value.equalsIgnoreCase("All")){
						branch_code = "%" ;
					}else{
					 value.substring(index + 3);
					}
					String date = txtDate.getText();
					int index1 = date.indexOf("-");
					int day = Integer.parseInt(date.substring(0, index1));
					int month = Integer.parseInt(date.substring(index1 + 1));
					rowDTO = agencyhandler.getAdminCommission(branch_code, day,	month);
					if (null != rowDTO) {
						int rows = rowDTO.length;

						createTable(column_head.length, rows, column_head,
								rowDTO);
					}
					// createTableEditor();
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;

	}

	/**
	 * 
	 */
	private void populateBranches() {
		try {
			StationsDTO[] stations = handler.getAllBranches();
			if (null != stations) {
				int len = stations.length;
				for (int i = 0; i < len; i++) {
					cbSB.add(stations[i].getName() + " - "
							+ stations[i].getId());
				}
			}
		} catch (Exception exception) {

		}
	}

	

	/**
	 * 
	 * @param columns
	 * @param rows
	 * @param column_name
	 * @param row_data
	 */
	private void createTable(int columns, int rows, String[] column_name,
			CommissionSummaryDTO[] row_data) {

		//System.out.println("len=-==> "+ row_data.length);
		tblCommission.removeAll();
		decimalFormat = new DecimalFormat("0.00");
		for (int j = 0; j < rows; j++) {
			Float total = 0.0f;
			item = new TableItem(tblCommission, SWT.NONE);
			//Font font1 = new Font(Display.getCurrent(), "Tahoma", 9, SWT.RIGHT_TO_LEFT);
			
			//item.setFont(font1);
			item.setText(0, row_data[j].getStation_name());
			total = total +  row_data[j].getBcTotal();
			item.setText(1, decimalFormat.format(row_data[j].getBcTotal()));
			total = total +  row_data[j].getDcTotal();
			item.setText(2, decimalFormat.format(row_data[j].getDcTotal()));
			total = total +  row_data[j].getCccTotal();
			item.setText(3, decimalFormat.format(row_data[j].getCccTotal()));
			total = total +  row_data[j].getRefundAmount();
			item.setText(4, decimalFormat.format(row_data[j].getRefundAmount()));
			total = total -  row_data[j].getRecoveryAmount();
			item.setText(5, decimalFormat.format(row_data[j].getRecoveryAmount()));
			
			item.setText(6,decimalFormat.format(total));

		}
		calcTotal();
	}

	private void calcTotal() {
		// TODO Auto-generated method stub
		TableItem item[] =  tblCommission.getItems();
		double tot1 = 0;
		double tot2 = 0;
		double tot3 = 0;
		double tot4 = 0;
		double tot5 = 0;
		double tot6 = 0;
		if(item != null)
		{
			for(int i = 0; i < item.length; i++ )
			{
				if(! item[i].getText(1).equalsIgnoreCase("")){
					tot1 = tot1 + Double.parseDouble(item[i].getText(1));
				}
				if(! item[i].getText(2).equalsIgnoreCase("")){
					tot2 = tot2 + Double.parseDouble(item[i].getText(2));
				}
				if(! item[i].getText(3).equalsIgnoreCase("")){
					tot3 = tot3 + Double.parseDouble(item[i].getText(3));
				}
				
				if(! item[i].getText(4).equalsIgnoreCase("")){
					tot4 = tot4 + Double.parseDouble(item[i].getText(4));
				}
				
				if(! item[i].getText(5).equalsIgnoreCase("")){
					tot5 = tot5 + Double.parseDouble(item[i].getText(5));
				}
				
				if(! item[i].getText(6).equalsIgnoreCase("")){
					tot6 = tot6 + Double.parseDouble(item[i].getText(6));
				}
			}
		}
		
		final TableItem item1 = new TableItem(tblCommission,SWT.NONE);
		
		Font font1 = new Font(Display.getCurrent(), "Tahoma", 8, SWT.BOLD) ;
		item1.setFont(font1);
		
		item1.setText(0, "Total");
		item1.setText(1, decimalFormat.format(tot1));
		item1.setText(2, decimalFormat.format(tot2));
		item1.setText(3, decimalFormat.format(tot3));
		item1.setText(4, decimalFormat.format(tot4));
		item1.setText(5, decimalFormat.format(tot5));
		item1.setText(6, decimalFormat.format(tot6));
	}

	public class SourceBranchListener implements SelectionListener {

		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {
			

		}

		@Override
		public void widgetSelected(SelectionEvent event) {			
			Object source = event.getSource();

			if (cbSB == source) {

			} else if (btnDateGo == source) {
				try {

					CommissionSummaryDTO[] rowDTO = null;
					int index = cbSB.getSelectionIndex();
					String branch_code = "";
					String value = cbSB.getItem(index);
					if(value.equalsIgnoreCase("All")){
						branch_code = "%" ;
					}else{
						cbSB.setText(value);
						index = value.indexOf(" - ");
						branch_code = value.substring(index + 3);
					}
					
					String date = txtDate.getText();
					int index1 = date.indexOf("-");
					int day = Integer.parseInt(date.substring(0, index1));
					int month = Integer.parseInt(date.substring(index1 + 1));

					rowDTO = agencyhandler.getAdminCommission(branch_code, day,
							month);
					if (null != tblCommission)
						tblCommission.removeAll();
					if (null != rowDTO) {
						int rows = rowDTO.length;

						createTable(column_head.length, rows, column_head,
								rowDTO);
					}
				}

				catch (Exception exception) {
					exception.printStackTrace();
				}
			}else if(btnExportXL == source){
				try{
					ArrayList<CommissionSummaryDTO> rowDTO = null;
					String[] value = null;
					value = getViewCommissionHeading();
					rowDTO = getViewCommissionTable();
					prepareExcel(rowDTO,"ViewCommission",VIEW_COMMISSION_JRXML,value);
				}catch(Exception e){
					e.printStackTrace();
				}
				
				
			}else if(btnPrint == source){
				try{
					ArrayList<CommissionSummaryDTO> rowDTO = null;
					String[] value = null;
					value = getViewCommissionHeading();
					rowDTO = getViewCommissionTable();
					agencyhandler.preparePrint(rowDTO,VIEW_COMMISSION_JRXML,value);
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
				
		}

		private ArrayList<CommissionSummaryDTO> getViewCommissionTable() {
			// TODO Auto-generated method stub
			CommissionSummaryDTO dto = null;
			ArrayList<CommissionSummaryDTO> list = new ArrayList<CommissionSummaryDTO>();
			int length = 0;

			TableItem[] items = tblCommission.getItems();
			if (items != null) {
				length = items.length;
			}
			if (length > 0) {
				for (int i = 0; i < length; i++) {
					dto = new CommissionSummaryDTO();
					dto.setStation_name(items[i].getText(0));
					if (!items[i].getText(1).equals(""))
						dto.setBcTotal(Float.parseFloat(items[i].getText(1)));
					if (!items[i].getText(2).equals(""))
						dto.setDcTotal(Float.parseFloat(items[i].getText(2)));
					if (!items[i].getText(3).equals(""))
						dto.setRefundAmount(Float.parseFloat(items[i].getText(3))); 
					
					if (!items[i].getText(4).equals(""))
						dto.setRecoveryAmount(Float.parseFloat(items[i].getText(4)));
					
					if (!items[i].getText(5).equals(""))
						dto.setCccTotal(Float.parseFloat(items[i].getText(5)));
					
					if (!items[i].getText(6).equals(""))
						dto.setNetAmount(Float.parseFloat(items[i].getText(6)));
					
					
					list.add(dto);
				}
			}

			return list;

			
			
		}

		private String[] getViewCommissionHeading() {
			// TODO Auto-generated method stub
			String list[] = new String[2];
			list[0] = cbSB.getText();
			list[1] = txtDate.getText();
			
			return list;
		}
		
		private void prepareExcel(ArrayList list, String fileName,
				String jrxmlFile, String[] value) {
			try {
				/*Composite.display("Exporting...", STATUS_SUCCESS,
						SUCCESS_FONT_COLOR);*/
				agencyhandler.printReportExcel(list, fileName, jrxmlFile, value);

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
						//AdminComposite.display("Saving...", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						copyFile(xl, df, dialog.getFileName());
					}
					/*AdminComposite.display("Excel Saved Successfully",
							STATUS_SUCCESS, SUCCESS_FONT_COLOR);*/
				}
				else
				{
					/*AdminComposite.display("Excel Saving Cancelled",
							STATUS_SUCCESS, SUCCESS_FONT_COLOR);*/
				}
			} catch (Exception e) {
				//AdminComposite.display("", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
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
				

	}

}