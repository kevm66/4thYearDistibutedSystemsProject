package services;

/* @File Title: ClientManager.java							
 *
 * @author:Kevin Maher,     x14328981
 *
 * @reference sample by Dominic Carr https://moodle.ncirl.ie/course/view.php?id=1473	
 */
import com.google.gson.Gson;
import java.util.Date;
import models.SpeakerModel;
import serviceui.ServiceUI;

public class SpeakerService extends Service {

    private int currentVolume;
    private final int lowestVolume, highestVolume;
    private static boolean isOn, isOff, isIncreasingVolume, isDecreasingVolume;
    private static boolean isCheckingWeather, isConnected, isPlaying;
    private static boolean isConnecting, isDisconnecting, connectionStatus;
    private String currentWeather, currentLocation;
    private String pass = "pass";
//    private Date dateUpdated;
    private String dateUpdated;
    private String timeUpdated;
    private String songName, artistName;
    Gson gson = new Gson();

    public SpeakerService(String name) {
        super(name, "_speaker._udp.local.");
        currentVolume = 40;
        lowestVolume = 0;
        highestVolume = 100;
        isOn = false;
        isOff = true;
        isConnecting = false;
        isConnected = false;
        isPlaying = false;
        isCheckingWeather = false;
        currentLocation = "Dublin"; //update
        currentWeather = "Sunny"; //update
        dateUpdated = "2018/01/01"; //update
        timeUpdated = "11:05"; //update
        songName = "All I Want for Christmas";
        artistName = "Mariah Carey";

        ui = new ServiceUI(this, name);
    }

    @Override
    public void performAction(String a) {
        System.out.println("recieved: " + a);
        SpeakerModel speakerM = new Gson().fromJson(a, SpeakerModel.class);

        //get status
        if (speakerM.getAction() == SpeakerModel.Action.STATUS) {
            String msg = getStatus();
            String json = new Gson().toJson(new SpeakerModel(SpeakerModel.Action.STATUS, msg));
            sendBack(json);
        } //power on speaker
        else if (speakerM.getAction() == SpeakerModel.Action.ON) {
            powerOn();
            String msg = (isOn) ? "This speaker is on\n" : "Speaker has been switched on\n";
            String json = new Gson().toJson(new SpeakerModel(SpeakerModel.Action.ON, msg));
            System.out.println(json);
            sendBack(json);

            String serviceMessage = (isOn) ? "The speaker is on\n" : "Switching on speaker...\n";
            ui.updateArea(serviceMessage);
        } //power off TV
        else if (speakerM.getAction() == SpeakerModel.Action.OFF) {
            powerOff();
            String msg = (isOff) ? "Speaker has been switched off\n" : "Speaker has been switched on\n";
            String json = new Gson().toJson(new SpeakerModel(SpeakerModel.Action.OFF, msg));
            System.out.println(json);
            sendBack(json);

            String serviceMessage = (isOff) ? "Switching off speaker...\n" : "...";
            ui.updateArea(serviceMessage);
        } //check weather
        else if (speakerM.getAction() == SpeakerModel.Action.CHECK_WEATHER) {
            checkWeather();
            String msg = (isCheckingWeather) ? "Current weather in " + currentLocation + ": " + currentWeather + "\n"
                    : "Current weather in " + currentLocation + ": " + currentWeather + "\n";
            String json = new Gson().toJson(new SpeakerModel(SpeakerModel.Action.CHECK_WEATHER, msg));
            System.out.println(json);
            sendBack(json);

            String serviceMessage = (isCheckingWeather) ? "Current weather in " + currentLocation + ": " + currentWeather + "\n"
                    : "Checking the weather in " + currentLocation + "...\n";
            ui.updateArea(serviceMessage);
        } //play music
        else if (speakerM.getAction() == SpeakerModel.Action.PLAY_MUSIC) {
            checkWeather();
            String msg = (isCheckingWeather) ? "Playing '" + songName + "' by '" + artistName + "'\n"
                    : "Playing '" + songName + "' by '" + artistName + "'\n";
            String json = new Gson().toJson(new SpeakerModel(SpeakerModel.Action.PLAY_MUSIC, msg));
            System.out.println(json);
            sendBack(json);

            String serviceMessage = (isCheckingWeather) ? "Playing '" + songName + "' by '" + artistName + "'\n"
                    : "Playing music...\n";
            ui.updateArea(serviceMessage);
        } //increase volume
        else if (speakerM.getAction() == SpeakerModel.Action.INCREASE_VOLUME) {
            increaseVolume();
            String msg = (isIncreasingVolume) ? "Volume has been set to" + currentVolume + "\n" : "Volume set to " + currentVolume + "\n";
            String json = new Gson().toJson(new SpeakerModel(SpeakerModel.Action.INCREASE_VOLUME, msg));
            System.out.println(json);
            sendBack(json);

            String serviceMessage = (isIncreasingVolume) ? "Current volume: " + currentVolume + "\n" : "Volume is increasing...\n";
            ui.updateArea(serviceMessage);
        } //decrease volume
        else if (speakerM.getAction() == SpeakerModel.Action.DECREASE_VOLUME) {
            decreaseVolume();
            String msg = (isDecreasingVolume) ? "Volume has been set to" + currentVolume + "\n" : "Volume set to " + currentVolume + "\n";
            String json = new Gson().toJson(new SpeakerModel(SpeakerModel.Action.DECREASE_VOLUME, msg));
            System.out.println(json);
            sendBack(json);

            String serviceMessage = (isDecreasingVolume) ? "Current volume: " + currentVolume + "\n" : "Volume is decreasing...\n";
            ui.updateArea(serviceMessage);
        } //connect to speaker
        else if (speakerM.getAction() == SpeakerModel.Action.CONNECT) {
            decreaseVolume();
            String msg = (isConnecting) ? "Speaker connected to speaker: " + connectionStatus + "\n" : "Speaker connected to speaker: " + connectionStatus + "\n";
            String json = new Gson().toJson(new SpeakerModel(SpeakerModel.Action.CONNECT, msg));
            System.out.println(json);
            sendBack(json);

            String serviceMessage = (isConnecting) ? "Speaker connected to speaker: " + connectionStatus + "\n" : "Speaker is connecting to TV...\n";
            ui.updateArea(serviceMessage);
        } //disconnect from speaker
        else if (speakerM.getAction() == SpeakerModel.Action.DISCONNECT) {
            decreaseVolume();
            String msg = (isDisconnecting) ? "Speaker disconnected from speaker: " + connectionStatus + "\n" : "Speaker disconnected from speaker: " + connectionStatus + "\n";
            String json = new Gson().toJson(new SpeakerModel(SpeakerModel.Action.DISCONNECT, msg));
            System.out.println(json);
            sendBack(json);

            String serviceMessage = (isDisconnecting) ? "Speaker disconnected from speaker: " + connectionStatus + "\n" : "Speaker is disconnecting from TV...\n";
            ui.updateArea(serviceMessage);
        } //error
        else {
            sendBack(BAD_COMMAND + " - " + a);
        }

    }

