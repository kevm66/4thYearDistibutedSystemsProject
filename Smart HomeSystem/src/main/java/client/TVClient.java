/*
 * 
 */
package client;

import com.google.gson.Gson;
import com.sun.org.apache.bcel.internal.Constants;
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
    private String pass = "pass";
    private final String On = "On";
    private final String Off = "Off";
    private boolean isOn = false;
    private boolean isOff = true;
    private boolean isWarming = false;
//    private String a;
//    private String b;

    private Gson gson = new Gson();

    /**
     * TV Client Constructor.
     */
    public TVClient() {
        super();
        serviceType = "_tv._udp.local.";
        ui = new TVUI(this);
        name = "TV";
    }

    //sends a message to turn-on the TV
    public void power_on() { //power_on_tv

//        **** The below code block is causing a GSON error ****

//        String json = new Gson().toJson(On);
//        String a = sendMessage(json);
//        String b = gson.fromJson(a, String.class);
//        System.out.println("Client Received " + json);

//        if (!isOn) { //if (!is off)
//
//            if (b.equals(pass)) {
//                isOn = true;
//                ui.updateArea("TV has been switched on");
//            }
//        } else {
//            ui.updateArea("TV has already been switched on");
//        }

//**** The above code block is causing a GSON error ****

    }

    //sends a message to turn-off the TV
    public void power_off() { //power_off_tv
        ui.updateArea("TV has been switched Off");
    }

    //sends a message to switch to the next TV channel
    public void next_channel() {
        ui.updateArea("Switched to the next TV channel");
    }

    //sends a message to switch to the previous TV channel
    public void previous_channel() {
        ui.updateArea("Switched to the previous TV channel");
    }

    //sends a message to increase the volume (of the TV)
    public void increase_volume() {
        ui.updateArea("TV volume has been turned up 5%");
    }

    //sends a message to decrease the volume (of the TV)
    public void decrease_volume() {
        ui.updateArea("TV volume has been turned down 5%");
    }

    //sends a message to connect to a speaker
    public void connect_to_speaker() {
        ui.updateArea("TV has been connected to speaker");
    }

    //sends a message to disconnect from a speaker
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
