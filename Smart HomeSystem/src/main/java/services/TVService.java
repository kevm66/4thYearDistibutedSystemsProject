package services;

import com.google.gson.Gson;
import models.TVModel;

import serviceui.ServiceUI;

/* @File Title:ClientManager.java							
 *
  * @author:Kevin Maher,     x14328981
 *
 * @reference sample by Dominic Carr https://moodle.ncirl.ie/course/view.php?id=1473	
 */
public class TVService extends Service {

    private int currentChannel, currentVolume;
    private final int firstChannel, lastChannel, lowestVolume, highestVolume;
    private static boolean isOn, isOff, isIncreasingVolume, isDecreasingVolume;
    private static boolean isSwitchingNext, isSwitchingPrevious, isConnected;
    private static boolean isConnecting, isDisconnecting, connectionStatus;
    private String pass = "pass";
    Gson gson = new Gson();

    public TVService(String name) {
        super(name, "_tv._udp.local.");
        currentVolume = 40;
        currentChannel = 1;
        lowestVolume = 0;
        highestVolume = 100;
        firstChannel = 1;
        lastChannel = 99;
        isOn = false;
        isOff = true;
        isConnecting = false;
        isConnected = false;
        ui = new ServiceUI(this, name);
    }

    @Override
    public void performAction(String a) {
        System.out.println("recieved: " + a);
        TVModel tvM = new Gson().fromJson(a, TVModel.class);

        //get status
        if (tvM.getAction() == TVModel.Action.STATUS) {
            String msg = getStatus();
            String json = new Gson().toJson(new TVModel(TVModel.Action.STATUS, msg));
            sendBack(json);
        } //power on TV
        else if (tvM.getAction() == TVModel.Action.ON) {
            powerOn();
            String msg = (isOn) ? "This TV is on\n" : "TV has been switched on\n";
            String json = new Gson().toJson(new TVModel(TVModel.Action.ON, msg));
            System.out.println(json);
            sendBack(json);

            String serviceMessage = (isOn) ? "The TV is on\n" : "Switching on TV...\n";
            ui.updateArea(serviceMessage);
        } //power off TV
        else if (tvM.getAction() == TVModel.Action.OFF) {
            powerOff();
            String msg = (isOff) ? "TV has been switched off\n" : "TV has been switched on\n";
            String json = new Gson().toJson(new TVModel(TVModel.Action.OFF, msg));
            System.out.println(json);
            sendBack(json);

            String serviceMessage = (isOff) ? "Switching off TV...\n" : "...";
            ui.updateArea(serviceMessage);
        } //next channel
        else if (tvM.getAction() == TVModel.Action.NEXT_CHANNEL) {
            nextChannel();
            String msg = (isSwitchingNext) ? "Switched to the next channel\n" : "Switched to channel " + currentChannel + "\n";
            String json = new Gson().toJson(new TVModel(TVModel.Action.NEXT_CHANNEL, msg));
            System.out.println(json);
            sendBack(json);

            String serviceMessage = (isSwitchingNext) ? "Current channel: " + currentChannel + "\n" : "Switching to the next channel...\n";
            ui.updateArea(serviceMessage);
        } //previous channel
        else if (tvM.getAction() == TVModel.Action.PREVIOUS_CHANNEL) {
            previousChannel();
            String msg = (isSwitchingPrevious) ? "Switched to the previous channel\n" : "Switched to channel " + currentChannel + "\n";
            String json = new Gson().toJson(new TVModel(TVModel.Action.PREVIOUS_CHANNEL, msg));
            System.out.println(json);
            sendBack(json);

            String serviceMessage = (isSwitchingPrevious) ? "Current channel: " + currentChannel + "\n" : "Switching to the previous channel...\n";
            ui.updateArea(serviceMessage);
        } //increase volume
        else if (tvM.getAction() == TVModel.Action.INCREASE_VOLUME) {
            increaseVolume();
            String msg = (isIncreasingVolume) ? "Volume has been set to" + currentVolume + "\n" : "Volume set to " + currentVolume + "\n";
            String json = new Gson().toJson(new TVModel(TVModel.Action.INCREASE_VOLUME, msg));
            System.out.println(json);
            sendBack(json);

            String serviceMessage = (isIncreasingVolume) ? "Current volume: " + currentVolume + "\n" : "Volume is increasing...\n";
            ui.updateArea(serviceMessage);
        } //decrease volume
        else if (tvM.getAction() == TVModel.Action.DECREASE_VOLUME) {
            decreaseVolume();
            String msg = (isDecreasingVolume) ? "Volume has been set to" + currentVolume + "\n" : "Volume set to " + currentVolume + "\n";
            String json = new Gson().toJson(new TVModel(TVModel.Action.DECREASE_VOLUME, msg));
            System.out.println(json);
            sendBack(json);

            String serviceMessage = (isDecreasingVolume) ? "Current volume: " + currentVolume + "\n" : "Volume is decreasing...\n";
            ui.updateArea(serviceMessage);
        } //connect to speaker
        else if (tvM.getAction() == TVModel.Action.CONNECT) {
            decreaseVolume();
            String msg = (isConnecting) ? "TV connected to speaker: " + connectionStatus + "\n" : "TV connected to speaker: " + connectionStatus + "\n";
            String json = new Gson().toJson(new TVModel(TVModel.Action.CONNECT, msg));
            System.out.println(json);
            sendBack(json);

            String serviceMessage = (isConnecting) ? "TV connected to speaker: " + connectionStatus + "\n" : "TV is connecting to speaker...\n";
            ui.updateArea(serviceMessage);
        } //disconnect from speaker
        else if (tvM.getAction() == TVModel.Action.DISCONNECT) {
            decreaseVolume();
            String msg = (isDisconnecting) ? "TV disconnected from speaker: " + connectionStatus + "\n" : "TV disconnected from speaker: " + connectionStatus + "\n";
            String json = new Gson().toJson(new TVModel(TVModel.Action.DISCONNECT, msg));
            System.out.println(json);
            sendBack(json);

            String serviceMessage = (isDisconnecting) ? "TV disconnected from speaker: " + connectionStatus + "\n" : "TV is disconnecting from speaker...\n";
            ui.updateArea(serviceMessage);
        } //error
        else {
            sendBack(BAD_COMMAND + " - " + a);
        }

    }

