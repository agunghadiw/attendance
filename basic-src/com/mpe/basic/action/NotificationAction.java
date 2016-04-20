package com.mpe.basic.action;

import java.util.List;

import com.depsos.hr.model.dao.HrDAOFactory;
import com.depsos.hr.model.dao.HrDAOFactoryHibernate;
import com.depsos.hr.model.other.AttendanceMachineList;
import com.mpe.basic.model.User;
import com.mpe.common.action.BaseAction;
import com.mpe.common.util.Constants;
import com.mpe.workflow.model.dao.WorkflowDAOFactory;
import com.mpe.workflow.model.dao.WorkflowDAOFactoryHibernate;
import com.mpe.workflow.model.helper.UserHomeTaskList;

public class NotificationAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	HrDAOFactory hrDAOFactory = HrDAOFactory.instance(HrDAOFactoryHibernate.class);
	WorkflowDAOFactory workflowDAOFactory = WorkflowDAOFactory.instance(WorkflowDAOFactoryHibernate.class);
	
	List<UserHomeTaskList> employeeTaskList;
	List<AttendanceMachineList> attendanceMachineLists;
	
	public String notification() {
		try {
			User user = (User)getSession().get(Constants.USER);
			attendanceMachineLists = hrDAOFactory.getAttendanceMachineDAO().dangerList(30);
			employeeTaskList = workflowDAOFactory.getProcessDAO().show(user, 0, "processDate", "asc");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
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
		return null;
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

	public List<UserHomeTaskList> getEmployeeTaskList() {
		return employeeTaskList;
	}

	public void setEmployeeTaskList(List<UserHomeTaskList> employeeTaskList) {
		this.employeeTaskList = employeeTaskList;
	}

	public List<AttendanceMachineList> getAttendanceMachineLists() {
		return attendanceMachineLists;
	}

	public void setAttendanceMachineLists(List<AttendanceMachineList> attendanceMachineLists) {
		this.attendanceMachineLists = attendanceMachineLists;
	}
	
	

}
