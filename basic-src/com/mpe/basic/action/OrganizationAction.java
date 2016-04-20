package com.mpe.basic.action;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.mpe.basic.model.Location;
import com.mpe.basic.model.Lookup;
import com.mpe.basic.model.Organization;
import com.mpe.basic.model.RunningNumber;
import com.mpe.basic.model.dao.BasicDAOFactory;
import com.mpe.basic.model.dao.BasicDAOFactoryHibernate;
import com.mpe.basic.model.other.LocationType;
import com.mpe.basic.model.other.LookupCategory;
import com.mpe.basic.model.other.RunningNumberPeriode;
import com.mpe.common.action.BaseAction;
import com.mpe.common.util.*;
import com.mpe.message.model.dao.MessageDAOFactory;
import com.mpe.message.model.dao.MessageDAOFactoryHibernate;
import com.mpe.upload.model.dao.UploadDAOFactory;
import com.mpe.upload.model.dao.UploadDAOFactoryHibernate;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
/**
 * @author Agung Hadiwaluyo
 *
 */
public class OrganizationAction extends BaseAction implements Preparable, ModelDriven<Organization> {
	
	private static final long serialVersionUID = 1L;
	
	BasicDAOFactory basicDAOFactory = BasicDAOFactory.instance(BasicDAOFactoryHibernate.class);
	MessageDAOFactory messageDAOFactory = MessageDAOFactory.instance(MessageDAOFactoryHibernate.class);
	UploadDAOFactory uploadDAOFactory = UploadDAOFactory.instance(UploadDAOFactoryHibernate.class);
	
	List<Organization> organizationList;
	Organization organization;
	long organizationId, parentId;
	String nameX="";//organizationTypeX="";
	List<Organization> parentList;
	//Vector<String> organizationTypeList = new Vector<String>();
	//List<OrganizationType> organizationTypes;
	
	//List<Currency> currencyList;
	List<Location> cities;
	Vector<String> runningNumberPeriodes = new Vector<String>();
	
	//Address address;
	//Contact contact;
	//OrganizationSetup organizationSetup;
	
	//
	File logoFile;
	String logoFileContentType;
	boolean removeLogoFile = false;
	
	// running number
	List<RunningNumber> runningNumberList = new LinkedList<RunningNumber>();
	long runningNumberId, runningNumberTypeId;
	List<Lookup> runningNumberTypeList;
	String number, prefix, suffix, subaction="";
	boolean branchSeparated = false;
	RunningNumberPeriode runningNumberPeriode;
	int selectedTab=0;
	
	@Override
	public String cancel() {
		setMappedRequest(Constants.PARTIAL_LIST);
		return Constants.REDIRECT;
	}
	
	@Override
	public String detail() {
		setReadOnly(true);
		return SUCCESS;
	}
	
	@Override
	public String add() {
		setPreviousPage(Constants.ADD);
		setMappedRequest(Constants.SAVE);
		if (getSubaction().equalsIgnoreCase("REMOVE")) {
			RunningNumber removeRunningNumber=null;
			Iterator<RunningNumber> iterator = organization.getRunningNumbers().iterator();
			while (iterator.hasNext()) {
				RunningNumber runningNumber = (RunningNumber)iterator.next();
				if (runningNumberId>0) {
					if (runningNumberId==runningNumber.getRunningNumberId()) {
						removeRunningNumber = runningNumber;
						break;
					}
				} else {
					if (runningNumberTypeId==runningNumber.getRunningNumberType().getLookupId()) {
						removeRunningNumber = runningNumber;
						break;
					}
				}
			}
			if (removeRunningNumber!=null) {
				organization.getRunningNumbers().remove(removeRunningNumber);
				getSession().put("organization", organization);
				runningNumberId = 0;
				runningNumberTypeId = 0;
			}
		}
		if (runningNumberTypeId>0) {
			Iterator<RunningNumber> iterator = organization.getRunningNumbers().iterator();
			while (iterator.hasNext()) {
				RunningNumber runningNumber = (RunningNumber)iterator.next();
				if (runningNumberId>0) {
					if (runningNumberId==runningNumber.getRunningNumberId()) {
						prefix = runningNumber.getPrefix();
						suffix = runningNumber.getSuffix();
						number = runningNumber.getStartNumber();
						branchSeparated = runningNumber.isBranchSeparated()?true:false;
						runningNumberTypeId = runningNumber.getRunningNumberType().getLookupId();
						runningNumberId = runningNumber.getRunningNumberId();
						runningNumberPeriode = runningNumber.getRunningNumberPeriode();
						break;
					}
				} else {
					if (runningNumberTypeId==runningNumber.getRunningNumberType().getLookupId()) {
						prefix = runningNumber.getPrefix();
						suffix = runningNumber.getSuffix();
						number = runningNumber.getStartNumber();
						branchSeparated = runningNumber.isBranchSeparated()?true:false;
						runningNumberTypeId = runningNumber.getRunningNumberType().getLookupId();
						runningNumberId = runningNumber.getRunningNumberId();
						runningNumberPeriode = runningNumber.getRunningNumberPeriode();
						break;
					}
				}
			}
		}
		return SUCCESS;
	}
	
