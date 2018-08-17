/**
 * 
 */
package hm.akr.dto;

import java.io.Serializable;

/**
 * @author Naruto1
 * 
 */
public class SMSDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String cnor_phone = null;
	
	private String cnor_message = null;
	
	private String cnee_phone = null;
	
	private String cnee_message = null;

	/**
	 * @return the cnor_phone
	 */
	public String getCnor_phone() {
		return cnor_phone;
	}

	/**
	 * @param cnor_phone the cnor_phone to set
	 */
	public void setCnor_phone(String cnor_phone) {
		this.cnor_phone = cnor_phone;
	}

	/**
	 * @return the cnor_message
	 */
	public String getCnor_message() {
		return cnor_message;
	}

	/**
	 * @param cnor_message the cnor_message to set
	 */
	public void setCnor_message(String cnor_message) {
		this.cnor_message = cnor_message;
	}

	/**
	 * @return the cnee_phone
	 */
	public String getCnee_phone() {
		return cnee_phone;
	}

	/**
	 * @param cnee_phone the cnee_phone to set
	 */
	public void setCnee_phone(String cnee_phone) {
		this.cnee_phone = cnee_phone;
	}

	/**
	 * @return the cnee_message
	 */
	public String getCnee_message() {
		return cnee_message;
	}

	/**
	 * @param cnee_message the cnee_message to set
	 */
	public void setCnee_message(String cnee_message) {
		this.cnee_message = cnee_message;
	}

}
