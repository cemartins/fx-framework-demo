package org.juffrou.fx.business.ctrl;

import org.juffrou.fx.business.dom.Person;
import org.juffrou.fx.controller.BeanController;
import org.juffrou.fx.controller.bind.Binder;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class PersonController extends BeanController<Person> {
	
	public static String FXML_PATH = "/org/juffrou/fx/business/Person";
	
	@FXML
	private TextField name;
	
	@FXML
	private TextField email;
	
	@FXML
	private DatePicker dateOfBirth;
	
	public PersonController() {
		super(Person.class);
	}

	public void bindControllerModel(Binder binder) {

		binder.addBidirectional(name.textProperty(), "name");
		binder.addBidirectional(email.textProperty(), "email");
		binder.addBidirectional(dateOfBirth.valueProperty(), "dateOfBirth");
	}
	
}
