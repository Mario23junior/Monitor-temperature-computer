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
	private ScatterChart<String, Number> lineChart;
	XYChart.Series<String, Number> series, series1, series2, series3;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		series = new XYChart.Series<>();
		series.setName("Data 0");
		series1 = new XYChart.Series<>();
		series1.setName("Data 1");
		series2 = new XYChart.Series<>();
		series2.setName("Data 2");
		series3 = new XYChart.Series<>();
		series3.setName("Data 3");

		lineChart.getData().add(series);
		lineChart.getData().add(series1);
		lineChart.getData().add(series2);
		lineChart.getData().add(series3);

		
		Thread th = new Thread(new TemperaureReader());
		th.start();

	}

	class TemperaureReader implements Runnable {

		@Override
		public void run() {
			try {
				int ctr = 1;
				while (true) {

					Pattern p = Pattern.compile("[+].......");

					Process proc = Runtime.getRuntime().exec("sensors");
					BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));

					String line = "";
					int coreNumber = 0;

					while ((line = reader.readLine()) != null) {
						Matcher m = p.matcher(line);
						if (m.find()) {
							System.out.println("Match found = " + m.group().substring(1));
							double temp = Double.parseDouble(m.group().substring(1));

							switch (coreNumber++) {
								case 0:
									series.getData().add(new XYChart.Data<>(ctr + "", temp));
									break;

								case 1:
									series1.getData().add(new XYChart.Data<>(ctr + "", temp));
									break;

								case 2:
									series2.getData().add(new XYChart.Data<>(ctr + "", temp));
									break;

								case 3:
									series3.getData().add(new XYChart.Data<>(ctr + "", temp));
									break;
							}
						}
					}
					ctr++;
					System.out.println("---------------------");
					Thread.sleep(100);
				}
			} catch (Exception ex) {
				Logger.getLogger(FXMLComputerTemperatureController.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

}