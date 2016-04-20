package com.mpe.basic.model.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.mpe.basic.model.UserPasswordHistory;
import com.mpe.common.dao.GenericHibernateDAO;
import com.mpe.common.util.CommonUtil;

/**
 * @author Agung Hadiwaluyo
 *
 */
public class UserPasswordHistoryDAOHibernate extends GenericHibernateDAO<UserPasswordHistory, Long> implements UserPasswordHistoryDAO {

	@Override
	public boolean userPassDurationExpired(long userId, int userPassDuration, String currentEncriptUserPass) throws Exception {
		Session session = getSessionAnnotated();
		Transaction transaction = null;
		try {
			// no user-pass-duration
			if (userPassDuration==0) return false;
			//
			else {
				transaction = session.beginTransaction();
				// get-last-history
				UserPasswordHistory lastUserPassHistory = (UserPasswordHistory)session.createCriteria(UserPasswordHistory.class)
						.add(Restrictions.eq("userId", userId))
						.addOrder(Order.desc("userPassChangeDate")).setMaxResults(1).uniqueResult();
				if (lastUserPassHistory==null) {
					lastUserPassHistory = new UserPasswordHistory();
					lastUserPassHistory.setOldUserPass(currentEncriptUserPass);
					lastUserPassHistory.setUserId(userId);
					Calendar c1 = new GregorianCalendar();
					c1.set(Calendar.HOUR, 0);
					c1.set(Calendar.MINUTE, 0);
					c1.add(Calendar.DAY_OF_YEAR, -userPassDuration);
					lastUserPassHistory.setUserPassChangeDate(c1.getTime());
					session.save(lastUserPassHistory);
				}
				transaction.commit();
				
				// compare-date
				Calendar c2 = Calendar.getInstance();
		        c2.setTime(lastUserPassHistory.getUserPassChangeDate());
		        long t1 = c2.getTimeInMillis();
		        c2.setTime(new Date());
		        long diff = Math.abs(c2.getTimeInMillis() - t1);
		        final int ONE_DAY = 1000 * 60 * 60 * 24;
		        long d = diff / ONE_DAY;
		        if (d>=userPassDuration) return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction!=null) transaction.rollback();
		} finally {
			if (session!=null) session.close();
		}
		return false;
	}

	@Override
	public boolean saveValidChangeUserPass(long userId, Integer userPassHistory, String newUserPass) throws Exception {
		Session session = getSessionAnnotated();
		Transaction transaction = null;
		boolean validUserPass = true;
		try {
			// default value
			if (userPassHistory==null) userPassHistory = 1; 
			transaction = session.beginTransaction();
			Vector<Long> longs = new Vector<Long>();
			// check password depend on last password related with userPassHistory
			List<UserPasswordHistory> histories = session.createCriteria(UserPasswordHistory.class)
					.add(Restrictions.eq("userId", userId))
					.addOrder(Order.desc("userPassChangeDate"))
					.setMaxResults(userPassHistory).list();
			int i = userPassHistory-1;
			for (UserPasswordHistory history : histories) {
				if (CommonUtil.digest(newUserPass).equals(history.getOldUserPass())) {
					validUserPass = false;
				}
				if (i>0) longs.add(history.getUserPasswordHistoryId());
				i--;
			}
			if (validUserPass) {
				// delete last & out of range old password
				if (longs.size()>0) {
					String sql = "delete from user_password_history where user_id =:userId and user_password_history_id NOT IN (:z) ";
					session.createSQLQuery(sql).setLong("userId", userId).setParameterList("z", longs).executeUpdate();
				} else {
					// handle if history = 1
					String sql = "delete from user_password_history where user_id = :userId ";
					session.createSQLQuery(sql).setLong("userId", userId).executeUpdate();
				}
				
				// save new password
				UserPasswordHistory newHistory = new UserPasswordHistory();
				newHistory.setUserId(userId);
				newHistory.setOldUserPass(CommonUtil.digest(newUserPass));
				session.save(newHistory);
			}
			
			transaction.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction!=null) transaction.rollback();
		} finally {
			if (session!=null) session.close();
		}
		return validUserPass;
	}
	
	/*@Override
	public boolean saveValidChangeUserPass(Session session, long userId, int userPassHistory, String newUserPass) throws Exception {
		boolean validUserPass = true;
		try {
			Vector<Long> longs = new Vector<Long>();
			// check password depend on last password related with userPassHistory
			List<UserPasswordHistory> histories = session.createCriteria(UserPasswordHistory.class)
					.add(Restrictions.eq("userId", userId))
					.addOrder(Order.desc("userPassChangeDate"))
					.setMaxResults(userPassHistory).list();
			int i = userPassHistory-1;
			for (UserPasswordHistory history : histories) {
				if (CommonUtil.digest(newUserPass).equals(history.getOldUserPass())) {
					validUserPass = false;
				}
				if (i>0) longs.add(history.getUserPasswordHistoryId());
				i--;
			}
			if (validUserPass) {
				// delete last & out of range old password
				if (longs.size()>0) {
					String sql = "delete from user_password_history where user_id =:userId and user_password_history_id NOT IN (:z) ";
					session.createSQLQuery(sql).setLong("userId", userId).setParameterList("z", longs).executeUpdate();
				} else {
					// handle if history = 1
					String sql = "delete from user_password_history where user_id = :userId ";
					session.createSQLQuery(sql).setLong("userId", userId).executeUpdate();
				}
				
				// save new password
				UserPasswordHistory newHistory = new UserPasswordHistory();
				newHistory.setUserId(userId);
				newHistory.setOldUserPass(CommonUtil.digest(newUserPass));
				session.save(newHistory);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return validUserPass;
	}*/

	@Override
	public void deleteHistory(long userId) throws Exception {
		Session session = getSessionAnnotated();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			String sql = "delete from user_password_history where user_id = :userId ";
			session.createSQLQuery(sql).setLong("userId", userId).executeUpdate();
			transaction.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction!=null) transaction.rollback();
			throw e;
		} finally {
			if (session!=null) session.close();
		}
		
	}
	
}
