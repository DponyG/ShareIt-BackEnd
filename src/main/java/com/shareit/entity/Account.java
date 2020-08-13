package com.shareit.entity;
import com.shareit.entity.iface.AccountEntity;
import com.shareit.entity.jpa.JpaAccount;

public class Account extends BusinessEntity<AccountEntity> {
	String accountName = "";
	String password = "";
	
	public Account() {
		
	}
	
	@Override
	public void save() {
		
	}
	
	public AccountEntity getAccountEntity() {
		AccountEntity entity = (AccountEntity)getDataEntity();
		if (entity==null) {
			//This will eventually change when implemting xml criteria.
			entity = new JpaAccount();
			setDataEntity(entity);                  
        }
        return entity;
	}
	
	public void setAccountName(String name) {
		this.accountName = name;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getAccountName() {
		return accountName;
	}

	public String getPassword() {
		return password;
	}
	

}
