/*
 * 
 */
package client;

import clientui.SpeakerUI;

/**
 * Speaker Client.
 *
 * @author dominic
 */
public class SpeakerClient extends Client {

    private final String WARM = "Warm";
    private boolean isWarming = false;

    /**
     * Speaker Client Constructor.
     */
    public SpeakerClient() {
        super();
        serviceType = "_speaker._udp.local.";
        ui = new SpeakerUI(this);
        name = "Speaker";
    }

    /**
     * sends a message to warm the speaker.
     */
    public void warm() {
        if (!isWarming) {
            String a = sendMessage(WARM);
            if (a.equals(OK)) {
                isWarming = true;
                ui.updateArea("Speaker is Warming");
            }
        } else {
            ui.updateArea("Speaker already Warming");
        }
    }

    @Override
    public void updatePoll(String msg) {
        if (msg.equals("Speaker is 100% warmed.")) {
            isWarming = false;
        }
    }

    @Override
    public void disable() {
        super.disable();
        ui = new SpeakerUI(this);
        isWarming = false;
    }
}
