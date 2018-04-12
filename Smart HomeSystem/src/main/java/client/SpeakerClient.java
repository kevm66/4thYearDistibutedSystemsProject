/*
 * 
 */
package client;

import clientui.SpeakerUI;

/**
 * Speaker Client.
 *
 * @author dominic
 */
public class SpeakerClient extends Client {

//    private final String WARM = "Warm";
    private boolean isWarming = false;  
    private final String transferAudio = "Transfer audio to another speaker";
    private final String checkWeather = "Check the weather forecast";
    private final String increaseVolume = "Increase Volume";
    private final String decreaseVolume = "Decrease Volume";
    private final String connectToTV = "Connect to TV";
    private final String disconnectFromTV = "Disconect from TV";


    /**
     * Speaker Client Constructor.
     */
    public SpeakerClient() {
        super();
        serviceType = "_speaker._udp.local.";
        ui = new SpeakerUI(this);
        name = "Speaker";
    }

    /**
     * sends a message to warm the speaker.
     */
//    public void warm() {
//        if (!isWarming) {
//            String a = sendMessage(WARM);
//            if (a.equals(OK)) {
//                isWarming = true;
//                ui.updateArea("Speaker is Warming");
//            }
//        } else {
//            ui.updateArea("Speaker already Warming");
//        }
//    }

    public void power_on() {
        ui.updateArea("Speaker has been turned on");
    }
    
    public void power_off() {
        ui.updateArea("Speaker has been turned off");
    }
    
    public void transfer_audio() {
        ui.updateArea("Transfering audio to another speaker");
    }
    
    public void check_weather() {
        ui.updateArea("Checking the weather forecast");
    }
    
    public void increase_volume() {
        ui.updateArea("Speaker volume has been turned up 5%");
    }
    
    public void decrease_volume() {
        ui.updateArea("Speaker volume has been turned down 5%");
    }
    
    public void connect_to_tv() {
        ui.updateArea("Speaker has been connected to TV");
    }
    
    public void disconnect_from_tv() {
        ui.updateArea("Speaker has been disconnected from TV");
    }
    
    @Override
    public void updatePoll(String msg) {
//        if (msg.equals("Speaker is 100% warmed.")) {
        if (msg.equals("Speaker is not switched on. Press the 'on' button to turn on.")) {
            
            isWarming = false;
        }
    }

    @Override
    public void disable() {
        super.disable();
        ui = new SpeakerUI(this);
        isWarming = false;
    }
}
