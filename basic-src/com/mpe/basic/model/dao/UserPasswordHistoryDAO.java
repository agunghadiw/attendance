package com.mpe.basic.model.dao;


import com.mpe.basic.model.UserPasswordHistory;
import com.mpe.common.dao.GenericDAO;
/**
 * @author Agung Hadiwaluyo
 *
 */
public interface UserPasswordHistoryDAO extends GenericDAO<UserPasswordHistory, Long> {

	public boolean userPassDurationExpired(long userId, int userPassDuration, String currentEncriptUserPass) throws Exception;
	// revisi : int userPassHistory => Integer userPassHistory!
	// kasus application-setup sudah terbuat terlebih dahulu tanpa history
	public boolean saveValidChangeUserPass(long userId, Integer userPassHistory, String newUserPass) throws Exception;
	//public boolean saveValidChangeUserPass(Session session, long userId, int userPassHistory, String newUserPass) throws Exception;
	
	public void deleteHistory(long userId) throws Exception;
	
	
}
