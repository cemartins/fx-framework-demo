package org.juffrou.fx.business.ctrl;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import org.juffrou.fx.business.dom.Contact;
import org.juffrou.fx.controller.ControllerFactory;
import org.juffrou.fx.controller.TableController;
import org.juffrou.fx.controller.model.TableControllerModel;

public class ContactTableController extends TableController<Contact> {
	
	public static String FXML_PATH = "/org/juffrou/fx/business/ContactTable";

	
	@FXML
	private TableView<Contact> table;
	
	@FXML
	private TableColumn<Contact,String> valueColumn;
	
	public ContactTableController() {
		super(Contact.class);
	}

	
	public void bindControllerModel(TableControllerModel<Contact> presentationModel) {

		table.setItems(getControllerModel().getModelSource());
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		super.initialize(location, resources);

		// add editable cell factory for the value column
		valueColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		
		// add double click editing
		table.setRowFactory( tv -> {
		    TableRow<Contact> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
					try {
						Contact rowData = row.getItem();
						
						FXMLLoader loader = ControllerFactory.getLoader(ContactController.FXML_PATH);
						loader.load();
						Parent parent = loader.getRoot();
						ContactController controller = loader.getController();
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
}
