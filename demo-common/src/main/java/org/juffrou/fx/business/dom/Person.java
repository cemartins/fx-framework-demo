package org.juffrou.fx.business.dom;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.juffrou.fx.serials.JFXSerializable;

@Entity
@Table(name="Person")
public class Person implements JFXSerializable {
	
	private static final long serialVersionUID = -6807947635627328530L;

	@Id
	@GeneratedValue
	private Integer id;

	private String name;
	private String email;

	@Column()
	private LocalDate dateOfBirth;

	@OneToMany(targetEntity = Contact.class, mappedBy = "person", fetch=FetchType.LAZY, orphanRemoval=true, cascade=CascadeType.ALL)
	private List<Contact> contacts;
	
	
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
	public List<Contact> getContacts() {
		return contacts;
	}
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public void addContact(Contact contact) {
		if(contacts == null)
			setContacts(new ArrayList<>());
		contact.setPerson(this);
		getContacts().add(contact);
	}
	
	public void removeContact(Contact contact) {
		getContacts().remove(contact);
	}
}
