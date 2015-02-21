package org.juffrou.fx.business.dal.support;

import org.juffrou.fx.business.dom.Person;
import org.springframework.stereotype.Repository;

@Repository("personDao")
public class PersonDaoImpl extends AbstractDaoImpl<Person> {
	
	public PersonDaoImpl() {
		super(Person.class);
	}

}
