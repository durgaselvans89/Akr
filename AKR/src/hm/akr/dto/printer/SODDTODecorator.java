package hm.akr.dto.printer;

import java.util.Date;

import hm.akr.dto.SODDTO;

/**
 */
public class SODDTODecorator 
{
	SODDTO sod = null;
	
	/**
	 * Constructor method
	 */
	public SODDTODecorator(SODDTO sobdto)
	{
		sod = sobdto;
	}

	
	
	/**
	 * @return Returns the crAmt.
	 */
	public Float getCrAmt() {
		return sod.getCrAmt();
	}
	
	/**
	 * @return Returns the crno.
	 */
	public String getCrno() {
		return sod.getCrno();
	}
	
	/**
	 * @return Returns the dd.
	 */
	public Float getDd() {
		return sod.getDd();
	}
	
	/**
	 * @return Returns the ddExtra.
	 */
	public Float getDdExtra() {
		return sod.getDdExtra();
	}
	
	/**
	 * @return Returns the demurrage.
	 */
	public Float getDemurrage() {
		return sod.getDemurrage();
	}
	
	/**
	 * @return Returns the from.
	 */
	public String getFrom() {
		return sod.getFrom();
	}
	
	/**
	 * @return Returns the lrDate.
	 */
	public Date getLrDate() {
		return sod.getLrDate();
	}
	
	/**
	 * @return Returns the lrno.
	 */
	public String getLrno() {
		return sod.getLrno();
	}
	
	/**
	 * @return Returns the lrtype.
	 */
	public String getLrtype() {
		return sod.getLrtype();
	}
	
	/**
	 * @return Returns the noofarticles.
	 */
	public int getNoofarticles() {
		return sod.getNoofarticles();
	}
	
	/**
	 * @return Returns the others.
	 */
	public Float getOthers() {
		return sod.getOthers();
	}
	
	/**
	 * @return Returns the postage.
	 */
	public Float getPostage() {
		return sod.getPostage();
	}
	
	/**
	 * @return Returns the sno.
	 */
	public String getSno() {
		return sod.getSno();
	}
	
	/**
	 * @return Returns the totalAmount.
	 */
	public Float getTotalAmount() {
		return sod.getTotalAmount();
	}
	
	/**
	 * @return Returns the underCharge.
	 */
	public Float getUnderCharge() {
		return sod.getUnderCharge();
	}
	
	/**
	 * @return Returns the weight.
	 */
	public Float getWeight() {
		return sod.getWeight();
	}
	
	/**
	 * 
	 * returns the status
	 * true - Canceled
	 * false - Avail
	 */
	public Boolean getStatus(){
		return this.sod.getStatus();
	}
}
