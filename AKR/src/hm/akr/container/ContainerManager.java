package hm.akr.container;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;

import hm.akr.common.BeanUtil;
import hm.akr.container.admin.AdminComposite;
import hm.akr.container.admin.CounterReport;
import hm.akr.container.admin.MonitorReportsComposite;
import hm.akr.container.admin.ReportsMenu;
import hm.akr.container.admin.SoftwareComposite;
import hm.akr.container.admin.SundryBooking;
import hm.akr.container.commission.CommissionComposite;
import hm.akr.container.cr.CashRegisterComposite;
import hm.akr.container.customer.CustomerComposite;
import hm.akr.container.drs.DailyRemittanceComposite;
import hm.akr.container.gmr.GMRComposite;
import hm.akr.container.history.HistoryComposite;
//import hm.akr.container.inward.InwardAnalysisComposite;
import hm.akr.container.lr.LRComposite;
import hm.akr.container.reports.ReportsComposite;
import hm.akr.util.HeaderComposite;
import hm.akr.workspace.ReceivedMessage;
import hm.akr.container.admin.CancelledLr;

/**
 * 
 * @version 1.0
 */
public class ContainerManager {

	public Composite composite = null;

	public Composite composite1 = null;

	//public Composite composite2 = null;

	/**
	 * Constructor Method
	 */
	public ContainerManager() {

	}

	/**
	 * Method to get the LR Container
	 * 
	 * @param shell
	 * @return Composite
	 */
	public Composite addLRContainer(Shell shell) throws Exception {

		
		try {
			// Dispose the current container
			if (!(composite instanceof LRComposite)) {
				addHeaderContainer(shell,"LR");
				if (null != composite && !composite.isDisposed()) {
					composite.dispose();
				}

				composite = (new LRComposite(shell, SWT.BORDER))
						.loadComposite();

			}
		} catch (Exception exception) {
			throw exception;
		}

		return composite;

	}

	/**
	 * 
	 * @param shell
	 * @return
	 * @throws Exception
	 */
	public Composite addSoftwareContainer(Shell shell, String version)
			throws Exception {

		
		
		try {
			// Dispose the current container
			if (!(composite instanceof SoftwareComposite)) {
				addHeaderContainer(shell,"VERSION");
				if (null != composite && !composite.isDisposed()) {
					composite.dispose();
				}

				composite = (new SoftwareComposite(shell, SWT.BORDER)
						.loadComposite(version));

			}
		} catch (Exception exception) {
			throw exception;
		}

		return composite;

	}

	/**
	 * 
	 * @param shell
	 * @return
	 * @throws Exception
	 */
	private Composite addHeaderContainer(Shell shell, String title)
			throws Exception {

		// For Header Composite
		if (null != composite1 && !composite1.isDisposed()) {
			composite1.dispose();
		}

		/*if (null != composite2 && !composite2.isDisposed()) {
			composite2.dispose();
		}*/

		composite1 = (new HeaderComposite(shell, title, SWT.BORDER))
				.loadComposite();

		return composite1;
	}

	/**
	 * Method to get the Cash Register Container
	 * 
	 * @param shell
	 * @return Composite
	 */
	public Composite addCRContainer(Shell shell) {

		
		// Dispose the current container
		if (!(composite instanceof CashRegisterComposite)) {
			try {
				addHeaderContainer(shell,"CASH RECEIPT");
			} catch (Exception e) {		
				e.printStackTrace();
			}
			if (null != composite && !composite.isDisposed())
				composite.dispose();

			composite = (new CashRegisterComposite(shell, SWT.BORDER))
					.loadComposite();
		}
		return composite;
	}

	/**
	 * Method to get the GMR Container
	 * 
	 * @param shell
	 * @return Compositess
	 */
	public Composite addGMRContainer(Shell shell) {

		
		// Dispose the current container
		if(BeanUtil.getDbName() == null || BeanUtil.getDbName().equals("")){
		if (!(composite instanceof GMRComposite)) {
			try {
				addHeaderContainer(shell,"GOODS MOVEMENT REGISTER");
			} catch (Exception e) {			
				e.printStackTrace();
			}
			if (null != composite && !composite.isDisposed())
				composite.dispose();

			composite = (new GMRComposite(shell, SWT.BORDER)).loadComposite();
		}
		}
		
		return composite;		
	}

