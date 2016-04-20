package com.mpe.basic.model.dao;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

import com.mpe.basic.model.Branch;
import com.mpe.basic.model.BranchType;
import com.mpe.basic.model.other.BranchList;
import com.mpe.common.dao.GenericHibernateDAO;
import com.mpe.common.model.ComboBoxModel;
import com.mpe.common.model.TreeNode;
import com.mpe.common.util.PartialList;

/**
 * @author Agung Hadiwaluyo
 *
 */
public class BranchDAOHibernate extends GenericHibernateDAO<Branch, Long> implements BranchDAO {

	@Override
	public PartialList<BranchList> findAll(int start, int count, String orderBy, String ascDesc,
			Long branchClassId, Long branchTypeId, String code, String name,
			String city) throws Exception {
		Session session = getSessionAnnotated();
		PartialList<BranchList> partialList = null;
		try {
			if (orderBy==null || (orderBy!=null && orderBy.length()==0)) orderBy = " b.name ";
			String string = "";
			if (branchClassId!=null && branchClassId.longValue()>0)  string = string + " and b.branch_class_id = "+branchClassId.longValue()+" ";
			if (branchTypeId!=null && branchTypeId.longValue()>0)  string = string + " and b.branch_type_id = "+branchTypeId.longValue()+" ";
			if (code!=null && code.length()>0) string = string + " and b.code like :code ";
			if (name!=null && name.length()>0) string = string + " and b.name like :name ";
			if (city!=null && city.length()>0) string = string + " and c.city like :city ";
			
			String sqlTotal = "" +
					"select count(*) as A " +
					"from branch b " +
					"left join branch_type bt on bt.branch_type_id=b.branch_type_id " +
					"left join branch_class bc on bc.branch_class_id=b.branch_class_id " +
					"left join address a on a.address_id=b.address_id " +
					//"left join city c on c.city_id=a.city_id " +
					"where 1=1 " + string + " " +
					"";
			
			SQLQuery queryTotal = session.createSQLQuery(sqlTotal).addScalar("A", LongType.INSTANCE);
			if (code!=null && code.length()>0) queryTotal.setString("code", "%"+code+"%");
			if (name!=null && name.length()>0) queryTotal.setString("name", "%"+name+"%");
			if (city!=null && city.length()>0) queryTotal.setString("city", "%"+city+"%");
			
			long total = ((Long)queryTotal.uniqueResult()).longValue();
			
			String sql = "" +
					"select " +
					"b.branch_id as branchId, " +
					"b.code as code, " +
					"b.name as name, " +
					"bc.code as branchClass, " +
					"bt.name as branchType, " +
					"'' as city, " +
					"p.name as parent " +
					"from branch b " +
					"left join branch p on b.parent_id=p.branch_id " +
					"left join branch_type bt on bt.branch_type_id=b.branch_type_id " +
					"left join branch_class bc on bc.branch_class_id=b.branch_class_id " +
					"left join address a on a.address_id=b.address_id " +
					//"left join city c on c.city_id=a.city_id " +
					"where 1=1 " + string + " order by " + orderBy + " " + ascDesc + " " + 
					"";
			
			SQLQuery query = session.createSQLQuery(sql)
				.addScalar("branchId", LongType.INSTANCE)
				.addScalar("code", StringType.INSTANCE) // 1
				.addScalar("name", StringType.INSTANCE)
				.addScalar("branchClass", StringType.INSTANCE)
				.addScalar("branchType", StringType.INSTANCE)
				.addScalar("city", StringType.INSTANCE)
				.addScalar("parent", StringType.INSTANCE)
				; // 
			
			if (code!=null && code.length()>0) query.setString("code", "%"+code+"%");
			if (name!=null && name.length()>0) query.setString("name", "%"+name+"%");
			if (city!=null && city.length()>0) query.setString("city", "%"+city+"%");
			
			List<BranchList> branchLists = query
					.setResultTransformer(Transformers.aliasToBean(BranchList.class))
					.setFirstResult(start).setMaxResults(count).list();		
			
			partialList = new PartialList<BranchList>(branchLists, total);
			
		} catch (Exception e) {
			throw e;
		} finally {
			if (session!=null) session.close();
		}
		return partialList;
	}

