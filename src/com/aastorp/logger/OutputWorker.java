package com.aastorp.logger;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;

import org.apache.commons.io.FileUtils;

public class OutputWorker implements Runnable {
	private Logger l;
	private int logLevel;
	private String function;
	private String message;
	private File logFile;
	
	public OutputWorker(Logger l, int logLevel, String function, String message, File logFile) {
		this.l = l;
		this.logLevel = logLevel;
		this.function = function;
		this.message = message;
		this.logFile = logFile;
	}
	
	@Override
	public void run() {
		if (!(l.getLogLevel() >= this.logLevel) && this.logLevel != Logger.ALWAYS) {
			//The log level of this message is too low to log.
			return;
		}
		Date d = new Date();
		PrintStream console = System.out;
		if (this.logLevel == Logger.ERROR) {
			console = System.err;
		}
		String formattedMessage = l.getFormat().format(d, l.getLogLevels(), this.logLevel, l.getInstantiatingClass().getSimpleName(), this.function, this.message);
		printToConsole(console, formattedMessage);
		printToFile(formattedMessage);
	}

	private void printToFile(String formattedMessage) {
		if (this.logFile != null) {
			try {
				FileUtils.writeStringToFile(this.logFile, formattedMessage + "\r\n", "utf-8", true);
			} catch (IOException e) {
				l.e("run", e);
			}
		}
	}

	private void printToConsole(PrintStream console, String formattedMessage) {
		try {
			console.println(formattedMessage);
		} catch (Exception e) {
			l.e("printToConsole", e);
		}
	}

}
