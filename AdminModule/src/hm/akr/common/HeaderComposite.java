package hm.akr.common;

import java.util.HashMap;

import hm.akr.admin.workspace.SWTResourceManager;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Resource;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;


/**
 * @author
 *Class to create HeaderComposite
 */
public class HeaderComposite extends Composite implements IUIConstants {

	private CLabel lblHeader;	
	
	String TITLE = "ABC LOGISTICS      ";
	
	private static HashMap<String, Resource> resources = new HashMap<String, Resource>();
	
	private static SWTResourceManager instance = new SWTResourceManager();
	
	/**
	 * Constructor for HeaderCoposite
	 * @param shell
	 * @param swtValue
	 */
	public HeaderComposite(Composite shell,int swtValue) {
		super(shell , swtValue);		
			
	}
	
	/**
	 * Method to load HeaderComposite
	 * @return
	 * @throws Exception
	 */
	public Composite loadComposite() throws Exception{
		
		try{
			RGB grayRGB = new RGB(245,245,245);
			final Color gray = new Color(Display.getCurrent(), grayRGB);
			Canvas canvas = new Canvas(this, SWT.NONE);
			canvas.setBounds(0, 0, 1024, 100);
			lblHeader = new CLabel(canvas, SWT.RIGHT );
			//lblHeader.setText(TITLE);			
			lblHeader.setFont(TEXT_FONT);
			lblHeader.setForeground(gray);
			lblHeader.setBackgroundImage(getImage("hm/akr/resources/AKRHeader.PNG"));
			lblHeader.setBounds(0, 10, 1024, 100);
				
			
		}catch(Exception exception){
			exception.printStackTrace();
		}	
		
		return this;
	}
	
	/**
	 * 
	 * @param url
	 * @return
	 */
	private Image getImage(String url) {
		try {
			url = url.replace('\\', '/');
			if (url.startsWith("/"))
				url = url.substring(1);
			if (resources.containsKey(url))
				return (Image) resources.get(url);
			Image img = new Image(Display.getDefault(), instance.getClass()
					.getClassLoader().getResourceAsStream(url));
			if (img != null)
				resources.put(url, img);
			return img;
		} catch (Exception e) {
			System.err
					.println("SWTResourceManager.getImage: Error getting image "
							+ url + ", " + e);
			return null;
		}
	}
}
