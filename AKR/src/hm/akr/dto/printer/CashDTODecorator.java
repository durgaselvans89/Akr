package hm.akr.dto.printer;

import hm.akr.dto.BookingDTO;

import java.io.Serializable;

/**
 * The class<code></code> is a decorator object over BookingDTO.The purpose of
 * introducing a DTO is to ensure that the decorator object is passed to the
 * JasperReport Generator.
 * 
 */
public class CashDTODecorator implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BookingDTO bookingDTO;
	private CashPrinterDTO casdto;
	private int sno;
	String arttype;

	/**
	 * No args Constructor
	 */
	public CashDTODecorator() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * The constructor takes the BookingDTO as the Constructor.
	 */
	public CashDTODecorator(CashPrinterDTO cashdto, BookingDTO bookingDTO,
			String arttype) {
		this.bookingDTO = bookingDTO;
		this.casdto = cashdto;
		this.arttype = arttype;
	}

	/**
	 * 
	 * @return
	 */
	public String getDuplicate() {
		return this.arttype;
	}

	/**
	 * @return Returns the actual_weight.
	 */
	public String getActual_weight() {
		return Float.toString(this.bookingDTO.getActual_weight());
	}

	/**
	 * @return Returns the article_description.
	 */
	public String getArticle_description() {
		return this.bookingDTO.getArticle_description();
	}

	/**
	 * @return Returns the article_value.
	 */
	public String getArticle_value() {
		return String.valueOf(this.bookingDTO.getArticle_value());
	}

	/**
	 * @return Returns the charged_weight.
	 */
	public String getCharged_weight() {
		return String.valueOf(this.bookingDTO.getCharged_weight());
	}

	/**
	 * @return Returns the consignee_address.
	 */
	public String getConsignee_address() {
		return this.bookingDTO.getConsignee_address();
	}

	/**
	 * @return Returns the consignor_address.
	 */
	public String getConsignor_address() {
		return this.bookingDTO.getConsignor_address();
	}

	/**
	 * @return Returns the consignee_CST.
	 */
	public String getConsignee_CST() {
		return (this.bookingDTO.getConsignee_GST());
		// return 10L;
	}

	/**
	 * @return Returns the consignor_GST.
	 */
	public String getConsignor_GST() {
		return (this.bookingDTO.getConsignor_CST());
		// return 10L;
	}

	/**
	 * @return Returns the date.
	 */
	public String getDate() {
		return this.casdto.getDate();
	}

	/**
	 * @return Returns the from.
	 */
	public String getFrom() {
		return this.bookingDTO.getFrom();
	}

	/**
	 * @return Returns the lrno.
	 */
	public String getLrno() {
		return this.bookingDTO.getLrno();
	}

	/**
	 * @return Returns the no_of_articles.
	 */
	public String getNo_of_articles() {
		return Integer.toString(this.bookingDTO.getNo_of_articles());
	}

	/**
	 * @return Returns the other_charges.
	 */
	public String getOther_charges() {
		return "OTHER" + Float.toString(this.bookingDTO.getOther_charges());
	}

	/**
	 * @return Returns the to.
	 */
	public String getTo() {
		return this.bookingDTO.getTo();
	}

	/**
	 * @return Returns the total.
	 */
	public String getTotal() {
		return "TOTAL  " + Float.toString(this.bookingDTO.getTotal());
	}

	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return this.bookingDTO.getType();
	}

	/**
	 * @return Returns the bft.
	 */
	public String getBft() {
		return "BFT  " + Float.toString(this.bookingDTO.getBft());
	}

	/**
	 * @return Returns the ccc.
	 */
	public String getCcc() {
		return "CCC  " + Float.toString(this.bookingDTO.getCcc());
	}

	/**
	 * @return Returns the dcc.
	 */
	public String getDcc() {
		return String.valueOf("DCC  " + this.bookingDTO.getDcc());
	}

	/**
	 * @return Returns the ddc.
	 */
	public String getDdc() {
		return "DDC  " + Float.toString(this.bookingDTO.getDdc());
	}

	public String getDhc() {
		return "DHC  " + Float.toString(this.bookingDTO.getDhc());
	}

	/**
	 * @return Returns the gsc.
	 */
	public String getGsc() {
		return String.valueOf("GSC  " + this.bookingDTO.getGsc());
	}

	/**
	 * @return Returns the iec.
	 */
	public String getIec() {
		return String.valueOf("IEC  " + this.bookingDTO.getIec());
	}

	/**
	 * @return Returns the lc.
	 */
	public String getLc() {
		return String.valueOf("LC   " + this.bookingDTO.getLc());
	}

	/**
	 * @return Returns the lrc.
	 */
	public String getLrc() {
		return String.valueOf("LRC  " + this.bookingDTO.getLrc());
	}

	/**
	 * @return Returns the stax.
	 */
	public String getStax() {
		return String.valueOf("STAX " + this.bookingDTO.getStax());
	}

	/**
	 * @return Returns the consigneeName.
	 */
	public String getConsigneeName() {
		return this.bookingDTO.getConsigneeName();
	}

	/**
	 * @return Returns the consignorName.
	 */
	public String getConsignorName() {
		return this.bookingDTO.getConsignorName();
	}

	public String getFromMobile() {
		return this.bookingDTO.getFromMobile();
	}

	public String getToMobile() {
		return this.bookingDTO.getToMobile();
	}

	public String getSno() {
		return String.valueOf(sno);
	}

	/**
	 * @return the cr_no
	 */
	public String getCr_no() {
		return casdto.getCr_no();
	}

	/**
	 * @return the lr_no
	 */
	public String getLr_no() {
		return casdto.getLr_no();
	}

	/**
	 * @return the station_name
	 */
	public String getStation_name() {
		return casdto.getStation_name();
	}

	/**
	 * @return the station_code
	 */
	public String getStation_code() {
		return casdto.getStation_code();
	}

	/**
	 * @return the consigne_name
	 */
	public String getConsignee_name() {
		return casdto.getConsignee_name();
	}

	/**
	 * @return the lr_date
	 */
	public String getLr_date() {
		return casdto.getLr_date();
	}

	/**
	 * @return the no_of_Articles
	 */
	public String getNo_of_Articles() {
		return casdto.getNo_of_Articles();
	}

	/**
	 * @return the weight
	 */
	public String getWeight() {
		return casdto.getWeight();
	}

	/**
	 * @return the freight
	 */
	public String getFreight() {
		return casdto.getFreight();
	}

	/**
	 * @return the dd_charges
	 */
	public String getDd_charges() {
		return casdto.getDd_charges();
	}

	/**
	 * @return the others
	 */
	public String getOthers() {
		return casdto.getOthers();
	}

	/**
	 * @return the total_amount
	 */
	public String getTotal_amount() {
		return casdto.getTotal_amount();
	}

	/**
	 * @return the demurrage
	 */
	public String getDemurrage() {
		return casdto.getDemurrage();
	}

	/**
	 * @return the under_charge
	 */
	public String getUnder_charge() {
		return casdto.getUnder_charge();
	}

	/**
	 * @return the postage
	 */
	public String getPostage() {
		return casdto.getPostage();
	}

	/**
	 * @return the dd_extra
	 */
	public String getDd_extra() {
		return casdto.getDd_extra();
	}

}
