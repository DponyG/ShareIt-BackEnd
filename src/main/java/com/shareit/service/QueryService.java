package com.shareit.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.shareit.entity.Account;

@Stateless
public class QueryService {
	@Inject
    private SecurityUtil securityUtil;
	
	@PersistenceContext
    private EntityManager em;
	
	public Account findAccountById(Long id) {
		return em.find(Account.class, id);
	}
	
	public boolean authenticateAccount(String accountName, String plainTextPassword) {

        Account account = em.createNamedQuery(Account.FIND_USER_BY_CREDENTIALS, Account.class)
                .setParameter("accountName", accountName.toLowerCase()).getResultList().get(0);

        if (account != null) {
            return securityUtil.passwordsMatch(account.getPassword(), account.getSalt(), plainTextPassword);
        }
        return false;
    }
	


}
