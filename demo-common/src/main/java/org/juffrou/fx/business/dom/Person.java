package org.juffrou.fx.business.dom;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.juffrou.fx.serials.FxSerials;

@Entity
@Table(name="Person")
public class Person implements FxSerials {
	
	private static final long serialVersionUID = -6807947635627328530L;

	@Id
	@GeneratedValue
	private Integer id;

	private String name;
	private String email;

	private LocalDate dateOfBirth;

	@OneToMany(mappedBy = "person", fetch=FetchType.LAZY, orphanRemoval=true, cascade=CascadeType.ALL)
	private Set<Contact> contacts;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Set<Contact> getContacts() {
		return contacts;
	}
	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}

	public void addContact(Contact contact) {
		if(contacts == null)
			contacts = new HashSet<>();
		contact.setPerson(this);
		contacts.add(contact);
	}
	
	public void removeContact(Contact contact) {
		contacts.remove(contact);
	}
}
