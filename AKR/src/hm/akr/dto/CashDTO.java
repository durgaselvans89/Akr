/**
 * 
 * @version 1.0
 */
package hm.akr.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @version 1.0
 */
public class CashDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String cr_no = null;

	private String consigneeName = null;

	private String consignorName = null;

	private boolean status = false;

	private String lr_no = null;

	private Date date = null;

	private String station_code = null;

	private float total_amount = 0;

	private float demurrage = 0;

	private float under_charge = 0;

	private float postage = 0;

	private float dd_extra = 0;

	private Date lr_date = null;

	private String from_station = null;

	private String to_station = null;

	private float no_of_articles = 0;

	private float actual_weight = 0;

	private float bft = 0;

	private float ddc = 0;
	
	

	private float other = 0;

	private float lr_total = 0;

	private boolean crprint = false;

	private CashRegisterDTO cashdto = null;

	private String cnor_phone = null;

	private String cnee_phone = null;

	private SMSDTO[] sms = null;

	private int rate_type = 0;

	private int sms_alert = 0;
	
	private float delivery_ommission = 0;
	
	private int isDRSConfirmed = 0;

	/**
	 * @return the sms
	 */
	public SMSDTO[] getSms() {
		return sms;
	}

	/**
	 * @param sms
	 *            the sms to set
	 */
	public void setSms(SMSDTO[] sms) {
		this.sms = sms;
	}

	/**
	 * @return the cashdto
	 */
	public CashRegisterDTO getCashdto() {
		return cashdto;
	}

	/**
	 * @param cashdto
	 *            the cashdto to set
	 */
	public void setCashdto(CashRegisterDTO cashdto) {
		this.cashdto = cashdto;
	}

	public float getOther() {
		return other;
	}

	public void setOther(float other) {
		this.other = other;
	}

	/**
	 * @return Returns the actual_weight.
	 */
	public float getActual_weight() {
		return actual_weight;
	}

	/**
	 * @param actual_weight
	 *            The actual_weight to set.
	 */
	public void setActual_weight(float actual_weight) {
		this.actual_weight = actual_weight;
	}

	/**
	 * @return Returns the bft.
	 */
	public float getBft() {
		return bft;
	}

	/**
	 * @param bft
	 *            The bft to set.
	 */
	public void setBft(float bft) {
		this.bft = bft;
	}

	/**
	 * @return Returns the ddc.
	 */
	public float getDdc() {
		return ddc;
	}

	/**
	 * @param ddc
	 *            The ddc to set.
	 */
	public void setDdc(float dhc) {
		this.ddc = ddc;
	}
	
	

	/**
	 * @return Returns the from_station.
	 */
	public String getFrom_station() {
		return from_station;
	}

	/**
	 * @param from_station
	 *            The from_station to set.
	 */
	public void setFrom_station(String from_station) {
		this.from_station = from_station;
	}

	/**
	 * @return Returns the lr_date.
	 */
	public Date getLr_date() {
		return lr_date;
	}

	/**
	 * @param lr_date
	 *            The lr_date to set.
	 */
	public void setLr_date(Date lr_date) {
		this.lr_date = lr_date;
	}

	/**
	 * @return Returns the no_of_articles.
	 */
	public float getNo_of_articles() {
		return no_of_articles;
	}

	/**
	 * @param no_of_articles
	 *            The no_of_articles to set.
	 */
	public void setNo_of_articles(float no_of_articles) {
		this.no_of_articles = no_of_articles;
	}

	/**
	 * @return Returns the to_station.
	 */
	public String getTo_station() {
		return to_station;
	}

	/**
	 * @param to_station
	 *            The to_station to set.
	 */
	public void setTo_station(String to_station) {
		this.to_station = to_station;
	}

	/**
	 * Constructor
	 */
	public CashDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return Returns the date.
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            The date to set.
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return Returns the lr_no.
	 */
	public String getLr_no() {
		return lr_no;
	}

	/**
	 * @param lr_no
	 *            The lr_no to set.
	 */
	public void setLr_no(String lr_no) {
		this.lr_no = lr_no;
	}

	/**
	 * @return Returns the no.
	 */
	public String getNo() {
		return cr_no;
	}

	/**
	 * @param no
	 *            The no to set.
	 */
	public void setNo(String no) {
		this.cr_no = no;
	}

	/**
	 * @return Returns the station_code.
	 */
	public String getStation_code() {
		return station_code;
	}

	/**
	 * @param station_code
	 *            The station_code to set.
	 */
	public void setStation_code(String station_code) {
		this.station_code = station_code;
	}

	/**
	 * @return Returns the total_amount.
	 */
	public float getTotal_amount() {
		return total_amount;
	}

	/**
	 * @param total_amount
	 *            The total_amount to set.
	 */
	public void setTotal_amount(float total_amount) {
		this.total_amount = total_amount;
	}

	/**
	 * @return Returns the cr_no.
	 */
	public String getCr_no() {
		return cr_no;
	}

	/**
	 * @param cr_no
	 *            The cr_no to set.
	 */
	public void setCr_no(String cr_no) {
		this.cr_no = cr_no;
	}

	/**
	 * @return Returns the dd_extra.
	 */
	public float getDd_extra() {
		return dd_extra;
	}

	/**
	 * @param dd_extra
	 *            The dd_extra to set.
	 */
	public void setDd_extra(float dd_extra) {
		this.dd_extra = dd_extra;
	}

	/**
	 * @return Returns the demurrage.
	 */
	public float getDemurrage() {
		return demurrage;
	}

	/**
	 * @param demurrage
	 *            The demurrage to set.
	 */
	public void setDemurrage(float demurrage) {
		this.demurrage = demurrage;
	}

	/**
	 * @return Returns the postage.
	 */
	public float getPostage() {
		return postage;
	}

	/**
	 * @param postage
	 *            The postage to set.
	 */
	public void setPostage(float postage) {
		this.postage = postage;
	}

	/**
	 * @return Returns the under_charge.
	 */
	public float getUnder_charge() {
		return under_charge;
	}

	/**
	 * @param under_charge
	 *            The under_charge to set.
	 */
	public void setUnder_charge(float under_charge) {
		this.under_charge = under_charge;
	}

	public String getConsigneeName() {
		return consigneeName;
	}

	public void setConsigneeName(String consignorName) {
		this.consigneeName = consignorName;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * @return the lr_total
	 */
	public float getLr_total() {
		return lr_total;
	}

	/**
	 * @param lr_total
	 *            the lr_total to set
	 */
	public void setLr_total(float lr_total) {
		this.lr_total = lr_total;
	}

	/**
	 * @return the crprint
	 */
	public boolean isCrprint() {
		return crprint;
	}

	/**
	 * @param crprint
	 *            the crprint to set
	 */
	public void setCrprint(boolean crprint) {
		this.crprint = crprint;
	}

	public String getCnor_phone() {
		return cnor_phone;
	}

	public void setCnor_phone(String cnor_phone) {
		this.cnor_phone = cnor_phone;
	}

	public String getCnee_phone() {
		return cnee_phone;
	}

	public void setCnee_phone(String cnee_phone) {
		this.cnee_phone = cnee_phone;
	}

	public String getConsignorName() {
		return consignorName;
	}

	public void setConsignorName(String consignorName) {
		this.consignorName = consignorName;
	}

	/**
	 * @return the rate_type
	 */
	public int getRate_type() {
		return rate_type;
	}

	/**
	 * @param rate_type
	 *            the rate_type to set
	 */
	public void setRate_type(int rate_type) {
		this.rate_type = rate_type;
	}

	/**
	 * @return the sms_alert
	 */
	public int getSms_alert() {
		return sms_alert;
	}

	/**
	 * @param sms_alert
	 *            the sms_alert to set
	 */
	public void setSms_alert(int sms_alert) {
		this.sms_alert = sms_alert;
	}

	/**
	 * @return the isDRSConfirmed
	 */
	public int getIsDRSConfirmed() {
		return isDRSConfirmed;
	}

	/**
	 * @param isDRSConfirmed the isDRSConfirmed to set
	 */
	public void setIsDRSConfirmed(int isDRSConfirmed) {
		this.isDRSConfirmed = isDRSConfirmed;
	}

	/**
	 * @return the delivery_ommission
	 */
	public float getDelivery_ommission() {
		return delivery_ommission;
	}

	/**
	 * @param delivery_ommission the delivery_ommission to set
	 */
	public void setDelivery_ommission(float delivery_ommission) {
		this.delivery_ommission = delivery_ommission;
	}

}
