package models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/*
 * @OvenModel.java							
 *
 * @author:Karolina Laptas, x14446332
 *
 * @reference sample by Dominic Carr https://moodle.ncirl.ie/course/view.php?id=1473	
 */

public class OvenModel {

    public enum Action {
       STATUS, warm, cool, On, Off, setTimer, reset;
       
   
    }

    private Action action;
    private String message;
    private boolean value;



    public OvenModel(Action action) {
        this.action = action;
    }

    public OvenModel(Action action, String message) {
        this.action = action;
        this.message = message;
    }

    public OvenModel(Action action, String message, boolean value) {
        this.action = action;
        this.message = message;
        this.value = value;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
    
  
}
