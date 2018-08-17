package hm.akr.admin.sundry.commodity;

import hm.akr.common.IUIConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * 
 * @author
 * 
 */
public class CommoditySetComposite extends Composite implements IUIConstants {

	private Label lblName;

	private Label lblValue;

	private Text txtName;

	private Text txtValue;

	private Label lblUnit;

	private Button btnSet;

	/**
	 * 
	 * @param parent
	 * @param style
	 * @param selectedTab
	 */
	public CommoditySetComposite(Composite parent, int style, String selectedTab) {
		super(parent, style);

	}

	/**
	 * 
	 * @return
	 */
	public Composite loadComposite() {
		GridLayout gl = new GridLayout();
		gl.numColumns = 4;
		gl.horizontalSpacing = 30;
		gl.verticalSpacing = 10;
		gl.marginWidth = 50;
		gl.marginHeight = 50;

		this.setLayout(gl);

		{
			lblName = new Label(this, SWT.NONE);
			lblName.setText("Commodity Name");
			lblName.setFont(LABLE_FONT);
		}
		{
			GridData gd = new GridData();

			gd.horizontalSpan = 3;

			lblValue = new Label(this, SWT.NONE);
			lblValue.setText("Value");
			lblValue.setLayoutData(gd);
			lblValue.setFont(LABLE_FONT);
		}

		{
			txtName = new Text(this, SWT.NONE);
			txtName.setFont(TEXT_FONT);

		}
		{
			txtValue = new Text(this, SWT.NONE);
			txtValue.setFont(TEXT_FONT);

		}
		{
			lblUnit = new Label(this, SWT.NONE);
			lblUnit.setText("per Km");
			lblUnit.setFont(LABLE_FONT);

		}
		{
			btnSet = new Button(this, SWT.NONE);
			btnSet.setText("Set");
			btnSet.setFont(BUTTON_FONT);
		}

		return this;
	}

}
