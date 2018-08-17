package hm.akr.dto;

import java.io.Serializable;

public class CardDTO implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String type = null;
	
	private float aboveCardrate = 0;
	
	private float equalCardrate = 0;
	
	private float upto20Cardrate = 0;
	
	private float above20Cardrate = 0;
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getAboveCardrate() {
		return aboveCardrate;
	}

	public void setAboveCardrate(float aboveCardrate) {
		this.aboveCardrate = aboveCardrate;
	}

	public float getEqualCardrate() {
		return equalCardrate;
	}

	public void setEqualCardrate(float equalCardrate) {
		this.equalCardrate = equalCardrate;
	}

	public float getUpto20Cardrate() {
		return upto20Cardrate;
	}

	public void setUpto20Cardrate(float upto20Cardrate) {
		this.upto20Cardrate = upto20Cardrate;
	}

	public float getAbove20Cardrate() {
		return above20Cardrate;
	}

	public void setAbove20Cardrate(float above20Cardrate) {
		this.above20Cardrate = above20Cardrate;
	}

	
	
}
