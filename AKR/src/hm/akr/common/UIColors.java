package hm.akr.common;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public class UIColors {
	Display display = Display.getDefault();

	public  Color red = display.getSystemColor(SWT.COLOR_RED);
	public  Color green = display.getSystemColor(SWT.COLOR_GREEN);
	public  Color blue = display.getSystemColor(SWT.COLOR_BLUE);
}
