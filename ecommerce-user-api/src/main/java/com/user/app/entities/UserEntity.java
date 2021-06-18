package com.user.app.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "TBL_USER")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = -5554035048186928522L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;

	@Column(nullable = false, length = 120, unique = true)
	private String email;

	@Column(nullable = false, unique = true)
	private String userId;

	@Column(nullable = false, unique = true)
	private String encryptedPassword;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "GENDER")
	private String gender;

	@Column(name = "MEMBER_TYPE")
	private String memberType;

	@Column(name = "ACCOUNT_NUMBER")
	private String accountNumber;

	@Temporal(TemporalType.DATE)
	@Column(name = "REGISTRATION_DATE", length = 10)
	private Date registrationDate;

}
