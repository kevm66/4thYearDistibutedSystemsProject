package client;

import com.google.gson.Gson;
import clientui.SpeakerUI;
import models.SpeakerModel;

/* @File Title: SpeakerClient.java							
 *
 * @author:Kevin Maher,     x14328981
 *
 * @reference sample by Dominic Carr https://moodle.ncirl.ie/course/view.php?id=1473	
 */
public class SpeakerClient extends Client {

    private boolean isWarming = false;
    private final String transferAudio = "Transfer audio to another speaker";
    private final String checkWeather = "Check the weather forecast";
    private final String increaseVolume = "Increase Volume";
    private final String decreaseVolume = "Decrease Volume";
    private final String connectToTV = "Connect to TV";
    private final String disconnectFromTV = "Disconect from TV";
    private static boolean isTurningOn, isTurningOff, isCheckingWeather, isPlayingMusic;
    private static boolean isIncreasingVolume, isDecreasingVolume, isConnecting, isDisconnecting;

    /**
     * Speaker Client Constructor.
     */
    public SpeakerClient() {
        super();
        serviceType = "_speaker._udp.local.";
        ui = new SpeakerUI(this);
        name = "Speaker";
    }

    //sends a message to turn-on the TV
    public void powerOn() { //power_on
        String json = new Gson().toJson(new SpeakerModel(SpeakerModel.Action.ON));
        String a = sendMessage(json);
        SpeakerModel speakerM = new Gson().fromJson(a, SpeakerModel.class);
        System.out.println("Client Received " + json);

        if (speakerM.getAction() == SpeakerModel.Action.ON) {
            isTurningOn = speakerM.getValue();
            ui.updateArea(speakerM.getMessage());
        }
    }

    //sends a message to turn-off the TV
    public void powerOff() { //power_off
        String json = new Gson().toJson(new SpeakerModel(SpeakerModel.Action.OFF));
        String a = sendMessage(json);
        SpeakerModel speakerM = new Gson().fromJson(a, SpeakerModel.class);
        System.out.println("Client Received " + json);

        if (speakerM.getAction() == SpeakerModel.Action.OFF) {
            isTurningOff = speakerM.getValue();
            ui.updateArea(speakerM.getMessage());
        }
    }

    //sends a message to switch to the next TV channel
    public void checkWeather() {
        String json = new Gson().toJson(new SpeakerModel(SpeakerModel.Action.CHECK_WEATHER));
        String a = sendMessage(json);
        SpeakerModel speakerM = new Gson().fromJson(a, SpeakerModel.class);
        System.out.println("Client Received " + json);

        if (speakerM.getAction() == SpeakerModel.Action.CHECK_WEATHER) {
            isCheckingWeather = speakerM.getValue();
            ui.updateArea(speakerM.getMessage());
        }
    }

    //sends a message to switch to the previous TV channel
    public void playMusic() {
        String json = new Gson().toJson(new SpeakerModel(SpeakerModel.Action.PLAY_MUSIC));
        String a = sendMessage(json);
        SpeakerModel speakerM = new Gson().fromJson(a, SpeakerModel.class);
        System.out.println("Client Received " + json);

        if (speakerM.getAction() == SpeakerModel.Action.PLAY_MUSIC) {
            isPlayingMusic = speakerM.getValue();
            ui.updateArea(speakerM.getMessage());
        }
    }

    //sends a message to increase the volume (of the speaker)
    public void increaseVolume() {
        String json = new Gson().toJson(new SpeakerModel(SpeakerModel.Action.INCREASE_VOLUME));
        String a = sendMessage(json);
        SpeakerModel speakerM = new Gson().fromJson(a, SpeakerModel.class);
        System.out.println("Client Received " + json);

        if (speakerM.getAction() == SpeakerModel.Action.INCREASE_VOLUME) {
            isIncreasingVolume = speakerM.getValue();
            ui.updateArea(speakerM.getMessage());
        };
    }

    //sends a message to decrease the volume (of the speaker)
    public void decreaseVolume() {
        String json = new Gson().toJson(new SpeakerModel(SpeakerModel.Action.DECREASE_VOLUME));
        String a = sendMessage(json);
        SpeakerModel speakerM = new Gson().fromJson(a, SpeakerModel.class);
        System.out.println("Client Received " + json);

        if (speakerM.getAction() == SpeakerModel.Action.DECREASE_VOLUME) {
            isDecreasingVolume = speakerM.getValue();
            ui.updateArea(speakerM.getMessage());
        };
    }

    //sends a message to connect to a speaker
    public void connectToTV() {
        String json = new Gson().toJson(new SpeakerModel(SpeakerModel.Action.CONNECT));
        String a = sendMessage(json);
        SpeakerModel speakerM = new Gson().fromJson(a, SpeakerModel.class);
        System.out.println("Client Received " + json);

        if (speakerM.getAction() == SpeakerModel.Action.CONNECT) {
            isConnecting = speakerM.getValue();
            ui.updateArea(speakerM.getMessage());
        };
    }

    //sends a message to disconnect from a speaker
    public void disconnectFromTV() {
        String json = new Gson().toJson(new SpeakerModel(SpeakerModel.Action.DISCONNECT));
        String a = sendMessage(json);
        SpeakerModel speakerM = new Gson().fromJson(a, SpeakerModel.class);
        System.out.println("Client Received " + json);

        if (speakerM.getAction() == SpeakerModel.Action.DISCONNECT) {
            isDisconnecting = speakerM.getValue();
            ui.updateArea(speakerM.getMessage());
        };
    }

    @Override
    public void updatePoll(String msg) {
//        if (msg.equals("Speaker is 100% warmed.")) {
        if (msg.equals("Speaker is not switched on. Press the 'on' button to turn on.")) {

//            isWarming = false;
        }
    }

    @Override
    public void disable() {
        super.disable();
        ui = new SpeakerUI(this);
//        isWarming = false;
    }
}
