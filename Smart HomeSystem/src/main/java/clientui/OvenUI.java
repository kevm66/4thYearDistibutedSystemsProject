package clientui;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import client.OvenClient;
import java.awt.event.ItemEvent;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;
import javax.swing.*;

/*
 *File Title: OvenUI.java							
 *
 * @author:Karolina Laptas, x14446332
 *
 * @reference sample by Dominic Carr https://moodle.ncirl.ie/course/view.php?id=1473	
 */
public class OvenUI extends ClientUI {

    private static final long serialVersionUID = -5318589393275157185L;
    private JButton preHeat;
    private JButton turnDownTemp;
    private JButton on;
    private JButton off;
  

    private final OvenClient parent;

    public OvenUI(OvenClient ovenClient) {
        super(ovenClient);
        parent = ovenClient;
        init();
    }

    @Override
    public void init() {
        super.init();
        preHeat = new JButton("PreHeat");
        preHeat.setEnabled(false);
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{preHeat});

        turnDownTemp = new JButton("Turn Down Temperature");
        turnDownTemp.setEnabled(false);
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{turnDownTemp});

        on = new JButton("Turn On");
        on.setEnabled(true);
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{on});

        off = new JButton("Turn Off");
        off.setEnabled(false);
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{off});

       

    }
//button view

    @Override
    public void actionPerformed(ActionEvent e) {

        JComboBox cookOpt = new JComboBox();
        cookOpt.addItem("Fan Grilling *");
        cookOpt.addItem("Conventional oven =");
        cookOpt.addItem("Top Heat ^");
        cookOpt.addItem("Base Heat _");
        cookOpt.addItem("Defrost ❅ ");
    

        if (e.getSource() == on) {
            parent.power_on();
            preHeat.setEnabled(true);
            turnDownTemp.setEnabled(true);
            off.setEnabled(true);
            on.setEnabled(false);// all buttons disabled except On button

        } else if (e.getSource() == off) {
            parent.power_off();
            preHeat.setEnabled(false);
            turnDownTemp.setEnabled(false);
            on.setEnabled(true);
            off.setEnabled(false);

        } else if (e.getSource() == preHeat) {
            parent.increase_temp();
            preHeat.setEnabled(true);
            turnDownTemp.setEnabled(true);

        } else if (e.getSource() == turnDownTemp) {
            parent.decrease_temp();
            turnDownTemp.setEnabled(true);
            preHeat.setEnabled(true);
        }
        
      
    }

   

}