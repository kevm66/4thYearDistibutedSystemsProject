package client;

import com.google.gson.Gson;
import clientui.TVUI;
import models.TVModel;

/* @File Title:TVClient.java							
 *
 * @author:Kevin Maher,     x14328981
 *
 * @reference sample by Dominic Carr https://moodle.ncirl.ie/course/view.php?id=1473	
 */
public class TVClient extends Client {

    private String pass = "pass";
    private final String On = "On";
    private final String Off = "Off";
    private boolean isOn = false;
    private boolean isOff = true;
    private boolean isSwitchingNext, isSwitchingPrevious, isIncreasingVolume;
    private boolean isDecreasingVolume, isConnected, isDisconnected;
    private boolean isConnecting, isDisconnecting, isTurningOn, isTurningOff;

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
    public void powerOn() { //power_on
        String json = new Gson().toJson(new TVModel(TVModel.Action.ON));
        String a = sendMessage(json);
        TVModel tvM = new Gson().fromJson(a, TVModel.class);
        System.out.println("Client Received " + json);

        if (tvM.getAction() == TVModel.Action.ON) {
            isTurningOn = tvM.getValue();
            ui.updateArea(tvM.getMessage());
        }
    }

    //sends a message to turn-off the TV
    public void powerOff() { //power_off
        String json = new Gson().toJson(new TVModel(TVModel.Action.OFF));
        String a = sendMessage(json);
        TVModel tvM = new Gson().fromJson(a, TVModel.class);
        System.out.println("Client Received " + json);

        if (tvM.getAction() == TVModel.Action.OFF) {
            isTurningOff = tvM.getValue();
            ui.updateArea(tvM.getMessage());
        }
    }

    //sends a message to switch to the next TV channel
    public void next_channel() {
        String json = new Gson().toJson(new TVModel(TVModel.Action.NEXT_CHANNEL));
        String a = sendMessage(json);
        TVModel tvM = new Gson().fromJson(a, TVModel.class);
        System.out.println("Client Received " + json);

        if (tvM.getAction() == TVModel.Action.NEXT_CHANNEL) {
            isSwitchingNext = tvM.getValue();
            ui.updateArea(tvM.getMessage());
        }
    }

    //sends a message to switch to the previous TV channel
    public void previous_channel() {
        String json = new Gson().toJson(new TVModel(TVModel.Action.PREVIOUS_CHANNEL));
        String a = sendMessage(json);
        TVModel tvM = new Gson().fromJson(a, TVModel.class);
        System.out.println("Client Received " + json);

        if (tvM.getAction() == TVModel.Action.PREVIOUS_CHANNEL) {
            isSwitchingPrevious = tvM.getValue();
            ui.updateArea(tvM.getMessage());
        };
    }

    //sends a message to increase the volume (of the TV)
    public void increase_volume() {
        String json = new Gson().toJson(new TVModel(TVModel.Action.INCREASE_VOLUME));
        String a = sendMessage(json);
        TVModel tvM = new Gson().fromJson(a, TVModel.class);
        System.out.println("Client Received " + json);

        if (tvM.getAction() == TVModel.Action.INCREASE_VOLUME) {
            isIncreasingVolume = tvM.getValue();
            ui.updateArea(tvM.getMessage());
        };
    }

    //sends a message to decrease the volume (of the TV)
    public void decrease_volume() {
        String json = new Gson().toJson(new TVModel(TVModel.Action.DECREASE_VOLUME));
        String a = sendMessage(json);
        TVModel tvM = new Gson().fromJson(a, TVModel.class);
        System.out.println("Client Received " + json);

        if (tvM.getAction() == TVModel.Action.DECREASE_VOLUME) {
            isDecreasingVolume = tvM.getValue();
            ui.updateArea(tvM.getMessage());
        };
    }

    //sends a message to connect to a speaker
    public void connect_to_speaker() {
        String json = new Gson().toJson(new TVModel(TVModel.Action.CONNECT));
        String a = sendMessage(json);
        TVModel tvM = new Gson().fromJson(a, TVModel.class);
        System.out.println("Client Received " + json);

        if (tvM.getAction() == TVModel.Action.CONNECT) {
            isConnecting = tvM.getValue();
            ui.updateArea(tvM.getMessage());
        };
    }

    //sends a message to disconnect from a speaker
    public void disconnect_from_speaker() {
        String json = new Gson().toJson(new TVModel(TVModel.Action.DISCONNECT));
        String a = sendMessage(json);
        TVModel tvM = new Gson().fromJson(a, TVModel.class);
        System.out.println("Client Received " + json);

        if (tvM.getAction() == TVModel.Action.DISCONNECT) {
            isDisconnecting = tvM.getValue();
            ui.updateArea(tvM.getMessage());
        };
    }

    @Override
    public void updatePoll(String msg) {
//        if (msg.equals("TV is ")) {
        if (msg.equals("Speaker is not switched on. Press the 'on' button to turn on.")) {
//            isWarming = false;
        }
    }

    @Override
    public void disable() {
        super.disable();
        ui = new TVUI(this);
//        isWarming = false;
    }
}
