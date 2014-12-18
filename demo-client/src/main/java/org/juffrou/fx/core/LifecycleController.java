package org.juffrou.fx.core;

import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class LifecycleController {
	
	private LifecyclePresentationManager presentationManager;
	
	@FXML
	private AnchorPane nodeContainer;

	public static FXMLLoader getLoader() {
		URL url = LifecycleController.class.getResource("/org/juffrou/fx/core/BeanLifecycle.fxml");
		FXMLLoader loader = new FXMLLoader(url);
		return loader;
	}
	
	@FXML
	private void save() {
		System.out.println("save");
		presentationManager.save();
	}
	
	@FXML
	private void cancel() {
		System.out.println("cancel");
		presentationManager.cancel();
	}

	public LifecyclePresentationManager getPresentationManager() {
		return presentationManager;
	}

	public void setPresentationManager(LifecyclePresentationManager presentationManager) {
		this.presentationManager = presentationManager;
	}

	public void setNode(Node node) {
		nodeContainer.getChildren().add(node);
	}
}
