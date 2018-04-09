package services;

import com.google.gson.Gson;
import java.util.Timer;
import java.util.TimerTask;
import model.OvenModel;

import serviceui.ServiceUI;

/*
 *File Title: OvenService.java							
 *
 * @author:Karolina Laptas, x14446332
 *
 * @reference sample by Dominic Carr https://moodle.ncirl.ie/course/view.php?id=1473	
 */
public class OvenService extends Service {

    private final Timer timer;
    private int percentHot;
    private int percentCold;
    private int currentTemp;
    private static boolean on, off, preHeat, turnDownTemp;

    public OvenService(String name) {
        super(name, "_oven._udp.local.");
        timer = new Timer();
        percentHot = -1;
        percentCold = 0;
        on = false;
        off = true;
        preHeat = false;
        ui = new ServiceUI(this, name);
    }

    @Override
    public void performAction(String a) {
        /* if (a.equals("get_status")) {
            sendBack(getStatus());
        } else if (a.equals("Warm")) {
// every task should run every 2 seconds
            timer.schedule(new RemindTask(), 0, 2000);
            sendBack("OK");
            ui.updateArea("Warming Oven");
         */

//get status of Oven process     
        System.out.println("received: " + a);
        OvenModel oven = new Gson().fromJson(a, OvenModel.class);

       /* if (oven.getAction() == OvenModel.Action.STATUS) {
            String msg = getStatus();
            String json = new Gson().toJson(new OvenModel(OvenModel.Action.STATUS, msg));
            sendBack(json);
// Pre-Heat Oven            
        } else if (oven.getAction() == OvenModel.Action.PREHEAT) {
            preHeat();
            String msg = (preHeat) ? "Oven is already pre-heating.." : "The Oven is pre-heating";
            String json = new Gson().toJson(new OvenModel(OvenModel.Action.PREHEAT, msg, preHeat));
            System.out.println(json);
            sendBack(json);

            String serviceMessage = (preHeat) ? "Oven is already pre-heating" : "The oven is heated..";
            ui.updateArea(serviceMessage);

// Turn on oven
        } else if (oven.getAction() == OvenModel.Action.ON) {
            power_on();
            String msg = (on) ? "The Oven is turned on" : "The Oven is on";
            String json = new Gson().toJson(new OvenModel(OvenModel.Action.ON, msg));
            System.out.println(json);
            sendBack(json);

            String serviceMessage = (on) ? "The oven is turned on" : "The oven is on";
            ui.updateArea(serviceMessage);

// Turn off Oven
        } else if (oven.getAction() == OvenModel.Action.OFF) {
            power_off();
            String msg = (off) ? "The Oven is turned off" : "The Oven is off";
            String json = new Gson().toJson(new OvenModel(OvenModel.Action.OFF, msg));
            System.out.println(json);
            sendBack(json);

            String serviceMessage = (off) ? "The oven is turned off" : "The oven is off";
            ui.updateArea(serviceMessage);

// Turn temperature down
        } else {
            sendBack(BAD_COMMAND + " - " + a);
        }
*/
    }

    public void increase_temp() {
        if (currentTemp != percentHot) {
            preHeat = true;
            currentTemp += 5;
        } else {
            preHeat = false;
        }
    }

    public void decrease_temp() {
        if (currentTemp != percentCold) {
            turnDownTemp = true;
            currentTemp -= 5;
        } else {
            turnDownTemp = false;
        }
    }

    public void power_off() {
        System.out.println("Oven is  turned Off");
    }

    public void power_on() {
        System.out.println("Oven is turned On");
    }

    private void preHeat() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    class RemindTask extends TimerTask {

        @Override
        public void run() {
            if (percentHot < 250) {
// every time run method gets called it adds 10% until it goes to 100^
                percentHot += 10;
            }
        }
    }

    @Override
    public String getStatus() {
        return "Oven is " + percentHot + "ºC warmed.";
    }

    public static void main(String[] args) {
        new OvenService("Oven");
        /*  new BedService("Karry's Bedroom");
        new BedService("Kevin's Bedroom");
        new BedService("Living Room");
        new BedService("Kitchen");*/
    }

}
