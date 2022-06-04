package com.project.computerTemperature;

import java.beans.EventHandler;
 
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainComputerTemperature extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/FXML/FXMLComputerTemperature.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		stage.setOnCloseRequest(new EventHandler(windowEvent() {
			public void handle(WindowEvent event) {
				System.exit(0);
			}
		});
 	}
	 
	public static void main(String[] args) {
		launch(args);
	}

}
