package com.mpe.basic.model.dao;

import com.mpe.basic.model.UserSecurityQuestion;
import com.mpe.common.dao.GenericDAO;

/**
 * @author Agung Hadiwaluyo
 *
 */
public interface UserSecurityQuestionDAO extends GenericDAO<UserSecurityQuestion, Long> {
	
	public boolean matchAnswer(long userId, String answer) throws Exception;

}
