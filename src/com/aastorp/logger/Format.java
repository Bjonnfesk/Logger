package com.aastorp.logger;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;

import com.aastorp.linguistics.Linguist;
import com.aastorp.linguistics.Padder;

// TODO: Auto-generated Javadoc
/**
 * The Class Format.
 */
public class Format {
	
	/** The log level length. */
	private int logLevelLength;
	
	/** The class length. */
	private int classLength;
	
	/** The function length. */
	private int functionLength;
	
	/** The format string. */
	private String formatString;

	/**
	 * Instantiates a new format.
	 *
	 * @param beforeLogLevel the before log level
	 * @param afterLogLevel the after log level
	 * @param logLevelLength the log level length
	 * @param classLength the class length
	 * @param functionLength the function length
	 */
	public Format(String beforeLogLevel, String afterLogLevel, int logLevelLength, int classLength, int functionLength) {
		StringBuilder sb = new StringBuilder();
		
		this.setLogLevelLength(logLevelLength);
		this.setClassLength(classLength);
		this.setFunctionLength(functionLength);
		
		sb.append("(%s)");
		sb.append(beforeLogLevel);
		sb.append("%s");
		sb.append(afterLogLevel);
		sb.append("%s");
		sb.append(" -> ");
		sb.append("%s");
		sb.append(": ");
		sb.append("%s");

		this.setFormatString(sb.toString());
	}

	/**
	 * Format.
	 *
	 * @param d A Date that should represent the current time, or the time the log request was made
	 * @param lls A HashMap with the available log levels
	 * @param ll An int specifying the current logLevel
	 * @param c The class name that will be logged
	 * @param f The function name that will be logged
	 * @param t The text message that will be logged
	 * @return the string
	 */
	public String format(Date d, HashMap<Integer, String> lls, int ll, String c, String f, String t) {
		String formattedDate = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).format(d);
		Padder padder = new Padder(lls.get(ll), "centre", this.logLevelLength);
		String formattedLogLevel = (String)padder.work();
		padder.setWidth(this.classLength);
		padder.setPadSide("right");
		padder.setUnmodifiedString(c);
		String formattedClassName = (String)padder.work();
		padder.setWidth(this.functionLength);
		padder.setPadSide("left");
		padder.setUnmodifiedString(f + (f.length() != 0 ? "()" : "")); //add the function suffix if f is non-empty
		String formattedFunctionName = (String)padder.work();
		
		return String.format(this.getFormatString(), formattedDate, formattedLogLevel, formattedClassName, formattedFunctionName, t);
	}
	
	/**
	 * @return The logLevelLength of the Format
	 */
	private int getLogLevelLength() {
		final String F = "getLogLevelLength";
		return logLevelLength;
	}

	/**
	 * @param logLevelLength The logLevelLength of the Format to set
	 */
	private void setLogLevelLength(int logLevelLength) {
		final String F = "setLogLevelLength";
		this.logLevelLength = logLevelLength;
	}

	/**
	 * @return The classLength of the Format
	 */
	private int getClassLength() {
		final String F = "getClassLength";
		return classLength;
	}

	/**
	 * @param classLength The classLength of the Format to set
	 */
	private void setClassLength(int classLength) {
		final String F = "setClassLength";
		this.classLength = classLength;
	}

	/**
	 * @return The functionLength of the Format
	 */
	private int getFunctionLength() {
		final String F = "getFunctionLength";
		return functionLength;
	}

	/**
	 * @param functionLength The functionLength of the Format to set
	 */
	private void setFunctionLength(int functionLength) {
		final String F = "setFunctionLength";
		this.functionLength = functionLength;
	}

	/**
	 * @return The formatString of the Format
	 */
	private String getFormatString() {
		final String F = "getFormatString";
		return formatString;
	}

	/**
	 * @param formatString The formatString of the Format to set
	 */
	private void setFormatString(String formatString) {
		final String F = "setFormatString";
		this.formatString = formatString;
	}

}