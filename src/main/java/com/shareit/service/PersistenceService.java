package com.shareit.service;

/**
 * 
 * @author dpony
 * PersistenceService.java
 * 
 * This classes the meat of all persistence activities in share it.
 * It uses Security Utils for account related security and query Service for
 * queries. This class should be left to persistence only.
 */
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import com.shareit.entity.Account;
import com.shareit.entity.jpa.JpaAccount;

@Stateless
public class PersistenceService {

	@PersistenceContext
	private EntityManager em;

	@Inject
	QueryService queryService;
	@Inject
	SecurityUtil securityUtil;

	/**
	 * This method saves the account. The password is 
	 * hashed and given the salt through securityUtils.
	 * @param account
	 */
	public void saveAccount(Account account) {
		account.setAccountName(account.getAccountName());
		account.setPassword(account.getPassword());
		Map<String, String> credMap = securityUtil.hashPassword(account.getPassword());
		account.setPassword(credMap.get("hashedPassword"));
		account.setSalt(credMap.get("salt"));
		credMap = null;
		em.persist(account);
	}
}
