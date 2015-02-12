package br.com.lopes.application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.lopes.application.MainApp;

public class MenuController implements Initializable{

	private static final Logger log = LoggerFactory.getLogger(MenuController.class);
	
	

	public void carregarMenu(){
		log.info("Starting Menu");
        
        String fxmlFile = "/fxml/Menu.fxml";
        log.debug("Loading FXML for main view from: {}", fxmlFile);
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode;
		
        try {
			
			rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
			
	        log.debug("Showing JFX scene");
	        Scene scene = new Scene(rootNode);
	        
			MainApp.SCENE = scene;			
			
			
		} catch (IOException e) {
			log.error(e.getMessage());			
		}
	}
	
	
	public void initialize(URL location, ResourceBundle resources) {

		
	}
}
