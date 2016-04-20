package com.mpe.message.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: IncomingMessage
 *
 */
@Entity
@Table(name="incoming_message",
	indexes=@Index(columnList="message_date,sender",name="IDX_INCOMING_MSG")
)

public class IncomingMessage implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="incoming_message_id")
	long incomingMessageId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="message_date")
	Date messageDate;
	
	@Column(length=20)
	String sender;
	
	@Column(name="message",length=160)
	String message;

	public IncomingMessage() {
		super();
	}

	public long getIncomingMessageId() {
		return incomingMessageId;
	}

	public void setIncomingMessageId(long incomingMessageId) {
		this.incomingMessageId = incomingMessageId;
	}

	public Date getMessageDate() {
		return messageDate;
	}

	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
   
}
