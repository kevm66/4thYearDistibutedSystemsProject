/*
 * 
 */
package client;

import clientui.TVUI;

/**
 * TV Client.
 *
 * @author dominic
 */
public class TVClient extends Client {

    private final String nextChannel = "Next Channel";
    private final String previousChannel = "Previous Channel";
    private final String increaseVolume = "Increase Volume";
    private final String decreaseVolume = "Decrease Volume";
    private final String connectToSpeaker = "Connect to Speaker";
    private final String disconnectFromSpeaker = "Disconect from Speaker";
    private boolean isWarming = false;

    /**
     * TV Client Constructor.
     */
    public TVClient() {
        super();
        serviceType = "_tv._udp.local.";
        ui = new TVUI(this);
        name = "TV";
    }

    /**
     * sends a message to warm the tv.
     */
//    public void warm() {
//        if (!isWarming) {
//            String a = sendMessage(WARM);
//            if (a.equals(OK)) {
//                isWarming = true;
//                ui.updateArea("TV is Warming");
//            }
//        } else {
//            ui.updateArea("TV already Warming");
//        }
//    }

    public void power_on() {
//        if (!is off)
        ui.updateArea("TV has been switched on");
//        else1
//        ui.updateArea("TV has already been switched on");
    }
    
    public void power_off() {
        ui.updateArea("TV has been switched Off");
    }
    
    public void next_channel() {
        ui.updateArea("Switched to the next TV channel");
    }
    
    public void previous_channel() {
        ui.updateArea("Switched to the previous TV channel");
    }
    
    public void increase_volume() {
        ui.updateArea("TV volume has been turned up 5%");
    }
    
    public void decrease_volume() {
        ui.updateArea("TV volume has been turned down 5%");
    }
    
    public void connect_to_speaker() {
        ui.updateArea("TV has been connected to speaker");
    }
    
    public void disconnect_from_speaker() {
        ui.updateArea("TV has been disconnected from speaker");
    }
       
    @Override
    public void updatePoll(String msg) {
//        if (msg.equals("TV is 100% warmed.")) {
        if (msg.equals("Speaker is not switched on. Press the 'on' button to turn on.")) {
//            isWarming = false;
        }
    }

    @Override
    public void disable() {
        super.disable();
        ui = new TVUI(this);
        isWarming = false;
    }
}
