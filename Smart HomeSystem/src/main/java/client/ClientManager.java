package client;

import java.io.IOException;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;

import clientui.ClientManagerUI;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/* @File Title:ClientManager.java							
 *
 * @author:Karolina Laptas, x14446332
 * @author:Kevin Maher,     x14328981
 *
 * @reference sample by Dominic Carr https://moodle.ncirl.ie/course/view.php?id=1473	
 */
//Control UI frame
public class ClientManager implements ServiceListener {

    private final ClientManagerUI ui;
    private JmDNS jmdns;
  /*  private final OvenClient ovenclient = new OvenClient();
    //private final TVClient tvclient = new TVClient();
    private final SpeakerClient speakerclient = new SpeakerClient();
    private final LightsClient lightclient = new LightsClient();
*/
    private ArrayList<Client> clients;
    
    public ClientManager() {
//Creating instance of jmDNS 
    clients = new ArrayList<>();
        clients.add(new LightsClient());
        clients.add(new OvenClient());
        clients.add(new SpeakerClient());
      //  clients.add(new TVClient());
    
        try {
//Adding service listener            
         jmdns = JmDNS.create(InetAddress.getLocalHost());
         
         for (Client client : clients){
                jmdns.addServiceListener(client.getServiceType(), this);
     /*       jmdns.addServiceListener(ovenclient.getServiceType(), this);
            //jmdns.addServiceListener(tvclient.getServiceType(), this);
            jmdns.addServiceListener(speakerclient.getServiceType(), this);
            jmdns.addServiceListener(lightclient.getServiceType(), this);
*/}
        } catch (IOException e) {
            e.printStackTrace();
        }
        ui = new ClientManagerUI(this);
    }

