package com.mpe.basic.model.dao;


import org.hibernate.Session;
import org.hibernate.type.StringType;

import com.mpe.basic.model.UserSecurityQuestion;
import com.mpe.common.dao.GenericHibernateDAO;

/**
 * @author Agung Hadiwaluyo
 *
 */
public class UserSecurityQuestionDAOHibernate extends GenericHibernateDAO<UserSecurityQuestion, Long> implements UserSecurityQuestionDAO {

	@Override
	public boolean matchAnswer(long userId, String answer) throws Exception {
		Session session = getSessionAnnotated();
		boolean b = false;
		try {
			
			String sql = "" +
					"select answer as A from user_security_question " +
					"where user_id = :userId " +
					"";
			String string = (String)session.createSQLQuery(sql).addScalar("A", StringType.INSTANCE).setLong("userId", new Long(userId)).uniqueResult();
			if (string == null) b = true;
			else {
				if (answer!=null && string.equalsIgnoreCase(answer)) b = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session!=null) session.close();
		}
		return b;
	}
	
	
}
