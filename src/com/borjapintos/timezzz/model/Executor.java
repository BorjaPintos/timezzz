/*
 * 02-abr-2017: Executor.java
 * Author: Borja Pintos Castro - borjapintoscastro@gmail.com
 */
package com.borjapintos.timezzz.model;

import java.io.IOException;

import com.borjapintos.timezzz.util.I18nKeys;
import com.borjapintos.timezzz.util.I18nManager;


/**
 * The Class Executor.
 *
 * @author Borja Pintos Castro
 */
public class Executor {


	/**
	 * Shutdown.
	 */
	public static void shutdown() {
	  String shutdownCommand;
	    String operatingSystem = System.getProperty("os.name");

	    if (operatingSystem == null){
	    	throw new RuntimeException(I18nManager.getValue(I18nKeys.TIMEZZZ_EXECUTOR_SHUTDOWN_UNSOPORTED));
	    }
	    if (operatingSystem.contains("Linux") || operatingSystem.contains("Mac OS X")) {
	        shutdownCommand = "shutdown -h now";
	    }
	    else if (operatingSystem.contains("Windows")) {
	        shutdownCommand = "shutdown.exe -s -t 0";
	    }
	    else {
	        throw new RuntimeException(I18nManager.getValue(I18nKeys.TIMEZZZ_EXECUTOR_SHUTDOWN_UNSOPORTED));
	    }

	    try {
			Runtime.getRuntime().exec(shutdownCommand);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    System.exit(0);
	}


}

