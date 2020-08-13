package com.shareit.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.shareit.entity.jpa.JpaAccount;

@Entity
public class User {
	
	@Id
    protected Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
    @MapsId
    protected JpaAccount account;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public JpaAccount getAccount() {
		return account;
	}

	public void setAccount(JpaAccount account) {
		this.account = account;
	}
	
	
}
