package com.mpe.basic.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: UserPasswordHistory
 *
 */
@Entity
@Table(name="user_password_history",
	indexes=@Index(columnList="user_id,old_user_pass,user_pass_change_date")
)

public class UserPasswordHistory implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	// always save password changed here!!!
	
	@Id
	@SequenceGenerator(allocationSize=1,initialValue=1,name="user_password_history_seq",sequenceName="user_password_history_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="user_password_history_seq")
	@Column(name="user_password_history_id")
	long userPasswordHistoryId;
	
	@Column(name="user_id")
	long userId;
	
	@Column(length=128,name="old_user_pass",nullable=false)
	String oldUserPass;
	
	@Temporal(TemporalType.DATE)
	@Column(name="user_pass_change_date")
	Date userPassChangeDate = new Date();

	public UserPasswordHistory() {
		super();
	}

	public long getUserPasswordHistoryId() {
		return userPasswordHistoryId;
	}

	public void setUserPasswordHistoryId(long userPasswordHistoryId) {
		this.userPasswordHistoryId = userPasswordHistoryId;
	}

	public String getOldUserPass() {
		return oldUserPass;
	}

	public void setOldUserPass(String oldUserPass) {
		this.oldUserPass = oldUserPass;
	}

	public Date getUserPassChangeDate() {
		return userPassChangeDate;
	}

	public void setUserPassChangeDate(Date userPassChangeDate) {
		this.userPassChangeDate = userPassChangeDate;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	
   
}
