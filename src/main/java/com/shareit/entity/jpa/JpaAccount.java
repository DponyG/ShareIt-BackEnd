package com.shareit.entity.jpa;

import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

import com.shareit.entity.User;
import com.shareit.entity.iface.AccountEntity;

@Entity
@NamedQuery(name = JpaAccount.FIND_ALL, query = "select u from JpaAccount u")
@NamedQuery(name = JpaAccount.FIND_USER_BY_CREDENTIALS, query = "select u from JpaAccount u where u.accountName = :accountName")
@NamedQuery(name = JpaAccount.FIND_USER_BY_CREDENTIALS_COUNT, query = "select count(u) from JpaAccount u where u.accountName = :accountName")
public class JpaAccount implements AccountEntity {

	public static final String FIND_ALL = "Account.findAll";
	public static final String FIND_USER_BY_CREDENTIALS = "Account.FIND_USER_BY_CREDENTIALS";
	public static final String FIND_USER_BY_CREDENTIALS_COUNT = "Account.FIND_USER_BY_CREDENTIALS_COUNT";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
	protected User user;

	@NotNull
	@Column(unique= true)
	protected String accountName;

	//@NotEmpty(message = "Email must be set")
	@Email(message = "The email must be in the form user@domain.com")
	@FormParam("email")
	private String email;

	@Size(min = 8)
	private String password;

	private String salt;

	public JpaAccount() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSequenceName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Map<String, Object> getAsMap() {
		// TODO Auto-generated method stub
		return null;
	}


}
