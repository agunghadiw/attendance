package com.mpe.basic.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotBlank;


/**
 * Entity implementation class for Entity: Organization
 *
 */
@Entity
@Table(name="organization",
	uniqueConstraints={@UniqueConstraint(columnNames={"code","name"})}
)
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

public class Organization implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(allocationSize=1,initialValue=1,name="organization_seq",sequenceName="organization_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="organization_seq")
	@Column(name="organization_id",nullable=false)
	long organizationId;
	
	/*@Column(name="parent_id",nullable=true)
	Long parentId;*/
	
	/*// munculkan type => organization-type parent-ID isnull!
	@Column(name="organization_type_id")
	long organizationTypeId;*/
	
	@NotBlank(message="Code can't blank")
	@Column(name="code",length=20)
	String code;
	
	@NotBlank(message="Name can't blank")
	@Column(name="name",nullable=false,length=64,unique=true)
	String name;
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,orphanRemoval=true)
	@JoinColumn(name="address_id")
	Address address = new Address();
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,orphanRemoval=true)
	@JoinColumn(name="contact_id")
	Contact contact = new Contact();
	
	@Column(nullable=true,length=128)
	String url;
	
	@Column(nullable=true,length=20)
	String npwp;
	
	@Column(name="npwp_date",nullable=true)
	@Temporal(TemporalType.DATE)
	Date npwpDate = new Date();
	
	@Column(name="npwp_sn",nullable=true,length=9)
	String npwpSn;
	
	/*@Column(name="approval_person",nullable=true,length=30)
	String approvalPerson;
	
	@Column(nullable=true,length=30)
	String position;*/
	
	/*@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="logo_id")
	UploadFile logo;*/
	
	@Column(name="logo_id")
	Long logoId;
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="organization_setup_id")
	@Fetch(FetchMode.JOIN)
	OrganizationSetup organizationSetup = new OrganizationSetup();
	
	//@Size(min=2,message="Minimum running number created is 2")
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="organization",orphanRemoval=true)
	@BatchSize(size=100)
	Set<RunningNumber> runningNumbers = new HashSet<RunningNumber>();
	
	@Column(length=128,name="create_by",insertable=true,updatable=false)
	String createBy;
	
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_on",insertable=true,updatable=false)
    Date createOn;
    
    @Column(length=128,name="change_by",insertable=false,updatable=true)
    String changeBy;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="change_on",insertable=false,updatable=true)
    Date changeOn;

	public Organization() {
		super();
	}

	public long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(long organizationId) {
		this.organizationId = organizationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNpwp() {
		return npwp;
	}

	public void setNpwp(String npwp) {
		this.npwp = npwp;
	}

	public Date getNpwpDate() {
		return npwpDate;
	}

	public void setNpwpDate(Date npwpDate) {
		this.npwpDate = npwpDate;
	}

	public String getNpwpSn() {
		return npwpSn;
	}

	public void setNpwpSn(String npwpSn) {
		this.npwpSn = npwpSn;
	}

	public OrganizationSetup getOrganizationSetup() {
		return organizationSetup;
	}

	public void setOrganizationSetup(OrganizationSetup organizationSetup) {
		this.organizationSetup = organizationSetup;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

/*	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}*/

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateOn() {
		return createOn;
	}

	public void setCreateOn(Date createOn) {
		this.createOn = createOn;
	}

	public String getChangeBy() {
		return changeBy;
	}

	public void setChangeBy(String changeBy) {
		this.changeBy = changeBy;
	}

	public Date getChangeOn() {
		return changeOn;
	}

	public void setChangeOn(Date changeOn) {
		this.changeOn = changeOn;
	}

	public Set<RunningNumber> getRunningNumbers() {
		return runningNumbers;
	}

	public void setRunningNumbers(Set<RunningNumber> runningNumbers) {
		this.runningNumbers = runningNumbers;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public Long getLogoId() {
		return logoId;
	}

	public void setLogoId(Long logoId) {
		this.logoId = logoId;
	}

/*	public long getOrganizationTypeId() {
		return organizationTypeId;
	}

	public void setOrganizationTypeId(long organizationTypeId) {
		this.organizationTypeId = organizationTypeId;
	}*/
	
	
   
}
