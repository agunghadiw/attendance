package com.mpe.message.action;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.mpe.common.action.BaseAction;
import com.mpe.common.util.Constants;
import com.mpe.common.util.Pager;
import com.mpe.common.util.PartialList;
import com.mpe.message.model.OutgoingMessage;
import com.mpe.message.model.dao.MessageDAOFactory;
import com.mpe.message.model.dao.MessageDAOFactoryHibernate;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
/**
 * @author Agung Hadiwaluyo
 *
 */
public class OutgoingMessageAction extends BaseAction implements Preparable, ModelDriven<OutgoingMessage> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	MessageDAOFactory addressDAOFactory = MessageDAOFactory.instance(MessageDAOFactoryHibernate.class);
	
	List<OutgoingMessage> outgoingMessageList;
	OutgoingMessage outgoingMessage;
	String receiverX="", messageX="";
	
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
			PartialList<OutgoingMessage> partialList = addressDAOFactory.getOutgoingMessageDAO().findByCriteria(getStart(), getCount(), 
					getAscDesc().equalsIgnoreCase("asc")?Order.asc(getOrderBy().length()>0?getOrderBy():"outgoingMessageId"):Order.desc(getOrderBy().length()>0?getOrderBy():"outgoingMessageId"), 
					receiverX.length()>0?Restrictions.ilike("receiver", receiverX, MatchMode.ANYWHERE):Restrictions.ne("outgoingMessageId", new Long(-1)),
					messageX.length()>0?Restrictions.ilike("message", messageX, MatchMode.ANYWHERE):Restrictions.ne("outgoingMessageId", new Long(-1))
			);
			outgoingMessageList = partialList.getList();
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
	public OutgoingMessage getModel() {
		return outgoingMessage;
	}

	public List<OutgoingMessage> getOutgoingMessageList() {
		return outgoingMessageList;
	}

	public void setOutgoingMessageList(List<OutgoingMessage> outgoingMessageList) {
		this.outgoingMessageList = outgoingMessageList;
	}

	public OutgoingMessage getOutgoingMessage() {
		return outgoingMessage;
	}

	public void setOutgoingMessage(OutgoingMessage outgoingMessage) {
		this.outgoingMessage = outgoingMessage;
	}

	public String getReceiverX() {
		return receiverX;
	}

	public void setReceiverX(String receiverX) {
		this.receiverX = receiverX;
	}

	public String getMessageX() {
		return messageX;
	}

	public void setMessageX(String messageX) {
		this.messageX = messageX;
	}
	
	

}
