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
public class ViewBillDecorator implements Serializable  {
 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BookingDTO bookingDTO;
	private int sno;
	
	/**
	 * No args Constructor
	 */
	public ViewBillDecorator() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * The constructor takes the BookingDTO as the Constructor.
	 */
	public ViewBillDecorator(BookingDTO bookingDTO) {
		this.bookingDTO = bookingDTO;
	}
	
	/**
	 * The constructor takes the BookingDTO as the Constructor.
	 */
	public ViewBillDecorator(BookingDTO bookingDTO, int no) {
		this.bookingDTO = bookingDTO;
		this.sno = no;
	}
	

	public String getSno() {
		return String.valueOf(sno);
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public BookingDTO getBookingDTO() {
		return bookingDTO;
	}

	public String getLrno() {
		return bookingDTO.getLrno();
	}

	
	public String getFrom() {
		return bookingDTO.getFrom();
	}

	public String getTo() {
		return bookingDTO.getTo();
	}

	


	public float getArticle_value() {
		return bookingDTO.getArticle_value();
	}

	


	public Date getDate() {
		return bookingDTO.getDate();
	}

	

	public int getNo_of_articles() {
		return bookingDTO.getNo_of_articles();
	}

	

	public Float getCharged_weight() {
		return bookingDTO.getCharged_weight();
	}

	public Float getBft() {
		return bookingDTO.getBft();
	}

	public Float getCcc() {
		return bookingDTO.getCcc();
	}

	public Float getLrc() {
		return bookingDTO.getLrc();
	}

	public Float getDcc() {
		return bookingDTO.getDcc();
	}

	public Float getDdc() {
		return bookingDTO.getDdc();
	}

	public Float getIec() {
		return bookingDTO.getIec();
	}

	public Float getLc() {
		return bookingDTO.getLc();
	}

	public Float getGsc() {
		return bookingDTO.getGsc();
	}

	public Float getInsuranceCharge() {
		return bookingDTO.getInsuranceCharge();
	}

	public Float getStax() {
		return bookingDTO.getStax();
	}

	

	public Float getOther_charges() {
		return bookingDTO.getOther_charges();
	}

	public Float getTotal() {
		return bookingDTO.getTotal();
	}



	

	public Date getDeliveredDate() {
		return bookingDTO.getDeliveredDate();
	}

	public String getInvoiceNo() {
		return bookingDTO.getInvoiceNo();
	}


}
