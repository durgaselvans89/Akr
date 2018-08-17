package hm.akr.dto.printer;

/**
 * @version 1.0 DTO class for DRS
 */

public class DRSPrinterDTO {

	private String no = null;

	private String date = null;

	private String station_code = null;

	private String station_name = null;

	private String paid_collection_from = null;

	private String paid_collection_to = null;

	private String topay_collection_from = null;

	private String topay_collection_to = null;

	private float paid_collection_amount = 0;

	private float topay_collection_amount = 0;

	private float total = 0;

	private float add_remittance = 0;

	private float less_remittance = 0;

	private float grand_total = 0;

	/**
	 * Constructor method
	 */
	public DRSPrinterDTO() {
		super();
	}

	/**
	 * @return Returns the add_remittance.
	 */
	public float getAdd_remittance() {
		return add_remittance;
	}

	/**
	 * @param add_remittance
	 *            The add_remittance to set.
	 */
	public void setAdd_remittance(float add_remittance) {
		this.add_remittance = add_remittance;
	}

	/**
	 * @return Returns the date.
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            The date to set.
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return Returns the grand_total.
	 */
	public float getGrand_total() {
		return grand_total;
	}

	/**
	 * @param grand_total
	 *            The grand_total to set.
	 */
	public void setGrand_total(float grand_total) {
		this.grand_total = grand_total;
	}

	/**
	 * @return Returns the less_remittance.
	 */
	public float getLess_remittance() {
		return less_remittance;
	}

	/**
	 * @param less_remittance
	 *            The less_remittance to set.
	 */
	public void setLess_remittance(float less_remittance) {
		this.less_remittance = less_remittance;
	}

	/**
	 * @return Returns the no.
	 */
	public String getNo() {
		return no;
	}

	/**
	 * @param no
	 *            The no to set.
	 */
	public void setNo(String no) {
		this.no = no;
	}

	/**
	 * @return Returns the paid_collection_amount.
	 */
	public float getPaid_collection_amount() {
		return paid_collection_amount;
	}

	/**
	 * @param paid_collection_amount
	 *            The paid_collection_amount to set.
	 */
	public void setPaid_collection_amount(float paid_collection_amount) {
		this.paid_collection_amount = paid_collection_amount;
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
	 * @return Returns the topay_collection_amount.
	 */
	public float getTopay_collection_amount() {
		return topay_collection_amount;
	}

	/**
	 * @param topay_collection_amount
	 *            The topay_collection_amount to set.
	 */
	public void setTopay_collection_amount(float topay_collection_amount) {
		this.topay_collection_amount = topay_collection_amount;
	}

	public String getPaid_collection_from() {
		return paid_collection_from;
	}

	public void setPaid_collection_from(String paid_collection_from) {
		this.paid_collection_from = paid_collection_from;
	}

	public String getPaid_collection_to() {
		return paid_collection_to;
	}

	public void setPaid_collection_to(String paid_collection_to) {
		this.paid_collection_to = paid_collection_to;
	}

	public String getStation_name() {
		return station_name;
	}

	public void setStation_name(String station_name) {
		this.station_name = station_name;
	}

	public String getTopay_collection_from() {
		return topay_collection_from;
	}

	public void setTopay_collection_from(String topay_collection_from) {
		this.topay_collection_from = topay_collection_from;
	}

	public String getTopay_collection_to() {
		return topay_collection_to;
	}

	public void setTopay_collection_to(String topay_collection_to) {
		this.topay_collection_to = topay_collection_to;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}
}
