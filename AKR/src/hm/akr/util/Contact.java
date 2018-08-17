package hm.akr.util;


/**
 * @author Nandhakumar
 * @version 2.0
 */
public class Contact {

	private int id = 0;
	
	/** Contact name **/
	private String name = null;
	
	/** Contact address **/
	private String address = null;
	
	/** Is Consignee or Consignor **/
	private boolean isConsignee = false;

	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isConsignee() {
		return isConsignee;
	}

	/**
	 * 
	 * @param isConsignee
	 */
	public void setConsignee(boolean isConsignee) {
		this.isConsignee = isConsignee;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
