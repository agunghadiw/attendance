package com.mpe.basic.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: UserActivity
 *
 */
@Entity
@Table(name="user_activity",
	indexes=@Index(columnList="activity_date,user_name,user_id",name="IDX_USER_ACTIVITY",unique=false)
)
/*@org.hibernate.annotations.Table(
	appliesTo="user_activity", 
	indexes=@org.hibernate.annotations.Index(name="IDX_USER_ACTIVITY",columnNames={"activity_date","user_name","user_id"})
)*/

public class UserActivity implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(allocationSize=1,initialValue=1,name="user_activity_seq",sequenceName="user_activity_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="user_activity_seq")
	@Column(name="user_activity_id")
	long userActivityId;
	
	@Column(name="user_id")
	long userId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="activity_date")
	Date activityDate = new Date();
	
	@Column(name="user_name",length=128)
	String userName;
	
	@Column(name="activity_name")
	String activityName;
	
	// activity-name tsb diakses berapa kali dalam 1 hari! => tidak ada repeated activity_name dalam 1hari oleh 1user
	//@Column(name="accessed_times",length=4)
	//int accessedTimes;
	
	@Column(name="ip",length=20)
	String ip;
	

	public UserActivity() {
		super();
	}


	public UserActivity(String userName,
			String activityName, String ip) {
		super();
		this.userName = userName;
		this.activityName = activityName;
		this.ip = ip;
	}


	public long getUserActivityId() {
		return userActivityId;
	}


	public void setUserActivityId(long userActivityId) {
		this.userActivityId = userActivityId;
	}


	public Date getActivityDate() {
		return activityDate;
	}


	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getActivityName() {
		return activityName;
	}


	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}


	public String getIp() {
		return ip;
	}


	public void setIp(String ip) {
		this.ip = ip;
	}


	public long getUserId() {
		return userId;
	}


	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	
   
}
