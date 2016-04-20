package com.mpe.basic.model.dao;


import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

import com.mpe.basic.model.ApplicationSetup;
import com.mpe.basic.model.User;
import com.mpe.common.model.ComboBoxModel;
import com.mpe.common.util.CommonUtil;
import com.mpe.common.util.PartialList;
import com.mpe.common.dao.GenericHibernateDAO;
/**
 * @author Agung Hadiwaluyo
 *
 */
public class UserDAOHibernate extends GenericHibernateDAO<User, Long> implements UserDAO {
	
	public Log logger = LogFactory.getFactory().getInstance(this.getClass());

	@Override
	public User findByUserName(String userName, String userPass) throws Exception {
		Session session = getSessionAnnotated();
		Transaction transaction = null;
		User user = null;
		try {
			transaction = session.beginTransaction();
			user = (User)session.createCriteria(User.class)
				.add(Restrictions.eq("userName", userName))
				//.setFetchMode("roles", FetchMode.JOIN)
				.setFetchMode("organization", FetchMode.JOIN)
				//.setFetchMode("customer", FetchMode.JOIN)
				.createCriteria("roles","role")
				//.setCacheable(true) => trouble if error login catchable!!!
				.setFetchMode("permissions", FetchMode.JOIN)
				.setCacheRegion("com.mpe.basic.model.View")
				.uniqueResult();
			if (user==null) throw new Exception("Username not found or wrong username!");
			if (user!=null) {
				if (!user.isActive()) throw new Exception("User inactive!");
				else  {
					if (!CommonUtil.digest(userPass).equals(user.getUserPass())) throw new Exception("Wrong password!");
				}
			}
			transaction.commit();
		} catch(Exception exception) {
			if (transaction!=null) transaction.rollback();
			throw exception;
		} finally {
			if (session!=null) session.close();
		}
		return user;
	}

	@Override
	public int getTotalUser() throws Exception {
		Session session = getSessionAnnotated();
		int i = 0;
		try {
			String sql = "" +
					"select count(*) as A from users where is_active = 'T'" +
					"";
			i = ((Integer)session.createSQLQuery(sql).addScalar("A", IntegerType.INSTANCE).setMaxResults(1).uniqueResult()).intValue();
		} catch (Exception e) {
			throw e;
		} finally {
			if (session!=null) session.close();
		}
		return i;
	}

	@SuppressWarnings("unchecked")
	public PartialList<User> findByCriteria(int start, int count, Order order, Criterion... criterion) throws Exception {
		Session session = getSessionAnnotated();
		Transaction transaction = null;
		PartialList<User> partialList = null;
		try {
			transaction = session.beginTransaction();
			// total
			Criteria criteria = session.createCriteria(getPersistentClass());
			criteria.setFetchMode("organization", FetchMode.JOIN).setFetchMode("customer", FetchMode.JOIN);			
			for (Criterion c : criterion) {
				criteria.add(c);
			}
			criteria.setProjection(Projections.rowCount());
			long total = ((Long)criteria.list().iterator().next()).longValue();
			// partial data
			criteria = session.createCriteria(getPersistentClass());
			//criteria.setCacheable(true);
			criteria.setFetchMode("organization", FetchMode.JOIN)
				.setFetchMode("customer", FetchMode.JOIN);
			for (Criterion c : criterion) {
				criteria.add(c);
			}
			if (order!=null) criteria.addOrder(order);
			criteria.setFirstResult(start);
			criteria.setMaxResults(count);
			partialList = new PartialList<User>(criteria.list(), total);
			transaction.commit();
		} catch(Exception exception){
			if (transaction!=null)transaction.rollback();
			throw exception;
		} finally {
			if (session!=null) session.close();
		}
		return partialList;
	}

