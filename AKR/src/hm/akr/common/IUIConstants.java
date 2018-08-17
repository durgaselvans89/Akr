package hm.akr.common;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;

public interface IUIConstants {

	/*************************FONT STYLES**********************************/
	
	public static final String HEAD_FONT_NAME = "Arial";

	public static final String LABLE_FONT_NAME = "";

	public static final String TEXT_FONT_NAME = "Arial";

	public static final String COMBO_FONT_NAME = "";

	public static final String BUTTON_FONT_NAME = "";
	
	public static final String SPECIAL_FONT_NAME = "Verdana";
	
	public static final String LINK_FONT_NAME = "Arial";
	
	public static final String LINK_FOCUS_FONT_NAME = "Verdana";	
	
	public static final String STATUS_SUCEESS = "Verdana";
	
	public static final String HYPERLINK_FONT_NAME = "Arial";
	
	public static final String REPLINK_FOCUS_FONT_NAME = "Verdana";
	
	
	
	/*************************FONT SIZE**********************************/

	public static final int HEAD_FONT_SIZE = 14;

	public static final int LABEL_FONT_SIZE = 8;

	public static final int PLUS_FONT_SIZE = 14;
	
	public static final int TEXT_FONT_SIZE = 9;

	public static final int COMBO_FONT_SIZE = 8;

	public static final int BUTTON_FONT_SIZE = 9;
	
	public static final int SUCCESS_FONT_SIZE = 10;
	
	public static final int SPECIAL_FONT_SIZE = 12;
	
	public static final int LINK_FONT_SIZE = 9;
	
	public static final int LINK_FOCUS_FONT_SIZE = 10;
	
	public static final int HYPERLINK_FONT_SIZE = 10;
	
	public static final int REPLABEL_FONT_SIZE = 12;
	
	
	
	/*************************COLORS********************************/
	
	public static final Color HEAD_FONT_COLOR = new Color(Display.getCurrent(), 139,69,19);

	public static final String LABLE_FONT_COLOR = "";

	public static final String TEXT_FONT_COLOR = "";

	public static final String COMBO_FONT_COLOR = "";

	public static final String BUTTON_FONT_COLOR = "";	
	
	public static final Color LINK_COLOR = new Color(Display.getCurrent(), 0,51,0);
	
	public static final Color LINK_FOCUS_COLOR = new Color(Display.getCurrent(), 175,0,0);	
	
	public static final Color SUCCESS_FONT_COLOR =  Display.getCurrent().getSystemColor(SWT.COLOR_RED);	
	
	public static final Color FORE_COLOR = new Color(Display.getCurrent(), 119,136,153);
	
	
	public static final Color BACK_COLOR = new Color(Display.getCurrent(), 49,53,61);//blue 58,89,153
	
	public static final Color LINK_BACK_COLOR = new Color(Display.getCurrent(), 255,228,225);
	
	public static final Color QUOTATION_COLOR = new Color(Display.getCurrent(),10,80,230);
	
	public static final Color REPLINK_FOCUS_COLOR = new Color(Display.getCurrent(), 175,0,0);
	
	public static final Color REPLINK_COLOR = new Color(Display.getCurrent(), 0,51,0);

	
	
	/*************************FONTS**********************************/
	
	public static final Font HEAD_FONT = new Font(Display.getCurrent(),
			HEAD_FONT_NAME, HEAD_FONT_SIZE, 1);

	public static final Font LABLE_FONT = new Font(Display.getCurrent(),
			LABLE_FONT_NAME, LABEL_FONT_SIZE, 0);

	public static final Font PLUS_FONT = new Font(Display.getCurrent(),
			LABLE_FONT_NAME, PLUS_FONT_SIZE, 0);
	
	public static final Font TEXT_FONT = new Font(Display.getCurrent(),
			TEXT_FONT_NAME, TEXT_FONT_SIZE, 0);

	public static final Font COMBO_FONT = new Font(Display.getCurrent(),
			COMBO_FONT_NAME, COMBO_FONT_SIZE, 0);

	public static final Font BUTTON_FONT = new Font(Display.getCurrent(),
			BUTTON_FONT_NAME, BUTTON_FONT_SIZE, 0);
	
	public static final Font STATUS_SUCCESS = new Font(Display.getCurrent(),
			STATUS_SUCEESS, SUCCESS_FONT_SIZE, 1);
	
	public static final Font SPECIAL_FONT = new Font(Display.getCurrent(),
			SPECIAL_FONT_NAME, SPECIAL_FONT_SIZE, 0);
	
	
	public static final Font LINK_FONT = new Font(Display.getCurrent(),
			LINK_FONT_NAME, LINK_FONT_SIZE, 0);
	

	public static final Font LINK_FOCUS_FONT = new Font(Display.getCurrent(),
			LINK_FOCUS_FONT_NAME, LINK_FOCUS_FONT_SIZE, 0);
	
	public static final Font REPLINK_FONT = new Font(Display.getCurrent(),HYPERLINK_FONT_NAME, HYPERLINK_FONT_SIZE, 0);
	
	public static final Font REPLABEL_FONT = new Font(Display.getCurrent(),HYPERLINK_FONT_NAME, REPLABEL_FONT_SIZE, 0);
	
	public static final Font REPLINK_FOCUS_FONT = new Font(Display.getCurrent(),REPLINK_FOCUS_FONT_NAME, HYPERLINK_FONT_SIZE, 0);
	
	
}
