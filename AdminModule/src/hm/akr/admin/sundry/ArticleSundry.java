/**
 * 
 */
package hm.akr.admin.sundry;

import hm.akr.admin.sundry.handler.SundryHandler;
import hm.akr.admin.workspace.AdminComposite;
import hm.akr.common.IUIConstants;
import hm.akr.dto.ArticleDTO;

import java.util.Arrays;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
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
 * @author user
 *
 */
public class ArticleSundry extends Composite implements IUIConstants, SelectionListener {

	private Canvas canvas;
	private Label lblArticleName;
	private Button btnSave;
	private Text txtArticle;
	private SundryHandler handler = null;
	private Table tblArticle;
	private Label lblHead;
	private TableColumn colSno;
	private TableColumn colArticle;
	ArticleDTO[] allArticles = null;
	private Button btnDelete;
	

	public ArticleSundry(Composite composite, int swtValue) throws Exception {
		super(composite, swtValue);

		try {

			handler = new SundryHandler();

		} catch (Exception e) {

			throw e;
		}

	}

	public Composite loadComposite() {
		try {
			{
				this.setSize(600, 560);

			}
			{
				{
					lblHead = new Label(this, SWT.NONE);
					lblHead.setText("Article");
					lblHead.setBounds(650, 10, 100, 21);
					lblHead.setFont(HEAD_FONT);
				}
				canvas = new Canvas(this, SWT.BORDER);
				canvas.setBounds(100, 20, 470, 500);
				

				{
					lblArticleName = new Label(canvas, SWT.NONE);
					lblArticleName.setText("Article Name");
					lblArticleName.setBounds(83, 17, 60, 15);
					lblArticleName.setFont(LABLE_FONT);
				}
				{
					txtArticle = new Text(canvas, SWT.BORDER);
					txtArticle.setBounds(147, 15, 100, 21);
				}
				
				{
					btnSave = new Button(canvas, SWT.PUSH | SWT.CENTER);
					btnSave.setText("Save");
					btnSave.setBounds(251, 14, 45, 24);
					btnSave.addSelectionListener(this);
				}
				
				{
					btnDelete = new Button(canvas, SWT.PUSH | SWT.CENTER);
					btnDelete.setText("Delete");
					btnDelete.setBounds(300, 14, 50, 24);
					btnDelete.addSelectionListener(this);
				}
				
				
				tblArticle = new Table(canvas, SWT.CHECK | SWT.MULTI | SWT.BORDER
						| SWT.FULL_SELECTION);
				tblArticle.setLinesVisible(true);
				tblArticle.setHeaderVisible(true);
				tblArticle.setBounds(80, 56, 218, 372);
				
				{
					colSno = new TableColumn(tblArticle, SWT.NONE);
					colSno.setText("S No");
					colSno.setWidth(50);
				}
				
				{
					colArticle = new TableColumn(tblArticle, SWT.NONE);
					colArticle.setText("Article");
					colArticle.setWidth(140);
				}				
				
				
				
				allArticles = handler.getSundryAricleTypes();
				
				if(allArticles != null){
					populateArticleTable(allArticles);
				}
				
			}
			
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		return this;
	}

	

	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void widgetSelected(SelectionEvent event) {
		Object source = event.getSource();
		if(source == btnSave){
			AdminComposite.display("", STATUS_SUCCESS,SUCCESS_FONT_COLOR);
			if(!txtArticle.getText().equals(""))
				try {
					handler.insertSundryArticle(txtArticle.getText());
					AdminComposite.display("Article Added Successfully", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					TableItem item = new TableItem(tblArticle, SWT.NONE);
					item.setText(0, String.valueOf(tblArticle.getItemCount()));
					item.setText(1, txtArticle.getText());
					txtArticle.setText("");
					sortArticleTable();
				} catch (Exception e) {					
					AdminComposite.display("Article Cant be Added", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
					e.printStackTrace();
				}
		}else if(source == btnDelete){
			AdminComposite.display("", STATUS_SUCCESS,SUCCESS_FONT_COLOR);
			TableItem[] items = tblArticle.getItems();			
			if (null != items ) {
				for (int i = 0; i < items.length; i++) {
					if (items[i].getChecked()) {
						if(handler != null){
							//System.out.println("sfsdfsf"+items[i].getText(2));
							try {
								handler.deleteSundryArticle(items[i].getText(1));
								AdminComposite.display("Article successfully deleted", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
								items[i].dispose();
								//tblArticle.remove(i);
							} catch (NumberFormatException e) {								
								e.printStackTrace();
							} catch (Exception e) {		
								AdminComposite.display("Article Cant be deleted", STATUS_SUCCESS, SUCCESS_FONT_COLOR);
								e.printStackTrace();
							}
						}
					}
				}
				
				refreshTable();
				
			}
		}
		
	}
	
	
	private void refreshTable() {
		TableItem[] items = tblArticle.getItems();
		int len = items.length;		
		for(int j = 0; j < len; j++){
			items[j].setText(0, String.valueOf(j+1));
		}
		
	}

	/**
	 * 
	 * @param allArticles
	 */
	private void populateArticleTable(ArticleDTO[] allArticles) {
		for(int i = 0; i < allArticles.length; i++){
			TableItem item = new TableItem(tblArticle, SWT.NONE);			
			item.setText(0, String.valueOf(i+1));
			item.setText(1, allArticles[i].getType());			
		}
	}
	
	private void sortArticleTable() {
		String[] articles = null;
		TableItem[] items = tblArticle.getItems();
		int len = items.length;
		articles = new String[len];
		for(int j = 0; j < len; j++){
			articles[j] = items[j].getText(1);
		}
		
		Arrays.sort(articles, String.CASE_INSENSITIVE_ORDER);
		
		tblArticle.removeAll();
		
		for(int i = 0; i < articles.length; i++){
			TableItem item = new TableItem(tblArticle, SWT.NONE);			
			item.setText(0, String.valueOf(i+1));
			item.setText(1, articles[i]);			
		}
	}

}
