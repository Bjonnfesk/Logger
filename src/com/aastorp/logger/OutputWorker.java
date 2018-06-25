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
	private String text;
	
	public OutputWorker(Logger l, int logLevel, String function, String text) {
		this.l = l;
		this.logLevel = logLevel;
		this.function = function;
		this.text = text;
	}
	
	@Override
	public void run() {
		Date d = new Date();
		PrintStream outputStream = System.out;
		if (this.logLevel == 1) {
			outputStream = System.err;
		}
		String formattedOutput = l.getFormat().format(d, Logger.getLogLevels(), this.logLevel, l.getCl(), this.function, this.text);
		try {
			outputStream.println(formattedOutput);
		} catch (Exception e) {
			l.e("doInBackground", e.getClass().getSimpleName() + "::" + e.getMessage());
		}
		try {
			FileUtils.writeStringToFile(new File("praefectusbiviorumdirectoriorum.log"), formattedOutput + "\r\n", "utf-8", true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
