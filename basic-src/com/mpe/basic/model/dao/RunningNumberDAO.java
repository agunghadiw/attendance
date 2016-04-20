package com.mpe.basic.model.dao;

import java.util.Date;

import com.mpe.basic.model.RunningNumber;
import com.mpe.common.dao.GenericDAO;


/**
 * @author Agung Hadiwaluyo
 *
 */
public interface RunningNumberDAO extends GenericDAO<RunningNumber, Long> {
	
	String getUniqueNumber(Date date, boolean yearly) throws Exception;
	String getUniqueRunningNumber(String runningNumberType, long organizationId, Date date, boolean yearly) throws Exception;
	String getUniqueRunningNumber(long runningNumberId, Date date, boolean yearly) throws Exception;
	String getUniqueRunningNumber(long runningNumberTypeId, long organizationId, Date date, boolean yearly) throws Exception;

}
