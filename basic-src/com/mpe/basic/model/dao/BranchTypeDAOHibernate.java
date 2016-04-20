package com.mpe.basic.model.dao;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

import com.mpe.basic.model.BranchType;
import com.mpe.basic.model.other.BranchTypeList;
import com.mpe.common.dao.GenericHibernateDAO;
import com.mpe.common.model.ComboBoxModel;
import com.mpe.common.util.PartialList;

/**
 * @author Agung Hadiwaluyo
 *
 */
public class BranchTypeDAOHibernate extends GenericHibernateDAO<BranchType, Long> implements BranchTypeDAO {

	@SuppressWarnings("unchecked")
	@Override
	public PartialList<BranchTypeList> findAll(int start, int count)
			throws Exception {
		Session session = getSessionAnnotated();
		PartialList<BranchTypeList> partialList = null;
		try {
			String sqlTotal = "" +
					"select count(*) as A " +
					"from branch_type bt " +
					"left join branch_type btp on bt.parent_id=btp.branch_type_id " +
					"where 1=1 " + " " +
					"";
			
			SQLQuery queryTotal = session.createSQLQuery(sqlTotal).addScalar("A", LongType.INSTANCE);
			long total = ((Long)queryTotal.uniqueResult()).longValue();
			
			String sql = "" +
					"select " +
					"bt.branch_type_id as A, " +
					"btp.name as B, " +
					"bt.name as C " +
					"from branch_type bt " +
					"left join branch_type btp on bt.parent_id=btp.branch_type_id " +
					"where 1=1 " + " order by bt.parent_id " + 
					"";
			
			SQLQuery query = session.createSQLQuery(sql)
				.addScalar("A", LongType.INSTANCE)
				.addScalar("B", StringType.INSTANCE) // 1
				.addScalar("C", StringType.INSTANCE)
				; // 
			
			List<Object[]> list = query.setFirstResult(start).setMaxResults(count).list();
			
			List<BranchTypeList> branchTypeLists = new LinkedList<BranchTypeList>();
			for (Object[] objects : list) {
				BranchTypeList model = new BranchTypeList();
				model.setBranchTypeId((Long)objects[0]);
				model.setParent((String)objects[1]);
				model.setName((String)objects[2]);
				branchTypeLists.add(model);
			}
			
			partialList = new PartialList<BranchTypeList>(branchTypeLists, total);
			
		} catch (Exception e) {
			throw e;
		} finally {
			if (session!=null) session.close(); 
		}
		return partialList;
	}

	@Override
	public List<ComboBoxModel> comboBoxList(String orderBy, String ascDesc,
			Long parentId, String name) throws Exception {
		Session session = getSessionAnnotated();
		List<ComboBoxModel> models = new LinkedList<ComboBoxModel>();
		try {
			if (orderBy==null || (orderBy!=null && orderBy.length()==0)) orderBy = " b.name ";
			String string  = "";
			if (parentId!=null && parentId.longValue()>0) string = string + " and parent_id = "+parentId+" ";
			if (name!=null && name.length()>0) string = string + " and name like :name ";
			String sql = "" +
					"select " +
					"branch_type_id as key," +
					"name as name " +
					"from branch_type " +
					"where 1=1 " + string + " order by " + orderBy + " " + ascDesc + " " + 
					"";
			
			SQLQuery query = session.createSQLQuery(sql)
					.addScalar("key", LongType.INSTANCE)
					.addScalar("name", StringType.INSTANCE);
			if (name!=null && name.length()>0) query.setString("name", "%"+name+"%");
			models = query.setResultTransformer(Transformers.aliasToBean(ComboBoxModel.class)).list();
			
		} catch (Exception e) {
			throw e;
		} finally {
			if (session!=null) session.close(); 
		}
		return models;
	}

	@Override
	public Long previousBranchType(long branchTypeId) throws Exception {
		Session session = getSessionAnnotated();
		try {
			String sql = "" +
					"select parent_id as A " +
					"from branch_type " +
					"where branch_type_id = " + branchTypeId + " " +
					"";
			return (Long)session.createSQLQuery(sql).addScalar("A", LongType.INSTANCE).uniqueResult();
			
		} catch (Exception e) {
			throw e;
		} finally {
			if (session!=null) session.close();
		}
	}
	
}
