package com.project.computerTemperature.exceptions;

public class TemperatureExceptions implements Runnable{

	@Override
	public void run() {
		throw new UnsupportedOperationException("Comando não suportado");
	}

}
