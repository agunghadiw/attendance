package com.mpe.message.model.dao;

import com.mpe.common.dao.GenericDAO;
import com.mpe.message.model.OutgoingMessage;
/**
 * @author Agung Hadiwaluyo
 *
 */
public interface OutgoingMessageDAO extends GenericDAO<OutgoingMessage, Long> {

	public OutgoingMessage findLastMessage(String receiver) throws Exception;
	
}
