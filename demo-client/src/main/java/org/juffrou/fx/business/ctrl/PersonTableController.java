package org.juffrou.fx.business.ctrl;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import org.juffrou.fx.business.bll.PersonManager;
import org.juffrou.fx.business.dom.DiscoPerson;
import org.juffrou.fx.business.dom.Person;
import org.juffrou.fx.controller.ControllerFactory;
import org.juffrou.fx.controller.TableController;
import org.juffrou.fx.controller.model.TableControllerModel;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonTableController extends TableController<DiscoPerson> {
	
	public static String FXML_PATH = "/org/juffrou/fx/business/PersonTable";

	
	@FXML
	private TableView<DiscoPerson> table;
	
	@Autowired
	private PersonManager personManager;
	

	public PersonTableController() {
		super(DiscoPerson.class);
	}
	
	
	public PersonManager getPersonManager() {
		return personManager;
	}
	public void setPersonManager(PersonManager personManager) {
		this.personManager = personManager;
	}



	public void bindControllerModel(TableControllerModel<DiscoPerson> presentationModel) {

		table.setItems(getControllerModel().getModelSource());
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		super.initialize(location, resources);

		// add double click editing
		table.setRowFactory( tv -> {
		    TableRow<DiscoPerson> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
					try {
						DiscoPerson rowData = row.getItem();
						
						FXMLLoader loader = ControllerFactory.getLoader(PersonController.FXML_PATH);
						loader.load();
						Parent parent = loader.getRoot();
						PersonController controller = loader.getController();
						controller.getControllerModel().setModelSource(rowData);
						
						Stage stage = new Stage();
						Scene scene = new Scene( parent );
						stage.setScene(scene);
						
						stage.show();

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
		    });
		    return row ;
		});	}
	
	@FXML
	private void search() {
		System.out.println("Search...");
		List<Person> list = personManager.get("from Person", null);
		System.out.println("Got " + list.size() + " guys");
	}
}
