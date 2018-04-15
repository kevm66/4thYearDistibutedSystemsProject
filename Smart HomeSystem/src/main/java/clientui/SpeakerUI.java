package clientui;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import client.SpeakerClient;
import java.awt.Dimension;

public class SpeakerUI extends ClientUI {

    private static final long serialVersionUID = -5318589393275157185L;
    private JButton warm;
    private JButton on;
    private JButton off;
    private JButton transferAudio;
    private JButton checkWeather;
    private JButton increaseVolume;
    private JButton decreaseVolume;    
    private JButton connectToTV;
    private JButton disconnectFromTV;
    private final SpeakerClient parent;

    public SpeakerUI(SpeakerClient speakerClient) {
        super(speakerClient);
        parent = speakerClient;
        init();
    }

    @Override
    public void init() {
        super.init();
        
        //on button
        on = new JButton("On");
        on.setEnabled(true);
        on.setPreferredSize(new Dimension(80, 25));
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{on});
        
        //transfer audio button
        transferAudio = new JButton("    Transfer Audio     ");
        transferAudio.setEnabled(false);
        transferAudio.setPreferredSize(new Dimension(180, 25));
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{transferAudio});         
        
        //increase volume button
        increaseVolume = new JButton("Volume +");
        increaseVolume.setEnabled(false);
        increaseVolume.setPreferredSize(new Dimension(100, 25));
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{increaseVolume});
        
        //connect to TV button
        connectToTV = new JButton("Connect to TV");
        connectToTV.setEnabled(false);
        connectToTV.setPreferredSize(new Dimension(140, 25));
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{connectToTV});
              
        //off button
        off = new JButton("Off");
        off.setEnabled(false);
        off.setPreferredSize(new Dimension(80, 25));
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{off});     
        
        //check weather button
        checkWeather = new JButton("Check Weather");
        checkWeather.setEnabled(false);
        checkWeather.setPreferredSize(new Dimension(180, 25));
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{checkWeather});
        
            //decrease volume button
        decreaseVolume = new JButton("Volume -");
        decreaseVolume.setEnabled(false);
        decreaseVolume.setPreferredSize(new Dimension(100, 25));
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{decreaseVolume});

        //disconnect from TV button
        disconnectFromTV = new JButton("Disconnect from TV");
        disconnectFromTV.setEnabled(false);
        disconnectFromTV.setPreferredSize(new Dimension(140, 25));
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{disconnectFromTV});      
           
        //delete
//        warm = new JButton("Warm");
//        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
//        add(new JButton[]{warm});
    }

    //show/hide buttons
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == on) {
            parent.power_on();
            connectToTV.setEnabled(true);
            disconnectFromTV.setEnabled(false);
            transferAudio.setEnabled(true);
            checkWeather.setEnabled(true);
            increaseVolume.setEnabled(true);
            decreaseVolume.setEnabled(true);
            off.setEnabled(true);
            on.setEnabled(false);// all buttons disabled except On button
            
        } else if (e.getSource() == off) {
            parent.power_off();
            connectToTV.setEnabled(false);
            disconnectFromTV.setEnabled(false);
            transferAudio.setEnabled(false);
            checkWeather.setEnabled(false);
            increaseVolume.setEnabled(false);
            decreaseVolume.setEnabled(false);
            on.setEnabled(true);
            off.setEnabled(false);

        } else if (e.getSource() == transferAudio) {
            parent.transfer_audio();
            
        } else if (e.getSource() == checkWeather) {
            parent.check_weather();   
            
        } else if (e.getSource() == increaseVolume) {
            parent.increase_volume();
           
        } else if (e.getSource() == decreaseVolume) {
            parent.decrease_volume();
           
        } else if (e.getSource() == connectToTV) {
            parent.connect_to_tv();
            connectToTV.setEnabled(false);
            disconnectFromTV.setEnabled(true);
            
        } else if (e.getSource() == disconnectFromTV) {
            parent.disconnect_from_tv();
            connectToTV.setEnabled(true);
            disconnectFromTV.setEnabled(false);   
        }

    }
}
