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


/*File Title:ClientMangaer.java							
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
  //  private final LightClient client = new LightClient();
    

    private ArrayList<Client> clients;

    public ClientManager() {

        clients = new ArrayList<>();
        clients.add(new BedClient());
        clients.add(new OvenClient());
        clients.add(new LightClient());
        clients.add(new KettleClient());

        try {
            jmdns = JmDNS.create(InetAddress.getLocalHost());
            for (Client client : clients) {
                jmdns.addServiceListener(client.getServiceType(), this);
            }
            

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

        //Lights Client
    }

    public static void main(String[] args) {
        new ClientManager();

    }
}
