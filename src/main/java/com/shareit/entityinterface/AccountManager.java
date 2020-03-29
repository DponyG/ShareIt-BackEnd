package com.shareit.entityinterface;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.shareit.entity.Account;
import com.shareit.entity.User;

@Stateful
public class AccountManager {
	
	@PersistenceContext
    private EntityManager em;
	
	public AccountManager() {
		
	}
	
	public void addUser(String name) {
		Account account = new Account();
		User user = new User();
		user.setAccount(account);
		account.setAccountName(name);
		account.setUser(user);
		em.persist(account);
		em.remove(account);
	}
	
//	public boolean userExists(String userName) {	
//		TypedQuery<Account> query = em.createQuery("Select u FROM Account u where u.accountName = :userNames  ", Account.class);
//		String userNames = userName;
//		return (query.getMaxResults() == 1);
//	}
	
	public Collection<Account> getAllUsers() {
		TypedQuery<Account> allUsers = em.createNamedQuery(Account.FIND_ALL, Account.class);
		return allUsers.getResultList();
	}
	
	public Collection<Account>getAllUsersFromCriteria(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Account> cq = cb.createQuery(Account.class);
		Root<Account> user = cq.from(Account.class);
		cq.select(user);
		return em.createQuery(cq).getResultList();
	}
	
}
