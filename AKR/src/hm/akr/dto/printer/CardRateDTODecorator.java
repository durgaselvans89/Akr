package hm.akr.dto.printer;

import hm.akr.dto.CardRateDTO;

import java.io.Serializable;

/**
 * The class<code></code> is a decorator object over BookingDTO.The purpose of
 * introducing a DTO is to ensure that the decorator object is passed to the
 * JasperReport Generator.
 * 
 */
public class CardRateDTODecorator implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CardRateDTO cardrate;
	private int sno;

	/**
	 * No args Constructor
	 */
	public CardRateDTODecorator() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * The constructor takes the BookingDTO as the Constructor.
	 */
	public CardRateDTODecorator(CardRateDTO cardrate) {
		this.cardrate = cardrate;
	}

	/**
	 * The constructor takes the BookingDTO as the Constructor.
	 */
	public CardRateDTODecorator(CardRateDTO cardrate, int no) {
		this.cardrate = cardrate;
		this.sno = no;
	}

	public String getStation_name() {
		return String.valueOf(cardrate.getStation_name());
	}

	public String getStation_code() {
		return String.valueOf(cardrate.getStation_code());
	}

	public String getDistance() {
		return String.valueOf(cardrate.getDistance());
	}

	public float getCard_rate() {
		return this.cardrate.getCard_rate();
	}

	public String getSno() {
		return String.valueOf(sno);
	}

}
