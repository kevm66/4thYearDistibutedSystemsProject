package services;

import com.google.gson.Gson;

import java.util.Timer;
import java.util.TimerTask;

import serviceui.ServiceUI;

/**
 * The Class TVService Service.
 */
public class TVService extends Service {

    private final Timer timer;
    private int percentHot, currentChannel, firstChannel, lastChannel; //channelNumber
    private int lowestVolume, highestVolume, currentVolume;
    private static boolean isOn, isOff, isIncreasingVolume, isDecreasingVolume;
    private static boolean isSwitchingNext, isSwitchingPrevious,isConnected;
    private static boolean isConnecting, isDisconnecting;
    private String pass = "pass";
    Gson gson = new Gson();
    
    public TVService(String name) {
        super(name, "_tv._udp.local.");
        timer = new Timer();
        percentHot = 0;
        currentVolume = 40;
        currentChannel = 1;
        lowestVolume = 0;
        highestVolume = 100;
        firstChannel = 1;
        lastChannel = 999;
        isOn = false;
        isOff = true;
        isConnecting = false;
        isConnected = false;
        ui = new ServiceUI(this, name);
    }

    @Override
    public void performAction(String a) {
        ui.updateArea("Received: " +a);
        String b = gson.fromJson(a, String.class);
        if (b.equals("get_status")) {
            sendBack(getStatus());
        } 
        
       // power on
        else if (a.equals("On")) {
            powerOnTv();
            String msg = (isOn) ? "The TV is on" : "TV is on";
            String json = gson.toJson(pass);
            sendBack(json);

            String serviceMessage = (isOn) ? "The TV is on" : "...";
            ui.updateArea(serviceMessage);
        }
       
        //power off
        else if (a.equals("Off")) {
            powerOffTv();
            String msg = (isOff) ? "The TV is off" : "TV is off";
            String json = gson.toJson(pass);
            sendBack(json);

            String serviceMessage = (isOn) ? "The TV is on" : "...";
            ui.updateArea(serviceMessage);
        }
        
        //next channel
        else if (a.equals("Next channel")) {
            nextChannel();
            String msg = (isSwitchingNext) ? "Switching to the next channel" : "TV is switching to the next channel";
            String json = gson.toJson(pass);
//            ui.updateArea(currentChannel);//int cannot be converted to string
            System.out.println(currentChannel);
            sendBack(json);
            String serviceMessage = (isSwitchingNext) ? "Changing to the next channel" : "...";
            ui.updateArea(serviceMessage);
        }
        
        //previous channel
        else if (a.equals("Previous channel")) {
            previousChannel();
            String msg = (isSwitchingPrevious) ? "Switching to the previous channel" : "TV is switching to the previous channel";
            String json = gson.toJson(pass);
//            ui.updateArea(currentChannel);//int cannot be converted to string
            System.out.println(currentVolume);
            sendBack(json);
            String serviceMessage = (isSwitchingPrevious) ? "Changing to the next channel" : "...";
            ui.updateArea(serviceMessage);
        }
        
        //increase volume
        else if (a.equals("Increase volume")) {
            nextChannel();
            String msg = (isIncreasingVolume) ? "Increasing volume" : "TV is increasing volume";
            String json = gson.toJson(pass);
//            ui.updateArea(currentChannel);//int cannot be converted to string
            System.out.println(currentVolume);
            sendBack(json);
            String serviceMessage = (isIncreasingVolume) ? "Increasing the volume" : "...";
            ui.updateArea(serviceMessage);
        }
        
        //decrease volume
        else if (a.equals("Decrease volume")) {
            nextChannel();
            String msg = (isDecreasingVolume) ? "Decreasing volume" : "TV is decreasing volume";
            String json = gson.toJson(pass);
//            ui.updateArea(currentChannel);//int cannot be converted to string
            System.out.println(currentVolume);
            sendBack(json);
            String serviceMessage = (isDecreasingVolume) ? "Decreasing the volume" : "...";
            ui.updateArea(serviceMessage);
        }
        
        //connect to speaker
        else if (a.equals("Connect to speaker")) {
            nextChannel();
            String msg = (isConnecting) ? "Connecting to speaker" : "TV is connecting to speaker";
            String json = gson.toJson(pass);
//            ui.updateArea(currentChannel);//int cannot be converted to string
            System.out.println(isConnected);
            sendBack(json);
            String serviceMessage = (isConnecting) ? "Connecting to speaker" : "...";
            ui.updateArea(serviceMessage);
        }
        
        //disconnect from speaker
        else if (a.equals("Disconnect from speaker")) {
            nextChannel();
            String msg = (isDisconnecting) ? "Disconnecting from speaker" : "TV is disconnecting from speaker";
            String json = gson.toJson(pass);
//            ui.updateArea(currentChannel);//int cannot be converted to string
            System.out.println(isConnected);
            sendBack(json);
            String serviceMessage = (isDisconnecting) ? "Disconnecting from speaker" : "...";
            ui.updateArea(serviceMessage);
        }
        
        //error
        else {
            sendBack(BAD_COMMAND + " - " + a);
        }
       
    }
    
    public void powerOnTv() {
        ui.updateArea("TV is turned on");
    }
    
    public void powerOffTv() {
        ui.updateArea("TV is turned off");
    }

    public void nextChannel() {
        if(currentChannel != lastChannel){
            currentChannel += 1;
        }else{
            ui.updateArea("You are already on the last channel. Use the 'Channel -' button.");
        }
    }
    
    public void previousChannel() {
        if(currentChannel != firstChannel){
            currentChannel -= 1;
        }else{
            ui.updateArea("You are on the first channel. Use the 'Channel +' button.");
        }
    }
    
    public void increaseVolume() {
        if(currentVolume != highestVolume){
            currentVolume += 1;
        }else{
            ui.updateArea("You are already on the highest volume.");
        }
    }
    
    public String decreaseVolume() {
        if(currentVolume != lowestVolume){
            currentVolume -= 1;
        }else{
            ui.updateArea("You are already on the lowest volume.");
        }
        return "Current volume: " + currentVolume;
    }
    
    public void connectToSpeaker(){
        if(isConnected == false){ //if not connected to the speaker
            //[set audio to speaker]
            ui.updateArea("Donnecting to speaker");
        }else{ //if already connected to speaker, do nothing
            ui.updateArea("Already connected to speaker");
        }
    }
    
    public void disconnectFromSpeaker(){
         if(isConnected == true){ //if already connected to the speaker
            //[disconnect from speaker]
            //[set audio to built-in TV audio]
            ui.updateArea("Disconnecting from speaker. Audio set to TV built-in audio");
        }else{ //if already connected to speaker, do nothing
            ui.updateArea("Already disconnected from speaker");
         }
    }

    class RemindTask extends TimerTask {

        @Override
        public void run() {
//            if (percentHot < 100) {
//// every time run method gets called it adds 10% until it goes to 100^
//                percentHot += 10;
//            }
        }
    }

    @Override
    public String getStatus() {
//        return "TV is " + percentHot + "% warmed.";
        return "Current volume is " + currentVolume;
    }

    public static void main(String[] args) {
        new TVService("Kevin's");
//        new TVService("Karry's TV");
//        new TVService("Dominic's TV");
        /*  new TVService("Karry's TVService");
        new TVService("Kevin's TVService");
        new TVService("Living Room");
        new TVService("Kitchen");*/
    }

}
