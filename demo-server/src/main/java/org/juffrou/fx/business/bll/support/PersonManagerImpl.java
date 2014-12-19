package org.juffrou.fx.business.bll.support;

import org.juffrou.fx.business.bll.PersonManager;
import org.juffrou.fx.business.dom.Person;
import org.springframework.stereotype.Service;

@Service("personManager")
public class PersonManagerImpl extends AbstractManagerImpl<Person> implements PersonManager {

}
