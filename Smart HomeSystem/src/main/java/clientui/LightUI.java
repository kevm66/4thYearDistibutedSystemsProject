package clientui;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import client.LightClient;

public class LightUI extends ClientUI {

    private static final long serialVersionUID = -5318589393275157185L;
    private JButton lighter;
    private JButton darker;
    private JButton turnOn;
    private JButton turnOff;
    
    private final LightClient parent;

    public LightUI(LightClient bedClient) {
        super(bedClient);
        parent = bedClient;
        init();
    }

    @Override
    public void init() {
        super.init();
        
        lighter = new JButton("Brighter Room");
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{lighter});
        
        darker = new JButton("Darken Room");
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{darker});
        
        turnOn = new JButton("Turn On");
        turnOn.setEnabled(true);
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{turnOn});

        turnOff = new JButton("Turn Off");
        turnOff.setEnabled(false);
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{turnOff});
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == lighter) {
            parent.brightUp();
            lighter.setEnabled(true);
            darker.setEnabled(true);

        } else if (e.getSource() == darker) {
            parent.darken();
            darker.setEnabled(true);
            lighter.setEnabled(true);
            
        }else if (e.getSource() == turnOn) {
            parent.power_on();
            lighter.setEnabled(true);
            darker.setEnabled(true);
            turnOff.setEnabled(true);
            turnOn.setEnabled(false);// all buttons disabled except On button

        } else if (e.getSource() == turnOff) {
            parent.power_off();
            lighter.setEnabled(false);
            darker.setEnabled(false);
            turnOn.setEnabled(true);
            turnOff.setEnabled(false);
        }

    }
}