package com.mpe.message.action;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.mpe.common.action.BaseAction;
import com.mpe.common.util.Constants;
import com.mpe.common.util.Pager;
import com.mpe.common.util.PartialList;
import com.mpe.message.model.IncomingMessage;
import com.mpe.message.model.dao.MessageDAOFactory;
import com.mpe.message.model.dao.MessageDAOFactoryHibernate;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
/**
 * @author Agung Hadiwaluyo
 *
 */
public class IncomingMessageAction extends BaseAction implements Preparable, ModelDriven<IncomingMessage> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	MessageDAOFactory addressDAOFactory = MessageDAOFactory.instance(MessageDAOFactoryHibernate.class);
	
	List<IncomingMessage> incomingMessageList;
	IncomingMessage incomingMessage;
	String senderX="", messageX="";
	
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
			PartialList<IncomingMessage> partialList = addressDAOFactory.getIncomingMessageDAO().findByCriteria(getStart(), getCount(), 
					getAscDesc().equalsIgnoreCase("asc")?Order.asc(getOrderBy().length()>0?getOrderBy():"incomingMessageId"):Order.desc(getOrderBy().length()>0?getOrderBy():"incomingMessageId"), 
					senderX.length()>0?Restrictions.ilike("sender", senderX, MatchMode.ANYWHERE):Restrictions.ne("incomingMessageId", new Long(-1)),
					messageX.length()>0?Restrictions.ilike("message", messageX, MatchMode.ANYWHERE):Restrictions.ne("incomingMessageId", new Long(-1))
			);
			incomingMessageList = partialList.getList();
			setPager(Pager.generatePager(getStart(), getCount(), partialList.getTotal()));
			setPagerItem(Pager.generatePagerItem(getStart(), getCount(), partialList.getTotal()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		setMappedRequest(Constants.PARTIAL_LIST);
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
	public IncomingMessage getModel() {
		return incomingMessage;
	}

}
