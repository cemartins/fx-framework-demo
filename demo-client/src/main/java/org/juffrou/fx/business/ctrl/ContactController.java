package org.juffrou.fx.business.ctrl;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import org.juffrou.fx.business.dom.Contact;
import org.juffrou.fx.controller.BeanController;
import org.juffrou.fx.controller.model.BeanControllerModel;

public class ContactController extends BeanController<Contact> {
	
	public static String FXML_PATH = "/org/juffrou/fx/business/Contact";

	
	@FXML
	private TextField description;
	
	@FXML
	private TextField value;
	
	
	public ContactController() {
		super(Contact.class);
	}

	public void bindControllerModel(BeanControllerModel<Contact> presentationModel) {

		presentationModel.readWriteBind(description.textProperty(), "description");
		presentationModel.readWriteBind(value.textProperty(), "value");
	}
	
}
