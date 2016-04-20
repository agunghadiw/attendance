package com.depsos.soap.other;

import java.io.Serializable;

/**
 * @author agunghadiw
 * @create on Jul 11, 2014 1:24:57 PM
 */

public class ResponseCode implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String code;
	String name;
	String description;
	
	public ResponseCode() {
	}

	public ResponseCode(String code, String name, String description) {
		super();
		this.code = code;
		this.name = name;
		this.description = description;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