	@Override
	public String save() {
		if (subaction.equalsIgnoreCase("CANCEL")) {
			getSession().remove("organization");
			setMappedRequest(Constants.PARTIAL_LIST);
			return Constants.REDIRECT;
		}
		try {
			/*if (parentId>0) {
				//Organization parent = basicDAOFactory.getOrganizationDAO().findById(parentId);
				//organization.setParent(parent.getName());
				organization.setParentId(new Long(parentId));
			} else {
				organization.setParentId(null);
			}*/
			if (logoFile!=null) {
				//UploadFile logo = organization.getLogo()!=null?organization.getLogo():new UploadFile();
				/*UploadFile logo = organization.getLogoId()!=null?uploadDAOFactory.getUploadFileDAO().findById(organization.getLogoId()):new UploadFile();
				logo.setDatabase(true);
				logo.setFileContent(FileUtils.readFileToByteArray(logoFile));
				logo.setFileContentType(logoFileContentType);
				logo.setFileSize(logoFile.length());
				logo.setFileName(logoFile.getName());
				if (logo.getUploadFileId()==0) uploadDAOFactory.getUploadFileDAO().save(logo);
				else uploadDAOFactory.getUploadFileDAO().update(logo);
				organization.setLogoId(logo.getUploadFileId());*/
			}
			if (removeLogoFile) {
				//if (organization.get) uploadDAOFactory.getUploadFileDAO().delete(id);
				organization.setLogoId(null);
			}
			if (subaction.equalsIgnoreCase("ADD")) {
				if (runningNumberId>0) {
					RunningNumber removeRunningNumber = null;
					Iterator<RunningNumber> iterator = organization.getRunningNumbers().iterator();
					while (iterator.hasNext()) {
						RunningNumber runningNumber2 = (RunningNumber)iterator.next();
						if (runningNumber2.getRunningNumberId()==runningNumberId) {
							removeRunningNumber = runningNumber2;
						}
					}
					if (removeRunningNumber!=null) {
						removeRunningNumber.setStartNumber(number);
						removeRunningNumber.setPrefix(prefix);
						Lookup runningNumberType = basicDAOFactory.getLookupDAO().findById(runningNumberTypeId);
						removeRunningNumber.setRunningNumberType(runningNumberType);
						removeRunningNumber.setSuffix(suffix);
						removeRunningNumber.setBranchSeparated(branchSeparated);
						removeRunningNumber.setRunningNumberPeriode(runningNumberPeriode);
						organization.getRunningNumbers().add(removeRunningNumber);
					}
				} else {
					Lookup runningNumberType = basicDAOFactory.getLookupDAO().findById(runningNumberTypeId);
					RunningNumber runningNumber = new RunningNumber(runningNumberType, prefix, suffix, number, organization, branchSeparated, runningNumberPeriode);
					RunningNumber removeRunningNumber = null;
					Iterator<RunningNumber> iterator = organization.getRunningNumbers().iterator();
					while (iterator.hasNext()) {
						RunningNumber runningNumber2 = (RunningNumber)iterator.next();
						if (runningNumber2.getRunningNumberType().getLookupId()==runningNumberTypeId) {
							removeRunningNumber = runningNumber2;
						}
					}
					if (removeRunningNumber!=null) {
						organization.getRunningNumbers().remove(removeRunningNumber);
					}
					organization.getRunningNumbers().add(runningNumber);
				}
				selectedTab = 2;
			}
			getSession().put("organization", organization);
			if (subaction.equalsIgnoreCase("SAVE_ALL")) {
				basicDAOFactory.getOrganizationDAO().save(organization);
				//
				getSession().remove("organization");
				setMappedRequest(Constants.PARTIAL_LIST);
				return Constants.REDIRECT;
			}
			
		} catch (Exception e) {
			addActionError(e.getMessage());
			return INPUT;
		}
		setMappedRequest(Constants.ADD);
		return Constants.REDIRECT;
	}
	
