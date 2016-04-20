package com.mpe.basic.action;

import java.io.StringWriter;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.mpe.basic.model.User;
import com.mpe.basic.model.UserDelegation;
import com.mpe.basic.model.dao.BasicDAOFactory;
import com.mpe.basic.model.dao.BasicDAOFactoryHibernate;
import com.mpe.common.action.BaseAction;
import com.mpe.common.util.Constants;
import com.mpe.common.util.Formater;
import com.mpe.common.util.Pager;
import com.mpe.common.util.PartialList;
import com.mpe.message.model.OutgoingEmail;
import com.mpe.message.model.dao.MessageDAOFactory;
import com.mpe.message.model.dao.MessageDAOFactoryHibernate;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
/**
 * @author Agung Hadiwaluyo
 *
 */
public class UserDelegationAction extends BaseAction implements ServletContextAware,
		ServletResponseAware, ServletRequestAware, Preparable, ModelDriven<UserDelegation> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	BasicDAOFactory basicDAOFactory = BasicDAOFactory.instance(BasicDAOFactoryHibernate.class);
	MessageDAOFactory addressDAOFactory = MessageDAOFactory.instance(MessageDAOFactoryHibernate.class);
	
	long userDelegationId;
	UserDelegation userDelegation;
	List<UserDelegation> userDelegationList;
	Date fromDateX;
	Date toDateX;
	List<User> toUserList;
	
	ServletContext servletContext;
	
	@Override
	public String cancel() {
		setMappedRequest(Constants.PARTIAL_LIST);
		return Constants.REDIRECT;
	}

	@Override
	public String add() {
		setPreviousPage(Constants.ADD);
		setMappedRequest(Constants.SAVE);
		return SUCCESS;
	}

	@Override
	public String delete() {
		try {
			basicDAOFactory.getUserDelegationDAO().delete(userDelegationId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setMappedRequest(Constants.PARTIAL_LIST);
		return Constants.REDIRECT;
	}

	@Override
	public String detail() {
		setReadOnly(true);
		return SUCCESS;
	}

	@Override
	public String edit() {
		setPreviousPage(Constants.EDIT);
		setMappedRequest(Constants.UPDATE);
		return SUCCESS;
	}

	@Override
	public String list() {
		return null;
	}

	@Override
	public String partialList() {
		try {
			PartialList<UserDelegation> partialList = basicDAOFactory.getUserDelegationDAO().findByCriteria(getStart(), getCount(), 
					getAscDesc().equalsIgnoreCase("asc")?Order.asc(getOrderBy().length()>0?getOrderBy():"userDelegationId"):Order.desc(getOrderBy().length()>0?getOrderBy():"userDelegationId"), 
					// only user login show user's delegation => not all
					Restrictions.eq("fromUserId", new Long(((User)getSession().get(Constants.USER)).getUserId())),
					(fromDateX!=null && toDateX!=null)?(Restrictions.or((Restrictions.and(Restrictions.ge("fromDate", fromDateX), Restrictions.le("fromDate", toDateX))), Restrictions.or(Restrictions.and(Restrictions.le("fromDate", fromDateX), Restrictions.ge("toDate", toDateX)), Restrictions.and(Restrictions.ge("toDate", fromDateX), Restrictions.le("toDate", toDateX))))):Restrictions.ne("userDelegationId", new Long(-1))
					);
			userDelegationList = partialList.getList();
			setPager(Pager.generatePager(getStart(), getCount(), partialList.getTotal()));
			setPagerItem(Pager.generatePagerItem(getStart(), getCount(), partialList.getTotal()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		setMappedRequest(Constants.PARTIAL_LIST);
		return SUCCESS;
	}

	@SuppressWarnings("deprecation")
	@Override
	public String save() {
		Session session = null;
		Transaction transaction = null;
		try {
			session = basicDAOFactory.getUserDelegationDAO().getSession(Constants.HIBERNATE_CFG_KEY_1);
			transaction = session.beginTransaction();
			basicDAOFactory.getUserDelegationDAO().save(userDelegation, session);
			// update all of activity-esof
			//projectDAOFactory.getEsofDAO().delegateEsofProcess(userDelegation.getFromUserId(), userDelegation.getToUserId(), session);
			// update all of activity-CR
			//projectDAOFactory.getChangeRequestDAO().delegateChangeRequestProcess(userDelegation.getFromUserId(), userDelegation.getToUserId(), session);
			String email = basicDAOFactory.getUserDAO().getEmail(userDelegation.getToUserId(), session);
			// insert message
			if (userDelegation!=null && email!=null && email.length()>0) {
				Properties p = new Properties();
				ServletContext servletContext = getServletContext();
				String path = servletContext.getRealPath("/");
				//logger.info("P : "+path);
				p.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, path);
				Velocity.init(p);
				VelocityContext context = new VelocityContext();
				
				context.put("fullName", userDelegation.getToUser());
				context.put("previousEmployee", userDelegation.getFromUser());
				context.put("startDate", Formater.getFormatedDate(userDelegation.getFromDate(),"dd MMM yyyy"));
				context.put("endDate", Formater.getFormatedDate(userDelegation.getToDate(),"dd MMM yyyy"));
				
				StringWriter w = new StringWriter();
			    Velocity.mergeTemplate("/template/user_delegation.vm", context, w );
				
				OutgoingEmail outgoingEmail = new OutgoingEmail();
				outgoingEmail.setEmailDate(new Date());
				outgoingEmail.setSubject("Task Delegation");
				outgoingEmail.setSender(Constants.EMAIL_FROM);
				outgoingEmail.setTo(email);
				outgoingEmail.setMessage(w.toString());
				addressDAOFactory.getOutgoingEmailDAO().save(outgoingEmail, session);
			}
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			if (session!=null)session.close();
		}
		setMappedRequest(Constants.PARTIAL_LIST);
		return Constants.REDIRECT;
	}

	@Override
	public String update() {
		Session session = null;
		Transaction transaction = null;
		try {
			session = basicDAOFactory.getUserDelegationDAO().getSession(Constants.HIBERNATE_CFG_KEY_1);
			transaction = session.beginTransaction();
			basicDAOFactory.getUserDelegationDAO().update(userDelegation);
			// update all of activity-esof
			//projectDAOFactory.getEsofDAO().delegateEsofProcess(userDelegation.getFromUserId(), userDelegation.getToUserId(), session);
			// update all of activity-CR
			//projectDAOFactory.getChangeRequestDAO().delegateChangeRequestProcess(userDelegation.getFromUserId(), userDelegation.getToUserId(), session);
			// send email confirmation
			
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			if (session!=null)session.close();
		}
		setMappedRequest(Constants.PARTIAL_LIST);
		return Constants.REDIRECT;
	}

	@Override
	public void prepare() throws Exception {
		User user = (User)getSession().get(Constants.USER);
		try {
			// customer
			//Set<Long> customerUserIds = thirdPartyDAOFactory.getCustomerDAO().listUserIdsByUserId(user.getUserId());
			// vendor
			//Set<Long> vendorUserIds = thirdPartyDAOFactory.getVendorDAO().listUserIdsByUserId(user.getUserId());
			//if (toUserList==null) toUserList = basicDAOFactory.getUserDAO().findByOrganizationIdAndOthers(user.getOrganization()!=null?user.getOrganization().getOrganizationId():null, customerUserIds.size()>0?customerUserIds:vendorUserIds);
			Session session = basicDAOFactory.getUserDAO().getSession(Constants.HIBERNATE_CFG_KEY_1);
			Criteria criteria = session.createCriteria(User.class)
				.setFetchMode("organization", FetchMode.JOIN)
				//.setFetchMode("customer", FetchMode.JOIN)
				.add(Restrictions.ne("userId", new Long(user.getUserId())));
			if (user.getOrganization()!=null) {
				criteria.add(Restrictions.eq("organization.organizationId", new Long(user.getOrganization().getOrganizationId())));
			} else {
				criteria.add(Restrictions.isNotNull("organization"));
			}
			/*// customer delegation
			if (user.getCustomerCompany()!=null && user.getCustomerCompany().length()>0) {
				criteria.add(Restrictions.in("userId", thirdPartyDAOFactory.getCustomerDAO().listUserIdsByUserId(user.getUserId())));
			}
			// vendor delegation
			if (user.getVendorCompany()!=null && user.getVendorCompany().length()>0) {
				criteria.add(Restrictions.in("userId", thirdPartyDAOFactory.getVendorDAO().listUserIdsByUserId(user.getUserId())));
			}*/
			toUserList = criteria.list();
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (userDelegationId==0) {
			userDelegation = new UserDelegation();
			userDelegation.setFromUserId(user.getUserId());
			userDelegation.setFromUser(user.getUserName());
		} else {
			userDelegation = basicDAOFactory.getUserDelegationDAO().findById(userDelegationId);
		}
		
	}

	@Override
	public UserDelegation getModel() {
		return userDelegation;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		
	}

	public long getUserDelegationId() {
		return userDelegationId;
	}

	public void setUserDelegationId(long userDelegationId) {
		this.userDelegationId = userDelegationId;
	}

	public UserDelegation getUserDelegation() {
		return userDelegation;
	}

	public void setUserDelegation(UserDelegation userDelegation) {
		this.userDelegation = userDelegation;
	}

	public List<UserDelegation> getUserDelegationList() {
		return userDelegationList;
	}

	public void setUserDelegationList(List<UserDelegation> userDelegationList) {
		this.userDelegationList = userDelegationList;
	}

	public Date getFromDateX() {
		return fromDateX;
	}

	public void setFromDateX(Date fromDateX) {
		this.fromDateX = fromDateX;
	}

	public Date getToDateX() {
		return toDateX;
	}

	public void setToDateX(Date toDateX) {
		this.toDateX = toDateX;
	}

	@Override
	public void setServletContext(ServletContext arg0) {
		this.servletContext = arg0;
		
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public List<User> getToUserList() {
		return toUserList;
	}

	public void setToUserList(List<User> toUserList) {
		this.toUserList = toUserList;
	}
	
	
	
	
}
