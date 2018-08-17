package hm.akr.dto;

/**
 * 
 */
public class ValidationDTO {

	private boolean isAdminHead = false;
	private boolean isValid = false;
	private boolean isAdmin = false;
	private String serverDate = null;
	
	public boolean isAdminHead() {
		return isAdminHead;
	}
	public void setAdminHead(boolean isAdminHead) {
		this.isAdminHead = isAdminHead;
	}
	public boolean isValid() {
		return isValid;
	}
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getServerDate() {
		return serverDate;
	}
	public void setServerDate(String serverDate) {
		this.serverDate = serverDate;
	}
}