	@Override
	public String edit() {
		setPreviousPage(Constants.EDIT);
		setMappedRequest(Constants.UPDATE);
		if (getSubaction().equalsIgnoreCase("REMOVE")) {
			RunningNumber removeRunningNumber=null;
			Iterator<RunningNumber> iterator = organization.getRunningNumbers().iterator();
			while (iterator.hasNext()) {
				RunningNumber runningNumber = (RunningNumber)iterator.next();
				if (runningNumberId>0) {
					if (runningNumberId==runningNumber.getRunningNumberId()) {
						removeRunningNumber = runningNumber;
						break;
					}
				} else {
					if (runningNumberTypeId==runningNumber.getRunningNumberType().getLookupId()) {
						removeRunningNumber = runningNumber;
						break;
					}
				}
			}
			if (removeRunningNumber!=null) {
				organization.getRunningNumbers().remove(removeRunningNumber);
				getSession().put("organization", organization);
				runningNumberId = 0;
				runningNumberTypeId = 0;
			}
		}
		if (runningNumberTypeId>0) {
			Iterator<RunningNumber> iterator = organization.getRunningNumbers().iterator();
			while (iterator.hasNext()) {
				RunningNumber runningNumber = (RunningNumber)iterator.next();
				if (runningNumberId>0) {
					if (runningNumberId==runningNumber.getRunningNumberId()) {
						prefix = runningNumber.getPrefix();
						suffix = runningNumber.getSuffix();
						number = runningNumber.getStartNumber();
						branchSeparated = runningNumber.isBranchSeparated()?true:false;
						runningNumberTypeId = runningNumber.getRunningNumberType().getLookupId();
						runningNumberId = runningNumber.getRunningNumberId();
						runningNumberPeriode = runningNumber.getRunningNumberPeriode();
						break;
					}
				} else {
					if (runningNumberTypeId==runningNumber.getRunningNumberType().getLookupId()) {
						prefix = runningNumber.getPrefix();
						suffix = runningNumber.getSuffix();
						number = runningNumber.getStartNumber();
						branchSeparated = runningNumber.isBranchSeparated()?true:false;
						runningNumberTypeId = runningNumber.getRunningNumberType().getLookupId();
						runningNumberId = runningNumber.getRunningNumberId();
						runningNumberPeriode = runningNumber.getRunningNumberPeriode();
						break;
					}
				}
			}
		}
		return SUCCESS;
	}
	
