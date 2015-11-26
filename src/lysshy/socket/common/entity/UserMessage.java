package lysshy.socket.common.entity;

import java.io.Serializable;

public class UserMessage implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8047364345782853574L;
	private String userName;
	private String msg;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String getPrintString(){
		return msg + " from " + userName;
	}
	
}