	@Override
	public void updateLastLogin(long userId) throws Exception {
		Session session = getSessionAnnotated();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			String sql = "" +
					"update users set last_login_date = :A where user_id = " + userId + " " +
					"";
			session.createSQLQuery(sql)
				.setTimestamp("A", new Date())
				.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			if (transaction!=null)transaction.rollback();
			throw e;
		} finally {
			if (session!=null) session.close();
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ComboBoxModel> findByOrganizationIdAndOthers(Long organizationId, Set<Long> userIds)
			throws Exception {
		Session session = getSessionAnnotated(); 
		List<ComboBoxModel> userComboBoxs = new LinkedList<ComboBoxModel>();
		try {
			String sql = "" +
					"select " +
					"user_id as A, " +
					"user_name as B " +
					"from users " +
					"where 1=1 " +
					(organizationId!=null?(" and organization_id="+organizationId.longValue()):(" and organization_id is null ")) +
					((userIds!=null && userIds.size()>0)?(" and user_id IN (:userIds) "):("")) +
					" order by user_name asc " +
					"";
			SQLQuery query = session.createSQLQuery(sql)
				.addScalar("A", LongType.INSTANCE)
				.addScalar("B", StringType.INSTANCE);
			
			if (userIds!=null && userIds.size()>0)query.setParameterList("userIds", userIds);
			
			List<Object[]> list = query.list();
			
			for (Object[] objects : list) {
				ComboBoxModel userComboBox = new ComboBoxModel();
				userComboBox.setId(((Long)objects[0]).longValue());
				userComboBox.setName((String)objects[1]);
				userComboBoxs.add(userComboBox);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session!=null) session.close();
		}
		return userComboBoxs;
	}

	@Override
	public String getUserName(long userId) throws Exception {
		Session session = getSessionAnnotated();
		try {
			String sql = "" +
					"select " +
					"user_name as A " +
					"from users " +
					"where user_id = " + userId+" " +
					"";
			return (String)session.createSQLQuery(sql).addScalar("A", StringType.INSTANCE).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session!=null) session.close();
		}
		return null;
	}

/*	@Override
	public void deleteByCustomerId(long customerId) throws Exception {
		Session session = getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			String sql1 = "" +
					"delete from user_role " +
					"where user_id IN (select user_id from customer_contact where customer_id = " + customerId+") " +
					"";
			session.createSQLQuery(sql1).executeUpdate();
			String sql2 = "" +
					"delete from users " +
					"where user_id IN (select user_id from customer_contact where customer_id = " + customerId+") " +
					"";
			session.createSQLQuery(sql2).executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			if (transaction!=null)transaction.rollback();
			e.printStackTrace();
		} finally {
			if (session!=null) session.close();
		}
		
	}*/
	
	@Override
	public void updateActiveByCustomerId(long customerId, boolean active) throws Exception {
		Session session = getSessionAnnotated();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			String sql2 = "" +
					"update users set is_active = " +(active?"'T'":"'F'") + " " +
					"where user_id IN (select user_id from customer_contact where customer_id = " + customerId+") " +
					"";
			session.createSQLQuery(sql2).executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			if (transaction!=null)transaction.rollback();
			e.printStackTrace();
		} finally {
			if (session!=null) session.close();
		}
		
	}
	
