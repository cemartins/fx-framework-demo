package org.juffrou.fx.core;

import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;

public class LifecycleController {
	
	private LifecyclePresentationManager presentationManager;
	
	@FXML
	private AnchorPane nodeContainer;
	
	@FXML
	private ToggleButton searchButton;
	@FXML
	private ToggleButton saveButton;

	public static FXMLLoader getLoader() {
		URL url = LifecycleController.class.getResource("/org/juffrou/fx/core/BeanLifecycle.fxml");
		FXMLLoader loader = new FXMLLoader(url);
		return loader;
	}
	
	@FXML
	private void save() {
		Object lifecycleModelSource = presentationManager.getLifecycleModelSource();
		if(lifecycleModelSource == null) {
			presentationManager.createNewTransient();;
			saveButton.setText("Create New");
		}
		else {
			System.out.println("save");
			presentationManager.save();
			saveButton.setText("Save");
		}
	}
	
	@FXML
	private void cancel() {
		System.out.println("cancel");
		presentationManager.cancel();
	}
	
	@FXML
	private void search() {
		if(searchButton.isSelected()) {
			presentationManager.createNewTransient();;
			searchButton.setText("Execute Search");
		}
		else {
			presentationManager.search();
			searchButton.setText("Search");
		}
	}

	public LifecyclePresentationManager getPresentationManager() {
		return presentationManager;
	}

	public void setPresentationManager(LifecyclePresentationManager presentationManager) {
		this.presentationManager = presentationManager;
	}

	public void setNode(Node node) {
		nodeContainer.getChildren().add(node);
		nodeContainer.setTopAnchor(node, 0.0);
		nodeContainer.setBottomAnchor(node, 0.0);
		nodeContainer.setLeftAnchor(node, 0.0);
		nodeContainer.setRightAnchor(node, 0.0);
	}
}
