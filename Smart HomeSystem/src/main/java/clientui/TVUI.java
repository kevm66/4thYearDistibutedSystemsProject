package clientui;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import client.TVClient;
import java.awt.Dimension;

public class TVUI extends ClientUI {

    private static final long serialVersionUID = -5318589393275157185L;
    private JButton warm;
    private JButton on;
    private JButton off;
    private JButton nextChannel;
    private JButton previousChannel;
    private JButton increaseVolume;
    private JButton decreaseVolume;
    private JButton connectToSpeaker;
    private JButton disconnectFromSpeaker;
    private final TVClient parent;

    public TVUI(TVClient tvClient) {
        super(tvClient);
        parent = tvClient;
        init();
    }

    //create client buttons
    @Override
    public void init() {
        super.init();
       
        //on button
        on = new JButton("On");
        on.setEnabled(true);
        on.setPreferredSize(new Dimension(80, 25));
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{on});
        
        //increase volume button
        increaseVolume = new JButton("Volume +");
        increaseVolume.setEnabled(false);
        increaseVolume.setPreferredSize(new Dimension(140, 25));
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{increaseVolume});
       
        //next channel button
        nextChannel = new JButton("Channel +");
        nextChannel.setEnabled(false);
        nextChannel.setPreferredSize(new Dimension(140, 25));
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{nextChannel});
        
        //connect to speaker button
        connectToSpeaker = new JButton("Connect to Speaker");
        connectToSpeaker.setEnabled(false);
        connectToSpeaker.setPreferredSize(new Dimension(140, 25));
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{connectToSpeaker});
                
        //off button
        off = new JButton("Off");
        off.setEnabled(false);
        off.setPreferredSize(new Dimension(80, 25));
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{off});            
        
        //decrease volume button
        decreaseVolume = new JButton("Volume -");
        decreaseVolume.setEnabled(false);
        decreaseVolume.setPreferredSize(new Dimension(140, 25));
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{decreaseVolume});
        
        //previous channel button
        previousChannel = new JButton("Channel -");
        previousChannel.setEnabled(false);
        previousChannel.setPreferredSize(new Dimension(140, 25));
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{previousChannel});
        
        //disconnect from speaker button
        disconnectFromSpeaker = new JButton("Disconnect Speaker");
        disconnectFromSpeaker.setEnabled(false);
        disconnectFromSpeaker.setPreferredSize(new Dimension(140, 25)); //add to add
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{disconnectFromSpeaker});  
        
        //delete
//        warm = new JButton("Warm");
//        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
//        add(new JButton[]{warm});
    }

    //show/hide client buttons
    @Override
    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == warm) {
//            parent.warm();
//        }

        if (e.getSource() == on) {
            parent.power_on();
            connectToSpeaker.setEnabled(true);
            disconnectFromSpeaker.setEnabled(false);
            nextChannel.setEnabled(true);
            previousChannel.setEnabled(true);
            increaseVolume.setEnabled(true);
            decreaseVolume.setEnabled(true);
            off.setEnabled(true);
            on.setEnabled(false);// all buttons disabled except On button

        } else if (e.getSource() == off) {
            parent.power_off();
            connectToSpeaker.setEnabled(false);
            disconnectFromSpeaker.setEnabled(false);
            nextChannel.setEnabled(false);
            previousChannel.setEnabled(false);
            increaseVolume.setEnabled(false);
            decreaseVolume.setEnabled(false);
            on.setEnabled(true);
            off.setEnabled(false);

        } else if (e.getSource() == nextChannel) {
            parent.next_channel();
            
        } else if (e.getSource() == previousChannel) {
            parent.previous_channel();   
            
        } else if (e.getSource() == increaseVolume) {
            parent.increase_volume();
           
        } else if (e.getSource() == decreaseVolume) {
            parent.decrease_volume();
           
        } else if (e.getSource() == connectToSpeaker) {
            parent.connect_to_speaker();
            connectToSpeaker.setEnabled(false);
            disconnectFromSpeaker.setEnabled(true);
            
        } else if (e.getSource() == disconnectFromSpeaker) {
            parent.disconnect_from_speaker();
            connectToSpeaker.setEnabled(true);
            disconnectFromSpeaker.setEnabled(false);   
        }
         
    }
}
