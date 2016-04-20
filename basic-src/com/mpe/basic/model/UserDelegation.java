package com.mpe.basic.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Formula;

/**
 * Entity implementation class for Entity: UserDelegation
 *
 */
@Entity
@Table(name="user_delegation",
	indexes=@Index(columnList="from_user_id,to_user_id,from_date,",name="IDX_USER_DELEGATION",unique=true)
)
/*@org.hibernate.annotations.Table(
		appliesTo="user_delegation",
		indexes={@Index(name="IDX_USER_DELEGATION",columnNames={"from_user_id","to_user_id"})}
)
*/
public class UserDelegation implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(allocationSize=1,initialValue=1,name="user_delegation_seq",sequenceName="user_delegation_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="user_delegation_seq")
	@Column(name="user_delegation_id")
	long userDelegationId;
	
	@Min(value=1,message="User delegated must be present")
	@Column(name="from_user_id")
	long fromUserId;
	
	@Formula(value="(select u.user_name from users u where u.user_id=from_user_id)")
	String fromUser;
	
	@Min(value=1,message="User delegation must be selected")
	@Column(name="to_user_id")
	long toUserId;
	
	@Formula(value="(select u.user_name from users u where u.user_id=to_user_id)")
	String toUser;
	
	@NotNull(message="From date can't be blank")
	@Temporal(TemporalType.DATE)
	@Column(name="from_date")
	Date fromDate;
	
	@NotNull(message="To date can't be blank")
	@Temporal(TemporalType.DATE)
	@Column(name="to_date")
	Date toDate;
	
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

	public UserDelegation() {
		super();
	}

	public long getUserDelegationId() {
		return userDelegationId;
	}

	public void setUserDelegationId(long userDelegationId) {
		this.userDelegationId = userDelegationId;
	}

	public long getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(long fromUserId) {
		this.fromUserId = fromUserId;
	}

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public long getToUserId() {
		return toUserId;
	}

	public void setToUserId(long toUserId) {
		this.toUserId = toUserId;
	}

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

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
	
	
   
}
