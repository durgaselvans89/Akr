package hm.akr.dto.printer;

import java.io.Serializable;
import java.util.Date;

import hm.akr.dto.BookingDTO;

/**
 * The class<code></code> is a decorator object over BookingDTO.The purpose of
 * introducing a DTO is to ensure that the decorator object is passed to the
 * JasperReport Generator.
 * 
 */
public class BookingDTODecorator implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BookingDTO bookingDTO;
	private int sno;

	/**
	 * No args Constructor
	 */
	public BookingDTODecorator() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * The constructor takes the BookingDTO as the Constructor.
	 */
	public BookingDTODecorator(BookingDTO bookingDTO) {
		this.bookingDTO = bookingDTO;
	}

	/**
	 * The constructor takes the BookingDTO as the Constructor.
	 */
	public BookingDTODecorator(BookingDTO bookingDTO, int no) {
		this.bookingDTO = bookingDTO;
		this.sno = no;
	}

	/**
	 * 
	 * @return
	 */
	public String getDuplicate() {
		return this.bookingDTO.getDuplicate();
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
	public Date getDate() {
		return this.bookingDTO.getDate();
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
		return String.valueOf(this.bookingDTO.getNo_of_articles());
	}

	/**
	 * @return Returns the other_charges.
	 */
	public String getOther_charges() {
		return String.valueOf(this.bookingDTO.getOther_charges());
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
		return Float.toString(this.bookingDTO.getTotal());
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
		return  Float.toString(this.bookingDTO.getBft());
	}

	/**
	 * @return Returns the ccc.
	 */
	public String getCcc() {
		return  Float.toString(this.bookingDTO.getCcc());
	}

	/**
	 * @return Returns the dcc.
	 */
	public String getDcc() {
		return String.valueOf(this.bookingDTO.getDcc());
	}

	/**
	 * @return Returns the ddc.
	 */
	public String getDdc() {
		return Float.toString(this.bookingDTO.getDdc());
	}

	/**
	 * @return Returns the gsc.
	 */
	public String getGsc() {
		return String.valueOf(this.bookingDTO.getGsc());
	}
	
	/**
	 * @return Returns the gsc.
	 */
	public String getDhc() {
		return String.valueOf(this.bookingDTO.getDhc());
	}

	/**
	 * @return Returns the iec.
	 */
	public String getIec() {
		return String.valueOf(this.bookingDTO.getIec());
	}

	/**
	 * @return Returns the lc.
	 */
	public String getLc() {
		return String.valueOf(this.bookingDTO.getLc());
	}

	/**
	 * @return Returns the lrc.
	 */
	public String getLrc() {
		return String.valueOf(this.bookingDTO.getLrc());
	}

	/**
	 * @return Returns the stax.
	 */
	public String getStax() {
		return String.valueOf(this.bookingDTO.getStax());
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

	public String getRate_type() {
		return String.valueOf(bookingDTO.getRate_type()).trim();
	}

	public String getCnorPhone() {
		return this.bookingDTO.getCnorPhone() + "; "
				+ this.bookingDTO.getCnorLandLine();
	}

	public String getCneePhone() {
		return this.bookingDTO.getCneePhone() + "; "
				+ this.bookingDTO.getCneeLandLine();
	}

}
