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
    private static boolean isOn, isOff, isChangingChannel, isChangingVolume, isConnecting, isConnected;
    private String pass = "pass";
    Gson gson = new Gson();
    
    public TVService(String name) {
        super(name, "_tv._udp.local.");
        timer = new Timer();
        percentHot = 0;
        ui = new ServiceUI(this, name);
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
        
        //power on
        else if (a.equals("On")) {
            powerOn();
            String msg = (isOn) ? "The TV is on" : "TV is on";
            String json = gson.toJson(pass);
            sendBack(json);

            String serviceMessage = (isOn) ? "The TV is on" : "...";
            ui.updateArea(serviceMessage);
        }
       
        //power off
        else if (a.equals("Off")) {
            powerOff();
            String msg = (isOff) ? "The TV is off" : "TV is off";
            String json = gson.toJson(pass);
            sendBack(json);

            String serviceMessage = (isOn) ? "The TV is on" : "...";
            ui.updateArea(serviceMessage);
        }
        
        //next channel
        else if (a.equals("Next Channel")) {
            nextChannel();
            String msg = (isChangingChannel) ? "Switching to the next channel" : "TV is switching channel";
            String json = gson.toJson(pass);
//            ui.updateArea(currentChannel);//int cannot be converted to string
            System.out.println(currentChannel);
            sendBack(json);
            String serviceMessage = (isOff) ? "The TV is on" : "...";
            ui.updateArea(serviceMessage);
        }
        
        //previous channel
        else if (a.equals("Previous Channel")) {
            previousChannel();
            String msg = (isOff) ? "The TV is off" : "TV is off";
            String json = gson.toJson(pass);
//            ui.updateArea(currentChannel);//int cannot be converted to string
            System.out.println(currentChannel);
            sendBack(json);
            String serviceMessage = (isOff) ? "The TV is on" : "...";
            ui.updateArea(serviceMessage);
        }
        
        //increase volume
        
        //decrease volume
        
        //connect to speaker
        
        //disconnect from speaker
        
        //error
        else {
            sendBack(BAD_COMMAND + " - " + a);
        }
       
    }
    
    public void powerOn() {
        ui.updateArea("TV is turned on");
    }
    
    public void powerOff() {
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
        //if not connected to speaker
            //connect to speaker
            //set audio to speaker
            //ui.updateArea("connecting to speaker");
        //if already connected to speaker
            //do nothing
            //ui.updateArea("already connected to speaker");
    }
    
    public void disconnectFromSpeaker(){
        //if connected to speaker
            //disconnect from speaker
            //set audio to built-in TV audio
            //ui.updateArea("disconnecting from speaker. Audio set to TV built-in audio");
        //if not connected to speaker
            //do nothing
            //ui.updateArea("already disconnected from speaker");
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
