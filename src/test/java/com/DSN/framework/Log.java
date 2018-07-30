package com.DSN.framework;

import java.util.ArrayList;

import org.apache.log4j.Logger;

public class Log extends Logger {

	protected Log(String name) {
		super(name);
	}

	public static ArrayList<String> sessionLog = new ArrayList<>();

	public static void info(String message) {
		sessionLog.add(message);
		String name = Commons.getCallerClassName();
		Logger logg = Logger.getLogger(name);
		logg.info(message + "\n");
	}

}