/*	@Override
	public void deleteByVendorId(long vendorId) throws Exception {
		Session session = getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			String sql1 = "" +
					"delete from user_role " +
					"where user_id IN (select user_id from vendor_contact where vendor_id = " + vendorId+") " +
					"";
			session.createSQLQuery(sql1).executeUpdate();
			String sql2 = "" +
					"delete from users " +
					"where user_id IN (select user_id from vendor_contact where vendor_id = " + vendorId+") " +
					"";
			session.createSQLQuery(sql2).executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			if (transaction!=null)transaction.rollback();
			e.printStackTrace();
		} finally {
			if (session!=null) session.close();
		}
		
	}*/
	
	@Override
	public void updateActiveByVendorId(long vendorId, boolean active) throws Exception {
		Session session = getSessionAnnotated();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			String sql2 = "" +
					"update users set is_active = " + (active?"'T'":"'F'") + " " +
					"where user_id IN (select user_id from vendor_contact where vendor_id = " + vendorId+") " +
					"";
			session.createSQLQuery(sql2).executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			if (transaction!=null)transaction.rollback();
			e.printStackTrace();
		} finally {
			if (session!=null) session.close();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ComboBoxModel> findByUserGroupIdBusinessUnitId(long userGroupId, long businessUnitId) throws Exception {
		Session session = getSessionAnnotated();
		List<ComboBoxModel> userComboBoxs = new LinkedList<ComboBoxModel>();
		try {
			String sql = "" +
					"select " +
					"u.user_id as A, " +
					"u.user_name as B " +
					"from users u " +
					"join user_role ur on ur.user_id=u.user_id " +
					"join role r on r.role_id=ur.role_id " +
					"where u.is_active = 'T' " +
					(userGroupId>0?(" and user_group_id="+userGroupId):("")) +
					(businessUnitId>0?(" and business_unit_id="+businessUnitId):("")) +
					" order by user_name asc " +
					"";
			List<Object[]> list = session.createSQLQuery(sql)
				.addScalar("A", LongType.INSTANCE)
				.addScalar("B", StringType.INSTANCE)
				.list();
			
			for (Object[] objects : list) {
				ComboBoxModel userComboBox = new ComboBoxModel();
				userComboBox.setId(((Long)objects[0]).longValue());
				userComboBox.setName((String)objects[1]);
				userComboBoxs.add(userComboBox);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session!=null) session.close();
		}
		return userComboBoxs;
	}

	@Override
	public String getEmail(long userId, Session session) throws Exception {
		try {
			String sql = "select email as A from users where user_id = "+userId+" ";
			return (String)session.createSQLQuery(sql).addScalar("A", StringType.INSTANCE).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		//return null;
	}
	
	@Override
	public String getEmail(long userId) throws Exception {
		Session session = getSessionAnnotated();
		try {
			String sql = "select email as A from users where user_id = "+userId+" ";
			return (String)session.createSQLQuery(sql).addScalar("A", StringType.INSTANCE).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (session!=null) session.close();
		}
		//return null;
	}
	
	@Override
	public User findUserAndRoleById(long userId) throws Exception {
		Session session = getSessionAnnotated();
		Transaction transaction = null;
		User user = null;
		try {
			transaction = session.beginTransaction();
			user = (User)session.createCriteria(User.class)
				.add(Restrictions.eq("userId", new Long(userId)))
				//.setFetchMode("roles", FetchMode.JOIN)
				.setFetchMode("organization", FetchMode.JOIN)
				//.setFetchMode("customer", FetchMode.JOIN)
				.createCriteria("roles","role")
				//.setCacheable(true) => trouble if error login catchable!!!
				.setFetchMode("permissions", FetchMode.JOIN)
				.setCacheRegion("com.mpe.basic.model.Permission")
				.uniqueResult();
			transaction.commit();
		} catch(Exception exception) {
			if (transaction!=null) transaction.rollback();
			throw exception;
		} finally {
			if (session!=null) session.close();
		}
		return user;
	}

	/* (non-Javadoc)
	 * @see com.mpe.basic.model.dao.UserDAO#duplicateUserName(java.lang.String)
	 */
	@Override
	public boolean duplicateUserName(String userName) throws Exception {
		Session session = getSessionAnnotated();
		boolean b = false;
		try {
			String sql = "" +
					"select count(*) as A " +
					"from users " +
					"where user_name = '" + userName+"' " +
					"";
			if (((Integer)session.createSQLQuery(sql).addScalar("A", IntegerType.INSTANCE).uniqueResult()).intValue()>0) b = true; 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session!=null)session.close();
		}
		return b;
	}

	/* (non-Javadoc)
	 * @see com.mpe.basic.model.dao.UserDAO#findByUserNameActive(java.lang.String, boolean)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ComboBoxModel> findByUserNameActive(String userName,
			boolean active) throws Exception {
		Session session = getSessionAnnotated();
		List<ComboBoxModel> userComboBoxs = new LinkedList<ComboBoxModel>();
		try {
			String string = "";
			if (userName!=null && userName.length()>0) string = string + " and user_name like '%"+userName+"%' ";
			String sql = "" +
					"select " +
					"user_id as A, " +
					"user_name as B " +
					"from users " +
					"where is_active = " + (active?"'T'":"'F'") + " " + string + " " +
					" order by user_name asc " +
					"";
			SQLQuery query = session.createSQLQuery(sql)
				.addScalar("A", LongType.INSTANCE)
				.addScalar("B", StringType.INSTANCE);
			
			List<Object[]> list = query.list();
			
			for (Object[] objects : list) {
				ComboBoxModel userComboBox = new ComboBoxModel();
				userComboBox.setId(((Long)objects[0]).longValue());
				userComboBox.setName((String)objects[1]);
				userComboBoxs.add(userComboBox);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (session!=null) session.close();
		}
		return userComboBoxs;
	}

	@Override
	public List<ComboBoxModel> findBySameBranchAndPositionWithCurrentUser(
			long currentUserId, Long positionId, Long branchId, Long organizationHierarchyId) throws Exception {
		Session session = getSessionAnnotated();
		List<ComboBoxModel>  comboBoxModels = new LinkedList<ComboBoxModel>();
		String string1 = "", string2 = "", string3 = "";
		if (branchId!=null) string1 = string1 + " and e.branch_id = "+branchId+" ";
		if (organizationHierarchyId!=null) string2 = string2 + " and e.organization_hierarchy_id = "+organizationHierarchyId+" ";
		if (positionId!=null) string3 = string3 + " and e.position_id = "+positionId+" ";
		try {
			
			String sql = "" +
					"select " +
					"u.user_id as key, " +
					"u.user_name || ' - ' || e.full_name as name " +
					"from users u " +
					"join employee e on u.user_id=e.user_id " +
					"where e.resign_date is null " +
					//"e.branch_id = (select branch_id from employee where user_id = "+currentUserId+") " +
					//(positionId!=null?(" and e.position_id = "+positionId+" "):("")) +
					"";
			
			comboBoxModels = session.createSQLQuery(sql+string1+string2+string3).addScalar("key", LongType.INSTANCE).addScalar("name", StringType.INSTANCE).setResultTransformer(Transformers.aliasToBean(ComboBoxModel.class)).list();
			//if (comboBoxModels==null || (comboBoxModels!=null && comboBoxModels.size()==0)) comboBoxModels = session.createSQLQuery(sql+string1+string2).addScalar("key", LongType.INSTANCE).addScalar("name", StringType.INSTANCE).setResultTransformer(Transformers.aliasToBean(ComboBoxModel.class)).list();
			if (comboBoxModels==null || (comboBoxModels!=null && comboBoxModels.size()==0)) comboBoxModels = session.createSQLQuery(sql+string3).addScalar("key", LongType.INSTANCE).addScalar("name", StringType.INSTANCE).setResultTransformer(Transformers.aliasToBean(ComboBoxModel.class)).list();
			//if (comboBoxModels==null || (comboBoxModels!=null && comboBoxModels.size()==0)) comboBoxModels = session.createSQLQuery(sql).addScalar("key", LongType.INSTANCE).addScalar("name", StringType.INSTANCE).setResultTransformer(Transformers.aliasToBean(ComboBoxModel.class)).list();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session!=null) session.close();
		}
		return comboBoxModels;
	}

	@Override
	public String[] getObjectByUserNameAndPassword(String userName,
			String password) throws Exception {
		Session session = getSessionAnnotated();
		String[] strings = new String[3];
		try {
			
			String sql = "" +
					"select " +
					"u.user_name as A, " +
					"u.user_pass as B, " +
					"e.full_name as C, " +
					"e.employee_id as D " +
					"from employee e " +
					"join users u on e.user_id=u.user_id " +
					"where u.is_active='T' and u.user_name like :userName " +
					"";
			
			Object[] objects = (Object[])session.createSQLQuery(sql)
					.addScalar("A", StringType.INSTANCE)
					.addScalar("B", StringType.INSTANCE)
					.addScalar("C", StringType.INSTANCE)
					.addScalar("D", LongType.INSTANCE)
					.setString("userName", userName)
					.setMaxResults(1)
					.uniqueResult();
			
			if (objects!=null) {				
				if (CommonUtil.digest(password).equals((String)objects[1])) {
					strings[0] = (String)objects[2];
					strings[1] = String.valueOf(((Long)objects[3]).longValue());
					//strings[2] = ""; 
					
					//logger.info(" s: "+strings[1] + " // " + strings[0]);
					
				} else {
					//strings[0] = "";
					strings[1] = "0";
					strings[2] = "Wrong username or password";
				}
			} else {
				//strings[0] = "";
				strings[1] = "0";
				strings[2] = "Employee not found or inactive";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session!=null) session.close();
		}
		return strings;
	}
	
	@Override
	public int getUserPassDuration(long userId) throws Exception {
		Session session = getSessionAnnotated();
		try {
			String sql = "" +
					"select min(a.default_user_pass_duration) as GBU from role a " +
					"join user_role b on b.role_id=a.role_id " +
					"where b.user_id =:userId " +
					"";
			
			Integer i = (Integer)session.createSQLQuery(sql).addScalar("GBU", IntegerType.INSTANCE)
					.setLong("userId", userId)
					.uniqueResult();
			
			if (i==null) {
				
				ApplicationSetup applicationSetup = (ApplicationSetup)session.createCriteria(ApplicationSetup.class)
						.add(Restrictions.ne("applicationSetupId", new Long(-1)))
						.uniqueResult();
				if (applicationSetup!=null && applicationSetup.getDefaultUserPassDuration()!=null) return applicationSetup.getDefaultUserPassDuration();				
				
			} else return i;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session!=null) session.close();
		}
		return 0;
	}
	
}
