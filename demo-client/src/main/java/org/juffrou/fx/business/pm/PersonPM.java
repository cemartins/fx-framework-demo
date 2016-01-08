package org.juffrou.fx.business.pm;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import org.juffrou.fx.business.bll.PersonManager;
import org.juffrou.fx.business.ctrl.ContactTableController;
import org.juffrou.fx.business.ctrl.PersonController;
import org.juffrou.fx.business.ctrl.PersonTableController;
import org.juffrou.fx.business.dom.Contact;
import org.juffrou.fx.business.dom.Person;
import org.juffrou.fx.controller.BeanController;
import org.juffrou.fx.controller.ControllerFactory;
import org.juffrou.fx.core.LifecyclePresentationManager;
import org.juffrou.fx.error.NodeBuildingException;
import org.juffrou.fx.serials.FxSerialsContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PersonPM implements LifecyclePresentationManager<Person> {
	
	@Autowired
	private PersonManager personManager;
	
	private FxSerialsContext fxSerialsContext = new FxSerialsContext();
	
	private PersonTableController personTableController;
	private BeanController<Person> personController;
	private ContactTableController contactController;
	
	private StackPane rootPane;
	private Node searchNode;
	private Node itemNode;
	
	public PersonPM() {
	}
	
	public void initialize() {
		
		rootPane = new StackPane();
		
		searchNode = getSearchNode();
		searchNode.setVisible(false);
		
		itemNode = getPersonNode();
		
		rootPane.getChildren().addAll(searchNode, itemNode);
	}
	
	public Node getRootNode() {
		return rootPane;
	}
	
	private Node getSearchNode() {
		// load PersonTable
		FXMLLoader loader = ControllerFactory.getLoader(PersonTableController.FXML_PATH);
		
		try {
			
			Parent searchNode = loader.load();
			personTableController = (PersonTableController) loader.getController();
			
			personTableController.setLifecyclePresentationManager(this);
			
			return searchNode;
		} catch (IOException e) {
			throw new NodeBuildingException("Cannot load PersonTable.fxml", e);
		}
	}
	
	private Node getPersonNode() {
		
		try {
			
			VBox vbox = new VBox();
			
			//Load person 
			FXMLLoader loader = ControllerFactory.getLoader(PersonController.FXML_PATH);
			loader.load();
			Parent parent = loader.getRoot();
			personController = loader.getController();
			
			vbox.getChildren().add(parent);
			
			loader = ControllerFactory.getLoader(ContactTableController.FXML_PATH);
			parent = loader.load();
			contactController = loader.getController();
			personController.getBinder().addBidirectional(contactController.getControllerModel(), "contacts");

			vbox.getChildren().add(parent);

			return vbox;
		} catch (IOException e) {
			throw new NodeBuildingException("Cannot load Person.fxml", e);
		}
	}
	
	public void save() {
		Person person = getLifecycleModelSource();
		Serializable id = person.getId();
		if(id == null)
			id = personManager.save(person);
		else
			personManager.update(person);

		System.out.println("saved person");

		person = personManager.load(id, "contacts");
		setLifecycleModelSource(person);
	}
	
	public void search() {
		System.out.println("Search...");
		ObservableList<Person> list = FXCollections.observableArrayList((Collection<Person>)personManager.get("from Person", null));
		personTableController.getControllerModel().setModelSource(list);
		itemNode.setVisible(false);
		searchNode.setVisible(true);
		System.out.println("Got " + list.size() + " guys");
	}
	
	public void setSearchItem(Person item) {
		Person person = (Person) item;
		
		// initialize the contacts collection
		person = personManager.load(person.getId(), "contacts");
		
		setLifecycleModelSource(person);
		searchNode.setVisible(false);
		itemNode.setVisible(true);
	}
	
	public void createNewTransient() {
		Person person = new Person();
		person.setContacts(new ArrayList<Contact>());
		setLifecycleModelSource(person);
		searchNode.setVisible(false);
		itemNode.setVisible(true);
	}
	
	
	public void cancel() {
		System.out.println("loading person");
		
		Person person = new Person();
		person.setName("Carlos");
		person.setEmail("cemartins@netcabo.pt");
		person.setDateOfBirth(LocalDate.of(1967, 10, 1));
		
		Contact contact = new Contact();
		contact.setDescription("phone");
		contact.setValue("21 441 97 53");
		person.addContact(contact);
		
		contact = new Contact();
		contact.setDescription("mobile");
		contact.setValue("916 173 239");
		person.addContact(contact);
		
		setLifecycleModelSource(person);
		searchNode.setVisible(false);
		itemNode.setVisible(true);

	}
	
	@Override
	public void setLifecycleModelSource(Person sourceDomain) {
		if(sourceDomain != null)
			sourceDomain = fxSerialsContext.getProxy(sourceDomain);
		personController.getControllerModel().setModelSource(sourceDomain);
	}

	@Override
	public Person getLifecycleModelSource() {
		Person person = personController.getControllerModel().getModelSource();
		if(person != null)
			person = (Person) fxSerialsContext.getOriginalBean(person);
		return person;
	}

}
