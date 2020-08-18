package com.shareit.entity.iface;

import com.shareit.entity.User;
import com.shareit.entity.jpa.JpaAccount;

public interface AccountEntity extends DataEntity {
	
	
	public String getAccountName();

	public void setAccountName(String accountName);
	
	public User getUser();

	public void setUser(User user); 

	public String getPassword(); 

	public void setPassword(String password) ;

	public String getSalt() ;

	public void setSalt(String salt) ;
	
	public String getEmail() ;

	public void setEmail(String email);
	
	
}
