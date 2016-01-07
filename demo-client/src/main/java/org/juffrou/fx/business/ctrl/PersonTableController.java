package org.juffrou.fx.business.ctrl;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;

import org.juffrou.fx.business.dom.Person;
import org.juffrou.fx.controller.TableController;
import org.juffrou.fx.controller.model.ListControllerModel;
import org.juffrou.fx.core.LifecyclePresentationManager;

public class PersonTableController extends TableController<Person> {
	
	public static String FXML_PATH = "/org/juffrou/fx/business/PersonTable";

	
	@FXML
	private TableView<Person> table;

	private LifecyclePresentationManager lifecyclePresentationManager;
	
	public PersonTableController() {
		super(Person.class);
	}
	

	public void bindControllerModel(ListControllerModel<Person> presentationModel) {

		table.setItems(getControllerModel().getModelSource());
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		super.initialize(location, resources);

		// add double click editing
		table.setRowFactory( tv -> {
		    TableRow<Person> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
						Person rowData = row.getItem();
						lifecyclePresentationManager.setSearchItem(rowData);
		        }
		    });
		    return row ;
		});	}
	
	@FXML
	private void search() {
		lifecyclePresentationManager.search();
	}


	public LifecyclePresentationManager getLifecyclePresentationManager() {
		return lifecyclePresentationManager;
	}


	public void setLifecyclePresentationManager(
			LifecyclePresentationManager lifecyclePresentationManager) {
		this.lifecyclePresentationManager = lifecyclePresentationManager;
	}
	
}
