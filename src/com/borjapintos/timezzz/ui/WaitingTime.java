/*
 * 02-abr-2017: WaitingTime.java
 * Author: Borja Pintos Castro - borjapintoscastro@gmail.com
 */

package com.borjapintos.timezzz.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.borjapintos.timezzz.model.Executor;
import com.borjapintos.timezzz.util.I18nKeys;
import com.borjapintos.timezzz.util.I18nManager;

/**
 * The Class WaitingTime.
 *
 * @author Borja Pintos Castro
 */
public class WaitingTime extends javax.swing.JFrame implements Runnable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4561320572412531063L;
	
	/** The Constant ONE_MINUTE_IN_MS. */
	private static final int ONE_MINUTE_IN_MS = 1000*60;
	
	/** The j button action. */
	private JButton jButtonAction = new JButton();
	
	/** The j title. */
	private JLabel jTitle = new JLabel();
    
    /** The author label. */
    private JLabel authorLabel = new JLabel();
    
    /** The j hours label. */
    private JLabel jHoursLabel = new JLabel();
    
    /** The j hours text. */
    private JLabel jHoursText = new JLabel();
    
    /** The j minutes label. */
    private JLabel jMinutesLabel = new JLabel();
    
    /** The j minutes text. */
    private JLabel jMinutesText = new JLabel();
    
    /** The user cancel. */
    private boolean userCancel = false;
    
    /** The hours. */
    private Integer hours;
    
    /** The minutes. */
    private Integer minutes;
    
    /** The miliseconds. */
    private Integer miliseconds;
    
    /** The origin dialog. */
    private JDialog originDialog;
    
    /** The me. */
    private Thread me;

    /**
     * Instantiates a new waiting time.
     *
     * @param minutes the minutes
     * @param originDialog the origin dialog
     */
    public WaitingTime(Integer minutes, JDialog originDialog) {
        this.originDialog = originDialog;
        this.hours = (minutes / 60);
        this.minutes = minutes - (hours*60);
        this.miliseconds = minutes*ONE_MINUTE_IN_MS;
        initComponents();
        me = new Thread(this);
        me.start();
    }

    /**
     * Waiting.
     */
    private void waiting() {
		while (!userCancel && miliseconds>0){
	        try {
	            Thread.sleep(ONE_MINUTE_IN_MS);
	            miliseconds-=ONE_MINUTE_IN_MS;
	            substract1Minute();
	            updateLabels();
	            if (miliseconds<=ONE_MINUTE_IN_MS){
	            	this.setAlwaysOnTop(true);
	            }
	        } catch(InterruptedException ex) {
	            Thread.currentThread().interrupt();
	        }
		}
		
		if (userCancel){
			returnToSelectorTime();
		} else {
			Executor.shutdown();
			System.exit(0);
		}
		
	}


	/**
	 * Return to selector time.
	 */
	private void returnToSelectorTime() {
		originDialog.setVisible(true);
		this.dispose();
	}


	/**
	 * Update labels.
	 */
	private void updateLabels() {
        jHoursText.setText(hours.toString());
        jMinutesText.setText(minutes.toString());
	}


	/**
	 * Substract 1 minute.
	 */
	private void substract1Minute() {
		this.minutes--;
		if (this.minutes<0){
			this.hours--;
			this.minutes = 59;
		}
	}


	/**
	 * Inits the components.
	 */
	private void initComponents() {

    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	initLabels();
    	initJButtonAction();
    	
        GroupLayout layout = new GroupLayout(getContentPane());
        this.getContentPane().setLayout(layout);
        
        layout.setHorizontalGroup(
        	layout.createSequentialGroup().addGroup(           
        			layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        			.addGroup(layout.createSequentialGroup()
        					.addGap(39, 39, 39)
        					.addComponent(jTitle))
                    .addGroup(layout.createSequentialGroup()
                    		.addGap(39, 39, 39)
                            .addComponent(jHoursLabel)
                            .addGap(75, 75, 75)
                    		.addComponent(jMinutesLabel))
                    .addGroup(layout.createSequentialGroup()
                    		.addGap(39, 39, 39)
                            .addComponent(jHoursText)
                            .addGap(100, 100, 100)
                            .addComponent(jMinutesText))
                    .addGroup(layout.createSequentialGroup()
	                    .addGap(39, 39, 39)
	                    .addComponent(jButtonAction))
            		.addGroup(layout.createSequentialGroup()
                    .addComponent(authorLabel)))
                    .addContainerGap(50, Short.MAX_VALUE)
        );
        
        
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jTitle)
                .addContainerGap(39, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                .addComponent(jHoursLabel)
	                .addComponent(jMinutesLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                .addComponent(jHoursText)
	                .addComponent(jMinutesText))
                .addGap(39, 39, 39)
                .addComponent(jButtonAction)
                .addGap(39, 39, 39)
                .addComponent(authorLabel)
        );

        pack();
    }

    
    /**
     * Inits the labels.
     */
    private void initLabels(){
    	jTitle.setText(I18nManager.getValue(I18nKeys.TIMEZZZ_WAITINGTIME_LABELTITLE));
    	authorLabel.setText(I18nManager.getValue(I18nKeys.TIMEZZZ_AUTHOR));
        jHoursLabel.setText(I18nManager.getValue(I18nKeys.TIMEZZZ_HOURS));
        jHoursText.setText(hours.toString());
        jMinutesLabel.setText(I18nManager.getValue(I18nKeys.TIMEZZZ_MINUTES));
        jMinutesText.setText(minutes.toString());
        
    }


    
	/**
	 * Inits the J button action.
	 */
	private void initJButtonAction() {
        jButtonAction.setText(I18nManager.getValue(I18nKeys.TIMEZZZ_WAITINGTIME_BUTTONACTION));
        jButtonAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButtonActionClicked(evt);
            }
        });
	}

	/**
	 * J button action clicked.
	 *
	 * @param evt the evt
	 */
	private void jButtonActionClicked(ActionEvent evt) {
		userCancel = true;
		me.interrupt();
    }

	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		this.waiting();
	}


}
