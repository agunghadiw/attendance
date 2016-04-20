package com.mpe.basic.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.Type;

/**
 * Entity implementation class for Entity: ApplicationSetup
 *
 */
@Entity
@Table(name="application_setup")

public class ApplicationSetup implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(allocationSize=1,initialValue=1,name="application_setup_seq",sequenceName="application_setup_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="application_setup_seq")
	@Column(name="application_setup_id")
	long applicationSetupId;
	
	/* email alert */
	
	@Type(type="true_false")
	@Column(name="is_email_internal_enable",length=1)
	boolean emailInternalEnable = false;
	
	@Type(type="true_false")
	@Column(name="is_message_internal_enable",length=1)
	Boolean messageInternalEnable = false;
	
	@Column(name="smtp_email",length=100)
	String smtpEmail;
	
	@Column(name="smtp_user_name",length=100)
	String smtpUserName;
	
	@Column(name="smtp_password",length=100)
	String smtpPassword;
	
	@Column(name="from_email_address",length=100)
	String fromEmailAddress;
	
	/*
	@Type(type="true_false")
	@Column(name="is_message_internal_enable",length=1)
	boolean messageInternalEnable = false;
	
	// email penampung/admin
	@Column(name="administrator_email",length=120)
	String administratorEmail;
	
	// email error service/app
	@Column(name="error_notification_email")
	String errorNotificationEmail;
	*/
	
	/* setup user security */
	
	@Column(name="user_pass_history",length=2)
	Integer userPassHistory;
	
	@Column(name="default_user_pass_duration",length=4)
	Integer defaultUserPassDuration;
	
	@Type(type="true_false")
	@Column(name="is_alphabet_user_pass")
	boolean alphabetUserPass;
	
	@Type(type="true_false")
	@Column(name="is_numeric_user_pass")
	boolean numericUserPass;
	
	@Type(type="true_false")
	@Column(name="is_upper_case_letter")
	boolean upperCaseLetter;
	
	@Column(name="min_user_pass_length")
	int minUserPassLength = 2;
	
	/* guest web */
	
	@Column(name="guest_user_name",length=128)
	String guestUserName;
	
	@Column(name="default_user_role_id")
	Long defaultUserRoleId;
	
	/*@Temporal(TemporalType.DATE)
	@Column(name="beginning_date_annual_leave")
	Date beginningDateAnnualLeave;
	
	@Type(type="true_false")
	@Column(name="is_join_date_annual_leave",length=1)
	boolean joinDateAnnualLeave = true;*/
	
	// separated with comma (,)
	@Column(name="employee_numbers")
	String employeeNumbers;
	
	@Temporal(TemporalType.DATE)
	@Column(name="from_attendance_date")
	Date fromAttendanceDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="to_attendance_date")
	Date toAttendanceDate;
	
	@Column(name="attendance_allowance_month",length=20)
	String attendanceAllowanceMonth;
	
	@Column(name="attendance_allowance_year",length=4)
	Integer attendanceAllowanceYear;
	
	@Column(name="max_month_keep_data",length=2)
	Integer maxMonthKeepData;

	public ApplicationSetup() {
		super();
	}

	public long getApplicationSetupId() {
		return applicationSetupId;
	}

	public void setApplicationSetupId(long applicationSetupId) {
		this.applicationSetupId = applicationSetupId;
	}

	public boolean isEmailInternalEnable() {
		return emailInternalEnable;
	}

	public void setEmailInternalEnable(boolean emailInternalEnable) {
		this.emailInternalEnable = emailInternalEnable;
	}

	public Integer getUserPassHistory() {
		return userPassHistory;
	}

	public void setUserPassHistory(Integer userPassHistory) {
		this.userPassHistory = userPassHistory;
	}

	public Integer getDefaultUserPassDuration() {
		return defaultUserPassDuration;
	}

	public void setDefaultUserPassDuration(Integer defaultUserPassDuration) {
		this.defaultUserPassDuration = defaultUserPassDuration;
	}

	public boolean isAlphabetUserPass() {
		return alphabetUserPass;
	}

	public void setAlphabetUserPass(boolean alphabetUserPass) {
		this.alphabetUserPass = alphabetUserPass;
	}

	public boolean isNumericUserPass() {
		return numericUserPass;
	}

	public void setNumericUserPass(boolean numericUserPass) {
		this.numericUserPass = numericUserPass;
	}

	public boolean isUpperCaseLetter() {
		return upperCaseLetter;
	}

	public void setUpperCaseLetter(boolean upperCaseLetter) {
		this.upperCaseLetter = upperCaseLetter;
	}

	public int getMinUserPassLength() {
		return minUserPassLength;
	}

	public void setMinUserPassLength(int minUserPassLength) {
		this.minUserPassLength = minUserPassLength;
	}

	public String getGuestUserName() {
		return guestUserName;
	}

	public void setGuestUserName(String guestUserName) {
		this.guestUserName = guestUserName;
	}

	public String getSmtpEmail() {
		return smtpEmail;
	}

	public void setSmtpEmail(String smtpEmail) {
		this.smtpEmail = smtpEmail;
	}

	public String getFromEmailAddress() {
		return fromEmailAddress;
	}

	public void setFromEmailAddress(String fromEmailAddress) {
		this.fromEmailAddress = fromEmailAddress;
	}

	public String getSmtpUserName() {
		return smtpUserName;
	}

	public void setSmtpUserName(String smtpUserName) {
		this.smtpUserName = smtpUserName;
	}

	public String getSmtpPassword() {
		return smtpPassword;
	}

	public void setSmtpPassword(String smtpPassword) {
		this.smtpPassword = smtpPassword;
	}

	public Boolean isMessageInternalEnable() {
		return messageInternalEnable;
	}

	public void setMessageInternalEnable(boolean messageInternalEnable) {
		this.messageInternalEnable = messageInternalEnable;
	}

	public Boolean getMessageInternalEnable() {
		return messageInternalEnable;
	}

	public void setMessageInternalEnable(Boolean messageInternalEnable) {
		this.messageInternalEnable = messageInternalEnable;
	}

	public Long getDefaultUserRoleId() {
		return defaultUserRoleId;
	}

	public void setDefaultUserRoleId(Long defaultUserRoleId) {
		this.defaultUserRoleId = defaultUserRoleId;
	}

	public String getEmployeeNumbers() {
		return employeeNumbers;
	}

	public void setEmployeeNumbers(String employeeNumbers) {
		this.employeeNumbers = employeeNumbers;
	}

	public Date getFromAttendanceDate() {
		return fromAttendanceDate;
	}

	public void setFromAttendanceDate(Date fromAttendanceDate) {
		this.fromAttendanceDate = fromAttendanceDate;
	}

	public Date getToAttendanceDate() {
		return toAttendanceDate;
	}

	public void setToAttendanceDate(Date toAttendanceDate) {
		this.toAttendanceDate = toAttendanceDate;
	}

	public String getAttendanceAllowanceMonth() {
		return attendanceAllowanceMonth;
	}

	public void setAttendanceAllowanceMonth(String attendanceAllowanceMonth) {
		this.attendanceAllowanceMonth = attendanceAllowanceMonth;
	}

	public Integer getAttendanceAllowanceYear() {
		return attendanceAllowanceYear;
	}

	public void setAttendanceAllowanceYear(Integer attendanceAllowanceYear) {
		this.attendanceAllowanceYear = attendanceAllowanceYear;
	}

	public Integer getMaxMonthKeepData() {
		return maxMonthKeepData;
	}

	public void setMaxMonthKeepData(Integer maxMonthKeepData) {
		this.maxMonthKeepData = maxMonthKeepData;
	}	
	
	
   
}
