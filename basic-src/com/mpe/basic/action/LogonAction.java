package com.mpe.basic.action;

import java.io.StringWriter;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.apache.struts2.util.ServletContextAware;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.depsos.hr.model.dao.HrDAOFactory;
import com.depsos.hr.model.dao.HrDAOFactoryHibernate;
import com.mpe.basic.model.ApplicationSetup;
import com.mpe.basic.model.User;
import com.mpe.basic.model.dao.BasicDAOFactory;
import com.mpe.basic.model.dao.BasicDAOFactoryHibernate;
import com.mpe.common.action.BaseAction;
import com.mpe.common.servlet.Struts2Captcha;
import com.mpe.common.util.CommonUtil;
import com.mpe.common.util.Constants;
import com.mpe.message.model.OutgoingEmail;
import com.mpe.message.model.dao.MessageDAOFactory;
import com.mpe.message.model.dao.MessageDAOFactoryHibernate;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

/**
 * @author Agung Hadiwaluyo
 *
 */
public class LogonAction extends BaseAction implements ServletContextAware,
	ServletResponseAware, ServletRequestAware {

	private static final long serialVersionUID = 1L;
	Log log = LogFactory.getFactory().getInstance(this.getClass());
	
	BasicDAOFactory basicDAOFactory = BasicDAOFactory.instance(BasicDAOFactoryHibernate.class);
	MessageDAOFactory addressDAOFactory = MessageDAOFactory.instance(MessageDAOFactoryHibernate.class);
	HrDAOFactory hrDAOFactory = HrDAOFactory.instance(HrDAOFactoryHibernate.class);
	
	//String regex = "((?=.*\\d)(?=.*[a-zA-Z])(?!.*[()+}{;=`~:\\|'?/>.<,]).{8,20})";
	String regex = "";
	
	String userName;
	String userPass;
	boolean rememberMe;
	
	String confirmUserPass;
	String confirmUserPass2;
	
	ServletContext servletContext;
	HttpServletResponse response;
	HttpServletRequest request;
	
	public String secureReceptor() {
		return SUCCESS;
	}
	
	@SkipValidation
	public String userPassExpiredForm() {
		setMappedRequest("userPassExpiredConfirm");
		return SUCCESS;
	}
	
	@SkipValidation
	public String userPassExpiredConfirm() {
		try {
			// check user-session
			User user = (User)getSession().get(Constants.USER);
			if (user!=null) {
				// compare prev user-pass
				if (!CommonUtil.digest(userPass).equals(user.getUserPass())) {
					addActionError("Old Password wrong or invalid");
					return INPUT;
				} else {
					if (confirmUserPass!=null && confirmUserPass.length()>0 && confirmUserPass2!=null && confirmUserPass2.length()>0) {
						if (confirmUserPass.equalsIgnoreCase(confirmUserPass2)) {
							// check numeric and character combination!
							ApplicationSetup applicationSetup = basicDAOFactory.getApplicationSetupDAO().findByCriteria(Restrictions.ne("applicationSetupId", new Long(-1)));
							if (applicationSetup.isAlphabetUserPass()) regex = "(?=.*[a-z])"+regex;
							if (applicationSetup.isNumericUserPass()) regex = "(?=.*\\d)"+regex;
							if (applicationSetup.isUpperCaseLetter()) regex = "(?=.*[A-Z])"+regex;
							regex = "("+regex+".{"+applicationSetup.getMinUserPassLength()+",20})";
							//
							Pattern pattern = Pattern.compile(regex);
							Matcher matcher = pattern.matcher(confirmUserPass);
							if (!matcher.matches()) {
								addActionError("Password must contain "+(applicationSetup.isAlphabetUserPass()?"alphabet":"")+" "+(applicationSetup.isNumericUserPass()?"numeric":"")+" "+(applicationSetup.isUpperCaseLetter()?"upper-case letter":"")+" combination and less "+(applicationSetup.getMinUserPassLength())+" characters.");
								return INPUT;
							}
							user = basicDAOFactory.getUserDAO().findUserAndRoleById(((User)getSession().get(Constants.USER)).getUserId());
							if (user!=null) {
								
								// save history
								//ApplicationSetup applicationSetup = basicDAOFactory.getApplicationSetupDAO().findByCriteria(Restrictions.ne("applicationSetupId", new Long(-1)));
								if (basicDAOFactory.getUserPasswordHistoryDAO().saveValidChangeUserPass(user.getUserId(), applicationSetup.getUserPassHistory(), confirmUserPass)) {
									if (user.getLastLoginDate()==null) {
										user.setLastLoginDate(new Date());
										//
										//session.put(Constants.USER, user);
										getSession().put(Constants.PERMISSIONS, user.getPermissionAccessByParentChild());
									}
									user.setUserPass(CommonUtil.digest(confirmUserPass));
									basicDAOFactory.getUserDAO().update(user);								
									
									// sent change password email confirmation
									if (user.getEmail()!=null && user.getEmail().length()>0) {
										Properties p = new Properties();
										ServletContext servletContext = getServletContext();
										String path = servletContext.getRealPath("/");
										//logger.info("P : "+path);
										p.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, path);
										Velocity.init(p);
										VelocityContext context = new VelocityContext();
										
										context.put("userName", user.getUserName());
										context.put("userPass", confirmUserPass);
										
										//StringWriter w = new StringWriter();
									    //Velocity.mergeTemplate("/template/password_change.vm", context, w );
										
										/*OutgoingEmail outgoingEmail = new OutgoingEmail();
										outgoingEmail.setEmailDate(new Date());
										outgoingEmail.setSubject("Change Password Confirmation");
										outgoingEmail.setSender(Constants.EMAIL_FROM);
										outgoingEmail.setTo(user.getEmail());
										outgoingEmail.setMessage(w.toString());
										messageDAOFactory.getOutgoingEmailDAO().save(outgoingEmail);*/
									}
								} else {
									addActionError("Please input another password!");
									return INPUT;
								}
							}
						} else {
							addActionError("New password and confirm new password are not same!");
							return INPUT;
						}
					}
				}
				
			} else return "index";
			
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
			return INPUT;
		}
		return SUCCESS;
	}
	
	@SkipValidation
	public String forgotPassForm() {	
		setMappedRequest("forgotPassConfirm");
		return SUCCESS;
	}
	
	@SuppressWarnings("deprecation")
	@SkipValidation
	public String forgotPassConfirm() {
		HttpServletRequest req  = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		HttpSession ses = req.getSession();
		String parm = req.getParameter("j_captcha_response");
		String cap = (String)ses.getAttribute(Struts2Captcha.CAPTCHA_KEY) ;
		if(!parm.equals(cap) ){
			 addActionError("Entered captcha response does not match with image");
	         return INPUT;
		} else {
			try {
				ApplicationSetup applicationSetup = basicDAOFactory.getApplicationSetupDAO().findByCriteria(Restrictions.ne("applicationSetupId", new Long(-1)));
				if (applicationSetup==null || (applicationSetup!=null && applicationSetup.getFromEmailAddress()==null)) {
					addActionError("Application setup's not configured properly! Please contact administrator.");
					return INPUT;
				}
				// reset password
				User user = basicDAOFactory.getUserDAO().findByCriteria(Restrictions.eq("userName", userName));
				if (user==null) {
					addActionError("User with username "+userName+" not found! Please input correct username or contact your admin.");
					return INPUT;
				} else {
					// send email confirmation
					String pass = CommonUtil.randomin(6, true, true, true);
					user.setUserPass(CommonUtil.digest(pass));
					user.setLastLoginDate(null);
					basicDAOFactory.getUserDAO().update(user);
					
					if (CommonUtil.isValidEmailAddress(user.getUserName())) {
					//if (user.getEmail()!=null && user.getEmail().length()>0) {
						Properties p = new Properties();
						String path = servletContext.getRealPath("/");
						//logger.info("P : "+path);
						p.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, path);
						Velocity.init(p);
						VelocityContext context = new VelocityContext();
						
						StringBuffer sb = request.getRequestURL();
						String s = sb.substring(0, sb.indexOf("/basic/"));
						context.put("urlPath", s);
						
						context.put("userName", user.getUserName());
						context.put("userPass", pass);
						
						StringWriter w = new StringWriter();
					    Velocity.mergeTemplate("/template/forgot_pass.vm", context, w );
						
						OutgoingEmail outgoingEmail = new OutgoingEmail();
						outgoingEmail.setEmailDate(new Date());
						outgoingEmail.setSubject("Reset Forgot Password");
						outgoingEmail.setSender(applicationSetup.getFromEmailAddress());
						//outgoingEmail.setTo(customer.getContact().getEmail1());
						outgoingEmail.setMessage(w.toString());
						addressDAOFactory.getOutgoingEmailDAO().save(outgoingEmail);
					}
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}
	
	public String getUserName() {
		return userName;
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, key="validation.userName.required")
	@StringLengthFieldValidator(type = ValidatorType.FIELD, minLength="3", maxLength = "100", 
			message = "Username must be between	${minLength} and ${maxLength} characters.")
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPass() {
		return userPass;
	}

	@RequiredStringValidator(type = ValidatorType.FIELD, key = "validation.userPass.required")
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	
	@SkipValidation
	public String form() {
		// check user guest session?
		/*User user = (User)getSession().get(Constants.USER);
		if (user==null) {
			try {
				// check guest user from database!
				int i = basicDAOFactory.getUserDAO().getTotalUser();
				//log.info("tot user >>> "+i);
				ApplicationSetup applicationSetup = basicDAOFactory.getApplicationSetupDAO().findByCriteria(Restrictions.ne("applicationSetupId", new Long(-1)));
				if (i>0 && applicationSetup!=null && applicationSetup.getGuestUserName()!=null && applicationSetup.getGuestUserName().length()>0) {
					setMappedRequestFullPath("/basic/LogonAction_logon.action?userName="+applicationSetup.getGuestUserName()+"&userPass="+CommonUtil.randomin(6, true, true, true));
					return Constants.REDIRECT;
				}
			} catch (Exception e) {}
		}
		String u="", p="";
		if (((String)getSession().get(Constants.SKIP_COOKIES))==null || 
				(((String)getSession().get(Constants.SKIP_COOKIES))!=null && !((String)getSession().get(Constants.SKIP_COOKIES)).equalsIgnoreCase("logoff"))) {
			if (request.getCookies()!=null) {
				for(Cookie c : request.getCookies()) {
				    if (c.getName().equals("cookiesUserName")) u = c.getValue();
				    if (c.getName().equals("cookiesUserPass")) p = c.getValue();
				}
			}
		} else {
			getSession().remove(Constants.SKIP_COOKIES);
		}
		
		if (u.length()>0 && p.length()>0) {
			try {
				user = basicDAOFactory.getUserDAO().findByUserName(u, p);
				if (user!=null) {
					basicDAOFactory.getUserDAO().updateLastLogin(user.getUserId());
					session.put(Constants.USER, user);
					session.put(Constants.PERMISSIONS, user.getPermissionAccessByParentChild());
					// return to index
					return "index";
				}
			} catch (Exception e) {
				//e.printStackTrace();
				//log.info("remove cookies");
				// remove cookies!
				Cookie c1 = new Cookie("cookiesUserName", getUserName());
				c1.setMaxAge(0); // delete!
				c1.setPath("/");
				response.addCookie(c1);
				Cookie c2 = new Cookie("cookiesUserPass", getUserPass());
				c2.setMaxAge(0); // delete!
				c2.setPath("/");
				response.addCookie(c2);
			}
		}*/
		
		setMappedRequest("logon");
		return SUCCESS;
	}
	
	public String logon() {
		boolean firstTimeLogin = false;
		//boolean userPassExpired = false;
		try {
			User user = null;
			int i = basicDAOFactory.getUserDAO().getTotalUser();
			if (i==0 && userName.equalsIgnoreCase("setup") && userPass.equalsIgnoreCase("setup123456")) {
				user = new User();
				user.setUserName(userName);
				user.setUserPass(userPass);
				getSession().put(Constants.USER, user);
			} else {
				
				ApplicationSetup applicationSetup = basicDAOFactory.getApplicationSetupDAO().findByCriteria(Restrictions.ne("applicationSetupId", new Long(-1)));
				if (applicationSetup!=null && applicationSetup.getGuestUserName()!=null && applicationSetup.getGuestUserName().equalsIgnoreCase(userName)) {
					getSession().remove(Constants.USER);
					getSession().remove(Constants.PERMISSIONS);
					//
					Session hibernateSess = basicDAOFactory.getUserDAO().getSession(Constants.HIBERNATE_CFG_KEY_1);
					user = (User) hibernateSess.createCriteria(User.class)
							.add(Restrictions.eq("userName", userName))
							//.setFetchMode("roles", FetchMode.JOIN)
							.setFetchMode("organization", FetchMode.JOIN)
							//.setFetchMode("customer", FetchMode.JOIN)
							.createCriteria("roles","role")
							//.setCacheable(true) => trouble if error login catchable!!!
							.setFetchMode("permissions", FetchMode.JOIN)
							.setCacheRegion("com.mpe.basic.model.Permission")
							.uniqueResult();
					hibernateSess.close();
					//
					getSession().put(Constants.USER, user);
					getSession().put(Constants.PERMISSIONS, user.getPermissionAccessByParentChild());
					String fullName = hrDAOFactory.getEmployeeDAO().findFullNameByUserId(user.getUserId());
					getSession().put(Constants.FULL_NAME, (fullName!=null && fullName.length()>0)?fullName:user.getUserName());
				} else {
					user = basicDAOFactory.getUserDAO().findByUserName(userName, userPass);
					if (user!=null) {
						basicDAOFactory.getUserDAO().updateLastLogin(user.getUserId());
						if (user.getLastLoginDate()==null) {
							getSession().put(Constants.USER, user);
							getSession().put(Constants.PERMISSIONS, user.getPermissionAccessByParentChild());
							firstTimeLogin = true;
						} else {
							// get default password-duration
							int userPassDuration = basicDAOFactory.getUserDAO().getUserPassDuration(user.getUserId());
							// check expired ?
							if (basicDAOFactory.getUserPasswordHistoryDAO().userPassDurationExpired(user.getUserId(), userPassDuration, user.getUserPass())) {
								getSession().put(Constants.USER, user);
								getSession().put(Constants.PERMISSIONS, user.getPermissionAccessByParentChild());
								String fullName = hrDAOFactory.getEmployeeDAO().findFullNameByUserId(user.getUserId());
								getSession().put(Constants.FULL_NAME, (fullName!=null && fullName.length()>0)?fullName:user.getUserName());
								//userPassExpired = true;
							} else {
								//basicDAOFactory.getUserDAO().updateLastLogin(user.getUserId());
								getSession().put(Constants.USER, user);
								getSession().put(Constants.PERMISSIONS, user.getPermissionAccessByParentChild());
								String fullName = hrDAOFactory.getEmployeeDAO().findFullNameByUserId(user.getUserId());
								getSession().put(Constants.FULL_NAME, (fullName!=null && fullName.length()>0)?fullName:user.getUserName());
							}
						}
					}
				}
			}
			// cookies => skip if first time login
			if (!firstTimeLogin && rememberMe) {
				// cek previous cookies!
				boolean sameCookiesFound = false;
				for(Cookie c : request.getCookies()) {
				    if (c.getName().equals("appUserName")) {
				    	sameCookiesFound = true;
				    	break;
				    }
				}
				if (!sameCookiesFound) {
					Cookie c1 = new Cookie("appUserName", getUserName());
					c1.setMaxAge(60*60*24*365); // Make the cookie last a year!
					c1.setPath("/");
					response.addCookie(c1);
					Cookie c2 = new Cookie("appUserPass", getUserPass());
					//c2.setSecure(true);
					c2.setMaxAge(60*60*24*365); // Make the cookie last a year!
					c2.setPath("/");
					response.addCookie(c2);
				}
			}
			// chat
			//if (user.isAutomaticChat()) {
				//setMappedRequestFullPath("/chat/ChatLoginAction_login.action");
				//return Constants.REDIRECT;
				
			//}
		} catch(Exception exception){
			exception.printStackTrace();
			addActionError(exception.getMessage());
			//setMappedRequestFullPath("/basic/UserAction_home.action?error="+exception.getMessage());
			//return Constants.REDIRECT;
			return INPUT;
		}
		return "index";
/*		if (!firstTimeLogin) {
			if (userPassExpired) {
				return "user_pass_expired";
			} else {
				return "index";
			}
		} else return "change_password_first_time";
*/	}
	
	@SkipValidation
	public String logoff() {
		// chat
		//User user = (User)getSession().get(Constants.USER);
		//if (user!=null && user.isAutomaticChat()) {
			/*WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
			ChatService chatService = (ChatService) context.getBean("chatService");
			chatService.logout(user.getUserName());
            session.remove(Constants.USER_SESSION_KEY);*/
		//}
		getSession().clear();
		// add signal to logoff for skip cookies!
		getSession().put(Constants.SKIP_COOKIES, "logoff");
		return "index";
	}
	
	@SkipValidation
	public String changePasswordEdit() {
		setPreviousPage("changePasswordEdit");
		setMappedRequest("changePasswordConfirm");
		return SUCCESS;
	}
	
	@SuppressWarnings("deprecation")
	@SkipValidation
	public String changePasswordConfirm() {
		try {
			if (confirmUserPass!=null && confirmUserPass.length()>0 && confirmUserPass2!=null && confirmUserPass2.length()>0) {
				if (confirmUserPass.equalsIgnoreCase(confirmUserPass2)) {
					// check numeric and character combination!
					ApplicationSetup applicationSetup = basicDAOFactory.getApplicationSetupDAO().findByCriteria(Restrictions.ne("applicationSetupId", new Long(-1)));
					if (applicationSetup.isAlphabetUserPass()) regex = "(?=.*[a-z])"+regex;
					if (applicationSetup.isNumericUserPass()) regex = "(?=.*\\d)"+regex;
					if (applicationSetup.isUpperCaseLetter()) regex = "(?=.*[A-Z])"+regex;
					regex = "("+regex+".{"+applicationSetup.getMinUserPassLength()+",20})";
					//
					Pattern pattern = Pattern.compile(regex);
					Matcher matcher = pattern.matcher(confirmUserPass);
					if (!matcher.matches()) {
						addActionError("Password must contain "+(applicationSetup.isAlphabetUserPass()?"alphabet":"")+" "+(applicationSetup.isNumericUserPass()?"numeric":"")+" "+(applicationSetup.isUpperCaseLetter()?"upper-case letter":"")+" combination and less "+(applicationSetup.getMinUserPassLength())+" characters.");
						return INPUT;
					}
					User user = basicDAOFactory.getUserDAO().findUserAndRoleById(((User)getSession().get(Constants.USER)).getUserId());
					if (user!=null) {
						if (basicDAOFactory.getUserPasswordHistoryDAO().saveValidChangeUserPass(user.getUserId(), applicationSetup.getUserPassHistory(), confirmUserPass)) {
							if (user.getLastLoginDate()==null) {
								user.setLastLoginDate(new Date());
								//
								//session.put(Constants.USER, user);
								getSession().put(Constants.PERMISSIONS, user.getPermissionAccessByParentChild());
							}
							user.setUserPass(CommonUtil.digest(confirmUserPass));
							basicDAOFactory.getUserDAO().update(user);
							
							// sent change password email confirmation
							if (user.getEmail()!=null && user.getEmail().length()>0) {
								Properties p = new Properties();
								ServletContext servletContext = getServletContext();
								String path = servletContext.getRealPath("/");
								//logger.info("P : "+path);
								p.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, path);
								Velocity.init(p);
								VelocityContext context = new VelocityContext();
								
								context.put("userName", user.getUserName());
								context.put("userPass", confirmUserPass);
								
								StringWriter w = new StringWriter();
							    Velocity.mergeTemplate("/template/password_change.vm", context, w );
								
								OutgoingEmail outgoingEmail = new OutgoingEmail();
								outgoingEmail.setEmailDate(new Date());
								outgoingEmail.setSubject("Change Password Confirmation");
								outgoingEmail.setSender(applicationSetup.getFromEmailAddress());
								outgoingEmail.setTo(user.getEmail());
								outgoingEmail.setMessage(w.toString());
								addressDAOFactory.getOutgoingEmailDAO().save(outgoingEmail);
							}
						} else {
							addActionError("Please input another password!");
							return INPUT;
						}
					}
				} else {
					addActionError("New password and confirm new password are not same!");
					return INPUT;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
			return INPUT;
		}
		if (getSubaction()==null || (getSubaction()!=null && getSubaction().length()==0)) return SUCCESS;
		else return "index";
	}
	
	public String getActionClass() {
		return getClass().getSimpleName();
	}
	
	public String getDestination() {
		return getClass().getSimpleName();
	}

	public String getConfirmUserPass() {
		return confirmUserPass;
	}

	public void setConfirmUserPass(String confirmUserPass) {
		this.confirmUserPass = confirmUserPass;
	}

	public String getConfirmUserPass2() {
		return confirmUserPass2;
	}

	public void setConfirmUserPass2(String confirmUserPass2) {
		this.confirmUserPass2 = confirmUserPass2;
	}

	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ServletResponseAware#setServletResponse(javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;
	}

	/* (non-Javadoc)
	 * @see org.apache.struts2.util.ServletContextAware#setServletContext(javax.servlet.ServletContext)
	 */
	@Override
	public void setServletContext(ServletContext arg0) {
		this.servletContext = arg0;		
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
		
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public String list() {
		return null;
	}

	@Override
	public String partialList() {
		return null;
	}

	@Override
	public String add() {
		return null;
	}

	@Override
	public String save() {
		return null;
	}

	@Override
	public String edit() {
		return null;
	}

	@Override
	public String update() {
		return null;
	}

	@Override
	public String detail() {
		return null;
	}

	@Override
	public String delete() {
		return null;
	}

	@Override
	public String cancel() {
		return null;
	}
	
	

}
