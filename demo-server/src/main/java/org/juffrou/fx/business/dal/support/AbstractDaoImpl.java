package org.juffrou.fx.business.dal.support;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Subgraph;
import javax.persistence.TypedQuery;

import org.juffrou.fx.business.dom.PersistableDomain;
import org.springframework.stereotype.Repository;

@Repository
public abstract class AbstractDaoImpl<T extends PersistableDomain> {
	
	private final Class<T> tClass;
	
	// Injected database connection:
    @PersistenceContext 
    private EntityManager em;

	
	protected AbstractDaoImpl(Class<T> tClass) {
		this.tClass = tClass;
	}
	
	public Serializable save(T domain) {
		em.persist(domain);
		return domain.getId();
	}
	
	public void merge(T domain) {
		em.merge(domain);
	}
	
	public void delete(T domain) {
		em.remove(domain);
	}
	
	public T load(Serializable id, String ... propertiesToInitialize) {
		
		Map<String, Object> hints = null;
		
		if(propertiesToInitialize != null) {
			hints = new HashMap<>();
			EntityGraph<T> graph = em.createEntityGraph(tClass);
			for(String path : propertiesToInitialize) {
				Subgraph<Object> itemGraph = graph.addSubgraph(path);
			}
		}
		
		T domain = (T) em.find(tClass, id, hints);
		return domain;
	}
	
	public T get(Serializable id) {
		T domain = (T) em.getReference(tClass, id);
		return domain;
	}
	
	public List<T> list(String query, Object...parameters) {
		TypedQuery<T> createQuery = em.createQuery(query, tClass);
		if(parameters != null)
			for(int i = 0; i<parameters.length; i++)
				createQuery.setParameter(i, parameters[i]);
		List<T> list = createQuery.getResultList();
		return list;
	}
}
