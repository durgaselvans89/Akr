package hm.akr.container.admin;

import hm.akr.common.SWTResourceManager;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class SoftwareComposite extends Composite {

	private Group group1;
	private Label lblVersion;
	private Label lblversion;

	public SoftwareComposite(Shell shell, int swtValue) throws Exception {
		super(shell, swtValue);
		try {

		} catch (Exception e) {

			throw e;
		}

	}

	/**
	 * 
	 */
	public Composite loadComposite(String version) {
		{

			this.setSize(971, 297);

		}

		{
			lblversion = new Label(this, SWT.NONE);
			lblversion.setText("Version:");
			lblversion.setBounds(320, 136, 86, 30);
			lblversion.setFont(SWTResourceManager.getFont("Tahoma", 14, 1,false, false));
		}
		{
			lblVersion = new Label(this, SWT.NONE);
			lblVersion.setBounds(432, 135, 135, 19);
			lblVersion.setText(version);
			lblVersion.setFont(SWTResourceManager.getFont("Tahoma", 14, 0,
					false, false));
		}
		{
			group1 = new Group(this, SWT.NONE);
			GridLayout group1Layout = new GridLayout();
			group1Layout.makeColumnsEqualWidth = true;
			group1.setLayout(group1Layout);
			group1.setText("Software Version");
			group1.setBounds(294, 93, 289, 104);
		}
		return this;
	}

}