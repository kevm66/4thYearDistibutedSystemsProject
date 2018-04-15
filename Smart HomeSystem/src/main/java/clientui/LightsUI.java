package clientui;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import client.LightsClient;

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
    private final LightsClient parent;

    public LightsUI(LightsClient lightsClient) {
        super(lightsClient);
        parent = lightsClient;
        init();
    }

    @Override
    public void init() {
        super.init();
        lighten = new JButton("Brighten");
        lighten.setEnabled(false);
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{lighten});

        reduceLight = new JButton("Dim");
        reduceLight.setEnabled(false);
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{reduceLight});

        turnOff = new JButton("Turn off Lights");
        turnOff.setEnabled(false);
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{turnOff});

        turnOn = new JButton("Turn on Lights");
        turnOn.setEnabled(true);
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{turnOn});
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
        }
    }
}
