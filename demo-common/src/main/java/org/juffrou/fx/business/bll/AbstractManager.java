package org.juffrou.fx.business.bll;

import java.io.Serializable;
import java.util.List;

public interface AbstractManager<T> {

	public Serializable save(T domain);
	
	public void update(T domain);
	
	public void delete(T domain);

	public T get(Serializable id);
	
	public List<T> get(String query, Object...parameters);

}
