package com.depsos.soap.action;

import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.apache.struts2.json.annotations.JSON;

import com.mpe.common.action.BaseAction;
import com.mpe.common.util.Constants;
import com.mpe.workflow.model.dao.WorkflowDAOFactory;
import com.mpe.workflow.model.dao.WorkflowDAOFactoryHibernate;
import com.mpe.workflow.model.helper.ProcessPopUpModel;
import com.opensymphony.xwork2.Preparable;
/**
 * @author Agung Hadiwaluyo
 *
 */
public class ProcessJsonAction extends BaseAction implements Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	WorkflowDAOFactory workflowDAOFactory = WorkflowDAOFactory.instance(WorkflowDAOFactoryHibernate.class);
	
	Long workflowId;
	Long processId;
	
	//List<Workflow> workflowList;
	List<ProcessPopUpModel> previousProcessList;
	
	@JSON(name="previousProcess")
	@SkipValidation
	public String previousProcess() {
		//if (previousProcessList!=null) logger.info(" SZ : "+previousProcessList.size());
		return "previousProcessSuccess";
	}

	public Long getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(Long workflowId) {
		this.workflowId = workflowId;
	}
	
	@Override
	public String cancel() {
		setMappedRequest(Constants.PARTIAL_LIST);
		return Constants.REDIRECT;
	}

	@Override
	public String add() {
		return null;
	}

	@Override
	public String delete() {
		return null;
	}

	@Override
	public String detail() {
		return null;
	}

	@Override
	public String edit() {
		return null;
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
	public String save() {
		return null;
	}

	@Override
	public String update() {
		return null;
	}

	@Override
	public void prepare() throws Exception {
		try {
			//if (workflowList==null) workflowList = workflowDAOFactory.getWorkflowDAO().findAll(null);
			if (workflowId!=null && workflowId.longValue()>0) {
					//logger.info("workId : "+workflowId.longValue());
					/*previousProcessList = workflowDAOFactory.getProcessDAO().findByCriteria(Order.asc("name"), 
							Restrictions.eq("workflowId", new Long(workflowId))
							);*/
				previousProcessList = workflowDAOFactory.getProcessDAO().findAll(workflowId, processId!=null?processId:0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<ProcessPopUpModel> getPreviousProcessList() {
		return previousProcessList;
	}

	public void setPreviousProcessList(List<ProcessPopUpModel> previousProcessList) {
		this.previousProcessList = previousProcessList;
	}

	public Long getProcessId() {
		return processId;
	}

	public void setProcessId(Long processId) {
		this.processId = processId;
	}
	
	

}