    public void end() {
        try {
            jmdns.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void serviceAdded(ServiceEvent arg0) {
        System.out.println(arg0);
        arg0.getDNS().requestServiceInfo(arg0.getType(), arg0.getName(), 0);
    }
//service online
    public void serviceRemoved(ServiceEvent arg0) {
        System.out.println(arg0);
        String type = arg0.getType();
        String name = arg0.getName();
        ServiceInfo newService = null;
        
        
        for (Client client : clients) {
            if (client.getServiceType().equals(type) && client.hasMultiple()) {
                if (client.isCurrent(name)) {
                    ServiceInfo[] a = jmdns.list(type);
                    for (ServiceInfo in : a) {
                        if (!in.getName().equals(name)) {
                            newService = in;
                        }
                    }
                    client.switchService(newService);
                }
                client.remove(name);
            } else if (client.getServiceType().equals(type)) {
                ui.removePanel(client.returnUI());
                client.disable();
                client.initialized = false;
            }
        }
        
        
//Switching clients
//Oven Client
     /*   if (ovenclient.getServiceType().equals(type) && ovenclient.hasMultiple()) {
            if (ovenclient.isCurrent(name)) {
                ServiceInfo[] a = jmdns.list(type);
                for (ServiceInfo in : a) {
                    if (!in.getName().equals(name)) {
                        newService = in;
                    }
                }
                ovenclient.switchService(newService);
            }
            ovenclient.remove(name);
        } //Run Thermostat Client
        else if (ovenclient.getServiceType().equals(type)) {
            ui.removePanel(ovenclient.returnUI());
            ovenclient.disable();
            ovenclient.initialized = false;
        } //Run Kettle Client
      /*  else if (tvclient.getServiceType().equals(type) && tvclient.hasMultiple()) {
            if (tvclient.isCurrent(name)) {
                ServiceInfo[] a = jmdns.list(type);
                for (ServiceInfo in : a) {
                    if (!in.getName().equals(name)) {
                        newService = in;
                    }
                }
                tvclient.switchService(newService);
            }
            tvclient.remove(name);
        } else if (tvclient.getServiceType().equals(type)) {
            ui.removePanel(tvclient.returnUI());
            tvclient.disable();
            tvclient.initialized = false;
        }*/ //Run Lights Client
     /*   else if (lightclient.getServiceType().equals(type) && lightclient.hasMultiple()) {
            if (lightclient.isCurrent(name)) {
                ServiceInfo[] a = jmdns.list(type);
                for (ServiceInfo in : a) {
                    if (!in.getName().equals(name)) {
                        newService = in;
                    }
                }
                lightclient.switchService(newService);
            }
            lightclient.remove(name);
        } else if (lightclient.getServiceType().equals(type)) {
            ui.removePanel(lightclient.returnUI());
            lightclient.disable();
            lightclient.initialized = false;
        } //Printer Client
        else if (speakerclient.getServiceType().equals(type) && speakerclient.hasMultiple()) {
            if (speakerclient.isCurrent(name)) {
                ServiceInfo[] a = jmdns.list(type);
                for (ServiceInfo in : a) {
                    if (!in.getName().equals(name)) {
                        newService = in;
                    }
                }
                speakerclient.switchService(newService);
            }
            speakerclient.remove(name);
        } else if (speakerclient.getServiceType().equals(type)) {
            ui.removePanel(speakerclient.returnUI());
            speakerclient.disable();
            speakerclient.initialized = false;
        }
        //TVClient
     /*   else if (tvclient.getServiceType().equals(type) && tvclient.hasMultiple()) {
            if (tvclient.isCurrent(name)) {
                ServiceInfo[] a = jmdns.list(type);
                for (ServiceInfo in : a) {
                    if (!in.getName().equals(name)) {
                        newService = in;
                    }
                }
                tvclient.switchService(newService);
            }
            tvclient.remove(name);
        } else if (tvclient.getServiceType().equals(type)) {
            ui.removePanel(tvclient.returnUI());
            tvclient.disable();
            tvclient.initialized = false;
        }
        
        */

    }
//where service works off  
    public void serviceResolved(ServiceEvent arg0) {
        System.out.println(arg0);
        String address = arg0.getInfo().getHostAddress();
        int port = arg0.getInfo().getPort();
        String type = arg0.getInfo().getType();

        
        for (Client client : clients) {
            if (client.getServiceType().equals(type) && !client.isInitialized()) {
                client.setUp(address, port);
                ui.addPanel(client.returnUI(), client.getName());
                client.setCurrent(arg0.getInfo());
                client.addChoice(arg0.getInfo());
            } else if (client.getServiceType().equals(type)
                    && client.isInitialized()) {
                client.addChoice(arg0.getInfo());
                
            }
        } 
        
        
        //Thermostat Client
     /*   if (ovenclient.getServiceType().equals(type) && !ovenclient.isInitialized()) {
            ovenclient.setUp(address, port);
            ui.addPanel(ovenclient.returnUI(), ovenclient.getName());
            ovenclient.setCurrent(arg0.getInfo());
            ovenclient.addChoice(arg0.getInfo());
        } else if (ovenclient.getServiceType().equals(type)
                && ovenclient.isInitialized()) {
            ovenclient.addChoice(arg0.getInfo());
        } //Kettle Client
    /*    else if (tvclient.getServiceType().equals(type) && !tvclient.isInitialized()) {
            tvclient.setUp(address, port);
            ui.addPanel(tvclient.returnUI(), tvclient.getName());
            tvclient.setCurrent(arg0.getInfo());
            tvclient.addChoice(arg0.getInfo());
        } else if (tvclient.getServiceType().equals(type)
                && tvclient.isInitialized()) {
            tvclient.addChoice(arg0.getInfo());
        } //Printer Client*/
    /*    else if (speakerclient.getServiceType().equals(type) && !speakerclient.isInitialized()) {
            speakerclient.setUp(address, port);
            ui.addPanel(speakerclient.returnUI(), speakerclient.getName());
            speakerclient.setCurrent(arg0.getInfo());
            speakerclient.addChoice(arg0.getInfo());
        } else if (speakerclient.getServiceType().equals(type)
                && speakerclient.isInitialized()) {
            speakerclient.addChoice(arg0.getInfo());
        } //Lights Client
        else if (lightclient.getServiceType().equals(type) && !lightclient.isInitialized()) {
            lightclient.setUp(address, port);
            ui.addPanel(lightclient.returnUI(), lightclient.getName());
            lightclient.setCurrent(arg0.getInfo());
            lightclient.addChoice(arg0.getInfo());
        } else if (lightclient.getServiceType().equals(type)
                && lightclient.isInitialized()) {
            lightclient.addChoice(arg0.getInfo());
        }
    
*/
    }
    public static void main(String[] args) {
        new ClientManager();

    }
}
