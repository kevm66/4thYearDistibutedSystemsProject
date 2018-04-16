package client;

import clientui.OvenUI;
import com.google.gson.Gson;
import models.OvenModel;

/*
 * @OvenClient.java							
 *
 * @author:Karolina Laptas, x14446332
 *
 * @reference sample by Dominic Carr https://moodle.ncirl.ie/course/view.php?id=1473	
 */
public class OvenClient extends Client {

    private final String warmTemp = "warm temperature";
    private final String coolTemp = "cold temperature";
    private final String Off = "Off";
    private final String On = "On";
    private final String setTime = "set timer";
    private final String resetTime = "reset timer";
    private boolean isWarming = false;

    /**
     * Bed Client Constructor.
     */
    public OvenClient() {
        super();
        serviceType = "_oven._udp.local.";
        ui = new OvenUI(this);
        name = "Oven";
    }

    /**
     * sends a message to warm the oven temperature.
     */
    public void warmTemp() {
        String json = new Gson().toJson(new OvenModel(OvenModel.Action.warm));
        String a = sendMessage(json);
        OvenModel ovenM = new Gson().fromJson(a, OvenModel.class);
        System.out.println("Client Received " + json);

        if (ovenM.getAction() == OvenModel.Action.warm) {
            isWarming = ovenM.getValue();
            ui.updateArea(ovenM.getMessage());
        }
    }

//decrease oven temperature 
    public void coolTemp() {
        String json = new Gson().toJson(new OvenModel(OvenModel.Action.cool));
        String a = sendMessage(json);
        OvenModel ovenM = new Gson().fromJson(a, OvenModel.class);
        System.out.println("Client Received " + json);

        if (ovenM.getAction() == OvenModel.Action.cool) {
            isWarming = ovenM.getValue();
            ui.updateArea(ovenM.getMessage());
        }
    }
//turn off oven messages

    public void powerOff() {
        String json = new Gson().toJson(new OvenModel(OvenModel.Action.Off));
        String a = sendMessage(json);
        OvenModel ovenM = new Gson().fromJson(a, OvenModel.class);
        System.out.println("Client Received " + json);

        if (ovenM.getAction() == OvenModel.Action.Off) {
            isWarming = ovenM.getValue();
            ui.updateArea(ovenM.getMessage());
        }
    }

//turn on oven messages
    public void powerOn() {
        String json = new Gson().toJson(new OvenModel(OvenModel.Action.On));
        String a = sendMessage(json);
        OvenModel ovenM = new Gson().fromJson(a, OvenModel.class);
        System.out.println("Client Received " + json);

        if (ovenM.getAction() == OvenModel.Action.On) {
            isWarming = ovenM.getValue();
            ui.updateArea(ovenM.getMessage());
        }
    }
//set timer message

    public void set() {
        String json = new Gson().toJson(new OvenModel(OvenModel.Action.setTimer));
        String a = sendMessage(json);
        OvenModel ovenM = new Gson().fromJson(a, OvenModel.class);
        System.out.println("Client Received " + json);

        if (ovenM.getAction() == OvenModel.Action.setTimer) {
            isWarming = ovenM.getValue();
            ui.updateArea(ovenM.getMessage());
        }
    }
//reset timer message

    public void reset() {
        String json = new Gson().toJson(new OvenModel(OvenModel.Action.reset));
        String a = sendMessage(json);
        OvenModel ovenM = new Gson().fromJson(a, OvenModel.class);
        System.out.println("Client Received " + json);

        if (ovenM.getAction() == OvenModel.Action.reset) {
            isWarming = ovenM.getValue();
            ui.updateArea(ovenM.getMessage());
        }
    }

    public void fan() {
        String json = new Gson().toJson(new OvenModel(OvenModel.Action.fan));
        String a = sendMessage(json);
        OvenModel ovenM = new Gson().fromJson(a, OvenModel.class);
        System.out.println("Client Received " + json);

        if (ovenM.getAction() == OvenModel.Action.fan) {
            isWarming = ovenM.getValue();
            ui.updateArea(ovenM.getMessage());
        }
    }

    public void externalFan() {
        String json = new Gson().toJson(new OvenModel(OvenModel.Action.external));
        String a = sendMessage(json);
        OvenModel ovenM = new Gson().fromJson(a, OvenModel.class);
        System.out.println("Client Received " + json);

        if (ovenM.getAction() == OvenModel.Action.external) {
            isWarming = ovenM.getValue();
            ui.updateArea(ovenM.getMessage());
        }
    }

    public void defrost() {
        String json = new Gson().toJson(new OvenModel(OvenModel.Action.defrost));
        String a = sendMessage(json);
        OvenModel ovenM = new Gson().fromJson(a, OvenModel.class);
        System.out.println("Client Received " + json);

        if (ovenM.getAction() == OvenModel.Action.defrost) {
            isWarming = ovenM.getValue();
            ui.updateArea(ovenM.getMessage());
        }
    }

    public void conventinalGrill() {
        String json = new Gson().toJson(new OvenModel(OvenModel.Action.both));
        String a = sendMessage(json);
        OvenModel ovenM = new Gson().fromJson(a, OvenModel.class);
        System.out.println("Client Received " + json);

        if (ovenM.getAction() == OvenModel.Action.both) {
            isWarming = ovenM.getValue();
            ui.updateArea(ovenM.getMessage());
        }
    }

    public void topOven() {
        String json = new Gson().toJson(new OvenModel(OvenModel.Action.top));
        String a = sendMessage(json);
        OvenModel ovenM = new Gson().fromJson(a, OvenModel.class);
        System.out.println("Client Received " + json);

        if (ovenM.getAction() == OvenModel.Action.top) {
            isWarming = ovenM.getValue();
            ui.updateArea(ovenM.getMessage());
        }
    }

    public void baseOven() {
        String json = new Gson().toJson(new OvenModel(OvenModel.Action.base));
        String a = sendMessage(json);
        OvenModel ovenM = new Gson().fromJson(a, OvenModel.class);
        System.out.println("Client Received " + json);

        if (ovenM.getAction() == OvenModel.Action.base) {
            isWarming = ovenM.getValue();
            ui.updateArea(ovenM.getMessage());
        }
    }

    @Override
    public void updatePoll(String msg) {
        if (msg.equals("Oven is 250ºC, on its Max temperature.")) {
            isWarming = false;
        }
    }

    @Override
    public void disable() {
        super.disable();
        ui = new OvenUI(this);
        isWarming = false;
    }
}