	@Override
	public List<ComboBoxModel> comboBoxList(String orderBy, String ascDesc,
			Long branchClassId, Long branchTypeId, String code, String name,
			String city) throws Exception {
		Session session = getSessionAnnotated();
		List<ComboBoxModel> comboBoxModels = new LinkedList<ComboBoxModel>();
		if (orderBy==null || (orderBy!=null && orderBy.length()==0)) orderBy = " b.name ";
		String string = "";
		if (branchClassId!=null && branchClassId.longValue()>0)  string = string + " and b.branch_class_id = "+branchClassId.longValue()+" ";
		if (branchTypeId!=null && branchTypeId.longValue()>0)  string = string + " and b.branch_type_id = "+branchTypeId.longValue()+" ";
		if (code!=null && code.length()>0) string = string + " and b.code like :code ";
		if (name!=null && name.length()>0) string = string + " and b.name like :name ";
		if (city!=null && city.length()>0) string = string + " and c.city like :city ";
		try {
			String sql = "" +
					"select " +
					"b.branch_id as key, " +
					"b.name as name " +
					"from branch b " +
					"left join branch_type bt on bt.branch_type_id=b.branch_type_id " +
					"left join branch_class bc on bc.branch_class_id=b.branch_class_id " +
					"left join address a on a.address_id=b.address_id " +
					//"left join city c on c.city_id=a.city_id " +
					"where 1=1 " + string + " order by " + orderBy + " " + ascDesc + " " + 
					"";
			
			SQLQuery query = session.createSQLQuery(sql)
				.addScalar("A", LongType.INSTANCE)
				.addScalar("B", StringType.INSTANCE) // 1
				; // 
			
			if (code!=null && code.length()>0) query.setString("code", "%"+code+"%");
			if (name!=null && name.length()>0) query.setString("name", "%"+name+"%");
			if (city!=null && city.length()>0) query.setString("city", "%"+city+"%");
			
			/*List<Object[]> list = query.list();
			for (Object[] objects : list) {
				ComboBoxModel model = new ComboBoxModel();
				model.setKey((Long)objects[0]);
				model.setName((String)objects[1]);
				comboBoxModels.add(model);
			}*/
			comboBoxModels = session.createSQLQuery(sql)
					.addScalar("key", LongType.INSTANCE)
					.addScalar("name", StringType.INSTANCE)
					.setResultTransformer(Transformers.aliasToBean(ComboBoxModel.class))
					.list();
			
		} catch (Exception e) {
			throw e;
		} finally {
			if (session!=null) session.close();
		}
		return comboBoxModels;
	}

	@Override
	public List<TreeNode> showTree(long organizationId) throws Exception {
		List<TreeNode> list = new LinkedList<TreeNode>();
		Session session = getSessionAnnotated();
		try {
			List<Branch> branchs = session.createCriteria(Branch.class).setCacheable(true).add(Restrictions.isNull("parentId")).addOrder(Order.asc("parentId")).list();
			for (Branch branch : branchs) {
				TreeNode treeNode1 = new TreeNode();
				treeNode1.setName(branch.getName());
				BranchType branchType = (BranchType)session.createCriteria(BranchType.class).setCacheable(true).add(Restrictions.eq("branchTypeId", branch.getBranchTypeId())).uniqueResult();
				treeNode1.setDescription1(branchType!=null?branchType.getName():"");
				// child #1
				List<Branch> branchs2 = session.createCriteria(Branch.class).setCacheable(true).add(Restrictions.eq("parentId", branch.getBranchId())).addOrder(Order.asc("parentId")).list();
				for (Branch branch2 : branchs2) {
					TreeNode treeNode2 = new TreeNode();
					treeNode2.setName(branch2.getName());
					BranchType branchType2 = (BranchType)session.createCriteria(BranchType.class).setCacheable(true).add(Restrictions.eq("branchTypeId", branch2.getBranchTypeId())).uniqueResult();
					treeNode2.setDescription1(branchType2!=null?branchType2.getName():"");
					// child #2
					List<Branch> branchs3 = session.createCriteria(Branch.class).setCacheable(true).add(Restrictions.eq("parentId", branch2.getBranchId())).addOrder(Order.asc("parentId")).list();
					for (Branch branch3 : branchs3) {
						TreeNode treeNode3 = new TreeNode();
						treeNode3.setName(branch3.getName());
						BranchType branchType3 = (BranchType)session.createCriteria(BranchType.class).setCacheable(true).add(Restrictions.eq("branchTypeId", branch3.getBranchTypeId())).uniqueResult();
						treeNode3.setDescription1(branchType3!=null?branchType3.getName():"");
						// child #3
						List<Branch> branchs4 = session.createCriteria(Branch.class).setCacheable(true).add(Restrictions.eq("parentId", branch3.getBranchId())).addOrder(Order.asc("parentId")).list();
						for (Branch branch4 : branchs4) {
							TreeNode treeNode4 = new TreeNode();
							treeNode4.setName(branch3.getName());
							BranchType branchType4 = (BranchType)session.createCriteria(BranchType.class).setCacheable(true).add(Restrictions.eq("branchTypeId", branch4.getBranchTypeId())).uniqueResult();
							treeNode4.setDescription1(branchType4!=null?branchType4.getName():"");							
							treeNode3.getChilds().add(treeNode4);
						}
						treeNode2.getChilds().add(treeNode3);
					}
					treeNode1.getChilds().add(treeNode2);
				}
				list.add(treeNode1);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (session!=null) session.close();
		}
		return list;
	}

	@Override
	public void deleteAllBranch() throws Exception {
		Session session = getSessionAnnotated();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			String sql = "" +
					"delete from branch " +
					"";
			
			session.createSQLQuery(sql).executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			if (transaction!=null) transaction.rollback();
			throw e;
		} finally {
			if (session!=null) session.close();
		}
		
	}
	
}
