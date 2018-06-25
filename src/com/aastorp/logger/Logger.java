package com.aastorp.logger;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;

import com.aastorp.linguistics.Linguist;
import com.aastorp.linguistics.Padder;

// TODO: Auto-generated Javadoc
/**
 * Logger - logs formatted messages to console and optionally a file, respecting the specified log level
 */
public class Logger {
	
	/** The Constant SILENT. */
	public static final int SILENT = 0;
	
	/** The Constant ERROR. */
	public static final int ERROR = 1;
	
	/** The Constant WARNING. */
	public static final int WARNING = 2;
	
	/** The Constant DEBUG. */
	public static final int DEBUG = 3;
	
	/** The Constant INFO. */
	public static final int INFO = 4;
	
	/** The Constant VERSION. */
	public static final String VERSION = "0.2.5";
	
	/** The instantiating class, the class of the object that instantiated the logger. */
	private Class<?> instantiatingClass;
	
	/** The available log levels */
	private HashMap<Integer, String> logLevels = new HashMap<Integer, String>();
	
	/** The log level. */
	private int logLevel;
	
	/** Whether the logger should log to a file or not. */
	private boolean logToFile;
	
	/** The log file. */
	private File logFile;
	
	/** The com.aastorp.logger.Format that defines how to output log messages. */
	private Format format;
	
	/**
	 * Instantiates a new logger.
	 *
	 * @param instantiatingClass The class of the object instantiating the logger.
	 * @param logLevel The log level this logger will log at.
	 */
	public Logger(Class<?> instantiatingClass, int logLevel) {
		this(instantiatingClass, logLevel, false, null, new Format(": ", " :: ", 7, 14, 20));
	}
	
	/**
	 * Instantiates a new logger.
	 *
	 * @param instantiatingClass The class of the object instantiating the logger.
	 * @param logLevel The log level this logger will log at.
	 * @param logToFile Whether the logger should log to a file or not. 
	 * @param logFile The log file
	 */
	public Logger(Class<?> instantiatingClass, int logLevel, boolean logToFile, File logFile) {
		this(instantiatingClass, logLevel, logToFile, logFile, new Format(": ", " :: ", 7, 14, 20));
	}
	
	/**
	 * Instantiates a new logger.
	 *
	 * @param instantiatingClass The class of the object instantiating the logger.
	 * @param logLevel The log level
	 * @param logToFile Whether the logger should log to a file or not. 
	 * @param logFile The log file
	 * @param format The com.aastorp.logger.Format that defines how to output log messages.
	 */
	public Logger(Class<?> instantiatingClass, int logLevel, boolean logToFile, File logFile, Format format) {
		this.instantiatingClass = instantiatingClass;
		this.setLogLevel(logLevel);
		this.setLogToFile(logToFile);
		this.setLogFile(logFile);
		this.setFormat(format);
		this.logLevels.put(0, "SILENT");
		this.logLevels.put(1, "ERROR");
		this.logLevels.put(2, "WARNING");
		this.logLevels.put(3, "DEBUG");
		this.logLevels.put(4, "INFO");
		
	}
	
	public void header(String h) {
		this.header(h, Logger.ERROR);
	}
	
	public void header(String h, int logLevel) {
		final String F = "header";
		String formattedHeader;
		PrintStream console = System.out;
		Linguist padder;
		StringBuilder sb = new StringBuilder(h.length() + 6);
		if (logLevel == Logger.ERROR) {
			console = System.err;
		}
		padder = new Padder("", "center", h.length() + 4, "-");
		sb.append("/");
		sb.append(padder.work());
		sb.append("\\\r\n");
		
		padder = new Padder(h, "center", h.length() + 4);
		sb.append("|");
		sb.append((String)padder.work());
		sb.append("|");
		
		padder = new Padder("", "center", h.length() + 4, "-");
		sb.append("\r\n\\");
		sb.append(padder.work());
		sb.append("/");
		
		formattedHeader = sb.toString();
		
		console.println(formattedHeader);
		
		if (this.isLogToFile()) {
			try {
				FileUtils.writeStringToFile(this.getLogFile(), formattedHeader, "UTF-8", true);
			} catch (IOException e) {
				this.e(F, "Could not write header to file " + this.getLogLevel() + ": " + e.getMessage());
			}
		}
		
	}
	
	public void e(String f, String m) {
		OutputWorker ow = new OutputWorker(this, Logger.ERROR, f, m, this.getLogFile());
		ow.run();
	}
	public void d(String f, String m) {
		OutputWorker ow = new OutputWorker(this, Logger.DEBUG, f, m, this.getLogFile());
		ow.run();
	}
	public void i(String f, String m) {
		OutputWorker ow = new OutputWorker(this, Logger.INFO, f, m, this.getLogFile());
		ow.run();
	}
	public void w(String f, String m) {
		OutputWorker ow = new OutputWorker(this, Logger.WARNING, f, m, this.getLogFile());
		ow.run();
	}
	public void l(String f, String m, int l) {
		OutputWorker ow = new OutputWorker(this, l, f, m, this.getLogFile());
		ow.run();
	}

	/**
	 * @return the logLevel
	 */
	public int getLogLevel() {
		return logLevel;
	}

	/**
	 * @param logLevel the logLevel to set
	 */
	public void setLogLevel(int logLevel) {
		this.logLevel = logLevel;
	}

	/**
	 * @return the logToFile
	 */
	public boolean isLogToFile() {
		return logToFile;
	}

	/**
	 * @param logToFile the logToFile to set
	 */
	public void setLogToFile(boolean logToFile) {
		this.logToFile = logToFile;
	}

	/**
	 * @return the logFile
	 */
	public File getLogFile() {
		return logFile;
	}

	/**
	 * @param logFile the logFile to set
	 */
	public void setLogFile(File logFile) {
		this.logFile = logFile;
	}

	/**
	 * @return the format
	 */
	public Format getFormat() {
		return format;
	}

	/**
	 * @param format the format to set
	 */
	public void setFormat(Format format) {
		this.format = format;
	}

	/**
	 * @return the instantiatingClass
	 */
	public Class<?> getInstantiatingClass() {
		return instantiatingClass;
	}

	public HashMap<Integer, String> getLogLevels() {
		return this.logLevels;
	}
}