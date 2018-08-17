package hm.akr.container.lr;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
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
public class LRCancelDialog extends Composite {
	private Shell shell;
	private Text txtlrcancelOthers;
	private Button LrCancelOption5;
	private Button LrCancelOption4;
	private Button LrCancelOption3;
	private Button LrCancelOption2;
	private Button LrCancelOption1;
	private Canvas canLRCancelOption;
	public String lrCancelOption = null;
	private Label lblLrcancelQuestion;
	private Button btnLRCancelOptionSet;
	String selectedOption = null;

	/**
	 * Constructor Method for GMRpriority composite class
	 * 
	 * @param shell
	 * @param swt
	 * @param set
	 */
	LRCancelDialog(Shell shell, int swt) {
		super(shell, swt);
		this.shell = shell;
		shell.setBounds(200, 200, 700, 500);

	}

	/**
	 * Method to load Composite
	 */
	public void loadComposite(String type) {

		shell.addDisposeListener(new LRCancelOptionListener());
		try {
			{
				canLRCancelOption = new Canvas(shell.getShell(), SWT.NONE);
				canLRCancelOption.setSize(493, 382);
				{
					LrCancelOption1 = new Button(canLRCancelOption, SWT.RADIO
							| SWT.LEFT);
					LrCancelOption1
							.setText("Wrong details entered by AKR Agent.");
					LrCancelOption1.setBounds(75, 50, 390, 30);
					LrCancelOption1
							.addSelectionListener(new LRCancelOptionListener());
				}
				{
					LrCancelOption2 = new Button(canLRCancelOption, SWT.RADIO
							| SWT.LEFT);
					String cancelType = null;
					if (null != type) {
						if (type.equalsIgnoreCase("Topay"))
							cancelType = "Customer wants to book in Paid";
						else if (type.equalsIgnoreCase("Paid"))
							cancelType = "Customer wants to book in Topay";

						else
							cancelType = "Customer wants to book in Paid/Topay.";
					} else
						cancelType = "Customer wants to book in Paid/Topay.";

					LrCancelOption2.setText(cancelType);
					LrCancelOption2.setBounds(75, 100, 390, 30);
					LrCancelOption2
							.addSelectionListener(new LRCancelOptionListener());
				}
				{
					LrCancelOption3 = new Button(canLRCancelOption, SWT.RADIO
							| SWT.LEFT);
					LrCancelOption3.setText("Customer requested cancellation.");
					LrCancelOption3.setBounds(75, 150, 390, 30);
					LrCancelOption3
							.addSelectionListener(new LRCancelOptionListener());
				}
				{
					LrCancelOption4 = new Button(canLRCancelOption, SWT.RADIO
							| SWT.LEFT);
					LrCancelOption4
							.setText("Customer cancelled because rate is too high.");
					LrCancelOption4.setBounds(75, 200, 390, 30);
					LrCancelOption4
							.addSelectionListener(new LRCancelOptionListener());
				}
				{
					LrCancelOption5 = new Button(canLRCancelOption, SWT.RADIO
							| SWT.LEFT);
					LrCancelOption5.setText("Other");
					LrCancelOption5.setBounds(75, 250, 48, 30);
					LrCancelOption5
							.addSelectionListener(new LRCancelOptionListener());
				}
				{
					txtlrcancelOthers = new Text(canLRCancelOption, SWT.BORDER);
					txtlrcancelOthers.setBounds(77, 288, 388, 25);
				}
				{
					btnLRCancelOptionSet = new Button(canLRCancelOption,
							SWT.PUSH | SWT.CENTER);
					btnLRCancelOptionSet.setText("Ok");
					btnLRCancelOptionSet.setBounds(213, 327, 47, 33);
					btnLRCancelOptionSet
							.addSelectionListener(new LRCancelOptionListener());
				}
				{
					lblLrcancelQuestion = new Label(canLRCancelOption, SWT.NONE);
					lblLrcancelQuestion
							.setText("What is the reason to Cancel the LR?");
					lblLrcancelQuestion.setBounds(12, 20, 447, 30);
				}
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
	public class LRCancelOptionListener implements DisposeListener,
			SelectionListener {

		@Override
		public void widgetDisposed(DisposeEvent arg0) {
			getCancelOption();

		}

		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {

		}

		@Override
		public void widgetSelected(SelectionEvent event) {
			Object source = event.getSource();
			if (source == LrCancelOption1 || source == LrCancelOption2
					|| source == LrCancelOption3 || source == LrCancelOption4) {
				txtlrcancelOthers.setEnabled(false);

			} else if (source == LrCancelOption5) {
				txtlrcancelOthers.setEnabled(true);
			} else if (source == btnLRCancelOptionSet) {
				shell.dispose();
			}

		}

	}

	/**
	 * 
	 */
	public void getCancelOption() {
		if (LrCancelOption1.getSelection())
			selectedOption = LrCancelOption1.getText();
		else if (LrCancelOption2.getSelection())
			selectedOption = LrCancelOption2.getText();
		else if (LrCancelOption3.getSelection())
			selectedOption = LrCancelOption3.getText();
		else if (LrCancelOption4.getSelection())
			selectedOption = LrCancelOption4.getText();
		else if (LrCancelOption5.getSelection())
			selectedOption = txtlrcancelOthers.getText();

		return;
	}

}