    public void powerOn() { //powerOnTv
        System.out.println("TV is turned on.\n");
    }

    public void powerOff() { //powerOffTv
        System.out.println("TV is turned off.\n");
    }

    public void nextChannel() {
        if (currentChannel != lastChannel) {
            currentChannel += 1;
        } else {
            currentChannel = currentChannel;
            ui.updateArea("You are on the last channel.\nUse the 'Channel -' button to switch to the previous channel.\n");
        }
    }

    public void previousChannel() {
        if (currentChannel != firstChannel) {
            currentChannel -= 1;
        } else {
            currentChannel = currentChannel;
            ui.updateArea("You are on the first channel. Use the 'Channel +' button to switch to the next channel.\n.");
        }
    }

    public void increaseVolume() {
        if (currentVolume != highestVolume) {
            currentVolume += 5;
        } else {
            currentVolume = currentVolume;
            ui.updateArea("You are already on the highest volume.\n");
        }
    }

    public void decreaseVolume() {
        if (currentVolume != lowestVolume) {
            currentVolume -= 5;
        } else {
            currentVolume = currentVolume;
            ui.updateArea("You are already on the lowest volume.\n");
        }
    }

    public void connectToSpeaker() {
        if (isConnected == false) { //if not connected to the speaker
            //[set audio to speaker]
            ui.updateArea("Connecting to speaker.\n");
        } else { //if already connected to speaker, do nothing
            ui.updateArea("Already connected to speaker.\n");
        }
    }

    public void disconnectFromSpeaker() {
        if (isConnected == true) { //if already connected to the speaker
            //[disconnect from speaker]
            //[set audio to built-in TV audio]
            ui.updateArea("Disconnecting from speaker. Audio set to TV built-in audio.\n");
        } else { //if already connected to speaker, do nothing
            ui.updateArea("Already disconnected from speaker.\n");
        }
    }

//    class RemindTask extends TimerTask {
//
//        @Override
//        public void run() {
////            if (percentHot < 100) {
////// every time run method gets called it adds 10% until it goes to 100^
////                percentHot += 10;
////            }
//        }
//    }
    @Override
    public String getStatus() {
//        return "TV is " + percentHot + "% warmed.";
        return "Current volume: " + currentVolume + "\nCurrent channel: "
                + currentChannel + "\nConnected to speaker: " + connectionStatus
                + "\n";
    }

    public static void main(String[] args) {
        new TVService("Room 1");
        new TVService("Room 2");
        new TVService("Room 3");
        new TVService("Living Room");
    }

}
