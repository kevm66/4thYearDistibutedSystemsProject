package clientui;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import client.LightsClient;
import java.awt.Dimension;

/*
 * @LightsUI.java							
 *
 * @author:Karolina Laptas, x14446332
 *
 * @reference sample skeleton by Dominic Carr https://moodle.ncirl.ie/course/view.php?id=1473	
 */
public class LightsUI extends ClientUI {

    private static final long serialVersionUID = -5318589393275157185L;
    private JButton lighten;
    private JButton reduceLight;
    private JButton turnOff;
    private JButton turnOn;

// Lights colour change
    private JButton blue;
    private JButton green;
    private JButton orange;
    private JButton purple;
    private JButton pink;

    private final LightsClient parent;

    public LightsUI(LightsClient lightsClient) {
        super(lightsClient);
        parent = lightsClient;
        init();
    }

    @Override
    public void init() {
        super.init();
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);

        lighten = new JButton("Brighten");
        lighten.setEnabled(false);
        lighten.setPreferredSize(new Dimension(140, 25));
        add(new JButton[]{lighten});

        reduceLight = new JButton("Dim");
        reduceLight.setEnabled(false);
        reduceLight.setPreferredSize(new Dimension(140, 25));
        add(new JButton[]{reduceLight});

        turnOff = new JButton("Turn off Lights");
        turnOff.setEnabled(false);
        turnOff.setPreferredSize(new Dimension(140, 25));
        add(new JButton[]{turnOff});

        turnOn = new JButton("Turn on Lights");
        turnOn.setEnabled(true);
        turnOn.setPreferredSize(new Dimension(140, 25));
        add(new JButton[]{turnOn});

//colours 
        blue = new JButton("Switch to Blue");
        blue.setPreferredSize(new Dimension(140, 25));
        add(new JButton[]{blue});

        green = new JButton("Switch to Green");
        green.setPreferredSize(new Dimension(140, 25));
        add(new JButton[]{green});

        orange = new JButton("Switch to Orange");
        orange.setPreferredSize(new Dimension(140, 25));
        add(new JButton[]{orange});

        purple = new JButton("Switch to Purple");
        purple.setPreferredSize(new Dimension(140, 25));
        add(new JButton[]{purple});

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == lighten) {
            parent.brighten_lights();
        } else if (e.getSource() == reduceLight) {
            parent.dim_lights();
        } else if (e.getSource() == turnOff) {
            parent.turn_off();
            lighten.setEnabled(false);
            reduceLight.setEnabled(false);
            turnOff.setEnabled(false);
            turnOn.setEnabled(true);
        } else if (e.getSource() == turnOn) {
            parent.turn_on();
            lighten.setEnabled(true);
            reduceLight.setEnabled(true);
            turnOff.setEnabled(true);
            turnOn.setEnabled(false);

        } else if (e.getSource() == blue) {
            parent.blueLight();
            // green.setEnabled(false);
            //  orange.setEnabled(false);
            //  purple.setEnabled(false);
            //  pink.setEnabled(false);

        } else if (e.getSource() == green) {
            parent.greenLight();
            //   blue.setEnabled(false);
            //   orange.setEnabled(false);
            //   purple.setEnabled(false);
            //   pink.setEnabled(false);

        } else if (e.getSource() == orange) {
            parent.orangeLight();
            //    green.setEnabled(false);
            //   blue.setEnabled(false);
            //  purple.setEnabled(false);
            //  pink.setEnabled(false);

        } else if (e.getSource() == purple) {
            parent.purpleLight();
            //  green.setEnabled(false);
            //   orange.setEnabled(false);
            //   blue.setEnabled(false);
            //   pink.setEnabled(false);

        }
    }
}
