package com.mpe.basic.model.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import com.mpe.basic.model.User;
import com.mpe.common.dao.GenericDAO;
import com.mpe.common.model.ComboBoxModel;
import com.mpe.common.util.PartialList;
/**
 * @author Agung Hadiwaluyo
 *
 */
public interface UserDAO extends GenericDAO<User, Long> {
	
	public User findByUserName(String userName, String userPass) throws Exception;	
	public int getTotalUser() throws Exception;	
	public PartialList<User> findByCriteria(int start, int count, Order order, Criterion... criterion) throws Exception;
	public List<ComboBoxModel> findByOrganizationIdAndOthers(Long organizationId, Set<Long> userIds) throws Exception;
	public List<ComboBoxModel> findByUserGroupIdBusinessUnitId(long userGroupId, long businessUnitId) throws Exception;
	public List<ComboBoxModel> findByUserNameActive(String userName, boolean active) throws Exception;
	
	public List<ComboBoxModel> findBySameBranchAndPositionWithCurrentUser(long currentUserId, Long positionId,
			Long branchId, Long organizationHierarchyId) throws Exception;
	
	public void updateLastLogin(long userId) throws Exception;
	public String getUserName(long userId) throws Exception;
	//public void deleteByCustomerId(long customerId) throws Exception;
	public void updateActiveByCustomerId(long customerId, boolean active) throws Exception;
	//public void deleteByVendorId(long vendorId) throws Exception;
	public void updateActiveByVendorId(long vendorId, boolean active) throws Exception;
	public String getEmail(long userId, Session session) throws Exception;
	public String getEmail(long userId) throws Exception;
	public User findUserAndRoleById(long userId) throws Exception;
	public boolean duplicateUserName(String userName) throws Exception;
	
	public String[] getObjectByUserNameAndPassword(String userName, String password) throws Exception;
	
	public int getUserPassDuration(long userId) throws Exception;

}
