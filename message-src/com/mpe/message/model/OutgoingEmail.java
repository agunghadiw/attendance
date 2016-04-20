package com.mpe.message.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: OutgoingEmail
 *
 */
@Entity
@Table(name="outgoing_email",
	indexes=@Index(columnList="receiver,email_date,subject",name="IDX_OUTGOING_EMAIL")
)

public class OutgoingEmail implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(allocationSize=1,initialValue=1,name="outgoing_email_seq",sequenceName="outgoing_email_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="outgoing_email_seq")
	@Column(name="outgoing_email_id")
	long outgoingEmailId;
	
	@Column(length=128)
	String sender;
	
	@Column(length=128, name="receiver")
	String to;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="email_date")
	Date emailDate;
	
	@Column(length=128)
	String subject;
	
	@Lob
	@Column(name="message")
	String message;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="sent_date")
	Date sentDate;
	
	/*@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,orphanRemoval=true)
	@JoinColumn(name="upload_file_id")
	UploadFile uploadFile;*/
	@Column(name="upload_file_id")
	Long uploadFileId;

	public OutgoingEmail() {
		super();
	}

	public long getOutgoingEmailId() {
		return outgoingEmailId;
	}

	public void setOutgoingEmailId(long outgoingEmailId) {
		this.outgoingEmailId = outgoingEmailId;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Date getEmailDate() {
		return emailDate;
	}

	public void setEmailDate(Date emailDate) {
		this.emailDate = emailDate;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getSentDate() {
		return sentDate;
	}

	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public Long getUploadFileId() {
		return uploadFileId;
	}

	public void setUploadFileId(Long uploadFileId) {
		this.uploadFileId = uploadFileId;
	}
	
	
	
	
   
}
