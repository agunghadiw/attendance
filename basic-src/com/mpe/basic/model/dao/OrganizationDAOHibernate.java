package com.mpe.basic.model.dao;


import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

import com.mpe.basic.model.Organization;
import com.mpe.common.dao.GenericHibernateDAO;
import com.mpe.common.model.ComboBoxModel;

/**
 * @author Agung Hadiwaluyo
 *
 */
public class OrganizationDAOHibernate extends GenericHibernateDAO<Organization, Long> implements OrganizationDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<ComboBoxModel> list(long parentId)
			throws Exception {
		Session session = getSessionAnnotated();
		List<ComboBoxModel> boxModels = new LinkedList<ComboBoxModel>();
		try {
			String string = "";
			if (parentId>0) string = string + " and o.parent_id="+parentId+" ";
			//if (type!=null) string = string + " and o.organization_type = '"+type.name()+"' ";
			String sql = "" +
					"select " +
					"o.organization_id as A, " +
					"o.name as B, " +
					"p.name as C " +
					"from organization o " +
					"left join organization p on p.organization_id=o.parent_id " +
					"where 1=1 " + string + " " +
					"";
			List<Object[]> objects = session.createSQLQuery(sql)
				.addScalar("A", LongType.INSTANCE)
				.addScalar("B", StringType.INSTANCE)
				.addScalar("C", StringType.INSTANCE)
				.list();
			
			for (Object[] objects2 : objects) {
				ComboBoxModel model = new ComboBoxModel((Long)objects2[0], ((String)objects2[2]==null || ((String)objects2[2]!=null && ((String)objects2[2]).length()==0))?(String)objects2[1]:((String)objects2[2]+"::"+(String)objects2[1]) );
				boxModels.add(model);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session!=null)session.close();
		}
		return boxModels;
	}

	@Override
	public void updateSodDate(long organizationId, Date sodDate)
			throws Exception {
		Session session = getSessionAnnotated();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			
			String sql = ""
					+ "update organization_setup a "
					+ "set sod_date = :sodDate "
					+ "from organization b "
					+ "where b.organization_setup_id=a.organization_setup_id "
					+ "and b.organization_id= :organizationId "
					+ "" +
					"";
			session.createSQLQuery(sql).setLong("organizationId", organizationId)
				.setDate("sodDate", sodDate)
				.executeUpdate();
			//
			transaction.commit();
			
		} catch (Exception e) {
			if (transaction!=null) transaction.rollback();
			e.printStackTrace();
		} finally {
			if (session!=null)session.close();
		}
		
	}
	
	@Override
	public void updateSodDate(long organizationId, Date sodDate, Session session)
			throws Exception {
		try {
			
			String sql = ""
					+ "update organization_setup a "
					+ "set sod_date = :sodDate "
					+ "from organization b "
					+ "where b.organization_setup_id=a.organization_setup_id "
					+ "and b.organization_id= :organizationId "
					+ "" +
					"";
			session.createSQLQuery(sql).setLong("organizationId", organizationId)
				.setDate("sodDate", sodDate)
				.executeUpdate();
			//
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		
	}

	@Override
	public Date getSodDate(Long organizationId) throws Exception {
		Date date = null;
		Session session = null;
		try {
			session = getSessionAnnotated();
			String string = "";
			if (organizationId!=null && organizationId > 0) string = string + " and organization_id = :organizationId ";
			String sql = ""
					+ "select sod_date as A "
					+ "from organization_setup "
					+ "where 1=1 " + string + " "
					+ "";
			
			SQLQuery query = session.createSQLQuery(sql).addScalar("A", DateType.INSTANCE);
			if (organizationId!=null && organizationId > 0) query.setLong("organizationId", organizationId);
			date = (Date)query.uniqueResult();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (session!=null) session.close();
		}
		return date;
	}

	/* (non-Javadoc)
	 * @see com.mpe.basic.model.dao.OrganizationDAO#getDefaultOrganizationId()
	 
	@Override
	public long getDefaultOrganizationId() throws Exception {
		Session session = getSessionAnnotated();
		try {
			String sql = "" +
					"select organization_id as A " +
					"from organization " +
					"where parent_id is null " +
					"" +
					"";
			return ((Long)session.createSQLQuery(sql).addScalar("A", LongType.INSTANCE).uniqueResult()).longValue();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session!=null)session.close();
		}
		return 0;
	}*/
	
	
}
