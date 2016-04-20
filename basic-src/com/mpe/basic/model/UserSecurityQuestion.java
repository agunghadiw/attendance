package com.mpe.basic.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;

/**
 * Entity implementation class for Entity: UserSecurityQuestion
 *
 */
@Entity
@Table(name="user_security_question")

public class UserSecurityQuestion implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(allocationSize=1,initialValue=1,name="user_security_question_seq",sequenceName="user_security_question_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="user_security_question_seq")
	@Column(name="user_security_question_id")
	long userSecurityQuestionId;
	
	@Min(value=1, message="User can't null")
	@Column(name="user_id")
	long userId;
	
	@Length(max=150, message="Max length 150 chars")
	@Column(length=150)
	String question;
	
	@Length(max=100, message="Max length 100 chars")
	@Column(length=100)
	String answer;
	
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

	public UserSecurityQuestion() {
		super();
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
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

	public long getUserSecurityQuestionId() {
		return userSecurityQuestionId;
	}

	public void setUserSecurityQuestionId(long userSecurityQuestionId) {
		this.userSecurityQuestionId = userSecurityQuestionId;
	}
	
	
   
}
