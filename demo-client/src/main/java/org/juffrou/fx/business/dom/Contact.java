package org.juffrou.fx.business.dom;

import org.juffrou.fx.serials.FxSerials;

public class Contact implements FxSerials {

	private static final long serialVersionUID = -1927971714182915038L;
	
	private Person person;
	private String description;
	private String value;
	
	
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "Contact: " + description + "= " + value;
	}
}
