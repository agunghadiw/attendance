package com.mpe.basic.action;

import java.io.StringWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.apache.struts2.util.ServletContextAware;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.depsos.hr.model.EmployeeAttendance;
import com.depsos.hr.model.dao.HrDAOFactory;
import com.depsos.hr.model.dao.HrDAOFactoryHibernate;
import com.depsos.hr.model.other.AttendanceMachineList;
import com.depsos.hr.model.other.EmployeeAttendanceBarChart;
import com.mpe.basic.model.ApplicationSetup;
import com.mpe.basic.model.Branch;
import com.mpe.basic.model.Organization;
import com.mpe.basic.model.User;
import com.mpe.basic.model.Role;
import com.mpe.basic.model.dao.BasicDAOFactory;
import com.mpe.basic.model.dao.BasicDAOFactoryHibernate;
import com.mpe.basic.model.other.UserType;
import com.mpe.basic.model.other.WorkOffDayCalendar;
import com.mpe.common.action.BaseAction;
import com.mpe.common.util.CommonUtil;
import com.mpe.common.util.Constants;
import com.mpe.common.util.Pager;
import com.mpe.common.util.PartialList;
import com.mpe.message.model.OutgoingEmail;
import com.mpe.message.model.dao.MessageDAOFactory;
import com.mpe.message.model.dao.MessageDAOFactoryHibernate;
import com.mpe.workflow.model.dao.WorkflowDAOFactory;
import com.mpe.workflow.model.dao.WorkflowDAOFactoryHibernate;
import com.mpe.workflow.model.helper.UserHomeTaskList;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;


/**
 * @author Agung Hadiwaluyo
 *
 */
public class UserAction extends BaseAction implements ServletContextAware,Preparable, ModelDriven<User> {

	private static final long serialVersionUID = 1L;
	BasicDAOFactory basicDAOFactory = BasicDAOFactory.instance(BasicDAOFactoryHibernate.class);
	MessageDAOFactory addressDAOFactory = MessageDAOFactory.instance(MessageDAOFactoryHibernate.class);
	HrDAOFactory hrDAOFactory = HrDAOFactory.instance(HrDAOFactoryHibernate.class);
	WorkflowDAOFactory workflowDAOFactory = WorkflowDAOFactory.instance(WorkflowDAOFactoryHibernate.class);
	
	//String regex = "((?=.*\\d)(?=.*[a-zA-Z])(?!.*[()+}{;=`~:\\|'?/>.<,]).{8,20})";
	String regex = "";
	
	User user;
	List<User> userList;
	List<Role> roleList;
	List<Organization> organizationList;
	List<Branch> branchList;
	
	
	//List<Customer> customerList;
	long userId, organizationIdx;
	long[] roleIdList;
	String userNamex="", userTypex="", fullNameX="";
	Vector<String> userTypeList = new Vector<String>();
	String confirmUserPass;
	String confirmUserPass2;
	Long organizationId;
	Long roleIdx;
	Long vendorIdx;
	//Long customerId;
	String error;
	
	// outgoing
	
	boolean showDashboard = false;
	
	String target="";
	String organizationTarget="";
	String roleType="";
	String viewName="";
	
	ServletContext servletContext;
	
	// home task list
	//List<UserHomeTaskList> employeeTaskList;
	//List<UserHomeTaskList> employeeAttendanceAllowanceTaskList;
	
	/*
	
	// overdue
	List<EmployeeOverDueTaskList> contractEmployeeOverDueTaskLists;
	List<EmployeeOverDueTaskList> probationEmployeeOverDueTaskLists;*/
	
	//List<AttendanceMachineList> attendanceMachineLists;
	List<WorkOffDayCalendar> workOffDayCalendars;
	
	String calendarMonth="";
	String calendarValue1="";
	String calendarValue2="";
	
	@Override
	public String cancel() {
		setMappedRequest(Constants.PARTIAL_LIST);
		return Constants.REDIRECT;
	}
	
