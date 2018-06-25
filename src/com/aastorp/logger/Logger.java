import java.io.File;

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
}