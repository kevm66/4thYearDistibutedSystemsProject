package services;

import java.util.Timer;
import java.util.TimerTask;

import serviceui.ServiceUI;

/**
 * The Class TVService Service.
 */
public class TVService extends Service {

    private final Timer timer;
    private int percentHot;

    public TVService(String name) {
        super(name, "_tv._udp.local.");
        timer = new Timer();
        percentHot = 0;
        ui = new ServiceUI(this, name);
    }

    @Override
    public void performAction(String a) {
        if (a.equals("get_status")) {
            sendBack(getStatus());
        } else if (a.equals("Warm")) {
// every task should run every 2 seconds
            timer.schedule(new RemindTask(), 0, 2000);
            sendBack("OK");
            ui.updateArea("Warming TV");
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
        return "TV is " + percentHot + "% warmed.";
    }

    public static void main(String[] args) {
        new TVService("Dominic's TV");
      /*  new TVService("Karry's TVService");
        new TVService("Kevin's TVService");
        new TVService("Living Room");
        new TVService("Kitchen");*/
    }
    
    
}
