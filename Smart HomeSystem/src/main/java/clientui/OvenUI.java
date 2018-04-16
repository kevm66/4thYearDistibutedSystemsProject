package clientui;

import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.JComboBox;
import javax.swing.JButton;
import client.OvenClient;
import java.awt.Dimension;

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
    private JButton fan;
    private JButton externalfan;

    private JButton conventional;
    private JButton top;
    private JButton base;
    private JButton defrost;

    // private JMenu type;
    // private JMenuItem i1,i2,i3,i4,i5;
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
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);

        warm = new JButton("      + TEMP      ");
        warm.setPreferredSize(new Dimension(140, 25));
        warm.setEnabled(false);
        add(new JButton[]{warm});

        cool = new JButton("      - TEMP      ");
        cool.setPreferredSize(new Dimension(140, 25));
        cool.setEnabled(false);
        add(new JButton[]{cool});

        turnOff = new JButton("     Power OFF      ");
        turnOff.setPreferredSize(new Dimension(140, 25));
        turnOff.setEnabled(false);
        add(new JButton[]{turnOff});

        turnOn = new JButton("      Power ON      ");
        turnOn.setPreferredSize(new Dimension(140, 25));
        turnOn.setEnabled(true);
        add(new JButton[]{turnOn});

        reset = new JButton("Reset Time");
        reset.setPreferredSize(new Dimension(140, 25));
        add(new JButton[]{reset});

        set = new JButton("Set Timer");
        set.setPreferredSize(new Dimension(140, 25));
        add(new JButton[]{set});

        fan = new JButton("Turn on FAN");
        fan.setPreferredSize(new Dimension(140, 25));
        fan.setEnabled(false);
        add(new JButton[]{fan});

        externalfan = new JButton("Connect external fan ...");
        externalfan.setPreferredSize(new Dimension(140, 25));
        externalfan.setEnabled(false);
        add(new JButton[]{externalfan});

        conventional = new JButton("Set Conventional Grill  = ");
        conventional.setPreferredSize(new Dimension(140, 25));
        conventional.setEnabled(false);
        add(new JButton[]{conventional});

        top = new JButton("Top Grilling  ^ ");
        top.setPreferredSize(new Dimension(140, 25));
        top.setEnabled(false);
        add(new JButton[]{top});

        base = new JButton("Base Grilling  _ ");
        base.setPreferredSize(new Dimension(140, 25));
        base.setEnabled(false);
        add(new JButton[]{base});

        defrost = new JButton("Defrosing  * ");
        defrost.setPreferredSize(new Dimension(140, 25));
        defrost.setEnabled(false);
        add(new JButton[]{defrost});

        /*  type = new JMenu("Option");
        type.setVisible(false);
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
        
         */
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
            fan.setEnabled(true);
            externalfan.setEnabled(true);
            top.setEnabled(true);
            base.setEnabled(true);
            conventional.setEnabled(true);
            defrost.setEnabled(true);

        } else if (e.getSource() == turnOff) {
            parent.powerOff();
            cool.setEnabled(false);
            warm.setEnabled(false);
            turnOn.setEnabled(true);
            turnOff.setEnabled(false);
            fan.setEnabled(false);
            externalfan.setEnabled(false);
            top.setEnabled(false);
            base.setEnabled(false);
            conventional.setEnabled(false);
            defrost.setEnabled(false);

        } else if (e.getSource() == reset) {
            parent.reset();

        } else if (e.getSource() == set) {
            parent.set();

        } else if (e.getSource() == externalfan) {
            parent.externalFan();
            fan.setEnabled(false);

        } else if (e.getSource() == fan) {
            parent.fan();

        } else if (e.getSource() == defrost) {
            parent.defrost();
            conventional.setEnabled(false);
            base.setEnabled(false);
            top.setEnabled(false);

        } else if (e.getSource() == conventional) {
            parent.conventinalGrill();
            defrost.setEnabled(false);
            base.setEnabled(false);
            top.setEnabled(false);

        } else if (e.getSource() == top) {
            parent.topOven();
            defrost.setEnabled(false);
            conventional.setEnabled(false);
            base.setEnabled(false);

        } else if (e.getSource() == base) {
            parent.baseOven();
            defrost.setEnabled(false);
            conventional.setEnabled(false);
            top.setEnabled(false);

        }

    }
}
