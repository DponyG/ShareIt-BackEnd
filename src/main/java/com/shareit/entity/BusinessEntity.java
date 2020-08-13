package com.shareit.entity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class BusinessEntity<D> {
	
	@PersistenceContext
	private EntityManager em;
	
	private D dataEntity = null;

	public BusinessEntity() {

	}

	public D getDataEntity() {
		return this.dataEntity;
	}
	
	public void setDataEntity(D dataEntity) {
		this.dataEntity = dataEntity;
	}
	
	public void createDataEntity() {
		
	}
	
	public void save() {
		if(dataEntity == null) {
			System.out.println("No underlying Data Entity");
		}
		em.persist(dataEntity);
		
	}
	
	

}
