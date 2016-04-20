 package com.mpe.basic.model.dao;
/**
 * @author Agung Hadiwaluyo
 *
 */
public abstract class BasicDAOFactory {

	/**
	* Factory method for instantiation of concrete factories.
	*/
	@SuppressWarnings("rawtypes")
	public static BasicDAOFactory instance(Class factory) {
		try {
			return (BasicDAOFactory)factory.newInstance();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("Couldn't create BasicDAOFactory: " + factory);
		}
	}
	
	// Add your DAO interfaces here
	public abstract ApplicationSetupDAO getApplicationSetupDAO();
	public abstract UserDAO getUserDAO();
	public abstract LookupDAO getLookupDAO();
	public abstract PermissionDAO getPermissionDAO();
	public abstract RoleDAO getRoleDAO();
	public abstract StatusDAO getStatusDAO();
	public abstract BranchDAO getBranchDAO();
	public abstract BranchClassDAO getBranchClassDAO();
	public abstract BranchTypeDAO getBranchTypeDAO();
	public abstract OrganizationDAO getOrganizationDAO();
	public abstract RunningNumberDAO getRunningNumberDAO();
	public abstract UserActivityDAO getUserActivityDAO();
	public abstract UserDelegationDAO getUserDelegationDAO();
	public abstract UserSecurityQuestionDAO getUserSecurityQuestionDAO();
	public abstract UserPasswordHistoryDAO getUserPasswordHistoryDAO();
	public abstract WorkOffDayDAO getWorkOffDayDAO();
	public abstract RegisteredDeviceDAO getRegisteredDeviceDAO();
	public abstract LocationDAO getLocationDAO();
	public abstract UserSecugenDAO getUserSecugenDAO();
	
}
