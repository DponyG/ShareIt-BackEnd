package com.shareit.entity;

import com.shareit.entity.iface.AccountEntity;
import com.shareit.entity.jpa.JpaAccount;

public class Account extends BusinessEntity<AccountEntity> {

	public Account() {

	}

	public AccountEntity getAccountEntity() {
		AccountEntity entity = getDataEntity();
		if (entity == null) {
			// This will eventually change when implemting xml criteria.
			entity = new JpaAccount();
			setDataEntity(entity);
		}
		return entity;
	}

	public void setAccountName(String name) {
		AccountEntity entity = getDataEntity();
		entity.setAccountName(name);
	}

	public void setPassword(String password) {
		AccountEntity entity = getDataEntity();
		entity.setPassword(password);
	}

	public void setSalt(String salt) {
		AccountEntity entity = getDataEntity();
		entity.setSalt(salt);
	}

	public String getAccountName() {
		AccountEntity entity = getDataEntity();
		return entity.getAccountName();
	}

	public String getPassword() {
		AccountEntity entity = getDataEntity();
		return entity.getPassword();
	}

	@Override
	public void save() {

	}

}
