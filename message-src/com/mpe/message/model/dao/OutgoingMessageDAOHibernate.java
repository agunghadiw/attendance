package com.mpe.message.model.dao;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.mpe.common.dao.GenericHibernateDAO;
import com.mpe.message.model.OutgoingMessage;

/**
 * @author Agung Hadiwaluyo
 *
 */
public class OutgoingMessageDAOHibernate extends GenericHibernateDAO<OutgoingMessage, Long> implements OutgoingMessageDAO {

	@Override
	public OutgoingMessage findLastMessage(String receiver) throws Exception {
		Session session = getSessionAnnotated();
		OutgoingMessage outgoingMessage = null;
		try {
			outgoingMessage = (OutgoingMessage)session.createCriteria(OutgoingMessage.class)
				.add(Restrictions.eq("receiver", new String(receiver)))
				//.add(Restrictions.isNotNull("sentDate"))
				//.addOrder(Order.desc("sentDate"))
				.addOrder(Order.desc("outgoingMessageId"))
				.setMaxResults(1).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session!=null)session.close();
		}
		return outgoingMessage;
	}
	
}
