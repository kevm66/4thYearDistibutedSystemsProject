/*
 * 
 */
package client;

import clientui.LightUI;

/**
 * Bed Client.
 *
 * @author dominic
 */
public class LightClient extends Client {

    private final String WARM = "Warm";
    private boolean isWarming = false;

    /**
     * Bed Client Constructor.
     */
    public LightClient() {
        super();
        serviceType = "_light._udp.local.";
        ui = new LightUI(this);
        name = "Light System";
    }

    /**
     * sends a message to warm the bed.
     */
    public void warm() {
        if (!isWarming) {
            String a = sendMessage(WARM);
            if (a.equals(OK)) {
                isWarming = true;
                ui.updateArea("Bed is Warming");
            }
        } else {
            ui.updateArea("Bed already Warming");
        }
    }

    @Override
    public void updatePoll(String msg) {
        if (msg.equals("Bed is 100% warmed.")) {
            isWarming = false;
        }
    }

    @Override
    public void disable() {
        super.disable();
        ui = new LightUI(this);
        isWarming = false;
    }
}
