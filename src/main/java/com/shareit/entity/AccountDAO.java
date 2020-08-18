package com.shareit.entity;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.shareit.entity.jpa.JpaAccount;
import com.shareit.service.SecurityUtil;

@Stateless
public class AccountDAO {

	@Inject
	private SecurityUtil securityUtil;

	@PersistenceContext
	private EntityManager em;

	public void accountDAO() {

	}

	public Account createAccount() {
		Account account = new Account();
		account.setDataEntity(new JpaAccount());
		return account;
	}

	public Account getAccount(Long id) {
		JpaAccount jpaAccount = em.find(JpaAccount.class, id);
		Account account = new Account();
		account.setDataEntity(jpaAccount);
		return account;
	}

}
