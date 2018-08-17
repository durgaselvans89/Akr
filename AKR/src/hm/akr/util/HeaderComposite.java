/**
 * 
 */
package hm.akr.util;

import hm.akr.common.SWTResourceManager;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Administrator
 * 
 */
public class HeaderComposite extends Composite {

	private CLabel lblHeader;

	String TITLE = null;

	

	public HeaderComposite(Shell shell, String title, int swtValue) {
		super(shell, swtValue);
		this.TITLE = title;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public Composite loadComposite() throws Exception {

		try {
			// RGB color code
			this.setBounds(1, 1, 1200, 101);
			RGB grayRGB = new RGB(245, 245, 245);
			final Color gray = new Color(Display.getCurrent(), grayRGB);
			Canvas canvas = new Canvas(this, SWT.NONE);
			canvas.setBounds(0, 0, 1024, 100);
			// Label with image and text
			lblHeader = new CLabel(canvas, SWT.RIGHT);
			lblHeader.setText(TITLE+"      ");
			lblHeader.setFont(new Font(Display.getCurrent(), "Arial_Rounded_MT_Bold_Bold", 20, 0));
			lblHeader.setForeground(gray);
			lblHeader.setBackgroundImage(SWTResourceManager
					.getImage("hm/akr/resources/Header.png"));
			
			lblHeader.setBounds(0, 15, 1024, 80);
			
			
		

		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return this;
	}
}
