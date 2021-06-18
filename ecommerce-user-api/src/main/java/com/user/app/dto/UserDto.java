package com.user.app.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
public class UserDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -953297098295050686L;

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String userId;
	private String encryptedPassword;
//	private List<AlbumResponseModel> albums;

	private String userName;
	private String gender;
	private String memberType;
	private String accountNumber;
	private Date registrationDate;

}