    public void powerOn() { //powerOnSpeaker
        System.out.println("Speaker is turned on.\n");
    }

    public void powerOff() { //powerOffSpeaker
        System.out.println("Speaker is turned off.\n");
    }

    public void playMusic() {
        System.out.println("Playing music.");
    }

    public void checkWeather() {
        System.out.println("Current weather in " + currentLocation + ": " + currentWeather);
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

    public void connectToTV() {
        if (isConnected == false) { //if not connected to the speaker
            //[set audio to speaker]
            ui.updateArea("Connecting to TV.\n");
        } else { //if already connected to speaker, do nothing
            ui.updateArea("Already connected to TV.\n");
        }
    }

    public void disconnectFromTV() {
        if (isConnected == true) { //if already connected to the speaker
            //[disconnect from TV]
            ui.updateArea("Disconnecting from TV.\n");
        } else { //if already connected to TV, do nothing
            ui.updateArea("Already disconnected from tv .\n");
        }
    }

//    class RemindTask extends TimerTask {
//
//        @Override
//        public void run() {
//            if (percentHot < 100) {
//// every time run method gets called it adds 10% until it goes to 100^
//                percentHot += 10;
//            }
//        }
//    }
    @Override
    public String getStatus() {
        return "Current volume: " + currentVolume + "\nCurrent weather in "
                + currentLocation + ": " + currentWeather + "\nLast updated: "
                + dateUpdated + " " + timeUpdated + "\nConnected to TV: " + connectionStatus + "\n";
    }

    public static void main(String[] args) {
        new SpeakerService("Kevin's");
//        new SpeakerService("Karry's ");
//        new SpeakerService("Dominic's");
    }

}
