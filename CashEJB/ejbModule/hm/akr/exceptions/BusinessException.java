package hm.akr.exceptions;

public class BusinessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int responseCode = 0;

	private String responseMessage = null;

	/**
	 * 
	 * @param message
	 */
	public void setResponseMessage(String message) {
		responseMessage = message;
	}

	/**
	 * @return Returns the responseCode.
	 */
	public int getResponseCode() {
		return responseCode;
	}

	/**
	 * @param responseCode The responseCode to set.
	 */
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * @return Returns the responseMessage.
	 */
	public String getResponseMessage() {
		return responseMessage;
	}

}
