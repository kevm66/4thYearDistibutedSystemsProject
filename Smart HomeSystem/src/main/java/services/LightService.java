package services;

import java.util.Timer;
import java.util.TimerTask;

import serviceui.ServiceUI;

/**
 * The Class LightService.
 */
public class LightService extends Service {

    private final Timer timer;
    private int bright;
    private int dark;
    private int percentHot; //was causing error when missing
    private static boolean on, off;
    
    public LightService(String name) {
        super(name, "_light._udp.local.");
        timer = new Timer();
        percentHot = 0;
        ui = new ServiceUI(this, name);
        
        
        
    }

    @Override
    public void performAction(String a) {
        if (a.equals("get_status")) {
            sendBack(getStatus());

//Increase Light
        } else if (a.equals("Warm")) {
// every task should run every 2 seconds
            timer.schedule(new RemindTask(), 0, 2000);
            sendBack("OK");
            ui.updateArea("Warming ");
            
//Decrease Light       



//Turn On light


//Turn Off light
            
            
        } else {
            sendBack(BAD_COMMAND + " - " + a);
        }
    }

    class RemindTask extends TimerTask {

        @Override
        public void run() {
            if (percentHot < 100) {
// every time run method gets called it adds 10% until it goes to 100^
                percentHot += 10;
            }
        }
    }

    @Override
    public String getStatus() {
        return "Bed is " + percentHot + "% warmed.";
    }

    public static void main(String[] args) {
        new LightService("Dominic's Bedroom");
      /*  new LightService("Karry's Bedroom");
        new LightService("Kevin's Bedroom");
        new LightService("Living Room");
        new LightService("Kitchen");*/
    }
    
    
}
