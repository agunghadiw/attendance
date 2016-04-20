package com.mpe.basic.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: UserSecugen
 *
 */
@Entity
@Table(name="user_secugen")

public class UserSecugen implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(allocationSize=1,initialValue=1,name="user_secugen_seq",sequenceName="user_secugen_seq")
	@GeneratedValue(generator="user_secugen_seq",strategy=GenerationType.SEQUENCE)
	@Column(name="user_secugen_id")
	long userSecugenId;
	
	@Column(name="user_id")
	long userId;
	

	public UserSecugen() {
		super();
	}


	public long getUserSecugenId() {
		return userSecugenId;
	}


	public void setUserSecugenId(long userSecugenId) {
		this.userSecugenId = userSecugenId;
	}


	public long getUserId() {
		return userId;
	}


	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	
   
}
