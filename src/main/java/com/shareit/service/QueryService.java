package com.shareit.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.shareit.entity.Account;
import com.shareit.entity.jpa.JpaAccount;

/**
 * 
 * @author dpony
 * This classes the meat of all query activities in share it.
 * It uses Security Utils for account related security
 * This class should be left to queries only.
 *
 */
@Stateless
public class QueryService {
	@Inject
	private SecurityUtil securityUtil;

	@PersistenceContext
	private EntityManager em;

	public JpaAccount findAccountById(Long id) {
		return em.find(JpaAccount.class, id);
	}

	/**
	 * Will use security utils to authenticate an account.
	 * @param accountName
	 * @param plainTextPassword
	 * @return
	 */
	public boolean authenticateAccount(String accountName, String plainTextPassword) {
		try {
			JpaAccount account = em.createNamedQuery(JpaAccount.FIND_USER_BY_CREDENTIALS, JpaAccount.class)
					.setParameter("accountName", accountName.toLowerCase()).getSingleResult();
			return securityUtil.passwordsMatch(account.getPassword(), account.getSalt(), plainTextPassword);
		} catch (NoResultException e) {
			//Could not find the account name
			return false;	
		}

	}

	/**
	 * If an accountName Exists it will return true. DB Contraints
	 * will prevent any account with the same name.
	 * @param account
	 * @return
	 */
	public boolean doesAccountNameExist(Account account) {
		Long accountCount = (Long) em.createNamedQuery(account.getJpaAccountClass().FIND_USER_BY_CREDENTIALS_COUNT)
				.setParameter("accountName", account.getAccountName().toLowerCase()).getSingleResult();
		if (accountCount > 0) {
			return true;
		}
		return false;
	}
}
