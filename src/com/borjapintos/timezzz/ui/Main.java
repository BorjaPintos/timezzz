/*
 * 02-abr-2017: Main.java
 * Author: Borja Pintos Castro - borjapintoscastro@gmail.com
 */

package com.borjapintos.timezzz.ui;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.borjapintos.timezzz.util.I18nKeys;
import com.borjapintos.timezzz.util.I18nManager;

/**
 * The Class Main.
 *
 * @author Borja Pintos Castro
 */
public class Main {

	/** The img. */
	public static BufferedImage img = loadImage("/timezzz.png");
	
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SelectorTime dialog = new SelectorTime(new JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setIconImage(img);
                dialog.setTitle(I18nManager.getValue(I18nKeys.TIMEZZZ_TITLE));
                dialog.setVisible(true);
            }
        });
	}
	
	 /**
 	 * Load image.
 	 *
 	 * @param fileName the file name
 	 * @return the buffered image
 	 */
 	public static BufferedImage loadImage(String fileName){

		    BufferedImage buff = null;
		    try {
		    	InputStream input = Main.class.getClass().getResourceAsStream(fileName);
		    	if (input != null){
		    		buff = ImageIO.read(input);
		    	}
		    } catch (IOException e) {
		        return null;
		    }
		    return buff;

		}
}
