package com.project.computerTemperature.Controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

public class FXMLComputerTemperatureController implements Initializable {

	@FXML
	private ScatterChart<Number, Number> lineChart;
	XYChart.Series<Number, Number> series, series1,series2;

	@Override
	public void initialize(URL arg0, ResourceBundle rb) {
		series = new XYChart.Series<>();
		series.setName("Data 1");
		series = new XYChart.Series<>();
		series.setName("Data 2");
		series = new XYChart.Series<>();
		series.setName("Data 3");
		
		Thread th = new Thread(new TemperaureReader());
		th.start();

	}
}

class TemperaureReader implements Runnable {
	@Override
	public void run() {
		try {

			while (true) {
				
				Pattern p = Pattern.compile("[+]...");
	
				Process proc = Runtime.getRuntime().exec("sensors");
				BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
				
				String line = " ";
				while ((line = reader.readLine()) != null) {
					Matcher m = p.matcher(line);
					if(m.find()) {
						System.out.println("Matematica erro = "+ m.group());
					}
					
					System.out.println(line);
				}
				System.out.println("-");
				Thread.sleep(2000);
			}

		} catch (Exception ex) {
			Logger.getLogger(FXMLComputerTemperatureController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}