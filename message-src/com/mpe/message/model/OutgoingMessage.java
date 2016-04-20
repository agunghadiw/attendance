package com.mpe.message.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: OutgoingMessage
 *
 */
@Entity
@Table(name="outgoing_message",
	indexes=@Index(columnList="receiver,message_date",name="IDX_OUTGOING_MSG")
)

public class OutgoingMessage implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(allocationSize=1,initialValue=1,name="outgoing_message_seq",sequenceName="outgoing_message_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="outgoing_message_seq")
	@Column(name="outgoing_message_id")
	long outgoingMessageId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="message_date")
	Date messageDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="sent_date")
	Date sentDate;
	
	@Column(length=20)
	String receiver;
	
	@Lob
	@Column(name="message")
	String message;
	
	@Column(name="reff_no",length=20)
	String reffNo;
	
	// -1 fail, 0=success, 3=wrong userid/password/masking
	@Column(length=1)
	Integer status;

	public OutgoingMessage() {
		super();
	}

	public long getOutgoingMessageId() {
		return outgoingMessageId;
	}

	public void setOutgoingMessageId(long outgoingMessageId) {
		this.outgoingMessageId = outgoingMessageId;
	}

	public Date getMessageDate() {
		return messageDate;
	}

	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
	}

	public Date getSentDate() {
		return sentDate;
	}

	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getReffNo() {
		return reffNo;
	}

	public void setReffNo(String reffNo) {
		this.reffNo = reffNo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	
	
   
}