	@Override
	public String update() {
		try {
			if (subaction.equalsIgnoreCase("CANCEL")) {
				getSession().remove("organization");
				setMappedRequest(Constants.PARTIAL_LIST);
				return Constants.REDIRECT;
			}
			if (logoFile!=null) {
				/*UploadFile logo = organization.getLogo()!=null?organization.getLogo():new UploadFile();
				logo.setDatabase(true);
				logo.setFileContent(FileUtils.readFileToByteArray(logoFile));
				logo.setFileContentType(logoFileContentType);
				logo.setFileSize(logoFile.length());
				logo.setFileName(logoFile.getName());
				organization.setLogo(logo);*/
			}
			if (removeLogoFile) {
				organization.setLogoId(null);
			}
			/*if (parentId>0) {
				//Organization parent = basicDAOFactory.getOrganizationDAO().findById(parentId);
				//organization.setParent(parent.getName());
				organization.setParentId(new Long(parentId));
			}  else {
				organization.setParentId(null);
			}*/
			if (subaction.equalsIgnoreCase("ADD")) {
				if (runningNumberId>0) {
					RunningNumber removeRunningNumber = null;
					Iterator<RunningNumber> iterator = organization.getRunningNumbers().iterator();
					while (iterator.hasNext()) {
						RunningNumber runningNumber2 = (RunningNumber)iterator.next();
						if (runningNumber2.getRunningNumberId()==runningNumberId) {
							removeRunningNumber = runningNumber2;
						}
					}
					if (removeRunningNumber!=null) {
						removeRunningNumber.setStartNumber(number);
						removeRunningNumber.setPrefix(prefix);
						Lookup runningNumberType = basicDAOFactory.getLookupDAO().findById(runningNumberTypeId);
						removeRunningNumber.setRunningNumberType(runningNumberType);
						removeRunningNumber.setSuffix(suffix);
						removeRunningNumber.setBranchSeparated(branchSeparated);
						removeRunningNumber.setRunningNumberPeriode(runningNumberPeriode);
						organization.getRunningNumbers().add(removeRunningNumber);
					}
				} else {
					Lookup runningNumberType = basicDAOFactory.getLookupDAO().findById(runningNumberTypeId);
					RunningNumber runningNumber = new RunningNumber(runningNumberType, prefix, suffix, number, organization, branchSeparated, runningNumberPeriode);
					RunningNumber removeRunningNumber = null;
					Iterator<RunningNumber> iterator = organization.getRunningNumbers().iterator();
					while (iterator.hasNext()) {
						RunningNumber runningNumber2 = (RunningNumber)iterator.next();
						if (runningNumber2.getRunningNumberType().getLookupId()==runningNumberTypeId) {
							removeRunningNumber = runningNumber2;
						}
					}
					if (removeRunningNumber!=null) {
						organization.getRunningNumbers().remove(removeRunningNumber);
					}
					organization.getRunningNumbers().add(runningNumber);
				}
				selectedTab = 2;
			}
			getSession().put("organization", organization);
			if (subaction.equalsIgnoreCase("UPDATE_ALL")) {
				/*Session session = basicDAOFactory.getOrganizationDAO().getSession();
				Transaction transaction = session.beginTransaction();
				//basicDAOFactory.getOrganizationDAO().update(organization);
				session.merge(organization);
				transaction.commit();*/
				basicDAOFactory.getOrganizationDAO().update(organization);			
				//
				getSession().remove("organization");
				setMappedRequest(Constants.PARTIAL_LIST);
				return Constants.REDIRECT;
			}
		} catch (Exception e) {
			addActionError(e.getMessage());
			return INPUT;
		}
		setMappedRequest(Constants.EDIT);
		return Constants.REDIRECT;
	}
	
