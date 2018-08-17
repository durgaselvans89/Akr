/**
 * @author hm
 * @version 1.0
 */
package hm.akr.dto.printer;

/**
 * @version 1.0
 */
public class CashPrinterDTO {

	private String cr_no = null;

	private String lr_no = null;

	private String date = null;

	private String station_name = null;
	
	private String station_code = null;

	private String consigne_name = null;
	
	private String lr_date = null;
	
	private String from = null;
	
	private String to = null;
	
	private String dhc = null;
	
	private String no_of_Articles = null;
	
	private String weight = null;
	
	private String freight = null;
	
	private String dd_charges = null;
	
	private String others = null;
	
	private String total_amount  = null;
	
	private String demurrage = null;
	
	private String under_charge = null;
	
	private String postage = null;
	
	private String dd_extra = null;
	
	/**
	 * Constructor
	 */
	public CashPrinterDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return Returns the date.
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date The date to set.
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return Returns the lr_no.
	 */
	public String getLr_no() {
		return lr_no;
	}

	/**
	 * @param lr_no The lr_no to set.
	 */
	public void setLr_no(String lr_no) {
		this.lr_no = lr_no;
	}

	/**
	 * @return Returns the station_code.
	 */
	public String getStation_code() {
		return station_code;
	}

	/**
	 * @param station_code The station_code to set.
	 */
	public void setStation_code(String station_code) {
		this.station_code = station_code;
	}

	/**
	 * @return Returns the total_amount.
	 */
	public String getTotal_amount() {
		return total_amount;
	}

	/**
	 * @param total_amount The total_amount to set.
	 */
	public void setTotal_amount(String total_amount) {
		this.total_amount = total_amount;
	}

	/**
	 * @return Returns the cr_no.
	 */
	public String getCr_no() {
		return cr_no;
	}

	/**
	 * @param cr_no The cr_no to set.
	 */
	public void setCr_no(String cr_no) {
		this.cr_no = cr_no;
	}

	/**
	 * @return Returns the dd_extra.
	 */
	public String getDd_extra() {
		return dd_extra;
	}

	/**
	 * @param dd_extra The dd_extra to set.
	 */
	public void setDd_extra(String dd_extra) {
		this.dd_extra = dd_extra;
	}

	/**
	 * @return Returns the demurrage.
	 */
	public String getDemurrage() {
		return demurrage;
	}

	/**
	 * @param demurrage The demurrage to set.
	 */
	public void setDemurrage(String demurrage) {
		this.demurrage = demurrage;
	}

	/**
	 * @return Returns the postage.
	 */
	public String getPostage() {
		return postage;
	}

	/**
	 * @param postage The postage to set.
	 */
	public void setPostage(String postage) {
		this.postage = postage;
	}

	/**
	 * @return Returns the under_charge.
	 */
	public String getUnder_charge() {
		return under_charge;
	}

	/**
	 * @param under_charge The under_charge to set.
	 */
	public void setUnder_charge(String under_charge) {
		this.under_charge = under_charge;
	}

	public String getConsignee_name() {
		return consigne_name;
	}

	public void setConsignee_name(String consignor_name) {
		this.consigne_name = consignor_name;
	}

	public String getDd_charges() {
		return dd_charges;
	}

	public void setDd_charges(String dd_charges) {
		this.dd_charges = dd_charges;
	}

	public String getFreight() {
		return freight;
	}

	public void setFreight(String freight) {
		this.freight = freight;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getLr_date() {
		return lr_date;
	}

	public void setLr_date(String lr_date) {
		this.lr_date = lr_date;
	}

	public String getNo_of_Articles() {
		return no_of_Articles;
	}

	public void setNo_of_Articles(String no_of_Articles) {
		this.no_of_Articles = no_of_Articles;
	}

	public String getOthers() {
		return others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

	public String getStation_name() {
		return station_name;
	}

	public void setStation_name(String station_name) {
		this.station_name = station_name;
	}

	public String getDhc() {
		return dhc;
	}

	public void setDhc(String dhc) {
		this.dhc = dhc;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}
	
	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

}
