package org.juffrou.fx.business.bll.support;

import java.io.Serializable;
import java.util.List;

import org.juffrou.fx.business.bll.AbstractManager;
import org.juffrou.fx.business.dal.support.AbstractDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractManagerImpl<T> implements AbstractManager<T>{

	@Autowired
	private AbstractDaoImpl<T> daoImpl;
	
	public Serializable save(T domain) {
		Serializable id = daoImpl.save(domain);
		return id;
	}
	
	public void update(T domain) {
		daoImpl.update(domain);
	}
	
	public void delete(T domain) {
		daoImpl.delete(domain);
	}

	public T get(Serializable id) {
		return daoImpl.get(id);
	}
	
	public List<T> get(String query, Object...parameters) {
		return daoImpl.list(query, parameters);
	}
}
