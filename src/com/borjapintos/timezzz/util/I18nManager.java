/*
 * 30-ago-2016: I18nManager.java
 */
package com.borjapintos.timezzz.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The Class I18nManager.
 *
 * @author Borja Pintos
 */
public class I18nManager { 
	
    private static Locale currentLocale = null;
    private static ResourceBundle messages = null;

	private static void init(){
		final String language = System.getProperty("user.country"); 
		final String country = System.getProperty("user.language");
	    currentLocale = new Locale(language, country);
	    messages = ResourceBundle.getBundle("Timezzz", currentLocale);
	}
	
	/**
	 * Gets the value.
	 *
	 * @param key the key
	 * @return the value
	 */
	public static String getValue(String key){
		if (messages == null){
			init();
		}
		return messages.getString(key);
	}
}
