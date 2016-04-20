package com.depsos.soap.other;

import java.io.Serializable;

public class ResponseResult implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Object result;	
	ResponseCode responseCode = new ResponseCode();
	
	public ResponseResult() {
		super();
	}

	public ResponseResult(Object result, ResponseCode responseCode) {
		super();
		this.result = result;
		this.responseCode = responseCode;
	}
	

	public ResponseCode getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(ResponseCode responseCode) {
		this.responseCode = responseCode;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
	
	
	
	

}
