package com.mpe.message.model.dao;
/**
 * @author Agung Hadiwaluyo
 *
 */
public abstract class MessageDAOFactory {

	/**
	* Factory method for instantiation of concrete factories.
	*/
	@SuppressWarnings("rawtypes")
	public static MessageDAOFactory instance(Class factory) {
		try {
			return (MessageDAOFactory)factory.newInstance();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("Couldn't create DAOFactory: " + factory);
		}
	}
	
	// Add your DAO interfaces here
	public abstract IncomingMessageDAO getIncomingMessageDAO();
	public abstract OutgoingEmailDAO getOutgoingEmailDAO();
	public abstract OutgoingMessageDAO getOutgoingMessageDAO();
	
	
}
