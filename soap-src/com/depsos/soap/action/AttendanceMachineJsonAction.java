package com.depsos.soap.action;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.criterion.Restrictions;

import com.depsos.hr.model.AttendanceMachine;
import com.depsos.hr.model.dao.HrDAOFactory;
import com.depsos.hr.model.dao.HrDAOFactoryHibernate;
import com.depsos.soap.other.ResponseCode;
import com.depsos.soap.other.ResponseResult;
import com.mpe.basic.model.dao.BasicDAOFactory;
import com.mpe.basic.model.dao.BasicDAOFactoryHibernate;
import com.mpe.common.action.BaseAction;
import com.mpe.common.util.CommonUtil;

public class AttendanceMachineJsonAction extends BaseAction implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	HrDAOFactory hrDAOFactory = HrDAOFactory.instance(HrDAOFactoryHibernate.class);
	BasicDAOFactory basicDAOFactory = BasicDAOFactory.instance(BasicDAOFactoryHibernate.class);
	
	String machineId;
	String pin;
	String attendanceDateTime;
	int verified;
	int status;
	int workCode;
	
	ResponseResult responseResult;
	
	HttpServletRequest request;
	
	public String ping() {
		try {
			AttendanceMachine attendanceMachine = hrDAOFactory.getAttendanceMachineDAO().findByCriteria(Restrictions.eq("machineId", machineId));
			if (attendanceMachine!=null) {
				StringBuffer sb = request.getRequestURL();
				attendanceMachine.setLastMessage(sb.toString());
				attendanceMachine.setLastResponse(new Date());
				hrDAOFactory.getAttendanceMachineDAO().update(attendanceMachine);
				ResponseCode responseCode = new ResponseCode("00", "Found", "Attendance machine found");
	           	responseResult = new ResponseResult((Long)attendanceMachine.getAttendanceMachineId(), responseCode);
			} else {
				ResponseCode responseCode = new ResponseCode("01", "Not Found", "Attendance machine not found");
	           	responseResult = new ResponseResult(null, responseCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ResponseCode responseCode = new ResponseCode("99", "Error", "There are error "+e.getMessage());
           	responseResult = new ResponseResult(null, responseCode);
		}
		return "pingJsonSuccess";
	}
	
	
	public String restart() {
		try {
			
			AttendanceMachine attendanceMachine = hrDAOFactory.getAttendanceMachineDAO().findByCriteria(Restrictions.eq("machineId", machineId));
			if (attendanceMachine!=null) {
				if (attendanceMachine.isRestart()) {
					//
					attendanceMachine.setRestart(false);
					hrDAOFactory.getAttendanceMachineDAO().update(attendanceMachine);
					//
					ResponseCode responseCode = new ResponseCode("00", "Restart", "Restart attendance machine");
		           	responseResult = new ResponseResult((Long)attendanceMachine.getAttendanceMachineId(), responseCode);
					
				} else {
					ResponseCode responseCode = new ResponseCode("01", "No Restart", "No Restart attendance machine");
		           	responseResult = new ResponseResult((Long)attendanceMachine.getAttendanceMachineId(), responseCode);
				}
			} else {
				ResponseCode responseCode = new ResponseCode("01", "No Attendance Machine", "Attendance machine ID "+machineId+" not found");
	           	responseResult = new ResponseResult(null, responseCode);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			ResponseCode responseCode = new ResponseCode("99", "Error", "There are error "+e.getMessage());
           	responseResult = new ResponseResult(null, responseCode);
		}
		return "restartJsonSuccess";
	}

	@Override
	public String list() {
		return null;
	}

	@Override
	public String partialList() {
		return null;
	}

	@Override
	public String add() {
		return null;
	}

	@Override
	public String save() {
		try {
			
			//Calendar c = DateUtils.truncate(CommonUtil.getCalendarFromString(attendanceDateTime, "yyyyMMddHHmmss"), Calendar.DATE);
			Calendar c = CommonUtil.getCalendarFromString(attendanceDateTime.substring(0, 8), "yyyyMMdd");
			
			//logger.info(" c >> "+c);
			// check holiday ???
			boolean b = basicDAOFactory.getWorkOffDayDAO().isWorkOnHoliday(c.getTime());
			
			// get emp-ID by PIN & attendance-machine
			Long employeeId = hrDAOFactory.getEmployeeDAO().findEmployeeIdByAttendanceId(pin, machineId);
			if (employeeId!=null) { 
				Time startTime = CommonUtil.getTimeFromString(attendanceDateTime.substring(8, 12), "HHmm");
				hrDAOFactory.getEmployeeAttendanceDAO().insertAttendance(employeeId, c.getTime(), startTime, null, false, "", b, false);				
				
				ResponseCode responseCode = new ResponseCode("00", "Success", "Employee attendance  with pin "+pin+" and date "+attendanceDateTime+" success");
	           	responseResult = new ResponseResult(null, responseCode);
				
			} else {
				ResponseCode responseCode = new ResponseCode("01", "Not Found", "Employee with pin "+pin+" and machineID "+machineId+" not found!");
				responseResult = new ResponseResult(null, responseCode);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			ResponseCode responseCode = new ResponseCode("99", "Fail", e.getMessage());
			responseResult = new ResponseResult(null, responseCode);
		}
		return "saveJsonSuccess";
	}

	@Override
	public String edit() {
		return null;
	}

	@Override
	public String update() {
		return null;
	}

	@Override
	public String detail() {
		return null;
	}

	@Override
	public String delete() {
		return null;
	}

	@Override
	public String cancel() {
		return null;
	}


	public String getMachineId() {
		return machineId;
	}


	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}


	public String getPin() {
		return pin;
	}


	public void setPin(String pin) {
		this.pin = pin;
	}


	public String getAttendanceDateTime() {
		return attendanceDateTime;
	}


	public void setAttendanceDateTime(String attendanceDateTime) {
		this.attendanceDateTime = attendanceDateTime;
	}


	public int getVerified() {
		return verified;
	}


	public void setVerified(int verified) {
		this.verified = verified;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public int getWorkCode() {
		return workCode;
	}


	public void setWorkCode(int workCode) {
		this.workCode = workCode;
	}


	public ResponseResult getResponseResult() {
		return responseResult;
	}


	public void setResponseResult(ResponseResult responseResult) {
		this.responseResult = responseResult;
	}


	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
		
	}
	
	

}
