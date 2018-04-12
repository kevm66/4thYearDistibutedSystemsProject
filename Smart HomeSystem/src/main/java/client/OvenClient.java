/*
 * 
 */
package client;
//import com.google.gson.Gson;
import clientui.OvenUI;
import model.OvenModel;

/*
 *File Title: OvenClient.java							
 *
 * @author:Karolina Laptas, x14446332
 *
 * @reference sample by Dominic Carr https://moodle.ncirl.ie/course/view.php?id=1473	
 */
public class OvenClient extends Client {

    private final String PREHEAT = "Warm"; 
    private final String COOL = "COOL Temperature";
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
     * sends a message to warm the bed.
     */
        public void increase_temp() {
        
    }

    //decrease temp message
    public void decrease_temp() {
       
        
    }

    public void power_off() {
        System.out.println("Oven is turned Off");
    }

    public void power_on() {
        System.out.println("Oven is turned On");
    }
    
    @Override
    public void updatePoll(String msg) {
        if (msg.equals("Oven is 100% heated.")) {
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
