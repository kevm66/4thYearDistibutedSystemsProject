package clientui;

import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.JComboBox;
import javax.swing.JButton;
import client.OvenClient;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/*
 * @OvenUI.java							
 *
 * @author:Karolina Laptas, x14446332
 *
 * @reference sample by Dominic Carr https://moodle.ncirl.ie/course/view.php?id=1473	
 */

public class OvenUI extends ClientUI {

    private static final long serialVersionUID = -5318589393275157185L;
    private JButton warm;
    private JButton cool;
    private JButton turnOn;
    private JButton turnOff;
    private JButton reset;
    private JButton set;
    private JToggleButton tapToggle;
    private JMenu type;
    private JMenuItem i1,i2,i3,i4,i5;
    
   // private JToggleButton toggle;
    private final OvenClient parent;

    public OvenUI(OvenClient ovenClient) {
        super(ovenClient);
        parent = ovenClient;
        init();
    }

    @Override
    public void init() {
        super.init();
       // toggle = new JToggleButton(UIConstants.LIGHTS_LABEL, true);
      //  scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
      //  add(toggle);
      
        warm = new JButton(" + TEMP");
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        warm.setEnabled(false);
        add(new JButton[]{warm});

        cool = new JButton(" - TEMP");
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        cool.setEnabled(false);
        add(new JButton[]{cool});

        turnOff = new JButton("Power OFF");
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        turnOff.setEnabled(false);
        add(new JButton[]{turnOff});

        turnOn = new JButton("Power ON");
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        turnOn.setEnabled(true);
        add(new JButton[]{turnOn});
        
        set = new JButton("Reset Time");
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{set});
        
        set = new JButton("Set Timer");
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{set});
        
        type = new JMenu("Option");
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        i1=new JMenuItem("Fan Grilling *");
        i2=new JMenuItem("Conventional oven =");
        i3=new JMenuItem("Top Heat ^");
        i4=new JMenuItem("Base Heat _");
        i5=new JMenuItem("Defrost ❅");
        add(type);
        type.add(i1);
        type.add(i2);
        type.add(i3);
        type.add(i4);
        type.add(i5);
        
        
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == warm) {
            parent.warmTemp();
            cool.setEnabled(true);
            warm.setEnabled(true);
            
        } else if (e.getSource() == cool) {
            parent.coolTemp();
            cool.setEnabled(true);
            warm.setEnabled(true);
        } else if (e.getSource() == turnOn) {
            parent.powerOn();
            cool.setEnabled(true);
            warm.setEnabled(true);
            turnOff.setEnabled(true);
            turnOn.setEnabled(false);
        } else if (e.getSource() == turnOff) {
            parent.powerOff();
            cool.setEnabled(false);
            warm.setEnabled(false);
            turnOn.setEnabled(true);
            turnOff.setEnabled(false); 
        
        } else if (e.getSource() == reset) {
            parent.reset();
            cool.setEnabled(false);
            warm.setEnabled(false);
            turnOn.setEnabled(true);
            turnOff.setEnabled(false);
            
        } else if (e.getSource() == set) {
            parent.set();
            cool.setEnabled(true);
            warm.setEnabled(true);
            turnOff.setEnabled(true);
            turnOn.setEnabled(false); 
            
        } else if (e.getSource() == type) {
            parent.powerOff();
            cool.setEnabled(false);
            warm.setEnabled(false);
            turnOn.setEnabled(true);
            turnOff.setEnabled(false); 
            type.setEnabled(false);
             turnOn.setEnabled(true);
            turnOff.setEnabled(false); 
            
        }
            
        
        }
    }

