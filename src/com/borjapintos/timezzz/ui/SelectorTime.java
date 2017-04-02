/*
 * 02-abr-2017: SelectorTime.java
 * Author: Borja Pintos Castro - borjapintoscastro@gmail.com
 */

package com.borjapintos.timezzz.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import com.borjapintos.timezzz.util.I18nKeys;
import com.borjapintos.timezzz.util.I18nManager;

/**
 * The Class SelectorTime.
 *
 * @author Borja Pintos Castro
 */
public class SelectorTime extends JDialog {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7903113463871088226L;
    
    /** The j button action. */
    private JButton jButtonAction = new JButton();
    
    /** The j title. */
    private JLabel jTitle = new JLabel();
    
    /** The j hours label. */
    private JLabel jHoursLabel = new JLabel();
    
    /** The j hours selector. */
    private JComboBox<Integer> jHoursSelector;
    
    /** The j minutes label. */
    private JLabel jMinutesLabel = new JLabel();
    
    /** The j minutes selector. */
    private JComboBox<Integer> jMinutesSelector;
    
    /** The author label. */
    private JLabel authorLabel = new JLabel();

    /**
     * Instantiates a new selector time.
     *
     * @param parent the parent
     * @param modal the modal
     */
    public SelectorTime(JFrame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * Inits the components.
     */
    private void initComponents() {
    	
    	initJTitle();
    	initJHoursSelector();
    	initJMinutesSelector();
    	initJButtonAction();
    	initAuthor();
    	setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    	
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
       
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
                            .addComponent(jHoursSelector)
                            .addGap(39, 39, 39)
                            .addComponent(jMinutesSelector))
                    
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
	                .addComponent(jHoursSelector)
	                .addComponent(jMinutesSelector))
                
                .addGap(39, 39, 39)
                .addComponent(jButtonAction)
                
                .addGap(39, 39, 39)
                .addComponent(authorLabel)
        );
        pack();
    }

	/**
	 * Inits the J title.
	 */
	private void initJTitle(){
    	jTitle.setText(I18nManager.getValue(I18nKeys.TIMEZZZ_SELECTORTIME_LABELTITLE));
    }
    
    /**
     * Inits the J hours selector.
     */
    private void initJHoursSelector() {
    	jHoursLabel.setText(I18nManager.getValue(I18nKeys.TIMEZZZ_HOURS));
    	Integer[] hours = new Integer[24];
    	for (int i=0;i<24;i++){
    		hours[i]=i;
    	}
    	jHoursSelector = new JComboBox<Integer>(hours);
    	jHoursSelector.setSelectedItem(1);
	}
    
    /**
     * Inits the J minutes selector.
     */
    private void initJMinutesSelector() {
    	jMinutesLabel.setText(I18nManager.getValue(I18nKeys.TIMEZZZ_MINUTES));
    	Integer[] minutes = new Integer[60];
    	for (int i=0;i<60;i++){
    		minutes[i]=i;
    	}
    	jMinutesSelector = new JComboBox<Integer>(minutes);
	}
    
    /**
     * Inits the author.
     */
    private void initAuthor() {
    	authorLabel.setText(I18nManager.getValue(I18nKeys.TIMEZZZ_AUTHOR));
	}
    
	/**
	 * Inits the J button action.
	 */
	private void initJButtonAction() {
        jButtonAction.setText(I18nManager.getValue(I18nKeys.TIMEZZZ_SELECTORTIME_BUTTONACTION));
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
		int totalMinutesTime = 
				((Integer) jHoursSelector.getSelectedItem() * 60)
				+ (Integer) jMinutesSelector.getSelectedItem();
		this.setVisible(false);
		WaitingTime waiting = new WaitingTime(totalMinutesTime, this);
		if (this.getIconImages() != null && !this.getIconImages().isEmpty()){
			waiting.setIconImage(this.getIconImages().get(0));
		}
		waiting.setTitle(this.getTitle());
		
		waiting.setVisible(true);
        
    }
	
	

}
