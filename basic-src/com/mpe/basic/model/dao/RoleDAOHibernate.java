package com.mpe.basic.model.dao;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

import com.mpe.basic.model.Role;
import com.mpe.common.dao.GenericHibernateDAO;
import com.mpe.common.model.ComboBoxModel;

/**
 * @author Agung Hadiwaluyo
 *
 */
public class RoleDAOHibernate extends GenericHibernateDAO<Role, Long> implements RoleDAO {

	@Override
	public Role findByRoleName(String name) throws Exception {
		Session session = getSessionAnnotated();
		Transaction transaction = null;
		Role entity = null;
		try {
			transaction = session.beginTransaction();
			//entity = (T)session.get(getPersistentClass(), id, LockMode.UPGRADE);
			entity = (Role)session.createCriteria(getPersistentClass()).add(Restrictions.eq("roleName", name)).uniqueResult();
			transaction.commit();
		} catch(Exception exception){
			if (transaction!=null)transaction.rollback();
			throw exception;
		} finally {
			if (session!=null) session.close();
		}
		return entity;
	}

	/* (non-Javadoc)
	 * @see com.mpe.basic.model.dao.RoleDAO#findByName(java.lang.String)
	 */
	@Override
	public long findByName(String name) throws Exception {
		Session session = getSessionAnnotated();
		try {
			String sql = "" +
					"select role_id as A " +
					"from role " +
					"where role_name like '%"+name+"%' " +
					"" +
					"";
			return ((Long)session.createSQLQuery(sql).addScalar("A", LongType.INSTANCE).setMaxResults(1).uniqueResult()).longValue();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session!=null)session.close();
		}
		return 0;
	}

	@Override
	public List<ComboBoxModel> comboBoxList(String orderBy, String ascDesc, Long userGroupId) throws Exception {
		Session session = getSessionAnnotated();
		List<ComboBoxModel> models = new LinkedList<ComboBoxModel>();
		try {
			if (orderBy==null || (orderBy!=null && orderBy.length()==0)) orderBy = " role_name ";
			String string = "";
			if (userGroupId!=null && userGroupId.longValue()>0) string = string + " and user_group_id = "+userGroupId+" "; 
			String sql = "" +
					"select role_id as key, role_name as name " +
					"from role " +
					"where 1=1 " + string + " order by " + orderBy + " " + ascDesc + " " + 
					"";
			models = session.createSQLQuery(sql).addScalar("key", LongType.INSTANCE).addScalar("name", StringType.INSTANCE)
					.setResultTransformer(Transformers.aliasToBean(ComboBoxModel.class))
					.list();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session!=null)session.close();
		}
		return models;
	}
	
	

}
