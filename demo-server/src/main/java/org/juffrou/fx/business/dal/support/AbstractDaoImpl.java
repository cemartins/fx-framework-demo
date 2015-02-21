package org.juffrou.fx.business.dal.support;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public abstract class AbstractDaoImpl<T> {
	
	private final Class<T> tClass;
	
	@Autowired
	private SessionFactory sessionFactory;

	
	protected AbstractDaoImpl(Class<T> tClass) {
		this.tClass = tClass;
	}
	
	private Session getCurrectSession() {
		return sessionFactory.getCurrentSession();
	}

	public Serializable save(T domain) {
		Serializable id = getCurrectSession().save(domain);
		return id;
	}
	
	public void update(T domain) {
		getCurrectSession().update(domain);
	}
	
	public void delete(T domain) {
		getCurrectSession().delete(domain);
	}
	
	public T get(Serializable id) {
		T domain = (T) getCurrectSession().get(tClass, id);
		return domain;
	}
	
	public List<T> list(String query, Object...parameters) {
		Query createQuery = getCurrectSession().createQuery(query);
		if(parameters != null)
			for(int i = 0; i<parameters.length; i++)
				createQuery.setParameter(i, parameters[i]);
		List<T> list = createQuery.list();
		return list;
	}
}
