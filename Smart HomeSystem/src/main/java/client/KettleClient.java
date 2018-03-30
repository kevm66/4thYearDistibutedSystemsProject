/*
 * 
 */
package client;

import clientui.KettleUI;

/**
 * Kettle Client.
 *
 * @author dominic
 */
public class KettleClient extends Client {

    private final String WARM = "Warm";
    private boolean isWarming = false;

    /**
     * Kettle Client Constructor.
     */
    public KettleClient() {
        super();
        serviceType = "_kettle._udp.local.";
        ui = new KettleUI(this);
        name = "Kettle";
    }

    /**
     * sends a message to warm the kettle.
     */
    public void warm() {
        if (!isWarming) {
            String a = sendMessage(WARM);
            if (a.equals(OK)) {
                isWarming = true;
                ui.updateArea("Kettle is Warming");
            }
        } else {
            ui.updateArea("Kettle already Warming");
        }
    }

    @Override
    public void updatePoll(String msg) {
        if (msg.equals("Kettle is 100% warmed.")) {
            isWarming = false;
        }
    }

    @Override
    public void disable() {
        super.disable();
        ui = new KettleUI(this);
        isWarming = false;
    }
}
