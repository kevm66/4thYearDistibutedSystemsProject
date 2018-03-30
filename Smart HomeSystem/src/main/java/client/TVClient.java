/*
 * 
 */
package client;

import clientui.TVUI;

/**
 * TV Client.
 *
 * @author dominic
 */
public class TVClient extends Client {

    private final String WARM = "Warm";
    private boolean isWarming = false;

    /**
     * TV Client Constructor.
     */
    public TVClient() {
        super();
        serviceType = "_tv._udp.local.";
        ui = new TVUI(this);
        name = "TV";
    }

    /**
     * sends a message to warm the tv.
     */
    public void warm() {
        if (!isWarming) {
            String a = sendMessage(WARM);
            if (a.equals(OK)) {
                isWarming = true;
                ui.updateArea("TV is Warming");
            }
        } else {
            ui.updateArea("TV already Warming");
        }
    }

    @Override
    public void updatePoll(String msg) {
        if (msg.equals("TV is 100% warmed.")) {
            isWarming = false;
        }
    }

    @Override
    public void disable() {
        super.disable();
        ui = new TVUI(this);
        isWarming = false;
    }
}
