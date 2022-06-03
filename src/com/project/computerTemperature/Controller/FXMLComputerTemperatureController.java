package com.project.computerTemperature.Controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import org.graalvm.compiler.core.amd64.AMD64ArithmeticLIRGenerator.Maths;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.ScatterChart;

public class FXMLComputerTemperatureController implements Initializable {

	@FXML
	private ScatterChart<?, ?> lineChart;

	@Override
	public void initialize(URL arg0, ResourceBundle rb) {
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
					Maths m = p.matcher(line);
					System.out.println(line);
				}
				Thread.sleep(1000);
			}

		} catch (Exception ex) {
			Logger.getLogger(FXMLComputerTemperatureController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}