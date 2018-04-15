/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author kevm6
 */
public class TVModel {
 
    private Action  action;
    private String message;
    private boolean value;
    
    public TVModel(boolean STATUS, String msg){
        throw new UnsupportedOperationException("Not yet supported.");
    }
    
    public TVModel(boolean CONNECT, String msg, boolean connect){
        throw new UnsupportedOperationException("Not yet supported.");
    }
    
    public TVModel(boolean CONNECT){
        throw new UnsupportedOperationException("Not yet supported.");
    }
    
    public Action getAction(){
        return action;
    }
    
    public boolean getValue(){
        return value;
    }
    
    public String getMessage(){
        return message;
    }
    
    public static class Action{
        public static boolean STATUS, ON, OFF, COONECT, DISCONNECT, INCREASE_VOLUME;
        public static boolean DECREASE_VOLUME, NEXT_CHANNEL, PREVIOUS_CHANNEL;
        
        public Action(){
            
        }
    }
}
