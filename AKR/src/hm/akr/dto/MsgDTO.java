package hm.akr.dto;

import java.io.Serializable;
import java.util.Date;

public class MsgDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message = null;

	private Date msgdate = null;

	private String stationcode = null;

	private String priority = null;
	
	private int msg_id = 0;
	
	private String msg_filename = null;
	
	private int msg_read = 0;
	
	private int read_count = 0;
	
	private int unread_count = 0;
	
	private int total_count = 0;

	/**
	 * @return the msg_id
	 */
	public int getMsg_id() {
		return msg_id;
	}

	/**
	 * @param msg_id the msg_id to set
	 */
	public void setMsg_id(int msg_id) {
		this.msg_id = msg_id;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the msgdate
	 */
	public Date getMsgdate() {
		return msgdate;
	}

	/**
	 * @param msgdate the msgdate to set
	 */
	public void setMsgdate(Date msgdate) {
		this.msgdate = msgdate;
	}

	/**
	 * @return the stationcode
	 */
	public String getStationcode() {
		return stationcode;
	}

	/**
	 * @param stationcode the stationcode to set
	 */
	public void setStationcode(String stationcode) {
		this.stationcode = stationcode;
	}

	/**
	 * @return the priority
	 */
	public String getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}

	/**
	 * @return the msg_filename
	 */
	public String getMsg_filename() {
		return msg_filename;
	}

	/**
	 * @param msg_filename the msg_filename to set
	 */
	public void setMsg_filename(String msg_filename) {
		this.msg_filename = msg_filename;
	}

	/**
	 * @return the msg_read
	 */
	public int getMsg_read() {
		return msg_read;
	}

	/**
	 * @param msg_read the msg_read to set
	 */
	public void setMsg_read(int msg_read) {
		this.msg_read = msg_read;
	}

	/**
	 * @return the read_count
	 */
	public int getRead_count() {
		return read_count;
	}

	/**
	 * @param read_count the read_count to set
	 */
	public void setRead_count(int read_count) {
		this.read_count = read_count;
	}

	/**
	 * @return the unread_count
	 */
	public int getUnread_count() {
		return unread_count;
	}

	/**
	 * @param unread_count the unread_count to set
	 */
	public void setUnread_count(int unread_count) {
		this.unread_count = unread_count;
	}

	/**
	 * @return the total_count
	 */
	public int getTotal_count() {
		return total_count;
	}

	/**
	 * @param total_count the total_count to set
	 */
	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}

}
