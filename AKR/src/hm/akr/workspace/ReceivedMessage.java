/**
 * 
 */
package hm.akr.workspace;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.naming.NamingException;

import hm.akr.common.BeanUtil;
import hm.akr.common.FileUpload;
import hm.akr.common.SWTResourceManager;

import hm.akr.dto.MsgDTO;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;


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
 */
public class ReceivedMessage extends Composite {

	{
		// Register as a resource user - SWTResourceManager will
		// handle the obtaining and disposing of resources
		SWTResourceManager.registerResourceUser(this);
	}

	MsgDTO[] msg = null;

	BeanUtil beanutil = null;

	Shell shell = null;
	private Label lblStatusMessage;
	
	Canvas canvas1;

	private Display display;

	private Table tblMessage;

	// private Button btnDownload;

	private TableColumn colMessages;

	private TableColumn colPriority;
	
	Font font1 = new Font(display, "Tahoma", 8, SWT.NONE);
	
	

	public ReceivedMessage(Shell shell, int style) throws Exception {
		super(shell, style);
		this.shell = shell;
		beanutil = BeanUtil.getInstance();
		// TODO Auto-generated constructor stub
	}

	public Composite loadComposite() {
		try {
			this.setSize(1029, 708);

			tblMessage = new Table(this, SWT.H_SCROLL | SWT.FULL_SELECTION
					| SWT.V_SCROLL | SWT.BORDER | SWT.MULTI);
			tblMessage.setHeaderVisible(true);
			tblMessage.setLinesVisible(true);
			tblMessage.setBounds(120, 200, 637, 290);
			tblMessage.setFont(SWTResourceManager.getFont("Tahoma", 10, 0,
					false, false));
			{
				lblStatusMessage = new Label(this, SWT.NONE);
				lblStatusMessage.setBounds(117, 516, 463, 30);
			}

			{
				colMessages = new TableColumn(tblMessage, SWT.NONE);

				colMessages.setText("Messages");
				colMessages.setWidth(500);
			}

			{
				colPriority = new TableColumn(tblMessage, SWT.NONE);
				colPriority.setText("Download");
				colPriority.setWidth(125);
			}

			try {				
				String station = beanutil.getActingStationCode();
				if (station != null) {
					msg = beanutil.getMessage(station);
				}
			} catch (NamingException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			class check implements SelectionListener {
				int msgId = 0;
				int ind = 0;
				int read = 0;
				public check(int msg_id, int index, int read) {
					this.msgId = msg_id;
					this.ind = index;
					this.read = read;
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void widgetSelected(SelectionEvent arg0) {
					lblStatusMessage.setText("");
					String name = arg0.getSource().toString();
					int index = name.indexOf('{');
					int index2 = name.indexOf('}');
					name = name.substring(index + 1, index2);
					FileDialog dialog = new FileDialog(shell, SWT.SAVE);
					dialog.setFilterExtensions(new String[] { "*.*" });
					dialog.setFilterNames(new String[] { "All" });

					// wild
					// cards
					dialog.setFilterPath("WINDOWS/Desktop"); // Windows path
					dialog.setFileName(name);
					FileUpload fu = new FileUpload();
					String filepath = dialog.open();

					if (null != filepath) {
						File df = new File(filepath);

						try {
							lblStatusMessage.setText("Downloading.........");
							/*fu.download("www.akradsonwheels.com", "superakr1",
									"scudREDiaf60", "/akri/messages/" + name,
									df.getCanonicalFile());*/
							fu.download("www.akrparcelservice.com", "messages",
									"0manaPenne", "/" + name,
									df.getCanonicalFile());
							if(read == 0){							
								beanutil.updateMsgDownload(msgId);
								TableItem[] items = tblMessage.getItems();	
								for(int i = 0; i < items.length; i++){
									//if(i == index)
										items[ind].setFont(font1);
								}
							}
							lblStatusMessage.setText("Message saved successfully!");							
							
						} catch (MalformedURLException exception) {
							lblStatusMessage.setText("Download failed");
							exception.printStackTrace();
						} catch (IOException exception) {
							lblStatusMessage.setText("Download failed");
							exception.printStackTrace();
						}
					}

				}
			}
			if (msg != null) {
				int len = msg.length;
				if (len > 0) {					
					for (int i = 0; i < len; i++) {
						TableItem item = new TableItem(tblMessage, SWT.NONE);
					
						Font itemFont = new Font(display, "Tahoma", 14, SWT.BOLD);
						item.setFont(font1);
						
						if (msg[i].getPriority().equals("1")) {
							item.setText(0, msg[i].getMessage());
							item.setForeground(new Color(display, 187, 0, 0));							
						} else {
							item.setText(0, msg[i].getMessage());
						}
						
						if(msg[i].getMsg_read() == 0){
							item.setFont(itemFont);
						}
						
						if (msg[i].getMsg_filename() != null) {
							TableEditor editor = new TableEditor(tblMessage);

							Button btnDownload = new Button(tblMessage,
									SWT.LEFT);
							btnDownload.setAlignment(SWT.LEFT);
							btnDownload.setBounds(btnDownload.getSize().x,
									btnDownload.getSize().y, 50, 20);
							btnDownload.setText(msg[i].getMsg_filename());
							btnDownload.addSelectionListener(new check(msg[i].getMsg_id(),i,msg[i].getMsg_read()));
							btnDownload.pack();
							editor.minimumWidth = 125;
							editor.minimumHeight = 15;
							editor.horizontalAlignment = SWT.LEFT;
							editor.setEditor(btnDownload, item, 1);

						}
					}

				}
			}
		} catch (Exception exception) {

		}
		return this;
	}

}
