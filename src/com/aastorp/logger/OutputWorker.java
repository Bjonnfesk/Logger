package com.aastorp.logger;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;

import javax.swing.SwingWorker;

import org.apache.commons.io.FileUtils;

public class OutputWorker implements Runnable {
	private Logger l;
	private int logLevel;
	private String function;
	private String message;
	
	public OutputWorker(Logger l, int logLevel, String function, String message) {
		this.l = l;
		this.logLevel = logLevel;
		this.function = function;
		this.message = message;
	}
	
	@Override
	public void run() {
		Date d = new Date();
		PrintStream console = System.out;
		if (this.logLevel == 1) {
			console = System.err;
		}
		String formattedMessage = l.getFormat().format(d, l.getLogLevels(), this.logLevel, l.getInstantiatingClass().getSimpleName(), this.function, this.message);
		try {
			console.println(formattedMessage);
		} catch (Exception e) {
			l.e("doInBackground", e.getClass().getSimpleName() + "::" + e.getMessage());
		}
		try {
			FileUtils.writeStringToFile(new File("praefectusbiviorumdirectoriorum.log"), formattedMessage + "\r\n", "utf-8", true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