	/**
	 * @throws Exception
	 * 
	 */
	public Composite addCustomerContainer(Shell shell) {

		
		// Dispose the current container
		if(BeanUtil.getDbName() == null || BeanUtil.getDbName().equals("")){
		if (!(composite instanceof CustomerComposite)) {
			try {
				addHeaderContainer(shell,"CUSTOMER");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (null != composite && !composite.isDisposed())
				composite.dispose();

			composite = (new CustomerComposite(shell, SWT.BORDER))
					.loadComposite();
		}
		}
		
		return composite;
	}

	/**
	 * Method to get the Daily Remittance Container
	 * 
	 * @param shell
	 * @return Composite
	 */
	public Composite addDRSContainer(Shell shell) {

	
		// Dispose the current container
		
		if (!(composite instanceof DailyRemittanceComposite)) {
			try {
				addHeaderContainer(shell,"DAILY REMITTANCE STATEMENT");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (null != composite && !composite.isDisposed()) {
				composite.dispose();
			}

			composite = (new DailyRemittanceComposite(shell, SWT.BORDER))
					.loadComposite();
		}
		
		
		return composite;
	}

	/**
	 * @throws Exception
	 * @throws Exception
	 * 
	 */

	public Composite addAdminContainer(Shell shell) throws Exception {

		
		
		// Dispose the current container
		if (!(composite instanceof AdminComposite)) {

			try {
				addHeaderContainer(shell,"ADMIN");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (null != composite && !composite.isDisposed()) {
				composite.dispose();
			}

			composite = (new AdminComposite(shell, SWT.BORDER)).loadComposite();
		}
		return composite;
	}

	/**
	 * Method to get the Reports Container
	 * 
	 * @param shell
	 * @return Composite
	 */
	public Composite addReportsContainer(Shell shell) {
		
		if (!(composite instanceof ReportsComposite)) {
			try {
				addHeaderContainer(shell,"REPORTS");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Dispose the current container
			if (null != composite && !composite.isDisposed()) {
				composite.dispose();
			}

			composite = (new ReportsComposite(shell, SWT.BORDER))
					.loadComposite();
		}
		return composite;
	}

	/**
	 * 
	 * @param shell
	 * @return
	 * @throws Exception
	 */
	public Composite addMonitorReportsContainer(Shell shell) throws Exception {
		

		if (!(composite instanceof MonitorReportsComposite)) {
			try {
				addHeaderContainer(shell,"MONITORING REPORTS");
			
			// Dispose the current container
			if (null != composite && !composite.isDisposed()) {
				composite.dispose();
			}

			composite = (new MonitorReportsComposite(shell, SWT.BORDER))
					.loadComposite();
			} catch (Exception e) {			
				e.printStackTrace();
			}
		}
		return composite;
	}

	/**
	 * Method to get the Messages Container
	 * 
	 * @param shell
	 * @return Composite
	 * @throws Exception
	 */
	public Composite addMsgContainer(Shell shell) throws Exception {
		
		// Dispose the current container
		if(BeanUtil.getDbName() == null || BeanUtil.getDbName().equals("")){
		if (!(composite instanceof ReceivedMessage)) {
			try {
				addHeaderContainer(shell,"MESSAGE");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (null != composite && !composite.isDisposed()) {
				composite.dispose();
			}

			composite = (new ReceivedMessage(shell, SWT.BORDER))
					.loadComposite();
		}
		}
		return composite;
	}

public Composite addCommissionContainer(Shell shell) {
		
		//composite 
		try {	
		
		// Dispose the current container
			
		if (!(composite instanceof CommissionComposite)) {
			
				addHeaderContainer(shell,"COMMISSION");
			
			if (null != composite && !composite.isDisposed())
				composite.dispose();
			
			composite = (new CommissionComposite(shell, SWT.BORDER)).loadComposite();
			composite.setBounds(10, 10, 1020, 700);
		}
			
		} catch (Exception e) {				
			e.printStackTrace();
		}
		
		return composite;
	}
	
	/*public Composite addInwardContainer(Shell shell) throws Exception {
		
		// Dispose the current container
		if (!(composite instanceof InwardAnalysisComposite)) {
			try {
				addHeaderContainer(shell,"INWARD ANALYSIS");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (null != composite && !composite.isDisposed()) {
				composite.dispose();
			}

			composite = (new InwardAnalysisComposite(shell, SWT.BORDER))
					.loadComposite();
		}
		return composite;
	}*/
	
	public Composite addHistoryContainer(Shell shell) throws Exception {
		
		// Dispose the current container
		if (!(composite instanceof HistoryComposite)) {
			try {
				addHeaderContainer(shell,"HISTORY");
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (null != composite && !composite.isDisposed()) {
				composite.dispose();
			}

			composite = (new HistoryComposite(shell, SWT.BORDER))
					.loadComposite();
		}
		return composite;
	}

	public Composite ReprtsMenuContainer(Shell shell) {
		// TODO Auto-generated method stub
		//if (!(composite instanceof ReportsMenu)) {
			try {
				addHeaderContainer(shell,"MONITORING REPORTS");
			
			// Dispose the current container
			if (null != composite && !composite.isDisposed()) {
				composite.dispose();
			}

			composite = (new ReportsMenu(shell, SWT.BORDER))
					.loadComposite();
			} catch (Exception e) {			
				e.printStackTrace();
			}
		//}
		return composite;
		
	}

	public Composite CancelledLrContainer(Shell shell) {
		// TODO Auto-generated method stub
		System.out.println("compositefdsdf"+composite );
		if (!(composite instanceof CancelledLr )) {
			try {
				addHeaderContainer(shell,"MONITORING REPORTS");
			
				// Dispose the current container
				if (null != composite && !composite.isDisposed()) {
					composite.dispose();
				}
				
				//composite = (new CancelledLr(shell, SWT.BORDER)).loadcomposite();
				System.out.println("compositefdsdf"+composite );
			} catch (Exception e) {			
				e.printStackTrace();
			}
			
		}
		return composite;
	}

	public Composite CounterReportContainer(Shell shell) {
		// TODO Auto-generated method stub

		if ((composite instanceof ReportsMenu)) {
			try {
				//addHeaderContainer(shell,"MONITORING REPORTS");
			
				// Dispose the current container
				if (null != composite && !composite.isDisposed()) {
					composite.dispose();
				}
				
				///composite = (new CounterReport(shell, SWT.BORDER)).loadcomposite();
				
			} catch (Exception e) {			
				e.printStackTrace();
			}
			
		}
		return composite;
	}

	public Composite sundrybookingContainer(Shell shell) {
		// TODO Auto-generated method stub
		Composite sundry = shell.getParent();
		
		if ((composite instanceof ReportsMenu)) {
			try {
				//addHeaderContainer(shell,"MONITORING REPORTS");
			
				// Dispose the current container
				if (null != composite && !composite.isDisposed()) {
					composite.dispose();
				}
				
				//composite = (new SundryBooking(shell, SWT.BORDER)).loadcomposite();
				
			} catch (Exception e) {			
				e.printStackTrace();
			}
			
		}
		return composite;
	}
	
}