	@SkipValidation
	public String home() {
		
		// clear session from back!
		getSession().remove(Constants.WORKFLOW);
		getSession().remove(Constants.CURRENT_PROCESS);
		getSession().remove("employeeLeave");
		getSession().remove("employeeAttendanceAllowanceMonthly");
		
		
		setPreviousPage("UserAction_home.action");
		setMappedRequestFullPath("/basic/LogonAction_logon.action");
		//getSession().remove(Constants.CHANGE_REQUEST);
		//getSession().remove(Constants.ACTIVITY);
		
		Calendar c = new GregorianCalendar();
		
		try {
			User user = (User)getSession().get(Constants.USER);
			if (user!=null) {
				
				/*promotions = smlPayDAOFactory.getPromotionDAO().findByCriteria(Order.desc("promotionId"), 
						Restrictions.le("startDate", new Date()),
						Restrictions.ge("endDate", new Date()));*/
				
				/*deals = smlPayDAOFactory.getDealDAO().findByCriteria(Order.desc("dealId"), 
						Restrictions.le("startDate", new Date()),
						Restrictions.ge("endDate", new Date()));*/
				
				for (Role role : user.getRoles()) {
					if (role.isShowDashboard()) showDashboard = true;
				} 
				
			}
			
			//
			/*internshipToEmployeeTaskList = hrDAOFactory.getInternshipToEmployeeDAO().show(user, 10, null, null);
			applicantTaskList = hrDAOFactory.getApplicantDAO().show(user, 10, null, null);
			employeeRecordTaskList = hrDAOFactory.getEmployeeRecordDAO().show(user, 10, null, null);
			
			HrSetup hrSetup = hrDAOFactory.getHrSetupDAO().findByCriteria(Restrictions.ne("hrSetupId", new Long(-1)));
			if (hrSetup!=null) {
				contractEmployeeOverDueTaskLists = hrDAOFactory.getEmployeeDAO().show(user, hrSetup.getContractStatusId(), 10, "e.full_name", "asc", hrSetup.getOverdueEmployeeContract());
				probationEmployeeOverDueTaskLists = hrDAOFactory.getEmployeeDAO().show(user, hrSetup.getProbationStatusId(), 10, "e.full_name", "asc", hrSetup.getOverdueEmployeeContract());
			}*/
			
			//attendanceMachineLists = hrDAOFactory.getAttendanceMachineDAO().dangerList(30);
			//employeeTaskList = workflowDAOFactory.getProcessDAO().show(user, 0, "processDate", "asc");
			workOffDayCalendars = basicDAOFactory.getWorkOffDayDAO().eventByYear(c.get(Calendar.YEAR));
			List<EmployeeAttendanceBarChart> employeeAttendanceBarCharts = hrDAOFactory.getEmployeeAttendanceAllowanceMonthlyDAO().listByYear();
			for (EmployeeAttendanceBarChart chart : employeeAttendanceBarCharts) {
				calendarMonth = calendarMonth + (calendarMonth.length()>0?",":"") + "'"+chart.getMonth()+"'";
				calendarValue1 = calendarValue1 + (calendarValue1.length()>0?",":"") + chart.getAttendance();
				calendarValue2 = calendarValue2 + (calendarValue2.length()>0?",":"") + chart.getAttendanceNote();
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	@Override
	public String add() {
		try {
			setPreviousPage(Constants.ADD);
			setMappedRequest(Constants.SAVE);
		} catch (Exception e) {
		}
		return SUCCESS;
	}

	@Override
	public String delete() {
		try {
			basicDAOFactory.getUserDAO().delete(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setMappedRequest(Constants.PARTIAL_LIST);
		return Constants.REDIRECT;
	}

	@Override
	public String detail() {
		try {
			if (user.getRoles()!=null && user.getRoles().size()>0) {
				Iterator<Role> iterator = user.getRoles().iterator();
				roleIdList = new long[user.getRoles().size()];
				int i=0;
				while (iterator.hasNext()) {
					Role role = (Role)iterator.next();
					roleIdList[i] = role.getRoleId();
					i++;
				}
			}
		} catch (Exception e) {
		}
		setReadOnly(true);
		setMappedRequest(Constants.LIST);
		return SUCCESS;
	}

	@Override
	public String edit() {
		try {
			setPreviousPage(Constants.EDIT);
			setMappedRequest(Constants.UPDATE);
			if (user.getRoles()!=null && user.getRoles().size()>0) {
				Iterator<Role> iterator = user.getRoles().iterator();
				roleIdList = new long[user.getRoles().size()];
				int i=0;
				while (iterator.hasNext()) {
					Role role = (Role)iterator.next();
					roleIdList[i] = role.getRoleId();
					i++;
				}
			}
		} catch (Exception e) {
		}
		return SUCCESS;
	}

	@Override
	public User getModel() {
		return user;
	}

	@Override
	public String list() {
		try {
			userList = basicDAOFactory.getUserDAO().findAll(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setMappedRequest(Constants.LIST);
		return Constants.LIST;
	}
	
	@SkipValidation
	public String popUpUser() {
		try {
			if (organizationTarget.equalsIgnoreCase("DC")) {
				// get organization
				//organizationIdx = basicDAOFactory.getOrganizationDAO().getDefaultOrganizationId();
			} else {
				
			}
			if (roleType!=null && roleType.length()>0) roleIdx = basicDAOFactory.getRoleDAO().findByName(roleType);	
			String sql = "";
			//String sql2 = "";
			String sql3 = "";
			if (roleIdx!=null && roleIdx.longValue()>0) sql = " user_id IN (select ur.user_id from user_role ur where ur.role_id="+roleIdx.longValue()+") ";
			//if (vendorIdx!=null && vendorIdx.longValue()>0) sql2 = " user_id IN (select cc.user_id from employee cc where cc.vendor_id="+vendorIdx.longValue()+") ";
			if (viewName!=null && viewName.length()>0) sql3 = " user_id IN (select dd.user_id from user_role dd join role_view ee on dd.role_id=ee.role_id join views vv on ee.view_id=vv.view_id where vv.link like '"+viewName+"') ";
			if (fullNameX.length()>0) sql = " user_id IN (select e.user_id from employee e where lower(e.full_name) like lower('%"+fullNameX+"%') ) ";
			PartialList<User> partialList = basicDAOFactory.getUserDAO().findByCriteria(getStart(), getCount(), getAscDesc().equalsIgnoreCase("asc")?Order.asc(getOrderBy().length()>0?getOrderBy():"userId"):Order.desc(getOrderBy().length()>0?getOrderBy():"userId"), 
					organizationIdx>0?Restrictions.eq("organization.organizationId", new Long(organizationIdx)):Restrictions.ne("userId", new Long(-1)), 
					userNamex.length()>0?Restrictions.like("userName", userNamex,MatchMode.ANYWHERE):Restrictions.ne("userId", new Long(-1)),
					roleIdx!=null && roleIdx.longValue()>0?Restrictions.sqlRestriction(sql):Restrictions.ne("userId", new Long(-1)),
					//vendorIdx!=null && vendorIdx.longValue()>0?Restrictions.sqlRestriction(sql2):Restrictions.ne("userId", new Long(-1)),
					viewName!=null && viewName.length()>0?Restrictions.sqlRestriction(sql3):Restrictions.ne("userId", new Long(-1)),
					fullNameX.length()>0?Restrictions.sqlRestriction(sql):Restrictions.ne("userId", new Long(-1))
			);
			userList = partialList.getList();
			setPager(Pager.generatePager(getStart(), getCount(), partialList.getTotal()));
			setPagerItem(Pager.generatePagerItem(getStart(), getCount(), partialList.getTotal()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		setMappedRequest("popUpUser");
		return SUCCESS;
	}

	@Override
	public String partialList() {
		try {
			String sql = "";
			if (roleIdx!=null && roleIdx.longValue()>0) sql = " user_id IN (select ur.user_id from user_role ur where ur.role_id="+roleIdx.longValue()+") ";
			if (fullNameX.length()>0) sql = " user_id IN (select e.user_id from employee e where lower(e.full_name) like lower('%"+fullNameX+"%') ) ";
			PartialList<User> partialList = basicDAOFactory.getUserDAO().findByCriteria(getStart(), getCount(), getAscDesc().equalsIgnoreCase("asc")?Order.asc(getOrderBy().length()>0?getOrderBy():"userId"):Order.desc(getOrderBy().length()>0?getOrderBy():"userId"), 
					organizationIdx>0?Restrictions.eq("organization.organizationId", new Long(organizationIdx)):(Restrictions.or(Restrictions.ne("organization.organizationId", new Long(-1)), Restrictions.isNull("organization.organizationId"))), 
					userNamex.length()>0?Restrictions.like("userName", userNamex,MatchMode.ANYWHERE):Restrictions.ne("userId", new Long(-1)), 
					//userTypex.length()>0?Restrictions.eq("userType", UserType.valueOf(userTypex)):Restrictions.isNotNull("userType"),
					roleIdx!=null && roleIdx.longValue()>0?Restrictions.sqlRestriction(sql):Restrictions.ne("userId", new Long(-1)),
					fullNameX.length()>0?Restrictions.sqlRestriction(sql):Restrictions.ne("userId", new Long(-1))
			);
			userList = partialList.getList();
			setPager(Pager.generatePager(getStart(), getCount(), partialList.getTotal()));
			setPagerItem(Pager.generatePagerItem(getStart(), getCount(), partialList.getTotal()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		setMappedRequest(Constants.PARTIAL_LIST);
		return SUCCESS;
	}
	
	/*@Validations(
		requiredStrings={
			@RequiredStringValidator(type=ValidatorType.SIMPLE,fieldName="userName",trim=true,key="validation.userName.required"),
			@RequiredStringValidator(type=ValidatorType.SIMPLE,fieldName="userPass",trim=true,key="validation.userPass.required")
		},
		stringLengthFields={
			@StringLengthFieldValidator(type=ValidatorType.SIMPLE,fieldName="userName",trim=true,minLength="8",message="Length of username must be greater than 8 characters")
		}
	)*/

	@Override
	public String save() {
		try {
			// check duplicate user-name
			if (basicDAOFactory.getUserDAO().duplicateUserName(user.getUserName())) {
				addActionError("Duplicate username, change with new one.");
				return INPUT;
			}
			// check numeric and character combination!
			ApplicationSetup applicationSetup = basicDAOFactory.getApplicationSetupDAO().findByCriteria(Restrictions.ne("applicationSetupId", new Long(-1)));
			if (applicationSetup==null) {
				addActionError("Please setup Application Setup first!");
				return INPUT;
			}
			if (applicationSetup.isAlphabetUserPass()) regex = "(?=.*[a-z])"+regex;
			if (applicationSetup.isNumericUserPass()) regex = "(?=.*\\d)"+regex;
			if (applicationSetup.isUpperCaseLetter()) regex = "(?=.*[A-Z])"+regex;
			regex = "("+regex+".{"+applicationSetup.getMinUserPassLength()+",20})";
			//
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(user.getUserPass());
			if (!matcher.matches()) {
				addActionError("Password must contain "+(applicationSetup.isAlphabetUserPass()?"alphabet":"")+" "+(applicationSetup.isNumericUserPass()?"numeric":"")+" "+(applicationSetup.isUpperCaseLetter()?"upper-case letter":"")+" combination and less "+(applicationSetup.getMinUserPassLength())+" characters.");
				return INPUT;
			}
			if (roleIdList.length>0) {
				for (int i=0; i<roleIdList.length; i++) {
					Role role = basicDAOFactory.getRoleDAO().findById(roleIdList[i]);
					user.getRoles().add(role);
				}
			}
			Organization organization = basicDAOFactory.getOrganizationDAO().findById(organizationId);
			//Customer customer = thirdPartyDAOFactory.getCustomerDAO().findById(customerId);
			user.setOrganization(organization);
			//user.setCustomer(customer);
			user.setUserPass(CommonUtil.digest(user.getUserPass()));
			user.setActive(true);
			basicDAOFactory.getUserDAO().save(user);
		} catch (Exception e) {
			addActionError(e.getMessage());
			return INPUT;
		}
		setMappedRequest(Constants.PARTIAL_LIST);
		return Constants.REDIRECT;
	}

	@Override
	public String update() {
		try {
			// remove old relation
			user.getRoles().clear();
			if (roleIdList.length>0) {
				for (int i=0; i<roleIdList.length; i++) {
					Role role = basicDAOFactory.getRoleDAO().findById(roleIdList[i]);
					user.getRoles().add(role);
				}
			}
			Organization organization = basicDAOFactory.getOrganizationDAO().findById(organizationId);
			//Customer customer = thirdPartyDAOFactory.getCustomerDAO().findById(customerId);
			user.setOrganization(organization);
			//user.setCustomer(customer);
			ApplicationSetup applicationSetup = basicDAOFactory.getApplicationSetupDAO().findByCriteria(Restrictions.ne("applicationSetupId", new Long(-1)));
			if (confirmUserPass!=null && confirmUserPass.length()>0) {
				// check numeric and character combination!
				if (applicationSetup.isAlphabetUserPass()) regex = "(?=.*[a-z])"+regex;
				if (applicationSetup.isNumericUserPass()) regex = "(?=.*\\d)"+regex;
				if (applicationSetup.isUpperCaseLetter()) regex = "(?=.*[A-Z])"+regex;
				regex = "("+regex+".{"+applicationSetup.getMinUserPassLength()+",20})";
				logger.info(" [ regex = "+regex+" ] ");
				//
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(confirmUserPass);
				if (!matcher.matches()) {
					addActionError("Password must contain "+(applicationSetup.isAlphabetUserPass()?"alphabet":"")+" "+(applicationSetup.isNumericUserPass()?"numeric":"")+" "+(applicationSetup.isUpperCaseLetter()?"upper-case letter":"")+" combination and less "+(applicationSetup.getMinUserPassLength())+" characters.");
					return INPUT;
				}
				user.setUserPass(CommonUtil.digest(confirmUserPass));
			}
			//if (basicDAOFactory.getUserPasswordHistoryDAO().saveValidChangeUserPass(userId, applicationSetup.getUserPassHistory(), confirmUserPass)) {
				basicDAOFactory.getUserDAO().update(user);
			/*} else {
				addActionError("Please input another password!");
				return INPUT; 
			}*/
		} catch (Exception e) {
			addActionError(e.getMessage());
			return INPUT;
		}
		setMappedRequest(Constants.PARTIAL_LIST);
		return Constants.REDIRECT;
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
					user = basicDAOFactory.getUserDAO().findById(((User)getSession().get(Constants.USER)).getUserId());
					if (basicDAOFactory.getUserPasswordHistoryDAO().saveValidChangeUserPass(user.getUserId(), applicationSetup.getUserPassHistory(), confirmUserPass)) {
						
						user.setUserPass(CommonUtil.digest(confirmUserPass));
						basicDAOFactory.getUserDAO().update(user);
						
						// sent email confirmation
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
							outgoingEmail.setSender(Constants.EMAIL_FROM);
							outgoingEmail.setTo(user.getEmail());
							outgoingEmail.setMessage(w.toString());
							addressDAOFactory.getOutgoingEmailDAO().save(outgoingEmail);
						}
					} else {
						addActionError("Please input another password!");
						return INPUT;
					}
					
				} else {
					addActionError("Password and confirm password not same!");
					return INPUT;
				}
			}
			
		} catch (Exception e) {
			addActionError(e.getMessage());
			return INPUT;
		}
		if (getSubaction()==null || (getSubaction()!=null && getSubaction().length()==0)) return SUCCESS;
		else return "index";
	}
	
	@Override
	public void prepare() throws Exception {
		try {
			if (roleList==null) roleList = basicDAOFactory.getRoleDAO().findAll(null);
			if (organizationList==null) organizationList = basicDAOFactory.getOrganizationDAO().findAll(Order.asc("name"));
			if (branchList==null) branchList = basicDAOFactory.getBranchDAO().findAll(Order.asc("name"));
			//if (customerList==null) customerList = thirdPartyDAOFactory.getCustomerDAO().findAll();
			if (userTypeList.size()==0) {
				UserType[] types = UserType.values();
				for (int i=0; i<types.length; i++) {
					userTypeList.add(types[i].name());
				}
			}
			//if (businessUnitList==null) businessUnitList = projectDAOFactory.getBusinessUnitDAO().findByCriteria(Order.asc("name"),Restrictions.isNull("parentId"));
		} catch (Exception e) {
		}
		if (getUserId()==0) {
			user = new User();
			//organization = new Organization();
		} else {
			Session session = basicDAOFactory.getUserDAO().getSession(Constants.HIBERNATE_CFG_KEY_1);
			user = (User)session.createCriteria(User.class)
				.add(Restrictions.eq("userId", new Long(userId)))
				.setFetchMode("organization", FetchMode.JOIN)
				//.setFetchMode("customer", FetchMode.JOIN)
				.uniqueResult();
			organizationId = user.getOrganization()!=null?user.getOrganization().getOrganizationId():0;
			//customerId = user.getCustomer()!=null?user.getCustomer().getCustomerId():0;
			//
			session.close();
		}
	}

	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public List<Organization> getOrganizationList() {
		return organizationList;
	}

	public void setOrganizationList(List<Organization> organizationList) {
		this.organizationList = organizationList;
	}

	public long[] getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(long[] roleIdList) {
		this.roleIdList = roleIdList;
	}

	public Vector<String> getUserTypeList() {
		return userTypeList;
	}

	public void setUserTypeList(Vector<String> userTypeList) {
		this.userTypeList = userTypeList;
	}

	public String getConfirmUserPass() {
		return confirmUserPass;
	}

	public void setConfirmUserPass(String confirmUserPass) {
		this.confirmUserPass = confirmUserPass;
	}

	public long getOrganizationIdx() {
		return organizationIdx;
	}

	public void setOrganizationIdx(long organizationIdx) {
		this.organizationIdx = organizationIdx;
	}

	public String getUserNamex() {
		return userNamex;
	}

	public void setUserNamex(String userNamex) {
		this.userNamex = userNamex;
	}

	public String getUserTypex() {
		return userTypex;
	}

	public void setUserTypex(String userTypex) {
		this.userTypex = userTypex;
	}
	

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public String getConfirmUserPass2() {
		return confirmUserPass2;
	}

	public void setConfirmUserPass2(String confirmUserPass2) {
		this.confirmUserPass2 = confirmUserPass2;
	}

	public boolean isShowDashboard() {
		return showDashboard;
	}

	public void setShowDashboard(boolean showDashboard) {
		this.showDashboard = showDashboard;
	}

	public Long getRoleIdx() {
		return roleIdx;
	}

	public void setRoleIdx(Long roleIdx) {
		this.roleIdx = roleIdx;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getOrganizationTarget() {
		return organizationTarget;
	}

	public void setOrganizationTarget(String organizationTarget) {
		this.organizationTarget = organizationTarget;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
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

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public Long getVendorIdx() {
		return vendorIdx;
	}

	public void setVendorIdx(Long vendorIdx) {
		this.vendorIdx = vendorIdx;
	}

	public String getFullNameX() {
		return fullNameX;
	}

	public void setFullNameX(String fullNameX) {
		this.fullNameX = fullNameX;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public List<Branch> getBranchList() {
		return branchList;
	}

	public void setBranchList(List<Branch> branchList) {
		this.branchList = branchList;
	}

	public List<WorkOffDayCalendar> getWorkOffDayCalendars() {
		return workOffDayCalendars;
	}

	public void setWorkOffDayCalendars(List<WorkOffDayCalendar> workOffDayCalendars) {
		this.workOffDayCalendars = workOffDayCalendars;
	}

	public String getCalendarMonth() {
		return calendarMonth;
	}

	public void setCalendarMonth(String calendarMonth) {
		this.calendarMonth = calendarMonth;
	}

	public String getCalendarValue1() {
		return calendarValue1;
	}

	public void setCalendarValue1(String calendarValue1) {
		this.calendarValue1 = calendarValue1;
	}

	public String getCalendarValue2() {
		return calendarValue2;
	}

	public void setCalendarValue2(String calendarValue2) {
		this.calendarValue2 = calendarValue2;
	}
	
	
	
	
	
}
