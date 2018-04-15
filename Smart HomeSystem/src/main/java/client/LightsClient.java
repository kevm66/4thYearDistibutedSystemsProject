package client;

import clientui.LightsUI;
import com.google.gson.Gson;
import models.LightsModel;

/*
*@author : Karolina Laptas
*
*@reference sample by Dominic Carr https://moodle.ncirl.ie/course/view.php?id=1473
*/

public class LightsClient extends Client {

    private final String lightenUP = "lightenUP";
    private final String lighenDown = "dim the lights";
    private final String turnOff = "turn off";
    private final String turnOn = "Turn on";
    private boolean modify = false;

    /**
     * Bed Client Constructor.
     */
    public LightsClient() {
        super();
        serviceType = "_lights._udp.local.";
        ui = new LightsUI(this);
        name = "Office Lights";
    }

    /**
     * sends a message to brighten the room
     */
    public void brighten_lights() {
        String json = new Gson().toJson(new LightsModel(LightsModel.Action.lighten));
        String a = sendMessage(json);
        LightsModel lights = new Gson().fromJson(a, LightsModel.class);
        System.out.println("Client Received " + json);

        if (lights.getAction() == LightsModel.Action.lighten) {
            modify = lights.getValue();
            ui.updateArea(lights.getMessage());
        }
    }

    //send a message to dim lights
    public void dim_lights() {
        String json = new Gson().toJson(new LightsModel(LightsModel.Action.darken));
        String a = sendMessage(json);
        LightsModel lights = new Gson().fromJson(a, LightsModel.class);
        System.out.println("Client Received " + json);

        if (lights.getAction() == LightsModel.Action.darken) {
            modify = lights.getValue();
            ui.updateArea(lights.getMessage());
        }
    }

    //turn off lights message
    public void turn_off() {
        String json = new Gson().toJson(new LightsModel(LightsModel.Action.lightOff));
        String a = sendMessage(json);
        LightsModel lights = new Gson().fromJson(a, LightsModel.class);
        System.out.println("Client Received " + json);

        if (lights.getAction() == LightsModel.Action.lightOff) {
            modify = lights.getValue();
            ui.updateArea(lights.getMessage());
        }
    }

    //turn on lights message
    public void turn_on() {
        String json = new Gson().toJson(new LightsModel(LightsModel.Action.lightOn));
        String a = sendMessage(json);
        LightsModel lights = new Gson().fromJson(a, LightsModel.class);
        System.out.println("Client Received " + json);

        if (lights.getAction() == LightsModel.Action.lightOn) {
            modify = lights.getValue();
            ui.updateArea(lights.getMessage());
        }
    }

    @Override
    public void updatePoll(String msg) {
        if (msg.equals("Lights are fully bright.")) {
            modify = false;
        }
    }

    @Override
    public void disable() {
        super.disable();
        ui = new LightsUI(this);
        modify = false;
    }
}
