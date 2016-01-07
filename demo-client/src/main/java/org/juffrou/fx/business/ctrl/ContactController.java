package org.juffrou.fx.business.ctrl;

import org.juffrou.fx.business.dom.Contact;
import org.juffrou.fx.controller.BeanController;
import org.juffrou.fx.controller.bind.Binder;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ContactController extends BeanController<Contact> {
	
	public static String FXML_PATH = "/org/juffrou/fx/business/Contact";

	
	@FXML
	private TextField description;
	
	@FXML
	private TextField value;
	
	
	public ContactController() {
		super(Contact.class);
	}

	public void bindControllerModel(Binder binder) {

		binder.addBidirectional(description.textProperty(), "description");
		binder.addBidirectional(value.textProperty(), "value");
	}
	
}
