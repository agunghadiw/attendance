package com.mpe.basic.action;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

public class CASLogonAction extends ActionSupport implements SessionAware, ServletContextAware,
ServletResponseAware, ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Log logger = LogFactory.getFactory().getInstance(this.getClass());
	
	Map<String, Object> session;
	ServletContext servletContext;
	HttpServletResponse response;
	HttpServletRequest request;
	
	
	public String connectToCAS() {
		logger.info("CAS");
		return null;
	}
	

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
		
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;
		
	}

	@Override
	public void setServletContext(ServletContext arg0) {
		this.servletContext = arg0;		
		
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
		
	}

}
