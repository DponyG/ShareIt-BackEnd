package com.shareit.service;

import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.shareit.entity.Account;

@Stateless
public class PersistenceService {
	
	@PersistenceContext
    private EntityManager em;
	
	@Inject
    QueryService queryService;
	@Inject
    SecurityUtil securityUtil;
	
	public void saveAccount() {
		Account account = new Account();
		account.setAccountName("Test");
//		Map<String, String>credMap = securityUtil.hashPassword(account.getPassword());
//		account.setPassword(credMap.get("hashedPassword"));
//		account.setSalt(credMap.get("salt"));
//		if(account.getId() == null) {
//			em.persist(account);
//		}
//		else {
//			em.merge(account);
//		}
//		credMap = null;
//		em.persis
		em.persist(account);
	}


}
