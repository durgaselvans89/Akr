package hm.akr.admin.station;

import javax.naming.NamingException;

import hm.akr.admin.station.handler.StationCompositeHandler;
import hm.akr.admin.workspace.AdminComposite;
import hm.akr.common.IUIConstants;
import hm.akr.msb.TreeComposite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public class StationComposite extends Composite implements SelectionListener, IUIConstants{

	private TabFolder msgTabFold;
	private TabItem tiLanguage;
	private Canvas cvsLang;
	private Composite treeLang;
	private Button rdTamil;
	private Button rdEnglish;
	private Button btnAssign;
	private StationCompositeHandler handler = null;
	
	
	public StationComposite(Composite cpt, int value) {
		super(cpt,value);
		try {
			handler = new StationCompositeHandler();
		} catch (NamingException e) {			
			e.printStackTrace();
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
	public Composite loadComposite() throws Exception {
		
		this.setSize(900,500);
		{
			msgTabFold = new TabFolder(this, SWT.NONE);
			msgTabFold.setBounds(20, 20, 790, 500);
			
			tiLanguage = new TabItem(msgTabFold, SWT.NONE);
			tiLanguage.setText("Language Settings");
			{
				cvsLang = new Canvas(msgTabFold, SWT.NONE);
				tiLanguage.setControl(cvsLang);
				{				
					treeLang = new TreeComposite(cvsLang, SWT.NONE, 350).loadComposite();
					treeLang.setBounds(76, 50, 300, 400);
					
					rdTamil = new Button(cvsLang, SWT.RADIO| SWT.LEFT);
					rdTamil.setBounds(400, 150, 60, 24);
					rdTamil.setText("Tamil");
					rdTamil.setSelection(true);
					
					rdEnglish = new Button(cvsLang, SWT.RADIO| SWT.LEFT);
					rdEnglish.setBounds(400, 200, 60, 24);
					rdEnglish.setText("English");
					
					btnAssign = new Button(cvsLang, SWT.PUSH);
					btnAssign.setBounds(490, 175, 50, 24);
					btnAssign.setText("Assign");
					btnAssign.addSelectionListener(this);
				}
			}
		}
		
		return this;
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		Object source = e.getSource();
		byte tamil = 0;
		String[] stations = null;
		if (source == btnAssign) {
			if (handler != null) {
				stations = ((TreeComposite) treeLang).getSelectedMSBStation();
				if (stations != null && stations.length > 0) {
					if (rdTamil.getSelection()) {
						tamil = 0;
					} else if (rdEnglish.getSelection()) {
						tamil = 1;
					}
					try {
						handler.setStationLanguage(stations, tamil);
						AdminComposite.display("Language assigned successfully",
								STATUS_SUCCESS, SUCCESS_FONT_COLOR);
						((TreeComposite) treeLang).clearTree();
					} catch (Exception e1) {
						AdminComposite.display("Language can not assigned",
								STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					}
				} else {
					AdminComposite.display("Please select atleast a station",
							STATUS_SUCCESS, SUCCESS_FONT_COLOR);
				}
			}
		}

	}

}
