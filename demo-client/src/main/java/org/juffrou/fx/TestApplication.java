package org.juffrou.fx;

import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestApplication extends Application {
	
	static final Logger logger = LogManager.getLogger(TestApplication.class.getName());

    private static final String SERVER_URL_PARAMETER="server-url";
    private static final String SERVICES_URL="SERVICES_URL";
    private static final String SERVER_URL="http://localhost:8080/fx-framework-demo/remoting";
    
    private ApplicationContext context = null;
	
	public static void main(String[] args) {
		
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		URL url = getClass().getResource("/org/juffrou/fx/Main.fxml");
		
		FXMLLoader loader = new FXMLLoader(url);
		Parent parent = loader.load();
		
		MainController mainController = loader.getController();
		mainController.setApplicationContext(context);
		
		Scene scene = new Scene( parent);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private URI exportServerEndpointAddress() {
		String serverEndpointStr = SERVER_URL;
		Parameters parameters = getParameters();
		if(parameters != null && parameters.getNamed().get(SERVER_URL_PARAMETER) != null) {
			serverEndpointStr = parameters.getNamed().get(SERVER_URL_PARAMETER);
			serverEndpointStr = serverEndpointStr + "/remoting";
			logger.debug("Calculated Server Endpoint Address: " + serverEndpointStr);
		}
		System.setProperty(SERVICES_URL, serverEndpointStr);
		return URI.create(serverEndpointStr);
	}

	
	@SuppressWarnings("restriction")
	@Override
	public void init() throws Exception {
		super.init();
		logger.debug("Reached init");
		Parameters parameters = getParameters();
		if(parameters != null) {
			
			logger.debug("Parameters:");
			
			Map<String, String> named = parameters.getNamed();
			if(named != null) {
				logger.debug("  Named: " + named.size());
				for(Entry<String, String> entry : named.entrySet()) {
					logger.debug("    " + entry.getKey() + "= " + entry.getValue());
				}
			}
			else
				logger.debug("  Named: NULL");
			
			List<String> unnamed = parameters.getUnnamed();
			if(unnamed != null) {
				logger.debug("  unnamed: " + unnamed.size());
				for(String param : unnamed) {
					logger.debug("    " + param);
				}
			}
			else
				logger.debug("  unnamed: NULL");
			
			List<String> raw = parameters.getRaw();
			if(raw != null) {
				logger.debug("  raw: " + raw.size());
				for(String param : raw) {
					logger.debug("    " + param);
				}
			}
			else
				logger.debug("  raw: NULL");
		}
		
		exportServerEndpointAddress();
		context = new ClassPathXmlApplicationContext("business-sil.xml", "business-pm.xml");

	}

	@Override
	public void stop() throws Exception {
		logger.debug("Reached stop");
		super.stop();
	}

}
