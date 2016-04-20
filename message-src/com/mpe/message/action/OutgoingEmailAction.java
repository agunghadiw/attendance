package com.mpe.message.action;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.mpe.common.action.BaseAction;
import com.mpe.common.util.Constants;
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
public class OutgoingEmailAction extends BaseAction implements Preparable, ModelDriven<OutgoingEmail> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	MessageDAOFactory addressDAOFactory = MessageDAOFactory.instance(MessageDAOFactoryHibernate.class);
	
	List<OutgoingEmail> outgoingEmailList;
	OutgoingEmail outgoingEmail;
	String senderX="",toX="",subjectX="", messageX="";
	
	@Override
	public String cancel() {
		setMappedRequest(Constants.PARTIAL_LIST);
		return Constants.REDIRECT;
	}

	@Override
	public String add() {
		return null;
	}

	@Override
	public String delete() {
		return null;
	}

	@Override
	public String detail() {
		return null;
	}

	@Override
	public String edit() {
		return null;
	}

	@Override
	public String list() {
		return null;
	}

	@Override
	public String partialList() {
		try {
			PartialList<OutgoingEmail> partialList = addressDAOFactory.getOutgoingEmailDAO().findByCriteria(getStart(), getCount(), 
					getAscDesc().equalsIgnoreCase("asc")?Order.asc(getOrderBy().length()>0?getOrderBy():"outgoingEmailId"):Order.desc(getOrderBy().length()>0?getOrderBy():"outgoingEmailId"), 
					senderX.length()>0?Restrictions.ilike("sender", senderX, MatchMode.ANYWHERE):Restrictions.ne("outgoingEmailId", new Long(-1)),
					toX.length()>0?Restrictions.ilike("to", toX, MatchMode.ANYWHERE):Restrictions.ne("outgoingEmailId", new Long(-1)),
					subjectX.length()>0?Restrictions.ilike("subject", subjectX, MatchMode.ANYWHERE):Restrictions.ne("outgoingEmailId", new Long(-1)),
					messageX.length()>0?Restrictions.ilike("message", messageX, MatchMode.ANYWHERE):Restrictions.ne("outgoingEmailId", new Long(-1)),
					Restrictions.isNull("sentDate")
			);
			outgoingEmailList = partialList.getList();
			setPager(Pager.generatePager(getStart(), getCount(), partialList.getTotal()));
			setPagerItem(Pager.generatePagerItem(getStart(), getCount(), partialList.getTotal()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		setMappedRequest(Constants.PARTIAL_LIST);
		return SUCCESS;
	}
	
	public String sentPartialList() {
		try {
			PartialList<OutgoingEmail> partialList = addressDAOFactory.getOutgoingEmailDAO().findByCriteria(getStart(), getCount(), 
					getAscDesc().equalsIgnoreCase("asc")?Order.asc(getOrderBy().length()>0?getOrderBy():"outgoingEmailId"):Order.desc(getOrderBy().length()>0?getOrderBy():"outgoingEmailId"), 
					senderX.length()>0?Restrictions.ilike("sender", senderX, MatchMode.ANYWHERE):Restrictions.ne("outgoingEmailId", new Long(-1)),
					toX.length()>0?Restrictions.ilike("to", toX, MatchMode.ANYWHERE):Restrictions.ne("outgoingEmailId", new Long(-1)),
					subjectX.length()>0?Restrictions.ilike("subject", subjectX, MatchMode.ANYWHERE):Restrictions.ne("outgoingEmailId", new Long(-1)),
					messageX.length()>0?Restrictions.ilike("message", messageX, MatchMode.ANYWHERE):Restrictions.ne("outgoingEmailId", new Long(-1)),
					Restrictions.isNotNull("sentDate")
			);
			outgoingEmailList = partialList.getList();
			setPager(Pager.generatePager(getStart(), getCount(), partialList.getTotal()));
			setPagerItem(Pager.generatePagerItem(getStart(), getCount(), partialList.getTotal()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		setMappedRequest("sentPartialList");
		return SUCCESS;
	}

	@Override
	public String save() {
		return null;
	}

	@Override
	public String update() {
		return null;
	}

	@Override
	public void prepare() throws Exception {
		
	}

	@Override
	public OutgoingEmail getModel() {
		return outgoingEmail;
	}

	public List<OutgoingEmail> getOutgoingEmailList() {
		return outgoingEmailList;
	}

	public void setOutgoingEmailList(List<OutgoingEmail> outgoingEmailList) {
		this.outgoingEmailList = outgoingEmailList;
	}

	public OutgoingEmail getOutgoingEmail() {
		return outgoingEmail;
	}

	public void setOutgoingEmail(OutgoingEmail outgoingEmail) {
		this.outgoingEmail = outgoingEmail;
	}

	public String getSenderX() {
		return senderX;
	}

	public void setSenderX(String senderX) {
		this.senderX = senderX;
	}

	public String getToX() {
		return toX;
	}

	public void setToX(String toX) {
		this.toX = toX;
	}

	public String getSubjectX() {
		return subjectX;
	}

	public void setSubjectX(String subjectX) {
		this.subjectX = subjectX;
	}

	public String getMessageX() {
		return messageX;
	}

	public void setMessageX(String messageX) {
		this.messageX = messageX;
	}
	
	

}
