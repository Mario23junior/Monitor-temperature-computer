package com.project.computerTemperature.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.ScatterChart;

public class FXMLComputerTemperatureController implements Initializable {

	@FXML
	private ScatterChart<?, ?> lineChart;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}
}

class TemperaureReader implements Runnable {
	@Override
	public void run() {
		try {
			Process proc = Runtime.getRuntime().exec("sensors");
       
		} catch (Exception ex) {
			Logger.getLogger(FXMLComputerTemperatureController.class.getName()).log(Level.SEVERE,null,ex);
 		}
	}

}