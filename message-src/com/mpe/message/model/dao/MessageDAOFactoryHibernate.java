package com.mpe.message.model.dao;

import java.io.Serializable;

import com.mpe.common.dao.GenericHibernateDAO;


/**
 * @author Agung Hadiwaluyo
 *
 */
public class MessageDAOFactoryHibernate extends MessageDAOFactory implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	private GenericHibernateDAO instantiateDAO(Class daoClass) {
		try {
			GenericHibernateDAO dao = (GenericHibernateDAO) daoClass.newInstance();
			return dao;
		} catch (Exception ex) {
			throw new RuntimeException("Can not instantiate DAO: " + daoClass, ex);
		}
	}

	@Override
	public IncomingMessageDAO getIncomingMessageDAO() {
		return (IncomingMessageDAO) instantiateDAO(IncomingMessageDAOHibernate.class);
	}

	@Override
	public OutgoingEmailDAO getOutgoingEmailDAO() {
		return (OutgoingEmailDAO) instantiateDAO(OutgoingEmailDAOHibernate.class);
	}

	@Override
	public OutgoingMessageDAO getOutgoingMessageDAO() {
		return (OutgoingMessageDAO) instantiateDAO(OutgoingMessageDAOHibernate.class);
	}
	
	

}
