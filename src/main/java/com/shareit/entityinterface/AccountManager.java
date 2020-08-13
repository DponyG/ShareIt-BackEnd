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

import com.shareit.entity.User;
import com.shareit.entity.jpa.JpaAccount;

@Stateful
public class AccountManager {
	
	@PersistenceContext
    private EntityManager em;
	
	public AccountManager() {
		
	}
	
	public void addUser(String name) {
		JpaAccount account = new JpaAccount();
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
	
	public Collection<JpaAccount> getAllUsers() {
		TypedQuery<JpaAccount> allUsers = em.createNamedQuery(JpaAccount.FIND_ALL, JpaAccount.class);
		return allUsers.getResultList();
	}
	
	public Collection<JpaAccount>getAllUsersFromCriteria(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<JpaAccount> cq = cb.createQuery(JpaAccount.class);
		Root<JpaAccount> user = cq.from(JpaAccount.class);
		cq.select(user);
		return em.createQuery(cq).getResultList();
	}
	
}
