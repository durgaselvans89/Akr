package hm.akr.dto;

import java.io.Serializable;

import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;

public class ArticleUIDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Combo cbname = null;

	private Text txtnoofarticle = null;

	private Text txtartvalue = null;

	private Text txtweight = null;

	private Text txtlength = null;

	private Text txtbreadth = null;

	private Text txtheight = null;

	private Text txtchargeweight = null;

	private Text txtdesc = null;

	private Button btndelete = null;

	/**
	 * @return the cbname
	 */
	public Combo getCbname() {
		return cbname;
	}

	/**
	 * @param cbname
	 *            the cbname to set
	 */
	public void setCbname(Combo cbname) {
		this.cbname = cbname;
	}

	/**
	 * @return the txtnoofarticle
	 */
	public Text getTxtnoofarticle() {
		return txtnoofarticle;
	}

	/**
	 * @param txtnoofarticle
	 *            the txtnoofarticle to set
	 */
	public void setTxtnoofarticle(Text txtnoofarticle) {
		this.txtnoofarticle = txtnoofarticle;
	}

	/**
	 * @return the txtartvalue
	 */
	public Text getTxtartvalue() {
		return txtartvalue;
	}

	/**
	 * @param txtartvalue
	 *            the txtartvalue to set
	 */
	public void setTxtartvalue(Text txtartvalue) {
		this.txtartvalue = txtartvalue;
	}

	/**
	 * @return the txtweight
	 */
	public Text getTxtweight() {
		return txtweight;
	}

	/**
	 * @param txtweight
	 *            the txtweight to set
	 */
	public void setTxtweight(Text txtweight) {
		this.txtweight = txtweight;
	}

	/**
	 * @return the txtlength
	 */
	public Text getTxtlength() {
		return txtlength;
	}

	/**
	 * @param txtlength
	 *            the txtlength to set
	 */
	public void setTxtlength(Text txtlength) {
		this.txtlength = txtlength;
	}

	/**
	 * @return the txtbreadth
	 */
	public Text getTxtbreadth() {
		return txtbreadth;
	}

	/**
	 * @param txtbreadth
	 *            the txtbreadth to set
	 */
	public void setTxtbreadth(Text txtbreadth) {
		this.txtbreadth = txtbreadth;
	}

	/**
	 * @return the txtheight
	 */
	public Text getTxtheight() {
		return txtheight;
	}

	/**
	 * @param txtheight
	 *            the txtheight to set
	 */
	public void setTxtheight(Text txtheight) {
		this.txtheight = txtheight;
	}

	/**
	 * @return the txtchargeweight
	 */
	public Text getTxtchargeweight() {
		return txtchargeweight;
	}

	/**
	 * @param txtchargeweight
	 *            the txtchargeweight to set
	 */
	public void setTxtchargeweight(Text txtchargeweight) {
		this.txtchargeweight = txtchargeweight;
	}

	/**
	 * @return the txtdesc
	 */
	public Text getTxtdesc() {
		return txtdesc;
	}

	/**
	 * @param txtdesc
	 *            the txtdesc to set
	 */
	public void setTxtdesc(Text txtdesc) {
		this.txtdesc = txtdesc;
	}

	/**
	 * @return the btndelete
	 */
	public Button getBtndelete() {
		return btndelete;
	}

	/**
	 * @param btndelete
	 *            the btndelete to set
	 */
	public void setBtndelete(Button btndelete) {
		this.btndelete = btndelete;
	}

}
