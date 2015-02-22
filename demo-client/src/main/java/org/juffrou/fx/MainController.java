package org.juffrou.fx;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.juffrou.fx.business.pm.PersonPM;
import org.juffrou.fx.core.LifecycleController;
import org.springframework.context.ApplicationContext;

public class MainController {
	
	private ApplicationContext applicationContext;
	
	@FXML
	private void open() throws IOException {
		System.out.println("open");
		
		Stage stage = new Stage();
		
		// load BeanLifecycle
		FXMLLoader loader = LifecycleController.getLoader();
		Parent beanLifecycleNode = loader.load();
		LifecycleController fifecycleController = (LifecycleController) loader.getController();
		
		PersonPM personPM = applicationContext.getBean(PersonPM.class);
		personPM.initialize();

		fifecycleController.setNode(personPM.getRootNode());
		fifecycleController.setPresentationManager(personPM);
		
		Scene scene = new Scene( beanLifecycleNode );
		stage.setScene(scene);
		stage.show();

	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
	
}
