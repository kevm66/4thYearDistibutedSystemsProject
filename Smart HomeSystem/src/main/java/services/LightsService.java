package services;

import com.google.gson.Gson;
import models.LightsModel;
import clientui.LightsUI;

import serviceui.ServiceUI;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;

/*
 * @OvenService.java							
 *
 * @author:Karolina Laptas, x14446332
 *
 * @reference sample by Dominic Carr https://moodle.ncirl.ie/course/view.php?id=1473	
 */
public class LightsService extends Service {

    private int lighest;
    private int darkest;
    private int currentLight;
    private static boolean isLightened, isDarken, off, on;

    String broker = "tcp://iot.eclipse.org:1883";
    String clientId = "Subscriber";
    MemoryPersistence persistence = new MemoryPersistence();
    private MqttClient mqttClient;

    public LightsService(String name) {
        super(name, "_lights._udp.local.");
        lighest = 100; //maximum brightness of the room
        darkest = 30; //lowest dimming settings
        currentLight = 0; //defaault standard room tbrightness with lights
        isLightened = false;
        isDarken = false;
        off = true;
        on = false;
        ui = new ServiceUI(this, name);

        try {
            MqttClient SubscriberClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            SubscriberClient.setCallback(new SampleSubscriber());
            System.out.println("Connecting to broker: " + broker);
            SubscriberClient.connect(connOpts);
            System.out.println("Connected");
            SubscriberClient.subscribe("/house/#");

            //Subscribe to all subtopics of home
            final String topic = "home/#";

            System.out.println("Subscriber is now listening to " + topic);
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
            System.exit(1);
        }

    }

    class SampleSubscriber implements MqttCallback {

        public SampleSubscriber() {

        }

        @Override
        public void connectionLost(Throwable thrwbl) {
        }

        @Override
        public void messageArrived(String string, MqttMessage mm) throws Exception {
            System.out.println(mm + " arrived from topic " + string);
        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken imdt) {
        }

    }

    @Override
    public void performAction(String a) {
        System.out.println("recieved: " + a);
        LightsModel lights = new Gson().fromJson(a, LightsModel.class);

        if (lights.getAction() == LightsModel.Action.STATUS) {
            String msg = getStatus();
            String json = new Gson().toJson(new LightsModel(LightsModel.Action.STATUS, msg));
            sendBack(json);
        } //BRIGHTEN ROOM
        else if (lights.getAction() == LightsModel.Action.lighten) {
            brighten_lights();
            String msg = (isLightened) ? "The Room is brightening by 10%" : "The room cant get any brighter";
            String json = new Gson().toJson(new LightsModel(LightsModel.Action.lighten, msg));
            System.out.println(json);
            sendBack(json);

            String serviceMessage = (isLightened) ? "The lights brightened!" : "The lights cant get any brighter..";
            ui.updateArea(serviceMessage);
        } //DIM ROOM
        else if (lights.getAction() == LightsModel.Action.darken) {
            dim_lights();
            String msg = (isDarken) ? "The Room is dimming by 10%" : "The lights cant dim any lower";
            String json = new Gson().toJson(new LightsModel(LightsModel.Action.darken, msg));
            System.out.println(json);
            sendBack(json);

            String serviceMessage = (isDarken) ? "The Room is dimming!" : "Sorry the room cant dim any more..";
            ui.updateArea(serviceMessage);
        } //TURN OFF LIGHTS
        else if (lights.getAction() == LightsModel.Action.lightOff) {
            turn_off_lights();
            String msg = (off) ? "The Lights have been turned off" : "Lights are already off";
            String json = new Gson().toJson(new LightsModel(LightsModel.Action.lightOff, msg));
            System.out.println(json);
            sendBack(json);

            String serviceMessage = (off) ? "Lights turned off" : "Lights are off";
            ui.updateArea(serviceMessage);
        } //TURN ON LIGHTS
        else if (lights.getAction() == LightsModel.Action.lightOn) {
            turn_on_lights();
            String msg = (on) ? "The Lights have been turned on" : "Lights have been turned on";
            String json = new Gson().toJson(new LightsModel(LightsModel.Action.lightOn, msg));
            System.out.println(json);
            sendBack(json);

            String serviceMessage = (on) ? "Lights turned on" : "Lights are on";
            ui.updateArea(serviceMessage);
        } else {
            sendBack(BAD_COMMAND + " - " + a);
        }
    }

    public void turn_off_lights() {
        if (currentLight >= 0) {
            currentLight = 0;
            System.out.println("Lights Turned off");
        }
    }

    public void turn_on_lights() {
        if (currentLight <= 0) {
            currentLight += 100;
            System.out.println("Room is" + currentLight + "% bright");
        }
    }

    public void brighten_lights() {
        if (currentLight != lighest) {
            isLightened = true;
            currentLight += 10;
        } else {
            isLightened = false;
        }
    }

    public void dim_lights() {
        if (currentLight != darkest) {
            isDarken = true;
            currentLight -= 10;
        } else {
            isDarken = false;
        }
    }

    @Override
    public String getStatus() {
        return "Room is " + currentLight + "% bright";
    }

    public static void main(String[] args) {
        new LightsService("Hall");
        new LightsService("Room 1");
        new LightsService("Room 2");
        new LightsService("Room 3");
        new LightsService("Living Room");
        new LightsService("Kitchen");
        new LightsService("Bathroom");

    }
}
