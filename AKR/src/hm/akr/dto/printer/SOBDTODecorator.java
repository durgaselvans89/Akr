package hm.akr.dto.printer;

import java.io.Serializable;
import java.util.Date;

import hm.akr.dto.BookingDTO;


/**
 * The class<code></code> is a decorator object over 
 * BookingDTO.The purpose of introducing a DTO is to ensure that
 * the decorator object is passed to the JasperReport Generator.
 *
 */
public class SOBDTODecorator implements Serializable  {
 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BookingDTO bookingDTO;
	private int sno;
	
	/**
	 * No args Constructor
	 */
	public SOBDTODecorator() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * The constructor takes the BookingDTO as the Constructor.
	 */
	public SOBDTODecorator(BookingDTO bookingDTO) {
		this.bookingDTO = bookingDTO;
	}
	
	/**
	 * The constructor takes the BookingDTO as the Constructor.
	 */
	public SOBDTODecorator(BookingDTO bookingDTO, int no) {
		this.bookingDTO = bookingDTO;
		this.sno = no;
	}

	
	/**
	 * @return Returns the actual_weight.
	 */
	public Float getActual_weight() {
		return this.bookingDTO.getActual_weight();
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
	public Float getArticle_value() {
		return this.bookingDTO.getArticle_value();
	}


		/**
	 * @return Returns the charged_weight.
	 */
	public Float getCharged_weight() {
		return this.bookingDTO.getCharged_weight();
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
		//return 10L;
	}

     /**
	 * @return Returns the consignor_GST.
	 */
	public String getConsignor_GST() {
		return (this.bookingDTO.getConsignor_CST());
		//return 10L;
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
	public int getNo_of_articles() {
		return this.bookingDTO.getNo_of_articles();
	}

	/**
	 * @return Returns the other_charges.
	 */
	public Float getOther_charges() {
		return this.bookingDTO.getOther_charges();
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
	public Float getTotal() {
		return this.bookingDTO.getTotal();
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
	public Float getBft() {
		return this.bookingDTO.getBft();
	}

	/**
	 * @return Returns the ccc.
	 */
	public Float getCcc() {
		return this.bookingDTO.getCcc();
	}

	/**
	 * @return Returns the dcc.
	 */
	public Float getDcc() {
		return this.bookingDTO.getDcc();
	}
	
	/**
	 * @return Returns the ddc.
	 */
	public Float getDdc() {
		return this.bookingDTO.getDdc();
	}
	
	/**
	 * @return Returns the gsc.
	 */
	public Float getGsc() {
		return this.bookingDTO.getGsc();
	}	

	/**
	 * @return Returns the iec.
	 */
	public Float getIec() {
		return this.bookingDTO.getIec();
	}

	/**
	 * @return Returns the lc.
	 */
	public Float getLc() {
		return this.bookingDTO.getLc();
	}
	
	/**
	 * @return Returns the lrc.
	 */
	public Float getLrc() {
		return this.bookingDTO.getLrc();
	}	

	/**
	 * @return Returns the stax.
	 */
	public Float getStax() {
		return this.bookingDTO.getStax();
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
	 * 
	 * returns the status
	 * true - Canceled
	 * false - Avail
	 */
	public Boolean getStatus(){
		return this.bookingDTO.getStatus();
	}

}
