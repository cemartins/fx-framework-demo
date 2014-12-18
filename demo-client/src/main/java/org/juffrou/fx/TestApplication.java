package org.juffrou.fx;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestApplication extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		URL url = getClass().getResource("/org/juffrou/fx/Main.fxml");
		Parent parent = FXMLLoader.load(url);
		
		Scene scene = new Scene( parent);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