	@Override
	public String delete() {
		try {
			basicDAOFactory.getOrganizationDAO().delete(organizationId);
			// reset organizationId => see struts-config
			organizationId = 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		setMappedRequest(Constants.PARTIAL_LIST);
		return Constants.REDIRECT;
	}
	
	@Override
	public String list() {
		try {
			organizationList = basicDAOFactory.getOrganizationDAO().findAll(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setMappedRequest(Constants.LIST);
		return SUCCESS;
	}
	
	@Override
	public String partialList() {
		getSession().remove("organization");
		try {
			PartialList<Organization> partialList = basicDAOFactory.getOrganizationDAO().findByCriteria(getStart(), getCount(), 
					getAscDesc().equalsIgnoreCase("asc")?Order.asc(getOrderBy().length()>0?getOrderBy():"organizationId"):Order.desc(getOrderBy().length()>0?getOrderBy():"organizationId"), 
					nameX.length()>0?Restrictions.like("name", nameX,MatchMode.ANYWHERE):Restrictions.ne("organizationId", new Long(-1)) 
					//organizationTypeX.length()>0?Restrictions.eq("organizationType", OrganizationType.valueOf(organizationTypeX)):Restrictions.isNotNull("organizationType")
					);
			organizationList = partialList.getList();
			setPager(Pager.generatePager(getStart(), getCount(), partialList.getTotal()));
			setPagerItem(Pager.generatePagerItem(getStart(), getCount(), partialList.getTotal()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		setMappedRequest(Constants.PARTIAL_LIST);
		return SUCCESS;
	}	

	@Override
	public Organization getModel() {
		return organization;
	}

	@Override
	public void prepare() throws Exception {
		try {
			//if (parentList==null)parentList = basicDAOFactory.getOrganizationDAO().findAll(Order.asc("name"));
			/*if (organizationTypeList.size()==0) {
				OrganizationType[] types = OrganizationType.values();
				for (int i=0; i<types.length; i++) {
					if (types[i].name().equalsIgnoreCase(OrganizationType.COMPANY.name())) organizationTypeList.add(types[i].name());
				}
			}*/
			/*if (organizationTypes==null) {
				organizationTypes = basicDAOFactory.getOrganizationTypeDAO().findByCriteria(Order.asc("name"), Restrictions.isNull("parentId"));
			}*/
			if (cities==null) cities = basicDAOFactory.getLocationDAO().findByCriteria(Order.asc("name"), Restrictions.eq("locationType", LocationType.CITY));
					
			if (runningNumberPeriodes.size()==0) {
				RunningNumberPeriode[] periodes = RunningNumberPeriode.values();
				for (int i=0; i<periodes.length; i++) {
					runningNumberPeriodes.add(periodes[i].name());
				}
			}
			if (runningNumberTypeList==null) runningNumberTypeList = basicDAOFactory.getLookupDAO().findByCategory(LookupCategory.RUNNING_NUMBER_TYPE);
		}catch (Exception e) {
		}
		if (organizationId==0) {
			organization = (Organization)getSession().get("organization")!=null?(Organization)getSession().get("organization"):new Organization();
			//address = organization.getAddress()!=null?organization.getAddress():new Address();
			//organizationSetup = organization.getOrganizationSetup()!=null?organization.getOrganizationSetup():new OrganizationSetup();
			organizationId = organization.getOrganizationId();
			//contact = organization.getContact()!=null?organization.getContact():new Contact();
		} else {
			organization = (Organization)getSession().get("organization");
			if (organization==null) {
				Session session = basicDAOFactory.getOrganizationDAO().getSession(Constants.HIBERNATE_CFG_KEY_1);
				organization = (Organization)session.createCriteria(Organization.class)
					.setFetchMode("address", FetchMode.JOIN)
					.setFetchMode("contact", FetchMode.JOIN)
					.setFetchMode("logo", FetchMode.JOIN)
					.setFetchMode("runningNumbers", FetchMode.JOIN)
					.add(Restrictions.eq("organizationId", new Long(organizationId)))
					//.setCacheable(true)
					.uniqueResult();
				session.close();
			}
			if (organization!=null) {
				//address = organization.getAddress()!=null?organization.getAddress():new Address();
				//organizationSetup = organization.getOrganizationSetup()!=null?organization.getOrganizationSetup():new OrganizationSetup();
				//parentId = organization.getParentId()!=null?organization.getParentId().longValue():0;
				//contact = organization.getContact()!=null?organization.getContact():new Contact();
			}
		}
	}

	public List<Organization> getOrganizationList() {
		return organizationList;
	}

	public void setOrganizationList(List<Organization> organizationList) {
		this.organizationList = organizationList;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(long organizationId) {
		this.organizationId = organizationId;
	}

	public List<Organization> getParentList() {
		return parentList;
	}

	public void setParentList(List<Organization> parentList) {
		this.parentList = parentList;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public List<RunningNumber> getRunningNumberList() {
		return runningNumberList;
	}

	public void setRunningNumberList(List<RunningNumber> runningNumberList) {
		this.runningNumberList = runningNumberList;
	}

	public long getRunningNumberId() {
		return runningNumberId;
	}

	public void setRunningNumberId(long runningNumberId) {
		this.runningNumberId = runningNumberId;
	}

	public List<Lookup> getRunningNumberTypeList() {
		return runningNumberTypeList;
	}

	public void setRunningNumberTypeList(List<Lookup> runningNumberTypeList) {
		this.runningNumberTypeList = runningNumberTypeList;
	}

	public long getRunningNumberTypeId() {
		return runningNumberTypeId;
	}

	public void setRunningNumberTypeId(long runningNumberTypeId) {
		this.runningNumberTypeId = runningNumberTypeId;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getSubaction() {
		return subaction;
	}

	public void setSubaction(String subaction) {
		this.subaction = subaction;
	}

	public String getNameX() {
		return nameX;
	}

	public void setNameX(String nameX) {
		this.nameX = nameX;
	}

	public int getSelectedTab() {
		return selectedTab;
	}

	public void setSelectedTab(int selectedTab) {
		this.selectedTab = selectedTab;
	}

	public File getLogoFile() {
		return logoFile;
	}

	public void setLogoFile(File logoFile) {
		this.logoFile = logoFile;
	}

	public String getLogoFileContentType() {
		return logoFileContentType;
	}

	public void setLogoFileContentType(String logoFileContentType) {
		this.logoFileContentType = logoFileContentType;
	}

	public boolean isRemoveLogoFile() {
		return removeLogoFile;
	}

	public void setRemoveLogoFile(boolean removeLogoFile) {
		this.removeLogoFile = removeLogoFile;
	}
	
	

}
