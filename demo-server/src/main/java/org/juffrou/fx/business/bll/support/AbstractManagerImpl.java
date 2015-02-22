package org.juffrou.fx.business.bll.support;

import java.io.Serializable;
import java.util.List;

import org.juffrou.fx.business.bll.AbstractManager;
import org.juffrou.fx.business.dal.support.AbstractDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractManagerImpl<T> implements AbstractManager<T>{

	@Autowired
	private AbstractDaoImpl<T> daoImpl;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public Serializable save(T domain) {
		Serializable id = daoImpl.save(domain);
		return id;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void update(T domain) {
		daoImpl.update(domain);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(T domain) {
		daoImpl.delete(domain);
	}
	
	@Transactional(readOnly=true, isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
	public T load(Serializable id, String...propertiesToInitialize) {
		return daoImpl.load(id, propertiesToInitialize);
	}

	@Transactional(readOnly=true, isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
	public T get(Serializable id) {
		return daoImpl.get(id);
	}
	
	@Transactional(readOnly=true, isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
	public List<T> get(String query, Object...parameters) {
		return daoImpl.list(query, parameters);
	}
}
