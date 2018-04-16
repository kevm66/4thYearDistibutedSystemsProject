package client;

import clientui.LightsUI;
import com.google.gson.Gson;
import models.LightsModel;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/* @File Title: LightsClient.java							
 *
 * @author:Karolina Laptas, x14446332
 *
 * @reference sample by Dominic Carr https://moodle.ncirl.ie/course/view.php?id=1473	
 */
public class LightsClient extends Client {

    private final String lightenUP = "lightenUP";
    private final String lighenDown = "dim the lights";
    private final String turnOff = "turn off";
    private final String turnOn = "Turn on";
    private boolean modify = false;
    
    

    public static final String BROKER_URL = "tcp://broker.mqttdashboard.com:1883";
    
    
    public static final String TOPIC_BRIGHTNESS = "home/brightness";
    public static final String TOPIC_TEMPERATURE = "home/temperature";
    String topic = "/house/black/world";
    String content = "SUNSET UPDATE: The lights will be turned on at 7:25 pm today.\n"
            + "WEATHER UPDATE: Today will be provede some sunny spells with minor clouds in the evening. ";
    int qos = 2;
    String broker = "tcp://iot.eclipse.org:1883";
    String clientId = "Publisher";
    MemoryPersistence persistence = new MemoryPersistence();

    /**
     * Bed Client Constructor.
     */
    public LightsClient() {
        super();
        serviceType = "_lights._udp.local.";
        ui = new LightsUI(this);
        name = "Office Lights";

        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            connOpts.setWill(sampleClient.getTopic("home/LWT"), "I'm gone :(".getBytes(), 0, false);
            System.out.println("Connecting to broker: " + broker);
            sampleClient.connect(connOpts);
            System.out.println("Connected");
            System.out.println("Publishing message: " + content);
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            message.setRetained(false);
            sampleClient.publish(topic, message);
            System.out.println("Message published");
            //    sampleClient.disconnect();

            //   System.out.println("Disconnected");
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }
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
 public void blueLight() {
         String json = new Gson().toJson(new LightsModel(LightsModel.Action.blue));
        String a = sendMessage(json);
        LightsModel lights = new Gson().fromJson(a, LightsModel.class);
        System.out.println("Client Received " + json);

        if (lights.getAction() == LightsModel.Action.blue) {
            modify = lights.getValue();
            ui.updateArea(lights.getMessage());
      }
     }
    public void greenLight() {
        String json = new Gson().toJson(new LightsModel(LightsModel.Action.green));
        String a = sendMessage(json);
        LightsModel lights = new Gson().fromJson(a, LightsModel.class);
        System.out.println("Client Received " + json);

        if (lights.getAction() == LightsModel.Action.green) {
            modify = lights.getValue();
            ui.updateArea(lights.getMessage());}
    }

    public void orangeLight() {
       String json = new Gson().toJson(new LightsModel(LightsModel.Action.orange));
        String a = sendMessage(json);
        LightsModel lights = new Gson().fromJson(a, LightsModel.class);
        System.out.println("Client Received " + json);

        if (lights.getAction() == LightsModel.Action.orange) {
            modify = lights.getValue();
            ui.updateArea(lights.getMessage());
        }

}
    public void purpleLight() {
     String json = new Gson().toJson(new LightsModel(LightsModel.Action.purple));
        String a = sendMessage(json);
        LightsModel lights = new Gson().fromJson(a, LightsModel.class);
        System.out.println("Client Received " + json);

        if (lights.getAction() == LightsModel.Action.purple) {
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