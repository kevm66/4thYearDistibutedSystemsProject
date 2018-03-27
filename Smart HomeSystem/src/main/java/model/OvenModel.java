/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Karry
 */
public class OvenModel {

    private Action action;
    private String message;
    private boolean value;
    
    public OvenModel(boolean STATUS, String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public OvenModel(boolean PREHEAT, String msg, boolean preHeat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public OvenModel(boolean PREHEAT) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Action getAction() {
        return action;
    }

    public boolean getValue() {
        return value;}

    public String getMessage() {
        return message;}

    public static class Action {

        public static boolean STATUS, ON, OFF, PREHEAT, COOL;

        public Action() {
        }
    }
    
}
